package android.service;

import com.google.protobuf.MessageLiteOrBuilder;

public interface NetworkStatsRecorderProtoOrBuilder extends MessageLiteOrBuilder {
    NetworkStatsCollectionProto getCompleteHistory();

    long getPendingTotalBytes();

    boolean hasCompleteHistory();

    boolean hasPendingTotalBytes();
}
