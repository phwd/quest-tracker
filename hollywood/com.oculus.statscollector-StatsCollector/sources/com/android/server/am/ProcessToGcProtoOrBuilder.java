package com.android.server.am;

import com.google.protobuf.MessageLiteOrBuilder;

public interface ProcessToGcProtoOrBuilder extends MessageLiteOrBuilder {
    long getLastGcedMs();

    long getLastLowMemoryMs();

    long getNowUptimeMs();

    ProcessRecordProto getProc();

    boolean getReportLowMemory();

    boolean hasLastGcedMs();

    boolean hasLastLowMemoryMs();

    boolean hasNowUptimeMs();

    boolean hasProc();

    boolean hasReportLowMemory();
}
