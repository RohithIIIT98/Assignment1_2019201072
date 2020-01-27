package com.cleancoder.args;

import static com.cleancoder.args.ArgsException.ErrorCode.*;

import java.util.*;

public class IntegerArgumentMarshaler implements ArgumentMarshaler {
  private int intValue = 0;

  public static boolean isValidArgumentMarshaler(ArgumentMarshaler am){
    if(am != null && am instanceof IntegerArgumentMarshaler){
      return true;
    }
    return false;
  }
  public void set(String parameter) throws ArgsException {
    if(parameter!="invalid") {
      try {
        intValue = Integer.parseInt(parameter);
      }
      catch (NumberFormatException e) {
        throw new ArgsException(INVALID_INTEGER, parameter);
      }
    }
    else {
      throw new ArgsException(MISSING_INTEGER);
    }
  }
  public static int getValue(ArgumentMarshaler am) {
    if (isValidArgumentMarshaler(am))
      return ((IntegerArgumentMarshaler) am).intValue;
    else
      return 0;
  }
}
