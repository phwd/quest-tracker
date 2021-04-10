package android.icu.text;

import android.icu.impl.coll.CollationData;
import android.icu.impl.coll.CollationIterator;
import android.icu.impl.coll.CollationSettings;
import android.icu.impl.coll.ContractionsAndExpansions;
import android.icu.impl.coll.FCDUTF16CollationIterator;
import android.icu.impl.coll.UTF16CollationIterator;
import android.icu.impl.coll.UVector32;
import java.util.HashMap;
import java.util.Map;

public final class CollationElementIterator {
    private byte dir_;
    private CollationIterator iter_;
    private UVector32 offsets_;
    private int otherHalf_;
    private RuleBasedCollator rbc_;
    private String string_;

    /* access modifiers changed from: private */
    public static final boolean ceNeedsTwoParts(long j) {
        return (j & 281470698455103L) != 0;
    }

    /* access modifiers changed from: private */
    public static final int getFirstHalf(long j, int i) {
        return (((int) j) & -65536) | ((i >> 16) & 65280) | ((i >> 8) & 255);
    }

    /* access modifiers changed from: private */
    public static final int getSecondHalf(long j, int i) {
        return (((int) j) << 16) | ((i >> 8) & 65280) | (i & 63);
    }

    public static final int primaryOrder(int i) {
        return (i >>> 16) & 65535;
    }

    public int hashCode() {
        return 42;
    }

    private CollationElementIterator(RuleBasedCollator ruleBasedCollator) {
        this.iter_ = null;
        this.rbc_ = ruleBasedCollator;
        this.otherHalf_ = 0;
        this.dir_ = 0;
        this.offsets_ = null;
    }

    CollationElementIterator(String str, RuleBasedCollator ruleBasedCollator) {
        this(ruleBasedCollator);
        setText(str);
    }

    public int getOffset() {
        UVector32 uVector32;
        if (this.dir_ >= 0 || (uVector32 = this.offsets_) == null || uVector32.isEmpty()) {
            return this.iter_.getOffset();
        }
        int cEsLength = this.iter_.getCEsLength();
        if (this.otherHalf_ != 0) {
            cEsLength++;
        }
        return this.offsets_.elementAti(cEsLength);
    }

    public int next() {
        byte b = this.dir_;
        if (b > 1) {
            int i = this.otherHalf_;
            if (i != 0) {
                this.otherHalf_ = 0;
                return i;
            }
        } else if (b == 1) {
            this.dir_ = 2;
        } else if (b == 0) {
            this.dir_ = 2;
        } else {
            throw new IllegalStateException("Illegal change of direction");
        }
        this.iter_.clearCEsIfNoneRemaining();
        long nextCE = this.iter_.nextCE();
        if (nextCE == 4311744768L) {
            return -1;
        }
        long j = nextCE >>> 32;
        int i2 = (int) nextCE;
        int firstHalf = getFirstHalf(j, i2);
        int secondHalf = getSecondHalf(j, i2);
        if (secondHalf != 0) {
            this.otherHalf_ = secondHalf | 192;
        }
        return firstHalf;
    }

    public void setText(String str) {
        CollationIterator collationIterator;
        this.string_ = str;
        boolean isNumeric = ((CollationSettings) this.rbc_.settings.readOnly()).isNumeric();
        if (((CollationSettings) this.rbc_.settings.readOnly()).dontCheckFCD()) {
            collationIterator = new UTF16CollationIterator(this.rbc_.data, isNumeric, this.string_, 0);
        } else {
            collationIterator = new FCDUTF16CollationIterator(this.rbc_.data, isNumeric, this.string_, 0);
        }
        this.iter_ = collationIterator;
        this.otherHalf_ = 0;
        this.dir_ = 0;
    }

    private static final class MaxExpSink implements ContractionsAndExpansions.CESink {
        private Map maxExpansions;

        @Override // android.icu.impl.coll.ContractionsAndExpansions.CESink
        public void handleCE(long j) {
        }

        MaxExpSink(Map map) {
            this.maxExpansions = map;
        }

        @Override // android.icu.impl.coll.ContractionsAndExpansions.CESink
        public void handleExpansion(long[] jArr, int i, int i2) {
            if (i2 > 1) {
                int i3 = 0;
                for (int i4 = 0; i4 < i2; i4++) {
                    i3 += CollationElementIterator.ceNeedsTwoParts(jArr[i + i4]) ? 2 : 1;
                }
                long j = jArr[(i + i2) - 1];
                long j2 = j >>> 32;
                int i5 = (int) j;
                int secondHalf = CollationElementIterator.getSecondHalf(j2, i5);
                int firstHalf = secondHalf == 0 ? CollationElementIterator.getFirstHalf(j2, i5) : secondHalf | 192;
                Integer num = (Integer) this.maxExpansions.get(Integer.valueOf(firstHalf));
                if (num == null || i3 > num.intValue()) {
                    this.maxExpansions.put(Integer.valueOf(firstHalf), Integer.valueOf(i3));
                }
            }
        }
    }

    static final Map computeMaxExpansions(CollationData collationData) {
        HashMap hashMap = new HashMap();
        new ContractionsAndExpansions(null, null, new MaxExpSink(hashMap), true).forData(collationData);
        return hashMap;
    }

    private byte normalizeDir() {
        byte b = this.dir_;
        if (b == 1) {
            return 0;
        }
        return b;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof CollationElementIterator)) {
            return false;
        }
        CollationElementIterator collationElementIterator = (CollationElementIterator) obj;
        return this.rbc_.equals(collationElementIterator.rbc_) && this.otherHalf_ == collationElementIterator.otherHalf_ && normalizeDir() == collationElementIterator.normalizeDir() && this.string_.equals(collationElementIterator.string_) && this.iter_.equals(collationElementIterator.iter_);
    }
}
