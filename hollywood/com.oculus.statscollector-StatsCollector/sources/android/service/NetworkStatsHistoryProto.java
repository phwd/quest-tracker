package android.service;

import android.service.NetworkStatsHistoryBucketProto;
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

public final class NetworkStatsHistoryProto extends GeneratedMessageLite<NetworkStatsHistoryProto, Builder> implements NetworkStatsHistoryProtoOrBuilder {
    public static final int BUCKETS_FIELD_NUMBER = 2;
    public static final int BUCKET_DURATION_MS_FIELD_NUMBER = 1;
    private static final NetworkStatsHistoryProto DEFAULT_INSTANCE = new NetworkStatsHistoryProto();
    private static volatile Parser<NetworkStatsHistoryProto> PARSER;
    private int bitField0_;
    private long bucketDurationMs_ = 0;
    private Internal.ProtobufList<NetworkStatsHistoryBucketProto> buckets_ = emptyProtobufList();

    private NetworkStatsHistoryProto() {
    }

    @Override // android.service.NetworkStatsHistoryProtoOrBuilder
    public boolean hasBucketDurationMs() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // android.service.NetworkStatsHistoryProtoOrBuilder
    public long getBucketDurationMs() {
        return this.bucketDurationMs_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setBucketDurationMs(long value) {
        this.bitField0_ |= 1;
        this.bucketDurationMs_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearBucketDurationMs() {
        this.bitField0_ &= -2;
        this.bucketDurationMs_ = 0;
    }

    @Override // android.service.NetworkStatsHistoryProtoOrBuilder
    public List<NetworkStatsHistoryBucketProto> getBucketsList() {
        return this.buckets_;
    }

    public List<? extends NetworkStatsHistoryBucketProtoOrBuilder> getBucketsOrBuilderList() {
        return this.buckets_;
    }

    @Override // android.service.NetworkStatsHistoryProtoOrBuilder
    public int getBucketsCount() {
        return this.buckets_.size();
    }

    @Override // android.service.NetworkStatsHistoryProtoOrBuilder
    public NetworkStatsHistoryBucketProto getBuckets(int index) {
        return this.buckets_.get(index);
    }

    public NetworkStatsHistoryBucketProtoOrBuilder getBucketsOrBuilder(int index) {
        return this.buckets_.get(index);
    }

    private void ensureBucketsIsMutable() {
        if (!this.buckets_.isModifiable()) {
            this.buckets_ = GeneratedMessageLite.mutableCopy(this.buckets_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setBuckets(int index, NetworkStatsHistoryBucketProto value) {
        if (value != null) {
            ensureBucketsIsMutable();
            this.buckets_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setBuckets(int index, NetworkStatsHistoryBucketProto.Builder builderForValue) {
        ensureBucketsIsMutable();
        this.buckets_.set(index, (NetworkStatsHistoryBucketProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addBuckets(NetworkStatsHistoryBucketProto value) {
        if (value != null) {
            ensureBucketsIsMutable();
            this.buckets_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addBuckets(int index, NetworkStatsHistoryBucketProto value) {
        if (value != null) {
            ensureBucketsIsMutable();
            this.buckets_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addBuckets(NetworkStatsHistoryBucketProto.Builder builderForValue) {
        ensureBucketsIsMutable();
        this.buckets_.add((NetworkStatsHistoryBucketProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addBuckets(int index, NetworkStatsHistoryBucketProto.Builder builderForValue) {
        ensureBucketsIsMutable();
        this.buckets_.add(index, (NetworkStatsHistoryBucketProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllBuckets(Iterable<? extends NetworkStatsHistoryBucketProto> values) {
        ensureBucketsIsMutable();
        AbstractMessageLite.addAll(values, this.buckets_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearBuckets() {
        this.buckets_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeBuckets(int index) {
        ensureBucketsIsMutable();
        this.buckets_.remove(index);
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeInt64(1, this.bucketDurationMs_);
        }
        for (int i = 0; i < this.buckets_.size(); i++) {
            output.writeMessage(2, this.buckets_.get(i));
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
            size2 = 0 + CodedOutputStream.computeInt64Size(1, this.bucketDurationMs_);
        }
        for (int i = 0; i < this.buckets_.size(); i++) {
            size2 += CodedOutputStream.computeMessageSize(2, this.buckets_.get(i));
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static NetworkStatsHistoryProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (NetworkStatsHistoryProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static NetworkStatsHistoryProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (NetworkStatsHistoryProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static NetworkStatsHistoryProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (NetworkStatsHistoryProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static NetworkStatsHistoryProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (NetworkStatsHistoryProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static NetworkStatsHistoryProto parseFrom(InputStream input) throws IOException {
        return (NetworkStatsHistoryProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static NetworkStatsHistoryProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (NetworkStatsHistoryProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static NetworkStatsHistoryProto parseDelimitedFrom(InputStream input) throws IOException {
        return (NetworkStatsHistoryProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static NetworkStatsHistoryProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (NetworkStatsHistoryProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static NetworkStatsHistoryProto parseFrom(CodedInputStream input) throws IOException {
        return (NetworkStatsHistoryProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static NetworkStatsHistoryProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (NetworkStatsHistoryProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(NetworkStatsHistoryProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<NetworkStatsHistoryProto, Builder> implements NetworkStatsHistoryProtoOrBuilder {
        private Builder() {
            super(NetworkStatsHistoryProto.DEFAULT_INSTANCE);
        }

        @Override // android.service.NetworkStatsHistoryProtoOrBuilder
        public boolean hasBucketDurationMs() {
            return ((NetworkStatsHistoryProto) this.instance).hasBucketDurationMs();
        }

        @Override // android.service.NetworkStatsHistoryProtoOrBuilder
        public long getBucketDurationMs() {
            return ((NetworkStatsHistoryProto) this.instance).getBucketDurationMs();
        }

        public Builder setBucketDurationMs(long value) {
            copyOnWrite();
            ((NetworkStatsHistoryProto) this.instance).setBucketDurationMs(value);
            return this;
        }

        public Builder clearBucketDurationMs() {
            copyOnWrite();
            ((NetworkStatsHistoryProto) this.instance).clearBucketDurationMs();
            return this;
        }

        @Override // android.service.NetworkStatsHistoryProtoOrBuilder
        public List<NetworkStatsHistoryBucketProto> getBucketsList() {
            return Collections.unmodifiableList(((NetworkStatsHistoryProto) this.instance).getBucketsList());
        }

        @Override // android.service.NetworkStatsHistoryProtoOrBuilder
        public int getBucketsCount() {
            return ((NetworkStatsHistoryProto) this.instance).getBucketsCount();
        }

        @Override // android.service.NetworkStatsHistoryProtoOrBuilder
        public NetworkStatsHistoryBucketProto getBuckets(int index) {
            return ((NetworkStatsHistoryProto) this.instance).getBuckets(index);
        }

        public Builder setBuckets(int index, NetworkStatsHistoryBucketProto value) {
            copyOnWrite();
            ((NetworkStatsHistoryProto) this.instance).setBuckets((NetworkStatsHistoryProto) index, (int) value);
            return this;
        }

        public Builder setBuckets(int index, NetworkStatsHistoryBucketProto.Builder builderForValue) {
            copyOnWrite();
            ((NetworkStatsHistoryProto) this.instance).setBuckets((NetworkStatsHistoryProto) index, (int) builderForValue);
            return this;
        }

        public Builder addBuckets(NetworkStatsHistoryBucketProto value) {
            copyOnWrite();
            ((NetworkStatsHistoryProto) this.instance).addBuckets((NetworkStatsHistoryProto) value);
            return this;
        }

        public Builder addBuckets(int index, NetworkStatsHistoryBucketProto value) {
            copyOnWrite();
            ((NetworkStatsHistoryProto) this.instance).addBuckets((NetworkStatsHistoryProto) index, (int) value);
            return this;
        }

        public Builder addBuckets(NetworkStatsHistoryBucketProto.Builder builderForValue) {
            copyOnWrite();
            ((NetworkStatsHistoryProto) this.instance).addBuckets((NetworkStatsHistoryProto) builderForValue);
            return this;
        }

        public Builder addBuckets(int index, NetworkStatsHistoryBucketProto.Builder builderForValue) {
            copyOnWrite();
            ((NetworkStatsHistoryProto) this.instance).addBuckets((NetworkStatsHistoryProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllBuckets(Iterable<? extends NetworkStatsHistoryBucketProto> values) {
            copyOnWrite();
            ((NetworkStatsHistoryProto) this.instance).addAllBuckets(values);
            return this;
        }

        public Builder clearBuckets() {
            copyOnWrite();
            ((NetworkStatsHistoryProto) this.instance).clearBuckets();
            return this;
        }

        public Builder removeBuckets(int index) {
            copyOnWrite();
            ((NetworkStatsHistoryProto) this.instance).removeBuckets(index);
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new NetworkStatsHistoryProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                this.buckets_.makeImmutable();
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                NetworkStatsHistoryProto other = (NetworkStatsHistoryProto) arg1;
                this.bucketDurationMs_ = visitor.visitLong(hasBucketDurationMs(), this.bucketDurationMs_, other.hasBucketDurationMs(), other.bucketDurationMs_);
                this.buckets_ = visitor.visitList(this.buckets_, other.buckets_);
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
                            this.bucketDurationMs_ = input.readInt64();
                        } else if (tag == 18) {
                            if (!this.buckets_.isModifiable()) {
                                this.buckets_ = GeneratedMessageLite.mutableCopy(this.buckets_);
                            }
                            this.buckets_.add((NetworkStatsHistoryBucketProto) input.readMessage(NetworkStatsHistoryBucketProto.parser(), extensionRegistry));
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
                    synchronized (NetworkStatsHistoryProto.class) {
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

    public static NetworkStatsHistoryProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<NetworkStatsHistoryProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
