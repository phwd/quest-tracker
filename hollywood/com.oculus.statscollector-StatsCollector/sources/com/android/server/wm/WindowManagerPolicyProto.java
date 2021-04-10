package com.android.server.wm;

import android.content.ActivityInfoProto;
import android.view.SurfaceProto;
import com.android.server.wm.BarControllerProto;
import com.android.server.wm.IdentifierProto;
import com.android.server.wm.KeyguardServiceDelegateProto;
import com.android.server.wm.WindowOrientationListenerProto;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;

public final class WindowManagerPolicyProto extends GeneratedMessageLite<WindowManagerPolicyProto, Builder> implements WindowManagerPolicyProtoOrBuilder {
    private static final WindowManagerPolicyProto DEFAULT_INSTANCE = new WindowManagerPolicyProto();
    public static final int FOCUSED_APP_TOKEN_FIELD_NUMBER = 8;
    public static final int FOCUSED_WINDOW_FIELD_NUMBER = 9;
    public static final int FORCE_STATUS_BAR_FIELD_NUMBER = 15;
    public static final int FORCE_STATUS_BAR_FROM_KEYGUARD_FIELD_NUMBER = 16;
    public static final int KEYGUARD_DELEGATE_FIELD_NUMBER = 20;
    public static final int KEYGUARD_DRAW_COMPLETE_FIELD_NUMBER = 6;
    public static final int KEYGUARD_OCCLUDED_CHANGED_FIELD_NUMBER = 13;
    public static final int KEYGUARD_OCCLUDED_FIELD_NUMBER = 12;
    public static final int KEYGUARD_OCCLUDED_PENDING_FIELD_NUMBER = 14;
    public static final int LAST_SYSTEM_UI_FLAGS_FIELD_NUMBER = 1;
    public static final int NAVIGATION_BAR_FIELD_NUMBER = 18;
    public static final int ORIENTATION_FIELD_NUMBER = 4;
    public static final int ORIENTATION_LISTENER_FIELD_NUMBER = 19;
    private static volatile Parser<WindowManagerPolicyProto> PARSER = null;
    public static final int ROTATION_FIELD_NUMBER = 3;
    public static final int ROTATION_MODE_FIELD_NUMBER = 2;
    public static final int SCREEN_ON_FULLY_FIELD_NUMBER = 5;
    public static final int STATUS_BAR_FIELD_NUMBER = 17;
    public static final int TOP_FULLSCREEN_OPAQUE_OR_DIMMING_WINDOW_FIELD_NUMBER = 11;
    public static final int TOP_FULLSCREEN_OPAQUE_WINDOW_FIELD_NUMBER = 10;
    public static final int WINDOW_MANAGER_DRAW_COMPLETE_FIELD_NUMBER = 7;
    private int bitField0_;
    private String focusedAppToken_ = "";
    private IdentifierProto focusedWindow_;
    private boolean forceStatusBarFromKeyguard_ = false;
    private boolean forceStatusBar_ = false;
    private KeyguardServiceDelegateProto keyguardDelegate_;
    private boolean keyguardDrawComplete_ = false;
    private boolean keyguardOccludedChanged_ = false;
    private boolean keyguardOccludedPending_ = false;
    private boolean keyguardOccluded_ = false;
    private int lastSystemUiFlags_ = 0;
    private BarControllerProto navigationBar_;
    private WindowOrientationListenerProto orientationListener_;
    private int orientation_ = -2;
    private int rotationMode_ = 0;
    private int rotation_ = 0;
    private boolean screenOnFully_ = false;
    private BarControllerProto statusBar_;
    private IdentifierProto topFullscreenOpaqueOrDimmingWindow_;
    private IdentifierProto topFullscreenOpaqueWindow_;
    private boolean windowManagerDrawComplete_ = false;

    private WindowManagerPolicyProto() {
    }

    public enum UserRotationMode implements Internal.EnumLite {
        USER_ROTATION_FREE(0),
        USER_ROTATION_LOCKED(1);
        
        public static final int USER_ROTATION_FREE_VALUE = 0;
        public static final int USER_ROTATION_LOCKED_VALUE = 1;
        private static final Internal.EnumLiteMap<UserRotationMode> internalValueMap = new Internal.EnumLiteMap<UserRotationMode>() {
            /* class com.android.server.wm.WindowManagerPolicyProto.UserRotationMode.AnonymousClass1 */

            @Override // com.google.protobuf.Internal.EnumLiteMap
            public UserRotationMode findValueByNumber(int number) {
                return UserRotationMode.forNumber(number);
            }
        };
        private final int value;

        @Override // com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        @Deprecated
        public static UserRotationMode valueOf(int value2) {
            return forNumber(value2);
        }

        public static UserRotationMode forNumber(int value2) {
            if (value2 == 0) {
                return USER_ROTATION_FREE;
            }
            if (value2 != 1) {
                return null;
            }
            return USER_ROTATION_LOCKED;
        }

        public static Internal.EnumLiteMap<UserRotationMode> internalGetValueMap() {
            return internalValueMap;
        }

        private UserRotationMode(int value2) {
            this.value = value2;
        }
    }

    @Override // com.android.server.wm.WindowManagerPolicyProtoOrBuilder
    @Deprecated
    public boolean hasLastSystemUiFlags() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // com.android.server.wm.WindowManagerPolicyProtoOrBuilder
    @Deprecated
    public int getLastSystemUiFlags() {
        return this.lastSystemUiFlags_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setLastSystemUiFlags(int value) {
        this.bitField0_ |= 1;
        this.lastSystemUiFlags_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearLastSystemUiFlags() {
        this.bitField0_ &= -2;
        this.lastSystemUiFlags_ = 0;
    }

    @Override // com.android.server.wm.WindowManagerPolicyProtoOrBuilder
    public boolean hasRotationMode() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // com.android.server.wm.WindowManagerPolicyProtoOrBuilder
    public UserRotationMode getRotationMode() {
        UserRotationMode result = UserRotationMode.forNumber(this.rotationMode_);
        return result == null ? UserRotationMode.USER_ROTATION_FREE : result;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setRotationMode(UserRotationMode value) {
        if (value != null) {
            this.bitField0_ |= 2;
            this.rotationMode_ = value.getNumber();
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearRotationMode() {
        this.bitField0_ &= -3;
        this.rotationMode_ = 0;
    }

    @Override // com.android.server.wm.WindowManagerPolicyProtoOrBuilder
    public boolean hasRotation() {
        return (this.bitField0_ & 4) == 4;
    }

    @Override // com.android.server.wm.WindowManagerPolicyProtoOrBuilder
    public SurfaceProto.Rotation getRotation() {
        SurfaceProto.Rotation result = SurfaceProto.Rotation.forNumber(this.rotation_);
        return result == null ? SurfaceProto.Rotation.ROTATION_0 : result;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setRotation(SurfaceProto.Rotation value) {
        if (value != null) {
            this.bitField0_ |= 4;
            this.rotation_ = value.getNumber();
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearRotation() {
        this.bitField0_ &= -5;
        this.rotation_ = 0;
    }

    @Override // com.android.server.wm.WindowManagerPolicyProtoOrBuilder
    public boolean hasOrientation() {
        return (this.bitField0_ & 8) == 8;
    }

    @Override // com.android.server.wm.WindowManagerPolicyProtoOrBuilder
    public ActivityInfoProto.ScreenOrientation getOrientation() {
        ActivityInfoProto.ScreenOrientation result = ActivityInfoProto.ScreenOrientation.forNumber(this.orientation_);
        return result == null ? ActivityInfoProto.ScreenOrientation.SCREEN_ORIENTATION_UNSET : result;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setOrientation(ActivityInfoProto.ScreenOrientation value) {
        if (value != null) {
            this.bitField0_ |= 8;
            this.orientation_ = value.getNumber();
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearOrientation() {
        this.bitField0_ &= -9;
        this.orientation_ = -2;
    }

    @Override // com.android.server.wm.WindowManagerPolicyProtoOrBuilder
    public boolean hasScreenOnFully() {
        return (this.bitField0_ & 16) == 16;
    }

    @Override // com.android.server.wm.WindowManagerPolicyProtoOrBuilder
    public boolean getScreenOnFully() {
        return this.screenOnFully_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setScreenOnFully(boolean value) {
        this.bitField0_ |= 16;
        this.screenOnFully_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearScreenOnFully() {
        this.bitField0_ &= -17;
        this.screenOnFully_ = false;
    }

    @Override // com.android.server.wm.WindowManagerPolicyProtoOrBuilder
    public boolean hasKeyguardDrawComplete() {
        return (this.bitField0_ & 32) == 32;
    }

    @Override // com.android.server.wm.WindowManagerPolicyProtoOrBuilder
    public boolean getKeyguardDrawComplete() {
        return this.keyguardDrawComplete_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setKeyguardDrawComplete(boolean value) {
        this.bitField0_ |= 32;
        this.keyguardDrawComplete_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearKeyguardDrawComplete() {
        this.bitField0_ &= -33;
        this.keyguardDrawComplete_ = false;
    }

    @Override // com.android.server.wm.WindowManagerPolicyProtoOrBuilder
    public boolean hasWindowManagerDrawComplete() {
        return (this.bitField0_ & 64) == 64;
    }

    @Override // com.android.server.wm.WindowManagerPolicyProtoOrBuilder
    public boolean getWindowManagerDrawComplete() {
        return this.windowManagerDrawComplete_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setWindowManagerDrawComplete(boolean value) {
        this.bitField0_ |= 64;
        this.windowManagerDrawComplete_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearWindowManagerDrawComplete() {
        this.bitField0_ &= -65;
        this.windowManagerDrawComplete_ = false;
    }

    @Override // com.android.server.wm.WindowManagerPolicyProtoOrBuilder
    @Deprecated
    public boolean hasFocusedAppToken() {
        return (this.bitField0_ & 128) == 128;
    }

    @Override // com.android.server.wm.WindowManagerPolicyProtoOrBuilder
    @Deprecated
    public String getFocusedAppToken() {
        return this.focusedAppToken_;
    }

    @Override // com.android.server.wm.WindowManagerPolicyProtoOrBuilder
    @Deprecated
    public ByteString getFocusedAppTokenBytes() {
        return ByteString.copyFromUtf8(this.focusedAppToken_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setFocusedAppToken(String value) {
        if (value != null) {
            this.bitField0_ |= 128;
            this.focusedAppToken_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearFocusedAppToken() {
        this.bitField0_ &= -129;
        this.focusedAppToken_ = getDefaultInstance().getFocusedAppToken();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setFocusedAppTokenBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 128;
            this.focusedAppToken_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // com.android.server.wm.WindowManagerPolicyProtoOrBuilder
    @Deprecated
    public boolean hasFocusedWindow() {
        return (this.bitField0_ & 256) == 256;
    }

    @Override // com.android.server.wm.WindowManagerPolicyProtoOrBuilder
    @Deprecated
    public IdentifierProto getFocusedWindow() {
        IdentifierProto identifierProto = this.focusedWindow_;
        return identifierProto == null ? IdentifierProto.getDefaultInstance() : identifierProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setFocusedWindow(IdentifierProto value) {
        if (value != null) {
            this.focusedWindow_ = value;
            this.bitField0_ |= 256;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setFocusedWindow(IdentifierProto.Builder builderForValue) {
        this.focusedWindow_ = (IdentifierProto) builderForValue.build();
        this.bitField0_ |= 256;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeFocusedWindow(IdentifierProto value) {
        IdentifierProto identifierProto = this.focusedWindow_;
        if (identifierProto == null || identifierProto == IdentifierProto.getDefaultInstance()) {
            this.focusedWindow_ = value;
        } else {
            this.focusedWindow_ = (IdentifierProto) ((IdentifierProto.Builder) IdentifierProto.newBuilder(this.focusedWindow_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 256;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearFocusedWindow() {
        this.focusedWindow_ = null;
        this.bitField0_ &= -257;
    }

    @Override // com.android.server.wm.WindowManagerPolicyProtoOrBuilder
    @Deprecated
    public boolean hasTopFullscreenOpaqueWindow() {
        return (this.bitField0_ & 512) == 512;
    }

    @Override // com.android.server.wm.WindowManagerPolicyProtoOrBuilder
    @Deprecated
    public IdentifierProto getTopFullscreenOpaqueWindow() {
        IdentifierProto identifierProto = this.topFullscreenOpaqueWindow_;
        return identifierProto == null ? IdentifierProto.getDefaultInstance() : identifierProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setTopFullscreenOpaqueWindow(IdentifierProto value) {
        if (value != null) {
            this.topFullscreenOpaqueWindow_ = value;
            this.bitField0_ |= 512;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setTopFullscreenOpaqueWindow(IdentifierProto.Builder builderForValue) {
        this.topFullscreenOpaqueWindow_ = (IdentifierProto) builderForValue.build();
        this.bitField0_ |= 512;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeTopFullscreenOpaqueWindow(IdentifierProto value) {
        IdentifierProto identifierProto = this.topFullscreenOpaqueWindow_;
        if (identifierProto == null || identifierProto == IdentifierProto.getDefaultInstance()) {
            this.topFullscreenOpaqueWindow_ = value;
        } else {
            this.topFullscreenOpaqueWindow_ = (IdentifierProto) ((IdentifierProto.Builder) IdentifierProto.newBuilder(this.topFullscreenOpaqueWindow_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 512;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearTopFullscreenOpaqueWindow() {
        this.topFullscreenOpaqueWindow_ = null;
        this.bitField0_ &= -513;
    }

    @Override // com.android.server.wm.WindowManagerPolicyProtoOrBuilder
    @Deprecated
    public boolean hasTopFullscreenOpaqueOrDimmingWindow() {
        return (this.bitField0_ & 1024) == 1024;
    }

    @Override // com.android.server.wm.WindowManagerPolicyProtoOrBuilder
    @Deprecated
    public IdentifierProto getTopFullscreenOpaqueOrDimmingWindow() {
        IdentifierProto identifierProto = this.topFullscreenOpaqueOrDimmingWindow_;
        return identifierProto == null ? IdentifierProto.getDefaultInstance() : identifierProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setTopFullscreenOpaqueOrDimmingWindow(IdentifierProto value) {
        if (value != null) {
            this.topFullscreenOpaqueOrDimmingWindow_ = value;
            this.bitField0_ |= 1024;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setTopFullscreenOpaqueOrDimmingWindow(IdentifierProto.Builder builderForValue) {
        this.topFullscreenOpaqueOrDimmingWindow_ = (IdentifierProto) builderForValue.build();
        this.bitField0_ |= 1024;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeTopFullscreenOpaqueOrDimmingWindow(IdentifierProto value) {
        IdentifierProto identifierProto = this.topFullscreenOpaqueOrDimmingWindow_;
        if (identifierProto == null || identifierProto == IdentifierProto.getDefaultInstance()) {
            this.topFullscreenOpaqueOrDimmingWindow_ = value;
        } else {
            this.topFullscreenOpaqueOrDimmingWindow_ = (IdentifierProto) ((IdentifierProto.Builder) IdentifierProto.newBuilder(this.topFullscreenOpaqueOrDimmingWindow_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 1024;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearTopFullscreenOpaqueOrDimmingWindow() {
        this.topFullscreenOpaqueOrDimmingWindow_ = null;
        this.bitField0_ &= -1025;
    }

    @Override // com.android.server.wm.WindowManagerPolicyProtoOrBuilder
    public boolean hasKeyguardOccluded() {
        return (this.bitField0_ & 2048) == 2048;
    }

    @Override // com.android.server.wm.WindowManagerPolicyProtoOrBuilder
    public boolean getKeyguardOccluded() {
        return this.keyguardOccluded_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setKeyguardOccluded(boolean value) {
        this.bitField0_ |= 2048;
        this.keyguardOccluded_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearKeyguardOccluded() {
        this.bitField0_ &= -2049;
        this.keyguardOccluded_ = false;
    }

    @Override // com.android.server.wm.WindowManagerPolicyProtoOrBuilder
    public boolean hasKeyguardOccludedChanged() {
        return (this.bitField0_ & 4096) == 4096;
    }

    @Override // com.android.server.wm.WindowManagerPolicyProtoOrBuilder
    public boolean getKeyguardOccludedChanged() {
        return this.keyguardOccludedChanged_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setKeyguardOccludedChanged(boolean value) {
        this.bitField0_ |= 4096;
        this.keyguardOccludedChanged_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearKeyguardOccludedChanged() {
        this.bitField0_ &= -4097;
        this.keyguardOccludedChanged_ = false;
    }

    @Override // com.android.server.wm.WindowManagerPolicyProtoOrBuilder
    public boolean hasKeyguardOccludedPending() {
        return (this.bitField0_ & 8192) == 8192;
    }

    @Override // com.android.server.wm.WindowManagerPolicyProtoOrBuilder
    public boolean getKeyguardOccludedPending() {
        return this.keyguardOccludedPending_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setKeyguardOccludedPending(boolean value) {
        this.bitField0_ |= 8192;
        this.keyguardOccludedPending_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearKeyguardOccludedPending() {
        this.bitField0_ &= -8193;
        this.keyguardOccludedPending_ = false;
    }

    @Override // com.android.server.wm.WindowManagerPolicyProtoOrBuilder
    @Deprecated
    public boolean hasForceStatusBar() {
        return (this.bitField0_ & 16384) == 16384;
    }

    @Override // com.android.server.wm.WindowManagerPolicyProtoOrBuilder
    @Deprecated
    public boolean getForceStatusBar() {
        return this.forceStatusBar_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setForceStatusBar(boolean value) {
        this.bitField0_ |= 16384;
        this.forceStatusBar_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearForceStatusBar() {
        this.bitField0_ &= -16385;
        this.forceStatusBar_ = false;
    }

    @Override // com.android.server.wm.WindowManagerPolicyProtoOrBuilder
    @Deprecated
    public boolean hasForceStatusBarFromKeyguard() {
        return (this.bitField0_ & 32768) == 32768;
    }

    @Override // com.android.server.wm.WindowManagerPolicyProtoOrBuilder
    @Deprecated
    public boolean getForceStatusBarFromKeyguard() {
        return this.forceStatusBarFromKeyguard_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setForceStatusBarFromKeyguard(boolean value) {
        this.bitField0_ |= 32768;
        this.forceStatusBarFromKeyguard_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearForceStatusBarFromKeyguard() {
        this.bitField0_ &= -32769;
        this.forceStatusBarFromKeyguard_ = false;
    }

    @Override // com.android.server.wm.WindowManagerPolicyProtoOrBuilder
    @Deprecated
    public boolean hasStatusBar() {
        return (this.bitField0_ & 65536) == 65536;
    }

    @Override // com.android.server.wm.WindowManagerPolicyProtoOrBuilder
    @Deprecated
    public BarControllerProto getStatusBar() {
        BarControllerProto barControllerProto = this.statusBar_;
        return barControllerProto == null ? BarControllerProto.getDefaultInstance() : barControllerProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setStatusBar(BarControllerProto value) {
        if (value != null) {
            this.statusBar_ = value;
            this.bitField0_ |= 65536;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setStatusBar(BarControllerProto.Builder builderForValue) {
        this.statusBar_ = (BarControllerProto) builderForValue.build();
        this.bitField0_ |= 65536;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeStatusBar(BarControllerProto value) {
        BarControllerProto barControllerProto = this.statusBar_;
        if (barControllerProto == null || barControllerProto == BarControllerProto.getDefaultInstance()) {
            this.statusBar_ = value;
        } else {
            this.statusBar_ = (BarControllerProto) ((BarControllerProto.Builder) BarControllerProto.newBuilder(this.statusBar_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 65536;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearStatusBar() {
        this.statusBar_ = null;
        this.bitField0_ &= -65537;
    }

    @Override // com.android.server.wm.WindowManagerPolicyProtoOrBuilder
    @Deprecated
    public boolean hasNavigationBar() {
        return (this.bitField0_ & 131072) == 131072;
    }

    @Override // com.android.server.wm.WindowManagerPolicyProtoOrBuilder
    @Deprecated
    public BarControllerProto getNavigationBar() {
        BarControllerProto barControllerProto = this.navigationBar_;
        return barControllerProto == null ? BarControllerProto.getDefaultInstance() : barControllerProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setNavigationBar(BarControllerProto value) {
        if (value != null) {
            this.navigationBar_ = value;
            this.bitField0_ |= 131072;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setNavigationBar(BarControllerProto.Builder builderForValue) {
        this.navigationBar_ = (BarControllerProto) builderForValue.build();
        this.bitField0_ |= 131072;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeNavigationBar(BarControllerProto value) {
        BarControllerProto barControllerProto = this.navigationBar_;
        if (barControllerProto == null || barControllerProto == BarControllerProto.getDefaultInstance()) {
            this.navigationBar_ = value;
        } else {
            this.navigationBar_ = (BarControllerProto) ((BarControllerProto.Builder) BarControllerProto.newBuilder(this.navigationBar_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 131072;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearNavigationBar() {
        this.navigationBar_ = null;
        this.bitField0_ &= -131073;
    }

    @Override // com.android.server.wm.WindowManagerPolicyProtoOrBuilder
    @Deprecated
    public boolean hasOrientationListener() {
        return (this.bitField0_ & 262144) == 262144;
    }

    @Override // com.android.server.wm.WindowManagerPolicyProtoOrBuilder
    @Deprecated
    public WindowOrientationListenerProto getOrientationListener() {
        WindowOrientationListenerProto windowOrientationListenerProto = this.orientationListener_;
        return windowOrientationListenerProto == null ? WindowOrientationListenerProto.getDefaultInstance() : windowOrientationListenerProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setOrientationListener(WindowOrientationListenerProto value) {
        if (value != null) {
            this.orientationListener_ = value;
            this.bitField0_ |= 262144;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setOrientationListener(WindowOrientationListenerProto.Builder builderForValue) {
        this.orientationListener_ = (WindowOrientationListenerProto) builderForValue.build();
        this.bitField0_ |= 262144;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeOrientationListener(WindowOrientationListenerProto value) {
        WindowOrientationListenerProto windowOrientationListenerProto = this.orientationListener_;
        if (windowOrientationListenerProto == null || windowOrientationListenerProto == WindowOrientationListenerProto.getDefaultInstance()) {
            this.orientationListener_ = value;
        } else {
            this.orientationListener_ = (WindowOrientationListenerProto) ((WindowOrientationListenerProto.Builder) WindowOrientationListenerProto.newBuilder(this.orientationListener_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 262144;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearOrientationListener() {
        this.orientationListener_ = null;
        this.bitField0_ &= -262145;
    }

    @Override // com.android.server.wm.WindowManagerPolicyProtoOrBuilder
    public boolean hasKeyguardDelegate() {
        return (this.bitField0_ & 524288) == 524288;
    }

    @Override // com.android.server.wm.WindowManagerPolicyProtoOrBuilder
    public KeyguardServiceDelegateProto getKeyguardDelegate() {
        KeyguardServiceDelegateProto keyguardServiceDelegateProto = this.keyguardDelegate_;
        return keyguardServiceDelegateProto == null ? KeyguardServiceDelegateProto.getDefaultInstance() : keyguardServiceDelegateProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setKeyguardDelegate(KeyguardServiceDelegateProto value) {
        if (value != null) {
            this.keyguardDelegate_ = value;
            this.bitField0_ |= 524288;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setKeyguardDelegate(KeyguardServiceDelegateProto.Builder builderForValue) {
        this.keyguardDelegate_ = (KeyguardServiceDelegateProto) builderForValue.build();
        this.bitField0_ |= 524288;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeKeyguardDelegate(KeyguardServiceDelegateProto value) {
        KeyguardServiceDelegateProto keyguardServiceDelegateProto = this.keyguardDelegate_;
        if (keyguardServiceDelegateProto == null || keyguardServiceDelegateProto == KeyguardServiceDelegateProto.getDefaultInstance()) {
            this.keyguardDelegate_ = value;
        } else {
            this.keyguardDelegate_ = (KeyguardServiceDelegateProto) ((KeyguardServiceDelegateProto.Builder) KeyguardServiceDelegateProto.newBuilder(this.keyguardDelegate_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 524288;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearKeyguardDelegate() {
        this.keyguardDelegate_ = null;
        this.bitField0_ &= -524289;
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeInt32(1, this.lastSystemUiFlags_);
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeEnum(2, this.rotationMode_);
        }
        if ((this.bitField0_ & 4) == 4) {
            output.writeEnum(3, this.rotation_);
        }
        if ((this.bitField0_ & 8) == 8) {
            output.writeEnum(4, this.orientation_);
        }
        if ((this.bitField0_ & 16) == 16) {
            output.writeBool(5, this.screenOnFully_);
        }
        if ((this.bitField0_ & 32) == 32) {
            output.writeBool(6, this.keyguardDrawComplete_);
        }
        if ((this.bitField0_ & 64) == 64) {
            output.writeBool(7, this.windowManagerDrawComplete_);
        }
        if ((this.bitField0_ & 128) == 128) {
            output.writeString(8, getFocusedAppToken());
        }
        if ((this.bitField0_ & 256) == 256) {
            output.writeMessage(9, getFocusedWindow());
        }
        if ((this.bitField0_ & 512) == 512) {
            output.writeMessage(10, getTopFullscreenOpaqueWindow());
        }
        if ((this.bitField0_ & 1024) == 1024) {
            output.writeMessage(11, getTopFullscreenOpaqueOrDimmingWindow());
        }
        if ((this.bitField0_ & 2048) == 2048) {
            output.writeBool(12, this.keyguardOccluded_);
        }
        if ((this.bitField0_ & 4096) == 4096) {
            output.writeBool(13, this.keyguardOccludedChanged_);
        }
        if ((this.bitField0_ & 8192) == 8192) {
            output.writeBool(14, this.keyguardOccludedPending_);
        }
        if ((this.bitField0_ & 16384) == 16384) {
            output.writeBool(15, this.forceStatusBar_);
        }
        if ((this.bitField0_ & 32768) == 32768) {
            output.writeBool(16, this.forceStatusBarFromKeyguard_);
        }
        if ((this.bitField0_ & 65536) == 65536) {
            output.writeMessage(17, getStatusBar());
        }
        if ((this.bitField0_ & 131072) == 131072) {
            output.writeMessage(18, getNavigationBar());
        }
        if ((this.bitField0_ & 262144) == 262144) {
            output.writeMessage(19, getOrientationListener());
        }
        if ((this.bitField0_ & 524288) == 524288) {
            output.writeMessage(20, getKeyguardDelegate());
        }
        this.unknownFields.writeTo(output);
    }

    @Override // com.google.protobuf.MessageLite
    public int getSerializedSize() {
        int size = this.memoizedSerializedSize;
        if (size != -1) {
            return size;
        }
        int size2 = 0;
        if ((this.bitField0_ & 1) == 1) {
            size2 = 0 + CodedOutputStream.computeInt32Size(1, this.lastSystemUiFlags_);
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeEnumSize(2, this.rotationMode_);
        }
        if ((this.bitField0_ & 4) == 4) {
            size2 += CodedOutputStream.computeEnumSize(3, this.rotation_);
        }
        if ((this.bitField0_ & 8) == 8) {
            size2 += CodedOutputStream.computeEnumSize(4, this.orientation_);
        }
        if ((this.bitField0_ & 16) == 16) {
            size2 += CodedOutputStream.computeBoolSize(5, this.screenOnFully_);
        }
        if ((this.bitField0_ & 32) == 32) {
            size2 += CodedOutputStream.computeBoolSize(6, this.keyguardDrawComplete_);
        }
        if ((this.bitField0_ & 64) == 64) {
            size2 += CodedOutputStream.computeBoolSize(7, this.windowManagerDrawComplete_);
        }
        if ((this.bitField0_ & 128) == 128) {
            size2 += CodedOutputStream.computeStringSize(8, getFocusedAppToken());
        }
        if ((this.bitField0_ & 256) == 256) {
            size2 += CodedOutputStream.computeMessageSize(9, getFocusedWindow());
        }
        if ((this.bitField0_ & 512) == 512) {
            size2 += CodedOutputStream.computeMessageSize(10, getTopFullscreenOpaqueWindow());
        }
        if ((this.bitField0_ & 1024) == 1024) {
            size2 += CodedOutputStream.computeMessageSize(11, getTopFullscreenOpaqueOrDimmingWindow());
        }
        if ((this.bitField0_ & 2048) == 2048) {
            size2 += CodedOutputStream.computeBoolSize(12, this.keyguardOccluded_);
        }
        if ((this.bitField0_ & 4096) == 4096) {
            size2 += CodedOutputStream.computeBoolSize(13, this.keyguardOccludedChanged_);
        }
        if ((this.bitField0_ & 8192) == 8192) {
            size2 += CodedOutputStream.computeBoolSize(14, this.keyguardOccludedPending_);
        }
        if ((this.bitField0_ & 16384) == 16384) {
            size2 += CodedOutputStream.computeBoolSize(15, this.forceStatusBar_);
        }
        if ((this.bitField0_ & 32768) == 32768) {
            size2 += CodedOutputStream.computeBoolSize(16, this.forceStatusBarFromKeyguard_);
        }
        if ((this.bitField0_ & 65536) == 65536) {
            size2 += CodedOutputStream.computeMessageSize(17, getStatusBar());
        }
        if ((this.bitField0_ & 131072) == 131072) {
            size2 += CodedOutputStream.computeMessageSize(18, getNavigationBar());
        }
        if ((this.bitField0_ & 262144) == 262144) {
            size2 += CodedOutputStream.computeMessageSize(19, getOrientationListener());
        }
        if ((this.bitField0_ & 524288) == 524288) {
            size2 += CodedOutputStream.computeMessageSize(20, getKeyguardDelegate());
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static WindowManagerPolicyProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (WindowManagerPolicyProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static WindowManagerPolicyProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (WindowManagerPolicyProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static WindowManagerPolicyProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (WindowManagerPolicyProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static WindowManagerPolicyProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (WindowManagerPolicyProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static WindowManagerPolicyProto parseFrom(InputStream input) throws IOException {
        return (WindowManagerPolicyProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static WindowManagerPolicyProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (WindowManagerPolicyProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static WindowManagerPolicyProto parseDelimitedFrom(InputStream input) throws IOException {
        return (WindowManagerPolicyProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static WindowManagerPolicyProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (WindowManagerPolicyProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static WindowManagerPolicyProto parseFrom(CodedInputStream input) throws IOException {
        return (WindowManagerPolicyProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static WindowManagerPolicyProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (WindowManagerPolicyProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(WindowManagerPolicyProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<WindowManagerPolicyProto, Builder> implements WindowManagerPolicyProtoOrBuilder {
        private Builder() {
            super(WindowManagerPolicyProto.DEFAULT_INSTANCE);
        }

        @Override // com.android.server.wm.WindowManagerPolicyProtoOrBuilder
        @Deprecated
        public boolean hasLastSystemUiFlags() {
            return ((WindowManagerPolicyProto) this.instance).hasLastSystemUiFlags();
        }

        @Override // com.android.server.wm.WindowManagerPolicyProtoOrBuilder
        @Deprecated
        public int getLastSystemUiFlags() {
            return ((WindowManagerPolicyProto) this.instance).getLastSystemUiFlags();
        }

        @Deprecated
        public Builder setLastSystemUiFlags(int value) {
            copyOnWrite();
            ((WindowManagerPolicyProto) this.instance).setLastSystemUiFlags(value);
            return this;
        }

        @Deprecated
        public Builder clearLastSystemUiFlags() {
            copyOnWrite();
            ((WindowManagerPolicyProto) this.instance).clearLastSystemUiFlags();
            return this;
        }

        @Override // com.android.server.wm.WindowManagerPolicyProtoOrBuilder
        public boolean hasRotationMode() {
            return ((WindowManagerPolicyProto) this.instance).hasRotationMode();
        }

        @Override // com.android.server.wm.WindowManagerPolicyProtoOrBuilder
        public UserRotationMode getRotationMode() {
            return ((WindowManagerPolicyProto) this.instance).getRotationMode();
        }

        public Builder setRotationMode(UserRotationMode value) {
            copyOnWrite();
            ((WindowManagerPolicyProto) this.instance).setRotationMode(value);
            return this;
        }

        public Builder clearRotationMode() {
            copyOnWrite();
            ((WindowManagerPolicyProto) this.instance).clearRotationMode();
            return this;
        }

        @Override // com.android.server.wm.WindowManagerPolicyProtoOrBuilder
        public boolean hasRotation() {
            return ((WindowManagerPolicyProto) this.instance).hasRotation();
        }

        @Override // com.android.server.wm.WindowManagerPolicyProtoOrBuilder
        public SurfaceProto.Rotation getRotation() {
            return ((WindowManagerPolicyProto) this.instance).getRotation();
        }

        public Builder setRotation(SurfaceProto.Rotation value) {
            copyOnWrite();
            ((WindowManagerPolicyProto) this.instance).setRotation(value);
            return this;
        }

        public Builder clearRotation() {
            copyOnWrite();
            ((WindowManagerPolicyProto) this.instance).clearRotation();
            return this;
        }

        @Override // com.android.server.wm.WindowManagerPolicyProtoOrBuilder
        public boolean hasOrientation() {
            return ((WindowManagerPolicyProto) this.instance).hasOrientation();
        }

        @Override // com.android.server.wm.WindowManagerPolicyProtoOrBuilder
        public ActivityInfoProto.ScreenOrientation getOrientation() {
            return ((WindowManagerPolicyProto) this.instance).getOrientation();
        }

        public Builder setOrientation(ActivityInfoProto.ScreenOrientation value) {
            copyOnWrite();
            ((WindowManagerPolicyProto) this.instance).setOrientation(value);
            return this;
        }

        public Builder clearOrientation() {
            copyOnWrite();
            ((WindowManagerPolicyProto) this.instance).clearOrientation();
            return this;
        }

        @Override // com.android.server.wm.WindowManagerPolicyProtoOrBuilder
        public boolean hasScreenOnFully() {
            return ((WindowManagerPolicyProto) this.instance).hasScreenOnFully();
        }

        @Override // com.android.server.wm.WindowManagerPolicyProtoOrBuilder
        public boolean getScreenOnFully() {
            return ((WindowManagerPolicyProto) this.instance).getScreenOnFully();
        }

        public Builder setScreenOnFully(boolean value) {
            copyOnWrite();
            ((WindowManagerPolicyProto) this.instance).setScreenOnFully(value);
            return this;
        }

        public Builder clearScreenOnFully() {
            copyOnWrite();
            ((WindowManagerPolicyProto) this.instance).clearScreenOnFully();
            return this;
        }

        @Override // com.android.server.wm.WindowManagerPolicyProtoOrBuilder
        public boolean hasKeyguardDrawComplete() {
            return ((WindowManagerPolicyProto) this.instance).hasKeyguardDrawComplete();
        }

        @Override // com.android.server.wm.WindowManagerPolicyProtoOrBuilder
        public boolean getKeyguardDrawComplete() {
            return ((WindowManagerPolicyProto) this.instance).getKeyguardDrawComplete();
        }

        public Builder setKeyguardDrawComplete(boolean value) {
            copyOnWrite();
            ((WindowManagerPolicyProto) this.instance).setKeyguardDrawComplete(value);
            return this;
        }

        public Builder clearKeyguardDrawComplete() {
            copyOnWrite();
            ((WindowManagerPolicyProto) this.instance).clearKeyguardDrawComplete();
            return this;
        }

        @Override // com.android.server.wm.WindowManagerPolicyProtoOrBuilder
        public boolean hasWindowManagerDrawComplete() {
            return ((WindowManagerPolicyProto) this.instance).hasWindowManagerDrawComplete();
        }

        @Override // com.android.server.wm.WindowManagerPolicyProtoOrBuilder
        public boolean getWindowManagerDrawComplete() {
            return ((WindowManagerPolicyProto) this.instance).getWindowManagerDrawComplete();
        }

        public Builder setWindowManagerDrawComplete(boolean value) {
            copyOnWrite();
            ((WindowManagerPolicyProto) this.instance).setWindowManagerDrawComplete(value);
            return this;
        }

        public Builder clearWindowManagerDrawComplete() {
            copyOnWrite();
            ((WindowManagerPolicyProto) this.instance).clearWindowManagerDrawComplete();
            return this;
        }

        @Override // com.android.server.wm.WindowManagerPolicyProtoOrBuilder
        @Deprecated
        public boolean hasFocusedAppToken() {
            return ((WindowManagerPolicyProto) this.instance).hasFocusedAppToken();
        }

        @Override // com.android.server.wm.WindowManagerPolicyProtoOrBuilder
        @Deprecated
        public String getFocusedAppToken() {
            return ((WindowManagerPolicyProto) this.instance).getFocusedAppToken();
        }

        @Override // com.android.server.wm.WindowManagerPolicyProtoOrBuilder
        @Deprecated
        public ByteString getFocusedAppTokenBytes() {
            return ((WindowManagerPolicyProto) this.instance).getFocusedAppTokenBytes();
        }

        @Deprecated
        public Builder setFocusedAppToken(String value) {
            copyOnWrite();
            ((WindowManagerPolicyProto) this.instance).setFocusedAppToken(value);
            return this;
        }

        @Deprecated
        public Builder clearFocusedAppToken() {
            copyOnWrite();
            ((WindowManagerPolicyProto) this.instance).clearFocusedAppToken();
            return this;
        }

        @Deprecated
        public Builder setFocusedAppTokenBytes(ByteString value) {
            copyOnWrite();
            ((WindowManagerPolicyProto) this.instance).setFocusedAppTokenBytes(value);
            return this;
        }

        @Override // com.android.server.wm.WindowManagerPolicyProtoOrBuilder
        @Deprecated
        public boolean hasFocusedWindow() {
            return ((WindowManagerPolicyProto) this.instance).hasFocusedWindow();
        }

        @Override // com.android.server.wm.WindowManagerPolicyProtoOrBuilder
        @Deprecated
        public IdentifierProto getFocusedWindow() {
            return ((WindowManagerPolicyProto) this.instance).getFocusedWindow();
        }

        @Deprecated
        public Builder setFocusedWindow(IdentifierProto value) {
            copyOnWrite();
            ((WindowManagerPolicyProto) this.instance).setFocusedWindow((WindowManagerPolicyProto) value);
            return this;
        }

        @Deprecated
        public Builder setFocusedWindow(IdentifierProto.Builder builderForValue) {
            copyOnWrite();
            ((WindowManagerPolicyProto) this.instance).setFocusedWindow((WindowManagerPolicyProto) builderForValue);
            return this;
        }

        @Deprecated
        public Builder mergeFocusedWindow(IdentifierProto value) {
            copyOnWrite();
            ((WindowManagerPolicyProto) this.instance).mergeFocusedWindow(value);
            return this;
        }

        @Deprecated
        public Builder clearFocusedWindow() {
            copyOnWrite();
            ((WindowManagerPolicyProto) this.instance).clearFocusedWindow();
            return this;
        }

        @Override // com.android.server.wm.WindowManagerPolicyProtoOrBuilder
        @Deprecated
        public boolean hasTopFullscreenOpaqueWindow() {
            return ((WindowManagerPolicyProto) this.instance).hasTopFullscreenOpaqueWindow();
        }

        @Override // com.android.server.wm.WindowManagerPolicyProtoOrBuilder
        @Deprecated
        public IdentifierProto getTopFullscreenOpaqueWindow() {
            return ((WindowManagerPolicyProto) this.instance).getTopFullscreenOpaqueWindow();
        }

        @Deprecated
        public Builder setTopFullscreenOpaqueWindow(IdentifierProto value) {
            copyOnWrite();
            ((WindowManagerPolicyProto) this.instance).setTopFullscreenOpaqueWindow((WindowManagerPolicyProto) value);
            return this;
        }

        @Deprecated
        public Builder setTopFullscreenOpaqueWindow(IdentifierProto.Builder builderForValue) {
            copyOnWrite();
            ((WindowManagerPolicyProto) this.instance).setTopFullscreenOpaqueWindow((WindowManagerPolicyProto) builderForValue);
            return this;
        }

        @Deprecated
        public Builder mergeTopFullscreenOpaqueWindow(IdentifierProto value) {
            copyOnWrite();
            ((WindowManagerPolicyProto) this.instance).mergeTopFullscreenOpaqueWindow(value);
            return this;
        }

        @Deprecated
        public Builder clearTopFullscreenOpaqueWindow() {
            copyOnWrite();
            ((WindowManagerPolicyProto) this.instance).clearTopFullscreenOpaqueWindow();
            return this;
        }

        @Override // com.android.server.wm.WindowManagerPolicyProtoOrBuilder
        @Deprecated
        public boolean hasTopFullscreenOpaqueOrDimmingWindow() {
            return ((WindowManagerPolicyProto) this.instance).hasTopFullscreenOpaqueOrDimmingWindow();
        }

        @Override // com.android.server.wm.WindowManagerPolicyProtoOrBuilder
        @Deprecated
        public IdentifierProto getTopFullscreenOpaqueOrDimmingWindow() {
            return ((WindowManagerPolicyProto) this.instance).getTopFullscreenOpaqueOrDimmingWindow();
        }

        @Deprecated
        public Builder setTopFullscreenOpaqueOrDimmingWindow(IdentifierProto value) {
            copyOnWrite();
            ((WindowManagerPolicyProto) this.instance).setTopFullscreenOpaqueOrDimmingWindow((WindowManagerPolicyProto) value);
            return this;
        }

        @Deprecated
        public Builder setTopFullscreenOpaqueOrDimmingWindow(IdentifierProto.Builder builderForValue) {
            copyOnWrite();
            ((WindowManagerPolicyProto) this.instance).setTopFullscreenOpaqueOrDimmingWindow((WindowManagerPolicyProto) builderForValue);
            return this;
        }

        @Deprecated
        public Builder mergeTopFullscreenOpaqueOrDimmingWindow(IdentifierProto value) {
            copyOnWrite();
            ((WindowManagerPolicyProto) this.instance).mergeTopFullscreenOpaqueOrDimmingWindow(value);
            return this;
        }

        @Deprecated
        public Builder clearTopFullscreenOpaqueOrDimmingWindow() {
            copyOnWrite();
            ((WindowManagerPolicyProto) this.instance).clearTopFullscreenOpaqueOrDimmingWindow();
            return this;
        }

        @Override // com.android.server.wm.WindowManagerPolicyProtoOrBuilder
        public boolean hasKeyguardOccluded() {
            return ((WindowManagerPolicyProto) this.instance).hasKeyguardOccluded();
        }

        @Override // com.android.server.wm.WindowManagerPolicyProtoOrBuilder
        public boolean getKeyguardOccluded() {
            return ((WindowManagerPolicyProto) this.instance).getKeyguardOccluded();
        }

        public Builder setKeyguardOccluded(boolean value) {
            copyOnWrite();
            ((WindowManagerPolicyProto) this.instance).setKeyguardOccluded(value);
            return this;
        }

        public Builder clearKeyguardOccluded() {
            copyOnWrite();
            ((WindowManagerPolicyProto) this.instance).clearKeyguardOccluded();
            return this;
        }

        @Override // com.android.server.wm.WindowManagerPolicyProtoOrBuilder
        public boolean hasKeyguardOccludedChanged() {
            return ((WindowManagerPolicyProto) this.instance).hasKeyguardOccludedChanged();
        }

        @Override // com.android.server.wm.WindowManagerPolicyProtoOrBuilder
        public boolean getKeyguardOccludedChanged() {
            return ((WindowManagerPolicyProto) this.instance).getKeyguardOccludedChanged();
        }

        public Builder setKeyguardOccludedChanged(boolean value) {
            copyOnWrite();
            ((WindowManagerPolicyProto) this.instance).setKeyguardOccludedChanged(value);
            return this;
        }

        public Builder clearKeyguardOccludedChanged() {
            copyOnWrite();
            ((WindowManagerPolicyProto) this.instance).clearKeyguardOccludedChanged();
            return this;
        }

        @Override // com.android.server.wm.WindowManagerPolicyProtoOrBuilder
        public boolean hasKeyguardOccludedPending() {
            return ((WindowManagerPolicyProto) this.instance).hasKeyguardOccludedPending();
        }

        @Override // com.android.server.wm.WindowManagerPolicyProtoOrBuilder
        public boolean getKeyguardOccludedPending() {
            return ((WindowManagerPolicyProto) this.instance).getKeyguardOccludedPending();
        }

        public Builder setKeyguardOccludedPending(boolean value) {
            copyOnWrite();
            ((WindowManagerPolicyProto) this.instance).setKeyguardOccludedPending(value);
            return this;
        }

        public Builder clearKeyguardOccludedPending() {
            copyOnWrite();
            ((WindowManagerPolicyProto) this.instance).clearKeyguardOccludedPending();
            return this;
        }

        @Override // com.android.server.wm.WindowManagerPolicyProtoOrBuilder
        @Deprecated
        public boolean hasForceStatusBar() {
            return ((WindowManagerPolicyProto) this.instance).hasForceStatusBar();
        }

        @Override // com.android.server.wm.WindowManagerPolicyProtoOrBuilder
        @Deprecated
        public boolean getForceStatusBar() {
            return ((WindowManagerPolicyProto) this.instance).getForceStatusBar();
        }

        @Deprecated
        public Builder setForceStatusBar(boolean value) {
            copyOnWrite();
            ((WindowManagerPolicyProto) this.instance).setForceStatusBar(value);
            return this;
        }

        @Deprecated
        public Builder clearForceStatusBar() {
            copyOnWrite();
            ((WindowManagerPolicyProto) this.instance).clearForceStatusBar();
            return this;
        }

        @Override // com.android.server.wm.WindowManagerPolicyProtoOrBuilder
        @Deprecated
        public boolean hasForceStatusBarFromKeyguard() {
            return ((WindowManagerPolicyProto) this.instance).hasForceStatusBarFromKeyguard();
        }

        @Override // com.android.server.wm.WindowManagerPolicyProtoOrBuilder
        @Deprecated
        public boolean getForceStatusBarFromKeyguard() {
            return ((WindowManagerPolicyProto) this.instance).getForceStatusBarFromKeyguard();
        }

        @Deprecated
        public Builder setForceStatusBarFromKeyguard(boolean value) {
            copyOnWrite();
            ((WindowManagerPolicyProto) this.instance).setForceStatusBarFromKeyguard(value);
            return this;
        }

        @Deprecated
        public Builder clearForceStatusBarFromKeyguard() {
            copyOnWrite();
            ((WindowManagerPolicyProto) this.instance).clearForceStatusBarFromKeyguard();
            return this;
        }

        @Override // com.android.server.wm.WindowManagerPolicyProtoOrBuilder
        @Deprecated
        public boolean hasStatusBar() {
            return ((WindowManagerPolicyProto) this.instance).hasStatusBar();
        }

        @Override // com.android.server.wm.WindowManagerPolicyProtoOrBuilder
        @Deprecated
        public BarControllerProto getStatusBar() {
            return ((WindowManagerPolicyProto) this.instance).getStatusBar();
        }

        @Deprecated
        public Builder setStatusBar(BarControllerProto value) {
            copyOnWrite();
            ((WindowManagerPolicyProto) this.instance).setStatusBar((WindowManagerPolicyProto) value);
            return this;
        }

        @Deprecated
        public Builder setStatusBar(BarControllerProto.Builder builderForValue) {
            copyOnWrite();
            ((WindowManagerPolicyProto) this.instance).setStatusBar((WindowManagerPolicyProto) builderForValue);
            return this;
        }

        @Deprecated
        public Builder mergeStatusBar(BarControllerProto value) {
            copyOnWrite();
            ((WindowManagerPolicyProto) this.instance).mergeStatusBar(value);
            return this;
        }

        @Deprecated
        public Builder clearStatusBar() {
            copyOnWrite();
            ((WindowManagerPolicyProto) this.instance).clearStatusBar();
            return this;
        }

        @Override // com.android.server.wm.WindowManagerPolicyProtoOrBuilder
        @Deprecated
        public boolean hasNavigationBar() {
            return ((WindowManagerPolicyProto) this.instance).hasNavigationBar();
        }

        @Override // com.android.server.wm.WindowManagerPolicyProtoOrBuilder
        @Deprecated
        public BarControllerProto getNavigationBar() {
            return ((WindowManagerPolicyProto) this.instance).getNavigationBar();
        }

        @Deprecated
        public Builder setNavigationBar(BarControllerProto value) {
            copyOnWrite();
            ((WindowManagerPolicyProto) this.instance).setNavigationBar((WindowManagerPolicyProto) value);
            return this;
        }

        @Deprecated
        public Builder setNavigationBar(BarControllerProto.Builder builderForValue) {
            copyOnWrite();
            ((WindowManagerPolicyProto) this.instance).setNavigationBar((WindowManagerPolicyProto) builderForValue);
            return this;
        }

        @Deprecated
        public Builder mergeNavigationBar(BarControllerProto value) {
            copyOnWrite();
            ((WindowManagerPolicyProto) this.instance).mergeNavigationBar(value);
            return this;
        }

        @Deprecated
        public Builder clearNavigationBar() {
            copyOnWrite();
            ((WindowManagerPolicyProto) this.instance).clearNavigationBar();
            return this;
        }

        @Override // com.android.server.wm.WindowManagerPolicyProtoOrBuilder
        @Deprecated
        public boolean hasOrientationListener() {
            return ((WindowManagerPolicyProto) this.instance).hasOrientationListener();
        }

        @Override // com.android.server.wm.WindowManagerPolicyProtoOrBuilder
        @Deprecated
        public WindowOrientationListenerProto getOrientationListener() {
            return ((WindowManagerPolicyProto) this.instance).getOrientationListener();
        }

        @Deprecated
        public Builder setOrientationListener(WindowOrientationListenerProto value) {
            copyOnWrite();
            ((WindowManagerPolicyProto) this.instance).setOrientationListener((WindowManagerPolicyProto) value);
            return this;
        }

        @Deprecated
        public Builder setOrientationListener(WindowOrientationListenerProto.Builder builderForValue) {
            copyOnWrite();
            ((WindowManagerPolicyProto) this.instance).setOrientationListener((WindowManagerPolicyProto) builderForValue);
            return this;
        }

        @Deprecated
        public Builder mergeOrientationListener(WindowOrientationListenerProto value) {
            copyOnWrite();
            ((WindowManagerPolicyProto) this.instance).mergeOrientationListener(value);
            return this;
        }

        @Deprecated
        public Builder clearOrientationListener() {
            copyOnWrite();
            ((WindowManagerPolicyProto) this.instance).clearOrientationListener();
            return this;
        }

        @Override // com.android.server.wm.WindowManagerPolicyProtoOrBuilder
        public boolean hasKeyguardDelegate() {
            return ((WindowManagerPolicyProto) this.instance).hasKeyguardDelegate();
        }

        @Override // com.android.server.wm.WindowManagerPolicyProtoOrBuilder
        public KeyguardServiceDelegateProto getKeyguardDelegate() {
            return ((WindowManagerPolicyProto) this.instance).getKeyguardDelegate();
        }

        public Builder setKeyguardDelegate(KeyguardServiceDelegateProto value) {
            copyOnWrite();
            ((WindowManagerPolicyProto) this.instance).setKeyguardDelegate((WindowManagerPolicyProto) value);
            return this;
        }

        public Builder setKeyguardDelegate(KeyguardServiceDelegateProto.Builder builderForValue) {
            copyOnWrite();
            ((WindowManagerPolicyProto) this.instance).setKeyguardDelegate((WindowManagerPolicyProto) builderForValue);
            return this;
        }

        public Builder mergeKeyguardDelegate(KeyguardServiceDelegateProto value) {
            copyOnWrite();
            ((WindowManagerPolicyProto) this.instance).mergeKeyguardDelegate(value);
            return this;
        }

        public Builder clearKeyguardDelegate() {
            copyOnWrite();
            ((WindowManagerPolicyProto) this.instance).clearKeyguardDelegate();
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new WindowManagerPolicyProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                WindowManagerPolicyProto other = (WindowManagerPolicyProto) arg1;
                this.lastSystemUiFlags_ = visitor.visitInt(hasLastSystemUiFlags(), this.lastSystemUiFlags_, other.hasLastSystemUiFlags(), other.lastSystemUiFlags_);
                this.rotationMode_ = visitor.visitInt(hasRotationMode(), this.rotationMode_, other.hasRotationMode(), other.rotationMode_);
                this.rotation_ = visitor.visitInt(hasRotation(), this.rotation_, other.hasRotation(), other.rotation_);
                this.orientation_ = visitor.visitInt(hasOrientation(), this.orientation_, other.hasOrientation(), other.orientation_);
                this.screenOnFully_ = visitor.visitBoolean(hasScreenOnFully(), this.screenOnFully_, other.hasScreenOnFully(), other.screenOnFully_);
                this.keyguardDrawComplete_ = visitor.visitBoolean(hasKeyguardDrawComplete(), this.keyguardDrawComplete_, other.hasKeyguardDrawComplete(), other.keyguardDrawComplete_);
                this.windowManagerDrawComplete_ = visitor.visitBoolean(hasWindowManagerDrawComplete(), this.windowManagerDrawComplete_, other.hasWindowManagerDrawComplete(), other.windowManagerDrawComplete_);
                this.focusedAppToken_ = visitor.visitString(hasFocusedAppToken(), this.focusedAppToken_, other.hasFocusedAppToken(), other.focusedAppToken_);
                this.focusedWindow_ = (IdentifierProto) visitor.visitMessage(this.focusedWindow_, other.focusedWindow_);
                this.topFullscreenOpaqueWindow_ = (IdentifierProto) visitor.visitMessage(this.topFullscreenOpaqueWindow_, other.topFullscreenOpaqueWindow_);
                this.topFullscreenOpaqueOrDimmingWindow_ = (IdentifierProto) visitor.visitMessage(this.topFullscreenOpaqueOrDimmingWindow_, other.topFullscreenOpaqueOrDimmingWindow_);
                this.keyguardOccluded_ = visitor.visitBoolean(hasKeyguardOccluded(), this.keyguardOccluded_, other.hasKeyguardOccluded(), other.keyguardOccluded_);
                this.keyguardOccludedChanged_ = visitor.visitBoolean(hasKeyguardOccludedChanged(), this.keyguardOccludedChanged_, other.hasKeyguardOccludedChanged(), other.keyguardOccludedChanged_);
                this.keyguardOccludedPending_ = visitor.visitBoolean(hasKeyguardOccludedPending(), this.keyguardOccludedPending_, other.hasKeyguardOccludedPending(), other.keyguardOccludedPending_);
                this.forceStatusBar_ = visitor.visitBoolean(hasForceStatusBar(), this.forceStatusBar_, other.hasForceStatusBar(), other.forceStatusBar_);
                this.forceStatusBarFromKeyguard_ = visitor.visitBoolean(hasForceStatusBarFromKeyguard(), this.forceStatusBarFromKeyguard_, other.hasForceStatusBarFromKeyguard(), other.forceStatusBarFromKeyguard_);
                this.statusBar_ = (BarControllerProto) visitor.visitMessage(this.statusBar_, other.statusBar_);
                this.navigationBar_ = (BarControllerProto) visitor.visitMessage(this.navigationBar_, other.navigationBar_);
                this.orientationListener_ = (WindowOrientationListenerProto) visitor.visitMessage(this.orientationListener_, other.orientationListener_);
                this.keyguardDelegate_ = (KeyguardServiceDelegateProto) visitor.visitMessage(this.keyguardDelegate_, other.keyguardDelegate_);
                if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE) {
                    this.bitField0_ |= other.bitField0_;
                }
                return this;
            case MERGE_FROM_STREAM:
                CodedInputStream input = (CodedInputStream) arg0;
                ExtensionRegistryLite extensionRegistry = (ExtensionRegistryLite) arg1;
                boolean done = false;
                while (!done) {
                    try {
                        int tag = input.readTag();
                        switch (tag) {
                            case 0:
                                done = true;
                                break;
                            case 8:
                                this.bitField0_ |= 1;
                                this.lastSystemUiFlags_ = input.readInt32();
                                break;
                            case 16:
                                int rawValue = input.readEnum();
                                if (UserRotationMode.forNumber(rawValue) != null) {
                                    this.bitField0_ = 2 | this.bitField0_;
                                    this.rotationMode_ = rawValue;
                                    break;
                                } else {
                                    super.mergeVarintField(2, rawValue);
                                    break;
                                }
                            case 24:
                                int rawValue2 = input.readEnum();
                                if (SurfaceProto.Rotation.forNumber(rawValue2) != null) {
                                    this.bitField0_ = 4 | this.bitField0_;
                                    this.rotation_ = rawValue2;
                                    break;
                                } else {
                                    super.mergeVarintField(3, rawValue2);
                                    break;
                                }
                            case 32:
                                int rawValue3 = input.readEnum();
                                if (ActivityInfoProto.ScreenOrientation.forNumber(rawValue3) != null) {
                                    this.bitField0_ |= 8;
                                    this.orientation_ = rawValue3;
                                    break;
                                } else {
                                    super.mergeVarintField(4, rawValue3);
                                    break;
                                }
                            case 40:
                                this.bitField0_ |= 16;
                                this.screenOnFully_ = input.readBool();
                                break;
                            case 48:
                                this.bitField0_ |= 32;
                                this.keyguardDrawComplete_ = input.readBool();
                                break;
                            case 56:
                                this.bitField0_ |= 64;
                                this.windowManagerDrawComplete_ = input.readBool();
                                break;
                            case 66:
                                String s = input.readString();
                                this.bitField0_ |= 128;
                                this.focusedAppToken_ = s;
                                break;
                            case 74:
                                IdentifierProto.Builder subBuilder = null;
                                if ((this.bitField0_ & 256) == 256) {
                                    subBuilder = (IdentifierProto.Builder) this.focusedWindow_.toBuilder();
                                }
                                this.focusedWindow_ = (IdentifierProto) input.readMessage(IdentifierProto.parser(), extensionRegistry);
                                if (subBuilder != null) {
                                    subBuilder.mergeFrom((GeneratedMessageLite) this.focusedWindow_);
                                    this.focusedWindow_ = (IdentifierProto) subBuilder.buildPartial();
                                }
                                this.bitField0_ |= 256;
                                break;
                            case 82:
                                IdentifierProto.Builder subBuilder2 = null;
                                if ((this.bitField0_ & 512) == 512) {
                                    subBuilder2 = (IdentifierProto.Builder) this.topFullscreenOpaqueWindow_.toBuilder();
                                }
                                this.topFullscreenOpaqueWindow_ = (IdentifierProto) input.readMessage(IdentifierProto.parser(), extensionRegistry);
                                if (subBuilder2 != null) {
                                    subBuilder2.mergeFrom((GeneratedMessageLite) this.topFullscreenOpaqueWindow_);
                                    this.topFullscreenOpaqueWindow_ = (IdentifierProto) subBuilder2.buildPartial();
                                }
                                this.bitField0_ |= 512;
                                break;
                            case 90:
                                IdentifierProto.Builder subBuilder3 = null;
                                if ((this.bitField0_ & 1024) == 1024) {
                                    subBuilder3 = (IdentifierProto.Builder) this.topFullscreenOpaqueOrDimmingWindow_.toBuilder();
                                }
                                this.topFullscreenOpaqueOrDimmingWindow_ = (IdentifierProto) input.readMessage(IdentifierProto.parser(), extensionRegistry);
                                if (subBuilder3 != null) {
                                    subBuilder3.mergeFrom((GeneratedMessageLite) this.topFullscreenOpaqueOrDimmingWindow_);
                                    this.topFullscreenOpaqueOrDimmingWindow_ = (IdentifierProto) subBuilder3.buildPartial();
                                }
                                this.bitField0_ |= 1024;
                                break;
                            case 96:
                                this.bitField0_ |= 2048;
                                this.keyguardOccluded_ = input.readBool();
                                break;
                            case 104:
                                this.bitField0_ |= 4096;
                                this.keyguardOccludedChanged_ = input.readBool();
                                break;
                            case 112:
                                this.bitField0_ |= 8192;
                                this.keyguardOccludedPending_ = input.readBool();
                                break;
                            case 120:
                                this.bitField0_ |= 16384;
                                this.forceStatusBar_ = input.readBool();
                                break;
                            case 128:
                                this.bitField0_ |= 32768;
                                this.forceStatusBarFromKeyguard_ = input.readBool();
                                break;
                            case 138:
                                BarControllerProto.Builder subBuilder4 = null;
                                if ((this.bitField0_ & 65536) == 65536) {
                                    subBuilder4 = (BarControllerProto.Builder) this.statusBar_.toBuilder();
                                }
                                this.statusBar_ = (BarControllerProto) input.readMessage(BarControllerProto.parser(), extensionRegistry);
                                if (subBuilder4 != null) {
                                    subBuilder4.mergeFrom((GeneratedMessageLite) this.statusBar_);
                                    this.statusBar_ = (BarControllerProto) subBuilder4.buildPartial();
                                }
                                this.bitField0_ |= 65536;
                                break;
                            case 146:
                                BarControllerProto.Builder subBuilder5 = null;
                                if ((this.bitField0_ & 131072) == 131072) {
                                    subBuilder5 = (BarControllerProto.Builder) this.navigationBar_.toBuilder();
                                }
                                this.navigationBar_ = (BarControllerProto) input.readMessage(BarControllerProto.parser(), extensionRegistry);
                                if (subBuilder5 != null) {
                                    subBuilder5.mergeFrom((GeneratedMessageLite) this.navigationBar_);
                                    this.navigationBar_ = (BarControllerProto) subBuilder5.buildPartial();
                                }
                                this.bitField0_ |= 131072;
                                break;
                            case 154:
                                WindowOrientationListenerProto.Builder subBuilder6 = null;
                                if ((this.bitField0_ & 262144) == 262144) {
                                    subBuilder6 = (WindowOrientationListenerProto.Builder) this.orientationListener_.toBuilder();
                                }
                                this.orientationListener_ = (WindowOrientationListenerProto) input.readMessage(WindowOrientationListenerProto.parser(), extensionRegistry);
                                if (subBuilder6 != null) {
                                    subBuilder6.mergeFrom((GeneratedMessageLite) this.orientationListener_);
                                    this.orientationListener_ = (WindowOrientationListenerProto) subBuilder6.buildPartial();
                                }
                                this.bitField0_ |= 262144;
                                break;
                            case 162:
                                KeyguardServiceDelegateProto.Builder subBuilder7 = null;
                                if ((this.bitField0_ & 524288) == 524288) {
                                    subBuilder7 = (KeyguardServiceDelegateProto.Builder) this.keyguardDelegate_.toBuilder();
                                }
                                this.keyguardDelegate_ = (KeyguardServiceDelegateProto) input.readMessage(KeyguardServiceDelegateProto.parser(), extensionRegistry);
                                if (subBuilder7 != null) {
                                    subBuilder7.mergeFrom((GeneratedMessageLite) this.keyguardDelegate_);
                                    this.keyguardDelegate_ = (KeyguardServiceDelegateProto) subBuilder7.buildPartial();
                                }
                                this.bitField0_ |= 524288;
                                break;
                            default:
                                if (parseUnknownField(tag, input)) {
                                    break;
                                } else {
                                    done = true;
                                    break;
                                }
                        }
                    } catch (InvalidProtocolBufferException e) {
                        throw new RuntimeException(e.setUnfinishedMessage(this));
                    } catch (IOException e2) {
                        throw new RuntimeException(new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this));
                    }
                }
                break;
            case GET_DEFAULT_INSTANCE:
                break;
            case GET_PARSER:
                if (PARSER == null) {
                    synchronized (WindowManagerPolicyProto.class) {
                        if (PARSER == null) {
                            PARSER = new GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
                        }
                    }
                }
                return PARSER;
            default:
                throw new UnsupportedOperationException();
        }
        return DEFAULT_INSTANCE;
    }

    static {
        DEFAULT_INSTANCE.makeImmutable();
    }

    public static WindowManagerPolicyProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<WindowManagerPolicyProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
