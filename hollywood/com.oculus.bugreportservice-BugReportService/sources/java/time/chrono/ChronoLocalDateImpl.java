package java.time.chrono;

import java.io.Serializable;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalField;
import java.time.temporal.TemporalUnit;
import java.time.temporal.UnsupportedTemporalTypeException;

/* access modifiers changed from: package-private */
public abstract class ChronoLocalDateImpl implements ChronoLocalDate, Temporal, TemporalAdjuster, Serializable {
    private static final long serialVersionUID = 6282433883239719096L;

    /* access modifiers changed from: package-private */
    public abstract ChronoLocalDate plusDays(long j);

    /* access modifiers changed from: package-private */
    public abstract ChronoLocalDate plusMonths(long j);

    /* access modifiers changed from: package-private */
    public abstract ChronoLocalDate plusYears(long j);

    static ChronoLocalDate ensureValid(Chronology chronology, Temporal temporal) {
        ChronoLocalDate chronoLocalDate = (ChronoLocalDate) temporal;
        if (chronology.equals(chronoLocalDate.getChronology())) {
            return chronoLocalDate;
        }
        throw new ClassCastException("Chronology mismatch, expected: " + chronology.getId() + ", actual: " + chronoLocalDate.getChronology().getId());
    }

    ChronoLocalDateImpl() {
    }

    @Override // java.time.chrono.ChronoLocalDate, java.time.temporal.Temporal
    public ChronoLocalDate with(TemporalAdjuster temporalAdjuster) {
        return super.with(temporalAdjuster);
    }

    @Override // java.time.chrono.ChronoLocalDate, java.time.temporal.Temporal
    public ChronoLocalDate with(TemporalField temporalField, long j) {
        return super.with(temporalField, j);
    }

    @Override // java.time.chrono.ChronoLocalDate, java.time.temporal.Temporal
    public ChronoLocalDate plus(long j, TemporalUnit temporalUnit) {
        if (!(temporalUnit instanceof ChronoUnit)) {
            return super.plus(j, temporalUnit);
        }
        switch (AnonymousClass1.$SwitchMap$java$time$temporal$ChronoUnit[((ChronoUnit) temporalUnit).ordinal()]) {
            case 1:
                return plusDays(j);
            case 2:
                return plusDays(Math.multiplyExact(j, 7));
            case 3:
                return plusMonths(j);
            case 4:
                return plusYears(j);
            case 5:
                return plusYears(Math.multiplyExact(j, 10));
            case 6:
                return plusYears(Math.multiplyExact(j, 100));
            case 7:
                return plusYears(Math.multiplyExact(j, 1000));
            case 8:
                ChronoField chronoField = ChronoField.ERA;
                return with((TemporalField) chronoField, Math.addExact(getLong(chronoField), j));
            default:
                throw new UnsupportedTemporalTypeException("Unsupported unit: " + temporalUnit);
        }
    }

    /* renamed from: java.time.chrono.ChronoLocalDateImpl$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$java$time$temporal$ChronoUnit = new int[ChronoUnit.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(16:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|(3:15|16|18)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(18:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|18) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0040 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x004b */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0056 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x002a */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0035 */
        static {
            /*
                java.time.temporal.ChronoUnit[] r0 = java.time.temporal.ChronoUnit.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                java.time.chrono.ChronoLocalDateImpl.AnonymousClass1.$SwitchMap$java$time$temporal$ChronoUnit = r0
                int[] r0 = java.time.chrono.ChronoLocalDateImpl.AnonymousClass1.$SwitchMap$java$time$temporal$ChronoUnit     // Catch:{ NoSuchFieldError -> 0x0014 }
                java.time.temporal.ChronoUnit r1 = java.time.temporal.ChronoUnit.DAYS     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = java.time.chrono.ChronoLocalDateImpl.AnonymousClass1.$SwitchMap$java$time$temporal$ChronoUnit     // Catch:{ NoSuchFieldError -> 0x001f }
                java.time.temporal.ChronoUnit r1 = java.time.temporal.ChronoUnit.WEEKS     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                int[] r0 = java.time.chrono.ChronoLocalDateImpl.AnonymousClass1.$SwitchMap$java$time$temporal$ChronoUnit     // Catch:{ NoSuchFieldError -> 0x002a }
                java.time.temporal.ChronoUnit r1 = java.time.temporal.ChronoUnit.MONTHS     // Catch:{ NoSuchFieldError -> 0x002a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                int[] r0 = java.time.chrono.ChronoLocalDateImpl.AnonymousClass1.$SwitchMap$java$time$temporal$ChronoUnit     // Catch:{ NoSuchFieldError -> 0x0035 }
                java.time.temporal.ChronoUnit r1 = java.time.temporal.ChronoUnit.YEARS     // Catch:{ NoSuchFieldError -> 0x0035 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0035 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0035 }
            L_0x0035:
                int[] r0 = java.time.chrono.ChronoLocalDateImpl.AnonymousClass1.$SwitchMap$java$time$temporal$ChronoUnit     // Catch:{ NoSuchFieldError -> 0x0040 }
                java.time.temporal.ChronoUnit r1 = java.time.temporal.ChronoUnit.DECADES     // Catch:{ NoSuchFieldError -> 0x0040 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0040 }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0040 }
            L_0x0040:
                int[] r0 = java.time.chrono.ChronoLocalDateImpl.AnonymousClass1.$SwitchMap$java$time$temporal$ChronoUnit     // Catch:{ NoSuchFieldError -> 0x004b }
                java.time.temporal.ChronoUnit r1 = java.time.temporal.ChronoUnit.CENTURIES     // Catch:{ NoSuchFieldError -> 0x004b }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x004b }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x004b }
            L_0x004b:
                int[] r0 = java.time.chrono.ChronoLocalDateImpl.AnonymousClass1.$SwitchMap$java$time$temporal$ChronoUnit     // Catch:{ NoSuchFieldError -> 0x0056 }
                java.time.temporal.ChronoUnit r1 = java.time.temporal.ChronoUnit.MILLENNIA     // Catch:{ NoSuchFieldError -> 0x0056 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0056 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0056 }
            L_0x0056:
                int[] r0 = java.time.chrono.ChronoLocalDateImpl.AnonymousClass1.$SwitchMap$java$time$temporal$ChronoUnit     // Catch:{ NoSuchFieldError -> 0x0062 }
                java.time.temporal.ChronoUnit r1 = java.time.temporal.ChronoUnit.ERAS     // Catch:{ NoSuchFieldError -> 0x0062 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0062 }
                r2 = 8
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0062 }
            L_0x0062:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: java.time.chrono.ChronoLocalDateImpl.AnonymousClass1.<clinit>():void");
        }
    }

    @Override // java.time.chrono.ChronoLocalDate, java.time.temporal.Temporal
    public ChronoLocalDate minus(long j, TemporalUnit temporalUnit) {
        return super.minus(j, temporalUnit);
    }

    @Override // java.time.chrono.ChronoLocalDate
    public String toString() {
        long j = getLong(ChronoField.YEAR_OF_ERA);
        long j2 = getLong(ChronoField.MONTH_OF_YEAR);
        long j3 = getLong(ChronoField.DAY_OF_MONTH);
        StringBuilder sb = new StringBuilder(30);
        sb.append(getChronology().toString());
        sb.append(" ");
        sb.append(getEra());
        sb.append(" ");
        sb.append(j);
        String str = "-0";
        sb.append(j2 < 10 ? str : "-");
        sb.append(j2);
        if (j3 >= 10) {
            str = "-";
        }
        sb.append(str);
        sb.append(j3);
        return sb.toString();
    }
}
