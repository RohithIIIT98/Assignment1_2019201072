package com.cleancoder.args;

import static com.cleancoder.args.ArgsException.ErrorCode.*;

import java.util.*;

public class DoubleArgumentMarshaler implements ArgumentMarshaler {
    private double doubleValue = 0;

    public static boolean isValidArgumentMarshaler(ArgumentMarshaler am) {
        if (am != null && am instanceof DoubleArgumentMarshaler) {
            return true;
        }
        return false;
    }

    public void set(String parameter) throws ArgsException {
        if (parameter != "invalid") {
            try {
                doubleValue = Double.parseDouble(parameter);
            } catch (NumberFormatException e) {
                throw new ArgsException(INVALID_DOUBLE, parameter);
            }
        } else {
            throw new ArgsException(MISSING_DOUBLE);
        }
    }

    public static double getValue(ArgumentMarshaler am) {
        if (isValidArgumentMarshaler(am))
            return ((DoubleArgumentMarshaler) am).doubleValue;
        else
            return 0.0;
    }
}
