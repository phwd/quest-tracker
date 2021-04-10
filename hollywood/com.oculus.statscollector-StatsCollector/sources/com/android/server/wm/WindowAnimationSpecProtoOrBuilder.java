package com.android.server.wm;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;

public interface WindowAnimationSpecProtoOrBuilder extends MessageLiteOrBuilder {
    String getAnimation();

    ByteString getAnimationBytes();

    boolean hasAnimation();
}
