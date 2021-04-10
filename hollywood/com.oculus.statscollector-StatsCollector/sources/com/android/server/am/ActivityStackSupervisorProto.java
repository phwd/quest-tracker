package com.android.server.am;

import com.android.server.am.ActivityDisplayProto;
import com.android.server.am.KeyguardControllerProto;
import com.android.server.wm.ConfigurationContainerProto;
import com.android.server.wm.IdentifierProto;
import com.android.server.wm.IdentifierProtoOrBuilder;
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

public final class ActivityStackSupervisorProto extends GeneratedMessageLite<ActivityStackSupervisorProto, Builder> implements ActivityStackSupervisorProtoOrBuilder {
    public static final int CONFIGURATION_CONTAINER_FIELD_NUMBER = 1;
    private static final ActivityStackSupervisorProto DEFAULT_INSTANCE = new ActivityStackSupervisorProto();
    public static final int DISPLAYS_FIELD_NUMBER = 2;
    public static final int FOCUSED_STACK_ID_FIELD_NUMBER = 4;
    public static final int IS_HOME_RECENTS_COMPONENT_FIELD_NUMBER = 6;
    public static final int KEYGUARD_CONTROLLER_FIELD_NUMBER = 3;
    private static volatile Parser<ActivityStackSupervisorProto> PARSER = null;
    public static final int PENDING_ACTIVITIES_FIELD_NUMBER = 7;
    public static final int RESUMED_ACTIVITY_FIELD_NUMBER = 5;
    private int bitField0_;
    private ConfigurationContainerProto configurationContainer_;
    private Internal.ProtobufList<ActivityDisplayProto> displays_ = emptyProtobufList();
    private int focusedStackId_ = 0;
    private boolean isHomeRecentsComponent_ = false;
    private KeyguardControllerProto keyguardController_;
    private Internal.ProtobufList<IdentifierProto> pendingActivities_ = emptyProtobufList();
    private IdentifierProto resumedActivity_;

    private ActivityStackSupervisorProto() {
    }

    @Override // com.android.server.am.ActivityStackSupervisorProtoOrBuilder
    public boolean hasConfigurationContainer() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // com.android.server.am.ActivityStackSupervisorProtoOrBuilder
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

    @Override // com.android.server.am.ActivityStackSupervisorProtoOrBuilder
    public List<ActivityDisplayProto> getDisplaysList() {
        return this.displays_;
    }

    public List<? extends ActivityDisplayProtoOrBuilder> getDisplaysOrBuilderList() {
        return this.displays_;
    }

    @Override // com.android.server.am.ActivityStackSupervisorProtoOrBuilder
    public int getDisplaysCount() {
        return this.displays_.size();
    }

    @Override // com.android.server.am.ActivityStackSupervisorProtoOrBuilder
    public ActivityDisplayProto getDisplays(int index) {
        return this.displays_.get(index);
    }

    public ActivityDisplayProtoOrBuilder getDisplaysOrBuilder(int index) {
        return this.displays_.get(index);
    }

    private void ensureDisplaysIsMutable() {
        if (!this.displays_.isModifiable()) {
            this.displays_ = GeneratedMessageLite.mutableCopy(this.displays_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setDisplays(int index, ActivityDisplayProto value) {
        if (value != null) {
            ensureDisplaysIsMutable();
            this.displays_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setDisplays(int index, ActivityDisplayProto.Builder builderForValue) {
        ensureDisplaysIsMutable();
        this.displays_.set(index, (ActivityDisplayProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addDisplays(ActivityDisplayProto value) {
        if (value != null) {
            ensureDisplaysIsMutable();
            this.displays_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addDisplays(int index, ActivityDisplayProto value) {
        if (value != null) {
            ensureDisplaysIsMutable();
            this.displays_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addDisplays(ActivityDisplayProto.Builder builderForValue) {
        ensureDisplaysIsMutable();
        this.displays_.add((ActivityDisplayProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addDisplays(int index, ActivityDisplayProto.Builder builderForValue) {
        ensureDisplaysIsMutable();
        this.displays_.add(index, (ActivityDisplayProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllDisplays(Iterable<? extends ActivityDisplayProto> values) {
        ensureDisplaysIsMutable();
        AbstractMessageLite.addAll(values, this.displays_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearDisplays() {
        this.displays_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeDisplays(int index) {
        ensureDisplaysIsMutable();
        this.displays_.remove(index);
    }

    @Override // com.android.server.am.ActivityStackSupervisorProtoOrBuilder
    public boolean hasKeyguardController() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // com.android.server.am.ActivityStackSupervisorProtoOrBuilder
    public KeyguardControllerProto getKeyguardController() {
        KeyguardControllerProto keyguardControllerProto = this.keyguardController_;
        return keyguardControllerProto == null ? KeyguardControllerProto.getDefaultInstance() : keyguardControllerProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setKeyguardController(KeyguardControllerProto value) {
        if (value != null) {
            this.keyguardController_ = value;
            this.bitField0_ |= 2;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setKeyguardController(KeyguardControllerProto.Builder builderForValue) {
        this.keyguardController_ = (KeyguardControllerProto) builderForValue.build();
        this.bitField0_ |= 2;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeKeyguardController(KeyguardControllerProto value) {
        KeyguardControllerProto keyguardControllerProto = this.keyguardController_;
        if (keyguardControllerProto == null || keyguardControllerProto == KeyguardControllerProto.getDefaultInstance()) {
            this.keyguardController_ = value;
        } else {
            this.keyguardController_ = (KeyguardControllerProto) ((KeyguardControllerProto.Builder) KeyguardControllerProto.newBuilder(this.keyguardController_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 2;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearKeyguardController() {
        this.keyguardController_ = null;
        this.bitField0_ &= -3;
    }

    @Override // com.android.server.am.ActivityStackSupervisorProtoOrBuilder
    public boolean hasFocusedStackId() {
        return (this.bitField0_ & 4) == 4;
    }

    @Override // com.android.server.am.ActivityStackSupervisorProtoOrBuilder
    public int getFocusedStackId() {
        return this.focusedStackId_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setFocusedStackId(int value) {
        this.bitField0_ |= 4;
        this.focusedStackId_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearFocusedStackId() {
        this.bitField0_ &= -5;
        this.focusedStackId_ = 0;
    }

    @Override // com.android.server.am.ActivityStackSupervisorProtoOrBuilder
    public boolean hasResumedActivity() {
        return (this.bitField0_ & 8) == 8;
    }

    @Override // com.android.server.am.ActivityStackSupervisorProtoOrBuilder
    public IdentifierProto getResumedActivity() {
        IdentifierProto identifierProto = this.resumedActivity_;
        return identifierProto == null ? IdentifierProto.getDefaultInstance() : identifierProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setResumedActivity(IdentifierProto value) {
        if (value != null) {
            this.resumedActivity_ = value;
            this.bitField0_ |= 8;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setResumedActivity(IdentifierProto.Builder builderForValue) {
        this.resumedActivity_ = (IdentifierProto) builderForValue.build();
        this.bitField0_ |= 8;
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
        this.bitField0_ |= 8;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearResumedActivity() {
        this.resumedActivity_ = null;
        this.bitField0_ &= -9;
    }

    @Override // com.android.server.am.ActivityStackSupervisorProtoOrBuilder
    public boolean hasIsHomeRecentsComponent() {
        return (this.bitField0_ & 16) == 16;
    }

    @Override // com.android.server.am.ActivityStackSupervisorProtoOrBuilder
    public boolean getIsHomeRecentsComponent() {
        return this.isHomeRecentsComponent_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setIsHomeRecentsComponent(boolean value) {
        this.bitField0_ |= 16;
        this.isHomeRecentsComponent_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearIsHomeRecentsComponent() {
        this.bitField0_ &= -17;
        this.isHomeRecentsComponent_ = false;
    }

    @Override // com.android.server.am.ActivityStackSupervisorProtoOrBuilder
    public List<IdentifierProto> getPendingActivitiesList() {
        return this.pendingActivities_;
    }

    public List<? extends IdentifierProtoOrBuilder> getPendingActivitiesOrBuilderList() {
        return this.pendingActivities_;
    }

    @Override // com.android.server.am.ActivityStackSupervisorProtoOrBuilder
    public int getPendingActivitiesCount() {
        return this.pendingActivities_.size();
    }

    @Override // com.android.server.am.ActivityStackSupervisorProtoOrBuilder
    public IdentifierProto getPendingActivities(int index) {
        return this.pendingActivities_.get(index);
    }

    public IdentifierProtoOrBuilder getPendingActivitiesOrBuilder(int index) {
        return this.pendingActivities_.get(index);
    }

    private void ensurePendingActivitiesIsMutable() {
        if (!this.pendingActivities_.isModifiable()) {
            this.pendingActivities_ = GeneratedMessageLite.mutableCopy(this.pendingActivities_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPendingActivities(int index, IdentifierProto value) {
        if (value != null) {
            ensurePendingActivitiesIsMutable();
            this.pendingActivities_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPendingActivities(int index, IdentifierProto.Builder builderForValue) {
        ensurePendingActivitiesIsMutable();
        this.pendingActivities_.set(index, (IdentifierProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addPendingActivities(IdentifierProto value) {
        if (value != null) {
            ensurePendingActivitiesIsMutable();
            this.pendingActivities_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addPendingActivities(int index, IdentifierProto value) {
        if (value != null) {
            ensurePendingActivitiesIsMutable();
            this.pendingActivities_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addPendingActivities(IdentifierProto.Builder builderForValue) {
        ensurePendingActivitiesIsMutable();
        this.pendingActivities_.add((IdentifierProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addPendingActivities(int index, IdentifierProto.Builder builderForValue) {
        ensurePendingActivitiesIsMutable();
        this.pendingActivities_.add(index, (IdentifierProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllPendingActivities(Iterable<? extends IdentifierProto> values) {
        ensurePendingActivitiesIsMutable();
        AbstractMessageLite.addAll(values, this.pendingActivities_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearPendingActivities() {
        this.pendingActivities_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removePendingActivities(int index) {
        ensurePendingActivitiesIsMutable();
        this.pendingActivities_.remove(index);
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeMessage(1, getConfigurationContainer());
        }
        for (int i = 0; i < this.displays_.size(); i++) {
            output.writeMessage(2, this.displays_.get(i));
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeMessage(3, getKeyguardController());
        }
        if ((this.bitField0_ & 4) == 4) {
            output.writeInt32(4, this.focusedStackId_);
        }
        if ((this.bitField0_ & 8) == 8) {
            output.writeMessage(5, getResumedActivity());
        }
        if ((this.bitField0_ & 16) == 16) {
            output.writeBool(6, this.isHomeRecentsComponent_);
        }
        for (int i2 = 0; i2 < this.pendingActivities_.size(); i2++) {
            output.writeMessage(7, this.pendingActivities_.get(i2));
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
        for (int i = 0; i < this.displays_.size(); i++) {
            size2 += CodedOutputStream.computeMessageSize(2, this.displays_.get(i));
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeMessageSize(3, getKeyguardController());
        }
        if ((this.bitField0_ & 4) == 4) {
            size2 += CodedOutputStream.computeInt32Size(4, this.focusedStackId_);
        }
        if ((this.bitField0_ & 8) == 8) {
            size2 += CodedOutputStream.computeMessageSize(5, getResumedActivity());
        }
        if ((this.bitField0_ & 16) == 16) {
            size2 += CodedOutputStream.computeBoolSize(6, this.isHomeRecentsComponent_);
        }
        for (int i2 = 0; i2 < this.pendingActivities_.size(); i2++) {
            size2 += CodedOutputStream.computeMessageSize(7, this.pendingActivities_.get(i2));
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static ActivityStackSupervisorProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (ActivityStackSupervisorProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static ActivityStackSupervisorProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (ActivityStackSupervisorProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static ActivityStackSupervisorProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (ActivityStackSupervisorProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static ActivityStackSupervisorProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (ActivityStackSupervisorProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static ActivityStackSupervisorProto parseFrom(InputStream input) throws IOException {
        return (ActivityStackSupervisorProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static ActivityStackSupervisorProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (ActivityStackSupervisorProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static ActivityStackSupervisorProto parseDelimitedFrom(InputStream input) throws IOException {
        return (ActivityStackSupervisorProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static ActivityStackSupervisorProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (ActivityStackSupervisorProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static ActivityStackSupervisorProto parseFrom(CodedInputStream input) throws IOException {
        return (ActivityStackSupervisorProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static ActivityStackSupervisorProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (ActivityStackSupervisorProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(ActivityStackSupervisorProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<ActivityStackSupervisorProto, Builder> implements ActivityStackSupervisorProtoOrBuilder {
        private Builder() {
            super(ActivityStackSupervisorProto.DEFAULT_INSTANCE);
        }

        @Override // com.android.server.am.ActivityStackSupervisorProtoOrBuilder
        public boolean hasConfigurationContainer() {
            return ((ActivityStackSupervisorProto) this.instance).hasConfigurationContainer();
        }

        @Override // com.android.server.am.ActivityStackSupervisorProtoOrBuilder
        public ConfigurationContainerProto getConfigurationContainer() {
            return ((ActivityStackSupervisorProto) this.instance).getConfigurationContainer();
        }

        public Builder setConfigurationContainer(ConfigurationContainerProto value) {
            copyOnWrite();
            ((ActivityStackSupervisorProto) this.instance).setConfigurationContainer((ActivityStackSupervisorProto) value);
            return this;
        }

        public Builder setConfigurationContainer(ConfigurationContainerProto.Builder builderForValue) {
            copyOnWrite();
            ((ActivityStackSupervisorProto) this.instance).setConfigurationContainer((ActivityStackSupervisorProto) builderForValue);
            return this;
        }

        public Builder mergeConfigurationContainer(ConfigurationContainerProto value) {
            copyOnWrite();
            ((ActivityStackSupervisorProto) this.instance).mergeConfigurationContainer(value);
            return this;
        }

        public Builder clearConfigurationContainer() {
            copyOnWrite();
            ((ActivityStackSupervisorProto) this.instance).clearConfigurationContainer();
            return this;
        }

        @Override // com.android.server.am.ActivityStackSupervisorProtoOrBuilder
        public List<ActivityDisplayProto> getDisplaysList() {
            return Collections.unmodifiableList(((ActivityStackSupervisorProto) this.instance).getDisplaysList());
        }

        @Override // com.android.server.am.ActivityStackSupervisorProtoOrBuilder
        public int getDisplaysCount() {
            return ((ActivityStackSupervisorProto) this.instance).getDisplaysCount();
        }

        @Override // com.android.server.am.ActivityStackSupervisorProtoOrBuilder
        public ActivityDisplayProto getDisplays(int index) {
            return ((ActivityStackSupervisorProto) this.instance).getDisplays(index);
        }

        public Builder setDisplays(int index, ActivityDisplayProto value) {
            copyOnWrite();
            ((ActivityStackSupervisorProto) this.instance).setDisplays((ActivityStackSupervisorProto) index, (int) value);
            return this;
        }

        public Builder setDisplays(int index, ActivityDisplayProto.Builder builderForValue) {
            copyOnWrite();
            ((ActivityStackSupervisorProto) this.instance).setDisplays((ActivityStackSupervisorProto) index, (int) builderForValue);
            return this;
        }

        public Builder addDisplays(ActivityDisplayProto value) {
            copyOnWrite();
            ((ActivityStackSupervisorProto) this.instance).addDisplays((ActivityStackSupervisorProto) value);
            return this;
        }

        public Builder addDisplays(int index, ActivityDisplayProto value) {
            copyOnWrite();
            ((ActivityStackSupervisorProto) this.instance).addDisplays((ActivityStackSupervisorProto) index, (int) value);
            return this;
        }

        public Builder addDisplays(ActivityDisplayProto.Builder builderForValue) {
            copyOnWrite();
            ((ActivityStackSupervisorProto) this.instance).addDisplays((ActivityStackSupervisorProto) builderForValue);
            return this;
        }

        public Builder addDisplays(int index, ActivityDisplayProto.Builder builderForValue) {
            copyOnWrite();
            ((ActivityStackSupervisorProto) this.instance).addDisplays((ActivityStackSupervisorProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllDisplays(Iterable<? extends ActivityDisplayProto> values) {
            copyOnWrite();
            ((ActivityStackSupervisorProto) this.instance).addAllDisplays(values);
            return this;
        }

        public Builder clearDisplays() {
            copyOnWrite();
            ((ActivityStackSupervisorProto) this.instance).clearDisplays();
            return this;
        }

        public Builder removeDisplays(int index) {
            copyOnWrite();
            ((ActivityStackSupervisorProto) this.instance).removeDisplays(index);
            return this;
        }

        @Override // com.android.server.am.ActivityStackSupervisorProtoOrBuilder
        public boolean hasKeyguardController() {
            return ((ActivityStackSupervisorProto) this.instance).hasKeyguardController();
        }

        @Override // com.android.server.am.ActivityStackSupervisorProtoOrBuilder
        public KeyguardControllerProto getKeyguardController() {
            return ((ActivityStackSupervisorProto) this.instance).getKeyguardController();
        }

        public Builder setKeyguardController(KeyguardControllerProto value) {
            copyOnWrite();
            ((ActivityStackSupervisorProto) this.instance).setKeyguardController((ActivityStackSupervisorProto) value);
            return this;
        }

        public Builder setKeyguardController(KeyguardControllerProto.Builder builderForValue) {
            copyOnWrite();
            ((ActivityStackSupervisorProto) this.instance).setKeyguardController((ActivityStackSupervisorProto) builderForValue);
            return this;
        }

        public Builder mergeKeyguardController(KeyguardControllerProto value) {
            copyOnWrite();
            ((ActivityStackSupervisorProto) this.instance).mergeKeyguardController(value);
            return this;
        }

        public Builder clearKeyguardController() {
            copyOnWrite();
            ((ActivityStackSupervisorProto) this.instance).clearKeyguardController();
            return this;
        }

        @Override // com.android.server.am.ActivityStackSupervisorProtoOrBuilder
        public boolean hasFocusedStackId() {
            return ((ActivityStackSupervisorProto) this.instance).hasFocusedStackId();
        }

        @Override // com.android.server.am.ActivityStackSupervisorProtoOrBuilder
        public int getFocusedStackId() {
            return ((ActivityStackSupervisorProto) this.instance).getFocusedStackId();
        }

        public Builder setFocusedStackId(int value) {
            copyOnWrite();
            ((ActivityStackSupervisorProto) this.instance).setFocusedStackId(value);
            return this;
        }

        public Builder clearFocusedStackId() {
            copyOnWrite();
            ((ActivityStackSupervisorProto) this.instance).clearFocusedStackId();
            return this;
        }

        @Override // com.android.server.am.ActivityStackSupervisorProtoOrBuilder
        public boolean hasResumedActivity() {
            return ((ActivityStackSupervisorProto) this.instance).hasResumedActivity();
        }

        @Override // com.android.server.am.ActivityStackSupervisorProtoOrBuilder
        public IdentifierProto getResumedActivity() {
            return ((ActivityStackSupervisorProto) this.instance).getResumedActivity();
        }

        public Builder setResumedActivity(IdentifierProto value) {
            copyOnWrite();
            ((ActivityStackSupervisorProto) this.instance).setResumedActivity((ActivityStackSupervisorProto) value);
            return this;
        }

        public Builder setResumedActivity(IdentifierProto.Builder builderForValue) {
            copyOnWrite();
            ((ActivityStackSupervisorProto) this.instance).setResumedActivity((ActivityStackSupervisorProto) builderForValue);
            return this;
        }

        public Builder mergeResumedActivity(IdentifierProto value) {
            copyOnWrite();
            ((ActivityStackSupervisorProto) this.instance).mergeResumedActivity(value);
            return this;
        }

        public Builder clearResumedActivity() {
            copyOnWrite();
            ((ActivityStackSupervisorProto) this.instance).clearResumedActivity();
            return this;
        }

        @Override // com.android.server.am.ActivityStackSupervisorProtoOrBuilder
        public boolean hasIsHomeRecentsComponent() {
            return ((ActivityStackSupervisorProto) this.instance).hasIsHomeRecentsComponent();
        }

        @Override // com.android.server.am.ActivityStackSupervisorProtoOrBuilder
        public boolean getIsHomeRecentsComponent() {
            return ((ActivityStackSupervisorProto) this.instance).getIsHomeRecentsComponent();
        }

        public Builder setIsHomeRecentsComponent(boolean value) {
            copyOnWrite();
            ((ActivityStackSupervisorProto) this.instance).setIsHomeRecentsComponent(value);
            return this;
        }

        public Builder clearIsHomeRecentsComponent() {
            copyOnWrite();
            ((ActivityStackSupervisorProto) this.instance).clearIsHomeRecentsComponent();
            return this;
        }

        @Override // com.android.server.am.ActivityStackSupervisorProtoOrBuilder
        public List<IdentifierProto> getPendingActivitiesList() {
            return Collections.unmodifiableList(((ActivityStackSupervisorProto) this.instance).getPendingActivitiesList());
        }

        @Override // com.android.server.am.ActivityStackSupervisorProtoOrBuilder
        public int getPendingActivitiesCount() {
            return ((ActivityStackSupervisorProto) this.instance).getPendingActivitiesCount();
        }

        @Override // com.android.server.am.ActivityStackSupervisorProtoOrBuilder
        public IdentifierProto getPendingActivities(int index) {
            return ((ActivityStackSupervisorProto) this.instance).getPendingActivities(index);
        }

        public Builder setPendingActivities(int index, IdentifierProto value) {
            copyOnWrite();
            ((ActivityStackSupervisorProto) this.instance).setPendingActivities((ActivityStackSupervisorProto) index, (int) value);
            return this;
        }

        public Builder setPendingActivities(int index, IdentifierProto.Builder builderForValue) {
            copyOnWrite();
            ((ActivityStackSupervisorProto) this.instance).setPendingActivities((ActivityStackSupervisorProto) index, (int) builderForValue);
            return this;
        }

        public Builder addPendingActivities(IdentifierProto value) {
            copyOnWrite();
            ((ActivityStackSupervisorProto) this.instance).addPendingActivities((ActivityStackSupervisorProto) value);
            return this;
        }

        public Builder addPendingActivities(int index, IdentifierProto value) {
            copyOnWrite();
            ((ActivityStackSupervisorProto) this.instance).addPendingActivities((ActivityStackSupervisorProto) index, (int) value);
            return this;
        }

        public Builder addPendingActivities(IdentifierProto.Builder builderForValue) {
            copyOnWrite();
            ((ActivityStackSupervisorProto) this.instance).addPendingActivities((ActivityStackSupervisorProto) builderForValue);
            return this;
        }

        public Builder addPendingActivities(int index, IdentifierProto.Builder builderForValue) {
            copyOnWrite();
            ((ActivityStackSupervisorProto) this.instance).addPendingActivities((ActivityStackSupervisorProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllPendingActivities(Iterable<? extends IdentifierProto> values) {
            copyOnWrite();
            ((ActivityStackSupervisorProto) this.instance).addAllPendingActivities(values);
            return this;
        }

        public Builder clearPendingActivities() {
            copyOnWrite();
            ((ActivityStackSupervisorProto) this.instance).clearPendingActivities();
            return this;
        }

        public Builder removePendingActivities(int index) {
            copyOnWrite();
            ((ActivityStackSupervisorProto) this.instance).removePendingActivities(index);
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new ActivityStackSupervisorProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                this.displays_.makeImmutable();
                this.pendingActivities_.makeImmutable();
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                ActivityStackSupervisorProto other = (ActivityStackSupervisorProto) arg1;
                this.configurationContainer_ = (ConfigurationContainerProto) visitor.visitMessage(this.configurationContainer_, other.configurationContainer_);
                this.displays_ = visitor.visitList(this.displays_, other.displays_);
                this.keyguardController_ = (KeyguardControllerProto) visitor.visitMessage(this.keyguardController_, other.keyguardController_);
                this.focusedStackId_ = visitor.visitInt(hasFocusedStackId(), this.focusedStackId_, other.hasFocusedStackId(), other.focusedStackId_);
                this.resumedActivity_ = (IdentifierProto) visitor.visitMessage(this.resumedActivity_, other.resumedActivity_);
                this.isHomeRecentsComponent_ = visitor.visitBoolean(hasIsHomeRecentsComponent(), this.isHomeRecentsComponent_, other.hasIsHomeRecentsComponent(), other.isHomeRecentsComponent_);
                this.pendingActivities_ = visitor.visitList(this.pendingActivities_, other.pendingActivities_);
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
                        } else if (tag == 18) {
                            if (!this.displays_.isModifiable()) {
                                this.displays_ = GeneratedMessageLite.mutableCopy(this.displays_);
                            }
                            this.displays_.add((ActivityDisplayProto) input.readMessage(ActivityDisplayProto.parser(), extensionRegistry));
                        } else if (tag == 26) {
                            KeyguardControllerProto.Builder subBuilder2 = null;
                            if ((this.bitField0_ & 2) == 2) {
                                subBuilder2 = (KeyguardControllerProto.Builder) this.keyguardController_.toBuilder();
                            }
                            this.keyguardController_ = (KeyguardControllerProto) input.readMessage(KeyguardControllerProto.parser(), extensionRegistry);
                            if (subBuilder2 != null) {
                                subBuilder2.mergeFrom((GeneratedMessageLite) this.keyguardController_);
                                this.keyguardController_ = (KeyguardControllerProto) subBuilder2.buildPartial();
                            }
                            this.bitField0_ |= 2;
                        } else if (tag == 32) {
                            this.bitField0_ |= 4;
                            this.focusedStackId_ = input.readInt32();
                        } else if (tag == 42) {
                            IdentifierProto.Builder subBuilder3 = null;
                            if ((this.bitField0_ & 8) == 8) {
                                subBuilder3 = (IdentifierProto.Builder) this.resumedActivity_.toBuilder();
                            }
                            this.resumedActivity_ = (IdentifierProto) input.readMessage(IdentifierProto.parser(), extensionRegistry);
                            if (subBuilder3 != null) {
                                subBuilder3.mergeFrom((GeneratedMessageLite) this.resumedActivity_);
                                this.resumedActivity_ = (IdentifierProto) subBuilder3.buildPartial();
                            }
                            this.bitField0_ |= 8;
                        } else if (tag == 48) {
                            this.bitField0_ |= 16;
                            this.isHomeRecentsComponent_ = input.readBool();
                        } else if (tag == 58) {
                            if (!this.pendingActivities_.isModifiable()) {
                                this.pendingActivities_ = GeneratedMessageLite.mutableCopy(this.pendingActivities_);
                            }
                            this.pendingActivities_.add((IdentifierProto) input.readMessage(IdentifierProto.parser(), extensionRegistry));
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
                    synchronized (ActivityStackSupervisorProto.class) {
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

    public static ActivityStackSupervisorProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<ActivityStackSupervisorProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
