package java.util;

import java.lang.reflect.Array;

/* access modifiers changed from: package-private */
public class TimSort<T> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final int INITIAL_TMP_STORAGE_LENGTH = 256;
    private static final int MIN_GALLOP = 7;
    private static final int MIN_MERGE = 32;
    private final T[] a;
    private final Comparator<? super T> c;
    private int minGallop = 7;
    private final int[] runBase;
    private final int[] runLen;
    private int stackSize = 0;
    private T[] tmp;
    private int tmpBase;
    private int tmpLen;

    private TimSort(T[] a2, Comparator<? super T> c2, T[] work, int workBase, int workLen) {
        int stackLen;
        this.a = a2;
        this.c = c2;
        int len = a2.length;
        int tlen = len < 512 ? len >>> 1 : 256;
        if (work == null || workLen < tlen || workBase + tlen > work.length) {
            this.tmp = (T[]) ((Object[]) Array.newInstance(a2.getClass().getComponentType(), tlen));
            this.tmpBase = 0;
            this.tmpLen = tlen;
        } else {
            this.tmp = work;
            this.tmpBase = workBase;
            this.tmpLen = workLen;
        }
        if (len < 120) {
            stackLen = 5;
        } else if (len < 1542) {
            stackLen = 10;
        } else {
            stackLen = len < 119151 ? 24 : 49;
        }
        this.runBase = new int[stackLen];
        this.runLen = new int[stackLen];
    }

    static <T> void sort(T[] a2, int lo, int hi, Comparator<? super T> c2, T[] work, int workBase, int workLen) {
        int nRemaining = hi - lo;
        if (nRemaining >= 2) {
            if (nRemaining < 32) {
                binarySort(a2, lo, hi, lo + countRunAndMakeAscending(a2, lo, hi, c2), c2);
                return;
            }
            TimSort<T> ts = new TimSort<>(a2, c2, work, workBase, workLen);
            int minRun = minRunLength(nRemaining);
            do {
                int runLen2 = countRunAndMakeAscending(a2, lo, hi, c2);
                if (runLen2 < minRun) {
                    int force = nRemaining <= minRun ? nRemaining : minRun;
                    binarySort(a2, lo, lo + force, lo + runLen2, c2);
                    runLen2 = force;
                }
                ts.pushRun(lo, runLen2);
                ts.mergeCollapse();
                lo += runLen2;
                nRemaining -= runLen2;
            } while (nRemaining != 0);
            ts.mergeForceCollapse();
        }
    }

    private static <T> void binarySort(T[] a2, int lo, int hi, int start, Comparator<? super T> c2) {
        if (start == lo) {
            start++;
        }
        while (start < hi) {
            T pivot = a2[start];
            int left = lo;
            int right = start;
            while (left < right) {
                int mid = (left + right) >>> 1;
                if (c2.compare(pivot, a2[mid]) < 0) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }
            int n = start - left;
            if (n != 1) {
                if (n != 2) {
                    System.arraycopy(a2, left, a2, left + 1, n);
                    a2[left] = pivot;
                    start++;
                } else {
                    a2[left + 2] = a2[left + 1];
                }
            }
            a2[left + 1] = a2[left];
            a2[left] = pivot;
            start++;
        }
    }

    private static <T> int countRunAndMakeAscending(T[] a2, int lo, int hi, Comparator<? super T> c2) {
        int runHi = lo + 1;
        if (runHi == hi) {
            return 1;
        }
        int runHi2 = runHi + 1;
        if (c2.compare(a2[runHi], a2[lo]) < 0) {
            while (runHi2 < hi && c2.compare(a2[runHi2], a2[runHi2 - 1]) < 0) {
                runHi2++;
            }
            reverseRange(a2, lo, runHi2);
        } else {
            while (runHi2 < hi && c2.compare(a2[runHi2], a2[runHi2 - 1]) >= 0) {
                runHi2++;
            }
        }
        return runHi2 - lo;
    }

    private static void reverseRange(Object[] a2, int hi, int hi2) {
        int hi3 = hi2 - 1;
        while (hi < hi3) {
            Object t = a2[hi];
            a2[hi] = a2[hi3];
            a2[hi3] = t;
            hi3--;
            hi++;
        }
    }

    private static int minRunLength(int n) {
        int r = 0;
        while (n >= 32) {
            r |= n & 1;
            n >>= 1;
        }
        return n + r;
    }

    private void pushRun(int runBase2, int runLen2) {
        int[] iArr = this.runBase;
        int i = this.stackSize;
        iArr[i] = runBase2;
        this.runLen[i] = runLen2;
        this.stackSize = i + 1;
    }

    private void mergeCollapse() {
        while (true) {
            int i = this.stackSize;
            if (i > 1) {
                int n = i - 2;
                if (n > 0) {
                    int[] iArr = this.runLen;
                    if (iArr[n - 1] <= iArr[n] + iArr[n + 1]) {
                        if (iArr[n - 1] < iArr[n + 1]) {
                            n--;
                        }
                        mergeAt(n);
                    }
                }
                int[] iArr2 = this.runLen;
                if (iArr2[n] <= iArr2[n + 1]) {
                    mergeAt(n);
                } else {
                    return;
                }
            } else {
                return;
            }
        }
    }

    private void mergeForceCollapse() {
        while (true) {
            int i = this.stackSize;
            if (i > 1) {
                int n = i - 2;
                if (n > 0) {
                    int[] iArr = this.runLen;
                    if (iArr[n - 1] < iArr[n + 1]) {
                        n--;
                    }
                }
                mergeAt(n);
            } else {
                return;
            }
        }
    }

    private void mergeAt(int i) {
        int[] iArr = this.runBase;
        int base1 = iArr[i];
        int[] iArr2 = this.runLen;
        int len1 = iArr2[i];
        int base2 = iArr[i + 1];
        int len2 = iArr2[i + 1];
        iArr2[i] = len1 + len2;
        if (i == this.stackSize - 3) {
            iArr[i + 1] = iArr[i + 2];
            iArr2[i + 1] = iArr2[i + 2];
        }
        this.stackSize--;
        T[] tArr = this.a;
        int k = gallopRight(tArr[base2], tArr, base1, len1, 0, this.c);
        int base12 = base1 + k;
        int len12 = len1 - k;
        if (len12 != 0) {
            T[] tArr2 = this.a;
            int len22 = gallopLeft(tArr2[(base12 + len12) - 1], tArr2, base2, len2, len2 - 1, this.c);
            if (len22 != 0) {
                if (len12 <= len22) {
                    mergeLo(base12, len12, base2, len22);
                } else {
                    mergeHi(base12, len12, base2, len22);
                }
            }
        }
    }

    private static <T> int gallopLeft(T key, T[] a2, int base, int len, int hint, Comparator<? super T> c2) {
        int ofs;
        int lastOfs;
        int lastOfs2 = 0;
        int ofs2 = 1;
        if (c2.compare(key, a2[base + hint]) > 0) {
            int maxOfs = len - hint;
            while (ofs2 < maxOfs && c2.compare(key, a2[base + hint + ofs2]) > 0) {
                lastOfs2 = ofs2;
                ofs2 = (ofs2 << 1) + 1;
                if (ofs2 <= 0) {
                    ofs2 = maxOfs;
                }
            }
            if (ofs2 > maxOfs) {
                ofs2 = maxOfs;
            }
            lastOfs = lastOfs2 + hint;
            ofs = ofs2 + hint;
        } else {
            int maxOfs2 = hint + 1;
            while (ofs2 < maxOfs2 && c2.compare(key, a2[(base + hint) - ofs2]) <= 0) {
                lastOfs2 = ofs2;
                ofs2 = (ofs2 << 1) + 1;
                if (ofs2 <= 0) {
                    ofs2 = maxOfs2;
                }
            }
            if (ofs2 > maxOfs2) {
                ofs2 = maxOfs2;
            }
            lastOfs = hint - ofs2;
            ofs = hint - lastOfs2;
        }
        int lastOfs3 = lastOfs + 1;
        while (lastOfs3 < ofs) {
            int m = ((ofs - lastOfs3) >>> 1) + lastOfs3;
            if (c2.compare(key, a2[base + m]) > 0) {
                lastOfs3 = m + 1;
            } else {
                ofs = m;
            }
        }
        return ofs;
    }

    private static <T> int gallopRight(T key, T[] a2, int base, int len, int hint, Comparator<? super T> c2) {
        int lastOfs;
        int ofs;
        int ofs2 = 1;
        int lastOfs2 = 0;
        if (c2.compare(key, a2[base + hint]) < 0) {
            int maxOfs = hint + 1;
            while (ofs2 < maxOfs && c2.compare(key, a2[(base + hint) - ofs2]) < 0) {
                lastOfs2 = ofs2;
                ofs2 = (ofs2 << 1) + 1;
                if (ofs2 <= 0) {
                    ofs2 = maxOfs;
                }
            }
            if (ofs2 > maxOfs) {
                ofs2 = maxOfs;
            }
            lastOfs = hint - ofs2;
            ofs = hint - lastOfs2;
        } else {
            int maxOfs2 = len - hint;
            while (ofs2 < maxOfs2 && c2.compare(key, a2[base + hint + ofs2]) >= 0) {
                lastOfs2 = ofs2;
                ofs2 = (ofs2 << 1) + 1;
                if (ofs2 <= 0) {
                    ofs2 = maxOfs2;
                }
            }
            if (ofs2 > maxOfs2) {
                ofs2 = maxOfs2;
            }
            lastOfs = lastOfs2 + hint;
            ofs = ofs2 + hint;
        }
        int lastOfs3 = lastOfs + 1;
        while (lastOfs3 < ofs) {
            int m = ((ofs - lastOfs3) >>> 1) + lastOfs3;
            if (c2.compare(key, a2[base + m]) < 0) {
                ofs = m;
            } else {
                lastOfs3 = m + 1;
            }
        }
        return ofs;
    }

    /* JADX INFO: Multiple debug info for r1v11 int: [D('count2' int), D('minGallop' int)] */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x007d, code lost:
        r14 = r2;
        r18 = r4;
        r15 = r6;
        r12 = r1;
        r13 = r3;
        r6 = r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0088, code lost:
        r6 = gallopRight(r7[r15], r8, r14, r12, 0, r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0095, code lost:
        if (r6 == 0) goto L_0x00ae;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0097, code lost:
        java.lang.System.arraycopy(r8, r14, r7, r6, r6);
        r1 = r6 + r6;
        r2 = r14 + r6;
        r3 = r12 - r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x00a1, code lost:
        if (r3 > 1) goto L_0x00ab;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x00a3, code lost:
        r14 = r2;
        r12 = r3;
        r6 = r15;
        r10 = r18;
        r9 = 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x00ab, code lost:
        r14 = r2;
        r12 = r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x00ae, code lost:
        r1 = r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x00af, code lost:
        r10 = r1 + 1;
        r5 = r15 + 1;
        r7[r1] = r7[r15];
        r13 = r13 - 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x00b9, code lost:
        if (r13 != 0) goto L_0x00c1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x00bb, code lost:
        r6 = r5;
        r1 = r10;
        r10 = r18;
        r9 = 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x00c1, code lost:
        r1 = gallopLeft(r8[r14], r7, r5, r13, 0, r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x00cf, code lost:
        if (r1 == 0) goto L_0x00e4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x00d1, code lost:
        java.lang.System.arraycopy(r7, r5, r7, r10, r1);
        r2 = r10 + r1;
        r6 = r5 + r1;
        r3 = r13 - r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x00da, code lost:
        if (r3 != 0) goto L_0x00e2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x00dc, code lost:
        r1 = r2;
        r13 = r3;
        r10 = r18;
        r9 = 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x00e2, code lost:
        r13 = r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x00e4, code lost:
        r6 = r5;
        r2 = r10;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x00e6, code lost:
        r3 = r2 + 1;
        r4 = r14 + 1;
        r7[r2] = r8[r14];
        r12 = r12 - 1;
        r9 = 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x00f1, code lost:
        if (r12 != 1) goto L_0x011b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x00f3, code lost:
        r1 = r3;
        r14 = r4;
        r10 = r18;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x011b, code lost:
        r18 = r18 - 1;
        r10 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x011f, code lost:
        if (r6 < 7) goto L_0x0123;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x0121, code lost:
        r5 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x0123, code lost:
        r5 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x0124, code lost:
        if (r1 < 7) goto L_0x0127;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x0126, code lost:
        r10 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x0129, code lost:
        if ((r5 | r10) != false) goto L_0x013c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x012b, code lost:
        if (r18 >= 0) goto L_0x012f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x012d, code lost:
        r18 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x013c, code lost:
        r14 = r4;
        r15 = r6;
        r6 = r3;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void mergeLo(int r20, int r21, int r22, int r23) {
        /*
        // Method dump skipped, instructions count: 333
        */
        throw new UnsupportedOperationException("Method not decompiled: java.util.TimSort.mergeLo(int, int, int, int):void");
    }

    /* JADX INFO: Multiple debug info for r2v19 int: [D('count2' int), D('minGallop' int)] */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0089, code lost:
        r11 = r3;
        r15 = r4;
        r19 = r5;
        r16 = r6;
        r13 = r2;
        r14 = r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0095, code lost:
        r7 = r11 - gallopRight(r9[r15], r8, r22, r11, r11 - 1, r12);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x00a5, code lost:
        if (r7 == 0) goto L_0x00c3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x00a7, code lost:
        r2 = r16 - r7;
        r3 = r14 - r7;
        r4 = r11 - r7;
        java.lang.System.arraycopy(r8, r3 + 1, r8, r2 + 1, r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x00b4, code lost:
        if (r4 != 0) goto L_0x00bf;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x00b6, code lost:
        r16 = r2;
        r14 = r3;
        r3 = r4;
        r4 = r15;
        r11 = r19;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x00bf, code lost:
        r16 = r2;
        r14 = r3;
        r11 = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x00c3, code lost:
        r17 = r16 - 1;
        r20 = r15 - 1;
        r8[r16] = r9[r15];
        r13 = r13 - 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x00ce, code lost:
        if (r13 != 1) goto L_0x00d8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x00d0, code lost:
        r3 = r11;
        r16 = r17;
        r11 = r19;
        r4 = r20;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x00d8, code lost:
        r2 = r13 - gallopLeft(r8[r14], r9, r10, r13, r13 - 1, r12);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x00e7, code lost:
        if (r2 == 0) goto L_0x0104;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x00e9, code lost:
        r3 = r17 - r2;
        r4 = r20 - r2;
        r5 = r13 - r2;
        java.lang.System.arraycopy(r9, r4 + 1, r8, r3 + 1, r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x00f7, code lost:
        if (r5 > 1) goto L_0x0100;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x00f9, code lost:
        r16 = r3;
        r13 = r5;
        r3 = r11;
        r11 = r19;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0100, code lost:
        r17 = r3;
        r13 = r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x0104, code lost:
        r4 = r20;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x0106, code lost:
        r16 = r17 - 1;
        r3 = r14 - 1;
        r8[r17] = r8[r14];
        r11 = r11 - 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x0110, code lost:
        if (r11 != 0) goto L_0x0144;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x0112, code lost:
        r14 = r3;
        r3 = r11;
        r11 = r19;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x0144, code lost:
        r19 = r19 - 1;
        r6 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x0149, code lost:
        if (r7 < 7) goto L_0x014d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x014b, code lost:
        r14 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x014d, code lost:
        r14 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x014e, code lost:
        if (r2 < 7) goto L_0x0151;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x0150, code lost:
        r6 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x0152, code lost:
        if ((r6 | r14) != false) goto L_0x0164;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x0154, code lost:
        if (r19 >= 0) goto L_0x0158;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x0156, code lost:
        r19 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x0164, code lost:
        r14 = r3;
        r15 = r4;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void mergeHi(int r22, int r23, int r24, int r25) {
        /*
        // Method dump skipped, instructions count: 367
        */
        throw new UnsupportedOperationException("Method not decompiled: java.util.TimSort.mergeHi(int, int, int, int):void");
    }

    private T[] ensureCapacity(int minCapacity) {
        int newSize;
        if (this.tmpLen < minCapacity) {
            int newSize2 = minCapacity | (minCapacity >> 1);
            int newSize3 = newSize2 | (newSize2 >> 2);
            int newSize4 = newSize3 | (newSize3 >> 4);
            int newSize5 = newSize4 | (newSize4 >> 8);
            int newSize6 = (newSize5 | (newSize5 >> 16)) + 1;
            if (newSize6 < 0) {
                newSize = minCapacity;
            } else {
                newSize = Math.min(newSize6, this.a.length >>> 1);
            }
            this.tmp = (T[]) ((Object[]) Array.newInstance(this.a.getClass().getComponentType(), newSize));
            this.tmpLen = newSize;
            this.tmpBase = 0;
        }
        return this.tmp;
    }
}
