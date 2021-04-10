package android.icu.number;

import android.icu.impl.number.DecimalFormatProperties;
import android.icu.text.DecimalFormatSymbols;

public final class NumberFormatter {
    private static final UnlocalizedNumberFormatter BASE = new UnlocalizedNumberFormatter();

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

    public static UnlocalizedNumberFormatter with() {
        return BASE;
    }

    public static UnlocalizedNumberFormatter fromDecimalFormat(DecimalFormatProperties decimalFormatProperties, DecimalFormatSymbols decimalFormatSymbols, DecimalFormatProperties decimalFormatProperties2) {
        return NumberPropertyMapper.create(decimalFormatProperties, decimalFormatSymbols, decimalFormatProperties2);
    }
}
