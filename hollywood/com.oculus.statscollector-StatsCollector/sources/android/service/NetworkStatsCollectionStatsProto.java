package android.service;

import android.service.NetworkStatsCollectionKeyProto;
import android.service.NetworkStatsHistoryProto;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;

public final class NetworkStatsCollectionStatsProto extends GeneratedMessageLite<NetworkStatsCollectionStatsProto, Builder> implements NetworkStatsCollectionStatsProtoOrBuilder {
    private static final NetworkStatsCollectionStatsProto DEFAULT_INSTANCE = new NetworkStatsCollectionStatsProto();
    public static final int HISTORY_FIELD_NUMBER = 2;
    public static final int KEY_FIELD_NUMBER = 1;
    private static volatile Parser<NetworkStatsCollectionStatsProto> PARSER;
    private int bitField0_;
    private NetworkStatsHistoryProto history_;
    private NetworkStatsCollectionKeyProto key_;

    private NetworkStatsCollectionStatsProto() {
    }

    @Override // android.service.NetworkStatsCollectionStatsProtoOrBuilder
    public boolean hasKey() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // android.service.NetworkStatsCollectionStatsProtoOrBuilder
    public NetworkStatsCollectionKeyProto getKey() {
        NetworkStatsCollectionKeyProto networkStatsCollectionKeyProto = this.key_;
        return networkStatsCollectionKeyProto == null ? NetworkStatsCollectionKeyProto.getDefaultInstance() : networkStatsCollectionKeyProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setKey(NetworkStatsCollectionKeyProto value) {
        if (value != null) {
            this.key_ = value;
            this.bitField0_ |= 1;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setKey(NetworkStatsCollectionKeyProto.Builder builderForValue) {
        this.key_ = (NetworkStatsCollectionKeyProto) builderForValue.build();
        this.bitField0_ |= 1;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeKey(NetworkStatsCollectionKeyProto value) {
        NetworkStatsCollectionKeyProto networkStatsCollectionKeyProto = this.key_;
        if (networkStatsCollectionKeyProto == null || networkStatsCollectionKeyProto == NetworkStatsCollectionKeyProto.getDefaultInstance()) {
            this.key_ = value;
        } else {
            this.key_ = (NetworkStatsCollectionKeyProto) ((NetworkStatsCollectionKeyProto.Builder) NetworkStatsCollectionKeyProto.newBuilder(this.key_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 1;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearKey() {
        this.key_ = null;
        this.bitField0_ &= -2;
    }

    @Override // android.service.NetworkStatsCollectionStatsProtoOrBuilder
    public boolean hasHistory() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // android.service.NetworkStatsCollectionStatsProtoOrBuilder
    public NetworkStatsHistoryProto getHistory() {
        NetworkStatsHistoryProto networkStatsHistoryProto = this.history_;
        return networkStatsHistoryProto == null ? NetworkStatsHistoryProto.getDefaultInstance() : networkStatsHistoryProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setHistory(NetworkStatsHistoryProto value) {
        if (value != null) {
            this.history_ = value;
            this.bitField0_ |= 2;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setHistory(NetworkStatsHistoryProto.Builder builderForValue) {
        this.history_ = (NetworkStatsHistoryProto) builderForValue.build();
        this.bitField0_ |= 2;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeHistory(NetworkStatsHistoryProto value) {
        NetworkStatsHistoryProto networkStatsHistoryProto = this.history_;
        if (networkStatsHistoryProto == null || networkStatsHistoryProto == NetworkStatsHistoryProto.getDefaultInstance()) {
            this.history_ = value;
        } else {
            this.history_ = (NetworkStatsHistoryProto) ((NetworkStatsHistoryProto.Builder) NetworkStatsHistoryProto.newBuilder(this.history_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 2;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearHistory() {
        this.history_ = null;
        this.bitField0_ &= -3;
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeMessage(1, getKey());
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeMessage(2, getHistory());
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
            size2 = 0 + CodedOutputStream.computeMessageSize(1, getKey());
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeMessageSize(2, getHistory());
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static NetworkStatsCollectionStatsProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (NetworkStatsCollectionStatsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static NetworkStatsCollectionStatsProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (NetworkStatsCollectionStatsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static NetworkStatsCollectionStatsProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (NetworkStatsCollectionStatsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static NetworkStatsCollectionStatsProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (NetworkStatsCollectionStatsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static NetworkStatsCollectionStatsProto parseFrom(InputStream input) throws IOException {
        return (NetworkStatsCollectionStatsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static NetworkStatsCollectionStatsProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (NetworkStatsCollectionStatsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static NetworkStatsCollectionStatsProto parseDelimitedFrom(InputStream input) throws IOException {
        return (NetworkStatsCollectionStatsProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static NetworkStatsCollectionStatsProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (NetworkStatsCollectionStatsProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static NetworkStatsCollectionStatsProto parseFrom(CodedInputStream input) throws IOException {
        return (NetworkStatsCollectionStatsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static NetworkStatsCollectionStatsProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (NetworkStatsCollectionStatsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(NetworkStatsCollectionStatsProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<NetworkStatsCollectionStatsProto, Builder> implements NetworkStatsCollectionStatsProtoOrBuilder {
        private Builder() {
            super(NetworkStatsCollectionStatsProto.DEFAULT_INSTANCE);
        }

        @Override // android.service.NetworkStatsCollectionStatsProtoOrBuilder
        public boolean hasKey() {
            return ((NetworkStatsCollectionStatsProto) this.instance).hasKey();
        }

        @Override // android.service.NetworkStatsCollectionStatsProtoOrBuilder
        public NetworkStatsCollectionKeyProto getKey() {
            return ((NetworkStatsCollectionStatsProto) this.instance).getKey();
        }

        public Builder setKey(NetworkStatsCollectionKeyProto value) {
            copyOnWrite();
            ((NetworkStatsCollectionStatsProto) this.instance).setKey((NetworkStatsCollectionStatsProto) value);
            return this;
        }

        public Builder setKey(NetworkStatsCollectionKeyProto.Builder builderForValue) {
            copyOnWrite();
            ((NetworkStatsCollectionStatsProto) this.instance).setKey((NetworkStatsCollectionStatsProto) builderForValue);
            return this;
        }

        public Builder mergeKey(NetworkStatsCollectionKeyProto value) {
            copyOnWrite();
            ((NetworkStatsCollectionStatsProto) this.instance).mergeKey(value);
            return this;
        }

        public Builder clearKey() {
            copyOnWrite();
            ((NetworkStatsCollectionStatsProto) this.instance).clearKey();
            return this;
        }

        @Override // android.service.NetworkStatsCollectionStatsProtoOrBuilder
        public boolean hasHistory() {
            return ((NetworkStatsCollectionStatsProto) this.instance).hasHistory();
        }

        @Override // android.service.NetworkStatsCollectionStatsProtoOrBuilder
        public NetworkStatsHistoryProto getHistory() {
            return ((NetworkStatsCollectionStatsProto) this.instance).getHistory();
        }

        public Builder setHistory(NetworkStatsHistoryProto value) {
            copyOnWrite();
            ((NetworkStatsCollectionStatsProto) this.instance).setHistory((NetworkStatsCollectionStatsProto) value);
            return this;
        }

        public Builder setHistory(NetworkStatsHistoryProto.Builder builderForValue) {
            copyOnWrite();
            ((NetworkStatsCollectionStatsProto) this.instance).setHistory((NetworkStatsCollectionStatsProto) builderForValue);
            return this;
        }

        public Builder mergeHistory(NetworkStatsHistoryProto value) {
            copyOnWrite();
            ((NetworkStatsCollectionStatsProto) this.instance).mergeHistory(value);
            return this;
        }

        public Builder clearHistory() {
            copyOnWrite();
            ((NetworkStatsCollectionStatsProto) this.instance).clearHistory();
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new NetworkStatsCollectionStatsProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                NetworkStatsCollectionStatsProto other = (NetworkStatsCollectionStatsProto) arg1;
                this.key_ = (NetworkStatsCollectionKeyProto) visitor.visitMessage(this.key_, other.key_);
                this.history_ = (NetworkStatsHistoryProto) visitor.visitMessage(this.history_, other.history_);
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
                            NetworkStatsCollectionKeyProto.Builder subBuilder = null;
                            if ((this.bitField0_ & 1) == 1) {
                                subBuilder = (NetworkStatsCollectionKeyProto.Builder) this.key_.toBuilder();
                            }
                            this.key_ = (NetworkStatsCollectionKeyProto) input.readMessage(NetworkStatsCollectionKeyProto.parser(), extensionRegistry);
                            if (subBuilder != null) {
                                subBuilder.mergeFrom((GeneratedMessageLite) this.key_);
                                this.key_ = (NetworkStatsCollectionKeyProto) subBuilder.buildPartial();
                            }
                            this.bitField0_ |= 1;
                        } else if (tag == 18) {
                            NetworkStatsHistoryProto.Builder subBuilder2 = null;
                            if ((this.bitField0_ & 2) == 2) {
                                subBuilder2 = (NetworkStatsHistoryProto.Builder) this.history_.toBuilder();
                            }
                            this.history_ = (NetworkStatsHistoryProto) input.readMessage(NetworkStatsHistoryProto.parser(), extensionRegistry);
                            if (subBuilder2 != null) {
                                subBuilder2.mergeFrom((GeneratedMessageLite) this.history_);
                                this.history_ = (NetworkStatsHistoryProto) subBuilder2.buildPartial();
                            }
                            this.bitField0_ |= 2;
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
                    synchronized (NetworkStatsCollectionStatsProto.class) {
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

    public static NetworkStatsCollectionStatsProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<NetworkStatsCollectionStatsProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
