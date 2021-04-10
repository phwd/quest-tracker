package com.android.server.am;

import com.android.server.am.GrantUriProto;
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

public final class UriPermissionOwnerProto extends GeneratedMessageLite<UriPermissionOwnerProto, Builder> implements UriPermissionOwnerProtoOrBuilder {
    private static final UriPermissionOwnerProto DEFAULT_INSTANCE = new UriPermissionOwnerProto();
    public static final int OWNER_FIELD_NUMBER = 1;
    private static volatile Parser<UriPermissionOwnerProto> PARSER = null;
    public static final int READ_PERMS_FIELD_NUMBER = 2;
    public static final int WRITE_PERMS_FIELD_NUMBER = 3;
    private int bitField0_;
    private String owner_ = "";
    private Internal.ProtobufList<GrantUriProto> readPerms_ = emptyProtobufList();
    private Internal.ProtobufList<GrantUriProto> writePerms_ = emptyProtobufList();

    private UriPermissionOwnerProto() {
    }

    @Override // com.android.server.am.UriPermissionOwnerProtoOrBuilder
    public boolean hasOwner() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // com.android.server.am.UriPermissionOwnerProtoOrBuilder
    public String getOwner() {
        return this.owner_;
    }

    @Override // com.android.server.am.UriPermissionOwnerProtoOrBuilder
    public ByteString getOwnerBytes() {
        return ByteString.copyFromUtf8(this.owner_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setOwner(String value) {
        if (value != null) {
            this.bitField0_ |= 1;
            this.owner_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearOwner() {
        this.bitField0_ &= -2;
        this.owner_ = getDefaultInstance().getOwner();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setOwnerBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 1;
            this.owner_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // com.android.server.am.UriPermissionOwnerProtoOrBuilder
    public List<GrantUriProto> getReadPermsList() {
        return this.readPerms_;
    }

    public List<? extends GrantUriProtoOrBuilder> getReadPermsOrBuilderList() {
        return this.readPerms_;
    }

    @Override // com.android.server.am.UriPermissionOwnerProtoOrBuilder
    public int getReadPermsCount() {
        return this.readPerms_.size();
    }

    @Override // com.android.server.am.UriPermissionOwnerProtoOrBuilder
    public GrantUriProto getReadPerms(int index) {
        return this.readPerms_.get(index);
    }

    public GrantUriProtoOrBuilder getReadPermsOrBuilder(int index) {
        return this.readPerms_.get(index);
    }

    private void ensureReadPermsIsMutable() {
        if (!this.readPerms_.isModifiable()) {
            this.readPerms_ = GeneratedMessageLite.mutableCopy(this.readPerms_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setReadPerms(int index, GrantUriProto value) {
        if (value != null) {
            ensureReadPermsIsMutable();
            this.readPerms_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setReadPerms(int index, GrantUriProto.Builder builderForValue) {
        ensureReadPermsIsMutable();
        this.readPerms_.set(index, (GrantUriProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addReadPerms(GrantUriProto value) {
        if (value != null) {
            ensureReadPermsIsMutable();
            this.readPerms_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addReadPerms(int index, GrantUriProto value) {
        if (value != null) {
            ensureReadPermsIsMutable();
            this.readPerms_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addReadPerms(GrantUriProto.Builder builderForValue) {
        ensureReadPermsIsMutable();
        this.readPerms_.add((GrantUriProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addReadPerms(int index, GrantUriProto.Builder builderForValue) {
        ensureReadPermsIsMutable();
        this.readPerms_.add(index, (GrantUriProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllReadPerms(Iterable<? extends GrantUriProto> values) {
        ensureReadPermsIsMutable();
        AbstractMessageLite.addAll(values, this.readPerms_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearReadPerms() {
        this.readPerms_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeReadPerms(int index) {
        ensureReadPermsIsMutable();
        this.readPerms_.remove(index);
    }

    @Override // com.android.server.am.UriPermissionOwnerProtoOrBuilder
    public List<GrantUriProto> getWritePermsList() {
        return this.writePerms_;
    }

    public List<? extends GrantUriProtoOrBuilder> getWritePermsOrBuilderList() {
        return this.writePerms_;
    }

    @Override // com.android.server.am.UriPermissionOwnerProtoOrBuilder
    public int getWritePermsCount() {
        return this.writePerms_.size();
    }

    @Override // com.android.server.am.UriPermissionOwnerProtoOrBuilder
    public GrantUriProto getWritePerms(int index) {
        return this.writePerms_.get(index);
    }

    public GrantUriProtoOrBuilder getWritePermsOrBuilder(int index) {
        return this.writePerms_.get(index);
    }

    private void ensureWritePermsIsMutable() {
        if (!this.writePerms_.isModifiable()) {
            this.writePerms_ = GeneratedMessageLite.mutableCopy(this.writePerms_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setWritePerms(int index, GrantUriProto value) {
        if (value != null) {
            ensureWritePermsIsMutable();
            this.writePerms_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setWritePerms(int index, GrantUriProto.Builder builderForValue) {
        ensureWritePermsIsMutable();
        this.writePerms_.set(index, (GrantUriProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addWritePerms(GrantUriProto value) {
        if (value != null) {
            ensureWritePermsIsMutable();
            this.writePerms_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addWritePerms(int index, GrantUriProto value) {
        if (value != null) {
            ensureWritePermsIsMutable();
            this.writePerms_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addWritePerms(GrantUriProto.Builder builderForValue) {
        ensureWritePermsIsMutable();
        this.writePerms_.add((GrantUriProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addWritePerms(int index, GrantUriProto.Builder builderForValue) {
        ensureWritePermsIsMutable();
        this.writePerms_.add(index, (GrantUriProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllWritePerms(Iterable<? extends GrantUriProto> values) {
        ensureWritePermsIsMutable();
        AbstractMessageLite.addAll(values, this.writePerms_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearWritePerms() {
        this.writePerms_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeWritePerms(int index) {
        ensureWritePermsIsMutable();
        this.writePerms_.remove(index);
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeString(1, getOwner());
        }
        for (int i = 0; i < this.readPerms_.size(); i++) {
            output.writeMessage(2, this.readPerms_.get(i));
        }
        for (int i2 = 0; i2 < this.writePerms_.size(); i2++) {
            output.writeMessage(3, this.writePerms_.get(i2));
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
            size2 = 0 + CodedOutputStream.computeStringSize(1, getOwner());
        }
        for (int i = 0; i < this.readPerms_.size(); i++) {
            size2 += CodedOutputStream.computeMessageSize(2, this.readPerms_.get(i));
        }
        for (int i2 = 0; i2 < this.writePerms_.size(); i2++) {
            size2 += CodedOutputStream.computeMessageSize(3, this.writePerms_.get(i2));
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static UriPermissionOwnerProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (UriPermissionOwnerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static UriPermissionOwnerProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (UriPermissionOwnerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static UriPermissionOwnerProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (UriPermissionOwnerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static UriPermissionOwnerProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (UriPermissionOwnerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static UriPermissionOwnerProto parseFrom(InputStream input) throws IOException {
        return (UriPermissionOwnerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static UriPermissionOwnerProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (UriPermissionOwnerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static UriPermissionOwnerProto parseDelimitedFrom(InputStream input) throws IOException {
        return (UriPermissionOwnerProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static UriPermissionOwnerProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (UriPermissionOwnerProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static UriPermissionOwnerProto parseFrom(CodedInputStream input) throws IOException {
        return (UriPermissionOwnerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static UriPermissionOwnerProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (UriPermissionOwnerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(UriPermissionOwnerProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<UriPermissionOwnerProto, Builder> implements UriPermissionOwnerProtoOrBuilder {
        private Builder() {
            super(UriPermissionOwnerProto.DEFAULT_INSTANCE);
        }

        @Override // com.android.server.am.UriPermissionOwnerProtoOrBuilder
        public boolean hasOwner() {
            return ((UriPermissionOwnerProto) this.instance).hasOwner();
        }

        @Override // com.android.server.am.UriPermissionOwnerProtoOrBuilder
        public String getOwner() {
            return ((UriPermissionOwnerProto) this.instance).getOwner();
        }

        @Override // com.android.server.am.UriPermissionOwnerProtoOrBuilder
        public ByteString getOwnerBytes() {
            return ((UriPermissionOwnerProto) this.instance).getOwnerBytes();
        }

        public Builder setOwner(String value) {
            copyOnWrite();
            ((UriPermissionOwnerProto) this.instance).setOwner(value);
            return this;
        }

        public Builder clearOwner() {
            copyOnWrite();
            ((UriPermissionOwnerProto) this.instance).clearOwner();
            return this;
        }

        public Builder setOwnerBytes(ByteString value) {
            copyOnWrite();
            ((UriPermissionOwnerProto) this.instance).setOwnerBytes(value);
            return this;
        }

        @Override // com.android.server.am.UriPermissionOwnerProtoOrBuilder
        public List<GrantUriProto> getReadPermsList() {
            return Collections.unmodifiableList(((UriPermissionOwnerProto) this.instance).getReadPermsList());
        }

        @Override // com.android.server.am.UriPermissionOwnerProtoOrBuilder
        public int getReadPermsCount() {
            return ((UriPermissionOwnerProto) this.instance).getReadPermsCount();
        }

        @Override // com.android.server.am.UriPermissionOwnerProtoOrBuilder
        public GrantUriProto getReadPerms(int index) {
            return ((UriPermissionOwnerProto) this.instance).getReadPerms(index);
        }

        public Builder setReadPerms(int index, GrantUriProto value) {
            copyOnWrite();
            ((UriPermissionOwnerProto) this.instance).setReadPerms((UriPermissionOwnerProto) index, (int) value);
            return this;
        }

        public Builder setReadPerms(int index, GrantUriProto.Builder builderForValue) {
            copyOnWrite();
            ((UriPermissionOwnerProto) this.instance).setReadPerms((UriPermissionOwnerProto) index, (int) builderForValue);
            return this;
        }

        public Builder addReadPerms(GrantUriProto value) {
            copyOnWrite();
            ((UriPermissionOwnerProto) this.instance).addReadPerms((UriPermissionOwnerProto) value);
            return this;
        }

        public Builder addReadPerms(int index, GrantUriProto value) {
            copyOnWrite();
            ((UriPermissionOwnerProto) this.instance).addReadPerms((UriPermissionOwnerProto) index, (int) value);
            return this;
        }

        public Builder addReadPerms(GrantUriProto.Builder builderForValue) {
            copyOnWrite();
            ((UriPermissionOwnerProto) this.instance).addReadPerms((UriPermissionOwnerProto) builderForValue);
            return this;
        }

        public Builder addReadPerms(int index, GrantUriProto.Builder builderForValue) {
            copyOnWrite();
            ((UriPermissionOwnerProto) this.instance).addReadPerms((UriPermissionOwnerProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllReadPerms(Iterable<? extends GrantUriProto> values) {
            copyOnWrite();
            ((UriPermissionOwnerProto) this.instance).addAllReadPerms(values);
            return this;
        }

        public Builder clearReadPerms() {
            copyOnWrite();
            ((UriPermissionOwnerProto) this.instance).clearReadPerms();
            return this;
        }

        public Builder removeReadPerms(int index) {
            copyOnWrite();
            ((UriPermissionOwnerProto) this.instance).removeReadPerms(index);
            return this;
        }

        @Override // com.android.server.am.UriPermissionOwnerProtoOrBuilder
        public List<GrantUriProto> getWritePermsList() {
            return Collections.unmodifiableList(((UriPermissionOwnerProto) this.instance).getWritePermsList());
        }

        @Override // com.android.server.am.UriPermissionOwnerProtoOrBuilder
        public int getWritePermsCount() {
            return ((UriPermissionOwnerProto) this.instance).getWritePermsCount();
        }

        @Override // com.android.server.am.UriPermissionOwnerProtoOrBuilder
        public GrantUriProto getWritePerms(int index) {
            return ((UriPermissionOwnerProto) this.instance).getWritePerms(index);
        }

        public Builder setWritePerms(int index, GrantUriProto value) {
            copyOnWrite();
            ((UriPermissionOwnerProto) this.instance).setWritePerms((UriPermissionOwnerProto) index, (int) value);
            return this;
        }

        public Builder setWritePerms(int index, GrantUriProto.Builder builderForValue) {
            copyOnWrite();
            ((UriPermissionOwnerProto) this.instance).setWritePerms((UriPermissionOwnerProto) index, (int) builderForValue);
            return this;
        }

        public Builder addWritePerms(GrantUriProto value) {
            copyOnWrite();
            ((UriPermissionOwnerProto) this.instance).addWritePerms((UriPermissionOwnerProto) value);
            return this;
        }

        public Builder addWritePerms(int index, GrantUriProto value) {
            copyOnWrite();
            ((UriPermissionOwnerProto) this.instance).addWritePerms((UriPermissionOwnerProto) index, (int) value);
            return this;
        }

        public Builder addWritePerms(GrantUriProto.Builder builderForValue) {
            copyOnWrite();
            ((UriPermissionOwnerProto) this.instance).addWritePerms((UriPermissionOwnerProto) builderForValue);
            return this;
        }

        public Builder addWritePerms(int index, GrantUriProto.Builder builderForValue) {
            copyOnWrite();
            ((UriPermissionOwnerProto) this.instance).addWritePerms((UriPermissionOwnerProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllWritePerms(Iterable<? extends GrantUriProto> values) {
            copyOnWrite();
            ((UriPermissionOwnerProto) this.instance).addAllWritePerms(values);
            return this;
        }

        public Builder clearWritePerms() {
            copyOnWrite();
            ((UriPermissionOwnerProto) this.instance).clearWritePerms();
            return this;
        }

        public Builder removeWritePerms(int index) {
            copyOnWrite();
            ((UriPermissionOwnerProto) this.instance).removeWritePerms(index);
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new UriPermissionOwnerProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                this.readPerms_.makeImmutable();
                this.writePerms_.makeImmutable();
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                UriPermissionOwnerProto other = (UriPermissionOwnerProto) arg1;
                this.owner_ = visitor.visitString(hasOwner(), this.owner_, other.hasOwner(), other.owner_);
                this.readPerms_ = visitor.visitList(this.readPerms_, other.readPerms_);
                this.writePerms_ = visitor.visitList(this.writePerms_, other.writePerms_);
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
                            String s = input.readString();
                            this.bitField0_ |= 1;
                            this.owner_ = s;
                        } else if (tag == 18) {
                            if (!this.readPerms_.isModifiable()) {
                                this.readPerms_ = GeneratedMessageLite.mutableCopy(this.readPerms_);
                            }
                            this.readPerms_.add((GrantUriProto) input.readMessage(GrantUriProto.parser(), extensionRegistry));
                        } else if (tag == 26) {
                            if (!this.writePerms_.isModifiable()) {
                                this.writePerms_ = GeneratedMessageLite.mutableCopy(this.writePerms_);
                            }
                            this.writePerms_.add((GrantUriProto) input.readMessage(GrantUriProto.parser(), extensionRegistry));
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
                    synchronized (UriPermissionOwnerProto.class) {
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

    public static UriPermissionOwnerProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<UriPermissionOwnerProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
