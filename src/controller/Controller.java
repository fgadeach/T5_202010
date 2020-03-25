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

import model.data_structures.LinearProbingHashST;
import model.data_structures.Queue;
import model.logic.Comparendos;
import model.logic.Modelo;
import view.View;

public class Controller {

	/*
	 * 
	 */
private LinearProbingHashST<String,Comparendos> listaComparendos;

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
		listaComparendos= new LinearProbingHashST<String,Comparendos>(String.class, Comparendos.class, 40);
		view = new View();
		modelo = new Modelo();
	}

	@SuppressWarnings("null")
	public void run() 
	{
		Scanner lector = new Scanner(System.in);
		boolean fin = false;
		String n = null;
		String m = null;
		Integer i = 1;
		Object datoS = null;
		String respuesta = "";
		String tipo = null;


		while( !fin ){
			view.printMenu();

			int option = lector.nextInt();
			switch(option){
			case 0:
				modelo.loadComparendos(ruta);
				System.out.println("--------- \nDar Fecha : ");
				n = lector.next();
				System.out.println("--------- \nDar clase vehi: ");
				tipo = lector.next();
				System.out.println("--------- \nDar infraccion: ");
				m = lector.next();
				
				modelo.buscarComparendoSC(n, tipo, m);
				
				break;

			default: 
				System.out.println("--------- \n Opcion Invalida !! \n---------");
				break;

			}
		}
	}
}	