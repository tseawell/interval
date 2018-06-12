package com.seawell.exceptions;

import org.junit.jupiter.api.Test;

/**
 User: tseawell
 Date: 10/15/12
 Time: 12:28 PM
 */
public class TestProgrammingException
{
    //**************************************************************************
    // Test Methods
    //**************************************************************************

    @Test
    @org.testng.annotations.Test
    public void testProgrammingExceptionNoMessage()
            throws Exception
    {
        try
        {
            throw new ProgrammingException();
        }
        catch (ProgrammingException e)
        {
            // successful catch
        }
    }

    @Test
    @org.testng.annotations.Test
    public void testProgrammingExceptionWithMessage()
            throws Exception
    {
        try
        {
           throw new ProgrammingException("Bad Foo");
        }
        catch (ProgrammingException e)
        {
            // successful catch
        }
    }

    @Test
    @org.testng.annotations.Test
    public void testProgrammingExceptionWithThrowable()
            throws Exception
    {
        try
        {
            throw new ProgrammingException(new Throwable("Boo"));
        }
        catch (ProgrammingException e)
        {
            // successful catch
        }
    }

    @Test
    @org.testng.annotations.Test
    public void testProgrammingExceptionWithMessageAndThrowable()
            throws Exception
    {
        try
        {
            throw new ProgrammingException("Boo", new Throwable("Hoo"));
        }
        catch (ProgrammingException e)
        {
            // successful catch
        }
    }

    //**************************************************************************
    // PROTECTED AND PRIVATE METHODS
    //**************************************************************************
}
