import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;


public class MapDataTest
{

    @Test
    public void testGetDirectory()
    {
        MapData md = new MapData(2002, 8, 27, 6, 7, "Directory");
        assertEquals(md.getDirectory(), "Directory");
    }
    
    @Test
    public void testGetYear()
    {
        MapData md = new MapData(2002, 8, 27, 6, 7, "Directory");
        assertEquals(md.getYear(), 2002);
    }
    
    @Test
    public void testGetMonth()
    {
        MapData md = new MapData(2002, 8, 27, 6, 7, "Directory");
        assertEquals(md.getMonth(), 8);
    }
    
    @Test
    public void testGetDay()
    {
        MapData md = new MapData(2002, 8, 27, 6, 7, "Directory");
        assertEquals(md.getDay(), 27);
    }

    @Test
    public void testGetHour()
    {
        MapData md = new MapData(2002, 8, 27, 6, 7, "Directory");
        assertEquals(md.getHour(), 6);
    }
    
    @Test
    public void testGetMinutes()
    {
        MapData md = new MapData(2002, 8, 27, 6, 7, "Directory");
        assertEquals(md.getMinute(), 7);
    }
    
    @Test
    public void testCreateFileName()
    {
        MapData md = new MapData(2018, 8, 30, 17, 45, "Directory");
        assertEquals(md.createFileName(), "201808301745.mdf" );
    }
    
    @Test
    public void testCreateDateTime()
    {
        MapData md = new MapData(2018, 8, 30, 17, 45, "Directory");
        assertEquals(md.createDateTime(), "2018-08-30'T'17:45:00 GMT-5" );
    }
    
    @Test
    public void testParseFile() throws IOException
    {
        MapData md = new MapData(2018, 8, 30, 17, 45, "Data");
        String expected = "========================================================\n=== 2018-08-30 17:45 ===\n====================================================="
                + "===\nMaximum Air Temperature[1.5m] = 36.5 C at HOOK\nMinimum Air Temperature[1.5m] = 20.8 C at MIAM\nAverage Air Temperature[1.5m] = 31.3 C at Mesonet\n=============="
                + "==========================================\n========================================================\nMaximum Air Temperature[9.0m] = 34.9 C at HOOK\nMinimum Air Temp"
                + "erature[9.0m] = 20.7 C at MIAM\nAverage Air Temperature[9.0m] = 31.0 C at Mesonet\n========================================================\n===================="
                + "====================================\nMaximim Solar Radiation[1.5m] = 968.0 W/m^2 at SLAP\nMinimum Solar Radiation[1.5m] = 163.0 W/m^2 at MIAM\nAverage So"
                + "lar Radiation[1.5m] = 814.3 W/m^2 at Mesonet\n========================================================";
        md.parseFile();
        assertEquals(md.toString(), expected);
    }
    
    @Test
    public void testGetTairMin() throws IOException
    {
        MapData md = new MapData(2018, 9, 30, 17, 45, "Data");
        
        md.parseFile();
        
        if(md.getTairMin().getValue()!= 20.8 )
        {
            fail("Incorrect minimum value");
        }
    }
    
    @Test
    public void testGetTairMax() throws IOException
    {
        MapData md = new MapData(2018, 8, 30, 17, 45, "Data");
        
        md.parseFile();
        
        if(md.getTairMax().getValue()!= 36.5 )
        {
            fail("Incorrect maximum value");
        }
        
    }
    
    @Test
    public void testGetTairAverage() throws IOException
    {
        MapData md = new MapData(2018, 8, 30, 17, 45, "Data");
        
        md.parseFile();
        
        if(md.getTairAverage().getValue()!= 31.3 )
        {
            fail("Incorrect average value");
        }
    }
    
    @Test
    public void testGetTa9mMin() throws IOException
    {
        MapData md = new MapData(2018, 8, 30, 17, 45, "Data");
        
        md.parseFile();
        
        if(md.getTa9mMin().getValue()!= 20.7 )
        {
            fail("Incorrect minimum value");
        }       
    }
    
    @Test
    public void testGetTa9mMax() throws IOException
    {
        MapData md = new MapData(2018, 8, 30, 17, 45, "Data");
        
        md.parseFile();
        
        if(md.getTa9mMax().getValue()!= 34.9 )
        {
            fail("Incorrect maximum value");
        }        
    }
    
    @Test
    public void testGetTa9mAverage() throws IOException
    {
        MapData md = new MapData(2018, 8, 30, 17, 45, "Data");
        
        md.parseFile();
        
        if(md.getTa9mAverage().getValue()!= 31.0 )
        {
            fail("Incorrect average value");
        }       
    }
    
    @Test
    public void testGetSradMin() throws IOException
    {
        MapData md = new MapData(2018, 8, 30, 17, 45, "Data");
        
        md.parseFile();
        
        if(md.getSradMin().getValue()!= 163.0 )
        {
            fail("Incorrect minimum value");
        }       
    }
    
    @Test
    public void testGetSradMax() throws IOException
    {
        MapData md = new MapData(2018, 8, 30, 17, 45, "Data");
        
        md.parseFile();
        
        if(md.getSradMax().getValue()!= 968.0 )
        {
            fail("Incorrect maximum value");
        }       
    }
    
    @Test
    public void testGetSradaAverage() throws IOException
    {
        MapData md = new MapData(2018, 8, 30, 17, 45, "Data");
        
        md.parseFile();
        
        if(md.getSradAverage().getValue()!= 814.3 )
        {
            fail("Incorrect average value");
        }       
    }
    
    @Test
    public void testGetSradaTotal() throws IOException
    {
        MapData md = new MapData(2018, 8, 30, 17, 45, "Data");
        
        md.parseFile();
        
        if(md.getSradTotal().getValue()!= 97720.0 )
        {
            fail("Incorrect total value");
        }       
    }
    
    @Test
    public void testToString() throws IOException
    {
        MapData md = new MapData(2018, 8, 30, 17, 45, "Data");
        String expected = "========================================================\n=== 2018-08-30 17:45 ===\n====================================================="
                + "===\nMaximum Air Temperature[1.5m] = 36.5 C at HOOK\nMinimum Air Temperature[1.5m] = 20.8 C at MIAM\nAverage Air Temperature[1.5m] = 31.3 C at Mesonet\n=============="
                + "==========================================\n========================================================\nMaximum Air Temperature[9.0m] = 34.9 C at HOOK\nMinimum Air Temp"
                + "erature[9.0m] = 20.7 C at MIAM\nAverage Air Temperature[9.0m] = 31.0 C at Mesonet\n========================================================\n===================="
                + "====================================\nMaximim Solar Radiation[1.5m] = 968.0 W/m^2 at SLAP\nMinimum Solar Radiation[1.5m] = 163.0 W/m^2 at MIAM\nAverage So"
                + "lar Radiation[1.5m] = 814.3 W/m^2 at Mesonet\n========================================================";
        md.parseFile();
        
        assertEquals(md.toString(), expected);
    
    }
    
    
}
