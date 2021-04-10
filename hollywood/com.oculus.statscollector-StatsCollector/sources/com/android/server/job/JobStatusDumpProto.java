package com.android.server.job;

import android.content.ClipDataProto;
import android.content.ComponentNameProto;
import android.content.IntentProto;
import android.net.NetworkProto;
import android.net.NetworkRequestProto;
import android.os.BundleProto;
import android.os.PersistableBundleProto;
import com.android.os.AtomsProto;
import com.android.server.job.GrantedUriPermissionsDumpProto;
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

public final class JobStatusDumpProto extends GeneratedMessageLite<JobStatusDumpProto, Builder> implements JobStatusDumpProtoOrBuilder {
    public static final int CALLING_UID_FIELD_NUMBER = 1;
    public static final int CHANGED_AUTHORITIES_FIELD_NUMBER = 12;
    public static final int CHANGED_URIS_FIELD_NUMBER = 13;
    private static final JobStatusDumpProto DEFAULT_INSTANCE = new JobStatusDumpProto();
    public static final int ENQUEUE_DURATION_MS_FIELD_NUMBER = 18;
    public static final int EXECUTING_WORK_FIELD_NUMBER = 16;
    public static final int IMPLICIT_CONSTRAINTS_FIELD_NUMBER = 25;
    public static final int INTERNAL_FLAGS_FIELD_NUMBER = 24;
    public static final int IS_DOZE_WHITELISTED_FIELD_NUMBER = 10;
    public static final int IS_EXEMPTED_FROM_APP_STANDBY_FIELD_NUMBER = 27;
    public static final int IS_UID_ACTIVE_FIELD_NUMBER = 26;
    public static final int JOB_INFO_FIELD_NUMBER = 6;
    public static final int LAST_FAILED_RUN_TIME_FIELD_NUMBER = 23;
    public static final int LAST_SUCCESSFUL_RUN_TIME_FIELD_NUMBER = 22;
    public static final int NETWORK_FIELD_NUMBER = 14;
    public static final int NUM_FAILURES_FIELD_NUMBER = 21;
    private static volatile Parser<JobStatusDumpProto> PARSER = null;
    public static final int PENDING_WORK_FIELD_NUMBER = 15;
    public static final int REQUIRED_CONSTRAINTS_FIELD_NUMBER = 7;
    public static final int SATISFIED_CONSTRAINTS_FIELD_NUMBER = 8;
    public static final int SOURCE_PACKAGE_NAME_FIELD_NUMBER = 5;
    public static final int SOURCE_UID_FIELD_NUMBER = 3;
    public static final int SOURCE_USER_ID_FIELD_NUMBER = 4;
    public static final int STANDBY_BUCKET_FIELD_NUMBER = 17;
    public static final int TAG_FIELD_NUMBER = 2;
    public static final int TIME_UNTIL_EARLIEST_RUNTIME_MS_FIELD_NUMBER = 19;
    public static final int TIME_UNTIL_LATEST_RUNTIME_MS_FIELD_NUMBER = 20;
    public static final int TRACKING_CONTROLLERS_FIELD_NUMBER = 11;
    public static final int UNSATISFIED_CONSTRAINTS_FIELD_NUMBER = 9;
    private static final Internal.ListAdapter.Converter<Integer, ConstraintEnum> requiredConstraints_converter_ = new Internal.ListAdapter.Converter<Integer, ConstraintEnum>() {
        /* class com.android.server.job.JobStatusDumpProto.AnonymousClass1 */

        public ConstraintEnum convert(Integer from) {
            ConstraintEnum result = ConstraintEnum.forNumber(from.intValue());
            return result == null ? ConstraintEnum.CONSTRAINT_UNKNOWN : result;
        }
    };
    private static final Internal.ListAdapter.Converter<Integer, ConstraintEnum> satisfiedConstraints_converter_ = new Internal.ListAdapter.Converter<Integer, ConstraintEnum>() {
        /* class com.android.server.job.JobStatusDumpProto.AnonymousClass2 */

        public ConstraintEnum convert(Integer from) {
            ConstraintEnum result = ConstraintEnum.forNumber(from.intValue());
            return result == null ? ConstraintEnum.CONSTRAINT_UNKNOWN : result;
        }
    };
    private static final Internal.ListAdapter.Converter<Integer, TrackingController> trackingControllers_converter_ = new Internal.ListAdapter.Converter<Integer, TrackingController>() {
        /* class com.android.server.job.JobStatusDumpProto.AnonymousClass4 */

        public TrackingController convert(Integer from) {
            TrackingController result = TrackingController.forNumber(from.intValue());
            return result == null ? TrackingController.TRACKING_BATTERY : result;
        }
    };
    private static final Internal.ListAdapter.Converter<Integer, ConstraintEnum> unsatisfiedConstraints_converter_ = new Internal.ListAdapter.Converter<Integer, ConstraintEnum>() {
        /* class com.android.server.job.JobStatusDumpProto.AnonymousClass3 */

        public ConstraintEnum convert(Integer from) {
            ConstraintEnum result = ConstraintEnum.forNumber(from.intValue());
            return result == null ? ConstraintEnum.CONSTRAINT_UNKNOWN : result;
        }
    };
    private int bitField0_;
    private int callingUid_ = 0;
    private Internal.ProtobufList<String> changedAuthorities_ = GeneratedMessageLite.emptyProtobufList();
    private Internal.ProtobufList<String> changedUris_ = GeneratedMessageLite.emptyProtobufList();
    private long enqueueDurationMs_ = 0;
    private Internal.ProtobufList<JobWorkItem> executingWork_ = emptyProtobufList();
    private ImplicitConstraints implicitConstraints_;
    private long internalFlags_ = 0;
    private boolean isDozeWhitelisted_ = false;
    private boolean isExemptedFromAppStandby_ = false;
    private boolean isUidActive_ = false;
    private JobInfo jobInfo_;
    private long lastFailedRunTime_ = 0;
    private long lastSuccessfulRunTime_ = 0;
    private NetworkProto network_;
    private int numFailures_ = 0;
    private Internal.ProtobufList<JobWorkItem> pendingWork_ = emptyProtobufList();
    private Internal.IntList requiredConstraints_ = emptyIntList();
    private Internal.IntList satisfiedConstraints_ = emptyIntList();
    private String sourcePackageName_ = "";
    private int sourceUid_ = 0;
    private int sourceUserId_ = 0;
    private int standbyBucket_ = 0;
    private String tag_ = "";
    private long timeUntilEarliestRuntimeMs_ = 0;
    private long timeUntilLatestRuntimeMs_ = 0;
    private Internal.IntList trackingControllers_ = emptyIntList();
    private Internal.IntList unsatisfiedConstraints_ = emptyIntList();

    public interface ImplicitConstraintsOrBuilder extends MessageLiteOrBuilder {
        boolean getIsNotDozing();

        boolean getIsNotRestrictedInBg();

        boolean hasIsNotDozing();

        boolean hasIsNotRestrictedInBg();
    }

    public interface JobInfoOrBuilder extends MessageLiteOrBuilder {
        JobInfo.Backoff getBackoffPolicy();

        ClipDataProto getClipData();

        PersistableBundleProto getExtras();

        int getFlags();

        GrantedUriPermissionsDumpProto getGrantedUriPermissions();

        boolean getHasEarlyConstraint();

        boolean getHasLateConstraint();

        boolean getIsPeriodic();

        boolean getIsPersisted();

        long getMaxExecutionDelayMs();

        long getMinLatencyMs();

        long getPeriodFlexMs();

        long getPeriodIntervalMs();

        int getPriority();

        NetworkRequestProto getRequiredNetwork();

        boolean getRequiresBatteryNotLow();

        boolean getRequiresCharging();

        boolean getRequiresDeviceIdle();

        ComponentNameProto getService();

        long getTotalNetworkBytes();

        BundleProto getTransientExtras();

        long getTriggerContentMaxDelayMs();

        long getTriggerContentUpdateDelayMs();

        JobInfo.TriggerContentUri getTriggerContentUris(int i);

        int getTriggerContentUrisCount();

        List<JobInfo.TriggerContentUri> getTriggerContentUrisList();

        boolean hasBackoffPolicy();

        boolean hasClipData();

        boolean hasExtras();

        boolean hasFlags();

        boolean hasGrantedUriPermissions();

        boolean hasHasEarlyConstraint();

        boolean hasHasLateConstraint();

        boolean hasIsPeriodic();

        boolean hasIsPersisted();

        boolean hasMaxExecutionDelayMs();

        boolean hasMinLatencyMs();

        boolean hasPeriodFlexMs();

        boolean hasPeriodIntervalMs();

        boolean hasPriority();

        boolean hasRequiredNetwork();

        boolean hasRequiresBatteryNotLow();

        boolean hasRequiresCharging();

        boolean hasRequiresDeviceIdle();

        boolean hasService();

        boolean hasTotalNetworkBytes();

        boolean hasTransientExtras();

        boolean hasTriggerContentMaxDelayMs();

        boolean hasTriggerContentUpdateDelayMs();
    }

    public interface JobWorkItemOrBuilder extends MessageLiteOrBuilder {
        int getDeliveryCount();

        IntentProto getIntent();

        GrantedUriPermissionsDumpProto getUriGrants();

        int getWorkId();

        boolean hasDeliveryCount();

        boolean hasIntent();

        boolean hasUriGrants();

        boolean hasWorkId();
    }

    private JobStatusDumpProto() {
    }

    public enum TrackingController implements Internal.EnumLite {
        TRACKING_BATTERY(0),
        TRACKING_CONNECTIVITY(1),
        TRACKING_CONTENT(2),
        TRACKING_IDLE(3),
        TRACKING_STORAGE(4),
        TRACKING_TIME(5),
        TRACKING_QUOTA(6);
        
        public static final int TRACKING_BATTERY_VALUE = 0;
        public static final int TRACKING_CONNECTIVITY_VALUE = 1;
        public static final int TRACKING_CONTENT_VALUE = 2;
        public static final int TRACKING_IDLE_VALUE = 3;
        public static final int TRACKING_QUOTA_VALUE = 6;
        public static final int TRACKING_STORAGE_VALUE = 4;
        public static final int TRACKING_TIME_VALUE = 5;
        private static final Internal.EnumLiteMap<TrackingController> internalValueMap = new Internal.EnumLiteMap<TrackingController>() {
            /* class com.android.server.job.JobStatusDumpProto.TrackingController.AnonymousClass1 */

            @Override // com.google.protobuf.Internal.EnumLiteMap
            public TrackingController findValueByNumber(int number) {
                return TrackingController.forNumber(number);
            }
        };
        private final int value;

        @Override // com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        @Deprecated
        public static TrackingController valueOf(int value2) {
            return forNumber(value2);
        }

        public static TrackingController forNumber(int value2) {
            switch (value2) {
                case 0:
                    return TRACKING_BATTERY;
                case 1:
                    return TRACKING_CONNECTIVITY;
                case 2:
                    return TRACKING_CONTENT;
                case 3:
                    return TRACKING_IDLE;
                case 4:
                    return TRACKING_STORAGE;
                case 5:
                    return TRACKING_TIME;
                case 6:
                    return TRACKING_QUOTA;
                default:
                    return null;
            }
        }

        public static Internal.EnumLiteMap<TrackingController> internalGetValueMap() {
            return internalValueMap;
        }

        private TrackingController(int value2) {
            this.value = value2;
        }
    }

    public enum Bucket implements Internal.EnumLite {
        ACTIVE(0),
        WORKING_SET(1),
        FREQUENT(2),
        RARE(3),
        NEVER(4);
        
        public static final int ACTIVE_VALUE = 0;
        public static final int FREQUENT_VALUE = 2;
        public static final int NEVER_VALUE = 4;
        public static final int RARE_VALUE = 3;
        public static final int WORKING_SET_VALUE = 1;
        private static final Internal.EnumLiteMap<Bucket> internalValueMap = new Internal.EnumLiteMap<Bucket>() {
            /* class com.android.server.job.JobStatusDumpProto.Bucket.AnonymousClass1 */

            @Override // com.google.protobuf.Internal.EnumLiteMap
            public Bucket findValueByNumber(int number) {
                return Bucket.forNumber(number);
            }
        };
        private final int value;

        @Override // com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        @Deprecated
        public static Bucket valueOf(int value2) {
            return forNumber(value2);
        }

        public static Bucket forNumber(int value2) {
            if (value2 == 0) {
                return ACTIVE;
            }
            if (value2 == 1) {
                return WORKING_SET;
            }
            if (value2 == 2) {
                return FREQUENT;
            }
            if (value2 == 3) {
                return RARE;
            }
            if (value2 != 4) {
                return null;
            }
            return NEVER;
        }

        public static Internal.EnumLiteMap<Bucket> internalGetValueMap() {
            return internalValueMap;
        }

        private Bucket(int value2) {
            this.value = value2;
        }
    }

    public static final class JobInfo extends GeneratedMessageLite<JobInfo, Builder> implements JobInfoOrBuilder {
        public static final int BACKOFF_POLICY_FIELD_NUMBER = 22;
        public static final int CLIP_DATA_FIELD_NUMBER = 16;
        private static final JobInfo DEFAULT_INSTANCE = new JobInfo();
        public static final int EXTRAS_FIELD_NUMBER = 14;
        public static final int FLAGS_FIELD_NUMBER = 7;
        public static final int GRANTED_URI_PERMISSIONS_FIELD_NUMBER = 17;
        public static final int HAS_EARLY_CONSTRAINT_FIELD_NUMBER = 23;
        public static final int HAS_LATE_CONSTRAINT_FIELD_NUMBER = 24;
        public static final int IS_PERIODIC_FIELD_NUMBER = 2;
        public static final int IS_PERSISTED_FIELD_NUMBER = 5;
        public static final int MAX_EXECUTION_DELAY_MS_FIELD_NUMBER = 21;
        public static final int MIN_LATENCY_MS_FIELD_NUMBER = 20;
        private static volatile Parser<JobInfo> PARSER = null;
        public static final int PERIOD_FLEX_MS_FIELD_NUMBER = 4;
        public static final int PERIOD_INTERVAL_MS_FIELD_NUMBER = 3;
        public static final int PRIORITY_FIELD_NUMBER = 6;
        public static final int REQUIRED_NETWORK_FIELD_NUMBER = 18;
        public static final int REQUIRES_BATTERY_NOT_LOW_FIELD_NUMBER = 9;
        public static final int REQUIRES_CHARGING_FIELD_NUMBER = 8;
        public static final int REQUIRES_DEVICE_IDLE_FIELD_NUMBER = 10;
        public static final int SERVICE_FIELD_NUMBER = 1;
        public static final int TOTAL_NETWORK_BYTES_FIELD_NUMBER = 19;
        public static final int TRANSIENT_EXTRAS_FIELD_NUMBER = 15;
        public static final int TRIGGER_CONTENT_MAX_DELAY_MS_FIELD_NUMBER = 13;
        public static final int TRIGGER_CONTENT_UPDATE_DELAY_MS_FIELD_NUMBER = 12;
        public static final int TRIGGER_CONTENT_URIS_FIELD_NUMBER = 11;
        private Backoff backoffPolicy_;
        private int bitField0_;
        private ClipDataProto clipData_;
        private PersistableBundleProto extras_;
        private int flags_ = 0;
        private GrantedUriPermissionsDumpProto grantedUriPermissions_;
        private boolean hasEarlyConstraint_ = false;
        private boolean hasLateConstraint_ = false;
        private boolean isPeriodic_ = false;
        private boolean isPersisted_ = false;
        private long maxExecutionDelayMs_ = 0;
        private long minLatencyMs_ = 0;
        private long periodFlexMs_ = 0;
        private long periodIntervalMs_ = 0;
        private int priority_ = 0;
        private NetworkRequestProto requiredNetwork_;
        private boolean requiresBatteryNotLow_ = false;
        private boolean requiresCharging_ = false;
        private boolean requiresDeviceIdle_ = false;
        private ComponentNameProto service_;
        private long totalNetworkBytes_ = 0;
        private BundleProto transientExtras_;
        private long triggerContentMaxDelayMs_ = 0;
        private long triggerContentUpdateDelayMs_ = 0;
        private Internal.ProtobufList<TriggerContentUri> triggerContentUris_ = emptyProtobufList();

        public interface BackoffOrBuilder extends MessageLiteOrBuilder {
            long getInitialBackoffMs();

            Backoff.Policy getPolicy();

            boolean hasInitialBackoffMs();

            boolean hasPolicy();
        }

        public interface TriggerContentUriOrBuilder extends MessageLiteOrBuilder {
            int getFlags();

            String getUri();

            ByteString getUriBytes();

            boolean hasFlags();

            boolean hasUri();
        }

        private JobInfo() {
        }

        public static final class TriggerContentUri extends GeneratedMessageLite<TriggerContentUri, Builder> implements TriggerContentUriOrBuilder {
            private static final TriggerContentUri DEFAULT_INSTANCE = new TriggerContentUri();
            public static final int FLAGS_FIELD_NUMBER = 1;
            private static volatile Parser<TriggerContentUri> PARSER = null;
            public static final int URI_FIELD_NUMBER = 2;
            private int bitField0_;
            private int flags_ = 0;
            private String uri_ = "";

            private TriggerContentUri() {
            }

            @Override // com.android.server.job.JobStatusDumpProto.JobInfo.TriggerContentUriOrBuilder
            public boolean hasFlags() {
                return (this.bitField0_ & 1) == 1;
            }

            @Override // com.android.server.job.JobStatusDumpProto.JobInfo.TriggerContentUriOrBuilder
            public int getFlags() {
                return this.flags_;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void setFlags(int value) {
                this.bitField0_ |= 1;
                this.flags_ = value;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void clearFlags() {
                this.bitField0_ &= -2;
                this.flags_ = 0;
            }

            @Override // com.android.server.job.JobStatusDumpProto.JobInfo.TriggerContentUriOrBuilder
            public boolean hasUri() {
                return (this.bitField0_ & 2) == 2;
            }

            @Override // com.android.server.job.JobStatusDumpProto.JobInfo.TriggerContentUriOrBuilder
            public String getUri() {
                return this.uri_;
            }

            @Override // com.android.server.job.JobStatusDumpProto.JobInfo.TriggerContentUriOrBuilder
            public ByteString getUriBytes() {
                return ByteString.copyFromUtf8(this.uri_);
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void setUri(String value) {
                if (value != null) {
                    this.bitField0_ |= 2;
                    this.uri_ = value;
                    return;
                }
                throw new NullPointerException();
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void clearUri() {
                this.bitField0_ &= -3;
                this.uri_ = getDefaultInstance().getUri();
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void setUriBytes(ByteString value) {
                if (value != null) {
                    this.bitField0_ |= 2;
                    this.uri_ = value.toStringUtf8();
                    return;
                }
                throw new NullPointerException();
            }

            @Override // com.google.protobuf.MessageLite
            public void writeTo(CodedOutputStream output) throws IOException {
                if ((this.bitField0_ & 1) == 1) {
                    output.writeInt32(1, this.flags_);
                }
                if ((this.bitField0_ & 2) == 2) {
                    output.writeString(2, getUri());
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
                    size2 = 0 + CodedOutputStream.computeInt32Size(1, this.flags_);
                }
                if ((this.bitField0_ & 2) == 2) {
                    size2 += CodedOutputStream.computeStringSize(2, getUri());
                }
                int size3 = size2 + this.unknownFields.getSerializedSize();
                this.memoizedSerializedSize = size3;
                return size3;
            }

            public static TriggerContentUri parseFrom(ByteString data) throws InvalidProtocolBufferException {
                return (TriggerContentUri) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
            }

            public static TriggerContentUri parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return (TriggerContentUri) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
            }

            public static TriggerContentUri parseFrom(byte[] data) throws InvalidProtocolBufferException {
                return (TriggerContentUri) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
            }

            public static TriggerContentUri parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return (TriggerContentUri) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
            }

            public static TriggerContentUri parseFrom(InputStream input) throws IOException {
                return (TriggerContentUri) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
            }

            public static TriggerContentUri parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (TriggerContentUri) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static TriggerContentUri parseDelimitedFrom(InputStream input) throws IOException {
                return (TriggerContentUri) parseDelimitedFrom(DEFAULT_INSTANCE, input);
            }

            public static TriggerContentUri parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (TriggerContentUri) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static TriggerContentUri parseFrom(CodedInputStream input) throws IOException {
                return (TriggerContentUri) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
            }

            public static TriggerContentUri parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (TriggerContentUri) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static Builder newBuilder() {
                return (Builder) DEFAULT_INSTANCE.toBuilder();
            }

            public static Builder newBuilder(TriggerContentUri prototype) {
                return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
            }

            public static final class Builder extends GeneratedMessageLite.Builder<TriggerContentUri, Builder> implements TriggerContentUriOrBuilder {
                private Builder() {
                    super(TriggerContentUri.DEFAULT_INSTANCE);
                }

                @Override // com.android.server.job.JobStatusDumpProto.JobInfo.TriggerContentUriOrBuilder
                public boolean hasFlags() {
                    return ((TriggerContentUri) this.instance).hasFlags();
                }

                @Override // com.android.server.job.JobStatusDumpProto.JobInfo.TriggerContentUriOrBuilder
                public int getFlags() {
                    return ((TriggerContentUri) this.instance).getFlags();
                }

                public Builder setFlags(int value) {
                    copyOnWrite();
                    ((TriggerContentUri) this.instance).setFlags(value);
                    return this;
                }

                public Builder clearFlags() {
                    copyOnWrite();
                    ((TriggerContentUri) this.instance).clearFlags();
                    return this;
                }

                @Override // com.android.server.job.JobStatusDumpProto.JobInfo.TriggerContentUriOrBuilder
                public boolean hasUri() {
                    return ((TriggerContentUri) this.instance).hasUri();
                }

                @Override // com.android.server.job.JobStatusDumpProto.JobInfo.TriggerContentUriOrBuilder
                public String getUri() {
                    return ((TriggerContentUri) this.instance).getUri();
                }

                @Override // com.android.server.job.JobStatusDumpProto.JobInfo.TriggerContentUriOrBuilder
                public ByteString getUriBytes() {
                    return ((TriggerContentUri) this.instance).getUriBytes();
                }

                public Builder setUri(String value) {
                    copyOnWrite();
                    ((TriggerContentUri) this.instance).setUri(value);
                    return this;
                }

                public Builder clearUri() {
                    copyOnWrite();
                    ((TriggerContentUri) this.instance).clearUri();
                    return this;
                }

                public Builder setUriBytes(ByteString value) {
                    copyOnWrite();
                    ((TriggerContentUri) this.instance).setUriBytes(value);
                    return this;
                }
            }

            /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
            /* access modifiers changed from: protected */
            @Override // com.google.protobuf.GeneratedMessageLite
            public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
                switch (method) {
                    case NEW_MUTABLE_INSTANCE:
                        return new TriggerContentUri();
                    case IS_INITIALIZED:
                        return DEFAULT_INSTANCE;
                    case MAKE_IMMUTABLE:
                        return null;
                    case NEW_BUILDER:
                        return new Builder();
                    case VISIT:
                        GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                        TriggerContentUri other = (TriggerContentUri) arg1;
                        this.flags_ = visitor.visitInt(hasFlags(), this.flags_, other.hasFlags(), other.flags_);
                        this.uri_ = visitor.visitString(hasUri(), this.uri_, other.hasUri(), other.uri_);
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
                                    this.flags_ = input.readInt32();
                                } else if (tag == 18) {
                                    String s = input.readString();
                                    this.bitField0_ |= 2;
                                    this.uri_ = s;
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
                            synchronized (TriggerContentUri.class) {
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

            public static TriggerContentUri getDefaultInstance() {
                return DEFAULT_INSTANCE;
            }

            public static Parser<TriggerContentUri> parser() {
                return DEFAULT_INSTANCE.getParserForType();
            }
        }

        public static final class Backoff extends GeneratedMessageLite<Backoff, Builder> implements BackoffOrBuilder {
            private static final Backoff DEFAULT_INSTANCE = new Backoff();
            public static final int INITIAL_BACKOFF_MS_FIELD_NUMBER = 2;
            private static volatile Parser<Backoff> PARSER = null;
            public static final int POLICY_FIELD_NUMBER = 1;
            private int bitField0_;
            private long initialBackoffMs_ = 0;
            private int policy_ = 0;

            private Backoff() {
            }

            public enum Policy implements Internal.EnumLite {
                BACKOFF_POLICY_LINEAR(0),
                BACKOFF_POLICY_EXPONENTIAL(1);
                
                public static final int BACKOFF_POLICY_EXPONENTIAL_VALUE = 1;
                public static final int BACKOFF_POLICY_LINEAR_VALUE = 0;
                private static final Internal.EnumLiteMap<Policy> internalValueMap = new Internal.EnumLiteMap<Policy>() {
                    /* class com.android.server.job.JobStatusDumpProto.JobInfo.Backoff.Policy.AnonymousClass1 */

                    @Override // com.google.protobuf.Internal.EnumLiteMap
                    public Policy findValueByNumber(int number) {
                        return Policy.forNumber(number);
                    }
                };
                private final int value;

                @Override // com.google.protobuf.Internal.EnumLite
                public final int getNumber() {
                    return this.value;
                }

                @Deprecated
                public static Policy valueOf(int value2) {
                    return forNumber(value2);
                }

                public static Policy forNumber(int value2) {
                    if (value2 == 0) {
                        return BACKOFF_POLICY_LINEAR;
                    }
                    if (value2 != 1) {
                        return null;
                    }
                    return BACKOFF_POLICY_EXPONENTIAL;
                }

                public static Internal.EnumLiteMap<Policy> internalGetValueMap() {
                    return internalValueMap;
                }

                private Policy(int value2) {
                    this.value = value2;
                }
            }

            @Override // com.android.server.job.JobStatusDumpProto.JobInfo.BackoffOrBuilder
            public boolean hasPolicy() {
                return (this.bitField0_ & 1) == 1;
            }

            @Override // com.android.server.job.JobStatusDumpProto.JobInfo.BackoffOrBuilder
            public Policy getPolicy() {
                Policy result = Policy.forNumber(this.policy_);
                return result == null ? Policy.BACKOFF_POLICY_LINEAR : result;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void setPolicy(Policy value) {
                if (value != null) {
                    this.bitField0_ |= 1;
                    this.policy_ = value.getNumber();
                    return;
                }
                throw new NullPointerException();
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void clearPolicy() {
                this.bitField0_ &= -2;
                this.policy_ = 0;
            }

            @Override // com.android.server.job.JobStatusDumpProto.JobInfo.BackoffOrBuilder
            public boolean hasInitialBackoffMs() {
                return (this.bitField0_ & 2) == 2;
            }

            @Override // com.android.server.job.JobStatusDumpProto.JobInfo.BackoffOrBuilder
            public long getInitialBackoffMs() {
                return this.initialBackoffMs_;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void setInitialBackoffMs(long value) {
                this.bitField0_ |= 2;
                this.initialBackoffMs_ = value;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void clearInitialBackoffMs() {
                this.bitField0_ &= -3;
                this.initialBackoffMs_ = 0;
            }

            @Override // com.google.protobuf.MessageLite
            public void writeTo(CodedOutputStream output) throws IOException {
                if ((this.bitField0_ & 1) == 1) {
                    output.writeEnum(1, this.policy_);
                }
                if ((this.bitField0_ & 2) == 2) {
                    output.writeInt64(2, this.initialBackoffMs_);
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
                    size2 = 0 + CodedOutputStream.computeEnumSize(1, this.policy_);
                }
                if ((this.bitField0_ & 2) == 2) {
                    size2 += CodedOutputStream.computeInt64Size(2, this.initialBackoffMs_);
                }
                int size3 = size2 + this.unknownFields.getSerializedSize();
                this.memoizedSerializedSize = size3;
                return size3;
            }

            public static Backoff parseFrom(ByteString data) throws InvalidProtocolBufferException {
                return (Backoff) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
            }

            public static Backoff parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return (Backoff) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
            }

            public static Backoff parseFrom(byte[] data) throws InvalidProtocolBufferException {
                return (Backoff) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
            }

            public static Backoff parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return (Backoff) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
            }

            public static Backoff parseFrom(InputStream input) throws IOException {
                return (Backoff) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
            }

            public static Backoff parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (Backoff) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static Backoff parseDelimitedFrom(InputStream input) throws IOException {
                return (Backoff) parseDelimitedFrom(DEFAULT_INSTANCE, input);
            }

            public static Backoff parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (Backoff) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static Backoff parseFrom(CodedInputStream input) throws IOException {
                return (Backoff) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
            }

            public static Backoff parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (Backoff) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static Builder newBuilder() {
                return (Builder) DEFAULT_INSTANCE.toBuilder();
            }

            public static Builder newBuilder(Backoff prototype) {
                return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
            }

            public static final class Builder extends GeneratedMessageLite.Builder<Backoff, Builder> implements BackoffOrBuilder {
                private Builder() {
                    super(Backoff.DEFAULT_INSTANCE);
                }

                @Override // com.android.server.job.JobStatusDumpProto.JobInfo.BackoffOrBuilder
                public boolean hasPolicy() {
                    return ((Backoff) this.instance).hasPolicy();
                }

                @Override // com.android.server.job.JobStatusDumpProto.JobInfo.BackoffOrBuilder
                public Policy getPolicy() {
                    return ((Backoff) this.instance).getPolicy();
                }

                public Builder setPolicy(Policy value) {
                    copyOnWrite();
                    ((Backoff) this.instance).setPolicy(value);
                    return this;
                }

                public Builder clearPolicy() {
                    copyOnWrite();
                    ((Backoff) this.instance).clearPolicy();
                    return this;
                }

                @Override // com.android.server.job.JobStatusDumpProto.JobInfo.BackoffOrBuilder
                public boolean hasInitialBackoffMs() {
                    return ((Backoff) this.instance).hasInitialBackoffMs();
                }

                @Override // com.android.server.job.JobStatusDumpProto.JobInfo.BackoffOrBuilder
                public long getInitialBackoffMs() {
                    return ((Backoff) this.instance).getInitialBackoffMs();
                }

                public Builder setInitialBackoffMs(long value) {
                    copyOnWrite();
                    ((Backoff) this.instance).setInitialBackoffMs(value);
                    return this;
                }

                public Builder clearInitialBackoffMs() {
                    copyOnWrite();
                    ((Backoff) this.instance).clearInitialBackoffMs();
                    return this;
                }
            }

            /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
            /* access modifiers changed from: protected */
            @Override // com.google.protobuf.GeneratedMessageLite
            public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
                switch (method) {
                    case NEW_MUTABLE_INSTANCE:
                        return new Backoff();
                    case IS_INITIALIZED:
                        return DEFAULT_INSTANCE;
                    case MAKE_IMMUTABLE:
                        return null;
                    case NEW_BUILDER:
                        return new Builder();
                    case VISIT:
                        GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                        Backoff other = (Backoff) arg1;
                        this.policy_ = visitor.visitInt(hasPolicy(), this.policy_, other.hasPolicy(), other.policy_);
                        this.initialBackoffMs_ = visitor.visitLong(hasInitialBackoffMs(), this.initialBackoffMs_, other.hasInitialBackoffMs(), other.initialBackoffMs_);
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
                                    int rawValue = input.readEnum();
                                    if (Policy.forNumber(rawValue) == null) {
                                        super.mergeVarintField(1, rawValue);
                                    } else {
                                        this.bitField0_ = 1 | this.bitField0_;
                                        this.policy_ = rawValue;
                                    }
                                } else if (tag == 16) {
                                    this.bitField0_ |= 2;
                                    this.initialBackoffMs_ = input.readInt64();
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
                            synchronized (Backoff.class) {
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

            public static Backoff getDefaultInstance() {
                return DEFAULT_INSTANCE;
            }

            public static Parser<Backoff> parser() {
                return DEFAULT_INSTANCE.getParserForType();
            }
        }

        @Override // com.android.server.job.JobStatusDumpProto.JobInfoOrBuilder
        public boolean hasService() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // com.android.server.job.JobStatusDumpProto.JobInfoOrBuilder
        public ComponentNameProto getService() {
            ComponentNameProto componentNameProto = this.service_;
            return componentNameProto == null ? ComponentNameProto.getDefaultInstance() : componentNameProto;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setService(ComponentNameProto value) {
            if (value != null) {
                this.service_ = value;
                this.bitField0_ |= 1;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setService(ComponentNameProto.Builder builderForValue) {
            this.service_ = (ComponentNameProto) builderForValue.build();
            this.bitField0_ |= 1;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void mergeService(ComponentNameProto value) {
            ComponentNameProto componentNameProto = this.service_;
            if (componentNameProto == null || componentNameProto == ComponentNameProto.getDefaultInstance()) {
                this.service_ = value;
            } else {
                this.service_ = (ComponentNameProto) ((ComponentNameProto.Builder) ComponentNameProto.newBuilder(this.service_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
            }
            this.bitField0_ |= 1;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearService() {
            this.service_ = null;
            this.bitField0_ &= -2;
        }

        @Override // com.android.server.job.JobStatusDumpProto.JobInfoOrBuilder
        public boolean hasIsPeriodic() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // com.android.server.job.JobStatusDumpProto.JobInfoOrBuilder
        public boolean getIsPeriodic() {
            return this.isPeriodic_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setIsPeriodic(boolean value) {
            this.bitField0_ |= 2;
            this.isPeriodic_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearIsPeriodic() {
            this.bitField0_ &= -3;
            this.isPeriodic_ = false;
        }

        @Override // com.android.server.job.JobStatusDumpProto.JobInfoOrBuilder
        public boolean hasPeriodIntervalMs() {
            return (this.bitField0_ & 4) == 4;
        }

        @Override // com.android.server.job.JobStatusDumpProto.JobInfoOrBuilder
        public long getPeriodIntervalMs() {
            return this.periodIntervalMs_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setPeriodIntervalMs(long value) {
            this.bitField0_ |= 4;
            this.periodIntervalMs_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearPeriodIntervalMs() {
            this.bitField0_ &= -5;
            this.periodIntervalMs_ = 0;
        }

        @Override // com.android.server.job.JobStatusDumpProto.JobInfoOrBuilder
        public boolean hasPeriodFlexMs() {
            return (this.bitField0_ & 8) == 8;
        }

        @Override // com.android.server.job.JobStatusDumpProto.JobInfoOrBuilder
        public long getPeriodFlexMs() {
            return this.periodFlexMs_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setPeriodFlexMs(long value) {
            this.bitField0_ |= 8;
            this.periodFlexMs_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearPeriodFlexMs() {
            this.bitField0_ &= -9;
            this.periodFlexMs_ = 0;
        }

        @Override // com.android.server.job.JobStatusDumpProto.JobInfoOrBuilder
        public boolean hasIsPersisted() {
            return (this.bitField0_ & 16) == 16;
        }

        @Override // com.android.server.job.JobStatusDumpProto.JobInfoOrBuilder
        public boolean getIsPersisted() {
            return this.isPersisted_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setIsPersisted(boolean value) {
            this.bitField0_ |= 16;
            this.isPersisted_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearIsPersisted() {
            this.bitField0_ &= -17;
            this.isPersisted_ = false;
        }

        @Override // com.android.server.job.JobStatusDumpProto.JobInfoOrBuilder
        public boolean hasPriority() {
            return (this.bitField0_ & 32) == 32;
        }

        @Override // com.android.server.job.JobStatusDumpProto.JobInfoOrBuilder
        public int getPriority() {
            return this.priority_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setPriority(int value) {
            this.bitField0_ |= 32;
            this.priority_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearPriority() {
            this.bitField0_ &= -33;
            this.priority_ = 0;
        }

        @Override // com.android.server.job.JobStatusDumpProto.JobInfoOrBuilder
        public boolean hasFlags() {
            return (this.bitField0_ & 64) == 64;
        }

        @Override // com.android.server.job.JobStatusDumpProto.JobInfoOrBuilder
        public int getFlags() {
            return this.flags_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setFlags(int value) {
            this.bitField0_ |= 64;
            this.flags_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearFlags() {
            this.bitField0_ &= -65;
            this.flags_ = 0;
        }

        @Override // com.android.server.job.JobStatusDumpProto.JobInfoOrBuilder
        public boolean hasRequiresCharging() {
            return (this.bitField0_ & 128) == 128;
        }

        @Override // com.android.server.job.JobStatusDumpProto.JobInfoOrBuilder
        public boolean getRequiresCharging() {
            return this.requiresCharging_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setRequiresCharging(boolean value) {
            this.bitField0_ |= 128;
            this.requiresCharging_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearRequiresCharging() {
            this.bitField0_ &= -129;
            this.requiresCharging_ = false;
        }

        @Override // com.android.server.job.JobStatusDumpProto.JobInfoOrBuilder
        public boolean hasRequiresBatteryNotLow() {
            return (this.bitField0_ & 256) == 256;
        }

        @Override // com.android.server.job.JobStatusDumpProto.JobInfoOrBuilder
        public boolean getRequiresBatteryNotLow() {
            return this.requiresBatteryNotLow_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setRequiresBatteryNotLow(boolean value) {
            this.bitField0_ |= 256;
            this.requiresBatteryNotLow_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearRequiresBatteryNotLow() {
            this.bitField0_ &= -257;
            this.requiresBatteryNotLow_ = false;
        }

        @Override // com.android.server.job.JobStatusDumpProto.JobInfoOrBuilder
        public boolean hasRequiresDeviceIdle() {
            return (this.bitField0_ & 512) == 512;
        }

        @Override // com.android.server.job.JobStatusDumpProto.JobInfoOrBuilder
        public boolean getRequiresDeviceIdle() {
            return this.requiresDeviceIdle_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setRequiresDeviceIdle(boolean value) {
            this.bitField0_ |= 512;
            this.requiresDeviceIdle_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearRequiresDeviceIdle() {
            this.bitField0_ &= -513;
            this.requiresDeviceIdle_ = false;
        }

        @Override // com.android.server.job.JobStatusDumpProto.JobInfoOrBuilder
        public List<TriggerContentUri> getTriggerContentUrisList() {
            return this.triggerContentUris_;
        }

        public List<? extends TriggerContentUriOrBuilder> getTriggerContentUrisOrBuilderList() {
            return this.triggerContentUris_;
        }

        @Override // com.android.server.job.JobStatusDumpProto.JobInfoOrBuilder
        public int getTriggerContentUrisCount() {
            return this.triggerContentUris_.size();
        }

        @Override // com.android.server.job.JobStatusDumpProto.JobInfoOrBuilder
        public TriggerContentUri getTriggerContentUris(int index) {
            return this.triggerContentUris_.get(index);
        }

        public TriggerContentUriOrBuilder getTriggerContentUrisOrBuilder(int index) {
            return this.triggerContentUris_.get(index);
        }

        private void ensureTriggerContentUrisIsMutable() {
            if (!this.triggerContentUris_.isModifiable()) {
                this.triggerContentUris_ = GeneratedMessageLite.mutableCopy(this.triggerContentUris_);
            }
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setTriggerContentUris(int index, TriggerContentUri value) {
            if (value != null) {
                ensureTriggerContentUrisIsMutable();
                this.triggerContentUris_.set(index, value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setTriggerContentUris(int index, TriggerContentUri.Builder builderForValue) {
            ensureTriggerContentUrisIsMutable();
            this.triggerContentUris_.set(index, (TriggerContentUri) builderForValue.build());
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addTriggerContentUris(TriggerContentUri value) {
            if (value != null) {
                ensureTriggerContentUrisIsMutable();
                this.triggerContentUris_.add(value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addTriggerContentUris(int index, TriggerContentUri value) {
            if (value != null) {
                ensureTriggerContentUrisIsMutable();
                this.triggerContentUris_.add(index, value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addTriggerContentUris(TriggerContentUri.Builder builderForValue) {
            ensureTriggerContentUrisIsMutable();
            this.triggerContentUris_.add((TriggerContentUri) builderForValue.build());
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addTriggerContentUris(int index, TriggerContentUri.Builder builderForValue) {
            ensureTriggerContentUrisIsMutable();
            this.triggerContentUris_.add(index, (TriggerContentUri) builderForValue.build());
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addAllTriggerContentUris(Iterable<? extends TriggerContentUri> values) {
            ensureTriggerContentUrisIsMutable();
            AbstractMessageLite.addAll(values, this.triggerContentUris_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearTriggerContentUris() {
            this.triggerContentUris_ = emptyProtobufList();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void removeTriggerContentUris(int index) {
            ensureTriggerContentUrisIsMutable();
            this.triggerContentUris_.remove(index);
        }

        @Override // com.android.server.job.JobStatusDumpProto.JobInfoOrBuilder
        public boolean hasTriggerContentUpdateDelayMs() {
            return (this.bitField0_ & 1024) == 1024;
        }

        @Override // com.android.server.job.JobStatusDumpProto.JobInfoOrBuilder
        public long getTriggerContentUpdateDelayMs() {
            return this.triggerContentUpdateDelayMs_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setTriggerContentUpdateDelayMs(long value) {
            this.bitField0_ |= 1024;
            this.triggerContentUpdateDelayMs_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearTriggerContentUpdateDelayMs() {
            this.bitField0_ &= -1025;
            this.triggerContentUpdateDelayMs_ = 0;
        }

        @Override // com.android.server.job.JobStatusDumpProto.JobInfoOrBuilder
        public boolean hasTriggerContentMaxDelayMs() {
            return (this.bitField0_ & 2048) == 2048;
        }

        @Override // com.android.server.job.JobStatusDumpProto.JobInfoOrBuilder
        public long getTriggerContentMaxDelayMs() {
            return this.triggerContentMaxDelayMs_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setTriggerContentMaxDelayMs(long value) {
            this.bitField0_ |= 2048;
            this.triggerContentMaxDelayMs_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearTriggerContentMaxDelayMs() {
            this.bitField0_ &= -2049;
            this.triggerContentMaxDelayMs_ = 0;
        }

        @Override // com.android.server.job.JobStatusDumpProto.JobInfoOrBuilder
        public boolean hasExtras() {
            return (this.bitField0_ & 4096) == 4096;
        }

        @Override // com.android.server.job.JobStatusDumpProto.JobInfoOrBuilder
        public PersistableBundleProto getExtras() {
            PersistableBundleProto persistableBundleProto = this.extras_;
            return persistableBundleProto == null ? PersistableBundleProto.getDefaultInstance() : persistableBundleProto;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setExtras(PersistableBundleProto value) {
            if (value != null) {
                this.extras_ = value;
                this.bitField0_ |= 4096;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setExtras(PersistableBundleProto.Builder builderForValue) {
            this.extras_ = (PersistableBundleProto) builderForValue.build();
            this.bitField0_ |= 4096;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void mergeExtras(PersistableBundleProto value) {
            PersistableBundleProto persistableBundleProto = this.extras_;
            if (persistableBundleProto == null || persistableBundleProto == PersistableBundleProto.getDefaultInstance()) {
                this.extras_ = value;
            } else {
                this.extras_ = (PersistableBundleProto) ((PersistableBundleProto.Builder) PersistableBundleProto.newBuilder(this.extras_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
            }
            this.bitField0_ |= 4096;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearExtras() {
            this.extras_ = null;
            this.bitField0_ &= -4097;
        }

        @Override // com.android.server.job.JobStatusDumpProto.JobInfoOrBuilder
        public boolean hasTransientExtras() {
            return (this.bitField0_ & 8192) == 8192;
        }

        @Override // com.android.server.job.JobStatusDumpProto.JobInfoOrBuilder
        public BundleProto getTransientExtras() {
            BundleProto bundleProto = this.transientExtras_;
            return bundleProto == null ? BundleProto.getDefaultInstance() : bundleProto;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setTransientExtras(BundleProto value) {
            if (value != null) {
                this.transientExtras_ = value;
                this.bitField0_ |= 8192;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setTransientExtras(BundleProto.Builder builderForValue) {
            this.transientExtras_ = (BundleProto) builderForValue.build();
            this.bitField0_ |= 8192;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void mergeTransientExtras(BundleProto value) {
            BundleProto bundleProto = this.transientExtras_;
            if (bundleProto == null || bundleProto == BundleProto.getDefaultInstance()) {
                this.transientExtras_ = value;
            } else {
                this.transientExtras_ = (BundleProto) ((BundleProto.Builder) BundleProto.newBuilder(this.transientExtras_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
            }
            this.bitField0_ |= 8192;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearTransientExtras() {
            this.transientExtras_ = null;
            this.bitField0_ &= -8193;
        }

        @Override // com.android.server.job.JobStatusDumpProto.JobInfoOrBuilder
        public boolean hasClipData() {
            return (this.bitField0_ & 16384) == 16384;
        }

        @Override // com.android.server.job.JobStatusDumpProto.JobInfoOrBuilder
        public ClipDataProto getClipData() {
            ClipDataProto clipDataProto = this.clipData_;
            return clipDataProto == null ? ClipDataProto.getDefaultInstance() : clipDataProto;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setClipData(ClipDataProto value) {
            if (value != null) {
                this.clipData_ = value;
                this.bitField0_ |= 16384;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setClipData(ClipDataProto.Builder builderForValue) {
            this.clipData_ = (ClipDataProto) builderForValue.build();
            this.bitField0_ |= 16384;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void mergeClipData(ClipDataProto value) {
            ClipDataProto clipDataProto = this.clipData_;
            if (clipDataProto == null || clipDataProto == ClipDataProto.getDefaultInstance()) {
                this.clipData_ = value;
            } else {
                this.clipData_ = (ClipDataProto) ((ClipDataProto.Builder) ClipDataProto.newBuilder(this.clipData_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
            }
            this.bitField0_ |= 16384;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearClipData() {
            this.clipData_ = null;
            this.bitField0_ &= -16385;
        }

        @Override // com.android.server.job.JobStatusDumpProto.JobInfoOrBuilder
        public boolean hasGrantedUriPermissions() {
            return (this.bitField0_ & 32768) == 32768;
        }

        @Override // com.android.server.job.JobStatusDumpProto.JobInfoOrBuilder
        public GrantedUriPermissionsDumpProto getGrantedUriPermissions() {
            GrantedUriPermissionsDumpProto grantedUriPermissionsDumpProto = this.grantedUriPermissions_;
            return grantedUriPermissionsDumpProto == null ? GrantedUriPermissionsDumpProto.getDefaultInstance() : grantedUriPermissionsDumpProto;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setGrantedUriPermissions(GrantedUriPermissionsDumpProto value) {
            if (value != null) {
                this.grantedUriPermissions_ = value;
                this.bitField0_ |= 32768;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setGrantedUriPermissions(GrantedUriPermissionsDumpProto.Builder builderForValue) {
            this.grantedUriPermissions_ = (GrantedUriPermissionsDumpProto) builderForValue.build();
            this.bitField0_ |= 32768;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void mergeGrantedUriPermissions(GrantedUriPermissionsDumpProto value) {
            GrantedUriPermissionsDumpProto grantedUriPermissionsDumpProto = this.grantedUriPermissions_;
            if (grantedUriPermissionsDumpProto == null || grantedUriPermissionsDumpProto == GrantedUriPermissionsDumpProto.getDefaultInstance()) {
                this.grantedUriPermissions_ = value;
            } else {
                this.grantedUriPermissions_ = (GrantedUriPermissionsDumpProto) ((GrantedUriPermissionsDumpProto.Builder) GrantedUriPermissionsDumpProto.newBuilder(this.grantedUriPermissions_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
            }
            this.bitField0_ |= 32768;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearGrantedUriPermissions() {
            this.grantedUriPermissions_ = null;
            this.bitField0_ &= -32769;
        }

        @Override // com.android.server.job.JobStatusDumpProto.JobInfoOrBuilder
        public boolean hasRequiredNetwork() {
            return (this.bitField0_ & 65536) == 65536;
        }

        @Override // com.android.server.job.JobStatusDumpProto.JobInfoOrBuilder
        public NetworkRequestProto getRequiredNetwork() {
            NetworkRequestProto networkRequestProto = this.requiredNetwork_;
            return networkRequestProto == null ? NetworkRequestProto.getDefaultInstance() : networkRequestProto;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setRequiredNetwork(NetworkRequestProto value) {
            if (value != null) {
                this.requiredNetwork_ = value;
                this.bitField0_ |= 65536;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setRequiredNetwork(NetworkRequestProto.Builder builderForValue) {
            this.requiredNetwork_ = (NetworkRequestProto) builderForValue.build();
            this.bitField0_ |= 65536;
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
            this.bitField0_ |= 65536;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearRequiredNetwork() {
            this.requiredNetwork_ = null;
            this.bitField0_ &= -65537;
        }

        @Override // com.android.server.job.JobStatusDumpProto.JobInfoOrBuilder
        public boolean hasTotalNetworkBytes() {
            return (this.bitField0_ & 131072) == 131072;
        }

        @Override // com.android.server.job.JobStatusDumpProto.JobInfoOrBuilder
        public long getTotalNetworkBytes() {
            return this.totalNetworkBytes_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setTotalNetworkBytes(long value) {
            this.bitField0_ |= 131072;
            this.totalNetworkBytes_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearTotalNetworkBytes() {
            this.bitField0_ &= -131073;
            this.totalNetworkBytes_ = 0;
        }

        @Override // com.android.server.job.JobStatusDumpProto.JobInfoOrBuilder
        public boolean hasMinLatencyMs() {
            return (this.bitField0_ & 262144) == 262144;
        }

        @Override // com.android.server.job.JobStatusDumpProto.JobInfoOrBuilder
        public long getMinLatencyMs() {
            return this.minLatencyMs_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setMinLatencyMs(long value) {
            this.bitField0_ |= 262144;
            this.minLatencyMs_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearMinLatencyMs() {
            this.bitField0_ &= -262145;
            this.minLatencyMs_ = 0;
        }

        @Override // com.android.server.job.JobStatusDumpProto.JobInfoOrBuilder
        public boolean hasMaxExecutionDelayMs() {
            return (this.bitField0_ & 524288) == 524288;
        }

        @Override // com.android.server.job.JobStatusDumpProto.JobInfoOrBuilder
        public long getMaxExecutionDelayMs() {
            return this.maxExecutionDelayMs_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setMaxExecutionDelayMs(long value) {
            this.bitField0_ |= 524288;
            this.maxExecutionDelayMs_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearMaxExecutionDelayMs() {
            this.bitField0_ &= -524289;
            this.maxExecutionDelayMs_ = 0;
        }

        @Override // com.android.server.job.JobStatusDumpProto.JobInfoOrBuilder
        public boolean hasBackoffPolicy() {
            return (this.bitField0_ & 1048576) == 1048576;
        }

        @Override // com.android.server.job.JobStatusDumpProto.JobInfoOrBuilder
        public Backoff getBackoffPolicy() {
            Backoff backoff = this.backoffPolicy_;
            return backoff == null ? Backoff.getDefaultInstance() : backoff;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setBackoffPolicy(Backoff value) {
            if (value != null) {
                this.backoffPolicy_ = value;
                this.bitField0_ |= 1048576;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setBackoffPolicy(Backoff.Builder builderForValue) {
            this.backoffPolicy_ = (Backoff) builderForValue.build();
            this.bitField0_ |= 1048576;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void mergeBackoffPolicy(Backoff value) {
            Backoff backoff = this.backoffPolicy_;
            if (backoff == null || backoff == Backoff.getDefaultInstance()) {
                this.backoffPolicy_ = value;
            } else {
                this.backoffPolicy_ = (Backoff) ((Backoff.Builder) Backoff.newBuilder(this.backoffPolicy_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
            }
            this.bitField0_ |= 1048576;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearBackoffPolicy() {
            this.backoffPolicy_ = null;
            this.bitField0_ &= -1048577;
        }

        @Override // com.android.server.job.JobStatusDumpProto.JobInfoOrBuilder
        public boolean hasHasEarlyConstraint() {
            return (this.bitField0_ & 2097152) == 2097152;
        }

        @Override // com.android.server.job.JobStatusDumpProto.JobInfoOrBuilder
        public boolean getHasEarlyConstraint() {
            return this.hasEarlyConstraint_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setHasEarlyConstraint(boolean value) {
            this.bitField0_ |= 2097152;
            this.hasEarlyConstraint_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearHasEarlyConstraint() {
            this.bitField0_ &= -2097153;
            this.hasEarlyConstraint_ = false;
        }

        @Override // com.android.server.job.JobStatusDumpProto.JobInfoOrBuilder
        public boolean hasHasLateConstraint() {
            return (this.bitField0_ & 4194304) == 4194304;
        }

        @Override // com.android.server.job.JobStatusDumpProto.JobInfoOrBuilder
        public boolean getHasLateConstraint() {
            return this.hasLateConstraint_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setHasLateConstraint(boolean value) {
            this.bitField0_ |= 4194304;
            this.hasLateConstraint_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearHasLateConstraint() {
            this.bitField0_ &= -4194305;
            this.hasLateConstraint_ = false;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                output.writeMessage(1, getService());
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeBool(2, this.isPeriodic_);
            }
            if ((this.bitField0_ & 4) == 4) {
                output.writeInt64(3, this.periodIntervalMs_);
            }
            if ((this.bitField0_ & 8) == 8) {
                output.writeInt64(4, this.periodFlexMs_);
            }
            if ((this.bitField0_ & 16) == 16) {
                output.writeBool(5, this.isPersisted_);
            }
            if ((this.bitField0_ & 32) == 32) {
                output.writeSInt32(6, this.priority_);
            }
            if ((this.bitField0_ & 64) == 64) {
                output.writeInt32(7, this.flags_);
            }
            if ((this.bitField0_ & 128) == 128) {
                output.writeBool(8, this.requiresCharging_);
            }
            if ((this.bitField0_ & 256) == 256) {
                output.writeBool(9, this.requiresBatteryNotLow_);
            }
            if ((this.bitField0_ & 512) == 512) {
                output.writeBool(10, this.requiresDeviceIdle_);
            }
            for (int i = 0; i < this.triggerContentUris_.size(); i++) {
                output.writeMessage(11, this.triggerContentUris_.get(i));
            }
            if ((this.bitField0_ & 1024) == 1024) {
                output.writeInt64(12, this.triggerContentUpdateDelayMs_);
            }
            if ((this.bitField0_ & 2048) == 2048) {
                output.writeInt64(13, this.triggerContentMaxDelayMs_);
            }
            if ((this.bitField0_ & 4096) == 4096) {
                output.writeMessage(14, getExtras());
            }
            if ((this.bitField0_ & 8192) == 8192) {
                output.writeMessage(15, getTransientExtras());
            }
            if ((this.bitField0_ & 16384) == 16384) {
                output.writeMessage(16, getClipData());
            }
            if ((this.bitField0_ & 32768) == 32768) {
                output.writeMessage(17, getGrantedUriPermissions());
            }
            if ((this.bitField0_ & 65536) == 65536) {
                output.writeMessage(18, getRequiredNetwork());
            }
            if ((this.bitField0_ & 131072) == 131072) {
                output.writeInt64(19, this.totalNetworkBytes_);
            }
            if ((this.bitField0_ & 262144) == 262144) {
                output.writeInt64(20, this.minLatencyMs_);
            }
            if ((this.bitField0_ & 524288) == 524288) {
                output.writeInt64(21, this.maxExecutionDelayMs_);
            }
            if ((this.bitField0_ & 1048576) == 1048576) {
                output.writeMessage(22, getBackoffPolicy());
            }
            if ((this.bitField0_ & 2097152) == 2097152) {
                output.writeBool(23, this.hasEarlyConstraint_);
            }
            if ((this.bitField0_ & 4194304) == 4194304) {
                output.writeBool(24, this.hasLateConstraint_);
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
                size2 = 0 + CodedOutputStream.computeMessageSize(1, getService());
            }
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeBoolSize(2, this.isPeriodic_);
            }
            if ((this.bitField0_ & 4) == 4) {
                size2 += CodedOutputStream.computeInt64Size(3, this.periodIntervalMs_);
            }
            if ((this.bitField0_ & 8) == 8) {
                size2 += CodedOutputStream.computeInt64Size(4, this.periodFlexMs_);
            }
            if ((this.bitField0_ & 16) == 16) {
                size2 += CodedOutputStream.computeBoolSize(5, this.isPersisted_);
            }
            if ((this.bitField0_ & 32) == 32) {
                size2 += CodedOutputStream.computeSInt32Size(6, this.priority_);
            }
            if ((this.bitField0_ & 64) == 64) {
                size2 += CodedOutputStream.computeInt32Size(7, this.flags_);
            }
            if ((this.bitField0_ & 128) == 128) {
                size2 += CodedOutputStream.computeBoolSize(8, this.requiresCharging_);
            }
            if ((this.bitField0_ & 256) == 256) {
                size2 += CodedOutputStream.computeBoolSize(9, this.requiresBatteryNotLow_);
            }
            if ((this.bitField0_ & 512) == 512) {
                size2 += CodedOutputStream.computeBoolSize(10, this.requiresDeviceIdle_);
            }
            for (int i = 0; i < this.triggerContentUris_.size(); i++) {
                size2 += CodedOutputStream.computeMessageSize(11, this.triggerContentUris_.get(i));
            }
            if ((this.bitField0_ & 1024) == 1024) {
                size2 += CodedOutputStream.computeInt64Size(12, this.triggerContentUpdateDelayMs_);
            }
            if ((this.bitField0_ & 2048) == 2048) {
                size2 += CodedOutputStream.computeInt64Size(13, this.triggerContentMaxDelayMs_);
            }
            if ((this.bitField0_ & 4096) == 4096) {
                size2 += CodedOutputStream.computeMessageSize(14, getExtras());
            }
            if ((this.bitField0_ & 8192) == 8192) {
                size2 += CodedOutputStream.computeMessageSize(15, getTransientExtras());
            }
            if ((this.bitField0_ & 16384) == 16384) {
                size2 += CodedOutputStream.computeMessageSize(16, getClipData());
            }
            if ((this.bitField0_ & 32768) == 32768) {
                size2 += CodedOutputStream.computeMessageSize(17, getGrantedUriPermissions());
            }
            if ((this.bitField0_ & 65536) == 65536) {
                size2 += CodedOutputStream.computeMessageSize(18, getRequiredNetwork());
            }
            if ((this.bitField0_ & 131072) == 131072) {
                size2 += CodedOutputStream.computeInt64Size(19, this.totalNetworkBytes_);
            }
            if ((this.bitField0_ & 262144) == 262144) {
                size2 += CodedOutputStream.computeInt64Size(20, this.minLatencyMs_);
            }
            if ((this.bitField0_ & 524288) == 524288) {
                size2 += CodedOutputStream.computeInt64Size(21, this.maxExecutionDelayMs_);
            }
            if ((this.bitField0_ & 1048576) == 1048576) {
                size2 += CodedOutputStream.computeMessageSize(22, getBackoffPolicy());
            }
            if ((this.bitField0_ & 2097152) == 2097152) {
                size2 += CodedOutputStream.computeBoolSize(23, this.hasEarlyConstraint_);
            }
            if ((this.bitField0_ & 4194304) == 4194304) {
                size2 += CodedOutputStream.computeBoolSize(24, this.hasLateConstraint_);
            }
            int size3 = size2 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static JobInfo parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (JobInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static JobInfo parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (JobInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static JobInfo parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (JobInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static JobInfo parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (JobInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static JobInfo parseFrom(InputStream input) throws IOException {
            return (JobInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static JobInfo parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (JobInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static JobInfo parseDelimitedFrom(InputStream input) throws IOException {
            return (JobInfo) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static JobInfo parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (JobInfo) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static JobInfo parseFrom(CodedInputStream input) throws IOException {
            return (JobInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static JobInfo parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (JobInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(JobInfo prototype) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<JobInfo, Builder> implements JobInfoOrBuilder {
            private Builder() {
                super(JobInfo.DEFAULT_INSTANCE);
            }

            @Override // com.android.server.job.JobStatusDumpProto.JobInfoOrBuilder
            public boolean hasService() {
                return ((JobInfo) this.instance).hasService();
            }

            @Override // com.android.server.job.JobStatusDumpProto.JobInfoOrBuilder
            public ComponentNameProto getService() {
                return ((JobInfo) this.instance).getService();
            }

            public Builder setService(ComponentNameProto value) {
                copyOnWrite();
                ((JobInfo) this.instance).setService((JobInfo) value);
                return this;
            }

            public Builder setService(ComponentNameProto.Builder builderForValue) {
                copyOnWrite();
                ((JobInfo) this.instance).setService((JobInfo) builderForValue);
                return this;
            }

            public Builder mergeService(ComponentNameProto value) {
                copyOnWrite();
                ((JobInfo) this.instance).mergeService(value);
                return this;
            }

            public Builder clearService() {
                copyOnWrite();
                ((JobInfo) this.instance).clearService();
                return this;
            }

            @Override // com.android.server.job.JobStatusDumpProto.JobInfoOrBuilder
            public boolean hasIsPeriodic() {
                return ((JobInfo) this.instance).hasIsPeriodic();
            }

            @Override // com.android.server.job.JobStatusDumpProto.JobInfoOrBuilder
            public boolean getIsPeriodic() {
                return ((JobInfo) this.instance).getIsPeriodic();
            }

            public Builder setIsPeriodic(boolean value) {
                copyOnWrite();
                ((JobInfo) this.instance).setIsPeriodic(value);
                return this;
            }

            public Builder clearIsPeriodic() {
                copyOnWrite();
                ((JobInfo) this.instance).clearIsPeriodic();
                return this;
            }

            @Override // com.android.server.job.JobStatusDumpProto.JobInfoOrBuilder
            public boolean hasPeriodIntervalMs() {
                return ((JobInfo) this.instance).hasPeriodIntervalMs();
            }

            @Override // com.android.server.job.JobStatusDumpProto.JobInfoOrBuilder
            public long getPeriodIntervalMs() {
                return ((JobInfo) this.instance).getPeriodIntervalMs();
            }

            public Builder setPeriodIntervalMs(long value) {
                copyOnWrite();
                ((JobInfo) this.instance).setPeriodIntervalMs(value);
                return this;
            }

            public Builder clearPeriodIntervalMs() {
                copyOnWrite();
                ((JobInfo) this.instance).clearPeriodIntervalMs();
                return this;
            }

            @Override // com.android.server.job.JobStatusDumpProto.JobInfoOrBuilder
            public boolean hasPeriodFlexMs() {
                return ((JobInfo) this.instance).hasPeriodFlexMs();
            }

            @Override // com.android.server.job.JobStatusDumpProto.JobInfoOrBuilder
            public long getPeriodFlexMs() {
                return ((JobInfo) this.instance).getPeriodFlexMs();
            }

            public Builder setPeriodFlexMs(long value) {
                copyOnWrite();
                ((JobInfo) this.instance).setPeriodFlexMs(value);
                return this;
            }

            public Builder clearPeriodFlexMs() {
                copyOnWrite();
                ((JobInfo) this.instance).clearPeriodFlexMs();
                return this;
            }

            @Override // com.android.server.job.JobStatusDumpProto.JobInfoOrBuilder
            public boolean hasIsPersisted() {
                return ((JobInfo) this.instance).hasIsPersisted();
            }

            @Override // com.android.server.job.JobStatusDumpProto.JobInfoOrBuilder
            public boolean getIsPersisted() {
                return ((JobInfo) this.instance).getIsPersisted();
            }

            public Builder setIsPersisted(boolean value) {
                copyOnWrite();
                ((JobInfo) this.instance).setIsPersisted(value);
                return this;
            }

            public Builder clearIsPersisted() {
                copyOnWrite();
                ((JobInfo) this.instance).clearIsPersisted();
                return this;
            }

            @Override // com.android.server.job.JobStatusDumpProto.JobInfoOrBuilder
            public boolean hasPriority() {
                return ((JobInfo) this.instance).hasPriority();
            }

            @Override // com.android.server.job.JobStatusDumpProto.JobInfoOrBuilder
            public int getPriority() {
                return ((JobInfo) this.instance).getPriority();
            }

            public Builder setPriority(int value) {
                copyOnWrite();
                ((JobInfo) this.instance).setPriority(value);
                return this;
            }

            public Builder clearPriority() {
                copyOnWrite();
                ((JobInfo) this.instance).clearPriority();
                return this;
            }

            @Override // com.android.server.job.JobStatusDumpProto.JobInfoOrBuilder
            public boolean hasFlags() {
                return ((JobInfo) this.instance).hasFlags();
            }

            @Override // com.android.server.job.JobStatusDumpProto.JobInfoOrBuilder
            public int getFlags() {
                return ((JobInfo) this.instance).getFlags();
            }

            public Builder setFlags(int value) {
                copyOnWrite();
                ((JobInfo) this.instance).setFlags(value);
                return this;
            }

            public Builder clearFlags() {
                copyOnWrite();
                ((JobInfo) this.instance).clearFlags();
                return this;
            }

            @Override // com.android.server.job.JobStatusDumpProto.JobInfoOrBuilder
            public boolean hasRequiresCharging() {
                return ((JobInfo) this.instance).hasRequiresCharging();
            }

            @Override // com.android.server.job.JobStatusDumpProto.JobInfoOrBuilder
            public boolean getRequiresCharging() {
                return ((JobInfo) this.instance).getRequiresCharging();
            }

            public Builder setRequiresCharging(boolean value) {
                copyOnWrite();
                ((JobInfo) this.instance).setRequiresCharging(value);
                return this;
            }

            public Builder clearRequiresCharging() {
                copyOnWrite();
                ((JobInfo) this.instance).clearRequiresCharging();
                return this;
            }

            @Override // com.android.server.job.JobStatusDumpProto.JobInfoOrBuilder
            public boolean hasRequiresBatteryNotLow() {
                return ((JobInfo) this.instance).hasRequiresBatteryNotLow();
            }

            @Override // com.android.server.job.JobStatusDumpProto.JobInfoOrBuilder
            public boolean getRequiresBatteryNotLow() {
                return ((JobInfo) this.instance).getRequiresBatteryNotLow();
            }

            public Builder setRequiresBatteryNotLow(boolean value) {
                copyOnWrite();
                ((JobInfo) this.instance).setRequiresBatteryNotLow(value);
                return this;
            }

            public Builder clearRequiresBatteryNotLow() {
                copyOnWrite();
                ((JobInfo) this.instance).clearRequiresBatteryNotLow();
                return this;
            }

            @Override // com.android.server.job.JobStatusDumpProto.JobInfoOrBuilder
            public boolean hasRequiresDeviceIdle() {
                return ((JobInfo) this.instance).hasRequiresDeviceIdle();
            }

            @Override // com.android.server.job.JobStatusDumpProto.JobInfoOrBuilder
            public boolean getRequiresDeviceIdle() {
                return ((JobInfo) this.instance).getRequiresDeviceIdle();
            }

            public Builder setRequiresDeviceIdle(boolean value) {
                copyOnWrite();
                ((JobInfo) this.instance).setRequiresDeviceIdle(value);
                return this;
            }

            public Builder clearRequiresDeviceIdle() {
                copyOnWrite();
                ((JobInfo) this.instance).clearRequiresDeviceIdle();
                return this;
            }

            @Override // com.android.server.job.JobStatusDumpProto.JobInfoOrBuilder
            public List<TriggerContentUri> getTriggerContentUrisList() {
                return Collections.unmodifiableList(((JobInfo) this.instance).getTriggerContentUrisList());
            }

            @Override // com.android.server.job.JobStatusDumpProto.JobInfoOrBuilder
            public int getTriggerContentUrisCount() {
                return ((JobInfo) this.instance).getTriggerContentUrisCount();
            }

            @Override // com.android.server.job.JobStatusDumpProto.JobInfoOrBuilder
            public TriggerContentUri getTriggerContentUris(int index) {
                return ((JobInfo) this.instance).getTriggerContentUris(index);
            }

            public Builder setTriggerContentUris(int index, TriggerContentUri value) {
                copyOnWrite();
                ((JobInfo) this.instance).setTriggerContentUris((JobInfo) index, (int) value);
                return this;
            }

            public Builder setTriggerContentUris(int index, TriggerContentUri.Builder builderForValue) {
                copyOnWrite();
                ((JobInfo) this.instance).setTriggerContentUris((JobInfo) index, (int) builderForValue);
                return this;
            }

            public Builder addTriggerContentUris(TriggerContentUri value) {
                copyOnWrite();
                ((JobInfo) this.instance).addTriggerContentUris((JobInfo) value);
                return this;
            }

            public Builder addTriggerContentUris(int index, TriggerContentUri value) {
                copyOnWrite();
                ((JobInfo) this.instance).addTriggerContentUris((JobInfo) index, (int) value);
                return this;
            }

            public Builder addTriggerContentUris(TriggerContentUri.Builder builderForValue) {
                copyOnWrite();
                ((JobInfo) this.instance).addTriggerContentUris((JobInfo) builderForValue);
                return this;
            }

            public Builder addTriggerContentUris(int index, TriggerContentUri.Builder builderForValue) {
                copyOnWrite();
                ((JobInfo) this.instance).addTriggerContentUris((JobInfo) index, (int) builderForValue);
                return this;
            }

            public Builder addAllTriggerContentUris(Iterable<? extends TriggerContentUri> values) {
                copyOnWrite();
                ((JobInfo) this.instance).addAllTriggerContentUris(values);
                return this;
            }

            public Builder clearTriggerContentUris() {
                copyOnWrite();
                ((JobInfo) this.instance).clearTriggerContentUris();
                return this;
            }

            public Builder removeTriggerContentUris(int index) {
                copyOnWrite();
                ((JobInfo) this.instance).removeTriggerContentUris(index);
                return this;
            }

            @Override // com.android.server.job.JobStatusDumpProto.JobInfoOrBuilder
            public boolean hasTriggerContentUpdateDelayMs() {
                return ((JobInfo) this.instance).hasTriggerContentUpdateDelayMs();
            }

            @Override // com.android.server.job.JobStatusDumpProto.JobInfoOrBuilder
            public long getTriggerContentUpdateDelayMs() {
                return ((JobInfo) this.instance).getTriggerContentUpdateDelayMs();
            }

            public Builder setTriggerContentUpdateDelayMs(long value) {
                copyOnWrite();
                ((JobInfo) this.instance).setTriggerContentUpdateDelayMs(value);
                return this;
            }

            public Builder clearTriggerContentUpdateDelayMs() {
                copyOnWrite();
                ((JobInfo) this.instance).clearTriggerContentUpdateDelayMs();
                return this;
            }

            @Override // com.android.server.job.JobStatusDumpProto.JobInfoOrBuilder
            public boolean hasTriggerContentMaxDelayMs() {
                return ((JobInfo) this.instance).hasTriggerContentMaxDelayMs();
            }

            @Override // com.android.server.job.JobStatusDumpProto.JobInfoOrBuilder
            public long getTriggerContentMaxDelayMs() {
                return ((JobInfo) this.instance).getTriggerContentMaxDelayMs();
            }

            public Builder setTriggerContentMaxDelayMs(long value) {
                copyOnWrite();
                ((JobInfo) this.instance).setTriggerContentMaxDelayMs(value);
                return this;
            }

            public Builder clearTriggerContentMaxDelayMs() {
                copyOnWrite();
                ((JobInfo) this.instance).clearTriggerContentMaxDelayMs();
                return this;
            }

            @Override // com.android.server.job.JobStatusDumpProto.JobInfoOrBuilder
            public boolean hasExtras() {
                return ((JobInfo) this.instance).hasExtras();
            }

            @Override // com.android.server.job.JobStatusDumpProto.JobInfoOrBuilder
            public PersistableBundleProto getExtras() {
                return ((JobInfo) this.instance).getExtras();
            }

            public Builder setExtras(PersistableBundleProto value) {
                copyOnWrite();
                ((JobInfo) this.instance).setExtras((JobInfo) value);
                return this;
            }

            public Builder setExtras(PersistableBundleProto.Builder builderForValue) {
                copyOnWrite();
                ((JobInfo) this.instance).setExtras((JobInfo) builderForValue);
                return this;
            }

            public Builder mergeExtras(PersistableBundleProto value) {
                copyOnWrite();
                ((JobInfo) this.instance).mergeExtras(value);
                return this;
            }

            public Builder clearExtras() {
                copyOnWrite();
                ((JobInfo) this.instance).clearExtras();
                return this;
            }

            @Override // com.android.server.job.JobStatusDumpProto.JobInfoOrBuilder
            public boolean hasTransientExtras() {
                return ((JobInfo) this.instance).hasTransientExtras();
            }

            @Override // com.android.server.job.JobStatusDumpProto.JobInfoOrBuilder
            public BundleProto getTransientExtras() {
                return ((JobInfo) this.instance).getTransientExtras();
            }

            public Builder setTransientExtras(BundleProto value) {
                copyOnWrite();
                ((JobInfo) this.instance).setTransientExtras((JobInfo) value);
                return this;
            }

            public Builder setTransientExtras(BundleProto.Builder builderForValue) {
                copyOnWrite();
                ((JobInfo) this.instance).setTransientExtras((JobInfo) builderForValue);
                return this;
            }

            public Builder mergeTransientExtras(BundleProto value) {
                copyOnWrite();
                ((JobInfo) this.instance).mergeTransientExtras(value);
                return this;
            }

            public Builder clearTransientExtras() {
                copyOnWrite();
                ((JobInfo) this.instance).clearTransientExtras();
                return this;
            }

            @Override // com.android.server.job.JobStatusDumpProto.JobInfoOrBuilder
            public boolean hasClipData() {
                return ((JobInfo) this.instance).hasClipData();
            }

            @Override // com.android.server.job.JobStatusDumpProto.JobInfoOrBuilder
            public ClipDataProto getClipData() {
                return ((JobInfo) this.instance).getClipData();
            }

            public Builder setClipData(ClipDataProto value) {
                copyOnWrite();
                ((JobInfo) this.instance).setClipData((JobInfo) value);
                return this;
            }

            public Builder setClipData(ClipDataProto.Builder builderForValue) {
                copyOnWrite();
                ((JobInfo) this.instance).setClipData((JobInfo) builderForValue);
                return this;
            }

            public Builder mergeClipData(ClipDataProto value) {
                copyOnWrite();
                ((JobInfo) this.instance).mergeClipData(value);
                return this;
            }

            public Builder clearClipData() {
                copyOnWrite();
                ((JobInfo) this.instance).clearClipData();
                return this;
            }

            @Override // com.android.server.job.JobStatusDumpProto.JobInfoOrBuilder
            public boolean hasGrantedUriPermissions() {
                return ((JobInfo) this.instance).hasGrantedUriPermissions();
            }

            @Override // com.android.server.job.JobStatusDumpProto.JobInfoOrBuilder
            public GrantedUriPermissionsDumpProto getGrantedUriPermissions() {
                return ((JobInfo) this.instance).getGrantedUriPermissions();
            }

            public Builder setGrantedUriPermissions(GrantedUriPermissionsDumpProto value) {
                copyOnWrite();
                ((JobInfo) this.instance).setGrantedUriPermissions((JobInfo) value);
                return this;
            }

            public Builder setGrantedUriPermissions(GrantedUriPermissionsDumpProto.Builder builderForValue) {
                copyOnWrite();
                ((JobInfo) this.instance).setGrantedUriPermissions((JobInfo) builderForValue);
                return this;
            }

            public Builder mergeGrantedUriPermissions(GrantedUriPermissionsDumpProto value) {
                copyOnWrite();
                ((JobInfo) this.instance).mergeGrantedUriPermissions(value);
                return this;
            }

            public Builder clearGrantedUriPermissions() {
                copyOnWrite();
                ((JobInfo) this.instance).clearGrantedUriPermissions();
                return this;
            }

            @Override // com.android.server.job.JobStatusDumpProto.JobInfoOrBuilder
            public boolean hasRequiredNetwork() {
                return ((JobInfo) this.instance).hasRequiredNetwork();
            }

            @Override // com.android.server.job.JobStatusDumpProto.JobInfoOrBuilder
            public NetworkRequestProto getRequiredNetwork() {
                return ((JobInfo) this.instance).getRequiredNetwork();
            }

            public Builder setRequiredNetwork(NetworkRequestProto value) {
                copyOnWrite();
                ((JobInfo) this.instance).setRequiredNetwork((JobInfo) value);
                return this;
            }

            public Builder setRequiredNetwork(NetworkRequestProto.Builder builderForValue) {
                copyOnWrite();
                ((JobInfo) this.instance).setRequiredNetwork((JobInfo) builderForValue);
                return this;
            }

            public Builder mergeRequiredNetwork(NetworkRequestProto value) {
                copyOnWrite();
                ((JobInfo) this.instance).mergeRequiredNetwork(value);
                return this;
            }

            public Builder clearRequiredNetwork() {
                copyOnWrite();
                ((JobInfo) this.instance).clearRequiredNetwork();
                return this;
            }

            @Override // com.android.server.job.JobStatusDumpProto.JobInfoOrBuilder
            public boolean hasTotalNetworkBytes() {
                return ((JobInfo) this.instance).hasTotalNetworkBytes();
            }

            @Override // com.android.server.job.JobStatusDumpProto.JobInfoOrBuilder
            public long getTotalNetworkBytes() {
                return ((JobInfo) this.instance).getTotalNetworkBytes();
            }

            public Builder setTotalNetworkBytes(long value) {
                copyOnWrite();
                ((JobInfo) this.instance).setTotalNetworkBytes(value);
                return this;
            }

            public Builder clearTotalNetworkBytes() {
                copyOnWrite();
                ((JobInfo) this.instance).clearTotalNetworkBytes();
                return this;
            }

            @Override // com.android.server.job.JobStatusDumpProto.JobInfoOrBuilder
            public boolean hasMinLatencyMs() {
                return ((JobInfo) this.instance).hasMinLatencyMs();
            }

            @Override // com.android.server.job.JobStatusDumpProto.JobInfoOrBuilder
            public long getMinLatencyMs() {
                return ((JobInfo) this.instance).getMinLatencyMs();
            }

            public Builder setMinLatencyMs(long value) {
                copyOnWrite();
                ((JobInfo) this.instance).setMinLatencyMs(value);
                return this;
            }

            public Builder clearMinLatencyMs() {
                copyOnWrite();
                ((JobInfo) this.instance).clearMinLatencyMs();
                return this;
            }

            @Override // com.android.server.job.JobStatusDumpProto.JobInfoOrBuilder
            public boolean hasMaxExecutionDelayMs() {
                return ((JobInfo) this.instance).hasMaxExecutionDelayMs();
            }

            @Override // com.android.server.job.JobStatusDumpProto.JobInfoOrBuilder
            public long getMaxExecutionDelayMs() {
                return ((JobInfo) this.instance).getMaxExecutionDelayMs();
            }

            public Builder setMaxExecutionDelayMs(long value) {
                copyOnWrite();
                ((JobInfo) this.instance).setMaxExecutionDelayMs(value);
                return this;
            }

            public Builder clearMaxExecutionDelayMs() {
                copyOnWrite();
                ((JobInfo) this.instance).clearMaxExecutionDelayMs();
                return this;
            }

            @Override // com.android.server.job.JobStatusDumpProto.JobInfoOrBuilder
            public boolean hasBackoffPolicy() {
                return ((JobInfo) this.instance).hasBackoffPolicy();
            }

            @Override // com.android.server.job.JobStatusDumpProto.JobInfoOrBuilder
            public Backoff getBackoffPolicy() {
                return ((JobInfo) this.instance).getBackoffPolicy();
            }

            public Builder setBackoffPolicy(Backoff value) {
                copyOnWrite();
                ((JobInfo) this.instance).setBackoffPolicy((JobInfo) value);
                return this;
            }

            public Builder setBackoffPolicy(Backoff.Builder builderForValue) {
                copyOnWrite();
                ((JobInfo) this.instance).setBackoffPolicy((JobInfo) builderForValue);
                return this;
            }

            public Builder mergeBackoffPolicy(Backoff value) {
                copyOnWrite();
                ((JobInfo) this.instance).mergeBackoffPolicy(value);
                return this;
            }

            public Builder clearBackoffPolicy() {
                copyOnWrite();
                ((JobInfo) this.instance).clearBackoffPolicy();
                return this;
            }

            @Override // com.android.server.job.JobStatusDumpProto.JobInfoOrBuilder
            public boolean hasHasEarlyConstraint() {
                return ((JobInfo) this.instance).hasHasEarlyConstraint();
            }

            @Override // com.android.server.job.JobStatusDumpProto.JobInfoOrBuilder
            public boolean getHasEarlyConstraint() {
                return ((JobInfo) this.instance).getHasEarlyConstraint();
            }

            public Builder setHasEarlyConstraint(boolean value) {
                copyOnWrite();
                ((JobInfo) this.instance).setHasEarlyConstraint(value);
                return this;
            }

            public Builder clearHasEarlyConstraint() {
                copyOnWrite();
                ((JobInfo) this.instance).clearHasEarlyConstraint();
                return this;
            }

            @Override // com.android.server.job.JobStatusDumpProto.JobInfoOrBuilder
            public boolean hasHasLateConstraint() {
                return ((JobInfo) this.instance).hasHasLateConstraint();
            }

            @Override // com.android.server.job.JobStatusDumpProto.JobInfoOrBuilder
            public boolean getHasLateConstraint() {
                return ((JobInfo) this.instance).getHasLateConstraint();
            }

            public Builder setHasLateConstraint(boolean value) {
                copyOnWrite();
                ((JobInfo) this.instance).setHasLateConstraint(value);
                return this;
            }

            public Builder clearHasLateConstraint() {
                copyOnWrite();
                ((JobInfo) this.instance).clearHasLateConstraint();
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new JobInfo();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    this.triggerContentUris_.makeImmutable();
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    JobInfo other = (JobInfo) arg1;
                    this.service_ = (ComponentNameProto) visitor.visitMessage(this.service_, other.service_);
                    this.isPeriodic_ = visitor.visitBoolean(hasIsPeriodic(), this.isPeriodic_, other.hasIsPeriodic(), other.isPeriodic_);
                    this.periodIntervalMs_ = visitor.visitLong(hasPeriodIntervalMs(), this.periodIntervalMs_, other.hasPeriodIntervalMs(), other.periodIntervalMs_);
                    this.periodFlexMs_ = visitor.visitLong(hasPeriodFlexMs(), this.periodFlexMs_, other.hasPeriodFlexMs(), other.periodFlexMs_);
                    this.isPersisted_ = visitor.visitBoolean(hasIsPersisted(), this.isPersisted_, other.hasIsPersisted(), other.isPersisted_);
                    this.priority_ = visitor.visitInt(hasPriority(), this.priority_, other.hasPriority(), other.priority_);
                    this.flags_ = visitor.visitInt(hasFlags(), this.flags_, other.hasFlags(), other.flags_);
                    this.requiresCharging_ = visitor.visitBoolean(hasRequiresCharging(), this.requiresCharging_, other.hasRequiresCharging(), other.requiresCharging_);
                    this.requiresBatteryNotLow_ = visitor.visitBoolean(hasRequiresBatteryNotLow(), this.requiresBatteryNotLow_, other.hasRequiresBatteryNotLow(), other.requiresBatteryNotLow_);
                    this.requiresDeviceIdle_ = visitor.visitBoolean(hasRequiresDeviceIdle(), this.requiresDeviceIdle_, other.hasRequiresDeviceIdle(), other.requiresDeviceIdle_);
                    this.triggerContentUris_ = visitor.visitList(this.triggerContentUris_, other.triggerContentUris_);
                    this.triggerContentUpdateDelayMs_ = visitor.visitLong(hasTriggerContentUpdateDelayMs(), this.triggerContentUpdateDelayMs_, other.hasTriggerContentUpdateDelayMs(), other.triggerContentUpdateDelayMs_);
                    this.triggerContentMaxDelayMs_ = visitor.visitLong(hasTriggerContentMaxDelayMs(), this.triggerContentMaxDelayMs_, other.hasTriggerContentMaxDelayMs(), other.triggerContentMaxDelayMs_);
                    this.extras_ = (PersistableBundleProto) visitor.visitMessage(this.extras_, other.extras_);
                    this.transientExtras_ = (BundleProto) visitor.visitMessage(this.transientExtras_, other.transientExtras_);
                    this.clipData_ = (ClipDataProto) visitor.visitMessage(this.clipData_, other.clipData_);
                    this.grantedUriPermissions_ = (GrantedUriPermissionsDumpProto) visitor.visitMessage(this.grantedUriPermissions_, other.grantedUriPermissions_);
                    this.requiredNetwork_ = (NetworkRequestProto) visitor.visitMessage(this.requiredNetwork_, other.requiredNetwork_);
                    this.totalNetworkBytes_ = visitor.visitLong(hasTotalNetworkBytes(), this.totalNetworkBytes_, other.hasTotalNetworkBytes(), other.totalNetworkBytes_);
                    this.minLatencyMs_ = visitor.visitLong(hasMinLatencyMs(), this.minLatencyMs_, other.hasMinLatencyMs(), other.minLatencyMs_);
                    this.maxExecutionDelayMs_ = visitor.visitLong(hasMaxExecutionDelayMs(), this.maxExecutionDelayMs_, other.hasMaxExecutionDelayMs(), other.maxExecutionDelayMs_);
                    this.backoffPolicy_ = (Backoff) visitor.visitMessage(this.backoffPolicy_, other.backoffPolicy_);
                    this.hasEarlyConstraint_ = visitor.visitBoolean(hasHasEarlyConstraint(), this.hasEarlyConstraint_, other.hasHasEarlyConstraint(), other.hasEarlyConstraint_);
                    this.hasLateConstraint_ = visitor.visitBoolean(hasHasLateConstraint(), this.hasLateConstraint_, other.hasHasLateConstraint(), other.hasLateConstraint_);
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
                                    ComponentNameProto.Builder subBuilder = null;
                                    if ((this.bitField0_ & 1) == 1) {
                                        subBuilder = (ComponentNameProto.Builder) this.service_.toBuilder();
                                    }
                                    this.service_ = (ComponentNameProto) input.readMessage(ComponentNameProto.parser(), extensionRegistry);
                                    if (subBuilder != null) {
                                        subBuilder.mergeFrom((GeneratedMessageLite) this.service_);
                                        this.service_ = (ComponentNameProto) subBuilder.buildPartial();
                                    }
                                    this.bitField0_ |= 1;
                                    break;
                                case 16:
                                    this.bitField0_ |= 2;
                                    this.isPeriodic_ = input.readBool();
                                    break;
                                case 24:
                                    this.bitField0_ |= 4;
                                    this.periodIntervalMs_ = input.readInt64();
                                    break;
                                case 32:
                                    this.bitField0_ |= 8;
                                    this.periodFlexMs_ = input.readInt64();
                                    break;
                                case 40:
                                    this.bitField0_ |= 16;
                                    this.isPersisted_ = input.readBool();
                                    break;
                                case 48:
                                    this.bitField0_ |= 32;
                                    this.priority_ = input.readSInt32();
                                    break;
                                case 56:
                                    this.bitField0_ |= 64;
                                    this.flags_ = input.readInt32();
                                    break;
                                case 64:
                                    this.bitField0_ |= 128;
                                    this.requiresCharging_ = input.readBool();
                                    break;
                                case 72:
                                    this.bitField0_ |= 256;
                                    this.requiresBatteryNotLow_ = input.readBool();
                                    break;
                                case 80:
                                    this.bitField0_ |= 512;
                                    this.requiresDeviceIdle_ = input.readBool();
                                    break;
                                case 90:
                                    if (!this.triggerContentUris_.isModifiable()) {
                                        this.triggerContentUris_ = GeneratedMessageLite.mutableCopy(this.triggerContentUris_);
                                    }
                                    this.triggerContentUris_.add((TriggerContentUri) input.readMessage(TriggerContentUri.parser(), extensionRegistry));
                                    break;
                                case 96:
                                    this.bitField0_ |= 1024;
                                    this.triggerContentUpdateDelayMs_ = input.readInt64();
                                    break;
                                case 104:
                                    this.bitField0_ |= 2048;
                                    this.triggerContentMaxDelayMs_ = input.readInt64();
                                    break;
                                case 114:
                                    PersistableBundleProto.Builder subBuilder2 = null;
                                    if ((this.bitField0_ & 4096) == 4096) {
                                        subBuilder2 = (PersistableBundleProto.Builder) this.extras_.toBuilder();
                                    }
                                    this.extras_ = (PersistableBundleProto) input.readMessage(PersistableBundleProto.parser(), extensionRegistry);
                                    if (subBuilder2 != null) {
                                        subBuilder2.mergeFrom((GeneratedMessageLite) this.extras_);
                                        this.extras_ = (PersistableBundleProto) subBuilder2.buildPartial();
                                    }
                                    this.bitField0_ |= 4096;
                                    break;
                                case 122:
                                    BundleProto.Builder subBuilder3 = null;
                                    if ((this.bitField0_ & 8192) == 8192) {
                                        subBuilder3 = (BundleProto.Builder) this.transientExtras_.toBuilder();
                                    }
                                    this.transientExtras_ = (BundleProto) input.readMessage(BundleProto.parser(), extensionRegistry);
                                    if (subBuilder3 != null) {
                                        subBuilder3.mergeFrom((GeneratedMessageLite) this.transientExtras_);
                                        this.transientExtras_ = (BundleProto) subBuilder3.buildPartial();
                                    }
                                    this.bitField0_ |= 8192;
                                    break;
                                case 130:
                                    ClipDataProto.Builder subBuilder4 = null;
                                    if ((this.bitField0_ & 16384) == 16384) {
                                        subBuilder4 = (ClipDataProto.Builder) this.clipData_.toBuilder();
                                    }
                                    this.clipData_ = (ClipDataProto) input.readMessage(ClipDataProto.parser(), extensionRegistry);
                                    if (subBuilder4 != null) {
                                        subBuilder4.mergeFrom((GeneratedMessageLite) this.clipData_);
                                        this.clipData_ = (ClipDataProto) subBuilder4.buildPartial();
                                    }
                                    this.bitField0_ |= 16384;
                                    break;
                                case 138:
                                    GrantedUriPermissionsDumpProto.Builder subBuilder5 = null;
                                    if ((this.bitField0_ & 32768) == 32768) {
                                        subBuilder5 = (GrantedUriPermissionsDumpProto.Builder) this.grantedUriPermissions_.toBuilder();
                                    }
                                    this.grantedUriPermissions_ = (GrantedUriPermissionsDumpProto) input.readMessage(GrantedUriPermissionsDumpProto.parser(), extensionRegistry);
                                    if (subBuilder5 != null) {
                                        subBuilder5.mergeFrom((GeneratedMessageLite) this.grantedUriPermissions_);
                                        this.grantedUriPermissions_ = (GrantedUriPermissionsDumpProto) subBuilder5.buildPartial();
                                    }
                                    this.bitField0_ |= 32768;
                                    break;
                                case 146:
                                    NetworkRequestProto.Builder subBuilder6 = null;
                                    if ((this.bitField0_ & 65536) == 65536) {
                                        subBuilder6 = (NetworkRequestProto.Builder) this.requiredNetwork_.toBuilder();
                                    }
                                    this.requiredNetwork_ = (NetworkRequestProto) input.readMessage(NetworkRequestProto.parser(), extensionRegistry);
                                    if (subBuilder6 != null) {
                                        subBuilder6.mergeFrom((GeneratedMessageLite) this.requiredNetwork_);
                                        this.requiredNetwork_ = (NetworkRequestProto) subBuilder6.buildPartial();
                                    }
                                    this.bitField0_ |= 65536;
                                    break;
                                case 152:
                                    this.bitField0_ |= 131072;
                                    this.totalNetworkBytes_ = input.readInt64();
                                    break;
                                case 160:
                                    this.bitField0_ |= 262144;
                                    this.minLatencyMs_ = input.readInt64();
                                    break;
                                case 168:
                                    this.bitField0_ |= 524288;
                                    this.maxExecutionDelayMs_ = input.readInt64();
                                    break;
                                case 178:
                                    Backoff.Builder subBuilder7 = null;
                                    if ((this.bitField0_ & 1048576) == 1048576) {
                                        subBuilder7 = (Backoff.Builder) this.backoffPolicy_.toBuilder();
                                    }
                                    this.backoffPolicy_ = (Backoff) input.readMessage(Backoff.parser(), extensionRegistry);
                                    if (subBuilder7 != null) {
                                        subBuilder7.mergeFrom((GeneratedMessageLite) this.backoffPolicy_);
                                        this.backoffPolicy_ = (Backoff) subBuilder7.buildPartial();
                                    }
                                    this.bitField0_ |= 1048576;
                                    break;
                                case 184:
                                    this.bitField0_ |= 2097152;
                                    this.hasEarlyConstraint_ = input.readBool();
                                    break;
                                case AtomsProto.Atom.MEDIAMETRICS_AUDIORECORD_REPORTED_FIELD_NUMBER:
                                    this.bitField0_ |= 4194304;
                                    this.hasLateConstraint_ = input.readBool();
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
                        synchronized (JobInfo.class) {
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

        public static JobInfo getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<JobInfo> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class ImplicitConstraints extends GeneratedMessageLite<ImplicitConstraints, Builder> implements ImplicitConstraintsOrBuilder {
        private static final ImplicitConstraints DEFAULT_INSTANCE = new ImplicitConstraints();
        public static final int IS_NOT_DOZING_FIELD_NUMBER = 1;
        public static final int IS_NOT_RESTRICTED_IN_BG_FIELD_NUMBER = 2;
        private static volatile Parser<ImplicitConstraints> PARSER;
        private int bitField0_;
        private boolean isNotDozing_ = false;
        private boolean isNotRestrictedInBg_ = false;

        private ImplicitConstraints() {
        }

        @Override // com.android.server.job.JobStatusDumpProto.ImplicitConstraintsOrBuilder
        public boolean hasIsNotDozing() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // com.android.server.job.JobStatusDumpProto.ImplicitConstraintsOrBuilder
        public boolean getIsNotDozing() {
            return this.isNotDozing_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setIsNotDozing(boolean value) {
            this.bitField0_ |= 1;
            this.isNotDozing_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearIsNotDozing() {
            this.bitField0_ &= -2;
            this.isNotDozing_ = false;
        }

        @Override // com.android.server.job.JobStatusDumpProto.ImplicitConstraintsOrBuilder
        public boolean hasIsNotRestrictedInBg() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // com.android.server.job.JobStatusDumpProto.ImplicitConstraintsOrBuilder
        public boolean getIsNotRestrictedInBg() {
            return this.isNotRestrictedInBg_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setIsNotRestrictedInBg(boolean value) {
            this.bitField0_ |= 2;
            this.isNotRestrictedInBg_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearIsNotRestrictedInBg() {
            this.bitField0_ &= -3;
            this.isNotRestrictedInBg_ = false;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                output.writeBool(1, this.isNotDozing_);
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeBool(2, this.isNotRestrictedInBg_);
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
                size2 = 0 + CodedOutputStream.computeBoolSize(1, this.isNotDozing_);
            }
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeBoolSize(2, this.isNotRestrictedInBg_);
            }
            int size3 = size2 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static ImplicitConstraints parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (ImplicitConstraints) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static ImplicitConstraints parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (ImplicitConstraints) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static ImplicitConstraints parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (ImplicitConstraints) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static ImplicitConstraints parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (ImplicitConstraints) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static ImplicitConstraints parseFrom(InputStream input) throws IOException {
            return (ImplicitConstraints) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static ImplicitConstraints parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (ImplicitConstraints) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static ImplicitConstraints parseDelimitedFrom(InputStream input) throws IOException {
            return (ImplicitConstraints) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static ImplicitConstraints parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (ImplicitConstraints) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static ImplicitConstraints parseFrom(CodedInputStream input) throws IOException {
            return (ImplicitConstraints) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static ImplicitConstraints parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (ImplicitConstraints) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(ImplicitConstraints prototype) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<ImplicitConstraints, Builder> implements ImplicitConstraintsOrBuilder {
            private Builder() {
                super(ImplicitConstraints.DEFAULT_INSTANCE);
            }

            @Override // com.android.server.job.JobStatusDumpProto.ImplicitConstraintsOrBuilder
            public boolean hasIsNotDozing() {
                return ((ImplicitConstraints) this.instance).hasIsNotDozing();
            }

            @Override // com.android.server.job.JobStatusDumpProto.ImplicitConstraintsOrBuilder
            public boolean getIsNotDozing() {
                return ((ImplicitConstraints) this.instance).getIsNotDozing();
            }

            public Builder setIsNotDozing(boolean value) {
                copyOnWrite();
                ((ImplicitConstraints) this.instance).setIsNotDozing(value);
                return this;
            }

            public Builder clearIsNotDozing() {
                copyOnWrite();
                ((ImplicitConstraints) this.instance).clearIsNotDozing();
                return this;
            }

            @Override // com.android.server.job.JobStatusDumpProto.ImplicitConstraintsOrBuilder
            public boolean hasIsNotRestrictedInBg() {
                return ((ImplicitConstraints) this.instance).hasIsNotRestrictedInBg();
            }

            @Override // com.android.server.job.JobStatusDumpProto.ImplicitConstraintsOrBuilder
            public boolean getIsNotRestrictedInBg() {
                return ((ImplicitConstraints) this.instance).getIsNotRestrictedInBg();
            }

            public Builder setIsNotRestrictedInBg(boolean value) {
                copyOnWrite();
                ((ImplicitConstraints) this.instance).setIsNotRestrictedInBg(value);
                return this;
            }

            public Builder clearIsNotRestrictedInBg() {
                copyOnWrite();
                ((ImplicitConstraints) this.instance).clearIsNotRestrictedInBg();
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new ImplicitConstraints();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    ImplicitConstraints other = (ImplicitConstraints) arg1;
                    this.isNotDozing_ = visitor.visitBoolean(hasIsNotDozing(), this.isNotDozing_, other.hasIsNotDozing(), other.isNotDozing_);
                    this.isNotRestrictedInBg_ = visitor.visitBoolean(hasIsNotRestrictedInBg(), this.isNotRestrictedInBg_, other.hasIsNotRestrictedInBg(), other.isNotRestrictedInBg_);
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
                                this.isNotDozing_ = input.readBool();
                            } else if (tag == 16) {
                                this.bitField0_ |= 2;
                                this.isNotRestrictedInBg_ = input.readBool();
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
                        synchronized (ImplicitConstraints.class) {
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

        public static ImplicitConstraints getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<ImplicitConstraints> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class JobWorkItem extends GeneratedMessageLite<JobWorkItem, Builder> implements JobWorkItemOrBuilder {
        private static final JobWorkItem DEFAULT_INSTANCE = new JobWorkItem();
        public static final int DELIVERY_COUNT_FIELD_NUMBER = 2;
        public static final int INTENT_FIELD_NUMBER = 3;
        private static volatile Parser<JobWorkItem> PARSER = null;
        public static final int URI_GRANTS_FIELD_NUMBER = 4;
        public static final int WORK_ID_FIELD_NUMBER = 1;
        private int bitField0_;
        private int deliveryCount_ = 0;
        private IntentProto intent_;
        private GrantedUriPermissionsDumpProto uriGrants_;
        private int workId_ = 0;

        private JobWorkItem() {
        }

        @Override // com.android.server.job.JobStatusDumpProto.JobWorkItemOrBuilder
        public boolean hasWorkId() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // com.android.server.job.JobStatusDumpProto.JobWorkItemOrBuilder
        public int getWorkId() {
            return this.workId_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setWorkId(int value) {
            this.bitField0_ |= 1;
            this.workId_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearWorkId() {
            this.bitField0_ &= -2;
            this.workId_ = 0;
        }

        @Override // com.android.server.job.JobStatusDumpProto.JobWorkItemOrBuilder
        public boolean hasDeliveryCount() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // com.android.server.job.JobStatusDumpProto.JobWorkItemOrBuilder
        public int getDeliveryCount() {
            return this.deliveryCount_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setDeliveryCount(int value) {
            this.bitField0_ |= 2;
            this.deliveryCount_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearDeliveryCount() {
            this.bitField0_ &= -3;
            this.deliveryCount_ = 0;
        }

        @Override // com.android.server.job.JobStatusDumpProto.JobWorkItemOrBuilder
        public boolean hasIntent() {
            return (this.bitField0_ & 4) == 4;
        }

        @Override // com.android.server.job.JobStatusDumpProto.JobWorkItemOrBuilder
        public IntentProto getIntent() {
            IntentProto intentProto = this.intent_;
            return intentProto == null ? IntentProto.getDefaultInstance() : intentProto;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setIntent(IntentProto value) {
            if (value != null) {
                this.intent_ = value;
                this.bitField0_ |= 4;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setIntent(IntentProto.Builder builderForValue) {
            this.intent_ = (IntentProto) builderForValue.build();
            this.bitField0_ |= 4;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void mergeIntent(IntentProto value) {
            IntentProto intentProto = this.intent_;
            if (intentProto == null || intentProto == IntentProto.getDefaultInstance()) {
                this.intent_ = value;
            } else {
                this.intent_ = (IntentProto) ((IntentProto.Builder) IntentProto.newBuilder(this.intent_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
            }
            this.bitField0_ |= 4;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearIntent() {
            this.intent_ = null;
            this.bitField0_ &= -5;
        }

        @Override // com.android.server.job.JobStatusDumpProto.JobWorkItemOrBuilder
        public boolean hasUriGrants() {
            return (this.bitField0_ & 8) == 8;
        }

        @Override // com.android.server.job.JobStatusDumpProto.JobWorkItemOrBuilder
        public GrantedUriPermissionsDumpProto getUriGrants() {
            GrantedUriPermissionsDumpProto grantedUriPermissionsDumpProto = this.uriGrants_;
            return grantedUriPermissionsDumpProto == null ? GrantedUriPermissionsDumpProto.getDefaultInstance() : grantedUriPermissionsDumpProto;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setUriGrants(GrantedUriPermissionsDumpProto value) {
            if (value != null) {
                this.uriGrants_ = value;
                this.bitField0_ |= 8;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setUriGrants(GrantedUriPermissionsDumpProto.Builder builderForValue) {
            this.uriGrants_ = (GrantedUriPermissionsDumpProto) builderForValue.build();
            this.bitField0_ |= 8;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void mergeUriGrants(GrantedUriPermissionsDumpProto value) {
            GrantedUriPermissionsDumpProto grantedUriPermissionsDumpProto = this.uriGrants_;
            if (grantedUriPermissionsDumpProto == null || grantedUriPermissionsDumpProto == GrantedUriPermissionsDumpProto.getDefaultInstance()) {
                this.uriGrants_ = value;
            } else {
                this.uriGrants_ = (GrantedUriPermissionsDumpProto) ((GrantedUriPermissionsDumpProto.Builder) GrantedUriPermissionsDumpProto.newBuilder(this.uriGrants_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
            }
            this.bitField0_ |= 8;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearUriGrants() {
            this.uriGrants_ = null;
            this.bitField0_ &= -9;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                output.writeInt32(1, this.workId_);
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeInt32(2, this.deliveryCount_);
            }
            if ((this.bitField0_ & 4) == 4) {
                output.writeMessage(3, getIntent());
            }
            if ((this.bitField0_ & 8) == 8) {
                output.writeMessage(4, getUriGrants());
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
                size2 = 0 + CodedOutputStream.computeInt32Size(1, this.workId_);
            }
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeInt32Size(2, this.deliveryCount_);
            }
            if ((this.bitField0_ & 4) == 4) {
                size2 += CodedOutputStream.computeMessageSize(3, getIntent());
            }
            if ((this.bitField0_ & 8) == 8) {
                size2 += CodedOutputStream.computeMessageSize(4, getUriGrants());
            }
            int size3 = size2 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static JobWorkItem parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (JobWorkItem) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static JobWorkItem parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (JobWorkItem) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static JobWorkItem parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (JobWorkItem) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static JobWorkItem parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (JobWorkItem) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static JobWorkItem parseFrom(InputStream input) throws IOException {
            return (JobWorkItem) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static JobWorkItem parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (JobWorkItem) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static JobWorkItem parseDelimitedFrom(InputStream input) throws IOException {
            return (JobWorkItem) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static JobWorkItem parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (JobWorkItem) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static JobWorkItem parseFrom(CodedInputStream input) throws IOException {
            return (JobWorkItem) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static JobWorkItem parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (JobWorkItem) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(JobWorkItem prototype) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<JobWorkItem, Builder> implements JobWorkItemOrBuilder {
            private Builder() {
                super(JobWorkItem.DEFAULT_INSTANCE);
            }

            @Override // com.android.server.job.JobStatusDumpProto.JobWorkItemOrBuilder
            public boolean hasWorkId() {
                return ((JobWorkItem) this.instance).hasWorkId();
            }

            @Override // com.android.server.job.JobStatusDumpProto.JobWorkItemOrBuilder
            public int getWorkId() {
                return ((JobWorkItem) this.instance).getWorkId();
            }

            public Builder setWorkId(int value) {
                copyOnWrite();
                ((JobWorkItem) this.instance).setWorkId(value);
                return this;
            }

            public Builder clearWorkId() {
                copyOnWrite();
                ((JobWorkItem) this.instance).clearWorkId();
                return this;
            }

            @Override // com.android.server.job.JobStatusDumpProto.JobWorkItemOrBuilder
            public boolean hasDeliveryCount() {
                return ((JobWorkItem) this.instance).hasDeliveryCount();
            }

            @Override // com.android.server.job.JobStatusDumpProto.JobWorkItemOrBuilder
            public int getDeliveryCount() {
                return ((JobWorkItem) this.instance).getDeliveryCount();
            }

            public Builder setDeliveryCount(int value) {
                copyOnWrite();
                ((JobWorkItem) this.instance).setDeliveryCount(value);
                return this;
            }

            public Builder clearDeliveryCount() {
                copyOnWrite();
                ((JobWorkItem) this.instance).clearDeliveryCount();
                return this;
            }

            @Override // com.android.server.job.JobStatusDumpProto.JobWorkItemOrBuilder
            public boolean hasIntent() {
                return ((JobWorkItem) this.instance).hasIntent();
            }

            @Override // com.android.server.job.JobStatusDumpProto.JobWorkItemOrBuilder
            public IntentProto getIntent() {
                return ((JobWorkItem) this.instance).getIntent();
            }

            public Builder setIntent(IntentProto value) {
                copyOnWrite();
                ((JobWorkItem) this.instance).setIntent((JobWorkItem) value);
                return this;
            }

            public Builder setIntent(IntentProto.Builder builderForValue) {
                copyOnWrite();
                ((JobWorkItem) this.instance).setIntent((JobWorkItem) builderForValue);
                return this;
            }

            public Builder mergeIntent(IntentProto value) {
                copyOnWrite();
                ((JobWorkItem) this.instance).mergeIntent(value);
                return this;
            }

            public Builder clearIntent() {
                copyOnWrite();
                ((JobWorkItem) this.instance).clearIntent();
                return this;
            }

            @Override // com.android.server.job.JobStatusDumpProto.JobWorkItemOrBuilder
            public boolean hasUriGrants() {
                return ((JobWorkItem) this.instance).hasUriGrants();
            }

            @Override // com.android.server.job.JobStatusDumpProto.JobWorkItemOrBuilder
            public GrantedUriPermissionsDumpProto getUriGrants() {
                return ((JobWorkItem) this.instance).getUriGrants();
            }

            public Builder setUriGrants(GrantedUriPermissionsDumpProto value) {
                copyOnWrite();
                ((JobWorkItem) this.instance).setUriGrants((JobWorkItem) value);
                return this;
            }

            public Builder setUriGrants(GrantedUriPermissionsDumpProto.Builder builderForValue) {
                copyOnWrite();
                ((JobWorkItem) this.instance).setUriGrants((JobWorkItem) builderForValue);
                return this;
            }

            public Builder mergeUriGrants(GrantedUriPermissionsDumpProto value) {
                copyOnWrite();
                ((JobWorkItem) this.instance).mergeUriGrants(value);
                return this;
            }

            public Builder clearUriGrants() {
                copyOnWrite();
                ((JobWorkItem) this.instance).clearUriGrants();
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new JobWorkItem();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    JobWorkItem other = (JobWorkItem) arg1;
                    this.workId_ = visitor.visitInt(hasWorkId(), this.workId_, other.hasWorkId(), other.workId_);
                    this.deliveryCount_ = visitor.visitInt(hasDeliveryCount(), this.deliveryCount_, other.hasDeliveryCount(), other.deliveryCount_);
                    this.intent_ = (IntentProto) visitor.visitMessage(this.intent_, other.intent_);
                    this.uriGrants_ = (GrantedUriPermissionsDumpProto) visitor.visitMessage(this.uriGrants_, other.uriGrants_);
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
                                this.workId_ = input.readInt32();
                            } else if (tag == 16) {
                                this.bitField0_ |= 2;
                                this.deliveryCount_ = input.readInt32();
                            } else if (tag == 26) {
                                IntentProto.Builder subBuilder = null;
                                if ((this.bitField0_ & 4) == 4) {
                                    subBuilder = (IntentProto.Builder) this.intent_.toBuilder();
                                }
                                this.intent_ = (IntentProto) input.readMessage(IntentProto.parser(), extensionRegistry);
                                if (subBuilder != null) {
                                    subBuilder.mergeFrom((GeneratedMessageLite) this.intent_);
                                    this.intent_ = (IntentProto) subBuilder.buildPartial();
                                }
                                this.bitField0_ |= 4;
                            } else if (tag == 34) {
                                GrantedUriPermissionsDumpProto.Builder subBuilder2 = null;
                                if ((this.bitField0_ & 8) == 8) {
                                    subBuilder2 = (GrantedUriPermissionsDumpProto.Builder) this.uriGrants_.toBuilder();
                                }
                                this.uriGrants_ = (GrantedUriPermissionsDumpProto) input.readMessage(GrantedUriPermissionsDumpProto.parser(), extensionRegistry);
                                if (subBuilder2 != null) {
                                    subBuilder2.mergeFrom((GeneratedMessageLite) this.uriGrants_);
                                    this.uriGrants_ = (GrantedUriPermissionsDumpProto) subBuilder2.buildPartial();
                                }
                                this.bitField0_ = 8 | this.bitField0_;
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
                        synchronized (JobWorkItem.class) {
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

        public static JobWorkItem getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<JobWorkItem> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    @Override // com.android.server.job.JobStatusDumpProtoOrBuilder
    public boolean hasCallingUid() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // com.android.server.job.JobStatusDumpProtoOrBuilder
    public int getCallingUid() {
        return this.callingUid_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setCallingUid(int value) {
        this.bitField0_ |= 1;
        this.callingUid_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearCallingUid() {
        this.bitField0_ &= -2;
        this.callingUid_ = 0;
    }

    @Override // com.android.server.job.JobStatusDumpProtoOrBuilder
    public boolean hasTag() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // com.android.server.job.JobStatusDumpProtoOrBuilder
    public String getTag() {
        return this.tag_;
    }

    @Override // com.android.server.job.JobStatusDumpProtoOrBuilder
    public ByteString getTagBytes() {
        return ByteString.copyFromUtf8(this.tag_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setTag(String value) {
        if (value != null) {
            this.bitField0_ |= 2;
            this.tag_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearTag() {
        this.bitField0_ &= -3;
        this.tag_ = getDefaultInstance().getTag();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setTagBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 2;
            this.tag_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // com.android.server.job.JobStatusDumpProtoOrBuilder
    public boolean hasSourceUid() {
        return (this.bitField0_ & 4) == 4;
    }

    @Override // com.android.server.job.JobStatusDumpProtoOrBuilder
    public int getSourceUid() {
        return this.sourceUid_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setSourceUid(int value) {
        this.bitField0_ |= 4;
        this.sourceUid_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearSourceUid() {
        this.bitField0_ &= -5;
        this.sourceUid_ = 0;
    }

    @Override // com.android.server.job.JobStatusDumpProtoOrBuilder
    public boolean hasSourceUserId() {
        return (this.bitField0_ & 8) == 8;
    }

    @Override // com.android.server.job.JobStatusDumpProtoOrBuilder
    public int getSourceUserId() {
        return this.sourceUserId_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setSourceUserId(int value) {
        this.bitField0_ |= 8;
        this.sourceUserId_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearSourceUserId() {
        this.bitField0_ &= -9;
        this.sourceUserId_ = 0;
    }

    @Override // com.android.server.job.JobStatusDumpProtoOrBuilder
    public boolean hasSourcePackageName() {
        return (this.bitField0_ & 16) == 16;
    }

    @Override // com.android.server.job.JobStatusDumpProtoOrBuilder
    public String getSourcePackageName() {
        return this.sourcePackageName_;
    }

    @Override // com.android.server.job.JobStatusDumpProtoOrBuilder
    public ByteString getSourcePackageNameBytes() {
        return ByteString.copyFromUtf8(this.sourcePackageName_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setSourcePackageName(String value) {
        if (value != null) {
            this.bitField0_ |= 16;
            this.sourcePackageName_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearSourcePackageName() {
        this.bitField0_ &= -17;
        this.sourcePackageName_ = getDefaultInstance().getSourcePackageName();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setSourcePackageNameBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 16;
            this.sourcePackageName_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // com.android.server.job.JobStatusDumpProtoOrBuilder
    public boolean hasJobInfo() {
        return (this.bitField0_ & 32) == 32;
    }

    @Override // com.android.server.job.JobStatusDumpProtoOrBuilder
    public JobInfo getJobInfo() {
        JobInfo jobInfo = this.jobInfo_;
        return jobInfo == null ? JobInfo.getDefaultInstance() : jobInfo;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setJobInfo(JobInfo value) {
        if (value != null) {
            this.jobInfo_ = value;
            this.bitField0_ |= 32;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setJobInfo(JobInfo.Builder builderForValue) {
        this.jobInfo_ = (JobInfo) builderForValue.build();
        this.bitField0_ |= 32;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeJobInfo(JobInfo value) {
        JobInfo jobInfo = this.jobInfo_;
        if (jobInfo == null || jobInfo == JobInfo.getDefaultInstance()) {
            this.jobInfo_ = value;
        } else {
            this.jobInfo_ = (JobInfo) ((JobInfo.Builder) JobInfo.newBuilder(this.jobInfo_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 32;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearJobInfo() {
        this.jobInfo_ = null;
        this.bitField0_ &= -33;
    }

    static {
        DEFAULT_INSTANCE.makeImmutable();
    }

    @Override // com.android.server.job.JobStatusDumpProtoOrBuilder
    public List<ConstraintEnum> getRequiredConstraintsList() {
        return new Internal.ListAdapter(this.requiredConstraints_, requiredConstraints_converter_);
    }

    @Override // com.android.server.job.JobStatusDumpProtoOrBuilder
    public int getRequiredConstraintsCount() {
        return this.requiredConstraints_.size();
    }

    @Override // com.android.server.job.JobStatusDumpProtoOrBuilder
    public ConstraintEnum getRequiredConstraints(int index) {
        return requiredConstraints_converter_.convert(Integer.valueOf(this.requiredConstraints_.getInt(index)));
    }

    private void ensureRequiredConstraintsIsMutable() {
        if (!this.requiredConstraints_.isModifiable()) {
            this.requiredConstraints_ = GeneratedMessageLite.mutableCopy(this.requiredConstraints_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setRequiredConstraints(int index, ConstraintEnum value) {
        if (value != null) {
            ensureRequiredConstraintsIsMutable();
            this.requiredConstraints_.setInt(index, value.getNumber());
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addRequiredConstraints(ConstraintEnum value) {
        if (value != null) {
            ensureRequiredConstraintsIsMutable();
            this.requiredConstraints_.addInt(value.getNumber());
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllRequiredConstraints(Iterable<? extends ConstraintEnum> values) {
        ensureRequiredConstraintsIsMutable();
        for (ConstraintEnum value : values) {
            this.requiredConstraints_.addInt(value.getNumber());
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearRequiredConstraints() {
        this.requiredConstraints_ = emptyIntList();
    }

    @Override // com.android.server.job.JobStatusDumpProtoOrBuilder
    public List<ConstraintEnum> getSatisfiedConstraintsList() {
        return new Internal.ListAdapter(this.satisfiedConstraints_, satisfiedConstraints_converter_);
    }

    @Override // com.android.server.job.JobStatusDumpProtoOrBuilder
    public int getSatisfiedConstraintsCount() {
        return this.satisfiedConstraints_.size();
    }

    @Override // com.android.server.job.JobStatusDumpProtoOrBuilder
    public ConstraintEnum getSatisfiedConstraints(int index) {
        return satisfiedConstraints_converter_.convert(Integer.valueOf(this.satisfiedConstraints_.getInt(index)));
    }

    private void ensureSatisfiedConstraintsIsMutable() {
        if (!this.satisfiedConstraints_.isModifiable()) {
            this.satisfiedConstraints_ = GeneratedMessageLite.mutableCopy(this.satisfiedConstraints_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setSatisfiedConstraints(int index, ConstraintEnum value) {
        if (value != null) {
            ensureSatisfiedConstraintsIsMutable();
            this.satisfiedConstraints_.setInt(index, value.getNumber());
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addSatisfiedConstraints(ConstraintEnum value) {
        if (value != null) {
            ensureSatisfiedConstraintsIsMutable();
            this.satisfiedConstraints_.addInt(value.getNumber());
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllSatisfiedConstraints(Iterable<? extends ConstraintEnum> values) {
        ensureSatisfiedConstraintsIsMutable();
        for (ConstraintEnum value : values) {
            this.satisfiedConstraints_.addInt(value.getNumber());
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearSatisfiedConstraints() {
        this.satisfiedConstraints_ = emptyIntList();
    }

    @Override // com.android.server.job.JobStatusDumpProtoOrBuilder
    public List<ConstraintEnum> getUnsatisfiedConstraintsList() {
        return new Internal.ListAdapter(this.unsatisfiedConstraints_, unsatisfiedConstraints_converter_);
    }

    @Override // com.android.server.job.JobStatusDumpProtoOrBuilder
    public int getUnsatisfiedConstraintsCount() {
        return this.unsatisfiedConstraints_.size();
    }

    @Override // com.android.server.job.JobStatusDumpProtoOrBuilder
    public ConstraintEnum getUnsatisfiedConstraints(int index) {
        return unsatisfiedConstraints_converter_.convert(Integer.valueOf(this.unsatisfiedConstraints_.getInt(index)));
    }

    private void ensureUnsatisfiedConstraintsIsMutable() {
        if (!this.unsatisfiedConstraints_.isModifiable()) {
            this.unsatisfiedConstraints_ = GeneratedMessageLite.mutableCopy(this.unsatisfiedConstraints_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setUnsatisfiedConstraints(int index, ConstraintEnum value) {
        if (value != null) {
            ensureUnsatisfiedConstraintsIsMutable();
            this.unsatisfiedConstraints_.setInt(index, value.getNumber());
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addUnsatisfiedConstraints(ConstraintEnum value) {
        if (value != null) {
            ensureUnsatisfiedConstraintsIsMutable();
            this.unsatisfiedConstraints_.addInt(value.getNumber());
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllUnsatisfiedConstraints(Iterable<? extends ConstraintEnum> values) {
        ensureUnsatisfiedConstraintsIsMutable();
        for (ConstraintEnum value : values) {
            this.unsatisfiedConstraints_.addInt(value.getNumber());
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearUnsatisfiedConstraints() {
        this.unsatisfiedConstraints_ = emptyIntList();
    }

    @Override // com.android.server.job.JobStatusDumpProtoOrBuilder
    public boolean hasIsDozeWhitelisted() {
        return (this.bitField0_ & 64) == 64;
    }

    @Override // com.android.server.job.JobStatusDumpProtoOrBuilder
    public boolean getIsDozeWhitelisted() {
        return this.isDozeWhitelisted_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setIsDozeWhitelisted(boolean value) {
        this.bitField0_ |= 64;
        this.isDozeWhitelisted_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearIsDozeWhitelisted() {
        this.bitField0_ &= -65;
        this.isDozeWhitelisted_ = false;
    }

    @Override // com.android.server.job.JobStatusDumpProtoOrBuilder
    public boolean hasIsUidActive() {
        return (this.bitField0_ & 128) == 128;
    }

    @Override // com.android.server.job.JobStatusDumpProtoOrBuilder
    public boolean getIsUidActive() {
        return this.isUidActive_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setIsUidActive(boolean value) {
        this.bitField0_ |= 128;
        this.isUidActive_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearIsUidActive() {
        this.bitField0_ &= -129;
        this.isUidActive_ = false;
    }

    @Override // com.android.server.job.JobStatusDumpProtoOrBuilder
    public boolean hasImplicitConstraints() {
        return (this.bitField0_ & 256) == 256;
    }

    @Override // com.android.server.job.JobStatusDumpProtoOrBuilder
    public ImplicitConstraints getImplicitConstraints() {
        ImplicitConstraints implicitConstraints = this.implicitConstraints_;
        return implicitConstraints == null ? ImplicitConstraints.getDefaultInstance() : implicitConstraints;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setImplicitConstraints(ImplicitConstraints value) {
        if (value != null) {
            this.implicitConstraints_ = value;
            this.bitField0_ |= 256;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setImplicitConstraints(ImplicitConstraints.Builder builderForValue) {
        this.implicitConstraints_ = (ImplicitConstraints) builderForValue.build();
        this.bitField0_ |= 256;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeImplicitConstraints(ImplicitConstraints value) {
        ImplicitConstraints implicitConstraints = this.implicitConstraints_;
        if (implicitConstraints == null || implicitConstraints == ImplicitConstraints.getDefaultInstance()) {
            this.implicitConstraints_ = value;
        } else {
            this.implicitConstraints_ = (ImplicitConstraints) ((ImplicitConstraints.Builder) ImplicitConstraints.newBuilder(this.implicitConstraints_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 256;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearImplicitConstraints() {
        this.implicitConstraints_ = null;
        this.bitField0_ &= -257;
    }

    @Override // com.android.server.job.JobStatusDumpProtoOrBuilder
    public List<TrackingController> getTrackingControllersList() {
        return new Internal.ListAdapter(this.trackingControllers_, trackingControllers_converter_);
    }

    @Override // com.android.server.job.JobStatusDumpProtoOrBuilder
    public int getTrackingControllersCount() {
        return this.trackingControllers_.size();
    }

    @Override // com.android.server.job.JobStatusDumpProtoOrBuilder
    public TrackingController getTrackingControllers(int index) {
        return trackingControllers_converter_.convert(Integer.valueOf(this.trackingControllers_.getInt(index)));
    }

    private void ensureTrackingControllersIsMutable() {
        if (!this.trackingControllers_.isModifiable()) {
            this.trackingControllers_ = GeneratedMessageLite.mutableCopy(this.trackingControllers_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setTrackingControllers(int index, TrackingController value) {
        if (value != null) {
            ensureTrackingControllersIsMutable();
            this.trackingControllers_.setInt(index, value.getNumber());
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addTrackingControllers(TrackingController value) {
        if (value != null) {
            ensureTrackingControllersIsMutable();
            this.trackingControllers_.addInt(value.getNumber());
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllTrackingControllers(Iterable<? extends TrackingController> values) {
        ensureTrackingControllersIsMutable();
        for (TrackingController value : values) {
            this.trackingControllers_.addInt(value.getNumber());
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearTrackingControllers() {
        this.trackingControllers_ = emptyIntList();
    }

    @Override // com.android.server.job.JobStatusDumpProtoOrBuilder
    public List<String> getChangedAuthoritiesList() {
        return this.changedAuthorities_;
    }

    @Override // com.android.server.job.JobStatusDumpProtoOrBuilder
    public int getChangedAuthoritiesCount() {
        return this.changedAuthorities_.size();
    }

    @Override // com.android.server.job.JobStatusDumpProtoOrBuilder
    public String getChangedAuthorities(int index) {
        return this.changedAuthorities_.get(index);
    }

    @Override // com.android.server.job.JobStatusDumpProtoOrBuilder
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

    @Override // com.android.server.job.JobStatusDumpProtoOrBuilder
    public List<String> getChangedUrisList() {
        return this.changedUris_;
    }

    @Override // com.android.server.job.JobStatusDumpProtoOrBuilder
    public int getChangedUrisCount() {
        return this.changedUris_.size();
    }

    @Override // com.android.server.job.JobStatusDumpProtoOrBuilder
    public String getChangedUris(int index) {
        return this.changedUris_.get(index);
    }

    @Override // com.android.server.job.JobStatusDumpProtoOrBuilder
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

    @Override // com.android.server.job.JobStatusDumpProtoOrBuilder
    public boolean hasNetwork() {
        return (this.bitField0_ & 512) == 512;
    }

    @Override // com.android.server.job.JobStatusDumpProtoOrBuilder
    public NetworkProto getNetwork() {
        NetworkProto networkProto = this.network_;
        return networkProto == null ? NetworkProto.getDefaultInstance() : networkProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setNetwork(NetworkProto value) {
        if (value != null) {
            this.network_ = value;
            this.bitField0_ |= 512;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setNetwork(NetworkProto.Builder builderForValue) {
        this.network_ = (NetworkProto) builderForValue.build();
        this.bitField0_ |= 512;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeNetwork(NetworkProto value) {
        NetworkProto networkProto = this.network_;
        if (networkProto == null || networkProto == NetworkProto.getDefaultInstance()) {
            this.network_ = value;
        } else {
            this.network_ = (NetworkProto) ((NetworkProto.Builder) NetworkProto.newBuilder(this.network_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 512;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearNetwork() {
        this.network_ = null;
        this.bitField0_ &= -513;
    }

    @Override // com.android.server.job.JobStatusDumpProtoOrBuilder
    public List<JobWorkItem> getPendingWorkList() {
        return this.pendingWork_;
    }

    public List<? extends JobWorkItemOrBuilder> getPendingWorkOrBuilderList() {
        return this.pendingWork_;
    }

    @Override // com.android.server.job.JobStatusDumpProtoOrBuilder
    public int getPendingWorkCount() {
        return this.pendingWork_.size();
    }

    @Override // com.android.server.job.JobStatusDumpProtoOrBuilder
    public JobWorkItem getPendingWork(int index) {
        return this.pendingWork_.get(index);
    }

    public JobWorkItemOrBuilder getPendingWorkOrBuilder(int index) {
        return this.pendingWork_.get(index);
    }

    private void ensurePendingWorkIsMutable() {
        if (!this.pendingWork_.isModifiable()) {
            this.pendingWork_ = GeneratedMessageLite.mutableCopy(this.pendingWork_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPendingWork(int index, JobWorkItem value) {
        if (value != null) {
            ensurePendingWorkIsMutable();
            this.pendingWork_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPendingWork(int index, JobWorkItem.Builder builderForValue) {
        ensurePendingWorkIsMutable();
        this.pendingWork_.set(index, (JobWorkItem) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addPendingWork(JobWorkItem value) {
        if (value != null) {
            ensurePendingWorkIsMutable();
            this.pendingWork_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addPendingWork(int index, JobWorkItem value) {
        if (value != null) {
            ensurePendingWorkIsMutable();
            this.pendingWork_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addPendingWork(JobWorkItem.Builder builderForValue) {
        ensurePendingWorkIsMutable();
        this.pendingWork_.add((JobWorkItem) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addPendingWork(int index, JobWorkItem.Builder builderForValue) {
        ensurePendingWorkIsMutable();
        this.pendingWork_.add(index, (JobWorkItem) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllPendingWork(Iterable<? extends JobWorkItem> values) {
        ensurePendingWorkIsMutable();
        AbstractMessageLite.addAll(values, this.pendingWork_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearPendingWork() {
        this.pendingWork_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removePendingWork(int index) {
        ensurePendingWorkIsMutable();
        this.pendingWork_.remove(index);
    }

    @Override // com.android.server.job.JobStatusDumpProtoOrBuilder
    public List<JobWorkItem> getExecutingWorkList() {
        return this.executingWork_;
    }

    public List<? extends JobWorkItemOrBuilder> getExecutingWorkOrBuilderList() {
        return this.executingWork_;
    }

    @Override // com.android.server.job.JobStatusDumpProtoOrBuilder
    public int getExecutingWorkCount() {
        return this.executingWork_.size();
    }

    @Override // com.android.server.job.JobStatusDumpProtoOrBuilder
    public JobWorkItem getExecutingWork(int index) {
        return this.executingWork_.get(index);
    }

    public JobWorkItemOrBuilder getExecutingWorkOrBuilder(int index) {
        return this.executingWork_.get(index);
    }

    private void ensureExecutingWorkIsMutable() {
        if (!this.executingWork_.isModifiable()) {
            this.executingWork_ = GeneratedMessageLite.mutableCopy(this.executingWork_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setExecutingWork(int index, JobWorkItem value) {
        if (value != null) {
            ensureExecutingWorkIsMutable();
            this.executingWork_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setExecutingWork(int index, JobWorkItem.Builder builderForValue) {
        ensureExecutingWorkIsMutable();
        this.executingWork_.set(index, (JobWorkItem) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addExecutingWork(JobWorkItem value) {
        if (value != null) {
            ensureExecutingWorkIsMutable();
            this.executingWork_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addExecutingWork(int index, JobWorkItem value) {
        if (value != null) {
            ensureExecutingWorkIsMutable();
            this.executingWork_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addExecutingWork(JobWorkItem.Builder builderForValue) {
        ensureExecutingWorkIsMutable();
        this.executingWork_.add((JobWorkItem) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addExecutingWork(int index, JobWorkItem.Builder builderForValue) {
        ensureExecutingWorkIsMutable();
        this.executingWork_.add(index, (JobWorkItem) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllExecutingWork(Iterable<? extends JobWorkItem> values) {
        ensureExecutingWorkIsMutable();
        AbstractMessageLite.addAll(values, this.executingWork_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearExecutingWork() {
        this.executingWork_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeExecutingWork(int index) {
        ensureExecutingWorkIsMutable();
        this.executingWork_.remove(index);
    }

    @Override // com.android.server.job.JobStatusDumpProtoOrBuilder
    public boolean hasStandbyBucket() {
        return (this.bitField0_ & 1024) == 1024;
    }

    @Override // com.android.server.job.JobStatusDumpProtoOrBuilder
    public Bucket getStandbyBucket() {
        Bucket result = Bucket.forNumber(this.standbyBucket_);
        return result == null ? Bucket.ACTIVE : result;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setStandbyBucket(Bucket value) {
        if (value != null) {
            this.bitField0_ |= 1024;
            this.standbyBucket_ = value.getNumber();
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearStandbyBucket() {
        this.bitField0_ &= -1025;
        this.standbyBucket_ = 0;
    }

    @Override // com.android.server.job.JobStatusDumpProtoOrBuilder
    public boolean hasIsExemptedFromAppStandby() {
        return (this.bitField0_ & 2048) == 2048;
    }

    @Override // com.android.server.job.JobStatusDumpProtoOrBuilder
    public boolean getIsExemptedFromAppStandby() {
        return this.isExemptedFromAppStandby_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setIsExemptedFromAppStandby(boolean value) {
        this.bitField0_ |= 2048;
        this.isExemptedFromAppStandby_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearIsExemptedFromAppStandby() {
        this.bitField0_ &= -2049;
        this.isExemptedFromAppStandby_ = false;
    }

    @Override // com.android.server.job.JobStatusDumpProtoOrBuilder
    public boolean hasEnqueueDurationMs() {
        return (this.bitField0_ & 4096) == 4096;
    }

    @Override // com.android.server.job.JobStatusDumpProtoOrBuilder
    public long getEnqueueDurationMs() {
        return this.enqueueDurationMs_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setEnqueueDurationMs(long value) {
        this.bitField0_ |= 4096;
        this.enqueueDurationMs_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearEnqueueDurationMs() {
        this.bitField0_ &= -4097;
        this.enqueueDurationMs_ = 0;
    }

    @Override // com.android.server.job.JobStatusDumpProtoOrBuilder
    public boolean hasTimeUntilEarliestRuntimeMs() {
        return (this.bitField0_ & 8192) == 8192;
    }

    @Override // com.android.server.job.JobStatusDumpProtoOrBuilder
    public long getTimeUntilEarliestRuntimeMs() {
        return this.timeUntilEarliestRuntimeMs_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setTimeUntilEarliestRuntimeMs(long value) {
        this.bitField0_ |= 8192;
        this.timeUntilEarliestRuntimeMs_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearTimeUntilEarliestRuntimeMs() {
        this.bitField0_ &= -8193;
        this.timeUntilEarliestRuntimeMs_ = 0;
    }

    @Override // com.android.server.job.JobStatusDumpProtoOrBuilder
    public boolean hasTimeUntilLatestRuntimeMs() {
        return (this.bitField0_ & 16384) == 16384;
    }

    @Override // com.android.server.job.JobStatusDumpProtoOrBuilder
    public long getTimeUntilLatestRuntimeMs() {
        return this.timeUntilLatestRuntimeMs_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setTimeUntilLatestRuntimeMs(long value) {
        this.bitField0_ |= 16384;
        this.timeUntilLatestRuntimeMs_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearTimeUntilLatestRuntimeMs() {
        this.bitField0_ &= -16385;
        this.timeUntilLatestRuntimeMs_ = 0;
    }

    @Override // com.android.server.job.JobStatusDumpProtoOrBuilder
    public boolean hasNumFailures() {
        return (this.bitField0_ & 32768) == 32768;
    }

    @Override // com.android.server.job.JobStatusDumpProtoOrBuilder
    public int getNumFailures() {
        return this.numFailures_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setNumFailures(int value) {
        this.bitField0_ |= 32768;
        this.numFailures_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearNumFailures() {
        this.bitField0_ &= -32769;
        this.numFailures_ = 0;
    }

    @Override // com.android.server.job.JobStatusDumpProtoOrBuilder
    public boolean hasLastSuccessfulRunTime() {
        return (this.bitField0_ & 65536) == 65536;
    }

    @Override // com.android.server.job.JobStatusDumpProtoOrBuilder
    public long getLastSuccessfulRunTime() {
        return this.lastSuccessfulRunTime_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setLastSuccessfulRunTime(long value) {
        this.bitField0_ |= 65536;
        this.lastSuccessfulRunTime_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearLastSuccessfulRunTime() {
        this.bitField0_ &= -65537;
        this.lastSuccessfulRunTime_ = 0;
    }

    @Override // com.android.server.job.JobStatusDumpProtoOrBuilder
    public boolean hasLastFailedRunTime() {
        return (this.bitField0_ & 131072) == 131072;
    }

    @Override // com.android.server.job.JobStatusDumpProtoOrBuilder
    public long getLastFailedRunTime() {
        return this.lastFailedRunTime_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setLastFailedRunTime(long value) {
        this.bitField0_ |= 131072;
        this.lastFailedRunTime_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearLastFailedRunTime() {
        this.bitField0_ &= -131073;
        this.lastFailedRunTime_ = 0;
    }

    @Override // com.android.server.job.JobStatusDumpProtoOrBuilder
    public boolean hasInternalFlags() {
        return (this.bitField0_ & 262144) == 262144;
    }

    @Override // com.android.server.job.JobStatusDumpProtoOrBuilder
    public long getInternalFlags() {
        return this.internalFlags_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setInternalFlags(long value) {
        this.bitField0_ |= 262144;
        this.internalFlags_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearInternalFlags() {
        this.bitField0_ &= -262145;
        this.internalFlags_ = 0;
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeInt32(1, this.callingUid_);
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeString(2, getTag());
        }
        if ((this.bitField0_ & 4) == 4) {
            output.writeInt32(3, this.sourceUid_);
        }
        if ((this.bitField0_ & 8) == 8) {
            output.writeInt32(4, this.sourceUserId_);
        }
        if ((this.bitField0_ & 16) == 16) {
            output.writeString(5, getSourcePackageName());
        }
        if ((this.bitField0_ & 32) == 32) {
            output.writeMessage(6, getJobInfo());
        }
        for (int i = 0; i < this.requiredConstraints_.size(); i++) {
            output.writeEnum(7, this.requiredConstraints_.getInt(i));
        }
        for (int i2 = 0; i2 < this.satisfiedConstraints_.size(); i2++) {
            output.writeEnum(8, this.satisfiedConstraints_.getInt(i2));
        }
        for (int i3 = 0; i3 < this.unsatisfiedConstraints_.size(); i3++) {
            output.writeEnum(9, this.unsatisfiedConstraints_.getInt(i3));
        }
        if ((this.bitField0_ & 64) == 64) {
            output.writeBool(10, this.isDozeWhitelisted_);
        }
        for (int i4 = 0; i4 < this.trackingControllers_.size(); i4++) {
            output.writeEnum(11, this.trackingControllers_.getInt(i4));
        }
        for (int i5 = 0; i5 < this.changedAuthorities_.size(); i5++) {
            output.writeString(12, this.changedAuthorities_.get(i5));
        }
        for (int i6 = 0; i6 < this.changedUris_.size(); i6++) {
            output.writeString(13, this.changedUris_.get(i6));
        }
        if ((this.bitField0_ & 512) == 512) {
            output.writeMessage(14, getNetwork());
        }
        for (int i7 = 0; i7 < this.pendingWork_.size(); i7++) {
            output.writeMessage(15, this.pendingWork_.get(i7));
        }
        for (int i8 = 0; i8 < this.executingWork_.size(); i8++) {
            output.writeMessage(16, this.executingWork_.get(i8));
        }
        if ((this.bitField0_ & 1024) == 1024) {
            output.writeEnum(17, this.standbyBucket_);
        }
        if ((this.bitField0_ & 4096) == 4096) {
            output.writeInt64(18, this.enqueueDurationMs_);
        }
        if ((this.bitField0_ & 8192) == 8192) {
            output.writeSInt64(19, this.timeUntilEarliestRuntimeMs_);
        }
        if ((this.bitField0_ & 16384) == 16384) {
            output.writeSInt64(20, this.timeUntilLatestRuntimeMs_);
        }
        if ((this.bitField0_ & 32768) == 32768) {
            output.writeInt32(21, this.numFailures_);
        }
        if ((this.bitField0_ & 65536) == 65536) {
            output.writeInt64(22, this.lastSuccessfulRunTime_);
        }
        if ((this.bitField0_ & 131072) == 131072) {
            output.writeInt64(23, this.lastFailedRunTime_);
        }
        if ((this.bitField0_ & 262144) == 262144) {
            output.writeInt64(24, this.internalFlags_);
        }
        if ((this.bitField0_ & 256) == 256) {
            output.writeMessage(25, getImplicitConstraints());
        }
        if ((this.bitField0_ & 128) == 128) {
            output.writeBool(26, this.isUidActive_);
        }
        if ((this.bitField0_ & 2048) == 2048) {
            output.writeBool(27, this.isExemptedFromAppStandby_);
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
            size2 = 0 + CodedOutputStream.computeInt32Size(1, this.callingUid_);
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeStringSize(2, getTag());
        }
        if ((this.bitField0_ & 4) == 4) {
            size2 += CodedOutputStream.computeInt32Size(3, this.sourceUid_);
        }
        if ((this.bitField0_ & 8) == 8) {
            size2 += CodedOutputStream.computeInt32Size(4, this.sourceUserId_);
        }
        if ((this.bitField0_ & 16) == 16) {
            size2 += CodedOutputStream.computeStringSize(5, getSourcePackageName());
        }
        if ((this.bitField0_ & 32) == 32) {
            size2 += CodedOutputStream.computeMessageSize(6, getJobInfo());
        }
        int dataSize = 0;
        for (int i = 0; i < this.requiredConstraints_.size(); i++) {
            dataSize += CodedOutputStream.computeEnumSizeNoTag(this.requiredConstraints_.getInt(i));
        }
        int size3 = size2 + dataSize + (this.requiredConstraints_.size() * 1);
        int dataSize2 = 0;
        for (int i2 = 0; i2 < this.satisfiedConstraints_.size(); i2++) {
            dataSize2 += CodedOutputStream.computeEnumSizeNoTag(this.satisfiedConstraints_.getInt(i2));
        }
        int size4 = size3 + dataSize2 + (this.satisfiedConstraints_.size() * 1);
        int dataSize3 = 0;
        for (int i3 = 0; i3 < this.unsatisfiedConstraints_.size(); i3++) {
            dataSize3 += CodedOutputStream.computeEnumSizeNoTag(this.unsatisfiedConstraints_.getInt(i3));
        }
        int size5 = size4 + dataSize3 + (this.unsatisfiedConstraints_.size() * 1);
        if ((this.bitField0_ & 64) == 64) {
            size5 += CodedOutputStream.computeBoolSize(10, this.isDozeWhitelisted_);
        }
        int dataSize4 = 0;
        for (int i4 = 0; i4 < this.trackingControllers_.size(); i4++) {
            dataSize4 += CodedOutputStream.computeEnumSizeNoTag(this.trackingControllers_.getInt(i4));
        }
        int size6 = size5 + dataSize4 + (this.trackingControllers_.size() * 1);
        int dataSize5 = 0;
        for (int i5 = 0; i5 < this.changedAuthorities_.size(); i5++) {
            dataSize5 += CodedOutputStream.computeStringSizeNoTag(this.changedAuthorities_.get(i5));
        }
        int size7 = size6 + dataSize5 + (getChangedAuthoritiesList().size() * 1);
        int dataSize6 = 0;
        for (int i6 = 0; i6 < this.changedUris_.size(); i6++) {
            dataSize6 += CodedOutputStream.computeStringSizeNoTag(this.changedUris_.get(i6));
        }
        int size8 = size7 + dataSize6 + (getChangedUrisList().size() * 1);
        if ((this.bitField0_ & 512) == 512) {
            size8 += CodedOutputStream.computeMessageSize(14, getNetwork());
        }
        for (int i7 = 0; i7 < this.pendingWork_.size(); i7++) {
            size8 += CodedOutputStream.computeMessageSize(15, this.pendingWork_.get(i7));
        }
        for (int i8 = 0; i8 < this.executingWork_.size(); i8++) {
            size8 += CodedOutputStream.computeMessageSize(16, this.executingWork_.get(i8));
        }
        if ((this.bitField0_ & 1024) == 1024) {
            size8 += CodedOutputStream.computeEnumSize(17, this.standbyBucket_);
        }
        if ((this.bitField0_ & 4096) == 4096) {
            size8 += CodedOutputStream.computeInt64Size(18, this.enqueueDurationMs_);
        }
        if ((this.bitField0_ & 8192) == 8192) {
            size8 += CodedOutputStream.computeSInt64Size(19, this.timeUntilEarliestRuntimeMs_);
        }
        if ((this.bitField0_ & 16384) == 16384) {
            size8 += CodedOutputStream.computeSInt64Size(20, this.timeUntilLatestRuntimeMs_);
        }
        if ((this.bitField0_ & 32768) == 32768) {
            size8 += CodedOutputStream.computeInt32Size(21, this.numFailures_);
        }
        if ((this.bitField0_ & 65536) == 65536) {
            size8 += CodedOutputStream.computeInt64Size(22, this.lastSuccessfulRunTime_);
        }
        if ((this.bitField0_ & 131072) == 131072) {
            size8 += CodedOutputStream.computeInt64Size(23, this.lastFailedRunTime_);
        }
        if ((this.bitField0_ & 262144) == 262144) {
            size8 += CodedOutputStream.computeInt64Size(24, this.internalFlags_);
        }
        if ((this.bitField0_ & 256) == 256) {
            size8 += CodedOutputStream.computeMessageSize(25, getImplicitConstraints());
        }
        if ((this.bitField0_ & 128) == 128) {
            size8 += CodedOutputStream.computeBoolSize(26, this.isUidActive_);
        }
        if ((this.bitField0_ & 2048) == 2048) {
            size8 += CodedOutputStream.computeBoolSize(27, this.isExemptedFromAppStandby_);
        }
        int size9 = size8 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size9;
        return size9;
    }

    public static JobStatusDumpProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (JobStatusDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static JobStatusDumpProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (JobStatusDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static JobStatusDumpProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (JobStatusDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static JobStatusDumpProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (JobStatusDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static JobStatusDumpProto parseFrom(InputStream input) throws IOException {
        return (JobStatusDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static JobStatusDumpProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (JobStatusDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static JobStatusDumpProto parseDelimitedFrom(InputStream input) throws IOException {
        return (JobStatusDumpProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static JobStatusDumpProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (JobStatusDumpProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static JobStatusDumpProto parseFrom(CodedInputStream input) throws IOException {
        return (JobStatusDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static JobStatusDumpProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (JobStatusDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(JobStatusDumpProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<JobStatusDumpProto, Builder> implements JobStatusDumpProtoOrBuilder {
        private Builder() {
            super(JobStatusDumpProto.DEFAULT_INSTANCE);
        }

        @Override // com.android.server.job.JobStatusDumpProtoOrBuilder
        public boolean hasCallingUid() {
            return ((JobStatusDumpProto) this.instance).hasCallingUid();
        }

        @Override // com.android.server.job.JobStatusDumpProtoOrBuilder
        public int getCallingUid() {
            return ((JobStatusDumpProto) this.instance).getCallingUid();
        }

        public Builder setCallingUid(int value) {
            copyOnWrite();
            ((JobStatusDumpProto) this.instance).setCallingUid(value);
            return this;
        }

        public Builder clearCallingUid() {
            copyOnWrite();
            ((JobStatusDumpProto) this.instance).clearCallingUid();
            return this;
        }

        @Override // com.android.server.job.JobStatusDumpProtoOrBuilder
        public boolean hasTag() {
            return ((JobStatusDumpProto) this.instance).hasTag();
        }

        @Override // com.android.server.job.JobStatusDumpProtoOrBuilder
        public String getTag() {
            return ((JobStatusDumpProto) this.instance).getTag();
        }

        @Override // com.android.server.job.JobStatusDumpProtoOrBuilder
        public ByteString getTagBytes() {
            return ((JobStatusDumpProto) this.instance).getTagBytes();
        }

        public Builder setTag(String value) {
            copyOnWrite();
            ((JobStatusDumpProto) this.instance).setTag(value);
            return this;
        }

        public Builder clearTag() {
            copyOnWrite();
            ((JobStatusDumpProto) this.instance).clearTag();
            return this;
        }

        public Builder setTagBytes(ByteString value) {
            copyOnWrite();
            ((JobStatusDumpProto) this.instance).setTagBytes(value);
            return this;
        }

        @Override // com.android.server.job.JobStatusDumpProtoOrBuilder
        public boolean hasSourceUid() {
            return ((JobStatusDumpProto) this.instance).hasSourceUid();
        }

        @Override // com.android.server.job.JobStatusDumpProtoOrBuilder
        public int getSourceUid() {
            return ((JobStatusDumpProto) this.instance).getSourceUid();
        }

        public Builder setSourceUid(int value) {
            copyOnWrite();
            ((JobStatusDumpProto) this.instance).setSourceUid(value);
            return this;
        }

        public Builder clearSourceUid() {
            copyOnWrite();
            ((JobStatusDumpProto) this.instance).clearSourceUid();
            return this;
        }

        @Override // com.android.server.job.JobStatusDumpProtoOrBuilder
        public boolean hasSourceUserId() {
            return ((JobStatusDumpProto) this.instance).hasSourceUserId();
        }

        @Override // com.android.server.job.JobStatusDumpProtoOrBuilder
        public int getSourceUserId() {
            return ((JobStatusDumpProto) this.instance).getSourceUserId();
        }

        public Builder setSourceUserId(int value) {
            copyOnWrite();
            ((JobStatusDumpProto) this.instance).setSourceUserId(value);
            return this;
        }

        public Builder clearSourceUserId() {
            copyOnWrite();
            ((JobStatusDumpProto) this.instance).clearSourceUserId();
            return this;
        }

        @Override // com.android.server.job.JobStatusDumpProtoOrBuilder
        public boolean hasSourcePackageName() {
            return ((JobStatusDumpProto) this.instance).hasSourcePackageName();
        }

        @Override // com.android.server.job.JobStatusDumpProtoOrBuilder
        public String getSourcePackageName() {
            return ((JobStatusDumpProto) this.instance).getSourcePackageName();
        }

        @Override // com.android.server.job.JobStatusDumpProtoOrBuilder
        public ByteString getSourcePackageNameBytes() {
            return ((JobStatusDumpProto) this.instance).getSourcePackageNameBytes();
        }

        public Builder setSourcePackageName(String value) {
            copyOnWrite();
            ((JobStatusDumpProto) this.instance).setSourcePackageName(value);
            return this;
        }

        public Builder clearSourcePackageName() {
            copyOnWrite();
            ((JobStatusDumpProto) this.instance).clearSourcePackageName();
            return this;
        }

        public Builder setSourcePackageNameBytes(ByteString value) {
            copyOnWrite();
            ((JobStatusDumpProto) this.instance).setSourcePackageNameBytes(value);
            return this;
        }

        @Override // com.android.server.job.JobStatusDumpProtoOrBuilder
        public boolean hasJobInfo() {
            return ((JobStatusDumpProto) this.instance).hasJobInfo();
        }

        @Override // com.android.server.job.JobStatusDumpProtoOrBuilder
        public JobInfo getJobInfo() {
            return ((JobStatusDumpProto) this.instance).getJobInfo();
        }

        public Builder setJobInfo(JobInfo value) {
            copyOnWrite();
            ((JobStatusDumpProto) this.instance).setJobInfo((JobStatusDumpProto) value);
            return this;
        }

        public Builder setJobInfo(JobInfo.Builder builderForValue) {
            copyOnWrite();
            ((JobStatusDumpProto) this.instance).setJobInfo((JobStatusDumpProto) builderForValue);
            return this;
        }

        public Builder mergeJobInfo(JobInfo value) {
            copyOnWrite();
            ((JobStatusDumpProto) this.instance).mergeJobInfo(value);
            return this;
        }

        public Builder clearJobInfo() {
            copyOnWrite();
            ((JobStatusDumpProto) this.instance).clearJobInfo();
            return this;
        }

        @Override // com.android.server.job.JobStatusDumpProtoOrBuilder
        public List<ConstraintEnum> getRequiredConstraintsList() {
            return ((JobStatusDumpProto) this.instance).getRequiredConstraintsList();
        }

        @Override // com.android.server.job.JobStatusDumpProtoOrBuilder
        public int getRequiredConstraintsCount() {
            return ((JobStatusDumpProto) this.instance).getRequiredConstraintsCount();
        }

        @Override // com.android.server.job.JobStatusDumpProtoOrBuilder
        public ConstraintEnum getRequiredConstraints(int index) {
            return ((JobStatusDumpProto) this.instance).getRequiredConstraints(index);
        }

        public Builder setRequiredConstraints(int index, ConstraintEnum value) {
            copyOnWrite();
            ((JobStatusDumpProto) this.instance).setRequiredConstraints(index, value);
            return this;
        }

        public Builder addRequiredConstraints(ConstraintEnum value) {
            copyOnWrite();
            ((JobStatusDumpProto) this.instance).addRequiredConstraints(value);
            return this;
        }

        public Builder addAllRequiredConstraints(Iterable<? extends ConstraintEnum> values) {
            copyOnWrite();
            ((JobStatusDumpProto) this.instance).addAllRequiredConstraints(values);
            return this;
        }

        public Builder clearRequiredConstraints() {
            copyOnWrite();
            ((JobStatusDumpProto) this.instance).clearRequiredConstraints();
            return this;
        }

        @Override // com.android.server.job.JobStatusDumpProtoOrBuilder
        public List<ConstraintEnum> getSatisfiedConstraintsList() {
            return ((JobStatusDumpProto) this.instance).getSatisfiedConstraintsList();
        }

        @Override // com.android.server.job.JobStatusDumpProtoOrBuilder
        public int getSatisfiedConstraintsCount() {
            return ((JobStatusDumpProto) this.instance).getSatisfiedConstraintsCount();
        }

        @Override // com.android.server.job.JobStatusDumpProtoOrBuilder
        public ConstraintEnum getSatisfiedConstraints(int index) {
            return ((JobStatusDumpProto) this.instance).getSatisfiedConstraints(index);
        }

        public Builder setSatisfiedConstraints(int index, ConstraintEnum value) {
            copyOnWrite();
            ((JobStatusDumpProto) this.instance).setSatisfiedConstraints(index, value);
            return this;
        }

        public Builder addSatisfiedConstraints(ConstraintEnum value) {
            copyOnWrite();
            ((JobStatusDumpProto) this.instance).addSatisfiedConstraints(value);
            return this;
        }

        public Builder addAllSatisfiedConstraints(Iterable<? extends ConstraintEnum> values) {
            copyOnWrite();
            ((JobStatusDumpProto) this.instance).addAllSatisfiedConstraints(values);
            return this;
        }

        public Builder clearSatisfiedConstraints() {
            copyOnWrite();
            ((JobStatusDumpProto) this.instance).clearSatisfiedConstraints();
            return this;
        }

        @Override // com.android.server.job.JobStatusDumpProtoOrBuilder
        public List<ConstraintEnum> getUnsatisfiedConstraintsList() {
            return ((JobStatusDumpProto) this.instance).getUnsatisfiedConstraintsList();
        }

        @Override // com.android.server.job.JobStatusDumpProtoOrBuilder
        public int getUnsatisfiedConstraintsCount() {
            return ((JobStatusDumpProto) this.instance).getUnsatisfiedConstraintsCount();
        }

        @Override // com.android.server.job.JobStatusDumpProtoOrBuilder
        public ConstraintEnum getUnsatisfiedConstraints(int index) {
            return ((JobStatusDumpProto) this.instance).getUnsatisfiedConstraints(index);
        }

        public Builder setUnsatisfiedConstraints(int index, ConstraintEnum value) {
            copyOnWrite();
            ((JobStatusDumpProto) this.instance).setUnsatisfiedConstraints(index, value);
            return this;
        }

        public Builder addUnsatisfiedConstraints(ConstraintEnum value) {
            copyOnWrite();
            ((JobStatusDumpProto) this.instance).addUnsatisfiedConstraints(value);
            return this;
        }

        public Builder addAllUnsatisfiedConstraints(Iterable<? extends ConstraintEnum> values) {
            copyOnWrite();
            ((JobStatusDumpProto) this.instance).addAllUnsatisfiedConstraints(values);
            return this;
        }

        public Builder clearUnsatisfiedConstraints() {
            copyOnWrite();
            ((JobStatusDumpProto) this.instance).clearUnsatisfiedConstraints();
            return this;
        }

        @Override // com.android.server.job.JobStatusDumpProtoOrBuilder
        public boolean hasIsDozeWhitelisted() {
            return ((JobStatusDumpProto) this.instance).hasIsDozeWhitelisted();
        }

        @Override // com.android.server.job.JobStatusDumpProtoOrBuilder
        public boolean getIsDozeWhitelisted() {
            return ((JobStatusDumpProto) this.instance).getIsDozeWhitelisted();
        }

        public Builder setIsDozeWhitelisted(boolean value) {
            copyOnWrite();
            ((JobStatusDumpProto) this.instance).setIsDozeWhitelisted(value);
            return this;
        }

        public Builder clearIsDozeWhitelisted() {
            copyOnWrite();
            ((JobStatusDumpProto) this.instance).clearIsDozeWhitelisted();
            return this;
        }

        @Override // com.android.server.job.JobStatusDumpProtoOrBuilder
        public boolean hasIsUidActive() {
            return ((JobStatusDumpProto) this.instance).hasIsUidActive();
        }

        @Override // com.android.server.job.JobStatusDumpProtoOrBuilder
        public boolean getIsUidActive() {
            return ((JobStatusDumpProto) this.instance).getIsUidActive();
        }

        public Builder setIsUidActive(boolean value) {
            copyOnWrite();
            ((JobStatusDumpProto) this.instance).setIsUidActive(value);
            return this;
        }

        public Builder clearIsUidActive() {
            copyOnWrite();
            ((JobStatusDumpProto) this.instance).clearIsUidActive();
            return this;
        }

        @Override // com.android.server.job.JobStatusDumpProtoOrBuilder
        public boolean hasImplicitConstraints() {
            return ((JobStatusDumpProto) this.instance).hasImplicitConstraints();
        }

        @Override // com.android.server.job.JobStatusDumpProtoOrBuilder
        public ImplicitConstraints getImplicitConstraints() {
            return ((JobStatusDumpProto) this.instance).getImplicitConstraints();
        }

        public Builder setImplicitConstraints(ImplicitConstraints value) {
            copyOnWrite();
            ((JobStatusDumpProto) this.instance).setImplicitConstraints((JobStatusDumpProto) value);
            return this;
        }

        public Builder setImplicitConstraints(ImplicitConstraints.Builder builderForValue) {
            copyOnWrite();
            ((JobStatusDumpProto) this.instance).setImplicitConstraints((JobStatusDumpProto) builderForValue);
            return this;
        }

        public Builder mergeImplicitConstraints(ImplicitConstraints value) {
            copyOnWrite();
            ((JobStatusDumpProto) this.instance).mergeImplicitConstraints(value);
            return this;
        }

        public Builder clearImplicitConstraints() {
            copyOnWrite();
            ((JobStatusDumpProto) this.instance).clearImplicitConstraints();
            return this;
        }

        @Override // com.android.server.job.JobStatusDumpProtoOrBuilder
        public List<TrackingController> getTrackingControllersList() {
            return ((JobStatusDumpProto) this.instance).getTrackingControllersList();
        }

        @Override // com.android.server.job.JobStatusDumpProtoOrBuilder
        public int getTrackingControllersCount() {
            return ((JobStatusDumpProto) this.instance).getTrackingControllersCount();
        }

        @Override // com.android.server.job.JobStatusDumpProtoOrBuilder
        public TrackingController getTrackingControllers(int index) {
            return ((JobStatusDumpProto) this.instance).getTrackingControllers(index);
        }

        public Builder setTrackingControllers(int index, TrackingController value) {
            copyOnWrite();
            ((JobStatusDumpProto) this.instance).setTrackingControllers(index, value);
            return this;
        }

        public Builder addTrackingControllers(TrackingController value) {
            copyOnWrite();
            ((JobStatusDumpProto) this.instance).addTrackingControllers(value);
            return this;
        }

        public Builder addAllTrackingControllers(Iterable<? extends TrackingController> values) {
            copyOnWrite();
            ((JobStatusDumpProto) this.instance).addAllTrackingControllers(values);
            return this;
        }

        public Builder clearTrackingControllers() {
            copyOnWrite();
            ((JobStatusDumpProto) this.instance).clearTrackingControllers();
            return this;
        }

        @Override // com.android.server.job.JobStatusDumpProtoOrBuilder
        public List<String> getChangedAuthoritiesList() {
            return Collections.unmodifiableList(((JobStatusDumpProto) this.instance).getChangedAuthoritiesList());
        }

        @Override // com.android.server.job.JobStatusDumpProtoOrBuilder
        public int getChangedAuthoritiesCount() {
            return ((JobStatusDumpProto) this.instance).getChangedAuthoritiesCount();
        }

        @Override // com.android.server.job.JobStatusDumpProtoOrBuilder
        public String getChangedAuthorities(int index) {
            return ((JobStatusDumpProto) this.instance).getChangedAuthorities(index);
        }

        @Override // com.android.server.job.JobStatusDumpProtoOrBuilder
        public ByteString getChangedAuthoritiesBytes(int index) {
            return ((JobStatusDumpProto) this.instance).getChangedAuthoritiesBytes(index);
        }

        public Builder setChangedAuthorities(int index, String value) {
            copyOnWrite();
            ((JobStatusDumpProto) this.instance).setChangedAuthorities(index, value);
            return this;
        }

        public Builder addChangedAuthorities(String value) {
            copyOnWrite();
            ((JobStatusDumpProto) this.instance).addChangedAuthorities(value);
            return this;
        }

        public Builder addAllChangedAuthorities(Iterable<String> values) {
            copyOnWrite();
            ((JobStatusDumpProto) this.instance).addAllChangedAuthorities(values);
            return this;
        }

        public Builder clearChangedAuthorities() {
            copyOnWrite();
            ((JobStatusDumpProto) this.instance).clearChangedAuthorities();
            return this;
        }

        public Builder addChangedAuthoritiesBytes(ByteString value) {
            copyOnWrite();
            ((JobStatusDumpProto) this.instance).addChangedAuthoritiesBytes(value);
            return this;
        }

        @Override // com.android.server.job.JobStatusDumpProtoOrBuilder
        public List<String> getChangedUrisList() {
            return Collections.unmodifiableList(((JobStatusDumpProto) this.instance).getChangedUrisList());
        }

        @Override // com.android.server.job.JobStatusDumpProtoOrBuilder
        public int getChangedUrisCount() {
            return ((JobStatusDumpProto) this.instance).getChangedUrisCount();
        }

        @Override // com.android.server.job.JobStatusDumpProtoOrBuilder
        public String getChangedUris(int index) {
            return ((JobStatusDumpProto) this.instance).getChangedUris(index);
        }

        @Override // com.android.server.job.JobStatusDumpProtoOrBuilder
        public ByteString getChangedUrisBytes(int index) {
            return ((JobStatusDumpProto) this.instance).getChangedUrisBytes(index);
        }

        public Builder setChangedUris(int index, String value) {
            copyOnWrite();
            ((JobStatusDumpProto) this.instance).setChangedUris(index, value);
            return this;
        }

        public Builder addChangedUris(String value) {
            copyOnWrite();
            ((JobStatusDumpProto) this.instance).addChangedUris(value);
            return this;
        }

        public Builder addAllChangedUris(Iterable<String> values) {
            copyOnWrite();
            ((JobStatusDumpProto) this.instance).addAllChangedUris(values);
            return this;
        }

        public Builder clearChangedUris() {
            copyOnWrite();
            ((JobStatusDumpProto) this.instance).clearChangedUris();
            return this;
        }

        public Builder addChangedUrisBytes(ByteString value) {
            copyOnWrite();
            ((JobStatusDumpProto) this.instance).addChangedUrisBytes(value);
            return this;
        }

        @Override // com.android.server.job.JobStatusDumpProtoOrBuilder
        public boolean hasNetwork() {
            return ((JobStatusDumpProto) this.instance).hasNetwork();
        }

        @Override // com.android.server.job.JobStatusDumpProtoOrBuilder
        public NetworkProto getNetwork() {
            return ((JobStatusDumpProto) this.instance).getNetwork();
        }

        public Builder setNetwork(NetworkProto value) {
            copyOnWrite();
            ((JobStatusDumpProto) this.instance).setNetwork((JobStatusDumpProto) value);
            return this;
        }

        public Builder setNetwork(NetworkProto.Builder builderForValue) {
            copyOnWrite();
            ((JobStatusDumpProto) this.instance).setNetwork((JobStatusDumpProto) builderForValue);
            return this;
        }

        public Builder mergeNetwork(NetworkProto value) {
            copyOnWrite();
            ((JobStatusDumpProto) this.instance).mergeNetwork(value);
            return this;
        }

        public Builder clearNetwork() {
            copyOnWrite();
            ((JobStatusDumpProto) this.instance).clearNetwork();
            return this;
        }

        @Override // com.android.server.job.JobStatusDumpProtoOrBuilder
        public List<JobWorkItem> getPendingWorkList() {
            return Collections.unmodifiableList(((JobStatusDumpProto) this.instance).getPendingWorkList());
        }

        @Override // com.android.server.job.JobStatusDumpProtoOrBuilder
        public int getPendingWorkCount() {
            return ((JobStatusDumpProto) this.instance).getPendingWorkCount();
        }

        @Override // com.android.server.job.JobStatusDumpProtoOrBuilder
        public JobWorkItem getPendingWork(int index) {
            return ((JobStatusDumpProto) this.instance).getPendingWork(index);
        }

        public Builder setPendingWork(int index, JobWorkItem value) {
            copyOnWrite();
            ((JobStatusDumpProto) this.instance).setPendingWork((JobStatusDumpProto) index, (int) value);
            return this;
        }

        public Builder setPendingWork(int index, JobWorkItem.Builder builderForValue) {
            copyOnWrite();
            ((JobStatusDumpProto) this.instance).setPendingWork((JobStatusDumpProto) index, (int) builderForValue);
            return this;
        }

        public Builder addPendingWork(JobWorkItem value) {
            copyOnWrite();
            ((JobStatusDumpProto) this.instance).addPendingWork((JobStatusDumpProto) value);
            return this;
        }

        public Builder addPendingWork(int index, JobWorkItem value) {
            copyOnWrite();
            ((JobStatusDumpProto) this.instance).addPendingWork((JobStatusDumpProto) index, (int) value);
            return this;
        }

        public Builder addPendingWork(JobWorkItem.Builder builderForValue) {
            copyOnWrite();
            ((JobStatusDumpProto) this.instance).addPendingWork((JobStatusDumpProto) builderForValue);
            return this;
        }

        public Builder addPendingWork(int index, JobWorkItem.Builder builderForValue) {
            copyOnWrite();
            ((JobStatusDumpProto) this.instance).addPendingWork((JobStatusDumpProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllPendingWork(Iterable<? extends JobWorkItem> values) {
            copyOnWrite();
            ((JobStatusDumpProto) this.instance).addAllPendingWork(values);
            return this;
        }

        public Builder clearPendingWork() {
            copyOnWrite();
            ((JobStatusDumpProto) this.instance).clearPendingWork();
            return this;
        }

        public Builder removePendingWork(int index) {
            copyOnWrite();
            ((JobStatusDumpProto) this.instance).removePendingWork(index);
            return this;
        }

        @Override // com.android.server.job.JobStatusDumpProtoOrBuilder
        public List<JobWorkItem> getExecutingWorkList() {
            return Collections.unmodifiableList(((JobStatusDumpProto) this.instance).getExecutingWorkList());
        }

        @Override // com.android.server.job.JobStatusDumpProtoOrBuilder
        public int getExecutingWorkCount() {
            return ((JobStatusDumpProto) this.instance).getExecutingWorkCount();
        }

        @Override // com.android.server.job.JobStatusDumpProtoOrBuilder
        public JobWorkItem getExecutingWork(int index) {
            return ((JobStatusDumpProto) this.instance).getExecutingWork(index);
        }

        public Builder setExecutingWork(int index, JobWorkItem value) {
            copyOnWrite();
            ((JobStatusDumpProto) this.instance).setExecutingWork((JobStatusDumpProto) index, (int) value);
            return this;
        }

        public Builder setExecutingWork(int index, JobWorkItem.Builder builderForValue) {
            copyOnWrite();
            ((JobStatusDumpProto) this.instance).setExecutingWork((JobStatusDumpProto) index, (int) builderForValue);
            return this;
        }

        public Builder addExecutingWork(JobWorkItem value) {
            copyOnWrite();
            ((JobStatusDumpProto) this.instance).addExecutingWork((JobStatusDumpProto) value);
            return this;
        }

        public Builder addExecutingWork(int index, JobWorkItem value) {
            copyOnWrite();
            ((JobStatusDumpProto) this.instance).addExecutingWork((JobStatusDumpProto) index, (int) value);
            return this;
        }

        public Builder addExecutingWork(JobWorkItem.Builder builderForValue) {
            copyOnWrite();
            ((JobStatusDumpProto) this.instance).addExecutingWork((JobStatusDumpProto) builderForValue);
            return this;
        }

        public Builder addExecutingWork(int index, JobWorkItem.Builder builderForValue) {
            copyOnWrite();
            ((JobStatusDumpProto) this.instance).addExecutingWork((JobStatusDumpProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllExecutingWork(Iterable<? extends JobWorkItem> values) {
            copyOnWrite();
            ((JobStatusDumpProto) this.instance).addAllExecutingWork(values);
            return this;
        }

        public Builder clearExecutingWork() {
            copyOnWrite();
            ((JobStatusDumpProto) this.instance).clearExecutingWork();
            return this;
        }

        public Builder removeExecutingWork(int index) {
            copyOnWrite();
            ((JobStatusDumpProto) this.instance).removeExecutingWork(index);
            return this;
        }

        @Override // com.android.server.job.JobStatusDumpProtoOrBuilder
        public boolean hasStandbyBucket() {
            return ((JobStatusDumpProto) this.instance).hasStandbyBucket();
        }

        @Override // com.android.server.job.JobStatusDumpProtoOrBuilder
        public Bucket getStandbyBucket() {
            return ((JobStatusDumpProto) this.instance).getStandbyBucket();
        }

        public Builder setStandbyBucket(Bucket value) {
            copyOnWrite();
            ((JobStatusDumpProto) this.instance).setStandbyBucket(value);
            return this;
        }

        public Builder clearStandbyBucket() {
            copyOnWrite();
            ((JobStatusDumpProto) this.instance).clearStandbyBucket();
            return this;
        }

        @Override // com.android.server.job.JobStatusDumpProtoOrBuilder
        public boolean hasIsExemptedFromAppStandby() {
            return ((JobStatusDumpProto) this.instance).hasIsExemptedFromAppStandby();
        }

        @Override // com.android.server.job.JobStatusDumpProtoOrBuilder
        public boolean getIsExemptedFromAppStandby() {
            return ((JobStatusDumpProto) this.instance).getIsExemptedFromAppStandby();
        }

        public Builder setIsExemptedFromAppStandby(boolean value) {
            copyOnWrite();
            ((JobStatusDumpProto) this.instance).setIsExemptedFromAppStandby(value);
            return this;
        }

        public Builder clearIsExemptedFromAppStandby() {
            copyOnWrite();
            ((JobStatusDumpProto) this.instance).clearIsExemptedFromAppStandby();
            return this;
        }

        @Override // com.android.server.job.JobStatusDumpProtoOrBuilder
        public boolean hasEnqueueDurationMs() {
            return ((JobStatusDumpProto) this.instance).hasEnqueueDurationMs();
        }

        @Override // com.android.server.job.JobStatusDumpProtoOrBuilder
        public long getEnqueueDurationMs() {
            return ((JobStatusDumpProto) this.instance).getEnqueueDurationMs();
        }

        public Builder setEnqueueDurationMs(long value) {
            copyOnWrite();
            ((JobStatusDumpProto) this.instance).setEnqueueDurationMs(value);
            return this;
        }

        public Builder clearEnqueueDurationMs() {
            copyOnWrite();
            ((JobStatusDumpProto) this.instance).clearEnqueueDurationMs();
            return this;
        }

        @Override // com.android.server.job.JobStatusDumpProtoOrBuilder
        public boolean hasTimeUntilEarliestRuntimeMs() {
            return ((JobStatusDumpProto) this.instance).hasTimeUntilEarliestRuntimeMs();
        }

        @Override // com.android.server.job.JobStatusDumpProtoOrBuilder
        public long getTimeUntilEarliestRuntimeMs() {
            return ((JobStatusDumpProto) this.instance).getTimeUntilEarliestRuntimeMs();
        }

        public Builder setTimeUntilEarliestRuntimeMs(long value) {
            copyOnWrite();
            ((JobStatusDumpProto) this.instance).setTimeUntilEarliestRuntimeMs(value);
            return this;
        }

        public Builder clearTimeUntilEarliestRuntimeMs() {
            copyOnWrite();
            ((JobStatusDumpProto) this.instance).clearTimeUntilEarliestRuntimeMs();
            return this;
        }

        @Override // com.android.server.job.JobStatusDumpProtoOrBuilder
        public boolean hasTimeUntilLatestRuntimeMs() {
            return ((JobStatusDumpProto) this.instance).hasTimeUntilLatestRuntimeMs();
        }

        @Override // com.android.server.job.JobStatusDumpProtoOrBuilder
        public long getTimeUntilLatestRuntimeMs() {
            return ((JobStatusDumpProto) this.instance).getTimeUntilLatestRuntimeMs();
        }

        public Builder setTimeUntilLatestRuntimeMs(long value) {
            copyOnWrite();
            ((JobStatusDumpProto) this.instance).setTimeUntilLatestRuntimeMs(value);
            return this;
        }

        public Builder clearTimeUntilLatestRuntimeMs() {
            copyOnWrite();
            ((JobStatusDumpProto) this.instance).clearTimeUntilLatestRuntimeMs();
            return this;
        }

        @Override // com.android.server.job.JobStatusDumpProtoOrBuilder
        public boolean hasNumFailures() {
            return ((JobStatusDumpProto) this.instance).hasNumFailures();
        }

        @Override // com.android.server.job.JobStatusDumpProtoOrBuilder
        public int getNumFailures() {
            return ((JobStatusDumpProto) this.instance).getNumFailures();
        }

        public Builder setNumFailures(int value) {
            copyOnWrite();
            ((JobStatusDumpProto) this.instance).setNumFailures(value);
            return this;
        }

        public Builder clearNumFailures() {
            copyOnWrite();
            ((JobStatusDumpProto) this.instance).clearNumFailures();
            return this;
        }

        @Override // com.android.server.job.JobStatusDumpProtoOrBuilder
        public boolean hasLastSuccessfulRunTime() {
            return ((JobStatusDumpProto) this.instance).hasLastSuccessfulRunTime();
        }

        @Override // com.android.server.job.JobStatusDumpProtoOrBuilder
        public long getLastSuccessfulRunTime() {
            return ((JobStatusDumpProto) this.instance).getLastSuccessfulRunTime();
        }

        public Builder setLastSuccessfulRunTime(long value) {
            copyOnWrite();
            ((JobStatusDumpProto) this.instance).setLastSuccessfulRunTime(value);
            return this;
        }

        public Builder clearLastSuccessfulRunTime() {
            copyOnWrite();
            ((JobStatusDumpProto) this.instance).clearLastSuccessfulRunTime();
            return this;
        }

        @Override // com.android.server.job.JobStatusDumpProtoOrBuilder
        public boolean hasLastFailedRunTime() {
            return ((JobStatusDumpProto) this.instance).hasLastFailedRunTime();
        }

        @Override // com.android.server.job.JobStatusDumpProtoOrBuilder
        public long getLastFailedRunTime() {
            return ((JobStatusDumpProto) this.instance).getLastFailedRunTime();
        }

        public Builder setLastFailedRunTime(long value) {
            copyOnWrite();
            ((JobStatusDumpProto) this.instance).setLastFailedRunTime(value);
            return this;
        }

        public Builder clearLastFailedRunTime() {
            copyOnWrite();
            ((JobStatusDumpProto) this.instance).clearLastFailedRunTime();
            return this;
        }

        @Override // com.android.server.job.JobStatusDumpProtoOrBuilder
        public boolean hasInternalFlags() {
            return ((JobStatusDumpProto) this.instance).hasInternalFlags();
        }

        @Override // com.android.server.job.JobStatusDumpProtoOrBuilder
        public long getInternalFlags() {
            return ((JobStatusDumpProto) this.instance).getInternalFlags();
        }

        public Builder setInternalFlags(long value) {
            copyOnWrite();
            ((JobStatusDumpProto) this.instance).setInternalFlags(value);
            return this;
        }

        public Builder clearInternalFlags() {
            copyOnWrite();
            ((JobStatusDumpProto) this.instance).clearInternalFlags();
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new JobStatusDumpProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                this.requiredConstraints_.makeImmutable();
                this.satisfiedConstraints_.makeImmutable();
                this.unsatisfiedConstraints_.makeImmutable();
                this.trackingControllers_.makeImmutable();
                this.changedAuthorities_.makeImmutable();
                this.changedUris_.makeImmutable();
                this.pendingWork_.makeImmutable();
                this.executingWork_.makeImmutable();
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                JobStatusDumpProto other = (JobStatusDumpProto) arg1;
                this.callingUid_ = visitor.visitInt(hasCallingUid(), this.callingUid_, other.hasCallingUid(), other.callingUid_);
                this.tag_ = visitor.visitString(hasTag(), this.tag_, other.hasTag(), other.tag_);
                this.sourceUid_ = visitor.visitInt(hasSourceUid(), this.sourceUid_, other.hasSourceUid(), other.sourceUid_);
                this.sourceUserId_ = visitor.visitInt(hasSourceUserId(), this.sourceUserId_, other.hasSourceUserId(), other.sourceUserId_);
                this.sourcePackageName_ = visitor.visitString(hasSourcePackageName(), this.sourcePackageName_, other.hasSourcePackageName(), other.sourcePackageName_);
                this.jobInfo_ = (JobInfo) visitor.visitMessage(this.jobInfo_, other.jobInfo_);
                this.requiredConstraints_ = visitor.visitIntList(this.requiredConstraints_, other.requiredConstraints_);
                this.satisfiedConstraints_ = visitor.visitIntList(this.satisfiedConstraints_, other.satisfiedConstraints_);
                this.unsatisfiedConstraints_ = visitor.visitIntList(this.unsatisfiedConstraints_, other.unsatisfiedConstraints_);
                this.isDozeWhitelisted_ = visitor.visitBoolean(hasIsDozeWhitelisted(), this.isDozeWhitelisted_, other.hasIsDozeWhitelisted(), other.isDozeWhitelisted_);
                this.isUidActive_ = visitor.visitBoolean(hasIsUidActive(), this.isUidActive_, other.hasIsUidActive(), other.isUidActive_);
                this.implicitConstraints_ = (ImplicitConstraints) visitor.visitMessage(this.implicitConstraints_, other.implicitConstraints_);
                this.trackingControllers_ = visitor.visitIntList(this.trackingControllers_, other.trackingControllers_);
                this.changedAuthorities_ = visitor.visitList(this.changedAuthorities_, other.changedAuthorities_);
                this.changedUris_ = visitor.visitList(this.changedUris_, other.changedUris_);
                this.network_ = (NetworkProto) visitor.visitMessage(this.network_, other.network_);
                this.pendingWork_ = visitor.visitList(this.pendingWork_, other.pendingWork_);
                this.executingWork_ = visitor.visitList(this.executingWork_, other.executingWork_);
                this.standbyBucket_ = visitor.visitInt(hasStandbyBucket(), this.standbyBucket_, other.hasStandbyBucket(), other.standbyBucket_);
                this.isExemptedFromAppStandby_ = visitor.visitBoolean(hasIsExemptedFromAppStandby(), this.isExemptedFromAppStandby_, other.hasIsExemptedFromAppStandby(), other.isExemptedFromAppStandby_);
                this.enqueueDurationMs_ = visitor.visitLong(hasEnqueueDurationMs(), this.enqueueDurationMs_, other.hasEnqueueDurationMs(), other.enqueueDurationMs_);
                this.timeUntilEarliestRuntimeMs_ = visitor.visitLong(hasTimeUntilEarliestRuntimeMs(), this.timeUntilEarliestRuntimeMs_, other.hasTimeUntilEarliestRuntimeMs(), other.timeUntilEarliestRuntimeMs_);
                this.timeUntilLatestRuntimeMs_ = visitor.visitLong(hasTimeUntilLatestRuntimeMs(), this.timeUntilLatestRuntimeMs_, other.hasTimeUntilLatestRuntimeMs(), other.timeUntilLatestRuntimeMs_);
                this.numFailures_ = visitor.visitInt(hasNumFailures(), this.numFailures_, other.hasNumFailures(), other.numFailures_);
                this.lastSuccessfulRunTime_ = visitor.visitLong(hasLastSuccessfulRunTime(), this.lastSuccessfulRunTime_, other.hasLastSuccessfulRunTime(), other.lastSuccessfulRunTime_);
                this.lastFailedRunTime_ = visitor.visitLong(hasLastFailedRunTime(), this.lastFailedRunTime_, other.hasLastFailedRunTime(), other.lastFailedRunTime_);
                this.internalFlags_ = visitor.visitLong(hasInternalFlags(), this.internalFlags_, other.hasInternalFlags(), other.internalFlags_);
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
                                this.callingUid_ = input.readInt32();
                                break;
                            case 18:
                                String s = input.readString();
                                this.bitField0_ |= 2;
                                this.tag_ = s;
                                break;
                            case 24:
                                this.bitField0_ |= 4;
                                this.sourceUid_ = input.readInt32();
                                break;
                            case 32:
                                this.bitField0_ |= 8;
                                this.sourceUserId_ = input.readInt32();
                                break;
                            case 42:
                                String s2 = input.readString();
                                this.bitField0_ |= 16;
                                this.sourcePackageName_ = s2;
                                break;
                            case 50:
                                JobInfo.Builder subBuilder = null;
                                if ((this.bitField0_ & 32) == 32) {
                                    subBuilder = (JobInfo.Builder) this.jobInfo_.toBuilder();
                                }
                                this.jobInfo_ = (JobInfo) input.readMessage(JobInfo.parser(), extensionRegistry);
                                if (subBuilder != null) {
                                    subBuilder.mergeFrom((GeneratedMessageLite) this.jobInfo_);
                                    this.jobInfo_ = (JobInfo) subBuilder.buildPartial();
                                }
                                this.bitField0_ |= 32;
                                break;
                            case 56:
                                if (!this.requiredConstraints_.isModifiable()) {
                                    this.requiredConstraints_ = GeneratedMessageLite.mutableCopy(this.requiredConstraints_);
                                }
                                int rawValue = input.readEnum();
                                if (ConstraintEnum.forNumber(rawValue) != null) {
                                    this.requiredConstraints_.addInt(rawValue);
                                    break;
                                } else {
                                    super.mergeVarintField(7, rawValue);
                                    break;
                                }
                            case 58:
                                if (!this.requiredConstraints_.isModifiable()) {
                                    this.requiredConstraints_ = GeneratedMessageLite.mutableCopy(this.requiredConstraints_);
                                }
                                int oldLimit = input.pushLimit(input.readRawVarint32());
                                while (input.getBytesUntilLimit() > 0) {
                                    int rawValue2 = input.readEnum();
                                    if (ConstraintEnum.forNumber(rawValue2) == null) {
                                        super.mergeVarintField(7, rawValue2);
                                    } else {
                                        this.requiredConstraints_.addInt(rawValue2);
                                    }
                                }
                                input.popLimit(oldLimit);
                                break;
                            case 64:
                                if (!this.satisfiedConstraints_.isModifiable()) {
                                    this.satisfiedConstraints_ = GeneratedMessageLite.mutableCopy(this.satisfiedConstraints_);
                                }
                                int rawValue3 = input.readEnum();
                                if (ConstraintEnum.forNumber(rawValue3) != null) {
                                    this.satisfiedConstraints_.addInt(rawValue3);
                                    break;
                                } else {
                                    super.mergeVarintField(8, rawValue3);
                                    break;
                                }
                            case 66:
                                if (!this.satisfiedConstraints_.isModifiable()) {
                                    this.satisfiedConstraints_ = GeneratedMessageLite.mutableCopy(this.satisfiedConstraints_);
                                }
                                int oldLimit2 = input.pushLimit(input.readRawVarint32());
                                while (input.getBytesUntilLimit() > 0) {
                                    int rawValue4 = input.readEnum();
                                    if (ConstraintEnum.forNumber(rawValue4) == null) {
                                        super.mergeVarintField(8, rawValue4);
                                    } else {
                                        this.satisfiedConstraints_.addInt(rawValue4);
                                    }
                                }
                                input.popLimit(oldLimit2);
                                break;
                            case 72:
                                if (!this.unsatisfiedConstraints_.isModifiable()) {
                                    this.unsatisfiedConstraints_ = GeneratedMessageLite.mutableCopy(this.unsatisfiedConstraints_);
                                }
                                int rawValue5 = input.readEnum();
                                if (ConstraintEnum.forNumber(rawValue5) != null) {
                                    this.unsatisfiedConstraints_.addInt(rawValue5);
                                    break;
                                } else {
                                    super.mergeVarintField(9, rawValue5);
                                    break;
                                }
                            case 74:
                                if (!this.unsatisfiedConstraints_.isModifiable()) {
                                    this.unsatisfiedConstraints_ = GeneratedMessageLite.mutableCopy(this.unsatisfiedConstraints_);
                                }
                                int oldLimit3 = input.pushLimit(input.readRawVarint32());
                                while (input.getBytesUntilLimit() > 0) {
                                    int rawValue6 = input.readEnum();
                                    if (ConstraintEnum.forNumber(rawValue6) == null) {
                                        super.mergeVarintField(9, rawValue6);
                                    } else {
                                        this.unsatisfiedConstraints_.addInt(rawValue6);
                                    }
                                }
                                input.popLimit(oldLimit3);
                                break;
                            case 80:
                                this.bitField0_ |= 64;
                                this.isDozeWhitelisted_ = input.readBool();
                                break;
                            case 88:
                                if (!this.trackingControllers_.isModifiable()) {
                                    this.trackingControllers_ = GeneratedMessageLite.mutableCopy(this.trackingControllers_);
                                }
                                int rawValue7 = input.readEnum();
                                if (TrackingController.forNumber(rawValue7) != null) {
                                    this.trackingControllers_.addInt(rawValue7);
                                    break;
                                } else {
                                    super.mergeVarintField(11, rawValue7);
                                    break;
                                }
                            case 90:
                                if (!this.trackingControllers_.isModifiable()) {
                                    this.trackingControllers_ = GeneratedMessageLite.mutableCopy(this.trackingControllers_);
                                }
                                int oldLimit4 = input.pushLimit(input.readRawVarint32());
                                while (input.getBytesUntilLimit() > 0) {
                                    int rawValue8 = input.readEnum();
                                    if (TrackingController.forNumber(rawValue8) == null) {
                                        super.mergeVarintField(11, rawValue8);
                                    } else {
                                        this.trackingControllers_.addInt(rawValue8);
                                    }
                                }
                                input.popLimit(oldLimit4);
                                break;
                            case 98:
                                String s3 = input.readString();
                                if (!this.changedAuthorities_.isModifiable()) {
                                    this.changedAuthorities_ = GeneratedMessageLite.mutableCopy(this.changedAuthorities_);
                                }
                                this.changedAuthorities_.add(s3);
                                break;
                            case 106:
                                String s4 = input.readString();
                                if (!this.changedUris_.isModifiable()) {
                                    this.changedUris_ = GeneratedMessageLite.mutableCopy(this.changedUris_);
                                }
                                this.changedUris_.add(s4);
                                break;
                            case 114:
                                NetworkProto.Builder subBuilder2 = null;
                                if ((this.bitField0_ & 512) == 512) {
                                    subBuilder2 = (NetworkProto.Builder) this.network_.toBuilder();
                                }
                                this.network_ = (NetworkProto) input.readMessage(NetworkProto.parser(), extensionRegistry);
                                if (subBuilder2 != null) {
                                    subBuilder2.mergeFrom((GeneratedMessageLite) this.network_);
                                    this.network_ = (NetworkProto) subBuilder2.buildPartial();
                                }
                                this.bitField0_ |= 512;
                                break;
                            case 122:
                                if (!this.pendingWork_.isModifiable()) {
                                    this.pendingWork_ = GeneratedMessageLite.mutableCopy(this.pendingWork_);
                                }
                                this.pendingWork_.add((JobWorkItem) input.readMessage(JobWorkItem.parser(), extensionRegistry));
                                break;
                            case 130:
                                if (!this.executingWork_.isModifiable()) {
                                    this.executingWork_ = GeneratedMessageLite.mutableCopy(this.executingWork_);
                                }
                                this.executingWork_.add((JobWorkItem) input.readMessage(JobWorkItem.parser(), extensionRegistry));
                                break;
                            case 136:
                                int rawValue9 = input.readEnum();
                                if (Bucket.forNumber(rawValue9) != null) {
                                    this.bitField0_ |= 1024;
                                    this.standbyBucket_ = rawValue9;
                                    break;
                                } else {
                                    super.mergeVarintField(17, rawValue9);
                                    break;
                                }
                            case 144:
                                this.bitField0_ |= 4096;
                                this.enqueueDurationMs_ = input.readInt64();
                                break;
                            case 152:
                                this.bitField0_ |= 8192;
                                this.timeUntilEarliestRuntimeMs_ = input.readSInt64();
                                break;
                            case 160:
                                this.bitField0_ |= 16384;
                                this.timeUntilLatestRuntimeMs_ = input.readSInt64();
                                break;
                            case 168:
                                this.bitField0_ |= 32768;
                                this.numFailures_ = input.readInt32();
                                break;
                            case AtomsProto.Atom.ASSIST_GESTURE_PROGRESS_REPORTED_FIELD_NUMBER:
                                this.bitField0_ |= 65536;
                                this.lastSuccessfulRunTime_ = input.readInt64();
                                break;
                            case 184:
                                this.bitField0_ |= 131072;
                                this.lastFailedRunTime_ = input.readInt64();
                                break;
                            case AtomsProto.Atom.MEDIAMETRICS_AUDIORECORD_REPORTED_FIELD_NUMBER:
                                this.bitField0_ |= 262144;
                                this.internalFlags_ = input.readInt64();
                                break;
                            case PROCESS_STATS_SUMMARY_VALUE:
                                ImplicitConstraints.Builder subBuilder3 = null;
                                if ((this.bitField0_ & 256) == 256) {
                                    subBuilder3 = (ImplicitConstraints.Builder) this.implicitConstraints_.toBuilder();
                                }
                                this.implicitConstraints_ = (ImplicitConstraints) input.readMessage(ImplicitConstraints.parser(), extensionRegistry);
                                if (subBuilder3 != null) {
                                    subBuilder3.mergeFrom((GeneratedMessageLite) this.implicitConstraints_);
                                    this.implicitConstraints_ = (ImplicitConstraints) subBuilder3.buildPartial();
                                }
                                this.bitField0_ |= 256;
                                break;
                            case AtomsProto.Atom.CONTENT_CAPTURE_SESSION_EVENTS_FIELD_NUMBER:
                                this.bitField0_ |= 128;
                                this.isUidActive_ = input.readBool();
                                break;
                            case AtomsProto.Atom.APP_PERMISSION_FRAGMENT_VIEWED_FIELD_NUMBER:
                                this.bitField0_ |= 2048;
                                this.isExemptedFromAppStandby_ = input.readBool();
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
                    synchronized (JobStatusDumpProto.class) {
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

    public static JobStatusDumpProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<JobStatusDumpProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
