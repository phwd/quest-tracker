package com.google.common.base;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.CharMatcher;
import java.util.BitSet;

/* access modifiers changed from: package-private */
@GwtIncompatible("no precomputation is done in GWT")
public final class SmallCharMatcher extends CharMatcher.FastMatcher {
    private static final int C1 = -862048943;
    private static final int C2 = 461845907;
    private static final double DESIRED_LOAD_FACTOR = 0.5d;
    static final int MAX_SIZE = 1023;
    private final boolean containsZero;
    private final long filter;
    private final char[] table;

    private SmallCharMatcher(char[] table2, long filter2, boolean containsZero2, String description) {
        super(description);
        this.table = table2;
        this.filter = filter2;
        this.containsZero = containsZero2;
    }

    static int smear(int hashCode) {
        return Integer.rotateLeft(C1 * hashCode, 15) * C2;
    }

    private boolean checkFilter(int c) {
        return 1 == ((this.filter >> c) & 1);
    }

    @VisibleForTesting
    static int chooseTableSize(int setSize) {
        if (setSize == 1) {
            return 2;
        }
        int tableSize = Integer.highestOneBit(setSize - 1) << 1;
        while (((double) tableSize) * DESIRED_LOAD_FACTOR < ((double) setSize)) {
            tableSize <<= 1;
        }
        return tableSize;
    }

    static CharMatcher from(BitSet chars, String description) {
        long filter2 = 0;
        int size = chars.cardinality();
        boolean containsZero2 = chars.get(0);
        char[] table2 = new char[chooseTableSize(size)];
        int mask = table2.length - 1;
        int c = chars.nextSetBit(0);
        while (c != -1) {
            long filter3 = (1 << c) | filter2;
            int index = smear(c) & mask;
            while (table2[index] != 0) {
                index = (index + 1) & mask;
            }
            table2[index] = (char) c;
            c = chars.nextSetBit(c + 1);
            filter2 = filter3;
        }
        return new SmallCharMatcher(table2, filter2, containsZero2, description);
    }

    @Override // com.google.common.base.CharMatcher
    public boolean matches(char c) {
        if (c == 0) {
            return this.containsZero;
        }
        if (!checkFilter(c)) {
            return false;
        }
        int mask = this.table.length - 1;
        int startingIndex = smear(c) & mask;
        int index = startingIndex;
        do {
            char[] cArr = this.table;
            if (cArr[index] == 0) {
                return false;
            }
            if (cArr[index] == c) {
                return true;
            }
            index = (index + 1) & mask;
        } while (index != startingIndex);
        return false;
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.base.CharMatcher
    public void setBits(BitSet table2) {
        if (this.containsZero) {
            table2.set(0);
        }
        char[] cArr = this.table;
        for (char c : cArr) {
            if (c != 0) {
                table2.set(c);
            }
        }
    }
}
