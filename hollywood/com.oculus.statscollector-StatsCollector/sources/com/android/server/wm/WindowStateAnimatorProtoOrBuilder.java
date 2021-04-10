package com.android.server.wm;

import android.graphics.RectProto;
import com.android.server.wm.WindowStateAnimatorProto;
import com.google.protobuf.MessageLiteOrBuilder;

public interface WindowStateAnimatorProtoOrBuilder extends MessageLiteOrBuilder {
    WindowStateAnimatorProto.DrawState getDrawState();

    RectProto getLastClipRect();

    WindowSurfaceControllerProto getSurface();

    RectProto getSystemDecorRect();

    boolean hasDrawState();

    boolean hasLastClipRect();

    boolean hasSurface();

    boolean hasSystemDecorRect();
}
