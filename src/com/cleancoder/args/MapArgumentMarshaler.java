package com.cleancoder.args;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;

import static com.cleancoder.args.ArgsException.ErrorCode.*;

public class MapArgumentMarshaler implements ArgumentMarshaler {
    private Map<String, String> map = new HashMap<>();

    public static boolean isvalidArgumentMarshaler(ArgumentMarshaler am){
        if(am != null && am instanceof MapArgumentMarshaler){
            return true;
        }
        return false;
    }

    public void set(String parameter) throws ArgsException {
        if (parameter != "invalid") {
            String[] mapEntries = parameter.split(",");
            for (String entry : mapEntries) {
                String[] entryComponents = entry.split(":");
                if (entryComponents.length != 2)
                    throw new ArgsException(MALFORMED_MAP);
                map.put(entryComponents[0], entryComponents[1]);
            }
        } else {
            throw new ArgsException(MISSING_MAP);
        }
    }

    public static Map<String, String> getValue(ArgumentMarshaler am) {
        if (isvalidArgumentMarshaler(am))
            return ((MapArgumentMarshaler) am).map;
        else
            return new HashMap<>();
    }
}
