package android.icu.impl.coll;

public final class Collation {
    static long ceFromCE32(int i) {
        int i2 = i & 255;
        if (i2 < 192) {
            return (((long) (-65536 & i)) << 32) | (((long) (i & 65280)) << 16) | ((long) (i2 << 8));
        }
        int i3 = i - i2;
        return (i2 & 15) == 1 ? (((long) i3) << 32) | 83887360 : ((long) i3) & 4294967295L;
    }

    static long ceFromLongPrimaryCE32(int i) {
        return (((long) (i & -256)) << 32) | 83887360;
    }

    static long ceFromLongSecondaryCE32(int i) {
        return ((long) i) & 4294967040L;
    }

    static long ceFromSimpleCE32(int i) {
        return (((long) (-65536 & i)) << 32) | (((long) (65280 & i)) << 16) | ((long) ((i & 255) << 8));
    }

    static char digitFromCE32(int i) {
        return (char) ((i >> 8) & 15);
    }

    static int indexFromCE32(int i) {
        return i >>> 13;
    }

    static boolean isSpecialCE32(int i) {
        return (i & 255) >= 192;
    }

    static long latinCE0FromCE32(int i) {
        return (((long) (-16777216 & i)) << 32) | 83886080 | ((long) ((i & 16711680) >> 8));
    }

    static long latinCE1FromCE32(int i) {
        return ((((long) i) & 65280) << 16) | 1280;
    }

    static int lengthFromCE32(int i) {
        return (i >> 8) & 31;
    }

    public static long makeCE(long j) {
        return (j << 32) | 83887360;
    }

    static long primaryFromLongPrimaryCE32(int i) {
        return ((long) i) & 4294967040L;
    }

    static int tagFromCE32(int i) {
        return i & 15;
    }

    static boolean hasCE32Tag(int i, int i2) {
        return isSpecialCE32(i) && tagFromCE32(i) == i2;
    }

    static boolean isLongPrimaryCE32(int i) {
        return hasCE32Tag(i, 1);
    }

    static boolean isSelfContainedCE32(int i) {
        if (!isSpecialCE32(i) || tagFromCE32(i) == 1 || tagFromCE32(i) == 2 || tagFromCE32(i) == 4) {
            return true;
        }
        return false;
    }

    static boolean isPrefixCE32(int i) {
        return hasCE32Tag(i, 8);
    }

    static boolean isContractionCE32(int i) {
        return hasCE32Tag(i, 9);
    }

    public static long incThreeBytePrimaryByOffset(long j, boolean z, int i) {
        int i2;
        long j2;
        int i3 = i + ((((int) (j >> 8)) & 255) - 2);
        long j3 = (long) (((i3 % 254) + 2) << 8);
        int i4 = i3 / 254;
        if (z) {
            int i5 = i4 + ((((int) (j >> 16)) & 255) - 4);
            j2 = j3 | ((long) (((i5 % 251) + 4) << 16));
            i2 = i5 / 251;
        } else {
            int i6 = i4 + ((((int) (j >> 16)) & 255) - 2);
            j2 = j3 | ((long) (((i6 % 254) + 2) << 16));
            i2 = i6 / 254;
        }
        return ((j & 4278190080L) + (((long) i2) << 24)) | j2;
    }

    static long getThreeBytePrimaryForOffsetData(int i, long j) {
        long j2 = j >>> 32;
        int i2 = (int) j;
        return incThreeBytePrimaryByOffset(j2, (i2 & 128) != 0, (i - (i2 >> 8)) * (i2 & 127));
    }

    static long unassignedPrimaryFromCodePoint(int i) {
        int i2 = i + 1;
        int i3 = i2 / 18;
        return ((long) (((i2 % 18) * 14) + 2)) | ((long) (((i3 % 254) + 2) << 8)) | ((long) ((((i3 / 254) % 251) + 4) << 16)) | 4261412864L;
    }

    static long unassignedCEFromCodePoint(int i) {
        return makeCE(unassignedPrimaryFromCodePoint(i));
    }
}
