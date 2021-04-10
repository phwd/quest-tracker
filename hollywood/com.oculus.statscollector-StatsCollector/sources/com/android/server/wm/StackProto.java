package com.android.server.wm;

import android.graphics.RectProto;
import com.android.server.wm.TaskProto;
import com.android.server.wm.WindowContainerProto;
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

public final class StackProto extends GeneratedMessageLite<StackProto, Builder> implements StackProtoOrBuilder {
    public static final int ADJUSTED_BOUNDS_FIELD_NUMBER = 12;
    public static final int ADJUSTED_FOR_IME_FIELD_NUMBER = 9;
    public static final int ADJUST_DIVIDER_AMOUNT_FIELD_NUMBER = 11;
    public static final int ADJUST_IME_AMOUNT_FIELD_NUMBER = 10;
    public static final int ANIMATING_BOUNDS_FIELD_NUMBER = 13;
    public static final int ANIMATION_BACKGROUND_SURFACE_IS_DIMMING_FIELD_NUMBER = 6;
    public static final int BOUNDS_FIELD_NUMBER = 5;
    private static final StackProto DEFAULT_INSTANCE = new StackProto();
    public static final int DEFER_REMOVAL_FIELD_NUMBER = 7;
    public static final int FILLS_PARENT_FIELD_NUMBER = 4;
    public static final int ID_FIELD_NUMBER = 2;
    public static final int MINIMIZE_AMOUNT_FIELD_NUMBER = 8;
    private static volatile Parser<StackProto> PARSER = null;
    public static final int TASKS_FIELD_NUMBER = 3;
    public static final int WINDOW_CONTAINER_FIELD_NUMBER = 1;
    private float adjustDividerAmount_ = 0.0f;
    private float adjustImeAmount_ = 0.0f;
    private RectProto adjustedBounds_;
    private boolean adjustedForIme_ = false;
    private boolean animatingBounds_ = false;
    private boolean animationBackgroundSurfaceIsDimming_ = false;
    private int bitField0_;
    private RectProto bounds_;
    private boolean deferRemoval_ = false;
    private boolean fillsParent_ = false;
    private int id_ = 0;
    private float minimizeAmount_ = 0.0f;
    private Internal.ProtobufList<TaskProto> tasks_ = emptyProtobufList();
    private WindowContainerProto windowContainer_;

    private StackProto() {
    }

    @Override // com.android.server.wm.StackProtoOrBuilder
    public boolean hasWindowContainer() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // com.android.server.wm.StackProtoOrBuilder
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

    @Override // com.android.server.wm.StackProtoOrBuilder
    public boolean hasId() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // com.android.server.wm.StackProtoOrBuilder
    public int getId() {
        return this.id_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setId(int value) {
        this.bitField0_ |= 2;
        this.id_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearId() {
        this.bitField0_ &= -3;
        this.id_ = 0;
    }

    @Override // com.android.server.wm.StackProtoOrBuilder
    public List<TaskProto> getTasksList() {
        return this.tasks_;
    }

    public List<? extends TaskProtoOrBuilder> getTasksOrBuilderList() {
        return this.tasks_;
    }

    @Override // com.android.server.wm.StackProtoOrBuilder
    public int getTasksCount() {
        return this.tasks_.size();
    }

    @Override // com.android.server.wm.StackProtoOrBuilder
    public TaskProto getTasks(int index) {
        return this.tasks_.get(index);
    }

    public TaskProtoOrBuilder getTasksOrBuilder(int index) {
        return this.tasks_.get(index);
    }

    private void ensureTasksIsMutable() {
        if (!this.tasks_.isModifiable()) {
            this.tasks_ = GeneratedMessageLite.mutableCopy(this.tasks_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setTasks(int index, TaskProto value) {
        if (value != null) {
            ensureTasksIsMutable();
            this.tasks_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setTasks(int index, TaskProto.Builder builderForValue) {
        ensureTasksIsMutable();
        this.tasks_.set(index, (TaskProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addTasks(TaskProto value) {
        if (value != null) {
            ensureTasksIsMutable();
            this.tasks_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addTasks(int index, TaskProto value) {
        if (value != null) {
            ensureTasksIsMutable();
            this.tasks_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addTasks(TaskProto.Builder builderForValue) {
        ensureTasksIsMutable();
        this.tasks_.add((TaskProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addTasks(int index, TaskProto.Builder builderForValue) {
        ensureTasksIsMutable();
        this.tasks_.add(index, (TaskProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllTasks(Iterable<? extends TaskProto> values) {
        ensureTasksIsMutable();
        AbstractMessageLite.addAll(values, this.tasks_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearTasks() {
        this.tasks_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeTasks(int index) {
        ensureTasksIsMutable();
        this.tasks_.remove(index);
    }

    @Override // com.android.server.wm.StackProtoOrBuilder
    public boolean hasFillsParent() {
        return (this.bitField0_ & 4) == 4;
    }

    @Override // com.android.server.wm.StackProtoOrBuilder
    public boolean getFillsParent() {
        return this.fillsParent_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setFillsParent(boolean value) {
        this.bitField0_ |= 4;
        this.fillsParent_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearFillsParent() {
        this.bitField0_ &= -5;
        this.fillsParent_ = false;
    }

    @Override // com.android.server.wm.StackProtoOrBuilder
    public boolean hasBounds() {
        return (this.bitField0_ & 8) == 8;
    }

    @Override // com.android.server.wm.StackProtoOrBuilder
    public RectProto getBounds() {
        RectProto rectProto = this.bounds_;
        return rectProto == null ? RectProto.getDefaultInstance() : rectProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setBounds(RectProto value) {
        if (value != null) {
            this.bounds_ = value;
            this.bitField0_ |= 8;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setBounds(RectProto.Builder builderForValue) {
        this.bounds_ = (RectProto) builderForValue.build();
        this.bitField0_ |= 8;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeBounds(RectProto value) {
        RectProto rectProto = this.bounds_;
        if (rectProto == null || rectProto == RectProto.getDefaultInstance()) {
            this.bounds_ = value;
        } else {
            this.bounds_ = (RectProto) ((RectProto.Builder) RectProto.newBuilder(this.bounds_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 8;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearBounds() {
        this.bounds_ = null;
        this.bitField0_ &= -9;
    }

    @Override // com.android.server.wm.StackProtoOrBuilder
    public boolean hasAnimationBackgroundSurfaceIsDimming() {
        return (this.bitField0_ & 16) == 16;
    }

    @Override // com.android.server.wm.StackProtoOrBuilder
    public boolean getAnimationBackgroundSurfaceIsDimming() {
        return this.animationBackgroundSurfaceIsDimming_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setAnimationBackgroundSurfaceIsDimming(boolean value) {
        this.bitField0_ |= 16;
        this.animationBackgroundSurfaceIsDimming_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearAnimationBackgroundSurfaceIsDimming() {
        this.bitField0_ &= -17;
        this.animationBackgroundSurfaceIsDimming_ = false;
    }

    @Override // com.android.server.wm.StackProtoOrBuilder
    public boolean hasDeferRemoval() {
        return (this.bitField0_ & 32) == 32;
    }

    @Override // com.android.server.wm.StackProtoOrBuilder
    public boolean getDeferRemoval() {
        return this.deferRemoval_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setDeferRemoval(boolean value) {
        this.bitField0_ |= 32;
        this.deferRemoval_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearDeferRemoval() {
        this.bitField0_ &= -33;
        this.deferRemoval_ = false;
    }

    @Override // com.android.server.wm.StackProtoOrBuilder
    public boolean hasMinimizeAmount() {
        return (this.bitField0_ & 64) == 64;
    }

    @Override // com.android.server.wm.StackProtoOrBuilder
    public float getMinimizeAmount() {
        return this.minimizeAmount_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setMinimizeAmount(float value) {
        this.bitField0_ |= 64;
        this.minimizeAmount_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearMinimizeAmount() {
        this.bitField0_ &= -65;
        this.minimizeAmount_ = 0.0f;
    }

    @Override // com.android.server.wm.StackProtoOrBuilder
    public boolean hasAdjustedForIme() {
        return (this.bitField0_ & 128) == 128;
    }

    @Override // com.android.server.wm.StackProtoOrBuilder
    public boolean getAdjustedForIme() {
        return this.adjustedForIme_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setAdjustedForIme(boolean value) {
        this.bitField0_ |= 128;
        this.adjustedForIme_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearAdjustedForIme() {
        this.bitField0_ &= -129;
        this.adjustedForIme_ = false;
    }

    @Override // com.android.server.wm.StackProtoOrBuilder
    public boolean hasAdjustImeAmount() {
        return (this.bitField0_ & 256) == 256;
    }

    @Override // com.android.server.wm.StackProtoOrBuilder
    public float getAdjustImeAmount() {
        return this.adjustImeAmount_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setAdjustImeAmount(float value) {
        this.bitField0_ |= 256;
        this.adjustImeAmount_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearAdjustImeAmount() {
        this.bitField0_ &= -257;
        this.adjustImeAmount_ = 0.0f;
    }

    @Override // com.android.server.wm.StackProtoOrBuilder
    public boolean hasAdjustDividerAmount() {
        return (this.bitField0_ & 512) == 512;
    }

    @Override // com.android.server.wm.StackProtoOrBuilder
    public float getAdjustDividerAmount() {
        return this.adjustDividerAmount_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setAdjustDividerAmount(float value) {
        this.bitField0_ |= 512;
        this.adjustDividerAmount_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearAdjustDividerAmount() {
        this.bitField0_ &= -513;
        this.adjustDividerAmount_ = 0.0f;
    }

    @Override // com.android.server.wm.StackProtoOrBuilder
    public boolean hasAdjustedBounds() {
        return (this.bitField0_ & 1024) == 1024;
    }

    @Override // com.android.server.wm.StackProtoOrBuilder
    public RectProto getAdjustedBounds() {
        RectProto rectProto = this.adjustedBounds_;
        return rectProto == null ? RectProto.getDefaultInstance() : rectProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setAdjustedBounds(RectProto value) {
        if (value != null) {
            this.adjustedBounds_ = value;
            this.bitField0_ |= 1024;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setAdjustedBounds(RectProto.Builder builderForValue) {
        this.adjustedBounds_ = (RectProto) builderForValue.build();
        this.bitField0_ |= 1024;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeAdjustedBounds(RectProto value) {
        RectProto rectProto = this.adjustedBounds_;
        if (rectProto == null || rectProto == RectProto.getDefaultInstance()) {
            this.adjustedBounds_ = value;
        } else {
            this.adjustedBounds_ = (RectProto) ((RectProto.Builder) RectProto.newBuilder(this.adjustedBounds_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 1024;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearAdjustedBounds() {
        this.adjustedBounds_ = null;
        this.bitField0_ &= -1025;
    }

    @Override // com.android.server.wm.StackProtoOrBuilder
    public boolean hasAnimatingBounds() {
        return (this.bitField0_ & 2048) == 2048;
    }

    @Override // com.android.server.wm.StackProtoOrBuilder
    public boolean getAnimatingBounds() {
        return this.animatingBounds_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setAnimatingBounds(boolean value) {
        this.bitField0_ |= 2048;
        this.animatingBounds_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearAnimatingBounds() {
        this.bitField0_ &= -2049;
        this.animatingBounds_ = false;
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeMessage(1, getWindowContainer());
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeInt32(2, this.id_);
        }
        for (int i = 0; i < this.tasks_.size(); i++) {
            output.writeMessage(3, this.tasks_.get(i));
        }
        if ((this.bitField0_ & 4) == 4) {
            output.writeBool(4, this.fillsParent_);
        }
        if ((this.bitField0_ & 8) == 8) {
            output.writeMessage(5, getBounds());
        }
        if ((this.bitField0_ & 16) == 16) {
            output.writeBool(6, this.animationBackgroundSurfaceIsDimming_);
        }
        if ((this.bitField0_ & 32) == 32) {
            output.writeBool(7, this.deferRemoval_);
        }
        if ((this.bitField0_ & 64) == 64) {
            output.writeFloat(8, this.minimizeAmount_);
        }
        if ((this.bitField0_ & 128) == 128) {
            output.writeBool(9, this.adjustedForIme_);
        }
        if ((this.bitField0_ & 256) == 256) {
            output.writeFloat(10, this.adjustImeAmount_);
        }
        if ((this.bitField0_ & 512) == 512) {
            output.writeFloat(11, this.adjustDividerAmount_);
        }
        if ((this.bitField0_ & 1024) == 1024) {
            output.writeMessage(12, getAdjustedBounds());
        }
        if ((this.bitField0_ & 2048) == 2048) {
            output.writeBool(13, this.animatingBounds_);
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
            size2 += CodedOutputStream.computeInt32Size(2, this.id_);
        }
        for (int i = 0; i < this.tasks_.size(); i++) {
            size2 += CodedOutputStream.computeMessageSize(3, this.tasks_.get(i));
        }
        if ((this.bitField0_ & 4) == 4) {
            size2 += CodedOutputStream.computeBoolSize(4, this.fillsParent_);
        }
        if ((this.bitField0_ & 8) == 8) {
            size2 += CodedOutputStream.computeMessageSize(5, getBounds());
        }
        if ((this.bitField0_ & 16) == 16) {
            size2 += CodedOutputStream.computeBoolSize(6, this.animationBackgroundSurfaceIsDimming_);
        }
        if ((this.bitField0_ & 32) == 32) {
            size2 += CodedOutputStream.computeBoolSize(7, this.deferRemoval_);
        }
        if ((this.bitField0_ & 64) == 64) {
            size2 += CodedOutputStream.computeFloatSize(8, this.minimizeAmount_);
        }
        if ((this.bitField0_ & 128) == 128) {
            size2 += CodedOutputStream.computeBoolSize(9, this.adjustedForIme_);
        }
        if ((this.bitField0_ & 256) == 256) {
            size2 += CodedOutputStream.computeFloatSize(10, this.adjustImeAmount_);
        }
        if ((this.bitField0_ & 512) == 512) {
            size2 += CodedOutputStream.computeFloatSize(11, this.adjustDividerAmount_);
        }
        if ((this.bitField0_ & 1024) == 1024) {
            size2 += CodedOutputStream.computeMessageSize(12, getAdjustedBounds());
        }
        if ((this.bitField0_ & 2048) == 2048) {
            size2 += CodedOutputStream.computeBoolSize(13, this.animatingBounds_);
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static StackProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (StackProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static StackProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (StackProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static StackProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (StackProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static StackProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (StackProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static StackProto parseFrom(InputStream input) throws IOException {
        return (StackProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static StackProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (StackProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static StackProto parseDelimitedFrom(InputStream input) throws IOException {
        return (StackProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static StackProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (StackProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static StackProto parseFrom(CodedInputStream input) throws IOException {
        return (StackProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static StackProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (StackProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(StackProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<StackProto, Builder> implements StackProtoOrBuilder {
        private Builder() {
            super(StackProto.DEFAULT_INSTANCE);
        }

        @Override // com.android.server.wm.StackProtoOrBuilder
        public boolean hasWindowContainer() {
            return ((StackProto) this.instance).hasWindowContainer();
        }

        @Override // com.android.server.wm.StackProtoOrBuilder
        public WindowContainerProto getWindowContainer() {
            return ((StackProto) this.instance).getWindowContainer();
        }

        public Builder setWindowContainer(WindowContainerProto value) {
            copyOnWrite();
            ((StackProto) this.instance).setWindowContainer((StackProto) value);
            return this;
        }

        public Builder setWindowContainer(WindowContainerProto.Builder builderForValue) {
            copyOnWrite();
            ((StackProto) this.instance).setWindowContainer((StackProto) builderForValue);
            return this;
        }

        public Builder mergeWindowContainer(WindowContainerProto value) {
            copyOnWrite();
            ((StackProto) this.instance).mergeWindowContainer(value);
            return this;
        }

        public Builder clearWindowContainer() {
            copyOnWrite();
            ((StackProto) this.instance).clearWindowContainer();
            return this;
        }

        @Override // com.android.server.wm.StackProtoOrBuilder
        public boolean hasId() {
            return ((StackProto) this.instance).hasId();
        }

        @Override // com.android.server.wm.StackProtoOrBuilder
        public int getId() {
            return ((StackProto) this.instance).getId();
        }

        public Builder setId(int value) {
            copyOnWrite();
            ((StackProto) this.instance).setId(value);
            return this;
        }

        public Builder clearId() {
            copyOnWrite();
            ((StackProto) this.instance).clearId();
            return this;
        }

        @Override // com.android.server.wm.StackProtoOrBuilder
        public List<TaskProto> getTasksList() {
            return Collections.unmodifiableList(((StackProto) this.instance).getTasksList());
        }

        @Override // com.android.server.wm.StackProtoOrBuilder
        public int getTasksCount() {
            return ((StackProto) this.instance).getTasksCount();
        }

        @Override // com.android.server.wm.StackProtoOrBuilder
        public TaskProto getTasks(int index) {
            return ((StackProto) this.instance).getTasks(index);
        }

        public Builder setTasks(int index, TaskProto value) {
            copyOnWrite();
            ((StackProto) this.instance).setTasks((StackProto) index, (int) value);
            return this;
        }

        public Builder setTasks(int index, TaskProto.Builder builderForValue) {
            copyOnWrite();
            ((StackProto) this.instance).setTasks((StackProto) index, (int) builderForValue);
            return this;
        }

        public Builder addTasks(TaskProto value) {
            copyOnWrite();
            ((StackProto) this.instance).addTasks((StackProto) value);
            return this;
        }

        public Builder addTasks(int index, TaskProto value) {
            copyOnWrite();
            ((StackProto) this.instance).addTasks((StackProto) index, (int) value);
            return this;
        }

        public Builder addTasks(TaskProto.Builder builderForValue) {
            copyOnWrite();
            ((StackProto) this.instance).addTasks((StackProto) builderForValue);
            return this;
        }

        public Builder addTasks(int index, TaskProto.Builder builderForValue) {
            copyOnWrite();
            ((StackProto) this.instance).addTasks((StackProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllTasks(Iterable<? extends TaskProto> values) {
            copyOnWrite();
            ((StackProto) this.instance).addAllTasks(values);
            return this;
        }

        public Builder clearTasks() {
            copyOnWrite();
            ((StackProto) this.instance).clearTasks();
            return this;
        }

        public Builder removeTasks(int index) {
            copyOnWrite();
            ((StackProto) this.instance).removeTasks(index);
            return this;
        }

        @Override // com.android.server.wm.StackProtoOrBuilder
        public boolean hasFillsParent() {
            return ((StackProto) this.instance).hasFillsParent();
        }

        @Override // com.android.server.wm.StackProtoOrBuilder
        public boolean getFillsParent() {
            return ((StackProto) this.instance).getFillsParent();
        }

        public Builder setFillsParent(boolean value) {
            copyOnWrite();
            ((StackProto) this.instance).setFillsParent(value);
            return this;
        }

        public Builder clearFillsParent() {
            copyOnWrite();
            ((StackProto) this.instance).clearFillsParent();
            return this;
        }

        @Override // com.android.server.wm.StackProtoOrBuilder
        public boolean hasBounds() {
            return ((StackProto) this.instance).hasBounds();
        }

        @Override // com.android.server.wm.StackProtoOrBuilder
        public RectProto getBounds() {
            return ((StackProto) this.instance).getBounds();
        }

        public Builder setBounds(RectProto value) {
            copyOnWrite();
            ((StackProto) this.instance).setBounds((StackProto) value);
            return this;
        }

        public Builder setBounds(RectProto.Builder builderForValue) {
            copyOnWrite();
            ((StackProto) this.instance).setBounds((StackProto) builderForValue);
            return this;
        }

        public Builder mergeBounds(RectProto value) {
            copyOnWrite();
            ((StackProto) this.instance).mergeBounds(value);
            return this;
        }

        public Builder clearBounds() {
            copyOnWrite();
            ((StackProto) this.instance).clearBounds();
            return this;
        }

        @Override // com.android.server.wm.StackProtoOrBuilder
        public boolean hasAnimationBackgroundSurfaceIsDimming() {
            return ((StackProto) this.instance).hasAnimationBackgroundSurfaceIsDimming();
        }

        @Override // com.android.server.wm.StackProtoOrBuilder
        public boolean getAnimationBackgroundSurfaceIsDimming() {
            return ((StackProto) this.instance).getAnimationBackgroundSurfaceIsDimming();
        }

        public Builder setAnimationBackgroundSurfaceIsDimming(boolean value) {
            copyOnWrite();
            ((StackProto) this.instance).setAnimationBackgroundSurfaceIsDimming(value);
            return this;
        }

        public Builder clearAnimationBackgroundSurfaceIsDimming() {
            copyOnWrite();
            ((StackProto) this.instance).clearAnimationBackgroundSurfaceIsDimming();
            return this;
        }

        @Override // com.android.server.wm.StackProtoOrBuilder
        public boolean hasDeferRemoval() {
            return ((StackProto) this.instance).hasDeferRemoval();
        }

        @Override // com.android.server.wm.StackProtoOrBuilder
        public boolean getDeferRemoval() {
            return ((StackProto) this.instance).getDeferRemoval();
        }

        public Builder setDeferRemoval(boolean value) {
            copyOnWrite();
            ((StackProto) this.instance).setDeferRemoval(value);
            return this;
        }

        public Builder clearDeferRemoval() {
            copyOnWrite();
            ((StackProto) this.instance).clearDeferRemoval();
            return this;
        }

        @Override // com.android.server.wm.StackProtoOrBuilder
        public boolean hasMinimizeAmount() {
            return ((StackProto) this.instance).hasMinimizeAmount();
        }

        @Override // com.android.server.wm.StackProtoOrBuilder
        public float getMinimizeAmount() {
            return ((StackProto) this.instance).getMinimizeAmount();
        }

        public Builder setMinimizeAmount(float value) {
            copyOnWrite();
            ((StackProto) this.instance).setMinimizeAmount(value);
            return this;
        }

        public Builder clearMinimizeAmount() {
            copyOnWrite();
            ((StackProto) this.instance).clearMinimizeAmount();
            return this;
        }

        @Override // com.android.server.wm.StackProtoOrBuilder
        public boolean hasAdjustedForIme() {
            return ((StackProto) this.instance).hasAdjustedForIme();
        }

        @Override // com.android.server.wm.StackProtoOrBuilder
        public boolean getAdjustedForIme() {
            return ((StackProto) this.instance).getAdjustedForIme();
        }

        public Builder setAdjustedForIme(boolean value) {
            copyOnWrite();
            ((StackProto) this.instance).setAdjustedForIme(value);
            return this;
        }

        public Builder clearAdjustedForIme() {
            copyOnWrite();
            ((StackProto) this.instance).clearAdjustedForIme();
            return this;
        }

        @Override // com.android.server.wm.StackProtoOrBuilder
        public boolean hasAdjustImeAmount() {
            return ((StackProto) this.instance).hasAdjustImeAmount();
        }

        @Override // com.android.server.wm.StackProtoOrBuilder
        public float getAdjustImeAmount() {
            return ((StackProto) this.instance).getAdjustImeAmount();
        }

        public Builder setAdjustImeAmount(float value) {
            copyOnWrite();
            ((StackProto) this.instance).setAdjustImeAmount(value);
            return this;
        }

        public Builder clearAdjustImeAmount() {
            copyOnWrite();
            ((StackProto) this.instance).clearAdjustImeAmount();
            return this;
        }

        @Override // com.android.server.wm.StackProtoOrBuilder
        public boolean hasAdjustDividerAmount() {
            return ((StackProto) this.instance).hasAdjustDividerAmount();
        }

        @Override // com.android.server.wm.StackProtoOrBuilder
        public float getAdjustDividerAmount() {
            return ((StackProto) this.instance).getAdjustDividerAmount();
        }

        public Builder setAdjustDividerAmount(float value) {
            copyOnWrite();
            ((StackProto) this.instance).setAdjustDividerAmount(value);
            return this;
        }

        public Builder clearAdjustDividerAmount() {
            copyOnWrite();
            ((StackProto) this.instance).clearAdjustDividerAmount();
            return this;
        }

        @Override // com.android.server.wm.StackProtoOrBuilder
        public boolean hasAdjustedBounds() {
            return ((StackProto) this.instance).hasAdjustedBounds();
        }

        @Override // com.android.server.wm.StackProtoOrBuilder
        public RectProto getAdjustedBounds() {
            return ((StackProto) this.instance).getAdjustedBounds();
        }

        public Builder setAdjustedBounds(RectProto value) {
            copyOnWrite();
            ((StackProto) this.instance).setAdjustedBounds((StackProto) value);
            return this;
        }

        public Builder setAdjustedBounds(RectProto.Builder builderForValue) {
            copyOnWrite();
            ((StackProto) this.instance).setAdjustedBounds((StackProto) builderForValue);
            return this;
        }

        public Builder mergeAdjustedBounds(RectProto value) {
            copyOnWrite();
            ((StackProto) this.instance).mergeAdjustedBounds(value);
            return this;
        }

        public Builder clearAdjustedBounds() {
            copyOnWrite();
            ((StackProto) this.instance).clearAdjustedBounds();
            return this;
        }

        @Override // com.android.server.wm.StackProtoOrBuilder
        public boolean hasAnimatingBounds() {
            return ((StackProto) this.instance).hasAnimatingBounds();
        }

        @Override // com.android.server.wm.StackProtoOrBuilder
        public boolean getAnimatingBounds() {
            return ((StackProto) this.instance).getAnimatingBounds();
        }

        public Builder setAnimatingBounds(boolean value) {
            copyOnWrite();
            ((StackProto) this.instance).setAnimatingBounds(value);
            return this;
        }

        public Builder clearAnimatingBounds() {
            copyOnWrite();
            ((StackProto) this.instance).clearAnimatingBounds();
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new StackProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                this.tasks_.makeImmutable();
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                StackProto other = (StackProto) arg1;
                this.windowContainer_ = (WindowContainerProto) visitor.visitMessage(this.windowContainer_, other.windowContainer_);
                this.id_ = visitor.visitInt(hasId(), this.id_, other.hasId(), other.id_);
                this.tasks_ = visitor.visitList(this.tasks_, other.tasks_);
                this.fillsParent_ = visitor.visitBoolean(hasFillsParent(), this.fillsParent_, other.hasFillsParent(), other.fillsParent_);
                this.bounds_ = (RectProto) visitor.visitMessage(this.bounds_, other.bounds_);
                this.animationBackgroundSurfaceIsDimming_ = visitor.visitBoolean(hasAnimationBackgroundSurfaceIsDimming(), this.animationBackgroundSurfaceIsDimming_, other.hasAnimationBackgroundSurfaceIsDimming(), other.animationBackgroundSurfaceIsDimming_);
                this.deferRemoval_ = visitor.visitBoolean(hasDeferRemoval(), this.deferRemoval_, other.hasDeferRemoval(), other.deferRemoval_);
                this.minimizeAmount_ = visitor.visitFloat(hasMinimizeAmount(), this.minimizeAmount_, other.hasMinimizeAmount(), other.minimizeAmount_);
                this.adjustedForIme_ = visitor.visitBoolean(hasAdjustedForIme(), this.adjustedForIme_, other.hasAdjustedForIme(), other.adjustedForIme_);
                this.adjustImeAmount_ = visitor.visitFloat(hasAdjustImeAmount(), this.adjustImeAmount_, other.hasAdjustImeAmount(), other.adjustImeAmount_);
                this.adjustDividerAmount_ = visitor.visitFloat(hasAdjustDividerAmount(), this.adjustDividerAmount_, other.hasAdjustDividerAmount(), other.adjustDividerAmount_);
                this.adjustedBounds_ = (RectProto) visitor.visitMessage(this.adjustedBounds_, other.adjustedBounds_);
                this.animatingBounds_ = visitor.visitBoolean(hasAnimatingBounds(), this.animatingBounds_, other.hasAnimatingBounds(), other.animatingBounds_);
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
                            case 16:
                                this.bitField0_ |= 2;
                                this.id_ = input.readInt32();
                                break;
                            case 26:
                                if (!this.tasks_.isModifiable()) {
                                    this.tasks_ = GeneratedMessageLite.mutableCopy(this.tasks_);
                                }
                                this.tasks_.add((TaskProto) input.readMessage(TaskProto.parser(), extensionRegistry));
                                break;
                            case 32:
                                this.bitField0_ |= 4;
                                this.fillsParent_ = input.readBool();
                                break;
                            case 42:
                                RectProto.Builder subBuilder2 = null;
                                if ((this.bitField0_ & 8) == 8) {
                                    subBuilder2 = (RectProto.Builder) this.bounds_.toBuilder();
                                }
                                this.bounds_ = (RectProto) input.readMessage(RectProto.parser(), extensionRegistry);
                                if (subBuilder2 != null) {
                                    subBuilder2.mergeFrom((GeneratedMessageLite) this.bounds_);
                                    this.bounds_ = (RectProto) subBuilder2.buildPartial();
                                }
                                this.bitField0_ |= 8;
                                break;
                            case 48:
                                this.bitField0_ |= 16;
                                this.animationBackgroundSurfaceIsDimming_ = input.readBool();
                                break;
                            case 56:
                                this.bitField0_ |= 32;
                                this.deferRemoval_ = input.readBool();
                                break;
                            case 69:
                                this.bitField0_ |= 64;
                                this.minimizeAmount_ = input.readFloat();
                                break;
                            case 72:
                                this.bitField0_ |= 128;
                                this.adjustedForIme_ = input.readBool();
                                break;
                            case 85:
                                this.bitField0_ |= 256;
                                this.adjustImeAmount_ = input.readFloat();
                                break;
                            case 93:
                                this.bitField0_ |= 512;
                                this.adjustDividerAmount_ = input.readFloat();
                                break;
                            case 98:
                                RectProto.Builder subBuilder3 = null;
                                if ((this.bitField0_ & 1024) == 1024) {
                                    subBuilder3 = (RectProto.Builder) this.adjustedBounds_.toBuilder();
                                }
                                this.adjustedBounds_ = (RectProto) input.readMessage(RectProto.parser(), extensionRegistry);
                                if (subBuilder3 != null) {
                                    subBuilder3.mergeFrom((GeneratedMessageLite) this.adjustedBounds_);
                                    this.adjustedBounds_ = (RectProto) subBuilder3.buildPartial();
                                }
                                this.bitField0_ |= 1024;
                                break;
                            case 104:
                                this.bitField0_ |= 2048;
                                this.animatingBounds_ = input.readBool();
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
                    synchronized (StackProto.class) {
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

    public static StackProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<StackProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
