package android.icu.text;

import android.icu.impl.ICULocaleService;
import android.icu.impl.ICUService;
import android.icu.impl.coll.CollationLoader;
import android.icu.text.Collator;
import android.icu.util.ICUCloneNotSupportedException;
import android.icu.util.Output;
import android.icu.util.ULocale;
import java.util.MissingResourceException;

final class CollatorServiceShim extends Collator.ServiceShim {
    private static ICULocaleService service = new CService();

    CollatorServiceShim() {
    }

    /* access modifiers changed from: package-private */
    @Override // android.icu.text.Collator.ServiceShim
    public Collator getInstance(ULocale uLocale) {
        try {
            Collator collator = (Collator) service.get(uLocale, new ULocale[1]);
            if (collator != null) {
                return (Collator) collator.clone();
            }
            throw new MissingResourceException("Could not locate Collator data", "", "");
        } catch (CloneNotSupportedException e) {
            throw new ICUCloneNotSupportedException(e);
        }
    }

    private static class CService extends ICULocaleService {
        @Override // android.icu.impl.ICULocaleService
        public String validateFallbackLocale() {
            return "";
        }

        CService() {
            super("Collator");
            registerFactory(new ICULocaleService.ICUResourceBundleFactory() {
                /* class android.icu.text.CollatorServiceShim.CService.AnonymousClass1CollatorFactory */

                /* access modifiers changed from: protected */
                @Override // android.icu.impl.ICULocaleService.LocaleKeyFactory
                public Object handleCreate(ULocale uLocale, int i, ICUService iCUService) {
                    return CollatorServiceShim.makeInstance(uLocale);
                }
            });
            markDefault();
        }

        /* access modifiers changed from: protected */
        @Override // android.icu.impl.ICUService
        public Object handleDefault(ICUService.Key key, String[] strArr) {
            if (strArr != null) {
                strArr[0] = "root";
            }
            try {
                return CollatorServiceShim.makeInstance(ULocale.ROOT);
            } catch (MissingResourceException unused) {
                return null;
            }
        }
    }

    /* access modifiers changed from: private */
    public static final Collator makeInstance(ULocale uLocale) {
        Output output = new Output(ULocale.ROOT);
        return new RuleBasedCollator(CollationLoader.loadTailoring(uLocale, output), (ULocale) output.value);
    }
}
