package android.os;

import android.app.job.StopReasonEnum;
import android.os.ControllerActivityProto;
import android.os.PowerManagerProto;
import android.os.TimerProto;
import com.android.os.AtomsProto;
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

public final class UidProto extends GeneratedMessageLite<UidProto, Builder> implements UidProtoOrBuilder {
    public static final int AGGREGATED_WAKELOCK_FIELD_NUMBER = 24;
    public static final int AUDIO_FIELD_NUMBER = 8;
    public static final int BLUETOOTH_CONTROLLER_FIELD_NUMBER = 3;
    public static final int BLUETOOTH_MISC_FIELD_NUMBER = 6;
    public static final int CAMERA_FIELD_NUMBER = 9;
    public static final int CPU_FIELD_NUMBER = 7;
    private static final UidProto DEFAULT_INSTANCE = new UidProto();
    public static final int FLASHLIGHT_FIELD_NUMBER = 10;
    public static final int FOREGROUND_ACTIVITY_FIELD_NUMBER = 11;
    public static final int FOREGROUND_SERVICE_FIELD_NUMBER = 12;
    public static final int JOBS_FIELD_NUMBER = 15;
    public static final int JOB_COMPLETION_FIELD_NUMBER = 16;
    public static final int MODEM_CONTROLLER_FIELD_NUMBER = 4;
    public static final int NETWORK_FIELD_NUMBER = 17;
    public static final int PACKAGES_FIELD_NUMBER = 2;
    private static volatile Parser<UidProto> PARSER = null;
    public static final int POWER_USE_ITEM_FIELD_NUMBER = 18;
    public static final int PROCESS_FIELD_NUMBER = 19;
    public static final int SENSORS_FIELD_NUMBER = 21;
    public static final int STATES_FIELD_NUMBER = 20;
    public static final int SYNCS_FIELD_NUMBER = 22;
    public static final int UID_FIELD_NUMBER = 1;
    public static final int USER_ACTIVITY_FIELD_NUMBER = 23;
    public static final int VIBRATOR_FIELD_NUMBER = 13;
    public static final int VIDEO_FIELD_NUMBER = 14;
    public static final int WAKELOCKS_FIELD_NUMBER = 25;
    public static final int WAKEUP_ALARM_FIELD_NUMBER = 26;
    public static final int WIFI_CONTROLLER_FIELD_NUMBER = 5;
    public static final int WIFI_FIELD_NUMBER = 27;
    public static final int WIFI_MULTICAST_WAKELOCK_FIELD_NUMBER = 28;
    private AggregatedWakelock aggregatedWakelock_;
    private TimerProto audio_;
    private int bitField0_;
    private ControllerActivityProto bluetoothController_;
    private BluetoothMisc bluetoothMisc_;
    private TimerProto camera_;
    private Cpu cpu_;
    private TimerProto flashlight_;
    private TimerProto foregroundActivity_;
    private TimerProto foregroundService_;
    private Internal.ProtobufList<JobCompletion> jobCompletion_ = emptyProtobufList();
    private Internal.ProtobufList<Job> jobs_ = emptyProtobufList();
    private ControllerActivityProto modemController_;
    private Network network_;
    private Internal.ProtobufList<Package> packages_ = emptyProtobufList();
    private PowerUseItem powerUseItem_;
    private Internal.ProtobufList<Process> process_ = emptyProtobufList();
    private Internal.ProtobufList<Sensor> sensors_ = emptyProtobufList();
    private Internal.ProtobufList<StateTime> states_ = emptyProtobufList();
    private Internal.ProtobufList<Sync> syncs_ = emptyProtobufList();
    private int uid_ = 0;
    private Internal.ProtobufList<UserActivity> userActivity_ = emptyProtobufList();
    private TimerProto vibrator_;
    private TimerProto video_;
    private Internal.ProtobufList<Wakelock> wakelocks_ = emptyProtobufList();
    private Internal.ProtobufList<WakeupAlarm> wakeupAlarm_ = emptyProtobufList();
    private ControllerActivityProto wifiController_;
    private TimerProto wifiMulticastWakelock_;
    private Wifi wifi_;

    public interface AggregatedWakelockOrBuilder extends MessageLiteOrBuilder {
        long getBackgroundPartialDurationMs();

        long getPartialDurationMs();

        boolean hasBackgroundPartialDurationMs();

        boolean hasPartialDurationMs();
    }

    public interface BluetoothMiscOrBuilder extends MessageLiteOrBuilder {
        TimerProto getApportionedBleScan();

        TimerProto getBackgroundBleScan();

        int getBackgroundBleScanResultCount();

        TimerProto getBackgroundUnoptimizedBleScan();

        int getBleScanResultCount();

        TimerProto getUnoptimizedBleScan();

        boolean hasApportionedBleScan();

        boolean hasBackgroundBleScan();

        boolean hasBackgroundBleScanResultCount();

        boolean hasBackgroundUnoptimizedBleScan();

        boolean hasBleScanResultCount();

        boolean hasUnoptimizedBleScan();
    }

    public interface CpuOrBuilder extends MessageLiteOrBuilder {
        Cpu.ByFrequency getByFrequency(int i);

        int getByFrequencyCount();

        List<Cpu.ByFrequency> getByFrequencyList();

        Cpu.ByProcessState getByProcessState(int i);

        int getByProcessStateCount();

        List<Cpu.ByProcessState> getByProcessStateList();

        long getSystemDurationMs();

        long getUserDurationMs();

        boolean hasSystemDurationMs();

        boolean hasUserDurationMs();
    }

    public interface JobCompletionOrBuilder extends MessageLiteOrBuilder {
        String getName();

        ByteString getNameBytes();

        JobCompletion.ReasonCount getReasonCount(int i);

        int getReasonCountCount();

        List<JobCompletion.ReasonCount> getReasonCountList();

        boolean hasName();
    }

    public interface JobOrBuilder extends MessageLiteOrBuilder {
        TimerProto getBackground();

        String getName();

        ByteString getNameBytes();

        TimerProto getTotal();

        boolean hasBackground();

        boolean hasName();

        boolean hasTotal();
    }

    public interface NetworkOrBuilder extends MessageLiteOrBuilder {
        long getBtBytesRx();

        long getBtBytesTx();

        int getMobileActiveCount();

        long getMobileActiveDurationMs();

        long getMobileBytesBgRx();

        long getMobileBytesBgTx();

        long getMobileBytesRx();

        long getMobileBytesTx();

        long getMobilePacketsBgRx();

        long getMobilePacketsBgTx();

        long getMobilePacketsRx();

        long getMobilePacketsTx();

        int getMobileWakeupCount();

        long getWifiBytesBgRx();

        long getWifiBytesBgTx();

        long getWifiBytesRx();

        long getWifiBytesTx();

        long getWifiPacketsBgRx();

        long getWifiPacketsBgTx();

        long getWifiPacketsRx();

        long getWifiPacketsTx();

        int getWifiWakeupCount();

        boolean hasBtBytesRx();

        boolean hasBtBytesTx();

        boolean hasMobileActiveCount();

        boolean hasMobileActiveDurationMs();

        boolean hasMobileBytesBgRx();

        boolean hasMobileBytesBgTx();

        boolean hasMobileBytesRx();

        boolean hasMobileBytesTx();

        boolean hasMobilePacketsBgRx();

        boolean hasMobilePacketsBgTx();

        boolean hasMobilePacketsRx();

        boolean hasMobilePacketsTx();

        boolean hasMobileWakeupCount();

        boolean hasWifiBytesBgRx();

        boolean hasWifiBytesBgTx();

        boolean hasWifiBytesRx();

        boolean hasWifiBytesTx();

        boolean hasWifiPacketsBgRx();

        boolean hasWifiPacketsBgTx();

        boolean hasWifiPacketsRx();

        boolean hasWifiPacketsTx();

        boolean hasWifiWakeupCount();
    }

    public interface PackageOrBuilder extends MessageLiteOrBuilder {
        String getName();

        ByteString getNameBytes();

        Package.Service getServices(int i);

        int getServicesCount();

        List<Package.Service> getServicesList();

        boolean hasName();
    }

    public interface PowerUseItemOrBuilder extends MessageLiteOrBuilder {
        double getComputedPowerMah();

        double getProportionalSmearMah();

        double getScreenPowerMah();

        boolean getShouldHide();

        boolean hasComputedPowerMah();

        boolean hasProportionalSmearMah();

        boolean hasScreenPowerMah();

        boolean hasShouldHide();
    }

    public interface ProcessOrBuilder extends MessageLiteOrBuilder {
        int getAnrCount();

        int getCrashCount();

        long getForegroundDurationMs();

        String getName();

        ByteString getNameBytes();

        int getStartCount();

        long getSystemDurationMs();

        long getUserDurationMs();

        boolean hasAnrCount();

        boolean hasCrashCount();

        boolean hasForegroundDurationMs();

        boolean hasName();

        boolean hasStartCount();

        boolean hasSystemDurationMs();

        boolean hasUserDurationMs();
    }

    public interface SensorOrBuilder extends MessageLiteOrBuilder {
        TimerProto getApportioned();

        TimerProto getBackground();

        int getId();

        boolean hasApportioned();

        boolean hasBackground();

        boolean hasId();
    }

    public interface StateTimeOrBuilder extends MessageLiteOrBuilder {
        long getDurationMs();

        StateTime.State getState();

        boolean hasDurationMs();

        boolean hasState();
    }

    public interface SyncOrBuilder extends MessageLiteOrBuilder {
        TimerProto getBackground();

        String getName();

        ByteString getNameBytes();

        TimerProto getTotal();

        boolean hasBackground();

        boolean hasName();

        boolean hasTotal();
    }

    public interface UserActivityOrBuilder extends MessageLiteOrBuilder {
        int getCount();

        PowerManagerProto.UserActivityEvent getName();

        boolean hasCount();

        boolean hasName();
    }

    public interface WakelockOrBuilder extends MessageLiteOrBuilder {
        TimerProto getBackgroundPartial();

        TimerProto getFull();

        String getName();

        ByteString getNameBytes();

        TimerProto getPartial();

        TimerProto getWindow();

        boolean hasBackgroundPartial();

        boolean hasFull();

        boolean hasName();

        boolean hasPartial();

        boolean hasWindow();
    }

    public interface WakeupAlarmOrBuilder extends MessageLiteOrBuilder {
        int getCount();

        String getName();

        ByteString getNameBytes();

        boolean hasCount();

        boolean hasName();
    }

    public interface WifiOrBuilder extends MessageLiteOrBuilder {
        TimerProto getApportionedScan();

        TimerProto getBackgroundScan();

        long getFullWifiLockDurationMs();

        long getRunningDurationMs();

        boolean hasApportionedScan();

        boolean hasBackgroundScan();

        boolean hasFullWifiLockDurationMs();

        boolean hasRunningDurationMs();
    }

    private UidProto() {
    }

    public static final class Package extends GeneratedMessageLite<Package, Builder> implements PackageOrBuilder {
        private static final Package DEFAULT_INSTANCE = new Package();
        public static final int NAME_FIELD_NUMBER = 1;
        private static volatile Parser<Package> PARSER = null;
        public static final int SERVICES_FIELD_NUMBER = 2;
        private int bitField0_;
        private String name_ = "";
        private Internal.ProtobufList<Service> services_ = emptyProtobufList();

        public interface ServiceOrBuilder extends MessageLiteOrBuilder {
            int getLaunchCount();

            String getName();

            ByteString getNameBytes();

            int getStartCount();

            long getStartDurationMs();

            boolean hasLaunchCount();

            boolean hasName();

            boolean hasStartCount();

            boolean hasStartDurationMs();
        }

        private Package() {
        }

        public static final class Service extends GeneratedMessageLite<Service, Builder> implements ServiceOrBuilder {
            private static final Service DEFAULT_INSTANCE = new Service();
            public static final int LAUNCH_COUNT_FIELD_NUMBER = 4;
            public static final int NAME_FIELD_NUMBER = 1;
            private static volatile Parser<Service> PARSER = null;
            public static final int START_COUNT_FIELD_NUMBER = 3;
            public static final int START_DURATION_MS_FIELD_NUMBER = 2;
            private int bitField0_;
            private int launchCount_ = 0;
            private String name_ = "";
            private int startCount_ = 0;
            private long startDurationMs_ = 0;

            private Service() {
            }

            @Override // android.os.UidProto.Package.ServiceOrBuilder
            public boolean hasName() {
                return (this.bitField0_ & 1) == 1;
            }

            @Override // android.os.UidProto.Package.ServiceOrBuilder
            public String getName() {
                return this.name_;
            }

            @Override // android.os.UidProto.Package.ServiceOrBuilder
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

            @Override // android.os.UidProto.Package.ServiceOrBuilder
            public boolean hasStartDurationMs() {
                return (this.bitField0_ & 2) == 2;
            }

            @Override // android.os.UidProto.Package.ServiceOrBuilder
            public long getStartDurationMs() {
                return this.startDurationMs_;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void setStartDurationMs(long value) {
                this.bitField0_ |= 2;
                this.startDurationMs_ = value;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void clearStartDurationMs() {
                this.bitField0_ &= -3;
                this.startDurationMs_ = 0;
            }

            @Override // android.os.UidProto.Package.ServiceOrBuilder
            public boolean hasStartCount() {
                return (this.bitField0_ & 4) == 4;
            }

            @Override // android.os.UidProto.Package.ServiceOrBuilder
            public int getStartCount() {
                return this.startCount_;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void setStartCount(int value) {
                this.bitField0_ |= 4;
                this.startCount_ = value;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void clearStartCount() {
                this.bitField0_ &= -5;
                this.startCount_ = 0;
            }

            @Override // android.os.UidProto.Package.ServiceOrBuilder
            public boolean hasLaunchCount() {
                return (this.bitField0_ & 8) == 8;
            }

            @Override // android.os.UidProto.Package.ServiceOrBuilder
            public int getLaunchCount() {
                return this.launchCount_;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void setLaunchCount(int value) {
                this.bitField0_ |= 8;
                this.launchCount_ = value;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void clearLaunchCount() {
                this.bitField0_ &= -9;
                this.launchCount_ = 0;
            }

            @Override // com.google.protobuf.MessageLite
            public void writeTo(CodedOutputStream output) throws IOException {
                if ((this.bitField0_ & 1) == 1) {
                    output.writeString(1, getName());
                }
                if ((this.bitField0_ & 2) == 2) {
                    output.writeInt64(2, this.startDurationMs_);
                }
                if ((this.bitField0_ & 4) == 4) {
                    output.writeInt32(3, this.startCount_);
                }
                if ((this.bitField0_ & 8) == 8) {
                    output.writeInt32(4, this.launchCount_);
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
                if ((this.bitField0_ & 2) == 2) {
                    size2 += CodedOutputStream.computeInt64Size(2, this.startDurationMs_);
                }
                if ((this.bitField0_ & 4) == 4) {
                    size2 += CodedOutputStream.computeInt32Size(3, this.startCount_);
                }
                if ((this.bitField0_ & 8) == 8) {
                    size2 += CodedOutputStream.computeInt32Size(4, this.launchCount_);
                }
                int size3 = size2 + this.unknownFields.getSerializedSize();
                this.memoizedSerializedSize = size3;
                return size3;
            }

            public static Service parseFrom(ByteString data) throws InvalidProtocolBufferException {
                return (Service) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
            }

            public static Service parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return (Service) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
            }

            public static Service parseFrom(byte[] data) throws InvalidProtocolBufferException {
                return (Service) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
            }

            public static Service parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return (Service) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
            }

            public static Service parseFrom(InputStream input) throws IOException {
                return (Service) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
            }

            public static Service parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (Service) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static Service parseDelimitedFrom(InputStream input) throws IOException {
                return (Service) parseDelimitedFrom(DEFAULT_INSTANCE, input);
            }

            public static Service parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (Service) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static Service parseFrom(CodedInputStream input) throws IOException {
                return (Service) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
            }

            public static Service parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (Service) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static Builder newBuilder() {
                return (Builder) DEFAULT_INSTANCE.toBuilder();
            }

            public static Builder newBuilder(Service prototype) {
                return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
            }

            public static final class Builder extends GeneratedMessageLite.Builder<Service, Builder> implements ServiceOrBuilder {
                private Builder() {
                    super(Service.DEFAULT_INSTANCE);
                }

                @Override // android.os.UidProto.Package.ServiceOrBuilder
                public boolean hasName() {
                    return ((Service) this.instance).hasName();
                }

                @Override // android.os.UidProto.Package.ServiceOrBuilder
                public String getName() {
                    return ((Service) this.instance).getName();
                }

                @Override // android.os.UidProto.Package.ServiceOrBuilder
                public ByteString getNameBytes() {
                    return ((Service) this.instance).getNameBytes();
                }

                public Builder setName(String value) {
                    copyOnWrite();
                    ((Service) this.instance).setName(value);
                    return this;
                }

                public Builder clearName() {
                    copyOnWrite();
                    ((Service) this.instance).clearName();
                    return this;
                }

                public Builder setNameBytes(ByteString value) {
                    copyOnWrite();
                    ((Service) this.instance).setNameBytes(value);
                    return this;
                }

                @Override // android.os.UidProto.Package.ServiceOrBuilder
                public boolean hasStartDurationMs() {
                    return ((Service) this.instance).hasStartDurationMs();
                }

                @Override // android.os.UidProto.Package.ServiceOrBuilder
                public long getStartDurationMs() {
                    return ((Service) this.instance).getStartDurationMs();
                }

                public Builder setStartDurationMs(long value) {
                    copyOnWrite();
                    ((Service) this.instance).setStartDurationMs(value);
                    return this;
                }

                public Builder clearStartDurationMs() {
                    copyOnWrite();
                    ((Service) this.instance).clearStartDurationMs();
                    return this;
                }

                @Override // android.os.UidProto.Package.ServiceOrBuilder
                public boolean hasStartCount() {
                    return ((Service) this.instance).hasStartCount();
                }

                @Override // android.os.UidProto.Package.ServiceOrBuilder
                public int getStartCount() {
                    return ((Service) this.instance).getStartCount();
                }

                public Builder setStartCount(int value) {
                    copyOnWrite();
                    ((Service) this.instance).setStartCount(value);
                    return this;
                }

                public Builder clearStartCount() {
                    copyOnWrite();
                    ((Service) this.instance).clearStartCount();
                    return this;
                }

                @Override // android.os.UidProto.Package.ServiceOrBuilder
                public boolean hasLaunchCount() {
                    return ((Service) this.instance).hasLaunchCount();
                }

                @Override // android.os.UidProto.Package.ServiceOrBuilder
                public int getLaunchCount() {
                    return ((Service) this.instance).getLaunchCount();
                }

                public Builder setLaunchCount(int value) {
                    copyOnWrite();
                    ((Service) this.instance).setLaunchCount(value);
                    return this;
                }

                public Builder clearLaunchCount() {
                    copyOnWrite();
                    ((Service) this.instance).clearLaunchCount();
                    return this;
                }
            }

            /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
            /* access modifiers changed from: protected */
            @Override // com.google.protobuf.GeneratedMessageLite
            public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
                switch (method) {
                    case NEW_MUTABLE_INSTANCE:
                        return new Service();
                    case IS_INITIALIZED:
                        return DEFAULT_INSTANCE;
                    case MAKE_IMMUTABLE:
                        return null;
                    case NEW_BUILDER:
                        return new Builder();
                    case VISIT:
                        GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                        Service other = (Service) arg1;
                        this.name_ = visitor.visitString(hasName(), this.name_, other.hasName(), other.name_);
                        this.startDurationMs_ = visitor.visitLong(hasStartDurationMs(), this.startDurationMs_, other.hasStartDurationMs(), other.startDurationMs_);
                        this.startCount_ = visitor.visitInt(hasStartCount(), this.startCount_, other.hasStartCount(), other.startCount_);
                        this.launchCount_ = visitor.visitInt(hasLaunchCount(), this.launchCount_, other.hasLaunchCount(), other.launchCount_);
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
                                    this.name_ = s;
                                } else if (tag == 16) {
                                    this.bitField0_ |= 2;
                                    this.startDurationMs_ = input.readInt64();
                                } else if (tag == 24) {
                                    this.bitField0_ |= 4;
                                    this.startCount_ = input.readInt32();
                                } else if (tag == 32) {
                                    this.bitField0_ |= 8;
                                    this.launchCount_ = input.readInt32();
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
                            synchronized (Service.class) {
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

            public static Service getDefaultInstance() {
                return DEFAULT_INSTANCE;
            }

            public static Parser<Service> parser() {
                return DEFAULT_INSTANCE.getParserForType();
            }
        }

        @Override // android.os.UidProto.PackageOrBuilder
        public boolean hasName() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // android.os.UidProto.PackageOrBuilder
        public String getName() {
            return this.name_;
        }

        @Override // android.os.UidProto.PackageOrBuilder
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

        @Override // android.os.UidProto.PackageOrBuilder
        public List<Service> getServicesList() {
            return this.services_;
        }

        public List<? extends ServiceOrBuilder> getServicesOrBuilderList() {
            return this.services_;
        }

        @Override // android.os.UidProto.PackageOrBuilder
        public int getServicesCount() {
            return this.services_.size();
        }

        @Override // android.os.UidProto.PackageOrBuilder
        public Service getServices(int index) {
            return this.services_.get(index);
        }

        public ServiceOrBuilder getServicesOrBuilder(int index) {
            return this.services_.get(index);
        }

        private void ensureServicesIsMutable() {
            if (!this.services_.isModifiable()) {
                this.services_ = GeneratedMessageLite.mutableCopy(this.services_);
            }
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setServices(int index, Service value) {
            if (value != null) {
                ensureServicesIsMutable();
                this.services_.set(index, value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setServices(int index, Service.Builder builderForValue) {
            ensureServicesIsMutable();
            this.services_.set(index, (Service) builderForValue.build());
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addServices(Service value) {
            if (value != null) {
                ensureServicesIsMutable();
                this.services_.add(value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addServices(int index, Service value) {
            if (value != null) {
                ensureServicesIsMutable();
                this.services_.add(index, value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addServices(Service.Builder builderForValue) {
            ensureServicesIsMutable();
            this.services_.add((Service) builderForValue.build());
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addServices(int index, Service.Builder builderForValue) {
            ensureServicesIsMutable();
            this.services_.add(index, (Service) builderForValue.build());
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addAllServices(Iterable<? extends Service> values) {
            ensureServicesIsMutable();
            AbstractMessageLite.addAll(values, this.services_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearServices() {
            this.services_ = emptyProtobufList();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void removeServices(int index) {
            ensureServicesIsMutable();
            this.services_.remove(index);
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                output.writeString(1, getName());
            }
            for (int i = 0; i < this.services_.size(); i++) {
                output.writeMessage(2, this.services_.get(i));
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
            for (int i = 0; i < this.services_.size(); i++) {
                size2 += CodedOutputStream.computeMessageSize(2, this.services_.get(i));
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

            @Override // android.os.UidProto.PackageOrBuilder
            public boolean hasName() {
                return ((Package) this.instance).hasName();
            }

            @Override // android.os.UidProto.PackageOrBuilder
            public String getName() {
                return ((Package) this.instance).getName();
            }

            @Override // android.os.UidProto.PackageOrBuilder
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

            @Override // android.os.UidProto.PackageOrBuilder
            public List<Service> getServicesList() {
                return Collections.unmodifiableList(((Package) this.instance).getServicesList());
            }

            @Override // android.os.UidProto.PackageOrBuilder
            public int getServicesCount() {
                return ((Package) this.instance).getServicesCount();
            }

            @Override // android.os.UidProto.PackageOrBuilder
            public Service getServices(int index) {
                return ((Package) this.instance).getServices(index);
            }

            public Builder setServices(int index, Service value) {
                copyOnWrite();
                ((Package) this.instance).setServices((Package) index, (int) value);
                return this;
            }

            public Builder setServices(int index, Service.Builder builderForValue) {
                copyOnWrite();
                ((Package) this.instance).setServices((Package) index, (int) builderForValue);
                return this;
            }

            public Builder addServices(Service value) {
                copyOnWrite();
                ((Package) this.instance).addServices((Package) value);
                return this;
            }

            public Builder addServices(int index, Service value) {
                copyOnWrite();
                ((Package) this.instance).addServices((Package) index, (int) value);
                return this;
            }

            public Builder addServices(Service.Builder builderForValue) {
                copyOnWrite();
                ((Package) this.instance).addServices((Package) builderForValue);
                return this;
            }

            public Builder addServices(int index, Service.Builder builderForValue) {
                copyOnWrite();
                ((Package) this.instance).addServices((Package) index, (int) builderForValue);
                return this;
            }

            public Builder addAllServices(Iterable<? extends Service> values) {
                copyOnWrite();
                ((Package) this.instance).addAllServices(values);
                return this;
            }

            public Builder clearServices() {
                copyOnWrite();
                ((Package) this.instance).clearServices();
                return this;
            }

            public Builder removeServices(int index) {
                copyOnWrite();
                ((Package) this.instance).removeServices(index);
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
                    this.services_.makeImmutable();
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    Package other = (Package) arg1;
                    this.name_ = visitor.visitString(hasName(), this.name_, other.hasName(), other.name_);
                    this.services_ = visitor.visitList(this.services_, other.services_);
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
                                if (!this.services_.isModifiable()) {
                                    this.services_ = GeneratedMessageLite.mutableCopy(this.services_);
                                }
                                this.services_.add((Service) input.readMessage(Service.parser(), extensionRegistry));
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

    public static final class BluetoothMisc extends GeneratedMessageLite<BluetoothMisc, Builder> implements BluetoothMiscOrBuilder {
        public static final int APPORTIONED_BLE_SCAN_FIELD_NUMBER = 1;
        public static final int BACKGROUND_BLE_SCAN_FIELD_NUMBER = 2;
        public static final int BACKGROUND_BLE_SCAN_RESULT_COUNT_FIELD_NUMBER = 6;
        public static final int BACKGROUND_UNOPTIMIZED_BLE_SCAN_FIELD_NUMBER = 4;
        public static final int BLE_SCAN_RESULT_COUNT_FIELD_NUMBER = 5;
        private static final BluetoothMisc DEFAULT_INSTANCE = new BluetoothMisc();
        private static volatile Parser<BluetoothMisc> PARSER = null;
        public static final int UNOPTIMIZED_BLE_SCAN_FIELD_NUMBER = 3;
        private TimerProto apportionedBleScan_;
        private int backgroundBleScanResultCount_ = 0;
        private TimerProto backgroundBleScan_;
        private TimerProto backgroundUnoptimizedBleScan_;
        private int bitField0_;
        private int bleScanResultCount_ = 0;
        private TimerProto unoptimizedBleScan_;

        private BluetoothMisc() {
        }

        @Override // android.os.UidProto.BluetoothMiscOrBuilder
        public boolean hasApportionedBleScan() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // android.os.UidProto.BluetoothMiscOrBuilder
        public TimerProto getApportionedBleScan() {
            TimerProto timerProto = this.apportionedBleScan_;
            return timerProto == null ? TimerProto.getDefaultInstance() : timerProto;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setApportionedBleScan(TimerProto value) {
            if (value != null) {
                this.apportionedBleScan_ = value;
                this.bitField0_ |= 1;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setApportionedBleScan(TimerProto.Builder builderForValue) {
            this.apportionedBleScan_ = (TimerProto) builderForValue.build();
            this.bitField0_ |= 1;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void mergeApportionedBleScan(TimerProto value) {
            TimerProto timerProto = this.apportionedBleScan_;
            if (timerProto == null || timerProto == TimerProto.getDefaultInstance()) {
                this.apportionedBleScan_ = value;
            } else {
                this.apportionedBleScan_ = (TimerProto) ((TimerProto.Builder) TimerProto.newBuilder(this.apportionedBleScan_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
            }
            this.bitField0_ |= 1;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearApportionedBleScan() {
            this.apportionedBleScan_ = null;
            this.bitField0_ &= -2;
        }

        @Override // android.os.UidProto.BluetoothMiscOrBuilder
        public boolean hasBackgroundBleScan() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // android.os.UidProto.BluetoothMiscOrBuilder
        public TimerProto getBackgroundBleScan() {
            TimerProto timerProto = this.backgroundBleScan_;
            return timerProto == null ? TimerProto.getDefaultInstance() : timerProto;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setBackgroundBleScan(TimerProto value) {
            if (value != null) {
                this.backgroundBleScan_ = value;
                this.bitField0_ |= 2;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setBackgroundBleScan(TimerProto.Builder builderForValue) {
            this.backgroundBleScan_ = (TimerProto) builderForValue.build();
            this.bitField0_ |= 2;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void mergeBackgroundBleScan(TimerProto value) {
            TimerProto timerProto = this.backgroundBleScan_;
            if (timerProto == null || timerProto == TimerProto.getDefaultInstance()) {
                this.backgroundBleScan_ = value;
            } else {
                this.backgroundBleScan_ = (TimerProto) ((TimerProto.Builder) TimerProto.newBuilder(this.backgroundBleScan_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
            }
            this.bitField0_ |= 2;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearBackgroundBleScan() {
            this.backgroundBleScan_ = null;
            this.bitField0_ &= -3;
        }

        @Override // android.os.UidProto.BluetoothMiscOrBuilder
        public boolean hasUnoptimizedBleScan() {
            return (this.bitField0_ & 4) == 4;
        }

        @Override // android.os.UidProto.BluetoothMiscOrBuilder
        public TimerProto getUnoptimizedBleScan() {
            TimerProto timerProto = this.unoptimizedBleScan_;
            return timerProto == null ? TimerProto.getDefaultInstance() : timerProto;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setUnoptimizedBleScan(TimerProto value) {
            if (value != null) {
                this.unoptimizedBleScan_ = value;
                this.bitField0_ |= 4;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setUnoptimizedBleScan(TimerProto.Builder builderForValue) {
            this.unoptimizedBleScan_ = (TimerProto) builderForValue.build();
            this.bitField0_ |= 4;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void mergeUnoptimizedBleScan(TimerProto value) {
            TimerProto timerProto = this.unoptimizedBleScan_;
            if (timerProto == null || timerProto == TimerProto.getDefaultInstance()) {
                this.unoptimizedBleScan_ = value;
            } else {
                this.unoptimizedBleScan_ = (TimerProto) ((TimerProto.Builder) TimerProto.newBuilder(this.unoptimizedBleScan_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
            }
            this.bitField0_ |= 4;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearUnoptimizedBleScan() {
            this.unoptimizedBleScan_ = null;
            this.bitField0_ &= -5;
        }

        @Override // android.os.UidProto.BluetoothMiscOrBuilder
        public boolean hasBackgroundUnoptimizedBleScan() {
            return (this.bitField0_ & 8) == 8;
        }

        @Override // android.os.UidProto.BluetoothMiscOrBuilder
        public TimerProto getBackgroundUnoptimizedBleScan() {
            TimerProto timerProto = this.backgroundUnoptimizedBleScan_;
            return timerProto == null ? TimerProto.getDefaultInstance() : timerProto;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setBackgroundUnoptimizedBleScan(TimerProto value) {
            if (value != null) {
                this.backgroundUnoptimizedBleScan_ = value;
                this.bitField0_ |= 8;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setBackgroundUnoptimizedBleScan(TimerProto.Builder builderForValue) {
            this.backgroundUnoptimizedBleScan_ = (TimerProto) builderForValue.build();
            this.bitField0_ |= 8;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void mergeBackgroundUnoptimizedBleScan(TimerProto value) {
            TimerProto timerProto = this.backgroundUnoptimizedBleScan_;
            if (timerProto == null || timerProto == TimerProto.getDefaultInstance()) {
                this.backgroundUnoptimizedBleScan_ = value;
            } else {
                this.backgroundUnoptimizedBleScan_ = (TimerProto) ((TimerProto.Builder) TimerProto.newBuilder(this.backgroundUnoptimizedBleScan_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
            }
            this.bitField0_ |= 8;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearBackgroundUnoptimizedBleScan() {
            this.backgroundUnoptimizedBleScan_ = null;
            this.bitField0_ &= -9;
        }

        @Override // android.os.UidProto.BluetoothMiscOrBuilder
        public boolean hasBleScanResultCount() {
            return (this.bitField0_ & 16) == 16;
        }

        @Override // android.os.UidProto.BluetoothMiscOrBuilder
        public int getBleScanResultCount() {
            return this.bleScanResultCount_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setBleScanResultCount(int value) {
            this.bitField0_ |= 16;
            this.bleScanResultCount_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearBleScanResultCount() {
            this.bitField0_ &= -17;
            this.bleScanResultCount_ = 0;
        }

        @Override // android.os.UidProto.BluetoothMiscOrBuilder
        public boolean hasBackgroundBleScanResultCount() {
            return (this.bitField0_ & 32) == 32;
        }

        @Override // android.os.UidProto.BluetoothMiscOrBuilder
        public int getBackgroundBleScanResultCount() {
            return this.backgroundBleScanResultCount_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setBackgroundBleScanResultCount(int value) {
            this.bitField0_ |= 32;
            this.backgroundBleScanResultCount_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearBackgroundBleScanResultCount() {
            this.bitField0_ &= -33;
            this.backgroundBleScanResultCount_ = 0;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                output.writeMessage(1, getApportionedBleScan());
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeMessage(2, getBackgroundBleScan());
            }
            if ((this.bitField0_ & 4) == 4) {
                output.writeMessage(3, getUnoptimizedBleScan());
            }
            if ((this.bitField0_ & 8) == 8) {
                output.writeMessage(4, getBackgroundUnoptimizedBleScan());
            }
            if ((this.bitField0_ & 16) == 16) {
                output.writeInt32(5, this.bleScanResultCount_);
            }
            if ((this.bitField0_ & 32) == 32) {
                output.writeInt32(6, this.backgroundBleScanResultCount_);
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
                size2 = 0 + CodedOutputStream.computeMessageSize(1, getApportionedBleScan());
            }
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeMessageSize(2, getBackgroundBleScan());
            }
            if ((this.bitField0_ & 4) == 4) {
                size2 += CodedOutputStream.computeMessageSize(3, getUnoptimizedBleScan());
            }
            if ((this.bitField0_ & 8) == 8) {
                size2 += CodedOutputStream.computeMessageSize(4, getBackgroundUnoptimizedBleScan());
            }
            if ((this.bitField0_ & 16) == 16) {
                size2 += CodedOutputStream.computeInt32Size(5, this.bleScanResultCount_);
            }
            if ((this.bitField0_ & 32) == 32) {
                size2 += CodedOutputStream.computeInt32Size(6, this.backgroundBleScanResultCount_);
            }
            int size3 = size2 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static BluetoothMisc parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (BluetoothMisc) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static BluetoothMisc parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (BluetoothMisc) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static BluetoothMisc parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (BluetoothMisc) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static BluetoothMisc parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (BluetoothMisc) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static BluetoothMisc parseFrom(InputStream input) throws IOException {
            return (BluetoothMisc) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static BluetoothMisc parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (BluetoothMisc) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static BluetoothMisc parseDelimitedFrom(InputStream input) throws IOException {
            return (BluetoothMisc) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static BluetoothMisc parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (BluetoothMisc) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static BluetoothMisc parseFrom(CodedInputStream input) throws IOException {
            return (BluetoothMisc) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static BluetoothMisc parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (BluetoothMisc) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(BluetoothMisc prototype) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<BluetoothMisc, Builder> implements BluetoothMiscOrBuilder {
            private Builder() {
                super(BluetoothMisc.DEFAULT_INSTANCE);
            }

            @Override // android.os.UidProto.BluetoothMiscOrBuilder
            public boolean hasApportionedBleScan() {
                return ((BluetoothMisc) this.instance).hasApportionedBleScan();
            }

            @Override // android.os.UidProto.BluetoothMiscOrBuilder
            public TimerProto getApportionedBleScan() {
                return ((BluetoothMisc) this.instance).getApportionedBleScan();
            }

            public Builder setApportionedBleScan(TimerProto value) {
                copyOnWrite();
                ((BluetoothMisc) this.instance).setApportionedBleScan((BluetoothMisc) value);
                return this;
            }

            public Builder setApportionedBleScan(TimerProto.Builder builderForValue) {
                copyOnWrite();
                ((BluetoothMisc) this.instance).setApportionedBleScan((BluetoothMisc) builderForValue);
                return this;
            }

            public Builder mergeApportionedBleScan(TimerProto value) {
                copyOnWrite();
                ((BluetoothMisc) this.instance).mergeApportionedBleScan(value);
                return this;
            }

            public Builder clearApportionedBleScan() {
                copyOnWrite();
                ((BluetoothMisc) this.instance).clearApportionedBleScan();
                return this;
            }

            @Override // android.os.UidProto.BluetoothMiscOrBuilder
            public boolean hasBackgroundBleScan() {
                return ((BluetoothMisc) this.instance).hasBackgroundBleScan();
            }

            @Override // android.os.UidProto.BluetoothMiscOrBuilder
            public TimerProto getBackgroundBleScan() {
                return ((BluetoothMisc) this.instance).getBackgroundBleScan();
            }

            public Builder setBackgroundBleScan(TimerProto value) {
                copyOnWrite();
                ((BluetoothMisc) this.instance).setBackgroundBleScan((BluetoothMisc) value);
                return this;
            }

            public Builder setBackgroundBleScan(TimerProto.Builder builderForValue) {
                copyOnWrite();
                ((BluetoothMisc) this.instance).setBackgroundBleScan((BluetoothMisc) builderForValue);
                return this;
            }

            public Builder mergeBackgroundBleScan(TimerProto value) {
                copyOnWrite();
                ((BluetoothMisc) this.instance).mergeBackgroundBleScan(value);
                return this;
            }

            public Builder clearBackgroundBleScan() {
                copyOnWrite();
                ((BluetoothMisc) this.instance).clearBackgroundBleScan();
                return this;
            }

            @Override // android.os.UidProto.BluetoothMiscOrBuilder
            public boolean hasUnoptimizedBleScan() {
                return ((BluetoothMisc) this.instance).hasUnoptimizedBleScan();
            }

            @Override // android.os.UidProto.BluetoothMiscOrBuilder
            public TimerProto getUnoptimizedBleScan() {
                return ((BluetoothMisc) this.instance).getUnoptimizedBleScan();
            }

            public Builder setUnoptimizedBleScan(TimerProto value) {
                copyOnWrite();
                ((BluetoothMisc) this.instance).setUnoptimizedBleScan((BluetoothMisc) value);
                return this;
            }

            public Builder setUnoptimizedBleScan(TimerProto.Builder builderForValue) {
                copyOnWrite();
                ((BluetoothMisc) this.instance).setUnoptimizedBleScan((BluetoothMisc) builderForValue);
                return this;
            }

            public Builder mergeUnoptimizedBleScan(TimerProto value) {
                copyOnWrite();
                ((BluetoothMisc) this.instance).mergeUnoptimizedBleScan(value);
                return this;
            }

            public Builder clearUnoptimizedBleScan() {
                copyOnWrite();
                ((BluetoothMisc) this.instance).clearUnoptimizedBleScan();
                return this;
            }

            @Override // android.os.UidProto.BluetoothMiscOrBuilder
            public boolean hasBackgroundUnoptimizedBleScan() {
                return ((BluetoothMisc) this.instance).hasBackgroundUnoptimizedBleScan();
            }

            @Override // android.os.UidProto.BluetoothMiscOrBuilder
            public TimerProto getBackgroundUnoptimizedBleScan() {
                return ((BluetoothMisc) this.instance).getBackgroundUnoptimizedBleScan();
            }

            public Builder setBackgroundUnoptimizedBleScan(TimerProto value) {
                copyOnWrite();
                ((BluetoothMisc) this.instance).setBackgroundUnoptimizedBleScan((BluetoothMisc) value);
                return this;
            }

            public Builder setBackgroundUnoptimizedBleScan(TimerProto.Builder builderForValue) {
                copyOnWrite();
                ((BluetoothMisc) this.instance).setBackgroundUnoptimizedBleScan((BluetoothMisc) builderForValue);
                return this;
            }

            public Builder mergeBackgroundUnoptimizedBleScan(TimerProto value) {
                copyOnWrite();
                ((BluetoothMisc) this.instance).mergeBackgroundUnoptimizedBleScan(value);
                return this;
            }

            public Builder clearBackgroundUnoptimizedBleScan() {
                copyOnWrite();
                ((BluetoothMisc) this.instance).clearBackgroundUnoptimizedBleScan();
                return this;
            }

            @Override // android.os.UidProto.BluetoothMiscOrBuilder
            public boolean hasBleScanResultCount() {
                return ((BluetoothMisc) this.instance).hasBleScanResultCount();
            }

            @Override // android.os.UidProto.BluetoothMiscOrBuilder
            public int getBleScanResultCount() {
                return ((BluetoothMisc) this.instance).getBleScanResultCount();
            }

            public Builder setBleScanResultCount(int value) {
                copyOnWrite();
                ((BluetoothMisc) this.instance).setBleScanResultCount(value);
                return this;
            }

            public Builder clearBleScanResultCount() {
                copyOnWrite();
                ((BluetoothMisc) this.instance).clearBleScanResultCount();
                return this;
            }

            @Override // android.os.UidProto.BluetoothMiscOrBuilder
            public boolean hasBackgroundBleScanResultCount() {
                return ((BluetoothMisc) this.instance).hasBackgroundBleScanResultCount();
            }

            @Override // android.os.UidProto.BluetoothMiscOrBuilder
            public int getBackgroundBleScanResultCount() {
                return ((BluetoothMisc) this.instance).getBackgroundBleScanResultCount();
            }

            public Builder setBackgroundBleScanResultCount(int value) {
                copyOnWrite();
                ((BluetoothMisc) this.instance).setBackgroundBleScanResultCount(value);
                return this;
            }

            public Builder clearBackgroundBleScanResultCount() {
                copyOnWrite();
                ((BluetoothMisc) this.instance).clearBackgroundBleScanResultCount();
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new BluetoothMisc();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    BluetoothMisc other = (BluetoothMisc) arg1;
                    this.apportionedBleScan_ = (TimerProto) visitor.visitMessage(this.apportionedBleScan_, other.apportionedBleScan_);
                    this.backgroundBleScan_ = (TimerProto) visitor.visitMessage(this.backgroundBleScan_, other.backgroundBleScan_);
                    this.unoptimizedBleScan_ = (TimerProto) visitor.visitMessage(this.unoptimizedBleScan_, other.unoptimizedBleScan_);
                    this.backgroundUnoptimizedBleScan_ = (TimerProto) visitor.visitMessage(this.backgroundUnoptimizedBleScan_, other.backgroundUnoptimizedBleScan_);
                    this.bleScanResultCount_ = visitor.visitInt(hasBleScanResultCount(), this.bleScanResultCount_, other.hasBleScanResultCount(), other.bleScanResultCount_);
                    this.backgroundBleScanResultCount_ = visitor.visitInt(hasBackgroundBleScanResultCount(), this.backgroundBleScanResultCount_, other.hasBackgroundBleScanResultCount(), other.backgroundBleScanResultCount_);
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
                                TimerProto.Builder subBuilder = null;
                                if ((this.bitField0_ & 1) == 1) {
                                    subBuilder = (TimerProto.Builder) this.apportionedBleScan_.toBuilder();
                                }
                                this.apportionedBleScan_ = (TimerProto) input.readMessage(TimerProto.parser(), extensionRegistry);
                                if (subBuilder != null) {
                                    subBuilder.mergeFrom((GeneratedMessageLite) this.apportionedBleScan_);
                                    this.apportionedBleScan_ = (TimerProto) subBuilder.buildPartial();
                                }
                                this.bitField0_ |= 1;
                            } else if (tag == 18) {
                                TimerProto.Builder subBuilder2 = null;
                                if ((this.bitField0_ & 2) == 2) {
                                    subBuilder2 = (TimerProto.Builder) this.backgroundBleScan_.toBuilder();
                                }
                                this.backgroundBleScan_ = (TimerProto) input.readMessage(TimerProto.parser(), extensionRegistry);
                                if (subBuilder2 != null) {
                                    subBuilder2.mergeFrom((GeneratedMessageLite) this.backgroundBleScan_);
                                    this.backgroundBleScan_ = (TimerProto) subBuilder2.buildPartial();
                                }
                                this.bitField0_ |= 2;
                            } else if (tag == 26) {
                                TimerProto.Builder subBuilder3 = null;
                                if ((this.bitField0_ & 4) == 4) {
                                    subBuilder3 = (TimerProto.Builder) this.unoptimizedBleScan_.toBuilder();
                                }
                                this.unoptimizedBleScan_ = (TimerProto) input.readMessage(TimerProto.parser(), extensionRegistry);
                                if (subBuilder3 != null) {
                                    subBuilder3.mergeFrom((GeneratedMessageLite) this.unoptimizedBleScan_);
                                    this.unoptimizedBleScan_ = (TimerProto) subBuilder3.buildPartial();
                                }
                                this.bitField0_ |= 4;
                            } else if (tag == 34) {
                                TimerProto.Builder subBuilder4 = null;
                                if ((this.bitField0_ & 8) == 8) {
                                    subBuilder4 = (TimerProto.Builder) this.backgroundUnoptimizedBleScan_.toBuilder();
                                }
                                this.backgroundUnoptimizedBleScan_ = (TimerProto) input.readMessage(TimerProto.parser(), extensionRegistry);
                                if (subBuilder4 != null) {
                                    subBuilder4.mergeFrom((GeneratedMessageLite) this.backgroundUnoptimizedBleScan_);
                                    this.backgroundUnoptimizedBleScan_ = (TimerProto) subBuilder4.buildPartial();
                                }
                                this.bitField0_ |= 8;
                            } else if (tag == 40) {
                                this.bitField0_ |= 16;
                                this.bleScanResultCount_ = input.readInt32();
                            } else if (tag == 48) {
                                this.bitField0_ |= 32;
                                this.backgroundBleScanResultCount_ = input.readInt32();
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
                        synchronized (BluetoothMisc.class) {
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

        public static BluetoothMisc getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<BluetoothMisc> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class Cpu extends GeneratedMessageLite<Cpu, Builder> implements CpuOrBuilder {
        public static final int BY_FREQUENCY_FIELD_NUMBER = 3;
        public static final int BY_PROCESS_STATE_FIELD_NUMBER = 4;
        private static final Cpu DEFAULT_INSTANCE = new Cpu();
        private static volatile Parser<Cpu> PARSER = null;
        public static final int SYSTEM_DURATION_MS_FIELD_NUMBER = 2;
        public static final int USER_DURATION_MS_FIELD_NUMBER = 1;
        private int bitField0_;
        private Internal.ProtobufList<ByFrequency> byFrequency_ = emptyProtobufList();
        private Internal.ProtobufList<ByProcessState> byProcessState_ = emptyProtobufList();
        private long systemDurationMs_ = 0;
        private long userDurationMs_ = 0;

        public interface ByFrequencyOrBuilder extends MessageLiteOrBuilder {
            int getFrequencyIndex();

            long getScreenOffDurationMs();

            long getTotalDurationMs();

            boolean hasFrequencyIndex();

            boolean hasScreenOffDurationMs();

            boolean hasTotalDurationMs();
        }

        public interface ByProcessStateOrBuilder extends MessageLiteOrBuilder {
            ByFrequency getByFrequency(int i);

            int getByFrequencyCount();

            List<ByFrequency> getByFrequencyList();

            ProcessState getProcessState();

            boolean hasProcessState();
        }

        private Cpu() {
        }

        public enum ProcessState implements Internal.EnumLite {
            TOP(0),
            FOREGROUND_SERVICE(1),
            FOREGROUND(2),
            BACKGROUND(3),
            TOP_SLEEPING(4),
            HEAVY_WEIGHT(5),
            CACHED(6);
            
            public static final int BACKGROUND_VALUE = 3;
            public static final int CACHED_VALUE = 6;
            public static final int FOREGROUND_SERVICE_VALUE = 1;
            public static final int FOREGROUND_VALUE = 2;
            public static final int HEAVY_WEIGHT_VALUE = 5;
            public static final int TOP_SLEEPING_VALUE = 4;
            public static final int TOP_VALUE = 0;
            private static final Internal.EnumLiteMap<ProcessState> internalValueMap = new Internal.EnumLiteMap<ProcessState>() {
                /* class android.os.UidProto.Cpu.ProcessState.AnonymousClass1 */

                @Override // com.google.protobuf.Internal.EnumLiteMap
                public ProcessState findValueByNumber(int number) {
                    return ProcessState.forNumber(number);
                }
            };
            private final int value;

            @Override // com.google.protobuf.Internal.EnumLite
            public final int getNumber() {
                return this.value;
            }

            @Deprecated
            public static ProcessState valueOf(int value2) {
                return forNumber(value2);
            }

            public static ProcessState forNumber(int value2) {
                switch (value2) {
                    case 0:
                        return TOP;
                    case 1:
                        return FOREGROUND_SERVICE;
                    case 2:
                        return FOREGROUND;
                    case 3:
                        return BACKGROUND;
                    case 4:
                        return TOP_SLEEPING;
                    case 5:
                        return HEAVY_WEIGHT;
                    case 6:
                        return CACHED;
                    default:
                        return null;
                }
            }

            public static Internal.EnumLiteMap<ProcessState> internalGetValueMap() {
                return internalValueMap;
            }

            private ProcessState(int value2) {
                this.value = value2;
            }
        }

        public static final class ByFrequency extends GeneratedMessageLite<ByFrequency, Builder> implements ByFrequencyOrBuilder {
            private static final ByFrequency DEFAULT_INSTANCE = new ByFrequency();
            public static final int FREQUENCY_INDEX_FIELD_NUMBER = 1;
            private static volatile Parser<ByFrequency> PARSER = null;
            public static final int SCREEN_OFF_DURATION_MS_FIELD_NUMBER = 3;
            public static final int TOTAL_DURATION_MS_FIELD_NUMBER = 2;
            private int bitField0_;
            private int frequencyIndex_ = 0;
            private long screenOffDurationMs_ = 0;
            private long totalDurationMs_ = 0;

            private ByFrequency() {
            }

            @Override // android.os.UidProto.Cpu.ByFrequencyOrBuilder
            public boolean hasFrequencyIndex() {
                return (this.bitField0_ & 1) == 1;
            }

            @Override // android.os.UidProto.Cpu.ByFrequencyOrBuilder
            public int getFrequencyIndex() {
                return this.frequencyIndex_;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void setFrequencyIndex(int value) {
                this.bitField0_ |= 1;
                this.frequencyIndex_ = value;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void clearFrequencyIndex() {
                this.bitField0_ &= -2;
                this.frequencyIndex_ = 0;
            }

            @Override // android.os.UidProto.Cpu.ByFrequencyOrBuilder
            public boolean hasTotalDurationMs() {
                return (this.bitField0_ & 2) == 2;
            }

            @Override // android.os.UidProto.Cpu.ByFrequencyOrBuilder
            public long getTotalDurationMs() {
                return this.totalDurationMs_;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void setTotalDurationMs(long value) {
                this.bitField0_ |= 2;
                this.totalDurationMs_ = value;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void clearTotalDurationMs() {
                this.bitField0_ &= -3;
                this.totalDurationMs_ = 0;
            }

            @Override // android.os.UidProto.Cpu.ByFrequencyOrBuilder
            public boolean hasScreenOffDurationMs() {
                return (this.bitField0_ & 4) == 4;
            }

            @Override // android.os.UidProto.Cpu.ByFrequencyOrBuilder
            public long getScreenOffDurationMs() {
                return this.screenOffDurationMs_;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void setScreenOffDurationMs(long value) {
                this.bitField0_ |= 4;
                this.screenOffDurationMs_ = value;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void clearScreenOffDurationMs() {
                this.bitField0_ &= -5;
                this.screenOffDurationMs_ = 0;
            }

            @Override // com.google.protobuf.MessageLite
            public void writeTo(CodedOutputStream output) throws IOException {
                if ((this.bitField0_ & 1) == 1) {
                    output.writeInt32(1, this.frequencyIndex_);
                }
                if ((this.bitField0_ & 2) == 2) {
                    output.writeInt64(2, this.totalDurationMs_);
                }
                if ((this.bitField0_ & 4) == 4) {
                    output.writeInt64(3, this.screenOffDurationMs_);
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
                    size2 = 0 + CodedOutputStream.computeInt32Size(1, this.frequencyIndex_);
                }
                if ((this.bitField0_ & 2) == 2) {
                    size2 += CodedOutputStream.computeInt64Size(2, this.totalDurationMs_);
                }
                if ((this.bitField0_ & 4) == 4) {
                    size2 += CodedOutputStream.computeInt64Size(3, this.screenOffDurationMs_);
                }
                int size3 = size2 + this.unknownFields.getSerializedSize();
                this.memoizedSerializedSize = size3;
                return size3;
            }

            public static ByFrequency parseFrom(ByteString data) throws InvalidProtocolBufferException {
                return (ByFrequency) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
            }

            public static ByFrequency parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return (ByFrequency) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
            }

            public static ByFrequency parseFrom(byte[] data) throws InvalidProtocolBufferException {
                return (ByFrequency) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
            }

            public static ByFrequency parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return (ByFrequency) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
            }

            public static ByFrequency parseFrom(InputStream input) throws IOException {
                return (ByFrequency) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
            }

            public static ByFrequency parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (ByFrequency) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static ByFrequency parseDelimitedFrom(InputStream input) throws IOException {
                return (ByFrequency) parseDelimitedFrom(DEFAULT_INSTANCE, input);
            }

            public static ByFrequency parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (ByFrequency) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static ByFrequency parseFrom(CodedInputStream input) throws IOException {
                return (ByFrequency) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
            }

            public static ByFrequency parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (ByFrequency) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static Builder newBuilder() {
                return (Builder) DEFAULT_INSTANCE.toBuilder();
            }

            public static Builder newBuilder(ByFrequency prototype) {
                return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
            }

            public static final class Builder extends GeneratedMessageLite.Builder<ByFrequency, Builder> implements ByFrequencyOrBuilder {
                private Builder() {
                    super(ByFrequency.DEFAULT_INSTANCE);
                }

                @Override // android.os.UidProto.Cpu.ByFrequencyOrBuilder
                public boolean hasFrequencyIndex() {
                    return ((ByFrequency) this.instance).hasFrequencyIndex();
                }

                @Override // android.os.UidProto.Cpu.ByFrequencyOrBuilder
                public int getFrequencyIndex() {
                    return ((ByFrequency) this.instance).getFrequencyIndex();
                }

                public Builder setFrequencyIndex(int value) {
                    copyOnWrite();
                    ((ByFrequency) this.instance).setFrequencyIndex(value);
                    return this;
                }

                public Builder clearFrequencyIndex() {
                    copyOnWrite();
                    ((ByFrequency) this.instance).clearFrequencyIndex();
                    return this;
                }

                @Override // android.os.UidProto.Cpu.ByFrequencyOrBuilder
                public boolean hasTotalDurationMs() {
                    return ((ByFrequency) this.instance).hasTotalDurationMs();
                }

                @Override // android.os.UidProto.Cpu.ByFrequencyOrBuilder
                public long getTotalDurationMs() {
                    return ((ByFrequency) this.instance).getTotalDurationMs();
                }

                public Builder setTotalDurationMs(long value) {
                    copyOnWrite();
                    ((ByFrequency) this.instance).setTotalDurationMs(value);
                    return this;
                }

                public Builder clearTotalDurationMs() {
                    copyOnWrite();
                    ((ByFrequency) this.instance).clearTotalDurationMs();
                    return this;
                }

                @Override // android.os.UidProto.Cpu.ByFrequencyOrBuilder
                public boolean hasScreenOffDurationMs() {
                    return ((ByFrequency) this.instance).hasScreenOffDurationMs();
                }

                @Override // android.os.UidProto.Cpu.ByFrequencyOrBuilder
                public long getScreenOffDurationMs() {
                    return ((ByFrequency) this.instance).getScreenOffDurationMs();
                }

                public Builder setScreenOffDurationMs(long value) {
                    copyOnWrite();
                    ((ByFrequency) this.instance).setScreenOffDurationMs(value);
                    return this;
                }

                public Builder clearScreenOffDurationMs() {
                    copyOnWrite();
                    ((ByFrequency) this.instance).clearScreenOffDurationMs();
                    return this;
                }
            }

            /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
            /* access modifiers changed from: protected */
            @Override // com.google.protobuf.GeneratedMessageLite
            public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
                switch (method) {
                    case NEW_MUTABLE_INSTANCE:
                        return new ByFrequency();
                    case IS_INITIALIZED:
                        return DEFAULT_INSTANCE;
                    case MAKE_IMMUTABLE:
                        return null;
                    case NEW_BUILDER:
                        return new Builder();
                    case VISIT:
                        GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                        ByFrequency other = (ByFrequency) arg1;
                        this.frequencyIndex_ = visitor.visitInt(hasFrequencyIndex(), this.frequencyIndex_, other.hasFrequencyIndex(), other.frequencyIndex_);
                        this.totalDurationMs_ = visitor.visitLong(hasTotalDurationMs(), this.totalDurationMs_, other.hasTotalDurationMs(), other.totalDurationMs_);
                        this.screenOffDurationMs_ = visitor.visitLong(hasScreenOffDurationMs(), this.screenOffDurationMs_, other.hasScreenOffDurationMs(), other.screenOffDurationMs_);
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
                                    this.frequencyIndex_ = input.readInt32();
                                } else if (tag == 16) {
                                    this.bitField0_ |= 2;
                                    this.totalDurationMs_ = input.readInt64();
                                } else if (tag == 24) {
                                    this.bitField0_ |= 4;
                                    this.screenOffDurationMs_ = input.readInt64();
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
                            synchronized (ByFrequency.class) {
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

            public static ByFrequency getDefaultInstance() {
                return DEFAULT_INSTANCE;
            }

            public static Parser<ByFrequency> parser() {
                return DEFAULT_INSTANCE.getParserForType();
            }
        }

        public static final class ByProcessState extends GeneratedMessageLite<ByProcessState, Builder> implements ByProcessStateOrBuilder {
            public static final int BY_FREQUENCY_FIELD_NUMBER = 2;
            private static final ByProcessState DEFAULT_INSTANCE = new ByProcessState();
            private static volatile Parser<ByProcessState> PARSER = null;
            public static final int PROCESS_STATE_FIELD_NUMBER = 1;
            private int bitField0_;
            private Internal.ProtobufList<ByFrequency> byFrequency_ = emptyProtobufList();
            private int processState_ = 0;

            private ByProcessState() {
            }

            @Override // android.os.UidProto.Cpu.ByProcessStateOrBuilder
            public boolean hasProcessState() {
                return (this.bitField0_ & 1) == 1;
            }

            @Override // android.os.UidProto.Cpu.ByProcessStateOrBuilder
            public ProcessState getProcessState() {
                ProcessState result = ProcessState.forNumber(this.processState_);
                return result == null ? ProcessState.TOP : result;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void setProcessState(ProcessState value) {
                if (value != null) {
                    this.bitField0_ |= 1;
                    this.processState_ = value.getNumber();
                    return;
                }
                throw new NullPointerException();
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void clearProcessState() {
                this.bitField0_ &= -2;
                this.processState_ = 0;
            }

            @Override // android.os.UidProto.Cpu.ByProcessStateOrBuilder
            public List<ByFrequency> getByFrequencyList() {
                return this.byFrequency_;
            }

            public List<? extends ByFrequencyOrBuilder> getByFrequencyOrBuilderList() {
                return this.byFrequency_;
            }

            @Override // android.os.UidProto.Cpu.ByProcessStateOrBuilder
            public int getByFrequencyCount() {
                return this.byFrequency_.size();
            }

            @Override // android.os.UidProto.Cpu.ByProcessStateOrBuilder
            public ByFrequency getByFrequency(int index) {
                return this.byFrequency_.get(index);
            }

            public ByFrequencyOrBuilder getByFrequencyOrBuilder(int index) {
                return this.byFrequency_.get(index);
            }

            private void ensureByFrequencyIsMutable() {
                if (!this.byFrequency_.isModifiable()) {
                    this.byFrequency_ = GeneratedMessageLite.mutableCopy(this.byFrequency_);
                }
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void setByFrequency(int index, ByFrequency value) {
                if (value != null) {
                    ensureByFrequencyIsMutable();
                    this.byFrequency_.set(index, value);
                    return;
                }
                throw new NullPointerException();
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void setByFrequency(int index, ByFrequency.Builder builderForValue) {
                ensureByFrequencyIsMutable();
                this.byFrequency_.set(index, (ByFrequency) builderForValue.build());
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void addByFrequency(ByFrequency value) {
                if (value != null) {
                    ensureByFrequencyIsMutable();
                    this.byFrequency_.add(value);
                    return;
                }
                throw new NullPointerException();
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void addByFrequency(int index, ByFrequency value) {
                if (value != null) {
                    ensureByFrequencyIsMutable();
                    this.byFrequency_.add(index, value);
                    return;
                }
                throw new NullPointerException();
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void addByFrequency(ByFrequency.Builder builderForValue) {
                ensureByFrequencyIsMutable();
                this.byFrequency_.add((ByFrequency) builderForValue.build());
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void addByFrequency(int index, ByFrequency.Builder builderForValue) {
                ensureByFrequencyIsMutable();
                this.byFrequency_.add(index, (ByFrequency) builderForValue.build());
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void addAllByFrequency(Iterable<? extends ByFrequency> values) {
                ensureByFrequencyIsMutable();
                AbstractMessageLite.addAll(values, this.byFrequency_);
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void clearByFrequency() {
                this.byFrequency_ = emptyProtobufList();
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void removeByFrequency(int index) {
                ensureByFrequencyIsMutable();
                this.byFrequency_.remove(index);
            }

            @Override // com.google.protobuf.MessageLite
            public void writeTo(CodedOutputStream output) throws IOException {
                if ((this.bitField0_ & 1) == 1) {
                    output.writeEnum(1, this.processState_);
                }
                for (int i = 0; i < this.byFrequency_.size(); i++) {
                    output.writeMessage(2, this.byFrequency_.get(i));
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
                    size2 = 0 + CodedOutputStream.computeEnumSize(1, this.processState_);
                }
                for (int i = 0; i < this.byFrequency_.size(); i++) {
                    size2 += CodedOutputStream.computeMessageSize(2, this.byFrequency_.get(i));
                }
                int size3 = size2 + this.unknownFields.getSerializedSize();
                this.memoizedSerializedSize = size3;
                return size3;
            }

            public static ByProcessState parseFrom(ByteString data) throws InvalidProtocolBufferException {
                return (ByProcessState) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
            }

            public static ByProcessState parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return (ByProcessState) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
            }

            public static ByProcessState parseFrom(byte[] data) throws InvalidProtocolBufferException {
                return (ByProcessState) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
            }

            public static ByProcessState parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return (ByProcessState) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
            }

            public static ByProcessState parseFrom(InputStream input) throws IOException {
                return (ByProcessState) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
            }

            public static ByProcessState parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (ByProcessState) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static ByProcessState parseDelimitedFrom(InputStream input) throws IOException {
                return (ByProcessState) parseDelimitedFrom(DEFAULT_INSTANCE, input);
            }

            public static ByProcessState parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (ByProcessState) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static ByProcessState parseFrom(CodedInputStream input) throws IOException {
                return (ByProcessState) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
            }

            public static ByProcessState parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (ByProcessState) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static Builder newBuilder() {
                return (Builder) DEFAULT_INSTANCE.toBuilder();
            }

            public static Builder newBuilder(ByProcessState prototype) {
                return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
            }

            public static final class Builder extends GeneratedMessageLite.Builder<ByProcessState, Builder> implements ByProcessStateOrBuilder {
                private Builder() {
                    super(ByProcessState.DEFAULT_INSTANCE);
                }

                @Override // android.os.UidProto.Cpu.ByProcessStateOrBuilder
                public boolean hasProcessState() {
                    return ((ByProcessState) this.instance).hasProcessState();
                }

                @Override // android.os.UidProto.Cpu.ByProcessStateOrBuilder
                public ProcessState getProcessState() {
                    return ((ByProcessState) this.instance).getProcessState();
                }

                public Builder setProcessState(ProcessState value) {
                    copyOnWrite();
                    ((ByProcessState) this.instance).setProcessState(value);
                    return this;
                }

                public Builder clearProcessState() {
                    copyOnWrite();
                    ((ByProcessState) this.instance).clearProcessState();
                    return this;
                }

                @Override // android.os.UidProto.Cpu.ByProcessStateOrBuilder
                public List<ByFrequency> getByFrequencyList() {
                    return Collections.unmodifiableList(((ByProcessState) this.instance).getByFrequencyList());
                }

                @Override // android.os.UidProto.Cpu.ByProcessStateOrBuilder
                public int getByFrequencyCount() {
                    return ((ByProcessState) this.instance).getByFrequencyCount();
                }

                @Override // android.os.UidProto.Cpu.ByProcessStateOrBuilder
                public ByFrequency getByFrequency(int index) {
                    return ((ByProcessState) this.instance).getByFrequency(index);
                }

                public Builder setByFrequency(int index, ByFrequency value) {
                    copyOnWrite();
                    ((ByProcessState) this.instance).setByFrequency((ByProcessState) index, (int) value);
                    return this;
                }

                public Builder setByFrequency(int index, ByFrequency.Builder builderForValue) {
                    copyOnWrite();
                    ((ByProcessState) this.instance).setByFrequency((ByProcessState) index, (int) builderForValue);
                    return this;
                }

                public Builder addByFrequency(ByFrequency value) {
                    copyOnWrite();
                    ((ByProcessState) this.instance).addByFrequency((ByProcessState) value);
                    return this;
                }

                public Builder addByFrequency(int index, ByFrequency value) {
                    copyOnWrite();
                    ((ByProcessState) this.instance).addByFrequency((ByProcessState) index, (int) value);
                    return this;
                }

                public Builder addByFrequency(ByFrequency.Builder builderForValue) {
                    copyOnWrite();
                    ((ByProcessState) this.instance).addByFrequency((ByProcessState) builderForValue);
                    return this;
                }

                public Builder addByFrequency(int index, ByFrequency.Builder builderForValue) {
                    copyOnWrite();
                    ((ByProcessState) this.instance).addByFrequency((ByProcessState) index, (int) builderForValue);
                    return this;
                }

                public Builder addAllByFrequency(Iterable<? extends ByFrequency> values) {
                    copyOnWrite();
                    ((ByProcessState) this.instance).addAllByFrequency(values);
                    return this;
                }

                public Builder clearByFrequency() {
                    copyOnWrite();
                    ((ByProcessState) this.instance).clearByFrequency();
                    return this;
                }

                public Builder removeByFrequency(int index) {
                    copyOnWrite();
                    ((ByProcessState) this.instance).removeByFrequency(index);
                    return this;
                }
            }

            /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
            /* access modifiers changed from: protected */
            @Override // com.google.protobuf.GeneratedMessageLite
            public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
                switch (method) {
                    case NEW_MUTABLE_INSTANCE:
                        return new ByProcessState();
                    case IS_INITIALIZED:
                        return DEFAULT_INSTANCE;
                    case MAKE_IMMUTABLE:
                        this.byFrequency_.makeImmutable();
                        return null;
                    case NEW_BUILDER:
                        return new Builder();
                    case VISIT:
                        GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                        ByProcessState other = (ByProcessState) arg1;
                        this.processState_ = visitor.visitInt(hasProcessState(), this.processState_, other.hasProcessState(), other.processState_);
                        this.byFrequency_ = visitor.visitList(this.byFrequency_, other.byFrequency_);
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
                                    if (ProcessState.forNumber(rawValue) == null) {
                                        super.mergeVarintField(1, rawValue);
                                    } else {
                                        this.bitField0_ = 1 | this.bitField0_;
                                        this.processState_ = rawValue;
                                    }
                                } else if (tag == 18) {
                                    if (!this.byFrequency_.isModifiable()) {
                                        this.byFrequency_ = GeneratedMessageLite.mutableCopy(this.byFrequency_);
                                    }
                                    this.byFrequency_.add((ByFrequency) input.readMessage(ByFrequency.parser(), extensionRegistry));
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
                            synchronized (ByProcessState.class) {
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

            public static ByProcessState getDefaultInstance() {
                return DEFAULT_INSTANCE;
            }

            public static Parser<ByProcessState> parser() {
                return DEFAULT_INSTANCE.getParserForType();
            }
        }

        @Override // android.os.UidProto.CpuOrBuilder
        public boolean hasUserDurationMs() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // android.os.UidProto.CpuOrBuilder
        public long getUserDurationMs() {
            return this.userDurationMs_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setUserDurationMs(long value) {
            this.bitField0_ |= 1;
            this.userDurationMs_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearUserDurationMs() {
            this.bitField0_ &= -2;
            this.userDurationMs_ = 0;
        }

        @Override // android.os.UidProto.CpuOrBuilder
        public boolean hasSystemDurationMs() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // android.os.UidProto.CpuOrBuilder
        public long getSystemDurationMs() {
            return this.systemDurationMs_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setSystemDurationMs(long value) {
            this.bitField0_ |= 2;
            this.systemDurationMs_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearSystemDurationMs() {
            this.bitField0_ &= -3;
            this.systemDurationMs_ = 0;
        }

        @Override // android.os.UidProto.CpuOrBuilder
        public List<ByFrequency> getByFrequencyList() {
            return this.byFrequency_;
        }

        public List<? extends ByFrequencyOrBuilder> getByFrequencyOrBuilderList() {
            return this.byFrequency_;
        }

        @Override // android.os.UidProto.CpuOrBuilder
        public int getByFrequencyCount() {
            return this.byFrequency_.size();
        }

        @Override // android.os.UidProto.CpuOrBuilder
        public ByFrequency getByFrequency(int index) {
            return this.byFrequency_.get(index);
        }

        public ByFrequencyOrBuilder getByFrequencyOrBuilder(int index) {
            return this.byFrequency_.get(index);
        }

        private void ensureByFrequencyIsMutable() {
            if (!this.byFrequency_.isModifiable()) {
                this.byFrequency_ = GeneratedMessageLite.mutableCopy(this.byFrequency_);
            }
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setByFrequency(int index, ByFrequency value) {
            if (value != null) {
                ensureByFrequencyIsMutable();
                this.byFrequency_.set(index, value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setByFrequency(int index, ByFrequency.Builder builderForValue) {
            ensureByFrequencyIsMutable();
            this.byFrequency_.set(index, (ByFrequency) builderForValue.build());
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addByFrequency(ByFrequency value) {
            if (value != null) {
                ensureByFrequencyIsMutable();
                this.byFrequency_.add(value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addByFrequency(int index, ByFrequency value) {
            if (value != null) {
                ensureByFrequencyIsMutable();
                this.byFrequency_.add(index, value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addByFrequency(ByFrequency.Builder builderForValue) {
            ensureByFrequencyIsMutable();
            this.byFrequency_.add((ByFrequency) builderForValue.build());
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addByFrequency(int index, ByFrequency.Builder builderForValue) {
            ensureByFrequencyIsMutable();
            this.byFrequency_.add(index, (ByFrequency) builderForValue.build());
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addAllByFrequency(Iterable<? extends ByFrequency> values) {
            ensureByFrequencyIsMutable();
            AbstractMessageLite.addAll(values, this.byFrequency_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearByFrequency() {
            this.byFrequency_ = emptyProtobufList();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void removeByFrequency(int index) {
            ensureByFrequencyIsMutable();
            this.byFrequency_.remove(index);
        }

        @Override // android.os.UidProto.CpuOrBuilder
        public List<ByProcessState> getByProcessStateList() {
            return this.byProcessState_;
        }

        public List<? extends ByProcessStateOrBuilder> getByProcessStateOrBuilderList() {
            return this.byProcessState_;
        }

        @Override // android.os.UidProto.CpuOrBuilder
        public int getByProcessStateCount() {
            return this.byProcessState_.size();
        }

        @Override // android.os.UidProto.CpuOrBuilder
        public ByProcessState getByProcessState(int index) {
            return this.byProcessState_.get(index);
        }

        public ByProcessStateOrBuilder getByProcessStateOrBuilder(int index) {
            return this.byProcessState_.get(index);
        }

        private void ensureByProcessStateIsMutable() {
            if (!this.byProcessState_.isModifiable()) {
                this.byProcessState_ = GeneratedMessageLite.mutableCopy(this.byProcessState_);
            }
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setByProcessState(int index, ByProcessState value) {
            if (value != null) {
                ensureByProcessStateIsMutable();
                this.byProcessState_.set(index, value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setByProcessState(int index, ByProcessState.Builder builderForValue) {
            ensureByProcessStateIsMutable();
            this.byProcessState_.set(index, (ByProcessState) builderForValue.build());
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addByProcessState(ByProcessState value) {
            if (value != null) {
                ensureByProcessStateIsMutable();
                this.byProcessState_.add(value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addByProcessState(int index, ByProcessState value) {
            if (value != null) {
                ensureByProcessStateIsMutable();
                this.byProcessState_.add(index, value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addByProcessState(ByProcessState.Builder builderForValue) {
            ensureByProcessStateIsMutable();
            this.byProcessState_.add((ByProcessState) builderForValue.build());
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addByProcessState(int index, ByProcessState.Builder builderForValue) {
            ensureByProcessStateIsMutable();
            this.byProcessState_.add(index, (ByProcessState) builderForValue.build());
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addAllByProcessState(Iterable<? extends ByProcessState> values) {
            ensureByProcessStateIsMutable();
            AbstractMessageLite.addAll(values, this.byProcessState_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearByProcessState() {
            this.byProcessState_ = emptyProtobufList();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void removeByProcessState(int index) {
            ensureByProcessStateIsMutable();
            this.byProcessState_.remove(index);
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                output.writeInt64(1, this.userDurationMs_);
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeInt64(2, this.systemDurationMs_);
            }
            for (int i = 0; i < this.byFrequency_.size(); i++) {
                output.writeMessage(3, this.byFrequency_.get(i));
            }
            for (int i2 = 0; i2 < this.byProcessState_.size(); i2++) {
                output.writeMessage(4, this.byProcessState_.get(i2));
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
                size2 = 0 + CodedOutputStream.computeInt64Size(1, this.userDurationMs_);
            }
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeInt64Size(2, this.systemDurationMs_);
            }
            for (int i = 0; i < this.byFrequency_.size(); i++) {
                size2 += CodedOutputStream.computeMessageSize(3, this.byFrequency_.get(i));
            }
            for (int i2 = 0; i2 < this.byProcessState_.size(); i2++) {
                size2 += CodedOutputStream.computeMessageSize(4, this.byProcessState_.get(i2));
            }
            int size3 = size2 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static Cpu parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (Cpu) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static Cpu parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Cpu) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static Cpu parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (Cpu) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static Cpu parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Cpu) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static Cpu parseFrom(InputStream input) throws IOException {
            return (Cpu) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static Cpu parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Cpu) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Cpu parseDelimitedFrom(InputStream input) throws IOException {
            return (Cpu) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static Cpu parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Cpu) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Cpu parseFrom(CodedInputStream input) throws IOException {
            return (Cpu) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static Cpu parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Cpu) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(Cpu prototype) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<Cpu, Builder> implements CpuOrBuilder {
            private Builder() {
                super(Cpu.DEFAULT_INSTANCE);
            }

            @Override // android.os.UidProto.CpuOrBuilder
            public boolean hasUserDurationMs() {
                return ((Cpu) this.instance).hasUserDurationMs();
            }

            @Override // android.os.UidProto.CpuOrBuilder
            public long getUserDurationMs() {
                return ((Cpu) this.instance).getUserDurationMs();
            }

            public Builder setUserDurationMs(long value) {
                copyOnWrite();
                ((Cpu) this.instance).setUserDurationMs(value);
                return this;
            }

            public Builder clearUserDurationMs() {
                copyOnWrite();
                ((Cpu) this.instance).clearUserDurationMs();
                return this;
            }

            @Override // android.os.UidProto.CpuOrBuilder
            public boolean hasSystemDurationMs() {
                return ((Cpu) this.instance).hasSystemDurationMs();
            }

            @Override // android.os.UidProto.CpuOrBuilder
            public long getSystemDurationMs() {
                return ((Cpu) this.instance).getSystemDurationMs();
            }

            public Builder setSystemDurationMs(long value) {
                copyOnWrite();
                ((Cpu) this.instance).setSystemDurationMs(value);
                return this;
            }

            public Builder clearSystemDurationMs() {
                copyOnWrite();
                ((Cpu) this.instance).clearSystemDurationMs();
                return this;
            }

            @Override // android.os.UidProto.CpuOrBuilder
            public List<ByFrequency> getByFrequencyList() {
                return Collections.unmodifiableList(((Cpu) this.instance).getByFrequencyList());
            }

            @Override // android.os.UidProto.CpuOrBuilder
            public int getByFrequencyCount() {
                return ((Cpu) this.instance).getByFrequencyCount();
            }

            @Override // android.os.UidProto.CpuOrBuilder
            public ByFrequency getByFrequency(int index) {
                return ((Cpu) this.instance).getByFrequency(index);
            }

            public Builder setByFrequency(int index, ByFrequency value) {
                copyOnWrite();
                ((Cpu) this.instance).setByFrequency((Cpu) index, (int) value);
                return this;
            }

            public Builder setByFrequency(int index, ByFrequency.Builder builderForValue) {
                copyOnWrite();
                ((Cpu) this.instance).setByFrequency((Cpu) index, (int) builderForValue);
                return this;
            }

            public Builder addByFrequency(ByFrequency value) {
                copyOnWrite();
                ((Cpu) this.instance).addByFrequency((Cpu) value);
                return this;
            }

            public Builder addByFrequency(int index, ByFrequency value) {
                copyOnWrite();
                ((Cpu) this.instance).addByFrequency((Cpu) index, (int) value);
                return this;
            }

            public Builder addByFrequency(ByFrequency.Builder builderForValue) {
                copyOnWrite();
                ((Cpu) this.instance).addByFrequency((Cpu) builderForValue);
                return this;
            }

            public Builder addByFrequency(int index, ByFrequency.Builder builderForValue) {
                copyOnWrite();
                ((Cpu) this.instance).addByFrequency((Cpu) index, (int) builderForValue);
                return this;
            }

            public Builder addAllByFrequency(Iterable<? extends ByFrequency> values) {
                copyOnWrite();
                ((Cpu) this.instance).addAllByFrequency(values);
                return this;
            }

            public Builder clearByFrequency() {
                copyOnWrite();
                ((Cpu) this.instance).clearByFrequency();
                return this;
            }

            public Builder removeByFrequency(int index) {
                copyOnWrite();
                ((Cpu) this.instance).removeByFrequency(index);
                return this;
            }

            @Override // android.os.UidProto.CpuOrBuilder
            public List<ByProcessState> getByProcessStateList() {
                return Collections.unmodifiableList(((Cpu) this.instance).getByProcessStateList());
            }

            @Override // android.os.UidProto.CpuOrBuilder
            public int getByProcessStateCount() {
                return ((Cpu) this.instance).getByProcessStateCount();
            }

            @Override // android.os.UidProto.CpuOrBuilder
            public ByProcessState getByProcessState(int index) {
                return ((Cpu) this.instance).getByProcessState(index);
            }

            public Builder setByProcessState(int index, ByProcessState value) {
                copyOnWrite();
                ((Cpu) this.instance).setByProcessState((Cpu) index, (int) value);
                return this;
            }

            public Builder setByProcessState(int index, ByProcessState.Builder builderForValue) {
                copyOnWrite();
                ((Cpu) this.instance).setByProcessState((Cpu) index, (int) builderForValue);
                return this;
            }

            public Builder addByProcessState(ByProcessState value) {
                copyOnWrite();
                ((Cpu) this.instance).addByProcessState((Cpu) value);
                return this;
            }

            public Builder addByProcessState(int index, ByProcessState value) {
                copyOnWrite();
                ((Cpu) this.instance).addByProcessState((Cpu) index, (int) value);
                return this;
            }

            public Builder addByProcessState(ByProcessState.Builder builderForValue) {
                copyOnWrite();
                ((Cpu) this.instance).addByProcessState((Cpu) builderForValue);
                return this;
            }

            public Builder addByProcessState(int index, ByProcessState.Builder builderForValue) {
                copyOnWrite();
                ((Cpu) this.instance).addByProcessState((Cpu) index, (int) builderForValue);
                return this;
            }

            public Builder addAllByProcessState(Iterable<? extends ByProcessState> values) {
                copyOnWrite();
                ((Cpu) this.instance).addAllByProcessState(values);
                return this;
            }

            public Builder clearByProcessState() {
                copyOnWrite();
                ((Cpu) this.instance).clearByProcessState();
                return this;
            }

            public Builder removeByProcessState(int index) {
                copyOnWrite();
                ((Cpu) this.instance).removeByProcessState(index);
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new Cpu();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    this.byFrequency_.makeImmutable();
                    this.byProcessState_.makeImmutable();
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    Cpu other = (Cpu) arg1;
                    this.userDurationMs_ = visitor.visitLong(hasUserDurationMs(), this.userDurationMs_, other.hasUserDurationMs(), other.userDurationMs_);
                    this.systemDurationMs_ = visitor.visitLong(hasSystemDurationMs(), this.systemDurationMs_, other.hasSystemDurationMs(), other.systemDurationMs_);
                    this.byFrequency_ = visitor.visitList(this.byFrequency_, other.byFrequency_);
                    this.byProcessState_ = visitor.visitList(this.byProcessState_, other.byProcessState_);
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
                                this.userDurationMs_ = input.readInt64();
                            } else if (tag == 16) {
                                this.bitField0_ |= 2;
                                this.systemDurationMs_ = input.readInt64();
                            } else if (tag == 26) {
                                if (!this.byFrequency_.isModifiable()) {
                                    this.byFrequency_ = GeneratedMessageLite.mutableCopy(this.byFrequency_);
                                }
                                this.byFrequency_.add((ByFrequency) input.readMessage(ByFrequency.parser(), extensionRegistry));
                            } else if (tag == 34) {
                                if (!this.byProcessState_.isModifiable()) {
                                    this.byProcessState_ = GeneratedMessageLite.mutableCopy(this.byProcessState_);
                                }
                                this.byProcessState_.add((ByProcessState) input.readMessage(ByProcessState.parser(), extensionRegistry));
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
                        synchronized (Cpu.class) {
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

        public static Cpu getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<Cpu> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class Job extends GeneratedMessageLite<Job, Builder> implements JobOrBuilder {
        public static final int BACKGROUND_FIELD_NUMBER = 3;
        private static final Job DEFAULT_INSTANCE = new Job();
        public static final int NAME_FIELD_NUMBER = 1;
        private static volatile Parser<Job> PARSER = null;
        public static final int TOTAL_FIELD_NUMBER = 2;
        private TimerProto background_;
        private int bitField0_;
        private String name_ = "";
        private TimerProto total_;

        private Job() {
        }

        @Override // android.os.UidProto.JobOrBuilder
        public boolean hasName() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // android.os.UidProto.JobOrBuilder
        public String getName() {
            return this.name_;
        }

        @Override // android.os.UidProto.JobOrBuilder
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

        @Override // android.os.UidProto.JobOrBuilder
        public boolean hasTotal() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // android.os.UidProto.JobOrBuilder
        public TimerProto getTotal() {
            TimerProto timerProto = this.total_;
            return timerProto == null ? TimerProto.getDefaultInstance() : timerProto;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setTotal(TimerProto value) {
            if (value != null) {
                this.total_ = value;
                this.bitField0_ |= 2;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setTotal(TimerProto.Builder builderForValue) {
            this.total_ = (TimerProto) builderForValue.build();
            this.bitField0_ |= 2;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void mergeTotal(TimerProto value) {
            TimerProto timerProto = this.total_;
            if (timerProto == null || timerProto == TimerProto.getDefaultInstance()) {
                this.total_ = value;
            } else {
                this.total_ = (TimerProto) ((TimerProto.Builder) TimerProto.newBuilder(this.total_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
            }
            this.bitField0_ |= 2;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearTotal() {
            this.total_ = null;
            this.bitField0_ &= -3;
        }

        @Override // android.os.UidProto.JobOrBuilder
        public boolean hasBackground() {
            return (this.bitField0_ & 4) == 4;
        }

        @Override // android.os.UidProto.JobOrBuilder
        public TimerProto getBackground() {
            TimerProto timerProto = this.background_;
            return timerProto == null ? TimerProto.getDefaultInstance() : timerProto;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setBackground(TimerProto value) {
            if (value != null) {
                this.background_ = value;
                this.bitField0_ |= 4;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setBackground(TimerProto.Builder builderForValue) {
            this.background_ = (TimerProto) builderForValue.build();
            this.bitField0_ |= 4;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void mergeBackground(TimerProto value) {
            TimerProto timerProto = this.background_;
            if (timerProto == null || timerProto == TimerProto.getDefaultInstance()) {
                this.background_ = value;
            } else {
                this.background_ = (TimerProto) ((TimerProto.Builder) TimerProto.newBuilder(this.background_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
            }
            this.bitField0_ |= 4;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearBackground() {
            this.background_ = null;
            this.bitField0_ &= -5;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                output.writeString(1, getName());
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeMessage(2, getTotal());
            }
            if ((this.bitField0_ & 4) == 4) {
                output.writeMessage(3, getBackground());
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
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeMessageSize(2, getTotal());
            }
            if ((this.bitField0_ & 4) == 4) {
                size2 += CodedOutputStream.computeMessageSize(3, getBackground());
            }
            int size3 = size2 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static Job parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (Job) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static Job parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Job) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static Job parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (Job) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static Job parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Job) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static Job parseFrom(InputStream input) throws IOException {
            return (Job) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static Job parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Job) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Job parseDelimitedFrom(InputStream input) throws IOException {
            return (Job) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static Job parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Job) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Job parseFrom(CodedInputStream input) throws IOException {
            return (Job) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static Job parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Job) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(Job prototype) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<Job, Builder> implements JobOrBuilder {
            private Builder() {
                super(Job.DEFAULT_INSTANCE);
            }

            @Override // android.os.UidProto.JobOrBuilder
            public boolean hasName() {
                return ((Job) this.instance).hasName();
            }

            @Override // android.os.UidProto.JobOrBuilder
            public String getName() {
                return ((Job) this.instance).getName();
            }

            @Override // android.os.UidProto.JobOrBuilder
            public ByteString getNameBytes() {
                return ((Job) this.instance).getNameBytes();
            }

            public Builder setName(String value) {
                copyOnWrite();
                ((Job) this.instance).setName(value);
                return this;
            }

            public Builder clearName() {
                copyOnWrite();
                ((Job) this.instance).clearName();
                return this;
            }

            public Builder setNameBytes(ByteString value) {
                copyOnWrite();
                ((Job) this.instance).setNameBytes(value);
                return this;
            }

            @Override // android.os.UidProto.JobOrBuilder
            public boolean hasTotal() {
                return ((Job) this.instance).hasTotal();
            }

            @Override // android.os.UidProto.JobOrBuilder
            public TimerProto getTotal() {
                return ((Job) this.instance).getTotal();
            }

            public Builder setTotal(TimerProto value) {
                copyOnWrite();
                ((Job) this.instance).setTotal((Job) value);
                return this;
            }

            public Builder setTotal(TimerProto.Builder builderForValue) {
                copyOnWrite();
                ((Job) this.instance).setTotal((Job) builderForValue);
                return this;
            }

            public Builder mergeTotal(TimerProto value) {
                copyOnWrite();
                ((Job) this.instance).mergeTotal(value);
                return this;
            }

            public Builder clearTotal() {
                copyOnWrite();
                ((Job) this.instance).clearTotal();
                return this;
            }

            @Override // android.os.UidProto.JobOrBuilder
            public boolean hasBackground() {
                return ((Job) this.instance).hasBackground();
            }

            @Override // android.os.UidProto.JobOrBuilder
            public TimerProto getBackground() {
                return ((Job) this.instance).getBackground();
            }

            public Builder setBackground(TimerProto value) {
                copyOnWrite();
                ((Job) this.instance).setBackground((Job) value);
                return this;
            }

            public Builder setBackground(TimerProto.Builder builderForValue) {
                copyOnWrite();
                ((Job) this.instance).setBackground((Job) builderForValue);
                return this;
            }

            public Builder mergeBackground(TimerProto value) {
                copyOnWrite();
                ((Job) this.instance).mergeBackground(value);
                return this;
            }

            public Builder clearBackground() {
                copyOnWrite();
                ((Job) this.instance).clearBackground();
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new Job();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    Job other = (Job) arg1;
                    this.name_ = visitor.visitString(hasName(), this.name_, other.hasName(), other.name_);
                    this.total_ = (TimerProto) visitor.visitMessage(this.total_, other.total_);
                    this.background_ = (TimerProto) visitor.visitMessage(this.background_, other.background_);
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
                                TimerProto.Builder subBuilder = null;
                                if ((this.bitField0_ & 2) == 2) {
                                    subBuilder = (TimerProto.Builder) this.total_.toBuilder();
                                }
                                this.total_ = (TimerProto) input.readMessage(TimerProto.parser(), extensionRegistry);
                                if (subBuilder != null) {
                                    subBuilder.mergeFrom((GeneratedMessageLite) this.total_);
                                    this.total_ = (TimerProto) subBuilder.buildPartial();
                                }
                                this.bitField0_ |= 2;
                            } else if (tag == 26) {
                                TimerProto.Builder subBuilder2 = null;
                                if ((this.bitField0_ & 4) == 4) {
                                    subBuilder2 = (TimerProto.Builder) this.background_.toBuilder();
                                }
                                this.background_ = (TimerProto) input.readMessage(TimerProto.parser(), extensionRegistry);
                                if (subBuilder2 != null) {
                                    subBuilder2.mergeFrom((GeneratedMessageLite) this.background_);
                                    this.background_ = (TimerProto) subBuilder2.buildPartial();
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
                        synchronized (Job.class) {
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

        public static Job getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<Job> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class JobCompletion extends GeneratedMessageLite<JobCompletion, Builder> implements JobCompletionOrBuilder {
        private static final JobCompletion DEFAULT_INSTANCE = new JobCompletion();
        public static final int NAME_FIELD_NUMBER = 1;
        private static volatile Parser<JobCompletion> PARSER = null;
        public static final int REASON_COUNT_FIELD_NUMBER = 2;
        private int bitField0_;
        private String name_ = "";
        private Internal.ProtobufList<ReasonCount> reasonCount_ = emptyProtobufList();

        public interface ReasonCountOrBuilder extends MessageLiteOrBuilder {
            int getCount();

            StopReasonEnum getName();

            boolean hasCount();

            boolean hasName();
        }

        private JobCompletion() {
        }

        public static final class ReasonCount extends GeneratedMessageLite<ReasonCount, Builder> implements ReasonCountOrBuilder {
            public static final int COUNT_FIELD_NUMBER = 2;
            private static final ReasonCount DEFAULT_INSTANCE = new ReasonCount();
            public static final int NAME_FIELD_NUMBER = 1;
            private static volatile Parser<ReasonCount> PARSER;
            private int bitField0_;
            private int count_ = 0;
            private int name_ = -1;

            private ReasonCount() {
            }

            @Override // android.os.UidProto.JobCompletion.ReasonCountOrBuilder
            public boolean hasName() {
                return (this.bitField0_ & 1) == 1;
            }

            @Override // android.os.UidProto.JobCompletion.ReasonCountOrBuilder
            public StopReasonEnum getName() {
                StopReasonEnum result = StopReasonEnum.forNumber(this.name_);
                return result == null ? StopReasonEnum.STOP_REASON_UNKNOWN : result;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void setName(StopReasonEnum value) {
                if (value != null) {
                    this.bitField0_ |= 1;
                    this.name_ = value.getNumber();
                    return;
                }
                throw new NullPointerException();
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void clearName() {
                this.bitField0_ &= -2;
                this.name_ = -1;
            }

            @Override // android.os.UidProto.JobCompletion.ReasonCountOrBuilder
            public boolean hasCount() {
                return (this.bitField0_ & 2) == 2;
            }

            @Override // android.os.UidProto.JobCompletion.ReasonCountOrBuilder
            public int getCount() {
                return this.count_;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void setCount(int value) {
                this.bitField0_ |= 2;
                this.count_ = value;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void clearCount() {
                this.bitField0_ &= -3;
                this.count_ = 0;
            }

            @Override // com.google.protobuf.MessageLite
            public void writeTo(CodedOutputStream output) throws IOException {
                if ((this.bitField0_ & 1) == 1) {
                    output.writeEnum(1, this.name_);
                }
                if ((this.bitField0_ & 2) == 2) {
                    output.writeInt32(2, this.count_);
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
                    size2 = 0 + CodedOutputStream.computeEnumSize(1, this.name_);
                }
                if ((this.bitField0_ & 2) == 2) {
                    size2 += CodedOutputStream.computeInt32Size(2, this.count_);
                }
                int size3 = size2 + this.unknownFields.getSerializedSize();
                this.memoizedSerializedSize = size3;
                return size3;
            }

            public static ReasonCount parseFrom(ByteString data) throws InvalidProtocolBufferException {
                return (ReasonCount) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
            }

            public static ReasonCount parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return (ReasonCount) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
            }

            public static ReasonCount parseFrom(byte[] data) throws InvalidProtocolBufferException {
                return (ReasonCount) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
            }

            public static ReasonCount parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return (ReasonCount) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
            }

            public static ReasonCount parseFrom(InputStream input) throws IOException {
                return (ReasonCount) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
            }

            public static ReasonCount parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (ReasonCount) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static ReasonCount parseDelimitedFrom(InputStream input) throws IOException {
                return (ReasonCount) parseDelimitedFrom(DEFAULT_INSTANCE, input);
            }

            public static ReasonCount parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (ReasonCount) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static ReasonCount parseFrom(CodedInputStream input) throws IOException {
                return (ReasonCount) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
            }

            public static ReasonCount parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (ReasonCount) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static Builder newBuilder() {
                return (Builder) DEFAULT_INSTANCE.toBuilder();
            }

            public static Builder newBuilder(ReasonCount prototype) {
                return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
            }

            public static final class Builder extends GeneratedMessageLite.Builder<ReasonCount, Builder> implements ReasonCountOrBuilder {
                private Builder() {
                    super(ReasonCount.DEFAULT_INSTANCE);
                }

                @Override // android.os.UidProto.JobCompletion.ReasonCountOrBuilder
                public boolean hasName() {
                    return ((ReasonCount) this.instance).hasName();
                }

                @Override // android.os.UidProto.JobCompletion.ReasonCountOrBuilder
                public StopReasonEnum getName() {
                    return ((ReasonCount) this.instance).getName();
                }

                public Builder setName(StopReasonEnum value) {
                    copyOnWrite();
                    ((ReasonCount) this.instance).setName(value);
                    return this;
                }

                public Builder clearName() {
                    copyOnWrite();
                    ((ReasonCount) this.instance).clearName();
                    return this;
                }

                @Override // android.os.UidProto.JobCompletion.ReasonCountOrBuilder
                public boolean hasCount() {
                    return ((ReasonCount) this.instance).hasCount();
                }

                @Override // android.os.UidProto.JobCompletion.ReasonCountOrBuilder
                public int getCount() {
                    return ((ReasonCount) this.instance).getCount();
                }

                public Builder setCount(int value) {
                    copyOnWrite();
                    ((ReasonCount) this.instance).setCount(value);
                    return this;
                }

                public Builder clearCount() {
                    copyOnWrite();
                    ((ReasonCount) this.instance).clearCount();
                    return this;
                }
            }

            /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
            /* access modifiers changed from: protected */
            @Override // com.google.protobuf.GeneratedMessageLite
            public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
                switch (method) {
                    case NEW_MUTABLE_INSTANCE:
                        return new ReasonCount();
                    case IS_INITIALIZED:
                        return DEFAULT_INSTANCE;
                    case MAKE_IMMUTABLE:
                        return null;
                    case NEW_BUILDER:
                        return new Builder();
                    case VISIT:
                        GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                        ReasonCount other = (ReasonCount) arg1;
                        this.name_ = visitor.visitInt(hasName(), this.name_, other.hasName(), other.name_);
                        this.count_ = visitor.visitInt(hasCount(), this.count_, other.hasCount(), other.count_);
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
                                    if (StopReasonEnum.forNumber(rawValue) == null) {
                                        super.mergeVarintField(1, rawValue);
                                    } else {
                                        this.bitField0_ = 1 | this.bitField0_;
                                        this.name_ = rawValue;
                                    }
                                } else if (tag == 16) {
                                    this.bitField0_ |= 2;
                                    this.count_ = input.readInt32();
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
                            synchronized (ReasonCount.class) {
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

            public static ReasonCount getDefaultInstance() {
                return DEFAULT_INSTANCE;
            }

            public static Parser<ReasonCount> parser() {
                return DEFAULT_INSTANCE.getParserForType();
            }
        }

        @Override // android.os.UidProto.JobCompletionOrBuilder
        public boolean hasName() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // android.os.UidProto.JobCompletionOrBuilder
        public String getName() {
            return this.name_;
        }

        @Override // android.os.UidProto.JobCompletionOrBuilder
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

        @Override // android.os.UidProto.JobCompletionOrBuilder
        public List<ReasonCount> getReasonCountList() {
            return this.reasonCount_;
        }

        public List<? extends ReasonCountOrBuilder> getReasonCountOrBuilderList() {
            return this.reasonCount_;
        }

        @Override // android.os.UidProto.JobCompletionOrBuilder
        public int getReasonCountCount() {
            return this.reasonCount_.size();
        }

        @Override // android.os.UidProto.JobCompletionOrBuilder
        public ReasonCount getReasonCount(int index) {
            return this.reasonCount_.get(index);
        }

        public ReasonCountOrBuilder getReasonCountOrBuilder(int index) {
            return this.reasonCount_.get(index);
        }

        private void ensureReasonCountIsMutable() {
            if (!this.reasonCount_.isModifiable()) {
                this.reasonCount_ = GeneratedMessageLite.mutableCopy(this.reasonCount_);
            }
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setReasonCount(int index, ReasonCount value) {
            if (value != null) {
                ensureReasonCountIsMutable();
                this.reasonCount_.set(index, value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setReasonCount(int index, ReasonCount.Builder builderForValue) {
            ensureReasonCountIsMutable();
            this.reasonCount_.set(index, (ReasonCount) builderForValue.build());
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addReasonCount(ReasonCount value) {
            if (value != null) {
                ensureReasonCountIsMutable();
                this.reasonCount_.add(value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addReasonCount(int index, ReasonCount value) {
            if (value != null) {
                ensureReasonCountIsMutable();
                this.reasonCount_.add(index, value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addReasonCount(ReasonCount.Builder builderForValue) {
            ensureReasonCountIsMutable();
            this.reasonCount_.add((ReasonCount) builderForValue.build());
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addReasonCount(int index, ReasonCount.Builder builderForValue) {
            ensureReasonCountIsMutable();
            this.reasonCount_.add(index, (ReasonCount) builderForValue.build());
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addAllReasonCount(Iterable<? extends ReasonCount> values) {
            ensureReasonCountIsMutable();
            AbstractMessageLite.addAll(values, this.reasonCount_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearReasonCount() {
            this.reasonCount_ = emptyProtobufList();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void removeReasonCount(int index) {
            ensureReasonCountIsMutable();
            this.reasonCount_.remove(index);
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                output.writeString(1, getName());
            }
            for (int i = 0; i < this.reasonCount_.size(); i++) {
                output.writeMessage(2, this.reasonCount_.get(i));
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
            for (int i = 0; i < this.reasonCount_.size(); i++) {
                size2 += CodedOutputStream.computeMessageSize(2, this.reasonCount_.get(i));
            }
            int size3 = size2 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static JobCompletion parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (JobCompletion) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static JobCompletion parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (JobCompletion) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static JobCompletion parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (JobCompletion) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static JobCompletion parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (JobCompletion) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static JobCompletion parseFrom(InputStream input) throws IOException {
            return (JobCompletion) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static JobCompletion parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (JobCompletion) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static JobCompletion parseDelimitedFrom(InputStream input) throws IOException {
            return (JobCompletion) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static JobCompletion parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (JobCompletion) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static JobCompletion parseFrom(CodedInputStream input) throws IOException {
            return (JobCompletion) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static JobCompletion parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (JobCompletion) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(JobCompletion prototype) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<JobCompletion, Builder> implements JobCompletionOrBuilder {
            private Builder() {
                super(JobCompletion.DEFAULT_INSTANCE);
            }

            @Override // android.os.UidProto.JobCompletionOrBuilder
            public boolean hasName() {
                return ((JobCompletion) this.instance).hasName();
            }

            @Override // android.os.UidProto.JobCompletionOrBuilder
            public String getName() {
                return ((JobCompletion) this.instance).getName();
            }

            @Override // android.os.UidProto.JobCompletionOrBuilder
            public ByteString getNameBytes() {
                return ((JobCompletion) this.instance).getNameBytes();
            }

            public Builder setName(String value) {
                copyOnWrite();
                ((JobCompletion) this.instance).setName(value);
                return this;
            }

            public Builder clearName() {
                copyOnWrite();
                ((JobCompletion) this.instance).clearName();
                return this;
            }

            public Builder setNameBytes(ByteString value) {
                copyOnWrite();
                ((JobCompletion) this.instance).setNameBytes(value);
                return this;
            }

            @Override // android.os.UidProto.JobCompletionOrBuilder
            public List<ReasonCount> getReasonCountList() {
                return Collections.unmodifiableList(((JobCompletion) this.instance).getReasonCountList());
            }

            @Override // android.os.UidProto.JobCompletionOrBuilder
            public int getReasonCountCount() {
                return ((JobCompletion) this.instance).getReasonCountCount();
            }

            @Override // android.os.UidProto.JobCompletionOrBuilder
            public ReasonCount getReasonCount(int index) {
                return ((JobCompletion) this.instance).getReasonCount(index);
            }

            public Builder setReasonCount(int index, ReasonCount value) {
                copyOnWrite();
                ((JobCompletion) this.instance).setReasonCount((JobCompletion) index, (int) value);
                return this;
            }

            public Builder setReasonCount(int index, ReasonCount.Builder builderForValue) {
                copyOnWrite();
                ((JobCompletion) this.instance).setReasonCount((JobCompletion) index, (int) builderForValue);
                return this;
            }

            public Builder addReasonCount(ReasonCount value) {
                copyOnWrite();
                ((JobCompletion) this.instance).addReasonCount((JobCompletion) value);
                return this;
            }

            public Builder addReasonCount(int index, ReasonCount value) {
                copyOnWrite();
                ((JobCompletion) this.instance).addReasonCount((JobCompletion) index, (int) value);
                return this;
            }

            public Builder addReasonCount(ReasonCount.Builder builderForValue) {
                copyOnWrite();
                ((JobCompletion) this.instance).addReasonCount((JobCompletion) builderForValue);
                return this;
            }

            public Builder addReasonCount(int index, ReasonCount.Builder builderForValue) {
                copyOnWrite();
                ((JobCompletion) this.instance).addReasonCount((JobCompletion) index, (int) builderForValue);
                return this;
            }

            public Builder addAllReasonCount(Iterable<? extends ReasonCount> values) {
                copyOnWrite();
                ((JobCompletion) this.instance).addAllReasonCount(values);
                return this;
            }

            public Builder clearReasonCount() {
                copyOnWrite();
                ((JobCompletion) this.instance).clearReasonCount();
                return this;
            }

            public Builder removeReasonCount(int index) {
                copyOnWrite();
                ((JobCompletion) this.instance).removeReasonCount(index);
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new JobCompletion();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    this.reasonCount_.makeImmutable();
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    JobCompletion other = (JobCompletion) arg1;
                    this.name_ = visitor.visitString(hasName(), this.name_, other.hasName(), other.name_);
                    this.reasonCount_ = visitor.visitList(this.reasonCount_, other.reasonCount_);
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
                                if (!this.reasonCount_.isModifiable()) {
                                    this.reasonCount_ = GeneratedMessageLite.mutableCopy(this.reasonCount_);
                                }
                                this.reasonCount_.add((ReasonCount) input.readMessage(ReasonCount.parser(), extensionRegistry));
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
                        synchronized (JobCompletion.class) {
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

        public static JobCompletion getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<JobCompletion> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class Network extends GeneratedMessageLite<Network, Builder> implements NetworkOrBuilder {
        public static final int BT_BYTES_RX_FIELD_NUMBER = 5;
        public static final int BT_BYTES_TX_FIELD_NUMBER = 6;
        private static final Network DEFAULT_INSTANCE = new Network();
        public static final int MOBILE_ACTIVE_COUNT_FIELD_NUMBER = 12;
        public static final int MOBILE_ACTIVE_DURATION_MS_FIELD_NUMBER = 11;
        public static final int MOBILE_BYTES_BG_RX_FIELD_NUMBER = 15;
        public static final int MOBILE_BYTES_BG_TX_FIELD_NUMBER = 16;
        public static final int MOBILE_BYTES_RX_FIELD_NUMBER = 1;
        public static final int MOBILE_BYTES_TX_FIELD_NUMBER = 2;
        public static final int MOBILE_PACKETS_BG_RX_FIELD_NUMBER = 19;
        public static final int MOBILE_PACKETS_BG_TX_FIELD_NUMBER = 20;
        public static final int MOBILE_PACKETS_RX_FIELD_NUMBER = 7;
        public static final int MOBILE_PACKETS_TX_FIELD_NUMBER = 8;
        public static final int MOBILE_WAKEUP_COUNT_FIELD_NUMBER = 13;
        private static volatile Parser<Network> PARSER = null;
        public static final int WIFI_BYTES_BG_RX_FIELD_NUMBER = 17;
        public static final int WIFI_BYTES_BG_TX_FIELD_NUMBER = 18;
        public static final int WIFI_BYTES_RX_FIELD_NUMBER = 3;
        public static final int WIFI_BYTES_TX_FIELD_NUMBER = 4;
        public static final int WIFI_PACKETS_BG_RX_FIELD_NUMBER = 21;
        public static final int WIFI_PACKETS_BG_TX_FIELD_NUMBER = 22;
        public static final int WIFI_PACKETS_RX_FIELD_NUMBER = 9;
        public static final int WIFI_PACKETS_TX_FIELD_NUMBER = 10;
        public static final int WIFI_WAKEUP_COUNT_FIELD_NUMBER = 14;
        private int bitField0_;
        private long btBytesRx_ = 0;
        private long btBytesTx_ = 0;
        private int mobileActiveCount_ = 0;
        private long mobileActiveDurationMs_ = 0;
        private long mobileBytesBgRx_ = 0;
        private long mobileBytesBgTx_ = 0;
        private long mobileBytesRx_ = 0;
        private long mobileBytesTx_ = 0;
        private long mobilePacketsBgRx_ = 0;
        private long mobilePacketsBgTx_ = 0;
        private long mobilePacketsRx_ = 0;
        private long mobilePacketsTx_ = 0;
        private int mobileWakeupCount_ = 0;
        private long wifiBytesBgRx_ = 0;
        private long wifiBytesBgTx_ = 0;
        private long wifiBytesRx_ = 0;
        private long wifiBytesTx_ = 0;
        private long wifiPacketsBgRx_ = 0;
        private long wifiPacketsBgTx_ = 0;
        private long wifiPacketsRx_ = 0;
        private long wifiPacketsTx_ = 0;
        private int wifiWakeupCount_ = 0;

        private Network() {
        }

        @Override // android.os.UidProto.NetworkOrBuilder
        public boolean hasMobileBytesRx() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // android.os.UidProto.NetworkOrBuilder
        public long getMobileBytesRx() {
            return this.mobileBytesRx_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setMobileBytesRx(long value) {
            this.bitField0_ |= 1;
            this.mobileBytesRx_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearMobileBytesRx() {
            this.bitField0_ &= -2;
            this.mobileBytesRx_ = 0;
        }

        @Override // android.os.UidProto.NetworkOrBuilder
        public boolean hasMobileBytesTx() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // android.os.UidProto.NetworkOrBuilder
        public long getMobileBytesTx() {
            return this.mobileBytesTx_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setMobileBytesTx(long value) {
            this.bitField0_ |= 2;
            this.mobileBytesTx_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearMobileBytesTx() {
            this.bitField0_ &= -3;
            this.mobileBytesTx_ = 0;
        }

        @Override // android.os.UidProto.NetworkOrBuilder
        public boolean hasWifiBytesRx() {
            return (this.bitField0_ & 4) == 4;
        }

        @Override // android.os.UidProto.NetworkOrBuilder
        public long getWifiBytesRx() {
            return this.wifiBytesRx_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setWifiBytesRx(long value) {
            this.bitField0_ |= 4;
            this.wifiBytesRx_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearWifiBytesRx() {
            this.bitField0_ &= -5;
            this.wifiBytesRx_ = 0;
        }

        @Override // android.os.UidProto.NetworkOrBuilder
        public boolean hasWifiBytesTx() {
            return (this.bitField0_ & 8) == 8;
        }

        @Override // android.os.UidProto.NetworkOrBuilder
        public long getWifiBytesTx() {
            return this.wifiBytesTx_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setWifiBytesTx(long value) {
            this.bitField0_ |= 8;
            this.wifiBytesTx_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearWifiBytesTx() {
            this.bitField0_ &= -9;
            this.wifiBytesTx_ = 0;
        }

        @Override // android.os.UidProto.NetworkOrBuilder
        public boolean hasBtBytesRx() {
            return (this.bitField0_ & 16) == 16;
        }

        @Override // android.os.UidProto.NetworkOrBuilder
        public long getBtBytesRx() {
            return this.btBytesRx_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setBtBytesRx(long value) {
            this.bitField0_ |= 16;
            this.btBytesRx_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearBtBytesRx() {
            this.bitField0_ &= -17;
            this.btBytesRx_ = 0;
        }

        @Override // android.os.UidProto.NetworkOrBuilder
        public boolean hasBtBytesTx() {
            return (this.bitField0_ & 32) == 32;
        }

        @Override // android.os.UidProto.NetworkOrBuilder
        public long getBtBytesTx() {
            return this.btBytesTx_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setBtBytesTx(long value) {
            this.bitField0_ |= 32;
            this.btBytesTx_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearBtBytesTx() {
            this.bitField0_ &= -33;
            this.btBytesTx_ = 0;
        }

        @Override // android.os.UidProto.NetworkOrBuilder
        public boolean hasMobilePacketsRx() {
            return (this.bitField0_ & 64) == 64;
        }

        @Override // android.os.UidProto.NetworkOrBuilder
        public long getMobilePacketsRx() {
            return this.mobilePacketsRx_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setMobilePacketsRx(long value) {
            this.bitField0_ |= 64;
            this.mobilePacketsRx_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearMobilePacketsRx() {
            this.bitField0_ &= -65;
            this.mobilePacketsRx_ = 0;
        }

        @Override // android.os.UidProto.NetworkOrBuilder
        public boolean hasMobilePacketsTx() {
            return (this.bitField0_ & 128) == 128;
        }

        @Override // android.os.UidProto.NetworkOrBuilder
        public long getMobilePacketsTx() {
            return this.mobilePacketsTx_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setMobilePacketsTx(long value) {
            this.bitField0_ |= 128;
            this.mobilePacketsTx_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearMobilePacketsTx() {
            this.bitField0_ &= -129;
            this.mobilePacketsTx_ = 0;
        }

        @Override // android.os.UidProto.NetworkOrBuilder
        public boolean hasWifiPacketsRx() {
            return (this.bitField0_ & 256) == 256;
        }

        @Override // android.os.UidProto.NetworkOrBuilder
        public long getWifiPacketsRx() {
            return this.wifiPacketsRx_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setWifiPacketsRx(long value) {
            this.bitField0_ |= 256;
            this.wifiPacketsRx_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearWifiPacketsRx() {
            this.bitField0_ &= -257;
            this.wifiPacketsRx_ = 0;
        }

        @Override // android.os.UidProto.NetworkOrBuilder
        public boolean hasWifiPacketsTx() {
            return (this.bitField0_ & 512) == 512;
        }

        @Override // android.os.UidProto.NetworkOrBuilder
        public long getWifiPacketsTx() {
            return this.wifiPacketsTx_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setWifiPacketsTx(long value) {
            this.bitField0_ |= 512;
            this.wifiPacketsTx_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearWifiPacketsTx() {
            this.bitField0_ &= -513;
            this.wifiPacketsTx_ = 0;
        }

        @Override // android.os.UidProto.NetworkOrBuilder
        public boolean hasMobileActiveDurationMs() {
            return (this.bitField0_ & 1024) == 1024;
        }

        @Override // android.os.UidProto.NetworkOrBuilder
        public long getMobileActiveDurationMs() {
            return this.mobileActiveDurationMs_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setMobileActiveDurationMs(long value) {
            this.bitField0_ |= 1024;
            this.mobileActiveDurationMs_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearMobileActiveDurationMs() {
            this.bitField0_ &= -1025;
            this.mobileActiveDurationMs_ = 0;
        }

        @Override // android.os.UidProto.NetworkOrBuilder
        public boolean hasMobileActiveCount() {
            return (this.bitField0_ & 2048) == 2048;
        }

        @Override // android.os.UidProto.NetworkOrBuilder
        public int getMobileActiveCount() {
            return this.mobileActiveCount_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setMobileActiveCount(int value) {
            this.bitField0_ |= 2048;
            this.mobileActiveCount_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearMobileActiveCount() {
            this.bitField0_ &= -2049;
            this.mobileActiveCount_ = 0;
        }

        @Override // android.os.UidProto.NetworkOrBuilder
        public boolean hasMobileWakeupCount() {
            return (this.bitField0_ & 4096) == 4096;
        }

        @Override // android.os.UidProto.NetworkOrBuilder
        public int getMobileWakeupCount() {
            return this.mobileWakeupCount_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setMobileWakeupCount(int value) {
            this.bitField0_ |= 4096;
            this.mobileWakeupCount_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearMobileWakeupCount() {
            this.bitField0_ &= -4097;
            this.mobileWakeupCount_ = 0;
        }

        @Override // android.os.UidProto.NetworkOrBuilder
        public boolean hasWifiWakeupCount() {
            return (this.bitField0_ & 8192) == 8192;
        }

        @Override // android.os.UidProto.NetworkOrBuilder
        public int getWifiWakeupCount() {
            return this.wifiWakeupCount_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setWifiWakeupCount(int value) {
            this.bitField0_ |= 8192;
            this.wifiWakeupCount_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearWifiWakeupCount() {
            this.bitField0_ &= -8193;
            this.wifiWakeupCount_ = 0;
        }

        @Override // android.os.UidProto.NetworkOrBuilder
        public boolean hasMobileBytesBgRx() {
            return (this.bitField0_ & 16384) == 16384;
        }

        @Override // android.os.UidProto.NetworkOrBuilder
        public long getMobileBytesBgRx() {
            return this.mobileBytesBgRx_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setMobileBytesBgRx(long value) {
            this.bitField0_ |= 16384;
            this.mobileBytesBgRx_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearMobileBytesBgRx() {
            this.bitField0_ &= -16385;
            this.mobileBytesBgRx_ = 0;
        }

        @Override // android.os.UidProto.NetworkOrBuilder
        public boolean hasMobileBytesBgTx() {
            return (this.bitField0_ & 32768) == 32768;
        }

        @Override // android.os.UidProto.NetworkOrBuilder
        public long getMobileBytesBgTx() {
            return this.mobileBytesBgTx_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setMobileBytesBgTx(long value) {
            this.bitField0_ |= 32768;
            this.mobileBytesBgTx_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearMobileBytesBgTx() {
            this.bitField0_ &= -32769;
            this.mobileBytesBgTx_ = 0;
        }

        @Override // android.os.UidProto.NetworkOrBuilder
        public boolean hasWifiBytesBgRx() {
            return (this.bitField0_ & 65536) == 65536;
        }

        @Override // android.os.UidProto.NetworkOrBuilder
        public long getWifiBytesBgRx() {
            return this.wifiBytesBgRx_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setWifiBytesBgRx(long value) {
            this.bitField0_ |= 65536;
            this.wifiBytesBgRx_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearWifiBytesBgRx() {
            this.bitField0_ &= -65537;
            this.wifiBytesBgRx_ = 0;
        }

        @Override // android.os.UidProto.NetworkOrBuilder
        public boolean hasWifiBytesBgTx() {
            return (this.bitField0_ & 131072) == 131072;
        }

        @Override // android.os.UidProto.NetworkOrBuilder
        public long getWifiBytesBgTx() {
            return this.wifiBytesBgTx_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setWifiBytesBgTx(long value) {
            this.bitField0_ |= 131072;
            this.wifiBytesBgTx_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearWifiBytesBgTx() {
            this.bitField0_ &= -131073;
            this.wifiBytesBgTx_ = 0;
        }

        @Override // android.os.UidProto.NetworkOrBuilder
        public boolean hasMobilePacketsBgRx() {
            return (this.bitField0_ & 262144) == 262144;
        }

        @Override // android.os.UidProto.NetworkOrBuilder
        public long getMobilePacketsBgRx() {
            return this.mobilePacketsBgRx_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setMobilePacketsBgRx(long value) {
            this.bitField0_ |= 262144;
            this.mobilePacketsBgRx_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearMobilePacketsBgRx() {
            this.bitField0_ &= -262145;
            this.mobilePacketsBgRx_ = 0;
        }

        @Override // android.os.UidProto.NetworkOrBuilder
        public boolean hasMobilePacketsBgTx() {
            return (this.bitField0_ & 524288) == 524288;
        }

        @Override // android.os.UidProto.NetworkOrBuilder
        public long getMobilePacketsBgTx() {
            return this.mobilePacketsBgTx_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setMobilePacketsBgTx(long value) {
            this.bitField0_ |= 524288;
            this.mobilePacketsBgTx_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearMobilePacketsBgTx() {
            this.bitField0_ &= -524289;
            this.mobilePacketsBgTx_ = 0;
        }

        @Override // android.os.UidProto.NetworkOrBuilder
        public boolean hasWifiPacketsBgRx() {
            return (this.bitField0_ & 1048576) == 1048576;
        }

        @Override // android.os.UidProto.NetworkOrBuilder
        public long getWifiPacketsBgRx() {
            return this.wifiPacketsBgRx_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setWifiPacketsBgRx(long value) {
            this.bitField0_ |= 1048576;
            this.wifiPacketsBgRx_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearWifiPacketsBgRx() {
            this.bitField0_ &= -1048577;
            this.wifiPacketsBgRx_ = 0;
        }

        @Override // android.os.UidProto.NetworkOrBuilder
        public boolean hasWifiPacketsBgTx() {
            return (this.bitField0_ & 2097152) == 2097152;
        }

        @Override // android.os.UidProto.NetworkOrBuilder
        public long getWifiPacketsBgTx() {
            return this.wifiPacketsBgTx_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setWifiPacketsBgTx(long value) {
            this.bitField0_ |= 2097152;
            this.wifiPacketsBgTx_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearWifiPacketsBgTx() {
            this.bitField0_ &= -2097153;
            this.wifiPacketsBgTx_ = 0;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                output.writeInt64(1, this.mobileBytesRx_);
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeInt64(2, this.mobileBytesTx_);
            }
            if ((this.bitField0_ & 4) == 4) {
                output.writeInt64(3, this.wifiBytesRx_);
            }
            if ((this.bitField0_ & 8) == 8) {
                output.writeInt64(4, this.wifiBytesTx_);
            }
            if ((this.bitField0_ & 16) == 16) {
                output.writeInt64(5, this.btBytesRx_);
            }
            if ((this.bitField0_ & 32) == 32) {
                output.writeInt64(6, this.btBytesTx_);
            }
            if ((this.bitField0_ & 64) == 64) {
                output.writeInt64(7, this.mobilePacketsRx_);
            }
            if ((this.bitField0_ & 128) == 128) {
                output.writeInt64(8, this.mobilePacketsTx_);
            }
            if ((this.bitField0_ & 256) == 256) {
                output.writeInt64(9, this.wifiPacketsRx_);
            }
            if ((this.bitField0_ & 512) == 512) {
                output.writeInt64(10, this.wifiPacketsTx_);
            }
            if ((this.bitField0_ & 1024) == 1024) {
                output.writeInt64(11, this.mobileActiveDurationMs_);
            }
            if ((this.bitField0_ & 2048) == 2048) {
                output.writeInt32(12, this.mobileActiveCount_);
            }
            if ((this.bitField0_ & 4096) == 4096) {
                output.writeInt32(13, this.mobileWakeupCount_);
            }
            if ((this.bitField0_ & 8192) == 8192) {
                output.writeInt32(14, this.wifiWakeupCount_);
            }
            if ((this.bitField0_ & 16384) == 16384) {
                output.writeInt64(15, this.mobileBytesBgRx_);
            }
            if ((this.bitField0_ & 32768) == 32768) {
                output.writeInt64(16, this.mobileBytesBgTx_);
            }
            if ((this.bitField0_ & 65536) == 65536) {
                output.writeInt64(17, this.wifiBytesBgRx_);
            }
            if ((this.bitField0_ & 131072) == 131072) {
                output.writeInt64(18, this.wifiBytesBgTx_);
            }
            if ((this.bitField0_ & 262144) == 262144) {
                output.writeInt64(19, this.mobilePacketsBgRx_);
            }
            if ((this.bitField0_ & 524288) == 524288) {
                output.writeInt64(20, this.mobilePacketsBgTx_);
            }
            if ((this.bitField0_ & 1048576) == 1048576) {
                output.writeInt64(21, this.wifiPacketsBgRx_);
            }
            if ((this.bitField0_ & 2097152) == 2097152) {
                output.writeInt64(22, this.wifiPacketsBgTx_);
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
                size2 = 0 + CodedOutputStream.computeInt64Size(1, this.mobileBytesRx_);
            }
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeInt64Size(2, this.mobileBytesTx_);
            }
            if ((this.bitField0_ & 4) == 4) {
                size2 += CodedOutputStream.computeInt64Size(3, this.wifiBytesRx_);
            }
            if ((this.bitField0_ & 8) == 8) {
                size2 += CodedOutputStream.computeInt64Size(4, this.wifiBytesTx_);
            }
            if ((this.bitField0_ & 16) == 16) {
                size2 += CodedOutputStream.computeInt64Size(5, this.btBytesRx_);
            }
            if ((this.bitField0_ & 32) == 32) {
                size2 += CodedOutputStream.computeInt64Size(6, this.btBytesTx_);
            }
            if ((this.bitField0_ & 64) == 64) {
                size2 += CodedOutputStream.computeInt64Size(7, this.mobilePacketsRx_);
            }
            if ((this.bitField0_ & 128) == 128) {
                size2 += CodedOutputStream.computeInt64Size(8, this.mobilePacketsTx_);
            }
            if ((this.bitField0_ & 256) == 256) {
                size2 += CodedOutputStream.computeInt64Size(9, this.wifiPacketsRx_);
            }
            if ((this.bitField0_ & 512) == 512) {
                size2 += CodedOutputStream.computeInt64Size(10, this.wifiPacketsTx_);
            }
            if ((this.bitField0_ & 1024) == 1024) {
                size2 += CodedOutputStream.computeInt64Size(11, this.mobileActiveDurationMs_);
            }
            if ((this.bitField0_ & 2048) == 2048) {
                size2 += CodedOutputStream.computeInt32Size(12, this.mobileActiveCount_);
            }
            if ((this.bitField0_ & 4096) == 4096) {
                size2 += CodedOutputStream.computeInt32Size(13, this.mobileWakeupCount_);
            }
            if ((this.bitField0_ & 8192) == 8192) {
                size2 += CodedOutputStream.computeInt32Size(14, this.wifiWakeupCount_);
            }
            if ((this.bitField0_ & 16384) == 16384) {
                size2 += CodedOutputStream.computeInt64Size(15, this.mobileBytesBgRx_);
            }
            if ((this.bitField0_ & 32768) == 32768) {
                size2 += CodedOutputStream.computeInt64Size(16, this.mobileBytesBgTx_);
            }
            if ((this.bitField0_ & 65536) == 65536) {
                size2 += CodedOutputStream.computeInt64Size(17, this.wifiBytesBgRx_);
            }
            if ((this.bitField0_ & 131072) == 131072) {
                size2 += CodedOutputStream.computeInt64Size(18, this.wifiBytesBgTx_);
            }
            if ((this.bitField0_ & 262144) == 262144) {
                size2 += CodedOutputStream.computeInt64Size(19, this.mobilePacketsBgRx_);
            }
            if ((this.bitField0_ & 524288) == 524288) {
                size2 += CodedOutputStream.computeInt64Size(20, this.mobilePacketsBgTx_);
            }
            if ((this.bitField0_ & 1048576) == 1048576) {
                size2 += CodedOutputStream.computeInt64Size(21, this.wifiPacketsBgRx_);
            }
            if ((this.bitField0_ & 2097152) == 2097152) {
                size2 += CodedOutputStream.computeInt64Size(22, this.wifiPacketsBgTx_);
            }
            int size3 = size2 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static Network parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (Network) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static Network parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Network) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static Network parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (Network) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static Network parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Network) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static Network parseFrom(InputStream input) throws IOException {
            return (Network) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static Network parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Network) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Network parseDelimitedFrom(InputStream input) throws IOException {
            return (Network) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static Network parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Network) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Network parseFrom(CodedInputStream input) throws IOException {
            return (Network) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static Network parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Network) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(Network prototype) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<Network, Builder> implements NetworkOrBuilder {
            private Builder() {
                super(Network.DEFAULT_INSTANCE);
            }

            @Override // android.os.UidProto.NetworkOrBuilder
            public boolean hasMobileBytesRx() {
                return ((Network) this.instance).hasMobileBytesRx();
            }

            @Override // android.os.UidProto.NetworkOrBuilder
            public long getMobileBytesRx() {
                return ((Network) this.instance).getMobileBytesRx();
            }

            public Builder setMobileBytesRx(long value) {
                copyOnWrite();
                ((Network) this.instance).setMobileBytesRx(value);
                return this;
            }

            public Builder clearMobileBytesRx() {
                copyOnWrite();
                ((Network) this.instance).clearMobileBytesRx();
                return this;
            }

            @Override // android.os.UidProto.NetworkOrBuilder
            public boolean hasMobileBytesTx() {
                return ((Network) this.instance).hasMobileBytesTx();
            }

            @Override // android.os.UidProto.NetworkOrBuilder
            public long getMobileBytesTx() {
                return ((Network) this.instance).getMobileBytesTx();
            }

            public Builder setMobileBytesTx(long value) {
                copyOnWrite();
                ((Network) this.instance).setMobileBytesTx(value);
                return this;
            }

            public Builder clearMobileBytesTx() {
                copyOnWrite();
                ((Network) this.instance).clearMobileBytesTx();
                return this;
            }

            @Override // android.os.UidProto.NetworkOrBuilder
            public boolean hasWifiBytesRx() {
                return ((Network) this.instance).hasWifiBytesRx();
            }

            @Override // android.os.UidProto.NetworkOrBuilder
            public long getWifiBytesRx() {
                return ((Network) this.instance).getWifiBytesRx();
            }

            public Builder setWifiBytesRx(long value) {
                copyOnWrite();
                ((Network) this.instance).setWifiBytesRx(value);
                return this;
            }

            public Builder clearWifiBytesRx() {
                copyOnWrite();
                ((Network) this.instance).clearWifiBytesRx();
                return this;
            }

            @Override // android.os.UidProto.NetworkOrBuilder
            public boolean hasWifiBytesTx() {
                return ((Network) this.instance).hasWifiBytesTx();
            }

            @Override // android.os.UidProto.NetworkOrBuilder
            public long getWifiBytesTx() {
                return ((Network) this.instance).getWifiBytesTx();
            }

            public Builder setWifiBytesTx(long value) {
                copyOnWrite();
                ((Network) this.instance).setWifiBytesTx(value);
                return this;
            }

            public Builder clearWifiBytesTx() {
                copyOnWrite();
                ((Network) this.instance).clearWifiBytesTx();
                return this;
            }

            @Override // android.os.UidProto.NetworkOrBuilder
            public boolean hasBtBytesRx() {
                return ((Network) this.instance).hasBtBytesRx();
            }

            @Override // android.os.UidProto.NetworkOrBuilder
            public long getBtBytesRx() {
                return ((Network) this.instance).getBtBytesRx();
            }

            public Builder setBtBytesRx(long value) {
                copyOnWrite();
                ((Network) this.instance).setBtBytesRx(value);
                return this;
            }

            public Builder clearBtBytesRx() {
                copyOnWrite();
                ((Network) this.instance).clearBtBytesRx();
                return this;
            }

            @Override // android.os.UidProto.NetworkOrBuilder
            public boolean hasBtBytesTx() {
                return ((Network) this.instance).hasBtBytesTx();
            }

            @Override // android.os.UidProto.NetworkOrBuilder
            public long getBtBytesTx() {
                return ((Network) this.instance).getBtBytesTx();
            }

            public Builder setBtBytesTx(long value) {
                copyOnWrite();
                ((Network) this.instance).setBtBytesTx(value);
                return this;
            }

            public Builder clearBtBytesTx() {
                copyOnWrite();
                ((Network) this.instance).clearBtBytesTx();
                return this;
            }

            @Override // android.os.UidProto.NetworkOrBuilder
            public boolean hasMobilePacketsRx() {
                return ((Network) this.instance).hasMobilePacketsRx();
            }

            @Override // android.os.UidProto.NetworkOrBuilder
            public long getMobilePacketsRx() {
                return ((Network) this.instance).getMobilePacketsRx();
            }

            public Builder setMobilePacketsRx(long value) {
                copyOnWrite();
                ((Network) this.instance).setMobilePacketsRx(value);
                return this;
            }

            public Builder clearMobilePacketsRx() {
                copyOnWrite();
                ((Network) this.instance).clearMobilePacketsRx();
                return this;
            }

            @Override // android.os.UidProto.NetworkOrBuilder
            public boolean hasMobilePacketsTx() {
                return ((Network) this.instance).hasMobilePacketsTx();
            }

            @Override // android.os.UidProto.NetworkOrBuilder
            public long getMobilePacketsTx() {
                return ((Network) this.instance).getMobilePacketsTx();
            }

            public Builder setMobilePacketsTx(long value) {
                copyOnWrite();
                ((Network) this.instance).setMobilePacketsTx(value);
                return this;
            }

            public Builder clearMobilePacketsTx() {
                copyOnWrite();
                ((Network) this.instance).clearMobilePacketsTx();
                return this;
            }

            @Override // android.os.UidProto.NetworkOrBuilder
            public boolean hasWifiPacketsRx() {
                return ((Network) this.instance).hasWifiPacketsRx();
            }

            @Override // android.os.UidProto.NetworkOrBuilder
            public long getWifiPacketsRx() {
                return ((Network) this.instance).getWifiPacketsRx();
            }

            public Builder setWifiPacketsRx(long value) {
                copyOnWrite();
                ((Network) this.instance).setWifiPacketsRx(value);
                return this;
            }

            public Builder clearWifiPacketsRx() {
                copyOnWrite();
                ((Network) this.instance).clearWifiPacketsRx();
                return this;
            }

            @Override // android.os.UidProto.NetworkOrBuilder
            public boolean hasWifiPacketsTx() {
                return ((Network) this.instance).hasWifiPacketsTx();
            }

            @Override // android.os.UidProto.NetworkOrBuilder
            public long getWifiPacketsTx() {
                return ((Network) this.instance).getWifiPacketsTx();
            }

            public Builder setWifiPacketsTx(long value) {
                copyOnWrite();
                ((Network) this.instance).setWifiPacketsTx(value);
                return this;
            }

            public Builder clearWifiPacketsTx() {
                copyOnWrite();
                ((Network) this.instance).clearWifiPacketsTx();
                return this;
            }

            @Override // android.os.UidProto.NetworkOrBuilder
            public boolean hasMobileActiveDurationMs() {
                return ((Network) this.instance).hasMobileActiveDurationMs();
            }

            @Override // android.os.UidProto.NetworkOrBuilder
            public long getMobileActiveDurationMs() {
                return ((Network) this.instance).getMobileActiveDurationMs();
            }

            public Builder setMobileActiveDurationMs(long value) {
                copyOnWrite();
                ((Network) this.instance).setMobileActiveDurationMs(value);
                return this;
            }

            public Builder clearMobileActiveDurationMs() {
                copyOnWrite();
                ((Network) this.instance).clearMobileActiveDurationMs();
                return this;
            }

            @Override // android.os.UidProto.NetworkOrBuilder
            public boolean hasMobileActiveCount() {
                return ((Network) this.instance).hasMobileActiveCount();
            }

            @Override // android.os.UidProto.NetworkOrBuilder
            public int getMobileActiveCount() {
                return ((Network) this.instance).getMobileActiveCount();
            }

            public Builder setMobileActiveCount(int value) {
                copyOnWrite();
                ((Network) this.instance).setMobileActiveCount(value);
                return this;
            }

            public Builder clearMobileActiveCount() {
                copyOnWrite();
                ((Network) this.instance).clearMobileActiveCount();
                return this;
            }

            @Override // android.os.UidProto.NetworkOrBuilder
            public boolean hasMobileWakeupCount() {
                return ((Network) this.instance).hasMobileWakeupCount();
            }

            @Override // android.os.UidProto.NetworkOrBuilder
            public int getMobileWakeupCount() {
                return ((Network) this.instance).getMobileWakeupCount();
            }

            public Builder setMobileWakeupCount(int value) {
                copyOnWrite();
                ((Network) this.instance).setMobileWakeupCount(value);
                return this;
            }

            public Builder clearMobileWakeupCount() {
                copyOnWrite();
                ((Network) this.instance).clearMobileWakeupCount();
                return this;
            }

            @Override // android.os.UidProto.NetworkOrBuilder
            public boolean hasWifiWakeupCount() {
                return ((Network) this.instance).hasWifiWakeupCount();
            }

            @Override // android.os.UidProto.NetworkOrBuilder
            public int getWifiWakeupCount() {
                return ((Network) this.instance).getWifiWakeupCount();
            }

            public Builder setWifiWakeupCount(int value) {
                copyOnWrite();
                ((Network) this.instance).setWifiWakeupCount(value);
                return this;
            }

            public Builder clearWifiWakeupCount() {
                copyOnWrite();
                ((Network) this.instance).clearWifiWakeupCount();
                return this;
            }

            @Override // android.os.UidProto.NetworkOrBuilder
            public boolean hasMobileBytesBgRx() {
                return ((Network) this.instance).hasMobileBytesBgRx();
            }

            @Override // android.os.UidProto.NetworkOrBuilder
            public long getMobileBytesBgRx() {
                return ((Network) this.instance).getMobileBytesBgRx();
            }

            public Builder setMobileBytesBgRx(long value) {
                copyOnWrite();
                ((Network) this.instance).setMobileBytesBgRx(value);
                return this;
            }

            public Builder clearMobileBytesBgRx() {
                copyOnWrite();
                ((Network) this.instance).clearMobileBytesBgRx();
                return this;
            }

            @Override // android.os.UidProto.NetworkOrBuilder
            public boolean hasMobileBytesBgTx() {
                return ((Network) this.instance).hasMobileBytesBgTx();
            }

            @Override // android.os.UidProto.NetworkOrBuilder
            public long getMobileBytesBgTx() {
                return ((Network) this.instance).getMobileBytesBgTx();
            }

            public Builder setMobileBytesBgTx(long value) {
                copyOnWrite();
                ((Network) this.instance).setMobileBytesBgTx(value);
                return this;
            }

            public Builder clearMobileBytesBgTx() {
                copyOnWrite();
                ((Network) this.instance).clearMobileBytesBgTx();
                return this;
            }

            @Override // android.os.UidProto.NetworkOrBuilder
            public boolean hasWifiBytesBgRx() {
                return ((Network) this.instance).hasWifiBytesBgRx();
            }

            @Override // android.os.UidProto.NetworkOrBuilder
            public long getWifiBytesBgRx() {
                return ((Network) this.instance).getWifiBytesBgRx();
            }

            public Builder setWifiBytesBgRx(long value) {
                copyOnWrite();
                ((Network) this.instance).setWifiBytesBgRx(value);
                return this;
            }

            public Builder clearWifiBytesBgRx() {
                copyOnWrite();
                ((Network) this.instance).clearWifiBytesBgRx();
                return this;
            }

            @Override // android.os.UidProto.NetworkOrBuilder
            public boolean hasWifiBytesBgTx() {
                return ((Network) this.instance).hasWifiBytesBgTx();
            }

            @Override // android.os.UidProto.NetworkOrBuilder
            public long getWifiBytesBgTx() {
                return ((Network) this.instance).getWifiBytesBgTx();
            }

            public Builder setWifiBytesBgTx(long value) {
                copyOnWrite();
                ((Network) this.instance).setWifiBytesBgTx(value);
                return this;
            }

            public Builder clearWifiBytesBgTx() {
                copyOnWrite();
                ((Network) this.instance).clearWifiBytesBgTx();
                return this;
            }

            @Override // android.os.UidProto.NetworkOrBuilder
            public boolean hasMobilePacketsBgRx() {
                return ((Network) this.instance).hasMobilePacketsBgRx();
            }

            @Override // android.os.UidProto.NetworkOrBuilder
            public long getMobilePacketsBgRx() {
                return ((Network) this.instance).getMobilePacketsBgRx();
            }

            public Builder setMobilePacketsBgRx(long value) {
                copyOnWrite();
                ((Network) this.instance).setMobilePacketsBgRx(value);
                return this;
            }

            public Builder clearMobilePacketsBgRx() {
                copyOnWrite();
                ((Network) this.instance).clearMobilePacketsBgRx();
                return this;
            }

            @Override // android.os.UidProto.NetworkOrBuilder
            public boolean hasMobilePacketsBgTx() {
                return ((Network) this.instance).hasMobilePacketsBgTx();
            }

            @Override // android.os.UidProto.NetworkOrBuilder
            public long getMobilePacketsBgTx() {
                return ((Network) this.instance).getMobilePacketsBgTx();
            }

            public Builder setMobilePacketsBgTx(long value) {
                copyOnWrite();
                ((Network) this.instance).setMobilePacketsBgTx(value);
                return this;
            }

            public Builder clearMobilePacketsBgTx() {
                copyOnWrite();
                ((Network) this.instance).clearMobilePacketsBgTx();
                return this;
            }

            @Override // android.os.UidProto.NetworkOrBuilder
            public boolean hasWifiPacketsBgRx() {
                return ((Network) this.instance).hasWifiPacketsBgRx();
            }

            @Override // android.os.UidProto.NetworkOrBuilder
            public long getWifiPacketsBgRx() {
                return ((Network) this.instance).getWifiPacketsBgRx();
            }

            public Builder setWifiPacketsBgRx(long value) {
                copyOnWrite();
                ((Network) this.instance).setWifiPacketsBgRx(value);
                return this;
            }

            public Builder clearWifiPacketsBgRx() {
                copyOnWrite();
                ((Network) this.instance).clearWifiPacketsBgRx();
                return this;
            }

            @Override // android.os.UidProto.NetworkOrBuilder
            public boolean hasWifiPacketsBgTx() {
                return ((Network) this.instance).hasWifiPacketsBgTx();
            }

            @Override // android.os.UidProto.NetworkOrBuilder
            public long getWifiPacketsBgTx() {
                return ((Network) this.instance).getWifiPacketsBgTx();
            }

            public Builder setWifiPacketsBgTx(long value) {
                copyOnWrite();
                ((Network) this.instance).setWifiPacketsBgTx(value);
                return this;
            }

            public Builder clearWifiPacketsBgTx() {
                copyOnWrite();
                ((Network) this.instance).clearWifiPacketsBgTx();
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new Network();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    Network other = (Network) arg1;
                    this.mobileBytesRx_ = visitor.visitLong(hasMobileBytesRx(), this.mobileBytesRx_, other.hasMobileBytesRx(), other.mobileBytesRx_);
                    this.mobileBytesTx_ = visitor.visitLong(hasMobileBytesTx(), this.mobileBytesTx_, other.hasMobileBytesTx(), other.mobileBytesTx_);
                    this.wifiBytesRx_ = visitor.visitLong(hasWifiBytesRx(), this.wifiBytesRx_, other.hasWifiBytesRx(), other.wifiBytesRx_);
                    this.wifiBytesTx_ = visitor.visitLong(hasWifiBytesTx(), this.wifiBytesTx_, other.hasWifiBytesTx(), other.wifiBytesTx_);
                    this.btBytesRx_ = visitor.visitLong(hasBtBytesRx(), this.btBytesRx_, other.hasBtBytesRx(), other.btBytesRx_);
                    this.btBytesTx_ = visitor.visitLong(hasBtBytesTx(), this.btBytesTx_, other.hasBtBytesTx(), other.btBytesTx_);
                    this.mobilePacketsRx_ = visitor.visitLong(hasMobilePacketsRx(), this.mobilePacketsRx_, other.hasMobilePacketsRx(), other.mobilePacketsRx_);
                    this.mobilePacketsTx_ = visitor.visitLong(hasMobilePacketsTx(), this.mobilePacketsTx_, other.hasMobilePacketsTx(), other.mobilePacketsTx_);
                    this.wifiPacketsRx_ = visitor.visitLong(hasWifiPacketsRx(), this.wifiPacketsRx_, other.hasWifiPacketsRx(), other.wifiPacketsRx_);
                    this.wifiPacketsTx_ = visitor.visitLong(hasWifiPacketsTx(), this.wifiPacketsTx_, other.hasWifiPacketsTx(), other.wifiPacketsTx_);
                    this.mobileActiveDurationMs_ = visitor.visitLong(hasMobileActiveDurationMs(), this.mobileActiveDurationMs_, other.hasMobileActiveDurationMs(), other.mobileActiveDurationMs_);
                    this.mobileActiveCount_ = visitor.visitInt(hasMobileActiveCount(), this.mobileActiveCount_, other.hasMobileActiveCount(), other.mobileActiveCount_);
                    this.mobileWakeupCount_ = visitor.visitInt(hasMobileWakeupCount(), this.mobileWakeupCount_, other.hasMobileWakeupCount(), other.mobileWakeupCount_);
                    this.wifiWakeupCount_ = visitor.visitInt(hasWifiWakeupCount(), this.wifiWakeupCount_, other.hasWifiWakeupCount(), other.wifiWakeupCount_);
                    this.mobileBytesBgRx_ = visitor.visitLong(hasMobileBytesBgRx(), this.mobileBytesBgRx_, other.hasMobileBytesBgRx(), other.mobileBytesBgRx_);
                    this.mobileBytesBgTx_ = visitor.visitLong(hasMobileBytesBgTx(), this.mobileBytesBgTx_, other.hasMobileBytesBgTx(), other.mobileBytesBgTx_);
                    this.wifiBytesBgRx_ = visitor.visitLong(hasWifiBytesBgRx(), this.wifiBytesBgRx_, other.hasWifiBytesBgRx(), other.wifiBytesBgRx_);
                    this.wifiBytesBgTx_ = visitor.visitLong(hasWifiBytesBgTx(), this.wifiBytesBgTx_, other.hasWifiBytesBgTx(), other.wifiBytesBgTx_);
                    this.mobilePacketsBgRx_ = visitor.visitLong(hasMobilePacketsBgRx(), this.mobilePacketsBgRx_, other.hasMobilePacketsBgRx(), other.mobilePacketsBgRx_);
                    this.mobilePacketsBgTx_ = visitor.visitLong(hasMobilePacketsBgTx(), this.mobilePacketsBgTx_, other.hasMobilePacketsBgTx(), other.mobilePacketsBgTx_);
                    this.wifiPacketsBgRx_ = visitor.visitLong(hasWifiPacketsBgRx(), this.wifiPacketsBgRx_, other.hasWifiPacketsBgRx(), other.wifiPacketsBgRx_);
                    this.wifiPacketsBgTx_ = visitor.visitLong(hasWifiPacketsBgTx(), this.wifiPacketsBgTx_, other.hasWifiPacketsBgTx(), other.wifiPacketsBgTx_);
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
                                    this.mobileBytesRx_ = input.readInt64();
                                    break;
                                case 16:
                                    this.bitField0_ |= 2;
                                    this.mobileBytesTx_ = input.readInt64();
                                    break;
                                case 24:
                                    this.bitField0_ |= 4;
                                    this.wifiBytesRx_ = input.readInt64();
                                    break;
                                case 32:
                                    this.bitField0_ |= 8;
                                    this.wifiBytesTx_ = input.readInt64();
                                    break;
                                case 40:
                                    this.bitField0_ |= 16;
                                    this.btBytesRx_ = input.readInt64();
                                    break;
                                case 48:
                                    this.bitField0_ |= 32;
                                    this.btBytesTx_ = input.readInt64();
                                    break;
                                case 56:
                                    this.bitField0_ |= 64;
                                    this.mobilePacketsRx_ = input.readInt64();
                                    break;
                                case 64:
                                    this.bitField0_ |= 128;
                                    this.mobilePacketsTx_ = input.readInt64();
                                    break;
                                case 72:
                                    this.bitField0_ |= 256;
                                    this.wifiPacketsRx_ = input.readInt64();
                                    break;
                                case 80:
                                    this.bitField0_ |= 512;
                                    this.wifiPacketsTx_ = input.readInt64();
                                    break;
                                case 88:
                                    this.bitField0_ |= 1024;
                                    this.mobileActiveDurationMs_ = input.readInt64();
                                    break;
                                case 96:
                                    this.bitField0_ |= 2048;
                                    this.mobileActiveCount_ = input.readInt32();
                                    break;
                                case 104:
                                    this.bitField0_ |= 4096;
                                    this.mobileWakeupCount_ = input.readInt32();
                                    break;
                                case 112:
                                    this.bitField0_ |= 8192;
                                    this.wifiWakeupCount_ = input.readInt32();
                                    break;
                                case 120:
                                    this.bitField0_ |= 16384;
                                    this.mobileBytesBgRx_ = input.readInt64();
                                    break;
                                case 128:
                                    this.bitField0_ |= 32768;
                                    this.mobileBytesBgTx_ = input.readInt64();
                                    break;
                                case 136:
                                    this.bitField0_ |= 65536;
                                    this.wifiBytesBgRx_ = input.readInt64();
                                    break;
                                case 144:
                                    this.bitField0_ |= 131072;
                                    this.wifiBytesBgTx_ = input.readInt64();
                                    break;
                                case 152:
                                    this.bitField0_ |= 262144;
                                    this.mobilePacketsBgRx_ = input.readInt64();
                                    break;
                                case 160:
                                    this.bitField0_ |= 524288;
                                    this.mobilePacketsBgTx_ = input.readInt64();
                                    break;
                                case 168:
                                    this.bitField0_ |= 1048576;
                                    this.wifiPacketsBgRx_ = input.readInt64();
                                    break;
                                case AtomsProto.Atom.ASSIST_GESTURE_PROGRESS_REPORTED_FIELD_NUMBER /*{ENCODED_INT: 176}*/:
                                    this.bitField0_ |= 2097152;
                                    this.wifiPacketsBgTx_ = input.readInt64();
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
                        synchronized (Network.class) {
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

        public static Network getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<Network> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class PowerUseItem extends GeneratedMessageLite<PowerUseItem, Builder> implements PowerUseItemOrBuilder {
        public static final int COMPUTED_POWER_MAH_FIELD_NUMBER = 1;
        private static final PowerUseItem DEFAULT_INSTANCE = new PowerUseItem();
        private static volatile Parser<PowerUseItem> PARSER = null;
        public static final int PROPORTIONAL_SMEAR_MAH_FIELD_NUMBER = 4;
        public static final int SCREEN_POWER_MAH_FIELD_NUMBER = 3;
        public static final int SHOULD_HIDE_FIELD_NUMBER = 2;
        private int bitField0_;
        private double computedPowerMah_ = 0.0d;
        private double proportionalSmearMah_ = 0.0d;
        private double screenPowerMah_ = 0.0d;
        private boolean shouldHide_ = false;

        private PowerUseItem() {
        }

        @Override // android.os.UidProto.PowerUseItemOrBuilder
        public boolean hasComputedPowerMah() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // android.os.UidProto.PowerUseItemOrBuilder
        public double getComputedPowerMah() {
            return this.computedPowerMah_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setComputedPowerMah(double value) {
            this.bitField0_ |= 1;
            this.computedPowerMah_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearComputedPowerMah() {
            this.bitField0_ &= -2;
            this.computedPowerMah_ = 0.0d;
        }

        @Override // android.os.UidProto.PowerUseItemOrBuilder
        public boolean hasShouldHide() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // android.os.UidProto.PowerUseItemOrBuilder
        public boolean getShouldHide() {
            return this.shouldHide_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setShouldHide(boolean value) {
            this.bitField0_ |= 2;
            this.shouldHide_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearShouldHide() {
            this.bitField0_ &= -3;
            this.shouldHide_ = false;
        }

        @Override // android.os.UidProto.PowerUseItemOrBuilder
        public boolean hasScreenPowerMah() {
            return (this.bitField0_ & 4) == 4;
        }

        @Override // android.os.UidProto.PowerUseItemOrBuilder
        public double getScreenPowerMah() {
            return this.screenPowerMah_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setScreenPowerMah(double value) {
            this.bitField0_ |= 4;
            this.screenPowerMah_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearScreenPowerMah() {
            this.bitField0_ &= -5;
            this.screenPowerMah_ = 0.0d;
        }

        @Override // android.os.UidProto.PowerUseItemOrBuilder
        public boolean hasProportionalSmearMah() {
            return (this.bitField0_ & 8) == 8;
        }

        @Override // android.os.UidProto.PowerUseItemOrBuilder
        public double getProportionalSmearMah() {
            return this.proportionalSmearMah_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setProportionalSmearMah(double value) {
            this.bitField0_ |= 8;
            this.proportionalSmearMah_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearProportionalSmearMah() {
            this.bitField0_ &= -9;
            this.proportionalSmearMah_ = 0.0d;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                output.writeDouble(1, this.computedPowerMah_);
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeBool(2, this.shouldHide_);
            }
            if ((this.bitField0_ & 4) == 4) {
                output.writeDouble(3, this.screenPowerMah_);
            }
            if ((this.bitField0_ & 8) == 8) {
                output.writeDouble(4, this.proportionalSmearMah_);
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
                size2 = 0 + CodedOutputStream.computeDoubleSize(1, this.computedPowerMah_);
            }
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeBoolSize(2, this.shouldHide_);
            }
            if ((this.bitField0_ & 4) == 4) {
                size2 += CodedOutputStream.computeDoubleSize(3, this.screenPowerMah_);
            }
            if ((this.bitField0_ & 8) == 8) {
                size2 += CodedOutputStream.computeDoubleSize(4, this.proportionalSmearMah_);
            }
            int size3 = size2 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static PowerUseItem parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (PowerUseItem) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static PowerUseItem parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (PowerUseItem) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static PowerUseItem parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (PowerUseItem) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static PowerUseItem parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (PowerUseItem) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static PowerUseItem parseFrom(InputStream input) throws IOException {
            return (PowerUseItem) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static PowerUseItem parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (PowerUseItem) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static PowerUseItem parseDelimitedFrom(InputStream input) throws IOException {
            return (PowerUseItem) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static PowerUseItem parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (PowerUseItem) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static PowerUseItem parseFrom(CodedInputStream input) throws IOException {
            return (PowerUseItem) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static PowerUseItem parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (PowerUseItem) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(PowerUseItem prototype) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<PowerUseItem, Builder> implements PowerUseItemOrBuilder {
            private Builder() {
                super(PowerUseItem.DEFAULT_INSTANCE);
            }

            @Override // android.os.UidProto.PowerUseItemOrBuilder
            public boolean hasComputedPowerMah() {
                return ((PowerUseItem) this.instance).hasComputedPowerMah();
            }

            @Override // android.os.UidProto.PowerUseItemOrBuilder
            public double getComputedPowerMah() {
                return ((PowerUseItem) this.instance).getComputedPowerMah();
            }

            public Builder setComputedPowerMah(double value) {
                copyOnWrite();
                ((PowerUseItem) this.instance).setComputedPowerMah(value);
                return this;
            }

            public Builder clearComputedPowerMah() {
                copyOnWrite();
                ((PowerUseItem) this.instance).clearComputedPowerMah();
                return this;
            }

            @Override // android.os.UidProto.PowerUseItemOrBuilder
            public boolean hasShouldHide() {
                return ((PowerUseItem) this.instance).hasShouldHide();
            }

            @Override // android.os.UidProto.PowerUseItemOrBuilder
            public boolean getShouldHide() {
                return ((PowerUseItem) this.instance).getShouldHide();
            }

            public Builder setShouldHide(boolean value) {
                copyOnWrite();
                ((PowerUseItem) this.instance).setShouldHide(value);
                return this;
            }

            public Builder clearShouldHide() {
                copyOnWrite();
                ((PowerUseItem) this.instance).clearShouldHide();
                return this;
            }

            @Override // android.os.UidProto.PowerUseItemOrBuilder
            public boolean hasScreenPowerMah() {
                return ((PowerUseItem) this.instance).hasScreenPowerMah();
            }

            @Override // android.os.UidProto.PowerUseItemOrBuilder
            public double getScreenPowerMah() {
                return ((PowerUseItem) this.instance).getScreenPowerMah();
            }

            public Builder setScreenPowerMah(double value) {
                copyOnWrite();
                ((PowerUseItem) this.instance).setScreenPowerMah(value);
                return this;
            }

            public Builder clearScreenPowerMah() {
                copyOnWrite();
                ((PowerUseItem) this.instance).clearScreenPowerMah();
                return this;
            }

            @Override // android.os.UidProto.PowerUseItemOrBuilder
            public boolean hasProportionalSmearMah() {
                return ((PowerUseItem) this.instance).hasProportionalSmearMah();
            }

            @Override // android.os.UidProto.PowerUseItemOrBuilder
            public double getProportionalSmearMah() {
                return ((PowerUseItem) this.instance).getProportionalSmearMah();
            }

            public Builder setProportionalSmearMah(double value) {
                copyOnWrite();
                ((PowerUseItem) this.instance).setProportionalSmearMah(value);
                return this;
            }

            public Builder clearProportionalSmearMah() {
                copyOnWrite();
                ((PowerUseItem) this.instance).clearProportionalSmearMah();
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new PowerUseItem();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    PowerUseItem other = (PowerUseItem) arg1;
                    this.computedPowerMah_ = visitor.visitDouble(hasComputedPowerMah(), this.computedPowerMah_, other.hasComputedPowerMah(), other.computedPowerMah_);
                    this.shouldHide_ = visitor.visitBoolean(hasShouldHide(), this.shouldHide_, other.hasShouldHide(), other.shouldHide_);
                    this.screenPowerMah_ = visitor.visitDouble(hasScreenPowerMah(), this.screenPowerMah_, other.hasScreenPowerMah(), other.screenPowerMah_);
                    this.proportionalSmearMah_ = visitor.visitDouble(hasProportionalSmearMah(), this.proportionalSmearMah_, other.hasProportionalSmearMah(), other.proportionalSmearMah_);
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
                            } else if (tag == 9) {
                                this.bitField0_ |= 1;
                                this.computedPowerMah_ = input.readDouble();
                            } else if (tag == 16) {
                                this.bitField0_ |= 2;
                                this.shouldHide_ = input.readBool();
                            } else if (tag == 25) {
                                this.bitField0_ |= 4;
                                this.screenPowerMah_ = input.readDouble();
                            } else if (tag == 33) {
                                this.bitField0_ |= 8;
                                this.proportionalSmearMah_ = input.readDouble();
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
                        synchronized (PowerUseItem.class) {
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

        public static PowerUseItem getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<PowerUseItem> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class Process extends GeneratedMessageLite<Process, Builder> implements ProcessOrBuilder {
        public static final int ANR_COUNT_FIELD_NUMBER = 6;
        public static final int CRASH_COUNT_FIELD_NUMBER = 7;
        private static final Process DEFAULT_INSTANCE = new Process();
        public static final int FOREGROUND_DURATION_MS_FIELD_NUMBER = 4;
        public static final int NAME_FIELD_NUMBER = 1;
        private static volatile Parser<Process> PARSER = null;
        public static final int START_COUNT_FIELD_NUMBER = 5;
        public static final int SYSTEM_DURATION_MS_FIELD_NUMBER = 3;
        public static final int USER_DURATION_MS_FIELD_NUMBER = 2;
        private int anrCount_ = 0;
        private int bitField0_;
        private int crashCount_ = 0;
        private long foregroundDurationMs_ = 0;
        private String name_ = "";
        private int startCount_ = 0;
        private long systemDurationMs_ = 0;
        private long userDurationMs_ = 0;

        private Process() {
        }

        @Override // android.os.UidProto.ProcessOrBuilder
        public boolean hasName() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // android.os.UidProto.ProcessOrBuilder
        public String getName() {
            return this.name_;
        }

        @Override // android.os.UidProto.ProcessOrBuilder
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

        @Override // android.os.UidProto.ProcessOrBuilder
        public boolean hasUserDurationMs() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // android.os.UidProto.ProcessOrBuilder
        public long getUserDurationMs() {
            return this.userDurationMs_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setUserDurationMs(long value) {
            this.bitField0_ |= 2;
            this.userDurationMs_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearUserDurationMs() {
            this.bitField0_ &= -3;
            this.userDurationMs_ = 0;
        }

        @Override // android.os.UidProto.ProcessOrBuilder
        public boolean hasSystemDurationMs() {
            return (this.bitField0_ & 4) == 4;
        }

        @Override // android.os.UidProto.ProcessOrBuilder
        public long getSystemDurationMs() {
            return this.systemDurationMs_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setSystemDurationMs(long value) {
            this.bitField0_ |= 4;
            this.systemDurationMs_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearSystemDurationMs() {
            this.bitField0_ &= -5;
            this.systemDurationMs_ = 0;
        }

        @Override // android.os.UidProto.ProcessOrBuilder
        public boolean hasForegroundDurationMs() {
            return (this.bitField0_ & 8) == 8;
        }

        @Override // android.os.UidProto.ProcessOrBuilder
        public long getForegroundDurationMs() {
            return this.foregroundDurationMs_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setForegroundDurationMs(long value) {
            this.bitField0_ |= 8;
            this.foregroundDurationMs_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearForegroundDurationMs() {
            this.bitField0_ &= -9;
            this.foregroundDurationMs_ = 0;
        }

        @Override // android.os.UidProto.ProcessOrBuilder
        public boolean hasStartCount() {
            return (this.bitField0_ & 16) == 16;
        }

        @Override // android.os.UidProto.ProcessOrBuilder
        public int getStartCount() {
            return this.startCount_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setStartCount(int value) {
            this.bitField0_ |= 16;
            this.startCount_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearStartCount() {
            this.bitField0_ &= -17;
            this.startCount_ = 0;
        }

        @Override // android.os.UidProto.ProcessOrBuilder
        public boolean hasAnrCount() {
            return (this.bitField0_ & 32) == 32;
        }

        @Override // android.os.UidProto.ProcessOrBuilder
        public int getAnrCount() {
            return this.anrCount_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setAnrCount(int value) {
            this.bitField0_ |= 32;
            this.anrCount_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearAnrCount() {
            this.bitField0_ &= -33;
            this.anrCount_ = 0;
        }

        @Override // android.os.UidProto.ProcessOrBuilder
        public boolean hasCrashCount() {
            return (this.bitField0_ & 64) == 64;
        }

        @Override // android.os.UidProto.ProcessOrBuilder
        public int getCrashCount() {
            return this.crashCount_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setCrashCount(int value) {
            this.bitField0_ |= 64;
            this.crashCount_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearCrashCount() {
            this.bitField0_ &= -65;
            this.crashCount_ = 0;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                output.writeString(1, getName());
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeInt64(2, this.userDurationMs_);
            }
            if ((this.bitField0_ & 4) == 4) {
                output.writeInt64(3, this.systemDurationMs_);
            }
            if ((this.bitField0_ & 8) == 8) {
                output.writeInt64(4, this.foregroundDurationMs_);
            }
            if ((this.bitField0_ & 16) == 16) {
                output.writeInt32(5, this.startCount_);
            }
            if ((this.bitField0_ & 32) == 32) {
                output.writeInt32(6, this.anrCount_);
            }
            if ((this.bitField0_ & 64) == 64) {
                output.writeInt32(7, this.crashCount_);
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
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeInt64Size(2, this.userDurationMs_);
            }
            if ((this.bitField0_ & 4) == 4) {
                size2 += CodedOutputStream.computeInt64Size(3, this.systemDurationMs_);
            }
            if ((this.bitField0_ & 8) == 8) {
                size2 += CodedOutputStream.computeInt64Size(4, this.foregroundDurationMs_);
            }
            if ((this.bitField0_ & 16) == 16) {
                size2 += CodedOutputStream.computeInt32Size(5, this.startCount_);
            }
            if ((this.bitField0_ & 32) == 32) {
                size2 += CodedOutputStream.computeInt32Size(6, this.anrCount_);
            }
            if ((this.bitField0_ & 64) == 64) {
                size2 += CodedOutputStream.computeInt32Size(7, this.crashCount_);
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

            @Override // android.os.UidProto.ProcessOrBuilder
            public boolean hasName() {
                return ((Process) this.instance).hasName();
            }

            @Override // android.os.UidProto.ProcessOrBuilder
            public String getName() {
                return ((Process) this.instance).getName();
            }

            @Override // android.os.UidProto.ProcessOrBuilder
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

            @Override // android.os.UidProto.ProcessOrBuilder
            public boolean hasUserDurationMs() {
                return ((Process) this.instance).hasUserDurationMs();
            }

            @Override // android.os.UidProto.ProcessOrBuilder
            public long getUserDurationMs() {
                return ((Process) this.instance).getUserDurationMs();
            }

            public Builder setUserDurationMs(long value) {
                copyOnWrite();
                ((Process) this.instance).setUserDurationMs(value);
                return this;
            }

            public Builder clearUserDurationMs() {
                copyOnWrite();
                ((Process) this.instance).clearUserDurationMs();
                return this;
            }

            @Override // android.os.UidProto.ProcessOrBuilder
            public boolean hasSystemDurationMs() {
                return ((Process) this.instance).hasSystemDurationMs();
            }

            @Override // android.os.UidProto.ProcessOrBuilder
            public long getSystemDurationMs() {
                return ((Process) this.instance).getSystemDurationMs();
            }

            public Builder setSystemDurationMs(long value) {
                copyOnWrite();
                ((Process) this.instance).setSystemDurationMs(value);
                return this;
            }

            public Builder clearSystemDurationMs() {
                copyOnWrite();
                ((Process) this.instance).clearSystemDurationMs();
                return this;
            }

            @Override // android.os.UidProto.ProcessOrBuilder
            public boolean hasForegroundDurationMs() {
                return ((Process) this.instance).hasForegroundDurationMs();
            }

            @Override // android.os.UidProto.ProcessOrBuilder
            public long getForegroundDurationMs() {
                return ((Process) this.instance).getForegroundDurationMs();
            }

            public Builder setForegroundDurationMs(long value) {
                copyOnWrite();
                ((Process) this.instance).setForegroundDurationMs(value);
                return this;
            }

            public Builder clearForegroundDurationMs() {
                copyOnWrite();
                ((Process) this.instance).clearForegroundDurationMs();
                return this;
            }

            @Override // android.os.UidProto.ProcessOrBuilder
            public boolean hasStartCount() {
                return ((Process) this.instance).hasStartCount();
            }

            @Override // android.os.UidProto.ProcessOrBuilder
            public int getStartCount() {
                return ((Process) this.instance).getStartCount();
            }

            public Builder setStartCount(int value) {
                copyOnWrite();
                ((Process) this.instance).setStartCount(value);
                return this;
            }

            public Builder clearStartCount() {
                copyOnWrite();
                ((Process) this.instance).clearStartCount();
                return this;
            }

            @Override // android.os.UidProto.ProcessOrBuilder
            public boolean hasAnrCount() {
                return ((Process) this.instance).hasAnrCount();
            }

            @Override // android.os.UidProto.ProcessOrBuilder
            public int getAnrCount() {
                return ((Process) this.instance).getAnrCount();
            }

            public Builder setAnrCount(int value) {
                copyOnWrite();
                ((Process) this.instance).setAnrCount(value);
                return this;
            }

            public Builder clearAnrCount() {
                copyOnWrite();
                ((Process) this.instance).clearAnrCount();
                return this;
            }

            @Override // android.os.UidProto.ProcessOrBuilder
            public boolean hasCrashCount() {
                return ((Process) this.instance).hasCrashCount();
            }

            @Override // android.os.UidProto.ProcessOrBuilder
            public int getCrashCount() {
                return ((Process) this.instance).getCrashCount();
            }

            public Builder setCrashCount(int value) {
                copyOnWrite();
                ((Process) this.instance).setCrashCount(value);
                return this;
            }

            public Builder clearCrashCount() {
                copyOnWrite();
                ((Process) this.instance).clearCrashCount();
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
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    Process other = (Process) arg1;
                    this.name_ = visitor.visitString(hasName(), this.name_, other.hasName(), other.name_);
                    this.userDurationMs_ = visitor.visitLong(hasUserDurationMs(), this.userDurationMs_, other.hasUserDurationMs(), other.userDurationMs_);
                    this.systemDurationMs_ = visitor.visitLong(hasSystemDurationMs(), this.systemDurationMs_, other.hasSystemDurationMs(), other.systemDurationMs_);
                    this.foregroundDurationMs_ = visitor.visitLong(hasForegroundDurationMs(), this.foregroundDurationMs_, other.hasForegroundDurationMs(), other.foregroundDurationMs_);
                    this.startCount_ = visitor.visitInt(hasStartCount(), this.startCount_, other.hasStartCount(), other.startCount_);
                    this.anrCount_ = visitor.visitInt(hasAnrCount(), this.anrCount_, other.hasAnrCount(), other.anrCount_);
                    this.crashCount_ = visitor.visitInt(hasCrashCount(), this.crashCount_, other.hasCrashCount(), other.crashCount_);
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
                                this.name_ = s;
                            } else if (tag == 16) {
                                this.bitField0_ |= 2;
                                this.userDurationMs_ = input.readInt64();
                            } else if (tag == 24) {
                                this.bitField0_ |= 4;
                                this.systemDurationMs_ = input.readInt64();
                            } else if (tag == 32) {
                                this.bitField0_ |= 8;
                                this.foregroundDurationMs_ = input.readInt64();
                            } else if (tag == 40) {
                                this.bitField0_ = 16 | this.bitField0_;
                                this.startCount_ = input.readInt32();
                            } else if (tag == 48) {
                                this.bitField0_ |= 32;
                                this.anrCount_ = input.readInt32();
                            } else if (tag == 56) {
                                this.bitField0_ |= 64;
                                this.crashCount_ = input.readInt32();
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

    public static final class StateTime extends GeneratedMessageLite<StateTime, Builder> implements StateTimeOrBuilder {
        private static final StateTime DEFAULT_INSTANCE = new StateTime();
        public static final int DURATION_MS_FIELD_NUMBER = 2;
        private static volatile Parser<StateTime> PARSER = null;
        public static final int STATE_FIELD_NUMBER = 1;
        private int bitField0_;
        private long durationMs_ = 0;
        private int state_ = 0;

        private StateTime() {
        }

        public enum State implements Internal.EnumLite {
            PROCESS_STATE_TOP(0),
            PROCESS_STATE_FOREGROUND_SERVICE(1),
            PROCESS_STATE_FOREGROUND(2),
            PROCESS_STATE_BACKGROUND(3),
            PROCESS_STATE_TOP_SLEEPING(4),
            PROCESS_STATE_HEAVY_WEIGHT(5),
            PROCESS_STATE_CACHED(6);
            
            public static final int PROCESS_STATE_BACKGROUND_VALUE = 3;
            public static final int PROCESS_STATE_CACHED_VALUE = 6;
            public static final int PROCESS_STATE_FOREGROUND_SERVICE_VALUE = 1;
            public static final int PROCESS_STATE_FOREGROUND_VALUE = 2;
            public static final int PROCESS_STATE_HEAVY_WEIGHT_VALUE = 5;
            public static final int PROCESS_STATE_TOP_SLEEPING_VALUE = 4;
            public static final int PROCESS_STATE_TOP_VALUE = 0;
            private static final Internal.EnumLiteMap<State> internalValueMap = new Internal.EnumLiteMap<State>() {
                /* class android.os.UidProto.StateTime.State.AnonymousClass1 */

                @Override // com.google.protobuf.Internal.EnumLiteMap
                public State findValueByNumber(int number) {
                    return State.forNumber(number);
                }
            };
            private final int value;

            @Override // com.google.protobuf.Internal.EnumLite
            public final int getNumber() {
                return this.value;
            }

            @Deprecated
            public static State valueOf(int value2) {
                return forNumber(value2);
            }

            public static State forNumber(int value2) {
                switch (value2) {
                    case 0:
                        return PROCESS_STATE_TOP;
                    case 1:
                        return PROCESS_STATE_FOREGROUND_SERVICE;
                    case 2:
                        return PROCESS_STATE_FOREGROUND;
                    case 3:
                        return PROCESS_STATE_BACKGROUND;
                    case 4:
                        return PROCESS_STATE_TOP_SLEEPING;
                    case 5:
                        return PROCESS_STATE_HEAVY_WEIGHT;
                    case 6:
                        return PROCESS_STATE_CACHED;
                    default:
                        return null;
                }
            }

            public static Internal.EnumLiteMap<State> internalGetValueMap() {
                return internalValueMap;
            }

            private State(int value2) {
                this.value = value2;
            }
        }

        @Override // android.os.UidProto.StateTimeOrBuilder
        public boolean hasState() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // android.os.UidProto.StateTimeOrBuilder
        public State getState() {
            State result = State.forNumber(this.state_);
            return result == null ? State.PROCESS_STATE_TOP : result;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setState(State value) {
            if (value != null) {
                this.bitField0_ |= 1;
                this.state_ = value.getNumber();
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearState() {
            this.bitField0_ &= -2;
            this.state_ = 0;
        }

        @Override // android.os.UidProto.StateTimeOrBuilder
        public boolean hasDurationMs() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // android.os.UidProto.StateTimeOrBuilder
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

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                output.writeEnum(1, this.state_);
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeInt64(2, this.durationMs_);
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
                size2 = 0 + CodedOutputStream.computeEnumSize(1, this.state_);
            }
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeInt64Size(2, this.durationMs_);
            }
            int size3 = size2 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static StateTime parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (StateTime) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static StateTime parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (StateTime) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static StateTime parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (StateTime) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static StateTime parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (StateTime) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static StateTime parseFrom(InputStream input) throws IOException {
            return (StateTime) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static StateTime parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (StateTime) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static StateTime parseDelimitedFrom(InputStream input) throws IOException {
            return (StateTime) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static StateTime parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (StateTime) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static StateTime parseFrom(CodedInputStream input) throws IOException {
            return (StateTime) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static StateTime parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (StateTime) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(StateTime prototype) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<StateTime, Builder> implements StateTimeOrBuilder {
            private Builder() {
                super(StateTime.DEFAULT_INSTANCE);
            }

            @Override // android.os.UidProto.StateTimeOrBuilder
            public boolean hasState() {
                return ((StateTime) this.instance).hasState();
            }

            @Override // android.os.UidProto.StateTimeOrBuilder
            public State getState() {
                return ((StateTime) this.instance).getState();
            }

            public Builder setState(State value) {
                copyOnWrite();
                ((StateTime) this.instance).setState(value);
                return this;
            }

            public Builder clearState() {
                copyOnWrite();
                ((StateTime) this.instance).clearState();
                return this;
            }

            @Override // android.os.UidProto.StateTimeOrBuilder
            public boolean hasDurationMs() {
                return ((StateTime) this.instance).hasDurationMs();
            }

            @Override // android.os.UidProto.StateTimeOrBuilder
            public long getDurationMs() {
                return ((StateTime) this.instance).getDurationMs();
            }

            public Builder setDurationMs(long value) {
                copyOnWrite();
                ((StateTime) this.instance).setDurationMs(value);
                return this;
            }

            public Builder clearDurationMs() {
                copyOnWrite();
                ((StateTime) this.instance).clearDurationMs();
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new StateTime();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    StateTime other = (StateTime) arg1;
                    this.state_ = visitor.visitInt(hasState(), this.state_, other.hasState(), other.state_);
                    this.durationMs_ = visitor.visitLong(hasDurationMs(), this.durationMs_, other.hasDurationMs(), other.durationMs_);
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
                                if (State.forNumber(rawValue) == null) {
                                    super.mergeVarintField(1, rawValue);
                                } else {
                                    this.bitField0_ = 1 | this.bitField0_;
                                    this.state_ = rawValue;
                                }
                            } else if (tag == 16) {
                                this.bitField0_ |= 2;
                                this.durationMs_ = input.readInt64();
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
                        synchronized (StateTime.class) {
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

        public static StateTime getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<StateTime> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class Sensor extends GeneratedMessageLite<Sensor, Builder> implements SensorOrBuilder {
        public static final int APPORTIONED_FIELD_NUMBER = 2;
        public static final int BACKGROUND_FIELD_NUMBER = 3;
        private static final Sensor DEFAULT_INSTANCE = new Sensor();
        public static final int ID_FIELD_NUMBER = 1;
        private static volatile Parser<Sensor> PARSER;
        private TimerProto apportioned_;
        private TimerProto background_;
        private int bitField0_;
        private int id_ = 0;

        private Sensor() {
        }

        @Override // android.os.UidProto.SensorOrBuilder
        public boolean hasId() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // android.os.UidProto.SensorOrBuilder
        public int getId() {
            return this.id_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setId(int value) {
            this.bitField0_ |= 1;
            this.id_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearId() {
            this.bitField0_ &= -2;
            this.id_ = 0;
        }

        @Override // android.os.UidProto.SensorOrBuilder
        public boolean hasApportioned() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // android.os.UidProto.SensorOrBuilder
        public TimerProto getApportioned() {
            TimerProto timerProto = this.apportioned_;
            return timerProto == null ? TimerProto.getDefaultInstance() : timerProto;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setApportioned(TimerProto value) {
            if (value != null) {
                this.apportioned_ = value;
                this.bitField0_ |= 2;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setApportioned(TimerProto.Builder builderForValue) {
            this.apportioned_ = (TimerProto) builderForValue.build();
            this.bitField0_ |= 2;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void mergeApportioned(TimerProto value) {
            TimerProto timerProto = this.apportioned_;
            if (timerProto == null || timerProto == TimerProto.getDefaultInstance()) {
                this.apportioned_ = value;
            } else {
                this.apportioned_ = (TimerProto) ((TimerProto.Builder) TimerProto.newBuilder(this.apportioned_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
            }
            this.bitField0_ |= 2;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearApportioned() {
            this.apportioned_ = null;
            this.bitField0_ &= -3;
        }

        @Override // android.os.UidProto.SensorOrBuilder
        public boolean hasBackground() {
            return (this.bitField0_ & 4) == 4;
        }

        @Override // android.os.UidProto.SensorOrBuilder
        public TimerProto getBackground() {
            TimerProto timerProto = this.background_;
            return timerProto == null ? TimerProto.getDefaultInstance() : timerProto;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setBackground(TimerProto value) {
            if (value != null) {
                this.background_ = value;
                this.bitField0_ |= 4;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setBackground(TimerProto.Builder builderForValue) {
            this.background_ = (TimerProto) builderForValue.build();
            this.bitField0_ |= 4;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void mergeBackground(TimerProto value) {
            TimerProto timerProto = this.background_;
            if (timerProto == null || timerProto == TimerProto.getDefaultInstance()) {
                this.background_ = value;
            } else {
                this.background_ = (TimerProto) ((TimerProto.Builder) TimerProto.newBuilder(this.background_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
            }
            this.bitField0_ |= 4;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearBackground() {
            this.background_ = null;
            this.bitField0_ &= -5;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                output.writeInt32(1, this.id_);
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeMessage(2, getApportioned());
            }
            if ((this.bitField0_ & 4) == 4) {
                output.writeMessage(3, getBackground());
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
                size2 = 0 + CodedOutputStream.computeInt32Size(1, this.id_);
            }
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeMessageSize(2, getApportioned());
            }
            if ((this.bitField0_ & 4) == 4) {
                size2 += CodedOutputStream.computeMessageSize(3, getBackground());
            }
            int size3 = size2 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static Sensor parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (Sensor) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static Sensor parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Sensor) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static Sensor parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (Sensor) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static Sensor parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Sensor) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static Sensor parseFrom(InputStream input) throws IOException {
            return (Sensor) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static Sensor parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Sensor) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Sensor parseDelimitedFrom(InputStream input) throws IOException {
            return (Sensor) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static Sensor parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Sensor) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Sensor parseFrom(CodedInputStream input) throws IOException {
            return (Sensor) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static Sensor parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Sensor) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(Sensor prototype) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<Sensor, Builder> implements SensorOrBuilder {
            private Builder() {
                super(Sensor.DEFAULT_INSTANCE);
            }

            @Override // android.os.UidProto.SensorOrBuilder
            public boolean hasId() {
                return ((Sensor) this.instance).hasId();
            }

            @Override // android.os.UidProto.SensorOrBuilder
            public int getId() {
                return ((Sensor) this.instance).getId();
            }

            public Builder setId(int value) {
                copyOnWrite();
                ((Sensor) this.instance).setId(value);
                return this;
            }

            public Builder clearId() {
                copyOnWrite();
                ((Sensor) this.instance).clearId();
                return this;
            }

            @Override // android.os.UidProto.SensorOrBuilder
            public boolean hasApportioned() {
                return ((Sensor) this.instance).hasApportioned();
            }

            @Override // android.os.UidProto.SensorOrBuilder
            public TimerProto getApportioned() {
                return ((Sensor) this.instance).getApportioned();
            }

            public Builder setApportioned(TimerProto value) {
                copyOnWrite();
                ((Sensor) this.instance).setApportioned((Sensor) value);
                return this;
            }

            public Builder setApportioned(TimerProto.Builder builderForValue) {
                copyOnWrite();
                ((Sensor) this.instance).setApportioned((Sensor) builderForValue);
                return this;
            }

            public Builder mergeApportioned(TimerProto value) {
                copyOnWrite();
                ((Sensor) this.instance).mergeApportioned(value);
                return this;
            }

            public Builder clearApportioned() {
                copyOnWrite();
                ((Sensor) this.instance).clearApportioned();
                return this;
            }

            @Override // android.os.UidProto.SensorOrBuilder
            public boolean hasBackground() {
                return ((Sensor) this.instance).hasBackground();
            }

            @Override // android.os.UidProto.SensorOrBuilder
            public TimerProto getBackground() {
                return ((Sensor) this.instance).getBackground();
            }

            public Builder setBackground(TimerProto value) {
                copyOnWrite();
                ((Sensor) this.instance).setBackground((Sensor) value);
                return this;
            }

            public Builder setBackground(TimerProto.Builder builderForValue) {
                copyOnWrite();
                ((Sensor) this.instance).setBackground((Sensor) builderForValue);
                return this;
            }

            public Builder mergeBackground(TimerProto value) {
                copyOnWrite();
                ((Sensor) this.instance).mergeBackground(value);
                return this;
            }

            public Builder clearBackground() {
                copyOnWrite();
                ((Sensor) this.instance).clearBackground();
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new Sensor();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    Sensor other = (Sensor) arg1;
                    this.id_ = visitor.visitInt(hasId(), this.id_, other.hasId(), other.id_);
                    this.apportioned_ = (TimerProto) visitor.visitMessage(this.apportioned_, other.apportioned_);
                    this.background_ = (TimerProto) visitor.visitMessage(this.background_, other.background_);
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
                                this.id_ = input.readInt32();
                            } else if (tag == 18) {
                                TimerProto.Builder subBuilder = null;
                                if ((this.bitField0_ & 2) == 2) {
                                    subBuilder = (TimerProto.Builder) this.apportioned_.toBuilder();
                                }
                                this.apportioned_ = (TimerProto) input.readMessage(TimerProto.parser(), extensionRegistry);
                                if (subBuilder != null) {
                                    subBuilder.mergeFrom((GeneratedMessageLite) this.apportioned_);
                                    this.apportioned_ = (TimerProto) subBuilder.buildPartial();
                                }
                                this.bitField0_ |= 2;
                            } else if (tag == 26) {
                                TimerProto.Builder subBuilder2 = null;
                                if ((this.bitField0_ & 4) == 4) {
                                    subBuilder2 = (TimerProto.Builder) this.background_.toBuilder();
                                }
                                this.background_ = (TimerProto) input.readMessage(TimerProto.parser(), extensionRegistry);
                                if (subBuilder2 != null) {
                                    subBuilder2.mergeFrom((GeneratedMessageLite) this.background_);
                                    this.background_ = (TimerProto) subBuilder2.buildPartial();
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
                        synchronized (Sensor.class) {
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

        public static Sensor getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<Sensor> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class Sync extends GeneratedMessageLite<Sync, Builder> implements SyncOrBuilder {
        public static final int BACKGROUND_FIELD_NUMBER = 3;
        private static final Sync DEFAULT_INSTANCE = new Sync();
        public static final int NAME_FIELD_NUMBER = 1;
        private static volatile Parser<Sync> PARSER = null;
        public static final int TOTAL_FIELD_NUMBER = 2;
        private TimerProto background_;
        private int bitField0_;
        private String name_ = "";
        private TimerProto total_;

        private Sync() {
        }

        @Override // android.os.UidProto.SyncOrBuilder
        public boolean hasName() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // android.os.UidProto.SyncOrBuilder
        public String getName() {
            return this.name_;
        }

        @Override // android.os.UidProto.SyncOrBuilder
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

        @Override // android.os.UidProto.SyncOrBuilder
        public boolean hasTotal() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // android.os.UidProto.SyncOrBuilder
        public TimerProto getTotal() {
            TimerProto timerProto = this.total_;
            return timerProto == null ? TimerProto.getDefaultInstance() : timerProto;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setTotal(TimerProto value) {
            if (value != null) {
                this.total_ = value;
                this.bitField0_ |= 2;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setTotal(TimerProto.Builder builderForValue) {
            this.total_ = (TimerProto) builderForValue.build();
            this.bitField0_ |= 2;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void mergeTotal(TimerProto value) {
            TimerProto timerProto = this.total_;
            if (timerProto == null || timerProto == TimerProto.getDefaultInstance()) {
                this.total_ = value;
            } else {
                this.total_ = (TimerProto) ((TimerProto.Builder) TimerProto.newBuilder(this.total_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
            }
            this.bitField0_ |= 2;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearTotal() {
            this.total_ = null;
            this.bitField0_ &= -3;
        }

        @Override // android.os.UidProto.SyncOrBuilder
        public boolean hasBackground() {
            return (this.bitField0_ & 4) == 4;
        }

        @Override // android.os.UidProto.SyncOrBuilder
        public TimerProto getBackground() {
            TimerProto timerProto = this.background_;
            return timerProto == null ? TimerProto.getDefaultInstance() : timerProto;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setBackground(TimerProto value) {
            if (value != null) {
                this.background_ = value;
                this.bitField0_ |= 4;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setBackground(TimerProto.Builder builderForValue) {
            this.background_ = (TimerProto) builderForValue.build();
            this.bitField0_ |= 4;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void mergeBackground(TimerProto value) {
            TimerProto timerProto = this.background_;
            if (timerProto == null || timerProto == TimerProto.getDefaultInstance()) {
                this.background_ = value;
            } else {
                this.background_ = (TimerProto) ((TimerProto.Builder) TimerProto.newBuilder(this.background_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
            }
            this.bitField0_ |= 4;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearBackground() {
            this.background_ = null;
            this.bitField0_ &= -5;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                output.writeString(1, getName());
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeMessage(2, getTotal());
            }
            if ((this.bitField0_ & 4) == 4) {
                output.writeMessage(3, getBackground());
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
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeMessageSize(2, getTotal());
            }
            if ((this.bitField0_ & 4) == 4) {
                size2 += CodedOutputStream.computeMessageSize(3, getBackground());
            }
            int size3 = size2 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static Sync parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (Sync) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static Sync parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Sync) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static Sync parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (Sync) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static Sync parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Sync) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static Sync parseFrom(InputStream input) throws IOException {
            return (Sync) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static Sync parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Sync) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Sync parseDelimitedFrom(InputStream input) throws IOException {
            return (Sync) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static Sync parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Sync) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Sync parseFrom(CodedInputStream input) throws IOException {
            return (Sync) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static Sync parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Sync) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(Sync prototype) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<Sync, Builder> implements SyncOrBuilder {
            private Builder() {
                super(Sync.DEFAULT_INSTANCE);
            }

            @Override // android.os.UidProto.SyncOrBuilder
            public boolean hasName() {
                return ((Sync) this.instance).hasName();
            }

            @Override // android.os.UidProto.SyncOrBuilder
            public String getName() {
                return ((Sync) this.instance).getName();
            }

            @Override // android.os.UidProto.SyncOrBuilder
            public ByteString getNameBytes() {
                return ((Sync) this.instance).getNameBytes();
            }

            public Builder setName(String value) {
                copyOnWrite();
                ((Sync) this.instance).setName(value);
                return this;
            }

            public Builder clearName() {
                copyOnWrite();
                ((Sync) this.instance).clearName();
                return this;
            }

            public Builder setNameBytes(ByteString value) {
                copyOnWrite();
                ((Sync) this.instance).setNameBytes(value);
                return this;
            }

            @Override // android.os.UidProto.SyncOrBuilder
            public boolean hasTotal() {
                return ((Sync) this.instance).hasTotal();
            }

            @Override // android.os.UidProto.SyncOrBuilder
            public TimerProto getTotal() {
                return ((Sync) this.instance).getTotal();
            }

            public Builder setTotal(TimerProto value) {
                copyOnWrite();
                ((Sync) this.instance).setTotal((Sync) value);
                return this;
            }

            public Builder setTotal(TimerProto.Builder builderForValue) {
                copyOnWrite();
                ((Sync) this.instance).setTotal((Sync) builderForValue);
                return this;
            }

            public Builder mergeTotal(TimerProto value) {
                copyOnWrite();
                ((Sync) this.instance).mergeTotal(value);
                return this;
            }

            public Builder clearTotal() {
                copyOnWrite();
                ((Sync) this.instance).clearTotal();
                return this;
            }

            @Override // android.os.UidProto.SyncOrBuilder
            public boolean hasBackground() {
                return ((Sync) this.instance).hasBackground();
            }

            @Override // android.os.UidProto.SyncOrBuilder
            public TimerProto getBackground() {
                return ((Sync) this.instance).getBackground();
            }

            public Builder setBackground(TimerProto value) {
                copyOnWrite();
                ((Sync) this.instance).setBackground((Sync) value);
                return this;
            }

            public Builder setBackground(TimerProto.Builder builderForValue) {
                copyOnWrite();
                ((Sync) this.instance).setBackground((Sync) builderForValue);
                return this;
            }

            public Builder mergeBackground(TimerProto value) {
                copyOnWrite();
                ((Sync) this.instance).mergeBackground(value);
                return this;
            }

            public Builder clearBackground() {
                copyOnWrite();
                ((Sync) this.instance).clearBackground();
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new Sync();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    Sync other = (Sync) arg1;
                    this.name_ = visitor.visitString(hasName(), this.name_, other.hasName(), other.name_);
                    this.total_ = (TimerProto) visitor.visitMessage(this.total_, other.total_);
                    this.background_ = (TimerProto) visitor.visitMessage(this.background_, other.background_);
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
                                TimerProto.Builder subBuilder = null;
                                if ((this.bitField0_ & 2) == 2) {
                                    subBuilder = (TimerProto.Builder) this.total_.toBuilder();
                                }
                                this.total_ = (TimerProto) input.readMessage(TimerProto.parser(), extensionRegistry);
                                if (subBuilder != null) {
                                    subBuilder.mergeFrom((GeneratedMessageLite) this.total_);
                                    this.total_ = (TimerProto) subBuilder.buildPartial();
                                }
                                this.bitField0_ |= 2;
                            } else if (tag == 26) {
                                TimerProto.Builder subBuilder2 = null;
                                if ((this.bitField0_ & 4) == 4) {
                                    subBuilder2 = (TimerProto.Builder) this.background_.toBuilder();
                                }
                                this.background_ = (TimerProto) input.readMessage(TimerProto.parser(), extensionRegistry);
                                if (subBuilder2 != null) {
                                    subBuilder2.mergeFrom((GeneratedMessageLite) this.background_);
                                    this.background_ = (TimerProto) subBuilder2.buildPartial();
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
                        synchronized (Sync.class) {
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

        public static Sync getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<Sync> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class UserActivity extends GeneratedMessageLite<UserActivity, Builder> implements UserActivityOrBuilder {
        public static final int COUNT_FIELD_NUMBER = 2;
        private static final UserActivity DEFAULT_INSTANCE = new UserActivity();
        public static final int NAME_FIELD_NUMBER = 1;
        private static volatile Parser<UserActivity> PARSER;
        private int bitField0_;
        private int count_ = 0;
        private int name_ = 0;

        private UserActivity() {
        }

        @Override // android.os.UidProto.UserActivityOrBuilder
        public boolean hasName() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // android.os.UidProto.UserActivityOrBuilder
        public PowerManagerProto.UserActivityEvent getName() {
            PowerManagerProto.UserActivityEvent result = PowerManagerProto.UserActivityEvent.forNumber(this.name_);
            return result == null ? PowerManagerProto.UserActivityEvent.USER_ACTIVITY_EVENT_OTHER : result;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setName(PowerManagerProto.UserActivityEvent value) {
            if (value != null) {
                this.bitField0_ |= 1;
                this.name_ = value.getNumber();
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearName() {
            this.bitField0_ &= -2;
            this.name_ = 0;
        }

        @Override // android.os.UidProto.UserActivityOrBuilder
        public boolean hasCount() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // android.os.UidProto.UserActivityOrBuilder
        public int getCount() {
            return this.count_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setCount(int value) {
            this.bitField0_ |= 2;
            this.count_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearCount() {
            this.bitField0_ &= -3;
            this.count_ = 0;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                output.writeEnum(1, this.name_);
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeInt32(2, this.count_);
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
                size2 = 0 + CodedOutputStream.computeEnumSize(1, this.name_);
            }
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeInt32Size(2, this.count_);
            }
            int size3 = size2 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static UserActivity parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (UserActivity) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static UserActivity parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (UserActivity) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static UserActivity parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (UserActivity) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static UserActivity parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (UserActivity) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static UserActivity parseFrom(InputStream input) throws IOException {
            return (UserActivity) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static UserActivity parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (UserActivity) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static UserActivity parseDelimitedFrom(InputStream input) throws IOException {
            return (UserActivity) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static UserActivity parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (UserActivity) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static UserActivity parseFrom(CodedInputStream input) throws IOException {
            return (UserActivity) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static UserActivity parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (UserActivity) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(UserActivity prototype) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<UserActivity, Builder> implements UserActivityOrBuilder {
            private Builder() {
                super(UserActivity.DEFAULT_INSTANCE);
            }

            @Override // android.os.UidProto.UserActivityOrBuilder
            public boolean hasName() {
                return ((UserActivity) this.instance).hasName();
            }

            @Override // android.os.UidProto.UserActivityOrBuilder
            public PowerManagerProto.UserActivityEvent getName() {
                return ((UserActivity) this.instance).getName();
            }

            public Builder setName(PowerManagerProto.UserActivityEvent value) {
                copyOnWrite();
                ((UserActivity) this.instance).setName(value);
                return this;
            }

            public Builder clearName() {
                copyOnWrite();
                ((UserActivity) this.instance).clearName();
                return this;
            }

            @Override // android.os.UidProto.UserActivityOrBuilder
            public boolean hasCount() {
                return ((UserActivity) this.instance).hasCount();
            }

            @Override // android.os.UidProto.UserActivityOrBuilder
            public int getCount() {
                return ((UserActivity) this.instance).getCount();
            }

            public Builder setCount(int value) {
                copyOnWrite();
                ((UserActivity) this.instance).setCount(value);
                return this;
            }

            public Builder clearCount() {
                copyOnWrite();
                ((UserActivity) this.instance).clearCount();
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new UserActivity();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    UserActivity other = (UserActivity) arg1;
                    this.name_ = visitor.visitInt(hasName(), this.name_, other.hasName(), other.name_);
                    this.count_ = visitor.visitInt(hasCount(), this.count_, other.hasCount(), other.count_);
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
                                if (PowerManagerProto.UserActivityEvent.forNumber(rawValue) == null) {
                                    super.mergeVarintField(1, rawValue);
                                } else {
                                    this.bitField0_ = 1 | this.bitField0_;
                                    this.name_ = rawValue;
                                }
                            } else if (tag == 16) {
                                this.bitField0_ |= 2;
                                this.count_ = input.readInt32();
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
                        synchronized (UserActivity.class) {
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

        public static UserActivity getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<UserActivity> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class AggregatedWakelock extends GeneratedMessageLite<AggregatedWakelock, Builder> implements AggregatedWakelockOrBuilder {
        public static final int BACKGROUND_PARTIAL_DURATION_MS_FIELD_NUMBER = 2;
        private static final AggregatedWakelock DEFAULT_INSTANCE = new AggregatedWakelock();
        private static volatile Parser<AggregatedWakelock> PARSER = null;
        public static final int PARTIAL_DURATION_MS_FIELD_NUMBER = 1;
        private long backgroundPartialDurationMs_ = 0;
        private int bitField0_;
        private long partialDurationMs_ = 0;

        private AggregatedWakelock() {
        }

        @Override // android.os.UidProto.AggregatedWakelockOrBuilder
        public boolean hasPartialDurationMs() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // android.os.UidProto.AggregatedWakelockOrBuilder
        public long getPartialDurationMs() {
            return this.partialDurationMs_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setPartialDurationMs(long value) {
            this.bitField0_ |= 1;
            this.partialDurationMs_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearPartialDurationMs() {
            this.bitField0_ &= -2;
            this.partialDurationMs_ = 0;
        }

        @Override // android.os.UidProto.AggregatedWakelockOrBuilder
        public boolean hasBackgroundPartialDurationMs() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // android.os.UidProto.AggregatedWakelockOrBuilder
        public long getBackgroundPartialDurationMs() {
            return this.backgroundPartialDurationMs_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setBackgroundPartialDurationMs(long value) {
            this.bitField0_ |= 2;
            this.backgroundPartialDurationMs_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearBackgroundPartialDurationMs() {
            this.bitField0_ &= -3;
            this.backgroundPartialDurationMs_ = 0;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                output.writeInt64(1, this.partialDurationMs_);
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeInt64(2, this.backgroundPartialDurationMs_);
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
                size2 = 0 + CodedOutputStream.computeInt64Size(1, this.partialDurationMs_);
            }
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeInt64Size(2, this.backgroundPartialDurationMs_);
            }
            int size3 = size2 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static AggregatedWakelock parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (AggregatedWakelock) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static AggregatedWakelock parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (AggregatedWakelock) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static AggregatedWakelock parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (AggregatedWakelock) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static AggregatedWakelock parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (AggregatedWakelock) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static AggregatedWakelock parseFrom(InputStream input) throws IOException {
            return (AggregatedWakelock) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static AggregatedWakelock parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (AggregatedWakelock) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static AggregatedWakelock parseDelimitedFrom(InputStream input) throws IOException {
            return (AggregatedWakelock) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static AggregatedWakelock parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (AggregatedWakelock) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static AggregatedWakelock parseFrom(CodedInputStream input) throws IOException {
            return (AggregatedWakelock) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static AggregatedWakelock parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (AggregatedWakelock) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(AggregatedWakelock prototype) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<AggregatedWakelock, Builder> implements AggregatedWakelockOrBuilder {
            private Builder() {
                super(AggregatedWakelock.DEFAULT_INSTANCE);
            }

            @Override // android.os.UidProto.AggregatedWakelockOrBuilder
            public boolean hasPartialDurationMs() {
                return ((AggregatedWakelock) this.instance).hasPartialDurationMs();
            }

            @Override // android.os.UidProto.AggregatedWakelockOrBuilder
            public long getPartialDurationMs() {
                return ((AggregatedWakelock) this.instance).getPartialDurationMs();
            }

            public Builder setPartialDurationMs(long value) {
                copyOnWrite();
                ((AggregatedWakelock) this.instance).setPartialDurationMs(value);
                return this;
            }

            public Builder clearPartialDurationMs() {
                copyOnWrite();
                ((AggregatedWakelock) this.instance).clearPartialDurationMs();
                return this;
            }

            @Override // android.os.UidProto.AggregatedWakelockOrBuilder
            public boolean hasBackgroundPartialDurationMs() {
                return ((AggregatedWakelock) this.instance).hasBackgroundPartialDurationMs();
            }

            @Override // android.os.UidProto.AggregatedWakelockOrBuilder
            public long getBackgroundPartialDurationMs() {
                return ((AggregatedWakelock) this.instance).getBackgroundPartialDurationMs();
            }

            public Builder setBackgroundPartialDurationMs(long value) {
                copyOnWrite();
                ((AggregatedWakelock) this.instance).setBackgroundPartialDurationMs(value);
                return this;
            }

            public Builder clearBackgroundPartialDurationMs() {
                copyOnWrite();
                ((AggregatedWakelock) this.instance).clearBackgroundPartialDurationMs();
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new AggregatedWakelock();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    AggregatedWakelock other = (AggregatedWakelock) arg1;
                    this.partialDurationMs_ = visitor.visitLong(hasPartialDurationMs(), this.partialDurationMs_, other.hasPartialDurationMs(), other.partialDurationMs_);
                    this.backgroundPartialDurationMs_ = visitor.visitLong(hasBackgroundPartialDurationMs(), this.backgroundPartialDurationMs_, other.hasBackgroundPartialDurationMs(), other.backgroundPartialDurationMs_);
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
                                this.partialDurationMs_ = input.readInt64();
                            } else if (tag == 16) {
                                this.bitField0_ |= 2;
                                this.backgroundPartialDurationMs_ = input.readInt64();
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
                        synchronized (AggregatedWakelock.class) {
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

        public static AggregatedWakelock getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<AggregatedWakelock> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class Wakelock extends GeneratedMessageLite<Wakelock, Builder> implements WakelockOrBuilder {
        public static final int BACKGROUND_PARTIAL_FIELD_NUMBER = 4;
        private static final Wakelock DEFAULT_INSTANCE = new Wakelock();
        public static final int FULL_FIELD_NUMBER = 2;
        public static final int NAME_FIELD_NUMBER = 1;
        private static volatile Parser<Wakelock> PARSER = null;
        public static final int PARTIAL_FIELD_NUMBER = 3;
        public static final int WINDOW_FIELD_NUMBER = 5;
        private TimerProto backgroundPartial_;
        private int bitField0_;
        private TimerProto full_;
        private String name_ = "";
        private TimerProto partial_;
        private TimerProto window_;

        private Wakelock() {
        }

        @Override // android.os.UidProto.WakelockOrBuilder
        public boolean hasName() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // android.os.UidProto.WakelockOrBuilder
        public String getName() {
            return this.name_;
        }

        @Override // android.os.UidProto.WakelockOrBuilder
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

        @Override // android.os.UidProto.WakelockOrBuilder
        public boolean hasFull() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // android.os.UidProto.WakelockOrBuilder
        public TimerProto getFull() {
            TimerProto timerProto = this.full_;
            return timerProto == null ? TimerProto.getDefaultInstance() : timerProto;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setFull(TimerProto value) {
            if (value != null) {
                this.full_ = value;
                this.bitField0_ |= 2;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setFull(TimerProto.Builder builderForValue) {
            this.full_ = (TimerProto) builderForValue.build();
            this.bitField0_ |= 2;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void mergeFull(TimerProto value) {
            TimerProto timerProto = this.full_;
            if (timerProto == null || timerProto == TimerProto.getDefaultInstance()) {
                this.full_ = value;
            } else {
                this.full_ = (TimerProto) ((TimerProto.Builder) TimerProto.newBuilder(this.full_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
            }
            this.bitField0_ |= 2;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearFull() {
            this.full_ = null;
            this.bitField0_ &= -3;
        }

        @Override // android.os.UidProto.WakelockOrBuilder
        public boolean hasPartial() {
            return (this.bitField0_ & 4) == 4;
        }

        @Override // android.os.UidProto.WakelockOrBuilder
        public TimerProto getPartial() {
            TimerProto timerProto = this.partial_;
            return timerProto == null ? TimerProto.getDefaultInstance() : timerProto;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setPartial(TimerProto value) {
            if (value != null) {
                this.partial_ = value;
                this.bitField0_ |= 4;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setPartial(TimerProto.Builder builderForValue) {
            this.partial_ = (TimerProto) builderForValue.build();
            this.bitField0_ |= 4;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void mergePartial(TimerProto value) {
            TimerProto timerProto = this.partial_;
            if (timerProto == null || timerProto == TimerProto.getDefaultInstance()) {
                this.partial_ = value;
            } else {
                this.partial_ = (TimerProto) ((TimerProto.Builder) TimerProto.newBuilder(this.partial_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
            }
            this.bitField0_ |= 4;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearPartial() {
            this.partial_ = null;
            this.bitField0_ &= -5;
        }

        @Override // android.os.UidProto.WakelockOrBuilder
        public boolean hasBackgroundPartial() {
            return (this.bitField0_ & 8) == 8;
        }

        @Override // android.os.UidProto.WakelockOrBuilder
        public TimerProto getBackgroundPartial() {
            TimerProto timerProto = this.backgroundPartial_;
            return timerProto == null ? TimerProto.getDefaultInstance() : timerProto;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setBackgroundPartial(TimerProto value) {
            if (value != null) {
                this.backgroundPartial_ = value;
                this.bitField0_ |= 8;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setBackgroundPartial(TimerProto.Builder builderForValue) {
            this.backgroundPartial_ = (TimerProto) builderForValue.build();
            this.bitField0_ |= 8;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void mergeBackgroundPartial(TimerProto value) {
            TimerProto timerProto = this.backgroundPartial_;
            if (timerProto == null || timerProto == TimerProto.getDefaultInstance()) {
                this.backgroundPartial_ = value;
            } else {
                this.backgroundPartial_ = (TimerProto) ((TimerProto.Builder) TimerProto.newBuilder(this.backgroundPartial_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
            }
            this.bitField0_ |= 8;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearBackgroundPartial() {
            this.backgroundPartial_ = null;
            this.bitField0_ &= -9;
        }

        @Override // android.os.UidProto.WakelockOrBuilder
        public boolean hasWindow() {
            return (this.bitField0_ & 16) == 16;
        }

        @Override // android.os.UidProto.WakelockOrBuilder
        public TimerProto getWindow() {
            TimerProto timerProto = this.window_;
            return timerProto == null ? TimerProto.getDefaultInstance() : timerProto;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setWindow(TimerProto value) {
            if (value != null) {
                this.window_ = value;
                this.bitField0_ |= 16;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setWindow(TimerProto.Builder builderForValue) {
            this.window_ = (TimerProto) builderForValue.build();
            this.bitField0_ |= 16;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void mergeWindow(TimerProto value) {
            TimerProto timerProto = this.window_;
            if (timerProto == null || timerProto == TimerProto.getDefaultInstance()) {
                this.window_ = value;
            } else {
                this.window_ = (TimerProto) ((TimerProto.Builder) TimerProto.newBuilder(this.window_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
            }
            this.bitField0_ |= 16;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearWindow() {
            this.window_ = null;
            this.bitField0_ &= -17;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                output.writeString(1, getName());
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeMessage(2, getFull());
            }
            if ((this.bitField0_ & 4) == 4) {
                output.writeMessage(3, getPartial());
            }
            if ((this.bitField0_ & 8) == 8) {
                output.writeMessage(4, getBackgroundPartial());
            }
            if ((this.bitField0_ & 16) == 16) {
                output.writeMessage(5, getWindow());
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
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeMessageSize(2, getFull());
            }
            if ((this.bitField0_ & 4) == 4) {
                size2 += CodedOutputStream.computeMessageSize(3, getPartial());
            }
            if ((this.bitField0_ & 8) == 8) {
                size2 += CodedOutputStream.computeMessageSize(4, getBackgroundPartial());
            }
            if ((this.bitField0_ & 16) == 16) {
                size2 += CodedOutputStream.computeMessageSize(5, getWindow());
            }
            int size3 = size2 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static Wakelock parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (Wakelock) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static Wakelock parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Wakelock) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static Wakelock parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (Wakelock) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static Wakelock parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Wakelock) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static Wakelock parseFrom(InputStream input) throws IOException {
            return (Wakelock) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static Wakelock parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Wakelock) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Wakelock parseDelimitedFrom(InputStream input) throws IOException {
            return (Wakelock) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static Wakelock parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Wakelock) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Wakelock parseFrom(CodedInputStream input) throws IOException {
            return (Wakelock) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static Wakelock parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Wakelock) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(Wakelock prototype) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<Wakelock, Builder> implements WakelockOrBuilder {
            private Builder() {
                super(Wakelock.DEFAULT_INSTANCE);
            }

            @Override // android.os.UidProto.WakelockOrBuilder
            public boolean hasName() {
                return ((Wakelock) this.instance).hasName();
            }

            @Override // android.os.UidProto.WakelockOrBuilder
            public String getName() {
                return ((Wakelock) this.instance).getName();
            }

            @Override // android.os.UidProto.WakelockOrBuilder
            public ByteString getNameBytes() {
                return ((Wakelock) this.instance).getNameBytes();
            }

            public Builder setName(String value) {
                copyOnWrite();
                ((Wakelock) this.instance).setName(value);
                return this;
            }

            public Builder clearName() {
                copyOnWrite();
                ((Wakelock) this.instance).clearName();
                return this;
            }

            public Builder setNameBytes(ByteString value) {
                copyOnWrite();
                ((Wakelock) this.instance).setNameBytes(value);
                return this;
            }

            @Override // android.os.UidProto.WakelockOrBuilder
            public boolean hasFull() {
                return ((Wakelock) this.instance).hasFull();
            }

            @Override // android.os.UidProto.WakelockOrBuilder
            public TimerProto getFull() {
                return ((Wakelock) this.instance).getFull();
            }

            public Builder setFull(TimerProto value) {
                copyOnWrite();
                ((Wakelock) this.instance).setFull((Wakelock) value);
                return this;
            }

            public Builder setFull(TimerProto.Builder builderForValue) {
                copyOnWrite();
                ((Wakelock) this.instance).setFull((Wakelock) builderForValue);
                return this;
            }

            public Builder mergeFull(TimerProto value) {
                copyOnWrite();
                ((Wakelock) this.instance).mergeFull(value);
                return this;
            }

            public Builder clearFull() {
                copyOnWrite();
                ((Wakelock) this.instance).clearFull();
                return this;
            }

            @Override // android.os.UidProto.WakelockOrBuilder
            public boolean hasPartial() {
                return ((Wakelock) this.instance).hasPartial();
            }

            @Override // android.os.UidProto.WakelockOrBuilder
            public TimerProto getPartial() {
                return ((Wakelock) this.instance).getPartial();
            }

            public Builder setPartial(TimerProto value) {
                copyOnWrite();
                ((Wakelock) this.instance).setPartial((Wakelock) value);
                return this;
            }

            public Builder setPartial(TimerProto.Builder builderForValue) {
                copyOnWrite();
                ((Wakelock) this.instance).setPartial((Wakelock) builderForValue);
                return this;
            }

            public Builder mergePartial(TimerProto value) {
                copyOnWrite();
                ((Wakelock) this.instance).mergePartial(value);
                return this;
            }

            public Builder clearPartial() {
                copyOnWrite();
                ((Wakelock) this.instance).clearPartial();
                return this;
            }

            @Override // android.os.UidProto.WakelockOrBuilder
            public boolean hasBackgroundPartial() {
                return ((Wakelock) this.instance).hasBackgroundPartial();
            }

            @Override // android.os.UidProto.WakelockOrBuilder
            public TimerProto getBackgroundPartial() {
                return ((Wakelock) this.instance).getBackgroundPartial();
            }

            public Builder setBackgroundPartial(TimerProto value) {
                copyOnWrite();
                ((Wakelock) this.instance).setBackgroundPartial((Wakelock) value);
                return this;
            }

            public Builder setBackgroundPartial(TimerProto.Builder builderForValue) {
                copyOnWrite();
                ((Wakelock) this.instance).setBackgroundPartial((Wakelock) builderForValue);
                return this;
            }

            public Builder mergeBackgroundPartial(TimerProto value) {
                copyOnWrite();
                ((Wakelock) this.instance).mergeBackgroundPartial(value);
                return this;
            }

            public Builder clearBackgroundPartial() {
                copyOnWrite();
                ((Wakelock) this.instance).clearBackgroundPartial();
                return this;
            }

            @Override // android.os.UidProto.WakelockOrBuilder
            public boolean hasWindow() {
                return ((Wakelock) this.instance).hasWindow();
            }

            @Override // android.os.UidProto.WakelockOrBuilder
            public TimerProto getWindow() {
                return ((Wakelock) this.instance).getWindow();
            }

            public Builder setWindow(TimerProto value) {
                copyOnWrite();
                ((Wakelock) this.instance).setWindow((Wakelock) value);
                return this;
            }

            public Builder setWindow(TimerProto.Builder builderForValue) {
                copyOnWrite();
                ((Wakelock) this.instance).setWindow((Wakelock) builderForValue);
                return this;
            }

            public Builder mergeWindow(TimerProto value) {
                copyOnWrite();
                ((Wakelock) this.instance).mergeWindow(value);
                return this;
            }

            public Builder clearWindow() {
                copyOnWrite();
                ((Wakelock) this.instance).clearWindow();
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new Wakelock();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    Wakelock other = (Wakelock) arg1;
                    this.name_ = visitor.visitString(hasName(), this.name_, other.hasName(), other.name_);
                    this.full_ = (TimerProto) visitor.visitMessage(this.full_, other.full_);
                    this.partial_ = (TimerProto) visitor.visitMessage(this.partial_, other.partial_);
                    this.backgroundPartial_ = (TimerProto) visitor.visitMessage(this.backgroundPartial_, other.backgroundPartial_);
                    this.window_ = (TimerProto) visitor.visitMessage(this.window_, other.window_);
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
                                TimerProto.Builder subBuilder = null;
                                if ((this.bitField0_ & 2) == 2) {
                                    subBuilder = (TimerProto.Builder) this.full_.toBuilder();
                                }
                                this.full_ = (TimerProto) input.readMessage(TimerProto.parser(), extensionRegistry);
                                if (subBuilder != null) {
                                    subBuilder.mergeFrom((GeneratedMessageLite) this.full_);
                                    this.full_ = (TimerProto) subBuilder.buildPartial();
                                }
                                this.bitField0_ |= 2;
                            } else if (tag == 26) {
                                TimerProto.Builder subBuilder2 = null;
                                if ((this.bitField0_ & 4) == 4) {
                                    subBuilder2 = (TimerProto.Builder) this.partial_.toBuilder();
                                }
                                this.partial_ = (TimerProto) input.readMessage(TimerProto.parser(), extensionRegistry);
                                if (subBuilder2 != null) {
                                    subBuilder2.mergeFrom((GeneratedMessageLite) this.partial_);
                                    this.partial_ = (TimerProto) subBuilder2.buildPartial();
                                }
                                this.bitField0_ |= 4;
                            } else if (tag == 34) {
                                TimerProto.Builder subBuilder3 = null;
                                if ((this.bitField0_ & 8) == 8) {
                                    subBuilder3 = (TimerProto.Builder) this.backgroundPartial_.toBuilder();
                                }
                                this.backgroundPartial_ = (TimerProto) input.readMessage(TimerProto.parser(), extensionRegistry);
                                if (subBuilder3 != null) {
                                    subBuilder3.mergeFrom((GeneratedMessageLite) this.backgroundPartial_);
                                    this.backgroundPartial_ = (TimerProto) subBuilder3.buildPartial();
                                }
                                this.bitField0_ |= 8;
                            } else if (tag == 42) {
                                TimerProto.Builder subBuilder4 = null;
                                if ((this.bitField0_ & 16) == 16) {
                                    subBuilder4 = (TimerProto.Builder) this.window_.toBuilder();
                                }
                                this.window_ = (TimerProto) input.readMessage(TimerProto.parser(), extensionRegistry);
                                if (subBuilder4 != null) {
                                    subBuilder4.mergeFrom((GeneratedMessageLite) this.window_);
                                    this.window_ = (TimerProto) subBuilder4.buildPartial();
                                }
                                this.bitField0_ |= 16;
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
                        synchronized (Wakelock.class) {
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

        public static Wakelock getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<Wakelock> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class WakeupAlarm extends GeneratedMessageLite<WakeupAlarm, Builder> implements WakeupAlarmOrBuilder {
        public static final int COUNT_FIELD_NUMBER = 2;
        private static final WakeupAlarm DEFAULT_INSTANCE = new WakeupAlarm();
        public static final int NAME_FIELD_NUMBER = 1;
        private static volatile Parser<WakeupAlarm> PARSER;
        private int bitField0_;
        private int count_ = 0;
        private String name_ = "";

        private WakeupAlarm() {
        }

        @Override // android.os.UidProto.WakeupAlarmOrBuilder
        public boolean hasName() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // android.os.UidProto.WakeupAlarmOrBuilder
        public String getName() {
            return this.name_;
        }

        @Override // android.os.UidProto.WakeupAlarmOrBuilder
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

        @Override // android.os.UidProto.WakeupAlarmOrBuilder
        public boolean hasCount() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // android.os.UidProto.WakeupAlarmOrBuilder
        public int getCount() {
            return this.count_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setCount(int value) {
            this.bitField0_ |= 2;
            this.count_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearCount() {
            this.bitField0_ &= -3;
            this.count_ = 0;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                output.writeString(1, getName());
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeInt32(2, this.count_);
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
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeInt32Size(2, this.count_);
            }
            int size3 = size2 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static WakeupAlarm parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (WakeupAlarm) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static WakeupAlarm parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (WakeupAlarm) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static WakeupAlarm parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (WakeupAlarm) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static WakeupAlarm parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (WakeupAlarm) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static WakeupAlarm parseFrom(InputStream input) throws IOException {
            return (WakeupAlarm) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static WakeupAlarm parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (WakeupAlarm) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static WakeupAlarm parseDelimitedFrom(InputStream input) throws IOException {
            return (WakeupAlarm) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static WakeupAlarm parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (WakeupAlarm) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static WakeupAlarm parseFrom(CodedInputStream input) throws IOException {
            return (WakeupAlarm) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static WakeupAlarm parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (WakeupAlarm) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(WakeupAlarm prototype) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<WakeupAlarm, Builder> implements WakeupAlarmOrBuilder {
            private Builder() {
                super(WakeupAlarm.DEFAULT_INSTANCE);
            }

            @Override // android.os.UidProto.WakeupAlarmOrBuilder
            public boolean hasName() {
                return ((WakeupAlarm) this.instance).hasName();
            }

            @Override // android.os.UidProto.WakeupAlarmOrBuilder
            public String getName() {
                return ((WakeupAlarm) this.instance).getName();
            }

            @Override // android.os.UidProto.WakeupAlarmOrBuilder
            public ByteString getNameBytes() {
                return ((WakeupAlarm) this.instance).getNameBytes();
            }

            public Builder setName(String value) {
                copyOnWrite();
                ((WakeupAlarm) this.instance).setName(value);
                return this;
            }

            public Builder clearName() {
                copyOnWrite();
                ((WakeupAlarm) this.instance).clearName();
                return this;
            }

            public Builder setNameBytes(ByteString value) {
                copyOnWrite();
                ((WakeupAlarm) this.instance).setNameBytes(value);
                return this;
            }

            @Override // android.os.UidProto.WakeupAlarmOrBuilder
            public boolean hasCount() {
                return ((WakeupAlarm) this.instance).hasCount();
            }

            @Override // android.os.UidProto.WakeupAlarmOrBuilder
            public int getCount() {
                return ((WakeupAlarm) this.instance).getCount();
            }

            public Builder setCount(int value) {
                copyOnWrite();
                ((WakeupAlarm) this.instance).setCount(value);
                return this;
            }

            public Builder clearCount() {
                copyOnWrite();
                ((WakeupAlarm) this.instance).clearCount();
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new WakeupAlarm();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    WakeupAlarm other = (WakeupAlarm) arg1;
                    this.name_ = visitor.visitString(hasName(), this.name_, other.hasName(), other.name_);
                    this.count_ = visitor.visitInt(hasCount(), this.count_, other.hasCount(), other.count_);
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
                                this.name_ = s;
                            } else if (tag == 16) {
                                this.bitField0_ |= 2;
                                this.count_ = input.readInt32();
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
                        synchronized (WakeupAlarm.class) {
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

        public static WakeupAlarm getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<WakeupAlarm> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class Wifi extends GeneratedMessageLite<Wifi, Builder> implements WifiOrBuilder {
        public static final int APPORTIONED_SCAN_FIELD_NUMBER = 3;
        public static final int BACKGROUND_SCAN_FIELD_NUMBER = 4;
        private static final Wifi DEFAULT_INSTANCE = new Wifi();
        public static final int FULL_WIFI_LOCK_DURATION_MS_FIELD_NUMBER = 1;
        private static volatile Parser<Wifi> PARSER = null;
        public static final int RUNNING_DURATION_MS_FIELD_NUMBER = 2;
        private TimerProto apportionedScan_;
        private TimerProto backgroundScan_;
        private int bitField0_;
        private long fullWifiLockDurationMs_ = 0;
        private long runningDurationMs_ = 0;

        private Wifi() {
        }

        @Override // android.os.UidProto.WifiOrBuilder
        public boolean hasFullWifiLockDurationMs() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // android.os.UidProto.WifiOrBuilder
        public long getFullWifiLockDurationMs() {
            return this.fullWifiLockDurationMs_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setFullWifiLockDurationMs(long value) {
            this.bitField0_ |= 1;
            this.fullWifiLockDurationMs_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearFullWifiLockDurationMs() {
            this.bitField0_ &= -2;
            this.fullWifiLockDurationMs_ = 0;
        }

        @Override // android.os.UidProto.WifiOrBuilder
        public boolean hasRunningDurationMs() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // android.os.UidProto.WifiOrBuilder
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

        @Override // android.os.UidProto.WifiOrBuilder
        public boolean hasApportionedScan() {
            return (this.bitField0_ & 4) == 4;
        }

        @Override // android.os.UidProto.WifiOrBuilder
        public TimerProto getApportionedScan() {
            TimerProto timerProto = this.apportionedScan_;
            return timerProto == null ? TimerProto.getDefaultInstance() : timerProto;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setApportionedScan(TimerProto value) {
            if (value != null) {
                this.apportionedScan_ = value;
                this.bitField0_ |= 4;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setApportionedScan(TimerProto.Builder builderForValue) {
            this.apportionedScan_ = (TimerProto) builderForValue.build();
            this.bitField0_ |= 4;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void mergeApportionedScan(TimerProto value) {
            TimerProto timerProto = this.apportionedScan_;
            if (timerProto == null || timerProto == TimerProto.getDefaultInstance()) {
                this.apportionedScan_ = value;
            } else {
                this.apportionedScan_ = (TimerProto) ((TimerProto.Builder) TimerProto.newBuilder(this.apportionedScan_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
            }
            this.bitField0_ |= 4;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearApportionedScan() {
            this.apportionedScan_ = null;
            this.bitField0_ &= -5;
        }

        @Override // android.os.UidProto.WifiOrBuilder
        public boolean hasBackgroundScan() {
            return (this.bitField0_ & 8) == 8;
        }

        @Override // android.os.UidProto.WifiOrBuilder
        public TimerProto getBackgroundScan() {
            TimerProto timerProto = this.backgroundScan_;
            return timerProto == null ? TimerProto.getDefaultInstance() : timerProto;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setBackgroundScan(TimerProto value) {
            if (value != null) {
                this.backgroundScan_ = value;
                this.bitField0_ |= 8;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setBackgroundScan(TimerProto.Builder builderForValue) {
            this.backgroundScan_ = (TimerProto) builderForValue.build();
            this.bitField0_ |= 8;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void mergeBackgroundScan(TimerProto value) {
            TimerProto timerProto = this.backgroundScan_;
            if (timerProto == null || timerProto == TimerProto.getDefaultInstance()) {
                this.backgroundScan_ = value;
            } else {
                this.backgroundScan_ = (TimerProto) ((TimerProto.Builder) TimerProto.newBuilder(this.backgroundScan_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
            }
            this.bitField0_ |= 8;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearBackgroundScan() {
            this.backgroundScan_ = null;
            this.bitField0_ &= -9;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                output.writeInt64(1, this.fullWifiLockDurationMs_);
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeInt64(2, this.runningDurationMs_);
            }
            if ((this.bitField0_ & 4) == 4) {
                output.writeMessage(3, getApportionedScan());
            }
            if ((this.bitField0_ & 8) == 8) {
                output.writeMessage(4, getBackgroundScan());
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
                size2 = 0 + CodedOutputStream.computeInt64Size(1, this.fullWifiLockDurationMs_);
            }
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeInt64Size(2, this.runningDurationMs_);
            }
            if ((this.bitField0_ & 4) == 4) {
                size2 += CodedOutputStream.computeMessageSize(3, getApportionedScan());
            }
            if ((this.bitField0_ & 8) == 8) {
                size2 += CodedOutputStream.computeMessageSize(4, getBackgroundScan());
            }
            int size3 = size2 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static Wifi parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (Wifi) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static Wifi parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Wifi) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static Wifi parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (Wifi) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static Wifi parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Wifi) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static Wifi parseFrom(InputStream input) throws IOException {
            return (Wifi) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static Wifi parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Wifi) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Wifi parseDelimitedFrom(InputStream input) throws IOException {
            return (Wifi) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static Wifi parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Wifi) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Wifi parseFrom(CodedInputStream input) throws IOException {
            return (Wifi) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static Wifi parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Wifi) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(Wifi prototype) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<Wifi, Builder> implements WifiOrBuilder {
            private Builder() {
                super(Wifi.DEFAULT_INSTANCE);
            }

            @Override // android.os.UidProto.WifiOrBuilder
            public boolean hasFullWifiLockDurationMs() {
                return ((Wifi) this.instance).hasFullWifiLockDurationMs();
            }

            @Override // android.os.UidProto.WifiOrBuilder
            public long getFullWifiLockDurationMs() {
                return ((Wifi) this.instance).getFullWifiLockDurationMs();
            }

            public Builder setFullWifiLockDurationMs(long value) {
                copyOnWrite();
                ((Wifi) this.instance).setFullWifiLockDurationMs(value);
                return this;
            }

            public Builder clearFullWifiLockDurationMs() {
                copyOnWrite();
                ((Wifi) this.instance).clearFullWifiLockDurationMs();
                return this;
            }

            @Override // android.os.UidProto.WifiOrBuilder
            public boolean hasRunningDurationMs() {
                return ((Wifi) this.instance).hasRunningDurationMs();
            }

            @Override // android.os.UidProto.WifiOrBuilder
            public long getRunningDurationMs() {
                return ((Wifi) this.instance).getRunningDurationMs();
            }

            public Builder setRunningDurationMs(long value) {
                copyOnWrite();
                ((Wifi) this.instance).setRunningDurationMs(value);
                return this;
            }

            public Builder clearRunningDurationMs() {
                copyOnWrite();
                ((Wifi) this.instance).clearRunningDurationMs();
                return this;
            }

            @Override // android.os.UidProto.WifiOrBuilder
            public boolean hasApportionedScan() {
                return ((Wifi) this.instance).hasApportionedScan();
            }

            @Override // android.os.UidProto.WifiOrBuilder
            public TimerProto getApportionedScan() {
                return ((Wifi) this.instance).getApportionedScan();
            }

            public Builder setApportionedScan(TimerProto value) {
                copyOnWrite();
                ((Wifi) this.instance).setApportionedScan((Wifi) value);
                return this;
            }

            public Builder setApportionedScan(TimerProto.Builder builderForValue) {
                copyOnWrite();
                ((Wifi) this.instance).setApportionedScan((Wifi) builderForValue);
                return this;
            }

            public Builder mergeApportionedScan(TimerProto value) {
                copyOnWrite();
                ((Wifi) this.instance).mergeApportionedScan(value);
                return this;
            }

            public Builder clearApportionedScan() {
                copyOnWrite();
                ((Wifi) this.instance).clearApportionedScan();
                return this;
            }

            @Override // android.os.UidProto.WifiOrBuilder
            public boolean hasBackgroundScan() {
                return ((Wifi) this.instance).hasBackgroundScan();
            }

            @Override // android.os.UidProto.WifiOrBuilder
            public TimerProto getBackgroundScan() {
                return ((Wifi) this.instance).getBackgroundScan();
            }

            public Builder setBackgroundScan(TimerProto value) {
                copyOnWrite();
                ((Wifi) this.instance).setBackgroundScan((Wifi) value);
                return this;
            }

            public Builder setBackgroundScan(TimerProto.Builder builderForValue) {
                copyOnWrite();
                ((Wifi) this.instance).setBackgroundScan((Wifi) builderForValue);
                return this;
            }

            public Builder mergeBackgroundScan(TimerProto value) {
                copyOnWrite();
                ((Wifi) this.instance).mergeBackgroundScan(value);
                return this;
            }

            public Builder clearBackgroundScan() {
                copyOnWrite();
                ((Wifi) this.instance).clearBackgroundScan();
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new Wifi();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    Wifi other = (Wifi) arg1;
                    this.fullWifiLockDurationMs_ = visitor.visitLong(hasFullWifiLockDurationMs(), this.fullWifiLockDurationMs_, other.hasFullWifiLockDurationMs(), other.fullWifiLockDurationMs_);
                    this.runningDurationMs_ = visitor.visitLong(hasRunningDurationMs(), this.runningDurationMs_, other.hasRunningDurationMs(), other.runningDurationMs_);
                    this.apportionedScan_ = (TimerProto) visitor.visitMessage(this.apportionedScan_, other.apportionedScan_);
                    this.backgroundScan_ = (TimerProto) visitor.visitMessage(this.backgroundScan_, other.backgroundScan_);
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
                                this.fullWifiLockDurationMs_ = input.readInt64();
                            } else if (tag == 16) {
                                this.bitField0_ |= 2;
                                this.runningDurationMs_ = input.readInt64();
                            } else if (tag == 26) {
                                TimerProto.Builder subBuilder = null;
                                if ((this.bitField0_ & 4) == 4) {
                                    subBuilder = (TimerProto.Builder) this.apportionedScan_.toBuilder();
                                }
                                this.apportionedScan_ = (TimerProto) input.readMessage(TimerProto.parser(), extensionRegistry);
                                if (subBuilder != null) {
                                    subBuilder.mergeFrom((GeneratedMessageLite) this.apportionedScan_);
                                    this.apportionedScan_ = (TimerProto) subBuilder.buildPartial();
                                }
                                this.bitField0_ |= 4;
                            } else if (tag == 34) {
                                TimerProto.Builder subBuilder2 = null;
                                if ((this.bitField0_ & 8) == 8) {
                                    subBuilder2 = (TimerProto.Builder) this.backgroundScan_.toBuilder();
                                }
                                this.backgroundScan_ = (TimerProto) input.readMessage(TimerProto.parser(), extensionRegistry);
                                if (subBuilder2 != null) {
                                    subBuilder2.mergeFrom((GeneratedMessageLite) this.backgroundScan_);
                                    this.backgroundScan_ = (TimerProto) subBuilder2.buildPartial();
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
                        synchronized (Wifi.class) {
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

        public static Wifi getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<Wifi> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    @Override // android.os.UidProtoOrBuilder
    public boolean hasUid() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // android.os.UidProtoOrBuilder
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

    @Override // android.os.UidProtoOrBuilder
    public List<Package> getPackagesList() {
        return this.packages_;
    }

    public List<? extends PackageOrBuilder> getPackagesOrBuilderList() {
        return this.packages_;
    }

    @Override // android.os.UidProtoOrBuilder
    public int getPackagesCount() {
        return this.packages_.size();
    }

    @Override // android.os.UidProtoOrBuilder
    public Package getPackages(int index) {
        return this.packages_.get(index);
    }

    public PackageOrBuilder getPackagesOrBuilder(int index) {
        return this.packages_.get(index);
    }

    private void ensurePackagesIsMutable() {
        if (!this.packages_.isModifiable()) {
            this.packages_ = GeneratedMessageLite.mutableCopy(this.packages_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPackages(int index, Package value) {
        if (value != null) {
            ensurePackagesIsMutable();
            this.packages_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPackages(int index, Package.Builder builderForValue) {
        ensurePackagesIsMutable();
        this.packages_.set(index, (Package) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addPackages(Package value) {
        if (value != null) {
            ensurePackagesIsMutable();
            this.packages_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addPackages(int index, Package value) {
        if (value != null) {
            ensurePackagesIsMutable();
            this.packages_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addPackages(Package.Builder builderForValue) {
        ensurePackagesIsMutable();
        this.packages_.add((Package) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addPackages(int index, Package.Builder builderForValue) {
        ensurePackagesIsMutable();
        this.packages_.add(index, (Package) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllPackages(Iterable<? extends Package> values) {
        ensurePackagesIsMutable();
        AbstractMessageLite.addAll(values, this.packages_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearPackages() {
        this.packages_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removePackages(int index) {
        ensurePackagesIsMutable();
        this.packages_.remove(index);
    }

    @Override // android.os.UidProtoOrBuilder
    public boolean hasBluetoothController() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // android.os.UidProtoOrBuilder
    public ControllerActivityProto getBluetoothController() {
        ControllerActivityProto controllerActivityProto = this.bluetoothController_;
        return controllerActivityProto == null ? ControllerActivityProto.getDefaultInstance() : controllerActivityProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setBluetoothController(ControllerActivityProto value) {
        if (value != null) {
            this.bluetoothController_ = value;
            this.bitField0_ |= 2;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setBluetoothController(ControllerActivityProto.Builder builderForValue) {
        this.bluetoothController_ = (ControllerActivityProto) builderForValue.build();
        this.bitField0_ |= 2;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeBluetoothController(ControllerActivityProto value) {
        ControllerActivityProto controllerActivityProto = this.bluetoothController_;
        if (controllerActivityProto == null || controllerActivityProto == ControllerActivityProto.getDefaultInstance()) {
            this.bluetoothController_ = value;
        } else {
            this.bluetoothController_ = (ControllerActivityProto) ((ControllerActivityProto.Builder) ControllerActivityProto.newBuilder(this.bluetoothController_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 2;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearBluetoothController() {
        this.bluetoothController_ = null;
        this.bitField0_ &= -3;
    }

    @Override // android.os.UidProtoOrBuilder
    public boolean hasModemController() {
        return (this.bitField0_ & 4) == 4;
    }

    @Override // android.os.UidProtoOrBuilder
    public ControllerActivityProto getModemController() {
        ControllerActivityProto controllerActivityProto = this.modemController_;
        return controllerActivityProto == null ? ControllerActivityProto.getDefaultInstance() : controllerActivityProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setModemController(ControllerActivityProto value) {
        if (value != null) {
            this.modemController_ = value;
            this.bitField0_ |= 4;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setModemController(ControllerActivityProto.Builder builderForValue) {
        this.modemController_ = (ControllerActivityProto) builderForValue.build();
        this.bitField0_ |= 4;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeModemController(ControllerActivityProto value) {
        ControllerActivityProto controllerActivityProto = this.modemController_;
        if (controllerActivityProto == null || controllerActivityProto == ControllerActivityProto.getDefaultInstance()) {
            this.modemController_ = value;
        } else {
            this.modemController_ = (ControllerActivityProto) ((ControllerActivityProto.Builder) ControllerActivityProto.newBuilder(this.modemController_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 4;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearModemController() {
        this.modemController_ = null;
        this.bitField0_ &= -5;
    }

    @Override // android.os.UidProtoOrBuilder
    public boolean hasWifiController() {
        return (this.bitField0_ & 8) == 8;
    }

    @Override // android.os.UidProtoOrBuilder
    public ControllerActivityProto getWifiController() {
        ControllerActivityProto controllerActivityProto = this.wifiController_;
        return controllerActivityProto == null ? ControllerActivityProto.getDefaultInstance() : controllerActivityProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setWifiController(ControllerActivityProto value) {
        if (value != null) {
            this.wifiController_ = value;
            this.bitField0_ |= 8;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setWifiController(ControllerActivityProto.Builder builderForValue) {
        this.wifiController_ = (ControllerActivityProto) builderForValue.build();
        this.bitField0_ |= 8;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeWifiController(ControllerActivityProto value) {
        ControllerActivityProto controllerActivityProto = this.wifiController_;
        if (controllerActivityProto == null || controllerActivityProto == ControllerActivityProto.getDefaultInstance()) {
            this.wifiController_ = value;
        } else {
            this.wifiController_ = (ControllerActivityProto) ((ControllerActivityProto.Builder) ControllerActivityProto.newBuilder(this.wifiController_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 8;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearWifiController() {
        this.wifiController_ = null;
        this.bitField0_ &= -9;
    }

    @Override // android.os.UidProtoOrBuilder
    public boolean hasBluetoothMisc() {
        return (this.bitField0_ & 16) == 16;
    }

    @Override // android.os.UidProtoOrBuilder
    public BluetoothMisc getBluetoothMisc() {
        BluetoothMisc bluetoothMisc = this.bluetoothMisc_;
        return bluetoothMisc == null ? BluetoothMisc.getDefaultInstance() : bluetoothMisc;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setBluetoothMisc(BluetoothMisc value) {
        if (value != null) {
            this.bluetoothMisc_ = value;
            this.bitField0_ |= 16;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setBluetoothMisc(BluetoothMisc.Builder builderForValue) {
        this.bluetoothMisc_ = (BluetoothMisc) builderForValue.build();
        this.bitField0_ |= 16;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeBluetoothMisc(BluetoothMisc value) {
        BluetoothMisc bluetoothMisc = this.bluetoothMisc_;
        if (bluetoothMisc == null || bluetoothMisc == BluetoothMisc.getDefaultInstance()) {
            this.bluetoothMisc_ = value;
        } else {
            this.bluetoothMisc_ = (BluetoothMisc) ((BluetoothMisc.Builder) BluetoothMisc.newBuilder(this.bluetoothMisc_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 16;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearBluetoothMisc() {
        this.bluetoothMisc_ = null;
        this.bitField0_ &= -17;
    }

    @Override // android.os.UidProtoOrBuilder
    public boolean hasCpu() {
        return (this.bitField0_ & 32) == 32;
    }

    @Override // android.os.UidProtoOrBuilder
    public Cpu getCpu() {
        Cpu cpu = this.cpu_;
        return cpu == null ? Cpu.getDefaultInstance() : cpu;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setCpu(Cpu value) {
        if (value != null) {
            this.cpu_ = value;
            this.bitField0_ |= 32;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setCpu(Cpu.Builder builderForValue) {
        this.cpu_ = (Cpu) builderForValue.build();
        this.bitField0_ |= 32;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeCpu(Cpu value) {
        Cpu cpu = this.cpu_;
        if (cpu == null || cpu == Cpu.getDefaultInstance()) {
            this.cpu_ = value;
        } else {
            this.cpu_ = (Cpu) ((Cpu.Builder) Cpu.newBuilder(this.cpu_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 32;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearCpu() {
        this.cpu_ = null;
        this.bitField0_ &= -33;
    }

    @Override // android.os.UidProtoOrBuilder
    public boolean hasAudio() {
        return (this.bitField0_ & 64) == 64;
    }

    @Override // android.os.UidProtoOrBuilder
    public TimerProto getAudio() {
        TimerProto timerProto = this.audio_;
        return timerProto == null ? TimerProto.getDefaultInstance() : timerProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setAudio(TimerProto value) {
        if (value != null) {
            this.audio_ = value;
            this.bitField0_ |= 64;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setAudio(TimerProto.Builder builderForValue) {
        this.audio_ = (TimerProto) builderForValue.build();
        this.bitField0_ |= 64;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeAudio(TimerProto value) {
        TimerProto timerProto = this.audio_;
        if (timerProto == null || timerProto == TimerProto.getDefaultInstance()) {
            this.audio_ = value;
        } else {
            this.audio_ = (TimerProto) ((TimerProto.Builder) TimerProto.newBuilder(this.audio_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 64;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearAudio() {
        this.audio_ = null;
        this.bitField0_ &= -65;
    }

    @Override // android.os.UidProtoOrBuilder
    public boolean hasCamera() {
        return (this.bitField0_ & 128) == 128;
    }

    @Override // android.os.UidProtoOrBuilder
    public TimerProto getCamera() {
        TimerProto timerProto = this.camera_;
        return timerProto == null ? TimerProto.getDefaultInstance() : timerProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setCamera(TimerProto value) {
        if (value != null) {
            this.camera_ = value;
            this.bitField0_ |= 128;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setCamera(TimerProto.Builder builderForValue) {
        this.camera_ = (TimerProto) builderForValue.build();
        this.bitField0_ |= 128;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeCamera(TimerProto value) {
        TimerProto timerProto = this.camera_;
        if (timerProto == null || timerProto == TimerProto.getDefaultInstance()) {
            this.camera_ = value;
        } else {
            this.camera_ = (TimerProto) ((TimerProto.Builder) TimerProto.newBuilder(this.camera_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 128;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearCamera() {
        this.camera_ = null;
        this.bitField0_ &= -129;
    }

    @Override // android.os.UidProtoOrBuilder
    public boolean hasFlashlight() {
        return (this.bitField0_ & 256) == 256;
    }

    @Override // android.os.UidProtoOrBuilder
    public TimerProto getFlashlight() {
        TimerProto timerProto = this.flashlight_;
        return timerProto == null ? TimerProto.getDefaultInstance() : timerProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setFlashlight(TimerProto value) {
        if (value != null) {
            this.flashlight_ = value;
            this.bitField0_ |= 256;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setFlashlight(TimerProto.Builder builderForValue) {
        this.flashlight_ = (TimerProto) builderForValue.build();
        this.bitField0_ |= 256;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeFlashlight(TimerProto value) {
        TimerProto timerProto = this.flashlight_;
        if (timerProto == null || timerProto == TimerProto.getDefaultInstance()) {
            this.flashlight_ = value;
        } else {
            this.flashlight_ = (TimerProto) ((TimerProto.Builder) TimerProto.newBuilder(this.flashlight_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 256;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearFlashlight() {
        this.flashlight_ = null;
        this.bitField0_ &= -257;
    }

    @Override // android.os.UidProtoOrBuilder
    public boolean hasForegroundActivity() {
        return (this.bitField0_ & 512) == 512;
    }

    @Override // android.os.UidProtoOrBuilder
    public TimerProto getForegroundActivity() {
        TimerProto timerProto = this.foregroundActivity_;
        return timerProto == null ? TimerProto.getDefaultInstance() : timerProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setForegroundActivity(TimerProto value) {
        if (value != null) {
            this.foregroundActivity_ = value;
            this.bitField0_ |= 512;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setForegroundActivity(TimerProto.Builder builderForValue) {
        this.foregroundActivity_ = (TimerProto) builderForValue.build();
        this.bitField0_ |= 512;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeForegroundActivity(TimerProto value) {
        TimerProto timerProto = this.foregroundActivity_;
        if (timerProto == null || timerProto == TimerProto.getDefaultInstance()) {
            this.foregroundActivity_ = value;
        } else {
            this.foregroundActivity_ = (TimerProto) ((TimerProto.Builder) TimerProto.newBuilder(this.foregroundActivity_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 512;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearForegroundActivity() {
        this.foregroundActivity_ = null;
        this.bitField0_ &= -513;
    }

    @Override // android.os.UidProtoOrBuilder
    public boolean hasForegroundService() {
        return (this.bitField0_ & 1024) == 1024;
    }

    @Override // android.os.UidProtoOrBuilder
    public TimerProto getForegroundService() {
        TimerProto timerProto = this.foregroundService_;
        return timerProto == null ? TimerProto.getDefaultInstance() : timerProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setForegroundService(TimerProto value) {
        if (value != null) {
            this.foregroundService_ = value;
            this.bitField0_ |= 1024;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setForegroundService(TimerProto.Builder builderForValue) {
        this.foregroundService_ = (TimerProto) builderForValue.build();
        this.bitField0_ |= 1024;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeForegroundService(TimerProto value) {
        TimerProto timerProto = this.foregroundService_;
        if (timerProto == null || timerProto == TimerProto.getDefaultInstance()) {
            this.foregroundService_ = value;
        } else {
            this.foregroundService_ = (TimerProto) ((TimerProto.Builder) TimerProto.newBuilder(this.foregroundService_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 1024;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearForegroundService() {
        this.foregroundService_ = null;
        this.bitField0_ &= -1025;
    }

    @Override // android.os.UidProtoOrBuilder
    public boolean hasVibrator() {
        return (this.bitField0_ & 2048) == 2048;
    }

    @Override // android.os.UidProtoOrBuilder
    public TimerProto getVibrator() {
        TimerProto timerProto = this.vibrator_;
        return timerProto == null ? TimerProto.getDefaultInstance() : timerProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setVibrator(TimerProto value) {
        if (value != null) {
            this.vibrator_ = value;
            this.bitField0_ |= 2048;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setVibrator(TimerProto.Builder builderForValue) {
        this.vibrator_ = (TimerProto) builderForValue.build();
        this.bitField0_ |= 2048;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeVibrator(TimerProto value) {
        TimerProto timerProto = this.vibrator_;
        if (timerProto == null || timerProto == TimerProto.getDefaultInstance()) {
            this.vibrator_ = value;
        } else {
            this.vibrator_ = (TimerProto) ((TimerProto.Builder) TimerProto.newBuilder(this.vibrator_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 2048;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearVibrator() {
        this.vibrator_ = null;
        this.bitField0_ &= -2049;
    }

    @Override // android.os.UidProtoOrBuilder
    public boolean hasVideo() {
        return (this.bitField0_ & 4096) == 4096;
    }

    @Override // android.os.UidProtoOrBuilder
    public TimerProto getVideo() {
        TimerProto timerProto = this.video_;
        return timerProto == null ? TimerProto.getDefaultInstance() : timerProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setVideo(TimerProto value) {
        if (value != null) {
            this.video_ = value;
            this.bitField0_ |= 4096;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setVideo(TimerProto.Builder builderForValue) {
        this.video_ = (TimerProto) builderForValue.build();
        this.bitField0_ |= 4096;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeVideo(TimerProto value) {
        TimerProto timerProto = this.video_;
        if (timerProto == null || timerProto == TimerProto.getDefaultInstance()) {
            this.video_ = value;
        } else {
            this.video_ = (TimerProto) ((TimerProto.Builder) TimerProto.newBuilder(this.video_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 4096;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearVideo() {
        this.video_ = null;
        this.bitField0_ &= -4097;
    }

    @Override // android.os.UidProtoOrBuilder
    public List<Job> getJobsList() {
        return this.jobs_;
    }

    public List<? extends JobOrBuilder> getJobsOrBuilderList() {
        return this.jobs_;
    }

    @Override // android.os.UidProtoOrBuilder
    public int getJobsCount() {
        return this.jobs_.size();
    }

    @Override // android.os.UidProtoOrBuilder
    public Job getJobs(int index) {
        return this.jobs_.get(index);
    }

    public JobOrBuilder getJobsOrBuilder(int index) {
        return this.jobs_.get(index);
    }

    private void ensureJobsIsMutable() {
        if (!this.jobs_.isModifiable()) {
            this.jobs_ = GeneratedMessageLite.mutableCopy(this.jobs_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setJobs(int index, Job value) {
        if (value != null) {
            ensureJobsIsMutable();
            this.jobs_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setJobs(int index, Job.Builder builderForValue) {
        ensureJobsIsMutable();
        this.jobs_.set(index, (Job) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addJobs(Job value) {
        if (value != null) {
            ensureJobsIsMutable();
            this.jobs_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addJobs(int index, Job value) {
        if (value != null) {
            ensureJobsIsMutable();
            this.jobs_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addJobs(Job.Builder builderForValue) {
        ensureJobsIsMutable();
        this.jobs_.add((Job) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addJobs(int index, Job.Builder builderForValue) {
        ensureJobsIsMutable();
        this.jobs_.add(index, (Job) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllJobs(Iterable<? extends Job> values) {
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

    @Override // android.os.UidProtoOrBuilder
    public List<JobCompletion> getJobCompletionList() {
        return this.jobCompletion_;
    }

    public List<? extends JobCompletionOrBuilder> getJobCompletionOrBuilderList() {
        return this.jobCompletion_;
    }

    @Override // android.os.UidProtoOrBuilder
    public int getJobCompletionCount() {
        return this.jobCompletion_.size();
    }

    @Override // android.os.UidProtoOrBuilder
    public JobCompletion getJobCompletion(int index) {
        return this.jobCompletion_.get(index);
    }

    public JobCompletionOrBuilder getJobCompletionOrBuilder(int index) {
        return this.jobCompletion_.get(index);
    }

    private void ensureJobCompletionIsMutable() {
        if (!this.jobCompletion_.isModifiable()) {
            this.jobCompletion_ = GeneratedMessageLite.mutableCopy(this.jobCompletion_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setJobCompletion(int index, JobCompletion value) {
        if (value != null) {
            ensureJobCompletionIsMutable();
            this.jobCompletion_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setJobCompletion(int index, JobCompletion.Builder builderForValue) {
        ensureJobCompletionIsMutable();
        this.jobCompletion_.set(index, (JobCompletion) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addJobCompletion(JobCompletion value) {
        if (value != null) {
            ensureJobCompletionIsMutable();
            this.jobCompletion_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addJobCompletion(int index, JobCompletion value) {
        if (value != null) {
            ensureJobCompletionIsMutable();
            this.jobCompletion_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addJobCompletion(JobCompletion.Builder builderForValue) {
        ensureJobCompletionIsMutable();
        this.jobCompletion_.add((JobCompletion) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addJobCompletion(int index, JobCompletion.Builder builderForValue) {
        ensureJobCompletionIsMutable();
        this.jobCompletion_.add(index, (JobCompletion) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllJobCompletion(Iterable<? extends JobCompletion> values) {
        ensureJobCompletionIsMutable();
        AbstractMessageLite.addAll(values, this.jobCompletion_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearJobCompletion() {
        this.jobCompletion_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeJobCompletion(int index) {
        ensureJobCompletionIsMutable();
        this.jobCompletion_.remove(index);
    }

    @Override // android.os.UidProtoOrBuilder
    public boolean hasNetwork() {
        return (this.bitField0_ & 8192) == 8192;
    }

    @Override // android.os.UidProtoOrBuilder
    public Network getNetwork() {
        Network network = this.network_;
        return network == null ? Network.getDefaultInstance() : network;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setNetwork(Network value) {
        if (value != null) {
            this.network_ = value;
            this.bitField0_ |= 8192;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setNetwork(Network.Builder builderForValue) {
        this.network_ = (Network) builderForValue.build();
        this.bitField0_ |= 8192;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeNetwork(Network value) {
        Network network = this.network_;
        if (network == null || network == Network.getDefaultInstance()) {
            this.network_ = value;
        } else {
            this.network_ = (Network) ((Network.Builder) Network.newBuilder(this.network_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 8192;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearNetwork() {
        this.network_ = null;
        this.bitField0_ &= -8193;
    }

    @Override // android.os.UidProtoOrBuilder
    public boolean hasPowerUseItem() {
        return (this.bitField0_ & 16384) == 16384;
    }

    @Override // android.os.UidProtoOrBuilder
    public PowerUseItem getPowerUseItem() {
        PowerUseItem powerUseItem = this.powerUseItem_;
        return powerUseItem == null ? PowerUseItem.getDefaultInstance() : powerUseItem;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPowerUseItem(PowerUseItem value) {
        if (value != null) {
            this.powerUseItem_ = value;
            this.bitField0_ |= 16384;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPowerUseItem(PowerUseItem.Builder builderForValue) {
        this.powerUseItem_ = (PowerUseItem) builderForValue.build();
        this.bitField0_ |= 16384;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergePowerUseItem(PowerUseItem value) {
        PowerUseItem powerUseItem = this.powerUseItem_;
        if (powerUseItem == null || powerUseItem == PowerUseItem.getDefaultInstance()) {
            this.powerUseItem_ = value;
        } else {
            this.powerUseItem_ = (PowerUseItem) ((PowerUseItem.Builder) PowerUseItem.newBuilder(this.powerUseItem_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 16384;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearPowerUseItem() {
        this.powerUseItem_ = null;
        this.bitField0_ &= -16385;
    }

    @Override // android.os.UidProtoOrBuilder
    public List<Process> getProcessList() {
        return this.process_;
    }

    public List<? extends ProcessOrBuilder> getProcessOrBuilderList() {
        return this.process_;
    }

    @Override // android.os.UidProtoOrBuilder
    public int getProcessCount() {
        return this.process_.size();
    }

    @Override // android.os.UidProtoOrBuilder
    public Process getProcess(int index) {
        return this.process_.get(index);
    }

    public ProcessOrBuilder getProcessOrBuilder(int index) {
        return this.process_.get(index);
    }

    private void ensureProcessIsMutable() {
        if (!this.process_.isModifiable()) {
            this.process_ = GeneratedMessageLite.mutableCopy(this.process_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setProcess(int index, Process value) {
        if (value != null) {
            ensureProcessIsMutable();
            this.process_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setProcess(int index, Process.Builder builderForValue) {
        ensureProcessIsMutable();
        this.process_.set(index, (Process) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addProcess(Process value) {
        if (value != null) {
            ensureProcessIsMutable();
            this.process_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addProcess(int index, Process value) {
        if (value != null) {
            ensureProcessIsMutable();
            this.process_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addProcess(Process.Builder builderForValue) {
        ensureProcessIsMutable();
        this.process_.add((Process) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addProcess(int index, Process.Builder builderForValue) {
        ensureProcessIsMutable();
        this.process_.add(index, (Process) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllProcess(Iterable<? extends Process> values) {
        ensureProcessIsMutable();
        AbstractMessageLite.addAll(values, this.process_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearProcess() {
        this.process_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeProcess(int index) {
        ensureProcessIsMutable();
        this.process_.remove(index);
    }

    @Override // android.os.UidProtoOrBuilder
    public List<StateTime> getStatesList() {
        return this.states_;
    }

    public List<? extends StateTimeOrBuilder> getStatesOrBuilderList() {
        return this.states_;
    }

    @Override // android.os.UidProtoOrBuilder
    public int getStatesCount() {
        return this.states_.size();
    }

    @Override // android.os.UidProtoOrBuilder
    public StateTime getStates(int index) {
        return this.states_.get(index);
    }

    public StateTimeOrBuilder getStatesOrBuilder(int index) {
        return this.states_.get(index);
    }

    private void ensureStatesIsMutable() {
        if (!this.states_.isModifiable()) {
            this.states_ = GeneratedMessageLite.mutableCopy(this.states_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setStates(int index, StateTime value) {
        if (value != null) {
            ensureStatesIsMutable();
            this.states_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setStates(int index, StateTime.Builder builderForValue) {
        ensureStatesIsMutable();
        this.states_.set(index, (StateTime) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addStates(StateTime value) {
        if (value != null) {
            ensureStatesIsMutable();
            this.states_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addStates(int index, StateTime value) {
        if (value != null) {
            ensureStatesIsMutable();
            this.states_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addStates(StateTime.Builder builderForValue) {
        ensureStatesIsMutable();
        this.states_.add((StateTime) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addStates(int index, StateTime.Builder builderForValue) {
        ensureStatesIsMutable();
        this.states_.add(index, (StateTime) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllStates(Iterable<? extends StateTime> values) {
        ensureStatesIsMutable();
        AbstractMessageLite.addAll(values, this.states_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearStates() {
        this.states_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeStates(int index) {
        ensureStatesIsMutable();
        this.states_.remove(index);
    }

    @Override // android.os.UidProtoOrBuilder
    public List<Sensor> getSensorsList() {
        return this.sensors_;
    }

    public List<? extends SensorOrBuilder> getSensorsOrBuilderList() {
        return this.sensors_;
    }

    @Override // android.os.UidProtoOrBuilder
    public int getSensorsCount() {
        return this.sensors_.size();
    }

    @Override // android.os.UidProtoOrBuilder
    public Sensor getSensors(int index) {
        return this.sensors_.get(index);
    }

    public SensorOrBuilder getSensorsOrBuilder(int index) {
        return this.sensors_.get(index);
    }

    private void ensureSensorsIsMutable() {
        if (!this.sensors_.isModifiable()) {
            this.sensors_ = GeneratedMessageLite.mutableCopy(this.sensors_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setSensors(int index, Sensor value) {
        if (value != null) {
            ensureSensorsIsMutable();
            this.sensors_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setSensors(int index, Sensor.Builder builderForValue) {
        ensureSensorsIsMutable();
        this.sensors_.set(index, (Sensor) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addSensors(Sensor value) {
        if (value != null) {
            ensureSensorsIsMutable();
            this.sensors_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addSensors(int index, Sensor value) {
        if (value != null) {
            ensureSensorsIsMutable();
            this.sensors_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addSensors(Sensor.Builder builderForValue) {
        ensureSensorsIsMutable();
        this.sensors_.add((Sensor) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addSensors(int index, Sensor.Builder builderForValue) {
        ensureSensorsIsMutable();
        this.sensors_.add(index, (Sensor) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllSensors(Iterable<? extends Sensor> values) {
        ensureSensorsIsMutable();
        AbstractMessageLite.addAll(values, this.sensors_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearSensors() {
        this.sensors_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeSensors(int index) {
        ensureSensorsIsMutable();
        this.sensors_.remove(index);
    }

    @Override // android.os.UidProtoOrBuilder
    public List<Sync> getSyncsList() {
        return this.syncs_;
    }

    public List<? extends SyncOrBuilder> getSyncsOrBuilderList() {
        return this.syncs_;
    }

    @Override // android.os.UidProtoOrBuilder
    public int getSyncsCount() {
        return this.syncs_.size();
    }

    @Override // android.os.UidProtoOrBuilder
    public Sync getSyncs(int index) {
        return this.syncs_.get(index);
    }

    public SyncOrBuilder getSyncsOrBuilder(int index) {
        return this.syncs_.get(index);
    }

    private void ensureSyncsIsMutable() {
        if (!this.syncs_.isModifiable()) {
            this.syncs_ = GeneratedMessageLite.mutableCopy(this.syncs_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setSyncs(int index, Sync value) {
        if (value != null) {
            ensureSyncsIsMutable();
            this.syncs_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setSyncs(int index, Sync.Builder builderForValue) {
        ensureSyncsIsMutable();
        this.syncs_.set(index, (Sync) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addSyncs(Sync value) {
        if (value != null) {
            ensureSyncsIsMutable();
            this.syncs_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addSyncs(int index, Sync value) {
        if (value != null) {
            ensureSyncsIsMutable();
            this.syncs_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addSyncs(Sync.Builder builderForValue) {
        ensureSyncsIsMutable();
        this.syncs_.add((Sync) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addSyncs(int index, Sync.Builder builderForValue) {
        ensureSyncsIsMutable();
        this.syncs_.add(index, (Sync) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllSyncs(Iterable<? extends Sync> values) {
        ensureSyncsIsMutable();
        AbstractMessageLite.addAll(values, this.syncs_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearSyncs() {
        this.syncs_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeSyncs(int index) {
        ensureSyncsIsMutable();
        this.syncs_.remove(index);
    }

    @Override // android.os.UidProtoOrBuilder
    public List<UserActivity> getUserActivityList() {
        return this.userActivity_;
    }

    public List<? extends UserActivityOrBuilder> getUserActivityOrBuilderList() {
        return this.userActivity_;
    }

    @Override // android.os.UidProtoOrBuilder
    public int getUserActivityCount() {
        return this.userActivity_.size();
    }

    @Override // android.os.UidProtoOrBuilder
    public UserActivity getUserActivity(int index) {
        return this.userActivity_.get(index);
    }

    public UserActivityOrBuilder getUserActivityOrBuilder(int index) {
        return this.userActivity_.get(index);
    }

    private void ensureUserActivityIsMutable() {
        if (!this.userActivity_.isModifiable()) {
            this.userActivity_ = GeneratedMessageLite.mutableCopy(this.userActivity_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setUserActivity(int index, UserActivity value) {
        if (value != null) {
            ensureUserActivityIsMutable();
            this.userActivity_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setUserActivity(int index, UserActivity.Builder builderForValue) {
        ensureUserActivityIsMutable();
        this.userActivity_.set(index, (UserActivity) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addUserActivity(UserActivity value) {
        if (value != null) {
            ensureUserActivityIsMutable();
            this.userActivity_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addUserActivity(int index, UserActivity value) {
        if (value != null) {
            ensureUserActivityIsMutable();
            this.userActivity_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addUserActivity(UserActivity.Builder builderForValue) {
        ensureUserActivityIsMutable();
        this.userActivity_.add((UserActivity) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addUserActivity(int index, UserActivity.Builder builderForValue) {
        ensureUserActivityIsMutable();
        this.userActivity_.add(index, (UserActivity) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllUserActivity(Iterable<? extends UserActivity> values) {
        ensureUserActivityIsMutable();
        AbstractMessageLite.addAll(values, this.userActivity_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearUserActivity() {
        this.userActivity_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeUserActivity(int index) {
        ensureUserActivityIsMutable();
        this.userActivity_.remove(index);
    }

    @Override // android.os.UidProtoOrBuilder
    public boolean hasAggregatedWakelock() {
        return (this.bitField0_ & 32768) == 32768;
    }

    @Override // android.os.UidProtoOrBuilder
    public AggregatedWakelock getAggregatedWakelock() {
        AggregatedWakelock aggregatedWakelock = this.aggregatedWakelock_;
        return aggregatedWakelock == null ? AggregatedWakelock.getDefaultInstance() : aggregatedWakelock;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setAggregatedWakelock(AggregatedWakelock value) {
        if (value != null) {
            this.aggregatedWakelock_ = value;
            this.bitField0_ |= 32768;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setAggregatedWakelock(AggregatedWakelock.Builder builderForValue) {
        this.aggregatedWakelock_ = (AggregatedWakelock) builderForValue.build();
        this.bitField0_ |= 32768;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeAggregatedWakelock(AggregatedWakelock value) {
        AggregatedWakelock aggregatedWakelock = this.aggregatedWakelock_;
        if (aggregatedWakelock == null || aggregatedWakelock == AggregatedWakelock.getDefaultInstance()) {
            this.aggregatedWakelock_ = value;
        } else {
            this.aggregatedWakelock_ = (AggregatedWakelock) ((AggregatedWakelock.Builder) AggregatedWakelock.newBuilder(this.aggregatedWakelock_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 32768;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearAggregatedWakelock() {
        this.aggregatedWakelock_ = null;
        this.bitField0_ &= -32769;
    }

    @Override // android.os.UidProtoOrBuilder
    public List<Wakelock> getWakelocksList() {
        return this.wakelocks_;
    }

    public List<? extends WakelockOrBuilder> getWakelocksOrBuilderList() {
        return this.wakelocks_;
    }

    @Override // android.os.UidProtoOrBuilder
    public int getWakelocksCount() {
        return this.wakelocks_.size();
    }

    @Override // android.os.UidProtoOrBuilder
    public Wakelock getWakelocks(int index) {
        return this.wakelocks_.get(index);
    }

    public WakelockOrBuilder getWakelocksOrBuilder(int index) {
        return this.wakelocks_.get(index);
    }

    private void ensureWakelocksIsMutable() {
        if (!this.wakelocks_.isModifiable()) {
            this.wakelocks_ = GeneratedMessageLite.mutableCopy(this.wakelocks_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setWakelocks(int index, Wakelock value) {
        if (value != null) {
            ensureWakelocksIsMutable();
            this.wakelocks_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setWakelocks(int index, Wakelock.Builder builderForValue) {
        ensureWakelocksIsMutable();
        this.wakelocks_.set(index, (Wakelock) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addWakelocks(Wakelock value) {
        if (value != null) {
            ensureWakelocksIsMutable();
            this.wakelocks_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addWakelocks(int index, Wakelock value) {
        if (value != null) {
            ensureWakelocksIsMutable();
            this.wakelocks_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addWakelocks(Wakelock.Builder builderForValue) {
        ensureWakelocksIsMutable();
        this.wakelocks_.add((Wakelock) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addWakelocks(int index, Wakelock.Builder builderForValue) {
        ensureWakelocksIsMutable();
        this.wakelocks_.add(index, (Wakelock) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllWakelocks(Iterable<? extends Wakelock> values) {
        ensureWakelocksIsMutable();
        AbstractMessageLite.addAll(values, this.wakelocks_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearWakelocks() {
        this.wakelocks_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeWakelocks(int index) {
        ensureWakelocksIsMutable();
        this.wakelocks_.remove(index);
    }

    @Override // android.os.UidProtoOrBuilder
    public List<WakeupAlarm> getWakeupAlarmList() {
        return this.wakeupAlarm_;
    }

    public List<? extends WakeupAlarmOrBuilder> getWakeupAlarmOrBuilderList() {
        return this.wakeupAlarm_;
    }

    @Override // android.os.UidProtoOrBuilder
    public int getWakeupAlarmCount() {
        return this.wakeupAlarm_.size();
    }

    @Override // android.os.UidProtoOrBuilder
    public WakeupAlarm getWakeupAlarm(int index) {
        return this.wakeupAlarm_.get(index);
    }

    public WakeupAlarmOrBuilder getWakeupAlarmOrBuilder(int index) {
        return this.wakeupAlarm_.get(index);
    }

    private void ensureWakeupAlarmIsMutable() {
        if (!this.wakeupAlarm_.isModifiable()) {
            this.wakeupAlarm_ = GeneratedMessageLite.mutableCopy(this.wakeupAlarm_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setWakeupAlarm(int index, WakeupAlarm value) {
        if (value != null) {
            ensureWakeupAlarmIsMutable();
            this.wakeupAlarm_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setWakeupAlarm(int index, WakeupAlarm.Builder builderForValue) {
        ensureWakeupAlarmIsMutable();
        this.wakeupAlarm_.set(index, (WakeupAlarm) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addWakeupAlarm(WakeupAlarm value) {
        if (value != null) {
            ensureWakeupAlarmIsMutable();
            this.wakeupAlarm_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addWakeupAlarm(int index, WakeupAlarm value) {
        if (value != null) {
            ensureWakeupAlarmIsMutable();
            this.wakeupAlarm_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addWakeupAlarm(WakeupAlarm.Builder builderForValue) {
        ensureWakeupAlarmIsMutable();
        this.wakeupAlarm_.add((WakeupAlarm) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addWakeupAlarm(int index, WakeupAlarm.Builder builderForValue) {
        ensureWakeupAlarmIsMutable();
        this.wakeupAlarm_.add(index, (WakeupAlarm) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllWakeupAlarm(Iterable<? extends WakeupAlarm> values) {
        ensureWakeupAlarmIsMutable();
        AbstractMessageLite.addAll(values, this.wakeupAlarm_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearWakeupAlarm() {
        this.wakeupAlarm_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeWakeupAlarm(int index) {
        ensureWakeupAlarmIsMutable();
        this.wakeupAlarm_.remove(index);
    }

    @Override // android.os.UidProtoOrBuilder
    public boolean hasWifi() {
        return (this.bitField0_ & 65536) == 65536;
    }

    @Override // android.os.UidProtoOrBuilder
    public Wifi getWifi() {
        Wifi wifi = this.wifi_;
        return wifi == null ? Wifi.getDefaultInstance() : wifi;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setWifi(Wifi value) {
        if (value != null) {
            this.wifi_ = value;
            this.bitField0_ |= 65536;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setWifi(Wifi.Builder builderForValue) {
        this.wifi_ = (Wifi) builderForValue.build();
        this.bitField0_ |= 65536;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeWifi(Wifi value) {
        Wifi wifi = this.wifi_;
        if (wifi == null || wifi == Wifi.getDefaultInstance()) {
            this.wifi_ = value;
        } else {
            this.wifi_ = (Wifi) ((Wifi.Builder) Wifi.newBuilder(this.wifi_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 65536;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearWifi() {
        this.wifi_ = null;
        this.bitField0_ &= -65537;
    }

    @Override // android.os.UidProtoOrBuilder
    public boolean hasWifiMulticastWakelock() {
        return (this.bitField0_ & 131072) == 131072;
    }

    @Override // android.os.UidProtoOrBuilder
    public TimerProto getWifiMulticastWakelock() {
        TimerProto timerProto = this.wifiMulticastWakelock_;
        return timerProto == null ? TimerProto.getDefaultInstance() : timerProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setWifiMulticastWakelock(TimerProto value) {
        if (value != null) {
            this.wifiMulticastWakelock_ = value;
            this.bitField0_ |= 131072;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setWifiMulticastWakelock(TimerProto.Builder builderForValue) {
        this.wifiMulticastWakelock_ = (TimerProto) builderForValue.build();
        this.bitField0_ |= 131072;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeWifiMulticastWakelock(TimerProto value) {
        TimerProto timerProto = this.wifiMulticastWakelock_;
        if (timerProto == null || timerProto == TimerProto.getDefaultInstance()) {
            this.wifiMulticastWakelock_ = value;
        } else {
            this.wifiMulticastWakelock_ = (TimerProto) ((TimerProto.Builder) TimerProto.newBuilder(this.wifiMulticastWakelock_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 131072;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearWifiMulticastWakelock() {
        this.wifiMulticastWakelock_ = null;
        this.bitField0_ &= -131073;
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeInt32(1, this.uid_);
        }
        for (int i = 0; i < this.packages_.size(); i++) {
            output.writeMessage(2, this.packages_.get(i));
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeMessage(3, getBluetoothController());
        }
        if ((this.bitField0_ & 4) == 4) {
            output.writeMessage(4, getModemController());
        }
        if ((this.bitField0_ & 8) == 8) {
            output.writeMessage(5, getWifiController());
        }
        if ((this.bitField0_ & 16) == 16) {
            output.writeMessage(6, getBluetoothMisc());
        }
        if ((this.bitField0_ & 32) == 32) {
            output.writeMessage(7, getCpu());
        }
        if ((this.bitField0_ & 64) == 64) {
            output.writeMessage(8, getAudio());
        }
        if ((this.bitField0_ & 128) == 128) {
            output.writeMessage(9, getCamera());
        }
        if ((this.bitField0_ & 256) == 256) {
            output.writeMessage(10, getFlashlight());
        }
        if ((this.bitField0_ & 512) == 512) {
            output.writeMessage(11, getForegroundActivity());
        }
        if ((this.bitField0_ & 1024) == 1024) {
            output.writeMessage(12, getForegroundService());
        }
        if ((this.bitField0_ & 2048) == 2048) {
            output.writeMessage(13, getVibrator());
        }
        if ((this.bitField0_ & 4096) == 4096) {
            output.writeMessage(14, getVideo());
        }
        for (int i2 = 0; i2 < this.jobs_.size(); i2++) {
            output.writeMessage(15, this.jobs_.get(i2));
        }
        for (int i3 = 0; i3 < this.jobCompletion_.size(); i3++) {
            output.writeMessage(16, this.jobCompletion_.get(i3));
        }
        if ((this.bitField0_ & 8192) == 8192) {
            output.writeMessage(17, getNetwork());
        }
        if ((this.bitField0_ & 16384) == 16384) {
            output.writeMessage(18, getPowerUseItem());
        }
        for (int i4 = 0; i4 < this.process_.size(); i4++) {
            output.writeMessage(19, this.process_.get(i4));
        }
        for (int i5 = 0; i5 < this.states_.size(); i5++) {
            output.writeMessage(20, this.states_.get(i5));
        }
        for (int i6 = 0; i6 < this.sensors_.size(); i6++) {
            output.writeMessage(21, this.sensors_.get(i6));
        }
        for (int i7 = 0; i7 < this.syncs_.size(); i7++) {
            output.writeMessage(22, this.syncs_.get(i7));
        }
        for (int i8 = 0; i8 < this.userActivity_.size(); i8++) {
            output.writeMessage(23, this.userActivity_.get(i8));
        }
        if ((this.bitField0_ & 32768) == 32768) {
            output.writeMessage(24, getAggregatedWakelock());
        }
        for (int i9 = 0; i9 < this.wakelocks_.size(); i9++) {
            output.writeMessage(25, this.wakelocks_.get(i9));
        }
        for (int i10 = 0; i10 < this.wakeupAlarm_.size(); i10++) {
            output.writeMessage(26, this.wakeupAlarm_.get(i10));
        }
        if ((this.bitField0_ & 65536) == 65536) {
            output.writeMessage(27, getWifi());
        }
        if ((this.bitField0_ & 131072) == 131072) {
            output.writeMessage(28, getWifiMulticastWakelock());
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
        for (int i = 0; i < this.packages_.size(); i++) {
            size2 += CodedOutputStream.computeMessageSize(2, this.packages_.get(i));
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeMessageSize(3, getBluetoothController());
        }
        if ((this.bitField0_ & 4) == 4) {
            size2 += CodedOutputStream.computeMessageSize(4, getModemController());
        }
        if ((this.bitField0_ & 8) == 8) {
            size2 += CodedOutputStream.computeMessageSize(5, getWifiController());
        }
        if ((this.bitField0_ & 16) == 16) {
            size2 += CodedOutputStream.computeMessageSize(6, getBluetoothMisc());
        }
        if ((this.bitField0_ & 32) == 32) {
            size2 += CodedOutputStream.computeMessageSize(7, getCpu());
        }
        if ((this.bitField0_ & 64) == 64) {
            size2 += CodedOutputStream.computeMessageSize(8, getAudio());
        }
        if ((this.bitField0_ & 128) == 128) {
            size2 += CodedOutputStream.computeMessageSize(9, getCamera());
        }
        if ((this.bitField0_ & 256) == 256) {
            size2 += CodedOutputStream.computeMessageSize(10, getFlashlight());
        }
        if ((this.bitField0_ & 512) == 512) {
            size2 += CodedOutputStream.computeMessageSize(11, getForegroundActivity());
        }
        if ((this.bitField0_ & 1024) == 1024) {
            size2 += CodedOutputStream.computeMessageSize(12, getForegroundService());
        }
        if ((this.bitField0_ & 2048) == 2048) {
            size2 += CodedOutputStream.computeMessageSize(13, getVibrator());
        }
        if ((this.bitField0_ & 4096) == 4096) {
            size2 += CodedOutputStream.computeMessageSize(14, getVideo());
        }
        for (int i2 = 0; i2 < this.jobs_.size(); i2++) {
            size2 += CodedOutputStream.computeMessageSize(15, this.jobs_.get(i2));
        }
        for (int i3 = 0; i3 < this.jobCompletion_.size(); i3++) {
            size2 += CodedOutputStream.computeMessageSize(16, this.jobCompletion_.get(i3));
        }
        if ((this.bitField0_ & 8192) == 8192) {
            size2 += CodedOutputStream.computeMessageSize(17, getNetwork());
        }
        if ((this.bitField0_ & 16384) == 16384) {
            size2 += CodedOutputStream.computeMessageSize(18, getPowerUseItem());
        }
        for (int i4 = 0; i4 < this.process_.size(); i4++) {
            size2 += CodedOutputStream.computeMessageSize(19, this.process_.get(i4));
        }
        for (int i5 = 0; i5 < this.states_.size(); i5++) {
            size2 += CodedOutputStream.computeMessageSize(20, this.states_.get(i5));
        }
        for (int i6 = 0; i6 < this.sensors_.size(); i6++) {
            size2 += CodedOutputStream.computeMessageSize(21, this.sensors_.get(i6));
        }
        for (int i7 = 0; i7 < this.syncs_.size(); i7++) {
            size2 += CodedOutputStream.computeMessageSize(22, this.syncs_.get(i7));
        }
        for (int i8 = 0; i8 < this.userActivity_.size(); i8++) {
            size2 += CodedOutputStream.computeMessageSize(23, this.userActivity_.get(i8));
        }
        if ((this.bitField0_ & 32768) == 32768) {
            size2 += CodedOutputStream.computeMessageSize(24, getAggregatedWakelock());
        }
        for (int i9 = 0; i9 < this.wakelocks_.size(); i9++) {
            size2 += CodedOutputStream.computeMessageSize(25, this.wakelocks_.get(i9));
        }
        for (int i10 = 0; i10 < this.wakeupAlarm_.size(); i10++) {
            size2 += CodedOutputStream.computeMessageSize(26, this.wakeupAlarm_.get(i10));
        }
        if ((this.bitField0_ & 65536) == 65536) {
            size2 += CodedOutputStream.computeMessageSize(27, getWifi());
        }
        if ((this.bitField0_ & 131072) == 131072) {
            size2 += CodedOutputStream.computeMessageSize(28, getWifiMulticastWakelock());
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static UidProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (UidProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static UidProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (UidProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static UidProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (UidProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static UidProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (UidProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static UidProto parseFrom(InputStream input) throws IOException {
        return (UidProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static UidProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (UidProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static UidProto parseDelimitedFrom(InputStream input) throws IOException {
        return (UidProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static UidProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (UidProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static UidProto parseFrom(CodedInputStream input) throws IOException {
        return (UidProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static UidProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (UidProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(UidProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<UidProto, Builder> implements UidProtoOrBuilder {
        private Builder() {
            super(UidProto.DEFAULT_INSTANCE);
        }

        @Override // android.os.UidProtoOrBuilder
        public boolean hasUid() {
            return ((UidProto) this.instance).hasUid();
        }

        @Override // android.os.UidProtoOrBuilder
        public int getUid() {
            return ((UidProto) this.instance).getUid();
        }

        public Builder setUid(int value) {
            copyOnWrite();
            ((UidProto) this.instance).setUid(value);
            return this;
        }

        public Builder clearUid() {
            copyOnWrite();
            ((UidProto) this.instance).clearUid();
            return this;
        }

        @Override // android.os.UidProtoOrBuilder
        public List<Package> getPackagesList() {
            return Collections.unmodifiableList(((UidProto) this.instance).getPackagesList());
        }

        @Override // android.os.UidProtoOrBuilder
        public int getPackagesCount() {
            return ((UidProto) this.instance).getPackagesCount();
        }

        @Override // android.os.UidProtoOrBuilder
        public Package getPackages(int index) {
            return ((UidProto) this.instance).getPackages(index);
        }

        public Builder setPackages(int index, Package value) {
            copyOnWrite();
            ((UidProto) this.instance).setPackages((UidProto) index, (int) value);
            return this;
        }

        public Builder setPackages(int index, Package.Builder builderForValue) {
            copyOnWrite();
            ((UidProto) this.instance).setPackages((UidProto) index, (int) builderForValue);
            return this;
        }

        public Builder addPackages(Package value) {
            copyOnWrite();
            ((UidProto) this.instance).addPackages((UidProto) value);
            return this;
        }

        public Builder addPackages(int index, Package value) {
            copyOnWrite();
            ((UidProto) this.instance).addPackages((UidProto) index, (int) value);
            return this;
        }

        public Builder addPackages(Package.Builder builderForValue) {
            copyOnWrite();
            ((UidProto) this.instance).addPackages((UidProto) builderForValue);
            return this;
        }

        public Builder addPackages(int index, Package.Builder builderForValue) {
            copyOnWrite();
            ((UidProto) this.instance).addPackages((UidProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllPackages(Iterable<? extends Package> values) {
            copyOnWrite();
            ((UidProto) this.instance).addAllPackages(values);
            return this;
        }

        public Builder clearPackages() {
            copyOnWrite();
            ((UidProto) this.instance).clearPackages();
            return this;
        }

        public Builder removePackages(int index) {
            copyOnWrite();
            ((UidProto) this.instance).removePackages(index);
            return this;
        }

        @Override // android.os.UidProtoOrBuilder
        public boolean hasBluetoothController() {
            return ((UidProto) this.instance).hasBluetoothController();
        }

        @Override // android.os.UidProtoOrBuilder
        public ControllerActivityProto getBluetoothController() {
            return ((UidProto) this.instance).getBluetoothController();
        }

        public Builder setBluetoothController(ControllerActivityProto value) {
            copyOnWrite();
            ((UidProto) this.instance).setBluetoothController((UidProto) value);
            return this;
        }

        public Builder setBluetoothController(ControllerActivityProto.Builder builderForValue) {
            copyOnWrite();
            ((UidProto) this.instance).setBluetoothController((UidProto) builderForValue);
            return this;
        }

        public Builder mergeBluetoothController(ControllerActivityProto value) {
            copyOnWrite();
            ((UidProto) this.instance).mergeBluetoothController(value);
            return this;
        }

        public Builder clearBluetoothController() {
            copyOnWrite();
            ((UidProto) this.instance).clearBluetoothController();
            return this;
        }

        @Override // android.os.UidProtoOrBuilder
        public boolean hasModemController() {
            return ((UidProto) this.instance).hasModemController();
        }

        @Override // android.os.UidProtoOrBuilder
        public ControllerActivityProto getModemController() {
            return ((UidProto) this.instance).getModemController();
        }

        public Builder setModemController(ControllerActivityProto value) {
            copyOnWrite();
            ((UidProto) this.instance).setModemController((UidProto) value);
            return this;
        }

        public Builder setModemController(ControllerActivityProto.Builder builderForValue) {
            copyOnWrite();
            ((UidProto) this.instance).setModemController((UidProto) builderForValue);
            return this;
        }

        public Builder mergeModemController(ControllerActivityProto value) {
            copyOnWrite();
            ((UidProto) this.instance).mergeModemController(value);
            return this;
        }

        public Builder clearModemController() {
            copyOnWrite();
            ((UidProto) this.instance).clearModemController();
            return this;
        }

        @Override // android.os.UidProtoOrBuilder
        public boolean hasWifiController() {
            return ((UidProto) this.instance).hasWifiController();
        }

        @Override // android.os.UidProtoOrBuilder
        public ControllerActivityProto getWifiController() {
            return ((UidProto) this.instance).getWifiController();
        }

        public Builder setWifiController(ControllerActivityProto value) {
            copyOnWrite();
            ((UidProto) this.instance).setWifiController((UidProto) value);
            return this;
        }

        public Builder setWifiController(ControllerActivityProto.Builder builderForValue) {
            copyOnWrite();
            ((UidProto) this.instance).setWifiController((UidProto) builderForValue);
            return this;
        }

        public Builder mergeWifiController(ControllerActivityProto value) {
            copyOnWrite();
            ((UidProto) this.instance).mergeWifiController(value);
            return this;
        }

        public Builder clearWifiController() {
            copyOnWrite();
            ((UidProto) this.instance).clearWifiController();
            return this;
        }

        @Override // android.os.UidProtoOrBuilder
        public boolean hasBluetoothMisc() {
            return ((UidProto) this.instance).hasBluetoothMisc();
        }

        @Override // android.os.UidProtoOrBuilder
        public BluetoothMisc getBluetoothMisc() {
            return ((UidProto) this.instance).getBluetoothMisc();
        }

        public Builder setBluetoothMisc(BluetoothMisc value) {
            copyOnWrite();
            ((UidProto) this.instance).setBluetoothMisc((UidProto) value);
            return this;
        }

        public Builder setBluetoothMisc(BluetoothMisc.Builder builderForValue) {
            copyOnWrite();
            ((UidProto) this.instance).setBluetoothMisc((UidProto) builderForValue);
            return this;
        }

        public Builder mergeBluetoothMisc(BluetoothMisc value) {
            copyOnWrite();
            ((UidProto) this.instance).mergeBluetoothMisc(value);
            return this;
        }

        public Builder clearBluetoothMisc() {
            copyOnWrite();
            ((UidProto) this.instance).clearBluetoothMisc();
            return this;
        }

        @Override // android.os.UidProtoOrBuilder
        public boolean hasCpu() {
            return ((UidProto) this.instance).hasCpu();
        }

        @Override // android.os.UidProtoOrBuilder
        public Cpu getCpu() {
            return ((UidProto) this.instance).getCpu();
        }

        public Builder setCpu(Cpu value) {
            copyOnWrite();
            ((UidProto) this.instance).setCpu((UidProto) value);
            return this;
        }

        public Builder setCpu(Cpu.Builder builderForValue) {
            copyOnWrite();
            ((UidProto) this.instance).setCpu((UidProto) builderForValue);
            return this;
        }

        public Builder mergeCpu(Cpu value) {
            copyOnWrite();
            ((UidProto) this.instance).mergeCpu(value);
            return this;
        }

        public Builder clearCpu() {
            copyOnWrite();
            ((UidProto) this.instance).clearCpu();
            return this;
        }

        @Override // android.os.UidProtoOrBuilder
        public boolean hasAudio() {
            return ((UidProto) this.instance).hasAudio();
        }

        @Override // android.os.UidProtoOrBuilder
        public TimerProto getAudio() {
            return ((UidProto) this.instance).getAudio();
        }

        public Builder setAudio(TimerProto value) {
            copyOnWrite();
            ((UidProto) this.instance).setAudio((UidProto) value);
            return this;
        }

        public Builder setAudio(TimerProto.Builder builderForValue) {
            copyOnWrite();
            ((UidProto) this.instance).setAudio((UidProto) builderForValue);
            return this;
        }

        public Builder mergeAudio(TimerProto value) {
            copyOnWrite();
            ((UidProto) this.instance).mergeAudio(value);
            return this;
        }

        public Builder clearAudio() {
            copyOnWrite();
            ((UidProto) this.instance).clearAudio();
            return this;
        }

        @Override // android.os.UidProtoOrBuilder
        public boolean hasCamera() {
            return ((UidProto) this.instance).hasCamera();
        }

        @Override // android.os.UidProtoOrBuilder
        public TimerProto getCamera() {
            return ((UidProto) this.instance).getCamera();
        }

        public Builder setCamera(TimerProto value) {
            copyOnWrite();
            ((UidProto) this.instance).setCamera((UidProto) value);
            return this;
        }

        public Builder setCamera(TimerProto.Builder builderForValue) {
            copyOnWrite();
            ((UidProto) this.instance).setCamera((UidProto) builderForValue);
            return this;
        }

        public Builder mergeCamera(TimerProto value) {
            copyOnWrite();
            ((UidProto) this.instance).mergeCamera(value);
            return this;
        }

        public Builder clearCamera() {
            copyOnWrite();
            ((UidProto) this.instance).clearCamera();
            return this;
        }

        @Override // android.os.UidProtoOrBuilder
        public boolean hasFlashlight() {
            return ((UidProto) this.instance).hasFlashlight();
        }

        @Override // android.os.UidProtoOrBuilder
        public TimerProto getFlashlight() {
            return ((UidProto) this.instance).getFlashlight();
        }

        public Builder setFlashlight(TimerProto value) {
            copyOnWrite();
            ((UidProto) this.instance).setFlashlight((UidProto) value);
            return this;
        }

        public Builder setFlashlight(TimerProto.Builder builderForValue) {
            copyOnWrite();
            ((UidProto) this.instance).setFlashlight((UidProto) builderForValue);
            return this;
        }

        public Builder mergeFlashlight(TimerProto value) {
            copyOnWrite();
            ((UidProto) this.instance).mergeFlashlight(value);
            return this;
        }

        public Builder clearFlashlight() {
            copyOnWrite();
            ((UidProto) this.instance).clearFlashlight();
            return this;
        }

        @Override // android.os.UidProtoOrBuilder
        public boolean hasForegroundActivity() {
            return ((UidProto) this.instance).hasForegroundActivity();
        }

        @Override // android.os.UidProtoOrBuilder
        public TimerProto getForegroundActivity() {
            return ((UidProto) this.instance).getForegroundActivity();
        }

        public Builder setForegroundActivity(TimerProto value) {
            copyOnWrite();
            ((UidProto) this.instance).setForegroundActivity((UidProto) value);
            return this;
        }

        public Builder setForegroundActivity(TimerProto.Builder builderForValue) {
            copyOnWrite();
            ((UidProto) this.instance).setForegroundActivity((UidProto) builderForValue);
            return this;
        }

        public Builder mergeForegroundActivity(TimerProto value) {
            copyOnWrite();
            ((UidProto) this.instance).mergeForegroundActivity(value);
            return this;
        }

        public Builder clearForegroundActivity() {
            copyOnWrite();
            ((UidProto) this.instance).clearForegroundActivity();
            return this;
        }

        @Override // android.os.UidProtoOrBuilder
        public boolean hasForegroundService() {
            return ((UidProto) this.instance).hasForegroundService();
        }

        @Override // android.os.UidProtoOrBuilder
        public TimerProto getForegroundService() {
            return ((UidProto) this.instance).getForegroundService();
        }

        public Builder setForegroundService(TimerProto value) {
            copyOnWrite();
            ((UidProto) this.instance).setForegroundService((UidProto) value);
            return this;
        }

        public Builder setForegroundService(TimerProto.Builder builderForValue) {
            copyOnWrite();
            ((UidProto) this.instance).setForegroundService((UidProto) builderForValue);
            return this;
        }

        public Builder mergeForegroundService(TimerProto value) {
            copyOnWrite();
            ((UidProto) this.instance).mergeForegroundService(value);
            return this;
        }

        public Builder clearForegroundService() {
            copyOnWrite();
            ((UidProto) this.instance).clearForegroundService();
            return this;
        }

        @Override // android.os.UidProtoOrBuilder
        public boolean hasVibrator() {
            return ((UidProto) this.instance).hasVibrator();
        }

        @Override // android.os.UidProtoOrBuilder
        public TimerProto getVibrator() {
            return ((UidProto) this.instance).getVibrator();
        }

        public Builder setVibrator(TimerProto value) {
            copyOnWrite();
            ((UidProto) this.instance).setVibrator((UidProto) value);
            return this;
        }

        public Builder setVibrator(TimerProto.Builder builderForValue) {
            copyOnWrite();
            ((UidProto) this.instance).setVibrator((UidProto) builderForValue);
            return this;
        }

        public Builder mergeVibrator(TimerProto value) {
            copyOnWrite();
            ((UidProto) this.instance).mergeVibrator(value);
            return this;
        }

        public Builder clearVibrator() {
            copyOnWrite();
            ((UidProto) this.instance).clearVibrator();
            return this;
        }

        @Override // android.os.UidProtoOrBuilder
        public boolean hasVideo() {
            return ((UidProto) this.instance).hasVideo();
        }

        @Override // android.os.UidProtoOrBuilder
        public TimerProto getVideo() {
            return ((UidProto) this.instance).getVideo();
        }

        public Builder setVideo(TimerProto value) {
            copyOnWrite();
            ((UidProto) this.instance).setVideo((UidProto) value);
            return this;
        }

        public Builder setVideo(TimerProto.Builder builderForValue) {
            copyOnWrite();
            ((UidProto) this.instance).setVideo((UidProto) builderForValue);
            return this;
        }

        public Builder mergeVideo(TimerProto value) {
            copyOnWrite();
            ((UidProto) this.instance).mergeVideo(value);
            return this;
        }

        public Builder clearVideo() {
            copyOnWrite();
            ((UidProto) this.instance).clearVideo();
            return this;
        }

        @Override // android.os.UidProtoOrBuilder
        public List<Job> getJobsList() {
            return Collections.unmodifiableList(((UidProto) this.instance).getJobsList());
        }

        @Override // android.os.UidProtoOrBuilder
        public int getJobsCount() {
            return ((UidProto) this.instance).getJobsCount();
        }

        @Override // android.os.UidProtoOrBuilder
        public Job getJobs(int index) {
            return ((UidProto) this.instance).getJobs(index);
        }

        public Builder setJobs(int index, Job value) {
            copyOnWrite();
            ((UidProto) this.instance).setJobs((UidProto) index, (int) value);
            return this;
        }

        public Builder setJobs(int index, Job.Builder builderForValue) {
            copyOnWrite();
            ((UidProto) this.instance).setJobs((UidProto) index, (int) builderForValue);
            return this;
        }

        public Builder addJobs(Job value) {
            copyOnWrite();
            ((UidProto) this.instance).addJobs((UidProto) value);
            return this;
        }

        public Builder addJobs(int index, Job value) {
            copyOnWrite();
            ((UidProto) this.instance).addJobs((UidProto) index, (int) value);
            return this;
        }

        public Builder addJobs(Job.Builder builderForValue) {
            copyOnWrite();
            ((UidProto) this.instance).addJobs((UidProto) builderForValue);
            return this;
        }

        public Builder addJobs(int index, Job.Builder builderForValue) {
            copyOnWrite();
            ((UidProto) this.instance).addJobs((UidProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllJobs(Iterable<? extends Job> values) {
            copyOnWrite();
            ((UidProto) this.instance).addAllJobs(values);
            return this;
        }

        public Builder clearJobs() {
            copyOnWrite();
            ((UidProto) this.instance).clearJobs();
            return this;
        }

        public Builder removeJobs(int index) {
            copyOnWrite();
            ((UidProto) this.instance).removeJobs(index);
            return this;
        }

        @Override // android.os.UidProtoOrBuilder
        public List<JobCompletion> getJobCompletionList() {
            return Collections.unmodifiableList(((UidProto) this.instance).getJobCompletionList());
        }

        @Override // android.os.UidProtoOrBuilder
        public int getJobCompletionCount() {
            return ((UidProto) this.instance).getJobCompletionCount();
        }

        @Override // android.os.UidProtoOrBuilder
        public JobCompletion getJobCompletion(int index) {
            return ((UidProto) this.instance).getJobCompletion(index);
        }

        public Builder setJobCompletion(int index, JobCompletion value) {
            copyOnWrite();
            ((UidProto) this.instance).setJobCompletion((UidProto) index, (int) value);
            return this;
        }

        public Builder setJobCompletion(int index, JobCompletion.Builder builderForValue) {
            copyOnWrite();
            ((UidProto) this.instance).setJobCompletion((UidProto) index, (int) builderForValue);
            return this;
        }

        public Builder addJobCompletion(JobCompletion value) {
            copyOnWrite();
            ((UidProto) this.instance).addJobCompletion((UidProto) value);
            return this;
        }

        public Builder addJobCompletion(int index, JobCompletion value) {
            copyOnWrite();
            ((UidProto) this.instance).addJobCompletion((UidProto) index, (int) value);
            return this;
        }

        public Builder addJobCompletion(JobCompletion.Builder builderForValue) {
            copyOnWrite();
            ((UidProto) this.instance).addJobCompletion((UidProto) builderForValue);
            return this;
        }

        public Builder addJobCompletion(int index, JobCompletion.Builder builderForValue) {
            copyOnWrite();
            ((UidProto) this.instance).addJobCompletion((UidProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllJobCompletion(Iterable<? extends JobCompletion> values) {
            copyOnWrite();
            ((UidProto) this.instance).addAllJobCompletion(values);
            return this;
        }

        public Builder clearJobCompletion() {
            copyOnWrite();
            ((UidProto) this.instance).clearJobCompletion();
            return this;
        }

        public Builder removeJobCompletion(int index) {
            copyOnWrite();
            ((UidProto) this.instance).removeJobCompletion(index);
            return this;
        }

        @Override // android.os.UidProtoOrBuilder
        public boolean hasNetwork() {
            return ((UidProto) this.instance).hasNetwork();
        }

        @Override // android.os.UidProtoOrBuilder
        public Network getNetwork() {
            return ((UidProto) this.instance).getNetwork();
        }

        public Builder setNetwork(Network value) {
            copyOnWrite();
            ((UidProto) this.instance).setNetwork((UidProto) value);
            return this;
        }

        public Builder setNetwork(Network.Builder builderForValue) {
            copyOnWrite();
            ((UidProto) this.instance).setNetwork((UidProto) builderForValue);
            return this;
        }

        public Builder mergeNetwork(Network value) {
            copyOnWrite();
            ((UidProto) this.instance).mergeNetwork(value);
            return this;
        }

        public Builder clearNetwork() {
            copyOnWrite();
            ((UidProto) this.instance).clearNetwork();
            return this;
        }

        @Override // android.os.UidProtoOrBuilder
        public boolean hasPowerUseItem() {
            return ((UidProto) this.instance).hasPowerUseItem();
        }

        @Override // android.os.UidProtoOrBuilder
        public PowerUseItem getPowerUseItem() {
            return ((UidProto) this.instance).getPowerUseItem();
        }

        public Builder setPowerUseItem(PowerUseItem value) {
            copyOnWrite();
            ((UidProto) this.instance).setPowerUseItem((UidProto) value);
            return this;
        }

        public Builder setPowerUseItem(PowerUseItem.Builder builderForValue) {
            copyOnWrite();
            ((UidProto) this.instance).setPowerUseItem((UidProto) builderForValue);
            return this;
        }

        public Builder mergePowerUseItem(PowerUseItem value) {
            copyOnWrite();
            ((UidProto) this.instance).mergePowerUseItem(value);
            return this;
        }

        public Builder clearPowerUseItem() {
            copyOnWrite();
            ((UidProto) this.instance).clearPowerUseItem();
            return this;
        }

        @Override // android.os.UidProtoOrBuilder
        public List<Process> getProcessList() {
            return Collections.unmodifiableList(((UidProto) this.instance).getProcessList());
        }

        @Override // android.os.UidProtoOrBuilder
        public int getProcessCount() {
            return ((UidProto) this.instance).getProcessCount();
        }

        @Override // android.os.UidProtoOrBuilder
        public Process getProcess(int index) {
            return ((UidProto) this.instance).getProcess(index);
        }

        public Builder setProcess(int index, Process value) {
            copyOnWrite();
            ((UidProto) this.instance).setProcess((UidProto) index, (int) value);
            return this;
        }

        public Builder setProcess(int index, Process.Builder builderForValue) {
            copyOnWrite();
            ((UidProto) this.instance).setProcess((UidProto) index, (int) builderForValue);
            return this;
        }

        public Builder addProcess(Process value) {
            copyOnWrite();
            ((UidProto) this.instance).addProcess((UidProto) value);
            return this;
        }

        public Builder addProcess(int index, Process value) {
            copyOnWrite();
            ((UidProto) this.instance).addProcess((UidProto) index, (int) value);
            return this;
        }

        public Builder addProcess(Process.Builder builderForValue) {
            copyOnWrite();
            ((UidProto) this.instance).addProcess((UidProto) builderForValue);
            return this;
        }

        public Builder addProcess(int index, Process.Builder builderForValue) {
            copyOnWrite();
            ((UidProto) this.instance).addProcess((UidProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllProcess(Iterable<? extends Process> values) {
            copyOnWrite();
            ((UidProto) this.instance).addAllProcess(values);
            return this;
        }

        public Builder clearProcess() {
            copyOnWrite();
            ((UidProto) this.instance).clearProcess();
            return this;
        }

        public Builder removeProcess(int index) {
            copyOnWrite();
            ((UidProto) this.instance).removeProcess(index);
            return this;
        }

        @Override // android.os.UidProtoOrBuilder
        public List<StateTime> getStatesList() {
            return Collections.unmodifiableList(((UidProto) this.instance).getStatesList());
        }

        @Override // android.os.UidProtoOrBuilder
        public int getStatesCount() {
            return ((UidProto) this.instance).getStatesCount();
        }

        @Override // android.os.UidProtoOrBuilder
        public StateTime getStates(int index) {
            return ((UidProto) this.instance).getStates(index);
        }

        public Builder setStates(int index, StateTime value) {
            copyOnWrite();
            ((UidProto) this.instance).setStates((UidProto) index, (int) value);
            return this;
        }

        public Builder setStates(int index, StateTime.Builder builderForValue) {
            copyOnWrite();
            ((UidProto) this.instance).setStates((UidProto) index, (int) builderForValue);
            return this;
        }

        public Builder addStates(StateTime value) {
            copyOnWrite();
            ((UidProto) this.instance).addStates((UidProto) value);
            return this;
        }

        public Builder addStates(int index, StateTime value) {
            copyOnWrite();
            ((UidProto) this.instance).addStates((UidProto) index, (int) value);
            return this;
        }

        public Builder addStates(StateTime.Builder builderForValue) {
            copyOnWrite();
            ((UidProto) this.instance).addStates((UidProto) builderForValue);
            return this;
        }

        public Builder addStates(int index, StateTime.Builder builderForValue) {
            copyOnWrite();
            ((UidProto) this.instance).addStates((UidProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllStates(Iterable<? extends StateTime> values) {
            copyOnWrite();
            ((UidProto) this.instance).addAllStates(values);
            return this;
        }

        public Builder clearStates() {
            copyOnWrite();
            ((UidProto) this.instance).clearStates();
            return this;
        }

        public Builder removeStates(int index) {
            copyOnWrite();
            ((UidProto) this.instance).removeStates(index);
            return this;
        }

        @Override // android.os.UidProtoOrBuilder
        public List<Sensor> getSensorsList() {
            return Collections.unmodifiableList(((UidProto) this.instance).getSensorsList());
        }

        @Override // android.os.UidProtoOrBuilder
        public int getSensorsCount() {
            return ((UidProto) this.instance).getSensorsCount();
        }

        @Override // android.os.UidProtoOrBuilder
        public Sensor getSensors(int index) {
            return ((UidProto) this.instance).getSensors(index);
        }

        public Builder setSensors(int index, Sensor value) {
            copyOnWrite();
            ((UidProto) this.instance).setSensors((UidProto) index, (int) value);
            return this;
        }

        public Builder setSensors(int index, Sensor.Builder builderForValue) {
            copyOnWrite();
            ((UidProto) this.instance).setSensors((UidProto) index, (int) builderForValue);
            return this;
        }

        public Builder addSensors(Sensor value) {
            copyOnWrite();
            ((UidProto) this.instance).addSensors((UidProto) value);
            return this;
        }

        public Builder addSensors(int index, Sensor value) {
            copyOnWrite();
            ((UidProto) this.instance).addSensors((UidProto) index, (int) value);
            return this;
        }

        public Builder addSensors(Sensor.Builder builderForValue) {
            copyOnWrite();
            ((UidProto) this.instance).addSensors((UidProto) builderForValue);
            return this;
        }

        public Builder addSensors(int index, Sensor.Builder builderForValue) {
            copyOnWrite();
            ((UidProto) this.instance).addSensors((UidProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllSensors(Iterable<? extends Sensor> values) {
            copyOnWrite();
            ((UidProto) this.instance).addAllSensors(values);
            return this;
        }

        public Builder clearSensors() {
            copyOnWrite();
            ((UidProto) this.instance).clearSensors();
            return this;
        }

        public Builder removeSensors(int index) {
            copyOnWrite();
            ((UidProto) this.instance).removeSensors(index);
            return this;
        }

        @Override // android.os.UidProtoOrBuilder
        public List<Sync> getSyncsList() {
            return Collections.unmodifiableList(((UidProto) this.instance).getSyncsList());
        }

        @Override // android.os.UidProtoOrBuilder
        public int getSyncsCount() {
            return ((UidProto) this.instance).getSyncsCount();
        }

        @Override // android.os.UidProtoOrBuilder
        public Sync getSyncs(int index) {
            return ((UidProto) this.instance).getSyncs(index);
        }

        public Builder setSyncs(int index, Sync value) {
            copyOnWrite();
            ((UidProto) this.instance).setSyncs((UidProto) index, (int) value);
            return this;
        }

        public Builder setSyncs(int index, Sync.Builder builderForValue) {
            copyOnWrite();
            ((UidProto) this.instance).setSyncs((UidProto) index, (int) builderForValue);
            return this;
        }

        public Builder addSyncs(Sync value) {
            copyOnWrite();
            ((UidProto) this.instance).addSyncs((UidProto) value);
            return this;
        }

        public Builder addSyncs(int index, Sync value) {
            copyOnWrite();
            ((UidProto) this.instance).addSyncs((UidProto) index, (int) value);
            return this;
        }

        public Builder addSyncs(Sync.Builder builderForValue) {
            copyOnWrite();
            ((UidProto) this.instance).addSyncs((UidProto) builderForValue);
            return this;
        }

        public Builder addSyncs(int index, Sync.Builder builderForValue) {
            copyOnWrite();
            ((UidProto) this.instance).addSyncs((UidProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllSyncs(Iterable<? extends Sync> values) {
            copyOnWrite();
            ((UidProto) this.instance).addAllSyncs(values);
            return this;
        }

        public Builder clearSyncs() {
            copyOnWrite();
            ((UidProto) this.instance).clearSyncs();
            return this;
        }

        public Builder removeSyncs(int index) {
            copyOnWrite();
            ((UidProto) this.instance).removeSyncs(index);
            return this;
        }

        @Override // android.os.UidProtoOrBuilder
        public List<UserActivity> getUserActivityList() {
            return Collections.unmodifiableList(((UidProto) this.instance).getUserActivityList());
        }

        @Override // android.os.UidProtoOrBuilder
        public int getUserActivityCount() {
            return ((UidProto) this.instance).getUserActivityCount();
        }

        @Override // android.os.UidProtoOrBuilder
        public UserActivity getUserActivity(int index) {
            return ((UidProto) this.instance).getUserActivity(index);
        }

        public Builder setUserActivity(int index, UserActivity value) {
            copyOnWrite();
            ((UidProto) this.instance).setUserActivity((UidProto) index, (int) value);
            return this;
        }

        public Builder setUserActivity(int index, UserActivity.Builder builderForValue) {
            copyOnWrite();
            ((UidProto) this.instance).setUserActivity((UidProto) index, (int) builderForValue);
            return this;
        }

        public Builder addUserActivity(UserActivity value) {
            copyOnWrite();
            ((UidProto) this.instance).addUserActivity((UidProto) value);
            return this;
        }

        public Builder addUserActivity(int index, UserActivity value) {
            copyOnWrite();
            ((UidProto) this.instance).addUserActivity((UidProto) index, (int) value);
            return this;
        }

        public Builder addUserActivity(UserActivity.Builder builderForValue) {
            copyOnWrite();
            ((UidProto) this.instance).addUserActivity((UidProto) builderForValue);
            return this;
        }

        public Builder addUserActivity(int index, UserActivity.Builder builderForValue) {
            copyOnWrite();
            ((UidProto) this.instance).addUserActivity((UidProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllUserActivity(Iterable<? extends UserActivity> values) {
            copyOnWrite();
            ((UidProto) this.instance).addAllUserActivity(values);
            return this;
        }

        public Builder clearUserActivity() {
            copyOnWrite();
            ((UidProto) this.instance).clearUserActivity();
            return this;
        }

        public Builder removeUserActivity(int index) {
            copyOnWrite();
            ((UidProto) this.instance).removeUserActivity(index);
            return this;
        }

        @Override // android.os.UidProtoOrBuilder
        public boolean hasAggregatedWakelock() {
            return ((UidProto) this.instance).hasAggregatedWakelock();
        }

        @Override // android.os.UidProtoOrBuilder
        public AggregatedWakelock getAggregatedWakelock() {
            return ((UidProto) this.instance).getAggregatedWakelock();
        }

        public Builder setAggregatedWakelock(AggregatedWakelock value) {
            copyOnWrite();
            ((UidProto) this.instance).setAggregatedWakelock((UidProto) value);
            return this;
        }

        public Builder setAggregatedWakelock(AggregatedWakelock.Builder builderForValue) {
            copyOnWrite();
            ((UidProto) this.instance).setAggregatedWakelock((UidProto) builderForValue);
            return this;
        }

        public Builder mergeAggregatedWakelock(AggregatedWakelock value) {
            copyOnWrite();
            ((UidProto) this.instance).mergeAggregatedWakelock(value);
            return this;
        }

        public Builder clearAggregatedWakelock() {
            copyOnWrite();
            ((UidProto) this.instance).clearAggregatedWakelock();
            return this;
        }

        @Override // android.os.UidProtoOrBuilder
        public List<Wakelock> getWakelocksList() {
            return Collections.unmodifiableList(((UidProto) this.instance).getWakelocksList());
        }

        @Override // android.os.UidProtoOrBuilder
        public int getWakelocksCount() {
            return ((UidProto) this.instance).getWakelocksCount();
        }

        @Override // android.os.UidProtoOrBuilder
        public Wakelock getWakelocks(int index) {
            return ((UidProto) this.instance).getWakelocks(index);
        }

        public Builder setWakelocks(int index, Wakelock value) {
            copyOnWrite();
            ((UidProto) this.instance).setWakelocks((UidProto) index, (int) value);
            return this;
        }

        public Builder setWakelocks(int index, Wakelock.Builder builderForValue) {
            copyOnWrite();
            ((UidProto) this.instance).setWakelocks((UidProto) index, (int) builderForValue);
            return this;
        }

        public Builder addWakelocks(Wakelock value) {
            copyOnWrite();
            ((UidProto) this.instance).addWakelocks((UidProto) value);
            return this;
        }

        public Builder addWakelocks(int index, Wakelock value) {
            copyOnWrite();
            ((UidProto) this.instance).addWakelocks((UidProto) index, (int) value);
            return this;
        }

        public Builder addWakelocks(Wakelock.Builder builderForValue) {
            copyOnWrite();
            ((UidProto) this.instance).addWakelocks((UidProto) builderForValue);
            return this;
        }

        public Builder addWakelocks(int index, Wakelock.Builder builderForValue) {
            copyOnWrite();
            ((UidProto) this.instance).addWakelocks((UidProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllWakelocks(Iterable<? extends Wakelock> values) {
            copyOnWrite();
            ((UidProto) this.instance).addAllWakelocks(values);
            return this;
        }

        public Builder clearWakelocks() {
            copyOnWrite();
            ((UidProto) this.instance).clearWakelocks();
            return this;
        }

        public Builder removeWakelocks(int index) {
            copyOnWrite();
            ((UidProto) this.instance).removeWakelocks(index);
            return this;
        }

        @Override // android.os.UidProtoOrBuilder
        public List<WakeupAlarm> getWakeupAlarmList() {
            return Collections.unmodifiableList(((UidProto) this.instance).getWakeupAlarmList());
        }

        @Override // android.os.UidProtoOrBuilder
        public int getWakeupAlarmCount() {
            return ((UidProto) this.instance).getWakeupAlarmCount();
        }

        @Override // android.os.UidProtoOrBuilder
        public WakeupAlarm getWakeupAlarm(int index) {
            return ((UidProto) this.instance).getWakeupAlarm(index);
        }

        public Builder setWakeupAlarm(int index, WakeupAlarm value) {
            copyOnWrite();
            ((UidProto) this.instance).setWakeupAlarm((UidProto) index, (int) value);
            return this;
        }

        public Builder setWakeupAlarm(int index, WakeupAlarm.Builder builderForValue) {
            copyOnWrite();
            ((UidProto) this.instance).setWakeupAlarm((UidProto) index, (int) builderForValue);
            return this;
        }

        public Builder addWakeupAlarm(WakeupAlarm value) {
            copyOnWrite();
            ((UidProto) this.instance).addWakeupAlarm((UidProto) value);
            return this;
        }

        public Builder addWakeupAlarm(int index, WakeupAlarm value) {
            copyOnWrite();
            ((UidProto) this.instance).addWakeupAlarm((UidProto) index, (int) value);
            return this;
        }

        public Builder addWakeupAlarm(WakeupAlarm.Builder builderForValue) {
            copyOnWrite();
            ((UidProto) this.instance).addWakeupAlarm((UidProto) builderForValue);
            return this;
        }

        public Builder addWakeupAlarm(int index, WakeupAlarm.Builder builderForValue) {
            copyOnWrite();
            ((UidProto) this.instance).addWakeupAlarm((UidProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllWakeupAlarm(Iterable<? extends WakeupAlarm> values) {
            copyOnWrite();
            ((UidProto) this.instance).addAllWakeupAlarm(values);
            return this;
        }

        public Builder clearWakeupAlarm() {
            copyOnWrite();
            ((UidProto) this.instance).clearWakeupAlarm();
            return this;
        }

        public Builder removeWakeupAlarm(int index) {
            copyOnWrite();
            ((UidProto) this.instance).removeWakeupAlarm(index);
            return this;
        }

        @Override // android.os.UidProtoOrBuilder
        public boolean hasWifi() {
            return ((UidProto) this.instance).hasWifi();
        }

        @Override // android.os.UidProtoOrBuilder
        public Wifi getWifi() {
            return ((UidProto) this.instance).getWifi();
        }

        public Builder setWifi(Wifi value) {
            copyOnWrite();
            ((UidProto) this.instance).setWifi((UidProto) value);
            return this;
        }

        public Builder setWifi(Wifi.Builder builderForValue) {
            copyOnWrite();
            ((UidProto) this.instance).setWifi((UidProto) builderForValue);
            return this;
        }

        public Builder mergeWifi(Wifi value) {
            copyOnWrite();
            ((UidProto) this.instance).mergeWifi(value);
            return this;
        }

        public Builder clearWifi() {
            copyOnWrite();
            ((UidProto) this.instance).clearWifi();
            return this;
        }

        @Override // android.os.UidProtoOrBuilder
        public boolean hasWifiMulticastWakelock() {
            return ((UidProto) this.instance).hasWifiMulticastWakelock();
        }

        @Override // android.os.UidProtoOrBuilder
        public TimerProto getWifiMulticastWakelock() {
            return ((UidProto) this.instance).getWifiMulticastWakelock();
        }

        public Builder setWifiMulticastWakelock(TimerProto value) {
            copyOnWrite();
            ((UidProto) this.instance).setWifiMulticastWakelock((UidProto) value);
            return this;
        }

        public Builder setWifiMulticastWakelock(TimerProto.Builder builderForValue) {
            copyOnWrite();
            ((UidProto) this.instance).setWifiMulticastWakelock((UidProto) builderForValue);
            return this;
        }

        public Builder mergeWifiMulticastWakelock(TimerProto value) {
            copyOnWrite();
            ((UidProto) this.instance).mergeWifiMulticastWakelock(value);
            return this;
        }

        public Builder clearWifiMulticastWakelock() {
            copyOnWrite();
            ((UidProto) this.instance).clearWifiMulticastWakelock();
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new UidProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                this.packages_.makeImmutable();
                this.jobs_.makeImmutable();
                this.jobCompletion_.makeImmutable();
                this.process_.makeImmutable();
                this.states_.makeImmutable();
                this.sensors_.makeImmutable();
                this.syncs_.makeImmutable();
                this.userActivity_.makeImmutable();
                this.wakelocks_.makeImmutable();
                this.wakeupAlarm_.makeImmutable();
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                UidProto other = (UidProto) arg1;
                this.uid_ = visitor.visitInt(hasUid(), this.uid_, other.hasUid(), other.uid_);
                this.packages_ = visitor.visitList(this.packages_, other.packages_);
                this.bluetoothController_ = (ControllerActivityProto) visitor.visitMessage(this.bluetoothController_, other.bluetoothController_);
                this.modemController_ = (ControllerActivityProto) visitor.visitMessage(this.modemController_, other.modemController_);
                this.wifiController_ = (ControllerActivityProto) visitor.visitMessage(this.wifiController_, other.wifiController_);
                this.bluetoothMisc_ = (BluetoothMisc) visitor.visitMessage(this.bluetoothMisc_, other.bluetoothMisc_);
                this.cpu_ = (Cpu) visitor.visitMessage(this.cpu_, other.cpu_);
                this.audio_ = (TimerProto) visitor.visitMessage(this.audio_, other.audio_);
                this.camera_ = (TimerProto) visitor.visitMessage(this.camera_, other.camera_);
                this.flashlight_ = (TimerProto) visitor.visitMessage(this.flashlight_, other.flashlight_);
                this.foregroundActivity_ = (TimerProto) visitor.visitMessage(this.foregroundActivity_, other.foregroundActivity_);
                this.foregroundService_ = (TimerProto) visitor.visitMessage(this.foregroundService_, other.foregroundService_);
                this.vibrator_ = (TimerProto) visitor.visitMessage(this.vibrator_, other.vibrator_);
                this.video_ = (TimerProto) visitor.visitMessage(this.video_, other.video_);
                this.jobs_ = visitor.visitList(this.jobs_, other.jobs_);
                this.jobCompletion_ = visitor.visitList(this.jobCompletion_, other.jobCompletion_);
                this.network_ = (Network) visitor.visitMessage(this.network_, other.network_);
                this.powerUseItem_ = (PowerUseItem) visitor.visitMessage(this.powerUseItem_, other.powerUseItem_);
                this.process_ = visitor.visitList(this.process_, other.process_);
                this.states_ = visitor.visitList(this.states_, other.states_);
                this.sensors_ = visitor.visitList(this.sensors_, other.sensors_);
                this.syncs_ = visitor.visitList(this.syncs_, other.syncs_);
                this.userActivity_ = visitor.visitList(this.userActivity_, other.userActivity_);
                this.aggregatedWakelock_ = (AggregatedWakelock) visitor.visitMessage(this.aggregatedWakelock_, other.aggregatedWakelock_);
                this.wakelocks_ = visitor.visitList(this.wakelocks_, other.wakelocks_);
                this.wakeupAlarm_ = visitor.visitList(this.wakeupAlarm_, other.wakeupAlarm_);
                this.wifi_ = (Wifi) visitor.visitMessage(this.wifi_, other.wifi_);
                this.wifiMulticastWakelock_ = (TimerProto) visitor.visitMessage(this.wifiMulticastWakelock_, other.wifiMulticastWakelock_);
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
                                this.uid_ = input.readInt32();
                                break;
                            case 18:
                                if (!this.packages_.isModifiable()) {
                                    this.packages_ = GeneratedMessageLite.mutableCopy(this.packages_);
                                }
                                this.packages_.add((Package) input.readMessage(Package.parser(), extensionRegistry));
                                break;
                            case 26:
                                ControllerActivityProto.Builder subBuilder = null;
                                if ((this.bitField0_ & 2) == 2) {
                                    subBuilder = (ControllerActivityProto.Builder) this.bluetoothController_.toBuilder();
                                }
                                this.bluetoothController_ = (ControllerActivityProto) input.readMessage(ControllerActivityProto.parser(), extensionRegistry);
                                if (subBuilder != null) {
                                    subBuilder.mergeFrom((GeneratedMessageLite) this.bluetoothController_);
                                    this.bluetoothController_ = (ControllerActivityProto) subBuilder.buildPartial();
                                }
                                this.bitField0_ |= 2;
                                break;
                            case 34:
                                ControllerActivityProto.Builder subBuilder2 = null;
                                if ((this.bitField0_ & 4) == 4) {
                                    subBuilder2 = (ControllerActivityProto.Builder) this.modemController_.toBuilder();
                                }
                                this.modemController_ = (ControllerActivityProto) input.readMessage(ControllerActivityProto.parser(), extensionRegistry);
                                if (subBuilder2 != null) {
                                    subBuilder2.mergeFrom((GeneratedMessageLite) this.modemController_);
                                    this.modemController_ = (ControllerActivityProto) subBuilder2.buildPartial();
                                }
                                this.bitField0_ |= 4;
                                break;
                            case 42:
                                ControllerActivityProto.Builder subBuilder3 = null;
                                if ((this.bitField0_ & 8) == 8) {
                                    subBuilder3 = (ControllerActivityProto.Builder) this.wifiController_.toBuilder();
                                }
                                this.wifiController_ = (ControllerActivityProto) input.readMessage(ControllerActivityProto.parser(), extensionRegistry);
                                if (subBuilder3 != null) {
                                    subBuilder3.mergeFrom((GeneratedMessageLite) this.wifiController_);
                                    this.wifiController_ = (ControllerActivityProto) subBuilder3.buildPartial();
                                }
                                this.bitField0_ |= 8;
                                break;
                            case 50:
                                BluetoothMisc.Builder subBuilder4 = null;
                                if ((this.bitField0_ & 16) == 16) {
                                    subBuilder4 = (BluetoothMisc.Builder) this.bluetoothMisc_.toBuilder();
                                }
                                this.bluetoothMisc_ = (BluetoothMisc) input.readMessage(BluetoothMisc.parser(), extensionRegistry);
                                if (subBuilder4 != null) {
                                    subBuilder4.mergeFrom((GeneratedMessageLite) this.bluetoothMisc_);
                                    this.bluetoothMisc_ = (BluetoothMisc) subBuilder4.buildPartial();
                                }
                                this.bitField0_ |= 16;
                                break;
                            case 58:
                                Cpu.Builder subBuilder5 = null;
                                if ((this.bitField0_ & 32) == 32) {
                                    subBuilder5 = (Cpu.Builder) this.cpu_.toBuilder();
                                }
                                this.cpu_ = (Cpu) input.readMessage(Cpu.parser(), extensionRegistry);
                                if (subBuilder5 != null) {
                                    subBuilder5.mergeFrom((GeneratedMessageLite) this.cpu_);
                                    this.cpu_ = (Cpu) subBuilder5.buildPartial();
                                }
                                this.bitField0_ |= 32;
                                break;
                            case 66:
                                TimerProto.Builder subBuilder6 = null;
                                if ((this.bitField0_ & 64) == 64) {
                                    subBuilder6 = (TimerProto.Builder) this.audio_.toBuilder();
                                }
                                this.audio_ = (TimerProto) input.readMessage(TimerProto.parser(), extensionRegistry);
                                if (subBuilder6 != null) {
                                    subBuilder6.mergeFrom((GeneratedMessageLite) this.audio_);
                                    this.audio_ = (TimerProto) subBuilder6.buildPartial();
                                }
                                this.bitField0_ |= 64;
                                break;
                            case 74:
                                TimerProto.Builder subBuilder7 = null;
                                if ((this.bitField0_ & 128) == 128) {
                                    subBuilder7 = (TimerProto.Builder) this.camera_.toBuilder();
                                }
                                this.camera_ = (TimerProto) input.readMessage(TimerProto.parser(), extensionRegistry);
                                if (subBuilder7 != null) {
                                    subBuilder7.mergeFrom((GeneratedMessageLite) this.camera_);
                                    this.camera_ = (TimerProto) subBuilder7.buildPartial();
                                }
                                this.bitField0_ |= 128;
                                break;
                            case 82:
                                TimerProto.Builder subBuilder8 = null;
                                if ((this.bitField0_ & 256) == 256) {
                                    subBuilder8 = (TimerProto.Builder) this.flashlight_.toBuilder();
                                }
                                this.flashlight_ = (TimerProto) input.readMessage(TimerProto.parser(), extensionRegistry);
                                if (subBuilder8 != null) {
                                    subBuilder8.mergeFrom((GeneratedMessageLite) this.flashlight_);
                                    this.flashlight_ = (TimerProto) subBuilder8.buildPartial();
                                }
                                this.bitField0_ |= 256;
                                break;
                            case 90:
                                TimerProto.Builder subBuilder9 = null;
                                if ((this.bitField0_ & 512) == 512) {
                                    subBuilder9 = (TimerProto.Builder) this.foregroundActivity_.toBuilder();
                                }
                                this.foregroundActivity_ = (TimerProto) input.readMessage(TimerProto.parser(), extensionRegistry);
                                if (subBuilder9 != null) {
                                    subBuilder9.mergeFrom((GeneratedMessageLite) this.foregroundActivity_);
                                    this.foregroundActivity_ = (TimerProto) subBuilder9.buildPartial();
                                }
                                this.bitField0_ |= 512;
                                break;
                            case 98:
                                TimerProto.Builder subBuilder10 = null;
                                if ((this.bitField0_ & 1024) == 1024) {
                                    subBuilder10 = (TimerProto.Builder) this.foregroundService_.toBuilder();
                                }
                                this.foregroundService_ = (TimerProto) input.readMessage(TimerProto.parser(), extensionRegistry);
                                if (subBuilder10 != null) {
                                    subBuilder10.mergeFrom((GeneratedMessageLite) this.foregroundService_);
                                    this.foregroundService_ = (TimerProto) subBuilder10.buildPartial();
                                }
                                this.bitField0_ |= 1024;
                                break;
                            case 106:
                                TimerProto.Builder subBuilder11 = null;
                                if ((this.bitField0_ & 2048) == 2048) {
                                    subBuilder11 = (TimerProto.Builder) this.vibrator_.toBuilder();
                                }
                                this.vibrator_ = (TimerProto) input.readMessage(TimerProto.parser(), extensionRegistry);
                                if (subBuilder11 != null) {
                                    subBuilder11.mergeFrom((GeneratedMessageLite) this.vibrator_);
                                    this.vibrator_ = (TimerProto) subBuilder11.buildPartial();
                                }
                                this.bitField0_ |= 2048;
                                break;
                            case 114:
                                TimerProto.Builder subBuilder12 = null;
                                if ((this.bitField0_ & 4096) == 4096) {
                                    subBuilder12 = (TimerProto.Builder) this.video_.toBuilder();
                                }
                                this.video_ = (TimerProto) input.readMessage(TimerProto.parser(), extensionRegistry);
                                if (subBuilder12 != null) {
                                    subBuilder12.mergeFrom((GeneratedMessageLite) this.video_);
                                    this.video_ = (TimerProto) subBuilder12.buildPartial();
                                }
                                this.bitField0_ |= 4096;
                                break;
                            case 122:
                                if (!this.jobs_.isModifiable()) {
                                    this.jobs_ = GeneratedMessageLite.mutableCopy(this.jobs_);
                                }
                                this.jobs_.add((Job) input.readMessage(Job.parser(), extensionRegistry));
                                break;
                            case 130:
                                if (!this.jobCompletion_.isModifiable()) {
                                    this.jobCompletion_ = GeneratedMessageLite.mutableCopy(this.jobCompletion_);
                                }
                                this.jobCompletion_.add((JobCompletion) input.readMessage(JobCompletion.parser(), extensionRegistry));
                                break;
                            case 138:
                                Network.Builder subBuilder13 = null;
                                if ((this.bitField0_ & 8192) == 8192) {
                                    subBuilder13 = (Network.Builder) this.network_.toBuilder();
                                }
                                this.network_ = (Network) input.readMessage(Network.parser(), extensionRegistry);
                                if (subBuilder13 != null) {
                                    subBuilder13.mergeFrom((GeneratedMessageLite) this.network_);
                                    this.network_ = (Network) subBuilder13.buildPartial();
                                }
                                this.bitField0_ |= 8192;
                                break;
                            case 146:
                                PowerUseItem.Builder subBuilder14 = null;
                                if ((this.bitField0_ & 16384) == 16384) {
                                    subBuilder14 = (PowerUseItem.Builder) this.powerUseItem_.toBuilder();
                                }
                                this.powerUseItem_ = (PowerUseItem) input.readMessage(PowerUseItem.parser(), extensionRegistry);
                                if (subBuilder14 != null) {
                                    subBuilder14.mergeFrom((GeneratedMessageLite) this.powerUseItem_);
                                    this.powerUseItem_ = (PowerUseItem) subBuilder14.buildPartial();
                                }
                                this.bitField0_ |= 16384;
                                break;
                            case 154:
                                if (!this.process_.isModifiable()) {
                                    this.process_ = GeneratedMessageLite.mutableCopy(this.process_);
                                }
                                this.process_.add((Process) input.readMessage(Process.parser(), extensionRegistry));
                                break;
                            case 162:
                                if (!this.states_.isModifiable()) {
                                    this.states_ = GeneratedMessageLite.mutableCopy(this.states_);
                                }
                                this.states_.add((StateTime) input.readMessage(StateTime.parser(), extensionRegistry));
                                break;
                            case 170:
                                if (!this.sensors_.isModifiable()) {
                                    this.sensors_ = GeneratedMessageLite.mutableCopy(this.sensors_);
                                }
                                this.sensors_.add((Sensor) input.readMessage(Sensor.parser(), extensionRegistry));
                                break;
                            case 178:
                                if (!this.syncs_.isModifiable()) {
                                    this.syncs_ = GeneratedMessageLite.mutableCopy(this.syncs_);
                                }
                                this.syncs_.add((Sync) input.readMessage(Sync.parser(), extensionRegistry));
                                break;
                            case AtomsProto.Atom.TOMB_STONE_OCCURRED_FIELD_NUMBER /*{ENCODED_INT: 186}*/:
                                if (!this.userActivity_.isModifiable()) {
                                    this.userActivity_ = GeneratedMessageLite.mutableCopy(this.userActivity_);
                                }
                                this.userActivity_.add((UserActivity) input.readMessage(UserActivity.parser(), extensionRegistry));
                                break;
                            case AtomsProto.Atom.MEDIAMETRICS_AUDIOTRACK_REPORTED_FIELD_NUMBER /*{ENCODED_INT: 194}*/:
                                AggregatedWakelock.Builder subBuilder15 = null;
                                if ((this.bitField0_ & 32768) == 32768) {
                                    subBuilder15 = (AggregatedWakelock.Builder) this.aggregatedWakelock_.toBuilder();
                                }
                                this.aggregatedWakelock_ = (AggregatedWakelock) input.readMessage(AggregatedWakelock.parser(), extensionRegistry);
                                if (subBuilder15 != null) {
                                    subBuilder15.mergeFrom((GeneratedMessageLite) this.aggregatedWakelock_);
                                    this.aggregatedWakelock_ = (AggregatedWakelock) subBuilder15.buildPartial();
                                }
                                this.bitField0_ |= 32768;
                                break;
                            case PROCESS_STATS_SUMMARY_VALUE:
                                if (!this.wakelocks_.isModifiable()) {
                                    this.wakelocks_ = GeneratedMessageLite.mutableCopy(this.wakelocks_);
                                }
                                this.wakelocks_.add((Wakelock) input.readMessage(Wakelock.parser(), extensionRegistry));
                                break;
                            case AtomsProto.Atom.LOCATION_MANAGER_API_USAGE_REPORTED_FIELD_NUMBER /*{ENCODED_INT: 210}*/:
                                if (!this.wakeupAlarm_.isModifiable()) {
                                    this.wakeupAlarm_ = GeneratedMessageLite.mutableCopy(this.wakeupAlarm_);
                                }
                                this.wakeupAlarm_.add((WakeupAlarm) input.readMessage(WakeupAlarm.parser(), extensionRegistry));
                                break;
                            case AtomsProto.Atom.PERMISSION_APPS_FRAGMENT_VIEWED_FIELD_NUMBER /*{ENCODED_INT: 218}*/:
                                Wifi.Builder subBuilder16 = null;
                                if ((this.bitField0_ & 65536) == 65536) {
                                    subBuilder16 = (Wifi.Builder) this.wifi_.toBuilder();
                                }
                                this.wifi_ = (Wifi) input.readMessage(Wifi.parser(), extensionRegistry);
                                if (subBuilder16 != null) {
                                    subBuilder16.mergeFrom((GeneratedMessageLite) this.wifi_);
                                    this.wifi_ = (Wifi) subBuilder16.buildPartial();
                                }
                                this.bitField0_ |= 65536;
                                break;
                            case ACTION_SEARCH_RESULTS_VALUE:
                                TimerProto.Builder subBuilder17 = null;
                                if ((this.bitField0_ & 131072) == 131072) {
                                    subBuilder17 = (TimerProto.Builder) this.wifiMulticastWakelock_.toBuilder();
                                }
                                this.wifiMulticastWakelock_ = (TimerProto) input.readMessage(TimerProto.parser(), extensionRegistry);
                                if (subBuilder17 != null) {
                                    subBuilder17.mergeFrom((GeneratedMessageLite) this.wifiMulticastWakelock_);
                                    this.wifiMulticastWakelock_ = (TimerProto) subBuilder17.buildPartial();
                                }
                                this.bitField0_ |= 131072;
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
                    synchronized (UidProto.class) {
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

    public static UidProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<UidProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
