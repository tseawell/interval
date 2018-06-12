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

public class TestDoubleInterval 
{
    public static final String NL = System.lineSeparator();

    public static final double ZERO = 0; 
    public static final double ONE = 1.0;
    public static final double TWO = 2.0;
    public static final double THREE = 3.0; 
    public static final double FIVE = 5.0;
    public static final double EIGHT = 8.0;
    public static final double FOURTEEN = 14.0;
    public static final double FIFTEEN = 15.0;
    public static final double SIXTEEN = 16.0;

 
    @Test
    @org.testng.annotations.Test
    public void testConstructorWithNullLowerEndpoint()
    {
        UpperEndpoint<Double> upperEndpoint = new UpperEndpoint<>(FIFTEEN, Clusivity.INCLUSIVE);
        assertThrows(NullPointerException.class, ()->new DoubleInterval (null, upperEndpoint));
    }

    @Test
    @org.testng.annotations.Test
    public void testConstructorWithNullUpperEndpoint()
    {
        LowerEndpoint<Double> lowerEndpoint = new LowerEndpoint<>(FOURTEEN, Clusivity.INCLUSIVE);
        assertThrows(NullPointerException.class, ()->new DoubleInterval (lowerEndpoint, null));
    }
    
    @Test
    @org.testng.annotations.Test
    public void testLowerEndpointGreaterThanUpperEndpoint()
    {
        assertThrows(InvalidDataException.class,
                ()->new DoubleInterval (new LowerEndpoint<>(-ONE, Clusivity.EXCLUSIVE), new UpperEndpoint<>(-TWO, Clusivity.INCLUSIVE)));
        assertThrows(InvalidDataException.class,
                ()->new DoubleInterval (new LowerEndpoint<>(ONE, Clusivity.EXCLUSIVE), new UpperEndpoint<>(-ONE, Clusivity.INCLUSIVE)));
        assertThrows(InvalidDataException.class,
                ()->new DoubleInterval (new LowerEndpoint<>(FIFTEEN, Clusivity.INCLUSIVE), new UpperEndpoint<>(FOURTEEN, Clusivity.INCLUSIVE)));
    }
    
    @Test
    @org.testng.annotations.Test
    public void testConstructor()
    {
        LowerEndpoint<Double> lowerEndpoint = new LowerEndpoint<>(FOURTEEN, Clusivity.INCLUSIVE);
        UpperEndpoint<Double> upperEndpoint = new UpperEndpoint<>(FIFTEEN, Clusivity.INCLUSIVE);
        DoubleInterval interval = new DoubleInterval (lowerEndpoint, upperEndpoint);

        assertEquals(lowerEndpoint, interval.getLowerEndpoint());
        assertEquals(upperEndpoint, interval.getUpperEndpoint());
    }

    @SuppressWarnings("EqualsWithItself")
    @Test
    @org.testng.annotations.Test
    public void testEqualsPositive()
    {
        LowerEndpoint<Double> lowerEndpoint = new LowerEndpoint<>(FOURTEEN, Clusivity.INCLUSIVE);
        UpperEndpoint<Double> upperEndpoint = new UpperEndpoint<>(FIFTEEN, Clusivity.INCLUSIVE);

        DoubleInterval  firstInterval = new DoubleInterval (lowerEndpoint, upperEndpoint);
        assertTrue(firstInterval.equals(firstInterval), NL + firstInterval + NL + firstInterval + NL);

        DoubleInterval  secondInterval = new DoubleInterval (lowerEndpoint, upperEndpoint);
        assertTrue(firstInterval.equals(secondInterval), NL + firstInterval + NL + secondInterval + NL);

        lowerEndpoint = new LowerEndpoint<>(FOURTEEN, Clusivity.EXCLUSIVE);
        upperEndpoint = new UpperEndpoint<>(FIFTEEN, Clusivity.EXCLUSIVE);

        DoubleInterval  thirdInterval = new DoubleInterval (lowerEndpoint, upperEndpoint);
        assertTrue(thirdInterval.equals(thirdInterval), NL + thirdInterval + NL + thirdInterval + NL);

        DoubleInterval  fourthInterval = new DoubleInterval (lowerEndpoint, upperEndpoint);
        assertTrue(thirdInterval.equals(fourthInterval), NL + thirdInterval + NL + fourthInterval + NL);

        assertFalse(firstInterval.equals(thirdInterval));
        assertFalse(secondInterval.equals(fourthInterval));
    }
    
    @SuppressWarnings("EqualsWithItself")
    @Test
    @org.testng.annotations.Test
    public void testEqualsNegative()
    {
        LowerEndpoint<Double> lowerEndpoint = new LowerEndpoint<>(-FIFTEEN, Clusivity.INCLUSIVE);
        UpperEndpoint<Double> upperEndpoint = new UpperEndpoint<>(-FOURTEEN, Clusivity.INCLUSIVE);

        DoubleInterval  firstInterval = new DoubleInterval (lowerEndpoint, upperEndpoint);
        assertTrue(firstInterval.equals(firstInterval), NL + firstInterval + NL + firstInterval + NL);

        DoubleInterval  secondInterval = new DoubleInterval (lowerEndpoint, upperEndpoint);
        assertTrue(firstInterval.equals(secondInterval), NL + firstInterval + NL + secondInterval + NL);

        lowerEndpoint = new LowerEndpoint<>(-FIFTEEN, Clusivity.EXCLUSIVE);
        upperEndpoint = new UpperEndpoint<>(-FOURTEEN, Clusivity.EXCLUSIVE);

        DoubleInterval  thirdInterval = new DoubleInterval (lowerEndpoint, upperEndpoint);
        assertTrue(thirdInterval.equals(thirdInterval), NL + thirdInterval + NL + thirdInterval + NL);

        DoubleInterval  fourthInterval = new DoubleInterval (lowerEndpoint, upperEndpoint);
        assertTrue(thirdInterval.equals(fourthInterval), NL + thirdInterval + NL + fourthInterval + NL);

        assertFalse(firstInterval.equals(thirdInterval));
        assertFalse(secondInterval.equals(fourthInterval));
    }

    @SuppressWarnings("EqualsWithItself")
    @Test
    @org.testng.annotations.Test
    public void testEqualsPositiveAndNegative()
    {
        LowerEndpoint<Double> lowerEndpoint = new LowerEndpoint<>(-FIFTEEN, Clusivity.INCLUSIVE);
        UpperEndpoint<Double> upperEndpoint = new UpperEndpoint<>(FOURTEEN, Clusivity.INCLUSIVE);

        DoubleInterval  firstInterval = new DoubleInterval (lowerEndpoint, upperEndpoint);
        assertTrue(firstInterval.equals(firstInterval), NL + firstInterval + NL + firstInterval + NL);

        DoubleInterval  secondInterval = new DoubleInterval (lowerEndpoint, upperEndpoint);
        assertTrue(firstInterval.equals(secondInterval), NL + firstInterval + NL + secondInterval + NL);

        lowerEndpoint = new LowerEndpoint<>(-FIFTEEN, Clusivity.EXCLUSIVE);
        upperEndpoint = new UpperEndpoint<>(FOURTEEN, Clusivity.EXCLUSIVE);

        DoubleInterval  thirdInterval = new DoubleInterval (lowerEndpoint, upperEndpoint);
        assertTrue(thirdInterval.equals(thirdInterval), NL + thirdInterval + NL + thirdInterval + NL);

        DoubleInterval  fourthInterval = new DoubleInterval (lowerEndpoint, upperEndpoint);
        assertTrue(thirdInterval.equals(fourthInterval), NL + thirdInterval + NL + fourthInterval + NL);

        assertFalse(firstInterval.equals(thirdInterval));
        assertFalse(secondInterval.equals(fourthInterval));
    }

    @Test
    @org.testng.annotations.Test
    public void testNotEquals()
    {
        DoubleInterval  firstInterval = new DoubleInterval (new LowerEndpoint<>(FOURTEEN, Clusivity.INCLUSIVE), new UpperEndpoint<>(FIFTEEN, Clusivity.INCLUSIVE));
        DoubleInterval  secondInterval = new DoubleInterval (new LowerEndpoint<>(FOURTEEN, Clusivity.EXCLUSIVE), new UpperEndpoint<>(FIFTEEN, Clusivity.EXCLUSIVE));

        assertFalse(firstInterval.equals(secondInterval));

        DoubleInterval  thirdInterval = new DoubleInterval (new LowerEndpoint<>(-FIFTEEN, Clusivity.INCLUSIVE), new UpperEndpoint<>(-FOURTEEN, Clusivity.INCLUSIVE));
        DoubleInterval  fourthInterval = new DoubleInterval (new LowerEndpoint<>(-FIFTEEN, Clusivity.EXCLUSIVE), new UpperEndpoint<>(-FOURTEEN, Clusivity.EXCLUSIVE));

        assertFalse(thirdInterval.equals(fourthInterval));
        assertFalse(firstInterval.equals(fourthInterval));
        assertFalse(secondInterval.equals(thirdInterval));
    }

    @Test
    @org.testng.annotations.Test
    public void testIsEmpty()
    {
        assertFalse(new DoubleInterval (new LowerEndpoint<>(TWO, Clusivity.INCLUSIVE), new UpperEndpoint<>(TWO, Clusivity.INCLUSIVE)).isEmpty());
        assertTrue(new DoubleInterval (new LowerEndpoint<>(TWO, Clusivity.INCLUSIVE), new UpperEndpoint<>(TWO, Clusivity.EXCLUSIVE)).isEmpty());
        assertTrue(new DoubleInterval (new LowerEndpoint<>(TWO, Clusivity.EXCLUSIVE), new UpperEndpoint<>(TWO, Clusivity.INCLUSIVE)).isEmpty());
        assertTrue(new DoubleInterval (new LowerEndpoint<>(TWO, Clusivity.EXCLUSIVE), new UpperEndpoint<>(TWO, Clusivity.EXCLUSIVE)).isEmpty());
        assertFalse(new DoubleInterval (new LowerEndpoint<>(ZERO, Clusivity.INCLUSIVE), new UpperEndpoint<>(ZERO, Clusivity.INCLUSIVE)).isEmpty());
        assertTrue(new DoubleInterval (new LowerEndpoint<>(ZERO, Clusivity.INCLUSIVE), new UpperEndpoint<>(ZERO, Clusivity.EXCLUSIVE)).isEmpty());
        assertTrue(new DoubleInterval (new LowerEndpoint<>(ZERO, Clusivity.EXCLUSIVE), new UpperEndpoint<>(ZERO, Clusivity.EXCLUSIVE)).isEmpty());
        assertFalse(new DoubleInterval (new LowerEndpoint<>(-TWO, Clusivity.INCLUSIVE), new UpperEndpoint<>(-TWO, Clusivity.INCLUSIVE)).isEmpty());
        assertTrue(new DoubleInterval (new LowerEndpoint<>(-TWO, Clusivity.INCLUSIVE), new UpperEndpoint<>(-TWO, Clusivity.EXCLUSIVE)).isEmpty());
        assertTrue(new DoubleInterval (new LowerEndpoint<>(-TWO, Clusivity.EXCLUSIVE), new UpperEndpoint<>(-TWO, Clusivity.EXCLUSIVE)).isEmpty());
        assertFalse(new DoubleInterval (new LowerEndpoint<>(null, Clusivity.EXCLUSIVE), new UpperEndpoint<>(-TWO, Clusivity.EXCLUSIVE)).isEmpty());
        assertFalse(new DoubleInterval (new LowerEndpoint<>(-TWO, Clusivity.EXCLUSIVE), new UpperEndpoint<>(null, Clusivity.EXCLUSIVE)).isEmpty());
    }

    @Test
    @org.testng.annotations.Test
    public void testIsUnbounded()
    {
        assertTrue(new DoubleInterval(new LowerEndpoint<>(null, Clusivity.EXCLUSIVE), new UpperEndpoint<>(null, Clusivity.EXCLUSIVE)).isUnBounded());
        assertTrue(new DoubleInterval(new LowerEndpoint<>(ONE, Clusivity.INCLUSIVE), new UpperEndpoint<>(null, Clusivity.EXCLUSIVE)).isUnBounded());
        assertTrue(new DoubleInterval(new LowerEndpoint<>(null, Clusivity.EXCLUSIVE), new UpperEndpoint<>(ONE, Clusivity.INCLUSIVE)).isUnBounded());
        assertFalse(new DoubleInterval(new LowerEndpoint<>(ONE, Clusivity.INCLUSIVE), new UpperEndpoint<>(ONE, Clusivity.INCLUSIVE)).isUnBounded());
        assertFalse(new DoubleInterval(new LowerEndpoint<>(ONE, Clusivity.INCLUSIVE), new UpperEndpoint<>(ONE, Clusivity.EXCLUSIVE)).isUnBounded());
        assertFalse(new DoubleInterval(new LowerEndpoint<>(ONE, Clusivity.EXCLUSIVE), new UpperEndpoint<>(ONE, Clusivity.EXCLUSIVE)).isUnBounded());
        assertFalse(new DoubleInterval(new LowerEndpoint<>(ZERO, Clusivity.INCLUSIVE), new UpperEndpoint<>(ZERO, Clusivity.INCLUSIVE)).isUnBounded());
        assertFalse(new DoubleInterval(new LowerEndpoint<>(ZERO, Clusivity.INCLUSIVE), new UpperEndpoint<>(ZERO, Clusivity.EXCLUSIVE)).isUnBounded());
        assertFalse(new DoubleInterval(new LowerEndpoint<>(ZERO, Clusivity.EXCLUSIVE), new UpperEndpoint<>(ZERO, Clusivity.EXCLUSIVE)).isUnBounded());
        assertFalse(new DoubleInterval(new LowerEndpoint<>(-ONE, Clusivity.INCLUSIVE), new UpperEndpoint<>(-ONE, Clusivity.INCLUSIVE)).isUnBounded());
        assertFalse(new DoubleInterval(new LowerEndpoint<>(-ONE, Clusivity.INCLUSIVE), new UpperEndpoint<>(-ONE, Clusivity.EXCLUSIVE)).isUnBounded());
        assertFalse(new DoubleInterval(new LowerEndpoint<>(-ONE, Clusivity.EXCLUSIVE), new UpperEndpoint<>(-ONE, Clusivity.EXCLUSIVE)).isUnBounded());
    }

    @Test
    @org.testng.annotations.Test
    public void testCompareEndpoints()
    {
        DoubleInterval  interval = new DoubleInterval (new LowerEndpoint<>(ONE, Clusivity.INCLUSIVE), new UpperEndpoint<>(ONE, Clusivity.INCLUSIVE));

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
        assertEquals(-1, interval.compare(new LowerEndpoint<>(ONE, Clusivity.INCLUSIVE), new UpperEndpoint<>(TWO, Clusivity.EXCLUSIVE)));
        assertEquals(-1, interval.compare(new LowerEndpoint<>(ONE, Clusivity.EXCLUSIVE), new UpperEndpoint<>(TWO, Clusivity.INCLUSIVE)));
        assertEquals(-1, interval.compare(new LowerEndpoint<>(ONE, Clusivity.EXCLUSIVE), new UpperEndpoint<>(TWO, Clusivity.EXCLUSIVE)));

        assertEquals(1, interval.compare(new UpperEndpoint<>(TWO, Clusivity.INCLUSIVE), new LowerEndpoint<>(ONE, Clusivity.INCLUSIVE)));
        assertEquals(1, interval.compare(new UpperEndpoint<>(TWO, Clusivity.INCLUSIVE), new LowerEndpoint<>(ONE, Clusivity.EXCLUSIVE)));
        assertEquals(1, interval.compare(new UpperEndpoint<>(TWO, Clusivity.EXCLUSIVE), new LowerEndpoint<>(ONE, Clusivity.INCLUSIVE)));
        assertEquals(1, interval.compare(new UpperEndpoint<>(TWO, Clusivity.EXCLUSIVE), new LowerEndpoint<>(ONE, Clusivity.EXCLUSIVE)));

        assertEquals(1, interval.compare(new LowerEndpoint<>(TWO, Clusivity.INCLUSIVE), new UpperEndpoint<>(ONE, Clusivity.INCLUSIVE)));
        assertEquals(1, interval.compare(new LowerEndpoint<>(TWO, Clusivity.INCLUSIVE), new UpperEndpoint<>(ONE, Clusivity.EXCLUSIVE)));
        assertEquals(1, interval.compare(new LowerEndpoint<>(TWO, Clusivity.EXCLUSIVE), new UpperEndpoint<>(ONE, Clusivity.INCLUSIVE)));
        assertEquals(1, interval.compare(new LowerEndpoint<>(TWO, Clusivity.EXCLUSIVE), new UpperEndpoint<>(ONE, Clusivity.EXCLUSIVE)));

        assertEquals(1, interval.compare(new LowerEndpoint<>(-ONE, Clusivity.INCLUSIVE), new UpperEndpoint<>(-TWO, Clusivity.INCLUSIVE)));
        assertEquals(1, interval.compare(new LowerEndpoint<>(-ONE, Clusivity.INCLUSIVE), new UpperEndpoint<>(-TWO, Clusivity.EXCLUSIVE)));
        assertEquals(1, interval.compare(new LowerEndpoint<>(-ONE, Clusivity.EXCLUSIVE), new UpperEndpoint<>(-TWO, Clusivity.INCLUSIVE)));
        assertEquals(1, interval.compare(new LowerEndpoint<>(-ONE, Clusivity.EXCLUSIVE), new UpperEndpoint<>(-TWO, Clusivity.EXCLUSIVE)));

        assertEquals(-1, interval.compare(new LowerEndpoint<>(-TWO, Clusivity.INCLUSIVE), new UpperEndpoint<>(-ONE, Clusivity.INCLUSIVE)));
        assertEquals(-1, interval.compare(new LowerEndpoint<>(-TWO, Clusivity.INCLUSIVE), new UpperEndpoint<>(-ONE, Clusivity.EXCLUSIVE)));
        assertEquals(-1, interval.compare(new LowerEndpoint<>(-TWO, Clusivity.EXCLUSIVE), new UpperEndpoint<>(-ONE, Clusivity.INCLUSIVE)));
        assertEquals(-1, interval.compare(new LowerEndpoint<>(-TWO, Clusivity.EXCLUSIVE), new UpperEndpoint<>(-ONE, Clusivity.EXCLUSIVE)));
    }

    @SuppressWarnings("EqualsWithItself")
    @Test
    @org.testng.annotations.Test
    public void testEqualBoundsInclusive()
    {
        double bound = 5.0f;
        LowerEndpoint<Double> lowerEndpoint = new LowerEndpoint<>(bound, Clusivity.INCLUSIVE);
        UpperEndpoint<Double> upperEndpoint = new UpperEndpoint<>(bound, Clusivity.INCLUSIVE);
        DoubleInterval  doubleInterval  = new DoubleInterval (lowerEndpoint, upperEndpoint);

        assertEquals(lowerEndpoint, doubleInterval .getLowerEndpoint());
        assertEquals(upperEndpoint, doubleInterval .getUpperEndpoint());
        assertEquals(ZERO, doubleInterval .compare(lowerEndpoint, upperEndpoint), 0.01f);
        assertEquals(ZERO, doubleInterval .compare(upperEndpoint, lowerEndpoint), 0.01f);
        assertFalse(doubleInterval.isEmpty());
        assertFalse(doubleInterval.isUnBounded());
        assertTrue(doubleInterval.equals(doubleInterval ));
        assertFalse(doubleInterval.isBefore(doubleInterval ));
        assertFalse(doubleInterval.isAfter(doubleInterval ));
        assertTrue(doubleInterval.intersects(doubleInterval ));
        assertEquals(doubleInterval, doubleInterval.intersect(doubleInterval ));
        assertEquals(doubleInterval, doubleInterval.union(doubleInterval ));
        List<IInterval<Double>> remainders = doubleInterval.subtract(doubleInterval);
        assertTrue(remainders == null || remainders.isEmpty());
        assertEquals(doubleInterval, doubleInterval.subInterval(lowerEndpoint, upperEndpoint));
        assertEquals(doubleInterval, doubleInterval.clone());
        assertTrue(doubleInterval.contains(bound));
        assertTrue(doubleInterval.contains(lowerEndpoint));
        assertTrue(doubleInterval.contains(upperEndpoint));
        assertTrue(doubleInterval.contains(doubleInterval ));
    }
    
    @SuppressWarnings({"EqualsWithItself", "ConstantConditions"})
    @Test
    @org.testng.annotations.Test
    public void testEqualUnbounded()
    {
        Double bound = null;
        LowerEndpoint<Double> lowerEndpoint = new LowerEndpoint<>(bound, Clusivity.EXCLUSIVE);
        UpperEndpoint<Double> upperEndpoint = new UpperEndpoint<>(bound, Clusivity.EXCLUSIVE);
        DoubleInterval  doubleInterval  = new DoubleInterval (lowerEndpoint, upperEndpoint);

        assertEquals(lowerEndpoint, doubleInterval.getLowerEndpoint());
        assertEquals(upperEndpoint, doubleInterval.getUpperEndpoint());
        assertEquals(-1, doubleInterval.compare(lowerEndpoint, upperEndpoint));
        assertEquals(1, doubleInterval.compare(upperEndpoint, lowerEndpoint));
        assertFalse(doubleInterval.isEmpty());
        assertTrue(doubleInterval.isUnBounded());
        assertTrue(doubleInterval.equals(doubleInterval ));
        assertFalse(doubleInterval.isBefore(doubleInterval ));
        assertFalse(doubleInterval.isAfter(doubleInterval ));
        assertTrue(doubleInterval.intersects(doubleInterval ));
        assertEquals(doubleInterval, doubleInterval.intersect(doubleInterval ));
        assertEquals(doubleInterval, doubleInterval.union(doubleInterval ));
        assertEquals(doubleInterval, doubleInterval.subInterval(lowerEndpoint, upperEndpoint));
        assertEquals(doubleInterval, doubleInterval.clone());
        assertTrue(doubleInterval.contains(bound));
        assertTrue(doubleInterval.contains(lowerEndpoint));
        assertTrue(doubleInterval.contains(upperEndpoint));
        assertTrue(doubleInterval.contains(doubleInterval));
        List<IInterval<Double>> subtractionIntervals = doubleInterval.subtract(doubleInterval);
        assertTrue(subtractionIntervals == null || subtractionIntervals.isEmpty());
    }
    
    
    @SuppressWarnings({"EqualsWithItself", "ConstantConditions"})
    @Test
    @org.testng.annotations.Test
    public void testPositiveEmptyInterval()
    {
        double bound = FIVE;
        LowerEndpoint<Double> lowerEndpoint = new LowerEndpoint<>(bound, Clusivity.EXCLUSIVE);
        UpperEndpoint<Double> upperEndpoint = new UpperEndpoint<>(bound, Clusivity.EXCLUSIVE);
        DoubleInterval  doubleInterval  = new DoubleInterval (lowerEndpoint, upperEndpoint);

        assertEquals(lowerEndpoint, doubleInterval.getLowerEndpoint());
        assertEquals(upperEndpoint, doubleInterval.getUpperEndpoint());
        assertEquals(1, doubleInterval.compare(lowerEndpoint, upperEndpoint));
        assertEquals(-1, doubleInterval.compare(upperEndpoint, lowerEndpoint));
        assertTrue(doubleInterval.isEmpty());
        assertFalse(doubleInterval.isUnBounded());
        assertTrue(doubleInterval.equals(doubleInterval ));
        assertTrue(doubleInterval.isBefore(doubleInterval ));
        assertTrue(doubleInterval.isAfter(doubleInterval ));
        assertFalse(doubleInterval.contains(bound));
        assertTrue(doubleInterval.contains(doubleInterval.getLowerEndpoint()));
        assertTrue(doubleInterval.contains(doubleInterval.getUpperEndpoint()));
        assertTrue(doubleInterval.contains(doubleInterval ));
        assertTrue(doubleInterval.intersects(doubleInterval ));
        assertEquals(doubleInterval, doubleInterval.intersect(doubleInterval ));
        assertEquals(doubleInterval, doubleInterval.union(doubleInterval ));
        List<IInterval<Double>> subtractionIntervals = doubleInterval.subtract(doubleInterval );
        assertFalse(subtractionIntervals.isEmpty());
        assertEquals(doubleInterval, subtractionIntervals.get(0));
        assertEquals(doubleInterval, doubleInterval.subInterval(lowerEndpoint, upperEndpoint));
        assertEquals(doubleInterval, doubleInterval.clone());
    }
    
    @SuppressWarnings({"EqualsWithItself", "ConstantConditions"})
    @Test
    @org.testng.annotations.Test
    public void testNegativeEmptyInterval()
    {
        double bound = -FIVE;
        LowerEndpoint<Double> lowerEndpoint = new LowerEndpoint<>(bound, Clusivity.EXCLUSIVE);
        UpperEndpoint<Double> upperEndpoint = new UpperEndpoint<>(bound, Clusivity.EXCLUSIVE);
        DoubleInterval  doubleInterval = new DoubleInterval (lowerEndpoint, upperEndpoint);

        assertEquals(lowerEndpoint, doubleInterval.getLowerEndpoint());
        assertEquals(upperEndpoint, doubleInterval.getUpperEndpoint());
        assertEquals(1, doubleInterval.compare(lowerEndpoint, upperEndpoint));
        assertEquals(-1, doubleInterval.compare(upperEndpoint, lowerEndpoint));
        assertTrue(doubleInterval.isEmpty());
        assertFalse(doubleInterval.isUnBounded());
        assertTrue(doubleInterval.equals(doubleInterval ));
        assertTrue(doubleInterval.isBefore(doubleInterval ));
        assertTrue(doubleInterval.isAfter(doubleInterval ));
        assertFalse(doubleInterval.contains(bound));
        assertTrue(doubleInterval.contains(doubleInterval.getLowerEndpoint()));
        assertTrue(doubleInterval.contains(doubleInterval.getUpperEndpoint()));
        assertTrue(doubleInterval.contains(doubleInterval ));
        assertTrue(doubleInterval.intersects(doubleInterval ));
        assertEquals(doubleInterval, doubleInterval.intersect(doubleInterval ));
        assertEquals(doubleInterval, doubleInterval.union(doubleInterval ));
        List<IInterval<Double>> subtractionIntervals = doubleInterval.subtract(doubleInterval );
        assertFalse(subtractionIntervals.isEmpty());
        assertEquals(doubleInterval, subtractionIntervals.get(0));
        assertEquals(doubleInterval, doubleInterval.subInterval(lowerEndpoint, upperEndpoint));
        assertEquals(doubleInterval, doubleInterval.clone());
    }
    
    @SuppressWarnings({"EqualsWithItself", "ConstantConditions"})
    @Test
    @org.testng.annotations.Test
    public void testPositiveUnEqualBoundsInclusive()
    {
        double lowerBound = TWO;
        double upperBound = THREE;
        LowerEndpoint<Double> lowerEndpoint = new LowerEndpoint<>(lowerBound, Clusivity.INCLUSIVE);
        UpperEndpoint<Double> upperEndpoint = new UpperEndpoint<>(upperBound, Clusivity.INCLUSIVE);
        DoubleInterval  doubleInterval  = new DoubleInterval (lowerEndpoint, upperEndpoint);

        assertEquals(lowerEndpoint, doubleInterval.getLowerEndpoint());
        assertEquals(upperEndpoint, doubleInterval.getUpperEndpoint());
        assertEquals(-1, doubleInterval.compare(lowerEndpoint, upperEndpoint));
        assertEquals(1, doubleInterval.compare(upperEndpoint, lowerEndpoint));
        assertFalse(doubleInterval.isEmpty());
        assertFalse(doubleInterval.isUnBounded());
        assertTrue(doubleInterval.equals(doubleInterval ));
        assertFalse(doubleInterval.isBefore(doubleInterval ));
        assertFalse(doubleInterval.isAfter(doubleInterval ));
        assertTrue(doubleInterval.intersects(doubleInterval ));
        assertEquals(doubleInterval, doubleInterval.intersect(doubleInterval ));
        assertEquals(doubleInterval, doubleInterval.union(doubleInterval ));
        List<IInterval<Double>> subtractionIntervals = doubleInterval.subtract(doubleInterval);
        assertTrue(subtractionIntervals == null || subtractionIntervals.isEmpty());
        assertEquals(doubleInterval, doubleInterval.subInterval(lowerEndpoint, upperEndpoint));
        assertEquals(doubleInterval, doubleInterval.clone());
        assertTrue(doubleInterval.contains(lowerBound));
        assertTrue(doubleInterval.contains(lowerEndpoint));
        assertTrue(doubleInterval.contains(upperBound));
        assertTrue(doubleInterval.contains(upperEndpoint));
        assertTrue(doubleInterval.contains(doubleInterval ));
    }
    
    @SuppressWarnings({"EqualsWithItself", "ConstantConditions"})
    @Test
    @org.testng.annotations.Test
    public void testNegativeUnEqualBoundsInclusive()
    {
        double lowerBound = -THREE;
        double upperBound = -TWO;
        LowerEndpoint<Double> lowerEndpoint = new LowerEndpoint<>(lowerBound, Clusivity.INCLUSIVE);
        UpperEndpoint<Double> upperEndpoint = new UpperEndpoint<>(upperBound, Clusivity.INCLUSIVE);
        DoubleInterval  doubleInterval  = new DoubleInterval (lowerEndpoint, upperEndpoint);

        assertEquals(lowerEndpoint, doubleInterval.getLowerEndpoint());
        assertEquals(upperEndpoint, doubleInterval.getUpperEndpoint());
        assertEquals(-1, doubleInterval.compare(lowerEndpoint, upperEndpoint));
        assertEquals(1, doubleInterval.compare(upperEndpoint, lowerEndpoint));
        assertFalse(doubleInterval.isEmpty());
        assertFalse(doubleInterval.isUnBounded());
        assertTrue(doubleInterval.equals(doubleInterval ));
        assertFalse(doubleInterval.isBefore(doubleInterval ));
        assertFalse(doubleInterval.isAfter(doubleInterval ));
        assertTrue(doubleInterval.intersects(doubleInterval ));
        assertEquals(doubleInterval, doubleInterval.intersect(doubleInterval ));
        assertEquals(doubleInterval, doubleInterval.union(doubleInterval ));
        List<IInterval<Double>> subtractionIntervals = doubleInterval.subtract(doubleInterval);
        assertTrue(subtractionIntervals == null || subtractionIntervals.isEmpty());
        assertEquals(doubleInterval, doubleInterval.subInterval(lowerEndpoint, upperEndpoint));
        assertEquals(doubleInterval, doubleInterval.clone());
        assertTrue(doubleInterval.contains(lowerBound));
        assertTrue(doubleInterval.contains(lowerEndpoint));
        assertTrue(doubleInterval.contains(upperBound));
        assertTrue(doubleInterval.contains(upperEndpoint));
        assertTrue(doubleInterval.contains(doubleInterval ));
    }
    
    @SuppressWarnings({"EqualsWithItself", "ConstantConditions"})
    @Test
    @org.testng.annotations.Test
    public void testPositiveUnEqualBoundsExclusive()
    {
        double lowerBound = TWO;
        double upperBound = THREE;
        LowerEndpoint<Double> lowerEndpoint = new LowerEndpoint<>(lowerBound, Clusivity.EXCLUSIVE);
        UpperEndpoint<Double> upperEndpoint = new UpperEndpoint<>(upperBound, Clusivity.EXCLUSIVE);
        DoubleInterval  doubleInterval  = new DoubleInterval (lowerEndpoint, upperEndpoint);

        assertEquals(lowerEndpoint, doubleInterval.getLowerEndpoint());
        assertEquals(upperEndpoint, doubleInterval.getUpperEndpoint());
        assertEquals(-1, doubleInterval.compare(lowerEndpoint, upperEndpoint));
        assertEquals(1, doubleInterval.compare(upperEndpoint, lowerEndpoint));
        assertFalse(doubleInterval.isEmpty());
        assertFalse(doubleInterval.isUnBounded());
        assertTrue(doubleInterval.equals(doubleInterval ));
        assertFalse(doubleInterval.isBefore(doubleInterval ));
        assertFalse(doubleInterval.isAfter(doubleInterval ));
        assertTrue(doubleInterval.intersects(doubleInterval ));
        assertEquals(doubleInterval , doubleInterval.intersect(doubleInterval ));
        assertEquals(doubleInterval , doubleInterval.union(doubleInterval ));
        List<IInterval<Double>> subtractionIntervals = doubleInterval.subtract(doubleInterval );
        assertTrue(subtractionIntervals == null || subtractionIntervals.isEmpty());
        assertEquals(doubleInterval , doubleInterval.subInterval(lowerEndpoint, upperEndpoint));
        assertEquals(doubleInterval , doubleInterval.clone());
        assertFalse(doubleInterval.contains(lowerBound));
        assertTrue(doubleInterval.contains(lowerEndpoint));
        assertFalse(doubleInterval.contains(upperBound));
        assertTrue(doubleInterval.contains(upperEndpoint));
        assertTrue(doubleInterval.contains(doubleInterval ));
    }
    
    @SuppressWarnings({"EqualsWithItself", "ConstantConditions"})
    @Test
    @org.testng.annotations.Test
    public void testNegativeUnEqualBoundsExclusive()
    {
        double lowerBound = -THREE;
        double upperBound = -TWO;
        LowerEndpoint<Double> lowerEndpoint = new LowerEndpoint<>(lowerBound, Clusivity.EXCLUSIVE);
        UpperEndpoint<Double> upperEndpoint = new UpperEndpoint<>(upperBound, Clusivity.EXCLUSIVE);
        DoubleInterval  doubleInterval  = new DoubleInterval (lowerEndpoint, upperEndpoint);

        assertEquals(lowerEndpoint, doubleInterval.getLowerEndpoint());
        assertEquals(upperEndpoint, doubleInterval.getUpperEndpoint());
        assertEquals(-1, doubleInterval.compare(lowerEndpoint, upperEndpoint));
        assertEquals(1, doubleInterval.compare(upperEndpoint, lowerEndpoint));
        assertFalse(doubleInterval.isEmpty());
        assertFalse(doubleInterval.isUnBounded());
        assertTrue(doubleInterval.equals(doubleInterval ));
        assertFalse(doubleInterval.isBefore(doubleInterval ));
        assertFalse(doubleInterval.isAfter(doubleInterval ));
        assertTrue(doubleInterval.intersects(doubleInterval ));
        assertEquals(doubleInterval , doubleInterval.intersect(doubleInterval ));
        assertEquals(doubleInterval , doubleInterval.union(doubleInterval ));
        List<IInterval<Double>> subtractionIntervals = doubleInterval.subtract(doubleInterval );
        assertTrue(subtractionIntervals == null || subtractionIntervals.isEmpty());
        assertEquals(doubleInterval , doubleInterval.subInterval(lowerEndpoint, upperEndpoint));
        assertEquals(doubleInterval , doubleInterval.clone());
        assertFalse(doubleInterval.contains(lowerBound));
        assertTrue(doubleInterval.contains(lowerEndpoint));
        assertFalse(doubleInterval.contains(upperBound));
        assertTrue(doubleInterval.contains(upperEndpoint));
        assertTrue(doubleInterval.contains(doubleInterval ));
    }
    
    @SuppressWarnings({"EqualsWithItself", "ConstantConditions"})
    @Test
    @org.testng.annotations.Test
    public void testIsBeforeValue()
    {
        Double nullFloat = null;

        DoubleInterval  interval = new DoubleInterval (new LowerEndpoint<>(EIGHT, Clusivity.INCLUSIVE), new UpperEndpoint<>(FIFTEEN, Clusivity.INCLUSIVE));

        assertNull(interval.isBefore(nullFloat));
        assertFalse(interval.isBefore(-ONE));
        assertFalse(interval.isBefore(ONE));
        assertFalse(interval.isBefore(EIGHT));
        assertFalse(interval.isBefore(FOURTEEN));
        assertFalse(interval.isBefore(FIFTEEN));
        assertTrue(interval.isBefore(SIXTEEN));

        interval = new DoubleInterval (new LowerEndpoint<>(EIGHT, Clusivity.EXCLUSIVE), new UpperEndpoint<>(FIFTEEN, Clusivity.EXCLUSIVE));

        assertNull(interval.isBefore(nullFloat));
        assertFalse(interval.isBefore(-ONE));
        assertFalse(interval.isBefore(ONE));
        assertFalse(interval.isBefore(EIGHT));
        assertFalse(interval.isBefore(FOURTEEN));
        assertTrue(interval.isBefore(FIFTEEN));
        assertTrue(interval.isBefore(SIXTEEN));

        interval = new DoubleInterval (new LowerEndpoint<>(-THREE, Clusivity.EXCLUSIVE), new UpperEndpoint<>(-ONE, Clusivity.INCLUSIVE));

        assertNull(interval.isBefore(nullFloat));
        assertFalse(interval.isBefore(-ONE));
        assertTrue(interval.isBefore(ONE));
        assertTrue(interval.isBefore(EIGHT));
        assertTrue(interval.isBefore(FOURTEEN));
        assertTrue(interval.isBefore(FIFTEEN));
        assertTrue(interval.isBefore(SIXTEEN));

        interval = new DoubleInterval (new LowerEndpoint<>(-THREE, Clusivity.EXCLUSIVE), new UpperEndpoint<>(-ONE, Clusivity.EXCLUSIVE));

        assertNull(interval.isBefore(nullFloat));
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
        LowerEndpoint<Double> lowerEndpoint = new LowerEndpoint<>(FOURTEEN, Clusivity.INCLUSIVE);
        UpperEndpoint<Double> upperEndpoint = new UpperEndpoint<>(FIFTEEN, Clusivity.INCLUSIVE);
        DoubleInterval  in14_15in = new DoubleInterval (lowerEndpoint, upperEndpoint);

        assertFalse(in14_15in.isBefore(in14_15in));

        DoubleInterval  in8_15in = new DoubleInterval (new LowerEndpoint<>(EIGHT, Clusivity.INCLUSIVE), upperEndpoint);
        assertFalse(in8_15in.isBefore(in14_15in));
        assertFalse(in14_15in.isBefore(in8_15in));

        DoubleInterval  ex8_15in = new DoubleInterval (new LowerEndpoint<>(EIGHT, Clusivity.EXCLUSIVE), upperEndpoint);
        assertFalse(ex8_15in.isBefore(in14_15in));
        assertFalse(in14_15in.isBefore(ex8_15in));

        DoubleInterval  in8_15ex = new DoubleInterval (new LowerEndpoint<>(EIGHT, Clusivity.INCLUSIVE), new UpperEndpoint<>(FIFTEEN, Clusivity.EXCLUSIVE));
        assertFalse(in14_15in.isBefore(in8_15ex));
        assertFalse(in8_15ex.isBefore(in14_15in));

        DoubleInterval  in8_14in = new DoubleInterval (new LowerEndpoint<>(EIGHT, Clusivity.INCLUSIVE), new UpperEndpoint<>(FOURTEEN, Clusivity.INCLUSIVE));
        assertFalse(in8_14in.isBefore(in14_15in));
        assertFalse(in14_15in.isBefore(in8_14in));

        DoubleInterval  in8_14ex = new DoubleInterval (new LowerEndpoint<>(EIGHT, Clusivity.INCLUSIVE), new UpperEndpoint<>(FOURTEEN, Clusivity.EXCLUSIVE));
        assertTrue(in8_14ex.isBefore(in14_15in));
        assertFalse(in14_15in.isBefore(in8_14ex));

        DoubleInterval  neg5in_neg2in = new DoubleInterval (new LowerEndpoint<>(-FIVE, Clusivity.INCLUSIVE), new UpperEndpoint<>(-TWO, Clusivity.INCLUSIVE));
        assertFalse(in14_15in.isBefore(neg5in_neg2in));
        assertTrue(neg5in_neg2in.isBefore(in14_15in));
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    @org.testng.annotations.Test
    public void testIsAfterValue()
    {
        Double nullFloat = null;

        DoubleInterval  interval = new DoubleInterval (new LowerEndpoint<>(EIGHT, Clusivity.INCLUSIVE), new UpperEndpoint<>(FIFTEEN, Clusivity.INCLUSIVE));

        assertNull(interval.isAfter(nullFloat));
        assertTrue(interval.isAfter(-ONE));
        assertTrue(interval.isAfter(ONE));
        assertFalse(interval.isAfter(EIGHT));
        assertFalse(interval.isAfter(FOURTEEN));
        assertFalse(interval.isAfter(FIFTEEN));
        assertFalse(interval.isAfter(SIXTEEN));

        interval = new DoubleInterval (new LowerEndpoint<>(EIGHT, Clusivity.EXCLUSIVE), new UpperEndpoint<>(FIFTEEN, Clusivity.EXCLUSIVE));

        assertNull(interval.isAfter(nullFloat));
        assertTrue(interval.isAfter(-ONE));
        assertTrue(interval.isAfter(ONE));
        assertTrue(interval.isAfter(EIGHT));
        assertFalse(interval.isAfter(FOURTEEN));
        assertFalse(interval.isAfter(FIFTEEN));
        assertFalse(interval.isAfter(SIXTEEN));

        interval = new DoubleInterval (new LowerEndpoint<>(-THREE, Clusivity.INCLUSIVE), new UpperEndpoint<>(-ONE, Clusivity.INCLUSIVE));

        assertNull(interval.isAfter(nullFloat));
        assertFalse(interval.isAfter(-THREE));
        assertFalse(interval.isAfter(-ONE));
        assertFalse(interval.isAfter(ONE));
        assertFalse(interval.isAfter(EIGHT));
        assertFalse(interval.isAfter(FOURTEEN));
        assertFalse(interval.isAfter(FIFTEEN));
        assertFalse(interval.isAfter(SIXTEEN));

        interval = new DoubleInterval (new LowerEndpoint<>(-THREE, Clusivity.EXCLUSIVE), new UpperEndpoint<>(-ONE, Clusivity.EXCLUSIVE));

        assertNull(interval.isAfter(nullFloat));
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
        DoubleInterval  interval = new DoubleInterval (new LowerEndpoint<>(EIGHT, Clusivity.INCLUSIVE), new UpperEndpoint<>(FIFTEEN, Clusivity.INCLUSIVE));

        assertTrue(interval.isAfter(new DoubleInterval (new LowerEndpoint<>(ONE, Clusivity.INCLUSIVE), new UpperEndpoint<>(EIGHT, Clusivity.EXCLUSIVE))));
        assertFalse(interval.isAfter(new DoubleInterval (new LowerEndpoint<>(ONE, Clusivity.INCLUSIVE), new UpperEndpoint<>(EIGHT, Clusivity.INCLUSIVE))));
        assertFalse(interval.isAfter(new DoubleInterval (new LowerEndpoint<>(EIGHT, Clusivity.INCLUSIVE), new UpperEndpoint<>(FIFTEEN, Clusivity.INCLUSIVE))));
        assertFalse(interval.isAfter(new DoubleInterval (new LowerEndpoint<>(FOURTEEN, Clusivity.INCLUSIVE), new UpperEndpoint<>(FIFTEEN, Clusivity.INCLUSIVE))));
        assertFalse(interval.isAfter(new DoubleInterval (new LowerEndpoint<>(FIFTEEN, Clusivity.INCLUSIVE), new UpperEndpoint<>(SIXTEEN, Clusivity.INCLUSIVE))));
        assertFalse(interval.isAfter(new DoubleInterval (new LowerEndpoint<>(FIFTEEN, Clusivity.EXCLUSIVE), new UpperEndpoint<>(SIXTEEN, Clusivity.INCLUSIVE))));
    }

    @Test
    @org.testng.annotations.Test
    public void testPositiveNullSubInterval()
    {
        LowerEndpoint<Double> lowerEndpoint = new LowerEndpoint<>(EIGHT, Clusivity.INCLUSIVE);
        UpperEndpoint<Double> upperEndpoint = new UpperEndpoint<>(FOURTEEN, Clusivity.INCLUSIVE);

        DoubleInterval  interval = new DoubleInterval (lowerEndpoint, upperEndpoint);
        DoubleInterval  subInterval = interval.subInterval(lowerEndpoint, upperEndpoint);
        assertNotNull(subInterval);
        assertEquals(interval, subInterval);

        lowerEndpoint = new LowerEndpoint<>(EIGHT, Clusivity.EXCLUSIVE);
        upperEndpoint = new UpperEndpoint<>(EIGHT, Clusivity.EXCLUSIVE);
        subInterval = interval.subInterval(lowerEndpoint, upperEndpoint);
        String message = interval + ".subInterval(" + lowerEndpoint + ", " + upperEndpoint + ")";
        assertEquals(new DoubleInterval (lowerEndpoint, upperEndpoint), subInterval, message);

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
        LowerEndpoint<Double> lowerEndpoint = new LowerEndpoint<>(-FOURTEEN, Clusivity.INCLUSIVE);
        UpperEndpoint<Double> upperEndpoint = new UpperEndpoint<>(-EIGHT, Clusivity.INCLUSIVE);

        DoubleInterval  interval = new DoubleInterval (lowerEndpoint, upperEndpoint);
        DoubleInterval  subInterval = interval.subInterval(lowerEndpoint, upperEndpoint);
        assertNotNull(subInterval);
        assertEquals(interval, subInterval);

        lowerEndpoint = new LowerEndpoint<>(-EIGHT, Clusivity.EXCLUSIVE);
        upperEndpoint = new UpperEndpoint<>(-EIGHT, Clusivity.EXCLUSIVE);
        subInterval = interval.subInterval(lowerEndpoint, upperEndpoint);
        String message = interval + ".subInterval(" + lowerEndpoint + ", " + upperEndpoint + ")";
        assertEquals(new DoubleInterval (lowerEndpoint, upperEndpoint), subInterval, message);

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
        LowerEndpoint<Double> lowerEndpoint = new LowerEndpoint<>(EIGHT, Clusivity.INCLUSIVE);
        UpperEndpoint<Double> upperEndpoint = new UpperEndpoint<>(FIFTEEN, Clusivity.INCLUSIVE);
        DoubleInterval  interval = new DoubleInterval (lowerEndpoint, upperEndpoint);

        UpperEndpoint<Double> newUpperEndpoint = new UpperEndpoint<>(FOURTEEN, Clusivity.INCLUSIVE);
        DoubleInterval  subInterval = interval.subInterval(lowerEndpoint, newUpperEndpoint);
        assertTrue(new DoubleInterval (lowerEndpoint, newUpperEndpoint).equals(subInterval));

        subInterval = interval.subInterval(new LowerEndpoint<>(EIGHT, Clusivity.INCLUSIVE), new UpperEndpoint<>(FIFTEEN, Clusivity.EXCLUSIVE));
        DoubleInterval  expectedSubInterval = new DoubleInterval (new LowerEndpoint<>(EIGHT, Clusivity.INCLUSIVE), new UpperEndpoint<>(FIFTEEN, Clusivity.EXCLUSIVE));
        assertEquals(expectedSubInterval, subInterval);

        subInterval = interval.subInterval(new LowerEndpoint<>(EIGHT, Clusivity.EXCLUSIVE), new UpperEndpoint<>(FIFTEEN, Clusivity.INCLUSIVE));
        expectedSubInterval = new DoubleInterval (new LowerEndpoint<>(EIGHT, Clusivity.EXCLUSIVE), new UpperEndpoint<>(FIFTEEN, Clusivity.INCLUSIVE));
        assertEquals(expectedSubInterval, subInterval);

        subInterval = interval.subInterval(new LowerEndpoint<>(EIGHT, Clusivity.EXCLUSIVE), new UpperEndpoint<>(FIFTEEN, Clusivity.EXCLUSIVE));
        expectedSubInterval = new DoubleInterval (new LowerEndpoint<>(EIGHT, Clusivity.EXCLUSIVE), new UpperEndpoint<>(FIFTEEN, Clusivity.EXCLUSIVE));
        assertEquals(expectedSubInterval, subInterval);
    }

    @Test
    @org.testng.annotations.Test
    public void testNegativeSubInterval()
    {
        LowerEndpoint<Double> lowerEndpoint = new LowerEndpoint<>(-FIFTEEN, Clusivity.INCLUSIVE);
        UpperEndpoint<Double> upperEndpoint = new UpperEndpoint<>(-EIGHT, Clusivity.INCLUSIVE);
        DoubleInterval  interval = new DoubleInterval (lowerEndpoint, upperEndpoint);

        UpperEndpoint<Double> newUpperEndpoint = new UpperEndpoint<>(-FOURTEEN, Clusivity.INCLUSIVE);
        DoubleInterval  subInterval = interval.subInterval(lowerEndpoint, newUpperEndpoint);
        assertTrue(new DoubleInterval (lowerEndpoint, newUpperEndpoint).equals(subInterval));

        subInterval = interval.subInterval(new LowerEndpoint<>(-FIFTEEN, Clusivity.INCLUSIVE), new UpperEndpoint<>(-EIGHT, Clusivity.EXCLUSIVE));
        DoubleInterval  expectedSubInterval = new DoubleInterval (new LowerEndpoint<>(-FIFTEEN, Clusivity.INCLUSIVE), new UpperEndpoint<>(-EIGHT, Clusivity.EXCLUSIVE));
        assertEquals(expectedSubInterval, subInterval);

        subInterval = interval.subInterval(new LowerEndpoint<>(-FIFTEEN, Clusivity.EXCLUSIVE), new UpperEndpoint<>(-EIGHT, Clusivity.INCLUSIVE));
        expectedSubInterval = new DoubleInterval (new LowerEndpoint<>(-FIFTEEN, Clusivity.EXCLUSIVE), new UpperEndpoint<>(-EIGHT, Clusivity.INCLUSIVE));
        assertEquals(expectedSubInterval, subInterval);

        subInterval = interval.subInterval(new LowerEndpoint<>(-FIFTEEN, Clusivity.EXCLUSIVE), new UpperEndpoint<>(-EIGHT, Clusivity.EXCLUSIVE));
        expectedSubInterval = new DoubleInterval (new LowerEndpoint<>(-FIFTEEN, Clusivity.EXCLUSIVE), new UpperEndpoint<>(-EIGHT, Clusivity.EXCLUSIVE));
        assertEquals(expectedSubInterval, subInterval);
    }

    @Test
    @org.testng.annotations.Test
    public void testOutOfBoundsSubInterval()
    {
        LowerEndpoint<Double> lowerEndpoint = new LowerEndpoint<>(EIGHT, Clusivity.EXCLUSIVE);
        UpperEndpoint<Double> upperEndpoint = new UpperEndpoint<>(FIFTEEN, Clusivity.EXCLUSIVE);
        DoubleInterval  interval = new DoubleInterval (lowerEndpoint, upperEndpoint);
        DoubleInterval  subInterval = interval.subInterval(lowerEndpoint, new UpperEndpoint<>(FIFTEEN, Clusivity.INCLUSIVE));
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
        LowerEndpoint<Double> lowerEndpoint = new LowerEndpoint<>(EIGHT, Clusivity.INCLUSIVE);
        UpperEndpoint<Double> upperEndpoint = new UpperEndpoint<>(FIFTEEN, Clusivity.INCLUSIVE);
        DoubleInterval  interval = new DoubleInterval (lowerEndpoint, upperEndpoint);

        assertFalse(interval.contains(ONE));
        assertTrue(interval.contains(EIGHT));
        assertTrue(interval.contains(FOURTEEN));
        assertTrue(interval.contains(FIFTEEN));
        assertFalse(interval.contains(SIXTEEN));

        lowerEndpoint = new LowerEndpoint<>(EIGHT, Clusivity.EXCLUSIVE);
        upperEndpoint = new UpperEndpoint<>(FIFTEEN, Clusivity.EXCLUSIVE);
        interval = new DoubleInterval (lowerEndpoint, upperEndpoint);

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
        LowerEndpoint<Double> lowerEndpoint = new LowerEndpoint<>(FOURTEEN, Clusivity.EXCLUSIVE);
        UpperEndpoint<Double> upperEndpoint = new UpperEndpoint<>(FIFTEEN, Clusivity.EXCLUSIVE);
        DoubleInterval  ex14_15ex = new DoubleInterval (lowerEndpoint, upperEndpoint);
        DoubleInterval  same_ex14_15ex = new DoubleInterval (lowerEndpoint, upperEndpoint);

        assertTrue(ex14_15ex.contains(same_ex14_15ex));
        assertTrue(same_ex14_15ex.contains(ex14_15ex));

        assertTrue(ex14_15ex.intersects(same_ex14_15ex));
        assertTrue(same_ex14_15ex.intersects(ex14_15ex));

        DoubleInterval  intersection = ex14_15ex.intersect(same_ex14_15ex);
        assertNotNull(intersection);
        assertTrue(ex14_15ex.equals(intersection));

        intersection = same_ex14_15ex.intersect(ex14_15ex);
        assertNotNull(intersection);
        assertTrue(same_ex14_15ex.equals(intersection));

        DoubleInterval  union = ex14_15ex.union(same_ex14_15ex);
        assertNotNull(union);
        assertTrue(ex14_15ex.equals(union));

        union = same_ex14_15ex.union(ex14_15ex);
        assertNotNull(union);
        assertTrue(ex14_15ex.equals(union));

        // (14.0, 15.0) - (14.0, 15.0)
        List<IInterval<Double>> remainders = ex14_15ex.subtract(same_ex14_15ex);
        String msg = "remainders " + (remainders == null ? "null" : remainders.toString());
        assertNull(remainders, msg);

        remainders = same_ex14_15ex.subtract(ex14_15ex);
        msg = "remainders " + (remainders == null ? "null" : remainders.toString());
        assertNull(remainders, msg);
    }

    @Test
    @org.testng.annotations.Test
    public void testNonEmptyEqualIntervals()
    {
        LowerEndpoint<Double> lowerEndpoint = new LowerEndpoint<>(FOURTEEN, Clusivity.INCLUSIVE);
        UpperEndpoint<Double> upperEndpoint = new UpperEndpoint<>(FIFTEEN, Clusivity.INCLUSIVE);
        DoubleInterval  ex14_15ex = new DoubleInterval (lowerEndpoint, upperEndpoint);
        DoubleInterval  same_ex14_15ex = new DoubleInterval (lowerEndpoint, upperEndpoint);

        assertTrue(ex14_15ex.contains(same_ex14_15ex));
        assertTrue(same_ex14_15ex.contains(ex14_15ex));

        assertTrue(ex14_15ex.intersects(same_ex14_15ex));
        assertTrue(same_ex14_15ex.intersects(ex14_15ex));

        DoubleInterval  intersection = ex14_15ex.intersect(same_ex14_15ex);
        assertNotNull(intersection);
        assertTrue(ex14_15ex.equals(intersection));

        intersection = same_ex14_15ex.intersect(ex14_15ex);
        assertNotNull(intersection);
        assertTrue(same_ex14_15ex.equals(intersection));

        DoubleInterval  union = ex14_15ex.union(same_ex14_15ex);
        assertNotNull(union);
        assertTrue(ex14_15ex.equals(union));

        union = same_ex14_15ex.union(ex14_15ex);
        assertNotNull(union);
        assertTrue(ex14_15ex.equals(union));

        List<IInterval<Double>> remainders = ex14_15ex.subtract(same_ex14_15ex);
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
        LowerEndpoint<Double> firstLowerEndpoint = new LowerEndpoint<>(FIFTEEN, Clusivity.EXCLUSIVE);
        UpperEndpoint<Double> firstUpperEndpoint = new UpperEndpoint<>(FIFTEEN, Clusivity.EXCLUSIVE);
        DoubleInterval  firstInterval = new DoubleInterval (firstLowerEndpoint, firstUpperEndpoint);

        LowerEndpoint<Double> secondLowerEndpoint = new LowerEndpoint<>(FIFTEEN, Clusivity.EXCLUSIVE);
        UpperEndpoint<Double> secondUpperEndpoint = new UpperEndpoint<>(FIFTEEN, Clusivity.EXCLUSIVE);
        DoubleInterval  secondInterval = new DoubleInterval (secondLowerEndpoint, secondUpperEndpoint);

        assertTrue(firstInterval.contains(secondInterval));
        assertTrue(secondInterval.contains(firstInterval));

        assertTrue(firstInterval.intersects(secondInterval));
        assertTrue(secondInterval.intersects(firstInterval));

        DoubleInterval  intersection = firstInterval.intersect(secondInterval);
        assertEquals(firstInterval, intersection);
        assertEquals(secondInterval, intersection);

        intersection = secondInterval.intersect(firstInterval);
        assertEquals(firstInterval, intersection);
        assertEquals(secondInterval, intersection);

        DoubleInterval  union = firstInterval.union(secondInterval);
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
        LowerEndpoint<Double> firstLowerEndpoint = new LowerEndpoint<>(ONE, Clusivity.EXCLUSIVE);
        UpperEndpoint<Double> firstUpperEndpoint = new UpperEndpoint<>(EIGHT, Clusivity.EXCLUSIVE);
        DoubleInterval  firstInterval = new DoubleInterval (firstLowerEndpoint, firstUpperEndpoint);

        LowerEndpoint<Double> secondLowerEndpoint = new LowerEndpoint<>(FOURTEEN, Clusivity.EXCLUSIVE);
        UpperEndpoint<Double> secondUpperEndpoint = new UpperEndpoint<>(SIXTEEN, Clusivity.EXCLUSIVE);
        DoubleInterval  secondInterval = new DoubleInterval (secondLowerEndpoint, secondUpperEndpoint);

        List<IInterval<Double>> remainders = firstInterval.subtract(secondInterval);
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
    public void testSubIntervals()
    {
        LowerEndpoint<Double> firstLowerEndpoint = new LowerEndpoint<>(EIGHT, Clusivity.INCLUSIVE);
        UpperEndpoint<Double> firstUpperEndpoint = new UpperEndpoint<>(SIXTEEN, Clusivity.INCLUSIVE);
        DoubleInterval  firstInterval = new DoubleInterval (firstLowerEndpoint, firstUpperEndpoint);

        LowerEndpoint<Double> secondLowerEndpoint = new LowerEndpoint<>(FOURTEEN, Clusivity.EXCLUSIVE);
        UpperEndpoint<Double> secondUpperEndpoint = new UpperEndpoint<>(FIFTEEN, Clusivity.EXCLUSIVE);
        DoubleInterval  emptyInterval = new DoubleInterval (secondLowerEndpoint, secondUpperEndpoint);

        assertTrue(firstInterval.contains(emptyInterval));
        assertFalse(emptyInterval.contains(firstInterval));

        assertTrue(firstInterval.intersects(emptyInterval));
        assertTrue(emptyInterval.intersects(firstInterval));

        DoubleInterval  intersection = firstInterval.intersect(emptyInterval);
        assertNotNull(intersection);
        assertEquals(emptyInterval, intersection);
        assertTrue(emptyInterval.equals(intersection));

        intersection = emptyInterval.intersect(firstInterval);
        assertNotNull(intersection);
        assertTrue(emptyInterval.equals(intersection));

        DoubleInterval  union = firstInterval.union(emptyInterval);
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
        LowerEndpoint<Double> firstLowerEndpoint = new LowerEndpoint<>(EIGHT, Clusivity.INCLUSIVE);
        UpperEndpoint<Double> firstUpperEndpoint = new UpperEndpoint<>(SIXTEEN, Clusivity.INCLUSIVE);
        DoubleInterval  firstInterval = new DoubleInterval (firstLowerEndpoint, firstUpperEndpoint);

        LowerEndpoint<Double> secondLowerEndpoint = new LowerEndpoint<>(FOURTEEN, Clusivity.EXCLUSIVE);
        UpperEndpoint<Double> secondUpperEndpoint = new UpperEndpoint<>(FIFTEEN, Clusivity.EXCLUSIVE);
        DoubleInterval  secondInterval = new DoubleInterval (secondLowerEndpoint, secondUpperEndpoint);

        List<IInterval<Double>> remainders = firstInterval.subtract(secondInterval);
        assertNotNull(remainders);
        assertEquals(2, remainders.size(), String.valueOf(remainders));
        assertEquals(new DoubleInterval (new LowerEndpoint<>(EIGHT, Clusivity.INCLUSIVE), 
                new UpperEndpoint<>(FOURTEEN, Clusivity.INCLUSIVE)), remainders.get(0), String.valueOf(remainders) + NL);
        assertEquals(new DoubleInterval (new LowerEndpoint<>(FIFTEEN, Clusivity.INCLUSIVE), 
                new UpperEndpoint<>(SIXTEEN, Clusivity.INCLUSIVE)), remainders.get(1), String.valueOf(remainders) + NL);

        remainders = secondInterval.subtract(firstInterval);
        assertTrue(remainders.isEmpty());
    }

    @Test
    @org.testng.annotations.Test
    public void testSubtractNonIntersectingIntervals()
    {
        LowerEndpoint<Double> firstLowerEndpoint = new LowerEndpoint<>(ONE, Clusivity.INCLUSIVE);
        UpperEndpoint<Double> firstUpperEndpoint = new UpperEndpoint<>(EIGHT, Clusivity.INCLUSIVE);
        DoubleInterval  firstInterval = new DoubleInterval (firstLowerEndpoint, firstUpperEndpoint);

        LowerEndpoint<Double> secondLowerEndpoint = new LowerEndpoint<>(EIGHT, Clusivity.EXCLUSIVE);
        UpperEndpoint<Double> secondUpperEndpoint = new UpperEndpoint<>(FIFTEEN, Clusivity.EXCLUSIVE);
        DoubleInterval  secondInterval = new DoubleInterval (secondLowerEndpoint, secondUpperEndpoint);

        List<IInterval<Double>> remainders = firstInterval.subtract(secondInterval);
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
    public void testSubtractIntersectingIntervals()
    {
        LowerEndpoint<Double> firstLowerEndpoint = new LowerEndpoint<>(ONE, Clusivity.INCLUSIVE);
        UpperEndpoint<Double> firstUpperEndpoint = new UpperEndpoint<>(EIGHT, Clusivity.INCLUSIVE);
        DoubleInterval  firstInterval = new DoubleInterval (firstLowerEndpoint, firstUpperEndpoint);

        LowerEndpoint<Double> secondLowerEndpoint = new LowerEndpoint<>(EIGHT, Clusivity.INCLUSIVE);
        UpperEndpoint<Double> secondUpperEndpoint = new UpperEndpoint<>(FIFTEEN, Clusivity.INCLUSIVE);
        DoubleInterval  secondInterval = new DoubleInterval (secondLowerEndpoint, secondUpperEndpoint);

        DoubleInterval  expectedInterval = new DoubleInterval (firstLowerEndpoint, new UpperEndpoint<>(EIGHT, Clusivity.EXCLUSIVE));
        List<IInterval<Double>> remainders = firstInterval.subtract(secondInterval);
        assertNotNull(remainders);
        assertEquals(1, remainders.size());
        assertEquals(expectedInterval, remainders.get(0));

        expectedInterval = new DoubleInterval (new LowerEndpoint<>(EIGHT, Clusivity.EXCLUSIVE), secondUpperEndpoint);
        remainders = secondInterval.subtract(firstInterval);
        assertNotNull(remainders);
        assertEquals(1, remainders.size());
        assertEquals(expectedInterval, remainders.get(0));
    }
    
 
    @Test
    @org.testng.annotations.Test
    public void testIntersectingIntervals()
    {
        DoubleInterval  firstInterval = new DoubleInterval (
                new LowerEndpoint<>(FIFTEEN, Clusivity.INCLUSIVE),
                new UpperEndpoint<>(SIXTEEN, Clusivity.INCLUSIVE));
        DoubleInterval  secondInterval = new DoubleInterval (
                new LowerEndpoint<>(FOURTEEN, Clusivity.INCLUSIVE),
                new UpperEndpoint<>(FIFTEEN, Clusivity.INCLUSIVE));

        assertFalse(firstInterval.contains(secondInterval));
        assertFalse(secondInterval.contains(firstInterval));

        assertTrue(firstInterval.intersects(secondInterval));
        assertTrue(secondInterval.intersects(firstInterval));

        DoubleInterval  expectedIntersection = new DoubleInterval (
                new LowerEndpoint<>(FIFTEEN, Clusivity.INCLUSIVE),
                new UpperEndpoint<>(FIFTEEN, Clusivity.INCLUSIVE));
        DoubleInterval  intersection = firstInterval.intersect(secondInterval);
        assertNotNull(intersection);
        assertEquals(expectedIntersection, intersection);

        intersection = secondInterval.intersect(firstInterval);
        assertNotNull(intersection);
        assertEquals(expectedIntersection, intersection);

        DoubleInterval  expectedUnion = new DoubleInterval (
                new LowerEndpoint<>(FOURTEEN, Clusivity.INCLUSIVE),
                new UpperEndpoint<>(SIXTEEN, Clusivity.INCLUSIVE));

        DoubleInterval  union = firstInterval.union(secondInterval);
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
        DoubleInterval  firstInterval = new DoubleInterval (
                new LowerEndpoint<>(ONE, Clusivity.INCLUSIVE),
                new UpperEndpoint<>(SIXTEEN, Clusivity.INCLUSIVE));
        DoubleInterval  secondInterval = new DoubleInterval (
                new LowerEndpoint<>(ONE, Clusivity.INCLUSIVE),
                new UpperEndpoint<>(SIXTEEN, Clusivity.INCLUSIVE));

        List<IInterval<Double>> subtractionIntervals = firstInterval.subtract(secondInterval);
        assertTrue(subtractionIntervals == null || subtractionIntervals.isEmpty());

        subtractionIntervals = secondInterval.subtract(firstInterval);
        assertTrue(subtractionIntervals == null || subtractionIntervals.isEmpty());
    }

    @Test
    @org.testng.annotations.Test
    public void testIntersectingIntervalsSubtraction()
    {
        DoubleInterval  firstInterval = new DoubleInterval (
                new LowerEndpoint<>(FIFTEEN, Clusivity.INCLUSIVE),
                new UpperEndpoint<>(SIXTEEN, Clusivity.INCLUSIVE));
        DoubleInterval  secondInterval = new DoubleInterval (
                new LowerEndpoint<>(FOURTEEN, Clusivity.INCLUSIVE),
                new UpperEndpoint<>(FIFTEEN, Clusivity.INCLUSIVE));

        List<IInterval<Double>> remainders = firstInterval.subtract(secondInterval);
        assertFalse(remainders.isEmpty());
        assertEquals(1, remainders.size());
        assertEquals(new DoubleInterval (
                new LowerEndpoint<>(FIFTEEN, Clusivity.EXCLUSIVE),
                new UpperEndpoint<>(SIXTEEN, Clusivity.INCLUSIVE)),
                remainders.get(0));

        remainders = secondInterval.subtract(firstInterval);
        assertFalse(remainders.isEmpty());
        assertEquals(1, remainders.size());
        assertEquals(new DoubleInterval (
                new LowerEndpoint<>(FOURTEEN, Clusivity.INCLUSIVE),
                new UpperEndpoint<>(FIFTEEN, Clusivity.EXCLUSIVE)),
                remainders.get(0));
    }

}