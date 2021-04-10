package android.content;

import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;

public final class AuthorityEntryProto extends GeneratedMessageLite<AuthorityEntryProto, Builder> implements AuthorityEntryProtoOrBuilder {
    private static final AuthorityEntryProto DEFAULT_INSTANCE = new AuthorityEntryProto();
    public static final int HOST_FIELD_NUMBER = 1;
    private static volatile Parser<AuthorityEntryProto> PARSER = null;
    public static final int PORT_FIELD_NUMBER = 3;
    public static final int WILD_FIELD_NUMBER = 2;
    private int bitField0_;
    private String host_ = "";
    private int port_ = 0;
    private boolean wild_ = false;

    private AuthorityEntryProto() {
    }

    @Override // android.content.AuthorityEntryProtoOrBuilder
    public boolean hasHost() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // android.content.AuthorityEntryProtoOrBuilder
    public String getHost() {
        return this.host_;
    }

    @Override // android.content.AuthorityEntryProtoOrBuilder
    public ByteString getHostBytes() {
        return ByteString.copyFromUtf8(this.host_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setHost(String value) {
        if (value != null) {
            this.bitField0_ |= 1;
            this.host_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearHost() {
        this.bitField0_ &= -2;
        this.host_ = getDefaultInstance().getHost();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setHostBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 1;
            this.host_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // android.content.AuthorityEntryProtoOrBuilder
    public boolean hasWild() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // android.content.AuthorityEntryProtoOrBuilder
    public boolean getWild() {
        return this.wild_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setWild(boolean value) {
        this.bitField0_ |= 2;
        this.wild_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearWild() {
        this.bitField0_ &= -3;
        this.wild_ = false;
    }

    @Override // android.content.AuthorityEntryProtoOrBuilder
    public boolean hasPort() {
        return (this.bitField0_ & 4) == 4;
    }

    @Override // android.content.AuthorityEntryProtoOrBuilder
    public int getPort() {
        return this.port_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPort(int value) {
        this.bitField0_ |= 4;
        this.port_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearPort() {
        this.bitField0_ &= -5;
        this.port_ = 0;
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeString(1, getHost());
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeBool(2, this.wild_);
        }
        if ((this.bitField0_ & 4) == 4) {
            output.writeInt32(3, this.port_);
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
            size2 = 0 + CodedOutputStream.computeStringSize(1, getHost());
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeBoolSize(2, this.wild_);
        }
        if ((this.bitField0_ & 4) == 4) {
            size2 += CodedOutputStream.computeInt32Size(3, this.port_);
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static AuthorityEntryProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (AuthorityEntryProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static AuthorityEntryProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (AuthorityEntryProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static AuthorityEntryProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (AuthorityEntryProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static AuthorityEntryProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (AuthorityEntryProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static AuthorityEntryProto parseFrom(InputStream input) throws IOException {
        return (AuthorityEntryProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static AuthorityEntryProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (AuthorityEntryProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static AuthorityEntryProto parseDelimitedFrom(InputStream input) throws IOException {
        return (AuthorityEntryProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static AuthorityEntryProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (AuthorityEntryProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static AuthorityEntryProto parseFrom(CodedInputStream input) throws IOException {
        return (AuthorityEntryProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static AuthorityEntryProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (AuthorityEntryProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(AuthorityEntryProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<AuthorityEntryProto, Builder> implements AuthorityEntryProtoOrBuilder {
        private Builder() {
            super(AuthorityEntryProto.DEFAULT_INSTANCE);
        }

        @Override // android.content.AuthorityEntryProtoOrBuilder
        public boolean hasHost() {
            return ((AuthorityEntryProto) this.instance).hasHost();
        }

        @Override // android.content.AuthorityEntryProtoOrBuilder
        public String getHost() {
            return ((AuthorityEntryProto) this.instance).getHost();
        }

        @Override // android.content.AuthorityEntryProtoOrBuilder
        public ByteString getHostBytes() {
            return ((AuthorityEntryProto) this.instance).getHostBytes();
        }

        public Builder setHost(String value) {
            copyOnWrite();
            ((AuthorityEntryProto) this.instance).setHost(value);
            return this;
        }

        public Builder clearHost() {
            copyOnWrite();
            ((AuthorityEntryProto) this.instance).clearHost();
            return this;
        }

        public Builder setHostBytes(ByteString value) {
            copyOnWrite();
            ((AuthorityEntryProto) this.instance).setHostBytes(value);
            return this;
        }

        @Override // android.content.AuthorityEntryProtoOrBuilder
        public boolean hasWild() {
            return ((AuthorityEntryProto) this.instance).hasWild();
        }

        @Override // android.content.AuthorityEntryProtoOrBuilder
        public boolean getWild() {
            return ((AuthorityEntryProto) this.instance).getWild();
        }

        public Builder setWild(boolean value) {
            copyOnWrite();
            ((AuthorityEntryProto) this.instance).setWild(value);
            return this;
        }

        public Builder clearWild() {
            copyOnWrite();
            ((AuthorityEntryProto) this.instance).clearWild();
            return this;
        }

        @Override // android.content.AuthorityEntryProtoOrBuilder
        public boolean hasPort() {
            return ((AuthorityEntryProto) this.instance).hasPort();
        }

        @Override // android.content.AuthorityEntryProtoOrBuilder
        public int getPort() {
            return ((AuthorityEntryProto) this.instance).getPort();
        }

        public Builder setPort(int value) {
            copyOnWrite();
            ((AuthorityEntryProto) this.instance).setPort(value);
            return this;
        }

        public Builder clearPort() {
            copyOnWrite();
            ((AuthorityEntryProto) this.instance).clearPort();
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new AuthorityEntryProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                AuthorityEntryProto other = (AuthorityEntryProto) arg1;
                this.host_ = visitor.visitString(hasHost(), this.host_, other.hasHost(), other.host_);
                this.wild_ = visitor.visitBoolean(hasWild(), this.wild_, other.hasWild(), other.wild_);
                this.port_ = visitor.visitInt(hasPort(), this.port_, other.hasPort(), other.port_);
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
                        } else if (tag == 10) {
                            String s = input.readString();
                            this.bitField0_ |= 1;
                            this.host_ = s;
                        } else if (tag == 16) {
                            this.bitField0_ |= 2;
                            this.wild_ = input.readBool();
                        } else if (tag == 24) {
                            this.bitField0_ |= 4;
                            this.port_ = input.readInt32();
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
                    synchronized (AuthorityEntryProto.class) {
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

    public static AuthorityEntryProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<AuthorityEntryProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
