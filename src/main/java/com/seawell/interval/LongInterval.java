package com.seawell.interval;


import java.util.List;

@SuppressWarnings("SimplifiableIfStatement")
/**
 User: tseawell
 Date: 12/15/12
 Time: 12:39 AM
 */

public class LongInterval extends AInterval<Long>
{
    //###########################################################################
    // CONSTRUCTORS
    //###########################################################################

    public LongInterval(LowerEndpoint<Long> lowerEndpoint, UpperEndpoint<Long> upperEndpoint)
    {
        super(lowerEndpoint, upperEndpoint);
    }

    //###########################################################################
    // PUBLIC METHODS
    //###########################################################################

    public Long length()
    {
        if (isEmpty()) return null;
        if (getLowerEndpoint().isInfinite() || getUpperEndpoint().isInfinite()) return null;
        Long length = getUpperEndpoint().getBound() - getLowerEndpoint().getBound() + 1;
        if (getUpperEndpoint().isExclusive())
            length--;
        if (getLowerEndpoint().isExclusive())
            length--;

        return length;
    }

    @Override
    public LongInterval subInterval(LowerEndpoint<Long> lowerEndpoint, UpperEndpoint<Long> upperEndpoint)
    {
        IInterval<Long> subInterval = super.subInterval(lowerEndpoint, upperEndpoint);
        return ( subInterval == null ) ? null : (LongInterval) subInterval;
    }

    @Override
    public LongInterval intersect(IInterval<Long> interval)
    {
        IInterval<Long> intersection = super.intersect(interval);
        return (intersection == null) ? null : (LongInterval) intersection;
    }

    @Override
    public LongInterval union(IInterval<Long> interval)
    {
        IInterval<Long> union = super.union(interval);
        return ( union == null ) ? null : (LongInterval) union;
    }

    @Override
    public List<IInterval<Long>> subtract(IInterval<Long> interval)
    {
        return super.subtract(interval);
    }

    @SuppressWarnings("CloneDoesntCallSuperClone")
    public LongInterval clone()
    {
        return new LongInterval(getLowerEndpoint(), getUpperEndpoint());
    }

    @Override
    public int compare(Endpoint<Long> endpointOne, Endpoint<Long> endpointTwo)
    {
        if (endpointOne instanceof LowerEndpoint && endpointTwo instanceof UpperEndpoint)
        {
            if (endpointOne.isInfinite() || endpointTwo.isInfinite())
                return -1;

            long firstValue = incrementEndpoint(endpointOne);
            long lastValue = decrementEndpoint(endpointTwo);
            return new Long(firstValue).compareTo(lastValue);
        }
        else if (endpointOne instanceof UpperEndpoint && endpointTwo instanceof LowerEndpoint)
        {
            if (endpointOne.isInfinite() || endpointTwo.isInfinite())
                return 1;

            long firstValue = decrementEndpoint(endpointOne);
            long lastValue = incrementEndpoint(endpointTwo);
            return new Long(firstValue).compareTo(lastValue);
        }
        else
        {
            return endpointOne.compareTo(endpointTwo);
        }
    }

    @Override
    protected Long decrementEndpoint(Endpoint<Long> endpoint)
    {
        Long bound = endpoint.getBound();
        return (endpoint.isInclusive()) ? bound : bound - 1;
    }

    @Override
    protected Long incrementEndpoint(Endpoint<Long> endpoint)
    {
        Long bound = endpoint.getBound();
        return (endpoint.isInclusive()) ? bound : bound + 1;
    }
}