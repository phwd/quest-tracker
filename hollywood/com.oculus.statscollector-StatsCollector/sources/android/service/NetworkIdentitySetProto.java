package android.service;

import android.service.NetworkIdentityProto;
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

public final class NetworkIdentitySetProto extends GeneratedMessageLite<NetworkIdentitySetProto, Builder> implements NetworkIdentitySetProtoOrBuilder {
    private static final NetworkIdentitySetProto DEFAULT_INSTANCE = new NetworkIdentitySetProto();
    public static final int IDENTITIES_FIELD_NUMBER = 1;
    private static volatile Parser<NetworkIdentitySetProto> PARSER;
    private Internal.ProtobufList<NetworkIdentityProto> identities_ = emptyProtobufList();

    private NetworkIdentitySetProto() {
    }

    @Override // android.service.NetworkIdentitySetProtoOrBuilder
    public List<NetworkIdentityProto> getIdentitiesList() {
        return this.identities_;
    }

    public List<? extends NetworkIdentityProtoOrBuilder> getIdentitiesOrBuilderList() {
        return this.identities_;
    }

    @Override // android.service.NetworkIdentitySetProtoOrBuilder
    public int getIdentitiesCount() {
        return this.identities_.size();
    }

    @Override // android.service.NetworkIdentitySetProtoOrBuilder
    public NetworkIdentityProto getIdentities(int index) {
        return this.identities_.get(index);
    }

    public NetworkIdentityProtoOrBuilder getIdentitiesOrBuilder(int index) {
        return this.identities_.get(index);
    }

    private void ensureIdentitiesIsMutable() {
        if (!this.identities_.isModifiable()) {
            this.identities_ = GeneratedMessageLite.mutableCopy(this.identities_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setIdentities(int index, NetworkIdentityProto value) {
        if (value != null) {
            ensureIdentitiesIsMutable();
            this.identities_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setIdentities(int index, NetworkIdentityProto.Builder builderForValue) {
        ensureIdentitiesIsMutable();
        this.identities_.set(index, (NetworkIdentityProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addIdentities(NetworkIdentityProto value) {
        if (value != null) {
            ensureIdentitiesIsMutable();
            this.identities_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addIdentities(int index, NetworkIdentityProto value) {
        if (value != null) {
            ensureIdentitiesIsMutable();
            this.identities_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addIdentities(NetworkIdentityProto.Builder builderForValue) {
        ensureIdentitiesIsMutable();
        this.identities_.add((NetworkIdentityProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addIdentities(int index, NetworkIdentityProto.Builder builderForValue) {
        ensureIdentitiesIsMutable();
        this.identities_.add(index, (NetworkIdentityProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllIdentities(Iterable<? extends NetworkIdentityProto> values) {
        ensureIdentitiesIsMutable();
        AbstractMessageLite.addAll(values, this.identities_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearIdentities() {
        this.identities_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeIdentities(int index) {
        ensureIdentitiesIsMutable();
        this.identities_.remove(index);
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        for (int i = 0; i < this.identities_.size(); i++) {
            output.writeMessage(1, this.identities_.get(i));
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
        for (int i = 0; i < this.identities_.size(); i++) {
            size2 += CodedOutputStream.computeMessageSize(1, this.identities_.get(i));
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static NetworkIdentitySetProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (NetworkIdentitySetProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static NetworkIdentitySetProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (NetworkIdentitySetProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static NetworkIdentitySetProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (NetworkIdentitySetProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static NetworkIdentitySetProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (NetworkIdentitySetProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static NetworkIdentitySetProto parseFrom(InputStream input) throws IOException {
        return (NetworkIdentitySetProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static NetworkIdentitySetProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (NetworkIdentitySetProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static NetworkIdentitySetProto parseDelimitedFrom(InputStream input) throws IOException {
        return (NetworkIdentitySetProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static NetworkIdentitySetProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (NetworkIdentitySetProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static NetworkIdentitySetProto parseFrom(CodedInputStream input) throws IOException {
        return (NetworkIdentitySetProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static NetworkIdentitySetProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (NetworkIdentitySetProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(NetworkIdentitySetProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<NetworkIdentitySetProto, Builder> implements NetworkIdentitySetProtoOrBuilder {
        private Builder() {
            super(NetworkIdentitySetProto.DEFAULT_INSTANCE);
        }

        @Override // android.service.NetworkIdentitySetProtoOrBuilder
        public List<NetworkIdentityProto> getIdentitiesList() {
            return Collections.unmodifiableList(((NetworkIdentitySetProto) this.instance).getIdentitiesList());
        }

        @Override // android.service.NetworkIdentitySetProtoOrBuilder
        public int getIdentitiesCount() {
            return ((NetworkIdentitySetProto) this.instance).getIdentitiesCount();
        }

        @Override // android.service.NetworkIdentitySetProtoOrBuilder
        public NetworkIdentityProto getIdentities(int index) {
            return ((NetworkIdentitySetProto) this.instance).getIdentities(index);
        }

        public Builder setIdentities(int index, NetworkIdentityProto value) {
            copyOnWrite();
            ((NetworkIdentitySetProto) this.instance).setIdentities((NetworkIdentitySetProto) index, (int) value);
            return this;
        }

        public Builder setIdentities(int index, NetworkIdentityProto.Builder builderForValue) {
            copyOnWrite();
            ((NetworkIdentitySetProto) this.instance).setIdentities((NetworkIdentitySetProto) index, (int) builderForValue);
            return this;
        }

        public Builder addIdentities(NetworkIdentityProto value) {
            copyOnWrite();
            ((NetworkIdentitySetProto) this.instance).addIdentities((NetworkIdentitySetProto) value);
            return this;
        }

        public Builder addIdentities(int index, NetworkIdentityProto value) {
            copyOnWrite();
            ((NetworkIdentitySetProto) this.instance).addIdentities((NetworkIdentitySetProto) index, (int) value);
            return this;
        }

        public Builder addIdentities(NetworkIdentityProto.Builder builderForValue) {
            copyOnWrite();
            ((NetworkIdentitySetProto) this.instance).addIdentities((NetworkIdentitySetProto) builderForValue);
            return this;
        }

        public Builder addIdentities(int index, NetworkIdentityProto.Builder builderForValue) {
            copyOnWrite();
            ((NetworkIdentitySetProto) this.instance).addIdentities((NetworkIdentitySetProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllIdentities(Iterable<? extends NetworkIdentityProto> values) {
            copyOnWrite();
            ((NetworkIdentitySetProto) this.instance).addAllIdentities(values);
            return this;
        }

        public Builder clearIdentities() {
            copyOnWrite();
            ((NetworkIdentitySetProto) this.instance).clearIdentities();
            return this;
        }

        public Builder removeIdentities(int index) {
            copyOnWrite();
            ((NetworkIdentitySetProto) this.instance).removeIdentities(index);
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new NetworkIdentitySetProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                this.identities_.makeImmutable();
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                this.identities_ = ((GeneratedMessageLite.Visitor) arg0).visitList(this.identities_, ((NetworkIdentitySetProto) arg1).identities_);
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
                            if (!this.identities_.isModifiable()) {
                                this.identities_ = GeneratedMessageLite.mutableCopy(this.identities_);
                            }
                            this.identities_.add((NetworkIdentityProto) input.readMessage(NetworkIdentityProto.parser(), extensionRegistry));
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
                    synchronized (NetworkIdentitySetProto.class) {
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

    public static NetworkIdentitySetProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<NetworkIdentitySetProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
