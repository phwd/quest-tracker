package com.android.server.am;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;

public interface BroadcastRecordProtoOrBuilder extends MessageLiteOrBuilder {
    String getIntentAction();

    ByteString getIntentActionBytes();

    int getUserId();

    boolean hasIntentAction();

    boolean hasUserId();
}
