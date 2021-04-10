package com.android.server.am;

import com.android.server.am.BroadcastQueueProto;
import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;
import java.util.List;

public interface BroadcastQueueProtoOrBuilder extends MessageLiteOrBuilder {
    BroadcastRecordProto getHistoricalBroadcasts(int i);

    int getHistoricalBroadcastsCount();

    List<BroadcastRecordProto> getHistoricalBroadcastsList();

    BroadcastQueueProto.BroadcastSummary getHistoricalBroadcastsSummary(int i);

    int getHistoricalBroadcastsSummaryCount();

    List<BroadcastQueueProto.BroadcastSummary> getHistoricalBroadcastsSummaryList();

    BroadcastRecordProto getOrderedBroadcasts(int i);

    int getOrderedBroadcastsCount();

    List<BroadcastRecordProto> getOrderedBroadcastsList();

    BroadcastRecordProto getParallelBroadcasts(int i);

    int getParallelBroadcastsCount();

    List<BroadcastRecordProto> getParallelBroadcastsList();

    BroadcastRecordProto getPendingBroadcast();

    String getQueueName();

    ByteString getQueueNameBytes();

    boolean hasPendingBroadcast();

    boolean hasQueueName();
}
