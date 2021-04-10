package com.android.server.job;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;

public interface JobStatusShortInfoProtoOrBuilder extends MessageLiteOrBuilder {
    String getBatteryName();

    ByteString getBatteryNameBytes();

    int getCallingUid();

    int getJobId();

    boolean hasBatteryName();

    boolean hasCallingUid();

    boolean hasJobId();
}
