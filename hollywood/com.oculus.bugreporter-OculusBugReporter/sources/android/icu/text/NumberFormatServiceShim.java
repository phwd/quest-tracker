package android.icu.text;

import android.icu.impl.ICULocaleService;
import android.icu.impl.ICUResourceBundle;
import android.icu.impl.ICUService;
import android.icu.text.NumberFormat;
import android.icu.util.Currency;
import android.icu.util.ULocale;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.Set;

class NumberFormatServiceShim extends NumberFormat.NumberFormatShim {
    private static ICULocaleService service = new NFService();

    NumberFormatServiceShim() {
    }

    /* access modifiers changed from: package-private */
    @Override // android.icu.text.NumberFormat.NumberFormatShim
    public Locale[] getAvailableLocales() {
        if (service.isDefault()) {
            return ICUResourceBundle.getAvailableLocales();
        }
        return service.getAvailableLocales();
    }

    /* access modifiers changed from: package-private */
    @Override // android.icu.text.NumberFormat.NumberFormatShim
    public ULocale[] getAvailableULocales() {
        if (service.isDefault()) {
            return ICUResourceBundle.getAvailableULocales();
        }
        return service.getAvailableULocales();
    }

    private static final class NFFactory extends ICULocaleService.LocaleKeyFactory {
        private NumberFormat.NumberFormatFactory delegate;

        NFFactory(NumberFormat.NumberFormatFactory delegate2) {
            super(delegate2.visible());
            this.delegate = delegate2;
        }

        @Override // android.icu.impl.ICUService.Factory, android.icu.impl.ICULocaleService.LocaleKeyFactory
        public Object create(ICUService.Key key, ICUService srvc) {
            if (!handlesKey(key) || !(key instanceof ICULocaleService.LocaleKey)) {
                return null;
            }
            ICULocaleService.LocaleKey lkey = (ICULocaleService.LocaleKey) key;
            Object result = this.delegate.createFormat(lkey.canonicalLocale(), lkey.kind());
            if (result == null) {
                return srvc.getKey(key, null, this);
            }
            return result;
        }

        /* access modifiers changed from: protected */
        @Override // android.icu.impl.ICULocaleService.LocaleKeyFactory
        public Set<String> getSupportedIDs() {
            return this.delegate.getSupportedLocaleNames();
        }
    }

    /* access modifiers changed from: package-private */
    @Override // android.icu.text.NumberFormat.NumberFormatShim
    public Object registerFactory(NumberFormat.NumberFormatFactory factory) {
        return service.registerFactory(new NFFactory(factory));
    }

    /* access modifiers changed from: package-private */
    @Override // android.icu.text.NumberFormat.NumberFormatShim
    public boolean unregister(Object registryKey) {
        return service.unregisterFactory((ICUService.Factory) registryKey);
    }

    /* access modifiers changed from: package-private */
    @Override // android.icu.text.NumberFormat.NumberFormatShim
    public NumberFormat createInstance(ULocale desiredLocale, int choice) {
        ULocale[] actualLoc = new ULocale[1];
        NumberFormat fmt = (NumberFormat) service.get(desiredLocale, choice, actualLoc);
        if (fmt != null) {
            NumberFormat fmt2 = (NumberFormat) fmt.clone();
            if (choice == 1 || choice == 5 || choice == 6) {
                fmt2.setCurrency(Currency.getInstance(desiredLocale));
            }
            ULocale uloc = actualLoc[0];
            fmt2.setLocale(uloc, uloc);
            return fmt2;
        }
        throw new MissingResourceException("Unable to construct NumberFormat", "", "");
    }

    private static class NFService extends ICULocaleService {
        NFService() {
            super("NumberFormat");
            registerFactory(new ICULocaleService.ICUResourceBundleFactory() {
                /* class android.icu.text.NumberFormatServiceShim.NFService.AnonymousClass1RBNumberFormatFactory */

                /* access modifiers changed from: protected */
                @Override // android.icu.impl.ICULocaleService.LocaleKeyFactory, android.icu.impl.ICULocaleService.ICUResourceBundleFactory
                public Object handleCreate(ULocale loc, int kind, ICUService srvc) {
                    return NumberFormat.createInstance(loc, kind);
                }
            });
            markDefault();
        }
    }
}
