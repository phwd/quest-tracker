package com.android.server.am;

import android.graphics.RectProto;
import com.android.server.am.TaskRecordProto;
import com.android.server.wm.ConfigurationContainerProto;
import com.android.server.wm.IdentifierProto;
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

public final class ActivityStackProto extends GeneratedMessageLite<ActivityStackProto, Builder> implements ActivityStackProtoOrBuilder {
    public static final int BOUNDS_FIELD_NUMBER = 7;
    public static final int CONFIGURATION_CONTAINER_FIELD_NUMBER = 1;
    private static final ActivityStackProto DEFAULT_INSTANCE = new ActivityStackProto();
    public static final int DISPLAY_ID_FIELD_NUMBER = 5;
    public static final int FULLSCREEN_FIELD_NUMBER = 6;
    public static final int ID_FIELD_NUMBER = 2;
    private static volatile Parser<ActivityStackProto> PARSER = null;
    public static final int RESUMED_ACTIVITY_FIELD_NUMBER = 4;
    public static final int TASKS_FIELD_NUMBER = 3;
    private int bitField0_;
    private RectProto bounds_;
    private ConfigurationContainerProto configurationContainer_;
    private int displayId_ = 0;
    private boolean fullscreen_ = false;
    private int id_ = 0;
    private IdentifierProto resumedActivity_;
    private Internal.ProtobufList<TaskRecordProto> tasks_ = emptyProtobufList();

    private ActivityStackProto() {
    }

    @Override // com.android.server.am.ActivityStackProtoOrBuilder
    public boolean hasConfigurationContainer() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // com.android.server.am.ActivityStackProtoOrBuilder
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

    @Override // com.android.server.am.ActivityStackProtoOrBuilder
    public boolean hasId() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // com.android.server.am.ActivityStackProtoOrBuilder
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

    @Override // com.android.server.am.ActivityStackProtoOrBuilder
    public List<TaskRecordProto> getTasksList() {
        return this.tasks_;
    }

    public List<? extends TaskRecordProtoOrBuilder> getTasksOrBuilderList() {
        return this.tasks_;
    }

    @Override // com.android.server.am.ActivityStackProtoOrBuilder
    public int getTasksCount() {
        return this.tasks_.size();
    }

    @Override // com.android.server.am.ActivityStackProtoOrBuilder
    public TaskRecordProto getTasks(int index) {
        return this.tasks_.get(index);
    }

    public TaskRecordProtoOrBuilder getTasksOrBuilder(int index) {
        return this.tasks_.get(index);
    }

    private void ensureTasksIsMutable() {
        if (!this.tasks_.isModifiable()) {
            this.tasks_ = GeneratedMessageLite.mutableCopy(this.tasks_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setTasks(int index, TaskRecordProto value) {
        if (value != null) {
            ensureTasksIsMutable();
            this.tasks_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setTasks(int index, TaskRecordProto.Builder builderForValue) {
        ensureTasksIsMutable();
        this.tasks_.set(index, (TaskRecordProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addTasks(TaskRecordProto value) {
        if (value != null) {
            ensureTasksIsMutable();
            this.tasks_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addTasks(int index, TaskRecordProto value) {
        if (value != null) {
            ensureTasksIsMutable();
            this.tasks_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addTasks(TaskRecordProto.Builder builderForValue) {
        ensureTasksIsMutable();
        this.tasks_.add((TaskRecordProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addTasks(int index, TaskRecordProto.Builder builderForValue) {
        ensureTasksIsMutable();
        this.tasks_.add(index, (TaskRecordProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllTasks(Iterable<? extends TaskRecordProto> values) {
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

    @Override // com.android.server.am.ActivityStackProtoOrBuilder
    public boolean hasResumedActivity() {
        return (this.bitField0_ & 4) == 4;
    }

    @Override // com.android.server.am.ActivityStackProtoOrBuilder
    public IdentifierProto getResumedActivity() {
        IdentifierProto identifierProto = this.resumedActivity_;
        return identifierProto == null ? IdentifierProto.getDefaultInstance() : identifierProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setResumedActivity(IdentifierProto value) {
        if (value != null) {
            this.resumedActivity_ = value;
            this.bitField0_ |= 4;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setResumedActivity(IdentifierProto.Builder builderForValue) {
        this.resumedActivity_ = (IdentifierProto) builderForValue.build();
        this.bitField0_ |= 4;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeResumedActivity(IdentifierProto value) {
        IdentifierProto identifierProto = this.resumedActivity_;
        if (identifierProto == null || identifierProto == IdentifierProto.getDefaultInstance()) {
            this.resumedActivity_ = value;
        } else {
            this.resumedActivity_ = (IdentifierProto) ((IdentifierProto.Builder) IdentifierProto.newBuilder(this.resumedActivity_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 4;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearResumedActivity() {
        this.resumedActivity_ = null;
        this.bitField0_ &= -5;
    }

    @Override // com.android.server.am.ActivityStackProtoOrBuilder
    public boolean hasDisplayId() {
        return (this.bitField0_ & 8) == 8;
    }

    @Override // com.android.server.am.ActivityStackProtoOrBuilder
    public int getDisplayId() {
        return this.displayId_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setDisplayId(int value) {
        this.bitField0_ |= 8;
        this.displayId_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearDisplayId() {
        this.bitField0_ &= -9;
        this.displayId_ = 0;
    }

    @Override // com.android.server.am.ActivityStackProtoOrBuilder
    public boolean hasFullscreen() {
        return (this.bitField0_ & 16) == 16;
    }

    @Override // com.android.server.am.ActivityStackProtoOrBuilder
    public boolean getFullscreen() {
        return this.fullscreen_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setFullscreen(boolean value) {
        this.bitField0_ |= 16;
        this.fullscreen_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearFullscreen() {
        this.bitField0_ &= -17;
        this.fullscreen_ = false;
    }

    @Override // com.android.server.am.ActivityStackProtoOrBuilder
    public boolean hasBounds() {
        return (this.bitField0_ & 32) == 32;
    }

    @Override // com.android.server.am.ActivityStackProtoOrBuilder
    public RectProto getBounds() {
        RectProto rectProto = this.bounds_;
        return rectProto == null ? RectProto.getDefaultInstance() : rectProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setBounds(RectProto value) {
        if (value != null) {
            this.bounds_ = value;
            this.bitField0_ |= 32;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setBounds(RectProto.Builder builderForValue) {
        this.bounds_ = (RectProto) builderForValue.build();
        this.bitField0_ |= 32;
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
        this.bitField0_ |= 32;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearBounds() {
        this.bounds_ = null;
        this.bitField0_ &= -33;
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeMessage(1, getConfigurationContainer());
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeInt32(2, this.id_);
        }
        for (int i = 0; i < this.tasks_.size(); i++) {
            output.writeMessage(3, this.tasks_.get(i));
        }
        if ((this.bitField0_ & 4) == 4) {
            output.writeMessage(4, getResumedActivity());
        }
        if ((this.bitField0_ & 8) == 8) {
            output.writeInt32(5, this.displayId_);
        }
        if ((this.bitField0_ & 16) == 16) {
            output.writeBool(6, this.fullscreen_);
        }
        if ((this.bitField0_ & 32) == 32) {
            output.writeMessage(7, getBounds());
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
        for (int i = 0; i < this.tasks_.size(); i++) {
            size2 += CodedOutputStream.computeMessageSize(3, this.tasks_.get(i));
        }
        if ((this.bitField0_ & 4) == 4) {
            size2 += CodedOutputStream.computeMessageSize(4, getResumedActivity());
        }
        if ((this.bitField0_ & 8) == 8) {
            size2 += CodedOutputStream.computeInt32Size(5, this.displayId_);
        }
        if ((this.bitField0_ & 16) == 16) {
            size2 += CodedOutputStream.computeBoolSize(6, this.fullscreen_);
        }
        if ((this.bitField0_ & 32) == 32) {
            size2 += CodedOutputStream.computeMessageSize(7, getBounds());
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static ActivityStackProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (ActivityStackProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static ActivityStackProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (ActivityStackProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static ActivityStackProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (ActivityStackProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static ActivityStackProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (ActivityStackProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static ActivityStackProto parseFrom(InputStream input) throws IOException {
        return (ActivityStackProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static ActivityStackProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (ActivityStackProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static ActivityStackProto parseDelimitedFrom(InputStream input) throws IOException {
        return (ActivityStackProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static ActivityStackProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (ActivityStackProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static ActivityStackProto parseFrom(CodedInputStream input) throws IOException {
        return (ActivityStackProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static ActivityStackProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (ActivityStackProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(ActivityStackProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<ActivityStackProto, Builder> implements ActivityStackProtoOrBuilder {
        private Builder() {
            super(ActivityStackProto.DEFAULT_INSTANCE);
        }

        @Override // com.android.server.am.ActivityStackProtoOrBuilder
        public boolean hasConfigurationContainer() {
            return ((ActivityStackProto) this.instance).hasConfigurationContainer();
        }

        @Override // com.android.server.am.ActivityStackProtoOrBuilder
        public ConfigurationContainerProto getConfigurationContainer() {
            return ((ActivityStackProto) this.instance).getConfigurationContainer();
        }

        public Builder setConfigurationContainer(ConfigurationContainerProto value) {
            copyOnWrite();
            ((ActivityStackProto) this.instance).setConfigurationContainer((ActivityStackProto) value);
            return this;
        }

        public Builder setConfigurationContainer(ConfigurationContainerProto.Builder builderForValue) {
            copyOnWrite();
            ((ActivityStackProto) this.instance).setConfigurationContainer((ActivityStackProto) builderForValue);
            return this;
        }

        public Builder mergeConfigurationContainer(ConfigurationContainerProto value) {
            copyOnWrite();
            ((ActivityStackProto) this.instance).mergeConfigurationContainer(value);
            return this;
        }

        public Builder clearConfigurationContainer() {
            copyOnWrite();
            ((ActivityStackProto) this.instance).clearConfigurationContainer();
            return this;
        }

        @Override // com.android.server.am.ActivityStackProtoOrBuilder
        public boolean hasId() {
            return ((ActivityStackProto) this.instance).hasId();
        }

        @Override // com.android.server.am.ActivityStackProtoOrBuilder
        public int getId() {
            return ((ActivityStackProto) this.instance).getId();
        }

        public Builder setId(int value) {
            copyOnWrite();
            ((ActivityStackProto) this.instance).setId(value);
            return this;
        }

        public Builder clearId() {
            copyOnWrite();
            ((ActivityStackProto) this.instance).clearId();
            return this;
        }

        @Override // com.android.server.am.ActivityStackProtoOrBuilder
        public List<TaskRecordProto> getTasksList() {
            return Collections.unmodifiableList(((ActivityStackProto) this.instance).getTasksList());
        }

        @Override // com.android.server.am.ActivityStackProtoOrBuilder
        public int getTasksCount() {
            return ((ActivityStackProto) this.instance).getTasksCount();
        }

        @Override // com.android.server.am.ActivityStackProtoOrBuilder
        public TaskRecordProto getTasks(int index) {
            return ((ActivityStackProto) this.instance).getTasks(index);
        }

        public Builder setTasks(int index, TaskRecordProto value) {
            copyOnWrite();
            ((ActivityStackProto) this.instance).setTasks((ActivityStackProto) index, (int) value);
            return this;
        }

        public Builder setTasks(int index, TaskRecordProto.Builder builderForValue) {
            copyOnWrite();
            ((ActivityStackProto) this.instance).setTasks((ActivityStackProto) index, (int) builderForValue);
            return this;
        }

        public Builder addTasks(TaskRecordProto value) {
            copyOnWrite();
            ((ActivityStackProto) this.instance).addTasks((ActivityStackProto) value);
            return this;
        }

        public Builder addTasks(int index, TaskRecordProto value) {
            copyOnWrite();
            ((ActivityStackProto) this.instance).addTasks((ActivityStackProto) index, (int) value);
            return this;
        }

        public Builder addTasks(TaskRecordProto.Builder builderForValue) {
            copyOnWrite();
            ((ActivityStackProto) this.instance).addTasks((ActivityStackProto) builderForValue);
            return this;
        }

        public Builder addTasks(int index, TaskRecordProto.Builder builderForValue) {
            copyOnWrite();
            ((ActivityStackProto) this.instance).addTasks((ActivityStackProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllTasks(Iterable<? extends TaskRecordProto> values) {
            copyOnWrite();
            ((ActivityStackProto) this.instance).addAllTasks(values);
            return this;
        }

        public Builder clearTasks() {
            copyOnWrite();
            ((ActivityStackProto) this.instance).clearTasks();
            return this;
        }

        public Builder removeTasks(int index) {
            copyOnWrite();
            ((ActivityStackProto) this.instance).removeTasks(index);
            return this;
        }

        @Override // com.android.server.am.ActivityStackProtoOrBuilder
        public boolean hasResumedActivity() {
            return ((ActivityStackProto) this.instance).hasResumedActivity();
        }

        @Override // com.android.server.am.ActivityStackProtoOrBuilder
        public IdentifierProto getResumedActivity() {
            return ((ActivityStackProto) this.instance).getResumedActivity();
        }

        public Builder setResumedActivity(IdentifierProto value) {
            copyOnWrite();
            ((ActivityStackProto) this.instance).setResumedActivity((ActivityStackProto) value);
            return this;
        }

        public Builder setResumedActivity(IdentifierProto.Builder builderForValue) {
            copyOnWrite();
            ((ActivityStackProto) this.instance).setResumedActivity((ActivityStackProto) builderForValue);
            return this;
        }

        public Builder mergeResumedActivity(IdentifierProto value) {
            copyOnWrite();
            ((ActivityStackProto) this.instance).mergeResumedActivity(value);
            return this;
        }

        public Builder clearResumedActivity() {
            copyOnWrite();
            ((ActivityStackProto) this.instance).clearResumedActivity();
            return this;
        }

        @Override // com.android.server.am.ActivityStackProtoOrBuilder
        public boolean hasDisplayId() {
            return ((ActivityStackProto) this.instance).hasDisplayId();
        }

        @Override // com.android.server.am.ActivityStackProtoOrBuilder
        public int getDisplayId() {
            return ((ActivityStackProto) this.instance).getDisplayId();
        }

        public Builder setDisplayId(int value) {
            copyOnWrite();
            ((ActivityStackProto) this.instance).setDisplayId(value);
            return this;
        }

        public Builder clearDisplayId() {
            copyOnWrite();
            ((ActivityStackProto) this.instance).clearDisplayId();
            return this;
        }

        @Override // com.android.server.am.ActivityStackProtoOrBuilder
        public boolean hasFullscreen() {
            return ((ActivityStackProto) this.instance).hasFullscreen();
        }

        @Override // com.android.server.am.ActivityStackProtoOrBuilder
        public boolean getFullscreen() {
            return ((ActivityStackProto) this.instance).getFullscreen();
        }

        public Builder setFullscreen(boolean value) {
            copyOnWrite();
            ((ActivityStackProto) this.instance).setFullscreen(value);
            return this;
        }

        public Builder clearFullscreen() {
            copyOnWrite();
            ((ActivityStackProto) this.instance).clearFullscreen();
            return this;
        }

        @Override // com.android.server.am.ActivityStackProtoOrBuilder
        public boolean hasBounds() {
            return ((ActivityStackProto) this.instance).hasBounds();
        }

        @Override // com.android.server.am.ActivityStackProtoOrBuilder
        public RectProto getBounds() {
            return ((ActivityStackProto) this.instance).getBounds();
        }

        public Builder setBounds(RectProto value) {
            copyOnWrite();
            ((ActivityStackProto) this.instance).setBounds((ActivityStackProto) value);
            return this;
        }

        public Builder setBounds(RectProto.Builder builderForValue) {
            copyOnWrite();
            ((ActivityStackProto) this.instance).setBounds((ActivityStackProto) builderForValue);
            return this;
        }

        public Builder mergeBounds(RectProto value) {
            copyOnWrite();
            ((ActivityStackProto) this.instance).mergeBounds(value);
            return this;
        }

        public Builder clearBounds() {
            copyOnWrite();
            ((ActivityStackProto) this.instance).clearBounds();
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new ActivityStackProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                this.tasks_.makeImmutable();
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                ActivityStackProto other = (ActivityStackProto) arg1;
                this.configurationContainer_ = (ConfigurationContainerProto) visitor.visitMessage(this.configurationContainer_, other.configurationContainer_);
                this.id_ = visitor.visitInt(hasId(), this.id_, other.hasId(), other.id_);
                this.tasks_ = visitor.visitList(this.tasks_, other.tasks_);
                this.resumedActivity_ = (IdentifierProto) visitor.visitMessage(this.resumedActivity_, other.resumedActivity_);
                this.displayId_ = visitor.visitInt(hasDisplayId(), this.displayId_, other.hasDisplayId(), other.displayId_);
                this.fullscreen_ = visitor.visitBoolean(hasFullscreen(), this.fullscreen_, other.hasFullscreen(), other.fullscreen_);
                this.bounds_ = (RectProto) visitor.visitMessage(this.bounds_, other.bounds_);
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
                        } else if (tag == 16) {
                            this.bitField0_ |= 2;
                            this.id_ = input.readInt32();
                        } else if (tag == 26) {
                            if (!this.tasks_.isModifiable()) {
                                this.tasks_ = GeneratedMessageLite.mutableCopy(this.tasks_);
                            }
                            this.tasks_.add((TaskRecordProto) input.readMessage(TaskRecordProto.parser(), extensionRegistry));
                        } else if (tag == 34) {
                            IdentifierProto.Builder subBuilder2 = null;
                            if ((this.bitField0_ & 4) == 4) {
                                subBuilder2 = (IdentifierProto.Builder) this.resumedActivity_.toBuilder();
                            }
                            this.resumedActivity_ = (IdentifierProto) input.readMessage(IdentifierProto.parser(), extensionRegistry);
                            if (subBuilder2 != null) {
                                subBuilder2.mergeFrom((GeneratedMessageLite) this.resumedActivity_);
                                this.resumedActivity_ = (IdentifierProto) subBuilder2.buildPartial();
                            }
                            this.bitField0_ |= 4;
                        } else if (tag == 40) {
                            this.bitField0_ |= 8;
                            this.displayId_ = input.readInt32();
                        } else if (tag == 48) {
                            this.bitField0_ = 16 | this.bitField0_;
                            this.fullscreen_ = input.readBool();
                        } else if (tag == 58) {
                            RectProto.Builder subBuilder3 = null;
                            if ((this.bitField0_ & 32) == 32) {
                                subBuilder3 = (RectProto.Builder) this.bounds_.toBuilder();
                            }
                            this.bounds_ = (RectProto) input.readMessage(RectProto.parser(), extensionRegistry);
                            if (subBuilder3 != null) {
                                subBuilder3.mergeFrom((GeneratedMessageLite) this.bounds_);
                                this.bounds_ = (RectProto) subBuilder3.buildPartial();
                            }
                            this.bitField0_ |= 32;
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
                    synchronized (ActivityStackProto.class) {
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

    public static ActivityStackProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<ActivityStackProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
