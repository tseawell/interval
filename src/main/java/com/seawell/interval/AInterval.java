package com.seawell.interval;

import com.seawell.exceptions.InvalidDataException;
import com.seawell.exceptions.ProgrammingException;

import java.util.ArrayList;
import java.util.List;

/**
 User: tseawell
 Date: 12/13/12
 Time: 9:28 PM
 */
@SuppressWarnings("SimplifiableIfStatement")
public abstract class AInterval<T extends Comparable<T>> implements IInterval<T>
{
    protected LowerEndpoint<T> mLowerEndpoint;
    protected UpperEndpoint<T> mUpperEndpoint;

    //**************************************************************************
    // CONSTRUCTORS
    //**************************************************************************

    protected AInterval(LowerEndpoint<T> lowerEndpoint, UpperEndpoint<T> upperEndpoint)
    {
        setLowerEndpoint(lowerEndpoint);
        setUpperEndpoint(upperEndpoint);
        if (lowerEndpoint.isInfinite() || upperEndpoint.isInfinite())
            return;
            
        if (lowerEndpoint.getBound().compareTo(upperEndpoint.getBound()) > 0 )
            throw new InvalidDataException("Lower endpoint " + lowerEndpoint +
                    " cannot be greater than upper endpoint " + upperEndpoint + "'");
    }

    //**************************************************************************
    // GETTERS AND SETTERS
    //**************************************************************************

    @Override
    public LowerEndpoint<T> getLowerEndpoint()
    {
       return mLowerEndpoint;
    }

    private void setLowerEndpoint(LowerEndpoint<T> lowerEndpoint)
    {
        if (lowerEndpoint == null)
            throw new NullPointerException("Parameter lowerEndpoint cannot be null");
        mLowerEndpoint = lowerEndpoint;
    }

    public UpperEndpoint<T> getUpperEndpoint()
    {
       return mUpperEndpoint;
    }

    private void setUpperEndpoint(UpperEndpoint<T> upperEndpoint)
          throws InvalidDataException
    {
        if (upperEndpoint == null)
            throw new NullPointerException("Parameter upperEndpoint cannot be null");
        mUpperEndpoint = upperEndpoint;
    }

    //**************************************************************************
    // PUBLIC METHODS
    //**************************************************************************
    
    /**
     (a, a), [a, a), and (a, a] represent the empty set,
     [a, a] denotes the set {a}.
     When a > b, all four notations are usually taken to represent the empty set.
 
     @return boolean false if [a, a] or [a, b]
     */
    @Override
    public boolean isEmpty()
    {
        if (mLowerEndpoint.isInfinite() || mUpperEndpoint.isInfinite())
            return false;

        return compare(mLowerEndpoint, mUpperEndpoint) > 0;
    }
    
    /**
     An infinite endpoint indicates that there is no bound in that direction, null values are used to represent
     infinite bound.
     
     @return boolean true if at least one endpoint is infinite
     */
    @Override
    public boolean isUnBounded()
    {
        return getLowerEndpoint().isInfinite() || getUpperEndpoint().isInfinite();
    }

    @Override
    public boolean contains(T value)
    {
        if (isUnBounded()) return true;
        if (isEmpty()) return false;

        return getLowerEndpoint().compareTo(new LowerEndpoint<>(value, Clusivity.INCLUSIVE)) <= 0
                && getUpperEndpoint().compareTo(new UpperEndpoint<>(value, Clusivity.INCLUSIVE)) >= 0;
    }

    @Override
    public boolean contains(Endpoint<T> endpoint)
    {
        if (isEmpty() || endpoint == null) return true;

        return compare(getLowerEndpoint(), endpoint) <=0
                && compare(getUpperEndpoint(), endpoint) >= 0;
    }

    public boolean contains(IInterval<T> interval)
    {
        if (this.isUnBounded())
            return true;

        if (interval == null) return true;

        // Any set contains the empty set
        if (interval.isEmpty()) return true;

        // empty set does not contain non-empty set;
        if (isEmpty()) return false;

        return contains(interval.getLowerEndpoint()) && contains(interval.getUpperEndpoint());
        //return getLowerEndpoint().compareTo(interval.getLowerEndpoint()) <= 0
        //        && getUpperEndpoint().compareTo(interval.getUpperEndpoint()) >=0;
    }

    /**
     Returns whether or not the two Intervals intersect (overlap).

     The intersection of any set with the empty set is the empty set:
     */
    @Override
    public boolean intersects(IInterval<T> interval)
    {
        if (interval == null || interval.isEmpty()) return true;

        if (isEmpty()) return true;

        return contains(interval.getLowerEndpoint())
                || contains(interval.getUpperEndpoint())
                || interval.contains(getLowerEndpoint())
                || interval.contains(getUpperEndpoint());
    }

    /**
     Returns the Interval of values that are in both this Interval and in the given Interval.

     The intersection of any set with the empty set is the empty set:
     */
    @Override
    public IInterval<T> intersect(IInterval<T> interval)
    {
        if (interval == null) return null;

        if (isEmpty())
            return this;

        if (interval.isEmpty())
            return interval;

        if (! intersects(interval))
            return null;

        LowerEndpoint<T> lowerEndpoint = getLowerEndpoint().max(interval.getLowerEndpoint());
        UpperEndpoint<T> upperEndpoint = getUpperEndpoint().min(interval.getUpperEndpoint());

        return subInterval(lowerEndpoint, upperEndpoint);
    }

    /**
     Returns the Interval of values that represents the union of this Interval and the given Interval.

     The union of A with the empty set is A.
     The union of the empty set with the empty set is the empty set

     Disjoint intervals are not supported.
     */
    @Override
    public IInterval<T> union(IInterval<T> interval)
    {
        if (interval == null) return null;

        if (interval.isEmpty())
            return this;

        if (this.isEmpty())
            return interval;

        if (! intersects(interval))
            throw new ProgrammingException("Disjoint intervals are not supported.");

        LowerEndpoint<T> lowerEndpoint = getLowerEndpoint().min(interval.getLowerEndpoint());
        UpperEndpoint<T> upperEndpoint = getUpperEndpoint().max(interval.getUpperEndpoint());

        return clone(lowerEndpoint, upperEndpoint);
   }

    /**
     Returns the Interval of values that are in this Interval but not in the given Interval.

     Set A subtract the empty set is A
     The empty set subtract the empty set is the empty set
     The empty set subtract set A is the empty set

     If the subtraction results in two disjoint intervals, they will be returned as two elements of a Interval array,
     otherwise the resultant Interval will be returned as the first element of an one element array.
     When this Interval is completely contained in the given Interval, an empty Interval is returned. If the given Interval is
     disjoint with this interval, null is returned.
     */
    @Override
    public List<IInterval<T>> subtract(IInterval<T> interval)
    {
        if (interval == null) return null;

        List<IInterval<T>> remainders = new ArrayList<>(3);
        if (isEmpty() || interval.isEmpty())
        {
            remainders.add(this);
            return remainders;
        }

        if (interval.isUnBounded()
                || this.equals(interval))
            return null;


        if (! intersects(interval))
        {
            remainders.add(this);
            return remainders;
        }

        IInterval<T> intersection = this.intersect(interval);

        LowerEndpoint<T> intersectionLowerEndpoint = intersection.getLowerEndpoint();
        Clusivity leftUpperClusivity = clusivity(intersectionLowerEndpoint);
        T leftUpperBound = intersectionLowerEndpoint.getBound();
        UpperEndpoint<T> leftUpperEndpoint = new UpperEndpoint<>(leftUpperBound, leftUpperClusivity);
        IInterval<T> leftInterval = subInterval(this.getLowerEndpoint(),leftUpperEndpoint);
        addInterval(remainders, leftInterval);

        UpperEndpoint<T> intersectionUpperEndpoint = intersection.getUpperEndpoint();
        Clusivity rightLowerClusivity = clusivity(intersectionUpperEndpoint);
        T rightLowerBound = intersectionUpperEndpoint.getBound();
        LowerEndpoint<T> rightLowerEndpoint = new LowerEndpoint<>(rightLowerBound, rightLowerClusivity);
        IInterval<T> rightInterval = subInterval(rightLowerEndpoint, this.getUpperEndpoint());
        addInterval(remainders, rightInterval);

        return remainders;
    }
    
    protected Clusivity clusivity(Endpoint<T> endpoint)
    {
        Clusivity clusivity;
        if (endpoint.isInfinite())
            clusivity = Clusivity.EXCLUSIVE;
        else
            clusivity = (endpoint.getClusivity() == Clusivity.INCLUSIVE)
                    ? Clusivity.EXCLUSIVE : Clusivity.INCLUSIVE;
        return clusivity;
    }

    public IInterval<T> subInterval(LowerEndpoint<T> lowerEndpoint,
                             UpperEndpoint<T> upperEndpoint)
    {
        if (lowerEndpoint == null || upperEndpoint == null)
            return null;

        if (isEmpty())
            return this;

        if (getLowerEndpoint().compareTo(lowerEndpoint) > 0) return null;
        if (getUpperEndpoint().compareTo(upperEndpoint) < 0) return null;

        return clone(lowerEndpoint, upperEndpoint);
    }

    @Override
    public boolean isBefore(IInterval<T> interval)
    {
        return compare(this.getUpperEndpoint(), interval.getLowerEndpoint()) < 0;
    }

    @Override
    public Boolean isBefore(T value)
    {
        if (this.isEmpty()) return null;
        if (value == null) return null;
        return getUpperEndpoint().isBefore(value);
    }

    @Override
    public Boolean isAfter(T value)
    {
        if (this.isEmpty()) return null;
        if (value == null) return null;

        return getLowerEndpoint().isAfter(value);
    }

    @Override
    public Boolean isAfter(IInterval<T> interval)
    {
        return compare(this.getLowerEndpoint(), interval.getUpperEndpoint()) > 0;
    }

    @Override
    public abstract AInterval<T> clone();

    @Override
    public int compare(Endpoint<T> endpointOne, Endpoint<T> endpointTwo)
    {
        if (endpointOne instanceof LowerEndpoint && endpointTwo instanceof UpperEndpoint)
        {
            if (endpointOne.isInfinite() || endpointTwo.isInfinite())
                return -1;

            int boundComparison = endpointOne.getBound().compareTo(endpointTwo.getBound());
            if (boundComparison == 0)
            {
                if (endpointOne.getClusivity().equals(endpointTwo.getClusivity()))
                    return endpointOne.getClusivity().equals(Clusivity.EXCLUSIVE) ? 1 : 0;
                if (endpointOne.getClusivity() == Clusivity.EXCLUSIVE
                        && endpointTwo.getClusivity() == Clusivity.INCLUSIVE)
                    return 1;
                if (endpointOne.getClusivity() == Clusivity.INCLUSIVE
                        && endpointTwo.getClusivity() == Clusivity.EXCLUSIVE)
                    return 1;
            }
            return boundComparison;
            //T firstValue = incrementEndpoint(endpointOne);
            //T lastValue = decrementEndpoint(endpointTwo);
            //return firstValue.compareTo(lastValue);
        }
        else if (endpointOne instanceof UpperEndpoint && endpointTwo instanceof LowerEndpoint)
        {
            if (endpointOne.isInfinite() || endpointTwo.isInfinite())
                return 1;

            int boundComparison = endpointOne.getBound().compareTo(endpointTwo.getBound());
            if (boundComparison == 0)
            {
                if (endpointOne.getClusivity().equals(endpointTwo.getClusivity()))
                    return endpointOne.getClusivity().equals(Clusivity.EXCLUSIVE) ? -1 : 0;
                if (endpointOne.getClusivity() == Clusivity.EXCLUSIVE
                        && endpointTwo.getClusivity() == Clusivity.INCLUSIVE)
                    return -1;
                if (endpointOne.getClusivity() == Clusivity.INCLUSIVE
                        && endpointTwo.getClusivity() == Clusivity.EXCLUSIVE)
                    return -1;
            }
            return boundComparison;

            //T firstValue = decrementEndpoint(endpointOne);
            //T lastValue = incrementEndpoint(endpointTwo);
            //return firstValue.compareTo(lastValue);
        }
        else
        {
            return endpointOne.compareTo(endpointTwo);
        }
    }

    @Override
    public int hashCode()
    {
        int result = mLowerEndpoint != null ? mLowerEndpoint.hashCode() : 0;
        result = 31 * result + (mUpperEndpoint != null ? mUpperEndpoint.hashCode() : 0);
        return result;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;

        AInterval interval = (AInterval) o;

        if (mUpperEndpoint != null ? !mUpperEndpoint.equals(interval.mUpperEndpoint) : interval.mUpperEndpoint != null) return false;
        return !(mLowerEndpoint != null ? !mLowerEndpoint.equals(interval.mLowerEndpoint) : interval.mLowerEndpoint != null);

    }

    @Override
    public String toString()
    {
        return getLowerEndpoint() + ", " + getUpperEndpoint();
    }

    //**************************************************************************
    // PROTECTED AND PRIVATE METHODS
    //**************************************************************************

    protected AInterval<T> clone(LowerEndpoint<T> lowerEndpoint, UpperEndpoint<T> upperEndpoint)
    {
        AInterval<T> clone = clone();
        clone.setLowerEndpoint(lowerEndpoint);
        clone.setUpperEndpoint(upperEndpoint);
        return clone;
    }

    protected void addInterval(List<IInterval<T>> remainders, IInterval<T> interval)
    {
        if (interval != null && ! interval.isEmpty())
            remainders.add(interval);
    }

    protected abstract T decrementEndpoint(Endpoint<T> endpoint);

    protected abstract T incrementEndpoint(Endpoint<T> endpoint);
}
