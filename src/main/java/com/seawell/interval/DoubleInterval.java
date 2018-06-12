package com.seawell.interval;


import java.util.List;

@SuppressWarnings("SimplifiableIfStatement")
/**
 User: tseawell
 Date: 12/15/12
 Time: 12:39 AM
 */

public class DoubleInterval extends AInterval<Double>
{
    //###########################################################################
    // CONSTRUCTORS
    //###########################################################################

    public DoubleInterval(LowerEndpoint<Double> lowerEndpoint, UpperEndpoint<Double> upperEndpoint)
    {
        super(lowerEndpoint, upperEndpoint);
    }

    //###########################################################################
    // PUBLIC METHODS
    //###########################################################################

    @Override
    public DoubleInterval subInterval(LowerEndpoint<Double> lowerEndpoint, UpperEndpoint<Double> upperEndpoint)
    {
        IInterval<Double> subInterval = super.subInterval(lowerEndpoint, upperEndpoint);
        return ( subInterval == null ) ? null : (DoubleInterval) subInterval;
    }

    @Override
    public DoubleInterval intersect(IInterval<Double> interval)
    {
        IInterval<Double> intersection = super.intersect(interval);
        return (intersection == null) ? null : (DoubleInterval) intersection;
    }

    @Override
    public DoubleInterval union(IInterval<Double> interval)
    {
        IInterval<Double> union = super.union(interval);
        return ( union == null ) ? null : (DoubleInterval) union;
    }

    @Override
    public List<IInterval<Double>> subtract(IInterval<Double> interval)
    {
        return super.subtract(interval);
    }
    
    @SuppressWarnings("CloneDoesntCallSuperClone")
    public DoubleInterval clone()
    {
        return new DoubleInterval(getLowerEndpoint(), getUpperEndpoint());
    }

    @Override
    protected Double decrementEndpoint(Endpoint<Double> endpoint)
    {
        Double bound = endpoint.getBound();
        return (endpoint.isInclusive()) ? bound
                : Double.longBitsToDouble(Double.doubleToLongBits(bound) - 1);
    }

    @Override
    protected Double incrementEndpoint(Endpoint<Double> endpoint)
    {
        Double bound = endpoint.getBound();
        return (endpoint.isInclusive()) ? bound
                : Double.longBitsToDouble(Double.doubleToLongBits(bound) + 1);
    }
}