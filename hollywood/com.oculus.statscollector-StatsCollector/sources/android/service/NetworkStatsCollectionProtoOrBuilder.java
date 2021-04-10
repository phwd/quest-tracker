package android.service;

import com.google.protobuf.MessageLiteOrBuilder;
import java.util.List;

public interface NetworkStatsCollectionProtoOrBuilder extends MessageLiteOrBuilder {
    NetworkStatsCollectionStatsProto getStats(int i);

    int getStatsCount();

    List<NetworkStatsCollectionStatsProto> getStatsList();
}
