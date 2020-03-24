package model.data_structures;

import java.util.Iterator;

public class DobleListaEncadenada<T extends Comparable<T>> implements InterfazLista<T>
{
	int tamanio;
	private Node<T> primero;

	public DobleListaEncadenada()
	{
		primero=null;
		tamanio=0;
	}

	public DobleListaEncadenada(T item)
	{
		primero = new Node<T>(item);
		tamanio=1;
	}

	@Override
	public Integer getSize() 
	{
		return tamanio;
	}

	@Override
	public void agregarAlInicio(T item) 
	{	
		try 
		{
			Node <T> nuevo = new Node<T>(item);

			if (primero == null)
			{
				primero = nuevo;
			}

			else
			{
				primero.setBackNode(nuevo);
				nuevo.setNextNode(primero);
				primero = nuevo;
			}
			
			tamanio++;
		} 
		catch (Exception e) 
		{
			System.out.println("Se produjo un error. No se pudo agregar a la lista");
		}
	}

	@Override
	public void agregarAlFinal(T item) 
	{
		try 
		{
			Node <T> nuevo = new Node<T> (item);

			if (primero == null)
			{
				primero = nuevo;
			}
			else
			{
				Node<T> actual = primero;

				while(actual.getNext()!=null)
				{
					actual = actual.getNext();
				}

				actual.setNextNode(nuevo);
				nuevo.setBackNode(actual);
			}
			tamanio ++;
		} 
		catch (Exception e) 
		{
			System.out.println("Se produjo un error. No se pudo agregar a la lista");
		}
	}

	@Override
	public void agregarEnPosicion(T item, int pos) 
	{
		try 
		{
			Node<T> nuevo = new Node<T> (item);
			int contador = 0;


			Node<T> actual = primero;
			while (contador!= pos && actual.getNext()!=null)
			{
				contador++;
				actual = actual.getNext();
			}

			Node<T> anterior = actual.getBack();

			actual.setBackNode(nuevo);
			nuevo.setNextNode(actual);
			anterior.setNextNode(nuevo);
			nuevo.setBackNode(anterior);

			tamanio ++;
		} 
		catch (Exception e) 
		{
			System.out.println("Se produjo un error. No se pudo agregar a la lista");
		}

	}

	@Override
	public T darElemento(int pos) 
	{
		try 
		{
			Node<T> actual = primero;
			int contador =0;

			while(contador != pos && actual.getNext()!=null)
			{
				contador++;
				actual = actual.getNext();
			}

			return actual.getItem();
		} 
		catch (Exception e) 
		{
			System.out.println("No se encontro el elemento");
			return null;
		}
	}
	
	public Node darNodoPrimero() {
		return primero;
	}

	@Override
	public void eliminar(T item) 
	{
		try 
		{
			if (primero.getItem().equals(item))
			{
				primero = primero.getNext();
				primero.setBackNode(null);
			}

			else
			{
				Node<T> actual = primero;

				while(actual!=null)
				{
					if (actual.getItem().equals(item) && actual.getNext()==null)
					{
						Node<T> anterior = actual.getBack();
						anterior.setNextNode(null);
					}

					else if(actual.getItem().equals(item))
					{
						Node<T> anterior = actual.getBack();
						Node<T> siguiente = actual.getNext();

						anterior.setNextNode(siguiente);
						siguiente.setBackNode(anterior);

					}

					actual = actual.getNext();
				}
			}

			tamanio --;
		} 
		catch (Exception e) 
		{
			System.out.println("Se produjo un error. No se pudo eliminar");
		}
	}


	@Override
	public void eliminarEnPosicion(int pos) 
	{
		try 
		{
			int contador = 0;

			if (contador == pos)
			{
				primero = primero.getNext();
				primero.setBackNode(null);
			}


			else
			{
				Node<T>actual = primero;
				contador++;

				while (contador != pos && actual.getNext()!=null)
				{
					contador++;
					actual = actual.getNext();
				}

				if (contador == tamanio)
				{
					Node<T> anterior = actual.getBack();
					anterior.setNextNode(null);
				}

				else
				{
					Node<T> anterior = actual.getBack();
					Node<T> siguiente = actual.getNext();

					anterior.setNextNode(siguiente);
					siguiente.setBackNode(anterior);
				}
			}

			tamanio--;
		} 
		catch (Exception e) 
		{
			System.out.println("Se produjo un error. No se pudo eliminar");
		}
	}

	@Override
	public Iterator<T> iterator()
	{
		return new Iterator<T>() {
			Node<T> act = null;

			@Override
			public boolean hasNext() {
				if (tamanio == 0) {
					return false;
				}

				if (act == null) {
					return true;
				}

				return act.getNext() != null; 
			}

			@Override
			public T next() {
				if (act == null) {
					act = primero;
				} else {
					act = act.getNext();
				}

				return act.getItem();
			}
		};
	}
}
