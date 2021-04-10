package android.icu.text;

import android.icu.impl.CurrencyData;
import android.icu.util.ULocale;
import java.util.Map;

public abstract class CurrencyDisplayNames {
    public abstract String getName(String str);

    public abstract String getNarrowSymbol(String str);

    public abstract String getPluralName(String str, String str2);

    public abstract String getSymbol(String str);

    public abstract Map nameMap();

    public abstract Map symbolMap();

    public static CurrencyDisplayNames getInstance(ULocale uLocale) {
        return CurrencyData.provider.getInstance(uLocale, true);
    }

    protected CurrencyDisplayNames() {
    }
}
