package com.android.server.job;

import com.android.server.job.ConstantsProto;
import com.android.server.job.JobConcurrencyManagerProto;
import com.android.server.job.JobPackageHistoryProto;
import com.android.server.job.JobPackageTrackerDumpProto;
import com.android.server.job.JobStatusDumpProto;
import com.android.server.job.JobStatusShortInfoProto;
import com.android.server.job.StateControllerProto;
import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;

public final class JobSchedulerServiceDumpProto extends GeneratedMessageLite<JobSchedulerServiceDumpProto, Builder> implements JobSchedulerServiceDumpProtoOrBuilder {
    public static final int ACTIVE_JOBS_FIELD_NUMBER = 10;
    public static final int BACKING_UP_UIDS_FIELD_NUMBER = 6;
    public static final int CONCURRENCY_MANAGER_FIELD_NUMBER = 20;
    public static final int CONTROLLERS_FIELD_NUMBER = 4;
    public static final int CURRENT_HEARTBEAT_FIELD_NUMBER = 14;
    private static final JobSchedulerServiceDumpProto DEFAULT_INSTANCE = new JobSchedulerServiceDumpProto();
    public static final int HISTORY_FIELD_NUMBER = 7;
    public static final int IN_PAROLE_FIELD_NUMBER = 18;
    public static final int IN_THERMAL_FIELD_NUMBER = 19;
    public static final int IS_READY_TO_ROCK_FIELD_NUMBER = 11;
    public static final int LAST_HEARTBEAT_TIME_MILLIS_FIELD_NUMBER = 16;
    public static final int MAX_ACTIVE_JOBS_FIELD_NUMBER = 13;
    public static final int NEXT_HEARTBEAT_FIELD_NUMBER = 15;
    public static final int NEXT_HEARTBEAT_TIME_MILLIS_FIELD_NUMBER = 17;
    public static final int PACKAGE_TRACKER_FIELD_NUMBER = 8;
    private static volatile Parser<JobSchedulerServiceDumpProto> PARSER = null;
    public static final int PENDING_JOBS_FIELD_NUMBER = 9;
    public static final int PRIORITY_OVERRIDES_FIELD_NUMBER = 5;
    public static final int REGISTERED_JOBS_FIELD_NUMBER = 3;
    public static final int REPORTED_ACTIVE_FIELD_NUMBER = 12;
    public static final int SETTINGS_FIELD_NUMBER = 1;
    public static final int STARTED_USERS_FIELD_NUMBER = 2;
    private Internal.ProtobufList<ActiveJob> activeJobs_ = emptyProtobufList();
    private Internal.IntList backingUpUids_ = emptyIntList();
    private int bitField0_;
    private JobConcurrencyManagerProto concurrencyManager_;
    private Internal.ProtobufList<StateControllerProto> controllers_ = emptyProtobufList();
    private int currentHeartbeat_ = 0;
    private JobPackageHistoryProto history_;
    private boolean inParole_ = false;
    private boolean inThermal_ = false;
    private boolean isReadyToRock_ = false;
    private long lastHeartbeatTimeMillis_ = 0;
    private int maxActiveJobs_ = 0;
    private long nextHeartbeatTimeMillis_ = 0;
    private Internal.IntList nextHeartbeat_ = emptyIntList();
    private JobPackageTrackerDumpProto packageTracker_;
    private Internal.ProtobufList<PendingJob> pendingJobs_ = emptyProtobufList();
    private Internal.ProtobufList<PriorityOverride> priorityOverrides_ = emptyProtobufList();
    private Internal.ProtobufList<RegisteredJob> registeredJobs_ = emptyProtobufList();
    private boolean reportedActive_ = false;
    private ConstantsProto settings_;
    private Internal.IntList startedUsers_ = emptyIntList();

    public interface ActiveJobOrBuilder extends MessageLiteOrBuilder {
        ActiveJob.InactiveJob getInactive();

        ActiveJob.JobCase getJobCase();

        ActiveJob.RunningJob getRunning();

        boolean hasInactive();

        boolean hasRunning();
    }

    public interface PendingJobOrBuilder extends MessageLiteOrBuilder {
        JobStatusDumpProto getDump();

        long getEnqueuedDurationMs();

        int getEvaluatedPriority();

        JobStatusShortInfoProto getInfo();

        boolean hasDump();

        boolean hasEnqueuedDurationMs();

        boolean hasEvaluatedPriority();

        boolean hasInfo();
    }

    public interface PriorityOverrideOrBuilder extends MessageLiteOrBuilder {
        int getOverrideValue();

        int getUid();

        boolean hasOverrideValue();

        boolean hasUid();
    }

    public interface RegisteredJobOrBuilder extends MessageLiteOrBuilder {
        JobStatusDumpProto getDump();

        JobStatusShortInfoProto getInfo();

        boolean getIsComponentPresent();

        boolean getIsJobCurrentlyActive();

        boolean getIsJobPending();

        boolean getIsJobReady();

        boolean getIsUidBackingUp();

        boolean getIsUserStarted();

        long getLastRunHeartbeat();

        boolean hasDump();

        boolean hasInfo();

        boolean hasIsComponentPresent();

        boolean hasIsJobCurrentlyActive();

        boolean hasIsJobPending();

        boolean hasIsJobReady();

        boolean hasIsUidBackingUp();

        boolean hasIsUserStarted();

        boolean hasLastRunHeartbeat();
    }

    private JobSchedulerServiceDumpProto() {
    }

    public static final class RegisteredJob extends GeneratedMessageLite<RegisteredJob, Builder> implements RegisteredJobOrBuilder {
        private static final RegisteredJob DEFAULT_INSTANCE = new RegisteredJob();
        public static final int DUMP_FIELD_NUMBER = 2;
        public static final int INFO_FIELD_NUMBER = 1;
        public static final int IS_COMPONENT_PRESENT_FIELD_NUMBER = 8;
        public static final int IS_JOB_CURRENTLY_ACTIVE_FIELD_NUMBER = 6;
        public static final int IS_JOB_PENDING_FIELD_NUMBER = 5;
        public static final int IS_JOB_READY_FIELD_NUMBER = 3;
        public static final int IS_UID_BACKING_UP_FIELD_NUMBER = 7;
        public static final int IS_USER_STARTED_FIELD_NUMBER = 4;
        public static final int LAST_RUN_HEARTBEAT_FIELD_NUMBER = 9;
        private static volatile Parser<RegisteredJob> PARSER;
        private int bitField0_;
        private JobStatusDumpProto dump_;
        private JobStatusShortInfoProto info_;
        private boolean isComponentPresent_ = false;
        private boolean isJobCurrentlyActive_ = false;
        private boolean isJobPending_ = false;
        private boolean isJobReady_ = false;
        private boolean isUidBackingUp_ = false;
        private boolean isUserStarted_ = false;
        private long lastRunHeartbeat_ = 0;

        private RegisteredJob() {
        }

        @Override // com.android.server.job.JobSchedulerServiceDumpProto.RegisteredJobOrBuilder
        public boolean hasInfo() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // com.android.server.job.JobSchedulerServiceDumpProto.RegisteredJobOrBuilder
        public JobStatusShortInfoProto getInfo() {
            JobStatusShortInfoProto jobStatusShortInfoProto = this.info_;
            return jobStatusShortInfoProto == null ? JobStatusShortInfoProto.getDefaultInstance() : jobStatusShortInfoProto;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setInfo(JobStatusShortInfoProto value) {
            if (value != null) {
                this.info_ = value;
                this.bitField0_ |= 1;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setInfo(JobStatusShortInfoProto.Builder builderForValue) {
            this.info_ = (JobStatusShortInfoProto) builderForValue.build();
            this.bitField0_ |= 1;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void mergeInfo(JobStatusShortInfoProto value) {
            JobStatusShortInfoProto jobStatusShortInfoProto = this.info_;
            if (jobStatusShortInfoProto == null || jobStatusShortInfoProto == JobStatusShortInfoProto.getDefaultInstance()) {
                this.info_ = value;
            } else {
                this.info_ = (JobStatusShortInfoProto) ((JobStatusShortInfoProto.Builder) JobStatusShortInfoProto.newBuilder(this.info_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
            }
            this.bitField0_ |= 1;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearInfo() {
            this.info_ = null;
            this.bitField0_ &= -2;
        }

        @Override // com.android.server.job.JobSchedulerServiceDumpProto.RegisteredJobOrBuilder
        public boolean hasDump() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // com.android.server.job.JobSchedulerServiceDumpProto.RegisteredJobOrBuilder
        public JobStatusDumpProto getDump() {
            JobStatusDumpProto jobStatusDumpProto = this.dump_;
            return jobStatusDumpProto == null ? JobStatusDumpProto.getDefaultInstance() : jobStatusDumpProto;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setDump(JobStatusDumpProto value) {
            if (value != null) {
                this.dump_ = value;
                this.bitField0_ |= 2;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setDump(JobStatusDumpProto.Builder builderForValue) {
            this.dump_ = (JobStatusDumpProto) builderForValue.build();
            this.bitField0_ |= 2;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void mergeDump(JobStatusDumpProto value) {
            JobStatusDumpProto jobStatusDumpProto = this.dump_;
            if (jobStatusDumpProto == null || jobStatusDumpProto == JobStatusDumpProto.getDefaultInstance()) {
                this.dump_ = value;
            } else {
                this.dump_ = (JobStatusDumpProto) ((JobStatusDumpProto.Builder) JobStatusDumpProto.newBuilder(this.dump_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
            }
            this.bitField0_ |= 2;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearDump() {
            this.dump_ = null;
            this.bitField0_ &= -3;
        }

        @Override // com.android.server.job.JobSchedulerServiceDumpProto.RegisteredJobOrBuilder
        public boolean hasIsJobReady() {
            return (this.bitField0_ & 4) == 4;
        }

        @Override // com.android.server.job.JobSchedulerServiceDumpProto.RegisteredJobOrBuilder
        public boolean getIsJobReady() {
            return this.isJobReady_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setIsJobReady(boolean value) {
            this.bitField0_ |= 4;
            this.isJobReady_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearIsJobReady() {
            this.bitField0_ &= -5;
            this.isJobReady_ = false;
        }

        @Override // com.android.server.job.JobSchedulerServiceDumpProto.RegisteredJobOrBuilder
        public boolean hasIsUserStarted() {
            return (this.bitField0_ & 8) == 8;
        }

        @Override // com.android.server.job.JobSchedulerServiceDumpProto.RegisteredJobOrBuilder
        public boolean getIsUserStarted() {
            return this.isUserStarted_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setIsUserStarted(boolean value) {
            this.bitField0_ |= 8;
            this.isUserStarted_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearIsUserStarted() {
            this.bitField0_ &= -9;
            this.isUserStarted_ = false;
        }

        @Override // com.android.server.job.JobSchedulerServiceDumpProto.RegisteredJobOrBuilder
        public boolean hasIsJobPending() {
            return (this.bitField0_ & 16) == 16;
        }

        @Override // com.android.server.job.JobSchedulerServiceDumpProto.RegisteredJobOrBuilder
        public boolean getIsJobPending() {
            return this.isJobPending_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setIsJobPending(boolean value) {
            this.bitField0_ |= 16;
            this.isJobPending_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearIsJobPending() {
            this.bitField0_ &= -17;
            this.isJobPending_ = false;
        }

        @Override // com.android.server.job.JobSchedulerServiceDumpProto.RegisteredJobOrBuilder
        public boolean hasIsJobCurrentlyActive() {
            return (this.bitField0_ & 32) == 32;
        }

        @Override // com.android.server.job.JobSchedulerServiceDumpProto.RegisteredJobOrBuilder
        public boolean getIsJobCurrentlyActive() {
            return this.isJobCurrentlyActive_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setIsJobCurrentlyActive(boolean value) {
            this.bitField0_ |= 32;
            this.isJobCurrentlyActive_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearIsJobCurrentlyActive() {
            this.bitField0_ &= -33;
            this.isJobCurrentlyActive_ = false;
        }

        @Override // com.android.server.job.JobSchedulerServiceDumpProto.RegisteredJobOrBuilder
        public boolean hasIsUidBackingUp() {
            return (this.bitField0_ & 64) == 64;
        }

        @Override // com.android.server.job.JobSchedulerServiceDumpProto.RegisteredJobOrBuilder
        public boolean getIsUidBackingUp() {
            return this.isUidBackingUp_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setIsUidBackingUp(boolean value) {
            this.bitField0_ |= 64;
            this.isUidBackingUp_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearIsUidBackingUp() {
            this.bitField0_ &= -65;
            this.isUidBackingUp_ = false;
        }

        @Override // com.android.server.job.JobSchedulerServiceDumpProto.RegisteredJobOrBuilder
        public boolean hasIsComponentPresent() {
            return (this.bitField0_ & 128) == 128;
        }

        @Override // com.android.server.job.JobSchedulerServiceDumpProto.RegisteredJobOrBuilder
        public boolean getIsComponentPresent() {
            return this.isComponentPresent_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setIsComponentPresent(boolean value) {
            this.bitField0_ |= 128;
            this.isComponentPresent_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearIsComponentPresent() {
            this.bitField0_ &= -129;
            this.isComponentPresent_ = false;
        }

        @Override // com.android.server.job.JobSchedulerServiceDumpProto.RegisteredJobOrBuilder
        public boolean hasLastRunHeartbeat() {
            return (this.bitField0_ & 256) == 256;
        }

        @Override // com.android.server.job.JobSchedulerServiceDumpProto.RegisteredJobOrBuilder
        public long getLastRunHeartbeat() {
            return this.lastRunHeartbeat_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setLastRunHeartbeat(long value) {
            this.bitField0_ |= 256;
            this.lastRunHeartbeat_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearLastRunHeartbeat() {
            this.bitField0_ &= -257;
            this.lastRunHeartbeat_ = 0;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                output.writeMessage(1, getInfo());
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeMessage(2, getDump());
            }
            if ((this.bitField0_ & 4) == 4) {
                output.writeBool(3, this.isJobReady_);
            }
            if ((this.bitField0_ & 8) == 8) {
                output.writeBool(4, this.isUserStarted_);
            }
            if ((this.bitField0_ & 16) == 16) {
                output.writeBool(5, this.isJobPending_);
            }
            if ((this.bitField0_ & 32) == 32) {
                output.writeBool(6, this.isJobCurrentlyActive_);
            }
            if ((this.bitField0_ & 64) == 64) {
                output.writeBool(7, this.isUidBackingUp_);
            }
            if ((this.bitField0_ & 128) == 128) {
                output.writeBool(8, this.isComponentPresent_);
            }
            if ((this.bitField0_ & 256) == 256) {
                output.writeInt64(9, this.lastRunHeartbeat_);
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
                size2 = 0 + CodedOutputStream.computeMessageSize(1, getInfo());
            }
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeMessageSize(2, getDump());
            }
            if ((this.bitField0_ & 4) == 4) {
                size2 += CodedOutputStream.computeBoolSize(3, this.isJobReady_);
            }
            if ((this.bitField0_ & 8) == 8) {
                size2 += CodedOutputStream.computeBoolSize(4, this.isUserStarted_);
            }
            if ((this.bitField0_ & 16) == 16) {
                size2 += CodedOutputStream.computeBoolSize(5, this.isJobPending_);
            }
            if ((this.bitField0_ & 32) == 32) {
                size2 += CodedOutputStream.computeBoolSize(6, this.isJobCurrentlyActive_);
            }
            if ((this.bitField0_ & 64) == 64) {
                size2 += CodedOutputStream.computeBoolSize(7, this.isUidBackingUp_);
            }
            if ((this.bitField0_ & 128) == 128) {
                size2 += CodedOutputStream.computeBoolSize(8, this.isComponentPresent_);
            }
            if ((this.bitField0_ & 256) == 256) {
                size2 += CodedOutputStream.computeInt64Size(9, this.lastRunHeartbeat_);
            }
            int size3 = size2 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static RegisteredJob parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (RegisteredJob) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static RegisteredJob parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (RegisteredJob) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static RegisteredJob parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (RegisteredJob) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static RegisteredJob parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (RegisteredJob) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static RegisteredJob parseFrom(InputStream input) throws IOException {
            return (RegisteredJob) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static RegisteredJob parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (RegisteredJob) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static RegisteredJob parseDelimitedFrom(InputStream input) throws IOException {
            return (RegisteredJob) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static RegisteredJob parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (RegisteredJob) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static RegisteredJob parseFrom(CodedInputStream input) throws IOException {
            return (RegisteredJob) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static RegisteredJob parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (RegisteredJob) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(RegisteredJob prototype) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<RegisteredJob, Builder> implements RegisteredJobOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 x0) {
                this();
            }

            private Builder() {
                super(RegisteredJob.DEFAULT_INSTANCE);
            }

            @Override // com.android.server.job.JobSchedulerServiceDumpProto.RegisteredJobOrBuilder
            public boolean hasInfo() {
                return ((RegisteredJob) this.instance).hasInfo();
            }

            @Override // com.android.server.job.JobSchedulerServiceDumpProto.RegisteredJobOrBuilder
            public JobStatusShortInfoProto getInfo() {
                return ((RegisteredJob) this.instance).getInfo();
            }

            public Builder setInfo(JobStatusShortInfoProto value) {
                copyOnWrite();
                ((RegisteredJob) this.instance).setInfo((RegisteredJob) value);
                return this;
            }

            public Builder setInfo(JobStatusShortInfoProto.Builder builderForValue) {
                copyOnWrite();
                ((RegisteredJob) this.instance).setInfo((RegisteredJob) builderForValue);
                return this;
            }

            public Builder mergeInfo(JobStatusShortInfoProto value) {
                copyOnWrite();
                ((RegisteredJob) this.instance).mergeInfo(value);
                return this;
            }

            public Builder clearInfo() {
                copyOnWrite();
                ((RegisteredJob) this.instance).clearInfo();
                return this;
            }

            @Override // com.android.server.job.JobSchedulerServiceDumpProto.RegisteredJobOrBuilder
            public boolean hasDump() {
                return ((RegisteredJob) this.instance).hasDump();
            }

            @Override // com.android.server.job.JobSchedulerServiceDumpProto.RegisteredJobOrBuilder
            public JobStatusDumpProto getDump() {
                return ((RegisteredJob) this.instance).getDump();
            }

            public Builder setDump(JobStatusDumpProto value) {
                copyOnWrite();
                ((RegisteredJob) this.instance).setDump((RegisteredJob) value);
                return this;
            }

            public Builder setDump(JobStatusDumpProto.Builder builderForValue) {
                copyOnWrite();
                ((RegisteredJob) this.instance).setDump((RegisteredJob) builderForValue);
                return this;
            }

            public Builder mergeDump(JobStatusDumpProto value) {
                copyOnWrite();
                ((RegisteredJob) this.instance).mergeDump(value);
                return this;
            }

            public Builder clearDump() {
                copyOnWrite();
                ((RegisteredJob) this.instance).clearDump();
                return this;
            }

            @Override // com.android.server.job.JobSchedulerServiceDumpProto.RegisteredJobOrBuilder
            public boolean hasIsJobReady() {
                return ((RegisteredJob) this.instance).hasIsJobReady();
            }

            @Override // com.android.server.job.JobSchedulerServiceDumpProto.RegisteredJobOrBuilder
            public boolean getIsJobReady() {
                return ((RegisteredJob) this.instance).getIsJobReady();
            }

            public Builder setIsJobReady(boolean value) {
                copyOnWrite();
                ((RegisteredJob) this.instance).setIsJobReady(value);
                return this;
            }

            public Builder clearIsJobReady() {
                copyOnWrite();
                ((RegisteredJob) this.instance).clearIsJobReady();
                return this;
            }

            @Override // com.android.server.job.JobSchedulerServiceDumpProto.RegisteredJobOrBuilder
            public boolean hasIsUserStarted() {
                return ((RegisteredJob) this.instance).hasIsUserStarted();
            }

            @Override // com.android.server.job.JobSchedulerServiceDumpProto.RegisteredJobOrBuilder
            public boolean getIsUserStarted() {
                return ((RegisteredJob) this.instance).getIsUserStarted();
            }

            public Builder setIsUserStarted(boolean value) {
                copyOnWrite();
                ((RegisteredJob) this.instance).setIsUserStarted(value);
                return this;
            }

            public Builder clearIsUserStarted() {
                copyOnWrite();
                ((RegisteredJob) this.instance).clearIsUserStarted();
                return this;
            }

            @Override // com.android.server.job.JobSchedulerServiceDumpProto.RegisteredJobOrBuilder
            public boolean hasIsJobPending() {
                return ((RegisteredJob) this.instance).hasIsJobPending();
            }

            @Override // com.android.server.job.JobSchedulerServiceDumpProto.RegisteredJobOrBuilder
            public boolean getIsJobPending() {
                return ((RegisteredJob) this.instance).getIsJobPending();
            }

            public Builder setIsJobPending(boolean value) {
                copyOnWrite();
                ((RegisteredJob) this.instance).setIsJobPending(value);
                return this;
            }

            public Builder clearIsJobPending() {
                copyOnWrite();
                ((RegisteredJob) this.instance).clearIsJobPending();
                return this;
            }

            @Override // com.android.server.job.JobSchedulerServiceDumpProto.RegisteredJobOrBuilder
            public boolean hasIsJobCurrentlyActive() {
                return ((RegisteredJob) this.instance).hasIsJobCurrentlyActive();
            }

            @Override // com.android.server.job.JobSchedulerServiceDumpProto.RegisteredJobOrBuilder
            public boolean getIsJobCurrentlyActive() {
                return ((RegisteredJob) this.instance).getIsJobCurrentlyActive();
            }

            public Builder setIsJobCurrentlyActive(boolean value) {
                copyOnWrite();
                ((RegisteredJob) this.instance).setIsJobCurrentlyActive(value);
                return this;
            }

            public Builder clearIsJobCurrentlyActive() {
                copyOnWrite();
                ((RegisteredJob) this.instance).clearIsJobCurrentlyActive();
                return this;
            }

            @Override // com.android.server.job.JobSchedulerServiceDumpProto.RegisteredJobOrBuilder
            public boolean hasIsUidBackingUp() {
                return ((RegisteredJob) this.instance).hasIsUidBackingUp();
            }

            @Override // com.android.server.job.JobSchedulerServiceDumpProto.RegisteredJobOrBuilder
            public boolean getIsUidBackingUp() {
                return ((RegisteredJob) this.instance).getIsUidBackingUp();
            }

            public Builder setIsUidBackingUp(boolean value) {
                copyOnWrite();
                ((RegisteredJob) this.instance).setIsUidBackingUp(value);
                return this;
            }

            public Builder clearIsUidBackingUp() {
                copyOnWrite();
                ((RegisteredJob) this.instance).clearIsUidBackingUp();
                return this;
            }

            @Override // com.android.server.job.JobSchedulerServiceDumpProto.RegisteredJobOrBuilder
            public boolean hasIsComponentPresent() {
                return ((RegisteredJob) this.instance).hasIsComponentPresent();
            }

            @Override // com.android.server.job.JobSchedulerServiceDumpProto.RegisteredJobOrBuilder
            public boolean getIsComponentPresent() {
                return ((RegisteredJob) this.instance).getIsComponentPresent();
            }

            public Builder setIsComponentPresent(boolean value) {
                copyOnWrite();
                ((RegisteredJob) this.instance).setIsComponentPresent(value);
                return this;
            }

            public Builder clearIsComponentPresent() {
                copyOnWrite();
                ((RegisteredJob) this.instance).clearIsComponentPresent();
                return this;
            }

            @Override // com.android.server.job.JobSchedulerServiceDumpProto.RegisteredJobOrBuilder
            public boolean hasLastRunHeartbeat() {
                return ((RegisteredJob) this.instance).hasLastRunHeartbeat();
            }

            @Override // com.android.server.job.JobSchedulerServiceDumpProto.RegisteredJobOrBuilder
            public long getLastRunHeartbeat() {
                return ((RegisteredJob) this.instance).getLastRunHeartbeat();
            }

            public Builder setLastRunHeartbeat(long value) {
                copyOnWrite();
                ((RegisteredJob) this.instance).setLastRunHeartbeat(value);
                return this;
            }

            public Builder clearLastRunHeartbeat() {
                copyOnWrite();
                ((RegisteredJob) this.instance).clearLastRunHeartbeat();
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new RegisteredJob();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder(null);
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    RegisteredJob other = (RegisteredJob) arg1;
                    this.info_ = (JobStatusShortInfoProto) visitor.visitMessage(this.info_, other.info_);
                    this.dump_ = (JobStatusDumpProto) visitor.visitMessage(this.dump_, other.dump_);
                    this.isJobReady_ = visitor.visitBoolean(hasIsJobReady(), this.isJobReady_, other.hasIsJobReady(), other.isJobReady_);
                    this.isUserStarted_ = visitor.visitBoolean(hasIsUserStarted(), this.isUserStarted_, other.hasIsUserStarted(), other.isUserStarted_);
                    this.isJobPending_ = visitor.visitBoolean(hasIsJobPending(), this.isJobPending_, other.hasIsJobPending(), other.isJobPending_);
                    this.isJobCurrentlyActive_ = visitor.visitBoolean(hasIsJobCurrentlyActive(), this.isJobCurrentlyActive_, other.hasIsJobCurrentlyActive(), other.isJobCurrentlyActive_);
                    this.isUidBackingUp_ = visitor.visitBoolean(hasIsUidBackingUp(), this.isUidBackingUp_, other.hasIsUidBackingUp(), other.isUidBackingUp_);
                    this.isComponentPresent_ = visitor.visitBoolean(hasIsComponentPresent(), this.isComponentPresent_, other.hasIsComponentPresent(), other.isComponentPresent_);
                    this.lastRunHeartbeat_ = visitor.visitLong(hasLastRunHeartbeat(), this.lastRunHeartbeat_, other.hasLastRunHeartbeat(), other.lastRunHeartbeat_);
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
                                JobStatusShortInfoProto.Builder subBuilder = null;
                                if ((this.bitField0_ & 1) == 1) {
                                    subBuilder = (JobStatusShortInfoProto.Builder) this.info_.toBuilder();
                                }
                                this.info_ = (JobStatusShortInfoProto) input.readMessage(JobStatusShortInfoProto.parser(), extensionRegistry);
                                if (subBuilder != null) {
                                    subBuilder.mergeFrom((GeneratedMessageLite) this.info_);
                                    this.info_ = (JobStatusShortInfoProto) subBuilder.buildPartial();
                                }
                                this.bitField0_ |= 1;
                            } else if (tag == 18) {
                                JobStatusDumpProto.Builder subBuilder2 = null;
                                if ((this.bitField0_ & 2) == 2) {
                                    subBuilder2 = (JobStatusDumpProto.Builder) this.dump_.toBuilder();
                                }
                                this.dump_ = (JobStatusDumpProto) input.readMessage(JobStatusDumpProto.parser(), extensionRegistry);
                                if (subBuilder2 != null) {
                                    subBuilder2.mergeFrom((GeneratedMessageLite) this.dump_);
                                    this.dump_ = (JobStatusDumpProto) subBuilder2.buildPartial();
                                }
                                this.bitField0_ |= 2;
                            } else if (tag == 24) {
                                this.bitField0_ |= 4;
                                this.isJobReady_ = input.readBool();
                            } else if (tag == 32) {
                                this.bitField0_ |= 8;
                                this.isUserStarted_ = input.readBool();
                            } else if (tag == 40) {
                                this.bitField0_ |= 16;
                                this.isJobPending_ = input.readBool();
                            } else if (tag == 48) {
                                this.bitField0_ = 32 | this.bitField0_;
                                this.isJobCurrentlyActive_ = input.readBool();
                            } else if (tag == 56) {
                                this.bitField0_ |= 64;
                                this.isUidBackingUp_ = input.readBool();
                            } else if (tag == 64) {
                                this.bitField0_ |= 128;
                                this.isComponentPresent_ = input.readBool();
                            } else if (tag == 72) {
                                this.bitField0_ |= 256;
                                this.lastRunHeartbeat_ = input.readInt64();
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
                        synchronized (RegisteredJob.class) {
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

        public static RegisteredJob getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<RegisteredJob> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class PriorityOverride extends GeneratedMessageLite<PriorityOverride, Builder> implements PriorityOverrideOrBuilder {
        private static final PriorityOverride DEFAULT_INSTANCE = new PriorityOverride();
        public static final int OVERRIDE_VALUE_FIELD_NUMBER = 2;
        private static volatile Parser<PriorityOverride> PARSER = null;
        public static final int UID_FIELD_NUMBER = 1;
        private int bitField0_;
        private int overrideValue_ = 0;
        private int uid_ = 0;

        private PriorityOverride() {
        }

        @Override // com.android.server.job.JobSchedulerServiceDumpProto.PriorityOverrideOrBuilder
        public boolean hasUid() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // com.android.server.job.JobSchedulerServiceDumpProto.PriorityOverrideOrBuilder
        public int getUid() {
            return this.uid_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setUid(int value) {
            this.bitField0_ |= 1;
            this.uid_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearUid() {
            this.bitField0_ &= -2;
            this.uid_ = 0;
        }

        @Override // com.android.server.job.JobSchedulerServiceDumpProto.PriorityOverrideOrBuilder
        public boolean hasOverrideValue() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // com.android.server.job.JobSchedulerServiceDumpProto.PriorityOverrideOrBuilder
        public int getOverrideValue() {
            return this.overrideValue_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setOverrideValue(int value) {
            this.bitField0_ |= 2;
            this.overrideValue_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearOverrideValue() {
            this.bitField0_ &= -3;
            this.overrideValue_ = 0;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                output.writeInt32(1, this.uid_);
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeSInt32(2, this.overrideValue_);
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
                size2 = 0 + CodedOutputStream.computeInt32Size(1, this.uid_);
            }
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeSInt32Size(2, this.overrideValue_);
            }
            int size3 = size2 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static PriorityOverride parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (PriorityOverride) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static PriorityOverride parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (PriorityOverride) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static PriorityOverride parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (PriorityOverride) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static PriorityOverride parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (PriorityOverride) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static PriorityOverride parseFrom(InputStream input) throws IOException {
            return (PriorityOverride) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static PriorityOverride parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (PriorityOverride) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static PriorityOverride parseDelimitedFrom(InputStream input) throws IOException {
            return (PriorityOverride) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static PriorityOverride parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (PriorityOverride) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static PriorityOverride parseFrom(CodedInputStream input) throws IOException {
            return (PriorityOverride) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static PriorityOverride parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (PriorityOverride) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(PriorityOverride prototype) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<PriorityOverride, Builder> implements PriorityOverrideOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 x0) {
                this();
            }

            private Builder() {
                super(PriorityOverride.DEFAULT_INSTANCE);
            }

            @Override // com.android.server.job.JobSchedulerServiceDumpProto.PriorityOverrideOrBuilder
            public boolean hasUid() {
                return ((PriorityOverride) this.instance).hasUid();
            }

            @Override // com.android.server.job.JobSchedulerServiceDumpProto.PriorityOverrideOrBuilder
            public int getUid() {
                return ((PriorityOverride) this.instance).getUid();
            }

            public Builder setUid(int value) {
                copyOnWrite();
                ((PriorityOverride) this.instance).setUid(value);
                return this;
            }

            public Builder clearUid() {
                copyOnWrite();
                ((PriorityOverride) this.instance).clearUid();
                return this;
            }

            @Override // com.android.server.job.JobSchedulerServiceDumpProto.PriorityOverrideOrBuilder
            public boolean hasOverrideValue() {
                return ((PriorityOverride) this.instance).hasOverrideValue();
            }

            @Override // com.android.server.job.JobSchedulerServiceDumpProto.PriorityOverrideOrBuilder
            public int getOverrideValue() {
                return ((PriorityOverride) this.instance).getOverrideValue();
            }

            public Builder setOverrideValue(int value) {
                copyOnWrite();
                ((PriorityOverride) this.instance).setOverrideValue(value);
                return this;
            }

            public Builder clearOverrideValue() {
                copyOnWrite();
                ((PriorityOverride) this.instance).clearOverrideValue();
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new PriorityOverride();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder(null);
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    PriorityOverride other = (PriorityOverride) arg1;
                    this.uid_ = visitor.visitInt(hasUid(), this.uid_, other.hasUid(), other.uid_);
                    this.overrideValue_ = visitor.visitInt(hasOverrideValue(), this.overrideValue_, other.hasOverrideValue(), other.overrideValue_);
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
                                this.uid_ = input.readInt32();
                            } else if (tag == 16) {
                                this.bitField0_ |= 2;
                                this.overrideValue_ = input.readSInt32();
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
                        synchronized (PriorityOverride.class) {
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

        public static PriorityOverride getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<PriorityOverride> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class PendingJob extends GeneratedMessageLite<PendingJob, Builder> implements PendingJobOrBuilder {
        private static final PendingJob DEFAULT_INSTANCE = new PendingJob();
        public static final int DUMP_FIELD_NUMBER = 2;
        public static final int ENQUEUED_DURATION_MS_FIELD_NUMBER = 4;
        public static final int EVALUATED_PRIORITY_FIELD_NUMBER = 3;
        public static final int INFO_FIELD_NUMBER = 1;
        private static volatile Parser<PendingJob> PARSER;
        private int bitField0_;
        private JobStatusDumpProto dump_;
        private long enqueuedDurationMs_ = 0;
        private int evaluatedPriority_ = 0;
        private JobStatusShortInfoProto info_;

        private PendingJob() {
        }

        @Override // com.android.server.job.JobSchedulerServiceDumpProto.PendingJobOrBuilder
        public boolean hasInfo() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // com.android.server.job.JobSchedulerServiceDumpProto.PendingJobOrBuilder
        public JobStatusShortInfoProto getInfo() {
            JobStatusShortInfoProto jobStatusShortInfoProto = this.info_;
            return jobStatusShortInfoProto == null ? JobStatusShortInfoProto.getDefaultInstance() : jobStatusShortInfoProto;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setInfo(JobStatusShortInfoProto value) {
            if (value != null) {
                this.info_ = value;
                this.bitField0_ |= 1;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setInfo(JobStatusShortInfoProto.Builder builderForValue) {
            this.info_ = (JobStatusShortInfoProto) builderForValue.build();
            this.bitField0_ |= 1;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void mergeInfo(JobStatusShortInfoProto value) {
            JobStatusShortInfoProto jobStatusShortInfoProto = this.info_;
            if (jobStatusShortInfoProto == null || jobStatusShortInfoProto == JobStatusShortInfoProto.getDefaultInstance()) {
                this.info_ = value;
            } else {
                this.info_ = (JobStatusShortInfoProto) ((JobStatusShortInfoProto.Builder) JobStatusShortInfoProto.newBuilder(this.info_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
            }
            this.bitField0_ |= 1;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearInfo() {
            this.info_ = null;
            this.bitField0_ &= -2;
        }

        @Override // com.android.server.job.JobSchedulerServiceDumpProto.PendingJobOrBuilder
        public boolean hasDump() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // com.android.server.job.JobSchedulerServiceDumpProto.PendingJobOrBuilder
        public JobStatusDumpProto getDump() {
            JobStatusDumpProto jobStatusDumpProto = this.dump_;
            return jobStatusDumpProto == null ? JobStatusDumpProto.getDefaultInstance() : jobStatusDumpProto;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setDump(JobStatusDumpProto value) {
            if (value != null) {
                this.dump_ = value;
                this.bitField0_ |= 2;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setDump(JobStatusDumpProto.Builder builderForValue) {
            this.dump_ = (JobStatusDumpProto) builderForValue.build();
            this.bitField0_ |= 2;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void mergeDump(JobStatusDumpProto value) {
            JobStatusDumpProto jobStatusDumpProto = this.dump_;
            if (jobStatusDumpProto == null || jobStatusDumpProto == JobStatusDumpProto.getDefaultInstance()) {
                this.dump_ = value;
            } else {
                this.dump_ = (JobStatusDumpProto) ((JobStatusDumpProto.Builder) JobStatusDumpProto.newBuilder(this.dump_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
            }
            this.bitField0_ |= 2;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearDump() {
            this.dump_ = null;
            this.bitField0_ &= -3;
        }

        @Override // com.android.server.job.JobSchedulerServiceDumpProto.PendingJobOrBuilder
        public boolean hasEvaluatedPriority() {
            return (this.bitField0_ & 4) == 4;
        }

        @Override // com.android.server.job.JobSchedulerServiceDumpProto.PendingJobOrBuilder
        public int getEvaluatedPriority() {
            return this.evaluatedPriority_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setEvaluatedPriority(int value) {
            this.bitField0_ |= 4;
            this.evaluatedPriority_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearEvaluatedPriority() {
            this.bitField0_ &= -5;
            this.evaluatedPriority_ = 0;
        }

        @Override // com.android.server.job.JobSchedulerServiceDumpProto.PendingJobOrBuilder
        public boolean hasEnqueuedDurationMs() {
            return (this.bitField0_ & 8) == 8;
        }

        @Override // com.android.server.job.JobSchedulerServiceDumpProto.PendingJobOrBuilder
        public long getEnqueuedDurationMs() {
            return this.enqueuedDurationMs_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setEnqueuedDurationMs(long value) {
            this.bitField0_ |= 8;
            this.enqueuedDurationMs_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearEnqueuedDurationMs() {
            this.bitField0_ &= -9;
            this.enqueuedDurationMs_ = 0;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                output.writeMessage(1, getInfo());
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeMessage(2, getDump());
            }
            if ((this.bitField0_ & 4) == 4) {
                output.writeSInt32(3, this.evaluatedPriority_);
            }
            if ((this.bitField0_ & 8) == 8) {
                output.writeInt64(4, this.enqueuedDurationMs_);
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
                size2 = 0 + CodedOutputStream.computeMessageSize(1, getInfo());
            }
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeMessageSize(2, getDump());
            }
            if ((this.bitField0_ & 4) == 4) {
                size2 += CodedOutputStream.computeSInt32Size(3, this.evaluatedPriority_);
            }
            if ((this.bitField0_ & 8) == 8) {
                size2 += CodedOutputStream.computeInt64Size(4, this.enqueuedDurationMs_);
            }
            int size3 = size2 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static PendingJob parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (PendingJob) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static PendingJob parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (PendingJob) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static PendingJob parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (PendingJob) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static PendingJob parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (PendingJob) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static PendingJob parseFrom(InputStream input) throws IOException {
            return (PendingJob) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static PendingJob parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (PendingJob) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static PendingJob parseDelimitedFrom(InputStream input) throws IOException {
            return (PendingJob) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static PendingJob parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (PendingJob) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static PendingJob parseFrom(CodedInputStream input) throws IOException {
            return (PendingJob) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static PendingJob parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (PendingJob) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(PendingJob prototype) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<PendingJob, Builder> implements PendingJobOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 x0) {
                this();
            }

            private Builder() {
                super(PendingJob.DEFAULT_INSTANCE);
            }

            @Override // com.android.server.job.JobSchedulerServiceDumpProto.PendingJobOrBuilder
            public boolean hasInfo() {
                return ((PendingJob) this.instance).hasInfo();
            }

            @Override // com.android.server.job.JobSchedulerServiceDumpProto.PendingJobOrBuilder
            public JobStatusShortInfoProto getInfo() {
                return ((PendingJob) this.instance).getInfo();
            }

            public Builder setInfo(JobStatusShortInfoProto value) {
                copyOnWrite();
                ((PendingJob) this.instance).setInfo((PendingJob) value);
                return this;
            }

            public Builder setInfo(JobStatusShortInfoProto.Builder builderForValue) {
                copyOnWrite();
                ((PendingJob) this.instance).setInfo((PendingJob) builderForValue);
                return this;
            }

            public Builder mergeInfo(JobStatusShortInfoProto value) {
                copyOnWrite();
                ((PendingJob) this.instance).mergeInfo(value);
                return this;
            }

            public Builder clearInfo() {
                copyOnWrite();
                ((PendingJob) this.instance).clearInfo();
                return this;
            }

            @Override // com.android.server.job.JobSchedulerServiceDumpProto.PendingJobOrBuilder
            public boolean hasDump() {
                return ((PendingJob) this.instance).hasDump();
            }

            @Override // com.android.server.job.JobSchedulerServiceDumpProto.PendingJobOrBuilder
            public JobStatusDumpProto getDump() {
                return ((PendingJob) this.instance).getDump();
            }

            public Builder setDump(JobStatusDumpProto value) {
                copyOnWrite();
                ((PendingJob) this.instance).setDump((PendingJob) value);
                return this;
            }

            public Builder setDump(JobStatusDumpProto.Builder builderForValue) {
                copyOnWrite();
                ((PendingJob) this.instance).setDump((PendingJob) builderForValue);
                return this;
            }

            public Builder mergeDump(JobStatusDumpProto value) {
                copyOnWrite();
                ((PendingJob) this.instance).mergeDump(value);
                return this;
            }

            public Builder clearDump() {
                copyOnWrite();
                ((PendingJob) this.instance).clearDump();
                return this;
            }

            @Override // com.android.server.job.JobSchedulerServiceDumpProto.PendingJobOrBuilder
            public boolean hasEvaluatedPriority() {
                return ((PendingJob) this.instance).hasEvaluatedPriority();
            }

            @Override // com.android.server.job.JobSchedulerServiceDumpProto.PendingJobOrBuilder
            public int getEvaluatedPriority() {
                return ((PendingJob) this.instance).getEvaluatedPriority();
            }

            public Builder setEvaluatedPriority(int value) {
                copyOnWrite();
                ((PendingJob) this.instance).setEvaluatedPriority(value);
                return this;
            }

            public Builder clearEvaluatedPriority() {
                copyOnWrite();
                ((PendingJob) this.instance).clearEvaluatedPriority();
                return this;
            }

            @Override // com.android.server.job.JobSchedulerServiceDumpProto.PendingJobOrBuilder
            public boolean hasEnqueuedDurationMs() {
                return ((PendingJob) this.instance).hasEnqueuedDurationMs();
            }

            @Override // com.android.server.job.JobSchedulerServiceDumpProto.PendingJobOrBuilder
            public long getEnqueuedDurationMs() {
                return ((PendingJob) this.instance).getEnqueuedDurationMs();
            }

            public Builder setEnqueuedDurationMs(long value) {
                copyOnWrite();
                ((PendingJob) this.instance).setEnqueuedDurationMs(value);
                return this;
            }

            public Builder clearEnqueuedDurationMs() {
                copyOnWrite();
                ((PendingJob) this.instance).clearEnqueuedDurationMs();
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new PendingJob();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder(null);
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    PendingJob other = (PendingJob) arg1;
                    this.info_ = (JobStatusShortInfoProto) visitor.visitMessage(this.info_, other.info_);
                    this.dump_ = (JobStatusDumpProto) visitor.visitMessage(this.dump_, other.dump_);
                    this.evaluatedPriority_ = visitor.visitInt(hasEvaluatedPriority(), this.evaluatedPriority_, other.hasEvaluatedPriority(), other.evaluatedPriority_);
                    this.enqueuedDurationMs_ = visitor.visitLong(hasEnqueuedDurationMs(), this.enqueuedDurationMs_, other.hasEnqueuedDurationMs(), other.enqueuedDurationMs_);
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
                                JobStatusShortInfoProto.Builder subBuilder = null;
                                if ((this.bitField0_ & 1) == 1) {
                                    subBuilder = (JobStatusShortInfoProto.Builder) this.info_.toBuilder();
                                }
                                this.info_ = (JobStatusShortInfoProto) input.readMessage(JobStatusShortInfoProto.parser(), extensionRegistry);
                                if (subBuilder != null) {
                                    subBuilder.mergeFrom((GeneratedMessageLite) this.info_);
                                    this.info_ = (JobStatusShortInfoProto) subBuilder.buildPartial();
                                }
                                this.bitField0_ |= 1;
                            } else if (tag == 18) {
                                JobStatusDumpProto.Builder subBuilder2 = null;
                                if ((this.bitField0_ & 2) == 2) {
                                    subBuilder2 = (JobStatusDumpProto.Builder) this.dump_.toBuilder();
                                }
                                this.dump_ = (JobStatusDumpProto) input.readMessage(JobStatusDumpProto.parser(), extensionRegistry);
                                if (subBuilder2 != null) {
                                    subBuilder2.mergeFrom((GeneratedMessageLite) this.dump_);
                                    this.dump_ = (JobStatusDumpProto) subBuilder2.buildPartial();
                                }
                                this.bitField0_ |= 2;
                            } else if (tag == 24) {
                                this.bitField0_ |= 4;
                                this.evaluatedPriority_ = input.readSInt32();
                            } else if (tag == 32) {
                                this.bitField0_ |= 8;
                                this.enqueuedDurationMs_ = input.readInt64();
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
                        synchronized (PendingJob.class) {
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

        public static PendingJob getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<PendingJob> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class ActiveJob extends GeneratedMessageLite<ActiveJob, Builder> implements ActiveJobOrBuilder {
        private static final ActiveJob DEFAULT_INSTANCE = new ActiveJob();
        public static final int INACTIVE_FIELD_NUMBER = 1;
        private static volatile Parser<ActiveJob> PARSER = null;
        public static final int RUNNING_FIELD_NUMBER = 2;
        private int bitField0_;
        private int jobCase_ = 0;
        private Object job_;

        public interface InactiveJobOrBuilder extends MessageLiteOrBuilder {
            String getStoppedReason();

            ByteString getStoppedReasonBytes();

            long getTimeSinceStoppedMs();

            boolean hasStoppedReason();

            boolean hasTimeSinceStoppedMs();
        }

        public interface RunningJobOrBuilder extends MessageLiteOrBuilder {
            JobStatusDumpProto getDump();

            int getEvaluatedPriority();

            JobStatusShortInfoProto getInfo();

            long getPendingDurationMs();

            long getRunningDurationMs();

            long getTimeSinceMadeActiveMs();

            long getTimeUntilTimeoutMs();

            boolean hasDump();

            boolean hasEvaluatedPriority();

            boolean hasInfo();

            boolean hasPendingDurationMs();

            boolean hasRunningDurationMs();

            boolean hasTimeSinceMadeActiveMs();

            boolean hasTimeUntilTimeoutMs();
        }

        private ActiveJob() {
        }

        public static final class InactiveJob extends GeneratedMessageLite<InactiveJob, Builder> implements InactiveJobOrBuilder {
            private static final InactiveJob DEFAULT_INSTANCE = new InactiveJob();
            private static volatile Parser<InactiveJob> PARSER = null;
            public static final int STOPPED_REASON_FIELD_NUMBER = 2;
            public static final int TIME_SINCE_STOPPED_MS_FIELD_NUMBER = 1;
            private int bitField0_;
            private String stoppedReason_ = "";
            private long timeSinceStoppedMs_ = 0;

            private InactiveJob() {
            }

            @Override // com.android.server.job.JobSchedulerServiceDumpProto.ActiveJob.InactiveJobOrBuilder
            public boolean hasTimeSinceStoppedMs() {
                return (this.bitField0_ & 1) == 1;
            }

            @Override // com.android.server.job.JobSchedulerServiceDumpProto.ActiveJob.InactiveJobOrBuilder
            public long getTimeSinceStoppedMs() {
                return this.timeSinceStoppedMs_;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void setTimeSinceStoppedMs(long value) {
                this.bitField0_ |= 1;
                this.timeSinceStoppedMs_ = value;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void clearTimeSinceStoppedMs() {
                this.bitField0_ &= -2;
                this.timeSinceStoppedMs_ = 0;
            }

            @Override // com.android.server.job.JobSchedulerServiceDumpProto.ActiveJob.InactiveJobOrBuilder
            public boolean hasStoppedReason() {
                return (this.bitField0_ & 2) == 2;
            }

            @Override // com.android.server.job.JobSchedulerServiceDumpProto.ActiveJob.InactiveJobOrBuilder
            public String getStoppedReason() {
                return this.stoppedReason_;
            }

            @Override // com.android.server.job.JobSchedulerServiceDumpProto.ActiveJob.InactiveJobOrBuilder
            public ByteString getStoppedReasonBytes() {
                return ByteString.copyFromUtf8(this.stoppedReason_);
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void setStoppedReason(String value) {
                if (value != null) {
                    this.bitField0_ |= 2;
                    this.stoppedReason_ = value;
                    return;
                }
                throw new NullPointerException();
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void clearStoppedReason() {
                this.bitField0_ &= -3;
                this.stoppedReason_ = getDefaultInstance().getStoppedReason();
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void setStoppedReasonBytes(ByteString value) {
                if (value != null) {
                    this.bitField0_ |= 2;
                    this.stoppedReason_ = value.toStringUtf8();
                    return;
                }
                throw new NullPointerException();
            }

            @Override // com.google.protobuf.MessageLite
            public void writeTo(CodedOutputStream output) throws IOException {
                if ((this.bitField0_ & 1) == 1) {
                    output.writeInt64(1, this.timeSinceStoppedMs_);
                }
                if ((this.bitField0_ & 2) == 2) {
                    output.writeString(2, getStoppedReason());
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
                    size2 = 0 + CodedOutputStream.computeInt64Size(1, this.timeSinceStoppedMs_);
                }
                if ((this.bitField0_ & 2) == 2) {
                    size2 += CodedOutputStream.computeStringSize(2, getStoppedReason());
                }
                int size3 = size2 + this.unknownFields.getSerializedSize();
                this.memoizedSerializedSize = size3;
                return size3;
            }

            public static InactiveJob parseFrom(ByteString data) throws InvalidProtocolBufferException {
                return (InactiveJob) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
            }

            public static InactiveJob parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return (InactiveJob) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
            }

            public static InactiveJob parseFrom(byte[] data) throws InvalidProtocolBufferException {
                return (InactiveJob) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
            }

            public static InactiveJob parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return (InactiveJob) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
            }

            public static InactiveJob parseFrom(InputStream input) throws IOException {
                return (InactiveJob) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
            }

            public static InactiveJob parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (InactiveJob) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static InactiveJob parseDelimitedFrom(InputStream input) throws IOException {
                return (InactiveJob) parseDelimitedFrom(DEFAULT_INSTANCE, input);
            }

            public static InactiveJob parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (InactiveJob) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static InactiveJob parseFrom(CodedInputStream input) throws IOException {
                return (InactiveJob) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
            }

            public static InactiveJob parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (InactiveJob) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static Builder newBuilder() {
                return (Builder) DEFAULT_INSTANCE.toBuilder();
            }

            public static Builder newBuilder(InactiveJob prototype) {
                return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
            }

            public static final class Builder extends GeneratedMessageLite.Builder<InactiveJob, Builder> implements InactiveJobOrBuilder {
                /* synthetic */ Builder(AnonymousClass1 x0) {
                    this();
                }

                private Builder() {
                    super(InactiveJob.DEFAULT_INSTANCE);
                }

                @Override // com.android.server.job.JobSchedulerServiceDumpProto.ActiveJob.InactiveJobOrBuilder
                public boolean hasTimeSinceStoppedMs() {
                    return ((InactiveJob) this.instance).hasTimeSinceStoppedMs();
                }

                @Override // com.android.server.job.JobSchedulerServiceDumpProto.ActiveJob.InactiveJobOrBuilder
                public long getTimeSinceStoppedMs() {
                    return ((InactiveJob) this.instance).getTimeSinceStoppedMs();
                }

                public Builder setTimeSinceStoppedMs(long value) {
                    copyOnWrite();
                    ((InactiveJob) this.instance).setTimeSinceStoppedMs(value);
                    return this;
                }

                public Builder clearTimeSinceStoppedMs() {
                    copyOnWrite();
                    ((InactiveJob) this.instance).clearTimeSinceStoppedMs();
                    return this;
                }

                @Override // com.android.server.job.JobSchedulerServiceDumpProto.ActiveJob.InactiveJobOrBuilder
                public boolean hasStoppedReason() {
                    return ((InactiveJob) this.instance).hasStoppedReason();
                }

                @Override // com.android.server.job.JobSchedulerServiceDumpProto.ActiveJob.InactiveJobOrBuilder
                public String getStoppedReason() {
                    return ((InactiveJob) this.instance).getStoppedReason();
                }

                @Override // com.android.server.job.JobSchedulerServiceDumpProto.ActiveJob.InactiveJobOrBuilder
                public ByteString getStoppedReasonBytes() {
                    return ((InactiveJob) this.instance).getStoppedReasonBytes();
                }

                public Builder setStoppedReason(String value) {
                    copyOnWrite();
                    ((InactiveJob) this.instance).setStoppedReason(value);
                    return this;
                }

                public Builder clearStoppedReason() {
                    copyOnWrite();
                    ((InactiveJob) this.instance).clearStoppedReason();
                    return this;
                }

                public Builder setStoppedReasonBytes(ByteString value) {
                    copyOnWrite();
                    ((InactiveJob) this.instance).setStoppedReasonBytes(value);
                    return this;
                }
            }

            /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
            /* access modifiers changed from: protected */
            @Override // com.google.protobuf.GeneratedMessageLite
            public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
                switch (method) {
                    case NEW_MUTABLE_INSTANCE:
                        return new InactiveJob();
                    case IS_INITIALIZED:
                        return DEFAULT_INSTANCE;
                    case MAKE_IMMUTABLE:
                        return null;
                    case NEW_BUILDER:
                        return new Builder(null);
                    case VISIT:
                        GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                        InactiveJob other = (InactiveJob) arg1;
                        this.timeSinceStoppedMs_ = visitor.visitLong(hasTimeSinceStoppedMs(), this.timeSinceStoppedMs_, other.hasTimeSinceStoppedMs(), other.timeSinceStoppedMs_);
                        this.stoppedReason_ = visitor.visitString(hasStoppedReason(), this.stoppedReason_, other.hasStoppedReason(), other.stoppedReason_);
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
                                    this.timeSinceStoppedMs_ = input.readInt64();
                                } else if (tag == 18) {
                                    String s = input.readString();
                                    this.bitField0_ |= 2;
                                    this.stoppedReason_ = s;
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
                            synchronized (InactiveJob.class) {
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

            public static InactiveJob getDefaultInstance() {
                return DEFAULT_INSTANCE;
            }

            public static Parser<InactiveJob> parser() {
                return DEFAULT_INSTANCE.getParserForType();
            }
        }

        public static final class RunningJob extends GeneratedMessageLite<RunningJob, Builder> implements RunningJobOrBuilder {
            private static final RunningJob DEFAULT_INSTANCE = new RunningJob();
            public static final int DUMP_FIELD_NUMBER = 4;
            public static final int EVALUATED_PRIORITY_FIELD_NUMBER = 5;
            public static final int INFO_FIELD_NUMBER = 1;
            private static volatile Parser<RunningJob> PARSER = null;
            public static final int PENDING_DURATION_MS_FIELD_NUMBER = 7;
            public static final int RUNNING_DURATION_MS_FIELD_NUMBER = 2;
            public static final int TIME_SINCE_MADE_ACTIVE_MS_FIELD_NUMBER = 6;
            public static final int TIME_UNTIL_TIMEOUT_MS_FIELD_NUMBER = 3;
            private int bitField0_;
            private JobStatusDumpProto dump_;
            private int evaluatedPriority_ = 0;
            private JobStatusShortInfoProto info_;
            private long pendingDurationMs_ = 0;
            private long runningDurationMs_ = 0;
            private long timeSinceMadeActiveMs_ = 0;
            private long timeUntilTimeoutMs_ = 0;

            private RunningJob() {
            }

            @Override // com.android.server.job.JobSchedulerServiceDumpProto.ActiveJob.RunningJobOrBuilder
            public boolean hasInfo() {
                return (this.bitField0_ & 1) == 1;
            }

            @Override // com.android.server.job.JobSchedulerServiceDumpProto.ActiveJob.RunningJobOrBuilder
            public JobStatusShortInfoProto getInfo() {
                JobStatusShortInfoProto jobStatusShortInfoProto = this.info_;
                return jobStatusShortInfoProto == null ? JobStatusShortInfoProto.getDefaultInstance() : jobStatusShortInfoProto;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void setInfo(JobStatusShortInfoProto value) {
                if (value != null) {
                    this.info_ = value;
                    this.bitField0_ |= 1;
                    return;
                }
                throw new NullPointerException();
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void setInfo(JobStatusShortInfoProto.Builder builderForValue) {
                this.info_ = (JobStatusShortInfoProto) builderForValue.build();
                this.bitField0_ |= 1;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void mergeInfo(JobStatusShortInfoProto value) {
                JobStatusShortInfoProto jobStatusShortInfoProto = this.info_;
                if (jobStatusShortInfoProto == null || jobStatusShortInfoProto == JobStatusShortInfoProto.getDefaultInstance()) {
                    this.info_ = value;
                } else {
                    this.info_ = (JobStatusShortInfoProto) ((JobStatusShortInfoProto.Builder) JobStatusShortInfoProto.newBuilder(this.info_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
                }
                this.bitField0_ |= 1;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void clearInfo() {
                this.info_ = null;
                this.bitField0_ &= -2;
            }

            @Override // com.android.server.job.JobSchedulerServiceDumpProto.ActiveJob.RunningJobOrBuilder
            public boolean hasRunningDurationMs() {
                return (this.bitField0_ & 2) == 2;
            }

            @Override // com.android.server.job.JobSchedulerServiceDumpProto.ActiveJob.RunningJobOrBuilder
            public long getRunningDurationMs() {
                return this.runningDurationMs_;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void setRunningDurationMs(long value) {
                this.bitField0_ |= 2;
                this.runningDurationMs_ = value;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void clearRunningDurationMs() {
                this.bitField0_ &= -3;
                this.runningDurationMs_ = 0;
            }

            @Override // com.android.server.job.JobSchedulerServiceDumpProto.ActiveJob.RunningJobOrBuilder
            public boolean hasTimeUntilTimeoutMs() {
                return (this.bitField0_ & 4) == 4;
            }

            @Override // com.android.server.job.JobSchedulerServiceDumpProto.ActiveJob.RunningJobOrBuilder
            public long getTimeUntilTimeoutMs() {
                return this.timeUntilTimeoutMs_;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void setTimeUntilTimeoutMs(long value) {
                this.bitField0_ |= 4;
                this.timeUntilTimeoutMs_ = value;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void clearTimeUntilTimeoutMs() {
                this.bitField0_ &= -5;
                this.timeUntilTimeoutMs_ = 0;
            }

            @Override // com.android.server.job.JobSchedulerServiceDumpProto.ActiveJob.RunningJobOrBuilder
            public boolean hasDump() {
                return (this.bitField0_ & 8) == 8;
            }

            @Override // com.android.server.job.JobSchedulerServiceDumpProto.ActiveJob.RunningJobOrBuilder
            public JobStatusDumpProto getDump() {
                JobStatusDumpProto jobStatusDumpProto = this.dump_;
                return jobStatusDumpProto == null ? JobStatusDumpProto.getDefaultInstance() : jobStatusDumpProto;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void setDump(JobStatusDumpProto value) {
                if (value != null) {
                    this.dump_ = value;
                    this.bitField0_ |= 8;
                    return;
                }
                throw new NullPointerException();
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void setDump(JobStatusDumpProto.Builder builderForValue) {
                this.dump_ = (JobStatusDumpProto) builderForValue.build();
                this.bitField0_ |= 8;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void mergeDump(JobStatusDumpProto value) {
                JobStatusDumpProto jobStatusDumpProto = this.dump_;
                if (jobStatusDumpProto == null || jobStatusDumpProto == JobStatusDumpProto.getDefaultInstance()) {
                    this.dump_ = value;
                } else {
                    this.dump_ = (JobStatusDumpProto) ((JobStatusDumpProto.Builder) JobStatusDumpProto.newBuilder(this.dump_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
                }
                this.bitField0_ |= 8;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void clearDump() {
                this.dump_ = null;
                this.bitField0_ &= -9;
            }

            @Override // com.android.server.job.JobSchedulerServiceDumpProto.ActiveJob.RunningJobOrBuilder
            public boolean hasEvaluatedPriority() {
                return (this.bitField0_ & 16) == 16;
            }

            @Override // com.android.server.job.JobSchedulerServiceDumpProto.ActiveJob.RunningJobOrBuilder
            public int getEvaluatedPriority() {
                return this.evaluatedPriority_;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void setEvaluatedPriority(int value) {
                this.bitField0_ |= 16;
                this.evaluatedPriority_ = value;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void clearEvaluatedPriority() {
                this.bitField0_ &= -17;
                this.evaluatedPriority_ = 0;
            }

            @Override // com.android.server.job.JobSchedulerServiceDumpProto.ActiveJob.RunningJobOrBuilder
            public boolean hasTimeSinceMadeActiveMs() {
                return (this.bitField0_ & 32) == 32;
            }

            @Override // com.android.server.job.JobSchedulerServiceDumpProto.ActiveJob.RunningJobOrBuilder
            public long getTimeSinceMadeActiveMs() {
                return this.timeSinceMadeActiveMs_;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void setTimeSinceMadeActiveMs(long value) {
                this.bitField0_ |= 32;
                this.timeSinceMadeActiveMs_ = value;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void clearTimeSinceMadeActiveMs() {
                this.bitField0_ &= -33;
                this.timeSinceMadeActiveMs_ = 0;
            }

            @Override // com.android.server.job.JobSchedulerServiceDumpProto.ActiveJob.RunningJobOrBuilder
            public boolean hasPendingDurationMs() {
                return (this.bitField0_ & 64) == 64;
            }

            @Override // com.android.server.job.JobSchedulerServiceDumpProto.ActiveJob.RunningJobOrBuilder
            public long getPendingDurationMs() {
                return this.pendingDurationMs_;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void setPendingDurationMs(long value) {
                this.bitField0_ |= 64;
                this.pendingDurationMs_ = value;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void clearPendingDurationMs() {
                this.bitField0_ &= -65;
                this.pendingDurationMs_ = 0;
            }

            @Override // com.google.protobuf.MessageLite
            public void writeTo(CodedOutputStream output) throws IOException {
                if ((this.bitField0_ & 1) == 1) {
                    output.writeMessage(1, getInfo());
                }
                if ((this.bitField0_ & 2) == 2) {
                    output.writeInt64(2, this.runningDurationMs_);
                }
                if ((this.bitField0_ & 4) == 4) {
                    output.writeInt64(3, this.timeUntilTimeoutMs_);
                }
                if ((this.bitField0_ & 8) == 8) {
                    output.writeMessage(4, getDump());
                }
                if ((this.bitField0_ & 16) == 16) {
                    output.writeSInt32(5, this.evaluatedPriority_);
                }
                if ((this.bitField0_ & 32) == 32) {
                    output.writeInt64(6, this.timeSinceMadeActiveMs_);
                }
                if ((this.bitField0_ & 64) == 64) {
                    output.writeInt64(7, this.pendingDurationMs_);
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
                    size2 = 0 + CodedOutputStream.computeMessageSize(1, getInfo());
                }
                if ((this.bitField0_ & 2) == 2) {
                    size2 += CodedOutputStream.computeInt64Size(2, this.runningDurationMs_);
                }
                if ((this.bitField0_ & 4) == 4) {
                    size2 += CodedOutputStream.computeInt64Size(3, this.timeUntilTimeoutMs_);
                }
                if ((this.bitField0_ & 8) == 8) {
                    size2 += CodedOutputStream.computeMessageSize(4, getDump());
                }
                if ((this.bitField0_ & 16) == 16) {
                    size2 += CodedOutputStream.computeSInt32Size(5, this.evaluatedPriority_);
                }
                if ((this.bitField0_ & 32) == 32) {
                    size2 += CodedOutputStream.computeInt64Size(6, this.timeSinceMadeActiveMs_);
                }
                if ((this.bitField0_ & 64) == 64) {
                    size2 += CodedOutputStream.computeInt64Size(7, this.pendingDurationMs_);
                }
                int size3 = size2 + this.unknownFields.getSerializedSize();
                this.memoizedSerializedSize = size3;
                return size3;
            }

            public static RunningJob parseFrom(ByteString data) throws InvalidProtocolBufferException {
                return (RunningJob) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
            }

            public static RunningJob parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return (RunningJob) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
            }

            public static RunningJob parseFrom(byte[] data) throws InvalidProtocolBufferException {
                return (RunningJob) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
            }

            public static RunningJob parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return (RunningJob) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
            }

            public static RunningJob parseFrom(InputStream input) throws IOException {
                return (RunningJob) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
            }

            public static RunningJob parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (RunningJob) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static RunningJob parseDelimitedFrom(InputStream input) throws IOException {
                return (RunningJob) parseDelimitedFrom(DEFAULT_INSTANCE, input);
            }

            public static RunningJob parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (RunningJob) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static RunningJob parseFrom(CodedInputStream input) throws IOException {
                return (RunningJob) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
            }

            public static RunningJob parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (RunningJob) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static Builder newBuilder() {
                return (Builder) DEFAULT_INSTANCE.toBuilder();
            }

            public static Builder newBuilder(RunningJob prototype) {
                return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
            }

            public static final class Builder extends GeneratedMessageLite.Builder<RunningJob, Builder> implements RunningJobOrBuilder {
                /* synthetic */ Builder(AnonymousClass1 x0) {
                    this();
                }

                private Builder() {
                    super(RunningJob.DEFAULT_INSTANCE);
                }

                @Override // com.android.server.job.JobSchedulerServiceDumpProto.ActiveJob.RunningJobOrBuilder
                public boolean hasInfo() {
                    return ((RunningJob) this.instance).hasInfo();
                }

                @Override // com.android.server.job.JobSchedulerServiceDumpProto.ActiveJob.RunningJobOrBuilder
                public JobStatusShortInfoProto getInfo() {
                    return ((RunningJob) this.instance).getInfo();
                }

                public Builder setInfo(JobStatusShortInfoProto value) {
                    copyOnWrite();
                    ((RunningJob) this.instance).setInfo((RunningJob) value);
                    return this;
                }

                public Builder setInfo(JobStatusShortInfoProto.Builder builderForValue) {
                    copyOnWrite();
                    ((RunningJob) this.instance).setInfo((RunningJob) builderForValue);
                    return this;
                }

                public Builder mergeInfo(JobStatusShortInfoProto value) {
                    copyOnWrite();
                    ((RunningJob) this.instance).mergeInfo(value);
                    return this;
                }

                public Builder clearInfo() {
                    copyOnWrite();
                    ((RunningJob) this.instance).clearInfo();
                    return this;
                }

                @Override // com.android.server.job.JobSchedulerServiceDumpProto.ActiveJob.RunningJobOrBuilder
                public boolean hasRunningDurationMs() {
                    return ((RunningJob) this.instance).hasRunningDurationMs();
                }

                @Override // com.android.server.job.JobSchedulerServiceDumpProto.ActiveJob.RunningJobOrBuilder
                public long getRunningDurationMs() {
                    return ((RunningJob) this.instance).getRunningDurationMs();
                }

                public Builder setRunningDurationMs(long value) {
                    copyOnWrite();
                    ((RunningJob) this.instance).setRunningDurationMs(value);
                    return this;
                }

                public Builder clearRunningDurationMs() {
                    copyOnWrite();
                    ((RunningJob) this.instance).clearRunningDurationMs();
                    return this;
                }

                @Override // com.android.server.job.JobSchedulerServiceDumpProto.ActiveJob.RunningJobOrBuilder
                public boolean hasTimeUntilTimeoutMs() {
                    return ((RunningJob) this.instance).hasTimeUntilTimeoutMs();
                }

                @Override // com.android.server.job.JobSchedulerServiceDumpProto.ActiveJob.RunningJobOrBuilder
                public long getTimeUntilTimeoutMs() {
                    return ((RunningJob) this.instance).getTimeUntilTimeoutMs();
                }

                public Builder setTimeUntilTimeoutMs(long value) {
                    copyOnWrite();
                    ((RunningJob) this.instance).setTimeUntilTimeoutMs(value);
                    return this;
                }

                public Builder clearTimeUntilTimeoutMs() {
                    copyOnWrite();
                    ((RunningJob) this.instance).clearTimeUntilTimeoutMs();
                    return this;
                }

                @Override // com.android.server.job.JobSchedulerServiceDumpProto.ActiveJob.RunningJobOrBuilder
                public boolean hasDump() {
                    return ((RunningJob) this.instance).hasDump();
                }

                @Override // com.android.server.job.JobSchedulerServiceDumpProto.ActiveJob.RunningJobOrBuilder
                public JobStatusDumpProto getDump() {
                    return ((RunningJob) this.instance).getDump();
                }

                public Builder setDump(JobStatusDumpProto value) {
                    copyOnWrite();
                    ((RunningJob) this.instance).setDump((RunningJob) value);
                    return this;
                }

                public Builder setDump(JobStatusDumpProto.Builder builderForValue) {
                    copyOnWrite();
                    ((RunningJob) this.instance).setDump((RunningJob) builderForValue);
                    return this;
                }

                public Builder mergeDump(JobStatusDumpProto value) {
                    copyOnWrite();
                    ((RunningJob) this.instance).mergeDump(value);
                    return this;
                }

                public Builder clearDump() {
                    copyOnWrite();
                    ((RunningJob) this.instance).clearDump();
                    return this;
                }

                @Override // com.android.server.job.JobSchedulerServiceDumpProto.ActiveJob.RunningJobOrBuilder
                public boolean hasEvaluatedPriority() {
                    return ((RunningJob) this.instance).hasEvaluatedPriority();
                }

                @Override // com.android.server.job.JobSchedulerServiceDumpProto.ActiveJob.RunningJobOrBuilder
                public int getEvaluatedPriority() {
                    return ((RunningJob) this.instance).getEvaluatedPriority();
                }

                public Builder setEvaluatedPriority(int value) {
                    copyOnWrite();
                    ((RunningJob) this.instance).setEvaluatedPriority(value);
                    return this;
                }

                public Builder clearEvaluatedPriority() {
                    copyOnWrite();
                    ((RunningJob) this.instance).clearEvaluatedPriority();
                    return this;
                }

                @Override // com.android.server.job.JobSchedulerServiceDumpProto.ActiveJob.RunningJobOrBuilder
                public boolean hasTimeSinceMadeActiveMs() {
                    return ((RunningJob) this.instance).hasTimeSinceMadeActiveMs();
                }

                @Override // com.android.server.job.JobSchedulerServiceDumpProto.ActiveJob.RunningJobOrBuilder
                public long getTimeSinceMadeActiveMs() {
                    return ((RunningJob) this.instance).getTimeSinceMadeActiveMs();
                }

                public Builder setTimeSinceMadeActiveMs(long value) {
                    copyOnWrite();
                    ((RunningJob) this.instance).setTimeSinceMadeActiveMs(value);
                    return this;
                }

                public Builder clearTimeSinceMadeActiveMs() {
                    copyOnWrite();
                    ((RunningJob) this.instance).clearTimeSinceMadeActiveMs();
                    return this;
                }

                @Override // com.android.server.job.JobSchedulerServiceDumpProto.ActiveJob.RunningJobOrBuilder
                public boolean hasPendingDurationMs() {
                    return ((RunningJob) this.instance).hasPendingDurationMs();
                }

                @Override // com.android.server.job.JobSchedulerServiceDumpProto.ActiveJob.RunningJobOrBuilder
                public long getPendingDurationMs() {
                    return ((RunningJob) this.instance).getPendingDurationMs();
                }

                public Builder setPendingDurationMs(long value) {
                    copyOnWrite();
                    ((RunningJob) this.instance).setPendingDurationMs(value);
                    return this;
                }

                public Builder clearPendingDurationMs() {
                    copyOnWrite();
                    ((RunningJob) this.instance).clearPendingDurationMs();
                    return this;
                }
            }

            /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
            /* access modifiers changed from: protected */
            @Override // com.google.protobuf.GeneratedMessageLite
            public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
                switch (method) {
                    case NEW_MUTABLE_INSTANCE:
                        return new RunningJob();
                    case IS_INITIALIZED:
                        return DEFAULT_INSTANCE;
                    case MAKE_IMMUTABLE:
                        return null;
                    case NEW_BUILDER:
                        return new Builder(null);
                    case VISIT:
                        GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                        RunningJob other = (RunningJob) arg1;
                        this.info_ = (JobStatusShortInfoProto) visitor.visitMessage(this.info_, other.info_);
                        this.runningDurationMs_ = visitor.visitLong(hasRunningDurationMs(), this.runningDurationMs_, other.hasRunningDurationMs(), other.runningDurationMs_);
                        this.timeUntilTimeoutMs_ = visitor.visitLong(hasTimeUntilTimeoutMs(), this.timeUntilTimeoutMs_, other.hasTimeUntilTimeoutMs(), other.timeUntilTimeoutMs_);
                        this.dump_ = (JobStatusDumpProto) visitor.visitMessage(this.dump_, other.dump_);
                        this.evaluatedPriority_ = visitor.visitInt(hasEvaluatedPriority(), this.evaluatedPriority_, other.hasEvaluatedPriority(), other.evaluatedPriority_);
                        this.timeSinceMadeActiveMs_ = visitor.visitLong(hasTimeSinceMadeActiveMs(), this.timeSinceMadeActiveMs_, other.hasTimeSinceMadeActiveMs(), other.timeSinceMadeActiveMs_);
                        this.pendingDurationMs_ = visitor.visitLong(hasPendingDurationMs(), this.pendingDurationMs_, other.hasPendingDurationMs(), other.pendingDurationMs_);
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
                                    JobStatusShortInfoProto.Builder subBuilder = null;
                                    if ((this.bitField0_ & 1) == 1) {
                                        subBuilder = (JobStatusShortInfoProto.Builder) this.info_.toBuilder();
                                    }
                                    this.info_ = (JobStatusShortInfoProto) input.readMessage(JobStatusShortInfoProto.parser(), extensionRegistry);
                                    if (subBuilder != null) {
                                        subBuilder.mergeFrom((GeneratedMessageLite) this.info_);
                                        this.info_ = (JobStatusShortInfoProto) subBuilder.buildPartial();
                                    }
                                    this.bitField0_ |= 1;
                                } else if (tag == 16) {
                                    this.bitField0_ |= 2;
                                    this.runningDurationMs_ = input.readInt64();
                                } else if (tag == 24) {
                                    this.bitField0_ |= 4;
                                    this.timeUntilTimeoutMs_ = input.readInt64();
                                } else if (tag == 34) {
                                    JobStatusDumpProto.Builder subBuilder2 = null;
                                    if ((this.bitField0_ & 8) == 8) {
                                        subBuilder2 = (JobStatusDumpProto.Builder) this.dump_.toBuilder();
                                    }
                                    this.dump_ = (JobStatusDumpProto) input.readMessage(JobStatusDumpProto.parser(), extensionRegistry);
                                    if (subBuilder2 != null) {
                                        subBuilder2.mergeFrom((GeneratedMessageLite) this.dump_);
                                        this.dump_ = (JobStatusDumpProto) subBuilder2.buildPartial();
                                    }
                                    this.bitField0_ |= 8;
                                } else if (tag == 40) {
                                    this.bitField0_ = 16 | this.bitField0_;
                                    this.evaluatedPriority_ = input.readSInt32();
                                } else if (tag == 48) {
                                    this.bitField0_ |= 32;
                                    this.timeSinceMadeActiveMs_ = input.readInt64();
                                } else if (tag == 56) {
                                    this.bitField0_ |= 64;
                                    this.pendingDurationMs_ = input.readInt64();
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
                            synchronized (RunningJob.class) {
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

            public static RunningJob getDefaultInstance() {
                return DEFAULT_INSTANCE;
            }

            public static Parser<RunningJob> parser() {
                return DEFAULT_INSTANCE.getParserForType();
            }
        }

        public enum JobCase implements Internal.EnumLite {
            INACTIVE(1),
            RUNNING(2),
            JOB_NOT_SET(0);
            
            private final int value;

            private JobCase(int value2) {
                this.value = value2;
            }

            @Deprecated
            public static JobCase valueOf(int value2) {
                return forNumber(value2);
            }

            public static JobCase forNumber(int value2) {
                if (value2 == 0) {
                    return JOB_NOT_SET;
                }
                if (value2 == 1) {
                    return INACTIVE;
                }
                if (value2 != 2) {
                    return null;
                }
                return RUNNING;
            }

            @Override // com.google.protobuf.Internal.EnumLite
            public int getNumber() {
                return this.value;
            }
        }

        @Override // com.android.server.job.JobSchedulerServiceDumpProto.ActiveJobOrBuilder
        public JobCase getJobCase() {
            return JobCase.forNumber(this.jobCase_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearJob() {
            this.jobCase_ = 0;
            this.job_ = null;
        }

        @Override // com.android.server.job.JobSchedulerServiceDumpProto.ActiveJobOrBuilder
        public boolean hasInactive() {
            return this.jobCase_ == 1;
        }

        @Override // com.android.server.job.JobSchedulerServiceDumpProto.ActiveJobOrBuilder
        public InactiveJob getInactive() {
            if (this.jobCase_ == 1) {
                return (InactiveJob) this.job_;
            }
            return InactiveJob.getDefaultInstance();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setInactive(InactiveJob value) {
            if (value != null) {
                this.job_ = value;
                this.jobCase_ = 1;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setInactive(InactiveJob.Builder builderForValue) {
            this.job_ = builderForValue.build();
            this.jobCase_ = 1;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void mergeInactive(InactiveJob value) {
            if (this.jobCase_ != 1 || this.job_ == InactiveJob.getDefaultInstance()) {
                this.job_ = value;
            } else {
                this.job_ = ((InactiveJob.Builder) InactiveJob.newBuilder((InactiveJob) this.job_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
            }
            this.jobCase_ = 1;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearInactive() {
            if (this.jobCase_ == 1) {
                this.jobCase_ = 0;
                this.job_ = null;
            }
        }

        @Override // com.android.server.job.JobSchedulerServiceDumpProto.ActiveJobOrBuilder
        public boolean hasRunning() {
            return this.jobCase_ == 2;
        }

        @Override // com.android.server.job.JobSchedulerServiceDumpProto.ActiveJobOrBuilder
        public RunningJob getRunning() {
            if (this.jobCase_ == 2) {
                return (RunningJob) this.job_;
            }
            return RunningJob.getDefaultInstance();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setRunning(RunningJob value) {
            if (value != null) {
                this.job_ = value;
                this.jobCase_ = 2;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setRunning(RunningJob.Builder builderForValue) {
            this.job_ = builderForValue.build();
            this.jobCase_ = 2;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void mergeRunning(RunningJob value) {
            if (this.jobCase_ != 2 || this.job_ == RunningJob.getDefaultInstance()) {
                this.job_ = value;
            } else {
                this.job_ = ((RunningJob.Builder) RunningJob.newBuilder((RunningJob) this.job_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
            }
            this.jobCase_ = 2;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearRunning() {
            if (this.jobCase_ == 2) {
                this.jobCase_ = 0;
                this.job_ = null;
            }
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if (this.jobCase_ == 1) {
                output.writeMessage(1, (InactiveJob) this.job_);
            }
            if (this.jobCase_ == 2) {
                output.writeMessage(2, (RunningJob) this.job_);
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
            if (this.jobCase_ == 1) {
                size2 = 0 + CodedOutputStream.computeMessageSize(1, (InactiveJob) this.job_);
            }
            if (this.jobCase_ == 2) {
                size2 += CodedOutputStream.computeMessageSize(2, (RunningJob) this.job_);
            }
            int size3 = size2 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static ActiveJob parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (ActiveJob) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static ActiveJob parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (ActiveJob) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static ActiveJob parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (ActiveJob) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static ActiveJob parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (ActiveJob) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static ActiveJob parseFrom(InputStream input) throws IOException {
            return (ActiveJob) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static ActiveJob parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (ActiveJob) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static ActiveJob parseDelimitedFrom(InputStream input) throws IOException {
            return (ActiveJob) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static ActiveJob parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (ActiveJob) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static ActiveJob parseFrom(CodedInputStream input) throws IOException {
            return (ActiveJob) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static ActiveJob parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (ActiveJob) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(ActiveJob prototype) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<ActiveJob, Builder> implements ActiveJobOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 x0) {
                this();
            }

            private Builder() {
                super(ActiveJob.DEFAULT_INSTANCE);
            }

            @Override // com.android.server.job.JobSchedulerServiceDumpProto.ActiveJobOrBuilder
            public JobCase getJobCase() {
                return ((ActiveJob) this.instance).getJobCase();
            }

            public Builder clearJob() {
                copyOnWrite();
                ((ActiveJob) this.instance).clearJob();
                return this;
            }

            @Override // com.android.server.job.JobSchedulerServiceDumpProto.ActiveJobOrBuilder
            public boolean hasInactive() {
                return ((ActiveJob) this.instance).hasInactive();
            }

            @Override // com.android.server.job.JobSchedulerServiceDumpProto.ActiveJobOrBuilder
            public InactiveJob getInactive() {
                return ((ActiveJob) this.instance).getInactive();
            }

            public Builder setInactive(InactiveJob value) {
                copyOnWrite();
                ((ActiveJob) this.instance).setInactive((ActiveJob) value);
                return this;
            }

            public Builder setInactive(InactiveJob.Builder builderForValue) {
                copyOnWrite();
                ((ActiveJob) this.instance).setInactive((ActiveJob) builderForValue);
                return this;
            }

            public Builder mergeInactive(InactiveJob value) {
                copyOnWrite();
                ((ActiveJob) this.instance).mergeInactive(value);
                return this;
            }

            public Builder clearInactive() {
                copyOnWrite();
                ((ActiveJob) this.instance).clearInactive();
                return this;
            }

            @Override // com.android.server.job.JobSchedulerServiceDumpProto.ActiveJobOrBuilder
            public boolean hasRunning() {
                return ((ActiveJob) this.instance).hasRunning();
            }

            @Override // com.android.server.job.JobSchedulerServiceDumpProto.ActiveJobOrBuilder
            public RunningJob getRunning() {
                return ((ActiveJob) this.instance).getRunning();
            }

            public Builder setRunning(RunningJob value) {
                copyOnWrite();
                ((ActiveJob) this.instance).setRunning((ActiveJob) value);
                return this;
            }

            public Builder setRunning(RunningJob.Builder builderForValue) {
                copyOnWrite();
                ((ActiveJob) this.instance).setRunning((ActiveJob) builderForValue);
                return this;
            }

            public Builder mergeRunning(RunningJob value) {
                copyOnWrite();
                ((ActiveJob) this.instance).mergeRunning(value);
                return this;
            }

            public Builder clearRunning() {
                copyOnWrite();
                ((ActiveJob) this.instance).clearRunning();
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            boolean z = true;
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new ActiveJob();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder(null);
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    ActiveJob other = (ActiveJob) arg1;
                    int i = AnonymousClass1.$SwitchMap$com$android$server$job$JobSchedulerServiceDumpProto$ActiveJob$JobCase[other.getJobCase().ordinal()];
                    if (i == 1) {
                        if (this.jobCase_ != 1) {
                            z = false;
                        }
                        this.job_ = visitor.visitOneofMessage(z, this.job_, other.job_);
                    } else if (i == 2) {
                        if (this.jobCase_ != 2) {
                            z = false;
                        }
                        this.job_ = visitor.visitOneofMessage(z, this.job_, other.job_);
                    } else if (i == 3) {
                        if (this.jobCase_ == 0) {
                            z = false;
                        }
                        visitor.visitOneofNotSet(z);
                    }
                    if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE) {
                        int i2 = other.jobCase_;
                        if (i2 != 0) {
                            this.jobCase_ = i2;
                        }
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
                                InactiveJob.Builder subBuilder = null;
                                if (this.jobCase_ == 1) {
                                    subBuilder = (InactiveJob.Builder) ((InactiveJob) this.job_).toBuilder();
                                }
                                this.job_ = input.readMessage(InactiveJob.parser(), extensionRegistry);
                                if (subBuilder != null) {
                                    subBuilder.mergeFrom((GeneratedMessageLite) ((InactiveJob) this.job_));
                                    this.job_ = subBuilder.buildPartial();
                                }
                                this.jobCase_ = 1;
                            } else if (tag == 18) {
                                RunningJob.Builder subBuilder2 = null;
                                if (this.jobCase_ == 2) {
                                    subBuilder2 = (RunningJob.Builder) ((RunningJob) this.job_).toBuilder();
                                }
                                this.job_ = input.readMessage(RunningJob.parser(), extensionRegistry);
                                if (subBuilder2 != null) {
                                    subBuilder2.mergeFrom((GeneratedMessageLite) ((RunningJob) this.job_));
                                    this.job_ = subBuilder2.buildPartial();
                                }
                                this.jobCase_ = 2;
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
                        synchronized (ActiveJob.class) {
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

        public static ActiveJob getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<ActiveJob> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    /* renamed from: com.android.server.job.JobSchedulerServiceDumpProto$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$android$server$job$JobSchedulerServiceDumpProto$ActiveJob$JobCase = new int[ActiveJob.JobCase.values().length];

        static {
            try {
                $SwitchMap$com$android$server$job$JobSchedulerServiceDumpProto$ActiveJob$JobCase[ActiveJob.JobCase.INACTIVE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$android$server$job$JobSchedulerServiceDumpProto$ActiveJob$JobCase[ActiveJob.JobCase.RUNNING.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$android$server$job$JobSchedulerServiceDumpProto$ActiveJob$JobCase[ActiveJob.JobCase.JOB_NOT_SET.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke = new int[GeneratedMessageLite.MethodToInvoke.values().length];
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.NEW_MUTABLE_INSTANCE.ordinal()] = 1;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.IS_INITIALIZED.ordinal()] = 2;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.MAKE_IMMUTABLE.ordinal()] = 3;
            } catch (NoSuchFieldError e6) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.NEW_BUILDER.ordinal()] = 4;
            } catch (NoSuchFieldError e7) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.VISIT.ordinal()] = 5;
            } catch (NoSuchFieldError e8) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.MERGE_FROM_STREAM.ordinal()] = 6;
            } catch (NoSuchFieldError e9) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.GET_DEFAULT_INSTANCE.ordinal()] = 7;
            } catch (NoSuchFieldError e10) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.GET_PARSER.ordinal()] = 8;
            } catch (NoSuchFieldError e11) {
            }
        }
    }

    @Override // com.android.server.job.JobSchedulerServiceDumpProtoOrBuilder
    public boolean hasSettings() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // com.android.server.job.JobSchedulerServiceDumpProtoOrBuilder
    public ConstantsProto getSettings() {
        ConstantsProto constantsProto = this.settings_;
        return constantsProto == null ? ConstantsProto.getDefaultInstance() : constantsProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setSettings(ConstantsProto value) {
        if (value != null) {
            this.settings_ = value;
            this.bitField0_ |= 1;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setSettings(ConstantsProto.Builder builderForValue) {
        this.settings_ = (ConstantsProto) builderForValue.build();
        this.bitField0_ |= 1;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeSettings(ConstantsProto value) {
        ConstantsProto constantsProto = this.settings_;
        if (constantsProto == null || constantsProto == ConstantsProto.getDefaultInstance()) {
            this.settings_ = value;
        } else {
            this.settings_ = (ConstantsProto) ((ConstantsProto.Builder) ConstantsProto.newBuilder(this.settings_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 1;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearSettings() {
        this.settings_ = null;
        this.bitField0_ &= -2;
    }

    @Override // com.android.server.job.JobSchedulerServiceDumpProtoOrBuilder
    public boolean hasCurrentHeartbeat() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // com.android.server.job.JobSchedulerServiceDumpProtoOrBuilder
    public int getCurrentHeartbeat() {
        return this.currentHeartbeat_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setCurrentHeartbeat(int value) {
        this.bitField0_ |= 2;
        this.currentHeartbeat_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearCurrentHeartbeat() {
        this.bitField0_ &= -3;
        this.currentHeartbeat_ = 0;
    }

    @Override // com.android.server.job.JobSchedulerServiceDumpProtoOrBuilder
    public List<Integer> getNextHeartbeatList() {
        return this.nextHeartbeat_;
    }

    @Override // com.android.server.job.JobSchedulerServiceDumpProtoOrBuilder
    public int getNextHeartbeatCount() {
        return this.nextHeartbeat_.size();
    }

    @Override // com.android.server.job.JobSchedulerServiceDumpProtoOrBuilder
    public int getNextHeartbeat(int index) {
        return this.nextHeartbeat_.getInt(index);
    }

    private void ensureNextHeartbeatIsMutable() {
        if (!this.nextHeartbeat_.isModifiable()) {
            this.nextHeartbeat_ = GeneratedMessageLite.mutableCopy(this.nextHeartbeat_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setNextHeartbeat(int index, int value) {
        ensureNextHeartbeatIsMutable();
        this.nextHeartbeat_.setInt(index, value);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addNextHeartbeat(int value) {
        ensureNextHeartbeatIsMutable();
        this.nextHeartbeat_.addInt(value);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllNextHeartbeat(Iterable<? extends Integer> values) {
        ensureNextHeartbeatIsMutable();
        AbstractMessageLite.addAll(values, this.nextHeartbeat_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearNextHeartbeat() {
        this.nextHeartbeat_ = emptyIntList();
    }

    @Override // com.android.server.job.JobSchedulerServiceDumpProtoOrBuilder
    public boolean hasLastHeartbeatTimeMillis() {
        return (this.bitField0_ & 4) == 4;
    }

    @Override // com.android.server.job.JobSchedulerServiceDumpProtoOrBuilder
    public long getLastHeartbeatTimeMillis() {
        return this.lastHeartbeatTimeMillis_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setLastHeartbeatTimeMillis(long value) {
        this.bitField0_ |= 4;
        this.lastHeartbeatTimeMillis_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearLastHeartbeatTimeMillis() {
        this.bitField0_ &= -5;
        this.lastHeartbeatTimeMillis_ = 0;
    }

    @Override // com.android.server.job.JobSchedulerServiceDumpProtoOrBuilder
    public boolean hasNextHeartbeatTimeMillis() {
        return (this.bitField0_ & 8) == 8;
    }

    @Override // com.android.server.job.JobSchedulerServiceDumpProtoOrBuilder
    public long getNextHeartbeatTimeMillis() {
        return this.nextHeartbeatTimeMillis_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setNextHeartbeatTimeMillis(long value) {
        this.bitField0_ |= 8;
        this.nextHeartbeatTimeMillis_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearNextHeartbeatTimeMillis() {
        this.bitField0_ &= -9;
        this.nextHeartbeatTimeMillis_ = 0;
    }

    @Override // com.android.server.job.JobSchedulerServiceDumpProtoOrBuilder
    public boolean hasInParole() {
        return (this.bitField0_ & 16) == 16;
    }

    @Override // com.android.server.job.JobSchedulerServiceDumpProtoOrBuilder
    public boolean getInParole() {
        return this.inParole_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setInParole(boolean value) {
        this.bitField0_ |= 16;
        this.inParole_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearInParole() {
        this.bitField0_ &= -17;
        this.inParole_ = false;
    }

    @Override // com.android.server.job.JobSchedulerServiceDumpProtoOrBuilder
    public boolean hasInThermal() {
        return (this.bitField0_ & 32) == 32;
    }

    @Override // com.android.server.job.JobSchedulerServiceDumpProtoOrBuilder
    public boolean getInThermal() {
        return this.inThermal_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setInThermal(boolean value) {
        this.bitField0_ |= 32;
        this.inThermal_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearInThermal() {
        this.bitField0_ &= -33;
        this.inThermal_ = false;
    }

    @Override // com.android.server.job.JobSchedulerServiceDumpProtoOrBuilder
    public List<Integer> getStartedUsersList() {
        return this.startedUsers_;
    }

    @Override // com.android.server.job.JobSchedulerServiceDumpProtoOrBuilder
    public int getStartedUsersCount() {
        return this.startedUsers_.size();
    }

    @Override // com.android.server.job.JobSchedulerServiceDumpProtoOrBuilder
    public int getStartedUsers(int index) {
        return this.startedUsers_.getInt(index);
    }

    private void ensureStartedUsersIsMutable() {
        if (!this.startedUsers_.isModifiable()) {
            this.startedUsers_ = GeneratedMessageLite.mutableCopy(this.startedUsers_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setStartedUsers(int index, int value) {
        ensureStartedUsersIsMutable();
        this.startedUsers_.setInt(index, value);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addStartedUsers(int value) {
        ensureStartedUsersIsMutable();
        this.startedUsers_.addInt(value);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllStartedUsers(Iterable<? extends Integer> values) {
        ensureStartedUsersIsMutable();
        AbstractMessageLite.addAll(values, this.startedUsers_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearStartedUsers() {
        this.startedUsers_ = emptyIntList();
    }

    @Override // com.android.server.job.JobSchedulerServiceDumpProtoOrBuilder
    public List<RegisteredJob> getRegisteredJobsList() {
        return this.registeredJobs_;
    }

    public List<? extends RegisteredJobOrBuilder> getRegisteredJobsOrBuilderList() {
        return this.registeredJobs_;
    }

    @Override // com.android.server.job.JobSchedulerServiceDumpProtoOrBuilder
    public int getRegisteredJobsCount() {
        return this.registeredJobs_.size();
    }

    @Override // com.android.server.job.JobSchedulerServiceDumpProtoOrBuilder
    public RegisteredJob getRegisteredJobs(int index) {
        return this.registeredJobs_.get(index);
    }

    public RegisteredJobOrBuilder getRegisteredJobsOrBuilder(int index) {
        return this.registeredJobs_.get(index);
    }

    private void ensureRegisteredJobsIsMutable() {
        if (!this.registeredJobs_.isModifiable()) {
            this.registeredJobs_ = GeneratedMessageLite.mutableCopy(this.registeredJobs_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setRegisteredJobs(int index, RegisteredJob value) {
        if (value != null) {
            ensureRegisteredJobsIsMutable();
            this.registeredJobs_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setRegisteredJobs(int index, RegisteredJob.Builder builderForValue) {
        ensureRegisteredJobsIsMutable();
        this.registeredJobs_.set(index, (RegisteredJob) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addRegisteredJobs(RegisteredJob value) {
        if (value != null) {
            ensureRegisteredJobsIsMutable();
            this.registeredJobs_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addRegisteredJobs(int index, RegisteredJob value) {
        if (value != null) {
            ensureRegisteredJobsIsMutable();
            this.registeredJobs_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addRegisteredJobs(RegisteredJob.Builder builderForValue) {
        ensureRegisteredJobsIsMutable();
        this.registeredJobs_.add((RegisteredJob) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addRegisteredJobs(int index, RegisteredJob.Builder builderForValue) {
        ensureRegisteredJobsIsMutable();
        this.registeredJobs_.add(index, (RegisteredJob) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllRegisteredJobs(Iterable<? extends RegisteredJob> values) {
        ensureRegisteredJobsIsMutable();
        AbstractMessageLite.addAll(values, this.registeredJobs_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearRegisteredJobs() {
        this.registeredJobs_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeRegisteredJobs(int index) {
        ensureRegisteredJobsIsMutable();
        this.registeredJobs_.remove(index);
    }

    @Override // com.android.server.job.JobSchedulerServiceDumpProtoOrBuilder
    public List<StateControllerProto> getControllersList() {
        return this.controllers_;
    }

    public List<? extends StateControllerProtoOrBuilder> getControllersOrBuilderList() {
        return this.controllers_;
    }

    @Override // com.android.server.job.JobSchedulerServiceDumpProtoOrBuilder
    public int getControllersCount() {
        return this.controllers_.size();
    }

    @Override // com.android.server.job.JobSchedulerServiceDumpProtoOrBuilder
    public StateControllerProto getControllers(int index) {
        return this.controllers_.get(index);
    }

    public StateControllerProtoOrBuilder getControllersOrBuilder(int index) {
        return this.controllers_.get(index);
    }

    private void ensureControllersIsMutable() {
        if (!this.controllers_.isModifiable()) {
            this.controllers_ = GeneratedMessageLite.mutableCopy(this.controllers_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setControllers(int index, StateControllerProto value) {
        if (value != null) {
            ensureControllersIsMutable();
            this.controllers_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setControllers(int index, StateControllerProto.Builder builderForValue) {
        ensureControllersIsMutable();
        this.controllers_.set(index, (StateControllerProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addControllers(StateControllerProto value) {
        if (value != null) {
            ensureControllersIsMutable();
            this.controllers_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addControllers(int index, StateControllerProto value) {
        if (value != null) {
            ensureControllersIsMutable();
            this.controllers_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addControllers(StateControllerProto.Builder builderForValue) {
        ensureControllersIsMutable();
        this.controllers_.add((StateControllerProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addControllers(int index, StateControllerProto.Builder builderForValue) {
        ensureControllersIsMutable();
        this.controllers_.add(index, (StateControllerProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllControllers(Iterable<? extends StateControllerProto> values) {
        ensureControllersIsMutable();
        AbstractMessageLite.addAll(values, this.controllers_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearControllers() {
        this.controllers_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeControllers(int index) {
        ensureControllersIsMutable();
        this.controllers_.remove(index);
    }

    @Override // com.android.server.job.JobSchedulerServiceDumpProtoOrBuilder
    public List<PriorityOverride> getPriorityOverridesList() {
        return this.priorityOverrides_;
    }

    public List<? extends PriorityOverrideOrBuilder> getPriorityOverridesOrBuilderList() {
        return this.priorityOverrides_;
    }

    @Override // com.android.server.job.JobSchedulerServiceDumpProtoOrBuilder
    public int getPriorityOverridesCount() {
        return this.priorityOverrides_.size();
    }

    @Override // com.android.server.job.JobSchedulerServiceDumpProtoOrBuilder
    public PriorityOverride getPriorityOverrides(int index) {
        return this.priorityOverrides_.get(index);
    }

    public PriorityOverrideOrBuilder getPriorityOverridesOrBuilder(int index) {
        return this.priorityOverrides_.get(index);
    }

    private void ensurePriorityOverridesIsMutable() {
        if (!this.priorityOverrides_.isModifiable()) {
            this.priorityOverrides_ = GeneratedMessageLite.mutableCopy(this.priorityOverrides_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPriorityOverrides(int index, PriorityOverride value) {
        if (value != null) {
            ensurePriorityOverridesIsMutable();
            this.priorityOverrides_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPriorityOverrides(int index, PriorityOverride.Builder builderForValue) {
        ensurePriorityOverridesIsMutable();
        this.priorityOverrides_.set(index, (PriorityOverride) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addPriorityOverrides(PriorityOverride value) {
        if (value != null) {
            ensurePriorityOverridesIsMutable();
            this.priorityOverrides_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addPriorityOverrides(int index, PriorityOverride value) {
        if (value != null) {
            ensurePriorityOverridesIsMutable();
            this.priorityOverrides_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addPriorityOverrides(PriorityOverride.Builder builderForValue) {
        ensurePriorityOverridesIsMutable();
        this.priorityOverrides_.add((PriorityOverride) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addPriorityOverrides(int index, PriorityOverride.Builder builderForValue) {
        ensurePriorityOverridesIsMutable();
        this.priorityOverrides_.add(index, (PriorityOverride) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllPriorityOverrides(Iterable<? extends PriorityOverride> values) {
        ensurePriorityOverridesIsMutable();
        AbstractMessageLite.addAll(values, this.priorityOverrides_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearPriorityOverrides() {
        this.priorityOverrides_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removePriorityOverrides(int index) {
        ensurePriorityOverridesIsMutable();
        this.priorityOverrides_.remove(index);
    }

    @Override // com.android.server.job.JobSchedulerServiceDumpProtoOrBuilder
    public List<Integer> getBackingUpUidsList() {
        return this.backingUpUids_;
    }

    @Override // com.android.server.job.JobSchedulerServiceDumpProtoOrBuilder
    public int getBackingUpUidsCount() {
        return this.backingUpUids_.size();
    }

    @Override // com.android.server.job.JobSchedulerServiceDumpProtoOrBuilder
    public int getBackingUpUids(int index) {
        return this.backingUpUids_.getInt(index);
    }

    private void ensureBackingUpUidsIsMutable() {
        if (!this.backingUpUids_.isModifiable()) {
            this.backingUpUids_ = GeneratedMessageLite.mutableCopy(this.backingUpUids_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setBackingUpUids(int index, int value) {
        ensureBackingUpUidsIsMutable();
        this.backingUpUids_.setInt(index, value);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addBackingUpUids(int value) {
        ensureBackingUpUidsIsMutable();
        this.backingUpUids_.addInt(value);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllBackingUpUids(Iterable<? extends Integer> values) {
        ensureBackingUpUidsIsMutable();
        AbstractMessageLite.addAll(values, this.backingUpUids_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearBackingUpUids() {
        this.backingUpUids_ = emptyIntList();
    }

    @Override // com.android.server.job.JobSchedulerServiceDumpProtoOrBuilder
    public boolean hasHistory() {
        return (this.bitField0_ & 64) == 64;
    }

    @Override // com.android.server.job.JobSchedulerServiceDumpProtoOrBuilder
    public JobPackageHistoryProto getHistory() {
        JobPackageHistoryProto jobPackageHistoryProto = this.history_;
        return jobPackageHistoryProto == null ? JobPackageHistoryProto.getDefaultInstance() : jobPackageHistoryProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setHistory(JobPackageHistoryProto value) {
        if (value != null) {
            this.history_ = value;
            this.bitField0_ |= 64;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setHistory(JobPackageHistoryProto.Builder builderForValue) {
        this.history_ = (JobPackageHistoryProto) builderForValue.build();
        this.bitField0_ |= 64;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeHistory(JobPackageHistoryProto value) {
        JobPackageHistoryProto jobPackageHistoryProto = this.history_;
        if (jobPackageHistoryProto == null || jobPackageHistoryProto == JobPackageHistoryProto.getDefaultInstance()) {
            this.history_ = value;
        } else {
            this.history_ = (JobPackageHistoryProto) ((JobPackageHistoryProto.Builder) JobPackageHistoryProto.newBuilder(this.history_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 64;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearHistory() {
        this.history_ = null;
        this.bitField0_ &= -65;
    }

    @Override // com.android.server.job.JobSchedulerServiceDumpProtoOrBuilder
    public boolean hasPackageTracker() {
        return (this.bitField0_ & 128) == 128;
    }

    @Override // com.android.server.job.JobSchedulerServiceDumpProtoOrBuilder
    public JobPackageTrackerDumpProto getPackageTracker() {
        JobPackageTrackerDumpProto jobPackageTrackerDumpProto = this.packageTracker_;
        return jobPackageTrackerDumpProto == null ? JobPackageTrackerDumpProto.getDefaultInstance() : jobPackageTrackerDumpProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPackageTracker(JobPackageTrackerDumpProto value) {
        if (value != null) {
            this.packageTracker_ = value;
            this.bitField0_ |= 128;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPackageTracker(JobPackageTrackerDumpProto.Builder builderForValue) {
        this.packageTracker_ = (JobPackageTrackerDumpProto) builderForValue.build();
        this.bitField0_ |= 128;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergePackageTracker(JobPackageTrackerDumpProto value) {
        JobPackageTrackerDumpProto jobPackageTrackerDumpProto = this.packageTracker_;
        if (jobPackageTrackerDumpProto == null || jobPackageTrackerDumpProto == JobPackageTrackerDumpProto.getDefaultInstance()) {
            this.packageTracker_ = value;
        } else {
            this.packageTracker_ = (JobPackageTrackerDumpProto) ((JobPackageTrackerDumpProto.Builder) JobPackageTrackerDumpProto.newBuilder(this.packageTracker_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 128;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearPackageTracker() {
        this.packageTracker_ = null;
        this.bitField0_ &= -129;
    }

    @Override // com.android.server.job.JobSchedulerServiceDumpProtoOrBuilder
    public List<PendingJob> getPendingJobsList() {
        return this.pendingJobs_;
    }

    public List<? extends PendingJobOrBuilder> getPendingJobsOrBuilderList() {
        return this.pendingJobs_;
    }

    @Override // com.android.server.job.JobSchedulerServiceDumpProtoOrBuilder
    public int getPendingJobsCount() {
        return this.pendingJobs_.size();
    }

    @Override // com.android.server.job.JobSchedulerServiceDumpProtoOrBuilder
    public PendingJob getPendingJobs(int index) {
        return this.pendingJobs_.get(index);
    }

    public PendingJobOrBuilder getPendingJobsOrBuilder(int index) {
        return this.pendingJobs_.get(index);
    }

    private void ensurePendingJobsIsMutable() {
        if (!this.pendingJobs_.isModifiable()) {
            this.pendingJobs_ = GeneratedMessageLite.mutableCopy(this.pendingJobs_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPendingJobs(int index, PendingJob value) {
        if (value != null) {
            ensurePendingJobsIsMutable();
            this.pendingJobs_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPendingJobs(int index, PendingJob.Builder builderForValue) {
        ensurePendingJobsIsMutable();
        this.pendingJobs_.set(index, (PendingJob) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addPendingJobs(PendingJob value) {
        if (value != null) {
            ensurePendingJobsIsMutable();
            this.pendingJobs_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addPendingJobs(int index, PendingJob value) {
        if (value != null) {
            ensurePendingJobsIsMutable();
            this.pendingJobs_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addPendingJobs(PendingJob.Builder builderForValue) {
        ensurePendingJobsIsMutable();
        this.pendingJobs_.add((PendingJob) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addPendingJobs(int index, PendingJob.Builder builderForValue) {
        ensurePendingJobsIsMutable();
        this.pendingJobs_.add(index, (PendingJob) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllPendingJobs(Iterable<? extends PendingJob> values) {
        ensurePendingJobsIsMutable();
        AbstractMessageLite.addAll(values, this.pendingJobs_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearPendingJobs() {
        this.pendingJobs_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removePendingJobs(int index) {
        ensurePendingJobsIsMutable();
        this.pendingJobs_.remove(index);
    }

    @Override // com.android.server.job.JobSchedulerServiceDumpProtoOrBuilder
    public List<ActiveJob> getActiveJobsList() {
        return this.activeJobs_;
    }

    public List<? extends ActiveJobOrBuilder> getActiveJobsOrBuilderList() {
        return this.activeJobs_;
    }

    @Override // com.android.server.job.JobSchedulerServiceDumpProtoOrBuilder
    public int getActiveJobsCount() {
        return this.activeJobs_.size();
    }

    @Override // com.android.server.job.JobSchedulerServiceDumpProtoOrBuilder
    public ActiveJob getActiveJobs(int index) {
        return this.activeJobs_.get(index);
    }

    public ActiveJobOrBuilder getActiveJobsOrBuilder(int index) {
        return this.activeJobs_.get(index);
    }

    private void ensureActiveJobsIsMutable() {
        if (!this.activeJobs_.isModifiable()) {
            this.activeJobs_ = GeneratedMessageLite.mutableCopy(this.activeJobs_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setActiveJobs(int index, ActiveJob value) {
        if (value != null) {
            ensureActiveJobsIsMutable();
            this.activeJobs_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setActiveJobs(int index, ActiveJob.Builder builderForValue) {
        ensureActiveJobsIsMutable();
        this.activeJobs_.set(index, (ActiveJob) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addActiveJobs(ActiveJob value) {
        if (value != null) {
            ensureActiveJobsIsMutable();
            this.activeJobs_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addActiveJobs(int index, ActiveJob value) {
        if (value != null) {
            ensureActiveJobsIsMutable();
            this.activeJobs_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addActiveJobs(ActiveJob.Builder builderForValue) {
        ensureActiveJobsIsMutable();
        this.activeJobs_.add((ActiveJob) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addActiveJobs(int index, ActiveJob.Builder builderForValue) {
        ensureActiveJobsIsMutable();
        this.activeJobs_.add(index, (ActiveJob) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllActiveJobs(Iterable<? extends ActiveJob> values) {
        ensureActiveJobsIsMutable();
        AbstractMessageLite.addAll(values, this.activeJobs_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearActiveJobs() {
        this.activeJobs_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeActiveJobs(int index) {
        ensureActiveJobsIsMutable();
        this.activeJobs_.remove(index);
    }

    @Override // com.android.server.job.JobSchedulerServiceDumpProtoOrBuilder
    public boolean hasIsReadyToRock() {
        return (this.bitField0_ & 256) == 256;
    }

    @Override // com.android.server.job.JobSchedulerServiceDumpProtoOrBuilder
    public boolean getIsReadyToRock() {
        return this.isReadyToRock_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setIsReadyToRock(boolean value) {
        this.bitField0_ |= 256;
        this.isReadyToRock_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearIsReadyToRock() {
        this.bitField0_ &= -257;
        this.isReadyToRock_ = false;
    }

    @Override // com.android.server.job.JobSchedulerServiceDumpProtoOrBuilder
    public boolean hasReportedActive() {
        return (this.bitField0_ & 512) == 512;
    }

    @Override // com.android.server.job.JobSchedulerServiceDumpProtoOrBuilder
    public boolean getReportedActive() {
        return this.reportedActive_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setReportedActive(boolean value) {
        this.bitField0_ |= 512;
        this.reportedActive_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearReportedActive() {
        this.bitField0_ &= -513;
        this.reportedActive_ = false;
    }

    @Override // com.android.server.job.JobSchedulerServiceDumpProtoOrBuilder
    public boolean hasMaxActiveJobs() {
        return (this.bitField0_ & 1024) == 1024;
    }

    @Override // com.android.server.job.JobSchedulerServiceDumpProtoOrBuilder
    public int getMaxActiveJobs() {
        return this.maxActiveJobs_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setMaxActiveJobs(int value) {
        this.bitField0_ |= 1024;
        this.maxActiveJobs_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearMaxActiveJobs() {
        this.bitField0_ &= -1025;
        this.maxActiveJobs_ = 0;
    }

    @Override // com.android.server.job.JobSchedulerServiceDumpProtoOrBuilder
    public boolean hasConcurrencyManager() {
        return (this.bitField0_ & 2048) == 2048;
    }

    @Override // com.android.server.job.JobSchedulerServiceDumpProtoOrBuilder
    public JobConcurrencyManagerProto getConcurrencyManager() {
        JobConcurrencyManagerProto jobConcurrencyManagerProto = this.concurrencyManager_;
        return jobConcurrencyManagerProto == null ? JobConcurrencyManagerProto.getDefaultInstance() : jobConcurrencyManagerProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setConcurrencyManager(JobConcurrencyManagerProto value) {
        if (value != null) {
            this.concurrencyManager_ = value;
            this.bitField0_ |= 2048;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setConcurrencyManager(JobConcurrencyManagerProto.Builder builderForValue) {
        this.concurrencyManager_ = (JobConcurrencyManagerProto) builderForValue.build();
        this.bitField0_ |= 2048;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeConcurrencyManager(JobConcurrencyManagerProto value) {
        JobConcurrencyManagerProto jobConcurrencyManagerProto = this.concurrencyManager_;
        if (jobConcurrencyManagerProto == null || jobConcurrencyManagerProto == JobConcurrencyManagerProto.getDefaultInstance()) {
            this.concurrencyManager_ = value;
        } else {
            this.concurrencyManager_ = (JobConcurrencyManagerProto) ((JobConcurrencyManagerProto.Builder) JobConcurrencyManagerProto.newBuilder(this.concurrencyManager_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 2048;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearConcurrencyManager() {
        this.concurrencyManager_ = null;
        this.bitField0_ &= -2049;
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeMessage(1, getSettings());
        }
        for (int i = 0; i < this.startedUsers_.size(); i++) {
            output.writeInt32(2, this.startedUsers_.getInt(i));
        }
        for (int i2 = 0; i2 < this.registeredJobs_.size(); i2++) {
            output.writeMessage(3, this.registeredJobs_.get(i2));
        }
        for (int i3 = 0; i3 < this.controllers_.size(); i3++) {
            output.writeMessage(4, this.controllers_.get(i3));
        }
        for (int i4 = 0; i4 < this.priorityOverrides_.size(); i4++) {
            output.writeMessage(5, this.priorityOverrides_.get(i4));
        }
        for (int i5 = 0; i5 < this.backingUpUids_.size(); i5++) {
            output.writeInt32(6, this.backingUpUids_.getInt(i5));
        }
        if ((this.bitField0_ & 64) == 64) {
            output.writeMessage(7, getHistory());
        }
        if ((this.bitField0_ & 128) == 128) {
            output.writeMessage(8, getPackageTracker());
        }
        for (int i6 = 0; i6 < this.pendingJobs_.size(); i6++) {
            output.writeMessage(9, this.pendingJobs_.get(i6));
        }
        for (int i7 = 0; i7 < this.activeJobs_.size(); i7++) {
            output.writeMessage(10, this.activeJobs_.get(i7));
        }
        if ((this.bitField0_ & 256) == 256) {
            output.writeBool(11, this.isReadyToRock_);
        }
        if ((this.bitField0_ & 512) == 512) {
            output.writeBool(12, this.reportedActive_);
        }
        if ((this.bitField0_ & 1024) == 1024) {
            output.writeInt32(13, this.maxActiveJobs_);
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeInt32(14, this.currentHeartbeat_);
        }
        for (int i8 = 0; i8 < this.nextHeartbeat_.size(); i8++) {
            output.writeInt32(15, this.nextHeartbeat_.getInt(i8));
        }
        if ((this.bitField0_ & 4) == 4) {
            output.writeInt64(16, this.lastHeartbeatTimeMillis_);
        }
        if ((this.bitField0_ & 8) == 8) {
            output.writeInt64(17, this.nextHeartbeatTimeMillis_);
        }
        if ((this.bitField0_ & 16) == 16) {
            output.writeBool(18, this.inParole_);
        }
        if ((this.bitField0_ & 32) == 32) {
            output.writeBool(19, this.inThermal_);
        }
        if ((this.bitField0_ & 2048) == 2048) {
            output.writeMessage(20, getConcurrencyManager());
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
            size2 = 0 + CodedOutputStream.computeMessageSize(1, getSettings());
        }
        int dataSize = 0;
        for (int i = 0; i < this.startedUsers_.size(); i++) {
            dataSize += CodedOutputStream.computeInt32SizeNoTag(this.startedUsers_.getInt(i));
        }
        int size3 = size2 + dataSize + (getStartedUsersList().size() * 1);
        for (int i2 = 0; i2 < this.registeredJobs_.size(); i2++) {
            size3 += CodedOutputStream.computeMessageSize(3, this.registeredJobs_.get(i2));
        }
        for (int i3 = 0; i3 < this.controllers_.size(); i3++) {
            size3 += CodedOutputStream.computeMessageSize(4, this.controllers_.get(i3));
        }
        for (int i4 = 0; i4 < this.priorityOverrides_.size(); i4++) {
            size3 += CodedOutputStream.computeMessageSize(5, this.priorityOverrides_.get(i4));
        }
        int dataSize2 = 0;
        for (int i5 = 0; i5 < this.backingUpUids_.size(); i5++) {
            dataSize2 += CodedOutputStream.computeInt32SizeNoTag(this.backingUpUids_.getInt(i5));
        }
        int size4 = size3 + dataSize2 + (getBackingUpUidsList().size() * 1);
        if ((this.bitField0_ & 64) == 64) {
            size4 += CodedOutputStream.computeMessageSize(7, getHistory());
        }
        if ((this.bitField0_ & 128) == 128) {
            size4 += CodedOutputStream.computeMessageSize(8, getPackageTracker());
        }
        for (int i6 = 0; i6 < this.pendingJobs_.size(); i6++) {
            size4 += CodedOutputStream.computeMessageSize(9, this.pendingJobs_.get(i6));
        }
        for (int i7 = 0; i7 < this.activeJobs_.size(); i7++) {
            size4 += CodedOutputStream.computeMessageSize(10, this.activeJobs_.get(i7));
        }
        if ((this.bitField0_ & 256) == 256) {
            size4 += CodedOutputStream.computeBoolSize(11, this.isReadyToRock_);
        }
        if ((this.bitField0_ & 512) == 512) {
            size4 += CodedOutputStream.computeBoolSize(12, this.reportedActive_);
        }
        if ((this.bitField0_ & 1024) == 1024) {
            size4 += CodedOutputStream.computeInt32Size(13, this.maxActiveJobs_);
        }
        if ((this.bitField0_ & 2) == 2) {
            size4 += CodedOutputStream.computeInt32Size(14, this.currentHeartbeat_);
        }
        int dataSize3 = 0;
        for (int i8 = 0; i8 < this.nextHeartbeat_.size(); i8++) {
            dataSize3 += CodedOutputStream.computeInt32SizeNoTag(this.nextHeartbeat_.getInt(i8));
        }
        int size5 = size4 + dataSize3 + (getNextHeartbeatList().size() * 1);
        if ((this.bitField0_ & 4) == 4) {
            size5 += CodedOutputStream.computeInt64Size(16, this.lastHeartbeatTimeMillis_);
        }
        if ((this.bitField0_ & 8) == 8) {
            size5 += CodedOutputStream.computeInt64Size(17, this.nextHeartbeatTimeMillis_);
        }
        if ((this.bitField0_ & 16) == 16) {
            size5 += CodedOutputStream.computeBoolSize(18, this.inParole_);
        }
        if ((this.bitField0_ & 32) == 32) {
            size5 += CodedOutputStream.computeBoolSize(19, this.inThermal_);
        }
        if ((this.bitField0_ & 2048) == 2048) {
            size5 += CodedOutputStream.computeMessageSize(20, getConcurrencyManager());
        }
        int size6 = size5 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size6;
        return size6;
    }

    public static JobSchedulerServiceDumpProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (JobSchedulerServiceDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static JobSchedulerServiceDumpProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (JobSchedulerServiceDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static JobSchedulerServiceDumpProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (JobSchedulerServiceDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static JobSchedulerServiceDumpProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (JobSchedulerServiceDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static JobSchedulerServiceDumpProto parseFrom(InputStream input) throws IOException {
        return (JobSchedulerServiceDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static JobSchedulerServiceDumpProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (JobSchedulerServiceDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static JobSchedulerServiceDumpProto parseDelimitedFrom(InputStream input) throws IOException {
        return (JobSchedulerServiceDumpProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static JobSchedulerServiceDumpProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (JobSchedulerServiceDumpProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static JobSchedulerServiceDumpProto parseFrom(CodedInputStream input) throws IOException {
        return (JobSchedulerServiceDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static JobSchedulerServiceDumpProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (JobSchedulerServiceDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(JobSchedulerServiceDumpProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<JobSchedulerServiceDumpProto, Builder> implements JobSchedulerServiceDumpProtoOrBuilder {
        /* synthetic */ Builder(AnonymousClass1 x0) {
            this();
        }

        private Builder() {
            super(JobSchedulerServiceDumpProto.DEFAULT_INSTANCE);
        }

        @Override // com.android.server.job.JobSchedulerServiceDumpProtoOrBuilder
        public boolean hasSettings() {
            return ((JobSchedulerServiceDumpProto) this.instance).hasSettings();
        }

        @Override // com.android.server.job.JobSchedulerServiceDumpProtoOrBuilder
        public ConstantsProto getSettings() {
            return ((JobSchedulerServiceDumpProto) this.instance).getSettings();
        }

        public Builder setSettings(ConstantsProto value) {
            copyOnWrite();
            ((JobSchedulerServiceDumpProto) this.instance).setSettings((JobSchedulerServiceDumpProto) value);
            return this;
        }

        public Builder setSettings(ConstantsProto.Builder builderForValue) {
            copyOnWrite();
            ((JobSchedulerServiceDumpProto) this.instance).setSettings((JobSchedulerServiceDumpProto) builderForValue);
            return this;
        }

        public Builder mergeSettings(ConstantsProto value) {
            copyOnWrite();
            ((JobSchedulerServiceDumpProto) this.instance).mergeSettings(value);
            return this;
        }

        public Builder clearSettings() {
            copyOnWrite();
            ((JobSchedulerServiceDumpProto) this.instance).clearSettings();
            return this;
        }

        @Override // com.android.server.job.JobSchedulerServiceDumpProtoOrBuilder
        public boolean hasCurrentHeartbeat() {
            return ((JobSchedulerServiceDumpProto) this.instance).hasCurrentHeartbeat();
        }

        @Override // com.android.server.job.JobSchedulerServiceDumpProtoOrBuilder
        public int getCurrentHeartbeat() {
            return ((JobSchedulerServiceDumpProto) this.instance).getCurrentHeartbeat();
        }

        public Builder setCurrentHeartbeat(int value) {
            copyOnWrite();
            ((JobSchedulerServiceDumpProto) this.instance).setCurrentHeartbeat(value);
            return this;
        }

        public Builder clearCurrentHeartbeat() {
            copyOnWrite();
            ((JobSchedulerServiceDumpProto) this.instance).clearCurrentHeartbeat();
            return this;
        }

        @Override // com.android.server.job.JobSchedulerServiceDumpProtoOrBuilder
        public List<Integer> getNextHeartbeatList() {
            return Collections.unmodifiableList(((JobSchedulerServiceDumpProto) this.instance).getNextHeartbeatList());
        }

        @Override // com.android.server.job.JobSchedulerServiceDumpProtoOrBuilder
        public int getNextHeartbeatCount() {
            return ((JobSchedulerServiceDumpProto) this.instance).getNextHeartbeatCount();
        }

        @Override // com.android.server.job.JobSchedulerServiceDumpProtoOrBuilder
        public int getNextHeartbeat(int index) {
            return ((JobSchedulerServiceDumpProto) this.instance).getNextHeartbeat(index);
        }

        public Builder setNextHeartbeat(int index, int value) {
            copyOnWrite();
            ((JobSchedulerServiceDumpProto) this.instance).setNextHeartbeat(index, value);
            return this;
        }

        public Builder addNextHeartbeat(int value) {
            copyOnWrite();
            ((JobSchedulerServiceDumpProto) this.instance).addNextHeartbeat(value);
            return this;
        }

        public Builder addAllNextHeartbeat(Iterable<? extends Integer> values) {
            copyOnWrite();
            ((JobSchedulerServiceDumpProto) this.instance).addAllNextHeartbeat(values);
            return this;
        }

        public Builder clearNextHeartbeat() {
            copyOnWrite();
            ((JobSchedulerServiceDumpProto) this.instance).clearNextHeartbeat();
            return this;
        }

        @Override // com.android.server.job.JobSchedulerServiceDumpProtoOrBuilder
        public boolean hasLastHeartbeatTimeMillis() {
            return ((JobSchedulerServiceDumpProto) this.instance).hasLastHeartbeatTimeMillis();
        }

        @Override // com.android.server.job.JobSchedulerServiceDumpProtoOrBuilder
        public long getLastHeartbeatTimeMillis() {
            return ((JobSchedulerServiceDumpProto) this.instance).getLastHeartbeatTimeMillis();
        }

        public Builder setLastHeartbeatTimeMillis(long value) {
            copyOnWrite();
            ((JobSchedulerServiceDumpProto) this.instance).setLastHeartbeatTimeMillis(value);
            return this;
        }

        public Builder clearLastHeartbeatTimeMillis() {
            copyOnWrite();
            ((JobSchedulerServiceDumpProto) this.instance).clearLastHeartbeatTimeMillis();
            return this;
        }

        @Override // com.android.server.job.JobSchedulerServiceDumpProtoOrBuilder
        public boolean hasNextHeartbeatTimeMillis() {
            return ((JobSchedulerServiceDumpProto) this.instance).hasNextHeartbeatTimeMillis();
        }

        @Override // com.android.server.job.JobSchedulerServiceDumpProtoOrBuilder
        public long getNextHeartbeatTimeMillis() {
            return ((JobSchedulerServiceDumpProto) this.instance).getNextHeartbeatTimeMillis();
        }

        public Builder setNextHeartbeatTimeMillis(long value) {
            copyOnWrite();
            ((JobSchedulerServiceDumpProto) this.instance).setNextHeartbeatTimeMillis(value);
            return this;
        }

        public Builder clearNextHeartbeatTimeMillis() {
            copyOnWrite();
            ((JobSchedulerServiceDumpProto) this.instance).clearNextHeartbeatTimeMillis();
            return this;
        }

        @Override // com.android.server.job.JobSchedulerServiceDumpProtoOrBuilder
        public boolean hasInParole() {
            return ((JobSchedulerServiceDumpProto) this.instance).hasInParole();
        }

        @Override // com.android.server.job.JobSchedulerServiceDumpProtoOrBuilder
        public boolean getInParole() {
            return ((JobSchedulerServiceDumpProto) this.instance).getInParole();
        }

        public Builder setInParole(boolean value) {
            copyOnWrite();
            ((JobSchedulerServiceDumpProto) this.instance).setInParole(value);
            return this;
        }

        public Builder clearInParole() {
            copyOnWrite();
            ((JobSchedulerServiceDumpProto) this.instance).clearInParole();
            return this;
        }

        @Override // com.android.server.job.JobSchedulerServiceDumpProtoOrBuilder
        public boolean hasInThermal() {
            return ((JobSchedulerServiceDumpProto) this.instance).hasInThermal();
        }

        @Override // com.android.server.job.JobSchedulerServiceDumpProtoOrBuilder
        public boolean getInThermal() {
            return ((JobSchedulerServiceDumpProto) this.instance).getInThermal();
        }

        public Builder setInThermal(boolean value) {
            copyOnWrite();
            ((JobSchedulerServiceDumpProto) this.instance).setInThermal(value);
            return this;
        }

        public Builder clearInThermal() {
            copyOnWrite();
            ((JobSchedulerServiceDumpProto) this.instance).clearInThermal();
            return this;
        }

        @Override // com.android.server.job.JobSchedulerServiceDumpProtoOrBuilder
        public List<Integer> getStartedUsersList() {
            return Collections.unmodifiableList(((JobSchedulerServiceDumpProto) this.instance).getStartedUsersList());
        }

        @Override // com.android.server.job.JobSchedulerServiceDumpProtoOrBuilder
        public int getStartedUsersCount() {
            return ((JobSchedulerServiceDumpProto) this.instance).getStartedUsersCount();
        }

        @Override // com.android.server.job.JobSchedulerServiceDumpProtoOrBuilder
        public int getStartedUsers(int index) {
            return ((JobSchedulerServiceDumpProto) this.instance).getStartedUsers(index);
        }

        public Builder setStartedUsers(int index, int value) {
            copyOnWrite();
            ((JobSchedulerServiceDumpProto) this.instance).setStartedUsers(index, value);
            return this;
        }

        public Builder addStartedUsers(int value) {
            copyOnWrite();
            ((JobSchedulerServiceDumpProto) this.instance).addStartedUsers(value);
            return this;
        }

        public Builder addAllStartedUsers(Iterable<? extends Integer> values) {
            copyOnWrite();
            ((JobSchedulerServiceDumpProto) this.instance).addAllStartedUsers(values);
            return this;
        }

        public Builder clearStartedUsers() {
            copyOnWrite();
            ((JobSchedulerServiceDumpProto) this.instance).clearStartedUsers();
            return this;
        }

        @Override // com.android.server.job.JobSchedulerServiceDumpProtoOrBuilder
        public List<RegisteredJob> getRegisteredJobsList() {
            return Collections.unmodifiableList(((JobSchedulerServiceDumpProto) this.instance).getRegisteredJobsList());
        }

        @Override // com.android.server.job.JobSchedulerServiceDumpProtoOrBuilder
        public int getRegisteredJobsCount() {
            return ((JobSchedulerServiceDumpProto) this.instance).getRegisteredJobsCount();
        }

        @Override // com.android.server.job.JobSchedulerServiceDumpProtoOrBuilder
        public RegisteredJob getRegisteredJobs(int index) {
            return ((JobSchedulerServiceDumpProto) this.instance).getRegisteredJobs(index);
        }

        public Builder setRegisteredJobs(int index, RegisteredJob value) {
            copyOnWrite();
            ((JobSchedulerServiceDumpProto) this.instance).setRegisteredJobs((JobSchedulerServiceDumpProto) index, (int) value);
            return this;
        }

        public Builder setRegisteredJobs(int index, RegisteredJob.Builder builderForValue) {
            copyOnWrite();
            ((JobSchedulerServiceDumpProto) this.instance).setRegisteredJobs((JobSchedulerServiceDumpProto) index, (int) builderForValue);
            return this;
        }

        public Builder addRegisteredJobs(RegisteredJob value) {
            copyOnWrite();
            ((JobSchedulerServiceDumpProto) this.instance).addRegisteredJobs((JobSchedulerServiceDumpProto) value);
            return this;
        }

        public Builder addRegisteredJobs(int index, RegisteredJob value) {
            copyOnWrite();
            ((JobSchedulerServiceDumpProto) this.instance).addRegisteredJobs((JobSchedulerServiceDumpProto) index, (int) value);
            return this;
        }

        public Builder addRegisteredJobs(RegisteredJob.Builder builderForValue) {
            copyOnWrite();
            ((JobSchedulerServiceDumpProto) this.instance).addRegisteredJobs((JobSchedulerServiceDumpProto) builderForValue);
            return this;
        }

        public Builder addRegisteredJobs(int index, RegisteredJob.Builder builderForValue) {
            copyOnWrite();
            ((JobSchedulerServiceDumpProto) this.instance).addRegisteredJobs((JobSchedulerServiceDumpProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllRegisteredJobs(Iterable<? extends RegisteredJob> values) {
            copyOnWrite();
            ((JobSchedulerServiceDumpProto) this.instance).addAllRegisteredJobs(values);
            return this;
        }

        public Builder clearRegisteredJobs() {
            copyOnWrite();
            ((JobSchedulerServiceDumpProto) this.instance).clearRegisteredJobs();
            return this;
        }

        public Builder removeRegisteredJobs(int index) {
            copyOnWrite();
            ((JobSchedulerServiceDumpProto) this.instance).removeRegisteredJobs(index);
            return this;
        }

        @Override // com.android.server.job.JobSchedulerServiceDumpProtoOrBuilder
        public List<StateControllerProto> getControllersList() {
            return Collections.unmodifiableList(((JobSchedulerServiceDumpProto) this.instance).getControllersList());
        }

        @Override // com.android.server.job.JobSchedulerServiceDumpProtoOrBuilder
        public int getControllersCount() {
            return ((JobSchedulerServiceDumpProto) this.instance).getControllersCount();
        }

        @Override // com.android.server.job.JobSchedulerServiceDumpProtoOrBuilder
        public StateControllerProto getControllers(int index) {
            return ((JobSchedulerServiceDumpProto) this.instance).getControllers(index);
        }

        public Builder setControllers(int index, StateControllerProto value) {
            copyOnWrite();
            ((JobSchedulerServiceDumpProto) this.instance).setControllers((JobSchedulerServiceDumpProto) index, (int) value);
            return this;
        }

        public Builder setControllers(int index, StateControllerProto.Builder builderForValue) {
            copyOnWrite();
            ((JobSchedulerServiceDumpProto) this.instance).setControllers((JobSchedulerServiceDumpProto) index, (int) builderForValue);
            return this;
        }

        public Builder addControllers(StateControllerProto value) {
            copyOnWrite();
            ((JobSchedulerServiceDumpProto) this.instance).addControllers((JobSchedulerServiceDumpProto) value);
            return this;
        }

        public Builder addControllers(int index, StateControllerProto value) {
            copyOnWrite();
            ((JobSchedulerServiceDumpProto) this.instance).addControllers((JobSchedulerServiceDumpProto) index, (int) value);
            return this;
        }

        public Builder addControllers(StateControllerProto.Builder builderForValue) {
            copyOnWrite();
            ((JobSchedulerServiceDumpProto) this.instance).addControllers((JobSchedulerServiceDumpProto) builderForValue);
            return this;
        }

        public Builder addControllers(int index, StateControllerProto.Builder builderForValue) {
            copyOnWrite();
            ((JobSchedulerServiceDumpProto) this.instance).addControllers((JobSchedulerServiceDumpProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllControllers(Iterable<? extends StateControllerProto> values) {
            copyOnWrite();
            ((JobSchedulerServiceDumpProto) this.instance).addAllControllers(values);
            return this;
        }

        public Builder clearControllers() {
            copyOnWrite();
            ((JobSchedulerServiceDumpProto) this.instance).clearControllers();
            return this;
        }

        public Builder removeControllers(int index) {
            copyOnWrite();
            ((JobSchedulerServiceDumpProto) this.instance).removeControllers(index);
            return this;
        }

        @Override // com.android.server.job.JobSchedulerServiceDumpProtoOrBuilder
        public List<PriorityOverride> getPriorityOverridesList() {
            return Collections.unmodifiableList(((JobSchedulerServiceDumpProto) this.instance).getPriorityOverridesList());
        }

        @Override // com.android.server.job.JobSchedulerServiceDumpProtoOrBuilder
        public int getPriorityOverridesCount() {
            return ((JobSchedulerServiceDumpProto) this.instance).getPriorityOverridesCount();
        }

        @Override // com.android.server.job.JobSchedulerServiceDumpProtoOrBuilder
        public PriorityOverride getPriorityOverrides(int index) {
            return ((JobSchedulerServiceDumpProto) this.instance).getPriorityOverrides(index);
        }

        public Builder setPriorityOverrides(int index, PriorityOverride value) {
            copyOnWrite();
            ((JobSchedulerServiceDumpProto) this.instance).setPriorityOverrides((JobSchedulerServiceDumpProto) index, (int) value);
            return this;
        }

        public Builder setPriorityOverrides(int index, PriorityOverride.Builder builderForValue) {
            copyOnWrite();
            ((JobSchedulerServiceDumpProto) this.instance).setPriorityOverrides((JobSchedulerServiceDumpProto) index, (int) builderForValue);
            return this;
        }

        public Builder addPriorityOverrides(PriorityOverride value) {
            copyOnWrite();
            ((JobSchedulerServiceDumpProto) this.instance).addPriorityOverrides((JobSchedulerServiceDumpProto) value);
            return this;
        }

        public Builder addPriorityOverrides(int index, PriorityOverride value) {
            copyOnWrite();
            ((JobSchedulerServiceDumpProto) this.instance).addPriorityOverrides((JobSchedulerServiceDumpProto) index, (int) value);
            return this;
        }

        public Builder addPriorityOverrides(PriorityOverride.Builder builderForValue) {
            copyOnWrite();
            ((JobSchedulerServiceDumpProto) this.instance).addPriorityOverrides((JobSchedulerServiceDumpProto) builderForValue);
            return this;
        }

        public Builder addPriorityOverrides(int index, PriorityOverride.Builder builderForValue) {
            copyOnWrite();
            ((JobSchedulerServiceDumpProto) this.instance).addPriorityOverrides((JobSchedulerServiceDumpProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllPriorityOverrides(Iterable<? extends PriorityOverride> values) {
            copyOnWrite();
            ((JobSchedulerServiceDumpProto) this.instance).addAllPriorityOverrides(values);
            return this;
        }

        public Builder clearPriorityOverrides() {
            copyOnWrite();
            ((JobSchedulerServiceDumpProto) this.instance).clearPriorityOverrides();
            return this;
        }

        public Builder removePriorityOverrides(int index) {
            copyOnWrite();
            ((JobSchedulerServiceDumpProto) this.instance).removePriorityOverrides(index);
            return this;
        }

        @Override // com.android.server.job.JobSchedulerServiceDumpProtoOrBuilder
        public List<Integer> getBackingUpUidsList() {
            return Collections.unmodifiableList(((JobSchedulerServiceDumpProto) this.instance).getBackingUpUidsList());
        }

        @Override // com.android.server.job.JobSchedulerServiceDumpProtoOrBuilder
        public int getBackingUpUidsCount() {
            return ((JobSchedulerServiceDumpProto) this.instance).getBackingUpUidsCount();
        }

        @Override // com.android.server.job.JobSchedulerServiceDumpProtoOrBuilder
        public int getBackingUpUids(int index) {
            return ((JobSchedulerServiceDumpProto) this.instance).getBackingUpUids(index);
        }

        public Builder setBackingUpUids(int index, int value) {
            copyOnWrite();
            ((JobSchedulerServiceDumpProto) this.instance).setBackingUpUids(index, value);
            return this;
        }

        public Builder addBackingUpUids(int value) {
            copyOnWrite();
            ((JobSchedulerServiceDumpProto) this.instance).addBackingUpUids(value);
            return this;
        }

        public Builder addAllBackingUpUids(Iterable<? extends Integer> values) {
            copyOnWrite();
            ((JobSchedulerServiceDumpProto) this.instance).addAllBackingUpUids(values);
            return this;
        }

        public Builder clearBackingUpUids() {
            copyOnWrite();
            ((JobSchedulerServiceDumpProto) this.instance).clearBackingUpUids();
            return this;
        }

        @Override // com.android.server.job.JobSchedulerServiceDumpProtoOrBuilder
        public boolean hasHistory() {
            return ((JobSchedulerServiceDumpProto) this.instance).hasHistory();
        }

        @Override // com.android.server.job.JobSchedulerServiceDumpProtoOrBuilder
        public JobPackageHistoryProto getHistory() {
            return ((JobSchedulerServiceDumpProto) this.instance).getHistory();
        }

        public Builder setHistory(JobPackageHistoryProto value) {
            copyOnWrite();
            ((JobSchedulerServiceDumpProto) this.instance).setHistory((JobSchedulerServiceDumpProto) value);
            return this;
        }

        public Builder setHistory(JobPackageHistoryProto.Builder builderForValue) {
            copyOnWrite();
            ((JobSchedulerServiceDumpProto) this.instance).setHistory((JobSchedulerServiceDumpProto) builderForValue);
            return this;
        }

        public Builder mergeHistory(JobPackageHistoryProto value) {
            copyOnWrite();
            ((JobSchedulerServiceDumpProto) this.instance).mergeHistory(value);
            return this;
        }

        public Builder clearHistory() {
            copyOnWrite();
            ((JobSchedulerServiceDumpProto) this.instance).clearHistory();
            return this;
        }

        @Override // com.android.server.job.JobSchedulerServiceDumpProtoOrBuilder
        public boolean hasPackageTracker() {
            return ((JobSchedulerServiceDumpProto) this.instance).hasPackageTracker();
        }

        @Override // com.android.server.job.JobSchedulerServiceDumpProtoOrBuilder
        public JobPackageTrackerDumpProto getPackageTracker() {
            return ((JobSchedulerServiceDumpProto) this.instance).getPackageTracker();
        }

        public Builder setPackageTracker(JobPackageTrackerDumpProto value) {
            copyOnWrite();
            ((JobSchedulerServiceDumpProto) this.instance).setPackageTracker((JobSchedulerServiceDumpProto) value);
            return this;
        }

        public Builder setPackageTracker(JobPackageTrackerDumpProto.Builder builderForValue) {
            copyOnWrite();
            ((JobSchedulerServiceDumpProto) this.instance).setPackageTracker((JobSchedulerServiceDumpProto) builderForValue);
            return this;
        }

        public Builder mergePackageTracker(JobPackageTrackerDumpProto value) {
            copyOnWrite();
            ((JobSchedulerServiceDumpProto) this.instance).mergePackageTracker(value);
            return this;
        }

        public Builder clearPackageTracker() {
            copyOnWrite();
            ((JobSchedulerServiceDumpProto) this.instance).clearPackageTracker();
            return this;
        }

        @Override // com.android.server.job.JobSchedulerServiceDumpProtoOrBuilder
        public List<PendingJob> getPendingJobsList() {
            return Collections.unmodifiableList(((JobSchedulerServiceDumpProto) this.instance).getPendingJobsList());
        }

        @Override // com.android.server.job.JobSchedulerServiceDumpProtoOrBuilder
        public int getPendingJobsCount() {
            return ((JobSchedulerServiceDumpProto) this.instance).getPendingJobsCount();
        }

        @Override // com.android.server.job.JobSchedulerServiceDumpProtoOrBuilder
        public PendingJob getPendingJobs(int index) {
            return ((JobSchedulerServiceDumpProto) this.instance).getPendingJobs(index);
        }

        public Builder setPendingJobs(int index, PendingJob value) {
            copyOnWrite();
            ((JobSchedulerServiceDumpProto) this.instance).setPendingJobs((JobSchedulerServiceDumpProto) index, (int) value);
            return this;
        }

        public Builder setPendingJobs(int index, PendingJob.Builder builderForValue) {
            copyOnWrite();
            ((JobSchedulerServiceDumpProto) this.instance).setPendingJobs((JobSchedulerServiceDumpProto) index, (int) builderForValue);
            return this;
        }

        public Builder addPendingJobs(PendingJob value) {
            copyOnWrite();
            ((JobSchedulerServiceDumpProto) this.instance).addPendingJobs((JobSchedulerServiceDumpProto) value);
            return this;
        }

        public Builder addPendingJobs(int index, PendingJob value) {
            copyOnWrite();
            ((JobSchedulerServiceDumpProto) this.instance).addPendingJobs((JobSchedulerServiceDumpProto) index, (int) value);
            return this;
        }

        public Builder addPendingJobs(PendingJob.Builder builderForValue) {
            copyOnWrite();
            ((JobSchedulerServiceDumpProto) this.instance).addPendingJobs((JobSchedulerServiceDumpProto) builderForValue);
            return this;
        }

        public Builder addPendingJobs(int index, PendingJob.Builder builderForValue) {
            copyOnWrite();
            ((JobSchedulerServiceDumpProto) this.instance).addPendingJobs((JobSchedulerServiceDumpProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllPendingJobs(Iterable<? extends PendingJob> values) {
            copyOnWrite();
            ((JobSchedulerServiceDumpProto) this.instance).addAllPendingJobs(values);
            return this;
        }

        public Builder clearPendingJobs() {
            copyOnWrite();
            ((JobSchedulerServiceDumpProto) this.instance).clearPendingJobs();
            return this;
        }

        public Builder removePendingJobs(int index) {
            copyOnWrite();
            ((JobSchedulerServiceDumpProto) this.instance).removePendingJobs(index);
            return this;
        }

        @Override // com.android.server.job.JobSchedulerServiceDumpProtoOrBuilder
        public List<ActiveJob> getActiveJobsList() {
            return Collections.unmodifiableList(((JobSchedulerServiceDumpProto) this.instance).getActiveJobsList());
        }

        @Override // com.android.server.job.JobSchedulerServiceDumpProtoOrBuilder
        public int getActiveJobsCount() {
            return ((JobSchedulerServiceDumpProto) this.instance).getActiveJobsCount();
        }

        @Override // com.android.server.job.JobSchedulerServiceDumpProtoOrBuilder
        public ActiveJob getActiveJobs(int index) {
            return ((JobSchedulerServiceDumpProto) this.instance).getActiveJobs(index);
        }

        public Builder setActiveJobs(int index, ActiveJob value) {
            copyOnWrite();
            ((JobSchedulerServiceDumpProto) this.instance).setActiveJobs((JobSchedulerServiceDumpProto) index, (int) value);
            return this;
        }

        public Builder setActiveJobs(int index, ActiveJob.Builder builderForValue) {
            copyOnWrite();
            ((JobSchedulerServiceDumpProto) this.instance).setActiveJobs((JobSchedulerServiceDumpProto) index, (int) builderForValue);
            return this;
        }

        public Builder addActiveJobs(ActiveJob value) {
            copyOnWrite();
            ((JobSchedulerServiceDumpProto) this.instance).addActiveJobs((JobSchedulerServiceDumpProto) value);
            return this;
        }

        public Builder addActiveJobs(int index, ActiveJob value) {
            copyOnWrite();
            ((JobSchedulerServiceDumpProto) this.instance).addActiveJobs((JobSchedulerServiceDumpProto) index, (int) value);
            return this;
        }

        public Builder addActiveJobs(ActiveJob.Builder builderForValue) {
            copyOnWrite();
            ((JobSchedulerServiceDumpProto) this.instance).addActiveJobs((JobSchedulerServiceDumpProto) builderForValue);
            return this;
        }

        public Builder addActiveJobs(int index, ActiveJob.Builder builderForValue) {
            copyOnWrite();
            ((JobSchedulerServiceDumpProto) this.instance).addActiveJobs((JobSchedulerServiceDumpProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllActiveJobs(Iterable<? extends ActiveJob> values) {
            copyOnWrite();
            ((JobSchedulerServiceDumpProto) this.instance).addAllActiveJobs(values);
            return this;
        }

        public Builder clearActiveJobs() {
            copyOnWrite();
            ((JobSchedulerServiceDumpProto) this.instance).clearActiveJobs();
            return this;
        }

        public Builder removeActiveJobs(int index) {
            copyOnWrite();
            ((JobSchedulerServiceDumpProto) this.instance).removeActiveJobs(index);
            return this;
        }

        @Override // com.android.server.job.JobSchedulerServiceDumpProtoOrBuilder
        public boolean hasIsReadyToRock() {
            return ((JobSchedulerServiceDumpProto) this.instance).hasIsReadyToRock();
        }

        @Override // com.android.server.job.JobSchedulerServiceDumpProtoOrBuilder
        public boolean getIsReadyToRock() {
            return ((JobSchedulerServiceDumpProto) this.instance).getIsReadyToRock();
        }

        public Builder setIsReadyToRock(boolean value) {
            copyOnWrite();
            ((JobSchedulerServiceDumpProto) this.instance).setIsReadyToRock(value);
            return this;
        }

        public Builder clearIsReadyToRock() {
            copyOnWrite();
            ((JobSchedulerServiceDumpProto) this.instance).clearIsReadyToRock();
            return this;
        }

        @Override // com.android.server.job.JobSchedulerServiceDumpProtoOrBuilder
        public boolean hasReportedActive() {
            return ((JobSchedulerServiceDumpProto) this.instance).hasReportedActive();
        }

        @Override // com.android.server.job.JobSchedulerServiceDumpProtoOrBuilder
        public boolean getReportedActive() {
            return ((JobSchedulerServiceDumpProto) this.instance).getReportedActive();
        }

        public Builder setReportedActive(boolean value) {
            copyOnWrite();
            ((JobSchedulerServiceDumpProto) this.instance).setReportedActive(value);
            return this;
        }

        public Builder clearReportedActive() {
            copyOnWrite();
            ((JobSchedulerServiceDumpProto) this.instance).clearReportedActive();
            return this;
        }

        @Override // com.android.server.job.JobSchedulerServiceDumpProtoOrBuilder
        public boolean hasMaxActiveJobs() {
            return ((JobSchedulerServiceDumpProto) this.instance).hasMaxActiveJobs();
        }

        @Override // com.android.server.job.JobSchedulerServiceDumpProtoOrBuilder
        public int getMaxActiveJobs() {
            return ((JobSchedulerServiceDumpProto) this.instance).getMaxActiveJobs();
        }

        public Builder setMaxActiveJobs(int value) {
            copyOnWrite();
            ((JobSchedulerServiceDumpProto) this.instance).setMaxActiveJobs(value);
            return this;
        }

        public Builder clearMaxActiveJobs() {
            copyOnWrite();
            ((JobSchedulerServiceDumpProto) this.instance).clearMaxActiveJobs();
            return this;
        }

        @Override // com.android.server.job.JobSchedulerServiceDumpProtoOrBuilder
        public boolean hasConcurrencyManager() {
            return ((JobSchedulerServiceDumpProto) this.instance).hasConcurrencyManager();
        }

        @Override // com.android.server.job.JobSchedulerServiceDumpProtoOrBuilder
        public JobConcurrencyManagerProto getConcurrencyManager() {
            return ((JobSchedulerServiceDumpProto) this.instance).getConcurrencyManager();
        }

        public Builder setConcurrencyManager(JobConcurrencyManagerProto value) {
            copyOnWrite();
            ((JobSchedulerServiceDumpProto) this.instance).setConcurrencyManager((JobSchedulerServiceDumpProto) value);
            return this;
        }

        public Builder setConcurrencyManager(JobConcurrencyManagerProto.Builder builderForValue) {
            copyOnWrite();
            ((JobSchedulerServiceDumpProto) this.instance).setConcurrencyManager((JobSchedulerServiceDumpProto) builderForValue);
            return this;
        }

        public Builder mergeConcurrencyManager(JobConcurrencyManagerProto value) {
            copyOnWrite();
            ((JobSchedulerServiceDumpProto) this.instance).mergeConcurrencyManager(value);
            return this;
        }

        public Builder clearConcurrencyManager() {
            copyOnWrite();
            ((JobSchedulerServiceDumpProto) this.instance).clearConcurrencyManager();
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new JobSchedulerServiceDumpProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                this.nextHeartbeat_.makeImmutable();
                this.startedUsers_.makeImmutable();
                this.registeredJobs_.makeImmutable();
                this.controllers_.makeImmutable();
                this.priorityOverrides_.makeImmutable();
                this.backingUpUids_.makeImmutable();
                this.pendingJobs_.makeImmutable();
                this.activeJobs_.makeImmutable();
                return null;
            case NEW_BUILDER:
                return new Builder(null);
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                JobSchedulerServiceDumpProto other = (JobSchedulerServiceDumpProto) arg1;
                this.settings_ = (ConstantsProto) visitor.visitMessage(this.settings_, other.settings_);
                this.currentHeartbeat_ = visitor.visitInt(hasCurrentHeartbeat(), this.currentHeartbeat_, other.hasCurrentHeartbeat(), other.currentHeartbeat_);
                this.nextHeartbeat_ = visitor.visitIntList(this.nextHeartbeat_, other.nextHeartbeat_);
                this.lastHeartbeatTimeMillis_ = visitor.visitLong(hasLastHeartbeatTimeMillis(), this.lastHeartbeatTimeMillis_, other.hasLastHeartbeatTimeMillis(), other.lastHeartbeatTimeMillis_);
                this.nextHeartbeatTimeMillis_ = visitor.visitLong(hasNextHeartbeatTimeMillis(), this.nextHeartbeatTimeMillis_, other.hasNextHeartbeatTimeMillis(), other.nextHeartbeatTimeMillis_);
                this.inParole_ = visitor.visitBoolean(hasInParole(), this.inParole_, other.hasInParole(), other.inParole_);
                this.inThermal_ = visitor.visitBoolean(hasInThermal(), this.inThermal_, other.hasInThermal(), other.inThermal_);
                this.startedUsers_ = visitor.visitIntList(this.startedUsers_, other.startedUsers_);
                this.registeredJobs_ = visitor.visitList(this.registeredJobs_, other.registeredJobs_);
                this.controllers_ = visitor.visitList(this.controllers_, other.controllers_);
                this.priorityOverrides_ = visitor.visitList(this.priorityOverrides_, other.priorityOverrides_);
                this.backingUpUids_ = visitor.visitIntList(this.backingUpUids_, other.backingUpUids_);
                this.history_ = (JobPackageHistoryProto) visitor.visitMessage(this.history_, other.history_);
                this.packageTracker_ = (JobPackageTrackerDumpProto) visitor.visitMessage(this.packageTracker_, other.packageTracker_);
                this.pendingJobs_ = visitor.visitList(this.pendingJobs_, other.pendingJobs_);
                this.activeJobs_ = visitor.visitList(this.activeJobs_, other.activeJobs_);
                this.isReadyToRock_ = visitor.visitBoolean(hasIsReadyToRock(), this.isReadyToRock_, other.hasIsReadyToRock(), other.isReadyToRock_);
                this.reportedActive_ = visitor.visitBoolean(hasReportedActive(), this.reportedActive_, other.hasReportedActive(), other.reportedActive_);
                this.maxActiveJobs_ = visitor.visitInt(hasMaxActiveJobs(), this.maxActiveJobs_, other.hasMaxActiveJobs(), other.maxActiveJobs_);
                this.concurrencyManager_ = (JobConcurrencyManagerProto) visitor.visitMessage(this.concurrencyManager_, other.concurrencyManager_);
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
                        switch (tag) {
                            case 0:
                                done = true;
                                break;
                            case 10:
                                ConstantsProto.Builder subBuilder = null;
                                if ((this.bitField0_ & 1) == 1) {
                                    subBuilder = (ConstantsProto.Builder) this.settings_.toBuilder();
                                }
                                this.settings_ = (ConstantsProto) input.readMessage(ConstantsProto.parser(), extensionRegistry);
                                if (subBuilder != null) {
                                    subBuilder.mergeFrom((GeneratedMessageLite) this.settings_);
                                    this.settings_ = (ConstantsProto) subBuilder.buildPartial();
                                }
                                this.bitField0_ |= 1;
                                break;
                            case 16:
                                if (!this.startedUsers_.isModifiable()) {
                                    this.startedUsers_ = GeneratedMessageLite.mutableCopy(this.startedUsers_);
                                }
                                this.startedUsers_.addInt(input.readInt32());
                                break;
                            case 18:
                                int limit = input.pushLimit(input.readRawVarint32());
                                if (!this.startedUsers_.isModifiable() && input.getBytesUntilLimit() > 0) {
                                    this.startedUsers_ = GeneratedMessageLite.mutableCopy(this.startedUsers_);
                                }
                                while (input.getBytesUntilLimit() > 0) {
                                    this.startedUsers_.addInt(input.readInt32());
                                }
                                input.popLimit(limit);
                                break;
                            case 26:
                                if (!this.registeredJobs_.isModifiable()) {
                                    this.registeredJobs_ = GeneratedMessageLite.mutableCopy(this.registeredJobs_);
                                }
                                this.registeredJobs_.add((RegisteredJob) input.readMessage(RegisteredJob.parser(), extensionRegistry));
                                break;
                            case 34:
                                if (!this.controllers_.isModifiable()) {
                                    this.controllers_ = GeneratedMessageLite.mutableCopy(this.controllers_);
                                }
                                this.controllers_.add((StateControllerProto) input.readMessage(StateControllerProto.parser(), extensionRegistry));
                                break;
                            case 42:
                                if (!this.priorityOverrides_.isModifiable()) {
                                    this.priorityOverrides_ = GeneratedMessageLite.mutableCopy(this.priorityOverrides_);
                                }
                                this.priorityOverrides_.add((PriorityOverride) input.readMessage(PriorityOverride.parser(), extensionRegistry));
                                break;
                            case 48:
                                if (!this.backingUpUids_.isModifiable()) {
                                    this.backingUpUids_ = GeneratedMessageLite.mutableCopy(this.backingUpUids_);
                                }
                                this.backingUpUids_.addInt(input.readInt32());
                                break;
                            case 50:
                                int limit2 = input.pushLimit(input.readRawVarint32());
                                if (!this.backingUpUids_.isModifiable() && input.getBytesUntilLimit() > 0) {
                                    this.backingUpUids_ = GeneratedMessageLite.mutableCopy(this.backingUpUids_);
                                }
                                while (input.getBytesUntilLimit() > 0) {
                                    this.backingUpUids_.addInt(input.readInt32());
                                }
                                input.popLimit(limit2);
                                break;
                            case 58:
                                JobPackageHistoryProto.Builder subBuilder2 = null;
                                if ((this.bitField0_ & 64) == 64) {
                                    subBuilder2 = (JobPackageHistoryProto.Builder) this.history_.toBuilder();
                                }
                                this.history_ = (JobPackageHistoryProto) input.readMessage(JobPackageHistoryProto.parser(), extensionRegistry);
                                if (subBuilder2 != null) {
                                    subBuilder2.mergeFrom((GeneratedMessageLite) this.history_);
                                    this.history_ = (JobPackageHistoryProto) subBuilder2.buildPartial();
                                }
                                this.bitField0_ |= 64;
                                break;
                            case 66:
                                JobPackageTrackerDumpProto.Builder subBuilder3 = null;
                                if ((this.bitField0_ & 128) == 128) {
                                    subBuilder3 = (JobPackageTrackerDumpProto.Builder) this.packageTracker_.toBuilder();
                                }
                                this.packageTracker_ = (JobPackageTrackerDumpProto) input.readMessage(JobPackageTrackerDumpProto.parser(), extensionRegistry);
                                if (subBuilder3 != null) {
                                    subBuilder3.mergeFrom((GeneratedMessageLite) this.packageTracker_);
                                    this.packageTracker_ = (JobPackageTrackerDumpProto) subBuilder3.buildPartial();
                                }
                                this.bitField0_ |= 128;
                                break;
                            case 74:
                                if (!this.pendingJobs_.isModifiable()) {
                                    this.pendingJobs_ = GeneratedMessageLite.mutableCopy(this.pendingJobs_);
                                }
                                this.pendingJobs_.add((PendingJob) input.readMessage(PendingJob.parser(), extensionRegistry));
                                break;
                            case 82:
                                if (!this.activeJobs_.isModifiable()) {
                                    this.activeJobs_ = GeneratedMessageLite.mutableCopy(this.activeJobs_);
                                }
                                this.activeJobs_.add((ActiveJob) input.readMessage(ActiveJob.parser(), extensionRegistry));
                                break;
                            case 88:
                                this.bitField0_ |= 256;
                                this.isReadyToRock_ = input.readBool();
                                break;
                            case 96:
                                this.bitField0_ |= 512;
                                this.reportedActive_ = input.readBool();
                                break;
                            case 104:
                                this.bitField0_ |= 1024;
                                this.maxActiveJobs_ = input.readInt32();
                                break;
                            case 112:
                                this.bitField0_ |= 2;
                                this.currentHeartbeat_ = input.readInt32();
                                break;
                            case 120:
                                if (!this.nextHeartbeat_.isModifiable()) {
                                    this.nextHeartbeat_ = GeneratedMessageLite.mutableCopy(this.nextHeartbeat_);
                                }
                                this.nextHeartbeat_.addInt(input.readInt32());
                                break;
                            case 122:
                                int limit3 = input.pushLimit(input.readRawVarint32());
                                if (!this.nextHeartbeat_.isModifiable() && input.getBytesUntilLimit() > 0) {
                                    this.nextHeartbeat_ = GeneratedMessageLite.mutableCopy(this.nextHeartbeat_);
                                }
                                while (input.getBytesUntilLimit() > 0) {
                                    this.nextHeartbeat_.addInt(input.readInt32());
                                }
                                input.popLimit(limit3);
                                break;
                            case 128:
                                this.bitField0_ |= 4;
                                this.lastHeartbeatTimeMillis_ = input.readInt64();
                                break;
                            case 136:
                                this.bitField0_ |= 8;
                                this.nextHeartbeatTimeMillis_ = input.readInt64();
                                break;
                            case 144:
                                this.bitField0_ |= 16;
                                this.inParole_ = input.readBool();
                                break;
                            case 152:
                                this.bitField0_ |= 32;
                                this.inThermal_ = input.readBool();
                                break;
                            case 162:
                                JobConcurrencyManagerProto.Builder subBuilder4 = null;
                                if ((this.bitField0_ & 2048) == 2048) {
                                    subBuilder4 = (JobConcurrencyManagerProto.Builder) this.concurrencyManager_.toBuilder();
                                }
                                this.concurrencyManager_ = (JobConcurrencyManagerProto) input.readMessage(JobConcurrencyManagerProto.parser(), extensionRegistry);
                                if (subBuilder4 != null) {
                                    subBuilder4.mergeFrom((GeneratedMessageLite) this.concurrencyManager_);
                                    this.concurrencyManager_ = (JobConcurrencyManagerProto) subBuilder4.buildPartial();
                                }
                                this.bitField0_ |= 2048;
                                break;
                            default:
                                if (parseUnknownField(tag, input)) {
                                    break;
                                } else {
                                    done = true;
                                    break;
                                }
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
                    synchronized (JobSchedulerServiceDumpProto.class) {
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

    public static JobSchedulerServiceDumpProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<JobSchedulerServiceDumpProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
