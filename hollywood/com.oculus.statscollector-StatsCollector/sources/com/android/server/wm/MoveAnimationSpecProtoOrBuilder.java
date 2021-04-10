package com.android.server.wm;

import android.graphics.PointProto;
import com.google.protobuf.MessageLiteOrBuilder;

public interface MoveAnimationSpecProtoOrBuilder extends MessageLiteOrBuilder {
    long getDurationMs();

    PointProto getFrom();

    PointProto getTo();

    boolean hasDurationMs();

    boolean hasFrom();

    boolean hasTo();
}
