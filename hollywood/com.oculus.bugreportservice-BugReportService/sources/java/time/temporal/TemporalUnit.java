package java.time.temporal;

public interface TemporalUnit {
    Temporal addTo(Temporal temporal, long j);

    boolean isDateBased();
}
