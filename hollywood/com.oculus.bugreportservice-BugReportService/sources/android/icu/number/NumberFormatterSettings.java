package android.icu.number;

import android.icu.impl.number.MacroProps;
import android.icu.impl.number.Padder;
import android.icu.number.NumberFormatter;
import android.icu.util.MeasureUnit;
import android.icu.util.ULocale;
import java.math.RoundingMode;

public abstract class NumberFormatterSettings {
    private final int key;
    private final NumberFormatterSettings parent;
    private volatile MacroProps resolvedMacros;
    private final Object value;

    /* access modifiers changed from: package-private */
    public abstract NumberFormatterSettings create(int i, Object obj);

    NumberFormatterSettings(NumberFormatterSettings numberFormatterSettings, int i, Object obj) {
        this.parent = numberFormatterSettings;
        this.key = i;
        this.value = obj;
    }

    public NumberFormatterSettings macros(MacroProps macroProps) {
        return create(0, macroProps);
    }

    /* access modifiers changed from: package-private */
    public MacroProps resolve() {
        if (this.resolvedMacros != null) {
            return this.resolvedMacros;
        }
        MacroProps macroProps = new MacroProps();
        for (NumberFormatterSettings numberFormatterSettings = this; numberFormatterSettings != null; numberFormatterSettings = numberFormatterSettings.parent) {
            switch (numberFormatterSettings.key) {
                case 0:
                    macroProps.fallback((MacroProps) numberFormatterSettings.value);
                    break;
                case 1:
                    if (macroProps.loc == null) {
                        macroProps.loc = (ULocale) numberFormatterSettings.value;
                        break;
                    } else {
                        break;
                    }
                case 2:
                    if (macroProps.notation == null) {
                        macroProps.notation = (Notation) numberFormatterSettings.value;
                        break;
                    } else {
                        break;
                    }
                case 3:
                    if (macroProps.unit == null) {
                        macroProps.unit = (MeasureUnit) numberFormatterSettings.value;
                        break;
                    } else {
                        break;
                    }
                case 4:
                    if (macroProps.precision == null) {
                        macroProps.precision = (Precision) numberFormatterSettings.value;
                        break;
                    } else {
                        break;
                    }
                case 5:
                    if (macroProps.roundingMode == null) {
                        macroProps.roundingMode = (RoundingMode) numberFormatterSettings.value;
                        break;
                    } else {
                        break;
                    }
                case 6:
                    if (macroProps.grouping == null) {
                        macroProps.grouping = numberFormatterSettings.value;
                        break;
                    } else {
                        break;
                    }
                case 7:
                    if (macroProps.padder == null) {
                        macroProps.padder = (Padder) numberFormatterSettings.value;
                        break;
                    } else {
                        break;
                    }
                case 8:
                    if (macroProps.integerWidth == null) {
                        macroProps.integerWidth = (IntegerWidth) numberFormatterSettings.value;
                        break;
                    } else {
                        break;
                    }
                case 9:
                    if (macroProps.symbols == null) {
                        macroProps.symbols = numberFormatterSettings.value;
                        break;
                    } else {
                        break;
                    }
                case 10:
                    if (macroProps.unitWidth == null) {
                        macroProps.unitWidth = (NumberFormatter.UnitWidth) numberFormatterSettings.value;
                        break;
                    } else {
                        break;
                    }
                case 11:
                    if (macroProps.sign == null) {
                        macroProps.sign = (NumberFormatter.SignDisplay) numberFormatterSettings.value;
                        break;
                    } else {
                        break;
                    }
                case 12:
                    if (macroProps.decimal == null) {
                        macroProps.decimal = (NumberFormatter.DecimalSeparatorDisplay) numberFormatterSettings.value;
                        break;
                    } else {
                        break;
                    }
                case 13:
                    if (macroProps.scale == null) {
                        macroProps.scale = (Scale) numberFormatterSettings.value;
                        break;
                    } else {
                        break;
                    }
                case 14:
                    if (macroProps.threshold == null) {
                        macroProps.threshold = (Long) numberFormatterSettings.value;
                        break;
                    } else {
                        break;
                    }
                case 15:
                    if (macroProps.perUnit == null) {
                        macroProps.perUnit = (MeasureUnit) numberFormatterSettings.value;
                        break;
                    } else {
                        break;
                    }
                default:
                    throw new AssertionError((Object) ("Unknown key: " + numberFormatterSettings.key));
            }
        }
        this.resolvedMacros = macroProps;
        return macroProps;
    }

    public int hashCode() {
        return resolve().hashCode();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && (obj instanceof NumberFormatterSettings)) {
            return resolve().equals(((NumberFormatterSettings) obj).resolve());
        }
        return false;
    }
}
