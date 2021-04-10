package com.facebook.qe.schema;

import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
public final class Types {
    public static final int[] ALL = {100, 101, 102, 103, 104, 105};
    public static final byte TYPE_BOOL = 101;
    public static final byte TYPE_ENUM = 105;
    public static final byte TYPE_FLOAT = 104;
    public static final byte TYPE_INT = 102;
    public static final byte TYPE_LONG = 103;
    public static final byte TYPE_STRING = 100;

    private Types() {
    }
}
