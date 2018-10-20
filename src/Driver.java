

import java.io.IOException;


/**
 * 
 * @author Peyton Richardson
 * @version 10/1/18
 * Project 2
 * 
 * This program take input from a file, specifically temperatures at 1.5 and 9 meters above the ground and solar radiation and 
 * create appropriate objects
 */

public class Driver {
	
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException
	{
		    final int YEAR = 2018;

	        final int MONTH = 8;

	        final int DAY = 30;

	        final int HOUR = 17;

	        final int MINUTE = 45;

	 

	        final String directory = "data";

	 
	        MapData mapData = new MapData(YEAR, MONTH, DAY, HOUR, MINUTE, directory);
                	 
	        mapData.parseFile();
	        
	        System.out.println(mapData.getTairMin().getValue());

	        System.out.println(mapData);
	}
}
