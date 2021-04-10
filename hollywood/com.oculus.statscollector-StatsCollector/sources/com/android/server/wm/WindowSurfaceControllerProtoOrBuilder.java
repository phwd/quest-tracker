package com.android.server.wm;

import com.google.protobuf.MessageLiteOrBuilder;

public interface WindowSurfaceControllerProtoOrBuilder extends MessageLiteOrBuilder {
    int getLayer();

    boolean getShown();

    boolean hasLayer();

    boolean hasShown();
}
