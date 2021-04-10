package org.apache.commons.cli;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.util.Date;
import org.apache.commons.cli.Option;

public class PatternOptionBuilder {
    public static final Class<?> CLASS_VALUE = Class.class;
    public static final Class<Date> DATE_VALUE = Date.class;
    public static final Class<FileInputStream> EXISTING_FILE_VALUE = FileInputStream.class;
    public static final Class<File[]> FILES_VALUE = File[].class;
    public static final Class<File> FILE_VALUE = File.class;
    public static final Class<Number> NUMBER_VALUE = Number.class;
    public static final Class<Object> OBJECT_VALUE = Object.class;
    public static final Class<String> STRING_VALUE = String.class;
    public static final Class<URL> URL_VALUE = URL.class;

    public static boolean isValueCode(char c) {
        return c == '@' || c == ':' || c == '%' || c == '+' || c == '#' || c == '<' || c == '>' || c == '*' || c == '/' || c == '!';
    }

    public static Object getValueClass(char c) {
        if (c == '#') {
            return DATE_VALUE;
        }
        if (c == '%') {
            return NUMBER_VALUE;
        }
        if (c == '/') {
            return URL_VALUE;
        }
        if (c == ':') {
            return STRING_VALUE;
        }
        if (c == '<') {
            return EXISTING_FILE_VALUE;
        }
        if (c == '>') {
            return FILE_VALUE;
        }
        if (c == '@') {
            return OBJECT_VALUE;
        }
        if (c == '*') {
            return FILES_VALUE;
        }
        if (c != '+') {
            return null;
        }
        return CLASS_VALUE;
    }

    public static Options parsePattern(String str) {
        Options options = new Options();
        boolean z = false;
        Class<?> cls = null;
        int i = 0;
        char c = ' ';
        boolean z2 = false;
        while (true) {
            boolean z3 = true;
            if (i >= str.length()) {
                break;
            }
            char charAt = str.charAt(i);
            if (!isValueCode(charAt)) {
                if (c != ' ') {
                    Option.Builder builder = Option.builder(String.valueOf(c));
                    if (cls == null) {
                        z3 = false;
                    }
                    options.addOption(builder.hasArg(z3).required(z2).type(cls).build());
                    cls = null;
                    z2 = false;
                }
                c = charAt;
            } else if (charAt == '!') {
                z2 = true;
            } else {
                cls = (Class) getValueClass(charAt);
            }
            i++;
        }
        if (c != ' ') {
            Option.Builder builder2 = Option.builder(String.valueOf(c));
            if (cls != null) {
                z = true;
            }
            options.addOption(builder2.hasArg(z).required(z2).type(cls).build());
        }
        return options;
    }
}
