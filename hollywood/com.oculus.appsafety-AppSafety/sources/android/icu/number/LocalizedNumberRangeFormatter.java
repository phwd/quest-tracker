package android.icu.number;

import android.icu.impl.number.DecimalQuantity;
import android.icu.impl.number.DecimalQuantity_DualStorageBCD;

public class LocalizedNumberRangeFormatter extends NumberRangeFormatterSettings<LocalizedNumberRangeFormatter> {
    private volatile NumberRangeFormatterImpl fImpl;

    LocalizedNumberRangeFormatter(NumberRangeFormatterSettings<?> parent, int key, Object value) {
        super(parent, key, value);
    }

    public FormattedNumberRange formatRange(int first, int second) {
        return formatImpl(new DecimalQuantity_DualStorageBCD(first), new DecimalQuantity_DualStorageBCD(second), first == second);
    }

    public FormattedNumberRange formatRange(double first, double second) {
        return formatImpl(new DecimalQuantity_DualStorageBCD(first), new DecimalQuantity_DualStorageBCD(second), first == second);
    }

    public FormattedNumberRange formatRange(Number first, Number second) {
        if (first != null && second != null) {
            return formatImpl(new DecimalQuantity_DualStorageBCD(first), new DecimalQuantity_DualStorageBCD(second), first.equals(second));
        }
        throw new IllegalArgumentException("Cannot format null values in range");
    }

    /* access modifiers changed from: package-private */
    public FormattedNumberRange formatImpl(DecimalQuantity first, DecimalQuantity second, boolean equalBeforeRounding) {
        if (this.fImpl == null) {
            this.fImpl = new NumberRangeFormatterImpl(resolve());
        }
        return this.fImpl.format(first, second, equalBeforeRounding);
    }

    /* access modifiers changed from: package-private */
    @Override // android.icu.number.NumberRangeFormatterSettings
    public LocalizedNumberRangeFormatter create(int key, Object value) {
        return new LocalizedNumberRangeFormatter(this, key, value);
    }
}
