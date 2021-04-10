package com.android.server;

import com.android.internal.util.LocalLogProto;
import com.android.os.AtomsProto;
import com.android.server.AlarmClockMetadataProto;
import com.android.server.AlarmProto;
import com.android.server.BatchProto;
import com.android.server.BroadcastStatsProto;
import com.android.server.ConstantsProto;
import com.android.server.FilterStatsProto;
import com.android.server.ForceAppStandbyTrackerProto;
import com.android.server.IdleDispatchEntryProto;
import com.android.server.InFlightProto;
import com.android.server.WakeupEventProto;
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

public final class AlarmManagerServiceDumpProto extends GeneratedMessageLite<AlarmManagerServiceDumpProto, Builder> implements AlarmManagerServiceDumpProtoOrBuilder {
    public static final int ALARM_STATS_FIELD_NUMBER = 39;
    public static final int ALLOW_WHILE_IDLE_DISPATCHES_FIELD_NUMBER = 40;
    public static final int BROADCAST_REF_COUNT_FIELD_NUMBER = 29;
    public static final int CURRENT_TIME_FIELD_NUMBER = 1;
    private static final AlarmManagerServiceDumpProto DEFAULT_INSTANCE = new AlarmManagerServiceDumpProto();
    public static final int DELAYED_ALARM_COUNT_FIELD_NUMBER = 25;
    public static final int DEVICE_IDLE_USER_WHITELIST_APP_IDS_FIELD_NUMBER = 17;
    public static final int ELAPSED_REALTIME_FIELD_NUMBER = 2;
    public static final int FORCE_APP_STANDBY_TRACKER_FIELD_NUMBER = 6;
    public static final int IS_INTERACTIVE_FIELD_NUMBER = 7;
    public static final int LAST_ALLOW_WHILE_IDLE_DISPATCH_TIMES_FIELD_NUMBER = 36;
    public static final int LAST_TIME_CHANGE_CLOCK_TIME_FIELD_NUMBER = 3;
    public static final int LAST_TIME_CHANGE_REALTIME_FIELD_NUMBER = 4;
    public static final int LISTENER_FINISH_COUNT_FIELD_NUMBER = 33;
    public static final int LISTENER_SEND_COUNT_FIELD_NUMBER = 32;
    public static final int MAX_DELAY_DURATION_MS_FIELD_NUMBER = 27;
    public static final int MAX_NON_INTERACTIVE_DURATION_MS_FIELD_NUMBER = 28;
    public static final int MAX_WAKEUP_DELAY_MS_FIELD_NUMBER = 9;
    public static final int NEXT_ALARM_CLOCK_METADATA_FIELD_NUMBER = 18;
    public static final int NEXT_WAKE_FROM_IDLE_FIELD_NUMBER = 23;
    public static final int OUTSTANDING_DELIVERIES_FIELD_NUMBER = 34;
    private static volatile Parser<AlarmManagerServiceDumpProto> PARSER = null;
    public static final int PAST_DUE_NON_WAKEUP_ALARMS_FIELD_NUMBER = 24;
    public static final int PENDING_ALARM_BATCHES_FIELD_NUMBER = 19;
    public static final int PENDING_IDLE_UNTIL_FIELD_NUMBER = 21;
    public static final int PENDING_INTENT_FINISH_COUNT_FIELD_NUMBER = 31;
    public static final int PENDING_INTENT_SEND_COUNT_FIELD_NUMBER = 30;
    public static final int PENDING_USER_BLOCKED_BACKGROUND_ALARMS_FIELD_NUMBER = 20;
    public static final int PENDING_WHILE_IDLE_ALARMS_FIELD_NUMBER = 22;
    public static final int RECENT_PROBLEMS_FIELD_NUMBER = 37;
    public static final int RECENT_WAKEUP_HISTORY_FIELD_NUMBER = 41;
    public static final int SETTINGS_FIELD_NUMBER = 5;
    public static final int TIME_CHANGE_EVENT_COUNT_FIELD_NUMBER = 16;
    public static final int TIME_SINCE_LAST_DISPATCH_MS_FIELD_NUMBER = 10;
    public static final int TIME_SINCE_LAST_WAKEUP_MS_FIELD_NUMBER = 14;
    public static final int TIME_SINCE_LAST_WAKEUP_SET_MS_FIELD_NUMBER = 15;
    public static final int TIME_SINCE_NON_INTERACTIVE_MS_FIELD_NUMBER = 8;
    public static final int TIME_UNTIL_NEXT_NON_WAKEUP_ALARM_MS_FIELD_NUMBER = 12;
    public static final int TIME_UNTIL_NEXT_NON_WAKEUP_DELIVERY_MS_FIELD_NUMBER = 11;
    public static final int TIME_UNTIL_NEXT_WAKEUP_MS_FIELD_NUMBER = 13;
    public static final int TOP_ALARMS_FIELD_NUMBER = 38;
    public static final int TOTAL_DELAY_TIME_MS_FIELD_NUMBER = 26;
    public static final int USE_ALLOW_WHILE_IDLE_SHORT_TIME_FIELD_NUMBER = 35;
    private Internal.ProtobufList<AlarmStat> alarmStats_ = emptyProtobufList();
    private Internal.ProtobufList<IdleDispatchEntryProto> allowWhileIdleDispatches_ = emptyProtobufList();
    private int bitField0_;
    private int broadcastRefCount_ = 0;
    private long currentTime_ = 0;
    private int delayedAlarmCount_ = 0;
    private Internal.IntList deviceIdleUserWhitelistAppIds_ = emptyIntList();
    private long elapsedRealtime_ = 0;
    private ForceAppStandbyTrackerProto forceAppStandbyTracker_;
    private boolean isInteractive_ = false;
    private Internal.ProtobufList<LastAllowWhileIdleDispatch> lastAllowWhileIdleDispatchTimes_ = emptyProtobufList();
    private long lastTimeChangeClockTime_ = 0;
    private long lastTimeChangeRealtime_ = 0;
    private int listenerFinishCount_ = 0;
    private int listenerSendCount_ = 0;
    private long maxDelayDurationMs_ = 0;
    private long maxNonInteractiveDurationMs_ = 0;
    private long maxWakeupDelayMs_ = 0;
    private Internal.ProtobufList<AlarmClockMetadataProto> nextAlarmClockMetadata_ = emptyProtobufList();
    private AlarmProto nextWakeFromIdle_;
    private Internal.ProtobufList<InFlightProto> outstandingDeliveries_ = emptyProtobufList();
    private Internal.ProtobufList<AlarmProto> pastDueNonWakeupAlarms_ = emptyProtobufList();
    private Internal.ProtobufList<BatchProto> pendingAlarmBatches_ = emptyProtobufList();
    private AlarmProto pendingIdleUntil_;
    private int pendingIntentFinishCount_ = 0;
    private int pendingIntentSendCount_ = 0;
    private Internal.ProtobufList<AlarmProto> pendingUserBlockedBackgroundAlarms_ = emptyProtobufList();
    private Internal.ProtobufList<AlarmProto> pendingWhileIdleAlarms_ = emptyProtobufList();
    private LocalLogProto recentProblems_;
    private Internal.ProtobufList<WakeupEventProto> recentWakeupHistory_ = emptyProtobufList();
    private ConstantsProto settings_;
    private long timeChangeEventCount_ = 0;
    private long timeSinceLastDispatchMs_ = 0;
    private long timeSinceLastWakeupMs_ = 0;
    private long timeSinceLastWakeupSetMs_ = 0;
    private long timeSinceNonInteractiveMs_ = 0;
    private long timeUntilNextNonWakeupAlarmMs_ = 0;
    private long timeUntilNextNonWakeupDeliveryMs_ = 0;
    private long timeUntilNextWakeupMs_ = 0;
    private Internal.ProtobufList<TopAlarm> topAlarms_ = emptyProtobufList();
    private long totalDelayTimeMs_ = 0;
    private Internal.IntList useAllowWhileIdleShortTime_ = emptyIntList();

    public interface AlarmStatOrBuilder extends MessageLiteOrBuilder {
        BroadcastStatsProto getBroadcast();

        FilterStatsProto getFilters(int i);

        int getFiltersCount();

        List<FilterStatsProto> getFiltersList();

        boolean hasBroadcast();
    }

    public interface LastAllowWhileIdleDispatchOrBuilder extends MessageLiteOrBuilder {
        long getNextAllowedMs();

        long getTimeMs();

        int getUid();

        boolean hasNextAllowedMs();

        boolean hasTimeMs();

        boolean hasUid();
    }

    public interface TopAlarmOrBuilder extends MessageLiteOrBuilder {
        FilterStatsProto getFilter();

        String getPackageName();

        ByteString getPackageNameBytes();

        int getUid();

        boolean hasFilter();

        boolean hasPackageName();

        boolean hasUid();
    }

    private AlarmManagerServiceDumpProto() {
    }

    public static final class LastAllowWhileIdleDispatch extends GeneratedMessageLite<LastAllowWhileIdleDispatch, Builder> implements LastAllowWhileIdleDispatchOrBuilder {
        private static final LastAllowWhileIdleDispatch DEFAULT_INSTANCE = new LastAllowWhileIdleDispatch();
        public static final int NEXT_ALLOWED_MS_FIELD_NUMBER = 3;
        private static volatile Parser<LastAllowWhileIdleDispatch> PARSER = null;
        public static final int TIME_MS_FIELD_NUMBER = 2;
        public static final int UID_FIELD_NUMBER = 1;
        private int bitField0_;
        private long nextAllowedMs_ = 0;
        private long timeMs_ = 0;
        private int uid_ = 0;

        private LastAllowWhileIdleDispatch() {
        }

        @Override // com.android.server.AlarmManagerServiceDumpProto.LastAllowWhileIdleDispatchOrBuilder
        public boolean hasUid() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // com.android.server.AlarmManagerServiceDumpProto.LastAllowWhileIdleDispatchOrBuilder
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

        @Override // com.android.server.AlarmManagerServiceDumpProto.LastAllowWhileIdleDispatchOrBuilder
        public boolean hasTimeMs() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // com.android.server.AlarmManagerServiceDumpProto.LastAllowWhileIdleDispatchOrBuilder
        public long getTimeMs() {
            return this.timeMs_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setTimeMs(long value) {
            this.bitField0_ |= 2;
            this.timeMs_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearTimeMs() {
            this.bitField0_ &= -3;
            this.timeMs_ = 0;
        }

        @Override // com.android.server.AlarmManagerServiceDumpProto.LastAllowWhileIdleDispatchOrBuilder
        public boolean hasNextAllowedMs() {
            return (this.bitField0_ & 4) == 4;
        }

        @Override // com.android.server.AlarmManagerServiceDumpProto.LastAllowWhileIdleDispatchOrBuilder
        public long getNextAllowedMs() {
            return this.nextAllowedMs_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setNextAllowedMs(long value) {
            this.bitField0_ |= 4;
            this.nextAllowedMs_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearNextAllowedMs() {
            this.bitField0_ &= -5;
            this.nextAllowedMs_ = 0;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                output.writeInt32(1, this.uid_);
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeInt64(2, this.timeMs_);
            }
            if ((this.bitField0_ & 4) == 4) {
                output.writeInt64(3, this.nextAllowedMs_);
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
                size2 += CodedOutputStream.computeInt64Size(2, this.timeMs_);
            }
            if ((this.bitField0_ & 4) == 4) {
                size2 += CodedOutputStream.computeInt64Size(3, this.nextAllowedMs_);
            }
            int size3 = size2 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static LastAllowWhileIdleDispatch parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (LastAllowWhileIdleDispatch) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static LastAllowWhileIdleDispatch parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (LastAllowWhileIdleDispatch) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static LastAllowWhileIdleDispatch parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (LastAllowWhileIdleDispatch) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static LastAllowWhileIdleDispatch parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (LastAllowWhileIdleDispatch) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static LastAllowWhileIdleDispatch parseFrom(InputStream input) throws IOException {
            return (LastAllowWhileIdleDispatch) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static LastAllowWhileIdleDispatch parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (LastAllowWhileIdleDispatch) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static LastAllowWhileIdleDispatch parseDelimitedFrom(InputStream input) throws IOException {
            return (LastAllowWhileIdleDispatch) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static LastAllowWhileIdleDispatch parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (LastAllowWhileIdleDispatch) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static LastAllowWhileIdleDispatch parseFrom(CodedInputStream input) throws IOException {
            return (LastAllowWhileIdleDispatch) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static LastAllowWhileIdleDispatch parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (LastAllowWhileIdleDispatch) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(LastAllowWhileIdleDispatch prototype) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<LastAllowWhileIdleDispatch, Builder> implements LastAllowWhileIdleDispatchOrBuilder {
            private Builder() {
                super(LastAllowWhileIdleDispatch.DEFAULT_INSTANCE);
            }

            @Override // com.android.server.AlarmManagerServiceDumpProto.LastAllowWhileIdleDispatchOrBuilder
            public boolean hasUid() {
                return ((LastAllowWhileIdleDispatch) this.instance).hasUid();
            }

            @Override // com.android.server.AlarmManagerServiceDumpProto.LastAllowWhileIdleDispatchOrBuilder
            public int getUid() {
                return ((LastAllowWhileIdleDispatch) this.instance).getUid();
            }

            public Builder setUid(int value) {
                copyOnWrite();
                ((LastAllowWhileIdleDispatch) this.instance).setUid(value);
                return this;
            }

            public Builder clearUid() {
                copyOnWrite();
                ((LastAllowWhileIdleDispatch) this.instance).clearUid();
                return this;
            }

            @Override // com.android.server.AlarmManagerServiceDumpProto.LastAllowWhileIdleDispatchOrBuilder
            public boolean hasTimeMs() {
                return ((LastAllowWhileIdleDispatch) this.instance).hasTimeMs();
            }

            @Override // com.android.server.AlarmManagerServiceDumpProto.LastAllowWhileIdleDispatchOrBuilder
            public long getTimeMs() {
                return ((LastAllowWhileIdleDispatch) this.instance).getTimeMs();
            }

            public Builder setTimeMs(long value) {
                copyOnWrite();
                ((LastAllowWhileIdleDispatch) this.instance).setTimeMs(value);
                return this;
            }

            public Builder clearTimeMs() {
                copyOnWrite();
                ((LastAllowWhileIdleDispatch) this.instance).clearTimeMs();
                return this;
            }

            @Override // com.android.server.AlarmManagerServiceDumpProto.LastAllowWhileIdleDispatchOrBuilder
            public boolean hasNextAllowedMs() {
                return ((LastAllowWhileIdleDispatch) this.instance).hasNextAllowedMs();
            }

            @Override // com.android.server.AlarmManagerServiceDumpProto.LastAllowWhileIdleDispatchOrBuilder
            public long getNextAllowedMs() {
                return ((LastAllowWhileIdleDispatch) this.instance).getNextAllowedMs();
            }

            public Builder setNextAllowedMs(long value) {
                copyOnWrite();
                ((LastAllowWhileIdleDispatch) this.instance).setNextAllowedMs(value);
                return this;
            }

            public Builder clearNextAllowedMs() {
                copyOnWrite();
                ((LastAllowWhileIdleDispatch) this.instance).clearNextAllowedMs();
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new LastAllowWhileIdleDispatch();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    LastAllowWhileIdleDispatch other = (LastAllowWhileIdleDispatch) arg1;
                    this.uid_ = visitor.visitInt(hasUid(), this.uid_, other.hasUid(), other.uid_);
                    this.timeMs_ = visitor.visitLong(hasTimeMs(), this.timeMs_, other.hasTimeMs(), other.timeMs_);
                    this.nextAllowedMs_ = visitor.visitLong(hasNextAllowedMs(), this.nextAllowedMs_, other.hasNextAllowedMs(), other.nextAllowedMs_);
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
                                this.timeMs_ = input.readInt64();
                            } else if (tag == 24) {
                                this.bitField0_ |= 4;
                                this.nextAllowedMs_ = input.readInt64();
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
                        synchronized (LastAllowWhileIdleDispatch.class) {
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

        public static LastAllowWhileIdleDispatch getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<LastAllowWhileIdleDispatch> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class TopAlarm extends GeneratedMessageLite<TopAlarm, Builder> implements TopAlarmOrBuilder {
        private static final TopAlarm DEFAULT_INSTANCE = new TopAlarm();
        public static final int FILTER_FIELD_NUMBER = 3;
        public static final int PACKAGE_NAME_FIELD_NUMBER = 2;
        private static volatile Parser<TopAlarm> PARSER = null;
        public static final int UID_FIELD_NUMBER = 1;
        private int bitField0_;
        private FilterStatsProto filter_;
        private String packageName_ = "";
        private int uid_ = 0;

        private TopAlarm() {
        }

        @Override // com.android.server.AlarmManagerServiceDumpProto.TopAlarmOrBuilder
        public boolean hasUid() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // com.android.server.AlarmManagerServiceDumpProto.TopAlarmOrBuilder
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

        @Override // com.android.server.AlarmManagerServiceDumpProto.TopAlarmOrBuilder
        public boolean hasPackageName() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // com.android.server.AlarmManagerServiceDumpProto.TopAlarmOrBuilder
        public String getPackageName() {
            return this.packageName_;
        }

        @Override // com.android.server.AlarmManagerServiceDumpProto.TopAlarmOrBuilder
        public ByteString getPackageNameBytes() {
            return ByteString.copyFromUtf8(this.packageName_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setPackageName(String value) {
            if (value != null) {
                this.bitField0_ |= 2;
                this.packageName_ = value;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearPackageName() {
            this.bitField0_ &= -3;
            this.packageName_ = getDefaultInstance().getPackageName();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setPackageNameBytes(ByteString value) {
            if (value != null) {
                this.bitField0_ |= 2;
                this.packageName_ = value.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        @Override // com.android.server.AlarmManagerServiceDumpProto.TopAlarmOrBuilder
        public boolean hasFilter() {
            return (this.bitField0_ & 4) == 4;
        }

        @Override // com.android.server.AlarmManagerServiceDumpProto.TopAlarmOrBuilder
        public FilterStatsProto getFilter() {
            FilterStatsProto filterStatsProto = this.filter_;
            return filterStatsProto == null ? FilterStatsProto.getDefaultInstance() : filterStatsProto;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setFilter(FilterStatsProto value) {
            if (value != null) {
                this.filter_ = value;
                this.bitField0_ |= 4;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setFilter(FilterStatsProto.Builder builderForValue) {
            this.filter_ = (FilterStatsProto) builderForValue.build();
            this.bitField0_ |= 4;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void mergeFilter(FilterStatsProto value) {
            FilterStatsProto filterStatsProto = this.filter_;
            if (filterStatsProto == null || filterStatsProto == FilterStatsProto.getDefaultInstance()) {
                this.filter_ = value;
            } else {
                this.filter_ = (FilterStatsProto) ((FilterStatsProto.Builder) FilterStatsProto.newBuilder(this.filter_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
            }
            this.bitField0_ |= 4;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearFilter() {
            this.filter_ = null;
            this.bitField0_ &= -5;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                output.writeInt32(1, this.uid_);
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeString(2, getPackageName());
            }
            if ((this.bitField0_ & 4) == 4) {
                output.writeMessage(3, getFilter());
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
                size2 += CodedOutputStream.computeStringSize(2, getPackageName());
            }
            if ((this.bitField0_ & 4) == 4) {
                size2 += CodedOutputStream.computeMessageSize(3, getFilter());
            }
            int size3 = size2 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static TopAlarm parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (TopAlarm) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static TopAlarm parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (TopAlarm) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static TopAlarm parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (TopAlarm) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static TopAlarm parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (TopAlarm) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static TopAlarm parseFrom(InputStream input) throws IOException {
            return (TopAlarm) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static TopAlarm parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (TopAlarm) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static TopAlarm parseDelimitedFrom(InputStream input) throws IOException {
            return (TopAlarm) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static TopAlarm parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (TopAlarm) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static TopAlarm parseFrom(CodedInputStream input) throws IOException {
            return (TopAlarm) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static TopAlarm parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (TopAlarm) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(TopAlarm prototype) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<TopAlarm, Builder> implements TopAlarmOrBuilder {
            private Builder() {
                super(TopAlarm.DEFAULT_INSTANCE);
            }

            @Override // com.android.server.AlarmManagerServiceDumpProto.TopAlarmOrBuilder
            public boolean hasUid() {
                return ((TopAlarm) this.instance).hasUid();
            }

            @Override // com.android.server.AlarmManagerServiceDumpProto.TopAlarmOrBuilder
            public int getUid() {
                return ((TopAlarm) this.instance).getUid();
            }

            public Builder setUid(int value) {
                copyOnWrite();
                ((TopAlarm) this.instance).setUid(value);
                return this;
            }

            public Builder clearUid() {
                copyOnWrite();
                ((TopAlarm) this.instance).clearUid();
                return this;
            }

            @Override // com.android.server.AlarmManagerServiceDumpProto.TopAlarmOrBuilder
            public boolean hasPackageName() {
                return ((TopAlarm) this.instance).hasPackageName();
            }

            @Override // com.android.server.AlarmManagerServiceDumpProto.TopAlarmOrBuilder
            public String getPackageName() {
                return ((TopAlarm) this.instance).getPackageName();
            }

            @Override // com.android.server.AlarmManagerServiceDumpProto.TopAlarmOrBuilder
            public ByteString getPackageNameBytes() {
                return ((TopAlarm) this.instance).getPackageNameBytes();
            }

            public Builder setPackageName(String value) {
                copyOnWrite();
                ((TopAlarm) this.instance).setPackageName(value);
                return this;
            }

            public Builder clearPackageName() {
                copyOnWrite();
                ((TopAlarm) this.instance).clearPackageName();
                return this;
            }

            public Builder setPackageNameBytes(ByteString value) {
                copyOnWrite();
                ((TopAlarm) this.instance).setPackageNameBytes(value);
                return this;
            }

            @Override // com.android.server.AlarmManagerServiceDumpProto.TopAlarmOrBuilder
            public boolean hasFilter() {
                return ((TopAlarm) this.instance).hasFilter();
            }

            @Override // com.android.server.AlarmManagerServiceDumpProto.TopAlarmOrBuilder
            public FilterStatsProto getFilter() {
                return ((TopAlarm) this.instance).getFilter();
            }

            public Builder setFilter(FilterStatsProto value) {
                copyOnWrite();
                ((TopAlarm) this.instance).setFilter((TopAlarm) value);
                return this;
            }

            public Builder setFilter(FilterStatsProto.Builder builderForValue) {
                copyOnWrite();
                ((TopAlarm) this.instance).setFilter((TopAlarm) builderForValue);
                return this;
            }

            public Builder mergeFilter(FilterStatsProto value) {
                copyOnWrite();
                ((TopAlarm) this.instance).mergeFilter(value);
                return this;
            }

            public Builder clearFilter() {
                copyOnWrite();
                ((TopAlarm) this.instance).clearFilter();
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new TopAlarm();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    TopAlarm other = (TopAlarm) arg1;
                    this.uid_ = visitor.visitInt(hasUid(), this.uid_, other.hasUid(), other.uid_);
                    this.packageName_ = visitor.visitString(hasPackageName(), this.packageName_, other.hasPackageName(), other.packageName_);
                    this.filter_ = (FilterStatsProto) visitor.visitMessage(this.filter_, other.filter_);
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
                                this.uid_ = input.readInt32();
                            } else if (tag == 18) {
                                String s = input.readString();
                                this.bitField0_ |= 2;
                                this.packageName_ = s;
                            } else if (tag == 26) {
                                FilterStatsProto.Builder subBuilder = null;
                                if ((this.bitField0_ & 4) == 4) {
                                    subBuilder = (FilterStatsProto.Builder) this.filter_.toBuilder();
                                }
                                this.filter_ = (FilterStatsProto) input.readMessage(FilterStatsProto.parser(), extensionRegistry);
                                if (subBuilder != null) {
                                    subBuilder.mergeFrom((GeneratedMessageLite) this.filter_);
                                    this.filter_ = (FilterStatsProto) subBuilder.buildPartial();
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
                        synchronized (TopAlarm.class) {
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

        public static TopAlarm getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<TopAlarm> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class AlarmStat extends GeneratedMessageLite<AlarmStat, Builder> implements AlarmStatOrBuilder {
        public static final int BROADCAST_FIELD_NUMBER = 1;
        private static final AlarmStat DEFAULT_INSTANCE = new AlarmStat();
        public static final int FILTERS_FIELD_NUMBER = 2;
        private static volatile Parser<AlarmStat> PARSER;
        private int bitField0_;
        private BroadcastStatsProto broadcast_;
        private Internal.ProtobufList<FilterStatsProto> filters_ = emptyProtobufList();

        private AlarmStat() {
        }

        @Override // com.android.server.AlarmManagerServiceDumpProto.AlarmStatOrBuilder
        public boolean hasBroadcast() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // com.android.server.AlarmManagerServiceDumpProto.AlarmStatOrBuilder
        public BroadcastStatsProto getBroadcast() {
            BroadcastStatsProto broadcastStatsProto = this.broadcast_;
            return broadcastStatsProto == null ? BroadcastStatsProto.getDefaultInstance() : broadcastStatsProto;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setBroadcast(BroadcastStatsProto value) {
            if (value != null) {
                this.broadcast_ = value;
                this.bitField0_ |= 1;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setBroadcast(BroadcastStatsProto.Builder builderForValue) {
            this.broadcast_ = (BroadcastStatsProto) builderForValue.build();
            this.bitField0_ |= 1;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void mergeBroadcast(BroadcastStatsProto value) {
            BroadcastStatsProto broadcastStatsProto = this.broadcast_;
            if (broadcastStatsProto == null || broadcastStatsProto == BroadcastStatsProto.getDefaultInstance()) {
                this.broadcast_ = value;
            } else {
                this.broadcast_ = (BroadcastStatsProto) ((BroadcastStatsProto.Builder) BroadcastStatsProto.newBuilder(this.broadcast_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
            }
            this.bitField0_ |= 1;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearBroadcast() {
            this.broadcast_ = null;
            this.bitField0_ &= -2;
        }

        @Override // com.android.server.AlarmManagerServiceDumpProto.AlarmStatOrBuilder
        public List<FilterStatsProto> getFiltersList() {
            return this.filters_;
        }

        public List<? extends FilterStatsProtoOrBuilder> getFiltersOrBuilderList() {
            return this.filters_;
        }

        @Override // com.android.server.AlarmManagerServiceDumpProto.AlarmStatOrBuilder
        public int getFiltersCount() {
            return this.filters_.size();
        }

        @Override // com.android.server.AlarmManagerServiceDumpProto.AlarmStatOrBuilder
        public FilterStatsProto getFilters(int index) {
            return this.filters_.get(index);
        }

        public FilterStatsProtoOrBuilder getFiltersOrBuilder(int index) {
            return this.filters_.get(index);
        }

        private void ensureFiltersIsMutable() {
            if (!this.filters_.isModifiable()) {
                this.filters_ = GeneratedMessageLite.mutableCopy(this.filters_);
            }
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setFilters(int index, FilterStatsProto value) {
            if (value != null) {
                ensureFiltersIsMutable();
                this.filters_.set(index, value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setFilters(int index, FilterStatsProto.Builder builderForValue) {
            ensureFiltersIsMutable();
            this.filters_.set(index, (FilterStatsProto) builderForValue.build());
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addFilters(FilterStatsProto value) {
            if (value != null) {
                ensureFiltersIsMutable();
                this.filters_.add(value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addFilters(int index, FilterStatsProto value) {
            if (value != null) {
                ensureFiltersIsMutable();
                this.filters_.add(index, value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addFilters(FilterStatsProto.Builder builderForValue) {
            ensureFiltersIsMutable();
            this.filters_.add((FilterStatsProto) builderForValue.build());
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addFilters(int index, FilterStatsProto.Builder builderForValue) {
            ensureFiltersIsMutable();
            this.filters_.add(index, (FilterStatsProto) builderForValue.build());
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addAllFilters(Iterable<? extends FilterStatsProto> values) {
            ensureFiltersIsMutable();
            AbstractMessageLite.addAll(values, this.filters_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearFilters() {
            this.filters_ = emptyProtobufList();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void removeFilters(int index) {
            ensureFiltersIsMutable();
            this.filters_.remove(index);
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                output.writeMessage(1, getBroadcast());
            }
            for (int i = 0; i < this.filters_.size(); i++) {
                output.writeMessage(2, this.filters_.get(i));
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
                size2 = 0 + CodedOutputStream.computeMessageSize(1, getBroadcast());
            }
            for (int i = 0; i < this.filters_.size(); i++) {
                size2 += CodedOutputStream.computeMessageSize(2, this.filters_.get(i));
            }
            int size3 = size2 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static AlarmStat parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (AlarmStat) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static AlarmStat parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (AlarmStat) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static AlarmStat parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (AlarmStat) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static AlarmStat parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (AlarmStat) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static AlarmStat parseFrom(InputStream input) throws IOException {
            return (AlarmStat) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static AlarmStat parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (AlarmStat) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static AlarmStat parseDelimitedFrom(InputStream input) throws IOException {
            return (AlarmStat) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static AlarmStat parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (AlarmStat) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static AlarmStat parseFrom(CodedInputStream input) throws IOException {
            return (AlarmStat) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static AlarmStat parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (AlarmStat) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(AlarmStat prototype) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<AlarmStat, Builder> implements AlarmStatOrBuilder {
            private Builder() {
                super(AlarmStat.DEFAULT_INSTANCE);
            }

            @Override // com.android.server.AlarmManagerServiceDumpProto.AlarmStatOrBuilder
            public boolean hasBroadcast() {
                return ((AlarmStat) this.instance).hasBroadcast();
            }

            @Override // com.android.server.AlarmManagerServiceDumpProto.AlarmStatOrBuilder
            public BroadcastStatsProto getBroadcast() {
                return ((AlarmStat) this.instance).getBroadcast();
            }

            public Builder setBroadcast(BroadcastStatsProto value) {
                copyOnWrite();
                ((AlarmStat) this.instance).setBroadcast((AlarmStat) value);
                return this;
            }

            public Builder setBroadcast(BroadcastStatsProto.Builder builderForValue) {
                copyOnWrite();
                ((AlarmStat) this.instance).setBroadcast((AlarmStat) builderForValue);
                return this;
            }

            public Builder mergeBroadcast(BroadcastStatsProto value) {
                copyOnWrite();
                ((AlarmStat) this.instance).mergeBroadcast(value);
                return this;
            }

            public Builder clearBroadcast() {
                copyOnWrite();
                ((AlarmStat) this.instance).clearBroadcast();
                return this;
            }

            @Override // com.android.server.AlarmManagerServiceDumpProto.AlarmStatOrBuilder
            public List<FilterStatsProto> getFiltersList() {
                return Collections.unmodifiableList(((AlarmStat) this.instance).getFiltersList());
            }

            @Override // com.android.server.AlarmManagerServiceDumpProto.AlarmStatOrBuilder
            public int getFiltersCount() {
                return ((AlarmStat) this.instance).getFiltersCount();
            }

            @Override // com.android.server.AlarmManagerServiceDumpProto.AlarmStatOrBuilder
            public FilterStatsProto getFilters(int index) {
                return ((AlarmStat) this.instance).getFilters(index);
            }

            public Builder setFilters(int index, FilterStatsProto value) {
                copyOnWrite();
                ((AlarmStat) this.instance).setFilters((AlarmStat) index, (int) value);
                return this;
            }

            public Builder setFilters(int index, FilterStatsProto.Builder builderForValue) {
                copyOnWrite();
                ((AlarmStat) this.instance).setFilters((AlarmStat) index, (int) builderForValue);
                return this;
            }

            public Builder addFilters(FilterStatsProto value) {
                copyOnWrite();
                ((AlarmStat) this.instance).addFilters((AlarmStat) value);
                return this;
            }

            public Builder addFilters(int index, FilterStatsProto value) {
                copyOnWrite();
                ((AlarmStat) this.instance).addFilters((AlarmStat) index, (int) value);
                return this;
            }

            public Builder addFilters(FilterStatsProto.Builder builderForValue) {
                copyOnWrite();
                ((AlarmStat) this.instance).addFilters((AlarmStat) builderForValue);
                return this;
            }

            public Builder addFilters(int index, FilterStatsProto.Builder builderForValue) {
                copyOnWrite();
                ((AlarmStat) this.instance).addFilters((AlarmStat) index, (int) builderForValue);
                return this;
            }

            public Builder addAllFilters(Iterable<? extends FilterStatsProto> values) {
                copyOnWrite();
                ((AlarmStat) this.instance).addAllFilters(values);
                return this;
            }

            public Builder clearFilters() {
                copyOnWrite();
                ((AlarmStat) this.instance).clearFilters();
                return this;
            }

            public Builder removeFilters(int index) {
                copyOnWrite();
                ((AlarmStat) this.instance).removeFilters(index);
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new AlarmStat();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    this.filters_.makeImmutable();
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    AlarmStat other = (AlarmStat) arg1;
                    this.broadcast_ = (BroadcastStatsProto) visitor.visitMessage(this.broadcast_, other.broadcast_);
                    this.filters_ = visitor.visitList(this.filters_, other.filters_);
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
                                BroadcastStatsProto.Builder subBuilder = null;
                                if ((this.bitField0_ & 1) == 1) {
                                    subBuilder = (BroadcastStatsProto.Builder) this.broadcast_.toBuilder();
                                }
                                this.broadcast_ = (BroadcastStatsProto) input.readMessage(BroadcastStatsProto.parser(), extensionRegistry);
                                if (subBuilder != null) {
                                    subBuilder.mergeFrom((GeneratedMessageLite) this.broadcast_);
                                    this.broadcast_ = (BroadcastStatsProto) subBuilder.buildPartial();
                                }
                                this.bitField0_ |= 1;
                            } else if (tag == 18) {
                                if (!this.filters_.isModifiable()) {
                                    this.filters_ = GeneratedMessageLite.mutableCopy(this.filters_);
                                }
                                this.filters_.add((FilterStatsProto) input.readMessage(FilterStatsProto.parser(), extensionRegistry));
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
                        synchronized (AlarmStat.class) {
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

        public static AlarmStat getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<AlarmStat> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
    public boolean hasCurrentTime() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
    public long getCurrentTime() {
        return this.currentTime_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setCurrentTime(long value) {
        this.bitField0_ |= 1;
        this.currentTime_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearCurrentTime() {
        this.bitField0_ &= -2;
        this.currentTime_ = 0;
    }

    @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
    public boolean hasElapsedRealtime() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
    public long getElapsedRealtime() {
        return this.elapsedRealtime_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setElapsedRealtime(long value) {
        this.bitField0_ |= 2;
        this.elapsedRealtime_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearElapsedRealtime() {
        this.bitField0_ &= -3;
        this.elapsedRealtime_ = 0;
    }

    @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
    public boolean hasLastTimeChangeClockTime() {
        return (this.bitField0_ & 4) == 4;
    }

    @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
    public long getLastTimeChangeClockTime() {
        return this.lastTimeChangeClockTime_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setLastTimeChangeClockTime(long value) {
        this.bitField0_ |= 4;
        this.lastTimeChangeClockTime_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearLastTimeChangeClockTime() {
        this.bitField0_ &= -5;
        this.lastTimeChangeClockTime_ = 0;
    }

    @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
    public boolean hasLastTimeChangeRealtime() {
        return (this.bitField0_ & 8) == 8;
    }

    @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
    public long getLastTimeChangeRealtime() {
        return this.lastTimeChangeRealtime_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setLastTimeChangeRealtime(long value) {
        this.bitField0_ |= 8;
        this.lastTimeChangeRealtime_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearLastTimeChangeRealtime() {
        this.bitField0_ &= -9;
        this.lastTimeChangeRealtime_ = 0;
    }

    @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
    public boolean hasSettings() {
        return (this.bitField0_ & 16) == 16;
    }

    @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
    public ConstantsProto getSettings() {
        ConstantsProto constantsProto = this.settings_;
        return constantsProto == null ? ConstantsProto.getDefaultInstance() : constantsProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setSettings(ConstantsProto value) {
        if (value != null) {
            this.settings_ = value;
            this.bitField0_ |= 16;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setSettings(ConstantsProto.Builder builderForValue) {
        this.settings_ = (ConstantsProto) builderForValue.build();
        this.bitField0_ |= 16;
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
        this.bitField0_ |= 16;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearSettings() {
        this.settings_ = null;
        this.bitField0_ &= -17;
    }

    @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
    public boolean hasForceAppStandbyTracker() {
        return (this.bitField0_ & 32) == 32;
    }

    @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
    public ForceAppStandbyTrackerProto getForceAppStandbyTracker() {
        ForceAppStandbyTrackerProto forceAppStandbyTrackerProto = this.forceAppStandbyTracker_;
        return forceAppStandbyTrackerProto == null ? ForceAppStandbyTrackerProto.getDefaultInstance() : forceAppStandbyTrackerProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setForceAppStandbyTracker(ForceAppStandbyTrackerProto value) {
        if (value != null) {
            this.forceAppStandbyTracker_ = value;
            this.bitField0_ |= 32;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setForceAppStandbyTracker(ForceAppStandbyTrackerProto.Builder builderForValue) {
        this.forceAppStandbyTracker_ = (ForceAppStandbyTrackerProto) builderForValue.build();
        this.bitField0_ |= 32;
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
        this.bitField0_ |= 32;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearForceAppStandbyTracker() {
        this.forceAppStandbyTracker_ = null;
        this.bitField0_ &= -33;
    }

    @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
    public boolean hasIsInteractive() {
        return (this.bitField0_ & 64) == 64;
    }

    @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
    public boolean getIsInteractive() {
        return this.isInteractive_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setIsInteractive(boolean value) {
        this.bitField0_ |= 64;
        this.isInteractive_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearIsInteractive() {
        this.bitField0_ &= -65;
        this.isInteractive_ = false;
    }

    @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
    public boolean hasTimeSinceNonInteractiveMs() {
        return (this.bitField0_ & 128) == 128;
    }

    @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
    public long getTimeSinceNonInteractiveMs() {
        return this.timeSinceNonInteractiveMs_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setTimeSinceNonInteractiveMs(long value) {
        this.bitField0_ |= 128;
        this.timeSinceNonInteractiveMs_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearTimeSinceNonInteractiveMs() {
        this.bitField0_ &= -129;
        this.timeSinceNonInteractiveMs_ = 0;
    }

    @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
    public boolean hasMaxWakeupDelayMs() {
        return (this.bitField0_ & 256) == 256;
    }

    @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
    public long getMaxWakeupDelayMs() {
        return this.maxWakeupDelayMs_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setMaxWakeupDelayMs(long value) {
        this.bitField0_ |= 256;
        this.maxWakeupDelayMs_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearMaxWakeupDelayMs() {
        this.bitField0_ &= -257;
        this.maxWakeupDelayMs_ = 0;
    }

    @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
    public boolean hasTimeSinceLastDispatchMs() {
        return (this.bitField0_ & 512) == 512;
    }

    @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
    public long getTimeSinceLastDispatchMs() {
        return this.timeSinceLastDispatchMs_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setTimeSinceLastDispatchMs(long value) {
        this.bitField0_ |= 512;
        this.timeSinceLastDispatchMs_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearTimeSinceLastDispatchMs() {
        this.bitField0_ &= -513;
        this.timeSinceLastDispatchMs_ = 0;
    }

    @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
    public boolean hasTimeUntilNextNonWakeupDeliveryMs() {
        return (this.bitField0_ & 1024) == 1024;
    }

    @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
    public long getTimeUntilNextNonWakeupDeliveryMs() {
        return this.timeUntilNextNonWakeupDeliveryMs_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setTimeUntilNextNonWakeupDeliveryMs(long value) {
        this.bitField0_ |= 1024;
        this.timeUntilNextNonWakeupDeliveryMs_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearTimeUntilNextNonWakeupDeliveryMs() {
        this.bitField0_ &= -1025;
        this.timeUntilNextNonWakeupDeliveryMs_ = 0;
    }

    @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
    public boolean hasTimeUntilNextNonWakeupAlarmMs() {
        return (this.bitField0_ & 2048) == 2048;
    }

    @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
    public long getTimeUntilNextNonWakeupAlarmMs() {
        return this.timeUntilNextNonWakeupAlarmMs_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setTimeUntilNextNonWakeupAlarmMs(long value) {
        this.bitField0_ |= 2048;
        this.timeUntilNextNonWakeupAlarmMs_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearTimeUntilNextNonWakeupAlarmMs() {
        this.bitField0_ &= -2049;
        this.timeUntilNextNonWakeupAlarmMs_ = 0;
    }

    @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
    public boolean hasTimeUntilNextWakeupMs() {
        return (this.bitField0_ & 4096) == 4096;
    }

    @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
    public long getTimeUntilNextWakeupMs() {
        return this.timeUntilNextWakeupMs_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setTimeUntilNextWakeupMs(long value) {
        this.bitField0_ |= 4096;
        this.timeUntilNextWakeupMs_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearTimeUntilNextWakeupMs() {
        this.bitField0_ &= -4097;
        this.timeUntilNextWakeupMs_ = 0;
    }

    @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
    public boolean hasTimeSinceLastWakeupMs() {
        return (this.bitField0_ & 8192) == 8192;
    }

    @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
    public long getTimeSinceLastWakeupMs() {
        return this.timeSinceLastWakeupMs_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setTimeSinceLastWakeupMs(long value) {
        this.bitField0_ |= 8192;
        this.timeSinceLastWakeupMs_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearTimeSinceLastWakeupMs() {
        this.bitField0_ &= -8193;
        this.timeSinceLastWakeupMs_ = 0;
    }

    @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
    public boolean hasTimeSinceLastWakeupSetMs() {
        return (this.bitField0_ & 16384) == 16384;
    }

    @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
    public long getTimeSinceLastWakeupSetMs() {
        return this.timeSinceLastWakeupSetMs_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setTimeSinceLastWakeupSetMs(long value) {
        this.bitField0_ |= 16384;
        this.timeSinceLastWakeupSetMs_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearTimeSinceLastWakeupSetMs() {
        this.bitField0_ &= -16385;
        this.timeSinceLastWakeupSetMs_ = 0;
    }

    @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
    public boolean hasTimeChangeEventCount() {
        return (this.bitField0_ & 32768) == 32768;
    }

    @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
    public long getTimeChangeEventCount() {
        return this.timeChangeEventCount_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setTimeChangeEventCount(long value) {
        this.bitField0_ |= 32768;
        this.timeChangeEventCount_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearTimeChangeEventCount() {
        this.bitField0_ &= -32769;
        this.timeChangeEventCount_ = 0;
    }

    @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
    public List<Integer> getDeviceIdleUserWhitelistAppIdsList() {
        return this.deviceIdleUserWhitelistAppIds_;
    }

    @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
    public int getDeviceIdleUserWhitelistAppIdsCount() {
        return this.deviceIdleUserWhitelistAppIds_.size();
    }

    @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
    public int getDeviceIdleUserWhitelistAppIds(int index) {
        return this.deviceIdleUserWhitelistAppIds_.getInt(index);
    }

    private void ensureDeviceIdleUserWhitelistAppIdsIsMutable() {
        if (!this.deviceIdleUserWhitelistAppIds_.isModifiable()) {
            this.deviceIdleUserWhitelistAppIds_ = GeneratedMessageLite.mutableCopy(this.deviceIdleUserWhitelistAppIds_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setDeviceIdleUserWhitelistAppIds(int index, int value) {
        ensureDeviceIdleUserWhitelistAppIdsIsMutable();
        this.deviceIdleUserWhitelistAppIds_.setInt(index, value);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addDeviceIdleUserWhitelistAppIds(int value) {
        ensureDeviceIdleUserWhitelistAppIdsIsMutable();
        this.deviceIdleUserWhitelistAppIds_.addInt(value);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllDeviceIdleUserWhitelistAppIds(Iterable<? extends Integer> values) {
        ensureDeviceIdleUserWhitelistAppIdsIsMutable();
        AbstractMessageLite.addAll(values, this.deviceIdleUserWhitelistAppIds_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearDeviceIdleUserWhitelistAppIds() {
        this.deviceIdleUserWhitelistAppIds_ = emptyIntList();
    }

    @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
    public List<AlarmClockMetadataProto> getNextAlarmClockMetadataList() {
        return this.nextAlarmClockMetadata_;
    }

    public List<? extends AlarmClockMetadataProtoOrBuilder> getNextAlarmClockMetadataOrBuilderList() {
        return this.nextAlarmClockMetadata_;
    }

    @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
    public int getNextAlarmClockMetadataCount() {
        return this.nextAlarmClockMetadata_.size();
    }

    @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
    public AlarmClockMetadataProto getNextAlarmClockMetadata(int index) {
        return this.nextAlarmClockMetadata_.get(index);
    }

    public AlarmClockMetadataProtoOrBuilder getNextAlarmClockMetadataOrBuilder(int index) {
        return this.nextAlarmClockMetadata_.get(index);
    }

    private void ensureNextAlarmClockMetadataIsMutable() {
        if (!this.nextAlarmClockMetadata_.isModifiable()) {
            this.nextAlarmClockMetadata_ = GeneratedMessageLite.mutableCopy(this.nextAlarmClockMetadata_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setNextAlarmClockMetadata(int index, AlarmClockMetadataProto value) {
        if (value != null) {
            ensureNextAlarmClockMetadataIsMutable();
            this.nextAlarmClockMetadata_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setNextAlarmClockMetadata(int index, AlarmClockMetadataProto.Builder builderForValue) {
        ensureNextAlarmClockMetadataIsMutable();
        this.nextAlarmClockMetadata_.set(index, (AlarmClockMetadataProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addNextAlarmClockMetadata(AlarmClockMetadataProto value) {
        if (value != null) {
            ensureNextAlarmClockMetadataIsMutable();
            this.nextAlarmClockMetadata_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addNextAlarmClockMetadata(int index, AlarmClockMetadataProto value) {
        if (value != null) {
            ensureNextAlarmClockMetadataIsMutable();
            this.nextAlarmClockMetadata_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addNextAlarmClockMetadata(AlarmClockMetadataProto.Builder builderForValue) {
        ensureNextAlarmClockMetadataIsMutable();
        this.nextAlarmClockMetadata_.add((AlarmClockMetadataProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addNextAlarmClockMetadata(int index, AlarmClockMetadataProto.Builder builderForValue) {
        ensureNextAlarmClockMetadataIsMutable();
        this.nextAlarmClockMetadata_.add(index, (AlarmClockMetadataProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllNextAlarmClockMetadata(Iterable<? extends AlarmClockMetadataProto> values) {
        ensureNextAlarmClockMetadataIsMutable();
        AbstractMessageLite.addAll(values, this.nextAlarmClockMetadata_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearNextAlarmClockMetadata() {
        this.nextAlarmClockMetadata_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeNextAlarmClockMetadata(int index) {
        ensureNextAlarmClockMetadataIsMutable();
        this.nextAlarmClockMetadata_.remove(index);
    }

    @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
    public List<BatchProto> getPendingAlarmBatchesList() {
        return this.pendingAlarmBatches_;
    }

    public List<? extends BatchProtoOrBuilder> getPendingAlarmBatchesOrBuilderList() {
        return this.pendingAlarmBatches_;
    }

    @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
    public int getPendingAlarmBatchesCount() {
        return this.pendingAlarmBatches_.size();
    }

    @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
    public BatchProto getPendingAlarmBatches(int index) {
        return this.pendingAlarmBatches_.get(index);
    }

    public BatchProtoOrBuilder getPendingAlarmBatchesOrBuilder(int index) {
        return this.pendingAlarmBatches_.get(index);
    }

    private void ensurePendingAlarmBatchesIsMutable() {
        if (!this.pendingAlarmBatches_.isModifiable()) {
            this.pendingAlarmBatches_ = GeneratedMessageLite.mutableCopy(this.pendingAlarmBatches_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPendingAlarmBatches(int index, BatchProto value) {
        if (value != null) {
            ensurePendingAlarmBatchesIsMutable();
            this.pendingAlarmBatches_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPendingAlarmBatches(int index, BatchProto.Builder builderForValue) {
        ensurePendingAlarmBatchesIsMutable();
        this.pendingAlarmBatches_.set(index, (BatchProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addPendingAlarmBatches(BatchProto value) {
        if (value != null) {
            ensurePendingAlarmBatchesIsMutable();
            this.pendingAlarmBatches_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addPendingAlarmBatches(int index, BatchProto value) {
        if (value != null) {
            ensurePendingAlarmBatchesIsMutable();
            this.pendingAlarmBatches_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addPendingAlarmBatches(BatchProto.Builder builderForValue) {
        ensurePendingAlarmBatchesIsMutable();
        this.pendingAlarmBatches_.add((BatchProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addPendingAlarmBatches(int index, BatchProto.Builder builderForValue) {
        ensurePendingAlarmBatchesIsMutable();
        this.pendingAlarmBatches_.add(index, (BatchProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllPendingAlarmBatches(Iterable<? extends BatchProto> values) {
        ensurePendingAlarmBatchesIsMutable();
        AbstractMessageLite.addAll(values, this.pendingAlarmBatches_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearPendingAlarmBatches() {
        this.pendingAlarmBatches_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removePendingAlarmBatches(int index) {
        ensurePendingAlarmBatchesIsMutable();
        this.pendingAlarmBatches_.remove(index);
    }

    @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
    public List<AlarmProto> getPendingUserBlockedBackgroundAlarmsList() {
        return this.pendingUserBlockedBackgroundAlarms_;
    }

    public List<? extends AlarmProtoOrBuilder> getPendingUserBlockedBackgroundAlarmsOrBuilderList() {
        return this.pendingUserBlockedBackgroundAlarms_;
    }

    @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
    public int getPendingUserBlockedBackgroundAlarmsCount() {
        return this.pendingUserBlockedBackgroundAlarms_.size();
    }

    @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
    public AlarmProto getPendingUserBlockedBackgroundAlarms(int index) {
        return this.pendingUserBlockedBackgroundAlarms_.get(index);
    }

    public AlarmProtoOrBuilder getPendingUserBlockedBackgroundAlarmsOrBuilder(int index) {
        return this.pendingUserBlockedBackgroundAlarms_.get(index);
    }

    private void ensurePendingUserBlockedBackgroundAlarmsIsMutable() {
        if (!this.pendingUserBlockedBackgroundAlarms_.isModifiable()) {
            this.pendingUserBlockedBackgroundAlarms_ = GeneratedMessageLite.mutableCopy(this.pendingUserBlockedBackgroundAlarms_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPendingUserBlockedBackgroundAlarms(int index, AlarmProto value) {
        if (value != null) {
            ensurePendingUserBlockedBackgroundAlarmsIsMutable();
            this.pendingUserBlockedBackgroundAlarms_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPendingUserBlockedBackgroundAlarms(int index, AlarmProto.Builder builderForValue) {
        ensurePendingUserBlockedBackgroundAlarmsIsMutable();
        this.pendingUserBlockedBackgroundAlarms_.set(index, (AlarmProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addPendingUserBlockedBackgroundAlarms(AlarmProto value) {
        if (value != null) {
            ensurePendingUserBlockedBackgroundAlarmsIsMutable();
            this.pendingUserBlockedBackgroundAlarms_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addPendingUserBlockedBackgroundAlarms(int index, AlarmProto value) {
        if (value != null) {
            ensurePendingUserBlockedBackgroundAlarmsIsMutable();
            this.pendingUserBlockedBackgroundAlarms_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addPendingUserBlockedBackgroundAlarms(AlarmProto.Builder builderForValue) {
        ensurePendingUserBlockedBackgroundAlarmsIsMutable();
        this.pendingUserBlockedBackgroundAlarms_.add((AlarmProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addPendingUserBlockedBackgroundAlarms(int index, AlarmProto.Builder builderForValue) {
        ensurePendingUserBlockedBackgroundAlarmsIsMutable();
        this.pendingUserBlockedBackgroundAlarms_.add(index, (AlarmProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllPendingUserBlockedBackgroundAlarms(Iterable<? extends AlarmProto> values) {
        ensurePendingUserBlockedBackgroundAlarmsIsMutable();
        AbstractMessageLite.addAll(values, this.pendingUserBlockedBackgroundAlarms_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearPendingUserBlockedBackgroundAlarms() {
        this.pendingUserBlockedBackgroundAlarms_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removePendingUserBlockedBackgroundAlarms(int index) {
        ensurePendingUserBlockedBackgroundAlarmsIsMutable();
        this.pendingUserBlockedBackgroundAlarms_.remove(index);
    }

    @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
    public boolean hasPendingIdleUntil() {
        return (this.bitField0_ & 65536) == 65536;
    }

    @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
    public AlarmProto getPendingIdleUntil() {
        AlarmProto alarmProto = this.pendingIdleUntil_;
        return alarmProto == null ? AlarmProto.getDefaultInstance() : alarmProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPendingIdleUntil(AlarmProto value) {
        if (value != null) {
            this.pendingIdleUntil_ = value;
            this.bitField0_ |= 65536;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPendingIdleUntil(AlarmProto.Builder builderForValue) {
        this.pendingIdleUntil_ = (AlarmProto) builderForValue.build();
        this.bitField0_ |= 65536;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergePendingIdleUntil(AlarmProto value) {
        AlarmProto alarmProto = this.pendingIdleUntil_;
        if (alarmProto == null || alarmProto == AlarmProto.getDefaultInstance()) {
            this.pendingIdleUntil_ = value;
        } else {
            this.pendingIdleUntil_ = (AlarmProto) ((AlarmProto.Builder) AlarmProto.newBuilder(this.pendingIdleUntil_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 65536;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearPendingIdleUntil() {
        this.pendingIdleUntil_ = null;
        this.bitField0_ &= -65537;
    }

    @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
    public List<AlarmProto> getPendingWhileIdleAlarmsList() {
        return this.pendingWhileIdleAlarms_;
    }

    public List<? extends AlarmProtoOrBuilder> getPendingWhileIdleAlarmsOrBuilderList() {
        return this.pendingWhileIdleAlarms_;
    }

    @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
    public int getPendingWhileIdleAlarmsCount() {
        return this.pendingWhileIdleAlarms_.size();
    }

    @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
    public AlarmProto getPendingWhileIdleAlarms(int index) {
        return this.pendingWhileIdleAlarms_.get(index);
    }

    public AlarmProtoOrBuilder getPendingWhileIdleAlarmsOrBuilder(int index) {
        return this.pendingWhileIdleAlarms_.get(index);
    }

    private void ensurePendingWhileIdleAlarmsIsMutable() {
        if (!this.pendingWhileIdleAlarms_.isModifiable()) {
            this.pendingWhileIdleAlarms_ = GeneratedMessageLite.mutableCopy(this.pendingWhileIdleAlarms_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPendingWhileIdleAlarms(int index, AlarmProto value) {
        if (value != null) {
            ensurePendingWhileIdleAlarmsIsMutable();
            this.pendingWhileIdleAlarms_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPendingWhileIdleAlarms(int index, AlarmProto.Builder builderForValue) {
        ensurePendingWhileIdleAlarmsIsMutable();
        this.pendingWhileIdleAlarms_.set(index, (AlarmProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addPendingWhileIdleAlarms(AlarmProto value) {
        if (value != null) {
            ensurePendingWhileIdleAlarmsIsMutable();
            this.pendingWhileIdleAlarms_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addPendingWhileIdleAlarms(int index, AlarmProto value) {
        if (value != null) {
            ensurePendingWhileIdleAlarmsIsMutable();
            this.pendingWhileIdleAlarms_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addPendingWhileIdleAlarms(AlarmProto.Builder builderForValue) {
        ensurePendingWhileIdleAlarmsIsMutable();
        this.pendingWhileIdleAlarms_.add((AlarmProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addPendingWhileIdleAlarms(int index, AlarmProto.Builder builderForValue) {
        ensurePendingWhileIdleAlarmsIsMutable();
        this.pendingWhileIdleAlarms_.add(index, (AlarmProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllPendingWhileIdleAlarms(Iterable<? extends AlarmProto> values) {
        ensurePendingWhileIdleAlarmsIsMutable();
        AbstractMessageLite.addAll(values, this.pendingWhileIdleAlarms_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearPendingWhileIdleAlarms() {
        this.pendingWhileIdleAlarms_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removePendingWhileIdleAlarms(int index) {
        ensurePendingWhileIdleAlarmsIsMutable();
        this.pendingWhileIdleAlarms_.remove(index);
    }

    @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
    public boolean hasNextWakeFromIdle() {
        return (this.bitField0_ & 131072) == 131072;
    }

    @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
    public AlarmProto getNextWakeFromIdle() {
        AlarmProto alarmProto = this.nextWakeFromIdle_;
        return alarmProto == null ? AlarmProto.getDefaultInstance() : alarmProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setNextWakeFromIdle(AlarmProto value) {
        if (value != null) {
            this.nextWakeFromIdle_ = value;
            this.bitField0_ |= 131072;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setNextWakeFromIdle(AlarmProto.Builder builderForValue) {
        this.nextWakeFromIdle_ = (AlarmProto) builderForValue.build();
        this.bitField0_ |= 131072;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeNextWakeFromIdle(AlarmProto value) {
        AlarmProto alarmProto = this.nextWakeFromIdle_;
        if (alarmProto == null || alarmProto == AlarmProto.getDefaultInstance()) {
            this.nextWakeFromIdle_ = value;
        } else {
            this.nextWakeFromIdle_ = (AlarmProto) ((AlarmProto.Builder) AlarmProto.newBuilder(this.nextWakeFromIdle_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 131072;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearNextWakeFromIdle() {
        this.nextWakeFromIdle_ = null;
        this.bitField0_ &= -131073;
    }

    @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
    public List<AlarmProto> getPastDueNonWakeupAlarmsList() {
        return this.pastDueNonWakeupAlarms_;
    }

    public List<? extends AlarmProtoOrBuilder> getPastDueNonWakeupAlarmsOrBuilderList() {
        return this.pastDueNonWakeupAlarms_;
    }

    @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
    public int getPastDueNonWakeupAlarmsCount() {
        return this.pastDueNonWakeupAlarms_.size();
    }

    @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
    public AlarmProto getPastDueNonWakeupAlarms(int index) {
        return this.pastDueNonWakeupAlarms_.get(index);
    }

    public AlarmProtoOrBuilder getPastDueNonWakeupAlarmsOrBuilder(int index) {
        return this.pastDueNonWakeupAlarms_.get(index);
    }

    private void ensurePastDueNonWakeupAlarmsIsMutable() {
        if (!this.pastDueNonWakeupAlarms_.isModifiable()) {
            this.pastDueNonWakeupAlarms_ = GeneratedMessageLite.mutableCopy(this.pastDueNonWakeupAlarms_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPastDueNonWakeupAlarms(int index, AlarmProto value) {
        if (value != null) {
            ensurePastDueNonWakeupAlarmsIsMutable();
            this.pastDueNonWakeupAlarms_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPastDueNonWakeupAlarms(int index, AlarmProto.Builder builderForValue) {
        ensurePastDueNonWakeupAlarmsIsMutable();
        this.pastDueNonWakeupAlarms_.set(index, (AlarmProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addPastDueNonWakeupAlarms(AlarmProto value) {
        if (value != null) {
            ensurePastDueNonWakeupAlarmsIsMutable();
            this.pastDueNonWakeupAlarms_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addPastDueNonWakeupAlarms(int index, AlarmProto value) {
        if (value != null) {
            ensurePastDueNonWakeupAlarmsIsMutable();
            this.pastDueNonWakeupAlarms_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addPastDueNonWakeupAlarms(AlarmProto.Builder builderForValue) {
        ensurePastDueNonWakeupAlarmsIsMutable();
        this.pastDueNonWakeupAlarms_.add((AlarmProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addPastDueNonWakeupAlarms(int index, AlarmProto.Builder builderForValue) {
        ensurePastDueNonWakeupAlarmsIsMutable();
        this.pastDueNonWakeupAlarms_.add(index, (AlarmProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllPastDueNonWakeupAlarms(Iterable<? extends AlarmProto> values) {
        ensurePastDueNonWakeupAlarmsIsMutable();
        AbstractMessageLite.addAll(values, this.pastDueNonWakeupAlarms_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearPastDueNonWakeupAlarms() {
        this.pastDueNonWakeupAlarms_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removePastDueNonWakeupAlarms(int index) {
        ensurePastDueNonWakeupAlarmsIsMutable();
        this.pastDueNonWakeupAlarms_.remove(index);
    }

    @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
    public boolean hasDelayedAlarmCount() {
        return (this.bitField0_ & 262144) == 262144;
    }

    @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
    public int getDelayedAlarmCount() {
        return this.delayedAlarmCount_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setDelayedAlarmCount(int value) {
        this.bitField0_ |= 262144;
        this.delayedAlarmCount_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearDelayedAlarmCount() {
        this.bitField0_ &= -262145;
        this.delayedAlarmCount_ = 0;
    }

    @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
    public boolean hasTotalDelayTimeMs() {
        return (this.bitField0_ & 524288) == 524288;
    }

    @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
    public long getTotalDelayTimeMs() {
        return this.totalDelayTimeMs_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setTotalDelayTimeMs(long value) {
        this.bitField0_ |= 524288;
        this.totalDelayTimeMs_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearTotalDelayTimeMs() {
        this.bitField0_ &= -524289;
        this.totalDelayTimeMs_ = 0;
    }

    @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
    public boolean hasMaxDelayDurationMs() {
        return (this.bitField0_ & 1048576) == 1048576;
    }

    @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
    public long getMaxDelayDurationMs() {
        return this.maxDelayDurationMs_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setMaxDelayDurationMs(long value) {
        this.bitField0_ |= 1048576;
        this.maxDelayDurationMs_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearMaxDelayDurationMs() {
        this.bitField0_ &= -1048577;
        this.maxDelayDurationMs_ = 0;
    }

    @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
    public boolean hasMaxNonInteractiveDurationMs() {
        return (this.bitField0_ & 2097152) == 2097152;
    }

    @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
    public long getMaxNonInteractiveDurationMs() {
        return this.maxNonInteractiveDurationMs_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setMaxNonInteractiveDurationMs(long value) {
        this.bitField0_ |= 2097152;
        this.maxNonInteractiveDurationMs_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearMaxNonInteractiveDurationMs() {
        this.bitField0_ &= -2097153;
        this.maxNonInteractiveDurationMs_ = 0;
    }

    @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
    public boolean hasBroadcastRefCount() {
        return (this.bitField0_ & 4194304) == 4194304;
    }

    @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
    public int getBroadcastRefCount() {
        return this.broadcastRefCount_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setBroadcastRefCount(int value) {
        this.bitField0_ |= 4194304;
        this.broadcastRefCount_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearBroadcastRefCount() {
        this.bitField0_ &= -4194305;
        this.broadcastRefCount_ = 0;
    }

    @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
    public boolean hasPendingIntentSendCount() {
        return (this.bitField0_ & 8388608) == 8388608;
    }

    @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
    public int getPendingIntentSendCount() {
        return this.pendingIntentSendCount_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPendingIntentSendCount(int value) {
        this.bitField0_ |= 8388608;
        this.pendingIntentSendCount_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearPendingIntentSendCount() {
        this.bitField0_ &= -8388609;
        this.pendingIntentSendCount_ = 0;
    }

    @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
    public boolean hasPendingIntentFinishCount() {
        return (this.bitField0_ & 16777216) == 16777216;
    }

    @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
    public int getPendingIntentFinishCount() {
        return this.pendingIntentFinishCount_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPendingIntentFinishCount(int value) {
        this.bitField0_ |= 16777216;
        this.pendingIntentFinishCount_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearPendingIntentFinishCount() {
        this.bitField0_ &= -16777217;
        this.pendingIntentFinishCount_ = 0;
    }

    @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
    public boolean hasListenerSendCount() {
        return (this.bitField0_ & 33554432) == 33554432;
    }

    @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
    public int getListenerSendCount() {
        return this.listenerSendCount_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setListenerSendCount(int value) {
        this.bitField0_ |= 33554432;
        this.listenerSendCount_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearListenerSendCount() {
        this.bitField0_ &= -33554433;
        this.listenerSendCount_ = 0;
    }

    @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
    public boolean hasListenerFinishCount() {
        return (this.bitField0_ & 67108864) == 67108864;
    }

    @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
    public int getListenerFinishCount() {
        return this.listenerFinishCount_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setListenerFinishCount(int value) {
        this.bitField0_ |= 67108864;
        this.listenerFinishCount_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearListenerFinishCount() {
        this.bitField0_ &= -67108865;
        this.listenerFinishCount_ = 0;
    }

    @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
    public List<InFlightProto> getOutstandingDeliveriesList() {
        return this.outstandingDeliveries_;
    }

    public List<? extends InFlightProtoOrBuilder> getOutstandingDeliveriesOrBuilderList() {
        return this.outstandingDeliveries_;
    }

    @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
    public int getOutstandingDeliveriesCount() {
        return this.outstandingDeliveries_.size();
    }

    @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
    public InFlightProto getOutstandingDeliveries(int index) {
        return this.outstandingDeliveries_.get(index);
    }

    public InFlightProtoOrBuilder getOutstandingDeliveriesOrBuilder(int index) {
        return this.outstandingDeliveries_.get(index);
    }

    private void ensureOutstandingDeliveriesIsMutable() {
        if (!this.outstandingDeliveries_.isModifiable()) {
            this.outstandingDeliveries_ = GeneratedMessageLite.mutableCopy(this.outstandingDeliveries_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setOutstandingDeliveries(int index, InFlightProto value) {
        if (value != null) {
            ensureOutstandingDeliveriesIsMutable();
            this.outstandingDeliveries_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setOutstandingDeliveries(int index, InFlightProto.Builder builderForValue) {
        ensureOutstandingDeliveriesIsMutable();
        this.outstandingDeliveries_.set(index, (InFlightProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addOutstandingDeliveries(InFlightProto value) {
        if (value != null) {
            ensureOutstandingDeliveriesIsMutable();
            this.outstandingDeliveries_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addOutstandingDeliveries(int index, InFlightProto value) {
        if (value != null) {
            ensureOutstandingDeliveriesIsMutable();
            this.outstandingDeliveries_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addOutstandingDeliveries(InFlightProto.Builder builderForValue) {
        ensureOutstandingDeliveriesIsMutable();
        this.outstandingDeliveries_.add((InFlightProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addOutstandingDeliveries(int index, InFlightProto.Builder builderForValue) {
        ensureOutstandingDeliveriesIsMutable();
        this.outstandingDeliveries_.add(index, (InFlightProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllOutstandingDeliveries(Iterable<? extends InFlightProto> values) {
        ensureOutstandingDeliveriesIsMutable();
        AbstractMessageLite.addAll(values, this.outstandingDeliveries_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearOutstandingDeliveries() {
        this.outstandingDeliveries_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeOutstandingDeliveries(int index) {
        ensureOutstandingDeliveriesIsMutable();
        this.outstandingDeliveries_.remove(index);
    }

    @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
    public List<Integer> getUseAllowWhileIdleShortTimeList() {
        return this.useAllowWhileIdleShortTime_;
    }

    @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
    public int getUseAllowWhileIdleShortTimeCount() {
        return this.useAllowWhileIdleShortTime_.size();
    }

    @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
    public int getUseAllowWhileIdleShortTime(int index) {
        return this.useAllowWhileIdleShortTime_.getInt(index);
    }

    private void ensureUseAllowWhileIdleShortTimeIsMutable() {
        if (!this.useAllowWhileIdleShortTime_.isModifiable()) {
            this.useAllowWhileIdleShortTime_ = GeneratedMessageLite.mutableCopy(this.useAllowWhileIdleShortTime_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setUseAllowWhileIdleShortTime(int index, int value) {
        ensureUseAllowWhileIdleShortTimeIsMutable();
        this.useAllowWhileIdleShortTime_.setInt(index, value);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addUseAllowWhileIdleShortTime(int value) {
        ensureUseAllowWhileIdleShortTimeIsMutable();
        this.useAllowWhileIdleShortTime_.addInt(value);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllUseAllowWhileIdleShortTime(Iterable<? extends Integer> values) {
        ensureUseAllowWhileIdleShortTimeIsMutable();
        AbstractMessageLite.addAll(values, this.useAllowWhileIdleShortTime_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearUseAllowWhileIdleShortTime() {
        this.useAllowWhileIdleShortTime_ = emptyIntList();
    }

    @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
    public List<LastAllowWhileIdleDispatch> getLastAllowWhileIdleDispatchTimesList() {
        return this.lastAllowWhileIdleDispatchTimes_;
    }

    public List<? extends LastAllowWhileIdleDispatchOrBuilder> getLastAllowWhileIdleDispatchTimesOrBuilderList() {
        return this.lastAllowWhileIdleDispatchTimes_;
    }

    @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
    public int getLastAllowWhileIdleDispatchTimesCount() {
        return this.lastAllowWhileIdleDispatchTimes_.size();
    }

    @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
    public LastAllowWhileIdleDispatch getLastAllowWhileIdleDispatchTimes(int index) {
        return this.lastAllowWhileIdleDispatchTimes_.get(index);
    }

    public LastAllowWhileIdleDispatchOrBuilder getLastAllowWhileIdleDispatchTimesOrBuilder(int index) {
        return this.lastAllowWhileIdleDispatchTimes_.get(index);
    }

    private void ensureLastAllowWhileIdleDispatchTimesIsMutable() {
        if (!this.lastAllowWhileIdleDispatchTimes_.isModifiable()) {
            this.lastAllowWhileIdleDispatchTimes_ = GeneratedMessageLite.mutableCopy(this.lastAllowWhileIdleDispatchTimes_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setLastAllowWhileIdleDispatchTimes(int index, LastAllowWhileIdleDispatch value) {
        if (value != null) {
            ensureLastAllowWhileIdleDispatchTimesIsMutable();
            this.lastAllowWhileIdleDispatchTimes_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setLastAllowWhileIdleDispatchTimes(int index, LastAllowWhileIdleDispatch.Builder builderForValue) {
        ensureLastAllowWhileIdleDispatchTimesIsMutable();
        this.lastAllowWhileIdleDispatchTimes_.set(index, (LastAllowWhileIdleDispatch) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addLastAllowWhileIdleDispatchTimes(LastAllowWhileIdleDispatch value) {
        if (value != null) {
            ensureLastAllowWhileIdleDispatchTimesIsMutable();
            this.lastAllowWhileIdleDispatchTimes_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addLastAllowWhileIdleDispatchTimes(int index, LastAllowWhileIdleDispatch value) {
        if (value != null) {
            ensureLastAllowWhileIdleDispatchTimesIsMutable();
            this.lastAllowWhileIdleDispatchTimes_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addLastAllowWhileIdleDispatchTimes(LastAllowWhileIdleDispatch.Builder builderForValue) {
        ensureLastAllowWhileIdleDispatchTimesIsMutable();
        this.lastAllowWhileIdleDispatchTimes_.add((LastAllowWhileIdleDispatch) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addLastAllowWhileIdleDispatchTimes(int index, LastAllowWhileIdleDispatch.Builder builderForValue) {
        ensureLastAllowWhileIdleDispatchTimesIsMutable();
        this.lastAllowWhileIdleDispatchTimes_.add(index, (LastAllowWhileIdleDispatch) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllLastAllowWhileIdleDispatchTimes(Iterable<? extends LastAllowWhileIdleDispatch> values) {
        ensureLastAllowWhileIdleDispatchTimesIsMutable();
        AbstractMessageLite.addAll(values, this.lastAllowWhileIdleDispatchTimes_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearLastAllowWhileIdleDispatchTimes() {
        this.lastAllowWhileIdleDispatchTimes_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeLastAllowWhileIdleDispatchTimes(int index) {
        ensureLastAllowWhileIdleDispatchTimesIsMutable();
        this.lastAllowWhileIdleDispatchTimes_.remove(index);
    }

    @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
    public boolean hasRecentProblems() {
        return (this.bitField0_ & 134217728) == 134217728;
    }

    @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
    public LocalLogProto getRecentProblems() {
        LocalLogProto localLogProto = this.recentProblems_;
        return localLogProto == null ? LocalLogProto.getDefaultInstance() : localLogProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setRecentProblems(LocalLogProto value) {
        if (value != null) {
            this.recentProblems_ = value;
            this.bitField0_ |= 134217728;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setRecentProblems(LocalLogProto.Builder builderForValue) {
        this.recentProblems_ = (LocalLogProto) builderForValue.build();
        this.bitField0_ |= 134217728;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeRecentProblems(LocalLogProto value) {
        LocalLogProto localLogProto = this.recentProblems_;
        if (localLogProto == null || localLogProto == LocalLogProto.getDefaultInstance()) {
            this.recentProblems_ = value;
        } else {
            this.recentProblems_ = (LocalLogProto) ((LocalLogProto.Builder) LocalLogProto.newBuilder(this.recentProblems_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 134217728;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearRecentProblems() {
        this.recentProblems_ = null;
        this.bitField0_ &= -134217729;
    }

    @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
    public List<TopAlarm> getTopAlarmsList() {
        return this.topAlarms_;
    }

    public List<? extends TopAlarmOrBuilder> getTopAlarmsOrBuilderList() {
        return this.topAlarms_;
    }

    @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
    public int getTopAlarmsCount() {
        return this.topAlarms_.size();
    }

    @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
    public TopAlarm getTopAlarms(int index) {
        return this.topAlarms_.get(index);
    }

    public TopAlarmOrBuilder getTopAlarmsOrBuilder(int index) {
        return this.topAlarms_.get(index);
    }

    private void ensureTopAlarmsIsMutable() {
        if (!this.topAlarms_.isModifiable()) {
            this.topAlarms_ = GeneratedMessageLite.mutableCopy(this.topAlarms_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setTopAlarms(int index, TopAlarm value) {
        if (value != null) {
            ensureTopAlarmsIsMutable();
            this.topAlarms_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setTopAlarms(int index, TopAlarm.Builder builderForValue) {
        ensureTopAlarmsIsMutable();
        this.topAlarms_.set(index, (TopAlarm) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addTopAlarms(TopAlarm value) {
        if (value != null) {
            ensureTopAlarmsIsMutable();
            this.topAlarms_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addTopAlarms(int index, TopAlarm value) {
        if (value != null) {
            ensureTopAlarmsIsMutable();
            this.topAlarms_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addTopAlarms(TopAlarm.Builder builderForValue) {
        ensureTopAlarmsIsMutable();
        this.topAlarms_.add((TopAlarm) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addTopAlarms(int index, TopAlarm.Builder builderForValue) {
        ensureTopAlarmsIsMutable();
        this.topAlarms_.add(index, (TopAlarm) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllTopAlarms(Iterable<? extends TopAlarm> values) {
        ensureTopAlarmsIsMutable();
        AbstractMessageLite.addAll(values, this.topAlarms_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearTopAlarms() {
        this.topAlarms_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeTopAlarms(int index) {
        ensureTopAlarmsIsMutable();
        this.topAlarms_.remove(index);
    }

    @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
    public List<AlarmStat> getAlarmStatsList() {
        return this.alarmStats_;
    }

    public List<? extends AlarmStatOrBuilder> getAlarmStatsOrBuilderList() {
        return this.alarmStats_;
    }

    @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
    public int getAlarmStatsCount() {
        return this.alarmStats_.size();
    }

    @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
    public AlarmStat getAlarmStats(int index) {
        return this.alarmStats_.get(index);
    }

    public AlarmStatOrBuilder getAlarmStatsOrBuilder(int index) {
        return this.alarmStats_.get(index);
    }

    private void ensureAlarmStatsIsMutable() {
        if (!this.alarmStats_.isModifiable()) {
            this.alarmStats_ = GeneratedMessageLite.mutableCopy(this.alarmStats_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setAlarmStats(int index, AlarmStat value) {
        if (value != null) {
            ensureAlarmStatsIsMutable();
            this.alarmStats_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setAlarmStats(int index, AlarmStat.Builder builderForValue) {
        ensureAlarmStatsIsMutable();
        this.alarmStats_.set(index, (AlarmStat) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAlarmStats(AlarmStat value) {
        if (value != null) {
            ensureAlarmStatsIsMutable();
            this.alarmStats_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAlarmStats(int index, AlarmStat value) {
        if (value != null) {
            ensureAlarmStatsIsMutable();
            this.alarmStats_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAlarmStats(AlarmStat.Builder builderForValue) {
        ensureAlarmStatsIsMutable();
        this.alarmStats_.add((AlarmStat) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAlarmStats(int index, AlarmStat.Builder builderForValue) {
        ensureAlarmStatsIsMutable();
        this.alarmStats_.add(index, (AlarmStat) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllAlarmStats(Iterable<? extends AlarmStat> values) {
        ensureAlarmStatsIsMutable();
        AbstractMessageLite.addAll(values, this.alarmStats_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearAlarmStats() {
        this.alarmStats_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeAlarmStats(int index) {
        ensureAlarmStatsIsMutable();
        this.alarmStats_.remove(index);
    }

    @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
    public List<IdleDispatchEntryProto> getAllowWhileIdleDispatchesList() {
        return this.allowWhileIdleDispatches_;
    }

    public List<? extends IdleDispatchEntryProtoOrBuilder> getAllowWhileIdleDispatchesOrBuilderList() {
        return this.allowWhileIdleDispatches_;
    }

    @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
    public int getAllowWhileIdleDispatchesCount() {
        return this.allowWhileIdleDispatches_.size();
    }

    @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
    public IdleDispatchEntryProto getAllowWhileIdleDispatches(int index) {
        return this.allowWhileIdleDispatches_.get(index);
    }

    public IdleDispatchEntryProtoOrBuilder getAllowWhileIdleDispatchesOrBuilder(int index) {
        return this.allowWhileIdleDispatches_.get(index);
    }

    private void ensureAllowWhileIdleDispatchesIsMutable() {
        if (!this.allowWhileIdleDispatches_.isModifiable()) {
            this.allowWhileIdleDispatches_ = GeneratedMessageLite.mutableCopy(this.allowWhileIdleDispatches_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setAllowWhileIdleDispatches(int index, IdleDispatchEntryProto value) {
        if (value != null) {
            ensureAllowWhileIdleDispatchesIsMutable();
            this.allowWhileIdleDispatches_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setAllowWhileIdleDispatches(int index, IdleDispatchEntryProto.Builder builderForValue) {
        ensureAllowWhileIdleDispatchesIsMutable();
        this.allowWhileIdleDispatches_.set(index, (IdleDispatchEntryProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllowWhileIdleDispatches(IdleDispatchEntryProto value) {
        if (value != null) {
            ensureAllowWhileIdleDispatchesIsMutable();
            this.allowWhileIdleDispatches_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllowWhileIdleDispatches(int index, IdleDispatchEntryProto value) {
        if (value != null) {
            ensureAllowWhileIdleDispatchesIsMutable();
            this.allowWhileIdleDispatches_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllowWhileIdleDispatches(IdleDispatchEntryProto.Builder builderForValue) {
        ensureAllowWhileIdleDispatchesIsMutable();
        this.allowWhileIdleDispatches_.add((IdleDispatchEntryProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllowWhileIdleDispatches(int index, IdleDispatchEntryProto.Builder builderForValue) {
        ensureAllowWhileIdleDispatchesIsMutable();
        this.allowWhileIdleDispatches_.add(index, (IdleDispatchEntryProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllAllowWhileIdleDispatches(Iterable<? extends IdleDispatchEntryProto> values) {
        ensureAllowWhileIdleDispatchesIsMutable();
        AbstractMessageLite.addAll(values, this.allowWhileIdleDispatches_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearAllowWhileIdleDispatches() {
        this.allowWhileIdleDispatches_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeAllowWhileIdleDispatches(int index) {
        ensureAllowWhileIdleDispatchesIsMutable();
        this.allowWhileIdleDispatches_.remove(index);
    }

    @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
    public List<WakeupEventProto> getRecentWakeupHistoryList() {
        return this.recentWakeupHistory_;
    }

    public List<? extends WakeupEventProtoOrBuilder> getRecentWakeupHistoryOrBuilderList() {
        return this.recentWakeupHistory_;
    }

    @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
    public int getRecentWakeupHistoryCount() {
        return this.recentWakeupHistory_.size();
    }

    @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
    public WakeupEventProto getRecentWakeupHistory(int index) {
        return this.recentWakeupHistory_.get(index);
    }

    public WakeupEventProtoOrBuilder getRecentWakeupHistoryOrBuilder(int index) {
        return this.recentWakeupHistory_.get(index);
    }

    private void ensureRecentWakeupHistoryIsMutable() {
        if (!this.recentWakeupHistory_.isModifiable()) {
            this.recentWakeupHistory_ = GeneratedMessageLite.mutableCopy(this.recentWakeupHistory_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setRecentWakeupHistory(int index, WakeupEventProto value) {
        if (value != null) {
            ensureRecentWakeupHistoryIsMutable();
            this.recentWakeupHistory_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setRecentWakeupHistory(int index, WakeupEventProto.Builder builderForValue) {
        ensureRecentWakeupHistoryIsMutable();
        this.recentWakeupHistory_.set(index, (WakeupEventProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addRecentWakeupHistory(WakeupEventProto value) {
        if (value != null) {
            ensureRecentWakeupHistoryIsMutable();
            this.recentWakeupHistory_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addRecentWakeupHistory(int index, WakeupEventProto value) {
        if (value != null) {
            ensureRecentWakeupHistoryIsMutable();
            this.recentWakeupHistory_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addRecentWakeupHistory(WakeupEventProto.Builder builderForValue) {
        ensureRecentWakeupHistoryIsMutable();
        this.recentWakeupHistory_.add((WakeupEventProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addRecentWakeupHistory(int index, WakeupEventProto.Builder builderForValue) {
        ensureRecentWakeupHistoryIsMutable();
        this.recentWakeupHistory_.add(index, (WakeupEventProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllRecentWakeupHistory(Iterable<? extends WakeupEventProto> values) {
        ensureRecentWakeupHistoryIsMutable();
        AbstractMessageLite.addAll(values, this.recentWakeupHistory_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearRecentWakeupHistory() {
        this.recentWakeupHistory_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeRecentWakeupHistory(int index) {
        ensureRecentWakeupHistoryIsMutable();
        this.recentWakeupHistory_.remove(index);
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeInt64(1, this.currentTime_);
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeInt64(2, this.elapsedRealtime_);
        }
        if ((this.bitField0_ & 4) == 4) {
            output.writeInt64(3, this.lastTimeChangeClockTime_);
        }
        if ((this.bitField0_ & 8) == 8) {
            output.writeInt64(4, this.lastTimeChangeRealtime_);
        }
        if ((this.bitField0_ & 16) == 16) {
            output.writeMessage(5, getSettings());
        }
        if ((this.bitField0_ & 32) == 32) {
            output.writeMessage(6, getForceAppStandbyTracker());
        }
        if ((this.bitField0_ & 64) == 64) {
            output.writeBool(7, this.isInteractive_);
        }
        if ((this.bitField0_ & 128) == 128) {
            output.writeInt64(8, this.timeSinceNonInteractiveMs_);
        }
        if ((this.bitField0_ & 256) == 256) {
            output.writeInt64(9, this.maxWakeupDelayMs_);
        }
        if ((this.bitField0_ & 512) == 512) {
            output.writeInt64(10, this.timeSinceLastDispatchMs_);
        }
        if ((this.bitField0_ & 1024) == 1024) {
            output.writeInt64(11, this.timeUntilNextNonWakeupDeliveryMs_);
        }
        if ((this.bitField0_ & 2048) == 2048) {
            output.writeInt64(12, this.timeUntilNextNonWakeupAlarmMs_);
        }
        if ((this.bitField0_ & 4096) == 4096) {
            output.writeInt64(13, this.timeUntilNextWakeupMs_);
        }
        if ((this.bitField0_ & 8192) == 8192) {
            output.writeInt64(14, this.timeSinceLastWakeupMs_);
        }
        if ((this.bitField0_ & 16384) == 16384) {
            output.writeInt64(15, this.timeSinceLastWakeupSetMs_);
        }
        if ((this.bitField0_ & 32768) == 32768) {
            output.writeInt64(16, this.timeChangeEventCount_);
        }
        for (int i = 0; i < this.deviceIdleUserWhitelistAppIds_.size(); i++) {
            output.writeInt32(17, this.deviceIdleUserWhitelistAppIds_.getInt(i));
        }
        for (int i2 = 0; i2 < this.nextAlarmClockMetadata_.size(); i2++) {
            output.writeMessage(18, this.nextAlarmClockMetadata_.get(i2));
        }
        for (int i3 = 0; i3 < this.pendingAlarmBatches_.size(); i3++) {
            output.writeMessage(19, this.pendingAlarmBatches_.get(i3));
        }
        for (int i4 = 0; i4 < this.pendingUserBlockedBackgroundAlarms_.size(); i4++) {
            output.writeMessage(20, this.pendingUserBlockedBackgroundAlarms_.get(i4));
        }
        if ((this.bitField0_ & 65536) == 65536) {
            output.writeMessage(21, getPendingIdleUntil());
        }
        for (int i5 = 0; i5 < this.pendingWhileIdleAlarms_.size(); i5++) {
            output.writeMessage(22, this.pendingWhileIdleAlarms_.get(i5));
        }
        if ((this.bitField0_ & 131072) == 131072) {
            output.writeMessage(23, getNextWakeFromIdle());
        }
        for (int i6 = 0; i6 < this.pastDueNonWakeupAlarms_.size(); i6++) {
            output.writeMessage(24, this.pastDueNonWakeupAlarms_.get(i6));
        }
        if ((this.bitField0_ & 262144) == 262144) {
            output.writeInt32(25, this.delayedAlarmCount_);
        }
        if ((this.bitField0_ & 524288) == 524288) {
            output.writeInt64(26, this.totalDelayTimeMs_);
        }
        if ((this.bitField0_ & 1048576) == 1048576) {
            output.writeInt64(27, this.maxDelayDurationMs_);
        }
        if ((this.bitField0_ & 2097152) == 2097152) {
            output.writeInt64(28, this.maxNonInteractiveDurationMs_);
        }
        if ((this.bitField0_ & 4194304) == 4194304) {
            output.writeInt32(29, this.broadcastRefCount_);
        }
        if ((this.bitField0_ & 8388608) == 8388608) {
            output.writeInt32(30, this.pendingIntentSendCount_);
        }
        if ((this.bitField0_ & 16777216) == 16777216) {
            output.writeInt32(31, this.pendingIntentFinishCount_);
        }
        if ((this.bitField0_ & 33554432) == 33554432) {
            output.writeInt32(32, this.listenerSendCount_);
        }
        if ((this.bitField0_ & 67108864) == 67108864) {
            output.writeInt32(33, this.listenerFinishCount_);
        }
        for (int i7 = 0; i7 < this.outstandingDeliveries_.size(); i7++) {
            output.writeMessage(34, this.outstandingDeliveries_.get(i7));
        }
        for (int i8 = 0; i8 < this.useAllowWhileIdleShortTime_.size(); i8++) {
            output.writeInt32(35, this.useAllowWhileIdleShortTime_.getInt(i8));
        }
        for (int i9 = 0; i9 < this.lastAllowWhileIdleDispatchTimes_.size(); i9++) {
            output.writeMessage(36, this.lastAllowWhileIdleDispatchTimes_.get(i9));
        }
        if ((this.bitField0_ & 134217728) == 134217728) {
            output.writeMessage(37, getRecentProblems());
        }
        for (int i10 = 0; i10 < this.topAlarms_.size(); i10++) {
            output.writeMessage(38, this.topAlarms_.get(i10));
        }
        for (int i11 = 0; i11 < this.alarmStats_.size(); i11++) {
            output.writeMessage(39, this.alarmStats_.get(i11));
        }
        for (int i12 = 0; i12 < this.allowWhileIdleDispatches_.size(); i12++) {
            output.writeMessage(40, this.allowWhileIdleDispatches_.get(i12));
        }
        for (int i13 = 0; i13 < this.recentWakeupHistory_.size(); i13++) {
            output.writeMessage(41, this.recentWakeupHistory_.get(i13));
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
            size2 = 0 + CodedOutputStream.computeInt64Size(1, this.currentTime_);
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeInt64Size(2, this.elapsedRealtime_);
        }
        if ((this.bitField0_ & 4) == 4) {
            size2 += CodedOutputStream.computeInt64Size(3, this.lastTimeChangeClockTime_);
        }
        if ((this.bitField0_ & 8) == 8) {
            size2 += CodedOutputStream.computeInt64Size(4, this.lastTimeChangeRealtime_);
        }
        if ((this.bitField0_ & 16) == 16) {
            size2 += CodedOutputStream.computeMessageSize(5, getSettings());
        }
        if ((this.bitField0_ & 32) == 32) {
            size2 += CodedOutputStream.computeMessageSize(6, getForceAppStandbyTracker());
        }
        if ((this.bitField0_ & 64) == 64) {
            size2 += CodedOutputStream.computeBoolSize(7, this.isInteractive_);
        }
        if ((this.bitField0_ & 128) == 128) {
            size2 += CodedOutputStream.computeInt64Size(8, this.timeSinceNonInteractiveMs_);
        }
        if ((this.bitField0_ & 256) == 256) {
            size2 += CodedOutputStream.computeInt64Size(9, this.maxWakeupDelayMs_);
        }
        if ((this.bitField0_ & 512) == 512) {
            size2 += CodedOutputStream.computeInt64Size(10, this.timeSinceLastDispatchMs_);
        }
        if ((this.bitField0_ & 1024) == 1024) {
            size2 += CodedOutputStream.computeInt64Size(11, this.timeUntilNextNonWakeupDeliveryMs_);
        }
        if ((this.bitField0_ & 2048) == 2048) {
            size2 += CodedOutputStream.computeInt64Size(12, this.timeUntilNextNonWakeupAlarmMs_);
        }
        if ((this.bitField0_ & 4096) == 4096) {
            size2 += CodedOutputStream.computeInt64Size(13, this.timeUntilNextWakeupMs_);
        }
        if ((this.bitField0_ & 8192) == 8192) {
            size2 += CodedOutputStream.computeInt64Size(14, this.timeSinceLastWakeupMs_);
        }
        if ((this.bitField0_ & 16384) == 16384) {
            size2 += CodedOutputStream.computeInt64Size(15, this.timeSinceLastWakeupSetMs_);
        }
        if ((this.bitField0_ & 32768) == 32768) {
            size2 += CodedOutputStream.computeInt64Size(16, this.timeChangeEventCount_);
        }
        int dataSize = 0;
        for (int i = 0; i < this.deviceIdleUserWhitelistAppIds_.size(); i++) {
            dataSize += CodedOutputStream.computeInt32SizeNoTag(this.deviceIdleUserWhitelistAppIds_.getInt(i));
        }
        int size3 = size2 + dataSize + (getDeviceIdleUserWhitelistAppIdsList().size() * 2);
        for (int i2 = 0; i2 < this.nextAlarmClockMetadata_.size(); i2++) {
            size3 += CodedOutputStream.computeMessageSize(18, this.nextAlarmClockMetadata_.get(i2));
        }
        for (int i3 = 0; i3 < this.pendingAlarmBatches_.size(); i3++) {
            size3 += CodedOutputStream.computeMessageSize(19, this.pendingAlarmBatches_.get(i3));
        }
        for (int i4 = 0; i4 < this.pendingUserBlockedBackgroundAlarms_.size(); i4++) {
            size3 += CodedOutputStream.computeMessageSize(20, this.pendingUserBlockedBackgroundAlarms_.get(i4));
        }
        if ((this.bitField0_ & 65536) == 65536) {
            size3 += CodedOutputStream.computeMessageSize(21, getPendingIdleUntil());
        }
        for (int i5 = 0; i5 < this.pendingWhileIdleAlarms_.size(); i5++) {
            size3 += CodedOutputStream.computeMessageSize(22, this.pendingWhileIdleAlarms_.get(i5));
        }
        if ((this.bitField0_ & 131072) == 131072) {
            size3 += CodedOutputStream.computeMessageSize(23, getNextWakeFromIdle());
        }
        for (int i6 = 0; i6 < this.pastDueNonWakeupAlarms_.size(); i6++) {
            size3 += CodedOutputStream.computeMessageSize(24, this.pastDueNonWakeupAlarms_.get(i6));
        }
        if ((this.bitField0_ & 262144) == 262144) {
            size3 += CodedOutputStream.computeInt32Size(25, this.delayedAlarmCount_);
        }
        if ((this.bitField0_ & 524288) == 524288) {
            size3 += CodedOutputStream.computeInt64Size(26, this.totalDelayTimeMs_);
        }
        if ((this.bitField0_ & 1048576) == 1048576) {
            size3 += CodedOutputStream.computeInt64Size(27, this.maxDelayDurationMs_);
        }
        if ((this.bitField0_ & 2097152) == 2097152) {
            size3 += CodedOutputStream.computeInt64Size(28, this.maxNonInteractiveDurationMs_);
        }
        if ((this.bitField0_ & 4194304) == 4194304) {
            size3 += CodedOutputStream.computeInt32Size(29, this.broadcastRefCount_);
        }
        if ((this.bitField0_ & 8388608) == 8388608) {
            size3 += CodedOutputStream.computeInt32Size(30, this.pendingIntentSendCount_);
        }
        if ((this.bitField0_ & 16777216) == 16777216) {
            size3 += CodedOutputStream.computeInt32Size(31, this.pendingIntentFinishCount_);
        }
        if ((this.bitField0_ & 33554432) == 33554432) {
            size3 += CodedOutputStream.computeInt32Size(32, this.listenerSendCount_);
        }
        if ((this.bitField0_ & 67108864) == 67108864) {
            size3 += CodedOutputStream.computeInt32Size(33, this.listenerFinishCount_);
        }
        for (int i7 = 0; i7 < this.outstandingDeliveries_.size(); i7++) {
            size3 += CodedOutputStream.computeMessageSize(34, this.outstandingDeliveries_.get(i7));
        }
        int dataSize2 = 0;
        for (int i8 = 0; i8 < this.useAllowWhileIdleShortTime_.size(); i8++) {
            dataSize2 += CodedOutputStream.computeInt32SizeNoTag(this.useAllowWhileIdleShortTime_.getInt(i8));
        }
        int size4 = size3 + dataSize2 + (getUseAllowWhileIdleShortTimeList().size() * 2);
        for (int i9 = 0; i9 < this.lastAllowWhileIdleDispatchTimes_.size(); i9++) {
            size4 += CodedOutputStream.computeMessageSize(36, this.lastAllowWhileIdleDispatchTimes_.get(i9));
        }
        if ((this.bitField0_ & 134217728) == 134217728) {
            size4 += CodedOutputStream.computeMessageSize(37, getRecentProblems());
        }
        for (int i10 = 0; i10 < this.topAlarms_.size(); i10++) {
            size4 += CodedOutputStream.computeMessageSize(38, this.topAlarms_.get(i10));
        }
        for (int i11 = 0; i11 < this.alarmStats_.size(); i11++) {
            size4 += CodedOutputStream.computeMessageSize(39, this.alarmStats_.get(i11));
        }
        for (int i12 = 0; i12 < this.allowWhileIdleDispatches_.size(); i12++) {
            size4 += CodedOutputStream.computeMessageSize(40, this.allowWhileIdleDispatches_.get(i12));
        }
        for (int i13 = 0; i13 < this.recentWakeupHistory_.size(); i13++) {
            size4 += CodedOutputStream.computeMessageSize(41, this.recentWakeupHistory_.get(i13));
        }
        int size5 = size4 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size5;
        return size5;
    }

    public static AlarmManagerServiceDumpProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (AlarmManagerServiceDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static AlarmManagerServiceDumpProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (AlarmManagerServiceDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static AlarmManagerServiceDumpProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (AlarmManagerServiceDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static AlarmManagerServiceDumpProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (AlarmManagerServiceDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static AlarmManagerServiceDumpProto parseFrom(InputStream input) throws IOException {
        return (AlarmManagerServiceDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static AlarmManagerServiceDumpProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (AlarmManagerServiceDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static AlarmManagerServiceDumpProto parseDelimitedFrom(InputStream input) throws IOException {
        return (AlarmManagerServiceDumpProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static AlarmManagerServiceDumpProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (AlarmManagerServiceDumpProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static AlarmManagerServiceDumpProto parseFrom(CodedInputStream input) throws IOException {
        return (AlarmManagerServiceDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static AlarmManagerServiceDumpProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (AlarmManagerServiceDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(AlarmManagerServiceDumpProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<AlarmManagerServiceDumpProto, Builder> implements AlarmManagerServiceDumpProtoOrBuilder {
        private Builder() {
            super(AlarmManagerServiceDumpProto.DEFAULT_INSTANCE);
        }

        @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
        public boolean hasCurrentTime() {
            return ((AlarmManagerServiceDumpProto) this.instance).hasCurrentTime();
        }

        @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
        public long getCurrentTime() {
            return ((AlarmManagerServiceDumpProto) this.instance).getCurrentTime();
        }

        public Builder setCurrentTime(long value) {
            copyOnWrite();
            ((AlarmManagerServiceDumpProto) this.instance).setCurrentTime(value);
            return this;
        }

        public Builder clearCurrentTime() {
            copyOnWrite();
            ((AlarmManagerServiceDumpProto) this.instance).clearCurrentTime();
            return this;
        }

        @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
        public boolean hasElapsedRealtime() {
            return ((AlarmManagerServiceDumpProto) this.instance).hasElapsedRealtime();
        }

        @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
        public long getElapsedRealtime() {
            return ((AlarmManagerServiceDumpProto) this.instance).getElapsedRealtime();
        }

        public Builder setElapsedRealtime(long value) {
            copyOnWrite();
            ((AlarmManagerServiceDumpProto) this.instance).setElapsedRealtime(value);
            return this;
        }

        public Builder clearElapsedRealtime() {
            copyOnWrite();
            ((AlarmManagerServiceDumpProto) this.instance).clearElapsedRealtime();
            return this;
        }

        @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
        public boolean hasLastTimeChangeClockTime() {
            return ((AlarmManagerServiceDumpProto) this.instance).hasLastTimeChangeClockTime();
        }

        @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
        public long getLastTimeChangeClockTime() {
            return ((AlarmManagerServiceDumpProto) this.instance).getLastTimeChangeClockTime();
        }

        public Builder setLastTimeChangeClockTime(long value) {
            copyOnWrite();
            ((AlarmManagerServiceDumpProto) this.instance).setLastTimeChangeClockTime(value);
            return this;
        }

        public Builder clearLastTimeChangeClockTime() {
            copyOnWrite();
            ((AlarmManagerServiceDumpProto) this.instance).clearLastTimeChangeClockTime();
            return this;
        }

        @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
        public boolean hasLastTimeChangeRealtime() {
            return ((AlarmManagerServiceDumpProto) this.instance).hasLastTimeChangeRealtime();
        }

        @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
        public long getLastTimeChangeRealtime() {
            return ((AlarmManagerServiceDumpProto) this.instance).getLastTimeChangeRealtime();
        }

        public Builder setLastTimeChangeRealtime(long value) {
            copyOnWrite();
            ((AlarmManagerServiceDumpProto) this.instance).setLastTimeChangeRealtime(value);
            return this;
        }

        public Builder clearLastTimeChangeRealtime() {
            copyOnWrite();
            ((AlarmManagerServiceDumpProto) this.instance).clearLastTimeChangeRealtime();
            return this;
        }

        @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
        public boolean hasSettings() {
            return ((AlarmManagerServiceDumpProto) this.instance).hasSettings();
        }

        @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
        public ConstantsProto getSettings() {
            return ((AlarmManagerServiceDumpProto) this.instance).getSettings();
        }

        public Builder setSettings(ConstantsProto value) {
            copyOnWrite();
            ((AlarmManagerServiceDumpProto) this.instance).setSettings((AlarmManagerServiceDumpProto) value);
            return this;
        }

        public Builder setSettings(ConstantsProto.Builder builderForValue) {
            copyOnWrite();
            ((AlarmManagerServiceDumpProto) this.instance).setSettings((AlarmManagerServiceDumpProto) builderForValue);
            return this;
        }

        public Builder mergeSettings(ConstantsProto value) {
            copyOnWrite();
            ((AlarmManagerServiceDumpProto) this.instance).mergeSettings(value);
            return this;
        }

        public Builder clearSettings() {
            copyOnWrite();
            ((AlarmManagerServiceDumpProto) this.instance).clearSettings();
            return this;
        }

        @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
        public boolean hasForceAppStandbyTracker() {
            return ((AlarmManagerServiceDumpProto) this.instance).hasForceAppStandbyTracker();
        }

        @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
        public ForceAppStandbyTrackerProto getForceAppStandbyTracker() {
            return ((AlarmManagerServiceDumpProto) this.instance).getForceAppStandbyTracker();
        }

        public Builder setForceAppStandbyTracker(ForceAppStandbyTrackerProto value) {
            copyOnWrite();
            ((AlarmManagerServiceDumpProto) this.instance).setForceAppStandbyTracker((AlarmManagerServiceDumpProto) value);
            return this;
        }

        public Builder setForceAppStandbyTracker(ForceAppStandbyTrackerProto.Builder builderForValue) {
            copyOnWrite();
            ((AlarmManagerServiceDumpProto) this.instance).setForceAppStandbyTracker((AlarmManagerServiceDumpProto) builderForValue);
            return this;
        }

        public Builder mergeForceAppStandbyTracker(ForceAppStandbyTrackerProto value) {
            copyOnWrite();
            ((AlarmManagerServiceDumpProto) this.instance).mergeForceAppStandbyTracker(value);
            return this;
        }

        public Builder clearForceAppStandbyTracker() {
            copyOnWrite();
            ((AlarmManagerServiceDumpProto) this.instance).clearForceAppStandbyTracker();
            return this;
        }

        @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
        public boolean hasIsInteractive() {
            return ((AlarmManagerServiceDumpProto) this.instance).hasIsInteractive();
        }

        @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
        public boolean getIsInteractive() {
            return ((AlarmManagerServiceDumpProto) this.instance).getIsInteractive();
        }

        public Builder setIsInteractive(boolean value) {
            copyOnWrite();
            ((AlarmManagerServiceDumpProto) this.instance).setIsInteractive(value);
            return this;
        }

        public Builder clearIsInteractive() {
            copyOnWrite();
            ((AlarmManagerServiceDumpProto) this.instance).clearIsInteractive();
            return this;
        }

        @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
        public boolean hasTimeSinceNonInteractiveMs() {
            return ((AlarmManagerServiceDumpProto) this.instance).hasTimeSinceNonInteractiveMs();
        }

        @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
        public long getTimeSinceNonInteractiveMs() {
            return ((AlarmManagerServiceDumpProto) this.instance).getTimeSinceNonInteractiveMs();
        }

        public Builder setTimeSinceNonInteractiveMs(long value) {
            copyOnWrite();
            ((AlarmManagerServiceDumpProto) this.instance).setTimeSinceNonInteractiveMs(value);
            return this;
        }

        public Builder clearTimeSinceNonInteractiveMs() {
            copyOnWrite();
            ((AlarmManagerServiceDumpProto) this.instance).clearTimeSinceNonInteractiveMs();
            return this;
        }

        @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
        public boolean hasMaxWakeupDelayMs() {
            return ((AlarmManagerServiceDumpProto) this.instance).hasMaxWakeupDelayMs();
        }

        @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
        public long getMaxWakeupDelayMs() {
            return ((AlarmManagerServiceDumpProto) this.instance).getMaxWakeupDelayMs();
        }

        public Builder setMaxWakeupDelayMs(long value) {
            copyOnWrite();
            ((AlarmManagerServiceDumpProto) this.instance).setMaxWakeupDelayMs(value);
            return this;
        }

        public Builder clearMaxWakeupDelayMs() {
            copyOnWrite();
            ((AlarmManagerServiceDumpProto) this.instance).clearMaxWakeupDelayMs();
            return this;
        }

        @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
        public boolean hasTimeSinceLastDispatchMs() {
            return ((AlarmManagerServiceDumpProto) this.instance).hasTimeSinceLastDispatchMs();
        }

        @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
        public long getTimeSinceLastDispatchMs() {
            return ((AlarmManagerServiceDumpProto) this.instance).getTimeSinceLastDispatchMs();
        }

        public Builder setTimeSinceLastDispatchMs(long value) {
            copyOnWrite();
            ((AlarmManagerServiceDumpProto) this.instance).setTimeSinceLastDispatchMs(value);
            return this;
        }

        public Builder clearTimeSinceLastDispatchMs() {
            copyOnWrite();
            ((AlarmManagerServiceDumpProto) this.instance).clearTimeSinceLastDispatchMs();
            return this;
        }

        @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
        public boolean hasTimeUntilNextNonWakeupDeliveryMs() {
            return ((AlarmManagerServiceDumpProto) this.instance).hasTimeUntilNextNonWakeupDeliveryMs();
        }

        @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
        public long getTimeUntilNextNonWakeupDeliveryMs() {
            return ((AlarmManagerServiceDumpProto) this.instance).getTimeUntilNextNonWakeupDeliveryMs();
        }

        public Builder setTimeUntilNextNonWakeupDeliveryMs(long value) {
            copyOnWrite();
            ((AlarmManagerServiceDumpProto) this.instance).setTimeUntilNextNonWakeupDeliveryMs(value);
            return this;
        }

        public Builder clearTimeUntilNextNonWakeupDeliveryMs() {
            copyOnWrite();
            ((AlarmManagerServiceDumpProto) this.instance).clearTimeUntilNextNonWakeupDeliveryMs();
            return this;
        }

        @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
        public boolean hasTimeUntilNextNonWakeupAlarmMs() {
            return ((AlarmManagerServiceDumpProto) this.instance).hasTimeUntilNextNonWakeupAlarmMs();
        }

        @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
        public long getTimeUntilNextNonWakeupAlarmMs() {
            return ((AlarmManagerServiceDumpProto) this.instance).getTimeUntilNextNonWakeupAlarmMs();
        }

        public Builder setTimeUntilNextNonWakeupAlarmMs(long value) {
            copyOnWrite();
            ((AlarmManagerServiceDumpProto) this.instance).setTimeUntilNextNonWakeupAlarmMs(value);
            return this;
        }

        public Builder clearTimeUntilNextNonWakeupAlarmMs() {
            copyOnWrite();
            ((AlarmManagerServiceDumpProto) this.instance).clearTimeUntilNextNonWakeupAlarmMs();
            return this;
        }

        @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
        public boolean hasTimeUntilNextWakeupMs() {
            return ((AlarmManagerServiceDumpProto) this.instance).hasTimeUntilNextWakeupMs();
        }

        @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
        public long getTimeUntilNextWakeupMs() {
            return ((AlarmManagerServiceDumpProto) this.instance).getTimeUntilNextWakeupMs();
        }

        public Builder setTimeUntilNextWakeupMs(long value) {
            copyOnWrite();
            ((AlarmManagerServiceDumpProto) this.instance).setTimeUntilNextWakeupMs(value);
            return this;
        }

        public Builder clearTimeUntilNextWakeupMs() {
            copyOnWrite();
            ((AlarmManagerServiceDumpProto) this.instance).clearTimeUntilNextWakeupMs();
            return this;
        }

        @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
        public boolean hasTimeSinceLastWakeupMs() {
            return ((AlarmManagerServiceDumpProto) this.instance).hasTimeSinceLastWakeupMs();
        }

        @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
        public long getTimeSinceLastWakeupMs() {
            return ((AlarmManagerServiceDumpProto) this.instance).getTimeSinceLastWakeupMs();
        }

        public Builder setTimeSinceLastWakeupMs(long value) {
            copyOnWrite();
            ((AlarmManagerServiceDumpProto) this.instance).setTimeSinceLastWakeupMs(value);
            return this;
        }

        public Builder clearTimeSinceLastWakeupMs() {
            copyOnWrite();
            ((AlarmManagerServiceDumpProto) this.instance).clearTimeSinceLastWakeupMs();
            return this;
        }

        @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
        public boolean hasTimeSinceLastWakeupSetMs() {
            return ((AlarmManagerServiceDumpProto) this.instance).hasTimeSinceLastWakeupSetMs();
        }

        @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
        public long getTimeSinceLastWakeupSetMs() {
            return ((AlarmManagerServiceDumpProto) this.instance).getTimeSinceLastWakeupSetMs();
        }

        public Builder setTimeSinceLastWakeupSetMs(long value) {
            copyOnWrite();
            ((AlarmManagerServiceDumpProto) this.instance).setTimeSinceLastWakeupSetMs(value);
            return this;
        }

        public Builder clearTimeSinceLastWakeupSetMs() {
            copyOnWrite();
            ((AlarmManagerServiceDumpProto) this.instance).clearTimeSinceLastWakeupSetMs();
            return this;
        }

        @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
        public boolean hasTimeChangeEventCount() {
            return ((AlarmManagerServiceDumpProto) this.instance).hasTimeChangeEventCount();
        }

        @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
        public long getTimeChangeEventCount() {
            return ((AlarmManagerServiceDumpProto) this.instance).getTimeChangeEventCount();
        }

        public Builder setTimeChangeEventCount(long value) {
            copyOnWrite();
            ((AlarmManagerServiceDumpProto) this.instance).setTimeChangeEventCount(value);
            return this;
        }

        public Builder clearTimeChangeEventCount() {
            copyOnWrite();
            ((AlarmManagerServiceDumpProto) this.instance).clearTimeChangeEventCount();
            return this;
        }

        @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
        public List<Integer> getDeviceIdleUserWhitelistAppIdsList() {
            return Collections.unmodifiableList(((AlarmManagerServiceDumpProto) this.instance).getDeviceIdleUserWhitelistAppIdsList());
        }

        @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
        public int getDeviceIdleUserWhitelistAppIdsCount() {
            return ((AlarmManagerServiceDumpProto) this.instance).getDeviceIdleUserWhitelistAppIdsCount();
        }

        @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
        public int getDeviceIdleUserWhitelistAppIds(int index) {
            return ((AlarmManagerServiceDumpProto) this.instance).getDeviceIdleUserWhitelistAppIds(index);
        }

        public Builder setDeviceIdleUserWhitelistAppIds(int index, int value) {
            copyOnWrite();
            ((AlarmManagerServiceDumpProto) this.instance).setDeviceIdleUserWhitelistAppIds(index, value);
            return this;
        }

        public Builder addDeviceIdleUserWhitelistAppIds(int value) {
            copyOnWrite();
            ((AlarmManagerServiceDumpProto) this.instance).addDeviceIdleUserWhitelistAppIds(value);
            return this;
        }

        public Builder addAllDeviceIdleUserWhitelistAppIds(Iterable<? extends Integer> values) {
            copyOnWrite();
            ((AlarmManagerServiceDumpProto) this.instance).addAllDeviceIdleUserWhitelistAppIds(values);
            return this;
        }

        public Builder clearDeviceIdleUserWhitelistAppIds() {
            copyOnWrite();
            ((AlarmManagerServiceDumpProto) this.instance).clearDeviceIdleUserWhitelistAppIds();
            return this;
        }

        @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
        public List<AlarmClockMetadataProto> getNextAlarmClockMetadataList() {
            return Collections.unmodifiableList(((AlarmManagerServiceDumpProto) this.instance).getNextAlarmClockMetadataList());
        }

        @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
        public int getNextAlarmClockMetadataCount() {
            return ((AlarmManagerServiceDumpProto) this.instance).getNextAlarmClockMetadataCount();
        }

        @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
        public AlarmClockMetadataProto getNextAlarmClockMetadata(int index) {
            return ((AlarmManagerServiceDumpProto) this.instance).getNextAlarmClockMetadata(index);
        }

        public Builder setNextAlarmClockMetadata(int index, AlarmClockMetadataProto value) {
            copyOnWrite();
            ((AlarmManagerServiceDumpProto) this.instance).setNextAlarmClockMetadata((AlarmManagerServiceDumpProto) index, (int) value);
            return this;
        }

        public Builder setNextAlarmClockMetadata(int index, AlarmClockMetadataProto.Builder builderForValue) {
            copyOnWrite();
            ((AlarmManagerServiceDumpProto) this.instance).setNextAlarmClockMetadata((AlarmManagerServiceDumpProto) index, (int) builderForValue);
            return this;
        }

        public Builder addNextAlarmClockMetadata(AlarmClockMetadataProto value) {
            copyOnWrite();
            ((AlarmManagerServiceDumpProto) this.instance).addNextAlarmClockMetadata((AlarmManagerServiceDumpProto) value);
            return this;
        }

        public Builder addNextAlarmClockMetadata(int index, AlarmClockMetadataProto value) {
            copyOnWrite();
            ((AlarmManagerServiceDumpProto) this.instance).addNextAlarmClockMetadata((AlarmManagerServiceDumpProto) index, (int) value);
            return this;
        }

        public Builder addNextAlarmClockMetadata(AlarmClockMetadataProto.Builder builderForValue) {
            copyOnWrite();
            ((AlarmManagerServiceDumpProto) this.instance).addNextAlarmClockMetadata((AlarmManagerServiceDumpProto) builderForValue);
            return this;
        }

        public Builder addNextAlarmClockMetadata(int index, AlarmClockMetadataProto.Builder builderForValue) {
            copyOnWrite();
            ((AlarmManagerServiceDumpProto) this.instance).addNextAlarmClockMetadata((AlarmManagerServiceDumpProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllNextAlarmClockMetadata(Iterable<? extends AlarmClockMetadataProto> values) {
            copyOnWrite();
            ((AlarmManagerServiceDumpProto) this.instance).addAllNextAlarmClockMetadata(values);
            return this;
        }

        public Builder clearNextAlarmClockMetadata() {
            copyOnWrite();
            ((AlarmManagerServiceDumpProto) this.instance).clearNextAlarmClockMetadata();
            return this;
        }

        public Builder removeNextAlarmClockMetadata(int index) {
            copyOnWrite();
            ((AlarmManagerServiceDumpProto) this.instance).removeNextAlarmClockMetadata(index);
            return this;
        }

        @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
        public List<BatchProto> getPendingAlarmBatchesList() {
            return Collections.unmodifiableList(((AlarmManagerServiceDumpProto) this.instance).getPendingAlarmBatchesList());
        }

        @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
        public int getPendingAlarmBatchesCount() {
            return ((AlarmManagerServiceDumpProto) this.instance).getPendingAlarmBatchesCount();
        }

        @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
        public BatchProto getPendingAlarmBatches(int index) {
            return ((AlarmManagerServiceDumpProto) this.instance).getPendingAlarmBatches(index);
        }

        public Builder setPendingAlarmBatches(int index, BatchProto value) {
            copyOnWrite();
            ((AlarmManagerServiceDumpProto) this.instance).setPendingAlarmBatches((AlarmManagerServiceDumpProto) index, (int) value);
            return this;
        }

        public Builder setPendingAlarmBatches(int index, BatchProto.Builder builderForValue) {
            copyOnWrite();
            ((AlarmManagerServiceDumpProto) this.instance).setPendingAlarmBatches((AlarmManagerServiceDumpProto) index, (int) builderForValue);
            return this;
        }

        public Builder addPendingAlarmBatches(BatchProto value) {
            copyOnWrite();
            ((AlarmManagerServiceDumpProto) this.instance).addPendingAlarmBatches((AlarmManagerServiceDumpProto) value);
            return this;
        }

        public Builder addPendingAlarmBatches(int index, BatchProto value) {
            copyOnWrite();
            ((AlarmManagerServiceDumpProto) this.instance).addPendingAlarmBatches((AlarmManagerServiceDumpProto) index, (int) value);
            return this;
        }

        public Builder addPendingAlarmBatches(BatchProto.Builder builderForValue) {
            copyOnWrite();
            ((AlarmManagerServiceDumpProto) this.instance).addPendingAlarmBatches((AlarmManagerServiceDumpProto) builderForValue);
            return this;
        }

        public Builder addPendingAlarmBatches(int index, BatchProto.Builder builderForValue) {
            copyOnWrite();
            ((AlarmManagerServiceDumpProto) this.instance).addPendingAlarmBatches((AlarmManagerServiceDumpProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllPendingAlarmBatches(Iterable<? extends BatchProto> values) {
            copyOnWrite();
            ((AlarmManagerServiceDumpProto) this.instance).addAllPendingAlarmBatches(values);
            return this;
        }

        public Builder clearPendingAlarmBatches() {
            copyOnWrite();
            ((AlarmManagerServiceDumpProto) this.instance).clearPendingAlarmBatches();
            return this;
        }

        public Builder removePendingAlarmBatches(int index) {
            copyOnWrite();
            ((AlarmManagerServiceDumpProto) this.instance).removePendingAlarmBatches(index);
            return this;
        }

        @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
        public List<AlarmProto> getPendingUserBlockedBackgroundAlarmsList() {
            return Collections.unmodifiableList(((AlarmManagerServiceDumpProto) this.instance).getPendingUserBlockedBackgroundAlarmsList());
        }

        @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
        public int getPendingUserBlockedBackgroundAlarmsCount() {
            return ((AlarmManagerServiceDumpProto) this.instance).getPendingUserBlockedBackgroundAlarmsCount();
        }

        @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
        public AlarmProto getPendingUserBlockedBackgroundAlarms(int index) {
            return ((AlarmManagerServiceDumpProto) this.instance).getPendingUserBlockedBackgroundAlarms(index);
        }

        public Builder setPendingUserBlockedBackgroundAlarms(int index, AlarmProto value) {
            copyOnWrite();
            ((AlarmManagerServiceDumpProto) this.instance).setPendingUserBlockedBackgroundAlarms((AlarmManagerServiceDumpProto) index, (int) value);
            return this;
        }

        public Builder setPendingUserBlockedBackgroundAlarms(int index, AlarmProto.Builder builderForValue) {
            copyOnWrite();
            ((AlarmManagerServiceDumpProto) this.instance).setPendingUserBlockedBackgroundAlarms((AlarmManagerServiceDumpProto) index, (int) builderForValue);
            return this;
        }

        public Builder addPendingUserBlockedBackgroundAlarms(AlarmProto value) {
            copyOnWrite();
            ((AlarmManagerServiceDumpProto) this.instance).addPendingUserBlockedBackgroundAlarms((AlarmManagerServiceDumpProto) value);
            return this;
        }

        public Builder addPendingUserBlockedBackgroundAlarms(int index, AlarmProto value) {
            copyOnWrite();
            ((AlarmManagerServiceDumpProto) this.instance).addPendingUserBlockedBackgroundAlarms((AlarmManagerServiceDumpProto) index, (int) value);
            return this;
        }

        public Builder addPendingUserBlockedBackgroundAlarms(AlarmProto.Builder builderForValue) {
            copyOnWrite();
            ((AlarmManagerServiceDumpProto) this.instance).addPendingUserBlockedBackgroundAlarms((AlarmManagerServiceDumpProto) builderForValue);
            return this;
        }

        public Builder addPendingUserBlockedBackgroundAlarms(int index, AlarmProto.Builder builderForValue) {
            copyOnWrite();
            ((AlarmManagerServiceDumpProto) this.instance).addPendingUserBlockedBackgroundAlarms((AlarmManagerServiceDumpProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllPendingUserBlockedBackgroundAlarms(Iterable<? extends AlarmProto> values) {
            copyOnWrite();
            ((AlarmManagerServiceDumpProto) this.instance).addAllPendingUserBlockedBackgroundAlarms(values);
            return this;
        }

        public Builder clearPendingUserBlockedBackgroundAlarms() {
            copyOnWrite();
            ((AlarmManagerServiceDumpProto) this.instance).clearPendingUserBlockedBackgroundAlarms();
            return this;
        }

        public Builder removePendingUserBlockedBackgroundAlarms(int index) {
            copyOnWrite();
            ((AlarmManagerServiceDumpProto) this.instance).removePendingUserBlockedBackgroundAlarms(index);
            return this;
        }

        @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
        public boolean hasPendingIdleUntil() {
            return ((AlarmManagerServiceDumpProto) this.instance).hasPendingIdleUntil();
        }

        @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
        public AlarmProto getPendingIdleUntil() {
            return ((AlarmManagerServiceDumpProto) this.instance).getPendingIdleUntil();
        }

        public Builder setPendingIdleUntil(AlarmProto value) {
            copyOnWrite();
            ((AlarmManagerServiceDumpProto) this.instance).setPendingIdleUntil((AlarmManagerServiceDumpProto) value);
            return this;
        }

        public Builder setPendingIdleUntil(AlarmProto.Builder builderForValue) {
            copyOnWrite();
            ((AlarmManagerServiceDumpProto) this.instance).setPendingIdleUntil((AlarmManagerServiceDumpProto) builderForValue);
            return this;
        }

        public Builder mergePendingIdleUntil(AlarmProto value) {
            copyOnWrite();
            ((AlarmManagerServiceDumpProto) this.instance).mergePendingIdleUntil(value);
            return this;
        }

        public Builder clearPendingIdleUntil() {
            copyOnWrite();
            ((AlarmManagerServiceDumpProto) this.instance).clearPendingIdleUntil();
            return this;
        }

        @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
        public List<AlarmProto> getPendingWhileIdleAlarmsList() {
            return Collections.unmodifiableList(((AlarmManagerServiceDumpProto) this.instance).getPendingWhileIdleAlarmsList());
        }

        @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
        public int getPendingWhileIdleAlarmsCount() {
            return ((AlarmManagerServiceDumpProto) this.instance).getPendingWhileIdleAlarmsCount();
        }

        @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
        public AlarmProto getPendingWhileIdleAlarms(int index) {
            return ((AlarmManagerServiceDumpProto) this.instance).getPendingWhileIdleAlarms(index);
        }

        public Builder setPendingWhileIdleAlarms(int index, AlarmProto value) {
            copyOnWrite();
            ((AlarmManagerServiceDumpProto) this.instance).setPendingWhileIdleAlarms((AlarmManagerServiceDumpProto) index, (int) value);
            return this;
        }

        public Builder setPendingWhileIdleAlarms(int index, AlarmProto.Builder builderForValue) {
            copyOnWrite();
            ((AlarmManagerServiceDumpProto) this.instance).setPendingWhileIdleAlarms((AlarmManagerServiceDumpProto) index, (int) builderForValue);
            return this;
        }

        public Builder addPendingWhileIdleAlarms(AlarmProto value) {
            copyOnWrite();
            ((AlarmManagerServiceDumpProto) this.instance).addPendingWhileIdleAlarms((AlarmManagerServiceDumpProto) value);
            return this;
        }

        public Builder addPendingWhileIdleAlarms(int index, AlarmProto value) {
            copyOnWrite();
            ((AlarmManagerServiceDumpProto) this.instance).addPendingWhileIdleAlarms((AlarmManagerServiceDumpProto) index, (int) value);
            return this;
        }

        public Builder addPendingWhileIdleAlarms(AlarmProto.Builder builderForValue) {
            copyOnWrite();
            ((AlarmManagerServiceDumpProto) this.instance).addPendingWhileIdleAlarms((AlarmManagerServiceDumpProto) builderForValue);
            return this;
        }

        public Builder addPendingWhileIdleAlarms(int index, AlarmProto.Builder builderForValue) {
            copyOnWrite();
            ((AlarmManagerServiceDumpProto) this.instance).addPendingWhileIdleAlarms((AlarmManagerServiceDumpProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllPendingWhileIdleAlarms(Iterable<? extends AlarmProto> values) {
            copyOnWrite();
            ((AlarmManagerServiceDumpProto) this.instance).addAllPendingWhileIdleAlarms(values);
            return this;
        }

        public Builder clearPendingWhileIdleAlarms() {
            copyOnWrite();
            ((AlarmManagerServiceDumpProto) this.instance).clearPendingWhileIdleAlarms();
            return this;
        }

        public Builder removePendingWhileIdleAlarms(int index) {
            copyOnWrite();
            ((AlarmManagerServiceDumpProto) this.instance).removePendingWhileIdleAlarms(index);
            return this;
        }

        @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
        public boolean hasNextWakeFromIdle() {
            return ((AlarmManagerServiceDumpProto) this.instance).hasNextWakeFromIdle();
        }

        @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
        public AlarmProto getNextWakeFromIdle() {
            return ((AlarmManagerServiceDumpProto) this.instance).getNextWakeFromIdle();
        }

        public Builder setNextWakeFromIdle(AlarmProto value) {
            copyOnWrite();
            ((AlarmManagerServiceDumpProto) this.instance).setNextWakeFromIdle((AlarmManagerServiceDumpProto) value);
            return this;
        }

        public Builder setNextWakeFromIdle(AlarmProto.Builder builderForValue) {
            copyOnWrite();
            ((AlarmManagerServiceDumpProto) this.instance).setNextWakeFromIdle((AlarmManagerServiceDumpProto) builderForValue);
            return this;
        }

        public Builder mergeNextWakeFromIdle(AlarmProto value) {
            copyOnWrite();
            ((AlarmManagerServiceDumpProto) this.instance).mergeNextWakeFromIdle(value);
            return this;
        }

        public Builder clearNextWakeFromIdle() {
            copyOnWrite();
            ((AlarmManagerServiceDumpProto) this.instance).clearNextWakeFromIdle();
            return this;
        }

        @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
        public List<AlarmProto> getPastDueNonWakeupAlarmsList() {
            return Collections.unmodifiableList(((AlarmManagerServiceDumpProto) this.instance).getPastDueNonWakeupAlarmsList());
        }

        @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
        public int getPastDueNonWakeupAlarmsCount() {
            return ((AlarmManagerServiceDumpProto) this.instance).getPastDueNonWakeupAlarmsCount();
        }

        @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
        public AlarmProto getPastDueNonWakeupAlarms(int index) {
            return ((AlarmManagerServiceDumpProto) this.instance).getPastDueNonWakeupAlarms(index);
        }

        public Builder setPastDueNonWakeupAlarms(int index, AlarmProto value) {
            copyOnWrite();
            ((AlarmManagerServiceDumpProto) this.instance).setPastDueNonWakeupAlarms((AlarmManagerServiceDumpProto) index, (int) value);
            return this;
        }

        public Builder setPastDueNonWakeupAlarms(int index, AlarmProto.Builder builderForValue) {
            copyOnWrite();
            ((AlarmManagerServiceDumpProto) this.instance).setPastDueNonWakeupAlarms((AlarmManagerServiceDumpProto) index, (int) builderForValue);
            return this;
        }

        public Builder addPastDueNonWakeupAlarms(AlarmProto value) {
            copyOnWrite();
            ((AlarmManagerServiceDumpProto) this.instance).addPastDueNonWakeupAlarms((AlarmManagerServiceDumpProto) value);
            return this;
        }

        public Builder addPastDueNonWakeupAlarms(int index, AlarmProto value) {
            copyOnWrite();
            ((AlarmManagerServiceDumpProto) this.instance).addPastDueNonWakeupAlarms((AlarmManagerServiceDumpProto) index, (int) value);
            return this;
        }

        public Builder addPastDueNonWakeupAlarms(AlarmProto.Builder builderForValue) {
            copyOnWrite();
            ((AlarmManagerServiceDumpProto) this.instance).addPastDueNonWakeupAlarms((AlarmManagerServiceDumpProto) builderForValue);
            return this;
        }

        public Builder addPastDueNonWakeupAlarms(int index, AlarmProto.Builder builderForValue) {
            copyOnWrite();
            ((AlarmManagerServiceDumpProto) this.instance).addPastDueNonWakeupAlarms((AlarmManagerServiceDumpProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllPastDueNonWakeupAlarms(Iterable<? extends AlarmProto> values) {
            copyOnWrite();
            ((AlarmManagerServiceDumpProto) this.instance).addAllPastDueNonWakeupAlarms(values);
            return this;
        }

        public Builder clearPastDueNonWakeupAlarms() {
            copyOnWrite();
            ((AlarmManagerServiceDumpProto) this.instance).clearPastDueNonWakeupAlarms();
            return this;
        }

        public Builder removePastDueNonWakeupAlarms(int index) {
            copyOnWrite();
            ((AlarmManagerServiceDumpProto) this.instance).removePastDueNonWakeupAlarms(index);
            return this;
        }

        @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
        public boolean hasDelayedAlarmCount() {
            return ((AlarmManagerServiceDumpProto) this.instance).hasDelayedAlarmCount();
        }

        @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
        public int getDelayedAlarmCount() {
            return ((AlarmManagerServiceDumpProto) this.instance).getDelayedAlarmCount();
        }

        public Builder setDelayedAlarmCount(int value) {
            copyOnWrite();
            ((AlarmManagerServiceDumpProto) this.instance).setDelayedAlarmCount(value);
            return this;
        }

        public Builder clearDelayedAlarmCount() {
            copyOnWrite();
            ((AlarmManagerServiceDumpProto) this.instance).clearDelayedAlarmCount();
            return this;
        }

        @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
        public boolean hasTotalDelayTimeMs() {
            return ((AlarmManagerServiceDumpProto) this.instance).hasTotalDelayTimeMs();
        }

        @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
        public long getTotalDelayTimeMs() {
            return ((AlarmManagerServiceDumpProto) this.instance).getTotalDelayTimeMs();
        }

        public Builder setTotalDelayTimeMs(long value) {
            copyOnWrite();
            ((AlarmManagerServiceDumpProto) this.instance).setTotalDelayTimeMs(value);
            return this;
        }

        public Builder clearTotalDelayTimeMs() {
            copyOnWrite();
            ((AlarmManagerServiceDumpProto) this.instance).clearTotalDelayTimeMs();
            return this;
        }

        @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
        public boolean hasMaxDelayDurationMs() {
            return ((AlarmManagerServiceDumpProto) this.instance).hasMaxDelayDurationMs();
        }

        @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
        public long getMaxDelayDurationMs() {
            return ((AlarmManagerServiceDumpProto) this.instance).getMaxDelayDurationMs();
        }

        public Builder setMaxDelayDurationMs(long value) {
            copyOnWrite();
            ((AlarmManagerServiceDumpProto) this.instance).setMaxDelayDurationMs(value);
            return this;
        }

        public Builder clearMaxDelayDurationMs() {
            copyOnWrite();
            ((AlarmManagerServiceDumpProto) this.instance).clearMaxDelayDurationMs();
            return this;
        }

        @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
        public boolean hasMaxNonInteractiveDurationMs() {
            return ((AlarmManagerServiceDumpProto) this.instance).hasMaxNonInteractiveDurationMs();
        }

        @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
        public long getMaxNonInteractiveDurationMs() {
            return ((AlarmManagerServiceDumpProto) this.instance).getMaxNonInteractiveDurationMs();
        }

        public Builder setMaxNonInteractiveDurationMs(long value) {
            copyOnWrite();
            ((AlarmManagerServiceDumpProto) this.instance).setMaxNonInteractiveDurationMs(value);
            return this;
        }

        public Builder clearMaxNonInteractiveDurationMs() {
            copyOnWrite();
            ((AlarmManagerServiceDumpProto) this.instance).clearMaxNonInteractiveDurationMs();
            return this;
        }

        @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
        public boolean hasBroadcastRefCount() {
            return ((AlarmManagerServiceDumpProto) this.instance).hasBroadcastRefCount();
        }

        @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
        public int getBroadcastRefCount() {
            return ((AlarmManagerServiceDumpProto) this.instance).getBroadcastRefCount();
        }

        public Builder setBroadcastRefCount(int value) {
            copyOnWrite();
            ((AlarmManagerServiceDumpProto) this.instance).setBroadcastRefCount(value);
            return this;
        }

        public Builder clearBroadcastRefCount() {
            copyOnWrite();
            ((AlarmManagerServiceDumpProto) this.instance).clearBroadcastRefCount();
            return this;
        }

        @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
        public boolean hasPendingIntentSendCount() {
            return ((AlarmManagerServiceDumpProto) this.instance).hasPendingIntentSendCount();
        }

        @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
        public int getPendingIntentSendCount() {
            return ((AlarmManagerServiceDumpProto) this.instance).getPendingIntentSendCount();
        }

        public Builder setPendingIntentSendCount(int value) {
            copyOnWrite();
            ((AlarmManagerServiceDumpProto) this.instance).setPendingIntentSendCount(value);
            return this;
        }

        public Builder clearPendingIntentSendCount() {
            copyOnWrite();
            ((AlarmManagerServiceDumpProto) this.instance).clearPendingIntentSendCount();
            return this;
        }

        @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
        public boolean hasPendingIntentFinishCount() {
            return ((AlarmManagerServiceDumpProto) this.instance).hasPendingIntentFinishCount();
        }

        @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
        public int getPendingIntentFinishCount() {
            return ((AlarmManagerServiceDumpProto) this.instance).getPendingIntentFinishCount();
        }

        public Builder setPendingIntentFinishCount(int value) {
            copyOnWrite();
            ((AlarmManagerServiceDumpProto) this.instance).setPendingIntentFinishCount(value);
            return this;
        }

        public Builder clearPendingIntentFinishCount() {
            copyOnWrite();
            ((AlarmManagerServiceDumpProto) this.instance).clearPendingIntentFinishCount();
            return this;
        }

        @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
        public boolean hasListenerSendCount() {
            return ((AlarmManagerServiceDumpProto) this.instance).hasListenerSendCount();
        }

        @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
        public int getListenerSendCount() {
            return ((AlarmManagerServiceDumpProto) this.instance).getListenerSendCount();
        }

        public Builder setListenerSendCount(int value) {
            copyOnWrite();
            ((AlarmManagerServiceDumpProto) this.instance).setListenerSendCount(value);
            return this;
        }

        public Builder clearListenerSendCount() {
            copyOnWrite();
            ((AlarmManagerServiceDumpProto) this.instance).clearListenerSendCount();
            return this;
        }

        @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
        public boolean hasListenerFinishCount() {
            return ((AlarmManagerServiceDumpProto) this.instance).hasListenerFinishCount();
        }

        @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
        public int getListenerFinishCount() {
            return ((AlarmManagerServiceDumpProto) this.instance).getListenerFinishCount();
        }

        public Builder setListenerFinishCount(int value) {
            copyOnWrite();
            ((AlarmManagerServiceDumpProto) this.instance).setListenerFinishCount(value);
            return this;
        }

        public Builder clearListenerFinishCount() {
            copyOnWrite();
            ((AlarmManagerServiceDumpProto) this.instance).clearListenerFinishCount();
            return this;
        }

        @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
        public List<InFlightProto> getOutstandingDeliveriesList() {
            return Collections.unmodifiableList(((AlarmManagerServiceDumpProto) this.instance).getOutstandingDeliveriesList());
        }

        @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
        public int getOutstandingDeliveriesCount() {
            return ((AlarmManagerServiceDumpProto) this.instance).getOutstandingDeliveriesCount();
        }

        @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
        public InFlightProto getOutstandingDeliveries(int index) {
            return ((AlarmManagerServiceDumpProto) this.instance).getOutstandingDeliveries(index);
        }

        public Builder setOutstandingDeliveries(int index, InFlightProto value) {
            copyOnWrite();
            ((AlarmManagerServiceDumpProto) this.instance).setOutstandingDeliveries((AlarmManagerServiceDumpProto) index, (int) value);
            return this;
        }

        public Builder setOutstandingDeliveries(int index, InFlightProto.Builder builderForValue) {
            copyOnWrite();
            ((AlarmManagerServiceDumpProto) this.instance).setOutstandingDeliveries((AlarmManagerServiceDumpProto) index, (int) builderForValue);
            return this;
        }

        public Builder addOutstandingDeliveries(InFlightProto value) {
            copyOnWrite();
            ((AlarmManagerServiceDumpProto) this.instance).addOutstandingDeliveries((AlarmManagerServiceDumpProto) value);
            return this;
        }

        public Builder addOutstandingDeliveries(int index, InFlightProto value) {
            copyOnWrite();
            ((AlarmManagerServiceDumpProto) this.instance).addOutstandingDeliveries((AlarmManagerServiceDumpProto) index, (int) value);
            return this;
        }

        public Builder addOutstandingDeliveries(InFlightProto.Builder builderForValue) {
            copyOnWrite();
            ((AlarmManagerServiceDumpProto) this.instance).addOutstandingDeliveries((AlarmManagerServiceDumpProto) builderForValue);
            return this;
        }

        public Builder addOutstandingDeliveries(int index, InFlightProto.Builder builderForValue) {
            copyOnWrite();
            ((AlarmManagerServiceDumpProto) this.instance).addOutstandingDeliveries((AlarmManagerServiceDumpProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllOutstandingDeliveries(Iterable<? extends InFlightProto> values) {
            copyOnWrite();
            ((AlarmManagerServiceDumpProto) this.instance).addAllOutstandingDeliveries(values);
            return this;
        }

        public Builder clearOutstandingDeliveries() {
            copyOnWrite();
            ((AlarmManagerServiceDumpProto) this.instance).clearOutstandingDeliveries();
            return this;
        }

        public Builder removeOutstandingDeliveries(int index) {
            copyOnWrite();
            ((AlarmManagerServiceDumpProto) this.instance).removeOutstandingDeliveries(index);
            return this;
        }

        @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
        public List<Integer> getUseAllowWhileIdleShortTimeList() {
            return Collections.unmodifiableList(((AlarmManagerServiceDumpProto) this.instance).getUseAllowWhileIdleShortTimeList());
        }

        @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
        public int getUseAllowWhileIdleShortTimeCount() {
            return ((AlarmManagerServiceDumpProto) this.instance).getUseAllowWhileIdleShortTimeCount();
        }

        @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
        public int getUseAllowWhileIdleShortTime(int index) {
            return ((AlarmManagerServiceDumpProto) this.instance).getUseAllowWhileIdleShortTime(index);
        }

        public Builder setUseAllowWhileIdleShortTime(int index, int value) {
            copyOnWrite();
            ((AlarmManagerServiceDumpProto) this.instance).setUseAllowWhileIdleShortTime(index, value);
            return this;
        }

        public Builder addUseAllowWhileIdleShortTime(int value) {
            copyOnWrite();
            ((AlarmManagerServiceDumpProto) this.instance).addUseAllowWhileIdleShortTime(value);
            return this;
        }

        public Builder addAllUseAllowWhileIdleShortTime(Iterable<? extends Integer> values) {
            copyOnWrite();
            ((AlarmManagerServiceDumpProto) this.instance).addAllUseAllowWhileIdleShortTime(values);
            return this;
        }

        public Builder clearUseAllowWhileIdleShortTime() {
            copyOnWrite();
            ((AlarmManagerServiceDumpProto) this.instance).clearUseAllowWhileIdleShortTime();
            return this;
        }

        @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
        public List<LastAllowWhileIdleDispatch> getLastAllowWhileIdleDispatchTimesList() {
            return Collections.unmodifiableList(((AlarmManagerServiceDumpProto) this.instance).getLastAllowWhileIdleDispatchTimesList());
        }

        @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
        public int getLastAllowWhileIdleDispatchTimesCount() {
            return ((AlarmManagerServiceDumpProto) this.instance).getLastAllowWhileIdleDispatchTimesCount();
        }

        @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
        public LastAllowWhileIdleDispatch getLastAllowWhileIdleDispatchTimes(int index) {
            return ((AlarmManagerServiceDumpProto) this.instance).getLastAllowWhileIdleDispatchTimes(index);
        }

        public Builder setLastAllowWhileIdleDispatchTimes(int index, LastAllowWhileIdleDispatch value) {
            copyOnWrite();
            ((AlarmManagerServiceDumpProto) this.instance).setLastAllowWhileIdleDispatchTimes((AlarmManagerServiceDumpProto) index, (int) value);
            return this;
        }

        public Builder setLastAllowWhileIdleDispatchTimes(int index, LastAllowWhileIdleDispatch.Builder builderForValue) {
            copyOnWrite();
            ((AlarmManagerServiceDumpProto) this.instance).setLastAllowWhileIdleDispatchTimes((AlarmManagerServiceDumpProto) index, (int) builderForValue);
            return this;
        }

        public Builder addLastAllowWhileIdleDispatchTimes(LastAllowWhileIdleDispatch value) {
            copyOnWrite();
            ((AlarmManagerServiceDumpProto) this.instance).addLastAllowWhileIdleDispatchTimes((AlarmManagerServiceDumpProto) value);
            return this;
        }

        public Builder addLastAllowWhileIdleDispatchTimes(int index, LastAllowWhileIdleDispatch value) {
            copyOnWrite();
            ((AlarmManagerServiceDumpProto) this.instance).addLastAllowWhileIdleDispatchTimes((AlarmManagerServiceDumpProto) index, (int) value);
            return this;
        }

        public Builder addLastAllowWhileIdleDispatchTimes(LastAllowWhileIdleDispatch.Builder builderForValue) {
            copyOnWrite();
            ((AlarmManagerServiceDumpProto) this.instance).addLastAllowWhileIdleDispatchTimes((AlarmManagerServiceDumpProto) builderForValue);
            return this;
        }

        public Builder addLastAllowWhileIdleDispatchTimes(int index, LastAllowWhileIdleDispatch.Builder builderForValue) {
            copyOnWrite();
            ((AlarmManagerServiceDumpProto) this.instance).addLastAllowWhileIdleDispatchTimes((AlarmManagerServiceDumpProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllLastAllowWhileIdleDispatchTimes(Iterable<? extends LastAllowWhileIdleDispatch> values) {
            copyOnWrite();
            ((AlarmManagerServiceDumpProto) this.instance).addAllLastAllowWhileIdleDispatchTimes(values);
            return this;
        }

        public Builder clearLastAllowWhileIdleDispatchTimes() {
            copyOnWrite();
            ((AlarmManagerServiceDumpProto) this.instance).clearLastAllowWhileIdleDispatchTimes();
            return this;
        }

        public Builder removeLastAllowWhileIdleDispatchTimes(int index) {
            copyOnWrite();
            ((AlarmManagerServiceDumpProto) this.instance).removeLastAllowWhileIdleDispatchTimes(index);
            return this;
        }

        @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
        public boolean hasRecentProblems() {
            return ((AlarmManagerServiceDumpProto) this.instance).hasRecentProblems();
        }

        @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
        public LocalLogProto getRecentProblems() {
            return ((AlarmManagerServiceDumpProto) this.instance).getRecentProblems();
        }

        public Builder setRecentProblems(LocalLogProto value) {
            copyOnWrite();
            ((AlarmManagerServiceDumpProto) this.instance).setRecentProblems((AlarmManagerServiceDumpProto) value);
            return this;
        }

        public Builder setRecentProblems(LocalLogProto.Builder builderForValue) {
            copyOnWrite();
            ((AlarmManagerServiceDumpProto) this.instance).setRecentProblems((AlarmManagerServiceDumpProto) builderForValue);
            return this;
        }

        public Builder mergeRecentProblems(LocalLogProto value) {
            copyOnWrite();
            ((AlarmManagerServiceDumpProto) this.instance).mergeRecentProblems(value);
            return this;
        }

        public Builder clearRecentProblems() {
            copyOnWrite();
            ((AlarmManagerServiceDumpProto) this.instance).clearRecentProblems();
            return this;
        }

        @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
        public List<TopAlarm> getTopAlarmsList() {
            return Collections.unmodifiableList(((AlarmManagerServiceDumpProto) this.instance).getTopAlarmsList());
        }

        @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
        public int getTopAlarmsCount() {
            return ((AlarmManagerServiceDumpProto) this.instance).getTopAlarmsCount();
        }

        @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
        public TopAlarm getTopAlarms(int index) {
            return ((AlarmManagerServiceDumpProto) this.instance).getTopAlarms(index);
        }

        public Builder setTopAlarms(int index, TopAlarm value) {
            copyOnWrite();
            ((AlarmManagerServiceDumpProto) this.instance).setTopAlarms((AlarmManagerServiceDumpProto) index, (int) value);
            return this;
        }

        public Builder setTopAlarms(int index, TopAlarm.Builder builderForValue) {
            copyOnWrite();
            ((AlarmManagerServiceDumpProto) this.instance).setTopAlarms((AlarmManagerServiceDumpProto) index, (int) builderForValue);
            return this;
        }

        public Builder addTopAlarms(TopAlarm value) {
            copyOnWrite();
            ((AlarmManagerServiceDumpProto) this.instance).addTopAlarms((AlarmManagerServiceDumpProto) value);
            return this;
        }

        public Builder addTopAlarms(int index, TopAlarm value) {
            copyOnWrite();
            ((AlarmManagerServiceDumpProto) this.instance).addTopAlarms((AlarmManagerServiceDumpProto) index, (int) value);
            return this;
        }

        public Builder addTopAlarms(TopAlarm.Builder builderForValue) {
            copyOnWrite();
            ((AlarmManagerServiceDumpProto) this.instance).addTopAlarms((AlarmManagerServiceDumpProto) builderForValue);
            return this;
        }

        public Builder addTopAlarms(int index, TopAlarm.Builder builderForValue) {
            copyOnWrite();
            ((AlarmManagerServiceDumpProto) this.instance).addTopAlarms((AlarmManagerServiceDumpProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllTopAlarms(Iterable<? extends TopAlarm> values) {
            copyOnWrite();
            ((AlarmManagerServiceDumpProto) this.instance).addAllTopAlarms(values);
            return this;
        }

        public Builder clearTopAlarms() {
            copyOnWrite();
            ((AlarmManagerServiceDumpProto) this.instance).clearTopAlarms();
            return this;
        }

        public Builder removeTopAlarms(int index) {
            copyOnWrite();
            ((AlarmManagerServiceDumpProto) this.instance).removeTopAlarms(index);
            return this;
        }

        @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
        public List<AlarmStat> getAlarmStatsList() {
            return Collections.unmodifiableList(((AlarmManagerServiceDumpProto) this.instance).getAlarmStatsList());
        }

        @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
        public int getAlarmStatsCount() {
            return ((AlarmManagerServiceDumpProto) this.instance).getAlarmStatsCount();
        }

        @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
        public AlarmStat getAlarmStats(int index) {
            return ((AlarmManagerServiceDumpProto) this.instance).getAlarmStats(index);
        }

        public Builder setAlarmStats(int index, AlarmStat value) {
            copyOnWrite();
            ((AlarmManagerServiceDumpProto) this.instance).setAlarmStats((AlarmManagerServiceDumpProto) index, (int) value);
            return this;
        }

        public Builder setAlarmStats(int index, AlarmStat.Builder builderForValue) {
            copyOnWrite();
            ((AlarmManagerServiceDumpProto) this.instance).setAlarmStats((AlarmManagerServiceDumpProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAlarmStats(AlarmStat value) {
            copyOnWrite();
            ((AlarmManagerServiceDumpProto) this.instance).addAlarmStats((AlarmManagerServiceDumpProto) value);
            return this;
        }

        public Builder addAlarmStats(int index, AlarmStat value) {
            copyOnWrite();
            ((AlarmManagerServiceDumpProto) this.instance).addAlarmStats((AlarmManagerServiceDumpProto) index, (int) value);
            return this;
        }

        public Builder addAlarmStats(AlarmStat.Builder builderForValue) {
            copyOnWrite();
            ((AlarmManagerServiceDumpProto) this.instance).addAlarmStats((AlarmManagerServiceDumpProto) builderForValue);
            return this;
        }

        public Builder addAlarmStats(int index, AlarmStat.Builder builderForValue) {
            copyOnWrite();
            ((AlarmManagerServiceDumpProto) this.instance).addAlarmStats((AlarmManagerServiceDumpProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllAlarmStats(Iterable<? extends AlarmStat> values) {
            copyOnWrite();
            ((AlarmManagerServiceDumpProto) this.instance).addAllAlarmStats(values);
            return this;
        }

        public Builder clearAlarmStats() {
            copyOnWrite();
            ((AlarmManagerServiceDumpProto) this.instance).clearAlarmStats();
            return this;
        }

        public Builder removeAlarmStats(int index) {
            copyOnWrite();
            ((AlarmManagerServiceDumpProto) this.instance).removeAlarmStats(index);
            return this;
        }

        @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
        public List<IdleDispatchEntryProto> getAllowWhileIdleDispatchesList() {
            return Collections.unmodifiableList(((AlarmManagerServiceDumpProto) this.instance).getAllowWhileIdleDispatchesList());
        }

        @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
        public int getAllowWhileIdleDispatchesCount() {
            return ((AlarmManagerServiceDumpProto) this.instance).getAllowWhileIdleDispatchesCount();
        }

        @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
        public IdleDispatchEntryProto getAllowWhileIdleDispatches(int index) {
            return ((AlarmManagerServiceDumpProto) this.instance).getAllowWhileIdleDispatches(index);
        }

        public Builder setAllowWhileIdleDispatches(int index, IdleDispatchEntryProto value) {
            copyOnWrite();
            ((AlarmManagerServiceDumpProto) this.instance).setAllowWhileIdleDispatches((AlarmManagerServiceDumpProto) index, (int) value);
            return this;
        }

        public Builder setAllowWhileIdleDispatches(int index, IdleDispatchEntryProto.Builder builderForValue) {
            copyOnWrite();
            ((AlarmManagerServiceDumpProto) this.instance).setAllowWhileIdleDispatches((AlarmManagerServiceDumpProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllowWhileIdleDispatches(IdleDispatchEntryProto value) {
            copyOnWrite();
            ((AlarmManagerServiceDumpProto) this.instance).addAllowWhileIdleDispatches((AlarmManagerServiceDumpProto) value);
            return this;
        }

        public Builder addAllowWhileIdleDispatches(int index, IdleDispatchEntryProto value) {
            copyOnWrite();
            ((AlarmManagerServiceDumpProto) this.instance).addAllowWhileIdleDispatches((AlarmManagerServiceDumpProto) index, (int) value);
            return this;
        }

        public Builder addAllowWhileIdleDispatches(IdleDispatchEntryProto.Builder builderForValue) {
            copyOnWrite();
            ((AlarmManagerServiceDumpProto) this.instance).addAllowWhileIdleDispatches((AlarmManagerServiceDumpProto) builderForValue);
            return this;
        }

        public Builder addAllowWhileIdleDispatches(int index, IdleDispatchEntryProto.Builder builderForValue) {
            copyOnWrite();
            ((AlarmManagerServiceDumpProto) this.instance).addAllowWhileIdleDispatches((AlarmManagerServiceDumpProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllAllowWhileIdleDispatches(Iterable<? extends IdleDispatchEntryProto> values) {
            copyOnWrite();
            ((AlarmManagerServiceDumpProto) this.instance).addAllAllowWhileIdleDispatches(values);
            return this;
        }

        public Builder clearAllowWhileIdleDispatches() {
            copyOnWrite();
            ((AlarmManagerServiceDumpProto) this.instance).clearAllowWhileIdleDispatches();
            return this;
        }

        public Builder removeAllowWhileIdleDispatches(int index) {
            copyOnWrite();
            ((AlarmManagerServiceDumpProto) this.instance).removeAllowWhileIdleDispatches(index);
            return this;
        }

        @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
        public List<WakeupEventProto> getRecentWakeupHistoryList() {
            return Collections.unmodifiableList(((AlarmManagerServiceDumpProto) this.instance).getRecentWakeupHistoryList());
        }

        @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
        public int getRecentWakeupHistoryCount() {
            return ((AlarmManagerServiceDumpProto) this.instance).getRecentWakeupHistoryCount();
        }

        @Override // com.android.server.AlarmManagerServiceDumpProtoOrBuilder
        public WakeupEventProto getRecentWakeupHistory(int index) {
            return ((AlarmManagerServiceDumpProto) this.instance).getRecentWakeupHistory(index);
        }

        public Builder setRecentWakeupHistory(int index, WakeupEventProto value) {
            copyOnWrite();
            ((AlarmManagerServiceDumpProto) this.instance).setRecentWakeupHistory((AlarmManagerServiceDumpProto) index, (int) value);
            return this;
        }

        public Builder setRecentWakeupHistory(int index, WakeupEventProto.Builder builderForValue) {
            copyOnWrite();
            ((AlarmManagerServiceDumpProto) this.instance).setRecentWakeupHistory((AlarmManagerServiceDumpProto) index, (int) builderForValue);
            return this;
        }

        public Builder addRecentWakeupHistory(WakeupEventProto value) {
            copyOnWrite();
            ((AlarmManagerServiceDumpProto) this.instance).addRecentWakeupHistory((AlarmManagerServiceDumpProto) value);
            return this;
        }

        public Builder addRecentWakeupHistory(int index, WakeupEventProto value) {
            copyOnWrite();
            ((AlarmManagerServiceDumpProto) this.instance).addRecentWakeupHistory((AlarmManagerServiceDumpProto) index, (int) value);
            return this;
        }

        public Builder addRecentWakeupHistory(WakeupEventProto.Builder builderForValue) {
            copyOnWrite();
            ((AlarmManagerServiceDumpProto) this.instance).addRecentWakeupHistory((AlarmManagerServiceDumpProto) builderForValue);
            return this;
        }

        public Builder addRecentWakeupHistory(int index, WakeupEventProto.Builder builderForValue) {
            copyOnWrite();
            ((AlarmManagerServiceDumpProto) this.instance).addRecentWakeupHistory((AlarmManagerServiceDumpProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllRecentWakeupHistory(Iterable<? extends WakeupEventProto> values) {
            copyOnWrite();
            ((AlarmManagerServiceDumpProto) this.instance).addAllRecentWakeupHistory(values);
            return this;
        }

        public Builder clearRecentWakeupHistory() {
            copyOnWrite();
            ((AlarmManagerServiceDumpProto) this.instance).clearRecentWakeupHistory();
            return this;
        }

        public Builder removeRecentWakeupHistory(int index) {
            copyOnWrite();
            ((AlarmManagerServiceDumpProto) this.instance).removeRecentWakeupHistory(index);
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new AlarmManagerServiceDumpProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                this.deviceIdleUserWhitelistAppIds_.makeImmutable();
                this.nextAlarmClockMetadata_.makeImmutable();
                this.pendingAlarmBatches_.makeImmutable();
                this.pendingUserBlockedBackgroundAlarms_.makeImmutable();
                this.pendingWhileIdleAlarms_.makeImmutable();
                this.pastDueNonWakeupAlarms_.makeImmutable();
                this.outstandingDeliveries_.makeImmutable();
                this.useAllowWhileIdleShortTime_.makeImmutable();
                this.lastAllowWhileIdleDispatchTimes_.makeImmutable();
                this.topAlarms_.makeImmutable();
                this.alarmStats_.makeImmutable();
                this.allowWhileIdleDispatches_.makeImmutable();
                this.recentWakeupHistory_.makeImmutable();
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                AlarmManagerServiceDumpProto other = (AlarmManagerServiceDumpProto) arg1;
                this.currentTime_ = visitor.visitLong(hasCurrentTime(), this.currentTime_, other.hasCurrentTime(), other.currentTime_);
                this.elapsedRealtime_ = visitor.visitLong(hasElapsedRealtime(), this.elapsedRealtime_, other.hasElapsedRealtime(), other.elapsedRealtime_);
                this.lastTimeChangeClockTime_ = visitor.visitLong(hasLastTimeChangeClockTime(), this.lastTimeChangeClockTime_, other.hasLastTimeChangeClockTime(), other.lastTimeChangeClockTime_);
                this.lastTimeChangeRealtime_ = visitor.visitLong(hasLastTimeChangeRealtime(), this.lastTimeChangeRealtime_, other.hasLastTimeChangeRealtime(), other.lastTimeChangeRealtime_);
                this.settings_ = (ConstantsProto) visitor.visitMessage(this.settings_, other.settings_);
                this.forceAppStandbyTracker_ = (ForceAppStandbyTrackerProto) visitor.visitMessage(this.forceAppStandbyTracker_, other.forceAppStandbyTracker_);
                this.isInteractive_ = visitor.visitBoolean(hasIsInteractive(), this.isInteractive_, other.hasIsInteractive(), other.isInteractive_);
                this.timeSinceNonInteractiveMs_ = visitor.visitLong(hasTimeSinceNonInteractiveMs(), this.timeSinceNonInteractiveMs_, other.hasTimeSinceNonInteractiveMs(), other.timeSinceNonInteractiveMs_);
                this.maxWakeupDelayMs_ = visitor.visitLong(hasMaxWakeupDelayMs(), this.maxWakeupDelayMs_, other.hasMaxWakeupDelayMs(), other.maxWakeupDelayMs_);
                this.timeSinceLastDispatchMs_ = visitor.visitLong(hasTimeSinceLastDispatchMs(), this.timeSinceLastDispatchMs_, other.hasTimeSinceLastDispatchMs(), other.timeSinceLastDispatchMs_);
                this.timeUntilNextNonWakeupDeliveryMs_ = visitor.visitLong(hasTimeUntilNextNonWakeupDeliveryMs(), this.timeUntilNextNonWakeupDeliveryMs_, other.hasTimeUntilNextNonWakeupDeliveryMs(), other.timeUntilNextNonWakeupDeliveryMs_);
                this.timeUntilNextNonWakeupAlarmMs_ = visitor.visitLong(hasTimeUntilNextNonWakeupAlarmMs(), this.timeUntilNextNonWakeupAlarmMs_, other.hasTimeUntilNextNonWakeupAlarmMs(), other.timeUntilNextNonWakeupAlarmMs_);
                this.timeUntilNextWakeupMs_ = visitor.visitLong(hasTimeUntilNextWakeupMs(), this.timeUntilNextWakeupMs_, other.hasTimeUntilNextWakeupMs(), other.timeUntilNextWakeupMs_);
                this.timeSinceLastWakeupMs_ = visitor.visitLong(hasTimeSinceLastWakeupMs(), this.timeSinceLastWakeupMs_, other.hasTimeSinceLastWakeupMs(), other.timeSinceLastWakeupMs_);
                this.timeSinceLastWakeupSetMs_ = visitor.visitLong(hasTimeSinceLastWakeupSetMs(), this.timeSinceLastWakeupSetMs_, other.hasTimeSinceLastWakeupSetMs(), other.timeSinceLastWakeupSetMs_);
                this.timeChangeEventCount_ = visitor.visitLong(hasTimeChangeEventCount(), this.timeChangeEventCount_, other.hasTimeChangeEventCount(), other.timeChangeEventCount_);
                this.deviceIdleUserWhitelistAppIds_ = visitor.visitIntList(this.deviceIdleUserWhitelistAppIds_, other.deviceIdleUserWhitelistAppIds_);
                this.nextAlarmClockMetadata_ = visitor.visitList(this.nextAlarmClockMetadata_, other.nextAlarmClockMetadata_);
                this.pendingAlarmBatches_ = visitor.visitList(this.pendingAlarmBatches_, other.pendingAlarmBatches_);
                this.pendingUserBlockedBackgroundAlarms_ = visitor.visitList(this.pendingUserBlockedBackgroundAlarms_, other.pendingUserBlockedBackgroundAlarms_);
                this.pendingIdleUntil_ = (AlarmProto) visitor.visitMessage(this.pendingIdleUntil_, other.pendingIdleUntil_);
                this.pendingWhileIdleAlarms_ = visitor.visitList(this.pendingWhileIdleAlarms_, other.pendingWhileIdleAlarms_);
                this.nextWakeFromIdle_ = (AlarmProto) visitor.visitMessage(this.nextWakeFromIdle_, other.nextWakeFromIdle_);
                this.pastDueNonWakeupAlarms_ = visitor.visitList(this.pastDueNonWakeupAlarms_, other.pastDueNonWakeupAlarms_);
                this.delayedAlarmCount_ = visitor.visitInt(hasDelayedAlarmCount(), this.delayedAlarmCount_, other.hasDelayedAlarmCount(), other.delayedAlarmCount_);
                this.totalDelayTimeMs_ = visitor.visitLong(hasTotalDelayTimeMs(), this.totalDelayTimeMs_, other.hasTotalDelayTimeMs(), other.totalDelayTimeMs_);
                this.maxDelayDurationMs_ = visitor.visitLong(hasMaxDelayDurationMs(), this.maxDelayDurationMs_, other.hasMaxDelayDurationMs(), other.maxDelayDurationMs_);
                this.maxNonInteractiveDurationMs_ = visitor.visitLong(hasMaxNonInteractiveDurationMs(), this.maxNonInteractiveDurationMs_, other.hasMaxNonInteractiveDurationMs(), other.maxNonInteractiveDurationMs_);
                this.broadcastRefCount_ = visitor.visitInt(hasBroadcastRefCount(), this.broadcastRefCount_, other.hasBroadcastRefCount(), other.broadcastRefCount_);
                this.pendingIntentSendCount_ = visitor.visitInt(hasPendingIntentSendCount(), this.pendingIntentSendCount_, other.hasPendingIntentSendCount(), other.pendingIntentSendCount_);
                this.pendingIntentFinishCount_ = visitor.visitInt(hasPendingIntentFinishCount(), this.pendingIntentFinishCount_, other.hasPendingIntentFinishCount(), other.pendingIntentFinishCount_);
                this.listenerSendCount_ = visitor.visitInt(hasListenerSendCount(), this.listenerSendCount_, other.hasListenerSendCount(), other.listenerSendCount_);
                this.listenerFinishCount_ = visitor.visitInt(hasListenerFinishCount(), this.listenerFinishCount_, other.hasListenerFinishCount(), other.listenerFinishCount_);
                this.outstandingDeliveries_ = visitor.visitList(this.outstandingDeliveries_, other.outstandingDeliveries_);
                this.useAllowWhileIdleShortTime_ = visitor.visitIntList(this.useAllowWhileIdleShortTime_, other.useAllowWhileIdleShortTime_);
                this.lastAllowWhileIdleDispatchTimes_ = visitor.visitList(this.lastAllowWhileIdleDispatchTimes_, other.lastAllowWhileIdleDispatchTimes_);
                this.recentProblems_ = (LocalLogProto) visitor.visitMessage(this.recentProblems_, other.recentProblems_);
                this.topAlarms_ = visitor.visitList(this.topAlarms_, other.topAlarms_);
                this.alarmStats_ = visitor.visitList(this.alarmStats_, other.alarmStats_);
                this.allowWhileIdleDispatches_ = visitor.visitList(this.allowWhileIdleDispatches_, other.allowWhileIdleDispatches_);
                this.recentWakeupHistory_ = visitor.visitList(this.recentWakeupHistory_, other.recentWakeupHistory_);
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
                                this.currentTime_ = input.readInt64();
                                break;
                            case 16:
                                this.bitField0_ |= 2;
                                this.elapsedRealtime_ = input.readInt64();
                                break;
                            case 24:
                                this.bitField0_ |= 4;
                                this.lastTimeChangeClockTime_ = input.readInt64();
                                break;
                            case 32:
                                this.bitField0_ |= 8;
                                this.lastTimeChangeRealtime_ = input.readInt64();
                                break;
                            case 42:
                                ConstantsProto.Builder subBuilder = null;
                                if ((this.bitField0_ & 16) == 16) {
                                    subBuilder = (ConstantsProto.Builder) this.settings_.toBuilder();
                                }
                                this.settings_ = (ConstantsProto) input.readMessage(ConstantsProto.parser(), extensionRegistry);
                                if (subBuilder != null) {
                                    subBuilder.mergeFrom((GeneratedMessageLite) this.settings_);
                                    this.settings_ = (ConstantsProto) subBuilder.buildPartial();
                                }
                                this.bitField0_ |= 16;
                                break;
                            case 50:
                                ForceAppStandbyTrackerProto.Builder subBuilder2 = null;
                                if ((this.bitField0_ & 32) == 32) {
                                    subBuilder2 = (ForceAppStandbyTrackerProto.Builder) this.forceAppStandbyTracker_.toBuilder();
                                }
                                this.forceAppStandbyTracker_ = (ForceAppStandbyTrackerProto) input.readMessage(ForceAppStandbyTrackerProto.parser(), extensionRegistry);
                                if (subBuilder2 != null) {
                                    subBuilder2.mergeFrom((GeneratedMessageLite) this.forceAppStandbyTracker_);
                                    this.forceAppStandbyTracker_ = (ForceAppStandbyTrackerProto) subBuilder2.buildPartial();
                                }
                                this.bitField0_ |= 32;
                                break;
                            case 56:
                                this.bitField0_ |= 64;
                                this.isInteractive_ = input.readBool();
                                break;
                            case 64:
                                this.bitField0_ |= 128;
                                this.timeSinceNonInteractiveMs_ = input.readInt64();
                                break;
                            case 72:
                                this.bitField0_ |= 256;
                                this.maxWakeupDelayMs_ = input.readInt64();
                                break;
                            case 80:
                                this.bitField0_ |= 512;
                                this.timeSinceLastDispatchMs_ = input.readInt64();
                                break;
                            case 88:
                                this.bitField0_ |= 1024;
                                this.timeUntilNextNonWakeupDeliveryMs_ = input.readInt64();
                                break;
                            case 96:
                                this.bitField0_ |= 2048;
                                this.timeUntilNextNonWakeupAlarmMs_ = input.readInt64();
                                break;
                            case 104:
                                this.bitField0_ |= 4096;
                                this.timeUntilNextWakeupMs_ = input.readInt64();
                                break;
                            case 112:
                                this.bitField0_ |= 8192;
                                this.timeSinceLastWakeupMs_ = input.readInt64();
                                break;
                            case 120:
                                this.bitField0_ |= 16384;
                                this.timeSinceLastWakeupSetMs_ = input.readInt64();
                                break;
                            case 128:
                                this.bitField0_ |= 32768;
                                this.timeChangeEventCount_ = input.readInt64();
                                break;
                            case 136:
                                if (!this.deviceIdleUserWhitelistAppIds_.isModifiable()) {
                                    this.deviceIdleUserWhitelistAppIds_ = GeneratedMessageLite.mutableCopy(this.deviceIdleUserWhitelistAppIds_);
                                }
                                this.deviceIdleUserWhitelistAppIds_.addInt(input.readInt32());
                                break;
                            case 138:
                                int limit = input.pushLimit(input.readRawVarint32());
                                if (!this.deviceIdleUserWhitelistAppIds_.isModifiable() && input.getBytesUntilLimit() > 0) {
                                    this.deviceIdleUserWhitelistAppIds_ = GeneratedMessageLite.mutableCopy(this.deviceIdleUserWhitelistAppIds_);
                                }
                                while (input.getBytesUntilLimit() > 0) {
                                    this.deviceIdleUserWhitelistAppIds_.addInt(input.readInt32());
                                }
                                input.popLimit(limit);
                                break;
                            case 146:
                                if (!this.nextAlarmClockMetadata_.isModifiable()) {
                                    this.nextAlarmClockMetadata_ = GeneratedMessageLite.mutableCopy(this.nextAlarmClockMetadata_);
                                }
                                this.nextAlarmClockMetadata_.add((AlarmClockMetadataProto) input.readMessage(AlarmClockMetadataProto.parser(), extensionRegistry));
                                break;
                            case 154:
                                if (!this.pendingAlarmBatches_.isModifiable()) {
                                    this.pendingAlarmBatches_ = GeneratedMessageLite.mutableCopy(this.pendingAlarmBatches_);
                                }
                                this.pendingAlarmBatches_.add((BatchProto) input.readMessage(BatchProto.parser(), extensionRegistry));
                                break;
                            case 162:
                                if (!this.pendingUserBlockedBackgroundAlarms_.isModifiable()) {
                                    this.pendingUserBlockedBackgroundAlarms_ = GeneratedMessageLite.mutableCopy(this.pendingUserBlockedBackgroundAlarms_);
                                }
                                this.pendingUserBlockedBackgroundAlarms_.add((AlarmProto) input.readMessage(AlarmProto.parser(), extensionRegistry));
                                break;
                            case 170:
                                AlarmProto.Builder subBuilder3 = null;
                                if ((this.bitField0_ & 65536) == 65536) {
                                    subBuilder3 = (AlarmProto.Builder) this.pendingIdleUntil_.toBuilder();
                                }
                                this.pendingIdleUntil_ = (AlarmProto) input.readMessage(AlarmProto.parser(), extensionRegistry);
                                if (subBuilder3 != null) {
                                    subBuilder3.mergeFrom((GeneratedMessageLite) this.pendingIdleUntil_);
                                    this.pendingIdleUntil_ = (AlarmProto) subBuilder3.buildPartial();
                                }
                                this.bitField0_ |= 65536;
                                break;
                            case 178:
                                if (!this.pendingWhileIdleAlarms_.isModifiable()) {
                                    this.pendingWhileIdleAlarms_ = GeneratedMessageLite.mutableCopy(this.pendingWhileIdleAlarms_);
                                }
                                this.pendingWhileIdleAlarms_.add((AlarmProto) input.readMessage(AlarmProto.parser(), extensionRegistry));
                                break;
                            case AtomsProto.Atom.TOMB_STONE_OCCURRED_FIELD_NUMBER:
                                AlarmProto.Builder subBuilder4 = null;
                                if ((this.bitField0_ & 131072) == 131072) {
                                    subBuilder4 = (AlarmProto.Builder) this.nextWakeFromIdle_.toBuilder();
                                }
                                this.nextWakeFromIdle_ = (AlarmProto) input.readMessage(AlarmProto.parser(), extensionRegistry);
                                if (subBuilder4 != null) {
                                    subBuilder4.mergeFrom((GeneratedMessageLite) this.nextWakeFromIdle_);
                                    this.nextWakeFromIdle_ = (AlarmProto) subBuilder4.buildPartial();
                                }
                                this.bitField0_ |= 131072;
                                break;
                            case AtomsProto.Atom.MEDIAMETRICS_AUDIOTRACK_REPORTED_FIELD_NUMBER:
                                if (!this.pastDueNonWakeupAlarms_.isModifiable()) {
                                    this.pastDueNonWakeupAlarms_ = GeneratedMessageLite.mutableCopy(this.pastDueNonWakeupAlarms_);
                                }
                                this.pastDueNonWakeupAlarms_.add((AlarmProto) input.readMessage(AlarmProto.parser(), extensionRegistry));
                                break;
                            case 200:
                                this.bitField0_ |= 262144;
                                this.delayedAlarmCount_ = input.readInt32();
                                break;
                            case AtomsProto.Atom.CONTENT_CAPTURE_SESSION_EVENTS_FIELD_NUMBER:
                                this.bitField0_ |= 524288;
                                this.totalDelayTimeMs_ = input.readInt64();
                                break;
                            case AtomsProto.Atom.APP_PERMISSION_FRAGMENT_VIEWED_FIELD_NUMBER:
                                this.bitField0_ |= 1048576;
                                this.maxDelayDurationMs_ = input.readInt64();
                                break;
                            case AtomsProto.Atom.BACK_GESTURE_REPORTED_REPORTED_FIELD_NUMBER:
                                this.bitField0_ |= 2097152;
                                this.maxNonInteractiveDurationMs_ = input.readInt64();
                                break;
                            case 232:
                                this.bitField0_ |= 4194304;
                                this.broadcastRefCount_ = input.readInt32();
                                break;
                            case FINGERPRINT_ENROLLING_VALUE:
                                this.bitField0_ |= 8388608;
                                this.pendingIntentSendCount_ = input.readInt32();
                                break;
                            case FINGERPRINT_ENROLL_FINISH_SETUP_VALUE:
                                this.bitField0_ |= 16777216;
                                this.pendingIntentFinishCount_ = input.readInt32();
                                break;
                            case 256:
                                this.bitField0_ |= 33554432;
                                this.listenerSendCount_ = input.readInt32();
                                break;
                            case 264:
                                this.bitField0_ |= 67108864;
                                this.listenerFinishCount_ = input.readInt32();
                                break;
                            case 274:
                                if (!this.outstandingDeliveries_.isModifiable()) {
                                    this.outstandingDeliveries_ = GeneratedMessageLite.mutableCopy(this.outstandingDeliveries_);
                                }
                                this.outstandingDeliveries_.add((InFlightProto) input.readMessage(InFlightProto.parser(), extensionRegistry));
                                break;
                            case 280:
                                if (!this.useAllowWhileIdleShortTime_.isModifiable()) {
                                    this.useAllowWhileIdleShortTime_ = GeneratedMessageLite.mutableCopy(this.useAllowWhileIdleShortTime_);
                                }
                                this.useAllowWhileIdleShortTime_.addInt(input.readInt32());
                                break;
                            case 282:
                                int limit2 = input.pushLimit(input.readRawVarint32());
                                if (!this.useAllowWhileIdleShortTime_.isModifiable() && input.getBytesUntilLimit() > 0) {
                                    this.useAllowWhileIdleShortTime_ = GeneratedMessageLite.mutableCopy(this.useAllowWhileIdleShortTime_);
                                }
                                while (input.getBytesUntilLimit() > 0) {
                                    this.useAllowWhileIdleShortTime_.addInt(input.readInt32());
                                }
                                input.popLimit(limit2);
                                break;
                            case 290:
                                if (!this.lastAllowWhileIdleDispatchTimes_.isModifiable()) {
                                    this.lastAllowWhileIdleDispatchTimes_ = GeneratedMessageLite.mutableCopy(this.lastAllowWhileIdleDispatchTimes_);
                                }
                                this.lastAllowWhileIdleDispatchTimes_.add((LastAllowWhileIdleDispatch) input.readMessage(LastAllowWhileIdleDispatch.parser(), extensionRegistry));
                                break;
                            case 298:
                                LocalLogProto.Builder subBuilder5 = null;
                                if ((this.bitField0_ & 134217728) == 134217728) {
                                    subBuilder5 = (LocalLogProto.Builder) this.recentProblems_.toBuilder();
                                }
                                this.recentProblems_ = (LocalLogProto) input.readMessage(LocalLogProto.parser(), extensionRegistry);
                                if (subBuilder5 != null) {
                                    subBuilder5.mergeFrom((GeneratedMessageLite) this.recentProblems_);
                                    this.recentProblems_ = (LocalLogProto) subBuilder5.buildPartial();
                                }
                                this.bitField0_ |= 134217728;
                                break;
                            case 306:
                                if (!this.topAlarms_.isModifiable()) {
                                    this.topAlarms_ = GeneratedMessageLite.mutableCopy(this.topAlarms_);
                                }
                                this.topAlarms_.add((TopAlarm) input.readMessage(TopAlarm.parser(), extensionRegistry));
                                break;
                            case 314:
                                if (!this.alarmStats_.isModifiable()) {
                                    this.alarmStats_ = GeneratedMessageLite.mutableCopy(this.alarmStats_);
                                }
                                this.alarmStats_.add((AlarmStat) input.readMessage(AlarmStat.parser(), extensionRegistry));
                                break;
                            case 322:
                                if (!this.allowWhileIdleDispatches_.isModifiable()) {
                                    this.allowWhileIdleDispatches_ = GeneratedMessageLite.mutableCopy(this.allowWhileIdleDispatches_);
                                }
                                this.allowWhileIdleDispatches_.add((IdleDispatchEntryProto) input.readMessage(IdleDispatchEntryProto.parser(), extensionRegistry));
                                break;
                            case 330:
                                if (!this.recentWakeupHistory_.isModifiable()) {
                                    this.recentWakeupHistory_ = GeneratedMessageLite.mutableCopy(this.recentWakeupHistory_);
                                }
                                this.recentWakeupHistory_.add((WakeupEventProto) input.readMessage(WakeupEventProto.parser(), extensionRegistry));
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
                    synchronized (AlarmManagerServiceDumpProto.class) {
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

    public static AlarmManagerServiceDumpProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<AlarmManagerServiceDumpProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
