package java.time;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.time.chrono.ChronoLocalDateTime;
import java.time.chrono.ChronoZonedDateTime;
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
import java.time.temporal.ValueRange;
import java.util.Objects;
import sun.security.x509.InvalidityDateExtension;

public final class LocalDateTime implements Temporal, TemporalAdjuster, ChronoLocalDateTime<LocalDate>, Serializable {
    public static final LocalDateTime MAX = of(LocalDate.MAX, LocalTime.MAX);
    public static final LocalDateTime MIN = of(LocalDate.MIN, LocalTime.MIN);
    private static final long serialVersionUID = 6207766400415563566L;
    private final LocalDate date;
    private final LocalTime time;

    public static LocalDateTime now() {
        return now(Clock.systemDefaultZone());
    }

    public static LocalDateTime now(ZoneId zone) {
        return now(Clock.system(zone));
    }

    public static LocalDateTime now(Clock clock) {
        Objects.requireNonNull(clock, "clock");
        Instant now = clock.instant();
        return ofEpochSecond(now.getEpochSecond(), now.getNano(), clock.getZone().getRules().getOffset(now));
    }

    public static LocalDateTime of(int year, Month month, int dayOfMonth, int hour, int minute) {
        return new LocalDateTime(LocalDate.of(year, month, dayOfMonth), LocalTime.of(hour, minute));
    }

    public static LocalDateTime of(int year, Month month, int dayOfMonth, int hour, int minute, int second) {
        return new LocalDateTime(LocalDate.of(year, month, dayOfMonth), LocalTime.of(hour, minute, second));
    }

    public static LocalDateTime of(int year, Month month, int dayOfMonth, int hour, int minute, int second, int nanoOfSecond) {
        return new LocalDateTime(LocalDate.of(year, month, dayOfMonth), LocalTime.of(hour, minute, second, nanoOfSecond));
    }

    public static LocalDateTime of(int year, int month, int dayOfMonth, int hour, int minute) {
        return new LocalDateTime(LocalDate.of(year, month, dayOfMonth), LocalTime.of(hour, minute));
    }

    public static LocalDateTime of(int year, int month, int dayOfMonth, int hour, int minute, int second) {
        return new LocalDateTime(LocalDate.of(year, month, dayOfMonth), LocalTime.of(hour, minute, second));
    }

    public static LocalDateTime of(int year, int month, int dayOfMonth, int hour, int minute, int second, int nanoOfSecond) {
        return new LocalDateTime(LocalDate.of(year, month, dayOfMonth), LocalTime.of(hour, minute, second, nanoOfSecond));
    }

    public static LocalDateTime of(LocalDate date2, LocalTime time2) {
        Objects.requireNonNull(date2, InvalidityDateExtension.DATE);
        Objects.requireNonNull(time2, "time");
        return new LocalDateTime(date2, time2);
    }

    public static LocalDateTime ofInstant(Instant instant, ZoneId zone) {
        Objects.requireNonNull(instant, "instant");
        Objects.requireNonNull(zone, "zone");
        return ofEpochSecond(instant.getEpochSecond(), instant.getNano(), zone.getRules().getOffset(instant));
    }

    public static LocalDateTime ofEpochSecond(long epochSecond, int nanoOfSecond, ZoneOffset offset) {
        Objects.requireNonNull(offset, "offset");
        ChronoField.NANO_OF_SECOND.checkValidValue((long) nanoOfSecond);
        long localSecond = ((long) offset.getTotalSeconds()) + epochSecond;
        return new LocalDateTime(LocalDate.ofEpochDay(Math.floorDiv(localSecond, 86400)), LocalTime.ofNanoOfDay((((long) ((int) Math.floorMod(localSecond, 86400))) * 1000000000) + ((long) nanoOfSecond)));
    }

    public static LocalDateTime from(TemporalAccessor temporal) {
        if (temporal instanceof LocalDateTime) {
            return (LocalDateTime) temporal;
        }
        if (temporal instanceof ZonedDateTime) {
            return ((ZonedDateTime) temporal).toLocalDateTime();
        }
        if (temporal instanceof OffsetDateTime) {
            return ((OffsetDateTime) temporal).toLocalDateTime();
        }
        try {
            return new LocalDateTime(LocalDate.from(temporal), LocalTime.from(temporal));
        } catch (DateTimeException ex) {
            throw new DateTimeException("Unable to obtain LocalDateTime from TemporalAccessor: " + ((Object) temporal) + " of type " + temporal.getClass().getName(), ex);
        }
    }

    public static LocalDateTime parse(CharSequence text) {
        return parse(text, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }

    public static LocalDateTime parse(CharSequence text, DateTimeFormatter formatter) {
        Objects.requireNonNull(formatter, "formatter");
        return (LocalDateTime) formatter.parse(text, $$Lambda$JBWLm7jbzHiLhHxYdB_IuO_vFO8.INSTANCE);
    }

    private LocalDateTime(LocalDate date2, LocalTime time2) {
        this.date = date2;
        this.time = time2;
    }

    private LocalDateTime with(LocalDate newDate, LocalTime newTime) {
        if (this.date == newDate && this.time == newTime) {
            return this;
        }
        return new LocalDateTime(newDate, newTime);
    }

    @Override // java.time.chrono.ChronoLocalDateTime, java.time.temporal.TemporalAccessor
    public boolean isSupported(TemporalField field) {
        if (!(field instanceof ChronoField)) {
            return field != null && field.isSupportedBy(this);
        }
        ChronoField f = (ChronoField) field;
        return f.isDateBased() || f.isTimeBased();
    }

    @Override // java.time.chrono.ChronoLocalDateTime, java.time.temporal.Temporal
    public boolean isSupported(TemporalUnit unit) {
        return super.isSupported(unit);
    }

    @Override // java.time.temporal.TemporalAccessor
    public ValueRange range(TemporalField field) {
        if (field instanceof ChronoField) {
            return ((ChronoField) field).isTimeBased() ? this.time.range(field) : this.date.range(field);
        }
        return field.rangeRefinedBy(this);
    }

    @Override // java.time.temporal.TemporalAccessor
    public int get(TemporalField field) {
        if (field instanceof ChronoField) {
            return ((ChronoField) field).isTimeBased() ? this.time.get(field) : this.date.get(field);
        }
        return super.get(field);
    }

    @Override // java.time.temporal.TemporalAccessor
    public long getLong(TemporalField field) {
        if (field instanceof ChronoField) {
            return ((ChronoField) field).isTimeBased() ? this.time.getLong(field) : this.date.getLong(field);
        }
        return field.getFrom(this);
    }

    @Override // java.time.chrono.ChronoLocalDateTime
    public LocalDate toLocalDate() {
        return this.date;
    }

    public int getYear() {
        return this.date.getYear();
    }

    public int getMonthValue() {
        return this.date.getMonthValue();
    }

    public Month getMonth() {
        return this.date.getMonth();
    }

    public int getDayOfMonth() {
        return this.date.getDayOfMonth();
    }

    public int getDayOfYear() {
        return this.date.getDayOfYear();
    }

    public DayOfWeek getDayOfWeek() {
        return this.date.getDayOfWeek();
    }

    @Override // java.time.chrono.ChronoLocalDateTime
    public LocalTime toLocalTime() {
        return this.time;
    }

    public int getHour() {
        return this.time.getHour();
    }

    public int getMinute() {
        return this.time.getMinute();
    }

    public int getSecond() {
        return this.time.getSecond();
    }

    public int getNano() {
        return this.time.getNano();
    }

    /* Return type fixed from 'java.time.LocalDateTime' to match base method */
    @Override // java.time.chrono.ChronoLocalDateTime, java.time.chrono.ChronoLocalDateTime, java.time.temporal.Temporal
    public ChronoLocalDateTime<LocalDate> with(TemporalAdjuster adjuster) {
        if (adjuster instanceof LocalDate) {
            return with((LocalDate) adjuster, this.time);
        }
        if (adjuster instanceof LocalTime) {
            return with(this.date, (LocalTime) adjuster);
        }
        if (adjuster instanceof LocalDateTime) {
            return (LocalDateTime) adjuster;
        }
        return (LocalDateTime) adjuster.adjustInto(this);
    }

    /* Return type fixed from 'java.time.LocalDateTime' to match base method */
    @Override // java.time.chrono.ChronoLocalDateTime, java.time.chrono.ChronoLocalDateTime, java.time.temporal.Temporal
    public ChronoLocalDateTime<LocalDate> with(TemporalField field, long newValue) {
        if (!(field instanceof ChronoField)) {
            return (LocalDateTime) field.adjustInto(this, newValue);
        }
        if (((ChronoField) field).isTimeBased()) {
            return with(this.date, this.time.with(field, newValue));
        }
        return with(this.date.with(field, newValue), this.time);
    }

    public LocalDateTime withYear(int year) {
        return with(this.date.withYear(year), this.time);
    }

    public LocalDateTime withMonth(int month) {
        return with(this.date.withMonth(month), this.time);
    }

    public LocalDateTime withDayOfMonth(int dayOfMonth) {
        return with(this.date.withDayOfMonth(dayOfMonth), this.time);
    }

    public LocalDateTime withDayOfYear(int dayOfYear) {
        return with(this.date.withDayOfYear(dayOfYear), this.time);
    }

    public LocalDateTime withHour(int hour) {
        return with(this.date, this.time.withHour(hour));
    }

    public LocalDateTime withMinute(int minute) {
        return with(this.date, this.time.withMinute(minute));
    }

    public LocalDateTime withSecond(int second) {
        return with(this.date, this.time.withSecond(second));
    }

    public LocalDateTime withNano(int nanoOfSecond) {
        return with(this.date, this.time.withNano(nanoOfSecond));
    }

    public LocalDateTime truncatedTo(TemporalUnit unit) {
        return with(this.date, this.time.truncatedTo(unit));
    }

    /* Return type fixed from 'java.time.LocalDateTime' to match base method */
    @Override // java.time.chrono.ChronoLocalDateTime, java.time.chrono.ChronoLocalDateTime, java.time.temporal.Temporal
    public ChronoLocalDateTime<LocalDate> plus(TemporalAmount amountToAdd) {
        if (amountToAdd instanceof Period) {
            return with(this.date.plus((TemporalAmount) ((Period) amountToAdd)), this.time);
        }
        Objects.requireNonNull(amountToAdd, "amountToAdd");
        return (LocalDateTime) amountToAdd.addTo(this);
    }

    /* Return type fixed from 'java.time.LocalDateTime' to match base method */
    @Override // java.time.chrono.ChronoLocalDateTime, java.time.chrono.ChronoLocalDateTime, java.time.temporal.Temporal
    public ChronoLocalDateTime<LocalDate> plus(long amountToAdd, TemporalUnit unit) {
        if (!(unit instanceof ChronoUnit)) {
            return (LocalDateTime) unit.addTo(this, amountToAdd);
        }
        switch ((ChronoUnit) unit) {
            case NANOS:
                return plusNanos(amountToAdd);
            case MICROS:
                return plusDays(amountToAdd / 86400000000L).plusNanos((amountToAdd % 86400000000L) * 1000);
            case MILLIS:
                return plusDays(amountToAdd / 86400000).plusNanos((amountToAdd % 86400000) * 1000000);
            case SECONDS:
                return plusSeconds(amountToAdd);
            case MINUTES:
                return plusMinutes(amountToAdd);
            case HOURS:
                return plusHours(amountToAdd);
            case HALF_DAYS:
                return plusDays(amountToAdd / 256).plusHours((amountToAdd % 256) * 12);
            default:
                return with(this.date.plus(amountToAdd, unit), this.time);
        }
    }

    public LocalDateTime plusYears(long years) {
        return with(this.date.plusYears(years), this.time);
    }

    public LocalDateTime plusMonths(long months) {
        return with(this.date.plusMonths(months), this.time);
    }

    public LocalDateTime plusWeeks(long weeks) {
        return with(this.date.plusWeeks(weeks), this.time);
    }

    public LocalDateTime plusDays(long days) {
        return with(this.date.plusDays(days), this.time);
    }

    public LocalDateTime plusHours(long hours) {
        return plusWithOverflow(this.date, hours, 0, 0, 0, 1);
    }

    public LocalDateTime plusMinutes(long minutes) {
        return plusWithOverflow(this.date, 0, minutes, 0, 0, 1);
    }

    public LocalDateTime plusSeconds(long seconds) {
        return plusWithOverflow(this.date, 0, 0, seconds, 0, 1);
    }

    public LocalDateTime plusNanos(long nanos) {
        return plusWithOverflow(this.date, 0, 0, 0, nanos, 1);
    }

    /* Return type fixed from 'java.time.LocalDateTime' to match base method */
    @Override // java.time.chrono.ChronoLocalDateTime, java.time.chrono.ChronoLocalDateTime, java.time.temporal.Temporal
    public ChronoLocalDateTime<LocalDate> minus(TemporalAmount amountToSubtract) {
        if (amountToSubtract instanceof Period) {
            return with(this.date.minus((TemporalAmount) ((Period) amountToSubtract)), this.time);
        }
        Objects.requireNonNull(amountToSubtract, "amountToSubtract");
        return (LocalDateTime) amountToSubtract.subtractFrom(this);
    }

    /* Return type fixed from 'java.time.LocalDateTime' to match base method */
    /* JADX WARN: Type inference failed for: r0v3, types: [java.time.LocalDateTime] */
    /* JADX WARN: Type inference failed for: r0v5, types: [java.time.LocalDateTime] */
    /* JADX WARN: Type inference failed for: r0v6, types: [java.time.LocalDateTime] */
    /* JADX WARNING: Unknown variable types count: 3 */
    @Override // java.time.chrono.ChronoLocalDateTime, java.time.chrono.ChronoLocalDateTime, java.time.temporal.Temporal
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.time.chrono.ChronoLocalDateTime<java.time.LocalDate> minus(long r4, java.time.temporal.TemporalUnit r6) {
        /*
            r3 = this;
            r0 = -9223372036854775808
            int r0 = (r4 > r0 ? 1 : (r4 == r0 ? 0 : -1))
            if (r0 != 0) goto L_0x0016
            r0 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
            java.time.LocalDateTime r0 = r3.plus(r0, r6)
            r1 = 1
            java.time.LocalDateTime r0 = r0.plus(r1, r6)
            goto L_0x001b
        L_0x0016:
            long r0 = -r4
            java.time.LocalDateTime r0 = r3.plus(r0, r6)
        L_0x001b:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: java.time.LocalDateTime.minus(long, java.time.temporal.TemporalUnit):java.time.LocalDateTime");
    }

    public LocalDateTime minusYears(long years) {
        return years == Long.MIN_VALUE ? plusYears(Long.MAX_VALUE).plusYears(1) : plusYears(-years);
    }

    public LocalDateTime minusMonths(long months) {
        return months == Long.MIN_VALUE ? plusMonths(Long.MAX_VALUE).plusMonths(1) : plusMonths(-months);
    }

    public LocalDateTime minusWeeks(long weeks) {
        return weeks == Long.MIN_VALUE ? plusWeeks(Long.MAX_VALUE).plusWeeks(1) : plusWeeks(-weeks);
    }

    public LocalDateTime minusDays(long days) {
        return days == Long.MIN_VALUE ? plusDays(Long.MAX_VALUE).plusDays(1) : plusDays(-days);
    }

    public LocalDateTime minusHours(long hours) {
        return plusWithOverflow(this.date, hours, 0, 0, 0, -1);
    }

    public LocalDateTime minusMinutes(long minutes) {
        return plusWithOverflow(this.date, 0, minutes, 0, 0, -1);
    }

    public LocalDateTime minusSeconds(long seconds) {
        return plusWithOverflow(this.date, 0, 0, seconds, 0, -1);
    }

    public LocalDateTime minusNanos(long nanos) {
        return plusWithOverflow(this.date, 0, 0, 0, nanos, -1);
    }

    private LocalDateTime plusWithOverflow(LocalDate newDate, long hours, long minutes, long seconds, long nanos, int sign) {
        if ((hours | minutes | seconds | nanos) == 0) {
            return with(newDate, this.time);
        }
        long curNoD = this.time.toNanoOfDay();
        long totNanos = (((long) sign) * ((nanos % 86400000000000L) + ((seconds % 86400) * 1000000000) + ((minutes % 1440) * 60000000000L) + ((hours % 24) * 3600000000000L))) + curNoD;
        long totDays = (((nanos / 86400000000000L) + (seconds / 86400) + (minutes / 1440) + (hours / 24)) * ((long) sign)) + Math.floorDiv(totNanos, 86400000000000L);
        long newNoD = Math.floorMod(totNanos, 86400000000000L);
        return with(newDate.plusDays(totDays), newNoD == curNoD ? this.time : LocalTime.ofNanoOfDay(newNoD));
    }

    @Override // java.time.chrono.ChronoLocalDateTime, java.time.temporal.TemporalAccessor
    public <R> R query(TemporalQuery<R> query) {
        return query == TemporalQueries.localDate() ? (R) this.date : (R) super.query(query);
    }

    @Override // java.time.chrono.ChronoLocalDateTime, java.time.temporal.TemporalAdjuster
    public Temporal adjustInto(Temporal temporal) {
        return super.adjustInto(temporal);
    }

    @Override // java.time.temporal.Temporal
    public long until(Temporal endExclusive, TemporalUnit unit) {
        long timePart;
        long amount;
        LocalDateTime end = from((TemporalAccessor) endExclusive);
        if (!(unit instanceof ChronoUnit)) {
            return unit.between(this, end);
        }
        if (unit.isTimeBased()) {
            long amount2 = this.date.daysUntil(end.date);
            if (amount2 == 0) {
                return this.time.until(end.time, unit);
            }
            long timePart2 = end.time.toNanoOfDay() - this.time.toNanoOfDay();
            if (amount2 > 0) {
                amount = amount2 - 1;
                timePart = timePart2 + 86400000000000L;
            } else {
                amount = amount2 + 1;
                timePart = timePart2 - 86400000000000L;
            }
            switch ((ChronoUnit) unit) {
                case NANOS:
                    amount = Math.multiplyExact(amount, 86400000000000L);
                    break;
                case MICROS:
                    amount = Math.multiplyExact(amount, 86400000000L);
                    timePart /= 1000;
                    break;
                case MILLIS:
                    amount = Math.multiplyExact(amount, 86400000);
                    timePart /= 1000000;
                    break;
                case SECONDS:
                    amount = Math.multiplyExact(amount, 86400);
                    timePart /= 1000000000;
                    break;
                case MINUTES:
                    amount = Math.multiplyExact(amount, 1440);
                    timePart /= 60000000000L;
                    break;
                case HOURS:
                    amount = Math.multiplyExact(amount, 24);
                    timePart /= 3600000000000L;
                    break;
                case HALF_DAYS:
                    amount = Math.multiplyExact(amount, 2);
                    timePart /= 43200000000000L;
                    break;
            }
            return Math.addExact(amount, timePart);
        }
        LocalDate endDate = end.date;
        if (endDate.isAfter(this.date) && end.time.isBefore(this.time)) {
            endDate = endDate.minusDays(1);
        } else if (endDate.isBefore(this.date) && end.time.isAfter(this.time)) {
            endDate = endDate.plusDays(1);
        }
        return this.date.until(endDate, unit);
    }

    @Override // java.time.chrono.ChronoLocalDateTime
    public String format(DateTimeFormatter formatter) {
        Objects.requireNonNull(formatter, "formatter");
        return formatter.format(this);
    }

    public OffsetDateTime atOffset(ZoneOffset offset) {
        return OffsetDateTime.of(this, offset);
    }

    /* Return type fixed from 'java.time.ZonedDateTime' to match base method */
    @Override // java.time.chrono.ChronoLocalDateTime
    public ChronoZonedDateTime<LocalDate> atZone(ZoneId zone) {
        return ZonedDateTime.of(this, zone);
    }

    @Override // java.time.chrono.ChronoLocalDateTime
    public int compareTo(ChronoLocalDateTime<?> other) {
        if (other instanceof LocalDateTime) {
            return compareTo0((LocalDateTime) other);
        }
        return super.compareTo(other);
    }

    private int compareTo0(LocalDateTime other) {
        int cmp = this.date.compareTo0(other.toLocalDate());
        if (cmp == 0) {
            return this.time.compareTo(other.toLocalTime());
        }
        return cmp;
    }

    @Override // java.time.chrono.ChronoLocalDateTime
    public boolean isAfter(ChronoLocalDateTime<?> other) {
        if (other instanceof LocalDateTime) {
            return compareTo0((LocalDateTime) other) > 0;
        }
        return super.isAfter(other);
    }

    @Override // java.time.chrono.ChronoLocalDateTime
    public boolean isBefore(ChronoLocalDateTime<?> other) {
        if (other instanceof LocalDateTime) {
            return compareTo0((LocalDateTime) other) < 0;
        }
        return super.isBefore(other);
    }

    @Override // java.time.chrono.ChronoLocalDateTime
    public boolean isEqual(ChronoLocalDateTime<?> other) {
        if (other instanceof LocalDateTime) {
            return compareTo0((LocalDateTime) other) == 0;
        }
        return super.isEqual(other);
    }

    @Override // java.time.chrono.ChronoLocalDateTime
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LocalDateTime)) {
            return false;
        }
        LocalDateTime other = (LocalDateTime) obj;
        if (!this.date.equals(other.date) || !this.time.equals(other.time)) {
            return false;
        }
        return true;
    }

    @Override // java.time.chrono.ChronoLocalDateTime
    public int hashCode() {
        return this.date.hashCode() ^ this.time.hashCode();
    }

    @Override // java.time.chrono.ChronoLocalDateTime
    public String toString() {
        return this.date.toString() + 'T' + this.time.toString();
    }

    private Object writeReplace() {
        return new Ser((byte) 5, this);
    }

    private void readObject(ObjectInputStream s) throws InvalidObjectException {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    /* access modifiers changed from: package-private */
    public void writeExternal(DataOutput out) throws IOException {
        this.date.writeExternal(out);
        this.time.writeExternal(out);
    }

    static LocalDateTime readExternal(DataInput in) throws IOException {
        return of(LocalDate.readExternal(in), LocalTime.readExternal(in));
    }
}
