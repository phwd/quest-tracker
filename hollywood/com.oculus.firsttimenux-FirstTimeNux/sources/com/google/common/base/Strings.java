package com.google.common.base;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.VisibleForTesting;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@GwtCompatible
public final class Strings {
    private Strings() {
    }

    public static String nullToEmpty(@NullableDecl String string) {
        return Platform.nullToEmpty(string);
    }

    @NullableDecl
    public static String emptyToNull(@NullableDecl String string) {
        return Platform.emptyToNull(string);
    }

    public static boolean isNullOrEmpty(@NullableDecl String string) {
        return Platform.stringIsNullOrEmpty(string);
    }

    public static String padStart(String string, int minLength, char padChar) {
        Preconditions.checkNotNull(string);
        if (string.length() >= minLength) {
            return string;
        }
        StringBuilder sb = new StringBuilder(minLength);
        for (int i = string.length(); i < minLength; i++) {
            sb.append(padChar);
        }
        sb.append(string);
        return sb.toString();
    }

    public static String padEnd(String string, int minLength, char padChar) {
        Preconditions.checkNotNull(string);
        if (string.length() >= minLength) {
            return string;
        }
        StringBuilder sb = new StringBuilder(minLength);
        sb.append(string);
        for (int i = string.length(); i < minLength; i++) {
            sb.append(padChar);
        }
        return sb.toString();
    }

    public static String repeat(String string, int count) {
        boolean z = true;
        Preconditions.checkNotNull(string);
        if (count <= 1) {
            if (count < 0) {
                z = false;
            }
            Preconditions.checkArgument(z, "invalid count: %s", count);
            return count == 0 ? "" : string;
        }
        int len = string.length();
        long longSize = ((long) len) * ((long) count);
        int size = (int) longSize;
        if (((long) size) != longSize) {
            throw new ArrayIndexOutOfBoundsException("Required array size too large: " + longSize);
        }
        char[] array = new char[size];
        string.getChars(0, len, array, 0);
        int n = len;
        while (n < size - n) {
            System.arraycopy(array, 0, array, n, n);
            n <<= 1;
        }
        System.arraycopy(array, 0, array, n, size - n);
        return new String(array);
    }

    public static String commonPrefix(CharSequence a, CharSequence b) {
        Preconditions.checkNotNull(a);
        Preconditions.checkNotNull(b);
        int maxPrefixLength = Math.min(a.length(), b.length());
        int p = 0;
        while (p < maxPrefixLength && a.charAt(p) == b.charAt(p)) {
            p++;
        }
        if (validSurrogatePairAt(a, p - 1) || validSurrogatePairAt(b, p - 1)) {
            p--;
        }
        return a.subSequence(0, p).toString();
    }

    public static String commonSuffix(CharSequence a, CharSequence b) {
        Preconditions.checkNotNull(a);
        Preconditions.checkNotNull(b);
        int maxSuffixLength = Math.min(a.length(), b.length());
        int s = 0;
        while (s < maxSuffixLength && a.charAt((a.length() - s) - 1) == b.charAt((b.length() - s) - 1)) {
            s++;
        }
        if (validSurrogatePairAt(a, (a.length() - s) - 1) || validSurrogatePairAt(b, (b.length() - s) - 1)) {
            s--;
        }
        return a.subSequence(a.length() - s, a.length()).toString();
    }

    @VisibleForTesting
    static boolean validSurrogatePairAt(CharSequence string, int index) {
        return index >= 0 && index <= string.length() + -2 && Character.isHighSurrogate(string.charAt(index)) && Character.isLowSurrogate(string.charAt(index + 1));
    }

    public static String lenientFormat(@NullableDecl String template, @NullableDecl Object... args) {
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

    private static String lenientToString(@NullableDecl Object o) {
        try {
            return String.valueOf(o);
        } catch (Exception e) {
            String objectToString = o.getClass().getName() + '@' + Integer.toHexString(System.identityHashCode(o));
            Logger.getLogger("com.google.common.base.Strings").log(Level.WARNING, "Exception during lenientFormat for " + objectToString, (Throwable) e);
            return "<" + objectToString + " threw " + e.getClass().getName() + ">";
        }
    }
}
