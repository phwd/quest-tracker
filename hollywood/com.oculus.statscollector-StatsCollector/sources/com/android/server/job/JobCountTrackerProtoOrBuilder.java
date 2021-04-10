package com.android.server.job;

import com.google.protobuf.MessageLiteOrBuilder;

public interface JobCountTrackerProtoOrBuilder extends MessageLiteOrBuilder {
    int getConfigNumMaxBgJobs();

    int getConfigNumMaxTotalJobs();

    int getConfigNumMinBgJobs();

    int getNumPendingBgJobs();

    int getNumPendingFgJobs();

    int getNumRunningBgJobs();

    int getNumRunningFgJobs();

    boolean hasConfigNumMaxBgJobs();

    boolean hasConfigNumMaxTotalJobs();

    boolean hasConfigNumMinBgJobs();

    boolean hasNumPendingBgJobs();

    boolean hasNumPendingFgJobs();

    boolean hasNumRunningBgJobs();

    boolean hasNumRunningFgJobs();
}
