package android.icu.text;

import android.icu.impl.ICUData;
import android.icu.impl.ICUResourceBundle;
import android.icu.impl.UResource;
import android.icu.number.LocalizedNumberFormatter;
import android.icu.text.MeasureFormat;
import android.icu.util.TimeUnit;
import android.icu.util.TimeUnitAmount;
import android.icu.util.ULocale;
import android.icu.util.UResourceBundle;
import java.io.ObjectStreamException;
import java.text.ParseException;
import java.text.ParsePosition;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.Set;
import java.util.TreeMap;

@Deprecated
public class TimeUnitFormat extends MeasureFormat {
    @Deprecated
    public static final int ABBREVIATED_NAME = 1;
    private static final String DEFAULT_PATTERN_FOR_DAY = "{0} d";
    private static final String DEFAULT_PATTERN_FOR_HOUR = "{0} h";
    private static final String DEFAULT_PATTERN_FOR_MINUTE = "{0} min";
    private static final String DEFAULT_PATTERN_FOR_MONTH = "{0} m";
    private static final String DEFAULT_PATTERN_FOR_SECOND = "{0} s";
    private static final String DEFAULT_PATTERN_FOR_WEEK = "{0} w";
    private static final String DEFAULT_PATTERN_FOR_YEAR = "{0} y";
    @Deprecated
    public static final int FULL_NAME = 0;
    private static final int TOTAL_STYLES = 2;
    private static final long serialVersionUID = -3707773153184971529L;
    private NumberFormat format;
    private transient boolean isReady;
    private ULocale locale;
    private transient PluralRules pluralRules;
    private int style;
    private transient Map<TimeUnit, Map<String, Object[]>> timeUnitToCountToPatterns;

    @Deprecated
    public TimeUnitFormat() {
        this(ULocale.getDefault(), 0);
    }

    @Deprecated
    public TimeUnitFormat(ULocale locale2) {
        this(locale2, 0);
    }

    @Deprecated
    public TimeUnitFormat(Locale locale2) {
        this(locale2, 0);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    @Deprecated
    public TimeUnitFormat(ULocale locale2, int style2) {
        super(locale2, style2 == 0 ? MeasureFormat.FormatWidth.WIDE : MeasureFormat.FormatWidth.SHORT);
        this.format = super.getNumberFormatInternal();
        if (style2 < 0 || style2 >= 2) {
            throw new IllegalArgumentException("style should be either FULL_NAME or ABBREVIATED_NAME style");
        }
        this.style = style2;
        this.isReady = false;
    }

    private TimeUnitFormat(ULocale locale2, int style2, NumberFormat numberFormat) {
        this(locale2, style2);
        if (numberFormat != null) {
            setNumberFormat((NumberFormat) numberFormat.clone());
        }
    }

    @Deprecated
    public TimeUnitFormat(Locale locale2, int style2) {
        this(ULocale.forLocale(locale2), style2);
    }

    @Deprecated
    public TimeUnitFormat setLocale(ULocale locale2) {
        setLocale(locale2, locale2);
        clearCache();
        return this;
    }

    @Deprecated
    public TimeUnitFormat setLocale(Locale locale2) {
        return setLocale(ULocale.forLocale(locale2));
    }

    @Deprecated
    public TimeUnitFormat setNumberFormat(NumberFormat format2) {
        if (format2 == this.format) {
            return this;
        }
        if (format2 == null) {
            ULocale uLocale = this.locale;
            if (uLocale == null) {
                this.isReady = false;
            } else {
                this.format = NumberFormat.getNumberInstance(uLocale);
            }
        } else {
            this.format = format2;
        }
        clearCache();
        return this;
    }

    @Override // android.icu.text.MeasureFormat
    @Deprecated
    public NumberFormat getNumberFormat() {
        return (NumberFormat) this.format.clone();
    }

    /* access modifiers changed from: package-private */
    @Override // android.icu.text.MeasureFormat
    public NumberFormat getNumberFormatInternal() {
        return this.format;
    }

    /* access modifiers changed from: package-private */
    @Override // android.icu.text.MeasureFormat
    public LocalizedNumberFormatter getNumberFormatter() {
        return ((DecimalFormat) this.format).toNumberFormatter();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.icu.text.MeasureFormat, android.icu.text.MeasureFormat, java.text.Format
    @Deprecated
    public TimeUnitAmount parseObject(String source, ParsePosition pos) {
        TimeUnitFormat timeUnitFormat = this;
        if (!timeUnitFormat.isReady) {
            setup();
        }
        int resultNumber = null;
        TimeUnit resultTimeUnit = null;
        int oldPos = pos.getIndex();
        int newPos = -1;
        int longestParseDistance = 0;
        String countOfLongestMatch = null;
        Iterator<TimeUnit> it = timeUnitFormat.timeUnitToCountToPatterns.keySet().iterator();
        while (true) {
            int i = 2;
            int i2 = -1;
            if (!it.hasNext()) {
                break;
            }
            TimeUnit timeUnit = it.next();
            for (Map.Entry<String, Object[]> patternEntry : timeUnitFormat.timeUnitToCountToPatterns.get(timeUnit).entrySet()) {
                String count = patternEntry.getKey();
                int newPos2 = newPos;
                Number resultNumber2 = resultNumber;
                TimeUnit resultTimeUnit2 = resultTimeUnit;
                int styl = 0;
                while (styl < i) {
                    pos.setErrorIndex(i2);
                    pos.setIndex(oldPos);
                    Object parsed = ((MessageFormat) patternEntry.getValue()[styl]).parseObject(source, pos);
                    if (pos.getErrorIndex() == i2 && pos.getIndex() != oldPos) {
                        Number temp = null;
                        if (((Object[]) parsed).length != 0) {
                            Object tempObj = ((Object[]) parsed)[0];
                            if (tempObj instanceof Number) {
                                temp = (Number) tempObj;
                            } else {
                                try {
                                    temp = timeUnitFormat.format.parse(tempObj.toString());
                                } catch (ParseException e) {
                                }
                            }
                        }
                        int parseDistance = pos.getIndex() - oldPos;
                        if (parseDistance > longestParseDistance) {
                            longestParseDistance = parseDistance;
                            countOfLongestMatch = count;
                            resultTimeUnit2 = timeUnit;
                            newPos2 = pos.getIndex();
                            resultNumber2 = temp;
                        }
                    }
                    styl++;
                    i = 2;
                    i2 = -1;
                    timeUnitFormat = this;
                }
                resultNumber = resultNumber2;
                resultTimeUnit = resultTimeUnit2;
                newPos = newPos2;
                i = 2;
                i2 = -1;
                timeUnitFormat = this;
            }
            timeUnitFormat = this;
        }
        if (resultNumber == null && longestParseDistance != 0) {
            if (countOfLongestMatch.equals(PluralRules.KEYWORD_ZERO)) {
                resultNumber = 0;
            } else if (countOfLongestMatch.equals(PluralRules.KEYWORD_ONE)) {
                resultNumber = 1;
            } else if (countOfLongestMatch.equals(PluralRules.KEYWORD_TWO)) {
                resultNumber = 2;
            } else {
                resultNumber = 3;
            }
        }
        if (longestParseDistance == 0) {
            pos.setIndex(oldPos);
            pos.setErrorIndex(0);
            return null;
        }
        pos.setIndex(newPos);
        pos.setErrorIndex(-1);
        return new TimeUnitAmount(resultNumber, resultTimeUnit);
    }

    private void setup() {
        if (this.locale == null) {
            NumberFormat numberFormat = this.format;
            if (numberFormat != null) {
                this.locale = numberFormat.getLocale(null);
            } else {
                this.locale = ULocale.getDefault(ULocale.Category.FORMAT);
            }
            ULocale uLocale = this.locale;
            setLocale(uLocale, uLocale);
        }
        if (this.format == null) {
            this.format = NumberFormat.getNumberInstance(this.locale);
        }
        this.pluralRules = PluralRules.forLocale(this.locale);
        this.timeUnitToCountToPatterns = new HashMap();
        Set<String> pluralKeywords = this.pluralRules.getKeywords();
        setup("units/duration", this.timeUnitToCountToPatterns, 0, pluralKeywords);
        setup("unitsShort/duration", this.timeUnitToCountToPatterns, 1, pluralKeywords);
        this.isReady = true;
    }

    /* access modifiers changed from: private */
    public static final class TimeUnitFormatSetupSink extends UResource.Sink {
        boolean beenHere = false;
        ULocale locale;
        Set<String> pluralKeywords;
        int style;
        Map<TimeUnit, Map<String, Object[]>> timeUnitToCountToPatterns;

        TimeUnitFormatSetupSink(Map<TimeUnit, Map<String, Object[]>> timeUnitToCountToPatterns2, int style2, Set<String> pluralKeywords2, ULocale locale2) {
            this.timeUnitToCountToPatterns = timeUnitToCountToPatterns2;
            this.style = style2;
            this.pluralKeywords = pluralKeywords2;
            this.locale = locale2;
        }

        @Override // android.icu.impl.UResource.Sink
        public void put(UResource.Key key, UResource.Value value, boolean noFallback) {
            TimeUnit timeUnit;
            if (!this.beenHere) {
                this.beenHere = true;
                UResource.Table units = value.getTable();
                for (int i = 0; units.getKeyAndValue(i, key, value); i++) {
                    String timeUnitName = key.toString();
                    if (timeUnitName.equals("year")) {
                        timeUnit = TimeUnit.YEAR;
                    } else if (timeUnitName.equals("month")) {
                        timeUnit = TimeUnit.MONTH;
                    } else if (timeUnitName.equals("day")) {
                        timeUnit = TimeUnit.DAY;
                    } else if (timeUnitName.equals("hour")) {
                        timeUnit = TimeUnit.HOUR;
                    } else if (timeUnitName.equals("minute")) {
                        timeUnit = TimeUnit.MINUTE;
                    } else if (timeUnitName.equals("second")) {
                        timeUnit = TimeUnit.SECOND;
                    } else if (timeUnitName.equals("week")) {
                        timeUnit = TimeUnit.WEEK;
                    }
                    Map<String, Object[]> countToPatterns = this.timeUnitToCountToPatterns.get(timeUnit);
                    if (countToPatterns == null) {
                        countToPatterns = new TreeMap();
                        this.timeUnitToCountToPatterns.put(timeUnit, countToPatterns);
                    }
                    UResource.Table countsToPatternTable = value.getTable();
                    for (int j = 0; countsToPatternTable.getKeyAndValue(j, key, value); j++) {
                        String pluralCount = key.toString();
                        if (this.pluralKeywords.contains(pluralCount)) {
                            Object[] pair = countToPatterns.get(pluralCount);
                            if (pair == null) {
                                pair = new Object[2];
                                countToPatterns.put(pluralCount, pair);
                            }
                            if (pair[this.style] == null) {
                                pair[this.style] = new MessageFormat(value.getString(), this.locale);
                            }
                        }
                    }
                }
            }
        }
    }

    private void setup(String resourceKey, Map<TimeUnit, Map<String, Object[]>> timeUnitToCountToPatterns2, int style2, Set<String> pluralKeywords) {
        Map<String, Object[]> countToPatterns;
        Map<String, Object[]> countToPatterns2;
        try {
            try {
                try {
                    ((ICUResourceBundle) UResourceBundle.getBundleInstance(ICUData.ICU_UNIT_BASE_NAME, this.locale)).getAllItemsWithFallback(resourceKey, new TimeUnitFormatSetupSink(timeUnitToCountToPatterns2, style2, pluralKeywords, this.locale));
                } catch (MissingResourceException e) {
                }
            } catch (MissingResourceException e2) {
            }
        } catch (MissingResourceException e3) {
        }
        TimeUnit[] timeUnits = TimeUnit.values();
        Set<String> keywords = this.pluralRules.getKeywords();
        for (TimeUnit timeUnit : timeUnits) {
            Map<String, Object[]> countToPatterns3 = timeUnitToCountToPatterns2.get(timeUnit);
            if (countToPatterns3 == null) {
                Map<String, Object[]> treeMap = new TreeMap<>();
                timeUnitToCountToPatterns2.put(timeUnit, treeMap);
                countToPatterns = treeMap;
            } else {
                countToPatterns = countToPatterns3;
            }
            for (String pluralCount : keywords) {
                if (countToPatterns.get(pluralCount) == null || countToPatterns.get(pluralCount)[style2] == null) {
                    countToPatterns2 = countToPatterns;
                    searchInTree(resourceKey, style2, timeUnit, pluralCount, pluralCount, countToPatterns);
                } else {
                    countToPatterns2 = countToPatterns;
                }
                countToPatterns = countToPatterns2;
            }
        }
    }

    private void searchInTree(String resourceKey, int styl, TimeUnit timeUnit, String srcPluralCount, String searchPluralCount, Map<String, Object[]> countToPatterns) {
        ULocale parentLocale = this.locale;
        String srcTimeUnitName = timeUnit.toString();
        ULocale parentLocale2 = parentLocale;
        while (parentLocale2 != null) {
            try {
                MessageFormat messageFormat = new MessageFormat(((ICUResourceBundle) UResourceBundle.getBundleInstance(ICUData.ICU_UNIT_BASE_NAME, parentLocale2)).getWithFallback(resourceKey).getWithFallback(srcTimeUnitName).getStringWithFallback(searchPluralCount), this.locale);
                Object[] pair = countToPatterns.get(srcPluralCount);
                if (pair == null) {
                    pair = new Object[2];
                    countToPatterns.put(srcPluralCount, pair);
                }
                pair[styl] = messageFormat;
                return;
            } catch (MissingResourceException e) {
                parentLocale2 = parentLocale2.getFallback();
            }
        }
        if (parentLocale2 == null && resourceKey.equals("unitsShort")) {
            searchInTree("units", styl, timeUnit, srcPluralCount, searchPluralCount, countToPatterns);
            if (!(countToPatterns.get(srcPluralCount) == null || countToPatterns.get(srcPluralCount)[styl] == null)) {
                return;
            }
        }
        if (searchPluralCount.equals(PluralRules.KEYWORD_OTHER)) {
            MessageFormat messageFormat2 = null;
            if (timeUnit == TimeUnit.SECOND) {
                messageFormat2 = new MessageFormat(DEFAULT_PATTERN_FOR_SECOND, this.locale);
            } else if (timeUnit == TimeUnit.MINUTE) {
                messageFormat2 = new MessageFormat(DEFAULT_PATTERN_FOR_MINUTE, this.locale);
            } else if (timeUnit == TimeUnit.HOUR) {
                messageFormat2 = new MessageFormat(DEFAULT_PATTERN_FOR_HOUR, this.locale);
            } else if (timeUnit == TimeUnit.WEEK) {
                messageFormat2 = new MessageFormat(DEFAULT_PATTERN_FOR_WEEK, this.locale);
            } else if (timeUnit == TimeUnit.DAY) {
                messageFormat2 = new MessageFormat(DEFAULT_PATTERN_FOR_DAY, this.locale);
            } else if (timeUnit == TimeUnit.MONTH) {
                messageFormat2 = new MessageFormat(DEFAULT_PATTERN_FOR_MONTH, this.locale);
            } else if (timeUnit == TimeUnit.YEAR) {
                messageFormat2 = new MessageFormat(DEFAULT_PATTERN_FOR_YEAR, this.locale);
            }
            Object[] pair2 = countToPatterns.get(srcPluralCount);
            if (pair2 == null) {
                pair2 = new Object[2];
                countToPatterns.put(srcPluralCount, pair2);
            }
            pair2[styl] = messageFormat2;
            return;
        }
        searchInTree(resourceKey, styl, timeUnit, srcPluralCount, PluralRules.KEYWORD_OTHER, countToPatterns);
    }

    @Override // java.text.Format
    @Deprecated
    public Object clone() {
        TimeUnitFormat result = (TimeUnitFormat) super.clone();
        result.format = (NumberFormat) this.format.clone();
        return result;
    }

    private Object writeReplace() throws ObjectStreamException {
        return super.toTimeUnitProxy();
    }

    private Object readResolve() throws ObjectStreamException {
        return new TimeUnitFormat(this.locale, this.style, this.format);
    }
}
