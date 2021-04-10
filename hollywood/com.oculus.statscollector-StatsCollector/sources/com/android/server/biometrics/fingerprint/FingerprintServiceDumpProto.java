package com.android.server.biometrics.fingerprint;

import com.android.server.biometrics.fingerprint.FingerprintUserStatsProto;
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

public final class FingerprintServiceDumpProto extends GeneratedMessageLite<FingerprintServiceDumpProto, Builder> implements FingerprintServiceDumpProtoOrBuilder {
    private static final FingerprintServiceDumpProto DEFAULT_INSTANCE = new FingerprintServiceDumpProto();
    private static volatile Parser<FingerprintServiceDumpProto> PARSER = null;
    public static final int USERS_FIELD_NUMBER = 1;
    private Internal.ProtobufList<FingerprintUserStatsProto> users_ = emptyProtobufList();

    private FingerprintServiceDumpProto() {
    }

    @Override // com.android.server.biometrics.fingerprint.FingerprintServiceDumpProtoOrBuilder
    public List<FingerprintUserStatsProto> getUsersList() {
        return this.users_;
    }

    public List<? extends FingerprintUserStatsProtoOrBuilder> getUsersOrBuilderList() {
        return this.users_;
    }

    @Override // com.android.server.biometrics.fingerprint.FingerprintServiceDumpProtoOrBuilder
    public int getUsersCount() {
        return this.users_.size();
    }

    @Override // com.android.server.biometrics.fingerprint.FingerprintServiceDumpProtoOrBuilder
    public FingerprintUserStatsProto getUsers(int index) {
        return this.users_.get(index);
    }

    public FingerprintUserStatsProtoOrBuilder getUsersOrBuilder(int index) {
        return this.users_.get(index);
    }

    private void ensureUsersIsMutable() {
        if (!this.users_.isModifiable()) {
            this.users_ = GeneratedMessageLite.mutableCopy(this.users_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setUsers(int index, FingerprintUserStatsProto value) {
        if (value != null) {
            ensureUsersIsMutable();
            this.users_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setUsers(int index, FingerprintUserStatsProto.Builder builderForValue) {
        ensureUsersIsMutable();
        this.users_.set(index, (FingerprintUserStatsProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addUsers(FingerprintUserStatsProto value) {
        if (value != null) {
            ensureUsersIsMutable();
            this.users_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addUsers(int index, FingerprintUserStatsProto value) {
        if (value != null) {
            ensureUsersIsMutable();
            this.users_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addUsers(FingerprintUserStatsProto.Builder builderForValue) {
        ensureUsersIsMutable();
        this.users_.add((FingerprintUserStatsProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addUsers(int index, FingerprintUserStatsProto.Builder builderForValue) {
        ensureUsersIsMutable();
        this.users_.add(index, (FingerprintUserStatsProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllUsers(Iterable<? extends FingerprintUserStatsProto> values) {
        ensureUsersIsMutable();
        AbstractMessageLite.addAll(values, this.users_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearUsers() {
        this.users_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeUsers(int index) {
        ensureUsersIsMutable();
        this.users_.remove(index);
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        for (int i = 0; i < this.users_.size(); i++) {
            output.writeMessage(1, this.users_.get(i));
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
        for (int i = 0; i < this.users_.size(); i++) {
            size2 += CodedOutputStream.computeMessageSize(1, this.users_.get(i));
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static FingerprintServiceDumpProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (FingerprintServiceDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static FingerprintServiceDumpProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (FingerprintServiceDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static FingerprintServiceDumpProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (FingerprintServiceDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static FingerprintServiceDumpProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (FingerprintServiceDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static FingerprintServiceDumpProto parseFrom(InputStream input) throws IOException {
        return (FingerprintServiceDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static FingerprintServiceDumpProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (FingerprintServiceDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static FingerprintServiceDumpProto parseDelimitedFrom(InputStream input) throws IOException {
        return (FingerprintServiceDumpProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static FingerprintServiceDumpProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (FingerprintServiceDumpProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static FingerprintServiceDumpProto parseFrom(CodedInputStream input) throws IOException {
        return (FingerprintServiceDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static FingerprintServiceDumpProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (FingerprintServiceDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(FingerprintServiceDumpProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<FingerprintServiceDumpProto, Builder> implements FingerprintServiceDumpProtoOrBuilder {
        private Builder() {
            super(FingerprintServiceDumpProto.DEFAULT_INSTANCE);
        }

        @Override // com.android.server.biometrics.fingerprint.FingerprintServiceDumpProtoOrBuilder
        public List<FingerprintUserStatsProto> getUsersList() {
            return Collections.unmodifiableList(((FingerprintServiceDumpProto) this.instance).getUsersList());
        }

        @Override // com.android.server.biometrics.fingerprint.FingerprintServiceDumpProtoOrBuilder
        public int getUsersCount() {
            return ((FingerprintServiceDumpProto) this.instance).getUsersCount();
        }

        @Override // com.android.server.biometrics.fingerprint.FingerprintServiceDumpProtoOrBuilder
        public FingerprintUserStatsProto getUsers(int index) {
            return ((FingerprintServiceDumpProto) this.instance).getUsers(index);
        }

        public Builder setUsers(int index, FingerprintUserStatsProto value) {
            copyOnWrite();
            ((FingerprintServiceDumpProto) this.instance).setUsers((FingerprintServiceDumpProto) index, (int) value);
            return this;
        }

        public Builder setUsers(int index, FingerprintUserStatsProto.Builder builderForValue) {
            copyOnWrite();
            ((FingerprintServiceDumpProto) this.instance).setUsers((FingerprintServiceDumpProto) index, (int) builderForValue);
            return this;
        }

        public Builder addUsers(FingerprintUserStatsProto value) {
            copyOnWrite();
            ((FingerprintServiceDumpProto) this.instance).addUsers((FingerprintServiceDumpProto) value);
            return this;
        }

        public Builder addUsers(int index, FingerprintUserStatsProto value) {
            copyOnWrite();
            ((FingerprintServiceDumpProto) this.instance).addUsers((FingerprintServiceDumpProto) index, (int) value);
            return this;
        }

        public Builder addUsers(FingerprintUserStatsProto.Builder builderForValue) {
            copyOnWrite();
            ((FingerprintServiceDumpProto) this.instance).addUsers((FingerprintServiceDumpProto) builderForValue);
            return this;
        }

        public Builder addUsers(int index, FingerprintUserStatsProto.Builder builderForValue) {
            copyOnWrite();
            ((FingerprintServiceDumpProto) this.instance).addUsers((FingerprintServiceDumpProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllUsers(Iterable<? extends FingerprintUserStatsProto> values) {
            copyOnWrite();
            ((FingerprintServiceDumpProto) this.instance).addAllUsers(values);
            return this;
        }

        public Builder clearUsers() {
            copyOnWrite();
            ((FingerprintServiceDumpProto) this.instance).clearUsers();
            return this;
        }

        public Builder removeUsers(int index) {
            copyOnWrite();
            ((FingerprintServiceDumpProto) this.instance).removeUsers(index);
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new FingerprintServiceDumpProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                this.users_.makeImmutable();
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                this.users_ = ((GeneratedMessageLite.Visitor) arg0).visitList(this.users_, ((FingerprintServiceDumpProto) arg1).users_);
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
                            if (!this.users_.isModifiable()) {
                                this.users_ = GeneratedMessageLite.mutableCopy(this.users_);
                            }
                            this.users_.add((FingerprintUserStatsProto) input.readMessage(FingerprintUserStatsProto.parser(), extensionRegistry));
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
                    synchronized (FingerprintServiceDumpProto.class) {
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

    public static FingerprintServiceDumpProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<FingerprintServiceDumpProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
