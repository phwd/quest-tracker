package java.util;

/* access modifiers changed from: package-private */
public final class DualPivotQuicksort {
    static void sort(long[] jArr, int i, int i2, long[] jArr2, int i3, int i4) {
        int i5;
        int i6;
        int i7;
        long[] jArr3 = jArr;
        long[] jArr4 = jArr2;
        if (i2 - i < 286) {
            sort(jArr3, i, i2, true);
            return;
        }
        int[] iArr = new int[68];
        iArr[0] = i;
        int i8 = i;
        int i9 = 0;
        while (i8 < i2) {
            int i10 = i8 + 1;
            if (jArr3[i8] < jArr3[i10]) {
                do {
                    i8++;
                    if (i8 > i2) {
                        break;
                    }
                } while (jArr3[i8 - 1] <= jArr3[i8]);
            } else if (jArr3[i8] <= jArr3[i10]) {
                int i11 = 33;
                while (true) {
                    i8++;
                    if (i8 > i2 || jArr3[i8 - 1] != jArr3[i8]) {
                        break;
                    }
                    i11--;
                    if (i11 == 0) {
                        sort(jArr3, i, i2, true);
                        return;
                    }
                }
            } else {
                do {
                    i8++;
                    if (i8 > i2) {
                        break;
                    }
                } while (jArr3[i8 - 1] >= jArr3[i8]);
                int i12 = iArr[i9] - 1;
                int i13 = i8;
                while (true) {
                    i12++;
                    i13--;
                    if (i12 >= i13) {
                        break;
                    }
                    long j = jArr3[i12];
                    jArr3[i12] = jArr3[i13];
                    jArr3[i13] = j;
                }
            }
            i9++;
            if (i9 == 67) {
                sort(jArr3, i, i2, true);
                return;
            }
            iArr[i9] = i8;
        }
        int i14 = i2 + 1;
        if (iArr[i9] == i2) {
            i9++;
            iArr[i9] = i14;
        } else if (i9 == 1) {
            return;
        }
        int i15 = 1;
        byte b = 0;
        while (true) {
            i15 <<= 1;
            if (i15 >= i9) {
                break;
            }
            b = (byte) (b ^ 1);
        }
        int i16 = i14 - i;
        if (jArr4 == null || i4 < i16 || i3 + i16 > jArr4.length) {
            jArr4 = new long[i16];
            i5 = 0;
        } else {
            i5 = i3;
        }
        if (b == 0) {
            System.arraycopy(jArr3, i, jArr4, i5, i16);
            i7 = i5 - i;
            i6 = 0;
            jArr4 = jArr3;
            jArr3 = jArr4;
        } else {
            i6 = i5 - i;
            i7 = 0;
        }
        while (i9 > 1) {
            int i17 = 0;
            for (int i18 = 2; i18 <= i9; i18 += 2) {
                int i19 = iArr[i18];
                int i20 = iArr[i18 - 1];
                int i21 = iArr[i18 - 2];
                int i22 = i20;
                int i23 = i21;
                while (i21 < i19) {
                    if (i22 >= i19 || (i23 < i20 && jArr3[i23 + i7] <= jArr3[i22 + i7])) {
                        jArr4[i21 + i6] = jArr3[i23 + i7];
                        i23++;
                    } else {
                        jArr4[i21 + i6] = jArr3[i22 + i7];
                        i22++;
                    }
                    i21++;
                }
                i17++;
                iArr[i17] = i19;
            }
            if ((i9 & 1) != 0) {
                int i24 = iArr[i9 - 1];
                int i25 = i14;
                while (true) {
                    i25--;
                    if (i25 < i24) {
                        break;
                    }
                    jArr4[i25 + i6] = jArr3[i25 + i7];
                }
                i17++;
                iArr[i17] = i14;
            }
            i9 = i17;
            jArr4 = jArr3;
            jArr3 = jArr4;
            i6 = i7;
            i7 = i6;
        }
    }

    private static void sort(long[] jArr, int i, int i2, boolean z) {
        int i3 = i;
        int i4 = i2;
        int i5 = (i4 - i3) + 1;
        if (i5 >= 47) {
            int i6 = (i5 >> 3) + (i5 >> 6) + 1;
            int i7 = (i3 + i4) >>> 1;
            int i8 = i7 - i6;
            int i9 = i8 - i6;
            int i10 = i7 + i6;
            int i11 = i6 + i10;
            if (jArr[i8] < jArr[i9]) {
                long j = jArr[i8];
                jArr[i8] = jArr[i9];
                jArr[i9] = j;
            }
            if (jArr[i7] < jArr[i8]) {
                long j2 = jArr[i7];
                jArr[i7] = jArr[i8];
                jArr[i8] = j2;
                if (j2 < jArr[i9]) {
                    jArr[i8] = jArr[i9];
                    jArr[i9] = j2;
                }
            }
            if (jArr[i10] < jArr[i7]) {
                long j3 = jArr[i10];
                jArr[i10] = jArr[i7];
                jArr[i7] = j3;
                if (j3 < jArr[i8]) {
                    jArr[i7] = jArr[i8];
                    jArr[i8] = j3;
                    if (j3 < jArr[i9]) {
                        jArr[i8] = jArr[i9];
                        jArr[i9] = j3;
                    }
                }
            }
            if (jArr[i11] < jArr[i10]) {
                long j4 = jArr[i11];
                jArr[i11] = jArr[i10];
                jArr[i10] = j4;
                if (j4 < jArr[i7]) {
                    jArr[i10] = jArr[i7];
                    jArr[i7] = j4;
                    if (j4 < jArr[i8]) {
                        jArr[i7] = jArr[i8];
                        jArr[i8] = j4;
                        if (j4 < jArr[i9]) {
                            jArr[i8] = jArr[i9];
                            jArr[i9] = j4;
                        }
                    }
                }
            }
            if (jArr[i9] == jArr[i8] || jArr[i8] == jArr[i7] || jArr[i7] == jArr[i10] || jArr[i10] == jArr[i11]) {
                long j5 = jArr[i7];
                int i12 = i3;
                int i13 = i12;
                int i14 = i4;
                while (i12 <= i14) {
                    if (jArr[i12] != j5) {
                        long j6 = jArr[i12];
                        if (j6 < j5) {
                            jArr[i12] = jArr[i13];
                            jArr[i13] = j6;
                            i13++;
                        } else {
                            while (jArr[i14] > j5) {
                                i14--;
                            }
                            if (jArr[i14] < j5) {
                                jArr[i12] = jArr[i13];
                                jArr[i13] = jArr[i14];
                                i13++;
                            } else {
                                jArr[i12] = j5;
                            }
                            jArr[i14] = j6;
                            i14--;
                        }
                    }
                    i12++;
                }
                sort(jArr, i3, i13 - 1, z);
                sort(jArr, i14 + 1, i4, false);
                return;
            }
            long j7 = jArr[i8];
            long j8 = jArr[i10];
            jArr[i8] = jArr[i3];
            jArr[i10] = jArr[i4];
            int i15 = i3;
            do {
                i15++;
            } while (jArr[i15] < j7);
            int i16 = i4;
            do {
                i16--;
            } while (jArr[i16] > j8);
            int i17 = i15 - 1;
            loop9:
            while (true) {
                i17++;
                if (i17 > i16) {
                    break;
                }
                long j9 = jArr[i17];
                if (j9 < j7) {
                    jArr[i17] = jArr[i15];
                    jArr[i15] = j9;
                    i15++;
                } else if (j9 > j8) {
                    while (jArr[i16] > j8) {
                        int i18 = i16 - 1;
                        if (i16 == i17) {
                            i16 = i18;
                            break loop9;
                        }
                        i16 = i18;
                    }
                    if (jArr[i16] < j7) {
                        jArr[i17] = jArr[i15];
                        jArr[i15] = jArr[i16];
                        i15++;
                    } else {
                        jArr[i17] = jArr[i16];
                    }
                    jArr[i16] = j9;
                    i16--;
                } else {
                    continue;
                }
            }
            int i19 = i15 - 1;
            jArr[i3] = jArr[i19];
            jArr[i19] = j7;
            int i20 = i16 + 1;
            jArr[i4] = jArr[i20];
            jArr[i20] = j8;
            sort(jArr, i3, i15 - 2, z);
            sort(jArr, i16 + 2, i4, false);
            if (i15 < i9 && i11 < i16) {
                while (jArr[i15] == j7) {
                    i15++;
                }
                while (jArr[i16] == j8) {
                    i16--;
                }
                int i21 = i15 - 1;
                loop13:
                while (true) {
                    i21++;
                    if (i21 > i16) {
                        break;
                    }
                    long j10 = jArr[i21];
                    if (j10 == j7) {
                        jArr[i21] = jArr[i15];
                        jArr[i15] = j10;
                        i15++;
                    } else if (j10 == j8) {
                        while (jArr[i16] == j8) {
                            int i22 = i16 - 1;
                            if (i16 == i21) {
                                i16 = i22;
                                break loop13;
                            }
                            i16 = i22;
                        }
                        if (jArr[i16] == j7) {
                            jArr[i21] = jArr[i15];
                            jArr[i15] = j7;
                            i15++;
                        } else {
                            jArr[i21] = jArr[i16];
                        }
                        jArr[i16] = j10;
                        i16--;
                    } else {
                        continue;
                    }
                }
            }
            sort(jArr, i15, i16, false);
        } else if (z) {
            int i23 = i3;
            while (i23 < i4) {
                int i24 = i23 + 1;
                long j11 = jArr[i24];
                while (true) {
                    if (j11 >= jArr[i23]) {
                        break;
                    }
                    jArr[i23 + 1] = jArr[i23];
                    int i25 = i23 - 1;
                    if (i23 == i3) {
                        i23 = i25;
                        break;
                    }
                    i23 = i25;
                }
                jArr[i23 + 1] = j11;
                i23 = i24;
            }
        } else {
            while (i3 < i4) {
                i3++;
                if (jArr[i3] < jArr[i3 - 1]) {
                    while (true) {
                        int i26 = i3 + 1;
                        if (i26 > i4) {
                            break;
                        }
                        long j12 = jArr[i3];
                        long j13 = jArr[i26];
                        if (j12 < j13) {
                            j12 = jArr[i26];
                            j13 = j12;
                        }
                        while (true) {
                            i3--;
                            if (j12 >= jArr[i3]) {
                                break;
                            }
                            jArr[i3 + 2] = jArr[i3];
                        }
                        int i27 = i3 + 1;
                        jArr[i27 + 1] = j12;
                        while (true) {
                            i27--;
                            if (j13 >= jArr[i27]) {
                                break;
                            }
                            jArr[i27 + 1] = jArr[i27];
                        }
                        jArr[i27 + 1] = j13;
                        i3 = i26 + 1;
                    }
                    long j14 = jArr[i4];
                    while (true) {
                        i4--;
                        if (j14 < jArr[i4]) {
                            jArr[i4 + 1] = jArr[i4];
                        } else {
                            jArr[i4 + 1] = j14;
                            return;
                        }
                    }
                }
            }
        }
    }
}
