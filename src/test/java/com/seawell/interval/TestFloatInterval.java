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
public class TestFloatInterval 
{
    public static final String NL = System.lineSeparator();

    public static final float ZERO = 0f;
    public static final float ONE = 1.0f;
    public static final float TWO = 2.0f;
    public static final float THREE = 3.0f;
    public static final float FIVE = 5;
    public static final float EIGHT = 8;
    public static final float FOURTEEN = 14;
    public static final float FIFTEEN = 15;
    public static final float SIXTEEN = 16;

 
    @Test
    @org.testng.annotations.Test
    public void testConstructorWithNullLowerEndpoint()
    {
        UpperEndpoint<Float> upperEndpoint = new UpperEndpoint<>(FIFTEEN, Clusivity.INCLUSIVE);
        assertThrows(NullPointerException.class, ()->new FloatInterval(null, upperEndpoint));
    }

    @Test
    @org.testng.annotations.Test
    public void testConstructorWithNullUpperEndpoint()
    {
        LowerEndpoint<Float> lowerEndpoint = new LowerEndpoint<>(FOURTEEN, Clusivity.INCLUSIVE);
        assertThrows(NullPointerException.class, ()->new FloatInterval(lowerEndpoint, null));
    }
    
    @Test
    @org.testng.annotations.Test
    public void testLowerEndpointGreaterThanUpperEndpoint()
    {
        assertThrows(InvalidDataException.class,
                () -> new FloatInterval(new LowerEndpoint<>(-FOURTEEN, Clusivity.INCLUSIVE), new UpperEndpoint<>(-FIFTEEN, Clusivity.INCLUSIVE)));
        assertThrows(InvalidDataException.class,
                () -> new FloatInterval(new LowerEndpoint<>(FIVE, Clusivity.EXCLUSIVE), new UpperEndpoint<>(TWO, Clusivity.INCLUSIVE)));
        assertThrows(InvalidDataException.class,
                () -> new FloatInterval(new LowerEndpoint<>(ZERO, Clusivity.EXCLUSIVE), new UpperEndpoint<>(-ZERO, Clusivity.INCLUSIVE)));
        assertThrows(InvalidDataException.class,
                () -> new FloatInterval(new LowerEndpoint<>(ONE, Clusivity.EXCLUSIVE), new UpperEndpoint<>(-FOURTEEN, Clusivity.INCLUSIVE)));
    }
    
    @Test
    @org.testng.annotations.Test
    public void testConstructor()
    {
        LowerEndpoint<Float> lowerEndpoint = new LowerEndpoint<>(FOURTEEN, Clusivity.INCLUSIVE);
        UpperEndpoint<Float> upperEndpoint = new UpperEndpoint<>(FIFTEEN, Clusivity.INCLUSIVE);
        FloatInterval interval = new FloatInterval(lowerEndpoint, upperEndpoint);
        
        assertEquals(lowerEndpoint, interval.getLowerEndpoint());
        assertEquals(upperEndpoint, interval.getUpperEndpoint());
    }

    @Test
    @org.testng.annotations.Test
    public void testEqualsPositive()
    {
        LowerEndpoint<Float> lowerEndpoint = new LowerEndpoint<>(FOURTEEN, Clusivity.INCLUSIVE);
        UpperEndpoint<Float> upperEndpoint = new UpperEndpoint<>(FIFTEEN, Clusivity.INCLUSIVE);

        FloatInterval firstInterval = new FloatInterval(lowerEndpoint, upperEndpoint);
        assertTrue(firstInterval.equals(firstInterval), NL + firstInterval + NL + firstInterval + NL);

        FloatInterval secondInterval = new FloatInterval(lowerEndpoint, upperEndpoint);
        assertTrue(firstInterval.equals(secondInterval), NL + firstInterval + NL + secondInterval + NL);

        lowerEndpoint = new LowerEndpoint<>(FOURTEEN, Clusivity.EXCLUSIVE);
        upperEndpoint = new UpperEndpoint<>(FIFTEEN, Clusivity.EXCLUSIVE);

        FloatInterval thirdInterval = new FloatInterval(lowerEndpoint, upperEndpoint);
        assertTrue(thirdInterval.equals(thirdInterval), NL + thirdInterval + NL + thirdInterval + NL);

        FloatInterval fourthInterval = new FloatInterval(lowerEndpoint, upperEndpoint);
        assertTrue(thirdInterval.equals(fourthInterval), NL + thirdInterval + NL + fourthInterval + NL);

        assertFalse(firstInterval.equals(thirdInterval));
        assertFalse(secondInterval.equals(fourthInterval));
    }

    @Test
    @org.testng.annotations.Test
    public void testEqualsNegative()
    {
        LowerEndpoint<Float> lowerEndpoint = new LowerEndpoint<>(-FIFTEEN, Clusivity.INCLUSIVE);
        UpperEndpoint<Float> upperEndpoint = new UpperEndpoint<>(-FOURTEEN, Clusivity.INCLUSIVE);

        FloatInterval firstInterval = new FloatInterval(lowerEndpoint, upperEndpoint);
        assertTrue(firstInterval.equals(firstInterval), NL + firstInterval + NL + firstInterval + NL);

        FloatInterval secondInterval = new FloatInterval(lowerEndpoint, upperEndpoint);
        assertTrue(firstInterval.equals(secondInterval), NL + firstInterval + NL + secondInterval + NL);

        lowerEndpoint = new LowerEndpoint<>(-FIFTEEN, Clusivity.EXCLUSIVE);
        upperEndpoint = new UpperEndpoint<>(-FOURTEEN, Clusivity.EXCLUSIVE);

        FloatInterval thirdInterval = new FloatInterval(lowerEndpoint, upperEndpoint);
        assertTrue(thirdInterval.equals(thirdInterval), NL + thirdInterval + NL + thirdInterval + NL);

        FloatInterval fourthInterval = new FloatInterval(lowerEndpoint, upperEndpoint);
        assertTrue(thirdInterval.equals(fourthInterval), NL + thirdInterval + NL + fourthInterval + NL);

        assertFalse(firstInterval.equals(thirdInterval));
        assertFalse(secondInterval.equals(fourthInterval));
    }

    @Test
    @org.testng.annotations.Test
    public void testEqualsPositiveAndNegative()
    {
        LowerEndpoint<Float> lowerEndpoint = new LowerEndpoint<>(-FIFTEEN, Clusivity.INCLUSIVE);
        UpperEndpoint<Float> upperEndpoint = new UpperEndpoint<>(FOURTEEN, Clusivity.INCLUSIVE);

        FloatInterval firstInterval = new FloatInterval(lowerEndpoint, upperEndpoint);
        assertTrue(firstInterval.equals(firstInterval), NL + firstInterval + NL + firstInterval + NL);

        FloatInterval secondInterval = new FloatInterval(lowerEndpoint, upperEndpoint);
        assertTrue(firstInterval.equals(secondInterval), NL + firstInterval + NL + secondInterval + NL);

        lowerEndpoint = new LowerEndpoint<>(-FIFTEEN, Clusivity.EXCLUSIVE);
        upperEndpoint = new UpperEndpoint<>(FOURTEEN, Clusivity.EXCLUSIVE);

        FloatInterval thirdInterval = new FloatInterval(lowerEndpoint, upperEndpoint);
        assertTrue(thirdInterval.equals(thirdInterval), NL + thirdInterval + NL + thirdInterval + NL);

        FloatInterval fourthInterval = new FloatInterval(lowerEndpoint, upperEndpoint);
        assertTrue(thirdInterval.equals(fourthInterval), NL + thirdInterval + NL + fourthInterval + NL);

        assertFalse(firstInterval.equals(thirdInterval));
        assertFalse(secondInterval.equals(fourthInterval));
    }

    @Test
    @org.testng.annotations.Test
    public void testNotEquals()
    {
        FloatInterval firstInterval = new FloatInterval(new LowerEndpoint<>(FOURTEEN, Clusivity.INCLUSIVE), new UpperEndpoint<>(FIFTEEN, Clusivity.INCLUSIVE));
        FloatInterval secondInterval = new FloatInterval(new LowerEndpoint<>(FOURTEEN, Clusivity.EXCLUSIVE), new UpperEndpoint<>(FIFTEEN, Clusivity.EXCLUSIVE));

        assertFalse(firstInterval.equals(secondInterval));

        FloatInterval thirdInterval = new FloatInterval(new LowerEndpoint<>(-FIFTEEN, Clusivity.INCLUSIVE), new UpperEndpoint<>(-FOURTEEN, Clusivity.INCLUSIVE));
        FloatInterval fourthInterval = new FloatInterval(new LowerEndpoint<>(-FIFTEEN, Clusivity.EXCLUSIVE), new UpperEndpoint<>(-FOURTEEN, Clusivity.EXCLUSIVE));

        assertFalse(thirdInterval.equals(fourthInterval));
        assertFalse(firstInterval.equals(fourthInterval));
        assertFalse(secondInterval.equals(thirdInterval));
    }

    @Test
    @org.testng.annotations.Test
    public void testIsEmpty()
    {
        assertFalse(new FloatInterval(new LowerEndpoint<>(TWO, Clusivity.INCLUSIVE), new UpperEndpoint<>(TWO, Clusivity.INCLUSIVE)).isEmpty());
        assertTrue(new FloatInterval(new LowerEndpoint<>(TWO, Clusivity.INCLUSIVE), new UpperEndpoint<>(TWO, Clusivity.EXCLUSIVE)).isEmpty());
        assertTrue(new FloatInterval(new LowerEndpoint<>(TWO, Clusivity.EXCLUSIVE), new UpperEndpoint<>(TWO, Clusivity.INCLUSIVE)).isEmpty());
        assertTrue(new FloatInterval(new LowerEndpoint<>(TWO, Clusivity.EXCLUSIVE), new UpperEndpoint<>(TWO, Clusivity.EXCLUSIVE)).isEmpty());
        assertFalse(new FloatInterval(new LowerEndpoint<>(ZERO, Clusivity.INCLUSIVE), new UpperEndpoint<>(ZERO, Clusivity.INCLUSIVE)).isEmpty());
        assertTrue(new FloatInterval(new LowerEndpoint<>(ZERO, Clusivity.INCLUSIVE), new UpperEndpoint<>(ZERO, Clusivity.EXCLUSIVE)).isEmpty());
        assertTrue(new FloatInterval(new LowerEndpoint<>(ZERO, Clusivity.EXCLUSIVE), new UpperEndpoint<>(ZERO, Clusivity.EXCLUSIVE)).isEmpty());
        assertFalse(new FloatInterval(new LowerEndpoint<>(-TWO, Clusivity.INCLUSIVE), new UpperEndpoint<>(-TWO, Clusivity.INCLUSIVE)).isEmpty());
        assertTrue(new FloatInterval(new LowerEndpoint<>(-TWO, Clusivity.INCLUSIVE), new UpperEndpoint<>(-TWO, Clusivity.EXCLUSIVE)).isEmpty());
        assertTrue(new FloatInterval(new LowerEndpoint<>(-TWO, Clusivity.EXCLUSIVE), new UpperEndpoint<>(-TWO, Clusivity.EXCLUSIVE)).isEmpty());
        assertFalse(new FloatInterval(new LowerEndpoint<>(null, Clusivity.EXCLUSIVE), new UpperEndpoint<>(-TWO, Clusivity.EXCLUSIVE)).isEmpty());
        assertFalse(new FloatInterval(new LowerEndpoint<>(-TWO, Clusivity.EXCLUSIVE), new UpperEndpoint<>(null, Clusivity.EXCLUSIVE)).isEmpty());
        assertFalse(new FloatInterval(new LowerEndpoint<>(null, Clusivity.EXCLUSIVE), new UpperEndpoint<>(null, Clusivity.EXCLUSIVE)).isEmpty());
    }

    @Test
    @org.testng.annotations.Test
    public void testIsUnbounded()
    {
        assertFalse(new FloatInterval(new LowerEndpoint<>(ONE, Clusivity.INCLUSIVE), new UpperEndpoint<>(ONE, Clusivity.INCLUSIVE)).isUnBounded());
        assertFalse(new FloatInterval(new LowerEndpoint<>(ONE, Clusivity.INCLUSIVE), new UpperEndpoint<>(ONE, Clusivity.EXCLUSIVE)).isUnBounded());
        assertFalse(new FloatInterval(new LowerEndpoint<>(ONE, Clusivity.EXCLUSIVE), new UpperEndpoint<>(ONE, Clusivity.EXCLUSIVE)).isUnBounded());
        assertFalse(new FloatInterval(new LowerEndpoint<>(ZERO, Clusivity.INCLUSIVE), new UpperEndpoint<>(ZERO, Clusivity.INCLUSIVE)).isUnBounded());
        assertFalse(new FloatInterval(new LowerEndpoint<>(ZERO, Clusivity.INCLUSIVE), new UpperEndpoint<>(ZERO, Clusivity.EXCLUSIVE)).isUnBounded());
        assertFalse(new FloatInterval(new LowerEndpoint<>(ZERO, Clusivity.EXCLUSIVE), new UpperEndpoint<>(ZERO, Clusivity.EXCLUSIVE)).isUnBounded());
        assertFalse(new FloatInterval(new LowerEndpoint<>(-ONE, Clusivity.INCLUSIVE), new UpperEndpoint<>(-ONE, Clusivity.INCLUSIVE)).isUnBounded());
        assertFalse(new FloatInterval(new LowerEndpoint<>(-ONE, Clusivity.INCLUSIVE), new UpperEndpoint<>(-ONE, Clusivity.EXCLUSIVE)).isUnBounded());
        assertFalse(new FloatInterval(new LowerEndpoint<>(-ONE, Clusivity.EXCLUSIVE), new UpperEndpoint<>(-ONE, Clusivity.EXCLUSIVE)).isUnBounded());
        assertTrue(new FloatInterval(new LowerEndpoint<>(ONE, Clusivity.INCLUSIVE), new UpperEndpoint<>(null, Clusivity.EXCLUSIVE)).isUnBounded());
        assertTrue(new FloatInterval(new LowerEndpoint<>(null, Clusivity.EXCLUSIVE), new UpperEndpoint<>(ONE, Clusivity.INCLUSIVE)).isUnBounded());
        assertTrue(new FloatInterval(new LowerEndpoint<>(null, Clusivity.EXCLUSIVE), new UpperEndpoint<>(null, Clusivity.EXCLUSIVE)).isUnBounded());
    }

    @Test
    @org.testng.annotations.Test
    public void testCompareEndpoints()
    {
        FloatInterval interval = new FloatInterval(new LowerEndpoint<>(ONE, Clusivity.INCLUSIVE), new UpperEndpoint<>(ONE, Clusivity.INCLUSIVE));

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

    @Test
    @org.testng.annotations.Test
    public void testEqualBoundsInclusive()
    {
        float bound = 5.0f;
        LowerEndpoint<Float> lowerEndpoint = new LowerEndpoint<>(bound, Clusivity.INCLUSIVE);
        UpperEndpoint<Float> upperEndpoint = new UpperEndpoint<>(bound, Clusivity.INCLUSIVE);
        FloatInterval floatInterval = new FloatInterval(lowerEndpoint, upperEndpoint);

        assertEquals(lowerEndpoint, floatInterval.getLowerEndpoint());
        assertEquals(upperEndpoint, floatInterval.getUpperEndpoint());
        assertEquals(ZERO, floatInterval.compare(lowerEndpoint, upperEndpoint), 0.01f);
        assertEquals(ZERO, floatInterval.compare(upperEndpoint, lowerEndpoint), 0.01f);
        assertFalse(floatInterval.isEmpty());
        assertFalse(floatInterval.isUnBounded());
        assertTrue(floatInterval.equals(floatInterval));
        assertFalse(floatInterval.isBefore(floatInterval));
        assertFalse(floatInterval.isAfter(floatInterval));
        assertTrue(floatInterval.intersects(floatInterval));
        assertEquals(floatInterval, floatInterval.intersect(floatInterval));
        assertEquals(floatInterval, floatInterval.union(floatInterval));
        List<IInterval<Float>> subtractionIntervals = floatInterval.subtract(floatInterval);
        assertTrue(subtractionIntervals == null || subtractionIntervals.isEmpty());
        assertEquals(floatInterval, floatInterval.subInterval(lowerEndpoint, upperEndpoint));
        assertEquals(floatInterval, floatInterval.clone());
        assertTrue(floatInterval.contains(bound));
        assertTrue(floatInterval.contains(lowerEndpoint));
        assertTrue(floatInterval.contains(upperEndpoint));
        assertTrue(floatInterval.contains(floatInterval));
    }

    @Test
    @org.testng.annotations.Test
    public void testEqualUnbounded()
    {
        Float bound = null;
        LowerEndpoint<Float> lowerEndpoint = new LowerEndpoint<>(bound, Clusivity.EXCLUSIVE);
        UpperEndpoint<Float> upperEndpoint = new UpperEndpoint<>(bound, Clusivity.EXCLUSIVE);
        FloatInterval floatInterval = new FloatInterval(lowerEndpoint, upperEndpoint);

        assertEquals(lowerEndpoint, floatInterval.getLowerEndpoint());
        assertEquals(upperEndpoint, floatInterval.getUpperEndpoint());
        assertEquals(-1, floatInterval.compare(lowerEndpoint, upperEndpoint));
        assertEquals(1, floatInterval.compare(upperEndpoint, lowerEndpoint));
        assertFalse(floatInterval.isEmpty());
        assertTrue(floatInterval.isUnBounded());
        assertTrue(floatInterval.equals(floatInterval));
        assertFalse(floatInterval.isBefore(floatInterval));
        assertFalse(floatInterval.isAfter(floatInterval));
        assertTrue(floatInterval.intersects(floatInterval));
        assertEquals(floatInterval, floatInterval.intersect(floatInterval));
        assertEquals(floatInterval, floatInterval.union(floatInterval));
        assertEquals(floatInterval, floatInterval.subInterval(lowerEndpoint, upperEndpoint));
        assertEquals(floatInterval, floatInterval.clone());
        assertTrue(floatInterval.contains(bound));
        assertTrue(floatInterval.contains(lowerEndpoint));
        assertTrue(floatInterval.contains(upperEndpoint));
        assertTrue(floatInterval.contains(floatInterval));
        List<IInterval<Float>> subtractionIntervals = floatInterval.subtract(floatInterval);
        assertTrue(subtractionIntervals == null || subtractionIntervals.isEmpty());
    }

    @Test
    @org.testng.annotations.Test
    public void testPositiveEmptyInterval()
    {
        float bound = FIVE;
        LowerEndpoint<Float> lowerEndpoint = new LowerEndpoint<>(bound, Clusivity.EXCLUSIVE);
        UpperEndpoint<Float> upperEndpoint = new UpperEndpoint<>(bound, Clusivity.EXCLUSIVE);
        FloatInterval floatInterval = new FloatInterval(lowerEndpoint, upperEndpoint);

        assertEquals(lowerEndpoint, floatInterval.getLowerEndpoint());
        assertEquals(upperEndpoint, floatInterval.getUpperEndpoint());
        assertEquals(1, floatInterval.compare(lowerEndpoint, upperEndpoint));
        assertEquals(-1, floatInterval.compare(upperEndpoint, lowerEndpoint));
        assertTrue(floatInterval.isEmpty());
        assertFalse(floatInterval.isUnBounded());
        assertTrue(floatInterval.equals(floatInterval));
        assertTrue(floatInterval.isBefore(floatInterval));
        assertTrue(floatInterval.isAfter(floatInterval));
        assertFalse(floatInterval.contains(bound));
        assertTrue(floatInterval.contains(floatInterval.getLowerEndpoint()));
        assertTrue(floatInterval.contains(floatInterval.getUpperEndpoint()));
        assertTrue(floatInterval.contains(floatInterval));
        assertTrue(floatInterval.intersects(floatInterval));
        assertEquals(floatInterval, floatInterval.intersect(floatInterval));
        assertEquals(floatInterval, floatInterval.union(floatInterval));
        List<IInterval<Float>> subtractionIntervals = floatInterval.subtract(floatInterval);
        assertFalse(subtractionIntervals.isEmpty());
        assertEquals(floatInterval, subtractionIntervals.get(0));
        assertEquals(floatInterval, floatInterval.subInterval(lowerEndpoint, upperEndpoint));
        assertEquals(floatInterval, floatInterval.clone());
    }

    @Test
    @org.testng.annotations.Test
    public void testNegativeEmptyInterval()
    {
        float bound = -FIVE;
        LowerEndpoint<Float> lowerEndpoint = new LowerEndpoint<>(bound, Clusivity.EXCLUSIVE);
        UpperEndpoint<Float> upperEndpoint = new UpperEndpoint<>(bound, Clusivity.EXCLUSIVE);
        FloatInterval floatInterval = new FloatInterval(lowerEndpoint, upperEndpoint);

        assertEquals(lowerEndpoint, floatInterval.getLowerEndpoint());
        assertEquals(upperEndpoint, floatInterval.getUpperEndpoint());
        assertEquals(1, floatInterval.compare(lowerEndpoint, upperEndpoint));
        assertEquals(-1, floatInterval.compare(upperEndpoint, lowerEndpoint));
        assertTrue(floatInterval.isEmpty());
        assertFalse(floatInterval.isUnBounded());
        assertTrue(floatInterval.equals(floatInterval));
        assertTrue(floatInterval.isBefore(floatInterval));
        assertTrue(floatInterval.isAfter(floatInterval));
        assertFalse(floatInterval.contains(bound));
        assertTrue(floatInterval.contains(floatInterval.getLowerEndpoint()));
        assertTrue(floatInterval.contains(floatInterval.getUpperEndpoint()));
        assertTrue(floatInterval.contains(floatInterval));
        assertTrue(floatInterval.intersects(floatInterval));
        assertEquals(floatInterval, floatInterval.intersect(floatInterval));
        assertEquals(floatInterval, floatInterval.union(floatInterval));
        List<IInterval<Float>> subtractionIntervals = floatInterval.subtract(floatInterval);
        assertFalse(subtractionIntervals.isEmpty());
        assertEquals(floatInterval, subtractionIntervals.get(0));
        assertEquals(floatInterval, floatInterval.subInterval(lowerEndpoint, upperEndpoint));
        assertEquals(floatInterval, floatInterval.clone());
    }

    @Test
    @org.testng.annotations.Test
    public void testPositiveUnEqualBoundsInclusive()
    {
        float lowerBound = TWO;
        float upperBound = THREE;
        LowerEndpoint<Float> lowerEndpoint = new LowerEndpoint<>(lowerBound, Clusivity.INCLUSIVE);
        UpperEndpoint<Float> upperEndpoint = new UpperEndpoint<>(upperBound, Clusivity.INCLUSIVE);
        FloatInterval floatInterval = new FloatInterval(lowerEndpoint, upperEndpoint);

        assertEquals(lowerEndpoint, floatInterval.getLowerEndpoint());
        assertEquals(upperEndpoint, floatInterval.getUpperEndpoint());
        assertEquals(-1, floatInterval.compare(lowerEndpoint, upperEndpoint));
        assertEquals(1, floatInterval.compare(upperEndpoint, lowerEndpoint));
        assertFalse(floatInterval.isEmpty());
        assertFalse(floatInterval.isUnBounded());
        assertTrue(floatInterval.equals(floatInterval));
        assertFalse(floatInterval.isBefore(floatInterval));
        assertFalse(floatInterval.isAfter(floatInterval));
        assertTrue(floatInterval.intersects(floatInterval));
        assertEquals(floatInterval, floatInterval.intersect(floatInterval));
        assertEquals(floatInterval, floatInterval.union(floatInterval));
        List<IInterval<Float>> subtractionIntervals = floatInterval.subtract(floatInterval);
        assertTrue(subtractionIntervals == null || subtractionIntervals.isEmpty());
        assertEquals(floatInterval, floatInterval.subInterval(lowerEndpoint, upperEndpoint));
        assertEquals(floatInterval, floatInterval.clone());
        assertTrue(floatInterval.contains(lowerBound));
        assertTrue(floatInterval.contains(lowerEndpoint));
        assertTrue(floatInterval.contains(upperBound));
        assertTrue(floatInterval.contains(upperEndpoint));
        assertTrue(floatInterval.contains(floatInterval));
    }

    @Test
    @org.testng.annotations.Test
    public void testNegativeUnEqualBoundsInclusive()
    {
        float lowerBound = -THREE;
        float upperBound = -TWO;
        LowerEndpoint<Float> lowerEndpoint = new LowerEndpoint<>(lowerBound, Clusivity.INCLUSIVE);
        UpperEndpoint<Float> upperEndpoint = new UpperEndpoint<>(upperBound, Clusivity.INCLUSIVE);
        FloatInterval floatInterval = new FloatInterval(lowerEndpoint, upperEndpoint);

        assertEquals(lowerEndpoint, floatInterval.getLowerEndpoint());
        assertEquals(upperEndpoint, floatInterval.getUpperEndpoint());
        assertEquals(-1, floatInterval.compare(lowerEndpoint, upperEndpoint));
        assertEquals(1, floatInterval.compare(upperEndpoint, lowerEndpoint));
        assertFalse(floatInterval.isEmpty());
        assertFalse(floatInterval.isUnBounded());
        assertTrue(floatInterval.equals(floatInterval));
        assertFalse(floatInterval.isBefore(floatInterval));
        assertFalse(floatInterval.isAfter(floatInterval));
        assertTrue(floatInterval.intersects(floatInterval));
        assertEquals(floatInterval, floatInterval.intersect(floatInterval));
        assertEquals(floatInterval, floatInterval.union(floatInterval));
        List<IInterval<Float>> subtractionIntervals = floatInterval.subtract(floatInterval);
        assertTrue(subtractionIntervals == null || subtractionIntervals.isEmpty());
        assertEquals(floatInterval, floatInterval.subInterval(lowerEndpoint, upperEndpoint));
        assertEquals(floatInterval, floatInterval.clone());
        assertTrue(floatInterval.contains(lowerBound));
        assertTrue(floatInterval.contains(lowerEndpoint));
        assertTrue(floatInterval.contains(upperBound));
        assertTrue(floatInterval.contains(upperEndpoint));
        assertTrue(floatInterval.contains(floatInterval));
    }

    @Test
    @org.testng.annotations.Test
    public void testPositiveUnEqualBoundsExclusive()
    {
        float lowerBound = TWO;
        float upperBound = THREE;
        LowerEndpoint<Float> lowerEndpoint = new LowerEndpoint<>(lowerBound, Clusivity.EXCLUSIVE);
        UpperEndpoint<Float> upperEndpoint = new UpperEndpoint<>(upperBound, Clusivity.EXCLUSIVE);
        FloatInterval floatInterval = new FloatInterval(lowerEndpoint, upperEndpoint);

        assertEquals(lowerEndpoint, floatInterval.getLowerEndpoint());
        assertEquals(upperEndpoint, floatInterval.getUpperEndpoint());
        assertEquals(-1, floatInterval.compare(lowerEndpoint, upperEndpoint));
        assertEquals(1, floatInterval.compare(upperEndpoint, lowerEndpoint));
        assertFalse(floatInterval.isEmpty());
        assertFalse(floatInterval.isUnBounded());
        assertTrue(floatInterval.equals(floatInterval));
        assertFalse(floatInterval.isBefore(floatInterval));
        assertFalse(floatInterval.isAfter(floatInterval));
        assertTrue(floatInterval.intersects(floatInterval));
        assertEquals(floatInterval, floatInterval.intersect(floatInterval));
        assertEquals(floatInterval, floatInterval.union(floatInterval));
        List<IInterval<Float>> subtractionIntervals = floatInterval.subtract(floatInterval);
        assertTrue(subtractionIntervals == null || subtractionIntervals.isEmpty());
        assertEquals(floatInterval, floatInterval.subInterval(lowerEndpoint, upperEndpoint));
        assertEquals(floatInterval, floatInterval.clone());
        assertFalse(floatInterval.contains(lowerBound));
        assertTrue(floatInterval.contains(lowerEndpoint));
        assertFalse(floatInterval.contains(upperBound));
        assertTrue(floatInterval.contains(upperEndpoint));
        assertTrue(floatInterval.contains(floatInterval));
    }

    @Test
    @org.testng.annotations.Test
    public void testNegativeUnEqualBoundsExclusive()
    {
        float lowerBound = -THREE;
        float upperBound = -TWO;
        LowerEndpoint<Float> lowerEndpoint = new LowerEndpoint<>(lowerBound, Clusivity.EXCLUSIVE);
        UpperEndpoint<Float> upperEndpoint = new UpperEndpoint<>(upperBound, Clusivity.EXCLUSIVE);
        FloatInterval floatInterval = new FloatInterval(lowerEndpoint, upperEndpoint);

        assertEquals(lowerEndpoint, floatInterval.getLowerEndpoint());
        assertEquals(upperEndpoint, floatInterval.getUpperEndpoint());
        assertEquals(-1, floatInterval.compare(lowerEndpoint, upperEndpoint));
        assertEquals(1, floatInterval.compare(upperEndpoint, lowerEndpoint));
        assertFalse(floatInterval.isEmpty());
        assertFalse(floatInterval.isUnBounded());
        assertTrue(floatInterval.equals(floatInterval));
        assertFalse(floatInterval.isBefore(floatInterval));
        assertFalse(floatInterval.isAfter(floatInterval));
        assertTrue(floatInterval.intersects(floatInterval));
        assertEquals(floatInterval, floatInterval.intersect(floatInterval));
        assertEquals(floatInterval, floatInterval.union(floatInterval));
        List<IInterval<Float>> subtractionIntervals = floatInterval.subtract(floatInterval);
        assertTrue(subtractionIntervals == null || subtractionIntervals.isEmpty());
        assertEquals(floatInterval, floatInterval.subInterval(lowerEndpoint, upperEndpoint));
        assertEquals(floatInterval, floatInterval.clone());
        assertFalse(floatInterval.contains(lowerBound));
        assertTrue(floatInterval.contains(lowerEndpoint));
        assertFalse(floatInterval.contains(upperBound));
        assertTrue(floatInterval.contains(upperEndpoint));
        assertTrue(floatInterval.contains(floatInterval));
    }

    @Test
    @org.testng.annotations.Test
    public void testIsBeforeValue()
    {
        Float nullFloat = null;

        FloatInterval interval = new FloatInterval(new LowerEndpoint<>(EIGHT, Clusivity.INCLUSIVE), new UpperEndpoint<>(FIFTEEN, Clusivity.INCLUSIVE));

        assertNull(interval.isBefore(nullFloat));
        assertFalse(interval.isBefore(-ONE));
        assertFalse(interval.isBefore(ONE));
        assertFalse(interval.isBefore(EIGHT));
        assertFalse(interval.isBefore(FOURTEEN));
        assertFalse(interval.isBefore(FIFTEEN));
        assertTrue(interval.isBefore(SIXTEEN));

        interval = new FloatInterval(new LowerEndpoint<>(EIGHT, Clusivity.EXCLUSIVE), new UpperEndpoint<>(FIFTEEN, Clusivity.EXCLUSIVE));

        assertNull(interval.isBefore(nullFloat));
        assertFalse(interval.isBefore(-ONE));
        assertFalse(interval.isBefore(ONE));
        assertFalse(interval.isBefore(EIGHT));
        assertFalse(interval.isBefore(FOURTEEN));
        assertTrue(interval.isBefore(FIFTEEN));
        assertTrue(interval.isBefore(SIXTEEN));

        interval = new FloatInterval(new LowerEndpoint<>(-THREE, Clusivity.EXCLUSIVE), new UpperEndpoint<>(-ONE, Clusivity.INCLUSIVE));

        assertNull(interval.isBefore(nullFloat));
        assertFalse(interval.isBefore(-ONE));
        assertTrue(interval.isBefore(ONE));
        assertTrue(interval.isBefore(EIGHT));
        assertTrue(interval.isBefore(FOURTEEN));
        assertTrue(interval.isBefore(FIFTEEN));
        assertTrue(interval.isBefore(SIXTEEN));

        interval = new FloatInterval(new LowerEndpoint<>(-THREE, Clusivity.EXCLUSIVE), new UpperEndpoint<>(-ONE, Clusivity.EXCLUSIVE));

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
        LowerEndpoint<Float> lowerEndpoint = new LowerEndpoint<>(FOURTEEN, Clusivity.INCLUSIVE);
        UpperEndpoint<Float> upperEndpoint = new UpperEndpoint<>(FIFTEEN, Clusivity.INCLUSIVE);
        FloatInterval in14_15in = new FloatInterval(lowerEndpoint, upperEndpoint);

        assertFalse(in14_15in.isBefore(in14_15in));

        FloatInterval in8_15in = new FloatInterval(new LowerEndpoint<>(EIGHT, Clusivity.INCLUSIVE), upperEndpoint);
        assertFalse(in8_15in.isBefore(in14_15in));
        assertFalse(in14_15in.isBefore(in8_15in));

        FloatInterval ex8_15in = new FloatInterval(new LowerEndpoint<>(EIGHT, Clusivity.EXCLUSIVE), upperEndpoint);
        assertFalse(ex8_15in.isBefore(in14_15in));
        assertFalse(in14_15in.isBefore(ex8_15in));

        FloatInterval in8_15ex = new FloatInterval(new LowerEndpoint<>(EIGHT, Clusivity.INCLUSIVE), new UpperEndpoint<>(FIFTEEN, Clusivity.EXCLUSIVE));
        assertFalse(in14_15in.isBefore(in8_15ex));
        assertFalse(in8_15ex.isBefore(in14_15in));

        FloatInterval in8_14in = new FloatInterval(new LowerEndpoint<>(EIGHT, Clusivity.INCLUSIVE), new UpperEndpoint<>(FOURTEEN, Clusivity.INCLUSIVE));
        assertFalse(in8_14in.isBefore(in14_15in));
        assertFalse(in14_15in.isBefore(in8_14in));

        FloatInterval in8_14ex = new FloatInterval(new LowerEndpoint<>(EIGHT, Clusivity.INCLUSIVE), new UpperEndpoint<>(FOURTEEN, Clusivity.EXCLUSIVE));
        assertTrue(in8_14ex.isBefore(in14_15in));
        assertFalse(in14_15in.isBefore(in8_14ex));

        FloatInterval neg5in_neg2in = new FloatInterval(new LowerEndpoint<>(-FIVE, Clusivity.INCLUSIVE), new UpperEndpoint<>(-TWO, Clusivity.INCLUSIVE));
        assertFalse(in14_15in.isBefore(neg5in_neg2in));
        assertTrue(neg5in_neg2in.isBefore(in14_15in));
    }

    @Test
    @org.testng.annotations.Test
    public void testIsAfterValue()
    {
        Float nullFloat = null;

        FloatInterval interval = new FloatInterval(new LowerEndpoint<>(EIGHT, Clusivity.INCLUSIVE), new UpperEndpoint<>(FIFTEEN, Clusivity.INCLUSIVE));

        assertNull(interval.isAfter(nullFloat));
        assertTrue(interval.isAfter(-ONE));
        assertTrue(interval.isAfter(ONE));
        assertFalse(interval.isAfter(EIGHT));
        assertFalse(interval.isAfter(FOURTEEN));
        assertFalse(interval.isAfter(FIFTEEN));
        assertFalse(interval.isAfter(SIXTEEN));

        interval = new FloatInterval(new LowerEndpoint<>(EIGHT, Clusivity.EXCLUSIVE), new UpperEndpoint<>(FIFTEEN, Clusivity.EXCLUSIVE));

        assertNull(interval.isAfter(nullFloat));
        assertTrue(interval.isAfter(-ONE));
        assertTrue(interval.isAfter(ONE));
        assertTrue(interval.isAfter(EIGHT));
        assertFalse(interval.isAfter(FOURTEEN));
        assertFalse(interval.isAfter(FIFTEEN));
        assertFalse(interval.isAfter(SIXTEEN));

        interval = new FloatInterval(new LowerEndpoint<>(-THREE, Clusivity.INCLUSIVE), new UpperEndpoint<>(-ONE, Clusivity.INCLUSIVE));

        assertNull(interval.isAfter(nullFloat));
        assertFalse(interval.isAfter(-THREE));
        assertFalse(interval.isAfter(-ONE));
        assertFalse(interval.isAfter(ONE));
        assertFalse(interval.isAfter(EIGHT));
        assertFalse(interval.isAfter(FOURTEEN));
        assertFalse(interval.isAfter(FIFTEEN));
        assertFalse(interval.isAfter(SIXTEEN));

        interval = new FloatInterval(new LowerEndpoint<>(-THREE, Clusivity.EXCLUSIVE), new UpperEndpoint<>(-ONE, Clusivity.EXCLUSIVE));

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
        FloatInterval interval = new FloatInterval(new LowerEndpoint<>(EIGHT, Clusivity.INCLUSIVE), new UpperEndpoint<>(FIFTEEN, Clusivity.INCLUSIVE));

        assertTrue(interval.isAfter(new FloatInterval(new LowerEndpoint<>(ONE, Clusivity.INCLUSIVE), new UpperEndpoint<>(EIGHT, Clusivity.EXCLUSIVE))));
        assertFalse(interval.isAfter(new FloatInterval(new LowerEndpoint<>(ONE, Clusivity.INCLUSIVE), new UpperEndpoint<>(EIGHT, Clusivity.INCLUSIVE))));
        assertFalse(interval.isAfter(new FloatInterval(new LowerEndpoint<>(EIGHT, Clusivity.INCLUSIVE), new UpperEndpoint<>(FIFTEEN, Clusivity.INCLUSIVE))));
        assertFalse(interval.isAfter(new FloatInterval(new LowerEndpoint<>(FOURTEEN, Clusivity.INCLUSIVE), new UpperEndpoint<>(FIFTEEN, Clusivity.INCLUSIVE))));
        assertFalse(interval.isAfter(new FloatInterval(new LowerEndpoint<>(FIFTEEN, Clusivity.INCLUSIVE), new UpperEndpoint<>(SIXTEEN, Clusivity.INCLUSIVE))));
        assertFalse(interval.isAfter(new FloatInterval(new LowerEndpoint<>(FIFTEEN, Clusivity.EXCLUSIVE), new UpperEndpoint<>(SIXTEEN, Clusivity.INCLUSIVE))));
    }

    @Test
    @org.testng.annotations.Test
    public void testPositiveNullSubInterval()
    {
        LowerEndpoint<Float> lowerEndpoint = new LowerEndpoint<>(EIGHT, Clusivity.INCLUSIVE);
        UpperEndpoint<Float> upperEndpoint = new UpperEndpoint<>(FOURTEEN, Clusivity.INCLUSIVE);

        FloatInterval interval = new FloatInterval(lowerEndpoint, upperEndpoint);
        FloatInterval subInterval = interval.subInterval(lowerEndpoint, upperEndpoint);
        assertNotNull(subInterval);
        assertEquals(interval, subInterval);

        lowerEndpoint = new LowerEndpoint<>(EIGHT, Clusivity.EXCLUSIVE);
        upperEndpoint = new UpperEndpoint<>(EIGHT, Clusivity.EXCLUSIVE);
        subInterval = interval.subInterval(lowerEndpoint, upperEndpoint);
        String message = interval + ".subInterval(" + lowerEndpoint + ", " + upperEndpoint + ")";
        assertEquals(new FloatInterval(lowerEndpoint, upperEndpoint), subInterval, message);

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
        LowerEndpoint<Float> lowerEndpoint = new LowerEndpoint<>(-FOURTEEN, Clusivity.INCLUSIVE);
        UpperEndpoint<Float> upperEndpoint = new UpperEndpoint<>(-EIGHT, Clusivity.INCLUSIVE);

        FloatInterval interval = new FloatInterval(lowerEndpoint, upperEndpoint);
        FloatInterval subInterval = interval.subInterval(lowerEndpoint, upperEndpoint);
        assertNotNull(subInterval);
        assertEquals(interval, subInterval);

        lowerEndpoint = new LowerEndpoint<>(-EIGHT, Clusivity.EXCLUSIVE);
        upperEndpoint = new UpperEndpoint<>(-EIGHT, Clusivity.EXCLUSIVE);
        subInterval = interval.subInterval(lowerEndpoint, upperEndpoint);
        String message = interval + ".subInterval(" + lowerEndpoint + ", " + upperEndpoint + ")";
        assertEquals(new FloatInterval(lowerEndpoint, upperEndpoint), subInterval, message);

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
        LowerEndpoint<Float> lowerEndpoint = new LowerEndpoint<>(EIGHT, Clusivity.INCLUSIVE);
        UpperEndpoint<Float> upperEndpoint = new UpperEndpoint<>(FIFTEEN, Clusivity.INCLUSIVE);
        FloatInterval interval = new FloatInterval(lowerEndpoint, upperEndpoint);

        UpperEndpoint<Float> newUpperEndpoint = new UpperEndpoint<>(FOURTEEN, Clusivity.INCLUSIVE);
        FloatInterval subInterval = interval.subInterval(lowerEndpoint, newUpperEndpoint);
        assertTrue(new FloatInterval(lowerEndpoint, newUpperEndpoint).equals(subInterval));

        subInterval = interval.subInterval(new LowerEndpoint<>(EIGHT, Clusivity.INCLUSIVE), new UpperEndpoint<>(FIFTEEN, Clusivity.EXCLUSIVE));
        FloatInterval expectedSubInterval = new FloatInterval(new LowerEndpoint<>(EIGHT, Clusivity.INCLUSIVE), new UpperEndpoint<>(FIFTEEN, Clusivity.EXCLUSIVE));
        assertEquals(expectedSubInterval, subInterval);

        subInterval = interval.subInterval(new LowerEndpoint<>(EIGHT, Clusivity.EXCLUSIVE), new UpperEndpoint<>(FIFTEEN, Clusivity.INCLUSIVE));
        expectedSubInterval = new FloatInterval(new LowerEndpoint<>(EIGHT, Clusivity.EXCLUSIVE), new UpperEndpoint<>(FIFTEEN, Clusivity.INCLUSIVE));
        assertEquals(expectedSubInterval, subInterval);

        subInterval = interval.subInterval(new LowerEndpoint<>(EIGHT, Clusivity.EXCLUSIVE), new UpperEndpoint<>(FIFTEEN, Clusivity.EXCLUSIVE));
        expectedSubInterval = new FloatInterval(new LowerEndpoint<>(EIGHT, Clusivity.EXCLUSIVE), new UpperEndpoint<>(FIFTEEN, Clusivity.EXCLUSIVE));
        assertEquals(expectedSubInterval, subInterval);
    }

    @Test
    @org.testng.annotations.Test
    public void testNegativeSubInterval()
    {
        LowerEndpoint<Float> lowerEndpoint = new LowerEndpoint<>(-FIFTEEN, Clusivity.INCLUSIVE);
        UpperEndpoint<Float> upperEndpoint = new UpperEndpoint<>(-EIGHT, Clusivity.INCLUSIVE);
        FloatInterval interval = new FloatInterval(lowerEndpoint, upperEndpoint);

        UpperEndpoint<Float> newUpperEndpoint = new UpperEndpoint<>(-FOURTEEN, Clusivity.INCLUSIVE);
        FloatInterval subInterval = interval.subInterval(lowerEndpoint, newUpperEndpoint);
        assertTrue(new FloatInterval(lowerEndpoint, newUpperEndpoint).equals(subInterval));

        subInterval = interval.subInterval(new LowerEndpoint<>(-FIFTEEN, Clusivity.INCLUSIVE), new UpperEndpoint<>(-EIGHT, Clusivity.EXCLUSIVE));
        FloatInterval expectedSubInterval = new FloatInterval(new LowerEndpoint<>(-FIFTEEN, Clusivity.INCLUSIVE), new UpperEndpoint<>(-EIGHT, Clusivity.EXCLUSIVE));
        assertEquals(expectedSubInterval, subInterval);

        subInterval = interval.subInterval(new LowerEndpoint<>(-FIFTEEN, Clusivity.EXCLUSIVE), new UpperEndpoint<>(-EIGHT, Clusivity.INCLUSIVE));
        expectedSubInterval = new FloatInterval(new LowerEndpoint<>(-FIFTEEN, Clusivity.EXCLUSIVE), new UpperEndpoint<>(-EIGHT, Clusivity.INCLUSIVE));
        assertEquals(expectedSubInterval, subInterval);

        subInterval = interval.subInterval(new LowerEndpoint<>(-FIFTEEN, Clusivity.EXCLUSIVE), new UpperEndpoint<>(-EIGHT, Clusivity.EXCLUSIVE));
        expectedSubInterval = new FloatInterval(new LowerEndpoint<>(-FIFTEEN, Clusivity.EXCLUSIVE), new UpperEndpoint<>(-EIGHT, Clusivity.EXCLUSIVE));
        assertEquals(expectedSubInterval, subInterval);
    }

    @Test
    @org.testng.annotations.Test
    public void testOutOfBoundsSubInterval()
    {
        LowerEndpoint<Float> lowerEndpoint = new LowerEndpoint<>(EIGHT, Clusivity.EXCLUSIVE);
        UpperEndpoint<Float> upperEndpoint = new UpperEndpoint<>(FIFTEEN, Clusivity.EXCLUSIVE);
        FloatInterval interval = new FloatInterval(lowerEndpoint, upperEndpoint);
        FloatInterval subInterval = interval.subInterval(lowerEndpoint, new UpperEndpoint<>(FIFTEEN, Clusivity.INCLUSIVE));
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
        LowerEndpoint<Float> lowerEndpoint = new LowerEndpoint<>(EIGHT, Clusivity.INCLUSIVE);
        UpperEndpoint<Float> upperEndpoint = new UpperEndpoint<>(FIFTEEN, Clusivity.INCLUSIVE);
        FloatInterval interval = new FloatInterval(lowerEndpoint, upperEndpoint);

        assertFalse(interval.contains(ONE));
        assertTrue(interval.contains(EIGHT));
        assertTrue(interval.contains(FOURTEEN));
        assertTrue(interval.contains(FIFTEEN));
        assertFalse(interval.contains(SIXTEEN));

        lowerEndpoint = new LowerEndpoint<>(EIGHT, Clusivity.EXCLUSIVE);
        upperEndpoint = new UpperEndpoint<>(FIFTEEN, Clusivity.EXCLUSIVE);
        interval = new FloatInterval(lowerEndpoint, upperEndpoint);

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
        LowerEndpoint<Float> lowerEndpoint = new LowerEndpoint<>(FOURTEEN, Clusivity.EXCLUSIVE);
        UpperEndpoint<Float> upperEndpoint = new UpperEndpoint<>(FIFTEEN, Clusivity.EXCLUSIVE);
        FloatInterval ex14_15ex = new FloatInterval(lowerEndpoint, upperEndpoint);
        FloatInterval same_ex14_15ex = new FloatInterval(lowerEndpoint, upperEndpoint);

        assertTrue(ex14_15ex.contains(same_ex14_15ex));
        assertTrue(same_ex14_15ex.contains(ex14_15ex));

        assertTrue(ex14_15ex.intersects(same_ex14_15ex));
        assertTrue(same_ex14_15ex.intersects(ex14_15ex));

        FloatInterval intersection = ex14_15ex.intersect(same_ex14_15ex);
        assertNotNull(intersection);
        assertTrue(ex14_15ex.equals(intersection));

        intersection = same_ex14_15ex.intersect(ex14_15ex);
        assertNotNull(intersection);
        assertTrue(same_ex14_15ex.equals(intersection));

        FloatInterval union = ex14_15ex.union(same_ex14_15ex);
        assertNotNull(union);
        assertTrue(ex14_15ex.equals(union));

        union = same_ex14_15ex.union(ex14_15ex);
        assertNotNull(union);
        assertTrue(ex14_15ex.equals(union));

        // (14.0, 15.0) - (14.0, 15.0)
        List<IInterval<Float>> remainders = ex14_15ex.subtract(same_ex14_15ex);
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
        LowerEndpoint<Float> lowerEndpoint = new LowerEndpoint<>(FOURTEEN, Clusivity.INCLUSIVE);
        UpperEndpoint<Float> upperEndpoint = new UpperEndpoint<>(FIFTEEN, Clusivity.INCLUSIVE);
        FloatInterval ex14_15ex = new FloatInterval(lowerEndpoint, upperEndpoint);
        FloatInterval same_ex14_15ex = new FloatInterval(lowerEndpoint, upperEndpoint);

        assertTrue(ex14_15ex.contains(same_ex14_15ex));
        assertTrue(same_ex14_15ex.contains(ex14_15ex));

        assertTrue(ex14_15ex.intersects(same_ex14_15ex));
        assertTrue(same_ex14_15ex.intersects(ex14_15ex));

        FloatInterval intersection = ex14_15ex.intersect(same_ex14_15ex);
        assertNotNull(intersection);
        assertTrue(ex14_15ex.equals(intersection));

        intersection = same_ex14_15ex.intersect(ex14_15ex);
        assertNotNull(intersection);
        assertTrue(same_ex14_15ex.equals(intersection));

        FloatInterval union = ex14_15ex.union(same_ex14_15ex);
        assertNotNull(union);
        assertTrue(ex14_15ex.equals(union));

        union = same_ex14_15ex.union(ex14_15ex);
        assertNotNull(union);
        assertTrue(ex14_15ex.equals(union));

        List<IInterval<Float>> remainders = ex14_15ex.subtract(same_ex14_15ex);
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
        LowerEndpoint<Float> firstLowerEndpoint = new LowerEndpoint<>(FIFTEEN, Clusivity.EXCLUSIVE);
        UpperEndpoint<Float> firstUpperEndpoint = new UpperEndpoint<>(FIFTEEN, Clusivity.EXCLUSIVE);
        FloatInterval firstInterval = new FloatInterval(firstLowerEndpoint, firstUpperEndpoint);

        LowerEndpoint<Float> secondLowerEndpoint = new LowerEndpoint<>(FIFTEEN, Clusivity.EXCLUSIVE);
        UpperEndpoint<Float> secondUpperEndpoint = new UpperEndpoint<>(FIFTEEN, Clusivity.EXCLUSIVE);
        FloatInterval secondInterval = new FloatInterval(secondLowerEndpoint, secondUpperEndpoint);

        assertTrue(firstInterval.contains(secondInterval));
        assertTrue(secondInterval.contains(firstInterval));

        assertTrue(firstInterval.intersects(secondInterval));
        assertTrue(secondInterval.intersects(firstInterval));

        FloatInterval intersection = firstInterval.intersect(secondInterval);
        assertEquals(firstInterval, intersection);
        assertEquals(secondInterval, intersection);

        intersection = secondInterval.intersect(firstInterval);
        assertEquals(firstInterval, intersection);
        assertEquals(secondInterval, intersection);

        FloatInterval union = firstInterval.union(secondInterval);
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
        LowerEndpoint<Float> firstLowerEndpoint = new LowerEndpoint<>(ONE, Clusivity.EXCLUSIVE);
        UpperEndpoint<Float> firstUpperEndpoint = new UpperEndpoint<>(EIGHT, Clusivity.EXCLUSIVE);
        FloatInterval firstInterval = new FloatInterval(firstLowerEndpoint, firstUpperEndpoint);

        LowerEndpoint<Float> secondLowerEndpoint = new LowerEndpoint<>(FOURTEEN, Clusivity.EXCLUSIVE);
        UpperEndpoint<Float> secondUpperEndpoint = new UpperEndpoint<>(SIXTEEN, Clusivity.EXCLUSIVE);
        FloatInterval secondInterval = new FloatInterval(secondLowerEndpoint, secondUpperEndpoint);

        List<IInterval<Float>> remainders = firstInterval.subtract(secondInterval);
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
        LowerEndpoint<Float> firstLowerEndpoint = new LowerEndpoint<>(EIGHT, Clusivity.INCLUSIVE);
        UpperEndpoint<Float> firstUpperEndpoint = new UpperEndpoint<>(SIXTEEN, Clusivity.INCLUSIVE);
        FloatInterval firstInterval = new FloatInterval(firstLowerEndpoint, firstUpperEndpoint);

        LowerEndpoint<Float> secondLowerEndpoint = new LowerEndpoint<>(FOURTEEN, Clusivity.EXCLUSIVE);
        UpperEndpoint<Float> secondUpperEndpoint = new UpperEndpoint<>(FIFTEEN, Clusivity.EXCLUSIVE);
        FloatInterval emptyInterval = new FloatInterval(secondLowerEndpoint, secondUpperEndpoint);

        assertTrue(firstInterval.contains(emptyInterval));
        assertFalse(emptyInterval.contains(firstInterval));

        assertTrue(firstInterval.intersects(emptyInterval));
        assertTrue(emptyInterval.intersects(firstInterval));

        FloatInterval intersection = firstInterval.intersect(emptyInterval);
        assertNotNull(intersection);
        assertEquals(emptyInterval, intersection);
        assertTrue(emptyInterval.equals(intersection));

        intersection = emptyInterval.intersect(firstInterval);
        assertNotNull(intersection);
        assertTrue(emptyInterval.equals(intersection));

        FloatInterval union = firstInterval.union(emptyInterval);
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
        LowerEndpoint<Float> firstLowerEndpoint = new LowerEndpoint<>(EIGHT, Clusivity.INCLUSIVE);
        UpperEndpoint<Float> firstUpperEndpoint = new UpperEndpoint<>(SIXTEEN, Clusivity.INCLUSIVE);
        FloatInterval firstInterval = new FloatInterval(firstLowerEndpoint, firstUpperEndpoint);

        LowerEndpoint<Float> secondLowerEndpoint = new LowerEndpoint<>(FOURTEEN, Clusivity.EXCLUSIVE);
        UpperEndpoint<Float> secondUpperEndpoint = new UpperEndpoint<>(FIFTEEN, Clusivity.EXCLUSIVE);
        FloatInterval secondInterval = new FloatInterval(secondLowerEndpoint, secondUpperEndpoint);

        List<IInterval<Float>> remainders = firstInterval.subtract(secondInterval);
        assertNotNull(remainders);
        String message = String.valueOf(remainders) + NL;
        assertEquals(2, remainders.size(), message);
        FloatInterval expected = new FloatInterval(new LowerEndpoint<>(EIGHT, Clusivity.INCLUSIVE), new UpperEndpoint<>(FOURTEEN, Clusivity.INCLUSIVE));
        assertEquals(expected, remainders.get(0), message);
        FloatInterval expectedInterval = new FloatInterval(new LowerEndpoint<>(FIFTEEN, Clusivity.INCLUSIVE), new UpperEndpoint<>(SIXTEEN, Clusivity.INCLUSIVE));
        assertEquals(expectedInterval, remainders.get(1), message);

        remainders = secondInterval.subtract(firstInterval);
        assertTrue(remainders.isEmpty());
    }

    @Test
    @org.testng.annotations.Test
    public void testSubtractNonIntersectingIntervals()
    {
        LowerEndpoint<Float> firstLowerEndpoint = new LowerEndpoint<>(ONE, Clusivity.INCLUSIVE);
        UpperEndpoint<Float> firstUpperEndpoint = new UpperEndpoint<>(EIGHT, Clusivity.INCLUSIVE);
        FloatInterval firstInterval = new FloatInterval(firstLowerEndpoint, firstUpperEndpoint);

        LowerEndpoint<Float> secondLowerEndpoint = new LowerEndpoint<>(EIGHT, Clusivity.EXCLUSIVE);
        UpperEndpoint<Float> secondUpperEndpoint = new UpperEndpoint<>(FIFTEEN, Clusivity.EXCLUSIVE);
        FloatInterval secondInterval = new FloatInterval(secondLowerEndpoint, secondUpperEndpoint);

        List<IInterval<Float>> remainders = firstInterval.subtract(secondInterval);
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
        LowerEndpoint<Float> firstLowerEndpoint = new LowerEndpoint<>(ONE, Clusivity.INCLUSIVE);
        UpperEndpoint<Float> firstUpperEndpoint = new UpperEndpoint<>(EIGHT, Clusivity.INCLUSIVE);
        FloatInterval firstInterval = new FloatInterval(firstLowerEndpoint, firstUpperEndpoint);

        LowerEndpoint<Float> secondLowerEndpoint = new LowerEndpoint<>(EIGHT, Clusivity.INCLUSIVE);
        UpperEndpoint<Float> secondUpperEndpoint = new UpperEndpoint<>(FIFTEEN, Clusivity.INCLUSIVE);
        FloatInterval secondInterval = new FloatInterval(secondLowerEndpoint, secondUpperEndpoint);

        FloatInterval expectedInterval = new FloatInterval(firstLowerEndpoint, new UpperEndpoint<>(EIGHT, Clusivity.EXCLUSIVE));
        List<IInterval<Float>> remainders = firstInterval.subtract(secondInterval);
        assertNotNull(remainders);
        assertEquals(1, remainders.size());
        assertEquals(expectedInterval, remainders.get(0));

        expectedInterval = new FloatInterval(new LowerEndpoint<>(EIGHT, Clusivity.EXCLUSIVE), secondUpperEndpoint);
        remainders = secondInterval.subtract(firstInterval);
        assertNotNull(remainders);
        assertEquals(1, remainders.size());
        assertEquals(expectedInterval, remainders.get(0));
    }

    @Test
    @org.testng.annotations.Test
    public void testIntersectingIntervals()
    {
        FloatInterval firstInterval = new FloatInterval(
                new LowerEndpoint<>(FIFTEEN, Clusivity.INCLUSIVE),
                new UpperEndpoint<>(SIXTEEN, Clusivity.INCLUSIVE));
        FloatInterval secondInterval = new FloatInterval(
                new LowerEndpoint<>(FOURTEEN, Clusivity.INCLUSIVE),
                new UpperEndpoint<>(FIFTEEN, Clusivity.INCLUSIVE));

        assertFalse(firstInterval.contains(secondInterval));
        assertFalse(secondInterval.contains(firstInterval));

        assertTrue(firstInterval.intersects(secondInterval));
        assertTrue(secondInterval.intersects(firstInterval));

        FloatInterval expectedIntersection = new FloatInterval(
                new LowerEndpoint<>(FIFTEEN, Clusivity.INCLUSIVE),
                new UpperEndpoint<>(FIFTEEN, Clusivity.INCLUSIVE));
        FloatInterval intersection = firstInterval.intersect(secondInterval);
        assertNotNull(intersection);
        assertEquals(expectedIntersection, intersection);

        intersection = secondInterval.intersect(firstInterval);
        assertNotNull(intersection);
        assertEquals(expectedIntersection, intersection);

        FloatInterval expectedUnion = new FloatInterval(
                new LowerEndpoint<>(FOURTEEN, Clusivity.INCLUSIVE),
                new UpperEndpoint<>(SIXTEEN, Clusivity.INCLUSIVE));

        FloatInterval union = firstInterval.union(secondInterval);
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
        FloatInterval firstInterval = new FloatInterval(
                new LowerEndpoint<>(ONE, Clusivity.INCLUSIVE),
                new UpperEndpoint<>(SIXTEEN, Clusivity.INCLUSIVE));
        FloatInterval secondInterval = new FloatInterval(
                new LowerEndpoint<>(ONE, Clusivity.INCLUSIVE),
                new UpperEndpoint<>(SIXTEEN, Clusivity.INCLUSIVE));

        List<IInterval<Float>> subtractionIntervals = firstInterval.subtract(secondInterval);
        assertTrue(subtractionIntervals == null || subtractionIntervals.isEmpty());

        subtractionIntervals = secondInterval.subtract(firstInterval);
        assertTrue(subtractionIntervals == null || subtractionIntervals.isEmpty());
    }

    @Test
    @org.testng.annotations.Test
    public void testIntersectingIntervalsSubtraction()
    {
        FloatInterval firstInterval = new FloatInterval(
                new LowerEndpoint<>(FIFTEEN, Clusivity.INCLUSIVE),
                new UpperEndpoint<>(SIXTEEN, Clusivity.INCLUSIVE));
        FloatInterval secondInterval = new FloatInterval(
                new LowerEndpoint<>(FOURTEEN, Clusivity.INCLUSIVE),
                new UpperEndpoint<>(FIFTEEN, Clusivity.INCLUSIVE));

        List<IInterval<Float>> remainders = firstInterval.subtract(secondInterval);
        assertFalse(remainders.isEmpty());
        assertEquals(1, remainders.size());
        assertEquals(new FloatInterval(
                new LowerEndpoint<>(FIFTEEN, Clusivity.EXCLUSIVE),
                new UpperEndpoint<>(SIXTEEN, Clusivity.INCLUSIVE)),
                remainders.get(0));

        remainders = secondInterval.subtract(firstInterval);
        assertFalse(remainders.isEmpty());
        assertEquals(1, remainders.size());
        assertEquals(new FloatInterval(
                new LowerEndpoint<>(FOURTEEN, Clusivity.INCLUSIVE),
                new UpperEndpoint<>(FIFTEEN, Clusivity.EXCLUSIVE)),
                remainders.get(0));
    }

}