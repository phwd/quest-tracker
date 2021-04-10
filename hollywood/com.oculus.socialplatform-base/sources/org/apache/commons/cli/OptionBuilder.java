package org.apache.commons.cli;

@Deprecated
public final class OptionBuilder {
    public static final OptionBuilder INSTANCE = new OptionBuilder();
    public static String argName = null;
    public static String description = null;
    public static String longopt = null;
    public static int numberOfArgs = -1;
    public static boolean optionalArg;
    public static boolean required;
    public static Class<?> type;
    public static char valuesep;

    public static OptionBuilder hasOptionalArg() {
        numberOfArgs = 1;
        optionalArg = true;
        return INSTANCE;
    }

    public static void reset() {
        description = null;
        argName = null;
        longopt = null;
        type = String.class;
        required = false;
        numberOfArgs = -1;
        optionalArg = false;
        valuesep = 0;
    }

    static {
        reset();
    }

    public static OptionBuilder withArgName(String str) {
        argName = str;
        return INSTANCE;
    }

    public static OptionBuilder withDescription(String str) {
        description = str;
        return INSTANCE;
    }

    public static OptionBuilder withLongOpt(String str) {
        longopt = str;
        return INSTANCE;
    }

    public static Option create() throws IllegalArgumentException {
        if (longopt != null) {
            return create((String) null);
        }
        reset();
        throw new IllegalArgumentException("must specify longopt");
    }

    public static Option create(char c) throws IllegalArgumentException {
        return create(String.valueOf(c));
    }

    public static Option create(String str) throws IllegalArgumentException {
        try {
            Option option = new Option(str, description);
            option.longOpt = longopt;
            option.required = required;
            option.optionalArg = optionalArg;
            option.numberOfArgs = numberOfArgs;
            option.type = type;
            option.valuesep = valuesep;
            option.argName = argName;
            return option;
        } finally {
            reset();
        }
    }

    public static OptionBuilder hasArg() {
        numberOfArgs = 1;
        return INSTANCE;
    }

    public static OptionBuilder hasArg(boolean z) {
        int i = -1;
        if (z) {
            i = 1;
        }
        numberOfArgs = i;
        return INSTANCE;
    }

    public static OptionBuilder hasArgs() {
        numberOfArgs = -2;
        return INSTANCE;
    }

    public static OptionBuilder hasArgs(int i) {
        numberOfArgs = i;
        return INSTANCE;
    }

    public static OptionBuilder hasOptionalArgs() {
        numberOfArgs = -2;
        optionalArg = true;
        return INSTANCE;
    }

    public static OptionBuilder hasOptionalArgs(int i) {
        numberOfArgs = i;
        optionalArg = true;
        return INSTANCE;
    }

    public static OptionBuilder isRequired() {
        required = true;
        return INSTANCE;
    }

    public static OptionBuilder isRequired(boolean z) {
        required = z;
        return INSTANCE;
    }

    public static OptionBuilder withType(Class<?> cls) {
        type = cls;
        return INSTANCE;
    }

    @Deprecated
    public static OptionBuilder withType(Object obj) {
        type = (Class) obj;
        return INSTANCE;
    }

    public static OptionBuilder withValueSeparator() {
        valuesep = '=';
        return INSTANCE;
    }

    public static OptionBuilder withValueSeparator(char c) {
        valuesep = c;
        return INSTANCE;
    }
}
