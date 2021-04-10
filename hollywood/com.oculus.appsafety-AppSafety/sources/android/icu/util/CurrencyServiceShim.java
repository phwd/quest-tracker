package android.icu.util;

import android.icu.impl.ICULocaleService;
import android.icu.impl.ICUResourceBundle;
import android.icu.impl.ICUService;
import android.icu.util.Currency;
import java.util.Locale;

final class CurrencyServiceShim extends Currency.ServiceShim {
    static final ICULocaleService service = new CFService();

    CurrencyServiceShim() {
    }

    /* access modifiers changed from: package-private */
    @Override // android.icu.util.Currency.ServiceShim
    public Locale[] getAvailableLocales() {
        if (service.isDefault()) {
            return ICUResourceBundle.getAvailableLocales();
        }
        return service.getAvailableLocales();
    }

    /* access modifiers changed from: package-private */
    @Override // android.icu.util.Currency.ServiceShim
    public ULocale[] getAvailableULocales() {
        if (service.isDefault()) {
            return ICUResourceBundle.getAvailableULocales();
        }
        return service.getAvailableULocales();
    }

    /* access modifiers changed from: package-private */
    @Override // android.icu.util.Currency.ServiceShim
    public Currency createInstance(ULocale loc) {
        if (service.isDefault()) {
            return Currency.createCurrency(loc);
        }
        return (Currency) service.get(loc);
    }

    /* access modifiers changed from: package-private */
    @Override // android.icu.util.Currency.ServiceShim
    public Object registerInstance(Currency currency, ULocale locale) {
        return service.registerObject(currency, locale);
    }

    /* access modifiers changed from: package-private */
    @Override // android.icu.util.Currency.ServiceShim
    public boolean unregister(Object registryKey) {
        return service.unregisterFactory((ICUService.Factory) registryKey);
    }

    private static class CFService extends ICULocaleService {
        CFService() {
            super("Currency");
            registerFactory(new ICULocaleService.ICUResourceBundleFactory() {
                /* class android.icu.util.CurrencyServiceShim.CFService.AnonymousClass1CurrencyFactory */

                /* access modifiers changed from: protected */
                @Override // android.icu.impl.ICULocaleService.LocaleKeyFactory, android.icu.impl.ICULocaleService.ICUResourceBundleFactory
                public Object handleCreate(ULocale loc, int kind, ICUService srvc) {
                    return Currency.createCurrency(loc);
                }
            });
            markDefault();
        }
    }
}
