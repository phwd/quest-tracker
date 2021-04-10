package android.icu.text;

import android.icu.impl.Assert;
import android.icu.impl.ICUBinary;
import android.icu.impl.ICUData;
import android.icu.impl.ICULocaleService;
import android.icu.impl.ICUResourceBundle;
import android.icu.impl.ICUService;
import android.icu.text.BreakIterator;
import android.icu.util.ULocale;
import java.io.IOException;
import java.text.StringCharacterIterator;
import java.util.Locale;
import java.util.MissingResourceException;

final class BreakIteratorFactory extends BreakIterator.BreakIteratorServiceShim {
    private static final String[] KIND_NAMES = {"grapheme", "word", "line", "sentence", "title"};
    static final ICULocaleService service = new BFService();

    BreakIteratorFactory() {
    }

    @Override // android.icu.text.BreakIterator.BreakIteratorServiceShim
    public Object registerInstance(BreakIterator iter, ULocale locale, int kind) {
        iter.setText(new StringCharacterIterator(""));
        return service.registerObject(iter, locale, kind);
    }

    @Override // android.icu.text.BreakIterator.BreakIteratorServiceShim
    public boolean unregister(Object key) {
        if (service.isDefault()) {
            return false;
        }
        return service.unregisterFactory((ICUService.Factory) key);
    }

    @Override // android.icu.text.BreakIterator.BreakIteratorServiceShim
    public Locale[] getAvailableLocales() {
        ICULocaleService iCULocaleService = service;
        if (iCULocaleService == null) {
            return ICUResourceBundle.getAvailableLocales();
        }
        return iCULocaleService.getAvailableLocales();
    }

    @Override // android.icu.text.BreakIterator.BreakIteratorServiceShim
    public ULocale[] getAvailableULocales() {
        ICULocaleService iCULocaleService = service;
        if (iCULocaleService == null) {
            return ICUResourceBundle.getAvailableULocales();
        }
        return iCULocaleService.getAvailableULocales();
    }

    @Override // android.icu.text.BreakIterator.BreakIteratorServiceShim
    public BreakIterator createBreakIterator(ULocale locale, int kind) {
        if (service.isDefault()) {
            return createBreakInstance(locale, kind);
        }
        ULocale[] actualLoc = new ULocale[1];
        BreakIterator iter = (BreakIterator) service.get(locale, kind, actualLoc);
        iter.setLocale(actualLoc[0], actualLoc[0]);
        return iter;
    }

    private static class BFService extends ICULocaleService {
        BFService() {
            super("BreakIterator");
            registerFactory(new ICULocaleService.ICUResourceBundleFactory() {
                /* class android.icu.text.BreakIteratorFactory.BFService.AnonymousClass1RBBreakIteratorFactory */

                /* access modifiers changed from: protected */
                @Override // android.icu.impl.ICULocaleService.LocaleKeyFactory, android.icu.impl.ICULocaleService.ICUResourceBundleFactory
                public Object handleCreate(ULocale loc, int kind, ICUService srvc) {
                    return BreakIteratorFactory.createBreakInstance(loc, kind);
                }
            });
            markDefault();
        }

        @Override // android.icu.impl.ICULocaleService
        public String validateFallbackLocale() {
            return "";
        }
    }

    /* access modifiers changed from: private */
    public static BreakIterator createBreakInstance(ULocale locale, int kind) {
        String typeKey;
        String ssKeyword;
        String lbKeyValue;
        RuleBasedBreakIterator iter = null;
        ICUResourceBundle rb = ICUResourceBundle.getBundleInstance(ICUData.ICU_BRKITR_BASE_NAME, locale, ICUResourceBundle.OpenType.LOCALE_ROOT);
        String typeKeyExt = null;
        if (kind == 2 && (lbKeyValue = locale.getKeywordValue("lb")) != null && (lbKeyValue.equals("strict") || lbKeyValue.equals("normal") || lbKeyValue.equals("loose"))) {
            typeKeyExt = "_" + lbKeyValue;
        }
        if (typeKeyExt == null) {
            try {
                typeKey = KIND_NAMES[kind];
            } catch (Exception e) {
                throw new MissingResourceException(e.toString(), "", "");
            }
        } else {
            typeKey = KIND_NAMES[kind] + typeKeyExt;
        }
        try {
            iter = RuleBasedBreakIterator.getInstanceFromCompiledRules(ICUBinary.getData("brkitr/" + rb.getStringWithFallback("boundaries/" + typeKey)));
        } catch (IOException e2) {
            Assert.fail(e2);
        }
        ULocale uloc = ULocale.forLocale(rb.getLocale());
        iter.setLocale(uloc, uloc);
        if (kind != 3 || (ssKeyword = locale.getKeywordValue("ss")) == null || !ssKeyword.equals("standard")) {
            return iter;
        }
        return FilteredBreakIteratorBuilder.getInstance(new ULocale(locale.getBaseName())).wrapIteratorWithFilter(iter);
    }
}
