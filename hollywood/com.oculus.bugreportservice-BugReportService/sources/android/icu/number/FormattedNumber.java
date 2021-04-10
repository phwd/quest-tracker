package android.icu.number;

import android.icu.impl.number.DecimalQuantity;
import android.icu.impl.number.NumberStringBuilder;
import android.icu.util.ICUUncheckedIOException;
import java.io.IOException;
import java.text.AttributedCharacterIterator;
import java.text.FieldPosition;
import java.util.Arrays;

public class FormattedNumber {
    final DecimalQuantity fq;
    final NumberStringBuilder nsb;

    FormattedNumber(NumberStringBuilder numberStringBuilder, DecimalQuantity decimalQuantity) {
        this.nsb = numberStringBuilder;
        this.fq = decimalQuantity;
    }

    public String toString() {
        this.nsb.toString();
        throw null;
    }

    public Appendable appendTo(Appendable appendable) {
        try {
            appendable.append(this.nsb);
            return appendable;
        } catch (IOException e) {
            throw new ICUUncheckedIOException(e);
        }
    }

    public boolean nextFieldPosition(FieldPosition fieldPosition) {
        this.fq.populateUFieldPosition(fieldPosition);
        return this.nsb.nextFieldPosition(fieldPosition);
    }

    public AttributedCharacterIterator getFieldIterator() {
        this.nsb.toCharacterIterator();
        throw null;
    }

    public int hashCode() {
        return this.fq.toBigDecimal().hashCode() ^ (Arrays.hashCode(this.nsb.toCharArray()) ^ Arrays.hashCode(this.nsb.toFieldArray()));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof FormattedNumber)) {
            return false;
        }
        FormattedNumber formattedNumber = (FormattedNumber) obj;
        return Arrays.equals(this.nsb.toCharArray(), formattedNumber.nsb.toCharArray()) && Arrays.equals(this.nsb.toFieldArray(), formattedNumber.nsb.toFieldArray()) && this.fq.toBigDecimal().equals(formattedNumber.fq.toBigDecimal());
    }
}
