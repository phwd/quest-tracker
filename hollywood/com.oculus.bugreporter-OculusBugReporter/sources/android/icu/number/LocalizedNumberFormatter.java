package android.icu.number;

import android.icu.impl.StandardPlural;
import android.icu.impl.number.DecimalQuantity;
import android.icu.impl.number.DecimalQuantity_DualStorageBCD;
import android.icu.impl.number.LocalizedNumberFormatterAsFormat;
import android.icu.impl.number.MacroProps;
import android.icu.impl.number.NumberStringBuilder;
import android.icu.util.Measure;
import android.icu.util.MeasureUnit;
import java.text.Format;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;

public class LocalizedNumberFormatter extends NumberFormatterSettings<LocalizedNumberFormatter> {
    static final AtomicLongFieldUpdater<LocalizedNumberFormatter> callCount = AtomicLongFieldUpdater.newUpdater(LocalizedNumberFormatter.class, "callCountInternal");
    volatile long callCountInternal;
    volatile NumberFormatterImpl compiled;
    volatile LocalizedNumberFormatter savedWithUnit;

    LocalizedNumberFormatter(NumberFormatterSettings<?> parent, int key, Object value) {
        super(parent, key, value);
    }

    public FormattedNumber format(long input) {
        return format(new DecimalQuantity_DualStorageBCD(input));
    }

    public FormattedNumber format(double input) {
        return format(new DecimalQuantity_DualStorageBCD(input));
    }

    public FormattedNumber format(Number input) {
        return format(new DecimalQuantity_DualStorageBCD(input));
    }

    public FormattedNumber format(Measure input) {
        MeasureUnit unit = input.getUnit();
        Number number = input.getNumber();
        if (Objects.equals(resolve().unit, unit)) {
            return format(number);
        }
        LocalizedNumberFormatter withUnit = this.savedWithUnit;
        if (withUnit == null || !Objects.equals(withUnit.resolve().unit, unit)) {
            withUnit = new LocalizedNumberFormatter(this, 3, unit);
            this.savedWithUnit = withUnit;
        }
        return withUnit.format(number);
    }

    public Format toFormat() {
        return new LocalizedNumberFormatterAsFormat(this, resolve().loc);
    }

    @Deprecated
    public FormattedNumber format(DecimalQuantity fq) {
        NumberStringBuilder string = new NumberStringBuilder();
        if (computeCompiled()) {
            this.compiled.format(fq, string);
        } else {
            NumberFormatterImpl.formatStatic(resolve(), fq, string);
        }
        return new FormattedNumber(string, fq);
    }

    @Deprecated
    public String getAffixImpl(boolean isPrefix, boolean isNegative) {
        int prefixLength;
        NumberStringBuilder string = new NumberStringBuilder();
        byte signum = (byte) (isNegative ? -1 : 1);
        StandardPlural plural = StandardPlural.OTHER;
        if (computeCompiled()) {
            prefixLength = this.compiled.getPrefixSuffix(signum, plural, string);
        } else {
            prefixLength = NumberFormatterImpl.getPrefixSuffixStatic(resolve(), signum, plural, string);
        }
        if (isPrefix) {
            return string.subSequence(0, prefixLength).toString();
        }
        return string.subSequence(prefixLength, string.length()).toString();
    }

    private boolean computeCompiled() {
        MacroProps macros = resolve();
        if (callCount.incrementAndGet(this) == macros.threshold.longValue()) {
            this.compiled = new NumberFormatterImpl(macros);
            return true;
        } else if (this.compiled != null) {
            return true;
        } else {
            return false;
        }
    }

    /* access modifiers changed from: package-private */
    @Override // android.icu.number.NumberFormatterSettings
    public LocalizedNumberFormatter create(int key, Object value) {
        return new LocalizedNumberFormatter(this, key, value);
    }
}
