package android.icu.util;

import android.icu.impl.Grego;
import android.icu.impl.ICUConfig;
import android.icu.impl.JavaTimeZone;
import android.icu.impl.OlsonTimeZone;
import android.icu.impl.ZoneMeta;
import java.io.Serializable;
import java.util.Set;
import java.util.logging.Logger;

public abstract class TimeZone implements Serializable, Cloneable, Freezable {
    public static final TimeZone GMT_ZONE = new ConstantZone(0, "Etc/GMT").freeze();
    private static final Logger LOGGER = Logger.getLogger("android.icu.util.TimeZone");
    private static int TZ_IMPL = 0;
    public static final TimeZone UNKNOWN_ZONE = new ConstantZone(0, "Etc/Unknown").freeze();
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

    public boolean isFrozen() {
        return false;
    }

    static {
        TZ_IMPL = 0;
        if (ICUConfig.get("android.icu.util.TimeZone.DefaultTimeZoneType", "ICU").equalsIgnoreCase("JDK")) {
            TZ_IMPL = 1;
        }
    }

    public TimeZone() {
    }

    protected TimeZone(String str) {
        if (str != null) {
            this.ID = str;
            return;
        }
        throw new NullPointerException();
    }

    public int getOffset(long j) {
        int[] iArr = new int[2];
        getOffset(j, false, iArr);
        return iArr[0] + iArr[1];
    }

    public void getOffset(long j, boolean z, int[] iArr) {
        iArr[0] = getRawOffset();
        if (!z) {
            j += (long) iArr[0];
        }
        int[] iArr2 = new int[6];
        int i = 0;
        while (true) {
            Grego.timeToFields(j, iArr2);
            iArr[1] = getOffset(1, iArr2[0], iArr2[1], iArr2[2], iArr2[3], iArr2[5]) - iArr[0];
            if (i == 0 && z && iArr[1] != 0) {
                j -= (long) iArr[1];
                i++;
            } else {
                return;
            }
        }
    }

    public String getID() {
        return this.ID;
    }

    public void setID(String str) {
        if (str == null) {
            throw new NullPointerException();
        } else if (!isFrozen()) {
            this.ID = str;
        } else {
            throw new UnsupportedOperationException("Attempt to modify a frozen TimeZone instance.");
        }
    }

    public static TimeZone getFrozenTimeZone(String str) {
        return getTimeZone(str, TZ_IMPL, true);
    }

    private static TimeZone getTimeZone(String str, int i, boolean z) {
        TimeZone timeZone;
        if (i == 1) {
            JavaTimeZone createTimeZone = JavaTimeZone.createTimeZone(str);
            if (createTimeZone != null) {
                return z ? createTimeZone.freeze() : createTimeZone;
            }
            timeZone = getFrozenICUTimeZone(str, false);
        } else {
            timeZone = getFrozenICUTimeZone(str, true);
        }
        if (timeZone == null) {
            Logger logger = LOGGER;
            logger.fine("\"" + str + "\" is a bogus id so timezone is falling back to Etc/Unknown(GMT).");
            timeZone = UNKNOWN_ZONE;
        }
        return z ? timeZone : timeZone.cloneAsThawed();
    }

    static BasicTimeZone getFrozenICUTimeZone(String str, boolean z) {
        OlsonTimeZone systemTimeZone = z ? ZoneMeta.getSystemTimeZone(str) : null;
        return systemTimeZone == null ? ZoneMeta.getCustomTimeZone(str) : systemTimeZone;
    }

    public static Set getAvailableIDs(SystemTimeZoneType systemTimeZoneType, String str, Integer num) {
        return ZoneMeta.getAvailableIDs(systemTimeZoneType, str, num);
    }

    public static TimeZone getDefault() {
        TimeZone timeZone;
        TimeZone timeZone2 = defaultZone;
        if (timeZone2 == null) {
            synchronized (java.util.TimeZone.class) {
                synchronized (TimeZone.class) {
                    timeZone = defaultZone;
                    if (timeZone == null) {
                        if (TZ_IMPL == 1) {
                            timeZone = new JavaTimeZone();
                        } else {
                            timeZone = getFrozenTimeZone(java.util.TimeZone.getDefault().getID());
                        }
                        defaultZone = timeZone;
                    }
                }
            }
            timeZone2 = timeZone;
        }
        return timeZone2.cloneAsThawed();
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

    public static String getCanonicalID(String str) {
        return getCanonicalID(str, null);
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x0023  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String getCanonicalID(java.lang.String r3, boolean[] r4) {
        /*
            java.lang.String r0 = "Etc/Unknown"
            r1 = 0
            if (r3 == 0) goto L_0x001f
            int r2 = r3.length()
            if (r2 == 0) goto L_0x001f
            boolean r2 = r3.equals(r0)
            if (r2 == 0) goto L_0x0012
            goto L_0x0020
        L_0x0012:
            java.lang.String r0 = android.icu.impl.ZoneMeta.getCanonicalCLDRID(r3)
            if (r0 == 0) goto L_0x001a
            r3 = 1
            goto L_0x0021
        L_0x001a:
            java.lang.String r0 = android.icu.impl.ZoneMeta.getCustomID(r3)
            goto L_0x0020
        L_0x001f:
            r0 = 0
        L_0x0020:
            r3 = r1
        L_0x0021:
            if (r4 == 0) goto L_0x0025
            r4[r1] = r3
        L_0x0025:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: android.icu.util.TimeZone.getCanonicalID(java.lang.String, boolean[]):java.lang.String");
    }

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

        private ConstantZone(int i, String str) {
            super(str);
            this.isFrozen = false;
            this.rawOffset = i;
        }

        @Override // android.icu.util.TimeZone
        public int getOffset(int i, int i2, int i3, int i4, int i5, int i6) {
            return this.rawOffset;
        }

        @Override // android.icu.util.TimeZone
        public int getRawOffset() {
            return this.rawOffset;
        }

        @Override // android.icu.util.TimeZone
        public boolean isFrozen() {
            return this.isFrozen;
        }

        public TimeZone freeze() {
            this.isFrozen = true;
            return this;
        }

        @Override // android.icu.util.TimeZone
        public TimeZone cloneAsThawed() {
            ConstantZone constantZone = (ConstantZone) TimeZone.super.cloneAsThawed();
            constantZone.isFrozen = false;
            return constantZone;
        }
    }
}
