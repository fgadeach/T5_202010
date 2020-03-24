package view;

import model.logic.Modelo;
import model.data_structures.*;

public class View 
{
	/**
	 * Metodo constructor
	 */
	public View()
	{

	}

	public void printMenu()
	{
		System.out.println("0. Carga de datos.");
		System.out.println("1. Mostrar N comparendos mas al norte (MaxCola).");
		System.out.println("2. Mostrar N comparendos mas al norte (MaxHeap).");
	}

	public void printMessage(String mensaje) {

		System.out.println(mensaje);
	}		

	public void printModelo(Modelo modelo)
	{
		// TODO implementar

	}
}
