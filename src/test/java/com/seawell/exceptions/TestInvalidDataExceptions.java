package com.seawell.exceptions;

import org.junit.jupiter.api.Test;

/**
 User: tseawell
 Date: 10/15/12
 Time: 12:28 PM
 */
public class TestInvalidDataExceptions
{
    //**************************************************************************
    // Test Methods
    //**************************************************************************

    @Test
    @org.testng.annotations.Test
    public void testInvalidDataExceptionNoMessage()
            throws Exception
    {
        try
        {
            throw new InvalidDataException();
        }
        catch (InvalidDataException e)
        {
            // successful catch
        }
    }

    @Test
    @org.testng.annotations.Test
    public void testInvalidDataExceptionWithMessage()
            throws Exception
    {
        try
        {
           throw new InvalidDataException("Bad Foo");
        }
        catch (InvalidDataException e)
        {
            // successful catch
        }
    }

    @Test
    @org.testng.annotations.Test
    public void testInvalidDataExceptionWithThrowable()
            throws Exception
    {
        try
        {
            throw new InvalidDataException(new Throwable("Boo"));
        }
        catch (InvalidDataException e)
        {
            // successful catch
        }
    }

    @Test
    @org.testng.annotations.Test
    public void testInvalidDataExceptionWithMessageAndThrowable()
            throws Exception
    {
        try
        {
            throw new InvalidDataException("Boo", new Throwable("Hoo"));
        }
        catch (InvalidDataException e)
        {
            // successful catch
        }
    }

    //**************************************************************************
    // PROTECTED AND PRIVATE METHODS
    //**************************************************************************
}
