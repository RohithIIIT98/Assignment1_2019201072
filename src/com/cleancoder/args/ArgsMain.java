package com.cleancoder.args;

import java.util.*;

public class ArgsMain {
    public static void main(String[] args) {
        try {
            Args arg = new Args("l,d*,k##,m&,s[*],s[*],p#", args);
            boolean logging = arg.getBoolean('l');
            int port = arg.getInt('p');
            String directory = arg.getString('d');
            double val = arg.getDouble('k');
            executeApplication(logging, port, directory);

            Map<String, String> map = new HashMap<>();
            map = arg.getMap('m');
            for (Map.Entry<String, String> entry : map.entrySet()) {
                System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
            }

            String[] strings;
            strings = arg.getStringArray('s');
            for (int i = 0; i < strings.length; i++) {
                System.out.print(strings[i] + " ");
            }

        } catch (ArgsException e) {
            System.out.printf("Argument error: %s\n", e.errorMessage());
        }
    }

    private static void executeApplication(boolean logging, int port, String directory) {
        System.out.printf("logging is %s, port:%d, directory:%s\n", logging, port, directory);
    }
}