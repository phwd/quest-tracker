package com.android.server.job;

import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;

public final class JobCountTrackerProto extends GeneratedMessageLite<JobCountTrackerProto, Builder> implements JobCountTrackerProtoOrBuilder {
    public static final int CONFIG_NUM_MAX_BG_JOBS_FIELD_NUMBER = 2;
    public static final int CONFIG_NUM_MAX_TOTAL_JOBS_FIELD_NUMBER = 1;
    public static final int CONFIG_NUM_MIN_BG_JOBS_FIELD_NUMBER = 3;
    private static final JobCountTrackerProto DEFAULT_INSTANCE = new JobCountTrackerProto();
    public static final int NUM_PENDING_BG_JOBS_FIELD_NUMBER = 7;
    public static final int NUM_PENDING_FG_JOBS_FIELD_NUMBER = 6;
    public static final int NUM_RUNNING_BG_JOBS_FIELD_NUMBER = 5;
    public static final int NUM_RUNNING_FG_JOBS_FIELD_NUMBER = 4;
    private static volatile Parser<JobCountTrackerProto> PARSER;
    private int bitField0_;
    private int configNumMaxBgJobs_ = 0;
    private int configNumMaxTotalJobs_ = 0;
    private int configNumMinBgJobs_ = 0;
    private int numPendingBgJobs_ = 0;
    private int numPendingFgJobs_ = 0;
    private int numRunningBgJobs_ = 0;
    private int numRunningFgJobs_ = 0;

    private JobCountTrackerProto() {
    }

    @Override // com.android.server.job.JobCountTrackerProtoOrBuilder
    public boolean hasConfigNumMaxTotalJobs() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // com.android.server.job.JobCountTrackerProtoOrBuilder
    public int getConfigNumMaxTotalJobs() {
        return this.configNumMaxTotalJobs_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setConfigNumMaxTotalJobs(int value) {
        this.bitField0_ |= 1;
        this.configNumMaxTotalJobs_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearConfigNumMaxTotalJobs() {
        this.bitField0_ &= -2;
        this.configNumMaxTotalJobs_ = 0;
    }

    @Override // com.android.server.job.JobCountTrackerProtoOrBuilder
    public boolean hasConfigNumMaxBgJobs() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // com.android.server.job.JobCountTrackerProtoOrBuilder
    public int getConfigNumMaxBgJobs() {
        return this.configNumMaxBgJobs_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setConfigNumMaxBgJobs(int value) {
        this.bitField0_ |= 2;
        this.configNumMaxBgJobs_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearConfigNumMaxBgJobs() {
        this.bitField0_ &= -3;
        this.configNumMaxBgJobs_ = 0;
    }

    @Override // com.android.server.job.JobCountTrackerProtoOrBuilder
    public boolean hasConfigNumMinBgJobs() {
        return (this.bitField0_ & 4) == 4;
    }

    @Override // com.android.server.job.JobCountTrackerProtoOrBuilder
    public int getConfigNumMinBgJobs() {
        return this.configNumMinBgJobs_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setConfigNumMinBgJobs(int value) {
        this.bitField0_ |= 4;
        this.configNumMinBgJobs_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearConfigNumMinBgJobs() {
        this.bitField0_ &= -5;
        this.configNumMinBgJobs_ = 0;
    }

    @Override // com.android.server.job.JobCountTrackerProtoOrBuilder
    public boolean hasNumRunningFgJobs() {
        return (this.bitField0_ & 8) == 8;
    }

    @Override // com.android.server.job.JobCountTrackerProtoOrBuilder
    public int getNumRunningFgJobs() {
        return this.numRunningFgJobs_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setNumRunningFgJobs(int value) {
        this.bitField0_ |= 8;
        this.numRunningFgJobs_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearNumRunningFgJobs() {
        this.bitField0_ &= -9;
        this.numRunningFgJobs_ = 0;
    }

    @Override // com.android.server.job.JobCountTrackerProtoOrBuilder
    public boolean hasNumRunningBgJobs() {
        return (this.bitField0_ & 16) == 16;
    }

    @Override // com.android.server.job.JobCountTrackerProtoOrBuilder
    public int getNumRunningBgJobs() {
        return this.numRunningBgJobs_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setNumRunningBgJobs(int value) {
        this.bitField0_ |= 16;
        this.numRunningBgJobs_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearNumRunningBgJobs() {
        this.bitField0_ &= -17;
        this.numRunningBgJobs_ = 0;
    }

    @Override // com.android.server.job.JobCountTrackerProtoOrBuilder
    public boolean hasNumPendingFgJobs() {
        return (this.bitField0_ & 32) == 32;
    }

    @Override // com.android.server.job.JobCountTrackerProtoOrBuilder
    public int getNumPendingFgJobs() {
        return this.numPendingFgJobs_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setNumPendingFgJobs(int value) {
        this.bitField0_ |= 32;
        this.numPendingFgJobs_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearNumPendingFgJobs() {
        this.bitField0_ &= -33;
        this.numPendingFgJobs_ = 0;
    }

    @Override // com.android.server.job.JobCountTrackerProtoOrBuilder
    public boolean hasNumPendingBgJobs() {
        return (this.bitField0_ & 64) == 64;
    }

    @Override // com.android.server.job.JobCountTrackerProtoOrBuilder
    public int getNumPendingBgJobs() {
        return this.numPendingBgJobs_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setNumPendingBgJobs(int value) {
        this.bitField0_ |= 64;
        this.numPendingBgJobs_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearNumPendingBgJobs() {
        this.bitField0_ &= -65;
        this.numPendingBgJobs_ = 0;
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeInt32(1, this.configNumMaxTotalJobs_);
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeInt32(2, this.configNumMaxBgJobs_);
        }
        if ((this.bitField0_ & 4) == 4) {
            output.writeInt32(3, this.configNumMinBgJobs_);
        }
        if ((this.bitField0_ & 8) == 8) {
            output.writeInt32(4, this.numRunningFgJobs_);
        }
        if ((this.bitField0_ & 16) == 16) {
            output.writeInt32(5, this.numRunningBgJobs_);
        }
        if ((this.bitField0_ & 32) == 32) {
            output.writeInt32(6, this.numPendingFgJobs_);
        }
        if ((this.bitField0_ & 64) == 64) {
            output.writeInt32(7, this.numPendingBgJobs_);
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
            size2 = 0 + CodedOutputStream.computeInt32Size(1, this.configNumMaxTotalJobs_);
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeInt32Size(2, this.configNumMaxBgJobs_);
        }
        if ((this.bitField0_ & 4) == 4) {
            size2 += CodedOutputStream.computeInt32Size(3, this.configNumMinBgJobs_);
        }
        if ((this.bitField0_ & 8) == 8) {
            size2 += CodedOutputStream.computeInt32Size(4, this.numRunningFgJobs_);
        }
        if ((this.bitField0_ & 16) == 16) {
            size2 += CodedOutputStream.computeInt32Size(5, this.numRunningBgJobs_);
        }
        if ((this.bitField0_ & 32) == 32) {
            size2 += CodedOutputStream.computeInt32Size(6, this.numPendingFgJobs_);
        }
        if ((this.bitField0_ & 64) == 64) {
            size2 += CodedOutputStream.computeInt32Size(7, this.numPendingBgJobs_);
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static JobCountTrackerProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (JobCountTrackerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static JobCountTrackerProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (JobCountTrackerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static JobCountTrackerProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (JobCountTrackerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static JobCountTrackerProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (JobCountTrackerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static JobCountTrackerProto parseFrom(InputStream input) throws IOException {
        return (JobCountTrackerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static JobCountTrackerProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (JobCountTrackerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static JobCountTrackerProto parseDelimitedFrom(InputStream input) throws IOException {
        return (JobCountTrackerProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static JobCountTrackerProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (JobCountTrackerProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static JobCountTrackerProto parseFrom(CodedInputStream input) throws IOException {
        return (JobCountTrackerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static JobCountTrackerProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (JobCountTrackerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(JobCountTrackerProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<JobCountTrackerProto, Builder> implements JobCountTrackerProtoOrBuilder {
        private Builder() {
            super(JobCountTrackerProto.DEFAULT_INSTANCE);
        }

        @Override // com.android.server.job.JobCountTrackerProtoOrBuilder
        public boolean hasConfigNumMaxTotalJobs() {
            return ((JobCountTrackerProto) this.instance).hasConfigNumMaxTotalJobs();
        }

        @Override // com.android.server.job.JobCountTrackerProtoOrBuilder
        public int getConfigNumMaxTotalJobs() {
            return ((JobCountTrackerProto) this.instance).getConfigNumMaxTotalJobs();
        }

        public Builder setConfigNumMaxTotalJobs(int value) {
            copyOnWrite();
            ((JobCountTrackerProto) this.instance).setConfigNumMaxTotalJobs(value);
            return this;
        }

        public Builder clearConfigNumMaxTotalJobs() {
            copyOnWrite();
            ((JobCountTrackerProto) this.instance).clearConfigNumMaxTotalJobs();
            return this;
        }

        @Override // com.android.server.job.JobCountTrackerProtoOrBuilder
        public boolean hasConfigNumMaxBgJobs() {
            return ((JobCountTrackerProto) this.instance).hasConfigNumMaxBgJobs();
        }

        @Override // com.android.server.job.JobCountTrackerProtoOrBuilder
        public int getConfigNumMaxBgJobs() {
            return ((JobCountTrackerProto) this.instance).getConfigNumMaxBgJobs();
        }

        public Builder setConfigNumMaxBgJobs(int value) {
            copyOnWrite();
            ((JobCountTrackerProto) this.instance).setConfigNumMaxBgJobs(value);
            return this;
        }

        public Builder clearConfigNumMaxBgJobs() {
            copyOnWrite();
            ((JobCountTrackerProto) this.instance).clearConfigNumMaxBgJobs();
            return this;
        }

        @Override // com.android.server.job.JobCountTrackerProtoOrBuilder
        public boolean hasConfigNumMinBgJobs() {
            return ((JobCountTrackerProto) this.instance).hasConfigNumMinBgJobs();
        }

        @Override // com.android.server.job.JobCountTrackerProtoOrBuilder
        public int getConfigNumMinBgJobs() {
            return ((JobCountTrackerProto) this.instance).getConfigNumMinBgJobs();
        }

        public Builder setConfigNumMinBgJobs(int value) {
            copyOnWrite();
            ((JobCountTrackerProto) this.instance).setConfigNumMinBgJobs(value);
            return this;
        }

        public Builder clearConfigNumMinBgJobs() {
            copyOnWrite();
            ((JobCountTrackerProto) this.instance).clearConfigNumMinBgJobs();
            return this;
        }

        @Override // com.android.server.job.JobCountTrackerProtoOrBuilder
        public boolean hasNumRunningFgJobs() {
            return ((JobCountTrackerProto) this.instance).hasNumRunningFgJobs();
        }

        @Override // com.android.server.job.JobCountTrackerProtoOrBuilder
        public int getNumRunningFgJobs() {
            return ((JobCountTrackerProto) this.instance).getNumRunningFgJobs();
        }

        public Builder setNumRunningFgJobs(int value) {
            copyOnWrite();
            ((JobCountTrackerProto) this.instance).setNumRunningFgJobs(value);
            return this;
        }

        public Builder clearNumRunningFgJobs() {
            copyOnWrite();
            ((JobCountTrackerProto) this.instance).clearNumRunningFgJobs();
            return this;
        }

        @Override // com.android.server.job.JobCountTrackerProtoOrBuilder
        public boolean hasNumRunningBgJobs() {
            return ((JobCountTrackerProto) this.instance).hasNumRunningBgJobs();
        }

        @Override // com.android.server.job.JobCountTrackerProtoOrBuilder
        public int getNumRunningBgJobs() {
            return ((JobCountTrackerProto) this.instance).getNumRunningBgJobs();
        }

        public Builder setNumRunningBgJobs(int value) {
            copyOnWrite();
            ((JobCountTrackerProto) this.instance).setNumRunningBgJobs(value);
            return this;
        }

        public Builder clearNumRunningBgJobs() {
            copyOnWrite();
            ((JobCountTrackerProto) this.instance).clearNumRunningBgJobs();
            return this;
        }

        @Override // com.android.server.job.JobCountTrackerProtoOrBuilder
        public boolean hasNumPendingFgJobs() {
            return ((JobCountTrackerProto) this.instance).hasNumPendingFgJobs();
        }

        @Override // com.android.server.job.JobCountTrackerProtoOrBuilder
        public int getNumPendingFgJobs() {
            return ((JobCountTrackerProto) this.instance).getNumPendingFgJobs();
        }

        public Builder setNumPendingFgJobs(int value) {
            copyOnWrite();
            ((JobCountTrackerProto) this.instance).setNumPendingFgJobs(value);
            return this;
        }

        public Builder clearNumPendingFgJobs() {
            copyOnWrite();
            ((JobCountTrackerProto) this.instance).clearNumPendingFgJobs();
            return this;
        }

        @Override // com.android.server.job.JobCountTrackerProtoOrBuilder
        public boolean hasNumPendingBgJobs() {
            return ((JobCountTrackerProto) this.instance).hasNumPendingBgJobs();
        }

        @Override // com.android.server.job.JobCountTrackerProtoOrBuilder
        public int getNumPendingBgJobs() {
            return ((JobCountTrackerProto) this.instance).getNumPendingBgJobs();
        }

        public Builder setNumPendingBgJobs(int value) {
            copyOnWrite();
            ((JobCountTrackerProto) this.instance).setNumPendingBgJobs(value);
            return this;
        }

        public Builder clearNumPendingBgJobs() {
            copyOnWrite();
            ((JobCountTrackerProto) this.instance).clearNumPendingBgJobs();
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new JobCountTrackerProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                JobCountTrackerProto other = (JobCountTrackerProto) arg1;
                this.configNumMaxTotalJobs_ = visitor.visitInt(hasConfigNumMaxTotalJobs(), this.configNumMaxTotalJobs_, other.hasConfigNumMaxTotalJobs(), other.configNumMaxTotalJobs_);
                this.configNumMaxBgJobs_ = visitor.visitInt(hasConfigNumMaxBgJobs(), this.configNumMaxBgJobs_, other.hasConfigNumMaxBgJobs(), other.configNumMaxBgJobs_);
                this.configNumMinBgJobs_ = visitor.visitInt(hasConfigNumMinBgJobs(), this.configNumMinBgJobs_, other.hasConfigNumMinBgJobs(), other.configNumMinBgJobs_);
                this.numRunningFgJobs_ = visitor.visitInt(hasNumRunningFgJobs(), this.numRunningFgJobs_, other.hasNumRunningFgJobs(), other.numRunningFgJobs_);
                this.numRunningBgJobs_ = visitor.visitInt(hasNumRunningBgJobs(), this.numRunningBgJobs_, other.hasNumRunningBgJobs(), other.numRunningBgJobs_);
                this.numPendingFgJobs_ = visitor.visitInt(hasNumPendingFgJobs(), this.numPendingFgJobs_, other.hasNumPendingFgJobs(), other.numPendingFgJobs_);
                this.numPendingBgJobs_ = visitor.visitInt(hasNumPendingBgJobs(), this.numPendingBgJobs_, other.hasNumPendingBgJobs(), other.numPendingBgJobs_);
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
                            this.configNumMaxTotalJobs_ = input.readInt32();
                        } else if (tag == 16) {
                            this.bitField0_ |= 2;
                            this.configNumMaxBgJobs_ = input.readInt32();
                        } else if (tag == 24) {
                            this.bitField0_ |= 4;
                            this.configNumMinBgJobs_ = input.readInt32();
                        } else if (tag == 32) {
                            this.bitField0_ = 8 | this.bitField0_;
                            this.numRunningFgJobs_ = input.readInt32();
                        } else if (tag == 40) {
                            this.bitField0_ |= 16;
                            this.numRunningBgJobs_ = input.readInt32();
                        } else if (tag == 48) {
                            this.bitField0_ |= 32;
                            this.numPendingFgJobs_ = input.readInt32();
                        } else if (tag == 56) {
                            this.bitField0_ |= 64;
                            this.numPendingBgJobs_ = input.readInt32();
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
                    synchronized (JobCountTrackerProto.class) {
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

    public static JobCountTrackerProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<JobCountTrackerProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
