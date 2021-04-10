package java.time.temporal;

public interface TemporalQuery {
    Object queryFrom(TemporalAccessor temporalAccessor);
}
