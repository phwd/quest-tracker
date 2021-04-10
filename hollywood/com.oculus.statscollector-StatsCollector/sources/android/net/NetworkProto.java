package android.net;

import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;

public final class NetworkProto extends GeneratedMessageLite<NetworkProto, Builder> implements NetworkProtoOrBuilder {
    private static final NetworkProto DEFAULT_INSTANCE = new NetworkProto();
    public static final int NET_ID_FIELD_NUMBER = 1;
    private static volatile Parser<NetworkProto> PARSER;
    private int bitField0_;
    private int netId_ = 0;

    private NetworkProto() {
    }

    @Override // android.net.NetworkProtoOrBuilder
    public boolean hasNetId() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // android.net.NetworkProtoOrBuilder
    public int getNetId() {
        return this.netId_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setNetId(int value) {
        this.bitField0_ |= 1;
        this.netId_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearNetId() {
        this.bitField0_ &= -2;
        this.netId_ = 0;
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeInt32(1, this.netId_);
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
            size2 = 0 + CodedOutputStream.computeInt32Size(1, this.netId_);
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static NetworkProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (NetworkProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static NetworkProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (NetworkProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static NetworkProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (NetworkProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static NetworkProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (NetworkProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static NetworkProto parseFrom(InputStream input) throws IOException {
        return (NetworkProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static NetworkProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (NetworkProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static NetworkProto parseDelimitedFrom(InputStream input) throws IOException {
        return (NetworkProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static NetworkProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (NetworkProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static NetworkProto parseFrom(CodedInputStream input) throws IOException {
        return (NetworkProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static NetworkProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (NetworkProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(NetworkProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<NetworkProto, Builder> implements NetworkProtoOrBuilder {
        private Builder() {
            super(NetworkProto.DEFAULT_INSTANCE);
        }

        @Override // android.net.NetworkProtoOrBuilder
        public boolean hasNetId() {
            return ((NetworkProto) this.instance).hasNetId();
        }

        @Override // android.net.NetworkProtoOrBuilder
        public int getNetId() {
            return ((NetworkProto) this.instance).getNetId();
        }

        public Builder setNetId(int value) {
            copyOnWrite();
            ((NetworkProto) this.instance).setNetId(value);
            return this;
        }

        public Builder clearNetId() {
            copyOnWrite();
            ((NetworkProto) this.instance).clearNetId();
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new NetworkProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                NetworkProto other = (NetworkProto) arg1;
                this.netId_ = visitor.visitInt(hasNetId(), this.netId_, other.hasNetId(), other.netId_);
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
                            this.netId_ = input.readInt32();
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
                    synchronized (NetworkProto.class) {
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

    public static NetworkProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<NetworkProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
