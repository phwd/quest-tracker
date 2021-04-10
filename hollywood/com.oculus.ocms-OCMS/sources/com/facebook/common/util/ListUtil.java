package com.facebook.common.util;

import com.facebook.infer.annotation.Nullsafe;
import java.util.List;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class ListUtil {
    public static <T> List<T> safeSubList(List<T> list, int i, int i2) {
        return list.subList(Math.max(0, i), Math.min(list.size(), i2));
    }

    @Nullable
    public static <T> T getFirst(@Nullable List<T> list) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    public static <T> int count(@Nullable List<T> list) {
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    public static <T> boolean safeEquals(@Nullable List<T> list, @Nullable List<T> list2) {
        if (list == null) {
            return list2 == null;
        }
        return list.equals(list2);
    }

    public static <T> boolean isEmpty(@Nullable List<T> list) {
        return list == null || list.isEmpty();
    }
}
