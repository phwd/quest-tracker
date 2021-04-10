package com.android.server.am;

import com.google.protobuf.MessageLiteOrBuilder;

public interface KeyguardOccludedProtoOrBuilder extends MessageLiteOrBuilder {
    int getDisplayId();

    boolean getKeyguardOccluded();

    boolean hasDisplayId();

    boolean hasKeyguardOccluded();
}
