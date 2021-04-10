package com.facebook.quicklog.identifiers;

import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class Utils {
    public static short getInternalEventId(int i) {
        return (short) (i & 65535);
    }

    public static short getModuleId(int i) {
        return (short) (i >> 16);
    }

    public static int makeEventId(short s, short s2) {
        return (s << 16) | s2;
    }
}
