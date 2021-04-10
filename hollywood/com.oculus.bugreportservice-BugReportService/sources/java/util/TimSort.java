package java.util;

import java.lang.reflect.Array;

/* access modifiers changed from: package-private */
public class TimSort {
    private final Object[] a;
    private final Comparator c;
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

    private TimSort(Object[] objArr, Comparator comparator, Object[] objArr2, int i, int i2) {
        this.a = objArr;
        this.c = comparator;
        int length = objArr.length;
        int i3 = length < 512 ? length >>> 1 : 256;
        if (objArr2 == null || i2 < i3 || i + i3 > objArr2.length) {
            this.tmp = (Object[]) Array.newInstance(objArr.getClass().getComponentType(), i3);
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

    static void sort(Object[] objArr, int i, int i2, Comparator comparator, Object[] objArr2, int i3, int i4) {
        int i5 = i2 - i;
        if (i5 >= 2) {
            if (i5 < 32) {
                binarySort(objArr, i, i2, countRunAndMakeAscending(objArr, i, i2, comparator) + i, comparator);
                return;
            }
            TimSort timSort = new TimSort(objArr, comparator, objArr2, i3, i4);
            int minRunLength = minRunLength(i5);
            do {
                int countRunAndMakeAscending = countRunAndMakeAscending(objArr, i, i2, comparator);
                if (countRunAndMakeAscending < minRunLength) {
                    int i6 = i5 <= minRunLength ? i5 : minRunLength;
                    binarySort(objArr, i, i + i6, countRunAndMakeAscending + i, comparator);
                    countRunAndMakeAscending = i6;
                }
                timSort.pushRun(i, countRunAndMakeAscending);
                timSort.mergeCollapse();
                i += countRunAndMakeAscending;
                i5 -= countRunAndMakeAscending;
            } while (i5 != 0);
            timSort.mergeForceCollapse();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:0:0x0000, code lost:
        if (r8 == r6) goto L_0x0002;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:10:0x001f, code lost:
        r2 = r8 - r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0021, code lost:
        if (r2 == 1) goto L_0x0034;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0024, code lost:
        if (r2 == 2) goto L_0x002c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0026, code lost:
        java.lang.System.arraycopy(r5, r1, r5, r1 + 1, r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x002c, code lost:
        r5[r1 + 2] = r5[r1 + 1];
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0034, code lost:
        r5[r1 + 1] = r5[r1];
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x003a, code lost:
        r5[r1] = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x003d, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:1:0x0002, code lost:
        r8 = r8 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0004, code lost:
        if (r8 >= r7) goto L_0x003d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0006, code lost:
        r0 = r5[r8];
        r1 = r6;
        r2 = r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:5:0x000b, code lost:
        if (r1 >= r2) goto L_0x001f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x000d, code lost:
        r3 = (r1 + r2) >>> 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0017, code lost:
        if (r9.compare(r0, r5[r3]) >= 0) goto L_0x001b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0019, code lost:
        r2 = r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x001b, code lost:
        r1 = r3 + 1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void binarySort(java.lang.Object[] r5, int r6, int r7, int r8, java.util.Comparator r9) {
        /*
            if (r8 != r6) goto L_0x0004
        L_0x0002:
            int r8 = r8 + 1
        L_0x0004:
            if (r8 >= r7) goto L_0x003d
            r0 = r5[r8]
            r1 = r6
            r2 = r8
        L_0x000a:
            r3 = 1
            if (r1 >= r2) goto L_0x001f
            int r4 = r1 + r2
            int r3 = r4 >>> 1
            r4 = r5[r3]
            int r4 = r9.compare(r0, r4)
            if (r4 >= 0) goto L_0x001b
            r2 = r3
            goto L_0x000a
        L_0x001b:
            int r3 = r3 + 1
            r1 = r3
            goto L_0x000a
        L_0x001f:
            int r2 = r8 - r1
            if (r2 == r3) goto L_0x0034
            r3 = 2
            if (r2 == r3) goto L_0x002c
            int r3 = r1 + 1
            java.lang.System.arraycopy(r5, r1, r5, r3, r2)
            goto L_0x003a
        L_0x002c:
            int r2 = r1 + 2
            int r3 = r1 + 1
            r3 = r5[r3]
            r5[r2] = r3
        L_0x0034:
            int r2 = r1 + 1
            r3 = r5[r1]
            r5[r2] = r3
        L_0x003a:
            r5[r1] = r0
            goto L_0x0002
        L_0x003d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: java.util.TimSort.binarySort(java.lang.Object[], int, int, int, java.util.Comparator):void");
    }

    private static int countRunAndMakeAscending(Object[] objArr, int i, int i2, Comparator comparator) {
        int i3 = i + 1;
        if (i3 == i2) {
            return 1;
        }
        int i4 = i3 + 1;
        if (comparator.compare(objArr[i3], objArr[i]) < 0) {
            while (i4 < i2 && comparator.compare(objArr[i4], objArr[i4 - 1]) < 0) {
                i4++;
            }
            reverseRange(objArr, i, i4);
        } else {
            while (i4 < i2 && comparator.compare(objArr[i4], objArr[i4 - 1]) >= 0) {
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
        int gallopRight = gallopRight(objArr[i5], objArr, i2, i3, 0, this.c);
        int i8 = i2 + gallopRight;
        int i9 = i3 - gallopRight;
        if (i9 != 0) {
            Object[] objArr2 = this.a;
            int gallopLeft = gallopLeft(objArr2[(i8 + i9) - 1], objArr2, i5, i6, i6 - 1, this.c);
            if (gallopLeft != 0) {
                if (i9 <= gallopLeft) {
                    mergeLo(i8, i9, i5, gallopLeft);
                } else {
                    mergeHi(i8, i9, i5, gallopLeft);
                }
            }
        }
    }

    private static int gallopLeft(Object obj, Object[] objArr, int i, int i2, int i3, Comparator comparator) {
        int i4;
        int i5;
        int i6 = i + i3;
        if (comparator.compare(obj, objArr[i6]) > 0) {
            int i7 = i2 - i3;
            int i8 = 0;
            int i9 = 1;
            while (i9 < i7 && comparator.compare(obj, objArr[i6 + i9]) > 0) {
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
            while (i14 < i12 && comparator.compare(obj, objArr[i6 - i14]) <= 0) {
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
            if (comparator.compare(obj, objArr[i + i17]) > 0) {
                i16 = i17 + 1;
            } else {
                i4 = i17;
            }
        }
        return i4;
    }

    private static int gallopRight(Object obj, Object[] objArr, int i, int i2, int i3, Comparator comparator) {
        int i4;
        int i5;
        int i6 = i + i3;
        if (comparator.compare(obj, objArr[i6]) < 0) {
            int i7 = i3 + 1;
            int i8 = 0;
            int i9 = 1;
            while (i9 < i7 && comparator.compare(obj, objArr[i6 - i9]) < 0) {
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
            while (i13 < i11 && comparator.compare(obj, objArr[i6 + i13]) >= 0) {
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
            if (comparator.compare(obj, objArr[i + i17]) < 0) {
                i4 = i17;
            } else {
                i16 = i17 + 1;
            }
        }
        return i4;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:22:0x006e, code lost:
        r13 = r1;
        r12 = r2;
        r14 = r3;
        r15 = r5;
        r16 = r6;
        r6 = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0075, code lost:
        r11 = r6;
        r6 = gallopRight(r7[r15], r8, r14, r12, 0, r10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0081, code lost:
        if (r6 == 0) goto L_0x0099;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0083, code lost:
        java.lang.System.arraycopy(r8, r14, r7, r11, r6);
        r1 = r11 + r6;
        r3 = r14 + r6;
        r2 = r12 - r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x008c, code lost:
        if (r2 > r9) goto L_0x0096;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x008e, code lost:
        r12 = r2;
        r14 = r3;
        r11 = r13;
        r6 = r16;
        r13 = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0096, code lost:
        r11 = r1;
        r12 = r2;
        r14 = r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0099, code lost:
        r5 = r11 + 1;
        r4 = r15 + 1;
        r7[r11] = r7[r15];
        r11 = r13 - 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x00a3, code lost:
        if (r11 != 0) goto L_0x00aa;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x00a5, code lost:
        r15 = r4;
        r13 = r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x00aa, code lost:
        r15 = r4;
        r1 = gallopLeft(r8[r14], r7, r4, r11, 0, r10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x00b9, code lost:
        if (r1 == 0) goto L_0x00d0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x00bb, code lost:
        java.lang.System.arraycopy(r7, r15, r7, r5, r1);
        r2 = r5 + r1;
        r5 = r15 + r1;
        r3 = r11 - r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x00c4, code lost:
        if (r3 != 0) goto L_0x00cd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x00c6, code lost:
        r13 = r2;
        r11 = r3;
        r15 = r5;
        r6 = r16;
        r9 = 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x00cd, code lost:
        r11 = r3;
        r15 = r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x00d0, code lost:
        r2 = r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x00d1, code lost:
        r6 = r2 + 1;
        r3 = r14 + 1;
        r7[r2] = r8[r14];
        r12 = r12 - 1;
        r9 = 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x00dc, code lost:
        if (r12 != 1) goto L_0x00ff;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x00de, code lost:
        r14 = r3;
        r13 = r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x00ff, code lost:
        r16 = r16 - 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x0102, code lost:
        if (r6 < 7) goto L_0x0106;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x0104, code lost:
        r4 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x0106, code lost:
        r4 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x0107, code lost:
        if (r1 < 7) goto L_0x010b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x0109, code lost:
        r1 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x010b, code lost:
        r1 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x010d, code lost:
        if ((r1 | r4) != false) goto L_0x011c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x010f, code lost:
        if (r16 >= 0) goto L_0x0113;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x0111, code lost:
        r16 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x011c, code lost:
        r14 = r3;
        r13 = r11;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void mergeLo(int r18, int r19, int r20, int r21) {
        /*
        // Method dump skipped, instructions count: 288
        */
        throw new UnsupportedOperationException("Method not decompiled: java.util.TimSort.mergeLo(int, int, int, int):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0082, code lost:
        r12 = r1;
        r13 = r2;
        r17 = r3;
        r15 = r4;
        r16 = r5;
        r14 = r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x008a, code lost:
        r6 = r12 - gallopRight(r8[r15], r7, r21, r12, r12 - 1, r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0099, code lost:
        if (r6 == 0) goto L_0x00b3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x009b, code lost:
        r1 = r16 - r6;
        r2 = r14 - r6;
        r3 = r12 - r6;
        java.lang.System.arraycopy(r7, r2 + 1, r7, r1 + 1, r6);
        r16 = r1;
        r14 = r2;
        r12 = r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x00ac, code lost:
        if (r3 != 0) goto L_0x00b3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x00ae, code lost:
        r19 = r15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x00b0, code lost:
        r3 = r17;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x00b3, code lost:
        r18 = r16 - 1;
        r19 = r15 - 1;
        r7[r16] = r8[r15];
        r13 = r13 - 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x00bd, code lost:
        if (r13 != 1) goto L_0x00c4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x00bf, code lost:
        r3 = r17;
        r16 = r18;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x00c4, code lost:
        r1 = r13 - gallopLeft(r7[r14], r8, r9, r13, r13 - 1, r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x00d3, code lost:
        if (r1 == 0) goto L_0x00ef;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x00d5, code lost:
        r2 = r18 - r1;
        r4 = r19 - r1;
        r3 = r13 - r1;
        java.lang.System.arraycopy(r8, r4 + 1, r7, r2 + 1, r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x00e2, code lost:
        if (r3 > 1) goto L_0x00ea;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x00e4, code lost:
        r16 = r2;
        r13 = r3;
        r19 = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x00ea, code lost:
        r18 = r2;
        r13 = r3;
        r19 = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x00ef, code lost:
        r16 = r18 - 1;
        r2 = r14 - 1;
        r7[r18] = r7[r14];
        r12 = r12 - 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x00f9, code lost:
        if (r12 != 0) goto L_0x0124;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x00fb, code lost:
        r14 = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x0124, code lost:
        r17 = r17 - 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x0127, code lost:
        if (r6 < 7) goto L_0x012b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x0129, code lost:
        r4 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x012b, code lost:
        r4 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x012c, code lost:
        if (r1 < 7) goto L_0x0130;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x012e, code lost:
        r1 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x0130, code lost:
        r1 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x0132, code lost:
        if ((r1 | r4) != false) goto L_0x0143;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x0134, code lost:
        if (r17 >= 0) goto L_0x0138;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x0136, code lost:
        r17 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x0143, code lost:
        r14 = r2;
        r15 = r19;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void mergeHi(int r21, int r22, int r23, int r24) {
        /*
        // Method dump skipped, instructions count: 328
        */
        throw new UnsupportedOperationException("Method not decompiled: java.util.TimSort.mergeHi(int, int, int, int):void");
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
            this.tmp = (Object[]) Array.newInstance(this.a.getClass().getComponentType(), i);
            this.tmpLen = i;
            this.tmpBase = 0;
        }
        return this.tmp;
    }
}
