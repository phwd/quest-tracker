package android.icu.util;

import android.icu.impl.CalType;
import android.icu.impl.ICUCache;
import android.icu.impl.ICUResourceBundle;
import android.icu.impl.SimpleCache;
import android.icu.impl.SoftCache;
import android.icu.util.ULocale;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Date;
import java.util.MissingResourceException;

public abstract class Calendar implements Serializable, Cloneable, Comparable {
    static final int[][][] DATE_PRECEDENCE = {new int[][]{new int[]{5}, new int[]{3, 7}, new int[]{4, 7}, new int[]{8, 7}, new int[]{3, 18}, new int[]{4, 18}, new int[]{8, 18}, new int[]{6}, new int[]{37, 1}, new int[]{35, 17}}, new int[][]{new int[]{3}, new int[]{4}, new int[]{8}, new int[]{40, 7}, new int[]{40, 18}}};
    private static final String[] DEFAULT_PATTERNS = {"HH:mm:ss z", "HH:mm:ss z", "HH:mm:ss", "HH:mm", "EEEE, yyyy MMMM dd", "yyyy MMMM d", "yyyy MMM d", "yy/MM/dd", "{1} {0}", "{1} {0}", "{1} {0}", "{1} {0}", "{1} {0}"};
    static final int[][][] DOW_PRECEDENCE = {new int[][]{new int[]{7}, new int[]{18}}};
    private static final String[] FIELD_NAME = {"ERA", "YEAR", "MONTH", "WEEK_OF_YEAR", "WEEK_OF_MONTH", "DAY_OF_MONTH", "DAY_OF_YEAR", "DAY_OF_WEEK", "DAY_OF_WEEK_IN_MONTH", "AM_PM", "HOUR", "HOUR_OF_DAY", "MINUTE", "SECOND", "MILLISECOND", "ZONE_OFFSET", "DST_OFFSET", "YEAR_WOY", "DOW_LOCAL", "EXTENDED_YEAR", "JULIAN_DAY", "MILLISECONDS_IN_DAY"};
    private static final int[] FIND_ZONE_TRANSITION_TIME_UNITS = {3600000, 1800000, 60000, 1000};
    private static final int[][] GREGORIAN_MONTH_COUNT = {new int[]{31, 31, 0, 0}, new int[]{28, 29, 31, 31}, new int[]{31, 31, 59, 60}, new int[]{30, 30, 90, 91}, new int[]{31, 31, 120, 121}, new int[]{30, 30, 151, 152}, new int[]{31, 31, 181, 182}, new int[]{31, 31, 212, 213}, new int[]{30, 30, 243, 244}, new int[]{31, 31, 273, 274}, new int[]{30, 30, 304, 305}, new int[]{31, 31, 334, 335}};
    private static final int[][] LIMITS = {new int[0], new int[0], new int[0], new int[0], new int[0], new int[0], new int[0], new int[]{1, 1, 7, 7}, new int[0], new int[]{0, 0, 1, 1}, new int[]{0, 0, 11, 11}, new int[]{0, 0, 23, 23}, new int[]{0, 0, 59, 59}, new int[]{0, 0, 59, 59}, new int[]{0, 0, 999, 999}, new int[]{-43200000, -43200000, 43200000, 43200000}, new int[]{0, 0, 3600000, 3600000}, new int[0], new int[]{1, 1, 7, 7}, new int[0], new int[]{-2130706432, -2130706432, 2130706432, 2130706432}, new int[]{0, 0, 86399999, 86399999}, new int[]{0, 0, 1, 1}};
    protected static final Date MAX_DATE = new Date(183882168921600000L);
    protected static final Date MIN_DATE = new Date(-184303902528000000L);
    private static final ICUCache PATTERN_CACHE = new SimpleCache();
    private static int STAMP_MAX = 10000;
    private static final WeekDataCache WEEK_DATA_CACHE = new WeekDataCache(null);
    private static final long serialVersionUID = 6222646104888790989L;
    private ULocale actualLocale;
    private transient boolean areAllFieldsSet;
    private transient boolean areFieldsSet;
    private transient boolean areFieldsVirtuallySet;
    private transient int[] fields;
    private int firstDayOfWeek;
    private transient int internalSetMask;
    private transient boolean isTimeSet;
    private boolean lenient;
    private int minimalDaysInFirstWeek;
    private transient int nextStamp;
    private int repeatedWallTime;
    private int skippedWallTime;
    private transient int[] stamp;
    private long time;
    private ULocale validLocale;
    private int weekendCease;
    private int weekendCeaseMillis;
    private int weekendOnset;
    private int weekendOnsetMillis;
    private TimeZone zone;

    protected static final long julianDayToMillis(int i) {
        return ((long) (i - 2440588)) * 86400000;
    }

    /* access modifiers changed from: protected */
    public int getDefaultDayInMonth(int i, int i2) {
        return 1;
    }

    /* access modifiers changed from: protected */
    public int getDefaultMonthInYear(int i) {
        return 0;
    }

    /* access modifiers changed from: protected */
    public abstract int handleComputeMonthStart(int i, int i2, boolean z);

    /* access modifiers changed from: protected */
    public int[] handleCreateFields() {
        return new int[23];
    }

    /* access modifiers changed from: protected */
    public abstract int handleGetExtendedYear();

    /* access modifiers changed from: protected */
    public abstract int handleGetLimit(int i, int i2);

    protected Calendar() {
        this(TimeZone.getDefault(), ULocale.getDefault(ULocale.Category.FORMAT));
    }

    protected Calendar(TimeZone timeZone, ULocale uLocale) {
        this.lenient = true;
        this.repeatedWallTime = 0;
        this.skippedWallTime = 0;
        this.nextStamp = 2;
        this.zone = timeZone;
        setWeekData(getRegionForCalendar(uLocale));
        setCalendarLocale(uLocale);
        initInternal();
    }

    private void setCalendarLocale(ULocale uLocale) {
        if (!(uLocale.getVariant().length() == 0 && uLocale.getKeywords() == null)) {
            StringBuilder sb = new StringBuilder();
            sb.append(uLocale.getLanguage());
            String script = uLocale.getScript();
            if (script.length() > 0) {
                sb.append("_");
                sb.append(script);
            }
            String country = uLocale.getCountry();
            if (country.length() > 0) {
                sb.append("_");
                sb.append(country);
            }
            String keywordValue = uLocale.getKeywordValue("calendar");
            if (keywordValue != null) {
                sb.append("@calendar=");
                sb.append(keywordValue);
            }
            uLocale = new ULocale(sb.toString());
        }
        setLocale(uLocale, uLocale);
    }

    private void initInternal() {
        this.fields = handleCreateFields();
        int[] iArr = this.fields;
        if (iArr != null) {
            if (iArr.length >= 23 && iArr.length <= 32) {
                this.stamp = new int[iArr.length];
                int i = 4718695;
                for (int i2 = 23; i2 < this.fields.length; i2++) {
                    i |= 1 << i2;
                }
                this.internalSetMask = i;
                return;
            }
        }
        throw new IllegalStateException("Invalid fields[]");
    }

    private static String getRegionForCalendar(ULocale uLocale) {
        String regionForSupplementalData = ULocale.getRegionForSupplementalData(uLocale, true);
        return regionForSupplementalData.length() == 0 ? "001" : regionForSupplementalData;
    }

    /* renamed from: android.icu.util.Calendar$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$android$icu$impl$CalType = new int[CalType.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(36:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|17|18|19|20|21|22|23|24|25|26|27|28|29|30|31|32|33|34|(3:35|36|38)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(38:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|17|18|19|20|21|22|23|24|25|26|27|28|29|30|31|32|33|34|35|36|38) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0040 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x004b */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0056 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0062 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x006e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x007a */
        /* JADX WARNING: Missing exception handler attribute for start block: B:23:0x0086 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:25:0x0092 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:27:0x009e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:29:0x00aa */
        /* JADX WARNING: Missing exception handler attribute for start block: B:31:0x00b6 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:33:0x00c2 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:35:0x00ce */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x002a */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0035 */
        static {
            /*
            // Method dump skipped, instructions count: 219
            */
            throw new UnsupportedOperationException("Method not decompiled: android.icu.util.Calendar.AnonymousClass1.<clinit>():void");
        }
    }

    public final Date getTime() {
        return new Date(getTimeInMillis());
    }

    public long getTimeInMillis() {
        if (!this.isTimeSet) {
            updateTime();
        }
        return this.time;
    }

    /* access modifiers changed from: protected */
    public final int internalGet(int i) {
        return this.fields[i];
    }

    /* access modifiers changed from: protected */
    public final int internalGet(int i, int i2) {
        return this.stamp[i] > 0 ? this.fields[i] : i2;
    }

    public final boolean isSet(int i) {
        return this.areFieldsVirtuallySet || this.stamp[i] != 0;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (Calendar.class != obj.getClass()) {
            return false;
        }
        Calendar calendar = (Calendar) obj;
        return isEquivalentTo(calendar) && getTimeInMillis() == calendar.getTime().getTime();
    }

    public boolean isEquivalentTo(Calendar calendar) {
        return Calendar.class == calendar.getClass() && isLenient() == calendar.isLenient() && getFirstDayOfWeek() == calendar.getFirstDayOfWeek() && getMinimalDaysInFirstWeek() == calendar.getMinimalDaysInFirstWeek() && getTimeZone().equals(calendar.getTimeZone()) && getRepeatedWallTimeOption() == calendar.getRepeatedWallTimeOption() && getSkippedWallTimeOption() == calendar.getSkippedWallTimeOption();
    }

    public int hashCode() {
        int i = this.lenient ? 1 : 0;
        return (this.zone.hashCode() << 11) | i | (this.firstDayOfWeek << 1) | (this.minimalDaysInFirstWeek << 4) | (this.repeatedWallTime << 7) | (this.skippedWallTime << 9);
    }

    public int compareTo(Calendar calendar) {
        int i = ((getTimeInMillis() - calendar.getTimeInMillis()) > 0 ? 1 : ((getTimeInMillis() - calendar.getTimeInMillis()) == 0 ? 0 : -1));
        if (i < 0) {
            return -1;
        }
        return i > 0 ? 1 : 0;
    }

    public TimeZone getTimeZone() {
        return this.zone;
    }

    public boolean isLenient() {
        return this.lenient;
    }

    public int getRepeatedWallTimeOption() {
        return this.repeatedWallTime;
    }

    public int getSkippedWallTimeOption() {
        return this.skippedWallTime;
    }

    public void setFirstDayOfWeek(int i) {
        if (this.firstDayOfWeek == i) {
            return;
        }
        if (i < 1 || i > 7) {
            throw new IllegalArgumentException("Invalid day of week");
        }
        this.firstDayOfWeek = i;
        this.areFieldsSet = false;
    }

    public int getFirstDayOfWeek() {
        return this.firstDayOfWeek;
    }

    public void setMinimalDaysInFirstWeek(int i) {
        if (i < 1) {
            i = 1;
        } else if (i > 7) {
            i = 7;
        }
        if (this.minimalDaysInFirstWeek != i) {
            this.minimalDaysInFirstWeek = i;
            this.areFieldsSet = false;
        }
    }

    public int getMinimalDaysInFirstWeek() {
        return this.minimalDaysInFirstWeek;
    }

    /* access modifiers changed from: protected */
    public int getLimit(int i, int i2) {
        switch (i) {
            case 4:
                if (i2 == 0) {
                    if (getMinimalDaysInFirstWeek() == 1) {
                        return 1;
                    }
                    return 0;
                } else if (i2 == 1) {
                    return 1;
                } else {
                    int minimalDaysInFirstWeek2 = getMinimalDaysInFirstWeek();
                    int handleGetLimit = handleGetLimit(5, i2);
                    if (i2 == 2) {
                        return (handleGetLimit + (7 - minimalDaysInFirstWeek2)) / 7;
                    }
                    return ((handleGetLimit + 6) + (7 - minimalDaysInFirstWeek2)) / 7;
                }
            case 5:
            case 6:
            case 8:
            case 17:
            case 19:
            default:
                return handleGetLimit(i, i2);
            case 7:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
            case 16:
            case 18:
            case 20:
            case 21:
            case 22:
                return LIMITS[i][i2];
        }
    }

    public final int getMinimum(int i) {
        return getLimit(i, 0);
    }

    public final int getMaximum(int i) {
        return getLimit(i, 3);
    }

    public Object clone() {
        try {
            Calendar calendar = (Calendar) super.clone();
            calendar.fields = new int[this.fields.length];
            calendar.stamp = new int[this.fields.length];
            System.arraycopy(this.fields, 0, calendar.fields, 0, this.fields.length);
            System.arraycopy(this.stamp, 0, calendar.stamp, 0, this.fields.length);
            calendar.zone = (TimeZone) this.zone.clone();
            return calendar;
        } catch (CloneNotSupportedException e) {
            throw new ICUCloneNotSupportedException(e);
        }
    }

    public String toString() {
        String str;
        StringBuilder sb = new StringBuilder();
        sb.append(Calendar.class.getName());
        sb.append("[time=");
        sb.append(this.isTimeSet ? String.valueOf(this.time) : "?");
        sb.append(",areFieldsSet=");
        sb.append(this.areFieldsSet);
        sb.append(",areAllFieldsSet=");
        sb.append(this.areAllFieldsSet);
        sb.append(",lenient=");
        sb.append(this.lenient);
        sb.append(",zone=");
        sb.append(this.zone);
        sb.append(",firstDayOfWeek=");
        sb.append(this.firstDayOfWeek);
        sb.append(",minimalDaysInFirstWeek=");
        sb.append(this.minimalDaysInFirstWeek);
        sb.append(",repeatedWallTime=");
        sb.append(this.repeatedWallTime);
        sb.append(",skippedWallTime=");
        sb.append(this.skippedWallTime);
        for (int i = 0; i < this.fields.length; i++) {
            sb.append(',');
            sb.append(fieldName(i));
            sb.append('=');
            if (isSet(i)) {
                str = String.valueOf(this.fields[i]);
            } else {
                str = "?";
            }
            sb.append(str);
        }
        sb.append(']');
        return sb.toString();
    }

    public static final class WeekData {
        public final int firstDayOfWeek;
        public final int minimalDaysInFirstWeek;
        public final int weekendCease;
        public final int weekendCeaseMillis;
        public final int weekendOnset;
        public final int weekendOnsetMillis;

        public WeekData(int i, int i2, int i3, int i4, int i5, int i6) {
            this.firstDayOfWeek = i;
            this.minimalDaysInFirstWeek = i2;
            this.weekendOnset = i3;
            this.weekendOnsetMillis = i4;
            this.weekendCease = i5;
            this.weekendCeaseMillis = i6;
        }

        public int hashCode() {
            return (((((((((this.firstDayOfWeek * 37) + this.minimalDaysInFirstWeek) * 37) + this.weekendOnset) * 37) + this.weekendOnsetMillis) * 37) + this.weekendCease) * 37) + this.weekendCeaseMillis;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof WeekData)) {
                return false;
            }
            WeekData weekData = (WeekData) obj;
            return this.firstDayOfWeek == weekData.firstDayOfWeek && this.minimalDaysInFirstWeek == weekData.minimalDaysInFirstWeek && this.weekendOnset == weekData.weekendOnset && this.weekendOnsetMillis == weekData.weekendOnsetMillis && this.weekendCease == weekData.weekendCease && this.weekendCeaseMillis == weekData.weekendCeaseMillis;
        }

        public String toString() {
            return "{" + this.firstDayOfWeek + ", " + this.minimalDaysInFirstWeek + ", " + this.weekendOnset + ", " + this.weekendOnsetMillis + ", " + this.weekendCease + ", " + this.weekendCeaseMillis + "}";
        }
    }

    public Calendar setWeekData(WeekData weekData) {
        setFirstDayOfWeek(weekData.firstDayOfWeek);
        setMinimalDaysInFirstWeek(weekData.minimalDaysInFirstWeek);
        this.weekendOnset = weekData.weekendOnset;
        this.weekendOnsetMillis = weekData.weekendOnsetMillis;
        this.weekendCease = weekData.weekendCease;
        this.weekendCeaseMillis = weekData.weekendCeaseMillis;
        return this;
    }

    /* access modifiers changed from: private */
    public static WeekData getWeekDataForRegionInternal(String str) {
        UResourceBundle uResourceBundle;
        if (str == null) {
            str = "001";
        }
        UResourceBundle uResourceBundle2 = UResourceBundle.getBundleInstance("android/icu/impl/data/icudt63b", "supplementalData", ICUResourceBundle.ICU_DATA_CLASS_LOADER).get("weekData");
        try {
            uResourceBundle = uResourceBundle2.get(str);
        } catch (MissingResourceException e) {
            if (!str.equals("001")) {
                uResourceBundle = uResourceBundle2.get("001");
            } else {
                throw e;
            }
        }
        int[] intVector = uResourceBundle.getIntVector();
        return new WeekData(intVector[0], intVector[1], intVector[2], intVector[3], intVector[4], intVector[5]);
    }

    /* access modifiers changed from: private */
    public static class WeekDataCache extends SoftCache {
        private WeekDataCache() {
        }

        /* synthetic */ WeekDataCache(AnonymousClass1 r1) {
            this();
        }

        /* access modifiers changed from: protected */
        public WeekData createInstance(String str, String str2) {
            return Calendar.getWeekDataForRegionInternal(str);
        }
    }

    private void setWeekData(String str) {
        if (str == null) {
            str = "001";
        }
        setWeekData((WeekData) WEEK_DATA_CACHE.getInstance(str, str));
    }

    private void updateTime() {
        computeTime();
        if (isLenient() || !this.areAllFieldsSet) {
            this.areFieldsSet = false;
        }
        this.isTimeSet = true;
        this.areFieldsVirtuallySet = false;
    }

    private void writeObject(ObjectOutputStream objectOutputStream) {
        if (!this.isTimeSet) {
            try {
                updateTime();
            } catch (IllegalArgumentException unused) {
            }
        }
        objectOutputStream.defaultWriteObject();
        throw null;
    }

    private void readObject(ObjectInputStream objectInputStream) {
        objectInputStream.defaultReadObject();
        throw null;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0042, code lost:
        if (r8[4] < r8[r7]) goto L_0x0044;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int resolveFields(int[][][] r13) {
        /*
            r12 = this;
            r0 = 0
            r1 = -1
            r2 = r1
            r1 = r0
        L_0x0004:
            int r3 = r13.length
            r4 = 32
            if (r1 >= r3) goto L_0x004f
            if (r2 >= 0) goto L_0x004f
            r3 = r13[r1]
            r6 = r0
            r5 = r2
            r2 = r6
        L_0x0010:
            int r7 = r3.length
            if (r2 >= r7) goto L_0x004b
            r7 = r3[r2]
            r8 = r7[r0]
            if (r8 < r4) goto L_0x001b
            r8 = 1
            goto L_0x001c
        L_0x001b:
            r8 = r0
        L_0x001c:
            r9 = r0
        L_0x001d:
            int r10 = r7.length
            if (r8 >= r10) goto L_0x0030
            int[] r10 = r12.stamp
            r11 = r7[r8]
            r10 = r10[r11]
            if (r10 != 0) goto L_0x0029
            goto L_0x0048
        L_0x0029:
            int r9 = java.lang.Math.max(r9, r10)
            int r8 = r8 + 1
            goto L_0x001d
        L_0x0030:
            if (r9 <= r6) goto L_0x0048
            r7 = r7[r0]
            if (r7 < r4) goto L_0x0044
            r7 = r7 & 31
            r8 = 5
            if (r7 != r8) goto L_0x0044
            int[] r8 = r12.stamp
            r10 = 4
            r10 = r8[r10]
            r8 = r8[r7]
            if (r10 >= r8) goto L_0x0045
        L_0x0044:
            r5 = r7
        L_0x0045:
            if (r5 != r7) goto L_0x0048
            r6 = r9
        L_0x0048:
            int r2 = r2 + 1
            goto L_0x0010
        L_0x004b:
            int r1 = r1 + 1
            r2 = r5
            goto L_0x0004
        L_0x004f:
            if (r2 < r4) goto L_0x0053
            r2 = r2 & 31
        L_0x0053:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: android.icu.util.Calendar.resolveFields(int[][][]):int");
    }

    /* access modifiers changed from: protected */
    public int newestStamp(int i, int i2, int i3) {
        while (i <= i2) {
            int[] iArr = this.stamp;
            if (iArr[i] > i3) {
                i3 = iArr[i];
            }
            i++;
        }
        return i3;
    }

    /* access modifiers changed from: protected */
    public int newerField(int i, int i2) {
        int[] iArr = this.stamp;
        return iArr[i2] > iArr[i] ? i2 : i;
    }

    /* access modifiers changed from: protected */
    public void validateFields() {
        for (int i = 0; i < this.fields.length; i++) {
            if (this.stamp[i] >= 2) {
                validateField(i);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void validateField(int i) {
        if (i == 5) {
            validateField(i, 1, handleGetMonthLength(handleGetExtendedYear(), internalGet(2)));
        } else if (i == 6) {
            validateField(i, 1, handleGetYearLength(handleGetExtendedYear()));
        } else if (i != 8) {
            validateField(i, getMinimum(i), getMaximum(i));
        } else if (internalGet(i) != 0) {
            validateField(i, getMinimum(i), getMaximum(i));
        } else {
            throw new IllegalArgumentException("DAY_OF_WEEK_IN_MONTH cannot be zero");
        }
    }

    /* access modifiers changed from: protected */
    public final void validateField(int i, int i2, int i3) {
        int i4 = this.fields[i];
        if (i4 < i2 || i4 > i3) {
            throw new IllegalArgumentException(fieldName(i) + '=' + i4 + ", valid range=" + i2 + ".." + i3);
        }
    }

    /* access modifiers changed from: protected */
    public void computeTime() {
        long j;
        int[] iArr;
        int i;
        if (!isLenient()) {
            validateFields();
        }
        long julianDayToMillis = julianDayToMillis(computeJulianDay());
        if (this.stamp[21] >= 2 && newestStamp(9, 14, 0) <= this.stamp[21]) {
            i = internalGet(21);
        } else if (Math.max(Math.abs(internalGet(11)), Math.abs(internalGet(10))) > 548) {
            j = computeMillisInDayLong();
            iArr = this.stamp;
            if (iArr[15] < 2 || iArr[16] >= 2) {
                this.time = (julianDayToMillis + j) - ((long) (internalGet(15) + internalGet(16)));
            } else if (!this.lenient || this.skippedWallTime == 2) {
                int computeZoneOffset = computeZoneOffset(julianDayToMillis, j);
                long j2 = (julianDayToMillis + j) - ((long) computeZoneOffset);
                if (computeZoneOffset == this.zone.getOffset(j2)) {
                    this.time = j2;
                    return;
                } else if (this.lenient) {
                    Long immediatePreviousZoneTransition = getImmediatePreviousZoneTransition(j2);
                    if (immediatePreviousZoneTransition != null) {
                        this.time = immediatePreviousZoneTransition.longValue();
                        return;
                    }
                    throw new RuntimeException("Could not locate a time zone transition before " + j2);
                } else {
                    throw new IllegalArgumentException("The specified wall time does not exist due to time zone offset transition.");
                }
            } else {
                this.time = (julianDayToMillis + j) - ((long) computeZoneOffset(julianDayToMillis, j));
                return;
            }
        } else {
            i = computeMillisInDay();
        }
        j = (long) i;
        iArr = this.stamp;
        if (iArr[15] < 2) {
        }
        this.time = (julianDayToMillis + j) - ((long) (internalGet(15) + internalGet(16)));
    }

    private Long getImmediatePreviousZoneTransition(long j) {
        TimeZone timeZone = this.zone;
        if (timeZone instanceof BasicTimeZone) {
            TimeZoneTransition previousTransition = ((BasicTimeZone) timeZone).getPreviousTransition(j, true);
            if (previousTransition != null) {
                return Long.valueOf(previousTransition.getTime());
            }
            return null;
        }
        Long previousZoneTransitionTime = getPreviousZoneTransitionTime(timeZone, j, 7200000);
        return previousZoneTransitionTime == null ? getPreviousZoneTransitionTime(this.zone, j, 108000000) : previousZoneTransitionTime;
    }

    private static Long getPreviousZoneTransitionTime(TimeZone timeZone, long j, long j2) {
        long j3 = (j - j2) - 1;
        int offset = timeZone.getOffset(j);
        if (offset == timeZone.getOffset(j3)) {
            return null;
        }
        return findPreviousZoneTransitionTime(timeZone, offset, j, j3);
    }

    private static Long findPreviousZoneTransitionTime(TimeZone timeZone, int i, long j, long j2) {
        long j3;
        long j4;
        long j5;
        int[] iArr = FIND_ZONE_TRANSITION_TIME_UNITS;
        int length = iArr.length;
        boolean z = false;
        int i2 = 0;
        while (true) {
            if (i2 >= length) {
                j3 = 0;
                break;
            }
            long j6 = (long) iArr[i2];
            long j7 = j2 / j6;
            long j8 = j / j6;
            if (j8 > j7) {
                j3 = (((j7 + j8) + 1) >>> 1) * j6;
                z = true;
                break;
            }
            i2++;
        }
        if (!z) {
            j3 = (j + j2) >>> 1;
        }
        if (z) {
            if (j3 == j) {
                j5 = j;
            } else if (timeZone.getOffset(j3) != i) {
                return findPreviousZoneTransitionTime(timeZone, i, j, j3);
            } else {
                j5 = j3;
            }
            j4 = j3 - 1;
        } else {
            j4 = (j + j2) >>> 1;
            j5 = j;
        }
        if (j4 == j2) {
            return Long.valueOf(j5);
        }
        if (timeZone.getOffset(j4) == i) {
            return findPreviousZoneTransitionTime(timeZone, i, j4, j2);
        }
        if (z) {
            return Long.valueOf(j5);
        }
        return findPreviousZoneTransitionTime(timeZone, i, j5, j4);
    }

    /* access modifiers changed from: protected */
    public int computeMillisInDay() {
        int[] iArr = this.stamp;
        int i = iArr[11];
        int max = Math.max(iArr[10], iArr[9]);
        if (max <= i) {
            max = i;
        }
        int i2 = 0;
        if (max != 0) {
            if (max == i) {
                i2 = 0 + internalGet(11);
            } else {
                i2 = internalGet(10) + 0 + (internalGet(9) * 12);
            }
        }
        return (((((i2 * 60) + internalGet(12)) * 60) + internalGet(13)) * 1000) + internalGet(14);
    }

    /* access modifiers changed from: protected */
    public long computeMillisInDayLong() {
        int[] iArr = this.stamp;
        int i = iArr[11];
        int max = Math.max(iArr[10], iArr[9]);
        if (max <= i) {
            max = i;
        }
        long j = 0;
        if (max != 0) {
            if (max == i) {
                j = 0 + ((long) internalGet(11));
            } else {
                j = ((long) internalGet(10)) + 0 + ((long) (internalGet(9) * 12));
            }
        }
        return (((((j * 60) + ((long) internalGet(12))) * 60) + ((long) internalGet(13))) * 1000) + ((long) internalGet(14));
    }

    /* access modifiers changed from: protected */
    public int computeZoneOffset(long j, long j2) {
        boolean z;
        int offset;
        int[] iArr = new int[2];
        long j3 = j + j2;
        TimeZone timeZone = this.zone;
        if (timeZone instanceof BasicTimeZone) {
            ((BasicTimeZone) this.zone).getOffsetFromLocal(j3, this.skippedWallTime == 1 ? 12 : 4, this.repeatedWallTime == 1 ? 4 : 12, iArr);
        } else {
            timeZone.getOffset(j3, true, iArr);
            if (this.repeatedWallTime != 1 || (offset = (iArr[0] + iArr[1]) - this.zone.getOffset((j3 - ((long) (iArr[0] + iArr[1]))) - 21600000)) >= 0) {
                z = false;
            } else {
                this.zone.getOffset(((long) offset) + j3, true, iArr);
                z = true;
            }
            if (!z && this.skippedWallTime == 1) {
                this.zone.getOffset(j3 - ((long) (iArr[0] + iArr[1])), false, iArr);
            }
        }
        return iArr[0] + iArr[1];
    }

    /* access modifiers changed from: protected */
    public int computeJulianDay() {
        if (this.stamp[20] >= 2 && newestStamp(17, 19, newestStamp(0, 8, 0)) <= this.stamp[20]) {
            return internalGet(20);
        }
        int resolveFields = resolveFields(getFieldResolutionTable());
        if (resolveFields < 0) {
            resolveFields = 5;
        }
        return handleComputeJulianDay(resolveFields);
    }

    /* access modifiers changed from: protected */
    public int[][][] getFieldResolutionTable() {
        return DATE_PRECEDENCE;
    }

    /* access modifiers changed from: protected */
    public int handleGetMonthLength(int i, int i2) {
        return handleComputeMonthStart(i, i2 + 1, true) - handleComputeMonthStart(i, i2, true);
    }

    /* access modifiers changed from: protected */
    public int handleGetYearLength(int i) {
        return handleComputeMonthStart(i + 1, 0, false) - handleComputeMonthStart(i, 0, false);
    }

    /* access modifiers changed from: protected */
    public int handleComputeJulianDay(int i) {
        int i2;
        int i3;
        int i4;
        int i5;
        int internalGet;
        boolean z = i == 5 || i == 4 || i == 8;
        if (i == 3 && newerField(17, 1) == 17) {
            i2 = internalGet(17);
        } else {
            i2 = handleGetExtendedYear();
        }
        internalSet(19, i2);
        int internalGet2 = z ? internalGet(2, getDefaultMonthInYear(i2)) : 0;
        int handleComputeMonthStart = handleComputeMonthStart(i2, internalGet2, z);
        if (i == 5) {
            if (isSet(5)) {
                internalGet = internalGet(5, getDefaultDayInMonth(i2, internalGet2));
            } else {
                internalGet = getDefaultDayInMonth(i2, internalGet2);
            }
        } else if (i == 6) {
            internalGet = internalGet(6);
        } else {
            int firstDayOfWeek2 = getFirstDayOfWeek();
            int julianDayToDayOfWeek = julianDayToDayOfWeek(handleComputeMonthStart + 1) - firstDayOfWeek2;
            if (julianDayToDayOfWeek < 0) {
                julianDayToDayOfWeek += 7;
            }
            int resolveFields = resolveFields(DOW_PRECEDENCE);
            if (resolveFields == 7) {
                i3 = internalGet(7) - firstDayOfWeek2;
            } else if (resolveFields != 18) {
                i3 = 0;
            } else {
                i3 = internalGet(18) - 1;
            }
            int i6 = i3 % 7;
            if (i6 < 0) {
                i6 += 7;
            }
            int i7 = (1 - julianDayToDayOfWeek) + i6;
            if (i == 8) {
                if (i7 < 1) {
                    i7 += 7;
                }
                int internalGet3 = internalGet(8, 1);
                if (internalGet3 >= 0) {
                    i4 = i7 + ((internalGet3 - 1) * 7);
                    return handleComputeMonthStart + i4;
                }
                i5 = ((handleGetMonthLength(i2, internalGet(2, 0)) - i7) / 7) + internalGet3 + 1;
            } else {
                if (7 - julianDayToDayOfWeek < getMinimalDaysInFirstWeek()) {
                    i7 += 7;
                }
                i5 = internalGet(i) - 1;
            }
            i4 = i7 + (i5 * 7);
            return handleComputeMonthStart + i4;
        }
        return handleComputeMonthStart + internalGet;
    }

    /* access modifiers changed from: protected */
    public final void internalSet(int i, int i2) {
        if (((1 << i) & this.internalSetMask) != 0) {
            this.fields[i] = i2;
            this.stamp[i] = 1;
            return;
        }
        throw new IllegalStateException("Subclass cannot set " + fieldName(i));
    }

    /* access modifiers changed from: protected */
    public String fieldName(int i) {
        try {
            return FIELD_NAME[i];
        } catch (ArrayIndexOutOfBoundsException unused) {
            return "Field " + i;
        }
    }

    protected static final int julianDayToDayOfWeek(int i) {
        int i2 = (i + 2) % 7;
        return i2 < 1 ? i2 + 7 : i2;
    }

    /* access modifiers changed from: package-private */
    public final void setLocale(ULocale uLocale, ULocale uLocale2) {
        boolean z = true;
        boolean z2 = uLocale == null;
        if (uLocale2 != null) {
            z = false;
        }
        if (z2 == z) {
            this.validLocale = uLocale;
            this.actualLocale = uLocale2;
            return;
        }
        throw new IllegalArgumentException();
    }
}
