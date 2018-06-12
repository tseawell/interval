package com.seawell.exceptions;

/**
 * User: tseawell
 * Date: 12/14/11
 * Time: 5:23 PM
 */

public class InvalidDataException extends RuntimeException
{
   // CONSTRUCTORS
   public InvalidDataException()
   {
   }

   public InvalidDataException(String msg)
   {
      super(msg);
   }

   public InvalidDataException(Throwable inThrowable)
   {
      super(inThrowable);
   }

   public InvalidDataException(String msg, Throwable inThrowable)
   {
      super(msg, inThrowable);
   }

}
