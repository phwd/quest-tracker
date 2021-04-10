package com.android.server;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;

public interface FilterStatsProtoOrBuilder extends MessageLiteOrBuilder {
    int getCount();

    long getLastFlightTimeRealtime();

    int getNesting();

    long getStartTimeRealtime();

    String getTag();

    ByteString getTagBytes();

    long getTotalFlightDurationMs();

    int getWakeupCount();

    boolean hasCount();

    boolean hasLastFlightTimeRealtime();

    boolean hasNesting();

    boolean hasStartTimeRealtime();

    boolean hasTag();

    boolean hasTotalFlightDurationMs();

    boolean hasWakeupCount();
}
