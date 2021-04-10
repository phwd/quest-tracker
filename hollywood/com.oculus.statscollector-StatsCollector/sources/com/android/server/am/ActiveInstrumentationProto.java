package com.android.server.am;

import android.content.ComponentNameProto;
import android.content.pm.ApplicationInfoProto;
import android.os.BundleProto;
import com.android.server.am.ProcessRecordProto;
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

public final class ActiveInstrumentationProto extends GeneratedMessageLite<ActiveInstrumentationProto, Builder> implements ActiveInstrumentationProtoOrBuilder {
    public static final int ARGUMENTS_FIELD_NUMBER = 10;
    public static final int CLASS_FIELD_NUMBER = 1;
    private static final ActiveInstrumentationProto DEFAULT_INSTANCE = new ActiveInstrumentationProto();
    public static final int FINISHED_FIELD_NUMBER = 2;
    private static volatile Parser<ActiveInstrumentationProto> PARSER = null;
    public static final int PROFILE_FILE_FIELD_NUMBER = 6;
    public static final int RUNNING_PROCESSES_FIELD_NUMBER = 3;
    public static final int TARGET_INFO_FIELD_NUMBER = 5;
    public static final int TARGET_PROCESSES_FIELD_NUMBER = 4;
    public static final int UI_AUTOMATION_CONNECTION_FIELD_NUMBER = 8;
    public static final int WATCHER_FIELD_NUMBER = 7;
    private BundleProto arguments_;
    private int bitField0_;
    private ComponentNameProto class__;
    private boolean finished_ = false;
    private String profileFile_ = "";
    private Internal.ProtobufList<ProcessRecordProto> runningProcesses_ = emptyProtobufList();
    private ApplicationInfoProto targetInfo_;
    private Internal.ProtobufList<String> targetProcesses_ = GeneratedMessageLite.emptyProtobufList();
    private String uiAutomationConnection_ = "";
    private String watcher_ = "";

    private ActiveInstrumentationProto() {
    }

    @Override // com.android.server.am.ActiveInstrumentationProtoOrBuilder
    public boolean hasClass_() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // com.android.server.am.ActiveInstrumentationProtoOrBuilder
    public ComponentNameProto getClass_() {
        ComponentNameProto componentNameProto = this.class__;
        return componentNameProto == null ? ComponentNameProto.getDefaultInstance() : componentNameProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setClass_(ComponentNameProto value) {
        if (value != null) {
            this.class__ = value;
            this.bitField0_ |= 1;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setClass_(ComponentNameProto.Builder builderForValue) {
        this.class__ = (ComponentNameProto) builderForValue.build();
        this.bitField0_ |= 1;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeClass_(ComponentNameProto value) {
        ComponentNameProto componentNameProto = this.class__;
        if (componentNameProto == null || componentNameProto == ComponentNameProto.getDefaultInstance()) {
            this.class__ = value;
        } else {
            this.class__ = (ComponentNameProto) ((ComponentNameProto.Builder) ComponentNameProto.newBuilder(this.class__).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 1;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearClass_() {
        this.class__ = null;
        this.bitField0_ &= -2;
    }

    @Override // com.android.server.am.ActiveInstrumentationProtoOrBuilder
    public boolean hasFinished() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // com.android.server.am.ActiveInstrumentationProtoOrBuilder
    public boolean getFinished() {
        return this.finished_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setFinished(boolean value) {
        this.bitField0_ |= 2;
        this.finished_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearFinished() {
        this.bitField0_ &= -3;
        this.finished_ = false;
    }

    @Override // com.android.server.am.ActiveInstrumentationProtoOrBuilder
    public List<ProcessRecordProto> getRunningProcessesList() {
        return this.runningProcesses_;
    }

    public List<? extends ProcessRecordProtoOrBuilder> getRunningProcessesOrBuilderList() {
        return this.runningProcesses_;
    }

    @Override // com.android.server.am.ActiveInstrumentationProtoOrBuilder
    public int getRunningProcessesCount() {
        return this.runningProcesses_.size();
    }

    @Override // com.android.server.am.ActiveInstrumentationProtoOrBuilder
    public ProcessRecordProto getRunningProcesses(int index) {
        return this.runningProcesses_.get(index);
    }

    public ProcessRecordProtoOrBuilder getRunningProcessesOrBuilder(int index) {
        return this.runningProcesses_.get(index);
    }

    private void ensureRunningProcessesIsMutable() {
        if (!this.runningProcesses_.isModifiable()) {
            this.runningProcesses_ = GeneratedMessageLite.mutableCopy(this.runningProcesses_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setRunningProcesses(int index, ProcessRecordProto value) {
        if (value != null) {
            ensureRunningProcessesIsMutable();
            this.runningProcesses_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setRunningProcesses(int index, ProcessRecordProto.Builder builderForValue) {
        ensureRunningProcessesIsMutable();
        this.runningProcesses_.set(index, (ProcessRecordProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addRunningProcesses(ProcessRecordProto value) {
        if (value != null) {
            ensureRunningProcessesIsMutable();
            this.runningProcesses_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addRunningProcesses(int index, ProcessRecordProto value) {
        if (value != null) {
            ensureRunningProcessesIsMutable();
            this.runningProcesses_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addRunningProcesses(ProcessRecordProto.Builder builderForValue) {
        ensureRunningProcessesIsMutable();
        this.runningProcesses_.add((ProcessRecordProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addRunningProcesses(int index, ProcessRecordProto.Builder builderForValue) {
        ensureRunningProcessesIsMutable();
        this.runningProcesses_.add(index, (ProcessRecordProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllRunningProcesses(Iterable<? extends ProcessRecordProto> values) {
        ensureRunningProcessesIsMutable();
        AbstractMessageLite.addAll(values, this.runningProcesses_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearRunningProcesses() {
        this.runningProcesses_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeRunningProcesses(int index) {
        ensureRunningProcessesIsMutable();
        this.runningProcesses_.remove(index);
    }

    @Override // com.android.server.am.ActiveInstrumentationProtoOrBuilder
    public List<String> getTargetProcessesList() {
        return this.targetProcesses_;
    }

    @Override // com.android.server.am.ActiveInstrumentationProtoOrBuilder
    public int getTargetProcessesCount() {
        return this.targetProcesses_.size();
    }

    @Override // com.android.server.am.ActiveInstrumentationProtoOrBuilder
    public String getTargetProcesses(int index) {
        return this.targetProcesses_.get(index);
    }

    @Override // com.android.server.am.ActiveInstrumentationProtoOrBuilder
    public ByteString getTargetProcessesBytes(int index) {
        return ByteString.copyFromUtf8(this.targetProcesses_.get(index));
    }

    private void ensureTargetProcessesIsMutable() {
        if (!this.targetProcesses_.isModifiable()) {
            this.targetProcesses_ = GeneratedMessageLite.mutableCopy(this.targetProcesses_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setTargetProcesses(int index, String value) {
        if (value != null) {
            ensureTargetProcessesIsMutable();
            this.targetProcesses_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addTargetProcesses(String value) {
        if (value != null) {
            ensureTargetProcessesIsMutable();
            this.targetProcesses_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllTargetProcesses(Iterable<String> values) {
        ensureTargetProcessesIsMutable();
        AbstractMessageLite.addAll(values, this.targetProcesses_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearTargetProcesses() {
        this.targetProcesses_ = GeneratedMessageLite.emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addTargetProcessesBytes(ByteString value) {
        if (value != null) {
            ensureTargetProcessesIsMutable();
            this.targetProcesses_.add(value.toStringUtf8());
            return;
        }
        throw new NullPointerException();
    }

    @Override // com.android.server.am.ActiveInstrumentationProtoOrBuilder
    public boolean hasTargetInfo() {
        return (this.bitField0_ & 4) == 4;
    }

    @Override // com.android.server.am.ActiveInstrumentationProtoOrBuilder
    public ApplicationInfoProto getTargetInfo() {
        ApplicationInfoProto applicationInfoProto = this.targetInfo_;
        return applicationInfoProto == null ? ApplicationInfoProto.getDefaultInstance() : applicationInfoProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setTargetInfo(ApplicationInfoProto value) {
        if (value != null) {
            this.targetInfo_ = value;
            this.bitField0_ |= 4;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setTargetInfo(ApplicationInfoProto.Builder builderForValue) {
        this.targetInfo_ = (ApplicationInfoProto) builderForValue.build();
        this.bitField0_ |= 4;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeTargetInfo(ApplicationInfoProto value) {
        ApplicationInfoProto applicationInfoProto = this.targetInfo_;
        if (applicationInfoProto == null || applicationInfoProto == ApplicationInfoProto.getDefaultInstance()) {
            this.targetInfo_ = value;
        } else {
            this.targetInfo_ = (ApplicationInfoProto) ((ApplicationInfoProto.Builder) ApplicationInfoProto.newBuilder(this.targetInfo_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 4;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearTargetInfo() {
        this.targetInfo_ = null;
        this.bitField0_ &= -5;
    }

    @Override // com.android.server.am.ActiveInstrumentationProtoOrBuilder
    public boolean hasProfileFile() {
        return (this.bitField0_ & 8) == 8;
    }

    @Override // com.android.server.am.ActiveInstrumentationProtoOrBuilder
    public String getProfileFile() {
        return this.profileFile_;
    }

    @Override // com.android.server.am.ActiveInstrumentationProtoOrBuilder
    public ByteString getProfileFileBytes() {
        return ByteString.copyFromUtf8(this.profileFile_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setProfileFile(String value) {
        if (value != null) {
            this.bitField0_ |= 8;
            this.profileFile_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearProfileFile() {
        this.bitField0_ &= -9;
        this.profileFile_ = getDefaultInstance().getProfileFile();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setProfileFileBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 8;
            this.profileFile_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // com.android.server.am.ActiveInstrumentationProtoOrBuilder
    public boolean hasWatcher() {
        return (this.bitField0_ & 16) == 16;
    }

    @Override // com.android.server.am.ActiveInstrumentationProtoOrBuilder
    public String getWatcher() {
        return this.watcher_;
    }

    @Override // com.android.server.am.ActiveInstrumentationProtoOrBuilder
    public ByteString getWatcherBytes() {
        return ByteString.copyFromUtf8(this.watcher_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setWatcher(String value) {
        if (value != null) {
            this.bitField0_ |= 16;
            this.watcher_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearWatcher() {
        this.bitField0_ &= -17;
        this.watcher_ = getDefaultInstance().getWatcher();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setWatcherBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 16;
            this.watcher_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // com.android.server.am.ActiveInstrumentationProtoOrBuilder
    public boolean hasUiAutomationConnection() {
        return (this.bitField0_ & 32) == 32;
    }

    @Override // com.android.server.am.ActiveInstrumentationProtoOrBuilder
    public String getUiAutomationConnection() {
        return this.uiAutomationConnection_;
    }

    @Override // com.android.server.am.ActiveInstrumentationProtoOrBuilder
    public ByteString getUiAutomationConnectionBytes() {
        return ByteString.copyFromUtf8(this.uiAutomationConnection_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setUiAutomationConnection(String value) {
        if (value != null) {
            this.bitField0_ |= 32;
            this.uiAutomationConnection_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearUiAutomationConnection() {
        this.bitField0_ &= -33;
        this.uiAutomationConnection_ = getDefaultInstance().getUiAutomationConnection();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setUiAutomationConnectionBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 32;
            this.uiAutomationConnection_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // com.android.server.am.ActiveInstrumentationProtoOrBuilder
    public boolean hasArguments() {
        return (this.bitField0_ & 64) == 64;
    }

    @Override // com.android.server.am.ActiveInstrumentationProtoOrBuilder
    public BundleProto getArguments() {
        BundleProto bundleProto = this.arguments_;
        return bundleProto == null ? BundleProto.getDefaultInstance() : bundleProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setArguments(BundleProto value) {
        if (value != null) {
            this.arguments_ = value;
            this.bitField0_ |= 64;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setArguments(BundleProto.Builder builderForValue) {
        this.arguments_ = (BundleProto) builderForValue.build();
        this.bitField0_ |= 64;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeArguments(BundleProto value) {
        BundleProto bundleProto = this.arguments_;
        if (bundleProto == null || bundleProto == BundleProto.getDefaultInstance()) {
            this.arguments_ = value;
        } else {
            this.arguments_ = (BundleProto) ((BundleProto.Builder) BundleProto.newBuilder(this.arguments_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 64;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearArguments() {
        this.arguments_ = null;
        this.bitField0_ &= -65;
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeMessage(1, getClass_());
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeBool(2, this.finished_);
        }
        for (int i = 0; i < this.runningProcesses_.size(); i++) {
            output.writeMessage(3, this.runningProcesses_.get(i));
        }
        for (int i2 = 0; i2 < this.targetProcesses_.size(); i2++) {
            output.writeString(4, this.targetProcesses_.get(i2));
        }
        if ((this.bitField0_ & 4) == 4) {
            output.writeMessage(5, getTargetInfo());
        }
        if ((this.bitField0_ & 8) == 8) {
            output.writeString(6, getProfileFile());
        }
        if ((this.bitField0_ & 16) == 16) {
            output.writeString(7, getWatcher());
        }
        if ((this.bitField0_ & 32) == 32) {
            output.writeString(8, getUiAutomationConnection());
        }
        if ((this.bitField0_ & 64) == 64) {
            output.writeMessage(10, getArguments());
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
            size2 = 0 + CodedOutputStream.computeMessageSize(1, getClass_());
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeBoolSize(2, this.finished_);
        }
        for (int i = 0; i < this.runningProcesses_.size(); i++) {
            size2 += CodedOutputStream.computeMessageSize(3, this.runningProcesses_.get(i));
        }
        int dataSize = 0;
        for (int i2 = 0; i2 < this.targetProcesses_.size(); i2++) {
            dataSize += CodedOutputStream.computeStringSizeNoTag(this.targetProcesses_.get(i2));
        }
        int size3 = size2 + dataSize + (getTargetProcessesList().size() * 1);
        if ((this.bitField0_ & 4) == 4) {
            size3 += CodedOutputStream.computeMessageSize(5, getTargetInfo());
        }
        if ((this.bitField0_ & 8) == 8) {
            size3 += CodedOutputStream.computeStringSize(6, getProfileFile());
        }
        if ((this.bitField0_ & 16) == 16) {
            size3 += CodedOutputStream.computeStringSize(7, getWatcher());
        }
        if ((this.bitField0_ & 32) == 32) {
            size3 += CodedOutputStream.computeStringSize(8, getUiAutomationConnection());
        }
        if ((this.bitField0_ & 64) == 64) {
            size3 += CodedOutputStream.computeMessageSize(10, getArguments());
        }
        int size4 = size3 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size4;
        return size4;
    }

    public static ActiveInstrumentationProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (ActiveInstrumentationProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static ActiveInstrumentationProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (ActiveInstrumentationProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static ActiveInstrumentationProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (ActiveInstrumentationProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static ActiveInstrumentationProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (ActiveInstrumentationProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static ActiveInstrumentationProto parseFrom(InputStream input) throws IOException {
        return (ActiveInstrumentationProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static ActiveInstrumentationProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (ActiveInstrumentationProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static ActiveInstrumentationProto parseDelimitedFrom(InputStream input) throws IOException {
        return (ActiveInstrumentationProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static ActiveInstrumentationProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (ActiveInstrumentationProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static ActiveInstrumentationProto parseFrom(CodedInputStream input) throws IOException {
        return (ActiveInstrumentationProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static ActiveInstrumentationProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (ActiveInstrumentationProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(ActiveInstrumentationProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<ActiveInstrumentationProto, Builder> implements ActiveInstrumentationProtoOrBuilder {
        private Builder() {
            super(ActiveInstrumentationProto.DEFAULT_INSTANCE);
        }

        @Override // com.android.server.am.ActiveInstrumentationProtoOrBuilder
        public boolean hasClass_() {
            return ((ActiveInstrumentationProto) this.instance).hasClass_();
        }

        @Override // com.android.server.am.ActiveInstrumentationProtoOrBuilder
        public ComponentNameProto getClass_() {
            return ((ActiveInstrumentationProto) this.instance).getClass_();
        }

        public Builder setClass_(ComponentNameProto value) {
            copyOnWrite();
            ((ActiveInstrumentationProto) this.instance).setClass_((ActiveInstrumentationProto) value);
            return this;
        }

        public Builder setClass_(ComponentNameProto.Builder builderForValue) {
            copyOnWrite();
            ((ActiveInstrumentationProto) this.instance).setClass_((ActiveInstrumentationProto) builderForValue);
            return this;
        }

        public Builder mergeClass_(ComponentNameProto value) {
            copyOnWrite();
            ((ActiveInstrumentationProto) this.instance).mergeClass_(value);
            return this;
        }

        public Builder clearClass_() {
            copyOnWrite();
            ((ActiveInstrumentationProto) this.instance).clearClass_();
            return this;
        }

        @Override // com.android.server.am.ActiveInstrumentationProtoOrBuilder
        public boolean hasFinished() {
            return ((ActiveInstrumentationProto) this.instance).hasFinished();
        }

        @Override // com.android.server.am.ActiveInstrumentationProtoOrBuilder
        public boolean getFinished() {
            return ((ActiveInstrumentationProto) this.instance).getFinished();
        }

        public Builder setFinished(boolean value) {
            copyOnWrite();
            ((ActiveInstrumentationProto) this.instance).setFinished(value);
            return this;
        }

        public Builder clearFinished() {
            copyOnWrite();
            ((ActiveInstrumentationProto) this.instance).clearFinished();
            return this;
        }

        @Override // com.android.server.am.ActiveInstrumentationProtoOrBuilder
        public List<ProcessRecordProto> getRunningProcessesList() {
            return Collections.unmodifiableList(((ActiveInstrumentationProto) this.instance).getRunningProcessesList());
        }

        @Override // com.android.server.am.ActiveInstrumentationProtoOrBuilder
        public int getRunningProcessesCount() {
            return ((ActiveInstrumentationProto) this.instance).getRunningProcessesCount();
        }

        @Override // com.android.server.am.ActiveInstrumentationProtoOrBuilder
        public ProcessRecordProto getRunningProcesses(int index) {
            return ((ActiveInstrumentationProto) this.instance).getRunningProcesses(index);
        }

        public Builder setRunningProcesses(int index, ProcessRecordProto value) {
            copyOnWrite();
            ((ActiveInstrumentationProto) this.instance).setRunningProcesses((ActiveInstrumentationProto) index, (int) value);
            return this;
        }

        public Builder setRunningProcesses(int index, ProcessRecordProto.Builder builderForValue) {
            copyOnWrite();
            ((ActiveInstrumentationProto) this.instance).setRunningProcesses((ActiveInstrumentationProto) index, (int) builderForValue);
            return this;
        }

        public Builder addRunningProcesses(ProcessRecordProto value) {
            copyOnWrite();
            ((ActiveInstrumentationProto) this.instance).addRunningProcesses((ActiveInstrumentationProto) value);
            return this;
        }

        public Builder addRunningProcesses(int index, ProcessRecordProto value) {
            copyOnWrite();
            ((ActiveInstrumentationProto) this.instance).addRunningProcesses((ActiveInstrumentationProto) index, (int) value);
            return this;
        }

        public Builder addRunningProcesses(ProcessRecordProto.Builder builderForValue) {
            copyOnWrite();
            ((ActiveInstrumentationProto) this.instance).addRunningProcesses((ActiveInstrumentationProto) builderForValue);
            return this;
        }

        public Builder addRunningProcesses(int index, ProcessRecordProto.Builder builderForValue) {
            copyOnWrite();
            ((ActiveInstrumentationProto) this.instance).addRunningProcesses((ActiveInstrumentationProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllRunningProcesses(Iterable<? extends ProcessRecordProto> values) {
            copyOnWrite();
            ((ActiveInstrumentationProto) this.instance).addAllRunningProcesses(values);
            return this;
        }

        public Builder clearRunningProcesses() {
            copyOnWrite();
            ((ActiveInstrumentationProto) this.instance).clearRunningProcesses();
            return this;
        }

        public Builder removeRunningProcesses(int index) {
            copyOnWrite();
            ((ActiveInstrumentationProto) this.instance).removeRunningProcesses(index);
            return this;
        }

        @Override // com.android.server.am.ActiveInstrumentationProtoOrBuilder
        public List<String> getTargetProcessesList() {
            return Collections.unmodifiableList(((ActiveInstrumentationProto) this.instance).getTargetProcessesList());
        }

        @Override // com.android.server.am.ActiveInstrumentationProtoOrBuilder
        public int getTargetProcessesCount() {
            return ((ActiveInstrumentationProto) this.instance).getTargetProcessesCount();
        }

        @Override // com.android.server.am.ActiveInstrumentationProtoOrBuilder
        public String getTargetProcesses(int index) {
            return ((ActiveInstrumentationProto) this.instance).getTargetProcesses(index);
        }

        @Override // com.android.server.am.ActiveInstrumentationProtoOrBuilder
        public ByteString getTargetProcessesBytes(int index) {
            return ((ActiveInstrumentationProto) this.instance).getTargetProcessesBytes(index);
        }

        public Builder setTargetProcesses(int index, String value) {
            copyOnWrite();
            ((ActiveInstrumentationProto) this.instance).setTargetProcesses(index, value);
            return this;
        }

        public Builder addTargetProcesses(String value) {
            copyOnWrite();
            ((ActiveInstrumentationProto) this.instance).addTargetProcesses(value);
            return this;
        }

        public Builder addAllTargetProcesses(Iterable<String> values) {
            copyOnWrite();
            ((ActiveInstrumentationProto) this.instance).addAllTargetProcesses(values);
            return this;
        }

        public Builder clearTargetProcesses() {
            copyOnWrite();
            ((ActiveInstrumentationProto) this.instance).clearTargetProcesses();
            return this;
        }

        public Builder addTargetProcessesBytes(ByteString value) {
            copyOnWrite();
            ((ActiveInstrumentationProto) this.instance).addTargetProcessesBytes(value);
            return this;
        }

        @Override // com.android.server.am.ActiveInstrumentationProtoOrBuilder
        public boolean hasTargetInfo() {
            return ((ActiveInstrumentationProto) this.instance).hasTargetInfo();
        }

        @Override // com.android.server.am.ActiveInstrumentationProtoOrBuilder
        public ApplicationInfoProto getTargetInfo() {
            return ((ActiveInstrumentationProto) this.instance).getTargetInfo();
        }

        public Builder setTargetInfo(ApplicationInfoProto value) {
            copyOnWrite();
            ((ActiveInstrumentationProto) this.instance).setTargetInfo((ActiveInstrumentationProto) value);
            return this;
        }

        public Builder setTargetInfo(ApplicationInfoProto.Builder builderForValue) {
            copyOnWrite();
            ((ActiveInstrumentationProto) this.instance).setTargetInfo((ActiveInstrumentationProto) builderForValue);
            return this;
        }

        public Builder mergeTargetInfo(ApplicationInfoProto value) {
            copyOnWrite();
            ((ActiveInstrumentationProto) this.instance).mergeTargetInfo(value);
            return this;
        }

        public Builder clearTargetInfo() {
            copyOnWrite();
            ((ActiveInstrumentationProto) this.instance).clearTargetInfo();
            return this;
        }

        @Override // com.android.server.am.ActiveInstrumentationProtoOrBuilder
        public boolean hasProfileFile() {
            return ((ActiveInstrumentationProto) this.instance).hasProfileFile();
        }

        @Override // com.android.server.am.ActiveInstrumentationProtoOrBuilder
        public String getProfileFile() {
            return ((ActiveInstrumentationProto) this.instance).getProfileFile();
        }

        @Override // com.android.server.am.ActiveInstrumentationProtoOrBuilder
        public ByteString getProfileFileBytes() {
            return ((ActiveInstrumentationProto) this.instance).getProfileFileBytes();
        }

        public Builder setProfileFile(String value) {
            copyOnWrite();
            ((ActiveInstrumentationProto) this.instance).setProfileFile(value);
            return this;
        }

        public Builder clearProfileFile() {
            copyOnWrite();
            ((ActiveInstrumentationProto) this.instance).clearProfileFile();
            return this;
        }

        public Builder setProfileFileBytes(ByteString value) {
            copyOnWrite();
            ((ActiveInstrumentationProto) this.instance).setProfileFileBytes(value);
            return this;
        }

        @Override // com.android.server.am.ActiveInstrumentationProtoOrBuilder
        public boolean hasWatcher() {
            return ((ActiveInstrumentationProto) this.instance).hasWatcher();
        }

        @Override // com.android.server.am.ActiveInstrumentationProtoOrBuilder
        public String getWatcher() {
            return ((ActiveInstrumentationProto) this.instance).getWatcher();
        }

        @Override // com.android.server.am.ActiveInstrumentationProtoOrBuilder
        public ByteString getWatcherBytes() {
            return ((ActiveInstrumentationProto) this.instance).getWatcherBytes();
        }

        public Builder setWatcher(String value) {
            copyOnWrite();
            ((ActiveInstrumentationProto) this.instance).setWatcher(value);
            return this;
        }

        public Builder clearWatcher() {
            copyOnWrite();
            ((ActiveInstrumentationProto) this.instance).clearWatcher();
            return this;
        }

        public Builder setWatcherBytes(ByteString value) {
            copyOnWrite();
            ((ActiveInstrumentationProto) this.instance).setWatcherBytes(value);
            return this;
        }

        @Override // com.android.server.am.ActiveInstrumentationProtoOrBuilder
        public boolean hasUiAutomationConnection() {
            return ((ActiveInstrumentationProto) this.instance).hasUiAutomationConnection();
        }

        @Override // com.android.server.am.ActiveInstrumentationProtoOrBuilder
        public String getUiAutomationConnection() {
            return ((ActiveInstrumentationProto) this.instance).getUiAutomationConnection();
        }

        @Override // com.android.server.am.ActiveInstrumentationProtoOrBuilder
        public ByteString getUiAutomationConnectionBytes() {
            return ((ActiveInstrumentationProto) this.instance).getUiAutomationConnectionBytes();
        }

        public Builder setUiAutomationConnection(String value) {
            copyOnWrite();
            ((ActiveInstrumentationProto) this.instance).setUiAutomationConnection(value);
            return this;
        }

        public Builder clearUiAutomationConnection() {
            copyOnWrite();
            ((ActiveInstrumentationProto) this.instance).clearUiAutomationConnection();
            return this;
        }

        public Builder setUiAutomationConnectionBytes(ByteString value) {
            copyOnWrite();
            ((ActiveInstrumentationProto) this.instance).setUiAutomationConnectionBytes(value);
            return this;
        }

        @Override // com.android.server.am.ActiveInstrumentationProtoOrBuilder
        public boolean hasArguments() {
            return ((ActiveInstrumentationProto) this.instance).hasArguments();
        }

        @Override // com.android.server.am.ActiveInstrumentationProtoOrBuilder
        public BundleProto getArguments() {
            return ((ActiveInstrumentationProto) this.instance).getArguments();
        }

        public Builder setArguments(BundleProto value) {
            copyOnWrite();
            ((ActiveInstrumentationProto) this.instance).setArguments((ActiveInstrumentationProto) value);
            return this;
        }

        public Builder setArguments(BundleProto.Builder builderForValue) {
            copyOnWrite();
            ((ActiveInstrumentationProto) this.instance).setArguments((ActiveInstrumentationProto) builderForValue);
            return this;
        }

        public Builder mergeArguments(BundleProto value) {
            copyOnWrite();
            ((ActiveInstrumentationProto) this.instance).mergeArguments(value);
            return this;
        }

        public Builder clearArguments() {
            copyOnWrite();
            ((ActiveInstrumentationProto) this.instance).clearArguments();
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new ActiveInstrumentationProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                this.runningProcesses_.makeImmutable();
                this.targetProcesses_.makeImmutable();
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                ActiveInstrumentationProto other = (ActiveInstrumentationProto) arg1;
                this.class__ = (ComponentNameProto) visitor.visitMessage(this.class__, other.class__);
                this.finished_ = visitor.visitBoolean(hasFinished(), this.finished_, other.hasFinished(), other.finished_);
                this.runningProcesses_ = visitor.visitList(this.runningProcesses_, other.runningProcesses_);
                this.targetProcesses_ = visitor.visitList(this.targetProcesses_, other.targetProcesses_);
                this.targetInfo_ = (ApplicationInfoProto) visitor.visitMessage(this.targetInfo_, other.targetInfo_);
                this.profileFile_ = visitor.visitString(hasProfileFile(), this.profileFile_, other.hasProfileFile(), other.profileFile_);
                this.watcher_ = visitor.visitString(hasWatcher(), this.watcher_, other.hasWatcher(), other.watcher_);
                this.uiAutomationConnection_ = visitor.visitString(hasUiAutomationConnection(), this.uiAutomationConnection_, other.hasUiAutomationConnection(), other.uiAutomationConnection_);
                this.arguments_ = (BundleProto) visitor.visitMessage(this.arguments_, other.arguments_);
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
                            ComponentNameProto.Builder subBuilder = null;
                            if ((this.bitField0_ & 1) == 1) {
                                subBuilder = (ComponentNameProto.Builder) this.class__.toBuilder();
                            }
                            this.class__ = (ComponentNameProto) input.readMessage(ComponentNameProto.parser(), extensionRegistry);
                            if (subBuilder != null) {
                                subBuilder.mergeFrom((GeneratedMessageLite) this.class__);
                                this.class__ = (ComponentNameProto) subBuilder.buildPartial();
                            }
                            this.bitField0_ |= 1;
                        } else if (tag == 16) {
                            this.bitField0_ |= 2;
                            this.finished_ = input.readBool();
                        } else if (tag == 26) {
                            if (!this.runningProcesses_.isModifiable()) {
                                this.runningProcesses_ = GeneratedMessageLite.mutableCopy(this.runningProcesses_);
                            }
                            this.runningProcesses_.add((ProcessRecordProto) input.readMessage(ProcessRecordProto.parser(), extensionRegistry));
                        } else if (tag == 34) {
                            String s = input.readString();
                            if (!this.targetProcesses_.isModifiable()) {
                                this.targetProcesses_ = GeneratedMessageLite.mutableCopy(this.targetProcesses_);
                            }
                            this.targetProcesses_.add(s);
                        } else if (tag == 42) {
                            ApplicationInfoProto.Builder subBuilder2 = null;
                            if ((this.bitField0_ & 4) == 4) {
                                subBuilder2 = (ApplicationInfoProto.Builder) this.targetInfo_.toBuilder();
                            }
                            this.targetInfo_ = (ApplicationInfoProto) input.readMessage(ApplicationInfoProto.parser(), extensionRegistry);
                            if (subBuilder2 != null) {
                                subBuilder2.mergeFrom((GeneratedMessageLite) this.targetInfo_);
                                this.targetInfo_ = (ApplicationInfoProto) subBuilder2.buildPartial();
                            }
                            this.bitField0_ |= 4;
                        } else if (tag == 50) {
                            String s2 = input.readString();
                            this.bitField0_ |= 8;
                            this.profileFile_ = s2;
                        } else if (tag == 58) {
                            String s3 = input.readString();
                            this.bitField0_ = 16 | this.bitField0_;
                            this.watcher_ = s3;
                        } else if (tag == 66) {
                            String s4 = input.readString();
                            this.bitField0_ |= 32;
                            this.uiAutomationConnection_ = s4;
                        } else if (tag == 82) {
                            BundleProto.Builder subBuilder3 = null;
                            if ((this.bitField0_ & 64) == 64) {
                                subBuilder3 = (BundleProto.Builder) this.arguments_.toBuilder();
                            }
                            this.arguments_ = (BundleProto) input.readMessage(BundleProto.parser(), extensionRegistry);
                            if (subBuilder3 != null) {
                                subBuilder3.mergeFrom((GeneratedMessageLite) this.arguments_);
                                this.arguments_ = (BundleProto) subBuilder3.buildPartial();
                            }
                            this.bitField0_ |= 64;
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
                    synchronized (ActiveInstrumentationProto.class) {
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

    public static ActiveInstrumentationProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<ActiveInstrumentationProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
