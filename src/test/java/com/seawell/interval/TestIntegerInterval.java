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
public class TestIntegerInterval 
{
    public static final String NL = System.lineSeparator();

    public static final int ZERO = 0;
    public static final int ONE = 1;
    public static final int TWO = 2;
    public static final int THREE = 3;
    public static final int FIVE = 5;
    public static final int EIGHT = 8;
    public static final int FOURTEEN = 14;
    public static final int FIFTEEN = 15;
    public static final int SIXTEEN = 16;

    @Test
    @org.testng.annotations.Test
    public void testConstructorWithNullLowerEndpoint()
    {
        UpperEndpoint<Integer> upperEndpoint = new UpperEndpoint<>(FIFTEEN, Clusivity.INCLUSIVE);
        assertThrows(NullPointerException.class, ()->new IntegerInterval(null, upperEndpoint));
    }

    @Test
    @org.testng.annotations.Test
    public void testConstructorWithNullUpperEndpoint()
    {
        LowerEndpoint<Integer> lowerEndpoint = new LowerEndpoint<>(FOURTEEN, Clusivity.INCLUSIVE);
        assertThrows(NullPointerException.class, ()->new IntegerInterval(lowerEndpoint, null));
    }
    
    @Test
    @org.testng.annotations.Test
    public void testLowerEndpointGreaterThanUpperEndpoint()
    {
        assertThrows(InvalidDataException.class,
                () -> new IntegerInterval(new LowerEndpoint<>(-FOURTEEN, Clusivity.INCLUSIVE), new UpperEndpoint<>(-FIFTEEN, Clusivity.INCLUSIVE)));
        assertThrows(InvalidDataException.class,
                () -> new IntegerInterval(new LowerEndpoint<>(FIFTEEN, Clusivity.EXCLUSIVE), new UpperEndpoint<>(FOURTEEN, Clusivity.INCLUSIVE)));
        assertThrows(InvalidDataException.class,
                () -> new IntegerInterval(new LowerEndpoint<>(TWO, Clusivity.EXCLUSIVE), new UpperEndpoint<>(-ONE, Clusivity.INCLUSIVE)));
    }
    
    @Test
    @org.testng.annotations.Test
    public void testConstructor()
    {
        LowerEndpoint<Integer> lowerEndpoint = new LowerEndpoint<>(FOURTEEN, Clusivity.INCLUSIVE);
        UpperEndpoint<Integer> upperEndpoint = new UpperEndpoint<>(FIFTEEN, Clusivity.INCLUSIVE);
        IntegerInterval interval = new IntegerInterval(lowerEndpoint, upperEndpoint);

        assertEquals(lowerEndpoint, interval.getLowerEndpoint());
        assertEquals(upperEndpoint, interval.getUpperEndpoint());
    }

    @Test
    @org.testng.annotations.Test
    public void testEqualsPositive()
    {
        LowerEndpoint<Integer> lowerEndpoint = new LowerEndpoint<>(FOURTEEN, Clusivity.INCLUSIVE);
        UpperEndpoint<Integer> upperEndpoint = new UpperEndpoint<>(FIFTEEN, Clusivity.INCLUSIVE);

        IntegerInterval firstInterval = new IntegerInterval(lowerEndpoint, upperEndpoint);
        assertTrue(firstInterval.equals(firstInterval), NL + firstInterval + NL + firstInterval + NL);

        IntegerInterval secondInterval = new IntegerInterval(lowerEndpoint, upperEndpoint);
        assertTrue(firstInterval.equals(secondInterval), NL + firstInterval + NL + secondInterval + NL);

        lowerEndpoint = new LowerEndpoint<>(FOURTEEN, Clusivity.EXCLUSIVE);
        upperEndpoint = new UpperEndpoint<>(FIFTEEN, Clusivity.EXCLUSIVE);

        IntegerInterval thirdInterval = new IntegerInterval(lowerEndpoint, upperEndpoint);
        assertTrue(thirdInterval.equals(thirdInterval), NL + thirdInterval + NL + thirdInterval + NL);

        IntegerInterval fourthInterval = new IntegerInterval(lowerEndpoint, upperEndpoint);
        assertTrue(thirdInterval.equals(fourthInterval), NL + thirdInterval + NL + fourthInterval + NL);

        assertFalse(firstInterval.equals(thirdInterval));
        assertFalse(secondInterval.equals(fourthInterval));
    }

    @Test
    @org.testng.annotations.Test
    public void testEqualsNegative()
    {
        LowerEndpoint<Integer> lowerEndpoint = new LowerEndpoint<>(-FIFTEEN, Clusivity.INCLUSIVE);
        UpperEndpoint<Integer> upperEndpoint = new UpperEndpoint<>(-FOURTEEN, Clusivity.INCLUSIVE);

        IntegerInterval firstInterval = new IntegerInterval(lowerEndpoint, upperEndpoint);
        assertTrue(firstInterval.equals(firstInterval), NL + firstInterval + NL + firstInterval + NL);

        IntegerInterval secondInterval = new IntegerInterval(lowerEndpoint, upperEndpoint);
        assertTrue(firstInterval.equals(secondInterval), NL + firstInterval + NL + secondInterval + NL);

        lowerEndpoint = new LowerEndpoint<>(-FIFTEEN, Clusivity.EXCLUSIVE);
        upperEndpoint = new UpperEndpoint<>(-FOURTEEN, Clusivity.EXCLUSIVE);

        IntegerInterval thirdInterval = new IntegerInterval(lowerEndpoint, upperEndpoint);
        assertTrue(thirdInterval.equals(thirdInterval), NL + thirdInterval + NL + thirdInterval + NL);

        IntegerInterval fourthInterval = new IntegerInterval(lowerEndpoint, upperEndpoint);
        assertTrue(thirdInterval.equals(fourthInterval), NL + thirdInterval + NL + fourthInterval + NL);

        assertFalse(firstInterval.equals(thirdInterval));
        assertFalse(secondInterval.equals(fourthInterval));
    }

    @Test
    @org.testng.annotations.Test
    public void testEqualsPositiveAndNegative()
    {
        LowerEndpoint<Integer> lowerEndpoint = new LowerEndpoint<>(-FIFTEEN, Clusivity.INCLUSIVE);
        UpperEndpoint<Integer> upperEndpoint = new UpperEndpoint<>(FOURTEEN, Clusivity.INCLUSIVE);

        IntegerInterval firstInterval = new IntegerInterval(lowerEndpoint, upperEndpoint);
        assertTrue(firstInterval.equals(firstInterval), NL + firstInterval + NL + firstInterval + NL);

        IntegerInterval secondInterval = new IntegerInterval(lowerEndpoint, upperEndpoint);
        assertTrue(firstInterval.equals(secondInterval), NL + firstInterval + NL + secondInterval + NL);

        lowerEndpoint = new LowerEndpoint<>(-FIFTEEN, Clusivity.EXCLUSIVE);
        upperEndpoint = new UpperEndpoint<>(FOURTEEN, Clusivity.EXCLUSIVE);

        IntegerInterval thirdInterval = new IntegerInterval(lowerEndpoint, upperEndpoint);
        assertTrue(thirdInterval.equals(thirdInterval), NL + thirdInterval + NL + thirdInterval + NL);

        IntegerInterval fourthInterval = new IntegerInterval(lowerEndpoint, upperEndpoint);
        assertTrue(thirdInterval.equals(fourthInterval), NL + thirdInterval + NL + fourthInterval + NL);

        assertFalse(firstInterval.equals(thirdInterval));
        assertFalse(secondInterval.equals(fourthInterval));
    }

    @Test
    @org.testng.annotations.Test
    public void testNotEquals()
    {
        IntegerInterval firstInterval = new IntegerInterval(new LowerEndpoint<>(FOURTEEN, Clusivity.INCLUSIVE), new UpperEndpoint<>(FIFTEEN, Clusivity.INCLUSIVE));
        IntegerInterval secondInterval = new IntegerInterval(new LowerEndpoint<>(FOURTEEN, Clusivity.EXCLUSIVE), new UpperEndpoint<>(FIFTEEN, Clusivity.EXCLUSIVE));

        assertFalse(firstInterval.equals(secondInterval));

        IntegerInterval thirdInterval = new IntegerInterval(new LowerEndpoint<>(-FIFTEEN, Clusivity.INCLUSIVE), new UpperEndpoint<>(-FOURTEEN, Clusivity.INCLUSIVE));
        IntegerInterval fourthInterval = new IntegerInterval(new LowerEndpoint<>(-FIFTEEN, Clusivity.EXCLUSIVE), new UpperEndpoint<>(-FOURTEEN, Clusivity.EXCLUSIVE));

        assertFalse(thirdInterval.equals(fourthInterval));
        assertFalse(firstInterval.equals(fourthInterval));
        assertFalse(secondInterval.equals(thirdInterval));
    }

    @Test
    @org.testng.annotations.Test
    public void testIsEmpty()
    {
        assertFalse(new IntegerInterval(new LowerEndpoint<>(TWO, Clusivity.INCLUSIVE), new UpperEndpoint<>(TWO, Clusivity.INCLUSIVE)).isEmpty());
        assertTrue(new IntegerInterval(new LowerEndpoint<>(TWO, Clusivity.INCLUSIVE), new UpperEndpoint<>(TWO, Clusivity.EXCLUSIVE)).isEmpty());
        assertTrue(new IntegerInterval(new LowerEndpoint<>(TWO, Clusivity.EXCLUSIVE), new UpperEndpoint<>(TWO, Clusivity.INCLUSIVE)).isEmpty());
        assertTrue(new IntegerInterval(new LowerEndpoint<>(TWO, Clusivity.EXCLUSIVE), new UpperEndpoint<>(TWO, Clusivity.EXCLUSIVE)).isEmpty());
        assertFalse(new IntegerInterval(new LowerEndpoint<>(ZERO, Clusivity.INCLUSIVE), new UpperEndpoint<>(ZERO, Clusivity.INCLUSIVE)).isEmpty());
        assertTrue(new IntegerInterval(new LowerEndpoint<>(ZERO, Clusivity.INCLUSIVE), new UpperEndpoint<>(ZERO, Clusivity.EXCLUSIVE)).isEmpty());
        assertTrue(new IntegerInterval(new LowerEndpoint<>(ZERO, Clusivity.EXCLUSIVE), new UpperEndpoint<>(ZERO, Clusivity.EXCLUSIVE)).isEmpty());
        assertFalse(new IntegerInterval(new LowerEndpoint<>(-TWO, Clusivity.INCLUSIVE), new UpperEndpoint<>(-TWO, Clusivity.INCLUSIVE)).isEmpty());
        assertTrue(new IntegerInterval(new LowerEndpoint<>(-TWO, Clusivity.INCLUSIVE), new UpperEndpoint<>(-TWO, Clusivity.EXCLUSIVE)).isEmpty());
        assertTrue(new IntegerInterval(new LowerEndpoint<>(-TWO, Clusivity.EXCLUSIVE), new UpperEndpoint<>(-TWO, Clusivity.EXCLUSIVE)).isEmpty());
        assertFalse(new IntegerInterval(new LowerEndpoint<>(null, Clusivity.EXCLUSIVE), new UpperEndpoint<>(-TWO, Clusivity.EXCLUSIVE)).isEmpty());
        assertFalse(new IntegerInterval(new LowerEndpoint<>(-TWO, Clusivity.EXCLUSIVE), new UpperEndpoint<>(null, Clusivity.EXCLUSIVE)).isEmpty());
        assertFalse(new IntegerInterval(new LowerEndpoint<>(null, Clusivity.EXCLUSIVE), new UpperEndpoint<>(null, Clusivity.EXCLUSIVE)).isEmpty());
    }

    @Test
    @org.testng.annotations.Test
    public void testIsUnbounded()
    {
        assertFalse(new IntegerInterval(new LowerEndpoint<>(ONE, Clusivity.INCLUSIVE), new UpperEndpoint<>(ONE, Clusivity.INCLUSIVE)).isUnBounded());
        assertFalse(new IntegerInterval(new LowerEndpoint<>(ONE, Clusivity.INCLUSIVE), new UpperEndpoint<>(ONE, Clusivity.EXCLUSIVE)).isUnBounded());
        assertFalse(new IntegerInterval(new LowerEndpoint<>(ONE, Clusivity.EXCLUSIVE), new UpperEndpoint<>(ONE, Clusivity.EXCLUSIVE)).isUnBounded());
        assertFalse(new IntegerInterval(new LowerEndpoint<>(ZERO, Clusivity.INCLUSIVE), new UpperEndpoint<>(ZERO, Clusivity.INCLUSIVE)).isUnBounded());
        assertFalse(new IntegerInterval(new LowerEndpoint<>(ZERO, Clusivity.INCLUSIVE), new UpperEndpoint<>(ZERO, Clusivity.EXCLUSIVE)).isUnBounded());
        assertFalse(new IntegerInterval(new LowerEndpoint<>(ZERO, Clusivity.EXCLUSIVE), new UpperEndpoint<>(ZERO, Clusivity.EXCLUSIVE)).isUnBounded());
        assertFalse(new IntegerInterval(new LowerEndpoint<>(-ONE, Clusivity.INCLUSIVE), new UpperEndpoint<>(-ONE, Clusivity.INCLUSIVE)).isUnBounded());
        assertFalse(new IntegerInterval(new LowerEndpoint<>(-ONE, Clusivity.INCLUSIVE), new UpperEndpoint<>(-ONE, Clusivity.EXCLUSIVE)).isUnBounded());
        assertFalse(new IntegerInterval(new LowerEndpoint<>(-ONE, Clusivity.EXCLUSIVE), new UpperEndpoint<>(-ONE, Clusivity.EXCLUSIVE)).isUnBounded());
        assertTrue(new IntegerInterval(new LowerEndpoint<>(ONE, Clusivity.INCLUSIVE), new UpperEndpoint<>(null, Clusivity.EXCLUSIVE)).isUnBounded());
        assertTrue(new IntegerInterval(new LowerEndpoint<>(null, Clusivity.EXCLUSIVE), new UpperEndpoint<>(ONE, Clusivity.INCLUSIVE)).isUnBounded());
        assertTrue(new IntegerInterval(new LowerEndpoint<>(null, Clusivity.EXCLUSIVE), new UpperEndpoint<>(null, Clusivity.EXCLUSIVE)).isUnBounded());
    }

    @Test
    @org.testng.annotations.Test
    public void testCompareEndpoints()
    {
        IntegerInterval interval = new IntegerInterval(new LowerEndpoint<>(ONE, Clusivity.INCLUSIVE), new UpperEndpoint<>(ONE, Clusivity.INCLUSIVE));

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
        int bound = 5;
        LowerEndpoint<Integer> lowerEndpoint = new LowerEndpoint<>(bound, Clusivity.INCLUSIVE);
        UpperEndpoint<Integer> upperEndpoint = new UpperEndpoint<>(bound, Clusivity.INCLUSIVE);
        IntegerInterval integerInterval = new IntegerInterval(lowerEndpoint, upperEndpoint);

        assertEquals(lowerEndpoint, integerInterval.getLowerEndpoint());
        assertEquals(upperEndpoint, integerInterval.getUpperEndpoint());
        assertEquals(ZERO, integerInterval.compare(lowerEndpoint, upperEndpoint));
        assertEquals(ZERO, integerInterval.compare(upperEndpoint, lowerEndpoint));
        assertFalse(integerInterval == null || integerInterval.isEmpty());
        assertFalse(integerInterval.isUnBounded());
        assertTrue(integerInterval.equals(integerInterval));
        assertFalse(integerInterval.isBefore(integerInterval));
        assertFalse(integerInterval.isAfter(integerInterval));
        assertTrue(integerInterval.intersects(integerInterval));
        assertEquals(integerInterval, integerInterval.intersect(integerInterval));
        assertEquals(integerInterval, integerInterval.union(integerInterval));
        List<IInterval<Integer>> subtractionInterval = integerInterval.subtract(integerInterval);
        assertTrue(subtractionInterval == null || subtractionInterval.isEmpty());
        assertEquals(integerInterval, integerInterval.subInterval(lowerEndpoint, upperEndpoint));
        assertEquals(integerInterval, integerInterval.clone());
        assertTrue(integerInterval.contains(bound));
        assertTrue(integerInterval.contains(lowerEndpoint));
        assertTrue(integerInterval.contains(upperEndpoint));
        assertTrue(integerInterval.contains(integerInterval));
        assertEquals(1, integerInterval.length().intValue());
    }

    @Test
    @org.testng.annotations.Test
    public void testEqualUnbounded()
    {
        Integer bound = null;
        LowerEndpoint<Integer> lowerEndpoint = new LowerEndpoint<>(bound, Clusivity.EXCLUSIVE);
        UpperEndpoint<Integer> upperEndpoint = new UpperEndpoint<>(bound, Clusivity.EXCLUSIVE);
        IntegerInterval integerInterval = new IntegerInterval(lowerEndpoint, upperEndpoint);

        assertEquals(lowerEndpoint, integerInterval.getLowerEndpoint());
        assertEquals(upperEndpoint, integerInterval.getUpperEndpoint());
        assertEquals(-1, integerInterval.compare(lowerEndpoint, upperEndpoint));
        assertEquals(1, integerInterval.compare(upperEndpoint, lowerEndpoint));
        assertFalse(integerInterval == null || integerInterval.isEmpty());
        assertTrue(integerInterval.isUnBounded());
        assertTrue(integerInterval.equals(integerInterval));
        assertFalse(integerInterval.isBefore(integerInterval));
        assertFalse(integerInterval.isAfter(integerInterval));
        assertTrue(integerInterval.intersects(integerInterval));
        assertEquals(integerInterval, integerInterval.intersect(integerInterval));
        assertEquals(integerInterval, integerInterval.union(integerInterval));
        assertEquals(integerInterval, integerInterval.subInterval(lowerEndpoint, upperEndpoint));
        assertEquals(integerInterval, integerInterval.clone());
        assertTrue(integerInterval.contains(bound));
        assertTrue(integerInterval.contains(lowerEndpoint));
        assertTrue(integerInterval.contains(upperEndpoint));
        assertTrue(integerInterval.contains(integerInterval));
        assertEquals(null, integerInterval.length());
        List<IInterval<Integer>> subtractionInterval = integerInterval.subtract(integerInterval);
        assertTrue(subtractionInterval == null || subtractionInterval.isEmpty());
    }


    @Test
    @org.testng.annotations.Test
    public void testPositiveEmptyInterval()
    {
        int bound = FIVE;
        LowerEndpoint<Integer> lowerEndpoint = new LowerEndpoint<>(bound, Clusivity.EXCLUSIVE);
        UpperEndpoint<Integer> upperEndpoint = new UpperEndpoint<>(bound, Clusivity.INCLUSIVE);
        IntegerInterval integerInterval = new IntegerInterval(lowerEndpoint, upperEndpoint);

        assertEquals(null, integerInterval.length());
        assertEquals(lowerEndpoint, integerInterval.getLowerEndpoint());
        assertEquals(upperEndpoint, integerInterval.getUpperEndpoint());
        assertEquals(1, integerInterval.compare(lowerEndpoint, upperEndpoint));
        assertEquals(-1, integerInterval.compare(upperEndpoint, lowerEndpoint));
        assertTrue(integerInterval == null || integerInterval.isEmpty());
        assertFalse(integerInterval.isUnBounded());
        assertTrue(integerInterval.equals(integerInterval));
        assertTrue(integerInterval.isBefore(integerInterval));
        assertTrue(integerInterval.isAfter(integerInterval));
        assertFalse(integerInterval.contains(bound));
        assertTrue(integerInterval.contains(integerInterval.getLowerEndpoint()));
        assertTrue(integerInterval.contains(integerInterval.getUpperEndpoint()));
        assertTrue(integerInterval.contains(integerInterval));
        assertTrue(integerInterval.intersects(integerInterval));
        assertEquals(integerInterval, integerInterval.intersect(integerInterval));
        assertEquals(integerInterval, integerInterval.union(integerInterval));
        List<IInterval<Integer>> subtractionIntervals = integerInterval.subtract(integerInterval);
        assertFalse(subtractionIntervals == null || subtractionIntervals.isEmpty());
        assertEquals(integerInterval, subtractionIntervals.get(0));
        assertEquals(integerInterval, integerInterval.subInterval(lowerEndpoint, upperEndpoint));
        assertEquals(integerInterval, integerInterval.clone());
    }

    @Test
    @org.testng.annotations.Test
    public void testNegativeEmptyInterval()
    {
        int bound = -FIVE;
        LowerEndpoint<Integer> lowerEndpoint = new LowerEndpoint<>(bound, Clusivity.EXCLUSIVE);
        UpperEndpoint<Integer> upperEndpoint = new UpperEndpoint<>(bound, Clusivity.EXCLUSIVE);
        IntegerInterval integerInterval = new IntegerInterval(lowerEndpoint, upperEndpoint);

        assertEquals(null, integerInterval.length());
        assertEquals(lowerEndpoint, integerInterval.getLowerEndpoint());
        assertEquals(upperEndpoint, integerInterval.getUpperEndpoint());
        assertEquals(1, integerInterval.compare(lowerEndpoint, upperEndpoint));
        assertEquals(-1, integerInterval.compare(upperEndpoint, lowerEndpoint));
        assertTrue(integerInterval == null || integerInterval.isEmpty());
        assertFalse(integerInterval.isUnBounded());
        assertTrue(integerInterval.equals(integerInterval));
        assertTrue(integerInterval.isBefore(integerInterval));
        assertTrue(integerInterval.isAfter(integerInterval));
        assertFalse(integerInterval.contains(bound));
        assertTrue(integerInterval.contains(integerInterval.getLowerEndpoint()));
        assertTrue(integerInterval.contains(integerInterval.getUpperEndpoint()));
        assertTrue(integerInterval.contains(integerInterval));
        assertTrue(integerInterval.intersects(integerInterval));
        assertEquals(integerInterval, integerInterval.intersect(integerInterval));
        assertEquals(integerInterval, integerInterval.union(integerInterval));
        List<IInterval<Integer>> subtractionIntervals = integerInterval.subtract(integerInterval);
        assertFalse(subtractionIntervals == null || subtractionIntervals.isEmpty());
        assertEquals(integerInterval, subtractionIntervals.get(0));
        assertEquals(integerInterval, integerInterval.subInterval(lowerEndpoint, upperEndpoint));
        assertEquals(integerInterval, integerInterval.clone());
    }

    @Test
    @org.testng.annotations.Test
    public void testPositiveUnEqualBoundsInclusive()
    {
        int lowerBound = TWO;
        int upperBound = THREE;
        LowerEndpoint<Integer> lowerEndpoint = new LowerEndpoint<>(lowerBound, Clusivity.INCLUSIVE);
        UpperEndpoint<Integer> upperEndpoint = new UpperEndpoint<>(upperBound, Clusivity.INCLUSIVE);
        IntegerInterval integerInterval = new IntegerInterval(lowerEndpoint, upperEndpoint);

        assertEquals(lowerEndpoint, integerInterval.getLowerEndpoint());
        assertEquals(upperEndpoint, integerInterval.getUpperEndpoint());
        assertEquals(-1, integerInterval.compare(lowerEndpoint, upperEndpoint));
        assertEquals(1, integerInterval.compare(upperEndpoint, lowerEndpoint));
        assertFalse(integerInterval == null || integerInterval.isEmpty());
        assertFalse(integerInterval.isUnBounded());
        assertTrue(integerInterval.equals(integerInterval));
        assertFalse(integerInterval.isBefore(integerInterval));
        assertFalse(integerInterval.isAfter(integerInterval));
        assertTrue(integerInterval.intersects(integerInterval));
        assertEquals(integerInterval, integerInterval.intersect(integerInterval));
        assertEquals(integerInterval, integerInterval.union(integerInterval));
        List<IInterval<Integer>> subtractionIntervals = integerInterval.subtract(integerInterval);
        assertTrue(subtractionIntervals == null || subtractionIntervals.isEmpty());
        assertEquals(integerInterval, integerInterval.subInterval(lowerEndpoint, upperEndpoint));
        assertEquals(integerInterval, integerInterval.clone());
        assertTrue(integerInterval.contains(lowerBound));
        assertTrue(integerInterval.contains(lowerEndpoint));
        assertTrue(integerInterval.contains(upperBound));
        assertTrue(integerInterval.contains(upperEndpoint));
        assertTrue(integerInterval.contains(integerInterval));
        assertEquals(TWO, integerInterval.length().intValue());
    }

    @Test
    @org.testng.annotations.Test
    public void testNegativeUnEqualBoundsInclusive()
    {
        int lowerBound = -THREE;
        int upperBound = -TWO;
        LowerEndpoint<Integer> lowerEndpoint = new LowerEndpoint<>(lowerBound, Clusivity.INCLUSIVE);
        UpperEndpoint<Integer> upperEndpoint = new UpperEndpoint<>(upperBound, Clusivity.INCLUSIVE);
        IntegerInterval integerInterval = new IntegerInterval(lowerEndpoint, upperEndpoint);

        assertEquals(lowerEndpoint, integerInterval.getLowerEndpoint());
        assertEquals(upperEndpoint, integerInterval.getUpperEndpoint());
        assertEquals(-1, integerInterval.compare(lowerEndpoint, upperEndpoint));
        assertEquals(1, integerInterval.compare(upperEndpoint, lowerEndpoint));
        assertFalse(integerInterval == null || integerInterval.isEmpty());
        assertFalse(integerInterval.isUnBounded());
        assertTrue(integerInterval.equals(integerInterval));
        assertFalse(integerInterval.isBefore(integerInterval));
        assertFalse(integerInterval.isAfter(integerInterval));
        assertTrue(integerInterval.intersects(integerInterval));
        assertEquals(integerInterval, integerInterval.intersect(integerInterval));
        assertEquals(integerInterval, integerInterval.union(integerInterval));
        List<IInterval<Integer>> subtractionIntervals = integerInterval.subtract(integerInterval);
        assertTrue(subtractionIntervals == null || subtractionIntervals.isEmpty());
        assertEquals(integerInterval, integerInterval.subInterval(lowerEndpoint, upperEndpoint));
        assertEquals(integerInterval, integerInterval.clone());
        assertTrue(integerInterval.contains(lowerBound));
        assertTrue(integerInterval.contains(lowerEndpoint));
        assertTrue(integerInterval.contains(upperBound));
        assertTrue(integerInterval.contains(upperEndpoint));
        assertTrue(integerInterval.contains(integerInterval));
        assertEquals(TWO, integerInterval.length().intValue());
    }

    @Test
    @org.testng.annotations.Test
    public void testPositiveUnEqualBoundsExclusive()
    {
        int lowerBound = TWO;
        int upperBound = THREE;
        LowerEndpoint<Integer> lowerEndpoint = new LowerEndpoint<>(lowerBound, Clusivity.EXCLUSIVE);
        UpperEndpoint<Integer> upperEndpoint = new UpperEndpoint<>(upperBound, Clusivity.EXCLUSIVE);
        IntegerInterval integerInterval = new IntegerInterval(lowerEndpoint, upperEndpoint);

        assertEquals(lowerEndpoint, integerInterval.getLowerEndpoint());
        assertEquals(upperEndpoint, integerInterval.getUpperEndpoint());
        assertEquals(1, integerInterval.compare(lowerEndpoint, upperEndpoint));
        assertEquals(-1, integerInterval.compare(upperEndpoint, lowerEndpoint));
        assertTrue(integerInterval == null || integerInterval.isEmpty());
        assertFalse(integerInterval.isUnBounded());
        assertTrue(integerInterval.equals(integerInterval));
        assertTrue(integerInterval.isBefore(integerInterval));
        assertTrue(integerInterval.isAfter(integerInterval));
        assertTrue(integerInterval.intersects(integerInterval));
        assertEquals(integerInterval, integerInterval.intersect(integerInterval));
        assertEquals(integerInterval, integerInterval.union(integerInterval));
        List<IInterval<Integer>> subtractionIntervals = integerInterval.subtract(integerInterval);
        assertFalse(subtractionIntervals == null || subtractionIntervals.isEmpty());
        assertEquals(1, subtractionIntervals.size());
        assertEquals(integerInterval, subtractionIntervals.get(0));
        assertEquals(integerInterval, integerInterval.subInterval(lowerEndpoint, upperEndpoint));
        assertEquals(integerInterval, integerInterval.clone());
        assertFalse(integerInterval.contains(lowerBound));
        assertTrue(integerInterval.contains(lowerEndpoint));
        assertFalse(integerInterval.contains(upperBound));
        assertTrue(integerInterval.contains(upperEndpoint));
        assertTrue(integerInterval.contains(integerInterval));
        assertEquals(null, integerInterval.length());
    }

    @Test
    @org.testng.annotations.Test
    public void testNegativeUnEqualBoundsExclusive()
    {
        int lowerBound = -THREE;
        int upperBound = -TWO;
        LowerEndpoint<Integer> lowerEndpoint = new LowerEndpoint<>(lowerBound, Clusivity.EXCLUSIVE);
        UpperEndpoint<Integer> upperEndpoint = new UpperEndpoint<>(upperBound, Clusivity.EXCLUSIVE);
        IntegerInterval integerInterval = new IntegerInterval(lowerEndpoint, upperEndpoint);

        assertEquals(lowerEndpoint, integerInterval.getLowerEndpoint());
        assertEquals(upperEndpoint, integerInterval.getUpperEndpoint());
        assertEquals(1, integerInterval.compare(lowerEndpoint, upperEndpoint));
        assertEquals(-1, integerInterval.compare(upperEndpoint, lowerEndpoint));
        assertTrue(integerInterval == null || integerInterval.isEmpty());
        assertFalse(integerInterval.isUnBounded());
        assertTrue(integerInterval.equals(integerInterval));
        assertTrue(integerInterval.isBefore(integerInterval));
        assertTrue(integerInterval.isAfter(integerInterval));
        assertTrue(integerInterval.intersects(integerInterval));
        assertEquals(integerInterval, integerInterval.intersect(integerInterval));
        assertEquals(integerInterval, integerInterval.union(integerInterval));
        List<IInterval<Integer>> subtractionIntervals = integerInterval.subtract(integerInterval);
        assertFalse(subtractionIntervals == null || subtractionIntervals.isEmpty());
        assertEquals(1, subtractionIntervals.size());
        assertEquals(integerInterval, subtractionIntervals.get(0));
        assertEquals(integerInterval, integerInterval.subInterval(lowerEndpoint, upperEndpoint));
        assertEquals(integerInterval, integerInterval.clone());
        assertFalse(integerInterval.contains(lowerBound));
        assertTrue(integerInterval.contains(lowerEndpoint));
        assertFalse(integerInterval.contains(upperBound));
        assertTrue(integerInterval.contains(upperEndpoint));
        assertTrue(integerInterval.contains(integerInterval));
        assertEquals(null, integerInterval.length());
    }

    @Test
    @org.testng.annotations.Test
    public void testIsBeforeValue()
    {
        Integer nullInteger = null;

        IntegerInterval interval = new IntegerInterval(new LowerEndpoint<>(EIGHT, Clusivity.INCLUSIVE), new UpperEndpoint<>(FIFTEEN, Clusivity.INCLUSIVE));

        assertNull(interval.isBefore(nullInteger));
        assertFalse(interval.isBefore(-ONE));
        assertFalse(interval.isBefore(ONE));
        assertFalse(interval.isBefore(EIGHT));
        assertFalse(interval.isBefore(FOURTEEN));
        assertFalse(interval.isBefore(FIFTEEN));
        assertTrue(interval.isBefore(SIXTEEN));

        interval = new IntegerInterval(new LowerEndpoint<>(EIGHT, Clusivity.EXCLUSIVE), new UpperEndpoint<>(FIFTEEN, Clusivity.EXCLUSIVE));

        assertNull(interval.isBefore(nullInteger));
        assertFalse(interval.isBefore(-ONE));
        assertFalse(interval.isBefore(ONE));
        assertFalse(interval.isBefore(EIGHT));
        assertFalse(interval.isBefore(FOURTEEN));
        assertTrue(interval.isBefore(FIFTEEN));
        assertTrue(interval.isBefore(SIXTEEN));

        interval = new IntegerInterval(new LowerEndpoint<>(-THREE, Clusivity.EXCLUSIVE), new UpperEndpoint<>(-ONE, Clusivity.INCLUSIVE));

        assertNull(interval.isBefore(nullInteger));
        assertFalse(interval.isBefore(-ONE));
        assertTrue(interval.isBefore(ONE));
        assertTrue(interval.isBefore(EIGHT));
        assertTrue(interval.isBefore(FOURTEEN));
        assertTrue(interval.isBefore(FIFTEEN));
        assertTrue(interval.isBefore(SIXTEEN));

        interval = new IntegerInterval(new LowerEndpoint<>(-THREE, Clusivity.EXCLUSIVE), new UpperEndpoint<>(-ONE, Clusivity.EXCLUSIVE));

        assertNull(interval.isBefore(nullInteger));
        assertTrue(interval.isBefore(-ONE));
        assertTrue(interval.isBefore(ONE));
        assertTrue(interval.isBefore(EIGHT));
        assertTrue(interval.isBefore(FOURTEEN));
        assertTrue(interval.isBefore(FIFTEEN));
        assertTrue(interval.isBefore(SIXTEEN));
    }

    @Test
    @org.testng.annotations.Test
    public void testBeforeIntervalInclusive()
    {
        LowerEndpoint<Integer> lowerEndpoint = new LowerEndpoint<>(FOURTEEN, Clusivity.INCLUSIVE);
        UpperEndpoint<Integer> upperEndpoint = new UpperEndpoint<>(FIFTEEN, Clusivity.INCLUSIVE);
        IntegerInterval in14_15in = new IntegerInterval(lowerEndpoint, upperEndpoint);

        assertFalse(in14_15in.isBefore(in14_15in));

        IntegerInterval in8_15in = new IntegerInterval(new LowerEndpoint<>(EIGHT, Clusivity.INCLUSIVE), upperEndpoint);
        assertFalse(in8_15in.isBefore(in14_15in));
        assertFalse(in14_15in.isBefore(in8_15in));

        IntegerInterval ex8_15in = new IntegerInterval(new LowerEndpoint<>(EIGHT, Clusivity.EXCLUSIVE), upperEndpoint);
        assertFalse(ex8_15in.isBefore(in14_15in));
        assertFalse(in14_15in.isBefore(ex8_15in));

        IntegerInterval in8_15ex = new IntegerInterval(new LowerEndpoint<>(EIGHT, Clusivity.INCLUSIVE), new UpperEndpoint<>(FIFTEEN, Clusivity.EXCLUSIVE));
        assertFalse(in14_15in.isBefore(in8_15ex));
        assertFalse(in8_15ex.isBefore(in14_15in));

        IntegerInterval in8_14in = new IntegerInterval(new LowerEndpoint<>(EIGHT, Clusivity.INCLUSIVE), new UpperEndpoint<>(FOURTEEN, Clusivity.INCLUSIVE));
        assertFalse(in8_14in.isBefore(in14_15in));
        assertFalse(in14_15in.isBefore(in8_14in));

        IntegerInterval in8_14ex = new IntegerInterval(new LowerEndpoint<>(EIGHT, Clusivity.INCLUSIVE), new UpperEndpoint<>(FOURTEEN, Clusivity.EXCLUSIVE));
        assertTrue(in8_14ex.isBefore(in14_15in));
        assertFalse(in14_15in.isBefore(in8_14ex));

        IntegerInterval neg5in_neg2in = new IntegerInterval(new LowerEndpoint<>(-FIVE, Clusivity.INCLUSIVE), new UpperEndpoint<>(-TWO, Clusivity.INCLUSIVE));
        assertFalse(in14_15in.isBefore(neg5in_neg2in));
        assertTrue(neg5in_neg2in.isBefore(in14_15in));
    }

    @Test
    @org.testng.annotations.Test
    public void testIsAfterValue()
    {
        Integer nullInteger = null;

        IntegerInterval interval = new IntegerInterval(new LowerEndpoint<>(EIGHT, Clusivity.INCLUSIVE), new UpperEndpoint<>(FIFTEEN, Clusivity.INCLUSIVE));

        assertNull(interval.isAfter(nullInteger));
        assertTrue(interval.isAfter(-ONE));
        assertTrue(interval.isAfter(ONE));
        assertFalse(interval.isAfter(EIGHT));
        assertFalse(interval.isAfter(FOURTEEN));
        assertFalse(interval.isAfter(FIFTEEN));
        assertFalse(interval.isAfter(SIXTEEN));

        interval = new IntegerInterval(new LowerEndpoint<>(EIGHT, Clusivity.EXCLUSIVE), new UpperEndpoint<>(FIFTEEN, Clusivity.EXCLUSIVE));

        assertNull(interval.isAfter(nullInteger));
        assertTrue(interval.isAfter(-ONE));
        assertTrue(interval.isAfter(ONE));
        assertTrue(interval.isAfter(EIGHT));
        assertFalse(interval.isAfter(FOURTEEN));
        assertFalse(interval.isAfter(FIFTEEN));
        assertFalse(interval.isAfter(SIXTEEN));

        interval = new IntegerInterval(new LowerEndpoint<>(-THREE, Clusivity.INCLUSIVE), new UpperEndpoint<>(-ONE, Clusivity.INCLUSIVE));

        assertNull(interval.isAfter(nullInteger));
        assertFalse(interval.isAfter(-THREE));
        assertFalse(interval.isAfter(-ONE));
        assertFalse(interval.isAfter(ONE));
        assertFalse(interval.isAfter(EIGHT));
        assertFalse(interval.isAfter(FOURTEEN));
        assertFalse(interval.isAfter(FIFTEEN));
        assertFalse(interval.isAfter(SIXTEEN));

        interval = new IntegerInterval(new LowerEndpoint<>(-THREE, Clusivity.EXCLUSIVE), new UpperEndpoint<>(-ONE, Clusivity.EXCLUSIVE));

        assertNull(interval.isAfter(nullInteger));
        assertTrue(interval.isAfter(-THREE));
        assertFalse(interval.isAfter(-ONE));
        assertFalse(interval.isAfter(ONE));
        assertFalse(interval.isAfter(EIGHT));
        assertFalse(interval.isAfter(FOURTEEN));
        assertFalse(interval.isAfter(FIFTEEN));
        assertFalse(interval.isAfter(SIXTEEN));
    }

    @Test
    @org.testng.annotations.Test
    public void testIsAfterInterval()
    {
        IntegerInterval interval = new IntegerInterval(new LowerEndpoint<>(EIGHT, Clusivity.INCLUSIVE), new UpperEndpoint<>(FIFTEEN, Clusivity.INCLUSIVE));

        assertTrue(interval.isAfter(new IntegerInterval(new LowerEndpoint<>(ONE, Clusivity.INCLUSIVE), new UpperEndpoint<>(EIGHT, Clusivity.EXCLUSIVE))));
        assertFalse(interval.isAfter(new IntegerInterval(new LowerEndpoint<>(ONE, Clusivity.INCLUSIVE), new UpperEndpoint<>(EIGHT, Clusivity.INCLUSIVE))));
        assertFalse(interval.isAfter(new IntegerInterval(new LowerEndpoint<>(EIGHT, Clusivity.INCLUSIVE), new UpperEndpoint<>(FIFTEEN, Clusivity.INCLUSIVE))));
        assertFalse(interval.isAfter(new IntegerInterval(new LowerEndpoint<>(FOURTEEN, Clusivity.INCLUSIVE), new UpperEndpoint<>(FIFTEEN, Clusivity.INCLUSIVE))));
        assertFalse(interval.isAfter(new IntegerInterval(new LowerEndpoint<>(FIFTEEN, Clusivity.INCLUSIVE), new UpperEndpoint<>(SIXTEEN, Clusivity.INCLUSIVE))));
        assertFalse(interval.isAfter(new IntegerInterval(new LowerEndpoint<>(FIFTEEN, Clusivity.EXCLUSIVE), new UpperEndpoint<>(SIXTEEN, Clusivity.INCLUSIVE))));
    }

    @Test
    @org.testng.annotations.Test
    public void testLength()
    {
        IntegerInterval interval = new IntegerInterval(new LowerEndpoint<>(THREE, Clusivity.INCLUSIVE), new UpperEndpoint<>(FIFTEEN, Clusivity.INCLUSIVE));
        assertEquals(13, interval.length().intValue());

        interval = new IntegerInterval(new LowerEndpoint<>(THREE, Clusivity.INCLUSIVE), new UpperEndpoint<>(FIFTEEN, Clusivity.EXCLUSIVE));
        assertEquals(12, interval.length().intValue());

        interval = new IntegerInterval(new LowerEndpoint<>(THREE, Clusivity.EXCLUSIVE), new UpperEndpoint<>(FIFTEEN, Clusivity.INCLUSIVE));
        assertEquals(12, interval.length().intValue());

        interval = new IntegerInterval(new LowerEndpoint<>(THREE, Clusivity.EXCLUSIVE), new UpperEndpoint<>(FIFTEEN, Clusivity.EXCLUSIVE));
        assertEquals(11, interval.length().intValue());

        interval = new IntegerInterval(new LowerEndpoint<>(-FIVE, Clusivity.INCLUSIVE), new UpperEndpoint<>(-ONE, Clusivity.INCLUSIVE));
        assertEquals(5, interval.length().intValue());

        interval = new IntegerInterval(new LowerEndpoint<>(-FIVE, Clusivity.INCLUSIVE), new UpperEndpoint<>(-ONE, Clusivity.EXCLUSIVE));
        assertEquals(4, interval.length().intValue());

        interval = new IntegerInterval(new LowerEndpoint<>(-FIVE, Clusivity.EXCLUSIVE), new UpperEndpoint<>(-ONE, Clusivity.INCLUSIVE));
        assertEquals(4, interval.length().intValue());

        interval = new IntegerInterval(new LowerEndpoint<>(-FIVE, Clusivity.EXCLUSIVE), new UpperEndpoint<>(-ONE, Clusivity.EXCLUSIVE));
        assertEquals(3, interval.length().intValue());

        interval = new IntegerInterval(new LowerEndpoint<>(-FIVE, Clusivity.INCLUSIVE), new UpperEndpoint<>(TWO, Clusivity.INCLUSIVE));
        assertEquals(8, interval.length().intValue());

        interval = new IntegerInterval(new LowerEndpoint<>(-FIVE, Clusivity.INCLUSIVE), new UpperEndpoint<>(TWO, Clusivity.EXCLUSIVE));
        assertEquals(7, interval.length().intValue());

        interval = new IntegerInterval(new LowerEndpoint<>(-FIVE, Clusivity.EXCLUSIVE), new UpperEndpoint<>(TWO, Clusivity.INCLUSIVE));
        assertEquals(7, interval.length().intValue());

        interval = new IntegerInterval(new LowerEndpoint<>(-FIVE, Clusivity.EXCLUSIVE), new UpperEndpoint<>(TWO, Clusivity.EXCLUSIVE));
        assertEquals(6, interval.length().intValue());

        interval = new IntegerInterval(new LowerEndpoint<>(TWO, Clusivity.INCLUSIVE), new UpperEndpoint<>(TWO, Clusivity.INCLUSIVE));
        assertEquals(1, interval.length().intValue());

        interval = new IntegerInterval(new LowerEndpoint<>(TWO, Clusivity.INCLUSIVE), new UpperEndpoint<>(TWO, Clusivity.EXCLUSIVE));
        assertEquals(null, interval.length());

       interval = new IntegerInterval(new LowerEndpoint<>(TWO, Clusivity.EXCLUSIVE), new UpperEndpoint<>(TWO, Clusivity.EXCLUSIVE));
        assertEquals(null, interval.length());
    }

    @Test
    @org.testng.annotations.Test
    public void testPositiveNullSubInterval()
    {
        LowerEndpoint<Integer> lowerEndpoint = new LowerEndpoint<>(EIGHT, Clusivity.INCLUSIVE);
        UpperEndpoint<Integer> upperEndpoint = new UpperEndpoint<>(FOURTEEN, Clusivity.INCLUSIVE);

        IntegerInterval interval = new IntegerInterval(lowerEndpoint, upperEndpoint);
        IntegerInterval subInterval = interval.subInterval(lowerEndpoint, upperEndpoint);
        assertNotNull(subInterval);
        assertEquals(interval, subInterval);

        lowerEndpoint = new LowerEndpoint<>(EIGHT, Clusivity.EXCLUSIVE);
        upperEndpoint = new UpperEndpoint<>(EIGHT, Clusivity.EXCLUSIVE);
        subInterval = interval.subInterval(lowerEndpoint, upperEndpoint);
        String message = interval + ".subInterval(" + lowerEndpoint + ", " + upperEndpoint + ")";
        assertEquals(new IntegerInterval(lowerEndpoint, upperEndpoint), subInterval, message);

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
        LowerEndpoint<Integer> lowerEndpoint = new LowerEndpoint<>(-FOURTEEN, Clusivity.INCLUSIVE);
        UpperEndpoint<Integer> upperEndpoint = new UpperEndpoint<>(-EIGHT, Clusivity.INCLUSIVE);

        IntegerInterval interval = new IntegerInterval(lowerEndpoint, upperEndpoint);
        IntegerInterval subInterval = interval.subInterval(lowerEndpoint, upperEndpoint);
        assertNotNull(subInterval);
        assertEquals(interval, subInterval);

        lowerEndpoint = new LowerEndpoint<>(-EIGHT, Clusivity.EXCLUSIVE);
        upperEndpoint = new UpperEndpoint<>(-EIGHT, Clusivity.EXCLUSIVE);
        subInterval = interval.subInterval(lowerEndpoint, upperEndpoint);
        String message = interval + ".subInterval(" + lowerEndpoint + ", " + upperEndpoint + ")";
        assertEquals(new IntegerInterval(lowerEndpoint, upperEndpoint), subInterval, message);

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
        LowerEndpoint<Integer> lowerEndpoint = new LowerEndpoint<>(EIGHT, Clusivity.INCLUSIVE);
        UpperEndpoint<Integer> upperEndpoint = new UpperEndpoint<>(FIFTEEN, Clusivity.INCLUSIVE);
        IntegerInterval interval = new IntegerInterval(lowerEndpoint, upperEndpoint);

        UpperEndpoint<Integer> newUpperEndpoint = new UpperEndpoint<>(FOURTEEN, Clusivity.INCLUSIVE);
        IntegerInterval subInterval = interval.subInterval(lowerEndpoint, newUpperEndpoint);
        assertTrue(new IntegerInterval(lowerEndpoint, newUpperEndpoint).equals(subInterval));

        subInterval = interval.subInterval(new LowerEndpoint<>(EIGHT, Clusivity.INCLUSIVE), new UpperEndpoint<>(FIFTEEN, Clusivity.EXCLUSIVE));
        IntegerInterval expectedSubInterval = new IntegerInterval(new LowerEndpoint<>(EIGHT, Clusivity.INCLUSIVE), new UpperEndpoint<>(FIFTEEN, Clusivity.EXCLUSIVE));
        assertEquals(expectedSubInterval, subInterval);

        subInterval = interval.subInterval(new LowerEndpoint<>(EIGHT, Clusivity.EXCLUSIVE), new UpperEndpoint<>(FIFTEEN, Clusivity.INCLUSIVE));
        expectedSubInterval = new IntegerInterval(new LowerEndpoint<>(EIGHT, Clusivity.EXCLUSIVE), new UpperEndpoint<>(FIFTEEN, Clusivity.INCLUSIVE));
        assertEquals(expectedSubInterval, subInterval);

        subInterval = interval.subInterval(new LowerEndpoint<>(EIGHT, Clusivity.EXCLUSIVE), new UpperEndpoint<>(FIFTEEN, Clusivity.EXCLUSIVE));
        expectedSubInterval = new IntegerInterval(new LowerEndpoint<>(EIGHT, Clusivity.EXCLUSIVE), new UpperEndpoint<>(FIFTEEN, Clusivity.EXCLUSIVE));
        assertEquals(expectedSubInterval, subInterval);
    }

    @Test
    @org.testng.annotations.Test
    public void testNegativeSubInterval()
    {
        LowerEndpoint<Integer> lowerEndpoint = new LowerEndpoint<>(-FIFTEEN, Clusivity.INCLUSIVE);
        UpperEndpoint<Integer> upperEndpoint = new UpperEndpoint<>(-EIGHT, Clusivity.INCLUSIVE);
        IntegerInterval interval = new IntegerInterval(lowerEndpoint, upperEndpoint);

        UpperEndpoint<Integer> newUpperEndpoint = new UpperEndpoint<>(-FOURTEEN, Clusivity.INCLUSIVE);
        IntegerInterval subInterval = interval.subInterval(lowerEndpoint, newUpperEndpoint);
        assertTrue(new IntegerInterval(lowerEndpoint, newUpperEndpoint).equals(subInterval));

        subInterval = interval.subInterval(new LowerEndpoint<>(-FIFTEEN, Clusivity.INCLUSIVE), new UpperEndpoint<>(-EIGHT, Clusivity.EXCLUSIVE));
        IntegerInterval expectedSubInterval = new IntegerInterval(new LowerEndpoint<>(-FIFTEEN, Clusivity.INCLUSIVE), new UpperEndpoint<>(-EIGHT, Clusivity.EXCLUSIVE));
        assertEquals(expectedSubInterval, subInterval);

        subInterval = interval.subInterval(new LowerEndpoint<>(-FIFTEEN, Clusivity.EXCLUSIVE), new UpperEndpoint<>(-EIGHT, Clusivity.INCLUSIVE));
        expectedSubInterval = new IntegerInterval(new LowerEndpoint<>(-FIFTEEN, Clusivity.EXCLUSIVE), new UpperEndpoint<>(-EIGHT, Clusivity.INCLUSIVE));
        assertEquals(expectedSubInterval, subInterval);

        subInterval = interval.subInterval(new LowerEndpoint<>(-FIFTEEN, Clusivity.EXCLUSIVE), new UpperEndpoint<>(-EIGHT, Clusivity.EXCLUSIVE));
        expectedSubInterval = new IntegerInterval(new LowerEndpoint<>(-FIFTEEN, Clusivity.EXCLUSIVE), new UpperEndpoint<>(-EIGHT, Clusivity.EXCLUSIVE));
        assertEquals(expectedSubInterval, subInterval);
    }

    @Test
    @org.testng.annotations.Test
    public void testOutOfBoundsSubInterval()
    {
        LowerEndpoint<Integer> lowerEndpoint = new LowerEndpoint<>(EIGHT, Clusivity.EXCLUSIVE);
        UpperEndpoint<Integer> upperEndpoint = new UpperEndpoint<>(FIFTEEN, Clusivity.EXCLUSIVE);
        IntegerInterval interval = new IntegerInterval(lowerEndpoint, upperEndpoint);
        IntegerInterval subInterval = interval.subInterval(lowerEndpoint, new UpperEndpoint<>(FIFTEEN, Clusivity.INCLUSIVE));
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
        LowerEndpoint<Integer> lowerEndpoint = new LowerEndpoint<>(EIGHT, Clusivity.INCLUSIVE);
        UpperEndpoint<Integer> upperEndpoint = new UpperEndpoint<>(FIFTEEN, Clusivity.INCLUSIVE);
        IntegerInterval interval = new IntegerInterval(lowerEndpoint, upperEndpoint);

        assertFalse(interval.contains(ONE));
        assertTrue(interval.contains(EIGHT));
        assertTrue(interval.contains(FOURTEEN));
        assertTrue(interval.contains(FIFTEEN));
        assertFalse(interval.contains(SIXTEEN));

        lowerEndpoint = new LowerEndpoint<>(EIGHT, Clusivity.EXCLUSIVE);
        upperEndpoint = new UpperEndpoint<>(FIFTEEN, Clusivity.EXCLUSIVE);
        interval = new IntegerInterval(lowerEndpoint, upperEndpoint);

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
        LowerEndpoint<Integer> lowerEndpoint = new LowerEndpoint<>(FOURTEEN, Clusivity.EXCLUSIVE);
        UpperEndpoint<Integer> upperEndpoint = new UpperEndpoint<>(FIFTEEN, Clusivity.EXCLUSIVE);
        IntegerInterval ex14_15ex = new IntegerInterval(lowerEndpoint, upperEndpoint);
        IntegerInterval same_ex14_15ex = new IntegerInterval(lowerEndpoint, upperEndpoint);

        assertTrue(ex14_15ex.contains(same_ex14_15ex));
        assertTrue(same_ex14_15ex.contains(ex14_15ex));

        assertTrue(ex14_15ex.intersects(same_ex14_15ex));
        assertTrue(same_ex14_15ex.intersects(ex14_15ex));

        IntegerInterval intersection = ex14_15ex.intersect(same_ex14_15ex);
        assertNotNull(intersection);
        assertTrue(ex14_15ex.equals(intersection));

        intersection = same_ex14_15ex.intersect(ex14_15ex);
        assertNotNull(intersection);
        assertTrue(same_ex14_15ex.equals(intersection));

        IntegerInterval union = ex14_15ex.union(same_ex14_15ex);
        assertNotNull(union);
        assertTrue(ex14_15ex.equals(union));

        union = same_ex14_15ex.union(ex14_15ex);
        assertNotNull(union);
        assertTrue(ex14_15ex.equals(union));

        // (14, 15) = empty set
        List<IInterval<Integer>> remainders = ex14_15ex.subtract(same_ex14_15ex);
        String msg = "remainders " + (remainders == null ? "null" : remainders.toString());
        assertNotNull(remainders);
        assertFalse(remainders.isEmpty(), msg);
        assertTrue(remainders.get(0).isEmpty());
        assertEquals(ex14_15ex, remainders.get(ZERO));

        remainders = same_ex14_15ex.subtract(ex14_15ex);
        msg = "remainders " + (remainders == null ? "null" : remainders.toString());
        assertNotNull(remainders);
        assertFalse(remainders.isEmpty(), msg);
        assertTrue(remainders.get(ZERO).isEmpty());
        assertEquals(ex14_15ex, remainders.get(ZERO));
    }

    @Test
    @org.testng.annotations.Test
    public void testNonEmptyEqualIntervals()
    {
        LowerEndpoint<Integer> lowerEndpoint = new LowerEndpoint<>(FOURTEEN, Clusivity.INCLUSIVE);
        UpperEndpoint<Integer> upperEndpoint = new UpperEndpoint<>(FIFTEEN, Clusivity.INCLUSIVE);
        IntegerInterval ex14_15ex = new IntegerInterval(lowerEndpoint, upperEndpoint);
        IntegerInterval same_ex14_15ex = new IntegerInterval(lowerEndpoint, upperEndpoint);

        assertTrue(ex14_15ex.contains(same_ex14_15ex));
        assertTrue(same_ex14_15ex.contains(ex14_15ex));

        assertTrue(ex14_15ex.intersects(same_ex14_15ex));
        assertTrue(same_ex14_15ex.intersects(ex14_15ex));

        IntegerInterval intersection = ex14_15ex.intersect(same_ex14_15ex);
        assertNotNull(intersection);
        assertTrue(ex14_15ex.equals(intersection));

        intersection = same_ex14_15ex.intersect(ex14_15ex);
        assertNotNull(intersection);
        assertTrue(same_ex14_15ex.equals(intersection));

        IntegerInterval union = ex14_15ex.union(same_ex14_15ex);
        assertNotNull(union);
        assertTrue(ex14_15ex.equals(union));

        union = same_ex14_15ex.union(ex14_15ex);
        assertNotNull(union);
        assertTrue(ex14_15ex.equals(union));

        List<IInterval<Integer>> remainders = ex14_15ex.subtract(same_ex14_15ex);
        String msg = "remainders " + (remainders == null ? "null" : remainders.toString());
        assertTrue(remainders == null || remainders.isEmpty(), msg);

        remainders = same_ex14_15ex.subtract(ex14_15ex);
        msg = "remainders " + (remainders == null ? "null" : remainders.toString());
        assertTrue(remainders == null || remainders.isEmpty(), msg);
    }


    @Test
    @org.testng.annotations.Test
    public void testNonIntersectingIntervals()
    {
        LowerEndpoint<Integer> firstLowerEndpoint = new LowerEndpoint<>(FIFTEEN, Clusivity.EXCLUSIVE);
        UpperEndpoint<Integer> firstUpperEndpoint = new UpperEndpoint<>(FIFTEEN, Clusivity.EXCLUSIVE);
        IntegerInterval firstInterval = new IntegerInterval(firstLowerEndpoint, firstUpperEndpoint);

        LowerEndpoint<Integer> secondLowerEndpoint = new LowerEndpoint<>(FIFTEEN, Clusivity.EXCLUSIVE);
        UpperEndpoint<Integer> secondUpperEndpoint = new UpperEndpoint<>(FIFTEEN, Clusivity.EXCLUSIVE);
        IntegerInterval secondInterval = new IntegerInterval(secondLowerEndpoint, secondUpperEndpoint);

        assertTrue(firstInterval.contains(secondInterval));
        assertTrue(secondInterval.contains(firstInterval));

        assertTrue(firstInterval.intersects(secondInterval));
        assertTrue(secondInterval.intersects(firstInterval));

        IntegerInterval intersection = firstInterval.intersect(secondInterval);
        assertEquals(firstInterval, intersection);
        assertEquals(secondInterval, intersection);

        intersection = secondInterval.intersect(firstInterval);
        assertEquals(firstInterval, intersection);
        assertEquals(secondInterval, intersection);

        IntegerInterval union = firstInterval.union(secondInterval);
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
        LowerEndpoint<Integer> firstLowerEndpoint = new LowerEndpoint<>(ONE, Clusivity.EXCLUSIVE);
        UpperEndpoint<Integer> firstUpperEndpoint = new UpperEndpoint<>(EIGHT, Clusivity.EXCLUSIVE);
        IntegerInterval firstInterval = new IntegerInterval(firstLowerEndpoint, firstUpperEndpoint);
        assertEquals(6, firstInterval.length().intValue());

        LowerEndpoint<Integer> secondLowerEndpoint = new LowerEndpoint<>(FOURTEEN, Clusivity.EXCLUSIVE);
        UpperEndpoint<Integer> secondUpperEndpoint = new UpperEndpoint<>(SIXTEEN, Clusivity.EXCLUSIVE);
        IntegerInterval secondInterval = new IntegerInterval(secondLowerEndpoint, secondUpperEndpoint);
        assertEquals(1, secondInterval.length().intValue());

        // (1, 8) - (1, 16)
        List<IInterval<Integer>> remainders = firstInterval.subtract(secondInterval);
        assertNotNull(remainders);
        assertFalse(remainders == null || remainders.isEmpty());
        assertEquals(firstInterval, remainders.get(0));

        // (1, 16) - (1, 8)
        remainders = secondInterval.subtract(firstInterval);
        assertNotNull(remainders);
        assertFalse(remainders == null || remainders.isEmpty());
        assertEquals(secondInterval, remainders.get(0));
    }

    @Test
    @org.testng.annotations.Test
    public void testSubIntervals()
    {
        LowerEndpoint<Integer> firstLowerEndpoint = new LowerEndpoint<>(EIGHT, Clusivity.INCLUSIVE);
        UpperEndpoint<Integer> firstUpperEndpoint = new UpperEndpoint<>(SIXTEEN, Clusivity.INCLUSIVE);
        IntegerInterval firstInterval = new IntegerInterval(firstLowerEndpoint, firstUpperEndpoint);

        LowerEndpoint<Integer> secondLowerEndpoint = new LowerEndpoint<>(FOURTEEN, Clusivity.EXCLUSIVE);
        UpperEndpoint<Integer> secondUpperEndpoint = new UpperEndpoint<>(FIFTEEN, Clusivity.EXCLUSIVE);
        IntegerInterval emptyInterval = new IntegerInterval(secondLowerEndpoint, secondUpperEndpoint);

        assertTrue(firstInterval.contains(emptyInterval));
        assertFalse(emptyInterval.contains(firstInterval));

        assertTrue(firstInterval.intersects(emptyInterval));
        assertTrue(emptyInterval.intersects(firstInterval));

        IntegerInterval intersection = firstInterval.intersect(emptyInterval);
        assertNotNull(intersection);
        assertEquals(emptyInterval, intersection);
        assertTrue(emptyInterval.equals(intersection));

        intersection = emptyInterval.intersect(firstInterval);
        assertNotNull(intersection);
        assertTrue(emptyInterval.equals(intersection));

        IntegerInterval union = firstInterval.union(emptyInterval);
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
        LowerEndpoint<Integer> firstLowerEndpoint = new LowerEndpoint<>(EIGHT, Clusivity.INCLUSIVE);
        UpperEndpoint<Integer> firstUpperEndpoint = new UpperEndpoint<>(SIXTEEN, Clusivity.INCLUSIVE);
        IntegerInterval firstInterval = new IntegerInterval(firstLowerEndpoint, firstUpperEndpoint);

        LowerEndpoint<Integer> secondLowerEndpoint = new LowerEndpoint<>(FOURTEEN, Clusivity.EXCLUSIVE);
        UpperEndpoint<Integer> secondUpperEndpoint = new UpperEndpoint<>(FIFTEEN, Clusivity.EXCLUSIVE);
        IntegerInterval secondInterval = new IntegerInterval(secondLowerEndpoint, secondUpperEndpoint);

        List<IInterval<Integer>> remainders = firstInterval.subtract(secondInterval);
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
        LowerEndpoint<Integer> firstLowerEndpoint = new LowerEndpoint<>(ONE, Clusivity.INCLUSIVE);
        UpperEndpoint<Integer> firstUpperEndpoint = new UpperEndpoint<>(EIGHT, Clusivity.INCLUSIVE);
        IntegerInterval firstInterval = new IntegerInterval(firstLowerEndpoint, firstUpperEndpoint);

        LowerEndpoint<Integer> secondLowerEndpoint = new LowerEndpoint<>(EIGHT, Clusivity.EXCLUSIVE);
        UpperEndpoint<Integer> secondUpperEndpoint = new UpperEndpoint<>(FIFTEEN, Clusivity.EXCLUSIVE);
        IntegerInterval secondInterval = new IntegerInterval(secondLowerEndpoint, secondUpperEndpoint);

        List<IInterval<Integer>> remainders = firstInterval.subtract(secondInterval);
        assertNotNull(remainders);
        assertFalse(remainders == null || remainders.isEmpty());
        assertEquals(1, remainders.size());
        assertEquals(firstInterval, remainders.get(0));

        remainders = secondInterval.subtract(firstInterval);
        assertNotNull(remainders);
        assertFalse(remainders == null || remainders.isEmpty());
        assertEquals(1, remainders.size());
        assertEquals(secondInterval, remainders.get(0));
    }

    @Test
    @org.testng.annotations.Test
    public void testSubtractIntersectingIntervals()
    {
        LowerEndpoint<Integer> firstLowerEndpoint = new LowerEndpoint<>(ONE, Clusivity.INCLUSIVE);
        UpperEndpoint<Integer> firstUpperEndpoint = new UpperEndpoint<>(EIGHT, Clusivity.INCLUSIVE);
        IntegerInterval firstInterval = new IntegerInterval(firstLowerEndpoint, firstUpperEndpoint);

        LowerEndpoint<Integer> secondLowerEndpoint = new LowerEndpoint<>(EIGHT, Clusivity.INCLUSIVE);
        UpperEndpoint<Integer> secondUpperEndpoint = new UpperEndpoint<>(FIFTEEN, Clusivity.INCLUSIVE);
        IntegerInterval secondInterval = new IntegerInterval(secondLowerEndpoint, secondUpperEndpoint);

        IntegerInterval expectedInterval = new IntegerInterval(firstLowerEndpoint, new UpperEndpoint<>(EIGHT, Clusivity.EXCLUSIVE));
        List<IInterval<Integer>> remainders = firstInterval.subtract(secondInterval);
        assertNotNull(remainders);
        assertEquals(1, remainders.size());
        assertEquals(expectedInterval, remainders.get(0));

        expectedInterval = new IntegerInterval(new LowerEndpoint<>(EIGHT, Clusivity.EXCLUSIVE), secondUpperEndpoint);
        remainders = secondInterval.subtract(firstInterval);
        assertNotNull(remainders);
        assertEquals(1, remainders.size());
        assertEquals(expectedInterval, remainders.get(0));
    }
    
    @Test
    @org.testng.annotations.Test
    public void testIntersectingIntervals()
    {
        IntegerInterval firstInterval = new IntegerInterval(
                new LowerEndpoint<>(FIFTEEN, Clusivity.INCLUSIVE),
                new UpperEndpoint<>(SIXTEEN, Clusivity.INCLUSIVE));
        IntegerInterval secondInterval = new IntegerInterval(
                new LowerEndpoint<>(FOURTEEN, Clusivity.INCLUSIVE),
                new UpperEndpoint<>(FIFTEEN, Clusivity.INCLUSIVE));

        assertFalse(firstInterval.contains(secondInterval));
        assertFalse(secondInterval.contains(firstInterval));

        assertTrue(firstInterval.intersects(secondInterval));
        assertTrue(secondInterval.intersects(firstInterval));

        IntegerInterval expectedIntersection = new IntegerInterval(
                new LowerEndpoint<>(FIFTEEN, Clusivity.INCLUSIVE),
                new UpperEndpoint<>(FIFTEEN, Clusivity.INCLUSIVE));
        IntegerInterval intersection = firstInterval.intersect(secondInterval);
        assertNotNull(intersection);
        assertEquals(expectedIntersection, intersection);

        intersection = secondInterval.intersect(firstInterval);
        assertNotNull(intersection);
        assertEquals(expectedIntersection, intersection);

        IntegerInterval expectedUnion = new IntegerInterval(
                new LowerEndpoint<>(FOURTEEN, Clusivity.INCLUSIVE),
                new UpperEndpoint<>(SIXTEEN, Clusivity.INCLUSIVE));

        IntegerInterval union = firstInterval.union(secondInterval);
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
        IntegerInterval firstInterval = new IntegerInterval(
                new LowerEndpoint<>(ONE, Clusivity.INCLUSIVE),
                new UpperEndpoint<>(SIXTEEN, Clusivity.INCLUSIVE));
        IntegerInterval secondInterval = new IntegerInterval(
                new LowerEndpoint<>(ONE, Clusivity.INCLUSIVE),
                new UpperEndpoint<>(SIXTEEN, Clusivity.INCLUSIVE));

        List<IInterval<Integer>> subtractionIntervals = firstInterval.subtract(secondInterval);
        assertTrue(subtractionIntervals == null || subtractionIntervals.isEmpty());

        subtractionIntervals = secondInterval.subtract(firstInterval);
        assertTrue(subtractionIntervals == null || subtractionIntervals.isEmpty());
    }

    @Test
    @org.testng.annotations.Test
    public void testIntersectingIntervalsSubtraction()
    {
        IntegerInterval firstInterval = new IntegerInterval(
                new LowerEndpoint<>(FIFTEEN, Clusivity.INCLUSIVE),
                new UpperEndpoint<>(SIXTEEN, Clusivity.INCLUSIVE));
        IntegerInterval secondInterval = new IntegerInterval(
                new LowerEndpoint<>(FOURTEEN, Clusivity.INCLUSIVE),
                new UpperEndpoint<>(FIFTEEN, Clusivity.INCLUSIVE));

        List<IInterval<Integer>> remainders = firstInterval.subtract(secondInterval);
        assertFalse(remainders == null || remainders.isEmpty());
        assertEquals(1, remainders.size());
        assertEquals(new IntegerInterval(
                new LowerEndpoint<>(FIFTEEN, Clusivity.EXCLUSIVE),
                new UpperEndpoint<>(SIXTEEN, Clusivity.INCLUSIVE)),
                remainders.get(0));

        remainders = secondInterval.subtract(firstInterval);
        assertFalse(remainders == null || remainders.isEmpty());
        assertEquals(1, remainders.size());
        assertEquals(new IntegerInterval(
                new LowerEndpoint<>(FOURTEEN, Clusivity.INCLUSIVE),
                new UpperEndpoint<>(FIFTEEN, Clusivity.EXCLUSIVE)),
                remainders.get(0));
    }

}