package android.service.procstats;

import android.util.AggStats;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;

public final class ProcessStatsStateProto extends GeneratedMessageLite<ProcessStatsStateProto, Builder> implements ProcessStatsStateProtoOrBuilder {
    private static final ProcessStatsStateProto DEFAULT_INSTANCE = new ProcessStatsStateProto();
    public static final int DURATION_MS_FIELD_NUMBER = 4;
    public static final int MEMORY_STATE_FIELD_NUMBER = 2;
    private static volatile Parser<ProcessStatsStateProto> PARSER = null;
    public static final int PROCESS_STATE_FIELD_NUMBER = 3;
    public static final int PSS_FIELD_NUMBER = 6;
    public static final int REALTIME_DURATION_MS_FIELD_NUMBER = 9;
    public static final int RSS_FIELD_NUMBER = 8;
    public static final int SAMPLE_SIZE_FIELD_NUMBER = 5;
    public static final int SCREEN_STATE_FIELD_NUMBER = 1;
    public static final int USS_FIELD_NUMBER = 7;
    private int bitField0_;
    private long durationMs_ = 0;
    private int memoryState_ = 0;
    private int processState_ = 0;
    private AggStats pss_;
    private long realtimeDurationMs_ = 0;
    private AggStats rss_;
    private int sampleSize_ = 0;
    private int screenState_ = 0;
    private AggStats uss_;

    private ProcessStatsStateProto() {
    }

    @Override // android.service.procstats.ProcessStatsStateProtoOrBuilder
    public boolean hasScreenState() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // android.service.procstats.ProcessStatsStateProtoOrBuilder
    public ScreenState getScreenState() {
        ScreenState result = ScreenState.forNumber(this.screenState_);
        return result == null ? ScreenState.SCREEN_STATE_UNKNOWN : result;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setScreenState(ScreenState value) {
        if (value != null) {
            this.bitField0_ |= 1;
            this.screenState_ = value.getNumber();
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearScreenState() {
        this.bitField0_ &= -2;
        this.screenState_ = 0;
    }

    @Override // android.service.procstats.ProcessStatsStateProtoOrBuilder
    public boolean hasMemoryState() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // android.service.procstats.ProcessStatsStateProtoOrBuilder
    public MemoryState getMemoryState() {
        MemoryState result = MemoryState.forNumber(this.memoryState_);
        return result == null ? MemoryState.MEMORY_STATE_UNKNOWN : result;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setMemoryState(MemoryState value) {
        if (value != null) {
            this.bitField0_ |= 2;
            this.memoryState_ = value.getNumber();
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearMemoryState() {
        this.bitField0_ &= -3;
        this.memoryState_ = 0;
    }

    @Override // android.service.procstats.ProcessStatsStateProtoOrBuilder
    public boolean hasProcessState() {
        return (this.bitField0_ & 4) == 4;
    }

    @Override // android.service.procstats.ProcessStatsStateProtoOrBuilder
    public ProcessState getProcessState() {
        ProcessState result = ProcessState.forNumber(this.processState_);
        return result == null ? ProcessState.PROCESS_STATE_UNKNOWN : result;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setProcessState(ProcessState value) {
        if (value != null) {
            this.bitField0_ |= 4;
            this.processState_ = value.getNumber();
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearProcessState() {
        this.bitField0_ &= -5;
        this.processState_ = 0;
    }

    @Override // android.service.procstats.ProcessStatsStateProtoOrBuilder
    public boolean hasDurationMs() {
        return (this.bitField0_ & 8) == 8;
    }

    @Override // android.service.procstats.ProcessStatsStateProtoOrBuilder
    public long getDurationMs() {
        return this.durationMs_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setDurationMs(long value) {
        this.bitField0_ |= 8;
        this.durationMs_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearDurationMs() {
        this.bitField0_ &= -9;
        this.durationMs_ = 0;
    }

    @Override // android.service.procstats.ProcessStatsStateProtoOrBuilder
    public boolean hasRealtimeDurationMs() {
        return (this.bitField0_ & 16) == 16;
    }

    @Override // android.service.procstats.ProcessStatsStateProtoOrBuilder
    public long getRealtimeDurationMs() {
        return this.realtimeDurationMs_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setRealtimeDurationMs(long value) {
        this.bitField0_ |= 16;
        this.realtimeDurationMs_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearRealtimeDurationMs() {
        this.bitField0_ &= -17;
        this.realtimeDurationMs_ = 0;
    }

    @Override // android.service.procstats.ProcessStatsStateProtoOrBuilder
    public boolean hasSampleSize() {
        return (this.bitField0_ & 32) == 32;
    }

    @Override // android.service.procstats.ProcessStatsStateProtoOrBuilder
    public int getSampleSize() {
        return this.sampleSize_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setSampleSize(int value) {
        this.bitField0_ |= 32;
        this.sampleSize_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearSampleSize() {
        this.bitField0_ &= -33;
        this.sampleSize_ = 0;
    }

    @Override // android.service.procstats.ProcessStatsStateProtoOrBuilder
    public boolean hasPss() {
        return (this.bitField0_ & 64) == 64;
    }

    @Override // android.service.procstats.ProcessStatsStateProtoOrBuilder
    public AggStats getPss() {
        AggStats aggStats = this.pss_;
        return aggStats == null ? AggStats.getDefaultInstance() : aggStats;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPss(AggStats value) {
        if (value != null) {
            this.pss_ = value;
            this.bitField0_ |= 64;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPss(AggStats.Builder builderForValue) {
        this.pss_ = (AggStats) builderForValue.build();
        this.bitField0_ |= 64;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergePss(AggStats value) {
        AggStats aggStats = this.pss_;
        if (aggStats == null || aggStats == AggStats.getDefaultInstance()) {
            this.pss_ = value;
        } else {
            this.pss_ = (AggStats) ((AggStats.Builder) AggStats.newBuilder(this.pss_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 64;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearPss() {
        this.pss_ = null;
        this.bitField0_ &= -65;
    }

    @Override // android.service.procstats.ProcessStatsStateProtoOrBuilder
    public boolean hasUss() {
        return (this.bitField0_ & 128) == 128;
    }

    @Override // android.service.procstats.ProcessStatsStateProtoOrBuilder
    public AggStats getUss() {
        AggStats aggStats = this.uss_;
        return aggStats == null ? AggStats.getDefaultInstance() : aggStats;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setUss(AggStats value) {
        if (value != null) {
            this.uss_ = value;
            this.bitField0_ |= 128;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setUss(AggStats.Builder builderForValue) {
        this.uss_ = (AggStats) builderForValue.build();
        this.bitField0_ |= 128;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeUss(AggStats value) {
        AggStats aggStats = this.uss_;
        if (aggStats == null || aggStats == AggStats.getDefaultInstance()) {
            this.uss_ = value;
        } else {
            this.uss_ = (AggStats) ((AggStats.Builder) AggStats.newBuilder(this.uss_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 128;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearUss() {
        this.uss_ = null;
        this.bitField0_ &= -129;
    }

    @Override // android.service.procstats.ProcessStatsStateProtoOrBuilder
    public boolean hasRss() {
        return (this.bitField0_ & 256) == 256;
    }

    @Override // android.service.procstats.ProcessStatsStateProtoOrBuilder
    public AggStats getRss() {
        AggStats aggStats = this.rss_;
        return aggStats == null ? AggStats.getDefaultInstance() : aggStats;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setRss(AggStats value) {
        if (value != null) {
            this.rss_ = value;
            this.bitField0_ |= 256;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setRss(AggStats.Builder builderForValue) {
        this.rss_ = (AggStats) builderForValue.build();
        this.bitField0_ |= 256;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeRss(AggStats value) {
        AggStats aggStats = this.rss_;
        if (aggStats == null || aggStats == AggStats.getDefaultInstance()) {
            this.rss_ = value;
        } else {
            this.rss_ = (AggStats) ((AggStats.Builder) AggStats.newBuilder(this.rss_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 256;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearRss() {
        this.rss_ = null;
        this.bitField0_ &= -257;
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeEnum(1, this.screenState_);
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeEnum(2, this.memoryState_);
        }
        if ((this.bitField0_ & 4) == 4) {
            output.writeEnum(3, this.processState_);
        }
        if ((this.bitField0_ & 8) == 8) {
            output.writeInt64(4, this.durationMs_);
        }
        if ((this.bitField0_ & 32) == 32) {
            output.writeInt32(5, this.sampleSize_);
        }
        if ((this.bitField0_ & 64) == 64) {
            output.writeMessage(6, getPss());
        }
        if ((this.bitField0_ & 128) == 128) {
            output.writeMessage(7, getUss());
        }
        if ((this.bitField0_ & 256) == 256) {
            output.writeMessage(8, getRss());
        }
        if ((this.bitField0_ & 16) == 16) {
            output.writeInt64(9, this.realtimeDurationMs_);
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
            size2 = 0 + CodedOutputStream.computeEnumSize(1, this.screenState_);
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeEnumSize(2, this.memoryState_);
        }
        if ((this.bitField0_ & 4) == 4) {
            size2 += CodedOutputStream.computeEnumSize(3, this.processState_);
        }
        if ((this.bitField0_ & 8) == 8) {
            size2 += CodedOutputStream.computeInt64Size(4, this.durationMs_);
        }
        if ((this.bitField0_ & 32) == 32) {
            size2 += CodedOutputStream.computeInt32Size(5, this.sampleSize_);
        }
        if ((this.bitField0_ & 64) == 64) {
            size2 += CodedOutputStream.computeMessageSize(6, getPss());
        }
        if ((this.bitField0_ & 128) == 128) {
            size2 += CodedOutputStream.computeMessageSize(7, getUss());
        }
        if ((this.bitField0_ & 256) == 256) {
            size2 += CodedOutputStream.computeMessageSize(8, getRss());
        }
        if ((this.bitField0_ & 16) == 16) {
            size2 += CodedOutputStream.computeInt64Size(9, this.realtimeDurationMs_);
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static ProcessStatsStateProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (ProcessStatsStateProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static ProcessStatsStateProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (ProcessStatsStateProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static ProcessStatsStateProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (ProcessStatsStateProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static ProcessStatsStateProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (ProcessStatsStateProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static ProcessStatsStateProto parseFrom(InputStream input) throws IOException {
        return (ProcessStatsStateProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static ProcessStatsStateProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (ProcessStatsStateProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static ProcessStatsStateProto parseDelimitedFrom(InputStream input) throws IOException {
        return (ProcessStatsStateProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static ProcessStatsStateProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (ProcessStatsStateProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static ProcessStatsStateProto parseFrom(CodedInputStream input) throws IOException {
        return (ProcessStatsStateProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static ProcessStatsStateProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (ProcessStatsStateProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(ProcessStatsStateProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<ProcessStatsStateProto, Builder> implements ProcessStatsStateProtoOrBuilder {
        private Builder() {
            super(ProcessStatsStateProto.DEFAULT_INSTANCE);
        }

        @Override // android.service.procstats.ProcessStatsStateProtoOrBuilder
        public boolean hasScreenState() {
            return ((ProcessStatsStateProto) this.instance).hasScreenState();
        }

        @Override // android.service.procstats.ProcessStatsStateProtoOrBuilder
        public ScreenState getScreenState() {
            return ((ProcessStatsStateProto) this.instance).getScreenState();
        }

        public Builder setScreenState(ScreenState value) {
            copyOnWrite();
            ((ProcessStatsStateProto) this.instance).setScreenState(value);
            return this;
        }

        public Builder clearScreenState() {
            copyOnWrite();
            ((ProcessStatsStateProto) this.instance).clearScreenState();
            return this;
        }

        @Override // android.service.procstats.ProcessStatsStateProtoOrBuilder
        public boolean hasMemoryState() {
            return ((ProcessStatsStateProto) this.instance).hasMemoryState();
        }

        @Override // android.service.procstats.ProcessStatsStateProtoOrBuilder
        public MemoryState getMemoryState() {
            return ((ProcessStatsStateProto) this.instance).getMemoryState();
        }

        public Builder setMemoryState(MemoryState value) {
            copyOnWrite();
            ((ProcessStatsStateProto) this.instance).setMemoryState(value);
            return this;
        }

        public Builder clearMemoryState() {
            copyOnWrite();
            ((ProcessStatsStateProto) this.instance).clearMemoryState();
            return this;
        }

        @Override // android.service.procstats.ProcessStatsStateProtoOrBuilder
        public boolean hasProcessState() {
            return ((ProcessStatsStateProto) this.instance).hasProcessState();
        }

        @Override // android.service.procstats.ProcessStatsStateProtoOrBuilder
        public ProcessState getProcessState() {
            return ((ProcessStatsStateProto) this.instance).getProcessState();
        }

        public Builder setProcessState(ProcessState value) {
            copyOnWrite();
            ((ProcessStatsStateProto) this.instance).setProcessState(value);
            return this;
        }

        public Builder clearProcessState() {
            copyOnWrite();
            ((ProcessStatsStateProto) this.instance).clearProcessState();
            return this;
        }

        @Override // android.service.procstats.ProcessStatsStateProtoOrBuilder
        public boolean hasDurationMs() {
            return ((ProcessStatsStateProto) this.instance).hasDurationMs();
        }

        @Override // android.service.procstats.ProcessStatsStateProtoOrBuilder
        public long getDurationMs() {
            return ((ProcessStatsStateProto) this.instance).getDurationMs();
        }

        public Builder setDurationMs(long value) {
            copyOnWrite();
            ((ProcessStatsStateProto) this.instance).setDurationMs(value);
            return this;
        }

        public Builder clearDurationMs() {
            copyOnWrite();
            ((ProcessStatsStateProto) this.instance).clearDurationMs();
            return this;
        }

        @Override // android.service.procstats.ProcessStatsStateProtoOrBuilder
        public boolean hasRealtimeDurationMs() {
            return ((ProcessStatsStateProto) this.instance).hasRealtimeDurationMs();
        }

        @Override // android.service.procstats.ProcessStatsStateProtoOrBuilder
        public long getRealtimeDurationMs() {
            return ((ProcessStatsStateProto) this.instance).getRealtimeDurationMs();
        }

        public Builder setRealtimeDurationMs(long value) {
            copyOnWrite();
            ((ProcessStatsStateProto) this.instance).setRealtimeDurationMs(value);
            return this;
        }

        public Builder clearRealtimeDurationMs() {
            copyOnWrite();
            ((ProcessStatsStateProto) this.instance).clearRealtimeDurationMs();
            return this;
        }

        @Override // android.service.procstats.ProcessStatsStateProtoOrBuilder
        public boolean hasSampleSize() {
            return ((ProcessStatsStateProto) this.instance).hasSampleSize();
        }

        @Override // android.service.procstats.ProcessStatsStateProtoOrBuilder
        public int getSampleSize() {
            return ((ProcessStatsStateProto) this.instance).getSampleSize();
        }

        public Builder setSampleSize(int value) {
            copyOnWrite();
            ((ProcessStatsStateProto) this.instance).setSampleSize(value);
            return this;
        }

        public Builder clearSampleSize() {
            copyOnWrite();
            ((ProcessStatsStateProto) this.instance).clearSampleSize();
            return this;
        }

        @Override // android.service.procstats.ProcessStatsStateProtoOrBuilder
        public boolean hasPss() {
            return ((ProcessStatsStateProto) this.instance).hasPss();
        }

        @Override // android.service.procstats.ProcessStatsStateProtoOrBuilder
        public AggStats getPss() {
            return ((ProcessStatsStateProto) this.instance).getPss();
        }

        public Builder setPss(AggStats value) {
            copyOnWrite();
            ((ProcessStatsStateProto) this.instance).setPss((ProcessStatsStateProto) value);
            return this;
        }

        public Builder setPss(AggStats.Builder builderForValue) {
            copyOnWrite();
            ((ProcessStatsStateProto) this.instance).setPss((ProcessStatsStateProto) builderForValue);
            return this;
        }

        public Builder mergePss(AggStats value) {
            copyOnWrite();
            ((ProcessStatsStateProto) this.instance).mergePss(value);
            return this;
        }

        public Builder clearPss() {
            copyOnWrite();
            ((ProcessStatsStateProto) this.instance).clearPss();
            return this;
        }

        @Override // android.service.procstats.ProcessStatsStateProtoOrBuilder
        public boolean hasUss() {
            return ((ProcessStatsStateProto) this.instance).hasUss();
        }

        @Override // android.service.procstats.ProcessStatsStateProtoOrBuilder
        public AggStats getUss() {
            return ((ProcessStatsStateProto) this.instance).getUss();
        }

        public Builder setUss(AggStats value) {
            copyOnWrite();
            ((ProcessStatsStateProto) this.instance).setUss((ProcessStatsStateProto) value);
            return this;
        }

        public Builder setUss(AggStats.Builder builderForValue) {
            copyOnWrite();
            ((ProcessStatsStateProto) this.instance).setUss((ProcessStatsStateProto) builderForValue);
            return this;
        }

        public Builder mergeUss(AggStats value) {
            copyOnWrite();
            ((ProcessStatsStateProto) this.instance).mergeUss(value);
            return this;
        }

        public Builder clearUss() {
            copyOnWrite();
            ((ProcessStatsStateProto) this.instance).clearUss();
            return this;
        }

        @Override // android.service.procstats.ProcessStatsStateProtoOrBuilder
        public boolean hasRss() {
            return ((ProcessStatsStateProto) this.instance).hasRss();
        }

        @Override // android.service.procstats.ProcessStatsStateProtoOrBuilder
        public AggStats getRss() {
            return ((ProcessStatsStateProto) this.instance).getRss();
        }

        public Builder setRss(AggStats value) {
            copyOnWrite();
            ((ProcessStatsStateProto) this.instance).setRss((ProcessStatsStateProto) value);
            return this;
        }

        public Builder setRss(AggStats.Builder builderForValue) {
            copyOnWrite();
            ((ProcessStatsStateProto) this.instance).setRss((ProcessStatsStateProto) builderForValue);
            return this;
        }

        public Builder mergeRss(AggStats value) {
            copyOnWrite();
            ((ProcessStatsStateProto) this.instance).mergeRss(value);
            return this;
        }

        public Builder clearRss() {
            copyOnWrite();
            ((ProcessStatsStateProto) this.instance).clearRss();
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new ProcessStatsStateProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                ProcessStatsStateProto other = (ProcessStatsStateProto) arg1;
                this.screenState_ = visitor.visitInt(hasScreenState(), this.screenState_, other.hasScreenState(), other.screenState_);
                this.memoryState_ = visitor.visitInt(hasMemoryState(), this.memoryState_, other.hasMemoryState(), other.memoryState_);
                this.processState_ = visitor.visitInt(hasProcessState(), this.processState_, other.hasProcessState(), other.processState_);
                this.durationMs_ = visitor.visitLong(hasDurationMs(), this.durationMs_, other.hasDurationMs(), other.durationMs_);
                this.realtimeDurationMs_ = visitor.visitLong(hasRealtimeDurationMs(), this.realtimeDurationMs_, other.hasRealtimeDurationMs(), other.realtimeDurationMs_);
                this.sampleSize_ = visitor.visitInt(hasSampleSize(), this.sampleSize_, other.hasSampleSize(), other.sampleSize_);
                this.pss_ = (AggStats) visitor.visitMessage(this.pss_, other.pss_);
                this.uss_ = (AggStats) visitor.visitMessage(this.uss_, other.uss_);
                this.rss_ = (AggStats) visitor.visitMessage(this.rss_, other.rss_);
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
                            int rawValue = input.readEnum();
                            if (ScreenState.forNumber(rawValue) == null) {
                                super.mergeVarintField(1, rawValue);
                            } else {
                                this.bitField0_ = 1 | this.bitField0_;
                                this.screenState_ = rawValue;
                            }
                        } else if (tag == 16) {
                            int rawValue2 = input.readEnum();
                            if (MemoryState.forNumber(rawValue2) == null) {
                                super.mergeVarintField(2, rawValue2);
                            } else {
                                this.bitField0_ = 2 | this.bitField0_;
                                this.memoryState_ = rawValue2;
                            }
                        } else if (tag == 24) {
                            int rawValue3 = input.readEnum();
                            if (ProcessState.forNumber(rawValue3) == null) {
                                super.mergeVarintField(3, rawValue3);
                            } else {
                                this.bitField0_ |= 4;
                                this.processState_ = rawValue3;
                            }
                        } else if (tag == 32) {
                            this.bitField0_ = 8 | this.bitField0_;
                            this.durationMs_ = input.readInt64();
                        } else if (tag == 40) {
                            this.bitField0_ |= 32;
                            this.sampleSize_ = input.readInt32();
                        } else if (tag == 50) {
                            AggStats.Builder subBuilder = null;
                            if ((this.bitField0_ & 64) == 64) {
                                subBuilder = (AggStats.Builder) this.pss_.toBuilder();
                            }
                            this.pss_ = (AggStats) input.readMessage(AggStats.parser(), extensionRegistry);
                            if (subBuilder != null) {
                                subBuilder.mergeFrom((GeneratedMessageLite) this.pss_);
                                this.pss_ = (AggStats) subBuilder.buildPartial();
                            }
                            this.bitField0_ |= 64;
                        } else if (tag == 58) {
                            AggStats.Builder subBuilder2 = null;
                            if ((this.bitField0_ & 128) == 128) {
                                subBuilder2 = (AggStats.Builder) this.uss_.toBuilder();
                            }
                            this.uss_ = (AggStats) input.readMessage(AggStats.parser(), extensionRegistry);
                            if (subBuilder2 != null) {
                                subBuilder2.mergeFrom((GeneratedMessageLite) this.uss_);
                                this.uss_ = (AggStats) subBuilder2.buildPartial();
                            }
                            this.bitField0_ |= 128;
                        } else if (tag == 66) {
                            AggStats.Builder subBuilder3 = null;
                            if ((this.bitField0_ & 256) == 256) {
                                subBuilder3 = (AggStats.Builder) this.rss_.toBuilder();
                            }
                            this.rss_ = (AggStats) input.readMessage(AggStats.parser(), extensionRegistry);
                            if (subBuilder3 != null) {
                                subBuilder3.mergeFrom((GeneratedMessageLite) this.rss_);
                                this.rss_ = (AggStats) subBuilder3.buildPartial();
                            }
                            this.bitField0_ |= 256;
                        } else if (tag == 72) {
                            this.bitField0_ |= 16;
                            this.realtimeDurationMs_ = input.readInt64();
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
                    synchronized (ProcessStatsStateProto.class) {
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

    public static ProcessStatsStateProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<ProcessStatsStateProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
