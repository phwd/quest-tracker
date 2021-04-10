package android.content;

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

public final class ResourcesConfigurationProto extends GeneratedMessageLite<ResourcesConfigurationProto, Builder> implements ResourcesConfigurationProtoOrBuilder {
    public static final int CONFIGURATION_FIELD_NUMBER = 1;
    private static final ResourcesConfigurationProto DEFAULT_INSTANCE = new ResourcesConfigurationProto();
    private static volatile Parser<ResourcesConfigurationProto> PARSER = null;
    public static final int SCREEN_HEIGHT_PX_FIELD_NUMBER = 4;
    public static final int SCREEN_WIDTH_PX_FIELD_NUMBER = 3;
    public static final int SDK_VERSION_FIELD_NUMBER = 2;
    private int bitField0_;
    private ConfigurationProto configuration_;
    private byte memoizedIsInitialized = -1;
    private int screenHeightPx_ = 0;
    private int screenWidthPx_ = 0;
    private int sdkVersion_ = 0;

    private ResourcesConfigurationProto() {
    }

    @Override // android.content.ResourcesConfigurationProtoOrBuilder
    public boolean hasConfiguration() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // android.content.ResourcesConfigurationProtoOrBuilder
    public ConfigurationProto getConfiguration() {
        ConfigurationProto configurationProto = this.configuration_;
        return configurationProto == null ? ConfigurationProto.getDefaultInstance() : configurationProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setConfiguration(ConfigurationProto value) {
        if (value != null) {
            this.configuration_ = value;
            this.bitField0_ |= 1;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setConfiguration(ConfigurationProto.Builder builderForValue) {
        this.configuration_ = (ConfigurationProto) builderForValue.build();
        this.bitField0_ |= 1;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeConfiguration(ConfigurationProto value) {
        ConfigurationProto configurationProto = this.configuration_;
        if (configurationProto == null || configurationProto == ConfigurationProto.getDefaultInstance()) {
            this.configuration_ = value;
        } else {
            this.configuration_ = (ConfigurationProto) ((ConfigurationProto.Builder) ConfigurationProto.newBuilder(this.configuration_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 1;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearConfiguration() {
        this.configuration_ = null;
        this.bitField0_ &= -2;
    }

    @Override // android.content.ResourcesConfigurationProtoOrBuilder
    public boolean hasSdkVersion() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // android.content.ResourcesConfigurationProtoOrBuilder
    public int getSdkVersion() {
        return this.sdkVersion_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setSdkVersion(int value) {
        this.bitField0_ |= 2;
        this.sdkVersion_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearSdkVersion() {
        this.bitField0_ &= -3;
        this.sdkVersion_ = 0;
    }

    @Override // android.content.ResourcesConfigurationProtoOrBuilder
    public boolean hasScreenWidthPx() {
        return (this.bitField0_ & 4) == 4;
    }

    @Override // android.content.ResourcesConfigurationProtoOrBuilder
    public int getScreenWidthPx() {
        return this.screenWidthPx_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setScreenWidthPx(int value) {
        this.bitField0_ |= 4;
        this.screenWidthPx_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearScreenWidthPx() {
        this.bitField0_ &= -5;
        this.screenWidthPx_ = 0;
    }

    @Override // android.content.ResourcesConfigurationProtoOrBuilder
    public boolean hasScreenHeightPx() {
        return (this.bitField0_ & 8) == 8;
    }

    @Override // android.content.ResourcesConfigurationProtoOrBuilder
    public int getScreenHeightPx() {
        return this.screenHeightPx_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setScreenHeightPx(int value) {
        this.bitField0_ |= 8;
        this.screenHeightPx_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearScreenHeightPx() {
        this.bitField0_ &= -9;
        this.screenHeightPx_ = 0;
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeMessage(1, getConfiguration());
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeUInt32(2, this.sdkVersion_);
        }
        if ((this.bitField0_ & 4) == 4) {
            output.writeUInt32(3, this.screenWidthPx_);
        }
        if ((this.bitField0_ & 8) == 8) {
            output.writeUInt32(4, this.screenHeightPx_);
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
            size2 = 0 + CodedOutputStream.computeMessageSize(1, getConfiguration());
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeUInt32Size(2, this.sdkVersion_);
        }
        if ((this.bitField0_ & 4) == 4) {
            size2 += CodedOutputStream.computeUInt32Size(3, this.screenWidthPx_);
        }
        if ((this.bitField0_ & 8) == 8) {
            size2 += CodedOutputStream.computeUInt32Size(4, this.screenHeightPx_);
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static ResourcesConfigurationProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (ResourcesConfigurationProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static ResourcesConfigurationProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (ResourcesConfigurationProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static ResourcesConfigurationProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (ResourcesConfigurationProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static ResourcesConfigurationProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (ResourcesConfigurationProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static ResourcesConfigurationProto parseFrom(InputStream input) throws IOException {
        return (ResourcesConfigurationProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static ResourcesConfigurationProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (ResourcesConfigurationProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static ResourcesConfigurationProto parseDelimitedFrom(InputStream input) throws IOException {
        return (ResourcesConfigurationProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static ResourcesConfigurationProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (ResourcesConfigurationProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static ResourcesConfigurationProto parseFrom(CodedInputStream input) throws IOException {
        return (ResourcesConfigurationProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static ResourcesConfigurationProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (ResourcesConfigurationProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(ResourcesConfigurationProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<ResourcesConfigurationProto, Builder> implements ResourcesConfigurationProtoOrBuilder {
        private Builder() {
            super(ResourcesConfigurationProto.DEFAULT_INSTANCE);
        }

        @Override // android.content.ResourcesConfigurationProtoOrBuilder
        public boolean hasConfiguration() {
            return ((ResourcesConfigurationProto) this.instance).hasConfiguration();
        }

        @Override // android.content.ResourcesConfigurationProtoOrBuilder
        public ConfigurationProto getConfiguration() {
            return ((ResourcesConfigurationProto) this.instance).getConfiguration();
        }

        public Builder setConfiguration(ConfigurationProto value) {
            copyOnWrite();
            ((ResourcesConfigurationProto) this.instance).setConfiguration((ResourcesConfigurationProto) value);
            return this;
        }

        public Builder setConfiguration(ConfigurationProto.Builder builderForValue) {
            copyOnWrite();
            ((ResourcesConfigurationProto) this.instance).setConfiguration((ResourcesConfigurationProto) builderForValue);
            return this;
        }

        public Builder mergeConfiguration(ConfigurationProto value) {
            copyOnWrite();
            ((ResourcesConfigurationProto) this.instance).mergeConfiguration(value);
            return this;
        }

        public Builder clearConfiguration() {
            copyOnWrite();
            ((ResourcesConfigurationProto) this.instance).clearConfiguration();
            return this;
        }

        @Override // android.content.ResourcesConfigurationProtoOrBuilder
        public boolean hasSdkVersion() {
            return ((ResourcesConfigurationProto) this.instance).hasSdkVersion();
        }

        @Override // android.content.ResourcesConfigurationProtoOrBuilder
        public int getSdkVersion() {
            return ((ResourcesConfigurationProto) this.instance).getSdkVersion();
        }

        public Builder setSdkVersion(int value) {
            copyOnWrite();
            ((ResourcesConfigurationProto) this.instance).setSdkVersion(value);
            return this;
        }

        public Builder clearSdkVersion() {
            copyOnWrite();
            ((ResourcesConfigurationProto) this.instance).clearSdkVersion();
            return this;
        }

        @Override // android.content.ResourcesConfigurationProtoOrBuilder
        public boolean hasScreenWidthPx() {
            return ((ResourcesConfigurationProto) this.instance).hasScreenWidthPx();
        }

        @Override // android.content.ResourcesConfigurationProtoOrBuilder
        public int getScreenWidthPx() {
            return ((ResourcesConfigurationProto) this.instance).getScreenWidthPx();
        }

        public Builder setScreenWidthPx(int value) {
            copyOnWrite();
            ((ResourcesConfigurationProto) this.instance).setScreenWidthPx(value);
            return this;
        }

        public Builder clearScreenWidthPx() {
            copyOnWrite();
            ((ResourcesConfigurationProto) this.instance).clearScreenWidthPx();
            return this;
        }

        @Override // android.content.ResourcesConfigurationProtoOrBuilder
        public boolean hasScreenHeightPx() {
            return ((ResourcesConfigurationProto) this.instance).hasScreenHeightPx();
        }

        @Override // android.content.ResourcesConfigurationProtoOrBuilder
        public int getScreenHeightPx() {
            return ((ResourcesConfigurationProto) this.instance).getScreenHeightPx();
        }

        public Builder setScreenHeightPx(int value) {
            copyOnWrite();
            ((ResourcesConfigurationProto) this.instance).setScreenHeightPx(value);
            return this;
        }

        public Builder clearScreenHeightPx() {
            copyOnWrite();
            ((ResourcesConfigurationProto) this.instance).clearScreenHeightPx();
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new ResourcesConfigurationProto();
            case IS_INITIALIZED:
                byte isInitialized = this.memoizedIsInitialized;
                if (isInitialized == 1) {
                    return DEFAULT_INSTANCE;
                }
                if (isInitialized == 0) {
                    return null;
                }
                boolean shouldMemoize = ((Boolean) arg0).booleanValue();
                if (!hasConfiguration()) {
                    if (shouldMemoize) {
                        this.memoizedIsInitialized = 0;
                    }
                    return null;
                }
                if (shouldMemoize) {
                    this.memoizedIsInitialized = 1;
                }
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                ResourcesConfigurationProto other = (ResourcesConfigurationProto) arg1;
                this.configuration_ = (ConfigurationProto) visitor.visitMessage(this.configuration_, other.configuration_);
                this.sdkVersion_ = visitor.visitInt(hasSdkVersion(), this.sdkVersion_, other.hasSdkVersion(), other.sdkVersion_);
                this.screenWidthPx_ = visitor.visitInt(hasScreenWidthPx(), this.screenWidthPx_, other.hasScreenWidthPx(), other.screenWidthPx_);
                this.screenHeightPx_ = visitor.visitInt(hasScreenHeightPx(), this.screenHeightPx_, other.hasScreenHeightPx(), other.screenHeightPx_);
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
                                subBuilder = (ConfigurationProto.Builder) this.configuration_.toBuilder();
                            }
                            this.configuration_ = (ConfigurationProto) input.readMessage(ConfigurationProto.parser(), extensionRegistry);
                            if (subBuilder != null) {
                                subBuilder.mergeFrom((GeneratedMessageLite) this.configuration_);
                                this.configuration_ = (ConfigurationProto) subBuilder.buildPartial();
                            }
                            this.bitField0_ |= 1;
                        } else if (tag == 16) {
                            this.bitField0_ |= 2;
                            this.sdkVersion_ = input.readUInt32();
                        } else if (tag == 24) {
                            this.bitField0_ |= 4;
                            this.screenWidthPx_ = input.readUInt32();
                        } else if (tag == 32) {
                            this.bitField0_ |= 8;
                            this.screenHeightPx_ = input.readUInt32();
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
                    synchronized (ResourcesConfigurationProto.class) {
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

    public static ResourcesConfigurationProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<ResourcesConfigurationProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
