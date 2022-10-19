


import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;



public class SortedDoubleLinkedList_STUDENT_Test {
	SortedDoubleLinkedList<String> sortedLinkedString;
	SortedDoubleLinkedList<Double> sortedLinkedDouble;
	SortedDoubleLinkedList<Car> sortedLinkedCar;
	StringComparator stringComparator;
	DoubleComparator doubleComparator;
	CarComparator carComparator;
	
	public Car a=new Car("Toyota", "Supra", 2002);
	public Car b=new Car("Nissan", "Altima", 2008);
	public Car c=new Car("Honda", "Crv", 2010);
	public Car d=new Car("Subaru", "Impreza", 2012);
	public Car e=new Car("Ford", "GT", 2018);
	public Car f=new Car("Chevorlet", "Cruze", 2011);
	//alphabetic order: f e c b d a
	
	@Before
	public void setUp() throws Exception {
		stringComparator = new StringComparator();
		sortedLinkedString = new SortedDoubleLinkedList<String>(stringComparator);
		
		doubleComparator = new DoubleComparator();
		sortedLinkedDouble = new SortedDoubleLinkedList<Double>(doubleComparator);
		
		carComparator = new CarComparator();
		sortedLinkedCar = new SortedDoubleLinkedList<Car>(carComparator);
		
	}

	@After
	public void tearDown() throws Exception {
		stringComparator = null;
		doubleComparator = null;
		carComparator = null;
		sortedLinkedString = null;
		sortedLinkedDouble = null;
		sortedLinkedCar = null;
	}

	@Test
	public void testAddToEnd() {
		try {
			sortedLinkedString.addToEnd("Hello");
			assertTrue("Did not throw an UnsupportedOperationException", false);
		}
		catch (UnsupportedOperationException e)
		{
			assertTrue("Successfully threw an UnsupportedOperationException", true);
		}
		catch (Exception e)
		{
			assertTrue("Threw an exception other than the UnsupportedOperationException", false);
		}
	}

	@Test
	public void testAddToFront() {
		try {
			sortedLinkedString.addToFront("Hello");
			assertTrue("Did not throw an UnsupportedOperationException", false);
		}
		catch (UnsupportedOperationException e)
		{
			assertTrue("Successfully threw an UnsupportedOperationException", true);
		}
		catch (Exception e)
		{
			assertTrue("Threw an exception other than the UnsupportedOperationException", false);
		}
	}

	@Test
	public void testIteratorSuccessfulNext() {
		sortedLinkedCar.add(f);
		sortedLinkedCar.add(e);
		sortedLinkedCar.add(c);
		sortedLinkedCar.add(b);
		ListIterator<Car> iterator = sortedLinkedCar.iterator();
		assertEquals(true, iterator.hasNext());
		assertEquals(f, iterator.next());
		assertEquals(e, iterator.next());
		assertEquals(c, iterator.next());
		assertEquals(true, iterator.hasNext());
	}

	@Test
	public void testIteratorSuccessfulPrevious() {
		sortedLinkedString.add("first");
		sortedLinkedString.add("second");
		sortedLinkedString.add("third");
		ListIterator<String> iterator = sortedLinkedString.iterator();
		assertEquals(true, iterator.hasNext());
		assertEquals("first", iterator.next());
		assertEquals("second", iterator.next());
		assertEquals("third", iterator.next());
		assertEquals(true, iterator.hasPrevious());
		assertEquals("third", iterator.previous());
		assertEquals("second", iterator.previous());
	}
	
	@Test
	public void testIteratorSuccessfulCarPrevious() {
		sortedLinkedCar.add(a);
		sortedLinkedCar.add(b);
		sortedLinkedCar.add(c);
		sortedLinkedCar.add(d);
		//alphabetic order: f e c b d a
		ListIterator<Car> iterator = sortedLinkedCar.iterator();
		assertEquals(true, iterator.hasNext());
		assertEquals(c, iterator.next());
		assertEquals(b, iterator.next());
		assertEquals(d, iterator.next());
		assertEquals(a, iterator.next());
		assertEquals(true, iterator.hasPrevious());
		assertEquals(a, iterator.previous());
		assertEquals(d, iterator.previous());
		assertEquals(b, iterator.previous());
	}
	@Test
	public void testIteratorSuccessfulDoubleNext() {
		sortedLinkedDouble.add(new Double(1));
		sortedLinkedDouble.add(new Double(2));
		sortedLinkedDouble.add(new Double(3));
		sortedLinkedDouble.add(new Double(4));
		ListIterator<Double> iterator = sortedLinkedDouble.iterator();
		assertEquals(true, iterator.hasNext());
		assertEquals(new Double(1), iterator.next());
		assertEquals(new Double(2), iterator.next());
		assertEquals(new Double(3), iterator.next());
		assertEquals(true, iterator.hasNext());	}
	
	@Test
	public void testIteratorSuccessfulDoublePrevious() {
		sortedLinkedDouble.add(new Double(88));
		sortedLinkedDouble.add(new Double(1123));
		sortedLinkedDouble.add(new Double(94));
		sortedLinkedDouble.add(new Double(22));
		ListIterator<Double> iterator = sortedLinkedDouble.iterator();
		assertEquals(new Double(22), iterator.next());
		assertEquals(new Double(88), iterator.next());
		assertEquals(new Double(94), iterator.next());
		assertEquals(true, iterator.hasPrevious());
		assertEquals(new Double(94), iterator.previous());
		assertEquals(true, iterator.hasPrevious());
	}
	
	@Test
	public void testIteratorNoSuchElementException() {
		sortedLinkedCar.add(e);
		sortedLinkedCar.add(d);
		sortedLinkedCar.add(a);
		sortedLinkedCar.add(c);
		//alphabetic order: f e c b d a
		ListIterator<Car> iterator = sortedLinkedCar.iterator();
		
		assertEquals(true, iterator.hasNext());
		assertEquals(e, iterator.next());
		assertEquals(c, iterator.next());
		assertEquals(d, iterator.next());
		assertEquals(true, iterator.hasNext());
		assertEquals(a, iterator.next());
		try{
			//no more elements in list
			iterator.next();
			assertTrue("Did not throw a NoSuchElementException",false);
		}
		catch (NoSuchElementException e)
		{
			assertTrue("Successfully threw a NoSuchElementException",true);
		}
		catch (Exception e)
		{
			assertTrue("Threw an exception other than the NoSuchElementException", false);
		}
	}
	

	@Test
	public void testAddCar() {
		//alphabetic order: f e c b d a
		sortedLinkedCar.add(f);
		sortedLinkedCar.add(e);
		sortedLinkedCar.add(c);
		assertEquals(f, sortedLinkedCar.getFirst());
		assertEquals(c, sortedLinkedCar.getLast());
		sortedLinkedCar.add(b);
		sortedLinkedCar.add(d);
		assertEquals(f, sortedLinkedCar.getFirst());
		assertEquals(d, sortedLinkedCar.getLast());
		assertEquals(d,sortedLinkedCar.retrieveLastElement());
		assertEquals(b, sortedLinkedCar.getLast());
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
			return arg0.getMake().compareTo(arg1.getMake());
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
