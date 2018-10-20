

public class Observation extends AbstractObservation
{
	
	/** Value of the measurement */
	double value;
		
	/** station id */
	String stid;
	
	/**
	 * Constructor for the Observation object
	 * 
	 * @param val 
	 * @param stationID
	 */
	Observation(double val, String stationID)
	{
		value = val;
		stid = stationID;
	}
	
	/** 
	 * Returns the value of value
	 * @return value
	 */
	public double getValue()
	{
		return value;
	}
	
	/** 
	 * Returns the boolean of valid
	 * @return valid
	 */
	@Override
	public boolean isValid()
	{
	    if(value >= -500)
        {
            valid = true;
        }
        else 
        {
            valid = false;
        }
        //System.out.println(valid + " " +String.valueOf(this.getValue()));
	    return valid;
    }
	
	/** 
	 * Returns the string of stid
	 * @return stid
	 */
	public String getStid()
	{
		return stid;
	}
	
	/** 
	 * Returns the toString of the object
	 * @return fin
	 */
	public String toString()
	{
		String fin = "Station ID: " + stid + ", Value: " + value;
		if(this.isValid()) 
		{
		fin += " is valid";
		}
		else
		{
		fin += " is not valid";
		}
		return fin;
	}
}
