package java.time.chrono;

import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.util.Comparator;
import java.util.concurrent.ConcurrentHashMap;

public abstract class AbstractChronology implements Chronology {
    private static final ConcurrentHashMap CHRONOS_BY_ID = new ConcurrentHashMap();
    private static final ConcurrentHashMap CHRONOS_BY_TYPE = new ConcurrentHashMap();
    static final Comparator DATE_ORDER = $$Lambda$AbstractChronology$j22w8kHhJoqCd56hhLQK1G0VLFw.INSTANCE;
    static final Comparator DATE_TIME_ORDER = $$Lambda$AbstractChronology$onW9aZyLFliH5Gg1qLodD_GoPfA.INSTANCE;
    static final Comparator INSTANT_ORDER = $$Lambda$AbstractChronology$5b0W7uLeaWkn0HLPDKwPXzJ7HPo.INSTANCE;

    static /* synthetic */ int lambda$static$b5a61975$1(ChronoLocalDateTime chronoLocalDateTime, ChronoLocalDateTime chronoLocalDateTime2) {
        int compare = Long.compare(chronoLocalDateTime.toLocalDate().toEpochDay(), chronoLocalDateTime2.toLocalDate().toEpochDay());
        return compare == 0 ? Long.compare(chronoLocalDateTime.toLocalTime().toNanoOfDay(), chronoLocalDateTime2.toLocalTime().toNanoOfDay()) : compare;
    }

    static /* synthetic */ int lambda$static$2241c452$1(ChronoZonedDateTime chronoZonedDateTime, ChronoZonedDateTime chronoZonedDateTime2) {
        int compare = Long.compare(chronoZonedDateTime.toEpochSecond(), chronoZonedDateTime2.toEpochSecond());
        return compare == 0 ? Long.compare((long) chronoZonedDateTime.toLocalTime().getNano(), (long) chronoZonedDateTime2.toLocalTime().getNano()) : compare;
    }

    protected AbstractChronology() {
    }

    @Override // java.time.chrono.Chronology
    public int compareTo(Chronology chronology) {
        return getId().compareTo(chronology.getId());
    }

    @Override // java.time.chrono.Chronology
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof AbstractChronology) {
            return compareTo((AbstractChronology) obj) == 0;
        }
        return false;
    }

    public int hashCode() {
        return getId().hashCode() ^ getClass().hashCode();
    }

    @Override // java.time.chrono.Chronology
    public String toString() {
        return getId();
    }

    /* access modifiers changed from: package-private */
    public Object writeReplace() {
        return new Ser((byte) 1, this);
    }

    private void readObject(ObjectInputStream objectInputStream) {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }
}
