package com.android.server.am;

import com.android.server.wm.ConfigurationContainerProto;
import com.android.server.wm.IdentifierProto;
import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;

public interface ActivityRecordProtoOrBuilder extends MessageLiteOrBuilder {
    ConfigurationContainerProto getConfigurationContainer();

    boolean getFrontOfTask();

    IdentifierProto getIdentifier();

    int getProcId();

    String getState();

    ByteString getStateBytes();

    boolean getTranslucent();

    boolean getVisible();

    boolean hasConfigurationContainer();

    boolean hasFrontOfTask();

    boolean hasIdentifier();

    boolean hasProcId();

    boolean hasState();

    boolean hasTranslucent();

    boolean hasVisible();
}
