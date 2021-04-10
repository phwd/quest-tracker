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

public final class IdleDispatchEntryProto extends GeneratedMessageLite<IdleDispatchEntryProto, Builder> implements IdleDispatchEntryProtoOrBuilder {
    public static final int ARG_REALTIME_FIELD_NUMBER = 6;
    private static final IdleDispatchEntryProto DEFAULT_INSTANCE = new IdleDispatchEntryProto();
    public static final int ENTRY_CREATION_REALTIME_FIELD_NUMBER = 5;
    public static final int OP_FIELD_NUMBER = 4;
    private static volatile Parser<IdleDispatchEntryProto> PARSER = null;
    public static final int PKG_FIELD_NUMBER = 2;
    public static final int TAG_FIELD_NUMBER = 3;
    public static final int UID_FIELD_NUMBER = 1;
    private long argRealtime_ = 0;
    private int bitField0_;
    private long entryCreationRealtime_ = 0;
    private String op_ = "";
    private String pkg_ = "";
    private String tag_ = "";
    private int uid_ = 0;

    private IdleDispatchEntryProto() {
    }

    @Override // com.android.server.IdleDispatchEntryProtoOrBuilder
    public boolean hasUid() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // com.android.server.IdleDispatchEntryProtoOrBuilder
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

    @Override // com.android.server.IdleDispatchEntryProtoOrBuilder
    public boolean hasPkg() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // com.android.server.IdleDispatchEntryProtoOrBuilder
    public String getPkg() {
        return this.pkg_;
    }

    @Override // com.android.server.IdleDispatchEntryProtoOrBuilder
    public ByteString getPkgBytes() {
        return ByteString.copyFromUtf8(this.pkg_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPkg(String value) {
        if (value != null) {
            this.bitField0_ |= 2;
            this.pkg_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearPkg() {
        this.bitField0_ &= -3;
        this.pkg_ = getDefaultInstance().getPkg();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPkgBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 2;
            this.pkg_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // com.android.server.IdleDispatchEntryProtoOrBuilder
    public boolean hasTag() {
        return (this.bitField0_ & 4) == 4;
    }

    @Override // com.android.server.IdleDispatchEntryProtoOrBuilder
    public String getTag() {
        return this.tag_;
    }

    @Override // com.android.server.IdleDispatchEntryProtoOrBuilder
    public ByteString getTagBytes() {
        return ByteString.copyFromUtf8(this.tag_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setTag(String value) {
        if (value != null) {
            this.bitField0_ |= 4;
            this.tag_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearTag() {
        this.bitField0_ &= -5;
        this.tag_ = getDefaultInstance().getTag();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setTagBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 4;
            this.tag_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // com.android.server.IdleDispatchEntryProtoOrBuilder
    public boolean hasOp() {
        return (this.bitField0_ & 8) == 8;
    }

    @Override // com.android.server.IdleDispatchEntryProtoOrBuilder
    public String getOp() {
        return this.op_;
    }

    @Override // com.android.server.IdleDispatchEntryProtoOrBuilder
    public ByteString getOpBytes() {
        return ByteString.copyFromUtf8(this.op_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setOp(String value) {
        if (value != null) {
            this.bitField0_ |= 8;
            this.op_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearOp() {
        this.bitField0_ &= -9;
        this.op_ = getDefaultInstance().getOp();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setOpBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 8;
            this.op_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // com.android.server.IdleDispatchEntryProtoOrBuilder
    public boolean hasEntryCreationRealtime() {
        return (this.bitField0_ & 16) == 16;
    }

    @Override // com.android.server.IdleDispatchEntryProtoOrBuilder
    public long getEntryCreationRealtime() {
        return this.entryCreationRealtime_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setEntryCreationRealtime(long value) {
        this.bitField0_ |= 16;
        this.entryCreationRealtime_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearEntryCreationRealtime() {
        this.bitField0_ &= -17;
        this.entryCreationRealtime_ = 0;
    }

    @Override // com.android.server.IdleDispatchEntryProtoOrBuilder
    public boolean hasArgRealtime() {
        return (this.bitField0_ & 32) == 32;
    }

    @Override // com.android.server.IdleDispatchEntryProtoOrBuilder
    public long getArgRealtime() {
        return this.argRealtime_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setArgRealtime(long value) {
        this.bitField0_ |= 32;
        this.argRealtime_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearArgRealtime() {
        this.bitField0_ &= -33;
        this.argRealtime_ = 0;
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeInt32(1, this.uid_);
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeString(2, getPkg());
        }
        if ((this.bitField0_ & 4) == 4) {
            output.writeString(3, getTag());
        }
        if ((this.bitField0_ & 8) == 8) {
            output.writeString(4, getOp());
        }
        if ((this.bitField0_ & 16) == 16) {
            output.writeInt64(5, this.entryCreationRealtime_);
        }
        if ((this.bitField0_ & 32) == 32) {
            output.writeInt64(6, this.argRealtime_);
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
            size2 += CodedOutputStream.computeStringSize(2, getPkg());
        }
        if ((this.bitField0_ & 4) == 4) {
            size2 += CodedOutputStream.computeStringSize(3, getTag());
        }
        if ((this.bitField0_ & 8) == 8) {
            size2 += CodedOutputStream.computeStringSize(4, getOp());
        }
        if ((this.bitField0_ & 16) == 16) {
            size2 += CodedOutputStream.computeInt64Size(5, this.entryCreationRealtime_);
        }
        if ((this.bitField0_ & 32) == 32) {
            size2 += CodedOutputStream.computeInt64Size(6, this.argRealtime_);
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static IdleDispatchEntryProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (IdleDispatchEntryProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static IdleDispatchEntryProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (IdleDispatchEntryProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static IdleDispatchEntryProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (IdleDispatchEntryProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static IdleDispatchEntryProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (IdleDispatchEntryProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static IdleDispatchEntryProto parseFrom(InputStream input) throws IOException {
        return (IdleDispatchEntryProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static IdleDispatchEntryProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (IdleDispatchEntryProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static IdleDispatchEntryProto parseDelimitedFrom(InputStream input) throws IOException {
        return (IdleDispatchEntryProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static IdleDispatchEntryProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (IdleDispatchEntryProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static IdleDispatchEntryProto parseFrom(CodedInputStream input) throws IOException {
        return (IdleDispatchEntryProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static IdleDispatchEntryProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (IdleDispatchEntryProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(IdleDispatchEntryProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<IdleDispatchEntryProto, Builder> implements IdleDispatchEntryProtoOrBuilder {
        private Builder() {
            super(IdleDispatchEntryProto.DEFAULT_INSTANCE);
        }

        @Override // com.android.server.IdleDispatchEntryProtoOrBuilder
        public boolean hasUid() {
            return ((IdleDispatchEntryProto) this.instance).hasUid();
        }

        @Override // com.android.server.IdleDispatchEntryProtoOrBuilder
        public int getUid() {
            return ((IdleDispatchEntryProto) this.instance).getUid();
        }

        public Builder setUid(int value) {
            copyOnWrite();
            ((IdleDispatchEntryProto) this.instance).setUid(value);
            return this;
        }

        public Builder clearUid() {
            copyOnWrite();
            ((IdleDispatchEntryProto) this.instance).clearUid();
            return this;
        }

        @Override // com.android.server.IdleDispatchEntryProtoOrBuilder
        public boolean hasPkg() {
            return ((IdleDispatchEntryProto) this.instance).hasPkg();
        }

        @Override // com.android.server.IdleDispatchEntryProtoOrBuilder
        public String getPkg() {
            return ((IdleDispatchEntryProto) this.instance).getPkg();
        }

        @Override // com.android.server.IdleDispatchEntryProtoOrBuilder
        public ByteString getPkgBytes() {
            return ((IdleDispatchEntryProto) this.instance).getPkgBytes();
        }

        public Builder setPkg(String value) {
            copyOnWrite();
            ((IdleDispatchEntryProto) this.instance).setPkg(value);
            return this;
        }

        public Builder clearPkg() {
            copyOnWrite();
            ((IdleDispatchEntryProto) this.instance).clearPkg();
            return this;
        }

        public Builder setPkgBytes(ByteString value) {
            copyOnWrite();
            ((IdleDispatchEntryProto) this.instance).setPkgBytes(value);
            return this;
        }

        @Override // com.android.server.IdleDispatchEntryProtoOrBuilder
        public boolean hasTag() {
            return ((IdleDispatchEntryProto) this.instance).hasTag();
        }

        @Override // com.android.server.IdleDispatchEntryProtoOrBuilder
        public String getTag() {
            return ((IdleDispatchEntryProto) this.instance).getTag();
        }

        @Override // com.android.server.IdleDispatchEntryProtoOrBuilder
        public ByteString getTagBytes() {
            return ((IdleDispatchEntryProto) this.instance).getTagBytes();
        }

        public Builder setTag(String value) {
            copyOnWrite();
            ((IdleDispatchEntryProto) this.instance).setTag(value);
            return this;
        }

        public Builder clearTag() {
            copyOnWrite();
            ((IdleDispatchEntryProto) this.instance).clearTag();
            return this;
        }

        public Builder setTagBytes(ByteString value) {
            copyOnWrite();
            ((IdleDispatchEntryProto) this.instance).setTagBytes(value);
            return this;
        }

        @Override // com.android.server.IdleDispatchEntryProtoOrBuilder
        public boolean hasOp() {
            return ((IdleDispatchEntryProto) this.instance).hasOp();
        }

        @Override // com.android.server.IdleDispatchEntryProtoOrBuilder
        public String getOp() {
            return ((IdleDispatchEntryProto) this.instance).getOp();
        }

        @Override // com.android.server.IdleDispatchEntryProtoOrBuilder
        public ByteString getOpBytes() {
            return ((IdleDispatchEntryProto) this.instance).getOpBytes();
        }

        public Builder setOp(String value) {
            copyOnWrite();
            ((IdleDispatchEntryProto) this.instance).setOp(value);
            return this;
        }

        public Builder clearOp() {
            copyOnWrite();
            ((IdleDispatchEntryProto) this.instance).clearOp();
            return this;
        }

        public Builder setOpBytes(ByteString value) {
            copyOnWrite();
            ((IdleDispatchEntryProto) this.instance).setOpBytes(value);
            return this;
        }

        @Override // com.android.server.IdleDispatchEntryProtoOrBuilder
        public boolean hasEntryCreationRealtime() {
            return ((IdleDispatchEntryProto) this.instance).hasEntryCreationRealtime();
        }

        @Override // com.android.server.IdleDispatchEntryProtoOrBuilder
        public long getEntryCreationRealtime() {
            return ((IdleDispatchEntryProto) this.instance).getEntryCreationRealtime();
        }

        public Builder setEntryCreationRealtime(long value) {
            copyOnWrite();
            ((IdleDispatchEntryProto) this.instance).setEntryCreationRealtime(value);
            return this;
        }

        public Builder clearEntryCreationRealtime() {
            copyOnWrite();
            ((IdleDispatchEntryProto) this.instance).clearEntryCreationRealtime();
            return this;
        }

        @Override // com.android.server.IdleDispatchEntryProtoOrBuilder
        public boolean hasArgRealtime() {
            return ((IdleDispatchEntryProto) this.instance).hasArgRealtime();
        }

        @Override // com.android.server.IdleDispatchEntryProtoOrBuilder
        public long getArgRealtime() {
            return ((IdleDispatchEntryProto) this.instance).getArgRealtime();
        }

        public Builder setArgRealtime(long value) {
            copyOnWrite();
            ((IdleDispatchEntryProto) this.instance).setArgRealtime(value);
            return this;
        }

        public Builder clearArgRealtime() {
            copyOnWrite();
            ((IdleDispatchEntryProto) this.instance).clearArgRealtime();
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new IdleDispatchEntryProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                IdleDispatchEntryProto other = (IdleDispatchEntryProto) arg1;
                this.uid_ = visitor.visitInt(hasUid(), this.uid_, other.hasUid(), other.uid_);
                this.pkg_ = visitor.visitString(hasPkg(), this.pkg_, other.hasPkg(), other.pkg_);
                this.tag_ = visitor.visitString(hasTag(), this.tag_, other.hasTag(), other.tag_);
                this.op_ = visitor.visitString(hasOp(), this.op_, other.hasOp(), other.op_);
                this.entryCreationRealtime_ = visitor.visitLong(hasEntryCreationRealtime(), this.entryCreationRealtime_, other.hasEntryCreationRealtime(), other.entryCreationRealtime_);
                this.argRealtime_ = visitor.visitLong(hasArgRealtime(), this.argRealtime_, other.hasArgRealtime(), other.argRealtime_);
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
                            this.pkg_ = s;
                        } else if (tag == 26) {
                            String s2 = input.readString();
                            this.bitField0_ |= 4;
                            this.tag_ = s2;
                        } else if (tag == 34) {
                            String s3 = input.readString();
                            this.bitField0_ = 8 | this.bitField0_;
                            this.op_ = s3;
                        } else if (tag == 40) {
                            this.bitField0_ |= 16;
                            this.entryCreationRealtime_ = input.readInt64();
                        } else if (tag == 48) {
                            this.bitField0_ |= 32;
                            this.argRealtime_ = input.readInt64();
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
                    synchronized (IdleDispatchEntryProto.class) {
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

    public static IdleDispatchEntryProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<IdleDispatchEntryProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
