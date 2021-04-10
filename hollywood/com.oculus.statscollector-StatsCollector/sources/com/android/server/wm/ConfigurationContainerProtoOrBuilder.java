package com.android.server.wm;

import android.content.ConfigurationProto;
import com.google.protobuf.MessageLiteOrBuilder;

public interface ConfigurationContainerProtoOrBuilder extends MessageLiteOrBuilder {
    ConfigurationProto getFullConfiguration();

    ConfigurationProto getMergedOverrideConfiguration();

    ConfigurationProto getOverrideConfiguration();

    boolean hasFullConfiguration();

    boolean hasMergedOverrideConfiguration();

    boolean hasOverrideConfiguration();
}
