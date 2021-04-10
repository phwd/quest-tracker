package android.service.procstats;

import android.util.AggStats;
import com.google.protobuf.MessageLiteOrBuilder;

public interface ProcessStatsStateProtoOrBuilder extends MessageLiteOrBuilder {
    long getDurationMs();

    MemoryState getMemoryState();

    ProcessState getProcessState();

    AggStats getPss();

    long getRealtimeDurationMs();

    AggStats getRss();

    int getSampleSize();

    ScreenState getScreenState();

    AggStats getUss();

    boolean hasDurationMs();

    boolean hasMemoryState();

    boolean hasProcessState();

    boolean hasPss();

    boolean hasRealtimeDurationMs();

    boolean hasRss();

    boolean hasSampleSize();

    boolean hasScreenState();

    boolean hasUss();
}
