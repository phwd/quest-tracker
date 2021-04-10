package android.icu.impl;

import android.icu.text.NumberFormat;
import android.icu.util.SimpleTimeZone;
import android.icu.util.TimeZone;
import android.icu.util.UResourceBundle;
import java.lang.ref.SoftReference;
import java.text.ParsePosition;
import java.util.Collections;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.Set;
import java.util.TreeSet;

public final class ZoneMeta {
    private static ICUCache CANONICAL_ID_CACHE = new SimpleCache();
    private static final CustomTimeZoneCache CUSTOM_ZONE_CACHE = new CustomTimeZoneCache(null);
    private static SoftReference REF_CANONICAL_SYSTEM_LOCATION_ZONES;
    private static SoftReference REF_CANONICAL_SYSTEM_ZONES;
    private static SoftReference REF_SYSTEM_ZONES;
    private static ICUCache REGION_CACHE = new SimpleCache();
    private static ICUCache SINGLE_COUNTRY_CACHE = new SimpleCache();
    private static final SystemTimeZoneCache SYSTEM_ZONE_CACHE = new SystemTimeZoneCache(null);
    private static String[] ZONEIDS;

    private static synchronized Set getSystemZIDs() {
        Set set;
        synchronized (ZoneMeta.class) {
            set = null;
            if (REF_SYSTEM_ZONES != null) {
                set = (Set) REF_SYSTEM_ZONES.get();
            }
            if (set == null) {
                TreeSet treeSet = new TreeSet();
                String[] zoneIDs = getZoneIDs();
                for (String str : zoneIDs) {
                    if (!str.equals("Etc/Unknown")) {
                        treeSet.add(str);
                    }
                }
                set = Collections.unmodifiableSet(treeSet);
                REF_SYSTEM_ZONES = new SoftReference(set);
            }
        }
        return set;
    }

    private static synchronized Set getCanonicalSystemZIDs() {
        Set set;
        synchronized (ZoneMeta.class) {
            set = null;
            if (REF_CANONICAL_SYSTEM_ZONES != null) {
                set = (Set) REF_CANONICAL_SYSTEM_ZONES.get();
            }
            if (set == null) {
                TreeSet treeSet = new TreeSet();
                String[] zoneIDs = getZoneIDs();
                for (String str : zoneIDs) {
                    if (!str.equals("Etc/Unknown")) {
                        if (str.equals(getCanonicalCLDRID(str))) {
                            treeSet.add(str);
                        }
                    }
                }
                set = Collections.unmodifiableSet(treeSet);
                REF_CANONICAL_SYSTEM_ZONES = new SoftReference(set);
            }
        }
        return set;
    }

    private static synchronized Set getCanonicalSystemLocationZIDs() {
        Set set;
        String region;
        synchronized (ZoneMeta.class) {
            set = null;
            if (REF_CANONICAL_SYSTEM_LOCATION_ZONES != null) {
                set = (Set) REF_CANONICAL_SYSTEM_LOCATION_ZONES.get();
            }
            if (set == null) {
                TreeSet treeSet = new TreeSet();
                String[] zoneIDs = getZoneIDs();
                for (String str : zoneIDs) {
                    if (!str.equals("Etc/Unknown")) {
                        if (str.equals(getCanonicalCLDRID(str)) && (region = getRegion(str)) != null && !region.equals("001")) {
                            treeSet.add(str);
                        }
                    }
                }
                set = Collections.unmodifiableSet(treeSet);
                REF_CANONICAL_SYSTEM_LOCATION_ZONES = new SoftReference(set);
            }
        }
        return set;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: android.icu.impl.ZoneMeta$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$android$icu$util$TimeZone$SystemTimeZoneType = new int[TimeZone.SystemTimeZoneType.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|8) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        static {
            /*
                android.icu.util.TimeZone$SystemTimeZoneType[] r0 = android.icu.util.TimeZone.SystemTimeZoneType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                android.icu.impl.ZoneMeta.AnonymousClass1.$SwitchMap$android$icu$util$TimeZone$SystemTimeZoneType = r0
                int[] r0 = android.icu.impl.ZoneMeta.AnonymousClass1.$SwitchMap$android$icu$util$TimeZone$SystemTimeZoneType     // Catch:{ NoSuchFieldError -> 0x0014 }
                android.icu.util.TimeZone$SystemTimeZoneType r1 = android.icu.util.TimeZone.SystemTimeZoneType.ANY     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = android.icu.impl.ZoneMeta.AnonymousClass1.$SwitchMap$android$icu$util$TimeZone$SystemTimeZoneType     // Catch:{ NoSuchFieldError -> 0x001f }
                android.icu.util.TimeZone$SystemTimeZoneType r1 = android.icu.util.TimeZone.SystemTimeZoneType.CANONICAL     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                int[] r0 = android.icu.impl.ZoneMeta.AnonymousClass1.$SwitchMap$android$icu$util$TimeZone$SystemTimeZoneType     // Catch:{ NoSuchFieldError -> 0x002a }
                android.icu.util.TimeZone$SystemTimeZoneType r1 = android.icu.util.TimeZone.SystemTimeZoneType.CANONICAL_LOCATION     // Catch:{ NoSuchFieldError -> 0x002a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: android.icu.impl.ZoneMeta.AnonymousClass1.<clinit>():void");
        }
    }

    public static Set getAvailableIDs(TimeZone.SystemTimeZoneType systemTimeZoneType, String str, Integer num) {
        Set<String> set;
        OlsonTimeZone systemTimeZone;
        int i = AnonymousClass1.$SwitchMap$android$icu$util$TimeZone$SystemTimeZoneType[systemTimeZoneType.ordinal()];
        if (i == 1) {
            set = getSystemZIDs();
        } else if (i == 2) {
            set = getCanonicalSystemZIDs();
        } else if (i == 3) {
            set = getCanonicalSystemLocationZIDs();
        } else {
            throw new IllegalArgumentException("Unknown SystemTimeZoneType");
        }
        if (str == null && num == null) {
            return set;
        }
        if (str != null) {
            str = str.toUpperCase(Locale.ENGLISH);
        }
        TreeSet treeSet = new TreeSet();
        for (String str2 : set) {
            if ((str == null || str.equals(getRegion(str2))) && (num == null || ((systemTimeZone = getSystemTimeZone(str2)) != null && num.equals(Integer.valueOf(systemTimeZone.getRawOffset()))))) {
                treeSet.add(str2);
            }
        }
        if (treeSet.isEmpty()) {
            return Collections.emptySet();
        }
        return Collections.unmodifiableSet(treeSet);
    }

    private static synchronized String[] getZoneIDs() {
        String[] strArr;
        synchronized (ZoneMeta.class) {
            if (ZONEIDS == null) {
                try {
                    ZONEIDS = UResourceBundle.getBundleInstance("android/icu/impl/data/icudt63b", "zoneinfo64", ICUResourceBundle.ICU_DATA_CLASS_LOADER).getStringArray("Names");
                } catch (MissingResourceException unused) {
                }
            }
            if (ZONEIDS == null) {
                ZONEIDS = new String[0];
            }
            strArr = ZONEIDS;
        }
        return strArr;
    }

    private static String getZoneID(int i) {
        if (i < 0) {
            return null;
        }
        String[] zoneIDs = getZoneIDs();
        if (i < zoneIDs.length) {
            return zoneIDs[i];
        }
        return null;
    }

    private static int getZoneIndex(String str) {
        String[] zoneIDs = getZoneIDs();
        if (zoneIDs.length > 0) {
            int i = 0;
            int length = zoneIDs.length;
            int i2 = Integer.MAX_VALUE;
            while (true) {
                int i3 = (i + length) / 2;
                if (i2 == i3) {
                    break;
                }
                int compareTo = str.compareTo(zoneIDs[i3]);
                if (compareTo == 0) {
                    return i3;
                }
                if (compareTo < 0) {
                    length = i3;
                } else {
                    i = i3;
                }
                i2 = i3;
            }
        }
        return -1;
    }

    public static String getCanonicalCLDRID(String str) {
        String str2 = (String) CANONICAL_ID_CACHE.get(str);
        if (str2 == null) {
            str2 = findCLDRCanonicalID(str);
            if (str2 == null) {
                try {
                    int zoneIndex = getZoneIndex(str);
                    if (zoneIndex >= 0) {
                        UResourceBundle uResourceBundle = UResourceBundle.getBundleInstance("android/icu/impl/data/icudt63b", "zoneinfo64", ICUResourceBundle.ICU_DATA_CLASS_LOADER).get("Zones").get(zoneIndex);
                        if (uResourceBundle.getType() == 7) {
                            str = getZoneID(uResourceBundle.getInt());
                            str2 = findCLDRCanonicalID(str);
                        }
                        if (str2 == null) {
                            str2 = str;
                        }
                    }
                } catch (MissingResourceException unused) {
                }
            }
            if (str2 != null) {
                CANONICAL_ID_CACHE.put(str, str2);
            }
        }
        return str2;
    }

    private static String findCLDRCanonicalID(String str) {
        String replace = str.replace('/', ':');
        String str2 = null;
        try {
            UResourceBundle bundleInstance = UResourceBundle.getBundleInstance("android/icu/impl/data/icudt63b", "keyTypeData", ICUResourceBundle.ICU_DATA_CLASS_LOADER);
            try {
                bundleInstance.get("typeMap").get("timezone").get(replace);
                str2 = str;
            } catch (MissingResourceException unused) {
            }
            return str2 == null ? bundleInstance.get("typeAlias").get("timezone").getString(replace) : str2;
        } catch (MissingResourceException unused2) {
            return null;
        }
    }

    public static String getRegion(String str) {
        int zoneIndex;
        String str2 = (String) REGION_CACHE.get(str);
        if (str2 == null && (zoneIndex = getZoneIndex(str)) >= 0) {
            try {
                UResourceBundle uResourceBundle = UResourceBundle.getBundleInstance("android/icu/impl/data/icudt63b", "zoneinfo64", ICUResourceBundle.ICU_DATA_CLASS_LOADER).get("Regions");
                if (zoneIndex < uResourceBundle.getSize()) {
                    str2 = uResourceBundle.getString(zoneIndex);
                }
            } catch (MissingResourceException unused) {
            }
            if (str2 != null) {
                REGION_CACHE.put(str, str2);
            }
        }
        return str2;
    }

    public static UResourceBundle openOlsonResource(UResourceBundle uResourceBundle, String str) {
        int zoneIndex = getZoneIndex(str);
        if (zoneIndex < 0) {
            return null;
        }
        if (uResourceBundle == null) {
            try {
                uResourceBundle = UResourceBundle.getBundleInstance("android/icu/impl/data/icudt63b", "zoneinfo64", ICUResourceBundle.ICU_DATA_CLASS_LOADER);
            } catch (MissingResourceException unused) {
                return null;
            }
        }
        UResourceBundle uResourceBundle2 = uResourceBundle.get("Zones");
        UResourceBundle uResourceBundle3 = uResourceBundle2.get(zoneIndex);
        return uResourceBundle3.getType() == 7 ? uResourceBundle2.get(uResourceBundle3.getInt()) : uResourceBundle3;
    }

    /* access modifiers changed from: private */
    public static class SystemTimeZoneCache extends SoftCache {
        private SystemTimeZoneCache() {
        }

        /* synthetic */ SystemTimeZoneCache(AnonymousClass1 r1) {
            this();
        }

        /* access modifiers changed from: protected */
        public OlsonTimeZone createInstance(String str, String str2) {
            try {
                UResourceBundle bundleInstance = UResourceBundle.getBundleInstance("android/icu/impl/data/icudt63b", "zoneinfo64", ICUResourceBundle.ICU_DATA_CLASS_LOADER);
                UResourceBundle openOlsonResource = ZoneMeta.openOlsonResource(bundleInstance, str2);
                if (openOlsonResource == null) {
                    return null;
                }
                OlsonTimeZone olsonTimeZone = new OlsonTimeZone(bundleInstance, openOlsonResource, str2);
                try {
                    olsonTimeZone.freeze();
                } catch (MissingResourceException unused) {
                }
                return olsonTimeZone;
            } catch (MissingResourceException unused2) {
                return null;
            }
        }
    }

    public static OlsonTimeZone getSystemTimeZone(String str) {
        return (OlsonTimeZone) SYSTEM_ZONE_CACHE.getInstance(str, str);
    }

    /* access modifiers changed from: private */
    public static class CustomTimeZoneCache extends SoftCache {
        private CustomTimeZoneCache() {
        }

        /* synthetic */ CustomTimeZoneCache(AnonymousClass1 r1) {
            this();
        }

        /* access modifiers changed from: protected */
        public SimpleTimeZone createInstance(Integer num, int[] iArr) {
            SimpleTimeZone simpleTimeZone = new SimpleTimeZone(iArr[0] * ((((iArr[1] * 60) + iArr[2]) * 60) + iArr[3]) * 1000, ZoneMeta.formatCustomID(iArr[1], iArr[2], iArr[3], iArr[0] < 0));
            simpleTimeZone.freeze();
            return simpleTimeZone;
        }
    }

    public static SimpleTimeZone getCustomTimeZone(String str) {
        int[] iArr = new int[4];
        if (!parseCustomID(str, iArr)) {
            return null;
        }
        return (SimpleTimeZone) CUSTOM_ZONE_CACHE.getInstance(Integer.valueOf(iArr[0] * (iArr[1] | (iArr[2] << 5) | (iArr[3] << 11))), iArr);
    }

    public static String getCustomID(String str) {
        int[] iArr = new int[4];
        if (!parseCustomID(str, iArr)) {
            return null;
        }
        boolean z = true;
        int i = iArr[1];
        int i2 = iArr[2];
        int i3 = iArr[3];
        if (iArr[0] >= 0) {
            z = false;
        }
        return formatCustomID(i, i2, i3, z);
    }

    static boolean parseCustomID(String str, int[] iArr) {
        int i;
        int i2;
        int i3;
        int i4;
        if (str != null && str.length() > 3 && str.toUpperCase(Locale.ENGLISH).startsWith("GMT")) {
            ParsePosition parsePosition = new ParsePosition(3);
            if (str.charAt(parsePosition.getIndex()) == '-') {
                i = -1;
            } else if (str.charAt(parsePosition.getIndex()) != '+') {
                return false;
            } else {
                i = 1;
            }
            parsePosition.setIndex(parsePosition.getIndex() + 1);
            NumberFormat instance = NumberFormat.getInstance();
            instance.setParseIntegerOnly(true);
            int index = parsePosition.getIndex();
            Number parse = instance.parse(str, parsePosition);
            if (parsePosition.getIndex() == index) {
                return false;
            }
            int intValue = parse.intValue();
            if (parsePosition.getIndex() >= str.length()) {
                int index2 = parsePosition.getIndex() - index;
                if (index2 > 0 && 6 >= index2) {
                    switch (index2) {
                        case 1:
                        case 2:
                        default:
                            i2 = 0;
                            i3 = 0;
                            break;
                        case 3:
                        case 4:
                            i2 = intValue % 100;
                            intValue /= 100;
                            i3 = 0;
                            break;
                        case 5:
                        case 6:
                            int i5 = intValue % 100;
                            int i6 = (intValue / 100) % 100;
                            intValue /= 10000;
                            i3 = i5;
                            i2 = i6;
                            break;
                    }
                }
            } else if (parsePosition.getIndex() - index > 2 || str.charAt(parsePosition.getIndex()) != ':') {
                return false;
            } else {
                parsePosition.setIndex(parsePosition.getIndex() + 1);
                int index3 = parsePosition.getIndex();
                Number parse2 = instance.parse(str, parsePosition);
                if (parsePosition.getIndex() - index3 != 2) {
                    return false;
                }
                int intValue2 = parse2.intValue();
                if (parsePosition.getIndex() >= str.length()) {
                    i4 = 0;
                } else if (str.charAt(parsePosition.getIndex()) != ':') {
                    return false;
                } else {
                    parsePosition.setIndex(parsePosition.getIndex() + 1);
                    int index4 = parsePosition.getIndex();
                    Number parse3 = instance.parse(str, parsePosition);
                    if (parsePosition.getIndex() != str.length() || parsePosition.getIndex() - index4 != 2) {
                        return false;
                    }
                    i4 = parse3.intValue();
                }
                i3 = i4;
                i2 = intValue2;
            }
            if (intValue <= 23 && i2 <= 59 && i3 <= 59) {
                if (iArr != null) {
                    if (iArr.length >= 1) {
                        iArr[0] = i;
                    }
                    if (iArr.length >= 2) {
                        iArr[1] = intValue;
                    }
                    if (iArr.length >= 3) {
                        iArr[2] = i2;
                    }
                    if (iArr.length >= 4) {
                        iArr[3] = i3;
                    }
                }
                return true;
            }
        }
        return false;
    }

    static String formatCustomID(int i, int i2, int i3, boolean z) {
        StringBuilder sb = new StringBuilder("GMT");
        if (!(i == 0 && i2 == 0)) {
            if (z) {
                sb.append('-');
            } else {
                sb.append('+');
            }
            if (i < 10) {
                sb.append('0');
            }
            sb.append(i);
            sb.append(':');
            if (i2 < 10) {
                sb.append('0');
            }
            sb.append(i2);
            if (i3 != 0) {
                sb.append(':');
                if (i3 < 10) {
                    sb.append('0');
                }
                sb.append(i3);
            }
        }
        return sb.toString();
    }
}
