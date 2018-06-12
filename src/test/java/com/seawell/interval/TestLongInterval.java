package com.seawell.interval;

import com.seawell.exceptions.InvalidDataException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 User: tseawell
 Date: 12/15/12
 Time: 12:42 AM
 */

@SuppressWarnings({"ConstantConditions", "EqualsWithItself"})
public class TestLongInterval 
{
    public static final String NL = System.lineSeparator();

    public static final long ZERO = 0L;
    public static final long ONE = 1L;
    public static final long TWO = 2L;
    public static final long THREE = 3L;
    public static final long FIVE = 5L;
    public static final long EIGHT = 8L;
    public static final long FOURTEEN = 14L;
    public static final long FIFTEEN = 15L;
    public static final long SIXTEEN = 16L;

    @Test
    @org.testng.annotations.Test
    public void testConstructorWithNullLowerEndpoint()
    {
        UpperEndpoint<Long> upperEndpoint = new UpperEndpoint<>(FIFTEEN, Clusivity.INCLUSIVE);
        assertThrows(NullPointerException.class, ()->new LongInterval(null, upperEndpoint));
    }

    @Test
    @org.testng.annotations.Test
    public void testConstructorWithNullUpperEndpoint()
    {
        LowerEndpoint<Long> lowerEndpoint = new LowerEndpoint<>(FOURTEEN, Clusivity.INCLUSIVE);
        assertThrows(NullPointerException.class, ()->new LongInterval(lowerEndpoint, null));
    }
    
    @Test
    @org.testng.annotations.Test
    public void testLowerEndpointGreaterThanUpperEndpoint()
    {
        assertThrows(InvalidDataException.class,
                ()->new LongInterval(new LowerEndpoint<>(-FOURTEEN, Clusivity.INCLUSIVE), new UpperEndpoint<>(-FIFTEEN, Clusivity.INCLUSIVE)));
        assertThrows(InvalidDataException.class,
                ()->new LongInterval(new LowerEndpoint<>(ZERO, Clusivity.EXCLUSIVE), new UpperEndpoint<>(-FIVE, Clusivity.INCLUSIVE)));
        assertThrows(InvalidDataException.class,
                ()->new LongInterval(new LowerEndpoint<>(FIFTEEN, Clusivity.EXCLUSIVE), new UpperEndpoint<>(FOURTEEN, Clusivity.INCLUSIVE)));
    }
    
    @Test
    @org.testng.annotations.Test
    public void testConstructor()
    {
        LowerEndpoint<Long> lowerEndpoint = new LowerEndpoint<>(FOURTEEN, Clusivity.INCLUSIVE);
        UpperEndpoint<Long> upperEndpoint = new UpperEndpoint<>(FIFTEEN, Clusivity.INCLUSIVE);
        LongInterval interval = new LongInterval(lowerEndpoint, upperEndpoint);

        assertEquals(lowerEndpoint, interval.getLowerEndpoint());
        assertEquals(upperEndpoint, interval.getUpperEndpoint());
    }

    @Test
    @org.testng.annotations.Test
    public void testEqualsPositive()
    {
        LowerEndpoint<Long> lowerEndpoint = new LowerEndpoint<>(FOURTEEN, Clusivity.INCLUSIVE);
        UpperEndpoint<Long> upperEndpoint = new UpperEndpoint<>(FIFTEEN, Clusivity.INCLUSIVE);

        LongInterval firstInterval = new LongInterval(lowerEndpoint, upperEndpoint);
        assertTrue(firstInterval.equals(firstInterval), NL + firstInterval + NL + firstInterval + NL);

        LongInterval secondInterval = new LongInterval(lowerEndpoint, upperEndpoint);
        assertTrue(firstInterval.equals(secondInterval), NL + firstInterval + NL + secondInterval + NL);

        lowerEndpoint = new LowerEndpoint<>(FOURTEEN, Clusivity.EXCLUSIVE);
        upperEndpoint = new UpperEndpoint<>(FIFTEEN, Clusivity.EXCLUSIVE);

        LongInterval thirdInterval = new LongInterval(lowerEndpoint, upperEndpoint);
        assertTrue(thirdInterval.equals(thirdInterval), NL + thirdInterval + NL + thirdInterval + NL);

        LongInterval fourthInterval = new LongInterval(lowerEndpoint, upperEndpoint);
        assertTrue(thirdInterval.equals(fourthInterval), NL + thirdInterval + NL + fourthInterval + NL);

        assertFalse(firstInterval.equals(thirdInterval));
        assertFalse(secondInterval.equals(fourthInterval));
    }

    @Test
    @org.testng.annotations.Test
    public void testEqualsNegative()
    {
        LowerEndpoint<Long> lowerEndpoint = new LowerEndpoint<>(-FIFTEEN, Clusivity.INCLUSIVE);
        UpperEndpoint<Long> upperEndpoint = new UpperEndpoint<>(-FOURTEEN, Clusivity.INCLUSIVE);

        LongInterval firstInterval = new LongInterval(lowerEndpoint, upperEndpoint);
        assertTrue(firstInterval.equals(firstInterval), NL + firstInterval + NL + firstInterval + NL);

        LongInterval secondInterval = new LongInterval(lowerEndpoint, upperEndpoint);
        assertTrue(firstInterval.equals(secondInterval), NL + firstInterval + NL + secondInterval + NL);

        lowerEndpoint = new LowerEndpoint<>(-FIFTEEN, Clusivity.EXCLUSIVE);
        upperEndpoint = new UpperEndpoint<>(-FOURTEEN, Clusivity.EXCLUSIVE);

        LongInterval thirdInterval = new LongInterval(lowerEndpoint, upperEndpoint);
        assertTrue(thirdInterval.equals(thirdInterval), NL + thirdInterval + NL + thirdInterval + NL);

        LongInterval fourthInterval = new LongInterval(lowerEndpoint, upperEndpoint);
        assertTrue(thirdInterval.equals(fourthInterval), NL + thirdInterval + NL + fourthInterval + NL);

        assertFalse(firstInterval.equals(thirdInterval));
        assertFalse(secondInterval.equals(fourthInterval));
    }

    @Test
    @org.testng.annotations.Test
    public void testEqualsPositiveAndNegative()
    {
        LowerEndpoint<Long> lowerEndpoint = new LowerEndpoint<>(-FIFTEEN, Clusivity.INCLUSIVE);
        UpperEndpoint<Long> upperEndpoint = new UpperEndpoint<>(FOURTEEN, Clusivity.INCLUSIVE);

        LongInterval firstInterval = new LongInterval(lowerEndpoint, upperEndpoint);
        assertTrue(firstInterval.equals(firstInterval), NL + firstInterval + NL + firstInterval + NL);

        LongInterval secondInterval = new LongInterval(lowerEndpoint, upperEndpoint);
        assertTrue(firstInterval.equals(secondInterval), NL + firstInterval + NL + secondInterval + NL);

        lowerEndpoint = new LowerEndpoint<>(-FIFTEEN, Clusivity.EXCLUSIVE);
        upperEndpoint = new UpperEndpoint<>(FOURTEEN, Clusivity.EXCLUSIVE);

        LongInterval thirdInterval = new LongInterval(lowerEndpoint, upperEndpoint);
        assertTrue(thirdInterval.equals(thirdInterval), NL + thirdInterval + NL + thirdInterval + NL);

        LongInterval fourthInterval = new LongInterval(lowerEndpoint, upperEndpoint);
        assertTrue(thirdInterval.equals(fourthInterval), NL + thirdInterval + NL + fourthInterval + NL);

        assertFalse(firstInterval.equals(thirdInterval));
        assertFalse(secondInterval.equals(fourthInterval));
    }

    @Test
    @org.testng.annotations.Test
    public void testNotEquals()
    {
        LongInterval firstInterval = new LongInterval(new LowerEndpoint<>(FOURTEEN, Clusivity.INCLUSIVE), new UpperEndpoint<>(FIFTEEN, Clusivity.INCLUSIVE));
        LongInterval secondInterval = new LongInterval(new LowerEndpoint<>(FOURTEEN, Clusivity.EXCLUSIVE), new UpperEndpoint<>(FIFTEEN, Clusivity.EXCLUSIVE));

        assertFalse(firstInterval.equals(secondInterval));

        LongInterval thirdInterval = new LongInterval(new LowerEndpoint<>(-FIFTEEN, Clusivity.INCLUSIVE), new UpperEndpoint<>(-FOURTEEN, Clusivity.INCLUSIVE));
        LongInterval fourthInterval = new LongInterval(new LowerEndpoint<>(-FIFTEEN, Clusivity.EXCLUSIVE), new UpperEndpoint<>(-FOURTEEN, Clusivity.EXCLUSIVE));

        assertFalse(thirdInterval.equals(fourthInterval));
        assertFalse(firstInterval.equals(fourthInterval));
        assertFalse(secondInterval.equals(thirdInterval));
    }

    @Test
    @org.testng.annotations.Test
    public void testIsEmpty()
    {
        assertFalse(new LongInterval(new LowerEndpoint<>(TWO, Clusivity.INCLUSIVE), new UpperEndpoint<>(TWO, Clusivity.INCLUSIVE)).isEmpty());
        assertTrue(new LongInterval(new LowerEndpoint<>(TWO, Clusivity.INCLUSIVE), new UpperEndpoint<>(TWO, Clusivity.EXCLUSIVE)).isEmpty());
        assertTrue(new LongInterval(new LowerEndpoint<>(TWO, Clusivity.EXCLUSIVE), new UpperEndpoint<>(TWO, Clusivity.EXCLUSIVE)).isEmpty());
        assertFalse(new LongInterval(new LowerEndpoint<>(ZERO, Clusivity.INCLUSIVE), new UpperEndpoint<>(ZERO, Clusivity.INCLUSIVE)).isEmpty());
        assertTrue(new LongInterval(new LowerEndpoint<>(ZERO, Clusivity.INCLUSIVE), new UpperEndpoint<>(ZERO, Clusivity.EXCLUSIVE)).isEmpty());
        assertTrue(new LongInterval(new LowerEndpoint<>(ZERO, Clusivity.EXCLUSIVE), new UpperEndpoint<>(ZERO, Clusivity.EXCLUSIVE)).isEmpty());
        assertFalse(new LongInterval(new LowerEndpoint<>(-TWO, Clusivity.INCLUSIVE), new UpperEndpoint<>(-TWO, Clusivity.INCLUSIVE)).isEmpty());
        assertTrue(new LongInterval(new LowerEndpoint<>(-TWO, Clusivity.INCLUSIVE), new UpperEndpoint<>(-TWO, Clusivity.EXCLUSIVE)).isEmpty());
        assertTrue(new LongInterval(new LowerEndpoint<>(-TWO, Clusivity.EXCLUSIVE), new UpperEndpoint<>(-TWO, Clusivity.EXCLUSIVE)).isEmpty());
        assertFalse(new LongInterval(new LowerEndpoint<>(null, Clusivity.EXCLUSIVE), new UpperEndpoint<>(-TWO, Clusivity.EXCLUSIVE)).isEmpty());
        assertFalse(new LongInterval(new LowerEndpoint<>(-TWO, Clusivity.EXCLUSIVE), new UpperEndpoint<>(null, Clusivity.EXCLUSIVE)).isEmpty());
    }

    @Test
    @org.testng.annotations.Test
    public void testIsUnbounded()
    {
        assertFalse(new LongInterval(new LowerEndpoint<>(ONE, Clusivity.INCLUSIVE), new UpperEndpoint<>(ONE, Clusivity.INCLUSIVE)).isUnBounded());
        assertFalse(new LongInterval(new LowerEndpoint<>(ONE, Clusivity.INCLUSIVE), new UpperEndpoint<>(ONE, Clusivity.EXCLUSIVE)).isUnBounded());
        assertFalse(new LongInterval(new LowerEndpoint<>(ONE, Clusivity.EXCLUSIVE), new UpperEndpoint<>(ONE, Clusivity.EXCLUSIVE)).isUnBounded());
        assertFalse(new LongInterval(new LowerEndpoint<>(ZERO, Clusivity.INCLUSIVE), new UpperEndpoint<>(ZERO, Clusivity.INCLUSIVE)).isUnBounded());
        assertFalse(new LongInterval(new LowerEndpoint<>(ZERO, Clusivity.INCLUSIVE), new UpperEndpoint<>(ZERO, Clusivity.EXCLUSIVE)).isUnBounded());
        assertFalse(new LongInterval(new LowerEndpoint<>(ZERO, Clusivity.EXCLUSIVE), new UpperEndpoint<>(ZERO, Clusivity.EXCLUSIVE)).isUnBounded());
        assertFalse(new LongInterval(new LowerEndpoint<>(-ONE, Clusivity.INCLUSIVE), new UpperEndpoint<>(-ONE, Clusivity.INCLUSIVE)).isUnBounded());
        assertFalse(new LongInterval(new LowerEndpoint<>(-ONE, Clusivity.INCLUSIVE), new UpperEndpoint<>(-ONE, Clusivity.EXCLUSIVE)).isUnBounded());
        assertFalse(new LongInterval(new LowerEndpoint<>(-ONE, Clusivity.EXCLUSIVE), new UpperEndpoint<>(-ONE, Clusivity.EXCLUSIVE)).isUnBounded());
        assertTrue(new LongInterval(new LowerEndpoint<>(ONE, Clusivity.INCLUSIVE), new UpperEndpoint<>(null, Clusivity.EXCLUSIVE)).isUnBounded());
        assertTrue(new LongInterval(new LowerEndpoint<>(null, Clusivity.EXCLUSIVE), new UpperEndpoint<>(ONE, Clusivity.INCLUSIVE)).isUnBounded());
        assertTrue(new LongInterval(new LowerEndpoint<>(null, Clusivity.EXCLUSIVE), new UpperEndpoint<>(null, Clusivity.EXCLUSIVE)).isUnBounded());
    }

    @Test
    @org.testng.annotations.Test
    public void testCompareEndpoints()
    {
        LongInterval interval = new LongInterval(new LowerEndpoint<>(ONE, Clusivity.INCLUSIVE), new UpperEndpoint<>(ONE, Clusivity.INCLUSIVE));

        assertEquals(0, interval.compare(new UpperEndpoint<>(ZERO, Clusivity.INCLUSIVE), new LowerEndpoint<>(ZERO, Clusivity.INCLUSIVE)));
        assertEquals(-1, interval.compare(new UpperEndpoint<>(ZERO, Clusivity.INCLUSIVE), new LowerEndpoint<>(ZERO, Clusivity.EXCLUSIVE)));
        assertEquals(-1, interval.compare(new UpperEndpoint<>(ZERO, Clusivity.EXCLUSIVE), new LowerEndpoint<>(ZERO, Clusivity.INCLUSIVE)));
        assertEquals(-1, interval.compare(new UpperEndpoint<>(ZERO, Clusivity.EXCLUSIVE), new LowerEndpoint<>(ZERO, Clusivity.EXCLUSIVE)));

        assertEquals(0, interval.compare(new UpperEndpoint<>(ONE, Clusivity.INCLUSIVE), new LowerEndpoint<>(ONE, Clusivity.INCLUSIVE)));
        assertEquals(-1, interval.compare(new UpperEndpoint<>(ONE, Clusivity.INCLUSIVE), new LowerEndpoint<>(ONE, Clusivity.EXCLUSIVE)));
        assertEquals(-1, interval.compare(new UpperEndpoint<>(ONE, Clusivity.EXCLUSIVE), new LowerEndpoint<>(ONE, Clusivity.INCLUSIVE)));
        assertEquals(-1, interval.compare(new UpperEndpoint<>(ONE, Clusivity.EXCLUSIVE), new LowerEndpoint<>(ONE, Clusivity.EXCLUSIVE)));

        assertEquals(0, interval.compare(new LowerEndpoint<>(ONE, Clusivity.INCLUSIVE), new UpperEndpoint<>(ONE, Clusivity.INCLUSIVE)));
        assertEquals(1, interval.compare(new LowerEndpoint<>(ONE, Clusivity.INCLUSIVE), new UpperEndpoint<>(ONE, Clusivity.EXCLUSIVE)));
        assertEquals(1, interval.compare(new LowerEndpoint<>(ONE, Clusivity.EXCLUSIVE), new UpperEndpoint<>(ONE, Clusivity.INCLUSIVE)));
        assertEquals(1, interval.compare(new LowerEndpoint<>(ONE, Clusivity.EXCLUSIVE), new UpperEndpoint<>(ONE, Clusivity.EXCLUSIVE)));

        assertEquals(-1, interval.compare(new UpperEndpoint<>(ONE, Clusivity.INCLUSIVE), new LowerEndpoint<>(TWO, Clusivity.INCLUSIVE)));
        assertEquals(-1, interval.compare(new UpperEndpoint<>(ONE, Clusivity.INCLUSIVE), new LowerEndpoint<>(TWO, Clusivity.EXCLUSIVE)));
        assertEquals(-1, interval.compare(new UpperEndpoint<>(ONE, Clusivity.EXCLUSIVE), new LowerEndpoint<>(TWO, Clusivity.INCLUSIVE)));
        assertEquals(-1, interval.compare(new UpperEndpoint<>(ONE, Clusivity.EXCLUSIVE), new LowerEndpoint<>(TWO, Clusivity.EXCLUSIVE)));

        assertEquals(-1, interval.compare(new LowerEndpoint<>(ONE, Clusivity.INCLUSIVE), new UpperEndpoint<>(TWO, Clusivity.INCLUSIVE)));
        assertEquals(0, interval.compare(new LowerEndpoint<>(ONE, Clusivity.INCLUSIVE), new UpperEndpoint<>(TWO, Clusivity.EXCLUSIVE)));
        assertEquals(0, interval.compare(new LowerEndpoint<>(ONE, Clusivity.EXCLUSIVE), new UpperEndpoint<>(TWO, Clusivity.INCLUSIVE)));
        assertEquals(1, interval.compare(new LowerEndpoint<>(ONE, Clusivity.EXCLUSIVE), new UpperEndpoint<>(TWO, Clusivity.EXCLUSIVE)));

        assertEquals(1, interval.compare(new UpperEndpoint<>(TWO, Clusivity.INCLUSIVE), new LowerEndpoint<>(ONE, Clusivity.INCLUSIVE)));
        assertEquals(0, interval.compare(new UpperEndpoint<>(TWO, Clusivity.INCLUSIVE), new LowerEndpoint<>(ONE, Clusivity.EXCLUSIVE)));
        assertEquals(0, interval.compare(new UpperEndpoint<>(TWO, Clusivity.EXCLUSIVE), new LowerEndpoint<>(ONE, Clusivity.INCLUSIVE)));
        assertEquals(-1, interval.compare(new UpperEndpoint<>(TWO, Clusivity.EXCLUSIVE), new LowerEndpoint<>(ONE, Clusivity.EXCLUSIVE)));

        assertEquals(1, interval.compare(new LowerEndpoint<>(TWO, Clusivity.INCLUSIVE), new UpperEndpoint<>(ONE, Clusivity.INCLUSIVE)));
        assertEquals(1, interval.compare(new LowerEndpoint<>(TWO, Clusivity.INCLUSIVE), new UpperEndpoint<>(ONE, Clusivity.EXCLUSIVE)));
        assertEquals(1, interval.compare(new LowerEndpoint<>(TWO, Clusivity.EXCLUSIVE), new UpperEndpoint<>(ONE, Clusivity.INCLUSIVE)));
        assertEquals(1, interval.compare(new LowerEndpoint<>(TWO, Clusivity.EXCLUSIVE), new UpperEndpoint<>(ONE, Clusivity.EXCLUSIVE)));

        assertEquals(1, interval.compare(new LowerEndpoint<>(-ONE, Clusivity.INCLUSIVE), new UpperEndpoint<>(-TWO, Clusivity.INCLUSIVE)));
        assertEquals(1, interval.compare(new LowerEndpoint<>(-ONE, Clusivity.INCLUSIVE), new UpperEndpoint<>(-TWO, Clusivity.EXCLUSIVE)));
        assertEquals(1, interval.compare(new LowerEndpoint<>(-ONE, Clusivity.EXCLUSIVE), new UpperEndpoint<>(-TWO, Clusivity.INCLUSIVE)));
        assertEquals(1, interval.compare(new LowerEndpoint<>(-ONE, Clusivity.EXCLUSIVE), new UpperEndpoint<>(-TWO, Clusivity.EXCLUSIVE)));

        assertEquals(-1, interval.compare(new LowerEndpoint<>(-TWO, Clusivity.INCLUSIVE), new UpperEndpoint<>(-ONE, Clusivity.INCLUSIVE)));
        assertEquals(0, interval.compare(new LowerEndpoint<>(-TWO, Clusivity.INCLUSIVE), new UpperEndpoint<>(-ONE, Clusivity.EXCLUSIVE)));
        assertEquals(0, interval.compare(new LowerEndpoint<>(-TWO, Clusivity.EXCLUSIVE), new UpperEndpoint<>(-ONE, Clusivity.INCLUSIVE)));
        assertEquals(1, interval.compare(new LowerEndpoint<>(-TWO, Clusivity.EXCLUSIVE), new UpperEndpoint<>(-ONE, Clusivity.EXCLUSIVE)));
    }

    @Test
    @org.testng.annotations.Test
    public void testEqualBoundsInclusive()
    {
        long bound = 5;
        LowerEndpoint<Long> lowerEndpoint = new LowerEndpoint<>(bound, Clusivity.INCLUSIVE);
        UpperEndpoint<Long> upperEndpoint = new UpperEndpoint<>(bound, Clusivity.INCLUSIVE);
        LongInterval longInterval = new LongInterval(lowerEndpoint, upperEndpoint);

        assertEquals(lowerEndpoint, longInterval.getLowerEndpoint());
        assertEquals(upperEndpoint, longInterval.getUpperEndpoint());
        assertEquals(ZERO, longInterval.compare(lowerEndpoint, upperEndpoint));
        assertEquals(ZERO, longInterval.compare(upperEndpoint, lowerEndpoint));
        assertFalse(longInterval.isEmpty());
        assertFalse(longInterval.isUnBounded());
        assertTrue(longInterval.equals(longInterval));
        assertFalse(longInterval.isBefore(longInterval));
        assertFalse(longInterval.isAfter(longInterval));
        assertTrue(longInterval.intersects(longInterval));
        assertEquals(longInterval, longInterval.intersect(longInterval));
        assertEquals(longInterval, longInterval.union(longInterval));
        List<IInterval<Long>> subtractionIntervals = longInterval.subtract(longInterval);
        assertTrue((subtractionIntervals == null || subtractionIntervals.isEmpty()));
        assertEquals(longInterval, longInterval.subInterval(lowerEndpoint, upperEndpoint));
        assertEquals(longInterval, longInterval.clone());
        assertTrue(longInterval.contains(bound));
        assertTrue(longInterval.contains(lowerEndpoint));
        assertTrue(longInterval.contains(upperEndpoint));
        assertTrue(longInterval.contains(longInterval));
        assertEquals(1, longInterval.length().intValue());
    }

    @Test
    @org.testng.annotations.Test
    public void testEqualUnbounded()
    {
        Long bound = null;
        LowerEndpoint<Long> lowerEndpoint = new LowerEndpoint<>(bound, Clusivity.EXCLUSIVE);
        UpperEndpoint<Long> upperEndpoint = new UpperEndpoint<>(bound, Clusivity.EXCLUSIVE);
        LongInterval longInterval = new LongInterval(lowerEndpoint, upperEndpoint);

        assertEquals(lowerEndpoint, longInterval.getLowerEndpoint());
        assertEquals(upperEndpoint, longInterval.getUpperEndpoint());
        assertEquals(-1, longInterval.compare(lowerEndpoint, upperEndpoint));
        assertEquals(1, longInterval.compare(upperEndpoint, lowerEndpoint));
        assertFalse(longInterval.isEmpty());
        assertTrue(longInterval.isUnBounded());
        assertTrue(longInterval.equals(longInterval));
        assertFalse(longInterval.isBefore(longInterval));
        assertFalse(longInterval.isAfter(longInterval));
        assertTrue(longInterval.intersects(longInterval));
        assertEquals(longInterval, longInterval.intersect(longInterval));
        assertEquals(longInterval, longInterval.union(longInterval));
        assertEquals(longInterval, longInterval.subInterval(lowerEndpoint, upperEndpoint));
        assertEquals(longInterval, longInterval.clone());
        assertTrue(longInterval.contains(bound));
        assertTrue(longInterval.contains(lowerEndpoint));
        assertTrue(longInterval.contains(upperEndpoint));
        assertTrue(longInterval.contains(longInterval));
        assertEquals(null, longInterval.length());
        List<IInterval<Long>> subtractionIntervals = longInterval.subtract(longInterval);
        assertTrue((subtractionIntervals == null || subtractionIntervals.isEmpty()));
    }


    @Test
    @org.testng.annotations.Test
    public void testPositiveEmptyInterval()
    {
        long bound = FIVE;
        LowerEndpoint<Long> lowerEndpoint = new LowerEndpoint<>(bound, Clusivity.EXCLUSIVE);
        UpperEndpoint<Long> upperEndpoint = new UpperEndpoint<>(bound, Clusivity.EXCLUSIVE);
        LongInterval longInterval = new LongInterval(lowerEndpoint, upperEndpoint);

        assertEquals(null, longInterval.length());
        assertEquals(lowerEndpoint, longInterval.getLowerEndpoint());
        assertEquals(upperEndpoint, longInterval.getUpperEndpoint());
        assertEquals(1, longInterval.compare(lowerEndpoint, upperEndpoint));
        assertEquals(-1, longInterval.compare(upperEndpoint, lowerEndpoint));
        assertTrue(longInterval.isEmpty());
        assertFalse(longInterval.isUnBounded());
        assertTrue(longInterval.equals(longInterval));
        assertTrue(longInterval.isBefore(longInterval));
        assertTrue(longInterval.isAfter(longInterval));
        assertFalse(longInterval.contains(bound));
        assertTrue(longInterval.contains(longInterval.getLowerEndpoint()));
        assertTrue(longInterval.contains(longInterval.getUpperEndpoint()));
        assertTrue(longInterval.contains(longInterval));
        assertTrue(longInterval.intersects(longInterval));
        assertEquals(longInterval, longInterval.intersect(longInterval));
        assertEquals(longInterval, longInterval.union(longInterval));
        List<IInterval<Long>> subtractionIntervals = longInterval.subtract(longInterval);
        assertFalse((subtractionIntervals == null || subtractionIntervals.isEmpty()));
        assertEquals(longInterval, subtractionIntervals.get(0));
        assertEquals(longInterval, longInterval.subInterval(lowerEndpoint, upperEndpoint));
        assertEquals(longInterval, longInterval.clone());
    }

    @Test
    @org.testng.annotations.Test
    public void testNegativeEmptyInterval()
    {
        long bound = -FIVE;
        LowerEndpoint<Long> lowerEndpoint = new LowerEndpoint<>(bound, Clusivity.EXCLUSIVE);
        UpperEndpoint<Long> upperEndpoint = new UpperEndpoint<>(bound, Clusivity.EXCLUSIVE);
        LongInterval longInterval = new LongInterval(lowerEndpoint, upperEndpoint);

        assertEquals(null, longInterval.length());
        assertEquals(lowerEndpoint, longInterval.getLowerEndpoint());
        assertEquals(upperEndpoint, longInterval.getUpperEndpoint());
        assertEquals(1, longInterval.compare(lowerEndpoint, upperEndpoint));
        assertEquals(-1, longInterval.compare(upperEndpoint, lowerEndpoint));
        assertTrue(longInterval.isEmpty());
        assertFalse(longInterval.isUnBounded());
        assertTrue(longInterval.equals(longInterval));
        assertTrue(longInterval.isBefore(longInterval));
        assertTrue(longInterval.isAfter(longInterval));
        assertFalse(longInterval.contains(bound));
        assertTrue(longInterval.contains(longInterval.getLowerEndpoint()));
        assertTrue(longInterval.contains(longInterval.getUpperEndpoint()));
        assertTrue(longInterval.contains(longInterval));
        assertTrue(longInterval.intersects(longInterval));
        assertEquals(longInterval, longInterval.intersect(longInterval));
        assertEquals(longInterval, longInterval.union(longInterval));
        List<IInterval<Long>> subtractionIntervals = longInterval.subtract(longInterval);
        assertFalse((subtractionIntervals == null || subtractionIntervals.isEmpty()));
        assertEquals(longInterval, subtractionIntervals.get(0));
        assertEquals(longInterval, longInterval.subInterval(lowerEndpoint, upperEndpoint));
        assertEquals(longInterval, longInterval.clone());
    }

    @Test
    @org.testng.annotations.Test
    public void testPositiveUnEqualBoundsInclusive()
    {
        long lowerBound = TWO;
        long upperBound = THREE;
        LowerEndpoint<Long> lowerEndpoint = new LowerEndpoint<>(lowerBound, Clusivity.INCLUSIVE);
        UpperEndpoint<Long> upperEndpoint = new UpperEndpoint<>(upperBound, Clusivity.INCLUSIVE);
        LongInterval longInterval = new LongInterval(lowerEndpoint, upperEndpoint);

        assertEquals(lowerEndpoint, longInterval.getLowerEndpoint());
        assertEquals(upperEndpoint, longInterval.getUpperEndpoint());
        assertEquals(-1, longInterval.compare(lowerEndpoint, upperEndpoint));
        assertEquals(1, longInterval.compare(upperEndpoint, lowerEndpoint));
        assertFalse(longInterval.isEmpty());
        assertFalse(longInterval.isUnBounded());
        assertTrue(longInterval.equals(longInterval));
        assertFalse(longInterval.isBefore(longInterval));
        assertFalse(longInterval.isAfter(longInterval));
        assertTrue(longInterval.intersects(longInterval));
        assertEquals(longInterval, longInterval.intersect(longInterval));
        assertEquals(longInterval, longInterval.union(longInterval));
        List<IInterval<Long>> subtractionIntervals = longInterval.subtract(longInterval);
        assertTrue((subtractionIntervals == null || subtractionIntervals.isEmpty()));
        assertEquals(longInterval, longInterval.subInterval(lowerEndpoint, upperEndpoint));
        assertEquals(longInterval, longInterval.clone());
        assertTrue(longInterval.contains(lowerBound));
        assertTrue(longInterval.contains(lowerEndpoint));
        assertTrue(longInterval.contains(upperBound));
        assertTrue(longInterval.contains(upperEndpoint));
        assertTrue(longInterval.contains(longInterval));
        assertEquals(TWO, longInterval.length().intValue());
    }

    @Test
    @org.testng.annotations.Test
    public void testNegativeUnEqualBoundsInclusive()
    {
        long lowerBound = -THREE;
        long upperBound = -TWO;
        LowerEndpoint<Long> lowerEndpoint = new LowerEndpoint<>(lowerBound, Clusivity.INCLUSIVE);
        UpperEndpoint<Long> upperEndpoint = new UpperEndpoint<>(upperBound, Clusivity.INCLUSIVE);
        LongInterval longInterval = new LongInterval(lowerEndpoint, upperEndpoint);

        assertEquals(lowerEndpoint, longInterval.getLowerEndpoint());
        assertEquals(upperEndpoint, longInterval.getUpperEndpoint());
        assertEquals(-1, longInterval.compare(lowerEndpoint, upperEndpoint));
        assertEquals(1, longInterval.compare(upperEndpoint, lowerEndpoint));
        assertFalse(longInterval.isEmpty());
        assertFalse(longInterval.isUnBounded());
        assertTrue(longInterval.equals(longInterval));
        assertFalse(longInterval.isBefore(longInterval));
        assertFalse(longInterval.isAfter(longInterval));
        assertTrue(longInterval.intersects(longInterval));
        assertEquals(longInterval, longInterval.intersect(longInterval));
        assertEquals(longInterval, longInterval.union(longInterval));
        List<IInterval<Long>> subtractionIntervals = longInterval.subtract(longInterval);
        assertTrue((subtractionIntervals == null || subtractionIntervals.isEmpty()));
        assertEquals(longInterval, longInterval.subInterval(lowerEndpoint, upperEndpoint));
        assertEquals(longInterval, longInterval.clone());
        assertTrue(longInterval.contains(lowerBound));
        assertTrue(longInterval.contains(lowerEndpoint));
        assertTrue(longInterval.contains(upperBound));
        assertTrue(longInterval.contains(upperEndpoint));
        assertTrue(longInterval.contains(longInterval));
        assertEquals(TWO, longInterval.length().intValue());
    }

    @Test
    @org.testng.annotations.Test
    public void testPositiveUnEqualBoundsExclusive()
    {
        long lowerBound = TWO;
        long upperBound = THREE;
        LowerEndpoint<Long> lowerEndpoint = new LowerEndpoint<>(lowerBound, Clusivity.EXCLUSIVE);
        UpperEndpoint<Long> upperEndpoint = new UpperEndpoint<>(upperBound, Clusivity.EXCLUSIVE);
        LongInterval longInterval = new LongInterval(lowerEndpoint, upperEndpoint);

        assertEquals(lowerEndpoint, longInterval.getLowerEndpoint());
        assertEquals(upperEndpoint, longInterval.getUpperEndpoint());
        assertEquals(1, longInterval.compare(lowerEndpoint, upperEndpoint));
        assertEquals(-1, longInterval.compare(upperEndpoint, lowerEndpoint));
        assertTrue(longInterval.isEmpty());
        assertFalse(longInterval.isUnBounded());
        assertTrue(longInterval.equals(longInterval));
        assertTrue(longInterval.isBefore(longInterval));
        assertTrue(longInterval.isAfter(longInterval));
        assertTrue(longInterval.intersects(longInterval));
        assertEquals(longInterval, longInterval.intersect(longInterval));
        assertEquals(longInterval, longInterval.union(longInterval));
        List<IInterval<Long>> subtractionIntervals = longInterval.subtract(longInterval);
        assertFalse((subtractionIntervals == null || subtractionIntervals.isEmpty()));
        assertEquals(1, subtractionIntervals.size());
        assertEquals(longInterval, subtractionIntervals.get(0));
        assertEquals(longInterval, longInterval.subInterval(lowerEndpoint, upperEndpoint));
        assertEquals(longInterval, longInterval.clone());
        assertFalse(longInterval.contains(lowerBound));
        assertTrue(longInterval.contains(lowerEndpoint));
        assertFalse(longInterval.contains(upperBound));
        assertTrue(longInterval.contains(upperEndpoint));
        assertTrue(longInterval.contains(longInterval));
        assertEquals(null, longInterval.length());
    }

    @Test
    @org.testng.annotations.Test
    public void testNegativeUnEqualBoundsExclusive()
    {
        long lowerBound = -THREE;
        long upperBound = -TWO;
        LowerEndpoint<Long> lowerEndpoint = new LowerEndpoint<>(lowerBound, Clusivity.EXCLUSIVE);
        UpperEndpoint<Long> upperEndpoint = new UpperEndpoint<>(upperBound, Clusivity.EXCLUSIVE);
        LongInterval longInterval = new LongInterval(lowerEndpoint, upperEndpoint);

        assertEquals(lowerEndpoint, longInterval.getLowerEndpoint());
        assertEquals(upperEndpoint, longInterval.getUpperEndpoint());
        assertEquals(1, longInterval.compare(lowerEndpoint, upperEndpoint));
        assertEquals(-1, longInterval.compare(upperEndpoint, lowerEndpoint));
        assertTrue(longInterval.isEmpty());
        assertFalse(longInterval.isUnBounded());
        assertTrue(longInterval.equals(longInterval));
        assertTrue(longInterval.isBefore(longInterval));
        assertTrue(longInterval.isAfter(longInterval));
        assertTrue(longInterval.intersects(longInterval));
        assertEquals(longInterval, longInterval.intersect(longInterval));
        assertEquals(longInterval, longInterval.union(longInterval));
        List<IInterval<Long>> subtractionIntervals = longInterval.subtract(longInterval);
        assertFalse((subtractionIntervals == null || subtractionIntervals.isEmpty()));
        assertEquals(1, subtractionIntervals.size());
        assertEquals(longInterval, subtractionIntervals.get(0));
        assertEquals(longInterval, longInterval.subInterval(lowerEndpoint, upperEndpoint));
        assertEquals(longInterval, longInterval.clone());
        assertFalse(longInterval.contains(lowerBound));
        assertTrue(longInterval.contains(lowerEndpoint));
        assertFalse(longInterval.contains(upperBound));
        assertTrue(longInterval.contains(upperEndpoint));
        assertTrue(longInterval.contains(longInterval));
        assertEquals(null, longInterval.length());
    }

    @Test
    @org.testng.annotations.Test
    public void testIsBeforeValue()
    {
        Long nullLong = null;

        LongInterval interval = new LongInterval(new LowerEndpoint<>(EIGHT, Clusivity.INCLUSIVE), new UpperEndpoint<>(FIFTEEN, Clusivity.INCLUSIVE));

        assertNull(interval.isBefore(nullLong));
        assertFalse(interval.isBefore(-ONE));
        assertFalse(interval.isBefore(ONE));
        assertFalse(interval.isBefore(EIGHT));
        assertFalse(interval.isBefore(FOURTEEN));
        assertFalse(interval.isBefore(FIFTEEN));
        assertTrue(interval.isBefore(SIXTEEN));

        interval = new LongInterval(new LowerEndpoint<>(EIGHT, Clusivity.EXCLUSIVE), new UpperEndpoint<>(FIFTEEN, Clusivity.EXCLUSIVE));

        assertNull(interval.isBefore(nullLong));
        assertFalse(interval.isBefore(-ONE));
        assertFalse(interval.isBefore(ONE));
        assertFalse(interval.isBefore(EIGHT));
        assertFalse(interval.isBefore(FOURTEEN));
        assertTrue(interval.isBefore(FIFTEEN));
        assertTrue(interval.isBefore(SIXTEEN));

        interval = new LongInterval(new LowerEndpoint<>(-THREE, Clusivity.EXCLUSIVE), new UpperEndpoint<>(-ONE, Clusivity.INCLUSIVE));

        assertNull(interval.isBefore(nullLong));
        assertFalse(interval.isBefore(-ONE));
        assertTrue(interval.isBefore(ONE));
        assertTrue(interval.isBefore(EIGHT));
        assertTrue(interval.isBefore(FOURTEEN));
        assertTrue(interval.isBefore(FIFTEEN));
        assertTrue(interval.isBefore(SIXTEEN));

        interval = new LongInterval(new LowerEndpoint<>(-THREE, Clusivity.EXCLUSIVE), new UpperEndpoint<>(-ONE, Clusivity.EXCLUSIVE));

        assertNull(interval.isBefore(nullLong));
        assertTrue(interval.isBefore(-ONE));
        assertTrue(interval.isBefore(ONE));
        assertTrue(interval.isBefore(EIGHT));
        assertTrue(interval.isBefore(FOURTEEN));
        assertTrue(interval.isBefore(FIFTEEN));
        assertTrue(interval.isBefore(SIXTEEN));
    
        LongInterval emptyInterval = new LongInterval(new LowerEndpoint<>(ONE, Clusivity.EXCLUSIVE), new UpperEndpoint<>(ONE, Clusivity.EXCLUSIVE));
    
        assertNull(emptyInterval.isBefore(nullLong));
        assertNull(emptyInterval.isBefore(-THREE));
        assertNull(emptyInterval.isBefore(-ONE));
        assertNull(emptyInterval.isBefore(ONE));
        assertNull(emptyInterval.isBefore(EIGHT));
        assertNull(emptyInterval.isBefore(FOURTEEN));
        assertNull(emptyInterval.isBefore(FIFTEEN));
        assertNull(emptyInterval.isBefore(SIXTEEN));
    
    }

    @Test
    @org.testng.annotations.Test
    public void testBeforeIntervalInclusive()
    {
        LowerEndpoint<Long> lowerEndpoint = new LowerEndpoint<>(FOURTEEN, Clusivity.INCLUSIVE);
        UpperEndpoint<Long> upperEndpoint = new UpperEndpoint<>(FIFTEEN, Clusivity.INCLUSIVE);
        LongInterval in14_15in = new LongInterval(lowerEndpoint, upperEndpoint);

        assertFalse(in14_15in.isBefore(in14_15in));

        LongInterval in8_15in = new LongInterval(new LowerEndpoint<>(EIGHT, Clusivity.INCLUSIVE), upperEndpoint);
        assertFalse(in8_15in.isBefore(in14_15in));
        assertFalse(in14_15in.isBefore(in8_15in));

        LongInterval ex8_15in = new LongInterval(new LowerEndpoint<>(EIGHT, Clusivity.EXCLUSIVE), upperEndpoint);
        assertFalse(ex8_15in.isBefore(in14_15in));
        assertFalse(in14_15in.isBefore(ex8_15in));

        LongInterval in8_15ex = new LongInterval(new LowerEndpoint<>(EIGHT, Clusivity.INCLUSIVE), new UpperEndpoint<>(FIFTEEN, Clusivity.EXCLUSIVE));
        assertFalse(in14_15in.isBefore(in8_15ex));
        assertFalse(in8_15ex.isBefore(in14_15in));

        LongInterval in8_14in = new LongInterval(new LowerEndpoint<>(EIGHT, Clusivity.INCLUSIVE), new UpperEndpoint<>(FOURTEEN, Clusivity.INCLUSIVE));
        assertFalse(in8_14in.isBefore(in14_15in));
        assertFalse(in14_15in.isBefore(in8_14in));

        LongInterval in8_14ex = new LongInterval(new LowerEndpoint<>(EIGHT, Clusivity.INCLUSIVE), new UpperEndpoint<>(FOURTEEN, Clusivity.EXCLUSIVE));
        assertTrue(in8_14ex.isBefore(in14_15in));
        assertFalse(in14_15in.isBefore(in8_14ex));

        LongInterval neg5in_neg2in = new LongInterval(new LowerEndpoint<>(-FIVE, Clusivity.INCLUSIVE), new UpperEndpoint<>(-TWO, Clusivity.INCLUSIVE));
        assertFalse(in14_15in.isBefore(neg5in_neg2in));
        assertTrue(neg5in_neg2in.isBefore(in14_15in));
    }

    @Test
    @org.testng.annotations.Test
    public void testIsAfterValue()
    {
        Long nullLong = null;

        LongInterval interval = new LongInterval(new LowerEndpoint<>(EIGHT, Clusivity.INCLUSIVE), new UpperEndpoint<>(FIFTEEN, Clusivity.INCLUSIVE));

        assertNull(interval.isAfter(nullLong));
        assertTrue(interval.isAfter(-ONE));
        assertTrue(interval.isAfter(ONE));
        assertFalse(interval.isAfter(EIGHT));
        assertFalse(interval.isAfter(FOURTEEN));
        assertFalse(interval.isAfter(FIFTEEN));
        assertFalse(interval.isAfter(SIXTEEN));

        interval = new LongInterval(new LowerEndpoint<>(EIGHT, Clusivity.EXCLUSIVE), new UpperEndpoint<>(FIFTEEN, Clusivity.EXCLUSIVE));

        assertNull(interval.isAfter(nullLong));
        assertTrue(interval.isAfter(-ONE));
        assertTrue(interval.isAfter(ONE));
        assertTrue(interval.isAfter(EIGHT));
        assertFalse(interval.isAfter(FOURTEEN));
        assertFalse(interval.isAfter(FIFTEEN));
        assertFalse(interval.isAfter(SIXTEEN));

        interval = new LongInterval(new LowerEndpoint<>(-THREE, Clusivity.INCLUSIVE), new UpperEndpoint<>(-ONE, Clusivity.INCLUSIVE));

        assertNull(interval.isAfter(nullLong));
        assertFalse(interval.isAfter(-THREE));
        assertFalse(interval.isAfter(-ONE));
        assertFalse(interval.isAfter(ONE));
        assertFalse(interval.isAfter(EIGHT));
        assertFalse(interval.isAfter(FOURTEEN));
        assertFalse(interval.isAfter(FIFTEEN));
        assertFalse(interval.isAfter(SIXTEEN));

        interval = new LongInterval(new LowerEndpoint<>(-THREE, Clusivity.EXCLUSIVE), new UpperEndpoint<>(-ONE, Clusivity.EXCLUSIVE));

        assertNull(interval.isAfter(nullLong));
        assertTrue(interval.isAfter(-THREE));
        assertFalse(interval.isAfter(-ONE));
        assertFalse(interval.isAfter(ONE));
        assertFalse(interval.isAfter(EIGHT));
        assertFalse(interval.isAfter(FOURTEEN));
        assertFalse(interval.isAfter(FIFTEEN));
        assertFalse(interval.isAfter(SIXTEEN));
    
        LongInterval emptyInterval = new LongInterval(new LowerEndpoint<>(ONE, Clusivity.EXCLUSIVE), new UpperEndpoint<>(ONE, Clusivity.EXCLUSIVE));
    
        assertNull(emptyInterval.isAfter(nullLong));
        assertNull(emptyInterval.isAfter(-THREE));
        assertNull(emptyInterval.isAfter(-ONE));
        assertNull(emptyInterval.isAfter(ONE));
        assertNull(emptyInterval.isAfter(EIGHT));
        assertNull(emptyInterval.isAfter(FOURTEEN));
        assertNull(emptyInterval.isAfter(FIFTEEN));
        assertNull(emptyInterval.isAfter(SIXTEEN));
    }

    @Test
    @org.testng.annotations.Test
    public void testIsAfterInterval()
    {
        LongInterval interval = new LongInterval(new LowerEndpoint<>(EIGHT, Clusivity.INCLUSIVE), new UpperEndpoint<>(FIFTEEN, Clusivity.INCLUSIVE));

        assertTrue(interval.isAfter(new LongInterval(new LowerEndpoint<>(ONE, Clusivity.INCLUSIVE), new UpperEndpoint<>(EIGHT, Clusivity.EXCLUSIVE))));
        assertFalse(interval.isAfter(new LongInterval(new LowerEndpoint<>(ONE, Clusivity.INCLUSIVE), new UpperEndpoint<>(EIGHT, Clusivity.INCLUSIVE))));
        assertFalse(interval.isAfter(new LongInterval(new LowerEndpoint<>(EIGHT, Clusivity.INCLUSIVE), new UpperEndpoint<>(FIFTEEN, Clusivity.INCLUSIVE))));
        assertFalse(interval.isAfter(new LongInterval(new LowerEndpoint<>(FOURTEEN, Clusivity.INCLUSIVE), new UpperEndpoint<>(FIFTEEN, Clusivity.INCLUSIVE))));
        assertFalse(interval.isAfter(new LongInterval(new LowerEndpoint<>(FIFTEEN, Clusivity.INCLUSIVE), new UpperEndpoint<>(SIXTEEN, Clusivity.INCLUSIVE))));
        assertFalse(interval.isAfter(new LongInterval(new LowerEndpoint<>(FIFTEEN, Clusivity.EXCLUSIVE), new UpperEndpoint<>(SIXTEEN, Clusivity.INCLUSIVE))));
    }

    @Test
    @org.testng.annotations.Test
    public void testLength()
    {
        LongInterval interval = new LongInterval(new LowerEndpoint<>(THREE, Clusivity.INCLUSIVE), new UpperEndpoint<>(FIFTEEN, Clusivity.INCLUSIVE));
        assertEquals(13, interval.length().intValue());

        interval = new LongInterval(new LowerEndpoint<>(THREE, Clusivity.INCLUSIVE), new UpperEndpoint<>(FIFTEEN, Clusivity.EXCLUSIVE));
        assertEquals(12, interval.length().intValue());

        interval = new LongInterval(new LowerEndpoint<>(THREE, Clusivity.EXCLUSIVE), new UpperEndpoint<>(FIFTEEN, Clusivity.INCLUSIVE));
        assertEquals(12, interval.length().intValue());

        interval = new LongInterval(new LowerEndpoint<>(THREE, Clusivity.EXCLUSIVE), new UpperEndpoint<>(FIFTEEN, Clusivity.EXCLUSIVE));
        assertEquals(11, interval.length().intValue());

        interval = new LongInterval(new LowerEndpoint<>(-FIVE, Clusivity.INCLUSIVE), new UpperEndpoint<>(-ONE, Clusivity.INCLUSIVE));
        assertEquals(5, interval.length().intValue());

        interval = new LongInterval(new LowerEndpoint<>(-FIVE, Clusivity.INCLUSIVE), new UpperEndpoint<>(-ONE, Clusivity.EXCLUSIVE));
        assertEquals(4, interval.length().intValue());

        interval = new LongInterval(new LowerEndpoint<>(-FIVE, Clusivity.EXCLUSIVE), new UpperEndpoint<>(-ONE, Clusivity.INCLUSIVE));
        assertEquals(4, interval.length().intValue());

        interval = new LongInterval(new LowerEndpoint<>(-FIVE, Clusivity.EXCLUSIVE), new UpperEndpoint<>(-ONE, Clusivity.EXCLUSIVE));
        assertEquals(3, interval.length().intValue());

        interval = new LongInterval(new LowerEndpoint<>(-FIVE, Clusivity.INCLUSIVE), new UpperEndpoint<>(TWO, Clusivity.INCLUSIVE));
        assertEquals(8, interval.length().intValue());

        interval = new LongInterval(new LowerEndpoint<>(-FIVE, Clusivity.INCLUSIVE), new UpperEndpoint<>(TWO, Clusivity.EXCLUSIVE));
        assertEquals(7, interval.length().intValue());

        interval = new LongInterval(new LowerEndpoint<>(-FIVE, Clusivity.EXCLUSIVE), new UpperEndpoint<>(TWO, Clusivity.INCLUSIVE));
        assertEquals(7, interval.length().intValue());

        interval = new LongInterval(new LowerEndpoint<>(-FIVE, Clusivity.EXCLUSIVE), new UpperEndpoint<>(TWO, Clusivity.EXCLUSIVE));
        assertEquals(6, interval.length().intValue());

        interval = new LongInterval(new LowerEndpoint<>(TWO, Clusivity.INCLUSIVE), new UpperEndpoint<>(TWO, Clusivity.INCLUSIVE));
        assertEquals(1, interval.length().intValue());

        interval = new LongInterval(new LowerEndpoint<>(TWO, Clusivity.INCLUSIVE), new UpperEndpoint<>(TWO, Clusivity.EXCLUSIVE));
        assertEquals(null, interval.length());

        interval = new LongInterval(new LowerEndpoint<>(TWO, Clusivity.EXCLUSIVE), new UpperEndpoint<>(TWO, Clusivity.EXCLUSIVE));
        assertEquals(null, interval.length());
    }

    @Test
    @org.testng.annotations.Test
    public void testPositiveNullSubInterval()
    {
        LowerEndpoint<Long> lowerEndpoint = new LowerEndpoint<>(EIGHT, Clusivity.INCLUSIVE);
        UpperEndpoint<Long> upperEndpoint = new UpperEndpoint<>(FOURTEEN, Clusivity.INCLUSIVE);

        LongInterval interval = new LongInterval(lowerEndpoint, upperEndpoint);
        LongInterval subInterval = interval.subInterval(lowerEndpoint, upperEndpoint);
        assertNotNull(subInterval);
        assertEquals(interval, subInterval);

        lowerEndpoint = new LowerEndpoint<>(EIGHT, Clusivity.EXCLUSIVE);
        upperEndpoint = new UpperEndpoint<>(EIGHT, Clusivity.EXCLUSIVE);
        subInterval = interval.subInterval(lowerEndpoint, upperEndpoint);
        String message = interval + ".subInterval(" + lowerEndpoint + ", " + upperEndpoint + ")";
        assertEquals(new LongInterval(lowerEndpoint, upperEndpoint), subInterval, message);

        lowerEndpoint = new LowerEndpoint<>(EIGHT, Clusivity.INCLUSIVE);
        upperEndpoint = new UpperEndpoint<>(FIFTEEN, Clusivity.EXCLUSIVE);
        subInterval = interval.subInterval(lowerEndpoint, upperEndpoint);
        assertNull(subInterval, interval + ".subInterval(" + lowerEndpoint + ", " + upperEndpoint + ")");

        lowerEndpoint = new LowerEndpoint<>(EIGHT, Clusivity.EXCLUSIVE);
        upperEndpoint = new UpperEndpoint<>(FIFTEEN, Clusivity.INCLUSIVE);
        subInterval = interval.subInterval(lowerEndpoint, upperEndpoint);
        assertNull(subInterval, interval + ".subInterval(" + lowerEndpoint + ", " + upperEndpoint + ")");

        lowerEndpoint = new LowerEndpoint<>(EIGHT, Clusivity.EXCLUSIVE);
        upperEndpoint = new UpperEndpoint<>(FIFTEEN, Clusivity.EXCLUSIVE);
        subInterval = interval.subInterval(lowerEndpoint, upperEndpoint);
        assertNull(subInterval, interval + ".subInterval(" + lowerEndpoint + ", " + upperEndpoint + ")");
    }

    @Test
    @org.testng.annotations.Test
    public void testNegativeNullSubInterval()
    {
        LowerEndpoint<Long> lowerEndpoint = new LowerEndpoint<>(-FOURTEEN, Clusivity.INCLUSIVE);
        UpperEndpoint<Long> upperEndpoint = new UpperEndpoint<>(-EIGHT, Clusivity.INCLUSIVE);

        LongInterval interval = new LongInterval(lowerEndpoint, upperEndpoint);
        LongInterval subInterval = interval.subInterval(lowerEndpoint, upperEndpoint);
        assertNotNull(subInterval);
        assertEquals(interval, subInterval);

        lowerEndpoint = new LowerEndpoint<>(-EIGHT, Clusivity.EXCLUSIVE);
        upperEndpoint = new UpperEndpoint<>(-EIGHT, Clusivity.EXCLUSIVE);
        subInterval = interval.subInterval(lowerEndpoint, upperEndpoint);
        String message = interval + ".subInterval(" + lowerEndpoint + ", " + upperEndpoint + ")";
        assertEquals(new LongInterval(lowerEndpoint, upperEndpoint), subInterval, message);

        lowerEndpoint = new LowerEndpoint<>(-FIFTEEN, Clusivity.INCLUSIVE);
        upperEndpoint = new UpperEndpoint<>(-EIGHT, Clusivity.EXCLUSIVE);
        subInterval = interval.subInterval(lowerEndpoint, upperEndpoint);
        assertNull(subInterval, interval + ".subInterval(" + lowerEndpoint + ", " + upperEndpoint + ")");

        lowerEndpoint = new LowerEndpoint<>(-FIFTEEN, Clusivity.EXCLUSIVE);
        upperEndpoint = new UpperEndpoint<>(-EIGHT, Clusivity.INCLUSIVE);
        subInterval = interval.subInterval(lowerEndpoint, upperEndpoint);
        assertNull(subInterval, interval + ".subInterval(" + lowerEndpoint + ", " + upperEndpoint + ")");

        lowerEndpoint = new LowerEndpoint<>(-FIFTEEN, Clusivity.EXCLUSIVE);
        upperEndpoint = new UpperEndpoint<>(-EIGHT, Clusivity.EXCLUSIVE);
        subInterval = interval.subInterval(lowerEndpoint, upperEndpoint);
        assertNull(subInterval, interval + ".subInterval(" + lowerEndpoint + ", " + upperEndpoint + ")");
    }

    @Test
    @org.testng.annotations.Test
    public void testPositiveSubInterval()
    {
        LowerEndpoint<Long> lowerEndpoint = new LowerEndpoint<>(EIGHT, Clusivity.INCLUSIVE);
        UpperEndpoint<Long> upperEndpoint = new UpperEndpoint<>(FIFTEEN, Clusivity.INCLUSIVE);
        LongInterval interval = new LongInterval(lowerEndpoint, upperEndpoint);

        UpperEndpoint<Long> newUpperEndpoint = new UpperEndpoint<>(FOURTEEN, Clusivity.INCLUSIVE);
        LongInterval subInterval = interval.subInterval(lowerEndpoint, newUpperEndpoint);
        assertTrue(new LongInterval(lowerEndpoint, newUpperEndpoint).equals(subInterval));

        subInterval = interval.subInterval(new LowerEndpoint<>(EIGHT, Clusivity.INCLUSIVE), new UpperEndpoint<>(FIFTEEN, Clusivity.EXCLUSIVE));
        LongInterval expectedSubInterval = new LongInterval(new LowerEndpoint<>(EIGHT, Clusivity.INCLUSIVE), new UpperEndpoint<>(FIFTEEN, Clusivity.EXCLUSIVE));
        assertEquals(expectedSubInterval, subInterval);

        subInterval = interval.subInterval(new LowerEndpoint<>(EIGHT, Clusivity.EXCLUSIVE), new UpperEndpoint<>(FIFTEEN, Clusivity.INCLUSIVE));
        expectedSubInterval = new LongInterval(new LowerEndpoint<>(EIGHT, Clusivity.EXCLUSIVE), new UpperEndpoint<>(FIFTEEN, Clusivity.INCLUSIVE));
        assertEquals(expectedSubInterval, subInterval);

        subInterval = interval.subInterval(new LowerEndpoint<>(EIGHT, Clusivity.EXCLUSIVE), new UpperEndpoint<>(FIFTEEN, Clusivity.EXCLUSIVE));
        expectedSubInterval = new LongInterval(new LowerEndpoint<>(EIGHT, Clusivity.EXCLUSIVE), new UpperEndpoint<>(FIFTEEN, Clusivity.EXCLUSIVE));
        assertEquals(expectedSubInterval, subInterval);
    }

    @Test
    @org.testng.annotations.Test
    public void testNegativeSubInterval()
    {
        LowerEndpoint<Long> lowerEndpoint = new LowerEndpoint<>(-FIFTEEN, Clusivity.INCLUSIVE);
        UpperEndpoint<Long> upperEndpoint = new UpperEndpoint<>(-EIGHT, Clusivity.INCLUSIVE);
        LongInterval interval = new LongInterval(lowerEndpoint, upperEndpoint);

        UpperEndpoint<Long> newUpperEndpoint = new UpperEndpoint<>(-FOURTEEN, Clusivity.INCLUSIVE);
        LongInterval subInterval = interval.subInterval(lowerEndpoint, newUpperEndpoint);
        assertTrue(new LongInterval(lowerEndpoint, newUpperEndpoint).equals(subInterval));

        subInterval = interval.subInterval(new LowerEndpoint<>(-FIFTEEN, Clusivity.INCLUSIVE), new UpperEndpoint<>(-EIGHT, Clusivity.EXCLUSIVE));
        LongInterval expectedSubInterval = new LongInterval(new LowerEndpoint<>(-FIFTEEN, Clusivity.INCLUSIVE), new UpperEndpoint<>(-EIGHT, Clusivity.EXCLUSIVE));
        assertEquals(expectedSubInterval, subInterval);

        subInterval = interval.subInterval(new LowerEndpoint<>(-FIFTEEN, Clusivity.EXCLUSIVE), new UpperEndpoint<>(-EIGHT, Clusivity.INCLUSIVE));
        expectedSubInterval = new LongInterval(new LowerEndpoint<>(-FIFTEEN, Clusivity.EXCLUSIVE), new UpperEndpoint<>(-EIGHT, Clusivity.INCLUSIVE));
        assertEquals(expectedSubInterval, subInterval);

        subInterval = interval.subInterval(new LowerEndpoint<>(-FIFTEEN, Clusivity.EXCLUSIVE), new UpperEndpoint<>(-EIGHT, Clusivity.EXCLUSIVE));
        expectedSubInterval = new LongInterval(new LowerEndpoint<>(-FIFTEEN, Clusivity.EXCLUSIVE), new UpperEndpoint<>(-EIGHT, Clusivity.EXCLUSIVE));
        assertEquals(expectedSubInterval, subInterval);
    }

    @Test
    @org.testng.annotations.Test
    public void testOutOfBoundsSubInterval()
    {
        LowerEndpoint<Long> lowerEndpoint = new LowerEndpoint<>(EIGHT, Clusivity.EXCLUSIVE);
        UpperEndpoint<Long> upperEndpoint = new UpperEndpoint<>(FIFTEEN, Clusivity.EXCLUSIVE);
        LongInterval interval = new LongInterval(lowerEndpoint, upperEndpoint);
        LongInterval subInterval = interval.subInterval(lowerEndpoint, new UpperEndpoint<>(FIFTEEN, Clusivity.INCLUSIVE));
        assertNull(subInterval);

        subInterval = interval.subInterval(lowerEndpoint, new UpperEndpoint<>(SIXTEEN, Clusivity.INCLUSIVE));
        assertNull(subInterval);

        subInterval = interval.subInterval(new LowerEndpoint<>(EIGHT, Clusivity.INCLUSIVE), upperEndpoint);
        assertNull(subInterval);

        subInterval = interval.subInterval(new LowerEndpoint<>(ONE, Clusivity.EXCLUSIVE), upperEndpoint);
        assertNull(subInterval);
    }

    @Test
    @org.testng.annotations.Test
    public void testContainsValue()
    {
        LowerEndpoint<Long> lowerEndpoint = new LowerEndpoint<>(EIGHT, Clusivity.INCLUSIVE);
        UpperEndpoint<Long> upperEndpoint = new UpperEndpoint<>(FIFTEEN, Clusivity.INCLUSIVE);
        LongInterval interval = new LongInterval(lowerEndpoint, upperEndpoint);

        assertFalse(interval.contains(ONE));
        assertTrue(interval.contains(EIGHT));
        assertTrue(interval.contains(FOURTEEN));
        assertTrue(interval.contains(FIFTEEN));
        assertFalse(interval.contains(SIXTEEN));

        lowerEndpoint = new LowerEndpoint<>(EIGHT, Clusivity.EXCLUSIVE);
        upperEndpoint = new UpperEndpoint<>(FIFTEEN, Clusivity.EXCLUSIVE);
        interval = new LongInterval(lowerEndpoint, upperEndpoint);

        assertFalse(interval.contains(ONE));
        assertFalse(interval.contains(EIGHT));
        assertTrue(interval.contains(FOURTEEN));
        assertFalse(interval.contains(FIFTEEN));
        assertFalse(interval.contains(SIXTEEN));
    }

    @Test
    @org.testng.annotations.Test
    public void testEmptyEqualIntervals()
    {
        LowerEndpoint<Long> lowerEndpoint = new LowerEndpoint<>(FOURTEEN, Clusivity.EXCLUSIVE);
        UpperEndpoint<Long> upperEndpoint = new UpperEndpoint<>(FIFTEEN, Clusivity.EXCLUSIVE);
        LongInterval ex14_15ex = new LongInterval(lowerEndpoint, upperEndpoint);
        LongInterval same_ex14_15ex = new LongInterval(lowerEndpoint, upperEndpoint);

        assertTrue(ex14_15ex.contains(same_ex14_15ex));
        assertTrue(same_ex14_15ex.contains(ex14_15ex));

        assertTrue(ex14_15ex.intersects(same_ex14_15ex));
        assertTrue(same_ex14_15ex.intersects(ex14_15ex));

        LongInterval intersection = ex14_15ex.intersect(same_ex14_15ex);
        assertNotNull(intersection);
        assertTrue(ex14_15ex.equals(intersection));

        intersection = same_ex14_15ex.intersect(ex14_15ex);
        assertNotNull(intersection);
        assertTrue(same_ex14_15ex.equals(intersection));

        LongInterval union = ex14_15ex.union(same_ex14_15ex);
        assertNotNull(union);
        assertTrue(ex14_15ex.equals(union));

        union = same_ex14_15ex.union(ex14_15ex);
        assertNotNull(union);
        assertTrue(ex14_15ex.equals(union));

        // (14, 15) = empty set
        List<IInterval<Long>> remainders = ex14_15ex.subtract(same_ex14_15ex);
        String msg = "remainders " + (remainders == null ? "null" : remainders.toString());
        assertNotNull(remainders);
        assertFalse((remainders == null || remainders.isEmpty()), msg);
        assertTrue(remainders.get(0).isEmpty());
        assertEquals(ex14_15ex, remainders.get(0));

        remainders = same_ex14_15ex.subtract(ex14_15ex);
        msg = "remainders " + (remainders == null ? "null" : remainders.toString());
        assertNotNull(remainders);
        assertFalse((remainders == null || remainders.isEmpty()), msg);
        assertTrue(remainders.get(0).isEmpty());
        assertEquals(ex14_15ex, remainders.get(0));
    }

    @Test
    @org.testng.annotations.Test
    public void testNonEmptyEqualIntervals()
    {
        LowerEndpoint<Long> lowerEndpoint = new LowerEndpoint<>(FOURTEEN, Clusivity.INCLUSIVE);
        UpperEndpoint<Long> upperEndpoint = new UpperEndpoint<>(FIFTEEN, Clusivity.INCLUSIVE);
        LongInterval ex14_15ex = new LongInterval(lowerEndpoint, upperEndpoint);
        LongInterval same_ex14_15ex = new LongInterval(lowerEndpoint, upperEndpoint);

        assertTrue(ex14_15ex.contains(same_ex14_15ex));
        assertTrue(same_ex14_15ex.contains(ex14_15ex));

        assertTrue(ex14_15ex.intersects(same_ex14_15ex));
        assertTrue(same_ex14_15ex.intersects(ex14_15ex));

        LongInterval intersection = ex14_15ex.intersect(same_ex14_15ex);
        assertNotNull(intersection);
        assertTrue(ex14_15ex.equals(intersection));

        intersection = same_ex14_15ex.intersect(ex14_15ex);
        assertNotNull(intersection);
        assertTrue(same_ex14_15ex.equals(intersection));

        LongInterval union = ex14_15ex.union(same_ex14_15ex);
        assertNotNull(union);
        assertTrue(ex14_15ex.equals(union));

        union = same_ex14_15ex.union(ex14_15ex);
        assertNotNull(union);
        assertTrue(ex14_15ex.equals(union));

        List<IInterval<Long>> remainders = ex14_15ex.subtract(same_ex14_15ex);
        String msg = "remainders " + (remainders == null ? "null" : remainders.toString());
        assertTrue((remainders == null || remainders.isEmpty()), msg);

        remainders = same_ex14_15ex.subtract(ex14_15ex);
        msg = "remainders " + (remainders == null ? "null" : remainders.toString());
        assertTrue((remainders == null || remainders.isEmpty()), msg);
    }


    @Test
    @org.testng.annotations.Test
    public void testNonIntersectingIntervals()
    {
        LowerEndpoint<Long> firstLowerEndpoint = new LowerEndpoint<>(FIFTEEN, Clusivity.EXCLUSIVE);
        UpperEndpoint<Long> firstUpperEndpoint = new UpperEndpoint<>(FIFTEEN, Clusivity.EXCLUSIVE);
        LongInterval firstInterval = new LongInterval(firstLowerEndpoint, firstUpperEndpoint);

        LowerEndpoint<Long> secondLowerEndpoint = new LowerEndpoint<>(FIFTEEN, Clusivity.EXCLUSIVE);
        UpperEndpoint<Long> secondUpperEndpoint = new UpperEndpoint<>(FIFTEEN, Clusivity.EXCLUSIVE);
        LongInterval secondInterval = new LongInterval(secondLowerEndpoint, secondUpperEndpoint);

        assertTrue(firstInterval.contains(secondInterval));
        assertTrue(secondInterval.contains(firstInterval));

        assertTrue(firstInterval.intersects(secondInterval));
        assertTrue(secondInterval.intersects(firstInterval));

        LongInterval intersection = firstInterval.intersect(secondInterval);
        assertEquals(firstInterval, intersection);
        assertEquals(secondInterval, intersection);

        intersection = secondInterval.intersect(firstInterval);
        assertEquals(firstInterval, intersection);
        assertEquals(secondInterval, intersection);

        LongInterval union = firstInterval.union(secondInterval);
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
        LowerEndpoint<Long> firstLowerEndpoint = new LowerEndpoint<>(ONE, Clusivity.EXCLUSIVE);
        UpperEndpoint<Long> firstUpperEndpoint = new UpperEndpoint<>(EIGHT, Clusivity.EXCLUSIVE);
        LongInterval firstInterval = new LongInterval(firstLowerEndpoint, firstUpperEndpoint);
        assertEquals(6, firstInterval.length().intValue());

        LowerEndpoint<Long> secondLowerEndpoint = new LowerEndpoint<>(FOURTEEN, Clusivity.EXCLUSIVE);
        UpperEndpoint<Long> secondUpperEndpoint = new UpperEndpoint<>(SIXTEEN, Clusivity.EXCLUSIVE);
        LongInterval secondInterval = new LongInterval(secondLowerEndpoint, secondUpperEndpoint);
        assertEquals(1, secondInterval.length().intValue());

        // (1, 8) - (1, 16)
        List<IInterval<Long>> remainders = firstInterval.subtract(secondInterval);
        assertNotNull(remainders);
        assertFalse((remainders == null || remainders.isEmpty()));
        assertEquals(firstInterval, remainders.get(0));

        // (1, 16) - (1, 8)
        remainders = secondInterval.subtract(firstInterval);
        assertNotNull(remainders);
        assertFalse((remainders == null || remainders.isEmpty()));
        assertEquals(secondInterval, remainders.get(0));
    }

    @Test
    @org.testng.annotations.Test
    public void testSubIntervals()
    {
        LowerEndpoint<Long> firstLowerEndpoint = new LowerEndpoint<>(EIGHT, Clusivity.INCLUSIVE);
        UpperEndpoint<Long> firstUpperEndpoint = new UpperEndpoint<>(SIXTEEN, Clusivity.INCLUSIVE);
        LongInterval firstInterval = new LongInterval(firstLowerEndpoint, firstUpperEndpoint);

        LowerEndpoint<Long> secondLowerEndpoint = new LowerEndpoint<>(FOURTEEN, Clusivity.EXCLUSIVE);
        UpperEndpoint<Long> secondUpperEndpoint = new UpperEndpoint<>(FIFTEEN, Clusivity.EXCLUSIVE);
        LongInterval emptyInterval = new LongInterval(secondLowerEndpoint, secondUpperEndpoint);

        assertTrue(firstInterval.contains(emptyInterval));
        assertFalse(emptyInterval.contains(firstInterval));

        assertTrue(firstInterval.intersects(emptyInterval));
        assertTrue(emptyInterval.intersects(firstInterval));

        LongInterval intersection = firstInterval.intersect(emptyInterval);
        assertNotNull(intersection);
        assertEquals(emptyInterval, intersection);
        assertTrue(emptyInterval.equals(intersection));

        intersection = emptyInterval.intersect(firstInterval);
        assertNotNull(intersection);
        assertTrue(emptyInterval.equals(intersection));

        LongInterval union = firstInterval.union(emptyInterval);
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
        LowerEndpoint<Long> firstLowerEndpoint = new LowerEndpoint<>(EIGHT, Clusivity.INCLUSIVE);
        UpperEndpoint<Long> firstUpperEndpoint = new UpperEndpoint<>(SIXTEEN, Clusivity.INCLUSIVE);
        LongInterval firstInterval = new LongInterval(firstLowerEndpoint, firstUpperEndpoint);

        LowerEndpoint<Long> secondLowerEndpoint = new LowerEndpoint<>(FOURTEEN, Clusivity.EXCLUSIVE);
        UpperEndpoint<Long> secondUpperEndpoint = new UpperEndpoint<>(FIFTEEN, Clusivity.EXCLUSIVE);
        LongInterval secondInterval = new LongInterval(secondLowerEndpoint, secondUpperEndpoint);

        List<IInterval<Long>> remainders = firstInterval.subtract(secondInterval);
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
        LowerEndpoint<Long> firstLowerEndpoint = new LowerEndpoint<>(ONE, Clusivity.INCLUSIVE);
        UpperEndpoint<Long> firstUpperEndpoint = new UpperEndpoint<>(EIGHT, Clusivity.INCLUSIVE);
        LongInterval firstInterval = new LongInterval(firstLowerEndpoint, firstUpperEndpoint);

        LowerEndpoint<Long> secondLowerEndpoint = new LowerEndpoint<>(EIGHT, Clusivity.EXCLUSIVE);
        UpperEndpoint<Long> secondUpperEndpoint = new UpperEndpoint<>(FIFTEEN, Clusivity.EXCLUSIVE);
        LongInterval secondInterval = new LongInterval(secondLowerEndpoint, secondUpperEndpoint);

        List<IInterval<Long>> remainders = firstInterval.subtract(secondInterval);
        assertNotNull(remainders);
        assertFalse((remainders == null || remainders.isEmpty()));
        assertEquals(1, remainders.size());
        assertEquals(firstInterval, remainders.get(0));

        remainders = secondInterval.subtract(firstInterval);
        assertNotNull(remainders);
        assertFalse((remainders == null || remainders.isEmpty()));
        assertEquals(1, remainders.size());
        assertEquals(secondInterval, remainders.get(0));
    }

    @Test
    @org.testng.annotations.Test
    public void testSubtractIntersectingIntervals()
    {
        LowerEndpoint<Long> firstLowerEndpoint = new LowerEndpoint<>(ONE, Clusivity.INCLUSIVE);
        UpperEndpoint<Long> firstUpperEndpoint = new UpperEndpoint<>(EIGHT, Clusivity.INCLUSIVE);
        LongInterval firstInterval = new LongInterval(firstLowerEndpoint, firstUpperEndpoint);

        LowerEndpoint<Long> secondLowerEndpoint = new LowerEndpoint<>(EIGHT, Clusivity.INCLUSIVE);
        UpperEndpoint<Long> secondUpperEndpoint = new UpperEndpoint<>(FIFTEEN, Clusivity.INCLUSIVE);
        LongInterval secondInterval = new LongInterval(secondLowerEndpoint, secondUpperEndpoint);

        LongInterval expectedInterval = new LongInterval(firstLowerEndpoint, new UpperEndpoint<>(EIGHT, Clusivity.EXCLUSIVE));
        List<IInterval<Long>> remainders = firstInterval.subtract(secondInterval);
        assertNotNull(remainders);
        assertEquals(1, remainders.size());
        assertEquals(expectedInterval, remainders.get(0));

        expectedInterval = new LongInterval(new LowerEndpoint<>(EIGHT, Clusivity.EXCLUSIVE), secondUpperEndpoint);
        remainders = secondInterval.subtract(firstInterval);
        assertNotNull(remainders);
        assertEquals(1, remainders.size());
        assertEquals(expectedInterval, remainders.get(0));
    }
    
    @Test
    @org.testng.annotations.Test
    public void testIntersectingIntervals()
    {
        LongInterval firstInterval = new LongInterval(
                new LowerEndpoint<>(FIFTEEN, Clusivity.INCLUSIVE),
                new UpperEndpoint<>(SIXTEEN, Clusivity.INCLUSIVE));
        LongInterval secondInterval = new LongInterval(
                new LowerEndpoint<>(FOURTEEN, Clusivity.INCLUSIVE),
                new UpperEndpoint<>(FIFTEEN, Clusivity.INCLUSIVE));

        assertFalse(firstInterval.contains(secondInterval));
        assertFalse(secondInterval.contains(firstInterval));

        assertTrue(firstInterval.intersects(secondInterval));
        assertTrue(secondInterval.intersects(firstInterval));

        LongInterval expectedIntersection = new LongInterval(
                new LowerEndpoint<>(FIFTEEN, Clusivity.INCLUSIVE),
                new UpperEndpoint<>(FIFTEEN, Clusivity.INCLUSIVE));
        LongInterval intersection = firstInterval.intersect(secondInterval);
        assertNotNull(intersection);
        assertEquals(expectedIntersection, intersection);

        intersection = secondInterval.intersect(firstInterval);
        assertNotNull(intersection);
        assertEquals(expectedIntersection, intersection);

        LongInterval expectedUnion = new LongInterval(
                new LowerEndpoint<>(FOURTEEN, Clusivity.INCLUSIVE),
                new UpperEndpoint<>(SIXTEEN, Clusivity.INCLUSIVE));

        LongInterval union = firstInterval.union(secondInterval);
        assertNotNull(union);
        assertEquals(expectedUnion, union);

        union = secondInterval.union(firstInterval);
        assertNotNull(union);
        assertEquals(expectedUnion, union);
    }

    @Test
    @org.testng.annotations.Test
    public void testEqualIntervalsSubtraction()
    {
        LongInterval firstInterval = new LongInterval(
                new LowerEndpoint<>(ONE, Clusivity.INCLUSIVE),
                new UpperEndpoint<>(SIXTEEN, Clusivity.INCLUSIVE));
        LongInterval secondInterval = new LongInterval(
                new LowerEndpoint<>(ONE, Clusivity.INCLUSIVE),
                new UpperEndpoint<>(SIXTEEN, Clusivity.INCLUSIVE));

        List<IInterval<Long>> subtractionIntervals = firstInterval.subtract(secondInterval);
        assertTrue((subtractionIntervals == null || subtractionIntervals.isEmpty()));

        subtractionIntervals = secondInterval.subtract(firstInterval);
        assertTrue((subtractionIntervals == null || subtractionIntervals.isEmpty()));
    }

    @Test
    @org.testng.annotations.Test
    public void testIntersectingIntervalsSubtraction()
    {
        LongInterval firstInterval = new LongInterval(
                new LowerEndpoint<>(FIFTEEN, Clusivity.INCLUSIVE),
                new UpperEndpoint<>(SIXTEEN, Clusivity.INCLUSIVE));
        LongInterval secondInterval = new LongInterval(
                new LowerEndpoint<>(FOURTEEN, Clusivity.INCLUSIVE),
                new UpperEndpoint<>(FIFTEEN, Clusivity.INCLUSIVE));

        List<IInterval<Long>> remainders = firstInterval.subtract(secondInterval);
        assertFalse((remainders == null || remainders.isEmpty()));
        assertEquals(1, remainders.size());
        assertEquals(new LongInterval(
                new LowerEndpoint<>(FIFTEEN, Clusivity.EXCLUSIVE),
                new UpperEndpoint<>(SIXTEEN, Clusivity.INCLUSIVE)),
                remainders.get(0));

        remainders = secondInterval.subtract(firstInterval);
        assertFalse((remainders == null || remainders.isEmpty()));
        assertEquals(1, remainders.size());
        assertEquals(new LongInterval(
                new LowerEndpoint<>(FOURTEEN, Clusivity.INCLUSIVE),
                new UpperEndpoint<>(FIFTEEN, Clusivity.EXCLUSIVE)),
                remainders.get(0));
    }

}