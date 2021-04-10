package com.android.server.wm;

import com.google.protobuf.MessageLiteOrBuilder;
import java.util.List;

public interface WindowTokenProtoOrBuilder extends MessageLiteOrBuilder {
    int getHashCode();

    boolean getHidden();

    boolean getPaused();

    boolean getWaitingToShow();

    WindowContainerProto getWindowContainer();

    WindowStateProto getWindows(int i);

    int getWindowsCount();

    List<WindowStateProto> getWindowsList();

    boolean hasHashCode();

    boolean hasHidden();

    boolean hasPaused();

    boolean hasWaitingToShow();

    boolean hasWindowContainer();
}
