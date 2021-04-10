package android.service;

import com.google.protobuf.MessageLiteOrBuilder;

public interface NetworkStatsHistoryBucketProtoOrBuilder extends MessageLiteOrBuilder {
    long getBucketStartMs();

    long getOperations();

    long getRxBytes();

    long getRxPackets();

    long getTxBytes();

    long getTxPackets();

    boolean hasBucketStartMs();

    boolean hasOperations();

    boolean hasRxBytes();

    boolean hasRxPackets();

    boolean hasTxBytes();

    boolean hasTxPackets();
}
