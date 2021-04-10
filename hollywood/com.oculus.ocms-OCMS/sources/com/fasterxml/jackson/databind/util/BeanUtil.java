package com.fasterxml.jackson.databind.util;

import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;

public class BeanUtil {
    public static String okNameForGetter(AnnotatedMethod annotatedMethod) {
        String name = annotatedMethod.getName();
        String okNameForIsGetter = okNameForIsGetter(annotatedMethod, name);
        return okNameForIsGetter == null ? okNameForRegularGetter(annotatedMethod, name) : okNameForIsGetter;
    }

    public static String okNameForRegularGetter(AnnotatedMethod annotatedMethod, String str) {
        if (!str.startsWith("get")) {
            return null;
        }
        if ("getCallbacks".equals(str)) {
            if (isCglibGetCallbacks(annotatedMethod)) {
                return null;
            }
        } else if ("getMetaClass".equals(str) && isGroovyMetaClassGetter(annotatedMethod)) {
            return null;
        }
        return manglePropertyName(str.substring(3));
    }

    public static String okNameForIsGetter(AnnotatedMethod annotatedMethod, String str) {
        if (!str.startsWith("is")) {
            return null;
        }
        Class<?> rawType = annotatedMethod.getRawType();
        if (rawType == Boolean.class || rawType == Boolean.TYPE) {
            return manglePropertyName(str.substring(2));
        }
        return null;
    }

    public static String okNameForSetter(AnnotatedMethod annotatedMethod) {
        String okNameForMutator = okNameForMutator(annotatedMethod, "set");
        if (okNameForMutator == null) {
            return null;
        }
        if (!"metaClass".equals(okNameForMutator) || !isGroovyMetaClassSetter(annotatedMethod)) {
            return okNameForMutator;
        }
        return null;
    }

    public static String okNameForMutator(AnnotatedMethod annotatedMethod, String str) {
        String name = annotatedMethod.getName();
        if (name.startsWith(str)) {
            return manglePropertyName(name.substring(str.length()));
        }
        return null;
    }

    protected static boolean isCglibGetCallbacks(AnnotatedMethod annotatedMethod) {
        Package r2;
        Class<?> rawType = annotatedMethod.getRawType();
        if (!(rawType == null || !rawType.isArray() || (r2 = rawType.getComponentType().getPackage()) == null)) {
            String name = r2.getName();
            if (name.startsWith("net.sf.cglib") || name.startsWith("org.hibernate.repackage.cglib")) {
                return true;
            }
        }
        return false;
    }

    protected static boolean isGroovyMetaClassSetter(AnnotatedMethod annotatedMethod) {
        Package r2 = annotatedMethod.getRawParameterType(0).getPackage();
        if (r2 == null || !r2.getName().startsWith("groovy.lang")) {
            return false;
        }
        return true;
    }

    protected static boolean isGroovyMetaClassGetter(AnnotatedMethod annotatedMethod) {
        Package r2;
        Class<?> rawType = annotatedMethod.getRawType();
        if (rawType == null || rawType.isArray() || (r2 = rawType.getPackage()) == null || !r2.getName().startsWith("groovy.lang")) {
            return false;
        }
        return true;
    }

    protected static String manglePropertyName(String str) {
        int length = str.length();
        StringBuilder sb = null;
        if (length == 0) {
            return null;
        }
        for (int i = 0; i < length; i++) {
            char charAt = str.charAt(i);
            char lowerCase = Character.toLowerCase(charAt);
            if (charAt == lowerCase) {
                break;
            }
            if (sb == null) {
                sb = new StringBuilder(str);
            }
            sb.setCharAt(i, lowerCase);
        }
        return sb == null ? str : sb.toString();
    }
}
