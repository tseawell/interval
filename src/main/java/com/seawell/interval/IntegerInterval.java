package com.seawell.interval;


import java.util.List;

@SuppressWarnings("SimplifiableIfStatement")
/**
 User: tseawell
 Date: 12/15/12
 Time: 12:39 AM
 */

public class IntegerInterval extends AInterval<Integer>
{
    //###########################################################################
    // CONSTRUCTORS
    //###########################################################################

    public IntegerInterval(LowerEndpoint<Integer> lowerEndpoint, UpperEndpoint<Integer> upperEndpoint)
    {
        super(lowerEndpoint, upperEndpoint);
    }

    //###########################################################################
    // PUBLIC METHODS
    //###########################################################################

    public Integer length()
    {
        if (isEmpty()) return null;
        if (getLowerEndpoint().isInfinite() || getUpperEndpoint().isInfinite()) return null;
        int length = getUpperEndpoint().getBound() - getLowerEndpoint().getBound() + 1;
        if (getUpperEndpoint().isExclusive())
            length--;
        if (getLowerEndpoint().isExclusive())
            length--;

        return length;
    }

    @Override
    public IntegerInterval subInterval(LowerEndpoint<Integer> lowerEndpoint, UpperEndpoint<Integer> upperEndpoint)
    {
        IInterval<Integer> subInterval = super.subInterval(lowerEndpoint, upperEndpoint);
        return ( subInterval == null ) ? null : (IntegerInterval) subInterval;
    }

    @Override
    public IntegerInterval intersect(IInterval<Integer> interval)
    {
        IInterval<Integer> intersection = super.intersect(interval);
        return (intersection == null) ? null : (IntegerInterval) intersection;
    }

    @Override
    public IntegerInterval union(IInterval<Integer> interval)
    {
        IInterval<Integer> union = super.union(interval);
        return ( union == null ) ? null : (IntegerInterval) union;
    }

    @Override
    public List<IInterval<Integer>> subtract(IInterval<Integer> interval)
    {
        return super.subtract(interval);
    }

    @SuppressWarnings("CloneDoesntCallSuperClone")
    public IntegerInterval clone()
    {
        return new IntegerInterval(getLowerEndpoint(), getUpperEndpoint());
    }

    @Override
    public int compare(Endpoint<Integer> endpointOne, Endpoint<Integer> endpointTwo)
    {
        if (endpointOne instanceof LowerEndpoint && endpointTwo instanceof UpperEndpoint)
        {
            if (endpointOne.isInfinite() || endpointTwo.isInfinite())
                return -1;

            int firstValue = incrementEndpoint(endpointOne);
            int lastValue = decrementEndpoint(endpointTwo);
            return new Integer(firstValue).compareTo(lastValue);
        }
        else if (endpointOne instanceof UpperEndpoint && endpointTwo instanceof LowerEndpoint)
        {
            if (endpointOne.isInfinite() || endpointTwo.isInfinite())
                return 1;

            int firstValue = decrementEndpoint(endpointOne);
            int lastValue = incrementEndpoint(endpointTwo);
            return new Integer(firstValue).compareTo(lastValue);
        }
        else
        {
            return endpointOne.compareTo(endpointTwo);
        }
    }

    @Override
    protected Integer decrementEndpoint(Endpoint<Integer> endpoint)
    {
        Integer bound = endpoint.getBound();
        return (endpoint.isInclusive()) ? bound : bound - 1;
    }

    @Override
    protected Integer incrementEndpoint(Endpoint<Integer> endpoint)
    {
        Integer bound = endpoint.getBound();
        return (endpoint.isInclusive()) ? bound : bound + 1;
    }
}