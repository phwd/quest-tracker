package android.icu.text;

import android.icu.impl.ICULocaleService;
import android.icu.impl.ICUService;
import android.icu.text.NumberFormat;
import android.icu.util.Currency;
import android.icu.util.ULocale;
import java.util.MissingResourceException;

class NumberFormatServiceShim extends NumberFormat.NumberFormatShim {
    private static ICULocaleService service = new NFService();

    NumberFormatServiceShim() {
    }

    /* access modifiers changed from: package-private */
    @Override // android.icu.text.NumberFormat.NumberFormatShim
    public NumberFormat createInstance(ULocale uLocale, int i) {
        ULocale[] uLocaleArr = new ULocale[1];
        NumberFormat numberFormat = (NumberFormat) service.get(uLocale, i, uLocaleArr);
        if (numberFormat != null) {
            NumberFormat numberFormat2 = (NumberFormat) numberFormat.clone();
            if (i == 1 || i == 5 || i == 6) {
                numberFormat2.setCurrency(Currency.getInstance(uLocale));
            }
            ULocale uLocale2 = uLocaleArr[0];
            numberFormat2.setLocale(uLocale2, uLocale2);
            return numberFormat2;
        }
        throw new MissingResourceException("Unable to construct NumberFormat", "", "");
    }

    private static class NFService extends ICULocaleService {
        NFService() {
            super("NumberFormat");
            registerFactory(new ICULocaleService.ICUResourceBundleFactory() {
                /* class android.icu.text.NumberFormatServiceShim.NFService.AnonymousClass1RBNumberFormatFactory */

                /* access modifiers changed from: protected */
                @Override // android.icu.impl.ICULocaleService.LocaleKeyFactory
                public Object handleCreate(ULocale uLocale, int i, ICUService iCUService) {
                    return NumberFormat.createInstance(uLocale, i);
                }
            });
            markDefault();
        }
    }
}
