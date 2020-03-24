package model.data_structures;

public interface InterfazLista <T extends Comparable<T>> extends Iterable<T> 
{
	/*
	 * Dar tamanio
	 */
	Integer getSize();
	
	/*
	 * Agregar al inicio
	 */
	public void agregarAlInicio(T item);
	
	/*
	 * Agregar al final
	 */
	public void agregarAlFinal (T item);
	
	/*
	 * Agregar en la posicion seleccionada
	 */
	public void agregarEnPosicion(T item, int pos);
	
	/*
	 * da el elemento de una posicion
	 */
	public T darElemento(int pos);
	
	/*
	 * elimina el elemento dado
	 */
	public void eliminar(T item);
	
	/*
	 * elimina el elemento en la posicion dada
	 */
	public void eliminarEnPosicion(int pos);
	
	public Node darNodoPrimero();
	
}