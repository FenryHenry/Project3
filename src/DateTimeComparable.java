import java.util.GregorianCalendar;

public interface DateTimeComparable
{
    public static boolean newerThan(GregorianCalendar inDateTimeUTC)
    {
        return true;
    }

    
    public static boolean olderThan(GregorianCalendar inDateTimeUTC)
    {
       
        return true;
    }
    
    public static boolean sameAs(GregorianCalendar inDateTimeUTC)
    {
      
        return true;
    }
}
