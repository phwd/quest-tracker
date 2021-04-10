package android.icu.impl.coll;

import android.icu.impl.Normalizer2Impl;
import android.icu.impl.Trie2;
import android.icu.text.UnicodeSet;
import java.util.Iterator;

public final class TailoredSet {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private CollationData baseData;
    private CollationData data;
    private String suffix;
    private UnicodeSet tailored;
    private StringBuilder unreversedPrefix = new StringBuilder();

    public TailoredSet(UnicodeSet t) {
        this.tailored = t;
    }

    public void forData(CollationData d) {
        this.data = d;
        this.baseData = d.base;
        Iterator<Trie2.Range> trieIterator = this.data.trie.iterator();
        while (trieIterator.hasNext()) {
            Trie2.Range range = trieIterator.next();
            if (!range.leadSurrogate) {
                enumTailoredRange(range.startCodePoint, range.endCodePoint, range.value, this);
            } else {
                return;
            }
        }
    }

    private void enumTailoredRange(int start, int end, int ce32, TailoredSet ts) {
        if (ce32 != 192) {
            ts.handleCE32(start, end, ce32);
        }
    }

    private void handleCE32(int start, int end, int ce32) {
        if (!Collation.isSpecialCE32(ce32) || (ce32 = this.data.getIndirectCE32(ce32)) != 192) {
            do {
                CollationData collationData = this.baseData;
                int baseCE32 = collationData.getFinalCE32(collationData.getCE32(start));
                if (!Collation.isSelfContainedCE32(ce32) || !Collation.isSelfContainedCE32(baseCE32)) {
                    compare(start, ce32, baseCE32);
                } else if (ce32 != baseCE32) {
                    this.tailored.add(start);
                }
                start++;
            } while (start <= end);
        }
    }

    private void compare(int c, int ce32, int baseCE32) {
        int tag;
        int baseTag;
        if (Collation.isPrefixCE32(ce32)) {
            int dataIndex = Collation.indexFromCE32(ce32);
            CollationData collationData = this.data;
            ce32 = collationData.getFinalCE32(collationData.getCE32FromContexts(dataIndex));
            if (Collation.isPrefixCE32(baseCE32)) {
                int baseIndex = Collation.indexFromCE32(baseCE32);
                CollationData collationData2 = this.baseData;
                baseCE32 = collationData2.getFinalCE32(collationData2.getCE32FromContexts(baseIndex));
                comparePrefixes(c, this.data.contexts, dataIndex + 2, this.baseData.contexts, baseIndex + 2);
            } else {
                CollationData collationData3 = this.data;
                addPrefixes(collationData3, c, collationData3.contexts, dataIndex + 2);
            }
        } else if (Collation.isPrefixCE32(baseCE32)) {
            int baseIndex2 = Collation.indexFromCE32(baseCE32);
            CollationData collationData4 = this.baseData;
            baseCE32 = collationData4.getFinalCE32(collationData4.getCE32FromContexts(baseIndex2));
            CollationData collationData5 = this.baseData;
            addPrefixes(collationData5, c, collationData5.contexts, baseIndex2 + 2);
        }
        if (Collation.isContractionCE32(ce32)) {
            int dataIndex2 = Collation.indexFromCE32(ce32);
            if ((ce32 & 256) != 0) {
                ce32 = 1;
            } else {
                CollationData collationData6 = this.data;
                ce32 = collationData6.getFinalCE32(collationData6.getCE32FromContexts(dataIndex2));
            }
            if (Collation.isContractionCE32(baseCE32)) {
                int baseIndex3 = Collation.indexFromCE32(baseCE32);
                if ((baseCE32 & 256) != 0) {
                    baseCE32 = 1;
                } else {
                    CollationData collationData7 = this.baseData;
                    baseCE32 = collationData7.getFinalCE32(collationData7.getCE32FromContexts(baseIndex3));
                }
                compareContractions(c, this.data.contexts, dataIndex2 + 2, this.baseData.contexts, baseIndex3 + 2);
            } else {
                addContractions(c, this.data.contexts, dataIndex2 + 2);
            }
        } else if (Collation.isContractionCE32(baseCE32)) {
            int baseIndex4 = Collation.indexFromCE32(baseCE32);
            CollationData collationData8 = this.baseData;
            baseCE32 = collationData8.getFinalCE32(collationData8.getCE32FromContexts(baseIndex4));
            addContractions(c, this.baseData.contexts, baseIndex4 + 2);
        }
        if (Collation.isSpecialCE32(ce32)) {
            tag = Collation.tagFromCE32(ce32);
        } else {
            tag = -1;
        }
        if (Collation.isSpecialCE32(baseCE32)) {
            baseTag = Collation.tagFromCE32(baseCE32);
        } else {
            baseTag = -1;
        }
        if (baseTag == 14) {
            if (!Collation.isLongPrimaryCE32(ce32)) {
                add(c);
                return;
            }
            if (Collation.primaryFromLongPrimaryCE32(ce32) != Collation.getThreeBytePrimaryForOffsetData(c, this.baseData.ces[Collation.indexFromCE32(baseCE32)])) {
                add(c);
                return;
            }
        }
        if (tag != baseTag) {
            add(c);
        } else if (tag == 5) {
            int length = Collation.lengthFromCE32(ce32);
            if (length != Collation.lengthFromCE32(baseCE32)) {
                add(c);
                return;
            }
            int idx0 = Collation.indexFromCE32(ce32);
            int idx1 = Collation.indexFromCE32(baseCE32);
            for (int i = 0; i < length; i++) {
                if (this.data.ce32s[idx0 + i] != this.baseData.ce32s[idx1 + i]) {
                    add(c);
                    return;
                }
            }
        } else if (tag == 6) {
            int length2 = Collation.lengthFromCE32(ce32);
            if (length2 != Collation.lengthFromCE32(baseCE32)) {
                add(c);
                return;
            }
            int idx02 = Collation.indexFromCE32(ce32);
            int idx12 = Collation.indexFromCE32(baseCE32);
            for (int i2 = 0; i2 < length2; i2++) {
                if (this.data.ces[idx02 + i2] != this.baseData.ces[idx12 + i2]) {
                    add(c);
                    return;
                }
            }
        } else if (tag == 12) {
            StringBuilder jamos = new StringBuilder();
            int length3 = Normalizer2Impl.Hangul.decompose(c, jamos);
            if (this.tailored.contains(jamos.charAt(0)) || this.tailored.contains(jamos.charAt(1)) || (length3 == 3 && this.tailored.contains(jamos.charAt(2)))) {
                add(c);
            }
        } else if (ce32 != baseCE32) {
            add(c);
        }
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [android.icu.util.CharsTrie$Iterator] */
    /* JADX WARN: Type inference failed for: r1v1, types: [android.icu.util.CharsTrie$Iterator] */
    /* JADX WARNING: Unknown variable types count: 2 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void comparePrefixes(int r11, java.lang.CharSequence r12, int r13, java.lang.CharSequence r14, int r15) {
        /*
        // Method dump skipped, instructions count: 130
        */
        throw new UnsupportedOperationException("Method not decompiled: android.icu.impl.coll.TailoredSet.comparePrefixes(int, java.lang.CharSequence, int, java.lang.CharSequence, int):void");
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [android.icu.util.CharsTrie$Iterator] */
    /* JADX WARN: Type inference failed for: r1v1, types: [android.icu.util.CharsTrie$Iterator] */
    /* JADX WARNING: Unknown variable types count: 2 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void compareContractions(int r11, java.lang.CharSequence r12, int r13, java.lang.CharSequence r14, int r15) {
        /*
        // Method dump skipped, instructions count: 117
        */
        throw new UnsupportedOperationException("Method not decompiled: android.icu.impl.coll.TailoredSet.compareContractions(int, java.lang.CharSequence, int, java.lang.CharSequence, int):void");
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [android.icu.util.CharsTrie$Iterator] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void addPrefixes(android.icu.impl.coll.CollationData r5, int r6, java.lang.CharSequence r7, int r8) {
        /*
            r4 = this;
            android.icu.util.CharsTrie r0 = new android.icu.util.CharsTrie
            r0.<init>(r7, r8)
            android.icu.util.CharsTrie$Iterator r0 = r0.iterator()
        L_0x0009:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x001b
            android.icu.util.CharsTrie$Entry r1 = r0.next()
            java.lang.CharSequence r2 = r1.chars
            int r3 = r1.value
            r4.addPrefix(r5, r2, r6, r3)
            goto L_0x0009
        L_0x001b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.icu.impl.coll.TailoredSet.addPrefixes(android.icu.impl.coll.CollationData, int, java.lang.CharSequence, int):void");
    }

    private void addPrefix(CollationData d, CharSequence pfx, int c, int ce32) {
        setPrefix(pfx);
        int ce322 = d.getFinalCE32(ce32);
        if (Collation.isContractionCE32(ce322)) {
            addContractions(c, d.contexts, Collation.indexFromCE32(ce322) + 2);
        }
        this.tailored.add(new StringBuilder(this.unreversedPrefix.appendCodePoint(c)));
        resetPrefix();
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [android.icu.util.CharsTrie$Iterator] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void addContractions(int r4, java.lang.CharSequence r5, int r6) {
        /*
            r3 = this;
            android.icu.util.CharsTrie r0 = new android.icu.util.CharsTrie
            r0.<init>(r5, r6)
            android.icu.util.CharsTrie$Iterator r0 = r0.iterator()
        L_0x0009:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x0019
            android.icu.util.CharsTrie$Entry r1 = r0.next()
            java.lang.CharSequence r2 = r1.chars
            r3.addSuffix(r4, r2)
            goto L_0x0009
        L_0x0019:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.icu.impl.coll.TailoredSet.addContractions(int, java.lang.CharSequence, int):void");
    }

    private void addSuffix(int c, CharSequence sfx) {
        UnicodeSet unicodeSet = this.tailored;
        StringBuilder appendCodePoint = new StringBuilder(this.unreversedPrefix).appendCodePoint(c);
        appendCodePoint.append(sfx);
        unicodeSet.add(appendCodePoint);
    }

    private void add(int c) {
        if (this.unreversedPrefix.length() == 0 && this.suffix == null) {
            this.tailored.add(c);
            return;
        }
        StringBuilder s = new StringBuilder(this.unreversedPrefix);
        s.appendCodePoint(c);
        String str = this.suffix;
        if (str != null) {
            s.append(str);
        }
        this.tailored.add(s);
    }

    private void setPrefix(CharSequence pfx) {
        this.unreversedPrefix.setLength(0);
        StringBuilder sb = this.unreversedPrefix;
        sb.append(pfx);
        sb.reverse();
    }

    private void resetPrefix() {
        this.unreversedPrefix.setLength(0);
    }
}
