package model.logic;

import java.awt.List;
import java.io.FileNotFoundException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import model.data_structures.MaxColaCP;
import model.data_structures.MaxHeapCP;
import model.data_structures.Node;

/**
 * Definicion del modelo del mundo
 *
 */
public class Modelo {

	private ArrayList<Comparendos> listComparendos= new ArrayList<>();
	private MaxColaCP<Comparendos> listaCola = new MaxColaCP<>();
	private MaxHeapCP<Comparendos> listaHeap = new MaxHeapCP<>();
	int numeroElementos = 0;

	public void loadComparendos (String comparendosFile)
	{
		JSONParser parser = new JSONParser();

		try {     
			Object obj = parser.parse(new FileReader(comparendosFile));

			JSONObject jsonObject =  (JSONObject) obj;
			JSONArray jsArray = (JSONArray) jsonObject.get("features");

			for(Object o: jsArray) {
				JSONObject comp = (JSONObject) o;	
				JSONObject properties =  (JSONObject) comp.get("properties");
				JSONObject geometry =  (JSONObject) comp.get("geometry");
				JSONArray coordinates = (JSONArray) geometry.get("coordinates");
				Comparendos comparendo = new Comparendos(String.valueOf(comp.get("type")), Integer.parseInt(String.valueOf(properties.get("OBJECTID"))), String.valueOf(properties.get("FECHA_HORA")), String.valueOf(properties.get("CLASE_VEHI")), String.valueOf(properties.get("TIPO_SERVI")), String.valueOf(properties.get("INFRACCION")), String.valueOf(properties.get("DES_INFRAC")), String.valueOf(properties.get("LOCALIDAD")), String.valueOf(geometry.get("type")), String.valueOf(coordinates));
				listComparendos.add(comparendo);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();

		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e){
			e.printStackTrace();
		}
	}

	public void muestra(int n) 
	{
		int contador = n;
		numeroElementos = n;
		Comparendos comparendo = null;

		Random rand = new Random(); //instance of random class
		int upperbound = listComparendos.size();
		int int_random = rand.nextInt(upperbound); 


		long tiempoInCola = System.nanoTime();
		while(n>0)
		{	
			comparendo = listComparendos.get(int_random);
			listaCola.agregar(comparendo);
			int_random = rand.nextInt(upperbound);
			n--;
		}
		long tiempoFINCola = System.nanoTime();
		double demoraMCola = (tiempoFINCola - tiempoInCola)/ 1e6;	
		System.out.println("Tiempo de demora Cola: " + demoraMCola);

		long tiempoInHeap = System.nanoTime();

		while(contador>0)
		{	
			listaHeap.agregar(listComparendos.get(int_random));	
			int_random = rand.nextInt(upperbound);
			contador--;
		}
		long tiempoFINHeap = System.nanoTime();
		double demoraMHeap = (tiempoFINHeap - tiempoInHeap)/ 1e6;

		System.out.println("Tiempo de demora Heap: " + demoraMHeap);
		System.out.println("//////////////////////////");
	}

	public String comparendosMasNorteMaxCola(int n, String tipo)
	{
		int contador = n;
		Comparendos comparendo = null;
		Comparendos c = null;
		
		while(contador>0)
		{
			comparendo = listaHeap.sacarMax();
			listaHeap.sacarMax();
			if(comparendo.getCLASE_VEHI().equals(tipo))
			{
				c = comparendo;
			}
			contador--;
		}
		return c.getOBJECTID() + c.getCLASE_VEHI() + c.getCOORDINATES();
	}

	public String comparendosMasNorteMaxHeap(int n, String tipo)
	{
		int contador = n;
		Comparendos comparendo = null;
		Comparendos c = null;

		while(contador>0)
		{
			comparendo = listaHeap.sacarMax();
			listaHeap.sacarMax();
			if(comparendo.getCLASE_VEHI().equals(tipo))
			{
				c = comparendo;
			}
			contador--;
		}
		return c.getOBJECTID() + c.getCLASE_VEHI() + c.getCOORDINATES();
	}

	public int numeroComparendos() 
	{
		return numeroElementos;
	}


}