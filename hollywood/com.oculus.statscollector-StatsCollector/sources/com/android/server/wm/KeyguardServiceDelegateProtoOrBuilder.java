package com.android.server.wm;

import com.android.server.wm.KeyguardServiceDelegateProto;
import com.google.protobuf.MessageLiteOrBuilder;

public interface KeyguardServiceDelegateProtoOrBuilder extends MessageLiteOrBuilder {
    KeyguardServiceDelegateProto.InteractiveState getInteractiveState();

    boolean getOccluded();

    KeyguardServiceDelegateProto.ScreenState getScreenState();

    boolean getSecure();

    boolean getShowing();

    boolean hasInteractiveState();

    boolean hasOccluded();

    boolean hasScreenState();

    boolean hasSecure();

    boolean hasShowing();
}
