package com.android.server.wm;

import com.google.protobuf.MessageLiteOrBuilder;

public interface AnimationSpecProtoOrBuilder extends MessageLiteOrBuilder {
    AlphaAnimationSpecProto getAlpha();

    MoveAnimationSpecProto getMove();

    WindowAnimationSpecProto getWindow();

    boolean hasAlpha();

    boolean hasMove();

    boolean hasWindow();
}
