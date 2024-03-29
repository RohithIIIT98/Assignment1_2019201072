package com.cleancoder.args;

import java.util.*;

import static com.cleancoder.args.ArgsException.ErrorCode.*;

public class Args {
    private Map<Character, ArgumentMarshaler> marshalers = new HashMap<Character, ArgumentMarshaler>();
    private Set<Character> argsFound = new HashSet<Character>();
    private ListIterator<String> currentArgument;
    private List<String> argsList;
    private Map<Character, String> data = new HashMap<Character, String>();
    private String schema;

    public Args(String schema, String[] args) throws ArgsException {
        this.schema = schema;
        argsList = Arrays.asList(args);
        parse();
    }

    private void parse() throws ArgsException {
        getKeys gk = new getKeys();
        gk.parseSchema();
        assignValuesToKeys vk = new assignValuesToKeys();
        vk.parseArgumentStrings();
    }

    class getKeys {
        private boolean parseSchema() throws ArgsException {
            for (String element : schema.split(",")) {
                if (element.length() > 0) {
                    parseSchemaElement(element.trim());
                }
            }
            return true;
        }

        private void repeatIdCheck(Character elementId, String elementTail) throws ArgsException {
            if (!data.containsKey(elementId)) {
                data.put(elementId, elementTail);
            } else {
                if (data.get(elementId) != elementTail)
                    throw new ArgsException(REPEAT_ID);
            }
        }


        private void parseSchemaElement(String element) throws ArgsException {
            char elementId = element.charAt(0);
            String elementTail = element.substring(1);
            validateSchemaElementId(elementId);
            repeatIdCheck(elementId, elementTail);
            if (elementTail.length() == 0)
                marshalers.put(elementId, new BooleanArgumentMarshaler());
            else if (elementTail.equals("*"))
                marshalers.put(elementId, new StringArgumentMarshaler());
            else if (elementTail.equals("#"))
                marshalers.put(elementId, new IntegerArgumentMarshaler());
            else if (elementTail.equals("##"))
                marshalers.put(elementId, new DoubleArgumentMarshaler());
            else if (elementTail.equals("[*]"))
                marshalers.put(elementId, new StringArrayArgumentMarshaler());
            else if (elementTail.equals("&"))
                marshalers.put(elementId, new MapArgumentMarshaler());
            else
                throw new ArgsException(INVALID_ARGUMENT_FORMAT, elementId, elementTail);
        }

        private void validateSchemaElementId(char elementId) throws ArgsException {
            if (!Character.isLetter(elementId))
                throw new ArgsException(INVALID_ARGUMENT_NAME, elementId, null);
        }
    }

    class assignValuesToKeys {

        private void parseArgumentStrings() throws ArgsException {
            for (currentArgument = argsList.listIterator(); currentArgument.hasNext(); ) {
                String argString = currentArgument.next();
                if (argString.startsWith("-")) {
                    parseArgumentCharacters(argString.substring(1));
                } else {
                    currentArgument.previous();
                    throw new ArgsException(EXTRA_ARGS);
                }
            }
        }

        private void parseArgumentCharacters(String argChars) throws ArgsException {
            for (int i = 0; i < argChars.length(); i++)
                parseArgumentCharacter(argChars.charAt(i));
        }

        private void parseArgumentCharacter(char argChar) throws ArgsException {
            ArgumentMarshaler m = marshalers.get(argChar);
            if (m == null) {
                throw new ArgsException(UNEXPECTED_ARGUMENT, argChar, null);
            } else {
                argsFound.add(argChar);
                try {
                    char fault = 'l';
                    String parameter;
                    if (argChar == fault) {
                        m.set("");
                    } else {
                        if (currentArgument.hasNext()) {
                            m.set(currentArgument.next());
                        } else {
                            m.set("invalid");
                        }
                    }
                } catch (ArgsException e) {
                    e.setErrorArgumentId(argChar);
                    throw e;
                }
            }
        }
    }

    public boolean has(char arg) {
        return argsFound.contains(arg);
    }

    public int nextArgument() {
        return currentArgument.nextIndex();
    }

    public boolean getBoolean(char arg) {
        return BooleanArgumentMarshaler.getValue(marshalers.get(arg));
    }

    public String getString(char arg) {
        return StringArgumentMarshaler.getValue(marshalers.get(arg));
    }

    public int getInt(char arg) {
        return IntegerArgumentMarshaler.getValue(marshalers.get(arg));
    }

    public double getDouble(char arg) {
        return DoubleArgumentMarshaler.getValue(marshalers.get(arg));
    }

    public String[] getStringArray(char arg) {
        return StringArrayArgumentMarshaler.getValue(marshalers.get(arg));
    }

    public Map<String, String> getMap(char arg) {
        return MapArgumentMarshaler.getValue(marshalers.get(arg));
    }
}