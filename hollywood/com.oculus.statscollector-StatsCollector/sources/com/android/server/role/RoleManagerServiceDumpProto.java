package com.android.server.role;

import com.android.server.role.RoleUserStateProto;
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

public final class RoleManagerServiceDumpProto extends GeneratedMessageLite<RoleManagerServiceDumpProto, Builder> implements RoleManagerServiceDumpProtoOrBuilder {
    private static final RoleManagerServiceDumpProto DEFAULT_INSTANCE = new RoleManagerServiceDumpProto();
    private static volatile Parser<RoleManagerServiceDumpProto> PARSER = null;
    public static final int USER_STATES_FIELD_NUMBER = 1;
    private Internal.ProtobufList<RoleUserStateProto> userStates_ = emptyProtobufList();

    private RoleManagerServiceDumpProto() {
    }

    @Override // com.android.server.role.RoleManagerServiceDumpProtoOrBuilder
    public List<RoleUserStateProto> getUserStatesList() {
        return this.userStates_;
    }

    public List<? extends RoleUserStateProtoOrBuilder> getUserStatesOrBuilderList() {
        return this.userStates_;
    }

    @Override // com.android.server.role.RoleManagerServiceDumpProtoOrBuilder
    public int getUserStatesCount() {
        return this.userStates_.size();
    }

    @Override // com.android.server.role.RoleManagerServiceDumpProtoOrBuilder
    public RoleUserStateProto getUserStates(int index) {
        return this.userStates_.get(index);
    }

    public RoleUserStateProtoOrBuilder getUserStatesOrBuilder(int index) {
        return this.userStates_.get(index);
    }

    private void ensureUserStatesIsMutable() {
        if (!this.userStates_.isModifiable()) {
            this.userStates_ = GeneratedMessageLite.mutableCopy(this.userStates_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setUserStates(int index, RoleUserStateProto value) {
        if (value != null) {
            ensureUserStatesIsMutable();
            this.userStates_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setUserStates(int index, RoleUserStateProto.Builder builderForValue) {
        ensureUserStatesIsMutable();
        this.userStates_.set(index, (RoleUserStateProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addUserStates(RoleUserStateProto value) {
        if (value != null) {
            ensureUserStatesIsMutable();
            this.userStates_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addUserStates(int index, RoleUserStateProto value) {
        if (value != null) {
            ensureUserStatesIsMutable();
            this.userStates_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addUserStates(RoleUserStateProto.Builder builderForValue) {
        ensureUserStatesIsMutable();
        this.userStates_.add((RoleUserStateProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addUserStates(int index, RoleUserStateProto.Builder builderForValue) {
        ensureUserStatesIsMutable();
        this.userStates_.add(index, (RoleUserStateProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllUserStates(Iterable<? extends RoleUserStateProto> values) {
        ensureUserStatesIsMutable();
        AbstractMessageLite.addAll(values, this.userStates_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearUserStates() {
        this.userStates_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeUserStates(int index) {
        ensureUserStatesIsMutable();
        this.userStates_.remove(index);
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        for (int i = 0; i < this.userStates_.size(); i++) {
            output.writeMessage(1, this.userStates_.get(i));
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
        for (int i = 0; i < this.userStates_.size(); i++) {
            size2 += CodedOutputStream.computeMessageSize(1, this.userStates_.get(i));
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static RoleManagerServiceDumpProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (RoleManagerServiceDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static RoleManagerServiceDumpProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (RoleManagerServiceDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static RoleManagerServiceDumpProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (RoleManagerServiceDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static RoleManagerServiceDumpProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (RoleManagerServiceDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static RoleManagerServiceDumpProto parseFrom(InputStream input) throws IOException {
        return (RoleManagerServiceDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static RoleManagerServiceDumpProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (RoleManagerServiceDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static RoleManagerServiceDumpProto parseDelimitedFrom(InputStream input) throws IOException {
        return (RoleManagerServiceDumpProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static RoleManagerServiceDumpProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (RoleManagerServiceDumpProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static RoleManagerServiceDumpProto parseFrom(CodedInputStream input) throws IOException {
        return (RoleManagerServiceDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static RoleManagerServiceDumpProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (RoleManagerServiceDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(RoleManagerServiceDumpProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<RoleManagerServiceDumpProto, Builder> implements RoleManagerServiceDumpProtoOrBuilder {
        private Builder() {
            super(RoleManagerServiceDumpProto.DEFAULT_INSTANCE);
        }

        @Override // com.android.server.role.RoleManagerServiceDumpProtoOrBuilder
        public List<RoleUserStateProto> getUserStatesList() {
            return Collections.unmodifiableList(((RoleManagerServiceDumpProto) this.instance).getUserStatesList());
        }

        @Override // com.android.server.role.RoleManagerServiceDumpProtoOrBuilder
        public int getUserStatesCount() {
            return ((RoleManagerServiceDumpProto) this.instance).getUserStatesCount();
        }

        @Override // com.android.server.role.RoleManagerServiceDumpProtoOrBuilder
        public RoleUserStateProto getUserStates(int index) {
            return ((RoleManagerServiceDumpProto) this.instance).getUserStates(index);
        }

        public Builder setUserStates(int index, RoleUserStateProto value) {
            copyOnWrite();
            ((RoleManagerServiceDumpProto) this.instance).setUserStates((RoleManagerServiceDumpProto) index, (int) value);
            return this;
        }

        public Builder setUserStates(int index, RoleUserStateProto.Builder builderForValue) {
            copyOnWrite();
            ((RoleManagerServiceDumpProto) this.instance).setUserStates((RoleManagerServiceDumpProto) index, (int) builderForValue);
            return this;
        }

        public Builder addUserStates(RoleUserStateProto value) {
            copyOnWrite();
            ((RoleManagerServiceDumpProto) this.instance).addUserStates((RoleManagerServiceDumpProto) value);
            return this;
        }

        public Builder addUserStates(int index, RoleUserStateProto value) {
            copyOnWrite();
            ((RoleManagerServiceDumpProto) this.instance).addUserStates((RoleManagerServiceDumpProto) index, (int) value);
            return this;
        }

        public Builder addUserStates(RoleUserStateProto.Builder builderForValue) {
            copyOnWrite();
            ((RoleManagerServiceDumpProto) this.instance).addUserStates((RoleManagerServiceDumpProto) builderForValue);
            return this;
        }

        public Builder addUserStates(int index, RoleUserStateProto.Builder builderForValue) {
            copyOnWrite();
            ((RoleManagerServiceDumpProto) this.instance).addUserStates((RoleManagerServiceDumpProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllUserStates(Iterable<? extends RoleUserStateProto> values) {
            copyOnWrite();
            ((RoleManagerServiceDumpProto) this.instance).addAllUserStates(values);
            return this;
        }

        public Builder clearUserStates() {
            copyOnWrite();
            ((RoleManagerServiceDumpProto) this.instance).clearUserStates();
            return this;
        }

        public Builder removeUserStates(int index) {
            copyOnWrite();
            ((RoleManagerServiceDumpProto) this.instance).removeUserStates(index);
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new RoleManagerServiceDumpProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                this.userStates_.makeImmutable();
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                this.userStates_ = ((GeneratedMessageLite.Visitor) arg0).visitList(this.userStates_, ((RoleManagerServiceDumpProto) arg1).userStates_);
                GeneratedMessageLite.MergeFromVisitor mergeFromVisitor = GeneratedMessageLite.MergeFromVisitor.INSTANCE;
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
                            if (!this.userStates_.isModifiable()) {
                                this.userStates_ = GeneratedMessageLite.mutableCopy(this.userStates_);
                            }
                            this.userStates_.add((RoleUserStateProto) input.readMessage(RoleUserStateProto.parser(), extensionRegistry));
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
                    synchronized (RoleManagerServiceDumpProto.class) {
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

    public static RoleManagerServiceDumpProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<RoleManagerServiceDumpProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
