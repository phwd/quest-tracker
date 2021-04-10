package android.icu.number;

import android.icu.util.ULocale;
import java.util.Locale;

public abstract class NumberRangeFormatter {
    private static final UnlocalizedNumberRangeFormatter BASE = new UnlocalizedNumberRangeFormatter();

    public enum RangeCollapse {
        AUTO,
        NONE,
        UNIT,
        ALL
    }

    public enum RangeIdentityFallback {
        SINGLE_VALUE,
        APPROXIMATELY_OR_SINGLE_VALUE,
        APPROXIMATELY,
        RANGE
    }

    public enum RangeIdentityResult {
        EQUAL_BEFORE_ROUNDING,
        EQUAL_AFTER_ROUNDING,
        NOT_EQUAL
    }

    public static UnlocalizedNumberRangeFormatter with() {
        return BASE;
    }

    public static LocalizedNumberRangeFormatter withLocale(Locale locale) {
        return BASE.locale(locale);
    }

    public static LocalizedNumberRangeFormatter withLocale(ULocale locale) {
        return BASE.locale(locale);
    }

    private NumberRangeFormatter() {
    }
}
