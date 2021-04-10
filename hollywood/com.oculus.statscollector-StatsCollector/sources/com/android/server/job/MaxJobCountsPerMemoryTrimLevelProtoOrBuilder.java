package com.android.server.job;

import com.google.protobuf.MessageLiteOrBuilder;

public interface MaxJobCountsPerMemoryTrimLevelProtoOrBuilder extends MessageLiteOrBuilder {
    MaxJobCountsProto getCritical();

    MaxJobCountsProto getLow();

    MaxJobCountsProto getModerate();

    MaxJobCountsProto getNormal();

    boolean hasCritical();

    boolean hasLow();

    boolean hasModerate();

    boolean hasNormal();
}
