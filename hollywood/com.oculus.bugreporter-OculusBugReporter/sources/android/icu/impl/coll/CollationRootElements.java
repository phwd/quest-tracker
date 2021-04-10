package android.icu.impl.coll;

import android.support.v4.view.MotionEventCompat;

public final class CollationRootElements {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static final int IX_COMMON_SEC_AND_TER_CE = 3;
    static final int IX_COUNT = 5;
    static final int IX_FIRST_PRIMARY_INDEX = 2;
    static final int IX_FIRST_SECONDARY_INDEX = 1;
    public static final int IX_FIRST_TERTIARY_INDEX = 0;
    static final int IX_SEC_TER_BOUNDARIES = 4;
    public static final long PRIMARY_SENTINEL = 4294967040L;
    public static final int PRIMARY_STEP_MASK = 127;
    public static final int SEC_TER_DELTA_FLAG = 128;
    private long[] elements;

    public CollationRootElements(long[] rootElements) {
        this.elements = rootElements;
    }

    public int getTertiaryBoundary() {
        return (((int) this.elements[4]) << 8) & MotionEventCompat.ACTION_POINTER_INDEX_MASK;
    }

    /* access modifiers changed from: package-private */
    public long getFirstTertiaryCE() {
        long[] jArr = this.elements;
        return jArr[(int) jArr[0]] & -129;
    }

    /* access modifiers changed from: package-private */
    public long getLastTertiaryCE() {
        long[] jArr = this.elements;
        return jArr[((int) jArr[1]) - 1] & -129;
    }

    public int getLastCommonSecondary() {
        return (((int) this.elements[4]) >> 16) & MotionEventCompat.ACTION_POINTER_INDEX_MASK;
    }

    public int getSecondaryBoundary() {
        return (((int) this.elements[4]) >> 8) & MotionEventCompat.ACTION_POINTER_INDEX_MASK;
    }

    /* access modifiers changed from: package-private */
    public long getFirstSecondaryCE() {
        long[] jArr = this.elements;
        return jArr[(int) jArr[1]] & -129;
    }

    /* access modifiers changed from: package-private */
    public long getLastSecondaryCE() {
        long[] jArr = this.elements;
        return jArr[((int) jArr[2]) - 1] & -129;
    }

    /* access modifiers changed from: package-private */
    public long getFirstPrimary() {
        long[] jArr = this.elements;
        return jArr[(int) jArr[2]];
    }

    /* access modifiers changed from: package-private */
    public long getFirstPrimaryCE() {
        return Collation.makeCE(getFirstPrimary());
    }

    /* access modifiers changed from: package-private */
    public long lastCEWithPrimaryBefore(long p) {
        long secTer;
        long p2;
        long p3;
        if (p == 0) {
            return 0;
        }
        int index = findP(p);
        long[] jArr = this.elements;
        long q = jArr[index];
        if (p == (q & PRIMARY_SENTINEL)) {
            secTer = jArr[index - 1];
            if ((secTer & 128) == 0) {
                p2 = secTer & PRIMARY_SENTINEL;
                secTer = 83887360;
            } else {
                int index2 = index - 2;
                while (true) {
                    p3 = this.elements[index2];
                    if ((p3 & 128) == 0) {
                        break;
                    }
                    index2--;
                }
                p2 = p3 & PRIMARY_SENTINEL;
            }
        } else {
            long p4 = PRIMARY_SENTINEL & q;
            secTer = 83887360;
            while (true) {
                index++;
                long q2 = this.elements[index];
                if ((q2 & 128) == 0) {
                    break;
                }
                secTer = q2;
            }
            p2 = p4;
        }
        return (p2 << 32) | (-129 & secTer);
    }

    /* access modifiers changed from: package-private */
    public long firstCEWithPrimaryAtLeast(long p) {
        if (p == 0) {
            return 0;
        }
        int index = findP(p);
        if (p != (this.elements[index] & PRIMARY_SENTINEL)) {
            do {
                index++;
                p = this.elements[index];
            } while ((128 & p) != 0);
        }
        return (p << 32) | 83887360;
    }

    /* access modifiers changed from: package-private */
    public long getPrimaryBefore(long p, boolean isCompressible) {
        int step;
        long p2;
        int index = findPrimary(p);
        long[] jArr = this.elements;
        long q = jArr[index];
        if (p == (q & PRIMARY_SENTINEL)) {
            step = ((int) q) & 127;
            if (step == 0) {
                do {
                    index--;
                    p2 = this.elements[index];
                } while ((128 & p2) != 0);
                return PRIMARY_SENTINEL & p2;
            }
        } else {
            step = ((int) jArr[index + 1]) & 127;
        }
        if ((65535 & p) == 0) {
            return Collation.decTwoBytePrimaryByOneStep(p, isCompressible, step);
        }
        return Collation.decThreeBytePrimaryByOneStep(p, isCompressible, step);
    }

    /* access modifiers changed from: package-private */
    public int getSecondaryBefore(long p, int s) {
        int previousSec;
        int index;
        int sec;
        if (p == 0) {
            long[] jArr = this.elements;
            index = (int) jArr[1];
            previousSec = 0;
            sec = (int) (jArr[index] >> 16);
        } else {
            index = 1 + findPrimary(p);
            previousSec = 256;
            sec = ((int) getFirstSecTerForPrimary(index)) >>> 16;
        }
        while (s > sec) {
            previousSec = sec;
            sec = (int) (this.elements[index] >> 16);
            index++;
        }
        return previousSec;
    }

    /* access modifiers changed from: package-private */
    public int getTertiaryBefore(long p, int s, int t) {
        long secTer;
        int previousTer;
        int index;
        if (p == 0) {
            if (s == 0) {
                index = (int) this.elements[0];
                previousTer = 0;
            } else {
                index = (int) this.elements[1];
                previousTer = 256;
            }
            secTer = this.elements[index] & -129;
        } else {
            index = findPrimary(p) + 1;
            previousTer = 256;
            secTer = getFirstSecTerForPrimary(index);
        }
        long st = (((long) s) << 16) | ((long) t);
        while (st > secTer) {
            if (((int) (secTer >> 16)) == s) {
                previousTer = (int) secTer;
            }
            secTer = this.elements[index] & -129;
            index++;
        }
        return 65535 & previousTer;
    }

    /* access modifiers changed from: package-private */
    public int findPrimary(long p) {
        return findP(p);
    }

    /* access modifiers changed from: package-private */
    public long getPrimaryAfter(long p, int index, boolean isCompressible) {
        int step;
        int index2 = index + 1;
        long q = this.elements[index2];
        if ((q & 128) != 0 || (step = ((int) q) & 127) == 0) {
            while ((q & 128) != 0) {
                index2++;
                q = this.elements[index2];
            }
            return q;
        } else if ((65535 & p) == 0) {
            return Collation.incTwoBytePrimaryByOffset(p, isCompressible, step);
        } else {
            return Collation.incThreeBytePrimaryByOffset(p, isCompressible, step);
        }
    }

    /* access modifiers changed from: package-private */
    public int getSecondaryAfter(int index, int s) {
        int secLimit;
        long secTer;
        if (index == 0) {
            long[] jArr = this.elements;
            index = (int) jArr[1];
            secTer = jArr[index];
            secLimit = 65536;
        } else {
            secTer = getFirstSecTerForPrimary(index + 1);
            secLimit = getSecondaryBoundary();
        }
        do {
            int sec = (int) (secTer >> 16);
            if (sec > s) {
                return sec;
            }
            index++;
            secTer = this.elements[index];
        } while ((128 & secTer) != 0);
        return secLimit;
    }

    /* access modifiers changed from: package-private */
    public int getTertiaryAfter(int index, int s, int t) {
        long secTer;
        int terLimit;
        if (index == 0) {
            if (s == 0) {
                index = (int) this.elements[0];
                terLimit = 16384;
            } else {
                index = (int) this.elements[1];
                terLimit = getTertiaryBoundary();
            }
            secTer = this.elements[index] & -129;
        } else {
            secTer = getFirstSecTerForPrimary(index + 1);
            terLimit = getTertiaryBoundary();
        }
        long st = ((((long) s) & 4294967295L) << 16) | ((long) t);
        while (secTer <= st) {
            index++;
            long secTer2 = this.elements[index];
            if ((128 & secTer2) == 0 || (secTer2 >> 16) > ((long) s)) {
                return terLimit;
            }
            secTer = secTer2 & -129;
        }
        return ((int) secTer) & 65535;
    }

    private long getFirstSecTerForPrimary(int index) {
        long secTer = this.elements[index];
        if ((128 & secTer) == 0) {
            return 83887360;
        }
        long secTer2 = secTer & -129;
        if (secTer2 > 83887360) {
            return 83887360;
        }
        return secTer2;
    }

    private int findP(long p) {
        long[] jArr = this.elements;
        int start = (int) jArr[2];
        int limit = jArr.length - 1;
        while (start + 1 < limit) {
            int i = (int) ((((long) start) + ((long) limit)) / 2);
            long q = this.elements[i];
            if ((q & 128) != 0) {
                int j = i + 1;
                while (true) {
                    if (j == limit) {
                        break;
                    }
                    q = this.elements[j];
                    if ((q & 128) == 0) {
                        i = j;
                        break;
                    }
                    j++;
                }
                if ((q & 128) != 0) {
                    int j2 = i - 1;
                    while (true) {
                        if (j2 == start) {
                            break;
                        }
                        q = this.elements[j2];
                        if ((q & 128) == 0) {
                            i = j2;
                            break;
                        }
                        j2--;
                    }
                    if ((128 & q) != 0) {
                        break;
                    }
                }
            }
            if (p < (PRIMARY_SENTINEL & q)) {
                limit = i;
            } else {
                start = i;
            }
        }
        return start;
    }

    private static boolean isEndOfPrimaryRange(long q) {
        return (128 & q) == 0 && (127 & q) != 0;
    }
}
