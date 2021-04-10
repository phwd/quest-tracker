package com.android.server.wm;

import android.view.TransitionTypeEnum;
import com.android.server.wm.AppTransitionProto;
import com.google.protobuf.MessageLiteOrBuilder;

public interface AppTransitionProtoOrBuilder extends MessageLiteOrBuilder {
    AppTransitionProto.AppState getAppTransitionState();

    TransitionTypeEnum getLastUsedAppTransition();

    boolean hasAppTransitionState();

    boolean hasLastUsedAppTransition();
}
