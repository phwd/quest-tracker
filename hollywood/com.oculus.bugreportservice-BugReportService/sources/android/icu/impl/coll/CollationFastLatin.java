package android.icu.impl.coll;

public final class CollationFastLatin {
    private static int getCases(int i, boolean z, int i2) {
        if (i2 > 65535) {
            int i3 = 65535 & i2;
            if (i3 >= 4096) {
                return (!z || (-67108864 & i2) != 0) ? i2 & 1572888 : i2 & 24;
            }
            if (i3 > i) {
                return 524296;
            }
        } else if (i2 >= 4096) {
            int i4 = i2 & 24;
            if (!z && (i2 & 992) >= 384) {
                i4 |= 524288;
            }
            return i4;
        } else if (i2 > i) {
            return 8;
        } else {
            if (i2 < 3072) {
                return i2;
            }
        }
        return 0;
    }

    private static int getPrimaries(int i, int i2) {
        int i3;
        int i4 = 65535 & i2;
        if (i4 >= 4096) {
            i3 = -67044352;
        } else if (i4 > i) {
            i3 = -458760;
        } else if (i4 >= 3072) {
            return 0;
        } else {
            return i2;
        }
        return i3 & i2;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0013, code lost:
        if ((r4 & 992) >= 384) goto L_0x0026;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static int getQuaternaries(int r3, int r4) {
        /*
            r0 = 64512(0xfc00, float:9.04E-41)
            r1 = -67044352(0xfffffffffc00fc00, float:-2.6789007E36)
            r2 = 65535(0xffff, float:9.1834E-41)
            if (r4 > r2) goto L_0x0022
            r2 = 4096(0x1000, float:5.74E-42)
            if (r4 < r2) goto L_0x0016
            r3 = r4 & 992(0x3e0, float:1.39E-42)
            r4 = 384(0x180, float:5.38E-43)
            if (r3 < r4) goto L_0x0018
            goto L_0x0026
        L_0x0016:
            if (r4 <= r3) goto L_0x001a
        L_0x0018:
            r4 = r0
            goto L_0x002c
        L_0x001a:
            r3 = 3072(0xc00, float:4.305E-42)
            if (r4 < r3) goto L_0x002c
            r3 = 65528(0xfff8, float:9.1824E-41)
            goto L_0x002b
        L_0x0022:
            r0 = r4 & r2
            if (r0 <= r3) goto L_0x0028
        L_0x0026:
            r4 = r1
            goto L_0x002c
        L_0x0028:
            r3 = -458760(0xfffffffffff8fff8, float:NaN)
        L_0x002b:
            r4 = r4 & r3
        L_0x002c:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: android.icu.impl.coll.CollationFastLatin.getQuaternaries(int, int):int");
    }

    private static int getSecondariesFromOneShortCE(int i) {
        int i2 = i & 992;
        return i2 < 384 ? i2 + 32 : ((i2 + 32) << 16) | 192;
    }

    private static int getTertiaries(int i, boolean z, int i2) {
        int i3;
        int i4;
        if (i2 > 65535) {
            int i5 = 65535 & i2;
            if (i5 >= 4096) {
                return (z ? 2031647 & i2 : i2 & 458759) + 2097184;
            } else if (i5 > i) {
                int i6 = (i2 & 458759) + 2097184;
                return z ? i6 | 524296 : i6;
            }
        } else if (i2 >= 4096) {
            if (z) {
                i3 = (i2 & 31) + 32;
                if ((i2 & 992) >= 384) {
                    i4 = 2621440;
                }
                return i3;
            }
            i3 = (i2 & 7) + 32;
            if ((i2 & 992) >= 384) {
                i4 = 2097152;
            }
            return i3;
            return i4 | i3;
        } else if (i2 > i) {
            int i7 = (i2 & 7) + 32;
            return z ? i7 | 8 : i7;
        } else if (i2 < 3072) {
            return i2;
        }
        return 0;
    }

    /* JADX WARNING: Removed duplicated region for block: B:49:0x0091  */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x00b7 A[LOOP:2: B:61:0x00b3->B:63:0x00b7, LOOP_END] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int getOptions(android.icu.impl.coll.CollationData r21, android.icu.impl.coll.CollationSettings r22, char[] r23) {
        /*
        // Method dump skipped, instructions count: 195
        */
        throw new UnsupportedOperationException("Method not decompiled: android.icu.impl.coll.CollationFastLatin.getOptions(android.icu.impl.coll.CollationData, android.icu.impl.coll.CollationSettings, char[]):int");
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:156:0x020b */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:326:0x0246 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:204:0x029f */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:338:0x02da */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:254:0x032c */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:350:0x0367 */
    /* JADX WARNING: Code restructure failed: missing block: B:128:0x01af, code lost:
        if (r8 != 2) goto L_0x01b2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:225:0x02e2, code lost:
        r13 = 2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x00e7, code lost:
        if (r7 != 2) goto L_0x00ea;
     */
    /* JADX WARNING: Removed duplicated region for block: B:105:0x015a  */
    /* JADX WARNING: Removed duplicated region for block: B:130:0x01b6  */
    /* JADX WARNING: Removed duplicated region for block: B:306:0x01ae A[EDGE_INSN: B:306:0x01ae->B:127:0x01ae ?: BREAK  , SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:311:0x01a5 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int compareUTF16(char[] r21, char[] r22, int r23, java.lang.CharSequence r24, java.lang.CharSequence r25, int r26) {
        /*
        // Method dump skipped, instructions count: 931
        */
        throw new UnsupportedOperationException("Method not decompiled: android.icu.impl.coll.CollationFastLatin.compareUTF16(char[], char[], int, java.lang.CharSequence, java.lang.CharSequence, int):int");
    }

    private static int lookup(char[] cArr, int i) {
        if (8192 <= i && i < 8256) {
            return cArr[(i - 8192) + 384];
        }
        if (i == 65534) {
            return 3;
        }
        return i == 65535 ? 64680 : 1;
    }

    private static long nextPair(char[] cArr, int i, int i2, CharSequence charSequence, int i3) {
        long j;
        int i4;
        if (i2 >= 3072 || i2 < 1024) {
            return (long) i2;
        }
        if (i2 >= 2048) {
            int i5 = (i2 & 1023) + 448;
            return ((long) cArr[i5]) | (((long) cArr[i5 + 1]) << 16);
        }
        int i6 = (i2 & 1023) + 448;
        boolean z = false;
        if (i3 != charSequence.length()) {
            int charAt = charSequence.charAt(i3);
            if (charAt > 383) {
                if (8192 <= charAt && charAt < 8256) {
                    charAt = (charAt - 8192) + 384;
                } else if (charAt != 65534 && charAt != 65535) {
                    return 1;
                } else {
                    charAt = -1;
                }
            }
            char c = cArr[i6];
            int i7 = i6;
            do {
                i7 += c >> '\t';
                c = cArr[i7];
                i4 = c & 511;
            } while (i4 < charAt);
            if (i4 == charAt) {
                i6 = i7;
                z = true;
            }
        }
        int i8 = cArr[i6] >> '\t';
        if (i8 == 1) {
            return 1;
        }
        char c2 = cArr[i6 + 1];
        if (i8 == 2) {
            j = (long) c2;
        } else {
            j = (((long) cArr[i6 + 2]) << 16) | ((long) c2);
        }
        return z ? ~j : j;
    }

    private static int getSecondaries(int i, int i2) {
        if (i2 > 65535) {
            int i3 = 65535 & i2;
            if (i3 >= 4096) {
                return 2097184 + (65012704 & i2);
            }
            if (i3 > i) {
                return 12583104;
            }
        } else if (i2 >= 4096) {
            return getSecondariesFromOneShortCE(i2);
        } else {
            if (i2 > i) {
                return 192;
            }
            if (i2 < 3072) {
                return i2;
            }
        }
        return 0;
    }
}
