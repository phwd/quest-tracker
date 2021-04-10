package android.service;

import com.google.protobuf.MessageLiteOrBuilder;
import java.util.List;

public interface NetworkStatsHistoryProtoOrBuilder extends MessageLiteOrBuilder {
    long getBucketDurationMs();

    NetworkStatsHistoryBucketProto getBuckets(int i);

    int getBucketsCount();

    List<NetworkStatsHistoryBucketProto> getBucketsList();

    boolean hasBucketDurationMs();
}
