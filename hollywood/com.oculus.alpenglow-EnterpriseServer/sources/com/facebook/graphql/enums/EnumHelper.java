package com.facebook.graphql.enums;

import com.facebook.infer.annotation.Nullsafe;
import java.util.Locale;

@Nullsafe(Nullsafe.Mode.LOCAL)
public final class EnumHelper {
    public static <T extends Enum<T>> T A00(String str, T t) {
        if (!(str == null || str.length() == 0)) {
            try {
                return (T) Enum.valueOf(t.getClass(), str.toUpperCase(Locale.US));
            } catch (IllegalArgumentException unused) {
            }
        }
        return t;
    }
}
