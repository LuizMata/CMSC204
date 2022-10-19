

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class BasicDoubleLinkedList_STUDENT_Test {
	BasicDoubleLinkedList<String> linkedString;
	BasicDoubleLinkedList<Double> linkedDouble;
	BasicDoubleLinkedList<Car> linkedCar;
	StringComparator stringComparator;
	DoubleComparator doubleComparator;
	CarComparator carComparator;
	
	public Car a=new Car("Toyota", "Supra", 2002);
	public Car b=new Car("Nissan", "Altima", 2008);
	public Car c=new Car("Honda", "Crv", 2010);
	public Car d=new Car("Subaru", "Impreza", 2012);
	public Car e=new Car("Ford", "GT", 2018);
	public Car f=new Car("Chevorlet", "Cruze", 2011);

	

	@Before
	public void setUp() throws Exception {
		linkedString = new BasicDoubleLinkedList<String>();
		linkedString.addToEnd("First");
		linkedString.addToEnd("Second");
		linkedString.addToEnd("Third");
		stringComparator = new StringComparator();
		
		linkedDouble = new BasicDoubleLinkedList<Double>();
		linkedDouble.addToEnd(1100.0);
		linkedDouble.addToEnd(2022.0);
		linkedDouble.addToEnd(1234.0);
		doubleComparator = new DoubleComparator();
		
		linkedCar= new BasicDoubleLinkedList<Car>();
		linkedCar.addToEnd(e);
		linkedCar.addToEnd(f);
		linkedCar.addToEnd(a);
		carComparator = new CarComparator();
	}

	@After
	public void tearDown() throws Exception {
		linkedString = null;
		linkedDouble = null;
		linkedCar = null;
		stringComparator = null;
		carComparator = null;
		doubleComparator = null;
	}

	@Test
	public void testGetSize() {
		assertEquals(3,linkedString.getSize());
		assertEquals(3,linkedDouble.getSize());
		assertEquals(3,linkedCar.getSize());
	}
	
	@Test
	public void testAddToEnd() {
		assertEquals("Third", linkedString.getLast());
		linkedString.addToEnd("End");
		assertEquals("End", linkedString.getLast());
		
		assertEquals(a,linkedCar.getLast());
		linkedCar.addToEnd(d);
		assertEquals(d,linkedCar.getLast());
	}
	
	@Test
	public void testAddToFront() {
		assertEquals("First", linkedString.getFirst());
		linkedString.addToFront("Last");
		assertEquals("Last", linkedString.getFirst());
		
		assertEquals(e,linkedCar.getFirst());
		linkedCar.addToFront(a);
		assertEquals(a,linkedCar.getFirst());
	}
	
	@Test
	public void testGetFirst() {
		assertEquals("First", linkedString.getFirst());
		linkedString.addToFront("New");
		assertEquals("New", linkedString.getFirst());
		
		assertEquals(e,linkedCar.getFirst());
		linkedCar.addToFront(a);
		assertEquals(a,linkedCar.getFirst());
	}

	@Test
	public void testGetLast() {
		assertEquals("Third", linkedString.getLast());
		linkedString.addToEnd("New");
		assertEquals("New", linkedString.getLast());
		
		assertEquals(a,linkedCar.getLast());
		linkedCar.addToEnd(d);
		assertEquals(d,linkedCar.getLast());
	}
	
	
	@Test
	public void testRemove() {
		assertEquals(e, linkedCar.getFirst());
		assertEquals(a, linkedCar.getLast());
		linkedCar.addToFront(d);
		assertEquals(d, linkedCar.getFirst());
		linkedCar.remove(d, carComparator);
		assertEquals(e, linkedCar.getFirst());
		linkedCar.addToEnd(b);
		assertEquals(b, linkedCar.getLast());
		linkedCar.remove(b, carComparator);
		assertEquals(a, linkedCar.getLast());
		linkedCar.addToFront(c);
		assertEquals(c, linkedCar.getFirst());
		assertEquals(a, linkedCar.getLast());
		linkedCar.remove(f, carComparator);
		assertEquals(c, linkedCar.getFirst());
		assertEquals(a, linkedCar.getLast());
		
	}


	private class StringComparator implements Comparator<String>
	{

		@Override
		public int compare(String arg0, String arg1) {
			// TODO Auto-generated method stub
			return arg0.compareTo(arg1);
		}
		
	}
	
	private class DoubleComparator implements Comparator<Double>
	{

		@Override
		public int compare(Double arg0, Double arg1) {
			// TODO Auto-generated method stub
			return arg0.compareTo(arg1);
		}
		
	}
	
	private class CarComparator implements Comparator<Car>
	{

		@Override
		public int compare(Car arg0, Car arg1) {
			// Just put cars in alphabetic order by make
			return arg0.toString().compareTo(arg1.toString());
		}
		
	}
	
	private class Car{
		String make;
		String model;
		int year;
		
		public Car(String make, String model, int year){
			this.make = make;
			this.model = model;
			this.year = year;
		}
		
		public String getMake(){
			return make;
		}
		public String getModel(){
			return model;
		}
		public int getYear(){
			return year;
		}
		
		public String toString() {
			return (getMake()+" "+getModel()+" "+getYear());
		}
	}
}
