package com.seawell.interval;

import com.seawell.exceptions.ProgrammingException;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 User: tseawell
 Date: 3/9/15
 Time: 5:40 PM
 */
public class Clusivity
{
    // Inclusive []
    // Exclusive ()
    public static final Clusivity INCLUSIVE = new Clusivity("INCLUSIVE", '[', ']');
    public static final Clusivity EXCLUSIVE = new Clusivity("EXCLUSIVE", '(', ')');

    private String mName;
    private Character mLowerSymbol;
    private Character mUpperSymbol;

    //**************************************************************************
    // CONSTRUCTORS
    //**************************************************************************

    private Clusivity(String name, Character lowerSymbol, Character upperSymbol)
    {
        setName(name);
        setLowerSymbol(lowerSymbol);
        setUpperSymbol(upperSymbol);
    }

    //**************************************************************************
    // GETTERS AND SETTERS
    //**************************************************************************


    public String getName()
    {
        return mName;
    }

    private void setName(String name)
    {
        mName = name;
    }

    public Character getLowerSymbol()
    {
        return mLowerSymbol;
    }

    private void setLowerSymbol(Character lowerSymbol)
    {
        mLowerSymbol = lowerSymbol;
    }

    public Character getUpperSymbol()
    {
        return mUpperSymbol;
    }

    private void setUpperSymbol(Character upperSymbol)
    {
        mUpperSymbol = upperSymbol;
    }

    //**************************************************************************
    // PUBLIC METHODS
    //**************************************************************************

    public static Clusivity[] values()
    {
        Field[] declaredFields = Clusivity.class.getDeclaredFields();
        List<Clusivity> clusivities = new ArrayList<>();
        for (Field declaredField : declaredFields)
        {
            try
            {
                if (java.lang.reflect.Modifier.isStatic(declaredField.getModifiers())
                        && declaredField.get(null) instanceof Clusivity)
                {
                    clusivities.add((Clusivity) declaredField.get(null));
                }
            }
            catch (IllegalAccessException e)
            {
                throw new ProgrammingException("Unable to use reflection to get static field "
                        + declaredField.getName() + "", e);
            }
        }

        return clusivities.toArray(new Clusivity[clusivities.size()]);
    }

    @Override
    public String toString()
    {
        return getName();
    }

    //**************************************************************************
    // PROTECTED AND PRIVATE METHODS
    //**************************************************************************
}
