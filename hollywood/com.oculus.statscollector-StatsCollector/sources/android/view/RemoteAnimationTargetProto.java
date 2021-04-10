package android.view;

import android.app.WindowConfigurationProto;
import android.graphics.PointProto;
import android.graphics.RectProto;
import android.view.SurfaceControlProto;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;

public final class RemoteAnimationTargetProto extends GeneratedMessageLite<RemoteAnimationTargetProto, Builder> implements RemoteAnimationTargetProtoOrBuilder {
    public static final int CLIP_RECT_FIELD_NUMBER = 5;
    public static final int CONTENT_INSETS_FIELD_NUMBER = 6;
    private static final RemoteAnimationTargetProto DEFAULT_INSTANCE = new RemoteAnimationTargetProto();
    public static final int IS_TRANSLUCENT_FIELD_NUMBER = 4;
    public static final int LEASH_FIELD_NUMBER = 3;
    public static final int MODE_FIELD_NUMBER = 2;
    private static volatile Parser<RemoteAnimationTargetProto> PARSER = null;
    public static final int POSITION_FIELD_NUMBER = 8;
    public static final int PREFIX_ORDER_INDEX_FIELD_NUMBER = 7;
    public static final int SOURCE_CONTAINER_BOUNDS_FIELD_NUMBER = 9;
    public static final int START_BOUNDS_FIELD_NUMBER = 12;
    public static final int START_LEASH_FIELD_NUMBER = 11;
    public static final int TASK_ID_FIELD_NUMBER = 1;
    public static final int WINDOW_CONFIGURATION_FIELD_NUMBER = 10;
    private int bitField0_;
    private RectProto clipRect_;
    private RectProto contentInsets_;
    private boolean isTranslucent_ = false;
    private SurfaceControlProto leash_;
    private int mode_ = 0;
    private PointProto position_;
    private int prefixOrderIndex_ = 0;
    private RectProto sourceContainerBounds_;
    private RectProto startBounds_;
    private SurfaceControlProto startLeash_;
    private int taskId_ = 0;
    private WindowConfigurationProto windowConfiguration_;

    private RemoteAnimationTargetProto() {
    }

    @Override // android.view.RemoteAnimationTargetProtoOrBuilder
    public boolean hasTaskId() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // android.view.RemoteAnimationTargetProtoOrBuilder
    public int getTaskId() {
        return this.taskId_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setTaskId(int value) {
        this.bitField0_ |= 1;
        this.taskId_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearTaskId() {
        this.bitField0_ &= -2;
        this.taskId_ = 0;
    }

    @Override // android.view.RemoteAnimationTargetProtoOrBuilder
    public boolean hasMode() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // android.view.RemoteAnimationTargetProtoOrBuilder
    public int getMode() {
        return this.mode_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setMode(int value) {
        this.bitField0_ |= 2;
        this.mode_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearMode() {
        this.bitField0_ &= -3;
        this.mode_ = 0;
    }

    @Override // android.view.RemoteAnimationTargetProtoOrBuilder
    public boolean hasLeash() {
        return (this.bitField0_ & 4) == 4;
    }

    @Override // android.view.RemoteAnimationTargetProtoOrBuilder
    public SurfaceControlProto getLeash() {
        SurfaceControlProto surfaceControlProto = this.leash_;
        return surfaceControlProto == null ? SurfaceControlProto.getDefaultInstance() : surfaceControlProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setLeash(SurfaceControlProto value) {
        if (value != null) {
            this.leash_ = value;
            this.bitField0_ |= 4;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setLeash(SurfaceControlProto.Builder builderForValue) {
        this.leash_ = (SurfaceControlProto) builderForValue.build();
        this.bitField0_ |= 4;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeLeash(SurfaceControlProto value) {
        SurfaceControlProto surfaceControlProto = this.leash_;
        if (surfaceControlProto == null || surfaceControlProto == SurfaceControlProto.getDefaultInstance()) {
            this.leash_ = value;
        } else {
            this.leash_ = (SurfaceControlProto) ((SurfaceControlProto.Builder) SurfaceControlProto.newBuilder(this.leash_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 4;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearLeash() {
        this.leash_ = null;
        this.bitField0_ &= -5;
    }

    @Override // android.view.RemoteAnimationTargetProtoOrBuilder
    public boolean hasIsTranslucent() {
        return (this.bitField0_ & 8) == 8;
    }

    @Override // android.view.RemoteAnimationTargetProtoOrBuilder
    public boolean getIsTranslucent() {
        return this.isTranslucent_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setIsTranslucent(boolean value) {
        this.bitField0_ |= 8;
        this.isTranslucent_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearIsTranslucent() {
        this.bitField0_ &= -9;
        this.isTranslucent_ = false;
    }

    @Override // android.view.RemoteAnimationTargetProtoOrBuilder
    public boolean hasClipRect() {
        return (this.bitField0_ & 16) == 16;
    }

    @Override // android.view.RemoteAnimationTargetProtoOrBuilder
    public RectProto getClipRect() {
        RectProto rectProto = this.clipRect_;
        return rectProto == null ? RectProto.getDefaultInstance() : rectProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setClipRect(RectProto value) {
        if (value != null) {
            this.clipRect_ = value;
            this.bitField0_ |= 16;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setClipRect(RectProto.Builder builderForValue) {
        this.clipRect_ = (RectProto) builderForValue.build();
        this.bitField0_ |= 16;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeClipRect(RectProto value) {
        RectProto rectProto = this.clipRect_;
        if (rectProto == null || rectProto == RectProto.getDefaultInstance()) {
            this.clipRect_ = value;
        } else {
            this.clipRect_ = (RectProto) ((RectProto.Builder) RectProto.newBuilder(this.clipRect_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 16;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearClipRect() {
        this.clipRect_ = null;
        this.bitField0_ &= -17;
    }

    @Override // android.view.RemoteAnimationTargetProtoOrBuilder
    public boolean hasContentInsets() {
        return (this.bitField0_ & 32) == 32;
    }

    @Override // android.view.RemoteAnimationTargetProtoOrBuilder
    public RectProto getContentInsets() {
        RectProto rectProto = this.contentInsets_;
        return rectProto == null ? RectProto.getDefaultInstance() : rectProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setContentInsets(RectProto value) {
        if (value != null) {
            this.contentInsets_ = value;
            this.bitField0_ |= 32;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setContentInsets(RectProto.Builder builderForValue) {
        this.contentInsets_ = (RectProto) builderForValue.build();
        this.bitField0_ |= 32;
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
        this.bitField0_ |= 32;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearContentInsets() {
        this.contentInsets_ = null;
        this.bitField0_ &= -33;
    }

    @Override // android.view.RemoteAnimationTargetProtoOrBuilder
    public boolean hasPrefixOrderIndex() {
        return (this.bitField0_ & 64) == 64;
    }

    @Override // android.view.RemoteAnimationTargetProtoOrBuilder
    public int getPrefixOrderIndex() {
        return this.prefixOrderIndex_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPrefixOrderIndex(int value) {
        this.bitField0_ |= 64;
        this.prefixOrderIndex_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearPrefixOrderIndex() {
        this.bitField0_ &= -65;
        this.prefixOrderIndex_ = 0;
    }

    @Override // android.view.RemoteAnimationTargetProtoOrBuilder
    public boolean hasPosition() {
        return (this.bitField0_ & 128) == 128;
    }

    @Override // android.view.RemoteAnimationTargetProtoOrBuilder
    public PointProto getPosition() {
        PointProto pointProto = this.position_;
        return pointProto == null ? PointProto.getDefaultInstance() : pointProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPosition(PointProto value) {
        if (value != null) {
            this.position_ = value;
            this.bitField0_ |= 128;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPosition(PointProto.Builder builderForValue) {
        this.position_ = (PointProto) builderForValue.build();
        this.bitField0_ |= 128;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergePosition(PointProto value) {
        PointProto pointProto = this.position_;
        if (pointProto == null || pointProto == PointProto.getDefaultInstance()) {
            this.position_ = value;
        } else {
            this.position_ = (PointProto) ((PointProto.Builder) PointProto.newBuilder(this.position_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 128;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearPosition() {
        this.position_ = null;
        this.bitField0_ &= -129;
    }

    @Override // android.view.RemoteAnimationTargetProtoOrBuilder
    public boolean hasSourceContainerBounds() {
        return (this.bitField0_ & 256) == 256;
    }

    @Override // android.view.RemoteAnimationTargetProtoOrBuilder
    public RectProto getSourceContainerBounds() {
        RectProto rectProto = this.sourceContainerBounds_;
        return rectProto == null ? RectProto.getDefaultInstance() : rectProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setSourceContainerBounds(RectProto value) {
        if (value != null) {
            this.sourceContainerBounds_ = value;
            this.bitField0_ |= 256;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setSourceContainerBounds(RectProto.Builder builderForValue) {
        this.sourceContainerBounds_ = (RectProto) builderForValue.build();
        this.bitField0_ |= 256;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeSourceContainerBounds(RectProto value) {
        RectProto rectProto = this.sourceContainerBounds_;
        if (rectProto == null || rectProto == RectProto.getDefaultInstance()) {
            this.sourceContainerBounds_ = value;
        } else {
            this.sourceContainerBounds_ = (RectProto) ((RectProto.Builder) RectProto.newBuilder(this.sourceContainerBounds_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 256;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearSourceContainerBounds() {
        this.sourceContainerBounds_ = null;
        this.bitField0_ &= -257;
    }

    @Override // android.view.RemoteAnimationTargetProtoOrBuilder
    public boolean hasWindowConfiguration() {
        return (this.bitField0_ & 512) == 512;
    }

    @Override // android.view.RemoteAnimationTargetProtoOrBuilder
    public WindowConfigurationProto getWindowConfiguration() {
        WindowConfigurationProto windowConfigurationProto = this.windowConfiguration_;
        return windowConfigurationProto == null ? WindowConfigurationProto.getDefaultInstance() : windowConfigurationProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setWindowConfiguration(WindowConfigurationProto value) {
        if (value != null) {
            this.windowConfiguration_ = value;
            this.bitField0_ |= 512;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setWindowConfiguration(WindowConfigurationProto.Builder builderForValue) {
        this.windowConfiguration_ = (WindowConfigurationProto) builderForValue.build();
        this.bitField0_ |= 512;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeWindowConfiguration(WindowConfigurationProto value) {
        WindowConfigurationProto windowConfigurationProto = this.windowConfiguration_;
        if (windowConfigurationProto == null || windowConfigurationProto == WindowConfigurationProto.getDefaultInstance()) {
            this.windowConfiguration_ = value;
        } else {
            this.windowConfiguration_ = (WindowConfigurationProto) ((WindowConfigurationProto.Builder) WindowConfigurationProto.newBuilder(this.windowConfiguration_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 512;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearWindowConfiguration() {
        this.windowConfiguration_ = null;
        this.bitField0_ &= -513;
    }

    @Override // android.view.RemoteAnimationTargetProtoOrBuilder
    public boolean hasStartLeash() {
        return (this.bitField0_ & 1024) == 1024;
    }

    @Override // android.view.RemoteAnimationTargetProtoOrBuilder
    public SurfaceControlProto getStartLeash() {
        SurfaceControlProto surfaceControlProto = this.startLeash_;
        return surfaceControlProto == null ? SurfaceControlProto.getDefaultInstance() : surfaceControlProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setStartLeash(SurfaceControlProto value) {
        if (value != null) {
            this.startLeash_ = value;
            this.bitField0_ |= 1024;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setStartLeash(SurfaceControlProto.Builder builderForValue) {
        this.startLeash_ = (SurfaceControlProto) builderForValue.build();
        this.bitField0_ |= 1024;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeStartLeash(SurfaceControlProto value) {
        SurfaceControlProto surfaceControlProto = this.startLeash_;
        if (surfaceControlProto == null || surfaceControlProto == SurfaceControlProto.getDefaultInstance()) {
            this.startLeash_ = value;
        } else {
            this.startLeash_ = (SurfaceControlProto) ((SurfaceControlProto.Builder) SurfaceControlProto.newBuilder(this.startLeash_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 1024;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearStartLeash() {
        this.startLeash_ = null;
        this.bitField0_ &= -1025;
    }

    @Override // android.view.RemoteAnimationTargetProtoOrBuilder
    public boolean hasStartBounds() {
        return (this.bitField0_ & 2048) == 2048;
    }

    @Override // android.view.RemoteAnimationTargetProtoOrBuilder
    public RectProto getStartBounds() {
        RectProto rectProto = this.startBounds_;
        return rectProto == null ? RectProto.getDefaultInstance() : rectProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setStartBounds(RectProto value) {
        if (value != null) {
            this.startBounds_ = value;
            this.bitField0_ |= 2048;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setStartBounds(RectProto.Builder builderForValue) {
        this.startBounds_ = (RectProto) builderForValue.build();
        this.bitField0_ |= 2048;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeStartBounds(RectProto value) {
        RectProto rectProto = this.startBounds_;
        if (rectProto == null || rectProto == RectProto.getDefaultInstance()) {
            this.startBounds_ = value;
        } else {
            this.startBounds_ = (RectProto) ((RectProto.Builder) RectProto.newBuilder(this.startBounds_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 2048;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearStartBounds() {
        this.startBounds_ = null;
        this.bitField0_ &= -2049;
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeInt32(1, this.taskId_);
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeInt32(2, this.mode_);
        }
        if ((this.bitField0_ & 4) == 4) {
            output.writeMessage(3, getLeash());
        }
        if ((this.bitField0_ & 8) == 8) {
            output.writeBool(4, this.isTranslucent_);
        }
        if ((this.bitField0_ & 16) == 16) {
            output.writeMessage(5, getClipRect());
        }
        if ((this.bitField0_ & 32) == 32) {
            output.writeMessage(6, getContentInsets());
        }
        if ((this.bitField0_ & 64) == 64) {
            output.writeInt32(7, this.prefixOrderIndex_);
        }
        if ((this.bitField0_ & 128) == 128) {
            output.writeMessage(8, getPosition());
        }
        if ((this.bitField0_ & 256) == 256) {
            output.writeMessage(9, getSourceContainerBounds());
        }
        if ((this.bitField0_ & 512) == 512) {
            output.writeMessage(10, getWindowConfiguration());
        }
        if ((this.bitField0_ & 1024) == 1024) {
            output.writeMessage(11, getStartLeash());
        }
        if ((this.bitField0_ & 2048) == 2048) {
            output.writeMessage(12, getStartBounds());
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
            size2 = 0 + CodedOutputStream.computeInt32Size(1, this.taskId_);
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeInt32Size(2, this.mode_);
        }
        if ((this.bitField0_ & 4) == 4) {
            size2 += CodedOutputStream.computeMessageSize(3, getLeash());
        }
        if ((this.bitField0_ & 8) == 8) {
            size2 += CodedOutputStream.computeBoolSize(4, this.isTranslucent_);
        }
        if ((this.bitField0_ & 16) == 16) {
            size2 += CodedOutputStream.computeMessageSize(5, getClipRect());
        }
        if ((this.bitField0_ & 32) == 32) {
            size2 += CodedOutputStream.computeMessageSize(6, getContentInsets());
        }
        if ((this.bitField0_ & 64) == 64) {
            size2 += CodedOutputStream.computeInt32Size(7, this.prefixOrderIndex_);
        }
        if ((this.bitField0_ & 128) == 128) {
            size2 += CodedOutputStream.computeMessageSize(8, getPosition());
        }
        if ((this.bitField0_ & 256) == 256) {
            size2 += CodedOutputStream.computeMessageSize(9, getSourceContainerBounds());
        }
        if ((this.bitField0_ & 512) == 512) {
            size2 += CodedOutputStream.computeMessageSize(10, getWindowConfiguration());
        }
        if ((this.bitField0_ & 1024) == 1024) {
            size2 += CodedOutputStream.computeMessageSize(11, getStartLeash());
        }
        if ((this.bitField0_ & 2048) == 2048) {
            size2 += CodedOutputStream.computeMessageSize(12, getStartBounds());
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static RemoteAnimationTargetProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (RemoteAnimationTargetProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static RemoteAnimationTargetProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (RemoteAnimationTargetProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static RemoteAnimationTargetProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (RemoteAnimationTargetProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static RemoteAnimationTargetProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (RemoteAnimationTargetProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static RemoteAnimationTargetProto parseFrom(InputStream input) throws IOException {
        return (RemoteAnimationTargetProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static RemoteAnimationTargetProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (RemoteAnimationTargetProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static RemoteAnimationTargetProto parseDelimitedFrom(InputStream input) throws IOException {
        return (RemoteAnimationTargetProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static RemoteAnimationTargetProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (RemoteAnimationTargetProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static RemoteAnimationTargetProto parseFrom(CodedInputStream input) throws IOException {
        return (RemoteAnimationTargetProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static RemoteAnimationTargetProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (RemoteAnimationTargetProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(RemoteAnimationTargetProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<RemoteAnimationTargetProto, Builder> implements RemoteAnimationTargetProtoOrBuilder {
        private Builder() {
            super(RemoteAnimationTargetProto.DEFAULT_INSTANCE);
        }

        @Override // android.view.RemoteAnimationTargetProtoOrBuilder
        public boolean hasTaskId() {
            return ((RemoteAnimationTargetProto) this.instance).hasTaskId();
        }

        @Override // android.view.RemoteAnimationTargetProtoOrBuilder
        public int getTaskId() {
            return ((RemoteAnimationTargetProto) this.instance).getTaskId();
        }

        public Builder setTaskId(int value) {
            copyOnWrite();
            ((RemoteAnimationTargetProto) this.instance).setTaskId(value);
            return this;
        }

        public Builder clearTaskId() {
            copyOnWrite();
            ((RemoteAnimationTargetProto) this.instance).clearTaskId();
            return this;
        }

        @Override // android.view.RemoteAnimationTargetProtoOrBuilder
        public boolean hasMode() {
            return ((RemoteAnimationTargetProto) this.instance).hasMode();
        }

        @Override // android.view.RemoteAnimationTargetProtoOrBuilder
        public int getMode() {
            return ((RemoteAnimationTargetProto) this.instance).getMode();
        }

        public Builder setMode(int value) {
            copyOnWrite();
            ((RemoteAnimationTargetProto) this.instance).setMode(value);
            return this;
        }

        public Builder clearMode() {
            copyOnWrite();
            ((RemoteAnimationTargetProto) this.instance).clearMode();
            return this;
        }

        @Override // android.view.RemoteAnimationTargetProtoOrBuilder
        public boolean hasLeash() {
            return ((RemoteAnimationTargetProto) this.instance).hasLeash();
        }

        @Override // android.view.RemoteAnimationTargetProtoOrBuilder
        public SurfaceControlProto getLeash() {
            return ((RemoteAnimationTargetProto) this.instance).getLeash();
        }

        public Builder setLeash(SurfaceControlProto value) {
            copyOnWrite();
            ((RemoteAnimationTargetProto) this.instance).setLeash((RemoteAnimationTargetProto) value);
            return this;
        }

        public Builder setLeash(SurfaceControlProto.Builder builderForValue) {
            copyOnWrite();
            ((RemoteAnimationTargetProto) this.instance).setLeash((RemoteAnimationTargetProto) builderForValue);
            return this;
        }

        public Builder mergeLeash(SurfaceControlProto value) {
            copyOnWrite();
            ((RemoteAnimationTargetProto) this.instance).mergeLeash(value);
            return this;
        }

        public Builder clearLeash() {
            copyOnWrite();
            ((RemoteAnimationTargetProto) this.instance).clearLeash();
            return this;
        }

        @Override // android.view.RemoteAnimationTargetProtoOrBuilder
        public boolean hasIsTranslucent() {
            return ((RemoteAnimationTargetProto) this.instance).hasIsTranslucent();
        }

        @Override // android.view.RemoteAnimationTargetProtoOrBuilder
        public boolean getIsTranslucent() {
            return ((RemoteAnimationTargetProto) this.instance).getIsTranslucent();
        }

        public Builder setIsTranslucent(boolean value) {
            copyOnWrite();
            ((RemoteAnimationTargetProto) this.instance).setIsTranslucent(value);
            return this;
        }

        public Builder clearIsTranslucent() {
            copyOnWrite();
            ((RemoteAnimationTargetProto) this.instance).clearIsTranslucent();
            return this;
        }

        @Override // android.view.RemoteAnimationTargetProtoOrBuilder
        public boolean hasClipRect() {
            return ((RemoteAnimationTargetProto) this.instance).hasClipRect();
        }

        @Override // android.view.RemoteAnimationTargetProtoOrBuilder
        public RectProto getClipRect() {
            return ((RemoteAnimationTargetProto) this.instance).getClipRect();
        }

        public Builder setClipRect(RectProto value) {
            copyOnWrite();
            ((RemoteAnimationTargetProto) this.instance).setClipRect((RemoteAnimationTargetProto) value);
            return this;
        }

        public Builder setClipRect(RectProto.Builder builderForValue) {
            copyOnWrite();
            ((RemoteAnimationTargetProto) this.instance).setClipRect((RemoteAnimationTargetProto) builderForValue);
            return this;
        }

        public Builder mergeClipRect(RectProto value) {
            copyOnWrite();
            ((RemoteAnimationTargetProto) this.instance).mergeClipRect(value);
            return this;
        }

        public Builder clearClipRect() {
            copyOnWrite();
            ((RemoteAnimationTargetProto) this.instance).clearClipRect();
            return this;
        }

        @Override // android.view.RemoteAnimationTargetProtoOrBuilder
        public boolean hasContentInsets() {
            return ((RemoteAnimationTargetProto) this.instance).hasContentInsets();
        }

        @Override // android.view.RemoteAnimationTargetProtoOrBuilder
        public RectProto getContentInsets() {
            return ((RemoteAnimationTargetProto) this.instance).getContentInsets();
        }

        public Builder setContentInsets(RectProto value) {
            copyOnWrite();
            ((RemoteAnimationTargetProto) this.instance).setContentInsets((RemoteAnimationTargetProto) value);
            return this;
        }

        public Builder setContentInsets(RectProto.Builder builderForValue) {
            copyOnWrite();
            ((RemoteAnimationTargetProto) this.instance).setContentInsets((RemoteAnimationTargetProto) builderForValue);
            return this;
        }

        public Builder mergeContentInsets(RectProto value) {
            copyOnWrite();
            ((RemoteAnimationTargetProto) this.instance).mergeContentInsets(value);
            return this;
        }

        public Builder clearContentInsets() {
            copyOnWrite();
            ((RemoteAnimationTargetProto) this.instance).clearContentInsets();
            return this;
        }

        @Override // android.view.RemoteAnimationTargetProtoOrBuilder
        public boolean hasPrefixOrderIndex() {
            return ((RemoteAnimationTargetProto) this.instance).hasPrefixOrderIndex();
        }

        @Override // android.view.RemoteAnimationTargetProtoOrBuilder
        public int getPrefixOrderIndex() {
            return ((RemoteAnimationTargetProto) this.instance).getPrefixOrderIndex();
        }

        public Builder setPrefixOrderIndex(int value) {
            copyOnWrite();
            ((RemoteAnimationTargetProto) this.instance).setPrefixOrderIndex(value);
            return this;
        }

        public Builder clearPrefixOrderIndex() {
            copyOnWrite();
            ((RemoteAnimationTargetProto) this.instance).clearPrefixOrderIndex();
            return this;
        }

        @Override // android.view.RemoteAnimationTargetProtoOrBuilder
        public boolean hasPosition() {
            return ((RemoteAnimationTargetProto) this.instance).hasPosition();
        }

        @Override // android.view.RemoteAnimationTargetProtoOrBuilder
        public PointProto getPosition() {
            return ((RemoteAnimationTargetProto) this.instance).getPosition();
        }

        public Builder setPosition(PointProto value) {
            copyOnWrite();
            ((RemoteAnimationTargetProto) this.instance).setPosition((RemoteAnimationTargetProto) value);
            return this;
        }

        public Builder setPosition(PointProto.Builder builderForValue) {
            copyOnWrite();
            ((RemoteAnimationTargetProto) this.instance).setPosition((RemoteAnimationTargetProto) builderForValue);
            return this;
        }

        public Builder mergePosition(PointProto value) {
            copyOnWrite();
            ((RemoteAnimationTargetProto) this.instance).mergePosition(value);
            return this;
        }

        public Builder clearPosition() {
            copyOnWrite();
            ((RemoteAnimationTargetProto) this.instance).clearPosition();
            return this;
        }

        @Override // android.view.RemoteAnimationTargetProtoOrBuilder
        public boolean hasSourceContainerBounds() {
            return ((RemoteAnimationTargetProto) this.instance).hasSourceContainerBounds();
        }

        @Override // android.view.RemoteAnimationTargetProtoOrBuilder
        public RectProto getSourceContainerBounds() {
            return ((RemoteAnimationTargetProto) this.instance).getSourceContainerBounds();
        }

        public Builder setSourceContainerBounds(RectProto value) {
            copyOnWrite();
            ((RemoteAnimationTargetProto) this.instance).setSourceContainerBounds((RemoteAnimationTargetProto) value);
            return this;
        }

        public Builder setSourceContainerBounds(RectProto.Builder builderForValue) {
            copyOnWrite();
            ((RemoteAnimationTargetProto) this.instance).setSourceContainerBounds((RemoteAnimationTargetProto) builderForValue);
            return this;
        }

        public Builder mergeSourceContainerBounds(RectProto value) {
            copyOnWrite();
            ((RemoteAnimationTargetProto) this.instance).mergeSourceContainerBounds(value);
            return this;
        }

        public Builder clearSourceContainerBounds() {
            copyOnWrite();
            ((RemoteAnimationTargetProto) this.instance).clearSourceContainerBounds();
            return this;
        }

        @Override // android.view.RemoteAnimationTargetProtoOrBuilder
        public boolean hasWindowConfiguration() {
            return ((RemoteAnimationTargetProto) this.instance).hasWindowConfiguration();
        }

        @Override // android.view.RemoteAnimationTargetProtoOrBuilder
        public WindowConfigurationProto getWindowConfiguration() {
            return ((RemoteAnimationTargetProto) this.instance).getWindowConfiguration();
        }

        public Builder setWindowConfiguration(WindowConfigurationProto value) {
            copyOnWrite();
            ((RemoteAnimationTargetProto) this.instance).setWindowConfiguration((RemoteAnimationTargetProto) value);
            return this;
        }

        public Builder setWindowConfiguration(WindowConfigurationProto.Builder builderForValue) {
            copyOnWrite();
            ((RemoteAnimationTargetProto) this.instance).setWindowConfiguration((RemoteAnimationTargetProto) builderForValue);
            return this;
        }

        public Builder mergeWindowConfiguration(WindowConfigurationProto value) {
            copyOnWrite();
            ((RemoteAnimationTargetProto) this.instance).mergeWindowConfiguration(value);
            return this;
        }

        public Builder clearWindowConfiguration() {
            copyOnWrite();
            ((RemoteAnimationTargetProto) this.instance).clearWindowConfiguration();
            return this;
        }

        @Override // android.view.RemoteAnimationTargetProtoOrBuilder
        public boolean hasStartLeash() {
            return ((RemoteAnimationTargetProto) this.instance).hasStartLeash();
        }

        @Override // android.view.RemoteAnimationTargetProtoOrBuilder
        public SurfaceControlProto getStartLeash() {
            return ((RemoteAnimationTargetProto) this.instance).getStartLeash();
        }

        public Builder setStartLeash(SurfaceControlProto value) {
            copyOnWrite();
            ((RemoteAnimationTargetProto) this.instance).setStartLeash((RemoteAnimationTargetProto) value);
            return this;
        }

        public Builder setStartLeash(SurfaceControlProto.Builder builderForValue) {
            copyOnWrite();
            ((RemoteAnimationTargetProto) this.instance).setStartLeash((RemoteAnimationTargetProto) builderForValue);
            return this;
        }

        public Builder mergeStartLeash(SurfaceControlProto value) {
            copyOnWrite();
            ((RemoteAnimationTargetProto) this.instance).mergeStartLeash(value);
            return this;
        }

        public Builder clearStartLeash() {
            copyOnWrite();
            ((RemoteAnimationTargetProto) this.instance).clearStartLeash();
            return this;
        }

        @Override // android.view.RemoteAnimationTargetProtoOrBuilder
        public boolean hasStartBounds() {
            return ((RemoteAnimationTargetProto) this.instance).hasStartBounds();
        }

        @Override // android.view.RemoteAnimationTargetProtoOrBuilder
        public RectProto getStartBounds() {
            return ((RemoteAnimationTargetProto) this.instance).getStartBounds();
        }

        public Builder setStartBounds(RectProto value) {
            copyOnWrite();
            ((RemoteAnimationTargetProto) this.instance).setStartBounds((RemoteAnimationTargetProto) value);
            return this;
        }

        public Builder setStartBounds(RectProto.Builder builderForValue) {
            copyOnWrite();
            ((RemoteAnimationTargetProto) this.instance).setStartBounds((RemoteAnimationTargetProto) builderForValue);
            return this;
        }

        public Builder mergeStartBounds(RectProto value) {
            copyOnWrite();
            ((RemoteAnimationTargetProto) this.instance).mergeStartBounds(value);
            return this;
        }

        public Builder clearStartBounds() {
            copyOnWrite();
            ((RemoteAnimationTargetProto) this.instance).clearStartBounds();
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new RemoteAnimationTargetProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                RemoteAnimationTargetProto other = (RemoteAnimationTargetProto) arg1;
                this.taskId_ = visitor.visitInt(hasTaskId(), this.taskId_, other.hasTaskId(), other.taskId_);
                this.mode_ = visitor.visitInt(hasMode(), this.mode_, other.hasMode(), other.mode_);
                this.leash_ = (SurfaceControlProto) visitor.visitMessage(this.leash_, other.leash_);
                this.isTranslucent_ = visitor.visitBoolean(hasIsTranslucent(), this.isTranslucent_, other.hasIsTranslucent(), other.isTranslucent_);
                this.clipRect_ = (RectProto) visitor.visitMessage(this.clipRect_, other.clipRect_);
                this.contentInsets_ = (RectProto) visitor.visitMessage(this.contentInsets_, other.contentInsets_);
                this.prefixOrderIndex_ = visitor.visitInt(hasPrefixOrderIndex(), this.prefixOrderIndex_, other.hasPrefixOrderIndex(), other.prefixOrderIndex_);
                this.position_ = (PointProto) visitor.visitMessage(this.position_, other.position_);
                this.sourceContainerBounds_ = (RectProto) visitor.visitMessage(this.sourceContainerBounds_, other.sourceContainerBounds_);
                this.windowConfiguration_ = (WindowConfigurationProto) visitor.visitMessage(this.windowConfiguration_, other.windowConfiguration_);
                this.startLeash_ = (SurfaceControlProto) visitor.visitMessage(this.startLeash_, other.startLeash_);
                this.startBounds_ = (RectProto) visitor.visitMessage(this.startBounds_, other.startBounds_);
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
                                this.taskId_ = input.readInt32();
                                break;
                            case 16:
                                this.bitField0_ |= 2;
                                this.mode_ = input.readInt32();
                                break;
                            case 26:
                                SurfaceControlProto.Builder subBuilder = null;
                                if ((this.bitField0_ & 4) == 4) {
                                    subBuilder = (SurfaceControlProto.Builder) this.leash_.toBuilder();
                                }
                                this.leash_ = (SurfaceControlProto) input.readMessage(SurfaceControlProto.parser(), extensionRegistry);
                                if (subBuilder != null) {
                                    subBuilder.mergeFrom((GeneratedMessageLite) this.leash_);
                                    this.leash_ = (SurfaceControlProto) subBuilder.buildPartial();
                                }
                                this.bitField0_ |= 4;
                                break;
                            case 32:
                                this.bitField0_ |= 8;
                                this.isTranslucent_ = input.readBool();
                                break;
                            case 42:
                                RectProto.Builder subBuilder2 = null;
                                if ((this.bitField0_ & 16) == 16) {
                                    subBuilder2 = (RectProto.Builder) this.clipRect_.toBuilder();
                                }
                                this.clipRect_ = (RectProto) input.readMessage(RectProto.parser(), extensionRegistry);
                                if (subBuilder2 != null) {
                                    subBuilder2.mergeFrom((GeneratedMessageLite) this.clipRect_);
                                    this.clipRect_ = (RectProto) subBuilder2.buildPartial();
                                }
                                this.bitField0_ |= 16;
                                break;
                            case 50:
                                RectProto.Builder subBuilder3 = null;
                                if ((this.bitField0_ & 32) == 32) {
                                    subBuilder3 = (RectProto.Builder) this.contentInsets_.toBuilder();
                                }
                                this.contentInsets_ = (RectProto) input.readMessage(RectProto.parser(), extensionRegistry);
                                if (subBuilder3 != null) {
                                    subBuilder3.mergeFrom((GeneratedMessageLite) this.contentInsets_);
                                    this.contentInsets_ = (RectProto) subBuilder3.buildPartial();
                                }
                                this.bitField0_ |= 32;
                                break;
                            case 56:
                                this.bitField0_ |= 64;
                                this.prefixOrderIndex_ = input.readInt32();
                                break;
                            case 66:
                                PointProto.Builder subBuilder4 = null;
                                if ((this.bitField0_ & 128) == 128) {
                                    subBuilder4 = (PointProto.Builder) this.position_.toBuilder();
                                }
                                this.position_ = (PointProto) input.readMessage(PointProto.parser(), extensionRegistry);
                                if (subBuilder4 != null) {
                                    subBuilder4.mergeFrom((GeneratedMessageLite) this.position_);
                                    this.position_ = (PointProto) subBuilder4.buildPartial();
                                }
                                this.bitField0_ |= 128;
                                break;
                            case 74:
                                RectProto.Builder subBuilder5 = null;
                                if ((this.bitField0_ & 256) == 256) {
                                    subBuilder5 = (RectProto.Builder) this.sourceContainerBounds_.toBuilder();
                                }
                                this.sourceContainerBounds_ = (RectProto) input.readMessage(RectProto.parser(), extensionRegistry);
                                if (subBuilder5 != null) {
                                    subBuilder5.mergeFrom((GeneratedMessageLite) this.sourceContainerBounds_);
                                    this.sourceContainerBounds_ = (RectProto) subBuilder5.buildPartial();
                                }
                                this.bitField0_ |= 256;
                                break;
                            case 82:
                                WindowConfigurationProto.Builder subBuilder6 = null;
                                if ((this.bitField0_ & 512) == 512) {
                                    subBuilder6 = (WindowConfigurationProto.Builder) this.windowConfiguration_.toBuilder();
                                }
                                this.windowConfiguration_ = (WindowConfigurationProto) input.readMessage(WindowConfigurationProto.parser(), extensionRegistry);
                                if (subBuilder6 != null) {
                                    subBuilder6.mergeFrom((GeneratedMessageLite) this.windowConfiguration_);
                                    this.windowConfiguration_ = (WindowConfigurationProto) subBuilder6.buildPartial();
                                }
                                this.bitField0_ |= 512;
                                break;
                            case 90:
                                SurfaceControlProto.Builder subBuilder7 = null;
                                if ((this.bitField0_ & 1024) == 1024) {
                                    subBuilder7 = (SurfaceControlProto.Builder) this.startLeash_.toBuilder();
                                }
                                this.startLeash_ = (SurfaceControlProto) input.readMessage(SurfaceControlProto.parser(), extensionRegistry);
                                if (subBuilder7 != null) {
                                    subBuilder7.mergeFrom((GeneratedMessageLite) this.startLeash_);
                                    this.startLeash_ = (SurfaceControlProto) subBuilder7.buildPartial();
                                }
                                this.bitField0_ |= 1024;
                                break;
                            case 98:
                                RectProto.Builder subBuilder8 = null;
                                if ((this.bitField0_ & 2048) == 2048) {
                                    subBuilder8 = (RectProto.Builder) this.startBounds_.toBuilder();
                                }
                                this.startBounds_ = (RectProto) input.readMessage(RectProto.parser(), extensionRegistry);
                                if (subBuilder8 != null) {
                                    subBuilder8.mergeFrom((GeneratedMessageLite) this.startBounds_);
                                    this.startBounds_ = (RectProto) subBuilder8.buildPartial();
                                }
                                this.bitField0_ |= 2048;
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
                    synchronized (RemoteAnimationTargetProto.class) {
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

    public static RemoteAnimationTargetProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<RemoteAnimationTargetProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
