package com.seawell.interval;

import java.util.List;

/**
 User: tseawell
 Date: 12/15/12
 Time: 11:56 AM
 */

public interface IInterval<T extends Comparable<T>> extends Cloneable
{
    LowerEndpoint<T> getLowerEndpoint();

    UpperEndpoint<T> getUpperEndpoint();
    
    boolean isEmpty();
    
    boolean contains(T object);

    boolean contains(Endpoint<T> object);

    boolean contains(IInterval<T> interval);

    boolean intersects(IInterval<T> interval);

    IInterval<T> intersect(IInterval<T> interval);

    IInterval<T> union(IInterval<T> interval);

    @SuppressWarnings("unchecked")
    List<IInterval<T>> subtract(IInterval<T> interval);

    IInterval<T> subInterval(LowerEndpoint<T> lowerEndpoint,
                             UpperEndpoint<T> upperEndpoint);

    Boolean isBefore(T value);

    boolean isBefore(IInterval<T> interval);

    Boolean isAfter(T value);

    Boolean isAfter(IInterval<T> interval);

    AInterval<T> clone();

    @Override
    int hashCode();

    boolean equals(Object other);

    int compare(Endpoint<T> endpointOne, Endpoint<T> endpointTwo);

    @Override
    String toString();

    boolean isUnBounded();
}
