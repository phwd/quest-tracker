package com.android.server.am;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;

public interface GrantUriProtoOrBuilder extends MessageLiteOrBuilder {
    int getSourceUserId();

    String getUri();

    ByteString getUriBytes();

    boolean hasSourceUserId();

    boolean hasUri();
}
