package com.android.server.wm;

import android.content.ConfigurationProto;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;

public final class ConfigurationContainerProto extends GeneratedMessageLite<ConfigurationContainerProto, Builder> implements ConfigurationContainerProtoOrBuilder {
    private static final ConfigurationContainerProto DEFAULT_INSTANCE = new ConfigurationContainerProto();
    public static final int FULL_CONFIGURATION_FIELD_NUMBER = 2;
    public static final int MERGED_OVERRIDE_CONFIGURATION_FIELD_NUMBER = 3;
    public static final int OVERRIDE_CONFIGURATION_FIELD_NUMBER = 1;
    private static volatile Parser<ConfigurationContainerProto> PARSER;
    private int bitField0_;
    private ConfigurationProto fullConfiguration_;
    private ConfigurationProto mergedOverrideConfiguration_;
    private ConfigurationProto overrideConfiguration_;

    private ConfigurationContainerProto() {
    }

    @Override // com.android.server.wm.ConfigurationContainerProtoOrBuilder
    public boolean hasOverrideConfiguration() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // com.android.server.wm.ConfigurationContainerProtoOrBuilder
    public ConfigurationProto getOverrideConfiguration() {
        ConfigurationProto configurationProto = this.overrideConfiguration_;
        return configurationProto == null ? ConfigurationProto.getDefaultInstance() : configurationProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setOverrideConfiguration(ConfigurationProto value) {
        if (value != null) {
            this.overrideConfiguration_ = value;
            this.bitField0_ |= 1;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setOverrideConfiguration(ConfigurationProto.Builder builderForValue) {
        this.overrideConfiguration_ = (ConfigurationProto) builderForValue.build();
        this.bitField0_ |= 1;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeOverrideConfiguration(ConfigurationProto value) {
        ConfigurationProto configurationProto = this.overrideConfiguration_;
        if (configurationProto == null || configurationProto == ConfigurationProto.getDefaultInstance()) {
            this.overrideConfiguration_ = value;
        } else {
            this.overrideConfiguration_ = (ConfigurationProto) ((ConfigurationProto.Builder) ConfigurationProto.newBuilder(this.overrideConfiguration_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 1;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearOverrideConfiguration() {
        this.overrideConfiguration_ = null;
        this.bitField0_ &= -2;
    }

    @Override // com.android.server.wm.ConfigurationContainerProtoOrBuilder
    public boolean hasFullConfiguration() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // com.android.server.wm.ConfigurationContainerProtoOrBuilder
    public ConfigurationProto getFullConfiguration() {
        ConfigurationProto configurationProto = this.fullConfiguration_;
        return configurationProto == null ? ConfigurationProto.getDefaultInstance() : configurationProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setFullConfiguration(ConfigurationProto value) {
        if (value != null) {
            this.fullConfiguration_ = value;
            this.bitField0_ |= 2;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setFullConfiguration(ConfigurationProto.Builder builderForValue) {
        this.fullConfiguration_ = (ConfigurationProto) builderForValue.build();
        this.bitField0_ |= 2;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeFullConfiguration(ConfigurationProto value) {
        ConfigurationProto configurationProto = this.fullConfiguration_;
        if (configurationProto == null || configurationProto == ConfigurationProto.getDefaultInstance()) {
            this.fullConfiguration_ = value;
        } else {
            this.fullConfiguration_ = (ConfigurationProto) ((ConfigurationProto.Builder) ConfigurationProto.newBuilder(this.fullConfiguration_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 2;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearFullConfiguration() {
        this.fullConfiguration_ = null;
        this.bitField0_ &= -3;
    }

    @Override // com.android.server.wm.ConfigurationContainerProtoOrBuilder
    public boolean hasMergedOverrideConfiguration() {
        return (this.bitField0_ & 4) == 4;
    }

    @Override // com.android.server.wm.ConfigurationContainerProtoOrBuilder
    public ConfigurationProto getMergedOverrideConfiguration() {
        ConfigurationProto configurationProto = this.mergedOverrideConfiguration_;
        return configurationProto == null ? ConfigurationProto.getDefaultInstance() : configurationProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setMergedOverrideConfiguration(ConfigurationProto value) {
        if (value != null) {
            this.mergedOverrideConfiguration_ = value;
            this.bitField0_ |= 4;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setMergedOverrideConfiguration(ConfigurationProto.Builder builderForValue) {
        this.mergedOverrideConfiguration_ = (ConfigurationProto) builderForValue.build();
        this.bitField0_ |= 4;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeMergedOverrideConfiguration(ConfigurationProto value) {
        ConfigurationProto configurationProto = this.mergedOverrideConfiguration_;
        if (configurationProto == null || configurationProto == ConfigurationProto.getDefaultInstance()) {
            this.mergedOverrideConfiguration_ = value;
        } else {
            this.mergedOverrideConfiguration_ = (ConfigurationProto) ((ConfigurationProto.Builder) ConfigurationProto.newBuilder(this.mergedOverrideConfiguration_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 4;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearMergedOverrideConfiguration() {
        this.mergedOverrideConfiguration_ = null;
        this.bitField0_ &= -5;
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeMessage(1, getOverrideConfiguration());
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeMessage(2, getFullConfiguration());
        }
        if ((this.bitField0_ & 4) == 4) {
            output.writeMessage(3, getMergedOverrideConfiguration());
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
            size2 = 0 + CodedOutputStream.computeMessageSize(1, getOverrideConfiguration());
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeMessageSize(2, getFullConfiguration());
        }
        if ((this.bitField0_ & 4) == 4) {
            size2 += CodedOutputStream.computeMessageSize(3, getMergedOverrideConfiguration());
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static ConfigurationContainerProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (ConfigurationContainerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static ConfigurationContainerProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (ConfigurationContainerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static ConfigurationContainerProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (ConfigurationContainerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static ConfigurationContainerProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (ConfigurationContainerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static ConfigurationContainerProto parseFrom(InputStream input) throws IOException {
        return (ConfigurationContainerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static ConfigurationContainerProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (ConfigurationContainerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static ConfigurationContainerProto parseDelimitedFrom(InputStream input) throws IOException {
        return (ConfigurationContainerProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static ConfigurationContainerProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (ConfigurationContainerProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static ConfigurationContainerProto parseFrom(CodedInputStream input) throws IOException {
        return (ConfigurationContainerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static ConfigurationContainerProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (ConfigurationContainerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(ConfigurationContainerProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<ConfigurationContainerProto, Builder> implements ConfigurationContainerProtoOrBuilder {
        private Builder() {
            super(ConfigurationContainerProto.DEFAULT_INSTANCE);
        }

        @Override // com.android.server.wm.ConfigurationContainerProtoOrBuilder
        public boolean hasOverrideConfiguration() {
            return ((ConfigurationContainerProto) this.instance).hasOverrideConfiguration();
        }

        @Override // com.android.server.wm.ConfigurationContainerProtoOrBuilder
        public ConfigurationProto getOverrideConfiguration() {
            return ((ConfigurationContainerProto) this.instance).getOverrideConfiguration();
        }

        public Builder setOverrideConfiguration(ConfigurationProto value) {
            copyOnWrite();
            ((ConfigurationContainerProto) this.instance).setOverrideConfiguration((ConfigurationContainerProto) value);
            return this;
        }

        public Builder setOverrideConfiguration(ConfigurationProto.Builder builderForValue) {
            copyOnWrite();
            ((ConfigurationContainerProto) this.instance).setOverrideConfiguration((ConfigurationContainerProto) builderForValue);
            return this;
        }

        public Builder mergeOverrideConfiguration(ConfigurationProto value) {
            copyOnWrite();
            ((ConfigurationContainerProto) this.instance).mergeOverrideConfiguration(value);
            return this;
        }

        public Builder clearOverrideConfiguration() {
            copyOnWrite();
            ((ConfigurationContainerProto) this.instance).clearOverrideConfiguration();
            return this;
        }

        @Override // com.android.server.wm.ConfigurationContainerProtoOrBuilder
        public boolean hasFullConfiguration() {
            return ((ConfigurationContainerProto) this.instance).hasFullConfiguration();
        }

        @Override // com.android.server.wm.ConfigurationContainerProtoOrBuilder
        public ConfigurationProto getFullConfiguration() {
            return ((ConfigurationContainerProto) this.instance).getFullConfiguration();
        }

        public Builder setFullConfiguration(ConfigurationProto value) {
            copyOnWrite();
            ((ConfigurationContainerProto) this.instance).setFullConfiguration((ConfigurationContainerProto) value);
            return this;
        }

        public Builder setFullConfiguration(ConfigurationProto.Builder builderForValue) {
            copyOnWrite();
            ((ConfigurationContainerProto) this.instance).setFullConfiguration((ConfigurationContainerProto) builderForValue);
            return this;
        }

        public Builder mergeFullConfiguration(ConfigurationProto value) {
            copyOnWrite();
            ((ConfigurationContainerProto) this.instance).mergeFullConfiguration(value);
            return this;
        }

        public Builder clearFullConfiguration() {
            copyOnWrite();
            ((ConfigurationContainerProto) this.instance).clearFullConfiguration();
            return this;
        }

        @Override // com.android.server.wm.ConfigurationContainerProtoOrBuilder
        public boolean hasMergedOverrideConfiguration() {
            return ((ConfigurationContainerProto) this.instance).hasMergedOverrideConfiguration();
        }

        @Override // com.android.server.wm.ConfigurationContainerProtoOrBuilder
        public ConfigurationProto getMergedOverrideConfiguration() {
            return ((ConfigurationContainerProto) this.instance).getMergedOverrideConfiguration();
        }

        public Builder setMergedOverrideConfiguration(ConfigurationProto value) {
            copyOnWrite();
            ((ConfigurationContainerProto) this.instance).setMergedOverrideConfiguration((ConfigurationContainerProto) value);
            return this;
        }

        public Builder setMergedOverrideConfiguration(ConfigurationProto.Builder builderForValue) {
            copyOnWrite();
            ((ConfigurationContainerProto) this.instance).setMergedOverrideConfiguration((ConfigurationContainerProto) builderForValue);
            return this;
        }

        public Builder mergeMergedOverrideConfiguration(ConfigurationProto value) {
            copyOnWrite();
            ((ConfigurationContainerProto) this.instance).mergeMergedOverrideConfiguration(value);
            return this;
        }

        public Builder clearMergedOverrideConfiguration() {
            copyOnWrite();
            ((ConfigurationContainerProto) this.instance).clearMergedOverrideConfiguration();
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new ConfigurationContainerProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                ConfigurationContainerProto other = (ConfigurationContainerProto) arg1;
                this.overrideConfiguration_ = (ConfigurationProto) visitor.visitMessage(this.overrideConfiguration_, other.overrideConfiguration_);
                this.fullConfiguration_ = (ConfigurationProto) visitor.visitMessage(this.fullConfiguration_, other.fullConfiguration_);
                this.mergedOverrideConfiguration_ = (ConfigurationProto) visitor.visitMessage(this.mergedOverrideConfiguration_, other.mergedOverrideConfiguration_);
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
                            ConfigurationProto.Builder subBuilder = null;
                            if ((this.bitField0_ & 1) == 1) {
                                subBuilder = (ConfigurationProto.Builder) this.overrideConfiguration_.toBuilder();
                            }
                            this.overrideConfiguration_ = (ConfigurationProto) input.readMessage(ConfigurationProto.parser(), extensionRegistry);
                            if (subBuilder != null) {
                                subBuilder.mergeFrom((GeneratedMessageLite) this.overrideConfiguration_);
                                this.overrideConfiguration_ = (ConfigurationProto) subBuilder.buildPartial();
                            }
                            this.bitField0_ |= 1;
                        } else if (tag == 18) {
                            ConfigurationProto.Builder subBuilder2 = null;
                            if ((this.bitField0_ & 2) == 2) {
                                subBuilder2 = (ConfigurationProto.Builder) this.fullConfiguration_.toBuilder();
                            }
                            this.fullConfiguration_ = (ConfigurationProto) input.readMessage(ConfigurationProto.parser(), extensionRegistry);
                            if (subBuilder2 != null) {
                                subBuilder2.mergeFrom((GeneratedMessageLite) this.fullConfiguration_);
                                this.fullConfiguration_ = (ConfigurationProto) subBuilder2.buildPartial();
                            }
                            this.bitField0_ |= 2;
                        } else if (tag == 26) {
                            ConfigurationProto.Builder subBuilder3 = null;
                            if ((this.bitField0_ & 4) == 4) {
                                subBuilder3 = (ConfigurationProto.Builder) this.mergedOverrideConfiguration_.toBuilder();
                            }
                            this.mergedOverrideConfiguration_ = (ConfigurationProto) input.readMessage(ConfigurationProto.parser(), extensionRegistry);
                            if (subBuilder3 != null) {
                                subBuilder3.mergeFrom((GeneratedMessageLite) this.mergedOverrideConfiguration_);
                                this.mergedOverrideConfiguration_ = (ConfigurationProto) subBuilder3.buildPartial();
                            }
                            this.bitField0_ |= 4;
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
                    synchronized (ConfigurationContainerProto.class) {
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

    public static ConfigurationContainerProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<ConfigurationContainerProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
