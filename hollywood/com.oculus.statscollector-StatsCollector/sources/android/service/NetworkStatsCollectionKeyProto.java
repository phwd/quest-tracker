package android.service;

import android.service.NetworkIdentitySetProto;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;

public final class NetworkStatsCollectionKeyProto extends GeneratedMessageLite<NetworkStatsCollectionKeyProto, Builder> implements NetworkStatsCollectionKeyProtoOrBuilder {
    private static final NetworkStatsCollectionKeyProto DEFAULT_INSTANCE = new NetworkStatsCollectionKeyProto();
    public static final int IDENTITY_FIELD_NUMBER = 1;
    private static volatile Parser<NetworkStatsCollectionKeyProto> PARSER = null;
    public static final int SET_FIELD_NUMBER = 3;
    public static final int TAG_FIELD_NUMBER = 4;
    public static final int UID_FIELD_NUMBER = 2;
    private int bitField0_;
    private NetworkIdentitySetProto identity_;
    private int set_ = 0;
    private int tag_ = 0;
    private int uid_ = 0;

    private NetworkStatsCollectionKeyProto() {
    }

    @Override // android.service.NetworkStatsCollectionKeyProtoOrBuilder
    public boolean hasIdentity() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // android.service.NetworkStatsCollectionKeyProtoOrBuilder
    public NetworkIdentitySetProto getIdentity() {
        NetworkIdentitySetProto networkIdentitySetProto = this.identity_;
        return networkIdentitySetProto == null ? NetworkIdentitySetProto.getDefaultInstance() : networkIdentitySetProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setIdentity(NetworkIdentitySetProto value) {
        if (value != null) {
            this.identity_ = value;
            this.bitField0_ |= 1;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setIdentity(NetworkIdentitySetProto.Builder builderForValue) {
        this.identity_ = (NetworkIdentitySetProto) builderForValue.build();
        this.bitField0_ |= 1;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeIdentity(NetworkIdentitySetProto value) {
        NetworkIdentitySetProto networkIdentitySetProto = this.identity_;
        if (networkIdentitySetProto == null || networkIdentitySetProto == NetworkIdentitySetProto.getDefaultInstance()) {
            this.identity_ = value;
        } else {
            this.identity_ = (NetworkIdentitySetProto) ((NetworkIdentitySetProto.Builder) NetworkIdentitySetProto.newBuilder(this.identity_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 1;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearIdentity() {
        this.identity_ = null;
        this.bitField0_ &= -2;
    }

    @Override // android.service.NetworkStatsCollectionKeyProtoOrBuilder
    public boolean hasUid() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // android.service.NetworkStatsCollectionKeyProtoOrBuilder
    public int getUid() {
        return this.uid_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setUid(int value) {
        this.bitField0_ |= 2;
        this.uid_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearUid() {
        this.bitField0_ &= -3;
        this.uid_ = 0;
    }

    @Override // android.service.NetworkStatsCollectionKeyProtoOrBuilder
    public boolean hasSet() {
        return (this.bitField0_ & 4) == 4;
    }

    @Override // android.service.NetworkStatsCollectionKeyProtoOrBuilder
    public int getSet() {
        return this.set_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setSet(int value) {
        this.bitField0_ |= 4;
        this.set_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearSet() {
        this.bitField0_ &= -5;
        this.set_ = 0;
    }

    @Override // android.service.NetworkStatsCollectionKeyProtoOrBuilder
    public boolean hasTag() {
        return (this.bitField0_ & 8) == 8;
    }

    @Override // android.service.NetworkStatsCollectionKeyProtoOrBuilder
    public int getTag() {
        return this.tag_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setTag(int value) {
        this.bitField0_ |= 8;
        this.tag_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearTag() {
        this.bitField0_ &= -9;
        this.tag_ = 0;
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeMessage(1, getIdentity());
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeInt32(2, this.uid_);
        }
        if ((this.bitField0_ & 4) == 4) {
            output.writeInt32(3, this.set_);
        }
        if ((this.bitField0_ & 8) == 8) {
            output.writeInt32(4, this.tag_);
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
            size2 = 0 + CodedOutputStream.computeMessageSize(1, getIdentity());
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeInt32Size(2, this.uid_);
        }
        if ((this.bitField0_ & 4) == 4) {
            size2 += CodedOutputStream.computeInt32Size(3, this.set_);
        }
        if ((this.bitField0_ & 8) == 8) {
            size2 += CodedOutputStream.computeInt32Size(4, this.tag_);
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static NetworkStatsCollectionKeyProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (NetworkStatsCollectionKeyProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static NetworkStatsCollectionKeyProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (NetworkStatsCollectionKeyProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static NetworkStatsCollectionKeyProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (NetworkStatsCollectionKeyProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static NetworkStatsCollectionKeyProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (NetworkStatsCollectionKeyProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static NetworkStatsCollectionKeyProto parseFrom(InputStream input) throws IOException {
        return (NetworkStatsCollectionKeyProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static NetworkStatsCollectionKeyProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (NetworkStatsCollectionKeyProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static NetworkStatsCollectionKeyProto parseDelimitedFrom(InputStream input) throws IOException {
        return (NetworkStatsCollectionKeyProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static NetworkStatsCollectionKeyProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (NetworkStatsCollectionKeyProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static NetworkStatsCollectionKeyProto parseFrom(CodedInputStream input) throws IOException {
        return (NetworkStatsCollectionKeyProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static NetworkStatsCollectionKeyProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (NetworkStatsCollectionKeyProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(NetworkStatsCollectionKeyProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<NetworkStatsCollectionKeyProto, Builder> implements NetworkStatsCollectionKeyProtoOrBuilder {
        private Builder() {
            super(NetworkStatsCollectionKeyProto.DEFAULT_INSTANCE);
        }

        @Override // android.service.NetworkStatsCollectionKeyProtoOrBuilder
        public boolean hasIdentity() {
            return ((NetworkStatsCollectionKeyProto) this.instance).hasIdentity();
        }

        @Override // android.service.NetworkStatsCollectionKeyProtoOrBuilder
        public NetworkIdentitySetProto getIdentity() {
            return ((NetworkStatsCollectionKeyProto) this.instance).getIdentity();
        }

        public Builder setIdentity(NetworkIdentitySetProto value) {
            copyOnWrite();
            ((NetworkStatsCollectionKeyProto) this.instance).setIdentity((NetworkStatsCollectionKeyProto) value);
            return this;
        }

        public Builder setIdentity(NetworkIdentitySetProto.Builder builderForValue) {
            copyOnWrite();
            ((NetworkStatsCollectionKeyProto) this.instance).setIdentity((NetworkStatsCollectionKeyProto) builderForValue);
            return this;
        }

        public Builder mergeIdentity(NetworkIdentitySetProto value) {
            copyOnWrite();
            ((NetworkStatsCollectionKeyProto) this.instance).mergeIdentity(value);
            return this;
        }

        public Builder clearIdentity() {
            copyOnWrite();
            ((NetworkStatsCollectionKeyProto) this.instance).clearIdentity();
            return this;
        }

        @Override // android.service.NetworkStatsCollectionKeyProtoOrBuilder
        public boolean hasUid() {
            return ((NetworkStatsCollectionKeyProto) this.instance).hasUid();
        }

        @Override // android.service.NetworkStatsCollectionKeyProtoOrBuilder
        public int getUid() {
            return ((NetworkStatsCollectionKeyProto) this.instance).getUid();
        }

        public Builder setUid(int value) {
            copyOnWrite();
            ((NetworkStatsCollectionKeyProto) this.instance).setUid(value);
            return this;
        }

        public Builder clearUid() {
            copyOnWrite();
            ((NetworkStatsCollectionKeyProto) this.instance).clearUid();
            return this;
        }

        @Override // android.service.NetworkStatsCollectionKeyProtoOrBuilder
        public boolean hasSet() {
            return ((NetworkStatsCollectionKeyProto) this.instance).hasSet();
        }

        @Override // android.service.NetworkStatsCollectionKeyProtoOrBuilder
        public int getSet() {
            return ((NetworkStatsCollectionKeyProto) this.instance).getSet();
        }

        public Builder setSet(int value) {
            copyOnWrite();
            ((NetworkStatsCollectionKeyProto) this.instance).setSet(value);
            return this;
        }

        public Builder clearSet() {
            copyOnWrite();
            ((NetworkStatsCollectionKeyProto) this.instance).clearSet();
            return this;
        }

        @Override // android.service.NetworkStatsCollectionKeyProtoOrBuilder
        public boolean hasTag() {
            return ((NetworkStatsCollectionKeyProto) this.instance).hasTag();
        }

        @Override // android.service.NetworkStatsCollectionKeyProtoOrBuilder
        public int getTag() {
            return ((NetworkStatsCollectionKeyProto) this.instance).getTag();
        }

        public Builder setTag(int value) {
            copyOnWrite();
            ((NetworkStatsCollectionKeyProto) this.instance).setTag(value);
            return this;
        }

        public Builder clearTag() {
            copyOnWrite();
            ((NetworkStatsCollectionKeyProto) this.instance).clearTag();
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new NetworkStatsCollectionKeyProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                NetworkStatsCollectionKeyProto other = (NetworkStatsCollectionKeyProto) arg1;
                this.identity_ = (NetworkIdentitySetProto) visitor.visitMessage(this.identity_, other.identity_);
                this.uid_ = visitor.visitInt(hasUid(), this.uid_, other.hasUid(), other.uid_);
                this.set_ = visitor.visitInt(hasSet(), this.set_, other.hasSet(), other.set_);
                this.tag_ = visitor.visitInt(hasTag(), this.tag_, other.hasTag(), other.tag_);
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
                            NetworkIdentitySetProto.Builder subBuilder = null;
                            if ((this.bitField0_ & 1) == 1) {
                                subBuilder = (NetworkIdentitySetProto.Builder) this.identity_.toBuilder();
                            }
                            this.identity_ = (NetworkIdentitySetProto) input.readMessage(NetworkIdentitySetProto.parser(), extensionRegistry);
                            if (subBuilder != null) {
                                subBuilder.mergeFrom((GeneratedMessageLite) this.identity_);
                                this.identity_ = (NetworkIdentitySetProto) subBuilder.buildPartial();
                            }
                            this.bitField0_ |= 1;
                        } else if (tag == 16) {
                            this.bitField0_ |= 2;
                            this.uid_ = input.readInt32();
                        } else if (tag == 24) {
                            this.bitField0_ |= 4;
                            this.set_ = input.readInt32();
                        } else if (tag == 32) {
                            this.bitField0_ |= 8;
                            this.tag_ = input.readInt32();
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
                    synchronized (NetworkStatsCollectionKeyProto.class) {
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

    public static NetworkStatsCollectionKeyProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<NetworkStatsCollectionKeyProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
