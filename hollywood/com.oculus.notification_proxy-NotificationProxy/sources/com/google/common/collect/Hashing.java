package com.google.common.collect;

/* access modifiers changed from: package-private */
public final class Hashing {
    private static int MAX_TABLE_SIZE = 1073741824;

    static int smear(int i) {
        return Integer.rotateLeft(i * -862048943, 15) * 461845907;
    }

    static int smearedHash(Object obj) {
        return smear(obj == null ? 0 : obj.hashCode());
    }

    static int closedTableSize(int i, double d) {
        int max = Math.max(i, 2);
        int highestOneBit = Integer.highestOneBit(max);
        if (max <= ((int) (d * ((double) highestOneBit)))) {
            return highestOneBit;
        }
        int i2 = highestOneBit << 1;
        return i2 > 0 ? i2 : MAX_TABLE_SIZE;
    }

    static boolean needsResizing(int i, int i2, double d) {
        return ((double) i) > d * ((double) i2) && i2 < MAX_TABLE_SIZE;
    }
}
