package java.util;

/* access modifiers changed from: package-private */
public class ComparableTimSort {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final int INITIAL_TMP_STORAGE_LENGTH = 256;
    private static final int MIN_GALLOP = 7;
    private static final int MIN_MERGE = 32;
    private final Object[] a;
    private int minGallop = 7;
    private final int[] runBase;
    private final int[] runLen;
    private int stackSize = 0;
    private Object[] tmp;
    private int tmpBase;
    private int tmpLen;

    private ComparableTimSort(Object[] a2, Object[] work, int workBase, int workLen) {
        int stackLen;
        this.a = a2;
        int len = a2.length;
        int tlen = len < 512 ? len >>> 1 : 256;
        if (work == null || workLen < tlen || workBase + tlen > work.length) {
            this.tmp = new Object[tlen];
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

    static void sort(Object[] a2, int lo, int hi, Object[] work, int workBase, int workLen) {
        int nRemaining = hi - lo;
        if (nRemaining >= 2) {
            if (nRemaining < 32) {
                binarySort(a2, lo, hi, lo + countRunAndMakeAscending(a2, lo, hi));
                return;
            }
            ComparableTimSort ts = new ComparableTimSort(a2, work, workBase, workLen);
            int minRun = minRunLength(nRemaining);
            do {
                int runLen2 = countRunAndMakeAscending(a2, lo, hi);
                if (runLen2 < minRun) {
                    int force = nRemaining <= minRun ? nRemaining : minRun;
                    binarySort(a2, lo, lo + force, lo + runLen2);
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

    private static void binarySort(Object[] a2, int lo, int hi, int start) {
        if (start == lo) {
            start++;
        }
        while (start < hi) {
            Comparable pivot = (Comparable) a2[start];
            int left = lo;
            int right = start;
            while (left < right) {
                int mid = (left + right) >>> 1;
                if (pivot.compareTo(a2[mid]) < 0) {
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

    private static int countRunAndMakeAscending(Object[] a2, int lo, int hi) {
        int runHi = lo + 1;
        if (runHi == hi) {
            return 1;
        }
        int runHi2 = runHi + 1;
        if (((Comparable) a2[runHi]).compareTo(a2[lo]) < 0) {
            while (runHi2 < hi && ((Comparable) a2[runHi2]).compareTo(a2[runHi2 - 1]) < 0) {
                runHi2++;
            }
            reverseRange(a2, lo, runHi2);
        } else {
            while (runHi2 < hi && ((Comparable) a2[runHi2]).compareTo(a2[runHi2 - 1]) >= 0) {
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
        Object[] objArr = this.a;
        int k = gallopRight((Comparable) objArr[base2], objArr, base1, len1, 0);
        int base12 = base1 + k;
        int len12 = len1 - k;
        if (len12 != 0) {
            Object[] objArr2 = this.a;
            int len22 = gallopLeft((Comparable) objArr2[(base12 + len12) - 1], objArr2, base2, len2, len2 - 1);
            if (len22 != 0) {
                if (len12 <= len22) {
                    mergeLo(base12, len12, base2, len22);
                } else {
                    mergeHi(base12, len12, base2, len22);
                }
            }
        }
    }

    private static int gallopLeft(Comparable<Object> key, Object[] a2, int base, int len, int hint) {
        int ofs;
        int lastOfs;
        int lastOfs2 = 0;
        int ofs2 = 1;
        if (key.compareTo(a2[base + hint]) > 0) {
            int maxOfs = len - hint;
            while (ofs2 < maxOfs && key.compareTo(a2[base + hint + ofs2]) > 0) {
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
            while (ofs2 < maxOfs2 && key.compareTo(a2[(base + hint) - ofs2]) <= 0) {
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
            if (key.compareTo(a2[base + m]) > 0) {
                lastOfs3 = m + 1;
            } else {
                ofs = m;
            }
        }
        return ofs;
    }

    private static int gallopRight(Comparable<Object> key, Object[] a2, int base, int len, int hint) {
        int lastOfs;
        int ofs;
        int ofs2 = 1;
        int lastOfs2 = 0;
        if (key.compareTo(a2[base + hint]) < 0) {
            int maxOfs = hint + 1;
            while (ofs2 < maxOfs && key.compareTo(a2[(base + hint) - ofs2]) < 0) {
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
            while (ofs2 < maxOfs2 && key.compareTo(a2[base + hint + ofs2]) >= 0) {
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
            if (key.compareTo(a2[base + m]) < 0) {
                ofs = m;
            } else {
                lastOfs3 = m + 1;
            }
        }
        return ofs;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0072, code lost:
        r14 = false;
        r11 = gallopRight((java.lang.Comparable) r2[r9], r3, r4, r1, 0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x007b, code lost:
        if (r11 == 0) goto L_0x0087;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x007d, code lost:
        java.lang.System.arraycopy(r3, r4, r2, r8, r11);
        r13 = r8 + r11;
        r4 = r4 + r11;
        r1 = r1 - r11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0084, code lost:
        if (r1 > 1) goto L_0x0088;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0087, code lost:
        r13 = r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0088, code lost:
        r8 = r13 + 1;
        r15 = r9 + 1;
        r2[r13] = r2[r9];
        r5 = r5 - 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0092, code lost:
        if (r5 != 0) goto L_0x0097;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0094, code lost:
        r13 = r8;
        r9 = r15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0097, code lost:
        r12 = gallopLeft((java.lang.Comparable) r3[r4], r2, r15, r5, 0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x009f, code lost:
        if (r12 == 0) goto L_0x00ac;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x00a1, code lost:
        java.lang.System.arraycopy(r2, r15, r2, r8, r12);
        r13 = r8 + r12;
        r9 = r15 + r12;
        r5 = r5 - r12;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x00a9, code lost:
        if (r5 != 0) goto L_0x00ae;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x00ac, code lost:
        r13 = r8;
        r9 = r15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x00ae, code lost:
        r8 = r13 + 1;
        r15 = r4 + 1;
        r2[r13] = r3[r4];
        r1 = r1 - 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x00b8, code lost:
        if (r1 != 1) goto L_0x00e0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x00ba, code lost:
        r13 = r8;
        r4 = r15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x00e0, code lost:
        r10 = r10 - 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x00e3, code lost:
        if (r11 < 7) goto L_0x00e7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x00e5, code lost:
        r13 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x00e7, code lost:
        r13 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x00e8, code lost:
        if (r12 < 7) goto L_0x00eb;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x00ea, code lost:
        r14 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x00ed, code lost:
        if ((r13 | r14) != false) goto L_0x00f8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x00ef, code lost:
        if (r10 >= 0) goto L_0x00f2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x00f1, code lost:
        r10 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x00f8, code lost:
        r4 = r15;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void mergeLo(int r17, int r18, int r19, int r20) {
        /*
        // Method dump skipped, instructions count: 251
        */
        throw new UnsupportedOperationException("Method not decompiled: java.util.ComparableTimSort.mergeLo(int, int, int, int):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0087, code lost:
        r13 = r7 - gallopRight((java.lang.Comparable) r5[r9], r4, r18, r7, r7 - 1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0093, code lost:
        if (r13 == 0) goto L_0x00a6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0095, code lost:
        r8 = r11 - r13;
        r12 = r12 - r13;
        r7 = r7 - r13;
        java.lang.System.arraycopy(r4, r12 + 1, r4, r8 + 1, r13);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x00a0, code lost:
        if (r7 != 0) goto L_0x00a5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x00a2, code lost:
        r11 = r8;
        r8 = r10;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x00a5, code lost:
        r11 = r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x00a6, code lost:
        r8 = r11 - 1;
        r15 = r9 - 1;
        r4[r11] = r5[r9];
        r3 = r3 - 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x00b1, code lost:
        if (r3 != 1) goto L_0x00b7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x00b3, code lost:
        r11 = r8;
        r8 = r10;
        r9 = r15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x00b7, code lost:
        r14 = r3 - gallopLeft((java.lang.Comparable) r4[r12], r5, r6, r3, r3 - 1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x00c3, code lost:
        if (r14 == 0) goto L_0x00d6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x00c5, code lost:
        r8 = r8 - r14;
        r9 = r15 - r14;
        r3 = r3 - r14;
        java.lang.System.arraycopy(r5, r9 + 1, r4, r8 + 1, r14);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x00d1, code lost:
        if (r3 > 1) goto L_0x00d7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x00d3, code lost:
        r11 = r8;
        r8 = r10;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x00d6, code lost:
        r9 = r15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x00d7, code lost:
        r11 = r8 - 1;
        r15 = r12 - 1;
        r4[r8] = r4[r12];
        r7 = r7 - 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x00e1, code lost:
        if (r7 != 0) goto L_0x0112;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x00e3, code lost:
        r8 = r10;
        r12 = r15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x0112, code lost:
        r10 = r10 - 1;
        r12 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x0117, code lost:
        if (r13 < 7) goto L_0x011c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x0119, code lost:
        r16 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x011c, code lost:
        r16 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x011e, code lost:
        if (r14 < 7) goto L_0x0121;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x0120, code lost:
        r12 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x0123, code lost:
        if ((r16 | r12) != false) goto L_0x012f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x0125, code lost:
        if (r10 >= 0) goto L_0x0128;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x0127, code lost:
        r10 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x012f, code lost:
        r12 = r15;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void mergeHi(int r18, int r19, int r20, int r21) {
        /*
        // Method dump skipped, instructions count: 309
        */
        throw new UnsupportedOperationException("Method not decompiled: java.util.ComparableTimSort.mergeHi(int, int, int, int):void");
    }

    private Object[] ensureCapacity(int minCapacity) {
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
            this.tmp = new Object[newSize];
            this.tmpLen = newSize;
            this.tmpBase = 0;
        }
        return this.tmp;
    }
}
