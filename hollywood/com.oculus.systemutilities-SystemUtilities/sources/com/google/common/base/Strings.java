package com.google.common.base;

import java.util.logging.Level;
import java.util.logging.Logger;

public final class Strings {
    public static boolean isNullOrEmpty(String string) {
        return Platform.stringIsNullOrEmpty(string);
    }

    public static String lenientFormat(String template, Object... args) {
        int placeholderStart;
        String template2 = String.valueOf(template);
        if (args == null) {
            args = new Object[]{"(Object[])null"};
        } else {
            for (int i = 0; i < args.length; i++) {
                args[i] = lenientToString(args[i]);
            }
        }
        StringBuilder builder = new StringBuilder(template2.length() + (args.length * 16));
        int templateStart = 0;
        int i2 = 0;
        while (i2 < args.length && (placeholderStart = template2.indexOf("%s", templateStart)) != -1) {
            builder.append((CharSequence) template2, templateStart, placeholderStart);
            builder.append(args[i2]);
            templateStart = placeholderStart + 2;
            i2++;
        }
        builder.append((CharSequence) template2, templateStart, template2.length());
        if (i2 < args.length) {
            builder.append(" [");
            int i3 = i2 + 1;
            builder.append(args[i2]);
            while (i3 < args.length) {
                builder.append(", ");
                i3++;
                builder.append(args[i3]);
            }
            builder.append(']');
        }
        return builder.toString();
    }

    private static String lenientToString(Object o) {
        try {
            return String.valueOf(o);
        } catch (Exception e) {
            String objectToString = o.getClass().getName() + '@' + Integer.toHexString(System.identityHashCode(o));
            Logger.getLogger("com.google.common.base.Strings").log(Level.WARNING, "Exception during lenientFormat for " + objectToString, (Throwable) e);
            return "<" + objectToString + " threw " + e.getClass().getName() + ">";
        }
    }
}
