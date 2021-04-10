package com.android.server.am;

import com.android.server.am.UserStateProto;
import com.google.protobuf.MessageLiteOrBuilder;

public interface UserStateProtoOrBuilder extends MessageLiteOrBuilder {
    UserStateProto.State getState();

    boolean getSwitching();

    boolean hasState();

    boolean hasSwitching();
}
