package android.icu.impl;

import android.icu.impl.TextTrieMap;
import android.icu.impl.UResource;
import android.icu.text.TimeZoneNames;
import android.icu.util.TimeZone;
import android.icu.util.ULocale;
import android.icu.util.UResourceBundle;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;
import libcore.icu.RelativeDateTimeFormatter;

public class TimeZoneNamesImpl extends TimeZoneNames {
    private static final Pattern LOC_EXCLUSION_PATTERN = Pattern.compile("Etc/.*|SystemV/.*|.*/Riyadh8[7-9]");
    private static volatile Set<String> METAZONE_IDS = null;
    private static final String MZ_PREFIX = "meta:";
    private static final MZ2TZsCache MZ_TO_TZS_CACHE = new MZ2TZsCache();
    private static final TZ2MZsCache TZ_TO_MZS_CACHE = new TZ2MZsCache();
    private static final String ZONE_STRINGS_BUNDLE = "zoneStrings";
    private static final long serialVersionUID = -2179814848495897472L;
    private transient ConcurrentHashMap<String, ZNames> _mzNamesMap;
    private transient boolean _namesFullyLoaded;
    private transient TextTrieMap<NameInfo> _namesTrie;
    private transient boolean _namesTrieFullyLoaded;
    private transient ConcurrentHashMap<String, ZNames> _tzNamesMap;
    private transient ICUResourceBundle _zoneStrings;

    public TimeZoneNamesImpl(ULocale locale) {
        initialize(locale);
    }

    @Override // android.icu.text.TimeZoneNames
    public Set<String> getAvailableMetaZoneIDs() {
        return _getAvailableMetaZoneIDs();
    }

    static Set<String> _getAvailableMetaZoneIDs() {
        if (METAZONE_IDS == null) {
            synchronized (TimeZoneNamesImpl.class) {
                if (METAZONE_IDS == null) {
                    METAZONE_IDS = Collections.unmodifiableSet(UResourceBundle.getBundleInstance(ICUData.ICU_BASE_NAME, "metaZones").get("mapTimezones").keySet());
                }
            }
        }
        return METAZONE_IDS;
    }

    @Override // android.icu.text.TimeZoneNames
    public Set<String> getAvailableMetaZoneIDs(String tzID) {
        return _getAvailableMetaZoneIDs(tzID);
    }

    static Set<String> _getAvailableMetaZoneIDs(String tzID) {
        if (tzID == null || tzID.length() == 0) {
            return Collections.emptySet();
        }
        List<MZMapEntry> maps = (List) TZ_TO_MZS_CACHE.getInstance(tzID, tzID);
        if (maps.isEmpty()) {
            return Collections.emptySet();
        }
        Set<String> mzIDs = new HashSet<>(maps.size());
        for (MZMapEntry map : maps) {
            mzIDs.add(map.mzID());
        }
        return Collections.unmodifiableSet(mzIDs);
    }

    @Override // android.icu.text.TimeZoneNames
    public String getMetaZoneID(String tzID, long date) {
        return _getMetaZoneID(tzID, date);
    }

    static String _getMetaZoneID(String tzID, long date) {
        if (tzID == null || tzID.length() == 0) {
            return null;
        }
        for (MZMapEntry map : (List) TZ_TO_MZS_CACHE.getInstance(tzID, tzID)) {
            if (date >= map.from() && date < map.to()) {
                return map.mzID();
            }
        }
        return null;
    }

    @Override // android.icu.text.TimeZoneNames
    public String getReferenceZoneID(String mzID, String region) {
        return _getReferenceZoneID(mzID, region);
    }

    static String _getReferenceZoneID(String mzID, String region) {
        if (mzID == null || mzID.length() == 0) {
            return null;
        }
        Map<String, String> regionTzMap = (Map) MZ_TO_TZS_CACHE.getInstance(mzID, mzID);
        if (regionTzMap.isEmpty()) {
            return null;
        }
        String refID = regionTzMap.get(region);
        if (refID == null) {
            return regionTzMap.get("001");
        }
        return refID;
    }

    @Override // android.icu.text.TimeZoneNames
    public String getMetaZoneDisplayName(String mzID, TimeZoneNames.NameType type) {
        if (mzID == null || mzID.length() == 0) {
            return null;
        }
        return loadMetaZoneNames(mzID).getName(type);
    }

    @Override // android.icu.text.TimeZoneNames
    public String getTimeZoneDisplayName(String tzID, TimeZoneNames.NameType type) {
        if (tzID == null || tzID.length() == 0) {
            return null;
        }
        return loadTimeZoneNames(tzID).getName(type);
    }

    @Override // android.icu.text.TimeZoneNames
    public String getExemplarLocationName(String tzID) {
        if (tzID == null || tzID.length() == 0) {
            return null;
        }
        return loadTimeZoneNames(tzID).getName(TimeZoneNames.NameType.EXEMPLAR_LOCATION);
    }

    @Override // android.icu.text.TimeZoneNames
    public synchronized Collection<TimeZoneNames.MatchInfo> find(CharSequence text, int start, EnumSet<TimeZoneNames.NameType> nameTypes) {
        if (text != null) {
            if (text.length() != 0 && start >= 0 && start < text.length()) {
                NameSearchHandler handler = new NameSearchHandler(nameTypes);
                Collection<TimeZoneNames.MatchInfo> matches = doFind(handler, text, start);
                if (matches != null) {
                    return matches;
                }
                addAllNamesIntoTrie();
                Collection<TimeZoneNames.MatchInfo> matches2 = doFind(handler, text, start);
                if (matches2 != null) {
                    return matches2;
                }
                internalLoadAllDisplayNames();
                for (String tzID : TimeZone.getAvailableIDs(TimeZone.SystemTimeZoneType.CANONICAL, null, null)) {
                    if (!this._tzNamesMap.containsKey(tzID)) {
                        ZNames.createTimeZoneAndPutInCache(this._tzNamesMap, null, tzID);
                    }
                }
                addAllNamesIntoTrie();
                this._namesTrieFullyLoaded = true;
                return doFind(handler, text, start);
            }
        }
        throw new IllegalArgumentException("bad input text or range");
    }

    private Collection<TimeZoneNames.MatchInfo> doFind(NameSearchHandler handler, CharSequence text, int start) {
        handler.resetResults();
        this._namesTrie.find(text, start, handler);
        if (handler.getMaxMatchLen() == text.length() - start || this._namesTrieFullyLoaded) {
            return handler.getMatches();
        }
        return null;
    }

    @Override // android.icu.text.TimeZoneNames
    public synchronized void loadAllDisplayNames() {
        internalLoadAllDisplayNames();
    }

    @Override // android.icu.text.TimeZoneNames
    public void getDisplayNames(String tzID, TimeZoneNames.NameType[] types, long date, String[] dest, int destOffset) {
        if (!(tzID == null || tzID.length() == 0)) {
            ZNames tzNames = loadTimeZoneNames(tzID);
            ZNames mzNames = null;
            for (int i = 0; i < types.length; i++) {
                TimeZoneNames.NameType type = types[i];
                String name = tzNames.getName(type);
                if (name == null) {
                    if (mzNames == null) {
                        String mzID = getMetaZoneID(tzID, date);
                        if (mzID == null || mzID.length() == 0) {
                            mzNames = ZNames.EMPTY_ZNAMES;
                        } else {
                            mzNames = loadMetaZoneNames(mzID);
                        }
                    }
                    name = mzNames.getName(type);
                }
                dest[destOffset + i] = name;
            }
        }
    }

    private void internalLoadAllDisplayNames() {
        if (!this._namesFullyLoaded) {
            this._namesFullyLoaded = true;
            new ZoneStringsLoader().load();
        }
    }

    private void addAllNamesIntoTrie() {
        for (Map.Entry<String, ZNames> entry : this._tzNamesMap.entrySet()) {
            entry.getValue().addAsTimeZoneIntoTrie(entry.getKey(), this._namesTrie);
        }
        for (Map.Entry<String, ZNames> entry2 : this._mzNamesMap.entrySet()) {
            entry2.getValue().addAsMetaZoneIntoTrie(entry2.getKey(), this._namesTrie);
        }
    }

    /* access modifiers changed from: private */
    public final class ZoneStringsLoader extends UResource.Sink {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private static final int INITIAL_NUM_ZONES = 300;
        private HashMap<UResource.Key, ZNamesLoader> keyToLoader;
        private StringBuilder sb;

        private ZoneStringsLoader() {
            this.keyToLoader = new HashMap<>(300);
            this.sb = new StringBuilder(32);
        }

        /* access modifiers changed from: package-private */
        public void load() {
            TimeZoneNamesImpl.this._zoneStrings.getAllItemsWithFallback("", this);
            for (Map.Entry<UResource.Key, ZNamesLoader> entry : this.keyToLoader.entrySet()) {
                ZNamesLoader loader = entry.getValue();
                if (loader != ZNamesLoader.DUMMY_LOADER) {
                    UResource.Key key = entry.getKey();
                    if (isMetaZone(key)) {
                        ZNames.createMetaZoneAndPutInCache(TimeZoneNamesImpl.this._mzNamesMap, loader.getNames(), mzIDFromKey(key));
                    } else {
                        ZNames.createTimeZoneAndPutInCache(TimeZoneNamesImpl.this._tzNamesMap, loader.getNames(), tzIDFromKey(key));
                    }
                }
            }
        }

        @Override // android.icu.impl.UResource.Sink
        public void put(UResource.Key key, UResource.Value value, boolean noFallback) {
            UResource.Table timeZonesTable = value.getTable();
            for (int j = 0; timeZonesTable.getKeyAndValue(j, key, value); j++) {
                if (value.getType() == 2) {
                    consumeNamesTable(key, value, noFallback);
                }
            }
        }

        private void consumeNamesTable(UResource.Key key, UResource.Value value, boolean noFallback) {
            ZNamesLoader loader = this.keyToLoader.get(key);
            if (loader == null) {
                if (isMetaZone(key)) {
                    if (TimeZoneNamesImpl.this._mzNamesMap.containsKey(mzIDFromKey(key))) {
                        loader = ZNamesLoader.DUMMY_LOADER;
                    } else {
                        loader = new ZNamesLoader();
                    }
                } else {
                    if (TimeZoneNamesImpl.this._tzNamesMap.containsKey(tzIDFromKey(key))) {
                        loader = ZNamesLoader.DUMMY_LOADER;
                    } else {
                        loader = new ZNamesLoader();
                    }
                }
                this.keyToLoader.put(createKey(key), loader);
            }
            if (loader != ZNamesLoader.DUMMY_LOADER) {
                loader.put(key, value, noFallback);
            }
        }

        /* access modifiers changed from: package-private */
        public UResource.Key createKey(UResource.Key key) {
            return key.clone();
        }

        /* access modifiers changed from: package-private */
        public boolean isMetaZone(UResource.Key key) {
            return key.startsWith(TimeZoneNamesImpl.MZ_PREFIX);
        }

        private String mzIDFromKey(UResource.Key key) {
            this.sb.setLength(0);
            for (int i = TimeZoneNamesImpl.MZ_PREFIX.length(); i < key.length(); i++) {
                this.sb.append(key.charAt(i));
            }
            return this.sb.toString();
        }

        private String tzIDFromKey(UResource.Key key) {
            this.sb.setLength(0);
            for (int i = 0; i < key.length(); i++) {
                char c = key.charAt(i);
                if (c == ':') {
                    c = '/';
                }
                this.sb.append(c);
            }
            return this.sb.toString();
        }
    }

    private void initialize(ULocale locale) {
        this._zoneStrings = (ICUResourceBundle) ((ICUResourceBundle) ICUResourceBundle.getBundleInstance(ICUData.ICU_ZONE_BASE_NAME, locale)).get(ZONE_STRINGS_BUNDLE);
        this._tzNamesMap = new ConcurrentHashMap<>();
        this._mzNamesMap = new ConcurrentHashMap<>();
        this._namesFullyLoaded = false;
        this._namesTrie = new TextTrieMap<>(true);
        this._namesTrieFullyLoaded = false;
        String tzCanonicalID = ZoneMeta.getCanonicalCLDRID(TimeZone.getDefault());
        if (tzCanonicalID != null) {
            loadStrings(tzCanonicalID);
        }
    }

    private synchronized void loadStrings(String tzCanonicalID) {
        if (tzCanonicalID != null) {
            if (tzCanonicalID.length() != 0) {
                loadTimeZoneNames(tzCanonicalID);
                for (String mzID : getAvailableMetaZoneIDs(tzCanonicalID)) {
                    loadMetaZoneNames(mzID);
                }
            }
        }
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.writeObject(this._zoneStrings.getULocale());
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        initialize((ULocale) in.readObject());
    }

    private synchronized ZNames loadMetaZoneNames(String mzID) {
        ZNames mznames;
        mznames = this._mzNamesMap.get(mzID);
        if (mznames == null) {
            ZNamesLoader loader = new ZNamesLoader();
            loader.loadMetaZone(this._zoneStrings, mzID);
            mznames = ZNames.createMetaZoneAndPutInCache(this._mzNamesMap, loader.getNames(), mzID);
        }
        return mznames;
    }

    private synchronized ZNames loadTimeZoneNames(String tzID) {
        ZNames tznames;
        tznames = this._tzNamesMap.get(tzID);
        if (tznames == null) {
            ZNamesLoader loader = new ZNamesLoader();
            loader.loadTimeZone(this._zoneStrings, tzID);
            tznames = ZNames.createTimeZoneAndPutInCache(this._tzNamesMap, loader.getNames(), tzID);
        }
        return tznames;
    }

    /* access modifiers changed from: private */
    public static class NameInfo {
        String mzID;
        TimeZoneNames.NameType type;
        String tzID;

        private NameInfo() {
        }
    }

    /* access modifiers changed from: private */
    public static class NameSearchHandler implements TextTrieMap.ResultHandler<NameInfo> {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private Collection<TimeZoneNames.MatchInfo> _matches;
        private int _maxMatchLen;
        private EnumSet<TimeZoneNames.NameType> _nameTypes;

        NameSearchHandler(EnumSet<TimeZoneNames.NameType> nameTypes) {
            this._nameTypes = nameTypes;
        }

        @Override // android.icu.impl.TextTrieMap.ResultHandler
        public boolean handlePrefixMatch(int matchLength, Iterator<NameInfo> values) {
            TimeZoneNames.MatchInfo minfo;
            while (values.hasNext()) {
                NameInfo ninfo = values.next();
                EnumSet<TimeZoneNames.NameType> enumSet = this._nameTypes;
                if (enumSet == null || enumSet.contains(ninfo.type)) {
                    if (ninfo.tzID != null) {
                        minfo = new TimeZoneNames.MatchInfo(ninfo.type, ninfo.tzID, null, matchLength);
                    } else {
                        minfo = new TimeZoneNames.MatchInfo(ninfo.type, null, ninfo.mzID, matchLength);
                    }
                    if (this._matches == null) {
                        this._matches = new LinkedList();
                    }
                    this._matches.add(minfo);
                    if (matchLength > this._maxMatchLen) {
                        this._maxMatchLen = matchLength;
                    }
                }
            }
            return true;
        }

        public Collection<TimeZoneNames.MatchInfo> getMatches() {
            Collection<TimeZoneNames.MatchInfo> collection = this._matches;
            if (collection == null) {
                return Collections.emptyList();
            }
            return collection;
        }

        public int getMaxMatchLen() {
            return this._maxMatchLen;
        }

        public void resetResults() {
            this._matches = null;
            this._maxMatchLen = 0;
        }
    }

    /* access modifiers changed from: private */
    public static final class ZNamesLoader extends UResource.Sink {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private static ZNamesLoader DUMMY_LOADER = new ZNamesLoader();
        private String[] names;

        private ZNamesLoader() {
        }

        /* access modifiers changed from: package-private */
        public void loadMetaZone(ICUResourceBundle zoneStrings, String mzID) {
            loadNames(zoneStrings, TimeZoneNamesImpl.MZ_PREFIX + mzID);
        }

        /* access modifiers changed from: package-private */
        public void loadTimeZone(ICUResourceBundle zoneStrings, String tzID) {
            loadNames(zoneStrings, tzID.replace('/', ':'));
        }

        /* access modifiers changed from: package-private */
        public void loadNames(ICUResourceBundle zoneStrings, String key) {
            this.names = null;
            try {
                zoneStrings.getAllItemsWithFallback(key, this);
            } catch (MissingResourceException e) {
            }
        }

        private static ZNames.NameTypeIndex nameTypeIndexFromKey(UResource.Key key) {
            if (key.length() != 2) {
                return null;
            }
            char c0 = key.charAt(0);
            char c1 = key.charAt(1);
            if (c0 == 'l') {
                if (c1 == 'g') {
                    return ZNames.NameTypeIndex.LONG_GENERIC;
                }
                if (c1 == 's') {
                    return ZNames.NameTypeIndex.LONG_STANDARD;
                }
                if (c1 == 'd') {
                    return ZNames.NameTypeIndex.LONG_DAYLIGHT;
                }
                return null;
            } else if (c0 == 's') {
                if (c1 == 'g') {
                    return ZNames.NameTypeIndex.SHORT_GENERIC;
                }
                if (c1 == 's') {
                    return ZNames.NameTypeIndex.SHORT_STANDARD;
                }
                if (c1 == 'd') {
                    return ZNames.NameTypeIndex.SHORT_DAYLIGHT;
                }
                return null;
            } else if (c0 == 'e' && c1 == 'c') {
                return ZNames.NameTypeIndex.EXEMPLAR_LOCATION;
            } else {
                return null;
            }
        }

        private void setNameIfEmpty(UResource.Key key, UResource.Value value) {
            if (this.names == null) {
                this.names = new String[7];
            }
            ZNames.NameTypeIndex index = nameTypeIndexFromKey(key);
            if (index != null && this.names[index.ordinal()] == null) {
                this.names[index.ordinal()] = value.getString();
            }
        }

        @Override // android.icu.impl.UResource.Sink
        public void put(UResource.Key key, UResource.Value value, boolean noFallback) {
            UResource.Table namesTable = value.getTable();
            for (int i = 0; namesTable.getKeyAndValue(i, key, value); i++) {
                setNameIfEmpty(key, value);
            }
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private String[] getNames() {
            if (Utility.sameObjects(this.names, null)) {
                return null;
            }
            int length = 0;
            for (int i = 0; i < 7; i++) {
                String name = this.names[i];
                if (name != null) {
                    if (name.equals(ICUResourceBundle.NO_INHERITANCE_MARKER)) {
                        this.names[i] = null;
                    } else {
                        length = i + 1;
                    }
                }
            }
            if (length == 7) {
                return this.names;
            }
            if (length == 0) {
                return null;
            }
            return (String[]) Arrays.copyOfRange(this.names, 0, length);
        }
    }

    /* access modifiers changed from: private */
    public static class ZNames {
        static final ZNames EMPTY_ZNAMES = new ZNames(null);
        private static final int EX_LOC_INDEX = NameTypeIndex.EXEMPLAR_LOCATION.ordinal();
        public static final int NUM_NAME_TYPES = 7;
        private String[] _names;
        private boolean didAddIntoTrie;

        /* access modifiers changed from: private */
        public enum NameTypeIndex {
            EXEMPLAR_LOCATION,
            LONG_GENERIC,
            LONG_STANDARD,
            LONG_DAYLIGHT,
            SHORT_GENERIC,
            SHORT_STANDARD,
            SHORT_DAYLIGHT;
            
            static final NameTypeIndex[] values = values();
        }

        private static int getNameTypeIndex(TimeZoneNames.NameType type) {
            switch (type) {
                case EXEMPLAR_LOCATION:
                    return NameTypeIndex.EXEMPLAR_LOCATION.ordinal();
                case LONG_GENERIC:
                    return NameTypeIndex.LONG_GENERIC.ordinal();
                case LONG_STANDARD:
                    return NameTypeIndex.LONG_STANDARD.ordinal();
                case LONG_DAYLIGHT:
                    return NameTypeIndex.LONG_DAYLIGHT.ordinal();
                case SHORT_GENERIC:
                    return NameTypeIndex.SHORT_GENERIC.ordinal();
                case SHORT_STANDARD:
                    return NameTypeIndex.SHORT_STANDARD.ordinal();
                case SHORT_DAYLIGHT:
                    return NameTypeIndex.SHORT_DAYLIGHT.ordinal();
                default:
                    throw new AssertionError((Object) ("No NameTypeIndex match for " + ((Object) type)));
            }
        }

        private static TimeZoneNames.NameType getNameType(int index) {
            switch (NameTypeIndex.values[index]) {
                case EXEMPLAR_LOCATION:
                    return TimeZoneNames.NameType.EXEMPLAR_LOCATION;
                case LONG_GENERIC:
                    return TimeZoneNames.NameType.LONG_GENERIC;
                case LONG_STANDARD:
                    return TimeZoneNames.NameType.LONG_STANDARD;
                case LONG_DAYLIGHT:
                    return TimeZoneNames.NameType.LONG_DAYLIGHT;
                case SHORT_GENERIC:
                    return TimeZoneNames.NameType.SHORT_GENERIC;
                case SHORT_STANDARD:
                    return TimeZoneNames.NameType.SHORT_STANDARD;
                case SHORT_DAYLIGHT:
                    return TimeZoneNames.NameType.SHORT_DAYLIGHT;
                default:
                    throw new AssertionError((Object) ("No NameType match for " + index));
            }
        }

        protected ZNames(String[] names) {
            this._names = names;
            this.didAddIntoTrie = names == null;
        }

        public static ZNames createMetaZoneAndPutInCache(Map<String, ZNames> cache, String[] names, String mzID) {
            ZNames value;
            String key = mzID.intern();
            if (names == null) {
                value = EMPTY_ZNAMES;
            } else {
                value = new ZNames(names);
            }
            cache.put(key, value);
            return value;
        }

        public static ZNames createTimeZoneAndPutInCache(Map<String, ZNames> cache, String[] names, String tzID) {
            String[] names2 = names == null ? new String[(EX_LOC_INDEX + 1)] : names;
            int i = EX_LOC_INDEX;
            if (names2[i] == null) {
                names2[i] = TimeZoneNamesImpl.getDefaultExemplarLocationName(tzID);
            }
            String key = tzID.intern();
            ZNames value = new ZNames(names2);
            cache.put(key, value);
            return value;
        }

        public String getName(TimeZoneNames.NameType type) {
            int index = getNameTypeIndex(type);
            String[] strArr = this._names;
            if (strArr == null || index >= strArr.length) {
                return null;
            }
            return strArr[index];
        }

        public void addAsMetaZoneIntoTrie(String mzID, TextTrieMap<NameInfo> trie) {
            addNamesIntoTrie(mzID, null, trie);
        }

        public void addAsTimeZoneIntoTrie(String tzID, TextTrieMap<NameInfo> trie) {
            addNamesIntoTrie(null, tzID, trie);
        }

        private void addNamesIntoTrie(String mzID, String tzID, TextTrieMap<NameInfo> trie) {
            if (this._names != null && !this.didAddIntoTrie) {
                this.didAddIntoTrie = true;
                int i = 0;
                while (true) {
                    String[] strArr = this._names;
                    if (i < strArr.length) {
                        String name = strArr[i];
                        if (name != null) {
                            NameInfo info = new NameInfo();
                            info.mzID = mzID;
                            info.tzID = tzID;
                            info.type = getNameType(i);
                            trie.put(name, info);
                        }
                        i++;
                    } else {
                        return;
                    }
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public static class MZMapEntry {
        private long _from;
        private String _mzID;
        private long _to;

        MZMapEntry(String mzID, long from, long to) {
            this._mzID = mzID;
            this._from = from;
            this._to = to;
        }

        /* access modifiers changed from: package-private */
        public String mzID() {
            return this._mzID;
        }

        /* access modifiers changed from: package-private */
        public long from() {
            return this._from;
        }

        /* access modifiers changed from: package-private */
        public long to() {
            return this._to;
        }
    }

    /* access modifiers changed from: private */
    public static class TZ2MZsCache extends SoftCache<String, List<MZMapEntry>, String> {
        private TZ2MZsCache() {
        }

        /* access modifiers changed from: protected */
        public List<MZMapEntry> createInstance(String key, String data) {
            String toStr;
            String fromStr;
            try {
                UResourceBundle zoneBundle = UResourceBundle.getBundleInstance(ICUData.ICU_BASE_NAME, "metaZones").get("metazoneInfo").get(data.replace('/', ':'));
                List<MZMapEntry> mzMaps = new ArrayList<>(zoneBundle.getSize());
                for (int idx = 0; idx < zoneBundle.getSize(); idx++) {
                    UResourceBundle mz = zoneBundle.get(idx);
                    String mzid = mz.getString(0);
                    if (mz.getSize() == 3) {
                        fromStr = mz.getString(1);
                        toStr = mz.getString(2);
                    } else {
                        fromStr = "1970-01-01 00:00";
                        toStr = "9999-12-31 23:59";
                    }
                    mzMaps.add(new MZMapEntry(mzid, parseDate(fromStr), parseDate(toStr)));
                }
                return mzMaps;
            } catch (MissingResourceException e) {
                return Collections.emptyList();
            }
        }

        private static long parseDate(String text) {
            int year = 0;
            int month = 0;
            int day = 0;
            int hour = 0;
            int min = 0;
            for (int idx = 0; idx <= 3; idx++) {
                int n = text.charAt(idx) - '0';
                if (n < 0 || n >= 10) {
                    throw new IllegalArgumentException("Bad year");
                }
                year = (year * 10) + n;
            }
            for (int idx2 = 5; idx2 <= 6; idx2++) {
                int n2 = text.charAt(idx2) - '0';
                if (n2 < 0 || n2 >= 10) {
                    throw new IllegalArgumentException("Bad month");
                }
                month = (month * 10) + n2;
            }
            for (int idx3 = 8; idx3 <= 9; idx3++) {
                int n3 = text.charAt(idx3) - '0';
                if (n3 < 0 || n3 >= 10) {
                    throw new IllegalArgumentException("Bad day");
                }
                day = (day * 10) + n3;
            }
            for (int idx4 = 11; idx4 <= 12; idx4++) {
                int n4 = text.charAt(idx4) - '0';
                if (n4 < 0 || n4 >= 10) {
                    throw new IllegalArgumentException("Bad hour");
                }
                hour = (hour * 10) + n4;
            }
            for (int idx5 = 14; idx5 <= 15; idx5++) {
                int n5 = text.charAt(idx5) - '0';
                if (n5 < 0 || n5 >= 10) {
                    throw new IllegalArgumentException("Bad minute");
                }
                min = (min * 10) + n5;
            }
            return (Grego.fieldsToDay(year, month - 1, day) * 86400000) + (((long) hour) * RelativeDateTimeFormatter.HOUR_IN_MILLIS) + (((long) min) * RelativeDateTimeFormatter.MINUTE_IN_MILLIS);
        }
    }

    /* access modifiers changed from: private */
    public static class MZ2TZsCache extends SoftCache<String, Map<String, String>, String> {
        private MZ2TZsCache() {
        }

        /* access modifiers changed from: protected */
        public Map<String, String> createInstance(String key, String data) {
            try {
                UResourceBundle regionMap = UResourceBundle.getBundleInstance(ICUData.ICU_BASE_NAME, "metaZones").get("mapTimezones").get(key);
                Set<String> regions = regionMap.keySet();
                Map<String, String> map = new HashMap<>(regions.size());
                for (String region : regions) {
                    map.put(region.intern(), regionMap.getString(region).intern());
                }
                return map;
            } catch (MissingResourceException e) {
                return Collections.emptyMap();
            }
        }
    }

    public static String getDefaultExemplarLocationName(String tzID) {
        int sep;
        if (tzID == null || tzID.length() == 0 || LOC_EXCLUSION_PATTERN.matcher(tzID).matches() || (sep = tzID.lastIndexOf(47)) <= 0 || sep + 1 >= tzID.length()) {
            return null;
        }
        return tzID.substring(sep + 1).replace('_', ' ');
    }
}
