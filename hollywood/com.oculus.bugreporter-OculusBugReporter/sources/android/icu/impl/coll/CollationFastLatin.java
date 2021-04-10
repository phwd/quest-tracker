package android.icu.impl.coll;

import android.icu.impl.Normalizer2Impl;

public final class CollationFastLatin {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static final int BAIL_OUT = 1;
    public static final int BAIL_OUT_RESULT = -2;
    static final int CASE_AND_TERTIARY_MASK = 31;
    static final int CASE_MASK = 24;
    static final int COMMON_SEC = 160;
    static final int COMMON_SEC_PLUS_OFFSET = 192;
    static final int COMMON_TER = 0;
    static final int COMMON_TER_PLUS_OFFSET = 32;
    static final int CONTRACTION = 1024;
    static final int CONTR_CHAR_MASK = 511;
    static final int CONTR_LENGTH_SHIFT = 9;
    static final int EOS = 2;
    static final int EXPANSION = 2048;
    static final int INDEX_MASK = 1023;
    public static final int LATIN_LIMIT = 384;
    public static final int LATIN_MAX = 383;
    static final int LATIN_MAX_UTF8_LEAD = 197;
    static final int LONG_INC = 8;
    static final int LONG_PRIMARY_MASK = 65528;
    static final int LOWER_CASE = 8;
    static final int MAX_LONG = 4088;
    static final int MAX_SEC_AFTER = 352;
    static final int MAX_SEC_BEFORE = 128;
    static final int MAX_SEC_HIGH = 992;
    static final int MAX_SHORT = 64512;
    static final int MAX_TER_AFTER = 7;
    static final int MERGE_WEIGHT = 3;
    static final int MIN_LONG = 3072;
    static final int MIN_SEC_AFTER = 192;
    static final int MIN_SEC_BEFORE = 0;
    static final int MIN_SEC_HIGH = 384;
    static final int MIN_SHORT = 4096;
    static final int NUM_FAST_CHARS = 448;
    static final int PUNCT_LIMIT = 8256;
    static final int PUNCT_START = 8192;
    static final int SECONDARY_MASK = 992;
    static final int SEC_INC = 32;
    static final int SEC_OFFSET = 32;
    static final int SHORT_INC = 1024;
    static final int SHORT_PRIMARY_MASK = 64512;
    static final int TERTIARY_MASK = 7;
    static final int TER_OFFSET = 32;
    static final int TWO_CASES_MASK = 1572888;
    static final int TWO_COMMON_SEC_PLUS_OFFSET = 12583104;
    static final int TWO_COMMON_TER_PLUS_OFFSET = 2097184;
    static final int TWO_LONG_PRIMARIES_MASK = -458760;
    static final int TWO_LOWER_CASES = 524296;
    static final int TWO_SECONDARIES_MASK = 65012704;
    static final int TWO_SEC_OFFSETS = 2097184;
    static final int TWO_SHORT_PRIMARIES_MASK = -67044352;
    static final int TWO_TERTIARIES_MASK = 458759;
    static final int TWO_TER_OFFSETS = 2097184;
    public static final int VERSION = 2;

    static int getCharIndex(char c) {
        if (c <= 383) {
            return c;
        }
        if (8192 > c || c >= PUNCT_LIMIT) {
            return -1;
        }
        return c - 7808;
    }

    /* JADX WARNING: Removed duplicated region for block: B:50:0x00a5  */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x00cc A[LOOP:2: B:61:0x00c8->B:63:0x00cc, LOOP_END] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int getOptions(android.icu.impl.coll.CollationData r24, android.icu.impl.coll.CollationSettings r25, char[] r26) {
        /*
        // Method dump skipped, instructions count: 216
        */
        throw new UnsupportedOperationException("Method not decompiled: android.icu.impl.coll.CollationFastLatin.getOptions(android.icu.impl.coll.CollationData, android.icu.impl.coll.CollationSettings, char[]):int");
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:309:0x00e1 */
    /* JADX DEBUG: Multi-variable search result rejected for r23v0, resolved type: char[] */
    /* JADX DEBUG: Multi-variable search result rejected for r9v65, resolved type: char */
    /* JADX DEBUG: Multi-variable search result rejected for r8v60, resolved type: char */
    /* JADX WARN: Multi-variable type inference failed */
    public static int compareUTF16(char[] table, char[] primaries, int options, CharSequence left, CharSequence right, int startIndex) {
        int i;
        int rightPair;
        int leftPair;
        int rightPair2;
        int leftPair2;
        int variableTop = options >> 16;
        int options2 = options & 65535;
        int c = startIndex;
        int rightIndex = startIndex;
        int leftPair3 = 0;
        char rightPair3 = 0;
        while (true) {
            int i2 = 4096;
            int i3 = PUNCT_LIMIT;
            int leftPair4 = leftPair3;
            if (leftPair3 == 0) {
                if (c == left.length()) {
                    leftPair4 = 2;
                } else {
                    int leftIndex = c + 1;
                    int c2 = left.charAt(c);
                    if (c2 <= 383) {
                        char c3 = primaries[c2];
                        if (c3 != 0) {
                            c = leftIndex;
                            leftPair4 = c3;
                        } else if (c2 <= 57 && c2 >= 48 && (options2 & 2) != 0) {
                            return -2;
                        } else {
                            leftPair2 = table[c2];
                        }
                    } else if (8192 > c2 || c2 >= PUNCT_LIMIT) {
                        leftPair2 = lookup(table, c2);
                    } else {
                        leftPair2 = table[(c2 - 8192) + 384];
                    }
                    if (leftPair2 >= 4096) {
                        c = leftIndex;
                        leftPair4 = leftPair2 & Normalizer2Impl.MIN_NORMAL_MAYBE_YES;
                    } else if (leftPair2 > variableTop) {
                        c = leftIndex;
                        leftPair4 = leftPair2 & LONG_PRIMARY_MASK;
                    } else {
                        long pairAndInc = nextPair(table, c2, leftPair2, left, leftIndex);
                        if (pairAndInc < 0) {
                            leftIndex++;
                            pairAndInc = ~pairAndInc;
                        }
                        int leftPair5 = (int) pairAndInc;
                        if (leftPair5 == 1) {
                            return -2;
                        }
                        leftPair3 = getPrimaries(variableTop, leftPair5);
                        c = leftIndex;
                    }
                }
            }
            while (true) {
                if (rightPair3 != 0) {
                    break;
                } else if (rightIndex == right.length()) {
                    rightPair3 = 2;
                    break;
                } else {
                    int rightIndex2 = rightIndex + 1;
                    int c4 = right.charAt(rightIndex);
                    if (c4 <= 383) {
                        rightPair3 = primaries[c4];
                        if (rightPair3 != 0) {
                            rightIndex = rightIndex2;
                            break;
                        } else if (c4 <= 57 && c4 >= 48 && (options2 & 2) != 0) {
                            return -2;
                        } else {
                            rightPair2 = table[c4];
                        }
                    } else if (8192 > c4 || c4 >= i3) {
                        rightPair2 = lookup(table, c4);
                    } else {
                        rightPair2 = table[(c4 - 8192) + 384];
                    }
                    if (rightPair2 >= i2) {
                        rightPair3 = rightPair2 & Normalizer2Impl.MIN_NORMAL_MAYBE_YES;
                        rightIndex = rightIndex2;
                        break;
                    } else if (rightPair2 > variableTop) {
                        rightPair3 = rightPair2 & LONG_PRIMARY_MASK;
                        rightIndex = rightIndex2;
                        break;
                    } else {
                        long pairAndInc2 = nextPair(table, c4, rightPair2, right, rightIndex2);
                        if (pairAndInc2 < 0) {
                            rightIndex2++;
                            pairAndInc2 = ~pairAndInc2;
                        }
                        int rightPair4 = (int) pairAndInc2;
                        if (rightPair4 == 1) {
                            return -2;
                        }
                        rightPair3 = getPrimaries(variableTop, rightPair4);
                        rightIndex = rightIndex2;
                        i2 = 4096;
                        i3 = PUNCT_LIMIT;
                    }
                }
            }
            if (leftPair4 != rightPair3) {
                int leftPrimary = leftPair4 & 65535;
                int rightPrimary = rightPair3 & 65535;
                if (leftPrimary != rightPrimary) {
                    return leftPrimary < rightPrimary ? -1 : 1;
                }
                if (leftPair4 == 2) {
                    break;
                }
                leftPair3 = leftPair4 >>> 16;
                rightPair3 >>>= 16;
            } else if (leftPair4 == 2) {
                break;
            } else {
                rightPair3 = 0;
                leftPair3 = 0;
            }
        }
        if (CollationSettings.getStrength(options2) >= 1) {
            int rightIndex3 = startIndex;
            int c5 = startIndex;
            int rightPair5 = 0;
            int leftPair6 = 0;
            while (true) {
                if (leftPair6 == 0) {
                    if (c5 == left.length()) {
                        leftPair6 = 2;
                    } else {
                        int leftIndex2 = c5 + 1;
                        int c6 = left.charAt(c5);
                        if (c6 <= 383) {
                            leftPair = table[c6];
                        } else if (8192 > c6 || c6 >= PUNCT_LIMIT) {
                            leftPair = lookup(table, c6);
                        } else {
                            leftPair = table[(c6 - 8192) + 384];
                        }
                        if (leftPair >= 4096) {
                            leftPair6 = getSecondariesFromOneShortCE(leftPair);
                            c5 = leftIndex2;
                        } else if (leftPair > variableTop) {
                            leftPair6 = 192;
                            c5 = leftIndex2;
                        } else {
                            long pairAndInc3 = nextPair(table, c6, leftPair, left, leftIndex2);
                            if (pairAndInc3 < 0) {
                                leftIndex2++;
                                pairAndInc3 = ~pairAndInc3;
                            }
                            leftPair6 = getSecondaries(variableTop, (int) pairAndInc3);
                            c5 = leftIndex2;
                        }
                    }
                }
                while (true) {
                    if (rightPair5 != 0) {
                        break;
                    } else if (rightIndex3 == right.length()) {
                        rightPair5 = 2;
                        break;
                    } else {
                        int rightIndex4 = rightIndex3 + 1;
                        int c7 = right.charAt(rightIndex3);
                        if (c7 <= 383) {
                            rightPair = table[c7];
                        } else {
                            if (8192 <= c7) {
                                if (c7 < PUNCT_LIMIT) {
                                    rightPair = table[(c7 - 8192) + 384];
                                }
                            }
                            rightPair = lookup(table, c7);
                        }
                        if (rightPair >= 4096) {
                            rightPair5 = getSecondariesFromOneShortCE(rightPair);
                            rightIndex3 = rightIndex4;
                            break;
                        } else if (rightPair > variableTop) {
                            rightPair5 = 192;
                            rightIndex3 = rightIndex4;
                            break;
                        } else {
                            long pairAndInc4 = nextPair(table, c7, rightPair, right, rightIndex4);
                            if (pairAndInc4 < 0) {
                                rightIndex4++;
                                pairAndInc4 = ~pairAndInc4;
                            }
                            rightPair5 = getSecondaries(variableTop, (int) pairAndInc4);
                            rightIndex3 = rightIndex4;
                        }
                    }
                }
                if (leftPair6 != rightPair5) {
                    int leftSecondary = leftPair6 & 65535;
                    int rightSecondary = rightPair5 & 65535;
                    if (leftSecondary != rightSecondary) {
                        if ((options2 & 2048) != 0) {
                            return -2;
                        }
                        return leftSecondary < rightSecondary ? -1 : 1;
                    } else if (leftPair6 == 2) {
                        break;
                    } else {
                        leftPair6 >>>= 16;
                        rightPair5 >>>= 16;
                    }
                } else if (leftPair6 == 2) {
                    break;
                } else {
                    rightPair5 = 0;
                    leftPair6 = 0;
                }
            }
        }
        if ((options2 & 1024) != 0) {
            boolean strengthIsPrimary = CollationSettings.getStrength(options2) == 0;
            int rightIndex5 = startIndex;
            int c8 = startIndex;
            int rightPair6 = 0;
            int leftPair7 = 0;
            while (true) {
                if (leftPair7 == 0) {
                    if (c8 == left.length()) {
                        leftPair7 = 2;
                    } else {
                        int leftIndex3 = c8 + 1;
                        int c9 = left.charAt(c8);
                        int leftPair8 = c9 <= 383 ? table[c9] : lookup(table, c9);
                        if (leftPair8 < MIN_LONG) {
                            long pairAndInc5 = nextPair(table, c9, leftPair8, left, leftIndex3);
                            if (pairAndInc5 < 0) {
                                leftIndex3++;
                                pairAndInc5 = ~pairAndInc5;
                            }
                            leftPair8 = (int) pairAndInc5;
                        }
                        leftPair7 = getCases(variableTop, strengthIsPrimary, leftPair8);
                        c8 = leftIndex3;
                    }
                }
                while (true) {
                    if (rightPair6 != 0) {
                        break;
                    } else if (rightIndex5 == right.length()) {
                        rightPair6 = 2;
                        break;
                    } else {
                        int rightIndex6 = rightIndex5 + 1;
                        int c10 = right.charAt(rightIndex5);
                        int rightPair7 = c10 <= 383 ? table[c10] : lookup(table, c10);
                        if (rightPair7 < MIN_LONG) {
                            long pairAndInc6 = nextPair(table, c10, rightPair7, right, rightIndex6);
                            if (pairAndInc6 < 0) {
                                rightIndex6++;
                                pairAndInc6 = ~pairAndInc6;
                            }
                            rightPair7 = (int) pairAndInc6;
                        }
                        rightPair6 = getCases(variableTop, strengthIsPrimary, rightPair7);
                        rightIndex5 = rightIndex6;
                    }
                }
                if (leftPair7 != rightPair6) {
                    int leftCase = leftPair7 & 65535;
                    int rightCase = rightPair6 & 65535;
                    if (leftCase != rightCase) {
                        return (options2 & 256) == 0 ? leftCase < rightCase ? -1 : 1 : leftCase < rightCase ? 1 : -1;
                    }
                    if (leftPair7 == 2) {
                        break;
                    }
                    leftPair7 >>>= 16;
                    rightPair6 >>>= 16;
                } else if (leftPair7 == 2) {
                    break;
                } else {
                    rightPair6 = 0;
                    leftPair7 = 0;
                }
            }
        }
        if (CollationSettings.getStrength(options2) <= 1) {
            return 0;
        }
        boolean withCaseBits = CollationSettings.isTertiaryWithCaseBits(options2);
        int rightIndex7 = startIndex;
        int c11 = startIndex;
        int rightPair8 = 0;
        int leftPair9 = 0;
        while (true) {
            if (leftPair9 == 0) {
                if (c11 == left.length()) {
                    leftPair9 = 2;
                } else {
                    int leftIndex4 = c11 + 1;
                    int c12 = left.charAt(c11);
                    int leftPair10 = c12 <= 383 ? table[c12] : lookup(table, c12);
                    if (leftPair10 < MIN_LONG) {
                        long pairAndInc7 = nextPair(table, c12, leftPair10, left, leftIndex4);
                        if (pairAndInc7 < 0) {
                            leftIndex4++;
                            pairAndInc7 = ~pairAndInc7;
                        }
                        leftPair10 = (int) pairAndInc7;
                    }
                    leftPair9 = getTertiaries(variableTop, withCaseBits, leftPair10);
                    c11 = leftIndex4;
                }
            }
            while (true) {
                if (rightPair8 != 0) {
                    break;
                } else if (rightIndex7 == right.length()) {
                    rightPair8 = 2;
                    break;
                } else {
                    int rightIndex8 = rightIndex7 + 1;
                    int c13 = right.charAt(rightIndex7);
                    int rightPair9 = c13 <= 383 ? table[c13] : lookup(table, c13);
                    if (rightPair9 < MIN_LONG) {
                        long pairAndInc8 = nextPair(table, c13, rightPair9, right, rightIndex8);
                        if (pairAndInc8 < 0) {
                            rightIndex8++;
                            pairAndInc8 = ~pairAndInc8;
                        }
                        rightPair9 = (int) pairAndInc8;
                    }
                    rightPair8 = getTertiaries(variableTop, withCaseBits, rightPair9);
                    rightIndex7 = rightIndex8;
                }
            }
            if (leftPair9 != rightPair8) {
                int leftTertiary = leftPair9 & 65535;
                int rightTertiary = rightPair8 & 65535;
                if (leftTertiary != rightTertiary) {
                    if (CollationSettings.sortsTertiaryUpperCaseFirst(options2)) {
                        if (leftTertiary > 3) {
                            leftTertiary ^= 24;
                        }
                        if (rightTertiary > 3) {
                            rightTertiary ^= 24;
                        }
                    }
                    return leftTertiary < rightTertiary ? -1 : 1;
                }
                i = 2;
                if (leftPair9 == 2) {
                    break;
                }
                leftPair9 >>>= 16;
                rightPair8 >>>= 16;
            } else if (leftPair9 == 2) {
                i = 2;
                break;
            } else {
                rightPair8 = 0;
                leftPair9 = 0;
            }
        }
        if (CollationSettings.getStrength(options2) <= i) {
            return 0;
        }
        int rightIndex9 = startIndex;
        int c14 = startIndex;
        int rightPair10 = 0;
        int leftPair11 = 0;
        while (true) {
            if (leftPair11 == 0) {
                if (c14 == left.length()) {
                    leftPair11 = 2;
                } else {
                    int leftIndex5 = c14 + 1;
                    int c15 = left.charAt(c14);
                    int leftPair12 = c15 <= 383 ? table[c15] : lookup(table, c15);
                    if (leftPair12 < MIN_LONG) {
                        long pairAndInc9 = nextPair(table, c15, leftPair12, left, leftIndex5);
                        if (pairAndInc9 < 0) {
                            leftIndex5++;
                            pairAndInc9 = ~pairAndInc9;
                        }
                        leftPair12 = (int) pairAndInc9;
                    }
                    leftPair11 = getQuaternaries(variableTop, leftPair12);
                    c14 = leftIndex5;
                }
            }
            while (true) {
                if (rightPair10 != 0) {
                    break;
                } else if (rightIndex9 == right.length()) {
                    rightPair10 = 2;
                    break;
                } else {
                    int rightIndex10 = rightIndex9 + 1;
                    int c16 = right.charAt(rightIndex9);
                    int rightPair11 = c16 <= 383 ? table[c16] : lookup(table, c16);
                    if (rightPair11 < MIN_LONG) {
                        long pairAndInc10 = nextPair(table, c16, rightPair11, right, rightIndex10);
                        if (pairAndInc10 < 0) {
                            rightIndex10++;
                            pairAndInc10 = ~pairAndInc10;
                        }
                        rightPair11 = (int) pairAndInc10;
                    }
                    rightPair10 = getQuaternaries(variableTop, rightPair11);
                    rightIndex9 = rightIndex10;
                }
            }
            if (leftPair11 != rightPair10) {
                int leftQuaternary = leftPair11 & 65535;
                int rightQuaternary = rightPair10 & 65535;
                if (leftQuaternary != rightQuaternary) {
                    return leftQuaternary < rightQuaternary ? -1 : 1;
                }
                if (leftPair11 == 2) {
                    return 0;
                }
                leftPair11 >>>= 16;
                rightPair10 >>>= 16;
            } else if (leftPair11 == 2) {
                return 0;
            } else {
                rightPair10 = 0;
                leftPair11 = 0;
            }
        }
    }

    private static int lookup(char[] table, int c) {
        if (8192 <= c && c < PUNCT_LIMIT) {
            return table[(c - 8192) + 384];
        }
        if (c == 65534) {
            return 3;
        }
        if (c == 65535) {
            return 64680;
        }
        return 1;
    }

    private static long nextPair(char[] table, int c, int ce, CharSequence s16, int sIndex) {
        long result;
        int x;
        if (ce >= MIN_LONG || ce < 1024) {
            return (long) ce;
        }
        if (ce >= 2048) {
            int index = (ce & 1023) + NUM_FAST_CHARS;
            return (((long) table[index + 1]) << 16) | ((long) table[index]);
        }
        int index2 = (ce & 1023) + NUM_FAST_CHARS;
        boolean inc = false;
        if (sIndex != s16.length()) {
            int i = sIndex + 1;
            int c2 = s16.charAt(sIndex);
            if (c2 > 383) {
                if (8192 <= c2 && c2 < PUNCT_LIMIT) {
                    c2 = (c2 - 8192) + 384;
                } else if (c2 != 65534 && c2 != 65535) {
                    return 1;
                } else {
                    c2 = -1;
                }
            }
            int i2 = index2;
            char c3 = table[i2];
            do {
                i2 += c3 >> '\t';
                c3 = table[i2];
                x = c3 & 511;
            } while (x < c2);
            if (x == c2) {
                index2 = i2;
                inc = true;
            }
        }
        int length = table[index2] >> '\t';
        if (length == 1) {
            return 1;
        }
        char c4 = table[index2 + 1];
        if (length == 2) {
            result = (long) c4;
        } else {
            result = (((long) table[index2 + 2]) << 16) | ((long) c4);
        }
        return inc ? ~result : result;
    }

    private static int getPrimaries(int variableTop, int pair) {
        int ce = 65535 & pair;
        if (ce >= 4096) {
            return TWO_SHORT_PRIMARIES_MASK & pair;
        }
        if (ce > variableTop) {
            return TWO_LONG_PRIMARIES_MASK & pair;
        }
        if (ce >= MIN_LONG) {
            return 0;
        }
        return pair;
    }

    private static int getSecondariesFromOneShortCE(int ce) {
        int ce2 = ce & 992;
        if (ce2 < 384) {
            return ce2 + 32;
        }
        return ((ce2 + 32) << 16) | 192;
    }

    private static int getSecondaries(int variableTop, int pair) {
        if (pair > 65535) {
            int ce = 65535 & pair;
            if (ce >= 4096) {
                return (TWO_SECONDARIES_MASK & pair) + 2097184;
            }
            if (ce > variableTop) {
                return TWO_COMMON_SEC_PLUS_OFFSET;
            }
            return 0;
        } else if (pair >= 4096) {
            return getSecondariesFromOneShortCE(pair);
        } else {
            if (pair > variableTop) {
                return 192;
            }
            if (pair >= MIN_LONG) {
                return 0;
            }
            return pair;
        }
    }

    private static int getCases(int variableTop, boolean strengthIsPrimary, int pair) {
        if (pair > 65535) {
            int ce = 65535 & pair;
            if (ce >= 4096) {
                if (!strengthIsPrimary || (-67108864 & pair) != 0) {
                    return pair & TWO_CASES_MASK;
                }
                return pair & 24;
            } else if (ce > variableTop) {
                return TWO_LOWER_CASES;
            } else {
                return 0;
            }
        } else if (pair >= 4096) {
            int pair2 = pair & 24;
            if (strengthIsPrimary || (pair & 992) < 384) {
                return pair2;
            }
            return pair2 | 524288;
        } else if (pair > variableTop) {
            return 8;
        } else {
            if (pair >= MIN_LONG) {
                return 0;
            }
            return pair;
        }
    }

    private static int getTertiaries(int variableTop, boolean withCaseBits, int pair) {
        int pair2;
        if (pair > 65535) {
            int ce = 65535 & pair;
            if (ce >= 4096) {
                if (withCaseBits) {
                    pair2 = pair & 2031647;
                } else {
                    pair2 = pair & TWO_TERTIARIES_MASK;
                }
                return pair2 + 2097184;
            } else if (ce <= variableTop) {
                return 0;
            } else {
                int pair3 = (pair & TWO_TERTIARIES_MASK) + 2097184;
                if (withCaseBits) {
                    return pair3 | TWO_LOWER_CASES;
                }
                return pair3;
            }
        } else if (pair >= 4096) {
            if (withCaseBits) {
                int pair4 = (pair & 31) + 32;
                if ((pair & 992) >= 384) {
                    return 2621440 | pair4;
                }
                return pair4;
            }
            int pair5 = (pair & 7) + 32;
            if ((pair & 992) >= 384) {
                return 2097152 | pair5;
            }
            return pair5;
        } else if (pair > variableTop) {
            int pair6 = (pair & 7) + 32;
            if (withCaseBits) {
                return pair6 | 8;
            }
            return pair6;
        } else if (pair >= MIN_LONG) {
            return 0;
        } else {
            return pair;
        }
    }

    private static int getQuaternaries(int variableTop, int pair) {
        if (pair <= 65535) {
            if (pair >= 4096) {
                if ((pair & 992) >= 384) {
                    return TWO_SHORT_PRIMARIES_MASK;
                }
                return Normalizer2Impl.MIN_NORMAL_MAYBE_YES;
            } else if (pair > variableTop) {
                return Normalizer2Impl.MIN_NORMAL_MAYBE_YES;
            } else {
                if (pair >= MIN_LONG) {
                    return pair & LONG_PRIMARY_MASK;
                }
                return pair;
            }
        } else if ((65535 & pair) > variableTop) {
            return TWO_SHORT_PRIMARIES_MASK;
        } else {
            return pair & TWO_LONG_PRIMARIES_MASK;
        }
    }

    private CollationFastLatin() {
    }
}
