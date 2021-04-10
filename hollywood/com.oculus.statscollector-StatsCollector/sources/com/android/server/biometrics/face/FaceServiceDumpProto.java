package com.android.server.biometrics.face;

import com.android.server.biometrics.face.FaceUserStatsProto;
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

public final class FaceServiceDumpProto extends GeneratedMessageLite<FaceServiceDumpProto, Builder> implements FaceServiceDumpProtoOrBuilder {
    private static final FaceServiceDumpProto DEFAULT_INSTANCE = new FaceServiceDumpProto();
    private static volatile Parser<FaceServiceDumpProto> PARSER = null;
    public static final int USERS_FIELD_NUMBER = 1;
    private Internal.ProtobufList<FaceUserStatsProto> users_ = emptyProtobufList();

    private FaceServiceDumpProto() {
    }

    @Override // com.android.server.biometrics.face.FaceServiceDumpProtoOrBuilder
    public List<FaceUserStatsProto> getUsersList() {
        return this.users_;
    }

    public List<? extends FaceUserStatsProtoOrBuilder> getUsersOrBuilderList() {
        return this.users_;
    }

    @Override // com.android.server.biometrics.face.FaceServiceDumpProtoOrBuilder
    public int getUsersCount() {
        return this.users_.size();
    }

    @Override // com.android.server.biometrics.face.FaceServiceDumpProtoOrBuilder
    public FaceUserStatsProto getUsers(int index) {
        return this.users_.get(index);
    }

    public FaceUserStatsProtoOrBuilder getUsersOrBuilder(int index) {
        return this.users_.get(index);
    }

    private void ensureUsersIsMutable() {
        if (!this.users_.isModifiable()) {
            this.users_ = GeneratedMessageLite.mutableCopy(this.users_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setUsers(int index, FaceUserStatsProto value) {
        if (value != null) {
            ensureUsersIsMutable();
            this.users_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setUsers(int index, FaceUserStatsProto.Builder builderForValue) {
        ensureUsersIsMutable();
        this.users_.set(index, (FaceUserStatsProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addUsers(FaceUserStatsProto value) {
        if (value != null) {
            ensureUsersIsMutable();
            this.users_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addUsers(int index, FaceUserStatsProto value) {
        if (value != null) {
            ensureUsersIsMutable();
            this.users_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addUsers(FaceUserStatsProto.Builder builderForValue) {
        ensureUsersIsMutable();
        this.users_.add((FaceUserStatsProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addUsers(int index, FaceUserStatsProto.Builder builderForValue) {
        ensureUsersIsMutable();
        this.users_.add(index, (FaceUserStatsProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllUsers(Iterable<? extends FaceUserStatsProto> values) {
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

    public static FaceServiceDumpProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (FaceServiceDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static FaceServiceDumpProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (FaceServiceDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static FaceServiceDumpProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (FaceServiceDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static FaceServiceDumpProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (FaceServiceDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static FaceServiceDumpProto parseFrom(InputStream input) throws IOException {
        return (FaceServiceDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static FaceServiceDumpProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (FaceServiceDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static FaceServiceDumpProto parseDelimitedFrom(InputStream input) throws IOException {
        return (FaceServiceDumpProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static FaceServiceDumpProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (FaceServiceDumpProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static FaceServiceDumpProto parseFrom(CodedInputStream input) throws IOException {
        return (FaceServiceDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static FaceServiceDumpProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (FaceServiceDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(FaceServiceDumpProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<FaceServiceDumpProto, Builder> implements FaceServiceDumpProtoOrBuilder {
        private Builder() {
            super(FaceServiceDumpProto.DEFAULT_INSTANCE);
        }

        @Override // com.android.server.biometrics.face.FaceServiceDumpProtoOrBuilder
        public List<FaceUserStatsProto> getUsersList() {
            return Collections.unmodifiableList(((FaceServiceDumpProto) this.instance).getUsersList());
        }

        @Override // com.android.server.biometrics.face.FaceServiceDumpProtoOrBuilder
        public int getUsersCount() {
            return ((FaceServiceDumpProto) this.instance).getUsersCount();
        }

        @Override // com.android.server.biometrics.face.FaceServiceDumpProtoOrBuilder
        public FaceUserStatsProto getUsers(int index) {
            return ((FaceServiceDumpProto) this.instance).getUsers(index);
        }

        public Builder setUsers(int index, FaceUserStatsProto value) {
            copyOnWrite();
            ((FaceServiceDumpProto) this.instance).setUsers((FaceServiceDumpProto) index, (int) value);
            return this;
        }

        public Builder setUsers(int index, FaceUserStatsProto.Builder builderForValue) {
            copyOnWrite();
            ((FaceServiceDumpProto) this.instance).setUsers((FaceServiceDumpProto) index, (int) builderForValue);
            return this;
        }

        public Builder addUsers(FaceUserStatsProto value) {
            copyOnWrite();
            ((FaceServiceDumpProto) this.instance).addUsers((FaceServiceDumpProto) value);
            return this;
        }

        public Builder addUsers(int index, FaceUserStatsProto value) {
            copyOnWrite();
            ((FaceServiceDumpProto) this.instance).addUsers((FaceServiceDumpProto) index, (int) value);
            return this;
        }

        public Builder addUsers(FaceUserStatsProto.Builder builderForValue) {
            copyOnWrite();
            ((FaceServiceDumpProto) this.instance).addUsers((FaceServiceDumpProto) builderForValue);
            return this;
        }

        public Builder addUsers(int index, FaceUserStatsProto.Builder builderForValue) {
            copyOnWrite();
            ((FaceServiceDumpProto) this.instance).addUsers((FaceServiceDumpProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllUsers(Iterable<? extends FaceUserStatsProto> values) {
            copyOnWrite();
            ((FaceServiceDumpProto) this.instance).addAllUsers(values);
            return this;
        }

        public Builder clearUsers() {
            copyOnWrite();
            ((FaceServiceDumpProto) this.instance).clearUsers();
            return this;
        }

        public Builder removeUsers(int index) {
            copyOnWrite();
            ((FaceServiceDumpProto) this.instance).removeUsers(index);
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new FaceServiceDumpProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                this.users_.makeImmutable();
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                this.users_ = ((GeneratedMessageLite.Visitor) arg0).visitList(this.users_, ((FaceServiceDumpProto) arg1).users_);
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
                            this.users_.add((FaceUserStatsProto) input.readMessage(FaceUserStatsProto.parser(), extensionRegistry));
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
                    synchronized (FaceServiceDumpProto.class) {
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

    public static FaceServiceDumpProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<FaceServiceDumpProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
