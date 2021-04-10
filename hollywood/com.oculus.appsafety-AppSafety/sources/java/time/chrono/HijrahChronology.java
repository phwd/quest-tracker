package java.time.chrono;

import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.time.Clock;
import java.time.DateTimeException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.ResolverStyle;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalField;
import java.time.temporal.ValueRange;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import sun.util.calendar.BaseCalendar;
import sun.util.logging.PlatformLogger;

public final class HijrahChronology extends AbstractChronology implements Serializable {
    public static final HijrahChronology INSTANCE;
    private static final String KEY_ID = "id";
    private static final String KEY_ISO_START = "iso-start";
    private static final String KEY_TYPE = "type";
    private static final String KEY_VERSION = "version";
    private static final String PROP_PREFIX = "calendar.hijrah.";
    private static final String PROP_TYPE_SUFFIX = ".type";
    private static final transient Properties calendarProperties;
    private static final long serialVersionUID = 3127340209035924785L;
    private final transient String calendarType;
    private transient int[] hijrahEpochMonthStartDays;
    private transient int hijrahStartEpochMonth;
    private volatile transient boolean initComplete;
    private transient int maxEpochDay;
    private transient int maxMonthLength;
    private transient int maxYearLength;
    private transient int minEpochDay;
    private transient int minMonthLength;
    private transient int minYearLength;
    private final transient String typeId;

    static {
        try {
            calendarProperties = BaseCalendar.getCalendarProperties();
            try {
                INSTANCE = new HijrahChronology("Hijrah-umalqura");
                AbstractChronology.registerChrono(INSTANCE, "Hijrah");
                AbstractChronology.registerChrono(INSTANCE, "islamic");
                registerVariants();
            } catch (DateTimeException ex) {
                PlatformLogger.getLogger("java.time.chrono").severe("Unable to initialize Hijrah calendar: Hijrah-umalqura", ex);
                throw new RuntimeException("Unable to initialize Hijrah-umalqura calendar", ex.getCause());
            }
        } catch (IOException ioe) {
            throw new InternalError("Can't initialize lib/calendars.properties", ioe);
        }
    }

    private static void registerVariants() {
        for (String name : calendarProperties.stringPropertyNames()) {
            if (name.startsWith(PROP_PREFIX)) {
                String id = name.substring(PROP_PREFIX.length());
                if (id.indexOf(46) < 0 && !id.equals(INSTANCE.getId())) {
                    try {
                        AbstractChronology.registerChrono(new HijrahChronology(id));
                    } catch (DateTimeException ex) {
                        PlatformLogger logger = PlatformLogger.getLogger("java.time.chrono");
                        logger.severe("Unable to initialize Hijrah calendar: " + id, ex);
                    }
                }
            }
        }
    }

    private HijrahChronology(String id) throws DateTimeException {
        if (!id.isEmpty()) {
            String propName = PROP_PREFIX + id + PROP_TYPE_SUFFIX;
            String calType = calendarProperties.getProperty(propName);
            if (calType == null || calType.isEmpty()) {
                throw new DateTimeException("calendarType is missing or empty for: " + propName);
            }
            this.typeId = id;
            this.calendarType = calType;
            return;
        }
        throw new IllegalArgumentException("calendar id is empty");
    }

    private void checkCalendarInit() {
        if (!this.initComplete) {
            loadCalendarData();
            this.initComplete = true;
        }
    }

    @Override // java.time.chrono.Chronology
    public String getId() {
        return this.typeId;
    }

    @Override // java.time.chrono.Chronology
    public String getCalendarType() {
        return this.calendarType;
    }

    @Override // java.time.chrono.Chronology
    public HijrahDate date(Era era, int yearOfEra, int month, int dayOfMonth) {
        return date(prolepticYear(era, yearOfEra), month, dayOfMonth);
    }

    @Override // java.time.chrono.Chronology
    public HijrahDate date(int prolepticYear, int month, int dayOfMonth) {
        return HijrahDate.of(this, prolepticYear, month, dayOfMonth);
    }

    @Override // java.time.chrono.Chronology
    public HijrahDate dateYearDay(Era era, int yearOfEra, int dayOfYear) {
        return dateYearDay(prolepticYear(era, yearOfEra), dayOfYear);
    }

    @Override // java.time.chrono.Chronology
    public HijrahDate dateYearDay(int prolepticYear, int dayOfYear) {
        HijrahDate date = HijrahDate.of(this, prolepticYear, 1, 1);
        if (dayOfYear <= date.lengthOfYear()) {
            return date.plusDays((long) (dayOfYear - 1));
        }
        throw new DateTimeException("Invalid dayOfYear: " + dayOfYear);
    }

    @Override // java.time.chrono.Chronology
    public HijrahDate dateEpochDay(long epochDay) {
        return HijrahDate.ofEpochDay(this, epochDay);
    }

    @Override // java.time.chrono.Chronology
    public HijrahDate dateNow() {
        return dateNow(Clock.systemDefaultZone());
    }

    @Override // java.time.chrono.Chronology
    public HijrahDate dateNow(ZoneId zone) {
        return dateNow(Clock.system(zone));
    }

    @Override // java.time.chrono.Chronology
    public HijrahDate dateNow(Clock clock) {
        return date((TemporalAccessor) LocalDate.now(clock));
    }

    @Override // java.time.chrono.Chronology
    public HijrahDate date(TemporalAccessor temporal) {
        if (temporal instanceof HijrahDate) {
            return (HijrahDate) temporal;
        }
        return HijrahDate.ofEpochDay(this, temporal.getLong(ChronoField.EPOCH_DAY));
    }

    /* JADX DEBUG: Type inference failed for r0v0. Raw type applied. Possible types: java.time.chrono.ChronoLocalDateTime<? extends java.time.chrono.ChronoLocalDate>, java.time.chrono.ChronoLocalDateTime<java.time.chrono.HijrahDate> */
    @Override // java.time.chrono.Chronology
    public ChronoLocalDateTime<HijrahDate> localDateTime(TemporalAccessor temporal) {
        return super.localDateTime(temporal);
    }

    /* JADX DEBUG: Type inference failed for r0v0. Raw type applied. Possible types: java.time.chrono.ChronoZonedDateTime<? extends java.time.chrono.ChronoLocalDate>, java.time.chrono.ChronoZonedDateTime<java.time.chrono.HijrahDate> */
    @Override // java.time.chrono.Chronology
    public ChronoZonedDateTime<HijrahDate> zonedDateTime(TemporalAccessor temporal) {
        return super.zonedDateTime(temporal);
    }

    /* JADX DEBUG: Type inference failed for r0v0. Raw type applied. Possible types: java.time.chrono.ChronoZonedDateTime<? extends java.time.chrono.ChronoLocalDate>, java.time.chrono.ChronoZonedDateTime<java.time.chrono.HijrahDate> */
    @Override // java.time.chrono.Chronology
    public ChronoZonedDateTime<HijrahDate> zonedDateTime(Instant instant, ZoneId zone) {
        return super.zonedDateTime(instant, zone);
    }

    @Override // java.time.chrono.Chronology
    public boolean isLeapYear(long prolepticYear) {
        checkCalendarInit();
        if (prolepticYear < ((long) getMinimumYear()) || prolepticYear > ((long) getMaximumYear()) || getYearLength((int) prolepticYear) <= 354) {
            return false;
        }
        return true;
    }

    @Override // java.time.chrono.Chronology
    public int prolepticYear(Era era, int yearOfEra) {
        if (era instanceof HijrahEra) {
            return yearOfEra;
        }
        throw new ClassCastException("Era must be HijrahEra");
    }

    @Override // java.time.chrono.Chronology
    public HijrahEra eraOf(int eraValue) {
        if (eraValue == 1) {
            return HijrahEra.AH;
        }
        throw new DateTimeException("invalid Hijrah era");
    }

    @Override // java.time.chrono.Chronology
    public List<Era> eras() {
        return Arrays.asList(HijrahEra.values());
    }

    @Override // java.time.chrono.Chronology
    public ValueRange range(ChronoField field) {
        checkCalendarInit();
        if (!(field instanceof ChronoField)) {
            return field.range();
        }
        switch (field) {
            case DAY_OF_MONTH:
                return ValueRange.of(1, 1, (long) getMinimumMonthLength(), (long) getMaximumMonthLength());
            case DAY_OF_YEAR:
                return ValueRange.of(1, (long) getMaximumDayOfYear());
            case ALIGNED_WEEK_OF_MONTH:
                return ValueRange.of(1, 5);
            case YEAR:
            case YEAR_OF_ERA:
                return ValueRange.of((long) getMinimumYear(), (long) getMaximumYear());
            case ERA:
                return ValueRange.of(1, 1);
            default:
                return field.range();
        }
    }

    @Override // java.time.chrono.Chronology, java.time.chrono.AbstractChronology
    public HijrahDate resolveDate(Map<TemporalField, Long> fieldValues, ResolverStyle resolverStyle) {
        return (HijrahDate) super.resolveDate(fieldValues, resolverStyle);
    }

    /* access modifiers changed from: package-private */
    public int checkValidYear(long prolepticYear) {
        if (prolepticYear >= ((long) getMinimumYear()) && prolepticYear <= ((long) getMaximumYear())) {
            return (int) prolepticYear;
        }
        throw new DateTimeException("Invalid Hijrah year: " + prolepticYear);
    }

    /* access modifiers changed from: package-private */
    public void checkValidDayOfYear(int dayOfYear) {
        if (dayOfYear < 1 || dayOfYear > getMaximumDayOfYear()) {
            throw new DateTimeException("Invalid Hijrah day of year: " + dayOfYear);
        }
    }

    /* access modifiers changed from: package-private */
    public void checkValidMonth(int month) {
        if (month < 1 || month > 12) {
            throw new DateTimeException("Invalid Hijrah month: " + month);
        }
    }

    /* access modifiers changed from: package-private */
    public int[] getHijrahDateInfo(int epochDay) {
        checkCalendarInit();
        if (epochDay < this.minEpochDay || epochDay >= this.maxEpochDay) {
            throw new DateTimeException("Hijrah date out of range");
        }
        int epochMonth = epochDayToEpochMonth(epochDay);
        return new int[]{epochMonthToYear(epochMonth), epochMonthToMonth(epochMonth) + 1, (epochDay - epochMonthToEpochDay(epochMonth)) + 1};
    }

    /* access modifiers changed from: package-private */
    public long getEpochDay(int prolepticYear, int monthOfYear, int dayOfMonth) {
        checkCalendarInit();
        checkValidMonth(monthOfYear);
        int epochMonth = yearToEpochMonth(prolepticYear) + (monthOfYear - 1);
        if (epochMonth < 0 || epochMonth >= this.hijrahEpochMonthStartDays.length) {
            throw new DateTimeException("Invalid Hijrah date, year: " + prolepticYear + ", month: " + monthOfYear);
        } else if (dayOfMonth >= 1 && dayOfMonth <= getMonthLength(prolepticYear, monthOfYear)) {
            return (long) (epochMonthToEpochDay(epochMonth) + (dayOfMonth - 1));
        } else {
            throw new DateTimeException("Invalid Hijrah day of month: " + dayOfMonth);
        }
    }

    /* access modifiers changed from: package-private */
    public int getDayOfYear(int prolepticYear, int month) {
        return yearMonthToDayOfYear(prolepticYear, month - 1);
    }

    /* access modifiers changed from: package-private */
    public int getMonthLength(int prolepticYear, int monthOfYear) {
        int epochMonth = yearToEpochMonth(prolepticYear) + (monthOfYear - 1);
        if (epochMonth >= 0 && epochMonth < this.hijrahEpochMonthStartDays.length) {
            return epochMonthLength(epochMonth);
        }
        throw new DateTimeException("Invalid Hijrah date, year: " + prolepticYear + ", month: " + monthOfYear);
    }

    /* access modifiers changed from: package-private */
    public int getYearLength(int prolepticYear) {
        return yearMonthToDayOfYear(prolepticYear, 12);
    }

    /* access modifiers changed from: package-private */
    public int getMinimumYear() {
        return epochMonthToYear(0);
    }

    /* access modifiers changed from: package-private */
    public int getMaximumYear() {
        return epochMonthToYear(this.hijrahEpochMonthStartDays.length - 1) - 1;
    }

    /* access modifiers changed from: package-private */
    public int getMaximumMonthLength() {
        return this.maxMonthLength;
    }

    /* access modifiers changed from: package-private */
    public int getMinimumMonthLength() {
        return this.minMonthLength;
    }

    /* access modifiers changed from: package-private */
    public int getMaximumDayOfYear() {
        return this.maxYearLength;
    }

    /* access modifiers changed from: package-private */
    public int getSmallestMaximumDayOfYear() {
        return this.minYearLength;
    }

    private int epochDayToEpochMonth(int epochDay) {
        int ndx = Arrays.binarySearch(this.hijrahEpochMonthStartDays, epochDay);
        if (ndx < 0) {
            return (-ndx) - 2;
        }
        return ndx;
    }

    private int epochMonthToYear(int epochMonth) {
        return (this.hijrahStartEpochMonth + epochMonth) / 12;
    }

    private int yearToEpochMonth(int year) {
        return (year * 12) - this.hijrahStartEpochMonth;
    }

    private int epochMonthToMonth(int epochMonth) {
        return (this.hijrahStartEpochMonth + epochMonth) % 12;
    }

    private int epochMonthToEpochDay(int epochMonth) {
        return this.hijrahEpochMonthStartDays[epochMonth];
    }

    private int yearMonthToDayOfYear(int prolepticYear, int month) {
        int epochMonthFirst = yearToEpochMonth(prolepticYear);
        return epochMonthToEpochDay(epochMonthFirst + month) - epochMonthToEpochDay(epochMonthFirst);
    }

    private int epochMonthLength(int epochMonth) {
        int[] iArr = this.hijrahEpochMonthStartDays;
        return iArr[epochMonth + 1] - iArr[epochMonth];
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0015, code lost:
        if (r1 != null) goto L_0x0017;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x001b, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x001c, code lost:
        r2.addSuppressed(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x001f, code lost:
        throw r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0014, code lost:
        r3 = move-exception;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.util.Properties readConfigProperties(java.lang.String r5) throws java.lang.Exception {
        /*
            java.util.Properties r0 = new java.util.Properties
            r0.<init>()
            java.io.InputStream r1 = java.lang.ClassLoader.getSystemResourceAsStream(r5)
            r0.load(r1)     // Catch:{ all -> 0x0012 }
            if (r1 == 0) goto L_0x0011
            r1.close()
        L_0x0011:
            return r0
        L_0x0012:
            r2 = move-exception
            throw r2     // Catch:{ all -> 0x0014 }
        L_0x0014:
            r3 = move-exception
            if (r1 == 0) goto L_0x001f
            r1.close()     // Catch:{ all -> 0x001b }
            goto L_0x001f
        L_0x001b:
            r4 = move-exception
            r2.addSuppressed(r4)
        L_0x001f:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: java.time.chrono.HijrahChronology.readConfigProperties(java.lang.String):java.util.Properties");
    }

    /* JADX WARNING: Removed duplicated region for block: B:28:0x00aa  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x012a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void loadCalendarData() {
        /*
        // Method dump skipped, instructions count: 515
        */
        throw new UnsupportedOperationException("Method not decompiled: java.time.chrono.HijrahChronology.loadCalendarData():void");
    }

    private int[] createEpochMonths(int epochDay, int minYear, int maxYear, Map<Integer, int[]> years) {
        int epochMonth = 0;
        int[] epochMonths = new int[((((maxYear - minYear) + 1) * 12) + 1)];
        this.minMonthLength = Integer.MAX_VALUE;
        this.maxMonthLength = Integer.MIN_VALUE;
        for (int year = minYear; year <= maxYear; year++) {
            int[] months = years.get(Integer.valueOf(year));
            int month = 0;
            while (month < 12) {
                int length = months[month];
                int epochMonth2 = epochMonth + 1;
                epochMonths[epochMonth] = epochDay;
                if (length < 29 || length > 32) {
                    throw new IllegalArgumentException("Invalid month length in year: " + minYear);
                }
                epochDay += length;
                this.minMonthLength = Math.min(this.minMonthLength, length);
                this.maxMonthLength = Math.max(this.maxMonthLength, length);
                month++;
                epochMonth = epochMonth2;
            }
        }
        int epochMonth3 = epochMonth + 1;
        epochMonths[epochMonth] = epochDay;
        if (epochMonth3 == epochMonths.length) {
            return epochMonths;
        }
        throw new IllegalStateException("Did not fill epochMonths exactly: ndx = " + epochMonth3 + " should be " + epochMonths.length);
    }

    private int[] parseMonths(String line) {
        int[] months = new int[12];
        String[] numbers = line.split("\\s");
        if (numbers.length == 12) {
            for (int i = 0; i < 12; i++) {
                try {
                    months[i] = Integer.valueOf(numbers[i]).intValue();
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("bad key: " + numbers[i]);
                }
            }
            return months;
        }
        throw new IllegalArgumentException("wrong number of months on line: " + Arrays.toString(numbers) + "; count: " + numbers.length);
    }

    private int[] parseYMD(String string) {
        String string2 = string.trim();
        try {
            if (string2.charAt(4) == '-' && string2.charAt(7) == '-') {
                return new int[]{Integer.valueOf(string2.substring(0, 4)).intValue(), Integer.valueOf(string2.substring(5, 7)).intValue(), Integer.valueOf(string2.substring(8, 10)).intValue()};
            }
            throw new IllegalArgumentException("date must be yyyy-MM-dd");
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException("date must be yyyy-MM-dd", ex);
        }
    }

    /* access modifiers changed from: package-private */
    @Override // java.time.chrono.AbstractChronology
    public Object writeReplace() {
        return super.writeReplace();
    }

    private void readObject(ObjectInputStream s) throws InvalidObjectException {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }
}
