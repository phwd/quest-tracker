package com.android.server.wm;

import android.graphics.RectProto;
import com.android.server.wm.AppWindowTokenProto;
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

public final class TaskProto extends GeneratedMessageLite<TaskProto, Builder> implements TaskProtoOrBuilder {
    public static final int APP_WINDOW_TOKENS_FIELD_NUMBER = 3;
    public static final int BOUNDS_FIELD_NUMBER = 5;
    private static final TaskProto DEFAULT_INSTANCE = new TaskProto();
    public static final int DEFER_REMOVAL_FIELD_NUMBER = 7;
    public static final int DISPLAYED_BOUNDS_FIELD_NUMBER = 6;
    public static final int FILLS_PARENT_FIELD_NUMBER = 4;
    public static final int ID_FIELD_NUMBER = 2;
    private static volatile Parser<TaskProto> PARSER = null;
    public static final int SURFACE_HEIGHT_FIELD_NUMBER = 9;
    public static final int SURFACE_WIDTH_FIELD_NUMBER = 8;
    public static final int WINDOW_CONTAINER_FIELD_NUMBER = 1;
    private Internal.ProtobufList<AppWindowTokenProto> appWindowTokens_ = emptyProtobufList();
    private int bitField0_;
    private RectProto bounds_;
    private boolean deferRemoval_ = false;
    private RectProto displayedBounds_;
    private boolean fillsParent_ = false;
    private int id_ = 0;
    private int surfaceHeight_ = 0;
    private int surfaceWidth_ = 0;
    private WindowContainerProto windowContainer_;

    private TaskProto() {
    }

    @Override // com.android.server.wm.TaskProtoOrBuilder
    public boolean hasWindowContainer() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // com.android.server.wm.TaskProtoOrBuilder
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

    @Override // com.android.server.wm.TaskProtoOrBuilder
    public boolean hasId() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // com.android.server.wm.TaskProtoOrBuilder
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

    @Override // com.android.server.wm.TaskProtoOrBuilder
    public List<AppWindowTokenProto> getAppWindowTokensList() {
        return this.appWindowTokens_;
    }

    public List<? extends AppWindowTokenProtoOrBuilder> getAppWindowTokensOrBuilderList() {
        return this.appWindowTokens_;
    }

    @Override // com.android.server.wm.TaskProtoOrBuilder
    public int getAppWindowTokensCount() {
        return this.appWindowTokens_.size();
    }

    @Override // com.android.server.wm.TaskProtoOrBuilder
    public AppWindowTokenProto getAppWindowTokens(int index) {
        return this.appWindowTokens_.get(index);
    }

    public AppWindowTokenProtoOrBuilder getAppWindowTokensOrBuilder(int index) {
        return this.appWindowTokens_.get(index);
    }

    private void ensureAppWindowTokensIsMutable() {
        if (!this.appWindowTokens_.isModifiable()) {
            this.appWindowTokens_ = GeneratedMessageLite.mutableCopy(this.appWindowTokens_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setAppWindowTokens(int index, AppWindowTokenProto value) {
        if (value != null) {
            ensureAppWindowTokensIsMutable();
            this.appWindowTokens_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setAppWindowTokens(int index, AppWindowTokenProto.Builder builderForValue) {
        ensureAppWindowTokensIsMutable();
        this.appWindowTokens_.set(index, (AppWindowTokenProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAppWindowTokens(AppWindowTokenProto value) {
        if (value != null) {
            ensureAppWindowTokensIsMutable();
            this.appWindowTokens_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAppWindowTokens(int index, AppWindowTokenProto value) {
        if (value != null) {
            ensureAppWindowTokensIsMutable();
            this.appWindowTokens_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAppWindowTokens(AppWindowTokenProto.Builder builderForValue) {
        ensureAppWindowTokensIsMutable();
        this.appWindowTokens_.add((AppWindowTokenProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAppWindowTokens(int index, AppWindowTokenProto.Builder builderForValue) {
        ensureAppWindowTokensIsMutable();
        this.appWindowTokens_.add(index, (AppWindowTokenProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllAppWindowTokens(Iterable<? extends AppWindowTokenProto> values) {
        ensureAppWindowTokensIsMutable();
        AbstractMessageLite.addAll(values, this.appWindowTokens_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearAppWindowTokens() {
        this.appWindowTokens_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeAppWindowTokens(int index) {
        ensureAppWindowTokensIsMutable();
        this.appWindowTokens_.remove(index);
    }

    @Override // com.android.server.wm.TaskProtoOrBuilder
    public boolean hasFillsParent() {
        return (this.bitField0_ & 4) == 4;
    }

    @Override // com.android.server.wm.TaskProtoOrBuilder
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

    @Override // com.android.server.wm.TaskProtoOrBuilder
    public boolean hasBounds() {
        return (this.bitField0_ & 8) == 8;
    }

    @Override // com.android.server.wm.TaskProtoOrBuilder
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

    @Override // com.android.server.wm.TaskProtoOrBuilder
    public boolean hasDisplayedBounds() {
        return (this.bitField0_ & 16) == 16;
    }

    @Override // com.android.server.wm.TaskProtoOrBuilder
    public RectProto getDisplayedBounds() {
        RectProto rectProto = this.displayedBounds_;
        return rectProto == null ? RectProto.getDefaultInstance() : rectProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setDisplayedBounds(RectProto value) {
        if (value != null) {
            this.displayedBounds_ = value;
            this.bitField0_ |= 16;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setDisplayedBounds(RectProto.Builder builderForValue) {
        this.displayedBounds_ = (RectProto) builderForValue.build();
        this.bitField0_ |= 16;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeDisplayedBounds(RectProto value) {
        RectProto rectProto = this.displayedBounds_;
        if (rectProto == null || rectProto == RectProto.getDefaultInstance()) {
            this.displayedBounds_ = value;
        } else {
            this.displayedBounds_ = (RectProto) ((RectProto.Builder) RectProto.newBuilder(this.displayedBounds_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 16;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearDisplayedBounds() {
        this.displayedBounds_ = null;
        this.bitField0_ &= -17;
    }

    @Override // com.android.server.wm.TaskProtoOrBuilder
    public boolean hasDeferRemoval() {
        return (this.bitField0_ & 32) == 32;
    }

    @Override // com.android.server.wm.TaskProtoOrBuilder
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

    @Override // com.android.server.wm.TaskProtoOrBuilder
    public boolean hasSurfaceWidth() {
        return (this.bitField0_ & 64) == 64;
    }

    @Override // com.android.server.wm.TaskProtoOrBuilder
    public int getSurfaceWidth() {
        return this.surfaceWidth_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setSurfaceWidth(int value) {
        this.bitField0_ |= 64;
        this.surfaceWidth_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearSurfaceWidth() {
        this.bitField0_ &= -65;
        this.surfaceWidth_ = 0;
    }

    @Override // com.android.server.wm.TaskProtoOrBuilder
    public boolean hasSurfaceHeight() {
        return (this.bitField0_ & 128) == 128;
    }

    @Override // com.android.server.wm.TaskProtoOrBuilder
    public int getSurfaceHeight() {
        return this.surfaceHeight_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setSurfaceHeight(int value) {
        this.bitField0_ |= 128;
        this.surfaceHeight_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearSurfaceHeight() {
        this.bitField0_ &= -129;
        this.surfaceHeight_ = 0;
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeMessage(1, getWindowContainer());
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeInt32(2, this.id_);
        }
        for (int i = 0; i < this.appWindowTokens_.size(); i++) {
            output.writeMessage(3, this.appWindowTokens_.get(i));
        }
        if ((this.bitField0_ & 4) == 4) {
            output.writeBool(4, this.fillsParent_);
        }
        if ((this.bitField0_ & 8) == 8) {
            output.writeMessage(5, getBounds());
        }
        if ((this.bitField0_ & 16) == 16) {
            output.writeMessage(6, getDisplayedBounds());
        }
        if ((this.bitField0_ & 32) == 32) {
            output.writeBool(7, this.deferRemoval_);
        }
        if ((this.bitField0_ & 64) == 64) {
            output.writeInt32(8, this.surfaceWidth_);
        }
        if ((this.bitField0_ & 128) == 128) {
            output.writeInt32(9, this.surfaceHeight_);
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
        for (int i = 0; i < this.appWindowTokens_.size(); i++) {
            size2 += CodedOutputStream.computeMessageSize(3, this.appWindowTokens_.get(i));
        }
        if ((this.bitField0_ & 4) == 4) {
            size2 += CodedOutputStream.computeBoolSize(4, this.fillsParent_);
        }
        if ((this.bitField0_ & 8) == 8) {
            size2 += CodedOutputStream.computeMessageSize(5, getBounds());
        }
        if ((this.bitField0_ & 16) == 16) {
            size2 += CodedOutputStream.computeMessageSize(6, getDisplayedBounds());
        }
        if ((this.bitField0_ & 32) == 32) {
            size2 += CodedOutputStream.computeBoolSize(7, this.deferRemoval_);
        }
        if ((this.bitField0_ & 64) == 64) {
            size2 += CodedOutputStream.computeInt32Size(8, this.surfaceWidth_);
        }
        if ((this.bitField0_ & 128) == 128) {
            size2 += CodedOutputStream.computeInt32Size(9, this.surfaceHeight_);
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static TaskProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (TaskProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static TaskProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (TaskProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static TaskProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (TaskProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static TaskProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (TaskProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static TaskProto parseFrom(InputStream input) throws IOException {
        return (TaskProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static TaskProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (TaskProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static TaskProto parseDelimitedFrom(InputStream input) throws IOException {
        return (TaskProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static TaskProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (TaskProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static TaskProto parseFrom(CodedInputStream input) throws IOException {
        return (TaskProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static TaskProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (TaskProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(TaskProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<TaskProto, Builder> implements TaskProtoOrBuilder {
        private Builder() {
            super(TaskProto.DEFAULT_INSTANCE);
        }

        @Override // com.android.server.wm.TaskProtoOrBuilder
        public boolean hasWindowContainer() {
            return ((TaskProto) this.instance).hasWindowContainer();
        }

        @Override // com.android.server.wm.TaskProtoOrBuilder
        public WindowContainerProto getWindowContainer() {
            return ((TaskProto) this.instance).getWindowContainer();
        }

        public Builder setWindowContainer(WindowContainerProto value) {
            copyOnWrite();
            ((TaskProto) this.instance).setWindowContainer((TaskProto) value);
            return this;
        }

        public Builder setWindowContainer(WindowContainerProto.Builder builderForValue) {
            copyOnWrite();
            ((TaskProto) this.instance).setWindowContainer((TaskProto) builderForValue);
            return this;
        }

        public Builder mergeWindowContainer(WindowContainerProto value) {
            copyOnWrite();
            ((TaskProto) this.instance).mergeWindowContainer(value);
            return this;
        }

        public Builder clearWindowContainer() {
            copyOnWrite();
            ((TaskProto) this.instance).clearWindowContainer();
            return this;
        }

        @Override // com.android.server.wm.TaskProtoOrBuilder
        public boolean hasId() {
            return ((TaskProto) this.instance).hasId();
        }

        @Override // com.android.server.wm.TaskProtoOrBuilder
        public int getId() {
            return ((TaskProto) this.instance).getId();
        }

        public Builder setId(int value) {
            copyOnWrite();
            ((TaskProto) this.instance).setId(value);
            return this;
        }

        public Builder clearId() {
            copyOnWrite();
            ((TaskProto) this.instance).clearId();
            return this;
        }

        @Override // com.android.server.wm.TaskProtoOrBuilder
        public List<AppWindowTokenProto> getAppWindowTokensList() {
            return Collections.unmodifiableList(((TaskProto) this.instance).getAppWindowTokensList());
        }

        @Override // com.android.server.wm.TaskProtoOrBuilder
        public int getAppWindowTokensCount() {
            return ((TaskProto) this.instance).getAppWindowTokensCount();
        }

        @Override // com.android.server.wm.TaskProtoOrBuilder
        public AppWindowTokenProto getAppWindowTokens(int index) {
            return ((TaskProto) this.instance).getAppWindowTokens(index);
        }

        public Builder setAppWindowTokens(int index, AppWindowTokenProto value) {
            copyOnWrite();
            ((TaskProto) this.instance).setAppWindowTokens((TaskProto) index, (int) value);
            return this;
        }

        public Builder setAppWindowTokens(int index, AppWindowTokenProto.Builder builderForValue) {
            copyOnWrite();
            ((TaskProto) this.instance).setAppWindowTokens((TaskProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAppWindowTokens(AppWindowTokenProto value) {
            copyOnWrite();
            ((TaskProto) this.instance).addAppWindowTokens((TaskProto) value);
            return this;
        }

        public Builder addAppWindowTokens(int index, AppWindowTokenProto value) {
            copyOnWrite();
            ((TaskProto) this.instance).addAppWindowTokens((TaskProto) index, (int) value);
            return this;
        }

        public Builder addAppWindowTokens(AppWindowTokenProto.Builder builderForValue) {
            copyOnWrite();
            ((TaskProto) this.instance).addAppWindowTokens((TaskProto) builderForValue);
            return this;
        }

        public Builder addAppWindowTokens(int index, AppWindowTokenProto.Builder builderForValue) {
            copyOnWrite();
            ((TaskProto) this.instance).addAppWindowTokens((TaskProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllAppWindowTokens(Iterable<? extends AppWindowTokenProto> values) {
            copyOnWrite();
            ((TaskProto) this.instance).addAllAppWindowTokens(values);
            return this;
        }

        public Builder clearAppWindowTokens() {
            copyOnWrite();
            ((TaskProto) this.instance).clearAppWindowTokens();
            return this;
        }

        public Builder removeAppWindowTokens(int index) {
            copyOnWrite();
            ((TaskProto) this.instance).removeAppWindowTokens(index);
            return this;
        }

        @Override // com.android.server.wm.TaskProtoOrBuilder
        public boolean hasFillsParent() {
            return ((TaskProto) this.instance).hasFillsParent();
        }

        @Override // com.android.server.wm.TaskProtoOrBuilder
        public boolean getFillsParent() {
            return ((TaskProto) this.instance).getFillsParent();
        }

        public Builder setFillsParent(boolean value) {
            copyOnWrite();
            ((TaskProto) this.instance).setFillsParent(value);
            return this;
        }

        public Builder clearFillsParent() {
            copyOnWrite();
            ((TaskProto) this.instance).clearFillsParent();
            return this;
        }

        @Override // com.android.server.wm.TaskProtoOrBuilder
        public boolean hasBounds() {
            return ((TaskProto) this.instance).hasBounds();
        }

        @Override // com.android.server.wm.TaskProtoOrBuilder
        public RectProto getBounds() {
            return ((TaskProto) this.instance).getBounds();
        }

        public Builder setBounds(RectProto value) {
            copyOnWrite();
            ((TaskProto) this.instance).setBounds((TaskProto) value);
            return this;
        }

        public Builder setBounds(RectProto.Builder builderForValue) {
            copyOnWrite();
            ((TaskProto) this.instance).setBounds((TaskProto) builderForValue);
            return this;
        }

        public Builder mergeBounds(RectProto value) {
            copyOnWrite();
            ((TaskProto) this.instance).mergeBounds(value);
            return this;
        }

        public Builder clearBounds() {
            copyOnWrite();
            ((TaskProto) this.instance).clearBounds();
            return this;
        }

        @Override // com.android.server.wm.TaskProtoOrBuilder
        public boolean hasDisplayedBounds() {
            return ((TaskProto) this.instance).hasDisplayedBounds();
        }

        @Override // com.android.server.wm.TaskProtoOrBuilder
        public RectProto getDisplayedBounds() {
            return ((TaskProto) this.instance).getDisplayedBounds();
        }

        public Builder setDisplayedBounds(RectProto value) {
            copyOnWrite();
            ((TaskProto) this.instance).setDisplayedBounds((TaskProto) value);
            return this;
        }

        public Builder setDisplayedBounds(RectProto.Builder builderForValue) {
            copyOnWrite();
            ((TaskProto) this.instance).setDisplayedBounds((TaskProto) builderForValue);
            return this;
        }

        public Builder mergeDisplayedBounds(RectProto value) {
            copyOnWrite();
            ((TaskProto) this.instance).mergeDisplayedBounds(value);
            return this;
        }

        public Builder clearDisplayedBounds() {
            copyOnWrite();
            ((TaskProto) this.instance).clearDisplayedBounds();
            return this;
        }

        @Override // com.android.server.wm.TaskProtoOrBuilder
        public boolean hasDeferRemoval() {
            return ((TaskProto) this.instance).hasDeferRemoval();
        }

        @Override // com.android.server.wm.TaskProtoOrBuilder
        public boolean getDeferRemoval() {
            return ((TaskProto) this.instance).getDeferRemoval();
        }

        public Builder setDeferRemoval(boolean value) {
            copyOnWrite();
            ((TaskProto) this.instance).setDeferRemoval(value);
            return this;
        }

        public Builder clearDeferRemoval() {
            copyOnWrite();
            ((TaskProto) this.instance).clearDeferRemoval();
            return this;
        }

        @Override // com.android.server.wm.TaskProtoOrBuilder
        public boolean hasSurfaceWidth() {
            return ((TaskProto) this.instance).hasSurfaceWidth();
        }

        @Override // com.android.server.wm.TaskProtoOrBuilder
        public int getSurfaceWidth() {
            return ((TaskProto) this.instance).getSurfaceWidth();
        }

        public Builder setSurfaceWidth(int value) {
            copyOnWrite();
            ((TaskProto) this.instance).setSurfaceWidth(value);
            return this;
        }

        public Builder clearSurfaceWidth() {
            copyOnWrite();
            ((TaskProto) this.instance).clearSurfaceWidth();
            return this;
        }

        @Override // com.android.server.wm.TaskProtoOrBuilder
        public boolean hasSurfaceHeight() {
            return ((TaskProto) this.instance).hasSurfaceHeight();
        }

        @Override // com.android.server.wm.TaskProtoOrBuilder
        public int getSurfaceHeight() {
            return ((TaskProto) this.instance).getSurfaceHeight();
        }

        public Builder setSurfaceHeight(int value) {
            copyOnWrite();
            ((TaskProto) this.instance).setSurfaceHeight(value);
            return this;
        }

        public Builder clearSurfaceHeight() {
            copyOnWrite();
            ((TaskProto) this.instance).clearSurfaceHeight();
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new TaskProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                this.appWindowTokens_.makeImmutable();
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                TaskProto other = (TaskProto) arg1;
                this.windowContainer_ = (WindowContainerProto) visitor.visitMessage(this.windowContainer_, other.windowContainer_);
                this.id_ = visitor.visitInt(hasId(), this.id_, other.hasId(), other.id_);
                this.appWindowTokens_ = visitor.visitList(this.appWindowTokens_, other.appWindowTokens_);
                this.fillsParent_ = visitor.visitBoolean(hasFillsParent(), this.fillsParent_, other.hasFillsParent(), other.fillsParent_);
                this.bounds_ = (RectProto) visitor.visitMessage(this.bounds_, other.bounds_);
                this.displayedBounds_ = (RectProto) visitor.visitMessage(this.displayedBounds_, other.displayedBounds_);
                this.deferRemoval_ = visitor.visitBoolean(hasDeferRemoval(), this.deferRemoval_, other.hasDeferRemoval(), other.deferRemoval_);
                this.surfaceWidth_ = visitor.visitInt(hasSurfaceWidth(), this.surfaceWidth_, other.hasSurfaceWidth(), other.surfaceWidth_);
                this.surfaceHeight_ = visitor.visitInt(hasSurfaceHeight(), this.surfaceHeight_, other.hasSurfaceHeight(), other.surfaceHeight_);
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
                        if (tag == 0) {
                            done = true;
                        } else if (tag == 10) {
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
                        } else if (tag == 16) {
                            this.bitField0_ |= 2;
                            this.id_ = input.readInt32();
                        } else if (tag == 26) {
                            if (!this.appWindowTokens_.isModifiable()) {
                                this.appWindowTokens_ = GeneratedMessageLite.mutableCopy(this.appWindowTokens_);
                            }
                            this.appWindowTokens_.add((AppWindowTokenProto) input.readMessage(AppWindowTokenProto.parser(), extensionRegistry));
                        } else if (tag == 32) {
                            this.bitField0_ |= 4;
                            this.fillsParent_ = input.readBool();
                        } else if (tag == 42) {
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
                        } else if (tag == 50) {
                            RectProto.Builder subBuilder3 = null;
                            if ((this.bitField0_ & 16) == 16) {
                                subBuilder3 = (RectProto.Builder) this.displayedBounds_.toBuilder();
                            }
                            this.displayedBounds_ = (RectProto) input.readMessage(RectProto.parser(), extensionRegistry);
                            if (subBuilder3 != null) {
                                subBuilder3.mergeFrom((GeneratedMessageLite) this.displayedBounds_);
                                this.displayedBounds_ = (RectProto) subBuilder3.buildPartial();
                            }
                            this.bitField0_ = 16 | this.bitField0_;
                        } else if (tag == 56) {
                            this.bitField0_ |= 32;
                            this.deferRemoval_ = input.readBool();
                        } else if (tag == 64) {
                            this.bitField0_ = 64 | this.bitField0_;
                            this.surfaceWidth_ = input.readInt32();
                        } else if (tag == 72) {
                            this.bitField0_ |= 128;
                            this.surfaceHeight_ = input.readInt32();
                        } else if (!parseUnknownField(tag, input)) {
                            done = true;
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
                    synchronized (TaskProto.class) {
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

    public static TaskProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<TaskProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
