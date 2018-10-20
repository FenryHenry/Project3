
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class MapData {

	ArrayList<Observation> sradData = new ArrayList<Observation>();
	
	ArrayList<Observation> tairData = new ArrayList<Observation>();
	
	ArrayList<Observation> ta9mData = new ArrayList<Observation>();
	
	Integer amountOfStations = null;
	
	int SIZE = 1000;
	
	int NUMBER_OF_MISSING_OBSERVATIONS = 10;
	
	String TA9M = "TA9M";
	
	String TAIR = "TAIR";
	
	String SRAD = "SRAD";
	
	String STID = "STID";
	
	int STID_POSITION = -1;
	
	int TAIR_POSITION = -1;
	
	int SRAD_POSITION = -1;
	
	int TA9M_POSITION = -1;
	
	String MESONET = "Mesonet";
	
	String directory;
	
	Statistics tairMin;
	
	Statistics tairMax;
	
	Statistics tairAverage;
	
	Statistics ta9mMin;
	
	Statistics ta9mMax;
	
	Statistics ta9mAverage;
	
	Statistics sradMin;
	
	Statistics sradMax;
	
	Statistics sradAverage;
	
	Statistics sradTotal;
	
	String fileName;
	
	GregorianCalendar utcDateTime; 
	
	int year;
	
	int month;
	
	int day;
	
	int hour;
	
	int minute;
	
	int second;
	DecimalFormat df = new DecimalFormat("#.#");
	
	/**
	 * Constructor for the MapData Object
	 * @param YEAR
	 * @param MONTH
	 * @param DAY
	 * @param HOUR
	 * @param MINUTE
	 * @param DIRECTORY
	 */
	MapData(int YEAR, int MONTH, int DAY, int HOUR, int MINUTE, String DIRECTORY)
	{
		year = YEAR;
		month = MONTH;
		day = DAY;
		hour = HOUR;
		minute = MINUTE;
		second = 0;
		directory = DIRECTORY;
    	utcDateTime = new GregorianCalendar(year, month, day, hour, minute, second);
	}

	/**
	 * Returns the directory
	 * @return String directory
	 */
	public String getDirectory() 
	{
		return directory;
	}

	/**
	 * Returns the year
	 * @return int year
	 */
	public int getYear() 
	{
		return year;
	}
	
	/**
	 * Returns the month
	 * @return int month
	 */
	public int getMonth() 
	{
		return month;
	}

	/**
	 * Returns the day
	 * @return int day
	 */
	public int getDay() 
	{
		return day;
	}

	/**
	 * Returns the hour
	 * @return int hour
	 */
	public int getHour()
	{
		return hour;
	}

	/**
	 * Returns the minute
	 * @return int minute
	 */
	public int getMinute() 
	{
		return minute;
	}
	
	/**
	 * Creates file name
	 * @return String filename
	 */
	public String createFileName()
	{
		String filename = String.valueOf(year) + String.format("%02d", month)
		+ String.format("%02d", day) + String.format("%02d", hour) + String.format("%02d", minute) + ".mdf";
		//System.out.println(filename);
		return filename;
	}
	
	/**
     * Creates date name
     * @return String filename
     */
	public String createDateTime()
    {
        String filename = String.valueOf(year) + "-" + String.format("%02d", month) + "-"
        + String.format("%02d", day) + "'T'" + String.format("%02d", hour) + ":"+ String.format("%02d", minute) + ":" + "00 GMT-5";
        //System.out.println(filename);
        return filename;
    }
	
	/**
     * Parses header line to find the position of the appropriate headers 
     * 
     */
	private void parseParamHeader(String inParamStr)
	{
	       
	   String[] toParse = inParamStr.split("\\s+");
	   
	   // Loop through array checking for the index of stid, tair, srad, ta9m
	   for (int i = 0; i < toParse.length; i++)
	   { 
	       if(toParse[i].equals("STID")) 
	       {
	       STID_POSITION = i;
	       }
	       if(toParse[i].equals("TAIR")) 
           {
           TAIR_POSITION = i;
           }
	       if(toParse[i].equals("SRAD")) 
           {
           SRAD_POSITION = i;
           }
	       if(toParse[i].equals("TA9M"))
           {
           TA9M_POSITION = i;       
           }
	       
	   }
	}
	
	/**
	 * Parses data from the file
	 * @throws IOException 
	 * 
	 */
	public void parseFile() throws IOException
	{          
		//An incrementing variable
		int i = 0;
		
		//gets current directory for future use
		String currentDir = new File("").getAbsolutePath();
		
		//creates variables to hold the data we are interested in
		double sradValue = -999;
		double tairValue = -999;
		double ta9mValue = -999;
		
		//Station ID variable
		String stid;
		
		//Puts together the data of given date and time for the file name
		String filename = this.createFileName();
		
		//Initializes the arrays to int SIZE
		sradData = new ArrayList<Observation>();
		tairData = new ArrayList<Observation>();
		ta9mData = new ArrayList<Observation>();
		
		//File data = new File("data");
		//incase file does not exist
		BufferedReader br = null;
		try
		{
			br = new BufferedReader(new FileReader(currentDir + "\\" + directory + "\\" + filename));
		} 
		catch (FileNotFoundException e)
		{
			//e.printStackTrace();
			System.out.println("File does not exist!");
		}
		
        String strg;  // String containing a line of data from the file.
        
        strg = br.readLine(); //throws out header       
        strg = br.readLine(); //throws out second line
        strg = br.readLine(); //throws out third line
        parseParamHeader(strg); // Parses the third line to find which collums to look at
        strg = br.readLine(); //reads in first line containing data
        
        //Loops through string assigning each important value to its respective place in the array
        while (strg != null)
        {   	
        	String[] temp = strg.split("\\s+");
        	
        	stid = temp[STID_POSITION];
        	tairValue = Double.parseDouble(temp[TAIR_POSITION]);
        	sradValue = Double.parseDouble(temp[SRAD_POSITION]);
        	ta9mValue = Double.parseDouble(temp[TA9M_POSITION]);	
        	
        	Observation tairObs = new Observation(tairValue, stid);
        	tairData.add(tairObs);
        
        	Observation sradObs = new Observation(sradValue, stid);
        	sradData.add(sradObs);
        	
        	Observation ta9mObs = new Observation(ta9mValue, stid);
        	ta9mData.add(ta9mObs);
        	
        	i++;
        	strg = br.readLine();
        }
        
        amountOfStations = i;
        br.close();         
	}
	
	
	/**
	 * Calculates the statistics based on paramId
	 * @param inData
	 * @param paramId
	 */
	private void calculateStatistics(ArrayList<Observation> inData, String paramId)
	{
	    if(paramId.equals("TAIR"))// calculate statistics for tair
	    {
	        double sum = 0;
	        double average = 0;
	        tairMin = new Statistics(100000, "default", createDateTime(), amountOfStations, StatsType.MINIMUM);
	        tairMax = new Statistics(-100000, "default", createDateTime(), amountOfStations, StatsType.MAXIMUM);
	        
	        //Loops through array assigning tairMin to the lowest value
	        for(int i = 0; i < inData.size(); i++) //Calculate min
	        {
	            //tests if data is valid
	            if(inData.get(i).isValid() && inData.get(i).getValue() < tairMin.getValue())
	            {           	            
	                tairMin = new Statistics(inData.get(i).getValue(), inData.get(i).getStid(), utcDateTime, amountOfStations, StatsType.MINIMUM);	            
	            }  
	        } 
	        for(int i = 0; i < inData.size(); i++) //Calculate max
	        {
	            //tests if data is valid
	            if(inData.get(i).isValid() && inData.get(i).getValue() > tairMax.getValue()) 
	            {
	                tairMax =  new Statistics(inData.get(i).getValue(), inData.get(i).getStid(), utcDateTime, amountOfStations, StatsType.MAXIMUM);      
	            }
	        } 
	        for(int i = 0; i < inData.size(); i++)//calculate average
	        {
	            //tests if data is valid
	            if(inData.get(i).isValid()) 
	            {
	            sum += inData.get(i).getValue();
	            }
	        }
	        
	        //calculates average
	        average = Double.parseDouble((df.format(sum/tairData.size())));
	                
	        tairAverage = new Statistics(average, MESONET, utcDateTime, amountOfStations, StatsType.AVERAGE);
	    }
	    if(paramId.equals("TA9M"))
	    {
	        double sum = 0;
	        double average = 0; 
	        ta9mMin = new Statistics(100000, "default", createDateTime(), amountOfStations, StatsType.MINIMUM);
	        ta9mMax = new Statistics(-100000, "default", createDateTime(), amountOfStations, StatsType.MAXIMUM);
	        //loops through array assigning ta9mMin to the minimum value
	        for(int i = 0; i < inData.size(); i++)//calculate min
	        {
	            //tests if data is valid
	            if(inData.get(i).isValid() && inData.get(i).getValue() < ta9mMin.getValue())
	            {	           
	                ta9mMin = new Statistics(inData.get(i).getValue(), inData.get(i).getStid(), utcDateTime, amountOfStations, StatsType.MINIMUM);
	            }
	        }
	        for(int i = 0; i < inData.size(); i++)//calculate max
	        {
	            //tests if data is valid
	            if(inData.get(i).isValid() && inData.get(i).getValue() > ta9mMax.getValue()) 
	            {
	                ta9mMax = new Statistics(inData.get(i).getValue(), inData.get(i).getStid(), utcDateTime, amountOfStations, StatsType.MAXIMUM);
	            }
	        }
	        for(int i = 0; i < tairData.size(); i++)//calculate average
	        {
	            //tests if data is valid
	            if(inData.get(i).isValid()) 
	            { 
	            sum += inData.get(i).getValue();
	            }
	        }
	        
	        //calculates average
	        average = Double.parseDouble((df.format(sum/ta9mData.size())));
	        
	        ta9mAverage =  new Statistics(average, MESONET, utcDateTime, amountOfStations, StatsType.AVERAGE);	        
	    }
	    
	    if(paramId.equals("SRAD"))
	    {
	        double sum = 0;
	        double average = 0;	        
	        sradMin = new Statistics(100000, "default", createDateTime(), amountOfStations, StatsType.MINIMUM);
	        sradMax = new Statistics(-100000, "default", createDateTime(), amountOfStations, StatsType.MAXIMUM);
	        //loops through array assigning the lowest value to sradMin
	        for(int i = 0; i < inData.size(); i++)// calculates min
	        {
	            //tests if data is valid
	            if(inData.get(i).isValid() && inData.get(i).getValue() < sradMin.getValue())
	            {
	                sradMin = new Statistics(inData.get(i).getValue(), inData.get(i).getStid(), utcDateTime, amountOfStations, StatsType.MINIMUM);
	            }
	        }
	        for(int i = 0; i < inData.size(); i++)// calculates max
	        {
	            //tests if data is valid
	            if(inData.get(i).isValid() && inData.get(i).getValue() > sradMax.getValue())
	            {
	                sradMax = new Statistics(inData.get(i).getValue(), inData.get(i).getStid(), utcDateTime, amountOfStations, StatsType.MAXIMUM);
	            }
	        }
	        for(int i = 0; i < inData.size(); i++)// calculates average
	        {
	            //tests if data is valid
	            if(inData.get(i).isValid())
	            { 
	            sum += inData.get(i).getValue();      
	            }   
	        }
	        
	        //Calculates average
	        average = Double.parseDouble((df.format(sum/sradData.size())));
	        
	        sradAverage =  new Statistics(average, MESONET, utcDateTime, amountOfStations, StatsType.AVERAGE);
	        sradTotal = new Statistics(sum, MESONET, utcDateTime, amountOfStations, StatsType.TOTAL);
	    }
	}

	/**
	 * returns the minimum air temperature
	 * @return Observation tairMin
	 */
	public Observation getTairMin()
	{  
	    calculateStatistics(tairData, "TAIR");
		return tairMin;
	}	

	/**
	 * returns the maximum air temperature
	 * @return Observation tairMax
	 */
	public Observation getTairMax() 
	{
	    calculateStatistics(tairData, "TAIR");
		return tairMax;
	}

	/**
	 * returns the average air temperature
	 * @return Observation tairAverage
	 */
	public Observation getTairAverage() 
	{	
	    calculateStatistics(tairData, "TAIR");
		return tairAverage;
	}

	/**
	 * returns the minimum air temperature at 9 meters
	 * @return ta9mMin
	 */
	public Observation getTa9mMin() 
	{
	    calculateStatistics(ta9mData,"TA9M");
		return ta9mMin;
	}
	
	/**
	 * returns the maximum air temperature at 9 meters
	 * @return ta9mMax
	 */
	public Observation getTa9mMax()
	{
	    calculateStatistics(ta9mData, "TA9M");
		return ta9mMax;
	}

	/**
	 * returns the average air temperature at 9 meters
	 * @return ta9mAverage
	 */
	public Observation getTa9mAverage() 
	{
	    calculateStatistics(ta9mData,"TA9M");
		return ta9mAverage;
	}

	/**
	 * returns the minimum solar radiation
	 * @return sradMin
	 */
	public Observation getSradMin() 
	{
	    calculateStatistics(sradData, "SRAD");
		return sradMin;
	}

	/**
	 * returns the maximum solar radiation
	 * @return sradMax
	 */
	public Observation getSradMax() 
	{
	    calculateStatistics(sradData, "SRAD");
		return sradMax;
	}

	/**
	 * returns the average solar radiation
	 * @return sradAverage
	 */
	public Observation getSradAverage() 
	{
	    calculateStatistics(sradData, "SRAD");
		return sradAverage;
	}

	/**
	 * returns the total solar radiation
	 * @return sradTotal
	 */
	public Observation getSradTotal() 
	{

	    calculateStatistics(sradData,"SRAD");
		return sradTotal;
	}
	
	/**
	 * Creates a toString for the MapData class
	 * @return fin
	 */
	public String toString()
	{
		String fin = "========================================================\n=== " + String.valueOf(year) + "-" + 
				String.format("%02d", month) + "-" + String.format("%02d", day) +  " " + String.format("%02d", hour) +  ":" +
				String.format("%02d", minute) + " ===\n========================================================\n" +
			    "Maximum Air Temperature[1.5m] = " + this.getTairMax().getValue() + " C at " + this.getTairMax().getStid() + 
				"\nMinimum Air Temperature[1.5m] = " + this.getTairMin().getValue() + " C at " + this.getTairMin().getStid() +
				"\nAverage Air Temperature[1.5m] = " + this.getTairAverage().getValue() + " C at " + this.getTairAverage().getStid() +
				"\n========================================================\n========================================================" +
				"\nMaximum Air Temperature[9.0m] = " + this.getTa9mMax().getValue() + " C at " + this.getTa9mMax().getStid() +
				"\nMinimum Air Temperature[9.0m] = " + this.getTa9mMin().getValue() + " C at " + this.getTa9mMin().getStid() +
				"\nAverage Air Temperature[9.0m] = " + this.getTa9mAverage().getValue() + " C at " + this.getTa9mAverage().getStid() +
				"\n========================================================\n========================================================" +
				"\nMaximim Solar Radiation[1.5m] = " + this.getSradMax().getValue() + " W/m^2 at " + this.getSradMax().getStid() +
				"\nMinimum Solar Radiation[1.5m] = " + this.getSradMin().getValue() + " W/m^2 at " + this.getSradMin().getStid() +
				"\nAverage Solar Radiation[1.5m] = " + this.getSradAverage().getValue() + " W/m^2 at " + this.getSradAverage().getStid() +
				"\n========================================================";
		return fin;
	}
			
}
