package android.icu.impl.number.parse;

import android.icu.impl.StringSegment;
import android.icu.impl.number.DecimalQuantity_DualStorageBCD;
import java.util.Comparator;

public class ParsedNumber {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final Comparator<ParsedNumber> COMPARATOR = new Comparator<ParsedNumber>() {
        /* class android.icu.impl.number.parse.ParsedNumber.AnonymousClass1 */

        public int compare(ParsedNumber o1, ParsedNumber o2) {
            return o1.charEnd - o2.charEnd;
        }
    };
    public static final int FLAG_FAIL = 256;
    public static final int FLAG_HAS_DECIMAL_SEPARATOR = 32;
    public static final int FLAG_HAS_EXPONENT = 8;
    public static final int FLAG_INFINITY = 128;
    public static final int FLAG_NAN = 64;
    public static final int FLAG_NEGATIVE = 1;
    public static final int FLAG_PERCENT = 2;
    public static final int FLAG_PERMILLE = 4;
    public int charEnd;
    public String currencyCode;
    public int flags;
    public String prefix;
    public DecimalQuantity_DualStorageBCD quantity;
    public String suffix;

    public ParsedNumber() {
        clear();
    }

    public void clear() {
        this.quantity = null;
        this.charEnd = 0;
        this.flags = 0;
        this.prefix = null;
        this.suffix = null;
        this.currencyCode = null;
    }

    public void copyFrom(ParsedNumber other) {
        DecimalQuantity_DualStorageBCD decimalQuantity_DualStorageBCD;
        DecimalQuantity_DualStorageBCD decimalQuantity_DualStorageBCD2 = other.quantity;
        if (decimalQuantity_DualStorageBCD2 == null) {
            decimalQuantity_DualStorageBCD = null;
        } else {
            decimalQuantity_DualStorageBCD = (DecimalQuantity_DualStorageBCD) decimalQuantity_DualStorageBCD2.createCopy();
        }
        this.quantity = decimalQuantity_DualStorageBCD;
        this.charEnd = other.charEnd;
        this.flags = other.flags;
        this.prefix = other.prefix;
        this.suffix = other.suffix;
        this.currencyCode = other.currencyCode;
    }

    public void setCharsConsumed(StringSegment segment) {
        this.charEnd = segment.getOffset();
    }

    public void postProcess() {
        DecimalQuantity_DualStorageBCD decimalQuantity_DualStorageBCD = this.quantity;
        if (decimalQuantity_DualStorageBCD != null && (this.flags & 1) != 0) {
            decimalQuantity_DualStorageBCD.negate();
        }
    }

    public boolean success() {
        return this.charEnd > 0 && (this.flags & 256) == 0;
    }

    public boolean seenNumber() {
        if (this.quantity == null) {
            int i = this.flags;
            return ((i & 64) == 0 && (i & 128) == 0) ? false : true;
        }
    }

    public Number getNumber() {
        return getNumber(0);
    }

    public Number getNumber(int parseFlags) {
        boolean sawNaN = (this.flags & 64) != 0;
        boolean sawInfinity = (this.flags & 128) != 0;
        boolean forceBigDecimal = (parseFlags & 4096) != 0;
        boolean integerOnly = (parseFlags & 16) != 0;
        if (sawNaN) {
            return Double.valueOf(Double.NaN);
        }
        if (sawInfinity) {
            if ((1 & this.flags) != 0) {
                return Double.valueOf(Double.NEGATIVE_INFINITY);
            }
            return Double.valueOf(Double.POSITIVE_INFINITY);
        } else if (this.quantity.isZero() && this.quantity.isNegative() && !integerOnly) {
            return Double.valueOf(-0.0d);
        } else {
            if (!this.quantity.fitsInLong() || forceBigDecimal) {
                return this.quantity.toBigDecimal();
            }
            return Long.valueOf(this.quantity.toLong(false));
        }
    }

    /* access modifiers changed from: package-private */
    public boolean isBetterThan(ParsedNumber other) {
        return COMPARATOR.compare(this, other) > 0;
    }
}
