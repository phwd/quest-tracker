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
            return Date.class;
        }
        if (c == '%') {
            return Number.class;
        }
        if (c == '/') {
            return URL.class;
        }
        if (c == ':') {
            return String.class;
        }
        if (c == '<') {
            return FileInputStream.class;
        }
        if (c == '>') {
            return File.class;
        }
        if (c == '@') {
            return Object.class;
        }
        if (c == '*') {
            return File[].class;
        }
        if (c != '+') {
            return null;
        }
        return Class.class;
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
                    Option.Builder builder = new Option.Builder(String.valueOf(c));
                    if (cls == null) {
                        z3 = false;
                    }
                    builder.hasArg(z3);
                    builder.required = z2;
                    builder.type = cls;
                    options.addOption(builder.build());
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
            Option.Builder builder2 = new Option.Builder(String.valueOf(c));
            if (cls != null) {
                z = true;
            }
            builder2.hasArg(z);
            builder2.required = z2;
            builder2.type = cls;
            options.addOption(builder2.build());
        }
        return options;
    }
}
