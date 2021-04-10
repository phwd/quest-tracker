package android.service;

import android.service.NetworkIdentitySetProto;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;

public final class NetworkInterfaceProto extends GeneratedMessageLite<NetworkInterfaceProto, Builder> implements NetworkInterfaceProtoOrBuilder {
    private static final NetworkInterfaceProto DEFAULT_INSTANCE = new NetworkInterfaceProto();
    public static final int IDENTITIES_FIELD_NUMBER = 2;
    public static final int INTERFACE_FIELD_NUMBER = 1;
    private static volatile Parser<NetworkInterfaceProto> PARSER;
    private int bitField0_;
    private NetworkIdentitySetProto identities_;
    private String interface_ = "";

    private NetworkInterfaceProto() {
    }

    @Override // android.service.NetworkInterfaceProtoOrBuilder
    public boolean hasInterface() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // android.service.NetworkInterfaceProtoOrBuilder
    public String getInterface() {
        return this.interface_;
    }

    @Override // android.service.NetworkInterfaceProtoOrBuilder
    public ByteString getInterfaceBytes() {
        return ByteString.copyFromUtf8(this.interface_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setInterface(String value) {
        if (value != null) {
            this.bitField0_ |= 1;
            this.interface_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearInterface() {
        this.bitField0_ &= -2;
        this.interface_ = getDefaultInstance().getInterface();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setInterfaceBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 1;
            this.interface_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // android.service.NetworkInterfaceProtoOrBuilder
    public boolean hasIdentities() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // android.service.NetworkInterfaceProtoOrBuilder
    public NetworkIdentitySetProto getIdentities() {
        NetworkIdentitySetProto networkIdentitySetProto = this.identities_;
        return networkIdentitySetProto == null ? NetworkIdentitySetProto.getDefaultInstance() : networkIdentitySetProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setIdentities(NetworkIdentitySetProto value) {
        if (value != null) {
            this.identities_ = value;
            this.bitField0_ |= 2;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setIdentities(NetworkIdentitySetProto.Builder builderForValue) {
        this.identities_ = (NetworkIdentitySetProto) builderForValue.build();
        this.bitField0_ |= 2;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeIdentities(NetworkIdentitySetProto value) {
        NetworkIdentitySetProto networkIdentitySetProto = this.identities_;
        if (networkIdentitySetProto == null || networkIdentitySetProto == NetworkIdentitySetProto.getDefaultInstance()) {
            this.identities_ = value;
        } else {
            this.identities_ = (NetworkIdentitySetProto) ((NetworkIdentitySetProto.Builder) NetworkIdentitySetProto.newBuilder(this.identities_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 2;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearIdentities() {
        this.identities_ = null;
        this.bitField0_ &= -3;
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeString(1, getInterface());
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeMessage(2, getIdentities());
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
            size2 = 0 + CodedOutputStream.computeStringSize(1, getInterface());
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeMessageSize(2, getIdentities());
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static NetworkInterfaceProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (NetworkInterfaceProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static NetworkInterfaceProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (NetworkInterfaceProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static NetworkInterfaceProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (NetworkInterfaceProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static NetworkInterfaceProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (NetworkInterfaceProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static NetworkInterfaceProto parseFrom(InputStream input) throws IOException {
        return (NetworkInterfaceProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static NetworkInterfaceProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (NetworkInterfaceProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static NetworkInterfaceProto parseDelimitedFrom(InputStream input) throws IOException {
        return (NetworkInterfaceProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static NetworkInterfaceProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (NetworkInterfaceProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static NetworkInterfaceProto parseFrom(CodedInputStream input) throws IOException {
        return (NetworkInterfaceProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static NetworkInterfaceProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (NetworkInterfaceProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(NetworkInterfaceProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<NetworkInterfaceProto, Builder> implements NetworkInterfaceProtoOrBuilder {
        private Builder() {
            super(NetworkInterfaceProto.DEFAULT_INSTANCE);
        }

        @Override // android.service.NetworkInterfaceProtoOrBuilder
        public boolean hasInterface() {
            return ((NetworkInterfaceProto) this.instance).hasInterface();
        }

        @Override // android.service.NetworkInterfaceProtoOrBuilder
        public String getInterface() {
            return ((NetworkInterfaceProto) this.instance).getInterface();
        }

        @Override // android.service.NetworkInterfaceProtoOrBuilder
        public ByteString getInterfaceBytes() {
            return ((NetworkInterfaceProto) this.instance).getInterfaceBytes();
        }

        public Builder setInterface(String value) {
            copyOnWrite();
            ((NetworkInterfaceProto) this.instance).setInterface(value);
            return this;
        }

        public Builder clearInterface() {
            copyOnWrite();
            ((NetworkInterfaceProto) this.instance).clearInterface();
            return this;
        }

        public Builder setInterfaceBytes(ByteString value) {
            copyOnWrite();
            ((NetworkInterfaceProto) this.instance).setInterfaceBytes(value);
            return this;
        }

        @Override // android.service.NetworkInterfaceProtoOrBuilder
        public boolean hasIdentities() {
            return ((NetworkInterfaceProto) this.instance).hasIdentities();
        }

        @Override // android.service.NetworkInterfaceProtoOrBuilder
        public NetworkIdentitySetProto getIdentities() {
            return ((NetworkInterfaceProto) this.instance).getIdentities();
        }

        public Builder setIdentities(NetworkIdentitySetProto value) {
            copyOnWrite();
            ((NetworkInterfaceProto) this.instance).setIdentities((NetworkInterfaceProto) value);
            return this;
        }

        public Builder setIdentities(NetworkIdentitySetProto.Builder builderForValue) {
            copyOnWrite();
            ((NetworkInterfaceProto) this.instance).setIdentities((NetworkInterfaceProto) builderForValue);
            return this;
        }

        public Builder mergeIdentities(NetworkIdentitySetProto value) {
            copyOnWrite();
            ((NetworkInterfaceProto) this.instance).mergeIdentities(value);
            return this;
        }

        public Builder clearIdentities() {
            copyOnWrite();
            ((NetworkInterfaceProto) this.instance).clearIdentities();
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new NetworkInterfaceProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                NetworkInterfaceProto other = (NetworkInterfaceProto) arg1;
                this.interface_ = visitor.visitString(hasInterface(), this.interface_, other.hasInterface(), other.interface_);
                this.identities_ = (NetworkIdentitySetProto) visitor.visitMessage(this.identities_, other.identities_);
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
                            String s = input.readString();
                            this.bitField0_ |= 1;
                            this.interface_ = s;
                        } else if (tag == 18) {
                            NetworkIdentitySetProto.Builder subBuilder = null;
                            if ((this.bitField0_ & 2) == 2) {
                                subBuilder = (NetworkIdentitySetProto.Builder) this.identities_.toBuilder();
                            }
                            this.identities_ = (NetworkIdentitySetProto) input.readMessage(NetworkIdentitySetProto.parser(), extensionRegistry);
                            if (subBuilder != null) {
                                subBuilder.mergeFrom((GeneratedMessageLite) this.identities_);
                                this.identities_ = (NetworkIdentitySetProto) subBuilder.buildPartial();
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
                    synchronized (NetworkInterfaceProto.class) {
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

    public static NetworkInterfaceProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<NetworkInterfaceProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
