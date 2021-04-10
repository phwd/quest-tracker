package com.android.server.am;

import com.google.protobuf.MessageLiteOrBuilder;
import java.util.List;

public interface KeyguardControllerProtoOrBuilder extends MessageLiteOrBuilder {
    boolean getAodShowing();

    KeyguardOccludedProto getKeyguardOccludedStates(int i);

    int getKeyguardOccludedStatesCount();

    List<KeyguardOccludedProto> getKeyguardOccludedStatesList();

    boolean getKeyguardShowing();

    boolean hasAodShowing();

    boolean hasKeyguardShowing();
}
