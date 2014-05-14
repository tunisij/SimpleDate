package package1;

/**********************************************************************
 * @author John Tunisi
 * 
 * Testing Simple Date
 *********************************************************************/

import org.junit.Test;

import static org.junit.Assert.*;

public class SimpleDateTest {
    @Test
    public void testIsLeapYear()  {
        SimpleDate d = new SimpleDate("3/1/2013");
        assertFalse(d.isLeapYear());
        d  =  new SimpleDate("3/1/2000");
        assertTrue(d.isLeapYear());
        d = new SimpleDate("3/1/1900");
        assertFalse(d.isLeapYear());
        d = new SimpleDate("3/1/2012");
        assertTrue(d.isLeapYear());
    }

    @Test
    public void testToString()  {
        SimpleDate d = new SimpleDate("3/1/2013");
        assertEquals("01 March 2013",d.toString());
        d =  new SimpleDate("08/21/2013");
        assertEquals("21 August 2013",d.toString());
    }

    @Test
    public void testEquals() {
        SimpleDate d1 = new SimpleDate("3/1/2013");
        SimpleDate d2 = new SimpleDate("8/21/2000");
        assertFalse(d1.equals(d2));
        d1 = new SimpleDate("8/21/2000");
        assertTrue(d1.equals(d2));
    }

    @Test
    public void testCompareTo()  {
        SimpleDate d1 = new SimpleDate("3/1/2013");
        SimpleDate d2 = new SimpleDate("8/21/2000");
        assertTrue(d1.compareTo(d2) > 0);
        d1 = new SimpleDate("8/21/2000");
        assertTrue(d1.compareTo(d2) == 0);
        d1 = new SimpleDate("8/21/1994");
        assertTrue(d1.compareTo(d2) < 0);
    }
    
    @Test
    public void testOrdinalDate(){
        SimpleDate d1 = new SimpleDate("1/1/2013");
        assertTrue(d1.ordinalDate() == 1);
        d1 = new SimpleDate("1/5/2013");
        assertTrue(d1.ordinalDate() == 5);
        d1 = new SimpleDate("2/2/2013");
        assertTrue(d1.ordinalDate() == 33);
        d1 = new SimpleDate("12/31/2013");
        assertTrue(d1.ordinalDate() == 365);
        d1 = new SimpleDate("12/31/2012");
        assertTrue(d1.ordinalDate() == 366);
    }
    
    @Test
    public void testOrdinalDate1(){
    	SimpleDate d1 = new SimpleDate("1/12/2013");
    	assertTrue(d1.ordinalDate(1, 1, 2013) == 1);
    	assertTrue(d1.ordinalDate(2, 1, 2013) == 32);
    }
    
    @Test
    public void testIncrement(){
    	SimpleDate d1 = new SimpleDate("1/1/2000");
    	d1.increment();
    	assertTrue(d1.getDay() == 2);
    	d1 = new SimpleDate("12/31/2000");
    	d1.increment();
    	assertTrue(d1.getDay() == 1);
    	assertTrue(d1.getMonth() == 1);
    	assertTrue(d1.getYear() == 2001);
    	d1 = new SimpleDate("1/31/2000");
    	d1.increment();
    	assertTrue(d1.getDay() == 1);
    	d1 = new SimpleDate("2/29/2000");
    	d1.increment();
    	assertTrue(d1.getDay() == 1);
    	d1 = new SimpleDate("2/28/1999");
    	d1.increment();
    	assertTrue(d1.getDay() == 1);
    }
    
    @Test
    public void testDecrement(){
    	SimpleDate d1 = new SimpleDate("1/1/2000");
    	d1.decrement();
    	assertTrue(d1.getDay() == 31);
    	assertTrue(d1.getMonth() == 12);
    	assertTrue(d1.getYear() == 1999);
    	d1 = new SimpleDate("1/10/2000");
    	d1.decrement();
    	assertTrue(d1.getDay() == 9);
    	d1 = new SimpleDate("3/1/2000");
    	d1.decrement();
    	assertTrue(d1.getDay() == 29);
    	assertTrue(d1.getMonth() == 2);
    	d1 = new SimpleDate("3/1/2001");
    	d1.decrement();
    	assertTrue(d1.getDay() == 28);
    	d1 = new SimpleDate("5/1/2000");
    	d1.decrement();
    	assertTrue(d1.getDay() == 30);
    }

    @Test
    public void testNumberSimpleDates(){
    	SimpleDate d1 = new SimpleDate("1/1/2000");
    	assertTrue(SimpleDate.getNumberOfSimpleDates() == 1);
    	SimpleDate d2 = new SimpleDate("1/1/2000");
    	assertTrue(SimpleDate.getNumberOfSimpleDates() == 2);
    }
    
    @Test
    public void testDaysFromNow(){
    	SimpleDate d1 = new SimpleDate("1/1/2000");
    	SimpleDate d2 = new SimpleDate("1/10/2000");
    	d2 = d1.daysFromNow(10);
    	assertTrue(d2.getDay() == 11);
    	d1 = d2.daysFromNow(25);
    	assertTrue(d1.getDay() == 5);
    	assertTrue(d1.getMonth() == 2);
    	d1 = new SimpleDate("1/1/2000");
    	d2 = d1.daysFromNow(-1);
    	assertTrue(d2.getDay() == 31);
    }
    
	@Test
    public void testSaveLoad(){
    	SimpleDate d1 = new SimpleDate("1/1/2000");
    	SimpleDate d2 = new SimpleDate("1/1/2000");
    	d1.save();
    	d1 = new SimpleDate("2/2/2000");
    	d1.load();
    	assertTrue(d1.equals(d2));
    	
    }
    
     @Test
     public void testDaysSince(){
     	SimpleDate d1 = new SimpleDate("1/10/2000");
     	SimpleDate d2 = new SimpleDate("1/1/2000");
     	assertTrue(d1.daysSince(d2) == 9);
     	d2 = new SimpleDate("1/12/2000");
     	assertTrue(d1.daysSince(d2) == -2);
     	
     }
    
    // must use separate
    @Test (expected = IllegalArgumentException.class)
    public void testIncorrectYear() {
        SimpleDate d1 = new SimpleDate("3/1/1700");
    }

    @Test (expected = IllegalArgumentException.class)
    public void testIncorrectMonth() {
        SimpleDate d1 = new SimpleDate("3/-1/2000");
    }
}