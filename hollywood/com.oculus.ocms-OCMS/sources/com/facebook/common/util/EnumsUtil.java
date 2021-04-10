package com.facebook.common.util;

import androidx.annotation.Nullable;
import com.google.common.collect.Iterables;
import java.lang.reflect.Array;
import java.util.Arrays;

public class EnumsUtil {
    public static boolean isAlphabeticallyOrdered(Enum[] enumArr) {
        int length = enumArr.length;
        Enum r3 = null;
        int i = 0;
        while (i < length) {
            Enum r4 = enumArr[i];
            if (r3 != null && r4.name().compareTo(r3.name()) < 0) {
                return false;
            }
            i++;
            r3 = r4;
        }
        return true;
    }

    public static <T extends Enum<T>> T valueOfIgnoreCase(Class<T> cls, String str, T t) {
        T t2 = (T) valueOfIgnoreCaseOrNull(cls, str);
        return t2 == null ? t : t2;
    }

    @Nullable
    public static <T extends Enum<T>> T valueOfIgnoreCaseOrNull(Class<T> cls, String str) {
        T[] enumConstants = cls.getEnumConstants();
        for (T t : enumConstants) {
            if (t.name().equalsIgnoreCase(str)) {
                return t;
            }
        }
        return null;
    }

    public static <T extends Enum<T>> String[] toStringArray(Class<T> cls) {
        T[] enumConstants = cls.getEnumConstants();
        String[] strArr = new String[enumConstants.length];
        for (int i = 0; i < enumConstants.length; i++) {
            strArr[i] = enumConstants[i].toString();
        }
        return strArr;
    }

    public static <T extends Enum<T>> T[] fromStrings(Class<T> cls, String[] strArr, T t) {
        return (T[]) fromStrings(cls, Arrays.asList(strArr), t);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r0v2, resolved type: T extends java.lang.Enum<T>[] */
    /* JADX WARN: Multi-variable type inference failed */
    public static <T extends Enum<T>> T[] fromStrings(Class<T> cls, Iterable<String> iterable, T t) {
        T[] tArr = (T[]) ((Enum[]) Array.newInstance((Class<?>) cls, Iterables.size(iterable)));
        int i = 0;
        for (String str : iterable) {
            tArr[i] = valueOfIgnoreCase(cls, str, t);
            i++;
        }
        return tArr;
    }
}
