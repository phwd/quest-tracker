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

public final class NeededUriGrantsProto extends GeneratedMessageLite<NeededUriGrantsProto, Builder> implements NeededUriGrantsProtoOrBuilder {
    private static final NeededUriGrantsProto DEFAULT_INSTANCE = new NeededUriGrantsProto();
    public static final int FLAGS_FIELD_NUMBER = 3;
    public static final int GRANTS_FIELD_NUMBER = 4;
    private static volatile Parser<NeededUriGrantsProto> PARSER = null;
    public static final int TARGET_PACKAGE_FIELD_NUMBER = 1;
    public static final int TARGET_UID_FIELD_NUMBER = 2;
    private int bitField0_;
    private int flags_ = 0;
    private Internal.ProtobufList<GrantUriProto> grants_ = emptyProtobufList();
    private String targetPackage_ = "";
    private int targetUid_ = 0;

    private NeededUriGrantsProto() {
    }

    @Override // com.android.server.am.NeededUriGrantsProtoOrBuilder
    public boolean hasTargetPackage() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // com.android.server.am.NeededUriGrantsProtoOrBuilder
    public String getTargetPackage() {
        return this.targetPackage_;
    }

    @Override // com.android.server.am.NeededUriGrantsProtoOrBuilder
    public ByteString getTargetPackageBytes() {
        return ByteString.copyFromUtf8(this.targetPackage_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setTargetPackage(String value) {
        if (value != null) {
            this.bitField0_ |= 1;
            this.targetPackage_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearTargetPackage() {
        this.bitField0_ &= -2;
        this.targetPackage_ = getDefaultInstance().getTargetPackage();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setTargetPackageBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 1;
            this.targetPackage_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // com.android.server.am.NeededUriGrantsProtoOrBuilder
    public boolean hasTargetUid() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // com.android.server.am.NeededUriGrantsProtoOrBuilder
    public int getTargetUid() {
        return this.targetUid_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setTargetUid(int value) {
        this.bitField0_ |= 2;
        this.targetUid_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearTargetUid() {
        this.bitField0_ &= -3;
        this.targetUid_ = 0;
    }

    @Override // com.android.server.am.NeededUriGrantsProtoOrBuilder
    public boolean hasFlags() {
        return (this.bitField0_ & 4) == 4;
    }

    @Override // com.android.server.am.NeededUriGrantsProtoOrBuilder
    public int getFlags() {
        return this.flags_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setFlags(int value) {
        this.bitField0_ |= 4;
        this.flags_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearFlags() {
        this.bitField0_ &= -5;
        this.flags_ = 0;
    }

    @Override // com.android.server.am.NeededUriGrantsProtoOrBuilder
    public List<GrantUriProto> getGrantsList() {
        return this.grants_;
    }

    public List<? extends GrantUriProtoOrBuilder> getGrantsOrBuilderList() {
        return this.grants_;
    }

    @Override // com.android.server.am.NeededUriGrantsProtoOrBuilder
    public int getGrantsCount() {
        return this.grants_.size();
    }

    @Override // com.android.server.am.NeededUriGrantsProtoOrBuilder
    public GrantUriProto getGrants(int index) {
        return this.grants_.get(index);
    }

    public GrantUriProtoOrBuilder getGrantsOrBuilder(int index) {
        return this.grants_.get(index);
    }

    private void ensureGrantsIsMutable() {
        if (!this.grants_.isModifiable()) {
            this.grants_ = GeneratedMessageLite.mutableCopy(this.grants_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setGrants(int index, GrantUriProto value) {
        if (value != null) {
            ensureGrantsIsMutable();
            this.grants_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setGrants(int index, GrantUriProto.Builder builderForValue) {
        ensureGrantsIsMutable();
        this.grants_.set(index, (GrantUriProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addGrants(GrantUriProto value) {
        if (value != null) {
            ensureGrantsIsMutable();
            this.grants_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addGrants(int index, GrantUriProto value) {
        if (value != null) {
            ensureGrantsIsMutable();
            this.grants_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addGrants(GrantUriProto.Builder builderForValue) {
        ensureGrantsIsMutable();
        this.grants_.add((GrantUriProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addGrants(int index, GrantUriProto.Builder builderForValue) {
        ensureGrantsIsMutable();
        this.grants_.add(index, (GrantUriProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllGrants(Iterable<? extends GrantUriProto> values) {
        ensureGrantsIsMutable();
        AbstractMessageLite.addAll(values, this.grants_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearGrants() {
        this.grants_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeGrants(int index) {
        ensureGrantsIsMutable();
        this.grants_.remove(index);
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeString(1, getTargetPackage());
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeInt32(2, this.targetUid_);
        }
        if ((this.bitField0_ & 4) == 4) {
            output.writeInt32(3, this.flags_);
        }
        for (int i = 0; i < this.grants_.size(); i++) {
            output.writeMessage(4, this.grants_.get(i));
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
            size2 = 0 + CodedOutputStream.computeStringSize(1, getTargetPackage());
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeInt32Size(2, this.targetUid_);
        }
        if ((this.bitField0_ & 4) == 4) {
            size2 += CodedOutputStream.computeInt32Size(3, this.flags_);
        }
        for (int i = 0; i < this.grants_.size(); i++) {
            size2 += CodedOutputStream.computeMessageSize(4, this.grants_.get(i));
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static NeededUriGrantsProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (NeededUriGrantsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static NeededUriGrantsProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (NeededUriGrantsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static NeededUriGrantsProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (NeededUriGrantsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static NeededUriGrantsProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (NeededUriGrantsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static NeededUriGrantsProto parseFrom(InputStream input) throws IOException {
        return (NeededUriGrantsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static NeededUriGrantsProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (NeededUriGrantsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static NeededUriGrantsProto parseDelimitedFrom(InputStream input) throws IOException {
        return (NeededUriGrantsProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static NeededUriGrantsProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (NeededUriGrantsProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static NeededUriGrantsProto parseFrom(CodedInputStream input) throws IOException {
        return (NeededUriGrantsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static NeededUriGrantsProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (NeededUriGrantsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(NeededUriGrantsProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<NeededUriGrantsProto, Builder> implements NeededUriGrantsProtoOrBuilder {
        private Builder() {
            super(NeededUriGrantsProto.DEFAULT_INSTANCE);
        }

        @Override // com.android.server.am.NeededUriGrantsProtoOrBuilder
        public boolean hasTargetPackage() {
            return ((NeededUriGrantsProto) this.instance).hasTargetPackage();
        }

        @Override // com.android.server.am.NeededUriGrantsProtoOrBuilder
        public String getTargetPackage() {
            return ((NeededUriGrantsProto) this.instance).getTargetPackage();
        }

        @Override // com.android.server.am.NeededUriGrantsProtoOrBuilder
        public ByteString getTargetPackageBytes() {
            return ((NeededUriGrantsProto) this.instance).getTargetPackageBytes();
        }

        public Builder setTargetPackage(String value) {
            copyOnWrite();
            ((NeededUriGrantsProto) this.instance).setTargetPackage(value);
            return this;
        }

        public Builder clearTargetPackage() {
            copyOnWrite();
            ((NeededUriGrantsProto) this.instance).clearTargetPackage();
            return this;
        }

        public Builder setTargetPackageBytes(ByteString value) {
            copyOnWrite();
            ((NeededUriGrantsProto) this.instance).setTargetPackageBytes(value);
            return this;
        }

        @Override // com.android.server.am.NeededUriGrantsProtoOrBuilder
        public boolean hasTargetUid() {
            return ((NeededUriGrantsProto) this.instance).hasTargetUid();
        }

        @Override // com.android.server.am.NeededUriGrantsProtoOrBuilder
        public int getTargetUid() {
            return ((NeededUriGrantsProto) this.instance).getTargetUid();
        }

        public Builder setTargetUid(int value) {
            copyOnWrite();
            ((NeededUriGrantsProto) this.instance).setTargetUid(value);
            return this;
        }

        public Builder clearTargetUid() {
            copyOnWrite();
            ((NeededUriGrantsProto) this.instance).clearTargetUid();
            return this;
        }

        @Override // com.android.server.am.NeededUriGrantsProtoOrBuilder
        public boolean hasFlags() {
            return ((NeededUriGrantsProto) this.instance).hasFlags();
        }

        @Override // com.android.server.am.NeededUriGrantsProtoOrBuilder
        public int getFlags() {
            return ((NeededUriGrantsProto) this.instance).getFlags();
        }

        public Builder setFlags(int value) {
            copyOnWrite();
            ((NeededUriGrantsProto) this.instance).setFlags(value);
            return this;
        }

        public Builder clearFlags() {
            copyOnWrite();
            ((NeededUriGrantsProto) this.instance).clearFlags();
            return this;
        }

        @Override // com.android.server.am.NeededUriGrantsProtoOrBuilder
        public List<GrantUriProto> getGrantsList() {
            return Collections.unmodifiableList(((NeededUriGrantsProto) this.instance).getGrantsList());
        }

        @Override // com.android.server.am.NeededUriGrantsProtoOrBuilder
        public int getGrantsCount() {
            return ((NeededUriGrantsProto) this.instance).getGrantsCount();
        }

        @Override // com.android.server.am.NeededUriGrantsProtoOrBuilder
        public GrantUriProto getGrants(int index) {
            return ((NeededUriGrantsProto) this.instance).getGrants(index);
        }

        public Builder setGrants(int index, GrantUriProto value) {
            copyOnWrite();
            ((NeededUriGrantsProto) this.instance).setGrants((NeededUriGrantsProto) index, (int) value);
            return this;
        }

        public Builder setGrants(int index, GrantUriProto.Builder builderForValue) {
            copyOnWrite();
            ((NeededUriGrantsProto) this.instance).setGrants((NeededUriGrantsProto) index, (int) builderForValue);
            return this;
        }

        public Builder addGrants(GrantUriProto value) {
            copyOnWrite();
            ((NeededUriGrantsProto) this.instance).addGrants((NeededUriGrantsProto) value);
            return this;
        }

        public Builder addGrants(int index, GrantUriProto value) {
            copyOnWrite();
            ((NeededUriGrantsProto) this.instance).addGrants((NeededUriGrantsProto) index, (int) value);
            return this;
        }

        public Builder addGrants(GrantUriProto.Builder builderForValue) {
            copyOnWrite();
            ((NeededUriGrantsProto) this.instance).addGrants((NeededUriGrantsProto) builderForValue);
            return this;
        }

        public Builder addGrants(int index, GrantUriProto.Builder builderForValue) {
            copyOnWrite();
            ((NeededUriGrantsProto) this.instance).addGrants((NeededUriGrantsProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllGrants(Iterable<? extends GrantUriProto> values) {
            copyOnWrite();
            ((NeededUriGrantsProto) this.instance).addAllGrants(values);
            return this;
        }

        public Builder clearGrants() {
            copyOnWrite();
            ((NeededUriGrantsProto) this.instance).clearGrants();
            return this;
        }

        public Builder removeGrants(int index) {
            copyOnWrite();
            ((NeededUriGrantsProto) this.instance).removeGrants(index);
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new NeededUriGrantsProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                this.grants_.makeImmutable();
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                NeededUriGrantsProto other = (NeededUriGrantsProto) arg1;
                this.targetPackage_ = visitor.visitString(hasTargetPackage(), this.targetPackage_, other.hasTargetPackage(), other.targetPackage_);
                this.targetUid_ = visitor.visitInt(hasTargetUid(), this.targetUid_, other.hasTargetUid(), other.targetUid_);
                this.flags_ = visitor.visitInt(hasFlags(), this.flags_, other.hasFlags(), other.flags_);
                this.grants_ = visitor.visitList(this.grants_, other.grants_);
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
                            this.targetPackage_ = s;
                        } else if (tag == 16) {
                            this.bitField0_ |= 2;
                            this.targetUid_ = input.readInt32();
                        } else if (tag == 24) {
                            this.bitField0_ |= 4;
                            this.flags_ = input.readInt32();
                        } else if (tag == 34) {
                            if (!this.grants_.isModifiable()) {
                                this.grants_ = GeneratedMessageLite.mutableCopy(this.grants_);
                            }
                            this.grants_.add((GrantUriProto) input.readMessage(GrantUriProto.parser(), extensionRegistry));
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
                    synchronized (NeededUriGrantsProto.class) {
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

    public static NeededUriGrantsProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<NeededUriGrantsProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
