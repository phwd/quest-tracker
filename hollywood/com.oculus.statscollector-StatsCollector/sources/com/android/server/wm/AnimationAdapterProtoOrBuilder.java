package com.android.server.wm;

import com.google.protobuf.MessageLiteOrBuilder;

public interface AnimationAdapterProtoOrBuilder extends MessageLiteOrBuilder {
    LocalAnimationAdapterProto getLocal();

    RemoteAnimationAdapterWrapperProto getRemote();

    boolean hasLocal();

    boolean hasRemote();
}
