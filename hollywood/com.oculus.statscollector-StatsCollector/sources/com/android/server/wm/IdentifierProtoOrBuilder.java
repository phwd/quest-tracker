package com.android.server.wm;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;

public interface IdentifierProtoOrBuilder extends MessageLiteOrBuilder {
    int getHashCode();

    String getTitle();

    ByteString getTitleBytes();

    int getUserId();

    boolean hasHashCode();

    boolean hasTitle();

    boolean hasUserId();
}
