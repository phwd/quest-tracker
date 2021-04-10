package java.time.chrono;

import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.time.LocalTime;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalField;
import java.time.temporal.TemporalUnit;
import java.time.temporal.ValueRange;
import java.util.Objects;

/* access modifiers changed from: package-private */
public final class ChronoLocalDateTimeImpl implements ChronoLocalDateTime, Temporal, TemporalAdjuster, Serializable {
    private static final long serialVersionUID = 4556003607393004514L;
    private final transient ChronoLocalDate date;
    private final transient LocalTime time;

    static ChronoLocalDateTimeImpl of(ChronoLocalDate chronoLocalDate, LocalTime localTime) {
        return new ChronoLocalDateTimeImpl(chronoLocalDate, localTime);
    }

    static ChronoLocalDateTimeImpl ensureValid(Chronology chronology, Temporal temporal) {
        ChronoLocalDateTimeImpl chronoLocalDateTimeImpl = (ChronoLocalDateTimeImpl) temporal;
        if (chronology.equals(chronoLocalDateTimeImpl.getChronology())) {
            return chronoLocalDateTimeImpl;
        }
        throw new ClassCastException("Chronology mismatch, required: " + chronology.getId() + ", actual: " + chronoLocalDateTimeImpl.getChronology().getId());
    }

    private ChronoLocalDateTimeImpl(ChronoLocalDate chronoLocalDate, LocalTime localTime) {
        Objects.requireNonNull(chronoLocalDate, "date");
        Objects.requireNonNull(localTime, "time");
        this.date = chronoLocalDate;
        this.time = localTime;
    }

    private ChronoLocalDateTimeImpl with(Temporal temporal, LocalTime localTime) {
        if (this.date == temporal && this.time == localTime) {
            return this;
        }
        return new ChronoLocalDateTimeImpl(ChronoLocalDateImpl.ensureValid(this.date.getChronology(), temporal), localTime);
    }

    @Override // java.time.chrono.ChronoLocalDateTime
    public ChronoLocalDate toLocalDate() {
        return this.date;
    }

    @Override // java.time.chrono.ChronoLocalDateTime
    public LocalTime toLocalTime() {
        return this.time;
    }

    @Override // java.time.temporal.TemporalAccessor
    public boolean isSupported(TemporalField temporalField) {
        if (!(temporalField instanceof ChronoField)) {
            return temporalField != null && temporalField.isSupportedBy(this);
        }
        ChronoField chronoField = (ChronoField) temporalField;
        return chronoField.isDateBased() || chronoField.isTimeBased();
    }

    @Override // java.time.temporal.TemporalAccessor
    public ValueRange range(TemporalField temporalField) {
        if (temporalField instanceof ChronoField) {
            return ((ChronoField) temporalField).isTimeBased() ? this.time.range(temporalField) : this.date.range(temporalField);
        }
        return temporalField.rangeRefinedBy(this);
    }

    @Override // java.time.temporal.TemporalAccessor
    public int get(TemporalField temporalField) {
        if (temporalField instanceof ChronoField) {
            return ((ChronoField) temporalField).isTimeBased() ? this.time.get(temporalField) : this.date.get(temporalField);
        }
        return range(temporalField).checkValidIntValue(getLong(temporalField), temporalField);
    }

    @Override // java.time.temporal.TemporalAccessor
    public long getLong(TemporalField temporalField) {
        if (temporalField instanceof ChronoField) {
            return ((ChronoField) temporalField).isTimeBased() ? this.time.getLong(temporalField) : this.date.getLong(temporalField);
        }
        return temporalField.getFrom(this);
    }

    @Override // java.time.temporal.Temporal
    public ChronoLocalDateTimeImpl with(TemporalAdjuster temporalAdjuster) {
        if (temporalAdjuster instanceof ChronoLocalDate) {
            return with((ChronoLocalDate) temporalAdjuster, this.time);
        }
        if (temporalAdjuster instanceof LocalTime) {
            return with(this.date, (LocalTime) temporalAdjuster);
        }
        if (temporalAdjuster instanceof ChronoLocalDateTimeImpl) {
            return ensureValid(this.date.getChronology(), (ChronoLocalDateTimeImpl) temporalAdjuster);
        }
        return ensureValid(this.date.getChronology(), (ChronoLocalDateTimeImpl) temporalAdjuster.adjustInto(this));
    }

    @Override // java.time.temporal.Temporal
    public ChronoLocalDateTimeImpl with(TemporalField temporalField, long j) {
        if (!(temporalField instanceof ChronoField)) {
            return ensureValid(this.date.getChronology(), temporalField.adjustInto(this, j));
        }
        if (((ChronoField) temporalField).isTimeBased()) {
            return with(this.date, this.time.with(temporalField, j));
        }
        return with(this.date.with(temporalField, j), this.time);
    }

    @Override // java.time.temporal.Temporal
    public ChronoLocalDateTimeImpl plus(long j, TemporalUnit temporalUnit) {
        if (!(temporalUnit instanceof ChronoUnit)) {
            return ensureValid(this.date.getChronology(), temporalUnit.addTo(this, j));
        }
        switch (AnonymousClass1.$SwitchMap$java$time$temporal$ChronoUnit[((ChronoUnit) temporalUnit).ordinal()]) {
            case 1:
                return plusNanos(j);
            case 2:
                return plusDays(j / 86400000000L).plusNanos((j % 86400000000L) * 1000);
            case 3:
                return plusDays(j / 86400000).plusNanos((j % 86400000) * 1000000);
            case 4:
                return plusSeconds(j);
            case 5:
                return plusMinutes(j);
            case 6:
                return plusHours(j);
            case 7:
                return plusDays(j / 256).plusHours((j % 256) * 12);
            default:
                return with(this.date.plus(j, temporalUnit), this.time);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: java.time.chrono.ChronoLocalDateTimeImpl$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$java$time$temporal$ChronoUnit = new int[ChronoUnit.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(14:0|1|2|3|4|5|6|7|8|9|10|11|12|(3:13|14|16)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(16:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|16) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0040 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x004b */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x002a */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0035 */
        static {
            /*
                java.time.temporal.ChronoUnit[] r0 = java.time.temporal.ChronoUnit.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                java.time.chrono.ChronoLocalDateTimeImpl.AnonymousClass1.$SwitchMap$java$time$temporal$ChronoUnit = r0
                int[] r0 = java.time.chrono.ChronoLocalDateTimeImpl.AnonymousClass1.$SwitchMap$java$time$temporal$ChronoUnit     // Catch:{ NoSuchFieldError -> 0x0014 }
                java.time.temporal.ChronoUnit r1 = java.time.temporal.ChronoUnit.NANOS     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = java.time.chrono.ChronoLocalDateTimeImpl.AnonymousClass1.$SwitchMap$java$time$temporal$ChronoUnit     // Catch:{ NoSuchFieldError -> 0x001f }
                java.time.temporal.ChronoUnit r1 = java.time.temporal.ChronoUnit.MICROS     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                int[] r0 = java.time.chrono.ChronoLocalDateTimeImpl.AnonymousClass1.$SwitchMap$java$time$temporal$ChronoUnit     // Catch:{ NoSuchFieldError -> 0x002a }
                java.time.temporal.ChronoUnit r1 = java.time.temporal.ChronoUnit.MILLIS     // Catch:{ NoSuchFieldError -> 0x002a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                int[] r0 = java.time.chrono.ChronoLocalDateTimeImpl.AnonymousClass1.$SwitchMap$java$time$temporal$ChronoUnit     // Catch:{ NoSuchFieldError -> 0x0035 }
                java.time.temporal.ChronoUnit r1 = java.time.temporal.ChronoUnit.SECONDS     // Catch:{ NoSuchFieldError -> 0x0035 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0035 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0035 }
            L_0x0035:
                int[] r0 = java.time.chrono.ChronoLocalDateTimeImpl.AnonymousClass1.$SwitchMap$java$time$temporal$ChronoUnit     // Catch:{ NoSuchFieldError -> 0x0040 }
                java.time.temporal.ChronoUnit r1 = java.time.temporal.ChronoUnit.MINUTES     // Catch:{ NoSuchFieldError -> 0x0040 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0040 }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0040 }
            L_0x0040:
                int[] r0 = java.time.chrono.ChronoLocalDateTimeImpl.AnonymousClass1.$SwitchMap$java$time$temporal$ChronoUnit     // Catch:{ NoSuchFieldError -> 0x004b }
                java.time.temporal.ChronoUnit r1 = java.time.temporal.ChronoUnit.HOURS     // Catch:{ NoSuchFieldError -> 0x004b }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x004b }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x004b }
            L_0x004b:
                int[] r0 = java.time.chrono.ChronoLocalDateTimeImpl.AnonymousClass1.$SwitchMap$java$time$temporal$ChronoUnit     // Catch:{ NoSuchFieldError -> 0x0056 }
                java.time.temporal.ChronoUnit r1 = java.time.temporal.ChronoUnit.HALF_DAYS     // Catch:{ NoSuchFieldError -> 0x0056 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0056 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0056 }
            L_0x0056:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: java.time.chrono.ChronoLocalDateTimeImpl.AnonymousClass1.<clinit>():void");
        }
    }

    private ChronoLocalDateTimeImpl plusDays(long j) {
        return with(this.date.plus(j, (TemporalUnit) ChronoUnit.DAYS), this.time);
    }

    private ChronoLocalDateTimeImpl plusHours(long j) {
        return plusWithOverflow(this.date, j, 0, 0, 0);
    }

    private ChronoLocalDateTimeImpl plusMinutes(long j) {
        return plusWithOverflow(this.date, 0, j, 0, 0);
    }

    /* access modifiers changed from: package-private */
    public ChronoLocalDateTimeImpl plusSeconds(long j) {
        return plusWithOverflow(this.date, 0, 0, j, 0);
    }

    private ChronoLocalDateTimeImpl plusNanos(long j) {
        return plusWithOverflow(this.date, 0, 0, 0, j);
    }

    private ChronoLocalDateTimeImpl plusWithOverflow(ChronoLocalDate chronoLocalDate, long j, long j2, long j3, long j4) {
        if ((j | j2 | j3 | j4) == 0) {
            return with(chronoLocalDate, this.time);
        }
        long nanoOfDay = this.time.toNanoOfDay();
        long j5 = (j4 % 86400000000000L) + ((j3 % 86400) * 1000000000) + ((j2 % 1440) * 60000000000L) + ((j % 24) * 3600000000000L) + nanoOfDay;
        long floorDiv = (j4 / 86400000000000L) + (j3 / 86400) + (j2 / 1440) + (j / 24) + Math.floorDiv(j5, 86400000000000L);
        long floorMod = Math.floorMod(j5, 86400000000000L);
        return with(chronoLocalDate.plus(floorDiv, (TemporalUnit) ChronoUnit.DAYS), floorMod == nanoOfDay ? this.time : LocalTime.ofNanoOfDay(floorMod));
    }

    private Object writeReplace() {
        return new Ser((byte) 2, this);
    }

    private void readObject(ObjectInputStream objectInputStream) {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof ChronoLocalDateTime) {
            return compareTo((ChronoLocalDateTime) obj) == 0;
        }
        return false;
    }

    @Override // java.time.chrono.ChronoLocalDateTime
    public int hashCode() {
        return toLocalTime().hashCode() ^ toLocalDate().hashCode();
    }

    @Override // java.time.chrono.ChronoLocalDateTime
    public String toString() {
        return toLocalDate().toString() + 'T' + toLocalTime().toString();
    }
}
