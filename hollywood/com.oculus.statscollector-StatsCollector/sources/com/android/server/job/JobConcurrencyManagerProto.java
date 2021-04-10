package com.android.server.job;

import com.android.server.job.JobCountTrackerProto;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;

public final class JobConcurrencyManagerProto extends GeneratedMessageLite<JobConcurrencyManagerProto, Builder> implements JobConcurrencyManagerProtoOrBuilder {
    public static final int CURRENT_INTERACTIVE_FIELD_NUMBER = 1;
    private static final JobConcurrencyManagerProto DEFAULT_INSTANCE = new JobConcurrencyManagerProto();
    public static final int EFFECTIVE_INTERACTIVE_FIELD_NUMBER = 2;
    public static final int JOB_COUNT_TRACKER_FIELD_NUMBER = 5;
    public static final int MEMORY_TRIM_LEVEL_FIELD_NUMBER = 6;
    private static volatile Parser<JobConcurrencyManagerProto> PARSER = null;
    public static final int TIME_SINCE_LAST_SCREEN_OFF_MS_FIELD_NUMBER = 4;
    public static final int TIME_SINCE_LAST_SCREEN_ON_MS_FIELD_NUMBER = 3;
    private int bitField0_;
    private boolean currentInteractive_ = false;
    private boolean effectiveInteractive_ = false;
    private JobCountTrackerProto jobCountTracker_;
    private int memoryTrimLevel_ = 0;
    private long timeSinceLastScreenOffMs_ = 0;
    private long timeSinceLastScreenOnMs_ = 0;

    private JobConcurrencyManagerProto() {
    }

    @Override // com.android.server.job.JobConcurrencyManagerProtoOrBuilder
    public boolean hasCurrentInteractive() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // com.android.server.job.JobConcurrencyManagerProtoOrBuilder
    public boolean getCurrentInteractive() {
        return this.currentInteractive_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setCurrentInteractive(boolean value) {
        this.bitField0_ |= 1;
        this.currentInteractive_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearCurrentInteractive() {
        this.bitField0_ &= -2;
        this.currentInteractive_ = false;
    }

    @Override // com.android.server.job.JobConcurrencyManagerProtoOrBuilder
    public boolean hasEffectiveInteractive() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // com.android.server.job.JobConcurrencyManagerProtoOrBuilder
    public boolean getEffectiveInteractive() {
        return this.effectiveInteractive_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setEffectiveInteractive(boolean value) {
        this.bitField0_ |= 2;
        this.effectiveInteractive_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearEffectiveInteractive() {
        this.bitField0_ &= -3;
        this.effectiveInteractive_ = false;
    }

    @Override // com.android.server.job.JobConcurrencyManagerProtoOrBuilder
    public boolean hasTimeSinceLastScreenOnMs() {
        return (this.bitField0_ & 4) == 4;
    }

    @Override // com.android.server.job.JobConcurrencyManagerProtoOrBuilder
    public long getTimeSinceLastScreenOnMs() {
        return this.timeSinceLastScreenOnMs_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setTimeSinceLastScreenOnMs(long value) {
        this.bitField0_ |= 4;
        this.timeSinceLastScreenOnMs_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearTimeSinceLastScreenOnMs() {
        this.bitField0_ &= -5;
        this.timeSinceLastScreenOnMs_ = 0;
    }

    @Override // com.android.server.job.JobConcurrencyManagerProtoOrBuilder
    public boolean hasTimeSinceLastScreenOffMs() {
        return (this.bitField0_ & 8) == 8;
    }

    @Override // com.android.server.job.JobConcurrencyManagerProtoOrBuilder
    public long getTimeSinceLastScreenOffMs() {
        return this.timeSinceLastScreenOffMs_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setTimeSinceLastScreenOffMs(long value) {
        this.bitField0_ |= 8;
        this.timeSinceLastScreenOffMs_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearTimeSinceLastScreenOffMs() {
        this.bitField0_ &= -9;
        this.timeSinceLastScreenOffMs_ = 0;
    }

    @Override // com.android.server.job.JobConcurrencyManagerProtoOrBuilder
    public boolean hasJobCountTracker() {
        return (this.bitField0_ & 16) == 16;
    }

    @Override // com.android.server.job.JobConcurrencyManagerProtoOrBuilder
    public JobCountTrackerProto getJobCountTracker() {
        JobCountTrackerProto jobCountTrackerProto = this.jobCountTracker_;
        return jobCountTrackerProto == null ? JobCountTrackerProto.getDefaultInstance() : jobCountTrackerProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setJobCountTracker(JobCountTrackerProto value) {
        if (value != null) {
            this.jobCountTracker_ = value;
            this.bitField0_ |= 16;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setJobCountTracker(JobCountTrackerProto.Builder builderForValue) {
        this.jobCountTracker_ = (JobCountTrackerProto) builderForValue.build();
        this.bitField0_ |= 16;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeJobCountTracker(JobCountTrackerProto value) {
        JobCountTrackerProto jobCountTrackerProto = this.jobCountTracker_;
        if (jobCountTrackerProto == null || jobCountTrackerProto == JobCountTrackerProto.getDefaultInstance()) {
            this.jobCountTracker_ = value;
        } else {
            this.jobCountTracker_ = (JobCountTrackerProto) ((JobCountTrackerProto.Builder) JobCountTrackerProto.newBuilder(this.jobCountTracker_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 16;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearJobCountTracker() {
        this.jobCountTracker_ = null;
        this.bitField0_ &= -17;
    }

    @Override // com.android.server.job.JobConcurrencyManagerProtoOrBuilder
    public boolean hasMemoryTrimLevel() {
        return (this.bitField0_ & 32) == 32;
    }

    @Override // com.android.server.job.JobConcurrencyManagerProtoOrBuilder
    public int getMemoryTrimLevel() {
        return this.memoryTrimLevel_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setMemoryTrimLevel(int value) {
        this.bitField0_ |= 32;
        this.memoryTrimLevel_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearMemoryTrimLevel() {
        this.bitField0_ &= -33;
        this.memoryTrimLevel_ = 0;
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeBool(1, this.currentInteractive_);
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeBool(2, this.effectiveInteractive_);
        }
        if ((this.bitField0_ & 4) == 4) {
            output.writeInt64(3, this.timeSinceLastScreenOnMs_);
        }
        if ((this.bitField0_ & 8) == 8) {
            output.writeInt64(4, this.timeSinceLastScreenOffMs_);
        }
        if ((this.bitField0_ & 16) == 16) {
            output.writeMessage(5, getJobCountTracker());
        }
        if ((this.bitField0_ & 32) == 32) {
            output.writeInt32(6, this.memoryTrimLevel_);
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
            size2 = 0 + CodedOutputStream.computeBoolSize(1, this.currentInteractive_);
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeBoolSize(2, this.effectiveInteractive_);
        }
        if ((this.bitField0_ & 4) == 4) {
            size2 += CodedOutputStream.computeInt64Size(3, this.timeSinceLastScreenOnMs_);
        }
        if ((this.bitField0_ & 8) == 8) {
            size2 += CodedOutputStream.computeInt64Size(4, this.timeSinceLastScreenOffMs_);
        }
        if ((this.bitField0_ & 16) == 16) {
            size2 += CodedOutputStream.computeMessageSize(5, getJobCountTracker());
        }
        if ((this.bitField0_ & 32) == 32) {
            size2 += CodedOutputStream.computeInt32Size(6, this.memoryTrimLevel_);
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static JobConcurrencyManagerProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (JobConcurrencyManagerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static JobConcurrencyManagerProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (JobConcurrencyManagerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static JobConcurrencyManagerProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (JobConcurrencyManagerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static JobConcurrencyManagerProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (JobConcurrencyManagerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static JobConcurrencyManagerProto parseFrom(InputStream input) throws IOException {
        return (JobConcurrencyManagerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static JobConcurrencyManagerProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (JobConcurrencyManagerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static JobConcurrencyManagerProto parseDelimitedFrom(InputStream input) throws IOException {
        return (JobConcurrencyManagerProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static JobConcurrencyManagerProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (JobConcurrencyManagerProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static JobConcurrencyManagerProto parseFrom(CodedInputStream input) throws IOException {
        return (JobConcurrencyManagerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static JobConcurrencyManagerProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (JobConcurrencyManagerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(JobConcurrencyManagerProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<JobConcurrencyManagerProto, Builder> implements JobConcurrencyManagerProtoOrBuilder {
        private Builder() {
            super(JobConcurrencyManagerProto.DEFAULT_INSTANCE);
        }

        @Override // com.android.server.job.JobConcurrencyManagerProtoOrBuilder
        public boolean hasCurrentInteractive() {
            return ((JobConcurrencyManagerProto) this.instance).hasCurrentInteractive();
        }

        @Override // com.android.server.job.JobConcurrencyManagerProtoOrBuilder
        public boolean getCurrentInteractive() {
            return ((JobConcurrencyManagerProto) this.instance).getCurrentInteractive();
        }

        public Builder setCurrentInteractive(boolean value) {
            copyOnWrite();
            ((JobConcurrencyManagerProto) this.instance).setCurrentInteractive(value);
            return this;
        }

        public Builder clearCurrentInteractive() {
            copyOnWrite();
            ((JobConcurrencyManagerProto) this.instance).clearCurrentInteractive();
            return this;
        }

        @Override // com.android.server.job.JobConcurrencyManagerProtoOrBuilder
        public boolean hasEffectiveInteractive() {
            return ((JobConcurrencyManagerProto) this.instance).hasEffectiveInteractive();
        }

        @Override // com.android.server.job.JobConcurrencyManagerProtoOrBuilder
        public boolean getEffectiveInteractive() {
            return ((JobConcurrencyManagerProto) this.instance).getEffectiveInteractive();
        }

        public Builder setEffectiveInteractive(boolean value) {
            copyOnWrite();
            ((JobConcurrencyManagerProto) this.instance).setEffectiveInteractive(value);
            return this;
        }

        public Builder clearEffectiveInteractive() {
            copyOnWrite();
            ((JobConcurrencyManagerProto) this.instance).clearEffectiveInteractive();
            return this;
        }

        @Override // com.android.server.job.JobConcurrencyManagerProtoOrBuilder
        public boolean hasTimeSinceLastScreenOnMs() {
            return ((JobConcurrencyManagerProto) this.instance).hasTimeSinceLastScreenOnMs();
        }

        @Override // com.android.server.job.JobConcurrencyManagerProtoOrBuilder
        public long getTimeSinceLastScreenOnMs() {
            return ((JobConcurrencyManagerProto) this.instance).getTimeSinceLastScreenOnMs();
        }

        public Builder setTimeSinceLastScreenOnMs(long value) {
            copyOnWrite();
            ((JobConcurrencyManagerProto) this.instance).setTimeSinceLastScreenOnMs(value);
            return this;
        }

        public Builder clearTimeSinceLastScreenOnMs() {
            copyOnWrite();
            ((JobConcurrencyManagerProto) this.instance).clearTimeSinceLastScreenOnMs();
            return this;
        }

        @Override // com.android.server.job.JobConcurrencyManagerProtoOrBuilder
        public boolean hasTimeSinceLastScreenOffMs() {
            return ((JobConcurrencyManagerProto) this.instance).hasTimeSinceLastScreenOffMs();
        }

        @Override // com.android.server.job.JobConcurrencyManagerProtoOrBuilder
        public long getTimeSinceLastScreenOffMs() {
            return ((JobConcurrencyManagerProto) this.instance).getTimeSinceLastScreenOffMs();
        }

        public Builder setTimeSinceLastScreenOffMs(long value) {
            copyOnWrite();
            ((JobConcurrencyManagerProto) this.instance).setTimeSinceLastScreenOffMs(value);
            return this;
        }

        public Builder clearTimeSinceLastScreenOffMs() {
            copyOnWrite();
            ((JobConcurrencyManagerProto) this.instance).clearTimeSinceLastScreenOffMs();
            return this;
        }

        @Override // com.android.server.job.JobConcurrencyManagerProtoOrBuilder
        public boolean hasJobCountTracker() {
            return ((JobConcurrencyManagerProto) this.instance).hasJobCountTracker();
        }

        @Override // com.android.server.job.JobConcurrencyManagerProtoOrBuilder
        public JobCountTrackerProto getJobCountTracker() {
            return ((JobConcurrencyManagerProto) this.instance).getJobCountTracker();
        }

        public Builder setJobCountTracker(JobCountTrackerProto value) {
            copyOnWrite();
            ((JobConcurrencyManagerProto) this.instance).setJobCountTracker((JobConcurrencyManagerProto) value);
            return this;
        }

        public Builder setJobCountTracker(JobCountTrackerProto.Builder builderForValue) {
            copyOnWrite();
            ((JobConcurrencyManagerProto) this.instance).setJobCountTracker((JobConcurrencyManagerProto) builderForValue);
            return this;
        }

        public Builder mergeJobCountTracker(JobCountTrackerProto value) {
            copyOnWrite();
            ((JobConcurrencyManagerProto) this.instance).mergeJobCountTracker(value);
            return this;
        }

        public Builder clearJobCountTracker() {
            copyOnWrite();
            ((JobConcurrencyManagerProto) this.instance).clearJobCountTracker();
            return this;
        }

        @Override // com.android.server.job.JobConcurrencyManagerProtoOrBuilder
        public boolean hasMemoryTrimLevel() {
            return ((JobConcurrencyManagerProto) this.instance).hasMemoryTrimLevel();
        }

        @Override // com.android.server.job.JobConcurrencyManagerProtoOrBuilder
        public int getMemoryTrimLevel() {
            return ((JobConcurrencyManagerProto) this.instance).getMemoryTrimLevel();
        }

        public Builder setMemoryTrimLevel(int value) {
            copyOnWrite();
            ((JobConcurrencyManagerProto) this.instance).setMemoryTrimLevel(value);
            return this;
        }

        public Builder clearMemoryTrimLevel() {
            copyOnWrite();
            ((JobConcurrencyManagerProto) this.instance).clearMemoryTrimLevel();
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new JobConcurrencyManagerProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                JobConcurrencyManagerProto other = (JobConcurrencyManagerProto) arg1;
                this.currentInteractive_ = visitor.visitBoolean(hasCurrentInteractive(), this.currentInteractive_, other.hasCurrentInteractive(), other.currentInteractive_);
                this.effectiveInteractive_ = visitor.visitBoolean(hasEffectiveInteractive(), this.effectiveInteractive_, other.hasEffectiveInteractive(), other.effectiveInteractive_);
                this.timeSinceLastScreenOnMs_ = visitor.visitLong(hasTimeSinceLastScreenOnMs(), this.timeSinceLastScreenOnMs_, other.hasTimeSinceLastScreenOnMs(), other.timeSinceLastScreenOnMs_);
                this.timeSinceLastScreenOffMs_ = visitor.visitLong(hasTimeSinceLastScreenOffMs(), this.timeSinceLastScreenOffMs_, other.hasTimeSinceLastScreenOffMs(), other.timeSinceLastScreenOffMs_);
                this.jobCountTracker_ = (JobCountTrackerProto) visitor.visitMessage(this.jobCountTracker_, other.jobCountTracker_);
                this.memoryTrimLevel_ = visitor.visitInt(hasMemoryTrimLevel(), this.memoryTrimLevel_, other.hasMemoryTrimLevel(), other.memoryTrimLevel_);
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
                            this.currentInteractive_ = input.readBool();
                        } else if (tag == 16) {
                            this.bitField0_ |= 2;
                            this.effectiveInteractive_ = input.readBool();
                        } else if (tag == 24) {
                            this.bitField0_ |= 4;
                            this.timeSinceLastScreenOnMs_ = input.readInt64();
                        } else if (tag == 32) {
                            this.bitField0_ = 8 | this.bitField0_;
                            this.timeSinceLastScreenOffMs_ = input.readInt64();
                        } else if (tag == 42) {
                            JobCountTrackerProto.Builder subBuilder = null;
                            if ((this.bitField0_ & 16) == 16) {
                                subBuilder = (JobCountTrackerProto.Builder) this.jobCountTracker_.toBuilder();
                            }
                            this.jobCountTracker_ = (JobCountTrackerProto) input.readMessage(JobCountTrackerProto.parser(), extensionRegistry);
                            if (subBuilder != null) {
                                subBuilder.mergeFrom((GeneratedMessageLite) this.jobCountTracker_);
                                this.jobCountTracker_ = (JobCountTrackerProto) subBuilder.buildPartial();
                            }
                            this.bitField0_ = 16 | this.bitField0_;
                        } else if (tag == 48) {
                            this.bitField0_ |= 32;
                            this.memoryTrimLevel_ = input.readInt32();
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
                    synchronized (JobConcurrencyManagerProto.class) {
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

    public static JobConcurrencyManagerProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<JobConcurrencyManagerProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
