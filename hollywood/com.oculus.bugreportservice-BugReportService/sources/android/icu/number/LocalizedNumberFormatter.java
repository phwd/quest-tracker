package android.icu.number;

import android.icu.impl.number.DecimalQuantity;
import android.icu.impl.number.DecimalQuantity_DualStorageBCD;
import android.icu.impl.number.MacroProps;
import android.icu.impl.number.NumberStringBuilder;
import android.icu.util.Measure;
import android.icu.util.MeasureUnit;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;

public class LocalizedNumberFormatter extends NumberFormatterSettings {
    static final AtomicLongFieldUpdater callCount = AtomicLongFieldUpdater.newUpdater(LocalizedNumberFormatter.class, "callCountInternal");
    volatile long callCountInternal;
    volatile NumberFormatterImpl compiled;
    volatile LocalizedNumberFormatter savedWithUnit;

    LocalizedNumberFormatter(NumberFormatterSettings numberFormatterSettings, int i, Object obj) {
        super(numberFormatterSettings, i, obj);
    }

    public FormattedNumber format(long j) {
        return format(new DecimalQuantity_DualStorageBCD(j));
    }

    public FormattedNumber format(double d) {
        return format(new DecimalQuantity_DualStorageBCD(d));
    }

    public FormattedNumber format(Number number) {
        return format(new DecimalQuantity_DualStorageBCD(number));
    }

    public FormattedNumber format(Measure measure) {
        MeasureUnit unit = measure.getUnit();
        Number number = measure.getNumber();
        if (Objects.equals(resolve().unit, unit)) {
            return format(number);
        }
        LocalizedNumberFormatter localizedNumberFormatter = this.savedWithUnit;
        if (localizedNumberFormatter == null || !Objects.equals(localizedNumberFormatter.resolve().unit, unit)) {
            localizedNumberFormatter = new LocalizedNumberFormatter(this, 3, unit);
            this.savedWithUnit = localizedNumberFormatter;
        }
        return localizedNumberFormatter.format(number);
    }

    public FormattedNumber format(DecimalQuantity decimalQuantity) {
        NumberStringBuilder numberStringBuilder = new NumberStringBuilder();
        if (computeCompiled()) {
            this.compiled.format(decimalQuantity, numberStringBuilder);
        } else {
            NumberFormatterImpl.formatStatic(resolve(), decimalQuantity, numberStringBuilder);
        }
        return new FormattedNumber(numberStringBuilder, decimalQuantity);
    }

    private boolean computeCompiled() {
        MacroProps resolve = resolve();
        if (callCount.incrementAndGet(this) == resolve.threshold.longValue()) {
            this.compiled = new NumberFormatterImpl(resolve);
            return true;
        } else if (this.compiled != null) {
            return true;
        } else {
            return false;
        }
    }

    /* access modifiers changed from: package-private */
    @Override // android.icu.number.NumberFormatterSettings
    public LocalizedNumberFormatter create(int i, Object obj) {
        return new LocalizedNumberFormatter(this, i, obj);
    }
}
