package com.seawell.interval;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 User: tseawell
 Date: 3/10/15
 Time: 12:12 PM
 */
@SuppressWarnings({"ConstantConditions", "EqualsWithItself"})
public class TestLowerEndpoint
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
        LowerEndpoint<Integer> lowerEndpoint = new LowerEndpoint<>(bound, clusivity);
        assertNotNull(lowerEndpoint);
        assertEquals(bound, lowerEndpoint.getBound().intValue());
        assertEquals(clusivity, lowerEndpoint.getClusivity());
        assertEquals("(1", lowerEndpoint.toString());
    }

    @Test
    @org.testng.annotations.Test
    public void testConstructorNullBound()
    {
        Integer bound = null;
        Clusivity clusivity = Clusivity.EXCLUSIVE;
        LowerEndpoint<Integer> lowerEndpoint = new LowerEndpoint<>(bound, clusivity);
        assertNotNull(lowerEndpoint);
        assertNull(lowerEndpoint.getBound());
        assertEquals(clusivity, lowerEndpoint.getClusivity());
        assertEquals("(~", lowerEndpoint.toString());
    }

    @Test
    @org.testng.annotations.Test
    public void testConstructorNullClusivity()
    {
        int bound = 1;
        Clusivity clusivity = null;
        assertThrows(NullPointerException.class, ()->new LowerEndpoint<>(bound, clusivity));
    }

    @SuppressWarnings("EqualsBetweenInconvertibleTypes")
    @Test
    @org.testng.annotations.Test
    public void testEqualsInteger()
    {
        Integer bound = 1;
        LowerEndpoint<Integer> inclusiveLowerEndpoint = new LowerEndpoint<>(bound, Clusivity.INCLUSIVE);
        assertEquals(inclusiveLowerEndpoint, inclusiveLowerEndpoint);

        LowerEndpoint<Integer> sameInclusiveLowerEndpoint = new LowerEndpoint<>(bound, Clusivity.INCLUSIVE);
        assertEquals(inclusiveLowerEndpoint, sameInclusiveLowerEndpoint);

        UpperEndpoint<Integer> sameInclusiveUpperEndpoint = new UpperEndpoint<>(bound, Clusivity.INCLUSIVE);
        assertTrue(inclusiveLowerEndpoint.equals(sameInclusiveUpperEndpoint));
        assertFalse(inclusiveLowerEndpoint.equals(bound));
    }

    @Test
    @org.testng.annotations.Test
    public void testCompareToIntegerEqualBounds()
    {
        // inclusive [1
        // exclusive (1
        Integer bound = 1;
        LowerEndpoint<Integer> inclusiveLowerEndpoint = new LowerEndpoint<>(bound, Clusivity.INCLUSIVE);
        LowerEndpoint<Integer> exclusiveLowerEndpoint = new LowerEndpoint<>(bound, Clusivity.EXCLUSIVE);

        assertEquals(0, inclusiveLowerEndpoint.compareTo(inclusiveLowerEndpoint));
        assertEquals(0, exclusiveLowerEndpoint.compareTo(exclusiveLowerEndpoint));
        assertEquals(-1, inclusiveLowerEndpoint.compareTo(exclusiveLowerEndpoint));
        assertEquals(1, exclusiveLowerEndpoint.compareTo(inclusiveLowerEndpoint));
    }

    @Test
    @org.testng.annotations.Test
    public void testCompareToIntegerUnequalBounds()
    {
        LowerEndpoint<Integer> inclusiveLowerEndpoint = new LowerEndpoint<>(1, Clusivity.INCLUSIVE);
        LowerEndpoint<Integer> exclusiveLowerEndpoint = new LowerEndpoint<>(2, Clusivity.EXCLUSIVE);

        assertEquals(0, inclusiveLowerEndpoint.compareTo(inclusiveLowerEndpoint));
        assertEquals(0, exclusiveLowerEndpoint.compareTo(exclusiveLowerEndpoint));
        assertEquals(-1, inclusiveLowerEndpoint.compareTo(exclusiveLowerEndpoint));
        assertEquals(1, exclusiveLowerEndpoint.compareTo(inclusiveLowerEndpoint));
    }

    @Test
    @org.testng.annotations.Test
    public void testCompareToFloat()
    {
        // inclusive [5.1
        // exclusive (5.1
        Float bound = 5.1f;
        LowerEndpoint<Float> inclusiveLowerEndpoint = new LowerEndpoint<>(bound, Clusivity.INCLUSIVE);
        LowerEndpoint<Float> exclusiveLowerEndpoint = new LowerEndpoint<>(bound, Clusivity.EXCLUSIVE);

        assertEquals(0, inclusiveLowerEndpoint.compareTo(inclusiveLowerEndpoint));
        assertEquals(0, exclusiveLowerEndpoint.compareTo(exclusiveLowerEndpoint));
        assertEquals(-1, inclusiveLowerEndpoint.compareTo(exclusiveLowerEndpoint));
        assertEquals(1, exclusiveLowerEndpoint.compareTo(inclusiveLowerEndpoint));
    }

    @Test
    @org.testng.annotations.Test
    public void testIsBeforeValue()
    {
        Float value = 5.1f;
        LowerEndpoint<Float> inclusiveLowerEndpoint = new LowerEndpoint<>(value, Clusivity.INCLUSIVE);
        assertFalse(inclusiveLowerEndpoint.isBefore(value - 1.0f));
        assertFalse(inclusiveLowerEndpoint.isBefore(value));
        assertTrue(inclusiveLowerEndpoint.isBefore(value + 1.0f));

        LowerEndpoint<Float> exclusiveLowerEndpoint = new LowerEndpoint<>(value, Clusivity.EXCLUSIVE);
        assertFalse(exclusiveLowerEndpoint.isBefore(value - 1.0f));
        assertTrue(exclusiveLowerEndpoint.isBefore(value));
        assertTrue(exclusiveLowerEndpoint.isBefore(value + 1.0f));
    }

    @Test
    @org.testng.annotations.Test
    public void testIsBeforeLowerEndpoint()
    {
        Float value = 5.1f;
        LowerEndpoint<Float> inclusiveLowerEndpoint = new LowerEndpoint<>(value, Clusivity.INCLUSIVE);

        assertFalse(inclusiveLowerEndpoint.isBefore(new LowerEndpoint<>(value - 1.0f, Clusivity.INCLUSIVE)));
        assertFalse(inclusiveLowerEndpoint.isBefore(new LowerEndpoint<>(value, Clusivity.INCLUSIVE)));
        assertTrue(inclusiveLowerEndpoint.isBefore(new LowerEndpoint<>(value + 1.0f, Clusivity.INCLUSIVE)));

        assertFalse(inclusiveLowerEndpoint.isBefore(new LowerEndpoint<>(value - 1.0f, Clusivity.EXCLUSIVE)));
        assertTrue(inclusiveLowerEndpoint.isBefore(new LowerEndpoint<>(value, Clusivity.EXCLUSIVE)));
        assertTrue(inclusiveLowerEndpoint.isBefore(new LowerEndpoint<>(value + 1.0f, Clusivity.EXCLUSIVE)));

        LowerEndpoint<Float> exclusiveLowerEndpoint = new LowerEndpoint<>(value, Clusivity.EXCLUSIVE);

        assertFalse(exclusiveLowerEndpoint.isBefore(new LowerEndpoint<>(value - 1.0f, Clusivity.INCLUSIVE)));
        assertFalse(exclusiveLowerEndpoint.isBefore(new LowerEndpoint<>(value, Clusivity.INCLUSIVE)));
        assertTrue(exclusiveLowerEndpoint.isBefore(new LowerEndpoint<>(value + 1.0f, Clusivity.INCLUSIVE)));

        assertFalse(exclusiveLowerEndpoint.isBefore(new LowerEndpoint<>(value - 1.0f, Clusivity.EXCLUSIVE)));
        assertFalse(exclusiveLowerEndpoint.isBefore(new LowerEndpoint<>(value, Clusivity.EXCLUSIVE)));
        assertTrue(exclusiveLowerEndpoint.isBefore(new LowerEndpoint<>(value + 1.0f, Clusivity.EXCLUSIVE)));
    }

    @Test
    @org.testng.annotations.Test
    public void testIsAfterValue()
    {
        Float value = 5.1f;
        LowerEndpoint<Float> inclusiveLowerEndpoint = new LowerEndpoint<>(value, Clusivity.INCLUSIVE);
        assertTrue(inclusiveLowerEndpoint.isAfter(value - 1.0f));
        assertFalse(inclusiveLowerEndpoint.isAfter(value));
        assertFalse(inclusiveLowerEndpoint.isAfter(value + 1.0f));

        LowerEndpoint<Float> exclusiveLowerEndpoint = new LowerEndpoint<>(value, Clusivity.EXCLUSIVE);
        assertTrue(exclusiveLowerEndpoint.isAfter(value - 1.0f));
        assertTrue(exclusiveLowerEndpoint.isAfter(value));
        assertFalse(exclusiveLowerEndpoint.isAfter(value + 1.0f));
    }

    @Test
    @org.testng.annotations.Test
    public void testIsAfterLowerEndpoint()
    {
        Float value = 5.1f;
        LowerEndpoint<Float> inclusiveLowerEndpoint = new LowerEndpoint<>(value, Clusivity.INCLUSIVE);

        assertTrue(inclusiveLowerEndpoint.isAfter(new LowerEndpoint<>(value - 1.0f, Clusivity.INCLUSIVE)));
        assertFalse(inclusiveLowerEndpoint.isAfter(new LowerEndpoint<>(value, Clusivity.INCLUSIVE)));
        assertFalse(inclusiveLowerEndpoint.isAfter(new LowerEndpoint<>(value + 1.0f, Clusivity.INCLUSIVE)));

        assertTrue(inclusiveLowerEndpoint.isAfter(new LowerEndpoint<>(value - 1.0f, Clusivity.EXCLUSIVE)));
        assertFalse(inclusiveLowerEndpoint.isAfter(new LowerEndpoint<>(value, Clusivity.EXCLUSIVE)));
        assertFalse(inclusiveLowerEndpoint.isAfter(new LowerEndpoint<>(value + 1.0f, Clusivity.EXCLUSIVE)));

        LowerEndpoint<Float> exclusiveLowerEndpoint = new LowerEndpoint<>(value, Clusivity.EXCLUSIVE);

        assertTrue(exclusiveLowerEndpoint.isAfter(new LowerEndpoint<>(value - 1.0f, Clusivity.INCLUSIVE)));
        assertTrue(exclusiveLowerEndpoint.isAfter(new LowerEndpoint<>(value, Clusivity.INCLUSIVE)));
        assertFalse(exclusiveLowerEndpoint.isAfter(new LowerEndpoint<>(value + 1.0f, Clusivity.INCLUSIVE)));

        assertTrue(exclusiveLowerEndpoint.isAfter(new LowerEndpoint<>(value - 1.0f, Clusivity.EXCLUSIVE)));
        assertFalse(exclusiveLowerEndpoint.isAfter(new LowerEndpoint<>(value, Clusivity.EXCLUSIVE)));
        assertFalse(exclusiveLowerEndpoint.isAfter(new LowerEndpoint<>(value + 1.0f, Clusivity.EXCLUSIVE)));
    }

    @Test
    @org.testng.annotations.Test
    public void testMinIntegerEqualBounds()
    {
        // inclusive [1 min
        // exclusive (1 max
        Integer bound = 1;
        LowerEndpoint<Integer> inclusiveLowerEndpoint = new LowerEndpoint<>(bound, Clusivity.INCLUSIVE);
        LowerEndpoint<Integer> exclusiveLowerEndpoint = new LowerEndpoint<>(bound, Clusivity.EXCLUSIVE);

        assertEquals(inclusiveLowerEndpoint, inclusiveLowerEndpoint.min(inclusiveLowerEndpoint));
        assertEquals(inclusiveLowerEndpoint, inclusiveLowerEndpoint.min(exclusiveLowerEndpoint));
        assertEquals(inclusiveLowerEndpoint, exclusiveLowerEndpoint.min(inclusiveLowerEndpoint));
        assertEquals(exclusiveLowerEndpoint, exclusiveLowerEndpoint.min(exclusiveLowerEndpoint));
    }

    @Test
    @org.testng.annotations.Test
    public void testMaxIntegerEqualBounds()
    {
        // inclusive [1 min
        // exclusive (1 max

        Integer bound = 1;
        LowerEndpoint<Integer> inclusiveLowerEndpoint = new LowerEndpoint<>(bound, Clusivity.INCLUSIVE);
        LowerEndpoint<Integer> exclusiveLowerEndpoint = new LowerEndpoint<>(bound, Clusivity.EXCLUSIVE);

        assertEquals(inclusiveLowerEndpoint, inclusiveLowerEndpoint.max(inclusiveLowerEndpoint));
        assertEquals(exclusiveLowerEndpoint, inclusiveLowerEndpoint.max(exclusiveLowerEndpoint));
        assertEquals(exclusiveLowerEndpoint, exclusiveLowerEndpoint.max(inclusiveLowerEndpoint));
        assertEquals(exclusiveLowerEndpoint, exclusiveLowerEndpoint.max(exclusiveLowerEndpoint));
    }

    @Test
    @org.testng.annotations.Test
    public void testMinIntegerUnequalBounds()
    {
        // inclusive [1 min
        // exclusive (1 max
        LowerEndpoint<Integer> inclusiveLowerEndpoint = new LowerEndpoint<>(1, Clusivity.INCLUSIVE);
        LowerEndpoint<Integer> exclusiveLowerEndpoint = new LowerEndpoint<>(2, Clusivity.EXCLUSIVE);

        assertEquals(inclusiveLowerEndpoint, inclusiveLowerEndpoint.min(inclusiveLowerEndpoint));
        assertEquals(inclusiveLowerEndpoint, inclusiveLowerEndpoint.min(exclusiveLowerEndpoint));
        assertEquals(inclusiveLowerEndpoint, exclusiveLowerEndpoint.min(inclusiveLowerEndpoint));
        assertEquals(exclusiveLowerEndpoint, exclusiveLowerEndpoint.min(exclusiveLowerEndpoint));
    }

    @Test
    @org.testng.annotations.Test
    public void testMaxIntegerUnequalBounds()
    {
        // inclusive [1 min
        // exclusive (1 max

        LowerEndpoint<Integer> inclusiveLowerEndpoint = new LowerEndpoint<>(1, Clusivity.INCLUSIVE);
        LowerEndpoint<Integer> exclusiveLowerEndpoint = new LowerEndpoint<>(2, Clusivity.EXCLUSIVE);

        assertEquals(inclusiveLowerEndpoint, inclusiveLowerEndpoint.max(inclusiveLowerEndpoint));
        assertEquals(exclusiveLowerEndpoint, inclusiveLowerEndpoint.max(exclusiveLowerEndpoint));
        assertEquals(exclusiveLowerEndpoint, exclusiveLowerEndpoint.max(inclusiveLowerEndpoint));
        assertEquals(exclusiveLowerEndpoint, exclusiveLowerEndpoint.max(exclusiveLowerEndpoint));
    }

    @Test
    @org.testng.annotations.Test
    public void testMinFloatEqualBounds()
    {
        // inclusive [2.3 min
        // exclusive (2.3 max
        Float bound = 2.3f;
        LowerEndpoint<Float> inclusiveLowerEndpoint = new LowerEndpoint<>(bound, Clusivity.INCLUSIVE);
        LowerEndpoint<Float> exclusiveLowerEndpoint = new LowerEndpoint<>(bound, Clusivity.EXCLUSIVE);

        assertEquals(inclusiveLowerEndpoint, inclusiveLowerEndpoint.min(inclusiveLowerEndpoint));
        assertEquals(inclusiveLowerEndpoint, inclusiveLowerEndpoint.min(exclusiveLowerEndpoint));
        assertEquals(inclusiveLowerEndpoint, exclusiveLowerEndpoint.min(inclusiveLowerEndpoint));
        assertEquals(exclusiveLowerEndpoint, exclusiveLowerEndpoint.min(exclusiveLowerEndpoint));
    }

    @Test
    @org.testng.annotations.Test
    public void testMaxFloatEqualBounds()
    {
        // inclusive [2.3 min
        // exclusive (2.3 max

        Float bound = 2.3f;
        LowerEndpoint<Float> inclusiveLowerEndpoint = new LowerEndpoint<>(bound, Clusivity.INCLUSIVE);
        LowerEndpoint<Float> exclusiveLowerEndpoint = new LowerEndpoint<>(bound, Clusivity.EXCLUSIVE);

        assertEquals(inclusiveLowerEndpoint, inclusiveLowerEndpoint.max(inclusiveLowerEndpoint));
        assertEquals(exclusiveLowerEndpoint, inclusiveLowerEndpoint.max(exclusiveLowerEndpoint));
        assertEquals(exclusiveLowerEndpoint, exclusiveLowerEndpoint.max(inclusiveLowerEndpoint));
        assertEquals(exclusiveLowerEndpoint, exclusiveLowerEndpoint.max(exclusiveLowerEndpoint));
    }

    @Test
    @org.testng.annotations.Test
    public void testMinFloatUnequalBounds()
    {
        // inclusive [2.3 min
        // exclusive (2.3 max
        LowerEndpoint<Float> inclusiveLowerEndpoint = new LowerEndpoint<>(2.3f, Clusivity.INCLUSIVE);
        LowerEndpoint<Float> exclusiveLowerEndpoint = new LowerEndpoint<>(2.4f, Clusivity.EXCLUSIVE);

        assertEquals(inclusiveLowerEndpoint, inclusiveLowerEndpoint.min(inclusiveLowerEndpoint));
        assertEquals(inclusiveLowerEndpoint, inclusiveLowerEndpoint.min(exclusiveLowerEndpoint));
        assertEquals(inclusiveLowerEndpoint, exclusiveLowerEndpoint.min(inclusiveLowerEndpoint));
        assertEquals(exclusiveLowerEndpoint, exclusiveLowerEndpoint.min(exclusiveLowerEndpoint));
    }

    @Test
    @org.testng.annotations.Test
    public void testMaxFloatUnequalBounds()
    {
        // inclusive [2.3 min
        // exclusive (2.3 max

        LowerEndpoint<Float> inclusiveLowerEndpoint = new LowerEndpoint<>(2.3f, Clusivity.INCLUSIVE);
        LowerEndpoint<Float> exclusiveLowerEndpoint = new LowerEndpoint<>(2.4f, Clusivity.EXCLUSIVE);

        assertEquals(inclusiveLowerEndpoint, inclusiveLowerEndpoint.max(inclusiveLowerEndpoint));
        assertEquals(exclusiveLowerEndpoint, inclusiveLowerEndpoint.max(exclusiveLowerEndpoint));
        assertEquals(exclusiveLowerEndpoint, exclusiveLowerEndpoint.max(inclusiveLowerEndpoint));
        assertEquals(exclusiveLowerEndpoint, exclusiveLowerEndpoint.max(exclusiveLowerEndpoint));
    }

    @Test
    @org.testng.annotations.Test
    public void testToString()
    {
        Clusivity exclusive = Clusivity.EXCLUSIVE;
        Float bound = null;
        LowerEndpoint<Float> lowerEndpoint = new LowerEndpoint<>(bound, exclusive);
        assertEquals(exclusive.getLowerSymbol() + Endpoint.INFINITY, lowerEndpoint.toString());

        UpperEndpoint<Float> upperEndpoint = new UpperEndpoint<>(bound, exclusive);
        assertEquals(Endpoint.INFINITY + exclusive.getUpperSymbol(), upperEndpoint.toString());

        bound = 2.3f;
        for (Clusivity clusivity : Clusivity.values())
        {
            String boundString = String.valueOf(bound);
            lowerEndpoint = new LowerEndpoint<>(bound, clusivity);
            assertEquals(clusivity.getLowerSymbol() + boundString, lowerEndpoint.toString());

            upperEndpoint = new UpperEndpoint<>(bound, clusivity);
            assertEquals(boundString + clusivity.getUpperSymbol(), upperEndpoint.toString());
        }
    }

    // todo: Test negative values
}
