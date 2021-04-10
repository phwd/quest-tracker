package java.time;

import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.Serializable;
import java.time.chrono.ChronoLocalDateTime;
import java.time.chrono.IsoChronology;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAmount;
import java.time.temporal.TemporalField;
import java.time.temporal.TemporalQueries;
import java.time.temporal.TemporalQuery;
import java.time.temporal.TemporalUnit;
import java.time.temporal.UnsupportedTemporalTypeException;
import java.time.temporal.ValueRange;
import java.util.Comparator;
import java.util.Objects;

public final class OffsetDateTime implements Temporal, TemporalAdjuster, Comparable<OffsetDateTime>, Serializable {
    public static final OffsetDateTime MAX = LocalDateTime.MAX.atOffset(ZoneOffset.MIN);
    public static final OffsetDateTime MIN = LocalDateTime.MIN.atOffset(ZoneOffset.MAX);
    private static final long serialVersionUID = 2287754244819255394L;
    private final LocalDateTime dateTime;
    private final ZoneOffset offset;

    public static Comparator<OffsetDateTime> timeLineOrder() {
        return $$Lambda$OffsetDateTime$d2QSmDYEJWeXCg2rcQuxVNPk3n4.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static int compareInstant(OffsetDateTime datetime1, OffsetDateTime datetime2) {
        if (datetime1.getOffset().equals(datetime2.getOffset())) {
            return datetime1.toLocalDateTime().compareTo((ChronoLocalDateTime<?>) datetime2.toLocalDateTime());
        }
        int cmp = Long.compare(datetime1.toEpochSecond(), datetime2.toEpochSecond());
        if (cmp == 0) {
            return datetime1.toLocalTime().getNano() - datetime2.toLocalTime().getNano();
        }
        return cmp;
    }

    public static OffsetDateTime now() {
        return now(Clock.systemDefaultZone());
    }

    public static OffsetDateTime now(ZoneId zone) {
        return now(Clock.system(zone));
    }

    public static OffsetDateTime now(Clock clock) {
        Objects.requireNonNull(clock, "clock");
        Instant now = clock.instant();
        return ofInstant(now, clock.getZone().getRules().getOffset(now));
    }

    public static OffsetDateTime of(LocalDate date, LocalTime time, ZoneOffset offset2) {
        return new OffsetDateTime(LocalDateTime.of(date, time), offset2);
    }

    public static OffsetDateTime of(LocalDateTime dateTime2, ZoneOffset offset2) {
        return new OffsetDateTime(dateTime2, offset2);
    }

    public static OffsetDateTime of(int year, int month, int dayOfMonth, int hour, int minute, int second, int nanoOfSecond, ZoneOffset offset2) {
        return new OffsetDateTime(LocalDateTime.of(year, month, dayOfMonth, hour, minute, second, nanoOfSecond), offset2);
    }

    public static OffsetDateTime ofInstant(Instant instant, ZoneId zone) {
        Objects.requireNonNull(instant, "instant");
        Objects.requireNonNull(zone, "zone");
        ZoneOffset offset2 = zone.getRules().getOffset(instant);
        return new OffsetDateTime(LocalDateTime.ofEpochSecond(instant.getEpochSecond(), instant.getNano(), offset2), offset2);
    }

    public static OffsetDateTime from(TemporalAccessor temporal) {
        if (temporal instanceof OffsetDateTime) {
            return (OffsetDateTime) temporal;
        }
        try {
            ZoneOffset offset2 = ZoneOffset.from(temporal);
            LocalDate date = (LocalDate) temporal.query(TemporalQueries.localDate());
            LocalTime time = (LocalTime) temporal.query(TemporalQueries.localTime());
            if (date == null || time == null) {
                return ofInstant(Instant.from(temporal), offset2);
            }
            return of(date, time, offset2);
        } catch (DateTimeException ex) {
            throw new DateTimeException("Unable to obtain OffsetDateTime from TemporalAccessor: " + ((Object) temporal) + " of type " + temporal.getClass().getName(), ex);
        }
    }

    public static OffsetDateTime parse(CharSequence text) {
        return parse(text, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
    }

    public static OffsetDateTime parse(CharSequence text, DateTimeFormatter formatter) {
        Objects.requireNonNull(formatter, "formatter");
        return (OffsetDateTime) formatter.parse(text, $$Lambda$sdbO4BiAEcJ0AbaR8ZYLiX9zuo.INSTANCE);
    }

    private OffsetDateTime(LocalDateTime dateTime2, ZoneOffset offset2) {
        this.dateTime = (LocalDateTime) Objects.requireNonNull(dateTime2, "dateTime");
        this.offset = (ZoneOffset) Objects.requireNonNull(offset2, "offset");
    }

    private OffsetDateTime with(LocalDateTime dateTime2, ZoneOffset offset2) {
        if (this.dateTime != dateTime2 || !this.offset.equals(offset2)) {
            return new OffsetDateTime(dateTime2, offset2);
        }
        return this;
    }

    @Override // java.time.temporal.TemporalAccessor
    public boolean isSupported(TemporalField field) {
        return (field instanceof ChronoField) || (field != null && field.isSupportedBy(this));
    }

    @Override // java.time.temporal.Temporal
    public boolean isSupported(TemporalUnit unit) {
        return unit instanceof ChronoUnit ? unit != ChronoUnit.FOREVER : unit != null && unit.isSupportedBy(this);
    }

    @Override // java.time.temporal.TemporalAccessor
    public ValueRange range(TemporalField field) {
        if (!(field instanceof ChronoField)) {
            return field.rangeRefinedBy(this);
        }
        if (field == ChronoField.INSTANT_SECONDS || field == ChronoField.OFFSET_SECONDS) {
            return field.range();
        }
        return this.dateTime.range(field);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: java.time.OffsetDateTime$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$java$time$temporal$ChronoField = new int[ChronoField.values().length];

        static {
            try {
                $SwitchMap$java$time$temporal$ChronoField[ChronoField.INSTANT_SECONDS.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$java$time$temporal$ChronoField[ChronoField.OFFSET_SECONDS.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
        }
    }

    @Override // java.time.temporal.TemporalAccessor
    public int get(TemporalField field) {
        if (!(field instanceof ChronoField)) {
            return super.get(field);
        }
        int i = AnonymousClass1.$SwitchMap$java$time$temporal$ChronoField[((ChronoField) field).ordinal()];
        if (i == 1) {
            throw new UnsupportedTemporalTypeException("Invalid field 'InstantSeconds' for get() method, use getLong() instead");
        } else if (i != 2) {
            return this.dateTime.get(field);
        } else {
            return getOffset().getTotalSeconds();
        }
    }

    @Override // java.time.temporal.TemporalAccessor
    public long getLong(TemporalField field) {
        if (!(field instanceof ChronoField)) {
            return field.getFrom(this);
        }
        int i = AnonymousClass1.$SwitchMap$java$time$temporal$ChronoField[((ChronoField) field).ordinal()];
        if (i == 1) {
            return toEpochSecond();
        }
        if (i != 2) {
            return this.dateTime.getLong(field);
        }
        return (long) getOffset().getTotalSeconds();
    }

    public ZoneOffset getOffset() {
        return this.offset;
    }

    public OffsetDateTime withOffsetSameLocal(ZoneOffset offset2) {
        return with(this.dateTime, offset2);
    }

    public OffsetDateTime withOffsetSameInstant(ZoneOffset offset2) {
        if (offset2.equals(this.offset)) {
            return this;
        }
        return new OffsetDateTime(this.dateTime.plusSeconds((long) (offset2.getTotalSeconds() - this.offset.getTotalSeconds())), offset2);
    }

    public LocalDateTime toLocalDateTime() {
        return this.dateTime;
    }

    public LocalDate toLocalDate() {
        return this.dateTime.toLocalDate();
    }

    public int getYear() {
        return this.dateTime.getYear();
    }

    public int getMonthValue() {
        return this.dateTime.getMonthValue();
    }

    public Month getMonth() {
        return this.dateTime.getMonth();
    }

    public int getDayOfMonth() {
        return this.dateTime.getDayOfMonth();
    }

    public int getDayOfYear() {
        return this.dateTime.getDayOfYear();
    }

    public DayOfWeek getDayOfWeek() {
        return this.dateTime.getDayOfWeek();
    }

    public LocalTime toLocalTime() {
        return this.dateTime.toLocalTime();
    }

    public int getHour() {
        return this.dateTime.getHour();
    }

    public int getMinute() {
        return this.dateTime.getMinute();
    }

    public int getSecond() {
        return this.dateTime.getSecond();
    }

    public int getNano() {
        return this.dateTime.getNano();
    }

    /* JADX DEBUG: Multi-variable search result rejected for r2v0, resolved type: java.time.OffsetDateTime */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v2, types: [java.time.LocalDateTime] */
    /* JADX WARNING: Unknown variable types count: 1 */
    @Override // java.time.temporal.Temporal
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.time.OffsetDateTime with(java.time.temporal.TemporalAdjuster r3) {
        /*
            r2 = this;
            boolean r0 = r3 instanceof java.time.LocalDate
            if (r0 != 0) goto L_0x0038
            boolean r0 = r3 instanceof java.time.LocalTime
            if (r0 != 0) goto L_0x0038
            boolean r0 = r3 instanceof java.time.LocalDateTime
            if (r0 == 0) goto L_0x000d
            goto L_0x0038
        L_0x000d:
            boolean r0 = r3 instanceof java.time.Instant
            if (r0 == 0) goto L_0x001b
            r0 = r3
            java.time.Instant r0 = (java.time.Instant) r0
            java.time.ZoneOffset r1 = r2.offset
            java.time.OffsetDateTime r0 = ofInstant(r0, r1)
            return r0
        L_0x001b:
            boolean r0 = r3 instanceof java.time.ZoneOffset
            if (r0 == 0) goto L_0x0029
            java.time.LocalDateTime r0 = r2.dateTime
            r1 = r3
            java.time.ZoneOffset r1 = (java.time.ZoneOffset) r1
            java.time.OffsetDateTime r0 = r2.with(r0, r1)
            return r0
        L_0x0029:
            boolean r0 = r3 instanceof java.time.OffsetDateTime
            if (r0 == 0) goto L_0x0031
            r0 = r3
            java.time.OffsetDateTime r0 = (java.time.OffsetDateTime) r0
            return r0
        L_0x0031:
            java.time.temporal.Temporal r0 = r3.adjustInto(r2)
            java.time.OffsetDateTime r0 = (java.time.OffsetDateTime) r0
            return r0
        L_0x0038:
            java.time.LocalDateTime r0 = r2.dateTime
            java.time.LocalDateTime r0 = r0.with(r3)
            java.time.ZoneOffset r1 = r2.offset
            java.time.OffsetDateTime r0 = r2.with(r0, r1)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: java.time.OffsetDateTime.with(java.time.temporal.TemporalAdjuster):java.time.OffsetDateTime");
    }

    /* JADX DEBUG: Multi-variable search result rejected for r3v0, resolved type: java.time.OffsetDateTime */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v9, types: [java.time.LocalDateTime] */
    /* JADX WARNING: Unknown variable types count: 1 */
    @Override // java.time.temporal.Temporal
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.time.OffsetDateTime with(java.time.temporal.TemporalField r4, long r5) {
        /*
            r3 = this;
            boolean r0 = r4 instanceof java.time.temporal.ChronoField
            if (r0 == 0) goto L_0x0041
            r0 = r4
            java.time.temporal.ChronoField r0 = (java.time.temporal.ChronoField) r0
            int[] r1 = java.time.OffsetDateTime.AnonymousClass1.$SwitchMap$java$time$temporal$ChronoField
            int r2 = r0.ordinal()
            r1 = r1[r2]
            r2 = 1
            if (r1 == r2) goto L_0x0031
            r2 = 2
            if (r1 == r2) goto L_0x0022
            java.time.LocalDateTime r1 = r3.dateTime
            java.time.LocalDateTime r1 = r1.with(r4, r5)
            java.time.ZoneOffset r2 = r3.offset
            java.time.OffsetDateTime r1 = r3.with(r1, r2)
            return r1
        L_0x0022:
            java.time.LocalDateTime r1 = r3.dateTime
            int r2 = r0.checkValidIntValue(r5)
            java.time.ZoneOffset r2 = java.time.ZoneOffset.ofTotalSeconds(r2)
            java.time.OffsetDateTime r1 = r3.with(r1, r2)
            return r1
        L_0x0031:
            int r1 = r3.getNano()
            long r1 = (long) r1
            java.time.Instant r1 = java.time.Instant.ofEpochSecond(r5, r1)
            java.time.ZoneOffset r2 = r3.offset
            java.time.OffsetDateTime r1 = ofInstant(r1, r2)
            return r1
        L_0x0041:
            java.time.temporal.Temporal r0 = r4.adjustInto(r3, r5)
            java.time.OffsetDateTime r0 = (java.time.OffsetDateTime) r0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: java.time.OffsetDateTime.with(java.time.temporal.TemporalField, long):java.time.OffsetDateTime");
    }

    public OffsetDateTime withYear(int year) {
        return with(this.dateTime.withYear(year), this.offset);
    }

    public OffsetDateTime withMonth(int month) {
        return with(this.dateTime.withMonth(month), this.offset);
    }

    public OffsetDateTime withDayOfMonth(int dayOfMonth) {
        return with(this.dateTime.withDayOfMonth(dayOfMonth), this.offset);
    }

    public OffsetDateTime withDayOfYear(int dayOfYear) {
        return with(this.dateTime.withDayOfYear(dayOfYear), this.offset);
    }

    public OffsetDateTime withHour(int hour) {
        return with(this.dateTime.withHour(hour), this.offset);
    }

    public OffsetDateTime withMinute(int minute) {
        return with(this.dateTime.withMinute(minute), this.offset);
    }

    public OffsetDateTime withSecond(int second) {
        return with(this.dateTime.withSecond(second), this.offset);
    }

    public OffsetDateTime withNano(int nanoOfSecond) {
        return with(this.dateTime.withNano(nanoOfSecond), this.offset);
    }

    public OffsetDateTime truncatedTo(TemporalUnit unit) {
        return with(this.dateTime.truncatedTo(unit), this.offset);
    }

    @Override // java.time.temporal.Temporal
    public OffsetDateTime plus(TemporalAmount amountToAdd) {
        return (OffsetDateTime) amountToAdd.addTo(this);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r2v0, resolved type: java.time.OffsetDateTime */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v4, types: [java.time.LocalDateTime] */
    /* JADX WARNING: Unknown variable types count: 1 */
    @Override // java.time.temporal.Temporal
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.time.OffsetDateTime plus(long r3, java.time.temporal.TemporalUnit r5) {
        /*
            r2 = this;
            boolean r0 = r5 instanceof java.time.temporal.ChronoUnit
            if (r0 == 0) goto L_0x0011
            java.time.LocalDateTime r0 = r2.dateTime
            java.time.LocalDateTime r0 = r0.plus(r3, r5)
            java.time.ZoneOffset r1 = r2.offset
            java.time.OffsetDateTime r0 = r2.with(r0, r1)
            return r0
        L_0x0011:
            java.time.temporal.Temporal r0 = r5.addTo(r2, r3)
            java.time.OffsetDateTime r0 = (java.time.OffsetDateTime) r0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: java.time.OffsetDateTime.plus(long, java.time.temporal.TemporalUnit):java.time.OffsetDateTime");
    }

    public OffsetDateTime plusYears(long years) {
        return with(this.dateTime.plusYears(years), this.offset);
    }

    public OffsetDateTime plusMonths(long months) {
        return with(this.dateTime.plusMonths(months), this.offset);
    }

    public OffsetDateTime plusWeeks(long weeks) {
        return with(this.dateTime.plusWeeks(weeks), this.offset);
    }

    public OffsetDateTime plusDays(long days) {
        return with(this.dateTime.plusDays(days), this.offset);
    }

    public OffsetDateTime plusHours(long hours) {
        return with(this.dateTime.plusHours(hours), this.offset);
    }

    public OffsetDateTime plusMinutes(long minutes) {
        return with(this.dateTime.plusMinutes(minutes), this.offset);
    }

    public OffsetDateTime plusSeconds(long seconds) {
        return with(this.dateTime.plusSeconds(seconds), this.offset);
    }

    public OffsetDateTime plusNanos(long nanos) {
        return with(this.dateTime.plusNanos(nanos), this.offset);
    }

    @Override // java.time.temporal.Temporal
    public OffsetDateTime minus(TemporalAmount amountToSubtract) {
        return (OffsetDateTime) amountToSubtract.subtractFrom(this);
    }

    @Override // java.time.temporal.Temporal
    public OffsetDateTime minus(long amountToSubtract, TemporalUnit unit) {
        return amountToSubtract == Long.MIN_VALUE ? plus(Long.MAX_VALUE, unit).plus(1L, unit) : plus(-amountToSubtract, unit);
    }

    public OffsetDateTime minusYears(long years) {
        return years == Long.MIN_VALUE ? plusYears(Long.MAX_VALUE).plusYears(1) : plusYears(-years);
    }

    public OffsetDateTime minusMonths(long months) {
        return months == Long.MIN_VALUE ? plusMonths(Long.MAX_VALUE).plusMonths(1) : plusMonths(-months);
    }

    public OffsetDateTime minusWeeks(long weeks) {
        return weeks == Long.MIN_VALUE ? plusWeeks(Long.MAX_VALUE).plusWeeks(1) : plusWeeks(-weeks);
    }

    public OffsetDateTime minusDays(long days) {
        return days == Long.MIN_VALUE ? plusDays(Long.MAX_VALUE).plusDays(1) : plusDays(-days);
    }

    public OffsetDateTime minusHours(long hours) {
        return hours == Long.MIN_VALUE ? plusHours(Long.MAX_VALUE).plusHours(1) : plusHours(-hours);
    }

    public OffsetDateTime minusMinutes(long minutes) {
        return minutes == Long.MIN_VALUE ? plusMinutes(Long.MAX_VALUE).plusMinutes(1) : plusMinutes(-minutes);
    }

    public OffsetDateTime minusSeconds(long seconds) {
        return seconds == Long.MIN_VALUE ? plusSeconds(Long.MAX_VALUE).plusSeconds(1) : plusSeconds(-seconds);
    }

    public OffsetDateTime minusNanos(long nanos) {
        return nanos == Long.MIN_VALUE ? plusNanos(Long.MAX_VALUE).plusNanos(1) : plusNanos(-nanos);
    }

    @Override // java.time.temporal.TemporalAccessor
    public <R> R query(TemporalQuery<R> query) {
        if (query == TemporalQueries.offset() || query == TemporalQueries.zone()) {
            return (R) getOffset();
        }
        if (query == TemporalQueries.zoneId()) {
            return null;
        }
        return query == TemporalQueries.localDate() ? (R) toLocalDate() : query == TemporalQueries.localTime() ? (R) toLocalTime() : query == TemporalQueries.chronology() ? (R) IsoChronology.INSTANCE : query == TemporalQueries.precision() ? (R) ChronoUnit.NANOS : query.queryFrom(this);
    }

    @Override // java.time.temporal.TemporalAdjuster
    public Temporal adjustInto(Temporal temporal) {
        return temporal.with(ChronoField.EPOCH_DAY, toLocalDate().toEpochDay()).with(ChronoField.NANO_OF_DAY, toLocalTime().toNanoOfDay()).with(ChronoField.OFFSET_SECONDS, (long) getOffset().getTotalSeconds());
    }

    @Override // java.time.temporal.Temporal
    public long until(Temporal endExclusive, TemporalUnit unit) {
        OffsetDateTime end = from(endExclusive);
        if (!(unit instanceof ChronoUnit)) {
            return unit.between(this, end);
        }
        return this.dateTime.until(end.withOffsetSameInstant(this.offset).dateTime, unit);
    }

    public String format(DateTimeFormatter formatter) {
        Objects.requireNonNull(formatter, "formatter");
        return formatter.format(this);
    }

    public ZonedDateTime atZoneSameInstant(ZoneId zone) {
        return ZonedDateTime.ofInstant(this.dateTime, this.offset, zone);
    }

    public ZonedDateTime atZoneSimilarLocal(ZoneId zone) {
        return ZonedDateTime.ofLocal(this.dateTime, zone, this.offset);
    }

    public OffsetTime toOffsetTime() {
        return OffsetTime.of(this.dateTime.toLocalTime(), this.offset);
    }

    public ZonedDateTime toZonedDateTime() {
        return ZonedDateTime.of(this.dateTime, this.offset);
    }

    public Instant toInstant() {
        return this.dateTime.toInstant(this.offset);
    }

    public long toEpochSecond() {
        return this.dateTime.toEpochSecond(this.offset);
    }

    public int compareTo(OffsetDateTime other) {
        int cmp = compareInstant(this, other);
        if (cmp == 0) {
            return toLocalDateTime().compareTo((ChronoLocalDateTime<?>) other.toLocalDateTime());
        }
        return cmp;
    }

    public boolean isAfter(OffsetDateTime other) {
        long thisEpochSec = toEpochSecond();
        long otherEpochSec = other.toEpochSecond();
        return thisEpochSec > otherEpochSec || (thisEpochSec == otherEpochSec && toLocalTime().getNano() > other.toLocalTime().getNano());
    }

    public boolean isBefore(OffsetDateTime other) {
        long thisEpochSec = toEpochSecond();
        long otherEpochSec = other.toEpochSecond();
        return thisEpochSec < otherEpochSec || (thisEpochSec == otherEpochSec && toLocalTime().getNano() < other.toLocalTime().getNano());
    }

    public boolean isEqual(OffsetDateTime other) {
        return toEpochSecond() == other.toEpochSecond() && toLocalTime().getNano() == other.toLocalTime().getNano();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof OffsetDateTime)) {
            return false;
        }
        OffsetDateTime other = (OffsetDateTime) obj;
        if (!this.dateTime.equals(other.dateTime) || !this.offset.equals(other.offset)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return this.dateTime.hashCode() ^ this.offset.hashCode();
    }

    public String toString() {
        return this.dateTime.toString() + this.offset.toString();
    }

    private Object writeReplace() {
        return new Ser((byte) 10, this);
    }

    private void readObject(ObjectInputStream s) throws InvalidObjectException {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    /* access modifiers changed from: package-private */
    public void writeExternal(ObjectOutput out) throws IOException {
        this.dateTime.writeExternal(out);
        this.offset.writeExternal(out);
    }

    static OffsetDateTime readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        return of(LocalDateTime.readExternal(in), ZoneOffset.readExternal(in));
    }
}
