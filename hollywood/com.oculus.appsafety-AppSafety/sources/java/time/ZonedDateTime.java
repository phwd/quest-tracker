package java.time;

import java.io.DataOutput;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.time.chrono.ChronoLocalDateTime;
import java.time.chrono.ChronoZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalField;
import java.time.temporal.TemporalQueries;
import java.time.temporal.TemporalQuery;
import java.time.temporal.TemporalUnit;
import java.time.temporal.UnsupportedTemporalTypeException;
import java.time.temporal.ValueRange;
import java.time.zone.ZoneOffsetTransition;
import java.time.zone.ZoneRules;
import java.util.List;
import java.util.Objects;

public final class ZonedDateTime implements Temporal, ChronoZonedDateTime<LocalDate>, Serializable {
    private static final long serialVersionUID = -6260982410461394882L;
    private final LocalDateTime dateTime;
    private final ZoneOffset offset;
    private final ZoneId zone;

    public static ZonedDateTime now() {
        return now(Clock.systemDefaultZone());
    }

    public static ZonedDateTime now(ZoneId zone2) {
        return now(Clock.system(zone2));
    }

    public static ZonedDateTime now(Clock clock) {
        Objects.requireNonNull(clock, "clock");
        return ofInstant(clock.instant(), clock.getZone());
    }

    public static ZonedDateTime of(LocalDate date, LocalTime time, ZoneId zone2) {
        return of(LocalDateTime.of(date, time), zone2);
    }

    public static ZonedDateTime of(LocalDateTime localDateTime, ZoneId zone2) {
        return ofLocal(localDateTime, zone2, null);
    }

    public static ZonedDateTime of(int year, int month, int dayOfMonth, int hour, int minute, int second, int nanoOfSecond, ZoneId zone2) {
        return ofLocal(LocalDateTime.of(year, month, dayOfMonth, hour, minute, second, nanoOfSecond), zone2, null);
    }

    public static ZonedDateTime ofLocal(LocalDateTime localDateTime, ZoneId zone2, ZoneOffset preferredOffset) {
        ZoneOffset offset2;
        Objects.requireNonNull(localDateTime, "localDateTime");
        Objects.requireNonNull(zone2, "zone");
        if (zone2 instanceof ZoneOffset) {
            return new ZonedDateTime(localDateTime, (ZoneOffset) zone2, zone2);
        }
        ZoneRules rules = zone2.getRules();
        List<ZoneOffset> validOffsets = rules.getValidOffsets(localDateTime);
        if (validOffsets.size() == 1) {
            offset2 = validOffsets.get(0);
        } else if (validOffsets.size() == 0) {
            ZoneOffsetTransition trans = rules.getTransition(localDateTime);
            localDateTime = localDateTime.plusSeconds(trans.getDuration().getSeconds());
            offset2 = trans.getOffsetAfter();
        } else if (preferredOffset == null || !validOffsets.contains(preferredOffset)) {
            offset2 = (ZoneOffset) Objects.requireNonNull(validOffsets.get(0), "offset");
        } else {
            offset2 = preferredOffset;
        }
        return new ZonedDateTime(localDateTime, offset2, zone2);
    }

    public static ZonedDateTime ofInstant(Instant instant, ZoneId zone2) {
        Objects.requireNonNull(instant, "instant");
        Objects.requireNonNull(zone2, "zone");
        return create(instant.getEpochSecond(), instant.getNano(), zone2);
    }

    public static ZonedDateTime ofInstant(LocalDateTime localDateTime, ZoneOffset offset2, ZoneId zone2) {
        Objects.requireNonNull(localDateTime, "localDateTime");
        Objects.requireNonNull(offset2, "offset");
        Objects.requireNonNull(zone2, "zone");
        if (zone2.getRules().isValidOffset(localDateTime, offset2)) {
            return new ZonedDateTime(localDateTime, offset2, zone2);
        }
        return create(localDateTime.toEpochSecond(offset2), localDateTime.getNano(), zone2);
    }

    private static ZonedDateTime create(long epochSecond, int nanoOfSecond, ZoneId zone2) {
        ZoneOffset offset2 = zone2.getRules().getOffset(Instant.ofEpochSecond(epochSecond, (long) nanoOfSecond));
        return new ZonedDateTime(LocalDateTime.ofEpochSecond(epochSecond, nanoOfSecond, offset2), offset2, zone2);
    }

    public static ZonedDateTime ofStrict(LocalDateTime localDateTime, ZoneOffset offset2, ZoneId zone2) {
        Objects.requireNonNull(localDateTime, "localDateTime");
        Objects.requireNonNull(offset2, "offset");
        Objects.requireNonNull(zone2, "zone");
        ZoneRules rules = zone2.getRules();
        if (rules.isValidOffset(localDateTime, offset2)) {
            return new ZonedDateTime(localDateTime, offset2, zone2);
        }
        ZoneOffsetTransition trans = rules.getTransition(localDateTime);
        if (trans == null || !trans.isGap()) {
            throw new DateTimeException("ZoneOffset '" + ((Object) offset2) + "' is not valid for LocalDateTime '" + ((Object) localDateTime) + "' in zone '" + ((Object) zone2) + "'");
        }
        throw new DateTimeException("LocalDateTime '" + ((Object) localDateTime) + "' does not exist in zone '" + ((Object) zone2) + "' due to a gap in the local time-line, typically caused by daylight savings");
    }

    private static ZonedDateTime ofLenient(LocalDateTime localDateTime, ZoneOffset offset2, ZoneId zone2) {
        Objects.requireNonNull(localDateTime, "localDateTime");
        Objects.requireNonNull(offset2, "offset");
        Objects.requireNonNull(zone2, "zone");
        if (!(zone2 instanceof ZoneOffset) || offset2.equals(zone2)) {
            return new ZonedDateTime(localDateTime, offset2, zone2);
        }
        throw new IllegalArgumentException("ZoneId must match ZoneOffset");
    }

    public static ZonedDateTime from(TemporalAccessor temporal) {
        if (temporal instanceof ZonedDateTime) {
            return (ZonedDateTime) temporal;
        }
        try {
            ZoneId zone2 = ZoneId.from(temporal);
            if (temporal.isSupported(ChronoField.INSTANT_SECONDS)) {
                return create(temporal.getLong(ChronoField.INSTANT_SECONDS), temporal.get(ChronoField.NANO_OF_SECOND), zone2);
            }
            return of(LocalDate.from(temporal), LocalTime.from(temporal), zone2);
        } catch (DateTimeException ex) {
            throw new DateTimeException("Unable to obtain ZonedDateTime from TemporalAccessor: " + ((Object) temporal) + " of type " + temporal.getClass().getName(), ex);
        }
    }

    public static ZonedDateTime parse(CharSequence text) {
        return parse(text, DateTimeFormatter.ISO_ZONED_DATE_TIME);
    }

    public static ZonedDateTime parse(CharSequence text, DateTimeFormatter formatter) {
        Objects.requireNonNull(formatter, "formatter");
        return (ZonedDateTime) formatter.parse(text, $$Lambda$up1HpCqucM_DXyYrpDOyCcdmIA.INSTANCE);
    }

    private ZonedDateTime(LocalDateTime dateTime2, ZoneOffset offset2, ZoneId zone2) {
        this.dateTime = dateTime2;
        this.offset = offset2;
        this.zone = zone2;
    }

    private ZonedDateTime resolveLocal(LocalDateTime newDateTime) {
        return ofLocal(newDateTime, this.zone, this.offset);
    }

    private ZonedDateTime resolveInstant(LocalDateTime newDateTime) {
        return ofInstant(newDateTime, this.offset, this.zone);
    }

    private ZonedDateTime resolveOffset(ZoneOffset offset2) {
        if (offset2.equals(this.offset) || !this.zone.getRules().isValidOffset(this.dateTime, offset2)) {
            return this;
        }
        return new ZonedDateTime(this.dateTime, offset2, this.zone);
    }

    @Override // java.time.chrono.ChronoZonedDateTime, java.time.temporal.TemporalAccessor
    public boolean isSupported(TemporalField field) {
        return (field instanceof ChronoField) || (field != null && field.isSupportedBy(this));
    }

    @Override // java.time.chrono.ChronoZonedDateTime, java.time.temporal.Temporal
    public boolean isSupported(TemporalUnit unit) {
        return super.isSupported(unit);
    }

    @Override // java.time.chrono.ChronoZonedDateTime, java.time.temporal.TemporalAccessor
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
    /* renamed from: java.time.ZonedDateTime$1  reason: invalid class name */
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

    @Override // java.time.chrono.ChronoZonedDateTime, java.time.temporal.TemporalAccessor
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

    @Override // java.time.chrono.ChronoZonedDateTime, java.time.temporal.TemporalAccessor
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

    @Override // java.time.chrono.ChronoZonedDateTime
    public ZoneOffset getOffset() {
        return this.offset;
    }

    /* Return type fixed from 'java.time.ZonedDateTime' to match base method */
    @Override // java.time.chrono.ChronoZonedDateTime
    public ChronoZonedDateTime<LocalDate> withEarlierOffsetAtOverlap() {
        ZoneOffsetTransition trans = getZone().getRules().getTransition(this.dateTime);
        if (trans != null && trans.isOverlap()) {
            ZoneOffset earlierOffset = trans.getOffsetBefore();
            if (!earlierOffset.equals(this.offset)) {
                return new ZonedDateTime(this.dateTime, earlierOffset, this.zone);
            }
        }
        return this;
    }

    /* Return type fixed from 'java.time.ZonedDateTime' to match base method */
    /* JADX WARN: Type inference failed for: r1v0, types: [java.time.LocalDateTime] */
    /* JADX WARNING: Unknown variable types count: 1 */
    @Override // java.time.chrono.ChronoZonedDateTime
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.time.chrono.ChronoZonedDateTime<java.time.LocalDate> withLaterOffsetAtOverlap() {
        /*
            r5 = this;
            java.time.ZoneId r0 = r5.getZone()
            java.time.zone.ZoneRules r0 = r0.getRules()
            java.time.LocalDateTime r1 = r5.toLocalDateTime()
            java.time.zone.ZoneOffsetTransition r0 = r0.getTransition(r1)
            if (r0 == 0) goto L_0x0028
            java.time.ZoneOffset r1 = r0.getOffsetAfter()
            java.time.ZoneOffset r2 = r5.offset
            boolean r2 = r1.equals(r2)
            if (r2 != 0) goto L_0x0028
            java.time.ZonedDateTime r2 = new java.time.ZonedDateTime
            java.time.LocalDateTime r3 = r5.dateTime
            java.time.ZoneId r4 = r5.zone
            r2.<init>(r3, r1, r4)
            return r2
        L_0x0028:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: java.time.ZonedDateTime.withLaterOffsetAtOverlap():java.time.ZonedDateTime");
    }

    @Override // java.time.chrono.ChronoZonedDateTime
    public ZoneId getZone() {
        return this.zone;
    }

    /* Return type fixed from 'java.time.ZonedDateTime' to match base method */
    @Override // java.time.chrono.ChronoZonedDateTime
    public ChronoZonedDateTime<LocalDate> withZoneSameLocal(ZoneId zone2) {
        Objects.requireNonNull(zone2, "zone");
        return this.zone.equals(zone2) ? this : ofLocal(this.dateTime, zone2, this.offset);
    }

    /* Return type fixed from 'java.time.ZonedDateTime' to match base method */
    @Override // java.time.chrono.ChronoZonedDateTime
    public ChronoZonedDateTime<LocalDate> withZoneSameInstant(ZoneId zone2) {
        Objects.requireNonNull(zone2, "zone");
        if (this.zone.equals(zone2)) {
            return this;
        }
        return create(this.dateTime.toEpochSecond(this.offset), this.dateTime.getNano(), zone2);
    }

    public ZonedDateTime withFixedOffsetZone() {
        if (this.zone.equals(this.offset)) {
            return this;
        }
        LocalDateTime localDateTime = this.dateTime;
        ZoneOffset zoneOffset = this.offset;
        return new ZonedDateTime(localDateTime, zoneOffset, zoneOffset);
    }

    /* Return type fixed from 'java.time.LocalDateTime' to match base method */
    @Override // java.time.chrono.ChronoZonedDateTime
    public ChronoLocalDateTime<LocalDate> toLocalDateTime() {
        return this.dateTime;
    }

    @Override // java.time.chrono.ChronoZonedDateTime
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

    @Override // java.time.chrono.ChronoZonedDateTime
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

    /* Return type fixed from 'java.time.ZonedDateTime' to match base method */
    @Override // java.time.chrono.ChronoZonedDateTime, java.time.chrono.ChronoZonedDateTime, java.time.temporal.Temporal
    public ChronoZonedDateTime<LocalDate> with(TemporalAdjuster adjuster) {
        if (adjuster instanceof LocalDate) {
            return resolveLocal(LocalDateTime.of((LocalDate) adjuster, this.dateTime.toLocalTime()));
        }
        if (adjuster instanceof LocalTime) {
            return resolveLocal(LocalDateTime.of(this.dateTime.toLocalDate(), (LocalTime) adjuster));
        }
        if (adjuster instanceof LocalDateTime) {
            return resolveLocal((LocalDateTime) adjuster);
        }
        if (adjuster instanceof OffsetDateTime) {
            OffsetDateTime odt = (OffsetDateTime) adjuster;
            return ofLocal(odt.toLocalDateTime(), this.zone, odt.getOffset());
        } else if (adjuster instanceof Instant) {
            Instant instant = (Instant) adjuster;
            return create(instant.getEpochSecond(), instant.getNano(), this.zone);
        } else if (adjuster instanceof ZoneOffset) {
            return resolveOffset((ZoneOffset) adjuster);
        } else {
            return (ZonedDateTime) adjuster.adjustInto(this);
        }
    }

    /* Return type fixed from 'java.time.ZonedDateTime' to match base method */
    /* JADX DEBUG: Multi-variable search result rejected for r3v0, resolved type: java.time.ZonedDateTime */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v7, types: [java.time.LocalDateTime] */
    /* JADX WARNING: Unknown variable types count: 1 */
    @Override // java.time.chrono.ChronoZonedDateTime, java.time.chrono.ChronoZonedDateTime, java.time.temporal.Temporal
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.time.chrono.ChronoZonedDateTime<java.time.LocalDate> with(java.time.temporal.TemporalField r4, long r5) {
        /*
            r3 = this;
            boolean r0 = r4 instanceof java.time.temporal.ChronoField
            if (r0 == 0) goto L_0x0038
            r0 = r4
            java.time.temporal.ChronoField r0 = (java.time.temporal.ChronoField) r0
            int[] r1 = java.time.ZonedDateTime.AnonymousClass1.$SwitchMap$java$time$temporal$ChronoField
            int r2 = r0.ordinal()
            r1 = r1[r2]
            r2 = 1
            if (r1 == r2) goto L_0x002d
            r2 = 2
            if (r1 == r2) goto L_0x0020
            java.time.LocalDateTime r1 = r3.dateTime
            java.time.LocalDateTime r1 = r1.with(r4, r5)
            java.time.ZonedDateTime r1 = r3.resolveLocal(r1)
            return r1
        L_0x0020:
            int r1 = r0.checkValidIntValue(r5)
            java.time.ZoneOffset r1 = java.time.ZoneOffset.ofTotalSeconds(r1)
            java.time.ZonedDateTime r2 = r3.resolveOffset(r1)
            return r2
        L_0x002d:
            int r1 = r3.getNano()
            java.time.ZoneId r2 = r3.zone
            java.time.ZonedDateTime r1 = create(r5, r1, r2)
            return r1
        L_0x0038:
            java.time.temporal.Temporal r0 = r4.adjustInto(r3, r5)
            java.time.ZonedDateTime r0 = (java.time.ZonedDateTime) r0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: java.time.ZonedDateTime.with(java.time.temporal.TemporalField, long):java.time.ZonedDateTime");
    }

    public ZonedDateTime withYear(int year) {
        return resolveLocal(this.dateTime.withYear(year));
    }

    public ZonedDateTime withMonth(int month) {
        return resolveLocal(this.dateTime.withMonth(month));
    }

    public ZonedDateTime withDayOfMonth(int dayOfMonth) {
        return resolveLocal(this.dateTime.withDayOfMonth(dayOfMonth));
    }

    public ZonedDateTime withDayOfYear(int dayOfYear) {
        return resolveLocal(this.dateTime.withDayOfYear(dayOfYear));
    }

    public ZonedDateTime withHour(int hour) {
        return resolveLocal(this.dateTime.withHour(hour));
    }

    public ZonedDateTime withMinute(int minute) {
        return resolveLocal(this.dateTime.withMinute(minute));
    }

    public ZonedDateTime withSecond(int second) {
        return resolveLocal(this.dateTime.withSecond(second));
    }

    public ZonedDateTime withNano(int nanoOfSecond) {
        return resolveLocal(this.dateTime.withNano(nanoOfSecond));
    }

    public ZonedDateTime truncatedTo(TemporalUnit unit) {
        return resolveLocal(this.dateTime.truncatedTo(unit));
    }

    /* Return type fixed from 'java.time.ZonedDateTime' to match base method */
    /* JADX DEBUG: Multi-variable search result rejected for r2v0, resolved type: java.time.ZonedDateTime */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v1, types: [java.time.LocalDateTime] */
    /* JADX WARNING: Unknown variable types count: 1 */
    @Override // java.time.chrono.ChronoZonedDateTime, java.time.chrono.ChronoZonedDateTime, java.time.temporal.Temporal
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.time.chrono.ChronoZonedDateTime<java.time.LocalDate> plus(java.time.temporal.TemporalAmount r3) {
        /*
            r2 = this;
            boolean r0 = r3 instanceof java.time.Period
            if (r0 == 0) goto L_0x0012
            r0 = r3
            java.time.Period r0 = (java.time.Period) r0
            java.time.LocalDateTime r1 = r2.dateTime
            java.time.LocalDateTime r1 = r1.plus(r0)
            java.time.ZonedDateTime r1 = r2.resolveLocal(r1)
            return r1
        L_0x0012:
            java.lang.String r0 = "amountToAdd"
            java.util.Objects.requireNonNull(r3, r0)
            java.time.temporal.Temporal r0 = r3.addTo(r2)
            java.time.ZonedDateTime r0 = (java.time.ZonedDateTime) r0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: java.time.ZonedDateTime.plus(java.time.temporal.TemporalAmount):java.time.ZonedDateTime");
    }

    /* Return type fixed from 'java.time.ZonedDateTime' to match base method */
    /* JADX DEBUG: Multi-variable search result rejected for r1v0, resolved type: java.time.ZonedDateTime */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v5, types: [java.time.LocalDateTime] */
    /* JADX WARN: Type inference failed for: r0v8, types: [java.time.LocalDateTime] */
    /* JADX WARNING: Unknown variable types count: 2 */
    @Override // java.time.chrono.ChronoZonedDateTime, java.time.chrono.ChronoZonedDateTime, java.time.temporal.Temporal
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.time.chrono.ChronoZonedDateTime<java.time.LocalDate> plus(long r2, java.time.temporal.TemporalUnit r4) {
        /*
            r1 = this;
            boolean r0 = r4 instanceof java.time.temporal.ChronoUnit
            if (r0 == 0) goto L_0x0020
            boolean r0 = r4.isDateBased()
            if (r0 == 0) goto L_0x0015
            java.time.LocalDateTime r0 = r1.dateTime
            java.time.LocalDateTime r0 = r0.plus(r2, r4)
            java.time.ZonedDateTime r0 = r1.resolveLocal(r0)
            return r0
        L_0x0015:
            java.time.LocalDateTime r0 = r1.dateTime
            java.time.LocalDateTime r0 = r0.plus(r2, r4)
            java.time.ZonedDateTime r0 = r1.resolveInstant(r0)
            return r0
        L_0x0020:
            java.time.temporal.Temporal r0 = r4.addTo(r1, r2)
            java.time.ZonedDateTime r0 = (java.time.ZonedDateTime) r0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: java.time.ZonedDateTime.plus(long, java.time.temporal.TemporalUnit):java.time.ZonedDateTime");
    }

    public ZonedDateTime plusYears(long years) {
        return resolveLocal(this.dateTime.plusYears(years));
    }

    public ZonedDateTime plusMonths(long months) {
        return resolveLocal(this.dateTime.plusMonths(months));
    }

    public ZonedDateTime plusWeeks(long weeks) {
        return resolveLocal(this.dateTime.plusWeeks(weeks));
    }

    public ZonedDateTime plusDays(long days) {
        return resolveLocal(this.dateTime.plusDays(days));
    }

    public ZonedDateTime plusHours(long hours) {
        return resolveInstant(this.dateTime.plusHours(hours));
    }

    public ZonedDateTime plusMinutes(long minutes) {
        return resolveInstant(this.dateTime.plusMinutes(minutes));
    }

    public ZonedDateTime plusSeconds(long seconds) {
        return resolveInstant(this.dateTime.plusSeconds(seconds));
    }

    public ZonedDateTime plusNanos(long nanos) {
        return resolveInstant(this.dateTime.plusNanos(nanos));
    }

    /* Return type fixed from 'java.time.ZonedDateTime' to match base method */
    /* JADX DEBUG: Multi-variable search result rejected for r2v0, resolved type: java.time.ZonedDateTime */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v1, types: [java.time.LocalDateTime] */
    /* JADX WARNING: Unknown variable types count: 1 */
    @Override // java.time.chrono.ChronoZonedDateTime, java.time.chrono.ChronoZonedDateTime, java.time.temporal.Temporal
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.time.chrono.ChronoZonedDateTime<java.time.LocalDate> minus(java.time.temporal.TemporalAmount r3) {
        /*
            r2 = this;
            boolean r0 = r3 instanceof java.time.Period
            if (r0 == 0) goto L_0x0012
            r0 = r3
            java.time.Period r0 = (java.time.Period) r0
            java.time.LocalDateTime r1 = r2.dateTime
            java.time.LocalDateTime r1 = r1.minus(r0)
            java.time.ZonedDateTime r1 = r2.resolveLocal(r1)
            return r1
        L_0x0012:
            java.lang.String r0 = "amountToSubtract"
            java.util.Objects.requireNonNull(r3, r0)
            java.time.temporal.Temporal r0 = r3.subtractFrom(r2)
            java.time.ZonedDateTime r0 = (java.time.ZonedDateTime) r0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: java.time.ZonedDateTime.minus(java.time.temporal.TemporalAmount):java.time.ZonedDateTime");
    }

    /* Return type fixed from 'java.time.ZonedDateTime' to match base method */
    /* JADX WARN: Type inference failed for: r0v3, types: [java.time.ZonedDateTime] */
    /* JADX WARN: Type inference failed for: r0v5, types: [java.time.ZonedDateTime] */
    /* JADX WARN: Type inference failed for: r0v6, types: [java.time.ZonedDateTime] */
    /* JADX WARNING: Unknown variable types count: 3 */
    @Override // java.time.chrono.ChronoZonedDateTime, java.time.chrono.ChronoZonedDateTime, java.time.temporal.Temporal
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.time.chrono.ChronoZonedDateTime<java.time.LocalDate> minus(long r4, java.time.temporal.TemporalUnit r6) {
        /*
            r3 = this;
            r0 = -9223372036854775808
            int r0 = (r4 > r0 ? 1 : (r4 == r0 ? 0 : -1))
            if (r0 != 0) goto L_0x0016
            r0 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
            java.time.ZonedDateTime r0 = r3.plus(r0, r6)
            r1 = 1
            java.time.ZonedDateTime r0 = r0.plus(r1, r6)
            goto L_0x001b
        L_0x0016:
            long r0 = -r4
            java.time.ZonedDateTime r0 = r3.plus(r0, r6)
        L_0x001b:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: java.time.ZonedDateTime.minus(long, java.time.temporal.TemporalUnit):java.time.ZonedDateTime");
    }

    public ZonedDateTime minusYears(long years) {
        return years == Long.MIN_VALUE ? plusYears(Long.MAX_VALUE).plusYears(1) : plusYears(-years);
    }

    public ZonedDateTime minusMonths(long months) {
        return months == Long.MIN_VALUE ? plusMonths(Long.MAX_VALUE).plusMonths(1) : plusMonths(-months);
    }

    public ZonedDateTime minusWeeks(long weeks) {
        return weeks == Long.MIN_VALUE ? plusWeeks(Long.MAX_VALUE).plusWeeks(1) : plusWeeks(-weeks);
    }

    public ZonedDateTime minusDays(long days) {
        return days == Long.MIN_VALUE ? plusDays(Long.MAX_VALUE).plusDays(1) : plusDays(-days);
    }

    public ZonedDateTime minusHours(long hours) {
        return hours == Long.MIN_VALUE ? plusHours(Long.MAX_VALUE).plusHours(1) : plusHours(-hours);
    }

    public ZonedDateTime minusMinutes(long minutes) {
        return minutes == Long.MIN_VALUE ? plusMinutes(Long.MAX_VALUE).plusMinutes(1) : plusMinutes(-minutes);
    }

    public ZonedDateTime minusSeconds(long seconds) {
        return seconds == Long.MIN_VALUE ? plusSeconds(Long.MAX_VALUE).plusSeconds(1) : plusSeconds(-seconds);
    }

    public ZonedDateTime minusNanos(long nanos) {
        return nanos == Long.MIN_VALUE ? plusNanos(Long.MAX_VALUE).plusNanos(1) : plusNanos(-nanos);
    }

    @Override // java.time.chrono.ChronoZonedDateTime, java.time.temporal.TemporalAccessor
    public <R> R query(TemporalQuery<R> query) {
        return query == TemporalQueries.localDate() ? (R) toLocalDate() : (R) super.query(query);
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [java.time.ZonedDateTime] */
    /* JADX WARNING: Unknown variable types count: 1 */
    @Override // java.time.temporal.Temporal
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public long until(java.time.temporal.Temporal r4, java.time.temporal.TemporalUnit r5) {
        /*
            r3 = this;
            java.time.ZonedDateTime r0 = from(r4)
            boolean r1 = r5 instanceof java.time.temporal.ChronoUnit
            if (r1 == 0) goto L_0x002a
            java.time.ZoneId r1 = r3.zone
            java.time.ZonedDateTime r0 = r0.withZoneSameInstant(r1)
            boolean r1 = r5.isDateBased()
            if (r1 == 0) goto L_0x001d
            java.time.LocalDateTime r1 = r3.dateTime
            java.time.LocalDateTime r2 = r0.dateTime
            long r1 = r1.until(r2, r5)
            return r1
        L_0x001d:
            java.time.OffsetDateTime r1 = r3.toOffsetDateTime()
            java.time.OffsetDateTime r2 = r0.toOffsetDateTime()
            long r1 = r1.until(r2, r5)
            return r1
        L_0x002a:
            long r1 = r5.between(r3, r0)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: java.time.ZonedDateTime.until(java.time.temporal.Temporal, java.time.temporal.TemporalUnit):long");
    }

    @Override // java.time.chrono.ChronoZonedDateTime
    public String format(DateTimeFormatter formatter) {
        Objects.requireNonNull(formatter, "formatter");
        return formatter.format(this);
    }

    public OffsetDateTime toOffsetDateTime() {
        return OffsetDateTime.of(this.dateTime, this.offset);
    }

    @Override // java.time.chrono.ChronoZonedDateTime
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ZonedDateTime)) {
            return false;
        }
        ZonedDateTime other = (ZonedDateTime) obj;
        if (!this.dateTime.equals(other.dateTime) || !this.offset.equals(other.offset) || !this.zone.equals(other.zone)) {
            return false;
        }
        return true;
    }

    @Override // java.time.chrono.ChronoZonedDateTime
    public int hashCode() {
        return (this.dateTime.hashCode() ^ this.offset.hashCode()) ^ Integer.rotateLeft(this.zone.hashCode(), 3);
    }

    @Override // java.time.chrono.ChronoZonedDateTime
    public String toString() {
        String str = this.dateTime.toString() + this.offset.toString();
        if (this.offset == this.zone) {
            return str;
        }
        return str + '[' + this.zone.toString() + ']';
    }

    private Object writeReplace() {
        return new Ser((byte) 6, this);
    }

    private void readObject(ObjectInputStream s) throws InvalidObjectException {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    /* access modifiers changed from: package-private */
    public void writeExternal(DataOutput out) throws IOException {
        this.dateTime.writeExternal(out);
        this.offset.writeExternal(out);
        this.zone.write(out);
    }

    static ZonedDateTime readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        return ofLenient(LocalDateTime.readExternal(in), ZoneOffset.readExternal(in), (ZoneId) Ser.read(in));
    }
}
