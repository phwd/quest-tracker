package org.apache.commons.cli;

import X.AnonymousClass006;
import java.io.File;
import java.io.FileInputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;

public class TypeHandler {
    public static Date createDate(String str) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public static File createFile(String str) {
        return new File(str);
    }

    public static File[] createFiles(String str) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public static Number createNumber(String str) throws ParseException {
        try {
            if (str.indexOf(46) != -1) {
                return Double.valueOf(str);
            }
            return Long.valueOf(str);
        } catch (NumberFormatException e) {
            throw new ParseException(e.getMessage());
        }
    }

    public static URL createURL(String str) throws ParseException {
        try {
            return new URL(str);
        } catch (MalformedURLException unused) {
            throw new ParseException(AnonymousClass006.A07("Unable to parse the URL: ", str));
        }
    }

    public static Class<?> createClass(String str) throws ParseException {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException unused) {
            throw new ParseException(AnonymousClass006.A07("Unable to find the class: ", str));
        }
    }

    public static Object createObject(String str) throws ParseException {
        try {
            try {
                return Class.forName(str).newInstance();
            } catch (Exception e) {
                throw new ParseException(AnonymousClass006.A09(e.getClass().getName(), "; Unable to create an instance of: ", str));
            }
        } catch (ClassNotFoundException unused) {
            throw new ParseException(AnonymousClass006.A07("Unable to find the class: ", str));
        }
    }

    public static Object createValue(String str, Class<?> cls) throws ParseException {
        if (String.class == cls) {
            return str;
        }
        if (Object.class == cls) {
            return createObject(str);
        }
        if (Number.class == cls) {
            return createNumber(str);
        }
        if (Date.class == cls) {
            createDate(str);
            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
        } else if (Class.class == cls) {
            return createClass(str);
        } else {
            if (File.class == cls || FileInputStream.class == cls) {
                return new File(str);
            }
            if (File[].class == cls) {
                createFiles(str);
                throw new RuntimeException("Redex: Unreachable code after no-return invoke");
            } else if (URL.class == cls) {
                return createURL(str);
            } else {
                return null;
            }
        }
    }

    public static Object createValue(String str, Object obj) throws ParseException {
        return createValue(str, (Class<?>) ((Class) obj));
    }
}
