package com.android.server.wm;

import android.graphics.RectProto;
import com.google.protobuf.MessageLiteOrBuilder;
import java.util.List;

public interface TaskProtoOrBuilder extends MessageLiteOrBuilder {
    AppWindowTokenProto getAppWindowTokens(int i);

    int getAppWindowTokensCount();

    List<AppWindowTokenProto> getAppWindowTokensList();

    RectProto getBounds();

    boolean getDeferRemoval();

    RectProto getDisplayedBounds();

    boolean getFillsParent();

    int getId();

    int getSurfaceHeight();

    int getSurfaceWidth();

    WindowContainerProto getWindowContainer();

    boolean hasBounds();

    boolean hasDeferRemoval();

    boolean hasDisplayedBounds();

    boolean hasFillsParent();

    boolean hasId();

    boolean hasSurfaceHeight();

    boolean hasSurfaceWidth();

    boolean hasWindowContainer();
}
