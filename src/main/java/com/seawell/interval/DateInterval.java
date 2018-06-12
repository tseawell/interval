package com.seawell.interval;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * User: tseawell
 * Date: 5/23/2017
 * Time: 2:48 PM
 */
public class DateInterval extends AInterval<Date>
{
    //**************************************************************************
    // CONSTRUCTORS
    //**************************************************************************
    
    public DateInterval(LowerEndpoint<Date> lowerEndpoint, UpperEndpoint<Date> upperEndpoint)
    {
        super(lowerEndpoint, upperEndpoint);
    }
    
    //**************************************************************************
    // GETTERS AND SETTERS
    //**************************************************************************
    
    //**************************************************************************
    // PUBLIC METHODS
    //**************************************************************************
    
    /**
     The number of milliseconds in the interval, will be inaccurate on leap seconds.
     @return Long null if empty or unbounded, else returns the milliseconds in the interval
     */
    public Long length()
    {
        if (isEmpty()) return null;
        if (isUnBounded()) return null;
        
        long upperBoundTime = getUpperEndpoint().getBound().getTime();
        long lowerBoundTime = getLowerEndpoint().getBound().getTime();
        long subtraction = upperBoundTime - lowerBoundTime;
        long toMillis = TimeUnit.DAYS.toMillis(1);
        long length = subtraction + toMillis;
        if (getUpperEndpoint().isExclusive())
            length--;
        if (getLowerEndpoint().isExclusive())
            length--;
    
        return length;
    }
    
    @Override
    public DateInterval subInterval(LowerEndpoint<Date> lowerEndpoint, UpperEndpoint<Date> upperEndpoint)
    {
        IInterval<Date> subInterval = super.subInterval(lowerEndpoint, upperEndpoint);
        return ( subInterval == null ) ? null : (DateInterval) subInterval;
    }
    
    @Override
    public DateInterval intersect(IInterval<Date> interval)
    {
        IInterval<Date> intersection = super.intersect(interval);
        return (intersection == null) ? null : (DateInterval) intersection;
    }
    
    @Override
    public DateInterval union(IInterval<Date> interval)
    {
        IInterval<Date> union = super.union(interval);
        return ( union == null ) ? null : (DateInterval) union;
    }
    
    @Override
    public List<IInterval<Date>> subtract(IInterval<Date> interval)
    {
        return super.subtract(interval);
    }
    
    @Override
    public AInterval<Date> clone()
    {
        return new DateInterval(getLowerEndpoint(), getUpperEndpoint());
    }
    
    @Override
    public int compare(Endpoint<Date> endpointOne, Endpoint<Date> endpointTwo)
    {
        if (endpointOne instanceof LowerEndpoint && endpointTwo instanceof UpperEndpoint)
        {
            if (endpointOne.isInfinite() || endpointTwo.isInfinite())
                return -1;
            
            Date firstValue = incrementEndpoint(endpointOne);
            Date lastValue = decrementEndpoint(endpointTwo);
            return firstValue.compareTo(lastValue);
        }
        else if (endpointOne instanceof UpperEndpoint && endpointTwo instanceof LowerEndpoint)
        {
            if (endpointOne.isInfinite() || endpointTwo.isInfinite())
                return 1;
            
            Date firstValue = decrementEndpoint(endpointOne);
            Date lastValue = incrementEndpoint(endpointTwo);
            return firstValue.compareTo(lastValue);
        }
        else
        {
            return endpointOne.compareTo(endpointTwo);
        }
    }
    
    @Override
    protected Date decrementEndpoint(Endpoint<Date> endpoint)
    {
        Date bound = endpoint.getBound();
        return (endpoint.isInclusive()) ? bound : new Date(bound.getTime() - 1);
    }
    
    @Override
    protected Date incrementEndpoint(Endpoint<Date> endpoint)
    {
        Date bound = endpoint.getBound();
        return (endpoint.isInclusive()) ? bound : new Date(bound.getTime() + 1);
    }
    
    
    //**************************************************************************
    // PROTECTED AND PRIVATE METHODS
    //**************************************************************************
}
