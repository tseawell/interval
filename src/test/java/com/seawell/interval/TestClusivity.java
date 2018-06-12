package com.seawell.interval;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 User: tseawell
 Date: 3/10/15
 Time: 12:12 PM
 */
public class TestClusivity
{
    //**************************************************************************
    // TEST METHODS
    //**************************************************************************

    @Test
    @org.testng.annotations.Test
    public void testExclusive()
    {
        Clusivity exclusive = Clusivity.EXCLUSIVE;
        assertNotNull(exclusive);
        assertEquals("EXCLUSIVE", exclusive.getName());
        assertEquals('(', exclusive.getLowerSymbol().charValue());
        assertEquals(')', exclusive.getUpperSymbol().charValue());
    }

    @Test
    @org.testng.annotations.Test
    public void testInclusive()
    {
        Clusivity inclusive = Clusivity.INCLUSIVE;
        assertNotNull(inclusive);
        assertEquals("INCLUSIVE", inclusive.getName());
        assertEquals('[', inclusive.getLowerSymbol().charValue());
        assertEquals(']', inclusive.getUpperSymbol().charValue());
    }

    @Test
    @org.testng.annotations.Test
    public void testValues()
    {
        Clusivity[] values = Clusivity.values();
        assertEquals(2, values.length);
        List<Clusivity> clusivities = Arrays.asList(values);
        assertTrue(clusivities.contains(Clusivity.INCLUSIVE),
                "Clusivities " + clusivities + " does not contain " + Clusivity.INCLUSIVE);
        assertTrue(clusivities.contains(Clusivity.EXCLUSIVE),
                "Clusivities " + clusivities + " does not contain " + Clusivity.EXCLUSIVE);
    }

    @Test
    @org.testng.annotations.Test
    public void testToString()
    {
        Clusivity[] values = Clusivity.values();
        assertNotNull(values);
        assertTrue(values.length > 0);
        for (Clusivity clusivity : values)
        {
            assertEquals(clusivity.getName(), clusivity.toString());
        }
    }
}
