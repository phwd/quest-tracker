package com.android.server.am;

import com.android.server.wm.ConfigurationContainerProto;
import com.android.server.wm.IdentifierProto;
import com.google.protobuf.MessageLiteOrBuilder;
import java.util.List;

public interface ActivityStackSupervisorProtoOrBuilder extends MessageLiteOrBuilder {
    ConfigurationContainerProto getConfigurationContainer();

    ActivityDisplayProto getDisplays(int i);

    int getDisplaysCount();

    List<ActivityDisplayProto> getDisplaysList();

    int getFocusedStackId();

    boolean getIsHomeRecentsComponent();

    KeyguardControllerProto getKeyguardController();

    IdentifierProto getPendingActivities(int i);

    int getPendingActivitiesCount();

    List<IdentifierProto> getPendingActivitiesList();

    IdentifierProto getResumedActivity();

    boolean hasConfigurationContainer();

    boolean hasFocusedStackId();

    boolean hasIsHomeRecentsComponent();

    boolean hasKeyguardController();

    boolean hasResumedActivity();
}
