package com.android.server.power;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;

public interface SuspendBlockerProtoOrBuilder extends MessageLiteOrBuilder {
    String getName();

    ByteString getNameBytes();

    int getReferenceCount();

    boolean hasName();

    boolean hasReferenceCount();
}
