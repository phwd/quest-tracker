package android.icu.number;

import android.icu.util.ULocale;

public class UnlocalizedNumberFormatter extends NumberFormatterSettings {
    UnlocalizedNumberFormatter() {
        super(null, 14, new Long(3));
    }

    UnlocalizedNumberFormatter(NumberFormatterSettings numberFormatterSettings, int i, Object obj) {
        super(numberFormatterSettings, i, obj);
    }

    public LocalizedNumberFormatter locale(ULocale uLocale) {
        return new LocalizedNumberFormatter(this, 1, uLocale);
    }

    /* access modifiers changed from: package-private */
    @Override // android.icu.number.NumberFormatterSettings
    public UnlocalizedNumberFormatter create(int i, Object obj) {
        return new UnlocalizedNumberFormatter(this, i, obj);
    }
}
