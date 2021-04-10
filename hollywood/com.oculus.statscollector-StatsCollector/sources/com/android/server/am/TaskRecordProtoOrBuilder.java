package com.android.server.am;

import android.graphics.RectProto;
import com.android.server.wm.ConfigurationContainerProto;
import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;
import java.util.List;

public interface TaskRecordProtoOrBuilder extends MessageLiteOrBuilder {
    ActivityRecordProto getActivities(int i);

    int getActivitiesCount();

    List<ActivityRecordProto> getActivitiesList();

    int getActivityType();

    RectProto getBounds();

    ConfigurationContainerProto getConfigurationContainer();

    boolean getFullscreen();

    int getId();

    RectProto getLastNonFullscreenBounds();

    int getMinHeight();

    int getMinWidth();

    String getOrigActivity();

    ByteString getOrigActivityBytes();

    String getRealActivity();

    ByteString getRealActivityBytes();

    int getResizeMode();

    int getStackId();

    boolean hasActivityType();

    boolean hasBounds();

    boolean hasConfigurationContainer();

    boolean hasFullscreen();

    boolean hasId();

    boolean hasLastNonFullscreenBounds();

    boolean hasMinHeight();

    boolean hasMinWidth();

    boolean hasOrigActivity();

    boolean hasRealActivity();

    boolean hasResizeMode();

    boolean hasStackId();
}
