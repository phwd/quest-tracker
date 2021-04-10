package com.android.server.wm;

import android.view.RemoteAnimationTargetProto;
import com.google.protobuf.MessageLiteOrBuilder;

public interface RemoteAnimationAdapterWrapperProtoOrBuilder extends MessageLiteOrBuilder {
    RemoteAnimationTargetProto getTarget();

    boolean hasTarget();
}
