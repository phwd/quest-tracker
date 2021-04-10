package android.icu.number;

import android.icu.impl.number.DecimalQuantity;
import android.icu.impl.number.NumberStringBuilder;
import android.icu.number.NumberRangeFormatter;
import android.icu.util.ICUUncheckedIOException;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.AttributedCharacterIterator;
import java.text.FieldPosition;
import java.util.Arrays;

public class FormattedNumberRange {
    final NumberRangeFormatter.RangeIdentityResult identityResult;
    final DecimalQuantity quantity1;
    final DecimalQuantity quantity2;
    final NumberStringBuilder string;

    FormattedNumberRange(NumberStringBuilder string2, DecimalQuantity quantity12, DecimalQuantity quantity22, NumberRangeFormatter.RangeIdentityResult identityResult2) {
        this.string = string2;
        this.quantity1 = quantity12;
        this.quantity2 = quantity22;
        this.identityResult = identityResult2;
    }

    public String toString() {
        return this.string.toString();
    }

    public <A extends Appendable> A appendTo(A appendable) {
        try {
            appendable.append(this.string);
            return appendable;
        } catch (IOException e) {
            throw new ICUUncheckedIOException(e);
        }
    }

    public boolean nextFieldPosition(FieldPosition fieldPosition) {
        return this.string.nextFieldPosition(fieldPosition);
    }

    public AttributedCharacterIterator toCharacterIterator() {
        return this.string.toCharacterIterator();
    }

    public BigDecimal getFirstBigDecimal() {
        return this.quantity1.toBigDecimal();
    }

    public BigDecimal getSecondBigDecimal() {
        return this.quantity2.toBigDecimal();
    }

    public NumberRangeFormatter.RangeIdentityResult getIdentityResult() {
        return this.identityResult;
    }

    public int hashCode() {
        return ((Arrays.hashCode(this.string.toCharArray()) ^ Arrays.hashCode(this.string.toFieldArray())) ^ this.quantity1.toBigDecimal().hashCode()) ^ this.quantity2.toBigDecimal().hashCode();
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || !(other instanceof FormattedNumberRange)) {
            return false;
        }
        FormattedNumberRange _other = (FormattedNumberRange) other;
        if (!Arrays.equals(this.string.toCharArray(), _other.string.toCharArray()) || !Arrays.equals(this.string.toFieldArray(), _other.string.toFieldArray()) || !this.quantity1.toBigDecimal().equals(_other.quantity1.toBigDecimal()) || !this.quantity2.toBigDecimal().equals(_other.quantity2.toBigDecimal())) {
            return false;
        }
        return true;
    }
}
