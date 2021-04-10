package com.android.server.am;

import com.android.server.wm.ConfigurationContainerProto;
import com.android.server.wm.IdentifierProto;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;

public final class ActivityRecordProto extends GeneratedMessageLite<ActivityRecordProto, Builder> implements ActivityRecordProtoOrBuilder {
    public static final int CONFIGURATION_CONTAINER_FIELD_NUMBER = 1;
    private static final ActivityRecordProto DEFAULT_INSTANCE = new ActivityRecordProto();
    public static final int FRONT_OF_TASK_FIELD_NUMBER = 5;
    public static final int IDENTIFIER_FIELD_NUMBER = 2;
    private static volatile Parser<ActivityRecordProto> PARSER = null;
    public static final int PROC_ID_FIELD_NUMBER = 6;
    public static final int STATE_FIELD_NUMBER = 3;
    public static final int TRANSLUCENT_FIELD_NUMBER = 7;
    public static final int VISIBLE_FIELD_NUMBER = 4;
    private int bitField0_;
    private ConfigurationContainerProto configurationContainer_;
    private boolean frontOfTask_ = false;
    private IdentifierProto identifier_;
    private int procId_ = 0;
    private String state_ = "";
    private boolean translucent_ = false;
    private boolean visible_ = false;

    private ActivityRecordProto() {
    }

    @Override // com.android.server.am.ActivityRecordProtoOrBuilder
    public boolean hasConfigurationContainer() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // com.android.server.am.ActivityRecordProtoOrBuilder
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

    @Override // com.android.server.am.ActivityRecordProtoOrBuilder
    public boolean hasIdentifier() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // com.android.server.am.ActivityRecordProtoOrBuilder
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

    @Override // com.android.server.am.ActivityRecordProtoOrBuilder
    public boolean hasState() {
        return (this.bitField0_ & 4) == 4;
    }

    @Override // com.android.server.am.ActivityRecordProtoOrBuilder
    public String getState() {
        return this.state_;
    }

    @Override // com.android.server.am.ActivityRecordProtoOrBuilder
    public ByteString getStateBytes() {
        return ByteString.copyFromUtf8(this.state_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setState(String value) {
        if (value != null) {
            this.bitField0_ |= 4;
            this.state_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearState() {
        this.bitField0_ &= -5;
        this.state_ = getDefaultInstance().getState();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setStateBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 4;
            this.state_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // com.android.server.am.ActivityRecordProtoOrBuilder
    public boolean hasVisible() {
        return (this.bitField0_ & 8) == 8;
    }

    @Override // com.android.server.am.ActivityRecordProtoOrBuilder
    public boolean getVisible() {
        return this.visible_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setVisible(boolean value) {
        this.bitField0_ |= 8;
        this.visible_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearVisible() {
        this.bitField0_ &= -9;
        this.visible_ = false;
    }

    @Override // com.android.server.am.ActivityRecordProtoOrBuilder
    public boolean hasFrontOfTask() {
        return (this.bitField0_ & 16) == 16;
    }

    @Override // com.android.server.am.ActivityRecordProtoOrBuilder
    public boolean getFrontOfTask() {
        return this.frontOfTask_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setFrontOfTask(boolean value) {
        this.bitField0_ |= 16;
        this.frontOfTask_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearFrontOfTask() {
        this.bitField0_ &= -17;
        this.frontOfTask_ = false;
    }

    @Override // com.android.server.am.ActivityRecordProtoOrBuilder
    public boolean hasProcId() {
        return (this.bitField0_ & 32) == 32;
    }

    @Override // com.android.server.am.ActivityRecordProtoOrBuilder
    public int getProcId() {
        return this.procId_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setProcId(int value) {
        this.bitField0_ |= 32;
        this.procId_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearProcId() {
        this.bitField0_ &= -33;
        this.procId_ = 0;
    }

    @Override // com.android.server.am.ActivityRecordProtoOrBuilder
    public boolean hasTranslucent() {
        return (this.bitField0_ & 64) == 64;
    }

    @Override // com.android.server.am.ActivityRecordProtoOrBuilder
    public boolean getTranslucent() {
        return this.translucent_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setTranslucent(boolean value) {
        this.bitField0_ |= 64;
        this.translucent_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearTranslucent() {
        this.bitField0_ &= -65;
        this.translucent_ = false;
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeMessage(1, getConfigurationContainer());
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeMessage(2, getIdentifier());
        }
        if ((this.bitField0_ & 4) == 4) {
            output.writeString(3, getState());
        }
        if ((this.bitField0_ & 8) == 8) {
            output.writeBool(4, this.visible_);
        }
        if ((this.bitField0_ & 16) == 16) {
            output.writeBool(5, this.frontOfTask_);
        }
        if ((this.bitField0_ & 32) == 32) {
            output.writeInt32(6, this.procId_);
        }
        if ((this.bitField0_ & 64) == 64) {
            output.writeBool(7, this.translucent_);
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
            size2 += CodedOutputStream.computeMessageSize(2, getIdentifier());
        }
        if ((this.bitField0_ & 4) == 4) {
            size2 += CodedOutputStream.computeStringSize(3, getState());
        }
        if ((this.bitField0_ & 8) == 8) {
            size2 += CodedOutputStream.computeBoolSize(4, this.visible_);
        }
        if ((this.bitField0_ & 16) == 16) {
            size2 += CodedOutputStream.computeBoolSize(5, this.frontOfTask_);
        }
        if ((this.bitField0_ & 32) == 32) {
            size2 += CodedOutputStream.computeInt32Size(6, this.procId_);
        }
        if ((this.bitField0_ & 64) == 64) {
            size2 += CodedOutputStream.computeBoolSize(7, this.translucent_);
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static ActivityRecordProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (ActivityRecordProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static ActivityRecordProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (ActivityRecordProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static ActivityRecordProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (ActivityRecordProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static ActivityRecordProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (ActivityRecordProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static ActivityRecordProto parseFrom(InputStream input) throws IOException {
        return (ActivityRecordProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static ActivityRecordProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (ActivityRecordProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static ActivityRecordProto parseDelimitedFrom(InputStream input) throws IOException {
        return (ActivityRecordProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static ActivityRecordProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (ActivityRecordProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static ActivityRecordProto parseFrom(CodedInputStream input) throws IOException {
        return (ActivityRecordProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static ActivityRecordProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (ActivityRecordProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(ActivityRecordProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<ActivityRecordProto, Builder> implements ActivityRecordProtoOrBuilder {
        private Builder() {
            super(ActivityRecordProto.DEFAULT_INSTANCE);
        }

        @Override // com.android.server.am.ActivityRecordProtoOrBuilder
        public boolean hasConfigurationContainer() {
            return ((ActivityRecordProto) this.instance).hasConfigurationContainer();
        }

        @Override // com.android.server.am.ActivityRecordProtoOrBuilder
        public ConfigurationContainerProto getConfigurationContainer() {
            return ((ActivityRecordProto) this.instance).getConfigurationContainer();
        }

        public Builder setConfigurationContainer(ConfigurationContainerProto value) {
            copyOnWrite();
            ((ActivityRecordProto) this.instance).setConfigurationContainer((ActivityRecordProto) value);
            return this;
        }

        public Builder setConfigurationContainer(ConfigurationContainerProto.Builder builderForValue) {
            copyOnWrite();
            ((ActivityRecordProto) this.instance).setConfigurationContainer((ActivityRecordProto) builderForValue);
            return this;
        }

        public Builder mergeConfigurationContainer(ConfigurationContainerProto value) {
            copyOnWrite();
            ((ActivityRecordProto) this.instance).mergeConfigurationContainer(value);
            return this;
        }

        public Builder clearConfigurationContainer() {
            copyOnWrite();
            ((ActivityRecordProto) this.instance).clearConfigurationContainer();
            return this;
        }

        @Override // com.android.server.am.ActivityRecordProtoOrBuilder
        public boolean hasIdentifier() {
            return ((ActivityRecordProto) this.instance).hasIdentifier();
        }

        @Override // com.android.server.am.ActivityRecordProtoOrBuilder
        public IdentifierProto getIdentifier() {
            return ((ActivityRecordProto) this.instance).getIdentifier();
        }

        public Builder setIdentifier(IdentifierProto value) {
            copyOnWrite();
            ((ActivityRecordProto) this.instance).setIdentifier((ActivityRecordProto) value);
            return this;
        }

        public Builder setIdentifier(IdentifierProto.Builder builderForValue) {
            copyOnWrite();
            ((ActivityRecordProto) this.instance).setIdentifier((ActivityRecordProto) builderForValue);
            return this;
        }

        public Builder mergeIdentifier(IdentifierProto value) {
            copyOnWrite();
            ((ActivityRecordProto) this.instance).mergeIdentifier(value);
            return this;
        }

        public Builder clearIdentifier() {
            copyOnWrite();
            ((ActivityRecordProto) this.instance).clearIdentifier();
            return this;
        }

        @Override // com.android.server.am.ActivityRecordProtoOrBuilder
        public boolean hasState() {
            return ((ActivityRecordProto) this.instance).hasState();
        }

        @Override // com.android.server.am.ActivityRecordProtoOrBuilder
        public String getState() {
            return ((ActivityRecordProto) this.instance).getState();
        }

        @Override // com.android.server.am.ActivityRecordProtoOrBuilder
        public ByteString getStateBytes() {
            return ((ActivityRecordProto) this.instance).getStateBytes();
        }

        public Builder setState(String value) {
            copyOnWrite();
            ((ActivityRecordProto) this.instance).setState(value);
            return this;
        }

        public Builder clearState() {
            copyOnWrite();
            ((ActivityRecordProto) this.instance).clearState();
            return this;
        }

        public Builder setStateBytes(ByteString value) {
            copyOnWrite();
            ((ActivityRecordProto) this.instance).setStateBytes(value);
            return this;
        }

        @Override // com.android.server.am.ActivityRecordProtoOrBuilder
        public boolean hasVisible() {
            return ((ActivityRecordProto) this.instance).hasVisible();
        }

        @Override // com.android.server.am.ActivityRecordProtoOrBuilder
        public boolean getVisible() {
            return ((ActivityRecordProto) this.instance).getVisible();
        }

        public Builder setVisible(boolean value) {
            copyOnWrite();
            ((ActivityRecordProto) this.instance).setVisible(value);
            return this;
        }

        public Builder clearVisible() {
            copyOnWrite();
            ((ActivityRecordProto) this.instance).clearVisible();
            return this;
        }

        @Override // com.android.server.am.ActivityRecordProtoOrBuilder
        public boolean hasFrontOfTask() {
            return ((ActivityRecordProto) this.instance).hasFrontOfTask();
        }

        @Override // com.android.server.am.ActivityRecordProtoOrBuilder
        public boolean getFrontOfTask() {
            return ((ActivityRecordProto) this.instance).getFrontOfTask();
        }

        public Builder setFrontOfTask(boolean value) {
            copyOnWrite();
            ((ActivityRecordProto) this.instance).setFrontOfTask(value);
            return this;
        }

        public Builder clearFrontOfTask() {
            copyOnWrite();
            ((ActivityRecordProto) this.instance).clearFrontOfTask();
            return this;
        }

        @Override // com.android.server.am.ActivityRecordProtoOrBuilder
        public boolean hasProcId() {
            return ((ActivityRecordProto) this.instance).hasProcId();
        }

        @Override // com.android.server.am.ActivityRecordProtoOrBuilder
        public int getProcId() {
            return ((ActivityRecordProto) this.instance).getProcId();
        }

        public Builder setProcId(int value) {
            copyOnWrite();
            ((ActivityRecordProto) this.instance).setProcId(value);
            return this;
        }

        public Builder clearProcId() {
            copyOnWrite();
            ((ActivityRecordProto) this.instance).clearProcId();
            return this;
        }

        @Override // com.android.server.am.ActivityRecordProtoOrBuilder
        public boolean hasTranslucent() {
            return ((ActivityRecordProto) this.instance).hasTranslucent();
        }

        @Override // com.android.server.am.ActivityRecordProtoOrBuilder
        public boolean getTranslucent() {
            return ((ActivityRecordProto) this.instance).getTranslucent();
        }

        public Builder setTranslucent(boolean value) {
            copyOnWrite();
            ((ActivityRecordProto) this.instance).setTranslucent(value);
            return this;
        }

        public Builder clearTranslucent() {
            copyOnWrite();
            ((ActivityRecordProto) this.instance).clearTranslucent();
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new ActivityRecordProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                ActivityRecordProto other = (ActivityRecordProto) arg1;
                this.configurationContainer_ = (ConfigurationContainerProto) visitor.visitMessage(this.configurationContainer_, other.configurationContainer_);
                this.identifier_ = (IdentifierProto) visitor.visitMessage(this.identifier_, other.identifier_);
                this.state_ = visitor.visitString(hasState(), this.state_, other.hasState(), other.state_);
                this.visible_ = visitor.visitBoolean(hasVisible(), this.visible_, other.hasVisible(), other.visible_);
                this.frontOfTask_ = visitor.visitBoolean(hasFrontOfTask(), this.frontOfTask_, other.hasFrontOfTask(), other.frontOfTask_);
                this.procId_ = visitor.visitInt(hasProcId(), this.procId_, other.hasProcId(), other.procId_);
                this.translucent_ = visitor.visitBoolean(hasTranslucent(), this.translucent_, other.hasTranslucent(), other.translucent_);
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
                        } else if (tag == 26) {
                            String s = input.readString();
                            this.bitField0_ |= 4;
                            this.state_ = s;
                        } else if (tag == 32) {
                            this.bitField0_ |= 8;
                            this.visible_ = input.readBool();
                        } else if (tag == 40) {
                            this.bitField0_ |= 16;
                            this.frontOfTask_ = input.readBool();
                        } else if (tag == 48) {
                            this.bitField0_ = 32 | this.bitField0_;
                            this.procId_ = input.readInt32();
                        } else if (tag == 56) {
                            this.bitField0_ |= 64;
                            this.translucent_ = input.readBool();
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
                    synchronized (ActivityRecordProto.class) {
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

    public static ActivityRecordProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<ActivityRecordProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
