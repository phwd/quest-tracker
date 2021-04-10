package com.android.server;

import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;

public final class WakeupEventProto extends GeneratedMessageLite<WakeupEventProto, Builder> implements WakeupEventProtoOrBuilder {
    public static final int ACTION_FIELD_NUMBER = 2;
    private static final WakeupEventProto DEFAULT_INSTANCE = new WakeupEventProto();
    private static volatile Parser<WakeupEventProto> PARSER = null;
    public static final int UID_FIELD_NUMBER = 1;
    public static final int WHEN_FIELD_NUMBER = 3;
    private String action_ = "";
    private int bitField0_;
    private int uid_ = 0;
    private long when_ = 0;

    private WakeupEventProto() {
    }

    @Override // com.android.server.WakeupEventProtoOrBuilder
    public boolean hasUid() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // com.android.server.WakeupEventProtoOrBuilder
    public int getUid() {
        return this.uid_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setUid(int value) {
        this.bitField0_ |= 1;
        this.uid_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearUid() {
        this.bitField0_ &= -2;
        this.uid_ = 0;
    }

    @Override // com.android.server.WakeupEventProtoOrBuilder
    public boolean hasAction() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // com.android.server.WakeupEventProtoOrBuilder
    public String getAction() {
        return this.action_;
    }

    @Override // com.android.server.WakeupEventProtoOrBuilder
    public ByteString getActionBytes() {
        return ByteString.copyFromUtf8(this.action_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setAction(String value) {
        if (value != null) {
            this.bitField0_ |= 2;
            this.action_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearAction() {
        this.bitField0_ &= -3;
        this.action_ = getDefaultInstance().getAction();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setActionBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 2;
            this.action_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // com.android.server.WakeupEventProtoOrBuilder
    public boolean hasWhen() {
        return (this.bitField0_ & 4) == 4;
    }

    @Override // com.android.server.WakeupEventProtoOrBuilder
    public long getWhen() {
        return this.when_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setWhen(long value) {
        this.bitField0_ |= 4;
        this.when_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearWhen() {
        this.bitField0_ &= -5;
        this.when_ = 0;
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeInt32(1, this.uid_);
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeString(2, getAction());
        }
        if ((this.bitField0_ & 4) == 4) {
            output.writeInt64(3, this.when_);
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
            size2 = 0 + CodedOutputStream.computeInt32Size(1, this.uid_);
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeStringSize(2, getAction());
        }
        if ((this.bitField0_ & 4) == 4) {
            size2 += CodedOutputStream.computeInt64Size(3, this.when_);
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static WakeupEventProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (WakeupEventProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static WakeupEventProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (WakeupEventProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static WakeupEventProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (WakeupEventProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static WakeupEventProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (WakeupEventProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static WakeupEventProto parseFrom(InputStream input) throws IOException {
        return (WakeupEventProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static WakeupEventProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (WakeupEventProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static WakeupEventProto parseDelimitedFrom(InputStream input) throws IOException {
        return (WakeupEventProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static WakeupEventProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (WakeupEventProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static WakeupEventProto parseFrom(CodedInputStream input) throws IOException {
        return (WakeupEventProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static WakeupEventProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (WakeupEventProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(WakeupEventProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<WakeupEventProto, Builder> implements WakeupEventProtoOrBuilder {
        private Builder() {
            super(WakeupEventProto.DEFAULT_INSTANCE);
        }

        @Override // com.android.server.WakeupEventProtoOrBuilder
        public boolean hasUid() {
            return ((WakeupEventProto) this.instance).hasUid();
        }

        @Override // com.android.server.WakeupEventProtoOrBuilder
        public int getUid() {
            return ((WakeupEventProto) this.instance).getUid();
        }

        public Builder setUid(int value) {
            copyOnWrite();
            ((WakeupEventProto) this.instance).setUid(value);
            return this;
        }

        public Builder clearUid() {
            copyOnWrite();
            ((WakeupEventProto) this.instance).clearUid();
            return this;
        }

        @Override // com.android.server.WakeupEventProtoOrBuilder
        public boolean hasAction() {
            return ((WakeupEventProto) this.instance).hasAction();
        }

        @Override // com.android.server.WakeupEventProtoOrBuilder
        public String getAction() {
            return ((WakeupEventProto) this.instance).getAction();
        }

        @Override // com.android.server.WakeupEventProtoOrBuilder
        public ByteString getActionBytes() {
            return ((WakeupEventProto) this.instance).getActionBytes();
        }

        public Builder setAction(String value) {
            copyOnWrite();
            ((WakeupEventProto) this.instance).setAction(value);
            return this;
        }

        public Builder clearAction() {
            copyOnWrite();
            ((WakeupEventProto) this.instance).clearAction();
            return this;
        }

        public Builder setActionBytes(ByteString value) {
            copyOnWrite();
            ((WakeupEventProto) this.instance).setActionBytes(value);
            return this;
        }

        @Override // com.android.server.WakeupEventProtoOrBuilder
        public boolean hasWhen() {
            return ((WakeupEventProto) this.instance).hasWhen();
        }

        @Override // com.android.server.WakeupEventProtoOrBuilder
        public long getWhen() {
            return ((WakeupEventProto) this.instance).getWhen();
        }

        public Builder setWhen(long value) {
            copyOnWrite();
            ((WakeupEventProto) this.instance).setWhen(value);
            return this;
        }

        public Builder clearWhen() {
            copyOnWrite();
            ((WakeupEventProto) this.instance).clearWhen();
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new WakeupEventProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                WakeupEventProto other = (WakeupEventProto) arg1;
                this.uid_ = visitor.visitInt(hasUid(), this.uid_, other.hasUid(), other.uid_);
                this.action_ = visitor.visitString(hasAction(), this.action_, other.hasAction(), other.action_);
                this.when_ = visitor.visitLong(hasWhen(), this.when_, other.hasWhen(), other.when_);
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
                            this.uid_ = input.readInt32();
                        } else if (tag == 18) {
                            String s = input.readString();
                            this.bitField0_ |= 2;
                            this.action_ = s;
                        } else if (tag == 24) {
                            this.bitField0_ |= 4;
                            this.when_ = input.readInt64();
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
                    synchronized (WakeupEventProto.class) {
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

    public static WakeupEventProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<WakeupEventProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
