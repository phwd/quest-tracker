package android.net;

import android.net.NetworkCapabilitiesProto;
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

public final class NetworkRequestProto extends GeneratedMessageLite<NetworkRequestProto, Builder> implements NetworkRequestProtoOrBuilder {
    private static final NetworkRequestProto DEFAULT_INSTANCE = new NetworkRequestProto();
    public static final int LEGACY_TYPE_FIELD_NUMBER = 3;
    public static final int NETWORK_CAPABILITIES_FIELD_NUMBER = 4;
    private static volatile Parser<NetworkRequestProto> PARSER = null;
    public static final int REQUEST_ID_FIELD_NUMBER = 2;
    public static final int TYPE_FIELD_NUMBER = 1;
    private int bitField0_;
    private int legacyType_ = 0;
    private NetworkCapabilitiesProto networkCapabilities_;
    private int requestId_ = 0;
    private int type_ = 0;

    private NetworkRequestProto() {
    }

    public enum Type implements Internal.EnumLite {
        TYPE_UNKNOWN(0),
        TYPE_NONE(1),
        TYPE_LISTEN(2),
        TYPE_TRACK_DEFAULT(3),
        TYPE_REQUEST(4),
        TYPE_BACKGROUND_REQUEST(5);
        
        public static final int TYPE_BACKGROUND_REQUEST_VALUE = 5;
        public static final int TYPE_LISTEN_VALUE = 2;
        public static final int TYPE_NONE_VALUE = 1;
        public static final int TYPE_REQUEST_VALUE = 4;
        public static final int TYPE_TRACK_DEFAULT_VALUE = 3;
        public static final int TYPE_UNKNOWN_VALUE = 0;
        private static final Internal.EnumLiteMap<Type> internalValueMap = new Internal.EnumLiteMap<Type>() {
            /* class android.net.NetworkRequestProto.Type.AnonymousClass1 */

            @Override // com.google.protobuf.Internal.EnumLiteMap
            public Type findValueByNumber(int number) {
                return Type.forNumber(number);
            }
        };
        private final int value;

        @Override // com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        @Deprecated
        public static Type valueOf(int value2) {
            return forNumber(value2);
        }

        public static Type forNumber(int value2) {
            if (value2 == 0) {
                return TYPE_UNKNOWN;
            }
            if (value2 == 1) {
                return TYPE_NONE;
            }
            if (value2 == 2) {
                return TYPE_LISTEN;
            }
            if (value2 == 3) {
                return TYPE_TRACK_DEFAULT;
            }
            if (value2 == 4) {
                return TYPE_REQUEST;
            }
            if (value2 != 5) {
                return null;
            }
            return TYPE_BACKGROUND_REQUEST;
        }

        public static Internal.EnumLiteMap<Type> internalGetValueMap() {
            return internalValueMap;
        }

        private Type(int value2) {
            this.value = value2;
        }
    }

    @Override // android.net.NetworkRequestProtoOrBuilder
    public boolean hasType() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // android.net.NetworkRequestProtoOrBuilder
    public Type getType() {
        Type result = Type.forNumber(this.type_);
        return result == null ? Type.TYPE_UNKNOWN : result;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setType(Type value) {
        if (value != null) {
            this.bitField0_ |= 1;
            this.type_ = value.getNumber();
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearType() {
        this.bitField0_ &= -2;
        this.type_ = 0;
    }

    @Override // android.net.NetworkRequestProtoOrBuilder
    public boolean hasRequestId() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // android.net.NetworkRequestProtoOrBuilder
    public int getRequestId() {
        return this.requestId_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setRequestId(int value) {
        this.bitField0_ |= 2;
        this.requestId_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearRequestId() {
        this.bitField0_ &= -3;
        this.requestId_ = 0;
    }

    @Override // android.net.NetworkRequestProtoOrBuilder
    public boolean hasLegacyType() {
        return (this.bitField0_ & 4) == 4;
    }

    @Override // android.net.NetworkRequestProtoOrBuilder
    public int getLegacyType() {
        return this.legacyType_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setLegacyType(int value) {
        this.bitField0_ |= 4;
        this.legacyType_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearLegacyType() {
        this.bitField0_ &= -5;
        this.legacyType_ = 0;
    }

    @Override // android.net.NetworkRequestProtoOrBuilder
    public boolean hasNetworkCapabilities() {
        return (this.bitField0_ & 8) == 8;
    }

    @Override // android.net.NetworkRequestProtoOrBuilder
    public NetworkCapabilitiesProto getNetworkCapabilities() {
        NetworkCapabilitiesProto networkCapabilitiesProto = this.networkCapabilities_;
        return networkCapabilitiesProto == null ? NetworkCapabilitiesProto.getDefaultInstance() : networkCapabilitiesProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setNetworkCapabilities(NetworkCapabilitiesProto value) {
        if (value != null) {
            this.networkCapabilities_ = value;
            this.bitField0_ |= 8;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setNetworkCapabilities(NetworkCapabilitiesProto.Builder builderForValue) {
        this.networkCapabilities_ = (NetworkCapabilitiesProto) builderForValue.build();
        this.bitField0_ |= 8;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeNetworkCapabilities(NetworkCapabilitiesProto value) {
        NetworkCapabilitiesProto networkCapabilitiesProto = this.networkCapabilities_;
        if (networkCapabilitiesProto == null || networkCapabilitiesProto == NetworkCapabilitiesProto.getDefaultInstance()) {
            this.networkCapabilities_ = value;
        } else {
            this.networkCapabilities_ = (NetworkCapabilitiesProto) ((NetworkCapabilitiesProto.Builder) NetworkCapabilitiesProto.newBuilder(this.networkCapabilities_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 8;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearNetworkCapabilities() {
        this.networkCapabilities_ = null;
        this.bitField0_ &= -9;
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeEnum(1, this.type_);
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeInt32(2, this.requestId_);
        }
        if ((this.bitField0_ & 4) == 4) {
            output.writeInt32(3, this.legacyType_);
        }
        if ((this.bitField0_ & 8) == 8) {
            output.writeMessage(4, getNetworkCapabilities());
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
            size2 = 0 + CodedOutputStream.computeEnumSize(1, this.type_);
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeInt32Size(2, this.requestId_);
        }
        if ((this.bitField0_ & 4) == 4) {
            size2 += CodedOutputStream.computeInt32Size(3, this.legacyType_);
        }
        if ((this.bitField0_ & 8) == 8) {
            size2 += CodedOutputStream.computeMessageSize(4, getNetworkCapabilities());
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static NetworkRequestProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (NetworkRequestProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static NetworkRequestProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (NetworkRequestProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static NetworkRequestProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (NetworkRequestProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static NetworkRequestProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (NetworkRequestProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static NetworkRequestProto parseFrom(InputStream input) throws IOException {
        return (NetworkRequestProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static NetworkRequestProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (NetworkRequestProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static NetworkRequestProto parseDelimitedFrom(InputStream input) throws IOException {
        return (NetworkRequestProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static NetworkRequestProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (NetworkRequestProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static NetworkRequestProto parseFrom(CodedInputStream input) throws IOException {
        return (NetworkRequestProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static NetworkRequestProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (NetworkRequestProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(NetworkRequestProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<NetworkRequestProto, Builder> implements NetworkRequestProtoOrBuilder {
        private Builder() {
            super(NetworkRequestProto.DEFAULT_INSTANCE);
        }

        @Override // android.net.NetworkRequestProtoOrBuilder
        public boolean hasType() {
            return ((NetworkRequestProto) this.instance).hasType();
        }

        @Override // android.net.NetworkRequestProtoOrBuilder
        public Type getType() {
            return ((NetworkRequestProto) this.instance).getType();
        }

        public Builder setType(Type value) {
            copyOnWrite();
            ((NetworkRequestProto) this.instance).setType(value);
            return this;
        }

        public Builder clearType() {
            copyOnWrite();
            ((NetworkRequestProto) this.instance).clearType();
            return this;
        }

        @Override // android.net.NetworkRequestProtoOrBuilder
        public boolean hasRequestId() {
            return ((NetworkRequestProto) this.instance).hasRequestId();
        }

        @Override // android.net.NetworkRequestProtoOrBuilder
        public int getRequestId() {
            return ((NetworkRequestProto) this.instance).getRequestId();
        }

        public Builder setRequestId(int value) {
            copyOnWrite();
            ((NetworkRequestProto) this.instance).setRequestId(value);
            return this;
        }

        public Builder clearRequestId() {
            copyOnWrite();
            ((NetworkRequestProto) this.instance).clearRequestId();
            return this;
        }

        @Override // android.net.NetworkRequestProtoOrBuilder
        public boolean hasLegacyType() {
            return ((NetworkRequestProto) this.instance).hasLegacyType();
        }

        @Override // android.net.NetworkRequestProtoOrBuilder
        public int getLegacyType() {
            return ((NetworkRequestProto) this.instance).getLegacyType();
        }

        public Builder setLegacyType(int value) {
            copyOnWrite();
            ((NetworkRequestProto) this.instance).setLegacyType(value);
            return this;
        }

        public Builder clearLegacyType() {
            copyOnWrite();
            ((NetworkRequestProto) this.instance).clearLegacyType();
            return this;
        }

        @Override // android.net.NetworkRequestProtoOrBuilder
        public boolean hasNetworkCapabilities() {
            return ((NetworkRequestProto) this.instance).hasNetworkCapabilities();
        }

        @Override // android.net.NetworkRequestProtoOrBuilder
        public NetworkCapabilitiesProto getNetworkCapabilities() {
            return ((NetworkRequestProto) this.instance).getNetworkCapabilities();
        }

        public Builder setNetworkCapabilities(NetworkCapabilitiesProto value) {
            copyOnWrite();
            ((NetworkRequestProto) this.instance).setNetworkCapabilities((NetworkRequestProto) value);
            return this;
        }

        public Builder setNetworkCapabilities(NetworkCapabilitiesProto.Builder builderForValue) {
            copyOnWrite();
            ((NetworkRequestProto) this.instance).setNetworkCapabilities((NetworkRequestProto) builderForValue);
            return this;
        }

        public Builder mergeNetworkCapabilities(NetworkCapabilitiesProto value) {
            copyOnWrite();
            ((NetworkRequestProto) this.instance).mergeNetworkCapabilities(value);
            return this;
        }

        public Builder clearNetworkCapabilities() {
            copyOnWrite();
            ((NetworkRequestProto) this.instance).clearNetworkCapabilities();
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new NetworkRequestProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                NetworkRequestProto other = (NetworkRequestProto) arg1;
                this.type_ = visitor.visitInt(hasType(), this.type_, other.hasType(), other.type_);
                this.requestId_ = visitor.visitInt(hasRequestId(), this.requestId_, other.hasRequestId(), other.requestId_);
                this.legacyType_ = visitor.visitInt(hasLegacyType(), this.legacyType_, other.hasLegacyType(), other.legacyType_);
                this.networkCapabilities_ = (NetworkCapabilitiesProto) visitor.visitMessage(this.networkCapabilities_, other.networkCapabilities_);
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
                        } else if (tag == 8) {
                            int rawValue = input.readEnum();
                            if (Type.forNumber(rawValue) == null) {
                                super.mergeVarintField(1, rawValue);
                            } else {
                                this.bitField0_ = 1 | this.bitField0_;
                                this.type_ = rawValue;
                            }
                        } else if (tag == 16) {
                            this.bitField0_ |= 2;
                            this.requestId_ = input.readInt32();
                        } else if (tag == 24) {
                            this.bitField0_ |= 4;
                            this.legacyType_ = input.readInt32();
                        } else if (tag == 34) {
                            NetworkCapabilitiesProto.Builder subBuilder = null;
                            if ((this.bitField0_ & 8) == 8) {
                                subBuilder = (NetworkCapabilitiesProto.Builder) this.networkCapabilities_.toBuilder();
                            }
                            this.networkCapabilities_ = (NetworkCapabilitiesProto) input.readMessage(NetworkCapabilitiesProto.parser(), extensionRegistry);
                            if (subBuilder != null) {
                                subBuilder.mergeFrom((GeneratedMessageLite) this.networkCapabilities_);
                                this.networkCapabilities_ = (NetworkCapabilitiesProto) subBuilder.buildPartial();
                            }
                            this.bitField0_ = 8 | this.bitField0_;
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
                    synchronized (NetworkRequestProto.class) {
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

    public static NetworkRequestProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<NetworkRequestProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
