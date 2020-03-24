package test.data_structures;


import static org.junit.Assert.assertTrue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import model.data_structures.IMaxHeapCp;
import model.data_structures.MaxHeapCP;

public class TestMaxHeapCP <T extends Comparable<T>>
{

	private MaxHeapCP<T> heap;

	public static final String obj1 = "obj1";

	public static final String obj2 = "obj2";

	public static final String obj3 = "obj3";

	public static final String obj4 = "obj4";

	public static final String obj5 = "obj5";

	public void setUp() throws Exception
	{
		heap = new MaxHeapCP<T>();
	}

	@SuppressWarnings("unchecked")
	public void add(String... strings)
	{
		for(String str : strings)
		{
			heap.agregar((T) str);
		}
	}

	@Test
	public void testIsEmpty()
	{
		try
		{
			setUp();
			assertTrue("Deberia ser vacio", true);
		}

		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	@Test
	public void testTamano()
	{
		try
		{
			setUp();
			add(obj1,obj2,obj3,obj4,obj5);
			
			assertEquals(5, heap.darNumElementos());
		} 

		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testAgregar()
	{
		try
		{
			setUp();
			add(obj1,obj2,obj3,obj4);
			heap.agregar((T)obj5);
			assertEquals(5, heap.darNumElementos());
		}

		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	@Test
	public void darMax()
	{
		try
		{
			setUp();
			add(obj1,obj2,obj3,obj4);
			assertEquals(obj4, heap.darMax());
		}

		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	@Test
	public void sacarMax()
	{
		try
		{
			setUp();
			add(obj1,obj2,obj3,obj4,obj5);
			assertEquals(obj5, heap.sacarMax());
			assertEquals(4,heap.darNumElementos());	
		}

		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
}