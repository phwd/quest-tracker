package java.util;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.DateFormatSymbols;
import java.util.Locale;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import libcore.icu.LocaleData;
import sun.util.locale.provider.CalendarDataUtility;

public abstract class Calendar implements Serializable, Cloneable, Comparable {
    private static final String[] FIELD_NAME = {"ERA", "YEAR", "MONTH", "WEEK_OF_YEAR", "WEEK_OF_MONTH", "DAY_OF_MONTH", "DAY_OF_YEAR", "DAY_OF_WEEK", "DAY_OF_WEEK_IN_MONTH", "AM_PM", "HOUR", "HOUR_OF_DAY", "MINUTE", "SECOND", "MILLISECOND", "ZONE_OFFSET", "DST_OFFSET"};
    private static final ConcurrentMap cachedLocaleData = new ConcurrentHashMap(3);
    static final long serialVersionUID = -1807547505821590642L;
    transient boolean areAllFieldsSet;
    protected boolean areFieldsSet;
    protected int[] fields;
    private int firstDayOfWeek;
    protected boolean[] isSet;
    protected boolean isTimeSet;
    private boolean lenient;
    private int minimalDaysInFirstWeek;
    private int nextStamp;
    private int serialVersionOnStream;
    private transient boolean sharedZone;
    private transient int[] stamp;
    protected long time;
    private TimeZone zone;

    private static int aggregateStamp(int i, int i2) {
        if (i == 0 || i2 == 0) {
            return 0;
        }
        return i > i2 ? i : i2;
    }

    static boolean isFieldSet(int i, int i2) {
        return (i & (1 << i2)) != 0;
    }

    private boolean isNarrowFormatStyle(int i) {
        return i == 4;
    }

    private boolean isStandaloneStyle(int i) {
        return (32768 & i) != 0;
    }

    public static int toStandaloneStyle(int i) {
        return i | 32768;
    }

    public abstract void add(int i, int i2);

    /* access modifiers changed from: protected */
    public abstract void computeFields();

    /* access modifiers changed from: protected */
    public abstract void computeTime();

    /* access modifiers changed from: package-private */
    public int getBaseStyle(int i) {
        return -32769 & i;
    }

    public abstract int getGreatestMinimum(int i);

    public abstract int getLeastMaximum(int i);

    public abstract int getMaximum(int i);

    public abstract int getMinimum(int i);

    public boolean isWeekDateSupported() {
        return false;
    }

    protected Calendar() {
        this(TimeZone.getDefaultRef(), Locale.getDefault(Locale.Category.FORMAT));
        this.sharedZone = true;
    }

    protected Calendar(TimeZone timeZone, Locale locale) {
        this.lenient = true;
        this.sharedZone = false;
        this.nextStamp = 2;
        this.serialVersionOnStream = 1;
        locale = locale == null ? Locale.getDefault() : locale;
        this.fields = new int[17];
        this.isSet = new boolean[17];
        this.stamp = new int[17];
        this.zone = timeZone;
        setWeekCountData(locale);
    }

    public static Calendar getInstance(Locale locale) {
        return createCalendar(TimeZone.getDefault(), locale);
    }

    public static Calendar getInstance(TimeZone timeZone, Locale locale) {
        return createCalendar(timeZone, locale);
    }

    public static Calendar getJapaneseImperialInstance(TimeZone timeZone, Locale locale) {
        return new JapaneseImperialCalendar(timeZone, locale);
    }

    private static Calendar createCalendar(TimeZone timeZone, Locale locale) {
        return new GregorianCalendar(timeZone, locale);
    }

    public final Date getTime() {
        return new Date(getTimeInMillis());
    }

    public final void setTime(Date date) {
        setTimeInMillis(date.getTime());
    }

    public long getTimeInMillis() {
        if (!this.isTimeSet) {
            updateTime();
        }
        return this.time;
    }

    public void setTimeInMillis(long j) {
        if (this.time != j || !this.isTimeSet || !this.areFieldsSet || !this.areAllFieldsSet) {
            this.time = j;
            this.isTimeSet = true;
            this.areFieldsSet = false;
            computeFields();
            this.areFieldsSet = true;
            this.areAllFieldsSet = true;
        }
    }

    public int get(int i) {
        complete();
        return internalGet(i);
    }

    /* access modifiers changed from: protected */
    public final int internalGet(int i) {
        return this.fields[i];
    }

    /* access modifiers changed from: package-private */
    public final void internalSet(int i, int i2) {
        this.fields[i] = i2;
    }

    public void set(int i, int i2) {
        if (this.areFieldsSet && !this.areAllFieldsSet) {
            computeFields();
        }
        internalSet(i, i2);
        this.isTimeSet = false;
        this.areFieldsSet = false;
        this.isSet[i] = true;
        int[] iArr = this.stamp;
        int i3 = this.nextStamp;
        this.nextStamp = i3 + 1;
        iArr[i] = i3;
        if (this.nextStamp == Integer.MAX_VALUE) {
            adjustStamp();
        }
    }

    public final void set(int i, int i2, int i3) {
        set(1, i);
        set(2, i2);
        set(5, i3);
    }

    public final void set(int i, int i2, int i3, int i4, int i5, int i6) {
        set(1, i);
        set(2, i2);
        set(5, i3);
        set(11, i4);
        set(12, i5);
        set(13, i6);
    }

    public final void clear() {
        int i = 0;
        while (true) {
            int[] iArr = this.fields;
            if (i < iArr.length) {
                int[] iArr2 = this.stamp;
                iArr[i] = 0;
                iArr2[i] = 0;
                this.isSet[i] = false;
                i++;
            } else {
                this.areFieldsSet = false;
                this.areAllFieldsSet = false;
                this.isTimeSet = false;
                return;
            }
        }
    }

    public final boolean isSet(int i) {
        return this.stamp[i] != 0;
    }

    public String getDisplayName(int i, int i2, Locale locale) {
        if (i2 == 0) {
            i2 = 1;
        }
        if (!checkDisplayNameParams(i, i2, 1, 4, locale, 645)) {
            return null;
        }
        String calendarType = getCalendarType();
        int i3 = get(i);
        if (isStandaloneStyle(i2) || isNarrowFormatStyle(i2)) {
            String retrieveFieldValueName = CalendarDataUtility.retrieveFieldValueName(calendarType, i, i3, i2, locale);
            if (retrieveFieldValueName != null) {
                return retrieveFieldValueName;
            }
            if (isNarrowFormatStyle(i2)) {
                return CalendarDataUtility.retrieveFieldValueName(calendarType, i, i3, toStandaloneStyle(i2), locale);
            }
            return isStandaloneStyle(i2) ? CalendarDataUtility.retrieveFieldValueName(calendarType, i, i3, getBaseStyle(i2), locale) : retrieveFieldValueName;
        }
        String[] fieldStrings = getFieldStrings(i, i2, DateFormatSymbols.getInstance(locale));
        if (fieldStrings == null || i3 >= fieldStrings.length) {
            return null;
        }
        return fieldStrings[i3];
    }

    public Map getDisplayNames(int i, int i2, Locale locale) {
        if (!checkDisplayNameParams(i, i2, 0, 4, locale, 645)) {
            return null;
        }
        complete();
        String calendarType = getCalendarType();
        if (i2 != 0 && !isStandaloneStyle(i2) && !isNarrowFormatStyle(i2)) {
            return getDisplayNamesImpl(i, i2, locale);
        }
        Map retrieveFieldValueNames = CalendarDataUtility.retrieveFieldValueNames(calendarType, i, i2, locale);
        if (retrieveFieldValueNames != null) {
            return retrieveFieldValueNames;
        }
        if (isNarrowFormatStyle(i2)) {
            return CalendarDataUtility.retrieveFieldValueNames(calendarType, i, toStandaloneStyle(i2), locale);
        }
        return i2 != 0 ? CalendarDataUtility.retrieveFieldValueNames(calendarType, i, getBaseStyle(i2), locale) : retrieveFieldValueNames;
    }

    private Map getDisplayNamesImpl(int i, int i2, Locale locale) {
        String[] fieldStrings = getFieldStrings(i, i2, DateFormatSymbols.getInstance(locale));
        if (fieldStrings == null) {
            return null;
        }
        HashMap hashMap = new HashMap();
        for (int i3 = 0; i3 < fieldStrings.length; i3++) {
            if (fieldStrings[i3].length() != 0) {
                hashMap.put(fieldStrings[i3], Integer.valueOf(i3));
            }
        }
        return hashMap;
    }

    /* access modifiers changed from: package-private */
    public boolean checkDisplayNameParams(int i, int i2, int i3, int i4, Locale locale, int i5) {
        int baseStyle = getBaseStyle(i2);
        if (i < 0 || i >= this.fields.length || baseStyle < i3 || baseStyle > i4) {
            throw new IllegalArgumentException();
        } else if (baseStyle == 3) {
            throw new IllegalArgumentException();
        } else if (locale != null) {
            return isFieldSet(i5, i);
        } else {
            throw new NullPointerException();
        }
    }

    private String[] getFieldStrings(int i, int i2, DateFormatSymbols dateFormatSymbols) {
        String[] months;
        int baseStyle = getBaseStyle(i2);
        if (baseStyle == 4) {
            return null;
        }
        if (i == 0) {
            return dateFormatSymbols.getEras();
        }
        if (i == 2) {
            months = baseStyle == 2 ? dateFormatSymbols.getMonths() : dateFormatSymbols.getShortMonths();
        } else if (i == 7) {
            months = baseStyle == 2 ? dateFormatSymbols.getWeekdays() : dateFormatSymbols.getShortWeekdays();
        } else if (i != 9) {
            return null;
        } else {
            return dateFormatSymbols.getAmPmStrings();
        }
        return months;
    }

    /* access modifiers changed from: protected */
    public void complete() {
        if (!this.isTimeSet) {
            updateTime();
        }
        if (!this.areFieldsSet || !this.areAllFieldsSet) {
            computeFields();
            this.areFieldsSet = true;
            this.areAllFieldsSet = true;
        }
    }

    /* access modifiers changed from: package-private */
    public final boolean isExternallySet(int i) {
        return this.stamp[i] >= 2;
    }

    /* access modifiers changed from: package-private */
    public final int getSetStateFields() {
        int i = 0;
        for (int i2 = 0; i2 < this.fields.length; i2++) {
            if (this.stamp[i2] != 0) {
                i |= 1 << i2;
            }
        }
        return i;
    }

    /* access modifiers changed from: package-private */
    public final void setFieldsComputed(int i) {
        if (i == 131071) {
            for (int i2 = 0; i2 < this.fields.length; i2++) {
                this.stamp[i2] = 1;
                this.isSet[i2] = true;
            }
            this.areAllFieldsSet = true;
            this.areFieldsSet = true;
            return;
        }
        int i3 = i;
        for (int i4 = 0; i4 < this.fields.length; i4++) {
            if ((i3 & 1) == 1) {
                this.stamp[i4] = 1;
                this.isSet[i4] = true;
            } else if (this.areAllFieldsSet && !this.isSet[i4]) {
                this.areAllFieldsSet = false;
            }
            i3 >>>= 1;
        }
    }

    /* access modifiers changed from: package-private */
    public final void setFieldsNormalized(int i) {
        if (i != 131071) {
            int i2 = i;
            int i3 = 0;
            while (true) {
                int[] iArr = this.fields;
                if (i3 >= iArr.length) {
                    break;
                }
                if ((i2 & 1) == 0) {
                    int[] iArr2 = this.stamp;
                    iArr[i3] = 0;
                    iArr2[i3] = 0;
                    this.isSet[i3] = false;
                }
                i2 >>= 1;
                i3++;
            }
        }
        this.areFieldsSet = true;
        this.areAllFieldsSet = false;
    }

    /* access modifiers changed from: package-private */
    public final boolean isPartiallyNormalized() {
        return this.areFieldsSet && !this.areAllFieldsSet;
    }

    /* access modifiers changed from: package-private */
    public final boolean isFullyNormalized() {
        return this.areFieldsSet && this.areAllFieldsSet;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x006b, code lost:
        if (r5[4] < r5[3]) goto L_0x006d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0075, code lost:
        if (r5[8] >= r5[3]) goto L_0x0083;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x0095, code lost:
        if (r2[4] >= r2[8]) goto L_0x009d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x00a4, code lost:
        if (r14.stamp[8] != 0) goto L_0x0098;
     */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x00bc  */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x00bf  */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x00cb  */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x00e2  */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x00ec  */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x00f6  */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x0100  */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x010c  */
    /* JADX WARNING: Removed duplicated region for block: B:71:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int selectFields() {
        /*
        // Method dump skipped, instructions count: 272
        */
        throw new UnsupportedOperationException("Method not decompiled: java.util.Calendar.selectFields():int");
    }

    public String getCalendarType() {
        return getClass().getName();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        try {
            Calendar calendar = (Calendar) obj;
            return compareTo(getMillisOf(calendar)) == 0 && this.lenient == calendar.lenient && this.firstDayOfWeek == calendar.firstDayOfWeek && this.minimalDaysInFirstWeek == calendar.minimalDaysInFirstWeek && this.zone.equals(calendar.zone);
        } catch (Exception unused) {
            return false;
        }
    }

    public int hashCode() {
        int hashCode = (this.lenient ? 1 : 0) | (this.firstDayOfWeek << 1) | (this.minimalDaysInFirstWeek << 4) | (this.zone.hashCode() << 7);
        long millisOf = getMillisOf(this);
        return (((int) millisOf) ^ ((int) (millisOf >> 32))) ^ hashCode;
    }

    public int compareTo(Calendar calendar) {
        return compareTo(getMillisOf(calendar));
    }

    public void setTimeZone(TimeZone timeZone) {
        this.zone = timeZone;
        this.sharedZone = false;
        this.areFieldsSet = false;
        this.areAllFieldsSet = false;
    }

    public TimeZone getTimeZone() {
        if (this.sharedZone) {
            this.zone = (TimeZone) this.zone.clone();
            this.sharedZone = false;
        }
        return this.zone;
    }

    /* access modifiers changed from: package-private */
    public TimeZone getZone() {
        return this.zone;
    }

    /* access modifiers changed from: package-private */
    public void setZoneShared(boolean z) {
        this.sharedZone = z;
    }

    public void setLenient(boolean z) {
        this.lenient = z;
    }

    public boolean isLenient() {
        return this.lenient;
    }

    public int getFirstDayOfWeek() {
        return this.firstDayOfWeek;
    }

    public int getMinimalDaysInFirstWeek() {
        return this.minimalDaysInFirstWeek;
    }

    public int getWeekYear() {
        throw new UnsupportedOperationException();
    }

    public void setWeekDate(int i, int i2, int i3) {
        throw new UnsupportedOperationException();
    }

    public int getActualMaximum(int i) {
        int leastMaximum = getLeastMaximum(i);
        int maximum = getMaximum(i);
        if (leastMaximum == maximum) {
            return leastMaximum;
        }
        Calendar calendar = (Calendar) clone();
        calendar.setLenient(true);
        if (i == 3 || i == 4) {
            calendar.set(7, this.firstDayOfWeek);
        }
        int i2 = leastMaximum;
        while (true) {
            calendar.set(i, leastMaximum);
            if (calendar.get(i) != leastMaximum) {
                return i2;
            }
            int i3 = leastMaximum + 1;
            if (i3 > maximum) {
                return leastMaximum;
            }
            leastMaximum = i3;
            i2 = leastMaximum;
        }
    }

    public Object clone() {
        try {
            Calendar calendar = (Calendar) super.clone();
            calendar.fields = new int[17];
            calendar.isSet = new boolean[17];
            calendar.stamp = new int[17];
            for (int i = 0; i < 17; i++) {
                calendar.fields[i] = this.fields[i];
                calendar.stamp[i] = this.stamp[i];
                calendar.isSet[i] = this.isSet[i];
            }
            calendar.zone = (TimeZone) this.zone.clone();
            return calendar;
        } catch (CloneNotSupportedException e) {
            throw new InternalError(e);
        }
    }

    static String getFieldName(int i) {
        return FIELD_NAME[i];
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(800);
        sb.append(getClass().getName());
        sb.append('[');
        appendValue(sb, "time", this.isTimeSet, this.time);
        sb.append(",areFieldsSet=");
        sb.append(this.areFieldsSet);
        sb.append(",areAllFieldsSet=");
        sb.append(this.areAllFieldsSet);
        sb.append(",lenient=");
        sb.append(this.lenient);
        sb.append(",zone=");
        sb.append(this.zone);
        appendValue(sb, ",firstDayOfWeek", true, (long) this.firstDayOfWeek);
        appendValue(sb, ",minimalDaysInFirstWeek", true, (long) this.minimalDaysInFirstWeek);
        for (int i = 0; i < 17; i++) {
            sb.append(',');
            appendValue(sb, FIELD_NAME[i], isSet(i), (long) this.fields[i]);
        }
        sb.append(']');
        return sb.toString();
    }

    private static void appendValue(StringBuilder sb, String str, boolean z, long j) {
        sb.append(str);
        sb.append('=');
        if (z) {
            sb.append(j);
        } else {
            sb.append('?');
        }
    }

    private void setWeekCountData(Locale locale) {
        int[] iArr = (int[]) cachedLocaleData.get(locale);
        if (iArr == null) {
            LocaleData localeData = LocaleData.get(locale);
            iArr = new int[]{localeData.firstDayOfWeek.intValue(), localeData.minimalDaysInFirstWeek.intValue()};
            cachedLocaleData.putIfAbsent(locale, iArr);
        }
        this.firstDayOfWeek = iArr[0];
        this.minimalDaysInFirstWeek = iArr[1];
    }

    private void updateTime() {
        computeTime();
        this.isTimeSet = true;
    }

    private int compareTo(long j) {
        int i = (getMillisOf(this) > j ? 1 : (getMillisOf(this) == j ? 0 : -1));
        if (i > 0) {
            return 1;
        }
        return i == 0 ? 0 : -1;
    }

    private static long getMillisOf(Calendar calendar) {
        if (calendar.isTimeSet) {
            return calendar.time;
        }
        Calendar calendar2 = (Calendar) calendar.clone();
        calendar2.setLenient(true);
        return calendar2.getTimeInMillis();
    }

    private void adjustStamp() {
        int i = 2;
        int i2 = 2;
        while (true) {
            int i3 = 0;
            int i4 = i;
            int i5 = 0;
            int i6 = Integer.MAX_VALUE;
            while (true) {
                int[] iArr = this.stamp;
                if (i5 >= iArr.length) {
                    break;
                }
                int i7 = iArr[i5];
                if (i7 >= i2 && i6 > i7) {
                    i6 = i7;
                }
                if (i4 < i7) {
                    i4 = i7;
                }
                i5++;
            }
            if (i4 != i6 && i6 == Integer.MAX_VALUE) {
                break;
            }
            while (true) {
                int[] iArr2 = this.stamp;
                if (i3 >= iArr2.length) {
                    break;
                }
                if (iArr2[i3] == i6) {
                    iArr2[i3] = i2;
                }
                i3++;
            }
            i2++;
            if (i6 == i4) {
                break;
            }
            i = i4;
        }
        this.nextStamp = i2;
    }

    private synchronized void writeObject(ObjectOutputStream objectOutputStream) {
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
}
