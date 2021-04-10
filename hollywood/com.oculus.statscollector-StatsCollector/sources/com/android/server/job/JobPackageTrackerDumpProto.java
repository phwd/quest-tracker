package com.android.server.job;

import com.android.server.job.DataSetProto;
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

public final class JobPackageTrackerDumpProto extends GeneratedMessageLite<JobPackageTrackerDumpProto, Builder> implements JobPackageTrackerDumpProtoOrBuilder {
    public static final int CURRENT_STATS_FIELD_NUMBER = 2;
    private static final JobPackageTrackerDumpProto DEFAULT_INSTANCE = new JobPackageTrackerDumpProto();
    public static final int HISTORICAL_STATS_FIELD_NUMBER = 1;
    private static volatile Parser<JobPackageTrackerDumpProto> PARSER;
    private int bitField0_;
    private DataSetProto currentStats_;
    private Internal.ProtobufList<DataSetProto> historicalStats_ = emptyProtobufList();

    private JobPackageTrackerDumpProto() {
    }

    @Override // com.android.server.job.JobPackageTrackerDumpProtoOrBuilder
    public List<DataSetProto> getHistoricalStatsList() {
        return this.historicalStats_;
    }

    public List<? extends DataSetProtoOrBuilder> getHistoricalStatsOrBuilderList() {
        return this.historicalStats_;
    }

    @Override // com.android.server.job.JobPackageTrackerDumpProtoOrBuilder
    public int getHistoricalStatsCount() {
        return this.historicalStats_.size();
    }

    @Override // com.android.server.job.JobPackageTrackerDumpProtoOrBuilder
    public DataSetProto getHistoricalStats(int index) {
        return this.historicalStats_.get(index);
    }

    public DataSetProtoOrBuilder getHistoricalStatsOrBuilder(int index) {
        return this.historicalStats_.get(index);
    }

    private void ensureHistoricalStatsIsMutable() {
        if (!this.historicalStats_.isModifiable()) {
            this.historicalStats_ = GeneratedMessageLite.mutableCopy(this.historicalStats_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setHistoricalStats(int index, DataSetProto value) {
        if (value != null) {
            ensureHistoricalStatsIsMutable();
            this.historicalStats_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setHistoricalStats(int index, DataSetProto.Builder builderForValue) {
        ensureHistoricalStatsIsMutable();
        this.historicalStats_.set(index, (DataSetProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addHistoricalStats(DataSetProto value) {
        if (value != null) {
            ensureHistoricalStatsIsMutable();
            this.historicalStats_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addHistoricalStats(int index, DataSetProto value) {
        if (value != null) {
            ensureHistoricalStatsIsMutable();
            this.historicalStats_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addHistoricalStats(DataSetProto.Builder builderForValue) {
        ensureHistoricalStatsIsMutable();
        this.historicalStats_.add((DataSetProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addHistoricalStats(int index, DataSetProto.Builder builderForValue) {
        ensureHistoricalStatsIsMutable();
        this.historicalStats_.add(index, (DataSetProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllHistoricalStats(Iterable<? extends DataSetProto> values) {
        ensureHistoricalStatsIsMutable();
        AbstractMessageLite.addAll(values, this.historicalStats_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearHistoricalStats() {
        this.historicalStats_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeHistoricalStats(int index) {
        ensureHistoricalStatsIsMutable();
        this.historicalStats_.remove(index);
    }

    @Override // com.android.server.job.JobPackageTrackerDumpProtoOrBuilder
    public boolean hasCurrentStats() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // com.android.server.job.JobPackageTrackerDumpProtoOrBuilder
    public DataSetProto getCurrentStats() {
        DataSetProto dataSetProto = this.currentStats_;
        return dataSetProto == null ? DataSetProto.getDefaultInstance() : dataSetProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setCurrentStats(DataSetProto value) {
        if (value != null) {
            this.currentStats_ = value;
            this.bitField0_ |= 1;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setCurrentStats(DataSetProto.Builder builderForValue) {
        this.currentStats_ = (DataSetProto) builderForValue.build();
        this.bitField0_ |= 1;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeCurrentStats(DataSetProto value) {
        DataSetProto dataSetProto = this.currentStats_;
        if (dataSetProto == null || dataSetProto == DataSetProto.getDefaultInstance()) {
            this.currentStats_ = value;
        } else {
            this.currentStats_ = (DataSetProto) ((DataSetProto.Builder) DataSetProto.newBuilder(this.currentStats_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 1;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearCurrentStats() {
        this.currentStats_ = null;
        this.bitField0_ &= -2;
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        for (int i = 0; i < this.historicalStats_.size(); i++) {
            output.writeMessage(1, this.historicalStats_.get(i));
        }
        if ((this.bitField0_ & 1) == 1) {
            output.writeMessage(2, getCurrentStats());
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
        for (int i = 0; i < this.historicalStats_.size(); i++) {
            size2 += CodedOutputStream.computeMessageSize(1, this.historicalStats_.get(i));
        }
        if ((this.bitField0_ & 1) == 1) {
            size2 += CodedOutputStream.computeMessageSize(2, getCurrentStats());
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static JobPackageTrackerDumpProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (JobPackageTrackerDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static JobPackageTrackerDumpProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (JobPackageTrackerDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static JobPackageTrackerDumpProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (JobPackageTrackerDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static JobPackageTrackerDumpProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (JobPackageTrackerDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static JobPackageTrackerDumpProto parseFrom(InputStream input) throws IOException {
        return (JobPackageTrackerDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static JobPackageTrackerDumpProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (JobPackageTrackerDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static JobPackageTrackerDumpProto parseDelimitedFrom(InputStream input) throws IOException {
        return (JobPackageTrackerDumpProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static JobPackageTrackerDumpProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (JobPackageTrackerDumpProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static JobPackageTrackerDumpProto parseFrom(CodedInputStream input) throws IOException {
        return (JobPackageTrackerDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static JobPackageTrackerDumpProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (JobPackageTrackerDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(JobPackageTrackerDumpProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<JobPackageTrackerDumpProto, Builder> implements JobPackageTrackerDumpProtoOrBuilder {
        private Builder() {
            super(JobPackageTrackerDumpProto.DEFAULT_INSTANCE);
        }

        @Override // com.android.server.job.JobPackageTrackerDumpProtoOrBuilder
        public List<DataSetProto> getHistoricalStatsList() {
            return Collections.unmodifiableList(((JobPackageTrackerDumpProto) this.instance).getHistoricalStatsList());
        }

        @Override // com.android.server.job.JobPackageTrackerDumpProtoOrBuilder
        public int getHistoricalStatsCount() {
            return ((JobPackageTrackerDumpProto) this.instance).getHistoricalStatsCount();
        }

        @Override // com.android.server.job.JobPackageTrackerDumpProtoOrBuilder
        public DataSetProto getHistoricalStats(int index) {
            return ((JobPackageTrackerDumpProto) this.instance).getHistoricalStats(index);
        }

        public Builder setHistoricalStats(int index, DataSetProto value) {
            copyOnWrite();
            ((JobPackageTrackerDumpProto) this.instance).setHistoricalStats((JobPackageTrackerDumpProto) index, (int) value);
            return this;
        }

        public Builder setHistoricalStats(int index, DataSetProto.Builder builderForValue) {
            copyOnWrite();
            ((JobPackageTrackerDumpProto) this.instance).setHistoricalStats((JobPackageTrackerDumpProto) index, (int) builderForValue);
            return this;
        }

        public Builder addHistoricalStats(DataSetProto value) {
            copyOnWrite();
            ((JobPackageTrackerDumpProto) this.instance).addHistoricalStats((JobPackageTrackerDumpProto) value);
            return this;
        }

        public Builder addHistoricalStats(int index, DataSetProto value) {
            copyOnWrite();
            ((JobPackageTrackerDumpProto) this.instance).addHistoricalStats((JobPackageTrackerDumpProto) index, (int) value);
            return this;
        }

        public Builder addHistoricalStats(DataSetProto.Builder builderForValue) {
            copyOnWrite();
            ((JobPackageTrackerDumpProto) this.instance).addHistoricalStats((JobPackageTrackerDumpProto) builderForValue);
            return this;
        }

        public Builder addHistoricalStats(int index, DataSetProto.Builder builderForValue) {
            copyOnWrite();
            ((JobPackageTrackerDumpProto) this.instance).addHistoricalStats((JobPackageTrackerDumpProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllHistoricalStats(Iterable<? extends DataSetProto> values) {
            copyOnWrite();
            ((JobPackageTrackerDumpProto) this.instance).addAllHistoricalStats(values);
            return this;
        }

        public Builder clearHistoricalStats() {
            copyOnWrite();
            ((JobPackageTrackerDumpProto) this.instance).clearHistoricalStats();
            return this;
        }

        public Builder removeHistoricalStats(int index) {
            copyOnWrite();
            ((JobPackageTrackerDumpProto) this.instance).removeHistoricalStats(index);
            return this;
        }

        @Override // com.android.server.job.JobPackageTrackerDumpProtoOrBuilder
        public boolean hasCurrentStats() {
            return ((JobPackageTrackerDumpProto) this.instance).hasCurrentStats();
        }

        @Override // com.android.server.job.JobPackageTrackerDumpProtoOrBuilder
        public DataSetProto getCurrentStats() {
            return ((JobPackageTrackerDumpProto) this.instance).getCurrentStats();
        }

        public Builder setCurrentStats(DataSetProto value) {
            copyOnWrite();
            ((JobPackageTrackerDumpProto) this.instance).setCurrentStats((JobPackageTrackerDumpProto) value);
            return this;
        }

        public Builder setCurrentStats(DataSetProto.Builder builderForValue) {
            copyOnWrite();
            ((JobPackageTrackerDumpProto) this.instance).setCurrentStats((JobPackageTrackerDumpProto) builderForValue);
            return this;
        }

        public Builder mergeCurrentStats(DataSetProto value) {
            copyOnWrite();
            ((JobPackageTrackerDumpProto) this.instance).mergeCurrentStats(value);
            return this;
        }

        public Builder clearCurrentStats() {
            copyOnWrite();
            ((JobPackageTrackerDumpProto) this.instance).clearCurrentStats();
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new JobPackageTrackerDumpProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                this.historicalStats_.makeImmutable();
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                JobPackageTrackerDumpProto other = (JobPackageTrackerDumpProto) arg1;
                this.historicalStats_ = visitor.visitList(this.historicalStats_, other.historicalStats_);
                this.currentStats_ = (DataSetProto) visitor.visitMessage(this.currentStats_, other.currentStats_);
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
                            if (!this.historicalStats_.isModifiable()) {
                                this.historicalStats_ = GeneratedMessageLite.mutableCopy(this.historicalStats_);
                            }
                            this.historicalStats_.add((DataSetProto) input.readMessage(DataSetProto.parser(), extensionRegistry));
                        } else if (tag == 18) {
                            DataSetProto.Builder subBuilder = null;
                            if ((this.bitField0_ & 1) == 1) {
                                subBuilder = (DataSetProto.Builder) this.currentStats_.toBuilder();
                            }
                            this.currentStats_ = (DataSetProto) input.readMessage(DataSetProto.parser(), extensionRegistry);
                            if (subBuilder != null) {
                                subBuilder.mergeFrom((GeneratedMessageLite) this.currentStats_);
                                this.currentStats_ = (DataSetProto) subBuilder.buildPartial();
                            }
                            this.bitField0_ |= 1;
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
                    synchronized (JobPackageTrackerDumpProto.class) {
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

    public static JobPackageTrackerDumpProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<JobPackageTrackerDumpProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
