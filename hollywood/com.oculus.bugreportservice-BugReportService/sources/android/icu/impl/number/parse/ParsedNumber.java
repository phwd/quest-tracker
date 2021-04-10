package android.icu.impl.number.parse;

import android.icu.impl.StringSegment;
import android.icu.impl.number.DecimalQuantity_DualStorageBCD;
import java.util.Comparator;

public class ParsedNumber {
    public static final Comparator COMPARATOR = new Comparator() {
        /* class android.icu.impl.number.parse.ParsedNumber.AnonymousClass1 */

        public int compare(ParsedNumber parsedNumber, ParsedNumber parsedNumber2) {
            return parsedNumber.charEnd - parsedNumber2.charEnd;
        }
    };
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

    public void copyFrom(ParsedNumber parsedNumber) {
        DecimalQuantity_DualStorageBCD decimalQuantity_DualStorageBCD;
        DecimalQuantity_DualStorageBCD decimalQuantity_DualStorageBCD2 = parsedNumber.quantity;
        if (decimalQuantity_DualStorageBCD2 == null) {
            decimalQuantity_DualStorageBCD = null;
        } else {
            decimalQuantity_DualStorageBCD = (DecimalQuantity_DualStorageBCD) decimalQuantity_DualStorageBCD2.createCopy();
        }
        this.quantity = decimalQuantity_DualStorageBCD;
        this.charEnd = parsedNumber.charEnd;
        this.flags = parsedNumber.flags;
        this.prefix = parsedNumber.prefix;
        this.suffix = parsedNumber.suffix;
        this.currencyCode = parsedNumber.currencyCode;
    }

    public void setCharsConsumed(StringSegment stringSegment) {
        this.charEnd = stringSegment.getOffset();
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

    public Number getNumber(int i) {
        boolean z = (this.flags & 64) != 0;
        boolean z2 = (this.flags & 128) != 0;
        boolean z3 = (i & 4096) != 0;
        boolean z4 = (i & 16) != 0;
        if (z) {
            return Double.valueOf(Double.NaN);
        }
        if (z2) {
            if ((this.flags & 1) != 0) {
                return Double.valueOf(Double.NEGATIVE_INFINITY);
            }
            return Double.valueOf(Double.POSITIVE_INFINITY);
        } else if (this.quantity.isZero() && this.quantity.isNegative() && !z4) {
            return Double.valueOf(-0.0d);
        } else {
            if (!this.quantity.fitsInLong() || z3) {
                return this.quantity.toBigDecimal();
            }
            return Long.valueOf(this.quantity.toLong(false));
        }
    }

    /* access modifiers changed from: package-private */
    public boolean isBetterThan(ParsedNumber parsedNumber) {
        return COMPARATOR.compare(this, parsedNumber) > 0;
    }
}
