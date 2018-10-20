
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Statistics extends Observation implements DateTimeComparable
{

    protected String DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss z";
    
    protected DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);
    
    private GregorianCalendar utcDateTime;
    
    String dateTimeString;
    
    private int numberOfReportingStations;
    
    StatsType statType;
    
    
    Statistics(double value, String stid, String dateTimeStr, int NumberOfValidStations, StatsType inStatType)
    {
        super(value, stid);
        dateTimeString = dateTimeStr;
        numberOfReportingStations = NumberOfValidStations;
        statType = inStatType;

      //TODO create constructor
        
        
    }
    
    Statistics(double value, String stid, GregorianCalendar dateTime, int NumberOfValidStations, StatsType inStatType)
    {
        super(value, stid);
        utcDateTime = dateTime;
        numberOfReportingStations = NumberOfValidStations;
        statType = inStatType;

        
    }
    
    public GregorianCalendar createDateFromString(String dateTimeStr)
    { //"yyyy-MM-dd'T'HH:mm:ss z"        
        String[] temp = dateTimeStr.split("'T'");
        String yMd = temp[0];
        String hMs = temp[1];
        
        String[] yMdT = yMd.split("-");
        int year = Integer.parseInt(yMdT[0]);
        int month = Integer.parseInt(yMdT[1]) - 1;
        int day = Integer.parseInt(yMdT[2]);
        
        String[] hMsT = hMs.split(":");
        int hour = Integer.parseInt(hMsT[0]);
        int minute = Integer.parseInt(hMsT[1]);
        
        String[] space = hMsT[2].split(" ");
        int second = Integer.parseInt(space[0]);
        System.out.println(space[0]);
        GregorianCalendar cal = new GregorianCalendar(year, month, day, hour, minute, second); 
            
        return cal;
    }
    
    public String createStringFromDate(GregorianCalendar calendar)
    {
        String year = Integer.toString(calendar.get(Calendar.YEAR));
        String month = Integer.toString(calendar.get(Calendar.MONTH));
        String day = Integer.toString(calendar.get(Calendar.DAY_OF_MONTH));
        String hour = Integer.toString(calendar.get(Calendar.HOUR));
        String minute = Integer.toString(calendar.get(Calendar.MINUTE));
        String second = Integer.toString(calendar.get(Calendar.SECOND));
        
        String yeet = year + "-" + month + "-" + day + "'T'" + hour + ":" + minute + ":" + second + "";
        
        return yeet;
    }
    
    public int getNumberOfReportingStations()
    {       
        return numberOfReportingStations;
    }
    
    public String getUTCDateTimeString()
    {
        String year = Integer.toString(utcDateTime.get(Calendar.YEAR));
        String month = Integer.toString(utcDateTime.get(Calendar.MONTH));
        String day = Integer.toString(utcDateTime.get(Calendar.DAY_OF_MONTH));
        String hour = Integer.toString(utcDateTime.get(Calendar.HOUR));
        String minute = Integer.toString(utcDateTime.get(Calendar.MINUTE));
        String second = Integer.toString(utcDateTime.get(Calendar.SECOND));
        
        String utcdatetime = year + "-" + month + "-" + day + "'T'" + hour + ":" + minute + ":" + second + "";
        
        return utcdatetime;
    }
    
    public boolean newerThan(GregorianCalendar inDateTime)
    {
        int compVal = utcDateTime.compareTo(inDateTime);
        return compVal > 0;
    }
    
    public boolean olderThan(GregorianCalendar inDateTime)
    {
        int compVal = utcDateTime.compareTo(inDateTime);
        return compVal < 0;

    }
    
    public boolean sameAs(GregorianCalendar inDateTime)
    {
        int compVal = utcDateTime.compareTo(inDateTime);
        return compVal == 0;
    }
    
    public String toString()
    {
        String fin = "STID: " + stid + " Value: " + value;
        return fin;
    }
}
