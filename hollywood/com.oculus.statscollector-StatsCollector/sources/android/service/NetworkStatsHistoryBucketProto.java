package android.service;

import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;

public final class NetworkStatsHistoryBucketProto extends GeneratedMessageLite<NetworkStatsHistoryBucketProto, Builder> implements NetworkStatsHistoryBucketProtoOrBuilder {
    public static final int BUCKET_START_MS_FIELD_NUMBER = 1;
    private static final NetworkStatsHistoryBucketProto DEFAULT_INSTANCE = new NetworkStatsHistoryBucketProto();
    public static final int OPERATIONS_FIELD_NUMBER = 6;
    private static volatile Parser<NetworkStatsHistoryBucketProto> PARSER = null;
    public static final int RX_BYTES_FIELD_NUMBER = 2;
    public static final int RX_PACKETS_FIELD_NUMBER = 3;
    public static final int TX_BYTES_FIELD_NUMBER = 4;
    public static final int TX_PACKETS_FIELD_NUMBER = 5;
    private int bitField0_;
    private long bucketStartMs_ = 0;
    private long operations_ = 0;
    private long rxBytes_ = 0;
    private long rxPackets_ = 0;
    private long txBytes_ = 0;
    private long txPackets_ = 0;

    private NetworkStatsHistoryBucketProto() {
    }

    @Override // android.service.NetworkStatsHistoryBucketProtoOrBuilder
    public boolean hasBucketStartMs() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // android.service.NetworkStatsHistoryBucketProtoOrBuilder
    public long getBucketStartMs() {
        return this.bucketStartMs_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setBucketStartMs(long value) {
        this.bitField0_ |= 1;
        this.bucketStartMs_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearBucketStartMs() {
        this.bitField0_ &= -2;
        this.bucketStartMs_ = 0;
    }

    @Override // android.service.NetworkStatsHistoryBucketProtoOrBuilder
    public boolean hasRxBytes() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // android.service.NetworkStatsHistoryBucketProtoOrBuilder
    public long getRxBytes() {
        return this.rxBytes_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setRxBytes(long value) {
        this.bitField0_ |= 2;
        this.rxBytes_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearRxBytes() {
        this.bitField0_ &= -3;
        this.rxBytes_ = 0;
    }

    @Override // android.service.NetworkStatsHistoryBucketProtoOrBuilder
    public boolean hasRxPackets() {
        return (this.bitField0_ & 4) == 4;
    }

    @Override // android.service.NetworkStatsHistoryBucketProtoOrBuilder
    public long getRxPackets() {
        return this.rxPackets_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setRxPackets(long value) {
        this.bitField0_ |= 4;
        this.rxPackets_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearRxPackets() {
        this.bitField0_ &= -5;
        this.rxPackets_ = 0;
    }

    @Override // android.service.NetworkStatsHistoryBucketProtoOrBuilder
    public boolean hasTxBytes() {
        return (this.bitField0_ & 8) == 8;
    }

    @Override // android.service.NetworkStatsHistoryBucketProtoOrBuilder
    public long getTxBytes() {
        return this.txBytes_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setTxBytes(long value) {
        this.bitField0_ |= 8;
        this.txBytes_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearTxBytes() {
        this.bitField0_ &= -9;
        this.txBytes_ = 0;
    }

    @Override // android.service.NetworkStatsHistoryBucketProtoOrBuilder
    public boolean hasTxPackets() {
        return (this.bitField0_ & 16) == 16;
    }

    @Override // android.service.NetworkStatsHistoryBucketProtoOrBuilder
    public long getTxPackets() {
        return this.txPackets_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setTxPackets(long value) {
        this.bitField0_ |= 16;
        this.txPackets_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearTxPackets() {
        this.bitField0_ &= -17;
        this.txPackets_ = 0;
    }

    @Override // android.service.NetworkStatsHistoryBucketProtoOrBuilder
    public boolean hasOperations() {
        return (this.bitField0_ & 32) == 32;
    }

    @Override // android.service.NetworkStatsHistoryBucketProtoOrBuilder
    public long getOperations() {
        return this.operations_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setOperations(long value) {
        this.bitField0_ |= 32;
        this.operations_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearOperations() {
        this.bitField0_ &= -33;
        this.operations_ = 0;
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeInt64(1, this.bucketStartMs_);
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeInt64(2, this.rxBytes_);
        }
        if ((this.bitField0_ & 4) == 4) {
            output.writeInt64(3, this.rxPackets_);
        }
        if ((this.bitField0_ & 8) == 8) {
            output.writeInt64(4, this.txBytes_);
        }
        if ((this.bitField0_ & 16) == 16) {
            output.writeInt64(5, this.txPackets_);
        }
        if ((this.bitField0_ & 32) == 32) {
            output.writeInt64(6, this.operations_);
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
            size2 = 0 + CodedOutputStream.computeInt64Size(1, this.bucketStartMs_);
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeInt64Size(2, this.rxBytes_);
        }
        if ((this.bitField0_ & 4) == 4) {
            size2 += CodedOutputStream.computeInt64Size(3, this.rxPackets_);
        }
        if ((this.bitField0_ & 8) == 8) {
            size2 += CodedOutputStream.computeInt64Size(4, this.txBytes_);
        }
        if ((this.bitField0_ & 16) == 16) {
            size2 += CodedOutputStream.computeInt64Size(5, this.txPackets_);
        }
        if ((this.bitField0_ & 32) == 32) {
            size2 += CodedOutputStream.computeInt64Size(6, this.operations_);
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static NetworkStatsHistoryBucketProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (NetworkStatsHistoryBucketProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static NetworkStatsHistoryBucketProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (NetworkStatsHistoryBucketProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static NetworkStatsHistoryBucketProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (NetworkStatsHistoryBucketProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static NetworkStatsHistoryBucketProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (NetworkStatsHistoryBucketProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static NetworkStatsHistoryBucketProto parseFrom(InputStream input) throws IOException {
        return (NetworkStatsHistoryBucketProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static NetworkStatsHistoryBucketProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (NetworkStatsHistoryBucketProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static NetworkStatsHistoryBucketProto parseDelimitedFrom(InputStream input) throws IOException {
        return (NetworkStatsHistoryBucketProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static NetworkStatsHistoryBucketProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (NetworkStatsHistoryBucketProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static NetworkStatsHistoryBucketProto parseFrom(CodedInputStream input) throws IOException {
        return (NetworkStatsHistoryBucketProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static NetworkStatsHistoryBucketProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (NetworkStatsHistoryBucketProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(NetworkStatsHistoryBucketProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<NetworkStatsHistoryBucketProto, Builder> implements NetworkStatsHistoryBucketProtoOrBuilder {
        private Builder() {
            super(NetworkStatsHistoryBucketProto.DEFAULT_INSTANCE);
        }

        @Override // android.service.NetworkStatsHistoryBucketProtoOrBuilder
        public boolean hasBucketStartMs() {
            return ((NetworkStatsHistoryBucketProto) this.instance).hasBucketStartMs();
        }

        @Override // android.service.NetworkStatsHistoryBucketProtoOrBuilder
        public long getBucketStartMs() {
            return ((NetworkStatsHistoryBucketProto) this.instance).getBucketStartMs();
        }

        public Builder setBucketStartMs(long value) {
            copyOnWrite();
            ((NetworkStatsHistoryBucketProto) this.instance).setBucketStartMs(value);
            return this;
        }

        public Builder clearBucketStartMs() {
            copyOnWrite();
            ((NetworkStatsHistoryBucketProto) this.instance).clearBucketStartMs();
            return this;
        }

        @Override // android.service.NetworkStatsHistoryBucketProtoOrBuilder
        public boolean hasRxBytes() {
            return ((NetworkStatsHistoryBucketProto) this.instance).hasRxBytes();
        }

        @Override // android.service.NetworkStatsHistoryBucketProtoOrBuilder
        public long getRxBytes() {
            return ((NetworkStatsHistoryBucketProto) this.instance).getRxBytes();
        }

        public Builder setRxBytes(long value) {
            copyOnWrite();
            ((NetworkStatsHistoryBucketProto) this.instance).setRxBytes(value);
            return this;
        }

        public Builder clearRxBytes() {
            copyOnWrite();
            ((NetworkStatsHistoryBucketProto) this.instance).clearRxBytes();
            return this;
        }

        @Override // android.service.NetworkStatsHistoryBucketProtoOrBuilder
        public boolean hasRxPackets() {
            return ((NetworkStatsHistoryBucketProto) this.instance).hasRxPackets();
        }

        @Override // android.service.NetworkStatsHistoryBucketProtoOrBuilder
        public long getRxPackets() {
            return ((NetworkStatsHistoryBucketProto) this.instance).getRxPackets();
        }

        public Builder setRxPackets(long value) {
            copyOnWrite();
            ((NetworkStatsHistoryBucketProto) this.instance).setRxPackets(value);
            return this;
        }

        public Builder clearRxPackets() {
            copyOnWrite();
            ((NetworkStatsHistoryBucketProto) this.instance).clearRxPackets();
            return this;
        }

        @Override // android.service.NetworkStatsHistoryBucketProtoOrBuilder
        public boolean hasTxBytes() {
            return ((NetworkStatsHistoryBucketProto) this.instance).hasTxBytes();
        }

        @Override // android.service.NetworkStatsHistoryBucketProtoOrBuilder
        public long getTxBytes() {
            return ((NetworkStatsHistoryBucketProto) this.instance).getTxBytes();
        }

        public Builder setTxBytes(long value) {
            copyOnWrite();
            ((NetworkStatsHistoryBucketProto) this.instance).setTxBytes(value);
            return this;
        }

        public Builder clearTxBytes() {
            copyOnWrite();
            ((NetworkStatsHistoryBucketProto) this.instance).clearTxBytes();
            return this;
        }

        @Override // android.service.NetworkStatsHistoryBucketProtoOrBuilder
        public boolean hasTxPackets() {
            return ((NetworkStatsHistoryBucketProto) this.instance).hasTxPackets();
        }

        @Override // android.service.NetworkStatsHistoryBucketProtoOrBuilder
        public long getTxPackets() {
            return ((NetworkStatsHistoryBucketProto) this.instance).getTxPackets();
        }

        public Builder setTxPackets(long value) {
            copyOnWrite();
            ((NetworkStatsHistoryBucketProto) this.instance).setTxPackets(value);
            return this;
        }

        public Builder clearTxPackets() {
            copyOnWrite();
            ((NetworkStatsHistoryBucketProto) this.instance).clearTxPackets();
            return this;
        }

        @Override // android.service.NetworkStatsHistoryBucketProtoOrBuilder
        public boolean hasOperations() {
            return ((NetworkStatsHistoryBucketProto) this.instance).hasOperations();
        }

        @Override // android.service.NetworkStatsHistoryBucketProtoOrBuilder
        public long getOperations() {
            return ((NetworkStatsHistoryBucketProto) this.instance).getOperations();
        }

        public Builder setOperations(long value) {
            copyOnWrite();
            ((NetworkStatsHistoryBucketProto) this.instance).setOperations(value);
            return this;
        }

        public Builder clearOperations() {
            copyOnWrite();
            ((NetworkStatsHistoryBucketProto) this.instance).clearOperations();
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new NetworkStatsHistoryBucketProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                NetworkStatsHistoryBucketProto other = (NetworkStatsHistoryBucketProto) arg1;
                this.bucketStartMs_ = visitor.visitLong(hasBucketStartMs(), this.bucketStartMs_, other.hasBucketStartMs(), other.bucketStartMs_);
                this.rxBytes_ = visitor.visitLong(hasRxBytes(), this.rxBytes_, other.hasRxBytes(), other.rxBytes_);
                this.rxPackets_ = visitor.visitLong(hasRxPackets(), this.rxPackets_, other.hasRxPackets(), other.rxPackets_);
                this.txBytes_ = visitor.visitLong(hasTxBytes(), this.txBytes_, other.hasTxBytes(), other.txBytes_);
                this.txPackets_ = visitor.visitLong(hasTxPackets(), this.txPackets_, other.hasTxPackets(), other.txPackets_);
                this.operations_ = visitor.visitLong(hasOperations(), this.operations_, other.hasOperations(), other.operations_);
                if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE) {
                    this.bitField0_ |= other.bitField0_;
                }
                return this;
            case MERGE_FROM_STREAM:
                CodedInputStream input = (CodedInputStream) arg0;
                ExtensionRegistryLite extensionRegistryLite = (ExtensionRegistryLite) arg1;
                boolean done = false;
                while (!done) {
                    try {
                        int tag = input.readTag();
                        if (tag == 0) {
                            done = true;
                        } else if (tag == 8) {
                            this.bitField0_ |= 1;
                            this.bucketStartMs_ = input.readInt64();
                        } else if (tag == 16) {
                            this.bitField0_ |= 2;
                            this.rxBytes_ = input.readInt64();
                        } else if (tag == 24) {
                            this.bitField0_ |= 4;
                            this.rxPackets_ = input.readInt64();
                        } else if (tag == 32) {
                            this.bitField0_ = 8 | this.bitField0_;
                            this.txBytes_ = input.readInt64();
                        } else if (tag == 40) {
                            this.bitField0_ |= 16;
                            this.txPackets_ = input.readInt64();
                        } else if (tag == 48) {
                            this.bitField0_ |= 32;
                            this.operations_ = input.readInt64();
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
                    synchronized (NetworkStatsHistoryBucketProto.class) {
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

    public static NetworkStatsHistoryBucketProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<NetworkStatsHistoryBucketProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
