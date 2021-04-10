package android.icu.util;

import android.icu.impl.Grego;
import android.icu.impl.ICUConfig;
import android.icu.impl.ICUData;
import android.icu.impl.ICUResourceBundle;
import android.icu.impl.JavaTimeZone;
import android.icu.impl.OlsonTimeZone;
import android.icu.impl.TimeZoneAdapter;
import android.icu.impl.ZoneMeta;
import android.icu.impl.number.Padder;
import android.icu.util.ULocale;
import java.io.Serializable;
import java.util.Date;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.Set;
import java.util.logging.Logger;

public abstract class TimeZone implements Serializable, Cloneable, Freezable<TimeZone> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final int GENERIC_LOCATION = 7;
    public static final TimeZone GMT_ZONE = new ConstantZone(0, GMT_ZONE_ID).freeze();
    static final String GMT_ZONE_ID = "Etc/GMT";
    private static final Logger LOGGER = Logger.getLogger("android.icu.util.TimeZone");
    public static final int LONG = 1;
    public static final int LONG_GENERIC = 3;
    public static final int LONG_GMT = 5;
    public static final int SHORT = 0;
    public static final int SHORT_COMMONLY_USED = 6;
    public static final int SHORT_GENERIC = 2;
    public static final int SHORT_GMT = 4;
    public static final int TIMEZONE_ICU = 0;
    public static final int TIMEZONE_JDK = 1;
    private static final String TZIMPL_CONFIG_ICU = "ICU";
    private static final String TZIMPL_CONFIG_JDK = "JDK";
    private static final String TZIMPL_CONFIG_KEY = "android.icu.util.TimeZone.DefaultTimeZoneType";
    private static int TZ_IMPL = 0;
    public static final TimeZone UNKNOWN_ZONE = new ConstantZone(0, UNKNOWN_ZONE_ID).freeze();
    public static final String UNKNOWN_ZONE_ID = "Etc/Unknown";
    private static volatile TimeZone defaultZone = null;
    private static final long serialVersionUID = -744942128318337471L;
    private String ID;

    public enum SystemTimeZoneType {
        ANY,
        CANONICAL,
        CANONICAL_LOCATION
    }

    public abstract int getOffset(int i, int i2, int i3, int i4, int i5, int i6);

    public abstract int getRawOffset();

    public abstract boolean inDaylightTime(Date date);

    public abstract void setRawOffset(int i);

    public abstract boolean useDaylightTime();

    static {
        TZ_IMPL = 0;
        if (ICUConfig.get(TZIMPL_CONFIG_KEY, TZIMPL_CONFIG_ICU).equalsIgnoreCase(TZIMPL_CONFIG_JDK)) {
            TZ_IMPL = 1;
        }
    }

    public TimeZone() {
    }

    @Deprecated
    protected TimeZone(String ID2) {
        if (ID2 != null) {
            this.ID = ID2;
            return;
        }
        throw new NullPointerException();
    }

    public int getOffset(long date) {
        int[] result = new int[2];
        getOffset(date, false, result);
        return result[0] + result[1];
    }

    public void getOffset(long date, boolean local, int[] offsets) {
        offsets[0] = getRawOffset();
        if (!local) {
            date += (long) offsets[0];
        }
        int[] fields = new int[6];
        int pass = 0;
        while (true) {
            Grego.timeToFields(date, fields);
            offsets[1] = getOffset(1, fields[0], fields[1], fields[2], fields[3], fields[5]) - offsets[0];
            if (pass == 0 && local && offsets[1] != 0) {
                date -= (long) offsets[1];
                pass++;
            } else {
                return;
            }
        }
    }

    public String getID() {
        return this.ID;
    }

    public void setID(String ID2) {
        if (ID2 == null) {
            throw new NullPointerException();
        } else if (!isFrozen()) {
            this.ID = ID2;
        } else {
            throw new UnsupportedOperationException("Attempt to modify a frozen TimeZone instance.");
        }
    }

    public final String getDisplayName() {
        return _getDisplayName(3, false, ULocale.getDefault(ULocale.Category.DISPLAY));
    }

    public final String getDisplayName(Locale locale) {
        return _getDisplayName(3, false, ULocale.forLocale(locale));
    }

    public final String getDisplayName(ULocale locale) {
        return _getDisplayName(3, false, locale);
    }

    public final String getDisplayName(boolean daylight, int style) {
        return getDisplayName(daylight, style, ULocale.getDefault(ULocale.Category.DISPLAY));
    }

    public String getDisplayName(boolean daylight, int style, Locale locale) {
        return getDisplayName(daylight, style, ULocale.forLocale(locale));
    }

    public String getDisplayName(boolean daylight, int style, ULocale locale) {
        if (style >= 0 && style <= 7) {
            return _getDisplayName(style, daylight, locale);
        }
        throw new IllegalArgumentException("Illegal style: " + style);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0027, code lost:
        if (r16 != 6) goto L_0x003b;
     */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0045  */
    /* JADX WARNING: Removed duplicated region for block: B:66:? A[ORIG_RETURN, RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String _getDisplayName(int r16, boolean r17, android.icu.util.ULocale r18) {
        /*
        // Method dump skipped, instructions count: 257
        */
        throw new UnsupportedOperationException("Method not decompiled: android.icu.util.TimeZone._getDisplayName(int, boolean, android.icu.util.ULocale):java.lang.String");
    }

    public int getDSTSavings() {
        if (useDaylightTime()) {
            return 3600000;
        }
        return 0;
    }

    public boolean observesDaylightTime() {
        return useDaylightTime() || inDaylightTime(new Date());
    }

    public static TimeZone getTimeZone(String ID2) {
        return getTimeZone(ID2, TZ_IMPL, false);
    }

    public static TimeZone getFrozenTimeZone(String ID2) {
        return getTimeZone(ID2, TZ_IMPL, true);
    }

    public static TimeZone getTimeZone(String ID2, int type) {
        return getTimeZone(ID2, type, false);
    }

    private static TimeZone getTimeZone(String id, int type, boolean frozen) {
        TimeZone result;
        if (type == 1) {
            TimeZone result2 = JavaTimeZone.createTimeZone(id);
            if (result2 != null) {
                return frozen ? result2.freeze() : result2;
            }
            result = getFrozenICUTimeZone(id, false);
        } else {
            result = getFrozenICUTimeZone(id, true);
        }
        if (result == null) {
            Logger logger = LOGGER;
            logger.fine("\"" + id + "\" is a bogus id so timezone is falling back to Etc/Unknown(GMT).");
            result = UNKNOWN_ZONE;
        }
        return frozen ? result : result.cloneAsThawed();
    }

    static BasicTimeZone getFrozenICUTimeZone(String id, boolean trySystem) {
        BasicTimeZone result = null;
        if (trySystem) {
            result = ZoneMeta.getSystemTimeZone(id);
        }
        if (result == null) {
            return ZoneMeta.getCustomTimeZone(id);
        }
        return result;
    }

    public static synchronized void setDefaultTimeZoneType(int type) {
        synchronized (TimeZone.class) {
            if (type == 0 || type == 1) {
                TZ_IMPL = type;
            } else {
                throw new IllegalArgumentException("Invalid timezone type");
            }
        }
    }

    public static int getDefaultTimeZoneType() {
        return TZ_IMPL;
    }

    public static Set<String> getAvailableIDs(SystemTimeZoneType zoneType, String region, Integer rawOffset) {
        return ZoneMeta.getAvailableIDs(zoneType, region, rawOffset);
    }

    public static String[] getAvailableIDs(int rawOffset) {
        return (String[]) getAvailableIDs(SystemTimeZoneType.ANY, null, Integer.valueOf(rawOffset)).toArray(new String[0]);
    }

    public static String[] getAvailableIDs(String country) {
        return (String[]) getAvailableIDs(SystemTimeZoneType.ANY, country, null).toArray(new String[0]);
    }

    public static String[] getAvailableIDs() {
        return (String[]) getAvailableIDs(SystemTimeZoneType.ANY, null, null).toArray(new String[0]);
    }

    public static int countEquivalentIDs(String id) {
        return ZoneMeta.countEquivalentIDs(id);
    }

    public static String getEquivalentID(String id, int index) {
        return ZoneMeta.getEquivalentID(id, index);
    }

    public static TimeZone getDefault() {
        TimeZone tmpDefaultZone = defaultZone;
        if (tmpDefaultZone == null) {
            synchronized (java.util.TimeZone.class) {
                synchronized (TimeZone.class) {
                    tmpDefaultZone = defaultZone;
                    if (tmpDefaultZone == null) {
                        if (TZ_IMPL == 1) {
                            tmpDefaultZone = new JavaTimeZone();
                        } else {
                            tmpDefaultZone = getFrozenTimeZone(java.util.TimeZone.getDefault().getID());
                        }
                        defaultZone = tmpDefaultZone;
                    }
                }
            }
        }
        return tmpDefaultZone.cloneAsThawed();
    }

    public static synchronized void setDefault(TimeZone tz) {
        synchronized (TimeZone.class) {
            setICUDefault(tz);
            if (tz != null) {
                java.util.TimeZone jdkZone = null;
                if (tz instanceof JavaTimeZone) {
                    jdkZone = ((JavaTimeZone) tz).unwrap();
                } else if (tz instanceof OlsonTimeZone) {
                    String icuID = tz.getID();
                    jdkZone = java.util.TimeZone.getTimeZone(icuID);
                    if (!icuID.equals(jdkZone.getID())) {
                        String icuID2 = getCanonicalID(icuID);
                        jdkZone = java.util.TimeZone.getTimeZone(icuID2);
                        if (!icuID2.equals(jdkZone.getID())) {
                            jdkZone = null;
                        }
                    }
                }
                if (jdkZone == null) {
                    jdkZone = TimeZoneAdapter.wrap(tz);
                }
                java.util.TimeZone.setDefault(jdkZone);
            }
        }
    }

    @Deprecated
    public static synchronized void setICUDefault(TimeZone tz) {
        synchronized (TimeZone.class) {
            if (tz == null) {
                defaultZone = null;
            } else if (tz.isFrozen()) {
                defaultZone = tz;
            } else {
                defaultZone = ((TimeZone) tz.clone()).freeze();
            }
        }
    }

    public boolean hasSameRules(TimeZone other) {
        return other != null && getRawOffset() == other.getRawOffset() && useDaylightTime() == other.useDaylightTime();
    }

    public Object clone() {
        if (isFrozen()) {
            return this;
        }
        return cloneAsThawed();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.ID.equals(((TimeZone) obj).ID);
    }

    public int hashCode() {
        return this.ID.hashCode();
    }

    public static String getTZDataVersion() {
        return VersionInfo.getTZDataVersion();
    }

    public static String getCanonicalID(String id) {
        return getCanonicalID(id, null);
    }

    public static String getCanonicalID(String id, boolean[] isSystemID) {
        String canonicalID = null;
        boolean systemTzid = false;
        if (!(id == null || id.length() == 0)) {
            if (id.equals(UNKNOWN_ZONE_ID)) {
                canonicalID = UNKNOWN_ZONE_ID;
                systemTzid = false;
            } else {
                canonicalID = ZoneMeta.getCanonicalCLDRID(id);
                if (canonicalID != null) {
                    systemTzid = true;
                } else {
                    canonicalID = ZoneMeta.getCustomID(id);
                }
            }
        }
        if (isSystemID != null) {
            isSystemID[0] = systemTzid;
        }
        return canonicalID;
    }

    public static String getRegion(String id) {
        String region = null;
        if (!id.equals(UNKNOWN_ZONE_ID)) {
            region = ZoneMeta.getRegion(id);
        }
        if (region != null) {
            return region;
        }
        throw new IllegalArgumentException("Unknown system zone id: " + id);
    }

    public static String getWindowsID(String id) {
        boolean[] isSystemID = {false};
        String id2 = getCanonicalID(id, isSystemID);
        if (!isSystemID[0]) {
            return null;
        }
        UResourceBundleIterator resitr = UResourceBundle.getBundleInstance(ICUData.ICU_BASE_NAME, "windowsZones", ICUResourceBundle.ICU_DATA_CLASS_LOADER).get("mapTimezones").getIterator();
        while (resitr.hasNext()) {
            UResourceBundle winzone = resitr.next();
            if (winzone.getType() == 2) {
                UResourceBundleIterator rgitr = winzone.getIterator();
                while (rgitr.hasNext()) {
                    UResourceBundle regionalData = rgitr.next();
                    if (regionalData.getType() == 0) {
                        for (String tzid : regionalData.getString().split(Padder.FALLBACK_PADDING_STRING)) {
                            if (tzid.equals(id2)) {
                                return winzone.getKey();
                            }
                        }
                        continue;
                    }
                }
                continue;
            }
        }
        return null;
    }

    public static String getIDForWindowsID(String winid, String region) {
        int endIdx;
        String id = null;
        try {
            UResourceBundle zones = UResourceBundle.getBundleInstance(ICUData.ICU_BASE_NAME, "windowsZones", ICUResourceBundle.ICU_DATA_CLASS_LOADER).get("mapTimezones").get(winid);
            if (region != null) {
                try {
                    id = zones.getString(region);
                    if (id != null && (endIdx = id.indexOf(32)) > 0) {
                        id = id.substring(0, endIdx);
                    }
                } catch (MissingResourceException e) {
                }
            }
            if (id == null) {
                return zones.getString("001");
            }
            return id;
        } catch (MissingResourceException e2) {
            return null;
        }
    }

    @Override // android.icu.util.Freezable
    public boolean isFrozen() {
        return false;
    }

    @Override // android.icu.util.Freezable
    public TimeZone freeze() {
        throw new UnsupportedOperationException("Needs to be implemented by the subclass.");
    }

    @Override // android.icu.util.Freezable
    public TimeZone cloneAsThawed() {
        try {
            return (TimeZone) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new ICUCloneNotSupportedException(e);
        }
    }

    private static final class ConstantZone extends TimeZone {
        private static final long serialVersionUID = 1;
        private volatile transient boolean isFrozen;
        private int rawOffset;

        private ConstantZone(int rawOffset2, String ID) {
            super(ID);
            this.isFrozen = false;
            this.rawOffset = rawOffset2;
        }

        @Override // android.icu.util.TimeZone
        public int getOffset(int era, int year, int month, int day, int dayOfWeek, int milliseconds) {
            return this.rawOffset;
        }

        @Override // android.icu.util.TimeZone
        public void setRawOffset(int offsetMillis) {
            if (!isFrozen()) {
                this.rawOffset = offsetMillis;
                return;
            }
            throw new UnsupportedOperationException("Attempt to modify a frozen TimeZone instance.");
        }

        @Override // android.icu.util.TimeZone
        public int getRawOffset() {
            return this.rawOffset;
        }

        @Override // android.icu.util.TimeZone
        public boolean useDaylightTime() {
            return false;
        }

        @Override // android.icu.util.TimeZone
        public boolean inDaylightTime(Date date) {
            return false;
        }

        @Override // android.icu.util.Freezable, android.icu.util.TimeZone
        public boolean isFrozen() {
            return this.isFrozen;
        }

        @Override // android.icu.util.Freezable, android.icu.util.TimeZone, android.icu.util.TimeZone
        public TimeZone freeze() {
            this.isFrozen = true;
            return this;
        }

        @Override // android.icu.util.Freezable, android.icu.util.TimeZone, android.icu.util.TimeZone
        public TimeZone cloneAsThawed() {
            ConstantZone tz = (ConstantZone) TimeZone.super.cloneAsThawed();
            tz.isFrozen = false;
            return tz;
        }
    }
}
