package com.android.server.job;

import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;

public final class GrantedUriPermissionsDumpProto extends GeneratedMessageLite<GrantedUriPermissionsDumpProto, Builder> implements GrantedUriPermissionsDumpProtoOrBuilder {
    private static final GrantedUriPermissionsDumpProto DEFAULT_INSTANCE = new GrantedUriPermissionsDumpProto();
    public static final int FLAGS_FIELD_NUMBER = 1;
    private static volatile Parser<GrantedUriPermissionsDumpProto> PARSER = null;
    public static final int PERMISSION_OWNER_FIELD_NUMBER = 4;
    public static final int SOURCE_USER_ID_FIELD_NUMBER = 2;
    public static final int TAG_FIELD_NUMBER = 3;
    public static final int URIS_FIELD_NUMBER = 5;
    private int bitField0_;
    private int flags_ = 0;
    private String permissionOwner_ = "";
    private int sourceUserId_ = 0;
    private String tag_ = "";
    private Internal.ProtobufList<String> uris_ = GeneratedMessageLite.emptyProtobufList();

    private GrantedUriPermissionsDumpProto() {
    }

    @Override // com.android.server.job.GrantedUriPermissionsDumpProtoOrBuilder
    public boolean hasFlags() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // com.android.server.job.GrantedUriPermissionsDumpProtoOrBuilder
    public int getFlags() {
        return this.flags_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setFlags(int value) {
        this.bitField0_ |= 1;
        this.flags_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearFlags() {
        this.bitField0_ &= -2;
        this.flags_ = 0;
    }

    @Override // com.android.server.job.GrantedUriPermissionsDumpProtoOrBuilder
    public boolean hasSourceUserId() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // com.android.server.job.GrantedUriPermissionsDumpProtoOrBuilder
    public int getSourceUserId() {
        return this.sourceUserId_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setSourceUserId(int value) {
        this.bitField0_ |= 2;
        this.sourceUserId_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearSourceUserId() {
        this.bitField0_ &= -3;
        this.sourceUserId_ = 0;
    }

    @Override // com.android.server.job.GrantedUriPermissionsDumpProtoOrBuilder
    public boolean hasTag() {
        return (this.bitField0_ & 4) == 4;
    }

    @Override // com.android.server.job.GrantedUriPermissionsDumpProtoOrBuilder
    public String getTag() {
        return this.tag_;
    }

    @Override // com.android.server.job.GrantedUriPermissionsDumpProtoOrBuilder
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

    @Override // com.android.server.job.GrantedUriPermissionsDumpProtoOrBuilder
    public boolean hasPermissionOwner() {
        return (this.bitField0_ & 8) == 8;
    }

    @Override // com.android.server.job.GrantedUriPermissionsDumpProtoOrBuilder
    public String getPermissionOwner() {
        return this.permissionOwner_;
    }

    @Override // com.android.server.job.GrantedUriPermissionsDumpProtoOrBuilder
    public ByteString getPermissionOwnerBytes() {
        return ByteString.copyFromUtf8(this.permissionOwner_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPermissionOwner(String value) {
        if (value != null) {
            this.bitField0_ |= 8;
            this.permissionOwner_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearPermissionOwner() {
        this.bitField0_ &= -9;
        this.permissionOwner_ = getDefaultInstance().getPermissionOwner();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPermissionOwnerBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 8;
            this.permissionOwner_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // com.android.server.job.GrantedUriPermissionsDumpProtoOrBuilder
    public List<String> getUrisList() {
        return this.uris_;
    }

    @Override // com.android.server.job.GrantedUriPermissionsDumpProtoOrBuilder
    public int getUrisCount() {
        return this.uris_.size();
    }

    @Override // com.android.server.job.GrantedUriPermissionsDumpProtoOrBuilder
    public String getUris(int index) {
        return this.uris_.get(index);
    }

    @Override // com.android.server.job.GrantedUriPermissionsDumpProtoOrBuilder
    public ByteString getUrisBytes(int index) {
        return ByteString.copyFromUtf8(this.uris_.get(index));
    }

    private void ensureUrisIsMutable() {
        if (!this.uris_.isModifiable()) {
            this.uris_ = GeneratedMessageLite.mutableCopy(this.uris_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setUris(int index, String value) {
        if (value != null) {
            ensureUrisIsMutable();
            this.uris_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addUris(String value) {
        if (value != null) {
            ensureUrisIsMutable();
            this.uris_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllUris(Iterable<String> values) {
        ensureUrisIsMutable();
        AbstractMessageLite.addAll(values, this.uris_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearUris() {
        this.uris_ = GeneratedMessageLite.emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addUrisBytes(ByteString value) {
        if (value != null) {
            ensureUrisIsMutable();
            this.uris_.add(value.toStringUtf8());
            return;
        }
        throw new NullPointerException();
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeInt32(1, this.flags_);
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeInt32(2, this.sourceUserId_);
        }
        if ((this.bitField0_ & 4) == 4) {
            output.writeString(3, getTag());
        }
        if ((this.bitField0_ & 8) == 8) {
            output.writeString(4, getPermissionOwner());
        }
        for (int i = 0; i < this.uris_.size(); i++) {
            output.writeString(5, this.uris_.get(i));
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
            size2 = 0 + CodedOutputStream.computeInt32Size(1, this.flags_);
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeInt32Size(2, this.sourceUserId_);
        }
        if ((this.bitField0_ & 4) == 4) {
            size2 += CodedOutputStream.computeStringSize(3, getTag());
        }
        if ((this.bitField0_ & 8) == 8) {
            size2 += CodedOutputStream.computeStringSize(4, getPermissionOwner());
        }
        int dataSize = 0;
        for (int i = 0; i < this.uris_.size(); i++) {
            dataSize += CodedOutputStream.computeStringSizeNoTag(this.uris_.get(i));
        }
        int size3 = size2 + dataSize + (getUrisList().size() * 1) + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static GrantedUriPermissionsDumpProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (GrantedUriPermissionsDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static GrantedUriPermissionsDumpProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (GrantedUriPermissionsDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static GrantedUriPermissionsDumpProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (GrantedUriPermissionsDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static GrantedUriPermissionsDumpProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (GrantedUriPermissionsDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static GrantedUriPermissionsDumpProto parseFrom(InputStream input) throws IOException {
        return (GrantedUriPermissionsDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static GrantedUriPermissionsDumpProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (GrantedUriPermissionsDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static GrantedUriPermissionsDumpProto parseDelimitedFrom(InputStream input) throws IOException {
        return (GrantedUriPermissionsDumpProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static GrantedUriPermissionsDumpProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (GrantedUriPermissionsDumpProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static GrantedUriPermissionsDumpProto parseFrom(CodedInputStream input) throws IOException {
        return (GrantedUriPermissionsDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static GrantedUriPermissionsDumpProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (GrantedUriPermissionsDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(GrantedUriPermissionsDumpProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<GrantedUriPermissionsDumpProto, Builder> implements GrantedUriPermissionsDumpProtoOrBuilder {
        private Builder() {
            super(GrantedUriPermissionsDumpProto.DEFAULT_INSTANCE);
        }

        @Override // com.android.server.job.GrantedUriPermissionsDumpProtoOrBuilder
        public boolean hasFlags() {
            return ((GrantedUriPermissionsDumpProto) this.instance).hasFlags();
        }

        @Override // com.android.server.job.GrantedUriPermissionsDumpProtoOrBuilder
        public int getFlags() {
            return ((GrantedUriPermissionsDumpProto) this.instance).getFlags();
        }

        public Builder setFlags(int value) {
            copyOnWrite();
            ((GrantedUriPermissionsDumpProto) this.instance).setFlags(value);
            return this;
        }

        public Builder clearFlags() {
            copyOnWrite();
            ((GrantedUriPermissionsDumpProto) this.instance).clearFlags();
            return this;
        }

        @Override // com.android.server.job.GrantedUriPermissionsDumpProtoOrBuilder
        public boolean hasSourceUserId() {
            return ((GrantedUriPermissionsDumpProto) this.instance).hasSourceUserId();
        }

        @Override // com.android.server.job.GrantedUriPermissionsDumpProtoOrBuilder
        public int getSourceUserId() {
            return ((GrantedUriPermissionsDumpProto) this.instance).getSourceUserId();
        }

        public Builder setSourceUserId(int value) {
            copyOnWrite();
            ((GrantedUriPermissionsDumpProto) this.instance).setSourceUserId(value);
            return this;
        }

        public Builder clearSourceUserId() {
            copyOnWrite();
            ((GrantedUriPermissionsDumpProto) this.instance).clearSourceUserId();
            return this;
        }

        @Override // com.android.server.job.GrantedUriPermissionsDumpProtoOrBuilder
        public boolean hasTag() {
            return ((GrantedUriPermissionsDumpProto) this.instance).hasTag();
        }

        @Override // com.android.server.job.GrantedUriPermissionsDumpProtoOrBuilder
        public String getTag() {
            return ((GrantedUriPermissionsDumpProto) this.instance).getTag();
        }

        @Override // com.android.server.job.GrantedUriPermissionsDumpProtoOrBuilder
        public ByteString getTagBytes() {
            return ((GrantedUriPermissionsDumpProto) this.instance).getTagBytes();
        }

        public Builder setTag(String value) {
            copyOnWrite();
            ((GrantedUriPermissionsDumpProto) this.instance).setTag(value);
            return this;
        }

        public Builder clearTag() {
            copyOnWrite();
            ((GrantedUriPermissionsDumpProto) this.instance).clearTag();
            return this;
        }

        public Builder setTagBytes(ByteString value) {
            copyOnWrite();
            ((GrantedUriPermissionsDumpProto) this.instance).setTagBytes(value);
            return this;
        }

        @Override // com.android.server.job.GrantedUriPermissionsDumpProtoOrBuilder
        public boolean hasPermissionOwner() {
            return ((GrantedUriPermissionsDumpProto) this.instance).hasPermissionOwner();
        }

        @Override // com.android.server.job.GrantedUriPermissionsDumpProtoOrBuilder
        public String getPermissionOwner() {
            return ((GrantedUriPermissionsDumpProto) this.instance).getPermissionOwner();
        }

        @Override // com.android.server.job.GrantedUriPermissionsDumpProtoOrBuilder
        public ByteString getPermissionOwnerBytes() {
            return ((GrantedUriPermissionsDumpProto) this.instance).getPermissionOwnerBytes();
        }

        public Builder setPermissionOwner(String value) {
            copyOnWrite();
            ((GrantedUriPermissionsDumpProto) this.instance).setPermissionOwner(value);
            return this;
        }

        public Builder clearPermissionOwner() {
            copyOnWrite();
            ((GrantedUriPermissionsDumpProto) this.instance).clearPermissionOwner();
            return this;
        }

        public Builder setPermissionOwnerBytes(ByteString value) {
            copyOnWrite();
            ((GrantedUriPermissionsDumpProto) this.instance).setPermissionOwnerBytes(value);
            return this;
        }

        @Override // com.android.server.job.GrantedUriPermissionsDumpProtoOrBuilder
        public List<String> getUrisList() {
            return Collections.unmodifiableList(((GrantedUriPermissionsDumpProto) this.instance).getUrisList());
        }

        @Override // com.android.server.job.GrantedUriPermissionsDumpProtoOrBuilder
        public int getUrisCount() {
            return ((GrantedUriPermissionsDumpProto) this.instance).getUrisCount();
        }

        @Override // com.android.server.job.GrantedUriPermissionsDumpProtoOrBuilder
        public String getUris(int index) {
            return ((GrantedUriPermissionsDumpProto) this.instance).getUris(index);
        }

        @Override // com.android.server.job.GrantedUriPermissionsDumpProtoOrBuilder
        public ByteString getUrisBytes(int index) {
            return ((GrantedUriPermissionsDumpProto) this.instance).getUrisBytes(index);
        }

        public Builder setUris(int index, String value) {
            copyOnWrite();
            ((GrantedUriPermissionsDumpProto) this.instance).setUris(index, value);
            return this;
        }

        public Builder addUris(String value) {
            copyOnWrite();
            ((GrantedUriPermissionsDumpProto) this.instance).addUris(value);
            return this;
        }

        public Builder addAllUris(Iterable<String> values) {
            copyOnWrite();
            ((GrantedUriPermissionsDumpProto) this.instance).addAllUris(values);
            return this;
        }

        public Builder clearUris() {
            copyOnWrite();
            ((GrantedUriPermissionsDumpProto) this.instance).clearUris();
            return this;
        }

        public Builder addUrisBytes(ByteString value) {
            copyOnWrite();
            ((GrantedUriPermissionsDumpProto) this.instance).addUrisBytes(value);
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new GrantedUriPermissionsDumpProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                this.uris_.makeImmutable();
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                GrantedUriPermissionsDumpProto other = (GrantedUriPermissionsDumpProto) arg1;
                this.flags_ = visitor.visitInt(hasFlags(), this.flags_, other.hasFlags(), other.flags_);
                this.sourceUserId_ = visitor.visitInt(hasSourceUserId(), this.sourceUserId_, other.hasSourceUserId(), other.sourceUserId_);
                this.tag_ = visitor.visitString(hasTag(), this.tag_, other.hasTag(), other.tag_);
                this.permissionOwner_ = visitor.visitString(hasPermissionOwner(), this.permissionOwner_, other.hasPermissionOwner(), other.permissionOwner_);
                this.uris_ = visitor.visitList(this.uris_, other.uris_);
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
                            this.flags_ = input.readInt32();
                        } else if (tag == 16) {
                            this.bitField0_ |= 2;
                            this.sourceUserId_ = input.readInt32();
                        } else if (tag == 26) {
                            String s = input.readString();
                            this.bitField0_ |= 4;
                            this.tag_ = s;
                        } else if (tag == 34) {
                            String s2 = input.readString();
                            this.bitField0_ = 8 | this.bitField0_;
                            this.permissionOwner_ = s2;
                        } else if (tag == 42) {
                            String s3 = input.readString();
                            if (!this.uris_.isModifiable()) {
                                this.uris_ = GeneratedMessageLite.mutableCopy(this.uris_);
                            }
                            this.uris_.add(s3);
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
                    synchronized (GrantedUriPermissionsDumpProto.class) {
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

    public static GrantedUriPermissionsDumpProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<GrantedUriPermissionsDumpProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
