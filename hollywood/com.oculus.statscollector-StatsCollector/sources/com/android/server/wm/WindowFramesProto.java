package com.android.server.wm;

import android.graphics.RectProto;
import android.view.DisplayCutoutProto;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;

public final class WindowFramesProto extends GeneratedMessageLite<WindowFramesProto, Builder> implements WindowFramesProtoOrBuilder {
    public static final int CONTAINING_FRAME_FIELD_NUMBER = 1;
    public static final int CONTENT_FRAME_FIELD_NUMBER = 2;
    public static final int CONTENT_INSETS_FIELD_NUMBER = 11;
    public static final int CUTOUT_FIELD_NUMBER = 10;
    public static final int DECOR_FRAME_FIELD_NUMBER = 3;
    private static final WindowFramesProto DEFAULT_INSTANCE = new WindowFramesProto();
    public static final int DISPLAY_FRAME_FIELD_NUMBER = 4;
    public static final int FRAME_FIELD_NUMBER = 5;
    public static final int OUTSETS_FIELD_NUMBER = 15;
    public static final int OUTSET_FRAME_FIELD_NUMBER = 6;
    public static final int OVERSCAN_FRAME_FIELD_NUMBER = 7;
    public static final int OVERSCAN_INSETS_FIELD_NUMBER = 12;
    public static final int PARENT_FRAME_FIELD_NUMBER = 8;
    private static volatile Parser<WindowFramesProto> PARSER = null;
    public static final int STABLE_INSETS_FIELD_NUMBER = 14;
    public static final int VISIBLE_FRAME_FIELD_NUMBER = 9;
    public static final int VISIBLE_INSETS_FIELD_NUMBER = 13;
    private int bitField0_;
    private RectProto containingFrame_;
    private RectProto contentFrame_;
    private RectProto contentInsets_;
    private DisplayCutoutProto cutout_;
    private RectProto decorFrame_;
    private RectProto displayFrame_;
    private RectProto frame_;
    private RectProto outsetFrame_;
    private RectProto outsets_;
    private RectProto overscanFrame_;
    private RectProto overscanInsets_;
    private RectProto parentFrame_;
    private RectProto stableInsets_;
    private RectProto visibleFrame_;
    private RectProto visibleInsets_;

    private WindowFramesProto() {
    }

    @Override // com.android.server.wm.WindowFramesProtoOrBuilder
    public boolean hasContainingFrame() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // com.android.server.wm.WindowFramesProtoOrBuilder
    public RectProto getContainingFrame() {
        RectProto rectProto = this.containingFrame_;
        return rectProto == null ? RectProto.getDefaultInstance() : rectProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setContainingFrame(RectProto value) {
        if (value != null) {
            this.containingFrame_ = value;
            this.bitField0_ |= 1;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setContainingFrame(RectProto.Builder builderForValue) {
        this.containingFrame_ = (RectProto) builderForValue.build();
        this.bitField0_ |= 1;
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
        this.bitField0_ |= 1;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearContainingFrame() {
        this.containingFrame_ = null;
        this.bitField0_ &= -2;
    }

    @Override // com.android.server.wm.WindowFramesProtoOrBuilder
    public boolean hasContentFrame() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // com.android.server.wm.WindowFramesProtoOrBuilder
    public RectProto getContentFrame() {
        RectProto rectProto = this.contentFrame_;
        return rectProto == null ? RectProto.getDefaultInstance() : rectProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setContentFrame(RectProto value) {
        if (value != null) {
            this.contentFrame_ = value;
            this.bitField0_ |= 2;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setContentFrame(RectProto.Builder builderForValue) {
        this.contentFrame_ = (RectProto) builderForValue.build();
        this.bitField0_ |= 2;
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
        this.bitField0_ |= 2;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearContentFrame() {
        this.contentFrame_ = null;
        this.bitField0_ &= -3;
    }

    @Override // com.android.server.wm.WindowFramesProtoOrBuilder
    public boolean hasDecorFrame() {
        return (this.bitField0_ & 4) == 4;
    }

    @Override // com.android.server.wm.WindowFramesProtoOrBuilder
    public RectProto getDecorFrame() {
        RectProto rectProto = this.decorFrame_;
        return rectProto == null ? RectProto.getDefaultInstance() : rectProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setDecorFrame(RectProto value) {
        if (value != null) {
            this.decorFrame_ = value;
            this.bitField0_ |= 4;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setDecorFrame(RectProto.Builder builderForValue) {
        this.decorFrame_ = (RectProto) builderForValue.build();
        this.bitField0_ |= 4;
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
        this.bitField0_ |= 4;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearDecorFrame() {
        this.decorFrame_ = null;
        this.bitField0_ &= -5;
    }

    @Override // com.android.server.wm.WindowFramesProtoOrBuilder
    public boolean hasDisplayFrame() {
        return (this.bitField0_ & 8) == 8;
    }

    @Override // com.android.server.wm.WindowFramesProtoOrBuilder
    public RectProto getDisplayFrame() {
        RectProto rectProto = this.displayFrame_;
        return rectProto == null ? RectProto.getDefaultInstance() : rectProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setDisplayFrame(RectProto value) {
        if (value != null) {
            this.displayFrame_ = value;
            this.bitField0_ |= 8;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setDisplayFrame(RectProto.Builder builderForValue) {
        this.displayFrame_ = (RectProto) builderForValue.build();
        this.bitField0_ |= 8;
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
        this.bitField0_ |= 8;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearDisplayFrame() {
        this.displayFrame_ = null;
        this.bitField0_ &= -9;
    }

    @Override // com.android.server.wm.WindowFramesProtoOrBuilder
    public boolean hasFrame() {
        return (this.bitField0_ & 16) == 16;
    }

    @Override // com.android.server.wm.WindowFramesProtoOrBuilder
    public RectProto getFrame() {
        RectProto rectProto = this.frame_;
        return rectProto == null ? RectProto.getDefaultInstance() : rectProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setFrame(RectProto value) {
        if (value != null) {
            this.frame_ = value;
            this.bitField0_ |= 16;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setFrame(RectProto.Builder builderForValue) {
        this.frame_ = (RectProto) builderForValue.build();
        this.bitField0_ |= 16;
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
        this.bitField0_ |= 16;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearFrame() {
        this.frame_ = null;
        this.bitField0_ &= -17;
    }

    @Override // com.android.server.wm.WindowFramesProtoOrBuilder
    public boolean hasOutsetFrame() {
        return (this.bitField0_ & 32) == 32;
    }

    @Override // com.android.server.wm.WindowFramesProtoOrBuilder
    public RectProto getOutsetFrame() {
        RectProto rectProto = this.outsetFrame_;
        return rectProto == null ? RectProto.getDefaultInstance() : rectProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setOutsetFrame(RectProto value) {
        if (value != null) {
            this.outsetFrame_ = value;
            this.bitField0_ |= 32;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setOutsetFrame(RectProto.Builder builderForValue) {
        this.outsetFrame_ = (RectProto) builderForValue.build();
        this.bitField0_ |= 32;
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
        this.bitField0_ |= 32;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearOutsetFrame() {
        this.outsetFrame_ = null;
        this.bitField0_ &= -33;
    }

    @Override // com.android.server.wm.WindowFramesProtoOrBuilder
    public boolean hasOverscanFrame() {
        return (this.bitField0_ & 64) == 64;
    }

    @Override // com.android.server.wm.WindowFramesProtoOrBuilder
    public RectProto getOverscanFrame() {
        RectProto rectProto = this.overscanFrame_;
        return rectProto == null ? RectProto.getDefaultInstance() : rectProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setOverscanFrame(RectProto value) {
        if (value != null) {
            this.overscanFrame_ = value;
            this.bitField0_ |= 64;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setOverscanFrame(RectProto.Builder builderForValue) {
        this.overscanFrame_ = (RectProto) builderForValue.build();
        this.bitField0_ |= 64;
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
        this.bitField0_ |= 64;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearOverscanFrame() {
        this.overscanFrame_ = null;
        this.bitField0_ &= -65;
    }

    @Override // com.android.server.wm.WindowFramesProtoOrBuilder
    public boolean hasParentFrame() {
        return (this.bitField0_ & 128) == 128;
    }

    @Override // com.android.server.wm.WindowFramesProtoOrBuilder
    public RectProto getParentFrame() {
        RectProto rectProto = this.parentFrame_;
        return rectProto == null ? RectProto.getDefaultInstance() : rectProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setParentFrame(RectProto value) {
        if (value != null) {
            this.parentFrame_ = value;
            this.bitField0_ |= 128;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setParentFrame(RectProto.Builder builderForValue) {
        this.parentFrame_ = (RectProto) builderForValue.build();
        this.bitField0_ |= 128;
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
        this.bitField0_ |= 128;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearParentFrame() {
        this.parentFrame_ = null;
        this.bitField0_ &= -129;
    }

    @Override // com.android.server.wm.WindowFramesProtoOrBuilder
    public boolean hasVisibleFrame() {
        return (this.bitField0_ & 256) == 256;
    }

    @Override // com.android.server.wm.WindowFramesProtoOrBuilder
    public RectProto getVisibleFrame() {
        RectProto rectProto = this.visibleFrame_;
        return rectProto == null ? RectProto.getDefaultInstance() : rectProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setVisibleFrame(RectProto value) {
        if (value != null) {
            this.visibleFrame_ = value;
            this.bitField0_ |= 256;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setVisibleFrame(RectProto.Builder builderForValue) {
        this.visibleFrame_ = (RectProto) builderForValue.build();
        this.bitField0_ |= 256;
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
        this.bitField0_ |= 256;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearVisibleFrame() {
        this.visibleFrame_ = null;
        this.bitField0_ &= -257;
    }

    @Override // com.android.server.wm.WindowFramesProtoOrBuilder
    public boolean hasCutout() {
        return (this.bitField0_ & 512) == 512;
    }

    @Override // com.android.server.wm.WindowFramesProtoOrBuilder
    public DisplayCutoutProto getCutout() {
        DisplayCutoutProto displayCutoutProto = this.cutout_;
        return displayCutoutProto == null ? DisplayCutoutProto.getDefaultInstance() : displayCutoutProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setCutout(DisplayCutoutProto value) {
        if (value != null) {
            this.cutout_ = value;
            this.bitField0_ |= 512;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setCutout(DisplayCutoutProto.Builder builderForValue) {
        this.cutout_ = (DisplayCutoutProto) builderForValue.build();
        this.bitField0_ |= 512;
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
        this.bitField0_ |= 512;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearCutout() {
        this.cutout_ = null;
        this.bitField0_ &= -513;
    }

    @Override // com.android.server.wm.WindowFramesProtoOrBuilder
    public boolean hasContentInsets() {
        return (this.bitField0_ & 1024) == 1024;
    }

    @Override // com.android.server.wm.WindowFramesProtoOrBuilder
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

    @Override // com.android.server.wm.WindowFramesProtoOrBuilder
    public boolean hasOverscanInsets() {
        return (this.bitField0_ & 2048) == 2048;
    }

    @Override // com.android.server.wm.WindowFramesProtoOrBuilder
    public RectProto getOverscanInsets() {
        RectProto rectProto = this.overscanInsets_;
        return rectProto == null ? RectProto.getDefaultInstance() : rectProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setOverscanInsets(RectProto value) {
        if (value != null) {
            this.overscanInsets_ = value;
            this.bitField0_ |= 2048;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setOverscanInsets(RectProto.Builder builderForValue) {
        this.overscanInsets_ = (RectProto) builderForValue.build();
        this.bitField0_ |= 2048;
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
        this.bitField0_ |= 2048;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearOverscanInsets() {
        this.overscanInsets_ = null;
        this.bitField0_ &= -2049;
    }

    @Override // com.android.server.wm.WindowFramesProtoOrBuilder
    public boolean hasVisibleInsets() {
        return (this.bitField0_ & 4096) == 4096;
    }

    @Override // com.android.server.wm.WindowFramesProtoOrBuilder
    public RectProto getVisibleInsets() {
        RectProto rectProto = this.visibleInsets_;
        return rectProto == null ? RectProto.getDefaultInstance() : rectProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setVisibleInsets(RectProto value) {
        if (value != null) {
            this.visibleInsets_ = value;
            this.bitField0_ |= 4096;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setVisibleInsets(RectProto.Builder builderForValue) {
        this.visibleInsets_ = (RectProto) builderForValue.build();
        this.bitField0_ |= 4096;
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
        this.bitField0_ |= 4096;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearVisibleInsets() {
        this.visibleInsets_ = null;
        this.bitField0_ &= -4097;
    }

    @Override // com.android.server.wm.WindowFramesProtoOrBuilder
    public boolean hasStableInsets() {
        return (this.bitField0_ & 8192) == 8192;
    }

    @Override // com.android.server.wm.WindowFramesProtoOrBuilder
    public RectProto getStableInsets() {
        RectProto rectProto = this.stableInsets_;
        return rectProto == null ? RectProto.getDefaultInstance() : rectProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setStableInsets(RectProto value) {
        if (value != null) {
            this.stableInsets_ = value;
            this.bitField0_ |= 8192;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setStableInsets(RectProto.Builder builderForValue) {
        this.stableInsets_ = (RectProto) builderForValue.build();
        this.bitField0_ |= 8192;
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
        this.bitField0_ |= 8192;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearStableInsets() {
        this.stableInsets_ = null;
        this.bitField0_ &= -8193;
    }

    @Override // com.android.server.wm.WindowFramesProtoOrBuilder
    public boolean hasOutsets() {
        return (this.bitField0_ & 16384) == 16384;
    }

    @Override // com.android.server.wm.WindowFramesProtoOrBuilder
    public RectProto getOutsets() {
        RectProto rectProto = this.outsets_;
        return rectProto == null ? RectProto.getDefaultInstance() : rectProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setOutsets(RectProto value) {
        if (value != null) {
            this.outsets_ = value;
            this.bitField0_ |= 16384;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setOutsets(RectProto.Builder builderForValue) {
        this.outsets_ = (RectProto) builderForValue.build();
        this.bitField0_ |= 16384;
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
        this.bitField0_ |= 16384;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearOutsets() {
        this.outsets_ = null;
        this.bitField0_ &= -16385;
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeMessage(1, getContainingFrame());
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeMessage(2, getContentFrame());
        }
        if ((this.bitField0_ & 4) == 4) {
            output.writeMessage(3, getDecorFrame());
        }
        if ((this.bitField0_ & 8) == 8) {
            output.writeMessage(4, getDisplayFrame());
        }
        if ((this.bitField0_ & 16) == 16) {
            output.writeMessage(5, getFrame());
        }
        if ((this.bitField0_ & 32) == 32) {
            output.writeMessage(6, getOutsetFrame());
        }
        if ((this.bitField0_ & 64) == 64) {
            output.writeMessage(7, getOverscanFrame());
        }
        if ((this.bitField0_ & 128) == 128) {
            output.writeMessage(8, getParentFrame());
        }
        if ((this.bitField0_ & 256) == 256) {
            output.writeMessage(9, getVisibleFrame());
        }
        if ((this.bitField0_ & 512) == 512) {
            output.writeMessage(10, getCutout());
        }
        if ((this.bitField0_ & 1024) == 1024) {
            output.writeMessage(11, getContentInsets());
        }
        if ((this.bitField0_ & 2048) == 2048) {
            output.writeMessage(12, getOverscanInsets());
        }
        if ((this.bitField0_ & 4096) == 4096) {
            output.writeMessage(13, getVisibleInsets());
        }
        if ((this.bitField0_ & 8192) == 8192) {
            output.writeMessage(14, getStableInsets());
        }
        if ((this.bitField0_ & 16384) == 16384) {
            output.writeMessage(15, getOutsets());
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
            size2 = 0 + CodedOutputStream.computeMessageSize(1, getContainingFrame());
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeMessageSize(2, getContentFrame());
        }
        if ((this.bitField0_ & 4) == 4) {
            size2 += CodedOutputStream.computeMessageSize(3, getDecorFrame());
        }
        if ((this.bitField0_ & 8) == 8) {
            size2 += CodedOutputStream.computeMessageSize(4, getDisplayFrame());
        }
        if ((this.bitField0_ & 16) == 16) {
            size2 += CodedOutputStream.computeMessageSize(5, getFrame());
        }
        if ((this.bitField0_ & 32) == 32) {
            size2 += CodedOutputStream.computeMessageSize(6, getOutsetFrame());
        }
        if ((this.bitField0_ & 64) == 64) {
            size2 += CodedOutputStream.computeMessageSize(7, getOverscanFrame());
        }
        if ((this.bitField0_ & 128) == 128) {
            size2 += CodedOutputStream.computeMessageSize(8, getParentFrame());
        }
        if ((this.bitField0_ & 256) == 256) {
            size2 += CodedOutputStream.computeMessageSize(9, getVisibleFrame());
        }
        if ((this.bitField0_ & 512) == 512) {
            size2 += CodedOutputStream.computeMessageSize(10, getCutout());
        }
        if ((this.bitField0_ & 1024) == 1024) {
            size2 += CodedOutputStream.computeMessageSize(11, getContentInsets());
        }
        if ((this.bitField0_ & 2048) == 2048) {
            size2 += CodedOutputStream.computeMessageSize(12, getOverscanInsets());
        }
        if ((this.bitField0_ & 4096) == 4096) {
            size2 += CodedOutputStream.computeMessageSize(13, getVisibleInsets());
        }
        if ((this.bitField0_ & 8192) == 8192) {
            size2 += CodedOutputStream.computeMessageSize(14, getStableInsets());
        }
        if ((this.bitField0_ & 16384) == 16384) {
            size2 += CodedOutputStream.computeMessageSize(15, getOutsets());
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static WindowFramesProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (WindowFramesProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static WindowFramesProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (WindowFramesProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static WindowFramesProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (WindowFramesProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static WindowFramesProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (WindowFramesProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static WindowFramesProto parseFrom(InputStream input) throws IOException {
        return (WindowFramesProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static WindowFramesProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (WindowFramesProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static WindowFramesProto parseDelimitedFrom(InputStream input) throws IOException {
        return (WindowFramesProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static WindowFramesProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (WindowFramesProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static WindowFramesProto parseFrom(CodedInputStream input) throws IOException {
        return (WindowFramesProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static WindowFramesProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (WindowFramesProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(WindowFramesProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<WindowFramesProto, Builder> implements WindowFramesProtoOrBuilder {
        private Builder() {
            super(WindowFramesProto.DEFAULT_INSTANCE);
        }

        @Override // com.android.server.wm.WindowFramesProtoOrBuilder
        public boolean hasContainingFrame() {
            return ((WindowFramesProto) this.instance).hasContainingFrame();
        }

        @Override // com.android.server.wm.WindowFramesProtoOrBuilder
        public RectProto getContainingFrame() {
            return ((WindowFramesProto) this.instance).getContainingFrame();
        }

        public Builder setContainingFrame(RectProto value) {
            copyOnWrite();
            ((WindowFramesProto) this.instance).setContainingFrame((WindowFramesProto) value);
            return this;
        }

        public Builder setContainingFrame(RectProto.Builder builderForValue) {
            copyOnWrite();
            ((WindowFramesProto) this.instance).setContainingFrame((WindowFramesProto) builderForValue);
            return this;
        }

        public Builder mergeContainingFrame(RectProto value) {
            copyOnWrite();
            ((WindowFramesProto) this.instance).mergeContainingFrame(value);
            return this;
        }

        public Builder clearContainingFrame() {
            copyOnWrite();
            ((WindowFramesProto) this.instance).clearContainingFrame();
            return this;
        }

        @Override // com.android.server.wm.WindowFramesProtoOrBuilder
        public boolean hasContentFrame() {
            return ((WindowFramesProto) this.instance).hasContentFrame();
        }

        @Override // com.android.server.wm.WindowFramesProtoOrBuilder
        public RectProto getContentFrame() {
            return ((WindowFramesProto) this.instance).getContentFrame();
        }

        public Builder setContentFrame(RectProto value) {
            copyOnWrite();
            ((WindowFramesProto) this.instance).setContentFrame((WindowFramesProto) value);
            return this;
        }

        public Builder setContentFrame(RectProto.Builder builderForValue) {
            copyOnWrite();
            ((WindowFramesProto) this.instance).setContentFrame((WindowFramesProto) builderForValue);
            return this;
        }

        public Builder mergeContentFrame(RectProto value) {
            copyOnWrite();
            ((WindowFramesProto) this.instance).mergeContentFrame(value);
            return this;
        }

        public Builder clearContentFrame() {
            copyOnWrite();
            ((WindowFramesProto) this.instance).clearContentFrame();
            return this;
        }

        @Override // com.android.server.wm.WindowFramesProtoOrBuilder
        public boolean hasDecorFrame() {
            return ((WindowFramesProto) this.instance).hasDecorFrame();
        }

        @Override // com.android.server.wm.WindowFramesProtoOrBuilder
        public RectProto getDecorFrame() {
            return ((WindowFramesProto) this.instance).getDecorFrame();
        }

        public Builder setDecorFrame(RectProto value) {
            copyOnWrite();
            ((WindowFramesProto) this.instance).setDecorFrame((WindowFramesProto) value);
            return this;
        }

        public Builder setDecorFrame(RectProto.Builder builderForValue) {
            copyOnWrite();
            ((WindowFramesProto) this.instance).setDecorFrame((WindowFramesProto) builderForValue);
            return this;
        }

        public Builder mergeDecorFrame(RectProto value) {
            copyOnWrite();
            ((WindowFramesProto) this.instance).mergeDecorFrame(value);
            return this;
        }

        public Builder clearDecorFrame() {
            copyOnWrite();
            ((WindowFramesProto) this.instance).clearDecorFrame();
            return this;
        }

        @Override // com.android.server.wm.WindowFramesProtoOrBuilder
        public boolean hasDisplayFrame() {
            return ((WindowFramesProto) this.instance).hasDisplayFrame();
        }

        @Override // com.android.server.wm.WindowFramesProtoOrBuilder
        public RectProto getDisplayFrame() {
            return ((WindowFramesProto) this.instance).getDisplayFrame();
        }

        public Builder setDisplayFrame(RectProto value) {
            copyOnWrite();
            ((WindowFramesProto) this.instance).setDisplayFrame((WindowFramesProto) value);
            return this;
        }

        public Builder setDisplayFrame(RectProto.Builder builderForValue) {
            copyOnWrite();
            ((WindowFramesProto) this.instance).setDisplayFrame((WindowFramesProto) builderForValue);
            return this;
        }

        public Builder mergeDisplayFrame(RectProto value) {
            copyOnWrite();
            ((WindowFramesProto) this.instance).mergeDisplayFrame(value);
            return this;
        }

        public Builder clearDisplayFrame() {
            copyOnWrite();
            ((WindowFramesProto) this.instance).clearDisplayFrame();
            return this;
        }

        @Override // com.android.server.wm.WindowFramesProtoOrBuilder
        public boolean hasFrame() {
            return ((WindowFramesProto) this.instance).hasFrame();
        }

        @Override // com.android.server.wm.WindowFramesProtoOrBuilder
        public RectProto getFrame() {
            return ((WindowFramesProto) this.instance).getFrame();
        }

        public Builder setFrame(RectProto value) {
            copyOnWrite();
            ((WindowFramesProto) this.instance).setFrame((WindowFramesProto) value);
            return this;
        }

        public Builder setFrame(RectProto.Builder builderForValue) {
            copyOnWrite();
            ((WindowFramesProto) this.instance).setFrame((WindowFramesProto) builderForValue);
            return this;
        }

        public Builder mergeFrame(RectProto value) {
            copyOnWrite();
            ((WindowFramesProto) this.instance).mergeFrame(value);
            return this;
        }

        public Builder clearFrame() {
            copyOnWrite();
            ((WindowFramesProto) this.instance).clearFrame();
            return this;
        }

        @Override // com.android.server.wm.WindowFramesProtoOrBuilder
        public boolean hasOutsetFrame() {
            return ((WindowFramesProto) this.instance).hasOutsetFrame();
        }

        @Override // com.android.server.wm.WindowFramesProtoOrBuilder
        public RectProto getOutsetFrame() {
            return ((WindowFramesProto) this.instance).getOutsetFrame();
        }

        public Builder setOutsetFrame(RectProto value) {
            copyOnWrite();
            ((WindowFramesProto) this.instance).setOutsetFrame((WindowFramesProto) value);
            return this;
        }

        public Builder setOutsetFrame(RectProto.Builder builderForValue) {
            copyOnWrite();
            ((WindowFramesProto) this.instance).setOutsetFrame((WindowFramesProto) builderForValue);
            return this;
        }

        public Builder mergeOutsetFrame(RectProto value) {
            copyOnWrite();
            ((WindowFramesProto) this.instance).mergeOutsetFrame(value);
            return this;
        }

        public Builder clearOutsetFrame() {
            copyOnWrite();
            ((WindowFramesProto) this.instance).clearOutsetFrame();
            return this;
        }

        @Override // com.android.server.wm.WindowFramesProtoOrBuilder
        public boolean hasOverscanFrame() {
            return ((WindowFramesProto) this.instance).hasOverscanFrame();
        }

        @Override // com.android.server.wm.WindowFramesProtoOrBuilder
        public RectProto getOverscanFrame() {
            return ((WindowFramesProto) this.instance).getOverscanFrame();
        }

        public Builder setOverscanFrame(RectProto value) {
            copyOnWrite();
            ((WindowFramesProto) this.instance).setOverscanFrame((WindowFramesProto) value);
            return this;
        }

        public Builder setOverscanFrame(RectProto.Builder builderForValue) {
            copyOnWrite();
            ((WindowFramesProto) this.instance).setOverscanFrame((WindowFramesProto) builderForValue);
            return this;
        }

        public Builder mergeOverscanFrame(RectProto value) {
            copyOnWrite();
            ((WindowFramesProto) this.instance).mergeOverscanFrame(value);
            return this;
        }

        public Builder clearOverscanFrame() {
            copyOnWrite();
            ((WindowFramesProto) this.instance).clearOverscanFrame();
            return this;
        }

        @Override // com.android.server.wm.WindowFramesProtoOrBuilder
        public boolean hasParentFrame() {
            return ((WindowFramesProto) this.instance).hasParentFrame();
        }

        @Override // com.android.server.wm.WindowFramesProtoOrBuilder
        public RectProto getParentFrame() {
            return ((WindowFramesProto) this.instance).getParentFrame();
        }

        public Builder setParentFrame(RectProto value) {
            copyOnWrite();
            ((WindowFramesProto) this.instance).setParentFrame((WindowFramesProto) value);
            return this;
        }

        public Builder setParentFrame(RectProto.Builder builderForValue) {
            copyOnWrite();
            ((WindowFramesProto) this.instance).setParentFrame((WindowFramesProto) builderForValue);
            return this;
        }

        public Builder mergeParentFrame(RectProto value) {
            copyOnWrite();
            ((WindowFramesProto) this.instance).mergeParentFrame(value);
            return this;
        }

        public Builder clearParentFrame() {
            copyOnWrite();
            ((WindowFramesProto) this.instance).clearParentFrame();
            return this;
        }

        @Override // com.android.server.wm.WindowFramesProtoOrBuilder
        public boolean hasVisibleFrame() {
            return ((WindowFramesProto) this.instance).hasVisibleFrame();
        }

        @Override // com.android.server.wm.WindowFramesProtoOrBuilder
        public RectProto getVisibleFrame() {
            return ((WindowFramesProto) this.instance).getVisibleFrame();
        }

        public Builder setVisibleFrame(RectProto value) {
            copyOnWrite();
            ((WindowFramesProto) this.instance).setVisibleFrame((WindowFramesProto) value);
            return this;
        }

        public Builder setVisibleFrame(RectProto.Builder builderForValue) {
            copyOnWrite();
            ((WindowFramesProto) this.instance).setVisibleFrame((WindowFramesProto) builderForValue);
            return this;
        }

        public Builder mergeVisibleFrame(RectProto value) {
            copyOnWrite();
            ((WindowFramesProto) this.instance).mergeVisibleFrame(value);
            return this;
        }

        public Builder clearVisibleFrame() {
            copyOnWrite();
            ((WindowFramesProto) this.instance).clearVisibleFrame();
            return this;
        }

        @Override // com.android.server.wm.WindowFramesProtoOrBuilder
        public boolean hasCutout() {
            return ((WindowFramesProto) this.instance).hasCutout();
        }

        @Override // com.android.server.wm.WindowFramesProtoOrBuilder
        public DisplayCutoutProto getCutout() {
            return ((WindowFramesProto) this.instance).getCutout();
        }

        public Builder setCutout(DisplayCutoutProto value) {
            copyOnWrite();
            ((WindowFramesProto) this.instance).setCutout((WindowFramesProto) value);
            return this;
        }

        public Builder setCutout(DisplayCutoutProto.Builder builderForValue) {
            copyOnWrite();
            ((WindowFramesProto) this.instance).setCutout((WindowFramesProto) builderForValue);
            return this;
        }

        public Builder mergeCutout(DisplayCutoutProto value) {
            copyOnWrite();
            ((WindowFramesProto) this.instance).mergeCutout(value);
            return this;
        }

        public Builder clearCutout() {
            copyOnWrite();
            ((WindowFramesProto) this.instance).clearCutout();
            return this;
        }

        @Override // com.android.server.wm.WindowFramesProtoOrBuilder
        public boolean hasContentInsets() {
            return ((WindowFramesProto) this.instance).hasContentInsets();
        }

        @Override // com.android.server.wm.WindowFramesProtoOrBuilder
        public RectProto getContentInsets() {
            return ((WindowFramesProto) this.instance).getContentInsets();
        }

        public Builder setContentInsets(RectProto value) {
            copyOnWrite();
            ((WindowFramesProto) this.instance).setContentInsets((WindowFramesProto) value);
            return this;
        }

        public Builder setContentInsets(RectProto.Builder builderForValue) {
            copyOnWrite();
            ((WindowFramesProto) this.instance).setContentInsets((WindowFramesProto) builderForValue);
            return this;
        }

        public Builder mergeContentInsets(RectProto value) {
            copyOnWrite();
            ((WindowFramesProto) this.instance).mergeContentInsets(value);
            return this;
        }

        public Builder clearContentInsets() {
            copyOnWrite();
            ((WindowFramesProto) this.instance).clearContentInsets();
            return this;
        }

        @Override // com.android.server.wm.WindowFramesProtoOrBuilder
        public boolean hasOverscanInsets() {
            return ((WindowFramesProto) this.instance).hasOverscanInsets();
        }

        @Override // com.android.server.wm.WindowFramesProtoOrBuilder
        public RectProto getOverscanInsets() {
            return ((WindowFramesProto) this.instance).getOverscanInsets();
        }

        public Builder setOverscanInsets(RectProto value) {
            copyOnWrite();
            ((WindowFramesProto) this.instance).setOverscanInsets((WindowFramesProto) value);
            return this;
        }

        public Builder setOverscanInsets(RectProto.Builder builderForValue) {
            copyOnWrite();
            ((WindowFramesProto) this.instance).setOverscanInsets((WindowFramesProto) builderForValue);
            return this;
        }

        public Builder mergeOverscanInsets(RectProto value) {
            copyOnWrite();
            ((WindowFramesProto) this.instance).mergeOverscanInsets(value);
            return this;
        }

        public Builder clearOverscanInsets() {
            copyOnWrite();
            ((WindowFramesProto) this.instance).clearOverscanInsets();
            return this;
        }

        @Override // com.android.server.wm.WindowFramesProtoOrBuilder
        public boolean hasVisibleInsets() {
            return ((WindowFramesProto) this.instance).hasVisibleInsets();
        }

        @Override // com.android.server.wm.WindowFramesProtoOrBuilder
        public RectProto getVisibleInsets() {
            return ((WindowFramesProto) this.instance).getVisibleInsets();
        }

        public Builder setVisibleInsets(RectProto value) {
            copyOnWrite();
            ((WindowFramesProto) this.instance).setVisibleInsets((WindowFramesProto) value);
            return this;
        }

        public Builder setVisibleInsets(RectProto.Builder builderForValue) {
            copyOnWrite();
            ((WindowFramesProto) this.instance).setVisibleInsets((WindowFramesProto) builderForValue);
            return this;
        }

        public Builder mergeVisibleInsets(RectProto value) {
            copyOnWrite();
            ((WindowFramesProto) this.instance).mergeVisibleInsets(value);
            return this;
        }

        public Builder clearVisibleInsets() {
            copyOnWrite();
            ((WindowFramesProto) this.instance).clearVisibleInsets();
            return this;
        }

        @Override // com.android.server.wm.WindowFramesProtoOrBuilder
        public boolean hasStableInsets() {
            return ((WindowFramesProto) this.instance).hasStableInsets();
        }

        @Override // com.android.server.wm.WindowFramesProtoOrBuilder
        public RectProto getStableInsets() {
            return ((WindowFramesProto) this.instance).getStableInsets();
        }

        public Builder setStableInsets(RectProto value) {
            copyOnWrite();
            ((WindowFramesProto) this.instance).setStableInsets((WindowFramesProto) value);
            return this;
        }

        public Builder setStableInsets(RectProto.Builder builderForValue) {
            copyOnWrite();
            ((WindowFramesProto) this.instance).setStableInsets((WindowFramesProto) builderForValue);
            return this;
        }

        public Builder mergeStableInsets(RectProto value) {
            copyOnWrite();
            ((WindowFramesProto) this.instance).mergeStableInsets(value);
            return this;
        }

        public Builder clearStableInsets() {
            copyOnWrite();
            ((WindowFramesProto) this.instance).clearStableInsets();
            return this;
        }

        @Override // com.android.server.wm.WindowFramesProtoOrBuilder
        public boolean hasOutsets() {
            return ((WindowFramesProto) this.instance).hasOutsets();
        }

        @Override // com.android.server.wm.WindowFramesProtoOrBuilder
        public RectProto getOutsets() {
            return ((WindowFramesProto) this.instance).getOutsets();
        }

        public Builder setOutsets(RectProto value) {
            copyOnWrite();
            ((WindowFramesProto) this.instance).setOutsets((WindowFramesProto) value);
            return this;
        }

        public Builder setOutsets(RectProto.Builder builderForValue) {
            copyOnWrite();
            ((WindowFramesProto) this.instance).setOutsets((WindowFramesProto) builderForValue);
            return this;
        }

        public Builder mergeOutsets(RectProto value) {
            copyOnWrite();
            ((WindowFramesProto) this.instance).mergeOutsets(value);
            return this;
        }

        public Builder clearOutsets() {
            copyOnWrite();
            ((WindowFramesProto) this.instance).clearOutsets();
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new WindowFramesProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                WindowFramesProto other = (WindowFramesProto) arg1;
                this.containingFrame_ = (RectProto) visitor.visitMessage(this.containingFrame_, other.containingFrame_);
                this.contentFrame_ = (RectProto) visitor.visitMessage(this.contentFrame_, other.contentFrame_);
                this.decorFrame_ = (RectProto) visitor.visitMessage(this.decorFrame_, other.decorFrame_);
                this.displayFrame_ = (RectProto) visitor.visitMessage(this.displayFrame_, other.displayFrame_);
                this.frame_ = (RectProto) visitor.visitMessage(this.frame_, other.frame_);
                this.outsetFrame_ = (RectProto) visitor.visitMessage(this.outsetFrame_, other.outsetFrame_);
                this.overscanFrame_ = (RectProto) visitor.visitMessage(this.overscanFrame_, other.overscanFrame_);
                this.parentFrame_ = (RectProto) visitor.visitMessage(this.parentFrame_, other.parentFrame_);
                this.visibleFrame_ = (RectProto) visitor.visitMessage(this.visibleFrame_, other.visibleFrame_);
                this.cutout_ = (DisplayCutoutProto) visitor.visitMessage(this.cutout_, other.cutout_);
                this.contentInsets_ = (RectProto) visitor.visitMessage(this.contentInsets_, other.contentInsets_);
                this.overscanInsets_ = (RectProto) visitor.visitMessage(this.overscanInsets_, other.overscanInsets_);
                this.visibleInsets_ = (RectProto) visitor.visitMessage(this.visibleInsets_, other.visibleInsets_);
                this.stableInsets_ = (RectProto) visitor.visitMessage(this.stableInsets_, other.stableInsets_);
                this.outsets_ = (RectProto) visitor.visitMessage(this.outsets_, other.outsets_);
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
                            case 10:
                                RectProto.Builder subBuilder = null;
                                if ((this.bitField0_ & 1) == 1) {
                                    subBuilder = (RectProto.Builder) this.containingFrame_.toBuilder();
                                }
                                this.containingFrame_ = (RectProto) input.readMessage(RectProto.parser(), extensionRegistry);
                                if (subBuilder != null) {
                                    subBuilder.mergeFrom((GeneratedMessageLite) this.containingFrame_);
                                    this.containingFrame_ = (RectProto) subBuilder.buildPartial();
                                }
                                this.bitField0_ |= 1;
                                break;
                            case 18:
                                RectProto.Builder subBuilder2 = null;
                                if ((this.bitField0_ & 2) == 2) {
                                    subBuilder2 = (RectProto.Builder) this.contentFrame_.toBuilder();
                                }
                                this.contentFrame_ = (RectProto) input.readMessage(RectProto.parser(), extensionRegistry);
                                if (subBuilder2 != null) {
                                    subBuilder2.mergeFrom((GeneratedMessageLite) this.contentFrame_);
                                    this.contentFrame_ = (RectProto) subBuilder2.buildPartial();
                                }
                                this.bitField0_ |= 2;
                                break;
                            case 26:
                                RectProto.Builder subBuilder3 = null;
                                if ((this.bitField0_ & 4) == 4) {
                                    subBuilder3 = (RectProto.Builder) this.decorFrame_.toBuilder();
                                }
                                this.decorFrame_ = (RectProto) input.readMessage(RectProto.parser(), extensionRegistry);
                                if (subBuilder3 != null) {
                                    subBuilder3.mergeFrom((GeneratedMessageLite) this.decorFrame_);
                                    this.decorFrame_ = (RectProto) subBuilder3.buildPartial();
                                }
                                this.bitField0_ |= 4;
                                break;
                            case 34:
                                RectProto.Builder subBuilder4 = null;
                                if ((this.bitField0_ & 8) == 8) {
                                    subBuilder4 = (RectProto.Builder) this.displayFrame_.toBuilder();
                                }
                                this.displayFrame_ = (RectProto) input.readMessage(RectProto.parser(), extensionRegistry);
                                if (subBuilder4 != null) {
                                    subBuilder4.mergeFrom((GeneratedMessageLite) this.displayFrame_);
                                    this.displayFrame_ = (RectProto) subBuilder4.buildPartial();
                                }
                                this.bitField0_ |= 8;
                                break;
                            case 42:
                                RectProto.Builder subBuilder5 = null;
                                if ((this.bitField0_ & 16) == 16) {
                                    subBuilder5 = (RectProto.Builder) this.frame_.toBuilder();
                                }
                                this.frame_ = (RectProto) input.readMessage(RectProto.parser(), extensionRegistry);
                                if (subBuilder5 != null) {
                                    subBuilder5.mergeFrom((GeneratedMessageLite) this.frame_);
                                    this.frame_ = (RectProto) subBuilder5.buildPartial();
                                }
                                this.bitField0_ |= 16;
                                break;
                            case 50:
                                RectProto.Builder subBuilder6 = null;
                                if ((this.bitField0_ & 32) == 32) {
                                    subBuilder6 = (RectProto.Builder) this.outsetFrame_.toBuilder();
                                }
                                this.outsetFrame_ = (RectProto) input.readMessage(RectProto.parser(), extensionRegistry);
                                if (subBuilder6 != null) {
                                    subBuilder6.mergeFrom((GeneratedMessageLite) this.outsetFrame_);
                                    this.outsetFrame_ = (RectProto) subBuilder6.buildPartial();
                                }
                                this.bitField0_ |= 32;
                                break;
                            case 58:
                                RectProto.Builder subBuilder7 = null;
                                if ((this.bitField0_ & 64) == 64) {
                                    subBuilder7 = (RectProto.Builder) this.overscanFrame_.toBuilder();
                                }
                                this.overscanFrame_ = (RectProto) input.readMessage(RectProto.parser(), extensionRegistry);
                                if (subBuilder7 != null) {
                                    subBuilder7.mergeFrom((GeneratedMessageLite) this.overscanFrame_);
                                    this.overscanFrame_ = (RectProto) subBuilder7.buildPartial();
                                }
                                this.bitField0_ |= 64;
                                break;
                            case 66:
                                RectProto.Builder subBuilder8 = null;
                                if ((this.bitField0_ & 128) == 128) {
                                    subBuilder8 = (RectProto.Builder) this.parentFrame_.toBuilder();
                                }
                                this.parentFrame_ = (RectProto) input.readMessage(RectProto.parser(), extensionRegistry);
                                if (subBuilder8 != null) {
                                    subBuilder8.mergeFrom((GeneratedMessageLite) this.parentFrame_);
                                    this.parentFrame_ = (RectProto) subBuilder8.buildPartial();
                                }
                                this.bitField0_ |= 128;
                                break;
                            case 74:
                                RectProto.Builder subBuilder9 = null;
                                if ((this.bitField0_ & 256) == 256) {
                                    subBuilder9 = (RectProto.Builder) this.visibleFrame_.toBuilder();
                                }
                                this.visibleFrame_ = (RectProto) input.readMessage(RectProto.parser(), extensionRegistry);
                                if (subBuilder9 != null) {
                                    subBuilder9.mergeFrom((GeneratedMessageLite) this.visibleFrame_);
                                    this.visibleFrame_ = (RectProto) subBuilder9.buildPartial();
                                }
                                this.bitField0_ |= 256;
                                break;
                            case 82:
                                DisplayCutoutProto.Builder subBuilder10 = null;
                                if ((this.bitField0_ & 512) == 512) {
                                    subBuilder10 = (DisplayCutoutProto.Builder) this.cutout_.toBuilder();
                                }
                                this.cutout_ = (DisplayCutoutProto) input.readMessage(DisplayCutoutProto.parser(), extensionRegistry);
                                if (subBuilder10 != null) {
                                    subBuilder10.mergeFrom((GeneratedMessageLite) this.cutout_);
                                    this.cutout_ = (DisplayCutoutProto) subBuilder10.buildPartial();
                                }
                                this.bitField0_ |= 512;
                                break;
                            case 90:
                                RectProto.Builder subBuilder11 = null;
                                if ((this.bitField0_ & 1024) == 1024) {
                                    subBuilder11 = (RectProto.Builder) this.contentInsets_.toBuilder();
                                }
                                this.contentInsets_ = (RectProto) input.readMessage(RectProto.parser(), extensionRegistry);
                                if (subBuilder11 != null) {
                                    subBuilder11.mergeFrom((GeneratedMessageLite) this.contentInsets_);
                                    this.contentInsets_ = (RectProto) subBuilder11.buildPartial();
                                }
                                this.bitField0_ |= 1024;
                                break;
                            case 98:
                                RectProto.Builder subBuilder12 = null;
                                if ((this.bitField0_ & 2048) == 2048) {
                                    subBuilder12 = (RectProto.Builder) this.overscanInsets_.toBuilder();
                                }
                                this.overscanInsets_ = (RectProto) input.readMessage(RectProto.parser(), extensionRegistry);
                                if (subBuilder12 != null) {
                                    subBuilder12.mergeFrom((GeneratedMessageLite) this.overscanInsets_);
                                    this.overscanInsets_ = (RectProto) subBuilder12.buildPartial();
                                }
                                this.bitField0_ |= 2048;
                                break;
                            case 106:
                                RectProto.Builder subBuilder13 = null;
                                if ((this.bitField0_ & 4096) == 4096) {
                                    subBuilder13 = (RectProto.Builder) this.visibleInsets_.toBuilder();
                                }
                                this.visibleInsets_ = (RectProto) input.readMessage(RectProto.parser(), extensionRegistry);
                                if (subBuilder13 != null) {
                                    subBuilder13.mergeFrom((GeneratedMessageLite) this.visibleInsets_);
                                    this.visibleInsets_ = (RectProto) subBuilder13.buildPartial();
                                }
                                this.bitField0_ |= 4096;
                                break;
                            case 114:
                                RectProto.Builder subBuilder14 = null;
                                if ((this.bitField0_ & 8192) == 8192) {
                                    subBuilder14 = (RectProto.Builder) this.stableInsets_.toBuilder();
                                }
                                this.stableInsets_ = (RectProto) input.readMessage(RectProto.parser(), extensionRegistry);
                                if (subBuilder14 != null) {
                                    subBuilder14.mergeFrom((GeneratedMessageLite) this.stableInsets_);
                                    this.stableInsets_ = (RectProto) subBuilder14.buildPartial();
                                }
                                this.bitField0_ |= 8192;
                                break;
                            case 122:
                                RectProto.Builder subBuilder15 = null;
                                if ((this.bitField0_ & 16384) == 16384) {
                                    subBuilder15 = (RectProto.Builder) this.outsets_.toBuilder();
                                }
                                this.outsets_ = (RectProto) input.readMessage(RectProto.parser(), extensionRegistry);
                                if (subBuilder15 != null) {
                                    subBuilder15.mergeFrom((GeneratedMessageLite) this.outsets_);
                                    this.outsets_ = (RectProto) subBuilder15.buildPartial();
                                }
                                this.bitField0_ |= 16384;
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
                    synchronized (WindowFramesProto.class) {
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

    public static WindowFramesProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<WindowFramesProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
