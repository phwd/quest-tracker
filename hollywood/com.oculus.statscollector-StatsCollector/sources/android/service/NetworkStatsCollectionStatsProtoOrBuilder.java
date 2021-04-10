package android.service;

import com.google.protobuf.MessageLiteOrBuilder;

public interface NetworkStatsCollectionStatsProtoOrBuilder extends MessageLiteOrBuilder {
    NetworkStatsHistoryProto getHistory();

    NetworkStatsCollectionKeyProto getKey();

    boolean hasHistory();

    boolean hasKey();
}
