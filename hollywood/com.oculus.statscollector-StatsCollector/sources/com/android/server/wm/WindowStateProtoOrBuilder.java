package com.android.server.wm;

import android.graphics.RectProto;
import android.view.DisplayCutoutProto;
import android.view.WindowLayoutParamsProto;
import com.google.protobuf.MessageLiteOrBuilder;
import java.util.List;

public interface WindowStateProtoOrBuilder extends MessageLiteOrBuilder {
    boolean getAnimatingExit();

    WindowStateAnimatorProto getAnimator();

    WindowLayoutParamsProto getAttributes();

    WindowStateProto getChildWindows(int i);

    int getChildWindowsCount();

    List<WindowStateProto> getChildWindowsList();

    @Deprecated
    RectProto getContainingFrame();

    @Deprecated
    RectProto getContentFrame();

    @Deprecated
    RectProto getContentInsets();

    @Deprecated
    DisplayCutoutProto getCutout();

    @Deprecated
    RectProto getDecorFrame();

    boolean getDestroying();

    @Deprecated
    RectProto getDisplayFrame();

    int getDisplayId();

    long getFinishedSeamlessRotationFrame();

    boolean getForceSeamlessRotation();

    @Deprecated
    RectProto getFrame();

    RectProto getGivenContentInsets();

    boolean getHasSurface();

    IdentifierProto getIdentifier();

    boolean getIsOnScreen();

    boolean getIsReadyForDisplay();

    boolean getIsVisible();

    @Deprecated
    RectProto getOutsetFrame();

    @Deprecated
    RectProto getOutsets();

    @Deprecated
    RectProto getOverscanFrame();

    @Deprecated
    RectProto getOverscanInsets();

    @Deprecated
    RectProto getParentFrame();

    boolean getPendingSeamlessRotation();

    boolean getRemoveOnExit();

    boolean getRemoved();

    int getRequestedHeight();

    int getRequestedWidth();

    @Deprecated
    RectProto getStableInsets();

    int getStackId();

    RectProto getSurfaceInsets();

    RectProto getSurfacePosition();

    int getSystemUiVisibility();

    int getViewVisibility();

    @Deprecated
    RectProto getVisibleFrame();

    @Deprecated
    RectProto getVisibleInsets();

    WindowContainerProto getWindowContainer();

    WindowFramesProto getWindowFrames();

    boolean hasAnimatingExit();

    boolean hasAnimator();

    boolean hasAttributes();

    @Deprecated
    boolean hasContainingFrame();

    @Deprecated
    boolean hasContentFrame();

    @Deprecated
    boolean hasContentInsets();

    @Deprecated
    boolean hasCutout();

    @Deprecated
    boolean hasDecorFrame();

    boolean hasDestroying();

    @Deprecated
    boolean hasDisplayFrame();

    boolean hasDisplayId();

    boolean hasFinishedSeamlessRotationFrame();

    boolean hasForceSeamlessRotation();

    @Deprecated
    boolean hasFrame();

    boolean hasGivenContentInsets();

    boolean hasHasSurface();

    boolean hasIdentifier();

    boolean hasIsOnScreen();

    boolean hasIsReadyForDisplay();

    boolean hasIsVisible();

    @Deprecated
    boolean hasOutsetFrame();

    @Deprecated
    boolean hasOutsets();

    @Deprecated
    boolean hasOverscanFrame();

    @Deprecated
    boolean hasOverscanInsets();

    @Deprecated
    boolean hasParentFrame();

    boolean hasPendingSeamlessRotation();

    boolean hasRemoveOnExit();

    boolean hasRemoved();

    boolean hasRequestedHeight();

    boolean hasRequestedWidth();

    @Deprecated
    boolean hasStableInsets();

    boolean hasStackId();

    boolean hasSurfaceInsets();

    boolean hasSurfacePosition();

    boolean hasSystemUiVisibility();

    boolean hasViewVisibility();

    @Deprecated
    boolean hasVisibleFrame();

    @Deprecated
    boolean hasVisibleInsets();

    boolean hasWindowContainer();

    boolean hasWindowFrames();
}
