package android.os;

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

public final class StatsDataDumpProto extends GeneratedMessageLite<StatsDataDumpProto, Builder> implements StatsDataDumpProtoOrBuilder {
    public static final int CONFIG_METRICS_REPORT_LIST_FIELD_NUMBER = 1;
    private static final StatsDataDumpProto DEFAULT_INSTANCE = new StatsDataDumpProto();
    private static volatile Parser<StatsDataDumpProto> PARSER;
    private Internal.ProtobufList<ByteString> configMetricsReportList_ = emptyProtobufList();

    private StatsDataDumpProto() {
    }

    @Override // android.os.StatsDataDumpProtoOrBuilder
    public List<ByteString> getConfigMetricsReportListList() {
        return this.configMetricsReportList_;
    }

    @Override // android.os.StatsDataDumpProtoOrBuilder
    public int getConfigMetricsReportListCount() {
        return this.configMetricsReportList_.size();
    }

    @Override // android.os.StatsDataDumpProtoOrBuilder
    public ByteString getConfigMetricsReportList(int index) {
        return this.configMetricsReportList_.get(index);
    }

    private void ensureConfigMetricsReportListIsMutable() {
        if (!this.configMetricsReportList_.isModifiable()) {
            this.configMetricsReportList_ = GeneratedMessageLite.mutableCopy(this.configMetricsReportList_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setConfigMetricsReportList(int index, ByteString value) {
        if (value != null) {
            ensureConfigMetricsReportListIsMutable();
            this.configMetricsReportList_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addConfigMetricsReportList(ByteString value) {
        if (value != null) {
            ensureConfigMetricsReportListIsMutable();
            this.configMetricsReportList_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllConfigMetricsReportList(Iterable<? extends ByteString> values) {
        ensureConfigMetricsReportListIsMutable();
        AbstractMessageLite.addAll(values, this.configMetricsReportList_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearConfigMetricsReportList() {
        this.configMetricsReportList_ = emptyProtobufList();
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        for (int i = 0; i < this.configMetricsReportList_.size(); i++) {
            output.writeBytes(1, this.configMetricsReportList_.get(i));
        }
        this.unknownFields.writeTo(output);
    }

    @Override // com.google.protobuf.MessageLite
    public int getSerializedSize() {
        int size = this.memoizedSerializedSize;
        if (size != -1) {
            return size;
        }
        int dataSize = 0;
        for (int i = 0; i < this.configMetricsReportList_.size(); i++) {
            dataSize += CodedOutputStream.computeBytesSizeNoTag(this.configMetricsReportList_.get(i));
        }
        int size2 = 0 + dataSize + (getConfigMetricsReportListList().size() * 1) + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size2;
        return size2;
    }

    public static StatsDataDumpProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (StatsDataDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static StatsDataDumpProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (StatsDataDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static StatsDataDumpProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (StatsDataDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static StatsDataDumpProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (StatsDataDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static StatsDataDumpProto parseFrom(InputStream input) throws IOException {
        return (StatsDataDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static StatsDataDumpProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (StatsDataDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static StatsDataDumpProto parseDelimitedFrom(InputStream input) throws IOException {
        return (StatsDataDumpProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static StatsDataDumpProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (StatsDataDumpProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static StatsDataDumpProto parseFrom(CodedInputStream input) throws IOException {
        return (StatsDataDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static StatsDataDumpProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (StatsDataDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(StatsDataDumpProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<StatsDataDumpProto, Builder> implements StatsDataDumpProtoOrBuilder {
        private Builder() {
            super(StatsDataDumpProto.DEFAULT_INSTANCE);
        }

        @Override // android.os.StatsDataDumpProtoOrBuilder
        public List<ByteString> getConfigMetricsReportListList() {
            return Collections.unmodifiableList(((StatsDataDumpProto) this.instance).getConfigMetricsReportListList());
        }

        @Override // android.os.StatsDataDumpProtoOrBuilder
        public int getConfigMetricsReportListCount() {
            return ((StatsDataDumpProto) this.instance).getConfigMetricsReportListCount();
        }

        @Override // android.os.StatsDataDumpProtoOrBuilder
        public ByteString getConfigMetricsReportList(int index) {
            return ((StatsDataDumpProto) this.instance).getConfigMetricsReportList(index);
        }

        public Builder setConfigMetricsReportList(int index, ByteString value) {
            copyOnWrite();
            ((StatsDataDumpProto) this.instance).setConfigMetricsReportList(index, value);
            return this;
        }

        public Builder addConfigMetricsReportList(ByteString value) {
            copyOnWrite();
            ((StatsDataDumpProto) this.instance).addConfigMetricsReportList(value);
            return this;
        }

        public Builder addAllConfigMetricsReportList(Iterable<? extends ByteString> values) {
            copyOnWrite();
            ((StatsDataDumpProto) this.instance).addAllConfigMetricsReportList(values);
            return this;
        }

        public Builder clearConfigMetricsReportList() {
            copyOnWrite();
            ((StatsDataDumpProto) this.instance).clearConfigMetricsReportList();
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new StatsDataDumpProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                this.configMetricsReportList_.makeImmutable();
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                this.configMetricsReportList_ = ((GeneratedMessageLite.Visitor) arg0).visitList(this.configMetricsReportList_, ((StatsDataDumpProto) arg1).configMetricsReportList_);
                GeneratedMessageLite.MergeFromVisitor mergeFromVisitor = GeneratedMessageLite.MergeFromVisitor.INSTANCE;
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
                        } else if (tag == 10) {
                            if (!this.configMetricsReportList_.isModifiable()) {
                                this.configMetricsReportList_ = GeneratedMessageLite.mutableCopy(this.configMetricsReportList_);
                            }
                            this.configMetricsReportList_.add(input.readBytes());
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
                    synchronized (StatsDataDumpProto.class) {
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

    public static StatsDataDumpProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<StatsDataDumpProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
