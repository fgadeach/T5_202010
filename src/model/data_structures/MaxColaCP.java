package model.data_structures;

import java.util.ArrayList;
import java.util.Iterator;

import model.logic.Comparendos;

public class MaxColaCP <T extends Comparable<T>> implements IMaxColaCP<T>{


	//ATRIBUTOS
	private int tamano;
	private Node<T> primerNode;
	private Node<T> ultimoNode;

	//CONSTRUCTORES
	public MaxColaCP()
	{
		primerNode = null;
		ultimoNode = null;
		tamano = 0;
	}

	public MaxColaCP(T item)
	{
		primerNode = new Node<T>(item);
		ultimoNode = new Node<T>(item);
		tamano =1;
	}

	//METODOS
	public Node<T> darPrimero()
	{
		return primerNode;
	}

	public Node<T> darUltimo()
	{
		return ultimoNode;
	}

	public boolean esVacia() 
	{
		boolean respuesta = false;	
		if (tamano >= 1)
			respuesta =false;
		else
			respuesta = true;
		return respuesta;
	}

	public Integer darNumElementos() 
	{
		return tamano;
	}


	public void agregar(T t) 
	{
		Node <T> contenedor = new Node <T>(t);
		if (tamano == 0)
		{
			primerNode = contenedor;
			ultimoNode = contenedor;
		}
		else
		{
			Node <T> ultimo = ultimoNode;
			ultimo.setNextNode(contenedor);
			ultimoNode = contenedor;
		}

		tamano++;
	}

	public T dequeue() 
	{
		T sacado = null;

		if (tamano ==0)
		{
			System.out.println("No hay elementos para quitar de la fila");
		}

		else
		{
			Node <T> primero = primerNode;
			sacado = primerNode.getItem();
			primerNode = primero.getNext();
			primero.setNextNode(null);
			tamano --;
		}

		return sacado;
	}

	public Iterator<T> iterator() 
	{
		return new Iterator<T>() {
			Node<T> act = null;

			@Override
			public boolean hasNext() {
				if (tamano == 0) {
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
					act = primerNode;
				} else {
					act = act.getNext();
				}

				return act.getItem();
			}
		};
	}

	public T sacarMax() 
	{
		if (esVacia()) {
			return null;
		}
		T obj = darMax();
		dequeue();

		return obj;
	}

	public T darMax() 
	{
		Iterator<T> iter = this.iterator();
		T nodoC =  (T) iter.next();	
		T nodoP =  nodoC;

		while(iter.hasNext()) {
			if(nodoC.compareTo(nodoP) > 1)
			{
				nodoP=nodoC;
			}
			nodoC = (T) iter.next();	
		}

		Node <T> ultimo = primerNode;
		ultimo.setNextNode(primerNode);
		primerNode.setItem(nodoC);

		return nodoC;
	}
}
