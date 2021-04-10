package com.android.server.am;

import com.android.server.wm.ConfigurationContainerProto;
import com.android.server.wm.IdentifierProto;
import com.google.protobuf.MessageLiteOrBuilder;
import java.util.List;

public interface ActivityDisplayProtoOrBuilder extends MessageLiteOrBuilder {
    ConfigurationContainerProto getConfigurationContainer();

    int getFocusedStackId();

    int getId();

    IdentifierProto getResumedActivity();

    boolean getSingleTaskInstance();

    ActivityStackProto getStacks(int i);

    int getStacksCount();

    List<ActivityStackProto> getStacksList();

    boolean hasConfigurationContainer();

    boolean hasFocusedStackId();

    boolean hasId();

    boolean hasResumedActivity();

    boolean hasSingleTaskInstance();
}
