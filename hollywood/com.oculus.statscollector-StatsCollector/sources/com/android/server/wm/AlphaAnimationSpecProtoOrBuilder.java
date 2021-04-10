package com.android.server.wm;

import com.google.protobuf.MessageLiteOrBuilder;

public interface AlphaAnimationSpecProtoOrBuilder extends MessageLiteOrBuilder {
    long getDurationMs();

    float getFrom();

    float getTo();

    boolean hasDurationMs();

    boolean hasFrom();

    boolean hasTo();
}
