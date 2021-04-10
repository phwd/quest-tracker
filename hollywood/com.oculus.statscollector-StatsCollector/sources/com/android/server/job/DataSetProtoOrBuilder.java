package com.android.server.job;

import com.android.server.job.DataSetProto;
import com.google.protobuf.MessageLiteOrBuilder;
import java.util.List;

public interface DataSetProtoOrBuilder extends MessageLiteOrBuilder {
    long getElapsedTimeMs();

    int getMaxConcurrency();

    int getMaxForegroundConcurrency();

    DataSetProto.PackageEntryProto getPackageEntries(int i);

    int getPackageEntriesCount();

    List<DataSetProto.PackageEntryProto> getPackageEntriesList();

    long getPeriodMs();

    long getStartClockTimeMs();

    boolean hasElapsedTimeMs();

    boolean hasMaxConcurrency();

    boolean hasMaxForegroundConcurrency();

    boolean hasPeriodMs();

    boolean hasStartClockTimeMs();
}
