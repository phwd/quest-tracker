package java.util;

import android.icu.text.TimeZoneNames;
import dalvik.system.RuntimeHooks;
import java.io.IOException;
import java.io.Serializable;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import libcore.io.IoUtils;
import libcore.timezone.ZoneInfoDB;

public abstract class TimeZone implements Serializable, Cloneable {
    private static final TimeZone GMT = new SimpleTimeZone(0, "GMT");
    static final TimeZone NO_TIMEZONE = null;
    private static final TimeZone UTC = new SimpleTimeZone(0, "UTC");
    private static volatile TimeZone defaultTimeZone = null;
    static final long serialVersionUID = 3581463369166924961L;
    private String ID;

    /* access modifiers changed from: private */
    public static class NoImagePreloadHolder {
        public static final Pattern CUSTOM_ZONE_ID_PATTERN = Pattern.compile("^GMT[-+](\\d{1,2})(:?(\\d\\d))?$");
    }

    public abstract int getOffset(int i, int i2, int i3, int i4, int i5, int i6);

    public abstract int getRawOffset();

    public abstract boolean inDaylightTime(Date date);

    public abstract boolean useDaylightTime();

    public int getOffset(long j) {
        if (inDaylightTime(new Date(j))) {
            return getRawOffset() + getDSTSavings();
        }
        return getRawOffset();
    }

    /* access modifiers changed from: package-private */
    public int getOffsets(long j, int[] iArr) {
        int rawOffset = getRawOffset();
        int dSTSavings = inDaylightTime(new Date(j)) ? getDSTSavings() : 0;
        if (iArr != null) {
            iArr[0] = rawOffset;
            iArr[1] = dSTSavings;
        }
        return rawOffset + dSTSavings;
    }

    public String getID() {
        return this.ID;
    }

    public void setID(String str) {
        if (str != null) {
            this.ID = str;
            return;
        }
        throw new NullPointerException();
    }

    public String getDisplayName(boolean z, int i, Locale locale) {
        TimeZoneNames.NameType nameType;
        String displayName;
        if (i != 0) {
            if (i != 1) {
                throw new IllegalArgumentException("Illegal style: " + i);
            } else if (z) {
                nameType = TimeZoneNames.NameType.LONG_DAYLIGHT;
            } else {
                nameType = TimeZoneNames.NameType.LONG_STANDARD;
            }
        } else if (z) {
            nameType = TimeZoneNames.NameType.SHORT_DAYLIGHT;
        } else {
            nameType = TimeZoneNames.NameType.SHORT_STANDARD;
        }
        String canonicalID = android.icu.util.TimeZone.getCanonicalID(getID());
        if (canonicalID != null && (displayName = TimeZoneNames.getInstance(locale).getDisplayName(canonicalID, nameType, System.currentTimeMillis())) != null) {
            return displayName;
        }
        int rawOffset = getRawOffset();
        if (z) {
            rawOffset += getDSTSavings();
        }
        return createGmtOffsetString(true, true, rawOffset);
    }

    public static String createGmtOffsetString(boolean z, boolean z2, int i) {
        char c;
        int i2 = i / 60000;
        if (i2 < 0) {
            c = '-';
            i2 = -i2;
        } else {
            c = '+';
        }
        StringBuilder sb = new StringBuilder(9);
        if (z) {
            sb.append("GMT");
        }
        sb.append(c);
        appendNumber(sb, 2, i2 / 60);
        if (z2) {
            sb.append(':');
        }
        appendNumber(sb, 2, i2 % 60);
        return sb.toString();
    }

    private static void appendNumber(StringBuilder sb, int i, int i2) {
        String num = Integer.toString(i2);
        for (int i3 = 0; i3 < i - num.length(); i3++) {
            sb.append('0');
        }
        sb.append(num);
    }

    public int getDSTSavings() {
        return useDaylightTime() ? 3600000 : 0;
    }

    public boolean observesDaylightTime() {
        return useDaylightTime() || inDaylightTime(new Date());
    }

    public static synchronized TimeZone getTimeZone(String str) {
        synchronized (TimeZone.class) {
            if (str != null) {
                if (str.length() == 3) {
                    if (str.equals("GMT")) {
                        return (TimeZone) GMT.clone();
                    } else if (str.equals("UTC")) {
                        return (TimeZone) UTC.clone();
                    }
                }
                TimeZone timeZone = null;
                try {
                    timeZone = ZoneInfoDB.getInstance().makeTimeZone(str);
                } catch (IOException unused) {
                }
                if (timeZone == null && str.length() > 3 && str.startsWith("GMT")) {
                    timeZone = getCustomTimeZone(str);
                }
                if (timeZone == null) {
                    timeZone = (TimeZone) GMT.clone();
                }
                return timeZone;
            }
            throw new NullPointerException("id == null");
        }
    }

    private static TimeZone getCustomTimeZone(String str) {
        Matcher matcher = NoImagePreloadHolder.CUSTOM_ZONE_ID_PATTERN.matcher(str);
        if (!matcher.matches()) {
            return null;
        }
        try {
            int parseInt = Integer.parseInt(matcher.group(1));
            int parseInt2 = matcher.group(3) != null ? Integer.parseInt(matcher.group(3)) : 0;
            if (parseInt < 0 || parseInt > 23 || parseInt2 < 0 || parseInt2 > 59) {
                return null;
            }
            char charAt = str.charAt(3);
            int i = (3600000 * parseInt) + (60000 * parseInt2);
            if (charAt == '-') {
                i = -i;
            }
            return new SimpleTimeZone(i, String.format(Locale.ROOT, "GMT%c%02d:%02d", Character.valueOf(charAt), Integer.valueOf(parseInt), Integer.valueOf(parseInt2)));
        } catch (NumberFormatException e) {
            throw new AssertionError(e);
        }
    }

    public static synchronized String[] getAvailableIDs() {
        String[] availableIDs;
        synchronized (TimeZone.class) {
            availableIDs = ZoneInfoDB.getInstance().getAvailableIDs();
        }
        return availableIDs;
    }

    public static TimeZone getDefault() {
        return (TimeZone) getDefaultRef().clone();
    }

    static synchronized TimeZone getDefaultRef() {
        TimeZone timeZone;
        synchronized (TimeZone.class) {
            if (defaultTimeZone == null) {
                Supplier timeZoneIdSupplier = RuntimeHooks.getTimeZoneIdSupplier();
                String str = timeZoneIdSupplier != null ? (String) timeZoneIdSupplier.get() : null;
                if (str != null) {
                    str = str.trim();
                }
                if (str == null || str.isEmpty()) {
                    try {
                        IoUtils.readFileAsString("/etc/timezone");
                        throw null;
                    } catch (IOException unused) {
                        str = "GMT";
                    }
                }
                defaultTimeZone = getTimeZone(str);
            }
            timeZone = defaultTimeZone;
        }
        return timeZone;
    }

    public Object clone() {
        try {
            TimeZone timeZone = (TimeZone) super.clone();
            timeZone.ID = this.ID;
            return timeZone;
        } catch (CloneNotSupportedException e) {
            throw new InternalError(e);
        }
    }
}
