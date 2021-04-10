package com.android.server.am;

import android.graphics.RectProto;
import com.android.server.wm.ConfigurationContainerProto;
import com.android.server.wm.IdentifierProto;
import com.google.protobuf.MessageLiteOrBuilder;
import java.util.List;

public interface ActivityStackProtoOrBuilder extends MessageLiteOrBuilder {
    RectProto getBounds();

    ConfigurationContainerProto getConfigurationContainer();

    int getDisplayId();

    boolean getFullscreen();

    int getId();

    IdentifierProto getResumedActivity();

    TaskRecordProto getTasks(int i);

    int getTasksCount();

    List<TaskRecordProto> getTasksList();

    boolean hasBounds();

    boolean hasConfigurationContainer();

    boolean hasDisplayId();

    boolean hasFullscreen();

    boolean hasId();

    boolean hasResumedActivity();
}
