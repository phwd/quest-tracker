package com.facebook.common.util;

import com.facebook.infer.annotation.Nullsafe;
import com.google.common.base.Optional;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class Optionals {
    public static boolean isNullOrAbsent(@Nullable Optional optional) {
        return optional == null || !optional.isPresent();
    }
}
