package com.facebook.common.identifiers;

import com.facebook.common.time.Clock;
import com.facebook.infer.annotation.Nullsafe;
import java.util.Random;

@Nullsafe(Nullsafe.Mode.LOCAL)
public final class UniqueIdHelper {
    private static final Random sRandom = new Random();

    private UniqueIdHelper() {
    }

    public static long generate() {
        return generateWithRandomInt(sRandom.nextInt());
    }

    public static long generateWithRandomInt(int i) {
        return ((System.currentTimeMillis() << 22) | ((long) (i & 4194303))) & Clock.MAX_TIME;
    }
}
