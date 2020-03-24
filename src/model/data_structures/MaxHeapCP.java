package model.data_structures;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Vector;

public class MaxHeapCP<T extends Comparable<T>> implements IMaxHeapCp<T>
{
	private Vector<T> elementos;


	public MaxHeapCP()
	{
		elementos = new Vector<T>();
	}

	public MaxHeapCP(int capacity)
	{
		elementos = new Vector<T>(capacity);

	}

	public Integer darNumElementos() {
		return elementos.size();
	}

	public void agregar(T item) 
	{
		elementos.add(item);
		siftUp();
	}

	public T sacarMax() {
		if (esVacia()) throw new NoSuchElementException("Priority queue underflow");
		if (elementos.size() == 1) 
		{
			return elementos.remove(0);
		}
		T elementoBuscado = elementos.firstElement();
		elementos.set(0, elementos.remove(elementos.size()-1));
		siftDown();

		return elementoBuscado;
	}

	public T darMax() {
		if (esVacia()) throw new NoSuchElementException("Priority queue underflow");
		return elementos.firstElement();
	}

	public boolean esVacia() {

		return elementos.size()==0;
	}

	private void siftDown() {
		int k = 0;
		int l = 2*k+1;

		while (l < elementos.size()) {
			int max=l, r=l+1;
			if (r < elementos.size()) { 
				if (elementos.get(r).compareTo(elementos.get(l)) < 0) {
					max++;
				}
			}
			if (elementos.get(k).compareTo(elementos.get(max)) < 0) {
				// switch
				T temp = elementos.get(k);
				elementos.set(k, elementos.get(max));
				elementos.set(max, temp);
				k = max;
				l = 2*k+1;
			} else {
				break;
			}
		}
	}
	private void siftUp() 
	{
		int k = elementos.size() - 1;
		while (k > 0) {
			int p = (k-1)/2;
			T item = elementos.get(k);
			T parent = elementos.get(p);
			if (item.compareTo(parent) > 0) {
				elementos.set(k, parent);
				elementos.set(p, item);
				k = p;
			} else {
				break;
			}
		}
	}
	

	public Iterator<T> iterator() {

		return null;
	}
}
