package com.seawell.exceptions;

/**
 * User: tseawell
 * Date: 12/14/11
 * Time: 5:23 PM
 */

public class ProgrammingException extends RuntimeException
{
   // CONSTRUCTORS
   public ProgrammingException()
   {
   }

   public ProgrammingException(String msg)
   {
      super(msg);
   }

   public ProgrammingException(Throwable inThrowable)
   {
      super(inThrowable);
   }

   public ProgrammingException(String msg, Throwable inThrowable)
   {
      super(msg, inThrowable);
   }

}
