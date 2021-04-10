package android.icu.impl;

import android.icu.text.UnicodeSet;
import android.icu.util.OutputInt;

public final class BMPSet {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static int U16_SURROGATE_OFFSET = 56613888;
    private int[] bmpBlockBits;
    private boolean[] latin1Contains;
    private final int[] list;
    private int[] list4kStarts;
    private final int listLength;
    private int[] table7FF;

    public BMPSet(int[] parentList, int parentListLength) {
        this.list = parentList;
        this.listLength = parentListLength;
        this.latin1Contains = new boolean[256];
        this.table7FF = new int[64];
        this.bmpBlockBits = new int[64];
        this.list4kStarts = new int[18];
        this.list4kStarts[0] = findCodePoint(2048, 0, this.listLength - 1);
        for (int i = 1; i <= 16; i++) {
            int[] iArr = this.list4kStarts;
            iArr[i] = findCodePoint(i << 12, iArr[i - 1], this.listLength - 1);
        }
        this.list4kStarts[17] = this.listLength - 1;
        initBits();
    }

    public BMPSet(BMPSet otherBMPSet, int[] newParentList, int newParentListLength) {
        this.list = newParentList;
        this.listLength = newParentListLength;
        this.latin1Contains = (boolean[]) otherBMPSet.latin1Contains.clone();
        this.table7FF = (int[]) otherBMPSet.table7FF.clone();
        this.bmpBlockBits = (int[]) otherBMPSet.bmpBlockBits.clone();
        this.list4kStarts = (int[]) otherBMPSet.list4kStarts.clone();
    }

    public boolean contains(int c) {
        if (c <= 255) {
            return this.latin1Contains[c];
        }
        if (c <= 2047) {
            return (this.table7FF[c & 63] & (1 << (c >> 6))) != 0;
        }
        if (c < 55296 || (c >= 57344 && c <= 65535)) {
            int lead = c >> 12;
            int twoBits = (this.bmpBlockBits[(c >> 6) & 63] >> lead) & 65537;
            if (twoBits <= 1) {
                return twoBits != 0;
            }
            int[] iArr = this.list4kStarts;
            return containsSlow(c, iArr[lead], iArr[lead + 1]);
        } else if (c > 1114111) {
            return false;
        } else {
            int[] iArr2 = this.list4kStarts;
            return containsSlow(c, iArr2[13], iArr2[17]);
        }
    }

    public final int span(CharSequence s, int start, UnicodeSet.SpanCondition spanCondition, OutputInt outCount) {
        int i = start;
        int limit = s.length();
        int numSupplementary = 0;
        char c = 17;
        char c2 = 57344;
        char c3 = 55296;
        char c4 = 2047;
        char c5 = 255;
        char c6 = 56320;
        if (UnicodeSet.SpanCondition.NOT_CONTAINED != spanCondition) {
            while (i < limit) {
                char c7 = s.charAt(i);
                if (c7 <= c5) {
                    if (!this.latin1Contains[c7]) {
                        break;
                    }
                } else if (c7 > 2047) {
                    if (c7 >= c3 && c7 < c6 && i + 1 != limit) {
                        char c22 = s.charAt(i + 1);
                        if (c22 >= c6) {
                            if (c22 < c2) {
                                int supplementary = Character.toCodePoint(c7, c22);
                                int[] iArr = this.list4kStarts;
                                if (!containsSlow(supplementary, iArr[16], iArr[17])) {
                                    break;
                                }
                                numSupplementary++;
                                i++;
                            }
                        }
                    }
                    int lead = c7 >> '\f';
                    int twoBits = (this.bmpBlockBits[(c7 >> 6) & 63] >> lead) & 65537;
                    if (twoBits > 1) {
                        int[] iArr2 = this.list4kStarts;
                        if (!containsSlow(c7, iArr2[lead], iArr2[lead + 1])) {
                            break;
                        }
                    } else if (twoBits == 0) {
                        break;
                    }
                } else if ((this.table7FF[c7 & '?'] & (1 << (c7 >> 6))) == 0) {
                    break;
                }
                i++;
                c2 = 57344;
                c3 = 55296;
                c5 = 255;
                c6 = 56320;
            }
        } else {
            while (i < limit) {
                char c8 = s.charAt(i);
                if (c8 <= 255) {
                    if (this.latin1Contains[c8]) {
                        break;
                    }
                } else if (c8 > c4) {
                    if (c8 >= 55296) {
                        if (c8 < 56320 && i + 1 != limit) {
                            char c23 = s.charAt(i + 1);
                            if (c23 >= 56320) {
                                if (c23 < 57344) {
                                    int supplementary2 = Character.toCodePoint(c8, c23);
                                    int[] iArr3 = this.list4kStarts;
                                    if (containsSlow(supplementary2, iArr3[16], iArr3[c])) {
                                        break;
                                    }
                                    numSupplementary++;
                                    i++;
                                }
                            }
                        }
                    }
                    int lead2 = c8 >> '\f';
                    int twoBits2 = (this.bmpBlockBits[(c8 >> 6) & 63] >> lead2) & 65537;
                    if (twoBits2 > 1) {
                        int[] iArr4 = this.list4kStarts;
                        if (containsSlow(c8, iArr4[lead2], iArr4[lead2 + 1])) {
                            break;
                        }
                    } else if (twoBits2 != 0) {
                        break;
                    }
                } else if ((this.table7FF[c8 & '?'] & (1 << (c8 >> 6))) != 0) {
                    break;
                }
                i++;
                c = 17;
                c4 = 2047;
            }
        }
        if (outCount != null) {
            outCount.value = (i - start) - numSupplementary;
        }
        return i;
    }

    public final int spanBack(CharSequence s, int limit, UnicodeSet.SpanCondition spanCondition) {
        int limit2;
        char c2;
        char c22;
        char c = 2047;
        char c3 = 255;
        char c4 = 56320;
        char c5 = 55296;
        if (UnicodeSet.SpanCondition.NOT_CONTAINED != spanCondition) {
            limit2 = limit;
            while (true) {
                limit2--;
                char c6 = s.charAt(limit2);
                if (c6 <= 255) {
                    if (!this.latin1Contains[c6]) {
                        break;
                    }
                } else if (c6 <= 2047) {
                    if ((this.table7FF[c6 & '?'] & (1 << (c6 >> 6))) == 0) {
                        break;
                    }
                } else if (c6 < c5 || c6 < c4 || limit2 == 0 || (c22 = s.charAt(limit2 - 1)) < c5 || c22 >= c4) {
                    int lead = c6 >> '\f';
                    int twoBits = (this.bmpBlockBits[(c6 >> 6) & 63] >> lead) & 65537;
                    if (twoBits <= 1) {
                        if (twoBits == 0) {
                            break;
                        }
                    } else {
                        int[] iArr = this.list4kStarts;
                        if (!containsSlow(c6, iArr[lead], iArr[lead + 1])) {
                            break;
                        }
                    }
                } else {
                    int supplementary = Character.toCodePoint(c22, c6);
                    int[] iArr2 = this.list4kStarts;
                    if (!containsSlow(supplementary, iArr2[16], iArr2[17])) {
                        break;
                    }
                    limit2--;
                }
                if (limit2 == 0) {
                    return 0;
                }
                c4 = 56320;
                c5 = 55296;
            }
        } else {
            limit2 = limit;
            while (true) {
                limit2--;
                char c7 = s.charAt(limit2);
                if (c7 > c3) {
                    if (c7 > c) {
                        if (c7 >= 55296) {
                            if (c7 >= 56320 && limit2 != 0 && (c2 = s.charAt(limit2 - 1)) >= 55296 && c2 < 56320) {
                                int supplementary2 = Character.toCodePoint(c2, c7);
                                int[] iArr3 = this.list4kStarts;
                                if (containsSlow(supplementary2, iArr3[16], iArr3[17])) {
                                    break;
                                }
                                limit2--;
                            }
                        }
                        int lead2 = c7 >> '\f';
                        int twoBits2 = (this.bmpBlockBits[(c7 >> 6) & 63] >> lead2) & 65537;
                        if (twoBits2 <= 1) {
                            if (twoBits2 != 0) {
                                break;
                            }
                        } else {
                            int[] iArr4 = this.list4kStarts;
                            if (containsSlow(c7, iArr4[lead2], iArr4[lead2 + 1])) {
                                break;
                            }
                        }
                    } else if ((this.table7FF[c7 & '?'] & (1 << (c7 >> 6))) != 0) {
                        break;
                    }
                } else if (this.latin1Contains[c7]) {
                    break;
                }
                if (limit2 == 0) {
                    return 0;
                }
                c = 2047;
                c3 = 255;
            }
        }
        return limit2 + 1;
    }

    private static void set32x64Bits(int[] table, int start, int limit) {
        int lead = start >> 6;
        int trail = start & 63;
        int bits = 1 << lead;
        if (start + 1 == limit) {
            table[trail] = table[trail] | bits;
            return;
        }
        int limitLead = limit >> 6;
        int limitTrail = limit & 63;
        if (lead == limitLead) {
            while (trail < limitTrail) {
                table[trail] = table[trail] | bits;
                trail++;
            }
            return;
        }
        if (trail > 0) {
            while (true) {
                int trail2 = trail + 1;
                table[trail] = table[trail] | bits;
                if (trail2 >= 64) {
                    break;
                }
                trail = trail2;
            }
            lead++;
        }
        if (lead < limitLead) {
            int bits2 = ~((1 << lead) - 1);
            if (limitLead < 32) {
                bits2 &= (1 << limitLead) - 1;
            }
            for (int trail3 = 0; trail3 < 64; trail3++) {
                table[trail3] = table[trail3] | bits2;
            }
        }
        int bits3 = 1 << limitLead;
        for (int trail4 = 0; trail4 < limitTrail; trail4++) {
            table[trail4] = table[trail4] | bits3;
        }
    }

    /* JADX INFO: Multiple debug info for r0v2 int: [D('listIndex' int), D('start' int)] */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x00b6 A[LOOP:0: B:1:0x0001->B:49:0x00b6, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x0028 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void initBits() {
        /*
        // Method dump skipped, instructions count: 185
        */
        throw new UnsupportedOperationException("Method not decompiled: android.icu.impl.BMPSet.initBits():void");
    }

    private int findCodePoint(int c, int lo, int hi) {
        int[] iArr = this.list;
        if (c < iArr[lo]) {
            return lo;
        }
        if (lo >= hi || c >= iArr[hi - 1]) {
            return hi;
        }
        while (true) {
            int i = (lo + hi) >>> 1;
            if (i == lo) {
                return hi;
            }
            if (c < this.list[i]) {
                hi = i;
            } else {
                lo = i;
            }
        }
    }

    private final boolean containsSlow(int c, int lo, int hi) {
        return (findCodePoint(c, lo, hi) & 1) != 0;
    }
}
