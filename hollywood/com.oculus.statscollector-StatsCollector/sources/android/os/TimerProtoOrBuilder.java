package android.os;

import com.google.protobuf.MessageLiteOrBuilder;

public interface TimerProtoOrBuilder extends MessageLiteOrBuilder {
    long getCount();

    long getCurrentDurationMs();

    long getDurationMs();

    long getMaxDurationMs();

    long getTotalDurationMs();

    boolean hasCount();

    boolean hasCurrentDurationMs();

    boolean hasDurationMs();

    boolean hasMaxDurationMs();

    boolean hasTotalDurationMs();
}
