package com.facebook.common.util.redex;

public class OriginalClassName {
    public static String getName(Class<?> cls) {
        try {
            return (String) cls.getDeclaredField("__redex_internal_original_name").get(cls);
        } catch (NoSuchFieldException unused) {
            return cls.getName();
        } catch (Exception e) {
            throw new Error(e);
        }
    }

    public static String getSimpleName(Class<?> cls) {
        String name = getName(cls);
        int lastIndexOf = name.lastIndexOf(46);
        if (lastIndexOf == -1) {
            return name;
        }
        if (lastIndexOf != name.length()) {
            try {
                return name.substring(lastIndexOf + 1);
            } catch (Exception e) {
                throw new Error(e);
            }
        } else {
            throw new Error("Unexpected string " + name + " in __redex_internal_original_name");
        }
    }

    public static String getClassName(Object obj) {
        return getName(obj.getClass());
    }

    public static String getClassSimpleName(Object obj) {
        return getSimpleName(obj.getClass());
    }
}
