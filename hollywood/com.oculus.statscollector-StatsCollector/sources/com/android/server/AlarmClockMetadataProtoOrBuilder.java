package com.android.server;

import com.google.protobuf.MessageLiteOrBuilder;

public interface AlarmClockMetadataProtoOrBuilder extends MessageLiteOrBuilder {
    boolean getIsPendingSend();

    long getTriggerTimeMs();

    int getUser();

    boolean hasIsPendingSend();

    boolean hasTriggerTimeMs();

    boolean hasUser();
}
