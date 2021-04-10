package android.icu.text;

import android.icu.impl.Assert;
import android.icu.impl.ICUBinary;
import android.icu.impl.ICULocaleService;
import android.icu.impl.ICUResourceBundle;
import android.icu.impl.ICUService;
import android.icu.text.BreakIterator;
import android.icu.util.ULocale;
import java.io.IOException;
import java.util.MissingResourceException;

final class BreakIteratorFactory extends BreakIterator.BreakIteratorServiceShim {
    private static final String[] KIND_NAMES = {"grapheme", "word", "line", "sentence", "title"};
    static final ICULocaleService service = new BFService();

    BreakIteratorFactory() {
    }

    @Override // android.icu.text.BreakIterator.BreakIteratorServiceShim
    public BreakIterator createBreakIterator(ULocale uLocale, int i) {
        if (service.isDefault()) {
            return createBreakInstance(uLocale, i);
        }
        ULocale[] uLocaleArr = new ULocale[1];
        BreakIterator breakIterator = (BreakIterator) service.get(uLocale, i, uLocaleArr);
        breakIterator.setLocale(uLocaleArr[0], uLocaleArr[0]);
        return breakIterator;
    }

    private static class BFService extends ICULocaleService {
        @Override // android.icu.impl.ICULocaleService
        public String validateFallbackLocale() {
            return "";
        }

        BFService() {
            super("BreakIterator");
            registerFactory(new ICULocaleService.ICUResourceBundleFactory() {
                /* class android.icu.text.BreakIteratorFactory.BFService.AnonymousClass1RBBreakIteratorFactory */

                /* access modifiers changed from: protected */
                @Override // android.icu.impl.ICULocaleService.LocaleKeyFactory
                public Object handleCreate(ULocale uLocale, int i, ICUService iCUService) {
                    return BreakIteratorFactory.createBreakInstance(uLocale, i);
                }
            });
            markDefault();
        }
    }

    /* access modifiers changed from: private */
    public static BreakIterator createBreakInstance(ULocale uLocale, int i) {
        String str;
        String str2;
        String keywordValue;
        String keywordValue2;
        ICUResourceBundle bundleInstance = ICUResourceBundle.getBundleInstance("android/icu/impl/data/icudt63b/brkitr", uLocale, ICUResourceBundle.OpenType.LOCALE_ROOT);
        if (i != 2 || (keywordValue2 = uLocale.getKeywordValue("lb")) == null || (!keywordValue2.equals("strict") && !keywordValue2.equals("normal") && !keywordValue2.equals("loose"))) {
            str = null;
        } else {
            str = "_" + keywordValue2;
        }
        if (str == null) {
            try {
                str2 = KIND_NAMES[i];
            } catch (Exception e) {
                throw new MissingResourceException(e.toString(), "", "");
            }
        } else {
            str2 = KIND_NAMES[i] + str;
        }
        try {
            RuleBasedBreakIterator instanceFromCompiledRules = RuleBasedBreakIterator.getInstanceFromCompiledRules(ICUBinary.getData("brkitr/" + bundleInstance.getStringWithFallback("boundaries/" + str2)));
            ULocale forLocale = ULocale.forLocale(bundleInstance.getLocale());
            instanceFromCompiledRules.setLocale(forLocale, forLocale);
            return (i != 3 || (keywordValue = uLocale.getKeywordValue("ss")) == null || !keywordValue.equals("standard")) ? instanceFromCompiledRules : FilteredBreakIteratorBuilder.getInstance(new ULocale(uLocale.getBaseName())).wrapIteratorWithFilter(instanceFromCompiledRules);
        } catch (IOException e2) {
            Assert.fail(e2);
            throw null;
        }
    }
}
