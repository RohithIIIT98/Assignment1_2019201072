package com.cleancoder.args;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static com.cleancoder.args.ArgsException.ErrorCode.MISSING_STRING;

public class StringArgumentMarshaler implements ArgumentMarshaler {
    private String stringValue = "";

    public static boolean isvalidArgumentMarshaler(ArgumentMarshaler am){
      if(am != null && am instanceof StringArgumentMarshaler){
        return true;
      }
      return false;
    }

    public void set(String parameter) throws ArgsException {
        if (parameter != "invalid") {
            try {
                stringValue = parameter;
            } catch (NoSuchElementException e) {
                throw new ArgsException(MISSING_STRING);
            }
        } else {
            throw new ArgsException(MISSING_STRING);
        }
    }

    public static String getValue(ArgumentMarshaler am) {
        if (isvalidArgumentMarshaler(am))
            return ((StringArgumentMarshaler) am).stringValue;
        else
            return "";
    }
}
