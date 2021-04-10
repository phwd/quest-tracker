package com.android.server;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;

public interface WakeupEventProtoOrBuilder extends MessageLiteOrBuilder {
    String getAction();

    ByteString getActionBytes();

    int getUid();

    long getWhen();

    boolean hasAction();

    boolean hasUid();

    boolean hasWhen();
}
