package com.android.server.am;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;

public interface ImportanceTokenProtoOrBuilder extends MessageLiteOrBuilder {
    int getPid();

    String getReason();

    ByteString getReasonBytes();

    String getToken();

    ByteString getTokenBytes();

    boolean hasPid();

    boolean hasReason();

    boolean hasToken();
}
