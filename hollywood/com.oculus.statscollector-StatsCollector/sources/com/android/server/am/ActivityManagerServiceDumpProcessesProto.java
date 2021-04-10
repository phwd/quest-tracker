package com.android.server.am;

import android.app.ProfilerInfoProto;
import android.app.UidObserverFlag;
import android.content.ConfigurationProto;
import android.os.PowerManagerInternalProto;
import android.os.PowerManagerProto;
import android.telephony.DataConnectionPowerStateEnum;
import android.util.Duration;
import com.android.os.AtomsProto;
import com.android.server.am.ActiveInstrumentationProto;
import com.android.server.am.AppErrorsProto;
import com.android.server.am.AppTimeTrackerProto;
import com.android.server.am.ImportanceTokenProto;
import com.android.server.am.ProcessOomProto;
import com.android.server.am.ProcessRecordProto;
import com.android.server.am.ProcessToGcProto;
import com.android.server.am.UidRecordProto;
import com.android.server.am.UserControllerProto;
import com.android.server.am.VrControllerProto;
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

public final class ActivityManagerServiceDumpProcessesProto extends GeneratedMessageLite<ActivityManagerServiceDumpProcessesProto, Builder> implements ActivityManagerServiceDumpProcessesProtoOrBuilder {
    public static final int ACTIVE_INSTRUMENTATIONS_FIELD_NUMBER = 3;
    public static final int ACTIVE_UIDS_FIELD_NUMBER = 4;
    public static final int ADJ_SEQ_FIELD_NUMBER = 49;
    public static final int ALLOW_LOWER_MEM_LEVEL_FIELD_NUMBER = 55;
    public static final int ALWAYS_FINISH_ACTIVITIES_FIELD_NUMBER = 36;
    public static final int APP_ERRORS_FIELD_NUMBER = 13;
    public static final int BOOTED_FIELD_NUMBER = 41;
    public static final int BOOTING_FIELD_NUMBER = 43;
    public static final int BOOT_ANIMATION_COMPLETE_FIELD_NUMBER = 45;
    public static final int CALL_FINISH_BOOTING_FIELD_NUMBER = 44;
    public static final int CONFIG_WILL_CHANGE_FIELD_NUMBER = 21;
    public static final int CONTROLLER_FIELD_NUMBER = 37;
    public static final int CURRENT_TRACKER_FIELD_NUMBER = 31;
    public static final int DEBUG_FIELD_NUMBER = 30;
    private static final ActivityManagerServiceDumpProcessesProto DEFAULT_INSTANCE = new ActivityManagerServiceDumpProcessesProto();
    public static final int DEVICE_IDLE_TEMP_WHITELIST_FIELD_NUMBER = 25;
    public static final int DEVICE_IDLE_WHITELIST_FIELD_NUMBER = 24;
    public static final int FACTORY_TEST_FIELD_NUMBER = 42;
    public static final int GC_PROCS_FIELD_NUMBER = 12;
    public static final int GLOBAL_CONFIGURATION_FIELD_NUMBER = 19;
    public static final int GOING_TO_SLEEP_FIELD_NUMBER = 47;
    public static final int HEAVY_WEIGHT_PROC_FIELD_NUMBER = 18;
    public static final int HOME_PROC_FIELD_NUMBER = 15;
    public static final int IMPORTANT_PROCS_FIELD_NUMBER = 8;
    public static final int ISOLATED_PROCS_FIELD_NUMBER = 2;
    public static final int LAST_IDLE_TIME_FIELD_NUMBER = 58;
    public static final int LAST_MEMORY_LEVEL_FIELD_NUMBER = 56;
    public static final int LAST_NUM_PROCESSES_FIELD_NUMBER = 57;
    public static final int LAST_POWER_CHECK_UPTIME_MS_FIELD_NUMBER = 46;
    public static final int LAUNCHING_ACTIVITY_FIELD_NUMBER = 48;
    public static final int LOW_RAM_SINCE_LAST_IDLE_MS_FIELD_NUMBER = 59;
    public static final int LRU_PROCS_FIELD_NUMBER = 6;
    public static final int LRU_SEQ_FIELD_NUMBER = 50;
    public static final int MEM_WATCH_PROCESSES_FIELD_NUMBER = 32;
    public static final int NATIVE_DEBUGGING_APP_FIELD_NUMBER = 35;
    public static final int NEW_NUM_SERVICE_PROCS_FIELD_NUMBER = 54;
    public static final int NUM_CACHED_HIDDEN_PROCS_FIELD_NUMBER = 52;
    public static final int NUM_NON_CACHED_PROCS_FIELD_NUMBER = 51;
    public static final int NUM_SERVICE_PROCS_FIELD_NUMBER = 53;
    public static final int ON_HOLD_PROCS_FIELD_NUMBER = 11;
    private static volatile Parser<ActivityManagerServiceDumpProcessesProto> PARSER = null;
    public static final int PENDING_TEMP_WHITELIST_FIELD_NUMBER = 26;
    public static final int PERSISTENT_STARTING_PROCS_FIELD_NUMBER = 9;
    public static final int PIDS_SELF_LOCKED_FIELD_NUMBER = 7;
    public static final int PREVIOUS_PROC_FIELD_NUMBER = 16;
    public static final int PREVIOUS_PROC_VISIBLE_TIME_MS_FIELD_NUMBER = 17;
    public static final int PROCESSES_READY_FIELD_NUMBER = 39;
    public static final int PROCS_FIELD_NUMBER = 1;
    public static final int PROFILE_FIELD_NUMBER = 34;
    public static final int REMOVED_PROCS_FIELD_NUMBER = 10;
    public static final int RUNNING_VOICE_FIELD_NUMBER = 28;
    public static final int SCREEN_COMPAT_PACKAGES_FIELD_NUMBER = 22;
    public static final int SLEEP_STATUS_FIELD_NUMBER = 27;
    public static final int SYSTEM_READY_FIELD_NUMBER = 40;
    public static final int TOTAL_PERSISTENT_PROCS_FIELD_NUMBER = 38;
    public static final int TRACK_ALLOCATION_APP_FIELD_NUMBER = 33;
    public static final int UID_OBSERVERS_FIELD_NUMBER = 23;
    public static final int USER_CONTROLLER_FIELD_NUMBER = 14;
    public static final int VALIDATE_UIDS_FIELD_NUMBER = 5;
    public static final int VR_CONTROLLER_FIELD_NUMBER = 29;
    private Internal.ProtobufList<ActiveInstrumentationProto> activeInstrumentations_ = emptyProtobufList();
    private Internal.ProtobufList<UidRecordProto> activeUids_ = emptyProtobufList();
    private int adjSeq_ = 0;
    private boolean allowLowerMemLevel_ = false;
    private boolean alwaysFinishActivities_ = false;
    private AppErrorsProto appErrors_;
    private int bitField0_;
    private int bitField1_;
    private boolean bootAnimationComplete_ = false;
    private boolean booted_ = false;
    private boolean booting_ = false;
    private boolean callFinishBooting_ = false;
    private boolean configWillChange_ = false;
    private Controller controller_;
    private AppTimeTrackerProto currentTracker_;
    private DebugApp debug_;
    private Internal.IntList deviceIdleTempWhitelist_ = emptyIntList();
    private Internal.IntList deviceIdleWhitelist_ = emptyIntList();
    private int factoryTest_ = 0;
    private Internal.ProtobufList<ProcessToGcProto> gcProcs_ = emptyProtobufList();
    private ConfigurationProto globalConfiguration_;
    private PowerManagerProto.WakeLock goingToSleep_;
    private ProcessRecordProto heavyWeightProc_;
    private ProcessRecordProto homeProc_;
    private Internal.ProtobufList<ImportanceTokenProto> importantProcs_ = emptyProtobufList();
    private Internal.ProtobufList<ProcessRecordProto> isolatedProcs_ = emptyProtobufList();
    private Duration lastIdleTime_;
    private int lastMemoryLevel_ = 0;
    private int lastNumProcesses_ = 0;
    private long lastPowerCheckUptimeMs_ = 0;
    private PowerManagerProto.WakeLock launchingActivity_;
    private long lowRamSinceLastIdleMs_ = 0;
    private LruProcesses lruProcs_;
    private int lruSeq_ = 0;
    private MemWatchProcess memWatchProcesses_;
    private String nativeDebuggingApp_ = "";
    private int newNumServiceProcs_ = 0;
    private int numCachedHiddenProcs_ = 0;
    private int numNonCachedProcs_ = 0;
    private int numServiceProcs_ = 0;
    private Internal.ProtobufList<ProcessRecordProto> onHoldProcs_ = emptyProtobufList();
    private Internal.ProtobufList<PendingTempWhitelist> pendingTempWhitelist_ = emptyProtobufList();
    private Internal.ProtobufList<ProcessRecordProto> persistentStartingProcs_ = emptyProtobufList();
    private Internal.ProtobufList<ProcessRecordProto> pidsSelfLocked_ = emptyProtobufList();
    private long previousProcVisibleTimeMs_ = 0;
    private ProcessRecordProto previousProc_;
    private boolean processesReady_ = false;
    private Internal.ProtobufList<ProcessRecordProto> procs_ = emptyProtobufList();
    private Profile profile_;
    private Internal.ProtobufList<ProcessRecordProto> removedProcs_ = emptyProtobufList();
    private Voice runningVoice_;
    private Internal.ProtobufList<ScreenCompatPackage> screenCompatPackages_ = emptyProtobufList();
    private SleepStatus sleepStatus_;
    private boolean systemReady_ = false;
    private int totalPersistentProcs_ = 0;
    private String trackAllocationApp_ = "";
    private Internal.ProtobufList<UidObserverRegistrationProto> uidObservers_ = emptyProtobufList();
    private UserControllerProto userController_;
    private Internal.ProtobufList<UidRecordProto> validateUids_ = emptyProtobufList();
    private VrControllerProto vrController_;

    public interface ControllerOrBuilder extends MessageLiteOrBuilder {
        String getController();

        ByteString getControllerBytes();

        boolean getIsAMonkey();

        boolean hasController();

        boolean hasIsAMonkey();
    }

    public interface DebugAppOrBuilder extends MessageLiteOrBuilder {
        String getDebugApp();

        ByteString getDebugAppBytes();

        boolean getDebugTransient();

        String getOrigDebugApp();

        ByteString getOrigDebugAppBytes();

        boolean getOrigWaitForDebugger();

        boolean hasDebugApp();

        boolean hasDebugTransient();

        boolean hasOrigDebugApp();

        boolean hasOrigWaitForDebugger();
    }

    public interface LruProcessesOrBuilder extends MessageLiteOrBuilder {
        ProcessOomProto getList(int i);

        int getListCount();

        List<ProcessOomProto> getListList();

        int getNonActAt();

        int getNonSvcAt();

        int getSize();

        boolean hasNonActAt();

        boolean hasNonSvcAt();

        boolean hasSize();
    }

    public interface MemWatchProcessOrBuilder extends MessageLiteOrBuilder {
        MemWatchProcess.Dump getDump();

        MemWatchProcess.Process getProcs(int i);

        int getProcsCount();

        List<MemWatchProcess.Process> getProcsList();

        boolean hasDump();
    }

    public interface PendingTempWhitelistOrBuilder extends MessageLiteOrBuilder {
        long getDurationMs();

        String getTag();

        ByteString getTagBytes();

        int getTargetUid();

        boolean hasDurationMs();

        boolean hasTag();

        boolean hasTargetUid();
    }

    public interface ProfileOrBuilder extends MessageLiteOrBuilder {
        String getAppName();

        ByteString getAppNameBytes();

        ProfilerInfoProto getInfo();

        ProcessRecordProto getProc();

        int getType();

        boolean hasAppName();

        boolean hasInfo();

        boolean hasProc();

        boolean hasType();
    }

    public interface ScreenCompatPackageOrBuilder extends MessageLiteOrBuilder {
        int getMode();

        String getPackage();

        ByteString getPackageBytes();

        boolean hasMode();

        boolean hasPackage();
    }

    public interface SleepStatusOrBuilder extends MessageLiteOrBuilder {
        boolean getShuttingDown();

        String getSleepTokens(int i);

        ByteString getSleepTokensBytes(int i);

        int getSleepTokensCount();

        List<String> getSleepTokensList();

        boolean getSleeping();

        boolean getTestPssMode();

        PowerManagerInternalProto.Wakefulness getWakefulness();

        boolean hasShuttingDown();

        boolean hasSleeping();

        boolean hasTestPssMode();

        boolean hasWakefulness();
    }

    public interface UidObserverRegistrationProtoOrBuilder extends MessageLiteOrBuilder {
        int getCutPoint();

        UidObserverFlag getFlags(int i);

        int getFlagsCount();

        List<UidObserverFlag> getFlagsList();

        UidObserverRegistrationProto.ProcState getLastProcStates(int i);

        int getLastProcStatesCount();

        List<UidObserverRegistrationProto.ProcState> getLastProcStatesList();

        String getPackage();

        ByteString getPackageBytes();

        int getUid();

        boolean hasCutPoint();

        boolean hasPackage();

        boolean hasUid();
    }

    public interface VoiceOrBuilder extends MessageLiteOrBuilder {
        String getSession();

        ByteString getSessionBytes();

        PowerManagerProto.WakeLock getWakelock();

        boolean hasSession();

        boolean hasWakelock();
    }

    private ActivityManagerServiceDumpProcessesProto() {
    }

    public static final class LruProcesses extends GeneratedMessageLite<LruProcesses, Builder> implements LruProcessesOrBuilder {
        private static final LruProcesses DEFAULT_INSTANCE = new LruProcesses();
        public static final int LIST_FIELD_NUMBER = 4;
        public static final int NON_ACT_AT_FIELD_NUMBER = 2;
        public static final int NON_SVC_AT_FIELD_NUMBER = 3;
        private static volatile Parser<LruProcesses> PARSER = null;
        public static final int SIZE_FIELD_NUMBER = 1;
        private int bitField0_;
        private Internal.ProtobufList<ProcessOomProto> list_ = emptyProtobufList();
        private int nonActAt_ = 0;
        private int nonSvcAt_ = 0;
        private int size_ = 0;

        private LruProcesses() {
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.LruProcessesOrBuilder
        public boolean hasSize() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.LruProcessesOrBuilder
        public int getSize() {
            return this.size_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setSize(int value) {
            this.bitField0_ |= 1;
            this.size_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearSize() {
            this.bitField0_ &= -2;
            this.size_ = 0;
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.LruProcessesOrBuilder
        public boolean hasNonActAt() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.LruProcessesOrBuilder
        public int getNonActAt() {
            return this.nonActAt_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setNonActAt(int value) {
            this.bitField0_ |= 2;
            this.nonActAt_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearNonActAt() {
            this.bitField0_ &= -3;
            this.nonActAt_ = 0;
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.LruProcessesOrBuilder
        public boolean hasNonSvcAt() {
            return (this.bitField0_ & 4) == 4;
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.LruProcessesOrBuilder
        public int getNonSvcAt() {
            return this.nonSvcAt_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setNonSvcAt(int value) {
            this.bitField0_ |= 4;
            this.nonSvcAt_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearNonSvcAt() {
            this.bitField0_ &= -5;
            this.nonSvcAt_ = 0;
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.LruProcessesOrBuilder
        public List<ProcessOomProto> getListList() {
            return this.list_;
        }

        public List<? extends ProcessOomProtoOrBuilder> getListOrBuilderList() {
            return this.list_;
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.LruProcessesOrBuilder
        public int getListCount() {
            return this.list_.size();
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.LruProcessesOrBuilder
        public ProcessOomProto getList(int index) {
            return this.list_.get(index);
        }

        public ProcessOomProtoOrBuilder getListOrBuilder(int index) {
            return this.list_.get(index);
        }

        private void ensureListIsMutable() {
            if (!this.list_.isModifiable()) {
                this.list_ = GeneratedMessageLite.mutableCopy(this.list_);
            }
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setList(int index, ProcessOomProto value) {
            if (value != null) {
                ensureListIsMutable();
                this.list_.set(index, value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setList(int index, ProcessOomProto.Builder builderForValue) {
            ensureListIsMutable();
            this.list_.set(index, (ProcessOomProto) builderForValue.build());
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addList(ProcessOomProto value) {
            if (value != null) {
                ensureListIsMutable();
                this.list_.add(value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addList(int index, ProcessOomProto value) {
            if (value != null) {
                ensureListIsMutable();
                this.list_.add(index, value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addList(ProcessOomProto.Builder builderForValue) {
            ensureListIsMutable();
            this.list_.add((ProcessOomProto) builderForValue.build());
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addList(int index, ProcessOomProto.Builder builderForValue) {
            ensureListIsMutable();
            this.list_.add(index, (ProcessOomProto) builderForValue.build());
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addAllList(Iterable<? extends ProcessOomProto> values) {
            ensureListIsMutable();
            AbstractMessageLite.addAll(values, this.list_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearList() {
            this.list_ = emptyProtobufList();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void removeList(int index) {
            ensureListIsMutable();
            this.list_.remove(index);
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                output.writeInt32(1, this.size_);
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeInt32(2, this.nonActAt_);
            }
            if ((this.bitField0_ & 4) == 4) {
                output.writeInt32(3, this.nonSvcAt_);
            }
            for (int i = 0; i < this.list_.size(); i++) {
                output.writeMessage(4, this.list_.get(i));
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
                size2 = 0 + CodedOutputStream.computeInt32Size(1, this.size_);
            }
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeInt32Size(2, this.nonActAt_);
            }
            if ((this.bitField0_ & 4) == 4) {
                size2 += CodedOutputStream.computeInt32Size(3, this.nonSvcAt_);
            }
            for (int i = 0; i < this.list_.size(); i++) {
                size2 += CodedOutputStream.computeMessageSize(4, this.list_.get(i));
            }
            int size3 = size2 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static LruProcesses parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (LruProcesses) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static LruProcesses parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (LruProcesses) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static LruProcesses parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (LruProcesses) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static LruProcesses parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (LruProcesses) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static LruProcesses parseFrom(InputStream input) throws IOException {
            return (LruProcesses) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static LruProcesses parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (LruProcesses) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static LruProcesses parseDelimitedFrom(InputStream input) throws IOException {
            return (LruProcesses) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static LruProcesses parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (LruProcesses) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static LruProcesses parseFrom(CodedInputStream input) throws IOException {
            return (LruProcesses) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static LruProcesses parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (LruProcesses) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(LruProcesses prototype) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<LruProcesses, Builder> implements LruProcessesOrBuilder {
            private Builder() {
                super(LruProcesses.DEFAULT_INSTANCE);
            }

            @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.LruProcessesOrBuilder
            public boolean hasSize() {
                return ((LruProcesses) this.instance).hasSize();
            }

            @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.LruProcessesOrBuilder
            public int getSize() {
                return ((LruProcesses) this.instance).getSize();
            }

            public Builder setSize(int value) {
                copyOnWrite();
                ((LruProcesses) this.instance).setSize(value);
                return this;
            }

            public Builder clearSize() {
                copyOnWrite();
                ((LruProcesses) this.instance).clearSize();
                return this;
            }

            @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.LruProcessesOrBuilder
            public boolean hasNonActAt() {
                return ((LruProcesses) this.instance).hasNonActAt();
            }

            @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.LruProcessesOrBuilder
            public int getNonActAt() {
                return ((LruProcesses) this.instance).getNonActAt();
            }

            public Builder setNonActAt(int value) {
                copyOnWrite();
                ((LruProcesses) this.instance).setNonActAt(value);
                return this;
            }

            public Builder clearNonActAt() {
                copyOnWrite();
                ((LruProcesses) this.instance).clearNonActAt();
                return this;
            }

            @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.LruProcessesOrBuilder
            public boolean hasNonSvcAt() {
                return ((LruProcesses) this.instance).hasNonSvcAt();
            }

            @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.LruProcessesOrBuilder
            public int getNonSvcAt() {
                return ((LruProcesses) this.instance).getNonSvcAt();
            }

            public Builder setNonSvcAt(int value) {
                copyOnWrite();
                ((LruProcesses) this.instance).setNonSvcAt(value);
                return this;
            }

            public Builder clearNonSvcAt() {
                copyOnWrite();
                ((LruProcesses) this.instance).clearNonSvcAt();
                return this;
            }

            @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.LruProcessesOrBuilder
            public List<ProcessOomProto> getListList() {
                return Collections.unmodifiableList(((LruProcesses) this.instance).getListList());
            }

            @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.LruProcessesOrBuilder
            public int getListCount() {
                return ((LruProcesses) this.instance).getListCount();
            }

            @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.LruProcessesOrBuilder
            public ProcessOomProto getList(int index) {
                return ((LruProcesses) this.instance).getList(index);
            }

            public Builder setList(int index, ProcessOomProto value) {
                copyOnWrite();
                ((LruProcesses) this.instance).setList((LruProcesses) index, (int) value);
                return this;
            }

            public Builder setList(int index, ProcessOomProto.Builder builderForValue) {
                copyOnWrite();
                ((LruProcesses) this.instance).setList((LruProcesses) index, (int) builderForValue);
                return this;
            }

            public Builder addList(ProcessOomProto value) {
                copyOnWrite();
                ((LruProcesses) this.instance).addList((LruProcesses) value);
                return this;
            }

            public Builder addList(int index, ProcessOomProto value) {
                copyOnWrite();
                ((LruProcesses) this.instance).addList((LruProcesses) index, (int) value);
                return this;
            }

            public Builder addList(ProcessOomProto.Builder builderForValue) {
                copyOnWrite();
                ((LruProcesses) this.instance).addList((LruProcesses) builderForValue);
                return this;
            }

            public Builder addList(int index, ProcessOomProto.Builder builderForValue) {
                copyOnWrite();
                ((LruProcesses) this.instance).addList((LruProcesses) index, (int) builderForValue);
                return this;
            }

            public Builder addAllList(Iterable<? extends ProcessOomProto> values) {
                copyOnWrite();
                ((LruProcesses) this.instance).addAllList(values);
                return this;
            }

            public Builder clearList() {
                copyOnWrite();
                ((LruProcesses) this.instance).clearList();
                return this;
            }

            public Builder removeList(int index) {
                copyOnWrite();
                ((LruProcesses) this.instance).removeList(index);
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new LruProcesses();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    this.list_.makeImmutable();
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    LruProcesses other = (LruProcesses) arg1;
                    this.size_ = visitor.visitInt(hasSize(), this.size_, other.hasSize(), other.size_);
                    this.nonActAt_ = visitor.visitInt(hasNonActAt(), this.nonActAt_, other.hasNonActAt(), other.nonActAt_);
                    this.nonSvcAt_ = visitor.visitInt(hasNonSvcAt(), this.nonSvcAt_, other.hasNonSvcAt(), other.nonSvcAt_);
                    this.list_ = visitor.visitList(this.list_, other.list_);
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
                                this.size_ = input.readInt32();
                            } else if (tag == 16) {
                                this.bitField0_ |= 2;
                                this.nonActAt_ = input.readInt32();
                            } else if (tag == 24) {
                                this.bitField0_ |= 4;
                                this.nonSvcAt_ = input.readInt32();
                            } else if (tag == 34) {
                                if (!this.list_.isModifiable()) {
                                    this.list_ = GeneratedMessageLite.mutableCopy(this.list_);
                                }
                                this.list_.add((ProcessOomProto) input.readMessage(ProcessOomProto.parser(), extensionRegistry));
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
                        synchronized (LruProcesses.class) {
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

        public static LruProcesses getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<LruProcesses> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class ScreenCompatPackage extends GeneratedMessageLite<ScreenCompatPackage, Builder> implements ScreenCompatPackageOrBuilder {
        private static final ScreenCompatPackage DEFAULT_INSTANCE = new ScreenCompatPackage();
        public static final int MODE_FIELD_NUMBER = 2;
        public static final int PACKAGE_FIELD_NUMBER = 1;
        private static volatile Parser<ScreenCompatPackage> PARSER;
        private int bitField0_;
        private int mode_ = 0;
        private String package_ = "";

        private ScreenCompatPackage() {
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.ScreenCompatPackageOrBuilder
        public boolean hasPackage() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.ScreenCompatPackageOrBuilder
        public String getPackage() {
            return this.package_;
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.ScreenCompatPackageOrBuilder
        public ByteString getPackageBytes() {
            return ByteString.copyFromUtf8(this.package_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setPackage(String value) {
            if (value != null) {
                this.bitField0_ |= 1;
                this.package_ = value;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearPackage() {
            this.bitField0_ &= -2;
            this.package_ = getDefaultInstance().getPackage();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setPackageBytes(ByteString value) {
            if (value != null) {
                this.bitField0_ |= 1;
                this.package_ = value.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.ScreenCompatPackageOrBuilder
        public boolean hasMode() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.ScreenCompatPackageOrBuilder
        public int getMode() {
            return this.mode_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setMode(int value) {
            this.bitField0_ |= 2;
            this.mode_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearMode() {
            this.bitField0_ &= -3;
            this.mode_ = 0;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                output.writeString(1, getPackage());
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeInt32(2, this.mode_);
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
                size2 = 0 + CodedOutputStream.computeStringSize(1, getPackage());
            }
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeInt32Size(2, this.mode_);
            }
            int size3 = size2 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static ScreenCompatPackage parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (ScreenCompatPackage) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static ScreenCompatPackage parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (ScreenCompatPackage) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static ScreenCompatPackage parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (ScreenCompatPackage) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static ScreenCompatPackage parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (ScreenCompatPackage) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static ScreenCompatPackage parseFrom(InputStream input) throws IOException {
            return (ScreenCompatPackage) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static ScreenCompatPackage parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (ScreenCompatPackage) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static ScreenCompatPackage parseDelimitedFrom(InputStream input) throws IOException {
            return (ScreenCompatPackage) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static ScreenCompatPackage parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (ScreenCompatPackage) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static ScreenCompatPackage parseFrom(CodedInputStream input) throws IOException {
            return (ScreenCompatPackage) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static ScreenCompatPackage parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (ScreenCompatPackage) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(ScreenCompatPackage prototype) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<ScreenCompatPackage, Builder> implements ScreenCompatPackageOrBuilder {
            private Builder() {
                super(ScreenCompatPackage.DEFAULT_INSTANCE);
            }

            @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.ScreenCompatPackageOrBuilder
            public boolean hasPackage() {
                return ((ScreenCompatPackage) this.instance).hasPackage();
            }

            @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.ScreenCompatPackageOrBuilder
            public String getPackage() {
                return ((ScreenCompatPackage) this.instance).getPackage();
            }

            @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.ScreenCompatPackageOrBuilder
            public ByteString getPackageBytes() {
                return ((ScreenCompatPackage) this.instance).getPackageBytes();
            }

            public Builder setPackage(String value) {
                copyOnWrite();
                ((ScreenCompatPackage) this.instance).setPackage(value);
                return this;
            }

            public Builder clearPackage() {
                copyOnWrite();
                ((ScreenCompatPackage) this.instance).clearPackage();
                return this;
            }

            public Builder setPackageBytes(ByteString value) {
                copyOnWrite();
                ((ScreenCompatPackage) this.instance).setPackageBytes(value);
                return this;
            }

            @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.ScreenCompatPackageOrBuilder
            public boolean hasMode() {
                return ((ScreenCompatPackage) this.instance).hasMode();
            }

            @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.ScreenCompatPackageOrBuilder
            public int getMode() {
                return ((ScreenCompatPackage) this.instance).getMode();
            }

            public Builder setMode(int value) {
                copyOnWrite();
                ((ScreenCompatPackage) this.instance).setMode(value);
                return this;
            }

            public Builder clearMode() {
                copyOnWrite();
                ((ScreenCompatPackage) this.instance).clearMode();
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new ScreenCompatPackage();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    ScreenCompatPackage other = (ScreenCompatPackage) arg1;
                    this.package_ = visitor.visitString(hasPackage(), this.package_, other.hasPackage(), other.package_);
                    this.mode_ = visitor.visitInt(hasMode(), this.mode_, other.hasMode(), other.mode_);
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
                            } else if (tag == 10) {
                                String s = input.readString();
                                this.bitField0_ |= 1;
                                this.package_ = s;
                            } else if (tag == 16) {
                                this.bitField0_ |= 2;
                                this.mode_ = input.readInt32();
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
                        synchronized (ScreenCompatPackage.class) {
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

        public static ScreenCompatPackage getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<ScreenCompatPackage> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class UidObserverRegistrationProto extends GeneratedMessageLite<UidObserverRegistrationProto, Builder> implements UidObserverRegistrationProtoOrBuilder {
        public static final int CUT_POINT_FIELD_NUMBER = 4;
        private static final UidObserverRegistrationProto DEFAULT_INSTANCE = new UidObserverRegistrationProto();
        public static final int FLAGS_FIELD_NUMBER = 3;
        public static final int LAST_PROC_STATES_FIELD_NUMBER = 5;
        public static final int PACKAGE_FIELD_NUMBER = 2;
        private static volatile Parser<UidObserverRegistrationProto> PARSER = null;
        public static final int UID_FIELD_NUMBER = 1;
        private static final Internal.ListAdapter.Converter<Integer, UidObserverFlag> flags_converter_ = new Internal.ListAdapter.Converter<Integer, UidObserverFlag>() {
            /* class com.android.server.am.ActivityManagerServiceDumpProcessesProto.UidObserverRegistrationProto.AnonymousClass1 */

            public UidObserverFlag convert(Integer from) {
                UidObserverFlag result = UidObserverFlag.forNumber(from.intValue());
                return result == null ? UidObserverFlag.UID_OBSERVER_FLAG_PROCSTATE : result;
            }
        };
        private int bitField0_;
        private int cutPoint_ = 0;
        private Internal.IntList flags_ = emptyIntList();
        private Internal.ProtobufList<ProcState> lastProcStates_ = emptyProtobufList();
        private String package_ = "";
        private int uid_ = 0;

        public interface ProcStateOrBuilder extends MessageLiteOrBuilder {
            int getState();

            int getUid();

            boolean hasState();

            boolean hasUid();
        }

        private UidObserverRegistrationProto() {
        }

        public static final class ProcState extends GeneratedMessageLite<ProcState, Builder> implements ProcStateOrBuilder {
            private static final ProcState DEFAULT_INSTANCE = new ProcState();
            private static volatile Parser<ProcState> PARSER = null;
            public static final int STATE_FIELD_NUMBER = 2;
            public static final int UID_FIELD_NUMBER = 1;
            private int bitField0_;
            private int state_ = 0;
            private int uid_ = 0;

            private ProcState() {
            }

            @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.UidObserverRegistrationProto.ProcStateOrBuilder
            public boolean hasUid() {
                return (this.bitField0_ & 1) == 1;
            }

            @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.UidObserverRegistrationProto.ProcStateOrBuilder
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

            @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.UidObserverRegistrationProto.ProcStateOrBuilder
            public boolean hasState() {
                return (this.bitField0_ & 2) == 2;
            }

            @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.UidObserverRegistrationProto.ProcStateOrBuilder
            public int getState() {
                return this.state_;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void setState(int value) {
                this.bitField0_ |= 2;
                this.state_ = value;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void clearState() {
                this.bitField0_ &= -3;
                this.state_ = 0;
            }

            @Override // com.google.protobuf.MessageLite
            public void writeTo(CodedOutputStream output) throws IOException {
                if ((this.bitField0_ & 1) == 1) {
                    output.writeInt32(1, this.uid_);
                }
                if ((this.bitField0_ & 2) == 2) {
                    output.writeInt32(2, this.state_);
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
                    size2 += CodedOutputStream.computeInt32Size(2, this.state_);
                }
                int size3 = size2 + this.unknownFields.getSerializedSize();
                this.memoizedSerializedSize = size3;
                return size3;
            }

            public static ProcState parseFrom(ByteString data) throws InvalidProtocolBufferException {
                return (ProcState) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
            }

            public static ProcState parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return (ProcState) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
            }

            public static ProcState parseFrom(byte[] data) throws InvalidProtocolBufferException {
                return (ProcState) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
            }

            public static ProcState parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return (ProcState) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
            }

            public static ProcState parseFrom(InputStream input) throws IOException {
                return (ProcState) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
            }

            public static ProcState parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (ProcState) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static ProcState parseDelimitedFrom(InputStream input) throws IOException {
                return (ProcState) parseDelimitedFrom(DEFAULT_INSTANCE, input);
            }

            public static ProcState parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (ProcState) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static ProcState parseFrom(CodedInputStream input) throws IOException {
                return (ProcState) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
            }

            public static ProcState parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (ProcState) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static Builder newBuilder() {
                return (Builder) DEFAULT_INSTANCE.toBuilder();
            }

            public static Builder newBuilder(ProcState prototype) {
                return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
            }

            public static final class Builder extends GeneratedMessageLite.Builder<ProcState, Builder> implements ProcStateOrBuilder {
                private Builder() {
                    super(ProcState.DEFAULT_INSTANCE);
                }

                @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.UidObserverRegistrationProto.ProcStateOrBuilder
                public boolean hasUid() {
                    return ((ProcState) this.instance).hasUid();
                }

                @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.UidObserverRegistrationProto.ProcStateOrBuilder
                public int getUid() {
                    return ((ProcState) this.instance).getUid();
                }

                public Builder setUid(int value) {
                    copyOnWrite();
                    ((ProcState) this.instance).setUid(value);
                    return this;
                }

                public Builder clearUid() {
                    copyOnWrite();
                    ((ProcState) this.instance).clearUid();
                    return this;
                }

                @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.UidObserverRegistrationProto.ProcStateOrBuilder
                public boolean hasState() {
                    return ((ProcState) this.instance).hasState();
                }

                @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.UidObserverRegistrationProto.ProcStateOrBuilder
                public int getState() {
                    return ((ProcState) this.instance).getState();
                }

                public Builder setState(int value) {
                    copyOnWrite();
                    ((ProcState) this.instance).setState(value);
                    return this;
                }

                public Builder clearState() {
                    copyOnWrite();
                    ((ProcState) this.instance).clearState();
                    return this;
                }
            }

            /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
            /* access modifiers changed from: protected */
            @Override // com.google.protobuf.GeneratedMessageLite
            public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
                switch (method) {
                    case NEW_MUTABLE_INSTANCE:
                        return new ProcState();
                    case IS_INITIALIZED:
                        return DEFAULT_INSTANCE;
                    case MAKE_IMMUTABLE:
                        return null;
                    case NEW_BUILDER:
                        return new Builder();
                    case VISIT:
                        GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                        ProcState other = (ProcState) arg1;
                        this.uid_ = visitor.visitInt(hasUid(), this.uid_, other.hasUid(), other.uid_);
                        this.state_ = visitor.visitInt(hasState(), this.state_, other.hasState(), other.state_);
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
                                    this.state_ = input.readInt32();
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
                            synchronized (ProcState.class) {
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

            public static ProcState getDefaultInstance() {
                return DEFAULT_INSTANCE;
            }

            public static Parser<ProcState> parser() {
                return DEFAULT_INSTANCE.getParserForType();
            }
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.UidObserverRegistrationProtoOrBuilder
        public boolean hasUid() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.UidObserverRegistrationProtoOrBuilder
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

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.UidObserverRegistrationProtoOrBuilder
        public boolean hasPackage() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.UidObserverRegistrationProtoOrBuilder
        public String getPackage() {
            return this.package_;
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.UidObserverRegistrationProtoOrBuilder
        public ByteString getPackageBytes() {
            return ByteString.copyFromUtf8(this.package_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setPackage(String value) {
            if (value != null) {
                this.bitField0_ |= 2;
                this.package_ = value;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearPackage() {
            this.bitField0_ &= -3;
            this.package_ = getDefaultInstance().getPackage();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setPackageBytes(ByteString value) {
            if (value != null) {
                this.bitField0_ |= 2;
                this.package_ = value.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.UidObserverRegistrationProtoOrBuilder
        public List<UidObserverFlag> getFlagsList() {
            return new Internal.ListAdapter(this.flags_, flags_converter_);
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.UidObserverRegistrationProtoOrBuilder
        public int getFlagsCount() {
            return this.flags_.size();
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.UidObserverRegistrationProtoOrBuilder
        public UidObserverFlag getFlags(int index) {
            return flags_converter_.convert(Integer.valueOf(this.flags_.getInt(index)));
        }

        private void ensureFlagsIsMutable() {
            if (!this.flags_.isModifiable()) {
                this.flags_ = GeneratedMessageLite.mutableCopy(this.flags_);
            }
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setFlags(int index, UidObserverFlag value) {
            if (value != null) {
                ensureFlagsIsMutable();
                this.flags_.setInt(index, value.getNumber());
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addFlags(UidObserverFlag value) {
            if (value != null) {
                ensureFlagsIsMutable();
                this.flags_.addInt(value.getNumber());
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addAllFlags(Iterable<? extends UidObserverFlag> values) {
            ensureFlagsIsMutable();
            for (UidObserverFlag value : values) {
                this.flags_.addInt(value.getNumber());
            }
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearFlags() {
            this.flags_ = emptyIntList();
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.UidObserverRegistrationProtoOrBuilder
        public boolean hasCutPoint() {
            return (this.bitField0_ & 4) == 4;
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.UidObserverRegistrationProtoOrBuilder
        public int getCutPoint() {
            return this.cutPoint_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setCutPoint(int value) {
            this.bitField0_ |= 4;
            this.cutPoint_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearCutPoint() {
            this.bitField0_ &= -5;
            this.cutPoint_ = 0;
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.UidObserverRegistrationProtoOrBuilder
        public List<ProcState> getLastProcStatesList() {
            return this.lastProcStates_;
        }

        public List<? extends ProcStateOrBuilder> getLastProcStatesOrBuilderList() {
            return this.lastProcStates_;
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.UidObserverRegistrationProtoOrBuilder
        public int getLastProcStatesCount() {
            return this.lastProcStates_.size();
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.UidObserverRegistrationProtoOrBuilder
        public ProcState getLastProcStates(int index) {
            return this.lastProcStates_.get(index);
        }

        public ProcStateOrBuilder getLastProcStatesOrBuilder(int index) {
            return this.lastProcStates_.get(index);
        }

        private void ensureLastProcStatesIsMutable() {
            if (!this.lastProcStates_.isModifiable()) {
                this.lastProcStates_ = GeneratedMessageLite.mutableCopy(this.lastProcStates_);
            }
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setLastProcStates(int index, ProcState value) {
            if (value != null) {
                ensureLastProcStatesIsMutable();
                this.lastProcStates_.set(index, value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setLastProcStates(int index, ProcState.Builder builderForValue) {
            ensureLastProcStatesIsMutable();
            this.lastProcStates_.set(index, (ProcState) builderForValue.build());
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addLastProcStates(ProcState value) {
            if (value != null) {
                ensureLastProcStatesIsMutable();
                this.lastProcStates_.add(value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addLastProcStates(int index, ProcState value) {
            if (value != null) {
                ensureLastProcStatesIsMutable();
                this.lastProcStates_.add(index, value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addLastProcStates(ProcState.Builder builderForValue) {
            ensureLastProcStatesIsMutable();
            this.lastProcStates_.add((ProcState) builderForValue.build());
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addLastProcStates(int index, ProcState.Builder builderForValue) {
            ensureLastProcStatesIsMutable();
            this.lastProcStates_.add(index, (ProcState) builderForValue.build());
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addAllLastProcStates(Iterable<? extends ProcState> values) {
            ensureLastProcStatesIsMutable();
            AbstractMessageLite.addAll(values, this.lastProcStates_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearLastProcStates() {
            this.lastProcStates_ = emptyProtobufList();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void removeLastProcStates(int index) {
            ensureLastProcStatesIsMutable();
            this.lastProcStates_.remove(index);
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                output.writeInt32(1, this.uid_);
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeString(2, getPackage());
            }
            for (int i = 0; i < this.flags_.size(); i++) {
                output.writeEnum(3, this.flags_.getInt(i));
            }
            if ((this.bitField0_ & 4) == 4) {
                output.writeInt32(4, this.cutPoint_);
            }
            for (int i2 = 0; i2 < this.lastProcStates_.size(); i2++) {
                output.writeMessage(5, this.lastProcStates_.get(i2));
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
                size2 += CodedOutputStream.computeStringSize(2, getPackage());
            }
            int dataSize = 0;
            for (int i = 0; i < this.flags_.size(); i++) {
                dataSize += CodedOutputStream.computeEnumSizeNoTag(this.flags_.getInt(i));
            }
            int size3 = size2 + dataSize + (this.flags_.size() * 1);
            if ((this.bitField0_ & 4) == 4) {
                size3 += CodedOutputStream.computeInt32Size(4, this.cutPoint_);
            }
            for (int i2 = 0; i2 < this.lastProcStates_.size(); i2++) {
                size3 += CodedOutputStream.computeMessageSize(5, this.lastProcStates_.get(i2));
            }
            int size4 = size3 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size4;
            return size4;
        }

        public static UidObserverRegistrationProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (UidObserverRegistrationProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static UidObserverRegistrationProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (UidObserverRegistrationProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static UidObserverRegistrationProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (UidObserverRegistrationProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static UidObserverRegistrationProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (UidObserverRegistrationProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static UidObserverRegistrationProto parseFrom(InputStream input) throws IOException {
            return (UidObserverRegistrationProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static UidObserverRegistrationProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (UidObserverRegistrationProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static UidObserverRegistrationProto parseDelimitedFrom(InputStream input) throws IOException {
            return (UidObserverRegistrationProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static UidObserverRegistrationProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (UidObserverRegistrationProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static UidObserverRegistrationProto parseFrom(CodedInputStream input) throws IOException {
            return (UidObserverRegistrationProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static UidObserverRegistrationProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (UidObserverRegistrationProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(UidObserverRegistrationProto prototype) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<UidObserverRegistrationProto, Builder> implements UidObserverRegistrationProtoOrBuilder {
            private Builder() {
                super(UidObserverRegistrationProto.DEFAULT_INSTANCE);
            }

            @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.UidObserverRegistrationProtoOrBuilder
            public boolean hasUid() {
                return ((UidObserverRegistrationProto) this.instance).hasUid();
            }

            @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.UidObserverRegistrationProtoOrBuilder
            public int getUid() {
                return ((UidObserverRegistrationProto) this.instance).getUid();
            }

            public Builder setUid(int value) {
                copyOnWrite();
                ((UidObserverRegistrationProto) this.instance).setUid(value);
                return this;
            }

            public Builder clearUid() {
                copyOnWrite();
                ((UidObserverRegistrationProto) this.instance).clearUid();
                return this;
            }

            @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.UidObserverRegistrationProtoOrBuilder
            public boolean hasPackage() {
                return ((UidObserverRegistrationProto) this.instance).hasPackage();
            }

            @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.UidObserverRegistrationProtoOrBuilder
            public String getPackage() {
                return ((UidObserverRegistrationProto) this.instance).getPackage();
            }

            @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.UidObserverRegistrationProtoOrBuilder
            public ByteString getPackageBytes() {
                return ((UidObserverRegistrationProto) this.instance).getPackageBytes();
            }

            public Builder setPackage(String value) {
                copyOnWrite();
                ((UidObserverRegistrationProto) this.instance).setPackage(value);
                return this;
            }

            public Builder clearPackage() {
                copyOnWrite();
                ((UidObserverRegistrationProto) this.instance).clearPackage();
                return this;
            }

            public Builder setPackageBytes(ByteString value) {
                copyOnWrite();
                ((UidObserverRegistrationProto) this.instance).setPackageBytes(value);
                return this;
            }

            @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.UidObserverRegistrationProtoOrBuilder
            public List<UidObserverFlag> getFlagsList() {
                return ((UidObserverRegistrationProto) this.instance).getFlagsList();
            }

            @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.UidObserverRegistrationProtoOrBuilder
            public int getFlagsCount() {
                return ((UidObserverRegistrationProto) this.instance).getFlagsCount();
            }

            @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.UidObserverRegistrationProtoOrBuilder
            public UidObserverFlag getFlags(int index) {
                return ((UidObserverRegistrationProto) this.instance).getFlags(index);
            }

            public Builder setFlags(int index, UidObserverFlag value) {
                copyOnWrite();
                ((UidObserverRegistrationProto) this.instance).setFlags(index, value);
                return this;
            }

            public Builder addFlags(UidObserverFlag value) {
                copyOnWrite();
                ((UidObserverRegistrationProto) this.instance).addFlags(value);
                return this;
            }

            public Builder addAllFlags(Iterable<? extends UidObserverFlag> values) {
                copyOnWrite();
                ((UidObserverRegistrationProto) this.instance).addAllFlags(values);
                return this;
            }

            public Builder clearFlags() {
                copyOnWrite();
                ((UidObserverRegistrationProto) this.instance).clearFlags();
                return this;
            }

            @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.UidObserverRegistrationProtoOrBuilder
            public boolean hasCutPoint() {
                return ((UidObserverRegistrationProto) this.instance).hasCutPoint();
            }

            @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.UidObserverRegistrationProtoOrBuilder
            public int getCutPoint() {
                return ((UidObserverRegistrationProto) this.instance).getCutPoint();
            }

            public Builder setCutPoint(int value) {
                copyOnWrite();
                ((UidObserverRegistrationProto) this.instance).setCutPoint(value);
                return this;
            }

            public Builder clearCutPoint() {
                copyOnWrite();
                ((UidObserverRegistrationProto) this.instance).clearCutPoint();
                return this;
            }

            @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.UidObserverRegistrationProtoOrBuilder
            public List<ProcState> getLastProcStatesList() {
                return Collections.unmodifiableList(((UidObserverRegistrationProto) this.instance).getLastProcStatesList());
            }

            @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.UidObserverRegistrationProtoOrBuilder
            public int getLastProcStatesCount() {
                return ((UidObserverRegistrationProto) this.instance).getLastProcStatesCount();
            }

            @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.UidObserverRegistrationProtoOrBuilder
            public ProcState getLastProcStates(int index) {
                return ((UidObserverRegistrationProto) this.instance).getLastProcStates(index);
            }

            public Builder setLastProcStates(int index, ProcState value) {
                copyOnWrite();
                ((UidObserverRegistrationProto) this.instance).setLastProcStates((UidObserverRegistrationProto) index, (int) value);
                return this;
            }

            public Builder setLastProcStates(int index, ProcState.Builder builderForValue) {
                copyOnWrite();
                ((UidObserverRegistrationProto) this.instance).setLastProcStates((UidObserverRegistrationProto) index, (int) builderForValue);
                return this;
            }

            public Builder addLastProcStates(ProcState value) {
                copyOnWrite();
                ((UidObserverRegistrationProto) this.instance).addLastProcStates((UidObserverRegistrationProto) value);
                return this;
            }

            public Builder addLastProcStates(int index, ProcState value) {
                copyOnWrite();
                ((UidObserverRegistrationProto) this.instance).addLastProcStates((UidObserverRegistrationProto) index, (int) value);
                return this;
            }

            public Builder addLastProcStates(ProcState.Builder builderForValue) {
                copyOnWrite();
                ((UidObserverRegistrationProto) this.instance).addLastProcStates((UidObserverRegistrationProto) builderForValue);
                return this;
            }

            public Builder addLastProcStates(int index, ProcState.Builder builderForValue) {
                copyOnWrite();
                ((UidObserverRegistrationProto) this.instance).addLastProcStates((UidObserverRegistrationProto) index, (int) builderForValue);
                return this;
            }

            public Builder addAllLastProcStates(Iterable<? extends ProcState> values) {
                copyOnWrite();
                ((UidObserverRegistrationProto) this.instance).addAllLastProcStates(values);
                return this;
            }

            public Builder clearLastProcStates() {
                copyOnWrite();
                ((UidObserverRegistrationProto) this.instance).clearLastProcStates();
                return this;
            }

            public Builder removeLastProcStates(int index) {
                copyOnWrite();
                ((UidObserverRegistrationProto) this.instance).removeLastProcStates(index);
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new UidObserverRegistrationProto();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    this.flags_.makeImmutable();
                    this.lastProcStates_.makeImmutable();
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    UidObserverRegistrationProto other = (UidObserverRegistrationProto) arg1;
                    this.uid_ = visitor.visitInt(hasUid(), this.uid_, other.hasUid(), other.uid_);
                    this.package_ = visitor.visitString(hasPackage(), this.package_, other.hasPackage(), other.package_);
                    this.flags_ = visitor.visitIntList(this.flags_, other.flags_);
                    this.cutPoint_ = visitor.visitInt(hasCutPoint(), this.cutPoint_, other.hasCutPoint(), other.cutPoint_);
                    this.lastProcStates_ = visitor.visitList(this.lastProcStates_, other.lastProcStates_);
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
                                this.package_ = s;
                            } else if (tag == 24) {
                                if (!this.flags_.isModifiable()) {
                                    this.flags_ = GeneratedMessageLite.mutableCopy(this.flags_);
                                }
                                int rawValue = input.readEnum();
                                if (UidObserverFlag.forNumber(rawValue) == null) {
                                    super.mergeVarintField(3, rawValue);
                                } else {
                                    this.flags_.addInt(rawValue);
                                }
                            } else if (tag == 26) {
                                if (!this.flags_.isModifiable()) {
                                    this.flags_ = GeneratedMessageLite.mutableCopy(this.flags_);
                                }
                                int oldLimit = input.pushLimit(input.readRawVarint32());
                                while (input.getBytesUntilLimit() > 0) {
                                    int rawValue2 = input.readEnum();
                                    if (UidObserverFlag.forNumber(rawValue2) == null) {
                                        super.mergeVarintField(3, rawValue2);
                                    } else {
                                        this.flags_.addInt(rawValue2);
                                    }
                                }
                                input.popLimit(oldLimit);
                            } else if (tag == 32) {
                                this.bitField0_ |= 4;
                                this.cutPoint_ = input.readInt32();
                            } else if (tag == 42) {
                                if (!this.lastProcStates_.isModifiable()) {
                                    this.lastProcStates_ = GeneratedMessageLite.mutableCopy(this.lastProcStates_);
                                }
                                this.lastProcStates_.add((ProcState) input.readMessage(ProcState.parser(), extensionRegistry));
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
                        synchronized (UidObserverRegistrationProto.class) {
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

        public static UidObserverRegistrationProto getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<UidObserverRegistrationProto> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class PendingTempWhitelist extends GeneratedMessageLite<PendingTempWhitelist, Builder> implements PendingTempWhitelistOrBuilder {
        private static final PendingTempWhitelist DEFAULT_INSTANCE = new PendingTempWhitelist();
        public static final int DURATION_MS_FIELD_NUMBER = 2;
        private static volatile Parser<PendingTempWhitelist> PARSER = null;
        public static final int TAG_FIELD_NUMBER = 3;
        public static final int TARGET_UID_FIELD_NUMBER = 1;
        private int bitField0_;
        private long durationMs_ = 0;
        private String tag_ = "";
        private int targetUid_ = 0;

        private PendingTempWhitelist() {
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.PendingTempWhitelistOrBuilder
        public boolean hasTargetUid() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.PendingTempWhitelistOrBuilder
        public int getTargetUid() {
            return this.targetUid_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setTargetUid(int value) {
            this.bitField0_ |= 1;
            this.targetUid_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearTargetUid() {
            this.bitField0_ &= -2;
            this.targetUid_ = 0;
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.PendingTempWhitelistOrBuilder
        public boolean hasDurationMs() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.PendingTempWhitelistOrBuilder
        public long getDurationMs() {
            return this.durationMs_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setDurationMs(long value) {
            this.bitField0_ |= 2;
            this.durationMs_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearDurationMs() {
            this.bitField0_ &= -3;
            this.durationMs_ = 0;
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.PendingTempWhitelistOrBuilder
        public boolean hasTag() {
            return (this.bitField0_ & 4) == 4;
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.PendingTempWhitelistOrBuilder
        public String getTag() {
            return this.tag_;
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.PendingTempWhitelistOrBuilder
        public ByteString getTagBytes() {
            return ByteString.copyFromUtf8(this.tag_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setTag(String value) {
            if (value != null) {
                this.bitField0_ |= 4;
                this.tag_ = value;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearTag() {
            this.bitField0_ &= -5;
            this.tag_ = getDefaultInstance().getTag();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setTagBytes(ByteString value) {
            if (value != null) {
                this.bitField0_ |= 4;
                this.tag_ = value.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                output.writeInt32(1, this.targetUid_);
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeInt64(2, this.durationMs_);
            }
            if ((this.bitField0_ & 4) == 4) {
                output.writeString(3, getTag());
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
                size2 = 0 + CodedOutputStream.computeInt32Size(1, this.targetUid_);
            }
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeInt64Size(2, this.durationMs_);
            }
            if ((this.bitField0_ & 4) == 4) {
                size2 += CodedOutputStream.computeStringSize(3, getTag());
            }
            int size3 = size2 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static PendingTempWhitelist parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (PendingTempWhitelist) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static PendingTempWhitelist parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (PendingTempWhitelist) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static PendingTempWhitelist parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (PendingTempWhitelist) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static PendingTempWhitelist parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (PendingTempWhitelist) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static PendingTempWhitelist parseFrom(InputStream input) throws IOException {
            return (PendingTempWhitelist) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static PendingTempWhitelist parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (PendingTempWhitelist) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static PendingTempWhitelist parseDelimitedFrom(InputStream input) throws IOException {
            return (PendingTempWhitelist) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static PendingTempWhitelist parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (PendingTempWhitelist) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static PendingTempWhitelist parseFrom(CodedInputStream input) throws IOException {
            return (PendingTempWhitelist) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static PendingTempWhitelist parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (PendingTempWhitelist) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(PendingTempWhitelist prototype) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<PendingTempWhitelist, Builder> implements PendingTempWhitelistOrBuilder {
            private Builder() {
                super(PendingTempWhitelist.DEFAULT_INSTANCE);
            }

            @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.PendingTempWhitelistOrBuilder
            public boolean hasTargetUid() {
                return ((PendingTempWhitelist) this.instance).hasTargetUid();
            }

            @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.PendingTempWhitelistOrBuilder
            public int getTargetUid() {
                return ((PendingTempWhitelist) this.instance).getTargetUid();
            }

            public Builder setTargetUid(int value) {
                copyOnWrite();
                ((PendingTempWhitelist) this.instance).setTargetUid(value);
                return this;
            }

            public Builder clearTargetUid() {
                copyOnWrite();
                ((PendingTempWhitelist) this.instance).clearTargetUid();
                return this;
            }

            @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.PendingTempWhitelistOrBuilder
            public boolean hasDurationMs() {
                return ((PendingTempWhitelist) this.instance).hasDurationMs();
            }

            @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.PendingTempWhitelistOrBuilder
            public long getDurationMs() {
                return ((PendingTempWhitelist) this.instance).getDurationMs();
            }

            public Builder setDurationMs(long value) {
                copyOnWrite();
                ((PendingTempWhitelist) this.instance).setDurationMs(value);
                return this;
            }

            public Builder clearDurationMs() {
                copyOnWrite();
                ((PendingTempWhitelist) this.instance).clearDurationMs();
                return this;
            }

            @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.PendingTempWhitelistOrBuilder
            public boolean hasTag() {
                return ((PendingTempWhitelist) this.instance).hasTag();
            }

            @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.PendingTempWhitelistOrBuilder
            public String getTag() {
                return ((PendingTempWhitelist) this.instance).getTag();
            }

            @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.PendingTempWhitelistOrBuilder
            public ByteString getTagBytes() {
                return ((PendingTempWhitelist) this.instance).getTagBytes();
            }

            public Builder setTag(String value) {
                copyOnWrite();
                ((PendingTempWhitelist) this.instance).setTag(value);
                return this;
            }

            public Builder clearTag() {
                copyOnWrite();
                ((PendingTempWhitelist) this.instance).clearTag();
                return this;
            }

            public Builder setTagBytes(ByteString value) {
                copyOnWrite();
                ((PendingTempWhitelist) this.instance).setTagBytes(value);
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new PendingTempWhitelist();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    PendingTempWhitelist other = (PendingTempWhitelist) arg1;
                    this.targetUid_ = visitor.visitInt(hasTargetUid(), this.targetUid_, other.hasTargetUid(), other.targetUid_);
                    this.durationMs_ = visitor.visitLong(hasDurationMs(), this.durationMs_, other.hasDurationMs(), other.durationMs_);
                    this.tag_ = visitor.visitString(hasTag(), this.tag_, other.hasTag(), other.tag_);
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
                                this.targetUid_ = input.readInt32();
                            } else if (tag == 16) {
                                this.bitField0_ |= 2;
                                this.durationMs_ = input.readInt64();
                            } else if (tag == 26) {
                                String s = input.readString();
                                this.bitField0_ |= 4;
                                this.tag_ = s;
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
                        synchronized (PendingTempWhitelist.class) {
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

        public static PendingTempWhitelist getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<PendingTempWhitelist> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class SleepStatus extends GeneratedMessageLite<SleepStatus, Builder> implements SleepStatusOrBuilder {
        private static final SleepStatus DEFAULT_INSTANCE = new SleepStatus();
        private static volatile Parser<SleepStatus> PARSER = null;
        public static final int SHUTTING_DOWN_FIELD_NUMBER = 4;
        public static final int SLEEPING_FIELD_NUMBER = 3;
        public static final int SLEEP_TOKENS_FIELD_NUMBER = 2;
        public static final int TEST_PSS_MODE_FIELD_NUMBER = 5;
        public static final int WAKEFULNESS_FIELD_NUMBER = 1;
        private int bitField0_;
        private boolean shuttingDown_ = false;
        private Internal.ProtobufList<String> sleepTokens_ = GeneratedMessageLite.emptyProtobufList();
        private boolean sleeping_ = false;
        private boolean testPssMode_ = false;
        private int wakefulness_ = 0;

        private SleepStatus() {
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.SleepStatusOrBuilder
        public boolean hasWakefulness() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.SleepStatusOrBuilder
        public PowerManagerInternalProto.Wakefulness getWakefulness() {
            PowerManagerInternalProto.Wakefulness result = PowerManagerInternalProto.Wakefulness.forNumber(this.wakefulness_);
            return result == null ? PowerManagerInternalProto.Wakefulness.WAKEFULNESS_ASLEEP : result;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setWakefulness(PowerManagerInternalProto.Wakefulness value) {
            if (value != null) {
                this.bitField0_ |= 1;
                this.wakefulness_ = value.getNumber();
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearWakefulness() {
            this.bitField0_ &= -2;
            this.wakefulness_ = 0;
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.SleepStatusOrBuilder
        public List<String> getSleepTokensList() {
            return this.sleepTokens_;
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.SleepStatusOrBuilder
        public int getSleepTokensCount() {
            return this.sleepTokens_.size();
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.SleepStatusOrBuilder
        public String getSleepTokens(int index) {
            return this.sleepTokens_.get(index);
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.SleepStatusOrBuilder
        public ByteString getSleepTokensBytes(int index) {
            return ByteString.copyFromUtf8(this.sleepTokens_.get(index));
        }

        private void ensureSleepTokensIsMutable() {
            if (!this.sleepTokens_.isModifiable()) {
                this.sleepTokens_ = GeneratedMessageLite.mutableCopy(this.sleepTokens_);
            }
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setSleepTokens(int index, String value) {
            if (value != null) {
                ensureSleepTokensIsMutable();
                this.sleepTokens_.set(index, value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addSleepTokens(String value) {
            if (value != null) {
                ensureSleepTokensIsMutable();
                this.sleepTokens_.add(value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addAllSleepTokens(Iterable<String> values) {
            ensureSleepTokensIsMutable();
            AbstractMessageLite.addAll(values, this.sleepTokens_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearSleepTokens() {
            this.sleepTokens_ = GeneratedMessageLite.emptyProtobufList();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addSleepTokensBytes(ByteString value) {
            if (value != null) {
                ensureSleepTokensIsMutable();
                this.sleepTokens_.add(value.toStringUtf8());
                return;
            }
            throw new NullPointerException();
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.SleepStatusOrBuilder
        public boolean hasSleeping() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.SleepStatusOrBuilder
        public boolean getSleeping() {
            return this.sleeping_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setSleeping(boolean value) {
            this.bitField0_ |= 2;
            this.sleeping_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearSleeping() {
            this.bitField0_ &= -3;
            this.sleeping_ = false;
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.SleepStatusOrBuilder
        public boolean hasShuttingDown() {
            return (this.bitField0_ & 4) == 4;
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.SleepStatusOrBuilder
        public boolean getShuttingDown() {
            return this.shuttingDown_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setShuttingDown(boolean value) {
            this.bitField0_ |= 4;
            this.shuttingDown_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearShuttingDown() {
            this.bitField0_ &= -5;
            this.shuttingDown_ = false;
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.SleepStatusOrBuilder
        public boolean hasTestPssMode() {
            return (this.bitField0_ & 8) == 8;
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.SleepStatusOrBuilder
        public boolean getTestPssMode() {
            return this.testPssMode_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setTestPssMode(boolean value) {
            this.bitField0_ |= 8;
            this.testPssMode_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearTestPssMode() {
            this.bitField0_ &= -9;
            this.testPssMode_ = false;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                output.writeEnum(1, this.wakefulness_);
            }
            for (int i = 0; i < this.sleepTokens_.size(); i++) {
                output.writeString(2, this.sleepTokens_.get(i));
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeBool(3, this.sleeping_);
            }
            if ((this.bitField0_ & 4) == 4) {
                output.writeBool(4, this.shuttingDown_);
            }
            if ((this.bitField0_ & 8) == 8) {
                output.writeBool(5, this.testPssMode_);
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
                size2 = 0 + CodedOutputStream.computeEnumSize(1, this.wakefulness_);
            }
            int dataSize = 0;
            for (int i = 0; i < this.sleepTokens_.size(); i++) {
                dataSize += CodedOutputStream.computeStringSizeNoTag(this.sleepTokens_.get(i));
            }
            int size3 = size2 + dataSize + (getSleepTokensList().size() * 1);
            if ((this.bitField0_ & 2) == 2) {
                size3 += CodedOutputStream.computeBoolSize(3, this.sleeping_);
            }
            if ((this.bitField0_ & 4) == 4) {
                size3 += CodedOutputStream.computeBoolSize(4, this.shuttingDown_);
            }
            if ((this.bitField0_ & 8) == 8) {
                size3 += CodedOutputStream.computeBoolSize(5, this.testPssMode_);
            }
            int size4 = size3 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size4;
            return size4;
        }

        public static SleepStatus parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (SleepStatus) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static SleepStatus parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (SleepStatus) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static SleepStatus parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (SleepStatus) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static SleepStatus parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (SleepStatus) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static SleepStatus parseFrom(InputStream input) throws IOException {
            return (SleepStatus) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static SleepStatus parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (SleepStatus) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static SleepStatus parseDelimitedFrom(InputStream input) throws IOException {
            return (SleepStatus) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static SleepStatus parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (SleepStatus) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static SleepStatus parseFrom(CodedInputStream input) throws IOException {
            return (SleepStatus) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static SleepStatus parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (SleepStatus) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(SleepStatus prototype) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<SleepStatus, Builder> implements SleepStatusOrBuilder {
            private Builder() {
                super(SleepStatus.DEFAULT_INSTANCE);
            }

            @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.SleepStatusOrBuilder
            public boolean hasWakefulness() {
                return ((SleepStatus) this.instance).hasWakefulness();
            }

            @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.SleepStatusOrBuilder
            public PowerManagerInternalProto.Wakefulness getWakefulness() {
                return ((SleepStatus) this.instance).getWakefulness();
            }

            public Builder setWakefulness(PowerManagerInternalProto.Wakefulness value) {
                copyOnWrite();
                ((SleepStatus) this.instance).setWakefulness(value);
                return this;
            }

            public Builder clearWakefulness() {
                copyOnWrite();
                ((SleepStatus) this.instance).clearWakefulness();
                return this;
            }

            @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.SleepStatusOrBuilder
            public List<String> getSleepTokensList() {
                return Collections.unmodifiableList(((SleepStatus) this.instance).getSleepTokensList());
            }

            @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.SleepStatusOrBuilder
            public int getSleepTokensCount() {
                return ((SleepStatus) this.instance).getSleepTokensCount();
            }

            @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.SleepStatusOrBuilder
            public String getSleepTokens(int index) {
                return ((SleepStatus) this.instance).getSleepTokens(index);
            }

            @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.SleepStatusOrBuilder
            public ByteString getSleepTokensBytes(int index) {
                return ((SleepStatus) this.instance).getSleepTokensBytes(index);
            }

            public Builder setSleepTokens(int index, String value) {
                copyOnWrite();
                ((SleepStatus) this.instance).setSleepTokens(index, value);
                return this;
            }

            public Builder addSleepTokens(String value) {
                copyOnWrite();
                ((SleepStatus) this.instance).addSleepTokens(value);
                return this;
            }

            public Builder addAllSleepTokens(Iterable<String> values) {
                copyOnWrite();
                ((SleepStatus) this.instance).addAllSleepTokens(values);
                return this;
            }

            public Builder clearSleepTokens() {
                copyOnWrite();
                ((SleepStatus) this.instance).clearSleepTokens();
                return this;
            }

            public Builder addSleepTokensBytes(ByteString value) {
                copyOnWrite();
                ((SleepStatus) this.instance).addSleepTokensBytes(value);
                return this;
            }

            @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.SleepStatusOrBuilder
            public boolean hasSleeping() {
                return ((SleepStatus) this.instance).hasSleeping();
            }

            @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.SleepStatusOrBuilder
            public boolean getSleeping() {
                return ((SleepStatus) this.instance).getSleeping();
            }

            public Builder setSleeping(boolean value) {
                copyOnWrite();
                ((SleepStatus) this.instance).setSleeping(value);
                return this;
            }

            public Builder clearSleeping() {
                copyOnWrite();
                ((SleepStatus) this.instance).clearSleeping();
                return this;
            }

            @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.SleepStatusOrBuilder
            public boolean hasShuttingDown() {
                return ((SleepStatus) this.instance).hasShuttingDown();
            }

            @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.SleepStatusOrBuilder
            public boolean getShuttingDown() {
                return ((SleepStatus) this.instance).getShuttingDown();
            }

            public Builder setShuttingDown(boolean value) {
                copyOnWrite();
                ((SleepStatus) this.instance).setShuttingDown(value);
                return this;
            }

            public Builder clearShuttingDown() {
                copyOnWrite();
                ((SleepStatus) this.instance).clearShuttingDown();
                return this;
            }

            @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.SleepStatusOrBuilder
            public boolean hasTestPssMode() {
                return ((SleepStatus) this.instance).hasTestPssMode();
            }

            @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.SleepStatusOrBuilder
            public boolean getTestPssMode() {
                return ((SleepStatus) this.instance).getTestPssMode();
            }

            public Builder setTestPssMode(boolean value) {
                copyOnWrite();
                ((SleepStatus) this.instance).setTestPssMode(value);
                return this;
            }

            public Builder clearTestPssMode() {
                copyOnWrite();
                ((SleepStatus) this.instance).clearTestPssMode();
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new SleepStatus();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    this.sleepTokens_.makeImmutable();
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    SleepStatus other = (SleepStatus) arg1;
                    this.wakefulness_ = visitor.visitInt(hasWakefulness(), this.wakefulness_, other.hasWakefulness(), other.wakefulness_);
                    this.sleepTokens_ = visitor.visitList(this.sleepTokens_, other.sleepTokens_);
                    this.sleeping_ = visitor.visitBoolean(hasSleeping(), this.sleeping_, other.hasSleeping(), other.sleeping_);
                    this.shuttingDown_ = visitor.visitBoolean(hasShuttingDown(), this.shuttingDown_, other.hasShuttingDown(), other.shuttingDown_);
                    this.testPssMode_ = visitor.visitBoolean(hasTestPssMode(), this.testPssMode_, other.hasTestPssMode(), other.testPssMode_);
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
                                if (PowerManagerInternalProto.Wakefulness.forNumber(rawValue) == null) {
                                    super.mergeVarintField(1, rawValue);
                                } else {
                                    this.bitField0_ = 1 | this.bitField0_;
                                    this.wakefulness_ = rawValue;
                                }
                            } else if (tag == 18) {
                                String s = input.readString();
                                if (!this.sleepTokens_.isModifiable()) {
                                    this.sleepTokens_ = GeneratedMessageLite.mutableCopy(this.sleepTokens_);
                                }
                                this.sleepTokens_.add(s);
                            } else if (tag == 24) {
                                this.bitField0_ |= 2;
                                this.sleeping_ = input.readBool();
                            } else if (tag == 32) {
                                this.bitField0_ |= 4;
                                this.shuttingDown_ = input.readBool();
                            } else if (tag == 40) {
                                this.bitField0_ = 8 | this.bitField0_;
                                this.testPssMode_ = input.readBool();
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
                        synchronized (SleepStatus.class) {
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

        public static SleepStatus getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<SleepStatus> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class Voice extends GeneratedMessageLite<Voice, Builder> implements VoiceOrBuilder {
        private static final Voice DEFAULT_INSTANCE = new Voice();
        private static volatile Parser<Voice> PARSER = null;
        public static final int SESSION_FIELD_NUMBER = 1;
        public static final int WAKELOCK_FIELD_NUMBER = 2;
        private int bitField0_;
        private String session_ = "";
        private PowerManagerProto.WakeLock wakelock_;

        private Voice() {
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.VoiceOrBuilder
        public boolean hasSession() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.VoiceOrBuilder
        public String getSession() {
            return this.session_;
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.VoiceOrBuilder
        public ByteString getSessionBytes() {
            return ByteString.copyFromUtf8(this.session_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setSession(String value) {
            if (value != null) {
                this.bitField0_ |= 1;
                this.session_ = value;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearSession() {
            this.bitField0_ &= -2;
            this.session_ = getDefaultInstance().getSession();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setSessionBytes(ByteString value) {
            if (value != null) {
                this.bitField0_ |= 1;
                this.session_ = value.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.VoiceOrBuilder
        public boolean hasWakelock() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.VoiceOrBuilder
        public PowerManagerProto.WakeLock getWakelock() {
            PowerManagerProto.WakeLock wakeLock = this.wakelock_;
            return wakeLock == null ? PowerManagerProto.WakeLock.getDefaultInstance() : wakeLock;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setWakelock(PowerManagerProto.WakeLock value) {
            if (value != null) {
                this.wakelock_ = value;
                this.bitField0_ |= 2;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setWakelock(PowerManagerProto.WakeLock.Builder builderForValue) {
            this.wakelock_ = (PowerManagerProto.WakeLock) builderForValue.build();
            this.bitField0_ |= 2;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void mergeWakelock(PowerManagerProto.WakeLock value) {
            PowerManagerProto.WakeLock wakeLock = this.wakelock_;
            if (wakeLock == null || wakeLock == PowerManagerProto.WakeLock.getDefaultInstance()) {
                this.wakelock_ = value;
            } else {
                this.wakelock_ = (PowerManagerProto.WakeLock) ((PowerManagerProto.WakeLock.Builder) PowerManagerProto.WakeLock.newBuilder(this.wakelock_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
            }
            this.bitField0_ |= 2;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearWakelock() {
            this.wakelock_ = null;
            this.bitField0_ &= -3;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                output.writeString(1, getSession());
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeMessage(2, getWakelock());
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
                size2 = 0 + CodedOutputStream.computeStringSize(1, getSession());
            }
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeMessageSize(2, getWakelock());
            }
            int size3 = size2 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static Voice parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (Voice) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static Voice parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Voice) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static Voice parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (Voice) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static Voice parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Voice) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static Voice parseFrom(InputStream input) throws IOException {
            return (Voice) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static Voice parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Voice) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Voice parseDelimitedFrom(InputStream input) throws IOException {
            return (Voice) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static Voice parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Voice) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Voice parseFrom(CodedInputStream input) throws IOException {
            return (Voice) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static Voice parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Voice) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(Voice prototype) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<Voice, Builder> implements VoiceOrBuilder {
            private Builder() {
                super(Voice.DEFAULT_INSTANCE);
            }

            @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.VoiceOrBuilder
            public boolean hasSession() {
                return ((Voice) this.instance).hasSession();
            }

            @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.VoiceOrBuilder
            public String getSession() {
                return ((Voice) this.instance).getSession();
            }

            @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.VoiceOrBuilder
            public ByteString getSessionBytes() {
                return ((Voice) this.instance).getSessionBytes();
            }

            public Builder setSession(String value) {
                copyOnWrite();
                ((Voice) this.instance).setSession(value);
                return this;
            }

            public Builder clearSession() {
                copyOnWrite();
                ((Voice) this.instance).clearSession();
                return this;
            }

            public Builder setSessionBytes(ByteString value) {
                copyOnWrite();
                ((Voice) this.instance).setSessionBytes(value);
                return this;
            }

            @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.VoiceOrBuilder
            public boolean hasWakelock() {
                return ((Voice) this.instance).hasWakelock();
            }

            @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.VoiceOrBuilder
            public PowerManagerProto.WakeLock getWakelock() {
                return ((Voice) this.instance).getWakelock();
            }

            public Builder setWakelock(PowerManagerProto.WakeLock value) {
                copyOnWrite();
                ((Voice) this.instance).setWakelock((Voice) value);
                return this;
            }

            public Builder setWakelock(PowerManagerProto.WakeLock.Builder builderForValue) {
                copyOnWrite();
                ((Voice) this.instance).setWakelock((Voice) builderForValue);
                return this;
            }

            public Builder mergeWakelock(PowerManagerProto.WakeLock value) {
                copyOnWrite();
                ((Voice) this.instance).mergeWakelock(value);
                return this;
            }

            public Builder clearWakelock() {
                copyOnWrite();
                ((Voice) this.instance).clearWakelock();
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new Voice();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    Voice other = (Voice) arg1;
                    this.session_ = visitor.visitString(hasSession(), this.session_, other.hasSession(), other.session_);
                    this.wakelock_ = (PowerManagerProto.WakeLock) visitor.visitMessage(this.wakelock_, other.wakelock_);
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
                                this.session_ = s;
                            } else if (tag == 18) {
                                PowerManagerProto.WakeLock.Builder subBuilder = null;
                                if ((this.bitField0_ & 2) == 2) {
                                    subBuilder = (PowerManagerProto.WakeLock.Builder) this.wakelock_.toBuilder();
                                }
                                this.wakelock_ = (PowerManagerProto.WakeLock) input.readMessage(PowerManagerProto.WakeLock.parser(), extensionRegistry);
                                if (subBuilder != null) {
                                    subBuilder.mergeFrom((GeneratedMessageLite) this.wakelock_);
                                    this.wakelock_ = (PowerManagerProto.WakeLock) subBuilder.buildPartial();
                                }
                                this.bitField0_ |= 2;
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
                        synchronized (Voice.class) {
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

        public static Voice getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<Voice> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class DebugApp extends GeneratedMessageLite<DebugApp, Builder> implements DebugAppOrBuilder {
        public static final int DEBUG_APP_FIELD_NUMBER = 1;
        public static final int DEBUG_TRANSIENT_FIELD_NUMBER = 3;
        private static final DebugApp DEFAULT_INSTANCE = new DebugApp();
        public static final int ORIG_DEBUG_APP_FIELD_NUMBER = 2;
        public static final int ORIG_WAIT_FOR_DEBUGGER_FIELD_NUMBER = 4;
        private static volatile Parser<DebugApp> PARSER;
        private int bitField0_;
        private String debugApp_ = "";
        private boolean debugTransient_ = false;
        private String origDebugApp_ = "";
        private boolean origWaitForDebugger_ = false;

        private DebugApp() {
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.DebugAppOrBuilder
        public boolean hasDebugApp() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.DebugAppOrBuilder
        public String getDebugApp() {
            return this.debugApp_;
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.DebugAppOrBuilder
        public ByteString getDebugAppBytes() {
            return ByteString.copyFromUtf8(this.debugApp_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setDebugApp(String value) {
            if (value != null) {
                this.bitField0_ |= 1;
                this.debugApp_ = value;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearDebugApp() {
            this.bitField0_ &= -2;
            this.debugApp_ = getDefaultInstance().getDebugApp();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setDebugAppBytes(ByteString value) {
            if (value != null) {
                this.bitField0_ |= 1;
                this.debugApp_ = value.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.DebugAppOrBuilder
        public boolean hasOrigDebugApp() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.DebugAppOrBuilder
        public String getOrigDebugApp() {
            return this.origDebugApp_;
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.DebugAppOrBuilder
        public ByteString getOrigDebugAppBytes() {
            return ByteString.copyFromUtf8(this.origDebugApp_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setOrigDebugApp(String value) {
            if (value != null) {
                this.bitField0_ |= 2;
                this.origDebugApp_ = value;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearOrigDebugApp() {
            this.bitField0_ &= -3;
            this.origDebugApp_ = getDefaultInstance().getOrigDebugApp();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setOrigDebugAppBytes(ByteString value) {
            if (value != null) {
                this.bitField0_ |= 2;
                this.origDebugApp_ = value.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.DebugAppOrBuilder
        public boolean hasDebugTransient() {
            return (this.bitField0_ & 4) == 4;
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.DebugAppOrBuilder
        public boolean getDebugTransient() {
            return this.debugTransient_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setDebugTransient(boolean value) {
            this.bitField0_ |= 4;
            this.debugTransient_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearDebugTransient() {
            this.bitField0_ &= -5;
            this.debugTransient_ = false;
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.DebugAppOrBuilder
        public boolean hasOrigWaitForDebugger() {
            return (this.bitField0_ & 8) == 8;
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.DebugAppOrBuilder
        public boolean getOrigWaitForDebugger() {
            return this.origWaitForDebugger_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setOrigWaitForDebugger(boolean value) {
            this.bitField0_ |= 8;
            this.origWaitForDebugger_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearOrigWaitForDebugger() {
            this.bitField0_ &= -9;
            this.origWaitForDebugger_ = false;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                output.writeString(1, getDebugApp());
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeString(2, getOrigDebugApp());
            }
            if ((this.bitField0_ & 4) == 4) {
                output.writeBool(3, this.debugTransient_);
            }
            if ((this.bitField0_ & 8) == 8) {
                output.writeBool(4, this.origWaitForDebugger_);
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
                size2 = 0 + CodedOutputStream.computeStringSize(1, getDebugApp());
            }
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeStringSize(2, getOrigDebugApp());
            }
            if ((this.bitField0_ & 4) == 4) {
                size2 += CodedOutputStream.computeBoolSize(3, this.debugTransient_);
            }
            if ((this.bitField0_ & 8) == 8) {
                size2 += CodedOutputStream.computeBoolSize(4, this.origWaitForDebugger_);
            }
            int size3 = size2 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static DebugApp parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (DebugApp) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static DebugApp parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (DebugApp) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static DebugApp parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (DebugApp) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static DebugApp parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (DebugApp) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static DebugApp parseFrom(InputStream input) throws IOException {
            return (DebugApp) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static DebugApp parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (DebugApp) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static DebugApp parseDelimitedFrom(InputStream input) throws IOException {
            return (DebugApp) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static DebugApp parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (DebugApp) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static DebugApp parseFrom(CodedInputStream input) throws IOException {
            return (DebugApp) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static DebugApp parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (DebugApp) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(DebugApp prototype) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<DebugApp, Builder> implements DebugAppOrBuilder {
            private Builder() {
                super(DebugApp.DEFAULT_INSTANCE);
            }

            @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.DebugAppOrBuilder
            public boolean hasDebugApp() {
                return ((DebugApp) this.instance).hasDebugApp();
            }

            @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.DebugAppOrBuilder
            public String getDebugApp() {
                return ((DebugApp) this.instance).getDebugApp();
            }

            @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.DebugAppOrBuilder
            public ByteString getDebugAppBytes() {
                return ((DebugApp) this.instance).getDebugAppBytes();
            }

            public Builder setDebugApp(String value) {
                copyOnWrite();
                ((DebugApp) this.instance).setDebugApp(value);
                return this;
            }

            public Builder clearDebugApp() {
                copyOnWrite();
                ((DebugApp) this.instance).clearDebugApp();
                return this;
            }

            public Builder setDebugAppBytes(ByteString value) {
                copyOnWrite();
                ((DebugApp) this.instance).setDebugAppBytes(value);
                return this;
            }

            @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.DebugAppOrBuilder
            public boolean hasOrigDebugApp() {
                return ((DebugApp) this.instance).hasOrigDebugApp();
            }

            @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.DebugAppOrBuilder
            public String getOrigDebugApp() {
                return ((DebugApp) this.instance).getOrigDebugApp();
            }

            @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.DebugAppOrBuilder
            public ByteString getOrigDebugAppBytes() {
                return ((DebugApp) this.instance).getOrigDebugAppBytes();
            }

            public Builder setOrigDebugApp(String value) {
                copyOnWrite();
                ((DebugApp) this.instance).setOrigDebugApp(value);
                return this;
            }

            public Builder clearOrigDebugApp() {
                copyOnWrite();
                ((DebugApp) this.instance).clearOrigDebugApp();
                return this;
            }

            public Builder setOrigDebugAppBytes(ByteString value) {
                copyOnWrite();
                ((DebugApp) this.instance).setOrigDebugAppBytes(value);
                return this;
            }

            @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.DebugAppOrBuilder
            public boolean hasDebugTransient() {
                return ((DebugApp) this.instance).hasDebugTransient();
            }

            @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.DebugAppOrBuilder
            public boolean getDebugTransient() {
                return ((DebugApp) this.instance).getDebugTransient();
            }

            public Builder setDebugTransient(boolean value) {
                copyOnWrite();
                ((DebugApp) this.instance).setDebugTransient(value);
                return this;
            }

            public Builder clearDebugTransient() {
                copyOnWrite();
                ((DebugApp) this.instance).clearDebugTransient();
                return this;
            }

            @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.DebugAppOrBuilder
            public boolean hasOrigWaitForDebugger() {
                return ((DebugApp) this.instance).hasOrigWaitForDebugger();
            }

            @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.DebugAppOrBuilder
            public boolean getOrigWaitForDebugger() {
                return ((DebugApp) this.instance).getOrigWaitForDebugger();
            }

            public Builder setOrigWaitForDebugger(boolean value) {
                copyOnWrite();
                ((DebugApp) this.instance).setOrigWaitForDebugger(value);
                return this;
            }

            public Builder clearOrigWaitForDebugger() {
                copyOnWrite();
                ((DebugApp) this.instance).clearOrigWaitForDebugger();
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new DebugApp();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    DebugApp other = (DebugApp) arg1;
                    this.debugApp_ = visitor.visitString(hasDebugApp(), this.debugApp_, other.hasDebugApp(), other.debugApp_);
                    this.origDebugApp_ = visitor.visitString(hasOrigDebugApp(), this.origDebugApp_, other.hasOrigDebugApp(), other.origDebugApp_);
                    this.debugTransient_ = visitor.visitBoolean(hasDebugTransient(), this.debugTransient_, other.hasDebugTransient(), other.debugTransient_);
                    this.origWaitForDebugger_ = visitor.visitBoolean(hasOrigWaitForDebugger(), this.origWaitForDebugger_, other.hasOrigWaitForDebugger(), other.origWaitForDebugger_);
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
                            } else if (tag == 10) {
                                String s = input.readString();
                                this.bitField0_ |= 1;
                                this.debugApp_ = s;
                            } else if (tag == 18) {
                                String s2 = input.readString();
                                this.bitField0_ |= 2;
                                this.origDebugApp_ = s2;
                            } else if (tag == 24) {
                                this.bitField0_ |= 4;
                                this.debugTransient_ = input.readBool();
                            } else if (tag == 32) {
                                this.bitField0_ |= 8;
                                this.origWaitForDebugger_ = input.readBool();
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
                        synchronized (DebugApp.class) {
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

        public static DebugApp getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<DebugApp> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class MemWatchProcess extends GeneratedMessageLite<MemWatchProcess, Builder> implements MemWatchProcessOrBuilder {
        private static final MemWatchProcess DEFAULT_INSTANCE = new MemWatchProcess();
        public static final int DUMP_FIELD_NUMBER = 2;
        private static volatile Parser<MemWatchProcess> PARSER = null;
        public static final int PROCS_FIELD_NUMBER = 1;
        private int bitField0_;
        private Dump dump_;
        private Internal.ProtobufList<Process> procs_ = emptyProtobufList();

        public interface DumpOrBuilder extends MessageLiteOrBuilder {
            String getFile();

            ByteString getFileBytes();

            boolean getIsUserInitiated();

            int getPid();

            String getProcName();

            ByteString getProcNameBytes();

            int getUid();

            boolean hasFile();

            boolean hasIsUserInitiated();

            boolean hasPid();

            boolean hasProcName();

            boolean hasUid();
        }

        public interface ProcessOrBuilder extends MessageLiteOrBuilder {
            Process.MemStats getMemStats(int i);

            int getMemStatsCount();

            List<Process.MemStats> getMemStatsList();

            String getName();

            ByteString getNameBytes();

            boolean hasName();
        }

        private MemWatchProcess() {
        }

        public static final class Process extends GeneratedMessageLite<Process, Builder> implements ProcessOrBuilder {
            private static final Process DEFAULT_INSTANCE = new Process();
            public static final int MEM_STATS_FIELD_NUMBER = 2;
            public static final int NAME_FIELD_NUMBER = 1;
            private static volatile Parser<Process> PARSER;
            private int bitField0_;
            private Internal.ProtobufList<MemStats> memStats_ = emptyProtobufList();
            private String name_ = "";

            public interface MemStatsOrBuilder extends MessageLiteOrBuilder {
                String getReportTo();

                ByteString getReportToBytes();

                String getSize();

                ByteString getSizeBytes();

                int getUid();

                boolean hasReportTo();

                boolean hasSize();

                boolean hasUid();
            }

            private Process() {
            }

            public static final class MemStats extends GeneratedMessageLite<MemStats, Builder> implements MemStatsOrBuilder {
                private static final MemStats DEFAULT_INSTANCE = new MemStats();
                private static volatile Parser<MemStats> PARSER = null;
                public static final int REPORT_TO_FIELD_NUMBER = 3;
                public static final int SIZE_FIELD_NUMBER = 2;
                public static final int UID_FIELD_NUMBER = 1;
                private int bitField0_;
                private String reportTo_ = "";
                private String size_ = "";
                private int uid_ = 0;

                private MemStats() {
                }

                @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.MemWatchProcess.Process.MemStatsOrBuilder
                public boolean hasUid() {
                    return (this.bitField0_ & 1) == 1;
                }

                @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.MemWatchProcess.Process.MemStatsOrBuilder
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

                @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.MemWatchProcess.Process.MemStatsOrBuilder
                public boolean hasSize() {
                    return (this.bitField0_ & 2) == 2;
                }

                @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.MemWatchProcess.Process.MemStatsOrBuilder
                public String getSize() {
                    return this.size_;
                }

                @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.MemWatchProcess.Process.MemStatsOrBuilder
                public ByteString getSizeBytes() {
                    return ByteString.copyFromUtf8(this.size_);
                }

                /* access modifiers changed from: private */
                /* access modifiers changed from: public */
                private void setSize(String value) {
                    if (value != null) {
                        this.bitField0_ |= 2;
                        this.size_ = value;
                        return;
                    }
                    throw new NullPointerException();
                }

                /* access modifiers changed from: private */
                /* access modifiers changed from: public */
                private void clearSize() {
                    this.bitField0_ &= -3;
                    this.size_ = getDefaultInstance().getSize();
                }

                /* access modifiers changed from: private */
                /* access modifiers changed from: public */
                private void setSizeBytes(ByteString value) {
                    if (value != null) {
                        this.bitField0_ |= 2;
                        this.size_ = value.toStringUtf8();
                        return;
                    }
                    throw new NullPointerException();
                }

                @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.MemWatchProcess.Process.MemStatsOrBuilder
                public boolean hasReportTo() {
                    return (this.bitField0_ & 4) == 4;
                }

                @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.MemWatchProcess.Process.MemStatsOrBuilder
                public String getReportTo() {
                    return this.reportTo_;
                }

                @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.MemWatchProcess.Process.MemStatsOrBuilder
                public ByteString getReportToBytes() {
                    return ByteString.copyFromUtf8(this.reportTo_);
                }

                /* access modifiers changed from: private */
                /* access modifiers changed from: public */
                private void setReportTo(String value) {
                    if (value != null) {
                        this.bitField0_ |= 4;
                        this.reportTo_ = value;
                        return;
                    }
                    throw new NullPointerException();
                }

                /* access modifiers changed from: private */
                /* access modifiers changed from: public */
                private void clearReportTo() {
                    this.bitField0_ &= -5;
                    this.reportTo_ = getDefaultInstance().getReportTo();
                }

                /* access modifiers changed from: private */
                /* access modifiers changed from: public */
                private void setReportToBytes(ByteString value) {
                    if (value != null) {
                        this.bitField0_ |= 4;
                        this.reportTo_ = value.toStringUtf8();
                        return;
                    }
                    throw new NullPointerException();
                }

                @Override // com.google.protobuf.MessageLite
                public void writeTo(CodedOutputStream output) throws IOException {
                    if ((this.bitField0_ & 1) == 1) {
                        output.writeInt32(1, this.uid_);
                    }
                    if ((this.bitField0_ & 2) == 2) {
                        output.writeString(2, getSize());
                    }
                    if ((this.bitField0_ & 4) == 4) {
                        output.writeString(3, getReportTo());
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
                        size2 += CodedOutputStream.computeStringSize(2, getSize());
                    }
                    if ((this.bitField0_ & 4) == 4) {
                        size2 += CodedOutputStream.computeStringSize(3, getReportTo());
                    }
                    int size3 = size2 + this.unknownFields.getSerializedSize();
                    this.memoizedSerializedSize = size3;
                    return size3;
                }

                public static MemStats parseFrom(ByteString data) throws InvalidProtocolBufferException {
                    return (MemStats) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
                }

                public static MemStats parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                    return (MemStats) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
                }

                public static MemStats parseFrom(byte[] data) throws InvalidProtocolBufferException {
                    return (MemStats) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
                }

                public static MemStats parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                    return (MemStats) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
                }

                public static MemStats parseFrom(InputStream input) throws IOException {
                    return (MemStats) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
                }

                public static MemStats parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                    return (MemStats) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
                }

                public static MemStats parseDelimitedFrom(InputStream input) throws IOException {
                    return (MemStats) parseDelimitedFrom(DEFAULT_INSTANCE, input);
                }

                public static MemStats parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                    return (MemStats) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
                }

                public static MemStats parseFrom(CodedInputStream input) throws IOException {
                    return (MemStats) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
                }

                public static MemStats parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                    return (MemStats) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
                }

                public static Builder newBuilder() {
                    return (Builder) DEFAULT_INSTANCE.toBuilder();
                }

                public static Builder newBuilder(MemStats prototype) {
                    return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
                }

                public static final class Builder extends GeneratedMessageLite.Builder<MemStats, Builder> implements MemStatsOrBuilder {
                    private Builder() {
                        super(MemStats.DEFAULT_INSTANCE);
                    }

                    @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.MemWatchProcess.Process.MemStatsOrBuilder
                    public boolean hasUid() {
                        return ((MemStats) this.instance).hasUid();
                    }

                    @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.MemWatchProcess.Process.MemStatsOrBuilder
                    public int getUid() {
                        return ((MemStats) this.instance).getUid();
                    }

                    public Builder setUid(int value) {
                        copyOnWrite();
                        ((MemStats) this.instance).setUid(value);
                        return this;
                    }

                    public Builder clearUid() {
                        copyOnWrite();
                        ((MemStats) this.instance).clearUid();
                        return this;
                    }

                    @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.MemWatchProcess.Process.MemStatsOrBuilder
                    public boolean hasSize() {
                        return ((MemStats) this.instance).hasSize();
                    }

                    @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.MemWatchProcess.Process.MemStatsOrBuilder
                    public String getSize() {
                        return ((MemStats) this.instance).getSize();
                    }

                    @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.MemWatchProcess.Process.MemStatsOrBuilder
                    public ByteString getSizeBytes() {
                        return ((MemStats) this.instance).getSizeBytes();
                    }

                    public Builder setSize(String value) {
                        copyOnWrite();
                        ((MemStats) this.instance).setSize(value);
                        return this;
                    }

                    public Builder clearSize() {
                        copyOnWrite();
                        ((MemStats) this.instance).clearSize();
                        return this;
                    }

                    public Builder setSizeBytes(ByteString value) {
                        copyOnWrite();
                        ((MemStats) this.instance).setSizeBytes(value);
                        return this;
                    }

                    @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.MemWatchProcess.Process.MemStatsOrBuilder
                    public boolean hasReportTo() {
                        return ((MemStats) this.instance).hasReportTo();
                    }

                    @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.MemWatchProcess.Process.MemStatsOrBuilder
                    public String getReportTo() {
                        return ((MemStats) this.instance).getReportTo();
                    }

                    @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.MemWatchProcess.Process.MemStatsOrBuilder
                    public ByteString getReportToBytes() {
                        return ((MemStats) this.instance).getReportToBytes();
                    }

                    public Builder setReportTo(String value) {
                        copyOnWrite();
                        ((MemStats) this.instance).setReportTo(value);
                        return this;
                    }

                    public Builder clearReportTo() {
                        copyOnWrite();
                        ((MemStats) this.instance).clearReportTo();
                        return this;
                    }

                    public Builder setReportToBytes(ByteString value) {
                        copyOnWrite();
                        ((MemStats) this.instance).setReportToBytes(value);
                        return this;
                    }
                }

                /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
                /* access modifiers changed from: protected */
                @Override // com.google.protobuf.GeneratedMessageLite
                public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
                    switch (method) {
                        case NEW_MUTABLE_INSTANCE:
                            return new MemStats();
                        case IS_INITIALIZED:
                            return DEFAULT_INSTANCE;
                        case MAKE_IMMUTABLE:
                            return null;
                        case NEW_BUILDER:
                            return new Builder();
                        case VISIT:
                            GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                            MemStats other = (MemStats) arg1;
                            this.uid_ = visitor.visitInt(hasUid(), this.uid_, other.hasUid(), other.uid_);
                            this.size_ = visitor.visitString(hasSize(), this.size_, other.hasSize(), other.size_);
                            this.reportTo_ = visitor.visitString(hasReportTo(), this.reportTo_, other.hasReportTo(), other.reportTo_);
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
                                    } else if (tag == 18) {
                                        String s = input.readString();
                                        this.bitField0_ |= 2;
                                        this.size_ = s;
                                    } else if (tag == 26) {
                                        String s2 = input.readString();
                                        this.bitField0_ |= 4;
                                        this.reportTo_ = s2;
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
                                synchronized (MemStats.class) {
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

                public static MemStats getDefaultInstance() {
                    return DEFAULT_INSTANCE;
                }

                public static Parser<MemStats> parser() {
                    return DEFAULT_INSTANCE.getParserForType();
                }
            }

            @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.MemWatchProcess.ProcessOrBuilder
            public boolean hasName() {
                return (this.bitField0_ & 1) == 1;
            }

            @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.MemWatchProcess.ProcessOrBuilder
            public String getName() {
                return this.name_;
            }

            @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.MemWatchProcess.ProcessOrBuilder
            public ByteString getNameBytes() {
                return ByteString.copyFromUtf8(this.name_);
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void setName(String value) {
                if (value != null) {
                    this.bitField0_ |= 1;
                    this.name_ = value;
                    return;
                }
                throw new NullPointerException();
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void clearName() {
                this.bitField0_ &= -2;
                this.name_ = getDefaultInstance().getName();
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void setNameBytes(ByteString value) {
                if (value != null) {
                    this.bitField0_ |= 1;
                    this.name_ = value.toStringUtf8();
                    return;
                }
                throw new NullPointerException();
            }

            @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.MemWatchProcess.ProcessOrBuilder
            public List<MemStats> getMemStatsList() {
                return this.memStats_;
            }

            public List<? extends MemStatsOrBuilder> getMemStatsOrBuilderList() {
                return this.memStats_;
            }

            @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.MemWatchProcess.ProcessOrBuilder
            public int getMemStatsCount() {
                return this.memStats_.size();
            }

            @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.MemWatchProcess.ProcessOrBuilder
            public MemStats getMemStats(int index) {
                return this.memStats_.get(index);
            }

            public MemStatsOrBuilder getMemStatsOrBuilder(int index) {
                return this.memStats_.get(index);
            }

            private void ensureMemStatsIsMutable() {
                if (!this.memStats_.isModifiable()) {
                    this.memStats_ = GeneratedMessageLite.mutableCopy(this.memStats_);
                }
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void setMemStats(int index, MemStats value) {
                if (value != null) {
                    ensureMemStatsIsMutable();
                    this.memStats_.set(index, value);
                    return;
                }
                throw new NullPointerException();
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void setMemStats(int index, MemStats.Builder builderForValue) {
                ensureMemStatsIsMutable();
                this.memStats_.set(index, (MemStats) builderForValue.build());
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void addMemStats(MemStats value) {
                if (value != null) {
                    ensureMemStatsIsMutable();
                    this.memStats_.add(value);
                    return;
                }
                throw new NullPointerException();
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void addMemStats(int index, MemStats value) {
                if (value != null) {
                    ensureMemStatsIsMutable();
                    this.memStats_.add(index, value);
                    return;
                }
                throw new NullPointerException();
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void addMemStats(MemStats.Builder builderForValue) {
                ensureMemStatsIsMutable();
                this.memStats_.add((MemStats) builderForValue.build());
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void addMemStats(int index, MemStats.Builder builderForValue) {
                ensureMemStatsIsMutable();
                this.memStats_.add(index, (MemStats) builderForValue.build());
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void addAllMemStats(Iterable<? extends MemStats> values) {
                ensureMemStatsIsMutable();
                AbstractMessageLite.addAll(values, this.memStats_);
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void clearMemStats() {
                this.memStats_ = emptyProtobufList();
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void removeMemStats(int index) {
                ensureMemStatsIsMutable();
                this.memStats_.remove(index);
            }

            @Override // com.google.protobuf.MessageLite
            public void writeTo(CodedOutputStream output) throws IOException {
                if ((this.bitField0_ & 1) == 1) {
                    output.writeString(1, getName());
                }
                for (int i = 0; i < this.memStats_.size(); i++) {
                    output.writeMessage(2, this.memStats_.get(i));
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
                    size2 = 0 + CodedOutputStream.computeStringSize(1, getName());
                }
                for (int i = 0; i < this.memStats_.size(); i++) {
                    size2 += CodedOutputStream.computeMessageSize(2, this.memStats_.get(i));
                }
                int size3 = size2 + this.unknownFields.getSerializedSize();
                this.memoizedSerializedSize = size3;
                return size3;
            }

            public static Process parseFrom(ByteString data) throws InvalidProtocolBufferException {
                return (Process) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
            }

            public static Process parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return (Process) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
            }

            public static Process parseFrom(byte[] data) throws InvalidProtocolBufferException {
                return (Process) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
            }

            public static Process parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return (Process) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
            }

            public static Process parseFrom(InputStream input) throws IOException {
                return (Process) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
            }

            public static Process parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (Process) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static Process parseDelimitedFrom(InputStream input) throws IOException {
                return (Process) parseDelimitedFrom(DEFAULT_INSTANCE, input);
            }

            public static Process parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (Process) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static Process parseFrom(CodedInputStream input) throws IOException {
                return (Process) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
            }

            public static Process parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (Process) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static Builder newBuilder() {
                return (Builder) DEFAULT_INSTANCE.toBuilder();
            }

            public static Builder newBuilder(Process prototype) {
                return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
            }

            public static final class Builder extends GeneratedMessageLite.Builder<Process, Builder> implements ProcessOrBuilder {
                private Builder() {
                    super(Process.DEFAULT_INSTANCE);
                }

                @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.MemWatchProcess.ProcessOrBuilder
                public boolean hasName() {
                    return ((Process) this.instance).hasName();
                }

                @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.MemWatchProcess.ProcessOrBuilder
                public String getName() {
                    return ((Process) this.instance).getName();
                }

                @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.MemWatchProcess.ProcessOrBuilder
                public ByteString getNameBytes() {
                    return ((Process) this.instance).getNameBytes();
                }

                public Builder setName(String value) {
                    copyOnWrite();
                    ((Process) this.instance).setName(value);
                    return this;
                }

                public Builder clearName() {
                    copyOnWrite();
                    ((Process) this.instance).clearName();
                    return this;
                }

                public Builder setNameBytes(ByteString value) {
                    copyOnWrite();
                    ((Process) this.instance).setNameBytes(value);
                    return this;
                }

                @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.MemWatchProcess.ProcessOrBuilder
                public List<MemStats> getMemStatsList() {
                    return Collections.unmodifiableList(((Process) this.instance).getMemStatsList());
                }

                @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.MemWatchProcess.ProcessOrBuilder
                public int getMemStatsCount() {
                    return ((Process) this.instance).getMemStatsCount();
                }

                @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.MemWatchProcess.ProcessOrBuilder
                public MemStats getMemStats(int index) {
                    return ((Process) this.instance).getMemStats(index);
                }

                public Builder setMemStats(int index, MemStats value) {
                    copyOnWrite();
                    ((Process) this.instance).setMemStats((Process) index, (int) value);
                    return this;
                }

                public Builder setMemStats(int index, MemStats.Builder builderForValue) {
                    copyOnWrite();
                    ((Process) this.instance).setMemStats((Process) index, (int) builderForValue);
                    return this;
                }

                public Builder addMemStats(MemStats value) {
                    copyOnWrite();
                    ((Process) this.instance).addMemStats((Process) value);
                    return this;
                }

                public Builder addMemStats(int index, MemStats value) {
                    copyOnWrite();
                    ((Process) this.instance).addMemStats((Process) index, (int) value);
                    return this;
                }

                public Builder addMemStats(MemStats.Builder builderForValue) {
                    copyOnWrite();
                    ((Process) this.instance).addMemStats((Process) builderForValue);
                    return this;
                }

                public Builder addMemStats(int index, MemStats.Builder builderForValue) {
                    copyOnWrite();
                    ((Process) this.instance).addMemStats((Process) index, (int) builderForValue);
                    return this;
                }

                public Builder addAllMemStats(Iterable<? extends MemStats> values) {
                    copyOnWrite();
                    ((Process) this.instance).addAllMemStats(values);
                    return this;
                }

                public Builder clearMemStats() {
                    copyOnWrite();
                    ((Process) this.instance).clearMemStats();
                    return this;
                }

                public Builder removeMemStats(int index) {
                    copyOnWrite();
                    ((Process) this.instance).removeMemStats(index);
                    return this;
                }
            }

            /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
            /* access modifiers changed from: protected */
            @Override // com.google.protobuf.GeneratedMessageLite
            public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
                switch (method) {
                    case NEW_MUTABLE_INSTANCE:
                        return new Process();
                    case IS_INITIALIZED:
                        return DEFAULT_INSTANCE;
                    case MAKE_IMMUTABLE:
                        this.memStats_.makeImmutable();
                        return null;
                    case NEW_BUILDER:
                        return new Builder();
                    case VISIT:
                        GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                        Process other = (Process) arg1;
                        this.name_ = visitor.visitString(hasName(), this.name_, other.hasName(), other.name_);
                        this.memStats_ = visitor.visitList(this.memStats_, other.memStats_);
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
                                    this.name_ = s;
                                } else if (tag == 18) {
                                    if (!this.memStats_.isModifiable()) {
                                        this.memStats_ = GeneratedMessageLite.mutableCopy(this.memStats_);
                                    }
                                    this.memStats_.add((MemStats) input.readMessage(MemStats.parser(), extensionRegistry));
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
                            synchronized (Process.class) {
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

            public static Process getDefaultInstance() {
                return DEFAULT_INSTANCE;
            }

            public static Parser<Process> parser() {
                return DEFAULT_INSTANCE.getParserForType();
            }
        }

        public static final class Dump extends GeneratedMessageLite<Dump, Builder> implements DumpOrBuilder {
            private static final Dump DEFAULT_INSTANCE = new Dump();
            public static final int FILE_FIELD_NUMBER = 2;
            public static final int IS_USER_INITIATED_FIELD_NUMBER = 5;
            private static volatile Parser<Dump> PARSER = null;
            public static final int PID_FIELD_NUMBER = 3;
            public static final int PROC_NAME_FIELD_NUMBER = 1;
            public static final int UID_FIELD_NUMBER = 4;
            private int bitField0_;
            private String file_ = "";
            private boolean isUserInitiated_ = false;
            private int pid_ = 0;
            private String procName_ = "";
            private int uid_ = 0;

            private Dump() {
            }

            @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.MemWatchProcess.DumpOrBuilder
            public boolean hasProcName() {
                return (this.bitField0_ & 1) == 1;
            }

            @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.MemWatchProcess.DumpOrBuilder
            public String getProcName() {
                return this.procName_;
            }

            @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.MemWatchProcess.DumpOrBuilder
            public ByteString getProcNameBytes() {
                return ByteString.copyFromUtf8(this.procName_);
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void setProcName(String value) {
                if (value != null) {
                    this.bitField0_ |= 1;
                    this.procName_ = value;
                    return;
                }
                throw new NullPointerException();
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void clearProcName() {
                this.bitField0_ &= -2;
                this.procName_ = getDefaultInstance().getProcName();
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void setProcNameBytes(ByteString value) {
                if (value != null) {
                    this.bitField0_ |= 1;
                    this.procName_ = value.toStringUtf8();
                    return;
                }
                throw new NullPointerException();
            }

            @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.MemWatchProcess.DumpOrBuilder
            public boolean hasFile() {
                return (this.bitField0_ & 2) == 2;
            }

            @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.MemWatchProcess.DumpOrBuilder
            public String getFile() {
                return this.file_;
            }

            @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.MemWatchProcess.DumpOrBuilder
            public ByteString getFileBytes() {
                return ByteString.copyFromUtf8(this.file_);
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void setFile(String value) {
                if (value != null) {
                    this.bitField0_ |= 2;
                    this.file_ = value;
                    return;
                }
                throw new NullPointerException();
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void clearFile() {
                this.bitField0_ &= -3;
                this.file_ = getDefaultInstance().getFile();
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void setFileBytes(ByteString value) {
                if (value != null) {
                    this.bitField0_ |= 2;
                    this.file_ = value.toStringUtf8();
                    return;
                }
                throw new NullPointerException();
            }

            @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.MemWatchProcess.DumpOrBuilder
            public boolean hasPid() {
                return (this.bitField0_ & 4) == 4;
            }

            @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.MemWatchProcess.DumpOrBuilder
            public int getPid() {
                return this.pid_;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void setPid(int value) {
                this.bitField0_ |= 4;
                this.pid_ = value;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void clearPid() {
                this.bitField0_ &= -5;
                this.pid_ = 0;
            }

            @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.MemWatchProcess.DumpOrBuilder
            public boolean hasUid() {
                return (this.bitField0_ & 8) == 8;
            }

            @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.MemWatchProcess.DumpOrBuilder
            public int getUid() {
                return this.uid_;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void setUid(int value) {
                this.bitField0_ |= 8;
                this.uid_ = value;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void clearUid() {
                this.bitField0_ &= -9;
                this.uid_ = 0;
            }

            @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.MemWatchProcess.DumpOrBuilder
            public boolean hasIsUserInitiated() {
                return (this.bitField0_ & 16) == 16;
            }

            @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.MemWatchProcess.DumpOrBuilder
            public boolean getIsUserInitiated() {
                return this.isUserInitiated_;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void setIsUserInitiated(boolean value) {
                this.bitField0_ |= 16;
                this.isUserInitiated_ = value;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void clearIsUserInitiated() {
                this.bitField0_ &= -17;
                this.isUserInitiated_ = false;
            }

            @Override // com.google.protobuf.MessageLite
            public void writeTo(CodedOutputStream output) throws IOException {
                if ((this.bitField0_ & 1) == 1) {
                    output.writeString(1, getProcName());
                }
                if ((this.bitField0_ & 2) == 2) {
                    output.writeString(2, getFile());
                }
                if ((this.bitField0_ & 4) == 4) {
                    output.writeInt32(3, this.pid_);
                }
                if ((this.bitField0_ & 8) == 8) {
                    output.writeInt32(4, this.uid_);
                }
                if ((this.bitField0_ & 16) == 16) {
                    output.writeBool(5, this.isUserInitiated_);
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
                    size2 = 0 + CodedOutputStream.computeStringSize(1, getProcName());
                }
                if ((this.bitField0_ & 2) == 2) {
                    size2 += CodedOutputStream.computeStringSize(2, getFile());
                }
                if ((this.bitField0_ & 4) == 4) {
                    size2 += CodedOutputStream.computeInt32Size(3, this.pid_);
                }
                if ((this.bitField0_ & 8) == 8) {
                    size2 += CodedOutputStream.computeInt32Size(4, this.uid_);
                }
                if ((this.bitField0_ & 16) == 16) {
                    size2 += CodedOutputStream.computeBoolSize(5, this.isUserInitiated_);
                }
                int size3 = size2 + this.unknownFields.getSerializedSize();
                this.memoizedSerializedSize = size3;
                return size3;
            }

            public static Dump parseFrom(ByteString data) throws InvalidProtocolBufferException {
                return (Dump) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
            }

            public static Dump parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return (Dump) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
            }

            public static Dump parseFrom(byte[] data) throws InvalidProtocolBufferException {
                return (Dump) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
            }

            public static Dump parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return (Dump) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
            }

            public static Dump parseFrom(InputStream input) throws IOException {
                return (Dump) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
            }

            public static Dump parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (Dump) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static Dump parseDelimitedFrom(InputStream input) throws IOException {
                return (Dump) parseDelimitedFrom(DEFAULT_INSTANCE, input);
            }

            public static Dump parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (Dump) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static Dump parseFrom(CodedInputStream input) throws IOException {
                return (Dump) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
            }

            public static Dump parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (Dump) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static Builder newBuilder() {
                return (Builder) DEFAULT_INSTANCE.toBuilder();
            }

            public static Builder newBuilder(Dump prototype) {
                return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
            }

            public static final class Builder extends GeneratedMessageLite.Builder<Dump, Builder> implements DumpOrBuilder {
                private Builder() {
                    super(Dump.DEFAULT_INSTANCE);
                }

                @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.MemWatchProcess.DumpOrBuilder
                public boolean hasProcName() {
                    return ((Dump) this.instance).hasProcName();
                }

                @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.MemWatchProcess.DumpOrBuilder
                public String getProcName() {
                    return ((Dump) this.instance).getProcName();
                }

                @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.MemWatchProcess.DumpOrBuilder
                public ByteString getProcNameBytes() {
                    return ((Dump) this.instance).getProcNameBytes();
                }

                public Builder setProcName(String value) {
                    copyOnWrite();
                    ((Dump) this.instance).setProcName(value);
                    return this;
                }

                public Builder clearProcName() {
                    copyOnWrite();
                    ((Dump) this.instance).clearProcName();
                    return this;
                }

                public Builder setProcNameBytes(ByteString value) {
                    copyOnWrite();
                    ((Dump) this.instance).setProcNameBytes(value);
                    return this;
                }

                @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.MemWatchProcess.DumpOrBuilder
                public boolean hasFile() {
                    return ((Dump) this.instance).hasFile();
                }

                @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.MemWatchProcess.DumpOrBuilder
                public String getFile() {
                    return ((Dump) this.instance).getFile();
                }

                @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.MemWatchProcess.DumpOrBuilder
                public ByteString getFileBytes() {
                    return ((Dump) this.instance).getFileBytes();
                }

                public Builder setFile(String value) {
                    copyOnWrite();
                    ((Dump) this.instance).setFile(value);
                    return this;
                }

                public Builder clearFile() {
                    copyOnWrite();
                    ((Dump) this.instance).clearFile();
                    return this;
                }

                public Builder setFileBytes(ByteString value) {
                    copyOnWrite();
                    ((Dump) this.instance).setFileBytes(value);
                    return this;
                }

                @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.MemWatchProcess.DumpOrBuilder
                public boolean hasPid() {
                    return ((Dump) this.instance).hasPid();
                }

                @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.MemWatchProcess.DumpOrBuilder
                public int getPid() {
                    return ((Dump) this.instance).getPid();
                }

                public Builder setPid(int value) {
                    copyOnWrite();
                    ((Dump) this.instance).setPid(value);
                    return this;
                }

                public Builder clearPid() {
                    copyOnWrite();
                    ((Dump) this.instance).clearPid();
                    return this;
                }

                @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.MemWatchProcess.DumpOrBuilder
                public boolean hasUid() {
                    return ((Dump) this.instance).hasUid();
                }

                @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.MemWatchProcess.DumpOrBuilder
                public int getUid() {
                    return ((Dump) this.instance).getUid();
                }

                public Builder setUid(int value) {
                    copyOnWrite();
                    ((Dump) this.instance).setUid(value);
                    return this;
                }

                public Builder clearUid() {
                    copyOnWrite();
                    ((Dump) this.instance).clearUid();
                    return this;
                }

                @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.MemWatchProcess.DumpOrBuilder
                public boolean hasIsUserInitiated() {
                    return ((Dump) this.instance).hasIsUserInitiated();
                }

                @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.MemWatchProcess.DumpOrBuilder
                public boolean getIsUserInitiated() {
                    return ((Dump) this.instance).getIsUserInitiated();
                }

                public Builder setIsUserInitiated(boolean value) {
                    copyOnWrite();
                    ((Dump) this.instance).setIsUserInitiated(value);
                    return this;
                }

                public Builder clearIsUserInitiated() {
                    copyOnWrite();
                    ((Dump) this.instance).clearIsUserInitiated();
                    return this;
                }
            }

            /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
            /* access modifiers changed from: protected */
            @Override // com.google.protobuf.GeneratedMessageLite
            public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
                switch (method) {
                    case NEW_MUTABLE_INSTANCE:
                        return new Dump();
                    case IS_INITIALIZED:
                        return DEFAULT_INSTANCE;
                    case MAKE_IMMUTABLE:
                        return null;
                    case NEW_BUILDER:
                        return new Builder();
                    case VISIT:
                        GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                        Dump other = (Dump) arg1;
                        this.procName_ = visitor.visitString(hasProcName(), this.procName_, other.hasProcName(), other.procName_);
                        this.file_ = visitor.visitString(hasFile(), this.file_, other.hasFile(), other.file_);
                        this.pid_ = visitor.visitInt(hasPid(), this.pid_, other.hasPid(), other.pid_);
                        this.uid_ = visitor.visitInt(hasUid(), this.uid_, other.hasUid(), other.uid_);
                        this.isUserInitiated_ = visitor.visitBoolean(hasIsUserInitiated(), this.isUserInitiated_, other.hasIsUserInitiated(), other.isUserInitiated_);
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
                                } else if (tag == 10) {
                                    String s = input.readString();
                                    this.bitField0_ |= 1;
                                    this.procName_ = s;
                                } else if (tag == 18) {
                                    String s2 = input.readString();
                                    this.bitField0_ |= 2;
                                    this.file_ = s2;
                                } else if (tag == 24) {
                                    this.bitField0_ |= 4;
                                    this.pid_ = input.readInt32();
                                } else if (tag == 32) {
                                    this.bitField0_ |= 8;
                                    this.uid_ = input.readInt32();
                                } else if (tag == 40) {
                                    this.bitField0_ |= 16;
                                    this.isUserInitiated_ = input.readBool();
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
                            synchronized (Dump.class) {
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

            public static Dump getDefaultInstance() {
                return DEFAULT_INSTANCE;
            }

            public static Parser<Dump> parser() {
                return DEFAULT_INSTANCE.getParserForType();
            }
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.MemWatchProcessOrBuilder
        public List<Process> getProcsList() {
            return this.procs_;
        }

        public List<? extends ProcessOrBuilder> getProcsOrBuilderList() {
            return this.procs_;
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.MemWatchProcessOrBuilder
        public int getProcsCount() {
            return this.procs_.size();
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.MemWatchProcessOrBuilder
        public Process getProcs(int index) {
            return this.procs_.get(index);
        }

        public ProcessOrBuilder getProcsOrBuilder(int index) {
            return this.procs_.get(index);
        }

        private void ensureProcsIsMutable() {
            if (!this.procs_.isModifiable()) {
                this.procs_ = GeneratedMessageLite.mutableCopy(this.procs_);
            }
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setProcs(int index, Process value) {
            if (value != null) {
                ensureProcsIsMutable();
                this.procs_.set(index, value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setProcs(int index, Process.Builder builderForValue) {
            ensureProcsIsMutable();
            this.procs_.set(index, (Process) builderForValue.build());
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addProcs(Process value) {
            if (value != null) {
                ensureProcsIsMutable();
                this.procs_.add(value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addProcs(int index, Process value) {
            if (value != null) {
                ensureProcsIsMutable();
                this.procs_.add(index, value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addProcs(Process.Builder builderForValue) {
            ensureProcsIsMutable();
            this.procs_.add((Process) builderForValue.build());
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addProcs(int index, Process.Builder builderForValue) {
            ensureProcsIsMutable();
            this.procs_.add(index, (Process) builderForValue.build());
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addAllProcs(Iterable<? extends Process> values) {
            ensureProcsIsMutable();
            AbstractMessageLite.addAll(values, this.procs_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearProcs() {
            this.procs_ = emptyProtobufList();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void removeProcs(int index) {
            ensureProcsIsMutable();
            this.procs_.remove(index);
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.MemWatchProcessOrBuilder
        public boolean hasDump() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.MemWatchProcessOrBuilder
        public Dump getDump() {
            Dump dump = this.dump_;
            return dump == null ? Dump.getDefaultInstance() : dump;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setDump(Dump value) {
            if (value != null) {
                this.dump_ = value;
                this.bitField0_ |= 1;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setDump(Dump.Builder builderForValue) {
            this.dump_ = (Dump) builderForValue.build();
            this.bitField0_ |= 1;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void mergeDump(Dump value) {
            Dump dump = this.dump_;
            if (dump == null || dump == Dump.getDefaultInstance()) {
                this.dump_ = value;
            } else {
                this.dump_ = (Dump) ((Dump.Builder) Dump.newBuilder(this.dump_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
            }
            this.bitField0_ |= 1;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearDump() {
            this.dump_ = null;
            this.bitField0_ &= -2;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            for (int i = 0; i < this.procs_.size(); i++) {
                output.writeMessage(1, this.procs_.get(i));
            }
            if ((this.bitField0_ & 1) == 1) {
                output.writeMessage(2, getDump());
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
            for (int i = 0; i < this.procs_.size(); i++) {
                size2 += CodedOutputStream.computeMessageSize(1, this.procs_.get(i));
            }
            if ((this.bitField0_ & 1) == 1) {
                size2 += CodedOutputStream.computeMessageSize(2, getDump());
            }
            int size3 = size2 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static MemWatchProcess parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (MemWatchProcess) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static MemWatchProcess parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (MemWatchProcess) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static MemWatchProcess parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (MemWatchProcess) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static MemWatchProcess parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (MemWatchProcess) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static MemWatchProcess parseFrom(InputStream input) throws IOException {
            return (MemWatchProcess) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static MemWatchProcess parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (MemWatchProcess) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static MemWatchProcess parseDelimitedFrom(InputStream input) throws IOException {
            return (MemWatchProcess) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static MemWatchProcess parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (MemWatchProcess) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static MemWatchProcess parseFrom(CodedInputStream input) throws IOException {
            return (MemWatchProcess) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static MemWatchProcess parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (MemWatchProcess) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(MemWatchProcess prototype) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<MemWatchProcess, Builder> implements MemWatchProcessOrBuilder {
            private Builder() {
                super(MemWatchProcess.DEFAULT_INSTANCE);
            }

            @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.MemWatchProcessOrBuilder
            public List<Process> getProcsList() {
                return Collections.unmodifiableList(((MemWatchProcess) this.instance).getProcsList());
            }

            @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.MemWatchProcessOrBuilder
            public int getProcsCount() {
                return ((MemWatchProcess) this.instance).getProcsCount();
            }

            @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.MemWatchProcessOrBuilder
            public Process getProcs(int index) {
                return ((MemWatchProcess) this.instance).getProcs(index);
            }

            public Builder setProcs(int index, Process value) {
                copyOnWrite();
                ((MemWatchProcess) this.instance).setProcs((MemWatchProcess) index, (int) value);
                return this;
            }

            public Builder setProcs(int index, Process.Builder builderForValue) {
                copyOnWrite();
                ((MemWatchProcess) this.instance).setProcs((MemWatchProcess) index, (int) builderForValue);
                return this;
            }

            public Builder addProcs(Process value) {
                copyOnWrite();
                ((MemWatchProcess) this.instance).addProcs((MemWatchProcess) value);
                return this;
            }

            public Builder addProcs(int index, Process value) {
                copyOnWrite();
                ((MemWatchProcess) this.instance).addProcs((MemWatchProcess) index, (int) value);
                return this;
            }

            public Builder addProcs(Process.Builder builderForValue) {
                copyOnWrite();
                ((MemWatchProcess) this.instance).addProcs((MemWatchProcess) builderForValue);
                return this;
            }

            public Builder addProcs(int index, Process.Builder builderForValue) {
                copyOnWrite();
                ((MemWatchProcess) this.instance).addProcs((MemWatchProcess) index, (int) builderForValue);
                return this;
            }

            public Builder addAllProcs(Iterable<? extends Process> values) {
                copyOnWrite();
                ((MemWatchProcess) this.instance).addAllProcs(values);
                return this;
            }

            public Builder clearProcs() {
                copyOnWrite();
                ((MemWatchProcess) this.instance).clearProcs();
                return this;
            }

            public Builder removeProcs(int index) {
                copyOnWrite();
                ((MemWatchProcess) this.instance).removeProcs(index);
                return this;
            }

            @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.MemWatchProcessOrBuilder
            public boolean hasDump() {
                return ((MemWatchProcess) this.instance).hasDump();
            }

            @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.MemWatchProcessOrBuilder
            public Dump getDump() {
                return ((MemWatchProcess) this.instance).getDump();
            }

            public Builder setDump(Dump value) {
                copyOnWrite();
                ((MemWatchProcess) this.instance).setDump((MemWatchProcess) value);
                return this;
            }

            public Builder setDump(Dump.Builder builderForValue) {
                copyOnWrite();
                ((MemWatchProcess) this.instance).setDump((MemWatchProcess) builderForValue);
                return this;
            }

            public Builder mergeDump(Dump value) {
                copyOnWrite();
                ((MemWatchProcess) this.instance).mergeDump(value);
                return this;
            }

            public Builder clearDump() {
                copyOnWrite();
                ((MemWatchProcess) this.instance).clearDump();
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new MemWatchProcess();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    this.procs_.makeImmutable();
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    MemWatchProcess other = (MemWatchProcess) arg1;
                    this.procs_ = visitor.visitList(this.procs_, other.procs_);
                    this.dump_ = (Dump) visitor.visitMessage(this.dump_, other.dump_);
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
                                if (!this.procs_.isModifiable()) {
                                    this.procs_ = GeneratedMessageLite.mutableCopy(this.procs_);
                                }
                                this.procs_.add((Process) input.readMessage(Process.parser(), extensionRegistry));
                            } else if (tag == 18) {
                                Dump.Builder subBuilder = null;
                                if ((this.bitField0_ & 1) == 1) {
                                    subBuilder = (Dump.Builder) this.dump_.toBuilder();
                                }
                                this.dump_ = (Dump) input.readMessage(Dump.parser(), extensionRegistry);
                                if (subBuilder != null) {
                                    subBuilder.mergeFrom((GeneratedMessageLite) this.dump_);
                                    this.dump_ = (Dump) subBuilder.buildPartial();
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
                        synchronized (MemWatchProcess.class) {
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

        public static MemWatchProcess getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<MemWatchProcess> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class Profile extends GeneratedMessageLite<Profile, Builder> implements ProfileOrBuilder {
        public static final int APP_NAME_FIELD_NUMBER = 1;
        private static final Profile DEFAULT_INSTANCE = new Profile();
        public static final int INFO_FIELD_NUMBER = 3;
        private static volatile Parser<Profile> PARSER = null;
        public static final int PROC_FIELD_NUMBER = 2;
        public static final int TYPE_FIELD_NUMBER = 4;
        private String appName_ = "";
        private int bitField0_;
        private ProfilerInfoProto info_;
        private ProcessRecordProto proc_;
        private int type_ = 0;

        private Profile() {
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.ProfileOrBuilder
        public boolean hasAppName() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.ProfileOrBuilder
        public String getAppName() {
            return this.appName_;
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.ProfileOrBuilder
        public ByteString getAppNameBytes() {
            return ByteString.copyFromUtf8(this.appName_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setAppName(String value) {
            if (value != null) {
                this.bitField0_ |= 1;
                this.appName_ = value;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearAppName() {
            this.bitField0_ &= -2;
            this.appName_ = getDefaultInstance().getAppName();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setAppNameBytes(ByteString value) {
            if (value != null) {
                this.bitField0_ |= 1;
                this.appName_ = value.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.ProfileOrBuilder
        public boolean hasProc() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.ProfileOrBuilder
        public ProcessRecordProto getProc() {
            ProcessRecordProto processRecordProto = this.proc_;
            return processRecordProto == null ? ProcessRecordProto.getDefaultInstance() : processRecordProto;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setProc(ProcessRecordProto value) {
            if (value != null) {
                this.proc_ = value;
                this.bitField0_ |= 2;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setProc(ProcessRecordProto.Builder builderForValue) {
            this.proc_ = (ProcessRecordProto) builderForValue.build();
            this.bitField0_ |= 2;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void mergeProc(ProcessRecordProto value) {
            ProcessRecordProto processRecordProto = this.proc_;
            if (processRecordProto == null || processRecordProto == ProcessRecordProto.getDefaultInstance()) {
                this.proc_ = value;
            } else {
                this.proc_ = (ProcessRecordProto) ((ProcessRecordProto.Builder) ProcessRecordProto.newBuilder(this.proc_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
            }
            this.bitField0_ |= 2;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearProc() {
            this.proc_ = null;
            this.bitField0_ &= -3;
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.ProfileOrBuilder
        public boolean hasInfo() {
            return (this.bitField0_ & 4) == 4;
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.ProfileOrBuilder
        public ProfilerInfoProto getInfo() {
            ProfilerInfoProto profilerInfoProto = this.info_;
            return profilerInfoProto == null ? ProfilerInfoProto.getDefaultInstance() : profilerInfoProto;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setInfo(ProfilerInfoProto value) {
            if (value != null) {
                this.info_ = value;
                this.bitField0_ |= 4;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setInfo(ProfilerInfoProto.Builder builderForValue) {
            this.info_ = (ProfilerInfoProto) builderForValue.build();
            this.bitField0_ |= 4;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void mergeInfo(ProfilerInfoProto value) {
            ProfilerInfoProto profilerInfoProto = this.info_;
            if (profilerInfoProto == null || profilerInfoProto == ProfilerInfoProto.getDefaultInstance()) {
                this.info_ = value;
            } else {
                this.info_ = (ProfilerInfoProto) ((ProfilerInfoProto.Builder) ProfilerInfoProto.newBuilder(this.info_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
            }
            this.bitField0_ |= 4;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearInfo() {
            this.info_ = null;
            this.bitField0_ &= -5;
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.ProfileOrBuilder
        public boolean hasType() {
            return (this.bitField0_ & 8) == 8;
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.ProfileOrBuilder
        public int getType() {
            return this.type_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setType(int value) {
            this.bitField0_ |= 8;
            this.type_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearType() {
            this.bitField0_ &= -9;
            this.type_ = 0;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                output.writeString(1, getAppName());
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeMessage(2, getProc());
            }
            if ((this.bitField0_ & 4) == 4) {
                output.writeMessage(3, getInfo());
            }
            if ((this.bitField0_ & 8) == 8) {
                output.writeInt32(4, this.type_);
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
                size2 = 0 + CodedOutputStream.computeStringSize(1, getAppName());
            }
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeMessageSize(2, getProc());
            }
            if ((this.bitField0_ & 4) == 4) {
                size2 += CodedOutputStream.computeMessageSize(3, getInfo());
            }
            if ((this.bitField0_ & 8) == 8) {
                size2 += CodedOutputStream.computeInt32Size(4, this.type_);
            }
            int size3 = size2 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static Profile parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (Profile) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static Profile parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Profile) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static Profile parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (Profile) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static Profile parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Profile) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static Profile parseFrom(InputStream input) throws IOException {
            return (Profile) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static Profile parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Profile) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Profile parseDelimitedFrom(InputStream input) throws IOException {
            return (Profile) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static Profile parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Profile) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Profile parseFrom(CodedInputStream input) throws IOException {
            return (Profile) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static Profile parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Profile) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(Profile prototype) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<Profile, Builder> implements ProfileOrBuilder {
            private Builder() {
                super(Profile.DEFAULT_INSTANCE);
            }

            @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.ProfileOrBuilder
            public boolean hasAppName() {
                return ((Profile) this.instance).hasAppName();
            }

            @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.ProfileOrBuilder
            public String getAppName() {
                return ((Profile) this.instance).getAppName();
            }

            @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.ProfileOrBuilder
            public ByteString getAppNameBytes() {
                return ((Profile) this.instance).getAppNameBytes();
            }

            public Builder setAppName(String value) {
                copyOnWrite();
                ((Profile) this.instance).setAppName(value);
                return this;
            }

            public Builder clearAppName() {
                copyOnWrite();
                ((Profile) this.instance).clearAppName();
                return this;
            }

            public Builder setAppNameBytes(ByteString value) {
                copyOnWrite();
                ((Profile) this.instance).setAppNameBytes(value);
                return this;
            }

            @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.ProfileOrBuilder
            public boolean hasProc() {
                return ((Profile) this.instance).hasProc();
            }

            @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.ProfileOrBuilder
            public ProcessRecordProto getProc() {
                return ((Profile) this.instance).getProc();
            }

            public Builder setProc(ProcessRecordProto value) {
                copyOnWrite();
                ((Profile) this.instance).setProc((Profile) value);
                return this;
            }

            public Builder setProc(ProcessRecordProto.Builder builderForValue) {
                copyOnWrite();
                ((Profile) this.instance).setProc((Profile) builderForValue);
                return this;
            }

            public Builder mergeProc(ProcessRecordProto value) {
                copyOnWrite();
                ((Profile) this.instance).mergeProc(value);
                return this;
            }

            public Builder clearProc() {
                copyOnWrite();
                ((Profile) this.instance).clearProc();
                return this;
            }

            @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.ProfileOrBuilder
            public boolean hasInfo() {
                return ((Profile) this.instance).hasInfo();
            }

            @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.ProfileOrBuilder
            public ProfilerInfoProto getInfo() {
                return ((Profile) this.instance).getInfo();
            }

            public Builder setInfo(ProfilerInfoProto value) {
                copyOnWrite();
                ((Profile) this.instance).setInfo((Profile) value);
                return this;
            }

            public Builder setInfo(ProfilerInfoProto.Builder builderForValue) {
                copyOnWrite();
                ((Profile) this.instance).setInfo((Profile) builderForValue);
                return this;
            }

            public Builder mergeInfo(ProfilerInfoProto value) {
                copyOnWrite();
                ((Profile) this.instance).mergeInfo(value);
                return this;
            }

            public Builder clearInfo() {
                copyOnWrite();
                ((Profile) this.instance).clearInfo();
                return this;
            }

            @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.ProfileOrBuilder
            public boolean hasType() {
                return ((Profile) this.instance).hasType();
            }

            @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.ProfileOrBuilder
            public int getType() {
                return ((Profile) this.instance).getType();
            }

            public Builder setType(int value) {
                copyOnWrite();
                ((Profile) this.instance).setType(value);
                return this;
            }

            public Builder clearType() {
                copyOnWrite();
                ((Profile) this.instance).clearType();
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new Profile();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    Profile other = (Profile) arg1;
                    this.appName_ = visitor.visitString(hasAppName(), this.appName_, other.hasAppName(), other.appName_);
                    this.proc_ = (ProcessRecordProto) visitor.visitMessage(this.proc_, other.proc_);
                    this.info_ = (ProfilerInfoProto) visitor.visitMessage(this.info_, other.info_);
                    this.type_ = visitor.visitInt(hasType(), this.type_, other.hasType(), other.type_);
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
                                this.appName_ = s;
                            } else if (tag == 18) {
                                ProcessRecordProto.Builder subBuilder = null;
                                if ((this.bitField0_ & 2) == 2) {
                                    subBuilder = (ProcessRecordProto.Builder) this.proc_.toBuilder();
                                }
                                this.proc_ = (ProcessRecordProto) input.readMessage(ProcessRecordProto.parser(), extensionRegistry);
                                if (subBuilder != null) {
                                    subBuilder.mergeFrom((GeneratedMessageLite) this.proc_);
                                    this.proc_ = (ProcessRecordProto) subBuilder.buildPartial();
                                }
                                this.bitField0_ |= 2;
                            } else if (tag == 26) {
                                ProfilerInfoProto.Builder subBuilder2 = null;
                                if ((this.bitField0_ & 4) == 4) {
                                    subBuilder2 = (ProfilerInfoProto.Builder) this.info_.toBuilder();
                                }
                                this.info_ = (ProfilerInfoProto) input.readMessage(ProfilerInfoProto.parser(), extensionRegistry);
                                if (subBuilder2 != null) {
                                    subBuilder2.mergeFrom((GeneratedMessageLite) this.info_);
                                    this.info_ = (ProfilerInfoProto) subBuilder2.buildPartial();
                                }
                                this.bitField0_ |= 4;
                            } else if (tag == 32) {
                                this.bitField0_ |= 8;
                                this.type_ = input.readInt32();
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
                        synchronized (Profile.class) {
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

        public static Profile getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<Profile> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class Controller extends GeneratedMessageLite<Controller, Builder> implements ControllerOrBuilder {
        public static final int CONTROLLER_FIELD_NUMBER = 1;
        private static final Controller DEFAULT_INSTANCE = new Controller();
        public static final int IS_A_MONKEY_FIELD_NUMBER = 2;
        private static volatile Parser<Controller> PARSER;
        private int bitField0_;
        private String controller_ = "";
        private boolean isAMonkey_ = false;

        private Controller() {
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.ControllerOrBuilder
        public boolean hasController() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.ControllerOrBuilder
        public String getController() {
            return this.controller_;
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.ControllerOrBuilder
        public ByteString getControllerBytes() {
            return ByteString.copyFromUtf8(this.controller_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setController(String value) {
            if (value != null) {
                this.bitField0_ |= 1;
                this.controller_ = value;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearController() {
            this.bitField0_ &= -2;
            this.controller_ = getDefaultInstance().getController();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setControllerBytes(ByteString value) {
            if (value != null) {
                this.bitField0_ |= 1;
                this.controller_ = value.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.ControllerOrBuilder
        public boolean hasIsAMonkey() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.ControllerOrBuilder
        public boolean getIsAMonkey() {
            return this.isAMonkey_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setIsAMonkey(boolean value) {
            this.bitField0_ |= 2;
            this.isAMonkey_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearIsAMonkey() {
            this.bitField0_ &= -3;
            this.isAMonkey_ = false;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                output.writeString(1, getController());
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeBool(2, this.isAMonkey_);
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
                size2 = 0 + CodedOutputStream.computeStringSize(1, getController());
            }
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeBoolSize(2, this.isAMonkey_);
            }
            int size3 = size2 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static Controller parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (Controller) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static Controller parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Controller) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static Controller parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (Controller) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static Controller parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Controller) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static Controller parseFrom(InputStream input) throws IOException {
            return (Controller) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static Controller parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Controller) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Controller parseDelimitedFrom(InputStream input) throws IOException {
            return (Controller) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static Controller parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Controller) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Controller parseFrom(CodedInputStream input) throws IOException {
            return (Controller) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static Controller parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Controller) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(Controller prototype) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<Controller, Builder> implements ControllerOrBuilder {
            private Builder() {
                super(Controller.DEFAULT_INSTANCE);
            }

            @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.ControllerOrBuilder
            public boolean hasController() {
                return ((Controller) this.instance).hasController();
            }

            @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.ControllerOrBuilder
            public String getController() {
                return ((Controller) this.instance).getController();
            }

            @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.ControllerOrBuilder
            public ByteString getControllerBytes() {
                return ((Controller) this.instance).getControllerBytes();
            }

            public Builder setController(String value) {
                copyOnWrite();
                ((Controller) this.instance).setController(value);
                return this;
            }

            public Builder clearController() {
                copyOnWrite();
                ((Controller) this.instance).clearController();
                return this;
            }

            public Builder setControllerBytes(ByteString value) {
                copyOnWrite();
                ((Controller) this.instance).setControllerBytes(value);
                return this;
            }

            @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.ControllerOrBuilder
            public boolean hasIsAMonkey() {
                return ((Controller) this.instance).hasIsAMonkey();
            }

            @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProto.ControllerOrBuilder
            public boolean getIsAMonkey() {
                return ((Controller) this.instance).getIsAMonkey();
            }

            public Builder setIsAMonkey(boolean value) {
                copyOnWrite();
                ((Controller) this.instance).setIsAMonkey(value);
                return this;
            }

            public Builder clearIsAMonkey() {
                copyOnWrite();
                ((Controller) this.instance).clearIsAMonkey();
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new Controller();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    Controller other = (Controller) arg1;
                    this.controller_ = visitor.visitString(hasController(), this.controller_, other.hasController(), other.controller_);
                    this.isAMonkey_ = visitor.visitBoolean(hasIsAMonkey(), this.isAMonkey_, other.hasIsAMonkey(), other.isAMonkey_);
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
                            } else if (tag == 10) {
                                String s = input.readString();
                                this.bitField0_ |= 1;
                                this.controller_ = s;
                            } else if (tag == 16) {
                                this.bitField0_ |= 2;
                                this.isAMonkey_ = input.readBool();
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
                        synchronized (Controller.class) {
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

        public static Controller getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<Controller> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
    public List<ProcessRecordProto> getProcsList() {
        return this.procs_;
    }

    public List<? extends ProcessRecordProtoOrBuilder> getProcsOrBuilderList() {
        return this.procs_;
    }

    @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
    public int getProcsCount() {
        return this.procs_.size();
    }

    @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
    public ProcessRecordProto getProcs(int index) {
        return this.procs_.get(index);
    }

    public ProcessRecordProtoOrBuilder getProcsOrBuilder(int index) {
        return this.procs_.get(index);
    }

    private void ensureProcsIsMutable() {
        if (!this.procs_.isModifiable()) {
            this.procs_ = GeneratedMessageLite.mutableCopy(this.procs_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setProcs(int index, ProcessRecordProto value) {
        if (value != null) {
            ensureProcsIsMutable();
            this.procs_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setProcs(int index, ProcessRecordProto.Builder builderForValue) {
        ensureProcsIsMutable();
        this.procs_.set(index, (ProcessRecordProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addProcs(ProcessRecordProto value) {
        if (value != null) {
            ensureProcsIsMutable();
            this.procs_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addProcs(int index, ProcessRecordProto value) {
        if (value != null) {
            ensureProcsIsMutable();
            this.procs_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addProcs(ProcessRecordProto.Builder builderForValue) {
        ensureProcsIsMutable();
        this.procs_.add((ProcessRecordProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addProcs(int index, ProcessRecordProto.Builder builderForValue) {
        ensureProcsIsMutable();
        this.procs_.add(index, (ProcessRecordProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllProcs(Iterable<? extends ProcessRecordProto> values) {
        ensureProcsIsMutable();
        AbstractMessageLite.addAll(values, this.procs_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearProcs() {
        this.procs_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeProcs(int index) {
        ensureProcsIsMutable();
        this.procs_.remove(index);
    }

    @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
    public List<ProcessRecordProto> getIsolatedProcsList() {
        return this.isolatedProcs_;
    }

    public List<? extends ProcessRecordProtoOrBuilder> getIsolatedProcsOrBuilderList() {
        return this.isolatedProcs_;
    }

    @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
    public int getIsolatedProcsCount() {
        return this.isolatedProcs_.size();
    }

    @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
    public ProcessRecordProto getIsolatedProcs(int index) {
        return this.isolatedProcs_.get(index);
    }

    public ProcessRecordProtoOrBuilder getIsolatedProcsOrBuilder(int index) {
        return this.isolatedProcs_.get(index);
    }

    private void ensureIsolatedProcsIsMutable() {
        if (!this.isolatedProcs_.isModifiable()) {
            this.isolatedProcs_ = GeneratedMessageLite.mutableCopy(this.isolatedProcs_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setIsolatedProcs(int index, ProcessRecordProto value) {
        if (value != null) {
            ensureIsolatedProcsIsMutable();
            this.isolatedProcs_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setIsolatedProcs(int index, ProcessRecordProto.Builder builderForValue) {
        ensureIsolatedProcsIsMutable();
        this.isolatedProcs_.set(index, (ProcessRecordProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addIsolatedProcs(ProcessRecordProto value) {
        if (value != null) {
            ensureIsolatedProcsIsMutable();
            this.isolatedProcs_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addIsolatedProcs(int index, ProcessRecordProto value) {
        if (value != null) {
            ensureIsolatedProcsIsMutable();
            this.isolatedProcs_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addIsolatedProcs(ProcessRecordProto.Builder builderForValue) {
        ensureIsolatedProcsIsMutable();
        this.isolatedProcs_.add((ProcessRecordProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addIsolatedProcs(int index, ProcessRecordProto.Builder builderForValue) {
        ensureIsolatedProcsIsMutable();
        this.isolatedProcs_.add(index, (ProcessRecordProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllIsolatedProcs(Iterable<? extends ProcessRecordProto> values) {
        ensureIsolatedProcsIsMutable();
        AbstractMessageLite.addAll(values, this.isolatedProcs_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearIsolatedProcs() {
        this.isolatedProcs_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeIsolatedProcs(int index) {
        ensureIsolatedProcsIsMutable();
        this.isolatedProcs_.remove(index);
    }

    @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
    public List<ActiveInstrumentationProto> getActiveInstrumentationsList() {
        return this.activeInstrumentations_;
    }

    public List<? extends ActiveInstrumentationProtoOrBuilder> getActiveInstrumentationsOrBuilderList() {
        return this.activeInstrumentations_;
    }

    @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
    public int getActiveInstrumentationsCount() {
        return this.activeInstrumentations_.size();
    }

    @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
    public ActiveInstrumentationProto getActiveInstrumentations(int index) {
        return this.activeInstrumentations_.get(index);
    }

    public ActiveInstrumentationProtoOrBuilder getActiveInstrumentationsOrBuilder(int index) {
        return this.activeInstrumentations_.get(index);
    }

    private void ensureActiveInstrumentationsIsMutable() {
        if (!this.activeInstrumentations_.isModifiable()) {
            this.activeInstrumentations_ = GeneratedMessageLite.mutableCopy(this.activeInstrumentations_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setActiveInstrumentations(int index, ActiveInstrumentationProto value) {
        if (value != null) {
            ensureActiveInstrumentationsIsMutable();
            this.activeInstrumentations_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setActiveInstrumentations(int index, ActiveInstrumentationProto.Builder builderForValue) {
        ensureActiveInstrumentationsIsMutable();
        this.activeInstrumentations_.set(index, (ActiveInstrumentationProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addActiveInstrumentations(ActiveInstrumentationProto value) {
        if (value != null) {
            ensureActiveInstrumentationsIsMutable();
            this.activeInstrumentations_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addActiveInstrumentations(int index, ActiveInstrumentationProto value) {
        if (value != null) {
            ensureActiveInstrumentationsIsMutable();
            this.activeInstrumentations_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addActiveInstrumentations(ActiveInstrumentationProto.Builder builderForValue) {
        ensureActiveInstrumentationsIsMutable();
        this.activeInstrumentations_.add((ActiveInstrumentationProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addActiveInstrumentations(int index, ActiveInstrumentationProto.Builder builderForValue) {
        ensureActiveInstrumentationsIsMutable();
        this.activeInstrumentations_.add(index, (ActiveInstrumentationProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllActiveInstrumentations(Iterable<? extends ActiveInstrumentationProto> values) {
        ensureActiveInstrumentationsIsMutable();
        AbstractMessageLite.addAll(values, this.activeInstrumentations_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearActiveInstrumentations() {
        this.activeInstrumentations_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeActiveInstrumentations(int index) {
        ensureActiveInstrumentationsIsMutable();
        this.activeInstrumentations_.remove(index);
    }

    @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
    public List<UidRecordProto> getActiveUidsList() {
        return this.activeUids_;
    }

    public List<? extends UidRecordProtoOrBuilder> getActiveUidsOrBuilderList() {
        return this.activeUids_;
    }

    @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
    public int getActiveUidsCount() {
        return this.activeUids_.size();
    }

    @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
    public UidRecordProto getActiveUids(int index) {
        return this.activeUids_.get(index);
    }

    public UidRecordProtoOrBuilder getActiveUidsOrBuilder(int index) {
        return this.activeUids_.get(index);
    }

    private void ensureActiveUidsIsMutable() {
        if (!this.activeUids_.isModifiable()) {
            this.activeUids_ = GeneratedMessageLite.mutableCopy(this.activeUids_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setActiveUids(int index, UidRecordProto value) {
        if (value != null) {
            ensureActiveUidsIsMutable();
            this.activeUids_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setActiveUids(int index, UidRecordProto.Builder builderForValue) {
        ensureActiveUidsIsMutable();
        this.activeUids_.set(index, (UidRecordProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addActiveUids(UidRecordProto value) {
        if (value != null) {
            ensureActiveUidsIsMutable();
            this.activeUids_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addActiveUids(int index, UidRecordProto value) {
        if (value != null) {
            ensureActiveUidsIsMutable();
            this.activeUids_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addActiveUids(UidRecordProto.Builder builderForValue) {
        ensureActiveUidsIsMutable();
        this.activeUids_.add((UidRecordProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addActiveUids(int index, UidRecordProto.Builder builderForValue) {
        ensureActiveUidsIsMutable();
        this.activeUids_.add(index, (UidRecordProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllActiveUids(Iterable<? extends UidRecordProto> values) {
        ensureActiveUidsIsMutable();
        AbstractMessageLite.addAll(values, this.activeUids_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearActiveUids() {
        this.activeUids_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeActiveUids(int index) {
        ensureActiveUidsIsMutable();
        this.activeUids_.remove(index);
    }

    @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
    public List<UidRecordProto> getValidateUidsList() {
        return this.validateUids_;
    }

    public List<? extends UidRecordProtoOrBuilder> getValidateUidsOrBuilderList() {
        return this.validateUids_;
    }

    @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
    public int getValidateUidsCount() {
        return this.validateUids_.size();
    }

    @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
    public UidRecordProto getValidateUids(int index) {
        return this.validateUids_.get(index);
    }

    public UidRecordProtoOrBuilder getValidateUidsOrBuilder(int index) {
        return this.validateUids_.get(index);
    }

    private void ensureValidateUidsIsMutable() {
        if (!this.validateUids_.isModifiable()) {
            this.validateUids_ = GeneratedMessageLite.mutableCopy(this.validateUids_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setValidateUids(int index, UidRecordProto value) {
        if (value != null) {
            ensureValidateUidsIsMutable();
            this.validateUids_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setValidateUids(int index, UidRecordProto.Builder builderForValue) {
        ensureValidateUidsIsMutable();
        this.validateUids_.set(index, (UidRecordProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addValidateUids(UidRecordProto value) {
        if (value != null) {
            ensureValidateUidsIsMutable();
            this.validateUids_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addValidateUids(int index, UidRecordProto value) {
        if (value != null) {
            ensureValidateUidsIsMutable();
            this.validateUids_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addValidateUids(UidRecordProto.Builder builderForValue) {
        ensureValidateUidsIsMutable();
        this.validateUids_.add((UidRecordProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addValidateUids(int index, UidRecordProto.Builder builderForValue) {
        ensureValidateUidsIsMutable();
        this.validateUids_.add(index, (UidRecordProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllValidateUids(Iterable<? extends UidRecordProto> values) {
        ensureValidateUidsIsMutable();
        AbstractMessageLite.addAll(values, this.validateUids_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearValidateUids() {
        this.validateUids_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeValidateUids(int index) {
        ensureValidateUidsIsMutable();
        this.validateUids_.remove(index);
    }

    @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
    public boolean hasLruProcs() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
    public LruProcesses getLruProcs() {
        LruProcesses lruProcesses = this.lruProcs_;
        return lruProcesses == null ? LruProcesses.getDefaultInstance() : lruProcesses;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setLruProcs(LruProcesses value) {
        if (value != null) {
            this.lruProcs_ = value;
            this.bitField0_ |= 1;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setLruProcs(LruProcesses.Builder builderForValue) {
        this.lruProcs_ = (LruProcesses) builderForValue.build();
        this.bitField0_ |= 1;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeLruProcs(LruProcesses value) {
        LruProcesses lruProcesses = this.lruProcs_;
        if (lruProcesses == null || lruProcesses == LruProcesses.getDefaultInstance()) {
            this.lruProcs_ = value;
        } else {
            this.lruProcs_ = (LruProcesses) ((LruProcesses.Builder) LruProcesses.newBuilder(this.lruProcs_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 1;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearLruProcs() {
        this.lruProcs_ = null;
        this.bitField0_ &= -2;
    }

    @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
    public List<ProcessRecordProto> getPidsSelfLockedList() {
        return this.pidsSelfLocked_;
    }

    public List<? extends ProcessRecordProtoOrBuilder> getPidsSelfLockedOrBuilderList() {
        return this.pidsSelfLocked_;
    }

    @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
    public int getPidsSelfLockedCount() {
        return this.pidsSelfLocked_.size();
    }

    @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
    public ProcessRecordProto getPidsSelfLocked(int index) {
        return this.pidsSelfLocked_.get(index);
    }

    public ProcessRecordProtoOrBuilder getPidsSelfLockedOrBuilder(int index) {
        return this.pidsSelfLocked_.get(index);
    }

    private void ensurePidsSelfLockedIsMutable() {
        if (!this.pidsSelfLocked_.isModifiable()) {
            this.pidsSelfLocked_ = GeneratedMessageLite.mutableCopy(this.pidsSelfLocked_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPidsSelfLocked(int index, ProcessRecordProto value) {
        if (value != null) {
            ensurePidsSelfLockedIsMutable();
            this.pidsSelfLocked_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPidsSelfLocked(int index, ProcessRecordProto.Builder builderForValue) {
        ensurePidsSelfLockedIsMutable();
        this.pidsSelfLocked_.set(index, (ProcessRecordProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addPidsSelfLocked(ProcessRecordProto value) {
        if (value != null) {
            ensurePidsSelfLockedIsMutable();
            this.pidsSelfLocked_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addPidsSelfLocked(int index, ProcessRecordProto value) {
        if (value != null) {
            ensurePidsSelfLockedIsMutable();
            this.pidsSelfLocked_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addPidsSelfLocked(ProcessRecordProto.Builder builderForValue) {
        ensurePidsSelfLockedIsMutable();
        this.pidsSelfLocked_.add((ProcessRecordProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addPidsSelfLocked(int index, ProcessRecordProto.Builder builderForValue) {
        ensurePidsSelfLockedIsMutable();
        this.pidsSelfLocked_.add(index, (ProcessRecordProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllPidsSelfLocked(Iterable<? extends ProcessRecordProto> values) {
        ensurePidsSelfLockedIsMutable();
        AbstractMessageLite.addAll(values, this.pidsSelfLocked_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearPidsSelfLocked() {
        this.pidsSelfLocked_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removePidsSelfLocked(int index) {
        ensurePidsSelfLockedIsMutable();
        this.pidsSelfLocked_.remove(index);
    }

    @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
    public List<ImportanceTokenProto> getImportantProcsList() {
        return this.importantProcs_;
    }

    public List<? extends ImportanceTokenProtoOrBuilder> getImportantProcsOrBuilderList() {
        return this.importantProcs_;
    }

    @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
    public int getImportantProcsCount() {
        return this.importantProcs_.size();
    }

    @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
    public ImportanceTokenProto getImportantProcs(int index) {
        return this.importantProcs_.get(index);
    }

    public ImportanceTokenProtoOrBuilder getImportantProcsOrBuilder(int index) {
        return this.importantProcs_.get(index);
    }

    private void ensureImportantProcsIsMutable() {
        if (!this.importantProcs_.isModifiable()) {
            this.importantProcs_ = GeneratedMessageLite.mutableCopy(this.importantProcs_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setImportantProcs(int index, ImportanceTokenProto value) {
        if (value != null) {
            ensureImportantProcsIsMutable();
            this.importantProcs_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setImportantProcs(int index, ImportanceTokenProto.Builder builderForValue) {
        ensureImportantProcsIsMutable();
        this.importantProcs_.set(index, (ImportanceTokenProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addImportantProcs(ImportanceTokenProto value) {
        if (value != null) {
            ensureImportantProcsIsMutable();
            this.importantProcs_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addImportantProcs(int index, ImportanceTokenProto value) {
        if (value != null) {
            ensureImportantProcsIsMutable();
            this.importantProcs_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addImportantProcs(ImportanceTokenProto.Builder builderForValue) {
        ensureImportantProcsIsMutable();
        this.importantProcs_.add((ImportanceTokenProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addImportantProcs(int index, ImportanceTokenProto.Builder builderForValue) {
        ensureImportantProcsIsMutable();
        this.importantProcs_.add(index, (ImportanceTokenProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllImportantProcs(Iterable<? extends ImportanceTokenProto> values) {
        ensureImportantProcsIsMutable();
        AbstractMessageLite.addAll(values, this.importantProcs_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearImportantProcs() {
        this.importantProcs_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeImportantProcs(int index) {
        ensureImportantProcsIsMutable();
        this.importantProcs_.remove(index);
    }

    @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
    public List<ProcessRecordProto> getPersistentStartingProcsList() {
        return this.persistentStartingProcs_;
    }

    public List<? extends ProcessRecordProtoOrBuilder> getPersistentStartingProcsOrBuilderList() {
        return this.persistentStartingProcs_;
    }

    @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
    public int getPersistentStartingProcsCount() {
        return this.persistentStartingProcs_.size();
    }

    @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
    public ProcessRecordProto getPersistentStartingProcs(int index) {
        return this.persistentStartingProcs_.get(index);
    }

    public ProcessRecordProtoOrBuilder getPersistentStartingProcsOrBuilder(int index) {
        return this.persistentStartingProcs_.get(index);
    }

    private void ensurePersistentStartingProcsIsMutable() {
        if (!this.persistentStartingProcs_.isModifiable()) {
            this.persistentStartingProcs_ = GeneratedMessageLite.mutableCopy(this.persistentStartingProcs_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPersistentStartingProcs(int index, ProcessRecordProto value) {
        if (value != null) {
            ensurePersistentStartingProcsIsMutable();
            this.persistentStartingProcs_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPersistentStartingProcs(int index, ProcessRecordProto.Builder builderForValue) {
        ensurePersistentStartingProcsIsMutable();
        this.persistentStartingProcs_.set(index, (ProcessRecordProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addPersistentStartingProcs(ProcessRecordProto value) {
        if (value != null) {
            ensurePersistentStartingProcsIsMutable();
            this.persistentStartingProcs_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addPersistentStartingProcs(int index, ProcessRecordProto value) {
        if (value != null) {
            ensurePersistentStartingProcsIsMutable();
            this.persistentStartingProcs_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addPersistentStartingProcs(ProcessRecordProto.Builder builderForValue) {
        ensurePersistentStartingProcsIsMutable();
        this.persistentStartingProcs_.add((ProcessRecordProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addPersistentStartingProcs(int index, ProcessRecordProto.Builder builderForValue) {
        ensurePersistentStartingProcsIsMutable();
        this.persistentStartingProcs_.add(index, (ProcessRecordProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllPersistentStartingProcs(Iterable<? extends ProcessRecordProto> values) {
        ensurePersistentStartingProcsIsMutable();
        AbstractMessageLite.addAll(values, this.persistentStartingProcs_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearPersistentStartingProcs() {
        this.persistentStartingProcs_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removePersistentStartingProcs(int index) {
        ensurePersistentStartingProcsIsMutable();
        this.persistentStartingProcs_.remove(index);
    }

    @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
    public List<ProcessRecordProto> getRemovedProcsList() {
        return this.removedProcs_;
    }

    public List<? extends ProcessRecordProtoOrBuilder> getRemovedProcsOrBuilderList() {
        return this.removedProcs_;
    }

    @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
    public int getRemovedProcsCount() {
        return this.removedProcs_.size();
    }

    @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
    public ProcessRecordProto getRemovedProcs(int index) {
        return this.removedProcs_.get(index);
    }

    public ProcessRecordProtoOrBuilder getRemovedProcsOrBuilder(int index) {
        return this.removedProcs_.get(index);
    }

    private void ensureRemovedProcsIsMutable() {
        if (!this.removedProcs_.isModifiable()) {
            this.removedProcs_ = GeneratedMessageLite.mutableCopy(this.removedProcs_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setRemovedProcs(int index, ProcessRecordProto value) {
        if (value != null) {
            ensureRemovedProcsIsMutable();
            this.removedProcs_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setRemovedProcs(int index, ProcessRecordProto.Builder builderForValue) {
        ensureRemovedProcsIsMutable();
        this.removedProcs_.set(index, (ProcessRecordProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addRemovedProcs(ProcessRecordProto value) {
        if (value != null) {
            ensureRemovedProcsIsMutable();
            this.removedProcs_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addRemovedProcs(int index, ProcessRecordProto value) {
        if (value != null) {
            ensureRemovedProcsIsMutable();
            this.removedProcs_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addRemovedProcs(ProcessRecordProto.Builder builderForValue) {
        ensureRemovedProcsIsMutable();
        this.removedProcs_.add((ProcessRecordProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addRemovedProcs(int index, ProcessRecordProto.Builder builderForValue) {
        ensureRemovedProcsIsMutable();
        this.removedProcs_.add(index, (ProcessRecordProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllRemovedProcs(Iterable<? extends ProcessRecordProto> values) {
        ensureRemovedProcsIsMutable();
        AbstractMessageLite.addAll(values, this.removedProcs_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearRemovedProcs() {
        this.removedProcs_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeRemovedProcs(int index) {
        ensureRemovedProcsIsMutable();
        this.removedProcs_.remove(index);
    }

    @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
    public List<ProcessRecordProto> getOnHoldProcsList() {
        return this.onHoldProcs_;
    }

    public List<? extends ProcessRecordProtoOrBuilder> getOnHoldProcsOrBuilderList() {
        return this.onHoldProcs_;
    }

    @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
    public int getOnHoldProcsCount() {
        return this.onHoldProcs_.size();
    }

    @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
    public ProcessRecordProto getOnHoldProcs(int index) {
        return this.onHoldProcs_.get(index);
    }

    public ProcessRecordProtoOrBuilder getOnHoldProcsOrBuilder(int index) {
        return this.onHoldProcs_.get(index);
    }

    private void ensureOnHoldProcsIsMutable() {
        if (!this.onHoldProcs_.isModifiable()) {
            this.onHoldProcs_ = GeneratedMessageLite.mutableCopy(this.onHoldProcs_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setOnHoldProcs(int index, ProcessRecordProto value) {
        if (value != null) {
            ensureOnHoldProcsIsMutable();
            this.onHoldProcs_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setOnHoldProcs(int index, ProcessRecordProto.Builder builderForValue) {
        ensureOnHoldProcsIsMutable();
        this.onHoldProcs_.set(index, (ProcessRecordProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addOnHoldProcs(ProcessRecordProto value) {
        if (value != null) {
            ensureOnHoldProcsIsMutable();
            this.onHoldProcs_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addOnHoldProcs(int index, ProcessRecordProto value) {
        if (value != null) {
            ensureOnHoldProcsIsMutable();
            this.onHoldProcs_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addOnHoldProcs(ProcessRecordProto.Builder builderForValue) {
        ensureOnHoldProcsIsMutable();
        this.onHoldProcs_.add((ProcessRecordProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addOnHoldProcs(int index, ProcessRecordProto.Builder builderForValue) {
        ensureOnHoldProcsIsMutable();
        this.onHoldProcs_.add(index, (ProcessRecordProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllOnHoldProcs(Iterable<? extends ProcessRecordProto> values) {
        ensureOnHoldProcsIsMutable();
        AbstractMessageLite.addAll(values, this.onHoldProcs_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearOnHoldProcs() {
        this.onHoldProcs_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeOnHoldProcs(int index) {
        ensureOnHoldProcsIsMutable();
        this.onHoldProcs_.remove(index);
    }

    @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
    public List<ProcessToGcProto> getGcProcsList() {
        return this.gcProcs_;
    }

    public List<? extends ProcessToGcProtoOrBuilder> getGcProcsOrBuilderList() {
        return this.gcProcs_;
    }

    @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
    public int getGcProcsCount() {
        return this.gcProcs_.size();
    }

    @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
    public ProcessToGcProto getGcProcs(int index) {
        return this.gcProcs_.get(index);
    }

    public ProcessToGcProtoOrBuilder getGcProcsOrBuilder(int index) {
        return this.gcProcs_.get(index);
    }

    private void ensureGcProcsIsMutable() {
        if (!this.gcProcs_.isModifiable()) {
            this.gcProcs_ = GeneratedMessageLite.mutableCopy(this.gcProcs_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setGcProcs(int index, ProcessToGcProto value) {
        if (value != null) {
            ensureGcProcsIsMutable();
            this.gcProcs_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setGcProcs(int index, ProcessToGcProto.Builder builderForValue) {
        ensureGcProcsIsMutable();
        this.gcProcs_.set(index, (ProcessToGcProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addGcProcs(ProcessToGcProto value) {
        if (value != null) {
            ensureGcProcsIsMutable();
            this.gcProcs_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addGcProcs(int index, ProcessToGcProto value) {
        if (value != null) {
            ensureGcProcsIsMutable();
            this.gcProcs_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addGcProcs(ProcessToGcProto.Builder builderForValue) {
        ensureGcProcsIsMutable();
        this.gcProcs_.add((ProcessToGcProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addGcProcs(int index, ProcessToGcProto.Builder builderForValue) {
        ensureGcProcsIsMutable();
        this.gcProcs_.add(index, (ProcessToGcProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllGcProcs(Iterable<? extends ProcessToGcProto> values) {
        ensureGcProcsIsMutable();
        AbstractMessageLite.addAll(values, this.gcProcs_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearGcProcs() {
        this.gcProcs_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeGcProcs(int index) {
        ensureGcProcsIsMutable();
        this.gcProcs_.remove(index);
    }

    @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
    public boolean hasAppErrors() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
    public AppErrorsProto getAppErrors() {
        AppErrorsProto appErrorsProto = this.appErrors_;
        return appErrorsProto == null ? AppErrorsProto.getDefaultInstance() : appErrorsProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setAppErrors(AppErrorsProto value) {
        if (value != null) {
            this.appErrors_ = value;
            this.bitField0_ |= 2;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setAppErrors(AppErrorsProto.Builder builderForValue) {
        this.appErrors_ = (AppErrorsProto) builderForValue.build();
        this.bitField0_ |= 2;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeAppErrors(AppErrorsProto value) {
        AppErrorsProto appErrorsProto = this.appErrors_;
        if (appErrorsProto == null || appErrorsProto == AppErrorsProto.getDefaultInstance()) {
            this.appErrors_ = value;
        } else {
            this.appErrors_ = (AppErrorsProto) ((AppErrorsProto.Builder) AppErrorsProto.newBuilder(this.appErrors_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 2;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearAppErrors() {
        this.appErrors_ = null;
        this.bitField0_ &= -3;
    }

    @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
    public boolean hasUserController() {
        return (this.bitField0_ & 4) == 4;
    }

    @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
    public UserControllerProto getUserController() {
        UserControllerProto userControllerProto = this.userController_;
        return userControllerProto == null ? UserControllerProto.getDefaultInstance() : userControllerProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setUserController(UserControllerProto value) {
        if (value != null) {
            this.userController_ = value;
            this.bitField0_ |= 4;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setUserController(UserControllerProto.Builder builderForValue) {
        this.userController_ = (UserControllerProto) builderForValue.build();
        this.bitField0_ |= 4;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeUserController(UserControllerProto value) {
        UserControllerProto userControllerProto = this.userController_;
        if (userControllerProto == null || userControllerProto == UserControllerProto.getDefaultInstance()) {
            this.userController_ = value;
        } else {
            this.userController_ = (UserControllerProto) ((UserControllerProto.Builder) UserControllerProto.newBuilder(this.userController_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 4;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearUserController() {
        this.userController_ = null;
        this.bitField0_ &= -5;
    }

    @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
    public boolean hasHomeProc() {
        return (this.bitField0_ & 8) == 8;
    }

    @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
    public ProcessRecordProto getHomeProc() {
        ProcessRecordProto processRecordProto = this.homeProc_;
        return processRecordProto == null ? ProcessRecordProto.getDefaultInstance() : processRecordProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setHomeProc(ProcessRecordProto value) {
        if (value != null) {
            this.homeProc_ = value;
            this.bitField0_ |= 8;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setHomeProc(ProcessRecordProto.Builder builderForValue) {
        this.homeProc_ = (ProcessRecordProto) builderForValue.build();
        this.bitField0_ |= 8;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeHomeProc(ProcessRecordProto value) {
        ProcessRecordProto processRecordProto = this.homeProc_;
        if (processRecordProto == null || processRecordProto == ProcessRecordProto.getDefaultInstance()) {
            this.homeProc_ = value;
        } else {
            this.homeProc_ = (ProcessRecordProto) ((ProcessRecordProto.Builder) ProcessRecordProto.newBuilder(this.homeProc_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 8;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearHomeProc() {
        this.homeProc_ = null;
        this.bitField0_ &= -9;
    }

    @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
    public boolean hasPreviousProc() {
        return (this.bitField0_ & 16) == 16;
    }

    @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
    public ProcessRecordProto getPreviousProc() {
        ProcessRecordProto processRecordProto = this.previousProc_;
        return processRecordProto == null ? ProcessRecordProto.getDefaultInstance() : processRecordProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPreviousProc(ProcessRecordProto value) {
        if (value != null) {
            this.previousProc_ = value;
            this.bitField0_ |= 16;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPreviousProc(ProcessRecordProto.Builder builderForValue) {
        this.previousProc_ = (ProcessRecordProto) builderForValue.build();
        this.bitField0_ |= 16;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergePreviousProc(ProcessRecordProto value) {
        ProcessRecordProto processRecordProto = this.previousProc_;
        if (processRecordProto == null || processRecordProto == ProcessRecordProto.getDefaultInstance()) {
            this.previousProc_ = value;
        } else {
            this.previousProc_ = (ProcessRecordProto) ((ProcessRecordProto.Builder) ProcessRecordProto.newBuilder(this.previousProc_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 16;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearPreviousProc() {
        this.previousProc_ = null;
        this.bitField0_ &= -17;
    }

    @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
    public boolean hasPreviousProcVisibleTimeMs() {
        return (this.bitField0_ & 32) == 32;
    }

    @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
    public long getPreviousProcVisibleTimeMs() {
        return this.previousProcVisibleTimeMs_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPreviousProcVisibleTimeMs(long value) {
        this.bitField0_ |= 32;
        this.previousProcVisibleTimeMs_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearPreviousProcVisibleTimeMs() {
        this.bitField0_ &= -33;
        this.previousProcVisibleTimeMs_ = 0;
    }

    @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
    public boolean hasHeavyWeightProc() {
        return (this.bitField0_ & 64) == 64;
    }

    @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
    public ProcessRecordProto getHeavyWeightProc() {
        ProcessRecordProto processRecordProto = this.heavyWeightProc_;
        return processRecordProto == null ? ProcessRecordProto.getDefaultInstance() : processRecordProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setHeavyWeightProc(ProcessRecordProto value) {
        if (value != null) {
            this.heavyWeightProc_ = value;
            this.bitField0_ |= 64;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setHeavyWeightProc(ProcessRecordProto.Builder builderForValue) {
        this.heavyWeightProc_ = (ProcessRecordProto) builderForValue.build();
        this.bitField0_ |= 64;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeHeavyWeightProc(ProcessRecordProto value) {
        ProcessRecordProto processRecordProto = this.heavyWeightProc_;
        if (processRecordProto == null || processRecordProto == ProcessRecordProto.getDefaultInstance()) {
            this.heavyWeightProc_ = value;
        } else {
            this.heavyWeightProc_ = (ProcessRecordProto) ((ProcessRecordProto.Builder) ProcessRecordProto.newBuilder(this.heavyWeightProc_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 64;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearHeavyWeightProc() {
        this.heavyWeightProc_ = null;
        this.bitField0_ &= -65;
    }

    @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
    public boolean hasGlobalConfiguration() {
        return (this.bitField0_ & 128) == 128;
    }

    @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
    public ConfigurationProto getGlobalConfiguration() {
        ConfigurationProto configurationProto = this.globalConfiguration_;
        return configurationProto == null ? ConfigurationProto.getDefaultInstance() : configurationProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setGlobalConfiguration(ConfigurationProto value) {
        if (value != null) {
            this.globalConfiguration_ = value;
            this.bitField0_ |= 128;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setGlobalConfiguration(ConfigurationProto.Builder builderForValue) {
        this.globalConfiguration_ = (ConfigurationProto) builderForValue.build();
        this.bitField0_ |= 128;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeGlobalConfiguration(ConfigurationProto value) {
        ConfigurationProto configurationProto = this.globalConfiguration_;
        if (configurationProto == null || configurationProto == ConfigurationProto.getDefaultInstance()) {
            this.globalConfiguration_ = value;
        } else {
            this.globalConfiguration_ = (ConfigurationProto) ((ConfigurationProto.Builder) ConfigurationProto.newBuilder(this.globalConfiguration_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 128;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearGlobalConfiguration() {
        this.globalConfiguration_ = null;
        this.bitField0_ &= -129;
    }

    @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
    public boolean hasConfigWillChange() {
        return (this.bitField0_ & 256) == 256;
    }

    @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
    public boolean getConfigWillChange() {
        return this.configWillChange_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setConfigWillChange(boolean value) {
        this.bitField0_ |= 256;
        this.configWillChange_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearConfigWillChange() {
        this.bitField0_ &= -257;
        this.configWillChange_ = false;
    }

    @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
    public List<ScreenCompatPackage> getScreenCompatPackagesList() {
        return this.screenCompatPackages_;
    }

    public List<? extends ScreenCompatPackageOrBuilder> getScreenCompatPackagesOrBuilderList() {
        return this.screenCompatPackages_;
    }

    @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
    public int getScreenCompatPackagesCount() {
        return this.screenCompatPackages_.size();
    }

    @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
    public ScreenCompatPackage getScreenCompatPackages(int index) {
        return this.screenCompatPackages_.get(index);
    }

    public ScreenCompatPackageOrBuilder getScreenCompatPackagesOrBuilder(int index) {
        return this.screenCompatPackages_.get(index);
    }

    private void ensureScreenCompatPackagesIsMutable() {
        if (!this.screenCompatPackages_.isModifiable()) {
            this.screenCompatPackages_ = GeneratedMessageLite.mutableCopy(this.screenCompatPackages_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setScreenCompatPackages(int index, ScreenCompatPackage value) {
        if (value != null) {
            ensureScreenCompatPackagesIsMutable();
            this.screenCompatPackages_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setScreenCompatPackages(int index, ScreenCompatPackage.Builder builderForValue) {
        ensureScreenCompatPackagesIsMutable();
        this.screenCompatPackages_.set(index, (ScreenCompatPackage) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addScreenCompatPackages(ScreenCompatPackage value) {
        if (value != null) {
            ensureScreenCompatPackagesIsMutable();
            this.screenCompatPackages_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addScreenCompatPackages(int index, ScreenCompatPackage value) {
        if (value != null) {
            ensureScreenCompatPackagesIsMutable();
            this.screenCompatPackages_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addScreenCompatPackages(ScreenCompatPackage.Builder builderForValue) {
        ensureScreenCompatPackagesIsMutable();
        this.screenCompatPackages_.add((ScreenCompatPackage) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addScreenCompatPackages(int index, ScreenCompatPackage.Builder builderForValue) {
        ensureScreenCompatPackagesIsMutable();
        this.screenCompatPackages_.add(index, (ScreenCompatPackage) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllScreenCompatPackages(Iterable<? extends ScreenCompatPackage> values) {
        ensureScreenCompatPackagesIsMutable();
        AbstractMessageLite.addAll(values, this.screenCompatPackages_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearScreenCompatPackages() {
        this.screenCompatPackages_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeScreenCompatPackages(int index) {
        ensureScreenCompatPackagesIsMutable();
        this.screenCompatPackages_.remove(index);
    }

    @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
    public List<UidObserverRegistrationProto> getUidObserversList() {
        return this.uidObservers_;
    }

    public List<? extends UidObserverRegistrationProtoOrBuilder> getUidObserversOrBuilderList() {
        return this.uidObservers_;
    }

    @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
    public int getUidObserversCount() {
        return this.uidObservers_.size();
    }

    @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
    public UidObserverRegistrationProto getUidObservers(int index) {
        return this.uidObservers_.get(index);
    }

    public UidObserverRegistrationProtoOrBuilder getUidObserversOrBuilder(int index) {
        return this.uidObservers_.get(index);
    }

    private void ensureUidObserversIsMutable() {
        if (!this.uidObservers_.isModifiable()) {
            this.uidObservers_ = GeneratedMessageLite.mutableCopy(this.uidObservers_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setUidObservers(int index, UidObserverRegistrationProto value) {
        if (value != null) {
            ensureUidObserversIsMutable();
            this.uidObservers_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setUidObservers(int index, UidObserverRegistrationProto.Builder builderForValue) {
        ensureUidObserversIsMutable();
        this.uidObservers_.set(index, (UidObserverRegistrationProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addUidObservers(UidObserverRegistrationProto value) {
        if (value != null) {
            ensureUidObserversIsMutable();
            this.uidObservers_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addUidObservers(int index, UidObserverRegistrationProto value) {
        if (value != null) {
            ensureUidObserversIsMutable();
            this.uidObservers_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addUidObservers(UidObserverRegistrationProto.Builder builderForValue) {
        ensureUidObserversIsMutable();
        this.uidObservers_.add((UidObserverRegistrationProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addUidObservers(int index, UidObserverRegistrationProto.Builder builderForValue) {
        ensureUidObserversIsMutable();
        this.uidObservers_.add(index, (UidObserverRegistrationProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllUidObservers(Iterable<? extends UidObserverRegistrationProto> values) {
        ensureUidObserversIsMutable();
        AbstractMessageLite.addAll(values, this.uidObservers_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearUidObservers() {
        this.uidObservers_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeUidObservers(int index) {
        ensureUidObserversIsMutable();
        this.uidObservers_.remove(index);
    }

    @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
    public List<Integer> getDeviceIdleWhitelistList() {
        return this.deviceIdleWhitelist_;
    }

    @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
    public int getDeviceIdleWhitelistCount() {
        return this.deviceIdleWhitelist_.size();
    }

    @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
    public int getDeviceIdleWhitelist(int index) {
        return this.deviceIdleWhitelist_.getInt(index);
    }

    private void ensureDeviceIdleWhitelistIsMutable() {
        if (!this.deviceIdleWhitelist_.isModifiable()) {
            this.deviceIdleWhitelist_ = GeneratedMessageLite.mutableCopy(this.deviceIdleWhitelist_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setDeviceIdleWhitelist(int index, int value) {
        ensureDeviceIdleWhitelistIsMutable();
        this.deviceIdleWhitelist_.setInt(index, value);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addDeviceIdleWhitelist(int value) {
        ensureDeviceIdleWhitelistIsMutable();
        this.deviceIdleWhitelist_.addInt(value);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllDeviceIdleWhitelist(Iterable<? extends Integer> values) {
        ensureDeviceIdleWhitelistIsMutable();
        AbstractMessageLite.addAll(values, this.deviceIdleWhitelist_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearDeviceIdleWhitelist() {
        this.deviceIdleWhitelist_ = emptyIntList();
    }

    @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
    public List<Integer> getDeviceIdleTempWhitelistList() {
        return this.deviceIdleTempWhitelist_;
    }

    @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
    public int getDeviceIdleTempWhitelistCount() {
        return this.deviceIdleTempWhitelist_.size();
    }

    @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
    public int getDeviceIdleTempWhitelist(int index) {
        return this.deviceIdleTempWhitelist_.getInt(index);
    }

    private void ensureDeviceIdleTempWhitelistIsMutable() {
        if (!this.deviceIdleTempWhitelist_.isModifiable()) {
            this.deviceIdleTempWhitelist_ = GeneratedMessageLite.mutableCopy(this.deviceIdleTempWhitelist_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setDeviceIdleTempWhitelist(int index, int value) {
        ensureDeviceIdleTempWhitelistIsMutable();
        this.deviceIdleTempWhitelist_.setInt(index, value);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addDeviceIdleTempWhitelist(int value) {
        ensureDeviceIdleTempWhitelistIsMutable();
        this.deviceIdleTempWhitelist_.addInt(value);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllDeviceIdleTempWhitelist(Iterable<? extends Integer> values) {
        ensureDeviceIdleTempWhitelistIsMutable();
        AbstractMessageLite.addAll(values, this.deviceIdleTempWhitelist_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearDeviceIdleTempWhitelist() {
        this.deviceIdleTempWhitelist_ = emptyIntList();
    }

    @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
    public List<PendingTempWhitelist> getPendingTempWhitelistList() {
        return this.pendingTempWhitelist_;
    }

    public List<? extends PendingTempWhitelistOrBuilder> getPendingTempWhitelistOrBuilderList() {
        return this.pendingTempWhitelist_;
    }

    @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
    public int getPendingTempWhitelistCount() {
        return this.pendingTempWhitelist_.size();
    }

    @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
    public PendingTempWhitelist getPendingTempWhitelist(int index) {
        return this.pendingTempWhitelist_.get(index);
    }

    public PendingTempWhitelistOrBuilder getPendingTempWhitelistOrBuilder(int index) {
        return this.pendingTempWhitelist_.get(index);
    }

    private void ensurePendingTempWhitelistIsMutable() {
        if (!this.pendingTempWhitelist_.isModifiable()) {
            this.pendingTempWhitelist_ = GeneratedMessageLite.mutableCopy(this.pendingTempWhitelist_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPendingTempWhitelist(int index, PendingTempWhitelist value) {
        if (value != null) {
            ensurePendingTempWhitelistIsMutable();
            this.pendingTempWhitelist_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPendingTempWhitelist(int index, PendingTempWhitelist.Builder builderForValue) {
        ensurePendingTempWhitelistIsMutable();
        this.pendingTempWhitelist_.set(index, (PendingTempWhitelist) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addPendingTempWhitelist(PendingTempWhitelist value) {
        if (value != null) {
            ensurePendingTempWhitelistIsMutable();
            this.pendingTempWhitelist_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addPendingTempWhitelist(int index, PendingTempWhitelist value) {
        if (value != null) {
            ensurePendingTempWhitelistIsMutable();
            this.pendingTempWhitelist_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addPendingTempWhitelist(PendingTempWhitelist.Builder builderForValue) {
        ensurePendingTempWhitelistIsMutable();
        this.pendingTempWhitelist_.add((PendingTempWhitelist) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addPendingTempWhitelist(int index, PendingTempWhitelist.Builder builderForValue) {
        ensurePendingTempWhitelistIsMutable();
        this.pendingTempWhitelist_.add(index, (PendingTempWhitelist) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllPendingTempWhitelist(Iterable<? extends PendingTempWhitelist> values) {
        ensurePendingTempWhitelistIsMutable();
        AbstractMessageLite.addAll(values, this.pendingTempWhitelist_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearPendingTempWhitelist() {
        this.pendingTempWhitelist_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removePendingTempWhitelist(int index) {
        ensurePendingTempWhitelistIsMutable();
        this.pendingTempWhitelist_.remove(index);
    }

    @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
    public boolean hasSleepStatus() {
        return (this.bitField0_ & 512) == 512;
    }

    @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
    public SleepStatus getSleepStatus() {
        SleepStatus sleepStatus = this.sleepStatus_;
        return sleepStatus == null ? SleepStatus.getDefaultInstance() : sleepStatus;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setSleepStatus(SleepStatus value) {
        if (value != null) {
            this.sleepStatus_ = value;
            this.bitField0_ |= 512;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setSleepStatus(SleepStatus.Builder builderForValue) {
        this.sleepStatus_ = (SleepStatus) builderForValue.build();
        this.bitField0_ |= 512;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeSleepStatus(SleepStatus value) {
        SleepStatus sleepStatus = this.sleepStatus_;
        if (sleepStatus == null || sleepStatus == SleepStatus.getDefaultInstance()) {
            this.sleepStatus_ = value;
        } else {
            this.sleepStatus_ = (SleepStatus) ((SleepStatus.Builder) SleepStatus.newBuilder(this.sleepStatus_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 512;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearSleepStatus() {
        this.sleepStatus_ = null;
        this.bitField0_ &= -513;
    }

    @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
    public boolean hasRunningVoice() {
        return (this.bitField0_ & 1024) == 1024;
    }

    @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
    public Voice getRunningVoice() {
        Voice voice = this.runningVoice_;
        return voice == null ? Voice.getDefaultInstance() : voice;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setRunningVoice(Voice value) {
        if (value != null) {
            this.runningVoice_ = value;
            this.bitField0_ |= 1024;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setRunningVoice(Voice.Builder builderForValue) {
        this.runningVoice_ = (Voice) builderForValue.build();
        this.bitField0_ |= 1024;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeRunningVoice(Voice value) {
        Voice voice = this.runningVoice_;
        if (voice == null || voice == Voice.getDefaultInstance()) {
            this.runningVoice_ = value;
        } else {
            this.runningVoice_ = (Voice) ((Voice.Builder) Voice.newBuilder(this.runningVoice_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 1024;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearRunningVoice() {
        this.runningVoice_ = null;
        this.bitField0_ &= -1025;
    }

    @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
    public boolean hasVrController() {
        return (this.bitField0_ & 2048) == 2048;
    }

    @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
    public VrControllerProto getVrController() {
        VrControllerProto vrControllerProto = this.vrController_;
        return vrControllerProto == null ? VrControllerProto.getDefaultInstance() : vrControllerProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setVrController(VrControllerProto value) {
        if (value != null) {
            this.vrController_ = value;
            this.bitField0_ |= 2048;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setVrController(VrControllerProto.Builder builderForValue) {
        this.vrController_ = (VrControllerProto) builderForValue.build();
        this.bitField0_ |= 2048;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeVrController(VrControllerProto value) {
        VrControllerProto vrControllerProto = this.vrController_;
        if (vrControllerProto == null || vrControllerProto == VrControllerProto.getDefaultInstance()) {
            this.vrController_ = value;
        } else {
            this.vrController_ = (VrControllerProto) ((VrControllerProto.Builder) VrControllerProto.newBuilder(this.vrController_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 2048;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearVrController() {
        this.vrController_ = null;
        this.bitField0_ &= -2049;
    }

    @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
    public boolean hasDebug() {
        return (this.bitField0_ & 4096) == 4096;
    }

    @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
    public DebugApp getDebug() {
        DebugApp debugApp = this.debug_;
        return debugApp == null ? DebugApp.getDefaultInstance() : debugApp;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setDebug(DebugApp value) {
        if (value != null) {
            this.debug_ = value;
            this.bitField0_ |= 4096;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setDebug(DebugApp.Builder builderForValue) {
        this.debug_ = (DebugApp) builderForValue.build();
        this.bitField0_ |= 4096;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeDebug(DebugApp value) {
        DebugApp debugApp = this.debug_;
        if (debugApp == null || debugApp == DebugApp.getDefaultInstance()) {
            this.debug_ = value;
        } else {
            this.debug_ = (DebugApp) ((DebugApp.Builder) DebugApp.newBuilder(this.debug_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 4096;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearDebug() {
        this.debug_ = null;
        this.bitField0_ &= -4097;
    }

    @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
    public boolean hasCurrentTracker() {
        return (this.bitField0_ & 8192) == 8192;
    }

    @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
    public AppTimeTrackerProto getCurrentTracker() {
        AppTimeTrackerProto appTimeTrackerProto = this.currentTracker_;
        return appTimeTrackerProto == null ? AppTimeTrackerProto.getDefaultInstance() : appTimeTrackerProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setCurrentTracker(AppTimeTrackerProto value) {
        if (value != null) {
            this.currentTracker_ = value;
            this.bitField0_ |= 8192;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setCurrentTracker(AppTimeTrackerProto.Builder builderForValue) {
        this.currentTracker_ = (AppTimeTrackerProto) builderForValue.build();
        this.bitField0_ |= 8192;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeCurrentTracker(AppTimeTrackerProto value) {
        AppTimeTrackerProto appTimeTrackerProto = this.currentTracker_;
        if (appTimeTrackerProto == null || appTimeTrackerProto == AppTimeTrackerProto.getDefaultInstance()) {
            this.currentTracker_ = value;
        } else {
            this.currentTracker_ = (AppTimeTrackerProto) ((AppTimeTrackerProto.Builder) AppTimeTrackerProto.newBuilder(this.currentTracker_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 8192;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearCurrentTracker() {
        this.currentTracker_ = null;
        this.bitField0_ &= -8193;
    }

    @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
    public boolean hasMemWatchProcesses() {
        return (this.bitField0_ & 16384) == 16384;
    }

    @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
    public MemWatchProcess getMemWatchProcesses() {
        MemWatchProcess memWatchProcess = this.memWatchProcesses_;
        return memWatchProcess == null ? MemWatchProcess.getDefaultInstance() : memWatchProcess;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setMemWatchProcesses(MemWatchProcess value) {
        if (value != null) {
            this.memWatchProcesses_ = value;
            this.bitField0_ |= 16384;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setMemWatchProcesses(MemWatchProcess.Builder builderForValue) {
        this.memWatchProcesses_ = (MemWatchProcess) builderForValue.build();
        this.bitField0_ |= 16384;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeMemWatchProcesses(MemWatchProcess value) {
        MemWatchProcess memWatchProcess = this.memWatchProcesses_;
        if (memWatchProcess == null || memWatchProcess == MemWatchProcess.getDefaultInstance()) {
            this.memWatchProcesses_ = value;
        } else {
            this.memWatchProcesses_ = (MemWatchProcess) ((MemWatchProcess.Builder) MemWatchProcess.newBuilder(this.memWatchProcesses_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 16384;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearMemWatchProcesses() {
        this.memWatchProcesses_ = null;
        this.bitField0_ &= -16385;
    }

    @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
    public boolean hasTrackAllocationApp() {
        return (this.bitField0_ & 32768) == 32768;
    }

    @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
    public String getTrackAllocationApp() {
        return this.trackAllocationApp_;
    }

    @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
    public ByteString getTrackAllocationAppBytes() {
        return ByteString.copyFromUtf8(this.trackAllocationApp_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setTrackAllocationApp(String value) {
        if (value != null) {
            this.bitField0_ |= 32768;
            this.trackAllocationApp_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearTrackAllocationApp() {
        this.bitField0_ &= -32769;
        this.trackAllocationApp_ = getDefaultInstance().getTrackAllocationApp();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setTrackAllocationAppBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 32768;
            this.trackAllocationApp_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
    public boolean hasProfile() {
        return (this.bitField0_ & 65536) == 65536;
    }

    @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
    public Profile getProfile() {
        Profile profile = this.profile_;
        return profile == null ? Profile.getDefaultInstance() : profile;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setProfile(Profile value) {
        if (value != null) {
            this.profile_ = value;
            this.bitField0_ |= 65536;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setProfile(Profile.Builder builderForValue) {
        this.profile_ = (Profile) builderForValue.build();
        this.bitField0_ |= 65536;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeProfile(Profile value) {
        Profile profile = this.profile_;
        if (profile == null || profile == Profile.getDefaultInstance()) {
            this.profile_ = value;
        } else {
            this.profile_ = (Profile) ((Profile.Builder) Profile.newBuilder(this.profile_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 65536;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearProfile() {
        this.profile_ = null;
        this.bitField0_ &= -65537;
    }

    @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
    public boolean hasNativeDebuggingApp() {
        return (this.bitField0_ & 131072) == 131072;
    }

    @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
    public String getNativeDebuggingApp() {
        return this.nativeDebuggingApp_;
    }

    @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
    public ByteString getNativeDebuggingAppBytes() {
        return ByteString.copyFromUtf8(this.nativeDebuggingApp_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setNativeDebuggingApp(String value) {
        if (value != null) {
            this.bitField0_ |= 131072;
            this.nativeDebuggingApp_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearNativeDebuggingApp() {
        this.bitField0_ &= -131073;
        this.nativeDebuggingApp_ = getDefaultInstance().getNativeDebuggingApp();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setNativeDebuggingAppBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 131072;
            this.nativeDebuggingApp_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
    public boolean hasAlwaysFinishActivities() {
        return (this.bitField0_ & 262144) == 262144;
    }

    @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
    public boolean getAlwaysFinishActivities() {
        return this.alwaysFinishActivities_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setAlwaysFinishActivities(boolean value) {
        this.bitField0_ |= 262144;
        this.alwaysFinishActivities_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearAlwaysFinishActivities() {
        this.bitField0_ &= -262145;
        this.alwaysFinishActivities_ = false;
    }

    @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
    public boolean hasController() {
        return (this.bitField0_ & 524288) == 524288;
    }

    @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
    public Controller getController() {
        Controller controller = this.controller_;
        return controller == null ? Controller.getDefaultInstance() : controller;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setController(Controller value) {
        if (value != null) {
            this.controller_ = value;
            this.bitField0_ |= 524288;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setController(Controller.Builder builderForValue) {
        this.controller_ = (Controller) builderForValue.build();
        this.bitField0_ |= 524288;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeController(Controller value) {
        Controller controller = this.controller_;
        if (controller == null || controller == Controller.getDefaultInstance()) {
            this.controller_ = value;
        } else {
            this.controller_ = (Controller) ((Controller.Builder) Controller.newBuilder(this.controller_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 524288;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearController() {
        this.controller_ = null;
        this.bitField0_ &= -524289;
    }

    @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
    public boolean hasTotalPersistentProcs() {
        return (this.bitField0_ & 1048576) == 1048576;
    }

    @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
    public int getTotalPersistentProcs() {
        return this.totalPersistentProcs_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setTotalPersistentProcs(int value) {
        this.bitField0_ |= 1048576;
        this.totalPersistentProcs_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearTotalPersistentProcs() {
        this.bitField0_ &= -1048577;
        this.totalPersistentProcs_ = 0;
    }

    @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
    public boolean hasProcessesReady() {
        return (this.bitField0_ & 2097152) == 2097152;
    }

    @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
    public boolean getProcessesReady() {
        return this.processesReady_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setProcessesReady(boolean value) {
        this.bitField0_ |= 2097152;
        this.processesReady_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearProcessesReady() {
        this.bitField0_ &= -2097153;
        this.processesReady_ = false;
    }

    @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
    public boolean hasSystemReady() {
        return (this.bitField0_ & 4194304) == 4194304;
    }

    @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
    public boolean getSystemReady() {
        return this.systemReady_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setSystemReady(boolean value) {
        this.bitField0_ |= 4194304;
        this.systemReady_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearSystemReady() {
        this.bitField0_ &= -4194305;
        this.systemReady_ = false;
    }

    @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
    public boolean hasBooted() {
        return (this.bitField0_ & 8388608) == 8388608;
    }

    @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
    public boolean getBooted() {
        return this.booted_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setBooted(boolean value) {
        this.bitField0_ |= 8388608;
        this.booted_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearBooted() {
        this.bitField0_ &= -8388609;
        this.booted_ = false;
    }

    @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
    public boolean hasFactoryTest() {
        return (this.bitField0_ & 16777216) == 16777216;
    }

    @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
    public int getFactoryTest() {
        return this.factoryTest_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setFactoryTest(int value) {
        this.bitField0_ |= 16777216;
        this.factoryTest_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearFactoryTest() {
        this.bitField0_ &= -16777217;
        this.factoryTest_ = 0;
    }

    @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
    public boolean hasBooting() {
        return (this.bitField0_ & 33554432) == 33554432;
    }

    @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
    public boolean getBooting() {
        return this.booting_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setBooting(boolean value) {
        this.bitField0_ |= 33554432;
        this.booting_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearBooting() {
        this.bitField0_ &= -33554433;
        this.booting_ = false;
    }

    @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
    public boolean hasCallFinishBooting() {
        return (this.bitField0_ & 67108864) == 67108864;
    }

    @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
    public boolean getCallFinishBooting() {
        return this.callFinishBooting_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setCallFinishBooting(boolean value) {
        this.bitField0_ |= 67108864;
        this.callFinishBooting_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearCallFinishBooting() {
        this.bitField0_ &= -67108865;
        this.callFinishBooting_ = false;
    }

    @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
    public boolean hasBootAnimationComplete() {
        return (this.bitField0_ & 134217728) == 134217728;
    }

    @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
    public boolean getBootAnimationComplete() {
        return this.bootAnimationComplete_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setBootAnimationComplete(boolean value) {
        this.bitField0_ |= 134217728;
        this.bootAnimationComplete_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearBootAnimationComplete() {
        this.bitField0_ &= -134217729;
        this.bootAnimationComplete_ = false;
    }

    @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
    public boolean hasLastPowerCheckUptimeMs() {
        return (this.bitField0_ & 268435456) == 268435456;
    }

    @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
    public long getLastPowerCheckUptimeMs() {
        return this.lastPowerCheckUptimeMs_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setLastPowerCheckUptimeMs(long value) {
        this.bitField0_ |= 268435456;
        this.lastPowerCheckUptimeMs_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearLastPowerCheckUptimeMs() {
        this.bitField0_ &= -268435457;
        this.lastPowerCheckUptimeMs_ = 0;
    }

    @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
    public boolean hasGoingToSleep() {
        return (this.bitField0_ & 536870912) == 536870912;
    }

    @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
    public PowerManagerProto.WakeLock getGoingToSleep() {
        PowerManagerProto.WakeLock wakeLock = this.goingToSleep_;
        return wakeLock == null ? PowerManagerProto.WakeLock.getDefaultInstance() : wakeLock;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setGoingToSleep(PowerManagerProto.WakeLock value) {
        if (value != null) {
            this.goingToSleep_ = value;
            this.bitField0_ |= 536870912;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setGoingToSleep(PowerManagerProto.WakeLock.Builder builderForValue) {
        this.goingToSleep_ = (PowerManagerProto.WakeLock) builderForValue.build();
        this.bitField0_ |= 536870912;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeGoingToSleep(PowerManagerProto.WakeLock value) {
        PowerManagerProto.WakeLock wakeLock = this.goingToSleep_;
        if (wakeLock == null || wakeLock == PowerManagerProto.WakeLock.getDefaultInstance()) {
            this.goingToSleep_ = value;
        } else {
            this.goingToSleep_ = (PowerManagerProto.WakeLock) ((PowerManagerProto.WakeLock.Builder) PowerManagerProto.WakeLock.newBuilder(this.goingToSleep_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 536870912;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearGoingToSleep() {
        this.goingToSleep_ = null;
        this.bitField0_ &= -536870913;
    }

    @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
    public boolean hasLaunchingActivity() {
        return (this.bitField0_ & 1073741824) == 1073741824;
    }

    @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
    public PowerManagerProto.WakeLock getLaunchingActivity() {
        PowerManagerProto.WakeLock wakeLock = this.launchingActivity_;
        return wakeLock == null ? PowerManagerProto.WakeLock.getDefaultInstance() : wakeLock;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setLaunchingActivity(PowerManagerProto.WakeLock value) {
        if (value != null) {
            this.launchingActivity_ = value;
            this.bitField0_ |= 1073741824;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setLaunchingActivity(PowerManagerProto.WakeLock.Builder builderForValue) {
        this.launchingActivity_ = (PowerManagerProto.WakeLock) builderForValue.build();
        this.bitField0_ |= 1073741824;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeLaunchingActivity(PowerManagerProto.WakeLock value) {
        PowerManagerProto.WakeLock wakeLock = this.launchingActivity_;
        if (wakeLock == null || wakeLock == PowerManagerProto.WakeLock.getDefaultInstance()) {
            this.launchingActivity_ = value;
        } else {
            this.launchingActivity_ = (PowerManagerProto.WakeLock) ((PowerManagerProto.WakeLock.Builder) PowerManagerProto.WakeLock.newBuilder(this.launchingActivity_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 1073741824;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearLaunchingActivity() {
        this.launchingActivity_ = null;
        this.bitField0_ &= -1073741825;
    }

    @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
    public boolean hasAdjSeq() {
        return (this.bitField0_ & Integer.MIN_VALUE) == Integer.MIN_VALUE;
    }

    @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
    public int getAdjSeq() {
        return this.adjSeq_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setAdjSeq(int value) {
        this.bitField0_ |= Integer.MIN_VALUE;
        this.adjSeq_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearAdjSeq() {
        this.bitField0_ &= DataConnectionPowerStateEnum.DATA_CONNECTION_POWER_STATE_UNKNOWN_VALUE;
        this.adjSeq_ = 0;
    }

    @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
    public boolean hasLruSeq() {
        return (this.bitField1_ & 1) == 1;
    }

    @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
    public int getLruSeq() {
        return this.lruSeq_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setLruSeq(int value) {
        this.bitField1_ |= 1;
        this.lruSeq_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearLruSeq() {
        this.bitField1_ &= -2;
        this.lruSeq_ = 0;
    }

    @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
    public boolean hasNumNonCachedProcs() {
        return (this.bitField1_ & 2) == 2;
    }

    @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
    public int getNumNonCachedProcs() {
        return this.numNonCachedProcs_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setNumNonCachedProcs(int value) {
        this.bitField1_ |= 2;
        this.numNonCachedProcs_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearNumNonCachedProcs() {
        this.bitField1_ &= -3;
        this.numNonCachedProcs_ = 0;
    }

    @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
    public boolean hasNumCachedHiddenProcs() {
        return (this.bitField1_ & 4) == 4;
    }

    @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
    public int getNumCachedHiddenProcs() {
        return this.numCachedHiddenProcs_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setNumCachedHiddenProcs(int value) {
        this.bitField1_ |= 4;
        this.numCachedHiddenProcs_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearNumCachedHiddenProcs() {
        this.bitField1_ &= -5;
        this.numCachedHiddenProcs_ = 0;
    }

    @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
    public boolean hasNumServiceProcs() {
        return (this.bitField1_ & 8) == 8;
    }

    @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
    public int getNumServiceProcs() {
        return this.numServiceProcs_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setNumServiceProcs(int value) {
        this.bitField1_ |= 8;
        this.numServiceProcs_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearNumServiceProcs() {
        this.bitField1_ &= -9;
        this.numServiceProcs_ = 0;
    }

    @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
    public boolean hasNewNumServiceProcs() {
        return (this.bitField1_ & 16) == 16;
    }

    @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
    public int getNewNumServiceProcs() {
        return this.newNumServiceProcs_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setNewNumServiceProcs(int value) {
        this.bitField1_ |= 16;
        this.newNumServiceProcs_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearNewNumServiceProcs() {
        this.bitField1_ &= -17;
        this.newNumServiceProcs_ = 0;
    }

    @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
    public boolean hasAllowLowerMemLevel() {
        return (this.bitField1_ & 32) == 32;
    }

    @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
    public boolean getAllowLowerMemLevel() {
        return this.allowLowerMemLevel_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setAllowLowerMemLevel(boolean value) {
        this.bitField1_ |= 32;
        this.allowLowerMemLevel_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearAllowLowerMemLevel() {
        this.bitField1_ &= -33;
        this.allowLowerMemLevel_ = false;
    }

    @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
    public boolean hasLastMemoryLevel() {
        return (this.bitField1_ & 64) == 64;
    }

    @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
    public int getLastMemoryLevel() {
        return this.lastMemoryLevel_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setLastMemoryLevel(int value) {
        this.bitField1_ |= 64;
        this.lastMemoryLevel_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearLastMemoryLevel() {
        this.bitField1_ &= -65;
        this.lastMemoryLevel_ = 0;
    }

    @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
    public boolean hasLastNumProcesses() {
        return (this.bitField1_ & 128) == 128;
    }

    @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
    public int getLastNumProcesses() {
        return this.lastNumProcesses_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setLastNumProcesses(int value) {
        this.bitField1_ |= 128;
        this.lastNumProcesses_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearLastNumProcesses() {
        this.bitField1_ &= -129;
        this.lastNumProcesses_ = 0;
    }

    @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
    public boolean hasLastIdleTime() {
        return (this.bitField1_ & 256) == 256;
    }

    @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
    public Duration getLastIdleTime() {
        Duration duration = this.lastIdleTime_;
        return duration == null ? Duration.getDefaultInstance() : duration;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setLastIdleTime(Duration value) {
        if (value != null) {
            this.lastIdleTime_ = value;
            this.bitField1_ |= 256;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setLastIdleTime(Duration.Builder builderForValue) {
        this.lastIdleTime_ = (Duration) builderForValue.build();
        this.bitField1_ |= 256;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeLastIdleTime(Duration value) {
        Duration duration = this.lastIdleTime_;
        if (duration == null || duration == Duration.getDefaultInstance()) {
            this.lastIdleTime_ = value;
        } else {
            this.lastIdleTime_ = (Duration) ((Duration.Builder) Duration.newBuilder(this.lastIdleTime_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField1_ |= 256;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearLastIdleTime() {
        this.lastIdleTime_ = null;
        this.bitField1_ &= -257;
    }

    @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
    public boolean hasLowRamSinceLastIdleMs() {
        return (this.bitField1_ & 512) == 512;
    }

    @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
    public long getLowRamSinceLastIdleMs() {
        return this.lowRamSinceLastIdleMs_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setLowRamSinceLastIdleMs(long value) {
        this.bitField1_ |= 512;
        this.lowRamSinceLastIdleMs_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearLowRamSinceLastIdleMs() {
        this.bitField1_ &= -513;
        this.lowRamSinceLastIdleMs_ = 0;
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        for (int i = 0; i < this.procs_.size(); i++) {
            output.writeMessage(1, this.procs_.get(i));
        }
        for (int i2 = 0; i2 < this.isolatedProcs_.size(); i2++) {
            output.writeMessage(2, this.isolatedProcs_.get(i2));
        }
        for (int i3 = 0; i3 < this.activeInstrumentations_.size(); i3++) {
            output.writeMessage(3, this.activeInstrumentations_.get(i3));
        }
        for (int i4 = 0; i4 < this.activeUids_.size(); i4++) {
            output.writeMessage(4, this.activeUids_.get(i4));
        }
        for (int i5 = 0; i5 < this.validateUids_.size(); i5++) {
            output.writeMessage(5, this.validateUids_.get(i5));
        }
        if ((this.bitField0_ & 1) == 1) {
            output.writeMessage(6, getLruProcs());
        }
        for (int i6 = 0; i6 < this.pidsSelfLocked_.size(); i6++) {
            output.writeMessage(7, this.pidsSelfLocked_.get(i6));
        }
        for (int i7 = 0; i7 < this.importantProcs_.size(); i7++) {
            output.writeMessage(8, this.importantProcs_.get(i7));
        }
        for (int i8 = 0; i8 < this.persistentStartingProcs_.size(); i8++) {
            output.writeMessage(9, this.persistentStartingProcs_.get(i8));
        }
        for (int i9 = 0; i9 < this.removedProcs_.size(); i9++) {
            output.writeMessage(10, this.removedProcs_.get(i9));
        }
        for (int i10 = 0; i10 < this.onHoldProcs_.size(); i10++) {
            output.writeMessage(11, this.onHoldProcs_.get(i10));
        }
        for (int i11 = 0; i11 < this.gcProcs_.size(); i11++) {
            output.writeMessage(12, this.gcProcs_.get(i11));
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeMessage(13, getAppErrors());
        }
        if ((this.bitField0_ & 4) == 4) {
            output.writeMessage(14, getUserController());
        }
        if ((this.bitField0_ & 8) == 8) {
            output.writeMessage(15, getHomeProc());
        }
        if ((this.bitField0_ & 16) == 16) {
            output.writeMessage(16, getPreviousProc());
        }
        if ((this.bitField0_ & 32) == 32) {
            output.writeInt64(17, this.previousProcVisibleTimeMs_);
        }
        if ((this.bitField0_ & 64) == 64) {
            output.writeMessage(18, getHeavyWeightProc());
        }
        if ((this.bitField0_ & 128) == 128) {
            output.writeMessage(19, getGlobalConfiguration());
        }
        if ((this.bitField0_ & 256) == 256) {
            output.writeBool(21, this.configWillChange_);
        }
        for (int i12 = 0; i12 < this.screenCompatPackages_.size(); i12++) {
            output.writeMessage(22, this.screenCompatPackages_.get(i12));
        }
        for (int i13 = 0; i13 < this.uidObservers_.size(); i13++) {
            output.writeMessage(23, this.uidObservers_.get(i13));
        }
        for (int i14 = 0; i14 < this.deviceIdleWhitelist_.size(); i14++) {
            output.writeInt32(24, this.deviceIdleWhitelist_.getInt(i14));
        }
        for (int i15 = 0; i15 < this.deviceIdleTempWhitelist_.size(); i15++) {
            output.writeInt32(25, this.deviceIdleTempWhitelist_.getInt(i15));
        }
        for (int i16 = 0; i16 < this.pendingTempWhitelist_.size(); i16++) {
            output.writeMessage(26, this.pendingTempWhitelist_.get(i16));
        }
        if ((this.bitField0_ & 512) == 512) {
            output.writeMessage(27, getSleepStatus());
        }
        if ((this.bitField0_ & 1024) == 1024) {
            output.writeMessage(28, getRunningVoice());
        }
        if ((this.bitField0_ & 2048) == 2048) {
            output.writeMessage(29, getVrController());
        }
        if ((this.bitField0_ & 4096) == 4096) {
            output.writeMessage(30, getDebug());
        }
        if ((this.bitField0_ & 8192) == 8192) {
            output.writeMessage(31, getCurrentTracker());
        }
        if ((this.bitField0_ & 16384) == 16384) {
            output.writeMessage(32, getMemWatchProcesses());
        }
        if ((this.bitField0_ & 32768) == 32768) {
            output.writeString(33, getTrackAllocationApp());
        }
        if ((this.bitField0_ & 65536) == 65536) {
            output.writeMessage(34, getProfile());
        }
        if ((this.bitField0_ & 131072) == 131072) {
            output.writeString(35, getNativeDebuggingApp());
        }
        if ((this.bitField0_ & 262144) == 262144) {
            output.writeBool(36, this.alwaysFinishActivities_);
        }
        if ((this.bitField0_ & 524288) == 524288) {
            output.writeMessage(37, getController());
        }
        if ((this.bitField0_ & 1048576) == 1048576) {
            output.writeInt32(38, this.totalPersistentProcs_);
        }
        if ((this.bitField0_ & 2097152) == 2097152) {
            output.writeBool(39, this.processesReady_);
        }
        if ((this.bitField0_ & 4194304) == 4194304) {
            output.writeBool(40, this.systemReady_);
        }
        if ((this.bitField0_ & 8388608) == 8388608) {
            output.writeBool(41, this.booted_);
        }
        if ((this.bitField0_ & 16777216) == 16777216) {
            output.writeInt32(42, this.factoryTest_);
        }
        if ((this.bitField0_ & 33554432) == 33554432) {
            output.writeBool(43, this.booting_);
        }
        if ((this.bitField0_ & 67108864) == 67108864) {
            output.writeBool(44, this.callFinishBooting_);
        }
        if ((this.bitField0_ & 134217728) == 134217728) {
            output.writeBool(45, this.bootAnimationComplete_);
        }
        if ((this.bitField0_ & 268435456) == 268435456) {
            output.writeInt64(46, this.lastPowerCheckUptimeMs_);
        }
        if ((this.bitField0_ & 536870912) == 536870912) {
            output.writeMessage(47, getGoingToSleep());
        }
        if ((this.bitField0_ & 1073741824) == 1073741824) {
            output.writeMessage(48, getLaunchingActivity());
        }
        if ((this.bitField0_ & Integer.MIN_VALUE) == Integer.MIN_VALUE) {
            output.writeInt32(49, this.adjSeq_);
        }
        if ((this.bitField1_ & 1) == 1) {
            output.writeInt32(50, this.lruSeq_);
        }
        if ((this.bitField1_ & 2) == 2) {
            output.writeInt32(51, this.numNonCachedProcs_);
        }
        if ((this.bitField1_ & 4) == 4) {
            output.writeInt32(52, this.numCachedHiddenProcs_);
        }
        if ((this.bitField1_ & 8) == 8) {
            output.writeInt32(53, this.numServiceProcs_);
        }
        if ((this.bitField1_ & 16) == 16) {
            output.writeInt32(54, this.newNumServiceProcs_);
        }
        if ((this.bitField1_ & 32) == 32) {
            output.writeBool(55, this.allowLowerMemLevel_);
        }
        if ((this.bitField1_ & 64) == 64) {
            output.writeInt32(56, this.lastMemoryLevel_);
        }
        if ((this.bitField1_ & 128) == 128) {
            output.writeInt32(57, this.lastNumProcesses_);
        }
        if ((this.bitField1_ & 256) == 256) {
            output.writeMessage(58, getLastIdleTime());
        }
        if ((this.bitField1_ & 512) == 512) {
            output.writeInt64(59, this.lowRamSinceLastIdleMs_);
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
        for (int i = 0; i < this.procs_.size(); i++) {
            size2 += CodedOutputStream.computeMessageSize(1, this.procs_.get(i));
        }
        for (int i2 = 0; i2 < this.isolatedProcs_.size(); i2++) {
            size2 += CodedOutputStream.computeMessageSize(2, this.isolatedProcs_.get(i2));
        }
        for (int i3 = 0; i3 < this.activeInstrumentations_.size(); i3++) {
            size2 += CodedOutputStream.computeMessageSize(3, this.activeInstrumentations_.get(i3));
        }
        for (int i4 = 0; i4 < this.activeUids_.size(); i4++) {
            size2 += CodedOutputStream.computeMessageSize(4, this.activeUids_.get(i4));
        }
        for (int i5 = 0; i5 < this.validateUids_.size(); i5++) {
            size2 += CodedOutputStream.computeMessageSize(5, this.validateUids_.get(i5));
        }
        if ((this.bitField0_ & 1) == 1) {
            size2 += CodedOutputStream.computeMessageSize(6, getLruProcs());
        }
        for (int i6 = 0; i6 < this.pidsSelfLocked_.size(); i6++) {
            size2 += CodedOutputStream.computeMessageSize(7, this.pidsSelfLocked_.get(i6));
        }
        for (int i7 = 0; i7 < this.importantProcs_.size(); i7++) {
            size2 += CodedOutputStream.computeMessageSize(8, this.importantProcs_.get(i7));
        }
        for (int i8 = 0; i8 < this.persistentStartingProcs_.size(); i8++) {
            size2 += CodedOutputStream.computeMessageSize(9, this.persistentStartingProcs_.get(i8));
        }
        for (int i9 = 0; i9 < this.removedProcs_.size(); i9++) {
            size2 += CodedOutputStream.computeMessageSize(10, this.removedProcs_.get(i9));
        }
        for (int i10 = 0; i10 < this.onHoldProcs_.size(); i10++) {
            size2 += CodedOutputStream.computeMessageSize(11, this.onHoldProcs_.get(i10));
        }
        for (int i11 = 0; i11 < this.gcProcs_.size(); i11++) {
            size2 += CodedOutputStream.computeMessageSize(12, this.gcProcs_.get(i11));
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeMessageSize(13, getAppErrors());
        }
        if ((this.bitField0_ & 4) == 4) {
            size2 += CodedOutputStream.computeMessageSize(14, getUserController());
        }
        if ((this.bitField0_ & 8) == 8) {
            size2 += CodedOutputStream.computeMessageSize(15, getHomeProc());
        }
        if ((this.bitField0_ & 16) == 16) {
            size2 += CodedOutputStream.computeMessageSize(16, getPreviousProc());
        }
        if ((this.bitField0_ & 32) == 32) {
            size2 += CodedOutputStream.computeInt64Size(17, this.previousProcVisibleTimeMs_);
        }
        if ((this.bitField0_ & 64) == 64) {
            size2 += CodedOutputStream.computeMessageSize(18, getHeavyWeightProc());
        }
        if ((this.bitField0_ & 128) == 128) {
            size2 += CodedOutputStream.computeMessageSize(19, getGlobalConfiguration());
        }
        if ((this.bitField0_ & 256) == 256) {
            size2 += CodedOutputStream.computeBoolSize(21, this.configWillChange_);
        }
        for (int i12 = 0; i12 < this.screenCompatPackages_.size(); i12++) {
            size2 += CodedOutputStream.computeMessageSize(22, this.screenCompatPackages_.get(i12));
        }
        for (int i13 = 0; i13 < this.uidObservers_.size(); i13++) {
            size2 += CodedOutputStream.computeMessageSize(23, this.uidObservers_.get(i13));
        }
        int dataSize = 0;
        for (int i14 = 0; i14 < this.deviceIdleWhitelist_.size(); i14++) {
            dataSize += CodedOutputStream.computeInt32SizeNoTag(this.deviceIdleWhitelist_.getInt(i14));
        }
        int size3 = size2 + dataSize + (getDeviceIdleWhitelistList().size() * 2);
        int dataSize2 = 0;
        for (int i15 = 0; i15 < this.deviceIdleTempWhitelist_.size(); i15++) {
            dataSize2 += CodedOutputStream.computeInt32SizeNoTag(this.deviceIdleTempWhitelist_.getInt(i15));
        }
        int size4 = size3 + dataSize2 + (getDeviceIdleTempWhitelistList().size() * 2);
        for (int i16 = 0; i16 < this.pendingTempWhitelist_.size(); i16++) {
            size4 += CodedOutputStream.computeMessageSize(26, this.pendingTempWhitelist_.get(i16));
        }
        if ((this.bitField0_ & 512) == 512) {
            size4 += CodedOutputStream.computeMessageSize(27, getSleepStatus());
        }
        if ((this.bitField0_ & 1024) == 1024) {
            size4 += CodedOutputStream.computeMessageSize(28, getRunningVoice());
        }
        if ((this.bitField0_ & 2048) == 2048) {
            size4 += CodedOutputStream.computeMessageSize(29, getVrController());
        }
        if ((this.bitField0_ & 4096) == 4096) {
            size4 += CodedOutputStream.computeMessageSize(30, getDebug());
        }
        if ((this.bitField0_ & 8192) == 8192) {
            size4 += CodedOutputStream.computeMessageSize(31, getCurrentTracker());
        }
        if ((this.bitField0_ & 16384) == 16384) {
            size4 += CodedOutputStream.computeMessageSize(32, getMemWatchProcesses());
        }
        if ((this.bitField0_ & 32768) == 32768) {
            size4 += CodedOutputStream.computeStringSize(33, getTrackAllocationApp());
        }
        if ((this.bitField0_ & 65536) == 65536) {
            size4 += CodedOutputStream.computeMessageSize(34, getProfile());
        }
        if ((this.bitField0_ & 131072) == 131072) {
            size4 += CodedOutputStream.computeStringSize(35, getNativeDebuggingApp());
        }
        if ((this.bitField0_ & 262144) == 262144) {
            size4 += CodedOutputStream.computeBoolSize(36, this.alwaysFinishActivities_);
        }
        if ((this.bitField0_ & 524288) == 524288) {
            size4 += CodedOutputStream.computeMessageSize(37, getController());
        }
        if ((this.bitField0_ & 1048576) == 1048576) {
            size4 += CodedOutputStream.computeInt32Size(38, this.totalPersistentProcs_);
        }
        if ((this.bitField0_ & 2097152) == 2097152) {
            size4 += CodedOutputStream.computeBoolSize(39, this.processesReady_);
        }
        if ((this.bitField0_ & 4194304) == 4194304) {
            size4 += CodedOutputStream.computeBoolSize(40, this.systemReady_);
        }
        if ((this.bitField0_ & 8388608) == 8388608) {
            size4 += CodedOutputStream.computeBoolSize(41, this.booted_);
        }
        if ((this.bitField0_ & 16777216) == 16777216) {
            size4 += CodedOutputStream.computeInt32Size(42, this.factoryTest_);
        }
        if ((this.bitField0_ & 33554432) == 33554432) {
            size4 += CodedOutputStream.computeBoolSize(43, this.booting_);
        }
        if ((this.bitField0_ & 67108864) == 67108864) {
            size4 += CodedOutputStream.computeBoolSize(44, this.callFinishBooting_);
        }
        if ((this.bitField0_ & 134217728) == 134217728) {
            size4 += CodedOutputStream.computeBoolSize(45, this.bootAnimationComplete_);
        }
        if ((this.bitField0_ & 268435456) == 268435456) {
            size4 += CodedOutputStream.computeInt64Size(46, this.lastPowerCheckUptimeMs_);
        }
        if ((this.bitField0_ & 536870912) == 536870912) {
            size4 += CodedOutputStream.computeMessageSize(47, getGoingToSleep());
        }
        if ((this.bitField0_ & 1073741824) == 1073741824) {
            size4 += CodedOutputStream.computeMessageSize(48, getLaunchingActivity());
        }
        if ((this.bitField0_ & Integer.MIN_VALUE) == Integer.MIN_VALUE) {
            size4 += CodedOutputStream.computeInt32Size(49, this.adjSeq_);
        }
        if ((this.bitField1_ & 1) == 1) {
            size4 += CodedOutputStream.computeInt32Size(50, this.lruSeq_);
        }
        if ((this.bitField1_ & 2) == 2) {
            size4 += CodedOutputStream.computeInt32Size(51, this.numNonCachedProcs_);
        }
        if ((this.bitField1_ & 4) == 4) {
            size4 += CodedOutputStream.computeInt32Size(52, this.numCachedHiddenProcs_);
        }
        if ((this.bitField1_ & 8) == 8) {
            size4 += CodedOutputStream.computeInt32Size(53, this.numServiceProcs_);
        }
        if ((this.bitField1_ & 16) == 16) {
            size4 += CodedOutputStream.computeInt32Size(54, this.newNumServiceProcs_);
        }
        if ((this.bitField1_ & 32) == 32) {
            size4 += CodedOutputStream.computeBoolSize(55, this.allowLowerMemLevel_);
        }
        if ((this.bitField1_ & 64) == 64) {
            size4 += CodedOutputStream.computeInt32Size(56, this.lastMemoryLevel_);
        }
        if ((this.bitField1_ & 128) == 128) {
            size4 += CodedOutputStream.computeInt32Size(57, this.lastNumProcesses_);
        }
        if ((this.bitField1_ & 256) == 256) {
            size4 += CodedOutputStream.computeMessageSize(58, getLastIdleTime());
        }
        if ((this.bitField1_ & 512) == 512) {
            size4 += CodedOutputStream.computeInt64Size(59, this.lowRamSinceLastIdleMs_);
        }
        int size5 = size4 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size5;
        return size5;
    }

    public static ActivityManagerServiceDumpProcessesProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (ActivityManagerServiceDumpProcessesProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static ActivityManagerServiceDumpProcessesProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (ActivityManagerServiceDumpProcessesProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static ActivityManagerServiceDumpProcessesProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (ActivityManagerServiceDumpProcessesProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static ActivityManagerServiceDumpProcessesProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (ActivityManagerServiceDumpProcessesProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static ActivityManagerServiceDumpProcessesProto parseFrom(InputStream input) throws IOException {
        return (ActivityManagerServiceDumpProcessesProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static ActivityManagerServiceDumpProcessesProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (ActivityManagerServiceDumpProcessesProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static ActivityManagerServiceDumpProcessesProto parseDelimitedFrom(InputStream input) throws IOException {
        return (ActivityManagerServiceDumpProcessesProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static ActivityManagerServiceDumpProcessesProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (ActivityManagerServiceDumpProcessesProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static ActivityManagerServiceDumpProcessesProto parseFrom(CodedInputStream input) throws IOException {
        return (ActivityManagerServiceDumpProcessesProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static ActivityManagerServiceDumpProcessesProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (ActivityManagerServiceDumpProcessesProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(ActivityManagerServiceDumpProcessesProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<ActivityManagerServiceDumpProcessesProto, Builder> implements ActivityManagerServiceDumpProcessesProtoOrBuilder {
        private Builder() {
            super(ActivityManagerServiceDumpProcessesProto.DEFAULT_INSTANCE);
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
        public List<ProcessRecordProto> getProcsList() {
            return Collections.unmodifiableList(((ActivityManagerServiceDumpProcessesProto) this.instance).getProcsList());
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
        public int getProcsCount() {
            return ((ActivityManagerServiceDumpProcessesProto) this.instance).getProcsCount();
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
        public ProcessRecordProto getProcs(int index) {
            return ((ActivityManagerServiceDumpProcessesProto) this.instance).getProcs(index);
        }

        public Builder setProcs(int index, ProcessRecordProto value) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).setProcs((ActivityManagerServiceDumpProcessesProto) index, (int) value);
            return this;
        }

        public Builder setProcs(int index, ProcessRecordProto.Builder builderForValue) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).setProcs((ActivityManagerServiceDumpProcessesProto) index, (int) builderForValue);
            return this;
        }

        public Builder addProcs(ProcessRecordProto value) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).addProcs((ActivityManagerServiceDumpProcessesProto) value);
            return this;
        }

        public Builder addProcs(int index, ProcessRecordProto value) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).addProcs((ActivityManagerServiceDumpProcessesProto) index, (int) value);
            return this;
        }

        public Builder addProcs(ProcessRecordProto.Builder builderForValue) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).addProcs((ActivityManagerServiceDumpProcessesProto) builderForValue);
            return this;
        }

        public Builder addProcs(int index, ProcessRecordProto.Builder builderForValue) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).addProcs((ActivityManagerServiceDumpProcessesProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllProcs(Iterable<? extends ProcessRecordProto> values) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).addAllProcs(values);
            return this;
        }

        public Builder clearProcs() {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).clearProcs();
            return this;
        }

        public Builder removeProcs(int index) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).removeProcs(index);
            return this;
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
        public List<ProcessRecordProto> getIsolatedProcsList() {
            return Collections.unmodifiableList(((ActivityManagerServiceDumpProcessesProto) this.instance).getIsolatedProcsList());
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
        public int getIsolatedProcsCount() {
            return ((ActivityManagerServiceDumpProcessesProto) this.instance).getIsolatedProcsCount();
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
        public ProcessRecordProto getIsolatedProcs(int index) {
            return ((ActivityManagerServiceDumpProcessesProto) this.instance).getIsolatedProcs(index);
        }

        public Builder setIsolatedProcs(int index, ProcessRecordProto value) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).setIsolatedProcs((ActivityManagerServiceDumpProcessesProto) index, (int) value);
            return this;
        }

        public Builder setIsolatedProcs(int index, ProcessRecordProto.Builder builderForValue) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).setIsolatedProcs((ActivityManagerServiceDumpProcessesProto) index, (int) builderForValue);
            return this;
        }

        public Builder addIsolatedProcs(ProcessRecordProto value) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).addIsolatedProcs((ActivityManagerServiceDumpProcessesProto) value);
            return this;
        }

        public Builder addIsolatedProcs(int index, ProcessRecordProto value) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).addIsolatedProcs((ActivityManagerServiceDumpProcessesProto) index, (int) value);
            return this;
        }

        public Builder addIsolatedProcs(ProcessRecordProto.Builder builderForValue) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).addIsolatedProcs((ActivityManagerServiceDumpProcessesProto) builderForValue);
            return this;
        }

        public Builder addIsolatedProcs(int index, ProcessRecordProto.Builder builderForValue) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).addIsolatedProcs((ActivityManagerServiceDumpProcessesProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllIsolatedProcs(Iterable<? extends ProcessRecordProto> values) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).addAllIsolatedProcs(values);
            return this;
        }

        public Builder clearIsolatedProcs() {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).clearIsolatedProcs();
            return this;
        }

        public Builder removeIsolatedProcs(int index) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).removeIsolatedProcs(index);
            return this;
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
        public List<ActiveInstrumentationProto> getActiveInstrumentationsList() {
            return Collections.unmodifiableList(((ActivityManagerServiceDumpProcessesProto) this.instance).getActiveInstrumentationsList());
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
        public int getActiveInstrumentationsCount() {
            return ((ActivityManagerServiceDumpProcessesProto) this.instance).getActiveInstrumentationsCount();
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
        public ActiveInstrumentationProto getActiveInstrumentations(int index) {
            return ((ActivityManagerServiceDumpProcessesProto) this.instance).getActiveInstrumentations(index);
        }

        public Builder setActiveInstrumentations(int index, ActiveInstrumentationProto value) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).setActiveInstrumentations((ActivityManagerServiceDumpProcessesProto) index, (int) value);
            return this;
        }

        public Builder setActiveInstrumentations(int index, ActiveInstrumentationProto.Builder builderForValue) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).setActiveInstrumentations((ActivityManagerServiceDumpProcessesProto) index, (int) builderForValue);
            return this;
        }

        public Builder addActiveInstrumentations(ActiveInstrumentationProto value) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).addActiveInstrumentations((ActivityManagerServiceDumpProcessesProto) value);
            return this;
        }

        public Builder addActiveInstrumentations(int index, ActiveInstrumentationProto value) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).addActiveInstrumentations((ActivityManagerServiceDumpProcessesProto) index, (int) value);
            return this;
        }

        public Builder addActiveInstrumentations(ActiveInstrumentationProto.Builder builderForValue) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).addActiveInstrumentations((ActivityManagerServiceDumpProcessesProto) builderForValue);
            return this;
        }

        public Builder addActiveInstrumentations(int index, ActiveInstrumentationProto.Builder builderForValue) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).addActiveInstrumentations((ActivityManagerServiceDumpProcessesProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllActiveInstrumentations(Iterable<? extends ActiveInstrumentationProto> values) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).addAllActiveInstrumentations(values);
            return this;
        }

        public Builder clearActiveInstrumentations() {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).clearActiveInstrumentations();
            return this;
        }

        public Builder removeActiveInstrumentations(int index) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).removeActiveInstrumentations(index);
            return this;
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
        public List<UidRecordProto> getActiveUidsList() {
            return Collections.unmodifiableList(((ActivityManagerServiceDumpProcessesProto) this.instance).getActiveUidsList());
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
        public int getActiveUidsCount() {
            return ((ActivityManagerServiceDumpProcessesProto) this.instance).getActiveUidsCount();
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
        public UidRecordProto getActiveUids(int index) {
            return ((ActivityManagerServiceDumpProcessesProto) this.instance).getActiveUids(index);
        }

        public Builder setActiveUids(int index, UidRecordProto value) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).setActiveUids((ActivityManagerServiceDumpProcessesProto) index, (int) value);
            return this;
        }

        public Builder setActiveUids(int index, UidRecordProto.Builder builderForValue) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).setActiveUids((ActivityManagerServiceDumpProcessesProto) index, (int) builderForValue);
            return this;
        }

        public Builder addActiveUids(UidRecordProto value) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).addActiveUids((ActivityManagerServiceDumpProcessesProto) value);
            return this;
        }

        public Builder addActiveUids(int index, UidRecordProto value) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).addActiveUids((ActivityManagerServiceDumpProcessesProto) index, (int) value);
            return this;
        }

        public Builder addActiveUids(UidRecordProto.Builder builderForValue) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).addActiveUids((ActivityManagerServiceDumpProcessesProto) builderForValue);
            return this;
        }

        public Builder addActiveUids(int index, UidRecordProto.Builder builderForValue) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).addActiveUids((ActivityManagerServiceDumpProcessesProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllActiveUids(Iterable<? extends UidRecordProto> values) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).addAllActiveUids(values);
            return this;
        }

        public Builder clearActiveUids() {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).clearActiveUids();
            return this;
        }

        public Builder removeActiveUids(int index) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).removeActiveUids(index);
            return this;
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
        public List<UidRecordProto> getValidateUidsList() {
            return Collections.unmodifiableList(((ActivityManagerServiceDumpProcessesProto) this.instance).getValidateUidsList());
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
        public int getValidateUidsCount() {
            return ((ActivityManagerServiceDumpProcessesProto) this.instance).getValidateUidsCount();
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
        public UidRecordProto getValidateUids(int index) {
            return ((ActivityManagerServiceDumpProcessesProto) this.instance).getValidateUids(index);
        }

        public Builder setValidateUids(int index, UidRecordProto value) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).setValidateUids((ActivityManagerServiceDumpProcessesProto) index, (int) value);
            return this;
        }

        public Builder setValidateUids(int index, UidRecordProto.Builder builderForValue) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).setValidateUids((ActivityManagerServiceDumpProcessesProto) index, (int) builderForValue);
            return this;
        }

        public Builder addValidateUids(UidRecordProto value) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).addValidateUids((ActivityManagerServiceDumpProcessesProto) value);
            return this;
        }

        public Builder addValidateUids(int index, UidRecordProto value) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).addValidateUids((ActivityManagerServiceDumpProcessesProto) index, (int) value);
            return this;
        }

        public Builder addValidateUids(UidRecordProto.Builder builderForValue) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).addValidateUids((ActivityManagerServiceDumpProcessesProto) builderForValue);
            return this;
        }

        public Builder addValidateUids(int index, UidRecordProto.Builder builderForValue) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).addValidateUids((ActivityManagerServiceDumpProcessesProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllValidateUids(Iterable<? extends UidRecordProto> values) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).addAllValidateUids(values);
            return this;
        }

        public Builder clearValidateUids() {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).clearValidateUids();
            return this;
        }

        public Builder removeValidateUids(int index) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).removeValidateUids(index);
            return this;
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
        public boolean hasLruProcs() {
            return ((ActivityManagerServiceDumpProcessesProto) this.instance).hasLruProcs();
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
        public LruProcesses getLruProcs() {
            return ((ActivityManagerServiceDumpProcessesProto) this.instance).getLruProcs();
        }

        public Builder setLruProcs(LruProcesses value) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).setLruProcs((ActivityManagerServiceDumpProcessesProto) value);
            return this;
        }

        public Builder setLruProcs(LruProcesses.Builder builderForValue) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).setLruProcs((ActivityManagerServiceDumpProcessesProto) builderForValue);
            return this;
        }

        public Builder mergeLruProcs(LruProcesses value) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).mergeLruProcs(value);
            return this;
        }

        public Builder clearLruProcs() {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).clearLruProcs();
            return this;
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
        public List<ProcessRecordProto> getPidsSelfLockedList() {
            return Collections.unmodifiableList(((ActivityManagerServiceDumpProcessesProto) this.instance).getPidsSelfLockedList());
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
        public int getPidsSelfLockedCount() {
            return ((ActivityManagerServiceDumpProcessesProto) this.instance).getPidsSelfLockedCount();
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
        public ProcessRecordProto getPidsSelfLocked(int index) {
            return ((ActivityManagerServiceDumpProcessesProto) this.instance).getPidsSelfLocked(index);
        }

        public Builder setPidsSelfLocked(int index, ProcessRecordProto value) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).setPidsSelfLocked((ActivityManagerServiceDumpProcessesProto) index, (int) value);
            return this;
        }

        public Builder setPidsSelfLocked(int index, ProcessRecordProto.Builder builderForValue) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).setPidsSelfLocked((ActivityManagerServiceDumpProcessesProto) index, (int) builderForValue);
            return this;
        }

        public Builder addPidsSelfLocked(ProcessRecordProto value) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).addPidsSelfLocked((ActivityManagerServiceDumpProcessesProto) value);
            return this;
        }

        public Builder addPidsSelfLocked(int index, ProcessRecordProto value) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).addPidsSelfLocked((ActivityManagerServiceDumpProcessesProto) index, (int) value);
            return this;
        }

        public Builder addPidsSelfLocked(ProcessRecordProto.Builder builderForValue) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).addPidsSelfLocked((ActivityManagerServiceDumpProcessesProto) builderForValue);
            return this;
        }

        public Builder addPidsSelfLocked(int index, ProcessRecordProto.Builder builderForValue) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).addPidsSelfLocked((ActivityManagerServiceDumpProcessesProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllPidsSelfLocked(Iterable<? extends ProcessRecordProto> values) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).addAllPidsSelfLocked(values);
            return this;
        }

        public Builder clearPidsSelfLocked() {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).clearPidsSelfLocked();
            return this;
        }

        public Builder removePidsSelfLocked(int index) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).removePidsSelfLocked(index);
            return this;
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
        public List<ImportanceTokenProto> getImportantProcsList() {
            return Collections.unmodifiableList(((ActivityManagerServiceDumpProcessesProto) this.instance).getImportantProcsList());
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
        public int getImportantProcsCount() {
            return ((ActivityManagerServiceDumpProcessesProto) this.instance).getImportantProcsCount();
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
        public ImportanceTokenProto getImportantProcs(int index) {
            return ((ActivityManagerServiceDumpProcessesProto) this.instance).getImportantProcs(index);
        }

        public Builder setImportantProcs(int index, ImportanceTokenProto value) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).setImportantProcs((ActivityManagerServiceDumpProcessesProto) index, (int) value);
            return this;
        }

        public Builder setImportantProcs(int index, ImportanceTokenProto.Builder builderForValue) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).setImportantProcs((ActivityManagerServiceDumpProcessesProto) index, (int) builderForValue);
            return this;
        }

        public Builder addImportantProcs(ImportanceTokenProto value) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).addImportantProcs((ActivityManagerServiceDumpProcessesProto) value);
            return this;
        }

        public Builder addImportantProcs(int index, ImportanceTokenProto value) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).addImportantProcs((ActivityManagerServiceDumpProcessesProto) index, (int) value);
            return this;
        }

        public Builder addImportantProcs(ImportanceTokenProto.Builder builderForValue) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).addImportantProcs((ActivityManagerServiceDumpProcessesProto) builderForValue);
            return this;
        }

        public Builder addImportantProcs(int index, ImportanceTokenProto.Builder builderForValue) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).addImportantProcs((ActivityManagerServiceDumpProcessesProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllImportantProcs(Iterable<? extends ImportanceTokenProto> values) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).addAllImportantProcs(values);
            return this;
        }

        public Builder clearImportantProcs() {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).clearImportantProcs();
            return this;
        }

        public Builder removeImportantProcs(int index) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).removeImportantProcs(index);
            return this;
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
        public List<ProcessRecordProto> getPersistentStartingProcsList() {
            return Collections.unmodifiableList(((ActivityManagerServiceDumpProcessesProto) this.instance).getPersistentStartingProcsList());
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
        public int getPersistentStartingProcsCount() {
            return ((ActivityManagerServiceDumpProcessesProto) this.instance).getPersistentStartingProcsCount();
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
        public ProcessRecordProto getPersistentStartingProcs(int index) {
            return ((ActivityManagerServiceDumpProcessesProto) this.instance).getPersistentStartingProcs(index);
        }

        public Builder setPersistentStartingProcs(int index, ProcessRecordProto value) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).setPersistentStartingProcs((ActivityManagerServiceDumpProcessesProto) index, (int) value);
            return this;
        }

        public Builder setPersistentStartingProcs(int index, ProcessRecordProto.Builder builderForValue) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).setPersistentStartingProcs((ActivityManagerServiceDumpProcessesProto) index, (int) builderForValue);
            return this;
        }

        public Builder addPersistentStartingProcs(ProcessRecordProto value) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).addPersistentStartingProcs((ActivityManagerServiceDumpProcessesProto) value);
            return this;
        }

        public Builder addPersistentStartingProcs(int index, ProcessRecordProto value) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).addPersistentStartingProcs((ActivityManagerServiceDumpProcessesProto) index, (int) value);
            return this;
        }

        public Builder addPersistentStartingProcs(ProcessRecordProto.Builder builderForValue) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).addPersistentStartingProcs((ActivityManagerServiceDumpProcessesProto) builderForValue);
            return this;
        }

        public Builder addPersistentStartingProcs(int index, ProcessRecordProto.Builder builderForValue) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).addPersistentStartingProcs((ActivityManagerServiceDumpProcessesProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllPersistentStartingProcs(Iterable<? extends ProcessRecordProto> values) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).addAllPersistentStartingProcs(values);
            return this;
        }

        public Builder clearPersistentStartingProcs() {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).clearPersistentStartingProcs();
            return this;
        }

        public Builder removePersistentStartingProcs(int index) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).removePersistentStartingProcs(index);
            return this;
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
        public List<ProcessRecordProto> getRemovedProcsList() {
            return Collections.unmodifiableList(((ActivityManagerServiceDumpProcessesProto) this.instance).getRemovedProcsList());
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
        public int getRemovedProcsCount() {
            return ((ActivityManagerServiceDumpProcessesProto) this.instance).getRemovedProcsCount();
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
        public ProcessRecordProto getRemovedProcs(int index) {
            return ((ActivityManagerServiceDumpProcessesProto) this.instance).getRemovedProcs(index);
        }

        public Builder setRemovedProcs(int index, ProcessRecordProto value) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).setRemovedProcs((ActivityManagerServiceDumpProcessesProto) index, (int) value);
            return this;
        }

        public Builder setRemovedProcs(int index, ProcessRecordProto.Builder builderForValue) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).setRemovedProcs((ActivityManagerServiceDumpProcessesProto) index, (int) builderForValue);
            return this;
        }

        public Builder addRemovedProcs(ProcessRecordProto value) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).addRemovedProcs((ActivityManagerServiceDumpProcessesProto) value);
            return this;
        }

        public Builder addRemovedProcs(int index, ProcessRecordProto value) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).addRemovedProcs((ActivityManagerServiceDumpProcessesProto) index, (int) value);
            return this;
        }

        public Builder addRemovedProcs(ProcessRecordProto.Builder builderForValue) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).addRemovedProcs((ActivityManagerServiceDumpProcessesProto) builderForValue);
            return this;
        }

        public Builder addRemovedProcs(int index, ProcessRecordProto.Builder builderForValue) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).addRemovedProcs((ActivityManagerServiceDumpProcessesProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllRemovedProcs(Iterable<? extends ProcessRecordProto> values) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).addAllRemovedProcs(values);
            return this;
        }

        public Builder clearRemovedProcs() {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).clearRemovedProcs();
            return this;
        }

        public Builder removeRemovedProcs(int index) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).removeRemovedProcs(index);
            return this;
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
        public List<ProcessRecordProto> getOnHoldProcsList() {
            return Collections.unmodifiableList(((ActivityManagerServiceDumpProcessesProto) this.instance).getOnHoldProcsList());
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
        public int getOnHoldProcsCount() {
            return ((ActivityManagerServiceDumpProcessesProto) this.instance).getOnHoldProcsCount();
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
        public ProcessRecordProto getOnHoldProcs(int index) {
            return ((ActivityManagerServiceDumpProcessesProto) this.instance).getOnHoldProcs(index);
        }

        public Builder setOnHoldProcs(int index, ProcessRecordProto value) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).setOnHoldProcs((ActivityManagerServiceDumpProcessesProto) index, (int) value);
            return this;
        }

        public Builder setOnHoldProcs(int index, ProcessRecordProto.Builder builderForValue) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).setOnHoldProcs((ActivityManagerServiceDumpProcessesProto) index, (int) builderForValue);
            return this;
        }

        public Builder addOnHoldProcs(ProcessRecordProto value) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).addOnHoldProcs((ActivityManagerServiceDumpProcessesProto) value);
            return this;
        }

        public Builder addOnHoldProcs(int index, ProcessRecordProto value) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).addOnHoldProcs((ActivityManagerServiceDumpProcessesProto) index, (int) value);
            return this;
        }

        public Builder addOnHoldProcs(ProcessRecordProto.Builder builderForValue) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).addOnHoldProcs((ActivityManagerServiceDumpProcessesProto) builderForValue);
            return this;
        }

        public Builder addOnHoldProcs(int index, ProcessRecordProto.Builder builderForValue) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).addOnHoldProcs((ActivityManagerServiceDumpProcessesProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllOnHoldProcs(Iterable<? extends ProcessRecordProto> values) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).addAllOnHoldProcs(values);
            return this;
        }

        public Builder clearOnHoldProcs() {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).clearOnHoldProcs();
            return this;
        }

        public Builder removeOnHoldProcs(int index) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).removeOnHoldProcs(index);
            return this;
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
        public List<ProcessToGcProto> getGcProcsList() {
            return Collections.unmodifiableList(((ActivityManagerServiceDumpProcessesProto) this.instance).getGcProcsList());
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
        public int getGcProcsCount() {
            return ((ActivityManagerServiceDumpProcessesProto) this.instance).getGcProcsCount();
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
        public ProcessToGcProto getGcProcs(int index) {
            return ((ActivityManagerServiceDumpProcessesProto) this.instance).getGcProcs(index);
        }

        public Builder setGcProcs(int index, ProcessToGcProto value) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).setGcProcs((ActivityManagerServiceDumpProcessesProto) index, (int) value);
            return this;
        }

        public Builder setGcProcs(int index, ProcessToGcProto.Builder builderForValue) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).setGcProcs((ActivityManagerServiceDumpProcessesProto) index, (int) builderForValue);
            return this;
        }

        public Builder addGcProcs(ProcessToGcProto value) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).addGcProcs((ActivityManagerServiceDumpProcessesProto) value);
            return this;
        }

        public Builder addGcProcs(int index, ProcessToGcProto value) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).addGcProcs((ActivityManagerServiceDumpProcessesProto) index, (int) value);
            return this;
        }

        public Builder addGcProcs(ProcessToGcProto.Builder builderForValue) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).addGcProcs((ActivityManagerServiceDumpProcessesProto) builderForValue);
            return this;
        }

        public Builder addGcProcs(int index, ProcessToGcProto.Builder builderForValue) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).addGcProcs((ActivityManagerServiceDumpProcessesProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllGcProcs(Iterable<? extends ProcessToGcProto> values) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).addAllGcProcs(values);
            return this;
        }

        public Builder clearGcProcs() {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).clearGcProcs();
            return this;
        }

        public Builder removeGcProcs(int index) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).removeGcProcs(index);
            return this;
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
        public boolean hasAppErrors() {
            return ((ActivityManagerServiceDumpProcessesProto) this.instance).hasAppErrors();
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
        public AppErrorsProto getAppErrors() {
            return ((ActivityManagerServiceDumpProcessesProto) this.instance).getAppErrors();
        }

        public Builder setAppErrors(AppErrorsProto value) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).setAppErrors((ActivityManagerServiceDumpProcessesProto) value);
            return this;
        }

        public Builder setAppErrors(AppErrorsProto.Builder builderForValue) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).setAppErrors((ActivityManagerServiceDumpProcessesProto) builderForValue);
            return this;
        }

        public Builder mergeAppErrors(AppErrorsProto value) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).mergeAppErrors(value);
            return this;
        }

        public Builder clearAppErrors() {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).clearAppErrors();
            return this;
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
        public boolean hasUserController() {
            return ((ActivityManagerServiceDumpProcessesProto) this.instance).hasUserController();
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
        public UserControllerProto getUserController() {
            return ((ActivityManagerServiceDumpProcessesProto) this.instance).getUserController();
        }

        public Builder setUserController(UserControllerProto value) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).setUserController((ActivityManagerServiceDumpProcessesProto) value);
            return this;
        }

        public Builder setUserController(UserControllerProto.Builder builderForValue) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).setUserController((ActivityManagerServiceDumpProcessesProto) builderForValue);
            return this;
        }

        public Builder mergeUserController(UserControllerProto value) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).mergeUserController(value);
            return this;
        }

        public Builder clearUserController() {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).clearUserController();
            return this;
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
        public boolean hasHomeProc() {
            return ((ActivityManagerServiceDumpProcessesProto) this.instance).hasHomeProc();
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
        public ProcessRecordProto getHomeProc() {
            return ((ActivityManagerServiceDumpProcessesProto) this.instance).getHomeProc();
        }

        public Builder setHomeProc(ProcessRecordProto value) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).setHomeProc((ActivityManagerServiceDumpProcessesProto) value);
            return this;
        }

        public Builder setHomeProc(ProcessRecordProto.Builder builderForValue) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).setHomeProc((ActivityManagerServiceDumpProcessesProto) builderForValue);
            return this;
        }

        public Builder mergeHomeProc(ProcessRecordProto value) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).mergeHomeProc(value);
            return this;
        }

        public Builder clearHomeProc() {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).clearHomeProc();
            return this;
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
        public boolean hasPreviousProc() {
            return ((ActivityManagerServiceDumpProcessesProto) this.instance).hasPreviousProc();
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
        public ProcessRecordProto getPreviousProc() {
            return ((ActivityManagerServiceDumpProcessesProto) this.instance).getPreviousProc();
        }

        public Builder setPreviousProc(ProcessRecordProto value) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).setPreviousProc((ActivityManagerServiceDumpProcessesProto) value);
            return this;
        }

        public Builder setPreviousProc(ProcessRecordProto.Builder builderForValue) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).setPreviousProc((ActivityManagerServiceDumpProcessesProto) builderForValue);
            return this;
        }

        public Builder mergePreviousProc(ProcessRecordProto value) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).mergePreviousProc(value);
            return this;
        }

        public Builder clearPreviousProc() {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).clearPreviousProc();
            return this;
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
        public boolean hasPreviousProcVisibleTimeMs() {
            return ((ActivityManagerServiceDumpProcessesProto) this.instance).hasPreviousProcVisibleTimeMs();
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
        public long getPreviousProcVisibleTimeMs() {
            return ((ActivityManagerServiceDumpProcessesProto) this.instance).getPreviousProcVisibleTimeMs();
        }

        public Builder setPreviousProcVisibleTimeMs(long value) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).setPreviousProcVisibleTimeMs(value);
            return this;
        }

        public Builder clearPreviousProcVisibleTimeMs() {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).clearPreviousProcVisibleTimeMs();
            return this;
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
        public boolean hasHeavyWeightProc() {
            return ((ActivityManagerServiceDumpProcessesProto) this.instance).hasHeavyWeightProc();
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
        public ProcessRecordProto getHeavyWeightProc() {
            return ((ActivityManagerServiceDumpProcessesProto) this.instance).getHeavyWeightProc();
        }

        public Builder setHeavyWeightProc(ProcessRecordProto value) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).setHeavyWeightProc((ActivityManagerServiceDumpProcessesProto) value);
            return this;
        }

        public Builder setHeavyWeightProc(ProcessRecordProto.Builder builderForValue) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).setHeavyWeightProc((ActivityManagerServiceDumpProcessesProto) builderForValue);
            return this;
        }

        public Builder mergeHeavyWeightProc(ProcessRecordProto value) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).mergeHeavyWeightProc(value);
            return this;
        }

        public Builder clearHeavyWeightProc() {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).clearHeavyWeightProc();
            return this;
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
        public boolean hasGlobalConfiguration() {
            return ((ActivityManagerServiceDumpProcessesProto) this.instance).hasGlobalConfiguration();
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
        public ConfigurationProto getGlobalConfiguration() {
            return ((ActivityManagerServiceDumpProcessesProto) this.instance).getGlobalConfiguration();
        }

        public Builder setGlobalConfiguration(ConfigurationProto value) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).setGlobalConfiguration((ActivityManagerServiceDumpProcessesProto) value);
            return this;
        }

        public Builder setGlobalConfiguration(ConfigurationProto.Builder builderForValue) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).setGlobalConfiguration((ActivityManagerServiceDumpProcessesProto) builderForValue);
            return this;
        }

        public Builder mergeGlobalConfiguration(ConfigurationProto value) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).mergeGlobalConfiguration(value);
            return this;
        }

        public Builder clearGlobalConfiguration() {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).clearGlobalConfiguration();
            return this;
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
        public boolean hasConfigWillChange() {
            return ((ActivityManagerServiceDumpProcessesProto) this.instance).hasConfigWillChange();
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
        public boolean getConfigWillChange() {
            return ((ActivityManagerServiceDumpProcessesProto) this.instance).getConfigWillChange();
        }

        public Builder setConfigWillChange(boolean value) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).setConfigWillChange(value);
            return this;
        }

        public Builder clearConfigWillChange() {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).clearConfigWillChange();
            return this;
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
        public List<ScreenCompatPackage> getScreenCompatPackagesList() {
            return Collections.unmodifiableList(((ActivityManagerServiceDumpProcessesProto) this.instance).getScreenCompatPackagesList());
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
        public int getScreenCompatPackagesCount() {
            return ((ActivityManagerServiceDumpProcessesProto) this.instance).getScreenCompatPackagesCount();
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
        public ScreenCompatPackage getScreenCompatPackages(int index) {
            return ((ActivityManagerServiceDumpProcessesProto) this.instance).getScreenCompatPackages(index);
        }

        public Builder setScreenCompatPackages(int index, ScreenCompatPackage value) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).setScreenCompatPackages((ActivityManagerServiceDumpProcessesProto) index, (int) value);
            return this;
        }

        public Builder setScreenCompatPackages(int index, ScreenCompatPackage.Builder builderForValue) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).setScreenCompatPackages((ActivityManagerServiceDumpProcessesProto) index, (int) builderForValue);
            return this;
        }

        public Builder addScreenCompatPackages(ScreenCompatPackage value) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).addScreenCompatPackages((ActivityManagerServiceDumpProcessesProto) value);
            return this;
        }

        public Builder addScreenCompatPackages(int index, ScreenCompatPackage value) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).addScreenCompatPackages((ActivityManagerServiceDumpProcessesProto) index, (int) value);
            return this;
        }

        public Builder addScreenCompatPackages(ScreenCompatPackage.Builder builderForValue) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).addScreenCompatPackages((ActivityManagerServiceDumpProcessesProto) builderForValue);
            return this;
        }

        public Builder addScreenCompatPackages(int index, ScreenCompatPackage.Builder builderForValue) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).addScreenCompatPackages((ActivityManagerServiceDumpProcessesProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllScreenCompatPackages(Iterable<? extends ScreenCompatPackage> values) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).addAllScreenCompatPackages(values);
            return this;
        }

        public Builder clearScreenCompatPackages() {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).clearScreenCompatPackages();
            return this;
        }

        public Builder removeScreenCompatPackages(int index) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).removeScreenCompatPackages(index);
            return this;
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
        public List<UidObserverRegistrationProto> getUidObserversList() {
            return Collections.unmodifiableList(((ActivityManagerServiceDumpProcessesProto) this.instance).getUidObserversList());
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
        public int getUidObserversCount() {
            return ((ActivityManagerServiceDumpProcessesProto) this.instance).getUidObserversCount();
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
        public UidObserverRegistrationProto getUidObservers(int index) {
            return ((ActivityManagerServiceDumpProcessesProto) this.instance).getUidObservers(index);
        }

        public Builder setUidObservers(int index, UidObserverRegistrationProto value) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).setUidObservers((ActivityManagerServiceDumpProcessesProto) index, (int) value);
            return this;
        }

        public Builder setUidObservers(int index, UidObserverRegistrationProto.Builder builderForValue) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).setUidObservers((ActivityManagerServiceDumpProcessesProto) index, (int) builderForValue);
            return this;
        }

        public Builder addUidObservers(UidObserverRegistrationProto value) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).addUidObservers((ActivityManagerServiceDumpProcessesProto) value);
            return this;
        }

        public Builder addUidObservers(int index, UidObserverRegistrationProto value) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).addUidObservers((ActivityManagerServiceDumpProcessesProto) index, (int) value);
            return this;
        }

        public Builder addUidObservers(UidObserverRegistrationProto.Builder builderForValue) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).addUidObservers((ActivityManagerServiceDumpProcessesProto) builderForValue);
            return this;
        }

        public Builder addUidObservers(int index, UidObserverRegistrationProto.Builder builderForValue) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).addUidObservers((ActivityManagerServiceDumpProcessesProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllUidObservers(Iterable<? extends UidObserverRegistrationProto> values) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).addAllUidObservers(values);
            return this;
        }

        public Builder clearUidObservers() {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).clearUidObservers();
            return this;
        }

        public Builder removeUidObservers(int index) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).removeUidObservers(index);
            return this;
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
        public List<Integer> getDeviceIdleWhitelistList() {
            return Collections.unmodifiableList(((ActivityManagerServiceDumpProcessesProto) this.instance).getDeviceIdleWhitelistList());
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
        public int getDeviceIdleWhitelistCount() {
            return ((ActivityManagerServiceDumpProcessesProto) this.instance).getDeviceIdleWhitelistCount();
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
        public int getDeviceIdleWhitelist(int index) {
            return ((ActivityManagerServiceDumpProcessesProto) this.instance).getDeviceIdleWhitelist(index);
        }

        public Builder setDeviceIdleWhitelist(int index, int value) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).setDeviceIdleWhitelist(index, value);
            return this;
        }

        public Builder addDeviceIdleWhitelist(int value) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).addDeviceIdleWhitelist(value);
            return this;
        }

        public Builder addAllDeviceIdleWhitelist(Iterable<? extends Integer> values) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).addAllDeviceIdleWhitelist(values);
            return this;
        }

        public Builder clearDeviceIdleWhitelist() {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).clearDeviceIdleWhitelist();
            return this;
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
        public List<Integer> getDeviceIdleTempWhitelistList() {
            return Collections.unmodifiableList(((ActivityManagerServiceDumpProcessesProto) this.instance).getDeviceIdleTempWhitelistList());
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
        public int getDeviceIdleTempWhitelistCount() {
            return ((ActivityManagerServiceDumpProcessesProto) this.instance).getDeviceIdleTempWhitelistCount();
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
        public int getDeviceIdleTempWhitelist(int index) {
            return ((ActivityManagerServiceDumpProcessesProto) this.instance).getDeviceIdleTempWhitelist(index);
        }

        public Builder setDeviceIdleTempWhitelist(int index, int value) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).setDeviceIdleTempWhitelist(index, value);
            return this;
        }

        public Builder addDeviceIdleTempWhitelist(int value) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).addDeviceIdleTempWhitelist(value);
            return this;
        }

        public Builder addAllDeviceIdleTempWhitelist(Iterable<? extends Integer> values) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).addAllDeviceIdleTempWhitelist(values);
            return this;
        }

        public Builder clearDeviceIdleTempWhitelist() {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).clearDeviceIdleTempWhitelist();
            return this;
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
        public List<PendingTempWhitelist> getPendingTempWhitelistList() {
            return Collections.unmodifiableList(((ActivityManagerServiceDumpProcessesProto) this.instance).getPendingTempWhitelistList());
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
        public int getPendingTempWhitelistCount() {
            return ((ActivityManagerServiceDumpProcessesProto) this.instance).getPendingTempWhitelistCount();
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
        public PendingTempWhitelist getPendingTempWhitelist(int index) {
            return ((ActivityManagerServiceDumpProcessesProto) this.instance).getPendingTempWhitelist(index);
        }

        public Builder setPendingTempWhitelist(int index, PendingTempWhitelist value) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).setPendingTempWhitelist((ActivityManagerServiceDumpProcessesProto) index, (int) value);
            return this;
        }

        public Builder setPendingTempWhitelist(int index, PendingTempWhitelist.Builder builderForValue) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).setPendingTempWhitelist((ActivityManagerServiceDumpProcessesProto) index, (int) builderForValue);
            return this;
        }

        public Builder addPendingTempWhitelist(PendingTempWhitelist value) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).addPendingTempWhitelist((ActivityManagerServiceDumpProcessesProto) value);
            return this;
        }

        public Builder addPendingTempWhitelist(int index, PendingTempWhitelist value) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).addPendingTempWhitelist((ActivityManagerServiceDumpProcessesProto) index, (int) value);
            return this;
        }

        public Builder addPendingTempWhitelist(PendingTempWhitelist.Builder builderForValue) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).addPendingTempWhitelist((ActivityManagerServiceDumpProcessesProto) builderForValue);
            return this;
        }

        public Builder addPendingTempWhitelist(int index, PendingTempWhitelist.Builder builderForValue) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).addPendingTempWhitelist((ActivityManagerServiceDumpProcessesProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllPendingTempWhitelist(Iterable<? extends PendingTempWhitelist> values) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).addAllPendingTempWhitelist(values);
            return this;
        }

        public Builder clearPendingTempWhitelist() {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).clearPendingTempWhitelist();
            return this;
        }

        public Builder removePendingTempWhitelist(int index) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).removePendingTempWhitelist(index);
            return this;
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
        public boolean hasSleepStatus() {
            return ((ActivityManagerServiceDumpProcessesProto) this.instance).hasSleepStatus();
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
        public SleepStatus getSleepStatus() {
            return ((ActivityManagerServiceDumpProcessesProto) this.instance).getSleepStatus();
        }

        public Builder setSleepStatus(SleepStatus value) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).setSleepStatus((ActivityManagerServiceDumpProcessesProto) value);
            return this;
        }

        public Builder setSleepStatus(SleepStatus.Builder builderForValue) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).setSleepStatus((ActivityManagerServiceDumpProcessesProto) builderForValue);
            return this;
        }

        public Builder mergeSleepStatus(SleepStatus value) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).mergeSleepStatus(value);
            return this;
        }

        public Builder clearSleepStatus() {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).clearSleepStatus();
            return this;
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
        public boolean hasRunningVoice() {
            return ((ActivityManagerServiceDumpProcessesProto) this.instance).hasRunningVoice();
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
        public Voice getRunningVoice() {
            return ((ActivityManagerServiceDumpProcessesProto) this.instance).getRunningVoice();
        }

        public Builder setRunningVoice(Voice value) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).setRunningVoice((ActivityManagerServiceDumpProcessesProto) value);
            return this;
        }

        public Builder setRunningVoice(Voice.Builder builderForValue) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).setRunningVoice((ActivityManagerServiceDumpProcessesProto) builderForValue);
            return this;
        }

        public Builder mergeRunningVoice(Voice value) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).mergeRunningVoice(value);
            return this;
        }

        public Builder clearRunningVoice() {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).clearRunningVoice();
            return this;
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
        public boolean hasVrController() {
            return ((ActivityManagerServiceDumpProcessesProto) this.instance).hasVrController();
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
        public VrControllerProto getVrController() {
            return ((ActivityManagerServiceDumpProcessesProto) this.instance).getVrController();
        }

        public Builder setVrController(VrControllerProto value) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).setVrController((ActivityManagerServiceDumpProcessesProto) value);
            return this;
        }

        public Builder setVrController(VrControllerProto.Builder builderForValue) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).setVrController((ActivityManagerServiceDumpProcessesProto) builderForValue);
            return this;
        }

        public Builder mergeVrController(VrControllerProto value) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).mergeVrController(value);
            return this;
        }

        public Builder clearVrController() {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).clearVrController();
            return this;
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
        public boolean hasDebug() {
            return ((ActivityManagerServiceDumpProcessesProto) this.instance).hasDebug();
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
        public DebugApp getDebug() {
            return ((ActivityManagerServiceDumpProcessesProto) this.instance).getDebug();
        }

        public Builder setDebug(DebugApp value) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).setDebug((ActivityManagerServiceDumpProcessesProto) value);
            return this;
        }

        public Builder setDebug(DebugApp.Builder builderForValue) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).setDebug((ActivityManagerServiceDumpProcessesProto) builderForValue);
            return this;
        }

        public Builder mergeDebug(DebugApp value) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).mergeDebug(value);
            return this;
        }

        public Builder clearDebug() {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).clearDebug();
            return this;
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
        public boolean hasCurrentTracker() {
            return ((ActivityManagerServiceDumpProcessesProto) this.instance).hasCurrentTracker();
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
        public AppTimeTrackerProto getCurrentTracker() {
            return ((ActivityManagerServiceDumpProcessesProto) this.instance).getCurrentTracker();
        }

        public Builder setCurrentTracker(AppTimeTrackerProto value) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).setCurrentTracker((ActivityManagerServiceDumpProcessesProto) value);
            return this;
        }

        public Builder setCurrentTracker(AppTimeTrackerProto.Builder builderForValue) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).setCurrentTracker((ActivityManagerServiceDumpProcessesProto) builderForValue);
            return this;
        }

        public Builder mergeCurrentTracker(AppTimeTrackerProto value) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).mergeCurrentTracker(value);
            return this;
        }

        public Builder clearCurrentTracker() {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).clearCurrentTracker();
            return this;
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
        public boolean hasMemWatchProcesses() {
            return ((ActivityManagerServiceDumpProcessesProto) this.instance).hasMemWatchProcesses();
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
        public MemWatchProcess getMemWatchProcesses() {
            return ((ActivityManagerServiceDumpProcessesProto) this.instance).getMemWatchProcesses();
        }

        public Builder setMemWatchProcesses(MemWatchProcess value) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).setMemWatchProcesses((ActivityManagerServiceDumpProcessesProto) value);
            return this;
        }

        public Builder setMemWatchProcesses(MemWatchProcess.Builder builderForValue) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).setMemWatchProcesses((ActivityManagerServiceDumpProcessesProto) builderForValue);
            return this;
        }

        public Builder mergeMemWatchProcesses(MemWatchProcess value) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).mergeMemWatchProcesses(value);
            return this;
        }

        public Builder clearMemWatchProcesses() {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).clearMemWatchProcesses();
            return this;
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
        public boolean hasTrackAllocationApp() {
            return ((ActivityManagerServiceDumpProcessesProto) this.instance).hasTrackAllocationApp();
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
        public String getTrackAllocationApp() {
            return ((ActivityManagerServiceDumpProcessesProto) this.instance).getTrackAllocationApp();
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
        public ByteString getTrackAllocationAppBytes() {
            return ((ActivityManagerServiceDumpProcessesProto) this.instance).getTrackAllocationAppBytes();
        }

        public Builder setTrackAllocationApp(String value) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).setTrackAllocationApp(value);
            return this;
        }

        public Builder clearTrackAllocationApp() {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).clearTrackAllocationApp();
            return this;
        }

        public Builder setTrackAllocationAppBytes(ByteString value) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).setTrackAllocationAppBytes(value);
            return this;
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
        public boolean hasProfile() {
            return ((ActivityManagerServiceDumpProcessesProto) this.instance).hasProfile();
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
        public Profile getProfile() {
            return ((ActivityManagerServiceDumpProcessesProto) this.instance).getProfile();
        }

        public Builder setProfile(Profile value) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).setProfile((ActivityManagerServiceDumpProcessesProto) value);
            return this;
        }

        public Builder setProfile(Profile.Builder builderForValue) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).setProfile((ActivityManagerServiceDumpProcessesProto) builderForValue);
            return this;
        }

        public Builder mergeProfile(Profile value) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).mergeProfile(value);
            return this;
        }

        public Builder clearProfile() {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).clearProfile();
            return this;
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
        public boolean hasNativeDebuggingApp() {
            return ((ActivityManagerServiceDumpProcessesProto) this.instance).hasNativeDebuggingApp();
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
        public String getNativeDebuggingApp() {
            return ((ActivityManagerServiceDumpProcessesProto) this.instance).getNativeDebuggingApp();
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
        public ByteString getNativeDebuggingAppBytes() {
            return ((ActivityManagerServiceDumpProcessesProto) this.instance).getNativeDebuggingAppBytes();
        }

        public Builder setNativeDebuggingApp(String value) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).setNativeDebuggingApp(value);
            return this;
        }

        public Builder clearNativeDebuggingApp() {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).clearNativeDebuggingApp();
            return this;
        }

        public Builder setNativeDebuggingAppBytes(ByteString value) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).setNativeDebuggingAppBytes(value);
            return this;
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
        public boolean hasAlwaysFinishActivities() {
            return ((ActivityManagerServiceDumpProcessesProto) this.instance).hasAlwaysFinishActivities();
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
        public boolean getAlwaysFinishActivities() {
            return ((ActivityManagerServiceDumpProcessesProto) this.instance).getAlwaysFinishActivities();
        }

        public Builder setAlwaysFinishActivities(boolean value) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).setAlwaysFinishActivities(value);
            return this;
        }

        public Builder clearAlwaysFinishActivities() {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).clearAlwaysFinishActivities();
            return this;
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
        public boolean hasController() {
            return ((ActivityManagerServiceDumpProcessesProto) this.instance).hasController();
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
        public Controller getController() {
            return ((ActivityManagerServiceDumpProcessesProto) this.instance).getController();
        }

        public Builder setController(Controller value) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).setController((ActivityManagerServiceDumpProcessesProto) value);
            return this;
        }

        public Builder setController(Controller.Builder builderForValue) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).setController((ActivityManagerServiceDumpProcessesProto) builderForValue);
            return this;
        }

        public Builder mergeController(Controller value) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).mergeController(value);
            return this;
        }

        public Builder clearController() {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).clearController();
            return this;
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
        public boolean hasTotalPersistentProcs() {
            return ((ActivityManagerServiceDumpProcessesProto) this.instance).hasTotalPersistentProcs();
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
        public int getTotalPersistentProcs() {
            return ((ActivityManagerServiceDumpProcessesProto) this.instance).getTotalPersistentProcs();
        }

        public Builder setTotalPersistentProcs(int value) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).setTotalPersistentProcs(value);
            return this;
        }

        public Builder clearTotalPersistentProcs() {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).clearTotalPersistentProcs();
            return this;
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
        public boolean hasProcessesReady() {
            return ((ActivityManagerServiceDumpProcessesProto) this.instance).hasProcessesReady();
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
        public boolean getProcessesReady() {
            return ((ActivityManagerServiceDumpProcessesProto) this.instance).getProcessesReady();
        }

        public Builder setProcessesReady(boolean value) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).setProcessesReady(value);
            return this;
        }

        public Builder clearProcessesReady() {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).clearProcessesReady();
            return this;
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
        public boolean hasSystemReady() {
            return ((ActivityManagerServiceDumpProcessesProto) this.instance).hasSystemReady();
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
        public boolean getSystemReady() {
            return ((ActivityManagerServiceDumpProcessesProto) this.instance).getSystemReady();
        }

        public Builder setSystemReady(boolean value) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).setSystemReady(value);
            return this;
        }

        public Builder clearSystemReady() {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).clearSystemReady();
            return this;
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
        public boolean hasBooted() {
            return ((ActivityManagerServiceDumpProcessesProto) this.instance).hasBooted();
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
        public boolean getBooted() {
            return ((ActivityManagerServiceDumpProcessesProto) this.instance).getBooted();
        }

        public Builder setBooted(boolean value) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).setBooted(value);
            return this;
        }

        public Builder clearBooted() {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).clearBooted();
            return this;
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
        public boolean hasFactoryTest() {
            return ((ActivityManagerServiceDumpProcessesProto) this.instance).hasFactoryTest();
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
        public int getFactoryTest() {
            return ((ActivityManagerServiceDumpProcessesProto) this.instance).getFactoryTest();
        }

        public Builder setFactoryTest(int value) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).setFactoryTest(value);
            return this;
        }

        public Builder clearFactoryTest() {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).clearFactoryTest();
            return this;
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
        public boolean hasBooting() {
            return ((ActivityManagerServiceDumpProcessesProto) this.instance).hasBooting();
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
        public boolean getBooting() {
            return ((ActivityManagerServiceDumpProcessesProto) this.instance).getBooting();
        }

        public Builder setBooting(boolean value) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).setBooting(value);
            return this;
        }

        public Builder clearBooting() {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).clearBooting();
            return this;
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
        public boolean hasCallFinishBooting() {
            return ((ActivityManagerServiceDumpProcessesProto) this.instance).hasCallFinishBooting();
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
        public boolean getCallFinishBooting() {
            return ((ActivityManagerServiceDumpProcessesProto) this.instance).getCallFinishBooting();
        }

        public Builder setCallFinishBooting(boolean value) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).setCallFinishBooting(value);
            return this;
        }

        public Builder clearCallFinishBooting() {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).clearCallFinishBooting();
            return this;
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
        public boolean hasBootAnimationComplete() {
            return ((ActivityManagerServiceDumpProcessesProto) this.instance).hasBootAnimationComplete();
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
        public boolean getBootAnimationComplete() {
            return ((ActivityManagerServiceDumpProcessesProto) this.instance).getBootAnimationComplete();
        }

        public Builder setBootAnimationComplete(boolean value) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).setBootAnimationComplete(value);
            return this;
        }

        public Builder clearBootAnimationComplete() {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).clearBootAnimationComplete();
            return this;
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
        public boolean hasLastPowerCheckUptimeMs() {
            return ((ActivityManagerServiceDumpProcessesProto) this.instance).hasLastPowerCheckUptimeMs();
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
        public long getLastPowerCheckUptimeMs() {
            return ((ActivityManagerServiceDumpProcessesProto) this.instance).getLastPowerCheckUptimeMs();
        }

        public Builder setLastPowerCheckUptimeMs(long value) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).setLastPowerCheckUptimeMs(value);
            return this;
        }

        public Builder clearLastPowerCheckUptimeMs() {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).clearLastPowerCheckUptimeMs();
            return this;
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
        public boolean hasGoingToSleep() {
            return ((ActivityManagerServiceDumpProcessesProto) this.instance).hasGoingToSleep();
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
        public PowerManagerProto.WakeLock getGoingToSleep() {
            return ((ActivityManagerServiceDumpProcessesProto) this.instance).getGoingToSleep();
        }

        public Builder setGoingToSleep(PowerManagerProto.WakeLock value) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).setGoingToSleep((ActivityManagerServiceDumpProcessesProto) value);
            return this;
        }

        public Builder setGoingToSleep(PowerManagerProto.WakeLock.Builder builderForValue) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).setGoingToSleep((ActivityManagerServiceDumpProcessesProto) builderForValue);
            return this;
        }

        public Builder mergeGoingToSleep(PowerManagerProto.WakeLock value) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).mergeGoingToSleep(value);
            return this;
        }

        public Builder clearGoingToSleep() {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).clearGoingToSleep();
            return this;
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
        public boolean hasLaunchingActivity() {
            return ((ActivityManagerServiceDumpProcessesProto) this.instance).hasLaunchingActivity();
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
        public PowerManagerProto.WakeLock getLaunchingActivity() {
            return ((ActivityManagerServiceDumpProcessesProto) this.instance).getLaunchingActivity();
        }

        public Builder setLaunchingActivity(PowerManagerProto.WakeLock value) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).setLaunchingActivity((ActivityManagerServiceDumpProcessesProto) value);
            return this;
        }

        public Builder setLaunchingActivity(PowerManagerProto.WakeLock.Builder builderForValue) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).setLaunchingActivity((ActivityManagerServiceDumpProcessesProto) builderForValue);
            return this;
        }

        public Builder mergeLaunchingActivity(PowerManagerProto.WakeLock value) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).mergeLaunchingActivity(value);
            return this;
        }

        public Builder clearLaunchingActivity() {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).clearLaunchingActivity();
            return this;
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
        public boolean hasAdjSeq() {
            return ((ActivityManagerServiceDumpProcessesProto) this.instance).hasAdjSeq();
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
        public int getAdjSeq() {
            return ((ActivityManagerServiceDumpProcessesProto) this.instance).getAdjSeq();
        }

        public Builder setAdjSeq(int value) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).setAdjSeq(value);
            return this;
        }

        public Builder clearAdjSeq() {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).clearAdjSeq();
            return this;
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
        public boolean hasLruSeq() {
            return ((ActivityManagerServiceDumpProcessesProto) this.instance).hasLruSeq();
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
        public int getLruSeq() {
            return ((ActivityManagerServiceDumpProcessesProto) this.instance).getLruSeq();
        }

        public Builder setLruSeq(int value) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).setLruSeq(value);
            return this;
        }

        public Builder clearLruSeq() {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).clearLruSeq();
            return this;
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
        public boolean hasNumNonCachedProcs() {
            return ((ActivityManagerServiceDumpProcessesProto) this.instance).hasNumNonCachedProcs();
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
        public int getNumNonCachedProcs() {
            return ((ActivityManagerServiceDumpProcessesProto) this.instance).getNumNonCachedProcs();
        }

        public Builder setNumNonCachedProcs(int value) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).setNumNonCachedProcs(value);
            return this;
        }

        public Builder clearNumNonCachedProcs() {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).clearNumNonCachedProcs();
            return this;
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
        public boolean hasNumCachedHiddenProcs() {
            return ((ActivityManagerServiceDumpProcessesProto) this.instance).hasNumCachedHiddenProcs();
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
        public int getNumCachedHiddenProcs() {
            return ((ActivityManagerServiceDumpProcessesProto) this.instance).getNumCachedHiddenProcs();
        }

        public Builder setNumCachedHiddenProcs(int value) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).setNumCachedHiddenProcs(value);
            return this;
        }

        public Builder clearNumCachedHiddenProcs() {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).clearNumCachedHiddenProcs();
            return this;
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
        public boolean hasNumServiceProcs() {
            return ((ActivityManagerServiceDumpProcessesProto) this.instance).hasNumServiceProcs();
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
        public int getNumServiceProcs() {
            return ((ActivityManagerServiceDumpProcessesProto) this.instance).getNumServiceProcs();
        }

        public Builder setNumServiceProcs(int value) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).setNumServiceProcs(value);
            return this;
        }

        public Builder clearNumServiceProcs() {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).clearNumServiceProcs();
            return this;
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
        public boolean hasNewNumServiceProcs() {
            return ((ActivityManagerServiceDumpProcessesProto) this.instance).hasNewNumServiceProcs();
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
        public int getNewNumServiceProcs() {
            return ((ActivityManagerServiceDumpProcessesProto) this.instance).getNewNumServiceProcs();
        }

        public Builder setNewNumServiceProcs(int value) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).setNewNumServiceProcs(value);
            return this;
        }

        public Builder clearNewNumServiceProcs() {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).clearNewNumServiceProcs();
            return this;
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
        public boolean hasAllowLowerMemLevel() {
            return ((ActivityManagerServiceDumpProcessesProto) this.instance).hasAllowLowerMemLevel();
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
        public boolean getAllowLowerMemLevel() {
            return ((ActivityManagerServiceDumpProcessesProto) this.instance).getAllowLowerMemLevel();
        }

        public Builder setAllowLowerMemLevel(boolean value) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).setAllowLowerMemLevel(value);
            return this;
        }

        public Builder clearAllowLowerMemLevel() {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).clearAllowLowerMemLevel();
            return this;
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
        public boolean hasLastMemoryLevel() {
            return ((ActivityManagerServiceDumpProcessesProto) this.instance).hasLastMemoryLevel();
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
        public int getLastMemoryLevel() {
            return ((ActivityManagerServiceDumpProcessesProto) this.instance).getLastMemoryLevel();
        }

        public Builder setLastMemoryLevel(int value) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).setLastMemoryLevel(value);
            return this;
        }

        public Builder clearLastMemoryLevel() {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).clearLastMemoryLevel();
            return this;
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
        public boolean hasLastNumProcesses() {
            return ((ActivityManagerServiceDumpProcessesProto) this.instance).hasLastNumProcesses();
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
        public int getLastNumProcesses() {
            return ((ActivityManagerServiceDumpProcessesProto) this.instance).getLastNumProcesses();
        }

        public Builder setLastNumProcesses(int value) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).setLastNumProcesses(value);
            return this;
        }

        public Builder clearLastNumProcesses() {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).clearLastNumProcesses();
            return this;
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
        public boolean hasLastIdleTime() {
            return ((ActivityManagerServiceDumpProcessesProto) this.instance).hasLastIdleTime();
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
        public Duration getLastIdleTime() {
            return ((ActivityManagerServiceDumpProcessesProto) this.instance).getLastIdleTime();
        }

        public Builder setLastIdleTime(Duration value) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).setLastIdleTime((ActivityManagerServiceDumpProcessesProto) value);
            return this;
        }

        public Builder setLastIdleTime(Duration.Builder builderForValue) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).setLastIdleTime((ActivityManagerServiceDumpProcessesProto) builderForValue);
            return this;
        }

        public Builder mergeLastIdleTime(Duration value) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).mergeLastIdleTime(value);
            return this;
        }

        public Builder clearLastIdleTime() {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).clearLastIdleTime();
            return this;
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
        public boolean hasLowRamSinceLastIdleMs() {
            return ((ActivityManagerServiceDumpProcessesProto) this.instance).hasLowRamSinceLastIdleMs();
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpProcessesProtoOrBuilder
        public long getLowRamSinceLastIdleMs() {
            return ((ActivityManagerServiceDumpProcessesProto) this.instance).getLowRamSinceLastIdleMs();
        }

        public Builder setLowRamSinceLastIdleMs(long value) {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).setLowRamSinceLastIdleMs(value);
            return this;
        }

        public Builder clearLowRamSinceLastIdleMs() {
            copyOnWrite();
            ((ActivityManagerServiceDumpProcessesProto) this.instance).clearLowRamSinceLastIdleMs();
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new ActivityManagerServiceDumpProcessesProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                this.procs_.makeImmutable();
                this.isolatedProcs_.makeImmutable();
                this.activeInstrumentations_.makeImmutable();
                this.activeUids_.makeImmutable();
                this.validateUids_.makeImmutable();
                this.pidsSelfLocked_.makeImmutable();
                this.importantProcs_.makeImmutable();
                this.persistentStartingProcs_.makeImmutable();
                this.removedProcs_.makeImmutable();
                this.onHoldProcs_.makeImmutable();
                this.gcProcs_.makeImmutable();
                this.screenCompatPackages_.makeImmutable();
                this.uidObservers_.makeImmutable();
                this.deviceIdleWhitelist_.makeImmutable();
                this.deviceIdleTempWhitelist_.makeImmutable();
                this.pendingTempWhitelist_.makeImmutable();
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                ActivityManagerServiceDumpProcessesProto other = (ActivityManagerServiceDumpProcessesProto) arg1;
                this.procs_ = visitor.visitList(this.procs_, other.procs_);
                this.isolatedProcs_ = visitor.visitList(this.isolatedProcs_, other.isolatedProcs_);
                this.activeInstrumentations_ = visitor.visitList(this.activeInstrumentations_, other.activeInstrumentations_);
                this.activeUids_ = visitor.visitList(this.activeUids_, other.activeUids_);
                this.validateUids_ = visitor.visitList(this.validateUids_, other.validateUids_);
                this.lruProcs_ = (LruProcesses) visitor.visitMessage(this.lruProcs_, other.lruProcs_);
                this.pidsSelfLocked_ = visitor.visitList(this.pidsSelfLocked_, other.pidsSelfLocked_);
                this.importantProcs_ = visitor.visitList(this.importantProcs_, other.importantProcs_);
                this.persistentStartingProcs_ = visitor.visitList(this.persistentStartingProcs_, other.persistentStartingProcs_);
                this.removedProcs_ = visitor.visitList(this.removedProcs_, other.removedProcs_);
                this.onHoldProcs_ = visitor.visitList(this.onHoldProcs_, other.onHoldProcs_);
                this.gcProcs_ = visitor.visitList(this.gcProcs_, other.gcProcs_);
                this.appErrors_ = (AppErrorsProto) visitor.visitMessage(this.appErrors_, other.appErrors_);
                this.userController_ = (UserControllerProto) visitor.visitMessage(this.userController_, other.userController_);
                this.homeProc_ = (ProcessRecordProto) visitor.visitMessage(this.homeProc_, other.homeProc_);
                this.previousProc_ = (ProcessRecordProto) visitor.visitMessage(this.previousProc_, other.previousProc_);
                this.previousProcVisibleTimeMs_ = visitor.visitLong(hasPreviousProcVisibleTimeMs(), this.previousProcVisibleTimeMs_, other.hasPreviousProcVisibleTimeMs(), other.previousProcVisibleTimeMs_);
                this.heavyWeightProc_ = (ProcessRecordProto) visitor.visitMessage(this.heavyWeightProc_, other.heavyWeightProc_);
                this.globalConfiguration_ = (ConfigurationProto) visitor.visitMessage(this.globalConfiguration_, other.globalConfiguration_);
                this.configWillChange_ = visitor.visitBoolean(hasConfigWillChange(), this.configWillChange_, other.hasConfigWillChange(), other.configWillChange_);
                this.screenCompatPackages_ = visitor.visitList(this.screenCompatPackages_, other.screenCompatPackages_);
                this.uidObservers_ = visitor.visitList(this.uidObservers_, other.uidObservers_);
                this.deviceIdleWhitelist_ = visitor.visitIntList(this.deviceIdleWhitelist_, other.deviceIdleWhitelist_);
                this.deviceIdleTempWhitelist_ = visitor.visitIntList(this.deviceIdleTempWhitelist_, other.deviceIdleTempWhitelist_);
                this.pendingTempWhitelist_ = visitor.visitList(this.pendingTempWhitelist_, other.pendingTempWhitelist_);
                this.sleepStatus_ = (SleepStatus) visitor.visitMessage(this.sleepStatus_, other.sleepStatus_);
                this.runningVoice_ = (Voice) visitor.visitMessage(this.runningVoice_, other.runningVoice_);
                this.vrController_ = (VrControllerProto) visitor.visitMessage(this.vrController_, other.vrController_);
                this.debug_ = (DebugApp) visitor.visitMessage(this.debug_, other.debug_);
                this.currentTracker_ = (AppTimeTrackerProto) visitor.visitMessage(this.currentTracker_, other.currentTracker_);
                this.memWatchProcesses_ = (MemWatchProcess) visitor.visitMessage(this.memWatchProcesses_, other.memWatchProcesses_);
                this.trackAllocationApp_ = visitor.visitString(hasTrackAllocationApp(), this.trackAllocationApp_, other.hasTrackAllocationApp(), other.trackAllocationApp_);
                this.profile_ = (Profile) visitor.visitMessage(this.profile_, other.profile_);
                this.nativeDebuggingApp_ = visitor.visitString(hasNativeDebuggingApp(), this.nativeDebuggingApp_, other.hasNativeDebuggingApp(), other.nativeDebuggingApp_);
                this.alwaysFinishActivities_ = visitor.visitBoolean(hasAlwaysFinishActivities(), this.alwaysFinishActivities_, other.hasAlwaysFinishActivities(), other.alwaysFinishActivities_);
                this.controller_ = (Controller) visitor.visitMessage(this.controller_, other.controller_);
                this.totalPersistentProcs_ = visitor.visitInt(hasTotalPersistentProcs(), this.totalPersistentProcs_, other.hasTotalPersistentProcs(), other.totalPersistentProcs_);
                this.processesReady_ = visitor.visitBoolean(hasProcessesReady(), this.processesReady_, other.hasProcessesReady(), other.processesReady_);
                this.systemReady_ = visitor.visitBoolean(hasSystemReady(), this.systemReady_, other.hasSystemReady(), other.systemReady_);
                this.booted_ = visitor.visitBoolean(hasBooted(), this.booted_, other.hasBooted(), other.booted_);
                this.factoryTest_ = visitor.visitInt(hasFactoryTest(), this.factoryTest_, other.hasFactoryTest(), other.factoryTest_);
                this.booting_ = visitor.visitBoolean(hasBooting(), this.booting_, other.hasBooting(), other.booting_);
                this.callFinishBooting_ = visitor.visitBoolean(hasCallFinishBooting(), this.callFinishBooting_, other.hasCallFinishBooting(), other.callFinishBooting_);
                this.bootAnimationComplete_ = visitor.visitBoolean(hasBootAnimationComplete(), this.bootAnimationComplete_, other.hasBootAnimationComplete(), other.bootAnimationComplete_);
                this.lastPowerCheckUptimeMs_ = visitor.visitLong(hasLastPowerCheckUptimeMs(), this.lastPowerCheckUptimeMs_, other.hasLastPowerCheckUptimeMs(), other.lastPowerCheckUptimeMs_);
                this.goingToSleep_ = (PowerManagerProto.WakeLock) visitor.visitMessage(this.goingToSleep_, other.goingToSleep_);
                this.launchingActivity_ = (PowerManagerProto.WakeLock) visitor.visitMessage(this.launchingActivity_, other.launchingActivity_);
                this.adjSeq_ = visitor.visitInt(hasAdjSeq(), this.adjSeq_, other.hasAdjSeq(), other.adjSeq_);
                this.lruSeq_ = visitor.visitInt(hasLruSeq(), this.lruSeq_, other.hasLruSeq(), other.lruSeq_);
                this.numNonCachedProcs_ = visitor.visitInt(hasNumNonCachedProcs(), this.numNonCachedProcs_, other.hasNumNonCachedProcs(), other.numNonCachedProcs_);
                this.numCachedHiddenProcs_ = visitor.visitInt(hasNumCachedHiddenProcs(), this.numCachedHiddenProcs_, other.hasNumCachedHiddenProcs(), other.numCachedHiddenProcs_);
                this.numServiceProcs_ = visitor.visitInt(hasNumServiceProcs(), this.numServiceProcs_, other.hasNumServiceProcs(), other.numServiceProcs_);
                this.newNumServiceProcs_ = visitor.visitInt(hasNewNumServiceProcs(), this.newNumServiceProcs_, other.hasNewNumServiceProcs(), other.newNumServiceProcs_);
                this.allowLowerMemLevel_ = visitor.visitBoolean(hasAllowLowerMemLevel(), this.allowLowerMemLevel_, other.hasAllowLowerMemLevel(), other.allowLowerMemLevel_);
                this.lastMemoryLevel_ = visitor.visitInt(hasLastMemoryLevel(), this.lastMemoryLevel_, other.hasLastMemoryLevel(), other.lastMemoryLevel_);
                this.lastNumProcesses_ = visitor.visitInt(hasLastNumProcesses(), this.lastNumProcesses_, other.hasLastNumProcesses(), other.lastNumProcesses_);
                this.lastIdleTime_ = (Duration) visitor.visitMessage(this.lastIdleTime_, other.lastIdleTime_);
                this.lowRamSinceLastIdleMs_ = visitor.visitLong(hasLowRamSinceLastIdleMs(), this.lowRamSinceLastIdleMs_, other.hasLowRamSinceLastIdleMs(), other.lowRamSinceLastIdleMs_);
                if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE) {
                    this.bitField0_ |= other.bitField0_;
                    this.bitField1_ |= other.bitField1_;
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
                                if (!this.procs_.isModifiable()) {
                                    this.procs_ = GeneratedMessageLite.mutableCopy(this.procs_);
                                }
                                this.procs_.add((ProcessRecordProto) input.readMessage(ProcessRecordProto.parser(), extensionRegistry));
                                break;
                            case 18:
                                if (!this.isolatedProcs_.isModifiable()) {
                                    this.isolatedProcs_ = GeneratedMessageLite.mutableCopy(this.isolatedProcs_);
                                }
                                this.isolatedProcs_.add((ProcessRecordProto) input.readMessage(ProcessRecordProto.parser(), extensionRegistry));
                                break;
                            case 26:
                                if (!this.activeInstrumentations_.isModifiable()) {
                                    this.activeInstrumentations_ = GeneratedMessageLite.mutableCopy(this.activeInstrumentations_);
                                }
                                this.activeInstrumentations_.add((ActiveInstrumentationProto) input.readMessage(ActiveInstrumentationProto.parser(), extensionRegistry));
                                break;
                            case 34:
                                if (!this.activeUids_.isModifiable()) {
                                    this.activeUids_ = GeneratedMessageLite.mutableCopy(this.activeUids_);
                                }
                                this.activeUids_.add((UidRecordProto) input.readMessage(UidRecordProto.parser(), extensionRegistry));
                                break;
                            case 42:
                                if (!this.validateUids_.isModifiable()) {
                                    this.validateUids_ = GeneratedMessageLite.mutableCopy(this.validateUids_);
                                }
                                this.validateUids_.add((UidRecordProto) input.readMessage(UidRecordProto.parser(), extensionRegistry));
                                break;
                            case 50:
                                LruProcesses.Builder subBuilder = null;
                                if ((this.bitField0_ & 1) == 1) {
                                    subBuilder = (LruProcesses.Builder) this.lruProcs_.toBuilder();
                                }
                                this.lruProcs_ = (LruProcesses) input.readMessage(LruProcesses.parser(), extensionRegistry);
                                if (subBuilder != null) {
                                    subBuilder.mergeFrom((GeneratedMessageLite) this.lruProcs_);
                                    this.lruProcs_ = (LruProcesses) subBuilder.buildPartial();
                                }
                                this.bitField0_ |= 1;
                                break;
                            case 58:
                                if (!this.pidsSelfLocked_.isModifiable()) {
                                    this.pidsSelfLocked_ = GeneratedMessageLite.mutableCopy(this.pidsSelfLocked_);
                                }
                                this.pidsSelfLocked_.add((ProcessRecordProto) input.readMessage(ProcessRecordProto.parser(), extensionRegistry));
                                break;
                            case 66:
                                if (!this.importantProcs_.isModifiable()) {
                                    this.importantProcs_ = GeneratedMessageLite.mutableCopy(this.importantProcs_);
                                }
                                this.importantProcs_.add((ImportanceTokenProto) input.readMessage(ImportanceTokenProto.parser(), extensionRegistry));
                                break;
                            case 74:
                                if (!this.persistentStartingProcs_.isModifiable()) {
                                    this.persistentStartingProcs_ = GeneratedMessageLite.mutableCopy(this.persistentStartingProcs_);
                                }
                                this.persistentStartingProcs_.add((ProcessRecordProto) input.readMessage(ProcessRecordProto.parser(), extensionRegistry));
                                break;
                            case 82:
                                if (!this.removedProcs_.isModifiable()) {
                                    this.removedProcs_ = GeneratedMessageLite.mutableCopy(this.removedProcs_);
                                }
                                this.removedProcs_.add((ProcessRecordProto) input.readMessage(ProcessRecordProto.parser(), extensionRegistry));
                                break;
                            case 90:
                                if (!this.onHoldProcs_.isModifiable()) {
                                    this.onHoldProcs_ = GeneratedMessageLite.mutableCopy(this.onHoldProcs_);
                                }
                                this.onHoldProcs_.add((ProcessRecordProto) input.readMessage(ProcessRecordProto.parser(), extensionRegistry));
                                break;
                            case 98:
                                if (!this.gcProcs_.isModifiable()) {
                                    this.gcProcs_ = GeneratedMessageLite.mutableCopy(this.gcProcs_);
                                }
                                this.gcProcs_.add((ProcessToGcProto) input.readMessage(ProcessToGcProto.parser(), extensionRegistry));
                                break;
                            case 106:
                                AppErrorsProto.Builder subBuilder2 = null;
                                if ((this.bitField0_ & 2) == 2) {
                                    subBuilder2 = (AppErrorsProto.Builder) this.appErrors_.toBuilder();
                                }
                                this.appErrors_ = (AppErrorsProto) input.readMessage(AppErrorsProto.parser(), extensionRegistry);
                                if (subBuilder2 != null) {
                                    subBuilder2.mergeFrom((GeneratedMessageLite) this.appErrors_);
                                    this.appErrors_ = (AppErrorsProto) subBuilder2.buildPartial();
                                }
                                this.bitField0_ |= 2;
                                break;
                            case 114:
                                UserControllerProto.Builder subBuilder3 = null;
                                if ((this.bitField0_ & 4) == 4) {
                                    subBuilder3 = (UserControllerProto.Builder) this.userController_.toBuilder();
                                }
                                this.userController_ = (UserControllerProto) input.readMessage(UserControllerProto.parser(), extensionRegistry);
                                if (subBuilder3 != null) {
                                    subBuilder3.mergeFrom((GeneratedMessageLite) this.userController_);
                                    this.userController_ = (UserControllerProto) subBuilder3.buildPartial();
                                }
                                this.bitField0_ |= 4;
                                break;
                            case 122:
                                ProcessRecordProto.Builder subBuilder4 = null;
                                if ((this.bitField0_ & 8) == 8) {
                                    subBuilder4 = (ProcessRecordProto.Builder) this.homeProc_.toBuilder();
                                }
                                this.homeProc_ = (ProcessRecordProto) input.readMessage(ProcessRecordProto.parser(), extensionRegistry);
                                if (subBuilder4 != null) {
                                    subBuilder4.mergeFrom((GeneratedMessageLite) this.homeProc_);
                                    this.homeProc_ = (ProcessRecordProto) subBuilder4.buildPartial();
                                }
                                this.bitField0_ |= 8;
                                break;
                            case 130:
                                ProcessRecordProto.Builder subBuilder5 = null;
                                if ((this.bitField0_ & 16) == 16) {
                                    subBuilder5 = (ProcessRecordProto.Builder) this.previousProc_.toBuilder();
                                }
                                this.previousProc_ = (ProcessRecordProto) input.readMessage(ProcessRecordProto.parser(), extensionRegistry);
                                if (subBuilder5 != null) {
                                    subBuilder5.mergeFrom((GeneratedMessageLite) this.previousProc_);
                                    this.previousProc_ = (ProcessRecordProto) subBuilder5.buildPartial();
                                }
                                this.bitField0_ |= 16;
                                break;
                            case 136:
                                this.bitField0_ |= 32;
                                this.previousProcVisibleTimeMs_ = input.readInt64();
                                break;
                            case 146:
                                ProcessRecordProto.Builder subBuilder6 = null;
                                if ((this.bitField0_ & 64) == 64) {
                                    subBuilder6 = (ProcessRecordProto.Builder) this.heavyWeightProc_.toBuilder();
                                }
                                this.heavyWeightProc_ = (ProcessRecordProto) input.readMessage(ProcessRecordProto.parser(), extensionRegistry);
                                if (subBuilder6 != null) {
                                    subBuilder6.mergeFrom((GeneratedMessageLite) this.heavyWeightProc_);
                                    this.heavyWeightProc_ = (ProcessRecordProto) subBuilder6.buildPartial();
                                }
                                this.bitField0_ |= 64;
                                break;
                            case 154:
                                ConfigurationProto.Builder subBuilder7 = null;
                                if ((this.bitField0_ & 128) == 128) {
                                    subBuilder7 = (ConfigurationProto.Builder) this.globalConfiguration_.toBuilder();
                                }
                                this.globalConfiguration_ = (ConfigurationProto) input.readMessage(ConfigurationProto.parser(), extensionRegistry);
                                if (subBuilder7 != null) {
                                    subBuilder7.mergeFrom((GeneratedMessageLite) this.globalConfiguration_);
                                    this.globalConfiguration_ = (ConfigurationProto) subBuilder7.buildPartial();
                                }
                                this.bitField0_ |= 128;
                                break;
                            case 168:
                                this.bitField0_ |= 256;
                                this.configWillChange_ = input.readBool();
                                break;
                            case 178:
                                if (!this.screenCompatPackages_.isModifiable()) {
                                    this.screenCompatPackages_ = GeneratedMessageLite.mutableCopy(this.screenCompatPackages_);
                                }
                                this.screenCompatPackages_.add((ScreenCompatPackage) input.readMessage(ScreenCompatPackage.parser(), extensionRegistry));
                                break;
                            case AtomsProto.Atom.TOMB_STONE_OCCURRED_FIELD_NUMBER:
                                if (!this.uidObservers_.isModifiable()) {
                                    this.uidObservers_ = GeneratedMessageLite.mutableCopy(this.uidObservers_);
                                }
                                this.uidObservers_.add((UidObserverRegistrationProto) input.readMessage(UidObserverRegistrationProto.parser(), extensionRegistry));
                                break;
                            case AtomsProto.Atom.MEDIAMETRICS_AUDIORECORD_REPORTED_FIELD_NUMBER:
                                if (!this.deviceIdleWhitelist_.isModifiable()) {
                                    this.deviceIdleWhitelist_ = GeneratedMessageLite.mutableCopy(this.deviceIdleWhitelist_);
                                }
                                this.deviceIdleWhitelist_.addInt(input.readInt32());
                                break;
                            case AtomsProto.Atom.MEDIAMETRICS_AUDIOTRACK_REPORTED_FIELD_NUMBER:
                                int limit = input.pushLimit(input.readRawVarint32());
                                if (!this.deviceIdleWhitelist_.isModifiable() && input.getBytesUntilLimit() > 0) {
                                    this.deviceIdleWhitelist_ = GeneratedMessageLite.mutableCopy(this.deviceIdleWhitelist_);
                                }
                                while (input.getBytesUntilLimit() > 0) {
                                    this.deviceIdleWhitelist_.addInt(input.readInt32());
                                }
                                input.popLimit(limit);
                                break;
                            case 200:
                                if (!this.deviceIdleTempWhitelist_.isModifiable()) {
                                    this.deviceIdleTempWhitelist_ = GeneratedMessageLite.mutableCopy(this.deviceIdleTempWhitelist_);
                                }
                                this.deviceIdleTempWhitelist_.addInt(input.readInt32());
                                break;
                            case PROCESS_STATS_SUMMARY_VALUE:
                                int limit2 = input.pushLimit(input.readRawVarint32());
                                if (!this.deviceIdleTempWhitelist_.isModifiable() && input.getBytesUntilLimit() > 0) {
                                    this.deviceIdleTempWhitelist_ = GeneratedMessageLite.mutableCopy(this.deviceIdleTempWhitelist_);
                                }
                                while (input.getBytesUntilLimit() > 0) {
                                    this.deviceIdleTempWhitelist_.addInt(input.readInt32());
                                }
                                input.popLimit(limit2);
                                break;
                            case AtomsProto.Atom.LOCATION_MANAGER_API_USAGE_REPORTED_FIELD_NUMBER:
                                if (!this.pendingTempWhitelist_.isModifiable()) {
                                    this.pendingTempWhitelist_ = GeneratedMessageLite.mutableCopy(this.pendingTempWhitelist_);
                                }
                                this.pendingTempWhitelist_.add((PendingTempWhitelist) input.readMessage(PendingTempWhitelist.parser(), extensionRegistry));
                                break;
                            case AtomsProto.Atom.PERMISSION_APPS_FRAGMENT_VIEWED_FIELD_NUMBER:
                                SleepStatus.Builder subBuilder8 = null;
                                if ((this.bitField0_ & 512) == 512) {
                                    subBuilder8 = (SleepStatus.Builder) this.sleepStatus_.toBuilder();
                                }
                                this.sleepStatus_ = (SleepStatus) input.readMessage(SleepStatus.parser(), extensionRegistry);
                                if (subBuilder8 != null) {
                                    subBuilder8.mergeFrom((GeneratedMessageLite) this.sleepStatus_);
                                    this.sleepStatus_ = (SleepStatus) subBuilder8.buildPartial();
                                }
                                this.bitField0_ = 512 | this.bitField0_;
                                break;
                            case ACTION_SEARCH_RESULTS_VALUE:
                                Voice.Builder subBuilder9 = null;
                                if ((this.bitField0_ & 1024) == 1024) {
                                    subBuilder9 = (Voice.Builder) this.runningVoice_.toBuilder();
                                }
                                this.runningVoice_ = (Voice) input.readMessage(Voice.parser(), extensionRegistry);
                                if (subBuilder9 != null) {
                                    subBuilder9.mergeFrom((GeneratedMessageLite) this.runningVoice_);
                                    this.runningVoice_ = (Voice) subBuilder9.buildPartial();
                                }
                                this.bitField0_ |= 1024;
                                break;
                            case 234:
                                VrControllerProto.Builder subBuilder10 = null;
                                if ((this.bitField0_ & 2048) == 2048) {
                                    subBuilder10 = (VrControllerProto.Builder) this.vrController_.toBuilder();
                                }
                                this.vrController_ = (VrControllerProto) input.readMessage(VrControllerProto.parser(), extensionRegistry);
                                if (subBuilder10 != null) {
                                    subBuilder10.mergeFrom((GeneratedMessageLite) this.vrController_);
                                    this.vrController_ = (VrControllerProto) subBuilder10.buildPartial();
                                }
                                this.bitField0_ |= 2048;
                                break;
                            case FINGERPRINT_ENROLL_FINISH_VALUE:
                                DebugApp.Builder subBuilder11 = null;
                                if ((this.bitField0_ & 4096) == 4096) {
                                    subBuilder11 = (DebugApp.Builder) this.debug_.toBuilder();
                                }
                                this.debug_ = (DebugApp) input.readMessage(DebugApp.parser(), extensionRegistry);
                                if (subBuilder11 != null) {
                                    subBuilder11.mergeFrom((GeneratedMessageLite) this.debug_);
                                    this.debug_ = (DebugApp) subBuilder11.buildPartial();
                                }
                                this.bitField0_ |= 4096;
                                break;
                            case NS_T_TSIG_VALUE:
                                AppTimeTrackerProto.Builder subBuilder12 = null;
                                if ((this.bitField0_ & 8192) == 8192) {
                                    subBuilder12 = (AppTimeTrackerProto.Builder) this.currentTracker_.toBuilder();
                                }
                                this.currentTracker_ = (AppTimeTrackerProto) input.readMessage(AppTimeTrackerProto.parser(), extensionRegistry);
                                if (subBuilder12 != null) {
                                    subBuilder12.mergeFrom((GeneratedMessageLite) this.currentTracker_);
                                    this.currentTracker_ = (AppTimeTrackerProto) subBuilder12.buildPartial();
                                }
                                this.bitField0_ |= 8192;
                                break;
                            case BACKGROUND_CHECK_SUMMARY_VALUE:
                                MemWatchProcess.Builder subBuilder13 = null;
                                if ((this.bitField0_ & 16384) == 16384) {
                                    subBuilder13 = (MemWatchProcess.Builder) this.memWatchProcesses_.toBuilder();
                                }
                                this.memWatchProcesses_ = (MemWatchProcess) input.readMessage(MemWatchProcess.parser(), extensionRegistry);
                                if (subBuilder13 != null) {
                                    subBuilder13.mergeFrom((GeneratedMessageLite) this.memWatchProcesses_);
                                    this.memWatchProcesses_ = (MemWatchProcess) subBuilder13.buildPartial();
                                }
                                this.bitField0_ |= 16384;
                                break;
                            case 266:
                                String s = input.readString();
                                this.bitField0_ |= 32768;
                                this.trackAllocationApp_ = s;
                                break;
                            case 274:
                                Profile.Builder subBuilder14 = null;
                                if ((this.bitField0_ & 65536) == 65536) {
                                    subBuilder14 = (Profile.Builder) this.profile_.toBuilder();
                                }
                                this.profile_ = (Profile) input.readMessage(Profile.parser(), extensionRegistry);
                                if (subBuilder14 != null) {
                                    subBuilder14.mergeFrom((GeneratedMessageLite) this.profile_);
                                    this.profile_ = (Profile) subBuilder14.buildPartial();
                                }
                                this.bitField0_ |= 65536;
                                break;
                            case 282:
                                String s2 = input.readString();
                                this.bitField0_ |= 131072;
                                this.nativeDebuggingApp_ = s2;
                                break;
                            case 288:
                                this.bitField0_ |= 262144;
                                this.alwaysFinishActivities_ = input.readBool();
                                break;
                            case 298:
                                Controller.Builder subBuilder15 = null;
                                if ((this.bitField0_ & 524288) == 524288) {
                                    subBuilder15 = (Controller.Builder) this.controller_.toBuilder();
                                }
                                this.controller_ = (Controller) input.readMessage(Controller.parser(), extensionRegistry);
                                if (subBuilder15 != null) {
                                    subBuilder15.mergeFrom((GeneratedMessageLite) this.controller_);
                                    this.controller_ = (Controller) subBuilder15.buildPartial();
                                }
                                this.bitField0_ |= 524288;
                                break;
                            case 304:
                                this.bitField0_ |= 1048576;
                                this.totalPersistentProcs_ = input.readInt32();
                                break;
                            case 312:
                                this.bitField0_ |= 2097152;
                                this.processesReady_ = input.readBool();
                                break;
                            case 320:
                                this.bitField0_ |= 4194304;
                                this.systemReady_ = input.readBool();
                                break;
                            case 328:
                                this.bitField0_ |= 8388608;
                                this.booted_ = input.readBool();
                                break;
                            case SOUND_VALUE:
                                this.bitField0_ |= 16777216;
                                this.factoryTest_ = input.readInt32();
                                break;
                            case USER_LOCALE_LIST_VALUE:
                                this.bitField0_ |= 33554432;
                                this.booting_ = input.readBool();
                                break;
                            case 352:
                                this.bitField0_ |= 67108864;
                                this.callFinishBooting_ = input.readBool();
                                break;
                            case 360:
                                this.bitField0_ |= 134217728;
                                this.bootAnimationComplete_ = input.readBool();
                                break;
                            case SUW_ACCESSIBILITY_TOGGLE_SCREEN_MAGNIFICATION_VALUE:
                                this.bitField0_ |= 268435456;
                                this.lastPowerCheckUptimeMs_ = input.readInt64();
                                break;
                            case SETTINGS_CONDITION_BACKGROUND_DATA_VALUE:
                                PowerManagerProto.WakeLock.Builder subBuilder16 = null;
                                if ((this.bitField0_ & 536870912) == 536870912) {
                                    subBuilder16 = (PowerManagerProto.WakeLock.Builder) this.goingToSleep_.toBuilder();
                                }
                                this.goingToSleep_ = (PowerManagerProto.WakeLock) input.readMessage(PowerManagerProto.WakeLock.parser(), extensionRegistry);
                                if (subBuilder16 != null) {
                                    subBuilder16.mergeFrom((GeneratedMessageLite) this.goingToSleep_);
                                    this.goingToSleep_ = (PowerManagerProto.WakeLock) subBuilder16.buildPartial();
                                }
                                this.bitField0_ |= 536870912;
                                break;
                            case 386:
                                PowerManagerProto.WakeLock.Builder subBuilder17 = null;
                                if ((this.bitField0_ & 1073741824) == 1073741824) {
                                    subBuilder17 = (PowerManagerProto.WakeLock.Builder) this.launchingActivity_.toBuilder();
                                }
                                this.launchingActivity_ = (PowerManagerProto.WakeLock) input.readMessage(PowerManagerProto.WakeLock.parser(), extensionRegistry);
                                if (subBuilder17 != null) {
                                    subBuilder17.mergeFrom((GeneratedMessageLite) this.launchingActivity_);
                                    this.launchingActivity_ = (PowerManagerProto.WakeLock) subBuilder17.buildPartial();
                                }
                                this.bitField0_ |= 1073741824;
                                break;
                            case 392:
                                this.bitField0_ |= Integer.MIN_VALUE;
                                this.adjSeq_ = input.readInt32();
                                break;
                            case 400:
                                this.bitField1_ |= 1;
                                this.lruSeq_ = input.readInt32();
                                break;
                            case 408:
                                this.bitField1_ |= 2;
                                this.numNonCachedProcs_ = input.readInt32();
                                break;
                            case 416:
                                this.bitField1_ |= 4;
                                this.numCachedHiddenProcs_ = input.readInt32();
                                break;
                            case 424:
                                this.bitField1_ |= 8;
                                this.numServiceProcs_ = input.readInt32();
                                break;
                            case 432:
                                this.bitField1_ |= 16;
                                this.newNumServiceProcs_ = input.readInt32();
                                break;
                            case 440:
                                this.bitField1_ |= 32;
                                this.allowLowerMemLevel_ = input.readBool();
                                break;
                            case 448:
                                this.bitField1_ |= 64;
                                this.lastMemoryLevel_ = input.readInt32();
                                break;
                            case 456:
                                this.bitField1_ |= 128;
                                this.lastNumProcesses_ = input.readInt32();
                                break;
                            case 466:
                                Duration.Builder subBuilder18 = null;
                                if ((this.bitField1_ & 256) == 256) {
                                    subBuilder18 = (Duration.Builder) this.lastIdleTime_.toBuilder();
                                }
                                this.lastIdleTime_ = (Duration) input.readMessage(Duration.parser(), extensionRegistry);
                                if (subBuilder18 != null) {
                                    subBuilder18.mergeFrom((GeneratedMessageLite) this.lastIdleTime_);
                                    this.lastIdleTime_ = (Duration) subBuilder18.buildPartial();
                                }
                                this.bitField1_ = 256 | this.bitField1_;
                                break;
                            case 472:
                                this.bitField1_ = 512 | this.bitField1_;
                                this.lowRamSinceLastIdleMs_ = input.readInt64();
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
                    synchronized (ActivityManagerServiceDumpProcessesProto.class) {
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

    public static ActivityManagerServiceDumpProcessesProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<ActivityManagerServiceDumpProcessesProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
