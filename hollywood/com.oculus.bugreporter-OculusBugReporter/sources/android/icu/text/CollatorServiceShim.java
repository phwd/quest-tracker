package android.icu.text;

import android.icu.impl.ICUData;
import android.icu.impl.ICULocaleService;
import android.icu.impl.ICUResourceBundle;
import android.icu.impl.ICUService;
import android.icu.impl.coll.CollationLoader;
import android.icu.text.Collator;
import android.icu.util.ICUCloneNotSupportedException;
import android.icu.util.Output;
import android.icu.util.ULocale;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.Set;

final class CollatorServiceShim extends Collator.ServiceShim {
    private static ICULocaleService service = new CService();

    CollatorServiceShim() {
    }

    /* access modifiers changed from: package-private */
    @Override // android.icu.text.Collator.ServiceShim
    public Collator getInstance(ULocale locale) {
        try {
            Collator coll = (Collator) service.get(locale, new ULocale[1]);
            if (coll != null) {
                return (Collator) coll.clone();
            }
            throw new MissingResourceException("Could not locate Collator data", "", "");
        } catch (CloneNotSupportedException e) {
            throw new ICUCloneNotSupportedException(e);
        }
    }

    /* access modifiers changed from: package-private */
    @Override // android.icu.text.Collator.ServiceShim
    public Object registerInstance(Collator collator, ULocale locale) {
        collator.setLocale(locale, locale);
        return service.registerObject(collator, locale);
    }

    /* access modifiers changed from: package-private */
    @Override // android.icu.text.Collator.ServiceShim
    public Object registerFactory(Collator.CollatorFactory f) {
        return service.registerFactory(new ICULocaleService.LocaleKeyFactory(f) {
            /* class android.icu.text.CollatorServiceShim.AnonymousClass1CFactory */
            Collator.CollatorFactory delegate;

            {
                this.delegate = fctry;
            }

            @Override // android.icu.impl.ICULocaleService.LocaleKeyFactory
            public Object handleCreate(ULocale loc, int kind, ICUService srvc) {
                return this.delegate.createCollator(loc);
            }

            @Override // android.icu.impl.ICUService.Factory, android.icu.impl.ICULocaleService.LocaleKeyFactory
            public String getDisplayName(String id, ULocale displayLocale) {
                return this.delegate.getDisplayName(new ULocale(id), displayLocale);
            }

            @Override // android.icu.impl.ICULocaleService.LocaleKeyFactory
            public Set<String> getSupportedIDs() {
                return this.delegate.getSupportedLocaleIDs();
            }
        });
    }

    /* access modifiers changed from: package-private */
    @Override // android.icu.text.Collator.ServiceShim
    public boolean unregister(Object registryKey) {
        return service.unregisterFactory((ICUService.Factory) registryKey);
    }

    /* access modifiers changed from: package-private */
    @Override // android.icu.text.Collator.ServiceShim
    public Locale[] getAvailableLocales() {
        if (service.isDefault()) {
            return ICUResourceBundle.getAvailableLocales(ICUData.ICU_COLLATION_BASE_NAME, ICUResourceBundle.ICU_DATA_CLASS_LOADER);
        }
        return service.getAvailableLocales();
    }

    /* access modifiers changed from: package-private */
    @Override // android.icu.text.Collator.ServiceShim
    public ULocale[] getAvailableULocales() {
        if (service.isDefault()) {
            return ICUResourceBundle.getAvailableULocales(ICUData.ICU_COLLATION_BASE_NAME, ICUResourceBundle.ICU_DATA_CLASS_LOADER);
        }
        return service.getAvailableULocales();
    }

    /* access modifiers changed from: package-private */
    @Override // android.icu.text.Collator.ServiceShim
    public String getDisplayName(ULocale objectLocale, ULocale displayLocale) {
        return service.getDisplayName(objectLocale.getName(), displayLocale);
    }

    private static class CService extends ICULocaleService {
        CService() {
            super("Collator");
            registerFactory(new ICULocaleService.ICUResourceBundleFactory() {
                /* class android.icu.text.CollatorServiceShim.CService.AnonymousClass1CollatorFactory */

                /* access modifiers changed from: protected */
                @Override // android.icu.impl.ICULocaleService.LocaleKeyFactory, android.icu.impl.ICULocaleService.ICUResourceBundleFactory
                public Object handleCreate(ULocale uloc, int kind, ICUService srvc) {
                    return CollatorServiceShim.makeInstance(uloc);
                }
            });
            markDefault();
        }

        @Override // android.icu.impl.ICULocaleService
        public String validateFallbackLocale() {
            return "";
        }

        /* access modifiers changed from: protected */
        @Override // android.icu.impl.ICUService
        public Object handleDefault(ICUService.Key key, String[] actualIDReturn) {
            if (actualIDReturn != null) {
                actualIDReturn[0] = "root";
            }
            try {
                return CollatorServiceShim.makeInstance(ULocale.ROOT);
            } catch (MissingResourceException e) {
                return null;
            }
        }
    }

    /* access modifiers changed from: private */
    public static final Collator makeInstance(ULocale desiredLocale) {
        Output<ULocale> validLocale = new Output<>(ULocale.ROOT);
        return new RuleBasedCollator(CollationLoader.loadTailoring(desiredLocale, validLocale), validLocale.value);
    }
}
