package com.android.server;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;

public interface IdleDispatchEntryProtoOrBuilder extends MessageLiteOrBuilder {
    long getArgRealtime();

    long getEntryCreationRealtime();

    String getOp();

    ByteString getOpBytes();

    String getPkg();

    ByteString getPkgBytes();

    String getTag();

    ByteString getTagBytes();

    int getUid();

    boolean hasArgRealtime();

    boolean hasEntryCreationRealtime();

    boolean hasOp();

    boolean hasPkg();

    boolean hasTag();

    boolean hasUid();
}
