package com.android.server.job;

import com.android.server.job.JobPackageHistoryProto;
import com.google.protobuf.MessageLiteOrBuilder;
import java.util.List;

public interface JobPackageHistoryProtoOrBuilder extends MessageLiteOrBuilder {
    JobPackageHistoryProto.HistoryEvent getHistoryEvent(int i);

    int getHistoryEventCount();

    List<JobPackageHistoryProto.HistoryEvent> getHistoryEventList();
}
