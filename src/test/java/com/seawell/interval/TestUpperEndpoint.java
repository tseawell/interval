package com.seawell.interval;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 User: tseawell
 Date: 3/10/15
 Time: 12:12 PM
 */
@SuppressWarnings({"ConstantConditions", "EqualsWithItself"})
public class TestUpperEndpoint
{
    //**************************************************************************
    // TEST METHODS
    //**************************************************************************

    @Test
    @org.testng.annotations.Test
    public void testConstructor()
    {
        int bound = 1;
        Clusivity clusivity = Clusivity.EXCLUSIVE;
        UpperEndpoint<Integer> upperEndpoint = new UpperEndpoint<>(bound, clusivity);
        assertNotNull(upperEndpoint);
        assertEquals(bound, upperEndpoint.getBound().intValue());
        assertEquals(clusivity, upperEndpoint.getClusivity());
        assertEquals("1)", upperEndpoint.toString());
    }

    @Test
    @org.testng.annotations.Test
    public void testConstructorNullBound()
    {
        Integer bound = null;
        Clusivity clusivity = Clusivity.EXCLUSIVE;
        UpperEndpoint<Integer> upperEndpoint = new UpperEndpoint<>(bound, clusivity);
        assertNotNull(upperEndpoint);
        assertNull(upperEndpoint.getBound());
        assertEquals(clusivity, upperEndpoint.getClusivity());
        assertEquals("~)", upperEndpoint.toString());
    }

    @Test
    @org.testng.annotations.Test
    public void testConstructorNullClusivity()
    {
        int bound = 1;
        Clusivity clusivity = null;
        assertThrows(NullPointerException.class, ()->new UpperEndpoint<>(bound, clusivity));
    }

    @Test
    @org.testng.annotations.Test
    public void testCompareToFloat()
    {
        // inclusive [5.1
        // exclusive (5.1
        Float bound = 5.1f;
        UpperEndpoint<Float> inclusiveUpperEndpoint = new UpperEndpoint<>(bound, Clusivity.INCLUSIVE);
        UpperEndpoint<Float> exclusiveUpperEndpoint = new UpperEndpoint<>(bound, Clusivity.EXCLUSIVE);

        assertEquals(0, inclusiveUpperEndpoint.compareTo(inclusiveUpperEndpoint));
        assertEquals(0, exclusiveUpperEndpoint.compareTo(exclusiveUpperEndpoint));
        assertEquals(1, inclusiveUpperEndpoint.compareTo(exclusiveUpperEndpoint));
        assertEquals(-1, exclusiveUpperEndpoint.compareTo(inclusiveUpperEndpoint));
    }

    @Test
    @org.testng.annotations.Test
    public void testIsBeforeValue()
    {
        Float value = 5.1f;
        UpperEndpoint<Float> inclusiveUpperEndpoint = new UpperEndpoint<>(value, Clusivity.INCLUSIVE);
        assertFalse(inclusiveUpperEndpoint.isBefore(value - 1.0f));
        assertFalse(inclusiveUpperEndpoint.isBefore(value));
        assertTrue(inclusiveUpperEndpoint.isBefore(value + 1.0f));

        UpperEndpoint<Float> exclusiveUpperEndpoint = new UpperEndpoint<>(value, Clusivity.EXCLUSIVE);
        assertFalse(exclusiveUpperEndpoint.isBefore(value - 1.0f));
        assertTrue(exclusiveUpperEndpoint.isBefore(value));
        assertTrue(exclusiveUpperEndpoint.isBefore(value + 1.0f));
    }

    @Test
    @org.testng.annotations.Test
    public void testIsBeforeUpperEndpoint()
    {
        Float value = 5.1f;
        UpperEndpoint<Float> inclusiveUpperEndpoint = new UpperEndpoint<>(value, Clusivity.INCLUSIVE);

        assertFalse(inclusiveUpperEndpoint.isBefore(new UpperEndpoint<>(value - 1.0f, Clusivity.INCLUSIVE)));
        assertFalse(inclusiveUpperEndpoint.isBefore(new UpperEndpoint<>(value, Clusivity.INCLUSIVE)));
        assertTrue(inclusiveUpperEndpoint.isBefore(new UpperEndpoint<>(value + 1.0f, Clusivity.INCLUSIVE)));

        assertFalse(inclusiveUpperEndpoint.isBefore(new UpperEndpoint<>(value - 1.0f, Clusivity.EXCLUSIVE)));
        assertFalse(inclusiveUpperEndpoint.isBefore(new UpperEndpoint<>(value, Clusivity.EXCLUSIVE)));
        assertTrue(inclusiveUpperEndpoint.isBefore(new UpperEndpoint<>(value + 1.0f, Clusivity.EXCLUSIVE)));

        UpperEndpoint<Float> exclusiveUpperEndpoint = new UpperEndpoint<>(value, Clusivity.EXCLUSIVE);

        assertFalse(exclusiveUpperEndpoint.isBefore(new UpperEndpoint<>(value - 1.0f, Clusivity.INCLUSIVE)));
        assertTrue(exclusiveUpperEndpoint.isBefore(new UpperEndpoint<>(value, Clusivity.INCLUSIVE)));
        assertTrue(exclusiveUpperEndpoint.isBefore(new UpperEndpoint<>(value + 1.0f, Clusivity.INCLUSIVE)));

        assertFalse(exclusiveUpperEndpoint.isBefore(new UpperEndpoint<>(value - 1.0f, Clusivity.EXCLUSIVE)));
        assertFalse(exclusiveUpperEndpoint.isBefore(new UpperEndpoint<>(value, Clusivity.EXCLUSIVE)));
        assertTrue(exclusiveUpperEndpoint.isBefore(new UpperEndpoint<>(value + 1.0f, Clusivity.EXCLUSIVE)));
    }

    @Test
    @org.testng.annotations.Test
    public void testIsAfterValue()
    {
        Float value = 5.1f;
        UpperEndpoint<Float> inclusiveUpperEndpoint = new UpperEndpoint<>(value, Clusivity.INCLUSIVE);
        assertTrue(inclusiveUpperEndpoint.isAfter(value - 1.0f));
        assertFalse(inclusiveUpperEndpoint.isAfter(value));
        assertFalse(inclusiveUpperEndpoint.isAfter(value + 1.0f));

        UpperEndpoint<Float> exclusiveUpperEndpoint = new UpperEndpoint<>(value, Clusivity.EXCLUSIVE);
        assertTrue(exclusiveUpperEndpoint.isAfter(value - 1.0f));
        assertTrue(exclusiveUpperEndpoint.isAfter(value));
        assertFalse(exclusiveUpperEndpoint.isAfter(value + 1.0f));
    }

    @Test
    @org.testng.annotations.Test
    public void testIsAfterUpperEndpoint()
    {
        Float value = 5.1f;
        UpperEndpoint<Float> inclusiveUpperEndpoint = new UpperEndpoint<>(value, Clusivity.INCLUSIVE);

        assertTrue(inclusiveUpperEndpoint.isAfter(new UpperEndpoint<>(value - 1.0f, Clusivity.INCLUSIVE)));
        assertFalse(inclusiveUpperEndpoint.isAfter(new UpperEndpoint<>(value, Clusivity.INCLUSIVE)));
        assertFalse(inclusiveUpperEndpoint.isAfter(new UpperEndpoint<>(value + 1.0f, Clusivity.INCLUSIVE)));

        assertTrue(inclusiveUpperEndpoint.isAfter(new UpperEndpoint<>(value - 1.0f, Clusivity.EXCLUSIVE)));
        assertTrue(inclusiveUpperEndpoint.isAfter(new UpperEndpoint<>(value, Clusivity.EXCLUSIVE)));
        assertFalse(inclusiveUpperEndpoint.isAfter(new UpperEndpoint<>(value + 1.0f, Clusivity.EXCLUSIVE)));

        UpperEndpoint<Float> exclusiveUpperEndpoint = new UpperEndpoint<>(value, Clusivity.EXCLUSIVE);

        assertTrue(exclusiveUpperEndpoint.isAfter(new UpperEndpoint<>(value - 1.0f, Clusivity.INCLUSIVE)));
        assertFalse(exclusiveUpperEndpoint.isAfter(new UpperEndpoint<>(value, Clusivity.INCLUSIVE)));
        assertFalse(exclusiveUpperEndpoint.isAfter(new UpperEndpoint<>(value + 1.0f, Clusivity.INCLUSIVE)));

        assertTrue(exclusiveUpperEndpoint.isAfter(new UpperEndpoint<>(value - 1.0f, Clusivity.EXCLUSIVE)));
        assertFalse(exclusiveUpperEndpoint.isAfter(new UpperEndpoint<>(value, Clusivity.EXCLUSIVE)));
        assertFalse(exclusiveUpperEndpoint.isAfter(new UpperEndpoint<>(value + 1.0f, Clusivity.EXCLUSIVE)));
    }

    @Test
    @org.testng.annotations.Test
    public void testMinIntegerEqualBounds()
    {
        // inclusive 1] min
        // exclusive 1) max
        // exclusive < inclusive
        Integer bound = 1;
        UpperEndpoint<Integer> inclusiveUpperEndpoint = new UpperEndpoint<>(bound, Clusivity.INCLUSIVE);
        UpperEndpoint<Integer> exclusiveUpperEndpoint = new UpperEndpoint<>(bound, Clusivity.EXCLUSIVE);

        assertEquals(inclusiveUpperEndpoint, inclusiveUpperEndpoint.min(inclusiveUpperEndpoint));
        assertEquals(exclusiveUpperEndpoint, inclusiveUpperEndpoint.min(exclusiveUpperEndpoint));
        assertEquals(exclusiveUpperEndpoint, exclusiveUpperEndpoint.min(inclusiveUpperEndpoint));
        assertEquals(exclusiveUpperEndpoint, exclusiveUpperEndpoint.min(exclusiveUpperEndpoint));
    }

    @Test
    @org.testng.annotations.Test
    public void testMaxIntegerEqualBounds()
    {
        // inclusive [1 min
        // exclusive (1 max
        /// inclusive > exclusive

        Integer bound = 1;
        UpperEndpoint<Integer> inclusiveUpperEndpoint = new UpperEndpoint<>(bound, Clusivity.INCLUSIVE);
        UpperEndpoint<Integer> exclusiveUpperEndpoint = new UpperEndpoint<>(bound, Clusivity.EXCLUSIVE);

        assertEquals(inclusiveUpperEndpoint, inclusiveUpperEndpoint.max(inclusiveUpperEndpoint));
        assertEquals(inclusiveUpperEndpoint, inclusiveUpperEndpoint.max(exclusiveUpperEndpoint));
        assertEquals(inclusiveUpperEndpoint, exclusiveUpperEndpoint.max(inclusiveUpperEndpoint));
        assertEquals(exclusiveUpperEndpoint, exclusiveUpperEndpoint.max(exclusiveUpperEndpoint));
    }

    @Test
    @org.testng.annotations.Test
    public void testMinIntegerUnequalBounds()
    {
        // inclusive 1]   1
        // exclusive 1)   0
        // inclusive > exclusive

        UpperEndpoint<Integer> inclusiveUpperEndpoint = new UpperEndpoint<>(1, Clusivity.INCLUSIVE);
        UpperEndpoint<Integer> exclusiveUpperEndpoint = new UpperEndpoint<>(2, Clusivity.EXCLUSIVE);

        assertEquals(inclusiveUpperEndpoint, inclusiveUpperEndpoint.min(inclusiveUpperEndpoint));
        assertEquals(inclusiveUpperEndpoint, inclusiveUpperEndpoint.min(exclusiveUpperEndpoint));
        assertEquals(inclusiveUpperEndpoint, exclusiveUpperEndpoint.min(inclusiveUpperEndpoint));
        assertEquals(exclusiveUpperEndpoint, exclusiveUpperEndpoint.min(exclusiveUpperEndpoint));
    }

    @Test
    @org.testng.annotations.Test
    public void testMaxIntegerUnequalBounds()
    {
        // inclusive [1 min
        // exclusive (1 max

        UpperEndpoint<Integer> inclusiveUpperEndpoint = new UpperEndpoint<>(1, Clusivity.INCLUSIVE);
        UpperEndpoint<Integer> exclusiveUpperEndpoint = new UpperEndpoint<>(2, Clusivity.EXCLUSIVE);

        assertEquals(inclusiveUpperEndpoint, inclusiveUpperEndpoint.max(inclusiveUpperEndpoint));
        assertEquals(exclusiveUpperEndpoint, inclusiveUpperEndpoint.max(exclusiveUpperEndpoint));
        assertEquals(exclusiveUpperEndpoint, exclusiveUpperEndpoint.max(inclusiveUpperEndpoint));
        assertEquals(exclusiveUpperEndpoint, exclusiveUpperEndpoint.max(exclusiveUpperEndpoint));
    }

    @Test
    @org.testng.annotations.Test
    public void testMinFloatEqualBounds()
    {
        UpperEndpoint<Float> inclusiveUpperEndpoint = new UpperEndpoint<>(2.3f, Clusivity.INCLUSIVE);
        UpperEndpoint<Float> exclusiveUpperEndpoint = new UpperEndpoint<>(2.3f, Clusivity.EXCLUSIVE);

        assertEquals(inclusiveUpperEndpoint, inclusiveUpperEndpoint.min(inclusiveUpperEndpoint));
        assertEquals(exclusiveUpperEndpoint, inclusiveUpperEndpoint.min(exclusiveUpperEndpoint));
        assertEquals(exclusiveUpperEndpoint, exclusiveUpperEndpoint.min(inclusiveUpperEndpoint));
        assertEquals(exclusiveUpperEndpoint, exclusiveUpperEndpoint.min(exclusiveUpperEndpoint));
    }

    @Test
    @org.testng.annotations.Test
    public void testMaxFloatEqualBounds()
    {
        // inclusive [2.3 max 2.3
        // exclusive (2.3 mim 2.2
        // inclusive > exclusive

        Float bound = 2.3f;
        UpperEndpoint<Float> inclusiveUpperEndpoint = new UpperEndpoint<>(bound, Clusivity.INCLUSIVE);
        UpperEndpoint<Float> exclusiveUpperEndpoint = new UpperEndpoint<>(bound, Clusivity.EXCLUSIVE);

        assertEquals(inclusiveUpperEndpoint, inclusiveUpperEndpoint.max(inclusiveUpperEndpoint));
        assertEquals(inclusiveUpperEndpoint, inclusiveUpperEndpoint.max(exclusiveUpperEndpoint));
        assertEquals(inclusiveUpperEndpoint, exclusiveUpperEndpoint.max(inclusiveUpperEndpoint));
        assertEquals(exclusiveUpperEndpoint, exclusiveUpperEndpoint.max(exclusiveUpperEndpoint));
    }

    @Test
    @org.testng.annotations.Test
    public void testMinFloatUnequalBounds()
    {
        // inclusive [2.3 min
        // exclusive (2.3 max
        UpperEndpoint<Float> inclusiveUpperEndpoint = new UpperEndpoint<>(2.3f, Clusivity.INCLUSIVE);
        UpperEndpoint<Float> exclusiveUpperEndpoint = new UpperEndpoint<>(2.4f, Clusivity.EXCLUSIVE);

        assertEquals(inclusiveUpperEndpoint, inclusiveUpperEndpoint.min(inclusiveUpperEndpoint));
        assertEquals(inclusiveUpperEndpoint, inclusiveUpperEndpoint.min(exclusiveUpperEndpoint));
        assertEquals(inclusiveUpperEndpoint, exclusiveUpperEndpoint.min(inclusiveUpperEndpoint));
        assertEquals(exclusiveUpperEndpoint, exclusiveUpperEndpoint.min(exclusiveUpperEndpoint));
    }

    @Test
    @org.testng.annotations.Test
    public void testMaxFloatUnequalBounds()
    {
        // inclusive [2.3 min
        // exclusive (2.3 max

        UpperEndpoint<Float> inclusiveUpperEndpoint = new UpperEndpoint<>(2.3f, Clusivity.INCLUSIVE);
        UpperEndpoint<Float> exclusiveUpperEndpoint = new UpperEndpoint<>(2.4f, Clusivity.EXCLUSIVE);

        assertEquals(inclusiveUpperEndpoint, inclusiveUpperEndpoint.max(inclusiveUpperEndpoint));
        assertEquals(exclusiveUpperEndpoint, inclusiveUpperEndpoint.max(exclusiveUpperEndpoint));
        assertEquals(exclusiveUpperEndpoint, exclusiveUpperEndpoint.max(inclusiveUpperEndpoint));
        assertEquals(exclusiveUpperEndpoint, exclusiveUpperEndpoint.max(exclusiveUpperEndpoint));
    }
    

    // todo: Test negative values
}
