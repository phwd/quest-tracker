package android.service;

import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;

public final class NetworkIdentityProto extends GeneratedMessageLite<NetworkIdentityProto, Builder> implements NetworkIdentityProtoOrBuilder {
    private static final NetworkIdentityProto DEFAULT_INSTANCE = new NetworkIdentityProto();
    public static final int DEFAULT_NETWORK_FIELD_NUMBER = 6;
    public static final int METERED_FIELD_NUMBER = 5;
    public static final int NETWORK_ID_FIELD_NUMBER = 3;
    private static volatile Parser<NetworkIdentityProto> PARSER = null;
    public static final int ROAMING_FIELD_NUMBER = 4;
    public static final int SUBSCRIBER_ID_FIELD_NUMBER = 2;
    public static final int TYPE_FIELD_NUMBER = 1;
    private int bitField0_;
    private boolean defaultNetwork_ = false;
    private boolean metered_ = false;
    private String networkId_ = "";
    private boolean roaming_ = false;
    private String subscriberId_ = "";
    private int type_ = 0;

    private NetworkIdentityProto() {
    }

    @Override // android.service.NetworkIdentityProtoOrBuilder
    public boolean hasType() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // android.service.NetworkIdentityProtoOrBuilder
    public int getType() {
        return this.type_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setType(int value) {
        this.bitField0_ |= 1;
        this.type_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearType() {
        this.bitField0_ &= -2;
        this.type_ = 0;
    }

    @Override // android.service.NetworkIdentityProtoOrBuilder
    public boolean hasSubscriberId() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // android.service.NetworkIdentityProtoOrBuilder
    public String getSubscriberId() {
        return this.subscriberId_;
    }

    @Override // android.service.NetworkIdentityProtoOrBuilder
    public ByteString getSubscriberIdBytes() {
        return ByteString.copyFromUtf8(this.subscriberId_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setSubscriberId(String value) {
        if (value != null) {
            this.bitField0_ |= 2;
            this.subscriberId_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearSubscriberId() {
        this.bitField0_ &= -3;
        this.subscriberId_ = getDefaultInstance().getSubscriberId();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setSubscriberIdBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 2;
            this.subscriberId_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // android.service.NetworkIdentityProtoOrBuilder
    public boolean hasNetworkId() {
        return (this.bitField0_ & 4) == 4;
    }

    @Override // android.service.NetworkIdentityProtoOrBuilder
    public String getNetworkId() {
        return this.networkId_;
    }

    @Override // android.service.NetworkIdentityProtoOrBuilder
    public ByteString getNetworkIdBytes() {
        return ByteString.copyFromUtf8(this.networkId_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setNetworkId(String value) {
        if (value != null) {
            this.bitField0_ |= 4;
            this.networkId_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearNetworkId() {
        this.bitField0_ &= -5;
        this.networkId_ = getDefaultInstance().getNetworkId();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setNetworkIdBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 4;
            this.networkId_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // android.service.NetworkIdentityProtoOrBuilder
    public boolean hasRoaming() {
        return (this.bitField0_ & 8) == 8;
    }

    @Override // android.service.NetworkIdentityProtoOrBuilder
    public boolean getRoaming() {
        return this.roaming_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setRoaming(boolean value) {
        this.bitField0_ |= 8;
        this.roaming_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearRoaming() {
        this.bitField0_ &= -9;
        this.roaming_ = false;
    }

    @Override // android.service.NetworkIdentityProtoOrBuilder
    public boolean hasMetered() {
        return (this.bitField0_ & 16) == 16;
    }

    @Override // android.service.NetworkIdentityProtoOrBuilder
    public boolean getMetered() {
        return this.metered_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setMetered(boolean value) {
        this.bitField0_ |= 16;
        this.metered_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearMetered() {
        this.bitField0_ &= -17;
        this.metered_ = false;
    }

    @Override // android.service.NetworkIdentityProtoOrBuilder
    public boolean hasDefaultNetwork() {
        return (this.bitField0_ & 32) == 32;
    }

    @Override // android.service.NetworkIdentityProtoOrBuilder
    public boolean getDefaultNetwork() {
        return this.defaultNetwork_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setDefaultNetwork(boolean value) {
        this.bitField0_ |= 32;
        this.defaultNetwork_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearDefaultNetwork() {
        this.bitField0_ &= -33;
        this.defaultNetwork_ = false;
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeInt32(1, this.type_);
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeString(2, getSubscriberId());
        }
        if ((this.bitField0_ & 4) == 4) {
            output.writeString(3, getNetworkId());
        }
        if ((this.bitField0_ & 8) == 8) {
            output.writeBool(4, this.roaming_);
        }
        if ((this.bitField0_ & 16) == 16) {
            output.writeBool(5, this.metered_);
        }
        if ((this.bitField0_ & 32) == 32) {
            output.writeBool(6, this.defaultNetwork_);
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
            size2 = 0 + CodedOutputStream.computeInt32Size(1, this.type_);
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeStringSize(2, getSubscriberId());
        }
        if ((this.bitField0_ & 4) == 4) {
            size2 += CodedOutputStream.computeStringSize(3, getNetworkId());
        }
        if ((this.bitField0_ & 8) == 8) {
            size2 += CodedOutputStream.computeBoolSize(4, this.roaming_);
        }
        if ((this.bitField0_ & 16) == 16) {
            size2 += CodedOutputStream.computeBoolSize(5, this.metered_);
        }
        if ((this.bitField0_ & 32) == 32) {
            size2 += CodedOutputStream.computeBoolSize(6, this.defaultNetwork_);
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static NetworkIdentityProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (NetworkIdentityProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static NetworkIdentityProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (NetworkIdentityProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static NetworkIdentityProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (NetworkIdentityProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static NetworkIdentityProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (NetworkIdentityProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static NetworkIdentityProto parseFrom(InputStream input) throws IOException {
        return (NetworkIdentityProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static NetworkIdentityProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (NetworkIdentityProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static NetworkIdentityProto parseDelimitedFrom(InputStream input) throws IOException {
        return (NetworkIdentityProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static NetworkIdentityProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (NetworkIdentityProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static NetworkIdentityProto parseFrom(CodedInputStream input) throws IOException {
        return (NetworkIdentityProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static NetworkIdentityProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (NetworkIdentityProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(NetworkIdentityProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<NetworkIdentityProto, Builder> implements NetworkIdentityProtoOrBuilder {
        private Builder() {
            super(NetworkIdentityProto.DEFAULT_INSTANCE);
        }

        @Override // android.service.NetworkIdentityProtoOrBuilder
        public boolean hasType() {
            return ((NetworkIdentityProto) this.instance).hasType();
        }

        @Override // android.service.NetworkIdentityProtoOrBuilder
        public int getType() {
            return ((NetworkIdentityProto) this.instance).getType();
        }

        public Builder setType(int value) {
            copyOnWrite();
            ((NetworkIdentityProto) this.instance).setType(value);
            return this;
        }

        public Builder clearType() {
            copyOnWrite();
            ((NetworkIdentityProto) this.instance).clearType();
            return this;
        }

        @Override // android.service.NetworkIdentityProtoOrBuilder
        public boolean hasSubscriberId() {
            return ((NetworkIdentityProto) this.instance).hasSubscriberId();
        }

        @Override // android.service.NetworkIdentityProtoOrBuilder
        public String getSubscriberId() {
            return ((NetworkIdentityProto) this.instance).getSubscriberId();
        }

        @Override // android.service.NetworkIdentityProtoOrBuilder
        public ByteString getSubscriberIdBytes() {
            return ((NetworkIdentityProto) this.instance).getSubscriberIdBytes();
        }

        public Builder setSubscriberId(String value) {
            copyOnWrite();
            ((NetworkIdentityProto) this.instance).setSubscriberId(value);
            return this;
        }

        public Builder clearSubscriberId() {
            copyOnWrite();
            ((NetworkIdentityProto) this.instance).clearSubscriberId();
            return this;
        }

        public Builder setSubscriberIdBytes(ByteString value) {
            copyOnWrite();
            ((NetworkIdentityProto) this.instance).setSubscriberIdBytes(value);
            return this;
        }

        @Override // android.service.NetworkIdentityProtoOrBuilder
        public boolean hasNetworkId() {
            return ((NetworkIdentityProto) this.instance).hasNetworkId();
        }

        @Override // android.service.NetworkIdentityProtoOrBuilder
        public String getNetworkId() {
            return ((NetworkIdentityProto) this.instance).getNetworkId();
        }

        @Override // android.service.NetworkIdentityProtoOrBuilder
        public ByteString getNetworkIdBytes() {
            return ((NetworkIdentityProto) this.instance).getNetworkIdBytes();
        }

        public Builder setNetworkId(String value) {
            copyOnWrite();
            ((NetworkIdentityProto) this.instance).setNetworkId(value);
            return this;
        }

        public Builder clearNetworkId() {
            copyOnWrite();
            ((NetworkIdentityProto) this.instance).clearNetworkId();
            return this;
        }

        public Builder setNetworkIdBytes(ByteString value) {
            copyOnWrite();
            ((NetworkIdentityProto) this.instance).setNetworkIdBytes(value);
            return this;
        }

        @Override // android.service.NetworkIdentityProtoOrBuilder
        public boolean hasRoaming() {
            return ((NetworkIdentityProto) this.instance).hasRoaming();
        }

        @Override // android.service.NetworkIdentityProtoOrBuilder
        public boolean getRoaming() {
            return ((NetworkIdentityProto) this.instance).getRoaming();
        }

        public Builder setRoaming(boolean value) {
            copyOnWrite();
            ((NetworkIdentityProto) this.instance).setRoaming(value);
            return this;
        }

        public Builder clearRoaming() {
            copyOnWrite();
            ((NetworkIdentityProto) this.instance).clearRoaming();
            return this;
        }

        @Override // android.service.NetworkIdentityProtoOrBuilder
        public boolean hasMetered() {
            return ((NetworkIdentityProto) this.instance).hasMetered();
        }

        @Override // android.service.NetworkIdentityProtoOrBuilder
        public boolean getMetered() {
            return ((NetworkIdentityProto) this.instance).getMetered();
        }

        public Builder setMetered(boolean value) {
            copyOnWrite();
            ((NetworkIdentityProto) this.instance).setMetered(value);
            return this;
        }

        public Builder clearMetered() {
            copyOnWrite();
            ((NetworkIdentityProto) this.instance).clearMetered();
            return this;
        }

        @Override // android.service.NetworkIdentityProtoOrBuilder
        public boolean hasDefaultNetwork() {
            return ((NetworkIdentityProto) this.instance).hasDefaultNetwork();
        }

        @Override // android.service.NetworkIdentityProtoOrBuilder
        public boolean getDefaultNetwork() {
            return ((NetworkIdentityProto) this.instance).getDefaultNetwork();
        }

        public Builder setDefaultNetwork(boolean value) {
            copyOnWrite();
            ((NetworkIdentityProto) this.instance).setDefaultNetwork(value);
            return this;
        }

        public Builder clearDefaultNetwork() {
            copyOnWrite();
            ((NetworkIdentityProto) this.instance).clearDefaultNetwork();
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new NetworkIdentityProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                NetworkIdentityProto other = (NetworkIdentityProto) arg1;
                this.type_ = visitor.visitInt(hasType(), this.type_, other.hasType(), other.type_);
                this.subscriberId_ = visitor.visitString(hasSubscriberId(), this.subscriberId_, other.hasSubscriberId(), other.subscriberId_);
                this.networkId_ = visitor.visitString(hasNetworkId(), this.networkId_, other.hasNetworkId(), other.networkId_);
                this.roaming_ = visitor.visitBoolean(hasRoaming(), this.roaming_, other.hasRoaming(), other.roaming_);
                this.metered_ = visitor.visitBoolean(hasMetered(), this.metered_, other.hasMetered(), other.metered_);
                this.defaultNetwork_ = visitor.visitBoolean(hasDefaultNetwork(), this.defaultNetwork_, other.hasDefaultNetwork(), other.defaultNetwork_);
                if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE) {
                    this.bitField0_ |= other.bitField0_;
                }
                return this;
            case MERGE_FROM_STREAM:
                CodedInputStream input = (CodedInputStream) arg0;
                ExtensionRegistryLite extensionRegistryLite = (ExtensionRegistryLite) arg1;
                boolean done = false;
                while (!done) {
                    try {
                        int tag = input.readTag();
                        if (tag == 0) {
                            done = true;
                        } else if (tag == 8) {
                            this.bitField0_ |= 1;
                            this.type_ = input.readInt32();
                        } else if (tag == 18) {
                            String s = input.readString();
                            this.bitField0_ |= 2;
                            this.subscriberId_ = s;
                        } else if (tag == 26) {
                            String s2 = input.readString();
                            this.bitField0_ |= 4;
                            this.networkId_ = s2;
                        } else if (tag == 32) {
                            this.bitField0_ = 8 | this.bitField0_;
                            this.roaming_ = input.readBool();
                        } else if (tag == 40) {
                            this.bitField0_ |= 16;
                            this.metered_ = input.readBool();
                        } else if (tag == 48) {
                            this.bitField0_ |= 32;
                            this.defaultNetwork_ = input.readBool();
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
                    synchronized (NetworkIdentityProto.class) {
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

    public static NetworkIdentityProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<NetworkIdentityProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
