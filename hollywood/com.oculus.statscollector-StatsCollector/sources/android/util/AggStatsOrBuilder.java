package android.util;

import com.google.protobuf.MessageLiteOrBuilder;

public interface AggStatsOrBuilder extends MessageLiteOrBuilder {
    long getAverage();

    long getMax();

    long getMin();

    boolean hasAverage();

    boolean hasMax();

    boolean hasMin();
}
