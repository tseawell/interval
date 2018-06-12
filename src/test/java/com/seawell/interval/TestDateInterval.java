package com.seawell.interval;

import com.seawell.exceptions.InvalidDataException;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

/**
 User: tseawell
 Date: 12/15/12
 Time: 12:42 AM
 */

@SuppressWarnings({"ConstantConditions", "EqualsWithItself"})
public class TestDateInterval 
{
    public static final String NL = System.lineSeparator();
    
    public static final long MILLIS_IN_A_DAY = TimeUnit.DAYS.toMillis(1);
    
    public static final Calendar CALENDAR = Calendar.getInstance();
    public static final Date TODAY = CALENDAR.getTime();
    
    static { CALENDAR.setTime(TODAY); CALENDAR.add(Calendar.YEAR, -1);}
    public static final Date LAST_YEAR = CALENDAR.getTime();
    
    static { CALENDAR.setTime(TODAY); CALENDAR.add(Calendar.MONTH, -1);}
    public static final Date LAST_MONTH = CALENDAR.getTime();
    
    static { CALENDAR.setTime(TODAY); CALENDAR.add(Calendar.WEEK_OF_YEAR, -1);}
    public static final Date LAST_WEEK = CALENDAR.getTime();
    
    static { CALENDAR.setTime(TODAY); CALENDAR.add(Calendar.DATE, -1);}
    public static final Date YESTERDAY = CALENDAR.getTime();
    
    static { CALENDAR.setTime(TODAY); CALENDAR.add(Calendar.DATE, 1);}
    public static final Date TOMORROW = CALENDAR.getTime();
    
    static { CALENDAR.setTime(TODAY); CALENDAR.add(Calendar.DATE, 6);}
    public static final Date NEXT_WEEK = CALENDAR.getTime();

    static { CALENDAR.setTime(TODAY); CALENDAR.add(Calendar.MONTH, 1);}
    public static final Date NEXT_MONTH = CALENDAR.getTime();
    
    static { CALENDAR.setTime(TODAY); CALENDAR.add(Calendar.YEAR, 1);}
    public static final Date NEXT_YEAR = CALENDAR.getTime();

    @Test
    @org.testng.annotations.Test
    public void testConstructorWithNullLowerEndpoint()
    {
        UpperEndpoint<Date> upperEndpoint = new UpperEndpoint<>(TODAY, Clusivity.INCLUSIVE);
        assertThrows(NullPointerException.class, ()-> new DateInterval(null, upperEndpoint));
    }

    @Test
    @org.testng.annotations.Test
    public void testConstructorWithNullUpperEndpoint()
    {
        LowerEndpoint<Date> lowerEndpoint = new LowerEndpoint<>(TOMORROW, Clusivity.INCLUSIVE);
        assertThrows(NullPointerException.class, ()->new DateInterval(lowerEndpoint, null));
    }
    
    @Test
    @org.testng.annotations.Test
    public void testLowerEndpointGreaterThanUpperEndpoint()
    {
        assertThrows(InvalidDataException.class,
                () -> new DateInterval(new LowerEndpoint<>(TOMORROW, Clusivity.EXCLUSIVE), new UpperEndpoint<>(TODAY, Clusivity.INCLUSIVE)));
        assertThrows(InvalidDataException.class,
                () -> new DateInterval(new LowerEndpoint<>(NEXT_YEAR, Clusivity.EXCLUSIVE), new UpperEndpoint<>(YESTERDAY, Clusivity.INCLUSIVE)));
        assertThrows(InvalidDataException.class,
                () -> new DateInterval(new LowerEndpoint<>(NEXT_YEAR, Clusivity.EXCLUSIVE), new UpperEndpoint<>(NEXT_MONTH, Clusivity.INCLUSIVE)));
    }
    
    @Test
    @org.testng.annotations.Test
    public void testConstructor()
    {
        LowerEndpoint<Date> lowerEndpoint = new LowerEndpoint<>(YESTERDAY, Clusivity.INCLUSIVE);
        UpperEndpoint<Date> upperEndpoint = new UpperEndpoint<>(TOMORROW, Clusivity.INCLUSIVE);
        DateInterval interval = new DateInterval(lowerEndpoint, upperEndpoint);

        assertEquals(lowerEndpoint, interval.getLowerEndpoint());
        assertEquals(upperEndpoint, interval.getUpperEndpoint());
    }

    @Test
    @org.testng.annotations.Test
    public void testEqualsPositive()
    {
        LowerEndpoint<Date> lowerEndpoint = new LowerEndpoint<>(YESTERDAY, Clusivity.INCLUSIVE);
        UpperEndpoint<Date> upperEndpoint = new UpperEndpoint<>(TOMORROW, Clusivity.INCLUSIVE);

        DateInterval firstInterval = new DateInterval(lowerEndpoint, upperEndpoint);
        assertTrue(firstInterval.equals(firstInterval), NL + firstInterval + NL + firstInterval + NL);

        DateInterval secondInterval = new DateInterval(lowerEndpoint, upperEndpoint);
        assertTrue(firstInterval.equals(secondInterval), NL + firstInterval + NL + secondInterval + NL);

        lowerEndpoint = new LowerEndpoint<>(YESTERDAY, Clusivity.EXCLUSIVE);
        upperEndpoint = new UpperEndpoint<>(TOMORROW, Clusivity.EXCLUSIVE);

        DateInterval thirdInterval = new DateInterval(lowerEndpoint, upperEndpoint);
        assertTrue(thirdInterval.equals(thirdInterval), NL + thirdInterval + NL + thirdInterval + NL);

        DateInterval fourthInterval = new DateInterval(lowerEndpoint, upperEndpoint);
        assertTrue(thirdInterval.equals(fourthInterval), NL + thirdInterval + NL + fourthInterval + NL);

        assertFalse(firstInterval.equals(thirdInterval));
        assertFalse(secondInterval.equals(fourthInterval));
    }
    
    @Test
    @org.testng.annotations.Test
    public void testNotEquals()
    {
        DateInterval firstInterval = new DateInterval(new LowerEndpoint<>(LAST_MONTH, Clusivity.INCLUSIVE), new UpperEndpoint<>(LAST_WEEK, Clusivity.INCLUSIVE));
        DateInterval secondInterval = new DateInterval(new LowerEndpoint<>(NEXT_WEEK, Clusivity.EXCLUSIVE), new UpperEndpoint<>(NEXT_MONTH, Clusivity.EXCLUSIVE));

        assertFalse(firstInterval.equals(secondInterval));
                                                                                                                                   
        DateInterval thirdInterval = new DateInterval(new LowerEndpoint<>(YESTERDAY, Clusivity.INCLUSIVE), new UpperEndpoint<>(TOMORROW, Clusivity.INCLUSIVE));
        DateInterval fourthInterval = new DateInterval(new LowerEndpoint<>(LAST_MONTH, Clusivity.EXCLUSIVE), new UpperEndpoint<>(NEXT_MONTH, Clusivity.EXCLUSIVE));

        assertFalse(thirdInterval.equals(fourthInterval));
        assertFalse(firstInterval.equals(fourthInterval));
        assertFalse(secondInterval.equals(thirdInterval));
    }

    @Test
    @org.testng.annotations.Test
    public void testIsEmpty()
    {
        assertFalse(new DateInterval(new LowerEndpoint<>(YESTERDAY, Clusivity.INCLUSIVE), new UpperEndpoint<>(YESTERDAY, Clusivity.INCLUSIVE)).isEmpty());
        assertTrue(new DateInterval(new LowerEndpoint<>(YESTERDAY, Clusivity.INCLUSIVE), new UpperEndpoint<>(YESTERDAY, Clusivity.EXCLUSIVE)).isEmpty());
        assertTrue(new DateInterval(new LowerEndpoint<>(YESTERDAY, Clusivity.EXCLUSIVE), new UpperEndpoint<>(YESTERDAY, Clusivity.INCLUSIVE)).isEmpty());
        assertTrue(new DateInterval(new LowerEndpoint<>(YESTERDAY, Clusivity.EXCLUSIVE), new UpperEndpoint<>(YESTERDAY, Clusivity.EXCLUSIVE)).isEmpty());

        assertFalse(new DateInterval(new LowerEndpoint<>(TODAY, Clusivity.INCLUSIVE), new UpperEndpoint<>(TODAY, Clusivity.INCLUSIVE)).isEmpty());
        assertTrue(new DateInterval(new LowerEndpoint<>(TODAY, Clusivity.INCLUSIVE), new UpperEndpoint<>(TODAY, Clusivity.EXCLUSIVE)).isEmpty());
        assertTrue(new DateInterval(new LowerEndpoint<>(TODAY, Clusivity.EXCLUSIVE), new UpperEndpoint<>(TODAY, Clusivity.INCLUSIVE)).isEmpty());
        assertTrue(new DateInterval(new LowerEndpoint<>(TODAY, Clusivity.EXCLUSIVE), new UpperEndpoint<>(TODAY, Clusivity.EXCLUSIVE)).isEmpty());
    
        assertFalse(new DateInterval(new LowerEndpoint<>(TOMORROW, Clusivity.INCLUSIVE), new UpperEndpoint<>(TOMORROW, Clusivity.INCLUSIVE)).isEmpty());
        assertTrue(new DateInterval(new LowerEndpoint<>(TOMORROW, Clusivity.INCLUSIVE), new UpperEndpoint<>(TOMORROW, Clusivity.EXCLUSIVE)).isEmpty());
        assertTrue(new DateInterval(new LowerEndpoint<>(TOMORROW, Clusivity.EXCLUSIVE), new UpperEndpoint<>(TOMORROW, Clusivity.INCLUSIVE)).isEmpty());
        assertTrue(new DateInterval(new LowerEndpoint<>(TOMORROW, Clusivity.EXCLUSIVE), new UpperEndpoint<>(TOMORROW, Clusivity.EXCLUSIVE)).isEmpty());

        assertFalse(new DateInterval(new LowerEndpoint<>(null, Clusivity.EXCLUSIVE), new UpperEndpoint<>(null, Clusivity.EXCLUSIVE)).isEmpty());
        assertFalse(new DateInterval(new LowerEndpoint<>(null, Clusivity.EXCLUSIVE), new UpperEndpoint<>(YESTERDAY, Clusivity.EXCLUSIVE)).isEmpty());
        assertFalse(new DateInterval(new LowerEndpoint<>(YESTERDAY, Clusivity.EXCLUSIVE), new UpperEndpoint<>(null, Clusivity.EXCLUSIVE)).isEmpty());

        assertFalse(new DateInterval(new LowerEndpoint<>(LAST_WEEK, Clusivity.INCLUSIVE), new UpperEndpoint<>(YESTERDAY, Clusivity.INCLUSIVE)).isEmpty());
        assertFalse(new DateInterval(new LowerEndpoint<>(LAST_WEEK, Clusivity.INCLUSIVE), new UpperEndpoint<>(YESTERDAY, Clusivity.EXCLUSIVE)).isEmpty());
        assertFalse(new DateInterval(new LowerEndpoint<>(LAST_WEEK, Clusivity.EXCLUSIVE), new UpperEndpoint<>(YESTERDAY, Clusivity.INCLUSIVE)).isEmpty());
        assertFalse(new DateInterval(new LowerEndpoint<>(LAST_WEEK, Clusivity.EXCLUSIVE), new UpperEndpoint<>(YESTERDAY, Clusivity.EXCLUSIVE)).isEmpty());
    }

    @Test
    @org.testng.annotations.Test
    public void testIsUnbounded()
    {
        assertFalse(new DateInterval(new LowerEndpoint<>(LAST_WEEK, Clusivity.INCLUSIVE), new UpperEndpoint<>(YESTERDAY, Clusivity.INCLUSIVE)).isUnBounded());
        assertFalse(new DateInterval(new LowerEndpoint<>(TOMORROW, Clusivity.INCLUSIVE), new UpperEndpoint<>(TOMORROW, Clusivity.INCLUSIVE)).isUnBounded());
        assertFalse(new DateInterval(new LowerEndpoint<>(TOMORROW, Clusivity.INCLUSIVE), new UpperEndpoint<>(TOMORROW, Clusivity.EXCLUSIVE)).isUnBounded());
        assertFalse(new DateInterval(new LowerEndpoint<>(TOMORROW, Clusivity.EXCLUSIVE), new UpperEndpoint<>(TOMORROW, Clusivity.EXCLUSIVE)).isUnBounded());
        assertFalse(new DateInterval(new LowerEndpoint<>(TODAY, Clusivity.INCLUSIVE), new UpperEndpoint<>(TODAY, Clusivity.INCLUSIVE)).isUnBounded());
        assertFalse(new DateInterval(new LowerEndpoint<>(TODAY, Clusivity.INCLUSIVE), new UpperEndpoint<>(TODAY, Clusivity.EXCLUSIVE)).isUnBounded());
        assertFalse(new DateInterval(new LowerEndpoint<>(TODAY, Clusivity.EXCLUSIVE), new UpperEndpoint<>(TODAY, Clusivity.EXCLUSIVE)).isUnBounded());
        assertFalse(new DateInterval(new LowerEndpoint<>(YESTERDAY, Clusivity.INCLUSIVE), new UpperEndpoint<>(YESTERDAY, Clusivity.INCLUSIVE)).isUnBounded());
        assertFalse(new DateInterval(new LowerEndpoint<>(YESTERDAY, Clusivity.INCLUSIVE), new UpperEndpoint<>(YESTERDAY, Clusivity.EXCLUSIVE)).isUnBounded());
        assertFalse(new DateInterval(new LowerEndpoint<>(YESTERDAY, Clusivity.EXCLUSIVE), new UpperEndpoint<>(YESTERDAY, Clusivity.EXCLUSIVE)).isUnBounded());
        assertTrue(new DateInterval(new LowerEndpoint<>(TODAY, Clusivity.INCLUSIVE), new UpperEndpoint<>(null, Clusivity.EXCLUSIVE)).isUnBounded());
        assertTrue(new DateInterval(new LowerEndpoint<>(null, Clusivity.EXCLUSIVE), new UpperEndpoint<>(TODAY, Clusivity.INCLUSIVE)).isUnBounded());
        assertTrue(new DateInterval(new LowerEndpoint<>(null, Clusivity.EXCLUSIVE), new UpperEndpoint<>(null, Clusivity.EXCLUSIVE)).isUnBounded());
    }

    @Test
    @org.testng.annotations.Test
    public void testCompareEndpoints()
    {
        DateInterval interval = new DateInterval(new LowerEndpoint<>(TODAY, Clusivity.INCLUSIVE), new UpperEndpoint<>(TODAY, Clusivity.INCLUSIVE));
         assertEquals(0, interval.compare(new UpperEndpoint<>(YESTERDAY, Clusivity.INCLUSIVE), new LowerEndpoint<>(YESTERDAY, Clusivity.INCLUSIVE)));
        assertEquals(-1, interval.compare(new UpperEndpoint<>(YESTERDAY, Clusivity.INCLUSIVE), new LowerEndpoint<>(YESTERDAY, Clusivity.EXCLUSIVE)));
        assertEquals(-1, interval.compare(new UpperEndpoint<>(YESTERDAY, Clusivity.EXCLUSIVE), new LowerEndpoint<>(YESTERDAY, Clusivity.INCLUSIVE)));
        assertEquals(-1, interval.compare(new UpperEndpoint<>(YESTERDAY, Clusivity.EXCLUSIVE), new LowerEndpoint<>(YESTERDAY, Clusivity.EXCLUSIVE)));

        assertEquals(0, interval.compare(new UpperEndpoint<>(TODAY, Clusivity.INCLUSIVE), new LowerEndpoint<>(TODAY, Clusivity.INCLUSIVE)));
        assertEquals(-1, interval.compare(new UpperEndpoint<>(TODAY, Clusivity.INCLUSIVE), new LowerEndpoint<>(TODAY, Clusivity.EXCLUSIVE)));
        assertEquals(-1, interval.compare(new UpperEndpoint<>(TODAY, Clusivity.EXCLUSIVE), new LowerEndpoint<>(TODAY, Clusivity.INCLUSIVE)));
        assertEquals(-1, interval.compare(new UpperEndpoint<>(TODAY, Clusivity.EXCLUSIVE), new LowerEndpoint<>(TODAY, Clusivity.EXCLUSIVE)));

        assertEquals(0, interval.compare(new LowerEndpoint<>(TODAY, Clusivity.INCLUSIVE), new UpperEndpoint<>(TODAY, Clusivity.INCLUSIVE)));
        assertEquals(1, interval.compare(new LowerEndpoint<>(TODAY, Clusivity.INCLUSIVE), new UpperEndpoint<>(TODAY, Clusivity.EXCLUSIVE)));
        assertEquals(1, interval.compare(new LowerEndpoint<>(TODAY, Clusivity.EXCLUSIVE), new UpperEndpoint<>(TODAY, Clusivity.INCLUSIVE)));
        assertEquals(1, interval.compare(new LowerEndpoint<>(TODAY, Clusivity.EXCLUSIVE), new UpperEndpoint<>(TODAY, Clusivity.EXCLUSIVE)));

        assertEquals(-1, interval.compare(new UpperEndpoint<>(TODAY, Clusivity.INCLUSIVE), new LowerEndpoint<>(TOMORROW, Clusivity.INCLUSIVE)));
        assertEquals(-1, interval.compare(new UpperEndpoint<>(TODAY, Clusivity.INCLUSIVE), new LowerEndpoint<>(TOMORROW, Clusivity.EXCLUSIVE)));
        assertEquals(-1, interval.compare(new UpperEndpoint<>(TODAY, Clusivity.EXCLUSIVE), new LowerEndpoint<>(TOMORROW, Clusivity.INCLUSIVE)));
        assertEquals(-1, interval.compare(new UpperEndpoint<>(TODAY, Clusivity.EXCLUSIVE), new LowerEndpoint<>(TOMORROW, Clusivity.EXCLUSIVE)));

        assertEquals(-1, interval.compare(new LowerEndpoint<>(TODAY, Clusivity.INCLUSIVE), new UpperEndpoint<>(TOMORROW, Clusivity.INCLUSIVE)));
        assertEquals(-1, interval.compare(new LowerEndpoint<>(TODAY, Clusivity.INCLUSIVE), new UpperEndpoint<>(TOMORROW, Clusivity.EXCLUSIVE)));
        assertEquals(-1, interval.compare(new LowerEndpoint<>(TODAY, Clusivity.EXCLUSIVE), new UpperEndpoint<>(TOMORROW, Clusivity.INCLUSIVE)));
        assertEquals(-1, interval.compare(new LowerEndpoint<>(TODAY, Clusivity.EXCLUSIVE), new UpperEndpoint<>(TOMORROW, Clusivity.EXCLUSIVE)));

        assertEquals(1, interval.compare(new UpperEndpoint<>(TOMORROW, Clusivity.INCLUSIVE), new LowerEndpoint<>(TODAY, Clusivity.INCLUSIVE)));
        assertEquals(1, interval.compare(new UpperEndpoint<>(TOMORROW, Clusivity.INCLUSIVE), new LowerEndpoint<>(TODAY, Clusivity.EXCLUSIVE)));
        assertEquals(1, interval.compare(new UpperEndpoint<>(TOMORROW, Clusivity.EXCLUSIVE), new LowerEndpoint<>(TODAY, Clusivity.INCLUSIVE)));
        assertEquals(1, interval.compare(new UpperEndpoint<>(TOMORROW, Clusivity.EXCLUSIVE), new LowerEndpoint<>(TODAY, Clusivity.EXCLUSIVE)));

        assertEquals(1, interval.compare(new LowerEndpoint<>(TOMORROW, Clusivity.INCLUSIVE), new UpperEndpoint<>(TODAY, Clusivity.INCLUSIVE)));
        assertEquals(1, interval.compare(new LowerEndpoint<>(TOMORROW, Clusivity.INCLUSIVE), new UpperEndpoint<>(TODAY, Clusivity.EXCLUSIVE)));
        assertEquals(1, interval.compare(new LowerEndpoint<>(TOMORROW, Clusivity.EXCLUSIVE), new UpperEndpoint<>(TODAY, Clusivity.INCLUSIVE)));
        assertEquals(1, interval.compare(new LowerEndpoint<>(TOMORROW, Clusivity.EXCLUSIVE), new UpperEndpoint<>(TODAY, Clusivity.EXCLUSIVE)));
    }

    @Test
    @org.testng.annotations.Test
    public void testEqualBoundsInclusive()
    {
        Date bound = TODAY;
        LowerEndpoint<Date> lowerEndpoint = new LowerEndpoint<>(bound, Clusivity.INCLUSIVE);
        UpperEndpoint<Date> upperEndpoint = new UpperEndpoint<>(bound, Clusivity.INCLUSIVE);
        DateInterval dateInterval = new DateInterval(lowerEndpoint, upperEndpoint);

        assertEquals(lowerEndpoint, dateInterval.getLowerEndpoint());
        assertEquals(upperEndpoint, dateInterval.getUpperEndpoint());
        assertEquals(0, dateInterval.compare(lowerEndpoint, upperEndpoint));
        assertEquals(0, dateInterval.compare(upperEndpoint, lowerEndpoint));
        assertFalse(dateInterval.isEmpty());
        assertFalse(dateInterval.isUnBounded());
        assertTrue(dateInterval.equals(dateInterval));
        assertFalse(dateInterval.isBefore(dateInterval));
        assertFalse(dateInterval.isAfter(dateInterval));
        assertTrue(dateInterval.intersects(dateInterval));
        assertEquals(dateInterval, dateInterval.intersect(dateInterval));
        assertEquals(dateInterval, dateInterval.union(dateInterval));
        assertNull(dateInterval.subtract(dateInterval));
        assertEquals(dateInterval, dateInterval.subInterval(lowerEndpoint, upperEndpoint));
        assertEquals(dateInterval, dateInterval.clone());
        assertTrue(dateInterval.contains(bound));
        assertTrue(dateInterval.contains(lowerEndpoint));
        assertTrue(dateInterval.contains(upperEndpoint));
        assertTrue(dateInterval.contains(dateInterval));
        assertEquals(MILLIS_IN_A_DAY, dateInterval.length().longValue());
    }

    @Test
    @org.testng.annotations.Test
    public void testEqualUnbounded()
    {
        Date bound = null;
        LowerEndpoint<Date> lowerEndpoint = new LowerEndpoint<>(bound, Clusivity.EXCLUSIVE);
        UpperEndpoint<Date> upperEndpoint = new UpperEndpoint<>(bound, Clusivity.EXCLUSIVE);
        DateInterval DateInterval = new DateInterval(lowerEndpoint, upperEndpoint);

        assertEquals(lowerEndpoint, DateInterval.getLowerEndpoint());
        assertEquals(upperEndpoint, DateInterval.getUpperEndpoint());
        assertEquals(-1, DateInterval.compare(lowerEndpoint, upperEndpoint));
        assertEquals(1, DateInterval.compare(upperEndpoint, lowerEndpoint));
        assertFalse(DateInterval.isEmpty());
        assertTrue(DateInterval.isUnBounded());
        assertTrue(DateInterval.equals(DateInterval));
        assertFalse(DateInterval.isBefore(DateInterval));
        assertFalse(DateInterval.isAfter(DateInterval));
        assertTrue(DateInterval.intersects(DateInterval));
        assertEquals(DateInterval, DateInterval.intersect(DateInterval));
        assertEquals(DateInterval, DateInterval.union(DateInterval));
        assertEquals(DateInterval, DateInterval.subInterval(lowerEndpoint, upperEndpoint));
        assertEquals(DateInterval, DateInterval.clone());
        assertTrue(DateInterval.contains(bound));
        assertTrue(DateInterval.contains(lowerEndpoint));
        assertTrue(DateInterval.contains(upperEndpoint));
        assertTrue(DateInterval.contains(DateInterval));
        assertEquals(null, DateInterval.length());
        assertNull(DateInterval.subtract(DateInterval));
    }
    
    @SuppressWarnings("Duplicates")
    @Test
    @org.testng.annotations.Test
    public void testPositiveEmptyInterval()
    {
        Date bound = TOMORROW;
        LowerEndpoint<Date> lowerEndpoint = new LowerEndpoint<>(bound, Clusivity.EXCLUSIVE);
        UpperEndpoint<Date> upperEndpoint = new UpperEndpoint<>(bound, Clusivity.EXCLUSIVE);
        DateInterval DateInterval = new DateInterval(lowerEndpoint, upperEndpoint);

        assertEquals(null, DateInterval.length());
        assertEquals(lowerEndpoint, DateInterval.getLowerEndpoint());
        assertEquals(upperEndpoint, DateInterval.getUpperEndpoint());
        assertEquals(1, DateInterval.compare(lowerEndpoint, upperEndpoint));
        assertEquals(-1, DateInterval.compare(upperEndpoint, lowerEndpoint));
        assertTrue(DateInterval.isEmpty());
        assertFalse(DateInterval.isUnBounded());
        assertTrue(DateInterval.equals(DateInterval));
        assertTrue(DateInterval.isBefore(DateInterval));
        assertTrue(DateInterval.isAfter(DateInterval));
        assertFalse(DateInterval.contains(bound));
        assertTrue(DateInterval.contains(DateInterval.getLowerEndpoint()));
        assertTrue(DateInterval.contains(DateInterval.getUpperEndpoint()));
        assertTrue(DateInterval.contains(DateInterval));
        assertTrue(DateInterval.intersects(DateInterval));
        assertEquals(DateInterval, DateInterval.intersect(DateInterval));
        assertEquals(DateInterval, DateInterval.union(DateInterval));
        List<IInterval<Date>> subtractionIntervals = DateInterval.subtract(DateInterval);
        assertFalse(subtractionIntervals.isEmpty());
        assertEquals(DateInterval, subtractionIntervals.get(0));
        assertEquals(DateInterval, DateInterval.subInterval(lowerEndpoint, upperEndpoint));
        assertEquals(DateInterval, DateInterval.clone());
    }
    
    @SuppressWarnings("Duplicates")
    @Test
    @org.testng.annotations.Test
    public void testNegativeEmptyInterval()
    {
        Date bound = YESTERDAY;
        LowerEndpoint<Date> lowerEndpoint = new LowerEndpoint<>(bound, Clusivity.EXCLUSIVE);
        UpperEndpoint<Date> upperEndpoint = new UpperEndpoint<>(bound, Clusivity.EXCLUSIVE);
        DateInterval DateInterval = new DateInterval(lowerEndpoint, upperEndpoint);

        assertEquals(null, DateInterval.length());
        assertEquals(lowerEndpoint, DateInterval.getLowerEndpoint());
        assertEquals(upperEndpoint, DateInterval.getUpperEndpoint());
        assertEquals(1, DateInterval.compare(lowerEndpoint, upperEndpoint));
        assertEquals(-1, DateInterval.compare(upperEndpoint, lowerEndpoint));
        assertTrue(DateInterval.isEmpty());
        assertFalse(DateInterval.isUnBounded());
        assertTrue(DateInterval.equals(DateInterval));
        assertTrue(DateInterval.isBefore(DateInterval));
        assertTrue(DateInterval.isAfter(DateInterval));
        assertFalse(DateInterval.contains(bound));
        assertTrue(DateInterval.contains(DateInterval.getLowerEndpoint()));
        assertTrue(DateInterval.contains(DateInterval.getUpperEndpoint()));
        assertTrue(DateInterval.contains(DateInterval));
        assertTrue(DateInterval.intersects(DateInterval));
        assertEquals(DateInterval, DateInterval.intersect(DateInterval));
        assertEquals(DateInterval, DateInterval.union(DateInterval));
        List<IInterval<Date>> subtractionIntervals = DateInterval.subtract(DateInterval);
        assertFalse(subtractionIntervals.isEmpty());
        assertEquals(DateInterval, subtractionIntervals.get(0));
        assertEquals(DateInterval, DateInterval.subInterval(lowerEndpoint, upperEndpoint));
        assertEquals(DateInterval, DateInterval.clone());
    }

    @Test
    @org.testng.annotations.Test
    public void testPositiveUnEqualBoundsInclusive()
    {
        Date lowerBound = YESTERDAY;
        Date upperBound = TOMORROW;
        LowerEndpoint<Date> lowerEndpoint = new LowerEndpoint<>(lowerBound, Clusivity.INCLUSIVE);
        UpperEndpoint<Date> upperEndpoint = new UpperEndpoint<>(upperBound, Clusivity.INCLUSIVE);
        DateInterval dateInterval = new DateInterval(lowerEndpoint, upperEndpoint);

        assertEquals(lowerEndpoint, dateInterval.getLowerEndpoint());
        assertEquals(upperEndpoint, dateInterval.getUpperEndpoint());
        assertEquals(-1, dateInterval.compare(lowerEndpoint, upperEndpoint));
        assertEquals(1, dateInterval.compare(upperEndpoint, lowerEndpoint));
        assertFalse(dateInterval.isEmpty());
        assertFalse(dateInterval.isUnBounded());
        assertTrue(dateInterval.equals(dateInterval));
        assertFalse(dateInterval.isBefore(dateInterval));
        assertFalse(dateInterval.isAfter(dateInterval));
        assertTrue(dateInterval.intersects(dateInterval));
        assertEquals(dateInterval, dateInterval.intersect(dateInterval));
        assertEquals(dateInterval, dateInterval.union(dateInterval));
        assertNull(dateInterval.subtract(dateInterval));
        assertEquals(dateInterval, dateInterval.subInterval(lowerEndpoint, upperEndpoint));
        assertEquals(dateInterval, dateInterval.clone());
        assertTrue(dateInterval.contains(lowerBound));
        assertTrue(dateInterval.contains(lowerEndpoint));
        assertTrue(dateInterval.contains(upperBound));
        assertTrue(dateInterval.contains(upperEndpoint));
        assertTrue(dateInterval.contains(dateInterval));
        assertEquals(TimeUnit.DAYS.toMillis(3), dateInterval.length().longValue());
    }

    @Test
    @org.testng.annotations.Test
    public void testNegativeUnEqualBoundsInclusive()
    {
        Date lowerBound = LAST_MONTH;
        Date upperBound = LAST_WEEK;
        LowerEndpoint<Date> lowerEndpoint = new LowerEndpoint<>(lowerBound, Clusivity.INCLUSIVE);
        UpperEndpoint<Date> upperEndpoint = new UpperEndpoint<>(upperBound, Clusivity.INCLUSIVE);
        DateInterval DateInterval = new DateInterval(lowerEndpoint, upperEndpoint);

        assertEquals(lowerEndpoint, DateInterval.getLowerEndpoint());
        assertEquals(upperEndpoint, DateInterval.getUpperEndpoint());
        assertEquals(-1, DateInterval.compare(lowerEndpoint, upperEndpoint));
        assertEquals(1, DateInterval.compare(upperEndpoint, lowerEndpoint));
        assertFalse(DateInterval.isEmpty());
        assertFalse(DateInterval.isUnBounded());
        assertTrue(DateInterval.equals(DateInterval));
        assertFalse(DateInterval.isBefore(DateInterval));
        assertFalse(DateInterval.isAfter(DateInterval));
        assertTrue(DateInterval.intersects(DateInterval));
        assertEquals(DateInterval, DateInterval.intersect(DateInterval));
        assertEquals(DateInterval, DateInterval.union(DateInterval));
        assertNull(DateInterval.subtract(DateInterval));
        assertEquals(DateInterval, DateInterval.subInterval(lowerEndpoint, upperEndpoint));
        assertEquals(DateInterval, DateInterval.clone());
        assertTrue(DateInterval.contains(lowerBound));
        assertTrue(DateInterval.contains(lowerEndpoint));
        assertTrue(DateInterval.contains(upperBound));
        assertTrue(DateInterval.contains(upperEndpoint));
        assertTrue(DateInterval.contains(DateInterval));
    
        long expectedLength = LAST_WEEK.getTime() - LAST_MONTH.getTime() + MILLIS_IN_A_DAY;
        assertEquals(expectedLength, DateInterval.length().longValue());
    }

    @Test
    @org.testng.annotations.Test
    public void testPositiveUnEqualBoundsExclusive()
    {
        Date lowerBound = TOMORROW;
        Date upperBound = NEXT_WEEK;
        LowerEndpoint<Date> lowerEndpoint = new LowerEndpoint<>(lowerBound, Clusivity.EXCLUSIVE);
        UpperEndpoint<Date> upperEndpoint = new UpperEndpoint<>(upperBound, Clusivity.EXCLUSIVE);
        DateInterval dateInterval = new DateInterval(lowerEndpoint, upperEndpoint);
        List<IInterval<Date>> subtractionIntervals = dateInterval.subtract(dateInterval);
    
        assertEquals(lowerEndpoint, dateInterval.getLowerEndpoint());
        assertEquals(upperEndpoint, dateInterval.getUpperEndpoint());
        assertEquals(-1, dateInterval.compare(lowerEndpoint, upperEndpoint));
        assertEquals(1, dateInterval.compare(upperEndpoint, lowerEndpoint));
        assertFalse(dateInterval.isEmpty());
        assertFalse(dateInterval.isUnBounded());
        assertTrue(dateInterval.equals(dateInterval));
        assertFalse(dateInterval.isBefore(dateInterval));
        assertFalse(dateInterval.isAfter(dateInterval));
        assertTrue(dateInterval.intersects(dateInterval));
        assertEquals(dateInterval, dateInterval.intersect(dateInterval));
        assertEquals(dateInterval, dateInterval.union(dateInterval));
        assertNull(subtractionIntervals);
        assertEquals(dateInterval, dateInterval.subInterval(lowerEndpoint, upperEndpoint));
        assertEquals(dateInterval, dateInterval.clone());
        assertFalse(dateInterval.contains(lowerBound));
        assertTrue(dateInterval.contains(lowerEndpoint));
        assertFalse(dateInterval.contains(upperBound));
        assertTrue(dateInterval.contains(upperEndpoint));
        assertTrue(dateInterval.contains(dateInterval));
        long expectedLength = NEXT_WEEK.getTime() - TOMORROW.getTime() + MILLIS_IN_A_DAY;
        expectedLength--;
        expectedLength--;
        assertEquals(expectedLength, dateInterval.length().longValue());
    }

    @Test
    @org.testng.annotations.Test
    public void testNegativeUnEqualBoundsExclusive()
    {
        Date lowerBound = LAST_WEEK;
        Date upperBound = NEXT_MONTH;
        LowerEndpoint<Date> lowerEndpoint = new LowerEndpoint<>(lowerBound, Clusivity.EXCLUSIVE);
        UpperEndpoint<Date> upperEndpoint = new UpperEndpoint<>(upperBound, Clusivity.EXCLUSIVE);
        DateInterval dateInterval = new DateInterval(lowerEndpoint, upperEndpoint);

        assertEquals(lowerEndpoint, dateInterval.getLowerEndpoint());
        assertEquals(upperEndpoint, dateInterval.getUpperEndpoint());
        assertEquals(-1, dateInterval.compare(lowerEndpoint, upperEndpoint));
        assertEquals(1, dateInterval.compare(upperEndpoint, lowerEndpoint));
        assertFalse(dateInterval.isEmpty());
        assertFalse(dateInterval.isUnBounded());
        assertTrue(dateInterval.equals(dateInterval));
        assertFalse(dateInterval.isBefore(dateInterval));
        assertFalse(dateInterval.isAfter(dateInterval));
        assertTrue(dateInterval.intersects(dateInterval));
        assertEquals(dateInterval, dateInterval.intersect(dateInterval));
        assertEquals(dateInterval, dateInterval.union(dateInterval));
        List<IInterval<Date>> subtractionIntervals = dateInterval.subtract(dateInterval);
        assertNull(subtractionIntervals);
        assertEquals(dateInterval, dateInterval.subInterval(lowerEndpoint, upperEndpoint));
        assertEquals(dateInterval, dateInterval.clone());
        assertFalse(dateInterval.contains(lowerBound));
        assertTrue(dateInterval.contains(lowerEndpoint));
        assertFalse(dateInterval.contains(upperBound));
        assertTrue(dateInterval.contains(upperEndpoint));
        assertTrue(dateInterval.contains(dateInterval));
        long expected = (NEXT_MONTH.getTime() - LAST_WEEK.getTime()) + MILLIS_IN_A_DAY;
        expected--; // exclusive upperBound
        expected--; // exclusive lowerBound
        assertEquals(new Long(expected), dateInterval.length());
        assertTrue(dateInterval.length().equals(expected));
        assertEquals(expected, (long) dateInterval.length());
    }

    @Test
    @org.testng.annotations.Test
    public void testIsBeforeValue()
    {
        Date nullLong = null;

        DateInterval interval = new DateInterval(new LowerEndpoint<>(YESTERDAY, Clusivity.INCLUSIVE), new UpperEndpoint<>(TOMORROW, Clusivity.INCLUSIVE));

        assertNull(interval.isBefore(nullLong));
        assertFalse(interval.isBefore(LAST_YEAR));
        assertFalse(interval.isBefore(LAST_MONTH));
        assertFalse(interval.isBefore(LAST_WEEK));
        assertFalse(interval.isBefore(YESTERDAY));
        assertFalse(interval.isBefore(TODAY));
        assertFalse(interval.isBefore(TOMORROW));
        assertTrue(interval.isBefore(NEXT_WEEK));
        assertTrue(interval.isBefore(NEXT_MONTH));
        assertTrue(interval.isBefore(NEXT_YEAR));

        interval = new DateInterval(new LowerEndpoint<>(YESTERDAY, Clusivity.EXCLUSIVE), new UpperEndpoint<>(TOMORROW, Clusivity.EXCLUSIVE));

        assertNull(interval.isBefore(nullLong));
        assertFalse(interval.isBefore(LAST_YEAR));
        assertFalse(interval.isBefore(LAST_MONTH));
        assertFalse(interval.isBefore(LAST_WEEK));
        assertFalse(interval.isBefore(YESTERDAY));
        assertFalse(interval.isBefore(TODAY));
        assertTrue(interval.isBefore(TOMORROW));
        assertTrue(interval.isBefore(NEXT_WEEK));
        assertTrue(interval.isBefore(NEXT_MONTH));
        assertTrue(interval.isBefore(NEXT_YEAR));
    
        DateInterval emptyInterval = new DateInterval(new LowerEndpoint<>(TODAY, Clusivity.EXCLUSIVE), new UpperEndpoint<>(TODAY, Clusivity.EXCLUSIVE));
    
        assertNull(emptyInterval.isBefore(nullLong));
        assertNull(emptyInterval.isBefore(LAST_YEAR));
        assertNull(emptyInterval.isBefore(LAST_MONTH));
        assertNull(emptyInterval.isBefore(LAST_WEEK));
        assertNull(emptyInterval.isBefore(YESTERDAY));
        assertNull(emptyInterval.isBefore(TODAY));
        assertNull(emptyInterval.isBefore(TOMORROW));
        assertNull(emptyInterval.isBefore(NEXT_WEEK));
        assertNull(emptyInterval.isBefore(NEXT_MONTH));
        assertNull(emptyInterval.isBefore(NEXT_YEAR));
    }

    @Test
    @org.testng.annotations.Test
    public void testBeforeIntervalInclusive()
    {
        LowerEndpoint<Date> lowerEndpoint = new LowerEndpoint<>(YESTERDAY, Clusivity.INCLUSIVE);
        UpperEndpoint<Date> upperEndpoint = new UpperEndpoint<>(TOMORROW, Clusivity.INCLUSIVE);
        DateInterval yesterdayAndTomorrow = new DateInterval(lowerEndpoint, upperEndpoint);

        assertFalse(yesterdayAndTomorrow.isBefore(yesterdayAndTomorrow));

        DateInterval lastWeekIn = new DateInterval(new LowerEndpoint<>(LAST_WEEK, Clusivity.INCLUSIVE), upperEndpoint);
        assertFalse(lastWeekIn.isBefore(yesterdayAndTomorrow));
        assertFalse(yesterdayAndTomorrow.isBefore(lastWeekIn));

        DateInterval lastWeekEx = new DateInterval(new LowerEndpoint<>(LAST_WEEK, Clusivity.EXCLUSIVE), upperEndpoint);
        assertFalse(lastWeekEx.isBefore(yesterdayAndTomorrow));
        assertFalse(yesterdayAndTomorrow.isBefore(lastWeekEx));

        DateInterval lastWeekTomorrow = new DateInterval(new LowerEndpoint<>(LAST_WEEK, Clusivity.INCLUSIVE), new UpperEndpoint<>(TOMORROW, Clusivity.EXCLUSIVE));
        assertFalse(yesterdayAndTomorrow.isBefore(lastWeekTomorrow));
        assertFalse(lastWeekTomorrow.isBefore(yesterdayAndTomorrow));

        DateInterval lastWeekTodayIn = new DateInterval(new LowerEndpoint<>(LAST_WEEK, Clusivity.INCLUSIVE), new UpperEndpoint<>(TODAY, Clusivity.INCLUSIVE));
        assertFalse(lastWeekTodayIn.isBefore(yesterdayAndTomorrow));
        assertFalse(yesterdayAndTomorrow.isBefore(lastWeekTodayIn));

        DateInterval lastWeekTodayEx = new DateInterval(new LowerEndpoint<>(LAST_WEEK, Clusivity.INCLUSIVE), new UpperEndpoint<>(YESTERDAY, Clusivity.EXCLUSIVE));
        assertTrue(lastWeekTodayEx.isBefore(yesterdayAndTomorrow));
        assertFalse(yesterdayAndTomorrow.isBefore(lastWeekTodayEx));

        DateInterval lastMonth = new DateInterval(new LowerEndpoint<>(LAST_MONTH, Clusivity.INCLUSIVE), new UpperEndpoint<>(YESTERDAY, Clusivity.EXCLUSIVE));
        assertFalse(yesterdayAndTomorrow.isBefore(lastMonth));
        assertTrue(lastMonth.isBefore(yesterdayAndTomorrow));
    }

    @Test
    @org.testng.annotations.Test
    public void testIsAfterValue()
    {
        Date nullLong = null;
    
        DateInterval interval = new DateInterval(new LowerEndpoint<>(TODAY, Clusivity.INCLUSIVE), new UpperEndpoint<>(TOMORROW, Clusivity.INCLUSIVE));

        assertNull(interval.isAfter(nullLong));
        assertTrue(interval.isAfter(LAST_YEAR));
        assertTrue(interval.isAfter(LAST_MONTH));
        assertTrue(interval.isAfter(LAST_WEEK));
        assertTrue(interval.isAfter(YESTERDAY));
        assertFalse(interval.isAfter(TODAY));
        assertFalse(interval.isAfter(TOMORROW));
        assertFalse(interval.isAfter(NEXT_WEEK));
        assertFalse(interval.isAfter(NEXT_MONTH));
        assertFalse(interval.isAfter(NEXT_YEAR));

        interval = new DateInterval(new LowerEndpoint<>(LAST_YEAR, Clusivity.EXCLUSIVE), new UpperEndpoint<>(LAST_MONTH, Clusivity.EXCLUSIVE));
    
        assertNull(interval.isAfter(nullLong));
        assertTrue(interval.isAfter(LAST_YEAR));
        assertFalse(interval.isAfter(LAST_MONTH));
        assertFalse(interval.isAfter(LAST_WEEK));
        assertFalse(interval.isAfter(YESTERDAY));
        assertFalse(interval.isAfter(TODAY));
        assertFalse(interval.isAfter(TOMORROW));
        assertFalse(interval.isAfter(NEXT_WEEK));
        assertFalse(interval.isAfter(NEXT_MONTH));
        assertFalse(interval.isAfter(NEXT_YEAR));

        interval = new DateInterval(new LowerEndpoint<>(NEXT_MONTH, Clusivity.INCLUSIVE), new UpperEndpoint<>(NEXT_YEAR, Clusivity.INCLUSIVE));

        assertNull(interval.isAfter(nullLong));
        assertTrue(interval.isAfter(LAST_YEAR));
        assertTrue(interval.isAfter(LAST_MONTH));
        assertTrue(interval.isAfter(LAST_WEEK));
        assertTrue(interval.isAfter(YESTERDAY));
        assertTrue(interval.isAfter(TODAY));
        assertTrue(interval.isAfter(TOMORROW));
        assertTrue(interval.isAfter(NEXT_WEEK));
        assertFalse(interval.isAfter(NEXT_MONTH));
        assertFalse(interval.isAfter(NEXT_YEAR));
    
        DateInterval emptyInterval = new DateInterval(new LowerEndpoint<>(TODAY, Clusivity.EXCLUSIVE), new UpperEndpoint<>(TODAY, Clusivity.EXCLUSIVE));
    
        assertNull(emptyInterval.isAfter(nullLong));
        assertNull(emptyInterval.isAfter(LAST_YEAR));
        assertNull(emptyInterval.isAfter(LAST_MONTH));
        assertNull(emptyInterval.isAfter(LAST_WEEK));
        assertNull(emptyInterval.isAfter(YESTERDAY));
        assertNull(emptyInterval.isAfter(TODAY));
        assertNull(emptyInterval.isAfter(TOMORROW));
        assertNull(emptyInterval.isAfter(NEXT_WEEK));
        assertNull(emptyInterval.isAfter(NEXT_MONTH));
        assertNull(emptyInterval.isAfter(NEXT_YEAR));
    
    }

    @Test
    @org.testng.annotations.Test
    public void testIsAfterInterval()
    {
        DateInterval interval = new DateInterval(new LowerEndpoint<>(YESTERDAY, Clusivity.INCLUSIVE), new UpperEndpoint<>(TOMORROW, Clusivity.INCLUSIVE));

        assertTrue(interval.isAfter(new DateInterval(new LowerEndpoint<>(LAST_YEAR, Clusivity.INCLUSIVE), new UpperEndpoint<>(LAST_YEAR, Clusivity.EXCLUSIVE))));
        assertTrue(interval.isAfter(new DateInterval(new LowerEndpoint<>(LAST_YEAR, Clusivity.INCLUSIVE), new UpperEndpoint<>(LAST_MONTH, Clusivity.EXCLUSIVE))));
        assertTrue(interval.isAfter(new DateInterval(new LowerEndpoint<>(LAST_MONTH, Clusivity.INCLUSIVE), new UpperEndpoint<>(LAST_WEEK, Clusivity.INCLUSIVE))));
        assertTrue(interval.isAfter(new DateInterval(new LowerEndpoint<>(LAST_MONTH, Clusivity.INCLUSIVE), new UpperEndpoint<>(YESTERDAY, Clusivity.EXCLUSIVE))));
        assertTrue(interval.isAfter(new DateInterval(new LowerEndpoint<>(YESTERDAY, Clusivity.EXCLUSIVE), new UpperEndpoint<>(YESTERDAY, Clusivity.EXCLUSIVE))));
        assertFalse(interval.isAfter(new DateInterval(new LowerEndpoint<>(YESTERDAY, Clusivity.INCLUSIVE), new UpperEndpoint<>(YESTERDAY, Clusivity.INCLUSIVE))));
        assertFalse(interval.isAfter(new DateInterval(new LowerEndpoint<>(TOMORROW, Clusivity.EXCLUSIVE), new UpperEndpoint<>(NEXT_MONTH, Clusivity.EXCLUSIVE))));
        assertFalse(interval.isAfter(new DateInterval(new LowerEndpoint<>(NEXT_WEEK, Clusivity.EXCLUSIVE), new UpperEndpoint<>(NEXT_MONTH, Clusivity.EXCLUSIVE))));
    }

    @Test
    @org.testng.annotations.Test
    public void testLength()
    {
        long expectedLength = (LAST_MONTH.getTime() - LAST_YEAR.getTime()) + MILLIS_IN_A_DAY;
        
        DateInterval interval = new DateInterval(new LowerEndpoint<>(LAST_YEAR, Clusivity.INCLUSIVE), new UpperEndpoint<>(LAST_MONTH, Clusivity.INCLUSIVE));
        assertEquals(expectedLength, interval.length().longValue());

        interval = new DateInterval(new LowerEndpoint<>(LAST_YEAR, Clusivity.INCLUSIVE), new UpperEndpoint<>(LAST_MONTH, Clusivity.EXCLUSIVE));
        assertEquals(--expectedLength, interval.length().longValue());

        interval = new DateInterval(new LowerEndpoint<>(LAST_YEAR, Clusivity.EXCLUSIVE), new UpperEndpoint<>(LAST_MONTH, Clusivity.INCLUSIVE));
        assertEquals(expectedLength, interval.length().longValue());

        interval = new DateInterval(new LowerEndpoint<>(LAST_YEAR, Clusivity.EXCLUSIVE), new UpperEndpoint<>(LAST_MONTH, Clusivity.EXCLUSIVE));
        assertEquals(--expectedLength, interval.length().longValue());
    
        expectedLength = (YESTERDAY.getTime() - LAST_WEEK.getTime()) + MILLIS_IN_A_DAY;
        interval = new DateInterval(new LowerEndpoint<>(LAST_WEEK, Clusivity.INCLUSIVE), new UpperEndpoint<>(YESTERDAY, Clusivity.INCLUSIVE));
        assertEquals(expectedLength, interval.length().longValue());

        interval = new DateInterval(new LowerEndpoint<>(LAST_WEEK, Clusivity.INCLUSIVE), new UpperEndpoint<>(YESTERDAY, Clusivity.EXCLUSIVE));
        assertEquals(--expectedLength, interval.length().longValue());

        interval = new DateInterval(new LowerEndpoint<>(LAST_WEEK, Clusivity.EXCLUSIVE), new UpperEndpoint<>(YESTERDAY, Clusivity.INCLUSIVE));
        assertEquals(expectedLength, interval.length().longValue());

        interval = new DateInterval(new LowerEndpoint<>(LAST_WEEK, Clusivity.EXCLUSIVE), new UpperEndpoint<>(YESTERDAY, Clusivity.EXCLUSIVE));
        assertEquals(--expectedLength, interval.length().longValue());
    
    
        expectedLength = MILLIS_IN_A_DAY;
        interval = new DateInterval(new LowerEndpoint<>(TODAY, Clusivity.INCLUSIVE), new UpperEndpoint<>(TODAY, Clusivity.INCLUSIVE));
        assertEquals(expectedLength, interval.length().longValue());
        
        interval = new DateInterval(new LowerEndpoint<>(TODAY, Clusivity.EXCLUSIVE), new UpperEndpoint<>(TODAY, Clusivity.EXCLUSIVE));
        assertNull(interval.length());
    
    
        expectedLength = (NEXT_MONTH.getTime() - NEXT_WEEK.getTime()) + MILLIS_IN_A_DAY;
        interval = new DateInterval(new LowerEndpoint<>(NEXT_WEEK, Clusivity.INCLUSIVE), new UpperEndpoint<>(NEXT_MONTH, Clusivity.INCLUSIVE));
        assertEquals(expectedLength, interval.length().longValue());

        interval = new DateInterval(new LowerEndpoint<>(NEXT_WEEK, Clusivity.INCLUSIVE), new UpperEndpoint<>(NEXT_MONTH, Clusivity.EXCLUSIVE));
        assertEquals(--expectedLength, interval.length().longValue());

        interval = new DateInterval(new LowerEndpoint<>(NEXT_WEEK, Clusivity.EXCLUSIVE), new UpperEndpoint<>(NEXT_MONTH, Clusivity.INCLUSIVE));
        assertEquals(expectedLength, interval.length().longValue());

        interval = new DateInterval(new LowerEndpoint<>(NEXT_WEEK, Clusivity.EXCLUSIVE), new UpperEndpoint<>(NEXT_MONTH, Clusivity.EXCLUSIVE));
        assertEquals(--expectedLength, interval.length().longValue());
    
        interval = new DateInterval(new LowerEndpoint<>(null, Clusivity.EXCLUSIVE), new UpperEndpoint<>(null, Clusivity.EXCLUSIVE));
        assertNull(interval.length());
    }
    
    @SuppressWarnings("Duplicates")
    @Test
    @org.testng.annotations.Test
    public void testPositiveNullSubInterval()
    {
        LowerEndpoint<Date> lowerEndpoint = new LowerEndpoint<>(TODAY, Clusivity.INCLUSIVE);
        UpperEndpoint<Date> upperEndpoint = new UpperEndpoint<>(NEXT_MONTH, Clusivity.INCLUSIVE);

        DateInterval interval = new DateInterval(lowerEndpoint, upperEndpoint);
        DateInterval subInterval = interval.subInterval(lowerEndpoint, upperEndpoint);
        assertNotNull(subInterval);
        assertEquals(interval, subInterval);

        lowerEndpoint = new LowerEndpoint<>(TODAY, Clusivity.EXCLUSIVE);
        upperEndpoint = new UpperEndpoint<>(TODAY, Clusivity.EXCLUSIVE);
        subInterval = interval.subInterval(lowerEndpoint, upperEndpoint);
        String message = interval + ".subInterval(" + lowerEndpoint + ", " + upperEndpoint + ")";
        assertEquals(new DateInterval(lowerEndpoint, upperEndpoint), subInterval, message);

        lowerEndpoint = new LowerEndpoint<>(TODAY, Clusivity.INCLUSIVE);
        upperEndpoint = new UpperEndpoint<>(NEXT_YEAR, Clusivity.EXCLUSIVE);
        subInterval = interval.subInterval(lowerEndpoint, upperEndpoint);
        assertNull(subInterval, interval + ".subInterval(" + lowerEndpoint + ", " + upperEndpoint + ")");

        lowerEndpoint = new LowerEndpoint<>(TODAY, Clusivity.EXCLUSIVE);
        upperEndpoint = new UpperEndpoint<>(NEXT_YEAR, Clusivity.INCLUSIVE);
        subInterval = interval.subInterval(lowerEndpoint, upperEndpoint);
        assertNull(subInterval, interval + ".subInterval(" + lowerEndpoint + ", " + upperEndpoint + ")");

        lowerEndpoint = new LowerEndpoint<>(TODAY, Clusivity.EXCLUSIVE);
        upperEndpoint = new UpperEndpoint<>(NEXT_YEAR, Clusivity.EXCLUSIVE);
        subInterval = interval.subInterval(lowerEndpoint, upperEndpoint);
        assertNull(subInterval, interval + ".subInterval(" + lowerEndpoint + ", " + upperEndpoint + ")");
    }
    
    @SuppressWarnings("Duplicates")
    @Test
    @org.testng.annotations.Test
    public void testNegativeNullSubInterval()
    {
        LowerEndpoint<Date> lowerEndpoint = new LowerEndpoint<>(LAST_MONTH, Clusivity.INCLUSIVE);
        UpperEndpoint<Date> upperEndpoint = new UpperEndpoint<>(LAST_WEEK, Clusivity.INCLUSIVE);

        DateInterval interval = new DateInterval(lowerEndpoint, upperEndpoint);
        DateInterval subInterval = interval.subInterval(lowerEndpoint, upperEndpoint);
        assertNotNull(subInterval);
        assertEquals(interval, subInterval);

        lowerEndpoint = new LowerEndpoint<>(LAST_WEEK, Clusivity.EXCLUSIVE);
        upperEndpoint = new UpperEndpoint<>(LAST_WEEK, Clusivity.EXCLUSIVE);
        subInterval = interval.subInterval(lowerEndpoint, upperEndpoint);
        String message = interval + ".subInterval(" + lowerEndpoint + ", " + upperEndpoint + ")";
        assertEquals(new DateInterval(lowerEndpoint, upperEndpoint), subInterval, message);

        lowerEndpoint = new LowerEndpoint<>(LAST_YEAR, Clusivity.INCLUSIVE);
        upperEndpoint = new UpperEndpoint<>(LAST_WEEK, Clusivity.EXCLUSIVE);
        subInterval = interval.subInterval(lowerEndpoint, upperEndpoint);
        assertNull(subInterval, interval + ".subInterval(" + lowerEndpoint + ", " + upperEndpoint + ")");

        lowerEndpoint = new LowerEndpoint<>(LAST_YEAR, Clusivity.EXCLUSIVE);
        upperEndpoint = new UpperEndpoint<>(LAST_WEEK, Clusivity.INCLUSIVE);
        subInterval = interval.subInterval(lowerEndpoint, upperEndpoint);
        assertNull(subInterval, interval + ".subInterval(" + lowerEndpoint + ", " + upperEndpoint + ")");

        lowerEndpoint = new LowerEndpoint<>(LAST_YEAR, Clusivity.EXCLUSIVE);
        upperEndpoint = new UpperEndpoint<>(LAST_WEEK, Clusivity.EXCLUSIVE);
        subInterval = interval.subInterval(lowerEndpoint, upperEndpoint);
        assertNull(subInterval, interval + ".subInterval(" + lowerEndpoint + ", " + upperEndpoint + ")");
    }
    
    @SuppressWarnings("Duplicates")
    @Test
    @org.testng.annotations.Test
    public void testPositiveSubInterval()
    {
        LowerEndpoint<Date> lowerEndpoint = new LowerEndpoint<>(TOMORROW, Clusivity.INCLUSIVE);
        UpperEndpoint<Date> upperEndpoint = new UpperEndpoint<>(NEXT_MONTH, Clusivity.INCLUSIVE);
        DateInterval interval = new DateInterval(lowerEndpoint, upperEndpoint);

        UpperEndpoint<Date> newUpperEndpoint = new UpperEndpoint<>(NEXT_WEEK, Clusivity.INCLUSIVE);
        DateInterval subInterval = interval.subInterval(lowerEndpoint, newUpperEndpoint);
        assertTrue(new DateInterval(lowerEndpoint, newUpperEndpoint).equals(subInterval));

        subInterval = interval.subInterval(new LowerEndpoint<>(TOMORROW, Clusivity.INCLUSIVE), new UpperEndpoint<>(NEXT_MONTH, Clusivity.EXCLUSIVE));
        DateInterval expectedSubInterval = new DateInterval(new LowerEndpoint<>(TOMORROW, Clusivity.INCLUSIVE), new UpperEndpoint<>(NEXT_MONTH, Clusivity.EXCLUSIVE));
        assertEquals(expectedSubInterval, subInterval);

        subInterval = interval.subInterval(new LowerEndpoint<>(TOMORROW, Clusivity.EXCLUSIVE), new UpperEndpoint<>(NEXT_MONTH, Clusivity.INCLUSIVE));
        expectedSubInterval = new DateInterval(new LowerEndpoint<>(TOMORROW, Clusivity.EXCLUSIVE), new UpperEndpoint<>(NEXT_MONTH, Clusivity.INCLUSIVE));
        assertEquals(expectedSubInterval, subInterval);

        subInterval = interval.subInterval(new LowerEndpoint<>(TOMORROW, Clusivity.EXCLUSIVE), new UpperEndpoint<>(NEXT_MONTH, Clusivity.EXCLUSIVE));
        expectedSubInterval = new DateInterval(new LowerEndpoint<>(TOMORROW, Clusivity.EXCLUSIVE), new UpperEndpoint<>(NEXT_MONTH, Clusivity.EXCLUSIVE));
        assertEquals(expectedSubInterval, subInterval);
    }
    
    @SuppressWarnings("Duplicates")
    @Test
    @org.testng.annotations.Test
    public void testNegativeSubInterval()
    {
        LowerEndpoint<Date> lowerEndpoint = new LowerEndpoint<>(LAST_MONTH, Clusivity.INCLUSIVE);
        UpperEndpoint<Date> upperEndpoint = new UpperEndpoint<>(YESTERDAY, Clusivity.INCLUSIVE);
        DateInterval interval = new DateInterval(lowerEndpoint, upperEndpoint);

        UpperEndpoint<Date> newUpperEndpoint = new UpperEndpoint<>(LAST_WEEK, Clusivity.INCLUSIVE);
        DateInterval subInterval = interval.subInterval(lowerEndpoint, newUpperEndpoint);
        assertTrue(new DateInterval(lowerEndpoint, newUpperEndpoint).equals(subInterval));

        subInterval = interval.subInterval(new LowerEndpoint<>(LAST_MONTH, Clusivity.INCLUSIVE), new UpperEndpoint<>(YESTERDAY, Clusivity.EXCLUSIVE));
        DateInterval expectedSubInterval = new DateInterval(new LowerEndpoint<>(LAST_MONTH, Clusivity.INCLUSIVE), new UpperEndpoint<>(YESTERDAY, Clusivity.EXCLUSIVE));
        assertEquals(expectedSubInterval, subInterval);

        subInterval = interval.subInterval(new LowerEndpoint<>(LAST_MONTH, Clusivity.EXCLUSIVE), new UpperEndpoint<>(YESTERDAY, Clusivity.INCLUSIVE));
        expectedSubInterval = new DateInterval(new LowerEndpoint<>(LAST_MONTH, Clusivity.EXCLUSIVE), new UpperEndpoint<>(YESTERDAY, Clusivity.INCLUSIVE));
        assertEquals(expectedSubInterval, subInterval);

        subInterval = interval.subInterval(new LowerEndpoint<>(LAST_MONTH, Clusivity.EXCLUSIVE), new UpperEndpoint<>(YESTERDAY, Clusivity.EXCLUSIVE));
        expectedSubInterval = new DateInterval(new LowerEndpoint<>(LAST_MONTH, Clusivity.EXCLUSIVE), new UpperEndpoint<>(YESTERDAY, Clusivity.EXCLUSIVE));
        assertEquals(expectedSubInterval, subInterval);
    }

    @Test
    @org.testng.annotations.Test
    public void testOutOfBoundsSubInterval()
    {
        LowerEndpoint<Date> lowerEndpoint = new LowerEndpoint<>(LAST_WEEK, Clusivity.EXCLUSIVE);
        UpperEndpoint<Date> upperEndpoint = new UpperEndpoint<>(NEXT_WEEK, Clusivity.EXCLUSIVE);
        DateInterval interval = new DateInterval(lowerEndpoint, upperEndpoint);
        DateInterval subInterval = interval.subInterval(lowerEndpoint, new UpperEndpoint<>(NEXT_WEEK, Clusivity.INCLUSIVE));
        assertNull(subInterval);

        subInterval = interval.subInterval(lowerEndpoint, new UpperEndpoint<>(NEXT_MONTH, Clusivity.INCLUSIVE));
        assertNull(subInterval);

        subInterval = interval.subInterval(new LowerEndpoint<>(LAST_WEEK, Clusivity.INCLUSIVE), upperEndpoint);
        assertNull(subInterval);

        subInterval = interval.subInterval(new LowerEndpoint<>(LAST_MONTH, Clusivity.EXCLUSIVE), upperEndpoint);
        assertNull(subInterval);
    }

    @Test
    @org.testng.annotations.Test
    public void testContainsValue()
    {
        LowerEndpoint<Date> lowerEndpoint = new LowerEndpoint<>(YESTERDAY, Clusivity.INCLUSIVE);
        UpperEndpoint<Date> upperEndpoint = new UpperEndpoint<>(TOMORROW, Clusivity.INCLUSIVE);
        DateInterval interval = new DateInterval(lowerEndpoint, upperEndpoint);

        assertFalse(interval.contains(LAST_YEAR));
        assertFalse(interval.contains(LAST_MONTH));
        assertFalse(interval.contains(LAST_WEEK));
        assertTrue(interval.contains(YESTERDAY));
        assertTrue(interval.contains(TODAY));
        assertTrue(interval.contains(TOMORROW));
        assertFalse(interval.contains(NEXT_WEEK));
        assertFalse(interval.contains(NEXT_MONTH));
        assertFalse(interval.contains(NEXT_YEAR));

        lowerEndpoint = new LowerEndpoint<>(YESTERDAY, Clusivity.EXCLUSIVE);
        upperEndpoint = new UpperEndpoint<>(TOMORROW, Clusivity.EXCLUSIVE);
        interval = new DateInterval(lowerEndpoint, upperEndpoint);
    
        assertFalse(interval.contains(LAST_YEAR));
        assertFalse(interval.contains(LAST_MONTH));
        assertFalse(interval.contains(LAST_WEEK));
        assertFalse(interval.contains(YESTERDAY));
        assertTrue(interval.contains(TODAY));
        assertFalse(interval.contains(TOMORROW));
        assertFalse(interval.contains(NEXT_WEEK));
        assertFalse(interval.contains(NEXT_MONTH));
        assertFalse(interval.contains(NEXT_YEAR));
    
        lowerEndpoint = new LowerEndpoint<>(YESTERDAY, Clusivity.INCLUSIVE);
        upperEndpoint = new UpperEndpoint<>(TOMORROW, Clusivity.EXCLUSIVE);
        interval = new DateInterval(lowerEndpoint, upperEndpoint);
    
        assertFalse(interval.contains(LAST_YEAR));
        assertFalse(interval.contains(LAST_MONTH));
        assertFalse(interval.contains(LAST_WEEK));
        assertTrue(interval.contains(YESTERDAY));
        assertTrue(interval.contains(TODAY));
        assertFalse(interval.contains(TOMORROW));
        assertFalse(interval.contains(NEXT_WEEK));
        assertFalse(interval.contains(NEXT_MONTH));
        assertFalse(interval.contains(NEXT_YEAR));
    
        lowerEndpoint = new LowerEndpoint<>(YESTERDAY, Clusivity.EXCLUSIVE);
        upperEndpoint = new UpperEndpoint<>(TOMORROW, Clusivity.INCLUSIVE);
        interval = new DateInterval(lowerEndpoint, upperEndpoint);
    
        assertFalse(interval.contains(LAST_YEAR));
        assertFalse(interval.contains(LAST_MONTH));
        assertFalse(interval.contains(LAST_WEEK));
        assertFalse(interval.contains(YESTERDAY));
        assertTrue(interval.contains(TODAY));
        assertTrue(interval.contains(TOMORROW));
        assertFalse(interval.contains(NEXT_WEEK));
        assertFalse(interval.contains(NEXT_MONTH));
        assertFalse(interval.contains(NEXT_YEAR));
    }

    @Test
    @org.testng.annotations.Test
    public void testEmptyEqualIntervals()
    {
        LowerEndpoint<Date> lowerEndpoint = new LowerEndpoint<>(TODAY, Clusivity.EXCLUSIVE);
        UpperEndpoint<Date> upperEndpoint = new UpperEndpoint<>(TODAY, Clusivity.EXCLUSIVE);
        DateInterval todayAndTomorrow = new DateInterval(lowerEndpoint, upperEndpoint);
        DateInterval sameTodayAndTomorrow = new DateInterval(lowerEndpoint, upperEndpoint);

        assertTrue(todayAndTomorrow.contains(sameTodayAndTomorrow));
        assertTrue(sameTodayAndTomorrow.contains(todayAndTomorrow));

        assertTrue(todayAndTomorrow.intersects(sameTodayAndTomorrow));
        assertTrue(sameTodayAndTomorrow.intersects(todayAndTomorrow));

        DateInterval intersection = todayAndTomorrow.intersect(sameTodayAndTomorrow);
        assertNotNull(intersection);
        assertTrue(todayAndTomorrow.equals(intersection));

        intersection = sameTodayAndTomorrow.intersect(todayAndTomorrow);
        assertNotNull(intersection);
        assertTrue(sameTodayAndTomorrow.equals(intersection));

        DateInterval union = todayAndTomorrow.union(sameTodayAndTomorrow);
        assertNotNull(union);
        assertTrue(todayAndTomorrow.equals(union));

        union = sameTodayAndTomorrow.union(todayAndTomorrow);
        assertNotNull(union);
        assertTrue(todayAndTomorrow.equals(union));

        // (14, 15) = empty set
        List<IInterval<Date>> remainders = todayAndTomorrow.subtract(sameTodayAndTomorrow);
        String msg = "remainders " + (remainders == null ? "null" : remainders.toString());
        assertNotNull(remainders);
        assertFalse(remainders.isEmpty(), msg);
        assertTrue(remainders.get(0).isEmpty());
        assertEquals(todayAndTomorrow, remainders.get(0));

        remainders = sameTodayAndTomorrow.subtract(todayAndTomorrow);
        msg = "remainders " + (remainders == null ? "null" : remainders.toString());
        assertNotNull(remainders);
        assertFalse(remainders.isEmpty(), msg);
        assertTrue(remainders.get(0).isEmpty());
        assertEquals(todayAndTomorrow, remainders.get(0));
    
    }

    @Test
    @org.testng.annotations.Test
    public void testNonEmptyEqualIntervals()
    {
        LowerEndpoint<Date> lowerEndpoint = new LowerEndpoint<>(YESTERDAY, Clusivity.INCLUSIVE);
        UpperEndpoint<Date> upperEndpoint = new UpperEndpoint<>(TOMORROW, Clusivity.INCLUSIVE);
        DateInterval yesterdayAndTomorrow = new DateInterval(lowerEndpoint, upperEndpoint);
        DateInterval sameYesterdayAndTomorrow = new DateInterval(lowerEndpoint, upperEndpoint);

        assertTrue(yesterdayAndTomorrow.contains(sameYesterdayAndTomorrow));
        assertTrue(sameYesterdayAndTomorrow.contains(yesterdayAndTomorrow));

        assertTrue(yesterdayAndTomorrow.intersects(sameYesterdayAndTomorrow));
        assertTrue(sameYesterdayAndTomorrow.intersects(yesterdayAndTomorrow));

        DateInterval intersection = yesterdayAndTomorrow.intersect(sameYesterdayAndTomorrow);
        assertNotNull(intersection);
        assertTrue(yesterdayAndTomorrow.equals(intersection));

        intersection = sameYesterdayAndTomorrow.intersect(yesterdayAndTomorrow);
        assertNotNull(intersection);
        assertTrue(sameYesterdayAndTomorrow.equals(intersection));

        DateInterval union = yesterdayAndTomorrow.union(sameYesterdayAndTomorrow);
        assertNotNull(union);
        assertTrue(yesterdayAndTomorrow.equals(union));

        union = sameYesterdayAndTomorrow.union(yesterdayAndTomorrow);
        assertNotNull(union);
        assertTrue(yesterdayAndTomorrow.equals(union));

        List<IInterval<Date>> remainders = yesterdayAndTomorrow.subtract(sameYesterdayAndTomorrow);
        String msg = "remainders " + (remainders == null ? "null" : remainders.toString());
        assertNull(remainders, msg);

        remainders = sameYesterdayAndTomorrow.subtract(yesterdayAndTomorrow);
        msg = "remainders " + (remainders == null ? "null" : remainders.toString());
        assertNull(remainders, msg);
    }
    
    @Test
    @org.testng.annotations.Test
    public void testIntersectingIntervals()
    {
        DateInterval firstInterval = new DateInterval(new LowerEndpoint<>(TOMORROW, Clusivity.INCLUSIVE), new UpperEndpoint<>(NEXT_WEEK, Clusivity.INCLUSIVE));
        DateInterval secondInterval = new DateInterval(new LowerEndpoint<>(LAST_WEEK, Clusivity.INCLUSIVE), new UpperEndpoint<>(TOMORROW, Clusivity.INCLUSIVE));
        
        assertFalse(firstInterval.contains(secondInterval));
        assertFalse(secondInterval.contains(firstInterval));
        
        assertTrue(firstInterval.intersects(secondInterval));
        assertTrue(secondInterval.intersects(firstInterval));
        
        DateInterval expectedIntersection = new DateInterval(new LowerEndpoint<>(TOMORROW, Clusivity.INCLUSIVE), new UpperEndpoint<>(TOMORROW, Clusivity.INCLUSIVE));
        DateInterval intersection = firstInterval.intersect(secondInterval);
        assertNotNull(intersection);
        assertEquals(expectedIntersection, intersection);
        
        intersection = secondInterval.intersect(firstInterval);
        assertNotNull(intersection);
        assertEquals(expectedIntersection, intersection);
        
        DateInterval expectedUnion = new DateInterval(
                new LowerEndpoint<>(LAST_WEEK, Clusivity.INCLUSIVE),
                new UpperEndpoint<>(NEXT_WEEK, Clusivity.INCLUSIVE));
        
        DateInterval union = firstInterval.union(secondInterval);
        assertNotNull(union);
        assertEquals(expectedUnion, union);
        
        union = secondInterval.union(firstInterval);
        assertNotNull(union);
        assertEquals(expectedUnion, union);
    }
    
    @Test
    @org.testng.annotations.Test
    public void testNonIntersectingIntervals()
    {
        LowerEndpoint<Date> firstLowerEndpoint = new LowerEndpoint<>(TODAY, Clusivity.EXCLUSIVE);
        UpperEndpoint<Date> firstUpperEndpoint = new UpperEndpoint<>(TODAY, Clusivity.EXCLUSIVE);
        DateInterval firstInterval = new DateInterval(firstLowerEndpoint, firstUpperEndpoint);

        LowerEndpoint<Date> secondLowerEndpoint = new LowerEndpoint<>(TODAY, Clusivity.EXCLUSIVE);
        UpperEndpoint<Date> secondUpperEndpoint = new UpperEndpoint<>(TODAY, Clusivity.EXCLUSIVE);
        DateInterval secondInterval = new DateInterval(secondLowerEndpoint, secondUpperEndpoint);

        assertTrue(firstInterval.contains(secondInterval));
        assertTrue(secondInterval.contains(firstInterval));

        assertTrue(firstInterval.intersects(secondInterval));
        assertTrue(secondInterval.intersects(firstInterval));

        DateInterval intersection = firstInterval.intersect(secondInterval);
        assertEquals(firstInterval, intersection);
        assertEquals(secondInterval, intersection);

        intersection = secondInterval.intersect(firstInterval);
        assertEquals(firstInterval, intersection);
        assertEquals(secondInterval, intersection);

        DateInterval union = firstInterval.union(secondInterval);
        assertEquals(firstInterval, union);
        assertEquals(secondInterval, union);

        union = secondInterval.union(firstInterval);
        assertEquals(firstInterval, union);
        assertEquals(secondInterval, union);
    }

    @Test
    @org.testng.annotations.Test
    public void testNonIntersectingIntervalsSubtraction()
    {
        long expectedLength = MILLIS_IN_A_DAY + (YESTERDAY.getTime() - LAST_WEEK.getTime());
        --expectedLength;
        --expectedLength;
        
        LowerEndpoint<Date> firstLowerEndpoint = new LowerEndpoint<>(LAST_WEEK, Clusivity.EXCLUSIVE);
        UpperEndpoint<Date> firstUpperEndpoint = new UpperEndpoint<>(YESTERDAY, Clusivity.EXCLUSIVE);
        DateInterval firstInterval = new DateInterval(firstLowerEndpoint, firstUpperEndpoint);
        assertEquals(expectedLength, firstInterval.length().longValue());
    
        expectedLength = MILLIS_IN_A_DAY + (NEXT_WEEK.getTime() - TOMORROW.getTime());
        --expectedLength;
        --expectedLength;
        
        LowerEndpoint<Date> secondLowerEndpoint = new LowerEndpoint<>(TOMORROW, Clusivity.EXCLUSIVE);
        UpperEndpoint<Date> secondUpperEndpoint = new UpperEndpoint<>(NEXT_WEEK, Clusivity.EXCLUSIVE);
        DateInterval secondInterval = new DateInterval(secondLowerEndpoint, secondUpperEndpoint);
        assertEquals(expectedLength, secondInterval.length().longValue());

        List<IInterval<Date>> remainders = firstInterval.subtract(secondInterval);
        assertNotNull(remainders);
        assertFalse(remainders.isEmpty());
        assertEquals(firstInterval, remainders.get(0));

        remainders = secondInterval.subtract(firstInterval);
        assertNotNull(remainders);
        assertFalse(remainders.isEmpty());
        assertEquals(secondInterval, remainders.get(0));
    }

    @Test
    @org.testng.annotations.Test
    public void testSubIntervals()
    {
        LowerEndpoint<Date> firstLowerEndpoint = new LowerEndpoint<>(LAST_WEEK, Clusivity.INCLUSIVE);
        UpperEndpoint<Date> firstUpperEndpoint = new UpperEndpoint<>(NEXT_WEEK, Clusivity.INCLUSIVE);
        DateInterval firstInterval = new DateInterval(firstLowerEndpoint, firstUpperEndpoint);

        LowerEndpoint<Date> secondLowerEndpoint = new LowerEndpoint<>(YESTERDAY, Clusivity.EXCLUSIVE);
        UpperEndpoint<Date> secondUpperEndpoint = new UpperEndpoint<>(TOMORROW, Clusivity.EXCLUSIVE);
        DateInterval emptyInterval = new DateInterval(secondLowerEndpoint, secondUpperEndpoint);

        assertTrue(firstInterval.contains(emptyInterval));
        assertFalse(emptyInterval.contains(firstInterval));

        assertTrue(firstInterval.intersects(emptyInterval));
        assertTrue(emptyInterval.intersects(firstInterval));

        DateInterval intersection = firstInterval.intersect(emptyInterval);
        assertNotNull(intersection);
        assertEquals(emptyInterval, intersection);
        assertTrue(emptyInterval.equals(intersection));

        intersection = emptyInterval.intersect(firstInterval);
        assertNotNull(intersection);
        assertTrue(emptyInterval.equals(intersection));

        DateInterval union = firstInterval.union(emptyInterval);
        assertNotNull(union);
        assertTrue(firstInterval.equals(union));

        union = emptyInterval.union(firstInterval);
        assertNotNull(union);
        assertTrue(firstInterval.equals(union));
    }

    @Test
    @org.testng.annotations.Test
    public void testSubtractEmptySet()
    {
        LowerEndpoint<Date> firstLowerEndpoint = new LowerEndpoint<>(LAST_WEEK, Clusivity.INCLUSIVE);
        UpperEndpoint<Date> firstUpperEndpoint = new UpperEndpoint<>(TODAY, Clusivity.INCLUSIVE);
        DateInterval firstInterval = new DateInterval(firstLowerEndpoint, firstUpperEndpoint);

        LowerEndpoint<Date> secondLowerEndpoint = new LowerEndpoint<>(NEXT_WEEK, Clusivity.EXCLUSIVE);
        UpperEndpoint<Date> secondUpperEndpoint = new UpperEndpoint<>(NEXT_WEEK, Clusivity.EXCLUSIVE);
        DateInterval secondInterval = new DateInterval(secondLowerEndpoint, secondUpperEndpoint);

        List<IInterval<Date>> remainders = firstInterval.subtract(secondInterval);
        assertNotNull(remainders);
        assertEquals(1, remainders.size());
        assertEquals(firstInterval, remainders.get(0));

        remainders = secondInterval.subtract(firstInterval);
        assertNotNull(remainders);
        assertEquals(1, remainders.size());
        assertEquals(secondInterval, remainders.get(0));
    }

    @Test
    @org.testng.annotations.Test
    public void testSubtractNonIntersectingIntervals()
    {
        LowerEndpoint<Date> firstLowerEndpoint = new LowerEndpoint<>(TODAY, Clusivity.INCLUSIVE);
        UpperEndpoint<Date> firstUpperEndpoint = new UpperEndpoint<>(NEXT_WEEK, Clusivity.INCLUSIVE);
        DateInterval firstInterval = new DateInterval(firstLowerEndpoint, firstUpperEndpoint);

        LowerEndpoint<Date> secondLowerEndpoint = new LowerEndpoint<>(NEXT_WEEK, Clusivity.EXCLUSIVE);
        UpperEndpoint<Date> secondUpperEndpoint = new UpperEndpoint<>(NEXT_MONTH, Clusivity.EXCLUSIVE);
        DateInterval secondInterval = new DateInterval(secondLowerEndpoint, secondUpperEndpoint);

        List<IInterval<Date>> remainders = firstInterval.subtract(secondInterval);
        assertNotNull(remainders);
        assertFalse(remainders.isEmpty());
        assertEquals(1, remainders.size());
        assertEquals(firstInterval, remainders.get(0));

        remainders = secondInterval.subtract(firstInterval);
        assertNotNull(remainders);
        assertFalse(remainders.isEmpty());
        assertEquals(1, remainders.size());
        assertEquals(secondInterval, remainders.get(0));
    }

    @Test
    @org.testng.annotations.Test
    public void testSubtractIntersectingIntervals()
    {
        LowerEndpoint<Date> firstLowerEndpoint = new LowerEndpoint<>(TODAY, Clusivity.INCLUSIVE);
        UpperEndpoint<Date> firstUpperEndpoint = new UpperEndpoint<>(NEXT_WEEK, Clusivity.INCLUSIVE);
        DateInterval firstInterval = new DateInterval(firstLowerEndpoint, firstUpperEndpoint);

        LowerEndpoint<Date> secondLowerEndpoint = new LowerEndpoint<>(NEXT_WEEK, Clusivity.INCLUSIVE);
        UpperEndpoint<Date> secondUpperEndpoint = new UpperEndpoint<>(NEXT_MONTH, Clusivity.INCLUSIVE);
        DateInterval secondInterval = new DateInterval(secondLowerEndpoint, secondUpperEndpoint);

        DateInterval expectedInterval = new DateInterval(firstLowerEndpoint, new UpperEndpoint<>(NEXT_WEEK, Clusivity.EXCLUSIVE));
        List<IInterval<Date>> remainders = firstInterval.subtract(secondInterval);
        assertNotNull(remainders);
        assertEquals(1, remainders.size());
        assertEquals(expectedInterval, remainders.get(0));

        expectedInterval = new DateInterval(new LowerEndpoint<>(NEXT_WEEK, Clusivity.EXCLUSIVE), secondUpperEndpoint);
        remainders = secondInterval.subtract(firstInterval);
        assertNotNull(remainders);
        assertEquals(1, remainders.size());
        assertEquals(expectedInterval, remainders.get(0));
    }
    
    @Test
    @org.testng.annotations.Test
    public void testEqualIntervalsSubtraction()
    {
        DateInterval firstInterval = new DateInterval(new LowerEndpoint<>(LAST_MONTH, Clusivity.INCLUSIVE), new UpperEndpoint<>(NEXT_WEEK, Clusivity.INCLUSIVE));
        DateInterval secondInterval = new DateInterval(new LowerEndpoint<>(LAST_MONTH, Clusivity.INCLUSIVE), new UpperEndpoint<>(NEXT_WEEK, Clusivity.INCLUSIVE));

        List<IInterval<Date>> subtractionIntervals = firstInterval.subtract(secondInterval);
        assertNull(subtractionIntervals);

        subtractionIntervals = secondInterval.subtract(firstInterval);
        assertNull(subtractionIntervals);
    }


}