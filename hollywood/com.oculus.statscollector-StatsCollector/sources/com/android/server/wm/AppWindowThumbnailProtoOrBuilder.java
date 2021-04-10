package com.android.server.wm;

import com.google.protobuf.MessageLiteOrBuilder;

public interface AppWindowThumbnailProtoOrBuilder extends MessageLiteOrBuilder {
    int getHeight();

    SurfaceAnimatorProto getSurfaceAnimator();

    int getWidth();

    boolean hasHeight();

    boolean hasSurfaceAnimator();

    boolean hasWidth();
}
