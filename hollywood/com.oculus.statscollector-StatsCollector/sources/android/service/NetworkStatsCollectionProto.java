package android.service;

import android.service.NetworkStatsCollectionStatsProto;
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

public final class NetworkStatsCollectionProto extends GeneratedMessageLite<NetworkStatsCollectionProto, Builder> implements NetworkStatsCollectionProtoOrBuilder {
    private static final NetworkStatsCollectionProto DEFAULT_INSTANCE = new NetworkStatsCollectionProto();
    private static volatile Parser<NetworkStatsCollectionProto> PARSER = null;
    public static final int STATS_FIELD_NUMBER = 1;
    private Internal.ProtobufList<NetworkStatsCollectionStatsProto> stats_ = emptyProtobufList();

    private NetworkStatsCollectionProto() {
    }

    @Override // android.service.NetworkStatsCollectionProtoOrBuilder
    public List<NetworkStatsCollectionStatsProto> getStatsList() {
        return this.stats_;
    }

    public List<? extends NetworkStatsCollectionStatsProtoOrBuilder> getStatsOrBuilderList() {
        return this.stats_;
    }

    @Override // android.service.NetworkStatsCollectionProtoOrBuilder
    public int getStatsCount() {
        return this.stats_.size();
    }

    @Override // android.service.NetworkStatsCollectionProtoOrBuilder
    public NetworkStatsCollectionStatsProto getStats(int index) {
        return this.stats_.get(index);
    }

    public NetworkStatsCollectionStatsProtoOrBuilder getStatsOrBuilder(int index) {
        return this.stats_.get(index);
    }

    private void ensureStatsIsMutable() {
        if (!this.stats_.isModifiable()) {
            this.stats_ = GeneratedMessageLite.mutableCopy(this.stats_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setStats(int index, NetworkStatsCollectionStatsProto value) {
        if (value != null) {
            ensureStatsIsMutable();
            this.stats_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setStats(int index, NetworkStatsCollectionStatsProto.Builder builderForValue) {
        ensureStatsIsMutable();
        this.stats_.set(index, (NetworkStatsCollectionStatsProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addStats(NetworkStatsCollectionStatsProto value) {
        if (value != null) {
            ensureStatsIsMutable();
            this.stats_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addStats(int index, NetworkStatsCollectionStatsProto value) {
        if (value != null) {
            ensureStatsIsMutable();
            this.stats_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addStats(NetworkStatsCollectionStatsProto.Builder builderForValue) {
        ensureStatsIsMutable();
        this.stats_.add((NetworkStatsCollectionStatsProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addStats(int index, NetworkStatsCollectionStatsProto.Builder builderForValue) {
        ensureStatsIsMutable();
        this.stats_.add(index, (NetworkStatsCollectionStatsProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllStats(Iterable<? extends NetworkStatsCollectionStatsProto> values) {
        ensureStatsIsMutable();
        AbstractMessageLite.addAll(values, this.stats_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearStats() {
        this.stats_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeStats(int index) {
        ensureStatsIsMutable();
        this.stats_.remove(index);
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        for (int i = 0; i < this.stats_.size(); i++) {
            output.writeMessage(1, this.stats_.get(i));
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
        for (int i = 0; i < this.stats_.size(); i++) {
            size2 += CodedOutputStream.computeMessageSize(1, this.stats_.get(i));
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static NetworkStatsCollectionProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (NetworkStatsCollectionProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static NetworkStatsCollectionProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (NetworkStatsCollectionProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static NetworkStatsCollectionProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (NetworkStatsCollectionProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static NetworkStatsCollectionProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (NetworkStatsCollectionProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static NetworkStatsCollectionProto parseFrom(InputStream input) throws IOException {
        return (NetworkStatsCollectionProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static NetworkStatsCollectionProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (NetworkStatsCollectionProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static NetworkStatsCollectionProto parseDelimitedFrom(InputStream input) throws IOException {
        return (NetworkStatsCollectionProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static NetworkStatsCollectionProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (NetworkStatsCollectionProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static NetworkStatsCollectionProto parseFrom(CodedInputStream input) throws IOException {
        return (NetworkStatsCollectionProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static NetworkStatsCollectionProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (NetworkStatsCollectionProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(NetworkStatsCollectionProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<NetworkStatsCollectionProto, Builder> implements NetworkStatsCollectionProtoOrBuilder {
        private Builder() {
            super(NetworkStatsCollectionProto.DEFAULT_INSTANCE);
        }

        @Override // android.service.NetworkStatsCollectionProtoOrBuilder
        public List<NetworkStatsCollectionStatsProto> getStatsList() {
            return Collections.unmodifiableList(((NetworkStatsCollectionProto) this.instance).getStatsList());
        }

        @Override // android.service.NetworkStatsCollectionProtoOrBuilder
        public int getStatsCount() {
            return ((NetworkStatsCollectionProto) this.instance).getStatsCount();
        }

        @Override // android.service.NetworkStatsCollectionProtoOrBuilder
        public NetworkStatsCollectionStatsProto getStats(int index) {
            return ((NetworkStatsCollectionProto) this.instance).getStats(index);
        }

        public Builder setStats(int index, NetworkStatsCollectionStatsProto value) {
            copyOnWrite();
            ((NetworkStatsCollectionProto) this.instance).setStats((NetworkStatsCollectionProto) index, (int) value);
            return this;
        }

        public Builder setStats(int index, NetworkStatsCollectionStatsProto.Builder builderForValue) {
            copyOnWrite();
            ((NetworkStatsCollectionProto) this.instance).setStats((NetworkStatsCollectionProto) index, (int) builderForValue);
            return this;
        }

        public Builder addStats(NetworkStatsCollectionStatsProto value) {
            copyOnWrite();
            ((NetworkStatsCollectionProto) this.instance).addStats((NetworkStatsCollectionProto) value);
            return this;
        }

        public Builder addStats(int index, NetworkStatsCollectionStatsProto value) {
            copyOnWrite();
            ((NetworkStatsCollectionProto) this.instance).addStats((NetworkStatsCollectionProto) index, (int) value);
            return this;
        }

        public Builder addStats(NetworkStatsCollectionStatsProto.Builder builderForValue) {
            copyOnWrite();
            ((NetworkStatsCollectionProto) this.instance).addStats((NetworkStatsCollectionProto) builderForValue);
            return this;
        }

        public Builder addStats(int index, NetworkStatsCollectionStatsProto.Builder builderForValue) {
            copyOnWrite();
            ((NetworkStatsCollectionProto) this.instance).addStats((NetworkStatsCollectionProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllStats(Iterable<? extends NetworkStatsCollectionStatsProto> values) {
            copyOnWrite();
            ((NetworkStatsCollectionProto) this.instance).addAllStats(values);
            return this;
        }

        public Builder clearStats() {
            copyOnWrite();
            ((NetworkStatsCollectionProto) this.instance).clearStats();
            return this;
        }

        public Builder removeStats(int index) {
            copyOnWrite();
            ((NetworkStatsCollectionProto) this.instance).removeStats(index);
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new NetworkStatsCollectionProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                this.stats_.makeImmutable();
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                this.stats_ = ((GeneratedMessageLite.Visitor) arg0).visitList(this.stats_, ((NetworkStatsCollectionProto) arg1).stats_);
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
                            if (!this.stats_.isModifiable()) {
                                this.stats_ = GeneratedMessageLite.mutableCopy(this.stats_);
                            }
                            this.stats_.add((NetworkStatsCollectionStatsProto) input.readMessage(NetworkStatsCollectionStatsProto.parser(), extensionRegistry));
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
                    synchronized (NetworkStatsCollectionProto.class) {
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

    public static NetworkStatsCollectionProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<NetworkStatsCollectionProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
