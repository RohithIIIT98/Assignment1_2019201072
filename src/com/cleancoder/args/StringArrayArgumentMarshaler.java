package com.cleancoder.args;

import static com.cleancoder.args.ArgsException.ErrorCode.*;

import java.util.*;

public class StringArrayArgumentMarshaler implements ArgumentMarshaler {
    private List<String> strings = new ArrayList<String>();

    public void set(String parameter) throws ArgsException {
        if (parameter != "invalid") {
            strings.add(parameter);
        } else {
            throw new ArgsException(MISSING_STRING);
        }
    }

    public static String[] getValue(ArgumentMarshaler am) {
        if (am != null && am instanceof StringArrayArgumentMarshaler)
            return ((StringArrayArgumentMarshaler) am).strings.toArray(new String[0]);
        else
            return new String[0];
    }
}
