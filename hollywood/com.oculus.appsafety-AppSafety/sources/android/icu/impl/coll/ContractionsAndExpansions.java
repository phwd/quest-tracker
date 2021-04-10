package android.icu.impl.coll;

import android.icu.impl.Trie2;
import android.icu.text.UnicodeSet;
import java.util.Iterator;

public final class ContractionsAndExpansions {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private boolean addPrefixes;
    private long[] ces = new long[31];
    private int checkTailored = 0;
    private UnicodeSet contractions;
    private CollationData data;
    private UnicodeSet expansions;
    private UnicodeSet ranges;
    private CESink sink;
    private String suffix;
    private UnicodeSet tailored = new UnicodeSet();
    private StringBuilder unreversedPrefix = new StringBuilder();

    public interface CESink {
        void handleCE(long j);

        void handleExpansion(long[] jArr, int i, int i2);
    }

    public ContractionsAndExpansions(UnicodeSet con, UnicodeSet exp, CESink s, boolean prefixes) {
        this.contractions = con;
        this.expansions = exp;
        this.sink = s;
        this.addPrefixes = prefixes;
    }

    public void forData(CollationData d) {
        if (d.base != null) {
            this.checkTailored = -1;
        }
        this.data = d;
        Iterator<Trie2.Range> trieIterator = this.data.trie.iterator();
        while (trieIterator.hasNext()) {
            Trie2.Range range = trieIterator.next();
            if (range.leadSurrogate) {
                break;
            }
            enumCnERange(range.startCodePoint, range.endCodePoint, range.value, this);
        }
        if (d.base != null) {
            this.tailored.freeze();
            this.checkTailored = 1;
            this.data = d.base;
            Iterator<Trie2.Range> trieIterator2 = this.data.trie.iterator();
            while (trieIterator2.hasNext()) {
                Trie2.Range range2 = trieIterator2.next();
                if (!range2.leadSurrogate) {
                    enumCnERange(range2.startCodePoint, range2.endCodePoint, range2.value, this);
                } else {
                    return;
                }
            }
        }
    }

    private void enumCnERange(int start, int end, int ce32, ContractionsAndExpansions cne) {
        int i = cne.checkTailored;
        if (i != 0) {
            if (i < 0) {
                if (ce32 != 192) {
                    cne.tailored.add(start, end);
                } else {
                    return;
                }
            } else if (start == end) {
                if (cne.tailored.contains(start)) {
                    return;
                }
            } else if (cne.tailored.containsSome(start, end)) {
                if (cne.ranges == null) {
                    cne.ranges = new UnicodeSet();
                }
                cne.ranges.set(start, end).removeAll(cne.tailored);
                int count = cne.ranges.getRangeCount();
                for (int i2 = 0; i2 < count; i2++) {
                    cne.handleCE32(cne.ranges.getRangeStart(i2), cne.ranges.getRangeEnd(i2), ce32);
                }
            }
        }
        cne.handleCE32(start, end, ce32);
    }

    public void forCodePoint(CollationData d, int c) {
        int ce32 = d.getCE32(c);
        if (ce32 == 192) {
            d = d.base;
            ce32 = d.getCE32(c);
        }
        this.data = d;
        handleCE32(c, c, ce32);
    }

    private void handleCE32(int start, int end, int ce32) {
        while ((ce32 & 255) >= 192) {
            switch (Collation.tagFromCE32(ce32)) {
                case 0:
                    return;
                case 1:
                    CESink cESink = this.sink;
                    if (cESink != null) {
                        cESink.handleCE(Collation.ceFromLongPrimaryCE32(ce32));
                        return;
                    }
                    return;
                case 2:
                    CESink cESink2 = this.sink;
                    if (cESink2 != null) {
                        cESink2.handleCE(Collation.ceFromLongSecondaryCE32(ce32));
                        return;
                    }
                    return;
                case 3:
                case 7:
                case 13:
                    throw new AssertionError((Object) String.format("Unexpected CE32 tag type %d for ce32=0x%08x", Integer.valueOf(Collation.tagFromCE32(ce32)), Integer.valueOf(ce32)));
                case 4:
                    if (this.sink != null) {
                        this.ces[0] = Collation.latinCE0FromCE32(ce32);
                        this.ces[1] = Collation.latinCE1FromCE32(ce32);
                        this.sink.handleExpansion(this.ces, 0, 2);
                    }
                    if (this.unreversedPrefix.length() == 0) {
                        addExpansions(start, end);
                        return;
                    }
                    return;
                case 5:
                    if (this.sink != null) {
                        int idx = Collation.indexFromCE32(ce32);
                        int length = Collation.lengthFromCE32(ce32);
                        for (int i = 0; i < length; i++) {
                            this.ces[i] = Collation.ceFromCE32(this.data.ce32s[idx + i]);
                        }
                        this.sink.handleExpansion(this.ces, 0, length);
                    }
                    if (this.unreversedPrefix.length() == 0) {
                        addExpansions(start, end);
                        return;
                    }
                    return;
                case 6:
                    if (this.sink != null) {
                        this.sink.handleExpansion(this.data.ces, Collation.indexFromCE32(ce32), Collation.lengthFromCE32(ce32));
                    }
                    if (this.unreversedPrefix.length() == 0) {
                        addExpansions(start, end);
                        return;
                    }
                    return;
                case 8:
                    handlePrefixes(start, end, ce32);
                    return;
                case 9:
                    handleContractions(start, end, ce32);
                    return;
                case 10:
                    ce32 = this.data.ce32s[Collation.indexFromCE32(ce32)];
                    break;
                case 11:
                    ce32 = this.data.ce32s[0];
                    break;
                case 12:
                    if (this.sink != null) {
                        UTF16CollationIterator iter = new UTF16CollationIterator(this.data);
                        StringBuilder hangul = new StringBuilder(1);
                        for (int c = start; c <= end; c++) {
                            hangul.setLength(0);
                            hangul.appendCodePoint(c);
                            iter.setText(false, hangul, 0);
                            this.sink.handleExpansion(iter.getCEs(), 0, iter.fetchCEs() - 1);
                        }
                    }
                    if (this.unreversedPrefix.length() == 0) {
                        addExpansions(start, end);
                        return;
                    }
                    return;
                case 14:
                    return;
                case 15:
                    return;
            }
        }
        CESink cESink3 = this.sink;
        if (cESink3 != null) {
            cESink3.handleCE(Collation.ceFromSimpleCE32(ce32));
        }
    }

    /* JADX WARN: Type inference failed for: r1v3, types: [android.icu.util.CharsTrie$Iterator] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void handlePrefixes(int r5, int r6, int r7) {
        /*
            r4 = this;
            int r0 = android.icu.impl.coll.Collation.indexFromCE32(r7)
            android.icu.impl.coll.CollationData r1 = r4.data
            int r7 = r1.getCE32FromContexts(r0)
            r4.handleCE32(r5, r6, r7)
            boolean r1 = r4.addPrefixes
            if (r1 != 0) goto L_0x0012
            return
        L_0x0012:
            android.icu.util.CharsTrie r1 = new android.icu.util.CharsTrie
            android.icu.impl.coll.CollationData r2 = r4.data
            java.lang.String r2 = r2.contexts
            int r3 = r0 + 2
            r1.<init>(r2, r3)
            android.icu.util.CharsTrie$Iterator r1 = r1.iterator()
        L_0x0021:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L_0x0040
            android.icu.util.CharsTrie$Entry r2 = r1.next()
            java.lang.CharSequence r3 = r2.chars
            r4.setPrefix(r3)
            android.icu.text.UnicodeSet r3 = r4.contractions
            r4.addStrings(r5, r6, r3)
            android.icu.text.UnicodeSet r3 = r4.expansions
            r4.addStrings(r5, r6, r3)
            int r3 = r2.value
            r4.handleCE32(r5, r6, r3)
            goto L_0x0021
        L_0x0040:
            r4.resetPrefix()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.icu.impl.coll.ContractionsAndExpansions.handlePrefixes(int, int, int):void");
    }

    /* JADX WARN: Type inference failed for: r1v2, types: [android.icu.util.CharsTrie$Iterator] */
    /* access modifiers changed from: package-private */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void handleContractions(int r5, int r6, int r7) {
        /*
            r4 = this;
            int r0 = android.icu.impl.coll.Collation.indexFromCE32(r7)
            r1 = r7 & 256(0x100, float:3.59E-43)
            if (r1 == 0) goto L_0x0009
            goto L_0x0013
        L_0x0009:
            android.icu.impl.coll.CollationData r1 = r4.data
            int r7 = r1.getCE32FromContexts(r0)
            r4.handleCE32(r5, r6, r7)
        L_0x0013:
            android.icu.util.CharsTrie r1 = new android.icu.util.CharsTrie
            android.icu.impl.coll.CollationData r2 = r4.data
            java.lang.String r2 = r2.contexts
            int r3 = r0 + 2
            r1.<init>(r2, r3)
            android.icu.util.CharsTrie$Iterator r1 = r1.iterator()
        L_0x0022:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L_0x004c
            android.icu.util.CharsTrie$Entry r2 = r1.next()
            java.lang.CharSequence r3 = r2.chars
            java.lang.String r3 = r3.toString()
            r4.suffix = r3
            android.icu.text.UnicodeSet r3 = r4.contractions
            r4.addStrings(r5, r6, r3)
            java.lang.StringBuilder r3 = r4.unreversedPrefix
            int r3 = r3.length()
            if (r3 == 0) goto L_0x0046
            android.icu.text.UnicodeSet r3 = r4.expansions
            r4.addStrings(r5, r6, r3)
        L_0x0046:
            int r3 = r2.value
            r4.handleCE32(r5, r6, r3)
            goto L_0x0022
        L_0x004c:
            r2 = 0
            r4.suffix = r2
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.icu.impl.coll.ContractionsAndExpansions.handleContractions(int, int, int):void");
    }

    /* access modifiers changed from: package-private */
    public void addExpansions(int start, int end) {
        if (this.unreversedPrefix.length() == 0 && this.suffix == null) {
            UnicodeSet unicodeSet = this.expansions;
            if (unicodeSet != null) {
                unicodeSet.add(start, end);
                return;
            }
            return;
        }
        addStrings(start, end, this.expansions);
    }

    /* access modifiers changed from: package-private */
    public void addStrings(int start, int end, UnicodeSet set) {
        if (set != null) {
            StringBuilder s = new StringBuilder(this.unreversedPrefix);
            do {
                s.appendCodePoint(start);
                String str = this.suffix;
                if (str != null) {
                    s.append(str);
                }
                set.add(s);
                s.setLength(this.unreversedPrefix.length());
                start++;
            } while (start <= end);
        }
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
