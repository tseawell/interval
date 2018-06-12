package com.seawell.interval;

import com.seawell.exceptions.InvalidDataException;

/**
 User: tseawell
 Date: 3/9/15
 Time: 5:44 PM
 */

public abstract class Endpoint<T extends Comparable<T>> implements Comparable<Endpoint<T>>
{
    public static final String INFINITY = "~";

    private T mBound;
    private Clusivity mClusivity;

    //**************************************************************************
    // CONSTRUCTORS
    //**************************************************************************

    public Endpoint(T bound, Clusivity clusivity)
    {
        setBound(bound);
        setClusivity(clusivity);
        if ((isInfinite()) && (isInclusive()))
            throw new InvalidDataException("Bound cannot be null when clusivity is inclusive");
    }

    //**************************************************************************
    // GETTERS AND SETTERS
    //**************************************************************************

    public T getBound()
    {
        return mBound;
    }

    private void setBound(T bound)
    {
        mBound = bound;
    }

    public Clusivity getClusivity()
    {
        return mClusivity;
    }

    private void setClusivity(Clusivity clusivity)
    {
        if (clusivity == null)
            throw new NullPointerException("Parameter clusivity cannot be null");
        mClusivity = clusivity;
    }

    //**************************************************************************
    // PUBLIC METHODS
    //**************************************************************************

    public Boolean isInfinite()
    {
        return mBound == null;
    }

    public Boolean isInclusive()
    {
        return Clusivity.INCLUSIVE == mClusivity;
    }

    public Boolean isExclusive()
    {
        return Clusivity.EXCLUSIVE == mClusivity;
    }

    public Boolean isBefore(T bound)
    {
        // null = infinity
        if (bound == null) return false;

        if (getClusivity().equals(Clusivity.INCLUSIVE))
            return getBound().compareTo(bound) < 0;
        else
            return getBound().compareTo(bound) <= 0;
    }

    public Boolean isBefore(Endpoint<T> other)
    {
        // null = infinity
        if (other == null) return false;

        return this.compareTo(other) < 0;
    }

    public Boolean isAfter(T bound)
    {
        // null = infinity

        if (bound == null) return false;

        if (getClusivity().equals(Clusivity.INCLUSIVE))
            return getBound().compareTo(bound) > 0;
        else
            return getBound().compareTo(bound) >= 0;
    }

    public Boolean isAfter(Endpoint<T> other)
    {
        // null = infinity
        if (other == null) return false;

        return this.compareTo(other) > 0;
    }

    @SuppressWarnings("unchecked")
    public <E extends Endpoint<T>> E min(E other)
    {
        return (this.compareTo(other) <= 0) ? (E) this : other;
    }

    @SuppressWarnings("unchecked")
    public <E extends Endpoint<T>> E  max(E other)
    {
        return (this.compareTo(other) >= 0) ? (E) this : other;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null) return false;

        if (! o.getClass().equals(this.getClass())
                && ! o.getClass().getSuperclass().equals(Endpoint.class))
        {
            return false;
        }

        Endpoint endpoint = (Endpoint) o;

        if (mBound != null ? !mBound.equals(endpoint.mBound) : endpoint.mBound != null) return false;
        if (mClusivity != null ? !mClusivity.equals(endpoint.mClusivity) : endpoint.mClusivity != null) return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = mBound != null ? mBound.hashCode() : 0;
        result = 31 * result + (mClusivity != null ? mClusivity.hashCode() : 0);
        return result;
    }

    @Override
    abstract public int compareTo(Endpoint<T> other);

    //**************************************************************************
    // PROTECTED AND PRIVATE METHODS
    //**************************************************************************

    protected String boundString(T bound)
    {
        return bound == null ? INFINITY : String.valueOf(bound);
    }
}
