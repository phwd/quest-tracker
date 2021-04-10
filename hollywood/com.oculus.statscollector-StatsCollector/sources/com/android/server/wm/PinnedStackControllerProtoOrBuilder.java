package com.android.server.wm;

import android.graphics.RectProto;
import com.google.protobuf.MessageLiteOrBuilder;

public interface PinnedStackControllerProtoOrBuilder extends MessageLiteOrBuilder {
    RectProto getDefaultBounds();

    RectProto getMovementBounds();

    boolean hasDefaultBounds();

    boolean hasMovementBounds();
}
