package model.data_structures;
public class Node <E extends Comparable<E>>
{
	private Node<E > next;
	private Node<E > back;
	private E item;

	public Node (E item) 
	{
		next = null;
		back=null;
		this.item = item;
	}

	public Node<E> getNext() 
	{
		return next;
	}
	public Node<E> getBack() 
	{
		return back;
	}

	public void setNextNode ( Node<E> next)
	{
		this.next = next;
	}
	public void setBackNode ( Node<E> back)
	{
		this.back = back;
	}	
	public E getItem()
	{
		return item;
	}
	public void setItem (E item) 
	{
		this.item = item;
	}
}