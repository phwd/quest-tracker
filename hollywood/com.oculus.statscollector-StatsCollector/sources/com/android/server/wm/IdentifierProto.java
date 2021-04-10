package com.android.server.wm;

import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;

public final class IdentifierProto extends GeneratedMessageLite<IdentifierProto, Builder> implements IdentifierProtoOrBuilder {
    private static final IdentifierProto DEFAULT_INSTANCE = new IdentifierProto();
    public static final int HASH_CODE_FIELD_NUMBER = 1;
    private static volatile Parser<IdentifierProto> PARSER = null;
    public static final int TITLE_FIELD_NUMBER = 3;
    public static final int USER_ID_FIELD_NUMBER = 2;
    private int bitField0_;
    private int hashCode_ = 0;
    private String title_ = "";
    private int userId_ = 0;

    private IdentifierProto() {
    }

    @Override // com.android.server.wm.IdentifierProtoOrBuilder
    public boolean hasHashCode() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // com.android.server.wm.IdentifierProtoOrBuilder
    public int getHashCode() {
        return this.hashCode_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setHashCode(int value) {
        this.bitField0_ |= 1;
        this.hashCode_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearHashCode() {
        this.bitField0_ &= -2;
        this.hashCode_ = 0;
    }

    @Override // com.android.server.wm.IdentifierProtoOrBuilder
    public boolean hasUserId() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // com.android.server.wm.IdentifierProtoOrBuilder
    public int getUserId() {
        return this.userId_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setUserId(int value) {
        this.bitField0_ |= 2;
        this.userId_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearUserId() {
        this.bitField0_ &= -3;
        this.userId_ = 0;
    }

    @Override // com.android.server.wm.IdentifierProtoOrBuilder
    public boolean hasTitle() {
        return (this.bitField0_ & 4) == 4;
    }

    @Override // com.android.server.wm.IdentifierProtoOrBuilder
    public String getTitle() {
        return this.title_;
    }

    @Override // com.android.server.wm.IdentifierProtoOrBuilder
    public ByteString getTitleBytes() {
        return ByteString.copyFromUtf8(this.title_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setTitle(String value) {
        if (value != null) {
            this.bitField0_ |= 4;
            this.title_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearTitle() {
        this.bitField0_ &= -5;
        this.title_ = getDefaultInstance().getTitle();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setTitleBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 4;
            this.title_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeInt32(1, this.hashCode_);
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeInt32(2, this.userId_);
        }
        if ((this.bitField0_ & 4) == 4) {
            output.writeString(3, getTitle());
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
            size2 = 0 + CodedOutputStream.computeInt32Size(1, this.hashCode_);
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeInt32Size(2, this.userId_);
        }
        if ((this.bitField0_ & 4) == 4) {
            size2 += CodedOutputStream.computeStringSize(3, getTitle());
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static IdentifierProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (IdentifierProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static IdentifierProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (IdentifierProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static IdentifierProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (IdentifierProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static IdentifierProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (IdentifierProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static IdentifierProto parseFrom(InputStream input) throws IOException {
        return (IdentifierProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static IdentifierProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (IdentifierProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static IdentifierProto parseDelimitedFrom(InputStream input) throws IOException {
        return (IdentifierProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static IdentifierProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (IdentifierProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static IdentifierProto parseFrom(CodedInputStream input) throws IOException {
        return (IdentifierProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static IdentifierProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (IdentifierProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(IdentifierProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<IdentifierProto, Builder> implements IdentifierProtoOrBuilder {
        private Builder() {
            super(IdentifierProto.DEFAULT_INSTANCE);
        }

        @Override // com.android.server.wm.IdentifierProtoOrBuilder
        public boolean hasHashCode() {
            return ((IdentifierProto) this.instance).hasHashCode();
        }

        @Override // com.android.server.wm.IdentifierProtoOrBuilder
        public int getHashCode() {
            return ((IdentifierProto) this.instance).getHashCode();
        }

        public Builder setHashCode(int value) {
            copyOnWrite();
            ((IdentifierProto) this.instance).setHashCode(value);
            return this;
        }

        public Builder clearHashCode() {
            copyOnWrite();
            ((IdentifierProto) this.instance).clearHashCode();
            return this;
        }

        @Override // com.android.server.wm.IdentifierProtoOrBuilder
        public boolean hasUserId() {
            return ((IdentifierProto) this.instance).hasUserId();
        }

        @Override // com.android.server.wm.IdentifierProtoOrBuilder
        public int getUserId() {
            return ((IdentifierProto) this.instance).getUserId();
        }

        public Builder setUserId(int value) {
            copyOnWrite();
            ((IdentifierProto) this.instance).setUserId(value);
            return this;
        }

        public Builder clearUserId() {
            copyOnWrite();
            ((IdentifierProto) this.instance).clearUserId();
            return this;
        }

        @Override // com.android.server.wm.IdentifierProtoOrBuilder
        public boolean hasTitle() {
            return ((IdentifierProto) this.instance).hasTitle();
        }

        @Override // com.android.server.wm.IdentifierProtoOrBuilder
        public String getTitle() {
            return ((IdentifierProto) this.instance).getTitle();
        }

        @Override // com.android.server.wm.IdentifierProtoOrBuilder
        public ByteString getTitleBytes() {
            return ((IdentifierProto) this.instance).getTitleBytes();
        }

        public Builder setTitle(String value) {
            copyOnWrite();
            ((IdentifierProto) this.instance).setTitle(value);
            return this;
        }

        public Builder clearTitle() {
            copyOnWrite();
            ((IdentifierProto) this.instance).clearTitle();
            return this;
        }

        public Builder setTitleBytes(ByteString value) {
            copyOnWrite();
            ((IdentifierProto) this.instance).setTitleBytes(value);
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new IdentifierProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                IdentifierProto other = (IdentifierProto) arg1;
                this.hashCode_ = visitor.visitInt(hasHashCode(), this.hashCode_, other.hasHashCode(), other.hashCode_);
                this.userId_ = visitor.visitInt(hasUserId(), this.userId_, other.hasUserId(), other.userId_);
                this.title_ = visitor.visitString(hasTitle(), this.title_, other.hasTitle(), other.title_);
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
                            this.hashCode_ = input.readInt32();
                        } else if (tag == 16) {
                            this.bitField0_ |= 2;
                            this.userId_ = input.readInt32();
                        } else if (tag == 26) {
                            String s = input.readString();
                            this.bitField0_ |= 4;
                            this.title_ = s;
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
                    synchronized (IdentifierProto.class) {
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

    public static IdentifierProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<IdentifierProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
