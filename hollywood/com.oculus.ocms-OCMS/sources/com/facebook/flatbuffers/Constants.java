package com.facebook.flatbuffers;

import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class Constants {
    static final String DEFAULT_IDENTIFIER = "FLAT";
    static final int FILE_IDENTIFIER_LENGTH = 4;
    static final short NULL_ENUM = -1;
    public static final int SIZEOF_DOUBLE = 8;
    public static final int SIZEOF_FLOAT = 4;
    public static final int SIZEOF_INT = 4;
    public static final int SIZEOF_LONG = 8;
    public static final int SIZEOF_SHORT = 2;
}
