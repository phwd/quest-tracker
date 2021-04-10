package com.android.server.job;

import com.google.protobuf.MessageLiteOrBuilder;

public interface JobConcurrencyManagerProtoOrBuilder extends MessageLiteOrBuilder {
    boolean getCurrentInteractive();

    boolean getEffectiveInteractive();

    JobCountTrackerProto getJobCountTracker();

    int getMemoryTrimLevel();

    long getTimeSinceLastScreenOffMs();

    long getTimeSinceLastScreenOnMs();

    boolean hasCurrentInteractive();

    boolean hasEffectiveInteractive();

    boolean hasJobCountTracker();

    boolean hasMemoryTrimLevel();

    boolean hasTimeSinceLastScreenOffMs();

    boolean hasTimeSinceLastScreenOnMs();
}
