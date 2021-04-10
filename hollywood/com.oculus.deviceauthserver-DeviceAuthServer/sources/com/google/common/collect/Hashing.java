package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.primitives.Ints;
import javax.annotation.Nullable;

/* access modifiers changed from: package-private */
@GwtCompatible
public final class Hashing {
    private static final int C1 = -862048943;
    private static final int C2 = 461845907;
    private static int MAX_TABLE_SIZE = Ints.MAX_POWER_OF_TWO;

    private Hashing() {
    }

    static int smear(int hashCode) {
        return Integer.rotateLeft(C1 * hashCode, 15) * C2;
    }

    static int smearedHash(@Nullable Object o) {
        return smear(o == null ? 0 : o.hashCode());
    }

    static int closedTableSize(int expectedEntries, double loadFactor) {
        int expectedEntries2 = Math.max(expectedEntries, 2);
        int tableSize = Integer.highestOneBit(expectedEntries2);
        if (expectedEntries2 <= ((int) (((double) tableSize) * loadFactor))) {
            return tableSize;
        }
        int tableSize2 = tableSize << 1;
        return tableSize2 > 0 ? tableSize2 : MAX_TABLE_SIZE;
    }

    static boolean needsResizing(int size, int tableSize, double loadFactor) {
        return ((double) size) > ((double) tableSize) * loadFactor && tableSize < MAX_TABLE_SIZE;
    }
}
