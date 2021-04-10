package android.service;

import android.service.NetworkStatsCollectionProto;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;

public final class NetworkStatsRecorderProto extends GeneratedMessageLite<NetworkStatsRecorderProto, Builder> implements NetworkStatsRecorderProtoOrBuilder {
    public static final int COMPLETE_HISTORY_FIELD_NUMBER = 2;
    private static final NetworkStatsRecorderProto DEFAULT_INSTANCE = new NetworkStatsRecorderProto();
    private static volatile Parser<NetworkStatsRecorderProto> PARSER = null;
    public static final int PENDING_TOTAL_BYTES_FIELD_NUMBER = 1;
    private int bitField0_;
    private NetworkStatsCollectionProto completeHistory_;
    private long pendingTotalBytes_ = 0;

    private NetworkStatsRecorderProto() {
    }

    @Override // android.service.NetworkStatsRecorderProtoOrBuilder
    public boolean hasPendingTotalBytes() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // android.service.NetworkStatsRecorderProtoOrBuilder
    public long getPendingTotalBytes() {
        return this.pendingTotalBytes_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPendingTotalBytes(long value) {
        this.bitField0_ |= 1;
        this.pendingTotalBytes_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearPendingTotalBytes() {
        this.bitField0_ &= -2;
        this.pendingTotalBytes_ = 0;
    }

    @Override // android.service.NetworkStatsRecorderProtoOrBuilder
    public boolean hasCompleteHistory() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // android.service.NetworkStatsRecorderProtoOrBuilder
    public NetworkStatsCollectionProto getCompleteHistory() {
        NetworkStatsCollectionProto networkStatsCollectionProto = this.completeHistory_;
        return networkStatsCollectionProto == null ? NetworkStatsCollectionProto.getDefaultInstance() : networkStatsCollectionProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setCompleteHistory(NetworkStatsCollectionProto value) {
        if (value != null) {
            this.completeHistory_ = value;
            this.bitField0_ |= 2;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setCompleteHistory(NetworkStatsCollectionProto.Builder builderForValue) {
        this.completeHistory_ = (NetworkStatsCollectionProto) builderForValue.build();
        this.bitField0_ |= 2;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeCompleteHistory(NetworkStatsCollectionProto value) {
        NetworkStatsCollectionProto networkStatsCollectionProto = this.completeHistory_;
        if (networkStatsCollectionProto == null || networkStatsCollectionProto == NetworkStatsCollectionProto.getDefaultInstance()) {
            this.completeHistory_ = value;
        } else {
            this.completeHistory_ = (NetworkStatsCollectionProto) ((NetworkStatsCollectionProto.Builder) NetworkStatsCollectionProto.newBuilder(this.completeHistory_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 2;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearCompleteHistory() {
        this.completeHistory_ = null;
        this.bitField0_ &= -3;
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeInt64(1, this.pendingTotalBytes_);
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeMessage(2, getCompleteHistory());
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
            size2 = 0 + CodedOutputStream.computeInt64Size(1, this.pendingTotalBytes_);
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeMessageSize(2, getCompleteHistory());
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static NetworkStatsRecorderProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (NetworkStatsRecorderProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static NetworkStatsRecorderProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (NetworkStatsRecorderProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static NetworkStatsRecorderProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (NetworkStatsRecorderProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static NetworkStatsRecorderProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (NetworkStatsRecorderProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static NetworkStatsRecorderProto parseFrom(InputStream input) throws IOException {
        return (NetworkStatsRecorderProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static NetworkStatsRecorderProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (NetworkStatsRecorderProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static NetworkStatsRecorderProto parseDelimitedFrom(InputStream input) throws IOException {
        return (NetworkStatsRecorderProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static NetworkStatsRecorderProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (NetworkStatsRecorderProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static NetworkStatsRecorderProto parseFrom(CodedInputStream input) throws IOException {
        return (NetworkStatsRecorderProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static NetworkStatsRecorderProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (NetworkStatsRecorderProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(NetworkStatsRecorderProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<NetworkStatsRecorderProto, Builder> implements NetworkStatsRecorderProtoOrBuilder {
        private Builder() {
            super(NetworkStatsRecorderProto.DEFAULT_INSTANCE);
        }

        @Override // android.service.NetworkStatsRecorderProtoOrBuilder
        public boolean hasPendingTotalBytes() {
            return ((NetworkStatsRecorderProto) this.instance).hasPendingTotalBytes();
        }

        @Override // android.service.NetworkStatsRecorderProtoOrBuilder
        public long getPendingTotalBytes() {
            return ((NetworkStatsRecorderProto) this.instance).getPendingTotalBytes();
        }

        public Builder setPendingTotalBytes(long value) {
            copyOnWrite();
            ((NetworkStatsRecorderProto) this.instance).setPendingTotalBytes(value);
            return this;
        }

        public Builder clearPendingTotalBytes() {
            copyOnWrite();
            ((NetworkStatsRecorderProto) this.instance).clearPendingTotalBytes();
            return this;
        }

        @Override // android.service.NetworkStatsRecorderProtoOrBuilder
        public boolean hasCompleteHistory() {
            return ((NetworkStatsRecorderProto) this.instance).hasCompleteHistory();
        }

        @Override // android.service.NetworkStatsRecorderProtoOrBuilder
        public NetworkStatsCollectionProto getCompleteHistory() {
            return ((NetworkStatsRecorderProto) this.instance).getCompleteHistory();
        }

        public Builder setCompleteHistory(NetworkStatsCollectionProto value) {
            copyOnWrite();
            ((NetworkStatsRecorderProto) this.instance).setCompleteHistory((NetworkStatsRecorderProto) value);
            return this;
        }

        public Builder setCompleteHistory(NetworkStatsCollectionProto.Builder builderForValue) {
            copyOnWrite();
            ((NetworkStatsRecorderProto) this.instance).setCompleteHistory((NetworkStatsRecorderProto) builderForValue);
            return this;
        }

        public Builder mergeCompleteHistory(NetworkStatsCollectionProto value) {
            copyOnWrite();
            ((NetworkStatsRecorderProto) this.instance).mergeCompleteHistory(value);
            return this;
        }

        public Builder clearCompleteHistory() {
            copyOnWrite();
            ((NetworkStatsRecorderProto) this.instance).clearCompleteHistory();
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new NetworkStatsRecorderProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                NetworkStatsRecorderProto other = (NetworkStatsRecorderProto) arg1;
                this.pendingTotalBytes_ = visitor.visitLong(hasPendingTotalBytes(), this.pendingTotalBytes_, other.hasPendingTotalBytes(), other.pendingTotalBytes_);
                this.completeHistory_ = (NetworkStatsCollectionProto) visitor.visitMessage(this.completeHistory_, other.completeHistory_);
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
                            this.pendingTotalBytes_ = input.readInt64();
                        } else if (tag == 18) {
                            NetworkStatsCollectionProto.Builder subBuilder = null;
                            if ((this.bitField0_ & 2) == 2) {
                                subBuilder = (NetworkStatsCollectionProto.Builder) this.completeHistory_.toBuilder();
                            }
                            this.completeHistory_ = (NetworkStatsCollectionProto) input.readMessage(NetworkStatsCollectionProto.parser(), extensionRegistry);
                            if (subBuilder != null) {
                                subBuilder.mergeFrom((GeneratedMessageLite) this.completeHistory_);
                                this.completeHistory_ = (NetworkStatsCollectionProto) subBuilder.buildPartial();
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
                    synchronized (NetworkStatsRecorderProto.class) {
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

    public static NetworkStatsRecorderProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<NetworkStatsRecorderProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
