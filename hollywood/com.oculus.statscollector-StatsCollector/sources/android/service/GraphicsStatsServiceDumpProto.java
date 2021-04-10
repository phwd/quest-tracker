package android.service;

import android.service.GraphicsStatsProto;
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

public final class GraphicsStatsServiceDumpProto extends GeneratedMessageLite<GraphicsStatsServiceDumpProto, Builder> implements GraphicsStatsServiceDumpProtoOrBuilder {
    private static final GraphicsStatsServiceDumpProto DEFAULT_INSTANCE = new GraphicsStatsServiceDumpProto();
    private static volatile Parser<GraphicsStatsServiceDumpProto> PARSER = null;
    public static final int STATS_FIELD_NUMBER = 1;
    private Internal.ProtobufList<GraphicsStatsProto> stats_ = emptyProtobufList();

    private GraphicsStatsServiceDumpProto() {
    }

    @Override // android.service.GraphicsStatsServiceDumpProtoOrBuilder
    public List<GraphicsStatsProto> getStatsList() {
        return this.stats_;
    }

    public List<? extends GraphicsStatsProtoOrBuilder> getStatsOrBuilderList() {
        return this.stats_;
    }

    @Override // android.service.GraphicsStatsServiceDumpProtoOrBuilder
    public int getStatsCount() {
        return this.stats_.size();
    }

    @Override // android.service.GraphicsStatsServiceDumpProtoOrBuilder
    public GraphicsStatsProto getStats(int index) {
        return this.stats_.get(index);
    }

    public GraphicsStatsProtoOrBuilder getStatsOrBuilder(int index) {
        return this.stats_.get(index);
    }

    private void ensureStatsIsMutable() {
        if (!this.stats_.isModifiable()) {
            this.stats_ = GeneratedMessageLite.mutableCopy(this.stats_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setStats(int index, GraphicsStatsProto value) {
        if (value != null) {
            ensureStatsIsMutable();
            this.stats_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setStats(int index, GraphicsStatsProto.Builder builderForValue) {
        ensureStatsIsMutable();
        this.stats_.set(index, (GraphicsStatsProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addStats(GraphicsStatsProto value) {
        if (value != null) {
            ensureStatsIsMutable();
            this.stats_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addStats(int index, GraphicsStatsProto value) {
        if (value != null) {
            ensureStatsIsMutable();
            this.stats_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addStats(GraphicsStatsProto.Builder builderForValue) {
        ensureStatsIsMutable();
        this.stats_.add((GraphicsStatsProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addStats(int index, GraphicsStatsProto.Builder builderForValue) {
        ensureStatsIsMutable();
        this.stats_.add(index, (GraphicsStatsProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllStats(Iterable<? extends GraphicsStatsProto> values) {
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

    public static GraphicsStatsServiceDumpProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (GraphicsStatsServiceDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static GraphicsStatsServiceDumpProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (GraphicsStatsServiceDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static GraphicsStatsServiceDumpProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (GraphicsStatsServiceDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static GraphicsStatsServiceDumpProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (GraphicsStatsServiceDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static GraphicsStatsServiceDumpProto parseFrom(InputStream input) throws IOException {
        return (GraphicsStatsServiceDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static GraphicsStatsServiceDumpProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (GraphicsStatsServiceDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static GraphicsStatsServiceDumpProto parseDelimitedFrom(InputStream input) throws IOException {
        return (GraphicsStatsServiceDumpProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static GraphicsStatsServiceDumpProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (GraphicsStatsServiceDumpProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static GraphicsStatsServiceDumpProto parseFrom(CodedInputStream input) throws IOException {
        return (GraphicsStatsServiceDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static GraphicsStatsServiceDumpProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (GraphicsStatsServiceDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(GraphicsStatsServiceDumpProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<GraphicsStatsServiceDumpProto, Builder> implements GraphicsStatsServiceDumpProtoOrBuilder {
        private Builder() {
            super(GraphicsStatsServiceDumpProto.DEFAULT_INSTANCE);
        }

        @Override // android.service.GraphicsStatsServiceDumpProtoOrBuilder
        public List<GraphicsStatsProto> getStatsList() {
            return Collections.unmodifiableList(((GraphicsStatsServiceDumpProto) this.instance).getStatsList());
        }

        @Override // android.service.GraphicsStatsServiceDumpProtoOrBuilder
        public int getStatsCount() {
            return ((GraphicsStatsServiceDumpProto) this.instance).getStatsCount();
        }

        @Override // android.service.GraphicsStatsServiceDumpProtoOrBuilder
        public GraphicsStatsProto getStats(int index) {
            return ((GraphicsStatsServiceDumpProto) this.instance).getStats(index);
        }

        public Builder setStats(int index, GraphicsStatsProto value) {
            copyOnWrite();
            ((GraphicsStatsServiceDumpProto) this.instance).setStats((GraphicsStatsServiceDumpProto) index, (int) value);
            return this;
        }

        public Builder setStats(int index, GraphicsStatsProto.Builder builderForValue) {
            copyOnWrite();
            ((GraphicsStatsServiceDumpProto) this.instance).setStats((GraphicsStatsServiceDumpProto) index, (int) builderForValue);
            return this;
        }

        public Builder addStats(GraphicsStatsProto value) {
            copyOnWrite();
            ((GraphicsStatsServiceDumpProto) this.instance).addStats((GraphicsStatsServiceDumpProto) value);
            return this;
        }

        public Builder addStats(int index, GraphicsStatsProto value) {
            copyOnWrite();
            ((GraphicsStatsServiceDumpProto) this.instance).addStats((GraphicsStatsServiceDumpProto) index, (int) value);
            return this;
        }

        public Builder addStats(GraphicsStatsProto.Builder builderForValue) {
            copyOnWrite();
            ((GraphicsStatsServiceDumpProto) this.instance).addStats((GraphicsStatsServiceDumpProto) builderForValue);
            return this;
        }

        public Builder addStats(int index, GraphicsStatsProto.Builder builderForValue) {
            copyOnWrite();
            ((GraphicsStatsServiceDumpProto) this.instance).addStats((GraphicsStatsServiceDumpProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllStats(Iterable<? extends GraphicsStatsProto> values) {
            copyOnWrite();
            ((GraphicsStatsServiceDumpProto) this.instance).addAllStats(values);
            return this;
        }

        public Builder clearStats() {
            copyOnWrite();
            ((GraphicsStatsServiceDumpProto) this.instance).clearStats();
            return this;
        }

        public Builder removeStats(int index) {
            copyOnWrite();
            ((GraphicsStatsServiceDumpProto) this.instance).removeStats(index);
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new GraphicsStatsServiceDumpProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                this.stats_.makeImmutable();
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                this.stats_ = ((GeneratedMessageLite.Visitor) arg0).visitList(this.stats_, ((GraphicsStatsServiceDumpProto) arg1).stats_);
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
                            this.stats_.add((GraphicsStatsProto) input.readMessage(GraphicsStatsProto.parser(), extensionRegistry));
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
                    synchronized (GraphicsStatsServiceDumpProto.class) {
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

    public static GraphicsStatsServiceDumpProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<GraphicsStatsServiceDumpProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
