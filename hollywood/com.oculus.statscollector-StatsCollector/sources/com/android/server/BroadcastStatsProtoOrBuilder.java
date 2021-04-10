package com.android.server;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;

public interface BroadcastStatsProtoOrBuilder extends MessageLiteOrBuilder {
    int getCount();

    int getNesting();

    String getPackageName();

    ByteString getPackageNameBytes();

    long getStartTimeRealtime();

    long getTotalFlightDurationMs();

    int getUid();

    int getWakeupCount();

    boolean hasCount();

    boolean hasNesting();

    boolean hasPackageName();

    boolean hasStartTimeRealtime();

    boolean hasTotalFlightDurationMs();

    boolean hasUid();

    boolean hasWakeupCount();
}
