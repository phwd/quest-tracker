package com.android.server.am;

import android.app.ProcessStateEnum;
import android.util.Duration;
import com.android.server.am.UidRecordProto;
import com.google.protobuf.MessageLiteOrBuilder;
import java.util.List;

public interface UidRecordProtoOrBuilder extends MessageLiteOrBuilder {
    ProcessStateEnum getCurrent();

    boolean getEphemeral();

    boolean getFgServices();

    boolean getIdle();

    Duration getLastBackgroundTime();

    UidRecordProto.Change getLastReportedChanges(int i);

    int getLastReportedChangesCount();

    List<UidRecordProto.Change> getLastReportedChangesList();

    UidRecordProto.ProcStateSequence getNetworkStateUpdate();

    int getNumProcs();

    int getUid();

    boolean getWhilelist();

    boolean hasCurrent();

    boolean hasEphemeral();

    boolean hasFgServices();

    boolean hasIdle();

    boolean hasLastBackgroundTime();

    boolean hasNetworkStateUpdate();

    boolean hasNumProcs();

    boolean hasUid();

    boolean hasWhilelist();
}
