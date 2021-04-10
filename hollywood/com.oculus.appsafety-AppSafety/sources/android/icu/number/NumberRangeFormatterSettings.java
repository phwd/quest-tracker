package android.icu.number;

import android.icu.impl.number.range.RangeMacroProps;
import android.icu.number.NumberRangeFormatter;
import android.icu.number.NumberRangeFormatterSettings;
import android.icu.util.ULocale;

public abstract class NumberRangeFormatterSettings<T extends NumberRangeFormatterSettings<?>> {
    static final int KEY_COLLAPSE = 5;
    static final int KEY_FORMATTER_1 = 2;
    static final int KEY_FORMATTER_2 = 3;
    static final int KEY_IDENTITY_FALLBACK = 6;
    static final int KEY_LOCALE = 1;
    static final int KEY_MACROS = 0;
    static final int KEY_MAX = 7;
    static final int KEY_SAME_FORMATTERS = 4;
    private final int key;
    private final NumberRangeFormatterSettings<?> parent;
    private volatile RangeMacroProps resolvedMacros;
    private final Object value;

    /* access modifiers changed from: package-private */
    public abstract T create(int i, Object obj);

    NumberRangeFormatterSettings(NumberRangeFormatterSettings<?> parent2, int key2, Object value2) {
        this.parent = parent2;
        this.key = key2;
        this.value = value2;
    }

    public T numberFormatterBoth(UnlocalizedNumberFormatter formatter) {
        return (T) create(4, true).create(2, formatter);
    }

    public T numberFormatterFirst(UnlocalizedNumberFormatter formatterFirst) {
        return (T) create(4, false).create(2, formatterFirst);
    }

    public T numberFormatterSecond(UnlocalizedNumberFormatter formatterSecond) {
        return (T) create(4, false).create(3, formatterSecond);
    }

    public T collapse(NumberRangeFormatter.RangeCollapse collapse) {
        return create(5, collapse);
    }

    public T identityFallback(NumberRangeFormatter.RangeIdentityFallback identityFallback) {
        return create(6, identityFallback);
    }

    /* access modifiers changed from: package-private */
    public RangeMacroProps resolve() {
        if (this.resolvedMacros != null) {
            return this.resolvedMacros;
        }
        RangeMacroProps macros = new RangeMacroProps();
        for (NumberRangeFormatterSettings numberRangeFormatterSettings = this; numberRangeFormatterSettings != null; numberRangeFormatterSettings = numberRangeFormatterSettings.parent) {
            switch (numberRangeFormatterSettings.key) {
                case 0:
                    break;
                case 1:
                    if (macros.loc == null) {
                        macros.loc = (ULocale) numberRangeFormatterSettings.value;
                        break;
                    } else {
                        break;
                    }
                case 2:
                    if (macros.formatter1 == null) {
                        macros.formatter1 = (UnlocalizedNumberFormatter) numberRangeFormatterSettings.value;
                        break;
                    } else {
                        break;
                    }
                case 3:
                    if (macros.formatter2 == null) {
                        macros.formatter2 = (UnlocalizedNumberFormatter) numberRangeFormatterSettings.value;
                        break;
                    } else {
                        break;
                    }
                case 4:
                    if (macros.sameFormatters == -1) {
                        macros.sameFormatters = ((Boolean) numberRangeFormatterSettings.value).booleanValue() ? 1 : 0;
                        break;
                    } else {
                        break;
                    }
                case 5:
                    if (macros.collapse == null) {
                        macros.collapse = (NumberRangeFormatter.RangeCollapse) numberRangeFormatterSettings.value;
                        break;
                    } else {
                        break;
                    }
                case 6:
                    if (macros.identityFallback == null) {
                        macros.identityFallback = (NumberRangeFormatter.RangeIdentityFallback) numberRangeFormatterSettings.value;
                        break;
                    } else {
                        break;
                    }
                default:
                    throw new AssertionError((Object) ("Unknown key: " + numberRangeFormatterSettings.key));
            }
        }
        if (macros.formatter1 != null) {
            macros.formatter1.resolve().loc = macros.loc;
        }
        if (macros.formatter2 != null) {
            macros.formatter2.resolve().loc = macros.loc;
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
        if (other != null && (other instanceof NumberRangeFormatterSettings)) {
            return resolve().equals(((NumberRangeFormatterSettings) other).resolve());
        }
        return false;
    }
}
