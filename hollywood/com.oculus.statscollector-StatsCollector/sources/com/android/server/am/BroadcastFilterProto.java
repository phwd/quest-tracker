package com.android.server.am;

import android.content.IntentFilterProto;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;

public final class BroadcastFilterProto extends GeneratedMessageLite<BroadcastFilterProto, Builder> implements BroadcastFilterProtoOrBuilder {
    private static final BroadcastFilterProto DEFAULT_INSTANCE = new BroadcastFilterProto();
    public static final int HEX_HASH_FIELD_NUMBER = 3;
    public static final int INTENT_FILTER_FIELD_NUMBER = 1;
    public static final int OWNING_USER_ID_FIELD_NUMBER = 4;
    private static volatile Parser<BroadcastFilterProto> PARSER = null;
    public static final int REQUIRED_PERMISSION_FIELD_NUMBER = 2;
    private int bitField0_;
    private String hexHash_ = "";
    private IntentFilterProto intentFilter_;
    private int owningUserId_ = 0;
    private String requiredPermission_ = "";

    private BroadcastFilterProto() {
    }

    @Override // com.android.server.am.BroadcastFilterProtoOrBuilder
    public boolean hasIntentFilter() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // com.android.server.am.BroadcastFilterProtoOrBuilder
    public IntentFilterProto getIntentFilter() {
        IntentFilterProto intentFilterProto = this.intentFilter_;
        return intentFilterProto == null ? IntentFilterProto.getDefaultInstance() : intentFilterProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setIntentFilter(IntentFilterProto value) {
        if (value != null) {
            this.intentFilter_ = value;
            this.bitField0_ |= 1;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setIntentFilter(IntentFilterProto.Builder builderForValue) {
        this.intentFilter_ = (IntentFilterProto) builderForValue.build();
        this.bitField0_ |= 1;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeIntentFilter(IntentFilterProto value) {
        IntentFilterProto intentFilterProto = this.intentFilter_;
        if (intentFilterProto == null || intentFilterProto == IntentFilterProto.getDefaultInstance()) {
            this.intentFilter_ = value;
        } else {
            this.intentFilter_ = (IntentFilterProto) ((IntentFilterProto.Builder) IntentFilterProto.newBuilder(this.intentFilter_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 1;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearIntentFilter() {
        this.intentFilter_ = null;
        this.bitField0_ &= -2;
    }

    @Override // com.android.server.am.BroadcastFilterProtoOrBuilder
    public boolean hasRequiredPermission() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // com.android.server.am.BroadcastFilterProtoOrBuilder
    public String getRequiredPermission() {
        return this.requiredPermission_;
    }

    @Override // com.android.server.am.BroadcastFilterProtoOrBuilder
    public ByteString getRequiredPermissionBytes() {
        return ByteString.copyFromUtf8(this.requiredPermission_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setRequiredPermission(String value) {
        if (value != null) {
            this.bitField0_ |= 2;
            this.requiredPermission_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearRequiredPermission() {
        this.bitField0_ &= -3;
        this.requiredPermission_ = getDefaultInstance().getRequiredPermission();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setRequiredPermissionBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 2;
            this.requiredPermission_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // com.android.server.am.BroadcastFilterProtoOrBuilder
    public boolean hasHexHash() {
        return (this.bitField0_ & 4) == 4;
    }

    @Override // com.android.server.am.BroadcastFilterProtoOrBuilder
    public String getHexHash() {
        return this.hexHash_;
    }

    @Override // com.android.server.am.BroadcastFilterProtoOrBuilder
    public ByteString getHexHashBytes() {
        return ByteString.copyFromUtf8(this.hexHash_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setHexHash(String value) {
        if (value != null) {
            this.bitField0_ |= 4;
            this.hexHash_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearHexHash() {
        this.bitField0_ &= -5;
        this.hexHash_ = getDefaultInstance().getHexHash();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setHexHashBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 4;
            this.hexHash_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // com.android.server.am.BroadcastFilterProtoOrBuilder
    public boolean hasOwningUserId() {
        return (this.bitField0_ & 8) == 8;
    }

    @Override // com.android.server.am.BroadcastFilterProtoOrBuilder
    public int getOwningUserId() {
        return this.owningUserId_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setOwningUserId(int value) {
        this.bitField0_ |= 8;
        this.owningUserId_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearOwningUserId() {
        this.bitField0_ &= -9;
        this.owningUserId_ = 0;
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeMessage(1, getIntentFilter());
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeString(2, getRequiredPermission());
        }
        if ((this.bitField0_ & 4) == 4) {
            output.writeString(3, getHexHash());
        }
        if ((this.bitField0_ & 8) == 8) {
            output.writeInt32(4, this.owningUserId_);
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
            size2 = 0 + CodedOutputStream.computeMessageSize(1, getIntentFilter());
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeStringSize(2, getRequiredPermission());
        }
        if ((this.bitField0_ & 4) == 4) {
            size2 += CodedOutputStream.computeStringSize(3, getHexHash());
        }
        if ((this.bitField0_ & 8) == 8) {
            size2 += CodedOutputStream.computeInt32Size(4, this.owningUserId_);
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static BroadcastFilterProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (BroadcastFilterProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static BroadcastFilterProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (BroadcastFilterProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static BroadcastFilterProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (BroadcastFilterProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static BroadcastFilterProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (BroadcastFilterProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static BroadcastFilterProto parseFrom(InputStream input) throws IOException {
        return (BroadcastFilterProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static BroadcastFilterProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (BroadcastFilterProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static BroadcastFilterProto parseDelimitedFrom(InputStream input) throws IOException {
        return (BroadcastFilterProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static BroadcastFilterProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (BroadcastFilterProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static BroadcastFilterProto parseFrom(CodedInputStream input) throws IOException {
        return (BroadcastFilterProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static BroadcastFilterProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (BroadcastFilterProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(BroadcastFilterProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<BroadcastFilterProto, Builder> implements BroadcastFilterProtoOrBuilder {
        private Builder() {
            super(BroadcastFilterProto.DEFAULT_INSTANCE);
        }

        @Override // com.android.server.am.BroadcastFilterProtoOrBuilder
        public boolean hasIntentFilter() {
            return ((BroadcastFilterProto) this.instance).hasIntentFilter();
        }

        @Override // com.android.server.am.BroadcastFilterProtoOrBuilder
        public IntentFilterProto getIntentFilter() {
            return ((BroadcastFilterProto) this.instance).getIntentFilter();
        }

        public Builder setIntentFilter(IntentFilterProto value) {
            copyOnWrite();
            ((BroadcastFilterProto) this.instance).setIntentFilter((BroadcastFilterProto) value);
            return this;
        }

        public Builder setIntentFilter(IntentFilterProto.Builder builderForValue) {
            copyOnWrite();
            ((BroadcastFilterProto) this.instance).setIntentFilter((BroadcastFilterProto) builderForValue);
            return this;
        }

        public Builder mergeIntentFilter(IntentFilterProto value) {
            copyOnWrite();
            ((BroadcastFilterProto) this.instance).mergeIntentFilter(value);
            return this;
        }

        public Builder clearIntentFilter() {
            copyOnWrite();
            ((BroadcastFilterProto) this.instance).clearIntentFilter();
            return this;
        }

        @Override // com.android.server.am.BroadcastFilterProtoOrBuilder
        public boolean hasRequiredPermission() {
            return ((BroadcastFilterProto) this.instance).hasRequiredPermission();
        }

        @Override // com.android.server.am.BroadcastFilterProtoOrBuilder
        public String getRequiredPermission() {
            return ((BroadcastFilterProto) this.instance).getRequiredPermission();
        }

        @Override // com.android.server.am.BroadcastFilterProtoOrBuilder
        public ByteString getRequiredPermissionBytes() {
            return ((BroadcastFilterProto) this.instance).getRequiredPermissionBytes();
        }

        public Builder setRequiredPermission(String value) {
            copyOnWrite();
            ((BroadcastFilterProto) this.instance).setRequiredPermission(value);
            return this;
        }

        public Builder clearRequiredPermission() {
            copyOnWrite();
            ((BroadcastFilterProto) this.instance).clearRequiredPermission();
            return this;
        }

        public Builder setRequiredPermissionBytes(ByteString value) {
            copyOnWrite();
            ((BroadcastFilterProto) this.instance).setRequiredPermissionBytes(value);
            return this;
        }

        @Override // com.android.server.am.BroadcastFilterProtoOrBuilder
        public boolean hasHexHash() {
            return ((BroadcastFilterProto) this.instance).hasHexHash();
        }

        @Override // com.android.server.am.BroadcastFilterProtoOrBuilder
        public String getHexHash() {
            return ((BroadcastFilterProto) this.instance).getHexHash();
        }

        @Override // com.android.server.am.BroadcastFilterProtoOrBuilder
        public ByteString getHexHashBytes() {
            return ((BroadcastFilterProto) this.instance).getHexHashBytes();
        }

        public Builder setHexHash(String value) {
            copyOnWrite();
            ((BroadcastFilterProto) this.instance).setHexHash(value);
            return this;
        }

        public Builder clearHexHash() {
            copyOnWrite();
            ((BroadcastFilterProto) this.instance).clearHexHash();
            return this;
        }

        public Builder setHexHashBytes(ByteString value) {
            copyOnWrite();
            ((BroadcastFilterProto) this.instance).setHexHashBytes(value);
            return this;
        }

        @Override // com.android.server.am.BroadcastFilterProtoOrBuilder
        public boolean hasOwningUserId() {
            return ((BroadcastFilterProto) this.instance).hasOwningUserId();
        }

        @Override // com.android.server.am.BroadcastFilterProtoOrBuilder
        public int getOwningUserId() {
            return ((BroadcastFilterProto) this.instance).getOwningUserId();
        }

        public Builder setOwningUserId(int value) {
            copyOnWrite();
            ((BroadcastFilterProto) this.instance).setOwningUserId(value);
            return this;
        }

        public Builder clearOwningUserId() {
            copyOnWrite();
            ((BroadcastFilterProto) this.instance).clearOwningUserId();
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new BroadcastFilterProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                BroadcastFilterProto other = (BroadcastFilterProto) arg1;
                this.intentFilter_ = (IntentFilterProto) visitor.visitMessage(this.intentFilter_, other.intentFilter_);
                this.requiredPermission_ = visitor.visitString(hasRequiredPermission(), this.requiredPermission_, other.hasRequiredPermission(), other.requiredPermission_);
                this.hexHash_ = visitor.visitString(hasHexHash(), this.hexHash_, other.hasHexHash(), other.hexHash_);
                this.owningUserId_ = visitor.visitInt(hasOwningUserId(), this.owningUserId_, other.hasOwningUserId(), other.owningUserId_);
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
                        } else if (tag == 10) {
                            IntentFilterProto.Builder subBuilder = null;
                            if ((this.bitField0_ & 1) == 1) {
                                subBuilder = (IntentFilterProto.Builder) this.intentFilter_.toBuilder();
                            }
                            this.intentFilter_ = (IntentFilterProto) input.readMessage(IntentFilterProto.parser(), extensionRegistry);
                            if (subBuilder != null) {
                                subBuilder.mergeFrom((GeneratedMessageLite) this.intentFilter_);
                                this.intentFilter_ = (IntentFilterProto) subBuilder.buildPartial();
                            }
                            this.bitField0_ |= 1;
                        } else if (tag == 18) {
                            String s = input.readString();
                            this.bitField0_ |= 2;
                            this.requiredPermission_ = s;
                        } else if (tag == 26) {
                            String s2 = input.readString();
                            this.bitField0_ |= 4;
                            this.hexHash_ = s2;
                        } else if (tag == 32) {
                            this.bitField0_ |= 8;
                            this.owningUserId_ = input.readInt32();
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
                    synchronized (BroadcastFilterProto.class) {
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

    public static BroadcastFilterProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<BroadcastFilterProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
