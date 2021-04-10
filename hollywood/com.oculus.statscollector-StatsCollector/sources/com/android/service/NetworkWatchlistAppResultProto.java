package com.android.service;

import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;

public final class NetworkWatchlistAppResultProto extends GeneratedMessageLite<NetworkWatchlistAppResultProto, Builder> implements NetworkWatchlistAppResultProtoOrBuilder {
    public static final int APP_DIGEST_FIELD_NUMBER = 1;
    private static final NetworkWatchlistAppResultProto DEFAULT_INSTANCE = new NetworkWatchlistAppResultProto();
    public static final int ENCODED_RESULT_FIELD_NUMBER = 2;
    private static volatile Parser<NetworkWatchlistAppResultProto> PARSER;
    private String appDigest_ = "";
    private int bitField0_;
    private boolean encodedResult_ = false;

    private NetworkWatchlistAppResultProto() {
    }

    @Override // com.android.service.NetworkWatchlistAppResultProtoOrBuilder
    public boolean hasAppDigest() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // com.android.service.NetworkWatchlistAppResultProtoOrBuilder
    public String getAppDigest() {
        return this.appDigest_;
    }

    @Override // com.android.service.NetworkWatchlistAppResultProtoOrBuilder
    public ByteString getAppDigestBytes() {
        return ByteString.copyFromUtf8(this.appDigest_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setAppDigest(String value) {
        if (value != null) {
            this.bitField0_ |= 1;
            this.appDigest_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearAppDigest() {
        this.bitField0_ &= -2;
        this.appDigest_ = getDefaultInstance().getAppDigest();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setAppDigestBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 1;
            this.appDigest_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // com.android.service.NetworkWatchlistAppResultProtoOrBuilder
    public boolean hasEncodedResult() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // com.android.service.NetworkWatchlistAppResultProtoOrBuilder
    public boolean getEncodedResult() {
        return this.encodedResult_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setEncodedResult(boolean value) {
        this.bitField0_ |= 2;
        this.encodedResult_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearEncodedResult() {
        this.bitField0_ &= -3;
        this.encodedResult_ = false;
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeString(1, getAppDigest());
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeBool(2, this.encodedResult_);
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
            size2 = 0 + CodedOutputStream.computeStringSize(1, getAppDigest());
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeBoolSize(2, this.encodedResult_);
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static NetworkWatchlistAppResultProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (NetworkWatchlistAppResultProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static NetworkWatchlistAppResultProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (NetworkWatchlistAppResultProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static NetworkWatchlistAppResultProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (NetworkWatchlistAppResultProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static NetworkWatchlistAppResultProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (NetworkWatchlistAppResultProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static NetworkWatchlistAppResultProto parseFrom(InputStream input) throws IOException {
        return (NetworkWatchlistAppResultProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static NetworkWatchlistAppResultProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (NetworkWatchlistAppResultProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static NetworkWatchlistAppResultProto parseDelimitedFrom(InputStream input) throws IOException {
        return (NetworkWatchlistAppResultProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static NetworkWatchlistAppResultProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (NetworkWatchlistAppResultProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static NetworkWatchlistAppResultProto parseFrom(CodedInputStream input) throws IOException {
        return (NetworkWatchlistAppResultProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static NetworkWatchlistAppResultProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (NetworkWatchlistAppResultProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(NetworkWatchlistAppResultProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<NetworkWatchlistAppResultProto, Builder> implements NetworkWatchlistAppResultProtoOrBuilder {
        private Builder() {
            super(NetworkWatchlistAppResultProto.DEFAULT_INSTANCE);
        }

        @Override // com.android.service.NetworkWatchlistAppResultProtoOrBuilder
        public boolean hasAppDigest() {
            return ((NetworkWatchlistAppResultProto) this.instance).hasAppDigest();
        }

        @Override // com.android.service.NetworkWatchlistAppResultProtoOrBuilder
        public String getAppDigest() {
            return ((NetworkWatchlistAppResultProto) this.instance).getAppDigest();
        }

        @Override // com.android.service.NetworkWatchlistAppResultProtoOrBuilder
        public ByteString getAppDigestBytes() {
            return ((NetworkWatchlistAppResultProto) this.instance).getAppDigestBytes();
        }

        public Builder setAppDigest(String value) {
            copyOnWrite();
            ((NetworkWatchlistAppResultProto) this.instance).setAppDigest(value);
            return this;
        }

        public Builder clearAppDigest() {
            copyOnWrite();
            ((NetworkWatchlistAppResultProto) this.instance).clearAppDigest();
            return this;
        }

        public Builder setAppDigestBytes(ByteString value) {
            copyOnWrite();
            ((NetworkWatchlistAppResultProto) this.instance).setAppDigestBytes(value);
            return this;
        }

        @Override // com.android.service.NetworkWatchlistAppResultProtoOrBuilder
        public boolean hasEncodedResult() {
            return ((NetworkWatchlistAppResultProto) this.instance).hasEncodedResult();
        }

        @Override // com.android.service.NetworkWatchlistAppResultProtoOrBuilder
        public boolean getEncodedResult() {
            return ((NetworkWatchlistAppResultProto) this.instance).getEncodedResult();
        }

        public Builder setEncodedResult(boolean value) {
            copyOnWrite();
            ((NetworkWatchlistAppResultProto) this.instance).setEncodedResult(value);
            return this;
        }

        public Builder clearEncodedResult() {
            copyOnWrite();
            ((NetworkWatchlistAppResultProto) this.instance).clearEncodedResult();
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new NetworkWatchlistAppResultProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                NetworkWatchlistAppResultProto other = (NetworkWatchlistAppResultProto) arg1;
                this.appDigest_ = visitor.visitString(hasAppDigest(), this.appDigest_, other.hasAppDigest(), other.appDigest_);
                this.encodedResult_ = visitor.visitBoolean(hasEncodedResult(), this.encodedResult_, other.hasEncodedResult(), other.encodedResult_);
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
                            this.appDigest_ = s;
                        } else if (tag == 16) {
                            this.bitField0_ |= 2;
                            this.encodedResult_ = input.readBool();
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
                    synchronized (NetworkWatchlistAppResultProto.class) {
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

    public static NetworkWatchlistAppResultProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<NetworkWatchlistAppResultProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
