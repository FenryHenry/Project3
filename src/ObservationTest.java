import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

public class ObservationTest
{

    @Test
    public void testGetValue()
    {
        Observation o = new Observation(5, "YEET");
        
        if(o.getValue() != 5)
        {
            Assert.fail("Incorrect value");
        }
    }
    
    @Test
    public void testGetStid()
    {
        Observation o = new Observation(5, "YEET");
        
        if(!o.getStid().equals("YEET"))
        {
            Assert.fail("Incorrect Station Id");
        }
    }

    @Test
    public void testIsValid()
    {
        Observation o = new Observation(5, "YEET");
        Observation o2 = new Observation(-600, "YEET");
        
        if(!o.isValid())
        {
            Assert.fail("Incorrect isValid");
        }
        if(o2.isValid())
        {
            Assert.fail("Incorrect isValid");
        }
    }
    
    @Test
    public void testToString()
    {
        String expected = "Station ID: YEET, Value: 5.0 is valid";
        Observation o = new Observation(5, "YEET");
        
        assertEquals(o.toString(),expected);
        

    }
    

}
