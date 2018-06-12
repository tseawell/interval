package com.seawell.interval;

/**
 User: tseawell
 Date: 3/10/15
 Time: 9:58 AM
 */
public class UpperEndpoint<T extends Comparable<T>> extends Endpoint<T>
{

    //**************************************************************************
    // CONSTRUCTORS
    //**************************************************************************

    public UpperEndpoint(T bound, Clusivity clusivity)
    {
        super(bound, clusivity);
    }

    //**************************************************************************
    // GETTERS AND SETTERS
    //**************************************************************************

    //**************************************************************************
    // PUBLIC METHODS
    //**************************************************************************

     @Override
    public String toString()
    {
        return boundString(getBound()) + String.valueOf(getClusivity().getUpperSymbol());
    }

    @Override
    public int compareTo(Endpoint<T> other)
    {
        // inclusive 1]
        // exclusive 1)
        // ')'.compareTo(']')
        // 1) < 1]  or 0 < 1
    
        if (this.isInfinite() && other.isInfinite())
            return 0;
        if (this.isInfinite() || other.isInfinite())
            return 1;
        int boundCompare = getBound().compareTo(other.getBound());
        if (boundCompare != 0)
            return boundCompare;
        else if (this.getClusivity() != other.getClusivity())
        {
            return (isInclusive() && other.isExclusive())
                    ? 1 : -1;
        }
        return -0;
    }

    //**************************************************************************
    // PROTECTED AND PRIVATE METHODS
    //**************************************************************************
}
