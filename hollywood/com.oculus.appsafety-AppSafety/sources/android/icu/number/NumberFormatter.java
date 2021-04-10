package android.icu.number;

import android.icu.impl.number.DecimalFormatProperties;
import android.icu.text.DecimalFormatSymbols;
import android.icu.util.ULocale;
import java.util.Locale;

public final class NumberFormatter {
    private static final UnlocalizedNumberFormatter BASE = new UnlocalizedNumberFormatter();
    static final long DEFAULT_THRESHOLD = 3;

    public enum DecimalSeparatorDisplay {
        AUTO,
        ALWAYS
    }

    public enum GroupingStrategy {
        OFF,
        MIN2,
        AUTO,
        ON_ALIGNED,
        THOUSANDS
    }

    public enum SignDisplay {
        AUTO,
        ALWAYS,
        NEVER,
        ACCOUNTING,
        ACCOUNTING_ALWAYS,
        EXCEPT_ZERO,
        ACCOUNTING_EXCEPT_ZERO
    }

    public enum UnitWidth {
        NARROW,
        SHORT,
        FULL_NAME,
        ISO_CODE,
        HIDDEN
    }

    private NumberFormatter() {
    }

    public static UnlocalizedNumberFormatter with() {
        return BASE;
    }

    public static LocalizedNumberFormatter withLocale(Locale locale) {
        return BASE.locale(locale);
    }

    public static LocalizedNumberFormatter withLocale(ULocale locale) {
        return BASE.locale(locale);
    }

    public static UnlocalizedNumberFormatter forSkeleton(String skeleton) {
        return NumberSkeletonImpl.getOrCreate(skeleton);
    }

    @Deprecated
    public static UnlocalizedNumberFormatter fromDecimalFormat(DecimalFormatProperties properties, DecimalFormatSymbols symbols, DecimalFormatProperties exportedProperties) {
        return NumberPropertyMapper.create(properties, symbols, exportedProperties);
    }
}
