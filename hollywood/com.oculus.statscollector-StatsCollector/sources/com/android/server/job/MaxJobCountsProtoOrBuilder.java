package com.android.server.job;

import com.google.protobuf.MessageLiteOrBuilder;

public interface MaxJobCountsProtoOrBuilder extends MessageLiteOrBuilder {
    int getMaxBg();

    int getMinBg();

    int getTotalJobs();

    boolean hasMaxBg();

    boolean hasMinBg();

    boolean hasTotalJobs();
}
