package com.oculus.video.extractor.mp4;

import com.oculus.android.exoplayer2.util.Util;

/* access modifiers changed from: package-private */
public final class FixedSampleSizeRechunker {
    private static final int MAX_SAMPLE_SIZE = 8192;

    FixedSampleSizeRechunker() {
    }

    public static final class Results {
        public final int[] flags;
        public final int maximumSize;
        public final long[] offsets;
        public final int[] sizes;
        public final long[] timestamps;

        private Results(long[] jArr, int[] iArr, int i, long[] jArr2, int[] iArr2) {
            this.offsets = jArr;
            this.sizes = iArr;
            this.maximumSize = i;
            this.timestamps = jArr2;
            this.flags = iArr2;
        }
    }

    public static Results rechunk(int i, long[] jArr, int[] iArr, long j) {
        int i2 = 8192 / i;
        int i3 = 0;
        for (int i4 : iArr) {
            i3 += Util.ceilDivide(i4, i2);
        }
        long[] jArr2 = new long[i3];
        int[] iArr2 = new int[i3];
        long[] jArr3 = new long[i3];
        int[] iArr3 = new int[i3];
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        for (int i8 = 0; i8 < iArr.length; i8++) {
            int i9 = iArr[i8];
            long j2 = jArr[i8];
            while (i9 > 0) {
                int min = Math.min(i2, i9);
                jArr2[i5] = j2;
                iArr2[i5] = i * min;
                i7 = Math.max(i7, iArr2[i5]);
                jArr3[i5] = ((long) i6) * j;
                iArr3[i5] = 1;
                j2 += (long) iArr2[i5];
                i6 += min;
                i9 -= min;
                i5++;
            }
        }
        return new Results(jArr2, iArr2, i7, jArr3, iArr3);
    }
}
