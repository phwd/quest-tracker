package com.android.server.wm;

import com.google.protobuf.MessageLiteOrBuilder;

public interface WindowContainerProtoOrBuilder extends MessageLiteOrBuilder {
    ConfigurationContainerProto getConfigurationContainer();

    int getOrientation();

    SurfaceAnimatorProto getSurfaceAnimator();

    boolean getVisible();

    boolean hasConfigurationContainer();

    boolean hasOrientation();

    boolean hasSurfaceAnimator();

    boolean hasVisible();
}
