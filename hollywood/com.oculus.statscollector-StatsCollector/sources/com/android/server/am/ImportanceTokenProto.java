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

public final class ImportanceTokenProto extends GeneratedMessageLite<ImportanceTokenProto, Builder> implements ImportanceTokenProtoOrBuilder {
    private static final ImportanceTokenProto DEFAULT_INSTANCE = new ImportanceTokenProto();
    private static volatile Parser<ImportanceTokenProto> PARSER = null;
    public static final int PID_FIELD_NUMBER = 1;
    public static final int REASON_FIELD_NUMBER = 3;
    public static final int TOKEN_FIELD_NUMBER = 2;
    private int bitField0_;
    private int pid_ = 0;
    private String reason_ = "";
    private String token_ = "";

    private ImportanceTokenProto() {
    }

    @Override // com.android.server.am.ImportanceTokenProtoOrBuilder
    public boolean hasPid() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // com.android.server.am.ImportanceTokenProtoOrBuilder
    public int getPid() {
        return this.pid_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPid(int value) {
        this.bitField0_ |= 1;
        this.pid_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearPid() {
        this.bitField0_ &= -2;
        this.pid_ = 0;
    }

    @Override // com.android.server.am.ImportanceTokenProtoOrBuilder
    public boolean hasToken() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // com.android.server.am.ImportanceTokenProtoOrBuilder
    public String getToken() {
        return this.token_;
    }

    @Override // com.android.server.am.ImportanceTokenProtoOrBuilder
    public ByteString getTokenBytes() {
        return ByteString.copyFromUtf8(this.token_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setToken(String value) {
        if (value != null) {
            this.bitField0_ |= 2;
            this.token_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearToken() {
        this.bitField0_ &= -3;
        this.token_ = getDefaultInstance().getToken();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setTokenBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 2;
            this.token_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // com.android.server.am.ImportanceTokenProtoOrBuilder
    public boolean hasReason() {
        return (this.bitField0_ & 4) == 4;
    }

    @Override // com.android.server.am.ImportanceTokenProtoOrBuilder
    public String getReason() {
        return this.reason_;
    }

    @Override // com.android.server.am.ImportanceTokenProtoOrBuilder
    public ByteString getReasonBytes() {
        return ByteString.copyFromUtf8(this.reason_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setReason(String value) {
        if (value != null) {
            this.bitField0_ |= 4;
            this.reason_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearReason() {
        this.bitField0_ &= -5;
        this.reason_ = getDefaultInstance().getReason();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setReasonBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 4;
            this.reason_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeInt32(1, this.pid_);
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeString(2, getToken());
        }
        if ((this.bitField0_ & 4) == 4) {
            output.writeString(3, getReason());
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
            size2 = 0 + CodedOutputStream.computeInt32Size(1, this.pid_);
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeStringSize(2, getToken());
        }
        if ((this.bitField0_ & 4) == 4) {
            size2 += CodedOutputStream.computeStringSize(3, getReason());
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static ImportanceTokenProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (ImportanceTokenProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static ImportanceTokenProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (ImportanceTokenProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static ImportanceTokenProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (ImportanceTokenProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static ImportanceTokenProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (ImportanceTokenProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static ImportanceTokenProto parseFrom(InputStream input) throws IOException {
        return (ImportanceTokenProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static ImportanceTokenProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (ImportanceTokenProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static ImportanceTokenProto parseDelimitedFrom(InputStream input) throws IOException {
        return (ImportanceTokenProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static ImportanceTokenProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (ImportanceTokenProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static ImportanceTokenProto parseFrom(CodedInputStream input) throws IOException {
        return (ImportanceTokenProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static ImportanceTokenProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (ImportanceTokenProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(ImportanceTokenProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<ImportanceTokenProto, Builder> implements ImportanceTokenProtoOrBuilder {
        private Builder() {
            super(ImportanceTokenProto.DEFAULT_INSTANCE);
        }

        @Override // com.android.server.am.ImportanceTokenProtoOrBuilder
        public boolean hasPid() {
            return ((ImportanceTokenProto) this.instance).hasPid();
        }

        @Override // com.android.server.am.ImportanceTokenProtoOrBuilder
        public int getPid() {
            return ((ImportanceTokenProto) this.instance).getPid();
        }

        public Builder setPid(int value) {
            copyOnWrite();
            ((ImportanceTokenProto) this.instance).setPid(value);
            return this;
        }

        public Builder clearPid() {
            copyOnWrite();
            ((ImportanceTokenProto) this.instance).clearPid();
            return this;
        }

        @Override // com.android.server.am.ImportanceTokenProtoOrBuilder
        public boolean hasToken() {
            return ((ImportanceTokenProto) this.instance).hasToken();
        }

        @Override // com.android.server.am.ImportanceTokenProtoOrBuilder
        public String getToken() {
            return ((ImportanceTokenProto) this.instance).getToken();
        }

        @Override // com.android.server.am.ImportanceTokenProtoOrBuilder
        public ByteString getTokenBytes() {
            return ((ImportanceTokenProto) this.instance).getTokenBytes();
        }

        public Builder setToken(String value) {
            copyOnWrite();
            ((ImportanceTokenProto) this.instance).setToken(value);
            return this;
        }

        public Builder clearToken() {
            copyOnWrite();
            ((ImportanceTokenProto) this.instance).clearToken();
            return this;
        }

        public Builder setTokenBytes(ByteString value) {
            copyOnWrite();
            ((ImportanceTokenProto) this.instance).setTokenBytes(value);
            return this;
        }

        @Override // com.android.server.am.ImportanceTokenProtoOrBuilder
        public boolean hasReason() {
            return ((ImportanceTokenProto) this.instance).hasReason();
        }

        @Override // com.android.server.am.ImportanceTokenProtoOrBuilder
        public String getReason() {
            return ((ImportanceTokenProto) this.instance).getReason();
        }

        @Override // com.android.server.am.ImportanceTokenProtoOrBuilder
        public ByteString getReasonBytes() {
            return ((ImportanceTokenProto) this.instance).getReasonBytes();
        }

        public Builder setReason(String value) {
            copyOnWrite();
            ((ImportanceTokenProto) this.instance).setReason(value);
            return this;
        }

        public Builder clearReason() {
            copyOnWrite();
            ((ImportanceTokenProto) this.instance).clearReason();
            return this;
        }

        public Builder setReasonBytes(ByteString value) {
            copyOnWrite();
            ((ImportanceTokenProto) this.instance).setReasonBytes(value);
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new ImportanceTokenProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                ImportanceTokenProto other = (ImportanceTokenProto) arg1;
                this.pid_ = visitor.visitInt(hasPid(), this.pid_, other.hasPid(), other.pid_);
                this.token_ = visitor.visitString(hasToken(), this.token_, other.hasToken(), other.token_);
                this.reason_ = visitor.visitString(hasReason(), this.reason_, other.hasReason(), other.reason_);
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
                            this.pid_ = input.readInt32();
                        } else if (tag == 18) {
                            String s = input.readString();
                            this.bitField0_ |= 2;
                            this.token_ = s;
                        } else if (tag == 26) {
                            String s2 = input.readString();
                            this.bitField0_ |= 4;
                            this.reason_ = s2;
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
                    synchronized (ImportanceTokenProto.class) {
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

    public static ImportanceTokenProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<ImportanceTokenProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
