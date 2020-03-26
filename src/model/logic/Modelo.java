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

	private SeparateChainingHashST<String, Array<Comparendos>> hashSectoresSC = new SeparateChainingHashST<String, Array<Comparendos>>(100);

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
				
				if(hashSectoresLP.contains(Key))
				{
					Key = Key + "a";
					hashSectoresLP.put(Key, comparendo);
				}
				
				else 
				{
					hashSectoresLP.put(Key, comparendo);
				}
				
				if(hashSectoresSC.get(Key) != (null))
				{
					hashSectoresSC.get(Key).append(comparendo);
				}

				else
				{
					hashSectoresSC.put(Key, new Array<Comparendos>());
					hashSectoresSC.get(Key).append(comparendo);
				}
				
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
//	public int numeroRehashSC() {
//		return hashSectoresSC.numeroRehash();
//	}

	public void buscarComparendoLP(String fecha, String clase, String infraccion) 
	{
//		Array<Comparendos> compa = new Array<Comparendos>();
		String Key = fecha+clase+infraccion; 
		Iterator<String> iter = hashSectoresLP.keys();
		while(iter.hasNext())
		{
			String llave = iter.next();
			if(Key.equals(llave)  || llave.equals(Key + "a"))
			{
//				compa.append(hashSectoresLP.get(Key));
				System.out.println("OBJECTID: " + hashSectoresLP.get(llave).getOBJECTID() + "\nFECHA_HORA: " + hashSectoresLP.get(llave).getFECHA_HORA() + "\nTIPO_SERVI: " + hashSectoresLP.get(llave).getTIPO_SERVI() + "\n																						CLASE_VEHI: " + hashSectoresLP.get(llave).getCLASE_VEHI() + "\nINFRACCION: " + hashSectoresLP.get(llave).getINFRACCION());
			}
		}
//		for (int i =0; i < compa.size(); i++)
//		{
//			System.out.println("OBJECTID: " + compa.get(i).getOBJECTID() + " FECHA_HORA " + compa.get(i).getFECHA_HORA()+ " TIPO_SERVI: "+ compa.get(i).getTIPO_SERVI()+ " CLASE_VEHI: "+compa.get(i).getCLASE_VEHI() + " INFRACCION: " + compa.get(i).getINFRACCION());
//		}
	}

	public void buscarComparendoSC(String fecha, String clase, String infraccion) 
	{
		String Key = fecha+clase+infraccion;
		Array<Comparendos> comparendos = new Array<Comparendos>();
 
		comparendos = hashSectoresSC.get(Key);
		
		for(int i =0; i<comparendos.size();i++)
		{
			System.out.println("OBJECTID: " +comparendos.get(i).getOBJECTID() + " FECHA_HORA " + comparendos.get(i).getFECHA_HORA()+ " TIPO_SERVI: "+ comparendos.get(i).getTIPO_SERVI()+ " CLASE_VEHI: "+comparendos.get(i).getCLASE_VEHI() + " INFRACCION: " + comparendos.get(i).getINFRACCION());	

		}
		
	}

}

