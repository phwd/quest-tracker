package com.android.server.wm;

import android.content.ActivityInfoProto;
import android.view.SurfaceProto;
import com.android.server.wm.WindowManagerPolicyProto;
import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;

public interface WindowManagerPolicyProtoOrBuilder extends MessageLiteOrBuilder {
    @Deprecated
    String getFocusedAppToken();

    @Deprecated
    ByteString getFocusedAppTokenBytes();

    @Deprecated
    IdentifierProto getFocusedWindow();

    @Deprecated
    boolean getForceStatusBar();

    @Deprecated
    boolean getForceStatusBarFromKeyguard();

    KeyguardServiceDelegateProto getKeyguardDelegate();

    boolean getKeyguardDrawComplete();

    boolean getKeyguardOccluded();

    boolean getKeyguardOccludedChanged();

    boolean getKeyguardOccludedPending();

    @Deprecated
    int getLastSystemUiFlags();

    @Deprecated
    BarControllerProto getNavigationBar();

    ActivityInfoProto.ScreenOrientation getOrientation();

    @Deprecated
    WindowOrientationListenerProto getOrientationListener();

    SurfaceProto.Rotation getRotation();

    WindowManagerPolicyProto.UserRotationMode getRotationMode();

    boolean getScreenOnFully();

    @Deprecated
    BarControllerProto getStatusBar();

    @Deprecated
    IdentifierProto getTopFullscreenOpaqueOrDimmingWindow();

    @Deprecated
    IdentifierProto getTopFullscreenOpaqueWindow();

    boolean getWindowManagerDrawComplete();

    @Deprecated
    boolean hasFocusedAppToken();

    @Deprecated
    boolean hasFocusedWindow();

    @Deprecated
    boolean hasForceStatusBar();

    @Deprecated
    boolean hasForceStatusBarFromKeyguard();

    boolean hasKeyguardDelegate();

    boolean hasKeyguardDrawComplete();

    boolean hasKeyguardOccluded();

    boolean hasKeyguardOccludedChanged();

    boolean hasKeyguardOccludedPending();

    @Deprecated
    boolean hasLastSystemUiFlags();

    @Deprecated
    boolean hasNavigationBar();

    boolean hasOrientation();

    @Deprecated
    boolean hasOrientationListener();

    boolean hasRotation();

    boolean hasRotationMode();

    boolean hasScreenOnFully();

    @Deprecated
    boolean hasStatusBar();

    @Deprecated
    boolean hasTopFullscreenOpaqueOrDimmingWindow();

    @Deprecated
    boolean hasTopFullscreenOpaqueWindow();

    boolean hasWindowManagerDrawComplete();
}
