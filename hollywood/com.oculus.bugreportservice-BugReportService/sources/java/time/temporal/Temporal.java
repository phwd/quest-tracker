package java.time.temporal;

public interface Temporal extends TemporalAccessor {
    Temporal plus(long j, TemporalUnit temporalUnit);

    Temporal with(TemporalField temporalField, long j);

    default Temporal with(TemporalAdjuster temporalAdjuster) {
        return temporalAdjuster.adjustInto(this);
    }

    default Temporal minus(long j, TemporalUnit temporalUnit) {
        long j2;
        if (j == Long.MIN_VALUE) {
            this = plus(Long.MAX_VALUE, temporalUnit);
            j2 = 1;
        } else {
            j2 = -j;
        }
        return this.plus(j2, temporalUnit);
    }
}
