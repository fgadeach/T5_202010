package controller;

import java.io.FileReader;


import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.awt.List;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

import model.data_structures.MaxColaCP;
import model.logic.Comparendos;
import model.logic.Modelo;
import view.View;

public class Controller {

	/*
	 * 
	 */
	private MaxColaCP<Comparendos> listaComparendos;

	/* Instancia del Modelo*/
	private Modelo modelo;

	/* Instancia de la Vista*/
	private View view;

	private Comparendos comparendo;

	public static final String ruta="./data/comparendos.geojson";
	/**
	 * Crear la vista y el modelo del proyecto
	 * @param capacidad tamaNo inicial del arreglo
	 */
	public Controller()
	{
		listaComparendos= new MaxColaCP<Comparendos>();
		view = new View();
		modelo = new Modelo();
	}

	@SuppressWarnings("null")
	public void run() 
	{
		Scanner lector = new Scanner(System.in);
		Comparable<Comparendos>[] arreglos = new Comparendos[listaComparendos.darNumElementos()];
		boolean fin = false;
		Integer n = null;
		Integer i = 1;
		Object datoS = null;
		String respuesta = "";
		String tipo = null;


		while( !fin ){
			view.printMenu();

			int option = lector.nextInt();
			switch(option){
			case 0:
				modelo = new Modelo(); 
				modelo.loadComparendos(ruta);
				System.out.println("--------- \nDar N comparendos a buscar: ");
				n = lector.nextInt();
				modelo.muestra(n);
				System.out.println("Numero actual de elementos " + modelo.numeroComparendos() + "\n---------");	
				break;

			case 1:

				System.out.println("--------- \nDar N comparendos a buscar: ");
				n = lector.nextInt();
				System.out.println("--------- \nDar Clase comparendos a buscar (Solo una): ");
				tipo = lector.next();
				
				long tiempoI = System.nanoTime();
				modelo.comparendosMasNorteMaxCola(n, tipo);
				long tiempoF = System.nanoTime();
				double demora = (tiempoF - tiempoI)/ 1e6;
				System.out.println("diez mas al norte");
				System.out.println("Tiempo de demora: " + demora);
				break;

			case 2:

				System.out.println("--------- \nDar N comparendos a buscar: ");
				n = lector.nextInt();
				System.out.println("--------- \nDar Clase comparendos a buscar (Solo una): ");
				tipo = lector.next();
				long tiempoIH = System.nanoTime();
				modelo.comparendosMasNorteMaxHeap(n, tipo);
				long tiempoFH = System.nanoTime();
				double demoraH = (tiempoFH - tiempoIH)/ 1e6;
				System.out.println("diez mas al norte");
				System.out.println("Tiempo de demora: " + demoraH);

				break;

			default: 
				System.out.println("--------- \n Opcion Invalida !! \n---------");
				break;

			}
		}
	}
}	