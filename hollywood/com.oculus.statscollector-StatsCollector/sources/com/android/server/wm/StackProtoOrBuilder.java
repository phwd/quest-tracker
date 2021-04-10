package com.android.server.wm;

import android.graphics.RectProto;
import com.google.protobuf.MessageLiteOrBuilder;
import java.util.List;

public interface StackProtoOrBuilder extends MessageLiteOrBuilder {
    float getAdjustDividerAmount();

    float getAdjustImeAmount();

    RectProto getAdjustedBounds();

    boolean getAdjustedForIme();

    boolean getAnimatingBounds();

    boolean getAnimationBackgroundSurfaceIsDimming();

    RectProto getBounds();

    boolean getDeferRemoval();

    boolean getFillsParent();

    int getId();

    float getMinimizeAmount();

    TaskProto getTasks(int i);

    int getTasksCount();

    List<TaskProto> getTasksList();

    WindowContainerProto getWindowContainer();

    boolean hasAdjustDividerAmount();

    boolean hasAdjustImeAmount();

    boolean hasAdjustedBounds();

    boolean hasAdjustedForIme();

    boolean hasAnimatingBounds();

    boolean hasAnimationBackgroundSurfaceIsDimming();

    boolean hasBounds();

    boolean hasDeferRemoval();

    boolean hasFillsParent();

    boolean hasId();

    boolean hasMinimizeAmount();

    boolean hasWindowContainer();
}
