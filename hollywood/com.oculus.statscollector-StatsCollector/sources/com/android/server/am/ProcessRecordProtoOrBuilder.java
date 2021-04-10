package com.android.server.am;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;

public interface ProcessRecordProtoOrBuilder extends MessageLiteOrBuilder {
    int getAppId();

    int getIsolatedAppId();

    int getLruIndex();

    boolean getPersistent();

    int getPid();

    String getProcessName();

    ByteString getProcessNameBytes();

    int getUid();

    int getUserId();

    boolean hasAppId();

    boolean hasIsolatedAppId();

    boolean hasLruIndex();

    boolean hasPersistent();

    boolean hasPid();

    boolean hasProcessName();

    boolean hasUid();

    boolean hasUserId();
}
