package android.icu.util;

import android.icu.impl.ICUData;
import android.icu.impl.ICUResourceBundle;
import android.icu.text.UnicodeSet;
import android.icu.util.ULocale;
import java.util.MissingResourceException;

public final class LocaleData {
    public static final int ALT_QUOTATION_END = 3;
    public static final int ALT_QUOTATION_START = 2;
    @Deprecated
    public static final int DELIMITER_COUNT = 4;
    private static final String[] DELIMITER_TYPES = {"quotationStart", "quotationEnd", "alternateQuotationStart", "alternateQuotationEnd"};
    public static final int ES_AUXILIARY = 1;
    @Deprecated
    public static final int ES_COUNT = 5;
    @Deprecated
    public static final int ES_CURRENCY = 3;
    public static final int ES_INDEX = 2;
    public static final int ES_PUNCTUATION = 4;
    public static final int ES_STANDARD = 0;
    private static final String LOCALE_DISPLAY_PATTERN = "localeDisplayPattern";
    private static final String MEASUREMENT_SYSTEM = "MeasurementSystem";
    private static final String PAPER_SIZE = "PaperSize";
    private static final String PATTERN = "pattern";
    public static final int QUOTATION_END = 1;
    public static final int QUOTATION_START = 0;
    private static final String SEPARATOR = "separator";
    private static VersionInfo gCLDRVersion = null;
    private ICUResourceBundle bundle;
    private ICUResourceBundle langBundle;
    private boolean noSubstitute;

    private LocaleData() {
    }

    public static UnicodeSet getExemplarSet(ULocale locale, int options) {
        return getInstance(locale).getExemplarSet(options, 0);
    }

    public static UnicodeSet getExemplarSet(ULocale locale, int options, int extype) {
        return getInstance(locale).getExemplarSet(options, extype);
    }

    public UnicodeSet getExemplarSet(int options, int extype) {
        String[] exemplarSetTypes = {"ExemplarCharacters", "AuxExemplarCharacters", "ExemplarCharactersIndex", "ExemplarCharactersCurrency", "ExemplarCharactersPunctuation"};
        if (extype != 3) {
            try {
                ICUResourceBundle stringBundle = (ICUResourceBundle) this.bundle.get(exemplarSetTypes[extype]);
                if (!this.noSubstitute || this.bundle.isRoot() || !stringBundle.isRoot()) {
                    return new UnicodeSet(stringBundle.getString(), options | 1);
                }
                return null;
            } catch (ArrayIndexOutOfBoundsException aiooe) {
                throw new IllegalArgumentException(aiooe);
            } catch (Exception e) {
                if (this.noSubstitute) {
                    return null;
                }
                return UnicodeSet.EMPTY;
            }
        } else if (this.noSubstitute) {
            return null;
        } else {
            return UnicodeSet.EMPTY;
        }
    }

    public static final LocaleData getInstance(ULocale locale) {
        LocaleData ld = new LocaleData();
        ld.bundle = (ICUResourceBundle) UResourceBundle.getBundleInstance(ICUData.ICU_BASE_NAME, locale);
        ld.langBundle = (ICUResourceBundle) UResourceBundle.getBundleInstance(ICUData.ICU_LANG_BASE_NAME, locale);
        ld.noSubstitute = false;
        return ld;
    }

    public static final LocaleData getInstance() {
        return getInstance(ULocale.getDefault(ULocale.Category.FORMAT));
    }

    public void setNoSubstitute(boolean setting) {
        this.noSubstitute = setting;
    }

    public boolean getNoSubstitute() {
        return this.noSubstitute;
    }

    public String getDelimiter(int type) {
        ICUResourceBundle stringBundle = ((ICUResourceBundle) this.bundle.get("delimiters")).getWithFallback(DELIMITER_TYPES[type]);
        if (!this.noSubstitute || this.bundle.isRoot() || !stringBundle.isRoot()) {
            return stringBundle.getString();
        }
        return null;
    }

    private static UResourceBundle measurementTypeBundleForLocale(ULocale locale, String measurementType) {
        String region = ULocale.getRegionForSupplementalData(locale, true);
        try {
            UResourceBundle measurementData = UResourceBundle.getBundleInstance(ICUData.ICU_BASE_NAME, "supplementalData", ICUResourceBundle.ICU_DATA_CLASS_LOADER).get("measurementData");
            try {
                return measurementData.get(region).get(measurementType);
            } catch (MissingResourceException e) {
                return measurementData.get("001").get(measurementType);
            }
        } catch (MissingResourceException e2) {
            return null;
        }
    }

    public static final class MeasurementSystem {
        public static final MeasurementSystem SI = new MeasurementSystem();
        public static final MeasurementSystem UK = new MeasurementSystem();
        public static final MeasurementSystem US = new MeasurementSystem();

        private MeasurementSystem() {
        }
    }

    public static final MeasurementSystem getMeasurementSystem(ULocale locale) {
        int i = measurementTypeBundleForLocale(locale, MEASUREMENT_SYSTEM).getInt();
        if (i == 0) {
            return MeasurementSystem.SI;
        }
        if (i == 1) {
            return MeasurementSystem.US;
        }
        if (i != 2) {
            return null;
        }
        return MeasurementSystem.UK;
    }

    public static final class PaperSize {
        private int height;
        private int width;

        private PaperSize(int h, int w) {
            this.height = h;
            this.width = w;
        }

        public int getHeight() {
            return this.height;
        }

        public int getWidth() {
            return this.width;
        }
    }

    public static final PaperSize getPaperSize(ULocale locale) {
        int[] size = measurementTypeBundleForLocale(locale, PAPER_SIZE).getIntVector();
        return new PaperSize(size[0], size[1]);
    }

    public String getLocaleDisplayPattern() {
        return ((ICUResourceBundle) this.langBundle.get(LOCALE_DISPLAY_PATTERN)).getStringWithFallback(PATTERN);
    }

    public String getLocaleSeparator() {
        String localeSeparator = ((ICUResourceBundle) this.langBundle.get(LOCALE_DISPLAY_PATTERN)).getStringWithFallback(SEPARATOR);
        int index0 = localeSeparator.indexOf("{0}");
        int index1 = localeSeparator.indexOf("{1}");
        if (index0 < 0 || index1 < 0 || index0 > index1) {
            return localeSeparator;
        }
        return localeSeparator.substring("{0}".length() + index0, index1);
    }

    public static VersionInfo getCLDRVersion() {
        if (gCLDRVersion == null) {
            gCLDRVersion = VersionInfo.getInstance(UResourceBundle.getBundleInstance(ICUData.ICU_BASE_NAME, "supplementalData", ICUResourceBundle.ICU_DATA_CLASS_LOADER).get("cldrVersion").getString());
        }
        return gCLDRVersion;
    }
}
