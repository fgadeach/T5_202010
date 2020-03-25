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

import model.data_structures.Array;
import model.data_structures.LinearProbingHashST;
import model.data_structures.SeparateChainingHashST;


/**
 * Definicion del modelo del mundo
 *
 */
public class Modelo {

	int numeroElementos = 0;

	int m = 20;

	private SeparateChainingHashST<String, Comparendos> hashSectoresSC = new SeparateChainingHashST<String, Comparendos>(m);

	private LinearProbingHashST<String, Comparendos> hashSectoresLP = new LinearProbingHashST<String, Comparendos>(String.class, Comparendos.class, m);

	public void loadComparendos (String comparendosFile)
	{
		JSONParser parser = new JSONParser();
		try {     
			Object obj = parser.parse(new FileReader(comparendosFile));

			JSONObject jsonObject =  (JSONObject) obj;
			JSONArray jsArray = (JSONArray) jsonObject.get("features");

			for(Object o: jsArray) 
			{
				JSONObject comp = (JSONObject) o;	
				JSONObject properties =  (JSONObject) comp.get("properties");
				JSONObject geometry =  (JSONObject) comp.get("geometry");
				JSONArray coordinates = (JSONArray) geometry.get("coordinates");
				Comparendos comparendo = new Comparendos(String.valueOf(comp.get("type")), Integer.parseInt(String.valueOf(properties.get("OBJECTID"))), String.valueOf(properties.get("FECHA_HORA")), String.valueOf(properties.get("CLASE_VEHI")), String.valueOf(properties.get("TIPO_SERVI")), String.valueOf(properties.get("INFRACCION")), String.valueOf(properties.get("DES_INFRAC")), String.valueOf(properties.get("LOCALIDAD")), String.valueOf(geometry.get("type")), String.valueOf(coordinates));
				String Key = comparendo.getFECHA_HORA()+comparendo.getCLASE_VEHI()+comparendo.getINFRACCION();

				hashSectoresLP.put(Key, comparendo);
				hashSectoresSC.put(Key, comparendo);	
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();

		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e){
			e.printStackTrace();
		}
	}

	public void numeroDuplasLP() 
	{
		System.out.println(hashSectoresLP.size());
	}
	public void numeroDuplasSC() 
	{
		System.out.println(hashSectoresSC.size());
	}

	public int tamanioInicialLP() {
		return m;
	}
	public int tamanioInicialSC() {
		return m;
	}

	public int tamanioFinalLP() {
		return m-hashSectoresLP.size();
	}
	public int tamanioFinalSC() {
		return m-hashSectoresSC.size();
	}

	public double factorCargaLP() 
	{
		return hashSectoresLP.size()/m;
	}

	public double factorCargaSC() 
	{
		return hashSectoresSC.size()/m;
	}

	public int numeroRehashLP() {
		return hashSectoresLP.numeroRehash();
	}
	public int numeroRehashSC() {
		return hashSectoresSC.numeroRehash();
	}

	public void buscarComparendoLP(String fecha, String clase, String infraccion) 
	{
		String Key = fecha+clase+infraccion; 
		System.out.println("OBJECTID: " +hashSectoresLP.get(Key).getOBJECTID() + " FECHA_HORA " + hashSectoresLP.get(Key).getFECHA_HORA()+ " TIPO_SERVI: "+ hashSectoresLP.get(Key).getTIPO_SERVI()+ " CLASE_VEHI: "+hashSectoresLP.get(Key).getCLASE_VEHI() + " INFRACCION: " + hashSectoresLP.get(Key).getINFRACCION());
	}

	public void buscarComparendoSC(String fecha, String clase, String infraccion) 
	{
		String Key = fecha+clase+infraccion;
		System.out.println("OBJECTID: " +hashSectoresLP.get(Key).getOBJECTID() + " FECHA_HORA " + hashSectoresLP.get(Key).getFECHA_HORA()+ " TIPO_SERVI: "+ hashSectoresLP.get(Key).getTIPO_SERVI()+ " CLASE_VEHI: "+hashSectoresLP.get(Key).getCLASE_VEHI() + " INFRACCION: " + hashSectoresLP.get(Key).getINFRACCION());
	}

}

