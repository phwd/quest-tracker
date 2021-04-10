package com.android.server.am;

import com.google.protobuf.MessageLiteOrBuilder;

public interface ActivityManagerServiceDumpServicesProtoOrBuilder extends MessageLiteOrBuilder {
    ActiveServicesProto getActiveServices();

    boolean hasActiveServices();
}
