package com.android.server.wm;

import android.graphics.RectProto;
import android.telephony.DataConnectionPowerStateEnum;
import android.view.DisplayCutoutProto;
import android.view.WindowLayoutParamsProto;
import com.android.os.AtomsProto;
import com.android.server.wm.IdentifierProto;
import com.android.server.wm.WindowContainerProto;
import com.android.server.wm.WindowFramesProto;
import com.android.server.wm.WindowStateAnimatorProto;
import com.google.protobuf.AbstractMessageLite;
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
import java.util.Collections;
import java.util.List;

public final class WindowStateProto extends GeneratedMessageLite<WindowStateProto, Builder> implements WindowStateProtoOrBuilder {
    public static final int ANIMATING_EXIT_FIELD_NUMBER = 14;
    public static final int ANIMATOR_FIELD_NUMBER = 13;
    public static final int ATTRIBUTES_FIELD_NUMBER = 5;
    public static final int CHILD_WINDOWS_FIELD_NUMBER = 15;
    public static final int CONTAINING_FRAME_FIELD_NUMBER = 8;
    public static final int CONTENT_FRAME_FIELD_NUMBER = 10;
    public static final int CONTENT_INSETS_FIELD_NUMBER = 11;
    public static final int CUTOUT_FIELD_NUMBER = 33;
    public static final int DECOR_FRAME_FIELD_NUMBER = 27;
    private static final WindowStateProto DEFAULT_INSTANCE = new WindowStateProto();
    public static final int DESTROYING_FIELD_NUMBER = 35;
    public static final int DISPLAY_FRAME_FIELD_NUMBER = 24;
    public static final int DISPLAY_ID_FIELD_NUMBER = 3;
    public static final int FINISHED_SEAMLESS_ROTATION_FRAME_FIELD_NUMBER = 40;
    public static final int FORCE_SEAMLESS_ROTATION_FIELD_NUMBER = 42;
    public static final int FRAME_FIELD_NUMBER = 7;
    public static final int GIVEN_CONTENT_INSETS_FIELD_NUMBER = 6;
    public static final int HAS_SURFACE_FIELD_NUMBER = 22;
    public static final int IDENTIFIER_FIELD_NUMBER = 2;
    public static final int IS_ON_SCREEN_FIELD_NUMBER = 37;
    public static final int IS_READY_FOR_DISPLAY_FIELD_NUMBER = 23;
    public static final int IS_VISIBLE_FIELD_NUMBER = 38;
    public static final int OUTSETS_FIELD_NUMBER = 32;
    public static final int OUTSET_FRAME_FIELD_NUMBER = 28;
    public static final int OVERSCAN_FRAME_FIELD_NUMBER = 25;
    public static final int OVERSCAN_INSETS_FIELD_NUMBER = 29;
    public static final int PARENT_FRAME_FIELD_NUMBER = 9;
    private static volatile Parser<WindowStateProto> PARSER = null;
    public static final int PENDING_SEAMLESS_ROTATION_FIELD_NUMBER = 39;
    public static final int REMOVED_FIELD_NUMBER = 36;
    public static final int REMOVE_ON_EXIT_FIELD_NUMBER = 34;
    public static final int REQUESTED_HEIGHT_FIELD_NUMBER = 19;
    public static final int REQUESTED_WIDTH_FIELD_NUMBER = 18;
    public static final int STABLE_INSETS_FIELD_NUMBER = 31;
    public static final int STACK_ID_FIELD_NUMBER = 4;
    public static final int SURFACE_INSETS_FIELD_NUMBER = 12;
    public static final int SURFACE_POSITION_FIELD_NUMBER = 16;
    public static final int SYSTEM_UI_VISIBILITY_FIELD_NUMBER = 21;
    public static final int VIEW_VISIBILITY_FIELD_NUMBER = 20;
    public static final int VISIBLE_FRAME_FIELD_NUMBER = 26;
    public static final int VISIBLE_INSETS_FIELD_NUMBER = 30;
    public static final int WINDOW_CONTAINER_FIELD_NUMBER = 1;
    public static final int WINDOW_FRAMES_FIELD_NUMBER = 41;
    private boolean animatingExit_ = false;
    private WindowStateAnimatorProto animator_;
    private WindowLayoutParamsProto attributes_;
    private int bitField0_;
    private int bitField1_;
    private Internal.ProtobufList<WindowStateProto> childWindows_ = emptyProtobufList();
    private RectProto containingFrame_;
    private RectProto contentFrame_;
    private RectProto contentInsets_;
    private DisplayCutoutProto cutout_;
    private RectProto decorFrame_;
    private boolean destroying_ = false;
    private RectProto displayFrame_;
    private int displayId_ = 0;
    private long finishedSeamlessRotationFrame_ = 0;
    private boolean forceSeamlessRotation_ = false;
    private RectProto frame_;
    private RectProto givenContentInsets_;
    private boolean hasSurface_ = false;
    private IdentifierProto identifier_;
    private boolean isOnScreen_ = false;
    private boolean isReadyForDisplay_ = false;
    private boolean isVisible_ = false;
    private RectProto outsetFrame_;
    private RectProto outsets_;
    private RectProto overscanFrame_;
    private RectProto overscanInsets_;
    private RectProto parentFrame_;
    private boolean pendingSeamlessRotation_ = false;
    private boolean removeOnExit_ = false;
    private boolean removed_ = false;
    private int requestedHeight_ = 0;
    private int requestedWidth_ = 0;
    private RectProto stableInsets_;
    private int stackId_ = 0;
    private RectProto surfaceInsets_;
    private RectProto surfacePosition_;
    private int systemUiVisibility_ = 0;
    private int viewVisibility_ = 0;
    private RectProto visibleFrame_;
    private RectProto visibleInsets_;
    private WindowContainerProto windowContainer_;
    private WindowFramesProto windowFrames_;

    private WindowStateProto() {
    }

    @Override // com.android.server.wm.WindowStateProtoOrBuilder
    public boolean hasWindowContainer() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // com.android.server.wm.WindowStateProtoOrBuilder
    public WindowContainerProto getWindowContainer() {
        WindowContainerProto windowContainerProto = this.windowContainer_;
        return windowContainerProto == null ? WindowContainerProto.getDefaultInstance() : windowContainerProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setWindowContainer(WindowContainerProto value) {
        if (value != null) {
            this.windowContainer_ = value;
            this.bitField0_ |= 1;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setWindowContainer(WindowContainerProto.Builder builderForValue) {
        this.windowContainer_ = (WindowContainerProto) builderForValue.build();
        this.bitField0_ |= 1;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeWindowContainer(WindowContainerProto value) {
        WindowContainerProto windowContainerProto = this.windowContainer_;
        if (windowContainerProto == null || windowContainerProto == WindowContainerProto.getDefaultInstance()) {
            this.windowContainer_ = value;
        } else {
            this.windowContainer_ = (WindowContainerProto) ((WindowContainerProto.Builder) WindowContainerProto.newBuilder(this.windowContainer_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 1;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearWindowContainer() {
        this.windowContainer_ = null;
        this.bitField0_ &= -2;
    }

    @Override // com.android.server.wm.WindowStateProtoOrBuilder
    public boolean hasIdentifier() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // com.android.server.wm.WindowStateProtoOrBuilder
    public IdentifierProto getIdentifier() {
        IdentifierProto identifierProto = this.identifier_;
        return identifierProto == null ? IdentifierProto.getDefaultInstance() : identifierProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setIdentifier(IdentifierProto value) {
        if (value != null) {
            this.identifier_ = value;
            this.bitField0_ |= 2;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setIdentifier(IdentifierProto.Builder builderForValue) {
        this.identifier_ = (IdentifierProto) builderForValue.build();
        this.bitField0_ |= 2;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeIdentifier(IdentifierProto value) {
        IdentifierProto identifierProto = this.identifier_;
        if (identifierProto == null || identifierProto == IdentifierProto.getDefaultInstance()) {
            this.identifier_ = value;
        } else {
            this.identifier_ = (IdentifierProto) ((IdentifierProto.Builder) IdentifierProto.newBuilder(this.identifier_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 2;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearIdentifier() {
        this.identifier_ = null;
        this.bitField0_ &= -3;
    }

    @Override // com.android.server.wm.WindowStateProtoOrBuilder
    public boolean hasDisplayId() {
        return (this.bitField0_ & 4) == 4;
    }

    @Override // com.android.server.wm.WindowStateProtoOrBuilder
    public int getDisplayId() {
        return this.displayId_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setDisplayId(int value) {
        this.bitField0_ |= 4;
        this.displayId_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearDisplayId() {
        this.bitField0_ &= -5;
        this.displayId_ = 0;
    }

    @Override // com.android.server.wm.WindowStateProtoOrBuilder
    public boolean hasStackId() {
        return (this.bitField0_ & 8) == 8;
    }

    @Override // com.android.server.wm.WindowStateProtoOrBuilder
    public int getStackId() {
        return this.stackId_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setStackId(int value) {
        this.bitField0_ |= 8;
        this.stackId_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearStackId() {
        this.bitField0_ &= -9;
        this.stackId_ = 0;
    }

    @Override // com.android.server.wm.WindowStateProtoOrBuilder
    public boolean hasAttributes() {
        return (this.bitField0_ & 16) == 16;
    }

    @Override // com.android.server.wm.WindowStateProtoOrBuilder
    public WindowLayoutParamsProto getAttributes() {
        WindowLayoutParamsProto windowLayoutParamsProto = this.attributes_;
        return windowLayoutParamsProto == null ? WindowLayoutParamsProto.getDefaultInstance() : windowLayoutParamsProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setAttributes(WindowLayoutParamsProto value) {
        if (value != null) {
            this.attributes_ = value;
            this.bitField0_ |= 16;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setAttributes(WindowLayoutParamsProto.Builder builderForValue) {
        this.attributes_ = (WindowLayoutParamsProto) builderForValue.build();
        this.bitField0_ |= 16;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeAttributes(WindowLayoutParamsProto value) {
        WindowLayoutParamsProto windowLayoutParamsProto = this.attributes_;
        if (windowLayoutParamsProto == null || windowLayoutParamsProto == WindowLayoutParamsProto.getDefaultInstance()) {
            this.attributes_ = value;
        } else {
            this.attributes_ = (WindowLayoutParamsProto) ((WindowLayoutParamsProto.Builder) WindowLayoutParamsProto.newBuilder(this.attributes_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 16;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearAttributes() {
        this.attributes_ = null;
        this.bitField0_ &= -17;
    }

    @Override // com.android.server.wm.WindowStateProtoOrBuilder
    public boolean hasGivenContentInsets() {
        return (this.bitField0_ & 32) == 32;
    }

    @Override // com.android.server.wm.WindowStateProtoOrBuilder
    public RectProto getGivenContentInsets() {
        RectProto rectProto = this.givenContentInsets_;
        return rectProto == null ? RectProto.getDefaultInstance() : rectProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setGivenContentInsets(RectProto value) {
        if (value != null) {
            this.givenContentInsets_ = value;
            this.bitField0_ |= 32;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setGivenContentInsets(RectProto.Builder builderForValue) {
        this.givenContentInsets_ = (RectProto) builderForValue.build();
        this.bitField0_ |= 32;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeGivenContentInsets(RectProto value) {
        RectProto rectProto = this.givenContentInsets_;
        if (rectProto == null || rectProto == RectProto.getDefaultInstance()) {
            this.givenContentInsets_ = value;
        } else {
            this.givenContentInsets_ = (RectProto) ((RectProto.Builder) RectProto.newBuilder(this.givenContentInsets_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 32;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearGivenContentInsets() {
        this.givenContentInsets_ = null;
        this.bitField0_ &= -33;
    }

    @Override // com.android.server.wm.WindowStateProtoOrBuilder
    @Deprecated
    public boolean hasFrame() {
        return (this.bitField0_ & 64) == 64;
    }

    @Override // com.android.server.wm.WindowStateProtoOrBuilder
    @Deprecated
    public RectProto getFrame() {
        RectProto rectProto = this.frame_;
        return rectProto == null ? RectProto.getDefaultInstance() : rectProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setFrame(RectProto value) {
        if (value != null) {
            this.frame_ = value;
            this.bitField0_ |= 64;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setFrame(RectProto.Builder builderForValue) {
        this.frame_ = (RectProto) builderForValue.build();
        this.bitField0_ |= 64;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeFrame(RectProto value) {
        RectProto rectProto = this.frame_;
        if (rectProto == null || rectProto == RectProto.getDefaultInstance()) {
            this.frame_ = value;
        } else {
            this.frame_ = (RectProto) ((RectProto.Builder) RectProto.newBuilder(this.frame_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 64;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearFrame() {
        this.frame_ = null;
        this.bitField0_ &= -65;
    }

    @Override // com.android.server.wm.WindowStateProtoOrBuilder
    @Deprecated
    public boolean hasContainingFrame() {
        return (this.bitField0_ & 128) == 128;
    }

    @Override // com.android.server.wm.WindowStateProtoOrBuilder
    @Deprecated
    public RectProto getContainingFrame() {
        RectProto rectProto = this.containingFrame_;
        return rectProto == null ? RectProto.getDefaultInstance() : rectProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setContainingFrame(RectProto value) {
        if (value != null) {
            this.containingFrame_ = value;
            this.bitField0_ |= 128;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setContainingFrame(RectProto.Builder builderForValue) {
        this.containingFrame_ = (RectProto) builderForValue.build();
        this.bitField0_ |= 128;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeContainingFrame(RectProto value) {
        RectProto rectProto = this.containingFrame_;
        if (rectProto == null || rectProto == RectProto.getDefaultInstance()) {
            this.containingFrame_ = value;
        } else {
            this.containingFrame_ = (RectProto) ((RectProto.Builder) RectProto.newBuilder(this.containingFrame_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 128;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearContainingFrame() {
        this.containingFrame_ = null;
        this.bitField0_ &= -129;
    }

    @Override // com.android.server.wm.WindowStateProtoOrBuilder
    @Deprecated
    public boolean hasParentFrame() {
        return (this.bitField0_ & 256) == 256;
    }

    @Override // com.android.server.wm.WindowStateProtoOrBuilder
    @Deprecated
    public RectProto getParentFrame() {
        RectProto rectProto = this.parentFrame_;
        return rectProto == null ? RectProto.getDefaultInstance() : rectProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setParentFrame(RectProto value) {
        if (value != null) {
            this.parentFrame_ = value;
            this.bitField0_ |= 256;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setParentFrame(RectProto.Builder builderForValue) {
        this.parentFrame_ = (RectProto) builderForValue.build();
        this.bitField0_ |= 256;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeParentFrame(RectProto value) {
        RectProto rectProto = this.parentFrame_;
        if (rectProto == null || rectProto == RectProto.getDefaultInstance()) {
            this.parentFrame_ = value;
        } else {
            this.parentFrame_ = (RectProto) ((RectProto.Builder) RectProto.newBuilder(this.parentFrame_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 256;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearParentFrame() {
        this.parentFrame_ = null;
        this.bitField0_ &= -257;
    }

    @Override // com.android.server.wm.WindowStateProtoOrBuilder
    @Deprecated
    public boolean hasContentFrame() {
        return (this.bitField0_ & 512) == 512;
    }

    @Override // com.android.server.wm.WindowStateProtoOrBuilder
    @Deprecated
    public RectProto getContentFrame() {
        RectProto rectProto = this.contentFrame_;
        return rectProto == null ? RectProto.getDefaultInstance() : rectProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setContentFrame(RectProto value) {
        if (value != null) {
            this.contentFrame_ = value;
            this.bitField0_ |= 512;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setContentFrame(RectProto.Builder builderForValue) {
        this.contentFrame_ = (RectProto) builderForValue.build();
        this.bitField0_ |= 512;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeContentFrame(RectProto value) {
        RectProto rectProto = this.contentFrame_;
        if (rectProto == null || rectProto == RectProto.getDefaultInstance()) {
            this.contentFrame_ = value;
        } else {
            this.contentFrame_ = (RectProto) ((RectProto.Builder) RectProto.newBuilder(this.contentFrame_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 512;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearContentFrame() {
        this.contentFrame_ = null;
        this.bitField0_ &= -513;
    }

    @Override // com.android.server.wm.WindowStateProtoOrBuilder
    @Deprecated
    public boolean hasContentInsets() {
        return (this.bitField0_ & 1024) == 1024;
    }

    @Override // com.android.server.wm.WindowStateProtoOrBuilder
    @Deprecated
    public RectProto getContentInsets() {
        RectProto rectProto = this.contentInsets_;
        return rectProto == null ? RectProto.getDefaultInstance() : rectProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setContentInsets(RectProto value) {
        if (value != null) {
            this.contentInsets_ = value;
            this.bitField0_ |= 1024;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setContentInsets(RectProto.Builder builderForValue) {
        this.contentInsets_ = (RectProto) builderForValue.build();
        this.bitField0_ |= 1024;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeContentInsets(RectProto value) {
        RectProto rectProto = this.contentInsets_;
        if (rectProto == null || rectProto == RectProto.getDefaultInstance()) {
            this.contentInsets_ = value;
        } else {
            this.contentInsets_ = (RectProto) ((RectProto.Builder) RectProto.newBuilder(this.contentInsets_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 1024;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearContentInsets() {
        this.contentInsets_ = null;
        this.bitField0_ &= -1025;
    }

    @Override // com.android.server.wm.WindowStateProtoOrBuilder
    public boolean hasSurfaceInsets() {
        return (this.bitField0_ & 2048) == 2048;
    }

    @Override // com.android.server.wm.WindowStateProtoOrBuilder
    public RectProto getSurfaceInsets() {
        RectProto rectProto = this.surfaceInsets_;
        return rectProto == null ? RectProto.getDefaultInstance() : rectProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setSurfaceInsets(RectProto value) {
        if (value != null) {
            this.surfaceInsets_ = value;
            this.bitField0_ |= 2048;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setSurfaceInsets(RectProto.Builder builderForValue) {
        this.surfaceInsets_ = (RectProto) builderForValue.build();
        this.bitField0_ |= 2048;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeSurfaceInsets(RectProto value) {
        RectProto rectProto = this.surfaceInsets_;
        if (rectProto == null || rectProto == RectProto.getDefaultInstance()) {
            this.surfaceInsets_ = value;
        } else {
            this.surfaceInsets_ = (RectProto) ((RectProto.Builder) RectProto.newBuilder(this.surfaceInsets_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 2048;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearSurfaceInsets() {
        this.surfaceInsets_ = null;
        this.bitField0_ &= -2049;
    }

    @Override // com.android.server.wm.WindowStateProtoOrBuilder
    public boolean hasAnimator() {
        return (this.bitField0_ & 4096) == 4096;
    }

    @Override // com.android.server.wm.WindowStateProtoOrBuilder
    public WindowStateAnimatorProto getAnimator() {
        WindowStateAnimatorProto windowStateAnimatorProto = this.animator_;
        return windowStateAnimatorProto == null ? WindowStateAnimatorProto.getDefaultInstance() : windowStateAnimatorProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setAnimator(WindowStateAnimatorProto value) {
        if (value != null) {
            this.animator_ = value;
            this.bitField0_ |= 4096;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setAnimator(WindowStateAnimatorProto.Builder builderForValue) {
        this.animator_ = (WindowStateAnimatorProto) builderForValue.build();
        this.bitField0_ |= 4096;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeAnimator(WindowStateAnimatorProto value) {
        WindowStateAnimatorProto windowStateAnimatorProto = this.animator_;
        if (windowStateAnimatorProto == null || windowStateAnimatorProto == WindowStateAnimatorProto.getDefaultInstance()) {
            this.animator_ = value;
        } else {
            this.animator_ = (WindowStateAnimatorProto) ((WindowStateAnimatorProto.Builder) WindowStateAnimatorProto.newBuilder(this.animator_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 4096;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearAnimator() {
        this.animator_ = null;
        this.bitField0_ &= -4097;
    }

    @Override // com.android.server.wm.WindowStateProtoOrBuilder
    public boolean hasAnimatingExit() {
        return (this.bitField0_ & 8192) == 8192;
    }

    @Override // com.android.server.wm.WindowStateProtoOrBuilder
    public boolean getAnimatingExit() {
        return this.animatingExit_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setAnimatingExit(boolean value) {
        this.bitField0_ |= 8192;
        this.animatingExit_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearAnimatingExit() {
        this.bitField0_ &= -8193;
        this.animatingExit_ = false;
    }

    @Override // com.android.server.wm.WindowStateProtoOrBuilder
    public List<WindowStateProto> getChildWindowsList() {
        return this.childWindows_;
    }

    public List<? extends WindowStateProtoOrBuilder> getChildWindowsOrBuilderList() {
        return this.childWindows_;
    }

    @Override // com.android.server.wm.WindowStateProtoOrBuilder
    public int getChildWindowsCount() {
        return this.childWindows_.size();
    }

    @Override // com.android.server.wm.WindowStateProtoOrBuilder
    public WindowStateProto getChildWindows(int index) {
        return this.childWindows_.get(index);
    }

    public WindowStateProtoOrBuilder getChildWindowsOrBuilder(int index) {
        return this.childWindows_.get(index);
    }

    private void ensureChildWindowsIsMutable() {
        if (!this.childWindows_.isModifiable()) {
            this.childWindows_ = GeneratedMessageLite.mutableCopy(this.childWindows_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setChildWindows(int index, WindowStateProto value) {
        if (value != null) {
            ensureChildWindowsIsMutable();
            this.childWindows_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setChildWindows(int index, Builder builderForValue) {
        ensureChildWindowsIsMutable();
        this.childWindows_.set(index, (WindowStateProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addChildWindows(WindowStateProto value) {
        if (value != null) {
            ensureChildWindowsIsMutable();
            this.childWindows_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addChildWindows(int index, WindowStateProto value) {
        if (value != null) {
            ensureChildWindowsIsMutable();
            this.childWindows_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addChildWindows(Builder builderForValue) {
        ensureChildWindowsIsMutable();
        this.childWindows_.add((WindowStateProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addChildWindows(int index, Builder builderForValue) {
        ensureChildWindowsIsMutable();
        this.childWindows_.add(index, (WindowStateProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllChildWindows(Iterable<? extends WindowStateProto> values) {
        ensureChildWindowsIsMutable();
        AbstractMessageLite.addAll(values, this.childWindows_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearChildWindows() {
        this.childWindows_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeChildWindows(int index) {
        ensureChildWindowsIsMutable();
        this.childWindows_.remove(index);
    }

    @Override // com.android.server.wm.WindowStateProtoOrBuilder
    public boolean hasSurfacePosition() {
        return (this.bitField0_ & 16384) == 16384;
    }

    @Override // com.android.server.wm.WindowStateProtoOrBuilder
    public RectProto getSurfacePosition() {
        RectProto rectProto = this.surfacePosition_;
        return rectProto == null ? RectProto.getDefaultInstance() : rectProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setSurfacePosition(RectProto value) {
        if (value != null) {
            this.surfacePosition_ = value;
            this.bitField0_ |= 16384;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setSurfacePosition(RectProto.Builder builderForValue) {
        this.surfacePosition_ = (RectProto) builderForValue.build();
        this.bitField0_ |= 16384;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeSurfacePosition(RectProto value) {
        RectProto rectProto = this.surfacePosition_;
        if (rectProto == null || rectProto == RectProto.getDefaultInstance()) {
            this.surfacePosition_ = value;
        } else {
            this.surfacePosition_ = (RectProto) ((RectProto.Builder) RectProto.newBuilder(this.surfacePosition_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 16384;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearSurfacePosition() {
        this.surfacePosition_ = null;
        this.bitField0_ &= -16385;
    }

    @Override // com.android.server.wm.WindowStateProtoOrBuilder
    public boolean hasRequestedWidth() {
        return (this.bitField0_ & 32768) == 32768;
    }

    @Override // com.android.server.wm.WindowStateProtoOrBuilder
    public int getRequestedWidth() {
        return this.requestedWidth_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setRequestedWidth(int value) {
        this.bitField0_ |= 32768;
        this.requestedWidth_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearRequestedWidth() {
        this.bitField0_ &= -32769;
        this.requestedWidth_ = 0;
    }

    @Override // com.android.server.wm.WindowStateProtoOrBuilder
    public boolean hasRequestedHeight() {
        return (this.bitField0_ & 65536) == 65536;
    }

    @Override // com.android.server.wm.WindowStateProtoOrBuilder
    public int getRequestedHeight() {
        return this.requestedHeight_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setRequestedHeight(int value) {
        this.bitField0_ |= 65536;
        this.requestedHeight_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearRequestedHeight() {
        this.bitField0_ &= -65537;
        this.requestedHeight_ = 0;
    }

    @Override // com.android.server.wm.WindowStateProtoOrBuilder
    public boolean hasViewVisibility() {
        return (this.bitField0_ & 131072) == 131072;
    }

    @Override // com.android.server.wm.WindowStateProtoOrBuilder
    public int getViewVisibility() {
        return this.viewVisibility_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setViewVisibility(int value) {
        this.bitField0_ |= 131072;
        this.viewVisibility_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearViewVisibility() {
        this.bitField0_ &= -131073;
        this.viewVisibility_ = 0;
    }

    @Override // com.android.server.wm.WindowStateProtoOrBuilder
    public boolean hasSystemUiVisibility() {
        return (this.bitField0_ & 262144) == 262144;
    }

    @Override // com.android.server.wm.WindowStateProtoOrBuilder
    public int getSystemUiVisibility() {
        return this.systemUiVisibility_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setSystemUiVisibility(int value) {
        this.bitField0_ |= 262144;
        this.systemUiVisibility_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearSystemUiVisibility() {
        this.bitField0_ &= -262145;
        this.systemUiVisibility_ = 0;
    }

    @Override // com.android.server.wm.WindowStateProtoOrBuilder
    public boolean hasHasSurface() {
        return (this.bitField0_ & 524288) == 524288;
    }

    @Override // com.android.server.wm.WindowStateProtoOrBuilder
    public boolean getHasSurface() {
        return this.hasSurface_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setHasSurface(boolean value) {
        this.bitField0_ |= 524288;
        this.hasSurface_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearHasSurface() {
        this.bitField0_ &= -524289;
        this.hasSurface_ = false;
    }

    @Override // com.android.server.wm.WindowStateProtoOrBuilder
    public boolean hasIsReadyForDisplay() {
        return (this.bitField0_ & 1048576) == 1048576;
    }

    @Override // com.android.server.wm.WindowStateProtoOrBuilder
    public boolean getIsReadyForDisplay() {
        return this.isReadyForDisplay_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setIsReadyForDisplay(boolean value) {
        this.bitField0_ |= 1048576;
        this.isReadyForDisplay_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearIsReadyForDisplay() {
        this.bitField0_ &= -1048577;
        this.isReadyForDisplay_ = false;
    }

    @Override // com.android.server.wm.WindowStateProtoOrBuilder
    @Deprecated
    public boolean hasDisplayFrame() {
        return (this.bitField0_ & 2097152) == 2097152;
    }

    @Override // com.android.server.wm.WindowStateProtoOrBuilder
    @Deprecated
    public RectProto getDisplayFrame() {
        RectProto rectProto = this.displayFrame_;
        return rectProto == null ? RectProto.getDefaultInstance() : rectProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setDisplayFrame(RectProto value) {
        if (value != null) {
            this.displayFrame_ = value;
            this.bitField0_ |= 2097152;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setDisplayFrame(RectProto.Builder builderForValue) {
        this.displayFrame_ = (RectProto) builderForValue.build();
        this.bitField0_ |= 2097152;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeDisplayFrame(RectProto value) {
        RectProto rectProto = this.displayFrame_;
        if (rectProto == null || rectProto == RectProto.getDefaultInstance()) {
            this.displayFrame_ = value;
        } else {
            this.displayFrame_ = (RectProto) ((RectProto.Builder) RectProto.newBuilder(this.displayFrame_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 2097152;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearDisplayFrame() {
        this.displayFrame_ = null;
        this.bitField0_ &= -2097153;
    }

    @Override // com.android.server.wm.WindowStateProtoOrBuilder
    @Deprecated
    public boolean hasOverscanFrame() {
        return (this.bitField0_ & 4194304) == 4194304;
    }

    @Override // com.android.server.wm.WindowStateProtoOrBuilder
    @Deprecated
    public RectProto getOverscanFrame() {
        RectProto rectProto = this.overscanFrame_;
        return rectProto == null ? RectProto.getDefaultInstance() : rectProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setOverscanFrame(RectProto value) {
        if (value != null) {
            this.overscanFrame_ = value;
            this.bitField0_ |= 4194304;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setOverscanFrame(RectProto.Builder builderForValue) {
        this.overscanFrame_ = (RectProto) builderForValue.build();
        this.bitField0_ |= 4194304;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeOverscanFrame(RectProto value) {
        RectProto rectProto = this.overscanFrame_;
        if (rectProto == null || rectProto == RectProto.getDefaultInstance()) {
            this.overscanFrame_ = value;
        } else {
            this.overscanFrame_ = (RectProto) ((RectProto.Builder) RectProto.newBuilder(this.overscanFrame_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 4194304;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearOverscanFrame() {
        this.overscanFrame_ = null;
        this.bitField0_ &= -4194305;
    }

    @Override // com.android.server.wm.WindowStateProtoOrBuilder
    @Deprecated
    public boolean hasVisibleFrame() {
        return (this.bitField0_ & 8388608) == 8388608;
    }

    @Override // com.android.server.wm.WindowStateProtoOrBuilder
    @Deprecated
    public RectProto getVisibleFrame() {
        RectProto rectProto = this.visibleFrame_;
        return rectProto == null ? RectProto.getDefaultInstance() : rectProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setVisibleFrame(RectProto value) {
        if (value != null) {
            this.visibleFrame_ = value;
            this.bitField0_ |= 8388608;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setVisibleFrame(RectProto.Builder builderForValue) {
        this.visibleFrame_ = (RectProto) builderForValue.build();
        this.bitField0_ |= 8388608;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeVisibleFrame(RectProto value) {
        RectProto rectProto = this.visibleFrame_;
        if (rectProto == null || rectProto == RectProto.getDefaultInstance()) {
            this.visibleFrame_ = value;
        } else {
            this.visibleFrame_ = (RectProto) ((RectProto.Builder) RectProto.newBuilder(this.visibleFrame_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 8388608;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearVisibleFrame() {
        this.visibleFrame_ = null;
        this.bitField0_ &= -8388609;
    }

    @Override // com.android.server.wm.WindowStateProtoOrBuilder
    @Deprecated
    public boolean hasDecorFrame() {
        return (this.bitField0_ & 16777216) == 16777216;
    }

    @Override // com.android.server.wm.WindowStateProtoOrBuilder
    @Deprecated
    public RectProto getDecorFrame() {
        RectProto rectProto = this.decorFrame_;
        return rectProto == null ? RectProto.getDefaultInstance() : rectProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setDecorFrame(RectProto value) {
        if (value != null) {
            this.decorFrame_ = value;
            this.bitField0_ |= 16777216;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setDecorFrame(RectProto.Builder builderForValue) {
        this.decorFrame_ = (RectProto) builderForValue.build();
        this.bitField0_ |= 16777216;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeDecorFrame(RectProto value) {
        RectProto rectProto = this.decorFrame_;
        if (rectProto == null || rectProto == RectProto.getDefaultInstance()) {
            this.decorFrame_ = value;
        } else {
            this.decorFrame_ = (RectProto) ((RectProto.Builder) RectProto.newBuilder(this.decorFrame_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 16777216;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearDecorFrame() {
        this.decorFrame_ = null;
        this.bitField0_ &= -16777217;
    }

    @Override // com.android.server.wm.WindowStateProtoOrBuilder
    @Deprecated
    public boolean hasOutsetFrame() {
        return (this.bitField0_ & 33554432) == 33554432;
    }

    @Override // com.android.server.wm.WindowStateProtoOrBuilder
    @Deprecated
    public RectProto getOutsetFrame() {
        RectProto rectProto = this.outsetFrame_;
        return rectProto == null ? RectProto.getDefaultInstance() : rectProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setOutsetFrame(RectProto value) {
        if (value != null) {
            this.outsetFrame_ = value;
            this.bitField0_ |= 33554432;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setOutsetFrame(RectProto.Builder builderForValue) {
        this.outsetFrame_ = (RectProto) builderForValue.build();
        this.bitField0_ |= 33554432;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeOutsetFrame(RectProto value) {
        RectProto rectProto = this.outsetFrame_;
        if (rectProto == null || rectProto == RectProto.getDefaultInstance()) {
            this.outsetFrame_ = value;
        } else {
            this.outsetFrame_ = (RectProto) ((RectProto.Builder) RectProto.newBuilder(this.outsetFrame_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 33554432;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearOutsetFrame() {
        this.outsetFrame_ = null;
        this.bitField0_ &= -33554433;
    }

    @Override // com.android.server.wm.WindowStateProtoOrBuilder
    @Deprecated
    public boolean hasOverscanInsets() {
        return (this.bitField0_ & 67108864) == 67108864;
    }

    @Override // com.android.server.wm.WindowStateProtoOrBuilder
    @Deprecated
    public RectProto getOverscanInsets() {
        RectProto rectProto = this.overscanInsets_;
        return rectProto == null ? RectProto.getDefaultInstance() : rectProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setOverscanInsets(RectProto value) {
        if (value != null) {
            this.overscanInsets_ = value;
            this.bitField0_ |= 67108864;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setOverscanInsets(RectProto.Builder builderForValue) {
        this.overscanInsets_ = (RectProto) builderForValue.build();
        this.bitField0_ |= 67108864;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeOverscanInsets(RectProto value) {
        RectProto rectProto = this.overscanInsets_;
        if (rectProto == null || rectProto == RectProto.getDefaultInstance()) {
            this.overscanInsets_ = value;
        } else {
            this.overscanInsets_ = (RectProto) ((RectProto.Builder) RectProto.newBuilder(this.overscanInsets_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 67108864;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearOverscanInsets() {
        this.overscanInsets_ = null;
        this.bitField0_ &= -67108865;
    }

    @Override // com.android.server.wm.WindowStateProtoOrBuilder
    @Deprecated
    public boolean hasVisibleInsets() {
        return (this.bitField0_ & 134217728) == 134217728;
    }

    @Override // com.android.server.wm.WindowStateProtoOrBuilder
    @Deprecated
    public RectProto getVisibleInsets() {
        RectProto rectProto = this.visibleInsets_;
        return rectProto == null ? RectProto.getDefaultInstance() : rectProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setVisibleInsets(RectProto value) {
        if (value != null) {
            this.visibleInsets_ = value;
            this.bitField0_ |= 134217728;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setVisibleInsets(RectProto.Builder builderForValue) {
        this.visibleInsets_ = (RectProto) builderForValue.build();
        this.bitField0_ |= 134217728;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeVisibleInsets(RectProto value) {
        RectProto rectProto = this.visibleInsets_;
        if (rectProto == null || rectProto == RectProto.getDefaultInstance()) {
            this.visibleInsets_ = value;
        } else {
            this.visibleInsets_ = (RectProto) ((RectProto.Builder) RectProto.newBuilder(this.visibleInsets_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 134217728;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearVisibleInsets() {
        this.visibleInsets_ = null;
        this.bitField0_ &= -134217729;
    }

    @Override // com.android.server.wm.WindowStateProtoOrBuilder
    @Deprecated
    public boolean hasStableInsets() {
        return (this.bitField0_ & 268435456) == 268435456;
    }

    @Override // com.android.server.wm.WindowStateProtoOrBuilder
    @Deprecated
    public RectProto getStableInsets() {
        RectProto rectProto = this.stableInsets_;
        return rectProto == null ? RectProto.getDefaultInstance() : rectProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setStableInsets(RectProto value) {
        if (value != null) {
            this.stableInsets_ = value;
            this.bitField0_ |= 268435456;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setStableInsets(RectProto.Builder builderForValue) {
        this.stableInsets_ = (RectProto) builderForValue.build();
        this.bitField0_ |= 268435456;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeStableInsets(RectProto value) {
        RectProto rectProto = this.stableInsets_;
        if (rectProto == null || rectProto == RectProto.getDefaultInstance()) {
            this.stableInsets_ = value;
        } else {
            this.stableInsets_ = (RectProto) ((RectProto.Builder) RectProto.newBuilder(this.stableInsets_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 268435456;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearStableInsets() {
        this.stableInsets_ = null;
        this.bitField0_ &= -268435457;
    }

    @Override // com.android.server.wm.WindowStateProtoOrBuilder
    @Deprecated
    public boolean hasOutsets() {
        return (this.bitField0_ & 536870912) == 536870912;
    }

    @Override // com.android.server.wm.WindowStateProtoOrBuilder
    @Deprecated
    public RectProto getOutsets() {
        RectProto rectProto = this.outsets_;
        return rectProto == null ? RectProto.getDefaultInstance() : rectProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setOutsets(RectProto value) {
        if (value != null) {
            this.outsets_ = value;
            this.bitField0_ |= 536870912;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setOutsets(RectProto.Builder builderForValue) {
        this.outsets_ = (RectProto) builderForValue.build();
        this.bitField0_ |= 536870912;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeOutsets(RectProto value) {
        RectProto rectProto = this.outsets_;
        if (rectProto == null || rectProto == RectProto.getDefaultInstance()) {
            this.outsets_ = value;
        } else {
            this.outsets_ = (RectProto) ((RectProto.Builder) RectProto.newBuilder(this.outsets_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 536870912;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearOutsets() {
        this.outsets_ = null;
        this.bitField0_ &= -536870913;
    }

    @Override // com.android.server.wm.WindowStateProtoOrBuilder
    @Deprecated
    public boolean hasCutout() {
        return (this.bitField0_ & 1073741824) == 1073741824;
    }

    @Override // com.android.server.wm.WindowStateProtoOrBuilder
    @Deprecated
    public DisplayCutoutProto getCutout() {
        DisplayCutoutProto displayCutoutProto = this.cutout_;
        return displayCutoutProto == null ? DisplayCutoutProto.getDefaultInstance() : displayCutoutProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setCutout(DisplayCutoutProto value) {
        if (value != null) {
            this.cutout_ = value;
            this.bitField0_ |= 1073741824;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setCutout(DisplayCutoutProto.Builder builderForValue) {
        this.cutout_ = (DisplayCutoutProto) builderForValue.build();
        this.bitField0_ |= 1073741824;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeCutout(DisplayCutoutProto value) {
        DisplayCutoutProto displayCutoutProto = this.cutout_;
        if (displayCutoutProto == null || displayCutoutProto == DisplayCutoutProto.getDefaultInstance()) {
            this.cutout_ = value;
        } else {
            this.cutout_ = (DisplayCutoutProto) ((DisplayCutoutProto.Builder) DisplayCutoutProto.newBuilder(this.cutout_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 1073741824;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearCutout() {
        this.cutout_ = null;
        this.bitField0_ &= -1073741825;
    }

    @Override // com.android.server.wm.WindowStateProtoOrBuilder
    public boolean hasRemoveOnExit() {
        return (this.bitField0_ & Integer.MIN_VALUE) == Integer.MIN_VALUE;
    }

    @Override // com.android.server.wm.WindowStateProtoOrBuilder
    public boolean getRemoveOnExit() {
        return this.removeOnExit_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setRemoveOnExit(boolean value) {
        this.bitField0_ |= Integer.MIN_VALUE;
        this.removeOnExit_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearRemoveOnExit() {
        this.bitField0_ &= DataConnectionPowerStateEnum.DATA_CONNECTION_POWER_STATE_UNKNOWN_VALUE;
        this.removeOnExit_ = false;
    }

    @Override // com.android.server.wm.WindowStateProtoOrBuilder
    public boolean hasDestroying() {
        return (this.bitField1_ & 1) == 1;
    }

    @Override // com.android.server.wm.WindowStateProtoOrBuilder
    public boolean getDestroying() {
        return this.destroying_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setDestroying(boolean value) {
        this.bitField1_ |= 1;
        this.destroying_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearDestroying() {
        this.bitField1_ &= -2;
        this.destroying_ = false;
    }

    @Override // com.android.server.wm.WindowStateProtoOrBuilder
    public boolean hasRemoved() {
        return (this.bitField1_ & 2) == 2;
    }

    @Override // com.android.server.wm.WindowStateProtoOrBuilder
    public boolean getRemoved() {
        return this.removed_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setRemoved(boolean value) {
        this.bitField1_ |= 2;
        this.removed_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearRemoved() {
        this.bitField1_ &= -3;
        this.removed_ = false;
    }

    @Override // com.android.server.wm.WindowStateProtoOrBuilder
    public boolean hasIsOnScreen() {
        return (this.bitField1_ & 4) == 4;
    }

    @Override // com.android.server.wm.WindowStateProtoOrBuilder
    public boolean getIsOnScreen() {
        return this.isOnScreen_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setIsOnScreen(boolean value) {
        this.bitField1_ |= 4;
        this.isOnScreen_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearIsOnScreen() {
        this.bitField1_ &= -5;
        this.isOnScreen_ = false;
    }

    @Override // com.android.server.wm.WindowStateProtoOrBuilder
    public boolean hasIsVisible() {
        return (this.bitField1_ & 8) == 8;
    }

    @Override // com.android.server.wm.WindowStateProtoOrBuilder
    public boolean getIsVisible() {
        return this.isVisible_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setIsVisible(boolean value) {
        this.bitField1_ |= 8;
        this.isVisible_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearIsVisible() {
        this.bitField1_ &= -9;
        this.isVisible_ = false;
    }

    @Override // com.android.server.wm.WindowStateProtoOrBuilder
    public boolean hasPendingSeamlessRotation() {
        return (this.bitField1_ & 16) == 16;
    }

    @Override // com.android.server.wm.WindowStateProtoOrBuilder
    public boolean getPendingSeamlessRotation() {
        return this.pendingSeamlessRotation_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPendingSeamlessRotation(boolean value) {
        this.bitField1_ |= 16;
        this.pendingSeamlessRotation_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearPendingSeamlessRotation() {
        this.bitField1_ &= -17;
        this.pendingSeamlessRotation_ = false;
    }

    @Override // com.android.server.wm.WindowStateProtoOrBuilder
    public boolean hasFinishedSeamlessRotationFrame() {
        return (this.bitField1_ & 32) == 32;
    }

    @Override // com.android.server.wm.WindowStateProtoOrBuilder
    public long getFinishedSeamlessRotationFrame() {
        return this.finishedSeamlessRotationFrame_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setFinishedSeamlessRotationFrame(long value) {
        this.bitField1_ |= 32;
        this.finishedSeamlessRotationFrame_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearFinishedSeamlessRotationFrame() {
        this.bitField1_ &= -33;
        this.finishedSeamlessRotationFrame_ = 0;
    }

    @Override // com.android.server.wm.WindowStateProtoOrBuilder
    public boolean hasWindowFrames() {
        return (this.bitField1_ & 64) == 64;
    }

    @Override // com.android.server.wm.WindowStateProtoOrBuilder
    public WindowFramesProto getWindowFrames() {
        WindowFramesProto windowFramesProto = this.windowFrames_;
        return windowFramesProto == null ? WindowFramesProto.getDefaultInstance() : windowFramesProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setWindowFrames(WindowFramesProto value) {
        if (value != null) {
            this.windowFrames_ = value;
            this.bitField1_ |= 64;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setWindowFrames(WindowFramesProto.Builder builderForValue) {
        this.windowFrames_ = (WindowFramesProto) builderForValue.build();
        this.bitField1_ |= 64;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeWindowFrames(WindowFramesProto value) {
        WindowFramesProto windowFramesProto = this.windowFrames_;
        if (windowFramesProto == null || windowFramesProto == WindowFramesProto.getDefaultInstance()) {
            this.windowFrames_ = value;
        } else {
            this.windowFrames_ = (WindowFramesProto) ((WindowFramesProto.Builder) WindowFramesProto.newBuilder(this.windowFrames_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField1_ |= 64;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearWindowFrames() {
        this.windowFrames_ = null;
        this.bitField1_ &= -65;
    }

    @Override // com.android.server.wm.WindowStateProtoOrBuilder
    public boolean hasForceSeamlessRotation() {
        return (this.bitField1_ & 128) == 128;
    }

    @Override // com.android.server.wm.WindowStateProtoOrBuilder
    public boolean getForceSeamlessRotation() {
        return this.forceSeamlessRotation_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setForceSeamlessRotation(boolean value) {
        this.bitField1_ |= 128;
        this.forceSeamlessRotation_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearForceSeamlessRotation() {
        this.bitField1_ &= -129;
        this.forceSeamlessRotation_ = false;
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeMessage(1, getWindowContainer());
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeMessage(2, getIdentifier());
        }
        if ((this.bitField0_ & 4) == 4) {
            output.writeInt32(3, this.displayId_);
        }
        if ((this.bitField0_ & 8) == 8) {
            output.writeInt32(4, this.stackId_);
        }
        if ((this.bitField0_ & 16) == 16) {
            output.writeMessage(5, getAttributes());
        }
        if ((this.bitField0_ & 32) == 32) {
            output.writeMessage(6, getGivenContentInsets());
        }
        if ((this.bitField0_ & 64) == 64) {
            output.writeMessage(7, getFrame());
        }
        if ((this.bitField0_ & 128) == 128) {
            output.writeMessage(8, getContainingFrame());
        }
        if ((this.bitField0_ & 256) == 256) {
            output.writeMessage(9, getParentFrame());
        }
        if ((this.bitField0_ & 512) == 512) {
            output.writeMessage(10, getContentFrame());
        }
        if ((this.bitField0_ & 1024) == 1024) {
            output.writeMessage(11, getContentInsets());
        }
        if ((this.bitField0_ & 2048) == 2048) {
            output.writeMessage(12, getSurfaceInsets());
        }
        if ((this.bitField0_ & 4096) == 4096) {
            output.writeMessage(13, getAnimator());
        }
        if ((this.bitField0_ & 8192) == 8192) {
            output.writeBool(14, this.animatingExit_);
        }
        for (int i = 0; i < this.childWindows_.size(); i++) {
            output.writeMessage(15, this.childWindows_.get(i));
        }
        if ((this.bitField0_ & 16384) == 16384) {
            output.writeMessage(16, getSurfacePosition());
        }
        if ((this.bitField0_ & 32768) == 32768) {
            output.writeInt32(18, this.requestedWidth_);
        }
        if ((this.bitField0_ & 65536) == 65536) {
            output.writeInt32(19, this.requestedHeight_);
        }
        if ((this.bitField0_ & 131072) == 131072) {
            output.writeInt32(20, this.viewVisibility_);
        }
        if ((this.bitField0_ & 262144) == 262144) {
            output.writeInt32(21, this.systemUiVisibility_);
        }
        if ((this.bitField0_ & 524288) == 524288) {
            output.writeBool(22, this.hasSurface_);
        }
        if ((this.bitField0_ & 1048576) == 1048576) {
            output.writeBool(23, this.isReadyForDisplay_);
        }
        if ((this.bitField0_ & 2097152) == 2097152) {
            output.writeMessage(24, getDisplayFrame());
        }
        if ((this.bitField0_ & 4194304) == 4194304) {
            output.writeMessage(25, getOverscanFrame());
        }
        if ((this.bitField0_ & 8388608) == 8388608) {
            output.writeMessage(26, getVisibleFrame());
        }
        if ((this.bitField0_ & 16777216) == 16777216) {
            output.writeMessage(27, getDecorFrame());
        }
        if ((this.bitField0_ & 33554432) == 33554432) {
            output.writeMessage(28, getOutsetFrame());
        }
        if ((this.bitField0_ & 67108864) == 67108864) {
            output.writeMessage(29, getOverscanInsets());
        }
        if ((this.bitField0_ & 134217728) == 134217728) {
            output.writeMessage(30, getVisibleInsets());
        }
        if ((this.bitField0_ & 268435456) == 268435456) {
            output.writeMessage(31, getStableInsets());
        }
        if ((this.bitField0_ & 536870912) == 536870912) {
            output.writeMessage(32, getOutsets());
        }
        if ((this.bitField0_ & 1073741824) == 1073741824) {
            output.writeMessage(33, getCutout());
        }
        if ((this.bitField0_ & Integer.MIN_VALUE) == Integer.MIN_VALUE) {
            output.writeBool(34, this.removeOnExit_);
        }
        if ((this.bitField1_ & 1) == 1) {
            output.writeBool(35, this.destroying_);
        }
        if ((this.bitField1_ & 2) == 2) {
            output.writeBool(36, this.removed_);
        }
        if ((this.bitField1_ & 4) == 4) {
            output.writeBool(37, this.isOnScreen_);
        }
        if ((this.bitField1_ & 8) == 8) {
            output.writeBool(38, this.isVisible_);
        }
        if ((this.bitField1_ & 16) == 16) {
            output.writeBool(39, this.pendingSeamlessRotation_);
        }
        if ((this.bitField1_ & 32) == 32) {
            output.writeInt64(40, this.finishedSeamlessRotationFrame_);
        }
        if ((this.bitField1_ & 64) == 64) {
            output.writeMessage(41, getWindowFrames());
        }
        if ((this.bitField1_ & 128) == 128) {
            output.writeBool(42, this.forceSeamlessRotation_);
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
            size2 = 0 + CodedOutputStream.computeMessageSize(1, getWindowContainer());
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeMessageSize(2, getIdentifier());
        }
        if ((this.bitField0_ & 4) == 4) {
            size2 += CodedOutputStream.computeInt32Size(3, this.displayId_);
        }
        if ((this.bitField0_ & 8) == 8) {
            size2 += CodedOutputStream.computeInt32Size(4, this.stackId_);
        }
        if ((this.bitField0_ & 16) == 16) {
            size2 += CodedOutputStream.computeMessageSize(5, getAttributes());
        }
        if ((this.bitField0_ & 32) == 32) {
            size2 += CodedOutputStream.computeMessageSize(6, getGivenContentInsets());
        }
        if ((this.bitField0_ & 64) == 64) {
            size2 += CodedOutputStream.computeMessageSize(7, getFrame());
        }
        if ((this.bitField0_ & 128) == 128) {
            size2 += CodedOutputStream.computeMessageSize(8, getContainingFrame());
        }
        if ((this.bitField0_ & 256) == 256) {
            size2 += CodedOutputStream.computeMessageSize(9, getParentFrame());
        }
        if ((this.bitField0_ & 512) == 512) {
            size2 += CodedOutputStream.computeMessageSize(10, getContentFrame());
        }
        if ((this.bitField0_ & 1024) == 1024) {
            size2 += CodedOutputStream.computeMessageSize(11, getContentInsets());
        }
        if ((this.bitField0_ & 2048) == 2048) {
            size2 += CodedOutputStream.computeMessageSize(12, getSurfaceInsets());
        }
        if ((this.bitField0_ & 4096) == 4096) {
            size2 += CodedOutputStream.computeMessageSize(13, getAnimator());
        }
        if ((this.bitField0_ & 8192) == 8192) {
            size2 += CodedOutputStream.computeBoolSize(14, this.animatingExit_);
        }
        for (int i = 0; i < this.childWindows_.size(); i++) {
            size2 += CodedOutputStream.computeMessageSize(15, this.childWindows_.get(i));
        }
        if ((this.bitField0_ & 16384) == 16384) {
            size2 += CodedOutputStream.computeMessageSize(16, getSurfacePosition());
        }
        if ((this.bitField0_ & 32768) == 32768) {
            size2 += CodedOutputStream.computeInt32Size(18, this.requestedWidth_);
        }
        if ((this.bitField0_ & 65536) == 65536) {
            size2 += CodedOutputStream.computeInt32Size(19, this.requestedHeight_);
        }
        if ((this.bitField0_ & 131072) == 131072) {
            size2 += CodedOutputStream.computeInt32Size(20, this.viewVisibility_);
        }
        if ((this.bitField0_ & 262144) == 262144) {
            size2 += CodedOutputStream.computeInt32Size(21, this.systemUiVisibility_);
        }
        if ((this.bitField0_ & 524288) == 524288) {
            size2 += CodedOutputStream.computeBoolSize(22, this.hasSurface_);
        }
        if ((this.bitField0_ & 1048576) == 1048576) {
            size2 += CodedOutputStream.computeBoolSize(23, this.isReadyForDisplay_);
        }
        if ((this.bitField0_ & 2097152) == 2097152) {
            size2 += CodedOutputStream.computeMessageSize(24, getDisplayFrame());
        }
        if ((this.bitField0_ & 4194304) == 4194304) {
            size2 += CodedOutputStream.computeMessageSize(25, getOverscanFrame());
        }
        if ((this.bitField0_ & 8388608) == 8388608) {
            size2 += CodedOutputStream.computeMessageSize(26, getVisibleFrame());
        }
        if ((this.bitField0_ & 16777216) == 16777216) {
            size2 += CodedOutputStream.computeMessageSize(27, getDecorFrame());
        }
        if ((this.bitField0_ & 33554432) == 33554432) {
            size2 += CodedOutputStream.computeMessageSize(28, getOutsetFrame());
        }
        if ((this.bitField0_ & 67108864) == 67108864) {
            size2 += CodedOutputStream.computeMessageSize(29, getOverscanInsets());
        }
        if ((this.bitField0_ & 134217728) == 134217728) {
            size2 += CodedOutputStream.computeMessageSize(30, getVisibleInsets());
        }
        if ((this.bitField0_ & 268435456) == 268435456) {
            size2 += CodedOutputStream.computeMessageSize(31, getStableInsets());
        }
        if ((this.bitField0_ & 536870912) == 536870912) {
            size2 += CodedOutputStream.computeMessageSize(32, getOutsets());
        }
        if ((this.bitField0_ & 1073741824) == 1073741824) {
            size2 += CodedOutputStream.computeMessageSize(33, getCutout());
        }
        if ((this.bitField0_ & Integer.MIN_VALUE) == Integer.MIN_VALUE) {
            size2 += CodedOutputStream.computeBoolSize(34, this.removeOnExit_);
        }
        if ((this.bitField1_ & 1) == 1) {
            size2 += CodedOutputStream.computeBoolSize(35, this.destroying_);
        }
        if ((this.bitField1_ & 2) == 2) {
            size2 += CodedOutputStream.computeBoolSize(36, this.removed_);
        }
        if ((this.bitField1_ & 4) == 4) {
            size2 += CodedOutputStream.computeBoolSize(37, this.isOnScreen_);
        }
        if ((this.bitField1_ & 8) == 8) {
            size2 += CodedOutputStream.computeBoolSize(38, this.isVisible_);
        }
        if ((this.bitField1_ & 16) == 16) {
            size2 += CodedOutputStream.computeBoolSize(39, this.pendingSeamlessRotation_);
        }
        if ((this.bitField1_ & 32) == 32) {
            size2 += CodedOutputStream.computeInt64Size(40, this.finishedSeamlessRotationFrame_);
        }
        if ((this.bitField1_ & 64) == 64) {
            size2 += CodedOutputStream.computeMessageSize(41, getWindowFrames());
        }
        if ((this.bitField1_ & 128) == 128) {
            size2 += CodedOutputStream.computeBoolSize(42, this.forceSeamlessRotation_);
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static WindowStateProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (WindowStateProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static WindowStateProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (WindowStateProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static WindowStateProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (WindowStateProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static WindowStateProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (WindowStateProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static WindowStateProto parseFrom(InputStream input) throws IOException {
        return (WindowStateProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static WindowStateProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (WindowStateProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static WindowStateProto parseDelimitedFrom(InputStream input) throws IOException {
        return (WindowStateProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static WindowStateProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (WindowStateProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static WindowStateProto parseFrom(CodedInputStream input) throws IOException {
        return (WindowStateProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static WindowStateProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (WindowStateProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(WindowStateProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<WindowStateProto, Builder> implements WindowStateProtoOrBuilder {
        private Builder() {
            super(WindowStateProto.DEFAULT_INSTANCE);
        }

        @Override // com.android.server.wm.WindowStateProtoOrBuilder
        public boolean hasWindowContainer() {
            return ((WindowStateProto) this.instance).hasWindowContainer();
        }

        @Override // com.android.server.wm.WindowStateProtoOrBuilder
        public WindowContainerProto getWindowContainer() {
            return ((WindowStateProto) this.instance).getWindowContainer();
        }

        public Builder setWindowContainer(WindowContainerProto value) {
            copyOnWrite();
            ((WindowStateProto) this.instance).setWindowContainer((WindowStateProto) value);
            return this;
        }

        public Builder setWindowContainer(WindowContainerProto.Builder builderForValue) {
            copyOnWrite();
            ((WindowStateProto) this.instance).setWindowContainer((WindowStateProto) builderForValue);
            return this;
        }

        public Builder mergeWindowContainer(WindowContainerProto value) {
            copyOnWrite();
            ((WindowStateProto) this.instance).mergeWindowContainer(value);
            return this;
        }

        public Builder clearWindowContainer() {
            copyOnWrite();
            ((WindowStateProto) this.instance).clearWindowContainer();
            return this;
        }

        @Override // com.android.server.wm.WindowStateProtoOrBuilder
        public boolean hasIdentifier() {
            return ((WindowStateProto) this.instance).hasIdentifier();
        }

        @Override // com.android.server.wm.WindowStateProtoOrBuilder
        public IdentifierProto getIdentifier() {
            return ((WindowStateProto) this.instance).getIdentifier();
        }

        public Builder setIdentifier(IdentifierProto value) {
            copyOnWrite();
            ((WindowStateProto) this.instance).setIdentifier((WindowStateProto) value);
            return this;
        }

        public Builder setIdentifier(IdentifierProto.Builder builderForValue) {
            copyOnWrite();
            ((WindowStateProto) this.instance).setIdentifier((WindowStateProto) builderForValue);
            return this;
        }

        public Builder mergeIdentifier(IdentifierProto value) {
            copyOnWrite();
            ((WindowStateProto) this.instance).mergeIdentifier(value);
            return this;
        }

        public Builder clearIdentifier() {
            copyOnWrite();
            ((WindowStateProto) this.instance).clearIdentifier();
            return this;
        }

        @Override // com.android.server.wm.WindowStateProtoOrBuilder
        public boolean hasDisplayId() {
            return ((WindowStateProto) this.instance).hasDisplayId();
        }

        @Override // com.android.server.wm.WindowStateProtoOrBuilder
        public int getDisplayId() {
            return ((WindowStateProto) this.instance).getDisplayId();
        }

        public Builder setDisplayId(int value) {
            copyOnWrite();
            ((WindowStateProto) this.instance).setDisplayId(value);
            return this;
        }

        public Builder clearDisplayId() {
            copyOnWrite();
            ((WindowStateProto) this.instance).clearDisplayId();
            return this;
        }

        @Override // com.android.server.wm.WindowStateProtoOrBuilder
        public boolean hasStackId() {
            return ((WindowStateProto) this.instance).hasStackId();
        }

        @Override // com.android.server.wm.WindowStateProtoOrBuilder
        public int getStackId() {
            return ((WindowStateProto) this.instance).getStackId();
        }

        public Builder setStackId(int value) {
            copyOnWrite();
            ((WindowStateProto) this.instance).setStackId(value);
            return this;
        }

        public Builder clearStackId() {
            copyOnWrite();
            ((WindowStateProto) this.instance).clearStackId();
            return this;
        }

        @Override // com.android.server.wm.WindowStateProtoOrBuilder
        public boolean hasAttributes() {
            return ((WindowStateProto) this.instance).hasAttributes();
        }

        @Override // com.android.server.wm.WindowStateProtoOrBuilder
        public WindowLayoutParamsProto getAttributes() {
            return ((WindowStateProto) this.instance).getAttributes();
        }

        public Builder setAttributes(WindowLayoutParamsProto value) {
            copyOnWrite();
            ((WindowStateProto) this.instance).setAttributes((WindowStateProto) value);
            return this;
        }

        public Builder setAttributes(WindowLayoutParamsProto.Builder builderForValue) {
            copyOnWrite();
            ((WindowStateProto) this.instance).setAttributes((WindowStateProto) builderForValue);
            return this;
        }

        public Builder mergeAttributes(WindowLayoutParamsProto value) {
            copyOnWrite();
            ((WindowStateProto) this.instance).mergeAttributes(value);
            return this;
        }

        public Builder clearAttributes() {
            copyOnWrite();
            ((WindowStateProto) this.instance).clearAttributes();
            return this;
        }

        @Override // com.android.server.wm.WindowStateProtoOrBuilder
        public boolean hasGivenContentInsets() {
            return ((WindowStateProto) this.instance).hasGivenContentInsets();
        }

        @Override // com.android.server.wm.WindowStateProtoOrBuilder
        public RectProto getGivenContentInsets() {
            return ((WindowStateProto) this.instance).getGivenContentInsets();
        }

        public Builder setGivenContentInsets(RectProto value) {
            copyOnWrite();
            ((WindowStateProto) this.instance).setGivenContentInsets((WindowStateProto) value);
            return this;
        }

        public Builder setGivenContentInsets(RectProto.Builder builderForValue) {
            copyOnWrite();
            ((WindowStateProto) this.instance).setGivenContentInsets((WindowStateProto) builderForValue);
            return this;
        }

        public Builder mergeGivenContentInsets(RectProto value) {
            copyOnWrite();
            ((WindowStateProto) this.instance).mergeGivenContentInsets(value);
            return this;
        }

        public Builder clearGivenContentInsets() {
            copyOnWrite();
            ((WindowStateProto) this.instance).clearGivenContentInsets();
            return this;
        }

        @Override // com.android.server.wm.WindowStateProtoOrBuilder
        @Deprecated
        public boolean hasFrame() {
            return ((WindowStateProto) this.instance).hasFrame();
        }

        @Override // com.android.server.wm.WindowStateProtoOrBuilder
        @Deprecated
        public RectProto getFrame() {
            return ((WindowStateProto) this.instance).getFrame();
        }

        @Deprecated
        public Builder setFrame(RectProto value) {
            copyOnWrite();
            ((WindowStateProto) this.instance).setFrame((WindowStateProto) value);
            return this;
        }

        @Deprecated
        public Builder setFrame(RectProto.Builder builderForValue) {
            copyOnWrite();
            ((WindowStateProto) this.instance).setFrame((WindowStateProto) builderForValue);
            return this;
        }

        @Deprecated
        public Builder mergeFrame(RectProto value) {
            copyOnWrite();
            ((WindowStateProto) this.instance).mergeFrame(value);
            return this;
        }

        @Deprecated
        public Builder clearFrame() {
            copyOnWrite();
            ((WindowStateProto) this.instance).clearFrame();
            return this;
        }

        @Override // com.android.server.wm.WindowStateProtoOrBuilder
        @Deprecated
        public boolean hasContainingFrame() {
            return ((WindowStateProto) this.instance).hasContainingFrame();
        }

        @Override // com.android.server.wm.WindowStateProtoOrBuilder
        @Deprecated
        public RectProto getContainingFrame() {
            return ((WindowStateProto) this.instance).getContainingFrame();
        }

        @Deprecated
        public Builder setContainingFrame(RectProto value) {
            copyOnWrite();
            ((WindowStateProto) this.instance).setContainingFrame((WindowStateProto) value);
            return this;
        }

        @Deprecated
        public Builder setContainingFrame(RectProto.Builder builderForValue) {
            copyOnWrite();
            ((WindowStateProto) this.instance).setContainingFrame((WindowStateProto) builderForValue);
            return this;
        }

        @Deprecated
        public Builder mergeContainingFrame(RectProto value) {
            copyOnWrite();
            ((WindowStateProto) this.instance).mergeContainingFrame(value);
            return this;
        }

        @Deprecated
        public Builder clearContainingFrame() {
            copyOnWrite();
            ((WindowStateProto) this.instance).clearContainingFrame();
            return this;
        }

        @Override // com.android.server.wm.WindowStateProtoOrBuilder
        @Deprecated
        public boolean hasParentFrame() {
            return ((WindowStateProto) this.instance).hasParentFrame();
        }

        @Override // com.android.server.wm.WindowStateProtoOrBuilder
        @Deprecated
        public RectProto getParentFrame() {
            return ((WindowStateProto) this.instance).getParentFrame();
        }

        @Deprecated
        public Builder setParentFrame(RectProto value) {
            copyOnWrite();
            ((WindowStateProto) this.instance).setParentFrame((WindowStateProto) value);
            return this;
        }

        @Deprecated
        public Builder setParentFrame(RectProto.Builder builderForValue) {
            copyOnWrite();
            ((WindowStateProto) this.instance).setParentFrame((WindowStateProto) builderForValue);
            return this;
        }

        @Deprecated
        public Builder mergeParentFrame(RectProto value) {
            copyOnWrite();
            ((WindowStateProto) this.instance).mergeParentFrame(value);
            return this;
        }

        @Deprecated
        public Builder clearParentFrame() {
            copyOnWrite();
            ((WindowStateProto) this.instance).clearParentFrame();
            return this;
        }

        @Override // com.android.server.wm.WindowStateProtoOrBuilder
        @Deprecated
        public boolean hasContentFrame() {
            return ((WindowStateProto) this.instance).hasContentFrame();
        }

        @Override // com.android.server.wm.WindowStateProtoOrBuilder
        @Deprecated
        public RectProto getContentFrame() {
            return ((WindowStateProto) this.instance).getContentFrame();
        }

        @Deprecated
        public Builder setContentFrame(RectProto value) {
            copyOnWrite();
            ((WindowStateProto) this.instance).setContentFrame((WindowStateProto) value);
            return this;
        }

        @Deprecated
        public Builder setContentFrame(RectProto.Builder builderForValue) {
            copyOnWrite();
            ((WindowStateProto) this.instance).setContentFrame((WindowStateProto) builderForValue);
            return this;
        }

        @Deprecated
        public Builder mergeContentFrame(RectProto value) {
            copyOnWrite();
            ((WindowStateProto) this.instance).mergeContentFrame(value);
            return this;
        }

        @Deprecated
        public Builder clearContentFrame() {
            copyOnWrite();
            ((WindowStateProto) this.instance).clearContentFrame();
            return this;
        }

        @Override // com.android.server.wm.WindowStateProtoOrBuilder
        @Deprecated
        public boolean hasContentInsets() {
            return ((WindowStateProto) this.instance).hasContentInsets();
        }

        @Override // com.android.server.wm.WindowStateProtoOrBuilder
        @Deprecated
        public RectProto getContentInsets() {
            return ((WindowStateProto) this.instance).getContentInsets();
        }

        @Deprecated
        public Builder setContentInsets(RectProto value) {
            copyOnWrite();
            ((WindowStateProto) this.instance).setContentInsets((WindowStateProto) value);
            return this;
        }

        @Deprecated
        public Builder setContentInsets(RectProto.Builder builderForValue) {
            copyOnWrite();
            ((WindowStateProto) this.instance).setContentInsets((WindowStateProto) builderForValue);
            return this;
        }

        @Deprecated
        public Builder mergeContentInsets(RectProto value) {
            copyOnWrite();
            ((WindowStateProto) this.instance).mergeContentInsets(value);
            return this;
        }

        @Deprecated
        public Builder clearContentInsets() {
            copyOnWrite();
            ((WindowStateProto) this.instance).clearContentInsets();
            return this;
        }

        @Override // com.android.server.wm.WindowStateProtoOrBuilder
        public boolean hasSurfaceInsets() {
            return ((WindowStateProto) this.instance).hasSurfaceInsets();
        }

        @Override // com.android.server.wm.WindowStateProtoOrBuilder
        public RectProto getSurfaceInsets() {
            return ((WindowStateProto) this.instance).getSurfaceInsets();
        }

        public Builder setSurfaceInsets(RectProto value) {
            copyOnWrite();
            ((WindowStateProto) this.instance).setSurfaceInsets((WindowStateProto) value);
            return this;
        }

        public Builder setSurfaceInsets(RectProto.Builder builderForValue) {
            copyOnWrite();
            ((WindowStateProto) this.instance).setSurfaceInsets((WindowStateProto) builderForValue);
            return this;
        }

        public Builder mergeSurfaceInsets(RectProto value) {
            copyOnWrite();
            ((WindowStateProto) this.instance).mergeSurfaceInsets(value);
            return this;
        }

        public Builder clearSurfaceInsets() {
            copyOnWrite();
            ((WindowStateProto) this.instance).clearSurfaceInsets();
            return this;
        }

        @Override // com.android.server.wm.WindowStateProtoOrBuilder
        public boolean hasAnimator() {
            return ((WindowStateProto) this.instance).hasAnimator();
        }

        @Override // com.android.server.wm.WindowStateProtoOrBuilder
        public WindowStateAnimatorProto getAnimator() {
            return ((WindowStateProto) this.instance).getAnimator();
        }

        public Builder setAnimator(WindowStateAnimatorProto value) {
            copyOnWrite();
            ((WindowStateProto) this.instance).setAnimator((WindowStateProto) value);
            return this;
        }

        public Builder setAnimator(WindowStateAnimatorProto.Builder builderForValue) {
            copyOnWrite();
            ((WindowStateProto) this.instance).setAnimator((WindowStateProto) builderForValue);
            return this;
        }

        public Builder mergeAnimator(WindowStateAnimatorProto value) {
            copyOnWrite();
            ((WindowStateProto) this.instance).mergeAnimator(value);
            return this;
        }

        public Builder clearAnimator() {
            copyOnWrite();
            ((WindowStateProto) this.instance).clearAnimator();
            return this;
        }

        @Override // com.android.server.wm.WindowStateProtoOrBuilder
        public boolean hasAnimatingExit() {
            return ((WindowStateProto) this.instance).hasAnimatingExit();
        }

        @Override // com.android.server.wm.WindowStateProtoOrBuilder
        public boolean getAnimatingExit() {
            return ((WindowStateProto) this.instance).getAnimatingExit();
        }

        public Builder setAnimatingExit(boolean value) {
            copyOnWrite();
            ((WindowStateProto) this.instance).setAnimatingExit(value);
            return this;
        }

        public Builder clearAnimatingExit() {
            copyOnWrite();
            ((WindowStateProto) this.instance).clearAnimatingExit();
            return this;
        }

        @Override // com.android.server.wm.WindowStateProtoOrBuilder
        public List<WindowStateProto> getChildWindowsList() {
            return Collections.unmodifiableList(((WindowStateProto) this.instance).getChildWindowsList());
        }

        @Override // com.android.server.wm.WindowStateProtoOrBuilder
        public int getChildWindowsCount() {
            return ((WindowStateProto) this.instance).getChildWindowsCount();
        }

        @Override // com.android.server.wm.WindowStateProtoOrBuilder
        public WindowStateProto getChildWindows(int index) {
            return ((WindowStateProto) this.instance).getChildWindows(index);
        }

        public Builder setChildWindows(int index, WindowStateProto value) {
            copyOnWrite();
            ((WindowStateProto) this.instance).setChildWindows((WindowStateProto) index, (int) value);
            return this;
        }

        public Builder setChildWindows(int index, Builder builderForValue) {
            copyOnWrite();
            ((WindowStateProto) this.instance).setChildWindows((WindowStateProto) index, (int) builderForValue);
            return this;
        }

        public Builder addChildWindows(WindowStateProto value) {
            copyOnWrite();
            ((WindowStateProto) this.instance).addChildWindows(value);
            return this;
        }

        public Builder addChildWindows(int index, WindowStateProto value) {
            copyOnWrite();
            ((WindowStateProto) this.instance).addChildWindows((WindowStateProto) index, (int) value);
            return this;
        }

        public Builder addChildWindows(Builder builderForValue) {
            copyOnWrite();
            ((WindowStateProto) this.instance).addChildWindows((WindowStateProto) builderForValue);
            return this;
        }

        public Builder addChildWindows(int index, Builder builderForValue) {
            copyOnWrite();
            ((WindowStateProto) this.instance).addChildWindows((WindowStateProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllChildWindows(Iterable<? extends WindowStateProto> values) {
            copyOnWrite();
            ((WindowStateProto) this.instance).addAllChildWindows(values);
            return this;
        }

        public Builder clearChildWindows() {
            copyOnWrite();
            ((WindowStateProto) this.instance).clearChildWindows();
            return this;
        }

        public Builder removeChildWindows(int index) {
            copyOnWrite();
            ((WindowStateProto) this.instance).removeChildWindows(index);
            return this;
        }

        @Override // com.android.server.wm.WindowStateProtoOrBuilder
        public boolean hasSurfacePosition() {
            return ((WindowStateProto) this.instance).hasSurfacePosition();
        }

        @Override // com.android.server.wm.WindowStateProtoOrBuilder
        public RectProto getSurfacePosition() {
            return ((WindowStateProto) this.instance).getSurfacePosition();
        }

        public Builder setSurfacePosition(RectProto value) {
            copyOnWrite();
            ((WindowStateProto) this.instance).setSurfacePosition((WindowStateProto) value);
            return this;
        }

        public Builder setSurfacePosition(RectProto.Builder builderForValue) {
            copyOnWrite();
            ((WindowStateProto) this.instance).setSurfacePosition((WindowStateProto) builderForValue);
            return this;
        }

        public Builder mergeSurfacePosition(RectProto value) {
            copyOnWrite();
            ((WindowStateProto) this.instance).mergeSurfacePosition(value);
            return this;
        }

        public Builder clearSurfacePosition() {
            copyOnWrite();
            ((WindowStateProto) this.instance).clearSurfacePosition();
            return this;
        }

        @Override // com.android.server.wm.WindowStateProtoOrBuilder
        public boolean hasRequestedWidth() {
            return ((WindowStateProto) this.instance).hasRequestedWidth();
        }

        @Override // com.android.server.wm.WindowStateProtoOrBuilder
        public int getRequestedWidth() {
            return ((WindowStateProto) this.instance).getRequestedWidth();
        }

        public Builder setRequestedWidth(int value) {
            copyOnWrite();
            ((WindowStateProto) this.instance).setRequestedWidth(value);
            return this;
        }

        public Builder clearRequestedWidth() {
            copyOnWrite();
            ((WindowStateProto) this.instance).clearRequestedWidth();
            return this;
        }

        @Override // com.android.server.wm.WindowStateProtoOrBuilder
        public boolean hasRequestedHeight() {
            return ((WindowStateProto) this.instance).hasRequestedHeight();
        }

        @Override // com.android.server.wm.WindowStateProtoOrBuilder
        public int getRequestedHeight() {
            return ((WindowStateProto) this.instance).getRequestedHeight();
        }

        public Builder setRequestedHeight(int value) {
            copyOnWrite();
            ((WindowStateProto) this.instance).setRequestedHeight(value);
            return this;
        }

        public Builder clearRequestedHeight() {
            copyOnWrite();
            ((WindowStateProto) this.instance).clearRequestedHeight();
            return this;
        }

        @Override // com.android.server.wm.WindowStateProtoOrBuilder
        public boolean hasViewVisibility() {
            return ((WindowStateProto) this.instance).hasViewVisibility();
        }

        @Override // com.android.server.wm.WindowStateProtoOrBuilder
        public int getViewVisibility() {
            return ((WindowStateProto) this.instance).getViewVisibility();
        }

        public Builder setViewVisibility(int value) {
            copyOnWrite();
            ((WindowStateProto) this.instance).setViewVisibility(value);
            return this;
        }

        public Builder clearViewVisibility() {
            copyOnWrite();
            ((WindowStateProto) this.instance).clearViewVisibility();
            return this;
        }

        @Override // com.android.server.wm.WindowStateProtoOrBuilder
        public boolean hasSystemUiVisibility() {
            return ((WindowStateProto) this.instance).hasSystemUiVisibility();
        }

        @Override // com.android.server.wm.WindowStateProtoOrBuilder
        public int getSystemUiVisibility() {
            return ((WindowStateProto) this.instance).getSystemUiVisibility();
        }

        public Builder setSystemUiVisibility(int value) {
            copyOnWrite();
            ((WindowStateProto) this.instance).setSystemUiVisibility(value);
            return this;
        }

        public Builder clearSystemUiVisibility() {
            copyOnWrite();
            ((WindowStateProto) this.instance).clearSystemUiVisibility();
            return this;
        }

        @Override // com.android.server.wm.WindowStateProtoOrBuilder
        public boolean hasHasSurface() {
            return ((WindowStateProto) this.instance).hasHasSurface();
        }

        @Override // com.android.server.wm.WindowStateProtoOrBuilder
        public boolean getHasSurface() {
            return ((WindowStateProto) this.instance).getHasSurface();
        }

        public Builder setHasSurface(boolean value) {
            copyOnWrite();
            ((WindowStateProto) this.instance).setHasSurface(value);
            return this;
        }

        public Builder clearHasSurface() {
            copyOnWrite();
            ((WindowStateProto) this.instance).clearHasSurface();
            return this;
        }

        @Override // com.android.server.wm.WindowStateProtoOrBuilder
        public boolean hasIsReadyForDisplay() {
            return ((WindowStateProto) this.instance).hasIsReadyForDisplay();
        }

        @Override // com.android.server.wm.WindowStateProtoOrBuilder
        public boolean getIsReadyForDisplay() {
            return ((WindowStateProto) this.instance).getIsReadyForDisplay();
        }

        public Builder setIsReadyForDisplay(boolean value) {
            copyOnWrite();
            ((WindowStateProto) this.instance).setIsReadyForDisplay(value);
            return this;
        }

        public Builder clearIsReadyForDisplay() {
            copyOnWrite();
            ((WindowStateProto) this.instance).clearIsReadyForDisplay();
            return this;
        }

        @Override // com.android.server.wm.WindowStateProtoOrBuilder
        @Deprecated
        public boolean hasDisplayFrame() {
            return ((WindowStateProto) this.instance).hasDisplayFrame();
        }

        @Override // com.android.server.wm.WindowStateProtoOrBuilder
        @Deprecated
        public RectProto getDisplayFrame() {
            return ((WindowStateProto) this.instance).getDisplayFrame();
        }

        @Deprecated
        public Builder setDisplayFrame(RectProto value) {
            copyOnWrite();
            ((WindowStateProto) this.instance).setDisplayFrame((WindowStateProto) value);
            return this;
        }

        @Deprecated
        public Builder setDisplayFrame(RectProto.Builder builderForValue) {
            copyOnWrite();
            ((WindowStateProto) this.instance).setDisplayFrame((WindowStateProto) builderForValue);
            return this;
        }

        @Deprecated
        public Builder mergeDisplayFrame(RectProto value) {
            copyOnWrite();
            ((WindowStateProto) this.instance).mergeDisplayFrame(value);
            return this;
        }

        @Deprecated
        public Builder clearDisplayFrame() {
            copyOnWrite();
            ((WindowStateProto) this.instance).clearDisplayFrame();
            return this;
        }

        @Override // com.android.server.wm.WindowStateProtoOrBuilder
        @Deprecated
        public boolean hasOverscanFrame() {
            return ((WindowStateProto) this.instance).hasOverscanFrame();
        }

        @Override // com.android.server.wm.WindowStateProtoOrBuilder
        @Deprecated
        public RectProto getOverscanFrame() {
            return ((WindowStateProto) this.instance).getOverscanFrame();
        }

        @Deprecated
        public Builder setOverscanFrame(RectProto value) {
            copyOnWrite();
            ((WindowStateProto) this.instance).setOverscanFrame((WindowStateProto) value);
            return this;
        }

        @Deprecated
        public Builder setOverscanFrame(RectProto.Builder builderForValue) {
            copyOnWrite();
            ((WindowStateProto) this.instance).setOverscanFrame((WindowStateProto) builderForValue);
            return this;
        }

        @Deprecated
        public Builder mergeOverscanFrame(RectProto value) {
            copyOnWrite();
            ((WindowStateProto) this.instance).mergeOverscanFrame(value);
            return this;
        }

        @Deprecated
        public Builder clearOverscanFrame() {
            copyOnWrite();
            ((WindowStateProto) this.instance).clearOverscanFrame();
            return this;
        }

        @Override // com.android.server.wm.WindowStateProtoOrBuilder
        @Deprecated
        public boolean hasVisibleFrame() {
            return ((WindowStateProto) this.instance).hasVisibleFrame();
        }

        @Override // com.android.server.wm.WindowStateProtoOrBuilder
        @Deprecated
        public RectProto getVisibleFrame() {
            return ((WindowStateProto) this.instance).getVisibleFrame();
        }

        @Deprecated
        public Builder setVisibleFrame(RectProto value) {
            copyOnWrite();
            ((WindowStateProto) this.instance).setVisibleFrame((WindowStateProto) value);
            return this;
        }

        @Deprecated
        public Builder setVisibleFrame(RectProto.Builder builderForValue) {
            copyOnWrite();
            ((WindowStateProto) this.instance).setVisibleFrame((WindowStateProto) builderForValue);
            return this;
        }

        @Deprecated
        public Builder mergeVisibleFrame(RectProto value) {
            copyOnWrite();
            ((WindowStateProto) this.instance).mergeVisibleFrame(value);
            return this;
        }

        @Deprecated
        public Builder clearVisibleFrame() {
            copyOnWrite();
            ((WindowStateProto) this.instance).clearVisibleFrame();
            return this;
        }

        @Override // com.android.server.wm.WindowStateProtoOrBuilder
        @Deprecated
        public boolean hasDecorFrame() {
            return ((WindowStateProto) this.instance).hasDecorFrame();
        }

        @Override // com.android.server.wm.WindowStateProtoOrBuilder
        @Deprecated
        public RectProto getDecorFrame() {
            return ((WindowStateProto) this.instance).getDecorFrame();
        }

        @Deprecated
        public Builder setDecorFrame(RectProto value) {
            copyOnWrite();
            ((WindowStateProto) this.instance).setDecorFrame((WindowStateProto) value);
            return this;
        }

        @Deprecated
        public Builder setDecorFrame(RectProto.Builder builderForValue) {
            copyOnWrite();
            ((WindowStateProto) this.instance).setDecorFrame((WindowStateProto) builderForValue);
            return this;
        }

        @Deprecated
        public Builder mergeDecorFrame(RectProto value) {
            copyOnWrite();
            ((WindowStateProto) this.instance).mergeDecorFrame(value);
            return this;
        }

        @Deprecated
        public Builder clearDecorFrame() {
            copyOnWrite();
            ((WindowStateProto) this.instance).clearDecorFrame();
            return this;
        }

        @Override // com.android.server.wm.WindowStateProtoOrBuilder
        @Deprecated
        public boolean hasOutsetFrame() {
            return ((WindowStateProto) this.instance).hasOutsetFrame();
        }

        @Override // com.android.server.wm.WindowStateProtoOrBuilder
        @Deprecated
        public RectProto getOutsetFrame() {
            return ((WindowStateProto) this.instance).getOutsetFrame();
        }

        @Deprecated
        public Builder setOutsetFrame(RectProto value) {
            copyOnWrite();
            ((WindowStateProto) this.instance).setOutsetFrame((WindowStateProto) value);
            return this;
        }

        @Deprecated
        public Builder setOutsetFrame(RectProto.Builder builderForValue) {
            copyOnWrite();
            ((WindowStateProto) this.instance).setOutsetFrame((WindowStateProto) builderForValue);
            return this;
        }

        @Deprecated
        public Builder mergeOutsetFrame(RectProto value) {
            copyOnWrite();
            ((WindowStateProto) this.instance).mergeOutsetFrame(value);
            return this;
        }

        @Deprecated
        public Builder clearOutsetFrame() {
            copyOnWrite();
            ((WindowStateProto) this.instance).clearOutsetFrame();
            return this;
        }

        @Override // com.android.server.wm.WindowStateProtoOrBuilder
        @Deprecated
        public boolean hasOverscanInsets() {
            return ((WindowStateProto) this.instance).hasOverscanInsets();
        }

        @Override // com.android.server.wm.WindowStateProtoOrBuilder
        @Deprecated
        public RectProto getOverscanInsets() {
            return ((WindowStateProto) this.instance).getOverscanInsets();
        }

        @Deprecated
        public Builder setOverscanInsets(RectProto value) {
            copyOnWrite();
            ((WindowStateProto) this.instance).setOverscanInsets((WindowStateProto) value);
            return this;
        }

        @Deprecated
        public Builder setOverscanInsets(RectProto.Builder builderForValue) {
            copyOnWrite();
            ((WindowStateProto) this.instance).setOverscanInsets((WindowStateProto) builderForValue);
            return this;
        }

        @Deprecated
        public Builder mergeOverscanInsets(RectProto value) {
            copyOnWrite();
            ((WindowStateProto) this.instance).mergeOverscanInsets(value);
            return this;
        }

        @Deprecated
        public Builder clearOverscanInsets() {
            copyOnWrite();
            ((WindowStateProto) this.instance).clearOverscanInsets();
            return this;
        }

        @Override // com.android.server.wm.WindowStateProtoOrBuilder
        @Deprecated
        public boolean hasVisibleInsets() {
            return ((WindowStateProto) this.instance).hasVisibleInsets();
        }

        @Override // com.android.server.wm.WindowStateProtoOrBuilder
        @Deprecated
        public RectProto getVisibleInsets() {
            return ((WindowStateProto) this.instance).getVisibleInsets();
        }

        @Deprecated
        public Builder setVisibleInsets(RectProto value) {
            copyOnWrite();
            ((WindowStateProto) this.instance).setVisibleInsets((WindowStateProto) value);
            return this;
        }

        @Deprecated
        public Builder setVisibleInsets(RectProto.Builder builderForValue) {
            copyOnWrite();
            ((WindowStateProto) this.instance).setVisibleInsets((WindowStateProto) builderForValue);
            return this;
        }

        @Deprecated
        public Builder mergeVisibleInsets(RectProto value) {
            copyOnWrite();
            ((WindowStateProto) this.instance).mergeVisibleInsets(value);
            return this;
        }

        @Deprecated
        public Builder clearVisibleInsets() {
            copyOnWrite();
            ((WindowStateProto) this.instance).clearVisibleInsets();
            return this;
        }

        @Override // com.android.server.wm.WindowStateProtoOrBuilder
        @Deprecated
        public boolean hasStableInsets() {
            return ((WindowStateProto) this.instance).hasStableInsets();
        }

        @Override // com.android.server.wm.WindowStateProtoOrBuilder
        @Deprecated
        public RectProto getStableInsets() {
            return ((WindowStateProto) this.instance).getStableInsets();
        }

        @Deprecated
        public Builder setStableInsets(RectProto value) {
            copyOnWrite();
            ((WindowStateProto) this.instance).setStableInsets((WindowStateProto) value);
            return this;
        }

        @Deprecated
        public Builder setStableInsets(RectProto.Builder builderForValue) {
            copyOnWrite();
            ((WindowStateProto) this.instance).setStableInsets((WindowStateProto) builderForValue);
            return this;
        }

        @Deprecated
        public Builder mergeStableInsets(RectProto value) {
            copyOnWrite();
            ((WindowStateProto) this.instance).mergeStableInsets(value);
            return this;
        }

        @Deprecated
        public Builder clearStableInsets() {
            copyOnWrite();
            ((WindowStateProto) this.instance).clearStableInsets();
            return this;
        }

        @Override // com.android.server.wm.WindowStateProtoOrBuilder
        @Deprecated
        public boolean hasOutsets() {
            return ((WindowStateProto) this.instance).hasOutsets();
        }

        @Override // com.android.server.wm.WindowStateProtoOrBuilder
        @Deprecated
        public RectProto getOutsets() {
            return ((WindowStateProto) this.instance).getOutsets();
        }

        @Deprecated
        public Builder setOutsets(RectProto value) {
            copyOnWrite();
            ((WindowStateProto) this.instance).setOutsets((WindowStateProto) value);
            return this;
        }

        @Deprecated
        public Builder setOutsets(RectProto.Builder builderForValue) {
            copyOnWrite();
            ((WindowStateProto) this.instance).setOutsets((WindowStateProto) builderForValue);
            return this;
        }

        @Deprecated
        public Builder mergeOutsets(RectProto value) {
            copyOnWrite();
            ((WindowStateProto) this.instance).mergeOutsets(value);
            return this;
        }

        @Deprecated
        public Builder clearOutsets() {
            copyOnWrite();
            ((WindowStateProto) this.instance).clearOutsets();
            return this;
        }

        @Override // com.android.server.wm.WindowStateProtoOrBuilder
        @Deprecated
        public boolean hasCutout() {
            return ((WindowStateProto) this.instance).hasCutout();
        }

        @Override // com.android.server.wm.WindowStateProtoOrBuilder
        @Deprecated
        public DisplayCutoutProto getCutout() {
            return ((WindowStateProto) this.instance).getCutout();
        }

        @Deprecated
        public Builder setCutout(DisplayCutoutProto value) {
            copyOnWrite();
            ((WindowStateProto) this.instance).setCutout((WindowStateProto) value);
            return this;
        }

        @Deprecated
        public Builder setCutout(DisplayCutoutProto.Builder builderForValue) {
            copyOnWrite();
            ((WindowStateProto) this.instance).setCutout((WindowStateProto) builderForValue);
            return this;
        }

        @Deprecated
        public Builder mergeCutout(DisplayCutoutProto value) {
            copyOnWrite();
            ((WindowStateProto) this.instance).mergeCutout(value);
            return this;
        }

        @Deprecated
        public Builder clearCutout() {
            copyOnWrite();
            ((WindowStateProto) this.instance).clearCutout();
            return this;
        }

        @Override // com.android.server.wm.WindowStateProtoOrBuilder
        public boolean hasRemoveOnExit() {
            return ((WindowStateProto) this.instance).hasRemoveOnExit();
        }

        @Override // com.android.server.wm.WindowStateProtoOrBuilder
        public boolean getRemoveOnExit() {
            return ((WindowStateProto) this.instance).getRemoveOnExit();
        }

        public Builder setRemoveOnExit(boolean value) {
            copyOnWrite();
            ((WindowStateProto) this.instance).setRemoveOnExit(value);
            return this;
        }

        public Builder clearRemoveOnExit() {
            copyOnWrite();
            ((WindowStateProto) this.instance).clearRemoveOnExit();
            return this;
        }

        @Override // com.android.server.wm.WindowStateProtoOrBuilder
        public boolean hasDestroying() {
            return ((WindowStateProto) this.instance).hasDestroying();
        }

        @Override // com.android.server.wm.WindowStateProtoOrBuilder
        public boolean getDestroying() {
            return ((WindowStateProto) this.instance).getDestroying();
        }

        public Builder setDestroying(boolean value) {
            copyOnWrite();
            ((WindowStateProto) this.instance).setDestroying(value);
            return this;
        }

        public Builder clearDestroying() {
            copyOnWrite();
            ((WindowStateProto) this.instance).clearDestroying();
            return this;
        }

        @Override // com.android.server.wm.WindowStateProtoOrBuilder
        public boolean hasRemoved() {
            return ((WindowStateProto) this.instance).hasRemoved();
        }

        @Override // com.android.server.wm.WindowStateProtoOrBuilder
        public boolean getRemoved() {
            return ((WindowStateProto) this.instance).getRemoved();
        }

        public Builder setRemoved(boolean value) {
            copyOnWrite();
            ((WindowStateProto) this.instance).setRemoved(value);
            return this;
        }

        public Builder clearRemoved() {
            copyOnWrite();
            ((WindowStateProto) this.instance).clearRemoved();
            return this;
        }

        @Override // com.android.server.wm.WindowStateProtoOrBuilder
        public boolean hasIsOnScreen() {
            return ((WindowStateProto) this.instance).hasIsOnScreen();
        }

        @Override // com.android.server.wm.WindowStateProtoOrBuilder
        public boolean getIsOnScreen() {
            return ((WindowStateProto) this.instance).getIsOnScreen();
        }

        public Builder setIsOnScreen(boolean value) {
            copyOnWrite();
            ((WindowStateProto) this.instance).setIsOnScreen(value);
            return this;
        }

        public Builder clearIsOnScreen() {
            copyOnWrite();
            ((WindowStateProto) this.instance).clearIsOnScreen();
            return this;
        }

        @Override // com.android.server.wm.WindowStateProtoOrBuilder
        public boolean hasIsVisible() {
            return ((WindowStateProto) this.instance).hasIsVisible();
        }

        @Override // com.android.server.wm.WindowStateProtoOrBuilder
        public boolean getIsVisible() {
            return ((WindowStateProto) this.instance).getIsVisible();
        }

        public Builder setIsVisible(boolean value) {
            copyOnWrite();
            ((WindowStateProto) this.instance).setIsVisible(value);
            return this;
        }

        public Builder clearIsVisible() {
            copyOnWrite();
            ((WindowStateProto) this.instance).clearIsVisible();
            return this;
        }

        @Override // com.android.server.wm.WindowStateProtoOrBuilder
        public boolean hasPendingSeamlessRotation() {
            return ((WindowStateProto) this.instance).hasPendingSeamlessRotation();
        }

        @Override // com.android.server.wm.WindowStateProtoOrBuilder
        public boolean getPendingSeamlessRotation() {
            return ((WindowStateProto) this.instance).getPendingSeamlessRotation();
        }

        public Builder setPendingSeamlessRotation(boolean value) {
            copyOnWrite();
            ((WindowStateProto) this.instance).setPendingSeamlessRotation(value);
            return this;
        }

        public Builder clearPendingSeamlessRotation() {
            copyOnWrite();
            ((WindowStateProto) this.instance).clearPendingSeamlessRotation();
            return this;
        }

        @Override // com.android.server.wm.WindowStateProtoOrBuilder
        public boolean hasFinishedSeamlessRotationFrame() {
            return ((WindowStateProto) this.instance).hasFinishedSeamlessRotationFrame();
        }

        @Override // com.android.server.wm.WindowStateProtoOrBuilder
        public long getFinishedSeamlessRotationFrame() {
            return ((WindowStateProto) this.instance).getFinishedSeamlessRotationFrame();
        }

        public Builder setFinishedSeamlessRotationFrame(long value) {
            copyOnWrite();
            ((WindowStateProto) this.instance).setFinishedSeamlessRotationFrame(value);
            return this;
        }

        public Builder clearFinishedSeamlessRotationFrame() {
            copyOnWrite();
            ((WindowStateProto) this.instance).clearFinishedSeamlessRotationFrame();
            return this;
        }

        @Override // com.android.server.wm.WindowStateProtoOrBuilder
        public boolean hasWindowFrames() {
            return ((WindowStateProto) this.instance).hasWindowFrames();
        }

        @Override // com.android.server.wm.WindowStateProtoOrBuilder
        public WindowFramesProto getWindowFrames() {
            return ((WindowStateProto) this.instance).getWindowFrames();
        }

        public Builder setWindowFrames(WindowFramesProto value) {
            copyOnWrite();
            ((WindowStateProto) this.instance).setWindowFrames((WindowStateProto) value);
            return this;
        }

        public Builder setWindowFrames(WindowFramesProto.Builder builderForValue) {
            copyOnWrite();
            ((WindowStateProto) this.instance).setWindowFrames((WindowStateProto) builderForValue);
            return this;
        }

        public Builder mergeWindowFrames(WindowFramesProto value) {
            copyOnWrite();
            ((WindowStateProto) this.instance).mergeWindowFrames(value);
            return this;
        }

        public Builder clearWindowFrames() {
            copyOnWrite();
            ((WindowStateProto) this.instance).clearWindowFrames();
            return this;
        }

        @Override // com.android.server.wm.WindowStateProtoOrBuilder
        public boolean hasForceSeamlessRotation() {
            return ((WindowStateProto) this.instance).hasForceSeamlessRotation();
        }

        @Override // com.android.server.wm.WindowStateProtoOrBuilder
        public boolean getForceSeamlessRotation() {
            return ((WindowStateProto) this.instance).getForceSeamlessRotation();
        }

        public Builder setForceSeamlessRotation(boolean value) {
            copyOnWrite();
            ((WindowStateProto) this.instance).setForceSeamlessRotation(value);
            return this;
        }

        public Builder clearForceSeamlessRotation() {
            copyOnWrite();
            ((WindowStateProto) this.instance).clearForceSeamlessRotation();
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new WindowStateProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                this.childWindows_.makeImmutable();
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                WindowStateProto other = (WindowStateProto) arg1;
                this.windowContainer_ = (WindowContainerProto) visitor.visitMessage(this.windowContainer_, other.windowContainer_);
                this.identifier_ = (IdentifierProto) visitor.visitMessage(this.identifier_, other.identifier_);
                this.displayId_ = visitor.visitInt(hasDisplayId(), this.displayId_, other.hasDisplayId(), other.displayId_);
                this.stackId_ = visitor.visitInt(hasStackId(), this.stackId_, other.hasStackId(), other.stackId_);
                this.attributes_ = (WindowLayoutParamsProto) visitor.visitMessage(this.attributes_, other.attributes_);
                this.givenContentInsets_ = (RectProto) visitor.visitMessage(this.givenContentInsets_, other.givenContentInsets_);
                this.frame_ = (RectProto) visitor.visitMessage(this.frame_, other.frame_);
                this.containingFrame_ = (RectProto) visitor.visitMessage(this.containingFrame_, other.containingFrame_);
                this.parentFrame_ = (RectProto) visitor.visitMessage(this.parentFrame_, other.parentFrame_);
                this.contentFrame_ = (RectProto) visitor.visitMessage(this.contentFrame_, other.contentFrame_);
                this.contentInsets_ = (RectProto) visitor.visitMessage(this.contentInsets_, other.contentInsets_);
                this.surfaceInsets_ = (RectProto) visitor.visitMessage(this.surfaceInsets_, other.surfaceInsets_);
                this.animator_ = (WindowStateAnimatorProto) visitor.visitMessage(this.animator_, other.animator_);
                this.animatingExit_ = visitor.visitBoolean(hasAnimatingExit(), this.animatingExit_, other.hasAnimatingExit(), other.animatingExit_);
                this.childWindows_ = visitor.visitList(this.childWindows_, other.childWindows_);
                this.surfacePosition_ = (RectProto) visitor.visitMessage(this.surfacePosition_, other.surfacePosition_);
                this.requestedWidth_ = visitor.visitInt(hasRequestedWidth(), this.requestedWidth_, other.hasRequestedWidth(), other.requestedWidth_);
                this.requestedHeight_ = visitor.visitInt(hasRequestedHeight(), this.requestedHeight_, other.hasRequestedHeight(), other.requestedHeight_);
                this.viewVisibility_ = visitor.visitInt(hasViewVisibility(), this.viewVisibility_, other.hasViewVisibility(), other.viewVisibility_);
                this.systemUiVisibility_ = visitor.visitInt(hasSystemUiVisibility(), this.systemUiVisibility_, other.hasSystemUiVisibility(), other.systemUiVisibility_);
                this.hasSurface_ = visitor.visitBoolean(hasHasSurface(), this.hasSurface_, other.hasHasSurface(), other.hasSurface_);
                this.isReadyForDisplay_ = visitor.visitBoolean(hasIsReadyForDisplay(), this.isReadyForDisplay_, other.hasIsReadyForDisplay(), other.isReadyForDisplay_);
                this.displayFrame_ = (RectProto) visitor.visitMessage(this.displayFrame_, other.displayFrame_);
                this.overscanFrame_ = (RectProto) visitor.visitMessage(this.overscanFrame_, other.overscanFrame_);
                this.visibleFrame_ = (RectProto) visitor.visitMessage(this.visibleFrame_, other.visibleFrame_);
                this.decorFrame_ = (RectProto) visitor.visitMessage(this.decorFrame_, other.decorFrame_);
                this.outsetFrame_ = (RectProto) visitor.visitMessage(this.outsetFrame_, other.outsetFrame_);
                this.overscanInsets_ = (RectProto) visitor.visitMessage(this.overscanInsets_, other.overscanInsets_);
                this.visibleInsets_ = (RectProto) visitor.visitMessage(this.visibleInsets_, other.visibleInsets_);
                this.stableInsets_ = (RectProto) visitor.visitMessage(this.stableInsets_, other.stableInsets_);
                this.outsets_ = (RectProto) visitor.visitMessage(this.outsets_, other.outsets_);
                this.cutout_ = (DisplayCutoutProto) visitor.visitMessage(this.cutout_, other.cutout_);
                this.removeOnExit_ = visitor.visitBoolean(hasRemoveOnExit(), this.removeOnExit_, other.hasRemoveOnExit(), other.removeOnExit_);
                this.destroying_ = visitor.visitBoolean(hasDestroying(), this.destroying_, other.hasDestroying(), other.destroying_);
                this.removed_ = visitor.visitBoolean(hasRemoved(), this.removed_, other.hasRemoved(), other.removed_);
                this.isOnScreen_ = visitor.visitBoolean(hasIsOnScreen(), this.isOnScreen_, other.hasIsOnScreen(), other.isOnScreen_);
                this.isVisible_ = visitor.visitBoolean(hasIsVisible(), this.isVisible_, other.hasIsVisible(), other.isVisible_);
                this.pendingSeamlessRotation_ = visitor.visitBoolean(hasPendingSeamlessRotation(), this.pendingSeamlessRotation_, other.hasPendingSeamlessRotation(), other.pendingSeamlessRotation_);
                this.finishedSeamlessRotationFrame_ = visitor.visitLong(hasFinishedSeamlessRotationFrame(), this.finishedSeamlessRotationFrame_, other.hasFinishedSeamlessRotationFrame(), other.finishedSeamlessRotationFrame_);
                this.windowFrames_ = (WindowFramesProto) visitor.visitMessage(this.windowFrames_, other.windowFrames_);
                this.forceSeamlessRotation_ = visitor.visitBoolean(hasForceSeamlessRotation(), this.forceSeamlessRotation_, other.hasForceSeamlessRotation(), other.forceSeamlessRotation_);
                if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE) {
                    this.bitField0_ |= other.bitField0_;
                    this.bitField1_ |= other.bitField1_;
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
                            case 10:
                                WindowContainerProto.Builder subBuilder = null;
                                if ((this.bitField0_ & 1) == 1) {
                                    subBuilder = (WindowContainerProto.Builder) this.windowContainer_.toBuilder();
                                }
                                this.windowContainer_ = (WindowContainerProto) input.readMessage(WindowContainerProto.parser(), extensionRegistry);
                                if (subBuilder != null) {
                                    subBuilder.mergeFrom((GeneratedMessageLite) this.windowContainer_);
                                    this.windowContainer_ = (WindowContainerProto) subBuilder.buildPartial();
                                }
                                this.bitField0_ |= 1;
                                break;
                            case 18:
                                IdentifierProto.Builder subBuilder2 = null;
                                if ((this.bitField0_ & 2) == 2) {
                                    subBuilder2 = (IdentifierProto.Builder) this.identifier_.toBuilder();
                                }
                                this.identifier_ = (IdentifierProto) input.readMessage(IdentifierProto.parser(), extensionRegistry);
                                if (subBuilder2 != null) {
                                    subBuilder2.mergeFrom((GeneratedMessageLite) this.identifier_);
                                    this.identifier_ = (IdentifierProto) subBuilder2.buildPartial();
                                }
                                this.bitField0_ |= 2;
                                break;
                            case 24:
                                this.bitField0_ |= 4;
                                this.displayId_ = input.readInt32();
                                break;
                            case 32:
                                this.bitField0_ |= 8;
                                this.stackId_ = input.readInt32();
                                break;
                            case 42:
                                WindowLayoutParamsProto.Builder subBuilder3 = null;
                                if ((this.bitField0_ & 16) == 16) {
                                    subBuilder3 = (WindowLayoutParamsProto.Builder) this.attributes_.toBuilder();
                                }
                                this.attributes_ = (WindowLayoutParamsProto) input.readMessage(WindowLayoutParamsProto.parser(), extensionRegistry);
                                if (subBuilder3 != null) {
                                    subBuilder3.mergeFrom((GeneratedMessageLite) this.attributes_);
                                    this.attributes_ = (WindowLayoutParamsProto) subBuilder3.buildPartial();
                                }
                                this.bitField0_ |= 16;
                                break;
                            case 50:
                                RectProto.Builder subBuilder4 = null;
                                if ((this.bitField0_ & 32) == 32) {
                                    subBuilder4 = (RectProto.Builder) this.givenContentInsets_.toBuilder();
                                }
                                this.givenContentInsets_ = (RectProto) input.readMessage(RectProto.parser(), extensionRegistry);
                                if (subBuilder4 != null) {
                                    subBuilder4.mergeFrom((GeneratedMessageLite) this.givenContentInsets_);
                                    this.givenContentInsets_ = (RectProto) subBuilder4.buildPartial();
                                }
                                this.bitField0_ = 32 | this.bitField0_;
                                break;
                            case 58:
                                RectProto.Builder subBuilder5 = null;
                                if ((this.bitField0_ & 64) == 64) {
                                    subBuilder5 = (RectProto.Builder) this.frame_.toBuilder();
                                }
                                this.frame_ = (RectProto) input.readMessage(RectProto.parser(), extensionRegistry);
                                if (subBuilder5 != null) {
                                    subBuilder5.mergeFrom((GeneratedMessageLite) this.frame_);
                                    this.frame_ = (RectProto) subBuilder5.buildPartial();
                                }
                                this.bitField0_ |= 64;
                                break;
                            case 66:
                                RectProto.Builder subBuilder6 = null;
                                if ((this.bitField0_ & 128) == 128) {
                                    subBuilder6 = (RectProto.Builder) this.containingFrame_.toBuilder();
                                }
                                this.containingFrame_ = (RectProto) input.readMessage(RectProto.parser(), extensionRegistry);
                                if (subBuilder6 != null) {
                                    subBuilder6.mergeFrom((GeneratedMessageLite) this.containingFrame_);
                                    this.containingFrame_ = (RectProto) subBuilder6.buildPartial();
                                }
                                this.bitField0_ = 128 | this.bitField0_;
                                break;
                            case 74:
                                RectProto.Builder subBuilder7 = null;
                                if ((this.bitField0_ & 256) == 256) {
                                    subBuilder7 = (RectProto.Builder) this.parentFrame_.toBuilder();
                                }
                                this.parentFrame_ = (RectProto) input.readMessage(RectProto.parser(), extensionRegistry);
                                if (subBuilder7 != null) {
                                    subBuilder7.mergeFrom((GeneratedMessageLite) this.parentFrame_);
                                    this.parentFrame_ = (RectProto) subBuilder7.buildPartial();
                                }
                                this.bitField0_ |= 256;
                                break;
                            case 82:
                                RectProto.Builder subBuilder8 = null;
                                if ((this.bitField0_ & 512) == 512) {
                                    subBuilder8 = (RectProto.Builder) this.contentFrame_.toBuilder();
                                }
                                this.contentFrame_ = (RectProto) input.readMessage(RectProto.parser(), extensionRegistry);
                                if (subBuilder8 != null) {
                                    subBuilder8.mergeFrom((GeneratedMessageLite) this.contentFrame_);
                                    this.contentFrame_ = (RectProto) subBuilder8.buildPartial();
                                }
                                this.bitField0_ |= 512;
                                break;
                            case 90:
                                RectProto.Builder subBuilder9 = null;
                                if ((this.bitField0_ & 1024) == 1024) {
                                    subBuilder9 = (RectProto.Builder) this.contentInsets_.toBuilder();
                                }
                                this.contentInsets_ = (RectProto) input.readMessage(RectProto.parser(), extensionRegistry);
                                if (subBuilder9 != null) {
                                    subBuilder9.mergeFrom((GeneratedMessageLite) this.contentInsets_);
                                    this.contentInsets_ = (RectProto) subBuilder9.buildPartial();
                                }
                                this.bitField0_ |= 1024;
                                break;
                            case 98:
                                RectProto.Builder subBuilder10 = null;
                                if ((this.bitField0_ & 2048) == 2048) {
                                    subBuilder10 = (RectProto.Builder) this.surfaceInsets_.toBuilder();
                                }
                                this.surfaceInsets_ = (RectProto) input.readMessage(RectProto.parser(), extensionRegistry);
                                if (subBuilder10 != null) {
                                    subBuilder10.mergeFrom((GeneratedMessageLite) this.surfaceInsets_);
                                    this.surfaceInsets_ = (RectProto) subBuilder10.buildPartial();
                                }
                                this.bitField0_ |= 2048;
                                break;
                            case 106:
                                WindowStateAnimatorProto.Builder subBuilder11 = null;
                                if ((this.bitField0_ & 4096) == 4096) {
                                    subBuilder11 = (WindowStateAnimatorProto.Builder) this.animator_.toBuilder();
                                }
                                this.animator_ = (WindowStateAnimatorProto) input.readMessage(WindowStateAnimatorProto.parser(), extensionRegistry);
                                if (subBuilder11 != null) {
                                    subBuilder11.mergeFrom((GeneratedMessageLite) this.animator_);
                                    this.animator_ = (WindowStateAnimatorProto) subBuilder11.buildPartial();
                                }
                                this.bitField0_ |= 4096;
                                break;
                            case 112:
                                this.bitField0_ |= 8192;
                                this.animatingExit_ = input.readBool();
                                break;
                            case 122:
                                if (!this.childWindows_.isModifiable()) {
                                    this.childWindows_ = GeneratedMessageLite.mutableCopy(this.childWindows_);
                                }
                                this.childWindows_.add((WindowStateProto) input.readMessage(parser(), extensionRegistry));
                                break;
                            case 130:
                                RectProto.Builder subBuilder12 = null;
                                if ((this.bitField0_ & 16384) == 16384) {
                                    subBuilder12 = (RectProto.Builder) this.surfacePosition_.toBuilder();
                                }
                                this.surfacePosition_ = (RectProto) input.readMessage(RectProto.parser(), extensionRegistry);
                                if (subBuilder12 != null) {
                                    subBuilder12.mergeFrom((GeneratedMessageLite) this.surfacePosition_);
                                    this.surfacePosition_ = (RectProto) subBuilder12.buildPartial();
                                }
                                this.bitField0_ |= 16384;
                                break;
                            case 144:
                                this.bitField0_ |= 32768;
                                this.requestedWidth_ = input.readInt32();
                                break;
                            case 152:
                                this.bitField0_ |= 65536;
                                this.requestedHeight_ = input.readInt32();
                                break;
                            case 160:
                                this.bitField0_ |= 131072;
                                this.viewVisibility_ = input.readInt32();
                                break;
                            case 168:
                                this.bitField0_ |= 262144;
                                this.systemUiVisibility_ = input.readInt32();
                                break;
                            case AtomsProto.Atom.ASSIST_GESTURE_PROGRESS_REPORTED_FIELD_NUMBER:
                                this.bitField0_ |= 524288;
                                this.hasSurface_ = input.readBool();
                                break;
                            case 184:
                                this.bitField0_ |= 1048576;
                                this.isReadyForDisplay_ = input.readBool();
                                break;
                            case AtomsProto.Atom.MEDIAMETRICS_AUDIOTRACK_REPORTED_FIELD_NUMBER:
                                RectProto.Builder subBuilder13 = null;
                                if ((this.bitField0_ & 2097152) == 2097152) {
                                    subBuilder13 = (RectProto.Builder) this.displayFrame_.toBuilder();
                                }
                                this.displayFrame_ = (RectProto) input.readMessage(RectProto.parser(), extensionRegistry);
                                if (subBuilder13 != null) {
                                    subBuilder13.mergeFrom((GeneratedMessageLite) this.displayFrame_);
                                    this.displayFrame_ = (RectProto) subBuilder13.buildPartial();
                                }
                                this.bitField0_ |= 2097152;
                                break;
                            case PROCESS_STATS_SUMMARY_VALUE:
                                RectProto.Builder subBuilder14 = null;
                                if ((this.bitField0_ & 4194304) == 4194304) {
                                    subBuilder14 = (RectProto.Builder) this.overscanFrame_.toBuilder();
                                }
                                this.overscanFrame_ = (RectProto) input.readMessage(RectProto.parser(), extensionRegistry);
                                if (subBuilder14 != null) {
                                    subBuilder14.mergeFrom((GeneratedMessageLite) this.overscanFrame_);
                                    this.overscanFrame_ = (RectProto) subBuilder14.buildPartial();
                                }
                                this.bitField0_ |= 4194304;
                                break;
                            case AtomsProto.Atom.LOCATION_MANAGER_API_USAGE_REPORTED_FIELD_NUMBER:
                                RectProto.Builder subBuilder15 = null;
                                if ((this.bitField0_ & 8388608) == 8388608) {
                                    subBuilder15 = (RectProto.Builder) this.visibleFrame_.toBuilder();
                                }
                                this.visibleFrame_ = (RectProto) input.readMessage(RectProto.parser(), extensionRegistry);
                                if (subBuilder15 != null) {
                                    subBuilder15.mergeFrom((GeneratedMessageLite) this.visibleFrame_);
                                    this.visibleFrame_ = (RectProto) subBuilder15.buildPartial();
                                }
                                this.bitField0_ |= 8388608;
                                break;
                            case AtomsProto.Atom.PERMISSION_APPS_FRAGMENT_VIEWED_FIELD_NUMBER:
                                RectProto.Builder subBuilder16 = null;
                                if ((this.bitField0_ & 16777216) == 16777216) {
                                    subBuilder16 = (RectProto.Builder) this.decorFrame_.toBuilder();
                                }
                                this.decorFrame_ = (RectProto) input.readMessage(RectProto.parser(), extensionRegistry);
                                if (subBuilder16 != null) {
                                    subBuilder16.mergeFrom((GeneratedMessageLite) this.decorFrame_);
                                    this.decorFrame_ = (RectProto) subBuilder16.buildPartial();
                                }
                                this.bitField0_ |= 16777216;
                                break;
                            case ACTION_SEARCH_RESULTS_VALUE:
                                RectProto.Builder subBuilder17 = null;
                                if ((this.bitField0_ & 33554432) == 33554432) {
                                    subBuilder17 = (RectProto.Builder) this.outsetFrame_.toBuilder();
                                }
                                this.outsetFrame_ = (RectProto) input.readMessage(RectProto.parser(), extensionRegistry);
                                if (subBuilder17 != null) {
                                    subBuilder17.mergeFrom((GeneratedMessageLite) this.outsetFrame_);
                                    this.outsetFrame_ = (RectProto) subBuilder17.buildPartial();
                                }
                                this.bitField0_ |= 33554432;
                                break;
                            case 234:
                                RectProto.Builder subBuilder18 = null;
                                if ((this.bitField0_ & 67108864) == 67108864) {
                                    subBuilder18 = (RectProto.Builder) this.overscanInsets_.toBuilder();
                                }
                                this.overscanInsets_ = (RectProto) input.readMessage(RectProto.parser(), extensionRegistry);
                                if (subBuilder18 != null) {
                                    subBuilder18.mergeFrom((GeneratedMessageLite) this.overscanInsets_);
                                    this.overscanInsets_ = (RectProto) subBuilder18.buildPartial();
                                }
                                this.bitField0_ |= 67108864;
                                break;
                            case FINGERPRINT_ENROLL_FINISH_VALUE:
                                RectProto.Builder subBuilder19 = null;
                                if ((this.bitField0_ & 134217728) == 134217728) {
                                    subBuilder19 = (RectProto.Builder) this.visibleInsets_.toBuilder();
                                }
                                this.visibleInsets_ = (RectProto) input.readMessage(RectProto.parser(), extensionRegistry);
                                if (subBuilder19 != null) {
                                    subBuilder19.mergeFrom((GeneratedMessageLite) this.visibleInsets_);
                                    this.visibleInsets_ = (RectProto) subBuilder19.buildPartial();
                                }
                                this.bitField0_ |= 134217728;
                                break;
                            case NS_T_TSIG_VALUE:
                                RectProto.Builder subBuilder20 = null;
                                if ((this.bitField0_ & 268435456) == 268435456) {
                                    subBuilder20 = (RectProto.Builder) this.stableInsets_.toBuilder();
                                }
                                this.stableInsets_ = (RectProto) input.readMessage(RectProto.parser(), extensionRegistry);
                                if (subBuilder20 != null) {
                                    subBuilder20.mergeFrom((GeneratedMessageLite) this.stableInsets_);
                                    this.stableInsets_ = (RectProto) subBuilder20.buildPartial();
                                }
                                this.bitField0_ |= 268435456;
                                break;
                            case BACKGROUND_CHECK_SUMMARY_VALUE:
                                RectProto.Builder subBuilder21 = null;
                                if ((this.bitField0_ & 536870912) == 536870912) {
                                    subBuilder21 = (RectProto.Builder) this.outsets_.toBuilder();
                                }
                                this.outsets_ = (RectProto) input.readMessage(RectProto.parser(), extensionRegistry);
                                if (subBuilder21 != null) {
                                    subBuilder21.mergeFrom((GeneratedMessageLite) this.outsets_);
                                    this.outsets_ = (RectProto) subBuilder21.buildPartial();
                                }
                                this.bitField0_ |= 536870912;
                                break;
                            case 266:
                                DisplayCutoutProto.Builder subBuilder22 = null;
                                if ((this.bitField0_ & 1073741824) == 1073741824) {
                                    subBuilder22 = (DisplayCutoutProto.Builder) this.cutout_.toBuilder();
                                }
                                this.cutout_ = (DisplayCutoutProto) input.readMessage(DisplayCutoutProto.parser(), extensionRegistry);
                                if (subBuilder22 != null) {
                                    subBuilder22.mergeFrom((GeneratedMessageLite) this.cutout_);
                                    this.cutout_ = (DisplayCutoutProto) subBuilder22.buildPartial();
                                }
                                this.bitField0_ |= 1073741824;
                                break;
                            case 272:
                                this.bitField0_ |= Integer.MIN_VALUE;
                                this.removeOnExit_ = input.readBool();
                                break;
                            case 280:
                                this.bitField1_ |= 1;
                                this.destroying_ = input.readBool();
                                break;
                            case 288:
                                this.bitField1_ |= 2;
                                this.removed_ = input.readBool();
                                break;
                            case 296:
                                this.bitField1_ |= 4;
                                this.isOnScreen_ = input.readBool();
                                break;
                            case 304:
                                this.bitField1_ |= 8;
                                this.isVisible_ = input.readBool();
                                break;
                            case 312:
                                this.bitField1_ |= 16;
                                this.pendingSeamlessRotation_ = input.readBool();
                                break;
                            case 320:
                                this.bitField1_ |= 32;
                                this.finishedSeamlessRotationFrame_ = input.readInt64();
                                break;
                            case 330:
                                WindowFramesProto.Builder subBuilder23 = null;
                                if ((this.bitField1_ & 64) == 64) {
                                    subBuilder23 = (WindowFramesProto.Builder) this.windowFrames_.toBuilder();
                                }
                                this.windowFrames_ = (WindowFramesProto) input.readMessage(WindowFramesProto.parser(), extensionRegistry);
                                if (subBuilder23 != null) {
                                    subBuilder23.mergeFrom((GeneratedMessageLite) this.windowFrames_);
                                    this.windowFrames_ = (WindowFramesProto) subBuilder23.buildPartial();
                                }
                                this.bitField1_ |= 64;
                                break;
                            case SOUND_VALUE:
                                this.bitField1_ = 128 | this.bitField1_;
                                this.forceSeamlessRotation_ = input.readBool();
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
                    synchronized (WindowStateProto.class) {
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

    public static WindowStateProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<WindowStateProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
