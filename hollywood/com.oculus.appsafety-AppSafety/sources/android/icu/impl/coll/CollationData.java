package android.icu.impl.coll;

import android.icu.impl.Normalizer2Impl;
import android.icu.impl.Trie2_32;
import android.icu.text.UnicodeSet;
import android.icu.util.ICUException;

public final class CollationData {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final int[] EMPTY_INT_ARRAY = new int[0];
    static final int JAMO_CE32S_LENGTH = 67;
    static final int MAX_NUM_SPECIAL_REORDER_CODES = 8;
    static final int REORDER_RESERVED_AFTER_LATIN = 4111;
    static final int REORDER_RESERVED_BEFORE_LATIN = 4110;
    public CollationData base;
    int[] ce32s;
    long[] ces;
    public boolean[] compressibleBytes;
    String contexts;
    public char[] fastLatinTable;
    char[] fastLatinTableHeader;
    int[] jamoCE32s = new int[67];
    public Normalizer2Impl nfcImpl;
    int numScripts;
    long numericPrimary = 301989888;
    public long[] rootElements;
    char[] scriptStarts;
    char[] scriptsIndex;
    Trie2_32 trie;
    UnicodeSet unsafeBackwardSet;

    CollationData(Normalizer2Impl nfc) {
        this.nfcImpl = nfc;
    }

    public int getCE32(int c) {
        return this.trie.get(c);
    }

    /* access modifiers changed from: package-private */
    public int getCE32FromSupplementary(int c) {
        return this.trie.get(c);
    }

    /* access modifiers changed from: package-private */
    public boolean isDigit(int c) {
        if (c < 1632) {
            return c <= 57 && 48 <= c;
        }
        return Collation.hasCE32Tag(getCE32(c), 10);
    }

    public boolean isUnsafeBackward(int c, boolean numeric) {
        return this.unsafeBackwardSet.contains(c) || (numeric && isDigit(c));
    }

    public boolean isCompressibleLeadByte(int b) {
        return this.compressibleBytes[b];
    }

    public boolean isCompressiblePrimary(long p) {
        return isCompressibleLeadByte(((int) p) >>> 24);
    }

    /* access modifiers changed from: package-private */
    public int getCE32FromContexts(int index) {
        return (this.contexts.charAt(index) << 16) | this.contexts.charAt(index + 1);
    }

    /* access modifiers changed from: package-private */
    public int getIndirectCE32(int ce32) {
        int tag = Collation.tagFromCE32(ce32);
        if (tag == 10) {
            return this.ce32s[Collation.indexFromCE32(ce32)];
        }
        if (tag == 13) {
            return -1;
        }
        if (tag == 11) {
            return this.ce32s[0];
        }
        return ce32;
    }

    /* access modifiers changed from: package-private */
    public int getFinalCE32(int ce32) {
        if (Collation.isSpecialCE32(ce32)) {
            return getIndirectCE32(ce32);
        }
        return ce32;
    }

    /* access modifiers changed from: package-private */
    public long getCEFromOffsetCE32(int c, int ce32) {
        return Collation.makeCE(Collation.getThreeBytePrimaryForOffsetData(c, this.ces[Collation.indexFromCE32(ce32)]));
    }

    /* access modifiers changed from: package-private */
    public long getSingleCE(int c) {
        CollationData d;
        int ce32 = getCE32(c);
        if (ce32 == 192) {
            d = this.base;
            ce32 = this.base.getCE32(c);
        } else {
            d = this;
        }
        while (Collation.isSpecialCE32(ce32)) {
            switch (Collation.tagFromCE32(ce32)) {
                case 0:
                case 3:
                    throw new AssertionError((Object) String.format("unexpected CE32 tag for U+%04X (CE32 0x%08x)", Integer.valueOf(c), Integer.valueOf(ce32)));
                case 1:
                    return Collation.ceFromLongPrimaryCE32(ce32);
                case 2:
                    return Collation.ceFromLongSecondaryCE32(ce32);
                case 4:
                case 7:
                case 8:
                case 9:
                case 12:
                case 13:
                    throw new UnsupportedOperationException(String.format("there is not exactly one collation element for U+%04X (CE32 0x%08x)", Integer.valueOf(c), Integer.valueOf(ce32)));
                case 5:
                    if (Collation.lengthFromCE32(ce32) == 1) {
                        ce32 = d.ce32s[Collation.indexFromCE32(ce32)];
                        break;
                    } else {
                        throw new UnsupportedOperationException(String.format("there is not exactly one collation element for U+%04X (CE32 0x%08x)", Integer.valueOf(c), Integer.valueOf(ce32)));
                    }
                case 6:
                    if (Collation.lengthFromCE32(ce32) == 1) {
                        return d.ces[Collation.indexFromCE32(ce32)];
                    }
                    throw new UnsupportedOperationException(String.format("there is not exactly one collation element for U+%04X (CE32 0x%08x)", Integer.valueOf(c), Integer.valueOf(ce32)));
                case 10:
                    ce32 = d.ce32s[Collation.indexFromCE32(ce32)];
                    break;
                case 11:
                    ce32 = d.ce32s[0];
                    break;
                case 14:
                    return d.getCEFromOffsetCE32(c, ce32);
                case 15:
                    return Collation.unassignedCEFromCodePoint(c);
            }
        }
        return Collation.ceFromSimpleCE32(ce32);
    }

    /* access modifiers changed from: package-private */
    public int getFCD16(int c) {
        return this.nfcImpl.getFCD16(c);
    }

    /* access modifiers changed from: package-private */
    public long getFirstPrimaryForGroup(int script) {
        int index = getScriptIndex(script);
        if (index == 0) {
            return 0;
        }
        return ((long) this.scriptStarts[index]) << 16;
    }

    public long getLastPrimaryForGroup(int script) {
        int index = getScriptIndex(script);
        if (index == 0) {
            return 0;
        }
        return (((long) this.scriptStarts[index + 1]) << 16) - 1;
    }

    public int getGroupForPrimary(long p) {
        long p2 = p >> 16;
        char[] cArr = this.scriptStarts;
        if (p2 < ((long) cArr[1]) || ((long) cArr[cArr.length - 1]) <= p2) {
            return -1;
        }
        int index = 1;
        while (p2 >= ((long) this.scriptStarts[index + 1])) {
            index++;
        }
        for (int i = 0; i < this.numScripts; i++) {
            if (this.scriptsIndex[i] == index) {
                return i;
            }
        }
        for (int i2 = 0; i2 < 8; i2++) {
            if (this.scriptsIndex[this.numScripts + i2] == index) {
                return i2 + 4096;
            }
        }
        return -1;
    }

    private int getScriptIndex(int script) {
        int script2;
        if (script < 0) {
            return 0;
        }
        int i = this.numScripts;
        if (script < i) {
            return this.scriptsIndex[script];
        }
        if (script >= 4096 && script - 4096 < 8) {
            return this.scriptsIndex[i + script2];
        }
        return 0;
    }

    /* JADX INFO: Multiple debug info for r4v2 int[]: [D('dest' int[]), D('i' int)] */
    public int[] getEquivalentScripts(int script) {
        int index = getScriptIndex(script);
        if (index == 0) {
            return EMPTY_INT_ARRAY;
        }
        if (script >= 4096) {
            return new int[]{script};
        }
        int length = 0;
        for (int i = 0; i < this.numScripts; i++) {
            if (this.scriptsIndex[i] == index) {
                length++;
            }
        }
        int[] dest = new int[length];
        if (length == 1) {
            dest[0] = script;
            return dest;
        }
        int length2 = 0;
        for (int i2 = 0; i2 < this.numScripts; i2++) {
            if (this.scriptsIndex[i2] == index) {
                dest[length2] = i2;
                length2++;
            }
        }
        return dest;
    }

    /* access modifiers changed from: package-private */
    public void makeReorderRanges(int[] reorder, UVector32 ranges) {
        makeReorderRanges(reorder, false, ranges);
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:111:0x0071 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:113:0x008e */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:120:0x009c */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:129:0x013f */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:119:0x008e */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:126:0x009c */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX INFO: Multiple debug info for r14v9 int: [D('script' int), D('i' int)] */
    /* JADX WARN: Type inference failed for: r18v0, types: [android.icu.impl.coll.CollationData] */
    /* JADX WARN: Type inference failed for: r8v3, types: [char[]] */
    /* JADX WARN: Type inference failed for: r10v1, types: [char] */
    /* JADX WARN: Type inference failed for: r8v4, types: [char] */
    /* JADX WARN: Type inference failed for: r8v6, types: [int] */
    /* JADX WARN: Type inference failed for: r8v7 */
    /* JADX WARN: Type inference failed for: r8v8, types: [int] */
    private void makeReorderRanges(int[] reorder, boolean latinMustMove, UVector32 ranges) {
        boolean hasReorderToEnd;
        int highLimit;
        ranges.removeAllElements();
        int length = reorder.length;
        if (length != 0) {
            int index = 103;
            if (length != 1 || reorder[0] != 103) {
                short[] table = new short[(this.scriptStarts.length - 1)];
                char c = this.scriptsIndex[(this.numScripts + 4110) - 4096];
                if (c != 0) {
                    table[c] = 255;
                }
                char c2 = this.scriptsIndex[(this.numScripts + 4111) - 4096];
                if (c2 != 0) {
                    table[c2] = 255;
                }
                char[] cArr = this.scriptStarts;
                char c3 = cArr[1];
                char c4 = cArr[cArr.length - 1];
                int specials = 0;
                for (int i = 0; i < length; i++) {
                    int reorderCode = reorder[i] - 4096;
                    if (reorderCode >= 0 && reorderCode < 8) {
                        specials |= 1 << reorderCode;
                    }
                }
                int i2 = 0;
                int lowStart = c3;
                while (i2 < 8) {
                    char c5 = this.scriptsIndex[this.numScripts + i2];
                    if (c5 != 0 && ((1 << i2) & specials) == 0) {
                        lowStart = addLowScriptRange(table, c5, lowStart);
                    }
                    i2++;
                    lowStart = lowStart;
                }
                int skippedReserved = 0;
                int lowStart2 = lowStart;
                if (specials == 0) {
                    lowStart2 = lowStart;
                    lowStart2 = lowStart;
                    if (reorder[0] == 25 && !latinMustMove) {
                        char c6 = this.scriptStarts[this.scriptsIndex[25]];
                        skippedReserved = c6 - lowStart;
                        lowStart2 = c6;
                    }
                }
                int script = 0;
                while (true) {
                    if (script >= length) {
                        hasReorderToEnd = false;
                        highLimit = c4;
                        break;
                    }
                    int i3 = script + 1;
                    int i4 = reorder[script];
                    if (i4 == index) {
                        while (i3 < length) {
                            length--;
                            int script2 = reorder[length];
                            if (script2 == index) {
                                throw new IllegalArgumentException("setReorderCodes(): duplicate UScript.UNKNOWN");
                            } else if (script2 != -1) {
                                int index2 = getScriptIndex(script2);
                                if (index2 == 0) {
                                    index = 103;
                                } else if (table[index2] == 0) {
                                    c4 = addHighScriptRange(table, index2, c4);
                                    index = 103;
                                } else {
                                    throw new IllegalArgumentException("setReorderCodes(): duplicate or equivalent script " + scriptCodeString(script2));
                                }
                            } else {
                                throw new IllegalArgumentException("setReorderCodes(): UScript.DEFAULT together with other scripts");
                            }
                        }
                        hasReorderToEnd = true;
                        highLimit = c4;
                    } else if (i4 != -1) {
                        int index3 = getScriptIndex(i4);
                        if (index3 == 0) {
                            script = i3;
                            index = 103;
                        } else if (table[index3] == 0) {
                            lowStart2 = addLowScriptRange(table, index3, lowStart2 == 1 ? 1 : 0);
                            script = i3;
                            index = 103;
                        } else {
                            throw new IllegalArgumentException("setReorderCodes(): duplicate or equivalent script " + scriptCodeString(i4));
                        }
                    } else {
                        throw new IllegalArgumentException("setReorderCodes(): UScript.DEFAULT together with other scripts");
                    }
                }
                int i5 = 1;
                int lowStart3 = lowStart2;
                while (true) {
                    char[] cArr2 = this.scriptStarts;
                    if (i5 >= cArr2.length - 1) {
                        break;
                    }
                    if (table[i5] == 0) {
                        char c7 = cArr2[i5];
                        int lowStart4 = lowStart3;
                        lowStart4 = lowStart3;
                        if (!hasReorderToEnd && c7 > lowStart3) {
                            lowStart4 = c7;
                        }
                        lowStart3 = addLowScriptRange(table, i5, lowStart4 == 1 ? 1 : 0);
                    }
                    i5++;
                    lowStart3 = lowStart3;
                }
                if (lowStart3 <= highLimit) {
                    int offset = 0;
                    int i6 = 1;
                    while (true) {
                        int nextOffset = offset;
                        while (true) {
                            char[] cArr3 = this.scriptStarts;
                            if (i6 >= cArr3.length - 1) {
                                break;
                            }
                            short s = table[i6];
                            if (s != 255) {
                                nextOffset = s - (cArr3[i6] >> '\b');
                                if (nextOffset != offset) {
                                    break;
                                }
                            }
                            i6++;
                        }
                        if (offset != 0 || i6 < this.scriptStarts.length - 1) {
                            ranges.addElement((this.scriptStarts[i6] << 16) | (65535 & offset));
                        }
                        if (i6 != this.scriptStarts.length - 1) {
                            offset = nextOffset;
                            i6++;
                        } else {
                            return;
                        }
                    }
                } else if (lowStart3 - (65280 & skippedReserved) <= highLimit) {
                    makeReorderRanges(reorder, true, ranges);
                } else {
                    throw new ICUException("setReorderCodes(): reordering too many partial-primary-lead-byte scripts");
                }
            }
        }
    }

    private int addLowScriptRange(short[] table, int index, int lowStart) {
        char c = this.scriptStarts[index];
        if ((c & 255) < (lowStart & 255)) {
            lowStart += 256;
        }
        table[index] = (short) (lowStart >> 8);
        char c2 = this.scriptStarts[index + 1];
        return ((lowStart & 65280) + ((c2 & 65280) - (65280 & c))) | (c2 & 255);
    }

    private int addHighScriptRange(short[] table, int index, int highLimit) {
        char c = this.scriptStarts[index + 1];
        if ((c & 255) > (highLimit & 255)) {
            highLimit -= 256;
        }
        char c2 = this.scriptStarts[index];
        int highLimit2 = ((highLimit & 65280) - ((c & 65280) - (65280 & c2))) | (c2 & 255);
        table[index] = (short) (highLimit2 >> 8);
        return highLimit2;
    }

    private static String scriptCodeString(int script) {
        if (script < 4096) {
            return Integer.toString(script);
        }
        return "0x" + Integer.toHexString(script);
    }
}
