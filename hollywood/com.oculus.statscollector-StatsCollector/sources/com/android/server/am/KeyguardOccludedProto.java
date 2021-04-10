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

public final class KeyguardOccludedProto extends GeneratedMessageLite<KeyguardOccludedProto, Builder> implements KeyguardOccludedProtoOrBuilder {
    private static final KeyguardOccludedProto DEFAULT_INSTANCE = new KeyguardOccludedProto();
    public static final int DISPLAY_ID_FIELD_NUMBER = 1;
    public static final int KEYGUARD_OCCLUDED_FIELD_NUMBER = 2;
    private static volatile Parser<KeyguardOccludedProto> PARSER;
    private int bitField0_;
    private int displayId_ = 0;
    private boolean keyguardOccluded_ = false;

    private KeyguardOccludedProto() {
    }

    @Override // com.android.server.am.KeyguardOccludedProtoOrBuilder
    public boolean hasDisplayId() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // com.android.server.am.KeyguardOccludedProtoOrBuilder
    public int getDisplayId() {
        return this.displayId_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setDisplayId(int value) {
        this.bitField0_ |= 1;
        this.displayId_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearDisplayId() {
        this.bitField0_ &= -2;
        this.displayId_ = 0;
    }

    @Override // com.android.server.am.KeyguardOccludedProtoOrBuilder
    public boolean hasKeyguardOccluded() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // com.android.server.am.KeyguardOccludedProtoOrBuilder
    public boolean getKeyguardOccluded() {
        return this.keyguardOccluded_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setKeyguardOccluded(boolean value) {
        this.bitField0_ |= 2;
        this.keyguardOccluded_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearKeyguardOccluded() {
        this.bitField0_ &= -3;
        this.keyguardOccluded_ = false;
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeInt32(1, this.displayId_);
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeBool(2, this.keyguardOccluded_);
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
            size2 = 0 + CodedOutputStream.computeInt32Size(1, this.displayId_);
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeBoolSize(2, this.keyguardOccluded_);
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static KeyguardOccludedProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (KeyguardOccludedProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static KeyguardOccludedProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (KeyguardOccludedProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static KeyguardOccludedProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (KeyguardOccludedProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static KeyguardOccludedProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (KeyguardOccludedProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static KeyguardOccludedProto parseFrom(InputStream input) throws IOException {
        return (KeyguardOccludedProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static KeyguardOccludedProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (KeyguardOccludedProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static KeyguardOccludedProto parseDelimitedFrom(InputStream input) throws IOException {
        return (KeyguardOccludedProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static KeyguardOccludedProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (KeyguardOccludedProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static KeyguardOccludedProto parseFrom(CodedInputStream input) throws IOException {
        return (KeyguardOccludedProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static KeyguardOccludedProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (KeyguardOccludedProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(KeyguardOccludedProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<KeyguardOccludedProto, Builder> implements KeyguardOccludedProtoOrBuilder {
        private Builder() {
            super(KeyguardOccludedProto.DEFAULT_INSTANCE);
        }

        @Override // com.android.server.am.KeyguardOccludedProtoOrBuilder
        public boolean hasDisplayId() {
            return ((KeyguardOccludedProto) this.instance).hasDisplayId();
        }

        @Override // com.android.server.am.KeyguardOccludedProtoOrBuilder
        public int getDisplayId() {
            return ((KeyguardOccludedProto) this.instance).getDisplayId();
        }

        public Builder setDisplayId(int value) {
            copyOnWrite();
            ((KeyguardOccludedProto) this.instance).setDisplayId(value);
            return this;
        }

        public Builder clearDisplayId() {
            copyOnWrite();
            ((KeyguardOccludedProto) this.instance).clearDisplayId();
            return this;
        }

        @Override // com.android.server.am.KeyguardOccludedProtoOrBuilder
        public boolean hasKeyguardOccluded() {
            return ((KeyguardOccludedProto) this.instance).hasKeyguardOccluded();
        }

        @Override // com.android.server.am.KeyguardOccludedProtoOrBuilder
        public boolean getKeyguardOccluded() {
            return ((KeyguardOccludedProto) this.instance).getKeyguardOccluded();
        }

        public Builder setKeyguardOccluded(boolean value) {
            copyOnWrite();
            ((KeyguardOccludedProto) this.instance).setKeyguardOccluded(value);
            return this;
        }

        public Builder clearKeyguardOccluded() {
            copyOnWrite();
            ((KeyguardOccludedProto) this.instance).clearKeyguardOccluded();
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new KeyguardOccludedProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                KeyguardOccludedProto other = (KeyguardOccludedProto) arg1;
                this.displayId_ = visitor.visitInt(hasDisplayId(), this.displayId_, other.hasDisplayId(), other.displayId_);
                this.keyguardOccluded_ = visitor.visitBoolean(hasKeyguardOccluded(), this.keyguardOccluded_, other.hasKeyguardOccluded(), other.keyguardOccluded_);
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
                            this.displayId_ = input.readInt32();
                        } else if (tag == 16) {
                            this.bitField0_ |= 2;
                            this.keyguardOccluded_ = input.readBool();
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
                    synchronized (KeyguardOccludedProto.class) {
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

    public static KeyguardOccludedProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<KeyguardOccludedProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
