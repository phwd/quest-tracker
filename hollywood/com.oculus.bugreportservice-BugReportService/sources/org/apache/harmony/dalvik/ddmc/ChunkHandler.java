package org.apache.harmony.dalvik.ddmc;

import java.nio.ByteOrder;

public abstract class ChunkHandler {
    public static final int CHUNK_FAIL = type("FAIL");
    public static final ByteOrder CHUNK_ORDER = ByteOrder.BIG_ENDIAN;

    public static int type(String str) {
        if (str.length() == 4) {
            int i = 0;
            for (int i2 = 0; i2 < 4; i2++) {
                i = (i << 8) | (str.charAt(i2) & 255);
            }
            return i;
        }
        throw new IllegalArgumentException("Bad type name: " + str);
    }
}
