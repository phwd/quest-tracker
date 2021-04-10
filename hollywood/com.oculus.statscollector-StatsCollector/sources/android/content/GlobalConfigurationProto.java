package android.content;

import android.content.DeviceConfigurationProto;
import android.content.ResourcesConfigurationProto;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;

public final class GlobalConfigurationProto extends GeneratedMessageLite<GlobalConfigurationProto, Builder> implements GlobalConfigurationProtoOrBuilder {
    private static final GlobalConfigurationProto DEFAULT_INSTANCE = new GlobalConfigurationProto();
    public static final int DEVICE_FIELD_NUMBER = 2;
    private static volatile Parser<GlobalConfigurationProto> PARSER = null;
    public static final int RESOURCES_FIELD_NUMBER = 1;
    private int bitField0_;
    private DeviceConfigurationProto device_;
    private byte memoizedIsInitialized = -1;
    private ResourcesConfigurationProto resources_;

    private GlobalConfigurationProto() {
    }

    @Override // android.content.GlobalConfigurationProtoOrBuilder
    public boolean hasResources() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // android.content.GlobalConfigurationProtoOrBuilder
    public ResourcesConfigurationProto getResources() {
        ResourcesConfigurationProto resourcesConfigurationProto = this.resources_;
        return resourcesConfigurationProto == null ? ResourcesConfigurationProto.getDefaultInstance() : resourcesConfigurationProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setResources(ResourcesConfigurationProto value) {
        if (value != null) {
            this.resources_ = value;
            this.bitField0_ |= 1;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setResources(ResourcesConfigurationProto.Builder builderForValue) {
        this.resources_ = (ResourcesConfigurationProto) builderForValue.build();
        this.bitField0_ |= 1;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeResources(ResourcesConfigurationProto value) {
        ResourcesConfigurationProto resourcesConfigurationProto = this.resources_;
        if (resourcesConfigurationProto == null || resourcesConfigurationProto == ResourcesConfigurationProto.getDefaultInstance()) {
            this.resources_ = value;
        } else {
            this.resources_ = (ResourcesConfigurationProto) ((ResourcesConfigurationProto.Builder) ResourcesConfigurationProto.newBuilder(this.resources_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 1;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearResources() {
        this.resources_ = null;
        this.bitField0_ &= -2;
    }

    @Override // android.content.GlobalConfigurationProtoOrBuilder
    public boolean hasDevice() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // android.content.GlobalConfigurationProtoOrBuilder
    public DeviceConfigurationProto getDevice() {
        DeviceConfigurationProto deviceConfigurationProto = this.device_;
        return deviceConfigurationProto == null ? DeviceConfigurationProto.getDefaultInstance() : deviceConfigurationProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setDevice(DeviceConfigurationProto value) {
        if (value != null) {
            this.device_ = value;
            this.bitField0_ |= 2;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setDevice(DeviceConfigurationProto.Builder builderForValue) {
        this.device_ = (DeviceConfigurationProto) builderForValue.build();
        this.bitField0_ |= 2;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeDevice(DeviceConfigurationProto value) {
        DeviceConfigurationProto deviceConfigurationProto = this.device_;
        if (deviceConfigurationProto == null || deviceConfigurationProto == DeviceConfigurationProto.getDefaultInstance()) {
            this.device_ = value;
        } else {
            this.device_ = (DeviceConfigurationProto) ((DeviceConfigurationProto.Builder) DeviceConfigurationProto.newBuilder(this.device_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 2;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearDevice() {
        this.device_ = null;
        this.bitField0_ &= -3;
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeMessage(1, getResources());
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeMessage(2, getDevice());
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
            size2 = 0 + CodedOutputStream.computeMessageSize(1, getResources());
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeMessageSize(2, getDevice());
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static GlobalConfigurationProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (GlobalConfigurationProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static GlobalConfigurationProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (GlobalConfigurationProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static GlobalConfigurationProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (GlobalConfigurationProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static GlobalConfigurationProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (GlobalConfigurationProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static GlobalConfigurationProto parseFrom(InputStream input) throws IOException {
        return (GlobalConfigurationProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static GlobalConfigurationProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (GlobalConfigurationProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static GlobalConfigurationProto parseDelimitedFrom(InputStream input) throws IOException {
        return (GlobalConfigurationProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static GlobalConfigurationProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (GlobalConfigurationProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static GlobalConfigurationProto parseFrom(CodedInputStream input) throws IOException {
        return (GlobalConfigurationProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static GlobalConfigurationProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (GlobalConfigurationProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(GlobalConfigurationProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<GlobalConfigurationProto, Builder> implements GlobalConfigurationProtoOrBuilder {
        private Builder() {
            super(GlobalConfigurationProto.DEFAULT_INSTANCE);
        }

        @Override // android.content.GlobalConfigurationProtoOrBuilder
        public boolean hasResources() {
            return ((GlobalConfigurationProto) this.instance).hasResources();
        }

        @Override // android.content.GlobalConfigurationProtoOrBuilder
        public ResourcesConfigurationProto getResources() {
            return ((GlobalConfigurationProto) this.instance).getResources();
        }

        public Builder setResources(ResourcesConfigurationProto value) {
            copyOnWrite();
            ((GlobalConfigurationProto) this.instance).setResources((GlobalConfigurationProto) value);
            return this;
        }

        public Builder setResources(ResourcesConfigurationProto.Builder builderForValue) {
            copyOnWrite();
            ((GlobalConfigurationProto) this.instance).setResources((GlobalConfigurationProto) builderForValue);
            return this;
        }

        public Builder mergeResources(ResourcesConfigurationProto value) {
            copyOnWrite();
            ((GlobalConfigurationProto) this.instance).mergeResources(value);
            return this;
        }

        public Builder clearResources() {
            copyOnWrite();
            ((GlobalConfigurationProto) this.instance).clearResources();
            return this;
        }

        @Override // android.content.GlobalConfigurationProtoOrBuilder
        public boolean hasDevice() {
            return ((GlobalConfigurationProto) this.instance).hasDevice();
        }

        @Override // android.content.GlobalConfigurationProtoOrBuilder
        public DeviceConfigurationProto getDevice() {
            return ((GlobalConfigurationProto) this.instance).getDevice();
        }

        public Builder setDevice(DeviceConfigurationProto value) {
            copyOnWrite();
            ((GlobalConfigurationProto) this.instance).setDevice((GlobalConfigurationProto) value);
            return this;
        }

        public Builder setDevice(DeviceConfigurationProto.Builder builderForValue) {
            copyOnWrite();
            ((GlobalConfigurationProto) this.instance).setDevice((GlobalConfigurationProto) builderForValue);
            return this;
        }

        public Builder mergeDevice(DeviceConfigurationProto value) {
            copyOnWrite();
            ((GlobalConfigurationProto) this.instance).mergeDevice(value);
            return this;
        }

        public Builder clearDevice() {
            copyOnWrite();
            ((GlobalConfigurationProto) this.instance).clearDevice();
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new GlobalConfigurationProto();
            case IS_INITIALIZED:
                byte isInitialized = this.memoizedIsInitialized;
                if (isInitialized == 1) {
                    return DEFAULT_INSTANCE;
                }
                if (isInitialized == 0) {
                    return null;
                }
                boolean shouldMemoize = ((Boolean) arg0).booleanValue();
                if (!hasResources() || getResources().isInitialized()) {
                    if (shouldMemoize) {
                        this.memoizedIsInitialized = 1;
                    }
                    return DEFAULT_INSTANCE;
                }
                if (shouldMemoize) {
                    this.memoizedIsInitialized = 0;
                }
                return null;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                GlobalConfigurationProto other = (GlobalConfigurationProto) arg1;
                this.resources_ = (ResourcesConfigurationProto) visitor.visitMessage(this.resources_, other.resources_);
                this.device_ = (DeviceConfigurationProto) visitor.visitMessage(this.device_, other.device_);
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
                            ResourcesConfigurationProto.Builder subBuilder = null;
                            if ((this.bitField0_ & 1) == 1) {
                                subBuilder = (ResourcesConfigurationProto.Builder) this.resources_.toBuilder();
                            }
                            this.resources_ = (ResourcesConfigurationProto) input.readMessage(ResourcesConfigurationProto.parser(), extensionRegistry);
                            if (subBuilder != null) {
                                subBuilder.mergeFrom((GeneratedMessageLite) this.resources_);
                                this.resources_ = (ResourcesConfigurationProto) subBuilder.buildPartial();
                            }
                            this.bitField0_ |= 1;
                        } else if (tag == 18) {
                            DeviceConfigurationProto.Builder subBuilder2 = null;
                            if ((this.bitField0_ & 2) == 2) {
                                subBuilder2 = (DeviceConfigurationProto.Builder) this.device_.toBuilder();
                            }
                            this.device_ = (DeviceConfigurationProto) input.readMessage(DeviceConfigurationProto.parser(), extensionRegistry);
                            if (subBuilder2 != null) {
                                subBuilder2.mergeFrom((GeneratedMessageLite) this.device_);
                                this.device_ = (DeviceConfigurationProto) subBuilder2.buildPartial();
                            }
                            this.bitField0_ |= 2;
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
                    synchronized (GlobalConfigurationProto.class) {
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

    public static GlobalConfigurationProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<GlobalConfigurationProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
