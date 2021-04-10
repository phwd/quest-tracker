package android.icu.number;

import android.icu.impl.number.DecimalQuantity;
import android.icu.impl.number.NumberStringBuilder;
import android.icu.text.PluralRules;
import android.icu.util.ICUUncheckedIOException;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.AttributedCharacterIterator;
import java.text.FieldPosition;
import java.util.Arrays;

public class FormattedNumber {
    final DecimalQuantity fq;
    final NumberStringBuilder nsb;

    FormattedNumber(NumberStringBuilder nsb2, DecimalQuantity fq2) {
        this.nsb = nsb2;
        this.fq = fq2;
    }

    public String toString() {
        return this.nsb.toString();
    }

    public <A extends Appendable> A appendTo(A appendable) {
        try {
            appendable.append(this.nsb);
            return appendable;
        } catch (IOException e) {
            throw new ICUUncheckedIOException(e);
        }
    }

    @Deprecated
    public void populateFieldPosition(FieldPosition fieldPosition) {
        fieldPosition.setBeginIndex(0);
        fieldPosition.setEndIndex(0);
        nextFieldPosition(fieldPosition);
    }

    public boolean nextFieldPosition(FieldPosition fieldPosition) {
        this.fq.populateUFieldPosition(fieldPosition);
        return this.nsb.nextFieldPosition(fieldPosition);
    }

    @Deprecated
    public AttributedCharacterIterator getFieldIterator() {
        return this.nsb.toCharacterIterator();
    }

    public AttributedCharacterIterator toCharacterIterator() {
        return this.nsb.toCharacterIterator();
    }

    public BigDecimal toBigDecimal() {
        return this.fq.toBigDecimal();
    }

    @Deprecated
    public PluralRules.IFixedDecimal getFixedDecimal() {
        return this.fq;
    }

    public int hashCode() {
        return (Arrays.hashCode(this.nsb.toCharArray()) ^ Arrays.hashCode(this.nsb.toFieldArray())) ^ this.fq.toBigDecimal().hashCode();
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || !(other instanceof FormattedNumber)) {
            return false;
        }
        FormattedNumber _other = (FormattedNumber) other;
        if (!Arrays.equals(this.nsb.toCharArray(), _other.nsb.toCharArray()) || !Arrays.equals(this.nsb.toFieldArray(), _other.nsb.toFieldArray()) || !this.fq.toBigDecimal().equals(_other.fq.toBigDecimal())) {
            return false;
        }
        return true;
    }
}
