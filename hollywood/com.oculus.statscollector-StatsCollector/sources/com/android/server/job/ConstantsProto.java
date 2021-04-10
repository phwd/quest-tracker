package com.android.server.job;

import com.android.os.AtomsProto;
import com.android.server.job.MaxJobCountsPerMemoryTrimLevelProto;
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

public final class ConstantsProto extends GeneratedMessageLite<ConstantsProto, Builder> implements ConstantsProtoOrBuilder {
    public static final int BG_CRITICAL_JOB_COUNT_FIELD_NUMBER = 14;
    public static final int BG_LOW_JOB_COUNT_FIELD_NUMBER = 13;
    public static final int BG_MODERATE_JOB_COUNT_FIELD_NUMBER = 12;
    public static final int BG_NORMAL_JOB_COUNT_FIELD_NUMBER = 11;
    public static final int CONN_CONGESTION_DELAY_FRAC_FIELD_NUMBER = 21;
    public static final int CONN_PREFETCH_RELAX_FRAC_FIELD_NUMBER = 22;
    private static final ConstantsProto DEFAULT_INSTANCE = new ConstantsProto();
    public static final int FG_JOB_COUNT_FIELD_NUMBER = 10;
    public static final int HEAVY_USE_FACTOR_FIELD_NUMBER = 8;
    public static final int MAX_JOB_COUNTS_SCREEN_OFF_FIELD_NUMBER = 27;
    public static final int MAX_JOB_COUNTS_SCREEN_ON_FIELD_NUMBER = 26;
    public static final int MAX_STANDARD_RESCHEDULE_COUNT_FIELD_NUMBER = 15;
    public static final int MAX_WORK_RESCHEDULE_COUNT_FIELD_NUMBER = 16;
    public static final int MIN_BATTERY_NOT_LOW_COUNT_FIELD_NUMBER = 3;
    public static final int MIN_CHARGING_COUNT_FIELD_NUMBER = 2;
    public static final int MIN_CONNECTIVITY_COUNT_FIELD_NUMBER = 5;
    public static final int MIN_CONTENT_COUNT_FIELD_NUMBER = 6;
    public static final int MIN_EXP_BACKOFF_TIME_MS_FIELD_NUMBER = 18;
    public static final int MIN_IDLE_COUNT_FIELD_NUMBER = 1;
    public static final int MIN_LINEAR_BACKOFF_TIME_MS_FIELD_NUMBER = 17;
    public static final int MIN_READY_JOBS_COUNT_FIELD_NUMBER = 7;
    public static final int MIN_STORAGE_NOT_LOW_COUNT_FIELD_NUMBER = 4;
    public static final int MODERATE_USE_FACTOR_FIELD_NUMBER = 9;
    private static volatile Parser<ConstantsProto> PARSER = null;
    public static final int QUOTA_CONTROLLER_FIELD_NUMBER = 24;
    public static final int SCREEN_OFF_JOB_CONCURRENCY_INCREASE_DELAY_MS_FIELD_NUMBER = 28;
    public static final int STANDBY_BEATS_FIELD_NUMBER = 20;
    public static final int STANDBY_HEARTBEAT_TIME_MS_FIELD_NUMBER = 19;
    public static final int TIME_CONTROLLER_FIELD_NUMBER = 25;
    public static final int USE_HEARTBEATS_FIELD_NUMBER = 23;
    private int bgCriticalJobCount_ = 0;
    private int bgLowJobCount_ = 0;
    private int bgModerateJobCount_ = 0;
    private int bgNormalJobCount_ = 0;
    private int bitField0_;
    private double connCongestionDelayFrac_ = 0.0d;
    private double connPrefetchRelaxFrac_ = 0.0d;
    private int fgJobCount_ = 0;
    private double heavyUseFactor_ = 0.0d;
    private MaxJobCountsPerMemoryTrimLevelProto maxJobCountsScreenOff_;
    private MaxJobCountsPerMemoryTrimLevelProto maxJobCountsScreenOn_;
    private int maxStandardRescheduleCount_ = 0;
    private int maxWorkRescheduleCount_ = 0;
    private int minBatteryNotLowCount_ = 0;
    private int minChargingCount_ = 0;
    private int minConnectivityCount_ = 0;
    private int minContentCount_ = 0;
    private long minExpBackoffTimeMs_ = 0;
    private int minIdleCount_ = 0;
    private long minLinearBackoffTimeMs_ = 0;
    private int minReadyJobsCount_ = 0;
    private int minStorageNotLowCount_ = 0;
    private double moderateUseFactor_ = 0.0d;
    private QuotaController quotaController_;
    private int screenOffJobConcurrencyIncreaseDelayMs_ = 0;
    private Internal.IntList standbyBeats_ = emptyIntList();
    private long standbyHeartbeatTimeMs_ = 0;
    private TimeController timeController_;
    private boolean useHeartbeats_ = false;

    public interface QuotaControllerOrBuilder extends MessageLiteOrBuilder {
        long getActiveWindowSizeMs();

        long getAllowedTimePerPeriodMs();

        long getFrequentWindowSizeMs();

        long getInQuotaBufferMs();

        long getMaxExecutionTimeMs();

        int getMaxJobCountActive();

        int getMaxJobCountFrequent();

        int getMaxJobCountPerRateLimitingWindow();

        int getMaxJobCountRare();

        int getMaxJobCountWorking();

        int getMaxSessionCountActive();

        int getMaxSessionCountFrequent();

        int getMaxSessionCountPerRateLimitingWindow();

        int getMaxSessionCountRare();

        int getMaxSessionCountWorking();

        long getRareWindowSizeMs();

        int getRateLimitingWindowMs();

        long getTimingSessionCoalescingDurationMs();

        long getWorkingWindowSizeMs();

        boolean hasActiveWindowSizeMs();

        boolean hasAllowedTimePerPeriodMs();

        boolean hasFrequentWindowSizeMs();

        boolean hasInQuotaBufferMs();

        boolean hasMaxExecutionTimeMs();

        boolean hasMaxJobCountActive();

        boolean hasMaxJobCountFrequent();

        boolean hasMaxJobCountPerRateLimitingWindow();

        boolean hasMaxJobCountRare();

        boolean hasMaxJobCountWorking();

        boolean hasMaxSessionCountActive();

        boolean hasMaxSessionCountFrequent();

        boolean hasMaxSessionCountPerRateLimitingWindow();

        boolean hasMaxSessionCountRare();

        boolean hasMaxSessionCountWorking();

        boolean hasRareWindowSizeMs();

        boolean hasRateLimitingWindowMs();

        boolean hasTimingSessionCoalescingDurationMs();

        boolean hasWorkingWindowSizeMs();
    }

    public interface TimeControllerOrBuilder extends MessageLiteOrBuilder {
        boolean getSkipNotReadyJobs();

        boolean hasSkipNotReadyJobs();
    }

    private ConstantsProto() {
    }

    public static final class TimeController extends GeneratedMessageLite<TimeController, Builder> implements TimeControllerOrBuilder {
        private static final TimeController DEFAULT_INSTANCE = new TimeController();
        private static volatile Parser<TimeController> PARSER = null;
        public static final int SKIP_NOT_READY_JOBS_FIELD_NUMBER = 1;
        private int bitField0_;
        private boolean skipNotReadyJobs_ = false;

        private TimeController() {
        }

        @Override // com.android.server.job.ConstantsProto.TimeControllerOrBuilder
        public boolean hasSkipNotReadyJobs() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // com.android.server.job.ConstantsProto.TimeControllerOrBuilder
        public boolean getSkipNotReadyJobs() {
            return this.skipNotReadyJobs_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setSkipNotReadyJobs(boolean value) {
            this.bitField0_ |= 1;
            this.skipNotReadyJobs_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearSkipNotReadyJobs() {
            this.bitField0_ &= -2;
            this.skipNotReadyJobs_ = false;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                output.writeBool(1, this.skipNotReadyJobs_);
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
                size2 = 0 + CodedOutputStream.computeBoolSize(1, this.skipNotReadyJobs_);
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

            @Override // com.android.server.job.ConstantsProto.TimeControllerOrBuilder
            public boolean hasSkipNotReadyJobs() {
                return ((TimeController) this.instance).hasSkipNotReadyJobs();
            }

            @Override // com.android.server.job.ConstantsProto.TimeControllerOrBuilder
            public boolean getSkipNotReadyJobs() {
                return ((TimeController) this.instance).getSkipNotReadyJobs();
            }

            public Builder setSkipNotReadyJobs(boolean value) {
                copyOnWrite();
                ((TimeController) this.instance).setSkipNotReadyJobs(value);
                return this;
            }

            public Builder clearSkipNotReadyJobs() {
                copyOnWrite();
                ((TimeController) this.instance).clearSkipNotReadyJobs();
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
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    TimeController other = (TimeController) arg1;
                    this.skipNotReadyJobs_ = visitor.visitBoolean(hasSkipNotReadyJobs(), this.skipNotReadyJobs_, other.hasSkipNotReadyJobs(), other.skipNotReadyJobs_);
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
                                this.skipNotReadyJobs_ = input.readBool();
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

    public static final class QuotaController extends GeneratedMessageLite<QuotaController, Builder> implements QuotaControllerOrBuilder {
        public static final int ACTIVE_WINDOW_SIZE_MS_FIELD_NUMBER = 3;
        public static final int ALLOWED_TIME_PER_PERIOD_MS_FIELD_NUMBER = 1;
        private static final QuotaController DEFAULT_INSTANCE = new QuotaController();
        public static final int FREQUENT_WINDOW_SIZE_MS_FIELD_NUMBER = 5;
        public static final int IN_QUOTA_BUFFER_MS_FIELD_NUMBER = 2;
        public static final int MAX_EXECUTION_TIME_MS_FIELD_NUMBER = 7;
        public static final int MAX_JOB_COUNT_ACTIVE_FIELD_NUMBER = 8;
        public static final int MAX_JOB_COUNT_FREQUENT_FIELD_NUMBER = 10;
        public static final int MAX_JOB_COUNT_PER_RATE_LIMITING_WINDOW_FIELD_NUMBER = 12;
        public static final int MAX_JOB_COUNT_RARE_FIELD_NUMBER = 11;
        public static final int MAX_JOB_COUNT_WORKING_FIELD_NUMBER = 9;
        public static final int MAX_SESSION_COUNT_ACTIVE_FIELD_NUMBER = 13;
        public static final int MAX_SESSION_COUNT_FREQUENT_FIELD_NUMBER = 15;
        public static final int MAX_SESSION_COUNT_PER_RATE_LIMITING_WINDOW_FIELD_NUMBER = 17;
        public static final int MAX_SESSION_COUNT_RARE_FIELD_NUMBER = 16;
        public static final int MAX_SESSION_COUNT_WORKING_FIELD_NUMBER = 14;
        private static volatile Parser<QuotaController> PARSER = null;
        public static final int RARE_WINDOW_SIZE_MS_FIELD_NUMBER = 6;
        public static final int RATE_LIMITING_WINDOW_MS_FIELD_NUMBER = 19;
        public static final int TIMING_SESSION_COALESCING_DURATION_MS_FIELD_NUMBER = 18;
        public static final int WORKING_WINDOW_SIZE_MS_FIELD_NUMBER = 4;
        private long activeWindowSizeMs_ = 0;
        private long allowedTimePerPeriodMs_ = 0;
        private int bitField0_;
        private long frequentWindowSizeMs_ = 0;
        private long inQuotaBufferMs_ = 0;
        private long maxExecutionTimeMs_ = 0;
        private int maxJobCountActive_ = 0;
        private int maxJobCountFrequent_ = 0;
        private int maxJobCountPerRateLimitingWindow_ = 0;
        private int maxJobCountRare_ = 0;
        private int maxJobCountWorking_ = 0;
        private int maxSessionCountActive_ = 0;
        private int maxSessionCountFrequent_ = 0;
        private int maxSessionCountPerRateLimitingWindow_ = 0;
        private int maxSessionCountRare_ = 0;
        private int maxSessionCountWorking_ = 0;
        private long rareWindowSizeMs_ = 0;
        private int rateLimitingWindowMs_ = 0;
        private long timingSessionCoalescingDurationMs_ = 0;
        private long workingWindowSizeMs_ = 0;

        private QuotaController() {
        }

        @Override // com.android.server.job.ConstantsProto.QuotaControllerOrBuilder
        public boolean hasAllowedTimePerPeriodMs() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // com.android.server.job.ConstantsProto.QuotaControllerOrBuilder
        public long getAllowedTimePerPeriodMs() {
            return this.allowedTimePerPeriodMs_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setAllowedTimePerPeriodMs(long value) {
            this.bitField0_ |= 1;
            this.allowedTimePerPeriodMs_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearAllowedTimePerPeriodMs() {
            this.bitField0_ &= -2;
            this.allowedTimePerPeriodMs_ = 0;
        }

        @Override // com.android.server.job.ConstantsProto.QuotaControllerOrBuilder
        public boolean hasInQuotaBufferMs() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // com.android.server.job.ConstantsProto.QuotaControllerOrBuilder
        public long getInQuotaBufferMs() {
            return this.inQuotaBufferMs_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setInQuotaBufferMs(long value) {
            this.bitField0_ |= 2;
            this.inQuotaBufferMs_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearInQuotaBufferMs() {
            this.bitField0_ &= -3;
            this.inQuotaBufferMs_ = 0;
        }

        @Override // com.android.server.job.ConstantsProto.QuotaControllerOrBuilder
        public boolean hasActiveWindowSizeMs() {
            return (this.bitField0_ & 4) == 4;
        }

        @Override // com.android.server.job.ConstantsProto.QuotaControllerOrBuilder
        public long getActiveWindowSizeMs() {
            return this.activeWindowSizeMs_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setActiveWindowSizeMs(long value) {
            this.bitField0_ |= 4;
            this.activeWindowSizeMs_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearActiveWindowSizeMs() {
            this.bitField0_ &= -5;
            this.activeWindowSizeMs_ = 0;
        }

        @Override // com.android.server.job.ConstantsProto.QuotaControllerOrBuilder
        public boolean hasWorkingWindowSizeMs() {
            return (this.bitField0_ & 8) == 8;
        }

        @Override // com.android.server.job.ConstantsProto.QuotaControllerOrBuilder
        public long getWorkingWindowSizeMs() {
            return this.workingWindowSizeMs_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setWorkingWindowSizeMs(long value) {
            this.bitField0_ |= 8;
            this.workingWindowSizeMs_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearWorkingWindowSizeMs() {
            this.bitField0_ &= -9;
            this.workingWindowSizeMs_ = 0;
        }

        @Override // com.android.server.job.ConstantsProto.QuotaControllerOrBuilder
        public boolean hasFrequentWindowSizeMs() {
            return (this.bitField0_ & 16) == 16;
        }

        @Override // com.android.server.job.ConstantsProto.QuotaControllerOrBuilder
        public long getFrequentWindowSizeMs() {
            return this.frequentWindowSizeMs_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setFrequentWindowSizeMs(long value) {
            this.bitField0_ |= 16;
            this.frequentWindowSizeMs_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearFrequentWindowSizeMs() {
            this.bitField0_ &= -17;
            this.frequentWindowSizeMs_ = 0;
        }

        @Override // com.android.server.job.ConstantsProto.QuotaControllerOrBuilder
        public boolean hasRareWindowSizeMs() {
            return (this.bitField0_ & 32) == 32;
        }

        @Override // com.android.server.job.ConstantsProto.QuotaControllerOrBuilder
        public long getRareWindowSizeMs() {
            return this.rareWindowSizeMs_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setRareWindowSizeMs(long value) {
            this.bitField0_ |= 32;
            this.rareWindowSizeMs_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearRareWindowSizeMs() {
            this.bitField0_ &= -33;
            this.rareWindowSizeMs_ = 0;
        }

        @Override // com.android.server.job.ConstantsProto.QuotaControllerOrBuilder
        public boolean hasMaxExecutionTimeMs() {
            return (this.bitField0_ & 64) == 64;
        }

        @Override // com.android.server.job.ConstantsProto.QuotaControllerOrBuilder
        public long getMaxExecutionTimeMs() {
            return this.maxExecutionTimeMs_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setMaxExecutionTimeMs(long value) {
            this.bitField0_ |= 64;
            this.maxExecutionTimeMs_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearMaxExecutionTimeMs() {
            this.bitField0_ &= -65;
            this.maxExecutionTimeMs_ = 0;
        }

        @Override // com.android.server.job.ConstantsProto.QuotaControllerOrBuilder
        public boolean hasMaxJobCountActive() {
            return (this.bitField0_ & 128) == 128;
        }

        @Override // com.android.server.job.ConstantsProto.QuotaControllerOrBuilder
        public int getMaxJobCountActive() {
            return this.maxJobCountActive_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setMaxJobCountActive(int value) {
            this.bitField0_ |= 128;
            this.maxJobCountActive_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearMaxJobCountActive() {
            this.bitField0_ &= -129;
            this.maxJobCountActive_ = 0;
        }

        @Override // com.android.server.job.ConstantsProto.QuotaControllerOrBuilder
        public boolean hasMaxJobCountWorking() {
            return (this.bitField0_ & 256) == 256;
        }

        @Override // com.android.server.job.ConstantsProto.QuotaControllerOrBuilder
        public int getMaxJobCountWorking() {
            return this.maxJobCountWorking_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setMaxJobCountWorking(int value) {
            this.bitField0_ |= 256;
            this.maxJobCountWorking_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearMaxJobCountWorking() {
            this.bitField0_ &= -257;
            this.maxJobCountWorking_ = 0;
        }

        @Override // com.android.server.job.ConstantsProto.QuotaControllerOrBuilder
        public boolean hasMaxJobCountFrequent() {
            return (this.bitField0_ & 512) == 512;
        }

        @Override // com.android.server.job.ConstantsProto.QuotaControllerOrBuilder
        public int getMaxJobCountFrequent() {
            return this.maxJobCountFrequent_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setMaxJobCountFrequent(int value) {
            this.bitField0_ |= 512;
            this.maxJobCountFrequent_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearMaxJobCountFrequent() {
            this.bitField0_ &= -513;
            this.maxJobCountFrequent_ = 0;
        }

        @Override // com.android.server.job.ConstantsProto.QuotaControllerOrBuilder
        public boolean hasMaxJobCountRare() {
            return (this.bitField0_ & 1024) == 1024;
        }

        @Override // com.android.server.job.ConstantsProto.QuotaControllerOrBuilder
        public int getMaxJobCountRare() {
            return this.maxJobCountRare_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setMaxJobCountRare(int value) {
            this.bitField0_ |= 1024;
            this.maxJobCountRare_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearMaxJobCountRare() {
            this.bitField0_ &= -1025;
            this.maxJobCountRare_ = 0;
        }

        @Override // com.android.server.job.ConstantsProto.QuotaControllerOrBuilder
        public boolean hasRateLimitingWindowMs() {
            return (this.bitField0_ & 2048) == 2048;
        }

        @Override // com.android.server.job.ConstantsProto.QuotaControllerOrBuilder
        public int getRateLimitingWindowMs() {
            return this.rateLimitingWindowMs_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setRateLimitingWindowMs(int value) {
            this.bitField0_ |= 2048;
            this.rateLimitingWindowMs_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearRateLimitingWindowMs() {
            this.bitField0_ &= -2049;
            this.rateLimitingWindowMs_ = 0;
        }

        @Override // com.android.server.job.ConstantsProto.QuotaControllerOrBuilder
        public boolean hasMaxJobCountPerRateLimitingWindow() {
            return (this.bitField0_ & 4096) == 4096;
        }

        @Override // com.android.server.job.ConstantsProto.QuotaControllerOrBuilder
        public int getMaxJobCountPerRateLimitingWindow() {
            return this.maxJobCountPerRateLimitingWindow_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setMaxJobCountPerRateLimitingWindow(int value) {
            this.bitField0_ |= 4096;
            this.maxJobCountPerRateLimitingWindow_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearMaxJobCountPerRateLimitingWindow() {
            this.bitField0_ &= -4097;
            this.maxJobCountPerRateLimitingWindow_ = 0;
        }

        @Override // com.android.server.job.ConstantsProto.QuotaControllerOrBuilder
        public boolean hasMaxSessionCountActive() {
            return (this.bitField0_ & 8192) == 8192;
        }

        @Override // com.android.server.job.ConstantsProto.QuotaControllerOrBuilder
        public int getMaxSessionCountActive() {
            return this.maxSessionCountActive_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setMaxSessionCountActive(int value) {
            this.bitField0_ |= 8192;
            this.maxSessionCountActive_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearMaxSessionCountActive() {
            this.bitField0_ &= -8193;
            this.maxSessionCountActive_ = 0;
        }

        @Override // com.android.server.job.ConstantsProto.QuotaControllerOrBuilder
        public boolean hasMaxSessionCountWorking() {
            return (this.bitField0_ & 16384) == 16384;
        }

        @Override // com.android.server.job.ConstantsProto.QuotaControllerOrBuilder
        public int getMaxSessionCountWorking() {
            return this.maxSessionCountWorking_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setMaxSessionCountWorking(int value) {
            this.bitField0_ |= 16384;
            this.maxSessionCountWorking_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearMaxSessionCountWorking() {
            this.bitField0_ &= -16385;
            this.maxSessionCountWorking_ = 0;
        }

        @Override // com.android.server.job.ConstantsProto.QuotaControllerOrBuilder
        public boolean hasMaxSessionCountFrequent() {
            return (this.bitField0_ & 32768) == 32768;
        }

        @Override // com.android.server.job.ConstantsProto.QuotaControllerOrBuilder
        public int getMaxSessionCountFrequent() {
            return this.maxSessionCountFrequent_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setMaxSessionCountFrequent(int value) {
            this.bitField0_ |= 32768;
            this.maxSessionCountFrequent_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearMaxSessionCountFrequent() {
            this.bitField0_ &= -32769;
            this.maxSessionCountFrequent_ = 0;
        }

        @Override // com.android.server.job.ConstantsProto.QuotaControllerOrBuilder
        public boolean hasMaxSessionCountRare() {
            return (this.bitField0_ & 65536) == 65536;
        }

        @Override // com.android.server.job.ConstantsProto.QuotaControllerOrBuilder
        public int getMaxSessionCountRare() {
            return this.maxSessionCountRare_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setMaxSessionCountRare(int value) {
            this.bitField0_ |= 65536;
            this.maxSessionCountRare_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearMaxSessionCountRare() {
            this.bitField0_ &= -65537;
            this.maxSessionCountRare_ = 0;
        }

        @Override // com.android.server.job.ConstantsProto.QuotaControllerOrBuilder
        public boolean hasMaxSessionCountPerRateLimitingWindow() {
            return (this.bitField0_ & 131072) == 131072;
        }

        @Override // com.android.server.job.ConstantsProto.QuotaControllerOrBuilder
        public int getMaxSessionCountPerRateLimitingWindow() {
            return this.maxSessionCountPerRateLimitingWindow_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setMaxSessionCountPerRateLimitingWindow(int value) {
            this.bitField0_ |= 131072;
            this.maxSessionCountPerRateLimitingWindow_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearMaxSessionCountPerRateLimitingWindow() {
            this.bitField0_ &= -131073;
            this.maxSessionCountPerRateLimitingWindow_ = 0;
        }

        @Override // com.android.server.job.ConstantsProto.QuotaControllerOrBuilder
        public boolean hasTimingSessionCoalescingDurationMs() {
            return (this.bitField0_ & 262144) == 262144;
        }

        @Override // com.android.server.job.ConstantsProto.QuotaControllerOrBuilder
        public long getTimingSessionCoalescingDurationMs() {
            return this.timingSessionCoalescingDurationMs_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setTimingSessionCoalescingDurationMs(long value) {
            this.bitField0_ |= 262144;
            this.timingSessionCoalescingDurationMs_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearTimingSessionCoalescingDurationMs() {
            this.bitField0_ &= -262145;
            this.timingSessionCoalescingDurationMs_ = 0;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                output.writeInt64(1, this.allowedTimePerPeriodMs_);
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeInt64(2, this.inQuotaBufferMs_);
            }
            if ((this.bitField0_ & 4) == 4) {
                output.writeInt64(3, this.activeWindowSizeMs_);
            }
            if ((this.bitField0_ & 8) == 8) {
                output.writeInt64(4, this.workingWindowSizeMs_);
            }
            if ((this.bitField0_ & 16) == 16) {
                output.writeInt64(5, this.frequentWindowSizeMs_);
            }
            if ((this.bitField0_ & 32) == 32) {
                output.writeInt64(6, this.rareWindowSizeMs_);
            }
            if ((this.bitField0_ & 64) == 64) {
                output.writeInt64(7, this.maxExecutionTimeMs_);
            }
            if ((this.bitField0_ & 128) == 128) {
                output.writeInt32(8, this.maxJobCountActive_);
            }
            if ((this.bitField0_ & 256) == 256) {
                output.writeInt32(9, this.maxJobCountWorking_);
            }
            if ((this.bitField0_ & 512) == 512) {
                output.writeInt32(10, this.maxJobCountFrequent_);
            }
            if ((this.bitField0_ & 1024) == 1024) {
                output.writeInt32(11, this.maxJobCountRare_);
            }
            if ((this.bitField0_ & 4096) == 4096) {
                output.writeInt32(12, this.maxJobCountPerRateLimitingWindow_);
            }
            if ((this.bitField0_ & 8192) == 8192) {
                output.writeInt32(13, this.maxSessionCountActive_);
            }
            if ((this.bitField0_ & 16384) == 16384) {
                output.writeInt32(14, this.maxSessionCountWorking_);
            }
            if ((this.bitField0_ & 32768) == 32768) {
                output.writeInt32(15, this.maxSessionCountFrequent_);
            }
            if ((this.bitField0_ & 65536) == 65536) {
                output.writeInt32(16, this.maxSessionCountRare_);
            }
            if ((this.bitField0_ & 131072) == 131072) {
                output.writeInt32(17, this.maxSessionCountPerRateLimitingWindow_);
            }
            if ((this.bitField0_ & 262144) == 262144) {
                output.writeInt64(18, this.timingSessionCoalescingDurationMs_);
            }
            if ((this.bitField0_ & 2048) == 2048) {
                output.writeInt32(19, this.rateLimitingWindowMs_);
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
                size2 = 0 + CodedOutputStream.computeInt64Size(1, this.allowedTimePerPeriodMs_);
            }
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeInt64Size(2, this.inQuotaBufferMs_);
            }
            if ((this.bitField0_ & 4) == 4) {
                size2 += CodedOutputStream.computeInt64Size(3, this.activeWindowSizeMs_);
            }
            if ((this.bitField0_ & 8) == 8) {
                size2 += CodedOutputStream.computeInt64Size(4, this.workingWindowSizeMs_);
            }
            if ((this.bitField0_ & 16) == 16) {
                size2 += CodedOutputStream.computeInt64Size(5, this.frequentWindowSizeMs_);
            }
            if ((this.bitField0_ & 32) == 32) {
                size2 += CodedOutputStream.computeInt64Size(6, this.rareWindowSizeMs_);
            }
            if ((this.bitField0_ & 64) == 64) {
                size2 += CodedOutputStream.computeInt64Size(7, this.maxExecutionTimeMs_);
            }
            if ((this.bitField0_ & 128) == 128) {
                size2 += CodedOutputStream.computeInt32Size(8, this.maxJobCountActive_);
            }
            if ((this.bitField0_ & 256) == 256) {
                size2 += CodedOutputStream.computeInt32Size(9, this.maxJobCountWorking_);
            }
            if ((this.bitField0_ & 512) == 512) {
                size2 += CodedOutputStream.computeInt32Size(10, this.maxJobCountFrequent_);
            }
            if ((this.bitField0_ & 1024) == 1024) {
                size2 += CodedOutputStream.computeInt32Size(11, this.maxJobCountRare_);
            }
            if ((this.bitField0_ & 4096) == 4096) {
                size2 += CodedOutputStream.computeInt32Size(12, this.maxJobCountPerRateLimitingWindow_);
            }
            if ((this.bitField0_ & 8192) == 8192) {
                size2 += CodedOutputStream.computeInt32Size(13, this.maxSessionCountActive_);
            }
            if ((this.bitField0_ & 16384) == 16384) {
                size2 += CodedOutputStream.computeInt32Size(14, this.maxSessionCountWorking_);
            }
            if ((this.bitField0_ & 32768) == 32768) {
                size2 += CodedOutputStream.computeInt32Size(15, this.maxSessionCountFrequent_);
            }
            if ((this.bitField0_ & 65536) == 65536) {
                size2 += CodedOutputStream.computeInt32Size(16, this.maxSessionCountRare_);
            }
            if ((this.bitField0_ & 131072) == 131072) {
                size2 += CodedOutputStream.computeInt32Size(17, this.maxSessionCountPerRateLimitingWindow_);
            }
            if ((this.bitField0_ & 262144) == 262144) {
                size2 += CodedOutputStream.computeInt64Size(18, this.timingSessionCoalescingDurationMs_);
            }
            if ((this.bitField0_ & 2048) == 2048) {
                size2 += CodedOutputStream.computeInt32Size(19, this.rateLimitingWindowMs_);
            }
            int size3 = size2 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
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

            @Override // com.android.server.job.ConstantsProto.QuotaControllerOrBuilder
            public boolean hasAllowedTimePerPeriodMs() {
                return ((QuotaController) this.instance).hasAllowedTimePerPeriodMs();
            }

            @Override // com.android.server.job.ConstantsProto.QuotaControllerOrBuilder
            public long getAllowedTimePerPeriodMs() {
                return ((QuotaController) this.instance).getAllowedTimePerPeriodMs();
            }

            public Builder setAllowedTimePerPeriodMs(long value) {
                copyOnWrite();
                ((QuotaController) this.instance).setAllowedTimePerPeriodMs(value);
                return this;
            }

            public Builder clearAllowedTimePerPeriodMs() {
                copyOnWrite();
                ((QuotaController) this.instance).clearAllowedTimePerPeriodMs();
                return this;
            }

            @Override // com.android.server.job.ConstantsProto.QuotaControllerOrBuilder
            public boolean hasInQuotaBufferMs() {
                return ((QuotaController) this.instance).hasInQuotaBufferMs();
            }

            @Override // com.android.server.job.ConstantsProto.QuotaControllerOrBuilder
            public long getInQuotaBufferMs() {
                return ((QuotaController) this.instance).getInQuotaBufferMs();
            }

            public Builder setInQuotaBufferMs(long value) {
                copyOnWrite();
                ((QuotaController) this.instance).setInQuotaBufferMs(value);
                return this;
            }

            public Builder clearInQuotaBufferMs() {
                copyOnWrite();
                ((QuotaController) this.instance).clearInQuotaBufferMs();
                return this;
            }

            @Override // com.android.server.job.ConstantsProto.QuotaControllerOrBuilder
            public boolean hasActiveWindowSizeMs() {
                return ((QuotaController) this.instance).hasActiveWindowSizeMs();
            }

            @Override // com.android.server.job.ConstantsProto.QuotaControllerOrBuilder
            public long getActiveWindowSizeMs() {
                return ((QuotaController) this.instance).getActiveWindowSizeMs();
            }

            public Builder setActiveWindowSizeMs(long value) {
                copyOnWrite();
                ((QuotaController) this.instance).setActiveWindowSizeMs(value);
                return this;
            }

            public Builder clearActiveWindowSizeMs() {
                copyOnWrite();
                ((QuotaController) this.instance).clearActiveWindowSizeMs();
                return this;
            }

            @Override // com.android.server.job.ConstantsProto.QuotaControllerOrBuilder
            public boolean hasWorkingWindowSizeMs() {
                return ((QuotaController) this.instance).hasWorkingWindowSizeMs();
            }

            @Override // com.android.server.job.ConstantsProto.QuotaControllerOrBuilder
            public long getWorkingWindowSizeMs() {
                return ((QuotaController) this.instance).getWorkingWindowSizeMs();
            }

            public Builder setWorkingWindowSizeMs(long value) {
                copyOnWrite();
                ((QuotaController) this.instance).setWorkingWindowSizeMs(value);
                return this;
            }

            public Builder clearWorkingWindowSizeMs() {
                copyOnWrite();
                ((QuotaController) this.instance).clearWorkingWindowSizeMs();
                return this;
            }

            @Override // com.android.server.job.ConstantsProto.QuotaControllerOrBuilder
            public boolean hasFrequentWindowSizeMs() {
                return ((QuotaController) this.instance).hasFrequentWindowSizeMs();
            }

            @Override // com.android.server.job.ConstantsProto.QuotaControllerOrBuilder
            public long getFrequentWindowSizeMs() {
                return ((QuotaController) this.instance).getFrequentWindowSizeMs();
            }

            public Builder setFrequentWindowSizeMs(long value) {
                copyOnWrite();
                ((QuotaController) this.instance).setFrequentWindowSizeMs(value);
                return this;
            }

            public Builder clearFrequentWindowSizeMs() {
                copyOnWrite();
                ((QuotaController) this.instance).clearFrequentWindowSizeMs();
                return this;
            }

            @Override // com.android.server.job.ConstantsProto.QuotaControllerOrBuilder
            public boolean hasRareWindowSizeMs() {
                return ((QuotaController) this.instance).hasRareWindowSizeMs();
            }

            @Override // com.android.server.job.ConstantsProto.QuotaControllerOrBuilder
            public long getRareWindowSizeMs() {
                return ((QuotaController) this.instance).getRareWindowSizeMs();
            }

            public Builder setRareWindowSizeMs(long value) {
                copyOnWrite();
                ((QuotaController) this.instance).setRareWindowSizeMs(value);
                return this;
            }

            public Builder clearRareWindowSizeMs() {
                copyOnWrite();
                ((QuotaController) this.instance).clearRareWindowSizeMs();
                return this;
            }

            @Override // com.android.server.job.ConstantsProto.QuotaControllerOrBuilder
            public boolean hasMaxExecutionTimeMs() {
                return ((QuotaController) this.instance).hasMaxExecutionTimeMs();
            }

            @Override // com.android.server.job.ConstantsProto.QuotaControllerOrBuilder
            public long getMaxExecutionTimeMs() {
                return ((QuotaController) this.instance).getMaxExecutionTimeMs();
            }

            public Builder setMaxExecutionTimeMs(long value) {
                copyOnWrite();
                ((QuotaController) this.instance).setMaxExecutionTimeMs(value);
                return this;
            }

            public Builder clearMaxExecutionTimeMs() {
                copyOnWrite();
                ((QuotaController) this.instance).clearMaxExecutionTimeMs();
                return this;
            }

            @Override // com.android.server.job.ConstantsProto.QuotaControllerOrBuilder
            public boolean hasMaxJobCountActive() {
                return ((QuotaController) this.instance).hasMaxJobCountActive();
            }

            @Override // com.android.server.job.ConstantsProto.QuotaControllerOrBuilder
            public int getMaxJobCountActive() {
                return ((QuotaController) this.instance).getMaxJobCountActive();
            }

            public Builder setMaxJobCountActive(int value) {
                copyOnWrite();
                ((QuotaController) this.instance).setMaxJobCountActive(value);
                return this;
            }

            public Builder clearMaxJobCountActive() {
                copyOnWrite();
                ((QuotaController) this.instance).clearMaxJobCountActive();
                return this;
            }

            @Override // com.android.server.job.ConstantsProto.QuotaControllerOrBuilder
            public boolean hasMaxJobCountWorking() {
                return ((QuotaController) this.instance).hasMaxJobCountWorking();
            }

            @Override // com.android.server.job.ConstantsProto.QuotaControllerOrBuilder
            public int getMaxJobCountWorking() {
                return ((QuotaController) this.instance).getMaxJobCountWorking();
            }

            public Builder setMaxJobCountWorking(int value) {
                copyOnWrite();
                ((QuotaController) this.instance).setMaxJobCountWorking(value);
                return this;
            }

            public Builder clearMaxJobCountWorking() {
                copyOnWrite();
                ((QuotaController) this.instance).clearMaxJobCountWorking();
                return this;
            }

            @Override // com.android.server.job.ConstantsProto.QuotaControllerOrBuilder
            public boolean hasMaxJobCountFrequent() {
                return ((QuotaController) this.instance).hasMaxJobCountFrequent();
            }

            @Override // com.android.server.job.ConstantsProto.QuotaControllerOrBuilder
            public int getMaxJobCountFrequent() {
                return ((QuotaController) this.instance).getMaxJobCountFrequent();
            }

            public Builder setMaxJobCountFrequent(int value) {
                copyOnWrite();
                ((QuotaController) this.instance).setMaxJobCountFrequent(value);
                return this;
            }

            public Builder clearMaxJobCountFrequent() {
                copyOnWrite();
                ((QuotaController) this.instance).clearMaxJobCountFrequent();
                return this;
            }

            @Override // com.android.server.job.ConstantsProto.QuotaControllerOrBuilder
            public boolean hasMaxJobCountRare() {
                return ((QuotaController) this.instance).hasMaxJobCountRare();
            }

            @Override // com.android.server.job.ConstantsProto.QuotaControllerOrBuilder
            public int getMaxJobCountRare() {
                return ((QuotaController) this.instance).getMaxJobCountRare();
            }

            public Builder setMaxJobCountRare(int value) {
                copyOnWrite();
                ((QuotaController) this.instance).setMaxJobCountRare(value);
                return this;
            }

            public Builder clearMaxJobCountRare() {
                copyOnWrite();
                ((QuotaController) this.instance).clearMaxJobCountRare();
                return this;
            }

            @Override // com.android.server.job.ConstantsProto.QuotaControllerOrBuilder
            public boolean hasRateLimitingWindowMs() {
                return ((QuotaController) this.instance).hasRateLimitingWindowMs();
            }

            @Override // com.android.server.job.ConstantsProto.QuotaControllerOrBuilder
            public int getRateLimitingWindowMs() {
                return ((QuotaController) this.instance).getRateLimitingWindowMs();
            }

            public Builder setRateLimitingWindowMs(int value) {
                copyOnWrite();
                ((QuotaController) this.instance).setRateLimitingWindowMs(value);
                return this;
            }

            public Builder clearRateLimitingWindowMs() {
                copyOnWrite();
                ((QuotaController) this.instance).clearRateLimitingWindowMs();
                return this;
            }

            @Override // com.android.server.job.ConstantsProto.QuotaControllerOrBuilder
            public boolean hasMaxJobCountPerRateLimitingWindow() {
                return ((QuotaController) this.instance).hasMaxJobCountPerRateLimitingWindow();
            }

            @Override // com.android.server.job.ConstantsProto.QuotaControllerOrBuilder
            public int getMaxJobCountPerRateLimitingWindow() {
                return ((QuotaController) this.instance).getMaxJobCountPerRateLimitingWindow();
            }

            public Builder setMaxJobCountPerRateLimitingWindow(int value) {
                copyOnWrite();
                ((QuotaController) this.instance).setMaxJobCountPerRateLimitingWindow(value);
                return this;
            }

            public Builder clearMaxJobCountPerRateLimitingWindow() {
                copyOnWrite();
                ((QuotaController) this.instance).clearMaxJobCountPerRateLimitingWindow();
                return this;
            }

            @Override // com.android.server.job.ConstantsProto.QuotaControllerOrBuilder
            public boolean hasMaxSessionCountActive() {
                return ((QuotaController) this.instance).hasMaxSessionCountActive();
            }

            @Override // com.android.server.job.ConstantsProto.QuotaControllerOrBuilder
            public int getMaxSessionCountActive() {
                return ((QuotaController) this.instance).getMaxSessionCountActive();
            }

            public Builder setMaxSessionCountActive(int value) {
                copyOnWrite();
                ((QuotaController) this.instance).setMaxSessionCountActive(value);
                return this;
            }

            public Builder clearMaxSessionCountActive() {
                copyOnWrite();
                ((QuotaController) this.instance).clearMaxSessionCountActive();
                return this;
            }

            @Override // com.android.server.job.ConstantsProto.QuotaControllerOrBuilder
            public boolean hasMaxSessionCountWorking() {
                return ((QuotaController) this.instance).hasMaxSessionCountWorking();
            }

            @Override // com.android.server.job.ConstantsProto.QuotaControllerOrBuilder
            public int getMaxSessionCountWorking() {
                return ((QuotaController) this.instance).getMaxSessionCountWorking();
            }

            public Builder setMaxSessionCountWorking(int value) {
                copyOnWrite();
                ((QuotaController) this.instance).setMaxSessionCountWorking(value);
                return this;
            }

            public Builder clearMaxSessionCountWorking() {
                copyOnWrite();
                ((QuotaController) this.instance).clearMaxSessionCountWorking();
                return this;
            }

            @Override // com.android.server.job.ConstantsProto.QuotaControllerOrBuilder
            public boolean hasMaxSessionCountFrequent() {
                return ((QuotaController) this.instance).hasMaxSessionCountFrequent();
            }

            @Override // com.android.server.job.ConstantsProto.QuotaControllerOrBuilder
            public int getMaxSessionCountFrequent() {
                return ((QuotaController) this.instance).getMaxSessionCountFrequent();
            }

            public Builder setMaxSessionCountFrequent(int value) {
                copyOnWrite();
                ((QuotaController) this.instance).setMaxSessionCountFrequent(value);
                return this;
            }

            public Builder clearMaxSessionCountFrequent() {
                copyOnWrite();
                ((QuotaController) this.instance).clearMaxSessionCountFrequent();
                return this;
            }

            @Override // com.android.server.job.ConstantsProto.QuotaControllerOrBuilder
            public boolean hasMaxSessionCountRare() {
                return ((QuotaController) this.instance).hasMaxSessionCountRare();
            }

            @Override // com.android.server.job.ConstantsProto.QuotaControllerOrBuilder
            public int getMaxSessionCountRare() {
                return ((QuotaController) this.instance).getMaxSessionCountRare();
            }

            public Builder setMaxSessionCountRare(int value) {
                copyOnWrite();
                ((QuotaController) this.instance).setMaxSessionCountRare(value);
                return this;
            }

            public Builder clearMaxSessionCountRare() {
                copyOnWrite();
                ((QuotaController) this.instance).clearMaxSessionCountRare();
                return this;
            }

            @Override // com.android.server.job.ConstantsProto.QuotaControllerOrBuilder
            public boolean hasMaxSessionCountPerRateLimitingWindow() {
                return ((QuotaController) this.instance).hasMaxSessionCountPerRateLimitingWindow();
            }

            @Override // com.android.server.job.ConstantsProto.QuotaControllerOrBuilder
            public int getMaxSessionCountPerRateLimitingWindow() {
                return ((QuotaController) this.instance).getMaxSessionCountPerRateLimitingWindow();
            }

            public Builder setMaxSessionCountPerRateLimitingWindow(int value) {
                copyOnWrite();
                ((QuotaController) this.instance).setMaxSessionCountPerRateLimitingWindow(value);
                return this;
            }

            public Builder clearMaxSessionCountPerRateLimitingWindow() {
                copyOnWrite();
                ((QuotaController) this.instance).clearMaxSessionCountPerRateLimitingWindow();
                return this;
            }

            @Override // com.android.server.job.ConstantsProto.QuotaControllerOrBuilder
            public boolean hasTimingSessionCoalescingDurationMs() {
                return ((QuotaController) this.instance).hasTimingSessionCoalescingDurationMs();
            }

            @Override // com.android.server.job.ConstantsProto.QuotaControllerOrBuilder
            public long getTimingSessionCoalescingDurationMs() {
                return ((QuotaController) this.instance).getTimingSessionCoalescingDurationMs();
            }

            public Builder setTimingSessionCoalescingDurationMs(long value) {
                copyOnWrite();
                ((QuotaController) this.instance).setTimingSessionCoalescingDurationMs(value);
                return this;
            }

            public Builder clearTimingSessionCoalescingDurationMs() {
                copyOnWrite();
                ((QuotaController) this.instance).clearTimingSessionCoalescingDurationMs();
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
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    QuotaController other = (QuotaController) arg1;
                    this.allowedTimePerPeriodMs_ = visitor.visitLong(hasAllowedTimePerPeriodMs(), this.allowedTimePerPeriodMs_, other.hasAllowedTimePerPeriodMs(), other.allowedTimePerPeriodMs_);
                    this.inQuotaBufferMs_ = visitor.visitLong(hasInQuotaBufferMs(), this.inQuotaBufferMs_, other.hasInQuotaBufferMs(), other.inQuotaBufferMs_);
                    this.activeWindowSizeMs_ = visitor.visitLong(hasActiveWindowSizeMs(), this.activeWindowSizeMs_, other.hasActiveWindowSizeMs(), other.activeWindowSizeMs_);
                    this.workingWindowSizeMs_ = visitor.visitLong(hasWorkingWindowSizeMs(), this.workingWindowSizeMs_, other.hasWorkingWindowSizeMs(), other.workingWindowSizeMs_);
                    this.frequentWindowSizeMs_ = visitor.visitLong(hasFrequentWindowSizeMs(), this.frequentWindowSizeMs_, other.hasFrequentWindowSizeMs(), other.frequentWindowSizeMs_);
                    this.rareWindowSizeMs_ = visitor.visitLong(hasRareWindowSizeMs(), this.rareWindowSizeMs_, other.hasRareWindowSizeMs(), other.rareWindowSizeMs_);
                    this.maxExecutionTimeMs_ = visitor.visitLong(hasMaxExecutionTimeMs(), this.maxExecutionTimeMs_, other.hasMaxExecutionTimeMs(), other.maxExecutionTimeMs_);
                    this.maxJobCountActive_ = visitor.visitInt(hasMaxJobCountActive(), this.maxJobCountActive_, other.hasMaxJobCountActive(), other.maxJobCountActive_);
                    this.maxJobCountWorking_ = visitor.visitInt(hasMaxJobCountWorking(), this.maxJobCountWorking_, other.hasMaxJobCountWorking(), other.maxJobCountWorking_);
                    this.maxJobCountFrequent_ = visitor.visitInt(hasMaxJobCountFrequent(), this.maxJobCountFrequent_, other.hasMaxJobCountFrequent(), other.maxJobCountFrequent_);
                    this.maxJobCountRare_ = visitor.visitInt(hasMaxJobCountRare(), this.maxJobCountRare_, other.hasMaxJobCountRare(), other.maxJobCountRare_);
                    this.rateLimitingWindowMs_ = visitor.visitInt(hasRateLimitingWindowMs(), this.rateLimitingWindowMs_, other.hasRateLimitingWindowMs(), other.rateLimitingWindowMs_);
                    this.maxJobCountPerRateLimitingWindow_ = visitor.visitInt(hasMaxJobCountPerRateLimitingWindow(), this.maxJobCountPerRateLimitingWindow_, other.hasMaxJobCountPerRateLimitingWindow(), other.maxJobCountPerRateLimitingWindow_);
                    this.maxSessionCountActive_ = visitor.visitInt(hasMaxSessionCountActive(), this.maxSessionCountActive_, other.hasMaxSessionCountActive(), other.maxSessionCountActive_);
                    this.maxSessionCountWorking_ = visitor.visitInt(hasMaxSessionCountWorking(), this.maxSessionCountWorking_, other.hasMaxSessionCountWorking(), other.maxSessionCountWorking_);
                    this.maxSessionCountFrequent_ = visitor.visitInt(hasMaxSessionCountFrequent(), this.maxSessionCountFrequent_, other.hasMaxSessionCountFrequent(), other.maxSessionCountFrequent_);
                    this.maxSessionCountRare_ = visitor.visitInt(hasMaxSessionCountRare(), this.maxSessionCountRare_, other.hasMaxSessionCountRare(), other.maxSessionCountRare_);
                    this.maxSessionCountPerRateLimitingWindow_ = visitor.visitInt(hasMaxSessionCountPerRateLimitingWindow(), this.maxSessionCountPerRateLimitingWindow_, other.hasMaxSessionCountPerRateLimitingWindow(), other.maxSessionCountPerRateLimitingWindow_);
                    this.timingSessionCoalescingDurationMs_ = visitor.visitLong(hasTimingSessionCoalescingDurationMs(), this.timingSessionCoalescingDurationMs_, other.hasTimingSessionCoalescingDurationMs(), other.timingSessionCoalescingDurationMs_);
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
                                    this.bitField0_ |= 1;
                                    this.allowedTimePerPeriodMs_ = input.readInt64();
                                    break;
                                case 16:
                                    this.bitField0_ |= 2;
                                    this.inQuotaBufferMs_ = input.readInt64();
                                    break;
                                case 24:
                                    this.bitField0_ |= 4;
                                    this.activeWindowSizeMs_ = input.readInt64();
                                    break;
                                case 32:
                                    this.bitField0_ |= 8;
                                    this.workingWindowSizeMs_ = input.readInt64();
                                    break;
                                case 40:
                                    this.bitField0_ |= 16;
                                    this.frequentWindowSizeMs_ = input.readInt64();
                                    break;
                                case 48:
                                    this.bitField0_ |= 32;
                                    this.rareWindowSizeMs_ = input.readInt64();
                                    break;
                                case 56:
                                    this.bitField0_ |= 64;
                                    this.maxExecutionTimeMs_ = input.readInt64();
                                    break;
                                case 64:
                                    this.bitField0_ |= 128;
                                    this.maxJobCountActive_ = input.readInt32();
                                    break;
                                case 72:
                                    this.bitField0_ |= 256;
                                    this.maxJobCountWorking_ = input.readInt32();
                                    break;
                                case 80:
                                    this.bitField0_ |= 512;
                                    this.maxJobCountFrequent_ = input.readInt32();
                                    break;
                                case 88:
                                    this.bitField0_ |= 1024;
                                    this.maxJobCountRare_ = input.readInt32();
                                    break;
                                case 96:
                                    this.bitField0_ |= 4096;
                                    this.maxJobCountPerRateLimitingWindow_ = input.readInt32();
                                    break;
                                case 104:
                                    this.bitField0_ |= 8192;
                                    this.maxSessionCountActive_ = input.readInt32();
                                    break;
                                case 112:
                                    this.bitField0_ |= 16384;
                                    this.maxSessionCountWorking_ = input.readInt32();
                                    break;
                                case 120:
                                    this.bitField0_ |= 32768;
                                    this.maxSessionCountFrequent_ = input.readInt32();
                                    break;
                                case 128:
                                    this.bitField0_ |= 65536;
                                    this.maxSessionCountRare_ = input.readInt32();
                                    break;
                                case 136:
                                    this.bitField0_ |= 131072;
                                    this.maxSessionCountPerRateLimitingWindow_ = input.readInt32();
                                    break;
                                case 144:
                                    this.bitField0_ |= 262144;
                                    this.timingSessionCoalescingDurationMs_ = input.readInt64();
                                    break;
                                case 152:
                                    this.bitField0_ |= 2048;
                                    this.rateLimitingWindowMs_ = input.readInt32();
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

    @Override // com.android.server.job.ConstantsProtoOrBuilder
    public boolean hasMinIdleCount() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // com.android.server.job.ConstantsProtoOrBuilder
    public int getMinIdleCount() {
        return this.minIdleCount_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setMinIdleCount(int value) {
        this.bitField0_ |= 1;
        this.minIdleCount_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearMinIdleCount() {
        this.bitField0_ &= -2;
        this.minIdleCount_ = 0;
    }

    @Override // com.android.server.job.ConstantsProtoOrBuilder
    public boolean hasMinChargingCount() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // com.android.server.job.ConstantsProtoOrBuilder
    public int getMinChargingCount() {
        return this.minChargingCount_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setMinChargingCount(int value) {
        this.bitField0_ |= 2;
        this.minChargingCount_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearMinChargingCount() {
        this.bitField0_ &= -3;
        this.minChargingCount_ = 0;
    }

    @Override // com.android.server.job.ConstantsProtoOrBuilder
    public boolean hasMinBatteryNotLowCount() {
        return (this.bitField0_ & 4) == 4;
    }

    @Override // com.android.server.job.ConstantsProtoOrBuilder
    public int getMinBatteryNotLowCount() {
        return this.minBatteryNotLowCount_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setMinBatteryNotLowCount(int value) {
        this.bitField0_ |= 4;
        this.minBatteryNotLowCount_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearMinBatteryNotLowCount() {
        this.bitField0_ &= -5;
        this.minBatteryNotLowCount_ = 0;
    }

    @Override // com.android.server.job.ConstantsProtoOrBuilder
    public boolean hasMinStorageNotLowCount() {
        return (this.bitField0_ & 8) == 8;
    }

    @Override // com.android.server.job.ConstantsProtoOrBuilder
    public int getMinStorageNotLowCount() {
        return this.minStorageNotLowCount_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setMinStorageNotLowCount(int value) {
        this.bitField0_ |= 8;
        this.minStorageNotLowCount_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearMinStorageNotLowCount() {
        this.bitField0_ &= -9;
        this.minStorageNotLowCount_ = 0;
    }

    @Override // com.android.server.job.ConstantsProtoOrBuilder
    public boolean hasMinConnectivityCount() {
        return (this.bitField0_ & 16) == 16;
    }

    @Override // com.android.server.job.ConstantsProtoOrBuilder
    public int getMinConnectivityCount() {
        return this.minConnectivityCount_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setMinConnectivityCount(int value) {
        this.bitField0_ |= 16;
        this.minConnectivityCount_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearMinConnectivityCount() {
        this.bitField0_ &= -17;
        this.minConnectivityCount_ = 0;
    }

    @Override // com.android.server.job.ConstantsProtoOrBuilder
    public boolean hasMinContentCount() {
        return (this.bitField0_ & 32) == 32;
    }

    @Override // com.android.server.job.ConstantsProtoOrBuilder
    public int getMinContentCount() {
        return this.minContentCount_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setMinContentCount(int value) {
        this.bitField0_ |= 32;
        this.minContentCount_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearMinContentCount() {
        this.bitField0_ &= -33;
        this.minContentCount_ = 0;
    }

    @Override // com.android.server.job.ConstantsProtoOrBuilder
    public boolean hasMinReadyJobsCount() {
        return (this.bitField0_ & 64) == 64;
    }

    @Override // com.android.server.job.ConstantsProtoOrBuilder
    public int getMinReadyJobsCount() {
        return this.minReadyJobsCount_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setMinReadyJobsCount(int value) {
        this.bitField0_ |= 64;
        this.minReadyJobsCount_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearMinReadyJobsCount() {
        this.bitField0_ &= -65;
        this.minReadyJobsCount_ = 0;
    }

    @Override // com.android.server.job.ConstantsProtoOrBuilder
    public boolean hasHeavyUseFactor() {
        return (this.bitField0_ & 128) == 128;
    }

    @Override // com.android.server.job.ConstantsProtoOrBuilder
    public double getHeavyUseFactor() {
        return this.heavyUseFactor_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setHeavyUseFactor(double value) {
        this.bitField0_ |= 128;
        this.heavyUseFactor_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearHeavyUseFactor() {
        this.bitField0_ &= -129;
        this.heavyUseFactor_ = 0.0d;
    }

    @Override // com.android.server.job.ConstantsProtoOrBuilder
    public boolean hasModerateUseFactor() {
        return (this.bitField0_ & 256) == 256;
    }

    @Override // com.android.server.job.ConstantsProtoOrBuilder
    public double getModerateUseFactor() {
        return this.moderateUseFactor_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setModerateUseFactor(double value) {
        this.bitField0_ |= 256;
        this.moderateUseFactor_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearModerateUseFactor() {
        this.bitField0_ &= -257;
        this.moderateUseFactor_ = 0.0d;
    }

    @Override // com.android.server.job.ConstantsProtoOrBuilder
    public boolean hasFgJobCount() {
        return (this.bitField0_ & 512) == 512;
    }

    @Override // com.android.server.job.ConstantsProtoOrBuilder
    public int getFgJobCount() {
        return this.fgJobCount_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setFgJobCount(int value) {
        this.bitField0_ |= 512;
        this.fgJobCount_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearFgJobCount() {
        this.bitField0_ &= -513;
        this.fgJobCount_ = 0;
    }

    @Override // com.android.server.job.ConstantsProtoOrBuilder
    public boolean hasBgNormalJobCount() {
        return (this.bitField0_ & 1024) == 1024;
    }

    @Override // com.android.server.job.ConstantsProtoOrBuilder
    public int getBgNormalJobCount() {
        return this.bgNormalJobCount_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setBgNormalJobCount(int value) {
        this.bitField0_ |= 1024;
        this.bgNormalJobCount_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearBgNormalJobCount() {
        this.bitField0_ &= -1025;
        this.bgNormalJobCount_ = 0;
    }

    @Override // com.android.server.job.ConstantsProtoOrBuilder
    public boolean hasBgModerateJobCount() {
        return (this.bitField0_ & 2048) == 2048;
    }

    @Override // com.android.server.job.ConstantsProtoOrBuilder
    public int getBgModerateJobCount() {
        return this.bgModerateJobCount_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setBgModerateJobCount(int value) {
        this.bitField0_ |= 2048;
        this.bgModerateJobCount_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearBgModerateJobCount() {
        this.bitField0_ &= -2049;
        this.bgModerateJobCount_ = 0;
    }

    @Override // com.android.server.job.ConstantsProtoOrBuilder
    public boolean hasBgLowJobCount() {
        return (this.bitField0_ & 4096) == 4096;
    }

    @Override // com.android.server.job.ConstantsProtoOrBuilder
    public int getBgLowJobCount() {
        return this.bgLowJobCount_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setBgLowJobCount(int value) {
        this.bitField0_ |= 4096;
        this.bgLowJobCount_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearBgLowJobCount() {
        this.bitField0_ &= -4097;
        this.bgLowJobCount_ = 0;
    }

    @Override // com.android.server.job.ConstantsProtoOrBuilder
    public boolean hasBgCriticalJobCount() {
        return (this.bitField0_ & 8192) == 8192;
    }

    @Override // com.android.server.job.ConstantsProtoOrBuilder
    public int getBgCriticalJobCount() {
        return this.bgCriticalJobCount_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setBgCriticalJobCount(int value) {
        this.bitField0_ |= 8192;
        this.bgCriticalJobCount_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearBgCriticalJobCount() {
        this.bitField0_ &= -8193;
        this.bgCriticalJobCount_ = 0;
    }

    @Override // com.android.server.job.ConstantsProtoOrBuilder
    public boolean hasMaxStandardRescheduleCount() {
        return (this.bitField0_ & 16384) == 16384;
    }

    @Override // com.android.server.job.ConstantsProtoOrBuilder
    public int getMaxStandardRescheduleCount() {
        return this.maxStandardRescheduleCount_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setMaxStandardRescheduleCount(int value) {
        this.bitField0_ |= 16384;
        this.maxStandardRescheduleCount_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearMaxStandardRescheduleCount() {
        this.bitField0_ &= -16385;
        this.maxStandardRescheduleCount_ = 0;
    }

    @Override // com.android.server.job.ConstantsProtoOrBuilder
    public boolean hasMaxWorkRescheduleCount() {
        return (this.bitField0_ & 32768) == 32768;
    }

    @Override // com.android.server.job.ConstantsProtoOrBuilder
    public int getMaxWorkRescheduleCount() {
        return this.maxWorkRescheduleCount_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setMaxWorkRescheduleCount(int value) {
        this.bitField0_ |= 32768;
        this.maxWorkRescheduleCount_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearMaxWorkRescheduleCount() {
        this.bitField0_ &= -32769;
        this.maxWorkRescheduleCount_ = 0;
    }

    @Override // com.android.server.job.ConstantsProtoOrBuilder
    public boolean hasMinLinearBackoffTimeMs() {
        return (this.bitField0_ & 65536) == 65536;
    }

    @Override // com.android.server.job.ConstantsProtoOrBuilder
    public long getMinLinearBackoffTimeMs() {
        return this.minLinearBackoffTimeMs_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setMinLinearBackoffTimeMs(long value) {
        this.bitField0_ |= 65536;
        this.minLinearBackoffTimeMs_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearMinLinearBackoffTimeMs() {
        this.bitField0_ &= -65537;
        this.minLinearBackoffTimeMs_ = 0;
    }

    @Override // com.android.server.job.ConstantsProtoOrBuilder
    public boolean hasMinExpBackoffTimeMs() {
        return (this.bitField0_ & 131072) == 131072;
    }

    @Override // com.android.server.job.ConstantsProtoOrBuilder
    public long getMinExpBackoffTimeMs() {
        return this.minExpBackoffTimeMs_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setMinExpBackoffTimeMs(long value) {
        this.bitField0_ |= 131072;
        this.minExpBackoffTimeMs_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearMinExpBackoffTimeMs() {
        this.bitField0_ &= -131073;
        this.minExpBackoffTimeMs_ = 0;
    }

    @Override // com.android.server.job.ConstantsProtoOrBuilder
    public boolean hasStandbyHeartbeatTimeMs() {
        return (this.bitField0_ & 262144) == 262144;
    }

    @Override // com.android.server.job.ConstantsProtoOrBuilder
    public long getStandbyHeartbeatTimeMs() {
        return this.standbyHeartbeatTimeMs_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setStandbyHeartbeatTimeMs(long value) {
        this.bitField0_ |= 262144;
        this.standbyHeartbeatTimeMs_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearStandbyHeartbeatTimeMs() {
        this.bitField0_ &= -262145;
        this.standbyHeartbeatTimeMs_ = 0;
    }

    @Override // com.android.server.job.ConstantsProtoOrBuilder
    public List<Integer> getStandbyBeatsList() {
        return this.standbyBeats_;
    }

    @Override // com.android.server.job.ConstantsProtoOrBuilder
    public int getStandbyBeatsCount() {
        return this.standbyBeats_.size();
    }

    @Override // com.android.server.job.ConstantsProtoOrBuilder
    public int getStandbyBeats(int index) {
        return this.standbyBeats_.getInt(index);
    }

    private void ensureStandbyBeatsIsMutable() {
        if (!this.standbyBeats_.isModifiable()) {
            this.standbyBeats_ = GeneratedMessageLite.mutableCopy(this.standbyBeats_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setStandbyBeats(int index, int value) {
        ensureStandbyBeatsIsMutable();
        this.standbyBeats_.setInt(index, value);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addStandbyBeats(int value) {
        ensureStandbyBeatsIsMutable();
        this.standbyBeats_.addInt(value);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllStandbyBeats(Iterable<? extends Integer> values) {
        ensureStandbyBeatsIsMutable();
        AbstractMessageLite.addAll(values, this.standbyBeats_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearStandbyBeats() {
        this.standbyBeats_ = emptyIntList();
    }

    @Override // com.android.server.job.ConstantsProtoOrBuilder
    public boolean hasConnCongestionDelayFrac() {
        return (this.bitField0_ & 524288) == 524288;
    }

    @Override // com.android.server.job.ConstantsProtoOrBuilder
    public double getConnCongestionDelayFrac() {
        return this.connCongestionDelayFrac_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setConnCongestionDelayFrac(double value) {
        this.bitField0_ |= 524288;
        this.connCongestionDelayFrac_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearConnCongestionDelayFrac() {
        this.bitField0_ &= -524289;
        this.connCongestionDelayFrac_ = 0.0d;
    }

    @Override // com.android.server.job.ConstantsProtoOrBuilder
    public boolean hasConnPrefetchRelaxFrac() {
        return (this.bitField0_ & 1048576) == 1048576;
    }

    @Override // com.android.server.job.ConstantsProtoOrBuilder
    public double getConnPrefetchRelaxFrac() {
        return this.connPrefetchRelaxFrac_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setConnPrefetchRelaxFrac(double value) {
        this.bitField0_ |= 1048576;
        this.connPrefetchRelaxFrac_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearConnPrefetchRelaxFrac() {
        this.bitField0_ &= -1048577;
        this.connPrefetchRelaxFrac_ = 0.0d;
    }

    @Override // com.android.server.job.ConstantsProtoOrBuilder
    public boolean hasUseHeartbeats() {
        return (this.bitField0_ & 2097152) == 2097152;
    }

    @Override // com.android.server.job.ConstantsProtoOrBuilder
    public boolean getUseHeartbeats() {
        return this.useHeartbeats_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setUseHeartbeats(boolean value) {
        this.bitField0_ |= 2097152;
        this.useHeartbeats_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearUseHeartbeats() {
        this.bitField0_ &= -2097153;
        this.useHeartbeats_ = false;
    }

    @Override // com.android.server.job.ConstantsProtoOrBuilder
    public boolean hasTimeController() {
        return (this.bitField0_ & 4194304) == 4194304;
    }

    @Override // com.android.server.job.ConstantsProtoOrBuilder
    public TimeController getTimeController() {
        TimeController timeController = this.timeController_;
        return timeController == null ? TimeController.getDefaultInstance() : timeController;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setTimeController(TimeController value) {
        if (value != null) {
            this.timeController_ = value;
            this.bitField0_ |= 4194304;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setTimeController(TimeController.Builder builderForValue) {
        this.timeController_ = (TimeController) builderForValue.build();
        this.bitField0_ |= 4194304;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeTimeController(TimeController value) {
        TimeController timeController = this.timeController_;
        if (timeController == null || timeController == TimeController.getDefaultInstance()) {
            this.timeController_ = value;
        } else {
            this.timeController_ = (TimeController) ((TimeController.Builder) TimeController.newBuilder(this.timeController_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 4194304;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearTimeController() {
        this.timeController_ = null;
        this.bitField0_ &= -4194305;
    }

    @Override // com.android.server.job.ConstantsProtoOrBuilder
    public boolean hasQuotaController() {
        return (this.bitField0_ & 8388608) == 8388608;
    }

    @Override // com.android.server.job.ConstantsProtoOrBuilder
    public QuotaController getQuotaController() {
        QuotaController quotaController = this.quotaController_;
        return quotaController == null ? QuotaController.getDefaultInstance() : quotaController;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setQuotaController(QuotaController value) {
        if (value != null) {
            this.quotaController_ = value;
            this.bitField0_ |= 8388608;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setQuotaController(QuotaController.Builder builderForValue) {
        this.quotaController_ = (QuotaController) builderForValue.build();
        this.bitField0_ |= 8388608;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeQuotaController(QuotaController value) {
        QuotaController quotaController = this.quotaController_;
        if (quotaController == null || quotaController == QuotaController.getDefaultInstance()) {
            this.quotaController_ = value;
        } else {
            this.quotaController_ = (QuotaController) ((QuotaController.Builder) QuotaController.newBuilder(this.quotaController_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 8388608;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearQuotaController() {
        this.quotaController_ = null;
        this.bitField0_ &= -8388609;
    }

    @Override // com.android.server.job.ConstantsProtoOrBuilder
    public boolean hasMaxJobCountsScreenOn() {
        return (this.bitField0_ & 16777216) == 16777216;
    }

    @Override // com.android.server.job.ConstantsProtoOrBuilder
    public MaxJobCountsPerMemoryTrimLevelProto getMaxJobCountsScreenOn() {
        MaxJobCountsPerMemoryTrimLevelProto maxJobCountsPerMemoryTrimLevelProto = this.maxJobCountsScreenOn_;
        return maxJobCountsPerMemoryTrimLevelProto == null ? MaxJobCountsPerMemoryTrimLevelProto.getDefaultInstance() : maxJobCountsPerMemoryTrimLevelProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setMaxJobCountsScreenOn(MaxJobCountsPerMemoryTrimLevelProto value) {
        if (value != null) {
            this.maxJobCountsScreenOn_ = value;
            this.bitField0_ |= 16777216;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setMaxJobCountsScreenOn(MaxJobCountsPerMemoryTrimLevelProto.Builder builderForValue) {
        this.maxJobCountsScreenOn_ = (MaxJobCountsPerMemoryTrimLevelProto) builderForValue.build();
        this.bitField0_ |= 16777216;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeMaxJobCountsScreenOn(MaxJobCountsPerMemoryTrimLevelProto value) {
        MaxJobCountsPerMemoryTrimLevelProto maxJobCountsPerMemoryTrimLevelProto = this.maxJobCountsScreenOn_;
        if (maxJobCountsPerMemoryTrimLevelProto == null || maxJobCountsPerMemoryTrimLevelProto == MaxJobCountsPerMemoryTrimLevelProto.getDefaultInstance()) {
            this.maxJobCountsScreenOn_ = value;
        } else {
            this.maxJobCountsScreenOn_ = (MaxJobCountsPerMemoryTrimLevelProto) ((MaxJobCountsPerMemoryTrimLevelProto.Builder) MaxJobCountsPerMemoryTrimLevelProto.newBuilder(this.maxJobCountsScreenOn_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 16777216;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearMaxJobCountsScreenOn() {
        this.maxJobCountsScreenOn_ = null;
        this.bitField0_ &= -16777217;
    }

    @Override // com.android.server.job.ConstantsProtoOrBuilder
    public boolean hasMaxJobCountsScreenOff() {
        return (this.bitField0_ & 33554432) == 33554432;
    }

    @Override // com.android.server.job.ConstantsProtoOrBuilder
    public MaxJobCountsPerMemoryTrimLevelProto getMaxJobCountsScreenOff() {
        MaxJobCountsPerMemoryTrimLevelProto maxJobCountsPerMemoryTrimLevelProto = this.maxJobCountsScreenOff_;
        return maxJobCountsPerMemoryTrimLevelProto == null ? MaxJobCountsPerMemoryTrimLevelProto.getDefaultInstance() : maxJobCountsPerMemoryTrimLevelProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setMaxJobCountsScreenOff(MaxJobCountsPerMemoryTrimLevelProto value) {
        if (value != null) {
            this.maxJobCountsScreenOff_ = value;
            this.bitField0_ |= 33554432;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setMaxJobCountsScreenOff(MaxJobCountsPerMemoryTrimLevelProto.Builder builderForValue) {
        this.maxJobCountsScreenOff_ = (MaxJobCountsPerMemoryTrimLevelProto) builderForValue.build();
        this.bitField0_ |= 33554432;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeMaxJobCountsScreenOff(MaxJobCountsPerMemoryTrimLevelProto value) {
        MaxJobCountsPerMemoryTrimLevelProto maxJobCountsPerMemoryTrimLevelProto = this.maxJobCountsScreenOff_;
        if (maxJobCountsPerMemoryTrimLevelProto == null || maxJobCountsPerMemoryTrimLevelProto == MaxJobCountsPerMemoryTrimLevelProto.getDefaultInstance()) {
            this.maxJobCountsScreenOff_ = value;
        } else {
            this.maxJobCountsScreenOff_ = (MaxJobCountsPerMemoryTrimLevelProto) ((MaxJobCountsPerMemoryTrimLevelProto.Builder) MaxJobCountsPerMemoryTrimLevelProto.newBuilder(this.maxJobCountsScreenOff_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 33554432;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearMaxJobCountsScreenOff() {
        this.maxJobCountsScreenOff_ = null;
        this.bitField0_ &= -33554433;
    }

    @Override // com.android.server.job.ConstantsProtoOrBuilder
    public boolean hasScreenOffJobConcurrencyIncreaseDelayMs() {
        return (this.bitField0_ & 67108864) == 67108864;
    }

    @Override // com.android.server.job.ConstantsProtoOrBuilder
    public int getScreenOffJobConcurrencyIncreaseDelayMs() {
        return this.screenOffJobConcurrencyIncreaseDelayMs_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setScreenOffJobConcurrencyIncreaseDelayMs(int value) {
        this.bitField0_ |= 67108864;
        this.screenOffJobConcurrencyIncreaseDelayMs_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearScreenOffJobConcurrencyIncreaseDelayMs() {
        this.bitField0_ &= -67108865;
        this.screenOffJobConcurrencyIncreaseDelayMs_ = 0;
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeInt32(1, this.minIdleCount_);
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeInt32(2, this.minChargingCount_);
        }
        if ((this.bitField0_ & 4) == 4) {
            output.writeInt32(3, this.minBatteryNotLowCount_);
        }
        if ((this.bitField0_ & 8) == 8) {
            output.writeInt32(4, this.minStorageNotLowCount_);
        }
        if ((this.bitField0_ & 16) == 16) {
            output.writeInt32(5, this.minConnectivityCount_);
        }
        if ((this.bitField0_ & 32) == 32) {
            output.writeInt32(6, this.minContentCount_);
        }
        if ((this.bitField0_ & 64) == 64) {
            output.writeInt32(7, this.minReadyJobsCount_);
        }
        if ((this.bitField0_ & 128) == 128) {
            output.writeDouble(8, this.heavyUseFactor_);
        }
        if ((this.bitField0_ & 256) == 256) {
            output.writeDouble(9, this.moderateUseFactor_);
        }
        if ((this.bitField0_ & 512) == 512) {
            output.writeInt32(10, this.fgJobCount_);
        }
        if ((this.bitField0_ & 1024) == 1024) {
            output.writeInt32(11, this.bgNormalJobCount_);
        }
        if ((this.bitField0_ & 2048) == 2048) {
            output.writeInt32(12, this.bgModerateJobCount_);
        }
        if ((this.bitField0_ & 4096) == 4096) {
            output.writeInt32(13, this.bgLowJobCount_);
        }
        if ((this.bitField0_ & 8192) == 8192) {
            output.writeInt32(14, this.bgCriticalJobCount_);
        }
        if ((this.bitField0_ & 16384) == 16384) {
            output.writeInt32(15, this.maxStandardRescheduleCount_);
        }
        if ((this.bitField0_ & 32768) == 32768) {
            output.writeInt32(16, this.maxWorkRescheduleCount_);
        }
        if ((this.bitField0_ & 65536) == 65536) {
            output.writeInt64(17, this.minLinearBackoffTimeMs_);
        }
        if ((this.bitField0_ & 131072) == 131072) {
            output.writeInt64(18, this.minExpBackoffTimeMs_);
        }
        if ((this.bitField0_ & 262144) == 262144) {
            output.writeInt64(19, this.standbyHeartbeatTimeMs_);
        }
        for (int i = 0; i < this.standbyBeats_.size(); i++) {
            output.writeInt32(20, this.standbyBeats_.getInt(i));
        }
        if ((this.bitField0_ & 524288) == 524288) {
            output.writeDouble(21, this.connCongestionDelayFrac_);
        }
        if ((this.bitField0_ & 1048576) == 1048576) {
            output.writeDouble(22, this.connPrefetchRelaxFrac_);
        }
        if ((this.bitField0_ & 2097152) == 2097152) {
            output.writeBool(23, this.useHeartbeats_);
        }
        if ((this.bitField0_ & 8388608) == 8388608) {
            output.writeMessage(24, getQuotaController());
        }
        if ((this.bitField0_ & 4194304) == 4194304) {
            output.writeMessage(25, getTimeController());
        }
        if ((this.bitField0_ & 16777216) == 16777216) {
            output.writeMessage(26, getMaxJobCountsScreenOn());
        }
        if ((this.bitField0_ & 33554432) == 33554432) {
            output.writeMessage(27, getMaxJobCountsScreenOff());
        }
        if ((this.bitField0_ & 67108864) == 67108864) {
            output.writeInt32(28, this.screenOffJobConcurrencyIncreaseDelayMs_);
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
            size2 = 0 + CodedOutputStream.computeInt32Size(1, this.minIdleCount_);
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeInt32Size(2, this.minChargingCount_);
        }
        if ((this.bitField0_ & 4) == 4) {
            size2 += CodedOutputStream.computeInt32Size(3, this.minBatteryNotLowCount_);
        }
        if ((this.bitField0_ & 8) == 8) {
            size2 += CodedOutputStream.computeInt32Size(4, this.minStorageNotLowCount_);
        }
        if ((this.bitField0_ & 16) == 16) {
            size2 += CodedOutputStream.computeInt32Size(5, this.minConnectivityCount_);
        }
        if ((this.bitField0_ & 32) == 32) {
            size2 += CodedOutputStream.computeInt32Size(6, this.minContentCount_);
        }
        if ((this.bitField0_ & 64) == 64) {
            size2 += CodedOutputStream.computeInt32Size(7, this.minReadyJobsCount_);
        }
        if ((this.bitField0_ & 128) == 128) {
            size2 += CodedOutputStream.computeDoubleSize(8, this.heavyUseFactor_);
        }
        if ((this.bitField0_ & 256) == 256) {
            size2 += CodedOutputStream.computeDoubleSize(9, this.moderateUseFactor_);
        }
        if ((this.bitField0_ & 512) == 512) {
            size2 += CodedOutputStream.computeInt32Size(10, this.fgJobCount_);
        }
        if ((this.bitField0_ & 1024) == 1024) {
            size2 += CodedOutputStream.computeInt32Size(11, this.bgNormalJobCount_);
        }
        if ((this.bitField0_ & 2048) == 2048) {
            size2 += CodedOutputStream.computeInt32Size(12, this.bgModerateJobCount_);
        }
        if ((this.bitField0_ & 4096) == 4096) {
            size2 += CodedOutputStream.computeInt32Size(13, this.bgLowJobCount_);
        }
        if ((this.bitField0_ & 8192) == 8192) {
            size2 += CodedOutputStream.computeInt32Size(14, this.bgCriticalJobCount_);
        }
        if ((this.bitField0_ & 16384) == 16384) {
            size2 += CodedOutputStream.computeInt32Size(15, this.maxStandardRescheduleCount_);
        }
        if ((this.bitField0_ & 32768) == 32768) {
            size2 += CodedOutputStream.computeInt32Size(16, this.maxWorkRescheduleCount_);
        }
        if ((this.bitField0_ & 65536) == 65536) {
            size2 += CodedOutputStream.computeInt64Size(17, this.minLinearBackoffTimeMs_);
        }
        if ((this.bitField0_ & 131072) == 131072) {
            size2 += CodedOutputStream.computeInt64Size(18, this.minExpBackoffTimeMs_);
        }
        if ((this.bitField0_ & 262144) == 262144) {
            size2 += CodedOutputStream.computeInt64Size(19, this.standbyHeartbeatTimeMs_);
        }
        int dataSize = 0;
        for (int i = 0; i < this.standbyBeats_.size(); i++) {
            dataSize += CodedOutputStream.computeInt32SizeNoTag(this.standbyBeats_.getInt(i));
        }
        int size3 = size2 + dataSize + (getStandbyBeatsList().size() * 2);
        if ((this.bitField0_ & 524288) == 524288) {
            size3 += CodedOutputStream.computeDoubleSize(21, this.connCongestionDelayFrac_);
        }
        if ((this.bitField0_ & 1048576) == 1048576) {
            size3 += CodedOutputStream.computeDoubleSize(22, this.connPrefetchRelaxFrac_);
        }
        if ((this.bitField0_ & 2097152) == 2097152) {
            size3 += CodedOutputStream.computeBoolSize(23, this.useHeartbeats_);
        }
        if ((this.bitField0_ & 8388608) == 8388608) {
            size3 += CodedOutputStream.computeMessageSize(24, getQuotaController());
        }
        if ((this.bitField0_ & 4194304) == 4194304) {
            size3 += CodedOutputStream.computeMessageSize(25, getTimeController());
        }
        if ((this.bitField0_ & 16777216) == 16777216) {
            size3 += CodedOutputStream.computeMessageSize(26, getMaxJobCountsScreenOn());
        }
        if ((this.bitField0_ & 33554432) == 33554432) {
            size3 += CodedOutputStream.computeMessageSize(27, getMaxJobCountsScreenOff());
        }
        if ((this.bitField0_ & 67108864) == 67108864) {
            size3 += CodedOutputStream.computeInt32Size(28, this.screenOffJobConcurrencyIncreaseDelayMs_);
        }
        int size4 = size3 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size4;
        return size4;
    }

    public static ConstantsProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (ConstantsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static ConstantsProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (ConstantsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static ConstantsProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (ConstantsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static ConstantsProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (ConstantsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static ConstantsProto parseFrom(InputStream input) throws IOException {
        return (ConstantsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static ConstantsProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (ConstantsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static ConstantsProto parseDelimitedFrom(InputStream input) throws IOException {
        return (ConstantsProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static ConstantsProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (ConstantsProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static ConstantsProto parseFrom(CodedInputStream input) throws IOException {
        return (ConstantsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static ConstantsProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (ConstantsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(ConstantsProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<ConstantsProto, Builder> implements ConstantsProtoOrBuilder {
        private Builder() {
            super(ConstantsProto.DEFAULT_INSTANCE);
        }

        @Override // com.android.server.job.ConstantsProtoOrBuilder
        public boolean hasMinIdleCount() {
            return ((ConstantsProto) this.instance).hasMinIdleCount();
        }

        @Override // com.android.server.job.ConstantsProtoOrBuilder
        public int getMinIdleCount() {
            return ((ConstantsProto) this.instance).getMinIdleCount();
        }

        public Builder setMinIdleCount(int value) {
            copyOnWrite();
            ((ConstantsProto) this.instance).setMinIdleCount(value);
            return this;
        }

        public Builder clearMinIdleCount() {
            copyOnWrite();
            ((ConstantsProto) this.instance).clearMinIdleCount();
            return this;
        }

        @Override // com.android.server.job.ConstantsProtoOrBuilder
        public boolean hasMinChargingCount() {
            return ((ConstantsProto) this.instance).hasMinChargingCount();
        }

        @Override // com.android.server.job.ConstantsProtoOrBuilder
        public int getMinChargingCount() {
            return ((ConstantsProto) this.instance).getMinChargingCount();
        }

        public Builder setMinChargingCount(int value) {
            copyOnWrite();
            ((ConstantsProto) this.instance).setMinChargingCount(value);
            return this;
        }

        public Builder clearMinChargingCount() {
            copyOnWrite();
            ((ConstantsProto) this.instance).clearMinChargingCount();
            return this;
        }

        @Override // com.android.server.job.ConstantsProtoOrBuilder
        public boolean hasMinBatteryNotLowCount() {
            return ((ConstantsProto) this.instance).hasMinBatteryNotLowCount();
        }

        @Override // com.android.server.job.ConstantsProtoOrBuilder
        public int getMinBatteryNotLowCount() {
            return ((ConstantsProto) this.instance).getMinBatteryNotLowCount();
        }

        public Builder setMinBatteryNotLowCount(int value) {
            copyOnWrite();
            ((ConstantsProto) this.instance).setMinBatteryNotLowCount(value);
            return this;
        }

        public Builder clearMinBatteryNotLowCount() {
            copyOnWrite();
            ((ConstantsProto) this.instance).clearMinBatteryNotLowCount();
            return this;
        }

        @Override // com.android.server.job.ConstantsProtoOrBuilder
        public boolean hasMinStorageNotLowCount() {
            return ((ConstantsProto) this.instance).hasMinStorageNotLowCount();
        }

        @Override // com.android.server.job.ConstantsProtoOrBuilder
        public int getMinStorageNotLowCount() {
            return ((ConstantsProto) this.instance).getMinStorageNotLowCount();
        }

        public Builder setMinStorageNotLowCount(int value) {
            copyOnWrite();
            ((ConstantsProto) this.instance).setMinStorageNotLowCount(value);
            return this;
        }

        public Builder clearMinStorageNotLowCount() {
            copyOnWrite();
            ((ConstantsProto) this.instance).clearMinStorageNotLowCount();
            return this;
        }

        @Override // com.android.server.job.ConstantsProtoOrBuilder
        public boolean hasMinConnectivityCount() {
            return ((ConstantsProto) this.instance).hasMinConnectivityCount();
        }

        @Override // com.android.server.job.ConstantsProtoOrBuilder
        public int getMinConnectivityCount() {
            return ((ConstantsProto) this.instance).getMinConnectivityCount();
        }

        public Builder setMinConnectivityCount(int value) {
            copyOnWrite();
            ((ConstantsProto) this.instance).setMinConnectivityCount(value);
            return this;
        }

        public Builder clearMinConnectivityCount() {
            copyOnWrite();
            ((ConstantsProto) this.instance).clearMinConnectivityCount();
            return this;
        }

        @Override // com.android.server.job.ConstantsProtoOrBuilder
        public boolean hasMinContentCount() {
            return ((ConstantsProto) this.instance).hasMinContentCount();
        }

        @Override // com.android.server.job.ConstantsProtoOrBuilder
        public int getMinContentCount() {
            return ((ConstantsProto) this.instance).getMinContentCount();
        }

        public Builder setMinContentCount(int value) {
            copyOnWrite();
            ((ConstantsProto) this.instance).setMinContentCount(value);
            return this;
        }

        public Builder clearMinContentCount() {
            copyOnWrite();
            ((ConstantsProto) this.instance).clearMinContentCount();
            return this;
        }

        @Override // com.android.server.job.ConstantsProtoOrBuilder
        public boolean hasMinReadyJobsCount() {
            return ((ConstantsProto) this.instance).hasMinReadyJobsCount();
        }

        @Override // com.android.server.job.ConstantsProtoOrBuilder
        public int getMinReadyJobsCount() {
            return ((ConstantsProto) this.instance).getMinReadyJobsCount();
        }

        public Builder setMinReadyJobsCount(int value) {
            copyOnWrite();
            ((ConstantsProto) this.instance).setMinReadyJobsCount(value);
            return this;
        }

        public Builder clearMinReadyJobsCount() {
            copyOnWrite();
            ((ConstantsProto) this.instance).clearMinReadyJobsCount();
            return this;
        }

        @Override // com.android.server.job.ConstantsProtoOrBuilder
        public boolean hasHeavyUseFactor() {
            return ((ConstantsProto) this.instance).hasHeavyUseFactor();
        }

        @Override // com.android.server.job.ConstantsProtoOrBuilder
        public double getHeavyUseFactor() {
            return ((ConstantsProto) this.instance).getHeavyUseFactor();
        }

        public Builder setHeavyUseFactor(double value) {
            copyOnWrite();
            ((ConstantsProto) this.instance).setHeavyUseFactor(value);
            return this;
        }

        public Builder clearHeavyUseFactor() {
            copyOnWrite();
            ((ConstantsProto) this.instance).clearHeavyUseFactor();
            return this;
        }

        @Override // com.android.server.job.ConstantsProtoOrBuilder
        public boolean hasModerateUseFactor() {
            return ((ConstantsProto) this.instance).hasModerateUseFactor();
        }

        @Override // com.android.server.job.ConstantsProtoOrBuilder
        public double getModerateUseFactor() {
            return ((ConstantsProto) this.instance).getModerateUseFactor();
        }

        public Builder setModerateUseFactor(double value) {
            copyOnWrite();
            ((ConstantsProto) this.instance).setModerateUseFactor(value);
            return this;
        }

        public Builder clearModerateUseFactor() {
            copyOnWrite();
            ((ConstantsProto) this.instance).clearModerateUseFactor();
            return this;
        }

        @Override // com.android.server.job.ConstantsProtoOrBuilder
        public boolean hasFgJobCount() {
            return ((ConstantsProto) this.instance).hasFgJobCount();
        }

        @Override // com.android.server.job.ConstantsProtoOrBuilder
        public int getFgJobCount() {
            return ((ConstantsProto) this.instance).getFgJobCount();
        }

        public Builder setFgJobCount(int value) {
            copyOnWrite();
            ((ConstantsProto) this.instance).setFgJobCount(value);
            return this;
        }

        public Builder clearFgJobCount() {
            copyOnWrite();
            ((ConstantsProto) this.instance).clearFgJobCount();
            return this;
        }

        @Override // com.android.server.job.ConstantsProtoOrBuilder
        public boolean hasBgNormalJobCount() {
            return ((ConstantsProto) this.instance).hasBgNormalJobCount();
        }

        @Override // com.android.server.job.ConstantsProtoOrBuilder
        public int getBgNormalJobCount() {
            return ((ConstantsProto) this.instance).getBgNormalJobCount();
        }

        public Builder setBgNormalJobCount(int value) {
            copyOnWrite();
            ((ConstantsProto) this.instance).setBgNormalJobCount(value);
            return this;
        }

        public Builder clearBgNormalJobCount() {
            copyOnWrite();
            ((ConstantsProto) this.instance).clearBgNormalJobCount();
            return this;
        }

        @Override // com.android.server.job.ConstantsProtoOrBuilder
        public boolean hasBgModerateJobCount() {
            return ((ConstantsProto) this.instance).hasBgModerateJobCount();
        }

        @Override // com.android.server.job.ConstantsProtoOrBuilder
        public int getBgModerateJobCount() {
            return ((ConstantsProto) this.instance).getBgModerateJobCount();
        }

        public Builder setBgModerateJobCount(int value) {
            copyOnWrite();
            ((ConstantsProto) this.instance).setBgModerateJobCount(value);
            return this;
        }

        public Builder clearBgModerateJobCount() {
            copyOnWrite();
            ((ConstantsProto) this.instance).clearBgModerateJobCount();
            return this;
        }

        @Override // com.android.server.job.ConstantsProtoOrBuilder
        public boolean hasBgLowJobCount() {
            return ((ConstantsProto) this.instance).hasBgLowJobCount();
        }

        @Override // com.android.server.job.ConstantsProtoOrBuilder
        public int getBgLowJobCount() {
            return ((ConstantsProto) this.instance).getBgLowJobCount();
        }

        public Builder setBgLowJobCount(int value) {
            copyOnWrite();
            ((ConstantsProto) this.instance).setBgLowJobCount(value);
            return this;
        }

        public Builder clearBgLowJobCount() {
            copyOnWrite();
            ((ConstantsProto) this.instance).clearBgLowJobCount();
            return this;
        }

        @Override // com.android.server.job.ConstantsProtoOrBuilder
        public boolean hasBgCriticalJobCount() {
            return ((ConstantsProto) this.instance).hasBgCriticalJobCount();
        }

        @Override // com.android.server.job.ConstantsProtoOrBuilder
        public int getBgCriticalJobCount() {
            return ((ConstantsProto) this.instance).getBgCriticalJobCount();
        }

        public Builder setBgCriticalJobCount(int value) {
            copyOnWrite();
            ((ConstantsProto) this.instance).setBgCriticalJobCount(value);
            return this;
        }

        public Builder clearBgCriticalJobCount() {
            copyOnWrite();
            ((ConstantsProto) this.instance).clearBgCriticalJobCount();
            return this;
        }

        @Override // com.android.server.job.ConstantsProtoOrBuilder
        public boolean hasMaxStandardRescheduleCount() {
            return ((ConstantsProto) this.instance).hasMaxStandardRescheduleCount();
        }

        @Override // com.android.server.job.ConstantsProtoOrBuilder
        public int getMaxStandardRescheduleCount() {
            return ((ConstantsProto) this.instance).getMaxStandardRescheduleCount();
        }

        public Builder setMaxStandardRescheduleCount(int value) {
            copyOnWrite();
            ((ConstantsProto) this.instance).setMaxStandardRescheduleCount(value);
            return this;
        }

        public Builder clearMaxStandardRescheduleCount() {
            copyOnWrite();
            ((ConstantsProto) this.instance).clearMaxStandardRescheduleCount();
            return this;
        }

        @Override // com.android.server.job.ConstantsProtoOrBuilder
        public boolean hasMaxWorkRescheduleCount() {
            return ((ConstantsProto) this.instance).hasMaxWorkRescheduleCount();
        }

        @Override // com.android.server.job.ConstantsProtoOrBuilder
        public int getMaxWorkRescheduleCount() {
            return ((ConstantsProto) this.instance).getMaxWorkRescheduleCount();
        }

        public Builder setMaxWorkRescheduleCount(int value) {
            copyOnWrite();
            ((ConstantsProto) this.instance).setMaxWorkRescheduleCount(value);
            return this;
        }

        public Builder clearMaxWorkRescheduleCount() {
            copyOnWrite();
            ((ConstantsProto) this.instance).clearMaxWorkRescheduleCount();
            return this;
        }

        @Override // com.android.server.job.ConstantsProtoOrBuilder
        public boolean hasMinLinearBackoffTimeMs() {
            return ((ConstantsProto) this.instance).hasMinLinearBackoffTimeMs();
        }

        @Override // com.android.server.job.ConstantsProtoOrBuilder
        public long getMinLinearBackoffTimeMs() {
            return ((ConstantsProto) this.instance).getMinLinearBackoffTimeMs();
        }

        public Builder setMinLinearBackoffTimeMs(long value) {
            copyOnWrite();
            ((ConstantsProto) this.instance).setMinLinearBackoffTimeMs(value);
            return this;
        }

        public Builder clearMinLinearBackoffTimeMs() {
            copyOnWrite();
            ((ConstantsProto) this.instance).clearMinLinearBackoffTimeMs();
            return this;
        }

        @Override // com.android.server.job.ConstantsProtoOrBuilder
        public boolean hasMinExpBackoffTimeMs() {
            return ((ConstantsProto) this.instance).hasMinExpBackoffTimeMs();
        }

        @Override // com.android.server.job.ConstantsProtoOrBuilder
        public long getMinExpBackoffTimeMs() {
            return ((ConstantsProto) this.instance).getMinExpBackoffTimeMs();
        }

        public Builder setMinExpBackoffTimeMs(long value) {
            copyOnWrite();
            ((ConstantsProto) this.instance).setMinExpBackoffTimeMs(value);
            return this;
        }

        public Builder clearMinExpBackoffTimeMs() {
            copyOnWrite();
            ((ConstantsProto) this.instance).clearMinExpBackoffTimeMs();
            return this;
        }

        @Override // com.android.server.job.ConstantsProtoOrBuilder
        public boolean hasStandbyHeartbeatTimeMs() {
            return ((ConstantsProto) this.instance).hasStandbyHeartbeatTimeMs();
        }

        @Override // com.android.server.job.ConstantsProtoOrBuilder
        public long getStandbyHeartbeatTimeMs() {
            return ((ConstantsProto) this.instance).getStandbyHeartbeatTimeMs();
        }

        public Builder setStandbyHeartbeatTimeMs(long value) {
            copyOnWrite();
            ((ConstantsProto) this.instance).setStandbyHeartbeatTimeMs(value);
            return this;
        }

        public Builder clearStandbyHeartbeatTimeMs() {
            copyOnWrite();
            ((ConstantsProto) this.instance).clearStandbyHeartbeatTimeMs();
            return this;
        }

        @Override // com.android.server.job.ConstantsProtoOrBuilder
        public List<Integer> getStandbyBeatsList() {
            return Collections.unmodifiableList(((ConstantsProto) this.instance).getStandbyBeatsList());
        }

        @Override // com.android.server.job.ConstantsProtoOrBuilder
        public int getStandbyBeatsCount() {
            return ((ConstantsProto) this.instance).getStandbyBeatsCount();
        }

        @Override // com.android.server.job.ConstantsProtoOrBuilder
        public int getStandbyBeats(int index) {
            return ((ConstantsProto) this.instance).getStandbyBeats(index);
        }

        public Builder setStandbyBeats(int index, int value) {
            copyOnWrite();
            ((ConstantsProto) this.instance).setStandbyBeats(index, value);
            return this;
        }

        public Builder addStandbyBeats(int value) {
            copyOnWrite();
            ((ConstantsProto) this.instance).addStandbyBeats(value);
            return this;
        }

        public Builder addAllStandbyBeats(Iterable<? extends Integer> values) {
            copyOnWrite();
            ((ConstantsProto) this.instance).addAllStandbyBeats(values);
            return this;
        }

        public Builder clearStandbyBeats() {
            copyOnWrite();
            ((ConstantsProto) this.instance).clearStandbyBeats();
            return this;
        }

        @Override // com.android.server.job.ConstantsProtoOrBuilder
        public boolean hasConnCongestionDelayFrac() {
            return ((ConstantsProto) this.instance).hasConnCongestionDelayFrac();
        }

        @Override // com.android.server.job.ConstantsProtoOrBuilder
        public double getConnCongestionDelayFrac() {
            return ((ConstantsProto) this.instance).getConnCongestionDelayFrac();
        }

        public Builder setConnCongestionDelayFrac(double value) {
            copyOnWrite();
            ((ConstantsProto) this.instance).setConnCongestionDelayFrac(value);
            return this;
        }

        public Builder clearConnCongestionDelayFrac() {
            copyOnWrite();
            ((ConstantsProto) this.instance).clearConnCongestionDelayFrac();
            return this;
        }

        @Override // com.android.server.job.ConstantsProtoOrBuilder
        public boolean hasConnPrefetchRelaxFrac() {
            return ((ConstantsProto) this.instance).hasConnPrefetchRelaxFrac();
        }

        @Override // com.android.server.job.ConstantsProtoOrBuilder
        public double getConnPrefetchRelaxFrac() {
            return ((ConstantsProto) this.instance).getConnPrefetchRelaxFrac();
        }

        public Builder setConnPrefetchRelaxFrac(double value) {
            copyOnWrite();
            ((ConstantsProto) this.instance).setConnPrefetchRelaxFrac(value);
            return this;
        }

        public Builder clearConnPrefetchRelaxFrac() {
            copyOnWrite();
            ((ConstantsProto) this.instance).clearConnPrefetchRelaxFrac();
            return this;
        }

        @Override // com.android.server.job.ConstantsProtoOrBuilder
        public boolean hasUseHeartbeats() {
            return ((ConstantsProto) this.instance).hasUseHeartbeats();
        }

        @Override // com.android.server.job.ConstantsProtoOrBuilder
        public boolean getUseHeartbeats() {
            return ((ConstantsProto) this.instance).getUseHeartbeats();
        }

        public Builder setUseHeartbeats(boolean value) {
            copyOnWrite();
            ((ConstantsProto) this.instance).setUseHeartbeats(value);
            return this;
        }

        public Builder clearUseHeartbeats() {
            copyOnWrite();
            ((ConstantsProto) this.instance).clearUseHeartbeats();
            return this;
        }

        @Override // com.android.server.job.ConstantsProtoOrBuilder
        public boolean hasTimeController() {
            return ((ConstantsProto) this.instance).hasTimeController();
        }

        @Override // com.android.server.job.ConstantsProtoOrBuilder
        public TimeController getTimeController() {
            return ((ConstantsProto) this.instance).getTimeController();
        }

        public Builder setTimeController(TimeController value) {
            copyOnWrite();
            ((ConstantsProto) this.instance).setTimeController((ConstantsProto) value);
            return this;
        }

        public Builder setTimeController(TimeController.Builder builderForValue) {
            copyOnWrite();
            ((ConstantsProto) this.instance).setTimeController((ConstantsProto) builderForValue);
            return this;
        }

        public Builder mergeTimeController(TimeController value) {
            copyOnWrite();
            ((ConstantsProto) this.instance).mergeTimeController(value);
            return this;
        }

        public Builder clearTimeController() {
            copyOnWrite();
            ((ConstantsProto) this.instance).clearTimeController();
            return this;
        }

        @Override // com.android.server.job.ConstantsProtoOrBuilder
        public boolean hasQuotaController() {
            return ((ConstantsProto) this.instance).hasQuotaController();
        }

        @Override // com.android.server.job.ConstantsProtoOrBuilder
        public QuotaController getQuotaController() {
            return ((ConstantsProto) this.instance).getQuotaController();
        }

        public Builder setQuotaController(QuotaController value) {
            copyOnWrite();
            ((ConstantsProto) this.instance).setQuotaController((ConstantsProto) value);
            return this;
        }

        public Builder setQuotaController(QuotaController.Builder builderForValue) {
            copyOnWrite();
            ((ConstantsProto) this.instance).setQuotaController((ConstantsProto) builderForValue);
            return this;
        }

        public Builder mergeQuotaController(QuotaController value) {
            copyOnWrite();
            ((ConstantsProto) this.instance).mergeQuotaController(value);
            return this;
        }

        public Builder clearQuotaController() {
            copyOnWrite();
            ((ConstantsProto) this.instance).clearQuotaController();
            return this;
        }

        @Override // com.android.server.job.ConstantsProtoOrBuilder
        public boolean hasMaxJobCountsScreenOn() {
            return ((ConstantsProto) this.instance).hasMaxJobCountsScreenOn();
        }

        @Override // com.android.server.job.ConstantsProtoOrBuilder
        public MaxJobCountsPerMemoryTrimLevelProto getMaxJobCountsScreenOn() {
            return ((ConstantsProto) this.instance).getMaxJobCountsScreenOn();
        }

        public Builder setMaxJobCountsScreenOn(MaxJobCountsPerMemoryTrimLevelProto value) {
            copyOnWrite();
            ((ConstantsProto) this.instance).setMaxJobCountsScreenOn((ConstantsProto) value);
            return this;
        }

        public Builder setMaxJobCountsScreenOn(MaxJobCountsPerMemoryTrimLevelProto.Builder builderForValue) {
            copyOnWrite();
            ((ConstantsProto) this.instance).setMaxJobCountsScreenOn((ConstantsProto) builderForValue);
            return this;
        }

        public Builder mergeMaxJobCountsScreenOn(MaxJobCountsPerMemoryTrimLevelProto value) {
            copyOnWrite();
            ((ConstantsProto) this.instance).mergeMaxJobCountsScreenOn(value);
            return this;
        }

        public Builder clearMaxJobCountsScreenOn() {
            copyOnWrite();
            ((ConstantsProto) this.instance).clearMaxJobCountsScreenOn();
            return this;
        }

        @Override // com.android.server.job.ConstantsProtoOrBuilder
        public boolean hasMaxJobCountsScreenOff() {
            return ((ConstantsProto) this.instance).hasMaxJobCountsScreenOff();
        }

        @Override // com.android.server.job.ConstantsProtoOrBuilder
        public MaxJobCountsPerMemoryTrimLevelProto getMaxJobCountsScreenOff() {
            return ((ConstantsProto) this.instance).getMaxJobCountsScreenOff();
        }

        public Builder setMaxJobCountsScreenOff(MaxJobCountsPerMemoryTrimLevelProto value) {
            copyOnWrite();
            ((ConstantsProto) this.instance).setMaxJobCountsScreenOff((ConstantsProto) value);
            return this;
        }

        public Builder setMaxJobCountsScreenOff(MaxJobCountsPerMemoryTrimLevelProto.Builder builderForValue) {
            copyOnWrite();
            ((ConstantsProto) this.instance).setMaxJobCountsScreenOff((ConstantsProto) builderForValue);
            return this;
        }

        public Builder mergeMaxJobCountsScreenOff(MaxJobCountsPerMemoryTrimLevelProto value) {
            copyOnWrite();
            ((ConstantsProto) this.instance).mergeMaxJobCountsScreenOff(value);
            return this;
        }

        public Builder clearMaxJobCountsScreenOff() {
            copyOnWrite();
            ((ConstantsProto) this.instance).clearMaxJobCountsScreenOff();
            return this;
        }

        @Override // com.android.server.job.ConstantsProtoOrBuilder
        public boolean hasScreenOffJobConcurrencyIncreaseDelayMs() {
            return ((ConstantsProto) this.instance).hasScreenOffJobConcurrencyIncreaseDelayMs();
        }

        @Override // com.android.server.job.ConstantsProtoOrBuilder
        public int getScreenOffJobConcurrencyIncreaseDelayMs() {
            return ((ConstantsProto) this.instance).getScreenOffJobConcurrencyIncreaseDelayMs();
        }

        public Builder setScreenOffJobConcurrencyIncreaseDelayMs(int value) {
            copyOnWrite();
            ((ConstantsProto) this.instance).setScreenOffJobConcurrencyIncreaseDelayMs(value);
            return this;
        }

        public Builder clearScreenOffJobConcurrencyIncreaseDelayMs() {
            copyOnWrite();
            ((ConstantsProto) this.instance).clearScreenOffJobConcurrencyIncreaseDelayMs();
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new ConstantsProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                this.standbyBeats_.makeImmutable();
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                ConstantsProto other = (ConstantsProto) arg1;
                this.minIdleCount_ = visitor.visitInt(hasMinIdleCount(), this.minIdleCount_, other.hasMinIdleCount(), other.minIdleCount_);
                this.minChargingCount_ = visitor.visitInt(hasMinChargingCount(), this.minChargingCount_, other.hasMinChargingCount(), other.minChargingCount_);
                this.minBatteryNotLowCount_ = visitor.visitInt(hasMinBatteryNotLowCount(), this.minBatteryNotLowCount_, other.hasMinBatteryNotLowCount(), other.minBatteryNotLowCount_);
                this.minStorageNotLowCount_ = visitor.visitInt(hasMinStorageNotLowCount(), this.minStorageNotLowCount_, other.hasMinStorageNotLowCount(), other.minStorageNotLowCount_);
                this.minConnectivityCount_ = visitor.visitInt(hasMinConnectivityCount(), this.minConnectivityCount_, other.hasMinConnectivityCount(), other.minConnectivityCount_);
                this.minContentCount_ = visitor.visitInt(hasMinContentCount(), this.minContentCount_, other.hasMinContentCount(), other.minContentCount_);
                this.minReadyJobsCount_ = visitor.visitInt(hasMinReadyJobsCount(), this.minReadyJobsCount_, other.hasMinReadyJobsCount(), other.minReadyJobsCount_);
                this.heavyUseFactor_ = visitor.visitDouble(hasHeavyUseFactor(), this.heavyUseFactor_, other.hasHeavyUseFactor(), other.heavyUseFactor_);
                this.moderateUseFactor_ = visitor.visitDouble(hasModerateUseFactor(), this.moderateUseFactor_, other.hasModerateUseFactor(), other.moderateUseFactor_);
                this.fgJobCount_ = visitor.visitInt(hasFgJobCount(), this.fgJobCount_, other.hasFgJobCount(), other.fgJobCount_);
                this.bgNormalJobCount_ = visitor.visitInt(hasBgNormalJobCount(), this.bgNormalJobCount_, other.hasBgNormalJobCount(), other.bgNormalJobCount_);
                this.bgModerateJobCount_ = visitor.visitInt(hasBgModerateJobCount(), this.bgModerateJobCount_, other.hasBgModerateJobCount(), other.bgModerateJobCount_);
                this.bgLowJobCount_ = visitor.visitInt(hasBgLowJobCount(), this.bgLowJobCount_, other.hasBgLowJobCount(), other.bgLowJobCount_);
                this.bgCriticalJobCount_ = visitor.visitInt(hasBgCriticalJobCount(), this.bgCriticalJobCount_, other.hasBgCriticalJobCount(), other.bgCriticalJobCount_);
                this.maxStandardRescheduleCount_ = visitor.visitInt(hasMaxStandardRescheduleCount(), this.maxStandardRescheduleCount_, other.hasMaxStandardRescheduleCount(), other.maxStandardRescheduleCount_);
                this.maxWorkRescheduleCount_ = visitor.visitInt(hasMaxWorkRescheduleCount(), this.maxWorkRescheduleCount_, other.hasMaxWorkRescheduleCount(), other.maxWorkRescheduleCount_);
                this.minLinearBackoffTimeMs_ = visitor.visitLong(hasMinLinearBackoffTimeMs(), this.minLinearBackoffTimeMs_, other.hasMinLinearBackoffTimeMs(), other.minLinearBackoffTimeMs_);
                this.minExpBackoffTimeMs_ = visitor.visitLong(hasMinExpBackoffTimeMs(), this.minExpBackoffTimeMs_, other.hasMinExpBackoffTimeMs(), other.minExpBackoffTimeMs_);
                this.standbyHeartbeatTimeMs_ = visitor.visitLong(hasStandbyHeartbeatTimeMs(), this.standbyHeartbeatTimeMs_, other.hasStandbyHeartbeatTimeMs(), other.standbyHeartbeatTimeMs_);
                this.standbyBeats_ = visitor.visitIntList(this.standbyBeats_, other.standbyBeats_);
                this.connCongestionDelayFrac_ = visitor.visitDouble(hasConnCongestionDelayFrac(), this.connCongestionDelayFrac_, other.hasConnCongestionDelayFrac(), other.connCongestionDelayFrac_);
                this.connPrefetchRelaxFrac_ = visitor.visitDouble(hasConnPrefetchRelaxFrac(), this.connPrefetchRelaxFrac_, other.hasConnPrefetchRelaxFrac(), other.connPrefetchRelaxFrac_);
                this.useHeartbeats_ = visitor.visitBoolean(hasUseHeartbeats(), this.useHeartbeats_, other.hasUseHeartbeats(), other.useHeartbeats_);
                this.timeController_ = (TimeController) visitor.visitMessage(this.timeController_, other.timeController_);
                this.quotaController_ = (QuotaController) visitor.visitMessage(this.quotaController_, other.quotaController_);
                this.maxJobCountsScreenOn_ = (MaxJobCountsPerMemoryTrimLevelProto) visitor.visitMessage(this.maxJobCountsScreenOn_, other.maxJobCountsScreenOn_);
                this.maxJobCountsScreenOff_ = (MaxJobCountsPerMemoryTrimLevelProto) visitor.visitMessage(this.maxJobCountsScreenOff_, other.maxJobCountsScreenOff_);
                this.screenOffJobConcurrencyIncreaseDelayMs_ = visitor.visitInt(hasScreenOffJobConcurrencyIncreaseDelayMs(), this.screenOffJobConcurrencyIncreaseDelayMs_, other.hasScreenOffJobConcurrencyIncreaseDelayMs(), other.screenOffJobConcurrencyIncreaseDelayMs_);
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
                            case 8:
                                this.bitField0_ |= 1;
                                this.minIdleCount_ = input.readInt32();
                                break;
                            case 16:
                                this.bitField0_ |= 2;
                                this.minChargingCount_ = input.readInt32();
                                break;
                            case 24:
                                this.bitField0_ |= 4;
                                this.minBatteryNotLowCount_ = input.readInt32();
                                break;
                            case 32:
                                this.bitField0_ |= 8;
                                this.minStorageNotLowCount_ = input.readInt32();
                                break;
                            case 40:
                                this.bitField0_ |= 16;
                                this.minConnectivityCount_ = input.readInt32();
                                break;
                            case 48:
                                this.bitField0_ |= 32;
                                this.minContentCount_ = input.readInt32();
                                break;
                            case 56:
                                this.bitField0_ |= 64;
                                this.minReadyJobsCount_ = input.readInt32();
                                break;
                            case 65:
                                this.bitField0_ |= 128;
                                this.heavyUseFactor_ = input.readDouble();
                                break;
                            case 73:
                                this.bitField0_ |= 256;
                                this.moderateUseFactor_ = input.readDouble();
                                break;
                            case 80:
                                this.bitField0_ |= 512;
                                this.fgJobCount_ = input.readInt32();
                                break;
                            case 88:
                                this.bitField0_ |= 1024;
                                this.bgNormalJobCount_ = input.readInt32();
                                break;
                            case 96:
                                this.bitField0_ |= 2048;
                                this.bgModerateJobCount_ = input.readInt32();
                                break;
                            case 104:
                                this.bitField0_ |= 4096;
                                this.bgLowJobCount_ = input.readInt32();
                                break;
                            case 112:
                                this.bitField0_ |= 8192;
                                this.bgCriticalJobCount_ = input.readInt32();
                                break;
                            case 120:
                                this.bitField0_ |= 16384;
                                this.maxStandardRescheduleCount_ = input.readInt32();
                                break;
                            case 128:
                                this.bitField0_ |= 32768;
                                this.maxWorkRescheduleCount_ = input.readInt32();
                                break;
                            case 136:
                                this.bitField0_ |= 65536;
                                this.minLinearBackoffTimeMs_ = input.readInt64();
                                break;
                            case 144:
                                this.bitField0_ |= 131072;
                                this.minExpBackoffTimeMs_ = input.readInt64();
                                break;
                            case 152:
                                this.bitField0_ |= 262144;
                                this.standbyHeartbeatTimeMs_ = input.readInt64();
                                break;
                            case 160:
                                if (!this.standbyBeats_.isModifiable()) {
                                    this.standbyBeats_ = GeneratedMessageLite.mutableCopy(this.standbyBeats_);
                                }
                                this.standbyBeats_.addInt(input.readInt32());
                                break;
                            case 162:
                                int limit = input.pushLimit(input.readRawVarint32());
                                if (!this.standbyBeats_.isModifiable() && input.getBytesUntilLimit() > 0) {
                                    this.standbyBeats_ = GeneratedMessageLite.mutableCopy(this.standbyBeats_);
                                }
                                while (input.getBytesUntilLimit() > 0) {
                                    this.standbyBeats_.addInt(input.readInt32());
                                }
                                input.popLimit(limit);
                                break;
                            case 169:
                                this.bitField0_ |= 524288;
                                this.connCongestionDelayFrac_ = input.readDouble();
                                break;
                            case 177:
                                this.bitField0_ |= 1048576;
                                this.connPrefetchRelaxFrac_ = input.readDouble();
                                break;
                            case 184:
                                this.bitField0_ |= 2097152;
                                this.useHeartbeats_ = input.readBool();
                                break;
                            case AtomsProto.Atom.MEDIAMETRICS_AUDIOTRACK_REPORTED_FIELD_NUMBER:
                                QuotaController.Builder subBuilder = null;
                                if ((this.bitField0_ & 8388608) == 8388608) {
                                    subBuilder = (QuotaController.Builder) this.quotaController_.toBuilder();
                                }
                                this.quotaController_ = (QuotaController) input.readMessage(QuotaController.parser(), extensionRegistry);
                                if (subBuilder != null) {
                                    subBuilder.mergeFrom((GeneratedMessageLite) this.quotaController_);
                                    this.quotaController_ = (QuotaController) subBuilder.buildPartial();
                                }
                                this.bitField0_ |= 8388608;
                                break;
                            case PROCESS_STATS_SUMMARY_VALUE:
                                TimeController.Builder subBuilder2 = null;
                                if ((this.bitField0_ & 4194304) == 4194304) {
                                    subBuilder2 = (TimeController.Builder) this.timeController_.toBuilder();
                                }
                                this.timeController_ = (TimeController) input.readMessage(TimeController.parser(), extensionRegistry);
                                if (subBuilder2 != null) {
                                    subBuilder2.mergeFrom((GeneratedMessageLite) this.timeController_);
                                    this.timeController_ = (TimeController) subBuilder2.buildPartial();
                                }
                                this.bitField0_ |= 4194304;
                                break;
                            case AtomsProto.Atom.LOCATION_MANAGER_API_USAGE_REPORTED_FIELD_NUMBER:
                                MaxJobCountsPerMemoryTrimLevelProto.Builder subBuilder3 = null;
                                if ((this.bitField0_ & 16777216) == 16777216) {
                                    subBuilder3 = (MaxJobCountsPerMemoryTrimLevelProto.Builder) this.maxJobCountsScreenOn_.toBuilder();
                                }
                                this.maxJobCountsScreenOn_ = (MaxJobCountsPerMemoryTrimLevelProto) input.readMessage(MaxJobCountsPerMemoryTrimLevelProto.parser(), extensionRegistry);
                                if (subBuilder3 != null) {
                                    subBuilder3.mergeFrom((GeneratedMessageLite) this.maxJobCountsScreenOn_);
                                    this.maxJobCountsScreenOn_ = (MaxJobCountsPerMemoryTrimLevelProto) subBuilder3.buildPartial();
                                }
                                this.bitField0_ |= 16777216;
                                break;
                            case AtomsProto.Atom.PERMISSION_APPS_FRAGMENT_VIEWED_FIELD_NUMBER:
                                MaxJobCountsPerMemoryTrimLevelProto.Builder subBuilder4 = null;
                                if ((this.bitField0_ & 33554432) == 33554432) {
                                    subBuilder4 = (MaxJobCountsPerMemoryTrimLevelProto.Builder) this.maxJobCountsScreenOff_.toBuilder();
                                }
                                this.maxJobCountsScreenOff_ = (MaxJobCountsPerMemoryTrimLevelProto) input.readMessage(MaxJobCountsPerMemoryTrimLevelProto.parser(), extensionRegistry);
                                if (subBuilder4 != null) {
                                    subBuilder4.mergeFrom((GeneratedMessageLite) this.maxJobCountsScreenOff_);
                                    this.maxJobCountsScreenOff_ = (MaxJobCountsPerMemoryTrimLevelProto) subBuilder4.buildPartial();
                                }
                                this.bitField0_ |= 33554432;
                                break;
                            case AtomsProto.Atom.BACK_GESTURE_REPORTED_REPORTED_FIELD_NUMBER:
                                this.bitField0_ |= 67108864;
                                this.screenOffJobConcurrencyIncreaseDelayMs_ = input.readInt32();
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
                    synchronized (ConstantsProto.class) {
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

    public static ConstantsProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<ConstantsProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
