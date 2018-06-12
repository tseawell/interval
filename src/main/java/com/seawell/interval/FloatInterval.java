package com.seawell.interval;


import java.util.List;

@SuppressWarnings("SimplifiableIfStatement")
/**
 User: tseawell
 Date: 12/15/12
 Time: 12:39 AM
 */

public class FloatInterval extends AInterval<Float>
{
    //###########################################################################
    // CONSTRUCTORS
    //###########################################################################

    public FloatInterval(LowerEndpoint<Float> lowerEndpoint, UpperEndpoint<Float> upperEndpoint)
    {
        super(lowerEndpoint, upperEndpoint);
    }

    //###########################################################################
    // PUBLIC METHODS
    //###########################################################################

    @Override
    public FloatInterval subInterval(LowerEndpoint<Float> lowerEndpoint, UpperEndpoint<Float> upperEndpoint)
    {
        IInterval<Float> subInterval = super.subInterval(lowerEndpoint, upperEndpoint);
        return ( subInterval == null ) ? null : (FloatInterval) subInterval;
    }

    @Override
    public FloatInterval intersect(IInterval<Float> interval)
    {
        IInterval<Float> intersection = super.intersect(interval);
        return (intersection == null) ? null : (FloatInterval) intersection;
    }

    @Override
    public FloatInterval union(IInterval<Float> interval)
    {
        IInterval<Float> union = super.union(interval);
        return ( union == null ) ? null : (FloatInterval) union;
    }

    @Override
    public List<IInterval<Float>> subtract(IInterval<Float> interval)
    {
        return super.subtract(interval);
    }
    
    
    @SuppressWarnings("CloneDoesntCallSuperClone")
    public FloatInterval clone()
    {
        return new FloatInterval(getLowerEndpoint(), getUpperEndpoint());
    }

    @Override
    protected Float decrementEndpoint(Endpoint<Float> endpoint)
    {
        Float bound = endpoint.getBound();
        return (endpoint.isInclusive()) ? bound
                : Float.intBitsToFloat(Float.floatToIntBits(bound) - 1);
    }

    @Override
    protected Float incrementEndpoint(Endpoint<Float> endpoint)
    {
        Float bound = endpoint.getBound();
        return (endpoint.isInclusive()) ? bound
                : Float.intBitsToFloat(Float.floatToIntBits(bound) + 1);
    }
}