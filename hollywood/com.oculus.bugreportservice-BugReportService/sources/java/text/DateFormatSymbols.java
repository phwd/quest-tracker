package java.text;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.ref.SoftReference;
import java.util.Arrays;
import java.util.Locale;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import libcore.icu.LocaleData;
import libcore.icu.TimeZoneNames;

public class DateFormatSymbols implements Serializable, Cloneable {
    private static final ConcurrentMap cachedInstances = new ConcurrentHashMap(3);
    static final long serialVersionUID = -5987973545549424702L;
    String[] ampms = null;
    volatile transient int cachedHashCode = 0;
    String[] eras = null;
    transient boolean isZoneStringsSet = false;
    private transient int lastZoneIndex = 0;
    String localPatternChars = null;
    Locale locale = null;
    String[] months = null;
    private int serialVersionOnStream = 1;
    String[] shortMonths = null;
    private String[] shortStandAloneMonths;
    private String[] shortStandAloneWeekdays;
    String[] shortWeekdays = null;
    private String[] standAloneMonths;
    private String[] standAloneWeekdays;
    private String[] tinyMonths;
    private String[] tinyStandAloneMonths;
    private String[] tinyStandAloneWeekdays;
    private String[] tinyWeekdays;
    String[] weekdays = null;
    String[][] zoneStrings = null;

    public DateFormatSymbols() {
        initializeData(Locale.getDefault(Locale.Category.FORMAT));
    }

    public DateFormatSymbols(Locale locale2) {
        initializeData(locale2);
    }

    public static final DateFormatSymbols getInstance(Locale locale2) {
        return (DateFormatSymbols) getCachedInstance(locale2).clone();
    }

    static final DateFormatSymbols getInstanceRef(Locale locale2) {
        return getCachedInstance(locale2);
    }

    private static DateFormatSymbols getCachedInstance(Locale locale2) {
        DateFormatSymbols dateFormatSymbols;
        SoftReference softReference = (SoftReference) cachedInstances.get(locale2);
        if (softReference != null && (dateFormatSymbols = (DateFormatSymbols) softReference.get()) != null) {
            return dateFormatSymbols;
        }
        DateFormatSymbols dateFormatSymbols2 = new DateFormatSymbols(locale2);
        SoftReference softReference2 = new SoftReference(dateFormatSymbols2);
        SoftReference softReference3 = (SoftReference) cachedInstances.putIfAbsent(locale2, softReference2);
        if (softReference3 == null) {
            return dateFormatSymbols2;
        }
        DateFormatSymbols dateFormatSymbols3 = (DateFormatSymbols) softReference3.get();
        if (dateFormatSymbols3 != null) {
            return dateFormatSymbols3;
        }
        cachedInstances.put(locale2, softReference2);
        return dateFormatSymbols2;
    }

    public String[] getEras() {
        String[] strArr = this.eras;
        return (String[]) Arrays.copyOf(strArr, strArr.length);
    }

    public String[] getMonths() {
        String[] strArr = this.months;
        return (String[]) Arrays.copyOf(strArr, strArr.length);
    }

    public String[] getShortMonths() {
        String[] strArr = this.shortMonths;
        return (String[]) Arrays.copyOf(strArr, strArr.length);
    }

    public String[] getWeekdays() {
        String[] strArr = this.weekdays;
        return (String[]) Arrays.copyOf(strArr, strArr.length);
    }

    public String[] getShortWeekdays() {
        String[] strArr = this.shortWeekdays;
        return (String[]) Arrays.copyOf(strArr, strArr.length);
    }

    public String[] getAmPmStrings() {
        String[] strArr = this.ampms;
        return (String[]) Arrays.copyOf(strArr, strArr.length);
    }

    public String[][] getZoneStrings() {
        return getZoneStringsImpl(true);
    }

    /* access modifiers changed from: package-private */
    public String[] getTinyMonths() {
        return this.tinyMonths;
    }

    /* access modifiers changed from: package-private */
    public String[] getStandAloneMonths() {
        return this.standAloneMonths;
    }

    /* access modifiers changed from: package-private */
    public String[] getShortStandAloneMonths() {
        return this.shortStandAloneMonths;
    }

    /* access modifiers changed from: package-private */
    public String[] getTinyStandAloneMonths() {
        return this.tinyStandAloneMonths;
    }

    /* access modifiers changed from: package-private */
    public String[] getTinyWeekdays() {
        return this.tinyWeekdays;
    }

    /* access modifiers changed from: package-private */
    public String[] getStandAloneWeekdays() {
        return this.standAloneWeekdays;
    }

    /* access modifiers changed from: package-private */
    public String[] getShortStandAloneWeekdays() {
        return this.shortStandAloneWeekdays;
    }

    /* access modifiers changed from: package-private */
    public String[] getTinyStandAloneWeekdays() {
        return this.tinyStandAloneWeekdays;
    }

    public Object clone() {
        try {
            DateFormatSymbols dateFormatSymbols = (DateFormatSymbols) super.clone();
            copyMembers(this, dateFormatSymbols);
            return dateFormatSymbols;
        } catch (CloneNotSupportedException e) {
            throw new InternalError(e);
        }
    }

    public int hashCode() {
        int i = this.cachedHashCode;
        if (i != 0) {
            return i;
        }
        int hashCode = ((((((((((((55 + Arrays.hashCode(this.eras)) * 11) + Arrays.hashCode(this.months)) * 11) + Arrays.hashCode(this.shortMonths)) * 11) + Arrays.hashCode(this.weekdays)) * 11) + Arrays.hashCode(this.shortWeekdays)) * 11) + Arrays.hashCode(this.ampms)) * 11) + Objects.hashCode(this.localPatternChars);
        this.cachedHashCode = hashCode;
        return hashCode;
    }

    public boolean equals(Object obj) {
        String str;
        if (this == obj) {
            return true;
        }
        if (obj != null && DateFormatSymbols.class == obj.getClass()) {
            DateFormatSymbols dateFormatSymbols = (DateFormatSymbols) obj;
            if (Arrays.equals(this.eras, dateFormatSymbols.eras) && Arrays.equals(this.months, dateFormatSymbols.months) && Arrays.equals(this.shortMonths, dateFormatSymbols.shortMonths) && Arrays.equals(this.tinyMonths, dateFormatSymbols.tinyMonths) && Arrays.equals(this.weekdays, dateFormatSymbols.weekdays) && Arrays.equals(this.shortWeekdays, dateFormatSymbols.shortWeekdays) && Arrays.equals(this.tinyWeekdays, dateFormatSymbols.tinyWeekdays) && Arrays.equals(this.standAloneMonths, dateFormatSymbols.standAloneMonths) && Arrays.equals(this.shortStandAloneMonths, dateFormatSymbols.shortStandAloneMonths) && Arrays.equals(this.tinyStandAloneMonths, dateFormatSymbols.tinyStandAloneMonths) && Arrays.equals(this.standAloneWeekdays, dateFormatSymbols.standAloneWeekdays) && Arrays.equals(this.shortStandAloneWeekdays, dateFormatSymbols.shortStandAloneWeekdays) && Arrays.equals(this.tinyStandAloneWeekdays, dateFormatSymbols.tinyStandAloneWeekdays) && Arrays.equals(this.ampms, dateFormatSymbols.ampms) && (((str = this.localPatternChars) != null && str.equals(dateFormatSymbols.localPatternChars)) || (this.localPatternChars == null && dateFormatSymbols.localPatternChars == null))) {
                if (this.isZoneStringsSet || dateFormatSymbols.isZoneStringsSet || !Objects.equals(this.locale, dateFormatSymbols.locale)) {
                    return Arrays.deepEquals(getZoneStringsWrapper(), dateFormatSymbols.getZoneStringsWrapper());
                }
                return true;
            }
        }
        return false;
    }

    private void initializeData(Locale locale2) {
        DateFormatSymbols dateFormatSymbols;
        SoftReference softReference = (SoftReference) cachedInstances.get(locale2);
        if (softReference == null || (dateFormatSymbols = (DateFormatSymbols) softReference.get()) == null) {
            Locale mapInvalidAndNullLocales = LocaleData.mapInvalidAndNullLocales(locale2);
            LocaleData localeData = LocaleData.get(mapInvalidAndNullLocales);
            this.locale = mapInvalidAndNullLocales;
            this.eras = localeData.eras;
            this.months = localeData.longMonthNames;
            this.shortMonths = localeData.shortMonthNames;
            this.ampms = localeData.amPm;
            this.localPatternChars = "GyMdkHmsSEDFwWahKzZYuXLcbB";
            this.weekdays = localeData.longWeekdayNames;
            this.shortWeekdays = localeData.shortWeekdayNames;
            initializeSupplementaryData(localeData);
            return;
        }
        copyMembers(dateFormatSymbols, this);
    }

    private void initializeSupplementaryData(LocaleData localeData) {
        this.tinyMonths = localeData.tinyMonthNames;
        this.tinyWeekdays = localeData.tinyWeekdayNames;
        this.standAloneMonths = localeData.longStandAloneMonthNames;
        this.shortStandAloneMonths = localeData.shortStandAloneMonthNames;
        this.tinyStandAloneMonths = localeData.tinyStandAloneMonthNames;
        this.standAloneWeekdays = localeData.longStandAloneWeekdayNames;
        this.shortStandAloneWeekdays = localeData.shortStandAloneWeekdayNames;
        this.tinyStandAloneWeekdays = localeData.tinyStandAloneWeekdayNames;
    }

    /* access modifiers changed from: package-private */
    public final int getZoneIndex(String str) {
        String[][] zoneStringsWrapper = getZoneStringsWrapper();
        int i = this.lastZoneIndex;
        if (i < zoneStringsWrapper.length && str.equals(zoneStringsWrapper[i][0])) {
            return this.lastZoneIndex;
        }
        for (int i2 = 0; i2 < zoneStringsWrapper.length; i2++) {
            if (str.equals(zoneStringsWrapper[i2][0])) {
                this.lastZoneIndex = i2;
                return i2;
            }
        }
        return -1;
    }

    /* access modifiers changed from: package-private */
    public final String[][] getZoneStringsWrapper() {
        if (isSubclassObject()) {
            return getZoneStrings();
        }
        return getZoneStringsImpl(false);
    }

    private synchronized String[][] internalZoneStrings() {
        if (this.zoneStrings == null) {
            this.zoneStrings = TimeZoneNames.getZoneStrings(this.locale);
        }
        return this.zoneStrings;
    }

    private String[][] getZoneStringsImpl(boolean z) {
        String[][] internalZoneStrings = internalZoneStrings();
        if (!z) {
            return internalZoneStrings;
        }
        int length = internalZoneStrings.length;
        String[][] strArr = new String[length][];
        for (int i = 0; i < length; i++) {
            strArr[i] = (String[]) Arrays.copyOf(internalZoneStrings[i], internalZoneStrings[i].length);
        }
        return strArr;
    }

    private boolean isSubclassObject() {
        return !DateFormatSymbols.class.getName().equals("java.text.DateFormatSymbols");
    }

    private void copyMembers(DateFormatSymbols dateFormatSymbols, DateFormatSymbols dateFormatSymbols2) {
        dateFormatSymbols2.locale = dateFormatSymbols.locale;
        String[] strArr = dateFormatSymbols.eras;
        dateFormatSymbols2.eras = (String[]) Arrays.copyOf(strArr, strArr.length);
        String[] strArr2 = dateFormatSymbols.months;
        dateFormatSymbols2.months = (String[]) Arrays.copyOf(strArr2, strArr2.length);
        String[] strArr3 = dateFormatSymbols.shortMonths;
        dateFormatSymbols2.shortMonths = (String[]) Arrays.copyOf(strArr3, strArr3.length);
        String[] strArr4 = dateFormatSymbols.weekdays;
        dateFormatSymbols2.weekdays = (String[]) Arrays.copyOf(strArr4, strArr4.length);
        String[] strArr5 = dateFormatSymbols.shortWeekdays;
        dateFormatSymbols2.shortWeekdays = (String[]) Arrays.copyOf(strArr5, strArr5.length);
        String[] strArr6 = dateFormatSymbols.ampms;
        dateFormatSymbols2.ampms = (String[]) Arrays.copyOf(strArr6, strArr6.length);
        if (dateFormatSymbols.zoneStrings != null) {
            dateFormatSymbols2.zoneStrings = dateFormatSymbols.getZoneStringsImpl(true);
        } else {
            dateFormatSymbols2.zoneStrings = null;
        }
        dateFormatSymbols2.localPatternChars = dateFormatSymbols.localPatternChars;
        dateFormatSymbols2.cachedHashCode = 0;
        dateFormatSymbols2.tinyMonths = dateFormatSymbols.tinyMonths;
        dateFormatSymbols2.tinyWeekdays = dateFormatSymbols.tinyWeekdays;
        dateFormatSymbols2.standAloneMonths = dateFormatSymbols.standAloneMonths;
        dateFormatSymbols2.shortStandAloneMonths = dateFormatSymbols.shortStandAloneMonths;
        dateFormatSymbols2.tinyStandAloneMonths = dateFormatSymbols.tinyStandAloneMonths;
        dateFormatSymbols2.standAloneWeekdays = dateFormatSymbols.standAloneWeekdays;
        dateFormatSymbols2.shortStandAloneWeekdays = dateFormatSymbols.shortStandAloneWeekdays;
        dateFormatSymbols2.tinyStandAloneWeekdays = dateFormatSymbols.tinyStandAloneWeekdays;
    }

    private void readObject(ObjectInputStream objectInputStream) {
        objectInputStream.defaultReadObject();
        throw null;
    }

    private void writeObject(ObjectOutputStream objectOutputStream) {
        internalZoneStrings();
        objectOutputStream.defaultWriteObject();
        throw null;
    }
}
