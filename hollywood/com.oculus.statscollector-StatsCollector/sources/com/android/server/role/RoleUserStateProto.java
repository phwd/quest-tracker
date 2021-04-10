package com.android.server.role;

import com.android.server.role.RoleProto;
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

public final class RoleUserStateProto extends GeneratedMessageLite<RoleUserStateProto, Builder> implements RoleUserStateProtoOrBuilder {
    private static final RoleUserStateProto DEFAULT_INSTANCE = new RoleUserStateProto();
    public static final int PACKAGES_HASH_FIELD_NUMBER = 3;
    private static volatile Parser<RoleUserStateProto> PARSER = null;
    public static final int ROLES_FIELD_NUMBER = 4;
    public static final int USER_ID_FIELD_NUMBER = 1;
    public static final int VERSION_FIELD_NUMBER = 2;
    private int bitField0_;
    private String packagesHash_ = "";
    private Internal.ProtobufList<RoleProto> roles_ = emptyProtobufList();
    private int userId_ = 0;
    private int version_ = 0;

    private RoleUserStateProto() {
    }

    @Override // com.android.server.role.RoleUserStateProtoOrBuilder
    public boolean hasUserId() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // com.android.server.role.RoleUserStateProtoOrBuilder
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

    @Override // com.android.server.role.RoleUserStateProtoOrBuilder
    public boolean hasVersion() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // com.android.server.role.RoleUserStateProtoOrBuilder
    public int getVersion() {
        return this.version_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setVersion(int value) {
        this.bitField0_ |= 2;
        this.version_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearVersion() {
        this.bitField0_ &= -3;
        this.version_ = 0;
    }

    @Override // com.android.server.role.RoleUserStateProtoOrBuilder
    public boolean hasPackagesHash() {
        return (this.bitField0_ & 4) == 4;
    }

    @Override // com.android.server.role.RoleUserStateProtoOrBuilder
    public String getPackagesHash() {
        return this.packagesHash_;
    }

    @Override // com.android.server.role.RoleUserStateProtoOrBuilder
    public ByteString getPackagesHashBytes() {
        return ByteString.copyFromUtf8(this.packagesHash_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPackagesHash(String value) {
        if (value != null) {
            this.bitField0_ |= 4;
            this.packagesHash_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearPackagesHash() {
        this.bitField0_ &= -5;
        this.packagesHash_ = getDefaultInstance().getPackagesHash();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPackagesHashBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 4;
            this.packagesHash_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // com.android.server.role.RoleUserStateProtoOrBuilder
    public List<RoleProto> getRolesList() {
        return this.roles_;
    }

    public List<? extends RoleProtoOrBuilder> getRolesOrBuilderList() {
        return this.roles_;
    }

    @Override // com.android.server.role.RoleUserStateProtoOrBuilder
    public int getRolesCount() {
        return this.roles_.size();
    }

    @Override // com.android.server.role.RoleUserStateProtoOrBuilder
    public RoleProto getRoles(int index) {
        return this.roles_.get(index);
    }

    public RoleProtoOrBuilder getRolesOrBuilder(int index) {
        return this.roles_.get(index);
    }

    private void ensureRolesIsMutable() {
        if (!this.roles_.isModifiable()) {
            this.roles_ = GeneratedMessageLite.mutableCopy(this.roles_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setRoles(int index, RoleProto value) {
        if (value != null) {
            ensureRolesIsMutable();
            this.roles_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setRoles(int index, RoleProto.Builder builderForValue) {
        ensureRolesIsMutable();
        this.roles_.set(index, (RoleProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addRoles(RoleProto value) {
        if (value != null) {
            ensureRolesIsMutable();
            this.roles_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addRoles(int index, RoleProto value) {
        if (value != null) {
            ensureRolesIsMutable();
            this.roles_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addRoles(RoleProto.Builder builderForValue) {
        ensureRolesIsMutable();
        this.roles_.add((RoleProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addRoles(int index, RoleProto.Builder builderForValue) {
        ensureRolesIsMutable();
        this.roles_.add(index, (RoleProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllRoles(Iterable<? extends RoleProto> values) {
        ensureRolesIsMutable();
        AbstractMessageLite.addAll(values, this.roles_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearRoles() {
        this.roles_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeRoles(int index) {
        ensureRolesIsMutable();
        this.roles_.remove(index);
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeInt32(1, this.userId_);
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeInt32(2, this.version_);
        }
        if ((this.bitField0_ & 4) == 4) {
            output.writeString(3, getPackagesHash());
        }
        for (int i = 0; i < this.roles_.size(); i++) {
            output.writeMessage(4, this.roles_.get(i));
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
            size2 += CodedOutputStream.computeInt32Size(2, this.version_);
        }
        if ((this.bitField0_ & 4) == 4) {
            size2 += CodedOutputStream.computeStringSize(3, getPackagesHash());
        }
        for (int i = 0; i < this.roles_.size(); i++) {
            size2 += CodedOutputStream.computeMessageSize(4, this.roles_.get(i));
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static RoleUserStateProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (RoleUserStateProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static RoleUserStateProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (RoleUserStateProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static RoleUserStateProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (RoleUserStateProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static RoleUserStateProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (RoleUserStateProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static RoleUserStateProto parseFrom(InputStream input) throws IOException {
        return (RoleUserStateProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static RoleUserStateProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (RoleUserStateProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static RoleUserStateProto parseDelimitedFrom(InputStream input) throws IOException {
        return (RoleUserStateProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static RoleUserStateProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (RoleUserStateProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static RoleUserStateProto parseFrom(CodedInputStream input) throws IOException {
        return (RoleUserStateProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static RoleUserStateProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (RoleUserStateProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(RoleUserStateProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<RoleUserStateProto, Builder> implements RoleUserStateProtoOrBuilder {
        private Builder() {
            super(RoleUserStateProto.DEFAULT_INSTANCE);
        }

        @Override // com.android.server.role.RoleUserStateProtoOrBuilder
        public boolean hasUserId() {
            return ((RoleUserStateProto) this.instance).hasUserId();
        }

        @Override // com.android.server.role.RoleUserStateProtoOrBuilder
        public int getUserId() {
            return ((RoleUserStateProto) this.instance).getUserId();
        }

        public Builder setUserId(int value) {
            copyOnWrite();
            ((RoleUserStateProto) this.instance).setUserId(value);
            return this;
        }

        public Builder clearUserId() {
            copyOnWrite();
            ((RoleUserStateProto) this.instance).clearUserId();
            return this;
        }

        @Override // com.android.server.role.RoleUserStateProtoOrBuilder
        public boolean hasVersion() {
            return ((RoleUserStateProto) this.instance).hasVersion();
        }

        @Override // com.android.server.role.RoleUserStateProtoOrBuilder
        public int getVersion() {
            return ((RoleUserStateProto) this.instance).getVersion();
        }

        public Builder setVersion(int value) {
            copyOnWrite();
            ((RoleUserStateProto) this.instance).setVersion(value);
            return this;
        }

        public Builder clearVersion() {
            copyOnWrite();
            ((RoleUserStateProto) this.instance).clearVersion();
            return this;
        }

        @Override // com.android.server.role.RoleUserStateProtoOrBuilder
        public boolean hasPackagesHash() {
            return ((RoleUserStateProto) this.instance).hasPackagesHash();
        }

        @Override // com.android.server.role.RoleUserStateProtoOrBuilder
        public String getPackagesHash() {
            return ((RoleUserStateProto) this.instance).getPackagesHash();
        }

        @Override // com.android.server.role.RoleUserStateProtoOrBuilder
        public ByteString getPackagesHashBytes() {
            return ((RoleUserStateProto) this.instance).getPackagesHashBytes();
        }

        public Builder setPackagesHash(String value) {
            copyOnWrite();
            ((RoleUserStateProto) this.instance).setPackagesHash(value);
            return this;
        }

        public Builder clearPackagesHash() {
            copyOnWrite();
            ((RoleUserStateProto) this.instance).clearPackagesHash();
            return this;
        }

        public Builder setPackagesHashBytes(ByteString value) {
            copyOnWrite();
            ((RoleUserStateProto) this.instance).setPackagesHashBytes(value);
            return this;
        }

        @Override // com.android.server.role.RoleUserStateProtoOrBuilder
        public List<RoleProto> getRolesList() {
            return Collections.unmodifiableList(((RoleUserStateProto) this.instance).getRolesList());
        }

        @Override // com.android.server.role.RoleUserStateProtoOrBuilder
        public int getRolesCount() {
            return ((RoleUserStateProto) this.instance).getRolesCount();
        }

        @Override // com.android.server.role.RoleUserStateProtoOrBuilder
        public RoleProto getRoles(int index) {
            return ((RoleUserStateProto) this.instance).getRoles(index);
        }

        public Builder setRoles(int index, RoleProto value) {
            copyOnWrite();
            ((RoleUserStateProto) this.instance).setRoles((RoleUserStateProto) index, (int) value);
            return this;
        }

        public Builder setRoles(int index, RoleProto.Builder builderForValue) {
            copyOnWrite();
            ((RoleUserStateProto) this.instance).setRoles((RoleUserStateProto) index, (int) builderForValue);
            return this;
        }

        public Builder addRoles(RoleProto value) {
            copyOnWrite();
            ((RoleUserStateProto) this.instance).addRoles((RoleUserStateProto) value);
            return this;
        }

        public Builder addRoles(int index, RoleProto value) {
            copyOnWrite();
            ((RoleUserStateProto) this.instance).addRoles((RoleUserStateProto) index, (int) value);
            return this;
        }

        public Builder addRoles(RoleProto.Builder builderForValue) {
            copyOnWrite();
            ((RoleUserStateProto) this.instance).addRoles((RoleUserStateProto) builderForValue);
            return this;
        }

        public Builder addRoles(int index, RoleProto.Builder builderForValue) {
            copyOnWrite();
            ((RoleUserStateProto) this.instance).addRoles((RoleUserStateProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllRoles(Iterable<? extends RoleProto> values) {
            copyOnWrite();
            ((RoleUserStateProto) this.instance).addAllRoles(values);
            return this;
        }

        public Builder clearRoles() {
            copyOnWrite();
            ((RoleUserStateProto) this.instance).clearRoles();
            return this;
        }

        public Builder removeRoles(int index) {
            copyOnWrite();
            ((RoleUserStateProto) this.instance).removeRoles(index);
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new RoleUserStateProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                this.roles_.makeImmutable();
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                RoleUserStateProto other = (RoleUserStateProto) arg1;
                this.userId_ = visitor.visitInt(hasUserId(), this.userId_, other.hasUserId(), other.userId_);
                this.version_ = visitor.visitInt(hasVersion(), this.version_, other.hasVersion(), other.version_);
                this.packagesHash_ = visitor.visitString(hasPackagesHash(), this.packagesHash_, other.hasPackagesHash(), other.packagesHash_);
                this.roles_ = visitor.visitList(this.roles_, other.roles_);
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
                        } else if (tag == 8) {
                            this.bitField0_ |= 1;
                            this.userId_ = input.readInt32();
                        } else if (tag == 16) {
                            this.bitField0_ |= 2;
                            this.version_ = input.readInt32();
                        } else if (tag == 26) {
                            String s = input.readString();
                            this.bitField0_ |= 4;
                            this.packagesHash_ = s;
                        } else if (tag == 34) {
                            if (!this.roles_.isModifiable()) {
                                this.roles_ = GeneratedMessageLite.mutableCopy(this.roles_);
                            }
                            this.roles_.add((RoleProto) input.readMessage(RoleProto.parser(), extensionRegistry));
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
                    synchronized (RoleUserStateProto.class) {
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

    public static RoleUserStateProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<RoleUserStateProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
