package android.icu.impl.coll;

import java.util.Arrays;

public final class CollationWeights {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private int[] maxBytes = new int[5];
    private int middleLength;
    private int[] minBytes = new int[5];
    private int rangeCount;
    private int rangeIndex;
    private WeightRange[] ranges = new WeightRange[7];

    public void initForPrimary(boolean compressible) {
        this.middleLength = 1;
        int[] iArr = this.minBytes;
        iArr[1] = 3;
        int[] iArr2 = this.maxBytes;
        iArr2[1] = 255;
        if (compressible) {
            iArr[2] = 4;
            iArr2[2] = 254;
        } else {
            iArr[2] = 2;
            iArr2[2] = 255;
        }
        int[] iArr3 = this.minBytes;
        iArr3[3] = 2;
        int[] iArr4 = this.maxBytes;
        iArr4[3] = 255;
        iArr3[4] = 2;
        iArr4[4] = 255;
    }

    public void initForSecondary() {
        this.middleLength = 3;
        int[] iArr = this.minBytes;
        iArr[1] = 0;
        int[] iArr2 = this.maxBytes;
        iArr2[1] = 0;
        iArr[2] = 0;
        iArr2[2] = 0;
        iArr[3] = 2;
        iArr2[3] = 255;
        iArr[4] = 2;
        iArr2[4] = 255;
    }

    public void initForTertiary() {
        this.middleLength = 3;
        int[] iArr = this.minBytes;
        iArr[1] = 0;
        int[] iArr2 = this.maxBytes;
        iArr2[1] = 0;
        iArr[2] = 0;
        iArr2[2] = 0;
        iArr[3] = 2;
        iArr2[3] = 63;
        iArr[4] = 2;
        iArr2[4] = 63;
    }

    public boolean allocWeights(long lowerLimit, long upperLimit, int n) {
        if (!getWeightRanges(lowerLimit, upperLimit)) {
            return false;
        }
        while (true) {
            int minLength = this.ranges[0].length;
            if (allocWeightsInShortRanges(n, minLength)) {
                break;
            } else if (minLength == 4) {
                return false;
            } else {
                if (allocWeightsInMinLengthRanges(n, minLength)) {
                    break;
                }
                int i = 0;
                while (i < this.rangeCount && this.ranges[i].length == minLength) {
                    lengthenRange(this.ranges[i]);
                    i++;
                }
            }
        }
        this.rangeIndex = 0;
        int i2 = this.rangeCount;
        WeightRange[] weightRangeArr = this.ranges;
        if (i2 >= weightRangeArr.length) {
            return true;
        }
        weightRangeArr[i2] = null;
        return true;
    }

    public long nextWeight() {
        int i = this.rangeIndex;
        if (i >= this.rangeCount) {
            return 4294967295L;
        }
        WeightRange range = this.ranges[i];
        long weight = range.start;
        int i2 = range.count - 1;
        range.count = i2;
        if (i2 == 0) {
            this.rangeIndex++;
        } else {
            range.start = incWeight(weight, range.length);
        }
        return weight;
    }

    /* access modifiers changed from: private */
    public static final class WeightRange implements Comparable<WeightRange> {
        int count;
        long end;
        int length;
        long start;

        private WeightRange() {
        }

        public int compareTo(WeightRange other) {
            long l = this.start;
            long r = other.start;
            if (l < r) {
                return -1;
            }
            if (l > r) {
                return 1;
            }
            return 0;
        }
    }

    public static int lengthOfWeight(long weight) {
        if ((16777215 & weight) == 0) {
            return 1;
        }
        if ((65535 & weight) == 0) {
            return 2;
        }
        if ((255 & weight) == 0) {
            return 3;
        }
        return 4;
    }

    private static int getWeightTrail(long weight, int length) {
        return ((int) (weight >> ((4 - length) * 8))) & 255;
    }

    private static long setWeightTrail(long weight, int length, int trail) {
        int length2 = (4 - length) * 8;
        return ((CollationRootElements.PRIMARY_SENTINEL << length2) & weight) | (((long) trail) << length2);
    }

    private static int getWeightByte(long weight, int idx) {
        return getWeightTrail(weight, idx);
    }

    private static long setWeightByte(long weight, int idx, int b) {
        long mask;
        int idx2 = idx * 8;
        if (idx2 < 32) {
            mask = 4294967295 >> idx2;
        } else {
            mask = 0;
        }
        int idx3 = 32 - idx2;
        return (weight & (mask | (CollationRootElements.PRIMARY_SENTINEL << idx3))) | (((long) b) << idx3);
    }

    private static long truncateWeight(long weight, int length) {
        return (4294967295 << ((4 - length) * 8)) & weight;
    }

    private static long incWeightTrail(long weight, int length) {
        return (1 << ((4 - length) * 8)) + weight;
    }

    private static long decWeightTrail(long weight, int length) {
        return weight - (1 << ((4 - length) * 8));
    }

    private int countBytes(int idx) {
        return (this.maxBytes[idx] - this.minBytes[idx]) + 1;
    }

    private long incWeight(long weight, int length) {
        while (true) {
            int b = getWeightByte(weight, length);
            if (b < this.maxBytes[length]) {
                return setWeightByte(weight, length, b + 1);
            }
            weight = setWeightByte(weight, length, this.minBytes[length]);
            length--;
        }
    }

    private long incWeightByOffset(long weight, int length, int offset) {
        while (true) {
            int offset2 = offset + getWeightByte(weight, length);
            if (offset2 <= this.maxBytes[length]) {
                return setWeightByte(weight, length, offset2);
            }
            int[] iArr = this.minBytes;
            int offset3 = offset2 - iArr[length];
            weight = setWeightByte(weight, length, iArr[length] + (offset3 % countBytes(length)));
            offset = offset3 / countBytes(length);
            length--;
        }
    }

    private void lengthenRange(WeightRange range) {
        int length = range.length + 1;
        range.start = setWeightTrail(range.start, length, this.minBytes[length]);
        range.end = setWeightTrail(range.end, length, this.maxBytes[length]);
        range.count *= countBytes(length);
        range.length = length;
    }

    private boolean getWeightRanges(long lowerLimit, long upperLimit) {
        int i;
        int i2;
        boolean z;
        long weight;
        Object obj;
        WeightRange[] lower;
        int lowerLength = lengthOfWeight(lowerLimit);
        int upperLength = lengthOfWeight(upperLimit);
        if (lowerLimit >= upperLimit) {
            return false;
        }
        if (lowerLength < upperLength && lowerLimit == truncateWeight(upperLimit, lowerLength)) {
            return false;
        }
        WeightRange[] lower2 = new WeightRange[5];
        Object obj2 = null;
        WeightRange middle = new WeightRange();
        WeightRange[] upper = new WeightRange[5];
        long weight2 = lowerLimit;
        int length = lowerLength;
        while (true) {
            i = this.middleLength;
            if (length <= i) {
                break;
            }
            int trail = getWeightTrail(weight2, length);
            if (trail < this.maxBytes[length]) {
                lower2[length] = new WeightRange();
                lower = lower2;
                lower2[length].start = incWeightTrail(weight2, length);
                lower[length].end = setWeightTrail(weight2, length, this.maxBytes[length]);
                lower[length].length = length;
                lower[length].count = this.maxBytes[length] - trail;
            } else {
                lower = lower2;
            }
            weight2 = truncateWeight(weight2, length - 1);
            length--;
            lower2 = lower;
        }
        if (weight2 < 4278190080L) {
            middle.start = incWeightTrail(weight2, i);
        } else {
            middle.start = 4294967295L;
        }
        long weight3 = upperLimit;
        int length2 = upperLength;
        while (true) {
            i2 = this.middleLength;
            if (length2 <= i2) {
                break;
            }
            int trail2 = getWeightTrail(weight3, length2);
            if (trail2 > this.minBytes[length2]) {
                upper[length2] = new WeightRange();
                upper[length2].start = setWeightTrail(weight3, length2, this.minBytes[length2]);
                upper[length2].end = decWeightTrail(weight3, length2);
                upper[length2].length = length2;
                upper[length2].count = trail2 - this.minBytes[length2];
            }
            weight3 = truncateWeight(weight3, length2 - 1);
            length2--;
        }
        middle.end = decWeightTrail(weight3, i2);
        middle.length = this.middleLength;
        if (middle.end < middle.start) {
            int length3 = 4;
            while (true) {
                if (length3 <= this.middleLength) {
                    break;
                }
                if (lower2[length3] == null || upper[length3] == null || lower2[length3].count <= 0 || upper[length3].count <= 0) {
                    weight = weight3;
                    obj = obj2;
                } else {
                    long lowerEnd = lower2[length3].end;
                    long upperStart = upper[length3].start;
                    boolean merged = false;
                    if (lowerEnd > upperStart) {
                        lower2[length3].end = upper[length3].end;
                        weight = weight3;
                        lower2[length3].count = (getWeightTrail(lower2[length3].end, length3) - getWeightTrail(lower2[length3].start, length3)) + 1;
                        merged = true;
                    } else {
                        weight = weight3;
                        if (lowerEnd != upperStart && incWeight(lowerEnd, length3) == upperStart) {
                            lower2[length3].end = upper[length3].end;
                            lower2[length3].count += upper[length3].count;
                            merged = true;
                        }
                    }
                    if (merged) {
                        upper[length3].count = 0;
                        while (true) {
                            length3--;
                            if (length3 <= this.middleLength) {
                                break;
                            }
                            upper[length3] = null;
                            lower2[length3] = null;
                        }
                    } else {
                        obj = null;
                    }
                }
                length3--;
                obj2 = obj;
                weight3 = weight;
            }
        } else {
            middle.count = ((int) ((middle.end - middle.start) >> ((4 - this.middleLength) * 8))) + 1;
        }
        this.rangeCount = 0;
        if (middle.count > 0) {
            this.ranges[0] = middle;
            z = true;
            this.rangeCount = 1;
        } else {
            z = true;
        }
        int i3 = this.middleLength;
        int i4 = z ? 1 : 0;
        int i5 = z ? 1 : 0;
        int i6 = z ? 1 : 0;
        for (int length4 = i3 + i4; length4 <= 4; length4++) {
            if (upper[length4] != null && upper[length4].count > 0) {
                WeightRange[] weightRangeArr = this.ranges;
                int i7 = this.rangeCount;
                this.rangeCount = i7 + 1;
                weightRangeArr[i7] = upper[length4];
            }
            if (lower2[length4] != null && lower2[length4].count > 0) {
                WeightRange[] weightRangeArr2 = this.ranges;
                int i8 = this.rangeCount;
                this.rangeCount = i8 + 1;
                weightRangeArr2[i8] = lower2[length4];
            }
        }
        if (this.rangeCount > 0) {
            return z;
        }
        return false;
    }

    private boolean allocWeightsInShortRanges(int n, int minLength) {
        int i = 0;
        while (i < this.rangeCount && this.ranges[i].length <= minLength + 1) {
            if (n <= this.ranges[i].count) {
                if (this.ranges[i].length > minLength) {
                    this.ranges[i].count = n;
                }
                this.rangeCount = i + 1;
                int i2 = this.rangeCount;
                if (i2 > 1) {
                    Arrays.sort(this.ranges, 0, i2);
                }
                return true;
            }
            n -= this.ranges[i].count;
            i++;
        }
        return false;
    }

    private boolean allocWeightsInMinLengthRanges(int n, int minLength) {
        int count = 0;
        int minLengthRangeCount = 0;
        while (minLengthRangeCount < this.rangeCount && this.ranges[minLengthRangeCount].length == minLength) {
            count += this.ranges[minLengthRangeCount].count;
            minLengthRangeCount++;
        }
        int nextCountBytes = countBytes(minLength + 1);
        if (n > count * nextCountBytes) {
            return false;
        }
        long start = this.ranges[0].start;
        long end = this.ranges[0].end;
        for (int i = 1; i < minLengthRangeCount; i++) {
            if (this.ranges[i].start < start) {
                start = this.ranges[i].start;
            }
            if (this.ranges[i].end > end) {
                end = this.ranges[i].end;
            }
        }
        int count2 = (n - count) / (nextCountBytes - 1);
        int count1 = count - count2;
        if (count2 == 0 || (count2 * nextCountBytes) + count1 < n) {
            count2++;
            count1--;
        }
        WeightRange[] weightRangeArr = this.ranges;
        weightRangeArr[0].start = start;
        if (count1 == 0) {
            weightRangeArr[0].end = end;
            weightRangeArr[0].count = count;
            lengthenRange(weightRangeArr[0]);
            this.rangeCount = 1;
            return true;
        }
        weightRangeArr[0].end = incWeightByOffset(start, minLength, count1 - 1);
        WeightRange[] weightRangeArr2 = this.ranges;
        weightRangeArr2[0].count = count1;
        if (weightRangeArr2[1] == null) {
            weightRangeArr2[1] = new WeightRange();
        }
        WeightRange[] weightRangeArr3 = this.ranges;
        weightRangeArr3[1].start = incWeight(weightRangeArr3[0].end, minLength);
        WeightRange[] weightRangeArr4 = this.ranges;
        weightRangeArr4[1].end = end;
        weightRangeArr4[1].length = minLength;
        weightRangeArr4[1].count = count2;
        lengthenRange(weightRangeArr4[1]);
        this.rangeCount = 2;
        return true;
    }
}
