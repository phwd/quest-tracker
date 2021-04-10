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

public final class BroadcastRecordProto extends GeneratedMessageLite<BroadcastRecordProto, Builder> implements BroadcastRecordProtoOrBuilder {
    private static final BroadcastRecordProto DEFAULT_INSTANCE = new BroadcastRecordProto();
    public static final int INTENT_ACTION_FIELD_NUMBER = 2;
    private static volatile Parser<BroadcastRecordProto> PARSER = null;
    public static final int USER_ID_FIELD_NUMBER = 1;
    private int bitField0_;
    private String intentAction_ = "";
    private int userId_ = 0;

    private BroadcastRecordProto() {
    }

    @Override // com.android.server.am.BroadcastRecordProtoOrBuilder
    public boolean hasUserId() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // com.android.server.am.BroadcastRecordProtoOrBuilder
    public int getUserId() {
        return this.userId_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setUserId(int value) {
        this.bitField0_ |= 1;
        this.userId_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearUserId() {
        this.bitField0_ &= -2;
        this.userId_ = 0;
    }

    @Override // com.android.server.am.BroadcastRecordProtoOrBuilder
    public boolean hasIntentAction() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // com.android.server.am.BroadcastRecordProtoOrBuilder
    public String getIntentAction() {
        return this.intentAction_;
    }

    @Override // com.android.server.am.BroadcastRecordProtoOrBuilder
    public ByteString getIntentActionBytes() {
        return ByteString.copyFromUtf8(this.intentAction_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setIntentAction(String value) {
        if (value != null) {
            this.bitField0_ |= 2;
            this.intentAction_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearIntentAction() {
        this.bitField0_ &= -3;
        this.intentAction_ = getDefaultInstance().getIntentAction();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setIntentActionBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 2;
            this.intentAction_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeInt32(1, this.userId_);
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeString(2, getIntentAction());
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
            size2 = 0 + CodedOutputStream.computeInt32Size(1, this.userId_);
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeStringSize(2, getIntentAction());
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static BroadcastRecordProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (BroadcastRecordProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static BroadcastRecordProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (BroadcastRecordProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static BroadcastRecordProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (BroadcastRecordProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static BroadcastRecordProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (BroadcastRecordProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static BroadcastRecordProto parseFrom(InputStream input) throws IOException {
        return (BroadcastRecordProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static BroadcastRecordProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (BroadcastRecordProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static BroadcastRecordProto parseDelimitedFrom(InputStream input) throws IOException {
        return (BroadcastRecordProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static BroadcastRecordProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (BroadcastRecordProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static BroadcastRecordProto parseFrom(CodedInputStream input) throws IOException {
        return (BroadcastRecordProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static BroadcastRecordProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (BroadcastRecordProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(BroadcastRecordProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<BroadcastRecordProto, Builder> implements BroadcastRecordProtoOrBuilder {
        private Builder() {
            super(BroadcastRecordProto.DEFAULT_INSTANCE);
        }

        @Override // com.android.server.am.BroadcastRecordProtoOrBuilder
        public boolean hasUserId() {
            return ((BroadcastRecordProto) this.instance).hasUserId();
        }

        @Override // com.android.server.am.BroadcastRecordProtoOrBuilder
        public int getUserId() {
            return ((BroadcastRecordProto) this.instance).getUserId();
        }

        public Builder setUserId(int value) {
            copyOnWrite();
            ((BroadcastRecordProto) this.instance).setUserId(value);
            return this;
        }

        public Builder clearUserId() {
            copyOnWrite();
            ((BroadcastRecordProto) this.instance).clearUserId();
            return this;
        }

        @Override // com.android.server.am.BroadcastRecordProtoOrBuilder
        public boolean hasIntentAction() {
            return ((BroadcastRecordProto) this.instance).hasIntentAction();
        }

        @Override // com.android.server.am.BroadcastRecordProtoOrBuilder
        public String getIntentAction() {
            return ((BroadcastRecordProto) this.instance).getIntentAction();
        }

        @Override // com.android.server.am.BroadcastRecordProtoOrBuilder
        public ByteString getIntentActionBytes() {
            return ((BroadcastRecordProto) this.instance).getIntentActionBytes();
        }

        public Builder setIntentAction(String value) {
            copyOnWrite();
            ((BroadcastRecordProto) this.instance).setIntentAction(value);
            return this;
        }

        public Builder clearIntentAction() {
            copyOnWrite();
            ((BroadcastRecordProto) this.instance).clearIntentAction();
            return this;
        }

        public Builder setIntentActionBytes(ByteString value) {
            copyOnWrite();
            ((BroadcastRecordProto) this.instance).setIntentActionBytes(value);
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new BroadcastRecordProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                BroadcastRecordProto other = (BroadcastRecordProto) arg1;
                this.userId_ = visitor.visitInt(hasUserId(), this.userId_, other.hasUserId(), other.userId_);
                this.intentAction_ = visitor.visitString(hasIntentAction(), this.intentAction_, other.hasIntentAction(), other.intentAction_);
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
                            this.userId_ = input.readInt32();
                        } else if (tag == 18) {
                            String s = input.readString();
                            this.bitField0_ |= 2;
                            this.intentAction_ = s;
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
                    synchronized (BroadcastRecordProto.class) {
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

    public static BroadcastRecordProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<BroadcastRecordProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
