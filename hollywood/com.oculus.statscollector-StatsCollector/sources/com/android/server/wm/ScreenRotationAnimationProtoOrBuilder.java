package com.android.server.wm;

import com.google.protobuf.MessageLiteOrBuilder;

public interface ScreenRotationAnimationProtoOrBuilder extends MessageLiteOrBuilder {
    boolean getAnimationRunning();

    boolean getStarted();

    boolean hasAnimationRunning();

    boolean hasStarted();
}
