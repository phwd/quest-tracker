package android.icu.text;

import android.icu.impl.ICUData;
import android.icu.impl.ICUResourceBundle;
import android.icu.impl.PatternProps;
import android.icu.impl.PatternTokenizer;
import android.icu.impl.SoftCache;
import android.icu.impl.TZDBTimeZoneNames;
import android.icu.impl.TextTrieMap;
import android.icu.impl.TimeZoneGenericNames;
import android.icu.impl.TimeZoneNamesImpl;
import android.icu.impl.ZoneMeta;
import android.icu.lang.UCharacter;
import android.icu.text.DateFormat;
import android.icu.text.TimeZoneNames;
import android.icu.util.Calendar;
import android.icu.util.Freezable;
import android.icu.util.Output;
import android.icu.util.TimeZone;
import android.icu.util.ULocale;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamField;
import java.io.Serializable;
import java.text.AttributedCharacterIterator;
import java.text.AttributedString;
import java.text.FieldPosition;
import java.text.ParseException;
import java.text.ParsePosition;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collection;
import java.util.Date;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.MissingResourceException;

public class TimeZoneFormat extends UFormat implements Freezable<TimeZoneFormat>, Serializable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final EnumSet<TimeZoneGenericNames.GenericNameType> ALL_GENERIC_NAME_TYPES = EnumSet.of(TimeZoneGenericNames.GenericNameType.LOCATION, TimeZoneGenericNames.GenericNameType.LONG, TimeZoneGenericNames.GenericNameType.SHORT);
    private static final EnumSet<TimeZoneNames.NameType> ALL_SIMPLE_NAME_TYPES = EnumSet.of(TimeZoneNames.NameType.LONG_STANDARD, TimeZoneNames.NameType.LONG_DAYLIGHT, TimeZoneNames.NameType.SHORT_STANDARD, TimeZoneNames.NameType.SHORT_DAYLIGHT, TimeZoneNames.NameType.EXEMPLAR_LOCATION);
    private static final String[] ALT_GMT_STRINGS = {DEFAULT_GMT_ZERO, "UTC", "UT"};
    private static final String ASCII_DIGITS = "0123456789";
    private static final String[] DEFAULT_GMT_DIGITS = {AndroidHardcodedSystemProperties.JAVA_VERSION, "1", "2", "3", "4", "5", "6", "7", "8", "9"};
    private static final char DEFAULT_GMT_OFFSET_SEP = ':';
    private static final String DEFAULT_GMT_PATTERN = "GMT{0}";
    private static final String DEFAULT_GMT_ZERO = "GMT";
    private static final String ISO8601_UTC = "Z";
    private static final int ISO_LOCAL_STYLE_FLAG = 256;
    private static final int ISO_Z_STYLE_FLAG = 128;
    private static final int MAX_OFFSET = 86400000;
    private static final int MAX_OFFSET_HOUR = 23;
    private static final int MAX_OFFSET_MINUTE = 59;
    private static final int MAX_OFFSET_SECOND = 59;
    private static final int MILLIS_PER_HOUR = 3600000;
    private static final int MILLIS_PER_MINUTE = 60000;
    private static final int MILLIS_PER_SECOND = 1000;
    private static final GMTOffsetPatternType[] PARSE_GMT_OFFSET_TYPES = {GMTOffsetPatternType.POSITIVE_HMS, GMTOffsetPatternType.NEGATIVE_HMS, GMTOffsetPatternType.POSITIVE_HM, GMTOffsetPatternType.NEGATIVE_HM, GMTOffsetPatternType.POSITIVE_H, GMTOffsetPatternType.NEGATIVE_H};
    private static volatile TextTrieMap<String> SHORT_ZONE_ID_TRIE = null;
    private static final String TZID_GMT = "Etc/GMT";
    private static final String UNKNOWN_LOCATION = "Unknown";
    private static final int UNKNOWN_OFFSET = Integer.MAX_VALUE;
    private static final String UNKNOWN_SHORT_ZONE_ID = "unk";
    private static final String UNKNOWN_ZONE_ID = "Etc/Unknown";
    private static volatile TextTrieMap<String> ZONE_ID_TRIE = null;
    private static TimeZoneFormatCache _tzfCache = new TimeZoneFormatCache(null);
    private static final ObjectStreamField[] serialPersistentFields = {new ObjectStreamField("_locale", ULocale.class), new ObjectStreamField("_tznames", TimeZoneNames.class), new ObjectStreamField("_gmtPattern", String.class), new ObjectStreamField("_gmtOffsetPatterns", String[].class), new ObjectStreamField("_gmtOffsetDigits", String[].class), new ObjectStreamField("_gmtZeroFormat", String.class), new ObjectStreamField("_parseAllStyles", Boolean.TYPE)};
    private static final long serialVersionUID = 2281246852693575022L;
    private transient boolean _abuttingOffsetHoursAndMinutes;
    private volatile transient boolean _frozen;
    private String[] _gmtOffsetDigits;
    private transient Object[][] _gmtOffsetPatternItems;
    private String[] _gmtOffsetPatterns;
    private String _gmtPattern;
    private transient String _gmtPatternPrefix;
    private transient String _gmtPatternSuffix;
    private String _gmtZeroFormat = DEFAULT_GMT_ZERO;
    private volatile transient TimeZoneGenericNames _gnames;
    private ULocale _locale;
    private boolean _parseAllStyles;
    private boolean _parseTZDBNames;
    private transient String _region;
    private volatile transient TimeZoneNames _tzdbNames;
    private TimeZoneNames _tznames;

    /* access modifiers changed from: private */
    public enum OffsetFields {
        H,
        HM,
        HMS
    }

    public enum ParseOption {
        ALL_STYLES,
        TZ_DATABASE_ABBREVIATIONS
    }

    public enum TimeType {
        UNKNOWN,
        STANDARD,
        DAYLIGHT
    }

    public enum Style {
        GENERIC_LOCATION(1),
        GENERIC_LONG(2),
        GENERIC_SHORT(4),
        SPECIFIC_LONG(8),
        SPECIFIC_SHORT(16),
        LOCALIZED_GMT(32),
        LOCALIZED_GMT_SHORT(64),
        ISO_BASIC_SHORT(128),
        ISO_BASIC_LOCAL_SHORT(256),
        ISO_BASIC_FIXED(128),
        ISO_BASIC_LOCAL_FIXED(256),
        ISO_BASIC_FULL(128),
        ISO_BASIC_LOCAL_FULL(256),
        ISO_EXTENDED_FIXED(128),
        ISO_EXTENDED_LOCAL_FIXED(256),
        ISO_EXTENDED_FULL(128),
        ISO_EXTENDED_LOCAL_FULL(256),
        ZONE_ID(512),
        ZONE_ID_SHORT(1024),
        EXEMPLAR_LOCATION(2048);
        
        final int flag;

        private Style(int flag2) {
            this.flag = flag2;
        }
    }

    public enum GMTOffsetPatternType {
        POSITIVE_HM("+H:mm", DateFormat.HOUR24_MINUTE, true),
        POSITIVE_HMS("+H:mm:ss", DateFormat.HOUR24_MINUTE_SECOND, true),
        NEGATIVE_HM("-H:mm", DateFormat.HOUR24_MINUTE, false),
        NEGATIVE_HMS("-H:mm:ss", DateFormat.HOUR24_MINUTE_SECOND, false),
        POSITIVE_H("+H", DateFormat.HOUR24, true),
        NEGATIVE_H("-H", DateFormat.HOUR24, false);
        
        private String _defaultPattern;
        private boolean _isPositive;
        private String _required;

        private GMTOffsetPatternType(String defaultPattern, String required, boolean isPositive) {
            this._defaultPattern = defaultPattern;
            this._required = required;
            this._isPositive = isPositive;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private String defaultPattern() {
            return this._defaultPattern;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private String required() {
            return this._required;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private boolean isPositive() {
            return this._isPositive;
        }
    }

    protected TimeZoneFormat(ULocale locale) {
        this._locale = locale;
        this._tznames = TimeZoneNames.getInstance(locale);
        String gmtPattern = null;
        String hourFormats = null;
        try {
            ICUResourceBundle bundle = (ICUResourceBundle) ICUResourceBundle.getBundleInstance(ICUData.ICU_ZONE_BASE_NAME, locale);
            try {
                gmtPattern = bundle.getStringWithFallback("zoneStrings/gmtFormat");
            } catch (MissingResourceException e) {
            }
            try {
                hourFormats = bundle.getStringWithFallback("zoneStrings/hourFormat");
            } catch (MissingResourceException e2) {
            }
            try {
                this._gmtZeroFormat = bundle.getStringWithFallback("zoneStrings/gmtZeroFormat");
            } catch (MissingResourceException e3) {
            }
        } catch (MissingResourceException e4) {
        }
        initGMTPattern(gmtPattern == null ? DEFAULT_GMT_PATTERN : gmtPattern);
        String[] gmtOffsetPatterns = new String[GMTOffsetPatternType.values().length];
        if (hourFormats != null) {
            String[] hourPatterns = hourFormats.split(";", 2);
            gmtOffsetPatterns[GMTOffsetPatternType.POSITIVE_H.ordinal()] = truncateOffsetPattern(hourPatterns[0]);
            gmtOffsetPatterns[GMTOffsetPatternType.POSITIVE_HM.ordinal()] = hourPatterns[0];
            gmtOffsetPatterns[GMTOffsetPatternType.POSITIVE_HMS.ordinal()] = expandOffsetPattern(hourPatterns[0]);
            gmtOffsetPatterns[GMTOffsetPatternType.NEGATIVE_H.ordinal()] = truncateOffsetPattern(hourPatterns[1]);
            gmtOffsetPatterns[GMTOffsetPatternType.NEGATIVE_HM.ordinal()] = hourPatterns[1];
            gmtOffsetPatterns[GMTOffsetPatternType.NEGATIVE_HMS.ordinal()] = expandOffsetPattern(hourPatterns[1]);
        } else {
            GMTOffsetPatternType[] values = GMTOffsetPatternType.values();
            for (GMTOffsetPatternType patType : values) {
                gmtOffsetPatterns[patType.ordinal()] = patType.defaultPattern();
            }
        }
        initGMTOffsetPatterns(gmtOffsetPatterns);
        this._gmtOffsetDigits = DEFAULT_GMT_DIGITS;
        NumberingSystem ns = NumberingSystem.getInstance(locale);
        if (!ns.isAlgorithmic()) {
            this._gmtOffsetDigits = toCodePoints(ns.getDescription());
        }
    }

    public static TimeZoneFormat getInstance(ULocale locale) {
        if (locale != null) {
            return (TimeZoneFormat) _tzfCache.getInstance(locale, locale);
        }
        throw new NullPointerException("locale is null");
    }

    public static TimeZoneFormat getInstance(Locale locale) {
        return getInstance(ULocale.forLocale(locale));
    }

    public TimeZoneNames getTimeZoneNames() {
        return this._tznames;
    }

    private TimeZoneGenericNames getTimeZoneGenericNames() {
        if (this._gnames == null) {
            synchronized (this) {
                if (this._gnames == null) {
                    this._gnames = TimeZoneGenericNames.getInstance(this._locale);
                }
            }
        }
        return this._gnames;
    }

    private TimeZoneNames getTZDBTimeZoneNames() {
        if (this._tzdbNames == null) {
            synchronized (this) {
                if (this._tzdbNames == null) {
                    this._tzdbNames = new TZDBTimeZoneNames(this._locale);
                }
            }
        }
        return this._tzdbNames;
    }

    public TimeZoneFormat setTimeZoneNames(TimeZoneNames tznames) {
        if (!isFrozen()) {
            this._tznames = tznames;
            this._gnames = new TimeZoneGenericNames(this._locale, this._tznames);
            return this;
        }
        throw new UnsupportedOperationException("Attempt to modify frozen object");
    }

    public String getGMTPattern() {
        return this._gmtPattern;
    }

    public TimeZoneFormat setGMTPattern(String pattern) {
        if (!isFrozen()) {
            initGMTPattern(pattern);
            return this;
        }
        throw new UnsupportedOperationException("Attempt to modify frozen object");
    }

    public String getGMTOffsetPattern(GMTOffsetPatternType type) {
        return this._gmtOffsetPatterns[type.ordinal()];
    }

    public TimeZoneFormat setGMTOffsetPattern(GMTOffsetPatternType type, String pattern) {
        if (isFrozen()) {
            throw new UnsupportedOperationException("Attempt to modify frozen object");
        } else if (pattern != null) {
            Object[] parsedItems = parseOffsetPattern(pattern, type.required());
            this._gmtOffsetPatterns[type.ordinal()] = pattern;
            this._gmtOffsetPatternItems[type.ordinal()] = parsedItems;
            checkAbuttingHoursAndMinutes();
            return this;
        } else {
            throw new NullPointerException("Null GMT offset pattern");
        }
    }

    public String getGMTOffsetDigits() {
        StringBuilder buf = new StringBuilder(this._gmtOffsetDigits.length);
        for (String digit : this._gmtOffsetDigits) {
            buf.append(digit);
        }
        return buf.toString();
    }

    public TimeZoneFormat setGMTOffsetDigits(String digits) {
        if (isFrozen()) {
            throw new UnsupportedOperationException("Attempt to modify frozen object");
        } else if (digits != null) {
            String[] digitArray = toCodePoints(digits);
            if (digitArray.length == 10) {
                this._gmtOffsetDigits = digitArray;
                return this;
            }
            throw new IllegalArgumentException("Length of digits must be 10");
        } else {
            throw new NullPointerException("Null GMT offset digits");
        }
    }

    public String getGMTZeroFormat() {
        return this._gmtZeroFormat;
    }

    public TimeZoneFormat setGMTZeroFormat(String gmtZeroFormat) {
        if (isFrozen()) {
            throw new UnsupportedOperationException("Attempt to modify frozen object");
        } else if (gmtZeroFormat == null) {
            throw new NullPointerException("Null GMT zero format");
        } else if (gmtZeroFormat.length() != 0) {
            this._gmtZeroFormat = gmtZeroFormat;
            return this;
        } else {
            throw new IllegalArgumentException("Empty GMT zero format");
        }
    }

    public TimeZoneFormat setDefaultParseOptions(EnumSet<ParseOption> options) {
        this._parseAllStyles = options.contains(ParseOption.ALL_STYLES);
        this._parseTZDBNames = options.contains(ParseOption.TZ_DATABASE_ABBREVIATIONS);
        return this;
    }

    public EnumSet<ParseOption> getDefaultParseOptions() {
        if (this._parseAllStyles && this._parseTZDBNames) {
            return EnumSet.of(ParseOption.ALL_STYLES, ParseOption.TZ_DATABASE_ABBREVIATIONS);
        }
        if (this._parseAllStyles) {
            return EnumSet.of(ParseOption.ALL_STYLES);
        }
        if (this._parseTZDBNames) {
            return EnumSet.of(ParseOption.TZ_DATABASE_ABBREVIATIONS);
        }
        return EnumSet.noneOf(ParseOption.class);
    }

    public final String formatOffsetISO8601Basic(int offset, boolean useUtcIndicator, boolean isShort, boolean ignoreSeconds) {
        return formatOffsetISO8601(offset, true, useUtcIndicator, isShort, ignoreSeconds);
    }

    public final String formatOffsetISO8601Extended(int offset, boolean useUtcIndicator, boolean isShort, boolean ignoreSeconds) {
        return formatOffsetISO8601(offset, false, useUtcIndicator, isShort, ignoreSeconds);
    }

    public String formatOffsetLocalizedGMT(int offset) {
        return formatOffsetLocalizedGMT(offset, false);
    }

    public String formatOffsetShortLocalizedGMT(int offset) {
        return formatOffsetLocalizedGMT(offset, true);
    }

    public final String format(Style style, TimeZone tz, long date) {
        return format(style, tz, date, null);
    }

    public String format(Style style, TimeZone tz, long date, Output<TimeType> timeType) {
        String result = null;
        if (timeType != null) {
            timeType.value = (T) TimeType.UNKNOWN;
        }
        boolean noOffsetFormatFallback = false;
        switch (style) {
            case GENERIC_LOCATION:
                result = getTimeZoneGenericNames().getGenericLocationName(ZoneMeta.getCanonicalCLDRID(tz));
                break;
            case GENERIC_LONG:
                result = getTimeZoneGenericNames().getDisplayName(tz, TimeZoneGenericNames.GenericNameType.LONG, date);
                break;
            case GENERIC_SHORT:
                result = getTimeZoneGenericNames().getDisplayName(tz, TimeZoneGenericNames.GenericNameType.SHORT, date);
                break;
            case SPECIFIC_LONG:
                result = formatSpecific(tz, TimeZoneNames.NameType.LONG_STANDARD, TimeZoneNames.NameType.LONG_DAYLIGHT, date, timeType);
                break;
            case SPECIFIC_SHORT:
                result = formatSpecific(tz, TimeZoneNames.NameType.SHORT_STANDARD, TimeZoneNames.NameType.SHORT_DAYLIGHT, date, timeType);
                break;
            case ZONE_ID:
                result = tz.getID();
                noOffsetFormatFallback = true;
                break;
            case ZONE_ID_SHORT:
                result = ZoneMeta.getShortID(tz);
                if (result == null) {
                    result = UNKNOWN_SHORT_ZONE_ID;
                }
                noOffsetFormatFallback = true;
                break;
            case EXEMPLAR_LOCATION:
                result = formatExemplarLocation(tz);
                noOffsetFormatFallback = true;
                break;
        }
        if (result == null && !noOffsetFormatFallback) {
            int[] offsets = {0, 0};
            tz.getOffset(date, false, offsets);
            int offset = offsets[0] + offsets[1];
            switch (style) {
                case GENERIC_LOCATION:
                case GENERIC_LONG:
                case SPECIFIC_LONG:
                case LOCALIZED_GMT:
                    result = formatOffsetLocalizedGMT(offset);
                    break;
                case GENERIC_SHORT:
                case SPECIFIC_SHORT:
                case LOCALIZED_GMT_SHORT:
                    result = formatOffsetShortLocalizedGMT(offset);
                    break;
                case ISO_BASIC_SHORT:
                    result = formatOffsetISO8601Basic(offset, true, true, true);
                    break;
                case ISO_BASIC_LOCAL_SHORT:
                    result = formatOffsetISO8601Basic(offset, false, true, true);
                    break;
                case ISO_BASIC_FIXED:
                    result = formatOffsetISO8601Basic(offset, true, false, true);
                    break;
                case ISO_BASIC_LOCAL_FIXED:
                    result = formatOffsetISO8601Basic(offset, false, false, true);
                    break;
                case ISO_BASIC_FULL:
                    result = formatOffsetISO8601Basic(offset, true, false, false);
                    break;
                case ISO_BASIC_LOCAL_FULL:
                    result = formatOffsetISO8601Basic(offset, false, false, false);
                    break;
                case ISO_EXTENDED_FIXED:
                    result = formatOffsetISO8601Extended(offset, true, false, true);
                    break;
                case ISO_EXTENDED_LOCAL_FIXED:
                    result = formatOffsetISO8601Extended(offset, false, false, true);
                    break;
                case ISO_EXTENDED_FULL:
                    result = formatOffsetISO8601Extended(offset, true, false, false);
                    break;
                case ISO_EXTENDED_LOCAL_FULL:
                    result = formatOffsetISO8601Extended(offset, false, false, false);
                    break;
            }
            if (timeType != null) {
                timeType.value = offsets[1] != 0 ? (T) TimeType.DAYLIGHT : (T) TimeType.STANDARD;
            }
        }
        return result;
    }

    public final int parseOffsetISO8601(String text, ParsePosition pos) {
        return parseOffsetISO8601(text, pos, false, null);
    }

    public int parseOffsetLocalizedGMT(String text, ParsePosition pos) {
        return parseOffsetLocalizedGMT(text, pos, false, null);
    }

    public int parseOffsetShortLocalizedGMT(String text, ParsePosition pos) {
        return parseOffsetLocalizedGMT(text, pos, true, null);
    }

    public TimeZone parse(Style style, String text, ParsePosition pos, EnumSet<ParseOption> options, Output<TimeType> timeType) {
        boolean parseTZDBAbbrev;
        String parsedID;
        boolean parseAllStyles;
        TimeZone parsedTZ;
        TimeZoneGenericNames.GenericMatchInfo genericMatch;
        TimeZoneNames.MatchInfo specificMatch;
        int matchPos;
        EnumSet<TimeZoneNames.NameType> nameTypes;
        Output<TimeType> timeType2 = timeType;
        if (timeType2 == null) {
            timeType2 = new Output<>(TimeType.UNKNOWN);
        } else {
            timeType2.value = (T) TimeType.UNKNOWN;
        }
        int startIdx = pos.getIndex();
        int maxPos = text.length();
        boolean fallbackLocalizedGMT = style == Style.SPECIFIC_LONG || style == Style.GENERIC_LONG || style == Style.GENERIC_LOCATION;
        boolean fallbackShortLocalizedGMT = style == Style.SPECIFIC_SHORT || style == Style.GENERIC_SHORT;
        int evaluated = 0;
        ParsePosition tmpPos = new ParsePosition(startIdx);
        int parsedOffset = Integer.MAX_VALUE;
        int parsedPos = -1;
        if (fallbackLocalizedGMT || fallbackShortLocalizedGMT) {
            Output<Boolean> hasDigitOffset = new Output<>(false);
            int offset = parseOffsetLocalizedGMT(text, tmpPos, fallbackShortLocalizedGMT, hasDigitOffset);
            if (tmpPos.getErrorIndex() == -1) {
                if (tmpPos.getIndex() == maxPos || hasDigitOffset.value.booleanValue()) {
                    pos.setIndex(tmpPos.getIndex());
                    return getTimeZoneForOffset(offset);
                }
                parsedOffset = offset;
                parsedPos = tmpPos.getIndex();
            }
            evaluated = 0 | Style.LOCALIZED_GMT.flag | Style.LOCALIZED_GMT_SHORT.flag;
        }
        if (options == null) {
            parseTZDBAbbrev = getDefaultParseOptions().contains(ParseOption.TZ_DATABASE_ABBREVIATIONS);
        } else {
            parseTZDBAbbrev = options.contains(ParseOption.TZ_DATABASE_ABBREVIATIONS);
        }
        switch (style) {
            case GENERIC_LOCATION:
            case GENERIC_LONG:
            case GENERIC_SHORT:
                EnumSet<TimeZoneGenericNames.GenericNameType> genericNameTypes = null;
                int i = AnonymousClass1.$SwitchMap$android$icu$text$TimeZoneFormat$Style[style.ordinal()];
                if (i == 1) {
                    genericNameTypes = EnumSet.of(TimeZoneGenericNames.GenericNameType.LOCATION);
                } else if (i == 2) {
                    genericNameTypes = EnumSet.of(TimeZoneGenericNames.GenericNameType.LONG, TimeZoneGenericNames.GenericNameType.LOCATION);
                } else if (i == 3) {
                    genericNameTypes = EnumSet.of(TimeZoneGenericNames.GenericNameType.SHORT, TimeZoneGenericNames.GenericNameType.LOCATION);
                }
                TimeZoneGenericNames.GenericMatchInfo bestGeneric = getTimeZoneGenericNames().findBestMatch(text, startIdx, genericNameTypes);
                if (bestGeneric != null && bestGeneric.matchLength() + startIdx > parsedPos) {
                    timeType2.value = (T) bestGeneric.timeType();
                    pos.setIndex(bestGeneric.matchLength() + startIdx);
                    return TimeZone.getTimeZone(bestGeneric.tzID());
                }
            case SPECIFIC_LONG:
            case SPECIFIC_SHORT:
                if (style == Style.SPECIFIC_LONG) {
                    nameTypes = EnumSet.of(TimeZoneNames.NameType.LONG_STANDARD, TimeZoneNames.NameType.LONG_DAYLIGHT);
                } else {
                    nameTypes = EnumSet.of(TimeZoneNames.NameType.SHORT_STANDARD, TimeZoneNames.NameType.SHORT_DAYLIGHT);
                }
                Collection<TimeZoneNames.MatchInfo> specificMatches = this._tznames.find(text, startIdx, nameTypes);
                if (specificMatches != null) {
                    TimeZoneNames.MatchInfo specificMatch2 = null;
                    for (TimeZoneNames.MatchInfo match : specificMatches) {
                        if (startIdx + match.matchLength() > parsedPos) {
                            parsedPos = match.matchLength() + startIdx;
                            specificMatch2 = match;
                        }
                        specificMatches = specificMatches;
                    }
                    if (specificMatch2 != null) {
                        timeType2.value = (T) getTimeType(specificMatch2.nameType());
                        pos.setIndex(parsedPos);
                        return TimeZone.getTimeZone(getTimeZoneID(specificMatch2.tzID(), specificMatch2.mzID()));
                    }
                }
                if (!parseTZDBAbbrev || style != Style.SPECIFIC_SHORT) {
                    break;
                } else {
                    Collection<TimeZoneNames.MatchInfo> tzdbNameMatches = getTZDBTimeZoneNames().find(text, startIdx, nameTypes);
                    if (tzdbNameMatches == null) {
                        break;
                    } else {
                        TimeZoneNames.MatchInfo tzdbNameMatch = null;
                        for (TimeZoneNames.MatchInfo match2 : tzdbNameMatches) {
                            if (startIdx + match2.matchLength() > parsedPos) {
                                parsedPos = match2.matchLength() + startIdx;
                                tzdbNameMatch = match2;
                            }
                            nameTypes = nameTypes;
                        }
                        if (tzdbNameMatch != null) {
                            timeType2.value = (T) getTimeType(tzdbNameMatch.nameType());
                            pos.setIndex(parsedPos);
                            return TimeZone.getTimeZone(getTimeZoneID(tzdbNameMatch.tzID(), tzdbNameMatch.mzID()));
                        }
                    }
                }
                break;
            case ZONE_ID:
                tmpPos.setIndex(startIdx);
                tmpPos.setErrorIndex(-1);
                String id = parseZoneID(text, tmpPos);
                if (tmpPos.getErrorIndex() == -1) {
                    pos.setIndex(tmpPos.getIndex());
                    return TimeZone.getTimeZone(id);
                }
                break;
            case ZONE_ID_SHORT:
                tmpPos.setIndex(startIdx);
                tmpPos.setErrorIndex(-1);
                String id2 = parseShortZoneID(text, tmpPos);
                if (tmpPos.getErrorIndex() == -1) {
                    pos.setIndex(tmpPos.getIndex());
                    return TimeZone.getTimeZone(id2);
                }
                break;
            case EXEMPLAR_LOCATION:
                tmpPos.setIndex(startIdx);
                tmpPos.setErrorIndex(-1);
                String id3 = parseExemplarLocation(text, tmpPos);
                if (tmpPos.getErrorIndex() == -1) {
                    pos.setIndex(tmpPos.getIndex());
                    return TimeZone.getTimeZone(id3);
                }
                break;
            case LOCALIZED_GMT:
                tmpPos.setIndex(startIdx);
                tmpPos.setErrorIndex(-1);
                int offset2 = parseOffsetLocalizedGMT(text, tmpPos);
                if (tmpPos.getErrorIndex() != -1) {
                    evaluated |= Style.LOCALIZED_GMT_SHORT.flag;
                    break;
                } else {
                    pos.setIndex(tmpPos.getIndex());
                    return getTimeZoneForOffset(offset2);
                }
            case LOCALIZED_GMT_SHORT:
                tmpPos.setIndex(startIdx);
                tmpPos.setErrorIndex(-1);
                int offset3 = parseOffsetShortLocalizedGMT(text, tmpPos);
                if (tmpPos.getErrorIndex() != -1) {
                    evaluated |= Style.LOCALIZED_GMT.flag;
                    break;
                } else {
                    pos.setIndex(tmpPos.getIndex());
                    return getTimeZoneForOffset(offset3);
                }
            case ISO_BASIC_SHORT:
            case ISO_BASIC_FIXED:
            case ISO_BASIC_FULL:
            case ISO_EXTENDED_FIXED:
            case ISO_EXTENDED_FULL:
                tmpPos.setIndex(startIdx);
                tmpPos.setErrorIndex(-1);
                int offset4 = parseOffsetISO8601(text, tmpPos);
                if (tmpPos.getErrorIndex() == -1) {
                    pos.setIndex(tmpPos.getIndex());
                    return getTimeZoneForOffset(offset4);
                }
                break;
            case ISO_BASIC_LOCAL_SHORT:
            case ISO_BASIC_LOCAL_FIXED:
            case ISO_BASIC_LOCAL_FULL:
            case ISO_EXTENDED_LOCAL_FIXED:
            case ISO_EXTENDED_LOCAL_FULL:
                tmpPos.setIndex(startIdx);
                tmpPos.setErrorIndex(-1);
                Output<Boolean> hasDigitOffset2 = new Output<>(false);
                int offset5 = parseOffsetISO8601(text, tmpPos, false, hasDigitOffset2);
                if (tmpPos.getErrorIndex() == -1 && hasDigitOffset2.value.booleanValue()) {
                    pos.setIndex(tmpPos.getIndex());
                    return getTimeZoneForOffset(offset5);
                }
        }
        int evaluated2 = style.flag | evaluated;
        if (parsedPos > startIdx) {
            pos.setIndex(parsedPos);
            return getTimeZoneForOffset(parsedOffset);
        }
        TimeType parsedTimeType = TimeType.UNKNOWN;
        if (parsedPos >= maxPos) {
            parsedID = null;
        } else if ((evaluated2 & 128) == 0 || (evaluated2 & 256) == 0) {
            tmpPos.setIndex(startIdx);
            tmpPos.setErrorIndex(-1);
            Output<Boolean> hasDigitOffset3 = new Output<>(false);
            int offset6 = parseOffsetISO8601(text, tmpPos, false, hasDigitOffset3);
            parsedID = null;
            if (tmpPos.getErrorIndex() == -1) {
                if (tmpPos.getIndex() == maxPos || hasDigitOffset3.value.booleanValue()) {
                    pos.setIndex(tmpPos.getIndex());
                    return getTimeZoneForOffset(offset6);
                } else if (parsedPos < tmpPos.getIndex()) {
                    parsedOffset = offset6;
                    parsedTimeType = TimeType.UNKNOWN;
                    parsedPos = tmpPos.getIndex();
                    parsedID = null;
                }
            }
        } else {
            parsedID = null;
        }
        if (parsedPos < maxPos && (Style.LOCALIZED_GMT.flag & evaluated2) == 0) {
            tmpPos.setIndex(startIdx);
            tmpPos.setErrorIndex(-1);
            Output<Boolean> hasDigitOffset4 = new Output<>(false);
            int offset7 = parseOffsetLocalizedGMT(text, tmpPos, false, hasDigitOffset4);
            if (tmpPos.getErrorIndex() == -1) {
                if (tmpPos.getIndex() == maxPos || hasDigitOffset4.value.booleanValue()) {
                    pos.setIndex(tmpPos.getIndex());
                    return getTimeZoneForOffset(offset7);
                } else if (parsedPos < tmpPos.getIndex()) {
                    parsedOffset = offset7;
                    parsedID = null;
                    parsedTimeType = TimeType.UNKNOWN;
                    parsedPos = tmpPos.getIndex();
                }
            }
        }
        if (parsedPos < maxPos && (Style.LOCALIZED_GMT_SHORT.flag & evaluated2) == 0) {
            tmpPos.setIndex(startIdx);
            tmpPos.setErrorIndex(-1);
            Output<Boolean> hasDigitOffset5 = new Output<>(false);
            int offset8 = parseOffsetLocalizedGMT(text, tmpPos, true, hasDigitOffset5);
            if (tmpPos.getErrorIndex() == -1) {
                if (tmpPos.getIndex() == maxPos || hasDigitOffset5.value.booleanValue()) {
                    pos.setIndex(tmpPos.getIndex());
                    return getTimeZoneForOffset(offset8);
                } else if (parsedPos < tmpPos.getIndex()) {
                    parsedOffset = offset8;
                    parsedID = null;
                    parsedTimeType = (T) TimeType.UNKNOWN;
                    parsedPos = tmpPos.getIndex();
                }
            }
        }
        if (options == null) {
            parseAllStyles = getDefaultParseOptions().contains(ParseOption.ALL_STYLES);
        } else {
            parseAllStyles = options.contains(ParseOption.ALL_STYLES);
        }
        if (parseAllStyles) {
            if (parsedPos < maxPos) {
                Collection<TimeZoneNames.MatchInfo> specificMatches2 = this._tznames.find(text, startIdx, ALL_SIMPLE_NAME_TYPES);
                if (specificMatches2 != null) {
                    specificMatch = null;
                    matchPos = -1;
                    for (TimeZoneNames.MatchInfo match3 : specificMatches2) {
                        if (startIdx + match3.matchLength() > matchPos) {
                            specificMatch = match3;
                            matchPos = startIdx + match3.matchLength();
                        }
                    }
                } else {
                    specificMatch = null;
                    matchPos = -1;
                }
                if (parsedPos < matchPos) {
                    parsedPos = matchPos;
                    parsedID = getTimeZoneID(specificMatch.tzID(), specificMatch.mzID());
                    parsedTimeType = (T) getTimeType(specificMatch.nameType());
                    parsedOffset = Integer.MAX_VALUE;
                }
            }
            if (parseTZDBAbbrev && parsedPos < maxPos && (Style.SPECIFIC_SHORT.flag & evaluated2) == 0) {
                Collection<TimeZoneNames.MatchInfo> tzdbNameMatches2 = getTZDBTimeZoneNames().find(text, startIdx, ALL_SIMPLE_NAME_TYPES);
                TimeZoneNames.MatchInfo tzdbNameMatch2 = null;
                int matchPos2 = -1;
                if (tzdbNameMatches2 != null) {
                    for (TimeZoneNames.MatchInfo match4 : tzdbNameMatches2) {
                        if (startIdx + match4.matchLength() > matchPos2) {
                            matchPos2 = match4.matchLength() + startIdx;
                            tzdbNameMatch2 = match4;
                        }
                        tzdbNameMatches2 = tzdbNameMatches2;
                    }
                    if (parsedPos < matchPos2) {
                        parsedPos = matchPos2;
                        parsedID = getTimeZoneID(tzdbNameMatch2.tzID(), tzdbNameMatch2.mzID());
                        parsedTimeType = getTimeType(tzdbNameMatch2.nameType());
                        parsedOffset = Integer.MAX_VALUE;
                    }
                }
            }
            if (parsedPos < maxPos && (genericMatch = getTimeZoneGenericNames().findBestMatch(text, startIdx, ALL_GENERIC_NAME_TYPES)) != null && parsedPos < genericMatch.matchLength() + startIdx) {
                parsedPos = startIdx + genericMatch.matchLength();
                parsedID = genericMatch.tzID();
                parsedTimeType = genericMatch.timeType();
                parsedOffset = Integer.MAX_VALUE;
            }
            if (parsedPos < maxPos && (Style.ZONE_ID.flag & evaluated2) == 0) {
                tmpPos.setIndex(startIdx);
                tmpPos.setErrorIndex(-1);
                String id4 = parseZoneID(text, tmpPos);
                if (tmpPos.getErrorIndex() == -1 && parsedPos < tmpPos.getIndex()) {
                    int parsedPos2 = tmpPos.getIndex();
                    parsedTimeType = (T) TimeType.UNKNOWN;
                    parsedPos = parsedPos2;
                    parsedID = id4;
                    parsedOffset = Integer.MAX_VALUE;
                }
            }
            if (parsedPos < maxPos && (Style.ZONE_ID_SHORT.flag & evaluated2) == 0) {
                tmpPos.setIndex(startIdx);
                tmpPos.setErrorIndex(-1);
                String id5 = parseShortZoneID(text, tmpPos);
                if (tmpPos.getErrorIndex() == -1 && parsedPos < tmpPos.getIndex()) {
                    parsedPos = tmpPos.getIndex();
                    parsedID = id5;
                    parsedTimeType = (T) TimeType.UNKNOWN;
                    parsedOffset = Integer.MAX_VALUE;
                }
            }
        }
        if (parsedPos > startIdx) {
            if (parsedID != null) {
                parsedTZ = TimeZone.getTimeZone(parsedID);
            } else {
                parsedTZ = getTimeZoneForOffset(parsedOffset);
            }
            timeType2.value = (T) parsedTimeType;
            pos.setIndex(parsedPos);
            return parsedTZ;
        }
        pos.setErrorIndex(startIdx);
        return null;
    }

    public TimeZone parse(Style style, String text, ParsePosition pos, Output<TimeType> timeType) {
        return parse(style, text, pos, null, timeType);
    }

    public final TimeZone parse(String text, ParsePosition pos) {
        return parse(Style.GENERIC_LOCATION, text, pos, EnumSet.of(ParseOption.ALL_STYLES), null);
    }

    public final TimeZone parse(String text) throws ParseException {
        ParsePosition pos = new ParsePosition(0);
        TimeZone tz = parse(text, pos);
        if (pos.getErrorIndex() < 0) {
            return tz;
        }
        throw new ParseException("Unparseable time zone: \"" + text + "\"", 0);
    }

    @Override // java.text.Format
    public StringBuffer format(Object obj, StringBuffer toAppendTo, FieldPosition pos) {
        TimeZone tz;
        long date = System.currentTimeMillis();
        if (obj instanceof TimeZone) {
            tz = (TimeZone) obj;
        } else if (obj instanceof Calendar) {
            tz = ((Calendar) obj).getTimeZone();
            date = ((Calendar) obj).getTimeInMillis();
        } else {
            throw new IllegalArgumentException("Cannot format given Object (" + obj.getClass().getName() + ") as a time zone");
        }
        String result = formatOffsetLocalizedGMT(tz.getOffset(date));
        toAppendTo.append(result);
        if (pos.getFieldAttribute() == DateFormat.Field.TIME_ZONE || pos.getField() == 17) {
            pos.setBeginIndex(0);
            pos.setEndIndex(result.length());
        }
        return toAppendTo;
    }

    @Override // java.text.Format
    public AttributedCharacterIterator formatToCharacterIterator(Object obj) {
        AttributedString as = new AttributedString(format(obj, new StringBuffer(), new FieldPosition(0)).toString());
        as.addAttribute(DateFormat.Field.TIME_ZONE, DateFormat.Field.TIME_ZONE);
        return as.getIterator();
    }

    @Override // java.text.Format
    public Object parseObject(String source, ParsePosition pos) {
        return parse(source, pos);
    }

    private String formatOffsetLocalizedGMT(int offset, boolean isShort) {
        Object[] offsetPatternItems;
        if (offset == 0) {
            return this._gmtZeroFormat;
        }
        StringBuilder buf = new StringBuilder();
        boolean positive = true;
        if (offset < 0) {
            offset = -offset;
            positive = false;
        }
        int offsetH = offset / 3600000;
        int offset2 = offset % 3600000;
        int offsetM = offset2 / 60000;
        int offset3 = offset2 % 60000;
        int offsetS = offset3 / 1000;
        if (offsetH > 23 || offsetM > 59 || offsetS > 59) {
            throw new IllegalArgumentException("Offset out of range :" + offset3);
        }
        if (positive) {
            if (offsetS != 0) {
                offsetPatternItems = this._gmtOffsetPatternItems[GMTOffsetPatternType.POSITIVE_HMS.ordinal()];
            } else if (offsetM != 0 || !isShort) {
                offsetPatternItems = this._gmtOffsetPatternItems[GMTOffsetPatternType.POSITIVE_HM.ordinal()];
            } else {
                offsetPatternItems = this._gmtOffsetPatternItems[GMTOffsetPatternType.POSITIVE_H.ordinal()];
            }
        } else if (offsetS != 0) {
            offsetPatternItems = this._gmtOffsetPatternItems[GMTOffsetPatternType.NEGATIVE_HMS.ordinal()];
        } else if (offsetM != 0 || !isShort) {
            offsetPatternItems = this._gmtOffsetPatternItems[GMTOffsetPatternType.NEGATIVE_HM.ordinal()];
        } else {
            offsetPatternItems = this._gmtOffsetPatternItems[GMTOffsetPatternType.NEGATIVE_H.ordinal()];
        }
        buf.append(this._gmtPatternPrefix);
        for (Object item : offsetPatternItems) {
            if (item instanceof String) {
                buf.append((String) item);
            } else if (item instanceof GMTOffsetField) {
                char type = ((GMTOffsetField) item).getType();
                int i = 2;
                if (type == 'H') {
                    if (isShort) {
                        i = 1;
                    }
                    appendOffsetDigits(buf, offsetH, i);
                } else if (type == 'm') {
                    appendOffsetDigits(buf, offsetM, 2);
                } else if (type == 's') {
                    appendOffsetDigits(buf, offsetS, 2);
                }
            }
        }
        buf.append(this._gmtPatternSuffix);
        return buf.toString();
    }

    private String formatOffsetISO8601(int offset, boolean isBasic, boolean useUtcIndicator, boolean isShort, boolean ignoreSeconds) {
        int absOffset = offset < 0 ? -offset : offset;
        if (useUtcIndicator) {
            if (absOffset < 1000) {
                return ISO8601_UTC;
            }
            if (ignoreSeconds && absOffset < 60000) {
                return ISO8601_UTC;
            }
        }
        OffsetFields minFields = isShort ? OffsetFields.H : OffsetFields.HM;
        OffsetFields maxFields = ignoreSeconds ? OffsetFields.HM : OffsetFields.HMS;
        Character sep = isBasic ? null : Character.valueOf(DEFAULT_GMT_OFFSET_SEP);
        if (absOffset < 86400000) {
            int absOffset2 = absOffset % 3600000;
            int[] fields = {absOffset / 3600000, absOffset2 / 60000, (absOffset2 % 60000) / 1000};
            int lastIdx = maxFields.ordinal();
            while (lastIdx > minFields.ordinal() && fields[lastIdx] == 0) {
                lastIdx--;
            }
            StringBuilder buf = new StringBuilder();
            char sign = '+';
            if (offset < 0) {
                int idx = 0;
                while (true) {
                    if (idx > lastIdx) {
                        break;
                    } else if (fields[idx] != 0) {
                        sign = '-';
                        break;
                    } else {
                        idx++;
                    }
                }
            }
            buf.append(sign);
            for (int idx2 = 0; idx2 <= lastIdx; idx2++) {
                if (!(sep == null || idx2 == 0)) {
                    buf.append((Object) sep);
                }
                if (fields[idx2] < 10) {
                    buf.append('0');
                }
                buf.append(fields[idx2]);
            }
            return buf.toString();
        }
        throw new IllegalArgumentException("Offset out of range :" + offset);
    }

    private String formatSpecific(TimeZone tz, TimeZoneNames.NameType stdType, TimeZoneNames.NameType dstType, long date, Output<TimeType> timeType) {
        String name;
        boolean isDaylight = tz.inDaylightTime(new Date(date));
        if (isDaylight) {
            name = getTimeZoneNames().getDisplayName(ZoneMeta.getCanonicalCLDRID(tz), dstType, date);
        } else {
            name = getTimeZoneNames().getDisplayName(ZoneMeta.getCanonicalCLDRID(tz), stdType, date);
        }
        if (!(name == null || timeType == null)) {
            timeType.value = isDaylight ? (T) TimeType.DAYLIGHT : (T) TimeType.STANDARD;
        }
        return name;
    }

    private String formatExemplarLocation(TimeZone tz) {
        String location = getTimeZoneNames().getExemplarLocationName(ZoneMeta.getCanonicalCLDRID(tz));
        if (location != null) {
            return location;
        }
        String location2 = getTimeZoneNames().getExemplarLocationName("Etc/Unknown");
        if (location2 == null) {
            return UNKNOWN_LOCATION;
        }
        return location2;
    }

    private String getTimeZoneID(String tzID, String mzID) {
        String id = tzID;
        if (id != null || (id = this._tznames.getReferenceZoneID(mzID, getTargetRegion())) != null) {
            return id;
        }
        throw new IllegalArgumentException("Invalid mzID: " + mzID);
    }

    private synchronized String getTargetRegion() {
        if (this._region == null) {
            this._region = this._locale.getCountry();
            if (this._region.length() == 0) {
                this._region = ULocale.addLikelySubtags(this._locale).getCountry();
                if (this._region.length() == 0) {
                    this._region = "001";
                }
            }
        }
        return this._region;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: android.icu.text.TimeZoneFormat$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$android$icu$text$TimeZoneNames$NameType = new int[TimeZoneNames.NameType.values().length];

        static {
            try {
                $SwitchMap$android$icu$text$TimeZoneNames$NameType[TimeZoneNames.NameType.LONG_STANDARD.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$android$icu$text$TimeZoneNames$NameType[TimeZoneNames.NameType.SHORT_STANDARD.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$android$icu$text$TimeZoneNames$NameType[TimeZoneNames.NameType.LONG_DAYLIGHT.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$android$icu$text$TimeZoneNames$NameType[TimeZoneNames.NameType.SHORT_DAYLIGHT.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            $SwitchMap$android$icu$text$TimeZoneFormat$Style = new int[Style.values().length];
            try {
                $SwitchMap$android$icu$text$TimeZoneFormat$Style[Style.GENERIC_LOCATION.ordinal()] = 1;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$android$icu$text$TimeZoneFormat$Style[Style.GENERIC_LONG.ordinal()] = 2;
            } catch (NoSuchFieldError e6) {
            }
            try {
                $SwitchMap$android$icu$text$TimeZoneFormat$Style[Style.GENERIC_SHORT.ordinal()] = 3;
            } catch (NoSuchFieldError e7) {
            }
            try {
                $SwitchMap$android$icu$text$TimeZoneFormat$Style[Style.SPECIFIC_LONG.ordinal()] = 4;
            } catch (NoSuchFieldError e8) {
            }
            try {
                $SwitchMap$android$icu$text$TimeZoneFormat$Style[Style.SPECIFIC_SHORT.ordinal()] = 5;
            } catch (NoSuchFieldError e9) {
            }
            try {
                $SwitchMap$android$icu$text$TimeZoneFormat$Style[Style.ZONE_ID.ordinal()] = 6;
            } catch (NoSuchFieldError e10) {
            }
            try {
                $SwitchMap$android$icu$text$TimeZoneFormat$Style[Style.ZONE_ID_SHORT.ordinal()] = 7;
            } catch (NoSuchFieldError e11) {
            }
            try {
                $SwitchMap$android$icu$text$TimeZoneFormat$Style[Style.EXEMPLAR_LOCATION.ordinal()] = 8;
            } catch (NoSuchFieldError e12) {
            }
            try {
                $SwitchMap$android$icu$text$TimeZoneFormat$Style[Style.LOCALIZED_GMT.ordinal()] = 9;
            } catch (NoSuchFieldError e13) {
            }
            try {
                $SwitchMap$android$icu$text$TimeZoneFormat$Style[Style.LOCALIZED_GMT_SHORT.ordinal()] = 10;
            } catch (NoSuchFieldError e14) {
            }
            try {
                $SwitchMap$android$icu$text$TimeZoneFormat$Style[Style.ISO_BASIC_SHORT.ordinal()] = 11;
            } catch (NoSuchFieldError e15) {
            }
            try {
                $SwitchMap$android$icu$text$TimeZoneFormat$Style[Style.ISO_BASIC_LOCAL_SHORT.ordinal()] = 12;
            } catch (NoSuchFieldError e16) {
            }
            try {
                $SwitchMap$android$icu$text$TimeZoneFormat$Style[Style.ISO_BASIC_FIXED.ordinal()] = 13;
            } catch (NoSuchFieldError e17) {
            }
            try {
                $SwitchMap$android$icu$text$TimeZoneFormat$Style[Style.ISO_BASIC_LOCAL_FIXED.ordinal()] = 14;
            } catch (NoSuchFieldError e18) {
            }
            try {
                $SwitchMap$android$icu$text$TimeZoneFormat$Style[Style.ISO_BASIC_FULL.ordinal()] = 15;
            } catch (NoSuchFieldError e19) {
            }
            try {
                $SwitchMap$android$icu$text$TimeZoneFormat$Style[Style.ISO_BASIC_LOCAL_FULL.ordinal()] = 16;
            } catch (NoSuchFieldError e20) {
            }
            try {
                $SwitchMap$android$icu$text$TimeZoneFormat$Style[Style.ISO_EXTENDED_FIXED.ordinal()] = 17;
            } catch (NoSuchFieldError e21) {
            }
            try {
                $SwitchMap$android$icu$text$TimeZoneFormat$Style[Style.ISO_EXTENDED_LOCAL_FIXED.ordinal()] = 18;
            } catch (NoSuchFieldError e22) {
            }
            try {
                $SwitchMap$android$icu$text$TimeZoneFormat$Style[Style.ISO_EXTENDED_FULL.ordinal()] = 19;
            } catch (NoSuchFieldError e23) {
            }
            try {
                $SwitchMap$android$icu$text$TimeZoneFormat$Style[Style.ISO_EXTENDED_LOCAL_FULL.ordinal()] = 20;
            } catch (NoSuchFieldError e24) {
            }
        }
    }

    private TimeType getTimeType(TimeZoneNames.NameType nameType) {
        int i = AnonymousClass1.$SwitchMap$android$icu$text$TimeZoneNames$NameType[nameType.ordinal()];
        if (i == 1 || i == 2) {
            return TimeType.STANDARD;
        }
        if (i == 3 || i == 4) {
            return TimeType.DAYLIGHT;
        }
        return TimeType.UNKNOWN;
    }

    private void initGMTPattern(String gmtPattern) {
        int idx = gmtPattern.indexOf("{0}");
        if (idx >= 0) {
            this._gmtPattern = gmtPattern;
            this._gmtPatternPrefix = unquote(gmtPattern.substring(0, idx));
            this._gmtPatternSuffix = unquote(gmtPattern.substring(idx + 3));
            return;
        }
        throw new IllegalArgumentException("Bad localized GMT pattern: " + gmtPattern);
    }

    private static String unquote(String s) {
        if (s.indexOf(39) < 0) {
            return s;
        }
        boolean isPrevQuote = false;
        boolean inQuote = false;
        StringBuilder buf = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '\'') {
                if (isPrevQuote) {
                    buf.append(c);
                    isPrevQuote = false;
                } else {
                    isPrevQuote = true;
                }
                inQuote = !inQuote;
            } else {
                isPrevQuote = false;
                buf.append(c);
            }
        }
        return buf.toString();
    }

    private void initGMTOffsetPatterns(String[] gmtOffsetPatterns) {
        int size = GMTOffsetPatternType.values().length;
        if (gmtOffsetPatterns.length >= size) {
            Object[][] gmtOffsetPatternItems = new Object[size][];
            GMTOffsetPatternType[] values = GMTOffsetPatternType.values();
            for (GMTOffsetPatternType t : values) {
                int idx = t.ordinal();
                gmtOffsetPatternItems[idx] = parseOffsetPattern(gmtOffsetPatterns[idx], t.required());
            }
            this._gmtOffsetPatterns = new String[size];
            System.arraycopy(gmtOffsetPatterns, 0, this._gmtOffsetPatterns, 0, size);
            this._gmtOffsetPatternItems = gmtOffsetPatternItems;
            checkAbuttingHoursAndMinutes();
            return;
        }
        throw new IllegalArgumentException("Insufficient number of elements in gmtOffsetPatterns");
    }

    private void checkAbuttingHoursAndMinutes() {
        this._abuttingOffsetHoursAndMinutes = false;
        Object[][] objArr = this._gmtOffsetPatternItems;
        for (Object[] items : objArr) {
            boolean afterH = false;
            for (Object item : items) {
                if (item instanceof GMTOffsetField) {
                    GMTOffsetField fld = (GMTOffsetField) item;
                    if (afterH) {
                        this._abuttingOffsetHoursAndMinutes = true;
                    } else if (fld.getType() == 'H') {
                        afterH = true;
                    }
                } else if (afterH) {
                    break;
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public static class GMTOffsetField {
        final char _type;
        final int _width;

        GMTOffsetField(char type, int width) {
            this._type = type;
            this._width = width;
        }

        /* access modifiers changed from: package-private */
        public char getType() {
            return this._type;
        }

        /* access modifiers changed from: package-private */
        public int getWidth() {
            return this._width;
        }

        static boolean isValid(char type, int width) {
            return width == 1 || width == 2;
        }
    }

    private static Object[] parseOffsetPattern(String pattern, String letters) {
        boolean isPrevQuote = false;
        boolean inQuote = false;
        StringBuilder text = new StringBuilder();
        char itemType = 0;
        int itemLength = 1;
        boolean invalidPattern = false;
        List<Object> items = new ArrayList<>();
        BitSet checkBits = new BitSet(letters.length());
        int i = 0;
        while (true) {
            boolean z = false;
            if (i >= pattern.length()) {
                break;
            }
            char ch = pattern.charAt(i);
            if (ch == '\'') {
                if (!isPrevQuote) {
                    isPrevQuote = true;
                    if (itemType != 0) {
                        if (!GMTOffsetField.isValid(itemType, itemLength)) {
                            invalidPattern = true;
                            break;
                        }
                        items.add(new GMTOffsetField(itemType, itemLength));
                        itemType = 0;
                    }
                } else {
                    text.append(PatternTokenizer.SINGLE_QUOTE);
                    isPrevQuote = false;
                }
                if (!inQuote) {
                    z = true;
                }
                inQuote = z;
            } else {
                isPrevQuote = false;
                if (inQuote) {
                    text.append(ch);
                } else {
                    int patFieldIdx = letters.indexOf(ch);
                    if (patFieldIdx < 0) {
                        if (itemType != 0) {
                            if (!GMTOffsetField.isValid(itemType, itemLength)) {
                                invalidPattern = true;
                                break;
                            }
                            items.add(new GMTOffsetField(itemType, itemLength));
                            itemType = 0;
                        }
                        text.append(ch);
                    } else if (ch == itemType) {
                        itemLength++;
                    } else {
                        if (itemType != 0) {
                            if (!GMTOffsetField.isValid(itemType, itemLength)) {
                                invalidPattern = true;
                                break;
                            }
                            items.add(new GMTOffsetField(itemType, itemLength));
                        } else if (text.length() > 0) {
                            items.add(text.toString());
                            text.setLength(0);
                        }
                        itemType = ch;
                        itemLength = 1;
                        checkBits.set(patFieldIdx);
                    }
                }
            }
            i++;
        }
        if (!invalidPattern) {
            if (itemType == 0) {
                if (text.length() > 0) {
                    items.add(text.toString());
                    text.setLength(0);
                }
            } else if (GMTOffsetField.isValid(itemType, itemLength)) {
                items.add(new GMTOffsetField(itemType, itemLength));
            } else {
                invalidPattern = true;
            }
        }
        if (!invalidPattern && checkBits.cardinality() == letters.length()) {
            return items.toArray(new Object[items.size()]);
        }
        throw new IllegalStateException("Bad localized GMT offset pattern: " + pattern);
    }

    private static String expandOffsetPattern(String offsetHM) {
        int idx_mm = offsetHM.indexOf("mm");
        if (idx_mm >= 0) {
            String sep = ":";
            int idx_H = offsetHM.substring(0, idx_mm).lastIndexOf(DateFormat.HOUR24);
            if (idx_H >= 0) {
                sep = offsetHM.substring(idx_H + 1, idx_mm);
            }
            return offsetHM.substring(0, idx_mm + 2) + sep + "ss" + offsetHM.substring(idx_mm + 2);
        }
        throw new RuntimeException("Bad time zone hour pattern data");
    }

    private static String truncateOffsetPattern(String offsetHM) {
        int idx_mm = offsetHM.indexOf("mm");
        if (idx_mm >= 0) {
            int idx_HH = offsetHM.substring(0, idx_mm).lastIndexOf("HH");
            if (idx_HH >= 0) {
                return offsetHM.substring(0, idx_HH + 2);
            }
            int idx_H = offsetHM.substring(0, idx_mm).lastIndexOf(DateFormat.HOUR24);
            if (idx_H >= 0) {
                return offsetHM.substring(0, idx_H + 1);
            }
            throw new RuntimeException("Bad time zone hour pattern data");
        }
        throw new RuntimeException("Bad time zone hour pattern data");
    }

    private void appendOffsetDigits(StringBuilder buf, int n, int minDigits) {
        int numDigits = n >= 10 ? 2 : 1;
        for (int i = 0; i < minDigits - numDigits; i++) {
            buf.append(this._gmtOffsetDigits[0]);
        }
        if (numDigits == 2) {
            buf.append(this._gmtOffsetDigits[n / 10]);
        }
        buf.append(this._gmtOffsetDigits[n % 10]);
    }

    private TimeZone getTimeZoneForOffset(int offset) {
        if (offset == 0) {
            return TimeZone.getTimeZone(TZID_GMT);
        }
        return ZoneMeta.getCustomTimeZone(offset);
    }

    private int parseOffsetLocalizedGMT(String text, ParsePosition pos, boolean isShort, Output<Boolean> hasDigitOffset) {
        int start = pos.getIndex();
        T t = (T) true;
        int[] parsedLength = {0};
        if (hasDigitOffset != null) {
            hasDigitOffset.value = (T) false;
        }
        int offset = parseOffsetLocalizedGMTPattern(text, start, isShort, parsedLength);
        if (parsedLength[0] > 0) {
            if (hasDigitOffset != null) {
                hasDigitOffset.value = t;
            }
            pos.setIndex(parsedLength[0] + start);
            return offset;
        }
        int offset2 = parseOffsetDefaultLocalizedGMT(text, start, parsedLength);
        if (parsedLength[0] > 0) {
            if (hasDigitOffset != null) {
                hasDigitOffset.value = t;
            }
            pos.setIndex(parsedLength[0] + start);
            return offset2;
        }
        String str = this._gmtZeroFormat;
        if (text.regionMatches(true, start, str, 0, str.length())) {
            pos.setIndex(this._gmtZeroFormat.length() + start);
            return 0;
        }
        String[] strArr = ALT_GMT_STRINGS;
        int i = 0;
        for (int length = strArr.length; i < length; length = length) {
            String defGMTZero = strArr[i];
            if (text.regionMatches(true, start, defGMTZero, 0, defGMTZero.length())) {
                pos.setIndex(defGMTZero.length() + start);
                return 0;
            }
            i++;
        }
        pos.setErrorIndex(start);
        return 0;
    }

    private int parseOffsetLocalizedGMTPattern(String text, int start, boolean isShort, int[] parsedLen) {
        int idx = start;
        int offset = 0;
        boolean parsed = false;
        int len = this._gmtPatternPrefix.length();
        if (len <= 0 || text.regionMatches(true, idx, this._gmtPatternPrefix, 0, len)) {
            idx += len;
            int[] offsetLen = new int[1];
            offset = parseOffsetFields(text, idx, false, offsetLen);
            if (offsetLen[0] != 0) {
                int idx2 = offsetLen[0] + idx;
                int len2 = this._gmtPatternSuffix.length();
                if (len2 <= 0 || text.regionMatches(true, idx2, this._gmtPatternSuffix, 0, len2)) {
                    idx = idx2 + len2;
                    parsed = true;
                } else {
                    idx = idx2;
                }
            }
        }
        parsedLen[0] = parsed ? idx - start : 0;
        return offset;
    }

    private int parseOffsetFields(String text, int start, boolean isShort, int[] parsedLen) {
        int i;
        int offsetS;
        int sign;
        int outLen;
        int tmpLen;
        if (parsedLen != null && parsedLen.length >= 1) {
            parsedLen[0] = 0;
        }
        int offsetM = 0;
        int offsetH = 0;
        int[] fields = {0, 0, 0};
        GMTOffsetPatternType[] gMTOffsetPatternTypeArr = PARSE_GMT_OFFSET_TYPES;
        int length = gMTOffsetPatternTypeArr.length;
        int outLen2 = 0;
        int i2 = 0;
        while (true) {
            i = -1;
            if (i2 >= length) {
                offsetS = 1;
                sign = outLen2;
                outLen = 0;
                break;
            }
            GMTOffsetPatternType gmtPatType = gMTOffsetPatternTypeArr[i2];
            outLen2 = parseOffsetFieldsWithPattern(text, start, this._gmtOffsetPatternItems[gmtPatType.ordinal()], false, fields);
            if (outLen2 > 0) {
                int sign2 = gmtPatType.isPositive() ? 1 : -1;
                offsetH = fields[0];
                offsetM = fields[1];
                offsetS = sign2;
                sign = outLen2;
                outLen = fields[2];
            } else {
                i2++;
                gMTOffsetPatternTypeArr = gMTOffsetPatternTypeArr;
                length = length;
            }
        }
        if (sign > 0 && this._abuttingOffsetHoursAndMinutes) {
            int tmpSign = 1;
            GMTOffsetPatternType[] gMTOffsetPatternTypeArr2 = PARSE_GMT_OFFSET_TYPES;
            int length2 = gMTOffsetPatternTypeArr2.length;
            int tmpLen2 = 0;
            int i3 = 0;
            while (true) {
                if (i3 >= length2) {
                    tmpLen = tmpLen2;
                    break;
                }
                GMTOffsetPatternType gmtPatType2 = gMTOffsetPatternTypeArr2[i3];
                tmpLen2 = parseOffsetFieldsWithPattern(text, start, this._gmtOffsetPatternItems[gmtPatType2.ordinal()], true, fields);
                if (tmpLen2 > 0) {
                    if (gmtPatType2.isPositive()) {
                        i = 1;
                    }
                    tmpSign = i;
                    tmpLen = tmpLen2;
                } else {
                    i3++;
                    gMTOffsetPatternTypeArr2 = gMTOffsetPatternTypeArr2;
                    length2 = length2;
                }
            }
            if (tmpLen > sign) {
                sign = tmpLen;
                offsetS = tmpSign;
                offsetH = fields[0];
                offsetM = fields[1];
                outLen = fields[2];
            }
        }
        if (parsedLen != null && parsedLen.length >= 1) {
            parsedLen[0] = sign;
        }
        if (sign > 0) {
            return ((((offsetH * 60) + offsetM) * 60) + outLen) * 1000 * offsetS;
        }
        return 0;
    }

    private int parseOffsetFieldsWithPattern(String text, int start, Object[] patternItems, boolean forceSingleHourDigit, int[] fields) {
        int i = 2;
        fields[2] = 0;
        fields[1] = 0;
        fields[0] = 0;
        boolean failed = false;
        int offsetM = 0;
        int offsetH = 0;
        int idx = start;
        int[] tmpParsedLen = {0};
        int offsetS = 0;
        int i2 = 0;
        while (true) {
            if (i2 >= patternItems.length) {
                break;
            }
            if (patternItems[i2] instanceof String) {
                String patStr = (String) patternItems[i2];
                int len = patStr.length();
                int patIdx = 0;
                if (i2 == 0) {
                    if (idx < text.length()) {
                        if (!PatternProps.isWhiteSpace(text.codePointAt(idx))) {
                            while (len > 0) {
                                int cp = patStr.codePointAt(patIdx);
                                if (!PatternProps.isWhiteSpace(cp)) {
                                    break;
                                }
                                int cpLen = Character.charCount(cp);
                                len -= cpLen;
                                patIdx += cpLen;
                            }
                        }
                    }
                }
                if (!text.regionMatches(true, idx, patStr, patIdx, len)) {
                    failed = true;
                    break;
                }
                idx += len;
            } else {
                char fieldType = ((GMTOffsetField) patternItems[i2]).getType();
                if (fieldType == 'H') {
                    offsetH = parseOffsetFieldWithLocalizedDigits(text, idx, 1, forceSingleHourDigit ? 1 : i, 0, 23, tmpParsedLen);
                } else if (fieldType == 'm') {
                    offsetM = parseOffsetFieldWithLocalizedDigits(text, idx, 2, 2, 0, 59, tmpParsedLen);
                } else if (fieldType == 's') {
                    offsetS = parseOffsetFieldWithLocalizedDigits(text, idx, 2, 2, 0, 59, tmpParsedLen);
                }
                if (tmpParsedLen[0] == 0) {
                    failed = true;
                    break;
                }
                idx += tmpParsedLen[0];
            }
            i2++;
            i = 2;
        }
        if (failed) {
            return 0;
        }
        fields[0] = offsetH;
        fields[1] = offsetM;
        fields[2] = offsetS;
        return idx - start;
    }

    /* JADX INFO: Multiple debug info for r3v2 int[]: [D('offset' int), D('lenAbut' int[])] */
    private int parseOffsetDefaultLocalizedGMT(String text, int start, int[] parsedLen) {
        int sign;
        int idx;
        int offset = 0;
        int parsed = 0;
        int gmtLen = 0;
        String[] strArr = ALT_GMT_STRINGS;
        int length = strArr.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                break;
            }
            String gmt = strArr[i];
            int len = gmt.length();
            if (text.regionMatches(true, start, gmt, 0, len)) {
                gmtLen = len;
                break;
            }
            i++;
        }
        if (gmtLen != 0) {
            int idx2 = start + gmtLen;
            if (idx2 + 1 < text.length()) {
                char c = text.charAt(idx2);
                if (c == '+') {
                    sign = 1;
                } else if (c == '-') {
                    sign = -1;
                }
                int idx3 = idx2 + 1;
                int[] lenWithSep = {0};
                int offsetWithSep = parseDefaultOffsetFields(text, idx3, DEFAULT_GMT_OFFSET_SEP, lenWithSep);
                if (lenWithSep[0] == text.length() - idx3) {
                    idx = idx3 + lenWithSep[0];
                    offset = offsetWithSep * sign;
                } else {
                    int[] lenAbut = {0};
                    int offsetAbut = parseAbuttingOffsetFields(text, idx3, lenAbut);
                    if (lenWithSep[0] > lenAbut[0]) {
                        offset = offsetWithSep * sign;
                        idx = idx3 + lenWithSep[0];
                    } else {
                        offset = offsetAbut * sign;
                        idx = idx3 + lenAbut[0];
                    }
                }
                parsed = idx - start;
            }
        }
        parsedLen[0] = parsed;
        return offset;
    }

    private int parseDefaultOffsetFields(String text, int start, char separator, int[] parsedLen) {
        int sec;
        int max = text.length();
        int idx = start;
        int[] len = {0};
        int min = 0;
        int hour = parseOffsetFieldWithLocalizedDigits(text, idx, 1, 2, 0, 23, len);
        if (len[0] == 0) {
            sec = 0;
        } else {
            idx += len[0];
            if (idx + 1 < max && text.charAt(idx) == separator) {
                min = parseOffsetFieldWithLocalizedDigits(text, idx + 1, 2, 2, 0, 59, len);
                if (len[0] == 0) {
                    sec = 0;
                } else {
                    idx += len[0] + 1;
                    if (idx + 1 < max && text.charAt(idx) == separator) {
                        int sec2 = parseOffsetFieldWithLocalizedDigits(text, idx + 1, 2, 2, 0, 59, len);
                        if (len[0] == 0) {
                            sec = sec2;
                        } else {
                            idx += len[0] + 1;
                            sec = sec2;
                        }
                    }
                }
            }
            sec = 0;
        }
        if (idx == start) {
            parsedLen[0] = 0;
            return 0;
        }
        parsedLen[0] = idx - start;
        return (3600000 * hour) + (60000 * min) + (sec * 1000);
    }

    private int parseAbuttingOffsetFields(String text, int start, int[] parsedLen) {
        int[] digits = new int[6];
        int[] parsed = new int[6];
        int idx = start;
        int[] len = {0};
        int numDigits = 0;
        int i = 0;
        while (true) {
            if (i >= 6) {
                break;
            }
            digits[i] = parseSingleLocalizedDigit(text, idx, len);
            if (digits[i] < 0) {
                break;
            }
            idx += len[0];
            parsed[i] = idx - start;
            numDigits++;
            i++;
        }
        if (numDigits == 0) {
            parsedLen[0] = 0;
            return 0;
        }
        while (numDigits > 0) {
            int hour = 0;
            int min = 0;
            int sec = 0;
            switch (numDigits) {
                case 1:
                    hour = digits[0];
                    break;
                case 2:
                    hour = (digits[0] * 10) + digits[1];
                    break;
                case 3:
                    hour = digits[0];
                    min = (digits[1] * 10) + digits[2];
                    break;
                case 4:
                    hour = (digits[0] * 10) + digits[1];
                    min = (digits[2] * 10) + digits[3];
                    break;
                case 5:
                    hour = digits[0];
                    min = (digits[1] * 10) + digits[2];
                    sec = (digits[3] * 10) + digits[4];
                    break;
                case 6:
                    hour = (digits[0] * 10) + digits[1];
                    min = (digits[2] * 10) + digits[3];
                    sec = (digits[4] * 10) + digits[5];
                    break;
            }
            if (hour > 23 || min > 59 || sec > 59) {
                numDigits--;
            } else {
                int offset = (3600000 * hour) + (60000 * min) + (sec * 1000);
                parsedLen[0] = parsed[numDigits - 1];
                return offset;
            }
        }
        return 0;
    }

    private int parseOffsetFieldWithLocalizedDigits(String text, int start, int minDigits, int maxDigits, int minVal, int maxVal, int[] parsedLen) {
        int tmpVal;
        parsedLen[0] = 0;
        int decVal = 0;
        int numDigits = 0;
        int idx = start;
        int[] digitLen = {0};
        while (idx < text.length() && numDigits < maxDigits && (digit = parseSingleLocalizedDigit(text, idx, digitLen)) >= 0 && (tmpVal = (decVal * 10) + digit) <= maxVal) {
            decVal = tmpVal;
            numDigits++;
            idx += digitLen[0];
        }
        if (numDigits < minDigits || decVal < minVal) {
            return -1;
        }
        parsedLen[0] = idx - start;
        return decVal;
    }

    private int parseSingleLocalizedDigit(String text, int start, int[] len) {
        int digit = -1;
        len[0] = 0;
        if (start < text.length()) {
            int cp = Character.codePointAt(text, start);
            int i = 0;
            while (true) {
                String[] strArr = this._gmtOffsetDigits;
                if (i >= strArr.length) {
                    break;
                } else if (cp == strArr[i].codePointAt(0)) {
                    digit = i;
                    break;
                } else {
                    i++;
                }
            }
            if (digit < 0) {
                digit = UCharacter.digit(cp);
            }
            if (digit >= 0) {
                len[0] = Character.charCount(cp);
            }
        }
        return digit;
    }

    private static String[] toCodePoints(String str) {
        int len = str.codePointCount(0, str.length());
        String[] codePoints = new String[len];
        int offset = 0;
        for (int i = 0; i < len; i++) {
            int codeLen = Character.charCount(str.codePointAt(offset));
            codePoints[i] = str.substring(offset, offset + codeLen);
            offset += codeLen;
        }
        return codePoints;
    }

    private static int parseOffsetISO8601(String text, ParsePosition pos, boolean extendedOnly, Output<Boolean> hasDigitOffset) {
        int sign;
        if (hasDigitOffset != null) {
            hasDigitOffset.value = (T) false;
        }
        int start = pos.getIndex();
        if (start >= text.length()) {
            pos.setErrorIndex(start);
            return 0;
        }
        char firstChar = text.charAt(start);
        if (Character.toUpperCase(firstChar) == ISO8601_UTC.charAt(0)) {
            pos.setIndex(start + 1);
            return 0;
        }
        if (firstChar == '+') {
            sign = 1;
        } else if (firstChar == '-') {
            sign = -1;
        } else {
            pos.setErrorIndex(start);
            return 0;
        }
        ParsePosition posOffset = new ParsePosition(start + 1);
        int offset = parseAsciiOffsetFields(text, posOffset, DEFAULT_GMT_OFFSET_SEP, OffsetFields.H, OffsetFields.HMS);
        if (posOffset.getErrorIndex() == -1 && !extendedOnly && posOffset.getIndex() - start <= 3) {
            ParsePosition posBasic = new ParsePosition(start + 1);
            int tmpOffset = parseAbuttingAsciiOffsetFields(text, posBasic, OffsetFields.H, OffsetFields.HMS, false);
            if (posBasic.getErrorIndex() == -1 && posBasic.getIndex() > posOffset.getIndex()) {
                offset = tmpOffset;
                posOffset.setIndex(posBasic.getIndex());
            }
        }
        if (posOffset.getErrorIndex() != -1) {
            pos.setErrorIndex(start);
            return 0;
        }
        pos.setIndex(posOffset.getIndex());
        if (hasDigitOffset != null) {
            hasDigitOffset.value = (T) true;
        }
        return sign * offset;
    }

    private static int parseAbuttingAsciiOffsetFields(String text, ParsePosition pos, OffsetFields minFields, OffsetFields maxFields, boolean fixedHourWidth) {
        int start = pos.getIndex();
        int minDigits = ((minFields.ordinal() + 1) * 2) - (!fixedHourWidth ? 1 : 0);
        int[] digits = new int[((maxFields.ordinal() + 1) * 2)];
        int numDigits = 0;
        int idx = start;
        while (true) {
            if (numDigits < digits.length && idx < text.length()) {
                int digit = ASCII_DIGITS.indexOf(text.charAt(idx));
                if (digit < 0) {
                    break;
                }
                digits[numDigits] = digit;
                numDigits++;
                idx++;
            }
        }
        if (fixedHourWidth && (numDigits & 1) != 0) {
            numDigits--;
        }
        if (numDigits < minDigits) {
            pos.setErrorIndex(start);
            return 0;
        }
        int hour = 0;
        int min = 0;
        int sec = 0;
        boolean bParsed = false;
        while (true) {
            if (numDigits >= minDigits) {
                switch (numDigits) {
                    case 1:
                        hour = digits[0];
                        break;
                    case 2:
                        hour = (digits[0] * 10) + digits[1];
                        break;
                    case 3:
                        hour = digits[0];
                        min = (digits[1] * 10) + digits[2];
                        break;
                    case 4:
                        hour = (digits[0] * 10) + digits[1];
                        min = (digits[2] * 10) + digits[3];
                        break;
                    case 5:
                        hour = digits[0];
                        min = (digits[1] * 10) + digits[2];
                        sec = (digits[3] * 10) + digits[4];
                        break;
                    case 6:
                        hour = (digits[0] * 10) + digits[1];
                        min = (digits[2] * 10) + digits[3];
                        sec = (digits[4] * 10) + digits[5];
                        break;
                }
                if (hour > 23 || min > 59 || sec > 59) {
                    numDigits -= fixedHourWidth ? 2 : 1;
                    sec = 0;
                    min = 0;
                    hour = 0;
                } else {
                    bParsed = true;
                }
            }
        }
        if (!bParsed) {
            pos.setErrorIndex(start);
            return 0;
        }
        pos.setIndex(start + numDigits);
        return ((((hour * 60) + min) * 60) + sec) * 1000;
    }

    private static int parseAsciiOffsetFields(String text, ParsePosition pos, char sep, OffsetFields minFields, OffsetFields maxFields) {
        int digit;
        int start = pos.getIndex();
        int[] fieldVal = {0, 0, 0};
        int[] fieldLen = {0, -1, -1};
        int idx = start;
        int fieldIdx = 0;
        while (true) {
            if (idx < text.length() && fieldIdx <= maxFields.ordinal()) {
                char c = text.charAt(idx);
                if (c != sep) {
                    if (fieldLen[fieldIdx] == -1 || (digit = ASCII_DIGITS.indexOf(c)) < 0) {
                        break;
                    }
                    fieldVal[fieldIdx] = (fieldVal[fieldIdx] * 10) + digit;
                    fieldLen[fieldIdx] = fieldLen[fieldIdx] + 1;
                    if (fieldLen[fieldIdx] >= 2) {
                        fieldIdx++;
                    }
                } else if (fieldIdx == 0) {
                    if (fieldLen[0] == 0) {
                        break;
                    }
                    fieldIdx++;
                } else if (fieldLen[fieldIdx] != -1) {
                    break;
                } else {
                    fieldLen[fieldIdx] = 0;
                }
                idx++;
            }
        }
        int offset = 0;
        int parsedLen = 0;
        OffsetFields parsedFields = null;
        if (fieldLen[0] != 0) {
            if (fieldVal[0] > 23) {
                offset = (fieldVal[0] / 10) * 3600000;
                parsedFields = OffsetFields.H;
                parsedLen = 1;
            } else {
                offset = fieldVal[0] * 3600000;
                parsedLen = fieldLen[0];
                parsedFields = OffsetFields.H;
                if (fieldLen[1] == 2 && fieldVal[1] <= 59) {
                    offset += fieldVal[1] * 60000;
                    parsedLen += fieldLen[1] + 1;
                    parsedFields = OffsetFields.HM;
                    if (fieldLen[2] == 2 && fieldVal[2] <= 59) {
                        offset += fieldVal[2] * 1000;
                        parsedLen += fieldLen[2] + 1;
                        parsedFields = OffsetFields.HMS;
                    }
                }
            }
        }
        if (parsedFields == null || parsedFields.ordinal() < minFields.ordinal()) {
            pos.setErrorIndex(start);
            return 0;
        }
        pos.setIndex(start + parsedLen);
        return offset;
    }

    private static String parseZoneID(String text, ParsePosition pos) {
        if (ZONE_ID_TRIE == null) {
            synchronized (TimeZoneFormat.class) {
                if (ZONE_ID_TRIE == null) {
                    TextTrieMap<String> trie = new TextTrieMap<>(true);
                    String[] ids = TimeZone.getAvailableIDs();
                    for (String id : ids) {
                        trie.put(id, id);
                    }
                    ZONE_ID_TRIE = trie;
                }
            }
        }
        TextTrieMap.Output trieOutput = new TextTrieMap.Output();
        Iterator<String> itr = ZONE_ID_TRIE.get(text, pos.getIndex(), trieOutput);
        if (itr != null) {
            String resolvedID = itr.next();
            pos.setIndex(pos.getIndex() + trieOutput.matchLength);
            return resolvedID;
        }
        pos.setErrorIndex(pos.getIndex());
        return null;
    }

    private static String parseShortZoneID(String text, ParsePosition pos) {
        if (SHORT_ZONE_ID_TRIE == null) {
            synchronized (TimeZoneFormat.class) {
                if (SHORT_ZONE_ID_TRIE == null) {
                    TextTrieMap<String> trie = new TextTrieMap<>(true);
                    for (String id : TimeZone.getAvailableIDs(TimeZone.SystemTimeZoneType.CANONICAL, null, null)) {
                        String shortID = ZoneMeta.getShortID(id);
                        if (shortID != null) {
                            trie.put(shortID, id);
                        }
                    }
                    trie.put(UNKNOWN_SHORT_ZONE_ID, "Etc/Unknown");
                    SHORT_ZONE_ID_TRIE = trie;
                }
            }
        }
        TextTrieMap.Output trieOutput = new TextTrieMap.Output();
        Iterator<String> itr = SHORT_ZONE_ID_TRIE.get(text, pos.getIndex(), trieOutput);
        if (itr != null) {
            String resolvedID = itr.next();
            pos.setIndex(pos.getIndex() + trieOutput.matchLength);
            return resolvedID;
        }
        pos.setErrorIndex(pos.getIndex());
        return null;
    }

    private String parseExemplarLocation(String text, ParsePosition pos) {
        int startIdx = pos.getIndex();
        int parsedPos = -1;
        String tzID = null;
        Collection<TimeZoneNames.MatchInfo> exemplarMatches = this._tznames.find(text, startIdx, EnumSet.of(TimeZoneNames.NameType.EXEMPLAR_LOCATION));
        if (exemplarMatches != null) {
            TimeZoneNames.MatchInfo exemplarMatch = null;
            for (TimeZoneNames.MatchInfo match : exemplarMatches) {
                if (match.matchLength() + startIdx > parsedPos) {
                    exemplarMatch = match;
                    parsedPos = startIdx + match.matchLength();
                }
            }
            if (exemplarMatch != null) {
                tzID = getTimeZoneID(exemplarMatch.tzID(), exemplarMatch.mzID());
                pos.setIndex(parsedPos);
            }
        }
        if (tzID == null) {
            pos.setErrorIndex(startIdx);
        }
        return tzID;
    }

    /* access modifiers changed from: private */
    public static class TimeZoneFormatCache extends SoftCache<ULocale, TimeZoneFormat, ULocale> {
        private TimeZoneFormatCache() {
        }

        /* synthetic */ TimeZoneFormatCache(AnonymousClass1 x0) {
            this();
        }

        /* access modifiers changed from: protected */
        public TimeZoneFormat createInstance(ULocale key, ULocale data) {
            TimeZoneFormat fmt = new TimeZoneFormat(data);
            fmt.freeze();
            return fmt;
        }
    }

    private void writeObject(ObjectOutputStream oos) throws IOException {
        ObjectOutputStream.PutField fields = oos.putFields();
        fields.put("_locale", this._locale);
        fields.put("_tznames", this._tznames);
        fields.put("_gmtPattern", this._gmtPattern);
        fields.put("_gmtOffsetPatterns", this._gmtOffsetPatterns);
        fields.put("_gmtOffsetDigits", this._gmtOffsetDigits);
        fields.put("_gmtZeroFormat", this._gmtZeroFormat);
        fields.put("_parseAllStyles", this._parseAllStyles);
        oos.writeFields();
    }

    private void readObject(ObjectInputStream ois) throws ClassNotFoundException, IOException {
        ObjectInputStream.GetField fields = ois.readFields();
        this._locale = (ULocale) fields.get("_locale", (Object) null);
        if (this._locale != null) {
            this._tznames = (TimeZoneNames) fields.get("_tznames", (Object) null);
            if (this._tznames != null) {
                this._gmtPattern = (String) fields.get("_gmtPattern", (Object) null);
                if (this._gmtPattern != null) {
                    String[] tmpGmtOffsetPatterns = (String[]) fields.get("_gmtOffsetPatterns", (Object) null);
                    if (tmpGmtOffsetPatterns == null) {
                        throw new InvalidObjectException("Missing field: gmtOffsetPatterns");
                    } else if (tmpGmtOffsetPatterns.length >= 4) {
                        this._gmtOffsetPatterns = new String[6];
                        if (tmpGmtOffsetPatterns.length == 4) {
                            for (int i = 0; i < 4; i++) {
                                this._gmtOffsetPatterns[i] = tmpGmtOffsetPatterns[i];
                            }
                            this._gmtOffsetPatterns[GMTOffsetPatternType.POSITIVE_H.ordinal()] = truncateOffsetPattern(this._gmtOffsetPatterns[GMTOffsetPatternType.POSITIVE_HM.ordinal()]);
                            this._gmtOffsetPatterns[GMTOffsetPatternType.NEGATIVE_H.ordinal()] = truncateOffsetPattern(this._gmtOffsetPatterns[GMTOffsetPatternType.NEGATIVE_HM.ordinal()]);
                        } else {
                            this._gmtOffsetPatterns = tmpGmtOffsetPatterns;
                        }
                        this._gmtOffsetDigits = (String[]) fields.get("_gmtOffsetDigits", (Object) null);
                        String[] strArr = this._gmtOffsetDigits;
                        if (strArr == null) {
                            throw new InvalidObjectException("Missing field: gmtOffsetDigits");
                        } else if (strArr.length == 10) {
                            this._gmtZeroFormat = (String) fields.get("_gmtZeroFormat", (Object) null);
                            if (this._gmtZeroFormat != null) {
                                this._parseAllStyles = fields.get("_parseAllStyles", false);
                                if (!fields.defaulted("_parseAllStyles")) {
                                    TimeZoneNames timeZoneNames = this._tznames;
                                    if (timeZoneNames instanceof TimeZoneNamesImpl) {
                                        this._tznames = TimeZoneNames.getInstance(this._locale);
                                        this._gnames = null;
                                    } else {
                                        this._gnames = new TimeZoneGenericNames(this._locale, timeZoneNames);
                                    }
                                    initGMTPattern(this._gmtPattern);
                                    initGMTOffsetPatterns(this._gmtOffsetPatterns);
                                    return;
                                }
                                throw new InvalidObjectException("Missing field: parseAllStyles");
                            }
                            throw new InvalidObjectException("Missing field: gmtZeroFormat");
                        } else {
                            throw new InvalidObjectException("Incompatible field: gmtOffsetDigits");
                        }
                    } else {
                        throw new InvalidObjectException("Incompatible field: gmtOffsetPatterns");
                    }
                } else {
                    throw new InvalidObjectException("Missing field: gmtPattern");
                }
            } else {
                throw new InvalidObjectException("Missing field: tznames");
            }
        } else {
            throw new InvalidObjectException("Missing field: locale");
        }
    }

    @Override // android.icu.util.Freezable
    public boolean isFrozen() {
        return this._frozen;
    }

    @Override // android.icu.util.Freezable
    public TimeZoneFormat freeze() {
        this._frozen = true;
        return this;
    }

    @Override // android.icu.util.Freezable
    public TimeZoneFormat cloneAsThawed() {
        TimeZoneFormat copy = (TimeZoneFormat) super.clone();
        copy._frozen = false;
        return copy;
    }
}
