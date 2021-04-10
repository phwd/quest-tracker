package com.android.server.power;

import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;

public final class SuspendBlockerProto extends GeneratedMessageLite<SuspendBlockerProto, Builder> implements SuspendBlockerProtoOrBuilder {
    private static final SuspendBlockerProto DEFAULT_INSTANCE = new SuspendBlockerProto();
    public static final int NAME_FIELD_NUMBER = 1;
    private static volatile Parser<SuspendBlockerProto> PARSER = null;
    public static final int REFERENCE_COUNT_FIELD_NUMBER = 2;
    private int bitField0_;
    private String name_ = "";
    private int referenceCount_ = 0;

    private SuspendBlockerProto() {
    }

    @Override // com.android.server.power.SuspendBlockerProtoOrBuilder
    public boolean hasName() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // com.android.server.power.SuspendBlockerProtoOrBuilder
    public String getName() {
        return this.name_;
    }

    @Override // com.android.server.power.SuspendBlockerProtoOrBuilder
    public ByteString getNameBytes() {
        return ByteString.copyFromUtf8(this.name_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setName(String value) {
        if (value != null) {
            this.bitField0_ |= 1;
            this.name_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearName() {
        this.bitField0_ &= -2;
        this.name_ = getDefaultInstance().getName();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setNameBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 1;
            this.name_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // com.android.server.power.SuspendBlockerProtoOrBuilder
    public boolean hasReferenceCount() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // com.android.server.power.SuspendBlockerProtoOrBuilder
    public int getReferenceCount() {
        return this.referenceCount_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setReferenceCount(int value) {
        this.bitField0_ |= 2;
        this.referenceCount_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearReferenceCount() {
        this.bitField0_ &= -3;
        this.referenceCount_ = 0;
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeString(1, getName());
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeInt32(2, this.referenceCount_);
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
            size2 = 0 + CodedOutputStream.computeStringSize(1, getName());
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeInt32Size(2, this.referenceCount_);
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static SuspendBlockerProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (SuspendBlockerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static SuspendBlockerProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (SuspendBlockerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static SuspendBlockerProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (SuspendBlockerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static SuspendBlockerProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (SuspendBlockerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static SuspendBlockerProto parseFrom(InputStream input) throws IOException {
        return (SuspendBlockerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static SuspendBlockerProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (SuspendBlockerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static SuspendBlockerProto parseDelimitedFrom(InputStream input) throws IOException {
        return (SuspendBlockerProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static SuspendBlockerProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (SuspendBlockerProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static SuspendBlockerProto parseFrom(CodedInputStream input) throws IOException {
        return (SuspendBlockerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static SuspendBlockerProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (SuspendBlockerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(SuspendBlockerProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<SuspendBlockerProto, Builder> implements SuspendBlockerProtoOrBuilder {
        private Builder() {
            super(SuspendBlockerProto.DEFAULT_INSTANCE);
        }

        @Override // com.android.server.power.SuspendBlockerProtoOrBuilder
        public boolean hasName() {
            return ((SuspendBlockerProto) this.instance).hasName();
        }

        @Override // com.android.server.power.SuspendBlockerProtoOrBuilder
        public String getName() {
            return ((SuspendBlockerProto) this.instance).getName();
        }

        @Override // com.android.server.power.SuspendBlockerProtoOrBuilder
        public ByteString getNameBytes() {
            return ((SuspendBlockerProto) this.instance).getNameBytes();
        }

        public Builder setName(String value) {
            copyOnWrite();
            ((SuspendBlockerProto) this.instance).setName(value);
            return this;
        }

        public Builder clearName() {
            copyOnWrite();
            ((SuspendBlockerProto) this.instance).clearName();
            return this;
        }

        public Builder setNameBytes(ByteString value) {
            copyOnWrite();
            ((SuspendBlockerProto) this.instance).setNameBytes(value);
            return this;
        }

        @Override // com.android.server.power.SuspendBlockerProtoOrBuilder
        public boolean hasReferenceCount() {
            return ((SuspendBlockerProto) this.instance).hasReferenceCount();
        }

        @Override // com.android.server.power.SuspendBlockerProtoOrBuilder
        public int getReferenceCount() {
            return ((SuspendBlockerProto) this.instance).getReferenceCount();
        }

        public Builder setReferenceCount(int value) {
            copyOnWrite();
            ((SuspendBlockerProto) this.instance).setReferenceCount(value);
            return this;
        }

        public Builder clearReferenceCount() {
            copyOnWrite();
            ((SuspendBlockerProto) this.instance).clearReferenceCount();
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new SuspendBlockerProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                SuspendBlockerProto other = (SuspendBlockerProto) arg1;
                this.name_ = visitor.visitString(hasName(), this.name_, other.hasName(), other.name_);
                this.referenceCount_ = visitor.visitInt(hasReferenceCount(), this.referenceCount_, other.hasReferenceCount(), other.referenceCount_);
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
                            this.name_ = s;
                        } else if (tag == 16) {
                            this.bitField0_ |= 2;
                            this.referenceCount_ = input.readInt32();
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
                    synchronized (SuspendBlockerProto.class) {
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

    public static SuspendBlockerProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<SuspendBlockerProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
