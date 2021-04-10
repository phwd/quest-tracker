package com.android.server.am;

import com.android.server.am.ActivityStackProto;
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

public final class ActivityDisplayProto extends GeneratedMessageLite<ActivityDisplayProto, Builder> implements ActivityDisplayProtoOrBuilder {
    public static final int CONFIGURATION_CONTAINER_FIELD_NUMBER = 1;
    private static final ActivityDisplayProto DEFAULT_INSTANCE = new ActivityDisplayProto();
    public static final int FOCUSED_STACK_ID_FIELD_NUMBER = 4;
    public static final int ID_FIELD_NUMBER = 2;
    private static volatile Parser<ActivityDisplayProto> PARSER = null;
    public static final int RESUMED_ACTIVITY_FIELD_NUMBER = 5;
    public static final int SINGLE_TASK_INSTANCE_FIELD_NUMBER = 6;
    public static final int STACKS_FIELD_NUMBER = 3;
    private int bitField0_;
    private ConfigurationContainerProto configurationContainer_;
    private int focusedStackId_ = 0;
    private int id_ = 0;
    private IdentifierProto resumedActivity_;
    private boolean singleTaskInstance_ = false;
    private Internal.ProtobufList<ActivityStackProto> stacks_ = emptyProtobufList();

    private ActivityDisplayProto() {
    }

    @Override // com.android.server.am.ActivityDisplayProtoOrBuilder
    public boolean hasConfigurationContainer() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // com.android.server.am.ActivityDisplayProtoOrBuilder
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

    @Override // com.android.server.am.ActivityDisplayProtoOrBuilder
    public boolean hasId() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // com.android.server.am.ActivityDisplayProtoOrBuilder
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

    @Override // com.android.server.am.ActivityDisplayProtoOrBuilder
    public List<ActivityStackProto> getStacksList() {
        return this.stacks_;
    }

    public List<? extends ActivityStackProtoOrBuilder> getStacksOrBuilderList() {
        return this.stacks_;
    }

    @Override // com.android.server.am.ActivityDisplayProtoOrBuilder
    public int getStacksCount() {
        return this.stacks_.size();
    }

    @Override // com.android.server.am.ActivityDisplayProtoOrBuilder
    public ActivityStackProto getStacks(int index) {
        return this.stacks_.get(index);
    }

    public ActivityStackProtoOrBuilder getStacksOrBuilder(int index) {
        return this.stacks_.get(index);
    }

    private void ensureStacksIsMutable() {
        if (!this.stacks_.isModifiable()) {
            this.stacks_ = GeneratedMessageLite.mutableCopy(this.stacks_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setStacks(int index, ActivityStackProto value) {
        if (value != null) {
            ensureStacksIsMutable();
            this.stacks_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setStacks(int index, ActivityStackProto.Builder builderForValue) {
        ensureStacksIsMutable();
        this.stacks_.set(index, (ActivityStackProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addStacks(ActivityStackProto value) {
        if (value != null) {
            ensureStacksIsMutable();
            this.stacks_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addStacks(int index, ActivityStackProto value) {
        if (value != null) {
            ensureStacksIsMutable();
            this.stacks_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addStacks(ActivityStackProto.Builder builderForValue) {
        ensureStacksIsMutable();
        this.stacks_.add((ActivityStackProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addStacks(int index, ActivityStackProto.Builder builderForValue) {
        ensureStacksIsMutable();
        this.stacks_.add(index, (ActivityStackProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllStacks(Iterable<? extends ActivityStackProto> values) {
        ensureStacksIsMutable();
        AbstractMessageLite.addAll(values, this.stacks_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearStacks() {
        this.stacks_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeStacks(int index) {
        ensureStacksIsMutable();
        this.stacks_.remove(index);
    }

    @Override // com.android.server.am.ActivityDisplayProtoOrBuilder
    public boolean hasFocusedStackId() {
        return (this.bitField0_ & 4) == 4;
    }

    @Override // com.android.server.am.ActivityDisplayProtoOrBuilder
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

    @Override // com.android.server.am.ActivityDisplayProtoOrBuilder
    public boolean hasResumedActivity() {
        return (this.bitField0_ & 8) == 8;
    }

    @Override // com.android.server.am.ActivityDisplayProtoOrBuilder
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

    @Override // com.android.server.am.ActivityDisplayProtoOrBuilder
    public boolean hasSingleTaskInstance() {
        return (this.bitField0_ & 16) == 16;
    }

    @Override // com.android.server.am.ActivityDisplayProtoOrBuilder
    public boolean getSingleTaskInstance() {
        return this.singleTaskInstance_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setSingleTaskInstance(boolean value) {
        this.bitField0_ |= 16;
        this.singleTaskInstance_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearSingleTaskInstance() {
        this.bitField0_ &= -17;
        this.singleTaskInstance_ = false;
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeMessage(1, getConfigurationContainer());
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeInt32(2, this.id_);
        }
        for (int i = 0; i < this.stacks_.size(); i++) {
            output.writeMessage(3, this.stacks_.get(i));
        }
        if ((this.bitField0_ & 4) == 4) {
            output.writeInt32(4, this.focusedStackId_);
        }
        if ((this.bitField0_ & 8) == 8) {
            output.writeMessage(5, getResumedActivity());
        }
        if ((this.bitField0_ & 16) == 16) {
            output.writeBool(6, this.singleTaskInstance_);
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
        for (int i = 0; i < this.stacks_.size(); i++) {
            size2 += CodedOutputStream.computeMessageSize(3, this.stacks_.get(i));
        }
        if ((this.bitField0_ & 4) == 4) {
            size2 += CodedOutputStream.computeInt32Size(4, this.focusedStackId_);
        }
        if ((this.bitField0_ & 8) == 8) {
            size2 += CodedOutputStream.computeMessageSize(5, getResumedActivity());
        }
        if ((this.bitField0_ & 16) == 16) {
            size2 += CodedOutputStream.computeBoolSize(6, this.singleTaskInstance_);
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static ActivityDisplayProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (ActivityDisplayProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static ActivityDisplayProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (ActivityDisplayProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static ActivityDisplayProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (ActivityDisplayProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static ActivityDisplayProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (ActivityDisplayProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static ActivityDisplayProto parseFrom(InputStream input) throws IOException {
        return (ActivityDisplayProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static ActivityDisplayProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (ActivityDisplayProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static ActivityDisplayProto parseDelimitedFrom(InputStream input) throws IOException {
        return (ActivityDisplayProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static ActivityDisplayProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (ActivityDisplayProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static ActivityDisplayProto parseFrom(CodedInputStream input) throws IOException {
        return (ActivityDisplayProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static ActivityDisplayProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (ActivityDisplayProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(ActivityDisplayProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<ActivityDisplayProto, Builder> implements ActivityDisplayProtoOrBuilder {
        private Builder() {
            super(ActivityDisplayProto.DEFAULT_INSTANCE);
        }

        @Override // com.android.server.am.ActivityDisplayProtoOrBuilder
        public boolean hasConfigurationContainer() {
            return ((ActivityDisplayProto) this.instance).hasConfigurationContainer();
        }

        @Override // com.android.server.am.ActivityDisplayProtoOrBuilder
        public ConfigurationContainerProto getConfigurationContainer() {
            return ((ActivityDisplayProto) this.instance).getConfigurationContainer();
        }

        public Builder setConfigurationContainer(ConfigurationContainerProto value) {
            copyOnWrite();
            ((ActivityDisplayProto) this.instance).setConfigurationContainer((ActivityDisplayProto) value);
            return this;
        }

        public Builder setConfigurationContainer(ConfigurationContainerProto.Builder builderForValue) {
            copyOnWrite();
            ((ActivityDisplayProto) this.instance).setConfigurationContainer((ActivityDisplayProto) builderForValue);
            return this;
        }

        public Builder mergeConfigurationContainer(ConfigurationContainerProto value) {
            copyOnWrite();
            ((ActivityDisplayProto) this.instance).mergeConfigurationContainer(value);
            return this;
        }

        public Builder clearConfigurationContainer() {
            copyOnWrite();
            ((ActivityDisplayProto) this.instance).clearConfigurationContainer();
            return this;
        }

        @Override // com.android.server.am.ActivityDisplayProtoOrBuilder
        public boolean hasId() {
            return ((ActivityDisplayProto) this.instance).hasId();
        }

        @Override // com.android.server.am.ActivityDisplayProtoOrBuilder
        public int getId() {
            return ((ActivityDisplayProto) this.instance).getId();
        }

        public Builder setId(int value) {
            copyOnWrite();
            ((ActivityDisplayProto) this.instance).setId(value);
            return this;
        }

        public Builder clearId() {
            copyOnWrite();
            ((ActivityDisplayProto) this.instance).clearId();
            return this;
        }

        @Override // com.android.server.am.ActivityDisplayProtoOrBuilder
        public List<ActivityStackProto> getStacksList() {
            return Collections.unmodifiableList(((ActivityDisplayProto) this.instance).getStacksList());
        }

        @Override // com.android.server.am.ActivityDisplayProtoOrBuilder
        public int getStacksCount() {
            return ((ActivityDisplayProto) this.instance).getStacksCount();
        }

        @Override // com.android.server.am.ActivityDisplayProtoOrBuilder
        public ActivityStackProto getStacks(int index) {
            return ((ActivityDisplayProto) this.instance).getStacks(index);
        }

        public Builder setStacks(int index, ActivityStackProto value) {
            copyOnWrite();
            ((ActivityDisplayProto) this.instance).setStacks((ActivityDisplayProto) index, (int) value);
            return this;
        }

        public Builder setStacks(int index, ActivityStackProto.Builder builderForValue) {
            copyOnWrite();
            ((ActivityDisplayProto) this.instance).setStacks((ActivityDisplayProto) index, (int) builderForValue);
            return this;
        }

        public Builder addStacks(ActivityStackProto value) {
            copyOnWrite();
            ((ActivityDisplayProto) this.instance).addStacks((ActivityDisplayProto) value);
            return this;
        }

        public Builder addStacks(int index, ActivityStackProto value) {
            copyOnWrite();
            ((ActivityDisplayProto) this.instance).addStacks((ActivityDisplayProto) index, (int) value);
            return this;
        }

        public Builder addStacks(ActivityStackProto.Builder builderForValue) {
            copyOnWrite();
            ((ActivityDisplayProto) this.instance).addStacks((ActivityDisplayProto) builderForValue);
            return this;
        }

        public Builder addStacks(int index, ActivityStackProto.Builder builderForValue) {
            copyOnWrite();
            ((ActivityDisplayProto) this.instance).addStacks((ActivityDisplayProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllStacks(Iterable<? extends ActivityStackProto> values) {
            copyOnWrite();
            ((ActivityDisplayProto) this.instance).addAllStacks(values);
            return this;
        }

        public Builder clearStacks() {
            copyOnWrite();
            ((ActivityDisplayProto) this.instance).clearStacks();
            return this;
        }

        public Builder removeStacks(int index) {
            copyOnWrite();
            ((ActivityDisplayProto) this.instance).removeStacks(index);
            return this;
        }

        @Override // com.android.server.am.ActivityDisplayProtoOrBuilder
        public boolean hasFocusedStackId() {
            return ((ActivityDisplayProto) this.instance).hasFocusedStackId();
        }

        @Override // com.android.server.am.ActivityDisplayProtoOrBuilder
        public int getFocusedStackId() {
            return ((ActivityDisplayProto) this.instance).getFocusedStackId();
        }

        public Builder setFocusedStackId(int value) {
            copyOnWrite();
            ((ActivityDisplayProto) this.instance).setFocusedStackId(value);
            return this;
        }

        public Builder clearFocusedStackId() {
            copyOnWrite();
            ((ActivityDisplayProto) this.instance).clearFocusedStackId();
            return this;
        }

        @Override // com.android.server.am.ActivityDisplayProtoOrBuilder
        public boolean hasResumedActivity() {
            return ((ActivityDisplayProto) this.instance).hasResumedActivity();
        }

        @Override // com.android.server.am.ActivityDisplayProtoOrBuilder
        public IdentifierProto getResumedActivity() {
            return ((ActivityDisplayProto) this.instance).getResumedActivity();
        }

        public Builder setResumedActivity(IdentifierProto value) {
            copyOnWrite();
            ((ActivityDisplayProto) this.instance).setResumedActivity((ActivityDisplayProto) value);
            return this;
        }

        public Builder setResumedActivity(IdentifierProto.Builder builderForValue) {
            copyOnWrite();
            ((ActivityDisplayProto) this.instance).setResumedActivity((ActivityDisplayProto) builderForValue);
            return this;
        }

        public Builder mergeResumedActivity(IdentifierProto value) {
            copyOnWrite();
            ((ActivityDisplayProto) this.instance).mergeResumedActivity(value);
            return this;
        }

        public Builder clearResumedActivity() {
            copyOnWrite();
            ((ActivityDisplayProto) this.instance).clearResumedActivity();
            return this;
        }

        @Override // com.android.server.am.ActivityDisplayProtoOrBuilder
        public boolean hasSingleTaskInstance() {
            return ((ActivityDisplayProto) this.instance).hasSingleTaskInstance();
        }

        @Override // com.android.server.am.ActivityDisplayProtoOrBuilder
        public boolean getSingleTaskInstance() {
            return ((ActivityDisplayProto) this.instance).getSingleTaskInstance();
        }

        public Builder setSingleTaskInstance(boolean value) {
            copyOnWrite();
            ((ActivityDisplayProto) this.instance).setSingleTaskInstance(value);
            return this;
        }

        public Builder clearSingleTaskInstance() {
            copyOnWrite();
            ((ActivityDisplayProto) this.instance).clearSingleTaskInstance();
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new ActivityDisplayProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                this.stacks_.makeImmutable();
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                ActivityDisplayProto other = (ActivityDisplayProto) arg1;
                this.configurationContainer_ = (ConfigurationContainerProto) visitor.visitMessage(this.configurationContainer_, other.configurationContainer_);
                this.id_ = visitor.visitInt(hasId(), this.id_, other.hasId(), other.id_);
                this.stacks_ = visitor.visitList(this.stacks_, other.stacks_);
                this.focusedStackId_ = visitor.visitInt(hasFocusedStackId(), this.focusedStackId_, other.hasFocusedStackId(), other.focusedStackId_);
                this.resumedActivity_ = (IdentifierProto) visitor.visitMessage(this.resumedActivity_, other.resumedActivity_);
                this.singleTaskInstance_ = visitor.visitBoolean(hasSingleTaskInstance(), this.singleTaskInstance_, other.hasSingleTaskInstance(), other.singleTaskInstance_);
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
                            if (!this.stacks_.isModifiable()) {
                                this.stacks_ = GeneratedMessageLite.mutableCopy(this.stacks_);
                            }
                            this.stacks_.add((ActivityStackProto) input.readMessage(ActivityStackProto.parser(), extensionRegistry));
                        } else if (tag == 32) {
                            this.bitField0_ |= 4;
                            this.focusedStackId_ = input.readInt32();
                        } else if (tag == 42) {
                            IdentifierProto.Builder subBuilder2 = null;
                            if ((this.bitField0_ & 8) == 8) {
                                subBuilder2 = (IdentifierProto.Builder) this.resumedActivity_.toBuilder();
                            }
                            this.resumedActivity_ = (IdentifierProto) input.readMessage(IdentifierProto.parser(), extensionRegistry);
                            if (subBuilder2 != null) {
                                subBuilder2.mergeFrom((GeneratedMessageLite) this.resumedActivity_);
                                this.resumedActivity_ = (IdentifierProto) subBuilder2.buildPartial();
                            }
                            this.bitField0_ |= 8;
                        } else if (tag == 48) {
                            this.bitField0_ = 16 | this.bitField0_;
                            this.singleTaskInstance_ = input.readBool();
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
                    synchronized (ActivityDisplayProto.class) {
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

    public static ActivityDisplayProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<ActivityDisplayProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
