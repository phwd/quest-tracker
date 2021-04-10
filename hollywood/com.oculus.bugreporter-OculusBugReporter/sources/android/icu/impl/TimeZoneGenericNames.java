package android.icu.impl;

import android.icu.impl.TextTrieMap;
import android.icu.text.LocaleDisplayNames;
import android.icu.text.TimeZoneFormat;
import android.icu.text.TimeZoneNames;
import android.icu.util.BasicTimeZone;
import android.icu.util.Freezable;
import android.icu.util.Output;
import android.icu.util.TimeZone;
import android.icu.util.TimeZoneTransition;
import android.icu.util.ULocale;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.lang.ref.WeakReference;
import java.text.MessageFormat;
import java.util.Collection;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.MissingResourceException;
import java.util.concurrent.ConcurrentHashMap;

public class TimeZoneGenericNames implements Serializable, Freezable<TimeZoneGenericNames> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final long DST_CHECK_RANGE = 15897600000L;
    private static Cache GENERIC_NAMES_CACHE = new Cache(null);
    private static final TimeZoneNames.NameType[] GENERIC_NON_LOCATION_TYPES = {TimeZoneNames.NameType.LONG_GENERIC, TimeZoneNames.NameType.SHORT_GENERIC};
    private static final long serialVersionUID = 2729910342063468417L;
    private volatile transient boolean _frozen;
    private transient ConcurrentHashMap<String, String> _genericLocationNamesMap;
    private transient ConcurrentHashMap<String, String> _genericPartialLocationNamesMap;
    private transient TextTrieMap<NameInfo> _gnamesTrie;
    private transient boolean _gnamesTrieFullyLoaded;
    private final ULocale _locale;
    private transient WeakReference<LocaleDisplayNames> _localeDisplayNamesRef;
    private transient MessageFormat[] _patternFormatters;
    private transient String _region;
    private TimeZoneNames _tznames;

    /* synthetic */ TimeZoneGenericNames(ULocale x0, AnonymousClass1 x1) {
        this(x0);
    }

    public enum GenericNameType {
        LOCATION("LONG", "SHORT"),
        LONG(new String[0]),
        SHORT(new String[0]);
        
        String[] _fallbackTypeOf;

        private GenericNameType(String... fallbackTypeOf) {
            this._fallbackTypeOf = fallbackTypeOf;
        }

        public boolean isFallbackTypeOf(GenericNameType type) {
            String typeStr = type.toString();
            for (String t : this._fallbackTypeOf) {
                if (t.equals(typeStr)) {
                    return true;
                }
            }
            return false;
        }
    }

    public enum Pattern {
        REGION_FORMAT("regionFormat", "({0})"),
        FALLBACK_FORMAT("fallbackFormat", "{1} ({0})");
        
        String _defaultVal;
        String _key;

        private Pattern(String key, String defaultVal) {
            this._key = key;
            this._defaultVal = defaultVal;
        }

        /* access modifiers changed from: package-private */
        public String key() {
            return this._key;
        }

        /* access modifiers changed from: package-private */
        public String defaultValue() {
            return this._defaultVal;
        }
    }

    public TimeZoneGenericNames(ULocale locale, TimeZoneNames tznames) {
        this._locale = locale;
        this._tznames = tznames;
        init();
    }

    private void init() {
        if (this._tznames == null) {
            this._tznames = TimeZoneNames.getInstance(this._locale);
        }
        this._genericLocationNamesMap = new ConcurrentHashMap<>();
        this._genericPartialLocationNamesMap = new ConcurrentHashMap<>();
        this._gnamesTrie = new TextTrieMap<>(true);
        this._gnamesTrieFullyLoaded = false;
        String tzCanonicalID = ZoneMeta.getCanonicalCLDRID(TimeZone.getDefault());
        if (tzCanonicalID != null) {
            loadStrings(tzCanonicalID);
        }
    }

    private TimeZoneGenericNames(ULocale locale) {
        this(locale, (TimeZoneNames) null);
    }

    public static TimeZoneGenericNames getInstance(ULocale locale) {
        return (TimeZoneGenericNames) GENERIC_NAMES_CACHE.getInstance(locale.getBaseName(), locale);
    }

    public String getDisplayName(TimeZone tz, GenericNameType type, long date) {
        String tzCanonicalID;
        int i = AnonymousClass1.$SwitchMap$android$icu$impl$TimeZoneGenericNames$GenericNameType[type.ordinal()];
        if (i == 1) {
            String tzCanonicalID2 = ZoneMeta.getCanonicalCLDRID(tz);
            if (tzCanonicalID2 != null) {
                return getGenericLocationName(tzCanonicalID2);
            }
            return null;
        } else if (i != 2 && i != 3) {
            return null;
        } else {
            String name = formatGenericNonLocationName(tz, type, date);
            if (name != null || (tzCanonicalID = ZoneMeta.getCanonicalCLDRID(tz)) == null) {
                return name;
            }
            return getGenericLocationName(tzCanonicalID);
        }
    }

    public String getGenericLocationName(String canonicalTzID) {
        if (canonicalTzID == null || canonicalTzID.length() == 0) {
            return null;
        }
        String name = this._genericLocationNamesMap.get(canonicalTzID);
        if (name == null) {
            Output<Boolean> isPrimary = new Output<>();
            String countryCode = ZoneMeta.getCanonicalCountry(canonicalTzID, isPrimary);
            if (countryCode != null) {
                if (isPrimary.value.booleanValue()) {
                    String country = getLocaleDisplayNames().regionDisplayName(countryCode);
                    name = formatPattern(Pattern.REGION_FORMAT, country);
                } else {
                    String city = this._tznames.getExemplarLocationName(canonicalTzID);
                    name = formatPattern(Pattern.REGION_FORMAT, city);
                }
            }
            if (name == null) {
                this._genericLocationNamesMap.putIfAbsent(canonicalTzID.intern(), "");
            } else {
                synchronized (this) {
                    String canonicalTzID2 = canonicalTzID.intern();
                    String tmp = this._genericLocationNamesMap.putIfAbsent(canonicalTzID2, name.intern());
                    if (tmp == null) {
                        this._gnamesTrie.put(name, new NameInfo(canonicalTzID2, GenericNameType.LOCATION));
                    } else {
                        name = tmp;
                    }
                }
            }
            return name;
        } else if (name.length() == 0) {
            return null;
        } else {
            return name;
        }
    }

    public TimeZoneGenericNames setFormatPattern(Pattern patType, String patStr) {
        if (!isFrozen()) {
            if (!this._genericLocationNamesMap.isEmpty()) {
                this._genericLocationNamesMap = new ConcurrentHashMap<>();
            }
            if (!this._genericPartialLocationNamesMap.isEmpty()) {
                this._genericPartialLocationNamesMap = new ConcurrentHashMap<>();
            }
            this._gnamesTrie = null;
            this._gnamesTrieFullyLoaded = false;
            if (this._patternFormatters == null) {
                this._patternFormatters = new MessageFormat[Pattern.values().length];
            }
            this._patternFormatters[patType.ordinal()] = new MessageFormat(patStr);
            return this;
        }
        throw new UnsupportedOperationException("Attempt to modify frozen object");
    }

    private String formatGenericNonLocationName(TimeZone tz, GenericNameType type, long date) {
        String mzID;
        int[] offsets;
        String mzName;
        String tzID = ZoneMeta.getCanonicalCLDRID(tz);
        if (tzID == null) {
            return null;
        }
        TimeZoneNames.NameType nameType = type == GenericNameType.LONG ? TimeZoneNames.NameType.LONG_GENERIC : TimeZoneNames.NameType.SHORT_GENERIC;
        String name = this._tznames.getTimeZoneDisplayName(tzID, nameType);
        if (name != null || (mzID = this._tznames.getMetaZoneID(tzID, date)) == null) {
            return name;
        }
        boolean useStandard = false;
        int[] offsets2 = {0, 0};
        tz.getOffset(date, false, offsets2);
        if (offsets2[1] == 0) {
            useStandard = true;
            if (tz instanceof BasicTimeZone) {
                BasicTimeZone btz = (BasicTimeZone) tz;
                TimeZoneTransition before = btz.getPreviousTransition(date, true);
                if (before == null || date - before.getTime() >= DST_CHECK_RANGE || before.getFrom().getDSTSavings() == 0) {
                    TimeZoneTransition after = btz.getNextTransition(date, false);
                    if (!(after == null || after.getTime() - date >= DST_CHECK_RANGE || after.getTo().getDSTSavings() == 0)) {
                        useStandard = false;
                    }
                } else {
                    useStandard = false;
                }
                offsets = offsets2;
            } else {
                int[] tmpOffsets = new int[2];
                offsets = offsets2;
                tz.getOffset(date - DST_CHECK_RANGE, false, tmpOffsets);
                if (tmpOffsets[1] != 0) {
                    useStandard = false;
                } else {
                    tz.getOffset(date + DST_CHECK_RANGE, false, tmpOffsets);
                    if (tmpOffsets[1] != 0) {
                        useStandard = false;
                    }
                }
            }
        } else {
            offsets = offsets2;
        }
        if (useStandard) {
            String stdName = this._tznames.getDisplayName(tzID, nameType == TimeZoneNames.NameType.LONG_GENERIC ? TimeZoneNames.NameType.LONG_STANDARD : TimeZoneNames.NameType.SHORT_STANDARD, date);
            if (stdName != null) {
                name = stdName;
                if (stdName.equalsIgnoreCase(this._tznames.getMetaZoneDisplayName(mzID, nameType))) {
                    name = null;
                }
            }
        }
        if (name != null || (mzName = this._tznames.getMetaZoneDisplayName(mzID, nameType)) == null) {
            return name;
        }
        String goldenID = this._tznames.getReferenceZoneID(mzID, getTargetRegion());
        if (goldenID == null || goldenID.equals(tzID)) {
            return mzName;
        }
        int[] offsets1 = {0, 0};
        boolean z = true;
        TimeZone.getFrozenTimeZone(goldenID).getOffset(((long) offsets[0]) + date + ((long) offsets[1]), true, offsets1);
        if (offsets[0] == offsets1[0] && offsets[1] == offsets1[1]) {
            return mzName;
        }
        if (nameType != TimeZoneNames.NameType.LONG_GENERIC) {
            z = false;
        }
        return getPartialLocationName(tzID, mzID, z, mzName);
    }

    private synchronized String formatPattern(Pattern pat, String... args) {
        int idx;
        String patText;
        if (this._patternFormatters == null) {
            this._patternFormatters = new MessageFormat[Pattern.values().length];
        }
        idx = pat.ordinal();
        if (this._patternFormatters[idx] == null) {
            try {
                patText = ((ICUResourceBundle) ICUResourceBundle.getBundleInstance(ICUData.ICU_ZONE_BASE_NAME, this._locale)).getStringWithFallback("zoneStrings/" + pat.key());
            } catch (MissingResourceException e) {
                patText = pat.defaultValue();
            }
            this._patternFormatters[idx] = new MessageFormat(patText);
        }
        return this._patternFormatters[idx].format(args);
    }

    private synchronized LocaleDisplayNames getLocaleDisplayNames() {
        LocaleDisplayNames locNames;
        locNames = null;
        if (this._localeDisplayNamesRef != null) {
            locNames = this._localeDisplayNamesRef.get();
        }
        if (locNames == null) {
            locNames = LocaleDisplayNames.getInstance(this._locale);
            this._localeDisplayNamesRef = new WeakReference<>(locNames);
        }
        return locNames;
    }

    private synchronized void loadStrings(String tzCanonicalID) {
        if (tzCanonicalID != null) {
            if (tzCanonicalID.length() != 0) {
                getGenericLocationName(tzCanonicalID);
                for (String mzID : this._tznames.getAvailableMetaZoneIDs(tzCanonicalID)) {
                    if (!tzCanonicalID.equals(this._tznames.getReferenceZoneID(mzID, getTargetRegion()))) {
                        TimeZoneNames.NameType[] nameTypeArr = GENERIC_NON_LOCATION_TYPES;
                        int length = nameTypeArr.length;
                        for (int i = 0; i < length; i++) {
                            TimeZoneNames.NameType genNonLocType = nameTypeArr[i];
                            String mzGenName = this._tznames.getMetaZoneDisplayName(mzID, genNonLocType);
                            if (mzGenName != null) {
                                getPartialLocationName(tzCanonicalID, mzID, genNonLocType == TimeZoneNames.NameType.LONG_GENERIC, mzGenName);
                            }
                        }
                    }
                }
            }
        }
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

    private String getPartialLocationName(String tzID, String mzID, boolean isLong, String mzDisplayName) {
        String location;
        String key = tzID + "&" + mzID + "#" + (isLong ? "L" : "S");
        String name = this._genericPartialLocationNamesMap.get(key);
        if (name != null) {
            return name;
        }
        String countryCode = ZoneMeta.getCanonicalCountry(tzID);
        if (countryCode == null) {
            location = this._tznames.getExemplarLocationName(tzID);
            if (location == null) {
                location = tzID;
            }
        } else if (tzID.equals(this._tznames.getReferenceZoneID(mzID, countryCode))) {
            location = getLocaleDisplayNames().regionDisplayName(countryCode);
        } else {
            location = this._tznames.getExemplarLocationName(tzID);
        }
        String name2 = formatPattern(Pattern.FALLBACK_FORMAT, location, mzDisplayName);
        synchronized (this) {
            String tmp = this._genericPartialLocationNamesMap.putIfAbsent(key.intern(), name2.intern());
            if (tmp == null) {
                this._gnamesTrie.put(name2, new NameInfo(tzID.intern(), isLong ? GenericNameType.LONG : GenericNameType.SHORT));
            } else {
                name2 = tmp;
            }
        }
        return name2;
    }

    /* access modifiers changed from: private */
    public static class NameInfo {
        final GenericNameType type;
        final String tzID;

        NameInfo(String tzID2, GenericNameType type2) {
            this.tzID = tzID2;
            this.type = type2;
        }
    }

    public static class GenericMatchInfo {
        final int matchLength;
        final GenericNameType nameType;
        final TimeZoneFormat.TimeType timeType;
        final String tzID;

        /* synthetic */ GenericMatchInfo(GenericNameType x0, String x1, int x2, AnonymousClass1 x3) {
            this(x0, x1, x2);
        }

        /* synthetic */ GenericMatchInfo(GenericNameType x0, String x1, int x2, TimeZoneFormat.TimeType x3, AnonymousClass1 x4) {
            this(x0, x1, x2, x3);
        }

        private GenericMatchInfo(GenericNameType nameType2, String tzID2, int matchLength2) {
            this(nameType2, tzID2, matchLength2, TimeZoneFormat.TimeType.UNKNOWN);
        }

        private GenericMatchInfo(GenericNameType nameType2, String tzID2, int matchLength2, TimeZoneFormat.TimeType timeType2) {
            this.nameType = nameType2;
            this.tzID = tzID2;
            this.matchLength = matchLength2;
            this.timeType = timeType2;
        }

        public GenericNameType nameType() {
            return this.nameType;
        }

        public String tzID() {
            return this.tzID;
        }

        public TimeZoneFormat.TimeType timeType() {
            return this.timeType;
        }

        public int matchLength() {
            return this.matchLength;
        }
    }

    /* access modifiers changed from: private */
    public static class GenericNameSearchHandler implements TextTrieMap.ResultHandler<NameInfo> {
        private Collection<GenericMatchInfo> _matches;
        private int _maxMatchLen;
        private EnumSet<GenericNameType> _types;

        GenericNameSearchHandler(EnumSet<GenericNameType> types) {
            this._types = types;
        }

        @Override // android.icu.impl.TextTrieMap.ResultHandler
        public boolean handlePrefixMatch(int matchLength, Iterator<NameInfo> values) {
            while (values.hasNext()) {
                NameInfo info = values.next();
                EnumSet<GenericNameType> enumSet = this._types;
                if (enumSet == null || enumSet.contains(info.type)) {
                    GenericMatchInfo matchInfo = new GenericMatchInfo(info.type, info.tzID, matchLength, (AnonymousClass1) null);
                    if (this._matches == null) {
                        this._matches = new LinkedList();
                    }
                    this._matches.add(matchInfo);
                    if (matchLength > this._maxMatchLen) {
                        this._maxMatchLen = matchLength;
                    }
                }
            }
            return true;
        }

        public Collection<GenericMatchInfo> getMatches() {
            return this._matches;
        }

        public int getMaxMatchLen() {
            return this._maxMatchLen;
        }

        public void resetResults() {
            this._matches = null;
            this._maxMatchLen = 0;
        }
    }

    public GenericMatchInfo findBestMatch(String text, int start, EnumSet<GenericNameType> genericTypes) {
        if (text == null || text.length() == 0 || start < 0 || start >= text.length()) {
            throw new IllegalArgumentException("bad input text or range");
        }
        GenericMatchInfo bestMatch = null;
        Collection<TimeZoneNames.MatchInfo> tznamesMatches = findTimeZoneNames(text, start, genericTypes);
        if (tznamesMatches != null) {
            TimeZoneNames.MatchInfo longestMatch = null;
            for (TimeZoneNames.MatchInfo match : tznamesMatches) {
                if (longestMatch == null || match.matchLength() > longestMatch.matchLength()) {
                    longestMatch = match;
                }
            }
            if (longestMatch != null) {
                bestMatch = createGenericMatchInfo(longestMatch);
                if (bestMatch.matchLength() == text.length() - start && bestMatch.timeType != TimeZoneFormat.TimeType.STANDARD) {
                    return bestMatch;
                }
            }
        }
        Collection<GenericMatchInfo> localMatches = findLocal(text, start, genericTypes);
        if (localMatches != null) {
            for (GenericMatchInfo match2 : localMatches) {
                if (bestMatch == null || match2.matchLength() >= bestMatch.matchLength()) {
                    bestMatch = match2;
                }
            }
        }
        return bestMatch;
    }

    public Collection<GenericMatchInfo> find(String text, int start, EnumSet<GenericNameType> genericTypes) {
        if (text == null || text.length() == 0 || start < 0 || start >= text.length()) {
            throw new IllegalArgumentException("bad input text or range");
        }
        Collection<GenericMatchInfo> results = findLocal(text, start, genericTypes);
        Collection<TimeZoneNames.MatchInfo> tznamesMatches = findTimeZoneNames(text, start, genericTypes);
        if (tznamesMatches != null) {
            for (TimeZoneNames.MatchInfo match : tznamesMatches) {
                if (results == null) {
                    results = new LinkedList();
                }
                results.add(createGenericMatchInfo(match));
            }
        }
        return results;
    }

    private GenericMatchInfo createGenericMatchInfo(TimeZoneNames.MatchInfo matchInfo) {
        GenericNameType nameType;
        String tzID;
        TimeZoneFormat.TimeType timeType = TimeZoneFormat.TimeType.UNKNOWN;
        int i = AnonymousClass1.$SwitchMap$android$icu$text$TimeZoneNames$NameType[matchInfo.nameType().ordinal()];
        if (i == 1) {
            nameType = GenericNameType.LONG;
            timeType = TimeZoneFormat.TimeType.STANDARD;
        } else if (i == 2) {
            nameType = GenericNameType.LONG;
        } else if (i == 3) {
            nameType = GenericNameType.SHORT;
            timeType = TimeZoneFormat.TimeType.STANDARD;
        } else if (i == 4) {
            nameType = GenericNameType.SHORT;
        } else {
            throw new IllegalArgumentException("Unexpected MatchInfo name type - " + ((Object) matchInfo.nameType()));
        }
        String tzID2 = matchInfo.tzID();
        if (tzID2 == null) {
            tzID = this._tznames.getReferenceZoneID(matchInfo.mzID(), getTargetRegion());
        } else {
            tzID = tzID2;
        }
        return new GenericMatchInfo(nameType, tzID, matchInfo.matchLength(), timeType, null);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: android.icu.impl.TimeZoneGenericNames$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$android$icu$impl$TimeZoneGenericNames$GenericNameType = new int[GenericNameType.values().length];
        static final /* synthetic */ int[] $SwitchMap$android$icu$text$TimeZoneNames$NameType = new int[TimeZoneNames.NameType.values().length];

        static {
            try {
                $SwitchMap$android$icu$text$TimeZoneNames$NameType[TimeZoneNames.NameType.LONG_STANDARD.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$android$icu$text$TimeZoneNames$NameType[TimeZoneNames.NameType.LONG_GENERIC.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$android$icu$text$TimeZoneNames$NameType[TimeZoneNames.NameType.SHORT_STANDARD.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$android$icu$text$TimeZoneNames$NameType[TimeZoneNames.NameType.SHORT_GENERIC.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$android$icu$impl$TimeZoneGenericNames$GenericNameType[GenericNameType.LOCATION.ordinal()] = 1;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$android$icu$impl$TimeZoneGenericNames$GenericNameType[GenericNameType.LONG.ordinal()] = 2;
            } catch (NoSuchFieldError e6) {
            }
            try {
                $SwitchMap$android$icu$impl$TimeZoneGenericNames$GenericNameType[GenericNameType.SHORT.ordinal()] = 3;
            } catch (NoSuchFieldError e7) {
            }
        }
    }

    private Collection<TimeZoneNames.MatchInfo> findTimeZoneNames(String text, int start, EnumSet<GenericNameType> types) {
        EnumSet<TimeZoneNames.NameType> nameTypes = EnumSet.noneOf(TimeZoneNames.NameType.class);
        if (types.contains(GenericNameType.LONG)) {
            nameTypes.add(TimeZoneNames.NameType.LONG_GENERIC);
            nameTypes.add(TimeZoneNames.NameType.LONG_STANDARD);
        }
        if (types.contains(GenericNameType.SHORT)) {
            nameTypes.add(TimeZoneNames.NameType.SHORT_GENERIC);
            nameTypes.add(TimeZoneNames.NameType.SHORT_STANDARD);
        }
        if (!nameTypes.isEmpty()) {
            return this._tznames.find(text, start, nameTypes);
        }
        return null;
    }

    private synchronized Collection<GenericMatchInfo> findLocal(String text, int start, EnumSet<GenericNameType> types) {
        GenericNameSearchHandler handler = new GenericNameSearchHandler(types);
        this._gnamesTrie.find(text, start, handler);
        if (handler.getMaxMatchLen() != text.length() - start) {
            if (!this._gnamesTrieFullyLoaded) {
                for (String tzID : TimeZone.getAvailableIDs(TimeZone.SystemTimeZoneType.CANONICAL, null, null)) {
                    loadStrings(tzID);
                }
                this._gnamesTrieFullyLoaded = true;
                handler.resetResults();
                this._gnamesTrie.find(text, start, handler);
                return handler.getMatches();
            }
        }
        return handler.getMatches();
    }

    /* access modifiers changed from: private */
    public static class Cache extends SoftCache<String, TimeZoneGenericNames, ULocale> {
        private Cache() {
        }

        /* synthetic */ Cache(AnonymousClass1 x0) {
            this();
        }

        /* access modifiers changed from: protected */
        public TimeZoneGenericNames createInstance(String key, ULocale data) {
            return new TimeZoneGenericNames(data, (AnonymousClass1) null).freeze();
        }
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        init();
    }

    @Override // android.icu.util.Freezable
    public boolean isFrozen() {
        return this._frozen;
    }

    @Override // android.icu.util.Freezable
    public TimeZoneGenericNames freeze() {
        this._frozen = true;
        return this;
    }

    @Override // android.icu.util.Freezable
    public TimeZoneGenericNames cloneAsThawed() {
        TimeZoneGenericNames copy = null;
        try {
            copy = (TimeZoneGenericNames) super.clone();
            copy._frozen = false;
            return copy;
        } catch (Throwable th) {
            return copy;
        }
    }
}
