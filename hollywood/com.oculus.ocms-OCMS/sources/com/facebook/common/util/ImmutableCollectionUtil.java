package com.facebook.common.util;

import com.facebook.infer.annotation.Nullsafe;
import com.google.common.collect.ImmutableList;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class ImmutableCollectionUtil {
    public static <T> ImmutableList<T> nullToEmpty(@Nullable ImmutableList<T> immutableList) {
        return immutableList == null ? ImmutableList.of() : immutableList;
    }
}
