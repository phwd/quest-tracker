package com.android.server.role;

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

public final class RoleProto extends GeneratedMessageLite<RoleProto, Builder> implements RoleProtoOrBuilder {
    private static final RoleProto DEFAULT_INSTANCE = new RoleProto();
    public static final int HOLDERS_FIELD_NUMBER = 2;
    public static final int NAME_FIELD_NUMBER = 1;
    private static volatile Parser<RoleProto> PARSER;
    private int bitField0_;
    private Internal.ProtobufList<String> holders_ = GeneratedMessageLite.emptyProtobufList();
    private String name_ = "";

    private RoleProto() {
    }

    @Override // com.android.server.role.RoleProtoOrBuilder
    public boolean hasName() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // com.android.server.role.RoleProtoOrBuilder
    public String getName() {
        return this.name_;
    }

    @Override // com.android.server.role.RoleProtoOrBuilder
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

    @Override // com.android.server.role.RoleProtoOrBuilder
    public List<String> getHoldersList() {
        return this.holders_;
    }

    @Override // com.android.server.role.RoleProtoOrBuilder
    public int getHoldersCount() {
        return this.holders_.size();
    }

    @Override // com.android.server.role.RoleProtoOrBuilder
    public String getHolders(int index) {
        return this.holders_.get(index);
    }

    @Override // com.android.server.role.RoleProtoOrBuilder
    public ByteString getHoldersBytes(int index) {
        return ByteString.copyFromUtf8(this.holders_.get(index));
    }

    private void ensureHoldersIsMutable() {
        if (!this.holders_.isModifiable()) {
            this.holders_ = GeneratedMessageLite.mutableCopy(this.holders_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setHolders(int index, String value) {
        if (value != null) {
            ensureHoldersIsMutable();
            this.holders_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addHolders(String value) {
        if (value != null) {
            ensureHoldersIsMutable();
            this.holders_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllHolders(Iterable<String> values) {
        ensureHoldersIsMutable();
        AbstractMessageLite.addAll(values, this.holders_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearHolders() {
        this.holders_ = GeneratedMessageLite.emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addHoldersBytes(ByteString value) {
        if (value != null) {
            ensureHoldersIsMutable();
            this.holders_.add(value.toStringUtf8());
            return;
        }
        throw new NullPointerException();
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeString(1, getName());
        }
        for (int i = 0; i < this.holders_.size(); i++) {
            output.writeString(2, this.holders_.get(i));
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
        int dataSize = 0;
        for (int i = 0; i < this.holders_.size(); i++) {
            dataSize += CodedOutputStream.computeStringSizeNoTag(this.holders_.get(i));
        }
        int size3 = size2 + dataSize + (getHoldersList().size() * 1) + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static RoleProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (RoleProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static RoleProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (RoleProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static RoleProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (RoleProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static RoleProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (RoleProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static RoleProto parseFrom(InputStream input) throws IOException {
        return (RoleProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static RoleProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (RoleProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static RoleProto parseDelimitedFrom(InputStream input) throws IOException {
        return (RoleProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static RoleProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (RoleProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static RoleProto parseFrom(CodedInputStream input) throws IOException {
        return (RoleProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static RoleProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (RoleProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(RoleProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<RoleProto, Builder> implements RoleProtoOrBuilder {
        private Builder() {
            super(RoleProto.DEFAULT_INSTANCE);
        }

        @Override // com.android.server.role.RoleProtoOrBuilder
        public boolean hasName() {
            return ((RoleProto) this.instance).hasName();
        }

        @Override // com.android.server.role.RoleProtoOrBuilder
        public String getName() {
            return ((RoleProto) this.instance).getName();
        }

        @Override // com.android.server.role.RoleProtoOrBuilder
        public ByteString getNameBytes() {
            return ((RoleProto) this.instance).getNameBytes();
        }

        public Builder setName(String value) {
            copyOnWrite();
            ((RoleProto) this.instance).setName(value);
            return this;
        }

        public Builder clearName() {
            copyOnWrite();
            ((RoleProto) this.instance).clearName();
            return this;
        }

        public Builder setNameBytes(ByteString value) {
            copyOnWrite();
            ((RoleProto) this.instance).setNameBytes(value);
            return this;
        }

        @Override // com.android.server.role.RoleProtoOrBuilder
        public List<String> getHoldersList() {
            return Collections.unmodifiableList(((RoleProto) this.instance).getHoldersList());
        }

        @Override // com.android.server.role.RoleProtoOrBuilder
        public int getHoldersCount() {
            return ((RoleProto) this.instance).getHoldersCount();
        }

        @Override // com.android.server.role.RoleProtoOrBuilder
        public String getHolders(int index) {
            return ((RoleProto) this.instance).getHolders(index);
        }

        @Override // com.android.server.role.RoleProtoOrBuilder
        public ByteString getHoldersBytes(int index) {
            return ((RoleProto) this.instance).getHoldersBytes(index);
        }

        public Builder setHolders(int index, String value) {
            copyOnWrite();
            ((RoleProto) this.instance).setHolders(index, value);
            return this;
        }

        public Builder addHolders(String value) {
            copyOnWrite();
            ((RoleProto) this.instance).addHolders(value);
            return this;
        }

        public Builder addAllHolders(Iterable<String> values) {
            copyOnWrite();
            ((RoleProto) this.instance).addAllHolders(values);
            return this;
        }

        public Builder clearHolders() {
            copyOnWrite();
            ((RoleProto) this.instance).clearHolders();
            return this;
        }

        public Builder addHoldersBytes(ByteString value) {
            copyOnWrite();
            ((RoleProto) this.instance).addHoldersBytes(value);
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new RoleProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                this.holders_.makeImmutable();
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                RoleProto other = (RoleProto) arg1;
                this.name_ = visitor.visitString(hasName(), this.name_, other.hasName(), other.name_);
                this.holders_ = visitor.visitList(this.holders_, other.holders_);
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
                        } else if (tag == 18) {
                            String s2 = input.readString();
                            if (!this.holders_.isModifiable()) {
                                this.holders_ = GeneratedMessageLite.mutableCopy(this.holders_);
                            }
                            this.holders_.add(s2);
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
                    synchronized (RoleProto.class) {
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

    public static RoleProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<RoleProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
