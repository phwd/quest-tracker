package com.android.server.am;

import android.graphics.RectProto;
import com.android.server.am.ActivityRecordProto;
import com.android.server.wm.ConfigurationContainerProto;
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

public final class TaskRecordProto extends GeneratedMessageLite<TaskRecordProto, Builder> implements TaskRecordProtoOrBuilder {
    public static final int ACTIVITIES_FIELD_NUMBER = 3;
    public static final int ACTIVITY_TYPE_FIELD_NUMBER = 8;
    public static final int BOUNDS_FIELD_NUMBER = 11;
    public static final int CONFIGURATION_CONTAINER_FIELD_NUMBER = 1;
    private static final TaskRecordProto DEFAULT_INSTANCE = new TaskRecordProto();
    public static final int FULLSCREEN_FIELD_NUMBER = 10;
    public static final int ID_FIELD_NUMBER = 2;
    public static final int LAST_NON_FULLSCREEN_BOUNDS_FIELD_NUMBER = 5;
    public static final int MIN_HEIGHT_FIELD_NUMBER = 13;
    public static final int MIN_WIDTH_FIELD_NUMBER = 12;
    public static final int ORIG_ACTIVITY_FIELD_NUMBER = 7;
    private static volatile Parser<TaskRecordProto> PARSER = null;
    public static final int REAL_ACTIVITY_FIELD_NUMBER = 6;
    public static final int RESIZE_MODE_FIELD_NUMBER = 9;
    public static final int STACK_ID_FIELD_NUMBER = 4;
    private Internal.ProtobufList<ActivityRecordProto> activities_ = emptyProtobufList();
    private int activityType_ = 0;
    private int bitField0_;
    private RectProto bounds_;
    private ConfigurationContainerProto configurationContainer_;
    private boolean fullscreen_ = false;
    private int id_ = 0;
    private RectProto lastNonFullscreenBounds_;
    private int minHeight_ = 0;
    private int minWidth_ = 0;
    private String origActivity_ = "";
    private String realActivity_ = "";
    private int resizeMode_ = 0;
    private int stackId_ = 0;

    private TaskRecordProto() {
    }

    @Override // com.android.server.am.TaskRecordProtoOrBuilder
    public boolean hasConfigurationContainer() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // com.android.server.am.TaskRecordProtoOrBuilder
    public ConfigurationContainerProto getConfigurationContainer() {
        ConfigurationContainerProto configurationContainerProto = this.configurationContainer_;
        return configurationContainerProto == null ? ConfigurationContainerProto.getDefaultInstance() : configurationContainerProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setConfigurationContainer(ConfigurationContainerProto value) {
        if (value != null) {
            this.configurationContainer_ = value;
            this.bitField0_ |= 1;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setConfigurationContainer(ConfigurationContainerProto.Builder builderForValue) {
        this.configurationContainer_ = (ConfigurationContainerProto) builderForValue.build();
        this.bitField0_ |= 1;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeConfigurationContainer(ConfigurationContainerProto value) {
        ConfigurationContainerProto configurationContainerProto = this.configurationContainer_;
        if (configurationContainerProto == null || configurationContainerProto == ConfigurationContainerProto.getDefaultInstance()) {
            this.configurationContainer_ = value;
        } else {
            this.configurationContainer_ = (ConfigurationContainerProto) ((ConfigurationContainerProto.Builder) ConfigurationContainerProto.newBuilder(this.configurationContainer_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 1;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearConfigurationContainer() {
        this.configurationContainer_ = null;
        this.bitField0_ &= -2;
    }

    @Override // com.android.server.am.TaskRecordProtoOrBuilder
    public boolean hasId() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // com.android.server.am.TaskRecordProtoOrBuilder
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

    @Override // com.android.server.am.TaskRecordProtoOrBuilder
    public List<ActivityRecordProto> getActivitiesList() {
        return this.activities_;
    }

    public List<? extends ActivityRecordProtoOrBuilder> getActivitiesOrBuilderList() {
        return this.activities_;
    }

    @Override // com.android.server.am.TaskRecordProtoOrBuilder
    public int getActivitiesCount() {
        return this.activities_.size();
    }

    @Override // com.android.server.am.TaskRecordProtoOrBuilder
    public ActivityRecordProto getActivities(int index) {
        return this.activities_.get(index);
    }

    public ActivityRecordProtoOrBuilder getActivitiesOrBuilder(int index) {
        return this.activities_.get(index);
    }

    private void ensureActivitiesIsMutable() {
        if (!this.activities_.isModifiable()) {
            this.activities_ = GeneratedMessageLite.mutableCopy(this.activities_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setActivities(int index, ActivityRecordProto value) {
        if (value != null) {
            ensureActivitiesIsMutable();
            this.activities_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setActivities(int index, ActivityRecordProto.Builder builderForValue) {
        ensureActivitiesIsMutable();
        this.activities_.set(index, (ActivityRecordProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addActivities(ActivityRecordProto value) {
        if (value != null) {
            ensureActivitiesIsMutable();
            this.activities_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addActivities(int index, ActivityRecordProto value) {
        if (value != null) {
            ensureActivitiesIsMutable();
            this.activities_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addActivities(ActivityRecordProto.Builder builderForValue) {
        ensureActivitiesIsMutable();
        this.activities_.add((ActivityRecordProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addActivities(int index, ActivityRecordProto.Builder builderForValue) {
        ensureActivitiesIsMutable();
        this.activities_.add(index, (ActivityRecordProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllActivities(Iterable<? extends ActivityRecordProto> values) {
        ensureActivitiesIsMutable();
        AbstractMessageLite.addAll(values, this.activities_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearActivities() {
        this.activities_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeActivities(int index) {
        ensureActivitiesIsMutable();
        this.activities_.remove(index);
    }

    @Override // com.android.server.am.TaskRecordProtoOrBuilder
    public boolean hasStackId() {
        return (this.bitField0_ & 4) == 4;
    }

    @Override // com.android.server.am.TaskRecordProtoOrBuilder
    public int getStackId() {
        return this.stackId_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setStackId(int value) {
        this.bitField0_ |= 4;
        this.stackId_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearStackId() {
        this.bitField0_ &= -5;
        this.stackId_ = 0;
    }

    @Override // com.android.server.am.TaskRecordProtoOrBuilder
    public boolean hasLastNonFullscreenBounds() {
        return (this.bitField0_ & 8) == 8;
    }

    @Override // com.android.server.am.TaskRecordProtoOrBuilder
    public RectProto getLastNonFullscreenBounds() {
        RectProto rectProto = this.lastNonFullscreenBounds_;
        return rectProto == null ? RectProto.getDefaultInstance() : rectProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setLastNonFullscreenBounds(RectProto value) {
        if (value != null) {
            this.lastNonFullscreenBounds_ = value;
            this.bitField0_ |= 8;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setLastNonFullscreenBounds(RectProto.Builder builderForValue) {
        this.lastNonFullscreenBounds_ = (RectProto) builderForValue.build();
        this.bitField0_ |= 8;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeLastNonFullscreenBounds(RectProto value) {
        RectProto rectProto = this.lastNonFullscreenBounds_;
        if (rectProto == null || rectProto == RectProto.getDefaultInstance()) {
            this.lastNonFullscreenBounds_ = value;
        } else {
            this.lastNonFullscreenBounds_ = (RectProto) ((RectProto.Builder) RectProto.newBuilder(this.lastNonFullscreenBounds_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 8;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearLastNonFullscreenBounds() {
        this.lastNonFullscreenBounds_ = null;
        this.bitField0_ &= -9;
    }

    @Override // com.android.server.am.TaskRecordProtoOrBuilder
    public boolean hasRealActivity() {
        return (this.bitField0_ & 16) == 16;
    }

    @Override // com.android.server.am.TaskRecordProtoOrBuilder
    public String getRealActivity() {
        return this.realActivity_;
    }

    @Override // com.android.server.am.TaskRecordProtoOrBuilder
    public ByteString getRealActivityBytes() {
        return ByteString.copyFromUtf8(this.realActivity_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setRealActivity(String value) {
        if (value != null) {
            this.bitField0_ |= 16;
            this.realActivity_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearRealActivity() {
        this.bitField0_ &= -17;
        this.realActivity_ = getDefaultInstance().getRealActivity();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setRealActivityBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 16;
            this.realActivity_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // com.android.server.am.TaskRecordProtoOrBuilder
    public boolean hasOrigActivity() {
        return (this.bitField0_ & 32) == 32;
    }

    @Override // com.android.server.am.TaskRecordProtoOrBuilder
    public String getOrigActivity() {
        return this.origActivity_;
    }

    @Override // com.android.server.am.TaskRecordProtoOrBuilder
    public ByteString getOrigActivityBytes() {
        return ByteString.copyFromUtf8(this.origActivity_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setOrigActivity(String value) {
        if (value != null) {
            this.bitField0_ |= 32;
            this.origActivity_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearOrigActivity() {
        this.bitField0_ &= -33;
        this.origActivity_ = getDefaultInstance().getOrigActivity();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setOrigActivityBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 32;
            this.origActivity_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // com.android.server.am.TaskRecordProtoOrBuilder
    public boolean hasActivityType() {
        return (this.bitField0_ & 64) == 64;
    }

    @Override // com.android.server.am.TaskRecordProtoOrBuilder
    public int getActivityType() {
        return this.activityType_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setActivityType(int value) {
        this.bitField0_ |= 64;
        this.activityType_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearActivityType() {
        this.bitField0_ &= -65;
        this.activityType_ = 0;
    }

    @Override // com.android.server.am.TaskRecordProtoOrBuilder
    public boolean hasResizeMode() {
        return (this.bitField0_ & 128) == 128;
    }

    @Override // com.android.server.am.TaskRecordProtoOrBuilder
    public int getResizeMode() {
        return this.resizeMode_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setResizeMode(int value) {
        this.bitField0_ |= 128;
        this.resizeMode_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearResizeMode() {
        this.bitField0_ &= -129;
        this.resizeMode_ = 0;
    }

    @Override // com.android.server.am.TaskRecordProtoOrBuilder
    public boolean hasFullscreen() {
        return (this.bitField0_ & 256) == 256;
    }

    @Override // com.android.server.am.TaskRecordProtoOrBuilder
    public boolean getFullscreen() {
        return this.fullscreen_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setFullscreen(boolean value) {
        this.bitField0_ |= 256;
        this.fullscreen_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearFullscreen() {
        this.bitField0_ &= -257;
        this.fullscreen_ = false;
    }

    @Override // com.android.server.am.TaskRecordProtoOrBuilder
    public boolean hasBounds() {
        return (this.bitField0_ & 512) == 512;
    }

    @Override // com.android.server.am.TaskRecordProtoOrBuilder
    public RectProto getBounds() {
        RectProto rectProto = this.bounds_;
        return rectProto == null ? RectProto.getDefaultInstance() : rectProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setBounds(RectProto value) {
        if (value != null) {
            this.bounds_ = value;
            this.bitField0_ |= 512;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setBounds(RectProto.Builder builderForValue) {
        this.bounds_ = (RectProto) builderForValue.build();
        this.bitField0_ |= 512;
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
        this.bitField0_ |= 512;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearBounds() {
        this.bounds_ = null;
        this.bitField0_ &= -513;
    }

    @Override // com.android.server.am.TaskRecordProtoOrBuilder
    public boolean hasMinWidth() {
        return (this.bitField0_ & 1024) == 1024;
    }

    @Override // com.android.server.am.TaskRecordProtoOrBuilder
    public int getMinWidth() {
        return this.minWidth_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setMinWidth(int value) {
        this.bitField0_ |= 1024;
        this.minWidth_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearMinWidth() {
        this.bitField0_ &= -1025;
        this.minWidth_ = 0;
    }

    @Override // com.android.server.am.TaskRecordProtoOrBuilder
    public boolean hasMinHeight() {
        return (this.bitField0_ & 2048) == 2048;
    }

    @Override // com.android.server.am.TaskRecordProtoOrBuilder
    public int getMinHeight() {
        return this.minHeight_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setMinHeight(int value) {
        this.bitField0_ |= 2048;
        this.minHeight_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearMinHeight() {
        this.bitField0_ &= -2049;
        this.minHeight_ = 0;
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeMessage(1, getConfigurationContainer());
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeInt32(2, this.id_);
        }
        for (int i = 0; i < this.activities_.size(); i++) {
            output.writeMessage(3, this.activities_.get(i));
        }
        if ((this.bitField0_ & 4) == 4) {
            output.writeInt32(4, this.stackId_);
        }
        if ((this.bitField0_ & 8) == 8) {
            output.writeMessage(5, getLastNonFullscreenBounds());
        }
        if ((this.bitField0_ & 16) == 16) {
            output.writeString(6, getRealActivity());
        }
        if ((this.bitField0_ & 32) == 32) {
            output.writeString(7, getOrigActivity());
        }
        if ((this.bitField0_ & 64) == 64) {
            output.writeInt32(8, this.activityType_);
        }
        if ((this.bitField0_ & 128) == 128) {
            output.writeInt32(9, this.resizeMode_);
        }
        if ((this.bitField0_ & 256) == 256) {
            output.writeBool(10, this.fullscreen_);
        }
        if ((this.bitField0_ & 512) == 512) {
            output.writeMessage(11, getBounds());
        }
        if ((this.bitField0_ & 1024) == 1024) {
            output.writeInt32(12, this.minWidth_);
        }
        if ((this.bitField0_ & 2048) == 2048) {
            output.writeInt32(13, this.minHeight_);
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
            size2 = 0 + CodedOutputStream.computeMessageSize(1, getConfigurationContainer());
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeInt32Size(2, this.id_);
        }
        for (int i = 0; i < this.activities_.size(); i++) {
            size2 += CodedOutputStream.computeMessageSize(3, this.activities_.get(i));
        }
        if ((this.bitField0_ & 4) == 4) {
            size2 += CodedOutputStream.computeInt32Size(4, this.stackId_);
        }
        if ((this.bitField0_ & 8) == 8) {
            size2 += CodedOutputStream.computeMessageSize(5, getLastNonFullscreenBounds());
        }
        if ((this.bitField0_ & 16) == 16) {
            size2 += CodedOutputStream.computeStringSize(6, getRealActivity());
        }
        if ((this.bitField0_ & 32) == 32) {
            size2 += CodedOutputStream.computeStringSize(7, getOrigActivity());
        }
        if ((this.bitField0_ & 64) == 64) {
            size2 += CodedOutputStream.computeInt32Size(8, this.activityType_);
        }
        if ((this.bitField0_ & 128) == 128) {
            size2 += CodedOutputStream.computeInt32Size(9, this.resizeMode_);
        }
        if ((this.bitField0_ & 256) == 256) {
            size2 += CodedOutputStream.computeBoolSize(10, this.fullscreen_);
        }
        if ((this.bitField0_ & 512) == 512) {
            size2 += CodedOutputStream.computeMessageSize(11, getBounds());
        }
        if ((this.bitField0_ & 1024) == 1024) {
            size2 += CodedOutputStream.computeInt32Size(12, this.minWidth_);
        }
        if ((this.bitField0_ & 2048) == 2048) {
            size2 += CodedOutputStream.computeInt32Size(13, this.minHeight_);
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static TaskRecordProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (TaskRecordProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static TaskRecordProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (TaskRecordProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static TaskRecordProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (TaskRecordProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static TaskRecordProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (TaskRecordProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static TaskRecordProto parseFrom(InputStream input) throws IOException {
        return (TaskRecordProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static TaskRecordProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (TaskRecordProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static TaskRecordProto parseDelimitedFrom(InputStream input) throws IOException {
        return (TaskRecordProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static TaskRecordProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (TaskRecordProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static TaskRecordProto parseFrom(CodedInputStream input) throws IOException {
        return (TaskRecordProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static TaskRecordProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (TaskRecordProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(TaskRecordProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<TaskRecordProto, Builder> implements TaskRecordProtoOrBuilder {
        private Builder() {
            super(TaskRecordProto.DEFAULT_INSTANCE);
        }

        @Override // com.android.server.am.TaskRecordProtoOrBuilder
        public boolean hasConfigurationContainer() {
            return ((TaskRecordProto) this.instance).hasConfigurationContainer();
        }

        @Override // com.android.server.am.TaskRecordProtoOrBuilder
        public ConfigurationContainerProto getConfigurationContainer() {
            return ((TaskRecordProto) this.instance).getConfigurationContainer();
        }

        public Builder setConfigurationContainer(ConfigurationContainerProto value) {
            copyOnWrite();
            ((TaskRecordProto) this.instance).setConfigurationContainer((TaskRecordProto) value);
            return this;
        }

        public Builder setConfigurationContainer(ConfigurationContainerProto.Builder builderForValue) {
            copyOnWrite();
            ((TaskRecordProto) this.instance).setConfigurationContainer((TaskRecordProto) builderForValue);
            return this;
        }

        public Builder mergeConfigurationContainer(ConfigurationContainerProto value) {
            copyOnWrite();
            ((TaskRecordProto) this.instance).mergeConfigurationContainer(value);
            return this;
        }

        public Builder clearConfigurationContainer() {
            copyOnWrite();
            ((TaskRecordProto) this.instance).clearConfigurationContainer();
            return this;
        }

        @Override // com.android.server.am.TaskRecordProtoOrBuilder
        public boolean hasId() {
            return ((TaskRecordProto) this.instance).hasId();
        }

        @Override // com.android.server.am.TaskRecordProtoOrBuilder
        public int getId() {
            return ((TaskRecordProto) this.instance).getId();
        }

        public Builder setId(int value) {
            copyOnWrite();
            ((TaskRecordProto) this.instance).setId(value);
            return this;
        }

        public Builder clearId() {
            copyOnWrite();
            ((TaskRecordProto) this.instance).clearId();
            return this;
        }

        @Override // com.android.server.am.TaskRecordProtoOrBuilder
        public List<ActivityRecordProto> getActivitiesList() {
            return Collections.unmodifiableList(((TaskRecordProto) this.instance).getActivitiesList());
        }

        @Override // com.android.server.am.TaskRecordProtoOrBuilder
        public int getActivitiesCount() {
            return ((TaskRecordProto) this.instance).getActivitiesCount();
        }

        @Override // com.android.server.am.TaskRecordProtoOrBuilder
        public ActivityRecordProto getActivities(int index) {
            return ((TaskRecordProto) this.instance).getActivities(index);
        }

        public Builder setActivities(int index, ActivityRecordProto value) {
            copyOnWrite();
            ((TaskRecordProto) this.instance).setActivities((TaskRecordProto) index, (int) value);
            return this;
        }

        public Builder setActivities(int index, ActivityRecordProto.Builder builderForValue) {
            copyOnWrite();
            ((TaskRecordProto) this.instance).setActivities((TaskRecordProto) index, (int) builderForValue);
            return this;
        }

        public Builder addActivities(ActivityRecordProto value) {
            copyOnWrite();
            ((TaskRecordProto) this.instance).addActivities((TaskRecordProto) value);
            return this;
        }

        public Builder addActivities(int index, ActivityRecordProto value) {
            copyOnWrite();
            ((TaskRecordProto) this.instance).addActivities((TaskRecordProto) index, (int) value);
            return this;
        }

        public Builder addActivities(ActivityRecordProto.Builder builderForValue) {
            copyOnWrite();
            ((TaskRecordProto) this.instance).addActivities((TaskRecordProto) builderForValue);
            return this;
        }

        public Builder addActivities(int index, ActivityRecordProto.Builder builderForValue) {
            copyOnWrite();
            ((TaskRecordProto) this.instance).addActivities((TaskRecordProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllActivities(Iterable<? extends ActivityRecordProto> values) {
            copyOnWrite();
            ((TaskRecordProto) this.instance).addAllActivities(values);
            return this;
        }

        public Builder clearActivities() {
            copyOnWrite();
            ((TaskRecordProto) this.instance).clearActivities();
            return this;
        }

        public Builder removeActivities(int index) {
            copyOnWrite();
            ((TaskRecordProto) this.instance).removeActivities(index);
            return this;
        }

        @Override // com.android.server.am.TaskRecordProtoOrBuilder
        public boolean hasStackId() {
            return ((TaskRecordProto) this.instance).hasStackId();
        }

        @Override // com.android.server.am.TaskRecordProtoOrBuilder
        public int getStackId() {
            return ((TaskRecordProto) this.instance).getStackId();
        }

        public Builder setStackId(int value) {
            copyOnWrite();
            ((TaskRecordProto) this.instance).setStackId(value);
            return this;
        }

        public Builder clearStackId() {
            copyOnWrite();
            ((TaskRecordProto) this.instance).clearStackId();
            return this;
        }

        @Override // com.android.server.am.TaskRecordProtoOrBuilder
        public boolean hasLastNonFullscreenBounds() {
            return ((TaskRecordProto) this.instance).hasLastNonFullscreenBounds();
        }

        @Override // com.android.server.am.TaskRecordProtoOrBuilder
        public RectProto getLastNonFullscreenBounds() {
            return ((TaskRecordProto) this.instance).getLastNonFullscreenBounds();
        }

        public Builder setLastNonFullscreenBounds(RectProto value) {
            copyOnWrite();
            ((TaskRecordProto) this.instance).setLastNonFullscreenBounds((TaskRecordProto) value);
            return this;
        }

        public Builder setLastNonFullscreenBounds(RectProto.Builder builderForValue) {
            copyOnWrite();
            ((TaskRecordProto) this.instance).setLastNonFullscreenBounds((TaskRecordProto) builderForValue);
            return this;
        }

        public Builder mergeLastNonFullscreenBounds(RectProto value) {
            copyOnWrite();
            ((TaskRecordProto) this.instance).mergeLastNonFullscreenBounds(value);
            return this;
        }

        public Builder clearLastNonFullscreenBounds() {
            copyOnWrite();
            ((TaskRecordProto) this.instance).clearLastNonFullscreenBounds();
            return this;
        }

        @Override // com.android.server.am.TaskRecordProtoOrBuilder
        public boolean hasRealActivity() {
            return ((TaskRecordProto) this.instance).hasRealActivity();
        }

        @Override // com.android.server.am.TaskRecordProtoOrBuilder
        public String getRealActivity() {
            return ((TaskRecordProto) this.instance).getRealActivity();
        }

        @Override // com.android.server.am.TaskRecordProtoOrBuilder
        public ByteString getRealActivityBytes() {
            return ((TaskRecordProto) this.instance).getRealActivityBytes();
        }

        public Builder setRealActivity(String value) {
            copyOnWrite();
            ((TaskRecordProto) this.instance).setRealActivity(value);
            return this;
        }

        public Builder clearRealActivity() {
            copyOnWrite();
            ((TaskRecordProto) this.instance).clearRealActivity();
            return this;
        }

        public Builder setRealActivityBytes(ByteString value) {
            copyOnWrite();
            ((TaskRecordProto) this.instance).setRealActivityBytes(value);
            return this;
        }

        @Override // com.android.server.am.TaskRecordProtoOrBuilder
        public boolean hasOrigActivity() {
            return ((TaskRecordProto) this.instance).hasOrigActivity();
        }

        @Override // com.android.server.am.TaskRecordProtoOrBuilder
        public String getOrigActivity() {
            return ((TaskRecordProto) this.instance).getOrigActivity();
        }

        @Override // com.android.server.am.TaskRecordProtoOrBuilder
        public ByteString getOrigActivityBytes() {
            return ((TaskRecordProto) this.instance).getOrigActivityBytes();
        }

        public Builder setOrigActivity(String value) {
            copyOnWrite();
            ((TaskRecordProto) this.instance).setOrigActivity(value);
            return this;
        }

        public Builder clearOrigActivity() {
            copyOnWrite();
            ((TaskRecordProto) this.instance).clearOrigActivity();
            return this;
        }

        public Builder setOrigActivityBytes(ByteString value) {
            copyOnWrite();
            ((TaskRecordProto) this.instance).setOrigActivityBytes(value);
            return this;
        }

        @Override // com.android.server.am.TaskRecordProtoOrBuilder
        public boolean hasActivityType() {
            return ((TaskRecordProto) this.instance).hasActivityType();
        }

        @Override // com.android.server.am.TaskRecordProtoOrBuilder
        public int getActivityType() {
            return ((TaskRecordProto) this.instance).getActivityType();
        }

        public Builder setActivityType(int value) {
            copyOnWrite();
            ((TaskRecordProto) this.instance).setActivityType(value);
            return this;
        }

        public Builder clearActivityType() {
            copyOnWrite();
            ((TaskRecordProto) this.instance).clearActivityType();
            return this;
        }

        @Override // com.android.server.am.TaskRecordProtoOrBuilder
        public boolean hasResizeMode() {
            return ((TaskRecordProto) this.instance).hasResizeMode();
        }

        @Override // com.android.server.am.TaskRecordProtoOrBuilder
        public int getResizeMode() {
            return ((TaskRecordProto) this.instance).getResizeMode();
        }

        public Builder setResizeMode(int value) {
            copyOnWrite();
            ((TaskRecordProto) this.instance).setResizeMode(value);
            return this;
        }

        public Builder clearResizeMode() {
            copyOnWrite();
            ((TaskRecordProto) this.instance).clearResizeMode();
            return this;
        }

        @Override // com.android.server.am.TaskRecordProtoOrBuilder
        public boolean hasFullscreen() {
            return ((TaskRecordProto) this.instance).hasFullscreen();
        }

        @Override // com.android.server.am.TaskRecordProtoOrBuilder
        public boolean getFullscreen() {
            return ((TaskRecordProto) this.instance).getFullscreen();
        }

        public Builder setFullscreen(boolean value) {
            copyOnWrite();
            ((TaskRecordProto) this.instance).setFullscreen(value);
            return this;
        }

        public Builder clearFullscreen() {
            copyOnWrite();
            ((TaskRecordProto) this.instance).clearFullscreen();
            return this;
        }

        @Override // com.android.server.am.TaskRecordProtoOrBuilder
        public boolean hasBounds() {
            return ((TaskRecordProto) this.instance).hasBounds();
        }

        @Override // com.android.server.am.TaskRecordProtoOrBuilder
        public RectProto getBounds() {
            return ((TaskRecordProto) this.instance).getBounds();
        }

        public Builder setBounds(RectProto value) {
            copyOnWrite();
            ((TaskRecordProto) this.instance).setBounds((TaskRecordProto) value);
            return this;
        }

        public Builder setBounds(RectProto.Builder builderForValue) {
            copyOnWrite();
            ((TaskRecordProto) this.instance).setBounds((TaskRecordProto) builderForValue);
            return this;
        }

        public Builder mergeBounds(RectProto value) {
            copyOnWrite();
            ((TaskRecordProto) this.instance).mergeBounds(value);
            return this;
        }

        public Builder clearBounds() {
            copyOnWrite();
            ((TaskRecordProto) this.instance).clearBounds();
            return this;
        }

        @Override // com.android.server.am.TaskRecordProtoOrBuilder
        public boolean hasMinWidth() {
            return ((TaskRecordProto) this.instance).hasMinWidth();
        }

        @Override // com.android.server.am.TaskRecordProtoOrBuilder
        public int getMinWidth() {
            return ((TaskRecordProto) this.instance).getMinWidth();
        }

        public Builder setMinWidth(int value) {
            copyOnWrite();
            ((TaskRecordProto) this.instance).setMinWidth(value);
            return this;
        }

        public Builder clearMinWidth() {
            copyOnWrite();
            ((TaskRecordProto) this.instance).clearMinWidth();
            return this;
        }

        @Override // com.android.server.am.TaskRecordProtoOrBuilder
        public boolean hasMinHeight() {
            return ((TaskRecordProto) this.instance).hasMinHeight();
        }

        @Override // com.android.server.am.TaskRecordProtoOrBuilder
        public int getMinHeight() {
            return ((TaskRecordProto) this.instance).getMinHeight();
        }

        public Builder setMinHeight(int value) {
            copyOnWrite();
            ((TaskRecordProto) this.instance).setMinHeight(value);
            return this;
        }

        public Builder clearMinHeight() {
            copyOnWrite();
            ((TaskRecordProto) this.instance).clearMinHeight();
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new TaskRecordProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                this.activities_.makeImmutable();
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                TaskRecordProto other = (TaskRecordProto) arg1;
                this.configurationContainer_ = (ConfigurationContainerProto) visitor.visitMessage(this.configurationContainer_, other.configurationContainer_);
                this.id_ = visitor.visitInt(hasId(), this.id_, other.hasId(), other.id_);
                this.activities_ = visitor.visitList(this.activities_, other.activities_);
                this.stackId_ = visitor.visitInt(hasStackId(), this.stackId_, other.hasStackId(), other.stackId_);
                this.lastNonFullscreenBounds_ = (RectProto) visitor.visitMessage(this.lastNonFullscreenBounds_, other.lastNonFullscreenBounds_);
                this.realActivity_ = visitor.visitString(hasRealActivity(), this.realActivity_, other.hasRealActivity(), other.realActivity_);
                this.origActivity_ = visitor.visitString(hasOrigActivity(), this.origActivity_, other.hasOrigActivity(), other.origActivity_);
                this.activityType_ = visitor.visitInt(hasActivityType(), this.activityType_, other.hasActivityType(), other.activityType_);
                this.resizeMode_ = visitor.visitInt(hasResizeMode(), this.resizeMode_, other.hasResizeMode(), other.resizeMode_);
                this.fullscreen_ = visitor.visitBoolean(hasFullscreen(), this.fullscreen_, other.hasFullscreen(), other.fullscreen_);
                this.bounds_ = (RectProto) visitor.visitMessage(this.bounds_, other.bounds_);
                this.minWidth_ = visitor.visitInt(hasMinWidth(), this.minWidth_, other.hasMinWidth(), other.minWidth_);
                this.minHeight_ = visitor.visitInt(hasMinHeight(), this.minHeight_, other.hasMinHeight(), other.minHeight_);
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
                                ConfigurationContainerProto.Builder subBuilder = null;
                                if ((this.bitField0_ & 1) == 1) {
                                    subBuilder = (ConfigurationContainerProto.Builder) this.configurationContainer_.toBuilder();
                                }
                                this.configurationContainer_ = (ConfigurationContainerProto) input.readMessage(ConfigurationContainerProto.parser(), extensionRegistry);
                                if (subBuilder != null) {
                                    subBuilder.mergeFrom((GeneratedMessageLite) this.configurationContainer_);
                                    this.configurationContainer_ = (ConfigurationContainerProto) subBuilder.buildPartial();
                                }
                                this.bitField0_ |= 1;
                                break;
                            case 16:
                                this.bitField0_ |= 2;
                                this.id_ = input.readInt32();
                                break;
                            case 26:
                                if (!this.activities_.isModifiable()) {
                                    this.activities_ = GeneratedMessageLite.mutableCopy(this.activities_);
                                }
                                this.activities_.add((ActivityRecordProto) input.readMessage(ActivityRecordProto.parser(), extensionRegistry));
                                break;
                            case 32:
                                this.bitField0_ |= 4;
                                this.stackId_ = input.readInt32();
                                break;
                            case 42:
                                RectProto.Builder subBuilder2 = null;
                                if ((this.bitField0_ & 8) == 8) {
                                    subBuilder2 = (RectProto.Builder) this.lastNonFullscreenBounds_.toBuilder();
                                }
                                this.lastNonFullscreenBounds_ = (RectProto) input.readMessage(RectProto.parser(), extensionRegistry);
                                if (subBuilder2 != null) {
                                    subBuilder2.mergeFrom((GeneratedMessageLite) this.lastNonFullscreenBounds_);
                                    this.lastNonFullscreenBounds_ = (RectProto) subBuilder2.buildPartial();
                                }
                                this.bitField0_ |= 8;
                                break;
                            case 50:
                                String s = input.readString();
                                this.bitField0_ |= 16;
                                this.realActivity_ = s;
                                break;
                            case 58:
                                String s2 = input.readString();
                                this.bitField0_ |= 32;
                                this.origActivity_ = s2;
                                break;
                            case 64:
                                this.bitField0_ |= 64;
                                this.activityType_ = input.readInt32();
                                break;
                            case 72:
                                this.bitField0_ |= 128;
                                this.resizeMode_ = input.readInt32();
                                break;
                            case 80:
                                this.bitField0_ |= 256;
                                this.fullscreen_ = input.readBool();
                                break;
                            case 90:
                                RectProto.Builder subBuilder3 = null;
                                if ((this.bitField0_ & 512) == 512) {
                                    subBuilder3 = (RectProto.Builder) this.bounds_.toBuilder();
                                }
                                this.bounds_ = (RectProto) input.readMessage(RectProto.parser(), extensionRegistry);
                                if (subBuilder3 != null) {
                                    subBuilder3.mergeFrom((GeneratedMessageLite) this.bounds_);
                                    this.bounds_ = (RectProto) subBuilder3.buildPartial();
                                }
                                this.bitField0_ |= 512;
                                break;
                            case 96:
                                this.bitField0_ |= 1024;
                                this.minWidth_ = input.readInt32();
                                break;
                            case 104:
                                this.bitField0_ |= 2048;
                                this.minHeight_ = input.readInt32();
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
                    synchronized (TaskRecordProto.class) {
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

    public static TaskRecordProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<TaskRecordProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
