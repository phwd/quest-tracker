package com.android.server.wm;

import android.view.SurfaceProto;
import com.google.protobuf.MessageLiteOrBuilder;

public interface WindowOrientationListenerProtoOrBuilder extends MessageLiteOrBuilder {
    boolean getEnabled();

    SurfaceProto.Rotation getRotation();

    boolean hasEnabled();

    boolean hasRotation();
}
