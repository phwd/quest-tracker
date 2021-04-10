package com.android.server.job;

import android.net.NetworkRequestProto;
import com.android.server.ForceAppStandbyTrackerProto;
import com.android.server.job.JobStatusDumpProto;
import com.android.server.job.JobStatusShortInfoProto;
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

public final class StateControllerProto extends GeneratedMessageLite<StateControllerProto, Builder> implements StateControllerProtoOrBuilder {
    public static final int BACKGROUND_FIELD_NUMBER = 1;
    public static final int BATTERY_FIELD_NUMBER = 2;
    public static final int CONNECTIVITY_FIELD_NUMBER = 3;
    public static final int CONTENT_OBSERVER_FIELD_NUMBER = 4;
    private static final StateControllerProto DEFAULT_INSTANCE = new StateControllerProto();
    public static final int DEVICE_IDLE_FIELD_NUMBER = 5;
    public static final int IDLE_FIELD_NUMBER = 6;
    private static volatile Parser<StateControllerProto> PARSER = null;
    public static final int QUOTA_FIELD_NUMBER = 9;
    public static final int STORAGE_FIELD_NUMBER = 7;
    public static final int TIME_FIELD_NUMBER = 8;
    private int bitField0_;
    private int controllerCase_ = 0;
    private Object controller_;

    public interface BackgroundJobsControllerOrBuilder extends MessageLiteOrBuilder {
        ForceAppStandbyTrackerProto getForceAppStandbyTracker();

        BackgroundJobsController.TrackedJob getTrackedJobs(int i);

        int getTrackedJobsCount();

        List<BackgroundJobsController.TrackedJob> getTrackedJobsList();

        boolean hasForceAppStandbyTracker();
    }

    public interface BatteryControllerOrBuilder extends MessageLiteOrBuilder {
        boolean getIsBatteryNotLow();

        boolean getIsMonitoring();

        boolean getIsOnStablePower();

        int getLastBroadcastSequenceNumber();

        BatteryController.TrackedJob getTrackedJobs(int i);

        int getTrackedJobsCount();

        List<BatteryController.TrackedJob> getTrackedJobsList();

        boolean hasIsBatteryNotLow();

        boolean hasIsMonitoring();

        boolean hasIsOnStablePower();

        boolean hasLastBroadcastSequenceNumber();
    }

    public interface ConnectivityControllerOrBuilder extends MessageLiteOrBuilder {
        boolean getIsConnected();

        ConnectivityController.TrackedJob getTrackedJobs(int i);

        int getTrackedJobsCount();

        List<ConnectivityController.TrackedJob> getTrackedJobsList();

        boolean hasIsConnected();
    }

    public interface ContentObserverControllerOrBuilder extends MessageLiteOrBuilder {
        ContentObserverController.Observer getObservers(int i);

        int getObserversCount();

        List<ContentObserverController.Observer> getObserversList();

        ContentObserverController.TrackedJob getTrackedJobs(int i);

        int getTrackedJobsCount();

        List<ContentObserverController.TrackedJob> getTrackedJobsList();
    }

    public interface DeviceIdleJobsControllerOrBuilder extends MessageLiteOrBuilder {
        boolean getIsDeviceIdleMode();

        DeviceIdleJobsController.TrackedJob getTrackedJobs(int i);

        int getTrackedJobsCount();

        List<DeviceIdleJobsController.TrackedJob> getTrackedJobsList();

        boolean hasIsDeviceIdleMode();
    }

    public interface IdleControllerOrBuilder extends MessageLiteOrBuilder {
        boolean getIsIdle();

        IdleController.TrackedJob getTrackedJobs(int i);

        int getTrackedJobsCount();

        List<IdleController.TrackedJob> getTrackedJobsList();

        boolean hasIsIdle();
    }

    public interface QuotaControllerOrBuilder extends MessageLiteOrBuilder {
        long getElapsedRealtime();

        int getForegroundUids(int i);

        int getForegroundUidsCount();

        List<Integer> getForegroundUidsList();

        boolean getIsCharging();

        boolean getIsInParole();

        QuotaController.PackageStats getPackageStats(int i);

        int getPackageStatsCount();

        List<QuotaController.PackageStats> getPackageStatsList();

        QuotaController.TrackedJob getTrackedJobs(int i);

        int getTrackedJobsCount();

        List<QuotaController.TrackedJob> getTrackedJobsList();

        boolean hasElapsedRealtime();

        boolean hasIsCharging();

        boolean hasIsInParole();
    }

    public interface StorageControllerOrBuilder extends MessageLiteOrBuilder {
        boolean getIsStorageNotLow();

        int getLastBroadcastSequenceNumber();

        StorageController.TrackedJob getTrackedJobs(int i);

        int getTrackedJobsCount();

        List<StorageController.TrackedJob> getTrackedJobsList();

        boolean hasIsStorageNotLow();

        boolean hasLastBroadcastSequenceNumber();
    }

    public interface TimeControllerOrBuilder extends MessageLiteOrBuilder {
        long getNowElapsedRealtime();

        long getTimeUntilNextDeadlineAlarmMs();

        long getTimeUntilNextDelayAlarmMs();

        TimeController.TrackedJob getTrackedJobs(int i);

        int getTrackedJobsCount();

        List<TimeController.TrackedJob> getTrackedJobsList();

        boolean hasNowElapsedRealtime();

        boolean hasTimeUntilNextDeadlineAlarmMs();

        boolean hasTimeUntilNextDelayAlarmMs();
    }

    private StateControllerProto() {
    }

    public static final class BackgroundJobsController extends GeneratedMessageLite<BackgroundJobsController, Builder> implements BackgroundJobsControllerOrBuilder {
        private static final BackgroundJobsController DEFAULT_INSTANCE = new BackgroundJobsController();
        public static final int FORCE_APP_STANDBY_TRACKER_FIELD_NUMBER = 1;
        private static volatile Parser<BackgroundJobsController> PARSER = null;
        public static final int TRACKED_JOBS_FIELD_NUMBER = 2;
        private int bitField0_;
        private ForceAppStandbyTrackerProto forceAppStandbyTracker_;
        private Internal.ProtobufList<TrackedJob> trackedJobs_ = emptyProtobufList();

        public interface TrackedJobOrBuilder extends MessageLiteOrBuilder {
            boolean getAreConstraintsSatisfied();

            boolean getCanRunAnyInBackground();

            JobStatusShortInfoProto getInfo();

            boolean getIsInForeground();

            boolean getIsWhitelisted();

            String getSourcePackageName();

            ByteString getSourcePackageNameBytes();

            int getSourceUid();

            boolean hasAreConstraintsSatisfied();

            boolean hasCanRunAnyInBackground();

            boolean hasInfo();

            boolean hasIsInForeground();

            boolean hasIsWhitelisted();

            boolean hasSourcePackageName();

            boolean hasSourceUid();
        }

        private BackgroundJobsController() {
        }

        public static final class TrackedJob extends GeneratedMessageLite<TrackedJob, Builder> implements TrackedJobOrBuilder {
            public static final int ARE_CONSTRAINTS_SATISFIED_FIELD_NUMBER = 7;
            public static final int CAN_RUN_ANY_IN_BACKGROUND_FIELD_NUMBER = 6;
            private static final TrackedJob DEFAULT_INSTANCE = new TrackedJob();
            public static final int INFO_FIELD_NUMBER = 1;
            public static final int IS_IN_FOREGROUND_FIELD_NUMBER = 4;
            public static final int IS_WHITELISTED_FIELD_NUMBER = 5;
            private static volatile Parser<TrackedJob> PARSER = null;
            public static final int SOURCE_PACKAGE_NAME_FIELD_NUMBER = 3;
            public static final int SOURCE_UID_FIELD_NUMBER = 2;
            private boolean areConstraintsSatisfied_ = false;
            private int bitField0_;
            private boolean canRunAnyInBackground_ = false;
            private JobStatusShortInfoProto info_;
            private boolean isInForeground_ = false;
            private boolean isWhitelisted_ = false;
            private String sourcePackageName_ = "";
            private int sourceUid_ = 0;

            private TrackedJob() {
            }

            @Override // com.android.server.job.StateControllerProto.BackgroundJobsController.TrackedJobOrBuilder
            public boolean hasInfo() {
                return (this.bitField0_ & 1) == 1;
            }

            @Override // com.android.server.job.StateControllerProto.BackgroundJobsController.TrackedJobOrBuilder
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

            @Override // com.android.server.job.StateControllerProto.BackgroundJobsController.TrackedJobOrBuilder
            public boolean hasSourceUid() {
                return (this.bitField0_ & 2) == 2;
            }

            @Override // com.android.server.job.StateControllerProto.BackgroundJobsController.TrackedJobOrBuilder
            public int getSourceUid() {
                return this.sourceUid_;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void setSourceUid(int value) {
                this.bitField0_ |= 2;
                this.sourceUid_ = value;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void clearSourceUid() {
                this.bitField0_ &= -3;
                this.sourceUid_ = 0;
            }

            @Override // com.android.server.job.StateControllerProto.BackgroundJobsController.TrackedJobOrBuilder
            public boolean hasSourcePackageName() {
                return (this.bitField0_ & 4) == 4;
            }

            @Override // com.android.server.job.StateControllerProto.BackgroundJobsController.TrackedJobOrBuilder
            public String getSourcePackageName() {
                return this.sourcePackageName_;
            }

            @Override // com.android.server.job.StateControllerProto.BackgroundJobsController.TrackedJobOrBuilder
            public ByteString getSourcePackageNameBytes() {
                return ByteString.copyFromUtf8(this.sourcePackageName_);
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void setSourcePackageName(String value) {
                if (value != null) {
                    this.bitField0_ |= 4;
                    this.sourcePackageName_ = value;
                    return;
                }
                throw new NullPointerException();
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void clearSourcePackageName() {
                this.bitField0_ &= -5;
                this.sourcePackageName_ = getDefaultInstance().getSourcePackageName();
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void setSourcePackageNameBytes(ByteString value) {
                if (value != null) {
                    this.bitField0_ |= 4;
                    this.sourcePackageName_ = value.toStringUtf8();
                    return;
                }
                throw new NullPointerException();
            }

            @Override // com.android.server.job.StateControllerProto.BackgroundJobsController.TrackedJobOrBuilder
            public boolean hasIsInForeground() {
                return (this.bitField0_ & 8) == 8;
            }

            @Override // com.android.server.job.StateControllerProto.BackgroundJobsController.TrackedJobOrBuilder
            public boolean getIsInForeground() {
                return this.isInForeground_;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void setIsInForeground(boolean value) {
                this.bitField0_ |= 8;
                this.isInForeground_ = value;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void clearIsInForeground() {
                this.bitField0_ &= -9;
                this.isInForeground_ = false;
            }

            @Override // com.android.server.job.StateControllerProto.BackgroundJobsController.TrackedJobOrBuilder
            public boolean hasIsWhitelisted() {
                return (this.bitField0_ & 16) == 16;
            }

            @Override // com.android.server.job.StateControllerProto.BackgroundJobsController.TrackedJobOrBuilder
            public boolean getIsWhitelisted() {
                return this.isWhitelisted_;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void setIsWhitelisted(boolean value) {
                this.bitField0_ |= 16;
                this.isWhitelisted_ = value;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void clearIsWhitelisted() {
                this.bitField0_ &= -17;
                this.isWhitelisted_ = false;
            }

            @Override // com.android.server.job.StateControllerProto.BackgroundJobsController.TrackedJobOrBuilder
            public boolean hasCanRunAnyInBackground() {
                return (this.bitField0_ & 32) == 32;
            }

            @Override // com.android.server.job.StateControllerProto.BackgroundJobsController.TrackedJobOrBuilder
            public boolean getCanRunAnyInBackground() {
                return this.canRunAnyInBackground_;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void setCanRunAnyInBackground(boolean value) {
                this.bitField0_ |= 32;
                this.canRunAnyInBackground_ = value;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void clearCanRunAnyInBackground() {
                this.bitField0_ &= -33;
                this.canRunAnyInBackground_ = false;
            }

            @Override // com.android.server.job.StateControllerProto.BackgroundJobsController.TrackedJobOrBuilder
            public boolean hasAreConstraintsSatisfied() {
                return (this.bitField0_ & 64) == 64;
            }

            @Override // com.android.server.job.StateControllerProto.BackgroundJobsController.TrackedJobOrBuilder
            public boolean getAreConstraintsSatisfied() {
                return this.areConstraintsSatisfied_;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void setAreConstraintsSatisfied(boolean value) {
                this.bitField0_ |= 64;
                this.areConstraintsSatisfied_ = value;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void clearAreConstraintsSatisfied() {
                this.bitField0_ &= -65;
                this.areConstraintsSatisfied_ = false;
            }

            @Override // com.google.protobuf.MessageLite
            public void writeTo(CodedOutputStream output) throws IOException {
                if ((this.bitField0_ & 1) == 1) {
                    output.writeMessage(1, getInfo());
                }
                if ((this.bitField0_ & 2) == 2) {
                    output.writeInt32(2, this.sourceUid_);
                }
                if ((this.bitField0_ & 4) == 4) {
                    output.writeString(3, getSourcePackageName());
                }
                if ((this.bitField0_ & 8) == 8) {
                    output.writeBool(4, this.isInForeground_);
                }
                if ((this.bitField0_ & 16) == 16) {
                    output.writeBool(5, this.isWhitelisted_);
                }
                if ((this.bitField0_ & 32) == 32) {
                    output.writeBool(6, this.canRunAnyInBackground_);
                }
                if ((this.bitField0_ & 64) == 64) {
                    output.writeBool(7, this.areConstraintsSatisfied_);
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
                    size2 += CodedOutputStream.computeInt32Size(2, this.sourceUid_);
                }
                if ((this.bitField0_ & 4) == 4) {
                    size2 += CodedOutputStream.computeStringSize(3, getSourcePackageName());
                }
                if ((this.bitField0_ & 8) == 8) {
                    size2 += CodedOutputStream.computeBoolSize(4, this.isInForeground_);
                }
                if ((this.bitField0_ & 16) == 16) {
                    size2 += CodedOutputStream.computeBoolSize(5, this.isWhitelisted_);
                }
                if ((this.bitField0_ & 32) == 32) {
                    size2 += CodedOutputStream.computeBoolSize(6, this.canRunAnyInBackground_);
                }
                if ((this.bitField0_ & 64) == 64) {
                    size2 += CodedOutputStream.computeBoolSize(7, this.areConstraintsSatisfied_);
                }
                int size3 = size2 + this.unknownFields.getSerializedSize();
                this.memoizedSerializedSize = size3;
                return size3;
            }

            public static TrackedJob parseFrom(ByteString data) throws InvalidProtocolBufferException {
                return (TrackedJob) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
            }

            public static TrackedJob parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return (TrackedJob) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
            }

            public static TrackedJob parseFrom(byte[] data) throws InvalidProtocolBufferException {
                return (TrackedJob) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
            }

            public static TrackedJob parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return (TrackedJob) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
            }

            public static TrackedJob parseFrom(InputStream input) throws IOException {
                return (TrackedJob) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
            }

            public static TrackedJob parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (TrackedJob) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static TrackedJob parseDelimitedFrom(InputStream input) throws IOException {
                return (TrackedJob) parseDelimitedFrom(DEFAULT_INSTANCE, input);
            }

            public static TrackedJob parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (TrackedJob) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static TrackedJob parseFrom(CodedInputStream input) throws IOException {
                return (TrackedJob) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
            }

            public static TrackedJob parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (TrackedJob) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static Builder newBuilder() {
                return (Builder) DEFAULT_INSTANCE.toBuilder();
            }

            public static Builder newBuilder(TrackedJob prototype) {
                return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
            }

            public static final class Builder extends GeneratedMessageLite.Builder<TrackedJob, Builder> implements TrackedJobOrBuilder {
                private Builder() {
                    super(TrackedJob.DEFAULT_INSTANCE);
                }

                @Override // com.android.server.job.StateControllerProto.BackgroundJobsController.TrackedJobOrBuilder
                public boolean hasInfo() {
                    return ((TrackedJob) this.instance).hasInfo();
                }

                @Override // com.android.server.job.StateControllerProto.BackgroundJobsController.TrackedJobOrBuilder
                public JobStatusShortInfoProto getInfo() {
                    return ((TrackedJob) this.instance).getInfo();
                }

                public Builder setInfo(JobStatusShortInfoProto value) {
                    copyOnWrite();
                    ((TrackedJob) this.instance).setInfo((TrackedJob) value);
                    return this;
                }

                public Builder setInfo(JobStatusShortInfoProto.Builder builderForValue) {
                    copyOnWrite();
                    ((TrackedJob) this.instance).setInfo((TrackedJob) builderForValue);
                    return this;
                }

                public Builder mergeInfo(JobStatusShortInfoProto value) {
                    copyOnWrite();
                    ((TrackedJob) this.instance).mergeInfo(value);
                    return this;
                }

                public Builder clearInfo() {
                    copyOnWrite();
                    ((TrackedJob) this.instance).clearInfo();
                    return this;
                }

                @Override // com.android.server.job.StateControllerProto.BackgroundJobsController.TrackedJobOrBuilder
                public boolean hasSourceUid() {
                    return ((TrackedJob) this.instance).hasSourceUid();
                }

                @Override // com.android.server.job.StateControllerProto.BackgroundJobsController.TrackedJobOrBuilder
                public int getSourceUid() {
                    return ((TrackedJob) this.instance).getSourceUid();
                }

                public Builder setSourceUid(int value) {
                    copyOnWrite();
                    ((TrackedJob) this.instance).setSourceUid(value);
                    return this;
                }

                public Builder clearSourceUid() {
                    copyOnWrite();
                    ((TrackedJob) this.instance).clearSourceUid();
                    return this;
                }

                @Override // com.android.server.job.StateControllerProto.BackgroundJobsController.TrackedJobOrBuilder
                public boolean hasSourcePackageName() {
                    return ((TrackedJob) this.instance).hasSourcePackageName();
                }

                @Override // com.android.server.job.StateControllerProto.BackgroundJobsController.TrackedJobOrBuilder
                public String getSourcePackageName() {
                    return ((TrackedJob) this.instance).getSourcePackageName();
                }

                @Override // com.android.server.job.StateControllerProto.BackgroundJobsController.TrackedJobOrBuilder
                public ByteString getSourcePackageNameBytes() {
                    return ((TrackedJob) this.instance).getSourcePackageNameBytes();
                }

                public Builder setSourcePackageName(String value) {
                    copyOnWrite();
                    ((TrackedJob) this.instance).setSourcePackageName(value);
                    return this;
                }

                public Builder clearSourcePackageName() {
                    copyOnWrite();
                    ((TrackedJob) this.instance).clearSourcePackageName();
                    return this;
                }

                public Builder setSourcePackageNameBytes(ByteString value) {
                    copyOnWrite();
                    ((TrackedJob) this.instance).setSourcePackageNameBytes(value);
                    return this;
                }

                @Override // com.android.server.job.StateControllerProto.BackgroundJobsController.TrackedJobOrBuilder
                public boolean hasIsInForeground() {
                    return ((TrackedJob) this.instance).hasIsInForeground();
                }

                @Override // com.android.server.job.StateControllerProto.BackgroundJobsController.TrackedJobOrBuilder
                public boolean getIsInForeground() {
                    return ((TrackedJob) this.instance).getIsInForeground();
                }

                public Builder setIsInForeground(boolean value) {
                    copyOnWrite();
                    ((TrackedJob) this.instance).setIsInForeground(value);
                    return this;
                }

                public Builder clearIsInForeground() {
                    copyOnWrite();
                    ((TrackedJob) this.instance).clearIsInForeground();
                    return this;
                }

                @Override // com.android.server.job.StateControllerProto.BackgroundJobsController.TrackedJobOrBuilder
                public boolean hasIsWhitelisted() {
                    return ((TrackedJob) this.instance).hasIsWhitelisted();
                }

                @Override // com.android.server.job.StateControllerProto.BackgroundJobsController.TrackedJobOrBuilder
                public boolean getIsWhitelisted() {
                    return ((TrackedJob) this.instance).getIsWhitelisted();
                }

                public Builder setIsWhitelisted(boolean value) {
                    copyOnWrite();
                    ((TrackedJob) this.instance).setIsWhitelisted(value);
                    return this;
                }

                public Builder clearIsWhitelisted() {
                    copyOnWrite();
                    ((TrackedJob) this.instance).clearIsWhitelisted();
                    return this;
                }

                @Override // com.android.server.job.StateControllerProto.BackgroundJobsController.TrackedJobOrBuilder
                public boolean hasCanRunAnyInBackground() {
                    return ((TrackedJob) this.instance).hasCanRunAnyInBackground();
                }

                @Override // com.android.server.job.StateControllerProto.BackgroundJobsController.TrackedJobOrBuilder
                public boolean getCanRunAnyInBackground() {
                    return ((TrackedJob) this.instance).getCanRunAnyInBackground();
                }

                public Builder setCanRunAnyInBackground(boolean value) {
                    copyOnWrite();
                    ((TrackedJob) this.instance).setCanRunAnyInBackground(value);
                    return this;
                }

                public Builder clearCanRunAnyInBackground() {
                    copyOnWrite();
                    ((TrackedJob) this.instance).clearCanRunAnyInBackground();
                    return this;
                }

                @Override // com.android.server.job.StateControllerProto.BackgroundJobsController.TrackedJobOrBuilder
                public boolean hasAreConstraintsSatisfied() {
                    return ((TrackedJob) this.instance).hasAreConstraintsSatisfied();
                }

                @Override // com.android.server.job.StateControllerProto.BackgroundJobsController.TrackedJobOrBuilder
                public boolean getAreConstraintsSatisfied() {
                    return ((TrackedJob) this.instance).getAreConstraintsSatisfied();
                }

                public Builder setAreConstraintsSatisfied(boolean value) {
                    copyOnWrite();
                    ((TrackedJob) this.instance).setAreConstraintsSatisfied(value);
                    return this;
                }

                public Builder clearAreConstraintsSatisfied() {
                    copyOnWrite();
                    ((TrackedJob) this.instance).clearAreConstraintsSatisfied();
                    return this;
                }
            }

            /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
            /* access modifiers changed from: protected */
            @Override // com.google.protobuf.GeneratedMessageLite
            public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
                switch (method) {
                    case NEW_MUTABLE_INSTANCE:
                        return new TrackedJob();
                    case IS_INITIALIZED:
                        return DEFAULT_INSTANCE;
                    case MAKE_IMMUTABLE:
                        return null;
                    case NEW_BUILDER:
                        return new Builder();
                    case VISIT:
                        GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                        TrackedJob other = (TrackedJob) arg1;
                        this.info_ = (JobStatusShortInfoProto) visitor.visitMessage(this.info_, other.info_);
                        this.sourceUid_ = visitor.visitInt(hasSourceUid(), this.sourceUid_, other.hasSourceUid(), other.sourceUid_);
                        this.sourcePackageName_ = visitor.visitString(hasSourcePackageName(), this.sourcePackageName_, other.hasSourcePackageName(), other.sourcePackageName_);
                        this.isInForeground_ = visitor.visitBoolean(hasIsInForeground(), this.isInForeground_, other.hasIsInForeground(), other.isInForeground_);
                        this.isWhitelisted_ = visitor.visitBoolean(hasIsWhitelisted(), this.isWhitelisted_, other.hasIsWhitelisted(), other.isWhitelisted_);
                        this.canRunAnyInBackground_ = visitor.visitBoolean(hasCanRunAnyInBackground(), this.canRunAnyInBackground_, other.hasCanRunAnyInBackground(), other.canRunAnyInBackground_);
                        this.areConstraintsSatisfied_ = visitor.visitBoolean(hasAreConstraintsSatisfied(), this.areConstraintsSatisfied_, other.hasAreConstraintsSatisfied(), other.areConstraintsSatisfied_);
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
                                    this.sourceUid_ = input.readInt32();
                                } else if (tag == 26) {
                                    String s = input.readString();
                                    this.bitField0_ |= 4;
                                    this.sourcePackageName_ = s;
                                } else if (tag == 32) {
                                    this.bitField0_ |= 8;
                                    this.isInForeground_ = input.readBool();
                                } else if (tag == 40) {
                                    this.bitField0_ = 16 | this.bitField0_;
                                    this.isWhitelisted_ = input.readBool();
                                } else if (tag == 48) {
                                    this.bitField0_ |= 32;
                                    this.canRunAnyInBackground_ = input.readBool();
                                } else if (tag == 56) {
                                    this.bitField0_ |= 64;
                                    this.areConstraintsSatisfied_ = input.readBool();
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
                            synchronized (TrackedJob.class) {
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

            public static TrackedJob getDefaultInstance() {
                return DEFAULT_INSTANCE;
            }

            public static Parser<TrackedJob> parser() {
                return DEFAULT_INSTANCE.getParserForType();
            }
        }

        @Override // com.android.server.job.StateControllerProto.BackgroundJobsControllerOrBuilder
        public boolean hasForceAppStandbyTracker() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // com.android.server.job.StateControllerProto.BackgroundJobsControllerOrBuilder
        public ForceAppStandbyTrackerProto getForceAppStandbyTracker() {
            ForceAppStandbyTrackerProto forceAppStandbyTrackerProto = this.forceAppStandbyTracker_;
            return forceAppStandbyTrackerProto == null ? ForceAppStandbyTrackerProto.getDefaultInstance() : forceAppStandbyTrackerProto;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setForceAppStandbyTracker(ForceAppStandbyTrackerProto value) {
            if (value != null) {
                this.forceAppStandbyTracker_ = value;
                this.bitField0_ |= 1;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setForceAppStandbyTracker(ForceAppStandbyTrackerProto.Builder builderForValue) {
            this.forceAppStandbyTracker_ = (ForceAppStandbyTrackerProto) builderForValue.build();
            this.bitField0_ |= 1;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void mergeForceAppStandbyTracker(ForceAppStandbyTrackerProto value) {
            ForceAppStandbyTrackerProto forceAppStandbyTrackerProto = this.forceAppStandbyTracker_;
            if (forceAppStandbyTrackerProto == null || forceAppStandbyTrackerProto == ForceAppStandbyTrackerProto.getDefaultInstance()) {
                this.forceAppStandbyTracker_ = value;
            } else {
                this.forceAppStandbyTracker_ = (ForceAppStandbyTrackerProto) ((ForceAppStandbyTrackerProto.Builder) ForceAppStandbyTrackerProto.newBuilder(this.forceAppStandbyTracker_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
            }
            this.bitField0_ |= 1;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearForceAppStandbyTracker() {
            this.forceAppStandbyTracker_ = null;
            this.bitField0_ &= -2;
        }

        @Override // com.android.server.job.StateControllerProto.BackgroundJobsControllerOrBuilder
        public List<TrackedJob> getTrackedJobsList() {
            return this.trackedJobs_;
        }

        public List<? extends TrackedJobOrBuilder> getTrackedJobsOrBuilderList() {
            return this.trackedJobs_;
        }

        @Override // com.android.server.job.StateControllerProto.BackgroundJobsControllerOrBuilder
        public int getTrackedJobsCount() {
            return this.trackedJobs_.size();
        }

        @Override // com.android.server.job.StateControllerProto.BackgroundJobsControllerOrBuilder
        public TrackedJob getTrackedJobs(int index) {
            return this.trackedJobs_.get(index);
        }

        public TrackedJobOrBuilder getTrackedJobsOrBuilder(int index) {
            return this.trackedJobs_.get(index);
        }

        private void ensureTrackedJobsIsMutable() {
            if (!this.trackedJobs_.isModifiable()) {
                this.trackedJobs_ = GeneratedMessageLite.mutableCopy(this.trackedJobs_);
            }
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setTrackedJobs(int index, TrackedJob value) {
            if (value != null) {
                ensureTrackedJobsIsMutable();
                this.trackedJobs_.set(index, value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setTrackedJobs(int index, TrackedJob.Builder builderForValue) {
            ensureTrackedJobsIsMutable();
            this.trackedJobs_.set(index, (TrackedJob) builderForValue.build());
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addTrackedJobs(TrackedJob value) {
            if (value != null) {
                ensureTrackedJobsIsMutable();
                this.trackedJobs_.add(value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addTrackedJobs(int index, TrackedJob value) {
            if (value != null) {
                ensureTrackedJobsIsMutable();
                this.trackedJobs_.add(index, value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addTrackedJobs(TrackedJob.Builder builderForValue) {
            ensureTrackedJobsIsMutable();
            this.trackedJobs_.add((TrackedJob) builderForValue.build());
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addTrackedJobs(int index, TrackedJob.Builder builderForValue) {
            ensureTrackedJobsIsMutable();
            this.trackedJobs_.add(index, (TrackedJob) builderForValue.build());
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addAllTrackedJobs(Iterable<? extends TrackedJob> values) {
            ensureTrackedJobsIsMutable();
            AbstractMessageLite.addAll(values, this.trackedJobs_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearTrackedJobs() {
            this.trackedJobs_ = emptyProtobufList();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void removeTrackedJobs(int index) {
            ensureTrackedJobsIsMutable();
            this.trackedJobs_.remove(index);
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                output.writeMessage(1, getForceAppStandbyTracker());
            }
            for (int i = 0; i < this.trackedJobs_.size(); i++) {
                output.writeMessage(2, this.trackedJobs_.get(i));
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
                size2 = 0 + CodedOutputStream.computeMessageSize(1, getForceAppStandbyTracker());
            }
            for (int i = 0; i < this.trackedJobs_.size(); i++) {
                size2 += CodedOutputStream.computeMessageSize(2, this.trackedJobs_.get(i));
            }
            int size3 = size2 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static BackgroundJobsController parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (BackgroundJobsController) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static BackgroundJobsController parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (BackgroundJobsController) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static BackgroundJobsController parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (BackgroundJobsController) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static BackgroundJobsController parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (BackgroundJobsController) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static BackgroundJobsController parseFrom(InputStream input) throws IOException {
            return (BackgroundJobsController) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static BackgroundJobsController parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (BackgroundJobsController) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static BackgroundJobsController parseDelimitedFrom(InputStream input) throws IOException {
            return (BackgroundJobsController) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static BackgroundJobsController parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (BackgroundJobsController) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static BackgroundJobsController parseFrom(CodedInputStream input) throws IOException {
            return (BackgroundJobsController) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static BackgroundJobsController parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (BackgroundJobsController) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(BackgroundJobsController prototype) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<BackgroundJobsController, Builder> implements BackgroundJobsControllerOrBuilder {
            private Builder() {
                super(BackgroundJobsController.DEFAULT_INSTANCE);
            }

            @Override // com.android.server.job.StateControllerProto.BackgroundJobsControllerOrBuilder
            public boolean hasForceAppStandbyTracker() {
                return ((BackgroundJobsController) this.instance).hasForceAppStandbyTracker();
            }

            @Override // com.android.server.job.StateControllerProto.BackgroundJobsControllerOrBuilder
            public ForceAppStandbyTrackerProto getForceAppStandbyTracker() {
                return ((BackgroundJobsController) this.instance).getForceAppStandbyTracker();
            }

            public Builder setForceAppStandbyTracker(ForceAppStandbyTrackerProto value) {
                copyOnWrite();
                ((BackgroundJobsController) this.instance).setForceAppStandbyTracker((BackgroundJobsController) value);
                return this;
            }

            public Builder setForceAppStandbyTracker(ForceAppStandbyTrackerProto.Builder builderForValue) {
                copyOnWrite();
                ((BackgroundJobsController) this.instance).setForceAppStandbyTracker((BackgroundJobsController) builderForValue);
                return this;
            }

            public Builder mergeForceAppStandbyTracker(ForceAppStandbyTrackerProto value) {
                copyOnWrite();
                ((BackgroundJobsController) this.instance).mergeForceAppStandbyTracker(value);
                return this;
            }

            public Builder clearForceAppStandbyTracker() {
                copyOnWrite();
                ((BackgroundJobsController) this.instance).clearForceAppStandbyTracker();
                return this;
            }

            @Override // com.android.server.job.StateControllerProto.BackgroundJobsControllerOrBuilder
            public List<TrackedJob> getTrackedJobsList() {
                return Collections.unmodifiableList(((BackgroundJobsController) this.instance).getTrackedJobsList());
            }

            @Override // com.android.server.job.StateControllerProto.BackgroundJobsControllerOrBuilder
            public int getTrackedJobsCount() {
                return ((BackgroundJobsController) this.instance).getTrackedJobsCount();
            }

            @Override // com.android.server.job.StateControllerProto.BackgroundJobsControllerOrBuilder
            public TrackedJob getTrackedJobs(int index) {
                return ((BackgroundJobsController) this.instance).getTrackedJobs(index);
            }

            public Builder setTrackedJobs(int index, TrackedJob value) {
                copyOnWrite();
                ((BackgroundJobsController) this.instance).setTrackedJobs((BackgroundJobsController) index, (int) value);
                return this;
            }

            public Builder setTrackedJobs(int index, TrackedJob.Builder builderForValue) {
                copyOnWrite();
                ((BackgroundJobsController) this.instance).setTrackedJobs((BackgroundJobsController) index, (int) builderForValue);
                return this;
            }

            public Builder addTrackedJobs(TrackedJob value) {
                copyOnWrite();
                ((BackgroundJobsController) this.instance).addTrackedJobs((BackgroundJobsController) value);
                return this;
            }

            public Builder addTrackedJobs(int index, TrackedJob value) {
                copyOnWrite();
                ((BackgroundJobsController) this.instance).addTrackedJobs((BackgroundJobsController) index, (int) value);
                return this;
            }

            public Builder addTrackedJobs(TrackedJob.Builder builderForValue) {
                copyOnWrite();
                ((BackgroundJobsController) this.instance).addTrackedJobs((BackgroundJobsController) builderForValue);
                return this;
            }

            public Builder addTrackedJobs(int index, TrackedJob.Builder builderForValue) {
                copyOnWrite();
                ((BackgroundJobsController) this.instance).addTrackedJobs((BackgroundJobsController) index, (int) builderForValue);
                return this;
            }

            public Builder addAllTrackedJobs(Iterable<? extends TrackedJob> values) {
                copyOnWrite();
                ((BackgroundJobsController) this.instance).addAllTrackedJobs(values);
                return this;
            }

            public Builder clearTrackedJobs() {
                copyOnWrite();
                ((BackgroundJobsController) this.instance).clearTrackedJobs();
                return this;
            }

            public Builder removeTrackedJobs(int index) {
                copyOnWrite();
                ((BackgroundJobsController) this.instance).removeTrackedJobs(index);
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new BackgroundJobsController();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    this.trackedJobs_.makeImmutable();
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    BackgroundJobsController other = (BackgroundJobsController) arg1;
                    this.forceAppStandbyTracker_ = (ForceAppStandbyTrackerProto) visitor.visitMessage(this.forceAppStandbyTracker_, other.forceAppStandbyTracker_);
                    this.trackedJobs_ = visitor.visitList(this.trackedJobs_, other.trackedJobs_);
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
                                ForceAppStandbyTrackerProto.Builder subBuilder = null;
                                if ((this.bitField0_ & 1) == 1) {
                                    subBuilder = (ForceAppStandbyTrackerProto.Builder) this.forceAppStandbyTracker_.toBuilder();
                                }
                                this.forceAppStandbyTracker_ = (ForceAppStandbyTrackerProto) input.readMessage(ForceAppStandbyTrackerProto.parser(), extensionRegistry);
                                if (subBuilder != null) {
                                    subBuilder.mergeFrom((GeneratedMessageLite) this.forceAppStandbyTracker_);
                                    this.forceAppStandbyTracker_ = (ForceAppStandbyTrackerProto) subBuilder.buildPartial();
                                }
                                this.bitField0_ |= 1;
                            } else if (tag == 18) {
                                if (!this.trackedJobs_.isModifiable()) {
                                    this.trackedJobs_ = GeneratedMessageLite.mutableCopy(this.trackedJobs_);
                                }
                                this.trackedJobs_.add((TrackedJob) input.readMessage(TrackedJob.parser(), extensionRegistry));
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
                        synchronized (BackgroundJobsController.class) {
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

        public static BackgroundJobsController getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<BackgroundJobsController> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class BatteryController extends GeneratedMessageLite<BatteryController, Builder> implements BatteryControllerOrBuilder {
        private static final BatteryController DEFAULT_INSTANCE = new BatteryController();
        public static final int IS_BATTERY_NOT_LOW_FIELD_NUMBER = 2;
        public static final int IS_MONITORING_FIELD_NUMBER = 3;
        public static final int IS_ON_STABLE_POWER_FIELD_NUMBER = 1;
        public static final int LAST_BROADCAST_SEQUENCE_NUMBER_FIELD_NUMBER = 4;
        private static volatile Parser<BatteryController> PARSER = null;
        public static final int TRACKED_JOBS_FIELD_NUMBER = 5;
        private int bitField0_;
        private boolean isBatteryNotLow_ = false;
        private boolean isMonitoring_ = false;
        private boolean isOnStablePower_ = false;
        private int lastBroadcastSequenceNumber_ = 0;
        private Internal.ProtobufList<TrackedJob> trackedJobs_ = emptyProtobufList();

        public interface TrackedJobOrBuilder extends MessageLiteOrBuilder {
            JobStatusShortInfoProto getInfo();

            int getSourceUid();

            boolean hasInfo();

            boolean hasSourceUid();
        }

        private BatteryController() {
        }

        public static final class TrackedJob extends GeneratedMessageLite<TrackedJob, Builder> implements TrackedJobOrBuilder {
            private static final TrackedJob DEFAULT_INSTANCE = new TrackedJob();
            public static final int INFO_FIELD_NUMBER = 1;
            private static volatile Parser<TrackedJob> PARSER = null;
            public static final int SOURCE_UID_FIELD_NUMBER = 2;
            private int bitField0_;
            private JobStatusShortInfoProto info_;
            private int sourceUid_ = 0;

            private TrackedJob() {
            }

            @Override // com.android.server.job.StateControllerProto.BatteryController.TrackedJobOrBuilder
            public boolean hasInfo() {
                return (this.bitField0_ & 1) == 1;
            }

            @Override // com.android.server.job.StateControllerProto.BatteryController.TrackedJobOrBuilder
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

            @Override // com.android.server.job.StateControllerProto.BatteryController.TrackedJobOrBuilder
            public boolean hasSourceUid() {
                return (this.bitField0_ & 2) == 2;
            }

            @Override // com.android.server.job.StateControllerProto.BatteryController.TrackedJobOrBuilder
            public int getSourceUid() {
                return this.sourceUid_;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void setSourceUid(int value) {
                this.bitField0_ |= 2;
                this.sourceUid_ = value;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void clearSourceUid() {
                this.bitField0_ &= -3;
                this.sourceUid_ = 0;
            }

            @Override // com.google.protobuf.MessageLite
            public void writeTo(CodedOutputStream output) throws IOException {
                if ((this.bitField0_ & 1) == 1) {
                    output.writeMessage(1, getInfo());
                }
                if ((this.bitField0_ & 2) == 2) {
                    output.writeInt32(2, this.sourceUid_);
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
                    size2 += CodedOutputStream.computeInt32Size(2, this.sourceUid_);
                }
                int size3 = size2 + this.unknownFields.getSerializedSize();
                this.memoizedSerializedSize = size3;
                return size3;
            }

            public static TrackedJob parseFrom(ByteString data) throws InvalidProtocolBufferException {
                return (TrackedJob) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
            }

            public static TrackedJob parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return (TrackedJob) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
            }

            public static TrackedJob parseFrom(byte[] data) throws InvalidProtocolBufferException {
                return (TrackedJob) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
            }

            public static TrackedJob parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return (TrackedJob) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
            }

            public static TrackedJob parseFrom(InputStream input) throws IOException {
                return (TrackedJob) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
            }

            public static TrackedJob parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (TrackedJob) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static TrackedJob parseDelimitedFrom(InputStream input) throws IOException {
                return (TrackedJob) parseDelimitedFrom(DEFAULT_INSTANCE, input);
            }

            public static TrackedJob parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (TrackedJob) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static TrackedJob parseFrom(CodedInputStream input) throws IOException {
                return (TrackedJob) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
            }

            public static TrackedJob parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (TrackedJob) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static Builder newBuilder() {
                return (Builder) DEFAULT_INSTANCE.toBuilder();
            }

            public static Builder newBuilder(TrackedJob prototype) {
                return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
            }

            public static final class Builder extends GeneratedMessageLite.Builder<TrackedJob, Builder> implements TrackedJobOrBuilder {
                private Builder() {
                    super(TrackedJob.DEFAULT_INSTANCE);
                }

                @Override // com.android.server.job.StateControllerProto.BatteryController.TrackedJobOrBuilder
                public boolean hasInfo() {
                    return ((TrackedJob) this.instance).hasInfo();
                }

                @Override // com.android.server.job.StateControllerProto.BatteryController.TrackedJobOrBuilder
                public JobStatusShortInfoProto getInfo() {
                    return ((TrackedJob) this.instance).getInfo();
                }

                public Builder setInfo(JobStatusShortInfoProto value) {
                    copyOnWrite();
                    ((TrackedJob) this.instance).setInfo((TrackedJob) value);
                    return this;
                }

                public Builder setInfo(JobStatusShortInfoProto.Builder builderForValue) {
                    copyOnWrite();
                    ((TrackedJob) this.instance).setInfo((TrackedJob) builderForValue);
                    return this;
                }

                public Builder mergeInfo(JobStatusShortInfoProto value) {
                    copyOnWrite();
                    ((TrackedJob) this.instance).mergeInfo(value);
                    return this;
                }

                public Builder clearInfo() {
                    copyOnWrite();
                    ((TrackedJob) this.instance).clearInfo();
                    return this;
                }

                @Override // com.android.server.job.StateControllerProto.BatteryController.TrackedJobOrBuilder
                public boolean hasSourceUid() {
                    return ((TrackedJob) this.instance).hasSourceUid();
                }

                @Override // com.android.server.job.StateControllerProto.BatteryController.TrackedJobOrBuilder
                public int getSourceUid() {
                    return ((TrackedJob) this.instance).getSourceUid();
                }

                public Builder setSourceUid(int value) {
                    copyOnWrite();
                    ((TrackedJob) this.instance).setSourceUid(value);
                    return this;
                }

                public Builder clearSourceUid() {
                    copyOnWrite();
                    ((TrackedJob) this.instance).clearSourceUid();
                    return this;
                }
            }

            /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
            /* access modifiers changed from: protected */
            @Override // com.google.protobuf.GeneratedMessageLite
            public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
                switch (method) {
                    case NEW_MUTABLE_INSTANCE:
                        return new TrackedJob();
                    case IS_INITIALIZED:
                        return DEFAULT_INSTANCE;
                    case MAKE_IMMUTABLE:
                        return null;
                    case NEW_BUILDER:
                        return new Builder();
                    case VISIT:
                        GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                        TrackedJob other = (TrackedJob) arg1;
                        this.info_ = (JobStatusShortInfoProto) visitor.visitMessage(this.info_, other.info_);
                        this.sourceUid_ = visitor.visitInt(hasSourceUid(), this.sourceUid_, other.hasSourceUid(), other.sourceUid_);
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
                                    this.sourceUid_ = input.readInt32();
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
                            synchronized (TrackedJob.class) {
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

            public static TrackedJob getDefaultInstance() {
                return DEFAULT_INSTANCE;
            }

            public static Parser<TrackedJob> parser() {
                return DEFAULT_INSTANCE.getParserForType();
            }
        }

        @Override // com.android.server.job.StateControllerProto.BatteryControllerOrBuilder
        public boolean hasIsOnStablePower() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // com.android.server.job.StateControllerProto.BatteryControllerOrBuilder
        public boolean getIsOnStablePower() {
            return this.isOnStablePower_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setIsOnStablePower(boolean value) {
            this.bitField0_ |= 1;
            this.isOnStablePower_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearIsOnStablePower() {
            this.bitField0_ &= -2;
            this.isOnStablePower_ = false;
        }

        @Override // com.android.server.job.StateControllerProto.BatteryControllerOrBuilder
        public boolean hasIsBatteryNotLow() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // com.android.server.job.StateControllerProto.BatteryControllerOrBuilder
        public boolean getIsBatteryNotLow() {
            return this.isBatteryNotLow_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setIsBatteryNotLow(boolean value) {
            this.bitField0_ |= 2;
            this.isBatteryNotLow_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearIsBatteryNotLow() {
            this.bitField0_ &= -3;
            this.isBatteryNotLow_ = false;
        }

        @Override // com.android.server.job.StateControllerProto.BatteryControllerOrBuilder
        public boolean hasIsMonitoring() {
            return (this.bitField0_ & 4) == 4;
        }

        @Override // com.android.server.job.StateControllerProto.BatteryControllerOrBuilder
        public boolean getIsMonitoring() {
            return this.isMonitoring_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setIsMonitoring(boolean value) {
            this.bitField0_ |= 4;
            this.isMonitoring_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearIsMonitoring() {
            this.bitField0_ &= -5;
            this.isMonitoring_ = false;
        }

        @Override // com.android.server.job.StateControllerProto.BatteryControllerOrBuilder
        public boolean hasLastBroadcastSequenceNumber() {
            return (this.bitField0_ & 8) == 8;
        }

        @Override // com.android.server.job.StateControllerProto.BatteryControllerOrBuilder
        public int getLastBroadcastSequenceNumber() {
            return this.lastBroadcastSequenceNumber_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setLastBroadcastSequenceNumber(int value) {
            this.bitField0_ |= 8;
            this.lastBroadcastSequenceNumber_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearLastBroadcastSequenceNumber() {
            this.bitField0_ &= -9;
            this.lastBroadcastSequenceNumber_ = 0;
        }

        @Override // com.android.server.job.StateControllerProto.BatteryControllerOrBuilder
        public List<TrackedJob> getTrackedJobsList() {
            return this.trackedJobs_;
        }

        public List<? extends TrackedJobOrBuilder> getTrackedJobsOrBuilderList() {
            return this.trackedJobs_;
        }

        @Override // com.android.server.job.StateControllerProto.BatteryControllerOrBuilder
        public int getTrackedJobsCount() {
            return this.trackedJobs_.size();
        }

        @Override // com.android.server.job.StateControllerProto.BatteryControllerOrBuilder
        public TrackedJob getTrackedJobs(int index) {
            return this.trackedJobs_.get(index);
        }

        public TrackedJobOrBuilder getTrackedJobsOrBuilder(int index) {
            return this.trackedJobs_.get(index);
        }

        private void ensureTrackedJobsIsMutable() {
            if (!this.trackedJobs_.isModifiable()) {
                this.trackedJobs_ = GeneratedMessageLite.mutableCopy(this.trackedJobs_);
            }
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setTrackedJobs(int index, TrackedJob value) {
            if (value != null) {
                ensureTrackedJobsIsMutable();
                this.trackedJobs_.set(index, value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setTrackedJobs(int index, TrackedJob.Builder builderForValue) {
            ensureTrackedJobsIsMutable();
            this.trackedJobs_.set(index, (TrackedJob) builderForValue.build());
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addTrackedJobs(TrackedJob value) {
            if (value != null) {
                ensureTrackedJobsIsMutable();
                this.trackedJobs_.add(value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addTrackedJobs(int index, TrackedJob value) {
            if (value != null) {
                ensureTrackedJobsIsMutable();
                this.trackedJobs_.add(index, value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addTrackedJobs(TrackedJob.Builder builderForValue) {
            ensureTrackedJobsIsMutable();
            this.trackedJobs_.add((TrackedJob) builderForValue.build());
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addTrackedJobs(int index, TrackedJob.Builder builderForValue) {
            ensureTrackedJobsIsMutable();
            this.trackedJobs_.add(index, (TrackedJob) builderForValue.build());
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addAllTrackedJobs(Iterable<? extends TrackedJob> values) {
            ensureTrackedJobsIsMutable();
            AbstractMessageLite.addAll(values, this.trackedJobs_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearTrackedJobs() {
            this.trackedJobs_ = emptyProtobufList();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void removeTrackedJobs(int index) {
            ensureTrackedJobsIsMutable();
            this.trackedJobs_.remove(index);
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                output.writeBool(1, this.isOnStablePower_);
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeBool(2, this.isBatteryNotLow_);
            }
            if ((this.bitField0_ & 4) == 4) {
                output.writeBool(3, this.isMonitoring_);
            }
            if ((this.bitField0_ & 8) == 8) {
                output.writeInt32(4, this.lastBroadcastSequenceNumber_);
            }
            for (int i = 0; i < this.trackedJobs_.size(); i++) {
                output.writeMessage(5, this.trackedJobs_.get(i));
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
                size2 = 0 + CodedOutputStream.computeBoolSize(1, this.isOnStablePower_);
            }
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeBoolSize(2, this.isBatteryNotLow_);
            }
            if ((this.bitField0_ & 4) == 4) {
                size2 += CodedOutputStream.computeBoolSize(3, this.isMonitoring_);
            }
            if ((this.bitField0_ & 8) == 8) {
                size2 += CodedOutputStream.computeInt32Size(4, this.lastBroadcastSequenceNumber_);
            }
            for (int i = 0; i < this.trackedJobs_.size(); i++) {
                size2 += CodedOutputStream.computeMessageSize(5, this.trackedJobs_.get(i));
            }
            int size3 = size2 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static BatteryController parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (BatteryController) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static BatteryController parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (BatteryController) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static BatteryController parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (BatteryController) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static BatteryController parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (BatteryController) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static BatteryController parseFrom(InputStream input) throws IOException {
            return (BatteryController) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static BatteryController parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (BatteryController) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static BatteryController parseDelimitedFrom(InputStream input) throws IOException {
            return (BatteryController) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static BatteryController parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (BatteryController) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static BatteryController parseFrom(CodedInputStream input) throws IOException {
            return (BatteryController) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static BatteryController parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (BatteryController) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(BatteryController prototype) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<BatteryController, Builder> implements BatteryControllerOrBuilder {
            private Builder() {
                super(BatteryController.DEFAULT_INSTANCE);
            }

            @Override // com.android.server.job.StateControllerProto.BatteryControllerOrBuilder
            public boolean hasIsOnStablePower() {
                return ((BatteryController) this.instance).hasIsOnStablePower();
            }

            @Override // com.android.server.job.StateControllerProto.BatteryControllerOrBuilder
            public boolean getIsOnStablePower() {
                return ((BatteryController) this.instance).getIsOnStablePower();
            }

            public Builder setIsOnStablePower(boolean value) {
                copyOnWrite();
                ((BatteryController) this.instance).setIsOnStablePower(value);
                return this;
            }

            public Builder clearIsOnStablePower() {
                copyOnWrite();
                ((BatteryController) this.instance).clearIsOnStablePower();
                return this;
            }

            @Override // com.android.server.job.StateControllerProto.BatteryControllerOrBuilder
            public boolean hasIsBatteryNotLow() {
                return ((BatteryController) this.instance).hasIsBatteryNotLow();
            }

            @Override // com.android.server.job.StateControllerProto.BatteryControllerOrBuilder
            public boolean getIsBatteryNotLow() {
                return ((BatteryController) this.instance).getIsBatteryNotLow();
            }

            public Builder setIsBatteryNotLow(boolean value) {
                copyOnWrite();
                ((BatteryController) this.instance).setIsBatteryNotLow(value);
                return this;
            }

            public Builder clearIsBatteryNotLow() {
                copyOnWrite();
                ((BatteryController) this.instance).clearIsBatteryNotLow();
                return this;
            }

            @Override // com.android.server.job.StateControllerProto.BatteryControllerOrBuilder
            public boolean hasIsMonitoring() {
                return ((BatteryController) this.instance).hasIsMonitoring();
            }

            @Override // com.android.server.job.StateControllerProto.BatteryControllerOrBuilder
            public boolean getIsMonitoring() {
                return ((BatteryController) this.instance).getIsMonitoring();
            }

            public Builder setIsMonitoring(boolean value) {
                copyOnWrite();
                ((BatteryController) this.instance).setIsMonitoring(value);
                return this;
            }

            public Builder clearIsMonitoring() {
                copyOnWrite();
                ((BatteryController) this.instance).clearIsMonitoring();
                return this;
            }

            @Override // com.android.server.job.StateControllerProto.BatteryControllerOrBuilder
            public boolean hasLastBroadcastSequenceNumber() {
                return ((BatteryController) this.instance).hasLastBroadcastSequenceNumber();
            }

            @Override // com.android.server.job.StateControllerProto.BatteryControllerOrBuilder
            public int getLastBroadcastSequenceNumber() {
                return ((BatteryController) this.instance).getLastBroadcastSequenceNumber();
            }

            public Builder setLastBroadcastSequenceNumber(int value) {
                copyOnWrite();
                ((BatteryController) this.instance).setLastBroadcastSequenceNumber(value);
                return this;
            }

            public Builder clearLastBroadcastSequenceNumber() {
                copyOnWrite();
                ((BatteryController) this.instance).clearLastBroadcastSequenceNumber();
                return this;
            }

            @Override // com.android.server.job.StateControllerProto.BatteryControllerOrBuilder
            public List<TrackedJob> getTrackedJobsList() {
                return Collections.unmodifiableList(((BatteryController) this.instance).getTrackedJobsList());
            }

            @Override // com.android.server.job.StateControllerProto.BatteryControllerOrBuilder
            public int getTrackedJobsCount() {
                return ((BatteryController) this.instance).getTrackedJobsCount();
            }

            @Override // com.android.server.job.StateControllerProto.BatteryControllerOrBuilder
            public TrackedJob getTrackedJobs(int index) {
                return ((BatteryController) this.instance).getTrackedJobs(index);
            }

            public Builder setTrackedJobs(int index, TrackedJob value) {
                copyOnWrite();
                ((BatteryController) this.instance).setTrackedJobs((BatteryController) index, (int) value);
                return this;
            }

            public Builder setTrackedJobs(int index, TrackedJob.Builder builderForValue) {
                copyOnWrite();
                ((BatteryController) this.instance).setTrackedJobs((BatteryController) index, (int) builderForValue);
                return this;
            }

            public Builder addTrackedJobs(TrackedJob value) {
                copyOnWrite();
                ((BatteryController) this.instance).addTrackedJobs((BatteryController) value);
                return this;
            }

            public Builder addTrackedJobs(int index, TrackedJob value) {
                copyOnWrite();
                ((BatteryController) this.instance).addTrackedJobs((BatteryController) index, (int) value);
                return this;
            }

            public Builder addTrackedJobs(TrackedJob.Builder builderForValue) {
                copyOnWrite();
                ((BatteryController) this.instance).addTrackedJobs((BatteryController) builderForValue);
                return this;
            }

            public Builder addTrackedJobs(int index, TrackedJob.Builder builderForValue) {
                copyOnWrite();
                ((BatteryController) this.instance).addTrackedJobs((BatteryController) index, (int) builderForValue);
                return this;
            }

            public Builder addAllTrackedJobs(Iterable<? extends TrackedJob> values) {
                copyOnWrite();
                ((BatteryController) this.instance).addAllTrackedJobs(values);
                return this;
            }

            public Builder clearTrackedJobs() {
                copyOnWrite();
                ((BatteryController) this.instance).clearTrackedJobs();
                return this;
            }

            public Builder removeTrackedJobs(int index) {
                copyOnWrite();
                ((BatteryController) this.instance).removeTrackedJobs(index);
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new BatteryController();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    this.trackedJobs_.makeImmutable();
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    BatteryController other = (BatteryController) arg1;
                    this.isOnStablePower_ = visitor.visitBoolean(hasIsOnStablePower(), this.isOnStablePower_, other.hasIsOnStablePower(), other.isOnStablePower_);
                    this.isBatteryNotLow_ = visitor.visitBoolean(hasIsBatteryNotLow(), this.isBatteryNotLow_, other.hasIsBatteryNotLow(), other.isBatteryNotLow_);
                    this.isMonitoring_ = visitor.visitBoolean(hasIsMonitoring(), this.isMonitoring_, other.hasIsMonitoring(), other.isMonitoring_);
                    this.lastBroadcastSequenceNumber_ = visitor.visitInt(hasLastBroadcastSequenceNumber(), this.lastBroadcastSequenceNumber_, other.hasLastBroadcastSequenceNumber(), other.lastBroadcastSequenceNumber_);
                    this.trackedJobs_ = visitor.visitList(this.trackedJobs_, other.trackedJobs_);
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
                                this.isOnStablePower_ = input.readBool();
                            } else if (tag == 16) {
                                this.bitField0_ |= 2;
                                this.isBatteryNotLow_ = input.readBool();
                            } else if (tag == 24) {
                                this.bitField0_ |= 4;
                                this.isMonitoring_ = input.readBool();
                            } else if (tag == 32) {
                                this.bitField0_ = 8 | this.bitField0_;
                                this.lastBroadcastSequenceNumber_ = input.readInt32();
                            } else if (tag == 42) {
                                if (!this.trackedJobs_.isModifiable()) {
                                    this.trackedJobs_ = GeneratedMessageLite.mutableCopy(this.trackedJobs_);
                                }
                                this.trackedJobs_.add((TrackedJob) input.readMessage(TrackedJob.parser(), extensionRegistry));
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
                        synchronized (BatteryController.class) {
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

        public static BatteryController getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<BatteryController> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class ConnectivityController extends GeneratedMessageLite<ConnectivityController, Builder> implements ConnectivityControllerOrBuilder {
        private static final ConnectivityController DEFAULT_INSTANCE = new ConnectivityController();
        public static final int IS_CONNECTED_FIELD_NUMBER = 1;
        private static volatile Parser<ConnectivityController> PARSER = null;
        public static final int TRACKED_JOBS_FIELD_NUMBER = 2;
        private int bitField0_;
        private boolean isConnected_ = false;
        private Internal.ProtobufList<TrackedJob> trackedJobs_ = emptyProtobufList();

        public interface TrackedJobOrBuilder extends MessageLiteOrBuilder {
            JobStatusShortInfoProto getInfo();

            NetworkRequestProto getRequiredNetwork();

            int getSourceUid();

            boolean hasInfo();

            boolean hasRequiredNetwork();

            boolean hasSourceUid();
        }

        private ConnectivityController() {
        }

        public static final class TrackedJob extends GeneratedMessageLite<TrackedJob, Builder> implements TrackedJobOrBuilder {
            private static final TrackedJob DEFAULT_INSTANCE = new TrackedJob();
            public static final int INFO_FIELD_NUMBER = 1;
            private static volatile Parser<TrackedJob> PARSER = null;
            public static final int REQUIRED_NETWORK_FIELD_NUMBER = 3;
            public static final int SOURCE_UID_FIELD_NUMBER = 2;
            private int bitField0_;
            private JobStatusShortInfoProto info_;
            private NetworkRequestProto requiredNetwork_;
            private int sourceUid_ = 0;

            private TrackedJob() {
            }

            @Override // com.android.server.job.StateControllerProto.ConnectivityController.TrackedJobOrBuilder
            public boolean hasInfo() {
                return (this.bitField0_ & 1) == 1;
            }

            @Override // com.android.server.job.StateControllerProto.ConnectivityController.TrackedJobOrBuilder
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

            @Override // com.android.server.job.StateControllerProto.ConnectivityController.TrackedJobOrBuilder
            public boolean hasSourceUid() {
                return (this.bitField0_ & 2) == 2;
            }

            @Override // com.android.server.job.StateControllerProto.ConnectivityController.TrackedJobOrBuilder
            public int getSourceUid() {
                return this.sourceUid_;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void setSourceUid(int value) {
                this.bitField0_ |= 2;
                this.sourceUid_ = value;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void clearSourceUid() {
                this.bitField0_ &= -3;
                this.sourceUid_ = 0;
            }

            @Override // com.android.server.job.StateControllerProto.ConnectivityController.TrackedJobOrBuilder
            public boolean hasRequiredNetwork() {
                return (this.bitField0_ & 4) == 4;
            }

            @Override // com.android.server.job.StateControllerProto.ConnectivityController.TrackedJobOrBuilder
            public NetworkRequestProto getRequiredNetwork() {
                NetworkRequestProto networkRequestProto = this.requiredNetwork_;
                return networkRequestProto == null ? NetworkRequestProto.getDefaultInstance() : networkRequestProto;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void setRequiredNetwork(NetworkRequestProto value) {
                if (value != null) {
                    this.requiredNetwork_ = value;
                    this.bitField0_ |= 4;
                    return;
                }
                throw new NullPointerException();
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void setRequiredNetwork(NetworkRequestProto.Builder builderForValue) {
                this.requiredNetwork_ = (NetworkRequestProto) builderForValue.build();
                this.bitField0_ |= 4;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void mergeRequiredNetwork(NetworkRequestProto value) {
                NetworkRequestProto networkRequestProto = this.requiredNetwork_;
                if (networkRequestProto == null || networkRequestProto == NetworkRequestProto.getDefaultInstance()) {
                    this.requiredNetwork_ = value;
                } else {
                    this.requiredNetwork_ = (NetworkRequestProto) ((NetworkRequestProto.Builder) NetworkRequestProto.newBuilder(this.requiredNetwork_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
                }
                this.bitField0_ |= 4;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void clearRequiredNetwork() {
                this.requiredNetwork_ = null;
                this.bitField0_ &= -5;
            }

            @Override // com.google.protobuf.MessageLite
            public void writeTo(CodedOutputStream output) throws IOException {
                if ((this.bitField0_ & 1) == 1) {
                    output.writeMessage(1, getInfo());
                }
                if ((this.bitField0_ & 2) == 2) {
                    output.writeInt32(2, this.sourceUid_);
                }
                if ((this.bitField0_ & 4) == 4) {
                    output.writeMessage(3, getRequiredNetwork());
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
                    size2 += CodedOutputStream.computeInt32Size(2, this.sourceUid_);
                }
                if ((this.bitField0_ & 4) == 4) {
                    size2 += CodedOutputStream.computeMessageSize(3, getRequiredNetwork());
                }
                int size3 = size2 + this.unknownFields.getSerializedSize();
                this.memoizedSerializedSize = size3;
                return size3;
            }

            public static TrackedJob parseFrom(ByteString data) throws InvalidProtocolBufferException {
                return (TrackedJob) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
            }

            public static TrackedJob parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return (TrackedJob) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
            }

            public static TrackedJob parseFrom(byte[] data) throws InvalidProtocolBufferException {
                return (TrackedJob) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
            }

            public static TrackedJob parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return (TrackedJob) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
            }

            public static TrackedJob parseFrom(InputStream input) throws IOException {
                return (TrackedJob) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
            }

            public static TrackedJob parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (TrackedJob) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static TrackedJob parseDelimitedFrom(InputStream input) throws IOException {
                return (TrackedJob) parseDelimitedFrom(DEFAULT_INSTANCE, input);
            }

            public static TrackedJob parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (TrackedJob) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static TrackedJob parseFrom(CodedInputStream input) throws IOException {
                return (TrackedJob) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
            }

            public static TrackedJob parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (TrackedJob) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static Builder newBuilder() {
                return (Builder) DEFAULT_INSTANCE.toBuilder();
            }

            public static Builder newBuilder(TrackedJob prototype) {
                return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
            }

            public static final class Builder extends GeneratedMessageLite.Builder<TrackedJob, Builder> implements TrackedJobOrBuilder {
                private Builder() {
                    super(TrackedJob.DEFAULT_INSTANCE);
                }

                @Override // com.android.server.job.StateControllerProto.ConnectivityController.TrackedJobOrBuilder
                public boolean hasInfo() {
                    return ((TrackedJob) this.instance).hasInfo();
                }

                @Override // com.android.server.job.StateControllerProto.ConnectivityController.TrackedJobOrBuilder
                public JobStatusShortInfoProto getInfo() {
                    return ((TrackedJob) this.instance).getInfo();
                }

                public Builder setInfo(JobStatusShortInfoProto value) {
                    copyOnWrite();
                    ((TrackedJob) this.instance).setInfo((TrackedJob) value);
                    return this;
                }

                public Builder setInfo(JobStatusShortInfoProto.Builder builderForValue) {
                    copyOnWrite();
                    ((TrackedJob) this.instance).setInfo((TrackedJob) builderForValue);
                    return this;
                }

                public Builder mergeInfo(JobStatusShortInfoProto value) {
                    copyOnWrite();
                    ((TrackedJob) this.instance).mergeInfo(value);
                    return this;
                }

                public Builder clearInfo() {
                    copyOnWrite();
                    ((TrackedJob) this.instance).clearInfo();
                    return this;
                }

                @Override // com.android.server.job.StateControllerProto.ConnectivityController.TrackedJobOrBuilder
                public boolean hasSourceUid() {
                    return ((TrackedJob) this.instance).hasSourceUid();
                }

                @Override // com.android.server.job.StateControllerProto.ConnectivityController.TrackedJobOrBuilder
                public int getSourceUid() {
                    return ((TrackedJob) this.instance).getSourceUid();
                }

                public Builder setSourceUid(int value) {
                    copyOnWrite();
                    ((TrackedJob) this.instance).setSourceUid(value);
                    return this;
                }

                public Builder clearSourceUid() {
                    copyOnWrite();
                    ((TrackedJob) this.instance).clearSourceUid();
                    return this;
                }

                @Override // com.android.server.job.StateControllerProto.ConnectivityController.TrackedJobOrBuilder
                public boolean hasRequiredNetwork() {
                    return ((TrackedJob) this.instance).hasRequiredNetwork();
                }

                @Override // com.android.server.job.StateControllerProto.ConnectivityController.TrackedJobOrBuilder
                public NetworkRequestProto getRequiredNetwork() {
                    return ((TrackedJob) this.instance).getRequiredNetwork();
                }

                public Builder setRequiredNetwork(NetworkRequestProto value) {
                    copyOnWrite();
                    ((TrackedJob) this.instance).setRequiredNetwork((TrackedJob) value);
                    return this;
                }

                public Builder setRequiredNetwork(NetworkRequestProto.Builder builderForValue) {
                    copyOnWrite();
                    ((TrackedJob) this.instance).setRequiredNetwork((TrackedJob) builderForValue);
                    return this;
                }

                public Builder mergeRequiredNetwork(NetworkRequestProto value) {
                    copyOnWrite();
                    ((TrackedJob) this.instance).mergeRequiredNetwork(value);
                    return this;
                }

                public Builder clearRequiredNetwork() {
                    copyOnWrite();
                    ((TrackedJob) this.instance).clearRequiredNetwork();
                    return this;
                }
            }

            /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
            /* access modifiers changed from: protected */
            @Override // com.google.protobuf.GeneratedMessageLite
            public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
                switch (method) {
                    case NEW_MUTABLE_INSTANCE:
                        return new TrackedJob();
                    case IS_INITIALIZED:
                        return DEFAULT_INSTANCE;
                    case MAKE_IMMUTABLE:
                        return null;
                    case NEW_BUILDER:
                        return new Builder();
                    case VISIT:
                        GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                        TrackedJob other = (TrackedJob) arg1;
                        this.info_ = (JobStatusShortInfoProto) visitor.visitMessage(this.info_, other.info_);
                        this.sourceUid_ = visitor.visitInt(hasSourceUid(), this.sourceUid_, other.hasSourceUid(), other.sourceUid_);
                        this.requiredNetwork_ = (NetworkRequestProto) visitor.visitMessage(this.requiredNetwork_, other.requiredNetwork_);
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
                                    this.sourceUid_ = input.readInt32();
                                } else if (tag == 26) {
                                    NetworkRequestProto.Builder subBuilder2 = null;
                                    if ((this.bitField0_ & 4) == 4) {
                                        subBuilder2 = (NetworkRequestProto.Builder) this.requiredNetwork_.toBuilder();
                                    }
                                    this.requiredNetwork_ = (NetworkRequestProto) input.readMessage(NetworkRequestProto.parser(), extensionRegistry);
                                    if (subBuilder2 != null) {
                                        subBuilder2.mergeFrom((GeneratedMessageLite) this.requiredNetwork_);
                                        this.requiredNetwork_ = (NetworkRequestProto) subBuilder2.buildPartial();
                                    }
                                    this.bitField0_ |= 4;
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
                            synchronized (TrackedJob.class) {
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

            public static TrackedJob getDefaultInstance() {
                return DEFAULT_INSTANCE;
            }

            public static Parser<TrackedJob> parser() {
                return DEFAULT_INSTANCE.getParserForType();
            }
        }

        @Override // com.android.server.job.StateControllerProto.ConnectivityControllerOrBuilder
        public boolean hasIsConnected() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // com.android.server.job.StateControllerProto.ConnectivityControllerOrBuilder
        public boolean getIsConnected() {
            return this.isConnected_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setIsConnected(boolean value) {
            this.bitField0_ |= 1;
            this.isConnected_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearIsConnected() {
            this.bitField0_ &= -2;
            this.isConnected_ = false;
        }

        @Override // com.android.server.job.StateControllerProto.ConnectivityControllerOrBuilder
        public List<TrackedJob> getTrackedJobsList() {
            return this.trackedJobs_;
        }

        public List<? extends TrackedJobOrBuilder> getTrackedJobsOrBuilderList() {
            return this.trackedJobs_;
        }

        @Override // com.android.server.job.StateControllerProto.ConnectivityControllerOrBuilder
        public int getTrackedJobsCount() {
            return this.trackedJobs_.size();
        }

        @Override // com.android.server.job.StateControllerProto.ConnectivityControllerOrBuilder
        public TrackedJob getTrackedJobs(int index) {
            return this.trackedJobs_.get(index);
        }

        public TrackedJobOrBuilder getTrackedJobsOrBuilder(int index) {
            return this.trackedJobs_.get(index);
        }

        private void ensureTrackedJobsIsMutable() {
            if (!this.trackedJobs_.isModifiable()) {
                this.trackedJobs_ = GeneratedMessageLite.mutableCopy(this.trackedJobs_);
            }
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setTrackedJobs(int index, TrackedJob value) {
            if (value != null) {
                ensureTrackedJobsIsMutable();
                this.trackedJobs_.set(index, value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setTrackedJobs(int index, TrackedJob.Builder builderForValue) {
            ensureTrackedJobsIsMutable();
            this.trackedJobs_.set(index, (TrackedJob) builderForValue.build());
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addTrackedJobs(TrackedJob value) {
            if (value != null) {
                ensureTrackedJobsIsMutable();
                this.trackedJobs_.add(value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addTrackedJobs(int index, TrackedJob value) {
            if (value != null) {
                ensureTrackedJobsIsMutable();
                this.trackedJobs_.add(index, value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addTrackedJobs(TrackedJob.Builder builderForValue) {
            ensureTrackedJobsIsMutable();
            this.trackedJobs_.add((TrackedJob) builderForValue.build());
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addTrackedJobs(int index, TrackedJob.Builder builderForValue) {
            ensureTrackedJobsIsMutable();
            this.trackedJobs_.add(index, (TrackedJob) builderForValue.build());
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addAllTrackedJobs(Iterable<? extends TrackedJob> values) {
            ensureTrackedJobsIsMutable();
            AbstractMessageLite.addAll(values, this.trackedJobs_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearTrackedJobs() {
            this.trackedJobs_ = emptyProtobufList();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void removeTrackedJobs(int index) {
            ensureTrackedJobsIsMutable();
            this.trackedJobs_.remove(index);
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                output.writeBool(1, this.isConnected_);
            }
            for (int i = 0; i < this.trackedJobs_.size(); i++) {
                output.writeMessage(2, this.trackedJobs_.get(i));
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
                size2 = 0 + CodedOutputStream.computeBoolSize(1, this.isConnected_);
            }
            for (int i = 0; i < this.trackedJobs_.size(); i++) {
                size2 += CodedOutputStream.computeMessageSize(2, this.trackedJobs_.get(i));
            }
            int size3 = size2 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static ConnectivityController parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (ConnectivityController) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static ConnectivityController parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (ConnectivityController) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static ConnectivityController parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (ConnectivityController) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static ConnectivityController parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (ConnectivityController) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static ConnectivityController parseFrom(InputStream input) throws IOException {
            return (ConnectivityController) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static ConnectivityController parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (ConnectivityController) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static ConnectivityController parseDelimitedFrom(InputStream input) throws IOException {
            return (ConnectivityController) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static ConnectivityController parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (ConnectivityController) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static ConnectivityController parseFrom(CodedInputStream input) throws IOException {
            return (ConnectivityController) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static ConnectivityController parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (ConnectivityController) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(ConnectivityController prototype) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<ConnectivityController, Builder> implements ConnectivityControllerOrBuilder {
            private Builder() {
                super(ConnectivityController.DEFAULT_INSTANCE);
            }

            @Override // com.android.server.job.StateControllerProto.ConnectivityControllerOrBuilder
            public boolean hasIsConnected() {
                return ((ConnectivityController) this.instance).hasIsConnected();
            }

            @Override // com.android.server.job.StateControllerProto.ConnectivityControllerOrBuilder
            public boolean getIsConnected() {
                return ((ConnectivityController) this.instance).getIsConnected();
            }

            public Builder setIsConnected(boolean value) {
                copyOnWrite();
                ((ConnectivityController) this.instance).setIsConnected(value);
                return this;
            }

            public Builder clearIsConnected() {
                copyOnWrite();
                ((ConnectivityController) this.instance).clearIsConnected();
                return this;
            }

            @Override // com.android.server.job.StateControllerProto.ConnectivityControllerOrBuilder
            public List<TrackedJob> getTrackedJobsList() {
                return Collections.unmodifiableList(((ConnectivityController) this.instance).getTrackedJobsList());
            }

            @Override // com.android.server.job.StateControllerProto.ConnectivityControllerOrBuilder
            public int getTrackedJobsCount() {
                return ((ConnectivityController) this.instance).getTrackedJobsCount();
            }

            @Override // com.android.server.job.StateControllerProto.ConnectivityControllerOrBuilder
            public TrackedJob getTrackedJobs(int index) {
                return ((ConnectivityController) this.instance).getTrackedJobs(index);
            }

            public Builder setTrackedJobs(int index, TrackedJob value) {
                copyOnWrite();
                ((ConnectivityController) this.instance).setTrackedJobs((ConnectivityController) index, (int) value);
                return this;
            }

            public Builder setTrackedJobs(int index, TrackedJob.Builder builderForValue) {
                copyOnWrite();
                ((ConnectivityController) this.instance).setTrackedJobs((ConnectivityController) index, (int) builderForValue);
                return this;
            }

            public Builder addTrackedJobs(TrackedJob value) {
                copyOnWrite();
                ((ConnectivityController) this.instance).addTrackedJobs((ConnectivityController) value);
                return this;
            }

            public Builder addTrackedJobs(int index, TrackedJob value) {
                copyOnWrite();
                ((ConnectivityController) this.instance).addTrackedJobs((ConnectivityController) index, (int) value);
                return this;
            }

            public Builder addTrackedJobs(TrackedJob.Builder builderForValue) {
                copyOnWrite();
                ((ConnectivityController) this.instance).addTrackedJobs((ConnectivityController) builderForValue);
                return this;
            }

            public Builder addTrackedJobs(int index, TrackedJob.Builder builderForValue) {
                copyOnWrite();
                ((ConnectivityController) this.instance).addTrackedJobs((ConnectivityController) index, (int) builderForValue);
                return this;
            }

            public Builder addAllTrackedJobs(Iterable<? extends TrackedJob> values) {
                copyOnWrite();
                ((ConnectivityController) this.instance).addAllTrackedJobs(values);
                return this;
            }

            public Builder clearTrackedJobs() {
                copyOnWrite();
                ((ConnectivityController) this.instance).clearTrackedJobs();
                return this;
            }

            public Builder removeTrackedJobs(int index) {
                copyOnWrite();
                ((ConnectivityController) this.instance).removeTrackedJobs(index);
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new ConnectivityController();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    this.trackedJobs_.makeImmutable();
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    ConnectivityController other = (ConnectivityController) arg1;
                    this.isConnected_ = visitor.visitBoolean(hasIsConnected(), this.isConnected_, other.hasIsConnected(), other.isConnected_);
                    this.trackedJobs_ = visitor.visitList(this.trackedJobs_, other.trackedJobs_);
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
                                this.isConnected_ = input.readBool();
                            } else if (tag == 18) {
                                if (!this.trackedJobs_.isModifiable()) {
                                    this.trackedJobs_ = GeneratedMessageLite.mutableCopy(this.trackedJobs_);
                                }
                                this.trackedJobs_.add((TrackedJob) input.readMessage(TrackedJob.parser(), extensionRegistry));
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
                        synchronized (ConnectivityController.class) {
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

        public static ConnectivityController getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<ConnectivityController> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class ContentObserverController extends GeneratedMessageLite<ContentObserverController, Builder> implements ContentObserverControllerOrBuilder {
        private static final ContentObserverController DEFAULT_INSTANCE = new ContentObserverController();
        public static final int OBSERVERS_FIELD_NUMBER = 2;
        private static volatile Parser<ContentObserverController> PARSER = null;
        public static final int TRACKED_JOBS_FIELD_NUMBER = 1;
        private Internal.ProtobufList<Observer> observers_ = emptyProtobufList();
        private Internal.ProtobufList<TrackedJob> trackedJobs_ = emptyProtobufList();

        public interface ObserverOrBuilder extends MessageLiteOrBuilder {
            Observer.TriggerContentData getTriggers(int i);

            int getTriggersCount();

            List<Observer.TriggerContentData> getTriggersList();

            int getUserId();

            boolean hasUserId();
        }

        public interface TrackedJobOrBuilder extends MessageLiteOrBuilder {
            JobStatusShortInfoProto getInfo();

            int getSourceUid();

            boolean hasInfo();

            boolean hasSourceUid();
        }

        private ContentObserverController() {
        }

        public static final class TrackedJob extends GeneratedMessageLite<TrackedJob, Builder> implements TrackedJobOrBuilder {
            private static final TrackedJob DEFAULT_INSTANCE = new TrackedJob();
            public static final int INFO_FIELD_NUMBER = 1;
            private static volatile Parser<TrackedJob> PARSER = null;
            public static final int SOURCE_UID_FIELD_NUMBER = 2;
            private int bitField0_;
            private JobStatusShortInfoProto info_;
            private int sourceUid_ = 0;

            private TrackedJob() {
            }

            @Override // com.android.server.job.StateControllerProto.ContentObserverController.TrackedJobOrBuilder
            public boolean hasInfo() {
                return (this.bitField0_ & 1) == 1;
            }

            @Override // com.android.server.job.StateControllerProto.ContentObserverController.TrackedJobOrBuilder
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

            @Override // com.android.server.job.StateControllerProto.ContentObserverController.TrackedJobOrBuilder
            public boolean hasSourceUid() {
                return (this.bitField0_ & 2) == 2;
            }

            @Override // com.android.server.job.StateControllerProto.ContentObserverController.TrackedJobOrBuilder
            public int getSourceUid() {
                return this.sourceUid_;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void setSourceUid(int value) {
                this.bitField0_ |= 2;
                this.sourceUid_ = value;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void clearSourceUid() {
                this.bitField0_ &= -3;
                this.sourceUid_ = 0;
            }

            @Override // com.google.protobuf.MessageLite
            public void writeTo(CodedOutputStream output) throws IOException {
                if ((this.bitField0_ & 1) == 1) {
                    output.writeMessage(1, getInfo());
                }
                if ((this.bitField0_ & 2) == 2) {
                    output.writeInt32(2, this.sourceUid_);
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
                    size2 += CodedOutputStream.computeInt32Size(2, this.sourceUid_);
                }
                int size3 = size2 + this.unknownFields.getSerializedSize();
                this.memoizedSerializedSize = size3;
                return size3;
            }

            public static TrackedJob parseFrom(ByteString data) throws InvalidProtocolBufferException {
                return (TrackedJob) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
            }

            public static TrackedJob parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return (TrackedJob) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
            }

            public static TrackedJob parseFrom(byte[] data) throws InvalidProtocolBufferException {
                return (TrackedJob) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
            }

            public static TrackedJob parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return (TrackedJob) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
            }

            public static TrackedJob parseFrom(InputStream input) throws IOException {
                return (TrackedJob) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
            }

            public static TrackedJob parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (TrackedJob) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static TrackedJob parseDelimitedFrom(InputStream input) throws IOException {
                return (TrackedJob) parseDelimitedFrom(DEFAULT_INSTANCE, input);
            }

            public static TrackedJob parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (TrackedJob) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static TrackedJob parseFrom(CodedInputStream input) throws IOException {
                return (TrackedJob) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
            }

            public static TrackedJob parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (TrackedJob) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static Builder newBuilder() {
                return (Builder) DEFAULT_INSTANCE.toBuilder();
            }

            public static Builder newBuilder(TrackedJob prototype) {
                return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
            }

            public static final class Builder extends GeneratedMessageLite.Builder<TrackedJob, Builder> implements TrackedJobOrBuilder {
                private Builder() {
                    super(TrackedJob.DEFAULT_INSTANCE);
                }

                @Override // com.android.server.job.StateControllerProto.ContentObserverController.TrackedJobOrBuilder
                public boolean hasInfo() {
                    return ((TrackedJob) this.instance).hasInfo();
                }

                @Override // com.android.server.job.StateControllerProto.ContentObserverController.TrackedJobOrBuilder
                public JobStatusShortInfoProto getInfo() {
                    return ((TrackedJob) this.instance).getInfo();
                }

                public Builder setInfo(JobStatusShortInfoProto value) {
                    copyOnWrite();
                    ((TrackedJob) this.instance).setInfo((TrackedJob) value);
                    return this;
                }

                public Builder setInfo(JobStatusShortInfoProto.Builder builderForValue) {
                    copyOnWrite();
                    ((TrackedJob) this.instance).setInfo((TrackedJob) builderForValue);
                    return this;
                }

                public Builder mergeInfo(JobStatusShortInfoProto value) {
                    copyOnWrite();
                    ((TrackedJob) this.instance).mergeInfo(value);
                    return this;
                }

                public Builder clearInfo() {
                    copyOnWrite();
                    ((TrackedJob) this.instance).clearInfo();
                    return this;
                }

                @Override // com.android.server.job.StateControllerProto.ContentObserverController.TrackedJobOrBuilder
                public boolean hasSourceUid() {
                    return ((TrackedJob) this.instance).hasSourceUid();
                }

                @Override // com.android.server.job.StateControllerProto.ContentObserverController.TrackedJobOrBuilder
                public int getSourceUid() {
                    return ((TrackedJob) this.instance).getSourceUid();
                }

                public Builder setSourceUid(int value) {
                    copyOnWrite();
                    ((TrackedJob) this.instance).setSourceUid(value);
                    return this;
                }

                public Builder clearSourceUid() {
                    copyOnWrite();
                    ((TrackedJob) this.instance).clearSourceUid();
                    return this;
                }
            }

            /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
            /* access modifiers changed from: protected */
            @Override // com.google.protobuf.GeneratedMessageLite
            public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
                switch (method) {
                    case NEW_MUTABLE_INSTANCE:
                        return new TrackedJob();
                    case IS_INITIALIZED:
                        return DEFAULT_INSTANCE;
                    case MAKE_IMMUTABLE:
                        return null;
                    case NEW_BUILDER:
                        return new Builder();
                    case VISIT:
                        GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                        TrackedJob other = (TrackedJob) arg1;
                        this.info_ = (JobStatusShortInfoProto) visitor.visitMessage(this.info_, other.info_);
                        this.sourceUid_ = visitor.visitInt(hasSourceUid(), this.sourceUid_, other.hasSourceUid(), other.sourceUid_);
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
                                    this.sourceUid_ = input.readInt32();
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
                            synchronized (TrackedJob.class) {
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

            public static TrackedJob getDefaultInstance() {
                return DEFAULT_INSTANCE;
            }

            public static Parser<TrackedJob> parser() {
                return DEFAULT_INSTANCE.getParserForType();
            }
        }

        public static final class Observer extends GeneratedMessageLite<Observer, Builder> implements ObserverOrBuilder {
            private static final Observer DEFAULT_INSTANCE = new Observer();
            private static volatile Parser<Observer> PARSER = null;
            public static final int TRIGGERS_FIELD_NUMBER = 2;
            public static final int USER_ID_FIELD_NUMBER = 1;
            private int bitField0_;
            private Internal.ProtobufList<TriggerContentData> triggers_ = emptyProtobufList();
            private int userId_ = 0;

            public interface TriggerContentDataOrBuilder extends MessageLiteOrBuilder {
                int getFlags();

                TriggerContentData.JobInstance getJobs(int i);

                int getJobsCount();

                List<TriggerContentData.JobInstance> getJobsList();

                String getUri();

                ByteString getUriBytes();

                boolean hasFlags();

                boolean hasUri();
            }

            private Observer() {
            }

            public static final class TriggerContentData extends GeneratedMessageLite<TriggerContentData, Builder> implements TriggerContentDataOrBuilder {
                private static final TriggerContentData DEFAULT_INSTANCE = new TriggerContentData();
                public static final int FLAGS_FIELD_NUMBER = 2;
                public static final int JOBS_FIELD_NUMBER = 3;
                private static volatile Parser<TriggerContentData> PARSER = null;
                public static final int URI_FIELD_NUMBER = 1;
                private int bitField0_;
                private int flags_ = 0;
                private Internal.ProtobufList<JobInstance> jobs_ = emptyProtobufList();
                private String uri_ = "";

                public interface JobInstanceOrBuilder extends MessageLiteOrBuilder {
                    String getChangedAuthorities(int i);

                    ByteString getChangedAuthoritiesBytes(int i);

                    int getChangedAuthoritiesCount();

                    List<String> getChangedAuthoritiesList();

                    String getChangedUris(int i);

                    ByteString getChangedUrisBytes(int i);

                    int getChangedUrisCount();

                    List<String> getChangedUrisList();

                    JobStatusShortInfoProto getInfo();

                    int getSourceUid();

                    long getTriggerContentMaxDelayMs();

                    long getTriggerContentUpdateDelayMs();

                    boolean hasInfo();

                    boolean hasSourceUid();

                    boolean hasTriggerContentMaxDelayMs();

                    boolean hasTriggerContentUpdateDelayMs();
                }

                private TriggerContentData() {
                }

                public static final class JobInstance extends GeneratedMessageLite<JobInstance, Builder> implements JobInstanceOrBuilder {
                    public static final int CHANGED_AUTHORITIES_FIELD_NUMBER = 5;
                    public static final int CHANGED_URIS_FIELD_NUMBER = 6;
                    private static final JobInstance DEFAULT_INSTANCE = new JobInstance();
                    public static final int INFO_FIELD_NUMBER = 1;
                    private static volatile Parser<JobInstance> PARSER = null;
                    public static final int SOURCE_UID_FIELD_NUMBER = 2;
                    public static final int TRIGGER_CONTENT_MAX_DELAY_MS_FIELD_NUMBER = 4;
                    public static final int TRIGGER_CONTENT_UPDATE_DELAY_MS_FIELD_NUMBER = 3;
                    private int bitField0_;
                    private Internal.ProtobufList<String> changedAuthorities_ = GeneratedMessageLite.emptyProtobufList();
                    private Internal.ProtobufList<String> changedUris_ = GeneratedMessageLite.emptyProtobufList();
                    private JobStatusShortInfoProto info_;
                    private int sourceUid_ = 0;
                    private long triggerContentMaxDelayMs_ = 0;
                    private long triggerContentUpdateDelayMs_ = 0;

                    private JobInstance() {
                    }

                    @Override // com.android.server.job.StateControllerProto.ContentObserverController.Observer.TriggerContentData.JobInstanceOrBuilder
                    public boolean hasInfo() {
                        return (this.bitField0_ & 1) == 1;
                    }

                    @Override // com.android.server.job.StateControllerProto.ContentObserverController.Observer.TriggerContentData.JobInstanceOrBuilder
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

                    @Override // com.android.server.job.StateControllerProto.ContentObserverController.Observer.TriggerContentData.JobInstanceOrBuilder
                    public boolean hasSourceUid() {
                        return (this.bitField0_ & 2) == 2;
                    }

                    @Override // com.android.server.job.StateControllerProto.ContentObserverController.Observer.TriggerContentData.JobInstanceOrBuilder
                    public int getSourceUid() {
                        return this.sourceUid_;
                    }

                    /* access modifiers changed from: private */
                    /* access modifiers changed from: public */
                    private void setSourceUid(int value) {
                        this.bitField0_ |= 2;
                        this.sourceUid_ = value;
                    }

                    /* access modifiers changed from: private */
                    /* access modifiers changed from: public */
                    private void clearSourceUid() {
                        this.bitField0_ &= -3;
                        this.sourceUid_ = 0;
                    }

                    @Override // com.android.server.job.StateControllerProto.ContentObserverController.Observer.TriggerContentData.JobInstanceOrBuilder
                    public boolean hasTriggerContentUpdateDelayMs() {
                        return (this.bitField0_ & 4) == 4;
                    }

                    @Override // com.android.server.job.StateControllerProto.ContentObserverController.Observer.TriggerContentData.JobInstanceOrBuilder
                    public long getTriggerContentUpdateDelayMs() {
                        return this.triggerContentUpdateDelayMs_;
                    }

                    /* access modifiers changed from: private */
                    /* access modifiers changed from: public */
                    private void setTriggerContentUpdateDelayMs(long value) {
                        this.bitField0_ |= 4;
                        this.triggerContentUpdateDelayMs_ = value;
                    }

                    /* access modifiers changed from: private */
                    /* access modifiers changed from: public */
                    private void clearTriggerContentUpdateDelayMs() {
                        this.bitField0_ &= -5;
                        this.triggerContentUpdateDelayMs_ = 0;
                    }

                    @Override // com.android.server.job.StateControllerProto.ContentObserverController.Observer.TriggerContentData.JobInstanceOrBuilder
                    public boolean hasTriggerContentMaxDelayMs() {
                        return (this.bitField0_ & 8) == 8;
                    }

                    @Override // com.android.server.job.StateControllerProto.ContentObserverController.Observer.TriggerContentData.JobInstanceOrBuilder
                    public long getTriggerContentMaxDelayMs() {
                        return this.triggerContentMaxDelayMs_;
                    }

                    /* access modifiers changed from: private */
                    /* access modifiers changed from: public */
                    private void setTriggerContentMaxDelayMs(long value) {
                        this.bitField0_ |= 8;
                        this.triggerContentMaxDelayMs_ = value;
                    }

                    /* access modifiers changed from: private */
                    /* access modifiers changed from: public */
                    private void clearTriggerContentMaxDelayMs() {
                        this.bitField0_ &= -9;
                        this.triggerContentMaxDelayMs_ = 0;
                    }

                    @Override // com.android.server.job.StateControllerProto.ContentObserverController.Observer.TriggerContentData.JobInstanceOrBuilder
                    public List<String> getChangedAuthoritiesList() {
                        return this.changedAuthorities_;
                    }

                    @Override // com.android.server.job.StateControllerProto.ContentObserverController.Observer.TriggerContentData.JobInstanceOrBuilder
                    public int getChangedAuthoritiesCount() {
                        return this.changedAuthorities_.size();
                    }

                    @Override // com.android.server.job.StateControllerProto.ContentObserverController.Observer.TriggerContentData.JobInstanceOrBuilder
                    public String getChangedAuthorities(int index) {
                        return this.changedAuthorities_.get(index);
                    }

                    @Override // com.android.server.job.StateControllerProto.ContentObserverController.Observer.TriggerContentData.JobInstanceOrBuilder
                    public ByteString getChangedAuthoritiesBytes(int index) {
                        return ByteString.copyFromUtf8(this.changedAuthorities_.get(index));
                    }

                    private void ensureChangedAuthoritiesIsMutable() {
                        if (!this.changedAuthorities_.isModifiable()) {
                            this.changedAuthorities_ = GeneratedMessageLite.mutableCopy(this.changedAuthorities_);
                        }
                    }

                    /* access modifiers changed from: private */
                    /* access modifiers changed from: public */
                    private void setChangedAuthorities(int index, String value) {
                        if (value != null) {
                            ensureChangedAuthoritiesIsMutable();
                            this.changedAuthorities_.set(index, value);
                            return;
                        }
                        throw new NullPointerException();
                    }

                    /* access modifiers changed from: private */
                    /* access modifiers changed from: public */
                    private void addChangedAuthorities(String value) {
                        if (value != null) {
                            ensureChangedAuthoritiesIsMutable();
                            this.changedAuthorities_.add(value);
                            return;
                        }
                        throw new NullPointerException();
                    }

                    /* access modifiers changed from: private */
                    /* access modifiers changed from: public */
                    private void addAllChangedAuthorities(Iterable<String> values) {
                        ensureChangedAuthoritiesIsMutable();
                        AbstractMessageLite.addAll(values, this.changedAuthorities_);
                    }

                    /* access modifiers changed from: private */
                    /* access modifiers changed from: public */
                    private void clearChangedAuthorities() {
                        this.changedAuthorities_ = GeneratedMessageLite.emptyProtobufList();
                    }

                    /* access modifiers changed from: private */
                    /* access modifiers changed from: public */
                    private void addChangedAuthoritiesBytes(ByteString value) {
                        if (value != null) {
                            ensureChangedAuthoritiesIsMutable();
                            this.changedAuthorities_.add(value.toStringUtf8());
                            return;
                        }
                        throw new NullPointerException();
                    }

                    @Override // com.android.server.job.StateControllerProto.ContentObserverController.Observer.TriggerContentData.JobInstanceOrBuilder
                    public List<String> getChangedUrisList() {
                        return this.changedUris_;
                    }

                    @Override // com.android.server.job.StateControllerProto.ContentObserverController.Observer.TriggerContentData.JobInstanceOrBuilder
                    public int getChangedUrisCount() {
                        return this.changedUris_.size();
                    }

                    @Override // com.android.server.job.StateControllerProto.ContentObserverController.Observer.TriggerContentData.JobInstanceOrBuilder
                    public String getChangedUris(int index) {
                        return this.changedUris_.get(index);
                    }

                    @Override // com.android.server.job.StateControllerProto.ContentObserverController.Observer.TriggerContentData.JobInstanceOrBuilder
                    public ByteString getChangedUrisBytes(int index) {
                        return ByteString.copyFromUtf8(this.changedUris_.get(index));
                    }

                    private void ensureChangedUrisIsMutable() {
                        if (!this.changedUris_.isModifiable()) {
                            this.changedUris_ = GeneratedMessageLite.mutableCopy(this.changedUris_);
                        }
                    }

                    /* access modifiers changed from: private */
                    /* access modifiers changed from: public */
                    private void setChangedUris(int index, String value) {
                        if (value != null) {
                            ensureChangedUrisIsMutable();
                            this.changedUris_.set(index, value);
                            return;
                        }
                        throw new NullPointerException();
                    }

                    /* access modifiers changed from: private */
                    /* access modifiers changed from: public */
                    private void addChangedUris(String value) {
                        if (value != null) {
                            ensureChangedUrisIsMutable();
                            this.changedUris_.add(value);
                            return;
                        }
                        throw new NullPointerException();
                    }

                    /* access modifiers changed from: private */
                    /* access modifiers changed from: public */
                    private void addAllChangedUris(Iterable<String> values) {
                        ensureChangedUrisIsMutable();
                        AbstractMessageLite.addAll(values, this.changedUris_);
                    }

                    /* access modifiers changed from: private */
                    /* access modifiers changed from: public */
                    private void clearChangedUris() {
                        this.changedUris_ = GeneratedMessageLite.emptyProtobufList();
                    }

                    /* access modifiers changed from: private */
                    /* access modifiers changed from: public */
                    private void addChangedUrisBytes(ByteString value) {
                        if (value != null) {
                            ensureChangedUrisIsMutable();
                            this.changedUris_.add(value.toStringUtf8());
                            return;
                        }
                        throw new NullPointerException();
                    }

                    @Override // com.google.protobuf.MessageLite
                    public void writeTo(CodedOutputStream output) throws IOException {
                        if ((this.bitField0_ & 1) == 1) {
                            output.writeMessage(1, getInfo());
                        }
                        if ((this.bitField0_ & 2) == 2) {
                            output.writeInt32(2, this.sourceUid_);
                        }
                        if ((this.bitField0_ & 4) == 4) {
                            output.writeInt64(3, this.triggerContentUpdateDelayMs_);
                        }
                        if ((this.bitField0_ & 8) == 8) {
                            output.writeInt64(4, this.triggerContentMaxDelayMs_);
                        }
                        for (int i = 0; i < this.changedAuthorities_.size(); i++) {
                            output.writeString(5, this.changedAuthorities_.get(i));
                        }
                        for (int i2 = 0; i2 < this.changedUris_.size(); i2++) {
                            output.writeString(6, this.changedUris_.get(i2));
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
                            size2 += CodedOutputStream.computeInt32Size(2, this.sourceUid_);
                        }
                        if ((this.bitField0_ & 4) == 4) {
                            size2 += CodedOutputStream.computeInt64Size(3, this.triggerContentUpdateDelayMs_);
                        }
                        if ((this.bitField0_ & 8) == 8) {
                            size2 += CodedOutputStream.computeInt64Size(4, this.triggerContentMaxDelayMs_);
                        }
                        int dataSize = 0;
                        for (int i = 0; i < this.changedAuthorities_.size(); i++) {
                            dataSize += CodedOutputStream.computeStringSizeNoTag(this.changedAuthorities_.get(i));
                        }
                        int size3 = size2 + dataSize + (getChangedAuthoritiesList().size() * 1);
                        int dataSize2 = 0;
                        for (int i2 = 0; i2 < this.changedUris_.size(); i2++) {
                            dataSize2 += CodedOutputStream.computeStringSizeNoTag(this.changedUris_.get(i2));
                        }
                        int size4 = size3 + dataSize2 + (getChangedUrisList().size() * 1) + this.unknownFields.getSerializedSize();
                        this.memoizedSerializedSize = size4;
                        return size4;
                    }

                    public static JobInstance parseFrom(ByteString data) throws InvalidProtocolBufferException {
                        return (JobInstance) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
                    }

                    public static JobInstance parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                        return (JobInstance) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
                    }

                    public static JobInstance parseFrom(byte[] data) throws InvalidProtocolBufferException {
                        return (JobInstance) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
                    }

                    public static JobInstance parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                        return (JobInstance) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
                    }

                    public static JobInstance parseFrom(InputStream input) throws IOException {
                        return (JobInstance) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
                    }

                    public static JobInstance parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                        return (JobInstance) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
                    }

                    public static JobInstance parseDelimitedFrom(InputStream input) throws IOException {
                        return (JobInstance) parseDelimitedFrom(DEFAULT_INSTANCE, input);
                    }

                    public static JobInstance parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                        return (JobInstance) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
                    }

                    public static JobInstance parseFrom(CodedInputStream input) throws IOException {
                        return (JobInstance) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
                    }

                    public static JobInstance parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                        return (JobInstance) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
                    }

                    public static Builder newBuilder() {
                        return (Builder) DEFAULT_INSTANCE.toBuilder();
                    }

                    public static Builder newBuilder(JobInstance prototype) {
                        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
                    }

                    public static final class Builder extends GeneratedMessageLite.Builder<JobInstance, Builder> implements JobInstanceOrBuilder {
                        private Builder() {
                            super(JobInstance.DEFAULT_INSTANCE);
                        }

                        @Override // com.android.server.job.StateControllerProto.ContentObserverController.Observer.TriggerContentData.JobInstanceOrBuilder
                        public boolean hasInfo() {
                            return ((JobInstance) this.instance).hasInfo();
                        }

                        @Override // com.android.server.job.StateControllerProto.ContentObserverController.Observer.TriggerContentData.JobInstanceOrBuilder
                        public JobStatusShortInfoProto getInfo() {
                            return ((JobInstance) this.instance).getInfo();
                        }

                        public Builder setInfo(JobStatusShortInfoProto value) {
                            copyOnWrite();
                            ((JobInstance) this.instance).setInfo((JobInstance) value);
                            return this;
                        }

                        public Builder setInfo(JobStatusShortInfoProto.Builder builderForValue) {
                            copyOnWrite();
                            ((JobInstance) this.instance).setInfo((JobInstance) builderForValue);
                            return this;
                        }

                        public Builder mergeInfo(JobStatusShortInfoProto value) {
                            copyOnWrite();
                            ((JobInstance) this.instance).mergeInfo(value);
                            return this;
                        }

                        public Builder clearInfo() {
                            copyOnWrite();
                            ((JobInstance) this.instance).clearInfo();
                            return this;
                        }

                        @Override // com.android.server.job.StateControllerProto.ContentObserverController.Observer.TriggerContentData.JobInstanceOrBuilder
                        public boolean hasSourceUid() {
                            return ((JobInstance) this.instance).hasSourceUid();
                        }

                        @Override // com.android.server.job.StateControllerProto.ContentObserverController.Observer.TriggerContentData.JobInstanceOrBuilder
                        public int getSourceUid() {
                            return ((JobInstance) this.instance).getSourceUid();
                        }

                        public Builder setSourceUid(int value) {
                            copyOnWrite();
                            ((JobInstance) this.instance).setSourceUid(value);
                            return this;
                        }

                        public Builder clearSourceUid() {
                            copyOnWrite();
                            ((JobInstance) this.instance).clearSourceUid();
                            return this;
                        }

                        @Override // com.android.server.job.StateControllerProto.ContentObserverController.Observer.TriggerContentData.JobInstanceOrBuilder
                        public boolean hasTriggerContentUpdateDelayMs() {
                            return ((JobInstance) this.instance).hasTriggerContentUpdateDelayMs();
                        }

                        @Override // com.android.server.job.StateControllerProto.ContentObserverController.Observer.TriggerContentData.JobInstanceOrBuilder
                        public long getTriggerContentUpdateDelayMs() {
                            return ((JobInstance) this.instance).getTriggerContentUpdateDelayMs();
                        }

                        public Builder setTriggerContentUpdateDelayMs(long value) {
                            copyOnWrite();
                            ((JobInstance) this.instance).setTriggerContentUpdateDelayMs(value);
                            return this;
                        }

                        public Builder clearTriggerContentUpdateDelayMs() {
                            copyOnWrite();
                            ((JobInstance) this.instance).clearTriggerContentUpdateDelayMs();
                            return this;
                        }

                        @Override // com.android.server.job.StateControllerProto.ContentObserverController.Observer.TriggerContentData.JobInstanceOrBuilder
                        public boolean hasTriggerContentMaxDelayMs() {
                            return ((JobInstance) this.instance).hasTriggerContentMaxDelayMs();
                        }

                        @Override // com.android.server.job.StateControllerProto.ContentObserverController.Observer.TriggerContentData.JobInstanceOrBuilder
                        public long getTriggerContentMaxDelayMs() {
                            return ((JobInstance) this.instance).getTriggerContentMaxDelayMs();
                        }

                        public Builder setTriggerContentMaxDelayMs(long value) {
                            copyOnWrite();
                            ((JobInstance) this.instance).setTriggerContentMaxDelayMs(value);
                            return this;
                        }

                        public Builder clearTriggerContentMaxDelayMs() {
                            copyOnWrite();
                            ((JobInstance) this.instance).clearTriggerContentMaxDelayMs();
                            return this;
                        }

                        @Override // com.android.server.job.StateControllerProto.ContentObserverController.Observer.TriggerContentData.JobInstanceOrBuilder
                        public List<String> getChangedAuthoritiesList() {
                            return Collections.unmodifiableList(((JobInstance) this.instance).getChangedAuthoritiesList());
                        }

                        @Override // com.android.server.job.StateControllerProto.ContentObserverController.Observer.TriggerContentData.JobInstanceOrBuilder
                        public int getChangedAuthoritiesCount() {
                            return ((JobInstance) this.instance).getChangedAuthoritiesCount();
                        }

                        @Override // com.android.server.job.StateControllerProto.ContentObserverController.Observer.TriggerContentData.JobInstanceOrBuilder
                        public String getChangedAuthorities(int index) {
                            return ((JobInstance) this.instance).getChangedAuthorities(index);
                        }

                        @Override // com.android.server.job.StateControllerProto.ContentObserverController.Observer.TriggerContentData.JobInstanceOrBuilder
                        public ByteString getChangedAuthoritiesBytes(int index) {
                            return ((JobInstance) this.instance).getChangedAuthoritiesBytes(index);
                        }

                        public Builder setChangedAuthorities(int index, String value) {
                            copyOnWrite();
                            ((JobInstance) this.instance).setChangedAuthorities(index, value);
                            return this;
                        }

                        public Builder addChangedAuthorities(String value) {
                            copyOnWrite();
                            ((JobInstance) this.instance).addChangedAuthorities(value);
                            return this;
                        }

                        public Builder addAllChangedAuthorities(Iterable<String> values) {
                            copyOnWrite();
                            ((JobInstance) this.instance).addAllChangedAuthorities(values);
                            return this;
                        }

                        public Builder clearChangedAuthorities() {
                            copyOnWrite();
                            ((JobInstance) this.instance).clearChangedAuthorities();
                            return this;
                        }

                        public Builder addChangedAuthoritiesBytes(ByteString value) {
                            copyOnWrite();
                            ((JobInstance) this.instance).addChangedAuthoritiesBytes(value);
                            return this;
                        }

                        @Override // com.android.server.job.StateControllerProto.ContentObserverController.Observer.TriggerContentData.JobInstanceOrBuilder
                        public List<String> getChangedUrisList() {
                            return Collections.unmodifiableList(((JobInstance) this.instance).getChangedUrisList());
                        }

                        @Override // com.android.server.job.StateControllerProto.ContentObserverController.Observer.TriggerContentData.JobInstanceOrBuilder
                        public int getChangedUrisCount() {
                            return ((JobInstance) this.instance).getChangedUrisCount();
                        }

                        @Override // com.android.server.job.StateControllerProto.ContentObserverController.Observer.TriggerContentData.JobInstanceOrBuilder
                        public String getChangedUris(int index) {
                            return ((JobInstance) this.instance).getChangedUris(index);
                        }

                        @Override // com.android.server.job.StateControllerProto.ContentObserverController.Observer.TriggerContentData.JobInstanceOrBuilder
                        public ByteString getChangedUrisBytes(int index) {
                            return ((JobInstance) this.instance).getChangedUrisBytes(index);
                        }

                        public Builder setChangedUris(int index, String value) {
                            copyOnWrite();
                            ((JobInstance) this.instance).setChangedUris(index, value);
                            return this;
                        }

                        public Builder addChangedUris(String value) {
                            copyOnWrite();
                            ((JobInstance) this.instance).addChangedUris(value);
                            return this;
                        }

                        public Builder addAllChangedUris(Iterable<String> values) {
                            copyOnWrite();
                            ((JobInstance) this.instance).addAllChangedUris(values);
                            return this;
                        }

                        public Builder clearChangedUris() {
                            copyOnWrite();
                            ((JobInstance) this.instance).clearChangedUris();
                            return this;
                        }

                        public Builder addChangedUrisBytes(ByteString value) {
                            copyOnWrite();
                            ((JobInstance) this.instance).addChangedUrisBytes(value);
                            return this;
                        }
                    }

                    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
                    /* access modifiers changed from: protected */
                    @Override // com.google.protobuf.GeneratedMessageLite
                    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
                        switch (method) {
                            case NEW_MUTABLE_INSTANCE:
                                return new JobInstance();
                            case IS_INITIALIZED:
                                return DEFAULT_INSTANCE;
                            case MAKE_IMMUTABLE:
                                this.changedAuthorities_.makeImmutable();
                                this.changedUris_.makeImmutable();
                                return null;
                            case NEW_BUILDER:
                                return new Builder();
                            case VISIT:
                                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                                JobInstance other = (JobInstance) arg1;
                                this.info_ = (JobStatusShortInfoProto) visitor.visitMessage(this.info_, other.info_);
                                this.sourceUid_ = visitor.visitInt(hasSourceUid(), this.sourceUid_, other.hasSourceUid(), other.sourceUid_);
                                this.triggerContentUpdateDelayMs_ = visitor.visitLong(hasTriggerContentUpdateDelayMs(), this.triggerContentUpdateDelayMs_, other.hasTriggerContentUpdateDelayMs(), other.triggerContentUpdateDelayMs_);
                                this.triggerContentMaxDelayMs_ = visitor.visitLong(hasTriggerContentMaxDelayMs(), this.triggerContentMaxDelayMs_, other.hasTriggerContentMaxDelayMs(), other.triggerContentMaxDelayMs_);
                                this.changedAuthorities_ = visitor.visitList(this.changedAuthorities_, other.changedAuthorities_);
                                this.changedUris_ = visitor.visitList(this.changedUris_, other.changedUris_);
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
                                            this.sourceUid_ = input.readInt32();
                                        } else if (tag == 24) {
                                            this.bitField0_ |= 4;
                                            this.triggerContentUpdateDelayMs_ = input.readInt64();
                                        } else if (tag == 32) {
                                            this.bitField0_ |= 8;
                                            this.triggerContentMaxDelayMs_ = input.readInt64();
                                        } else if (tag == 42) {
                                            String s = input.readString();
                                            if (!this.changedAuthorities_.isModifiable()) {
                                                this.changedAuthorities_ = GeneratedMessageLite.mutableCopy(this.changedAuthorities_);
                                            }
                                            this.changedAuthorities_.add(s);
                                        } else if (tag == 50) {
                                            String s2 = input.readString();
                                            if (!this.changedUris_.isModifiable()) {
                                                this.changedUris_ = GeneratedMessageLite.mutableCopy(this.changedUris_);
                                            }
                                            this.changedUris_.add(s2);
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
                                    synchronized (JobInstance.class) {
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

                    public static JobInstance getDefaultInstance() {
                        return DEFAULT_INSTANCE;
                    }

                    public static Parser<JobInstance> parser() {
                        return DEFAULT_INSTANCE.getParserForType();
                    }
                }

                @Override // com.android.server.job.StateControllerProto.ContentObserverController.Observer.TriggerContentDataOrBuilder
                public boolean hasUri() {
                    return (this.bitField0_ & 1) == 1;
                }

                @Override // com.android.server.job.StateControllerProto.ContentObserverController.Observer.TriggerContentDataOrBuilder
                public String getUri() {
                    return this.uri_;
                }

                @Override // com.android.server.job.StateControllerProto.ContentObserverController.Observer.TriggerContentDataOrBuilder
                public ByteString getUriBytes() {
                    return ByteString.copyFromUtf8(this.uri_);
                }

                /* access modifiers changed from: private */
                /* access modifiers changed from: public */
                private void setUri(String value) {
                    if (value != null) {
                        this.bitField0_ |= 1;
                        this.uri_ = value;
                        return;
                    }
                    throw new NullPointerException();
                }

                /* access modifiers changed from: private */
                /* access modifiers changed from: public */
                private void clearUri() {
                    this.bitField0_ &= -2;
                    this.uri_ = getDefaultInstance().getUri();
                }

                /* access modifiers changed from: private */
                /* access modifiers changed from: public */
                private void setUriBytes(ByteString value) {
                    if (value != null) {
                        this.bitField0_ |= 1;
                        this.uri_ = value.toStringUtf8();
                        return;
                    }
                    throw new NullPointerException();
                }

                @Override // com.android.server.job.StateControllerProto.ContentObserverController.Observer.TriggerContentDataOrBuilder
                public boolean hasFlags() {
                    return (this.bitField0_ & 2) == 2;
                }

                @Override // com.android.server.job.StateControllerProto.ContentObserverController.Observer.TriggerContentDataOrBuilder
                public int getFlags() {
                    return this.flags_;
                }

                /* access modifiers changed from: private */
                /* access modifiers changed from: public */
                private void setFlags(int value) {
                    this.bitField0_ |= 2;
                    this.flags_ = value;
                }

                /* access modifiers changed from: private */
                /* access modifiers changed from: public */
                private void clearFlags() {
                    this.bitField0_ &= -3;
                    this.flags_ = 0;
                }

                @Override // com.android.server.job.StateControllerProto.ContentObserverController.Observer.TriggerContentDataOrBuilder
                public List<JobInstance> getJobsList() {
                    return this.jobs_;
                }

                public List<? extends JobInstanceOrBuilder> getJobsOrBuilderList() {
                    return this.jobs_;
                }

                @Override // com.android.server.job.StateControllerProto.ContentObserverController.Observer.TriggerContentDataOrBuilder
                public int getJobsCount() {
                    return this.jobs_.size();
                }

                @Override // com.android.server.job.StateControllerProto.ContentObserverController.Observer.TriggerContentDataOrBuilder
                public JobInstance getJobs(int index) {
                    return this.jobs_.get(index);
                }

                public JobInstanceOrBuilder getJobsOrBuilder(int index) {
                    return this.jobs_.get(index);
                }

                private void ensureJobsIsMutable() {
                    if (!this.jobs_.isModifiable()) {
                        this.jobs_ = GeneratedMessageLite.mutableCopy(this.jobs_);
                    }
                }

                /* access modifiers changed from: private */
                /* access modifiers changed from: public */
                private void setJobs(int index, JobInstance value) {
                    if (value != null) {
                        ensureJobsIsMutable();
                        this.jobs_.set(index, value);
                        return;
                    }
                    throw new NullPointerException();
                }

                /* access modifiers changed from: private */
                /* access modifiers changed from: public */
                private void setJobs(int index, JobInstance.Builder builderForValue) {
                    ensureJobsIsMutable();
                    this.jobs_.set(index, (JobInstance) builderForValue.build());
                }

                /* access modifiers changed from: private */
                /* access modifiers changed from: public */
                private void addJobs(JobInstance value) {
                    if (value != null) {
                        ensureJobsIsMutable();
                        this.jobs_.add(value);
                        return;
                    }
                    throw new NullPointerException();
                }

                /* access modifiers changed from: private */
                /* access modifiers changed from: public */
                private void addJobs(int index, JobInstance value) {
                    if (value != null) {
                        ensureJobsIsMutable();
                        this.jobs_.add(index, value);
                        return;
                    }
                    throw new NullPointerException();
                }

                /* access modifiers changed from: private */
                /* access modifiers changed from: public */
                private void addJobs(JobInstance.Builder builderForValue) {
                    ensureJobsIsMutable();
                    this.jobs_.add((JobInstance) builderForValue.build());
                }

                /* access modifiers changed from: private */
                /* access modifiers changed from: public */
                private void addJobs(int index, JobInstance.Builder builderForValue) {
                    ensureJobsIsMutable();
                    this.jobs_.add(index, (JobInstance) builderForValue.build());
                }

                /* access modifiers changed from: private */
                /* access modifiers changed from: public */
                private void addAllJobs(Iterable<? extends JobInstance> values) {
                    ensureJobsIsMutable();
                    AbstractMessageLite.addAll(values, this.jobs_);
                }

                /* access modifiers changed from: private */
                /* access modifiers changed from: public */
                private void clearJobs() {
                    this.jobs_ = emptyProtobufList();
                }

                /* access modifiers changed from: private */
                /* access modifiers changed from: public */
                private void removeJobs(int index) {
                    ensureJobsIsMutable();
                    this.jobs_.remove(index);
                }

                @Override // com.google.protobuf.MessageLite
                public void writeTo(CodedOutputStream output) throws IOException {
                    if ((this.bitField0_ & 1) == 1) {
                        output.writeString(1, getUri());
                    }
                    if ((this.bitField0_ & 2) == 2) {
                        output.writeInt32(2, this.flags_);
                    }
                    for (int i = 0; i < this.jobs_.size(); i++) {
                        output.writeMessage(3, this.jobs_.get(i));
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
                        size2 = 0 + CodedOutputStream.computeStringSize(1, getUri());
                    }
                    if ((this.bitField0_ & 2) == 2) {
                        size2 += CodedOutputStream.computeInt32Size(2, this.flags_);
                    }
                    for (int i = 0; i < this.jobs_.size(); i++) {
                        size2 += CodedOutputStream.computeMessageSize(3, this.jobs_.get(i));
                    }
                    int size3 = size2 + this.unknownFields.getSerializedSize();
                    this.memoizedSerializedSize = size3;
                    return size3;
                }

                public static TriggerContentData parseFrom(ByteString data) throws InvalidProtocolBufferException {
                    return (TriggerContentData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
                }

                public static TriggerContentData parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                    return (TriggerContentData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
                }

                public static TriggerContentData parseFrom(byte[] data) throws InvalidProtocolBufferException {
                    return (TriggerContentData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
                }

                public static TriggerContentData parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                    return (TriggerContentData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
                }

                public static TriggerContentData parseFrom(InputStream input) throws IOException {
                    return (TriggerContentData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
                }

                public static TriggerContentData parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                    return (TriggerContentData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
                }

                public static TriggerContentData parseDelimitedFrom(InputStream input) throws IOException {
                    return (TriggerContentData) parseDelimitedFrom(DEFAULT_INSTANCE, input);
                }

                public static TriggerContentData parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                    return (TriggerContentData) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
                }

                public static TriggerContentData parseFrom(CodedInputStream input) throws IOException {
                    return (TriggerContentData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
                }

                public static TriggerContentData parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                    return (TriggerContentData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
                }

                public static Builder newBuilder() {
                    return (Builder) DEFAULT_INSTANCE.toBuilder();
                }

                public static Builder newBuilder(TriggerContentData prototype) {
                    return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
                }

                public static final class Builder extends GeneratedMessageLite.Builder<TriggerContentData, Builder> implements TriggerContentDataOrBuilder {
                    private Builder() {
                        super(TriggerContentData.DEFAULT_INSTANCE);
                    }

                    @Override // com.android.server.job.StateControllerProto.ContentObserverController.Observer.TriggerContentDataOrBuilder
                    public boolean hasUri() {
                        return ((TriggerContentData) this.instance).hasUri();
                    }

                    @Override // com.android.server.job.StateControllerProto.ContentObserverController.Observer.TriggerContentDataOrBuilder
                    public String getUri() {
                        return ((TriggerContentData) this.instance).getUri();
                    }

                    @Override // com.android.server.job.StateControllerProto.ContentObserverController.Observer.TriggerContentDataOrBuilder
                    public ByteString getUriBytes() {
                        return ((TriggerContentData) this.instance).getUriBytes();
                    }

                    public Builder setUri(String value) {
                        copyOnWrite();
                        ((TriggerContentData) this.instance).setUri(value);
                        return this;
                    }

                    public Builder clearUri() {
                        copyOnWrite();
                        ((TriggerContentData) this.instance).clearUri();
                        return this;
                    }

                    public Builder setUriBytes(ByteString value) {
                        copyOnWrite();
                        ((TriggerContentData) this.instance).setUriBytes(value);
                        return this;
                    }

                    @Override // com.android.server.job.StateControllerProto.ContentObserverController.Observer.TriggerContentDataOrBuilder
                    public boolean hasFlags() {
                        return ((TriggerContentData) this.instance).hasFlags();
                    }

                    @Override // com.android.server.job.StateControllerProto.ContentObserverController.Observer.TriggerContentDataOrBuilder
                    public int getFlags() {
                        return ((TriggerContentData) this.instance).getFlags();
                    }

                    public Builder setFlags(int value) {
                        copyOnWrite();
                        ((TriggerContentData) this.instance).setFlags(value);
                        return this;
                    }

                    public Builder clearFlags() {
                        copyOnWrite();
                        ((TriggerContentData) this.instance).clearFlags();
                        return this;
                    }

                    @Override // com.android.server.job.StateControllerProto.ContentObserverController.Observer.TriggerContentDataOrBuilder
                    public List<JobInstance> getJobsList() {
                        return Collections.unmodifiableList(((TriggerContentData) this.instance).getJobsList());
                    }

                    @Override // com.android.server.job.StateControllerProto.ContentObserverController.Observer.TriggerContentDataOrBuilder
                    public int getJobsCount() {
                        return ((TriggerContentData) this.instance).getJobsCount();
                    }

                    @Override // com.android.server.job.StateControllerProto.ContentObserverController.Observer.TriggerContentDataOrBuilder
                    public JobInstance getJobs(int index) {
                        return ((TriggerContentData) this.instance).getJobs(index);
                    }

                    public Builder setJobs(int index, JobInstance value) {
                        copyOnWrite();
                        ((TriggerContentData) this.instance).setJobs((TriggerContentData) index, (int) value);
                        return this;
                    }

                    public Builder setJobs(int index, JobInstance.Builder builderForValue) {
                        copyOnWrite();
                        ((TriggerContentData) this.instance).setJobs((TriggerContentData) index, (int) builderForValue);
                        return this;
                    }

                    public Builder addJobs(JobInstance value) {
                        copyOnWrite();
                        ((TriggerContentData) this.instance).addJobs((TriggerContentData) value);
                        return this;
                    }

                    public Builder addJobs(int index, JobInstance value) {
                        copyOnWrite();
                        ((TriggerContentData) this.instance).addJobs((TriggerContentData) index, (int) value);
                        return this;
                    }

                    public Builder addJobs(JobInstance.Builder builderForValue) {
                        copyOnWrite();
                        ((TriggerContentData) this.instance).addJobs((TriggerContentData) builderForValue);
                        return this;
                    }

                    public Builder addJobs(int index, JobInstance.Builder builderForValue) {
                        copyOnWrite();
                        ((TriggerContentData) this.instance).addJobs((TriggerContentData) index, (int) builderForValue);
                        return this;
                    }

                    public Builder addAllJobs(Iterable<? extends JobInstance> values) {
                        copyOnWrite();
                        ((TriggerContentData) this.instance).addAllJobs(values);
                        return this;
                    }

                    public Builder clearJobs() {
                        copyOnWrite();
                        ((TriggerContentData) this.instance).clearJobs();
                        return this;
                    }

                    public Builder removeJobs(int index) {
                        copyOnWrite();
                        ((TriggerContentData) this.instance).removeJobs(index);
                        return this;
                    }
                }

                /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
                /* access modifiers changed from: protected */
                @Override // com.google.protobuf.GeneratedMessageLite
                public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
                    switch (method) {
                        case NEW_MUTABLE_INSTANCE:
                            return new TriggerContentData();
                        case IS_INITIALIZED:
                            return DEFAULT_INSTANCE;
                        case MAKE_IMMUTABLE:
                            this.jobs_.makeImmutable();
                            return null;
                        case NEW_BUILDER:
                            return new Builder();
                        case VISIT:
                            GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                            TriggerContentData other = (TriggerContentData) arg1;
                            this.uri_ = visitor.visitString(hasUri(), this.uri_, other.hasUri(), other.uri_);
                            this.flags_ = visitor.visitInt(hasFlags(), this.flags_, other.hasFlags(), other.flags_);
                            this.jobs_ = visitor.visitList(this.jobs_, other.jobs_);
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
                                        String s = input.readString();
                                        this.bitField0_ |= 1;
                                        this.uri_ = s;
                                    } else if (tag == 16) {
                                        this.bitField0_ |= 2;
                                        this.flags_ = input.readInt32();
                                    } else if (tag == 26) {
                                        if (!this.jobs_.isModifiable()) {
                                            this.jobs_ = GeneratedMessageLite.mutableCopy(this.jobs_);
                                        }
                                        this.jobs_.add((JobInstance) input.readMessage(JobInstance.parser(), extensionRegistry));
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
                                synchronized (TriggerContentData.class) {
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

                public static TriggerContentData getDefaultInstance() {
                    return DEFAULT_INSTANCE;
                }

                public static Parser<TriggerContentData> parser() {
                    return DEFAULT_INSTANCE.getParserForType();
                }
            }

            @Override // com.android.server.job.StateControllerProto.ContentObserverController.ObserverOrBuilder
            public boolean hasUserId() {
                return (this.bitField0_ & 1) == 1;
            }

            @Override // com.android.server.job.StateControllerProto.ContentObserverController.ObserverOrBuilder
            public int getUserId() {
                return this.userId_;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void setUserId(int value) {
                this.bitField0_ |= 1;
                this.userId_ = value;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void clearUserId() {
                this.bitField0_ &= -2;
                this.userId_ = 0;
            }

            @Override // com.android.server.job.StateControllerProto.ContentObserverController.ObserverOrBuilder
            public List<TriggerContentData> getTriggersList() {
                return this.triggers_;
            }

            public List<? extends TriggerContentDataOrBuilder> getTriggersOrBuilderList() {
                return this.triggers_;
            }

            @Override // com.android.server.job.StateControllerProto.ContentObserverController.ObserverOrBuilder
            public int getTriggersCount() {
                return this.triggers_.size();
            }

            @Override // com.android.server.job.StateControllerProto.ContentObserverController.ObserverOrBuilder
            public TriggerContentData getTriggers(int index) {
                return this.triggers_.get(index);
            }

            public TriggerContentDataOrBuilder getTriggersOrBuilder(int index) {
                return this.triggers_.get(index);
            }

            private void ensureTriggersIsMutable() {
                if (!this.triggers_.isModifiable()) {
                    this.triggers_ = GeneratedMessageLite.mutableCopy(this.triggers_);
                }
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void setTriggers(int index, TriggerContentData value) {
                if (value != null) {
                    ensureTriggersIsMutable();
                    this.triggers_.set(index, value);
                    return;
                }
                throw new NullPointerException();
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void setTriggers(int index, TriggerContentData.Builder builderForValue) {
                ensureTriggersIsMutable();
                this.triggers_.set(index, (TriggerContentData) builderForValue.build());
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void addTriggers(TriggerContentData value) {
                if (value != null) {
                    ensureTriggersIsMutable();
                    this.triggers_.add(value);
                    return;
                }
                throw new NullPointerException();
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void addTriggers(int index, TriggerContentData value) {
                if (value != null) {
                    ensureTriggersIsMutable();
                    this.triggers_.add(index, value);
                    return;
                }
                throw new NullPointerException();
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void addTriggers(TriggerContentData.Builder builderForValue) {
                ensureTriggersIsMutable();
                this.triggers_.add((TriggerContentData) builderForValue.build());
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void addTriggers(int index, TriggerContentData.Builder builderForValue) {
                ensureTriggersIsMutable();
                this.triggers_.add(index, (TriggerContentData) builderForValue.build());
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void addAllTriggers(Iterable<? extends TriggerContentData> values) {
                ensureTriggersIsMutable();
                AbstractMessageLite.addAll(values, this.triggers_);
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void clearTriggers() {
                this.triggers_ = emptyProtobufList();
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void removeTriggers(int index) {
                ensureTriggersIsMutable();
                this.triggers_.remove(index);
            }

            @Override // com.google.protobuf.MessageLite
            public void writeTo(CodedOutputStream output) throws IOException {
                if ((this.bitField0_ & 1) == 1) {
                    output.writeInt32(1, this.userId_);
                }
                for (int i = 0; i < this.triggers_.size(); i++) {
                    output.writeMessage(2, this.triggers_.get(i));
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
                    size2 = 0 + CodedOutputStream.computeInt32Size(1, this.userId_);
                }
                for (int i = 0; i < this.triggers_.size(); i++) {
                    size2 += CodedOutputStream.computeMessageSize(2, this.triggers_.get(i));
                }
                int size3 = size2 + this.unknownFields.getSerializedSize();
                this.memoizedSerializedSize = size3;
                return size3;
            }

            public static Observer parseFrom(ByteString data) throws InvalidProtocolBufferException {
                return (Observer) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
            }

            public static Observer parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return (Observer) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
            }

            public static Observer parseFrom(byte[] data) throws InvalidProtocolBufferException {
                return (Observer) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
            }

            public static Observer parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return (Observer) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
            }

            public static Observer parseFrom(InputStream input) throws IOException {
                return (Observer) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
            }

            public static Observer parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (Observer) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static Observer parseDelimitedFrom(InputStream input) throws IOException {
                return (Observer) parseDelimitedFrom(DEFAULT_INSTANCE, input);
            }

            public static Observer parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (Observer) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static Observer parseFrom(CodedInputStream input) throws IOException {
                return (Observer) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
            }

            public static Observer parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (Observer) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static Builder newBuilder() {
                return (Builder) DEFAULT_INSTANCE.toBuilder();
            }

            public static Builder newBuilder(Observer prototype) {
                return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
            }

            public static final class Builder extends GeneratedMessageLite.Builder<Observer, Builder> implements ObserverOrBuilder {
                private Builder() {
                    super(Observer.DEFAULT_INSTANCE);
                }

                @Override // com.android.server.job.StateControllerProto.ContentObserverController.ObserverOrBuilder
                public boolean hasUserId() {
                    return ((Observer) this.instance).hasUserId();
                }

                @Override // com.android.server.job.StateControllerProto.ContentObserverController.ObserverOrBuilder
                public int getUserId() {
                    return ((Observer) this.instance).getUserId();
                }

                public Builder setUserId(int value) {
                    copyOnWrite();
                    ((Observer) this.instance).setUserId(value);
                    return this;
                }

                public Builder clearUserId() {
                    copyOnWrite();
                    ((Observer) this.instance).clearUserId();
                    return this;
                }

                @Override // com.android.server.job.StateControllerProto.ContentObserverController.ObserverOrBuilder
                public List<TriggerContentData> getTriggersList() {
                    return Collections.unmodifiableList(((Observer) this.instance).getTriggersList());
                }

                @Override // com.android.server.job.StateControllerProto.ContentObserverController.ObserverOrBuilder
                public int getTriggersCount() {
                    return ((Observer) this.instance).getTriggersCount();
                }

                @Override // com.android.server.job.StateControllerProto.ContentObserverController.ObserverOrBuilder
                public TriggerContentData getTriggers(int index) {
                    return ((Observer) this.instance).getTriggers(index);
                }

                public Builder setTriggers(int index, TriggerContentData value) {
                    copyOnWrite();
                    ((Observer) this.instance).setTriggers((Observer) index, (int) value);
                    return this;
                }

                public Builder setTriggers(int index, TriggerContentData.Builder builderForValue) {
                    copyOnWrite();
                    ((Observer) this.instance).setTriggers((Observer) index, (int) builderForValue);
                    return this;
                }

                public Builder addTriggers(TriggerContentData value) {
                    copyOnWrite();
                    ((Observer) this.instance).addTriggers((Observer) value);
                    return this;
                }

                public Builder addTriggers(int index, TriggerContentData value) {
                    copyOnWrite();
                    ((Observer) this.instance).addTriggers((Observer) index, (int) value);
                    return this;
                }

                public Builder addTriggers(TriggerContentData.Builder builderForValue) {
                    copyOnWrite();
                    ((Observer) this.instance).addTriggers((Observer) builderForValue);
                    return this;
                }

                public Builder addTriggers(int index, TriggerContentData.Builder builderForValue) {
                    copyOnWrite();
                    ((Observer) this.instance).addTriggers((Observer) index, (int) builderForValue);
                    return this;
                }

                public Builder addAllTriggers(Iterable<? extends TriggerContentData> values) {
                    copyOnWrite();
                    ((Observer) this.instance).addAllTriggers(values);
                    return this;
                }

                public Builder clearTriggers() {
                    copyOnWrite();
                    ((Observer) this.instance).clearTriggers();
                    return this;
                }

                public Builder removeTriggers(int index) {
                    copyOnWrite();
                    ((Observer) this.instance).removeTriggers(index);
                    return this;
                }
            }

            /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
            /* access modifiers changed from: protected */
            @Override // com.google.protobuf.GeneratedMessageLite
            public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
                switch (method) {
                    case NEW_MUTABLE_INSTANCE:
                        return new Observer();
                    case IS_INITIALIZED:
                        return DEFAULT_INSTANCE;
                    case MAKE_IMMUTABLE:
                        this.triggers_.makeImmutable();
                        return null;
                    case NEW_BUILDER:
                        return new Builder();
                    case VISIT:
                        GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                        Observer other = (Observer) arg1;
                        this.userId_ = visitor.visitInt(hasUserId(), this.userId_, other.hasUserId(), other.userId_);
                        this.triggers_ = visitor.visitList(this.triggers_, other.triggers_);
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
                                    this.userId_ = input.readInt32();
                                } else if (tag == 18) {
                                    if (!this.triggers_.isModifiable()) {
                                        this.triggers_ = GeneratedMessageLite.mutableCopy(this.triggers_);
                                    }
                                    this.triggers_.add((TriggerContentData) input.readMessage(TriggerContentData.parser(), extensionRegistry));
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
                            synchronized (Observer.class) {
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

            public static Observer getDefaultInstance() {
                return DEFAULT_INSTANCE;
            }

            public static Parser<Observer> parser() {
                return DEFAULT_INSTANCE.getParserForType();
            }
        }

        @Override // com.android.server.job.StateControllerProto.ContentObserverControllerOrBuilder
        public List<TrackedJob> getTrackedJobsList() {
            return this.trackedJobs_;
        }

        public List<? extends TrackedJobOrBuilder> getTrackedJobsOrBuilderList() {
            return this.trackedJobs_;
        }

        @Override // com.android.server.job.StateControllerProto.ContentObserverControllerOrBuilder
        public int getTrackedJobsCount() {
            return this.trackedJobs_.size();
        }

        @Override // com.android.server.job.StateControllerProto.ContentObserverControllerOrBuilder
        public TrackedJob getTrackedJobs(int index) {
            return this.trackedJobs_.get(index);
        }

        public TrackedJobOrBuilder getTrackedJobsOrBuilder(int index) {
            return this.trackedJobs_.get(index);
        }

        private void ensureTrackedJobsIsMutable() {
            if (!this.trackedJobs_.isModifiable()) {
                this.trackedJobs_ = GeneratedMessageLite.mutableCopy(this.trackedJobs_);
            }
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setTrackedJobs(int index, TrackedJob value) {
            if (value != null) {
                ensureTrackedJobsIsMutable();
                this.trackedJobs_.set(index, value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setTrackedJobs(int index, TrackedJob.Builder builderForValue) {
            ensureTrackedJobsIsMutable();
            this.trackedJobs_.set(index, (TrackedJob) builderForValue.build());
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addTrackedJobs(TrackedJob value) {
            if (value != null) {
                ensureTrackedJobsIsMutable();
                this.trackedJobs_.add(value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addTrackedJobs(int index, TrackedJob value) {
            if (value != null) {
                ensureTrackedJobsIsMutable();
                this.trackedJobs_.add(index, value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addTrackedJobs(TrackedJob.Builder builderForValue) {
            ensureTrackedJobsIsMutable();
            this.trackedJobs_.add((TrackedJob) builderForValue.build());
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addTrackedJobs(int index, TrackedJob.Builder builderForValue) {
            ensureTrackedJobsIsMutable();
            this.trackedJobs_.add(index, (TrackedJob) builderForValue.build());
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addAllTrackedJobs(Iterable<? extends TrackedJob> values) {
            ensureTrackedJobsIsMutable();
            AbstractMessageLite.addAll(values, this.trackedJobs_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearTrackedJobs() {
            this.trackedJobs_ = emptyProtobufList();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void removeTrackedJobs(int index) {
            ensureTrackedJobsIsMutable();
            this.trackedJobs_.remove(index);
        }

        @Override // com.android.server.job.StateControllerProto.ContentObserverControllerOrBuilder
        public List<Observer> getObserversList() {
            return this.observers_;
        }

        public List<? extends ObserverOrBuilder> getObserversOrBuilderList() {
            return this.observers_;
        }

        @Override // com.android.server.job.StateControllerProto.ContentObserverControllerOrBuilder
        public int getObserversCount() {
            return this.observers_.size();
        }

        @Override // com.android.server.job.StateControllerProto.ContentObserverControllerOrBuilder
        public Observer getObservers(int index) {
            return this.observers_.get(index);
        }

        public ObserverOrBuilder getObserversOrBuilder(int index) {
            return this.observers_.get(index);
        }

        private void ensureObserversIsMutable() {
            if (!this.observers_.isModifiable()) {
                this.observers_ = GeneratedMessageLite.mutableCopy(this.observers_);
            }
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setObservers(int index, Observer value) {
            if (value != null) {
                ensureObserversIsMutable();
                this.observers_.set(index, value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setObservers(int index, Observer.Builder builderForValue) {
            ensureObserversIsMutable();
            this.observers_.set(index, (Observer) builderForValue.build());
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addObservers(Observer value) {
            if (value != null) {
                ensureObserversIsMutable();
                this.observers_.add(value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addObservers(int index, Observer value) {
            if (value != null) {
                ensureObserversIsMutable();
                this.observers_.add(index, value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addObservers(Observer.Builder builderForValue) {
            ensureObserversIsMutable();
            this.observers_.add((Observer) builderForValue.build());
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addObservers(int index, Observer.Builder builderForValue) {
            ensureObserversIsMutable();
            this.observers_.add(index, (Observer) builderForValue.build());
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addAllObservers(Iterable<? extends Observer> values) {
            ensureObserversIsMutable();
            AbstractMessageLite.addAll(values, this.observers_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearObservers() {
            this.observers_ = emptyProtobufList();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void removeObservers(int index) {
            ensureObserversIsMutable();
            this.observers_.remove(index);
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            for (int i = 0; i < this.trackedJobs_.size(); i++) {
                output.writeMessage(1, this.trackedJobs_.get(i));
            }
            for (int i2 = 0; i2 < this.observers_.size(); i2++) {
                output.writeMessage(2, this.observers_.get(i2));
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
            for (int i = 0; i < this.trackedJobs_.size(); i++) {
                size2 += CodedOutputStream.computeMessageSize(1, this.trackedJobs_.get(i));
            }
            for (int i2 = 0; i2 < this.observers_.size(); i2++) {
                size2 += CodedOutputStream.computeMessageSize(2, this.observers_.get(i2));
            }
            int size3 = size2 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static ContentObserverController parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (ContentObserverController) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static ContentObserverController parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (ContentObserverController) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static ContentObserverController parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (ContentObserverController) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static ContentObserverController parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (ContentObserverController) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static ContentObserverController parseFrom(InputStream input) throws IOException {
            return (ContentObserverController) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static ContentObserverController parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (ContentObserverController) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static ContentObserverController parseDelimitedFrom(InputStream input) throws IOException {
            return (ContentObserverController) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static ContentObserverController parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (ContentObserverController) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static ContentObserverController parseFrom(CodedInputStream input) throws IOException {
            return (ContentObserverController) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static ContentObserverController parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (ContentObserverController) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(ContentObserverController prototype) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<ContentObserverController, Builder> implements ContentObserverControllerOrBuilder {
            private Builder() {
                super(ContentObserverController.DEFAULT_INSTANCE);
            }

            @Override // com.android.server.job.StateControllerProto.ContentObserverControllerOrBuilder
            public List<TrackedJob> getTrackedJobsList() {
                return Collections.unmodifiableList(((ContentObserverController) this.instance).getTrackedJobsList());
            }

            @Override // com.android.server.job.StateControllerProto.ContentObserverControllerOrBuilder
            public int getTrackedJobsCount() {
                return ((ContentObserverController) this.instance).getTrackedJobsCount();
            }

            @Override // com.android.server.job.StateControllerProto.ContentObserverControllerOrBuilder
            public TrackedJob getTrackedJobs(int index) {
                return ((ContentObserverController) this.instance).getTrackedJobs(index);
            }

            public Builder setTrackedJobs(int index, TrackedJob value) {
                copyOnWrite();
                ((ContentObserverController) this.instance).setTrackedJobs((ContentObserverController) index, (int) value);
                return this;
            }

            public Builder setTrackedJobs(int index, TrackedJob.Builder builderForValue) {
                copyOnWrite();
                ((ContentObserverController) this.instance).setTrackedJobs((ContentObserverController) index, (int) builderForValue);
                return this;
            }

            public Builder addTrackedJobs(TrackedJob value) {
                copyOnWrite();
                ((ContentObserverController) this.instance).addTrackedJobs((ContentObserverController) value);
                return this;
            }

            public Builder addTrackedJobs(int index, TrackedJob value) {
                copyOnWrite();
                ((ContentObserverController) this.instance).addTrackedJobs((ContentObserverController) index, (int) value);
                return this;
            }

            public Builder addTrackedJobs(TrackedJob.Builder builderForValue) {
                copyOnWrite();
                ((ContentObserverController) this.instance).addTrackedJobs((ContentObserverController) builderForValue);
                return this;
            }

            public Builder addTrackedJobs(int index, TrackedJob.Builder builderForValue) {
                copyOnWrite();
                ((ContentObserverController) this.instance).addTrackedJobs((ContentObserverController) index, (int) builderForValue);
                return this;
            }

            public Builder addAllTrackedJobs(Iterable<? extends TrackedJob> values) {
                copyOnWrite();
                ((ContentObserverController) this.instance).addAllTrackedJobs(values);
                return this;
            }

            public Builder clearTrackedJobs() {
                copyOnWrite();
                ((ContentObserverController) this.instance).clearTrackedJobs();
                return this;
            }

            public Builder removeTrackedJobs(int index) {
                copyOnWrite();
                ((ContentObserverController) this.instance).removeTrackedJobs(index);
                return this;
            }

            @Override // com.android.server.job.StateControllerProto.ContentObserverControllerOrBuilder
            public List<Observer> getObserversList() {
                return Collections.unmodifiableList(((ContentObserverController) this.instance).getObserversList());
            }

            @Override // com.android.server.job.StateControllerProto.ContentObserverControllerOrBuilder
            public int getObserversCount() {
                return ((ContentObserverController) this.instance).getObserversCount();
            }

            @Override // com.android.server.job.StateControllerProto.ContentObserverControllerOrBuilder
            public Observer getObservers(int index) {
                return ((ContentObserverController) this.instance).getObservers(index);
            }

            public Builder setObservers(int index, Observer value) {
                copyOnWrite();
                ((ContentObserverController) this.instance).setObservers((ContentObserverController) index, (int) value);
                return this;
            }

            public Builder setObservers(int index, Observer.Builder builderForValue) {
                copyOnWrite();
                ((ContentObserverController) this.instance).setObservers((ContentObserverController) index, (int) builderForValue);
                return this;
            }

            public Builder addObservers(Observer value) {
                copyOnWrite();
                ((ContentObserverController) this.instance).addObservers((ContentObserverController) value);
                return this;
            }

            public Builder addObservers(int index, Observer value) {
                copyOnWrite();
                ((ContentObserverController) this.instance).addObservers((ContentObserverController) index, (int) value);
                return this;
            }

            public Builder addObservers(Observer.Builder builderForValue) {
                copyOnWrite();
                ((ContentObserverController) this.instance).addObservers((ContentObserverController) builderForValue);
                return this;
            }

            public Builder addObservers(int index, Observer.Builder builderForValue) {
                copyOnWrite();
                ((ContentObserverController) this.instance).addObservers((ContentObserverController) index, (int) builderForValue);
                return this;
            }

            public Builder addAllObservers(Iterable<? extends Observer> values) {
                copyOnWrite();
                ((ContentObserverController) this.instance).addAllObservers(values);
                return this;
            }

            public Builder clearObservers() {
                copyOnWrite();
                ((ContentObserverController) this.instance).clearObservers();
                return this;
            }

            public Builder removeObservers(int index) {
                copyOnWrite();
                ((ContentObserverController) this.instance).removeObservers(index);
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new ContentObserverController();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    this.trackedJobs_.makeImmutable();
                    this.observers_.makeImmutable();
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    ContentObserverController other = (ContentObserverController) arg1;
                    this.trackedJobs_ = visitor.visitList(this.trackedJobs_, other.trackedJobs_);
                    this.observers_ = visitor.visitList(this.observers_, other.observers_);
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
                                if (!this.trackedJobs_.isModifiable()) {
                                    this.trackedJobs_ = GeneratedMessageLite.mutableCopy(this.trackedJobs_);
                                }
                                this.trackedJobs_.add((TrackedJob) input.readMessage(TrackedJob.parser(), extensionRegistry));
                            } else if (tag == 18) {
                                if (!this.observers_.isModifiable()) {
                                    this.observers_ = GeneratedMessageLite.mutableCopy(this.observers_);
                                }
                                this.observers_.add((Observer) input.readMessage(Observer.parser(), extensionRegistry));
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
                        synchronized (ContentObserverController.class) {
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

        public static ContentObserverController getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<ContentObserverController> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class DeviceIdleJobsController extends GeneratedMessageLite<DeviceIdleJobsController, Builder> implements DeviceIdleJobsControllerOrBuilder {
        private static final DeviceIdleJobsController DEFAULT_INSTANCE = new DeviceIdleJobsController();
        public static final int IS_DEVICE_IDLE_MODE_FIELD_NUMBER = 1;
        private static volatile Parser<DeviceIdleJobsController> PARSER = null;
        public static final int TRACKED_JOBS_FIELD_NUMBER = 2;
        private int bitField0_;
        private boolean isDeviceIdleMode_ = false;
        private Internal.ProtobufList<TrackedJob> trackedJobs_ = emptyProtobufList();

        public interface TrackedJobOrBuilder extends MessageLiteOrBuilder {
            boolean getAreConstraintsSatisfied();

            JobStatusShortInfoProto getInfo();

            boolean getIsAllowedInDoze();

            boolean getIsDozeWhitelisted();

            String getSourcePackageName();

            ByteString getSourcePackageNameBytes();

            int getSourceUid();

            boolean hasAreConstraintsSatisfied();

            boolean hasInfo();

            boolean hasIsAllowedInDoze();

            boolean hasIsDozeWhitelisted();

            boolean hasSourcePackageName();

            boolean hasSourceUid();
        }

        private DeviceIdleJobsController() {
        }

        public static final class TrackedJob extends GeneratedMessageLite<TrackedJob, Builder> implements TrackedJobOrBuilder {
            public static final int ARE_CONSTRAINTS_SATISFIED_FIELD_NUMBER = 4;
            private static final TrackedJob DEFAULT_INSTANCE = new TrackedJob();
            public static final int INFO_FIELD_NUMBER = 1;
            public static final int IS_ALLOWED_IN_DOZE_FIELD_NUMBER = 6;
            public static final int IS_DOZE_WHITELISTED_FIELD_NUMBER = 5;
            private static volatile Parser<TrackedJob> PARSER = null;
            public static final int SOURCE_PACKAGE_NAME_FIELD_NUMBER = 3;
            public static final int SOURCE_UID_FIELD_NUMBER = 2;
            private boolean areConstraintsSatisfied_ = false;
            private int bitField0_;
            private JobStatusShortInfoProto info_;
            private boolean isAllowedInDoze_ = false;
            private boolean isDozeWhitelisted_ = false;
            private String sourcePackageName_ = "";
            private int sourceUid_ = 0;

            private TrackedJob() {
            }

            @Override // com.android.server.job.StateControllerProto.DeviceIdleJobsController.TrackedJobOrBuilder
            public boolean hasInfo() {
                return (this.bitField0_ & 1) == 1;
            }

            @Override // com.android.server.job.StateControllerProto.DeviceIdleJobsController.TrackedJobOrBuilder
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

            @Override // com.android.server.job.StateControllerProto.DeviceIdleJobsController.TrackedJobOrBuilder
            public boolean hasSourceUid() {
                return (this.bitField0_ & 2) == 2;
            }

            @Override // com.android.server.job.StateControllerProto.DeviceIdleJobsController.TrackedJobOrBuilder
            public int getSourceUid() {
                return this.sourceUid_;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void setSourceUid(int value) {
                this.bitField0_ |= 2;
                this.sourceUid_ = value;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void clearSourceUid() {
                this.bitField0_ &= -3;
                this.sourceUid_ = 0;
            }

            @Override // com.android.server.job.StateControllerProto.DeviceIdleJobsController.TrackedJobOrBuilder
            public boolean hasSourcePackageName() {
                return (this.bitField0_ & 4) == 4;
            }

            @Override // com.android.server.job.StateControllerProto.DeviceIdleJobsController.TrackedJobOrBuilder
            public String getSourcePackageName() {
                return this.sourcePackageName_;
            }

            @Override // com.android.server.job.StateControllerProto.DeviceIdleJobsController.TrackedJobOrBuilder
            public ByteString getSourcePackageNameBytes() {
                return ByteString.copyFromUtf8(this.sourcePackageName_);
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void setSourcePackageName(String value) {
                if (value != null) {
                    this.bitField0_ |= 4;
                    this.sourcePackageName_ = value;
                    return;
                }
                throw new NullPointerException();
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void clearSourcePackageName() {
                this.bitField0_ &= -5;
                this.sourcePackageName_ = getDefaultInstance().getSourcePackageName();
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void setSourcePackageNameBytes(ByteString value) {
                if (value != null) {
                    this.bitField0_ |= 4;
                    this.sourcePackageName_ = value.toStringUtf8();
                    return;
                }
                throw new NullPointerException();
            }

            @Override // com.android.server.job.StateControllerProto.DeviceIdleJobsController.TrackedJobOrBuilder
            public boolean hasAreConstraintsSatisfied() {
                return (this.bitField0_ & 8) == 8;
            }

            @Override // com.android.server.job.StateControllerProto.DeviceIdleJobsController.TrackedJobOrBuilder
            public boolean getAreConstraintsSatisfied() {
                return this.areConstraintsSatisfied_;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void setAreConstraintsSatisfied(boolean value) {
                this.bitField0_ |= 8;
                this.areConstraintsSatisfied_ = value;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void clearAreConstraintsSatisfied() {
                this.bitField0_ &= -9;
                this.areConstraintsSatisfied_ = false;
            }

            @Override // com.android.server.job.StateControllerProto.DeviceIdleJobsController.TrackedJobOrBuilder
            public boolean hasIsDozeWhitelisted() {
                return (this.bitField0_ & 16) == 16;
            }

            @Override // com.android.server.job.StateControllerProto.DeviceIdleJobsController.TrackedJobOrBuilder
            public boolean getIsDozeWhitelisted() {
                return this.isDozeWhitelisted_;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void setIsDozeWhitelisted(boolean value) {
                this.bitField0_ |= 16;
                this.isDozeWhitelisted_ = value;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void clearIsDozeWhitelisted() {
                this.bitField0_ &= -17;
                this.isDozeWhitelisted_ = false;
            }

            @Override // com.android.server.job.StateControllerProto.DeviceIdleJobsController.TrackedJobOrBuilder
            public boolean hasIsAllowedInDoze() {
                return (this.bitField0_ & 32) == 32;
            }

            @Override // com.android.server.job.StateControllerProto.DeviceIdleJobsController.TrackedJobOrBuilder
            public boolean getIsAllowedInDoze() {
                return this.isAllowedInDoze_;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void setIsAllowedInDoze(boolean value) {
                this.bitField0_ |= 32;
                this.isAllowedInDoze_ = value;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void clearIsAllowedInDoze() {
                this.bitField0_ &= -33;
                this.isAllowedInDoze_ = false;
            }

            @Override // com.google.protobuf.MessageLite
            public void writeTo(CodedOutputStream output) throws IOException {
                if ((this.bitField0_ & 1) == 1) {
                    output.writeMessage(1, getInfo());
                }
                if ((this.bitField0_ & 2) == 2) {
                    output.writeInt32(2, this.sourceUid_);
                }
                if ((this.bitField0_ & 4) == 4) {
                    output.writeString(3, getSourcePackageName());
                }
                if ((this.bitField0_ & 8) == 8) {
                    output.writeBool(4, this.areConstraintsSatisfied_);
                }
                if ((this.bitField0_ & 16) == 16) {
                    output.writeBool(5, this.isDozeWhitelisted_);
                }
                if ((this.bitField0_ & 32) == 32) {
                    output.writeBool(6, this.isAllowedInDoze_);
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
                    size2 += CodedOutputStream.computeInt32Size(2, this.sourceUid_);
                }
                if ((this.bitField0_ & 4) == 4) {
                    size2 += CodedOutputStream.computeStringSize(3, getSourcePackageName());
                }
                if ((this.bitField0_ & 8) == 8) {
                    size2 += CodedOutputStream.computeBoolSize(4, this.areConstraintsSatisfied_);
                }
                if ((this.bitField0_ & 16) == 16) {
                    size2 += CodedOutputStream.computeBoolSize(5, this.isDozeWhitelisted_);
                }
                if ((this.bitField0_ & 32) == 32) {
                    size2 += CodedOutputStream.computeBoolSize(6, this.isAllowedInDoze_);
                }
                int size3 = size2 + this.unknownFields.getSerializedSize();
                this.memoizedSerializedSize = size3;
                return size3;
            }

            public static TrackedJob parseFrom(ByteString data) throws InvalidProtocolBufferException {
                return (TrackedJob) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
            }

            public static TrackedJob parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return (TrackedJob) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
            }

            public static TrackedJob parseFrom(byte[] data) throws InvalidProtocolBufferException {
                return (TrackedJob) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
            }

            public static TrackedJob parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return (TrackedJob) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
            }

            public static TrackedJob parseFrom(InputStream input) throws IOException {
                return (TrackedJob) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
            }

            public static TrackedJob parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (TrackedJob) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static TrackedJob parseDelimitedFrom(InputStream input) throws IOException {
                return (TrackedJob) parseDelimitedFrom(DEFAULT_INSTANCE, input);
            }

            public static TrackedJob parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (TrackedJob) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static TrackedJob parseFrom(CodedInputStream input) throws IOException {
                return (TrackedJob) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
            }

            public static TrackedJob parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (TrackedJob) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static Builder newBuilder() {
                return (Builder) DEFAULT_INSTANCE.toBuilder();
            }

            public static Builder newBuilder(TrackedJob prototype) {
                return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
            }

            public static final class Builder extends GeneratedMessageLite.Builder<TrackedJob, Builder> implements TrackedJobOrBuilder {
                private Builder() {
                    super(TrackedJob.DEFAULT_INSTANCE);
                }

                @Override // com.android.server.job.StateControllerProto.DeviceIdleJobsController.TrackedJobOrBuilder
                public boolean hasInfo() {
                    return ((TrackedJob) this.instance).hasInfo();
                }

                @Override // com.android.server.job.StateControllerProto.DeviceIdleJobsController.TrackedJobOrBuilder
                public JobStatusShortInfoProto getInfo() {
                    return ((TrackedJob) this.instance).getInfo();
                }

                public Builder setInfo(JobStatusShortInfoProto value) {
                    copyOnWrite();
                    ((TrackedJob) this.instance).setInfo((TrackedJob) value);
                    return this;
                }

                public Builder setInfo(JobStatusShortInfoProto.Builder builderForValue) {
                    copyOnWrite();
                    ((TrackedJob) this.instance).setInfo((TrackedJob) builderForValue);
                    return this;
                }

                public Builder mergeInfo(JobStatusShortInfoProto value) {
                    copyOnWrite();
                    ((TrackedJob) this.instance).mergeInfo(value);
                    return this;
                }

                public Builder clearInfo() {
                    copyOnWrite();
                    ((TrackedJob) this.instance).clearInfo();
                    return this;
                }

                @Override // com.android.server.job.StateControllerProto.DeviceIdleJobsController.TrackedJobOrBuilder
                public boolean hasSourceUid() {
                    return ((TrackedJob) this.instance).hasSourceUid();
                }

                @Override // com.android.server.job.StateControllerProto.DeviceIdleJobsController.TrackedJobOrBuilder
                public int getSourceUid() {
                    return ((TrackedJob) this.instance).getSourceUid();
                }

                public Builder setSourceUid(int value) {
                    copyOnWrite();
                    ((TrackedJob) this.instance).setSourceUid(value);
                    return this;
                }

                public Builder clearSourceUid() {
                    copyOnWrite();
                    ((TrackedJob) this.instance).clearSourceUid();
                    return this;
                }

                @Override // com.android.server.job.StateControllerProto.DeviceIdleJobsController.TrackedJobOrBuilder
                public boolean hasSourcePackageName() {
                    return ((TrackedJob) this.instance).hasSourcePackageName();
                }

                @Override // com.android.server.job.StateControllerProto.DeviceIdleJobsController.TrackedJobOrBuilder
                public String getSourcePackageName() {
                    return ((TrackedJob) this.instance).getSourcePackageName();
                }

                @Override // com.android.server.job.StateControllerProto.DeviceIdleJobsController.TrackedJobOrBuilder
                public ByteString getSourcePackageNameBytes() {
                    return ((TrackedJob) this.instance).getSourcePackageNameBytes();
                }

                public Builder setSourcePackageName(String value) {
                    copyOnWrite();
                    ((TrackedJob) this.instance).setSourcePackageName(value);
                    return this;
                }

                public Builder clearSourcePackageName() {
                    copyOnWrite();
                    ((TrackedJob) this.instance).clearSourcePackageName();
                    return this;
                }

                public Builder setSourcePackageNameBytes(ByteString value) {
                    copyOnWrite();
                    ((TrackedJob) this.instance).setSourcePackageNameBytes(value);
                    return this;
                }

                @Override // com.android.server.job.StateControllerProto.DeviceIdleJobsController.TrackedJobOrBuilder
                public boolean hasAreConstraintsSatisfied() {
                    return ((TrackedJob) this.instance).hasAreConstraintsSatisfied();
                }

                @Override // com.android.server.job.StateControllerProto.DeviceIdleJobsController.TrackedJobOrBuilder
                public boolean getAreConstraintsSatisfied() {
                    return ((TrackedJob) this.instance).getAreConstraintsSatisfied();
                }

                public Builder setAreConstraintsSatisfied(boolean value) {
                    copyOnWrite();
                    ((TrackedJob) this.instance).setAreConstraintsSatisfied(value);
                    return this;
                }

                public Builder clearAreConstraintsSatisfied() {
                    copyOnWrite();
                    ((TrackedJob) this.instance).clearAreConstraintsSatisfied();
                    return this;
                }

                @Override // com.android.server.job.StateControllerProto.DeviceIdleJobsController.TrackedJobOrBuilder
                public boolean hasIsDozeWhitelisted() {
                    return ((TrackedJob) this.instance).hasIsDozeWhitelisted();
                }

                @Override // com.android.server.job.StateControllerProto.DeviceIdleJobsController.TrackedJobOrBuilder
                public boolean getIsDozeWhitelisted() {
                    return ((TrackedJob) this.instance).getIsDozeWhitelisted();
                }

                public Builder setIsDozeWhitelisted(boolean value) {
                    copyOnWrite();
                    ((TrackedJob) this.instance).setIsDozeWhitelisted(value);
                    return this;
                }

                public Builder clearIsDozeWhitelisted() {
                    copyOnWrite();
                    ((TrackedJob) this.instance).clearIsDozeWhitelisted();
                    return this;
                }

                @Override // com.android.server.job.StateControllerProto.DeviceIdleJobsController.TrackedJobOrBuilder
                public boolean hasIsAllowedInDoze() {
                    return ((TrackedJob) this.instance).hasIsAllowedInDoze();
                }

                @Override // com.android.server.job.StateControllerProto.DeviceIdleJobsController.TrackedJobOrBuilder
                public boolean getIsAllowedInDoze() {
                    return ((TrackedJob) this.instance).getIsAllowedInDoze();
                }

                public Builder setIsAllowedInDoze(boolean value) {
                    copyOnWrite();
                    ((TrackedJob) this.instance).setIsAllowedInDoze(value);
                    return this;
                }

                public Builder clearIsAllowedInDoze() {
                    copyOnWrite();
                    ((TrackedJob) this.instance).clearIsAllowedInDoze();
                    return this;
                }
            }

            /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
            /* access modifiers changed from: protected */
            @Override // com.google.protobuf.GeneratedMessageLite
            public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
                switch (method) {
                    case NEW_MUTABLE_INSTANCE:
                        return new TrackedJob();
                    case IS_INITIALIZED:
                        return DEFAULT_INSTANCE;
                    case MAKE_IMMUTABLE:
                        return null;
                    case NEW_BUILDER:
                        return new Builder();
                    case VISIT:
                        GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                        TrackedJob other = (TrackedJob) arg1;
                        this.info_ = (JobStatusShortInfoProto) visitor.visitMessage(this.info_, other.info_);
                        this.sourceUid_ = visitor.visitInt(hasSourceUid(), this.sourceUid_, other.hasSourceUid(), other.sourceUid_);
                        this.sourcePackageName_ = visitor.visitString(hasSourcePackageName(), this.sourcePackageName_, other.hasSourcePackageName(), other.sourcePackageName_);
                        this.areConstraintsSatisfied_ = visitor.visitBoolean(hasAreConstraintsSatisfied(), this.areConstraintsSatisfied_, other.hasAreConstraintsSatisfied(), other.areConstraintsSatisfied_);
                        this.isDozeWhitelisted_ = visitor.visitBoolean(hasIsDozeWhitelisted(), this.isDozeWhitelisted_, other.hasIsDozeWhitelisted(), other.isDozeWhitelisted_);
                        this.isAllowedInDoze_ = visitor.visitBoolean(hasIsAllowedInDoze(), this.isAllowedInDoze_, other.hasIsAllowedInDoze(), other.isAllowedInDoze_);
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
                                    this.sourceUid_ = input.readInt32();
                                } else if (tag == 26) {
                                    String s = input.readString();
                                    this.bitField0_ |= 4;
                                    this.sourcePackageName_ = s;
                                } else if (tag == 32) {
                                    this.bitField0_ |= 8;
                                    this.areConstraintsSatisfied_ = input.readBool();
                                } else if (tag == 40) {
                                    this.bitField0_ = 16 | this.bitField0_;
                                    this.isDozeWhitelisted_ = input.readBool();
                                } else if (tag == 48) {
                                    this.bitField0_ |= 32;
                                    this.isAllowedInDoze_ = input.readBool();
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
                            synchronized (TrackedJob.class) {
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

            public static TrackedJob getDefaultInstance() {
                return DEFAULT_INSTANCE;
            }

            public static Parser<TrackedJob> parser() {
                return DEFAULT_INSTANCE.getParserForType();
            }
        }

        @Override // com.android.server.job.StateControllerProto.DeviceIdleJobsControllerOrBuilder
        public boolean hasIsDeviceIdleMode() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // com.android.server.job.StateControllerProto.DeviceIdleJobsControllerOrBuilder
        public boolean getIsDeviceIdleMode() {
            return this.isDeviceIdleMode_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setIsDeviceIdleMode(boolean value) {
            this.bitField0_ |= 1;
            this.isDeviceIdleMode_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearIsDeviceIdleMode() {
            this.bitField0_ &= -2;
            this.isDeviceIdleMode_ = false;
        }

        @Override // com.android.server.job.StateControllerProto.DeviceIdleJobsControllerOrBuilder
        public List<TrackedJob> getTrackedJobsList() {
            return this.trackedJobs_;
        }

        public List<? extends TrackedJobOrBuilder> getTrackedJobsOrBuilderList() {
            return this.trackedJobs_;
        }

        @Override // com.android.server.job.StateControllerProto.DeviceIdleJobsControllerOrBuilder
        public int getTrackedJobsCount() {
            return this.trackedJobs_.size();
        }

        @Override // com.android.server.job.StateControllerProto.DeviceIdleJobsControllerOrBuilder
        public TrackedJob getTrackedJobs(int index) {
            return this.trackedJobs_.get(index);
        }

        public TrackedJobOrBuilder getTrackedJobsOrBuilder(int index) {
            return this.trackedJobs_.get(index);
        }

        private void ensureTrackedJobsIsMutable() {
            if (!this.trackedJobs_.isModifiable()) {
                this.trackedJobs_ = GeneratedMessageLite.mutableCopy(this.trackedJobs_);
            }
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setTrackedJobs(int index, TrackedJob value) {
            if (value != null) {
                ensureTrackedJobsIsMutable();
                this.trackedJobs_.set(index, value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setTrackedJobs(int index, TrackedJob.Builder builderForValue) {
            ensureTrackedJobsIsMutable();
            this.trackedJobs_.set(index, (TrackedJob) builderForValue.build());
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addTrackedJobs(TrackedJob value) {
            if (value != null) {
                ensureTrackedJobsIsMutable();
                this.trackedJobs_.add(value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addTrackedJobs(int index, TrackedJob value) {
            if (value != null) {
                ensureTrackedJobsIsMutable();
                this.trackedJobs_.add(index, value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addTrackedJobs(TrackedJob.Builder builderForValue) {
            ensureTrackedJobsIsMutable();
            this.trackedJobs_.add((TrackedJob) builderForValue.build());
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addTrackedJobs(int index, TrackedJob.Builder builderForValue) {
            ensureTrackedJobsIsMutable();
            this.trackedJobs_.add(index, (TrackedJob) builderForValue.build());
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addAllTrackedJobs(Iterable<? extends TrackedJob> values) {
            ensureTrackedJobsIsMutable();
            AbstractMessageLite.addAll(values, this.trackedJobs_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearTrackedJobs() {
            this.trackedJobs_ = emptyProtobufList();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void removeTrackedJobs(int index) {
            ensureTrackedJobsIsMutable();
            this.trackedJobs_.remove(index);
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                output.writeBool(1, this.isDeviceIdleMode_);
            }
            for (int i = 0; i < this.trackedJobs_.size(); i++) {
                output.writeMessage(2, this.trackedJobs_.get(i));
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
                size2 = 0 + CodedOutputStream.computeBoolSize(1, this.isDeviceIdleMode_);
            }
            for (int i = 0; i < this.trackedJobs_.size(); i++) {
                size2 += CodedOutputStream.computeMessageSize(2, this.trackedJobs_.get(i));
            }
            int size3 = size2 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static DeviceIdleJobsController parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (DeviceIdleJobsController) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static DeviceIdleJobsController parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (DeviceIdleJobsController) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static DeviceIdleJobsController parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (DeviceIdleJobsController) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static DeviceIdleJobsController parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (DeviceIdleJobsController) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static DeviceIdleJobsController parseFrom(InputStream input) throws IOException {
            return (DeviceIdleJobsController) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static DeviceIdleJobsController parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (DeviceIdleJobsController) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static DeviceIdleJobsController parseDelimitedFrom(InputStream input) throws IOException {
            return (DeviceIdleJobsController) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static DeviceIdleJobsController parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (DeviceIdleJobsController) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static DeviceIdleJobsController parseFrom(CodedInputStream input) throws IOException {
            return (DeviceIdleJobsController) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static DeviceIdleJobsController parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (DeviceIdleJobsController) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(DeviceIdleJobsController prototype) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<DeviceIdleJobsController, Builder> implements DeviceIdleJobsControllerOrBuilder {
            private Builder() {
                super(DeviceIdleJobsController.DEFAULT_INSTANCE);
            }

            @Override // com.android.server.job.StateControllerProto.DeviceIdleJobsControllerOrBuilder
            public boolean hasIsDeviceIdleMode() {
                return ((DeviceIdleJobsController) this.instance).hasIsDeviceIdleMode();
            }

            @Override // com.android.server.job.StateControllerProto.DeviceIdleJobsControllerOrBuilder
            public boolean getIsDeviceIdleMode() {
                return ((DeviceIdleJobsController) this.instance).getIsDeviceIdleMode();
            }

            public Builder setIsDeviceIdleMode(boolean value) {
                copyOnWrite();
                ((DeviceIdleJobsController) this.instance).setIsDeviceIdleMode(value);
                return this;
            }

            public Builder clearIsDeviceIdleMode() {
                copyOnWrite();
                ((DeviceIdleJobsController) this.instance).clearIsDeviceIdleMode();
                return this;
            }

            @Override // com.android.server.job.StateControllerProto.DeviceIdleJobsControllerOrBuilder
            public List<TrackedJob> getTrackedJobsList() {
                return Collections.unmodifiableList(((DeviceIdleJobsController) this.instance).getTrackedJobsList());
            }

            @Override // com.android.server.job.StateControllerProto.DeviceIdleJobsControllerOrBuilder
            public int getTrackedJobsCount() {
                return ((DeviceIdleJobsController) this.instance).getTrackedJobsCount();
            }

            @Override // com.android.server.job.StateControllerProto.DeviceIdleJobsControllerOrBuilder
            public TrackedJob getTrackedJobs(int index) {
                return ((DeviceIdleJobsController) this.instance).getTrackedJobs(index);
            }

            public Builder setTrackedJobs(int index, TrackedJob value) {
                copyOnWrite();
                ((DeviceIdleJobsController) this.instance).setTrackedJobs((DeviceIdleJobsController) index, (int) value);
                return this;
            }

            public Builder setTrackedJobs(int index, TrackedJob.Builder builderForValue) {
                copyOnWrite();
                ((DeviceIdleJobsController) this.instance).setTrackedJobs((DeviceIdleJobsController) index, (int) builderForValue);
                return this;
            }

            public Builder addTrackedJobs(TrackedJob value) {
                copyOnWrite();
                ((DeviceIdleJobsController) this.instance).addTrackedJobs((DeviceIdleJobsController) value);
                return this;
            }

            public Builder addTrackedJobs(int index, TrackedJob value) {
                copyOnWrite();
                ((DeviceIdleJobsController) this.instance).addTrackedJobs((DeviceIdleJobsController) index, (int) value);
                return this;
            }

            public Builder addTrackedJobs(TrackedJob.Builder builderForValue) {
                copyOnWrite();
                ((DeviceIdleJobsController) this.instance).addTrackedJobs((DeviceIdleJobsController) builderForValue);
                return this;
            }

            public Builder addTrackedJobs(int index, TrackedJob.Builder builderForValue) {
                copyOnWrite();
                ((DeviceIdleJobsController) this.instance).addTrackedJobs((DeviceIdleJobsController) index, (int) builderForValue);
                return this;
            }

            public Builder addAllTrackedJobs(Iterable<? extends TrackedJob> values) {
                copyOnWrite();
                ((DeviceIdleJobsController) this.instance).addAllTrackedJobs(values);
                return this;
            }

            public Builder clearTrackedJobs() {
                copyOnWrite();
                ((DeviceIdleJobsController) this.instance).clearTrackedJobs();
                return this;
            }

            public Builder removeTrackedJobs(int index) {
                copyOnWrite();
                ((DeviceIdleJobsController) this.instance).removeTrackedJobs(index);
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new DeviceIdleJobsController();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    this.trackedJobs_.makeImmutable();
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    DeviceIdleJobsController other = (DeviceIdleJobsController) arg1;
                    this.isDeviceIdleMode_ = visitor.visitBoolean(hasIsDeviceIdleMode(), this.isDeviceIdleMode_, other.hasIsDeviceIdleMode(), other.isDeviceIdleMode_);
                    this.trackedJobs_ = visitor.visitList(this.trackedJobs_, other.trackedJobs_);
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
                                this.isDeviceIdleMode_ = input.readBool();
                            } else if (tag == 18) {
                                if (!this.trackedJobs_.isModifiable()) {
                                    this.trackedJobs_ = GeneratedMessageLite.mutableCopy(this.trackedJobs_);
                                }
                                this.trackedJobs_.add((TrackedJob) input.readMessage(TrackedJob.parser(), extensionRegistry));
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
                        synchronized (DeviceIdleJobsController.class) {
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

        public static DeviceIdleJobsController getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<DeviceIdleJobsController> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class IdleController extends GeneratedMessageLite<IdleController, Builder> implements IdleControllerOrBuilder {
        private static final IdleController DEFAULT_INSTANCE = new IdleController();
        public static final int IS_IDLE_FIELD_NUMBER = 1;
        private static volatile Parser<IdleController> PARSER = null;
        public static final int TRACKED_JOBS_FIELD_NUMBER = 2;
        private int bitField0_;
        private boolean isIdle_ = false;
        private Internal.ProtobufList<TrackedJob> trackedJobs_ = emptyProtobufList();

        public interface TrackedJobOrBuilder extends MessageLiteOrBuilder {
            JobStatusShortInfoProto getInfo();

            int getSourceUid();

            boolean hasInfo();

            boolean hasSourceUid();
        }

        private IdleController() {
        }

        public static final class TrackedJob extends GeneratedMessageLite<TrackedJob, Builder> implements TrackedJobOrBuilder {
            private static final TrackedJob DEFAULT_INSTANCE = new TrackedJob();
            public static final int INFO_FIELD_NUMBER = 1;
            private static volatile Parser<TrackedJob> PARSER = null;
            public static final int SOURCE_UID_FIELD_NUMBER = 2;
            private int bitField0_;
            private JobStatusShortInfoProto info_;
            private int sourceUid_ = 0;

            private TrackedJob() {
            }

            @Override // com.android.server.job.StateControllerProto.IdleController.TrackedJobOrBuilder
            public boolean hasInfo() {
                return (this.bitField0_ & 1) == 1;
            }

            @Override // com.android.server.job.StateControllerProto.IdleController.TrackedJobOrBuilder
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

            @Override // com.android.server.job.StateControllerProto.IdleController.TrackedJobOrBuilder
            public boolean hasSourceUid() {
                return (this.bitField0_ & 2) == 2;
            }

            @Override // com.android.server.job.StateControllerProto.IdleController.TrackedJobOrBuilder
            public int getSourceUid() {
                return this.sourceUid_;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void setSourceUid(int value) {
                this.bitField0_ |= 2;
                this.sourceUid_ = value;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void clearSourceUid() {
                this.bitField0_ &= -3;
                this.sourceUid_ = 0;
            }

            @Override // com.google.protobuf.MessageLite
            public void writeTo(CodedOutputStream output) throws IOException {
                if ((this.bitField0_ & 1) == 1) {
                    output.writeMessage(1, getInfo());
                }
                if ((this.bitField0_ & 2) == 2) {
                    output.writeInt32(2, this.sourceUid_);
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
                    size2 += CodedOutputStream.computeInt32Size(2, this.sourceUid_);
                }
                int size3 = size2 + this.unknownFields.getSerializedSize();
                this.memoizedSerializedSize = size3;
                return size3;
            }

            public static TrackedJob parseFrom(ByteString data) throws InvalidProtocolBufferException {
                return (TrackedJob) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
            }

            public static TrackedJob parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return (TrackedJob) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
            }

            public static TrackedJob parseFrom(byte[] data) throws InvalidProtocolBufferException {
                return (TrackedJob) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
            }

            public static TrackedJob parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return (TrackedJob) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
            }

            public static TrackedJob parseFrom(InputStream input) throws IOException {
                return (TrackedJob) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
            }

            public static TrackedJob parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (TrackedJob) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static TrackedJob parseDelimitedFrom(InputStream input) throws IOException {
                return (TrackedJob) parseDelimitedFrom(DEFAULT_INSTANCE, input);
            }

            public static TrackedJob parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (TrackedJob) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static TrackedJob parseFrom(CodedInputStream input) throws IOException {
                return (TrackedJob) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
            }

            public static TrackedJob parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (TrackedJob) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static Builder newBuilder() {
                return (Builder) DEFAULT_INSTANCE.toBuilder();
            }

            public static Builder newBuilder(TrackedJob prototype) {
                return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
            }

            public static final class Builder extends GeneratedMessageLite.Builder<TrackedJob, Builder> implements TrackedJobOrBuilder {
                private Builder() {
                    super(TrackedJob.DEFAULT_INSTANCE);
                }

                @Override // com.android.server.job.StateControllerProto.IdleController.TrackedJobOrBuilder
                public boolean hasInfo() {
                    return ((TrackedJob) this.instance).hasInfo();
                }

                @Override // com.android.server.job.StateControllerProto.IdleController.TrackedJobOrBuilder
                public JobStatusShortInfoProto getInfo() {
                    return ((TrackedJob) this.instance).getInfo();
                }

                public Builder setInfo(JobStatusShortInfoProto value) {
                    copyOnWrite();
                    ((TrackedJob) this.instance).setInfo((TrackedJob) value);
                    return this;
                }

                public Builder setInfo(JobStatusShortInfoProto.Builder builderForValue) {
                    copyOnWrite();
                    ((TrackedJob) this.instance).setInfo((TrackedJob) builderForValue);
                    return this;
                }

                public Builder mergeInfo(JobStatusShortInfoProto value) {
                    copyOnWrite();
                    ((TrackedJob) this.instance).mergeInfo(value);
                    return this;
                }

                public Builder clearInfo() {
                    copyOnWrite();
                    ((TrackedJob) this.instance).clearInfo();
                    return this;
                }

                @Override // com.android.server.job.StateControllerProto.IdleController.TrackedJobOrBuilder
                public boolean hasSourceUid() {
                    return ((TrackedJob) this.instance).hasSourceUid();
                }

                @Override // com.android.server.job.StateControllerProto.IdleController.TrackedJobOrBuilder
                public int getSourceUid() {
                    return ((TrackedJob) this.instance).getSourceUid();
                }

                public Builder setSourceUid(int value) {
                    copyOnWrite();
                    ((TrackedJob) this.instance).setSourceUid(value);
                    return this;
                }

                public Builder clearSourceUid() {
                    copyOnWrite();
                    ((TrackedJob) this.instance).clearSourceUid();
                    return this;
                }
            }

            /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
            /* access modifiers changed from: protected */
            @Override // com.google.protobuf.GeneratedMessageLite
            public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
                switch (method) {
                    case NEW_MUTABLE_INSTANCE:
                        return new TrackedJob();
                    case IS_INITIALIZED:
                        return DEFAULT_INSTANCE;
                    case MAKE_IMMUTABLE:
                        return null;
                    case NEW_BUILDER:
                        return new Builder();
                    case VISIT:
                        GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                        TrackedJob other = (TrackedJob) arg1;
                        this.info_ = (JobStatusShortInfoProto) visitor.visitMessage(this.info_, other.info_);
                        this.sourceUid_ = visitor.visitInt(hasSourceUid(), this.sourceUid_, other.hasSourceUid(), other.sourceUid_);
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
                                    this.sourceUid_ = input.readInt32();
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
                            synchronized (TrackedJob.class) {
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

            public static TrackedJob getDefaultInstance() {
                return DEFAULT_INSTANCE;
            }

            public static Parser<TrackedJob> parser() {
                return DEFAULT_INSTANCE.getParserForType();
            }
        }

        @Override // com.android.server.job.StateControllerProto.IdleControllerOrBuilder
        public boolean hasIsIdle() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // com.android.server.job.StateControllerProto.IdleControllerOrBuilder
        public boolean getIsIdle() {
            return this.isIdle_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setIsIdle(boolean value) {
            this.bitField0_ |= 1;
            this.isIdle_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearIsIdle() {
            this.bitField0_ &= -2;
            this.isIdle_ = false;
        }

        @Override // com.android.server.job.StateControllerProto.IdleControllerOrBuilder
        public List<TrackedJob> getTrackedJobsList() {
            return this.trackedJobs_;
        }

        public List<? extends TrackedJobOrBuilder> getTrackedJobsOrBuilderList() {
            return this.trackedJobs_;
        }

        @Override // com.android.server.job.StateControllerProto.IdleControllerOrBuilder
        public int getTrackedJobsCount() {
            return this.trackedJobs_.size();
        }

        @Override // com.android.server.job.StateControllerProto.IdleControllerOrBuilder
        public TrackedJob getTrackedJobs(int index) {
            return this.trackedJobs_.get(index);
        }

        public TrackedJobOrBuilder getTrackedJobsOrBuilder(int index) {
            return this.trackedJobs_.get(index);
        }

        private void ensureTrackedJobsIsMutable() {
            if (!this.trackedJobs_.isModifiable()) {
                this.trackedJobs_ = GeneratedMessageLite.mutableCopy(this.trackedJobs_);
            }
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setTrackedJobs(int index, TrackedJob value) {
            if (value != null) {
                ensureTrackedJobsIsMutable();
                this.trackedJobs_.set(index, value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setTrackedJobs(int index, TrackedJob.Builder builderForValue) {
            ensureTrackedJobsIsMutable();
            this.trackedJobs_.set(index, (TrackedJob) builderForValue.build());
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addTrackedJobs(TrackedJob value) {
            if (value != null) {
                ensureTrackedJobsIsMutable();
                this.trackedJobs_.add(value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addTrackedJobs(int index, TrackedJob value) {
            if (value != null) {
                ensureTrackedJobsIsMutable();
                this.trackedJobs_.add(index, value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addTrackedJobs(TrackedJob.Builder builderForValue) {
            ensureTrackedJobsIsMutable();
            this.trackedJobs_.add((TrackedJob) builderForValue.build());
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addTrackedJobs(int index, TrackedJob.Builder builderForValue) {
            ensureTrackedJobsIsMutable();
            this.trackedJobs_.add(index, (TrackedJob) builderForValue.build());
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addAllTrackedJobs(Iterable<? extends TrackedJob> values) {
            ensureTrackedJobsIsMutable();
            AbstractMessageLite.addAll(values, this.trackedJobs_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearTrackedJobs() {
            this.trackedJobs_ = emptyProtobufList();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void removeTrackedJobs(int index) {
            ensureTrackedJobsIsMutable();
            this.trackedJobs_.remove(index);
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                output.writeBool(1, this.isIdle_);
            }
            for (int i = 0; i < this.trackedJobs_.size(); i++) {
                output.writeMessage(2, this.trackedJobs_.get(i));
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
                size2 = 0 + CodedOutputStream.computeBoolSize(1, this.isIdle_);
            }
            for (int i = 0; i < this.trackedJobs_.size(); i++) {
                size2 += CodedOutputStream.computeMessageSize(2, this.trackedJobs_.get(i));
            }
            int size3 = size2 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static IdleController parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (IdleController) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static IdleController parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (IdleController) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static IdleController parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (IdleController) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static IdleController parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (IdleController) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static IdleController parseFrom(InputStream input) throws IOException {
            return (IdleController) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static IdleController parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (IdleController) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static IdleController parseDelimitedFrom(InputStream input) throws IOException {
            return (IdleController) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static IdleController parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (IdleController) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static IdleController parseFrom(CodedInputStream input) throws IOException {
            return (IdleController) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static IdleController parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (IdleController) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(IdleController prototype) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<IdleController, Builder> implements IdleControllerOrBuilder {
            private Builder() {
                super(IdleController.DEFAULT_INSTANCE);
            }

            @Override // com.android.server.job.StateControllerProto.IdleControllerOrBuilder
            public boolean hasIsIdle() {
                return ((IdleController) this.instance).hasIsIdle();
            }

            @Override // com.android.server.job.StateControllerProto.IdleControllerOrBuilder
            public boolean getIsIdle() {
                return ((IdleController) this.instance).getIsIdle();
            }

            public Builder setIsIdle(boolean value) {
                copyOnWrite();
                ((IdleController) this.instance).setIsIdle(value);
                return this;
            }

            public Builder clearIsIdle() {
                copyOnWrite();
                ((IdleController) this.instance).clearIsIdle();
                return this;
            }

            @Override // com.android.server.job.StateControllerProto.IdleControllerOrBuilder
            public List<TrackedJob> getTrackedJobsList() {
                return Collections.unmodifiableList(((IdleController) this.instance).getTrackedJobsList());
            }

            @Override // com.android.server.job.StateControllerProto.IdleControllerOrBuilder
            public int getTrackedJobsCount() {
                return ((IdleController) this.instance).getTrackedJobsCount();
            }

            @Override // com.android.server.job.StateControllerProto.IdleControllerOrBuilder
            public TrackedJob getTrackedJobs(int index) {
                return ((IdleController) this.instance).getTrackedJobs(index);
            }

            public Builder setTrackedJobs(int index, TrackedJob value) {
                copyOnWrite();
                ((IdleController) this.instance).setTrackedJobs((IdleController) index, (int) value);
                return this;
            }

            public Builder setTrackedJobs(int index, TrackedJob.Builder builderForValue) {
                copyOnWrite();
                ((IdleController) this.instance).setTrackedJobs((IdleController) index, (int) builderForValue);
                return this;
            }

            public Builder addTrackedJobs(TrackedJob value) {
                copyOnWrite();
                ((IdleController) this.instance).addTrackedJobs((IdleController) value);
                return this;
            }

            public Builder addTrackedJobs(int index, TrackedJob value) {
                copyOnWrite();
                ((IdleController) this.instance).addTrackedJobs((IdleController) index, (int) value);
                return this;
            }

            public Builder addTrackedJobs(TrackedJob.Builder builderForValue) {
                copyOnWrite();
                ((IdleController) this.instance).addTrackedJobs((IdleController) builderForValue);
                return this;
            }

            public Builder addTrackedJobs(int index, TrackedJob.Builder builderForValue) {
                copyOnWrite();
                ((IdleController) this.instance).addTrackedJobs((IdleController) index, (int) builderForValue);
                return this;
            }

            public Builder addAllTrackedJobs(Iterable<? extends TrackedJob> values) {
                copyOnWrite();
                ((IdleController) this.instance).addAllTrackedJobs(values);
                return this;
            }

            public Builder clearTrackedJobs() {
                copyOnWrite();
                ((IdleController) this.instance).clearTrackedJobs();
                return this;
            }

            public Builder removeTrackedJobs(int index) {
                copyOnWrite();
                ((IdleController) this.instance).removeTrackedJobs(index);
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new IdleController();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    this.trackedJobs_.makeImmutable();
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    IdleController other = (IdleController) arg1;
                    this.isIdle_ = visitor.visitBoolean(hasIsIdle(), this.isIdle_, other.hasIsIdle(), other.isIdle_);
                    this.trackedJobs_ = visitor.visitList(this.trackedJobs_, other.trackedJobs_);
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
                                this.isIdle_ = input.readBool();
                            } else if (tag == 18) {
                                if (!this.trackedJobs_.isModifiable()) {
                                    this.trackedJobs_ = GeneratedMessageLite.mutableCopy(this.trackedJobs_);
                                }
                                this.trackedJobs_.add((TrackedJob) input.readMessage(TrackedJob.parser(), extensionRegistry));
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
                        synchronized (IdleController.class) {
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

        public static IdleController getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<IdleController> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class QuotaController extends GeneratedMessageLite<QuotaController, Builder> implements QuotaControllerOrBuilder {
        private static final QuotaController DEFAULT_INSTANCE = new QuotaController();
        public static final int ELAPSED_REALTIME_FIELD_NUMBER = 6;
        public static final int FOREGROUND_UIDS_FIELD_NUMBER = 3;
        public static final int IS_CHARGING_FIELD_NUMBER = 1;
        public static final int IS_IN_PAROLE_FIELD_NUMBER = 2;
        public static final int PACKAGE_STATS_FIELD_NUMBER = 5;
        private static volatile Parser<QuotaController> PARSER = null;
        public static final int TRACKED_JOBS_FIELD_NUMBER = 4;
        private int bitField0_;
        private long elapsedRealtime_ = 0;
        private Internal.IntList foregroundUids_ = emptyIntList();
        private boolean isCharging_ = false;
        private boolean isInParole_ = false;
        private Internal.ProtobufList<PackageStats> packageStats_ = emptyProtobufList();
        private Internal.ProtobufList<TrackedJob> trackedJobs_ = emptyProtobufList();

        public interface AlarmListenerOrBuilder extends MessageLiteOrBuilder {
            boolean getIsWaiting();

            long getTriggerTimeElapsed();

            boolean hasIsWaiting();

            boolean hasTriggerTimeElapsed();
        }

        public interface ExecutionStatsOrBuilder extends MessageLiteOrBuilder {
            int getBgJobCountInMaxPeriod();

            int getBgJobCountInWindow();

            long getExecutionTimeInMaxPeriodMs();

            long getExecutionTimeInWindowMs();

            long getExpirationTimeElapsed();

            long getInQuotaTimeElapsed();

            long getJobCountExpirationTimeElapsed();

            int getJobCountInRateLimitingWindow();

            int getJobCountLimit();

            long getSessionCountExpirationTimeElapsed();

            int getSessionCountInRateLimitingWindow();

            int getSessionCountInWindow();

            int getSessionCountLimit();

            JobStatusDumpProto.Bucket getStandbyBucket();

            long getWindowSizeMs();

            boolean hasBgJobCountInMaxPeriod();

            boolean hasBgJobCountInWindow();

            boolean hasExecutionTimeInMaxPeriodMs();

            boolean hasExecutionTimeInWindowMs();

            boolean hasExpirationTimeElapsed();

            boolean hasInQuotaTimeElapsed();

            boolean hasJobCountExpirationTimeElapsed();

            boolean hasJobCountInRateLimitingWindow();

            boolean hasJobCountLimit();

            boolean hasSessionCountExpirationTimeElapsed();

            boolean hasSessionCountInRateLimitingWindow();

            boolean hasSessionCountInWindow();

            boolean hasSessionCountLimit();

            boolean hasStandbyBucket();

            boolean hasWindowSizeMs();
        }

        public interface PackageOrBuilder extends MessageLiteOrBuilder {
            String getName();

            ByteString getNameBytes();

            int getUserId();

            boolean hasName();

            boolean hasUserId();
        }

        public interface PackageStatsOrBuilder extends MessageLiteOrBuilder {
            ExecutionStats getExecutionStats(int i);

            int getExecutionStatsCount();

            List<ExecutionStats> getExecutionStatsList();

            AlarmListener getInQuotaAlarmListener();

            Package getPkg();

            TimingSession getSavedSessions(int i);

            int getSavedSessionsCount();

            List<TimingSession> getSavedSessionsList();

            Timer getTimer();

            boolean hasInQuotaAlarmListener();

            boolean hasPkg();

            boolean hasTimer();
        }

        public interface TimerOrBuilder extends MessageLiteOrBuilder {
            int getBgJobCount();

            boolean getIsActive();

            Package getPkg();

            JobStatusShortInfoProto getRunningJobs(int i);

            int getRunningJobsCount();

            List<JobStatusShortInfoProto> getRunningJobsList();

            long getStartTimeElapsed();

            boolean hasBgJobCount();

            boolean hasIsActive();

            boolean hasPkg();

            boolean hasStartTimeElapsed();
        }

        public interface TimingSessionOrBuilder extends MessageLiteOrBuilder {
            int getBgJobCount();

            long getEndTimeElapsed();

            long getStartTimeElapsed();

            boolean hasBgJobCount();

            boolean hasEndTimeElapsed();

            boolean hasStartTimeElapsed();
        }

        public interface TrackedJobOrBuilder extends MessageLiteOrBuilder {
            JobStatusDumpProto.Bucket getEffectiveStandbyBucket();

            boolean getHasQuota();

            JobStatusShortInfoProto getInfo();

            boolean getIsTopStartedJob();

            long getRemainingQuotaMs();

            int getSourceUid();

            boolean hasEffectiveStandbyBucket();

            boolean hasHasQuota();

            boolean hasInfo();

            boolean hasIsTopStartedJob();

            boolean hasRemainingQuotaMs();

            boolean hasSourceUid();
        }

        private QuotaController() {
        }

        public static final class TrackedJob extends GeneratedMessageLite<TrackedJob, Builder> implements TrackedJobOrBuilder {
            private static final TrackedJob DEFAULT_INSTANCE = new TrackedJob();
            public static final int EFFECTIVE_STANDBY_BUCKET_FIELD_NUMBER = 3;
            public static final int HAS_QUOTA_FIELD_NUMBER = 5;
            public static final int INFO_FIELD_NUMBER = 1;
            public static final int IS_TOP_STARTED_JOB_FIELD_NUMBER = 4;
            private static volatile Parser<TrackedJob> PARSER = null;
            public static final int REMAINING_QUOTA_MS_FIELD_NUMBER = 6;
            public static final int SOURCE_UID_FIELD_NUMBER = 2;
            private int bitField0_;
            private int effectiveStandbyBucket_ = 0;
            private boolean hasQuota_ = false;
            private JobStatusShortInfoProto info_;
            private boolean isTopStartedJob_ = false;
            private long remainingQuotaMs_ = 0;
            private int sourceUid_ = 0;

            private TrackedJob() {
            }

            @Override // com.android.server.job.StateControllerProto.QuotaController.TrackedJobOrBuilder
            public boolean hasInfo() {
                return (this.bitField0_ & 1) == 1;
            }

            @Override // com.android.server.job.StateControllerProto.QuotaController.TrackedJobOrBuilder
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

            @Override // com.android.server.job.StateControllerProto.QuotaController.TrackedJobOrBuilder
            public boolean hasSourceUid() {
                return (this.bitField0_ & 2) == 2;
            }

            @Override // com.android.server.job.StateControllerProto.QuotaController.TrackedJobOrBuilder
            public int getSourceUid() {
                return this.sourceUid_;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void setSourceUid(int value) {
                this.bitField0_ |= 2;
                this.sourceUid_ = value;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void clearSourceUid() {
                this.bitField0_ &= -3;
                this.sourceUid_ = 0;
            }

            @Override // com.android.server.job.StateControllerProto.QuotaController.TrackedJobOrBuilder
            public boolean hasEffectiveStandbyBucket() {
                return (this.bitField0_ & 4) == 4;
            }

            @Override // com.android.server.job.StateControllerProto.QuotaController.TrackedJobOrBuilder
            public JobStatusDumpProto.Bucket getEffectiveStandbyBucket() {
                JobStatusDumpProto.Bucket result = JobStatusDumpProto.Bucket.forNumber(this.effectiveStandbyBucket_);
                return result == null ? JobStatusDumpProto.Bucket.ACTIVE : result;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void setEffectiveStandbyBucket(JobStatusDumpProto.Bucket value) {
                if (value != null) {
                    this.bitField0_ |= 4;
                    this.effectiveStandbyBucket_ = value.getNumber();
                    return;
                }
                throw new NullPointerException();
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void clearEffectiveStandbyBucket() {
                this.bitField0_ &= -5;
                this.effectiveStandbyBucket_ = 0;
            }

            @Override // com.android.server.job.StateControllerProto.QuotaController.TrackedJobOrBuilder
            public boolean hasIsTopStartedJob() {
                return (this.bitField0_ & 8) == 8;
            }

            @Override // com.android.server.job.StateControllerProto.QuotaController.TrackedJobOrBuilder
            public boolean getIsTopStartedJob() {
                return this.isTopStartedJob_;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void setIsTopStartedJob(boolean value) {
                this.bitField0_ |= 8;
                this.isTopStartedJob_ = value;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void clearIsTopStartedJob() {
                this.bitField0_ &= -9;
                this.isTopStartedJob_ = false;
            }

            @Override // com.android.server.job.StateControllerProto.QuotaController.TrackedJobOrBuilder
            public boolean hasHasQuota() {
                return (this.bitField0_ & 16) == 16;
            }

            @Override // com.android.server.job.StateControllerProto.QuotaController.TrackedJobOrBuilder
            public boolean getHasQuota() {
                return this.hasQuota_;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void setHasQuota(boolean value) {
                this.bitField0_ |= 16;
                this.hasQuota_ = value;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void clearHasQuota() {
                this.bitField0_ &= -17;
                this.hasQuota_ = false;
            }

            @Override // com.android.server.job.StateControllerProto.QuotaController.TrackedJobOrBuilder
            public boolean hasRemainingQuotaMs() {
                return (this.bitField0_ & 32) == 32;
            }

            @Override // com.android.server.job.StateControllerProto.QuotaController.TrackedJobOrBuilder
            public long getRemainingQuotaMs() {
                return this.remainingQuotaMs_;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void setRemainingQuotaMs(long value) {
                this.bitField0_ |= 32;
                this.remainingQuotaMs_ = value;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void clearRemainingQuotaMs() {
                this.bitField0_ &= -33;
                this.remainingQuotaMs_ = 0;
            }

            @Override // com.google.protobuf.MessageLite
            public void writeTo(CodedOutputStream output) throws IOException {
                if ((this.bitField0_ & 1) == 1) {
                    output.writeMessage(1, getInfo());
                }
                if ((this.bitField0_ & 2) == 2) {
                    output.writeInt32(2, this.sourceUid_);
                }
                if ((this.bitField0_ & 4) == 4) {
                    output.writeEnum(3, this.effectiveStandbyBucket_);
                }
                if ((this.bitField0_ & 8) == 8) {
                    output.writeBool(4, this.isTopStartedJob_);
                }
                if ((this.bitField0_ & 16) == 16) {
                    output.writeBool(5, this.hasQuota_);
                }
                if ((this.bitField0_ & 32) == 32) {
                    output.writeInt64(6, this.remainingQuotaMs_);
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
                    size2 += CodedOutputStream.computeInt32Size(2, this.sourceUid_);
                }
                if ((this.bitField0_ & 4) == 4) {
                    size2 += CodedOutputStream.computeEnumSize(3, this.effectiveStandbyBucket_);
                }
                if ((this.bitField0_ & 8) == 8) {
                    size2 += CodedOutputStream.computeBoolSize(4, this.isTopStartedJob_);
                }
                if ((this.bitField0_ & 16) == 16) {
                    size2 += CodedOutputStream.computeBoolSize(5, this.hasQuota_);
                }
                if ((this.bitField0_ & 32) == 32) {
                    size2 += CodedOutputStream.computeInt64Size(6, this.remainingQuotaMs_);
                }
                int size3 = size2 + this.unknownFields.getSerializedSize();
                this.memoizedSerializedSize = size3;
                return size3;
            }

            public static TrackedJob parseFrom(ByteString data) throws InvalidProtocolBufferException {
                return (TrackedJob) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
            }

            public static TrackedJob parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return (TrackedJob) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
            }

            public static TrackedJob parseFrom(byte[] data) throws InvalidProtocolBufferException {
                return (TrackedJob) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
            }

            public static TrackedJob parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return (TrackedJob) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
            }

            public static TrackedJob parseFrom(InputStream input) throws IOException {
                return (TrackedJob) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
            }

            public static TrackedJob parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (TrackedJob) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static TrackedJob parseDelimitedFrom(InputStream input) throws IOException {
                return (TrackedJob) parseDelimitedFrom(DEFAULT_INSTANCE, input);
            }

            public static TrackedJob parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (TrackedJob) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static TrackedJob parseFrom(CodedInputStream input) throws IOException {
                return (TrackedJob) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
            }

            public static TrackedJob parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (TrackedJob) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static Builder newBuilder() {
                return (Builder) DEFAULT_INSTANCE.toBuilder();
            }

            public static Builder newBuilder(TrackedJob prototype) {
                return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
            }

            public static final class Builder extends GeneratedMessageLite.Builder<TrackedJob, Builder> implements TrackedJobOrBuilder {
                private Builder() {
                    super(TrackedJob.DEFAULT_INSTANCE);
                }

                @Override // com.android.server.job.StateControllerProto.QuotaController.TrackedJobOrBuilder
                public boolean hasInfo() {
                    return ((TrackedJob) this.instance).hasInfo();
                }

                @Override // com.android.server.job.StateControllerProto.QuotaController.TrackedJobOrBuilder
                public JobStatusShortInfoProto getInfo() {
                    return ((TrackedJob) this.instance).getInfo();
                }

                public Builder setInfo(JobStatusShortInfoProto value) {
                    copyOnWrite();
                    ((TrackedJob) this.instance).setInfo((TrackedJob) value);
                    return this;
                }

                public Builder setInfo(JobStatusShortInfoProto.Builder builderForValue) {
                    copyOnWrite();
                    ((TrackedJob) this.instance).setInfo((TrackedJob) builderForValue);
                    return this;
                }

                public Builder mergeInfo(JobStatusShortInfoProto value) {
                    copyOnWrite();
                    ((TrackedJob) this.instance).mergeInfo(value);
                    return this;
                }

                public Builder clearInfo() {
                    copyOnWrite();
                    ((TrackedJob) this.instance).clearInfo();
                    return this;
                }

                @Override // com.android.server.job.StateControllerProto.QuotaController.TrackedJobOrBuilder
                public boolean hasSourceUid() {
                    return ((TrackedJob) this.instance).hasSourceUid();
                }

                @Override // com.android.server.job.StateControllerProto.QuotaController.TrackedJobOrBuilder
                public int getSourceUid() {
                    return ((TrackedJob) this.instance).getSourceUid();
                }

                public Builder setSourceUid(int value) {
                    copyOnWrite();
                    ((TrackedJob) this.instance).setSourceUid(value);
                    return this;
                }

                public Builder clearSourceUid() {
                    copyOnWrite();
                    ((TrackedJob) this.instance).clearSourceUid();
                    return this;
                }

                @Override // com.android.server.job.StateControllerProto.QuotaController.TrackedJobOrBuilder
                public boolean hasEffectiveStandbyBucket() {
                    return ((TrackedJob) this.instance).hasEffectiveStandbyBucket();
                }

                @Override // com.android.server.job.StateControllerProto.QuotaController.TrackedJobOrBuilder
                public JobStatusDumpProto.Bucket getEffectiveStandbyBucket() {
                    return ((TrackedJob) this.instance).getEffectiveStandbyBucket();
                }

                public Builder setEffectiveStandbyBucket(JobStatusDumpProto.Bucket value) {
                    copyOnWrite();
                    ((TrackedJob) this.instance).setEffectiveStandbyBucket(value);
                    return this;
                }

                public Builder clearEffectiveStandbyBucket() {
                    copyOnWrite();
                    ((TrackedJob) this.instance).clearEffectiveStandbyBucket();
                    return this;
                }

                @Override // com.android.server.job.StateControllerProto.QuotaController.TrackedJobOrBuilder
                public boolean hasIsTopStartedJob() {
                    return ((TrackedJob) this.instance).hasIsTopStartedJob();
                }

                @Override // com.android.server.job.StateControllerProto.QuotaController.TrackedJobOrBuilder
                public boolean getIsTopStartedJob() {
                    return ((TrackedJob) this.instance).getIsTopStartedJob();
                }

                public Builder setIsTopStartedJob(boolean value) {
                    copyOnWrite();
                    ((TrackedJob) this.instance).setIsTopStartedJob(value);
                    return this;
                }

                public Builder clearIsTopStartedJob() {
                    copyOnWrite();
                    ((TrackedJob) this.instance).clearIsTopStartedJob();
                    return this;
                }

                @Override // com.android.server.job.StateControllerProto.QuotaController.TrackedJobOrBuilder
                public boolean hasHasQuota() {
                    return ((TrackedJob) this.instance).hasHasQuota();
                }

                @Override // com.android.server.job.StateControllerProto.QuotaController.TrackedJobOrBuilder
                public boolean getHasQuota() {
                    return ((TrackedJob) this.instance).getHasQuota();
                }

                public Builder setHasQuota(boolean value) {
                    copyOnWrite();
                    ((TrackedJob) this.instance).setHasQuota(value);
                    return this;
                }

                public Builder clearHasQuota() {
                    copyOnWrite();
                    ((TrackedJob) this.instance).clearHasQuota();
                    return this;
                }

                @Override // com.android.server.job.StateControllerProto.QuotaController.TrackedJobOrBuilder
                public boolean hasRemainingQuotaMs() {
                    return ((TrackedJob) this.instance).hasRemainingQuotaMs();
                }

                @Override // com.android.server.job.StateControllerProto.QuotaController.TrackedJobOrBuilder
                public long getRemainingQuotaMs() {
                    return ((TrackedJob) this.instance).getRemainingQuotaMs();
                }

                public Builder setRemainingQuotaMs(long value) {
                    copyOnWrite();
                    ((TrackedJob) this.instance).setRemainingQuotaMs(value);
                    return this;
                }

                public Builder clearRemainingQuotaMs() {
                    copyOnWrite();
                    ((TrackedJob) this.instance).clearRemainingQuotaMs();
                    return this;
                }
            }

            /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
            /* access modifiers changed from: protected */
            @Override // com.google.protobuf.GeneratedMessageLite
            public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
                switch (method) {
                    case NEW_MUTABLE_INSTANCE:
                        return new TrackedJob();
                    case IS_INITIALIZED:
                        return DEFAULT_INSTANCE;
                    case MAKE_IMMUTABLE:
                        return null;
                    case NEW_BUILDER:
                        return new Builder();
                    case VISIT:
                        GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                        TrackedJob other = (TrackedJob) arg1;
                        this.info_ = (JobStatusShortInfoProto) visitor.visitMessage(this.info_, other.info_);
                        this.sourceUid_ = visitor.visitInt(hasSourceUid(), this.sourceUid_, other.hasSourceUid(), other.sourceUid_);
                        this.effectiveStandbyBucket_ = visitor.visitInt(hasEffectiveStandbyBucket(), this.effectiveStandbyBucket_, other.hasEffectiveStandbyBucket(), other.effectiveStandbyBucket_);
                        this.isTopStartedJob_ = visitor.visitBoolean(hasIsTopStartedJob(), this.isTopStartedJob_, other.hasIsTopStartedJob(), other.isTopStartedJob_);
                        this.hasQuota_ = visitor.visitBoolean(hasHasQuota(), this.hasQuota_, other.hasHasQuota(), other.hasQuota_);
                        this.remainingQuotaMs_ = visitor.visitLong(hasRemainingQuotaMs(), this.remainingQuotaMs_, other.hasRemainingQuotaMs(), other.remainingQuotaMs_);
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
                                    this.sourceUid_ = input.readInt32();
                                } else if (tag == 24) {
                                    int rawValue = input.readEnum();
                                    if (JobStatusDumpProto.Bucket.forNumber(rawValue) == null) {
                                        super.mergeVarintField(3, rawValue);
                                    } else {
                                        this.bitField0_ |= 4;
                                        this.effectiveStandbyBucket_ = rawValue;
                                    }
                                } else if (tag == 32) {
                                    this.bitField0_ |= 8;
                                    this.isTopStartedJob_ = input.readBool();
                                } else if (tag == 40) {
                                    this.bitField0_ = 16 | this.bitField0_;
                                    this.hasQuota_ = input.readBool();
                                } else if (tag == 48) {
                                    this.bitField0_ |= 32;
                                    this.remainingQuotaMs_ = input.readInt64();
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
                            synchronized (TrackedJob.class) {
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

            public static TrackedJob getDefaultInstance() {
                return DEFAULT_INSTANCE;
            }

            public static Parser<TrackedJob> parser() {
                return DEFAULT_INSTANCE.getParserForType();
            }
        }

        public static final class AlarmListener extends GeneratedMessageLite<AlarmListener, Builder> implements AlarmListenerOrBuilder {
            private static final AlarmListener DEFAULT_INSTANCE = new AlarmListener();
            public static final int IS_WAITING_FIELD_NUMBER = 1;
            private static volatile Parser<AlarmListener> PARSER = null;
            public static final int TRIGGER_TIME_ELAPSED_FIELD_NUMBER = 2;
            private int bitField0_;
            private boolean isWaiting_ = false;
            private long triggerTimeElapsed_ = 0;

            private AlarmListener() {
            }

            @Override // com.android.server.job.StateControllerProto.QuotaController.AlarmListenerOrBuilder
            public boolean hasIsWaiting() {
                return (this.bitField0_ & 1) == 1;
            }

            @Override // com.android.server.job.StateControllerProto.QuotaController.AlarmListenerOrBuilder
            public boolean getIsWaiting() {
                return this.isWaiting_;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void setIsWaiting(boolean value) {
                this.bitField0_ |= 1;
                this.isWaiting_ = value;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void clearIsWaiting() {
                this.bitField0_ &= -2;
                this.isWaiting_ = false;
            }

            @Override // com.android.server.job.StateControllerProto.QuotaController.AlarmListenerOrBuilder
            public boolean hasTriggerTimeElapsed() {
                return (this.bitField0_ & 2) == 2;
            }

            @Override // com.android.server.job.StateControllerProto.QuotaController.AlarmListenerOrBuilder
            public long getTriggerTimeElapsed() {
                return this.triggerTimeElapsed_;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void setTriggerTimeElapsed(long value) {
                this.bitField0_ |= 2;
                this.triggerTimeElapsed_ = value;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void clearTriggerTimeElapsed() {
                this.bitField0_ &= -3;
                this.triggerTimeElapsed_ = 0;
            }

            @Override // com.google.protobuf.MessageLite
            public void writeTo(CodedOutputStream output) throws IOException {
                if ((this.bitField0_ & 1) == 1) {
                    output.writeBool(1, this.isWaiting_);
                }
                if ((this.bitField0_ & 2) == 2) {
                    output.writeInt64(2, this.triggerTimeElapsed_);
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
                    size2 = 0 + CodedOutputStream.computeBoolSize(1, this.isWaiting_);
                }
                if ((this.bitField0_ & 2) == 2) {
                    size2 += CodedOutputStream.computeInt64Size(2, this.triggerTimeElapsed_);
                }
                int size3 = size2 + this.unknownFields.getSerializedSize();
                this.memoizedSerializedSize = size3;
                return size3;
            }

            public static AlarmListener parseFrom(ByteString data) throws InvalidProtocolBufferException {
                return (AlarmListener) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
            }

            public static AlarmListener parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return (AlarmListener) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
            }

            public static AlarmListener parseFrom(byte[] data) throws InvalidProtocolBufferException {
                return (AlarmListener) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
            }

            public static AlarmListener parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return (AlarmListener) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
            }

            public static AlarmListener parseFrom(InputStream input) throws IOException {
                return (AlarmListener) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
            }

            public static AlarmListener parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (AlarmListener) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static AlarmListener parseDelimitedFrom(InputStream input) throws IOException {
                return (AlarmListener) parseDelimitedFrom(DEFAULT_INSTANCE, input);
            }

            public static AlarmListener parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (AlarmListener) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static AlarmListener parseFrom(CodedInputStream input) throws IOException {
                return (AlarmListener) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
            }

            public static AlarmListener parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (AlarmListener) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static Builder newBuilder() {
                return (Builder) DEFAULT_INSTANCE.toBuilder();
            }

            public static Builder newBuilder(AlarmListener prototype) {
                return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
            }

            public static final class Builder extends GeneratedMessageLite.Builder<AlarmListener, Builder> implements AlarmListenerOrBuilder {
                private Builder() {
                    super(AlarmListener.DEFAULT_INSTANCE);
                }

                @Override // com.android.server.job.StateControllerProto.QuotaController.AlarmListenerOrBuilder
                public boolean hasIsWaiting() {
                    return ((AlarmListener) this.instance).hasIsWaiting();
                }

                @Override // com.android.server.job.StateControllerProto.QuotaController.AlarmListenerOrBuilder
                public boolean getIsWaiting() {
                    return ((AlarmListener) this.instance).getIsWaiting();
                }

                public Builder setIsWaiting(boolean value) {
                    copyOnWrite();
                    ((AlarmListener) this.instance).setIsWaiting(value);
                    return this;
                }

                public Builder clearIsWaiting() {
                    copyOnWrite();
                    ((AlarmListener) this.instance).clearIsWaiting();
                    return this;
                }

                @Override // com.android.server.job.StateControllerProto.QuotaController.AlarmListenerOrBuilder
                public boolean hasTriggerTimeElapsed() {
                    return ((AlarmListener) this.instance).hasTriggerTimeElapsed();
                }

                @Override // com.android.server.job.StateControllerProto.QuotaController.AlarmListenerOrBuilder
                public long getTriggerTimeElapsed() {
                    return ((AlarmListener) this.instance).getTriggerTimeElapsed();
                }

                public Builder setTriggerTimeElapsed(long value) {
                    copyOnWrite();
                    ((AlarmListener) this.instance).setTriggerTimeElapsed(value);
                    return this;
                }

                public Builder clearTriggerTimeElapsed() {
                    copyOnWrite();
                    ((AlarmListener) this.instance).clearTriggerTimeElapsed();
                    return this;
                }
            }

            /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
            /* access modifiers changed from: protected */
            @Override // com.google.protobuf.GeneratedMessageLite
            public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
                switch (method) {
                    case NEW_MUTABLE_INSTANCE:
                        return new AlarmListener();
                    case IS_INITIALIZED:
                        return DEFAULT_INSTANCE;
                    case MAKE_IMMUTABLE:
                        return null;
                    case NEW_BUILDER:
                        return new Builder();
                    case VISIT:
                        GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                        AlarmListener other = (AlarmListener) arg1;
                        this.isWaiting_ = visitor.visitBoolean(hasIsWaiting(), this.isWaiting_, other.hasIsWaiting(), other.isWaiting_);
                        this.triggerTimeElapsed_ = visitor.visitLong(hasTriggerTimeElapsed(), this.triggerTimeElapsed_, other.hasTriggerTimeElapsed(), other.triggerTimeElapsed_);
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
                                    this.isWaiting_ = input.readBool();
                                } else if (tag == 16) {
                                    this.bitField0_ |= 2;
                                    this.triggerTimeElapsed_ = input.readInt64();
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
                            synchronized (AlarmListener.class) {
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

            public static AlarmListener getDefaultInstance() {
                return DEFAULT_INSTANCE;
            }

            public static Parser<AlarmListener> parser() {
                return DEFAULT_INSTANCE.getParserForType();
            }
        }

        public static final class ExecutionStats extends GeneratedMessageLite<ExecutionStats, Builder> implements ExecutionStatsOrBuilder {
            public static final int BG_JOB_COUNT_IN_MAX_PERIOD_FIELD_NUMBER = 7;
            public static final int BG_JOB_COUNT_IN_WINDOW_FIELD_NUMBER = 5;
            private static final ExecutionStats DEFAULT_INSTANCE = new ExecutionStats();
            public static final int EXECUTION_TIME_IN_MAX_PERIOD_MS_FIELD_NUMBER = 6;
            public static final int EXECUTION_TIME_IN_WINDOW_MS_FIELD_NUMBER = 4;
            public static final int EXPIRATION_TIME_ELAPSED_FIELD_NUMBER = 2;
            public static final int IN_QUOTA_TIME_ELAPSED_FIELD_NUMBER = 8;
            public static final int JOB_COUNT_EXPIRATION_TIME_ELAPSED_FIELD_NUMBER = 9;
            public static final int JOB_COUNT_IN_RATE_LIMITING_WINDOW_FIELD_NUMBER = 10;
            public static final int JOB_COUNT_LIMIT_FIELD_NUMBER = 14;
            private static volatile Parser<ExecutionStats> PARSER = null;
            public static final int SESSION_COUNT_EXPIRATION_TIME_ELAPSED_FIELD_NUMBER = 12;
            public static final int SESSION_COUNT_IN_RATE_LIMITING_WINDOW_FIELD_NUMBER = 13;
            public static final int SESSION_COUNT_IN_WINDOW_FIELD_NUMBER = 11;
            public static final int SESSION_COUNT_LIMIT_FIELD_NUMBER = 15;
            public static final int STANDBY_BUCKET_FIELD_NUMBER = 1;
            public static final int WINDOW_SIZE_MS_FIELD_NUMBER = 3;
            private int bgJobCountInMaxPeriod_ = 0;
            private int bgJobCountInWindow_ = 0;
            private int bitField0_;
            private long executionTimeInMaxPeriodMs_ = 0;
            private long executionTimeInWindowMs_ = 0;
            private long expirationTimeElapsed_ = 0;
            private long inQuotaTimeElapsed_ = 0;
            private long jobCountExpirationTimeElapsed_ = 0;
            private int jobCountInRateLimitingWindow_ = 0;
            private int jobCountLimit_ = 0;
            private long sessionCountExpirationTimeElapsed_ = 0;
            private int sessionCountInRateLimitingWindow_ = 0;
            private int sessionCountInWindow_ = 0;
            private int sessionCountLimit_ = 0;
            private int standbyBucket_ = 0;
            private long windowSizeMs_ = 0;

            private ExecutionStats() {
            }

            @Override // com.android.server.job.StateControllerProto.QuotaController.ExecutionStatsOrBuilder
            public boolean hasStandbyBucket() {
                return (this.bitField0_ & 1) == 1;
            }

            @Override // com.android.server.job.StateControllerProto.QuotaController.ExecutionStatsOrBuilder
            public JobStatusDumpProto.Bucket getStandbyBucket() {
                JobStatusDumpProto.Bucket result = JobStatusDumpProto.Bucket.forNumber(this.standbyBucket_);
                return result == null ? JobStatusDumpProto.Bucket.ACTIVE : result;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void setStandbyBucket(JobStatusDumpProto.Bucket value) {
                if (value != null) {
                    this.bitField0_ |= 1;
                    this.standbyBucket_ = value.getNumber();
                    return;
                }
                throw new NullPointerException();
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void clearStandbyBucket() {
                this.bitField0_ &= -2;
                this.standbyBucket_ = 0;
            }

            @Override // com.android.server.job.StateControllerProto.QuotaController.ExecutionStatsOrBuilder
            public boolean hasExpirationTimeElapsed() {
                return (this.bitField0_ & 2) == 2;
            }

            @Override // com.android.server.job.StateControllerProto.QuotaController.ExecutionStatsOrBuilder
            public long getExpirationTimeElapsed() {
                return this.expirationTimeElapsed_;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void setExpirationTimeElapsed(long value) {
                this.bitField0_ |= 2;
                this.expirationTimeElapsed_ = value;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void clearExpirationTimeElapsed() {
                this.bitField0_ &= -3;
                this.expirationTimeElapsed_ = 0;
            }

            @Override // com.android.server.job.StateControllerProto.QuotaController.ExecutionStatsOrBuilder
            public boolean hasWindowSizeMs() {
                return (this.bitField0_ & 4) == 4;
            }

            @Override // com.android.server.job.StateControllerProto.QuotaController.ExecutionStatsOrBuilder
            public long getWindowSizeMs() {
                return this.windowSizeMs_;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void setWindowSizeMs(long value) {
                this.bitField0_ |= 4;
                this.windowSizeMs_ = value;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void clearWindowSizeMs() {
                this.bitField0_ &= -5;
                this.windowSizeMs_ = 0;
            }

            @Override // com.android.server.job.StateControllerProto.QuotaController.ExecutionStatsOrBuilder
            public boolean hasJobCountLimit() {
                return (this.bitField0_ & 8) == 8;
            }

            @Override // com.android.server.job.StateControllerProto.QuotaController.ExecutionStatsOrBuilder
            public int getJobCountLimit() {
                return this.jobCountLimit_;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void setJobCountLimit(int value) {
                this.bitField0_ |= 8;
                this.jobCountLimit_ = value;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void clearJobCountLimit() {
                this.bitField0_ &= -9;
                this.jobCountLimit_ = 0;
            }

            @Override // com.android.server.job.StateControllerProto.QuotaController.ExecutionStatsOrBuilder
            public boolean hasSessionCountLimit() {
                return (this.bitField0_ & 16) == 16;
            }

            @Override // com.android.server.job.StateControllerProto.QuotaController.ExecutionStatsOrBuilder
            public int getSessionCountLimit() {
                return this.sessionCountLimit_;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void setSessionCountLimit(int value) {
                this.bitField0_ |= 16;
                this.sessionCountLimit_ = value;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void clearSessionCountLimit() {
                this.bitField0_ &= -17;
                this.sessionCountLimit_ = 0;
            }

            @Override // com.android.server.job.StateControllerProto.QuotaController.ExecutionStatsOrBuilder
            public boolean hasExecutionTimeInWindowMs() {
                return (this.bitField0_ & 32) == 32;
            }

            @Override // com.android.server.job.StateControllerProto.QuotaController.ExecutionStatsOrBuilder
            public long getExecutionTimeInWindowMs() {
                return this.executionTimeInWindowMs_;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void setExecutionTimeInWindowMs(long value) {
                this.bitField0_ |= 32;
                this.executionTimeInWindowMs_ = value;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void clearExecutionTimeInWindowMs() {
                this.bitField0_ &= -33;
                this.executionTimeInWindowMs_ = 0;
            }

            @Override // com.android.server.job.StateControllerProto.QuotaController.ExecutionStatsOrBuilder
            public boolean hasBgJobCountInWindow() {
                return (this.bitField0_ & 64) == 64;
            }

            @Override // com.android.server.job.StateControllerProto.QuotaController.ExecutionStatsOrBuilder
            public int getBgJobCountInWindow() {
                return this.bgJobCountInWindow_;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void setBgJobCountInWindow(int value) {
                this.bitField0_ |= 64;
                this.bgJobCountInWindow_ = value;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void clearBgJobCountInWindow() {
                this.bitField0_ &= -65;
                this.bgJobCountInWindow_ = 0;
            }

            @Override // com.android.server.job.StateControllerProto.QuotaController.ExecutionStatsOrBuilder
            public boolean hasExecutionTimeInMaxPeriodMs() {
                return (this.bitField0_ & 128) == 128;
            }

            @Override // com.android.server.job.StateControllerProto.QuotaController.ExecutionStatsOrBuilder
            public long getExecutionTimeInMaxPeriodMs() {
                return this.executionTimeInMaxPeriodMs_;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void setExecutionTimeInMaxPeriodMs(long value) {
                this.bitField0_ |= 128;
                this.executionTimeInMaxPeriodMs_ = value;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void clearExecutionTimeInMaxPeriodMs() {
                this.bitField0_ &= -129;
                this.executionTimeInMaxPeriodMs_ = 0;
            }

            @Override // com.android.server.job.StateControllerProto.QuotaController.ExecutionStatsOrBuilder
            public boolean hasBgJobCountInMaxPeriod() {
                return (this.bitField0_ & 256) == 256;
            }

            @Override // com.android.server.job.StateControllerProto.QuotaController.ExecutionStatsOrBuilder
            public int getBgJobCountInMaxPeriod() {
                return this.bgJobCountInMaxPeriod_;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void setBgJobCountInMaxPeriod(int value) {
                this.bitField0_ |= 256;
                this.bgJobCountInMaxPeriod_ = value;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void clearBgJobCountInMaxPeriod() {
                this.bitField0_ &= -257;
                this.bgJobCountInMaxPeriod_ = 0;
            }

            @Override // com.android.server.job.StateControllerProto.QuotaController.ExecutionStatsOrBuilder
            public boolean hasSessionCountInWindow() {
                return (this.bitField0_ & 512) == 512;
            }

            @Override // com.android.server.job.StateControllerProto.QuotaController.ExecutionStatsOrBuilder
            public int getSessionCountInWindow() {
                return this.sessionCountInWindow_;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void setSessionCountInWindow(int value) {
                this.bitField0_ |= 512;
                this.sessionCountInWindow_ = value;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void clearSessionCountInWindow() {
                this.bitField0_ &= -513;
                this.sessionCountInWindow_ = 0;
            }

            @Override // com.android.server.job.StateControllerProto.QuotaController.ExecutionStatsOrBuilder
            public boolean hasInQuotaTimeElapsed() {
                return (this.bitField0_ & 1024) == 1024;
            }

            @Override // com.android.server.job.StateControllerProto.QuotaController.ExecutionStatsOrBuilder
            public long getInQuotaTimeElapsed() {
                return this.inQuotaTimeElapsed_;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void setInQuotaTimeElapsed(long value) {
                this.bitField0_ |= 1024;
                this.inQuotaTimeElapsed_ = value;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void clearInQuotaTimeElapsed() {
                this.bitField0_ &= -1025;
                this.inQuotaTimeElapsed_ = 0;
            }

            @Override // com.android.server.job.StateControllerProto.QuotaController.ExecutionStatsOrBuilder
            public boolean hasJobCountExpirationTimeElapsed() {
                return (this.bitField0_ & 2048) == 2048;
            }

            @Override // com.android.server.job.StateControllerProto.QuotaController.ExecutionStatsOrBuilder
            public long getJobCountExpirationTimeElapsed() {
                return this.jobCountExpirationTimeElapsed_;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void setJobCountExpirationTimeElapsed(long value) {
                this.bitField0_ |= 2048;
                this.jobCountExpirationTimeElapsed_ = value;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void clearJobCountExpirationTimeElapsed() {
                this.bitField0_ &= -2049;
                this.jobCountExpirationTimeElapsed_ = 0;
            }

            @Override // com.android.server.job.StateControllerProto.QuotaController.ExecutionStatsOrBuilder
            public boolean hasJobCountInRateLimitingWindow() {
                return (this.bitField0_ & 4096) == 4096;
            }

            @Override // com.android.server.job.StateControllerProto.QuotaController.ExecutionStatsOrBuilder
            public int getJobCountInRateLimitingWindow() {
                return this.jobCountInRateLimitingWindow_;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void setJobCountInRateLimitingWindow(int value) {
                this.bitField0_ |= 4096;
                this.jobCountInRateLimitingWindow_ = value;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void clearJobCountInRateLimitingWindow() {
                this.bitField0_ &= -4097;
                this.jobCountInRateLimitingWindow_ = 0;
            }

            @Override // com.android.server.job.StateControllerProto.QuotaController.ExecutionStatsOrBuilder
            public boolean hasSessionCountExpirationTimeElapsed() {
                return (this.bitField0_ & 8192) == 8192;
            }

            @Override // com.android.server.job.StateControllerProto.QuotaController.ExecutionStatsOrBuilder
            public long getSessionCountExpirationTimeElapsed() {
                return this.sessionCountExpirationTimeElapsed_;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void setSessionCountExpirationTimeElapsed(long value) {
                this.bitField0_ |= 8192;
                this.sessionCountExpirationTimeElapsed_ = value;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void clearSessionCountExpirationTimeElapsed() {
                this.bitField0_ &= -8193;
                this.sessionCountExpirationTimeElapsed_ = 0;
            }

            @Override // com.android.server.job.StateControllerProto.QuotaController.ExecutionStatsOrBuilder
            public boolean hasSessionCountInRateLimitingWindow() {
                return (this.bitField0_ & 16384) == 16384;
            }

            @Override // com.android.server.job.StateControllerProto.QuotaController.ExecutionStatsOrBuilder
            public int getSessionCountInRateLimitingWindow() {
                return this.sessionCountInRateLimitingWindow_;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void setSessionCountInRateLimitingWindow(int value) {
                this.bitField0_ |= 16384;
                this.sessionCountInRateLimitingWindow_ = value;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void clearSessionCountInRateLimitingWindow() {
                this.bitField0_ &= -16385;
                this.sessionCountInRateLimitingWindow_ = 0;
            }

            @Override // com.google.protobuf.MessageLite
            public void writeTo(CodedOutputStream output) throws IOException {
                if ((this.bitField0_ & 1) == 1) {
                    output.writeEnum(1, this.standbyBucket_);
                }
                if ((this.bitField0_ & 2) == 2) {
                    output.writeInt64(2, this.expirationTimeElapsed_);
                }
                if ((this.bitField0_ & 4) == 4) {
                    output.writeInt64(3, this.windowSizeMs_);
                }
                if ((this.bitField0_ & 32) == 32) {
                    output.writeInt64(4, this.executionTimeInWindowMs_);
                }
                if ((this.bitField0_ & 64) == 64) {
                    output.writeInt32(5, this.bgJobCountInWindow_);
                }
                if ((this.bitField0_ & 128) == 128) {
                    output.writeInt64(6, this.executionTimeInMaxPeriodMs_);
                }
                if ((this.bitField0_ & 256) == 256) {
                    output.writeInt32(7, this.bgJobCountInMaxPeriod_);
                }
                if ((this.bitField0_ & 1024) == 1024) {
                    output.writeInt64(8, this.inQuotaTimeElapsed_);
                }
                if ((this.bitField0_ & 2048) == 2048) {
                    output.writeInt64(9, this.jobCountExpirationTimeElapsed_);
                }
                if ((this.bitField0_ & 4096) == 4096) {
                    output.writeInt32(10, this.jobCountInRateLimitingWindow_);
                }
                if ((this.bitField0_ & 512) == 512) {
                    output.writeInt32(11, this.sessionCountInWindow_);
                }
                if ((this.bitField0_ & 8192) == 8192) {
                    output.writeInt64(12, this.sessionCountExpirationTimeElapsed_);
                }
                if ((this.bitField0_ & 16384) == 16384) {
                    output.writeInt32(13, this.sessionCountInRateLimitingWindow_);
                }
                if ((this.bitField0_ & 8) == 8) {
                    output.writeInt32(14, this.jobCountLimit_);
                }
                if ((this.bitField0_ & 16) == 16) {
                    output.writeInt32(15, this.sessionCountLimit_);
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
                    size2 = 0 + CodedOutputStream.computeEnumSize(1, this.standbyBucket_);
                }
                if ((this.bitField0_ & 2) == 2) {
                    size2 += CodedOutputStream.computeInt64Size(2, this.expirationTimeElapsed_);
                }
                if ((this.bitField0_ & 4) == 4) {
                    size2 += CodedOutputStream.computeInt64Size(3, this.windowSizeMs_);
                }
                if ((this.bitField0_ & 32) == 32) {
                    size2 += CodedOutputStream.computeInt64Size(4, this.executionTimeInWindowMs_);
                }
                if ((this.bitField0_ & 64) == 64) {
                    size2 += CodedOutputStream.computeInt32Size(5, this.bgJobCountInWindow_);
                }
                if ((this.bitField0_ & 128) == 128) {
                    size2 += CodedOutputStream.computeInt64Size(6, this.executionTimeInMaxPeriodMs_);
                }
                if ((this.bitField0_ & 256) == 256) {
                    size2 += CodedOutputStream.computeInt32Size(7, this.bgJobCountInMaxPeriod_);
                }
                if ((this.bitField0_ & 1024) == 1024) {
                    size2 += CodedOutputStream.computeInt64Size(8, this.inQuotaTimeElapsed_);
                }
                if ((this.bitField0_ & 2048) == 2048) {
                    size2 += CodedOutputStream.computeInt64Size(9, this.jobCountExpirationTimeElapsed_);
                }
                if ((this.bitField0_ & 4096) == 4096) {
                    size2 += CodedOutputStream.computeInt32Size(10, this.jobCountInRateLimitingWindow_);
                }
                if ((this.bitField0_ & 512) == 512) {
                    size2 += CodedOutputStream.computeInt32Size(11, this.sessionCountInWindow_);
                }
                if ((this.bitField0_ & 8192) == 8192) {
                    size2 += CodedOutputStream.computeInt64Size(12, this.sessionCountExpirationTimeElapsed_);
                }
                if ((this.bitField0_ & 16384) == 16384) {
                    size2 += CodedOutputStream.computeInt32Size(13, this.sessionCountInRateLimitingWindow_);
                }
                if ((this.bitField0_ & 8) == 8) {
                    size2 += CodedOutputStream.computeInt32Size(14, this.jobCountLimit_);
                }
                if ((this.bitField0_ & 16) == 16) {
                    size2 += CodedOutputStream.computeInt32Size(15, this.sessionCountLimit_);
                }
                int size3 = size2 + this.unknownFields.getSerializedSize();
                this.memoizedSerializedSize = size3;
                return size3;
            }

            public static ExecutionStats parseFrom(ByteString data) throws InvalidProtocolBufferException {
                return (ExecutionStats) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
            }

            public static ExecutionStats parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return (ExecutionStats) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
            }

            public static ExecutionStats parseFrom(byte[] data) throws InvalidProtocolBufferException {
                return (ExecutionStats) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
            }

            public static ExecutionStats parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return (ExecutionStats) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
            }

            public static ExecutionStats parseFrom(InputStream input) throws IOException {
                return (ExecutionStats) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
            }

            public static ExecutionStats parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (ExecutionStats) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static ExecutionStats parseDelimitedFrom(InputStream input) throws IOException {
                return (ExecutionStats) parseDelimitedFrom(DEFAULT_INSTANCE, input);
            }

            public static ExecutionStats parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (ExecutionStats) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static ExecutionStats parseFrom(CodedInputStream input) throws IOException {
                return (ExecutionStats) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
            }

            public static ExecutionStats parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (ExecutionStats) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static Builder newBuilder() {
                return (Builder) DEFAULT_INSTANCE.toBuilder();
            }

            public static Builder newBuilder(ExecutionStats prototype) {
                return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
            }

            public static final class Builder extends GeneratedMessageLite.Builder<ExecutionStats, Builder> implements ExecutionStatsOrBuilder {
                private Builder() {
                    super(ExecutionStats.DEFAULT_INSTANCE);
                }

                @Override // com.android.server.job.StateControllerProto.QuotaController.ExecutionStatsOrBuilder
                public boolean hasStandbyBucket() {
                    return ((ExecutionStats) this.instance).hasStandbyBucket();
                }

                @Override // com.android.server.job.StateControllerProto.QuotaController.ExecutionStatsOrBuilder
                public JobStatusDumpProto.Bucket getStandbyBucket() {
                    return ((ExecutionStats) this.instance).getStandbyBucket();
                }

                public Builder setStandbyBucket(JobStatusDumpProto.Bucket value) {
                    copyOnWrite();
                    ((ExecutionStats) this.instance).setStandbyBucket(value);
                    return this;
                }

                public Builder clearStandbyBucket() {
                    copyOnWrite();
                    ((ExecutionStats) this.instance).clearStandbyBucket();
                    return this;
                }

                @Override // com.android.server.job.StateControllerProto.QuotaController.ExecutionStatsOrBuilder
                public boolean hasExpirationTimeElapsed() {
                    return ((ExecutionStats) this.instance).hasExpirationTimeElapsed();
                }

                @Override // com.android.server.job.StateControllerProto.QuotaController.ExecutionStatsOrBuilder
                public long getExpirationTimeElapsed() {
                    return ((ExecutionStats) this.instance).getExpirationTimeElapsed();
                }

                public Builder setExpirationTimeElapsed(long value) {
                    copyOnWrite();
                    ((ExecutionStats) this.instance).setExpirationTimeElapsed(value);
                    return this;
                }

                public Builder clearExpirationTimeElapsed() {
                    copyOnWrite();
                    ((ExecutionStats) this.instance).clearExpirationTimeElapsed();
                    return this;
                }

                @Override // com.android.server.job.StateControllerProto.QuotaController.ExecutionStatsOrBuilder
                public boolean hasWindowSizeMs() {
                    return ((ExecutionStats) this.instance).hasWindowSizeMs();
                }

                @Override // com.android.server.job.StateControllerProto.QuotaController.ExecutionStatsOrBuilder
                public long getWindowSizeMs() {
                    return ((ExecutionStats) this.instance).getWindowSizeMs();
                }

                public Builder setWindowSizeMs(long value) {
                    copyOnWrite();
                    ((ExecutionStats) this.instance).setWindowSizeMs(value);
                    return this;
                }

                public Builder clearWindowSizeMs() {
                    copyOnWrite();
                    ((ExecutionStats) this.instance).clearWindowSizeMs();
                    return this;
                }

                @Override // com.android.server.job.StateControllerProto.QuotaController.ExecutionStatsOrBuilder
                public boolean hasJobCountLimit() {
                    return ((ExecutionStats) this.instance).hasJobCountLimit();
                }

                @Override // com.android.server.job.StateControllerProto.QuotaController.ExecutionStatsOrBuilder
                public int getJobCountLimit() {
                    return ((ExecutionStats) this.instance).getJobCountLimit();
                }

                public Builder setJobCountLimit(int value) {
                    copyOnWrite();
                    ((ExecutionStats) this.instance).setJobCountLimit(value);
                    return this;
                }

                public Builder clearJobCountLimit() {
                    copyOnWrite();
                    ((ExecutionStats) this.instance).clearJobCountLimit();
                    return this;
                }

                @Override // com.android.server.job.StateControllerProto.QuotaController.ExecutionStatsOrBuilder
                public boolean hasSessionCountLimit() {
                    return ((ExecutionStats) this.instance).hasSessionCountLimit();
                }

                @Override // com.android.server.job.StateControllerProto.QuotaController.ExecutionStatsOrBuilder
                public int getSessionCountLimit() {
                    return ((ExecutionStats) this.instance).getSessionCountLimit();
                }

                public Builder setSessionCountLimit(int value) {
                    copyOnWrite();
                    ((ExecutionStats) this.instance).setSessionCountLimit(value);
                    return this;
                }

                public Builder clearSessionCountLimit() {
                    copyOnWrite();
                    ((ExecutionStats) this.instance).clearSessionCountLimit();
                    return this;
                }

                @Override // com.android.server.job.StateControllerProto.QuotaController.ExecutionStatsOrBuilder
                public boolean hasExecutionTimeInWindowMs() {
                    return ((ExecutionStats) this.instance).hasExecutionTimeInWindowMs();
                }

                @Override // com.android.server.job.StateControllerProto.QuotaController.ExecutionStatsOrBuilder
                public long getExecutionTimeInWindowMs() {
                    return ((ExecutionStats) this.instance).getExecutionTimeInWindowMs();
                }

                public Builder setExecutionTimeInWindowMs(long value) {
                    copyOnWrite();
                    ((ExecutionStats) this.instance).setExecutionTimeInWindowMs(value);
                    return this;
                }

                public Builder clearExecutionTimeInWindowMs() {
                    copyOnWrite();
                    ((ExecutionStats) this.instance).clearExecutionTimeInWindowMs();
                    return this;
                }

                @Override // com.android.server.job.StateControllerProto.QuotaController.ExecutionStatsOrBuilder
                public boolean hasBgJobCountInWindow() {
                    return ((ExecutionStats) this.instance).hasBgJobCountInWindow();
                }

                @Override // com.android.server.job.StateControllerProto.QuotaController.ExecutionStatsOrBuilder
                public int getBgJobCountInWindow() {
                    return ((ExecutionStats) this.instance).getBgJobCountInWindow();
                }

                public Builder setBgJobCountInWindow(int value) {
                    copyOnWrite();
                    ((ExecutionStats) this.instance).setBgJobCountInWindow(value);
                    return this;
                }

                public Builder clearBgJobCountInWindow() {
                    copyOnWrite();
                    ((ExecutionStats) this.instance).clearBgJobCountInWindow();
                    return this;
                }

                @Override // com.android.server.job.StateControllerProto.QuotaController.ExecutionStatsOrBuilder
                public boolean hasExecutionTimeInMaxPeriodMs() {
                    return ((ExecutionStats) this.instance).hasExecutionTimeInMaxPeriodMs();
                }

                @Override // com.android.server.job.StateControllerProto.QuotaController.ExecutionStatsOrBuilder
                public long getExecutionTimeInMaxPeriodMs() {
                    return ((ExecutionStats) this.instance).getExecutionTimeInMaxPeriodMs();
                }

                public Builder setExecutionTimeInMaxPeriodMs(long value) {
                    copyOnWrite();
                    ((ExecutionStats) this.instance).setExecutionTimeInMaxPeriodMs(value);
                    return this;
                }

                public Builder clearExecutionTimeInMaxPeriodMs() {
                    copyOnWrite();
                    ((ExecutionStats) this.instance).clearExecutionTimeInMaxPeriodMs();
                    return this;
                }

                @Override // com.android.server.job.StateControllerProto.QuotaController.ExecutionStatsOrBuilder
                public boolean hasBgJobCountInMaxPeriod() {
                    return ((ExecutionStats) this.instance).hasBgJobCountInMaxPeriod();
                }

                @Override // com.android.server.job.StateControllerProto.QuotaController.ExecutionStatsOrBuilder
                public int getBgJobCountInMaxPeriod() {
                    return ((ExecutionStats) this.instance).getBgJobCountInMaxPeriod();
                }

                public Builder setBgJobCountInMaxPeriod(int value) {
                    copyOnWrite();
                    ((ExecutionStats) this.instance).setBgJobCountInMaxPeriod(value);
                    return this;
                }

                public Builder clearBgJobCountInMaxPeriod() {
                    copyOnWrite();
                    ((ExecutionStats) this.instance).clearBgJobCountInMaxPeriod();
                    return this;
                }

                @Override // com.android.server.job.StateControllerProto.QuotaController.ExecutionStatsOrBuilder
                public boolean hasSessionCountInWindow() {
                    return ((ExecutionStats) this.instance).hasSessionCountInWindow();
                }

                @Override // com.android.server.job.StateControllerProto.QuotaController.ExecutionStatsOrBuilder
                public int getSessionCountInWindow() {
                    return ((ExecutionStats) this.instance).getSessionCountInWindow();
                }

                public Builder setSessionCountInWindow(int value) {
                    copyOnWrite();
                    ((ExecutionStats) this.instance).setSessionCountInWindow(value);
                    return this;
                }

                public Builder clearSessionCountInWindow() {
                    copyOnWrite();
                    ((ExecutionStats) this.instance).clearSessionCountInWindow();
                    return this;
                }

                @Override // com.android.server.job.StateControllerProto.QuotaController.ExecutionStatsOrBuilder
                public boolean hasInQuotaTimeElapsed() {
                    return ((ExecutionStats) this.instance).hasInQuotaTimeElapsed();
                }

                @Override // com.android.server.job.StateControllerProto.QuotaController.ExecutionStatsOrBuilder
                public long getInQuotaTimeElapsed() {
                    return ((ExecutionStats) this.instance).getInQuotaTimeElapsed();
                }

                public Builder setInQuotaTimeElapsed(long value) {
                    copyOnWrite();
                    ((ExecutionStats) this.instance).setInQuotaTimeElapsed(value);
                    return this;
                }

                public Builder clearInQuotaTimeElapsed() {
                    copyOnWrite();
                    ((ExecutionStats) this.instance).clearInQuotaTimeElapsed();
                    return this;
                }

                @Override // com.android.server.job.StateControllerProto.QuotaController.ExecutionStatsOrBuilder
                public boolean hasJobCountExpirationTimeElapsed() {
                    return ((ExecutionStats) this.instance).hasJobCountExpirationTimeElapsed();
                }

                @Override // com.android.server.job.StateControllerProto.QuotaController.ExecutionStatsOrBuilder
                public long getJobCountExpirationTimeElapsed() {
                    return ((ExecutionStats) this.instance).getJobCountExpirationTimeElapsed();
                }

                public Builder setJobCountExpirationTimeElapsed(long value) {
                    copyOnWrite();
                    ((ExecutionStats) this.instance).setJobCountExpirationTimeElapsed(value);
                    return this;
                }

                public Builder clearJobCountExpirationTimeElapsed() {
                    copyOnWrite();
                    ((ExecutionStats) this.instance).clearJobCountExpirationTimeElapsed();
                    return this;
                }

                @Override // com.android.server.job.StateControllerProto.QuotaController.ExecutionStatsOrBuilder
                public boolean hasJobCountInRateLimitingWindow() {
                    return ((ExecutionStats) this.instance).hasJobCountInRateLimitingWindow();
                }

                @Override // com.android.server.job.StateControllerProto.QuotaController.ExecutionStatsOrBuilder
                public int getJobCountInRateLimitingWindow() {
                    return ((ExecutionStats) this.instance).getJobCountInRateLimitingWindow();
                }

                public Builder setJobCountInRateLimitingWindow(int value) {
                    copyOnWrite();
                    ((ExecutionStats) this.instance).setJobCountInRateLimitingWindow(value);
                    return this;
                }

                public Builder clearJobCountInRateLimitingWindow() {
                    copyOnWrite();
                    ((ExecutionStats) this.instance).clearJobCountInRateLimitingWindow();
                    return this;
                }

                @Override // com.android.server.job.StateControllerProto.QuotaController.ExecutionStatsOrBuilder
                public boolean hasSessionCountExpirationTimeElapsed() {
                    return ((ExecutionStats) this.instance).hasSessionCountExpirationTimeElapsed();
                }

                @Override // com.android.server.job.StateControllerProto.QuotaController.ExecutionStatsOrBuilder
                public long getSessionCountExpirationTimeElapsed() {
                    return ((ExecutionStats) this.instance).getSessionCountExpirationTimeElapsed();
                }

                public Builder setSessionCountExpirationTimeElapsed(long value) {
                    copyOnWrite();
                    ((ExecutionStats) this.instance).setSessionCountExpirationTimeElapsed(value);
                    return this;
                }

                public Builder clearSessionCountExpirationTimeElapsed() {
                    copyOnWrite();
                    ((ExecutionStats) this.instance).clearSessionCountExpirationTimeElapsed();
                    return this;
                }

                @Override // com.android.server.job.StateControllerProto.QuotaController.ExecutionStatsOrBuilder
                public boolean hasSessionCountInRateLimitingWindow() {
                    return ((ExecutionStats) this.instance).hasSessionCountInRateLimitingWindow();
                }

                @Override // com.android.server.job.StateControllerProto.QuotaController.ExecutionStatsOrBuilder
                public int getSessionCountInRateLimitingWindow() {
                    return ((ExecutionStats) this.instance).getSessionCountInRateLimitingWindow();
                }

                public Builder setSessionCountInRateLimitingWindow(int value) {
                    copyOnWrite();
                    ((ExecutionStats) this.instance).setSessionCountInRateLimitingWindow(value);
                    return this;
                }

                public Builder clearSessionCountInRateLimitingWindow() {
                    copyOnWrite();
                    ((ExecutionStats) this.instance).clearSessionCountInRateLimitingWindow();
                    return this;
                }
            }

            /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
            /* access modifiers changed from: protected */
            @Override // com.google.protobuf.GeneratedMessageLite
            public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
                switch (method) {
                    case NEW_MUTABLE_INSTANCE:
                        return new ExecutionStats();
                    case IS_INITIALIZED:
                        return DEFAULT_INSTANCE;
                    case MAKE_IMMUTABLE:
                        return null;
                    case NEW_BUILDER:
                        return new Builder();
                    case VISIT:
                        GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                        ExecutionStats other = (ExecutionStats) arg1;
                        this.standbyBucket_ = visitor.visitInt(hasStandbyBucket(), this.standbyBucket_, other.hasStandbyBucket(), other.standbyBucket_);
                        this.expirationTimeElapsed_ = visitor.visitLong(hasExpirationTimeElapsed(), this.expirationTimeElapsed_, other.hasExpirationTimeElapsed(), other.expirationTimeElapsed_);
                        this.windowSizeMs_ = visitor.visitLong(hasWindowSizeMs(), this.windowSizeMs_, other.hasWindowSizeMs(), other.windowSizeMs_);
                        this.jobCountLimit_ = visitor.visitInt(hasJobCountLimit(), this.jobCountLimit_, other.hasJobCountLimit(), other.jobCountLimit_);
                        this.sessionCountLimit_ = visitor.visitInt(hasSessionCountLimit(), this.sessionCountLimit_, other.hasSessionCountLimit(), other.sessionCountLimit_);
                        this.executionTimeInWindowMs_ = visitor.visitLong(hasExecutionTimeInWindowMs(), this.executionTimeInWindowMs_, other.hasExecutionTimeInWindowMs(), other.executionTimeInWindowMs_);
                        this.bgJobCountInWindow_ = visitor.visitInt(hasBgJobCountInWindow(), this.bgJobCountInWindow_, other.hasBgJobCountInWindow(), other.bgJobCountInWindow_);
                        this.executionTimeInMaxPeriodMs_ = visitor.visitLong(hasExecutionTimeInMaxPeriodMs(), this.executionTimeInMaxPeriodMs_, other.hasExecutionTimeInMaxPeriodMs(), other.executionTimeInMaxPeriodMs_);
                        this.bgJobCountInMaxPeriod_ = visitor.visitInt(hasBgJobCountInMaxPeriod(), this.bgJobCountInMaxPeriod_, other.hasBgJobCountInMaxPeriod(), other.bgJobCountInMaxPeriod_);
                        this.sessionCountInWindow_ = visitor.visitInt(hasSessionCountInWindow(), this.sessionCountInWindow_, other.hasSessionCountInWindow(), other.sessionCountInWindow_);
                        this.inQuotaTimeElapsed_ = visitor.visitLong(hasInQuotaTimeElapsed(), this.inQuotaTimeElapsed_, other.hasInQuotaTimeElapsed(), other.inQuotaTimeElapsed_);
                        this.jobCountExpirationTimeElapsed_ = visitor.visitLong(hasJobCountExpirationTimeElapsed(), this.jobCountExpirationTimeElapsed_, other.hasJobCountExpirationTimeElapsed(), other.jobCountExpirationTimeElapsed_);
                        this.jobCountInRateLimitingWindow_ = visitor.visitInt(hasJobCountInRateLimitingWindow(), this.jobCountInRateLimitingWindow_, other.hasJobCountInRateLimitingWindow(), other.jobCountInRateLimitingWindow_);
                        this.sessionCountExpirationTimeElapsed_ = visitor.visitLong(hasSessionCountExpirationTimeElapsed(), this.sessionCountExpirationTimeElapsed_, other.hasSessionCountExpirationTimeElapsed(), other.sessionCountExpirationTimeElapsed_);
                        this.sessionCountInRateLimitingWindow_ = visitor.visitInt(hasSessionCountInRateLimitingWindow(), this.sessionCountInRateLimitingWindow_, other.hasSessionCountInRateLimitingWindow(), other.sessionCountInRateLimitingWindow_);
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
                                switch (tag) {
                                    case 0:
                                        done = true;
                                        break;
                                    case 8:
                                        int rawValue = input.readEnum();
                                        if (JobStatusDumpProto.Bucket.forNumber(rawValue) != null) {
                                            this.bitField0_ = 1 | this.bitField0_;
                                            this.standbyBucket_ = rawValue;
                                            break;
                                        } else {
                                            super.mergeVarintField(1, rawValue);
                                            break;
                                        }
                                    case 16:
                                        this.bitField0_ |= 2;
                                        this.expirationTimeElapsed_ = input.readInt64();
                                        break;
                                    case 24:
                                        this.bitField0_ |= 4;
                                        this.windowSizeMs_ = input.readInt64();
                                        break;
                                    case 32:
                                        this.bitField0_ |= 32;
                                        this.executionTimeInWindowMs_ = input.readInt64();
                                        break;
                                    case 40:
                                        this.bitField0_ |= 64;
                                        this.bgJobCountInWindow_ = input.readInt32();
                                        break;
                                    case 48:
                                        this.bitField0_ |= 128;
                                        this.executionTimeInMaxPeriodMs_ = input.readInt64();
                                        break;
                                    case 56:
                                        this.bitField0_ |= 256;
                                        this.bgJobCountInMaxPeriod_ = input.readInt32();
                                        break;
                                    case 64:
                                        this.bitField0_ |= 1024;
                                        this.inQuotaTimeElapsed_ = input.readInt64();
                                        break;
                                    case 72:
                                        this.bitField0_ |= 2048;
                                        this.jobCountExpirationTimeElapsed_ = input.readInt64();
                                        break;
                                    case 80:
                                        this.bitField0_ |= 4096;
                                        this.jobCountInRateLimitingWindow_ = input.readInt32();
                                        break;
                                    case 88:
                                        this.bitField0_ |= 512;
                                        this.sessionCountInWindow_ = input.readInt32();
                                        break;
                                    case 96:
                                        this.bitField0_ |= 8192;
                                        this.sessionCountExpirationTimeElapsed_ = input.readInt64();
                                        break;
                                    case 104:
                                        this.bitField0_ |= 16384;
                                        this.sessionCountInRateLimitingWindow_ = input.readInt32();
                                        break;
                                    case 112:
                                        this.bitField0_ |= 8;
                                        this.jobCountLimit_ = input.readInt32();
                                        break;
                                    case 120:
                                        this.bitField0_ |= 16;
                                        this.sessionCountLimit_ = input.readInt32();
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
                            synchronized (ExecutionStats.class) {
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

            public static ExecutionStats getDefaultInstance() {
                return DEFAULT_INSTANCE;
            }

            public static Parser<ExecutionStats> parser() {
                return DEFAULT_INSTANCE.getParserForType();
            }
        }

        public static final class Package extends GeneratedMessageLite<Package, Builder> implements PackageOrBuilder {
            private static final Package DEFAULT_INSTANCE = new Package();
            public static final int NAME_FIELD_NUMBER = 2;
            private static volatile Parser<Package> PARSER = null;
            public static final int USER_ID_FIELD_NUMBER = 1;
            private int bitField0_;
            private String name_ = "";
            private int userId_ = 0;

            private Package() {
            }

            @Override // com.android.server.job.StateControllerProto.QuotaController.PackageOrBuilder
            public boolean hasUserId() {
                return (this.bitField0_ & 1) == 1;
            }

            @Override // com.android.server.job.StateControllerProto.QuotaController.PackageOrBuilder
            public int getUserId() {
                return this.userId_;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void setUserId(int value) {
                this.bitField0_ |= 1;
                this.userId_ = value;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void clearUserId() {
                this.bitField0_ &= -2;
                this.userId_ = 0;
            }

            @Override // com.android.server.job.StateControllerProto.QuotaController.PackageOrBuilder
            public boolean hasName() {
                return (this.bitField0_ & 2) == 2;
            }

            @Override // com.android.server.job.StateControllerProto.QuotaController.PackageOrBuilder
            public String getName() {
                return this.name_;
            }

            @Override // com.android.server.job.StateControllerProto.QuotaController.PackageOrBuilder
            public ByteString getNameBytes() {
                return ByteString.copyFromUtf8(this.name_);
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void setName(String value) {
                if (value != null) {
                    this.bitField0_ |= 2;
                    this.name_ = value;
                    return;
                }
                throw new NullPointerException();
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void clearName() {
                this.bitField0_ &= -3;
                this.name_ = getDefaultInstance().getName();
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void setNameBytes(ByteString value) {
                if (value != null) {
                    this.bitField0_ |= 2;
                    this.name_ = value.toStringUtf8();
                    return;
                }
                throw new NullPointerException();
            }

            @Override // com.google.protobuf.MessageLite
            public void writeTo(CodedOutputStream output) throws IOException {
                if ((this.bitField0_ & 1) == 1) {
                    output.writeInt32(1, this.userId_);
                }
                if ((this.bitField0_ & 2) == 2) {
                    output.writeString(2, getName());
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
                    size2 = 0 + CodedOutputStream.computeInt32Size(1, this.userId_);
                }
                if ((this.bitField0_ & 2) == 2) {
                    size2 += CodedOutputStream.computeStringSize(2, getName());
                }
                int size3 = size2 + this.unknownFields.getSerializedSize();
                this.memoizedSerializedSize = size3;
                return size3;
            }

            public static Package parseFrom(ByteString data) throws InvalidProtocolBufferException {
                return (Package) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
            }

            public static Package parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return (Package) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
            }

            public static Package parseFrom(byte[] data) throws InvalidProtocolBufferException {
                return (Package) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
            }

            public static Package parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return (Package) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
            }

            public static Package parseFrom(InputStream input) throws IOException {
                return (Package) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
            }

            public static Package parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (Package) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static Package parseDelimitedFrom(InputStream input) throws IOException {
                return (Package) parseDelimitedFrom(DEFAULT_INSTANCE, input);
            }

            public static Package parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (Package) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static Package parseFrom(CodedInputStream input) throws IOException {
                return (Package) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
            }

            public static Package parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (Package) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static Builder newBuilder() {
                return (Builder) DEFAULT_INSTANCE.toBuilder();
            }

            public static Builder newBuilder(Package prototype) {
                return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
            }

            public static final class Builder extends GeneratedMessageLite.Builder<Package, Builder> implements PackageOrBuilder {
                private Builder() {
                    super(Package.DEFAULT_INSTANCE);
                }

                @Override // com.android.server.job.StateControllerProto.QuotaController.PackageOrBuilder
                public boolean hasUserId() {
                    return ((Package) this.instance).hasUserId();
                }

                @Override // com.android.server.job.StateControllerProto.QuotaController.PackageOrBuilder
                public int getUserId() {
                    return ((Package) this.instance).getUserId();
                }

                public Builder setUserId(int value) {
                    copyOnWrite();
                    ((Package) this.instance).setUserId(value);
                    return this;
                }

                public Builder clearUserId() {
                    copyOnWrite();
                    ((Package) this.instance).clearUserId();
                    return this;
                }

                @Override // com.android.server.job.StateControllerProto.QuotaController.PackageOrBuilder
                public boolean hasName() {
                    return ((Package) this.instance).hasName();
                }

                @Override // com.android.server.job.StateControllerProto.QuotaController.PackageOrBuilder
                public String getName() {
                    return ((Package) this.instance).getName();
                }

                @Override // com.android.server.job.StateControllerProto.QuotaController.PackageOrBuilder
                public ByteString getNameBytes() {
                    return ((Package) this.instance).getNameBytes();
                }

                public Builder setName(String value) {
                    copyOnWrite();
                    ((Package) this.instance).setName(value);
                    return this;
                }

                public Builder clearName() {
                    copyOnWrite();
                    ((Package) this.instance).clearName();
                    return this;
                }

                public Builder setNameBytes(ByteString value) {
                    copyOnWrite();
                    ((Package) this.instance).setNameBytes(value);
                    return this;
                }
            }

            /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
            /* access modifiers changed from: protected */
            @Override // com.google.protobuf.GeneratedMessageLite
            public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
                switch (method) {
                    case NEW_MUTABLE_INSTANCE:
                        return new Package();
                    case IS_INITIALIZED:
                        return DEFAULT_INSTANCE;
                    case MAKE_IMMUTABLE:
                        return null;
                    case NEW_BUILDER:
                        return new Builder();
                    case VISIT:
                        GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                        Package other = (Package) arg1;
                        this.userId_ = visitor.visitInt(hasUserId(), this.userId_, other.hasUserId(), other.userId_);
                        this.name_ = visitor.visitString(hasName(), this.name_, other.hasName(), other.name_);
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
                                    this.userId_ = input.readInt32();
                                } else if (tag == 18) {
                                    String s = input.readString();
                                    this.bitField0_ |= 2;
                                    this.name_ = s;
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
                            synchronized (Package.class) {
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

            public static Package getDefaultInstance() {
                return DEFAULT_INSTANCE;
            }

            public static Parser<Package> parser() {
                return DEFAULT_INSTANCE.getParserForType();
            }
        }

        public static final class TimingSession extends GeneratedMessageLite<TimingSession, Builder> implements TimingSessionOrBuilder {
            public static final int BG_JOB_COUNT_FIELD_NUMBER = 3;
            private static final TimingSession DEFAULT_INSTANCE = new TimingSession();
            public static final int END_TIME_ELAPSED_FIELD_NUMBER = 2;
            private static volatile Parser<TimingSession> PARSER = null;
            public static final int START_TIME_ELAPSED_FIELD_NUMBER = 1;
            private int bgJobCount_ = 0;
            private int bitField0_;
            private long endTimeElapsed_ = 0;
            private long startTimeElapsed_ = 0;

            private TimingSession() {
            }

            @Override // com.android.server.job.StateControllerProto.QuotaController.TimingSessionOrBuilder
            public boolean hasStartTimeElapsed() {
                return (this.bitField0_ & 1) == 1;
            }

            @Override // com.android.server.job.StateControllerProto.QuotaController.TimingSessionOrBuilder
            public long getStartTimeElapsed() {
                return this.startTimeElapsed_;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void setStartTimeElapsed(long value) {
                this.bitField0_ |= 1;
                this.startTimeElapsed_ = value;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void clearStartTimeElapsed() {
                this.bitField0_ &= -2;
                this.startTimeElapsed_ = 0;
            }

            @Override // com.android.server.job.StateControllerProto.QuotaController.TimingSessionOrBuilder
            public boolean hasEndTimeElapsed() {
                return (this.bitField0_ & 2) == 2;
            }

            @Override // com.android.server.job.StateControllerProto.QuotaController.TimingSessionOrBuilder
            public long getEndTimeElapsed() {
                return this.endTimeElapsed_;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void setEndTimeElapsed(long value) {
                this.bitField0_ |= 2;
                this.endTimeElapsed_ = value;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void clearEndTimeElapsed() {
                this.bitField0_ &= -3;
                this.endTimeElapsed_ = 0;
            }

            @Override // com.android.server.job.StateControllerProto.QuotaController.TimingSessionOrBuilder
            public boolean hasBgJobCount() {
                return (this.bitField0_ & 4) == 4;
            }

            @Override // com.android.server.job.StateControllerProto.QuotaController.TimingSessionOrBuilder
            public int getBgJobCount() {
                return this.bgJobCount_;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void setBgJobCount(int value) {
                this.bitField0_ |= 4;
                this.bgJobCount_ = value;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void clearBgJobCount() {
                this.bitField0_ &= -5;
                this.bgJobCount_ = 0;
            }

            @Override // com.google.protobuf.MessageLite
            public void writeTo(CodedOutputStream output) throws IOException {
                if ((this.bitField0_ & 1) == 1) {
                    output.writeInt64(1, this.startTimeElapsed_);
                }
                if ((this.bitField0_ & 2) == 2) {
                    output.writeInt64(2, this.endTimeElapsed_);
                }
                if ((this.bitField0_ & 4) == 4) {
                    output.writeInt32(3, this.bgJobCount_);
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
                    size2 = 0 + CodedOutputStream.computeInt64Size(1, this.startTimeElapsed_);
                }
                if ((this.bitField0_ & 2) == 2) {
                    size2 += CodedOutputStream.computeInt64Size(2, this.endTimeElapsed_);
                }
                if ((this.bitField0_ & 4) == 4) {
                    size2 += CodedOutputStream.computeInt32Size(3, this.bgJobCount_);
                }
                int size3 = size2 + this.unknownFields.getSerializedSize();
                this.memoizedSerializedSize = size3;
                return size3;
            }

            public static TimingSession parseFrom(ByteString data) throws InvalidProtocolBufferException {
                return (TimingSession) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
            }

            public static TimingSession parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return (TimingSession) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
            }

            public static TimingSession parseFrom(byte[] data) throws InvalidProtocolBufferException {
                return (TimingSession) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
            }

            public static TimingSession parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return (TimingSession) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
            }

            public static TimingSession parseFrom(InputStream input) throws IOException {
                return (TimingSession) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
            }

            public static TimingSession parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (TimingSession) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static TimingSession parseDelimitedFrom(InputStream input) throws IOException {
                return (TimingSession) parseDelimitedFrom(DEFAULT_INSTANCE, input);
            }

            public static TimingSession parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (TimingSession) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static TimingSession parseFrom(CodedInputStream input) throws IOException {
                return (TimingSession) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
            }

            public static TimingSession parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (TimingSession) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static Builder newBuilder() {
                return (Builder) DEFAULT_INSTANCE.toBuilder();
            }

            public static Builder newBuilder(TimingSession prototype) {
                return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
            }

            public static final class Builder extends GeneratedMessageLite.Builder<TimingSession, Builder> implements TimingSessionOrBuilder {
                private Builder() {
                    super(TimingSession.DEFAULT_INSTANCE);
                }

                @Override // com.android.server.job.StateControllerProto.QuotaController.TimingSessionOrBuilder
                public boolean hasStartTimeElapsed() {
                    return ((TimingSession) this.instance).hasStartTimeElapsed();
                }

                @Override // com.android.server.job.StateControllerProto.QuotaController.TimingSessionOrBuilder
                public long getStartTimeElapsed() {
                    return ((TimingSession) this.instance).getStartTimeElapsed();
                }

                public Builder setStartTimeElapsed(long value) {
                    copyOnWrite();
                    ((TimingSession) this.instance).setStartTimeElapsed(value);
                    return this;
                }

                public Builder clearStartTimeElapsed() {
                    copyOnWrite();
                    ((TimingSession) this.instance).clearStartTimeElapsed();
                    return this;
                }

                @Override // com.android.server.job.StateControllerProto.QuotaController.TimingSessionOrBuilder
                public boolean hasEndTimeElapsed() {
                    return ((TimingSession) this.instance).hasEndTimeElapsed();
                }

                @Override // com.android.server.job.StateControllerProto.QuotaController.TimingSessionOrBuilder
                public long getEndTimeElapsed() {
                    return ((TimingSession) this.instance).getEndTimeElapsed();
                }

                public Builder setEndTimeElapsed(long value) {
                    copyOnWrite();
                    ((TimingSession) this.instance).setEndTimeElapsed(value);
                    return this;
                }

                public Builder clearEndTimeElapsed() {
                    copyOnWrite();
                    ((TimingSession) this.instance).clearEndTimeElapsed();
                    return this;
                }

                @Override // com.android.server.job.StateControllerProto.QuotaController.TimingSessionOrBuilder
                public boolean hasBgJobCount() {
                    return ((TimingSession) this.instance).hasBgJobCount();
                }

                @Override // com.android.server.job.StateControllerProto.QuotaController.TimingSessionOrBuilder
                public int getBgJobCount() {
                    return ((TimingSession) this.instance).getBgJobCount();
                }

                public Builder setBgJobCount(int value) {
                    copyOnWrite();
                    ((TimingSession) this.instance).setBgJobCount(value);
                    return this;
                }

                public Builder clearBgJobCount() {
                    copyOnWrite();
                    ((TimingSession) this.instance).clearBgJobCount();
                    return this;
                }
            }

            /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
            /* access modifiers changed from: protected */
            @Override // com.google.protobuf.GeneratedMessageLite
            public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
                switch (method) {
                    case NEW_MUTABLE_INSTANCE:
                        return new TimingSession();
                    case IS_INITIALIZED:
                        return DEFAULT_INSTANCE;
                    case MAKE_IMMUTABLE:
                        return null;
                    case NEW_BUILDER:
                        return new Builder();
                    case VISIT:
                        GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                        TimingSession other = (TimingSession) arg1;
                        this.startTimeElapsed_ = visitor.visitLong(hasStartTimeElapsed(), this.startTimeElapsed_, other.hasStartTimeElapsed(), other.startTimeElapsed_);
                        this.endTimeElapsed_ = visitor.visitLong(hasEndTimeElapsed(), this.endTimeElapsed_, other.hasEndTimeElapsed(), other.endTimeElapsed_);
                        this.bgJobCount_ = visitor.visitInt(hasBgJobCount(), this.bgJobCount_, other.hasBgJobCount(), other.bgJobCount_);
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
                                    this.startTimeElapsed_ = input.readInt64();
                                } else if (tag == 16) {
                                    this.bitField0_ |= 2;
                                    this.endTimeElapsed_ = input.readInt64();
                                } else if (tag == 24) {
                                    this.bitField0_ |= 4;
                                    this.bgJobCount_ = input.readInt32();
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
                            synchronized (TimingSession.class) {
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

            public static TimingSession getDefaultInstance() {
                return DEFAULT_INSTANCE;
            }

            public static Parser<TimingSession> parser() {
                return DEFAULT_INSTANCE.getParserForType();
            }
        }

        public static final class Timer extends GeneratedMessageLite<Timer, Builder> implements TimerOrBuilder {
            public static final int BG_JOB_COUNT_FIELD_NUMBER = 4;
            private static final Timer DEFAULT_INSTANCE = new Timer();
            public static final int IS_ACTIVE_FIELD_NUMBER = 2;
            private static volatile Parser<Timer> PARSER = null;
            public static final int PKG_FIELD_NUMBER = 1;
            public static final int RUNNING_JOBS_FIELD_NUMBER = 5;
            public static final int START_TIME_ELAPSED_FIELD_NUMBER = 3;
            private int bgJobCount_ = 0;
            private int bitField0_;
            private boolean isActive_ = false;
            private Package pkg_;
            private Internal.ProtobufList<JobStatusShortInfoProto> runningJobs_ = emptyProtobufList();
            private long startTimeElapsed_ = 0;

            private Timer() {
            }

            @Override // com.android.server.job.StateControllerProto.QuotaController.TimerOrBuilder
            public boolean hasPkg() {
                return (this.bitField0_ & 1) == 1;
            }

            @Override // com.android.server.job.StateControllerProto.QuotaController.TimerOrBuilder
            public Package getPkg() {
                Package r0 = this.pkg_;
                return r0 == null ? Package.getDefaultInstance() : r0;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void setPkg(Package value) {
                if (value != null) {
                    this.pkg_ = value;
                    this.bitField0_ |= 1;
                    return;
                }
                throw new NullPointerException();
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void setPkg(Package.Builder builderForValue) {
                this.pkg_ = (Package) builderForValue.build();
                this.bitField0_ |= 1;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void mergePkg(Package value) {
                Package r0 = this.pkg_;
                if (r0 == null || r0 == Package.getDefaultInstance()) {
                    this.pkg_ = value;
                } else {
                    this.pkg_ = (Package) ((Package.Builder) Package.newBuilder(this.pkg_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
                }
                this.bitField0_ |= 1;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void clearPkg() {
                this.pkg_ = null;
                this.bitField0_ &= -2;
            }

            @Override // com.android.server.job.StateControllerProto.QuotaController.TimerOrBuilder
            public boolean hasIsActive() {
                return (this.bitField0_ & 2) == 2;
            }

            @Override // com.android.server.job.StateControllerProto.QuotaController.TimerOrBuilder
            public boolean getIsActive() {
                return this.isActive_;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void setIsActive(boolean value) {
                this.bitField0_ |= 2;
                this.isActive_ = value;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void clearIsActive() {
                this.bitField0_ &= -3;
                this.isActive_ = false;
            }

            @Override // com.android.server.job.StateControllerProto.QuotaController.TimerOrBuilder
            public boolean hasStartTimeElapsed() {
                return (this.bitField0_ & 4) == 4;
            }

            @Override // com.android.server.job.StateControllerProto.QuotaController.TimerOrBuilder
            public long getStartTimeElapsed() {
                return this.startTimeElapsed_;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void setStartTimeElapsed(long value) {
                this.bitField0_ |= 4;
                this.startTimeElapsed_ = value;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void clearStartTimeElapsed() {
                this.bitField0_ &= -5;
                this.startTimeElapsed_ = 0;
            }

            @Override // com.android.server.job.StateControllerProto.QuotaController.TimerOrBuilder
            public boolean hasBgJobCount() {
                return (this.bitField0_ & 8) == 8;
            }

            @Override // com.android.server.job.StateControllerProto.QuotaController.TimerOrBuilder
            public int getBgJobCount() {
                return this.bgJobCount_;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void setBgJobCount(int value) {
                this.bitField0_ |= 8;
                this.bgJobCount_ = value;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void clearBgJobCount() {
                this.bitField0_ &= -9;
                this.bgJobCount_ = 0;
            }

            @Override // com.android.server.job.StateControllerProto.QuotaController.TimerOrBuilder
            public List<JobStatusShortInfoProto> getRunningJobsList() {
                return this.runningJobs_;
            }

            public List<? extends JobStatusShortInfoProtoOrBuilder> getRunningJobsOrBuilderList() {
                return this.runningJobs_;
            }

            @Override // com.android.server.job.StateControllerProto.QuotaController.TimerOrBuilder
            public int getRunningJobsCount() {
                return this.runningJobs_.size();
            }

            @Override // com.android.server.job.StateControllerProto.QuotaController.TimerOrBuilder
            public JobStatusShortInfoProto getRunningJobs(int index) {
                return this.runningJobs_.get(index);
            }

            public JobStatusShortInfoProtoOrBuilder getRunningJobsOrBuilder(int index) {
                return this.runningJobs_.get(index);
            }

            private void ensureRunningJobsIsMutable() {
                if (!this.runningJobs_.isModifiable()) {
                    this.runningJobs_ = GeneratedMessageLite.mutableCopy(this.runningJobs_);
                }
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void setRunningJobs(int index, JobStatusShortInfoProto value) {
                if (value != null) {
                    ensureRunningJobsIsMutable();
                    this.runningJobs_.set(index, value);
                    return;
                }
                throw new NullPointerException();
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void setRunningJobs(int index, JobStatusShortInfoProto.Builder builderForValue) {
                ensureRunningJobsIsMutable();
                this.runningJobs_.set(index, (JobStatusShortInfoProto) builderForValue.build());
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void addRunningJobs(JobStatusShortInfoProto value) {
                if (value != null) {
                    ensureRunningJobsIsMutable();
                    this.runningJobs_.add(value);
                    return;
                }
                throw new NullPointerException();
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void addRunningJobs(int index, JobStatusShortInfoProto value) {
                if (value != null) {
                    ensureRunningJobsIsMutable();
                    this.runningJobs_.add(index, value);
                    return;
                }
                throw new NullPointerException();
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void addRunningJobs(JobStatusShortInfoProto.Builder builderForValue) {
                ensureRunningJobsIsMutable();
                this.runningJobs_.add((JobStatusShortInfoProto) builderForValue.build());
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void addRunningJobs(int index, JobStatusShortInfoProto.Builder builderForValue) {
                ensureRunningJobsIsMutable();
                this.runningJobs_.add(index, (JobStatusShortInfoProto) builderForValue.build());
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void addAllRunningJobs(Iterable<? extends JobStatusShortInfoProto> values) {
                ensureRunningJobsIsMutable();
                AbstractMessageLite.addAll(values, this.runningJobs_);
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void clearRunningJobs() {
                this.runningJobs_ = emptyProtobufList();
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void removeRunningJobs(int index) {
                ensureRunningJobsIsMutable();
                this.runningJobs_.remove(index);
            }

            @Override // com.google.protobuf.MessageLite
            public void writeTo(CodedOutputStream output) throws IOException {
                if ((this.bitField0_ & 1) == 1) {
                    output.writeMessage(1, getPkg());
                }
                if ((this.bitField0_ & 2) == 2) {
                    output.writeBool(2, this.isActive_);
                }
                if ((this.bitField0_ & 4) == 4) {
                    output.writeInt64(3, this.startTimeElapsed_);
                }
                if ((this.bitField0_ & 8) == 8) {
                    output.writeInt32(4, this.bgJobCount_);
                }
                for (int i = 0; i < this.runningJobs_.size(); i++) {
                    output.writeMessage(5, this.runningJobs_.get(i));
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
                    size2 = 0 + CodedOutputStream.computeMessageSize(1, getPkg());
                }
                if ((this.bitField0_ & 2) == 2) {
                    size2 += CodedOutputStream.computeBoolSize(2, this.isActive_);
                }
                if ((this.bitField0_ & 4) == 4) {
                    size2 += CodedOutputStream.computeInt64Size(3, this.startTimeElapsed_);
                }
                if ((this.bitField0_ & 8) == 8) {
                    size2 += CodedOutputStream.computeInt32Size(4, this.bgJobCount_);
                }
                for (int i = 0; i < this.runningJobs_.size(); i++) {
                    size2 += CodedOutputStream.computeMessageSize(5, this.runningJobs_.get(i));
                }
                int size3 = size2 + this.unknownFields.getSerializedSize();
                this.memoizedSerializedSize = size3;
                return size3;
            }

            public static Timer parseFrom(ByteString data) throws InvalidProtocolBufferException {
                return (Timer) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
            }

            public static Timer parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return (Timer) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
            }

            public static Timer parseFrom(byte[] data) throws InvalidProtocolBufferException {
                return (Timer) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
            }

            public static Timer parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return (Timer) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
            }

            public static Timer parseFrom(InputStream input) throws IOException {
                return (Timer) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
            }

            public static Timer parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (Timer) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static Timer parseDelimitedFrom(InputStream input) throws IOException {
                return (Timer) parseDelimitedFrom(DEFAULT_INSTANCE, input);
            }

            public static Timer parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (Timer) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static Timer parseFrom(CodedInputStream input) throws IOException {
                return (Timer) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
            }

            public static Timer parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (Timer) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static Builder newBuilder() {
                return (Builder) DEFAULT_INSTANCE.toBuilder();
            }

            public static Builder newBuilder(Timer prototype) {
                return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
            }

            public static final class Builder extends GeneratedMessageLite.Builder<Timer, Builder> implements TimerOrBuilder {
                private Builder() {
                    super(Timer.DEFAULT_INSTANCE);
                }

                @Override // com.android.server.job.StateControllerProto.QuotaController.TimerOrBuilder
                public boolean hasPkg() {
                    return ((Timer) this.instance).hasPkg();
                }

                @Override // com.android.server.job.StateControllerProto.QuotaController.TimerOrBuilder
                public Package getPkg() {
                    return ((Timer) this.instance).getPkg();
                }

                public Builder setPkg(Package value) {
                    copyOnWrite();
                    ((Timer) this.instance).setPkg((Timer) value);
                    return this;
                }

                public Builder setPkg(Package.Builder builderForValue) {
                    copyOnWrite();
                    ((Timer) this.instance).setPkg((Timer) builderForValue);
                    return this;
                }

                public Builder mergePkg(Package value) {
                    copyOnWrite();
                    ((Timer) this.instance).mergePkg(value);
                    return this;
                }

                public Builder clearPkg() {
                    copyOnWrite();
                    ((Timer) this.instance).clearPkg();
                    return this;
                }

                @Override // com.android.server.job.StateControllerProto.QuotaController.TimerOrBuilder
                public boolean hasIsActive() {
                    return ((Timer) this.instance).hasIsActive();
                }

                @Override // com.android.server.job.StateControllerProto.QuotaController.TimerOrBuilder
                public boolean getIsActive() {
                    return ((Timer) this.instance).getIsActive();
                }

                public Builder setIsActive(boolean value) {
                    copyOnWrite();
                    ((Timer) this.instance).setIsActive(value);
                    return this;
                }

                public Builder clearIsActive() {
                    copyOnWrite();
                    ((Timer) this.instance).clearIsActive();
                    return this;
                }

                @Override // com.android.server.job.StateControllerProto.QuotaController.TimerOrBuilder
                public boolean hasStartTimeElapsed() {
                    return ((Timer) this.instance).hasStartTimeElapsed();
                }

                @Override // com.android.server.job.StateControllerProto.QuotaController.TimerOrBuilder
                public long getStartTimeElapsed() {
                    return ((Timer) this.instance).getStartTimeElapsed();
                }

                public Builder setStartTimeElapsed(long value) {
                    copyOnWrite();
                    ((Timer) this.instance).setStartTimeElapsed(value);
                    return this;
                }

                public Builder clearStartTimeElapsed() {
                    copyOnWrite();
                    ((Timer) this.instance).clearStartTimeElapsed();
                    return this;
                }

                @Override // com.android.server.job.StateControllerProto.QuotaController.TimerOrBuilder
                public boolean hasBgJobCount() {
                    return ((Timer) this.instance).hasBgJobCount();
                }

                @Override // com.android.server.job.StateControllerProto.QuotaController.TimerOrBuilder
                public int getBgJobCount() {
                    return ((Timer) this.instance).getBgJobCount();
                }

                public Builder setBgJobCount(int value) {
                    copyOnWrite();
                    ((Timer) this.instance).setBgJobCount(value);
                    return this;
                }

                public Builder clearBgJobCount() {
                    copyOnWrite();
                    ((Timer) this.instance).clearBgJobCount();
                    return this;
                }

                @Override // com.android.server.job.StateControllerProto.QuotaController.TimerOrBuilder
                public List<JobStatusShortInfoProto> getRunningJobsList() {
                    return Collections.unmodifiableList(((Timer) this.instance).getRunningJobsList());
                }

                @Override // com.android.server.job.StateControllerProto.QuotaController.TimerOrBuilder
                public int getRunningJobsCount() {
                    return ((Timer) this.instance).getRunningJobsCount();
                }

                @Override // com.android.server.job.StateControllerProto.QuotaController.TimerOrBuilder
                public JobStatusShortInfoProto getRunningJobs(int index) {
                    return ((Timer) this.instance).getRunningJobs(index);
                }

                public Builder setRunningJobs(int index, JobStatusShortInfoProto value) {
                    copyOnWrite();
                    ((Timer) this.instance).setRunningJobs((Timer) index, (int) value);
                    return this;
                }

                public Builder setRunningJobs(int index, JobStatusShortInfoProto.Builder builderForValue) {
                    copyOnWrite();
                    ((Timer) this.instance).setRunningJobs((Timer) index, (int) builderForValue);
                    return this;
                }

                public Builder addRunningJobs(JobStatusShortInfoProto value) {
                    copyOnWrite();
                    ((Timer) this.instance).addRunningJobs((Timer) value);
                    return this;
                }

                public Builder addRunningJobs(int index, JobStatusShortInfoProto value) {
                    copyOnWrite();
                    ((Timer) this.instance).addRunningJobs((Timer) index, (int) value);
                    return this;
                }

                public Builder addRunningJobs(JobStatusShortInfoProto.Builder builderForValue) {
                    copyOnWrite();
                    ((Timer) this.instance).addRunningJobs((Timer) builderForValue);
                    return this;
                }

                public Builder addRunningJobs(int index, JobStatusShortInfoProto.Builder builderForValue) {
                    copyOnWrite();
                    ((Timer) this.instance).addRunningJobs((Timer) index, (int) builderForValue);
                    return this;
                }

                public Builder addAllRunningJobs(Iterable<? extends JobStatusShortInfoProto> values) {
                    copyOnWrite();
                    ((Timer) this.instance).addAllRunningJobs(values);
                    return this;
                }

                public Builder clearRunningJobs() {
                    copyOnWrite();
                    ((Timer) this.instance).clearRunningJobs();
                    return this;
                }

                public Builder removeRunningJobs(int index) {
                    copyOnWrite();
                    ((Timer) this.instance).removeRunningJobs(index);
                    return this;
                }
            }

            /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
            /* access modifiers changed from: protected */
            @Override // com.google.protobuf.GeneratedMessageLite
            public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
                switch (method) {
                    case NEW_MUTABLE_INSTANCE:
                        return new Timer();
                    case IS_INITIALIZED:
                        return DEFAULT_INSTANCE;
                    case MAKE_IMMUTABLE:
                        this.runningJobs_.makeImmutable();
                        return null;
                    case NEW_BUILDER:
                        return new Builder();
                    case VISIT:
                        GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                        Timer other = (Timer) arg1;
                        this.pkg_ = (Package) visitor.visitMessage(this.pkg_, other.pkg_);
                        this.isActive_ = visitor.visitBoolean(hasIsActive(), this.isActive_, other.hasIsActive(), other.isActive_);
                        this.startTimeElapsed_ = visitor.visitLong(hasStartTimeElapsed(), this.startTimeElapsed_, other.hasStartTimeElapsed(), other.startTimeElapsed_);
                        this.bgJobCount_ = visitor.visitInt(hasBgJobCount(), this.bgJobCount_, other.hasBgJobCount(), other.bgJobCount_);
                        this.runningJobs_ = visitor.visitList(this.runningJobs_, other.runningJobs_);
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
                                    Package.Builder subBuilder = null;
                                    if ((this.bitField0_ & 1) == 1) {
                                        subBuilder = (Package.Builder) this.pkg_.toBuilder();
                                    }
                                    this.pkg_ = (Package) input.readMessage(Package.parser(), extensionRegistry);
                                    if (subBuilder != null) {
                                        subBuilder.mergeFrom((GeneratedMessageLite) this.pkg_);
                                        this.pkg_ = (Package) subBuilder.buildPartial();
                                    }
                                    this.bitField0_ |= 1;
                                } else if (tag == 16) {
                                    this.bitField0_ |= 2;
                                    this.isActive_ = input.readBool();
                                } else if (tag == 24) {
                                    this.bitField0_ |= 4;
                                    this.startTimeElapsed_ = input.readInt64();
                                } else if (tag == 32) {
                                    this.bitField0_ |= 8;
                                    this.bgJobCount_ = input.readInt32();
                                } else if (tag == 42) {
                                    if (!this.runningJobs_.isModifiable()) {
                                        this.runningJobs_ = GeneratedMessageLite.mutableCopy(this.runningJobs_);
                                    }
                                    this.runningJobs_.add((JobStatusShortInfoProto) input.readMessage(JobStatusShortInfoProto.parser(), extensionRegistry));
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
                            synchronized (Timer.class) {
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

            public static Timer getDefaultInstance() {
                return DEFAULT_INSTANCE;
            }

            public static Parser<Timer> parser() {
                return DEFAULT_INSTANCE.getParserForType();
            }
        }

        public static final class PackageStats extends GeneratedMessageLite<PackageStats, Builder> implements PackageStatsOrBuilder {
            private static final PackageStats DEFAULT_INSTANCE = new PackageStats();
            public static final int EXECUTION_STATS_FIELD_NUMBER = 4;
            public static final int IN_QUOTA_ALARM_LISTENER_FIELD_NUMBER = 5;
            private static volatile Parser<PackageStats> PARSER = null;
            public static final int PKG_FIELD_NUMBER = 1;
            public static final int SAVED_SESSIONS_FIELD_NUMBER = 3;
            public static final int TIMER_FIELD_NUMBER = 2;
            private int bitField0_;
            private Internal.ProtobufList<ExecutionStats> executionStats_ = emptyProtobufList();
            private AlarmListener inQuotaAlarmListener_;
            private Package pkg_;
            private Internal.ProtobufList<TimingSession> savedSessions_ = emptyProtobufList();
            private Timer timer_;

            private PackageStats() {
            }

            @Override // com.android.server.job.StateControllerProto.QuotaController.PackageStatsOrBuilder
            public boolean hasPkg() {
                return (this.bitField0_ & 1) == 1;
            }

            @Override // com.android.server.job.StateControllerProto.QuotaController.PackageStatsOrBuilder
            public Package getPkg() {
                Package r0 = this.pkg_;
                return r0 == null ? Package.getDefaultInstance() : r0;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void setPkg(Package value) {
                if (value != null) {
                    this.pkg_ = value;
                    this.bitField0_ |= 1;
                    return;
                }
                throw new NullPointerException();
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void setPkg(Package.Builder builderForValue) {
                this.pkg_ = (Package) builderForValue.build();
                this.bitField0_ |= 1;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void mergePkg(Package value) {
                Package r0 = this.pkg_;
                if (r0 == null || r0 == Package.getDefaultInstance()) {
                    this.pkg_ = value;
                } else {
                    this.pkg_ = (Package) ((Package.Builder) Package.newBuilder(this.pkg_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
                }
                this.bitField0_ |= 1;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void clearPkg() {
                this.pkg_ = null;
                this.bitField0_ &= -2;
            }

            @Override // com.android.server.job.StateControllerProto.QuotaController.PackageStatsOrBuilder
            public boolean hasTimer() {
                return (this.bitField0_ & 2) == 2;
            }

            @Override // com.android.server.job.StateControllerProto.QuotaController.PackageStatsOrBuilder
            public Timer getTimer() {
                Timer timer = this.timer_;
                return timer == null ? Timer.getDefaultInstance() : timer;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void setTimer(Timer value) {
                if (value != null) {
                    this.timer_ = value;
                    this.bitField0_ |= 2;
                    return;
                }
                throw new NullPointerException();
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void setTimer(Timer.Builder builderForValue) {
                this.timer_ = (Timer) builderForValue.build();
                this.bitField0_ |= 2;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void mergeTimer(Timer value) {
                Timer timer = this.timer_;
                if (timer == null || timer == Timer.getDefaultInstance()) {
                    this.timer_ = value;
                } else {
                    this.timer_ = (Timer) ((Timer.Builder) Timer.newBuilder(this.timer_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
                }
                this.bitField0_ |= 2;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void clearTimer() {
                this.timer_ = null;
                this.bitField0_ &= -3;
            }

            @Override // com.android.server.job.StateControllerProto.QuotaController.PackageStatsOrBuilder
            public List<TimingSession> getSavedSessionsList() {
                return this.savedSessions_;
            }

            public List<? extends TimingSessionOrBuilder> getSavedSessionsOrBuilderList() {
                return this.savedSessions_;
            }

            @Override // com.android.server.job.StateControllerProto.QuotaController.PackageStatsOrBuilder
            public int getSavedSessionsCount() {
                return this.savedSessions_.size();
            }

            @Override // com.android.server.job.StateControllerProto.QuotaController.PackageStatsOrBuilder
            public TimingSession getSavedSessions(int index) {
                return this.savedSessions_.get(index);
            }

            public TimingSessionOrBuilder getSavedSessionsOrBuilder(int index) {
                return this.savedSessions_.get(index);
            }

            private void ensureSavedSessionsIsMutable() {
                if (!this.savedSessions_.isModifiable()) {
                    this.savedSessions_ = GeneratedMessageLite.mutableCopy(this.savedSessions_);
                }
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void setSavedSessions(int index, TimingSession value) {
                if (value != null) {
                    ensureSavedSessionsIsMutable();
                    this.savedSessions_.set(index, value);
                    return;
                }
                throw new NullPointerException();
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void setSavedSessions(int index, TimingSession.Builder builderForValue) {
                ensureSavedSessionsIsMutable();
                this.savedSessions_.set(index, (TimingSession) builderForValue.build());
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void addSavedSessions(TimingSession value) {
                if (value != null) {
                    ensureSavedSessionsIsMutable();
                    this.savedSessions_.add(value);
                    return;
                }
                throw new NullPointerException();
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void addSavedSessions(int index, TimingSession value) {
                if (value != null) {
                    ensureSavedSessionsIsMutable();
                    this.savedSessions_.add(index, value);
                    return;
                }
                throw new NullPointerException();
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void addSavedSessions(TimingSession.Builder builderForValue) {
                ensureSavedSessionsIsMutable();
                this.savedSessions_.add((TimingSession) builderForValue.build());
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void addSavedSessions(int index, TimingSession.Builder builderForValue) {
                ensureSavedSessionsIsMutable();
                this.savedSessions_.add(index, (TimingSession) builderForValue.build());
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void addAllSavedSessions(Iterable<? extends TimingSession> values) {
                ensureSavedSessionsIsMutable();
                AbstractMessageLite.addAll(values, this.savedSessions_);
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void clearSavedSessions() {
                this.savedSessions_ = emptyProtobufList();
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void removeSavedSessions(int index) {
                ensureSavedSessionsIsMutable();
                this.savedSessions_.remove(index);
            }

            @Override // com.android.server.job.StateControllerProto.QuotaController.PackageStatsOrBuilder
            public List<ExecutionStats> getExecutionStatsList() {
                return this.executionStats_;
            }

            public List<? extends ExecutionStatsOrBuilder> getExecutionStatsOrBuilderList() {
                return this.executionStats_;
            }

            @Override // com.android.server.job.StateControllerProto.QuotaController.PackageStatsOrBuilder
            public int getExecutionStatsCount() {
                return this.executionStats_.size();
            }

            @Override // com.android.server.job.StateControllerProto.QuotaController.PackageStatsOrBuilder
            public ExecutionStats getExecutionStats(int index) {
                return this.executionStats_.get(index);
            }

            public ExecutionStatsOrBuilder getExecutionStatsOrBuilder(int index) {
                return this.executionStats_.get(index);
            }

            private void ensureExecutionStatsIsMutable() {
                if (!this.executionStats_.isModifiable()) {
                    this.executionStats_ = GeneratedMessageLite.mutableCopy(this.executionStats_);
                }
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void setExecutionStats(int index, ExecutionStats value) {
                if (value != null) {
                    ensureExecutionStatsIsMutable();
                    this.executionStats_.set(index, value);
                    return;
                }
                throw new NullPointerException();
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void setExecutionStats(int index, ExecutionStats.Builder builderForValue) {
                ensureExecutionStatsIsMutable();
                this.executionStats_.set(index, (ExecutionStats) builderForValue.build());
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void addExecutionStats(ExecutionStats value) {
                if (value != null) {
                    ensureExecutionStatsIsMutable();
                    this.executionStats_.add(value);
                    return;
                }
                throw new NullPointerException();
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void addExecutionStats(int index, ExecutionStats value) {
                if (value != null) {
                    ensureExecutionStatsIsMutable();
                    this.executionStats_.add(index, value);
                    return;
                }
                throw new NullPointerException();
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void addExecutionStats(ExecutionStats.Builder builderForValue) {
                ensureExecutionStatsIsMutable();
                this.executionStats_.add((ExecutionStats) builderForValue.build());
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void addExecutionStats(int index, ExecutionStats.Builder builderForValue) {
                ensureExecutionStatsIsMutable();
                this.executionStats_.add(index, (ExecutionStats) builderForValue.build());
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void addAllExecutionStats(Iterable<? extends ExecutionStats> values) {
                ensureExecutionStatsIsMutable();
                AbstractMessageLite.addAll(values, this.executionStats_);
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void clearExecutionStats() {
                this.executionStats_ = emptyProtobufList();
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void removeExecutionStats(int index) {
                ensureExecutionStatsIsMutable();
                this.executionStats_.remove(index);
            }

            @Override // com.android.server.job.StateControllerProto.QuotaController.PackageStatsOrBuilder
            public boolean hasInQuotaAlarmListener() {
                return (this.bitField0_ & 4) == 4;
            }

            @Override // com.android.server.job.StateControllerProto.QuotaController.PackageStatsOrBuilder
            public AlarmListener getInQuotaAlarmListener() {
                AlarmListener alarmListener = this.inQuotaAlarmListener_;
                return alarmListener == null ? AlarmListener.getDefaultInstance() : alarmListener;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void setInQuotaAlarmListener(AlarmListener value) {
                if (value != null) {
                    this.inQuotaAlarmListener_ = value;
                    this.bitField0_ |= 4;
                    return;
                }
                throw new NullPointerException();
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void setInQuotaAlarmListener(AlarmListener.Builder builderForValue) {
                this.inQuotaAlarmListener_ = (AlarmListener) builderForValue.build();
                this.bitField0_ |= 4;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void mergeInQuotaAlarmListener(AlarmListener value) {
                AlarmListener alarmListener = this.inQuotaAlarmListener_;
                if (alarmListener == null || alarmListener == AlarmListener.getDefaultInstance()) {
                    this.inQuotaAlarmListener_ = value;
                } else {
                    this.inQuotaAlarmListener_ = (AlarmListener) ((AlarmListener.Builder) AlarmListener.newBuilder(this.inQuotaAlarmListener_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
                }
                this.bitField0_ |= 4;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void clearInQuotaAlarmListener() {
                this.inQuotaAlarmListener_ = null;
                this.bitField0_ &= -5;
            }

            @Override // com.google.protobuf.MessageLite
            public void writeTo(CodedOutputStream output) throws IOException {
                if ((this.bitField0_ & 1) == 1) {
                    output.writeMessage(1, getPkg());
                }
                if ((this.bitField0_ & 2) == 2) {
                    output.writeMessage(2, getTimer());
                }
                for (int i = 0; i < this.savedSessions_.size(); i++) {
                    output.writeMessage(3, this.savedSessions_.get(i));
                }
                for (int i2 = 0; i2 < this.executionStats_.size(); i2++) {
                    output.writeMessage(4, this.executionStats_.get(i2));
                }
                if ((this.bitField0_ & 4) == 4) {
                    output.writeMessage(5, getInQuotaAlarmListener());
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
                    size2 = 0 + CodedOutputStream.computeMessageSize(1, getPkg());
                }
                if ((this.bitField0_ & 2) == 2) {
                    size2 += CodedOutputStream.computeMessageSize(2, getTimer());
                }
                for (int i = 0; i < this.savedSessions_.size(); i++) {
                    size2 += CodedOutputStream.computeMessageSize(3, this.savedSessions_.get(i));
                }
                for (int i2 = 0; i2 < this.executionStats_.size(); i2++) {
                    size2 += CodedOutputStream.computeMessageSize(4, this.executionStats_.get(i2));
                }
                if ((this.bitField0_ & 4) == 4) {
                    size2 += CodedOutputStream.computeMessageSize(5, getInQuotaAlarmListener());
                }
                int size3 = size2 + this.unknownFields.getSerializedSize();
                this.memoizedSerializedSize = size3;
                return size3;
            }

            public static PackageStats parseFrom(ByteString data) throws InvalidProtocolBufferException {
                return (PackageStats) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
            }

            public static PackageStats parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return (PackageStats) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
            }

            public static PackageStats parseFrom(byte[] data) throws InvalidProtocolBufferException {
                return (PackageStats) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
            }

            public static PackageStats parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return (PackageStats) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
            }

            public static PackageStats parseFrom(InputStream input) throws IOException {
                return (PackageStats) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
            }

            public static PackageStats parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (PackageStats) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static PackageStats parseDelimitedFrom(InputStream input) throws IOException {
                return (PackageStats) parseDelimitedFrom(DEFAULT_INSTANCE, input);
            }

            public static PackageStats parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (PackageStats) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static PackageStats parseFrom(CodedInputStream input) throws IOException {
                return (PackageStats) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
            }

            public static PackageStats parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (PackageStats) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static Builder newBuilder() {
                return (Builder) DEFAULT_INSTANCE.toBuilder();
            }

            public static Builder newBuilder(PackageStats prototype) {
                return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
            }

            public static final class Builder extends GeneratedMessageLite.Builder<PackageStats, Builder> implements PackageStatsOrBuilder {
                private Builder() {
                    super(PackageStats.DEFAULT_INSTANCE);
                }

                @Override // com.android.server.job.StateControllerProto.QuotaController.PackageStatsOrBuilder
                public boolean hasPkg() {
                    return ((PackageStats) this.instance).hasPkg();
                }

                @Override // com.android.server.job.StateControllerProto.QuotaController.PackageStatsOrBuilder
                public Package getPkg() {
                    return ((PackageStats) this.instance).getPkg();
                }

                public Builder setPkg(Package value) {
                    copyOnWrite();
                    ((PackageStats) this.instance).setPkg((PackageStats) value);
                    return this;
                }

                public Builder setPkg(Package.Builder builderForValue) {
                    copyOnWrite();
                    ((PackageStats) this.instance).setPkg((PackageStats) builderForValue);
                    return this;
                }

                public Builder mergePkg(Package value) {
                    copyOnWrite();
                    ((PackageStats) this.instance).mergePkg(value);
                    return this;
                }

                public Builder clearPkg() {
                    copyOnWrite();
                    ((PackageStats) this.instance).clearPkg();
                    return this;
                }

                @Override // com.android.server.job.StateControllerProto.QuotaController.PackageStatsOrBuilder
                public boolean hasTimer() {
                    return ((PackageStats) this.instance).hasTimer();
                }

                @Override // com.android.server.job.StateControllerProto.QuotaController.PackageStatsOrBuilder
                public Timer getTimer() {
                    return ((PackageStats) this.instance).getTimer();
                }

                public Builder setTimer(Timer value) {
                    copyOnWrite();
                    ((PackageStats) this.instance).setTimer((PackageStats) value);
                    return this;
                }

                public Builder setTimer(Timer.Builder builderForValue) {
                    copyOnWrite();
                    ((PackageStats) this.instance).setTimer((PackageStats) builderForValue);
                    return this;
                }

                public Builder mergeTimer(Timer value) {
                    copyOnWrite();
                    ((PackageStats) this.instance).mergeTimer(value);
                    return this;
                }

                public Builder clearTimer() {
                    copyOnWrite();
                    ((PackageStats) this.instance).clearTimer();
                    return this;
                }

                @Override // com.android.server.job.StateControllerProto.QuotaController.PackageStatsOrBuilder
                public List<TimingSession> getSavedSessionsList() {
                    return Collections.unmodifiableList(((PackageStats) this.instance).getSavedSessionsList());
                }

                @Override // com.android.server.job.StateControllerProto.QuotaController.PackageStatsOrBuilder
                public int getSavedSessionsCount() {
                    return ((PackageStats) this.instance).getSavedSessionsCount();
                }

                @Override // com.android.server.job.StateControllerProto.QuotaController.PackageStatsOrBuilder
                public TimingSession getSavedSessions(int index) {
                    return ((PackageStats) this.instance).getSavedSessions(index);
                }

                public Builder setSavedSessions(int index, TimingSession value) {
                    copyOnWrite();
                    ((PackageStats) this.instance).setSavedSessions((PackageStats) index, (int) value);
                    return this;
                }

                public Builder setSavedSessions(int index, TimingSession.Builder builderForValue) {
                    copyOnWrite();
                    ((PackageStats) this.instance).setSavedSessions((PackageStats) index, (int) builderForValue);
                    return this;
                }

                public Builder addSavedSessions(TimingSession value) {
                    copyOnWrite();
                    ((PackageStats) this.instance).addSavedSessions((PackageStats) value);
                    return this;
                }

                public Builder addSavedSessions(int index, TimingSession value) {
                    copyOnWrite();
                    ((PackageStats) this.instance).addSavedSessions((PackageStats) index, (int) value);
                    return this;
                }

                public Builder addSavedSessions(TimingSession.Builder builderForValue) {
                    copyOnWrite();
                    ((PackageStats) this.instance).addSavedSessions((PackageStats) builderForValue);
                    return this;
                }

                public Builder addSavedSessions(int index, TimingSession.Builder builderForValue) {
                    copyOnWrite();
                    ((PackageStats) this.instance).addSavedSessions((PackageStats) index, (int) builderForValue);
                    return this;
                }

                public Builder addAllSavedSessions(Iterable<? extends TimingSession> values) {
                    copyOnWrite();
                    ((PackageStats) this.instance).addAllSavedSessions(values);
                    return this;
                }

                public Builder clearSavedSessions() {
                    copyOnWrite();
                    ((PackageStats) this.instance).clearSavedSessions();
                    return this;
                }

                public Builder removeSavedSessions(int index) {
                    copyOnWrite();
                    ((PackageStats) this.instance).removeSavedSessions(index);
                    return this;
                }

                @Override // com.android.server.job.StateControllerProto.QuotaController.PackageStatsOrBuilder
                public List<ExecutionStats> getExecutionStatsList() {
                    return Collections.unmodifiableList(((PackageStats) this.instance).getExecutionStatsList());
                }

                @Override // com.android.server.job.StateControllerProto.QuotaController.PackageStatsOrBuilder
                public int getExecutionStatsCount() {
                    return ((PackageStats) this.instance).getExecutionStatsCount();
                }

                @Override // com.android.server.job.StateControllerProto.QuotaController.PackageStatsOrBuilder
                public ExecutionStats getExecutionStats(int index) {
                    return ((PackageStats) this.instance).getExecutionStats(index);
                }

                public Builder setExecutionStats(int index, ExecutionStats value) {
                    copyOnWrite();
                    ((PackageStats) this.instance).setExecutionStats((PackageStats) index, (int) value);
                    return this;
                }

                public Builder setExecutionStats(int index, ExecutionStats.Builder builderForValue) {
                    copyOnWrite();
                    ((PackageStats) this.instance).setExecutionStats((PackageStats) index, (int) builderForValue);
                    return this;
                }

                public Builder addExecutionStats(ExecutionStats value) {
                    copyOnWrite();
                    ((PackageStats) this.instance).addExecutionStats((PackageStats) value);
                    return this;
                }

                public Builder addExecutionStats(int index, ExecutionStats value) {
                    copyOnWrite();
                    ((PackageStats) this.instance).addExecutionStats((PackageStats) index, (int) value);
                    return this;
                }

                public Builder addExecutionStats(ExecutionStats.Builder builderForValue) {
                    copyOnWrite();
                    ((PackageStats) this.instance).addExecutionStats((PackageStats) builderForValue);
                    return this;
                }

                public Builder addExecutionStats(int index, ExecutionStats.Builder builderForValue) {
                    copyOnWrite();
                    ((PackageStats) this.instance).addExecutionStats((PackageStats) index, (int) builderForValue);
                    return this;
                }

                public Builder addAllExecutionStats(Iterable<? extends ExecutionStats> values) {
                    copyOnWrite();
                    ((PackageStats) this.instance).addAllExecutionStats(values);
                    return this;
                }

                public Builder clearExecutionStats() {
                    copyOnWrite();
                    ((PackageStats) this.instance).clearExecutionStats();
                    return this;
                }

                public Builder removeExecutionStats(int index) {
                    copyOnWrite();
                    ((PackageStats) this.instance).removeExecutionStats(index);
                    return this;
                }

                @Override // com.android.server.job.StateControllerProto.QuotaController.PackageStatsOrBuilder
                public boolean hasInQuotaAlarmListener() {
                    return ((PackageStats) this.instance).hasInQuotaAlarmListener();
                }

                @Override // com.android.server.job.StateControllerProto.QuotaController.PackageStatsOrBuilder
                public AlarmListener getInQuotaAlarmListener() {
                    return ((PackageStats) this.instance).getInQuotaAlarmListener();
                }

                public Builder setInQuotaAlarmListener(AlarmListener value) {
                    copyOnWrite();
                    ((PackageStats) this.instance).setInQuotaAlarmListener((PackageStats) value);
                    return this;
                }

                public Builder setInQuotaAlarmListener(AlarmListener.Builder builderForValue) {
                    copyOnWrite();
                    ((PackageStats) this.instance).setInQuotaAlarmListener((PackageStats) builderForValue);
                    return this;
                }

                public Builder mergeInQuotaAlarmListener(AlarmListener value) {
                    copyOnWrite();
                    ((PackageStats) this.instance).mergeInQuotaAlarmListener(value);
                    return this;
                }

                public Builder clearInQuotaAlarmListener() {
                    copyOnWrite();
                    ((PackageStats) this.instance).clearInQuotaAlarmListener();
                    return this;
                }
            }

            /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
            /* access modifiers changed from: protected */
            @Override // com.google.protobuf.GeneratedMessageLite
            public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
                switch (method) {
                    case NEW_MUTABLE_INSTANCE:
                        return new PackageStats();
                    case IS_INITIALIZED:
                        return DEFAULT_INSTANCE;
                    case MAKE_IMMUTABLE:
                        this.savedSessions_.makeImmutable();
                        this.executionStats_.makeImmutable();
                        return null;
                    case NEW_BUILDER:
                        return new Builder();
                    case VISIT:
                        GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                        PackageStats other = (PackageStats) arg1;
                        this.pkg_ = (Package) visitor.visitMessage(this.pkg_, other.pkg_);
                        this.timer_ = (Timer) visitor.visitMessage(this.timer_, other.timer_);
                        this.savedSessions_ = visitor.visitList(this.savedSessions_, other.savedSessions_);
                        this.executionStats_ = visitor.visitList(this.executionStats_, other.executionStats_);
                        this.inQuotaAlarmListener_ = (AlarmListener) visitor.visitMessage(this.inQuotaAlarmListener_, other.inQuotaAlarmListener_);
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
                                    Package.Builder subBuilder = null;
                                    if ((this.bitField0_ & 1) == 1) {
                                        subBuilder = (Package.Builder) this.pkg_.toBuilder();
                                    }
                                    this.pkg_ = (Package) input.readMessage(Package.parser(), extensionRegistry);
                                    if (subBuilder != null) {
                                        subBuilder.mergeFrom((GeneratedMessageLite) this.pkg_);
                                        this.pkg_ = (Package) subBuilder.buildPartial();
                                    }
                                    this.bitField0_ |= 1;
                                } else if (tag == 18) {
                                    Timer.Builder subBuilder2 = null;
                                    if ((this.bitField0_ & 2) == 2) {
                                        subBuilder2 = (Timer.Builder) this.timer_.toBuilder();
                                    }
                                    this.timer_ = (Timer) input.readMessage(Timer.parser(), extensionRegistry);
                                    if (subBuilder2 != null) {
                                        subBuilder2.mergeFrom((GeneratedMessageLite) this.timer_);
                                        this.timer_ = (Timer) subBuilder2.buildPartial();
                                    }
                                    this.bitField0_ |= 2;
                                } else if (tag == 26) {
                                    if (!this.savedSessions_.isModifiable()) {
                                        this.savedSessions_ = GeneratedMessageLite.mutableCopy(this.savedSessions_);
                                    }
                                    this.savedSessions_.add((TimingSession) input.readMessage(TimingSession.parser(), extensionRegistry));
                                } else if (tag == 34) {
                                    if (!this.executionStats_.isModifiable()) {
                                        this.executionStats_ = GeneratedMessageLite.mutableCopy(this.executionStats_);
                                    }
                                    this.executionStats_.add((ExecutionStats) input.readMessage(ExecutionStats.parser(), extensionRegistry));
                                } else if (tag == 42) {
                                    AlarmListener.Builder subBuilder3 = null;
                                    if ((this.bitField0_ & 4) == 4) {
                                        subBuilder3 = (AlarmListener.Builder) this.inQuotaAlarmListener_.toBuilder();
                                    }
                                    this.inQuotaAlarmListener_ = (AlarmListener) input.readMessage(AlarmListener.parser(), extensionRegistry);
                                    if (subBuilder3 != null) {
                                        subBuilder3.mergeFrom((GeneratedMessageLite) this.inQuotaAlarmListener_);
                                        this.inQuotaAlarmListener_ = (AlarmListener) subBuilder3.buildPartial();
                                    }
                                    this.bitField0_ |= 4;
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
                            synchronized (PackageStats.class) {
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

            public static PackageStats getDefaultInstance() {
                return DEFAULT_INSTANCE;
            }

            public static Parser<PackageStats> parser() {
                return DEFAULT_INSTANCE.getParserForType();
            }
        }

        @Override // com.android.server.job.StateControllerProto.QuotaControllerOrBuilder
        public boolean hasIsCharging() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // com.android.server.job.StateControllerProto.QuotaControllerOrBuilder
        public boolean getIsCharging() {
            return this.isCharging_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setIsCharging(boolean value) {
            this.bitField0_ |= 1;
            this.isCharging_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearIsCharging() {
            this.bitField0_ &= -2;
            this.isCharging_ = false;
        }

        @Override // com.android.server.job.StateControllerProto.QuotaControllerOrBuilder
        public boolean hasIsInParole() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // com.android.server.job.StateControllerProto.QuotaControllerOrBuilder
        public boolean getIsInParole() {
            return this.isInParole_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setIsInParole(boolean value) {
            this.bitField0_ |= 2;
            this.isInParole_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearIsInParole() {
            this.bitField0_ &= -3;
            this.isInParole_ = false;
        }

        @Override // com.android.server.job.StateControllerProto.QuotaControllerOrBuilder
        public boolean hasElapsedRealtime() {
            return (this.bitField0_ & 4) == 4;
        }

        @Override // com.android.server.job.StateControllerProto.QuotaControllerOrBuilder
        public long getElapsedRealtime() {
            return this.elapsedRealtime_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setElapsedRealtime(long value) {
            this.bitField0_ |= 4;
            this.elapsedRealtime_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearElapsedRealtime() {
            this.bitField0_ &= -5;
            this.elapsedRealtime_ = 0;
        }

        @Override // com.android.server.job.StateControllerProto.QuotaControllerOrBuilder
        public List<Integer> getForegroundUidsList() {
            return this.foregroundUids_;
        }

        @Override // com.android.server.job.StateControllerProto.QuotaControllerOrBuilder
        public int getForegroundUidsCount() {
            return this.foregroundUids_.size();
        }

        @Override // com.android.server.job.StateControllerProto.QuotaControllerOrBuilder
        public int getForegroundUids(int index) {
            return this.foregroundUids_.getInt(index);
        }

        private void ensureForegroundUidsIsMutable() {
            if (!this.foregroundUids_.isModifiable()) {
                this.foregroundUids_ = GeneratedMessageLite.mutableCopy(this.foregroundUids_);
            }
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setForegroundUids(int index, int value) {
            ensureForegroundUidsIsMutable();
            this.foregroundUids_.setInt(index, value);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addForegroundUids(int value) {
            ensureForegroundUidsIsMutable();
            this.foregroundUids_.addInt(value);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addAllForegroundUids(Iterable<? extends Integer> values) {
            ensureForegroundUidsIsMutable();
            AbstractMessageLite.addAll(values, this.foregroundUids_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearForegroundUids() {
            this.foregroundUids_ = emptyIntList();
        }

        @Override // com.android.server.job.StateControllerProto.QuotaControllerOrBuilder
        public List<TrackedJob> getTrackedJobsList() {
            return this.trackedJobs_;
        }

        public List<? extends TrackedJobOrBuilder> getTrackedJobsOrBuilderList() {
            return this.trackedJobs_;
        }

        @Override // com.android.server.job.StateControllerProto.QuotaControllerOrBuilder
        public int getTrackedJobsCount() {
            return this.trackedJobs_.size();
        }

        @Override // com.android.server.job.StateControllerProto.QuotaControllerOrBuilder
        public TrackedJob getTrackedJobs(int index) {
            return this.trackedJobs_.get(index);
        }

        public TrackedJobOrBuilder getTrackedJobsOrBuilder(int index) {
            return this.trackedJobs_.get(index);
        }

        private void ensureTrackedJobsIsMutable() {
            if (!this.trackedJobs_.isModifiable()) {
                this.trackedJobs_ = GeneratedMessageLite.mutableCopy(this.trackedJobs_);
            }
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setTrackedJobs(int index, TrackedJob value) {
            if (value != null) {
                ensureTrackedJobsIsMutable();
                this.trackedJobs_.set(index, value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setTrackedJobs(int index, TrackedJob.Builder builderForValue) {
            ensureTrackedJobsIsMutable();
            this.trackedJobs_.set(index, (TrackedJob) builderForValue.build());
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addTrackedJobs(TrackedJob value) {
            if (value != null) {
                ensureTrackedJobsIsMutable();
                this.trackedJobs_.add(value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addTrackedJobs(int index, TrackedJob value) {
            if (value != null) {
                ensureTrackedJobsIsMutable();
                this.trackedJobs_.add(index, value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addTrackedJobs(TrackedJob.Builder builderForValue) {
            ensureTrackedJobsIsMutable();
            this.trackedJobs_.add((TrackedJob) builderForValue.build());
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addTrackedJobs(int index, TrackedJob.Builder builderForValue) {
            ensureTrackedJobsIsMutable();
            this.trackedJobs_.add(index, (TrackedJob) builderForValue.build());
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addAllTrackedJobs(Iterable<? extends TrackedJob> values) {
            ensureTrackedJobsIsMutable();
            AbstractMessageLite.addAll(values, this.trackedJobs_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearTrackedJobs() {
            this.trackedJobs_ = emptyProtobufList();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void removeTrackedJobs(int index) {
            ensureTrackedJobsIsMutable();
            this.trackedJobs_.remove(index);
        }

        @Override // com.android.server.job.StateControllerProto.QuotaControllerOrBuilder
        public List<PackageStats> getPackageStatsList() {
            return this.packageStats_;
        }

        public List<? extends PackageStatsOrBuilder> getPackageStatsOrBuilderList() {
            return this.packageStats_;
        }

        @Override // com.android.server.job.StateControllerProto.QuotaControllerOrBuilder
        public int getPackageStatsCount() {
            return this.packageStats_.size();
        }

        @Override // com.android.server.job.StateControllerProto.QuotaControllerOrBuilder
        public PackageStats getPackageStats(int index) {
            return this.packageStats_.get(index);
        }

        public PackageStatsOrBuilder getPackageStatsOrBuilder(int index) {
            return this.packageStats_.get(index);
        }

        private void ensurePackageStatsIsMutable() {
            if (!this.packageStats_.isModifiable()) {
                this.packageStats_ = GeneratedMessageLite.mutableCopy(this.packageStats_);
            }
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setPackageStats(int index, PackageStats value) {
            if (value != null) {
                ensurePackageStatsIsMutable();
                this.packageStats_.set(index, value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setPackageStats(int index, PackageStats.Builder builderForValue) {
            ensurePackageStatsIsMutable();
            this.packageStats_.set(index, (PackageStats) builderForValue.build());
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addPackageStats(PackageStats value) {
            if (value != null) {
                ensurePackageStatsIsMutable();
                this.packageStats_.add(value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addPackageStats(int index, PackageStats value) {
            if (value != null) {
                ensurePackageStatsIsMutable();
                this.packageStats_.add(index, value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addPackageStats(PackageStats.Builder builderForValue) {
            ensurePackageStatsIsMutable();
            this.packageStats_.add((PackageStats) builderForValue.build());
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addPackageStats(int index, PackageStats.Builder builderForValue) {
            ensurePackageStatsIsMutable();
            this.packageStats_.add(index, (PackageStats) builderForValue.build());
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addAllPackageStats(Iterable<? extends PackageStats> values) {
            ensurePackageStatsIsMutable();
            AbstractMessageLite.addAll(values, this.packageStats_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearPackageStats() {
            this.packageStats_ = emptyProtobufList();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void removePackageStats(int index) {
            ensurePackageStatsIsMutable();
            this.packageStats_.remove(index);
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                output.writeBool(1, this.isCharging_);
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeBool(2, this.isInParole_);
            }
            for (int i = 0; i < this.foregroundUids_.size(); i++) {
                output.writeInt32(3, this.foregroundUids_.getInt(i));
            }
            for (int i2 = 0; i2 < this.trackedJobs_.size(); i2++) {
                output.writeMessage(4, this.trackedJobs_.get(i2));
            }
            for (int i3 = 0; i3 < this.packageStats_.size(); i3++) {
                output.writeMessage(5, this.packageStats_.get(i3));
            }
            if ((this.bitField0_ & 4) == 4) {
                output.writeInt64(6, this.elapsedRealtime_);
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
                size2 = 0 + CodedOutputStream.computeBoolSize(1, this.isCharging_);
            }
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeBoolSize(2, this.isInParole_);
            }
            int dataSize = 0;
            for (int i = 0; i < this.foregroundUids_.size(); i++) {
                dataSize += CodedOutputStream.computeInt32SizeNoTag(this.foregroundUids_.getInt(i));
            }
            int size3 = size2 + dataSize + (getForegroundUidsList().size() * 1);
            for (int i2 = 0; i2 < this.trackedJobs_.size(); i2++) {
                size3 += CodedOutputStream.computeMessageSize(4, this.trackedJobs_.get(i2));
            }
            for (int i3 = 0; i3 < this.packageStats_.size(); i3++) {
                size3 += CodedOutputStream.computeMessageSize(5, this.packageStats_.get(i3));
            }
            if ((this.bitField0_ & 4) == 4) {
                size3 += CodedOutputStream.computeInt64Size(6, this.elapsedRealtime_);
            }
            int size4 = size3 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size4;
            return size4;
        }

        public static QuotaController parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (QuotaController) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static QuotaController parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (QuotaController) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static QuotaController parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (QuotaController) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static QuotaController parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (QuotaController) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static QuotaController parseFrom(InputStream input) throws IOException {
            return (QuotaController) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static QuotaController parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (QuotaController) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static QuotaController parseDelimitedFrom(InputStream input) throws IOException {
            return (QuotaController) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static QuotaController parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (QuotaController) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static QuotaController parseFrom(CodedInputStream input) throws IOException {
            return (QuotaController) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static QuotaController parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (QuotaController) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(QuotaController prototype) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<QuotaController, Builder> implements QuotaControllerOrBuilder {
            private Builder() {
                super(QuotaController.DEFAULT_INSTANCE);
            }

            @Override // com.android.server.job.StateControllerProto.QuotaControllerOrBuilder
            public boolean hasIsCharging() {
                return ((QuotaController) this.instance).hasIsCharging();
            }

            @Override // com.android.server.job.StateControllerProto.QuotaControllerOrBuilder
            public boolean getIsCharging() {
                return ((QuotaController) this.instance).getIsCharging();
            }

            public Builder setIsCharging(boolean value) {
                copyOnWrite();
                ((QuotaController) this.instance).setIsCharging(value);
                return this;
            }

            public Builder clearIsCharging() {
                copyOnWrite();
                ((QuotaController) this.instance).clearIsCharging();
                return this;
            }

            @Override // com.android.server.job.StateControllerProto.QuotaControllerOrBuilder
            public boolean hasIsInParole() {
                return ((QuotaController) this.instance).hasIsInParole();
            }

            @Override // com.android.server.job.StateControllerProto.QuotaControllerOrBuilder
            public boolean getIsInParole() {
                return ((QuotaController) this.instance).getIsInParole();
            }

            public Builder setIsInParole(boolean value) {
                copyOnWrite();
                ((QuotaController) this.instance).setIsInParole(value);
                return this;
            }

            public Builder clearIsInParole() {
                copyOnWrite();
                ((QuotaController) this.instance).clearIsInParole();
                return this;
            }

            @Override // com.android.server.job.StateControllerProto.QuotaControllerOrBuilder
            public boolean hasElapsedRealtime() {
                return ((QuotaController) this.instance).hasElapsedRealtime();
            }

            @Override // com.android.server.job.StateControllerProto.QuotaControllerOrBuilder
            public long getElapsedRealtime() {
                return ((QuotaController) this.instance).getElapsedRealtime();
            }

            public Builder setElapsedRealtime(long value) {
                copyOnWrite();
                ((QuotaController) this.instance).setElapsedRealtime(value);
                return this;
            }

            public Builder clearElapsedRealtime() {
                copyOnWrite();
                ((QuotaController) this.instance).clearElapsedRealtime();
                return this;
            }

            @Override // com.android.server.job.StateControllerProto.QuotaControllerOrBuilder
            public List<Integer> getForegroundUidsList() {
                return Collections.unmodifiableList(((QuotaController) this.instance).getForegroundUidsList());
            }

            @Override // com.android.server.job.StateControllerProto.QuotaControllerOrBuilder
            public int getForegroundUidsCount() {
                return ((QuotaController) this.instance).getForegroundUidsCount();
            }

            @Override // com.android.server.job.StateControllerProto.QuotaControllerOrBuilder
            public int getForegroundUids(int index) {
                return ((QuotaController) this.instance).getForegroundUids(index);
            }

            public Builder setForegroundUids(int index, int value) {
                copyOnWrite();
                ((QuotaController) this.instance).setForegroundUids(index, value);
                return this;
            }

            public Builder addForegroundUids(int value) {
                copyOnWrite();
                ((QuotaController) this.instance).addForegroundUids(value);
                return this;
            }

            public Builder addAllForegroundUids(Iterable<? extends Integer> values) {
                copyOnWrite();
                ((QuotaController) this.instance).addAllForegroundUids(values);
                return this;
            }

            public Builder clearForegroundUids() {
                copyOnWrite();
                ((QuotaController) this.instance).clearForegroundUids();
                return this;
            }

            @Override // com.android.server.job.StateControllerProto.QuotaControllerOrBuilder
            public List<TrackedJob> getTrackedJobsList() {
                return Collections.unmodifiableList(((QuotaController) this.instance).getTrackedJobsList());
            }

            @Override // com.android.server.job.StateControllerProto.QuotaControllerOrBuilder
            public int getTrackedJobsCount() {
                return ((QuotaController) this.instance).getTrackedJobsCount();
            }

            @Override // com.android.server.job.StateControllerProto.QuotaControllerOrBuilder
            public TrackedJob getTrackedJobs(int index) {
                return ((QuotaController) this.instance).getTrackedJobs(index);
            }

            public Builder setTrackedJobs(int index, TrackedJob value) {
                copyOnWrite();
                ((QuotaController) this.instance).setTrackedJobs((QuotaController) index, (int) value);
                return this;
            }

            public Builder setTrackedJobs(int index, TrackedJob.Builder builderForValue) {
                copyOnWrite();
                ((QuotaController) this.instance).setTrackedJobs((QuotaController) index, (int) builderForValue);
                return this;
            }

            public Builder addTrackedJobs(TrackedJob value) {
                copyOnWrite();
                ((QuotaController) this.instance).addTrackedJobs((QuotaController) value);
                return this;
            }

            public Builder addTrackedJobs(int index, TrackedJob value) {
                copyOnWrite();
                ((QuotaController) this.instance).addTrackedJobs((QuotaController) index, (int) value);
                return this;
            }

            public Builder addTrackedJobs(TrackedJob.Builder builderForValue) {
                copyOnWrite();
                ((QuotaController) this.instance).addTrackedJobs((QuotaController) builderForValue);
                return this;
            }

            public Builder addTrackedJobs(int index, TrackedJob.Builder builderForValue) {
                copyOnWrite();
                ((QuotaController) this.instance).addTrackedJobs((QuotaController) index, (int) builderForValue);
                return this;
            }

            public Builder addAllTrackedJobs(Iterable<? extends TrackedJob> values) {
                copyOnWrite();
                ((QuotaController) this.instance).addAllTrackedJobs(values);
                return this;
            }

            public Builder clearTrackedJobs() {
                copyOnWrite();
                ((QuotaController) this.instance).clearTrackedJobs();
                return this;
            }

            public Builder removeTrackedJobs(int index) {
                copyOnWrite();
                ((QuotaController) this.instance).removeTrackedJobs(index);
                return this;
            }

            @Override // com.android.server.job.StateControllerProto.QuotaControllerOrBuilder
            public List<PackageStats> getPackageStatsList() {
                return Collections.unmodifiableList(((QuotaController) this.instance).getPackageStatsList());
            }

            @Override // com.android.server.job.StateControllerProto.QuotaControllerOrBuilder
            public int getPackageStatsCount() {
                return ((QuotaController) this.instance).getPackageStatsCount();
            }

            @Override // com.android.server.job.StateControllerProto.QuotaControllerOrBuilder
            public PackageStats getPackageStats(int index) {
                return ((QuotaController) this.instance).getPackageStats(index);
            }

            public Builder setPackageStats(int index, PackageStats value) {
                copyOnWrite();
                ((QuotaController) this.instance).setPackageStats((QuotaController) index, (int) value);
                return this;
            }

            public Builder setPackageStats(int index, PackageStats.Builder builderForValue) {
                copyOnWrite();
                ((QuotaController) this.instance).setPackageStats((QuotaController) index, (int) builderForValue);
                return this;
            }

            public Builder addPackageStats(PackageStats value) {
                copyOnWrite();
                ((QuotaController) this.instance).addPackageStats((QuotaController) value);
                return this;
            }

            public Builder addPackageStats(int index, PackageStats value) {
                copyOnWrite();
                ((QuotaController) this.instance).addPackageStats((QuotaController) index, (int) value);
                return this;
            }

            public Builder addPackageStats(PackageStats.Builder builderForValue) {
                copyOnWrite();
                ((QuotaController) this.instance).addPackageStats((QuotaController) builderForValue);
                return this;
            }

            public Builder addPackageStats(int index, PackageStats.Builder builderForValue) {
                copyOnWrite();
                ((QuotaController) this.instance).addPackageStats((QuotaController) index, (int) builderForValue);
                return this;
            }

            public Builder addAllPackageStats(Iterable<? extends PackageStats> values) {
                copyOnWrite();
                ((QuotaController) this.instance).addAllPackageStats(values);
                return this;
            }

            public Builder clearPackageStats() {
                copyOnWrite();
                ((QuotaController) this.instance).clearPackageStats();
                return this;
            }

            public Builder removePackageStats(int index) {
                copyOnWrite();
                ((QuotaController) this.instance).removePackageStats(index);
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new QuotaController();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    this.foregroundUids_.makeImmutable();
                    this.trackedJobs_.makeImmutable();
                    this.packageStats_.makeImmutable();
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    QuotaController other = (QuotaController) arg1;
                    this.isCharging_ = visitor.visitBoolean(hasIsCharging(), this.isCharging_, other.hasIsCharging(), other.isCharging_);
                    this.isInParole_ = visitor.visitBoolean(hasIsInParole(), this.isInParole_, other.hasIsInParole(), other.isInParole_);
                    this.elapsedRealtime_ = visitor.visitLong(hasElapsedRealtime(), this.elapsedRealtime_, other.hasElapsedRealtime(), other.elapsedRealtime_);
                    this.foregroundUids_ = visitor.visitIntList(this.foregroundUids_, other.foregroundUids_);
                    this.trackedJobs_ = visitor.visitList(this.trackedJobs_, other.trackedJobs_);
                    this.packageStats_ = visitor.visitList(this.packageStats_, other.packageStats_);
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
                                this.isCharging_ = input.readBool();
                            } else if (tag == 16) {
                                this.bitField0_ |= 2;
                                this.isInParole_ = input.readBool();
                            } else if (tag == 24) {
                                if (!this.foregroundUids_.isModifiable()) {
                                    this.foregroundUids_ = GeneratedMessageLite.mutableCopy(this.foregroundUids_);
                                }
                                this.foregroundUids_.addInt(input.readInt32());
                            } else if (tag == 26) {
                                int limit = input.pushLimit(input.readRawVarint32());
                                if (!this.foregroundUids_.isModifiable() && input.getBytesUntilLimit() > 0) {
                                    this.foregroundUids_ = GeneratedMessageLite.mutableCopy(this.foregroundUids_);
                                }
                                while (input.getBytesUntilLimit() > 0) {
                                    this.foregroundUids_.addInt(input.readInt32());
                                }
                                input.popLimit(limit);
                            } else if (tag == 34) {
                                if (!this.trackedJobs_.isModifiable()) {
                                    this.trackedJobs_ = GeneratedMessageLite.mutableCopy(this.trackedJobs_);
                                }
                                this.trackedJobs_.add((TrackedJob) input.readMessage(TrackedJob.parser(), extensionRegistry));
                            } else if (tag == 42) {
                                if (!this.packageStats_.isModifiable()) {
                                    this.packageStats_ = GeneratedMessageLite.mutableCopy(this.packageStats_);
                                }
                                this.packageStats_.add((PackageStats) input.readMessage(PackageStats.parser(), extensionRegistry));
                            } else if (tag == 48) {
                                this.bitField0_ |= 4;
                                this.elapsedRealtime_ = input.readInt64();
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
                        synchronized (QuotaController.class) {
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

        public static QuotaController getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<QuotaController> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class StorageController extends GeneratedMessageLite<StorageController, Builder> implements StorageControllerOrBuilder {
        private static final StorageController DEFAULT_INSTANCE = new StorageController();
        public static final int IS_STORAGE_NOT_LOW_FIELD_NUMBER = 1;
        public static final int LAST_BROADCAST_SEQUENCE_NUMBER_FIELD_NUMBER = 2;
        private static volatile Parser<StorageController> PARSER = null;
        public static final int TRACKED_JOBS_FIELD_NUMBER = 3;
        private int bitField0_;
        private boolean isStorageNotLow_ = false;
        private int lastBroadcastSequenceNumber_ = 0;
        private Internal.ProtobufList<TrackedJob> trackedJobs_ = emptyProtobufList();

        public interface TrackedJobOrBuilder extends MessageLiteOrBuilder {
            JobStatusShortInfoProto getInfo();

            int getSourceUid();

            boolean hasInfo();

            boolean hasSourceUid();
        }

        private StorageController() {
        }

        public static final class TrackedJob extends GeneratedMessageLite<TrackedJob, Builder> implements TrackedJobOrBuilder {
            private static final TrackedJob DEFAULT_INSTANCE = new TrackedJob();
            public static final int INFO_FIELD_NUMBER = 1;
            private static volatile Parser<TrackedJob> PARSER = null;
            public static final int SOURCE_UID_FIELD_NUMBER = 2;
            private int bitField0_;
            private JobStatusShortInfoProto info_;
            private int sourceUid_ = 0;

            private TrackedJob() {
            }

            @Override // com.android.server.job.StateControllerProto.StorageController.TrackedJobOrBuilder
            public boolean hasInfo() {
                return (this.bitField0_ & 1) == 1;
            }

            @Override // com.android.server.job.StateControllerProto.StorageController.TrackedJobOrBuilder
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

            @Override // com.android.server.job.StateControllerProto.StorageController.TrackedJobOrBuilder
            public boolean hasSourceUid() {
                return (this.bitField0_ & 2) == 2;
            }

            @Override // com.android.server.job.StateControllerProto.StorageController.TrackedJobOrBuilder
            public int getSourceUid() {
                return this.sourceUid_;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void setSourceUid(int value) {
                this.bitField0_ |= 2;
                this.sourceUid_ = value;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void clearSourceUid() {
                this.bitField0_ &= -3;
                this.sourceUid_ = 0;
            }

            @Override // com.google.protobuf.MessageLite
            public void writeTo(CodedOutputStream output) throws IOException {
                if ((this.bitField0_ & 1) == 1) {
                    output.writeMessage(1, getInfo());
                }
                if ((this.bitField0_ & 2) == 2) {
                    output.writeInt32(2, this.sourceUid_);
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
                    size2 += CodedOutputStream.computeInt32Size(2, this.sourceUid_);
                }
                int size3 = size2 + this.unknownFields.getSerializedSize();
                this.memoizedSerializedSize = size3;
                return size3;
            }

            public static TrackedJob parseFrom(ByteString data) throws InvalidProtocolBufferException {
                return (TrackedJob) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
            }

            public static TrackedJob parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return (TrackedJob) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
            }

            public static TrackedJob parseFrom(byte[] data) throws InvalidProtocolBufferException {
                return (TrackedJob) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
            }

            public static TrackedJob parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return (TrackedJob) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
            }

            public static TrackedJob parseFrom(InputStream input) throws IOException {
                return (TrackedJob) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
            }

            public static TrackedJob parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (TrackedJob) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static TrackedJob parseDelimitedFrom(InputStream input) throws IOException {
                return (TrackedJob) parseDelimitedFrom(DEFAULT_INSTANCE, input);
            }

            public static TrackedJob parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (TrackedJob) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static TrackedJob parseFrom(CodedInputStream input) throws IOException {
                return (TrackedJob) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
            }

            public static TrackedJob parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (TrackedJob) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static Builder newBuilder() {
                return (Builder) DEFAULT_INSTANCE.toBuilder();
            }

            public static Builder newBuilder(TrackedJob prototype) {
                return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
            }

            public static final class Builder extends GeneratedMessageLite.Builder<TrackedJob, Builder> implements TrackedJobOrBuilder {
                private Builder() {
                    super(TrackedJob.DEFAULT_INSTANCE);
                }

                @Override // com.android.server.job.StateControllerProto.StorageController.TrackedJobOrBuilder
                public boolean hasInfo() {
                    return ((TrackedJob) this.instance).hasInfo();
                }

                @Override // com.android.server.job.StateControllerProto.StorageController.TrackedJobOrBuilder
                public JobStatusShortInfoProto getInfo() {
                    return ((TrackedJob) this.instance).getInfo();
                }

                public Builder setInfo(JobStatusShortInfoProto value) {
                    copyOnWrite();
                    ((TrackedJob) this.instance).setInfo((TrackedJob) value);
                    return this;
                }

                public Builder setInfo(JobStatusShortInfoProto.Builder builderForValue) {
                    copyOnWrite();
                    ((TrackedJob) this.instance).setInfo((TrackedJob) builderForValue);
                    return this;
                }

                public Builder mergeInfo(JobStatusShortInfoProto value) {
                    copyOnWrite();
                    ((TrackedJob) this.instance).mergeInfo(value);
                    return this;
                }

                public Builder clearInfo() {
                    copyOnWrite();
                    ((TrackedJob) this.instance).clearInfo();
                    return this;
                }

                @Override // com.android.server.job.StateControllerProto.StorageController.TrackedJobOrBuilder
                public boolean hasSourceUid() {
                    return ((TrackedJob) this.instance).hasSourceUid();
                }

                @Override // com.android.server.job.StateControllerProto.StorageController.TrackedJobOrBuilder
                public int getSourceUid() {
                    return ((TrackedJob) this.instance).getSourceUid();
                }

                public Builder setSourceUid(int value) {
                    copyOnWrite();
                    ((TrackedJob) this.instance).setSourceUid(value);
                    return this;
                }

                public Builder clearSourceUid() {
                    copyOnWrite();
                    ((TrackedJob) this.instance).clearSourceUid();
                    return this;
                }
            }

            /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
            /* access modifiers changed from: protected */
            @Override // com.google.protobuf.GeneratedMessageLite
            public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
                switch (method) {
                    case NEW_MUTABLE_INSTANCE:
                        return new TrackedJob();
                    case IS_INITIALIZED:
                        return DEFAULT_INSTANCE;
                    case MAKE_IMMUTABLE:
                        return null;
                    case NEW_BUILDER:
                        return new Builder();
                    case VISIT:
                        GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                        TrackedJob other = (TrackedJob) arg1;
                        this.info_ = (JobStatusShortInfoProto) visitor.visitMessage(this.info_, other.info_);
                        this.sourceUid_ = visitor.visitInt(hasSourceUid(), this.sourceUid_, other.hasSourceUid(), other.sourceUid_);
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
                                    this.sourceUid_ = input.readInt32();
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
                            synchronized (TrackedJob.class) {
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

            public static TrackedJob getDefaultInstance() {
                return DEFAULT_INSTANCE;
            }

            public static Parser<TrackedJob> parser() {
                return DEFAULT_INSTANCE.getParserForType();
            }
        }

        @Override // com.android.server.job.StateControllerProto.StorageControllerOrBuilder
        public boolean hasIsStorageNotLow() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // com.android.server.job.StateControllerProto.StorageControllerOrBuilder
        public boolean getIsStorageNotLow() {
            return this.isStorageNotLow_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setIsStorageNotLow(boolean value) {
            this.bitField0_ |= 1;
            this.isStorageNotLow_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearIsStorageNotLow() {
            this.bitField0_ &= -2;
            this.isStorageNotLow_ = false;
        }

        @Override // com.android.server.job.StateControllerProto.StorageControllerOrBuilder
        public boolean hasLastBroadcastSequenceNumber() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // com.android.server.job.StateControllerProto.StorageControllerOrBuilder
        public int getLastBroadcastSequenceNumber() {
            return this.lastBroadcastSequenceNumber_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setLastBroadcastSequenceNumber(int value) {
            this.bitField0_ |= 2;
            this.lastBroadcastSequenceNumber_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearLastBroadcastSequenceNumber() {
            this.bitField0_ &= -3;
            this.lastBroadcastSequenceNumber_ = 0;
        }

        @Override // com.android.server.job.StateControllerProto.StorageControllerOrBuilder
        public List<TrackedJob> getTrackedJobsList() {
            return this.trackedJobs_;
        }

        public List<? extends TrackedJobOrBuilder> getTrackedJobsOrBuilderList() {
            return this.trackedJobs_;
        }

        @Override // com.android.server.job.StateControllerProto.StorageControllerOrBuilder
        public int getTrackedJobsCount() {
            return this.trackedJobs_.size();
        }

        @Override // com.android.server.job.StateControllerProto.StorageControllerOrBuilder
        public TrackedJob getTrackedJobs(int index) {
            return this.trackedJobs_.get(index);
        }

        public TrackedJobOrBuilder getTrackedJobsOrBuilder(int index) {
            return this.trackedJobs_.get(index);
        }

        private void ensureTrackedJobsIsMutable() {
            if (!this.trackedJobs_.isModifiable()) {
                this.trackedJobs_ = GeneratedMessageLite.mutableCopy(this.trackedJobs_);
            }
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setTrackedJobs(int index, TrackedJob value) {
            if (value != null) {
                ensureTrackedJobsIsMutable();
                this.trackedJobs_.set(index, value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setTrackedJobs(int index, TrackedJob.Builder builderForValue) {
            ensureTrackedJobsIsMutable();
            this.trackedJobs_.set(index, (TrackedJob) builderForValue.build());
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addTrackedJobs(TrackedJob value) {
            if (value != null) {
                ensureTrackedJobsIsMutable();
                this.trackedJobs_.add(value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addTrackedJobs(int index, TrackedJob value) {
            if (value != null) {
                ensureTrackedJobsIsMutable();
                this.trackedJobs_.add(index, value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addTrackedJobs(TrackedJob.Builder builderForValue) {
            ensureTrackedJobsIsMutable();
            this.trackedJobs_.add((TrackedJob) builderForValue.build());
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addTrackedJobs(int index, TrackedJob.Builder builderForValue) {
            ensureTrackedJobsIsMutable();
            this.trackedJobs_.add(index, (TrackedJob) builderForValue.build());
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addAllTrackedJobs(Iterable<? extends TrackedJob> values) {
            ensureTrackedJobsIsMutable();
            AbstractMessageLite.addAll(values, this.trackedJobs_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearTrackedJobs() {
            this.trackedJobs_ = emptyProtobufList();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void removeTrackedJobs(int index) {
            ensureTrackedJobsIsMutable();
            this.trackedJobs_.remove(index);
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                output.writeBool(1, this.isStorageNotLow_);
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeInt32(2, this.lastBroadcastSequenceNumber_);
            }
            for (int i = 0; i < this.trackedJobs_.size(); i++) {
                output.writeMessage(3, this.trackedJobs_.get(i));
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
                size2 = 0 + CodedOutputStream.computeBoolSize(1, this.isStorageNotLow_);
            }
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeInt32Size(2, this.lastBroadcastSequenceNumber_);
            }
            for (int i = 0; i < this.trackedJobs_.size(); i++) {
                size2 += CodedOutputStream.computeMessageSize(3, this.trackedJobs_.get(i));
            }
            int size3 = size2 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static StorageController parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (StorageController) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static StorageController parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (StorageController) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static StorageController parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (StorageController) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static StorageController parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (StorageController) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static StorageController parseFrom(InputStream input) throws IOException {
            return (StorageController) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static StorageController parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (StorageController) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static StorageController parseDelimitedFrom(InputStream input) throws IOException {
            return (StorageController) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static StorageController parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (StorageController) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static StorageController parseFrom(CodedInputStream input) throws IOException {
            return (StorageController) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static StorageController parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (StorageController) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(StorageController prototype) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<StorageController, Builder> implements StorageControllerOrBuilder {
            private Builder() {
                super(StorageController.DEFAULT_INSTANCE);
            }

            @Override // com.android.server.job.StateControllerProto.StorageControllerOrBuilder
            public boolean hasIsStorageNotLow() {
                return ((StorageController) this.instance).hasIsStorageNotLow();
            }

            @Override // com.android.server.job.StateControllerProto.StorageControllerOrBuilder
            public boolean getIsStorageNotLow() {
                return ((StorageController) this.instance).getIsStorageNotLow();
            }

            public Builder setIsStorageNotLow(boolean value) {
                copyOnWrite();
                ((StorageController) this.instance).setIsStorageNotLow(value);
                return this;
            }

            public Builder clearIsStorageNotLow() {
                copyOnWrite();
                ((StorageController) this.instance).clearIsStorageNotLow();
                return this;
            }

            @Override // com.android.server.job.StateControllerProto.StorageControllerOrBuilder
            public boolean hasLastBroadcastSequenceNumber() {
                return ((StorageController) this.instance).hasLastBroadcastSequenceNumber();
            }

            @Override // com.android.server.job.StateControllerProto.StorageControllerOrBuilder
            public int getLastBroadcastSequenceNumber() {
                return ((StorageController) this.instance).getLastBroadcastSequenceNumber();
            }

            public Builder setLastBroadcastSequenceNumber(int value) {
                copyOnWrite();
                ((StorageController) this.instance).setLastBroadcastSequenceNumber(value);
                return this;
            }

            public Builder clearLastBroadcastSequenceNumber() {
                copyOnWrite();
                ((StorageController) this.instance).clearLastBroadcastSequenceNumber();
                return this;
            }

            @Override // com.android.server.job.StateControllerProto.StorageControllerOrBuilder
            public List<TrackedJob> getTrackedJobsList() {
                return Collections.unmodifiableList(((StorageController) this.instance).getTrackedJobsList());
            }

            @Override // com.android.server.job.StateControllerProto.StorageControllerOrBuilder
            public int getTrackedJobsCount() {
                return ((StorageController) this.instance).getTrackedJobsCount();
            }

            @Override // com.android.server.job.StateControllerProto.StorageControllerOrBuilder
            public TrackedJob getTrackedJobs(int index) {
                return ((StorageController) this.instance).getTrackedJobs(index);
            }

            public Builder setTrackedJobs(int index, TrackedJob value) {
                copyOnWrite();
                ((StorageController) this.instance).setTrackedJobs((StorageController) index, (int) value);
                return this;
            }

            public Builder setTrackedJobs(int index, TrackedJob.Builder builderForValue) {
                copyOnWrite();
                ((StorageController) this.instance).setTrackedJobs((StorageController) index, (int) builderForValue);
                return this;
            }

            public Builder addTrackedJobs(TrackedJob value) {
                copyOnWrite();
                ((StorageController) this.instance).addTrackedJobs((StorageController) value);
                return this;
            }

            public Builder addTrackedJobs(int index, TrackedJob value) {
                copyOnWrite();
                ((StorageController) this.instance).addTrackedJobs((StorageController) index, (int) value);
                return this;
            }

            public Builder addTrackedJobs(TrackedJob.Builder builderForValue) {
                copyOnWrite();
                ((StorageController) this.instance).addTrackedJobs((StorageController) builderForValue);
                return this;
            }

            public Builder addTrackedJobs(int index, TrackedJob.Builder builderForValue) {
                copyOnWrite();
                ((StorageController) this.instance).addTrackedJobs((StorageController) index, (int) builderForValue);
                return this;
            }

            public Builder addAllTrackedJobs(Iterable<? extends TrackedJob> values) {
                copyOnWrite();
                ((StorageController) this.instance).addAllTrackedJobs(values);
                return this;
            }

            public Builder clearTrackedJobs() {
                copyOnWrite();
                ((StorageController) this.instance).clearTrackedJobs();
                return this;
            }

            public Builder removeTrackedJobs(int index) {
                copyOnWrite();
                ((StorageController) this.instance).removeTrackedJobs(index);
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new StorageController();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    this.trackedJobs_.makeImmutable();
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    StorageController other = (StorageController) arg1;
                    this.isStorageNotLow_ = visitor.visitBoolean(hasIsStorageNotLow(), this.isStorageNotLow_, other.hasIsStorageNotLow(), other.isStorageNotLow_);
                    this.lastBroadcastSequenceNumber_ = visitor.visitInt(hasLastBroadcastSequenceNumber(), this.lastBroadcastSequenceNumber_, other.hasLastBroadcastSequenceNumber(), other.lastBroadcastSequenceNumber_);
                    this.trackedJobs_ = visitor.visitList(this.trackedJobs_, other.trackedJobs_);
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
                                this.isStorageNotLow_ = input.readBool();
                            } else if (tag == 16) {
                                this.bitField0_ |= 2;
                                this.lastBroadcastSequenceNumber_ = input.readInt32();
                            } else if (tag == 26) {
                                if (!this.trackedJobs_.isModifiable()) {
                                    this.trackedJobs_ = GeneratedMessageLite.mutableCopy(this.trackedJobs_);
                                }
                                this.trackedJobs_.add((TrackedJob) input.readMessage(TrackedJob.parser(), extensionRegistry));
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
                        synchronized (StorageController.class) {
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

        public static StorageController getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<StorageController> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class TimeController extends GeneratedMessageLite<TimeController, Builder> implements TimeControllerOrBuilder {
        private static final TimeController DEFAULT_INSTANCE = new TimeController();
        public static final int NOW_ELAPSED_REALTIME_FIELD_NUMBER = 1;
        private static volatile Parser<TimeController> PARSER = null;
        public static final int TIME_UNTIL_NEXT_DEADLINE_ALARM_MS_FIELD_NUMBER = 3;
        public static final int TIME_UNTIL_NEXT_DELAY_ALARM_MS_FIELD_NUMBER = 2;
        public static final int TRACKED_JOBS_FIELD_NUMBER = 4;
        private int bitField0_;
        private long nowElapsedRealtime_ = 0;
        private long timeUntilNextDeadlineAlarmMs_ = 0;
        private long timeUntilNextDelayAlarmMs_ = 0;
        private Internal.ProtobufList<TrackedJob> trackedJobs_ = emptyProtobufList();

        public interface TrackedJobOrBuilder extends MessageLiteOrBuilder {
            long getDelayTimeRemainingMs();

            boolean getHasDeadlineConstraint();

            boolean getHasTimingDelayConstraint();

            JobStatusShortInfoProto getInfo();

            int getSourceUid();

            long getTimeRemainingUntilDeadlineMs();

            boolean hasDelayTimeRemainingMs();

            boolean hasHasDeadlineConstraint();

            boolean hasHasTimingDelayConstraint();

            boolean hasInfo();

            boolean hasSourceUid();

            boolean hasTimeRemainingUntilDeadlineMs();
        }

        private TimeController() {
        }

        public static final class TrackedJob extends GeneratedMessageLite<TrackedJob, Builder> implements TrackedJobOrBuilder {
            private static final TrackedJob DEFAULT_INSTANCE = new TrackedJob();
            public static final int DELAY_TIME_REMAINING_MS_FIELD_NUMBER = 4;
            public static final int HAS_DEADLINE_CONSTRAINT_FIELD_NUMBER = 5;
            public static final int HAS_TIMING_DELAY_CONSTRAINT_FIELD_NUMBER = 3;
            public static final int INFO_FIELD_NUMBER = 1;
            private static volatile Parser<TrackedJob> PARSER = null;
            public static final int SOURCE_UID_FIELD_NUMBER = 2;
            public static final int TIME_REMAINING_UNTIL_DEADLINE_MS_FIELD_NUMBER = 6;
            private int bitField0_;
            private long delayTimeRemainingMs_ = 0;
            private boolean hasDeadlineConstraint_ = false;
            private boolean hasTimingDelayConstraint_ = false;
            private JobStatusShortInfoProto info_;
            private int sourceUid_ = 0;
            private long timeRemainingUntilDeadlineMs_ = 0;

            private TrackedJob() {
            }

            @Override // com.android.server.job.StateControllerProto.TimeController.TrackedJobOrBuilder
            public boolean hasInfo() {
                return (this.bitField0_ & 1) == 1;
            }

            @Override // com.android.server.job.StateControllerProto.TimeController.TrackedJobOrBuilder
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

            @Override // com.android.server.job.StateControllerProto.TimeController.TrackedJobOrBuilder
            public boolean hasSourceUid() {
                return (this.bitField0_ & 2) == 2;
            }

            @Override // com.android.server.job.StateControllerProto.TimeController.TrackedJobOrBuilder
            public int getSourceUid() {
                return this.sourceUid_;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void setSourceUid(int value) {
                this.bitField0_ |= 2;
                this.sourceUid_ = value;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void clearSourceUid() {
                this.bitField0_ &= -3;
                this.sourceUid_ = 0;
            }

            @Override // com.android.server.job.StateControllerProto.TimeController.TrackedJobOrBuilder
            public boolean hasHasTimingDelayConstraint() {
                return (this.bitField0_ & 4) == 4;
            }

            @Override // com.android.server.job.StateControllerProto.TimeController.TrackedJobOrBuilder
            public boolean getHasTimingDelayConstraint() {
                return this.hasTimingDelayConstraint_;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void setHasTimingDelayConstraint(boolean value) {
                this.bitField0_ |= 4;
                this.hasTimingDelayConstraint_ = value;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void clearHasTimingDelayConstraint() {
                this.bitField0_ &= -5;
                this.hasTimingDelayConstraint_ = false;
            }

            @Override // com.android.server.job.StateControllerProto.TimeController.TrackedJobOrBuilder
            public boolean hasDelayTimeRemainingMs() {
                return (this.bitField0_ & 8) == 8;
            }

            @Override // com.android.server.job.StateControllerProto.TimeController.TrackedJobOrBuilder
            public long getDelayTimeRemainingMs() {
                return this.delayTimeRemainingMs_;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void setDelayTimeRemainingMs(long value) {
                this.bitField0_ |= 8;
                this.delayTimeRemainingMs_ = value;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void clearDelayTimeRemainingMs() {
                this.bitField0_ &= -9;
                this.delayTimeRemainingMs_ = 0;
            }

            @Override // com.android.server.job.StateControllerProto.TimeController.TrackedJobOrBuilder
            public boolean hasHasDeadlineConstraint() {
                return (this.bitField0_ & 16) == 16;
            }

            @Override // com.android.server.job.StateControllerProto.TimeController.TrackedJobOrBuilder
            public boolean getHasDeadlineConstraint() {
                return this.hasDeadlineConstraint_;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void setHasDeadlineConstraint(boolean value) {
                this.bitField0_ |= 16;
                this.hasDeadlineConstraint_ = value;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void clearHasDeadlineConstraint() {
                this.bitField0_ &= -17;
                this.hasDeadlineConstraint_ = false;
            }

            @Override // com.android.server.job.StateControllerProto.TimeController.TrackedJobOrBuilder
            public boolean hasTimeRemainingUntilDeadlineMs() {
                return (this.bitField0_ & 32) == 32;
            }

            @Override // com.android.server.job.StateControllerProto.TimeController.TrackedJobOrBuilder
            public long getTimeRemainingUntilDeadlineMs() {
                return this.timeRemainingUntilDeadlineMs_;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void setTimeRemainingUntilDeadlineMs(long value) {
                this.bitField0_ |= 32;
                this.timeRemainingUntilDeadlineMs_ = value;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void clearTimeRemainingUntilDeadlineMs() {
                this.bitField0_ &= -33;
                this.timeRemainingUntilDeadlineMs_ = 0;
            }

            @Override // com.google.protobuf.MessageLite
            public void writeTo(CodedOutputStream output) throws IOException {
                if ((this.bitField0_ & 1) == 1) {
                    output.writeMessage(1, getInfo());
                }
                if ((this.bitField0_ & 2) == 2) {
                    output.writeInt32(2, this.sourceUid_);
                }
                if ((this.bitField0_ & 4) == 4) {
                    output.writeBool(3, this.hasTimingDelayConstraint_);
                }
                if ((this.bitField0_ & 8) == 8) {
                    output.writeInt64(4, this.delayTimeRemainingMs_);
                }
                if ((this.bitField0_ & 16) == 16) {
                    output.writeBool(5, this.hasDeadlineConstraint_);
                }
                if ((this.bitField0_ & 32) == 32) {
                    output.writeInt64(6, this.timeRemainingUntilDeadlineMs_);
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
                    size2 += CodedOutputStream.computeInt32Size(2, this.sourceUid_);
                }
                if ((this.bitField0_ & 4) == 4) {
                    size2 += CodedOutputStream.computeBoolSize(3, this.hasTimingDelayConstraint_);
                }
                if ((this.bitField0_ & 8) == 8) {
                    size2 += CodedOutputStream.computeInt64Size(4, this.delayTimeRemainingMs_);
                }
                if ((this.bitField0_ & 16) == 16) {
                    size2 += CodedOutputStream.computeBoolSize(5, this.hasDeadlineConstraint_);
                }
                if ((this.bitField0_ & 32) == 32) {
                    size2 += CodedOutputStream.computeInt64Size(6, this.timeRemainingUntilDeadlineMs_);
                }
                int size3 = size2 + this.unknownFields.getSerializedSize();
                this.memoizedSerializedSize = size3;
                return size3;
            }

            public static TrackedJob parseFrom(ByteString data) throws InvalidProtocolBufferException {
                return (TrackedJob) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
            }

            public static TrackedJob parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return (TrackedJob) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
            }

            public static TrackedJob parseFrom(byte[] data) throws InvalidProtocolBufferException {
                return (TrackedJob) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
            }

            public static TrackedJob parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return (TrackedJob) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
            }

            public static TrackedJob parseFrom(InputStream input) throws IOException {
                return (TrackedJob) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
            }

            public static TrackedJob parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (TrackedJob) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static TrackedJob parseDelimitedFrom(InputStream input) throws IOException {
                return (TrackedJob) parseDelimitedFrom(DEFAULT_INSTANCE, input);
            }

            public static TrackedJob parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (TrackedJob) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static TrackedJob parseFrom(CodedInputStream input) throws IOException {
                return (TrackedJob) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
            }

            public static TrackedJob parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (TrackedJob) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static Builder newBuilder() {
                return (Builder) DEFAULT_INSTANCE.toBuilder();
            }

            public static Builder newBuilder(TrackedJob prototype) {
                return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
            }

            public static final class Builder extends GeneratedMessageLite.Builder<TrackedJob, Builder> implements TrackedJobOrBuilder {
                private Builder() {
                    super(TrackedJob.DEFAULT_INSTANCE);
                }

                @Override // com.android.server.job.StateControllerProto.TimeController.TrackedJobOrBuilder
                public boolean hasInfo() {
                    return ((TrackedJob) this.instance).hasInfo();
                }

                @Override // com.android.server.job.StateControllerProto.TimeController.TrackedJobOrBuilder
                public JobStatusShortInfoProto getInfo() {
                    return ((TrackedJob) this.instance).getInfo();
                }

                public Builder setInfo(JobStatusShortInfoProto value) {
                    copyOnWrite();
                    ((TrackedJob) this.instance).setInfo((TrackedJob) value);
                    return this;
                }

                public Builder setInfo(JobStatusShortInfoProto.Builder builderForValue) {
                    copyOnWrite();
                    ((TrackedJob) this.instance).setInfo((TrackedJob) builderForValue);
                    return this;
                }

                public Builder mergeInfo(JobStatusShortInfoProto value) {
                    copyOnWrite();
                    ((TrackedJob) this.instance).mergeInfo(value);
                    return this;
                }

                public Builder clearInfo() {
                    copyOnWrite();
                    ((TrackedJob) this.instance).clearInfo();
                    return this;
                }

                @Override // com.android.server.job.StateControllerProto.TimeController.TrackedJobOrBuilder
                public boolean hasSourceUid() {
                    return ((TrackedJob) this.instance).hasSourceUid();
                }

                @Override // com.android.server.job.StateControllerProto.TimeController.TrackedJobOrBuilder
                public int getSourceUid() {
                    return ((TrackedJob) this.instance).getSourceUid();
                }

                public Builder setSourceUid(int value) {
                    copyOnWrite();
                    ((TrackedJob) this.instance).setSourceUid(value);
                    return this;
                }

                public Builder clearSourceUid() {
                    copyOnWrite();
                    ((TrackedJob) this.instance).clearSourceUid();
                    return this;
                }

                @Override // com.android.server.job.StateControllerProto.TimeController.TrackedJobOrBuilder
                public boolean hasHasTimingDelayConstraint() {
                    return ((TrackedJob) this.instance).hasHasTimingDelayConstraint();
                }

                @Override // com.android.server.job.StateControllerProto.TimeController.TrackedJobOrBuilder
                public boolean getHasTimingDelayConstraint() {
                    return ((TrackedJob) this.instance).getHasTimingDelayConstraint();
                }

                public Builder setHasTimingDelayConstraint(boolean value) {
                    copyOnWrite();
                    ((TrackedJob) this.instance).setHasTimingDelayConstraint(value);
                    return this;
                }

                public Builder clearHasTimingDelayConstraint() {
                    copyOnWrite();
                    ((TrackedJob) this.instance).clearHasTimingDelayConstraint();
                    return this;
                }

                @Override // com.android.server.job.StateControllerProto.TimeController.TrackedJobOrBuilder
                public boolean hasDelayTimeRemainingMs() {
                    return ((TrackedJob) this.instance).hasDelayTimeRemainingMs();
                }

                @Override // com.android.server.job.StateControllerProto.TimeController.TrackedJobOrBuilder
                public long getDelayTimeRemainingMs() {
                    return ((TrackedJob) this.instance).getDelayTimeRemainingMs();
                }

                public Builder setDelayTimeRemainingMs(long value) {
                    copyOnWrite();
                    ((TrackedJob) this.instance).setDelayTimeRemainingMs(value);
                    return this;
                }

                public Builder clearDelayTimeRemainingMs() {
                    copyOnWrite();
                    ((TrackedJob) this.instance).clearDelayTimeRemainingMs();
                    return this;
                }

                @Override // com.android.server.job.StateControllerProto.TimeController.TrackedJobOrBuilder
                public boolean hasHasDeadlineConstraint() {
                    return ((TrackedJob) this.instance).hasHasDeadlineConstraint();
                }

                @Override // com.android.server.job.StateControllerProto.TimeController.TrackedJobOrBuilder
                public boolean getHasDeadlineConstraint() {
                    return ((TrackedJob) this.instance).getHasDeadlineConstraint();
                }

                public Builder setHasDeadlineConstraint(boolean value) {
                    copyOnWrite();
                    ((TrackedJob) this.instance).setHasDeadlineConstraint(value);
                    return this;
                }

                public Builder clearHasDeadlineConstraint() {
                    copyOnWrite();
                    ((TrackedJob) this.instance).clearHasDeadlineConstraint();
                    return this;
                }

                @Override // com.android.server.job.StateControllerProto.TimeController.TrackedJobOrBuilder
                public boolean hasTimeRemainingUntilDeadlineMs() {
                    return ((TrackedJob) this.instance).hasTimeRemainingUntilDeadlineMs();
                }

                @Override // com.android.server.job.StateControllerProto.TimeController.TrackedJobOrBuilder
                public long getTimeRemainingUntilDeadlineMs() {
                    return ((TrackedJob) this.instance).getTimeRemainingUntilDeadlineMs();
                }

                public Builder setTimeRemainingUntilDeadlineMs(long value) {
                    copyOnWrite();
                    ((TrackedJob) this.instance).setTimeRemainingUntilDeadlineMs(value);
                    return this;
                }

                public Builder clearTimeRemainingUntilDeadlineMs() {
                    copyOnWrite();
                    ((TrackedJob) this.instance).clearTimeRemainingUntilDeadlineMs();
                    return this;
                }
            }

            /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
            /* access modifiers changed from: protected */
            @Override // com.google.protobuf.GeneratedMessageLite
            public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
                switch (method) {
                    case NEW_MUTABLE_INSTANCE:
                        return new TrackedJob();
                    case IS_INITIALIZED:
                        return DEFAULT_INSTANCE;
                    case MAKE_IMMUTABLE:
                        return null;
                    case NEW_BUILDER:
                        return new Builder();
                    case VISIT:
                        GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                        TrackedJob other = (TrackedJob) arg1;
                        this.info_ = (JobStatusShortInfoProto) visitor.visitMessage(this.info_, other.info_);
                        this.sourceUid_ = visitor.visitInt(hasSourceUid(), this.sourceUid_, other.hasSourceUid(), other.sourceUid_);
                        this.hasTimingDelayConstraint_ = visitor.visitBoolean(hasHasTimingDelayConstraint(), this.hasTimingDelayConstraint_, other.hasHasTimingDelayConstraint(), other.hasTimingDelayConstraint_);
                        this.delayTimeRemainingMs_ = visitor.visitLong(hasDelayTimeRemainingMs(), this.delayTimeRemainingMs_, other.hasDelayTimeRemainingMs(), other.delayTimeRemainingMs_);
                        this.hasDeadlineConstraint_ = visitor.visitBoolean(hasHasDeadlineConstraint(), this.hasDeadlineConstraint_, other.hasHasDeadlineConstraint(), other.hasDeadlineConstraint_);
                        this.timeRemainingUntilDeadlineMs_ = visitor.visitLong(hasTimeRemainingUntilDeadlineMs(), this.timeRemainingUntilDeadlineMs_, other.hasTimeRemainingUntilDeadlineMs(), other.timeRemainingUntilDeadlineMs_);
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
                                    this.sourceUid_ = input.readInt32();
                                } else if (tag == 24) {
                                    this.bitField0_ |= 4;
                                    this.hasTimingDelayConstraint_ = input.readBool();
                                } else if (tag == 32) {
                                    this.bitField0_ |= 8;
                                    this.delayTimeRemainingMs_ = input.readInt64();
                                } else if (tag == 40) {
                                    this.bitField0_ = 16 | this.bitField0_;
                                    this.hasDeadlineConstraint_ = input.readBool();
                                } else if (tag == 48) {
                                    this.bitField0_ |= 32;
                                    this.timeRemainingUntilDeadlineMs_ = input.readInt64();
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
                            synchronized (TrackedJob.class) {
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

            public static TrackedJob getDefaultInstance() {
                return DEFAULT_INSTANCE;
            }

            public static Parser<TrackedJob> parser() {
                return DEFAULT_INSTANCE.getParserForType();
            }
        }

        @Override // com.android.server.job.StateControllerProto.TimeControllerOrBuilder
        public boolean hasNowElapsedRealtime() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // com.android.server.job.StateControllerProto.TimeControllerOrBuilder
        public long getNowElapsedRealtime() {
            return this.nowElapsedRealtime_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setNowElapsedRealtime(long value) {
            this.bitField0_ |= 1;
            this.nowElapsedRealtime_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearNowElapsedRealtime() {
            this.bitField0_ &= -2;
            this.nowElapsedRealtime_ = 0;
        }

        @Override // com.android.server.job.StateControllerProto.TimeControllerOrBuilder
        public boolean hasTimeUntilNextDelayAlarmMs() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // com.android.server.job.StateControllerProto.TimeControllerOrBuilder
        public long getTimeUntilNextDelayAlarmMs() {
            return this.timeUntilNextDelayAlarmMs_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setTimeUntilNextDelayAlarmMs(long value) {
            this.bitField0_ |= 2;
            this.timeUntilNextDelayAlarmMs_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearTimeUntilNextDelayAlarmMs() {
            this.bitField0_ &= -3;
            this.timeUntilNextDelayAlarmMs_ = 0;
        }

        @Override // com.android.server.job.StateControllerProto.TimeControllerOrBuilder
        public boolean hasTimeUntilNextDeadlineAlarmMs() {
            return (this.bitField0_ & 4) == 4;
        }

        @Override // com.android.server.job.StateControllerProto.TimeControllerOrBuilder
        public long getTimeUntilNextDeadlineAlarmMs() {
            return this.timeUntilNextDeadlineAlarmMs_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setTimeUntilNextDeadlineAlarmMs(long value) {
            this.bitField0_ |= 4;
            this.timeUntilNextDeadlineAlarmMs_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearTimeUntilNextDeadlineAlarmMs() {
            this.bitField0_ &= -5;
            this.timeUntilNextDeadlineAlarmMs_ = 0;
        }

        @Override // com.android.server.job.StateControllerProto.TimeControllerOrBuilder
        public List<TrackedJob> getTrackedJobsList() {
            return this.trackedJobs_;
        }

        public List<? extends TrackedJobOrBuilder> getTrackedJobsOrBuilderList() {
            return this.trackedJobs_;
        }

        @Override // com.android.server.job.StateControllerProto.TimeControllerOrBuilder
        public int getTrackedJobsCount() {
            return this.trackedJobs_.size();
        }

        @Override // com.android.server.job.StateControllerProto.TimeControllerOrBuilder
        public TrackedJob getTrackedJobs(int index) {
            return this.trackedJobs_.get(index);
        }

        public TrackedJobOrBuilder getTrackedJobsOrBuilder(int index) {
            return this.trackedJobs_.get(index);
        }

        private void ensureTrackedJobsIsMutable() {
            if (!this.trackedJobs_.isModifiable()) {
                this.trackedJobs_ = GeneratedMessageLite.mutableCopy(this.trackedJobs_);
            }
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setTrackedJobs(int index, TrackedJob value) {
            if (value != null) {
                ensureTrackedJobsIsMutable();
                this.trackedJobs_.set(index, value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setTrackedJobs(int index, TrackedJob.Builder builderForValue) {
            ensureTrackedJobsIsMutable();
            this.trackedJobs_.set(index, (TrackedJob) builderForValue.build());
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addTrackedJobs(TrackedJob value) {
            if (value != null) {
                ensureTrackedJobsIsMutable();
                this.trackedJobs_.add(value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addTrackedJobs(int index, TrackedJob value) {
            if (value != null) {
                ensureTrackedJobsIsMutable();
                this.trackedJobs_.add(index, value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addTrackedJobs(TrackedJob.Builder builderForValue) {
            ensureTrackedJobsIsMutable();
            this.trackedJobs_.add((TrackedJob) builderForValue.build());
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addTrackedJobs(int index, TrackedJob.Builder builderForValue) {
            ensureTrackedJobsIsMutable();
            this.trackedJobs_.add(index, (TrackedJob) builderForValue.build());
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addAllTrackedJobs(Iterable<? extends TrackedJob> values) {
            ensureTrackedJobsIsMutable();
            AbstractMessageLite.addAll(values, this.trackedJobs_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearTrackedJobs() {
            this.trackedJobs_ = emptyProtobufList();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void removeTrackedJobs(int index) {
            ensureTrackedJobsIsMutable();
            this.trackedJobs_.remove(index);
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                output.writeInt64(1, this.nowElapsedRealtime_);
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeInt64(2, this.timeUntilNextDelayAlarmMs_);
            }
            if ((this.bitField0_ & 4) == 4) {
                output.writeInt64(3, this.timeUntilNextDeadlineAlarmMs_);
            }
            for (int i = 0; i < this.trackedJobs_.size(); i++) {
                output.writeMessage(4, this.trackedJobs_.get(i));
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
                size2 = 0 + CodedOutputStream.computeInt64Size(1, this.nowElapsedRealtime_);
            }
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeInt64Size(2, this.timeUntilNextDelayAlarmMs_);
            }
            if ((this.bitField0_ & 4) == 4) {
                size2 += CodedOutputStream.computeInt64Size(3, this.timeUntilNextDeadlineAlarmMs_);
            }
            for (int i = 0; i < this.trackedJobs_.size(); i++) {
                size2 += CodedOutputStream.computeMessageSize(4, this.trackedJobs_.get(i));
            }
            int size3 = size2 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static TimeController parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (TimeController) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static TimeController parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (TimeController) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static TimeController parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (TimeController) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static TimeController parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (TimeController) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static TimeController parseFrom(InputStream input) throws IOException {
            return (TimeController) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static TimeController parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (TimeController) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static TimeController parseDelimitedFrom(InputStream input) throws IOException {
            return (TimeController) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static TimeController parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (TimeController) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static TimeController parseFrom(CodedInputStream input) throws IOException {
            return (TimeController) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static TimeController parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (TimeController) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(TimeController prototype) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<TimeController, Builder> implements TimeControllerOrBuilder {
            private Builder() {
                super(TimeController.DEFAULT_INSTANCE);
            }

            @Override // com.android.server.job.StateControllerProto.TimeControllerOrBuilder
            public boolean hasNowElapsedRealtime() {
                return ((TimeController) this.instance).hasNowElapsedRealtime();
            }

            @Override // com.android.server.job.StateControllerProto.TimeControllerOrBuilder
            public long getNowElapsedRealtime() {
                return ((TimeController) this.instance).getNowElapsedRealtime();
            }

            public Builder setNowElapsedRealtime(long value) {
                copyOnWrite();
                ((TimeController) this.instance).setNowElapsedRealtime(value);
                return this;
            }

            public Builder clearNowElapsedRealtime() {
                copyOnWrite();
                ((TimeController) this.instance).clearNowElapsedRealtime();
                return this;
            }

            @Override // com.android.server.job.StateControllerProto.TimeControllerOrBuilder
            public boolean hasTimeUntilNextDelayAlarmMs() {
                return ((TimeController) this.instance).hasTimeUntilNextDelayAlarmMs();
            }

            @Override // com.android.server.job.StateControllerProto.TimeControllerOrBuilder
            public long getTimeUntilNextDelayAlarmMs() {
                return ((TimeController) this.instance).getTimeUntilNextDelayAlarmMs();
            }

            public Builder setTimeUntilNextDelayAlarmMs(long value) {
                copyOnWrite();
                ((TimeController) this.instance).setTimeUntilNextDelayAlarmMs(value);
                return this;
            }

            public Builder clearTimeUntilNextDelayAlarmMs() {
                copyOnWrite();
                ((TimeController) this.instance).clearTimeUntilNextDelayAlarmMs();
                return this;
            }

            @Override // com.android.server.job.StateControllerProto.TimeControllerOrBuilder
            public boolean hasTimeUntilNextDeadlineAlarmMs() {
                return ((TimeController) this.instance).hasTimeUntilNextDeadlineAlarmMs();
            }

            @Override // com.android.server.job.StateControllerProto.TimeControllerOrBuilder
            public long getTimeUntilNextDeadlineAlarmMs() {
                return ((TimeController) this.instance).getTimeUntilNextDeadlineAlarmMs();
            }

            public Builder setTimeUntilNextDeadlineAlarmMs(long value) {
                copyOnWrite();
                ((TimeController) this.instance).setTimeUntilNextDeadlineAlarmMs(value);
                return this;
            }

            public Builder clearTimeUntilNextDeadlineAlarmMs() {
                copyOnWrite();
                ((TimeController) this.instance).clearTimeUntilNextDeadlineAlarmMs();
                return this;
            }

            @Override // com.android.server.job.StateControllerProto.TimeControllerOrBuilder
            public List<TrackedJob> getTrackedJobsList() {
                return Collections.unmodifiableList(((TimeController) this.instance).getTrackedJobsList());
            }

            @Override // com.android.server.job.StateControllerProto.TimeControllerOrBuilder
            public int getTrackedJobsCount() {
                return ((TimeController) this.instance).getTrackedJobsCount();
            }

            @Override // com.android.server.job.StateControllerProto.TimeControllerOrBuilder
            public TrackedJob getTrackedJobs(int index) {
                return ((TimeController) this.instance).getTrackedJobs(index);
            }

            public Builder setTrackedJobs(int index, TrackedJob value) {
                copyOnWrite();
                ((TimeController) this.instance).setTrackedJobs((TimeController) index, (int) value);
                return this;
            }

            public Builder setTrackedJobs(int index, TrackedJob.Builder builderForValue) {
                copyOnWrite();
                ((TimeController) this.instance).setTrackedJobs((TimeController) index, (int) builderForValue);
                return this;
            }

            public Builder addTrackedJobs(TrackedJob value) {
                copyOnWrite();
                ((TimeController) this.instance).addTrackedJobs((TimeController) value);
                return this;
            }

            public Builder addTrackedJobs(int index, TrackedJob value) {
                copyOnWrite();
                ((TimeController) this.instance).addTrackedJobs((TimeController) index, (int) value);
                return this;
            }

            public Builder addTrackedJobs(TrackedJob.Builder builderForValue) {
                copyOnWrite();
                ((TimeController) this.instance).addTrackedJobs((TimeController) builderForValue);
                return this;
            }

            public Builder addTrackedJobs(int index, TrackedJob.Builder builderForValue) {
                copyOnWrite();
                ((TimeController) this.instance).addTrackedJobs((TimeController) index, (int) builderForValue);
                return this;
            }

            public Builder addAllTrackedJobs(Iterable<? extends TrackedJob> values) {
                copyOnWrite();
                ((TimeController) this.instance).addAllTrackedJobs(values);
                return this;
            }

            public Builder clearTrackedJobs() {
                copyOnWrite();
                ((TimeController) this.instance).clearTrackedJobs();
                return this;
            }

            public Builder removeTrackedJobs(int index) {
                copyOnWrite();
                ((TimeController) this.instance).removeTrackedJobs(index);
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new TimeController();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    this.trackedJobs_.makeImmutable();
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    TimeController other = (TimeController) arg1;
                    this.nowElapsedRealtime_ = visitor.visitLong(hasNowElapsedRealtime(), this.nowElapsedRealtime_, other.hasNowElapsedRealtime(), other.nowElapsedRealtime_);
                    this.timeUntilNextDelayAlarmMs_ = visitor.visitLong(hasTimeUntilNextDelayAlarmMs(), this.timeUntilNextDelayAlarmMs_, other.hasTimeUntilNextDelayAlarmMs(), other.timeUntilNextDelayAlarmMs_);
                    this.timeUntilNextDeadlineAlarmMs_ = visitor.visitLong(hasTimeUntilNextDeadlineAlarmMs(), this.timeUntilNextDeadlineAlarmMs_, other.hasTimeUntilNextDeadlineAlarmMs(), other.timeUntilNextDeadlineAlarmMs_);
                    this.trackedJobs_ = visitor.visitList(this.trackedJobs_, other.trackedJobs_);
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
                                this.nowElapsedRealtime_ = input.readInt64();
                            } else if (tag == 16) {
                                this.bitField0_ |= 2;
                                this.timeUntilNextDelayAlarmMs_ = input.readInt64();
                            } else if (tag == 24) {
                                this.bitField0_ |= 4;
                                this.timeUntilNextDeadlineAlarmMs_ = input.readInt64();
                            } else if (tag == 34) {
                                if (!this.trackedJobs_.isModifiable()) {
                                    this.trackedJobs_ = GeneratedMessageLite.mutableCopy(this.trackedJobs_);
                                }
                                this.trackedJobs_.add((TrackedJob) input.readMessage(TrackedJob.parser(), extensionRegistry));
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
                        synchronized (TimeController.class) {
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

        public static TimeController getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<TimeController> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public enum ControllerCase implements Internal.EnumLite {
        BACKGROUND(1),
        BATTERY(2),
        CONNECTIVITY(3),
        CONTENT_OBSERVER(4),
        DEVICE_IDLE(5),
        IDLE(6),
        QUOTA(9),
        STORAGE(7),
        TIME(8),
        CONTROLLER_NOT_SET(0);
        
        private final int value;

        private ControllerCase(int value2) {
            this.value = value2;
        }

        @Deprecated
        public static ControllerCase valueOf(int value2) {
            return forNumber(value2);
        }

        public static ControllerCase forNumber(int value2) {
            switch (value2) {
                case 0:
                    return CONTROLLER_NOT_SET;
                case 1:
                    return BACKGROUND;
                case 2:
                    return BATTERY;
                case 3:
                    return CONNECTIVITY;
                case 4:
                    return CONTENT_OBSERVER;
                case 5:
                    return DEVICE_IDLE;
                case 6:
                    return IDLE;
                case 7:
                    return STORAGE;
                case 8:
                    return TIME;
                case 9:
                    return QUOTA;
                default:
                    return null;
            }
        }

        @Override // com.google.protobuf.Internal.EnumLite
        public int getNumber() {
            return this.value;
        }
    }

    @Override // com.android.server.job.StateControllerProtoOrBuilder
    public ControllerCase getControllerCase() {
        return ControllerCase.forNumber(this.controllerCase_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearController() {
        this.controllerCase_ = 0;
        this.controller_ = null;
    }

    @Override // com.android.server.job.StateControllerProtoOrBuilder
    public boolean hasBackground() {
        return this.controllerCase_ == 1;
    }

    @Override // com.android.server.job.StateControllerProtoOrBuilder
    public BackgroundJobsController getBackground() {
        if (this.controllerCase_ == 1) {
            return (BackgroundJobsController) this.controller_;
        }
        return BackgroundJobsController.getDefaultInstance();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setBackground(BackgroundJobsController value) {
        if (value != null) {
            this.controller_ = value;
            this.controllerCase_ = 1;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setBackground(BackgroundJobsController.Builder builderForValue) {
        this.controller_ = builderForValue.build();
        this.controllerCase_ = 1;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeBackground(BackgroundJobsController value) {
        if (this.controllerCase_ != 1 || this.controller_ == BackgroundJobsController.getDefaultInstance()) {
            this.controller_ = value;
        } else {
            this.controller_ = ((BackgroundJobsController.Builder) BackgroundJobsController.newBuilder((BackgroundJobsController) this.controller_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.controllerCase_ = 1;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearBackground() {
        if (this.controllerCase_ == 1) {
            this.controllerCase_ = 0;
            this.controller_ = null;
        }
    }

    @Override // com.android.server.job.StateControllerProtoOrBuilder
    public boolean hasBattery() {
        return this.controllerCase_ == 2;
    }

    @Override // com.android.server.job.StateControllerProtoOrBuilder
    public BatteryController getBattery() {
        if (this.controllerCase_ == 2) {
            return (BatteryController) this.controller_;
        }
        return BatteryController.getDefaultInstance();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setBattery(BatteryController value) {
        if (value != null) {
            this.controller_ = value;
            this.controllerCase_ = 2;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setBattery(BatteryController.Builder builderForValue) {
        this.controller_ = builderForValue.build();
        this.controllerCase_ = 2;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeBattery(BatteryController value) {
        if (this.controllerCase_ != 2 || this.controller_ == BatteryController.getDefaultInstance()) {
            this.controller_ = value;
        } else {
            this.controller_ = ((BatteryController.Builder) BatteryController.newBuilder((BatteryController) this.controller_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.controllerCase_ = 2;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearBattery() {
        if (this.controllerCase_ == 2) {
            this.controllerCase_ = 0;
            this.controller_ = null;
        }
    }

    @Override // com.android.server.job.StateControllerProtoOrBuilder
    public boolean hasConnectivity() {
        return this.controllerCase_ == 3;
    }

    @Override // com.android.server.job.StateControllerProtoOrBuilder
    public ConnectivityController getConnectivity() {
        if (this.controllerCase_ == 3) {
            return (ConnectivityController) this.controller_;
        }
        return ConnectivityController.getDefaultInstance();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setConnectivity(ConnectivityController value) {
        if (value != null) {
            this.controller_ = value;
            this.controllerCase_ = 3;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setConnectivity(ConnectivityController.Builder builderForValue) {
        this.controller_ = builderForValue.build();
        this.controllerCase_ = 3;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeConnectivity(ConnectivityController value) {
        if (this.controllerCase_ != 3 || this.controller_ == ConnectivityController.getDefaultInstance()) {
            this.controller_ = value;
        } else {
            this.controller_ = ((ConnectivityController.Builder) ConnectivityController.newBuilder((ConnectivityController) this.controller_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.controllerCase_ = 3;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearConnectivity() {
        if (this.controllerCase_ == 3) {
            this.controllerCase_ = 0;
            this.controller_ = null;
        }
    }

    @Override // com.android.server.job.StateControllerProtoOrBuilder
    public boolean hasContentObserver() {
        return this.controllerCase_ == 4;
    }

    @Override // com.android.server.job.StateControllerProtoOrBuilder
    public ContentObserverController getContentObserver() {
        if (this.controllerCase_ == 4) {
            return (ContentObserverController) this.controller_;
        }
        return ContentObserverController.getDefaultInstance();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setContentObserver(ContentObserverController value) {
        if (value != null) {
            this.controller_ = value;
            this.controllerCase_ = 4;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setContentObserver(ContentObserverController.Builder builderForValue) {
        this.controller_ = builderForValue.build();
        this.controllerCase_ = 4;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeContentObserver(ContentObserverController value) {
        if (this.controllerCase_ != 4 || this.controller_ == ContentObserverController.getDefaultInstance()) {
            this.controller_ = value;
        } else {
            this.controller_ = ((ContentObserverController.Builder) ContentObserverController.newBuilder((ContentObserverController) this.controller_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.controllerCase_ = 4;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearContentObserver() {
        if (this.controllerCase_ == 4) {
            this.controllerCase_ = 0;
            this.controller_ = null;
        }
    }

    @Override // com.android.server.job.StateControllerProtoOrBuilder
    public boolean hasDeviceIdle() {
        return this.controllerCase_ == 5;
    }

    @Override // com.android.server.job.StateControllerProtoOrBuilder
    public DeviceIdleJobsController getDeviceIdle() {
        if (this.controllerCase_ == 5) {
            return (DeviceIdleJobsController) this.controller_;
        }
        return DeviceIdleJobsController.getDefaultInstance();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setDeviceIdle(DeviceIdleJobsController value) {
        if (value != null) {
            this.controller_ = value;
            this.controllerCase_ = 5;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setDeviceIdle(DeviceIdleJobsController.Builder builderForValue) {
        this.controller_ = builderForValue.build();
        this.controllerCase_ = 5;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeDeviceIdle(DeviceIdleJobsController value) {
        if (this.controllerCase_ != 5 || this.controller_ == DeviceIdleJobsController.getDefaultInstance()) {
            this.controller_ = value;
        } else {
            this.controller_ = ((DeviceIdleJobsController.Builder) DeviceIdleJobsController.newBuilder((DeviceIdleJobsController) this.controller_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.controllerCase_ = 5;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearDeviceIdle() {
        if (this.controllerCase_ == 5) {
            this.controllerCase_ = 0;
            this.controller_ = null;
        }
    }

    @Override // com.android.server.job.StateControllerProtoOrBuilder
    public boolean hasIdle() {
        return this.controllerCase_ == 6;
    }

    @Override // com.android.server.job.StateControllerProtoOrBuilder
    public IdleController getIdle() {
        if (this.controllerCase_ == 6) {
            return (IdleController) this.controller_;
        }
        return IdleController.getDefaultInstance();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setIdle(IdleController value) {
        if (value != null) {
            this.controller_ = value;
            this.controllerCase_ = 6;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setIdle(IdleController.Builder builderForValue) {
        this.controller_ = builderForValue.build();
        this.controllerCase_ = 6;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeIdle(IdleController value) {
        if (this.controllerCase_ != 6 || this.controller_ == IdleController.getDefaultInstance()) {
            this.controller_ = value;
        } else {
            this.controller_ = ((IdleController.Builder) IdleController.newBuilder((IdleController) this.controller_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.controllerCase_ = 6;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearIdle() {
        if (this.controllerCase_ == 6) {
            this.controllerCase_ = 0;
            this.controller_ = null;
        }
    }

    @Override // com.android.server.job.StateControllerProtoOrBuilder
    public boolean hasQuota() {
        return this.controllerCase_ == 9;
    }

    @Override // com.android.server.job.StateControllerProtoOrBuilder
    public QuotaController getQuota() {
        if (this.controllerCase_ == 9) {
            return (QuotaController) this.controller_;
        }
        return QuotaController.getDefaultInstance();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setQuota(QuotaController value) {
        if (value != null) {
            this.controller_ = value;
            this.controllerCase_ = 9;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setQuota(QuotaController.Builder builderForValue) {
        this.controller_ = builderForValue.build();
        this.controllerCase_ = 9;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeQuota(QuotaController value) {
        if (this.controllerCase_ != 9 || this.controller_ == QuotaController.getDefaultInstance()) {
            this.controller_ = value;
        } else {
            this.controller_ = ((QuotaController.Builder) QuotaController.newBuilder((QuotaController) this.controller_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.controllerCase_ = 9;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearQuota() {
        if (this.controllerCase_ == 9) {
            this.controllerCase_ = 0;
            this.controller_ = null;
        }
    }

    @Override // com.android.server.job.StateControllerProtoOrBuilder
    public boolean hasStorage() {
        return this.controllerCase_ == 7;
    }

    @Override // com.android.server.job.StateControllerProtoOrBuilder
    public StorageController getStorage() {
        if (this.controllerCase_ == 7) {
            return (StorageController) this.controller_;
        }
        return StorageController.getDefaultInstance();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setStorage(StorageController value) {
        if (value != null) {
            this.controller_ = value;
            this.controllerCase_ = 7;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setStorage(StorageController.Builder builderForValue) {
        this.controller_ = builderForValue.build();
        this.controllerCase_ = 7;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeStorage(StorageController value) {
        if (this.controllerCase_ != 7 || this.controller_ == StorageController.getDefaultInstance()) {
            this.controller_ = value;
        } else {
            this.controller_ = ((StorageController.Builder) StorageController.newBuilder((StorageController) this.controller_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.controllerCase_ = 7;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearStorage() {
        if (this.controllerCase_ == 7) {
            this.controllerCase_ = 0;
            this.controller_ = null;
        }
    }

    @Override // com.android.server.job.StateControllerProtoOrBuilder
    public boolean hasTime() {
        return this.controllerCase_ == 8;
    }

    @Override // com.android.server.job.StateControllerProtoOrBuilder
    public TimeController getTime() {
        if (this.controllerCase_ == 8) {
            return (TimeController) this.controller_;
        }
        return TimeController.getDefaultInstance();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setTime(TimeController value) {
        if (value != null) {
            this.controller_ = value;
            this.controllerCase_ = 8;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setTime(TimeController.Builder builderForValue) {
        this.controller_ = builderForValue.build();
        this.controllerCase_ = 8;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeTime(TimeController value) {
        if (this.controllerCase_ != 8 || this.controller_ == TimeController.getDefaultInstance()) {
            this.controller_ = value;
        } else {
            this.controller_ = ((TimeController.Builder) TimeController.newBuilder((TimeController) this.controller_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.controllerCase_ = 8;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearTime() {
        if (this.controllerCase_ == 8) {
            this.controllerCase_ = 0;
            this.controller_ = null;
        }
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if (this.controllerCase_ == 1) {
            output.writeMessage(1, (BackgroundJobsController) this.controller_);
        }
        if (this.controllerCase_ == 2) {
            output.writeMessage(2, (BatteryController) this.controller_);
        }
        if (this.controllerCase_ == 3) {
            output.writeMessage(3, (ConnectivityController) this.controller_);
        }
        if (this.controllerCase_ == 4) {
            output.writeMessage(4, (ContentObserverController) this.controller_);
        }
        if (this.controllerCase_ == 5) {
            output.writeMessage(5, (DeviceIdleJobsController) this.controller_);
        }
        if (this.controllerCase_ == 6) {
            output.writeMessage(6, (IdleController) this.controller_);
        }
        if (this.controllerCase_ == 7) {
            output.writeMessage(7, (StorageController) this.controller_);
        }
        if (this.controllerCase_ == 8) {
            output.writeMessage(8, (TimeController) this.controller_);
        }
        if (this.controllerCase_ == 9) {
            output.writeMessage(9, (QuotaController) this.controller_);
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
        if (this.controllerCase_ == 1) {
            size2 = 0 + CodedOutputStream.computeMessageSize(1, (BackgroundJobsController) this.controller_);
        }
        if (this.controllerCase_ == 2) {
            size2 += CodedOutputStream.computeMessageSize(2, (BatteryController) this.controller_);
        }
        if (this.controllerCase_ == 3) {
            size2 += CodedOutputStream.computeMessageSize(3, (ConnectivityController) this.controller_);
        }
        if (this.controllerCase_ == 4) {
            size2 += CodedOutputStream.computeMessageSize(4, (ContentObserverController) this.controller_);
        }
        if (this.controllerCase_ == 5) {
            size2 += CodedOutputStream.computeMessageSize(5, (DeviceIdleJobsController) this.controller_);
        }
        if (this.controllerCase_ == 6) {
            size2 += CodedOutputStream.computeMessageSize(6, (IdleController) this.controller_);
        }
        if (this.controllerCase_ == 7) {
            size2 += CodedOutputStream.computeMessageSize(7, (StorageController) this.controller_);
        }
        if (this.controllerCase_ == 8) {
            size2 += CodedOutputStream.computeMessageSize(8, (TimeController) this.controller_);
        }
        if (this.controllerCase_ == 9) {
            size2 += CodedOutputStream.computeMessageSize(9, (QuotaController) this.controller_);
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static StateControllerProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (StateControllerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static StateControllerProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (StateControllerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static StateControllerProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (StateControllerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static StateControllerProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (StateControllerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static StateControllerProto parseFrom(InputStream input) throws IOException {
        return (StateControllerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static StateControllerProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (StateControllerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static StateControllerProto parseDelimitedFrom(InputStream input) throws IOException {
        return (StateControllerProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static StateControllerProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (StateControllerProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static StateControllerProto parseFrom(CodedInputStream input) throws IOException {
        return (StateControllerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static StateControllerProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (StateControllerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(StateControllerProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<StateControllerProto, Builder> implements StateControllerProtoOrBuilder {
        private Builder() {
            super(StateControllerProto.DEFAULT_INSTANCE);
        }

        @Override // com.android.server.job.StateControllerProtoOrBuilder
        public ControllerCase getControllerCase() {
            return ((StateControllerProto) this.instance).getControllerCase();
        }

        public Builder clearController() {
            copyOnWrite();
            ((StateControllerProto) this.instance).clearController();
            return this;
        }

        @Override // com.android.server.job.StateControllerProtoOrBuilder
        public boolean hasBackground() {
            return ((StateControllerProto) this.instance).hasBackground();
        }

        @Override // com.android.server.job.StateControllerProtoOrBuilder
        public BackgroundJobsController getBackground() {
            return ((StateControllerProto) this.instance).getBackground();
        }

        public Builder setBackground(BackgroundJobsController value) {
            copyOnWrite();
            ((StateControllerProto) this.instance).setBackground((StateControllerProto) value);
            return this;
        }

        public Builder setBackground(BackgroundJobsController.Builder builderForValue) {
            copyOnWrite();
            ((StateControllerProto) this.instance).setBackground((StateControllerProto) builderForValue);
            return this;
        }

        public Builder mergeBackground(BackgroundJobsController value) {
            copyOnWrite();
            ((StateControllerProto) this.instance).mergeBackground(value);
            return this;
        }

        public Builder clearBackground() {
            copyOnWrite();
            ((StateControllerProto) this.instance).clearBackground();
            return this;
        }

        @Override // com.android.server.job.StateControllerProtoOrBuilder
        public boolean hasBattery() {
            return ((StateControllerProto) this.instance).hasBattery();
        }

        @Override // com.android.server.job.StateControllerProtoOrBuilder
        public BatteryController getBattery() {
            return ((StateControllerProto) this.instance).getBattery();
        }

        public Builder setBattery(BatteryController value) {
            copyOnWrite();
            ((StateControllerProto) this.instance).setBattery((StateControllerProto) value);
            return this;
        }

        public Builder setBattery(BatteryController.Builder builderForValue) {
            copyOnWrite();
            ((StateControllerProto) this.instance).setBattery((StateControllerProto) builderForValue);
            return this;
        }

        public Builder mergeBattery(BatteryController value) {
            copyOnWrite();
            ((StateControllerProto) this.instance).mergeBattery(value);
            return this;
        }

        public Builder clearBattery() {
            copyOnWrite();
            ((StateControllerProto) this.instance).clearBattery();
            return this;
        }

        @Override // com.android.server.job.StateControllerProtoOrBuilder
        public boolean hasConnectivity() {
            return ((StateControllerProto) this.instance).hasConnectivity();
        }

        @Override // com.android.server.job.StateControllerProtoOrBuilder
        public ConnectivityController getConnectivity() {
            return ((StateControllerProto) this.instance).getConnectivity();
        }

        public Builder setConnectivity(ConnectivityController value) {
            copyOnWrite();
            ((StateControllerProto) this.instance).setConnectivity((StateControllerProto) value);
            return this;
        }

        public Builder setConnectivity(ConnectivityController.Builder builderForValue) {
            copyOnWrite();
            ((StateControllerProto) this.instance).setConnectivity((StateControllerProto) builderForValue);
            return this;
        }

        public Builder mergeConnectivity(ConnectivityController value) {
            copyOnWrite();
            ((StateControllerProto) this.instance).mergeConnectivity(value);
            return this;
        }

        public Builder clearConnectivity() {
            copyOnWrite();
            ((StateControllerProto) this.instance).clearConnectivity();
            return this;
        }

        @Override // com.android.server.job.StateControllerProtoOrBuilder
        public boolean hasContentObserver() {
            return ((StateControllerProto) this.instance).hasContentObserver();
        }

        @Override // com.android.server.job.StateControllerProtoOrBuilder
        public ContentObserverController getContentObserver() {
            return ((StateControllerProto) this.instance).getContentObserver();
        }

        public Builder setContentObserver(ContentObserverController value) {
            copyOnWrite();
            ((StateControllerProto) this.instance).setContentObserver((StateControllerProto) value);
            return this;
        }

        public Builder setContentObserver(ContentObserverController.Builder builderForValue) {
            copyOnWrite();
            ((StateControllerProto) this.instance).setContentObserver((StateControllerProto) builderForValue);
            return this;
        }

        public Builder mergeContentObserver(ContentObserverController value) {
            copyOnWrite();
            ((StateControllerProto) this.instance).mergeContentObserver(value);
            return this;
        }

        public Builder clearContentObserver() {
            copyOnWrite();
            ((StateControllerProto) this.instance).clearContentObserver();
            return this;
        }

        @Override // com.android.server.job.StateControllerProtoOrBuilder
        public boolean hasDeviceIdle() {
            return ((StateControllerProto) this.instance).hasDeviceIdle();
        }

        @Override // com.android.server.job.StateControllerProtoOrBuilder
        public DeviceIdleJobsController getDeviceIdle() {
            return ((StateControllerProto) this.instance).getDeviceIdle();
        }

        public Builder setDeviceIdle(DeviceIdleJobsController value) {
            copyOnWrite();
            ((StateControllerProto) this.instance).setDeviceIdle((StateControllerProto) value);
            return this;
        }

        public Builder setDeviceIdle(DeviceIdleJobsController.Builder builderForValue) {
            copyOnWrite();
            ((StateControllerProto) this.instance).setDeviceIdle((StateControllerProto) builderForValue);
            return this;
        }

        public Builder mergeDeviceIdle(DeviceIdleJobsController value) {
            copyOnWrite();
            ((StateControllerProto) this.instance).mergeDeviceIdle(value);
            return this;
        }

        public Builder clearDeviceIdle() {
            copyOnWrite();
            ((StateControllerProto) this.instance).clearDeviceIdle();
            return this;
        }

        @Override // com.android.server.job.StateControllerProtoOrBuilder
        public boolean hasIdle() {
            return ((StateControllerProto) this.instance).hasIdle();
        }

        @Override // com.android.server.job.StateControllerProtoOrBuilder
        public IdleController getIdle() {
            return ((StateControllerProto) this.instance).getIdle();
        }

        public Builder setIdle(IdleController value) {
            copyOnWrite();
            ((StateControllerProto) this.instance).setIdle((StateControllerProto) value);
            return this;
        }

        public Builder setIdle(IdleController.Builder builderForValue) {
            copyOnWrite();
            ((StateControllerProto) this.instance).setIdle((StateControllerProto) builderForValue);
            return this;
        }

        public Builder mergeIdle(IdleController value) {
            copyOnWrite();
            ((StateControllerProto) this.instance).mergeIdle(value);
            return this;
        }

        public Builder clearIdle() {
            copyOnWrite();
            ((StateControllerProto) this.instance).clearIdle();
            return this;
        }

        @Override // com.android.server.job.StateControllerProtoOrBuilder
        public boolean hasQuota() {
            return ((StateControllerProto) this.instance).hasQuota();
        }

        @Override // com.android.server.job.StateControllerProtoOrBuilder
        public QuotaController getQuota() {
            return ((StateControllerProto) this.instance).getQuota();
        }

        public Builder setQuota(QuotaController value) {
            copyOnWrite();
            ((StateControllerProto) this.instance).setQuota((StateControllerProto) value);
            return this;
        }

        public Builder setQuota(QuotaController.Builder builderForValue) {
            copyOnWrite();
            ((StateControllerProto) this.instance).setQuota((StateControllerProto) builderForValue);
            return this;
        }

        public Builder mergeQuota(QuotaController value) {
            copyOnWrite();
            ((StateControllerProto) this.instance).mergeQuota(value);
            return this;
        }

        public Builder clearQuota() {
            copyOnWrite();
            ((StateControllerProto) this.instance).clearQuota();
            return this;
        }

        @Override // com.android.server.job.StateControllerProtoOrBuilder
        public boolean hasStorage() {
            return ((StateControllerProto) this.instance).hasStorage();
        }

        @Override // com.android.server.job.StateControllerProtoOrBuilder
        public StorageController getStorage() {
            return ((StateControllerProto) this.instance).getStorage();
        }

        public Builder setStorage(StorageController value) {
            copyOnWrite();
            ((StateControllerProto) this.instance).setStorage((StateControllerProto) value);
            return this;
        }

        public Builder setStorage(StorageController.Builder builderForValue) {
            copyOnWrite();
            ((StateControllerProto) this.instance).setStorage((StateControllerProto) builderForValue);
            return this;
        }

        public Builder mergeStorage(StorageController value) {
            copyOnWrite();
            ((StateControllerProto) this.instance).mergeStorage(value);
            return this;
        }

        public Builder clearStorage() {
            copyOnWrite();
            ((StateControllerProto) this.instance).clearStorage();
            return this;
        }

        @Override // com.android.server.job.StateControllerProtoOrBuilder
        public boolean hasTime() {
            return ((StateControllerProto) this.instance).hasTime();
        }

        @Override // com.android.server.job.StateControllerProtoOrBuilder
        public TimeController getTime() {
            return ((StateControllerProto) this.instance).getTime();
        }

        public Builder setTime(TimeController value) {
            copyOnWrite();
            ((StateControllerProto) this.instance).setTime((StateControllerProto) value);
            return this;
        }

        public Builder setTime(TimeController.Builder builderForValue) {
            copyOnWrite();
            ((StateControllerProto) this.instance).setTime((StateControllerProto) builderForValue);
            return this;
        }

        public Builder mergeTime(TimeController value) {
            copyOnWrite();
            ((StateControllerProto) this.instance).mergeTime(value);
            return this;
        }

        public Builder clearTime() {
            copyOnWrite();
            ((StateControllerProto) this.instance).clearTime();
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new StateControllerProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                StateControllerProto other = (StateControllerProto) arg1;
                boolean z = false;
                switch (other.getControllerCase()) {
                    case BACKGROUND:
                        if (this.controllerCase_ == 1) {
                            z = true;
                        }
                        this.controller_ = visitor.visitOneofMessage(z, this.controller_, other.controller_);
                        break;
                    case BATTERY:
                        if (this.controllerCase_ == 2) {
                            z = true;
                        }
                        this.controller_ = visitor.visitOneofMessage(z, this.controller_, other.controller_);
                        break;
                    case CONNECTIVITY:
                        if (this.controllerCase_ == 3) {
                            z = true;
                        }
                        this.controller_ = visitor.visitOneofMessage(z, this.controller_, other.controller_);
                        break;
                    case CONTENT_OBSERVER:
                        if (this.controllerCase_ == 4) {
                            z = true;
                        }
                        this.controller_ = visitor.visitOneofMessage(z, this.controller_, other.controller_);
                        break;
                    case DEVICE_IDLE:
                        if (this.controllerCase_ == 5) {
                            z = true;
                        }
                        this.controller_ = visitor.visitOneofMessage(z, this.controller_, other.controller_);
                        break;
                    case IDLE:
                        if (this.controllerCase_ == 6) {
                            z = true;
                        }
                        this.controller_ = visitor.visitOneofMessage(z, this.controller_, other.controller_);
                        break;
                    case QUOTA:
                        if (this.controllerCase_ == 9) {
                            z = true;
                        }
                        this.controller_ = visitor.visitOneofMessage(z, this.controller_, other.controller_);
                        break;
                    case STORAGE:
                        if (this.controllerCase_ == 7) {
                            z = true;
                        }
                        this.controller_ = visitor.visitOneofMessage(z, this.controller_, other.controller_);
                        break;
                    case TIME:
                        if (this.controllerCase_ == 8) {
                            z = true;
                        }
                        this.controller_ = visitor.visitOneofMessage(z, this.controller_, other.controller_);
                        break;
                    case CONTROLLER_NOT_SET:
                        if (this.controllerCase_ != 0) {
                            z = true;
                        }
                        visitor.visitOneofNotSet(z);
                        break;
                }
                if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE) {
                    int i = other.controllerCase_;
                    if (i != 0) {
                        this.controllerCase_ = i;
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
                            BackgroundJobsController.Builder subBuilder = null;
                            if (this.controllerCase_ == 1) {
                                subBuilder = (BackgroundJobsController.Builder) ((BackgroundJobsController) this.controller_).toBuilder();
                            }
                            this.controller_ = input.readMessage(BackgroundJobsController.parser(), extensionRegistry);
                            if (subBuilder != null) {
                                subBuilder.mergeFrom((GeneratedMessageLite) ((BackgroundJobsController) this.controller_));
                                this.controller_ = subBuilder.buildPartial();
                            }
                            this.controllerCase_ = 1;
                        } else if (tag == 18) {
                            BatteryController.Builder subBuilder2 = null;
                            if (this.controllerCase_ == 2) {
                                subBuilder2 = (BatteryController.Builder) ((BatteryController) this.controller_).toBuilder();
                            }
                            this.controller_ = input.readMessage(BatteryController.parser(), extensionRegistry);
                            if (subBuilder2 != null) {
                                subBuilder2.mergeFrom((GeneratedMessageLite) ((BatteryController) this.controller_));
                                this.controller_ = subBuilder2.buildPartial();
                            }
                            this.controllerCase_ = 2;
                        } else if (tag == 26) {
                            ConnectivityController.Builder subBuilder3 = null;
                            if (this.controllerCase_ == 3) {
                                subBuilder3 = (ConnectivityController.Builder) ((ConnectivityController) this.controller_).toBuilder();
                            }
                            this.controller_ = input.readMessage(ConnectivityController.parser(), extensionRegistry);
                            if (subBuilder3 != null) {
                                subBuilder3.mergeFrom((GeneratedMessageLite) ((ConnectivityController) this.controller_));
                                this.controller_ = subBuilder3.buildPartial();
                            }
                            this.controllerCase_ = 3;
                        } else if (tag == 34) {
                            ContentObserverController.Builder subBuilder4 = null;
                            if (this.controllerCase_ == 4) {
                                subBuilder4 = (ContentObserverController.Builder) ((ContentObserverController) this.controller_).toBuilder();
                            }
                            this.controller_ = input.readMessage(ContentObserverController.parser(), extensionRegistry);
                            if (subBuilder4 != null) {
                                subBuilder4.mergeFrom((GeneratedMessageLite) ((ContentObserverController) this.controller_));
                                this.controller_ = subBuilder4.buildPartial();
                            }
                            this.controllerCase_ = 4;
                        } else if (tag == 42) {
                            DeviceIdleJobsController.Builder subBuilder5 = null;
                            if (this.controllerCase_ == 5) {
                                subBuilder5 = (DeviceIdleJobsController.Builder) ((DeviceIdleJobsController) this.controller_).toBuilder();
                            }
                            this.controller_ = input.readMessage(DeviceIdleJobsController.parser(), extensionRegistry);
                            if (subBuilder5 != null) {
                                subBuilder5.mergeFrom((GeneratedMessageLite) ((DeviceIdleJobsController) this.controller_));
                                this.controller_ = subBuilder5.buildPartial();
                            }
                            this.controllerCase_ = 5;
                        } else if (tag == 50) {
                            IdleController.Builder subBuilder6 = null;
                            if (this.controllerCase_ == 6) {
                                subBuilder6 = (IdleController.Builder) ((IdleController) this.controller_).toBuilder();
                            }
                            this.controller_ = input.readMessage(IdleController.parser(), extensionRegistry);
                            if (subBuilder6 != null) {
                                subBuilder6.mergeFrom((GeneratedMessageLite) ((IdleController) this.controller_));
                                this.controller_ = subBuilder6.buildPartial();
                            }
                            this.controllerCase_ = 6;
                        } else if (tag == 58) {
                            StorageController.Builder subBuilder7 = null;
                            if (this.controllerCase_ == 7) {
                                subBuilder7 = (StorageController.Builder) ((StorageController) this.controller_).toBuilder();
                            }
                            this.controller_ = input.readMessage(StorageController.parser(), extensionRegistry);
                            if (subBuilder7 != null) {
                                subBuilder7.mergeFrom((GeneratedMessageLite) ((StorageController) this.controller_));
                                this.controller_ = subBuilder7.buildPartial();
                            }
                            this.controllerCase_ = 7;
                        } else if (tag == 66) {
                            TimeController.Builder subBuilder8 = null;
                            if (this.controllerCase_ == 8) {
                                subBuilder8 = (TimeController.Builder) ((TimeController) this.controller_).toBuilder();
                            }
                            this.controller_ = input.readMessage(TimeController.parser(), extensionRegistry);
                            if (subBuilder8 != null) {
                                subBuilder8.mergeFrom((GeneratedMessageLite) ((TimeController) this.controller_));
                                this.controller_ = subBuilder8.buildPartial();
                            }
                            this.controllerCase_ = 8;
                        } else if (tag == 74) {
                            QuotaController.Builder subBuilder9 = null;
                            if (this.controllerCase_ == 9) {
                                subBuilder9 = (QuotaController.Builder) ((QuotaController) this.controller_).toBuilder();
                            }
                            this.controller_ = input.readMessage(QuotaController.parser(), extensionRegistry);
                            if (subBuilder9 != null) {
                                subBuilder9.mergeFrom((GeneratedMessageLite) ((QuotaController) this.controller_));
                                this.controller_ = subBuilder9.buildPartial();
                            }
                            this.controllerCase_ = 9;
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
                    synchronized (StateControllerProto.class) {
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

    public static StateControllerProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<StateControllerProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
