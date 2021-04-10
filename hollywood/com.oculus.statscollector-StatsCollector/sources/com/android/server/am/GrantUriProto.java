package com.android.server.am;

import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;

public final class GrantUriProto extends GeneratedMessageLite<GrantUriProto, Builder> implements GrantUriProtoOrBuilder {
    private static final GrantUriProto DEFAULT_INSTANCE = new GrantUriProto();
    private static volatile Parser<GrantUriProto> PARSER = null;
    public static final int SOURCE_USER_ID_FIELD_NUMBER = 1;
    public static final int URI_FIELD_NUMBER = 2;
    private int bitField0_;
    private int sourceUserId_ = 0;
    private String uri_ = "";

    private GrantUriProto() {
    }

    @Override // com.android.server.am.GrantUriProtoOrBuilder
    public boolean hasSourceUserId() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // com.android.server.am.GrantUriProtoOrBuilder
    public int getSourceUserId() {
        return this.sourceUserId_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setSourceUserId(int value) {
        this.bitField0_ |= 1;
        this.sourceUserId_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearSourceUserId() {
        this.bitField0_ &= -2;
        this.sourceUserId_ = 0;
    }

    @Override // com.android.server.am.GrantUriProtoOrBuilder
    public boolean hasUri() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // com.android.server.am.GrantUriProtoOrBuilder
    public String getUri() {
        return this.uri_;
    }

    @Override // com.android.server.am.GrantUriProtoOrBuilder
    public ByteString getUriBytes() {
        return ByteString.copyFromUtf8(this.uri_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setUri(String value) {
        if (value != null) {
            this.bitField0_ |= 2;
            this.uri_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearUri() {
        this.bitField0_ &= -3;
        this.uri_ = getDefaultInstance().getUri();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setUriBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 2;
            this.uri_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeInt32(1, this.sourceUserId_);
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeString(2, getUri());
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
            size2 = 0 + CodedOutputStream.computeInt32Size(1, this.sourceUserId_);
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeStringSize(2, getUri());
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static GrantUriProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (GrantUriProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static GrantUriProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (GrantUriProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static GrantUriProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (GrantUriProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static GrantUriProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (GrantUriProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static GrantUriProto parseFrom(InputStream input) throws IOException {
        return (GrantUriProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static GrantUriProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (GrantUriProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static GrantUriProto parseDelimitedFrom(InputStream input) throws IOException {
        return (GrantUriProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static GrantUriProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (GrantUriProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static GrantUriProto parseFrom(CodedInputStream input) throws IOException {
        return (GrantUriProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static GrantUriProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (GrantUriProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(GrantUriProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<GrantUriProto, Builder> implements GrantUriProtoOrBuilder {
        private Builder() {
            super(GrantUriProto.DEFAULT_INSTANCE);
        }

        @Override // com.android.server.am.GrantUriProtoOrBuilder
        public boolean hasSourceUserId() {
            return ((GrantUriProto) this.instance).hasSourceUserId();
        }

        @Override // com.android.server.am.GrantUriProtoOrBuilder
        public int getSourceUserId() {
            return ((GrantUriProto) this.instance).getSourceUserId();
        }

        public Builder setSourceUserId(int value) {
            copyOnWrite();
            ((GrantUriProto) this.instance).setSourceUserId(value);
            return this;
        }

        public Builder clearSourceUserId() {
            copyOnWrite();
            ((GrantUriProto) this.instance).clearSourceUserId();
            return this;
        }

        @Override // com.android.server.am.GrantUriProtoOrBuilder
        public boolean hasUri() {
            return ((GrantUriProto) this.instance).hasUri();
        }

        @Override // com.android.server.am.GrantUriProtoOrBuilder
        public String getUri() {
            return ((GrantUriProto) this.instance).getUri();
        }

        @Override // com.android.server.am.GrantUriProtoOrBuilder
        public ByteString getUriBytes() {
            return ((GrantUriProto) this.instance).getUriBytes();
        }

        public Builder setUri(String value) {
            copyOnWrite();
            ((GrantUriProto) this.instance).setUri(value);
            return this;
        }

        public Builder clearUri() {
            copyOnWrite();
            ((GrantUriProto) this.instance).clearUri();
            return this;
        }

        public Builder setUriBytes(ByteString value) {
            copyOnWrite();
            ((GrantUriProto) this.instance).setUriBytes(value);
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new GrantUriProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                GrantUriProto other = (GrantUriProto) arg1;
                this.sourceUserId_ = visitor.visitInt(hasSourceUserId(), this.sourceUserId_, other.hasSourceUserId(), other.sourceUserId_);
                this.uri_ = visitor.visitString(hasUri(), this.uri_, other.hasUri(), other.uri_);
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
                            this.sourceUserId_ = input.readInt32();
                        } else if (tag == 18) {
                            String s = input.readString();
                            this.bitField0_ |= 2;
                            this.uri_ = s;
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
                    synchronized (GrantUriProto.class) {
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

    public static GrantUriProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<GrantUriProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
