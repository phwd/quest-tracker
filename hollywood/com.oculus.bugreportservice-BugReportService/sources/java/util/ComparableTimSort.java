package java.util;

/* access modifiers changed from: package-private */
public class ComparableTimSort {
    private final Object[] a;
    private int minGallop = 7;
    private final int[] runBase;
    private final int[] runLen;
    private int stackSize = 0;
    private Object[] tmp;
    private int tmpBase;
    private int tmpLen;

    private static int minRunLength(int i) {
        int i2 = 0;
        while (i >= 32) {
            i2 |= i & 1;
            i >>= 1;
        }
        return i + i2;
    }

    private ComparableTimSort(Object[] objArr, Object[] objArr2, int i, int i2) {
        this.a = objArr;
        int length = objArr.length;
        int i3 = length < 512 ? length >>> 1 : 256;
        if (objArr2 == null || i2 < i3 || i + i3 > objArr2.length) {
            this.tmp = new Object[i3];
            this.tmpBase = 0;
            this.tmpLen = i3;
        } else {
            this.tmp = objArr2;
            this.tmpBase = i;
            this.tmpLen = i2;
        }
        int i4 = length < 120 ? 5 : length < 1542 ? 10 : length < 119151 ? 24 : 49;
        this.runBase = new int[i4];
        this.runLen = new int[i4];
    }

    static void sort(Object[] objArr, int i, int i2, Object[] objArr2, int i3, int i4) {
        int i5 = i2 - i;
        if (i5 >= 2) {
            if (i5 < 32) {
                binarySort(objArr, i, i2, countRunAndMakeAscending(objArr, i, i2) + i);
                return;
            }
            ComparableTimSort comparableTimSort = new ComparableTimSort(objArr, objArr2, i3, i4);
            int minRunLength = minRunLength(i5);
            do {
                int countRunAndMakeAscending = countRunAndMakeAscending(objArr, i, i2);
                if (countRunAndMakeAscending < minRunLength) {
                    int i6 = i5 <= minRunLength ? i5 : minRunLength;
                    binarySort(objArr, i, i + i6, countRunAndMakeAscending + i);
                    countRunAndMakeAscending = i6;
                }
                comparableTimSort.pushRun(i, countRunAndMakeAscending);
                comparableTimSort.mergeCollapse();
                i += countRunAndMakeAscending;
                i5 -= countRunAndMakeAscending;
            } while (i5 != 0);
            comparableTimSort.mergeForceCollapse();
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for r0v0, resolved type: java.lang.Object[] */
    /* JADX DEBUG: Multi-variable search result rejected for r3v1, resolved type: java.lang.Object[] */
    /* JADX DEBUG: Multi-variable search result rejected for r3v4, resolved type: java.lang.Object[] */
    /* JADX DEBUG: Multi-variable search result rejected for r4v1, resolved type: java.lang.Object[] */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARNING: Code restructure failed: missing block: B:0:0x0000, code lost:
        if (r8 == r6) goto L_0x0002;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0021, code lost:
        r2 = r8 - r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0023, code lost:
        if (r2 == 1) goto L_0x0036;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0026, code lost:
        if (r2 == 2) goto L_0x002e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0028, code lost:
        java.lang.System.arraycopy(r5, r1, r5, r1 + 1, r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x002e, code lost:
        r5[r1 + 2] = r5[r1 + 1];
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0036, code lost:
        r5[r1 + 1] = r5[r1];
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x003c, code lost:
        r5[r1] = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x003f, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:1:0x0002, code lost:
        r8 = r8 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0004, code lost:
        if (r8 >= r7) goto L_0x003f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0006, code lost:
        r0 = (java.lang.Comparable) r5[r8];
        r1 = r6;
        r2 = r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:5:0x000d, code lost:
        if (r1 >= r2) goto L_0x0021;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x000f, code lost:
        r3 = (r1 + r2) >>> 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0019, code lost:
        if (r0.compareTo(r5[r3]) >= 0) goto L_0x001d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x001b, code lost:
        r2 = r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x001d, code lost:
        r1 = r3 + 1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void binarySort(java.lang.Object[] r5, int r6, int r7, int r8) {
        /*
            if (r8 != r6) goto L_0x0004
        L_0x0002:
            int r8 = r8 + 1
        L_0x0004:
            if (r8 >= r7) goto L_0x003f
            r0 = r5[r8]
            java.lang.Comparable r0 = (java.lang.Comparable) r0
            r1 = r6
            r2 = r8
        L_0x000c:
            r3 = 1
            if (r1 >= r2) goto L_0x0021
            int r4 = r1 + r2
            int r3 = r4 >>> 1
            r4 = r5[r3]
            int r4 = r0.compareTo(r4)
            if (r4 >= 0) goto L_0x001d
            r2 = r3
            goto L_0x000c
        L_0x001d:
            int r3 = r3 + 1
            r1 = r3
            goto L_0x000c
        L_0x0021:
            int r2 = r8 - r1
            if (r2 == r3) goto L_0x0036
            r3 = 2
            if (r2 == r3) goto L_0x002e
            int r3 = r1 + 1
            java.lang.System.arraycopy(r5, r1, r5, r3, r2)
            goto L_0x003c
        L_0x002e:
            int r2 = r1 + 2
            int r3 = r1 + 1
            r3 = r5[r3]
            r5[r2] = r3
        L_0x0036:
            int r2 = r1 + 1
            r3 = r5[r1]
            r5[r2] = r3
        L_0x003c:
            r5[r1] = r0
            goto L_0x0002
        L_0x003f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: java.util.ComparableTimSort.binarySort(java.lang.Object[], int, int, int):void");
    }

    private static int countRunAndMakeAscending(Object[] objArr, int i, int i2) {
        int i3 = i + 1;
        if (i3 == i2) {
            return 1;
        }
        int i4 = i3 + 1;
        if (((Comparable) objArr[i3]).compareTo(objArr[i]) < 0) {
            while (i4 < i2 && ((Comparable) objArr[i4]).compareTo(objArr[i4 - 1]) < 0) {
                i4++;
            }
            reverseRange(objArr, i, i4);
        } else {
            while (i4 < i2 && ((Comparable) objArr[i4]).compareTo(objArr[i4 - 1]) >= 0) {
                i4++;
            }
        }
        return i4 - i;
    }

    private static void reverseRange(Object[] objArr, int i, int i2) {
        int i3 = i2 - 1;
        while (i < i3) {
            Object obj = objArr[i];
            objArr[i] = objArr[i3];
            objArr[i3] = obj;
            i3--;
            i++;
        }
    }

    private void pushRun(int i, int i2) {
        int[] iArr = this.runBase;
        int i3 = this.stackSize;
        iArr[i3] = i;
        this.runLen[i3] = i2;
        this.stackSize = i3 + 1;
    }

    private void mergeCollapse() {
        while (true) {
            int i = this.stackSize;
            if (i > 1) {
                int i2 = i - 2;
                if (i2 > 0) {
                    int[] iArr = this.runLen;
                    int i3 = i2 - 1;
                    int i4 = i2 + 1;
                    if (iArr[i3] <= iArr[i2] + iArr[i4]) {
                        if (iArr[i3] < iArr[i4]) {
                            i2--;
                        }
                        mergeAt(i2);
                    }
                }
                int[] iArr2 = this.runLen;
                if (iArr2[i2] <= iArr2[i2 + 1]) {
                    mergeAt(i2);
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
                int i2 = i - 2;
                if (i2 > 0) {
                    int[] iArr = this.runLen;
                    if (iArr[i2 - 1] < iArr[i2 + 1]) {
                        i2--;
                    }
                }
                mergeAt(i2);
            } else {
                return;
            }
        }
    }

    private void mergeAt(int i) {
        int[] iArr = this.runBase;
        int i2 = iArr[i];
        int[] iArr2 = this.runLen;
        int i3 = iArr2[i];
        int i4 = i + 1;
        int i5 = iArr[i4];
        int i6 = iArr2[i4];
        iArr2[i] = i3 + i6;
        if (i == this.stackSize - 3) {
            int i7 = i + 2;
            iArr[i4] = iArr[i7];
            iArr2[i4] = iArr2[i7];
        }
        this.stackSize--;
        Object[] objArr = this.a;
        int gallopRight = gallopRight((Comparable) objArr[i5], objArr, i2, i3, 0);
        int i8 = i2 + gallopRight;
        int i9 = i3 - gallopRight;
        if (i9 != 0) {
            Object[] objArr2 = this.a;
            int gallopLeft = gallopLeft((Comparable) objArr2[(i8 + i9) - 1], objArr2, i5, i6, i6 - 1);
            if (gallopLeft != 0) {
                if (i9 <= gallopLeft) {
                    mergeLo(i8, i9, i5, gallopLeft);
                } else {
                    mergeHi(i8, i9, i5, gallopLeft);
                }
            }
        }
    }

    private static int gallopLeft(Comparable comparable, Object[] objArr, int i, int i2, int i3) {
        int i4;
        int i5;
        int i6 = i + i3;
        if (comparable.compareTo(objArr[i6]) > 0) {
            int i7 = i2 - i3;
            int i8 = 0;
            int i9 = 1;
            while (i9 < i7 && comparable.compareTo(objArr[i6 + i9]) > 0) {
                int i10 = (i9 << 1) + 1;
                if (i10 <= 0) {
                    i8 = i9;
                    i9 = i7;
                } else {
                    i8 = i9;
                    i9 = i10;
                }
            }
            if (i9 <= i7) {
                i7 = i9;
            }
            int i11 = i8 + i3;
            i4 = i7 + i3;
            i5 = i11;
        } else {
            int i12 = i3 + 1;
            int i13 = 0;
            int i14 = 1;
            while (i14 < i12 && comparable.compareTo(objArr[i6 - i14]) <= 0) {
                int i15 = (i14 << 1) + 1;
                if (i15 <= 0) {
                    i13 = i14;
                    i14 = i12;
                } else {
                    i13 = i14;
                    i14 = i15;
                }
            }
            if (i14 <= i12) {
                i12 = i14;
            }
            i5 = i3 - i12;
            i4 = i3 - i13;
        }
        int i16 = i5 + 1;
        while (i16 < i4) {
            int i17 = ((i4 - i16) >>> 1) + i16;
            if (comparable.compareTo(objArr[i + i17]) > 0) {
                i16 = i17 + 1;
            } else {
                i4 = i17;
            }
        }
        return i4;
    }

    private static int gallopRight(Comparable comparable, Object[] objArr, int i, int i2, int i3) {
        int i4;
        int i5;
        int i6 = i + i3;
        if (comparable.compareTo(objArr[i6]) < 0) {
            int i7 = i3 + 1;
            int i8 = 0;
            int i9 = 1;
            while (i9 < i7 && comparable.compareTo(objArr[i6 - i9]) < 0) {
                int i10 = (i9 << 1) + 1;
                if (i10 <= 0) {
                    i8 = i9;
                    i9 = i7;
                } else {
                    i8 = i9;
                    i9 = i10;
                }
            }
            if (i9 <= i7) {
                i7 = i9;
            }
            i5 = i3 - i7;
            i4 = i3 - i8;
        } else {
            int i11 = i2 - i3;
            int i12 = 0;
            int i13 = 1;
            while (i13 < i11 && comparable.compareTo(objArr[i6 + i13]) >= 0) {
                int i14 = (i13 << 1) + 1;
                if (i14 <= 0) {
                    i12 = i13;
                    i13 = i11;
                } else {
                    i12 = i13;
                    i13 = i14;
                }
            }
            if (i13 <= i11) {
                i11 = i13;
            }
            int i15 = i12 + i3;
            i4 = i3 + i11;
            i5 = i15;
        }
        int i16 = i5 + 1;
        while (i16 < i4) {
            int i17 = ((i4 - i16) >>> 1) + i16;
            if (comparable.compareTo(objArr[i + i17]) < 0) {
                i4 = i17;
            } else {
                i16 = i17 + 1;
            }
        }
        return i4;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0046, code lost:
        r4 = r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0063, code lost:
        r6 = gallopRight((java.lang.Comparable) r0[r4], r1, r2, r13, 0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x006b, code lost:
        if (r6 == 0) goto L_0x0077;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x006d, code lost:
        java.lang.System.arraycopy(r1, r2, r0, r3, r6);
        r3 = r3 + r6;
        r2 = r2 + r6;
        r13 = r13 - r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0073, code lost:
        if (r13 > 1) goto L_0x0077;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0075, code lost:
        r7 = r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0077, code lost:
        r7 = r3 + 1;
        r8 = r4 + 1;
        r0[r3] = r0[r4];
        r15 = r15 - 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0081, code lost:
        if (r15 != 0) goto L_0x0084;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0084, code lost:
        r3 = gallopLeft((java.lang.Comparable) r1[r2], r0, r8, r15, 0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x008c, code lost:
        if (r3 == 0) goto L_0x009c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x008e, code lost:
        java.lang.System.arraycopy(r0, r8, r0, r7, r3);
        r4 = r7 + r3;
        r7 = r8 + r3;
        r15 = r15 - r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0096, code lost:
        if (r15 != 0) goto L_0x009e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0098, code lost:
        r7 = r4;
        r4 = r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x009c, code lost:
        r4 = r7;
        r7 = r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x009e, code lost:
        r8 = r4 + 1;
        r9 = r2 + 1;
        r0[r4] = r1[r2];
        r13 = r13 - 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x00a8, code lost:
        if (r13 != 1) goto L_0x00cb;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x00aa, code lost:
        r4 = r7;
        r7 = r8;
        r2 = r9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x00cb, code lost:
        r14 = r14 - 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x00ce, code lost:
        if (r6 < 7) goto L_0x00d2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x00d0, code lost:
        r4 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x00d2, code lost:
        r4 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x00d3, code lost:
        if (r3 < 7) goto L_0x00d7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x00d5, code lost:
        r2 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x00d7, code lost:
        r2 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x00d9, code lost:
        if ((r2 | r4) != false) goto L_0x00e5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x00db, code lost:
        if (r14 >= 0) goto L_0x00de;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x00dd, code lost:
        r14 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x00e5, code lost:
        r4 = r7;
        r3 = r8;
        r2 = r9;
     */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x0063 A[EDGE_INSN: B:69:0x0063->B:23:0x0063 ?: BREAK  , SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void mergeLo(int r12, int r13, int r14, int r15) {
        /*
        // Method dump skipped, instructions count: 234
        */
        throw new UnsupportedOperationException("Method not decompiled: java.util.ComparableTimSort.mergeLo(int, int, int, int):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0079, code lost:
        r12 = r2 - gallopRight((java.lang.Comparable) r5[r9], r4, r17, r2, r2 - 1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0085, code lost:
        if (r12 == 0) goto L_0x0095;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0087, code lost:
        r10 = r10 - r12;
        r11 = r11 - r12;
        r2 = r2 - r12;
        java.lang.System.arraycopy(r4, r11 + 1, r4, r10 + 1, r12);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0091, code lost:
        if (r2 != 0) goto L_0x0095;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0093, code lost:
        r13 = r9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0095, code lost:
        r13 = r10 - 1;
        r14 = r9 - 1;
        r4[r10] = r5[r9];
        r3 = r3 - 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x009f, code lost:
        if (r3 != 1) goto L_0x00a4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x00a1, code lost:
        r10 = r13;
        r13 = r14;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x00a4, code lost:
        r9 = r3 - gallopLeft((java.lang.Comparable) r4[r11], r5, r6, r3, r3 - 1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x00b0, code lost:
        if (r9 == 0) goto L_0x00c3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x00b2, code lost:
        r10 = r13 - r9;
        r13 = r14 - r9;
        r3 = r3 - r9;
        java.lang.System.arraycopy(r5, r13 + 1, r4, r10 + 1, r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x00be, code lost:
        if (r3 > 1) goto L_0x00c1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x00c1, code lost:
        r14 = r13;
        r13 = r10;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x00c3, code lost:
        r10 = r13 - 1;
        r15 = r11 - 1;
        r4[r13] = r4[r11];
        r2 = r2 - 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x00cd, code lost:
        if (r2 != 0) goto L_0x00f6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x00cf, code lost:
        r13 = r14;
        r11 = r15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x00f6, code lost:
        r7 = r7 - 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x00f9, code lost:
        if (r12 < 7) goto L_0x00fd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x00fb, code lost:
        r12 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x00fd, code lost:
        r12 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x00fe, code lost:
        if (r9 < 7) goto L_0x0102;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x0100, code lost:
        r9 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x0102, code lost:
        r9 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x0104, code lost:
        if ((r9 | r12) != false) goto L_0x010f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x0106, code lost:
        if (r7 >= 0) goto L_0x0109;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x0108, code lost:
        r7 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x010f, code lost:
        r9 = r14;
        r11 = r15;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void mergeHi(int r17, int r18, int r19, int r20) {
        /*
        // Method dump skipped, instructions count: 275
        */
        throw new UnsupportedOperationException("Method not decompiled: java.util.ComparableTimSort.mergeHi(int, int, int, int):void");
    }

    private Object[] ensureCapacity(int i) {
        if (this.tmpLen < i) {
            int i2 = (i >> 1) | i;
            int i3 = i2 | (i2 >> 2);
            int i4 = i3 | (i3 >> 4);
            int i5 = i4 | (i4 >> 8);
            int i6 = (i5 | (i5 >> 16)) + 1;
            if (i6 >= 0) {
                i = Math.min(i6, this.a.length >>> 1);
            }
            this.tmp = new Object[i];
            this.tmpLen = i;
            this.tmpBase = 0;
        }
        return this.tmp;
    }
}
