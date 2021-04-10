package com.android.server.wm;

import android.graphics.RectProto;
import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;
import java.util.List;

public interface AppWindowTokenProtoOrBuilder extends MessageLiteOrBuilder {
    boolean getAllDrawn();

    boolean getAppStopped();

    boolean getClientHidden();

    boolean getDeferHidingClient();

    boolean getFillsParent();

    RectProto getFrozenBounds(int i);

    int getFrozenBoundsCount();

    List<RectProto> getFrozenBoundsList();

    boolean getHiddenRequested();

    boolean getHiddenSetFromTransferredStartingWindow();

    boolean getIsReallyAnimating();

    boolean getIsWaitingForTransitionStart();

    boolean getLastAllDrawn();

    boolean getLastSurfaceShowing();

    String getName();

    ByteString getNameBytes();

    int getNumDrawnWindows();

    int getNumInterestingWindows();

    boolean getRemoved();

    boolean getReportedDrawn();

    boolean getReportedVisible();

    boolean getStartingDisplayed();

    boolean getStartingMoved();

    IdentifierProto getStartingWindow();

    AppWindowThumbnailProto getThumbnail();

    WindowTokenProto getWindowToken();

    boolean hasAllDrawn();

    boolean hasAppStopped();

    boolean hasClientHidden();

    boolean hasDeferHidingClient();

    boolean hasFillsParent();

    boolean hasHiddenRequested();

    boolean hasHiddenSetFromTransferredStartingWindow();

    boolean hasIsReallyAnimating();

    boolean hasIsWaitingForTransitionStart();

    boolean hasLastAllDrawn();

    boolean hasLastSurfaceShowing();

    boolean hasName();

    boolean hasNumDrawnWindows();

    boolean hasNumInterestingWindows();

    boolean hasRemoved();

    boolean hasReportedDrawn();

    boolean hasReportedVisible();

    boolean hasStartingDisplayed();

    boolean hasStartingMoved();

    boolean hasStartingWindow();

    boolean hasThumbnail();

    boolean hasWindowToken();
}
