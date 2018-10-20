import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Test;

public class StatisticsTest
{

    @Test
    public void testCreateDateFromString()
    {
        Statistics s = new Statistics(5, "YEET", "80", 10, StatsType.MAXIMUM);      
        assertEquals(s.createDateFromString("2018-08-30'T'17:45:0 z" ).get(Calendar.YEAR), 2018);
    }
    
    @Test
    public void testCreateStringFromDate()
    {
        Statistics s = new Statistics(5, "YEET", "80", 10, StatsType.MAXIMUM);      
        GregorianCalendar cal = new GregorianCalendar(2018, 8, 30, 17, 45, 0);
        assertEquals(s.createStringFromDate(cal), "2018-8-30'T'5:45:0");
    }
    
    @Test
    public void testGetNumberOfReportingStations()
    {
        Statistics s = new Statistics(5, "YEET", "80", 10, StatsType.MAXIMUM);      
        assertEquals(s.getNumberOfReportingStations(), 10);
    }
    
    @Test
    public void testGetUTCDateTimeString()
    {
        GregorianCalendar cal = new GregorianCalendar(2018, 8, 30, 17, 45, 0);
        Statistics s = new Statistics(5, "YEET", cal, 10, StatsType.MAXIMUM);   
               
        assertEquals(s.getUTCDateTimeString(), "2018-8-30'T'5:45:0");
    }
    
    @Test
    public void testNewerThan()
    {
        GregorianCalendar cal = new GregorianCalendar(2018, 8, 30, 17, 45, 0);
        GregorianCalendar cal2 = new GregorianCalendar(2019, 8, 30, 17, 45, 0);
        Statistics s = new Statistics(5, "YEET", cal, 10, StatsType.MAXIMUM); 
        Statistics s2 = new Statistics(5, "YEET", cal2, 10, StatsType.MAXIMUM);
        
        
        assertEquals(s.newerThan(cal2), false);
        assertEquals(s2.newerThan(cal), true);
    }
    
    @Test
    public void testOlderThan()
    {
        GregorianCalendar cal = new GregorianCalendar(2018, 8, 30, 17, 45, 0);
        GregorianCalendar cal2 = new GregorianCalendar(2019, 8, 30, 17, 45, 0);
        Statistics s = new Statistics(5, "YEET", cal, 10, StatsType.MAXIMUM); 
        Statistics s2 = new Statistics(5, "YEET", cal2, 10, StatsType.MAXIMUM);
        
        
        assertEquals(s.olderThan(cal2), true);
        assertEquals(s2.olderThan(cal), false);
    }
    
    @Test
    public void testSameAs()
    {
        GregorianCalendar cal = new GregorianCalendar(2018, 8, 30, 17, 45, 0);
        GregorianCalendar cal2 = new GregorianCalendar(2019, 8, 30, 17, 45, 0);
        Statistics s = new Statistics(5, "YEET", cal, 10, StatsType.MAXIMUM); 
        Statistics s2 = new Statistics(5, "YEET", cal2, 10, StatsType.MAXIMUM);
        
        
        assertEquals(s.sameAs(cal), true);
        assertEquals(s2.sameAs(cal), false);
    }
    
    @Test
    public void testToString()
    {
        Statistics s = new Statistics(5, "YEET", "80", 10, StatsType.MAXIMUM);      
        assertEquals(s.toString(), "STID: YEET Value: 5.0");
    }

}
