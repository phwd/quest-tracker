package android.icu.number;

import android.icu.impl.number.MacroProps;
import android.icu.impl.number.Padder;
import android.icu.number.NumberFormatter;
import android.icu.number.NumberFormatterSettings;
import android.icu.text.DecimalFormatSymbols;
import android.icu.text.NumberingSystem;
import android.icu.util.MeasureUnit;
import android.icu.util.ULocale;
import java.math.RoundingMode;

public abstract class NumberFormatterSettings<T extends NumberFormatterSettings<?>> {
    static final int KEY_DECIMAL = 12;
    static final int KEY_GROUPING = 6;
    static final int KEY_INTEGER = 8;
    static final int KEY_LOCALE = 1;
    static final int KEY_MACROS = 0;
    static final int KEY_MAX = 16;
    static final int KEY_NOTATION = 2;
    static final int KEY_PADDER = 7;
    static final int KEY_PER_UNIT = 15;
    static final int KEY_PRECISION = 4;
    static final int KEY_ROUNDING_MODE = 5;
    static final int KEY_SCALE = 13;
    static final int KEY_SIGN = 11;
    static final int KEY_SYMBOLS = 9;
    static final int KEY_THRESHOLD = 14;
    static final int KEY_UNIT = 3;
    static final int KEY_UNIT_WIDTH = 10;
    private final int key;
    private final NumberFormatterSettings<?> parent;
    private volatile MacroProps resolvedMacros;
    private final Object value;

    /* access modifiers changed from: package-private */
    public abstract T create(int i, Object obj);

    NumberFormatterSettings(NumberFormatterSettings<?> parent2, int key2, Object value2) {
        this.parent = parent2;
        this.key = key2;
        this.value = value2;
    }

    public T notation(Notation notation) {
        return create(2, notation);
    }

    public T unit(MeasureUnit unit) {
        return create(3, unit);
    }

    public T perUnit(MeasureUnit perUnit) {
        return create(15, perUnit);
    }

    public T precision(Precision precision) {
        return create(4, precision);
    }

    @Deprecated
    public T rounding(Precision rounder) {
        return precision(rounder);
    }

    public T roundingMode(RoundingMode roundingMode) {
        return create(5, roundingMode);
    }

    public T grouping(NumberFormatter.GroupingStrategy strategy) {
        return create(6, strategy);
    }

    public T integerWidth(IntegerWidth style) {
        return create(8, style);
    }

    public T symbols(DecimalFormatSymbols symbols) {
        return create(9, (DecimalFormatSymbols) symbols.clone());
    }

    public T symbols(NumberingSystem ns) {
        return create(9, ns);
    }

    public T unitWidth(NumberFormatter.UnitWidth style) {
        return create(10, style);
    }

    public T sign(NumberFormatter.SignDisplay style) {
        return create(11, style);
    }

    public T decimal(NumberFormatter.DecimalSeparatorDisplay style) {
        return create(12, style);
    }

    public T scale(Scale scale) {
        return create(13, scale);
    }

    @Deprecated
    public T macros(MacroProps macros) {
        return create(0, macros);
    }

    @Deprecated
    public T padding(Padder padder) {
        return create(7, padder);
    }

    @Deprecated
    public T threshold(Long threshold) {
        return create(14, threshold);
    }

    public String toSkeleton() {
        return NumberSkeletonImpl.generate(resolve());
    }

    /* access modifiers changed from: package-private */
    public MacroProps resolve() {
        if (this.resolvedMacros != null) {
            return this.resolvedMacros;
        }
        MacroProps macros = new MacroProps();
        for (NumberFormatterSettings numberFormatterSettings = this; numberFormatterSettings != null; numberFormatterSettings = numberFormatterSettings.parent) {
            switch (numberFormatterSettings.key) {
                case 0:
                    macros.fallback((MacroProps) numberFormatterSettings.value);
                    break;
                case 1:
                    if (macros.loc == null) {
                        macros.loc = (ULocale) numberFormatterSettings.value;
                        break;
                    } else {
                        break;
                    }
                case 2:
                    if (macros.notation == null) {
                        macros.notation = (Notation) numberFormatterSettings.value;
                        break;
                    } else {
                        break;
                    }
                case 3:
                    if (macros.unit == null) {
                        macros.unit = (MeasureUnit) numberFormatterSettings.value;
                        break;
                    } else {
                        break;
                    }
                case 4:
                    if (macros.precision == null) {
                        macros.precision = (Precision) numberFormatterSettings.value;
                        break;
                    } else {
                        break;
                    }
                case 5:
                    if (macros.roundingMode == null) {
                        macros.roundingMode = (RoundingMode) numberFormatterSettings.value;
                        break;
                    } else {
                        break;
                    }
                case 6:
                    if (macros.grouping == null) {
                        macros.grouping = numberFormatterSettings.value;
                        break;
                    } else {
                        break;
                    }
                case 7:
                    if (macros.padder == null) {
                        macros.padder = (Padder) numberFormatterSettings.value;
                        break;
                    } else {
                        break;
                    }
                case 8:
                    if (macros.integerWidth == null) {
                        macros.integerWidth = (IntegerWidth) numberFormatterSettings.value;
                        break;
                    } else {
                        break;
                    }
                case 9:
                    if (macros.symbols == null) {
                        macros.symbols = numberFormatterSettings.value;
                        break;
                    } else {
                        break;
                    }
                case 10:
                    if (macros.unitWidth == null) {
                        macros.unitWidth = (NumberFormatter.UnitWidth) numberFormatterSettings.value;
                        break;
                    } else {
                        break;
                    }
                case 11:
                    if (macros.sign == null) {
                        macros.sign = (NumberFormatter.SignDisplay) numberFormatterSettings.value;
                        break;
                    } else {
                        break;
                    }
                case 12:
                    if (macros.decimal == null) {
                        macros.decimal = (NumberFormatter.DecimalSeparatorDisplay) numberFormatterSettings.value;
                        break;
                    } else {
                        break;
                    }
                case 13:
                    if (macros.scale == null) {
                        macros.scale = (Scale) numberFormatterSettings.value;
                        break;
                    } else {
                        break;
                    }
                case 14:
                    if (macros.threshold == null) {
                        macros.threshold = (Long) numberFormatterSettings.value;
                        break;
                    } else {
                        break;
                    }
                case 15:
                    if (macros.perUnit == null) {
                        macros.perUnit = (MeasureUnit) numberFormatterSettings.value;
                        break;
                    } else {
                        break;
                    }
                default:
                    throw new AssertionError((Object) ("Unknown key: " + numberFormatterSettings.key));
            }
        }
        this.resolvedMacros = macros;
        return macros;
    }

    public int hashCode() {
        return resolve().hashCode();
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other != null && (other instanceof NumberFormatterSettings)) {
            return resolve().equals(((NumberFormatterSettings) other).resolve());
        }
        return false;
    }
}
