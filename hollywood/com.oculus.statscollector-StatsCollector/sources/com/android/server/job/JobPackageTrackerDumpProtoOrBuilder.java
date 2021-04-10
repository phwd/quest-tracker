package com.android.server.job;

import com.google.protobuf.MessageLiteOrBuilder;
import java.util.List;

public interface JobPackageTrackerDumpProtoOrBuilder extends MessageLiteOrBuilder {
    DataSetProto getCurrentStats();

    DataSetProto getHistoricalStats(int i);

    int getHistoricalStatsCount();

    List<DataSetProto> getHistoricalStatsList();

    boolean hasCurrentStats();
}
