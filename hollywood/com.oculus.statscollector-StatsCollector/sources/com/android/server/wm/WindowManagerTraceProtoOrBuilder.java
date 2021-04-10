package com.android.server.wm;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;

public interface WindowManagerTraceProtoOrBuilder extends MessageLiteOrBuilder {
    long getElapsedRealtimeNanos();

    String getWhere();

    ByteString getWhereBytes();

    WindowManagerServiceDumpProto getWindowManagerService();

    boolean hasElapsedRealtimeNanos();

    boolean hasWhere();

    boolean hasWindowManagerService();
}
