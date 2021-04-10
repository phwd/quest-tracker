package com.android.server.wm;

import android.view.DisplayInfoProto;
import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;
import java.util.List;

public interface DisplayContentProtoOrBuilder extends MessageLiteOrBuilder {
    WindowTokenProto getAboveAppWindows(int i);

    int getAboveAppWindowsCount();

    List<WindowTokenProto> getAboveAppWindowsList();

    AppTransitionProto getAppTransition();

    WindowTokenProto getBelowAppWindows(int i);

    int getBelowAppWindowsCount();

    List<WindowTokenProto> getBelowAppWindowsList();

    IdentifierProto getChangingApps(int i);

    int getChangingAppsCount();

    List<IdentifierProto> getChangingAppsList();

    IdentifierProto getClosingApps(int i);

    int getClosingAppsCount();

    List<IdentifierProto> getClosingAppsList();

    DisplayFramesProto getDisplayFrames();

    DisplayInfoProto getDisplayInfo();

    DockedStackDividerControllerProto getDockedStackDividerController();

    int getDpi();

    String getFocusedApp();

    ByteString getFocusedAppBytes();

    int getId();

    WindowTokenProto getImeWindows(int i);

    int getImeWindowsCount();

    List<WindowTokenProto> getImeWindowsList();

    IdentifierProto getOpeningApps(int i);

    int getOpeningAppsCount();

    List<IdentifierProto> getOpeningAppsList();

    PinnedStackControllerProto getPinnedStackController();

    int getRotation();

    ScreenRotationAnimationProto getScreenRotationAnimation();

    StackProto getStacks(int i);

    int getStacksCount();

    List<StackProto> getStacksList();

    @Deprecated
    int getSurfaceSize();

    WindowContainerProto getWindowContainer();

    boolean hasAppTransition();

    boolean hasDisplayFrames();

    boolean hasDisplayInfo();

    boolean hasDockedStackDividerController();

    boolean hasDpi();

    boolean hasFocusedApp();

    boolean hasId();

    boolean hasPinnedStackController();

    boolean hasRotation();

    boolean hasScreenRotationAnimation();

    @Deprecated
    boolean hasSurfaceSize();

    boolean hasWindowContainer();
}
