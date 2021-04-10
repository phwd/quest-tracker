package com.android.server.wm;

import android.graphics.RectProto;
import com.google.protobuf.MessageLiteOrBuilder;

public interface DisplayFramesProtoOrBuilder extends MessageLiteOrBuilder {
    RectProto getStableBounds();

    boolean hasStableBounds();
}
