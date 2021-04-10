package android.icu.impl.coll;

import android.icu.impl.Normalizer2Impl;
import android.icu.impl.Trie2_32;
import android.icu.text.UnicodeSet;

public final class CollationData {
    private static final int[] EMPTY_INT_ARRAY = new int[0];
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

    CollationData(Normalizer2Impl normalizer2Impl) {
        this.nfcImpl = normalizer2Impl;
    }

    public int getCE32(int i) {
        return this.trie.get(i);
    }

    /* access modifiers changed from: package-private */
    public int getCE32FromSupplementary(int i) {
        return this.trie.get(i);
    }

    /* access modifiers changed from: package-private */
    public boolean isDigit(int i) {
        if (i < 1632) {
            return i <= 57 && 48 <= i;
        }
        return Collation.hasCE32Tag(getCE32(i), 10);
    }

    public boolean isUnsafeBackward(int i, boolean z) {
        return this.unsafeBackwardSet.contains(i) || (z && isDigit(i));
    }

    /* access modifiers changed from: package-private */
    public int getCE32FromContexts(int i) {
        return this.contexts.charAt(i + 1) | (this.contexts.charAt(i) << 16);
    }

    /* access modifiers changed from: package-private */
    public int getIndirectCE32(int i) {
        int tagFromCE32 = Collation.tagFromCE32(i);
        if (tagFromCE32 == 10) {
            return this.ce32s[Collation.indexFromCE32(i)];
        }
        if (tagFromCE32 == 13) {
            return -1;
        }
        return tagFromCE32 == 11 ? this.ce32s[0] : i;
    }

    /* access modifiers changed from: package-private */
    public int getFinalCE32(int i) {
        return Collation.isSpecialCE32(i) ? getIndirectCE32(i) : i;
    }

    /* access modifiers changed from: package-private */
    public long getCEFromOffsetCE32(int i, int i2) {
        return Collation.makeCE(Collation.getThreeBytePrimaryForOffsetData(i, this.ces[Collation.indexFromCE32(i2)]));
    }

    /* access modifiers changed from: package-private */
    public int getFCD16(int i) {
        return this.nfcImpl.getFCD16(i);
    }

    /* access modifiers changed from: package-private */
    public long getFirstPrimaryForGroup(int i) {
        int scriptIndex = getScriptIndex(i);
        if (scriptIndex == 0) {
            return 0;
        }
        return ((long) this.scriptStarts[scriptIndex]) << 16;
    }

    public long getLastPrimaryForGroup(int i) {
        int scriptIndex = getScriptIndex(i);
        if (scriptIndex == 0) {
            return 0;
        }
        return (((long) this.scriptStarts[scriptIndex + 1]) << 16) - 1;
    }

    private int getScriptIndex(int i) {
        int i2;
        if (i < 0) {
            return 0;
        }
        int i3 = this.numScripts;
        if (i < i3) {
            return this.scriptsIndex[i];
        }
        if (i >= 4096 && i - 4096 < 8) {
            return this.scriptsIndex[i3 + i2];
        }
        return 0;
    }

    /* access modifiers changed from: package-private */
    public void makeReorderRanges(int[] iArr, UVector32 uVector32) {
        makeReorderRanges(iArr, false, uVector32);
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:110:0x006e */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:112:0x0097 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:122:0x00a7 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:116:0x00e4 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:126:0x012c */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r17v0, types: [android.icu.impl.coll.CollationData] */
    /* JADX WARN: Type inference failed for: r8v5, types: [char] */
    /* JADX WARN: Type inference failed for: r11v5, types: [int] */
    /* JADX WARN: Type inference failed for: r12v3, types: [int] */
    /* JADX WARN: Type inference failed for: r5v7, types: [int] */
    /* JADX WARN: Type inference failed for: r8v17, types: [int] */
    /* JADX WARN: Type inference failed for: r8v18, types: [int] */
    /* JADX WARN: Type inference failed for: r8v19 */
    /* JADX WARN: Type inference failed for: r12v10, types: [int] */
    /* JADX WARN: Type inference failed for: r12v11 */
    /* JADX WARN: Type inference failed for: r11v9 */
    /* JADX WARN: Type inference failed for: r11v10, types: [int] */
    /* JADX WARN: Type inference failed for: r11v12 */
    /* JADX WARN: Type inference failed for: r12v18 */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void makeReorderRanges(int[] r18, boolean r19, android.icu.impl.coll.UVector32 r20) {
        /*
        // Method dump skipped, instructions count: 389
        */
        throw new UnsupportedOperationException("Method not decompiled: android.icu.impl.coll.CollationData.makeReorderRanges(int[], boolean, android.icu.impl.coll.UVector32):void");
    }

    private int addLowScriptRange(short[] sArr, int i, int i2) {
        char c = this.scriptStarts[i];
        if ((c & 255) < (i2 & 255)) {
            i2 += 256;
        }
        sArr[i] = (short) (i2 >> 8);
        char c2 = this.scriptStarts[i + 1];
        return (c2 & 255) | ((i2 & 65280) + ((c2 & 65280) - (65280 & c)));
    }

    private int addHighScriptRange(short[] sArr, int i, int i2) {
        char c = this.scriptStarts[i + 1];
        if ((c & 255) > (i2 & 255)) {
            i2 -= 256;
        }
        char c2 = this.scriptStarts[i];
        int i3 = (c2 & 255) | ((i2 & 65280) - ((c & 65280) - (65280 & c2)));
        sArr[i] = (short) (i3 >> 8);
        return i3;
    }

    private static String scriptCodeString(int i) {
        if (i < 4096) {
            return Integer.toString(i);
        }
        new StringBuilder().append("0x");
        Integer.toHexString(i);
        throw null;
    }
}
