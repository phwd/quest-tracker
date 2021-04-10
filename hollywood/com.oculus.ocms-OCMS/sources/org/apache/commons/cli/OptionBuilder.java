package org.apache.commons.cli;

@Deprecated
public final class OptionBuilder {
    private static final OptionBuilder INSTANCE = new OptionBuilder();
    private static String argName = null;
    private static String description = null;
    private static String longopt = null;
    private static int numberOfArgs = -1;
    private static boolean optionalArg;
    private static boolean required;
    private static Class<?> type;
    private static char valuesep;

    static {
        reset();
    }

    private OptionBuilder() {
    }

    private static void reset() {
        description = null;
        argName = null;
        longopt = null;
        type = String.class;
        required = false;
        numberOfArgs = -1;
        optionalArg = false;
        valuesep = 0;
    }

    public static OptionBuilder withLongOpt(String str) {
        longopt = str;
        return INSTANCE;
    }

    public static OptionBuilder hasArg() {
        numberOfArgs = 1;
        return INSTANCE;
    }

    public static OptionBuilder hasArg(boolean z) {
        numberOfArgs = z ? 1 : -1;
        return INSTANCE;
    }

    public static OptionBuilder withArgName(String str) {
        argName = str;
        return INSTANCE;
    }

    public static OptionBuilder isRequired() {
        required = true;
        return INSTANCE;
    }

    public static OptionBuilder withValueSeparator(char c) {
        valuesep = c;
        return INSTANCE;
    }

    public static OptionBuilder withValueSeparator() {
        valuesep = '=';
        return INSTANCE;
    }

    public static OptionBuilder isRequired(boolean z) {
        required = z;
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

    public static OptionBuilder hasOptionalArg() {
        numberOfArgs = 1;
        optionalArg = true;
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

    @Deprecated
    public static OptionBuilder withType(Object obj) {
        return withType((Class<?>) ((Class) obj));
    }

    public static OptionBuilder withType(Class<?> cls) {
        type = cls;
        return INSTANCE;
    }

    public static OptionBuilder withDescription(String str) {
        description = str;
        return INSTANCE;
    }

    public static Option create(char c) throws IllegalArgumentException {
        return create(String.valueOf(c));
    }

    public static Option create() throws IllegalArgumentException {
        if (longopt != null) {
            return create((String) null);
        }
        reset();
        throw new IllegalArgumentException("must specify longopt");
    }

    public static Option create(String str) throws IllegalArgumentException {
        try {
            Option option = new Option(str, description);
            option.setLongOpt(longopt);
            option.setRequired(required);
            option.setOptionalArg(optionalArg);
            option.setArgs(numberOfArgs);
            option.setType(type);
            option.setValueSeparator(valuesep);
            option.setArgName(argName);
            return option;
        } finally {
            reset();
        }
    }
}
