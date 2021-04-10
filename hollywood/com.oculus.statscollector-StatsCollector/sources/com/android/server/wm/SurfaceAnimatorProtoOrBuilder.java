package com.android.server.wm;

import android.view.SurfaceControlProto;
import com.google.protobuf.MessageLiteOrBuilder;

public interface SurfaceAnimatorProtoOrBuilder extends MessageLiteOrBuilder {
    AnimationAdapterProto getAnimationAdapter();

    boolean getAnimationStartDelayed();

    SurfaceControlProto getLeash();

    boolean hasAnimationAdapter();

    boolean hasAnimationStartDelayed();

    boolean hasLeash();
}
