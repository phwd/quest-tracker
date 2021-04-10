package java.util;

/* access modifiers changed from: package-private */
public final class DualPivotQuicksort {
    private static final int COUNTING_SORT_THRESHOLD_FOR_BYTE = 29;
    private static final int COUNTING_SORT_THRESHOLD_FOR_SHORT_OR_CHAR = 3200;
    private static final int INSERTION_SORT_THRESHOLD = 47;
    private static final int MAX_RUN_COUNT = 67;
    private static final int MAX_RUN_LENGTH = 33;
    private static final int NUM_BYTE_VALUES = 256;
    private static final int NUM_CHAR_VALUES = 65536;
    private static final int NUM_SHORT_VALUES = 65536;
    private static final int QUICKSORT_THRESHOLD = 286;

    private DualPivotQuicksort() {
    }

    /* JADX INFO: Multiple debug info for r8v6 int: [D('blen' int), D('n' int)] */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x00f2, code lost:
        if (r0[r2 + r14] <= r0[r3 + r14]) goto L_0x0108;
     */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x00ad  */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x00b7  */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x00bd  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static void sort(int[] r21, int r22, int r23, int[] r24, int r25, int r26) {
        /*
        // Method dump skipped, instructions count: 353
        */
        throw new UnsupportedOperationException("Method not decompiled: java.util.DualPivotQuicksort.sort(int[], int, int, int[], int, int):void");
    }

    /* JADX INFO: Multiple debug info for r11v4 int: [D('less' int), D('t' int)] */
    /* JADX INFO: Multiple debug info for r4v2 int: [D('pivot' int), D('length' int)] */
    /* JADX INFO: Multiple debug info for r4v18 int: [D('ak' int), D('length' int)] */
    /* JADX INFO: Multiple debug info for r5v23 int: [D('k' int), D('last' int)] */
    private static void sort(int[] a, int left, int right, boolean leftmost) {
        int seventh;
        int left2 = left;
        int right2 = right;
        int ak = (right2 - left2) + 1;
        if (ak >= 47) {
            int great = (ak >> 3) + (ak >> 6) + 1;
            int e3 = (left2 + right2) >>> 1;
            int e2 = e3 - great;
            int e1 = e2 - great;
            int e4 = e3 + great;
            int e5 = e4 + great;
            if (a[e2] < a[e1]) {
                int t = a[e2];
                a[e2] = a[e1];
                a[e1] = t;
            }
            if (a[e3] < a[e2]) {
                int t2 = a[e3];
                a[e3] = a[e2];
                a[e2] = t2;
                if (t2 < a[e1]) {
                    a[e2] = a[e1];
                    a[e1] = t2;
                }
            }
            if (a[e4] < a[e3]) {
                int t3 = a[e4];
                a[e4] = a[e3];
                a[e3] = t3;
                if (t3 < a[e2]) {
                    a[e3] = a[e2];
                    a[e2] = t3;
                    if (t3 < a[e1]) {
                        a[e2] = a[e1];
                        a[e1] = t3;
                    }
                }
            }
            if (a[e5] < a[e4]) {
                int t4 = a[e5];
                a[e5] = a[e4];
                a[e4] = t4;
                if (t4 < a[e3]) {
                    a[e4] = a[e3];
                    a[e3] = t4;
                    if (t4 < a[e2]) {
                        a[e3] = a[e2];
                        a[e2] = t4;
                        if (t4 < a[e1]) {
                            a[e2] = a[e1];
                            a[e1] = t4;
                        }
                    }
                }
            }
            int less = left;
            int great2 = right;
            if (a[e1] == a[e2] || a[e2] == a[e3] || a[e3] == a[e4] || a[e4] == a[e5]) {
                int length = a[e3];
                for (int k = less; k <= great2; k++) {
                    if (a[k] != length) {
                        int ak2 = a[k];
                        if (ak2 < length) {
                            a[k] = a[less];
                            a[less] = ak2;
                            less++;
                        } else {
                            while (a[great2] > length) {
                                great2--;
                            }
                            if (a[great2] < length) {
                                a[k] = a[less];
                                a[less] = a[great2];
                                less++;
                            } else {
                                a[k] = length;
                            }
                            a[great2] = ak2;
                            great2--;
                        }
                    }
                }
                sort(a, left2, less - 1, leftmost);
                sort(a, great2 + 1, right2, false);
                return;
            }
            int pivot1 = a[e2];
            int pivot2 = a[e4];
            a[e2] = a[left2];
            a[e4] = a[right2];
            do {
                less++;
            } while (a[less] < pivot1);
            do {
                great2--;
            } while (a[great2] > pivot2);
            int k2 = less - 1;
            loop9:
            while (true) {
                k2++;
                if (k2 > great2) {
                    break;
                }
                int length2 = a[k2];
                if (length2 < pivot1) {
                    a[k2] = a[less];
                    a[less] = length2;
                    less++;
                    seventh = great;
                } else if (length2 > pivot2) {
                    while (true) {
                        seventh = great;
                        if (a[great2] > pivot2) {
                            int great3 = great2 - 1;
                            if (great2 == k2) {
                                great2 = great3;
                                break loop9;
                            } else {
                                great2 = great3;
                                great = seventh;
                            }
                        } else {
                            if (a[great2] < pivot1) {
                                a[k2] = a[less];
                                a[less] = a[great2];
                                less++;
                            } else {
                                a[k2] = a[great2];
                            }
                            a[great2] = length2;
                            great2--;
                        }
                    }
                } else {
                    seventh = great;
                }
                ak = ak;
                great = seventh;
            }
            a[left2] = a[less - 1];
            a[less - 1] = pivot1;
            a[right2] = a[great2 + 1];
            a[great2 + 1] = pivot2;
            sort(a, left2, less - 2, leftmost);
            sort(a, great2 + 2, right2, false);
            if (less < e1 && e5 < great2) {
                while (a[less] == pivot1) {
                    less++;
                }
                while (a[great2] == pivot2) {
                    great2--;
                }
                int k3 = less - 1;
                loop13:
                while (true) {
                    k3++;
                    if (k3 > great2) {
                        break;
                    }
                    int ak3 = a[k3];
                    if (ak3 == pivot1) {
                        a[k3] = a[less];
                        a[less] = ak3;
                        less++;
                    } else if (ak3 == pivot2) {
                        while (a[great2] == pivot2) {
                            int great4 = great2 - 1;
                            if (great2 == k3) {
                                great2 = great4;
                                break loop13;
                            }
                            great2 = great4;
                        }
                        if (a[great2] == pivot1) {
                            a[k3] = a[less];
                            a[less] = pivot1;
                            less++;
                        } else {
                            a[k3] = a[great2];
                        }
                        a[great2] = ak3;
                        great2--;
                    } else {
                        continue;
                    }
                }
            }
            sort(a, less, great2, false);
        } else if (leftmost) {
            int i = left;
            int j = i;
            while (i < right2) {
                int ai = a[i + 1];
                while (true) {
                    if (ai >= a[j]) {
                        break;
                    }
                    a[j + 1] = a[j];
                    int j2 = j - 1;
                    if (j == left2) {
                        j = j2;
                        break;
                    }
                    j = j2;
                }
                a[j + 1] = ai;
                i++;
                j = i;
            }
        } else {
            while (left2 < right2) {
                left2++;
                if (a[left2] < a[left2 - 1]) {
                    while (true) {
                        int k4 = left2;
                        int left3 = left2 + 1;
                        if (left3 > right2) {
                            break;
                        }
                        int a1 = a[k4];
                        int a2 = a[left3];
                        if (a1 < a2) {
                            a2 = a1;
                            a1 = a[left3];
                        }
                        while (true) {
                            k4--;
                            if (a1 >= a[k4]) {
                                break;
                            }
                            a[k4 + 2] = a[k4];
                        }
                        int k5 = k4 + 1;
                        a[k5 + 1] = a1;
                        while (true) {
                            k5--;
                            if (a2 >= a[k5]) {
                                break;
                            }
                            a[k5 + 1] = a[k5];
                        }
                        a[k5 + 1] = a2;
                        left2 = left3 + 1;
                    }
                    int last = a[right2];
                    while (true) {
                        right2--;
                        if (last < a[right2]) {
                            a[right2 + 1] = a[right2];
                        } else {
                            a[right2 + 1] = last;
                            return;
                        }
                    }
                }
            }
        }
    }

    /* JADX INFO: Multiple debug info for r8v6 int: [D('blen' int), D('n' int)] */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x00b7  */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x00c1  */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x00c7  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static void sort(long[] r23, int r24, int r25, long[] r26, int r27, int r28) {
        /*
        // Method dump skipped, instructions count: 343
        */
        throw new UnsupportedOperationException("Method not decompiled: java.util.DualPivotQuicksort.sort(long[], int, int, long[], int, int):void");
    }

    /* JADX INFO: Multiple debug info for r11v8 int: [D('less' int), D('t' long)] */
    /* JADX INFO: Multiple debug info for r5v7 long: [D('k' int), D('last' long)] */
    private static void sort(long[] a, int left, int right, boolean leftmost) {
        int left2 = left;
        int right2 = right;
        int length = (right2 - left2) + 1;
        if (length >= 47) {
            int seventh = (length >> 3) + (length >> 6) + 1;
            int e3 = (left2 + right2) >>> 1;
            int e2 = e3 - seventh;
            int e1 = e2 - seventh;
            int e4 = e3 + seventh;
            int e5 = e4 + seventh;
            if (a[e2] < a[e1]) {
                long t = a[e2];
                a[e2] = a[e1];
                a[e1] = t;
            }
            if (a[e3] < a[e2]) {
                long t2 = a[e3];
                a[e3] = a[e2];
                a[e2] = t2;
                if (t2 < a[e1]) {
                    a[e2] = a[e1];
                    a[e1] = t2;
                }
            }
            if (a[e4] < a[e3]) {
                long t3 = a[e4];
                a[e4] = a[e3];
                a[e3] = t3;
                if (t3 < a[e2]) {
                    a[e3] = a[e2];
                    a[e2] = t3;
                    if (t3 < a[e1]) {
                        a[e2] = a[e1];
                        a[e1] = t3;
                    }
                }
            }
            if (a[e5] < a[e4]) {
                long t4 = a[e5];
                a[e5] = a[e4];
                a[e4] = t4;
                if (t4 < a[e3]) {
                    a[e4] = a[e3];
                    a[e3] = t4;
                    if (t4 < a[e2]) {
                        a[e3] = a[e2];
                        a[e2] = t4;
                        if (t4 < a[e1]) {
                            a[e2] = a[e1];
                            a[e1] = t4;
                        }
                    }
                }
            }
            int less = left;
            int great = right;
            if (a[e1] == a[e2] || a[e2] == a[e3] || a[e3] == a[e4] || a[e4] == a[e5]) {
                long pivot = a[e3];
                for (int k = less; k <= great; k++) {
                    if (a[k] != pivot) {
                        long ak = a[k];
                        if (ak < pivot) {
                            a[k] = a[less];
                            a[less] = ak;
                            less++;
                        } else {
                            while (a[great] > pivot) {
                                great--;
                            }
                            if (a[great] < pivot) {
                                a[k] = a[less];
                                a[less] = a[great];
                                less++;
                            } else {
                                a[k] = pivot;
                            }
                            a[great] = ak;
                            great--;
                        }
                    }
                }
                sort(a, left2, less - 1, leftmost);
                sort(a, great + 1, right2, false);
                return;
            }
            long pivot1 = a[e2];
            long pivot2 = a[e4];
            a[e2] = a[left2];
            a[e4] = a[right2];
            do {
                less++;
            } while (a[less] < pivot1);
            do {
                great--;
            } while (a[great] > pivot2);
            int k2 = less - 1;
            loop9:
            while (true) {
                k2++;
                if (k2 > great) {
                    break;
                }
                long ak2 = a[k2];
                if (ak2 < pivot1) {
                    a[k2] = a[less];
                    a[less] = ak2;
                    less++;
                } else if (ak2 > pivot2) {
                    while (a[great] > pivot2) {
                        int great2 = great - 1;
                        if (great == k2) {
                            great = great2;
                            break loop9;
                        }
                        great = great2;
                    }
                    if (a[great] < pivot1) {
                        a[k2] = a[less];
                        a[less] = a[great];
                        less++;
                    } else {
                        a[k2] = a[great];
                    }
                    a[great] = ak2;
                    great--;
                } else {
                    continue;
                }
            }
            a[left2] = a[less - 1];
            a[less - 1] = pivot1;
            a[right2] = a[great + 1];
            a[great + 1] = pivot2;
            sort(a, left2, less - 2, leftmost);
            sort(a, great + 2, right2, false);
            if (less < e1 && e5 < great) {
                while (a[less] == pivot1) {
                    less++;
                }
                while (a[great] == pivot2) {
                    great--;
                }
                int k3 = less - 1;
                loop13:
                while (true) {
                    k3++;
                    if (k3 > great) {
                        break;
                    }
                    long ak3 = a[k3];
                    if (ak3 == pivot1) {
                        a[k3] = a[less];
                        a[less] = ak3;
                        less++;
                    } else if (ak3 == pivot2) {
                        while (a[great] == pivot2) {
                            int great3 = great - 1;
                            if (great == k3) {
                                great = great3;
                                break loop13;
                            }
                            great = great3;
                        }
                        if (a[great] == pivot1) {
                            a[k3] = a[less];
                            a[less] = pivot1;
                            less++;
                        } else {
                            a[k3] = a[great];
                        }
                        a[great] = ak3;
                        great--;
                    } else {
                        continue;
                    }
                }
            }
            sort(a, less, great, false);
        } else if (leftmost) {
            int i = left;
            int j = i;
            while (i < right2) {
                long ai = a[i + 1];
                while (true) {
                    if (ai >= a[j]) {
                        break;
                    }
                    a[j + 1] = a[j];
                    int j2 = j - 1;
                    if (j == left2) {
                        j = j2;
                        break;
                    }
                    j = j2;
                }
                a[j + 1] = ai;
                i++;
                j = i;
            }
        } else {
            while (left2 < right2) {
                left2++;
                if (a[left2] < a[left2 - 1]) {
                    while (true) {
                        int k4 = left2;
                        int left3 = left2 + 1;
                        if (left3 > right2) {
                            break;
                        }
                        long a1 = a[k4];
                        long a2 = a[left3];
                        if (a1 < a2) {
                            a2 = a1;
                            a1 = a[left3];
                        }
                        while (true) {
                            k4--;
                            if (a1 >= a[k4]) {
                                break;
                            }
                            a[k4 + 2] = a[k4];
                        }
                        int k5 = k4 + 1;
                        a[k5 + 1] = a1;
                        while (true) {
                            k5--;
                            if (a2 >= a[k5]) {
                                break;
                            }
                            a[k5 + 1] = a[k5];
                        }
                        a[k5 + 1] = a2;
                        left2 = left3 + 1;
                    }
                    long last = a[right2];
                    while (true) {
                        right2--;
                        if (last < a[right2]) {
                            a[right2 + 1] = a[right2];
                        } else {
                            a[right2 + 1] = last;
                            return;
                        }
                    }
                }
            }
        }
    }

    static void sort(short[] a, int left, int right, short[] work, int workBase, int workLen) {
        int i;
        if (right - left > COUNTING_SORT_THRESHOLD_FOR_SHORT_OR_CHAR) {
            int[] count = new int[65536];
            int i2 = left - 1;
            while (true) {
                i2++;
                if (i2 > right) {
                    break;
                }
                int i3 = a[i2] - Short.MIN_VALUE;
                count[i3] = count[i3] + 1;
            }
            int i4 = 65536;
            int k = right + 1;
            while (k > left) {
                while (true) {
                    i = i4 - 1;
                    if (count[i] != 0) {
                        break;
                    }
                    i4 = i;
                }
                short value = (short) (i - 32768);
                int s = count[i];
                do {
                    k--;
                    a[k] = value;
                    s--;
                } while (s > 0);
                i4 = i;
            }
            return;
        }
        doSort(a, left, right, work, workBase, workLen);
    }

    /* JADX INFO: Multiple debug info for r8v6 int: [D('blen' int), D('n' int)] */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x00f2, code lost:
        if (r0[r2 + r14] <= r0[r3 + r14]) goto L_0x0108;
     */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x00ad  */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x00b7  */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x00bd  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void doSort(short[] r21, int r22, int r23, short[] r24, int r25, int r26) {
        /*
        // Method dump skipped, instructions count: 353
        */
        throw new UnsupportedOperationException("Method not decompiled: java.util.DualPivotQuicksort.doSort(short[], int, int, short[], int, int):void");
    }

    /* JADX INFO: Multiple debug info for r11v4 int: [D('less' int), D('t' short)] */
    /* JADX INFO: Multiple debug info for r4v2 short: [D('length' int), D('pivot' short)] */
    /* JADX INFO: Multiple debug info for r4v18 short: [D('ak' short), D('length' int)] */
    /* JADX INFO: Multiple debug info for r5v23 short: [D('k' int), D('last' short)] */
    private static void sort(short[] a, int left, int right, boolean leftmost) {
        int seventh;
        int left2 = left;
        int right2 = right;
        int length = (right2 - left2) + 1;
        if (length >= 47) {
            int great = (length >> 3) + (length >> 6) + 1;
            int e3 = (left2 + right2) >>> 1;
            int e2 = e3 - great;
            int e1 = e2 - great;
            int e4 = e3 + great;
            int e5 = e4 + great;
            if (a[e2] < a[e1]) {
                short t = a[e2];
                a[e2] = a[e1];
                a[e1] = t;
            }
            if (a[e3] < a[e2]) {
                short t2 = a[e3];
                a[e3] = a[e2];
                a[e2] = t2;
                if (t2 < a[e1]) {
                    a[e2] = a[e1];
                    a[e1] = t2;
                }
            }
            if (a[e4] < a[e3]) {
                short t3 = a[e4];
                a[e4] = a[e3];
                a[e3] = t3;
                if (t3 < a[e2]) {
                    a[e3] = a[e2];
                    a[e2] = t3;
                    if (t3 < a[e1]) {
                        a[e2] = a[e1];
                        a[e1] = t3;
                    }
                }
            }
            if (a[e5] < a[e4]) {
                short t4 = a[e5];
                a[e5] = a[e4];
                a[e4] = t4;
                if (t4 < a[e3]) {
                    a[e4] = a[e3];
                    a[e3] = t4;
                    if (t4 < a[e2]) {
                        a[e3] = a[e2];
                        a[e2] = t4;
                        if (t4 < a[e1]) {
                            a[e2] = a[e1];
                            a[e1] = t4;
                        }
                    }
                }
            }
            int less = left;
            int great2 = right;
            if (a[e1] == a[e2] || a[e2] == a[e3] || a[e3] == a[e4] || a[e4] == a[e5]) {
                short pivot = a[e3];
                for (int k = less; k <= great2; k++) {
                    if (a[k] != pivot) {
                        short ak = a[k];
                        if (ak < pivot) {
                            a[k] = a[less];
                            a[less] = ak;
                            less++;
                        } else {
                            while (a[great2] > pivot) {
                                great2--;
                            }
                            if (a[great2] < pivot) {
                                a[k] = a[less];
                                a[less] = a[great2];
                                less++;
                            } else {
                                a[k] = pivot;
                            }
                            a[great2] = ak;
                            great2--;
                        }
                    }
                }
                sort(a, left2, less - 1, leftmost);
                sort(a, great2 + 1, right2, false);
                return;
            }
            short pivot1 = a[e2];
            short pivot2 = a[e4];
            a[e2] = a[left2];
            a[e4] = a[right2];
            do {
                less++;
            } while (a[less] < pivot1);
            do {
                great2--;
            } while (a[great2] > pivot2);
            int k2 = less - 1;
            loop9:
            while (true) {
                k2++;
                if (k2 > great2) {
                    break;
                }
                short ak2 = a[k2];
                if (ak2 < pivot1) {
                    a[k2] = a[less];
                    a[less] = ak2;
                    less++;
                    seventh = great;
                } else if (ak2 > pivot2) {
                    while (true) {
                        seventh = great;
                        if (a[great2] > pivot2) {
                            int great3 = great2 - 1;
                            if (great2 == k2) {
                                great2 = great3;
                                break loop9;
                            } else {
                                great2 = great3;
                                great = seventh;
                            }
                        } else {
                            if (a[great2] < pivot1) {
                                a[k2] = a[less];
                                a[less] = a[great2];
                                less++;
                            } else {
                                a[k2] = a[great2];
                            }
                            a[great2] = ak2;
                            great2--;
                        }
                    }
                } else {
                    seventh = great;
                }
                length = length;
                great = seventh;
            }
            a[left2] = a[less - 1];
            a[less - 1] = pivot1;
            a[right2] = a[great2 + 1];
            a[great2 + 1] = pivot2;
            sort(a, left2, less - 2, leftmost);
            sort(a, great2 + 2, right2, false);
            if (less < e1 && e5 < great2) {
                while (a[less] == pivot1) {
                    less++;
                }
                while (a[great2] == pivot2) {
                    great2--;
                }
                int k3 = less - 1;
                loop13:
                while (true) {
                    k3++;
                    if (k3 > great2) {
                        break;
                    }
                    short ak3 = a[k3];
                    if (ak3 == pivot1) {
                        a[k3] = a[less];
                        a[less] = ak3;
                        less++;
                    } else if (ak3 == pivot2) {
                        while (a[great2] == pivot2) {
                            int great4 = great2 - 1;
                            if (great2 == k3) {
                                great2 = great4;
                                break loop13;
                            }
                            great2 = great4;
                        }
                        if (a[great2] == pivot1) {
                            a[k3] = a[less];
                            a[less] = pivot1;
                            less++;
                        } else {
                            a[k3] = a[great2];
                        }
                        a[great2] = ak3;
                        great2--;
                    } else {
                        continue;
                    }
                }
            }
            sort(a, less, great2, false);
        } else if (leftmost) {
            int i = left;
            int j = i;
            while (i < right2) {
                short ai = a[i + 1];
                while (true) {
                    if (ai >= a[j]) {
                        break;
                    }
                    a[j + 1] = a[j];
                    int j2 = j - 1;
                    if (j == left2) {
                        j = j2;
                        break;
                    }
                    j = j2;
                }
                a[j + 1] = ai;
                i++;
                j = i;
            }
        } else {
            while (left2 < right2) {
                left2++;
                if (a[left2] < a[left2 - 1]) {
                    while (true) {
                        int k4 = left2;
                        int left3 = left2 + 1;
                        if (left3 > right2) {
                            break;
                        }
                        short a1 = a[k4];
                        short a2 = a[left3];
                        if (a1 < a2) {
                            a2 = a1;
                            a1 = a[left3];
                        }
                        while (true) {
                            k4--;
                            if (a1 >= a[k4]) {
                                break;
                            }
                            a[k4 + 2] = a[k4];
                        }
                        int k5 = k4 + 1;
                        a[k5 + 1] = a1;
                        while (true) {
                            k5--;
                            if (a2 >= a[k5]) {
                                break;
                            }
                            a[k5 + 1] = a[k5];
                        }
                        a[k5 + 1] = a2;
                        left2 = left3 + 1;
                    }
                    short last = a[right2];
                    while (true) {
                        right2--;
                        if (last < a[right2]) {
                            a[right2 + 1] = a[right2];
                        } else {
                            a[right2 + 1] = last;
                            return;
                        }
                    }
                }
            }
        }
    }

    static void sort(char[] a, int left, int right, char[] work, int workBase, int workLen) {
        int i;
        if (right - left > COUNTING_SORT_THRESHOLD_FOR_SHORT_OR_CHAR) {
            int[] count = new int[65536];
            int i2 = left - 1;
            while (true) {
                i2++;
                if (i2 > right) {
                    break;
                }
                char c = a[i2];
                count[c] = count[c] + 1;
            }
            int i3 = 65536;
            int k = right + 1;
            while (k > left) {
                while (true) {
                    i = i3 - 1;
                    if (count[i] != 0) {
                        break;
                    }
                    i3 = i;
                }
                char value = (char) i;
                int s = count[i];
                do {
                    k--;
                    a[k] = value;
                    s--;
                } while (s > 0);
                i3 = i;
            }
            return;
        }
        doSort(a, left, right, work, workBase, workLen);
    }

    /* JADX INFO: Multiple debug info for r8v6 int: [D('blen' int), D('n' int)] */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x00f2, code lost:
        if (r0[r2 + r14] <= r0[r3 + r14]) goto L_0x0108;
     */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x00ad  */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x00b7  */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x00bd  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void doSort(char[] r21, int r22, int r23, char[] r24, int r25, int r26) {
        /*
        // Method dump skipped, instructions count: 353
        */
        throw new UnsupportedOperationException("Method not decompiled: java.util.DualPivotQuicksort.doSort(char[], int, int, char[], int, int):void");
    }

    /* JADX INFO: Multiple debug info for r11v4 int: [D('less' int), D('t' char)] */
    /* JADX INFO: Multiple debug info for r4v2 char: [D('length' int), D('pivot' char)] */
    /* JADX INFO: Multiple debug info for r4v18 char: [D('ak' char), D('length' int)] */
    /* JADX INFO: Multiple debug info for r5v23 char: [D('k' int), D('last' char)] */
    private static void sort(char[] a, int left, int right, boolean leftmost) {
        int seventh;
        int left2 = left;
        int right2 = right;
        int length = (right2 - left2) + 1;
        if (length >= 47) {
            int great = (length >> 3) + (length >> 6) + 1;
            int e3 = (left2 + right2) >>> 1;
            int e2 = e3 - great;
            int e1 = e2 - great;
            int e4 = e3 + great;
            int e5 = e4 + great;
            if (a[e2] < a[e1]) {
                char t = a[e2];
                a[e2] = a[e1];
                a[e1] = t;
            }
            if (a[e3] < a[e2]) {
                char t2 = a[e3];
                a[e3] = a[e2];
                a[e2] = t2;
                if (t2 < a[e1]) {
                    a[e2] = a[e1];
                    a[e1] = t2;
                }
            }
            if (a[e4] < a[e3]) {
                char t3 = a[e4];
                a[e4] = a[e3];
                a[e3] = t3;
                if (t3 < a[e2]) {
                    a[e3] = a[e2];
                    a[e2] = t3;
                    if (t3 < a[e1]) {
                        a[e2] = a[e1];
                        a[e1] = t3;
                    }
                }
            }
            if (a[e5] < a[e4]) {
                char t4 = a[e5];
                a[e5] = a[e4];
                a[e4] = t4;
                if (t4 < a[e3]) {
                    a[e4] = a[e3];
                    a[e3] = t4;
                    if (t4 < a[e2]) {
                        a[e3] = a[e2];
                        a[e2] = t4;
                        if (t4 < a[e1]) {
                            a[e2] = a[e1];
                            a[e1] = t4;
                        }
                    }
                }
            }
            int less = left;
            int great2 = right;
            if (a[e1] == a[e2] || a[e2] == a[e3] || a[e3] == a[e4] || a[e4] == a[e5]) {
                char pivot = a[e3];
                for (int k = less; k <= great2; k++) {
                    if (a[k] != pivot) {
                        char ak = a[k];
                        if (ak < pivot) {
                            a[k] = a[less];
                            a[less] = ak;
                            less++;
                        } else {
                            while (a[great2] > pivot) {
                                great2--;
                            }
                            if (a[great2] < pivot) {
                                a[k] = a[less];
                                a[less] = a[great2];
                                less++;
                            } else {
                                a[k] = pivot;
                            }
                            a[great2] = ak;
                            great2--;
                        }
                    }
                }
                sort(a, left2, less - 1, leftmost);
                sort(a, great2 + 1, right2, false);
                return;
            }
            char pivot1 = a[e2];
            char pivot2 = a[e4];
            a[e2] = a[left2];
            a[e4] = a[right2];
            do {
                less++;
            } while (a[less] < pivot1);
            do {
                great2--;
            } while (a[great2] > pivot2);
            int k2 = less - 1;
            loop9:
            while (true) {
                k2++;
                if (k2 > great2) {
                    break;
                }
                char ak2 = a[k2];
                if (ak2 < pivot1) {
                    a[k2] = a[less];
                    a[less] = ak2;
                    less++;
                    seventh = great;
                } else if (ak2 > pivot2) {
                    while (true) {
                        seventh = great;
                        if (a[great2] > pivot2) {
                            int great3 = great2 - 1;
                            if (great2 == k2) {
                                great2 = great3;
                                break loop9;
                            } else {
                                great2 = great3;
                                great = seventh;
                            }
                        } else {
                            if (a[great2] < pivot1) {
                                a[k2] = a[less];
                                a[less] = a[great2];
                                less++;
                            } else {
                                a[k2] = a[great2];
                            }
                            a[great2] = ak2;
                            great2--;
                        }
                    }
                } else {
                    seventh = great;
                }
                length = length;
                great = seventh;
            }
            a[left2] = a[less - 1];
            a[less - 1] = pivot1;
            a[right2] = a[great2 + 1];
            a[great2 + 1] = pivot2;
            sort(a, left2, less - 2, leftmost);
            sort(a, great2 + 2, right2, false);
            if (less < e1 && e5 < great2) {
                while (a[less] == pivot1) {
                    less++;
                }
                while (a[great2] == pivot2) {
                    great2--;
                }
                int k3 = less - 1;
                loop13:
                while (true) {
                    k3++;
                    if (k3 > great2) {
                        break;
                    }
                    char ak3 = a[k3];
                    if (ak3 == pivot1) {
                        a[k3] = a[less];
                        a[less] = ak3;
                        less++;
                    } else if (ak3 == pivot2) {
                        while (a[great2] == pivot2) {
                            int great4 = great2 - 1;
                            if (great2 == k3) {
                                great2 = great4;
                                break loop13;
                            }
                            great2 = great4;
                        }
                        if (a[great2] == pivot1) {
                            a[k3] = a[less];
                            a[less] = pivot1;
                            less++;
                        } else {
                            a[k3] = a[great2];
                        }
                        a[great2] = ak3;
                        great2--;
                    } else {
                        continue;
                    }
                }
            }
            sort(a, less, great2, false);
        } else if (leftmost) {
            int i = left;
            int j = i;
            while (i < right2) {
                char ai = a[i + 1];
                while (true) {
                    if (ai >= a[j]) {
                        break;
                    }
                    a[j + 1] = a[j];
                    int j2 = j - 1;
                    if (j == left2) {
                        j = j2;
                        break;
                    }
                    j = j2;
                }
                a[j + 1] = ai;
                i++;
                j = i;
            }
        } else {
            while (left2 < right2) {
                left2++;
                if (a[left2] < a[left2 - 1]) {
                    while (true) {
                        int k4 = left2;
                        int left3 = left2 + 1;
                        if (left3 > right2) {
                            break;
                        }
                        char a1 = a[k4];
                        char a2 = a[left3];
                        if (a1 < a2) {
                            a2 = a1;
                            a1 = a[left3];
                        }
                        while (true) {
                            k4--;
                            if (a1 >= a[k4]) {
                                break;
                            }
                            a[k4 + 2] = a[k4];
                        }
                        int k5 = k4 + 1;
                        a[k5 + 1] = a1;
                        while (true) {
                            k5--;
                            if (a2 >= a[k5]) {
                                break;
                            }
                            a[k5 + 1] = a[k5];
                        }
                        a[k5 + 1] = a2;
                        left2 = left3 + 1;
                    }
                    char last = a[right2];
                    while (true) {
                        right2--;
                        if (last < a[right2]) {
                            a[right2 + 1] = a[right2];
                        } else {
                            a[right2 + 1] = last;
                            return;
                        }
                    }
                }
            }
        }
    }

    static void sort(byte[] a, int left, int right) {
        int i;
        if (right - left > 29) {
            int[] count = new int[256];
            int i2 = left - 1;
            while (true) {
                i2++;
                if (i2 > right) {
                    break;
                }
                int i3 = a[i2] + 128;
                count[i3] = count[i3] + 1;
            }
            int i4 = 256;
            int k = right + 1;
            while (k > left) {
                while (true) {
                    i = i4 - 1;
                    if (count[i] != 0) {
                        break;
                    }
                    i4 = i;
                }
                byte value = (byte) (i - 128);
                int s = count[i];
                do {
                    k--;
                    a[k] = value;
                    s--;
                } while (s > 0);
                i4 = i;
            }
            return;
        }
        int i5 = left;
        int j = i5;
        while (i5 < right) {
            byte ai = a[i5 + 1];
            while (true) {
                if (ai >= a[j]) {
                    break;
                }
                a[j + 1] = a[j];
                int j2 = j - 1;
                if (j == left) {
                    j = j2;
                    break;
                }
                j = j2;
            }
            a[j + 1] = ai;
            i5++;
            j = i5;
        }
    }

    static void sort(float[] a, int left, int right, float[] work, int workBase, int workLen) {
        while (left <= right && Float.isNaN(a[right])) {
            right--;
        }
        int k = right;
        while (true) {
            k--;
            if (k < left) {
                break;
            }
            float ak = a[k];
            if (ak != ak) {
                a[k] = a[right];
                a[right] = ak;
                right--;
            }
        }
        doSort(a, left, right, work, workBase, workLen);
        int hi = right;
        while (left < hi) {
            int middle = (left + hi) >>> 1;
            if (a[middle] < 0.0f) {
                left = middle + 1;
            } else {
                hi = middle;
            }
        }
        while (left <= right && Float.floatToRawIntBits(a[left]) < 0) {
            left++;
        }
        int k2 = left;
        int p = left - 1;
        while (true) {
            k2++;
            if (k2 <= right) {
                float ak2 = a[k2];
                if (ak2 == 0.0f) {
                    if (Float.floatToRawIntBits(ak2) < 0) {
                        a[k2] = 0.0f;
                        p++;
                        a[p] = -0.0f;
                    }
                } else {
                    return;
                }
            } else {
                return;
            }
        }
    }

    /* JADX INFO: Multiple debug info for r8v6 int: [D('blen' int), D('n' int)] */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x00b7  */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x00c1  */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x00c7  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void doSort(float[] r21, int r22, int r23, float[] r24, int r25, int r26) {
        /*
        // Method dump skipped, instructions count: 343
        */
        throw new UnsupportedOperationException("Method not decompiled: java.util.DualPivotQuicksort.doSort(float[], int, int, float[], int, int):void");
    }

    /* JADX INFO: Multiple debug info for r11v8 int: [D('less' int), D('t' float)] */
    /* JADX INFO: Multiple debug info for r4v2 float: [D('pivot' float), D('length' int)] */
    /* JADX INFO: Multiple debug info for r5v7 float: [D('k' int), D('last' float)] */
    private static void sort(float[] a, int left, int right, boolean leftmost) {
        int left2 = left;
        int right2 = right;
        int length = (right2 - left2) + 1;
        if (length >= 47) {
            int seventh = (length >> 3) + (length >> 6) + 1;
            int e3 = (left2 + right2) >>> 1;
            int e2 = e3 - seventh;
            int e1 = e2 - seventh;
            int e4 = e3 + seventh;
            int e5 = e4 + seventh;
            if (a[e2] < a[e1]) {
                float t = a[e2];
                a[e2] = a[e1];
                a[e1] = t;
            }
            if (a[e3] < a[e2]) {
                float t2 = a[e3];
                a[e3] = a[e2];
                a[e2] = t2;
                if (t2 < a[e1]) {
                    a[e2] = a[e1];
                    a[e1] = t2;
                }
            }
            if (a[e4] < a[e3]) {
                float t3 = a[e4];
                a[e4] = a[e3];
                a[e3] = t3;
                if (t3 < a[e2]) {
                    a[e3] = a[e2];
                    a[e2] = t3;
                    if (t3 < a[e1]) {
                        a[e2] = a[e1];
                        a[e1] = t3;
                    }
                }
            }
            if (a[e5] < a[e4]) {
                float t4 = a[e5];
                a[e5] = a[e4];
                a[e4] = t4;
                if (t4 < a[e3]) {
                    a[e4] = a[e3];
                    a[e3] = t4;
                    if (t4 < a[e2]) {
                        a[e3] = a[e2];
                        a[e2] = t4;
                        if (t4 < a[e1]) {
                            a[e2] = a[e1];
                            a[e1] = t4;
                        }
                    }
                }
            }
            int less = left;
            int great = right;
            if (a[e1] == a[e2] || a[e2] == a[e3] || a[e3] == a[e4] || a[e4] == a[e5]) {
                float pivot = a[e3];
                for (int k = less; k <= great; k++) {
                    if (a[k] != pivot) {
                        float ak = a[k];
                        if (ak < pivot) {
                            a[k] = a[less];
                            a[less] = ak;
                            less++;
                        } else {
                            while (a[great] > pivot) {
                                great--;
                            }
                            if (a[great] < pivot) {
                                a[k] = a[less];
                                a[less] = a[great];
                                less++;
                            } else {
                                a[k] = a[great];
                            }
                            a[great] = ak;
                            great--;
                        }
                    }
                }
                sort(a, left2, less - 1, leftmost);
                sort(a, great + 1, right2, false);
                return;
            }
            float pivot1 = a[e2];
            float pivot2 = a[e4];
            a[e2] = a[left2];
            a[e4] = a[right2];
            do {
                less++;
            } while (a[less] < pivot1);
            do {
                great--;
            } while (a[great] > pivot2);
            int k2 = less - 1;
            loop9:
            while (true) {
                int k3 = k2 + 1;
                if (k3 > great) {
                    break;
                }
                float ak2 = a[k3];
                if (ak2 < pivot1) {
                    a[k3] = a[less];
                    a[less] = ak2;
                    less++;
                } else if (ak2 > pivot2) {
                    while (a[great] > pivot2) {
                        int great2 = great - 1;
                        if (great == k3) {
                            great = great2;
                            break loop9;
                        }
                        great = great2;
                    }
                    if (a[great] < pivot1) {
                        a[k3] = a[less];
                        a[less] = a[great];
                        less++;
                    } else {
                        a[k3] = a[great];
                    }
                    a[great] = ak2;
                    great--;
                } else {
                    continue;
                }
                k2 = k3;
            }
            a[left2] = a[less - 1];
            a[less - 1] = pivot1;
            a[right2] = a[great + 1];
            a[great + 1] = pivot2;
            sort(a, left2, less - 2, leftmost);
            sort(a, great + 2, right2, false);
            if (less < e1 && e5 < great) {
                while (a[less] == pivot1) {
                    less++;
                }
                while (a[great] == pivot2) {
                    great--;
                }
                int k4 = less - 1;
                loop13:
                while (true) {
                    k4++;
                    if (k4 > great) {
                        break;
                    }
                    float ak3 = a[k4];
                    if (ak3 == pivot1) {
                        a[k4] = a[less];
                        a[less] = ak3;
                        less++;
                    } else if (ak3 == pivot2) {
                        while (a[great] == pivot2) {
                            int great3 = great - 1;
                            if (great == k4) {
                                great = great3;
                                break loop13;
                            }
                            great = great3;
                        }
                        if (a[great] == pivot1) {
                            a[k4] = a[less];
                            a[less] = a[great];
                            less++;
                        } else {
                            a[k4] = a[great];
                        }
                        a[great] = ak3;
                        great--;
                    } else {
                        continue;
                    }
                }
            }
            sort(a, less, great, false);
        } else if (leftmost) {
            int i = left;
            int j = i;
            while (i < right2) {
                float ai = a[i + 1];
                while (true) {
                    if (ai >= a[j]) {
                        break;
                    }
                    a[j + 1] = a[j];
                    int j2 = j - 1;
                    if (j == left2) {
                        j = j2;
                        break;
                    }
                    j = j2;
                }
                a[j + 1] = ai;
                i++;
                j = i;
            }
        } else {
            while (left2 < right2) {
                left2++;
                if (a[left2] < a[left2 - 1]) {
                    while (true) {
                        int k5 = left2;
                        int left3 = left2 + 1;
                        if (left3 > right2) {
                            break;
                        }
                        float a1 = a[k5];
                        float a2 = a[left3];
                        if (a1 < a2) {
                            a2 = a1;
                            a1 = a[left3];
                        }
                        while (true) {
                            k5--;
                            if (a1 >= a[k5]) {
                                break;
                            }
                            a[k5 + 2] = a[k5];
                        }
                        int k6 = k5 + 1;
                        a[k6 + 1] = a1;
                        while (true) {
                            k6--;
                            if (a2 >= a[k6]) {
                                break;
                            }
                            a[k6 + 1] = a[k6];
                        }
                        a[k6 + 1] = a2;
                        left2 = left3 + 1;
                    }
                    float last = a[right2];
                    while (true) {
                        right2--;
                        if (last < a[right2]) {
                            a[right2 + 1] = a[right2];
                        } else {
                            a[right2 + 1] = last;
                            return;
                        }
                    }
                }
            }
        }
    }

    static void sort(double[] a, int left, int right, double[] work, int workBase, int workLen) {
        int left2 = left;
        int right2 = right;
        while (left2 <= right2 && Double.isNaN(a[right2])) {
            right2--;
        }
        int k = right2;
        int right3 = right2;
        while (true) {
            k--;
            if (k < left2) {
                break;
            }
            double ak = a[k];
            if (ak != ak) {
                a[k] = a[right3];
                a[right3] = ak;
                right3--;
            }
        }
        doSort(a, left, right3, work, workBase, workLen);
        int hi = right3;
        while (left2 < hi) {
            int middle = (left2 + hi) >>> 1;
            if (a[middle] < 0.0d) {
                left2 = middle + 1;
            } else {
                hi = middle;
            }
        }
        while (left2 <= right3 && Double.doubleToRawLongBits(a[left2]) < 0) {
            left2++;
        }
        int k2 = left2;
        int p = left2 - 1;
        while (true) {
            k2++;
            if (k2 <= right3) {
                double ak2 = a[k2];
                if (ak2 == 0.0d) {
                    if (Double.doubleToRawLongBits(ak2) < 0) {
                        a[k2] = 0.0d;
                        p++;
                        a[p] = -0.0d;
                    }
                } else {
                    return;
                }
            } else {
                return;
            }
        }
    }

    /* JADX INFO: Multiple debug info for r8v6 int: [D('blen' int), D('n' int)] */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x00b7  */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x00c1  */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x00c7  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void doSort(double[] r23, int r24, int r25, double[] r26, int r27, int r28) {
        /*
        // Method dump skipped, instructions count: 343
        */
        throw new UnsupportedOperationException("Method not decompiled: java.util.DualPivotQuicksort.doSort(double[], int, int, double[], int, int):void");
    }

    /* JADX INFO: Multiple debug info for r11v8 int: [D('less' int), D('t' double)] */
    /* JADX INFO: Multiple debug info for r5v7 double: [D('k' int), D('last' double)] */
    private static void sort(double[] a, int left, int right, boolean leftmost) {
        int left2 = left;
        int right2 = right;
        int length = (right2 - left2) + 1;
        if (length >= 47) {
            int seventh = (length >> 3) + (length >> 6) + 1;
            int e3 = (left2 + right2) >>> 1;
            int e2 = e3 - seventh;
            int e1 = e2 - seventh;
            int e4 = e3 + seventh;
            int e5 = e4 + seventh;
            if (a[e2] < a[e1]) {
                double t = a[e2];
                a[e2] = a[e1];
                a[e1] = t;
            }
            if (a[e3] < a[e2]) {
                double t2 = a[e3];
                a[e3] = a[e2];
                a[e2] = t2;
                if (t2 < a[e1]) {
                    a[e2] = a[e1];
                    a[e1] = t2;
                }
            }
            if (a[e4] < a[e3]) {
                double t3 = a[e4];
                a[e4] = a[e3];
                a[e3] = t3;
                if (t3 < a[e2]) {
                    a[e3] = a[e2];
                    a[e2] = t3;
                    if (t3 < a[e1]) {
                        a[e2] = a[e1];
                        a[e1] = t3;
                    }
                }
            }
            if (a[e5] < a[e4]) {
                double t4 = a[e5];
                a[e5] = a[e4];
                a[e4] = t4;
                if (t4 < a[e3]) {
                    a[e4] = a[e3];
                    a[e3] = t4;
                    if (t4 < a[e2]) {
                        a[e3] = a[e2];
                        a[e2] = t4;
                        if (t4 < a[e1]) {
                            a[e2] = a[e1];
                            a[e1] = t4;
                        }
                    }
                }
            }
            int less = left;
            int great = right;
            if (a[e1] == a[e2] || a[e2] == a[e3] || a[e3] == a[e4] || a[e4] == a[e5]) {
                double pivot = a[e3];
                for (int k = less; k <= great; k++) {
                    if (a[k] != pivot) {
                        double ak = a[k];
                        if (ak < pivot) {
                            a[k] = a[less];
                            a[less] = ak;
                            less++;
                        } else {
                            while (a[great] > pivot) {
                                great--;
                            }
                            if (a[great] < pivot) {
                                a[k] = a[less];
                                a[less] = a[great];
                                less++;
                            } else {
                                a[k] = a[great];
                            }
                            a[great] = ak;
                            great--;
                        }
                    }
                }
                sort(a, left2, less - 1, leftmost);
                sort(a, great + 1, right2, false);
                return;
            }
            double pivot1 = a[e2];
            double pivot2 = a[e4];
            a[e2] = a[left2];
            a[e4] = a[right2];
            do {
                less++;
            } while (a[less] < pivot1);
            do {
                great--;
            } while (a[great] > pivot2);
            int k2 = less - 1;
            loop9:
            while (true) {
                k2++;
                if (k2 > great) {
                    break;
                }
                double ak2 = a[k2];
                if (ak2 < pivot1) {
                    a[k2] = a[less];
                    a[less] = ak2;
                    less++;
                } else if (ak2 > pivot2) {
                    while (a[great] > pivot2) {
                        int great2 = great - 1;
                        if (great == k2) {
                            great = great2;
                            break loop9;
                        }
                        great = great2;
                    }
                    if (a[great] < pivot1) {
                        a[k2] = a[less];
                        a[less] = a[great];
                        less++;
                    } else {
                        a[k2] = a[great];
                    }
                    a[great] = ak2;
                    great--;
                } else {
                    continue;
                }
            }
            a[left2] = a[less - 1];
            a[less - 1] = pivot1;
            a[right2] = a[great + 1];
            a[great + 1] = pivot2;
            sort(a, left2, less - 2, leftmost);
            sort(a, great + 2, right2, false);
            if (less < e1 && e5 < great) {
                while (a[less] == pivot1) {
                    less++;
                }
                while (a[great] == pivot2) {
                    great--;
                }
                int k3 = less - 1;
                loop13:
                while (true) {
                    k3++;
                    if (k3 > great) {
                        break;
                    }
                    double ak3 = a[k3];
                    if (ak3 == pivot1) {
                        a[k3] = a[less];
                        a[less] = ak3;
                        less++;
                    } else if (ak3 == pivot2) {
                        while (a[great] == pivot2) {
                            int great3 = great - 1;
                            if (great == k3) {
                                great = great3;
                                break loop13;
                            }
                            great = great3;
                        }
                        if (a[great] == pivot1) {
                            a[k3] = a[less];
                            a[less] = a[great];
                            less++;
                        } else {
                            a[k3] = a[great];
                        }
                        a[great] = ak3;
                        great--;
                    } else {
                        continue;
                    }
                }
            }
            sort(a, less, great, false);
        } else if (leftmost) {
            int i = left;
            int j = i;
            while (i < right2) {
                double ai = a[i + 1];
                while (true) {
                    if (ai >= a[j]) {
                        break;
                    }
                    a[j + 1] = a[j];
                    int j2 = j - 1;
                    if (j == left2) {
                        j = j2;
                        break;
                    }
                    j = j2;
                }
                a[j + 1] = ai;
                i++;
                j = i;
            }
        } else {
            while (left2 < right2) {
                left2++;
                if (a[left2] < a[left2 - 1]) {
                    while (true) {
                        int k4 = left2;
                        int left3 = left2 + 1;
                        if (left3 > right2) {
                            break;
                        }
                        double a1 = a[k4];
                        double a2 = a[left3];
                        if (a1 < a2) {
                            a2 = a1;
                            a1 = a[left3];
                        }
                        while (true) {
                            k4--;
                            if (a1 >= a[k4]) {
                                break;
                            }
                            a[k4 + 2] = a[k4];
                        }
                        int k5 = k4 + 1;
                        a[k5 + 1] = a1;
                        while (true) {
                            k5--;
                            if (a2 >= a[k5]) {
                                break;
                            }
                            a[k5 + 1] = a[k5];
                        }
                        a[k5 + 1] = a2;
                        left2 = left3 + 1;
                    }
                    double last = a[right2];
                    while (true) {
                        right2--;
                        if (last < a[right2]) {
                            a[right2 + 1] = a[right2];
                        } else {
                            a[right2 + 1] = last;
                            return;
                        }
                    }
                }
            }
        }
    }
}
