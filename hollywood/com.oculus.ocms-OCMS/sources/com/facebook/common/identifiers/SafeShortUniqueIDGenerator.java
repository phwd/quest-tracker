package com.facebook.common.identifiers;

import com.facebook.infer.annotation.Nullsafe;
import java.security.SecureRandom;

@Nullsafe(Nullsafe.Mode.LOCAL)
public final class SafeShortUniqueIDGenerator {
    public static final int ID_MAX_VALUE = 1073741824;

    public static int randomShortUID() {
        return new SecureRandom().nextInt(1073741824) + 1;
    }
}
