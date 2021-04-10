package android.os;

import android.os.ControllerActivityProto;
import android.os.TimerProto;
import android.telephony.NetworkTypeEnum;
import android.telephony.SignalStrengthEnum;
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

public final class SystemProto extends GeneratedMessageLite<SystemProto, Builder> implements SystemProtoOrBuilder {
    public static final int BATTERY_DISCHARGE_FIELD_NUMBER = 2;
    public static final int BATTERY_FIELD_NUMBER = 1;
    public static final int CHARGE_STEP_FIELD_NUMBER = 5;
    public static final int CHARGE_TIME_REMAINING_MS_FIELD_NUMBER = 3;
    public static final int CPU_FREQUENCY_FIELD_NUMBER = 7;
    public static final int DATA_CONNECTION_FIELD_NUMBER = 8;
    private static final SystemProto DEFAULT_INSTANCE = new SystemProto();
    public static final int DISCHARGE_STEP_FIELD_NUMBER = 6;
    public static final int DISCHARGE_TIME_REMAINING_MS_FIELD_NUMBER = 4;
    public static final int GLOBAL_BLUETOOTH_CONTROLLER_FIELD_NUMBER = 9;
    public static final int GLOBAL_MODEM_CONTROLLER_FIELD_NUMBER = 10;
    public static final int GLOBAL_NETWORK_FIELD_NUMBER = 12;
    public static final int GLOBAL_WIFI_CONTROLLER_FIELD_NUMBER = 11;
    public static final int GLOBAL_WIFI_FIELD_NUMBER = 13;
    public static final int KERNEL_WAKELOCK_FIELD_NUMBER = 14;
    public static final int MISC_FIELD_NUMBER = 15;
    private static volatile Parser<SystemProto> PARSER = null;
    public static final int PHONE_SIGNAL_STRENGTH_FIELD_NUMBER = 16;
    public static final int POWER_USE_ITEM_FIELD_NUMBER = 17;
    public static final int POWER_USE_SUMMARY_FIELD_NUMBER = 18;
    public static final int RESOURCE_POWER_MANAGER_FIELD_NUMBER = 19;
    public static final int SCREEN_BRIGHTNESS_FIELD_NUMBER = 20;
    public static final int SIGNAL_SCANNING_FIELD_NUMBER = 21;
    public static final int WAKEUP_REASON_FIELD_NUMBER = 22;
    public static final int WIFI_MULTICAST_WAKELOCK_TOTAL_FIELD_NUMBER = 23;
    public static final int WIFI_SIGNAL_STRENGTH_FIELD_NUMBER = 24;
    public static final int WIFI_STATE_FIELD_NUMBER = 25;
    public static final int WIFI_SUPPLICANT_STATE_FIELD_NUMBER = 26;
    private BatteryDischarge batteryDischarge_;
    private Battery battery_;
    private int bitField0_;
    private Internal.ProtobufList<BatteryLevelStep> chargeStep_ = emptyProtobufList();
    private Internal.LongList cpuFrequency_ = emptyLongList();
    private Internal.ProtobufList<DataConnection> dataConnection_ = emptyProtobufList();
    private Internal.ProtobufList<BatteryLevelStep> dischargeStep_ = emptyProtobufList();
    private ControllerActivityProto globalBluetoothController_;
    private ControllerActivityProto globalModemController_;
    private GlobalNetwork globalNetwork_;
    private ControllerActivityProto globalWifiController_;
    private GlobalWifi globalWifi_;
    private Internal.ProtobufList<KernelWakelock> kernelWakelock_ = emptyProtobufList();
    private Misc misc_;
    private Internal.ProtobufList<PhoneSignalStrength> phoneSignalStrength_ = emptyProtobufList();
    private Internal.ProtobufList<PowerUseItem> powerUseItem_ = emptyProtobufList();
    private PowerUseSummary powerUseSummary_;
    private Internal.ProtobufList<ResourcePowerManager> resourcePowerManager_ = emptyProtobufList();
    private Internal.ProtobufList<ScreenBrightness> screenBrightness_ = emptyProtobufList();
    private TimerProto signalScanning_;
    private int timeRemainingCase_ = 0;
    private Object timeRemaining_;
    private Internal.ProtobufList<WakeupReason> wakeupReason_ = emptyProtobufList();
    private WifiMulticastWakelockTotal wifiMulticastWakelockTotal_;
    private Internal.ProtobufList<WifiSignalStrength> wifiSignalStrength_ = emptyProtobufList();
    private Internal.ProtobufList<WifiState> wifiState_ = emptyProtobufList();
    private Internal.ProtobufList<WifiSupplicantState> wifiSupplicantState_ = emptyProtobufList();

    public interface BatteryDischargeOrBuilder extends MessageLiteOrBuilder {
        int getLowerBoundSinceCharge();

        int getScreenDozeSinceCharge();

        int getScreenOffSinceCharge();

        int getScreenOnSinceCharge();

        long getTotalMah();

        long getTotalMahDeepDoze();

        long getTotalMahLightDoze();

        long getTotalMahScreenDoze();

        long getTotalMahScreenOff();

        int getUpperBoundSinceCharge();

        boolean hasLowerBoundSinceCharge();

        boolean hasScreenDozeSinceCharge();

        boolean hasScreenOffSinceCharge();

        boolean hasScreenOnSinceCharge();

        boolean hasTotalMah();

        boolean hasTotalMahDeepDoze();

        boolean hasTotalMahLightDoze();

        boolean hasTotalMahScreenDoze();

        boolean hasTotalMahScreenOff();

        boolean hasUpperBoundSinceCharge();
    }

    public interface BatteryLevelStepOrBuilder extends MessageLiteOrBuilder {
        BatteryLevelStep.DisplayState getDisplayState();

        long getDurationMs();

        BatteryLevelStep.IdleMode getIdleMode();

        int getLevel();

        BatteryLevelStep.PowerSaveMode getPowerSaveMode();

        boolean hasDisplayState();

        boolean hasDurationMs();

        boolean hasIdleMode();

        boolean hasLevel();

        boolean hasPowerSaveMode();
    }

    public interface BatteryOrBuilder extends MessageLiteOrBuilder {
        long getBatteryRealtimeMs();

        long getBatteryUptimeMs();

        long getEstimatedBatteryCapacityMah();

        long getMaxLearnedBatteryCapacityUah();

        long getMinLearnedBatteryCapacityUah();

        long getScreenDozeDurationMs();

        long getScreenOffRealtimeMs();

        long getScreenOffUptimeMs();

        long getStartClockTimeMs();

        long getStartCount();

        long getTotalRealtimeMs();

        long getTotalUptimeMs();

        boolean hasBatteryRealtimeMs();

        boolean hasBatteryUptimeMs();

        boolean hasEstimatedBatteryCapacityMah();

        boolean hasMaxLearnedBatteryCapacityUah();

        boolean hasMinLearnedBatteryCapacityUah();

        boolean hasScreenDozeDurationMs();

        boolean hasScreenOffRealtimeMs();

        boolean hasScreenOffUptimeMs();

        boolean hasStartClockTimeMs();

        boolean hasStartCount();

        boolean hasTotalRealtimeMs();

        boolean hasTotalUptimeMs();
    }

    public interface DataConnectionOrBuilder extends MessageLiteOrBuilder {
        boolean getIsNone();

        NetworkTypeEnum getName();

        TimerProto getTotal();

        DataConnection.TypeCase getTypeCase();

        boolean hasIsNone();

        boolean hasName();

        boolean hasTotal();
    }

    public interface GlobalNetworkOrBuilder extends MessageLiteOrBuilder {
        long getBtBytesRx();

        long getBtBytesTx();

        long getMobileBytesRx();

        long getMobileBytesTx();

        long getMobilePacketsRx();

        long getMobilePacketsTx();

        long getWifiBytesRx();

        long getWifiBytesTx();

        long getWifiPacketsRx();

        long getWifiPacketsTx();

        boolean hasBtBytesRx();

        boolean hasBtBytesTx();

        boolean hasMobileBytesRx();

        boolean hasMobileBytesTx();

        boolean hasMobilePacketsRx();

        boolean hasMobilePacketsTx();

        boolean hasWifiBytesRx();

        boolean hasWifiBytesTx();

        boolean hasWifiPacketsRx();

        boolean hasWifiPacketsTx();
    }

    public interface GlobalWifiOrBuilder extends MessageLiteOrBuilder {
        long getOnDurationMs();

        long getRunningDurationMs();

        boolean hasOnDurationMs();

        boolean hasRunningDurationMs();
    }

    public interface KernelWakelockOrBuilder extends MessageLiteOrBuilder {
        String getName();

        ByteString getNameBytes();

        TimerProto getTotal();

        boolean hasName();

        boolean hasTotal();
    }

    public interface MiscOrBuilder extends MessageLiteOrBuilder {
        long getBatterySaverModeEnabledDurationMs();

        int getDeepDozeCount();

        long getDeepDozeEnabledDurationMs();

        int getDeepDozeIdlingCount();

        long getDeepDozeIdlingDurationMs();

        long getFullWakelockTotalDurationMs();

        long getInteractiveDurationMs();

        int getLightDozeCount();

        long getLightDozeEnabledDurationMs();

        int getLightDozeIdlingCount();

        long getLightDozeIdlingDurationMs();

        long getLongestDeepDozeDurationMs();

        long getLongestLightDozeDurationMs();

        long getMobileRadioActiveAdjustedTimeMs();

        int getMobileRadioActiveCount();

        long getMobileRadioActiveDurationMs();

        int getMobileRadioActiveUnknownDurationMs();

        int getNumConnectivityChanges();

        long getPartialWakelockTotalDurationMs();

        long getPhoneOnDurationMs();

        long getScreenOnDurationMs();

        boolean hasBatterySaverModeEnabledDurationMs();

        boolean hasDeepDozeCount();

        boolean hasDeepDozeEnabledDurationMs();

        boolean hasDeepDozeIdlingCount();

        boolean hasDeepDozeIdlingDurationMs();

        boolean hasFullWakelockTotalDurationMs();

        boolean hasInteractiveDurationMs();

        boolean hasLightDozeCount();

        boolean hasLightDozeEnabledDurationMs();

        boolean hasLightDozeIdlingCount();

        boolean hasLightDozeIdlingDurationMs();

        boolean hasLongestDeepDozeDurationMs();

        boolean hasLongestLightDozeDurationMs();

        boolean hasMobileRadioActiveAdjustedTimeMs();

        boolean hasMobileRadioActiveCount();

        boolean hasMobileRadioActiveDurationMs();

        boolean hasMobileRadioActiveUnknownDurationMs();

        boolean hasNumConnectivityChanges();

        boolean hasPartialWakelockTotalDurationMs();

        boolean hasPhoneOnDurationMs();

        boolean hasScreenOnDurationMs();
    }

    public interface PhoneSignalStrengthOrBuilder extends MessageLiteOrBuilder {
        SignalStrengthEnum getName();

        TimerProto getTotal();

        boolean hasName();

        boolean hasTotal();
    }

    public interface PowerUseItemOrBuilder extends MessageLiteOrBuilder {
        double getComputedPowerMah();

        PowerUseItem.Sipper getName();

        double getProportionalSmearMah();

        double getScreenPowerMah();

        boolean getShouldHide();

        int getUid();

        boolean hasComputedPowerMah();

        boolean hasName();

        boolean hasProportionalSmearMah();

        boolean hasScreenPowerMah();

        boolean hasShouldHide();

        boolean hasUid();
    }

    public interface PowerUseSummaryOrBuilder extends MessageLiteOrBuilder {
        double getBatteryCapacityMah();

        double getComputedPowerMah();

        double getMaxDrainedPowerMah();

        double getMinDrainedPowerMah();

        boolean hasBatteryCapacityMah();

        boolean hasComputedPowerMah();

        boolean hasMaxDrainedPowerMah();

        boolean hasMinDrainedPowerMah();
    }

    public interface ResourcePowerManagerOrBuilder extends MessageLiteOrBuilder {
        String getName();

        ByteString getNameBytes();

        TimerProto getScreenOff();

        TimerProto getTotal();

        boolean hasName();

        boolean hasScreenOff();

        boolean hasTotal();
    }

    public interface ScreenBrightnessOrBuilder extends MessageLiteOrBuilder {
        ScreenBrightness.Name getName();

        TimerProto getTotal();

        boolean hasName();

        boolean hasTotal();
    }

    public interface WakeupReasonOrBuilder extends MessageLiteOrBuilder {
        String getName();

        ByteString getNameBytes();

        TimerProto getTotal();

        boolean hasName();

        boolean hasTotal();
    }

    public interface WifiMulticastWakelockTotalOrBuilder extends MessageLiteOrBuilder {
        int getCount();

        long getDurationMs();

        boolean hasCount();

        boolean hasDurationMs();
    }

    public interface WifiSignalStrengthOrBuilder extends MessageLiteOrBuilder {
        WifiSignalStrength.Name getName();

        TimerProto getTotal();

        boolean hasName();

        boolean hasTotal();
    }

    public interface WifiStateOrBuilder extends MessageLiteOrBuilder {
        WifiState.Name getName();

        TimerProto getTotal();

        boolean hasName();

        boolean hasTotal();
    }

    public interface WifiSupplicantStateOrBuilder extends MessageLiteOrBuilder {
        WifiSupplicantState.Name getName();

        TimerProto getTotal();

        boolean hasName();

        boolean hasTotal();
    }

    private SystemProto() {
    }

    public static final class Battery extends GeneratedMessageLite<Battery, Builder> implements BatteryOrBuilder {
        public static final int BATTERY_REALTIME_MS_FIELD_NUMBER = 5;
        public static final int BATTERY_UPTIME_MS_FIELD_NUMBER = 6;
        private static final Battery DEFAULT_INSTANCE = new Battery();
        public static final int ESTIMATED_BATTERY_CAPACITY_MAH_FIELD_NUMBER = 10;
        public static final int MAX_LEARNED_BATTERY_CAPACITY_UAH_FIELD_NUMBER = 12;
        public static final int MIN_LEARNED_BATTERY_CAPACITY_UAH_FIELD_NUMBER = 11;
        private static volatile Parser<Battery> PARSER = null;
        public static final int SCREEN_DOZE_DURATION_MS_FIELD_NUMBER = 9;
        public static final int SCREEN_OFF_REALTIME_MS_FIELD_NUMBER = 7;
        public static final int SCREEN_OFF_UPTIME_MS_FIELD_NUMBER = 8;
        public static final int START_CLOCK_TIME_MS_FIELD_NUMBER = 1;
        public static final int START_COUNT_FIELD_NUMBER = 2;
        public static final int TOTAL_REALTIME_MS_FIELD_NUMBER = 3;
        public static final int TOTAL_UPTIME_MS_FIELD_NUMBER = 4;
        private long batteryRealtimeMs_ = 0;
        private long batteryUptimeMs_ = 0;
        private int bitField0_;
        private long estimatedBatteryCapacityMah_ = 0;
        private long maxLearnedBatteryCapacityUah_ = 0;
        private long minLearnedBatteryCapacityUah_ = 0;
        private long screenDozeDurationMs_ = 0;
        private long screenOffRealtimeMs_ = 0;
        private long screenOffUptimeMs_ = 0;
        private long startClockTimeMs_ = 0;
        private long startCount_ = 0;
        private long totalRealtimeMs_ = 0;
        private long totalUptimeMs_ = 0;

        private Battery() {
        }

        @Override // android.os.SystemProto.BatteryOrBuilder
        public boolean hasStartClockTimeMs() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // android.os.SystemProto.BatteryOrBuilder
        public long getStartClockTimeMs() {
            return this.startClockTimeMs_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setStartClockTimeMs(long value) {
            this.bitField0_ |= 1;
            this.startClockTimeMs_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearStartClockTimeMs() {
            this.bitField0_ &= -2;
            this.startClockTimeMs_ = 0;
        }

        @Override // android.os.SystemProto.BatteryOrBuilder
        public boolean hasStartCount() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // android.os.SystemProto.BatteryOrBuilder
        public long getStartCount() {
            return this.startCount_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setStartCount(long value) {
            this.bitField0_ |= 2;
            this.startCount_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearStartCount() {
            this.bitField0_ &= -3;
            this.startCount_ = 0;
        }

        @Override // android.os.SystemProto.BatteryOrBuilder
        public boolean hasTotalRealtimeMs() {
            return (this.bitField0_ & 4) == 4;
        }

        @Override // android.os.SystemProto.BatteryOrBuilder
        public long getTotalRealtimeMs() {
            return this.totalRealtimeMs_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setTotalRealtimeMs(long value) {
            this.bitField0_ |= 4;
            this.totalRealtimeMs_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearTotalRealtimeMs() {
            this.bitField0_ &= -5;
            this.totalRealtimeMs_ = 0;
        }

        @Override // android.os.SystemProto.BatteryOrBuilder
        public boolean hasTotalUptimeMs() {
            return (this.bitField0_ & 8) == 8;
        }

        @Override // android.os.SystemProto.BatteryOrBuilder
        public long getTotalUptimeMs() {
            return this.totalUptimeMs_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setTotalUptimeMs(long value) {
            this.bitField0_ |= 8;
            this.totalUptimeMs_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearTotalUptimeMs() {
            this.bitField0_ &= -9;
            this.totalUptimeMs_ = 0;
        }

        @Override // android.os.SystemProto.BatteryOrBuilder
        public boolean hasBatteryRealtimeMs() {
            return (this.bitField0_ & 16) == 16;
        }

        @Override // android.os.SystemProto.BatteryOrBuilder
        public long getBatteryRealtimeMs() {
            return this.batteryRealtimeMs_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setBatteryRealtimeMs(long value) {
            this.bitField0_ |= 16;
            this.batteryRealtimeMs_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearBatteryRealtimeMs() {
            this.bitField0_ &= -17;
            this.batteryRealtimeMs_ = 0;
        }

        @Override // android.os.SystemProto.BatteryOrBuilder
        public boolean hasBatteryUptimeMs() {
            return (this.bitField0_ & 32) == 32;
        }

        @Override // android.os.SystemProto.BatteryOrBuilder
        public long getBatteryUptimeMs() {
            return this.batteryUptimeMs_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setBatteryUptimeMs(long value) {
            this.bitField0_ |= 32;
            this.batteryUptimeMs_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearBatteryUptimeMs() {
            this.bitField0_ &= -33;
            this.batteryUptimeMs_ = 0;
        }

        @Override // android.os.SystemProto.BatteryOrBuilder
        public boolean hasScreenOffRealtimeMs() {
            return (this.bitField0_ & 64) == 64;
        }

        @Override // android.os.SystemProto.BatteryOrBuilder
        public long getScreenOffRealtimeMs() {
            return this.screenOffRealtimeMs_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setScreenOffRealtimeMs(long value) {
            this.bitField0_ |= 64;
            this.screenOffRealtimeMs_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearScreenOffRealtimeMs() {
            this.bitField0_ &= -65;
            this.screenOffRealtimeMs_ = 0;
        }

        @Override // android.os.SystemProto.BatteryOrBuilder
        public boolean hasScreenOffUptimeMs() {
            return (this.bitField0_ & 128) == 128;
        }

        @Override // android.os.SystemProto.BatteryOrBuilder
        public long getScreenOffUptimeMs() {
            return this.screenOffUptimeMs_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setScreenOffUptimeMs(long value) {
            this.bitField0_ |= 128;
            this.screenOffUptimeMs_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearScreenOffUptimeMs() {
            this.bitField0_ &= -129;
            this.screenOffUptimeMs_ = 0;
        }

        @Override // android.os.SystemProto.BatteryOrBuilder
        public boolean hasScreenDozeDurationMs() {
            return (this.bitField0_ & 256) == 256;
        }

        @Override // android.os.SystemProto.BatteryOrBuilder
        public long getScreenDozeDurationMs() {
            return this.screenDozeDurationMs_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setScreenDozeDurationMs(long value) {
            this.bitField0_ |= 256;
            this.screenDozeDurationMs_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearScreenDozeDurationMs() {
            this.bitField0_ &= -257;
            this.screenDozeDurationMs_ = 0;
        }

        @Override // android.os.SystemProto.BatteryOrBuilder
        public boolean hasEstimatedBatteryCapacityMah() {
            return (this.bitField0_ & 512) == 512;
        }

        @Override // android.os.SystemProto.BatteryOrBuilder
        public long getEstimatedBatteryCapacityMah() {
            return this.estimatedBatteryCapacityMah_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setEstimatedBatteryCapacityMah(long value) {
            this.bitField0_ |= 512;
            this.estimatedBatteryCapacityMah_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearEstimatedBatteryCapacityMah() {
            this.bitField0_ &= -513;
            this.estimatedBatteryCapacityMah_ = 0;
        }

        @Override // android.os.SystemProto.BatteryOrBuilder
        public boolean hasMinLearnedBatteryCapacityUah() {
            return (this.bitField0_ & 1024) == 1024;
        }

        @Override // android.os.SystemProto.BatteryOrBuilder
        public long getMinLearnedBatteryCapacityUah() {
            return this.minLearnedBatteryCapacityUah_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setMinLearnedBatteryCapacityUah(long value) {
            this.bitField0_ |= 1024;
            this.minLearnedBatteryCapacityUah_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearMinLearnedBatteryCapacityUah() {
            this.bitField0_ &= -1025;
            this.minLearnedBatteryCapacityUah_ = 0;
        }

        @Override // android.os.SystemProto.BatteryOrBuilder
        public boolean hasMaxLearnedBatteryCapacityUah() {
            return (this.bitField0_ & 2048) == 2048;
        }

        @Override // android.os.SystemProto.BatteryOrBuilder
        public long getMaxLearnedBatteryCapacityUah() {
            return this.maxLearnedBatteryCapacityUah_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setMaxLearnedBatteryCapacityUah(long value) {
            this.bitField0_ |= 2048;
            this.maxLearnedBatteryCapacityUah_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearMaxLearnedBatteryCapacityUah() {
            this.bitField0_ &= -2049;
            this.maxLearnedBatteryCapacityUah_ = 0;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                output.writeInt64(1, this.startClockTimeMs_);
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeInt64(2, this.startCount_);
            }
            if ((this.bitField0_ & 4) == 4) {
                output.writeInt64(3, this.totalRealtimeMs_);
            }
            if ((this.bitField0_ & 8) == 8) {
                output.writeInt64(4, this.totalUptimeMs_);
            }
            if ((this.bitField0_ & 16) == 16) {
                output.writeInt64(5, this.batteryRealtimeMs_);
            }
            if ((this.bitField0_ & 32) == 32) {
                output.writeInt64(6, this.batteryUptimeMs_);
            }
            if ((this.bitField0_ & 64) == 64) {
                output.writeInt64(7, this.screenOffRealtimeMs_);
            }
            if ((this.bitField0_ & 128) == 128) {
                output.writeInt64(8, this.screenOffUptimeMs_);
            }
            if ((this.bitField0_ & 256) == 256) {
                output.writeInt64(9, this.screenDozeDurationMs_);
            }
            if ((this.bitField0_ & 512) == 512) {
                output.writeInt64(10, this.estimatedBatteryCapacityMah_);
            }
            if ((this.bitField0_ & 1024) == 1024) {
                output.writeInt64(11, this.minLearnedBatteryCapacityUah_);
            }
            if ((this.bitField0_ & 2048) == 2048) {
                output.writeInt64(12, this.maxLearnedBatteryCapacityUah_);
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
                size2 = 0 + CodedOutputStream.computeInt64Size(1, this.startClockTimeMs_);
            }
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeInt64Size(2, this.startCount_);
            }
            if ((this.bitField0_ & 4) == 4) {
                size2 += CodedOutputStream.computeInt64Size(3, this.totalRealtimeMs_);
            }
            if ((this.bitField0_ & 8) == 8) {
                size2 += CodedOutputStream.computeInt64Size(4, this.totalUptimeMs_);
            }
            if ((this.bitField0_ & 16) == 16) {
                size2 += CodedOutputStream.computeInt64Size(5, this.batteryRealtimeMs_);
            }
            if ((this.bitField0_ & 32) == 32) {
                size2 += CodedOutputStream.computeInt64Size(6, this.batteryUptimeMs_);
            }
            if ((this.bitField0_ & 64) == 64) {
                size2 += CodedOutputStream.computeInt64Size(7, this.screenOffRealtimeMs_);
            }
            if ((this.bitField0_ & 128) == 128) {
                size2 += CodedOutputStream.computeInt64Size(8, this.screenOffUptimeMs_);
            }
            if ((this.bitField0_ & 256) == 256) {
                size2 += CodedOutputStream.computeInt64Size(9, this.screenDozeDurationMs_);
            }
            if ((this.bitField0_ & 512) == 512) {
                size2 += CodedOutputStream.computeInt64Size(10, this.estimatedBatteryCapacityMah_);
            }
            if ((this.bitField0_ & 1024) == 1024) {
                size2 += CodedOutputStream.computeInt64Size(11, this.minLearnedBatteryCapacityUah_);
            }
            if ((this.bitField0_ & 2048) == 2048) {
                size2 += CodedOutputStream.computeInt64Size(12, this.maxLearnedBatteryCapacityUah_);
            }
            int size3 = size2 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static Battery parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (Battery) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static Battery parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Battery) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static Battery parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (Battery) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static Battery parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Battery) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static Battery parseFrom(InputStream input) throws IOException {
            return (Battery) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static Battery parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Battery) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Battery parseDelimitedFrom(InputStream input) throws IOException {
            return (Battery) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static Battery parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Battery) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Battery parseFrom(CodedInputStream input) throws IOException {
            return (Battery) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static Battery parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Battery) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(Battery prototype) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<Battery, Builder> implements BatteryOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 x0) {
                this();
            }

            private Builder() {
                super(Battery.DEFAULT_INSTANCE);
            }

            @Override // android.os.SystemProto.BatteryOrBuilder
            public boolean hasStartClockTimeMs() {
                return ((Battery) this.instance).hasStartClockTimeMs();
            }

            @Override // android.os.SystemProto.BatteryOrBuilder
            public long getStartClockTimeMs() {
                return ((Battery) this.instance).getStartClockTimeMs();
            }

            public Builder setStartClockTimeMs(long value) {
                copyOnWrite();
                ((Battery) this.instance).setStartClockTimeMs(value);
                return this;
            }

            public Builder clearStartClockTimeMs() {
                copyOnWrite();
                ((Battery) this.instance).clearStartClockTimeMs();
                return this;
            }

            @Override // android.os.SystemProto.BatteryOrBuilder
            public boolean hasStartCount() {
                return ((Battery) this.instance).hasStartCount();
            }

            @Override // android.os.SystemProto.BatteryOrBuilder
            public long getStartCount() {
                return ((Battery) this.instance).getStartCount();
            }

            public Builder setStartCount(long value) {
                copyOnWrite();
                ((Battery) this.instance).setStartCount(value);
                return this;
            }

            public Builder clearStartCount() {
                copyOnWrite();
                ((Battery) this.instance).clearStartCount();
                return this;
            }

            @Override // android.os.SystemProto.BatteryOrBuilder
            public boolean hasTotalRealtimeMs() {
                return ((Battery) this.instance).hasTotalRealtimeMs();
            }

            @Override // android.os.SystemProto.BatteryOrBuilder
            public long getTotalRealtimeMs() {
                return ((Battery) this.instance).getTotalRealtimeMs();
            }

            public Builder setTotalRealtimeMs(long value) {
                copyOnWrite();
                ((Battery) this.instance).setTotalRealtimeMs(value);
                return this;
            }

            public Builder clearTotalRealtimeMs() {
                copyOnWrite();
                ((Battery) this.instance).clearTotalRealtimeMs();
                return this;
            }

            @Override // android.os.SystemProto.BatteryOrBuilder
            public boolean hasTotalUptimeMs() {
                return ((Battery) this.instance).hasTotalUptimeMs();
            }

            @Override // android.os.SystemProto.BatteryOrBuilder
            public long getTotalUptimeMs() {
                return ((Battery) this.instance).getTotalUptimeMs();
            }

            public Builder setTotalUptimeMs(long value) {
                copyOnWrite();
                ((Battery) this.instance).setTotalUptimeMs(value);
                return this;
            }

            public Builder clearTotalUptimeMs() {
                copyOnWrite();
                ((Battery) this.instance).clearTotalUptimeMs();
                return this;
            }

            @Override // android.os.SystemProto.BatteryOrBuilder
            public boolean hasBatteryRealtimeMs() {
                return ((Battery) this.instance).hasBatteryRealtimeMs();
            }

            @Override // android.os.SystemProto.BatteryOrBuilder
            public long getBatteryRealtimeMs() {
                return ((Battery) this.instance).getBatteryRealtimeMs();
            }

            public Builder setBatteryRealtimeMs(long value) {
                copyOnWrite();
                ((Battery) this.instance).setBatteryRealtimeMs(value);
                return this;
            }

            public Builder clearBatteryRealtimeMs() {
                copyOnWrite();
                ((Battery) this.instance).clearBatteryRealtimeMs();
                return this;
            }

            @Override // android.os.SystemProto.BatteryOrBuilder
            public boolean hasBatteryUptimeMs() {
                return ((Battery) this.instance).hasBatteryUptimeMs();
            }

            @Override // android.os.SystemProto.BatteryOrBuilder
            public long getBatteryUptimeMs() {
                return ((Battery) this.instance).getBatteryUptimeMs();
            }

            public Builder setBatteryUptimeMs(long value) {
                copyOnWrite();
                ((Battery) this.instance).setBatteryUptimeMs(value);
                return this;
            }

            public Builder clearBatteryUptimeMs() {
                copyOnWrite();
                ((Battery) this.instance).clearBatteryUptimeMs();
                return this;
            }

            @Override // android.os.SystemProto.BatteryOrBuilder
            public boolean hasScreenOffRealtimeMs() {
                return ((Battery) this.instance).hasScreenOffRealtimeMs();
            }

            @Override // android.os.SystemProto.BatteryOrBuilder
            public long getScreenOffRealtimeMs() {
                return ((Battery) this.instance).getScreenOffRealtimeMs();
            }

            public Builder setScreenOffRealtimeMs(long value) {
                copyOnWrite();
                ((Battery) this.instance).setScreenOffRealtimeMs(value);
                return this;
            }

            public Builder clearScreenOffRealtimeMs() {
                copyOnWrite();
                ((Battery) this.instance).clearScreenOffRealtimeMs();
                return this;
            }

            @Override // android.os.SystemProto.BatteryOrBuilder
            public boolean hasScreenOffUptimeMs() {
                return ((Battery) this.instance).hasScreenOffUptimeMs();
            }

            @Override // android.os.SystemProto.BatteryOrBuilder
            public long getScreenOffUptimeMs() {
                return ((Battery) this.instance).getScreenOffUptimeMs();
            }

            public Builder setScreenOffUptimeMs(long value) {
                copyOnWrite();
                ((Battery) this.instance).setScreenOffUptimeMs(value);
                return this;
            }

            public Builder clearScreenOffUptimeMs() {
                copyOnWrite();
                ((Battery) this.instance).clearScreenOffUptimeMs();
                return this;
            }

            @Override // android.os.SystemProto.BatteryOrBuilder
            public boolean hasScreenDozeDurationMs() {
                return ((Battery) this.instance).hasScreenDozeDurationMs();
            }

            @Override // android.os.SystemProto.BatteryOrBuilder
            public long getScreenDozeDurationMs() {
                return ((Battery) this.instance).getScreenDozeDurationMs();
            }

            public Builder setScreenDozeDurationMs(long value) {
                copyOnWrite();
                ((Battery) this.instance).setScreenDozeDurationMs(value);
                return this;
            }

            public Builder clearScreenDozeDurationMs() {
                copyOnWrite();
                ((Battery) this.instance).clearScreenDozeDurationMs();
                return this;
            }

            @Override // android.os.SystemProto.BatteryOrBuilder
            public boolean hasEstimatedBatteryCapacityMah() {
                return ((Battery) this.instance).hasEstimatedBatteryCapacityMah();
            }

            @Override // android.os.SystemProto.BatteryOrBuilder
            public long getEstimatedBatteryCapacityMah() {
                return ((Battery) this.instance).getEstimatedBatteryCapacityMah();
            }

            public Builder setEstimatedBatteryCapacityMah(long value) {
                copyOnWrite();
                ((Battery) this.instance).setEstimatedBatteryCapacityMah(value);
                return this;
            }

            public Builder clearEstimatedBatteryCapacityMah() {
                copyOnWrite();
                ((Battery) this.instance).clearEstimatedBatteryCapacityMah();
                return this;
            }

            @Override // android.os.SystemProto.BatteryOrBuilder
            public boolean hasMinLearnedBatteryCapacityUah() {
                return ((Battery) this.instance).hasMinLearnedBatteryCapacityUah();
            }

            @Override // android.os.SystemProto.BatteryOrBuilder
            public long getMinLearnedBatteryCapacityUah() {
                return ((Battery) this.instance).getMinLearnedBatteryCapacityUah();
            }

            public Builder setMinLearnedBatteryCapacityUah(long value) {
                copyOnWrite();
                ((Battery) this.instance).setMinLearnedBatteryCapacityUah(value);
                return this;
            }

            public Builder clearMinLearnedBatteryCapacityUah() {
                copyOnWrite();
                ((Battery) this.instance).clearMinLearnedBatteryCapacityUah();
                return this;
            }

            @Override // android.os.SystemProto.BatteryOrBuilder
            public boolean hasMaxLearnedBatteryCapacityUah() {
                return ((Battery) this.instance).hasMaxLearnedBatteryCapacityUah();
            }

            @Override // android.os.SystemProto.BatteryOrBuilder
            public long getMaxLearnedBatteryCapacityUah() {
                return ((Battery) this.instance).getMaxLearnedBatteryCapacityUah();
            }

            public Builder setMaxLearnedBatteryCapacityUah(long value) {
                copyOnWrite();
                ((Battery) this.instance).setMaxLearnedBatteryCapacityUah(value);
                return this;
            }

            public Builder clearMaxLearnedBatteryCapacityUah() {
                copyOnWrite();
                ((Battery) this.instance).clearMaxLearnedBatteryCapacityUah();
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new Battery();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder(null);
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    Battery other = (Battery) arg1;
                    this.startClockTimeMs_ = visitor.visitLong(hasStartClockTimeMs(), this.startClockTimeMs_, other.hasStartClockTimeMs(), other.startClockTimeMs_);
                    this.startCount_ = visitor.visitLong(hasStartCount(), this.startCount_, other.hasStartCount(), other.startCount_);
                    this.totalRealtimeMs_ = visitor.visitLong(hasTotalRealtimeMs(), this.totalRealtimeMs_, other.hasTotalRealtimeMs(), other.totalRealtimeMs_);
                    this.totalUptimeMs_ = visitor.visitLong(hasTotalUptimeMs(), this.totalUptimeMs_, other.hasTotalUptimeMs(), other.totalUptimeMs_);
                    this.batteryRealtimeMs_ = visitor.visitLong(hasBatteryRealtimeMs(), this.batteryRealtimeMs_, other.hasBatteryRealtimeMs(), other.batteryRealtimeMs_);
                    this.batteryUptimeMs_ = visitor.visitLong(hasBatteryUptimeMs(), this.batteryUptimeMs_, other.hasBatteryUptimeMs(), other.batteryUptimeMs_);
                    this.screenOffRealtimeMs_ = visitor.visitLong(hasScreenOffRealtimeMs(), this.screenOffRealtimeMs_, other.hasScreenOffRealtimeMs(), other.screenOffRealtimeMs_);
                    this.screenOffUptimeMs_ = visitor.visitLong(hasScreenOffUptimeMs(), this.screenOffUptimeMs_, other.hasScreenOffUptimeMs(), other.screenOffUptimeMs_);
                    this.screenDozeDurationMs_ = visitor.visitLong(hasScreenDozeDurationMs(), this.screenDozeDurationMs_, other.hasScreenDozeDurationMs(), other.screenDozeDurationMs_);
                    this.estimatedBatteryCapacityMah_ = visitor.visitLong(hasEstimatedBatteryCapacityMah(), this.estimatedBatteryCapacityMah_, other.hasEstimatedBatteryCapacityMah(), other.estimatedBatteryCapacityMah_);
                    this.minLearnedBatteryCapacityUah_ = visitor.visitLong(hasMinLearnedBatteryCapacityUah(), this.minLearnedBatteryCapacityUah_, other.hasMinLearnedBatteryCapacityUah(), other.minLearnedBatteryCapacityUah_);
                    this.maxLearnedBatteryCapacityUah_ = visitor.visitLong(hasMaxLearnedBatteryCapacityUah(), this.maxLearnedBatteryCapacityUah_, other.hasMaxLearnedBatteryCapacityUah(), other.maxLearnedBatteryCapacityUah_);
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
                                    this.startClockTimeMs_ = input.readInt64();
                                    break;
                                case 16:
                                    this.bitField0_ |= 2;
                                    this.startCount_ = input.readInt64();
                                    break;
                                case 24:
                                    this.bitField0_ |= 4;
                                    this.totalRealtimeMs_ = input.readInt64();
                                    break;
                                case 32:
                                    this.bitField0_ |= 8;
                                    this.totalUptimeMs_ = input.readInt64();
                                    break;
                                case 40:
                                    this.bitField0_ |= 16;
                                    this.batteryRealtimeMs_ = input.readInt64();
                                    break;
                                case 48:
                                    this.bitField0_ |= 32;
                                    this.batteryUptimeMs_ = input.readInt64();
                                    break;
                                case 56:
                                    this.bitField0_ |= 64;
                                    this.screenOffRealtimeMs_ = input.readInt64();
                                    break;
                                case 64:
                                    this.bitField0_ |= 128;
                                    this.screenOffUptimeMs_ = input.readInt64();
                                    break;
                                case 72:
                                    this.bitField0_ |= 256;
                                    this.screenDozeDurationMs_ = input.readInt64();
                                    break;
                                case 80:
                                    this.bitField0_ |= 512;
                                    this.estimatedBatteryCapacityMah_ = input.readInt64();
                                    break;
                                case 88:
                                    this.bitField0_ |= 1024;
                                    this.minLearnedBatteryCapacityUah_ = input.readInt64();
                                    break;
                                case 96:
                                    this.bitField0_ |= 2048;
                                    this.maxLearnedBatteryCapacityUah_ = input.readInt64();
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
                        synchronized (Battery.class) {
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

        public static Battery getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<Battery> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class BatteryDischarge extends GeneratedMessageLite<BatteryDischarge, Builder> implements BatteryDischargeOrBuilder {
        private static final BatteryDischarge DEFAULT_INSTANCE = new BatteryDischarge();
        public static final int LOWER_BOUND_SINCE_CHARGE_FIELD_NUMBER = 1;
        private static volatile Parser<BatteryDischarge> PARSER = null;
        public static final int SCREEN_DOZE_SINCE_CHARGE_FIELD_NUMBER = 5;
        public static final int SCREEN_OFF_SINCE_CHARGE_FIELD_NUMBER = 4;
        public static final int SCREEN_ON_SINCE_CHARGE_FIELD_NUMBER = 3;
        public static final int TOTAL_MAH_DEEP_DOZE_FIELD_NUMBER = 10;
        public static final int TOTAL_MAH_FIELD_NUMBER = 6;
        public static final int TOTAL_MAH_LIGHT_DOZE_FIELD_NUMBER = 9;
        public static final int TOTAL_MAH_SCREEN_DOZE_FIELD_NUMBER = 8;
        public static final int TOTAL_MAH_SCREEN_OFF_FIELD_NUMBER = 7;
        public static final int UPPER_BOUND_SINCE_CHARGE_FIELD_NUMBER = 2;
        private int bitField0_;
        private int lowerBoundSinceCharge_ = 0;
        private int screenDozeSinceCharge_ = 0;
        private int screenOffSinceCharge_ = 0;
        private int screenOnSinceCharge_ = 0;
        private long totalMahDeepDoze_ = 0;
        private long totalMahLightDoze_ = 0;
        private long totalMahScreenDoze_ = 0;
        private long totalMahScreenOff_ = 0;
        private long totalMah_ = 0;
        private int upperBoundSinceCharge_ = 0;

        private BatteryDischarge() {
        }

        @Override // android.os.SystemProto.BatteryDischargeOrBuilder
        public boolean hasLowerBoundSinceCharge() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // android.os.SystemProto.BatteryDischargeOrBuilder
        public int getLowerBoundSinceCharge() {
            return this.lowerBoundSinceCharge_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setLowerBoundSinceCharge(int value) {
            this.bitField0_ |= 1;
            this.lowerBoundSinceCharge_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearLowerBoundSinceCharge() {
            this.bitField0_ &= -2;
            this.lowerBoundSinceCharge_ = 0;
        }

        @Override // android.os.SystemProto.BatteryDischargeOrBuilder
        public boolean hasUpperBoundSinceCharge() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // android.os.SystemProto.BatteryDischargeOrBuilder
        public int getUpperBoundSinceCharge() {
            return this.upperBoundSinceCharge_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setUpperBoundSinceCharge(int value) {
            this.bitField0_ |= 2;
            this.upperBoundSinceCharge_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearUpperBoundSinceCharge() {
            this.bitField0_ &= -3;
            this.upperBoundSinceCharge_ = 0;
        }

        @Override // android.os.SystemProto.BatteryDischargeOrBuilder
        public boolean hasScreenOnSinceCharge() {
            return (this.bitField0_ & 4) == 4;
        }

        @Override // android.os.SystemProto.BatteryDischargeOrBuilder
        public int getScreenOnSinceCharge() {
            return this.screenOnSinceCharge_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setScreenOnSinceCharge(int value) {
            this.bitField0_ |= 4;
            this.screenOnSinceCharge_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearScreenOnSinceCharge() {
            this.bitField0_ &= -5;
            this.screenOnSinceCharge_ = 0;
        }

        @Override // android.os.SystemProto.BatteryDischargeOrBuilder
        public boolean hasScreenOffSinceCharge() {
            return (this.bitField0_ & 8) == 8;
        }

        @Override // android.os.SystemProto.BatteryDischargeOrBuilder
        public int getScreenOffSinceCharge() {
            return this.screenOffSinceCharge_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setScreenOffSinceCharge(int value) {
            this.bitField0_ |= 8;
            this.screenOffSinceCharge_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearScreenOffSinceCharge() {
            this.bitField0_ &= -9;
            this.screenOffSinceCharge_ = 0;
        }

        @Override // android.os.SystemProto.BatteryDischargeOrBuilder
        public boolean hasScreenDozeSinceCharge() {
            return (this.bitField0_ & 16) == 16;
        }

        @Override // android.os.SystemProto.BatteryDischargeOrBuilder
        public int getScreenDozeSinceCharge() {
            return this.screenDozeSinceCharge_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setScreenDozeSinceCharge(int value) {
            this.bitField0_ |= 16;
            this.screenDozeSinceCharge_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearScreenDozeSinceCharge() {
            this.bitField0_ &= -17;
            this.screenDozeSinceCharge_ = 0;
        }

        @Override // android.os.SystemProto.BatteryDischargeOrBuilder
        public boolean hasTotalMah() {
            return (this.bitField0_ & 32) == 32;
        }

        @Override // android.os.SystemProto.BatteryDischargeOrBuilder
        public long getTotalMah() {
            return this.totalMah_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setTotalMah(long value) {
            this.bitField0_ |= 32;
            this.totalMah_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearTotalMah() {
            this.bitField0_ &= -33;
            this.totalMah_ = 0;
        }

        @Override // android.os.SystemProto.BatteryDischargeOrBuilder
        public boolean hasTotalMahScreenOff() {
            return (this.bitField0_ & 64) == 64;
        }

        @Override // android.os.SystemProto.BatteryDischargeOrBuilder
        public long getTotalMahScreenOff() {
            return this.totalMahScreenOff_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setTotalMahScreenOff(long value) {
            this.bitField0_ |= 64;
            this.totalMahScreenOff_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearTotalMahScreenOff() {
            this.bitField0_ &= -65;
            this.totalMahScreenOff_ = 0;
        }

        @Override // android.os.SystemProto.BatteryDischargeOrBuilder
        public boolean hasTotalMahScreenDoze() {
            return (this.bitField0_ & 128) == 128;
        }

        @Override // android.os.SystemProto.BatteryDischargeOrBuilder
        public long getTotalMahScreenDoze() {
            return this.totalMahScreenDoze_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setTotalMahScreenDoze(long value) {
            this.bitField0_ |= 128;
            this.totalMahScreenDoze_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearTotalMahScreenDoze() {
            this.bitField0_ &= -129;
            this.totalMahScreenDoze_ = 0;
        }

        @Override // android.os.SystemProto.BatteryDischargeOrBuilder
        public boolean hasTotalMahLightDoze() {
            return (this.bitField0_ & 256) == 256;
        }

        @Override // android.os.SystemProto.BatteryDischargeOrBuilder
        public long getTotalMahLightDoze() {
            return this.totalMahLightDoze_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setTotalMahLightDoze(long value) {
            this.bitField0_ |= 256;
            this.totalMahLightDoze_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearTotalMahLightDoze() {
            this.bitField0_ &= -257;
            this.totalMahLightDoze_ = 0;
        }

        @Override // android.os.SystemProto.BatteryDischargeOrBuilder
        public boolean hasTotalMahDeepDoze() {
            return (this.bitField0_ & 512) == 512;
        }

        @Override // android.os.SystemProto.BatteryDischargeOrBuilder
        public long getTotalMahDeepDoze() {
            return this.totalMahDeepDoze_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setTotalMahDeepDoze(long value) {
            this.bitField0_ |= 512;
            this.totalMahDeepDoze_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearTotalMahDeepDoze() {
            this.bitField0_ &= -513;
            this.totalMahDeepDoze_ = 0;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                output.writeInt32(1, this.lowerBoundSinceCharge_);
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeInt32(2, this.upperBoundSinceCharge_);
            }
            if ((this.bitField0_ & 4) == 4) {
                output.writeInt32(3, this.screenOnSinceCharge_);
            }
            if ((this.bitField0_ & 8) == 8) {
                output.writeInt32(4, this.screenOffSinceCharge_);
            }
            if ((this.bitField0_ & 16) == 16) {
                output.writeInt32(5, this.screenDozeSinceCharge_);
            }
            if ((this.bitField0_ & 32) == 32) {
                output.writeInt64(6, this.totalMah_);
            }
            if ((this.bitField0_ & 64) == 64) {
                output.writeInt64(7, this.totalMahScreenOff_);
            }
            if ((this.bitField0_ & 128) == 128) {
                output.writeInt64(8, this.totalMahScreenDoze_);
            }
            if ((this.bitField0_ & 256) == 256) {
                output.writeInt64(9, this.totalMahLightDoze_);
            }
            if ((this.bitField0_ & 512) == 512) {
                output.writeInt64(10, this.totalMahDeepDoze_);
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
                size2 = 0 + CodedOutputStream.computeInt32Size(1, this.lowerBoundSinceCharge_);
            }
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeInt32Size(2, this.upperBoundSinceCharge_);
            }
            if ((this.bitField0_ & 4) == 4) {
                size2 += CodedOutputStream.computeInt32Size(3, this.screenOnSinceCharge_);
            }
            if ((this.bitField0_ & 8) == 8) {
                size2 += CodedOutputStream.computeInt32Size(4, this.screenOffSinceCharge_);
            }
            if ((this.bitField0_ & 16) == 16) {
                size2 += CodedOutputStream.computeInt32Size(5, this.screenDozeSinceCharge_);
            }
            if ((this.bitField0_ & 32) == 32) {
                size2 += CodedOutputStream.computeInt64Size(6, this.totalMah_);
            }
            if ((this.bitField0_ & 64) == 64) {
                size2 += CodedOutputStream.computeInt64Size(7, this.totalMahScreenOff_);
            }
            if ((this.bitField0_ & 128) == 128) {
                size2 += CodedOutputStream.computeInt64Size(8, this.totalMahScreenDoze_);
            }
            if ((this.bitField0_ & 256) == 256) {
                size2 += CodedOutputStream.computeInt64Size(9, this.totalMahLightDoze_);
            }
            if ((this.bitField0_ & 512) == 512) {
                size2 += CodedOutputStream.computeInt64Size(10, this.totalMahDeepDoze_);
            }
            int size3 = size2 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static BatteryDischarge parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (BatteryDischarge) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static BatteryDischarge parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (BatteryDischarge) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static BatteryDischarge parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (BatteryDischarge) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static BatteryDischarge parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (BatteryDischarge) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static BatteryDischarge parseFrom(InputStream input) throws IOException {
            return (BatteryDischarge) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static BatteryDischarge parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (BatteryDischarge) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static BatteryDischarge parseDelimitedFrom(InputStream input) throws IOException {
            return (BatteryDischarge) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static BatteryDischarge parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (BatteryDischarge) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static BatteryDischarge parseFrom(CodedInputStream input) throws IOException {
            return (BatteryDischarge) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static BatteryDischarge parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (BatteryDischarge) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(BatteryDischarge prototype) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<BatteryDischarge, Builder> implements BatteryDischargeOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 x0) {
                this();
            }

            private Builder() {
                super(BatteryDischarge.DEFAULT_INSTANCE);
            }

            @Override // android.os.SystemProto.BatteryDischargeOrBuilder
            public boolean hasLowerBoundSinceCharge() {
                return ((BatteryDischarge) this.instance).hasLowerBoundSinceCharge();
            }

            @Override // android.os.SystemProto.BatteryDischargeOrBuilder
            public int getLowerBoundSinceCharge() {
                return ((BatteryDischarge) this.instance).getLowerBoundSinceCharge();
            }

            public Builder setLowerBoundSinceCharge(int value) {
                copyOnWrite();
                ((BatteryDischarge) this.instance).setLowerBoundSinceCharge(value);
                return this;
            }

            public Builder clearLowerBoundSinceCharge() {
                copyOnWrite();
                ((BatteryDischarge) this.instance).clearLowerBoundSinceCharge();
                return this;
            }

            @Override // android.os.SystemProto.BatteryDischargeOrBuilder
            public boolean hasUpperBoundSinceCharge() {
                return ((BatteryDischarge) this.instance).hasUpperBoundSinceCharge();
            }

            @Override // android.os.SystemProto.BatteryDischargeOrBuilder
            public int getUpperBoundSinceCharge() {
                return ((BatteryDischarge) this.instance).getUpperBoundSinceCharge();
            }

            public Builder setUpperBoundSinceCharge(int value) {
                copyOnWrite();
                ((BatteryDischarge) this.instance).setUpperBoundSinceCharge(value);
                return this;
            }

            public Builder clearUpperBoundSinceCharge() {
                copyOnWrite();
                ((BatteryDischarge) this.instance).clearUpperBoundSinceCharge();
                return this;
            }

            @Override // android.os.SystemProto.BatteryDischargeOrBuilder
            public boolean hasScreenOnSinceCharge() {
                return ((BatteryDischarge) this.instance).hasScreenOnSinceCharge();
            }

            @Override // android.os.SystemProto.BatteryDischargeOrBuilder
            public int getScreenOnSinceCharge() {
                return ((BatteryDischarge) this.instance).getScreenOnSinceCharge();
            }

            public Builder setScreenOnSinceCharge(int value) {
                copyOnWrite();
                ((BatteryDischarge) this.instance).setScreenOnSinceCharge(value);
                return this;
            }

            public Builder clearScreenOnSinceCharge() {
                copyOnWrite();
                ((BatteryDischarge) this.instance).clearScreenOnSinceCharge();
                return this;
            }

            @Override // android.os.SystemProto.BatteryDischargeOrBuilder
            public boolean hasScreenOffSinceCharge() {
                return ((BatteryDischarge) this.instance).hasScreenOffSinceCharge();
            }

            @Override // android.os.SystemProto.BatteryDischargeOrBuilder
            public int getScreenOffSinceCharge() {
                return ((BatteryDischarge) this.instance).getScreenOffSinceCharge();
            }

            public Builder setScreenOffSinceCharge(int value) {
                copyOnWrite();
                ((BatteryDischarge) this.instance).setScreenOffSinceCharge(value);
                return this;
            }

            public Builder clearScreenOffSinceCharge() {
                copyOnWrite();
                ((BatteryDischarge) this.instance).clearScreenOffSinceCharge();
                return this;
            }

            @Override // android.os.SystemProto.BatteryDischargeOrBuilder
            public boolean hasScreenDozeSinceCharge() {
                return ((BatteryDischarge) this.instance).hasScreenDozeSinceCharge();
            }

            @Override // android.os.SystemProto.BatteryDischargeOrBuilder
            public int getScreenDozeSinceCharge() {
                return ((BatteryDischarge) this.instance).getScreenDozeSinceCharge();
            }

            public Builder setScreenDozeSinceCharge(int value) {
                copyOnWrite();
                ((BatteryDischarge) this.instance).setScreenDozeSinceCharge(value);
                return this;
            }

            public Builder clearScreenDozeSinceCharge() {
                copyOnWrite();
                ((BatteryDischarge) this.instance).clearScreenDozeSinceCharge();
                return this;
            }

            @Override // android.os.SystemProto.BatteryDischargeOrBuilder
            public boolean hasTotalMah() {
                return ((BatteryDischarge) this.instance).hasTotalMah();
            }

            @Override // android.os.SystemProto.BatteryDischargeOrBuilder
            public long getTotalMah() {
                return ((BatteryDischarge) this.instance).getTotalMah();
            }

            public Builder setTotalMah(long value) {
                copyOnWrite();
                ((BatteryDischarge) this.instance).setTotalMah(value);
                return this;
            }

            public Builder clearTotalMah() {
                copyOnWrite();
                ((BatteryDischarge) this.instance).clearTotalMah();
                return this;
            }

            @Override // android.os.SystemProto.BatteryDischargeOrBuilder
            public boolean hasTotalMahScreenOff() {
                return ((BatteryDischarge) this.instance).hasTotalMahScreenOff();
            }

            @Override // android.os.SystemProto.BatteryDischargeOrBuilder
            public long getTotalMahScreenOff() {
                return ((BatteryDischarge) this.instance).getTotalMahScreenOff();
            }

            public Builder setTotalMahScreenOff(long value) {
                copyOnWrite();
                ((BatteryDischarge) this.instance).setTotalMahScreenOff(value);
                return this;
            }

            public Builder clearTotalMahScreenOff() {
                copyOnWrite();
                ((BatteryDischarge) this.instance).clearTotalMahScreenOff();
                return this;
            }

            @Override // android.os.SystemProto.BatteryDischargeOrBuilder
            public boolean hasTotalMahScreenDoze() {
                return ((BatteryDischarge) this.instance).hasTotalMahScreenDoze();
            }

            @Override // android.os.SystemProto.BatteryDischargeOrBuilder
            public long getTotalMahScreenDoze() {
                return ((BatteryDischarge) this.instance).getTotalMahScreenDoze();
            }

            public Builder setTotalMahScreenDoze(long value) {
                copyOnWrite();
                ((BatteryDischarge) this.instance).setTotalMahScreenDoze(value);
                return this;
            }

            public Builder clearTotalMahScreenDoze() {
                copyOnWrite();
                ((BatteryDischarge) this.instance).clearTotalMahScreenDoze();
                return this;
            }

            @Override // android.os.SystemProto.BatteryDischargeOrBuilder
            public boolean hasTotalMahLightDoze() {
                return ((BatteryDischarge) this.instance).hasTotalMahLightDoze();
            }

            @Override // android.os.SystemProto.BatteryDischargeOrBuilder
            public long getTotalMahLightDoze() {
                return ((BatteryDischarge) this.instance).getTotalMahLightDoze();
            }

            public Builder setTotalMahLightDoze(long value) {
                copyOnWrite();
                ((BatteryDischarge) this.instance).setTotalMahLightDoze(value);
                return this;
            }

            public Builder clearTotalMahLightDoze() {
                copyOnWrite();
                ((BatteryDischarge) this.instance).clearTotalMahLightDoze();
                return this;
            }

            @Override // android.os.SystemProto.BatteryDischargeOrBuilder
            public boolean hasTotalMahDeepDoze() {
                return ((BatteryDischarge) this.instance).hasTotalMahDeepDoze();
            }

            @Override // android.os.SystemProto.BatteryDischargeOrBuilder
            public long getTotalMahDeepDoze() {
                return ((BatteryDischarge) this.instance).getTotalMahDeepDoze();
            }

            public Builder setTotalMahDeepDoze(long value) {
                copyOnWrite();
                ((BatteryDischarge) this.instance).setTotalMahDeepDoze(value);
                return this;
            }

            public Builder clearTotalMahDeepDoze() {
                copyOnWrite();
                ((BatteryDischarge) this.instance).clearTotalMahDeepDoze();
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new BatteryDischarge();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder(null);
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    BatteryDischarge other = (BatteryDischarge) arg1;
                    this.lowerBoundSinceCharge_ = visitor.visitInt(hasLowerBoundSinceCharge(), this.lowerBoundSinceCharge_, other.hasLowerBoundSinceCharge(), other.lowerBoundSinceCharge_);
                    this.upperBoundSinceCharge_ = visitor.visitInt(hasUpperBoundSinceCharge(), this.upperBoundSinceCharge_, other.hasUpperBoundSinceCharge(), other.upperBoundSinceCharge_);
                    this.screenOnSinceCharge_ = visitor.visitInt(hasScreenOnSinceCharge(), this.screenOnSinceCharge_, other.hasScreenOnSinceCharge(), other.screenOnSinceCharge_);
                    this.screenOffSinceCharge_ = visitor.visitInt(hasScreenOffSinceCharge(), this.screenOffSinceCharge_, other.hasScreenOffSinceCharge(), other.screenOffSinceCharge_);
                    this.screenDozeSinceCharge_ = visitor.visitInt(hasScreenDozeSinceCharge(), this.screenDozeSinceCharge_, other.hasScreenDozeSinceCharge(), other.screenDozeSinceCharge_);
                    this.totalMah_ = visitor.visitLong(hasTotalMah(), this.totalMah_, other.hasTotalMah(), other.totalMah_);
                    this.totalMahScreenOff_ = visitor.visitLong(hasTotalMahScreenOff(), this.totalMahScreenOff_, other.hasTotalMahScreenOff(), other.totalMahScreenOff_);
                    this.totalMahScreenDoze_ = visitor.visitLong(hasTotalMahScreenDoze(), this.totalMahScreenDoze_, other.hasTotalMahScreenDoze(), other.totalMahScreenDoze_);
                    this.totalMahLightDoze_ = visitor.visitLong(hasTotalMahLightDoze(), this.totalMahLightDoze_, other.hasTotalMahLightDoze(), other.totalMahLightDoze_);
                    this.totalMahDeepDoze_ = visitor.visitLong(hasTotalMahDeepDoze(), this.totalMahDeepDoze_, other.hasTotalMahDeepDoze(), other.totalMahDeepDoze_);
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
                                    this.lowerBoundSinceCharge_ = input.readInt32();
                                    break;
                                case 16:
                                    this.bitField0_ |= 2;
                                    this.upperBoundSinceCharge_ = input.readInt32();
                                    break;
                                case 24:
                                    this.bitField0_ |= 4;
                                    this.screenOnSinceCharge_ = input.readInt32();
                                    break;
                                case 32:
                                    this.bitField0_ |= 8;
                                    this.screenOffSinceCharge_ = input.readInt32();
                                    break;
                                case 40:
                                    this.bitField0_ |= 16;
                                    this.screenDozeSinceCharge_ = input.readInt32();
                                    break;
                                case 48:
                                    this.bitField0_ |= 32;
                                    this.totalMah_ = input.readInt64();
                                    break;
                                case 56:
                                    this.bitField0_ |= 64;
                                    this.totalMahScreenOff_ = input.readInt64();
                                    break;
                                case 64:
                                    this.bitField0_ |= 128;
                                    this.totalMahScreenDoze_ = input.readInt64();
                                    break;
                                case 72:
                                    this.bitField0_ |= 256;
                                    this.totalMahLightDoze_ = input.readInt64();
                                    break;
                                case 80:
                                    this.bitField0_ |= 512;
                                    this.totalMahDeepDoze_ = input.readInt64();
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
                        synchronized (BatteryDischarge.class) {
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

        public static BatteryDischarge getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<BatteryDischarge> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class BatteryLevelStep extends GeneratedMessageLite<BatteryLevelStep, Builder> implements BatteryLevelStepOrBuilder {
        private static final BatteryLevelStep DEFAULT_INSTANCE = new BatteryLevelStep();
        public static final int DISPLAY_STATE_FIELD_NUMBER = 3;
        public static final int DURATION_MS_FIELD_NUMBER = 1;
        public static final int IDLE_MODE_FIELD_NUMBER = 5;
        public static final int LEVEL_FIELD_NUMBER = 2;
        private static volatile Parser<BatteryLevelStep> PARSER = null;
        public static final int POWER_SAVE_MODE_FIELD_NUMBER = 4;
        private int bitField0_;
        private int displayState_ = 0;
        private long durationMs_ = 0;
        private int idleMode_ = 0;
        private int level_ = 0;
        private int powerSaveMode_ = 0;

        private BatteryLevelStep() {
        }

        public enum DisplayState implements Internal.EnumLite {
            DS_MIXED(0),
            DS_ON(1),
            DS_OFF(2),
            DS_DOZE(3),
            DS_DOZE_SUSPEND(4),
            DS_ERROR(5);
            
            public static final int DS_DOZE_SUSPEND_VALUE = 4;
            public static final int DS_DOZE_VALUE = 3;
            public static final int DS_ERROR_VALUE = 5;
            public static final int DS_MIXED_VALUE = 0;
            public static final int DS_OFF_VALUE = 2;
            public static final int DS_ON_VALUE = 1;
            private static final Internal.EnumLiteMap<DisplayState> internalValueMap = new Internal.EnumLiteMap<DisplayState>() {
                /* class android.os.SystemProto.BatteryLevelStep.DisplayState.AnonymousClass1 */

                @Override // com.google.protobuf.Internal.EnumLiteMap
                public DisplayState findValueByNumber(int number) {
                    return DisplayState.forNumber(number);
                }
            };
            private final int value;

            @Override // com.google.protobuf.Internal.EnumLite
            public final int getNumber() {
                return this.value;
            }

            @Deprecated
            public static DisplayState valueOf(int value2) {
                return forNumber(value2);
            }

            public static DisplayState forNumber(int value2) {
                if (value2 == 0) {
                    return DS_MIXED;
                }
                if (value2 == 1) {
                    return DS_ON;
                }
                if (value2 == 2) {
                    return DS_OFF;
                }
                if (value2 == 3) {
                    return DS_DOZE;
                }
                if (value2 == 4) {
                    return DS_DOZE_SUSPEND;
                }
                if (value2 != 5) {
                    return null;
                }
                return DS_ERROR;
            }

            public static Internal.EnumLiteMap<DisplayState> internalGetValueMap() {
                return internalValueMap;
            }

            private DisplayState(int value2) {
                this.value = value2;
            }
        }

        public enum PowerSaveMode implements Internal.EnumLite {
            PSM_MIXED(0),
            PSM_ON(1),
            PSM_OFF(2);
            
            public static final int PSM_MIXED_VALUE = 0;
            public static final int PSM_OFF_VALUE = 2;
            public static final int PSM_ON_VALUE = 1;
            private static final Internal.EnumLiteMap<PowerSaveMode> internalValueMap = new Internal.EnumLiteMap<PowerSaveMode>() {
                /* class android.os.SystemProto.BatteryLevelStep.PowerSaveMode.AnonymousClass1 */

                @Override // com.google.protobuf.Internal.EnumLiteMap
                public PowerSaveMode findValueByNumber(int number) {
                    return PowerSaveMode.forNumber(number);
                }
            };
            private final int value;

            @Override // com.google.protobuf.Internal.EnumLite
            public final int getNumber() {
                return this.value;
            }

            @Deprecated
            public static PowerSaveMode valueOf(int value2) {
                return forNumber(value2);
            }

            public static PowerSaveMode forNumber(int value2) {
                if (value2 == 0) {
                    return PSM_MIXED;
                }
                if (value2 == 1) {
                    return PSM_ON;
                }
                if (value2 != 2) {
                    return null;
                }
                return PSM_OFF;
            }

            public static Internal.EnumLiteMap<PowerSaveMode> internalGetValueMap() {
                return internalValueMap;
            }

            private PowerSaveMode(int value2) {
                this.value = value2;
            }
        }

        public enum IdleMode implements Internal.EnumLite {
            IM_MIXED(0),
            IM_ON(2),
            IM_OFF(3);
            
            public static final int IM_MIXED_VALUE = 0;
            public static final int IM_OFF_VALUE = 3;
            public static final int IM_ON_VALUE = 2;
            private static final Internal.EnumLiteMap<IdleMode> internalValueMap = new Internal.EnumLiteMap<IdleMode>() {
                /* class android.os.SystemProto.BatteryLevelStep.IdleMode.AnonymousClass1 */

                @Override // com.google.protobuf.Internal.EnumLiteMap
                public IdleMode findValueByNumber(int number) {
                    return IdleMode.forNumber(number);
                }
            };
            private final int value;

            @Override // com.google.protobuf.Internal.EnumLite
            public final int getNumber() {
                return this.value;
            }

            @Deprecated
            public static IdleMode valueOf(int value2) {
                return forNumber(value2);
            }

            public static IdleMode forNumber(int value2) {
                if (value2 == 0) {
                    return IM_MIXED;
                }
                if (value2 == 2) {
                    return IM_ON;
                }
                if (value2 != 3) {
                    return null;
                }
                return IM_OFF;
            }

            public static Internal.EnumLiteMap<IdleMode> internalGetValueMap() {
                return internalValueMap;
            }

            private IdleMode(int value2) {
                this.value = value2;
            }
        }

        @Override // android.os.SystemProto.BatteryLevelStepOrBuilder
        public boolean hasDurationMs() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // android.os.SystemProto.BatteryLevelStepOrBuilder
        public long getDurationMs() {
            return this.durationMs_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setDurationMs(long value) {
            this.bitField0_ |= 1;
            this.durationMs_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearDurationMs() {
            this.bitField0_ &= -2;
            this.durationMs_ = 0;
        }

        @Override // android.os.SystemProto.BatteryLevelStepOrBuilder
        public boolean hasLevel() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // android.os.SystemProto.BatteryLevelStepOrBuilder
        public int getLevel() {
            return this.level_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setLevel(int value) {
            this.bitField0_ |= 2;
            this.level_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearLevel() {
            this.bitField0_ &= -3;
            this.level_ = 0;
        }

        @Override // android.os.SystemProto.BatteryLevelStepOrBuilder
        public boolean hasDisplayState() {
            return (this.bitField0_ & 4) == 4;
        }

        @Override // android.os.SystemProto.BatteryLevelStepOrBuilder
        public DisplayState getDisplayState() {
            DisplayState result = DisplayState.forNumber(this.displayState_);
            return result == null ? DisplayState.DS_MIXED : result;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setDisplayState(DisplayState value) {
            if (value != null) {
                this.bitField0_ |= 4;
                this.displayState_ = value.getNumber();
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearDisplayState() {
            this.bitField0_ &= -5;
            this.displayState_ = 0;
        }

        @Override // android.os.SystemProto.BatteryLevelStepOrBuilder
        public boolean hasPowerSaveMode() {
            return (this.bitField0_ & 8) == 8;
        }

        @Override // android.os.SystemProto.BatteryLevelStepOrBuilder
        public PowerSaveMode getPowerSaveMode() {
            PowerSaveMode result = PowerSaveMode.forNumber(this.powerSaveMode_);
            return result == null ? PowerSaveMode.PSM_MIXED : result;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setPowerSaveMode(PowerSaveMode value) {
            if (value != null) {
                this.bitField0_ |= 8;
                this.powerSaveMode_ = value.getNumber();
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearPowerSaveMode() {
            this.bitField0_ &= -9;
            this.powerSaveMode_ = 0;
        }

        @Override // android.os.SystemProto.BatteryLevelStepOrBuilder
        public boolean hasIdleMode() {
            return (this.bitField0_ & 16) == 16;
        }

        @Override // android.os.SystemProto.BatteryLevelStepOrBuilder
        public IdleMode getIdleMode() {
            IdleMode result = IdleMode.forNumber(this.idleMode_);
            return result == null ? IdleMode.IM_MIXED : result;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setIdleMode(IdleMode value) {
            if (value != null) {
                this.bitField0_ |= 16;
                this.idleMode_ = value.getNumber();
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearIdleMode() {
            this.bitField0_ &= -17;
            this.idleMode_ = 0;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                output.writeInt64(1, this.durationMs_);
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeInt32(2, this.level_);
            }
            if ((this.bitField0_ & 4) == 4) {
                output.writeEnum(3, this.displayState_);
            }
            if ((this.bitField0_ & 8) == 8) {
                output.writeEnum(4, this.powerSaveMode_);
            }
            if ((this.bitField0_ & 16) == 16) {
                output.writeEnum(5, this.idleMode_);
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
                size2 = 0 + CodedOutputStream.computeInt64Size(1, this.durationMs_);
            }
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeInt32Size(2, this.level_);
            }
            if ((this.bitField0_ & 4) == 4) {
                size2 += CodedOutputStream.computeEnumSize(3, this.displayState_);
            }
            if ((this.bitField0_ & 8) == 8) {
                size2 += CodedOutputStream.computeEnumSize(4, this.powerSaveMode_);
            }
            if ((this.bitField0_ & 16) == 16) {
                size2 += CodedOutputStream.computeEnumSize(5, this.idleMode_);
            }
            int size3 = size2 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static BatteryLevelStep parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (BatteryLevelStep) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static BatteryLevelStep parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (BatteryLevelStep) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static BatteryLevelStep parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (BatteryLevelStep) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static BatteryLevelStep parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (BatteryLevelStep) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static BatteryLevelStep parseFrom(InputStream input) throws IOException {
            return (BatteryLevelStep) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static BatteryLevelStep parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (BatteryLevelStep) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static BatteryLevelStep parseDelimitedFrom(InputStream input) throws IOException {
            return (BatteryLevelStep) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static BatteryLevelStep parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (BatteryLevelStep) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static BatteryLevelStep parseFrom(CodedInputStream input) throws IOException {
            return (BatteryLevelStep) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static BatteryLevelStep parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (BatteryLevelStep) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(BatteryLevelStep prototype) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<BatteryLevelStep, Builder> implements BatteryLevelStepOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 x0) {
                this();
            }

            private Builder() {
                super(BatteryLevelStep.DEFAULT_INSTANCE);
            }

            @Override // android.os.SystemProto.BatteryLevelStepOrBuilder
            public boolean hasDurationMs() {
                return ((BatteryLevelStep) this.instance).hasDurationMs();
            }

            @Override // android.os.SystemProto.BatteryLevelStepOrBuilder
            public long getDurationMs() {
                return ((BatteryLevelStep) this.instance).getDurationMs();
            }

            public Builder setDurationMs(long value) {
                copyOnWrite();
                ((BatteryLevelStep) this.instance).setDurationMs(value);
                return this;
            }

            public Builder clearDurationMs() {
                copyOnWrite();
                ((BatteryLevelStep) this.instance).clearDurationMs();
                return this;
            }

            @Override // android.os.SystemProto.BatteryLevelStepOrBuilder
            public boolean hasLevel() {
                return ((BatteryLevelStep) this.instance).hasLevel();
            }

            @Override // android.os.SystemProto.BatteryLevelStepOrBuilder
            public int getLevel() {
                return ((BatteryLevelStep) this.instance).getLevel();
            }

            public Builder setLevel(int value) {
                copyOnWrite();
                ((BatteryLevelStep) this.instance).setLevel(value);
                return this;
            }

            public Builder clearLevel() {
                copyOnWrite();
                ((BatteryLevelStep) this.instance).clearLevel();
                return this;
            }

            @Override // android.os.SystemProto.BatteryLevelStepOrBuilder
            public boolean hasDisplayState() {
                return ((BatteryLevelStep) this.instance).hasDisplayState();
            }

            @Override // android.os.SystemProto.BatteryLevelStepOrBuilder
            public DisplayState getDisplayState() {
                return ((BatteryLevelStep) this.instance).getDisplayState();
            }

            public Builder setDisplayState(DisplayState value) {
                copyOnWrite();
                ((BatteryLevelStep) this.instance).setDisplayState(value);
                return this;
            }

            public Builder clearDisplayState() {
                copyOnWrite();
                ((BatteryLevelStep) this.instance).clearDisplayState();
                return this;
            }

            @Override // android.os.SystemProto.BatteryLevelStepOrBuilder
            public boolean hasPowerSaveMode() {
                return ((BatteryLevelStep) this.instance).hasPowerSaveMode();
            }

            @Override // android.os.SystemProto.BatteryLevelStepOrBuilder
            public PowerSaveMode getPowerSaveMode() {
                return ((BatteryLevelStep) this.instance).getPowerSaveMode();
            }

            public Builder setPowerSaveMode(PowerSaveMode value) {
                copyOnWrite();
                ((BatteryLevelStep) this.instance).setPowerSaveMode(value);
                return this;
            }

            public Builder clearPowerSaveMode() {
                copyOnWrite();
                ((BatteryLevelStep) this.instance).clearPowerSaveMode();
                return this;
            }

            @Override // android.os.SystemProto.BatteryLevelStepOrBuilder
            public boolean hasIdleMode() {
                return ((BatteryLevelStep) this.instance).hasIdleMode();
            }

            @Override // android.os.SystemProto.BatteryLevelStepOrBuilder
            public IdleMode getIdleMode() {
                return ((BatteryLevelStep) this.instance).getIdleMode();
            }

            public Builder setIdleMode(IdleMode value) {
                copyOnWrite();
                ((BatteryLevelStep) this.instance).setIdleMode(value);
                return this;
            }

            public Builder clearIdleMode() {
                copyOnWrite();
                ((BatteryLevelStep) this.instance).clearIdleMode();
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new BatteryLevelStep();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder(null);
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    BatteryLevelStep other = (BatteryLevelStep) arg1;
                    this.durationMs_ = visitor.visitLong(hasDurationMs(), this.durationMs_, other.hasDurationMs(), other.durationMs_);
                    this.level_ = visitor.visitInt(hasLevel(), this.level_, other.hasLevel(), other.level_);
                    this.displayState_ = visitor.visitInt(hasDisplayState(), this.displayState_, other.hasDisplayState(), other.displayState_);
                    this.powerSaveMode_ = visitor.visitInt(hasPowerSaveMode(), this.powerSaveMode_, other.hasPowerSaveMode(), other.powerSaveMode_);
                    this.idleMode_ = visitor.visitInt(hasIdleMode(), this.idleMode_, other.hasIdleMode(), other.idleMode_);
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
                                this.durationMs_ = input.readInt64();
                            } else if (tag == 16) {
                                this.bitField0_ |= 2;
                                this.level_ = input.readInt32();
                            } else if (tag == 24) {
                                int rawValue = input.readEnum();
                                if (DisplayState.forNumber(rawValue) == null) {
                                    super.mergeVarintField(3, rawValue);
                                } else {
                                    this.bitField0_ |= 4;
                                    this.displayState_ = rawValue;
                                }
                            } else if (tag == 32) {
                                int rawValue2 = input.readEnum();
                                if (PowerSaveMode.forNumber(rawValue2) == null) {
                                    super.mergeVarintField(4, rawValue2);
                                } else {
                                    this.bitField0_ = 8 | this.bitField0_;
                                    this.powerSaveMode_ = rawValue2;
                                }
                            } else if (tag == 40) {
                                int rawValue3 = input.readEnum();
                                if (IdleMode.forNumber(rawValue3) == null) {
                                    super.mergeVarintField(5, rawValue3);
                                } else {
                                    this.bitField0_ = 16 | this.bitField0_;
                                    this.idleMode_ = rawValue3;
                                }
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
                        synchronized (BatteryLevelStep.class) {
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

        public static BatteryLevelStep getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<BatteryLevelStep> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class DataConnection extends GeneratedMessageLite<DataConnection, Builder> implements DataConnectionOrBuilder {
        private static final DataConnection DEFAULT_INSTANCE = new DataConnection();
        public static final int IS_NONE_FIELD_NUMBER = 2;
        public static final int NAME_FIELD_NUMBER = 1;
        private static volatile Parser<DataConnection> PARSER = null;
        public static final int TOTAL_FIELD_NUMBER = 3;
        private int bitField0_;
        private TimerProto total_;
        private int typeCase_ = 0;
        private Object type_;

        private DataConnection() {
        }

        public enum TypeCase implements Internal.EnumLite {
            NAME(1),
            IS_NONE(2),
            TYPE_NOT_SET(0);
            
            private final int value;

            private TypeCase(int value2) {
                this.value = value2;
            }

            @Deprecated
            public static TypeCase valueOf(int value2) {
                return forNumber(value2);
            }

            public static TypeCase forNumber(int value2) {
                if (value2 == 0) {
                    return TYPE_NOT_SET;
                }
                if (value2 == 1) {
                    return NAME;
                }
                if (value2 != 2) {
                    return null;
                }
                return IS_NONE;
            }

            @Override // com.google.protobuf.Internal.EnumLite
            public int getNumber() {
                return this.value;
            }
        }

        @Override // android.os.SystemProto.DataConnectionOrBuilder
        public TypeCase getTypeCase() {
            return TypeCase.forNumber(this.typeCase_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearType() {
            this.typeCase_ = 0;
            this.type_ = null;
        }

        @Override // android.os.SystemProto.DataConnectionOrBuilder
        public boolean hasName() {
            return this.typeCase_ == 1;
        }

        @Override // android.os.SystemProto.DataConnectionOrBuilder
        public NetworkTypeEnum getName() {
            if (this.typeCase_ != 1) {
                return NetworkTypeEnum.NETWORK_TYPE_UNKNOWN;
            }
            NetworkTypeEnum result = NetworkTypeEnum.forNumber(((Integer) this.type_).intValue());
            return result == null ? NetworkTypeEnum.NETWORK_TYPE_UNKNOWN : result;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setName(NetworkTypeEnum value) {
            if (value != null) {
                this.typeCase_ = 1;
                this.type_ = Integer.valueOf(value.getNumber());
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearName() {
            if (this.typeCase_ == 1) {
                this.typeCase_ = 0;
                this.type_ = null;
            }
        }

        @Override // android.os.SystemProto.DataConnectionOrBuilder
        public boolean hasIsNone() {
            return this.typeCase_ == 2;
        }

        @Override // android.os.SystemProto.DataConnectionOrBuilder
        public boolean getIsNone() {
            if (this.typeCase_ == 2) {
                return ((Boolean) this.type_).booleanValue();
            }
            return false;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setIsNone(boolean value) {
            this.typeCase_ = 2;
            this.type_ = Boolean.valueOf(value);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearIsNone() {
            if (this.typeCase_ == 2) {
                this.typeCase_ = 0;
                this.type_ = null;
            }
        }

        @Override // android.os.SystemProto.DataConnectionOrBuilder
        public boolean hasTotal() {
            return (this.bitField0_ & 4) == 4;
        }

        @Override // android.os.SystemProto.DataConnectionOrBuilder
        public TimerProto getTotal() {
            TimerProto timerProto = this.total_;
            return timerProto == null ? TimerProto.getDefaultInstance() : timerProto;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setTotal(TimerProto value) {
            if (value != null) {
                this.total_ = value;
                this.bitField0_ |= 4;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setTotal(TimerProto.Builder builderForValue) {
            this.total_ = (TimerProto) builderForValue.build();
            this.bitField0_ |= 4;
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
            this.bitField0_ |= 4;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearTotal() {
            this.total_ = null;
            this.bitField0_ &= -5;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if (this.typeCase_ == 1) {
                output.writeEnum(1, ((Integer) this.type_).intValue());
            }
            if (this.typeCase_ == 2) {
                output.writeBool(2, ((Boolean) this.type_).booleanValue());
            }
            if ((this.bitField0_ & 4) == 4) {
                output.writeMessage(3, getTotal());
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
            if (this.typeCase_ == 1) {
                size2 = 0 + CodedOutputStream.computeEnumSize(1, ((Integer) this.type_).intValue());
            }
            if (this.typeCase_ == 2) {
                size2 += CodedOutputStream.computeBoolSize(2, ((Boolean) this.type_).booleanValue());
            }
            if ((this.bitField0_ & 4) == 4) {
                size2 += CodedOutputStream.computeMessageSize(3, getTotal());
            }
            int size3 = size2 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static DataConnection parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (DataConnection) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static DataConnection parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (DataConnection) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static DataConnection parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (DataConnection) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static DataConnection parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (DataConnection) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static DataConnection parseFrom(InputStream input) throws IOException {
            return (DataConnection) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static DataConnection parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (DataConnection) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static DataConnection parseDelimitedFrom(InputStream input) throws IOException {
            return (DataConnection) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static DataConnection parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (DataConnection) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static DataConnection parseFrom(CodedInputStream input) throws IOException {
            return (DataConnection) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static DataConnection parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (DataConnection) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(DataConnection prototype) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<DataConnection, Builder> implements DataConnectionOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 x0) {
                this();
            }

            private Builder() {
                super(DataConnection.DEFAULT_INSTANCE);
            }

            @Override // android.os.SystemProto.DataConnectionOrBuilder
            public TypeCase getTypeCase() {
                return ((DataConnection) this.instance).getTypeCase();
            }

            public Builder clearType() {
                copyOnWrite();
                ((DataConnection) this.instance).clearType();
                return this;
            }

            @Override // android.os.SystemProto.DataConnectionOrBuilder
            public boolean hasName() {
                return ((DataConnection) this.instance).hasName();
            }

            @Override // android.os.SystemProto.DataConnectionOrBuilder
            public NetworkTypeEnum getName() {
                return ((DataConnection) this.instance).getName();
            }

            public Builder setName(NetworkTypeEnum value) {
                copyOnWrite();
                ((DataConnection) this.instance).setName(value);
                return this;
            }

            public Builder clearName() {
                copyOnWrite();
                ((DataConnection) this.instance).clearName();
                return this;
            }

            @Override // android.os.SystemProto.DataConnectionOrBuilder
            public boolean hasIsNone() {
                return ((DataConnection) this.instance).hasIsNone();
            }

            @Override // android.os.SystemProto.DataConnectionOrBuilder
            public boolean getIsNone() {
                return ((DataConnection) this.instance).getIsNone();
            }

            public Builder setIsNone(boolean value) {
                copyOnWrite();
                ((DataConnection) this.instance).setIsNone(value);
                return this;
            }

            public Builder clearIsNone() {
                copyOnWrite();
                ((DataConnection) this.instance).clearIsNone();
                return this;
            }

            @Override // android.os.SystemProto.DataConnectionOrBuilder
            public boolean hasTotal() {
                return ((DataConnection) this.instance).hasTotal();
            }

            @Override // android.os.SystemProto.DataConnectionOrBuilder
            public TimerProto getTotal() {
                return ((DataConnection) this.instance).getTotal();
            }

            public Builder setTotal(TimerProto value) {
                copyOnWrite();
                ((DataConnection) this.instance).setTotal((DataConnection) value);
                return this;
            }

            public Builder setTotal(TimerProto.Builder builderForValue) {
                copyOnWrite();
                ((DataConnection) this.instance).setTotal((DataConnection) builderForValue);
                return this;
            }

            public Builder mergeTotal(TimerProto value) {
                copyOnWrite();
                ((DataConnection) this.instance).mergeTotal(value);
                return this;
            }

            public Builder clearTotal() {
                copyOnWrite();
                ((DataConnection) this.instance).clearTotal();
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
                    return new DataConnection();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder(null);
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    DataConnection other = (DataConnection) arg1;
                    this.total_ = (TimerProto) visitor.visitMessage(this.total_, other.total_);
                    int i = AnonymousClass1.$SwitchMap$android$os$SystemProto$DataConnection$TypeCase[other.getTypeCase().ordinal()];
                    if (i == 1) {
                        if (this.typeCase_ != 1) {
                            z = false;
                        }
                        this.type_ = visitor.visitOneofInt(z, this.type_, other.type_);
                    } else if (i == 2) {
                        if (this.typeCase_ != 2) {
                            z = false;
                        }
                        this.type_ = visitor.visitOneofBoolean(z, this.type_, other.type_);
                    } else if (i == 3) {
                        if (this.typeCase_ == 0) {
                            z = false;
                        }
                        visitor.visitOneofNotSet(z);
                    }
                    if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE) {
                        int i2 = other.typeCase_;
                        if (i2 != 0) {
                            this.typeCase_ = i2;
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
                            } else if (tag == 8) {
                                int rawValue = input.readEnum();
                                if (NetworkTypeEnum.forNumber(rawValue) == null) {
                                    super.mergeVarintField(1, rawValue);
                                } else {
                                    this.typeCase_ = 1;
                                    this.type_ = Integer.valueOf(rawValue);
                                }
                            } else if (tag == 16) {
                                this.typeCase_ = 2;
                                this.type_ = Boolean.valueOf(input.readBool());
                            } else if (tag == 26) {
                                TimerProto.Builder subBuilder = null;
                                if ((this.bitField0_ & 4) == 4) {
                                    subBuilder = (TimerProto.Builder) this.total_.toBuilder();
                                }
                                this.total_ = (TimerProto) input.readMessage(TimerProto.parser(), extensionRegistry);
                                if (subBuilder != null) {
                                    subBuilder.mergeFrom((GeneratedMessageLite) this.total_);
                                    this.total_ = (TimerProto) subBuilder.buildPartial();
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
                        synchronized (DataConnection.class) {
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

        public static DataConnection getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<DataConnection> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class GlobalNetwork extends GeneratedMessageLite<GlobalNetwork, Builder> implements GlobalNetworkOrBuilder {
        public static final int BT_BYTES_RX_FIELD_NUMBER = 9;
        public static final int BT_BYTES_TX_FIELD_NUMBER = 10;
        private static final GlobalNetwork DEFAULT_INSTANCE = new GlobalNetwork();
        public static final int MOBILE_BYTES_RX_FIELD_NUMBER = 1;
        public static final int MOBILE_BYTES_TX_FIELD_NUMBER = 2;
        public static final int MOBILE_PACKETS_RX_FIELD_NUMBER = 5;
        public static final int MOBILE_PACKETS_TX_FIELD_NUMBER = 6;
        private static volatile Parser<GlobalNetwork> PARSER = null;
        public static final int WIFI_BYTES_RX_FIELD_NUMBER = 3;
        public static final int WIFI_BYTES_TX_FIELD_NUMBER = 4;
        public static final int WIFI_PACKETS_RX_FIELD_NUMBER = 7;
        public static final int WIFI_PACKETS_TX_FIELD_NUMBER = 8;
        private int bitField0_;
        private long btBytesRx_ = 0;
        private long btBytesTx_ = 0;
        private long mobileBytesRx_ = 0;
        private long mobileBytesTx_ = 0;
        private long mobilePacketsRx_ = 0;
        private long mobilePacketsTx_ = 0;
        private long wifiBytesRx_ = 0;
        private long wifiBytesTx_ = 0;
        private long wifiPacketsRx_ = 0;
        private long wifiPacketsTx_ = 0;

        private GlobalNetwork() {
        }

        @Override // android.os.SystemProto.GlobalNetworkOrBuilder
        public boolean hasMobileBytesRx() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // android.os.SystemProto.GlobalNetworkOrBuilder
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

        @Override // android.os.SystemProto.GlobalNetworkOrBuilder
        public boolean hasMobileBytesTx() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // android.os.SystemProto.GlobalNetworkOrBuilder
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

        @Override // android.os.SystemProto.GlobalNetworkOrBuilder
        public boolean hasWifiBytesRx() {
            return (this.bitField0_ & 4) == 4;
        }

        @Override // android.os.SystemProto.GlobalNetworkOrBuilder
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

        @Override // android.os.SystemProto.GlobalNetworkOrBuilder
        public boolean hasWifiBytesTx() {
            return (this.bitField0_ & 8) == 8;
        }

        @Override // android.os.SystemProto.GlobalNetworkOrBuilder
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

        @Override // android.os.SystemProto.GlobalNetworkOrBuilder
        public boolean hasMobilePacketsRx() {
            return (this.bitField0_ & 16) == 16;
        }

        @Override // android.os.SystemProto.GlobalNetworkOrBuilder
        public long getMobilePacketsRx() {
            return this.mobilePacketsRx_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setMobilePacketsRx(long value) {
            this.bitField0_ |= 16;
            this.mobilePacketsRx_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearMobilePacketsRx() {
            this.bitField0_ &= -17;
            this.mobilePacketsRx_ = 0;
        }

        @Override // android.os.SystemProto.GlobalNetworkOrBuilder
        public boolean hasMobilePacketsTx() {
            return (this.bitField0_ & 32) == 32;
        }

        @Override // android.os.SystemProto.GlobalNetworkOrBuilder
        public long getMobilePacketsTx() {
            return this.mobilePacketsTx_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setMobilePacketsTx(long value) {
            this.bitField0_ |= 32;
            this.mobilePacketsTx_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearMobilePacketsTx() {
            this.bitField0_ &= -33;
            this.mobilePacketsTx_ = 0;
        }

        @Override // android.os.SystemProto.GlobalNetworkOrBuilder
        public boolean hasWifiPacketsRx() {
            return (this.bitField0_ & 64) == 64;
        }

        @Override // android.os.SystemProto.GlobalNetworkOrBuilder
        public long getWifiPacketsRx() {
            return this.wifiPacketsRx_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setWifiPacketsRx(long value) {
            this.bitField0_ |= 64;
            this.wifiPacketsRx_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearWifiPacketsRx() {
            this.bitField0_ &= -65;
            this.wifiPacketsRx_ = 0;
        }

        @Override // android.os.SystemProto.GlobalNetworkOrBuilder
        public boolean hasWifiPacketsTx() {
            return (this.bitField0_ & 128) == 128;
        }

        @Override // android.os.SystemProto.GlobalNetworkOrBuilder
        public long getWifiPacketsTx() {
            return this.wifiPacketsTx_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setWifiPacketsTx(long value) {
            this.bitField0_ |= 128;
            this.wifiPacketsTx_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearWifiPacketsTx() {
            this.bitField0_ &= -129;
            this.wifiPacketsTx_ = 0;
        }

        @Override // android.os.SystemProto.GlobalNetworkOrBuilder
        public boolean hasBtBytesRx() {
            return (this.bitField0_ & 256) == 256;
        }

        @Override // android.os.SystemProto.GlobalNetworkOrBuilder
        public long getBtBytesRx() {
            return this.btBytesRx_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setBtBytesRx(long value) {
            this.bitField0_ |= 256;
            this.btBytesRx_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearBtBytesRx() {
            this.bitField0_ &= -257;
            this.btBytesRx_ = 0;
        }

        @Override // android.os.SystemProto.GlobalNetworkOrBuilder
        public boolean hasBtBytesTx() {
            return (this.bitField0_ & 512) == 512;
        }

        @Override // android.os.SystemProto.GlobalNetworkOrBuilder
        public long getBtBytesTx() {
            return this.btBytesTx_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setBtBytesTx(long value) {
            this.bitField0_ |= 512;
            this.btBytesTx_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearBtBytesTx() {
            this.bitField0_ &= -513;
            this.btBytesTx_ = 0;
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
                output.writeInt64(5, this.mobilePacketsRx_);
            }
            if ((this.bitField0_ & 32) == 32) {
                output.writeInt64(6, this.mobilePacketsTx_);
            }
            if ((this.bitField0_ & 64) == 64) {
                output.writeInt64(7, this.wifiPacketsRx_);
            }
            if ((this.bitField0_ & 128) == 128) {
                output.writeInt64(8, this.wifiPacketsTx_);
            }
            if ((this.bitField0_ & 256) == 256) {
                output.writeInt64(9, this.btBytesRx_);
            }
            if ((this.bitField0_ & 512) == 512) {
                output.writeInt64(10, this.btBytesTx_);
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
                size2 += CodedOutputStream.computeInt64Size(5, this.mobilePacketsRx_);
            }
            if ((this.bitField0_ & 32) == 32) {
                size2 += CodedOutputStream.computeInt64Size(6, this.mobilePacketsTx_);
            }
            if ((this.bitField0_ & 64) == 64) {
                size2 += CodedOutputStream.computeInt64Size(7, this.wifiPacketsRx_);
            }
            if ((this.bitField0_ & 128) == 128) {
                size2 += CodedOutputStream.computeInt64Size(8, this.wifiPacketsTx_);
            }
            if ((this.bitField0_ & 256) == 256) {
                size2 += CodedOutputStream.computeInt64Size(9, this.btBytesRx_);
            }
            if ((this.bitField0_ & 512) == 512) {
                size2 += CodedOutputStream.computeInt64Size(10, this.btBytesTx_);
            }
            int size3 = size2 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static GlobalNetwork parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (GlobalNetwork) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static GlobalNetwork parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (GlobalNetwork) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static GlobalNetwork parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (GlobalNetwork) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static GlobalNetwork parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (GlobalNetwork) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static GlobalNetwork parseFrom(InputStream input) throws IOException {
            return (GlobalNetwork) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static GlobalNetwork parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (GlobalNetwork) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static GlobalNetwork parseDelimitedFrom(InputStream input) throws IOException {
            return (GlobalNetwork) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static GlobalNetwork parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (GlobalNetwork) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static GlobalNetwork parseFrom(CodedInputStream input) throws IOException {
            return (GlobalNetwork) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static GlobalNetwork parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (GlobalNetwork) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(GlobalNetwork prototype) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<GlobalNetwork, Builder> implements GlobalNetworkOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 x0) {
                this();
            }

            private Builder() {
                super(GlobalNetwork.DEFAULT_INSTANCE);
            }

            @Override // android.os.SystemProto.GlobalNetworkOrBuilder
            public boolean hasMobileBytesRx() {
                return ((GlobalNetwork) this.instance).hasMobileBytesRx();
            }

            @Override // android.os.SystemProto.GlobalNetworkOrBuilder
            public long getMobileBytesRx() {
                return ((GlobalNetwork) this.instance).getMobileBytesRx();
            }

            public Builder setMobileBytesRx(long value) {
                copyOnWrite();
                ((GlobalNetwork) this.instance).setMobileBytesRx(value);
                return this;
            }

            public Builder clearMobileBytesRx() {
                copyOnWrite();
                ((GlobalNetwork) this.instance).clearMobileBytesRx();
                return this;
            }

            @Override // android.os.SystemProto.GlobalNetworkOrBuilder
            public boolean hasMobileBytesTx() {
                return ((GlobalNetwork) this.instance).hasMobileBytesTx();
            }

            @Override // android.os.SystemProto.GlobalNetworkOrBuilder
            public long getMobileBytesTx() {
                return ((GlobalNetwork) this.instance).getMobileBytesTx();
            }

            public Builder setMobileBytesTx(long value) {
                copyOnWrite();
                ((GlobalNetwork) this.instance).setMobileBytesTx(value);
                return this;
            }

            public Builder clearMobileBytesTx() {
                copyOnWrite();
                ((GlobalNetwork) this.instance).clearMobileBytesTx();
                return this;
            }

            @Override // android.os.SystemProto.GlobalNetworkOrBuilder
            public boolean hasWifiBytesRx() {
                return ((GlobalNetwork) this.instance).hasWifiBytesRx();
            }

            @Override // android.os.SystemProto.GlobalNetworkOrBuilder
            public long getWifiBytesRx() {
                return ((GlobalNetwork) this.instance).getWifiBytesRx();
            }

            public Builder setWifiBytesRx(long value) {
                copyOnWrite();
                ((GlobalNetwork) this.instance).setWifiBytesRx(value);
                return this;
            }

            public Builder clearWifiBytesRx() {
                copyOnWrite();
                ((GlobalNetwork) this.instance).clearWifiBytesRx();
                return this;
            }

            @Override // android.os.SystemProto.GlobalNetworkOrBuilder
            public boolean hasWifiBytesTx() {
                return ((GlobalNetwork) this.instance).hasWifiBytesTx();
            }

            @Override // android.os.SystemProto.GlobalNetworkOrBuilder
            public long getWifiBytesTx() {
                return ((GlobalNetwork) this.instance).getWifiBytesTx();
            }

            public Builder setWifiBytesTx(long value) {
                copyOnWrite();
                ((GlobalNetwork) this.instance).setWifiBytesTx(value);
                return this;
            }

            public Builder clearWifiBytesTx() {
                copyOnWrite();
                ((GlobalNetwork) this.instance).clearWifiBytesTx();
                return this;
            }

            @Override // android.os.SystemProto.GlobalNetworkOrBuilder
            public boolean hasMobilePacketsRx() {
                return ((GlobalNetwork) this.instance).hasMobilePacketsRx();
            }

            @Override // android.os.SystemProto.GlobalNetworkOrBuilder
            public long getMobilePacketsRx() {
                return ((GlobalNetwork) this.instance).getMobilePacketsRx();
            }

            public Builder setMobilePacketsRx(long value) {
                copyOnWrite();
                ((GlobalNetwork) this.instance).setMobilePacketsRx(value);
                return this;
            }

            public Builder clearMobilePacketsRx() {
                copyOnWrite();
                ((GlobalNetwork) this.instance).clearMobilePacketsRx();
                return this;
            }

            @Override // android.os.SystemProto.GlobalNetworkOrBuilder
            public boolean hasMobilePacketsTx() {
                return ((GlobalNetwork) this.instance).hasMobilePacketsTx();
            }

            @Override // android.os.SystemProto.GlobalNetworkOrBuilder
            public long getMobilePacketsTx() {
                return ((GlobalNetwork) this.instance).getMobilePacketsTx();
            }

            public Builder setMobilePacketsTx(long value) {
                copyOnWrite();
                ((GlobalNetwork) this.instance).setMobilePacketsTx(value);
                return this;
            }

            public Builder clearMobilePacketsTx() {
                copyOnWrite();
                ((GlobalNetwork) this.instance).clearMobilePacketsTx();
                return this;
            }

            @Override // android.os.SystemProto.GlobalNetworkOrBuilder
            public boolean hasWifiPacketsRx() {
                return ((GlobalNetwork) this.instance).hasWifiPacketsRx();
            }

            @Override // android.os.SystemProto.GlobalNetworkOrBuilder
            public long getWifiPacketsRx() {
                return ((GlobalNetwork) this.instance).getWifiPacketsRx();
            }

            public Builder setWifiPacketsRx(long value) {
                copyOnWrite();
                ((GlobalNetwork) this.instance).setWifiPacketsRx(value);
                return this;
            }

            public Builder clearWifiPacketsRx() {
                copyOnWrite();
                ((GlobalNetwork) this.instance).clearWifiPacketsRx();
                return this;
            }

            @Override // android.os.SystemProto.GlobalNetworkOrBuilder
            public boolean hasWifiPacketsTx() {
                return ((GlobalNetwork) this.instance).hasWifiPacketsTx();
            }

            @Override // android.os.SystemProto.GlobalNetworkOrBuilder
            public long getWifiPacketsTx() {
                return ((GlobalNetwork) this.instance).getWifiPacketsTx();
            }

            public Builder setWifiPacketsTx(long value) {
                copyOnWrite();
                ((GlobalNetwork) this.instance).setWifiPacketsTx(value);
                return this;
            }

            public Builder clearWifiPacketsTx() {
                copyOnWrite();
                ((GlobalNetwork) this.instance).clearWifiPacketsTx();
                return this;
            }

            @Override // android.os.SystemProto.GlobalNetworkOrBuilder
            public boolean hasBtBytesRx() {
                return ((GlobalNetwork) this.instance).hasBtBytesRx();
            }

            @Override // android.os.SystemProto.GlobalNetworkOrBuilder
            public long getBtBytesRx() {
                return ((GlobalNetwork) this.instance).getBtBytesRx();
            }

            public Builder setBtBytesRx(long value) {
                copyOnWrite();
                ((GlobalNetwork) this.instance).setBtBytesRx(value);
                return this;
            }

            public Builder clearBtBytesRx() {
                copyOnWrite();
                ((GlobalNetwork) this.instance).clearBtBytesRx();
                return this;
            }

            @Override // android.os.SystemProto.GlobalNetworkOrBuilder
            public boolean hasBtBytesTx() {
                return ((GlobalNetwork) this.instance).hasBtBytesTx();
            }

            @Override // android.os.SystemProto.GlobalNetworkOrBuilder
            public long getBtBytesTx() {
                return ((GlobalNetwork) this.instance).getBtBytesTx();
            }

            public Builder setBtBytesTx(long value) {
                copyOnWrite();
                ((GlobalNetwork) this.instance).setBtBytesTx(value);
                return this;
            }

            public Builder clearBtBytesTx() {
                copyOnWrite();
                ((GlobalNetwork) this.instance).clearBtBytesTx();
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new GlobalNetwork();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder(null);
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    GlobalNetwork other = (GlobalNetwork) arg1;
                    this.mobileBytesRx_ = visitor.visitLong(hasMobileBytesRx(), this.mobileBytesRx_, other.hasMobileBytesRx(), other.mobileBytesRx_);
                    this.mobileBytesTx_ = visitor.visitLong(hasMobileBytesTx(), this.mobileBytesTx_, other.hasMobileBytesTx(), other.mobileBytesTx_);
                    this.wifiBytesRx_ = visitor.visitLong(hasWifiBytesRx(), this.wifiBytesRx_, other.hasWifiBytesRx(), other.wifiBytesRx_);
                    this.wifiBytesTx_ = visitor.visitLong(hasWifiBytesTx(), this.wifiBytesTx_, other.hasWifiBytesTx(), other.wifiBytesTx_);
                    this.mobilePacketsRx_ = visitor.visitLong(hasMobilePacketsRx(), this.mobilePacketsRx_, other.hasMobilePacketsRx(), other.mobilePacketsRx_);
                    this.mobilePacketsTx_ = visitor.visitLong(hasMobilePacketsTx(), this.mobilePacketsTx_, other.hasMobilePacketsTx(), other.mobilePacketsTx_);
                    this.wifiPacketsRx_ = visitor.visitLong(hasWifiPacketsRx(), this.wifiPacketsRx_, other.hasWifiPacketsRx(), other.wifiPacketsRx_);
                    this.wifiPacketsTx_ = visitor.visitLong(hasWifiPacketsTx(), this.wifiPacketsTx_, other.hasWifiPacketsTx(), other.wifiPacketsTx_);
                    this.btBytesRx_ = visitor.visitLong(hasBtBytesRx(), this.btBytesRx_, other.hasBtBytesRx(), other.btBytesRx_);
                    this.btBytesTx_ = visitor.visitLong(hasBtBytesTx(), this.btBytesTx_, other.hasBtBytesTx(), other.btBytesTx_);
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
                                    this.mobilePacketsRx_ = input.readInt64();
                                    break;
                                case 48:
                                    this.bitField0_ |= 32;
                                    this.mobilePacketsTx_ = input.readInt64();
                                    break;
                                case 56:
                                    this.bitField0_ |= 64;
                                    this.wifiPacketsRx_ = input.readInt64();
                                    break;
                                case 64:
                                    this.bitField0_ |= 128;
                                    this.wifiPacketsTx_ = input.readInt64();
                                    break;
                                case 72:
                                    this.bitField0_ |= 256;
                                    this.btBytesRx_ = input.readInt64();
                                    break;
                                case 80:
                                    this.bitField0_ |= 512;
                                    this.btBytesTx_ = input.readInt64();
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
                        synchronized (GlobalNetwork.class) {
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

        public static GlobalNetwork getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<GlobalNetwork> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class GlobalWifi extends GeneratedMessageLite<GlobalWifi, Builder> implements GlobalWifiOrBuilder {
        private static final GlobalWifi DEFAULT_INSTANCE = new GlobalWifi();
        public static final int ON_DURATION_MS_FIELD_NUMBER = 1;
        private static volatile Parser<GlobalWifi> PARSER = null;
        public static final int RUNNING_DURATION_MS_FIELD_NUMBER = 2;
        private int bitField0_;
        private long onDurationMs_ = 0;
        private long runningDurationMs_ = 0;

        private GlobalWifi() {
        }

        @Override // android.os.SystemProto.GlobalWifiOrBuilder
        public boolean hasOnDurationMs() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // android.os.SystemProto.GlobalWifiOrBuilder
        public long getOnDurationMs() {
            return this.onDurationMs_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setOnDurationMs(long value) {
            this.bitField0_ |= 1;
            this.onDurationMs_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearOnDurationMs() {
            this.bitField0_ &= -2;
            this.onDurationMs_ = 0;
        }

        @Override // android.os.SystemProto.GlobalWifiOrBuilder
        public boolean hasRunningDurationMs() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // android.os.SystemProto.GlobalWifiOrBuilder
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

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                output.writeInt64(1, this.onDurationMs_);
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeInt64(2, this.runningDurationMs_);
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
                size2 = 0 + CodedOutputStream.computeInt64Size(1, this.onDurationMs_);
            }
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeInt64Size(2, this.runningDurationMs_);
            }
            int size3 = size2 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static GlobalWifi parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (GlobalWifi) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static GlobalWifi parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (GlobalWifi) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static GlobalWifi parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (GlobalWifi) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static GlobalWifi parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (GlobalWifi) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static GlobalWifi parseFrom(InputStream input) throws IOException {
            return (GlobalWifi) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static GlobalWifi parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (GlobalWifi) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static GlobalWifi parseDelimitedFrom(InputStream input) throws IOException {
            return (GlobalWifi) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static GlobalWifi parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (GlobalWifi) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static GlobalWifi parseFrom(CodedInputStream input) throws IOException {
            return (GlobalWifi) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static GlobalWifi parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (GlobalWifi) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(GlobalWifi prototype) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<GlobalWifi, Builder> implements GlobalWifiOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 x0) {
                this();
            }

            private Builder() {
                super(GlobalWifi.DEFAULT_INSTANCE);
            }

            @Override // android.os.SystemProto.GlobalWifiOrBuilder
            public boolean hasOnDurationMs() {
                return ((GlobalWifi) this.instance).hasOnDurationMs();
            }

            @Override // android.os.SystemProto.GlobalWifiOrBuilder
            public long getOnDurationMs() {
                return ((GlobalWifi) this.instance).getOnDurationMs();
            }

            public Builder setOnDurationMs(long value) {
                copyOnWrite();
                ((GlobalWifi) this.instance).setOnDurationMs(value);
                return this;
            }

            public Builder clearOnDurationMs() {
                copyOnWrite();
                ((GlobalWifi) this.instance).clearOnDurationMs();
                return this;
            }

            @Override // android.os.SystemProto.GlobalWifiOrBuilder
            public boolean hasRunningDurationMs() {
                return ((GlobalWifi) this.instance).hasRunningDurationMs();
            }

            @Override // android.os.SystemProto.GlobalWifiOrBuilder
            public long getRunningDurationMs() {
                return ((GlobalWifi) this.instance).getRunningDurationMs();
            }

            public Builder setRunningDurationMs(long value) {
                copyOnWrite();
                ((GlobalWifi) this.instance).setRunningDurationMs(value);
                return this;
            }

            public Builder clearRunningDurationMs() {
                copyOnWrite();
                ((GlobalWifi) this.instance).clearRunningDurationMs();
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new GlobalWifi();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder(null);
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    GlobalWifi other = (GlobalWifi) arg1;
                    this.onDurationMs_ = visitor.visitLong(hasOnDurationMs(), this.onDurationMs_, other.hasOnDurationMs(), other.onDurationMs_);
                    this.runningDurationMs_ = visitor.visitLong(hasRunningDurationMs(), this.runningDurationMs_, other.hasRunningDurationMs(), other.runningDurationMs_);
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
                                this.onDurationMs_ = input.readInt64();
                            } else if (tag == 16) {
                                this.bitField0_ |= 2;
                                this.runningDurationMs_ = input.readInt64();
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
                        synchronized (GlobalWifi.class) {
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

        public static GlobalWifi getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<GlobalWifi> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class KernelWakelock extends GeneratedMessageLite<KernelWakelock, Builder> implements KernelWakelockOrBuilder {
        private static final KernelWakelock DEFAULT_INSTANCE = new KernelWakelock();
        public static final int NAME_FIELD_NUMBER = 1;
        private static volatile Parser<KernelWakelock> PARSER = null;
        public static final int TOTAL_FIELD_NUMBER = 2;
        private int bitField0_;
        private String name_ = "";
        private TimerProto total_;

        private KernelWakelock() {
        }

        @Override // android.os.SystemProto.KernelWakelockOrBuilder
        public boolean hasName() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // android.os.SystemProto.KernelWakelockOrBuilder
        public String getName() {
            return this.name_;
        }

        @Override // android.os.SystemProto.KernelWakelockOrBuilder
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

        @Override // android.os.SystemProto.KernelWakelockOrBuilder
        public boolean hasTotal() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // android.os.SystemProto.KernelWakelockOrBuilder
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

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                output.writeString(1, getName());
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeMessage(2, getTotal());
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
            int size3 = size2 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static KernelWakelock parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (KernelWakelock) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static KernelWakelock parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (KernelWakelock) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static KernelWakelock parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (KernelWakelock) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static KernelWakelock parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (KernelWakelock) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static KernelWakelock parseFrom(InputStream input) throws IOException {
            return (KernelWakelock) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static KernelWakelock parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (KernelWakelock) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static KernelWakelock parseDelimitedFrom(InputStream input) throws IOException {
            return (KernelWakelock) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static KernelWakelock parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (KernelWakelock) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static KernelWakelock parseFrom(CodedInputStream input) throws IOException {
            return (KernelWakelock) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static KernelWakelock parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (KernelWakelock) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(KernelWakelock prototype) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<KernelWakelock, Builder> implements KernelWakelockOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 x0) {
                this();
            }

            private Builder() {
                super(KernelWakelock.DEFAULT_INSTANCE);
            }

            @Override // android.os.SystemProto.KernelWakelockOrBuilder
            public boolean hasName() {
                return ((KernelWakelock) this.instance).hasName();
            }

            @Override // android.os.SystemProto.KernelWakelockOrBuilder
            public String getName() {
                return ((KernelWakelock) this.instance).getName();
            }

            @Override // android.os.SystemProto.KernelWakelockOrBuilder
            public ByteString getNameBytes() {
                return ((KernelWakelock) this.instance).getNameBytes();
            }

            public Builder setName(String value) {
                copyOnWrite();
                ((KernelWakelock) this.instance).setName(value);
                return this;
            }

            public Builder clearName() {
                copyOnWrite();
                ((KernelWakelock) this.instance).clearName();
                return this;
            }

            public Builder setNameBytes(ByteString value) {
                copyOnWrite();
                ((KernelWakelock) this.instance).setNameBytes(value);
                return this;
            }

            @Override // android.os.SystemProto.KernelWakelockOrBuilder
            public boolean hasTotal() {
                return ((KernelWakelock) this.instance).hasTotal();
            }

            @Override // android.os.SystemProto.KernelWakelockOrBuilder
            public TimerProto getTotal() {
                return ((KernelWakelock) this.instance).getTotal();
            }

            public Builder setTotal(TimerProto value) {
                copyOnWrite();
                ((KernelWakelock) this.instance).setTotal((KernelWakelock) value);
                return this;
            }

            public Builder setTotal(TimerProto.Builder builderForValue) {
                copyOnWrite();
                ((KernelWakelock) this.instance).setTotal((KernelWakelock) builderForValue);
                return this;
            }

            public Builder mergeTotal(TimerProto value) {
                copyOnWrite();
                ((KernelWakelock) this.instance).mergeTotal(value);
                return this;
            }

            public Builder clearTotal() {
                copyOnWrite();
                ((KernelWakelock) this.instance).clearTotal();
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new KernelWakelock();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder(null);
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    KernelWakelock other = (KernelWakelock) arg1;
                    this.name_ = visitor.visitString(hasName(), this.name_, other.hasName(), other.name_);
                    this.total_ = (TimerProto) visitor.visitMessage(this.total_, other.total_);
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
                        synchronized (KernelWakelock.class) {
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

        public static KernelWakelock getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<KernelWakelock> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class Misc extends GeneratedMessageLite<Misc, Builder> implements MiscOrBuilder {
        public static final int BATTERY_SAVER_MODE_ENABLED_DURATION_MS_FIELD_NUMBER = 10;
        public static final int DEEP_DOZE_COUNT_FIELD_NUMBER = 13;
        public static final int DEEP_DOZE_ENABLED_DURATION_MS_FIELD_NUMBER = 12;
        public static final int DEEP_DOZE_IDLING_COUNT_FIELD_NUMBER = 15;
        public static final int DEEP_DOZE_IDLING_DURATION_MS_FIELD_NUMBER = 14;
        private static final Misc DEFAULT_INSTANCE = new Misc();
        public static final int FULL_WAKELOCK_TOTAL_DURATION_MS_FIELD_NUMBER = 3;
        public static final int INTERACTIVE_DURATION_MS_FIELD_NUMBER = 9;
        public static final int LIGHT_DOZE_COUNT_FIELD_NUMBER = 18;
        public static final int LIGHT_DOZE_ENABLED_DURATION_MS_FIELD_NUMBER = 17;
        public static final int LIGHT_DOZE_IDLING_COUNT_FIELD_NUMBER = 20;
        public static final int LIGHT_DOZE_IDLING_DURATION_MS_FIELD_NUMBER = 19;
        public static final int LONGEST_DEEP_DOZE_DURATION_MS_FIELD_NUMBER = 16;
        public static final int LONGEST_LIGHT_DOZE_DURATION_MS_FIELD_NUMBER = 21;
        public static final int MOBILE_RADIO_ACTIVE_ADJUSTED_TIME_MS_FIELD_NUMBER = 6;
        public static final int MOBILE_RADIO_ACTIVE_COUNT_FIELD_NUMBER = 7;
        public static final int MOBILE_RADIO_ACTIVE_DURATION_MS_FIELD_NUMBER = 5;
        public static final int MOBILE_RADIO_ACTIVE_UNKNOWN_DURATION_MS_FIELD_NUMBER = 8;
        public static final int NUM_CONNECTIVITY_CHANGES_FIELD_NUMBER = 11;
        private static volatile Parser<Misc> PARSER = null;
        public static final int PARTIAL_WAKELOCK_TOTAL_DURATION_MS_FIELD_NUMBER = 4;
        public static final int PHONE_ON_DURATION_MS_FIELD_NUMBER = 2;
        public static final int SCREEN_ON_DURATION_MS_FIELD_NUMBER = 1;
        private long batterySaverModeEnabledDurationMs_ = 0;
        private int bitField0_;
        private int deepDozeCount_ = 0;
        private long deepDozeEnabledDurationMs_ = 0;
        private int deepDozeIdlingCount_ = 0;
        private long deepDozeIdlingDurationMs_ = 0;
        private long fullWakelockTotalDurationMs_ = 0;
        private long interactiveDurationMs_ = 0;
        private int lightDozeCount_ = 0;
        private long lightDozeEnabledDurationMs_ = 0;
        private int lightDozeIdlingCount_ = 0;
        private long lightDozeIdlingDurationMs_ = 0;
        private long longestDeepDozeDurationMs_ = 0;
        private long longestLightDozeDurationMs_ = 0;
        private long mobileRadioActiveAdjustedTimeMs_ = 0;
        private int mobileRadioActiveCount_ = 0;
        private long mobileRadioActiveDurationMs_ = 0;
        private int mobileRadioActiveUnknownDurationMs_ = 0;
        private int numConnectivityChanges_ = 0;
        private long partialWakelockTotalDurationMs_ = 0;
        private long phoneOnDurationMs_ = 0;
        private long screenOnDurationMs_ = 0;

        private Misc() {
        }

        @Override // android.os.SystemProto.MiscOrBuilder
        public boolean hasScreenOnDurationMs() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // android.os.SystemProto.MiscOrBuilder
        public long getScreenOnDurationMs() {
            return this.screenOnDurationMs_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setScreenOnDurationMs(long value) {
            this.bitField0_ |= 1;
            this.screenOnDurationMs_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearScreenOnDurationMs() {
            this.bitField0_ &= -2;
            this.screenOnDurationMs_ = 0;
        }

        @Override // android.os.SystemProto.MiscOrBuilder
        public boolean hasPhoneOnDurationMs() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // android.os.SystemProto.MiscOrBuilder
        public long getPhoneOnDurationMs() {
            return this.phoneOnDurationMs_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setPhoneOnDurationMs(long value) {
            this.bitField0_ |= 2;
            this.phoneOnDurationMs_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearPhoneOnDurationMs() {
            this.bitField0_ &= -3;
            this.phoneOnDurationMs_ = 0;
        }

        @Override // android.os.SystemProto.MiscOrBuilder
        public boolean hasFullWakelockTotalDurationMs() {
            return (this.bitField0_ & 4) == 4;
        }

        @Override // android.os.SystemProto.MiscOrBuilder
        public long getFullWakelockTotalDurationMs() {
            return this.fullWakelockTotalDurationMs_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setFullWakelockTotalDurationMs(long value) {
            this.bitField0_ |= 4;
            this.fullWakelockTotalDurationMs_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearFullWakelockTotalDurationMs() {
            this.bitField0_ &= -5;
            this.fullWakelockTotalDurationMs_ = 0;
        }

        @Override // android.os.SystemProto.MiscOrBuilder
        public boolean hasPartialWakelockTotalDurationMs() {
            return (this.bitField0_ & 8) == 8;
        }

        @Override // android.os.SystemProto.MiscOrBuilder
        public long getPartialWakelockTotalDurationMs() {
            return this.partialWakelockTotalDurationMs_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setPartialWakelockTotalDurationMs(long value) {
            this.bitField0_ |= 8;
            this.partialWakelockTotalDurationMs_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearPartialWakelockTotalDurationMs() {
            this.bitField0_ &= -9;
            this.partialWakelockTotalDurationMs_ = 0;
        }

        @Override // android.os.SystemProto.MiscOrBuilder
        public boolean hasMobileRadioActiveDurationMs() {
            return (this.bitField0_ & 16) == 16;
        }

        @Override // android.os.SystemProto.MiscOrBuilder
        public long getMobileRadioActiveDurationMs() {
            return this.mobileRadioActiveDurationMs_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setMobileRadioActiveDurationMs(long value) {
            this.bitField0_ |= 16;
            this.mobileRadioActiveDurationMs_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearMobileRadioActiveDurationMs() {
            this.bitField0_ &= -17;
            this.mobileRadioActiveDurationMs_ = 0;
        }

        @Override // android.os.SystemProto.MiscOrBuilder
        public boolean hasMobileRadioActiveAdjustedTimeMs() {
            return (this.bitField0_ & 32) == 32;
        }

        @Override // android.os.SystemProto.MiscOrBuilder
        public long getMobileRadioActiveAdjustedTimeMs() {
            return this.mobileRadioActiveAdjustedTimeMs_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setMobileRadioActiveAdjustedTimeMs(long value) {
            this.bitField0_ |= 32;
            this.mobileRadioActiveAdjustedTimeMs_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearMobileRadioActiveAdjustedTimeMs() {
            this.bitField0_ &= -33;
            this.mobileRadioActiveAdjustedTimeMs_ = 0;
        }

        @Override // android.os.SystemProto.MiscOrBuilder
        public boolean hasMobileRadioActiveCount() {
            return (this.bitField0_ & 64) == 64;
        }

        @Override // android.os.SystemProto.MiscOrBuilder
        public int getMobileRadioActiveCount() {
            return this.mobileRadioActiveCount_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setMobileRadioActiveCount(int value) {
            this.bitField0_ |= 64;
            this.mobileRadioActiveCount_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearMobileRadioActiveCount() {
            this.bitField0_ &= -65;
            this.mobileRadioActiveCount_ = 0;
        }

        @Override // android.os.SystemProto.MiscOrBuilder
        public boolean hasMobileRadioActiveUnknownDurationMs() {
            return (this.bitField0_ & 128) == 128;
        }

        @Override // android.os.SystemProto.MiscOrBuilder
        public int getMobileRadioActiveUnknownDurationMs() {
            return this.mobileRadioActiveUnknownDurationMs_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setMobileRadioActiveUnknownDurationMs(int value) {
            this.bitField0_ |= 128;
            this.mobileRadioActiveUnknownDurationMs_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearMobileRadioActiveUnknownDurationMs() {
            this.bitField0_ &= -129;
            this.mobileRadioActiveUnknownDurationMs_ = 0;
        }

        @Override // android.os.SystemProto.MiscOrBuilder
        public boolean hasInteractiveDurationMs() {
            return (this.bitField0_ & 256) == 256;
        }

        @Override // android.os.SystemProto.MiscOrBuilder
        public long getInteractiveDurationMs() {
            return this.interactiveDurationMs_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setInteractiveDurationMs(long value) {
            this.bitField0_ |= 256;
            this.interactiveDurationMs_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearInteractiveDurationMs() {
            this.bitField0_ &= -257;
            this.interactiveDurationMs_ = 0;
        }

        @Override // android.os.SystemProto.MiscOrBuilder
        public boolean hasBatterySaverModeEnabledDurationMs() {
            return (this.bitField0_ & 512) == 512;
        }

        @Override // android.os.SystemProto.MiscOrBuilder
        public long getBatterySaverModeEnabledDurationMs() {
            return this.batterySaverModeEnabledDurationMs_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setBatterySaverModeEnabledDurationMs(long value) {
            this.bitField0_ |= 512;
            this.batterySaverModeEnabledDurationMs_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearBatterySaverModeEnabledDurationMs() {
            this.bitField0_ &= -513;
            this.batterySaverModeEnabledDurationMs_ = 0;
        }

        @Override // android.os.SystemProto.MiscOrBuilder
        public boolean hasNumConnectivityChanges() {
            return (this.bitField0_ & 1024) == 1024;
        }

        @Override // android.os.SystemProto.MiscOrBuilder
        public int getNumConnectivityChanges() {
            return this.numConnectivityChanges_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setNumConnectivityChanges(int value) {
            this.bitField0_ |= 1024;
            this.numConnectivityChanges_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearNumConnectivityChanges() {
            this.bitField0_ &= -1025;
            this.numConnectivityChanges_ = 0;
        }

        @Override // android.os.SystemProto.MiscOrBuilder
        public boolean hasDeepDozeEnabledDurationMs() {
            return (this.bitField0_ & 2048) == 2048;
        }

        @Override // android.os.SystemProto.MiscOrBuilder
        public long getDeepDozeEnabledDurationMs() {
            return this.deepDozeEnabledDurationMs_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setDeepDozeEnabledDurationMs(long value) {
            this.bitField0_ |= 2048;
            this.deepDozeEnabledDurationMs_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearDeepDozeEnabledDurationMs() {
            this.bitField0_ &= -2049;
            this.deepDozeEnabledDurationMs_ = 0;
        }

        @Override // android.os.SystemProto.MiscOrBuilder
        public boolean hasDeepDozeCount() {
            return (this.bitField0_ & 4096) == 4096;
        }

        @Override // android.os.SystemProto.MiscOrBuilder
        public int getDeepDozeCount() {
            return this.deepDozeCount_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setDeepDozeCount(int value) {
            this.bitField0_ |= 4096;
            this.deepDozeCount_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearDeepDozeCount() {
            this.bitField0_ &= -4097;
            this.deepDozeCount_ = 0;
        }

        @Override // android.os.SystemProto.MiscOrBuilder
        public boolean hasDeepDozeIdlingDurationMs() {
            return (this.bitField0_ & 8192) == 8192;
        }

        @Override // android.os.SystemProto.MiscOrBuilder
        public long getDeepDozeIdlingDurationMs() {
            return this.deepDozeIdlingDurationMs_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setDeepDozeIdlingDurationMs(long value) {
            this.bitField0_ |= 8192;
            this.deepDozeIdlingDurationMs_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearDeepDozeIdlingDurationMs() {
            this.bitField0_ &= -8193;
            this.deepDozeIdlingDurationMs_ = 0;
        }

        @Override // android.os.SystemProto.MiscOrBuilder
        public boolean hasDeepDozeIdlingCount() {
            return (this.bitField0_ & 16384) == 16384;
        }

        @Override // android.os.SystemProto.MiscOrBuilder
        public int getDeepDozeIdlingCount() {
            return this.deepDozeIdlingCount_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setDeepDozeIdlingCount(int value) {
            this.bitField0_ |= 16384;
            this.deepDozeIdlingCount_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearDeepDozeIdlingCount() {
            this.bitField0_ &= -16385;
            this.deepDozeIdlingCount_ = 0;
        }

        @Override // android.os.SystemProto.MiscOrBuilder
        public boolean hasLongestDeepDozeDurationMs() {
            return (this.bitField0_ & 32768) == 32768;
        }

        @Override // android.os.SystemProto.MiscOrBuilder
        public long getLongestDeepDozeDurationMs() {
            return this.longestDeepDozeDurationMs_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setLongestDeepDozeDurationMs(long value) {
            this.bitField0_ |= 32768;
            this.longestDeepDozeDurationMs_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearLongestDeepDozeDurationMs() {
            this.bitField0_ &= -32769;
            this.longestDeepDozeDurationMs_ = 0;
        }

        @Override // android.os.SystemProto.MiscOrBuilder
        public boolean hasLightDozeEnabledDurationMs() {
            return (this.bitField0_ & 65536) == 65536;
        }

        @Override // android.os.SystemProto.MiscOrBuilder
        public long getLightDozeEnabledDurationMs() {
            return this.lightDozeEnabledDurationMs_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setLightDozeEnabledDurationMs(long value) {
            this.bitField0_ |= 65536;
            this.lightDozeEnabledDurationMs_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearLightDozeEnabledDurationMs() {
            this.bitField0_ &= -65537;
            this.lightDozeEnabledDurationMs_ = 0;
        }

        @Override // android.os.SystemProto.MiscOrBuilder
        public boolean hasLightDozeCount() {
            return (this.bitField0_ & 131072) == 131072;
        }

        @Override // android.os.SystemProto.MiscOrBuilder
        public int getLightDozeCount() {
            return this.lightDozeCount_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setLightDozeCount(int value) {
            this.bitField0_ |= 131072;
            this.lightDozeCount_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearLightDozeCount() {
            this.bitField0_ &= -131073;
            this.lightDozeCount_ = 0;
        }

        @Override // android.os.SystemProto.MiscOrBuilder
        public boolean hasLightDozeIdlingDurationMs() {
            return (this.bitField0_ & 262144) == 262144;
        }

        @Override // android.os.SystemProto.MiscOrBuilder
        public long getLightDozeIdlingDurationMs() {
            return this.lightDozeIdlingDurationMs_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setLightDozeIdlingDurationMs(long value) {
            this.bitField0_ |= 262144;
            this.lightDozeIdlingDurationMs_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearLightDozeIdlingDurationMs() {
            this.bitField0_ &= -262145;
            this.lightDozeIdlingDurationMs_ = 0;
        }

        @Override // android.os.SystemProto.MiscOrBuilder
        public boolean hasLightDozeIdlingCount() {
            return (this.bitField0_ & 524288) == 524288;
        }

        @Override // android.os.SystemProto.MiscOrBuilder
        public int getLightDozeIdlingCount() {
            return this.lightDozeIdlingCount_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setLightDozeIdlingCount(int value) {
            this.bitField0_ |= 524288;
            this.lightDozeIdlingCount_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearLightDozeIdlingCount() {
            this.bitField0_ &= -524289;
            this.lightDozeIdlingCount_ = 0;
        }

        @Override // android.os.SystemProto.MiscOrBuilder
        public boolean hasLongestLightDozeDurationMs() {
            return (this.bitField0_ & 1048576) == 1048576;
        }

        @Override // android.os.SystemProto.MiscOrBuilder
        public long getLongestLightDozeDurationMs() {
            return this.longestLightDozeDurationMs_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setLongestLightDozeDurationMs(long value) {
            this.bitField0_ |= 1048576;
            this.longestLightDozeDurationMs_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearLongestLightDozeDurationMs() {
            this.bitField0_ &= -1048577;
            this.longestLightDozeDurationMs_ = 0;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                output.writeInt64(1, this.screenOnDurationMs_);
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeInt64(2, this.phoneOnDurationMs_);
            }
            if ((this.bitField0_ & 4) == 4) {
                output.writeInt64(3, this.fullWakelockTotalDurationMs_);
            }
            if ((this.bitField0_ & 8) == 8) {
                output.writeInt64(4, this.partialWakelockTotalDurationMs_);
            }
            if ((this.bitField0_ & 16) == 16) {
                output.writeInt64(5, this.mobileRadioActiveDurationMs_);
            }
            if ((this.bitField0_ & 32) == 32) {
                output.writeInt64(6, this.mobileRadioActiveAdjustedTimeMs_);
            }
            if ((this.bitField0_ & 64) == 64) {
                output.writeInt32(7, this.mobileRadioActiveCount_);
            }
            if ((this.bitField0_ & 128) == 128) {
                output.writeInt32(8, this.mobileRadioActiveUnknownDurationMs_);
            }
            if ((this.bitField0_ & 256) == 256) {
                output.writeInt64(9, this.interactiveDurationMs_);
            }
            if ((this.bitField0_ & 512) == 512) {
                output.writeInt64(10, this.batterySaverModeEnabledDurationMs_);
            }
            if ((this.bitField0_ & 1024) == 1024) {
                output.writeInt32(11, this.numConnectivityChanges_);
            }
            if ((this.bitField0_ & 2048) == 2048) {
                output.writeInt64(12, this.deepDozeEnabledDurationMs_);
            }
            if ((this.bitField0_ & 4096) == 4096) {
                output.writeInt32(13, this.deepDozeCount_);
            }
            if ((this.bitField0_ & 8192) == 8192) {
                output.writeInt64(14, this.deepDozeIdlingDurationMs_);
            }
            if ((this.bitField0_ & 16384) == 16384) {
                output.writeInt32(15, this.deepDozeIdlingCount_);
            }
            if ((this.bitField0_ & 32768) == 32768) {
                output.writeInt64(16, this.longestDeepDozeDurationMs_);
            }
            if ((this.bitField0_ & 65536) == 65536) {
                output.writeInt64(17, this.lightDozeEnabledDurationMs_);
            }
            if ((this.bitField0_ & 131072) == 131072) {
                output.writeInt32(18, this.lightDozeCount_);
            }
            if ((this.bitField0_ & 262144) == 262144) {
                output.writeInt64(19, this.lightDozeIdlingDurationMs_);
            }
            if ((this.bitField0_ & 524288) == 524288) {
                output.writeInt32(20, this.lightDozeIdlingCount_);
            }
            if ((this.bitField0_ & 1048576) == 1048576) {
                output.writeInt64(21, this.longestLightDozeDurationMs_);
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
                size2 = 0 + CodedOutputStream.computeInt64Size(1, this.screenOnDurationMs_);
            }
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeInt64Size(2, this.phoneOnDurationMs_);
            }
            if ((this.bitField0_ & 4) == 4) {
                size2 += CodedOutputStream.computeInt64Size(3, this.fullWakelockTotalDurationMs_);
            }
            if ((this.bitField0_ & 8) == 8) {
                size2 += CodedOutputStream.computeInt64Size(4, this.partialWakelockTotalDurationMs_);
            }
            if ((this.bitField0_ & 16) == 16) {
                size2 += CodedOutputStream.computeInt64Size(5, this.mobileRadioActiveDurationMs_);
            }
            if ((this.bitField0_ & 32) == 32) {
                size2 += CodedOutputStream.computeInt64Size(6, this.mobileRadioActiveAdjustedTimeMs_);
            }
            if ((this.bitField0_ & 64) == 64) {
                size2 += CodedOutputStream.computeInt32Size(7, this.mobileRadioActiveCount_);
            }
            if ((this.bitField0_ & 128) == 128) {
                size2 += CodedOutputStream.computeInt32Size(8, this.mobileRadioActiveUnknownDurationMs_);
            }
            if ((this.bitField0_ & 256) == 256) {
                size2 += CodedOutputStream.computeInt64Size(9, this.interactiveDurationMs_);
            }
            if ((this.bitField0_ & 512) == 512) {
                size2 += CodedOutputStream.computeInt64Size(10, this.batterySaverModeEnabledDurationMs_);
            }
            if ((this.bitField0_ & 1024) == 1024) {
                size2 += CodedOutputStream.computeInt32Size(11, this.numConnectivityChanges_);
            }
            if ((this.bitField0_ & 2048) == 2048) {
                size2 += CodedOutputStream.computeInt64Size(12, this.deepDozeEnabledDurationMs_);
            }
            if ((this.bitField0_ & 4096) == 4096) {
                size2 += CodedOutputStream.computeInt32Size(13, this.deepDozeCount_);
            }
            if ((this.bitField0_ & 8192) == 8192) {
                size2 += CodedOutputStream.computeInt64Size(14, this.deepDozeIdlingDurationMs_);
            }
            if ((this.bitField0_ & 16384) == 16384) {
                size2 += CodedOutputStream.computeInt32Size(15, this.deepDozeIdlingCount_);
            }
            if ((this.bitField0_ & 32768) == 32768) {
                size2 += CodedOutputStream.computeInt64Size(16, this.longestDeepDozeDurationMs_);
            }
            if ((this.bitField0_ & 65536) == 65536) {
                size2 += CodedOutputStream.computeInt64Size(17, this.lightDozeEnabledDurationMs_);
            }
            if ((this.bitField0_ & 131072) == 131072) {
                size2 += CodedOutputStream.computeInt32Size(18, this.lightDozeCount_);
            }
            if ((this.bitField0_ & 262144) == 262144) {
                size2 += CodedOutputStream.computeInt64Size(19, this.lightDozeIdlingDurationMs_);
            }
            if ((this.bitField0_ & 524288) == 524288) {
                size2 += CodedOutputStream.computeInt32Size(20, this.lightDozeIdlingCount_);
            }
            if ((this.bitField0_ & 1048576) == 1048576) {
                size2 += CodedOutputStream.computeInt64Size(21, this.longestLightDozeDurationMs_);
            }
            int size3 = size2 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static Misc parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (Misc) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static Misc parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Misc) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static Misc parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (Misc) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static Misc parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Misc) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static Misc parseFrom(InputStream input) throws IOException {
            return (Misc) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static Misc parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Misc) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Misc parseDelimitedFrom(InputStream input) throws IOException {
            return (Misc) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static Misc parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Misc) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Misc parseFrom(CodedInputStream input) throws IOException {
            return (Misc) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static Misc parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Misc) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(Misc prototype) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<Misc, Builder> implements MiscOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 x0) {
                this();
            }

            private Builder() {
                super(Misc.DEFAULT_INSTANCE);
            }

            @Override // android.os.SystemProto.MiscOrBuilder
            public boolean hasScreenOnDurationMs() {
                return ((Misc) this.instance).hasScreenOnDurationMs();
            }

            @Override // android.os.SystemProto.MiscOrBuilder
            public long getScreenOnDurationMs() {
                return ((Misc) this.instance).getScreenOnDurationMs();
            }

            public Builder setScreenOnDurationMs(long value) {
                copyOnWrite();
                ((Misc) this.instance).setScreenOnDurationMs(value);
                return this;
            }

            public Builder clearScreenOnDurationMs() {
                copyOnWrite();
                ((Misc) this.instance).clearScreenOnDurationMs();
                return this;
            }

            @Override // android.os.SystemProto.MiscOrBuilder
            public boolean hasPhoneOnDurationMs() {
                return ((Misc) this.instance).hasPhoneOnDurationMs();
            }

            @Override // android.os.SystemProto.MiscOrBuilder
            public long getPhoneOnDurationMs() {
                return ((Misc) this.instance).getPhoneOnDurationMs();
            }

            public Builder setPhoneOnDurationMs(long value) {
                copyOnWrite();
                ((Misc) this.instance).setPhoneOnDurationMs(value);
                return this;
            }

            public Builder clearPhoneOnDurationMs() {
                copyOnWrite();
                ((Misc) this.instance).clearPhoneOnDurationMs();
                return this;
            }

            @Override // android.os.SystemProto.MiscOrBuilder
            public boolean hasFullWakelockTotalDurationMs() {
                return ((Misc) this.instance).hasFullWakelockTotalDurationMs();
            }

            @Override // android.os.SystemProto.MiscOrBuilder
            public long getFullWakelockTotalDurationMs() {
                return ((Misc) this.instance).getFullWakelockTotalDurationMs();
            }

            public Builder setFullWakelockTotalDurationMs(long value) {
                copyOnWrite();
                ((Misc) this.instance).setFullWakelockTotalDurationMs(value);
                return this;
            }

            public Builder clearFullWakelockTotalDurationMs() {
                copyOnWrite();
                ((Misc) this.instance).clearFullWakelockTotalDurationMs();
                return this;
            }

            @Override // android.os.SystemProto.MiscOrBuilder
            public boolean hasPartialWakelockTotalDurationMs() {
                return ((Misc) this.instance).hasPartialWakelockTotalDurationMs();
            }

            @Override // android.os.SystemProto.MiscOrBuilder
            public long getPartialWakelockTotalDurationMs() {
                return ((Misc) this.instance).getPartialWakelockTotalDurationMs();
            }

            public Builder setPartialWakelockTotalDurationMs(long value) {
                copyOnWrite();
                ((Misc) this.instance).setPartialWakelockTotalDurationMs(value);
                return this;
            }

            public Builder clearPartialWakelockTotalDurationMs() {
                copyOnWrite();
                ((Misc) this.instance).clearPartialWakelockTotalDurationMs();
                return this;
            }

            @Override // android.os.SystemProto.MiscOrBuilder
            public boolean hasMobileRadioActiveDurationMs() {
                return ((Misc) this.instance).hasMobileRadioActiveDurationMs();
            }

            @Override // android.os.SystemProto.MiscOrBuilder
            public long getMobileRadioActiveDurationMs() {
                return ((Misc) this.instance).getMobileRadioActiveDurationMs();
            }

            public Builder setMobileRadioActiveDurationMs(long value) {
                copyOnWrite();
                ((Misc) this.instance).setMobileRadioActiveDurationMs(value);
                return this;
            }

            public Builder clearMobileRadioActiveDurationMs() {
                copyOnWrite();
                ((Misc) this.instance).clearMobileRadioActiveDurationMs();
                return this;
            }

            @Override // android.os.SystemProto.MiscOrBuilder
            public boolean hasMobileRadioActiveAdjustedTimeMs() {
                return ((Misc) this.instance).hasMobileRadioActiveAdjustedTimeMs();
            }

            @Override // android.os.SystemProto.MiscOrBuilder
            public long getMobileRadioActiveAdjustedTimeMs() {
                return ((Misc) this.instance).getMobileRadioActiveAdjustedTimeMs();
            }

            public Builder setMobileRadioActiveAdjustedTimeMs(long value) {
                copyOnWrite();
                ((Misc) this.instance).setMobileRadioActiveAdjustedTimeMs(value);
                return this;
            }

            public Builder clearMobileRadioActiveAdjustedTimeMs() {
                copyOnWrite();
                ((Misc) this.instance).clearMobileRadioActiveAdjustedTimeMs();
                return this;
            }

            @Override // android.os.SystemProto.MiscOrBuilder
            public boolean hasMobileRadioActiveCount() {
                return ((Misc) this.instance).hasMobileRadioActiveCount();
            }

            @Override // android.os.SystemProto.MiscOrBuilder
            public int getMobileRadioActiveCount() {
                return ((Misc) this.instance).getMobileRadioActiveCount();
            }

            public Builder setMobileRadioActiveCount(int value) {
                copyOnWrite();
                ((Misc) this.instance).setMobileRadioActiveCount(value);
                return this;
            }

            public Builder clearMobileRadioActiveCount() {
                copyOnWrite();
                ((Misc) this.instance).clearMobileRadioActiveCount();
                return this;
            }

            @Override // android.os.SystemProto.MiscOrBuilder
            public boolean hasMobileRadioActiveUnknownDurationMs() {
                return ((Misc) this.instance).hasMobileRadioActiveUnknownDurationMs();
            }

            @Override // android.os.SystemProto.MiscOrBuilder
            public int getMobileRadioActiveUnknownDurationMs() {
                return ((Misc) this.instance).getMobileRadioActiveUnknownDurationMs();
            }

            public Builder setMobileRadioActiveUnknownDurationMs(int value) {
                copyOnWrite();
                ((Misc) this.instance).setMobileRadioActiveUnknownDurationMs(value);
                return this;
            }

            public Builder clearMobileRadioActiveUnknownDurationMs() {
                copyOnWrite();
                ((Misc) this.instance).clearMobileRadioActiveUnknownDurationMs();
                return this;
            }

            @Override // android.os.SystemProto.MiscOrBuilder
            public boolean hasInteractiveDurationMs() {
                return ((Misc) this.instance).hasInteractiveDurationMs();
            }

            @Override // android.os.SystemProto.MiscOrBuilder
            public long getInteractiveDurationMs() {
                return ((Misc) this.instance).getInteractiveDurationMs();
            }

            public Builder setInteractiveDurationMs(long value) {
                copyOnWrite();
                ((Misc) this.instance).setInteractiveDurationMs(value);
                return this;
            }

            public Builder clearInteractiveDurationMs() {
                copyOnWrite();
                ((Misc) this.instance).clearInteractiveDurationMs();
                return this;
            }

            @Override // android.os.SystemProto.MiscOrBuilder
            public boolean hasBatterySaverModeEnabledDurationMs() {
                return ((Misc) this.instance).hasBatterySaverModeEnabledDurationMs();
            }

            @Override // android.os.SystemProto.MiscOrBuilder
            public long getBatterySaverModeEnabledDurationMs() {
                return ((Misc) this.instance).getBatterySaverModeEnabledDurationMs();
            }

            public Builder setBatterySaverModeEnabledDurationMs(long value) {
                copyOnWrite();
                ((Misc) this.instance).setBatterySaverModeEnabledDurationMs(value);
                return this;
            }

            public Builder clearBatterySaverModeEnabledDurationMs() {
                copyOnWrite();
                ((Misc) this.instance).clearBatterySaverModeEnabledDurationMs();
                return this;
            }

            @Override // android.os.SystemProto.MiscOrBuilder
            public boolean hasNumConnectivityChanges() {
                return ((Misc) this.instance).hasNumConnectivityChanges();
            }

            @Override // android.os.SystemProto.MiscOrBuilder
            public int getNumConnectivityChanges() {
                return ((Misc) this.instance).getNumConnectivityChanges();
            }

            public Builder setNumConnectivityChanges(int value) {
                copyOnWrite();
                ((Misc) this.instance).setNumConnectivityChanges(value);
                return this;
            }

            public Builder clearNumConnectivityChanges() {
                copyOnWrite();
                ((Misc) this.instance).clearNumConnectivityChanges();
                return this;
            }

            @Override // android.os.SystemProto.MiscOrBuilder
            public boolean hasDeepDozeEnabledDurationMs() {
                return ((Misc) this.instance).hasDeepDozeEnabledDurationMs();
            }

            @Override // android.os.SystemProto.MiscOrBuilder
            public long getDeepDozeEnabledDurationMs() {
                return ((Misc) this.instance).getDeepDozeEnabledDurationMs();
            }

            public Builder setDeepDozeEnabledDurationMs(long value) {
                copyOnWrite();
                ((Misc) this.instance).setDeepDozeEnabledDurationMs(value);
                return this;
            }

            public Builder clearDeepDozeEnabledDurationMs() {
                copyOnWrite();
                ((Misc) this.instance).clearDeepDozeEnabledDurationMs();
                return this;
            }

            @Override // android.os.SystemProto.MiscOrBuilder
            public boolean hasDeepDozeCount() {
                return ((Misc) this.instance).hasDeepDozeCount();
            }

            @Override // android.os.SystemProto.MiscOrBuilder
            public int getDeepDozeCount() {
                return ((Misc) this.instance).getDeepDozeCount();
            }

            public Builder setDeepDozeCount(int value) {
                copyOnWrite();
                ((Misc) this.instance).setDeepDozeCount(value);
                return this;
            }

            public Builder clearDeepDozeCount() {
                copyOnWrite();
                ((Misc) this.instance).clearDeepDozeCount();
                return this;
            }

            @Override // android.os.SystemProto.MiscOrBuilder
            public boolean hasDeepDozeIdlingDurationMs() {
                return ((Misc) this.instance).hasDeepDozeIdlingDurationMs();
            }

            @Override // android.os.SystemProto.MiscOrBuilder
            public long getDeepDozeIdlingDurationMs() {
                return ((Misc) this.instance).getDeepDozeIdlingDurationMs();
            }

            public Builder setDeepDozeIdlingDurationMs(long value) {
                copyOnWrite();
                ((Misc) this.instance).setDeepDozeIdlingDurationMs(value);
                return this;
            }

            public Builder clearDeepDozeIdlingDurationMs() {
                copyOnWrite();
                ((Misc) this.instance).clearDeepDozeIdlingDurationMs();
                return this;
            }

            @Override // android.os.SystemProto.MiscOrBuilder
            public boolean hasDeepDozeIdlingCount() {
                return ((Misc) this.instance).hasDeepDozeIdlingCount();
            }

            @Override // android.os.SystemProto.MiscOrBuilder
            public int getDeepDozeIdlingCount() {
                return ((Misc) this.instance).getDeepDozeIdlingCount();
            }

            public Builder setDeepDozeIdlingCount(int value) {
                copyOnWrite();
                ((Misc) this.instance).setDeepDozeIdlingCount(value);
                return this;
            }

            public Builder clearDeepDozeIdlingCount() {
                copyOnWrite();
                ((Misc) this.instance).clearDeepDozeIdlingCount();
                return this;
            }

            @Override // android.os.SystemProto.MiscOrBuilder
            public boolean hasLongestDeepDozeDurationMs() {
                return ((Misc) this.instance).hasLongestDeepDozeDurationMs();
            }

            @Override // android.os.SystemProto.MiscOrBuilder
            public long getLongestDeepDozeDurationMs() {
                return ((Misc) this.instance).getLongestDeepDozeDurationMs();
            }

            public Builder setLongestDeepDozeDurationMs(long value) {
                copyOnWrite();
                ((Misc) this.instance).setLongestDeepDozeDurationMs(value);
                return this;
            }

            public Builder clearLongestDeepDozeDurationMs() {
                copyOnWrite();
                ((Misc) this.instance).clearLongestDeepDozeDurationMs();
                return this;
            }

            @Override // android.os.SystemProto.MiscOrBuilder
            public boolean hasLightDozeEnabledDurationMs() {
                return ((Misc) this.instance).hasLightDozeEnabledDurationMs();
            }

            @Override // android.os.SystemProto.MiscOrBuilder
            public long getLightDozeEnabledDurationMs() {
                return ((Misc) this.instance).getLightDozeEnabledDurationMs();
            }

            public Builder setLightDozeEnabledDurationMs(long value) {
                copyOnWrite();
                ((Misc) this.instance).setLightDozeEnabledDurationMs(value);
                return this;
            }

            public Builder clearLightDozeEnabledDurationMs() {
                copyOnWrite();
                ((Misc) this.instance).clearLightDozeEnabledDurationMs();
                return this;
            }

            @Override // android.os.SystemProto.MiscOrBuilder
            public boolean hasLightDozeCount() {
                return ((Misc) this.instance).hasLightDozeCount();
            }

            @Override // android.os.SystemProto.MiscOrBuilder
            public int getLightDozeCount() {
                return ((Misc) this.instance).getLightDozeCount();
            }

            public Builder setLightDozeCount(int value) {
                copyOnWrite();
                ((Misc) this.instance).setLightDozeCount(value);
                return this;
            }

            public Builder clearLightDozeCount() {
                copyOnWrite();
                ((Misc) this.instance).clearLightDozeCount();
                return this;
            }

            @Override // android.os.SystemProto.MiscOrBuilder
            public boolean hasLightDozeIdlingDurationMs() {
                return ((Misc) this.instance).hasLightDozeIdlingDurationMs();
            }

            @Override // android.os.SystemProto.MiscOrBuilder
            public long getLightDozeIdlingDurationMs() {
                return ((Misc) this.instance).getLightDozeIdlingDurationMs();
            }

            public Builder setLightDozeIdlingDurationMs(long value) {
                copyOnWrite();
                ((Misc) this.instance).setLightDozeIdlingDurationMs(value);
                return this;
            }

            public Builder clearLightDozeIdlingDurationMs() {
                copyOnWrite();
                ((Misc) this.instance).clearLightDozeIdlingDurationMs();
                return this;
            }

            @Override // android.os.SystemProto.MiscOrBuilder
            public boolean hasLightDozeIdlingCount() {
                return ((Misc) this.instance).hasLightDozeIdlingCount();
            }

            @Override // android.os.SystemProto.MiscOrBuilder
            public int getLightDozeIdlingCount() {
                return ((Misc) this.instance).getLightDozeIdlingCount();
            }

            public Builder setLightDozeIdlingCount(int value) {
                copyOnWrite();
                ((Misc) this.instance).setLightDozeIdlingCount(value);
                return this;
            }

            public Builder clearLightDozeIdlingCount() {
                copyOnWrite();
                ((Misc) this.instance).clearLightDozeIdlingCount();
                return this;
            }

            @Override // android.os.SystemProto.MiscOrBuilder
            public boolean hasLongestLightDozeDurationMs() {
                return ((Misc) this.instance).hasLongestLightDozeDurationMs();
            }

            @Override // android.os.SystemProto.MiscOrBuilder
            public long getLongestLightDozeDurationMs() {
                return ((Misc) this.instance).getLongestLightDozeDurationMs();
            }

            public Builder setLongestLightDozeDurationMs(long value) {
                copyOnWrite();
                ((Misc) this.instance).setLongestLightDozeDurationMs(value);
                return this;
            }

            public Builder clearLongestLightDozeDurationMs() {
                copyOnWrite();
                ((Misc) this.instance).clearLongestLightDozeDurationMs();
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new Misc();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder(null);
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    Misc other = (Misc) arg1;
                    this.screenOnDurationMs_ = visitor.visitLong(hasScreenOnDurationMs(), this.screenOnDurationMs_, other.hasScreenOnDurationMs(), other.screenOnDurationMs_);
                    this.phoneOnDurationMs_ = visitor.visitLong(hasPhoneOnDurationMs(), this.phoneOnDurationMs_, other.hasPhoneOnDurationMs(), other.phoneOnDurationMs_);
                    this.fullWakelockTotalDurationMs_ = visitor.visitLong(hasFullWakelockTotalDurationMs(), this.fullWakelockTotalDurationMs_, other.hasFullWakelockTotalDurationMs(), other.fullWakelockTotalDurationMs_);
                    this.partialWakelockTotalDurationMs_ = visitor.visitLong(hasPartialWakelockTotalDurationMs(), this.partialWakelockTotalDurationMs_, other.hasPartialWakelockTotalDurationMs(), other.partialWakelockTotalDurationMs_);
                    this.mobileRadioActiveDurationMs_ = visitor.visitLong(hasMobileRadioActiveDurationMs(), this.mobileRadioActiveDurationMs_, other.hasMobileRadioActiveDurationMs(), other.mobileRadioActiveDurationMs_);
                    this.mobileRadioActiveAdjustedTimeMs_ = visitor.visitLong(hasMobileRadioActiveAdjustedTimeMs(), this.mobileRadioActiveAdjustedTimeMs_, other.hasMobileRadioActiveAdjustedTimeMs(), other.mobileRadioActiveAdjustedTimeMs_);
                    this.mobileRadioActiveCount_ = visitor.visitInt(hasMobileRadioActiveCount(), this.mobileRadioActiveCount_, other.hasMobileRadioActiveCount(), other.mobileRadioActiveCount_);
                    this.mobileRadioActiveUnknownDurationMs_ = visitor.visitInt(hasMobileRadioActiveUnknownDurationMs(), this.mobileRadioActiveUnknownDurationMs_, other.hasMobileRadioActiveUnknownDurationMs(), other.mobileRadioActiveUnknownDurationMs_);
                    this.interactiveDurationMs_ = visitor.visitLong(hasInteractiveDurationMs(), this.interactiveDurationMs_, other.hasInteractiveDurationMs(), other.interactiveDurationMs_);
                    this.batterySaverModeEnabledDurationMs_ = visitor.visitLong(hasBatterySaverModeEnabledDurationMs(), this.batterySaverModeEnabledDurationMs_, other.hasBatterySaverModeEnabledDurationMs(), other.batterySaverModeEnabledDurationMs_);
                    this.numConnectivityChanges_ = visitor.visitInt(hasNumConnectivityChanges(), this.numConnectivityChanges_, other.hasNumConnectivityChanges(), other.numConnectivityChanges_);
                    this.deepDozeEnabledDurationMs_ = visitor.visitLong(hasDeepDozeEnabledDurationMs(), this.deepDozeEnabledDurationMs_, other.hasDeepDozeEnabledDurationMs(), other.deepDozeEnabledDurationMs_);
                    this.deepDozeCount_ = visitor.visitInt(hasDeepDozeCount(), this.deepDozeCount_, other.hasDeepDozeCount(), other.deepDozeCount_);
                    this.deepDozeIdlingDurationMs_ = visitor.visitLong(hasDeepDozeIdlingDurationMs(), this.deepDozeIdlingDurationMs_, other.hasDeepDozeIdlingDurationMs(), other.deepDozeIdlingDurationMs_);
                    this.deepDozeIdlingCount_ = visitor.visitInt(hasDeepDozeIdlingCount(), this.deepDozeIdlingCount_, other.hasDeepDozeIdlingCount(), other.deepDozeIdlingCount_);
                    this.longestDeepDozeDurationMs_ = visitor.visitLong(hasLongestDeepDozeDurationMs(), this.longestDeepDozeDurationMs_, other.hasLongestDeepDozeDurationMs(), other.longestDeepDozeDurationMs_);
                    this.lightDozeEnabledDurationMs_ = visitor.visitLong(hasLightDozeEnabledDurationMs(), this.lightDozeEnabledDurationMs_, other.hasLightDozeEnabledDurationMs(), other.lightDozeEnabledDurationMs_);
                    this.lightDozeCount_ = visitor.visitInt(hasLightDozeCount(), this.lightDozeCount_, other.hasLightDozeCount(), other.lightDozeCount_);
                    this.lightDozeIdlingDurationMs_ = visitor.visitLong(hasLightDozeIdlingDurationMs(), this.lightDozeIdlingDurationMs_, other.hasLightDozeIdlingDurationMs(), other.lightDozeIdlingDurationMs_);
                    this.lightDozeIdlingCount_ = visitor.visitInt(hasLightDozeIdlingCount(), this.lightDozeIdlingCount_, other.hasLightDozeIdlingCount(), other.lightDozeIdlingCount_);
                    this.longestLightDozeDurationMs_ = visitor.visitLong(hasLongestLightDozeDurationMs(), this.longestLightDozeDurationMs_, other.hasLongestLightDozeDurationMs(), other.longestLightDozeDurationMs_);
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
                                    this.screenOnDurationMs_ = input.readInt64();
                                    break;
                                case 16:
                                    this.bitField0_ |= 2;
                                    this.phoneOnDurationMs_ = input.readInt64();
                                    break;
                                case 24:
                                    this.bitField0_ |= 4;
                                    this.fullWakelockTotalDurationMs_ = input.readInt64();
                                    break;
                                case 32:
                                    this.bitField0_ |= 8;
                                    this.partialWakelockTotalDurationMs_ = input.readInt64();
                                    break;
                                case 40:
                                    this.bitField0_ |= 16;
                                    this.mobileRadioActiveDurationMs_ = input.readInt64();
                                    break;
                                case 48:
                                    this.bitField0_ |= 32;
                                    this.mobileRadioActiveAdjustedTimeMs_ = input.readInt64();
                                    break;
                                case 56:
                                    this.bitField0_ |= 64;
                                    this.mobileRadioActiveCount_ = input.readInt32();
                                    break;
                                case 64:
                                    this.bitField0_ |= 128;
                                    this.mobileRadioActiveUnknownDurationMs_ = input.readInt32();
                                    break;
                                case 72:
                                    this.bitField0_ |= 256;
                                    this.interactiveDurationMs_ = input.readInt64();
                                    break;
                                case 80:
                                    this.bitField0_ |= 512;
                                    this.batterySaverModeEnabledDurationMs_ = input.readInt64();
                                    break;
                                case 88:
                                    this.bitField0_ |= 1024;
                                    this.numConnectivityChanges_ = input.readInt32();
                                    break;
                                case 96:
                                    this.bitField0_ |= 2048;
                                    this.deepDozeEnabledDurationMs_ = input.readInt64();
                                    break;
                                case 104:
                                    this.bitField0_ |= 4096;
                                    this.deepDozeCount_ = input.readInt32();
                                    break;
                                case 112:
                                    this.bitField0_ |= 8192;
                                    this.deepDozeIdlingDurationMs_ = input.readInt64();
                                    break;
                                case 120:
                                    this.bitField0_ |= 16384;
                                    this.deepDozeIdlingCount_ = input.readInt32();
                                    break;
                                case 128:
                                    this.bitField0_ |= 32768;
                                    this.longestDeepDozeDurationMs_ = input.readInt64();
                                    break;
                                case 136:
                                    this.bitField0_ |= 65536;
                                    this.lightDozeEnabledDurationMs_ = input.readInt64();
                                    break;
                                case 144:
                                    this.bitField0_ |= 131072;
                                    this.lightDozeCount_ = input.readInt32();
                                    break;
                                case 152:
                                    this.bitField0_ |= 262144;
                                    this.lightDozeIdlingDurationMs_ = input.readInt64();
                                    break;
                                case 160:
                                    this.bitField0_ |= 524288;
                                    this.lightDozeIdlingCount_ = input.readInt32();
                                    break;
                                case 168:
                                    this.bitField0_ |= 1048576;
                                    this.longestLightDozeDurationMs_ = input.readInt64();
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
                        synchronized (Misc.class) {
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

        public static Misc getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<Misc> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class PhoneSignalStrength extends GeneratedMessageLite<PhoneSignalStrength, Builder> implements PhoneSignalStrengthOrBuilder {
        private static final PhoneSignalStrength DEFAULT_INSTANCE = new PhoneSignalStrength();
        public static final int NAME_FIELD_NUMBER = 1;
        private static volatile Parser<PhoneSignalStrength> PARSER = null;
        public static final int TOTAL_FIELD_NUMBER = 2;
        private int bitField0_;
        private int name_ = 0;
        private TimerProto total_;

        private PhoneSignalStrength() {
        }

        @Override // android.os.SystemProto.PhoneSignalStrengthOrBuilder
        public boolean hasName() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // android.os.SystemProto.PhoneSignalStrengthOrBuilder
        public SignalStrengthEnum getName() {
            SignalStrengthEnum result = SignalStrengthEnum.forNumber(this.name_);
            return result == null ? SignalStrengthEnum.SIGNAL_STRENGTH_NONE_OR_UNKNOWN : result;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setName(SignalStrengthEnum value) {
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

        @Override // android.os.SystemProto.PhoneSignalStrengthOrBuilder
        public boolean hasTotal() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // android.os.SystemProto.PhoneSignalStrengthOrBuilder
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

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                output.writeEnum(1, this.name_);
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeMessage(2, getTotal());
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
                size2 += CodedOutputStream.computeMessageSize(2, getTotal());
            }
            int size3 = size2 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static PhoneSignalStrength parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (PhoneSignalStrength) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static PhoneSignalStrength parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (PhoneSignalStrength) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static PhoneSignalStrength parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (PhoneSignalStrength) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static PhoneSignalStrength parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (PhoneSignalStrength) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static PhoneSignalStrength parseFrom(InputStream input) throws IOException {
            return (PhoneSignalStrength) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static PhoneSignalStrength parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (PhoneSignalStrength) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static PhoneSignalStrength parseDelimitedFrom(InputStream input) throws IOException {
            return (PhoneSignalStrength) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static PhoneSignalStrength parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (PhoneSignalStrength) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static PhoneSignalStrength parseFrom(CodedInputStream input) throws IOException {
            return (PhoneSignalStrength) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static PhoneSignalStrength parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (PhoneSignalStrength) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(PhoneSignalStrength prototype) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<PhoneSignalStrength, Builder> implements PhoneSignalStrengthOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 x0) {
                this();
            }

            private Builder() {
                super(PhoneSignalStrength.DEFAULT_INSTANCE);
            }

            @Override // android.os.SystemProto.PhoneSignalStrengthOrBuilder
            public boolean hasName() {
                return ((PhoneSignalStrength) this.instance).hasName();
            }

            @Override // android.os.SystemProto.PhoneSignalStrengthOrBuilder
            public SignalStrengthEnum getName() {
                return ((PhoneSignalStrength) this.instance).getName();
            }

            public Builder setName(SignalStrengthEnum value) {
                copyOnWrite();
                ((PhoneSignalStrength) this.instance).setName(value);
                return this;
            }

            public Builder clearName() {
                copyOnWrite();
                ((PhoneSignalStrength) this.instance).clearName();
                return this;
            }

            @Override // android.os.SystemProto.PhoneSignalStrengthOrBuilder
            public boolean hasTotal() {
                return ((PhoneSignalStrength) this.instance).hasTotal();
            }

            @Override // android.os.SystemProto.PhoneSignalStrengthOrBuilder
            public TimerProto getTotal() {
                return ((PhoneSignalStrength) this.instance).getTotal();
            }

            public Builder setTotal(TimerProto value) {
                copyOnWrite();
                ((PhoneSignalStrength) this.instance).setTotal((PhoneSignalStrength) value);
                return this;
            }

            public Builder setTotal(TimerProto.Builder builderForValue) {
                copyOnWrite();
                ((PhoneSignalStrength) this.instance).setTotal((PhoneSignalStrength) builderForValue);
                return this;
            }

            public Builder mergeTotal(TimerProto value) {
                copyOnWrite();
                ((PhoneSignalStrength) this.instance).mergeTotal(value);
                return this;
            }

            public Builder clearTotal() {
                copyOnWrite();
                ((PhoneSignalStrength) this.instance).clearTotal();
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new PhoneSignalStrength();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder(null);
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    PhoneSignalStrength other = (PhoneSignalStrength) arg1;
                    this.name_ = visitor.visitInt(hasName(), this.name_, other.hasName(), other.name_);
                    this.total_ = (TimerProto) visitor.visitMessage(this.total_, other.total_);
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
                                if (SignalStrengthEnum.forNumber(rawValue) == null) {
                                    super.mergeVarintField(1, rawValue);
                                } else {
                                    this.bitField0_ = 1 | this.bitField0_;
                                    this.name_ = rawValue;
                                }
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
                        synchronized (PhoneSignalStrength.class) {
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

        public static PhoneSignalStrength getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<PhoneSignalStrength> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class PowerUseItem extends GeneratedMessageLite<PowerUseItem, Builder> implements PowerUseItemOrBuilder {
        public static final int COMPUTED_POWER_MAH_FIELD_NUMBER = 3;
        private static final PowerUseItem DEFAULT_INSTANCE = new PowerUseItem();
        public static final int NAME_FIELD_NUMBER = 1;
        private static volatile Parser<PowerUseItem> PARSER = null;
        public static final int PROPORTIONAL_SMEAR_MAH_FIELD_NUMBER = 6;
        public static final int SCREEN_POWER_MAH_FIELD_NUMBER = 5;
        public static final int SHOULD_HIDE_FIELD_NUMBER = 4;
        public static final int UID_FIELD_NUMBER = 2;
        private int bitField0_;
        private double computedPowerMah_ = 0.0d;
        private int name_ = 0;
        private double proportionalSmearMah_ = 0.0d;
        private double screenPowerMah_ = 0.0d;
        private boolean shouldHide_ = false;
        private int uid_ = 0;

        private PowerUseItem() {
        }

        public enum Sipper implements Internal.EnumLite {
            UNKNOWN_SIPPER(0),
            IDLE(1),
            CELL(2),
            PHONE(3),
            WIFI(4),
            BLUETOOTH(5),
            FLASHLIGHT(6),
            SCREEN(7),
            USER(8),
            UNACCOUNTED(9),
            OVERCOUNTED(10),
            CAMERA(11),
            MEMORY(12),
            AMBIENT_DISPLAY(13);
            
            public static final int AMBIENT_DISPLAY_VALUE = 13;
            public static final int BLUETOOTH_VALUE = 5;
            public static final int CAMERA_VALUE = 11;
            public static final int CELL_VALUE = 2;
            public static final int FLASHLIGHT_VALUE = 6;
            public static final int IDLE_VALUE = 1;
            public static final int MEMORY_VALUE = 12;
            public static final int OVERCOUNTED_VALUE = 10;
            public static final int PHONE_VALUE = 3;
            public static final int SCREEN_VALUE = 7;
            public static final int UNACCOUNTED_VALUE = 9;
            public static final int UNKNOWN_SIPPER_VALUE = 0;
            public static final int USER_VALUE = 8;
            public static final int WIFI_VALUE = 4;
            private static final Internal.EnumLiteMap<Sipper> internalValueMap = new Internal.EnumLiteMap<Sipper>() {
                /* class android.os.SystemProto.PowerUseItem.Sipper.AnonymousClass1 */

                @Override // com.google.protobuf.Internal.EnumLiteMap
                public Sipper findValueByNumber(int number) {
                    return Sipper.forNumber(number);
                }
            };
            private final int value;

            @Override // com.google.protobuf.Internal.EnumLite
            public final int getNumber() {
                return this.value;
            }

            @Deprecated
            public static Sipper valueOf(int value2) {
                return forNumber(value2);
            }

            public static Sipper forNumber(int value2) {
                switch (value2) {
                    case 0:
                        return UNKNOWN_SIPPER;
                    case 1:
                        return IDLE;
                    case 2:
                        return CELL;
                    case 3:
                        return PHONE;
                    case 4:
                        return WIFI;
                    case 5:
                        return BLUETOOTH;
                    case 6:
                        return FLASHLIGHT;
                    case 7:
                        return SCREEN;
                    case 8:
                        return USER;
                    case 9:
                        return UNACCOUNTED;
                    case 10:
                        return OVERCOUNTED;
                    case 11:
                        return CAMERA;
                    case 12:
                        return MEMORY;
                    case 13:
                        return AMBIENT_DISPLAY;
                    default:
                        return null;
                }
            }

            public static Internal.EnumLiteMap<Sipper> internalGetValueMap() {
                return internalValueMap;
            }

            private Sipper(int value2) {
                this.value = value2;
            }
        }

        @Override // android.os.SystemProto.PowerUseItemOrBuilder
        public boolean hasName() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // android.os.SystemProto.PowerUseItemOrBuilder
        public Sipper getName() {
            Sipper result = Sipper.forNumber(this.name_);
            return result == null ? Sipper.UNKNOWN_SIPPER : result;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setName(Sipper value) {
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

        @Override // android.os.SystemProto.PowerUseItemOrBuilder
        public boolean hasUid() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // android.os.SystemProto.PowerUseItemOrBuilder
        public int getUid() {
            return this.uid_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setUid(int value) {
            this.bitField0_ |= 2;
            this.uid_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearUid() {
            this.bitField0_ &= -3;
            this.uid_ = 0;
        }

        @Override // android.os.SystemProto.PowerUseItemOrBuilder
        public boolean hasComputedPowerMah() {
            return (this.bitField0_ & 4) == 4;
        }

        @Override // android.os.SystemProto.PowerUseItemOrBuilder
        public double getComputedPowerMah() {
            return this.computedPowerMah_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setComputedPowerMah(double value) {
            this.bitField0_ |= 4;
            this.computedPowerMah_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearComputedPowerMah() {
            this.bitField0_ &= -5;
            this.computedPowerMah_ = 0.0d;
        }

        @Override // android.os.SystemProto.PowerUseItemOrBuilder
        public boolean hasShouldHide() {
            return (this.bitField0_ & 8) == 8;
        }

        @Override // android.os.SystemProto.PowerUseItemOrBuilder
        public boolean getShouldHide() {
            return this.shouldHide_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setShouldHide(boolean value) {
            this.bitField0_ |= 8;
            this.shouldHide_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearShouldHide() {
            this.bitField0_ &= -9;
            this.shouldHide_ = false;
        }

        @Override // android.os.SystemProto.PowerUseItemOrBuilder
        public boolean hasScreenPowerMah() {
            return (this.bitField0_ & 16) == 16;
        }

        @Override // android.os.SystemProto.PowerUseItemOrBuilder
        public double getScreenPowerMah() {
            return this.screenPowerMah_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setScreenPowerMah(double value) {
            this.bitField0_ |= 16;
            this.screenPowerMah_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearScreenPowerMah() {
            this.bitField0_ &= -17;
            this.screenPowerMah_ = 0.0d;
        }

        @Override // android.os.SystemProto.PowerUseItemOrBuilder
        public boolean hasProportionalSmearMah() {
            return (this.bitField0_ & 32) == 32;
        }

        @Override // android.os.SystemProto.PowerUseItemOrBuilder
        public double getProportionalSmearMah() {
            return this.proportionalSmearMah_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setProportionalSmearMah(double value) {
            this.bitField0_ |= 32;
            this.proportionalSmearMah_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearProportionalSmearMah() {
            this.bitField0_ &= -33;
            this.proportionalSmearMah_ = 0.0d;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                output.writeEnum(1, this.name_);
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeInt32(2, this.uid_);
            }
            if ((this.bitField0_ & 4) == 4) {
                output.writeDouble(3, this.computedPowerMah_);
            }
            if ((this.bitField0_ & 8) == 8) {
                output.writeBool(4, this.shouldHide_);
            }
            if ((this.bitField0_ & 16) == 16) {
                output.writeDouble(5, this.screenPowerMah_);
            }
            if ((this.bitField0_ & 32) == 32) {
                output.writeDouble(6, this.proportionalSmearMah_);
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
                size2 += CodedOutputStream.computeInt32Size(2, this.uid_);
            }
            if ((this.bitField0_ & 4) == 4) {
                size2 += CodedOutputStream.computeDoubleSize(3, this.computedPowerMah_);
            }
            if ((this.bitField0_ & 8) == 8) {
                size2 += CodedOutputStream.computeBoolSize(4, this.shouldHide_);
            }
            if ((this.bitField0_ & 16) == 16) {
                size2 += CodedOutputStream.computeDoubleSize(5, this.screenPowerMah_);
            }
            if ((this.bitField0_ & 32) == 32) {
                size2 += CodedOutputStream.computeDoubleSize(6, this.proportionalSmearMah_);
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
            /* synthetic */ Builder(AnonymousClass1 x0) {
                this();
            }

            private Builder() {
                super(PowerUseItem.DEFAULT_INSTANCE);
            }

            @Override // android.os.SystemProto.PowerUseItemOrBuilder
            public boolean hasName() {
                return ((PowerUseItem) this.instance).hasName();
            }

            @Override // android.os.SystemProto.PowerUseItemOrBuilder
            public Sipper getName() {
                return ((PowerUseItem) this.instance).getName();
            }

            public Builder setName(Sipper value) {
                copyOnWrite();
                ((PowerUseItem) this.instance).setName(value);
                return this;
            }

            public Builder clearName() {
                copyOnWrite();
                ((PowerUseItem) this.instance).clearName();
                return this;
            }

            @Override // android.os.SystemProto.PowerUseItemOrBuilder
            public boolean hasUid() {
                return ((PowerUseItem) this.instance).hasUid();
            }

            @Override // android.os.SystemProto.PowerUseItemOrBuilder
            public int getUid() {
                return ((PowerUseItem) this.instance).getUid();
            }

            public Builder setUid(int value) {
                copyOnWrite();
                ((PowerUseItem) this.instance).setUid(value);
                return this;
            }

            public Builder clearUid() {
                copyOnWrite();
                ((PowerUseItem) this.instance).clearUid();
                return this;
            }

            @Override // android.os.SystemProto.PowerUseItemOrBuilder
            public boolean hasComputedPowerMah() {
                return ((PowerUseItem) this.instance).hasComputedPowerMah();
            }

            @Override // android.os.SystemProto.PowerUseItemOrBuilder
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

            @Override // android.os.SystemProto.PowerUseItemOrBuilder
            public boolean hasShouldHide() {
                return ((PowerUseItem) this.instance).hasShouldHide();
            }

            @Override // android.os.SystemProto.PowerUseItemOrBuilder
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

            @Override // android.os.SystemProto.PowerUseItemOrBuilder
            public boolean hasScreenPowerMah() {
                return ((PowerUseItem) this.instance).hasScreenPowerMah();
            }

            @Override // android.os.SystemProto.PowerUseItemOrBuilder
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

            @Override // android.os.SystemProto.PowerUseItemOrBuilder
            public boolean hasProportionalSmearMah() {
                return ((PowerUseItem) this.instance).hasProportionalSmearMah();
            }

            @Override // android.os.SystemProto.PowerUseItemOrBuilder
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
                    return new Builder(null);
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    PowerUseItem other = (PowerUseItem) arg1;
                    this.name_ = visitor.visitInt(hasName(), this.name_, other.hasName(), other.name_);
                    this.uid_ = visitor.visitInt(hasUid(), this.uid_, other.hasUid(), other.uid_);
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
                            } else if (tag == 8) {
                                int rawValue = input.readEnum();
                                if (Sipper.forNumber(rawValue) == null) {
                                    super.mergeVarintField(1, rawValue);
                                } else {
                                    this.bitField0_ = 1 | this.bitField0_;
                                    this.name_ = rawValue;
                                }
                            } else if (tag == 16) {
                                this.bitField0_ |= 2;
                                this.uid_ = input.readInt32();
                            } else if (tag == 25) {
                                this.bitField0_ |= 4;
                                this.computedPowerMah_ = input.readDouble();
                            } else if (tag == 32) {
                                this.bitField0_ = 8 | this.bitField0_;
                                this.shouldHide_ = input.readBool();
                            } else if (tag == 41) {
                                this.bitField0_ |= 16;
                                this.screenPowerMah_ = input.readDouble();
                            } else if (tag == 49) {
                                this.bitField0_ |= 32;
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

    public static final class PowerUseSummary extends GeneratedMessageLite<PowerUseSummary, Builder> implements PowerUseSummaryOrBuilder {
        public static final int BATTERY_CAPACITY_MAH_FIELD_NUMBER = 1;
        public static final int COMPUTED_POWER_MAH_FIELD_NUMBER = 2;
        private static final PowerUseSummary DEFAULT_INSTANCE = new PowerUseSummary();
        public static final int MAX_DRAINED_POWER_MAH_FIELD_NUMBER = 4;
        public static final int MIN_DRAINED_POWER_MAH_FIELD_NUMBER = 3;
        private static volatile Parser<PowerUseSummary> PARSER;
        private double batteryCapacityMah_ = 0.0d;
        private int bitField0_;
        private double computedPowerMah_ = 0.0d;
        private double maxDrainedPowerMah_ = 0.0d;
        private double minDrainedPowerMah_ = 0.0d;

        private PowerUseSummary() {
        }

        @Override // android.os.SystemProto.PowerUseSummaryOrBuilder
        public boolean hasBatteryCapacityMah() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // android.os.SystemProto.PowerUseSummaryOrBuilder
        public double getBatteryCapacityMah() {
            return this.batteryCapacityMah_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setBatteryCapacityMah(double value) {
            this.bitField0_ |= 1;
            this.batteryCapacityMah_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearBatteryCapacityMah() {
            this.bitField0_ &= -2;
            this.batteryCapacityMah_ = 0.0d;
        }

        @Override // android.os.SystemProto.PowerUseSummaryOrBuilder
        public boolean hasComputedPowerMah() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // android.os.SystemProto.PowerUseSummaryOrBuilder
        public double getComputedPowerMah() {
            return this.computedPowerMah_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setComputedPowerMah(double value) {
            this.bitField0_ |= 2;
            this.computedPowerMah_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearComputedPowerMah() {
            this.bitField0_ &= -3;
            this.computedPowerMah_ = 0.0d;
        }

        @Override // android.os.SystemProto.PowerUseSummaryOrBuilder
        public boolean hasMinDrainedPowerMah() {
            return (this.bitField0_ & 4) == 4;
        }

        @Override // android.os.SystemProto.PowerUseSummaryOrBuilder
        public double getMinDrainedPowerMah() {
            return this.minDrainedPowerMah_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setMinDrainedPowerMah(double value) {
            this.bitField0_ |= 4;
            this.minDrainedPowerMah_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearMinDrainedPowerMah() {
            this.bitField0_ &= -5;
            this.minDrainedPowerMah_ = 0.0d;
        }

        @Override // android.os.SystemProto.PowerUseSummaryOrBuilder
        public boolean hasMaxDrainedPowerMah() {
            return (this.bitField0_ & 8) == 8;
        }

        @Override // android.os.SystemProto.PowerUseSummaryOrBuilder
        public double getMaxDrainedPowerMah() {
            return this.maxDrainedPowerMah_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setMaxDrainedPowerMah(double value) {
            this.bitField0_ |= 8;
            this.maxDrainedPowerMah_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearMaxDrainedPowerMah() {
            this.bitField0_ &= -9;
            this.maxDrainedPowerMah_ = 0.0d;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                output.writeDouble(1, this.batteryCapacityMah_);
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeDouble(2, this.computedPowerMah_);
            }
            if ((this.bitField0_ & 4) == 4) {
                output.writeDouble(3, this.minDrainedPowerMah_);
            }
            if ((this.bitField0_ & 8) == 8) {
                output.writeDouble(4, this.maxDrainedPowerMah_);
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
                size2 = 0 + CodedOutputStream.computeDoubleSize(1, this.batteryCapacityMah_);
            }
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeDoubleSize(2, this.computedPowerMah_);
            }
            if ((this.bitField0_ & 4) == 4) {
                size2 += CodedOutputStream.computeDoubleSize(3, this.minDrainedPowerMah_);
            }
            if ((this.bitField0_ & 8) == 8) {
                size2 += CodedOutputStream.computeDoubleSize(4, this.maxDrainedPowerMah_);
            }
            int size3 = size2 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static PowerUseSummary parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (PowerUseSummary) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static PowerUseSummary parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (PowerUseSummary) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static PowerUseSummary parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (PowerUseSummary) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static PowerUseSummary parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (PowerUseSummary) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static PowerUseSummary parseFrom(InputStream input) throws IOException {
            return (PowerUseSummary) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static PowerUseSummary parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (PowerUseSummary) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static PowerUseSummary parseDelimitedFrom(InputStream input) throws IOException {
            return (PowerUseSummary) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static PowerUseSummary parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (PowerUseSummary) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static PowerUseSummary parseFrom(CodedInputStream input) throws IOException {
            return (PowerUseSummary) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static PowerUseSummary parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (PowerUseSummary) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(PowerUseSummary prototype) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<PowerUseSummary, Builder> implements PowerUseSummaryOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 x0) {
                this();
            }

            private Builder() {
                super(PowerUseSummary.DEFAULT_INSTANCE);
            }

            @Override // android.os.SystemProto.PowerUseSummaryOrBuilder
            public boolean hasBatteryCapacityMah() {
                return ((PowerUseSummary) this.instance).hasBatteryCapacityMah();
            }

            @Override // android.os.SystemProto.PowerUseSummaryOrBuilder
            public double getBatteryCapacityMah() {
                return ((PowerUseSummary) this.instance).getBatteryCapacityMah();
            }

            public Builder setBatteryCapacityMah(double value) {
                copyOnWrite();
                ((PowerUseSummary) this.instance).setBatteryCapacityMah(value);
                return this;
            }

            public Builder clearBatteryCapacityMah() {
                copyOnWrite();
                ((PowerUseSummary) this.instance).clearBatteryCapacityMah();
                return this;
            }

            @Override // android.os.SystemProto.PowerUseSummaryOrBuilder
            public boolean hasComputedPowerMah() {
                return ((PowerUseSummary) this.instance).hasComputedPowerMah();
            }

            @Override // android.os.SystemProto.PowerUseSummaryOrBuilder
            public double getComputedPowerMah() {
                return ((PowerUseSummary) this.instance).getComputedPowerMah();
            }

            public Builder setComputedPowerMah(double value) {
                copyOnWrite();
                ((PowerUseSummary) this.instance).setComputedPowerMah(value);
                return this;
            }

            public Builder clearComputedPowerMah() {
                copyOnWrite();
                ((PowerUseSummary) this.instance).clearComputedPowerMah();
                return this;
            }

            @Override // android.os.SystemProto.PowerUseSummaryOrBuilder
            public boolean hasMinDrainedPowerMah() {
                return ((PowerUseSummary) this.instance).hasMinDrainedPowerMah();
            }

            @Override // android.os.SystemProto.PowerUseSummaryOrBuilder
            public double getMinDrainedPowerMah() {
                return ((PowerUseSummary) this.instance).getMinDrainedPowerMah();
            }

            public Builder setMinDrainedPowerMah(double value) {
                copyOnWrite();
                ((PowerUseSummary) this.instance).setMinDrainedPowerMah(value);
                return this;
            }

            public Builder clearMinDrainedPowerMah() {
                copyOnWrite();
                ((PowerUseSummary) this.instance).clearMinDrainedPowerMah();
                return this;
            }

            @Override // android.os.SystemProto.PowerUseSummaryOrBuilder
            public boolean hasMaxDrainedPowerMah() {
                return ((PowerUseSummary) this.instance).hasMaxDrainedPowerMah();
            }

            @Override // android.os.SystemProto.PowerUseSummaryOrBuilder
            public double getMaxDrainedPowerMah() {
                return ((PowerUseSummary) this.instance).getMaxDrainedPowerMah();
            }

            public Builder setMaxDrainedPowerMah(double value) {
                copyOnWrite();
                ((PowerUseSummary) this.instance).setMaxDrainedPowerMah(value);
                return this;
            }

            public Builder clearMaxDrainedPowerMah() {
                copyOnWrite();
                ((PowerUseSummary) this.instance).clearMaxDrainedPowerMah();
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new PowerUseSummary();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder(null);
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    PowerUseSummary other = (PowerUseSummary) arg1;
                    this.batteryCapacityMah_ = visitor.visitDouble(hasBatteryCapacityMah(), this.batteryCapacityMah_, other.hasBatteryCapacityMah(), other.batteryCapacityMah_);
                    this.computedPowerMah_ = visitor.visitDouble(hasComputedPowerMah(), this.computedPowerMah_, other.hasComputedPowerMah(), other.computedPowerMah_);
                    this.minDrainedPowerMah_ = visitor.visitDouble(hasMinDrainedPowerMah(), this.minDrainedPowerMah_, other.hasMinDrainedPowerMah(), other.minDrainedPowerMah_);
                    this.maxDrainedPowerMah_ = visitor.visitDouble(hasMaxDrainedPowerMah(), this.maxDrainedPowerMah_, other.hasMaxDrainedPowerMah(), other.maxDrainedPowerMah_);
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
                                this.batteryCapacityMah_ = input.readDouble();
                            } else if (tag == 17) {
                                this.bitField0_ |= 2;
                                this.computedPowerMah_ = input.readDouble();
                            } else if (tag == 25) {
                                this.bitField0_ |= 4;
                                this.minDrainedPowerMah_ = input.readDouble();
                            } else if (tag == 33) {
                                this.bitField0_ |= 8;
                                this.maxDrainedPowerMah_ = input.readDouble();
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
                        synchronized (PowerUseSummary.class) {
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

        public static PowerUseSummary getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<PowerUseSummary> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class ResourcePowerManager extends GeneratedMessageLite<ResourcePowerManager, Builder> implements ResourcePowerManagerOrBuilder {
        private static final ResourcePowerManager DEFAULT_INSTANCE = new ResourcePowerManager();
        public static final int NAME_FIELD_NUMBER = 1;
        private static volatile Parser<ResourcePowerManager> PARSER = null;
        public static final int SCREEN_OFF_FIELD_NUMBER = 3;
        public static final int TOTAL_FIELD_NUMBER = 2;
        private int bitField0_;
        private String name_ = "";
        private TimerProto screenOff_;
        private TimerProto total_;

        private ResourcePowerManager() {
        }

        @Override // android.os.SystemProto.ResourcePowerManagerOrBuilder
        public boolean hasName() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // android.os.SystemProto.ResourcePowerManagerOrBuilder
        public String getName() {
            return this.name_;
        }

        @Override // android.os.SystemProto.ResourcePowerManagerOrBuilder
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

        @Override // android.os.SystemProto.ResourcePowerManagerOrBuilder
        public boolean hasTotal() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // android.os.SystemProto.ResourcePowerManagerOrBuilder
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

        @Override // android.os.SystemProto.ResourcePowerManagerOrBuilder
        public boolean hasScreenOff() {
            return (this.bitField0_ & 4) == 4;
        }

        @Override // android.os.SystemProto.ResourcePowerManagerOrBuilder
        public TimerProto getScreenOff() {
            TimerProto timerProto = this.screenOff_;
            return timerProto == null ? TimerProto.getDefaultInstance() : timerProto;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setScreenOff(TimerProto value) {
            if (value != null) {
                this.screenOff_ = value;
                this.bitField0_ |= 4;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setScreenOff(TimerProto.Builder builderForValue) {
            this.screenOff_ = (TimerProto) builderForValue.build();
            this.bitField0_ |= 4;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void mergeScreenOff(TimerProto value) {
            TimerProto timerProto = this.screenOff_;
            if (timerProto == null || timerProto == TimerProto.getDefaultInstance()) {
                this.screenOff_ = value;
            } else {
                this.screenOff_ = (TimerProto) ((TimerProto.Builder) TimerProto.newBuilder(this.screenOff_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
            }
            this.bitField0_ |= 4;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearScreenOff() {
            this.screenOff_ = null;
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
                output.writeMessage(3, getScreenOff());
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
                size2 += CodedOutputStream.computeMessageSize(3, getScreenOff());
            }
            int size3 = size2 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static ResourcePowerManager parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (ResourcePowerManager) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static ResourcePowerManager parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (ResourcePowerManager) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static ResourcePowerManager parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (ResourcePowerManager) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static ResourcePowerManager parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (ResourcePowerManager) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static ResourcePowerManager parseFrom(InputStream input) throws IOException {
            return (ResourcePowerManager) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static ResourcePowerManager parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (ResourcePowerManager) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static ResourcePowerManager parseDelimitedFrom(InputStream input) throws IOException {
            return (ResourcePowerManager) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static ResourcePowerManager parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (ResourcePowerManager) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static ResourcePowerManager parseFrom(CodedInputStream input) throws IOException {
            return (ResourcePowerManager) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static ResourcePowerManager parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (ResourcePowerManager) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(ResourcePowerManager prototype) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<ResourcePowerManager, Builder> implements ResourcePowerManagerOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 x0) {
                this();
            }

            private Builder() {
                super(ResourcePowerManager.DEFAULT_INSTANCE);
            }

            @Override // android.os.SystemProto.ResourcePowerManagerOrBuilder
            public boolean hasName() {
                return ((ResourcePowerManager) this.instance).hasName();
            }

            @Override // android.os.SystemProto.ResourcePowerManagerOrBuilder
            public String getName() {
                return ((ResourcePowerManager) this.instance).getName();
            }

            @Override // android.os.SystemProto.ResourcePowerManagerOrBuilder
            public ByteString getNameBytes() {
                return ((ResourcePowerManager) this.instance).getNameBytes();
            }

            public Builder setName(String value) {
                copyOnWrite();
                ((ResourcePowerManager) this.instance).setName(value);
                return this;
            }

            public Builder clearName() {
                copyOnWrite();
                ((ResourcePowerManager) this.instance).clearName();
                return this;
            }

            public Builder setNameBytes(ByteString value) {
                copyOnWrite();
                ((ResourcePowerManager) this.instance).setNameBytes(value);
                return this;
            }

            @Override // android.os.SystemProto.ResourcePowerManagerOrBuilder
            public boolean hasTotal() {
                return ((ResourcePowerManager) this.instance).hasTotal();
            }

            @Override // android.os.SystemProto.ResourcePowerManagerOrBuilder
            public TimerProto getTotal() {
                return ((ResourcePowerManager) this.instance).getTotal();
            }

            public Builder setTotal(TimerProto value) {
                copyOnWrite();
                ((ResourcePowerManager) this.instance).setTotal((ResourcePowerManager) value);
                return this;
            }

            public Builder setTotal(TimerProto.Builder builderForValue) {
                copyOnWrite();
                ((ResourcePowerManager) this.instance).setTotal((ResourcePowerManager) builderForValue);
                return this;
            }

            public Builder mergeTotal(TimerProto value) {
                copyOnWrite();
                ((ResourcePowerManager) this.instance).mergeTotal(value);
                return this;
            }

            public Builder clearTotal() {
                copyOnWrite();
                ((ResourcePowerManager) this.instance).clearTotal();
                return this;
            }

            @Override // android.os.SystemProto.ResourcePowerManagerOrBuilder
            public boolean hasScreenOff() {
                return ((ResourcePowerManager) this.instance).hasScreenOff();
            }

            @Override // android.os.SystemProto.ResourcePowerManagerOrBuilder
            public TimerProto getScreenOff() {
                return ((ResourcePowerManager) this.instance).getScreenOff();
            }

            public Builder setScreenOff(TimerProto value) {
                copyOnWrite();
                ((ResourcePowerManager) this.instance).setScreenOff((ResourcePowerManager) value);
                return this;
            }

            public Builder setScreenOff(TimerProto.Builder builderForValue) {
                copyOnWrite();
                ((ResourcePowerManager) this.instance).setScreenOff((ResourcePowerManager) builderForValue);
                return this;
            }

            public Builder mergeScreenOff(TimerProto value) {
                copyOnWrite();
                ((ResourcePowerManager) this.instance).mergeScreenOff(value);
                return this;
            }

            public Builder clearScreenOff() {
                copyOnWrite();
                ((ResourcePowerManager) this.instance).clearScreenOff();
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new ResourcePowerManager();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder(null);
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    ResourcePowerManager other = (ResourcePowerManager) arg1;
                    this.name_ = visitor.visitString(hasName(), this.name_, other.hasName(), other.name_);
                    this.total_ = (TimerProto) visitor.visitMessage(this.total_, other.total_);
                    this.screenOff_ = (TimerProto) visitor.visitMessage(this.screenOff_, other.screenOff_);
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
                                    subBuilder2 = (TimerProto.Builder) this.screenOff_.toBuilder();
                                }
                                this.screenOff_ = (TimerProto) input.readMessage(TimerProto.parser(), extensionRegistry);
                                if (subBuilder2 != null) {
                                    subBuilder2.mergeFrom((GeneratedMessageLite) this.screenOff_);
                                    this.screenOff_ = (TimerProto) subBuilder2.buildPartial();
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
                        synchronized (ResourcePowerManager.class) {
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

        public static ResourcePowerManager getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<ResourcePowerManager> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class ScreenBrightness extends GeneratedMessageLite<ScreenBrightness, Builder> implements ScreenBrightnessOrBuilder {
        private static final ScreenBrightness DEFAULT_INSTANCE = new ScreenBrightness();
        public static final int NAME_FIELD_NUMBER = 1;
        private static volatile Parser<ScreenBrightness> PARSER = null;
        public static final int TOTAL_FIELD_NUMBER = 2;
        private int bitField0_;
        private int name_ = 0;
        private TimerProto total_;

        private ScreenBrightness() {
        }

        public enum Name implements Internal.EnumLite {
            DARK(0),
            DIM(1),
            MEDIUM(2),
            LIGHT(3),
            BRIGHT(4);
            
            public static final int BRIGHT_VALUE = 4;
            public static final int DARK_VALUE = 0;
            public static final int DIM_VALUE = 1;
            public static final int LIGHT_VALUE = 3;
            public static final int MEDIUM_VALUE = 2;
            private static final Internal.EnumLiteMap<Name> internalValueMap = new Internal.EnumLiteMap<Name>() {
                /* class android.os.SystemProto.ScreenBrightness.Name.AnonymousClass1 */

                @Override // com.google.protobuf.Internal.EnumLiteMap
                public Name findValueByNumber(int number) {
                    return Name.forNumber(number);
                }
            };
            private final int value;

            @Override // com.google.protobuf.Internal.EnumLite
            public final int getNumber() {
                return this.value;
            }

            @Deprecated
            public static Name valueOf(int value2) {
                return forNumber(value2);
            }

            public static Name forNumber(int value2) {
                if (value2 == 0) {
                    return DARK;
                }
                if (value2 == 1) {
                    return DIM;
                }
                if (value2 == 2) {
                    return MEDIUM;
                }
                if (value2 == 3) {
                    return LIGHT;
                }
                if (value2 != 4) {
                    return null;
                }
                return BRIGHT;
            }

            public static Internal.EnumLiteMap<Name> internalGetValueMap() {
                return internalValueMap;
            }

            private Name(int value2) {
                this.value = value2;
            }
        }

        @Override // android.os.SystemProto.ScreenBrightnessOrBuilder
        public boolean hasName() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // android.os.SystemProto.ScreenBrightnessOrBuilder
        public Name getName() {
            Name result = Name.forNumber(this.name_);
            return result == null ? Name.DARK : result;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setName(Name value) {
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

        @Override // android.os.SystemProto.ScreenBrightnessOrBuilder
        public boolean hasTotal() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // android.os.SystemProto.ScreenBrightnessOrBuilder
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

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                output.writeEnum(1, this.name_);
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeMessage(2, getTotal());
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
                size2 += CodedOutputStream.computeMessageSize(2, getTotal());
            }
            int size3 = size2 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static ScreenBrightness parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (ScreenBrightness) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static ScreenBrightness parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (ScreenBrightness) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static ScreenBrightness parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (ScreenBrightness) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static ScreenBrightness parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (ScreenBrightness) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static ScreenBrightness parseFrom(InputStream input) throws IOException {
            return (ScreenBrightness) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static ScreenBrightness parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (ScreenBrightness) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static ScreenBrightness parseDelimitedFrom(InputStream input) throws IOException {
            return (ScreenBrightness) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static ScreenBrightness parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (ScreenBrightness) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static ScreenBrightness parseFrom(CodedInputStream input) throws IOException {
            return (ScreenBrightness) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static ScreenBrightness parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (ScreenBrightness) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(ScreenBrightness prototype) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<ScreenBrightness, Builder> implements ScreenBrightnessOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 x0) {
                this();
            }

            private Builder() {
                super(ScreenBrightness.DEFAULT_INSTANCE);
            }

            @Override // android.os.SystemProto.ScreenBrightnessOrBuilder
            public boolean hasName() {
                return ((ScreenBrightness) this.instance).hasName();
            }

            @Override // android.os.SystemProto.ScreenBrightnessOrBuilder
            public Name getName() {
                return ((ScreenBrightness) this.instance).getName();
            }

            public Builder setName(Name value) {
                copyOnWrite();
                ((ScreenBrightness) this.instance).setName(value);
                return this;
            }

            public Builder clearName() {
                copyOnWrite();
                ((ScreenBrightness) this.instance).clearName();
                return this;
            }

            @Override // android.os.SystemProto.ScreenBrightnessOrBuilder
            public boolean hasTotal() {
                return ((ScreenBrightness) this.instance).hasTotal();
            }

            @Override // android.os.SystemProto.ScreenBrightnessOrBuilder
            public TimerProto getTotal() {
                return ((ScreenBrightness) this.instance).getTotal();
            }

            public Builder setTotal(TimerProto value) {
                copyOnWrite();
                ((ScreenBrightness) this.instance).setTotal((ScreenBrightness) value);
                return this;
            }

            public Builder setTotal(TimerProto.Builder builderForValue) {
                copyOnWrite();
                ((ScreenBrightness) this.instance).setTotal((ScreenBrightness) builderForValue);
                return this;
            }

            public Builder mergeTotal(TimerProto value) {
                copyOnWrite();
                ((ScreenBrightness) this.instance).mergeTotal(value);
                return this;
            }

            public Builder clearTotal() {
                copyOnWrite();
                ((ScreenBrightness) this.instance).clearTotal();
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new ScreenBrightness();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder(null);
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    ScreenBrightness other = (ScreenBrightness) arg1;
                    this.name_ = visitor.visitInt(hasName(), this.name_, other.hasName(), other.name_);
                    this.total_ = (TimerProto) visitor.visitMessage(this.total_, other.total_);
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
                                if (Name.forNumber(rawValue) == null) {
                                    super.mergeVarintField(1, rawValue);
                                } else {
                                    this.bitField0_ = 1 | this.bitField0_;
                                    this.name_ = rawValue;
                                }
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
                        synchronized (ScreenBrightness.class) {
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

        public static ScreenBrightness getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<ScreenBrightness> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class WakeupReason extends GeneratedMessageLite<WakeupReason, Builder> implements WakeupReasonOrBuilder {
        private static final WakeupReason DEFAULT_INSTANCE = new WakeupReason();
        public static final int NAME_FIELD_NUMBER = 1;
        private static volatile Parser<WakeupReason> PARSER = null;
        public static final int TOTAL_FIELD_NUMBER = 2;
        private int bitField0_;
        private String name_ = "";
        private TimerProto total_;

        private WakeupReason() {
        }

        @Override // android.os.SystemProto.WakeupReasonOrBuilder
        public boolean hasName() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // android.os.SystemProto.WakeupReasonOrBuilder
        public String getName() {
            return this.name_;
        }

        @Override // android.os.SystemProto.WakeupReasonOrBuilder
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

        @Override // android.os.SystemProto.WakeupReasonOrBuilder
        public boolean hasTotal() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // android.os.SystemProto.WakeupReasonOrBuilder
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

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                output.writeString(1, getName());
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeMessage(2, getTotal());
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
            int size3 = size2 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static WakeupReason parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (WakeupReason) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static WakeupReason parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (WakeupReason) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static WakeupReason parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (WakeupReason) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static WakeupReason parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (WakeupReason) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static WakeupReason parseFrom(InputStream input) throws IOException {
            return (WakeupReason) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static WakeupReason parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (WakeupReason) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static WakeupReason parseDelimitedFrom(InputStream input) throws IOException {
            return (WakeupReason) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static WakeupReason parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (WakeupReason) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static WakeupReason parseFrom(CodedInputStream input) throws IOException {
            return (WakeupReason) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static WakeupReason parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (WakeupReason) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(WakeupReason prototype) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<WakeupReason, Builder> implements WakeupReasonOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 x0) {
                this();
            }

            private Builder() {
                super(WakeupReason.DEFAULT_INSTANCE);
            }

            @Override // android.os.SystemProto.WakeupReasonOrBuilder
            public boolean hasName() {
                return ((WakeupReason) this.instance).hasName();
            }

            @Override // android.os.SystemProto.WakeupReasonOrBuilder
            public String getName() {
                return ((WakeupReason) this.instance).getName();
            }

            @Override // android.os.SystemProto.WakeupReasonOrBuilder
            public ByteString getNameBytes() {
                return ((WakeupReason) this.instance).getNameBytes();
            }

            public Builder setName(String value) {
                copyOnWrite();
                ((WakeupReason) this.instance).setName(value);
                return this;
            }

            public Builder clearName() {
                copyOnWrite();
                ((WakeupReason) this.instance).clearName();
                return this;
            }

            public Builder setNameBytes(ByteString value) {
                copyOnWrite();
                ((WakeupReason) this.instance).setNameBytes(value);
                return this;
            }

            @Override // android.os.SystemProto.WakeupReasonOrBuilder
            public boolean hasTotal() {
                return ((WakeupReason) this.instance).hasTotal();
            }

            @Override // android.os.SystemProto.WakeupReasonOrBuilder
            public TimerProto getTotal() {
                return ((WakeupReason) this.instance).getTotal();
            }

            public Builder setTotal(TimerProto value) {
                copyOnWrite();
                ((WakeupReason) this.instance).setTotal((WakeupReason) value);
                return this;
            }

            public Builder setTotal(TimerProto.Builder builderForValue) {
                copyOnWrite();
                ((WakeupReason) this.instance).setTotal((WakeupReason) builderForValue);
                return this;
            }

            public Builder mergeTotal(TimerProto value) {
                copyOnWrite();
                ((WakeupReason) this.instance).mergeTotal(value);
                return this;
            }

            public Builder clearTotal() {
                copyOnWrite();
                ((WakeupReason) this.instance).clearTotal();
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new WakeupReason();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder(null);
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    WakeupReason other = (WakeupReason) arg1;
                    this.name_ = visitor.visitString(hasName(), this.name_, other.hasName(), other.name_);
                    this.total_ = (TimerProto) visitor.visitMessage(this.total_, other.total_);
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
                        synchronized (WakeupReason.class) {
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

        public static WakeupReason getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<WakeupReason> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class WifiMulticastWakelockTotal extends GeneratedMessageLite<WifiMulticastWakelockTotal, Builder> implements WifiMulticastWakelockTotalOrBuilder {
        public static final int COUNT_FIELD_NUMBER = 2;
        private static final WifiMulticastWakelockTotal DEFAULT_INSTANCE = new WifiMulticastWakelockTotal();
        public static final int DURATION_MS_FIELD_NUMBER = 1;
        private static volatile Parser<WifiMulticastWakelockTotal> PARSER;
        private int bitField0_;
        private int count_ = 0;
        private long durationMs_ = 0;

        private WifiMulticastWakelockTotal() {
        }

        @Override // android.os.SystemProto.WifiMulticastWakelockTotalOrBuilder
        public boolean hasDurationMs() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // android.os.SystemProto.WifiMulticastWakelockTotalOrBuilder
        public long getDurationMs() {
            return this.durationMs_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setDurationMs(long value) {
            this.bitField0_ |= 1;
            this.durationMs_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearDurationMs() {
            this.bitField0_ &= -2;
            this.durationMs_ = 0;
        }

        @Override // android.os.SystemProto.WifiMulticastWakelockTotalOrBuilder
        public boolean hasCount() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // android.os.SystemProto.WifiMulticastWakelockTotalOrBuilder
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
                output.writeInt64(1, this.durationMs_);
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
                size2 = 0 + CodedOutputStream.computeInt64Size(1, this.durationMs_);
            }
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeInt32Size(2, this.count_);
            }
            int size3 = size2 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static WifiMulticastWakelockTotal parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (WifiMulticastWakelockTotal) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static WifiMulticastWakelockTotal parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (WifiMulticastWakelockTotal) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static WifiMulticastWakelockTotal parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (WifiMulticastWakelockTotal) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static WifiMulticastWakelockTotal parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (WifiMulticastWakelockTotal) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static WifiMulticastWakelockTotal parseFrom(InputStream input) throws IOException {
            return (WifiMulticastWakelockTotal) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static WifiMulticastWakelockTotal parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (WifiMulticastWakelockTotal) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static WifiMulticastWakelockTotal parseDelimitedFrom(InputStream input) throws IOException {
            return (WifiMulticastWakelockTotal) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static WifiMulticastWakelockTotal parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (WifiMulticastWakelockTotal) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static WifiMulticastWakelockTotal parseFrom(CodedInputStream input) throws IOException {
            return (WifiMulticastWakelockTotal) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static WifiMulticastWakelockTotal parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (WifiMulticastWakelockTotal) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(WifiMulticastWakelockTotal prototype) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<WifiMulticastWakelockTotal, Builder> implements WifiMulticastWakelockTotalOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 x0) {
                this();
            }

            private Builder() {
                super(WifiMulticastWakelockTotal.DEFAULT_INSTANCE);
            }

            @Override // android.os.SystemProto.WifiMulticastWakelockTotalOrBuilder
            public boolean hasDurationMs() {
                return ((WifiMulticastWakelockTotal) this.instance).hasDurationMs();
            }

            @Override // android.os.SystemProto.WifiMulticastWakelockTotalOrBuilder
            public long getDurationMs() {
                return ((WifiMulticastWakelockTotal) this.instance).getDurationMs();
            }

            public Builder setDurationMs(long value) {
                copyOnWrite();
                ((WifiMulticastWakelockTotal) this.instance).setDurationMs(value);
                return this;
            }

            public Builder clearDurationMs() {
                copyOnWrite();
                ((WifiMulticastWakelockTotal) this.instance).clearDurationMs();
                return this;
            }

            @Override // android.os.SystemProto.WifiMulticastWakelockTotalOrBuilder
            public boolean hasCount() {
                return ((WifiMulticastWakelockTotal) this.instance).hasCount();
            }

            @Override // android.os.SystemProto.WifiMulticastWakelockTotalOrBuilder
            public int getCount() {
                return ((WifiMulticastWakelockTotal) this.instance).getCount();
            }

            public Builder setCount(int value) {
                copyOnWrite();
                ((WifiMulticastWakelockTotal) this.instance).setCount(value);
                return this;
            }

            public Builder clearCount() {
                copyOnWrite();
                ((WifiMulticastWakelockTotal) this.instance).clearCount();
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new WifiMulticastWakelockTotal();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder(null);
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    WifiMulticastWakelockTotal other = (WifiMulticastWakelockTotal) arg1;
                    this.durationMs_ = visitor.visitLong(hasDurationMs(), this.durationMs_, other.hasDurationMs(), other.durationMs_);
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
                                this.bitField0_ |= 1;
                                this.durationMs_ = input.readInt64();
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
                        synchronized (WifiMulticastWakelockTotal.class) {
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

        public static WifiMulticastWakelockTotal getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<WifiMulticastWakelockTotal> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class WifiSignalStrength extends GeneratedMessageLite<WifiSignalStrength, Builder> implements WifiSignalStrengthOrBuilder {
        private static final WifiSignalStrength DEFAULT_INSTANCE = new WifiSignalStrength();
        public static final int NAME_FIELD_NUMBER = 1;
        private static volatile Parser<WifiSignalStrength> PARSER = null;
        public static final int TOTAL_FIELD_NUMBER = 2;
        private int bitField0_;
        private int name_ = 0;
        private TimerProto total_;

        private WifiSignalStrength() {
        }

        public enum Name implements Internal.EnumLite {
            NONE(0),
            POOR(1),
            MODERATE(2),
            GOOD(3),
            GREAT(4);
            
            public static final int GOOD_VALUE = 3;
            public static final int GREAT_VALUE = 4;
            public static final int MODERATE_VALUE = 2;
            public static final int NONE_VALUE = 0;
            public static final int POOR_VALUE = 1;
            private static final Internal.EnumLiteMap<Name> internalValueMap = new Internal.EnumLiteMap<Name>() {
                /* class android.os.SystemProto.WifiSignalStrength.Name.AnonymousClass1 */

                @Override // com.google.protobuf.Internal.EnumLiteMap
                public Name findValueByNumber(int number) {
                    return Name.forNumber(number);
                }
            };
            private final int value;

            @Override // com.google.protobuf.Internal.EnumLite
            public final int getNumber() {
                return this.value;
            }

            @Deprecated
            public static Name valueOf(int value2) {
                return forNumber(value2);
            }

            public static Name forNumber(int value2) {
                if (value2 == 0) {
                    return NONE;
                }
                if (value2 == 1) {
                    return POOR;
                }
                if (value2 == 2) {
                    return MODERATE;
                }
                if (value2 == 3) {
                    return GOOD;
                }
                if (value2 != 4) {
                    return null;
                }
                return GREAT;
            }

            public static Internal.EnumLiteMap<Name> internalGetValueMap() {
                return internalValueMap;
            }

            private Name(int value2) {
                this.value = value2;
            }
        }

        @Override // android.os.SystemProto.WifiSignalStrengthOrBuilder
        public boolean hasName() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // android.os.SystemProto.WifiSignalStrengthOrBuilder
        public Name getName() {
            Name result = Name.forNumber(this.name_);
            return result == null ? Name.NONE : result;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setName(Name value) {
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

        @Override // android.os.SystemProto.WifiSignalStrengthOrBuilder
        public boolean hasTotal() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // android.os.SystemProto.WifiSignalStrengthOrBuilder
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

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                output.writeEnum(1, this.name_);
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeMessage(2, getTotal());
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
                size2 += CodedOutputStream.computeMessageSize(2, getTotal());
            }
            int size3 = size2 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static WifiSignalStrength parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (WifiSignalStrength) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static WifiSignalStrength parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (WifiSignalStrength) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static WifiSignalStrength parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (WifiSignalStrength) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static WifiSignalStrength parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (WifiSignalStrength) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static WifiSignalStrength parseFrom(InputStream input) throws IOException {
            return (WifiSignalStrength) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static WifiSignalStrength parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (WifiSignalStrength) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static WifiSignalStrength parseDelimitedFrom(InputStream input) throws IOException {
            return (WifiSignalStrength) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static WifiSignalStrength parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (WifiSignalStrength) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static WifiSignalStrength parseFrom(CodedInputStream input) throws IOException {
            return (WifiSignalStrength) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static WifiSignalStrength parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (WifiSignalStrength) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(WifiSignalStrength prototype) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<WifiSignalStrength, Builder> implements WifiSignalStrengthOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 x0) {
                this();
            }

            private Builder() {
                super(WifiSignalStrength.DEFAULT_INSTANCE);
            }

            @Override // android.os.SystemProto.WifiSignalStrengthOrBuilder
            public boolean hasName() {
                return ((WifiSignalStrength) this.instance).hasName();
            }

            @Override // android.os.SystemProto.WifiSignalStrengthOrBuilder
            public Name getName() {
                return ((WifiSignalStrength) this.instance).getName();
            }

            public Builder setName(Name value) {
                copyOnWrite();
                ((WifiSignalStrength) this.instance).setName(value);
                return this;
            }

            public Builder clearName() {
                copyOnWrite();
                ((WifiSignalStrength) this.instance).clearName();
                return this;
            }

            @Override // android.os.SystemProto.WifiSignalStrengthOrBuilder
            public boolean hasTotal() {
                return ((WifiSignalStrength) this.instance).hasTotal();
            }

            @Override // android.os.SystemProto.WifiSignalStrengthOrBuilder
            public TimerProto getTotal() {
                return ((WifiSignalStrength) this.instance).getTotal();
            }

            public Builder setTotal(TimerProto value) {
                copyOnWrite();
                ((WifiSignalStrength) this.instance).setTotal((WifiSignalStrength) value);
                return this;
            }

            public Builder setTotal(TimerProto.Builder builderForValue) {
                copyOnWrite();
                ((WifiSignalStrength) this.instance).setTotal((WifiSignalStrength) builderForValue);
                return this;
            }

            public Builder mergeTotal(TimerProto value) {
                copyOnWrite();
                ((WifiSignalStrength) this.instance).mergeTotal(value);
                return this;
            }

            public Builder clearTotal() {
                copyOnWrite();
                ((WifiSignalStrength) this.instance).clearTotal();
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new WifiSignalStrength();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder(null);
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    WifiSignalStrength other = (WifiSignalStrength) arg1;
                    this.name_ = visitor.visitInt(hasName(), this.name_, other.hasName(), other.name_);
                    this.total_ = (TimerProto) visitor.visitMessage(this.total_, other.total_);
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
                                if (Name.forNumber(rawValue) == null) {
                                    super.mergeVarintField(1, rawValue);
                                } else {
                                    this.bitField0_ = 1 | this.bitField0_;
                                    this.name_ = rawValue;
                                }
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
                        synchronized (WifiSignalStrength.class) {
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

        public static WifiSignalStrength getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<WifiSignalStrength> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class WifiState extends GeneratedMessageLite<WifiState, Builder> implements WifiStateOrBuilder {
        private static final WifiState DEFAULT_INSTANCE = new WifiState();
        public static final int NAME_FIELD_NUMBER = 1;
        private static volatile Parser<WifiState> PARSER = null;
        public static final int TOTAL_FIELD_NUMBER = 2;
        private int bitField0_;
        private int name_ = 0;
        private TimerProto total_;

        private WifiState() {
        }

        public enum Name implements Internal.EnumLite {
            OFF(0),
            OFF_SCANNING(1),
            ON_NO_NETWORKS(2),
            ON_DISCONNECTED(3),
            ON_CONNECTED_STA(4),
            ON_CONNECTED_P2P(5),
            ON_CONNECTED_STA_P2P(6),
            SOFT_AP(7);
            
            public static final int OFF_SCANNING_VALUE = 1;
            public static final int OFF_VALUE = 0;
            public static final int ON_CONNECTED_P2P_VALUE = 5;
            public static final int ON_CONNECTED_STA_P2P_VALUE = 6;
            public static final int ON_CONNECTED_STA_VALUE = 4;
            public static final int ON_DISCONNECTED_VALUE = 3;
            public static final int ON_NO_NETWORKS_VALUE = 2;
            public static final int SOFT_AP_VALUE = 7;
            private static final Internal.EnumLiteMap<Name> internalValueMap = new Internal.EnumLiteMap<Name>() {
                /* class android.os.SystemProto.WifiState.Name.AnonymousClass1 */

                @Override // com.google.protobuf.Internal.EnumLiteMap
                public Name findValueByNumber(int number) {
                    return Name.forNumber(number);
                }
            };
            private final int value;

            @Override // com.google.protobuf.Internal.EnumLite
            public final int getNumber() {
                return this.value;
            }

            @Deprecated
            public static Name valueOf(int value2) {
                return forNumber(value2);
            }

            public static Name forNumber(int value2) {
                switch (value2) {
                    case 0:
                        return OFF;
                    case 1:
                        return OFF_SCANNING;
                    case 2:
                        return ON_NO_NETWORKS;
                    case 3:
                        return ON_DISCONNECTED;
                    case 4:
                        return ON_CONNECTED_STA;
                    case 5:
                        return ON_CONNECTED_P2P;
                    case 6:
                        return ON_CONNECTED_STA_P2P;
                    case 7:
                        return SOFT_AP;
                    default:
                        return null;
                }
            }

            public static Internal.EnumLiteMap<Name> internalGetValueMap() {
                return internalValueMap;
            }

            private Name(int value2) {
                this.value = value2;
            }
        }

        @Override // android.os.SystemProto.WifiStateOrBuilder
        public boolean hasName() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // android.os.SystemProto.WifiStateOrBuilder
        public Name getName() {
            Name result = Name.forNumber(this.name_);
            return result == null ? Name.OFF : result;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setName(Name value) {
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

        @Override // android.os.SystemProto.WifiStateOrBuilder
        public boolean hasTotal() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // android.os.SystemProto.WifiStateOrBuilder
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

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                output.writeEnum(1, this.name_);
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeMessage(2, getTotal());
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
                size2 += CodedOutputStream.computeMessageSize(2, getTotal());
            }
            int size3 = size2 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static WifiState parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (WifiState) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static WifiState parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (WifiState) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static WifiState parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (WifiState) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static WifiState parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (WifiState) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static WifiState parseFrom(InputStream input) throws IOException {
            return (WifiState) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static WifiState parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (WifiState) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static WifiState parseDelimitedFrom(InputStream input) throws IOException {
            return (WifiState) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static WifiState parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (WifiState) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static WifiState parseFrom(CodedInputStream input) throws IOException {
            return (WifiState) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static WifiState parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (WifiState) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(WifiState prototype) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<WifiState, Builder> implements WifiStateOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 x0) {
                this();
            }

            private Builder() {
                super(WifiState.DEFAULT_INSTANCE);
            }

            @Override // android.os.SystemProto.WifiStateOrBuilder
            public boolean hasName() {
                return ((WifiState) this.instance).hasName();
            }

            @Override // android.os.SystemProto.WifiStateOrBuilder
            public Name getName() {
                return ((WifiState) this.instance).getName();
            }

            public Builder setName(Name value) {
                copyOnWrite();
                ((WifiState) this.instance).setName(value);
                return this;
            }

            public Builder clearName() {
                copyOnWrite();
                ((WifiState) this.instance).clearName();
                return this;
            }

            @Override // android.os.SystemProto.WifiStateOrBuilder
            public boolean hasTotal() {
                return ((WifiState) this.instance).hasTotal();
            }

            @Override // android.os.SystemProto.WifiStateOrBuilder
            public TimerProto getTotal() {
                return ((WifiState) this.instance).getTotal();
            }

            public Builder setTotal(TimerProto value) {
                copyOnWrite();
                ((WifiState) this.instance).setTotal((WifiState) value);
                return this;
            }

            public Builder setTotal(TimerProto.Builder builderForValue) {
                copyOnWrite();
                ((WifiState) this.instance).setTotal((WifiState) builderForValue);
                return this;
            }

            public Builder mergeTotal(TimerProto value) {
                copyOnWrite();
                ((WifiState) this.instance).mergeTotal(value);
                return this;
            }

            public Builder clearTotal() {
                copyOnWrite();
                ((WifiState) this.instance).clearTotal();
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new WifiState();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder(null);
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    WifiState other = (WifiState) arg1;
                    this.name_ = visitor.visitInt(hasName(), this.name_, other.hasName(), other.name_);
                    this.total_ = (TimerProto) visitor.visitMessage(this.total_, other.total_);
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
                                if (Name.forNumber(rawValue) == null) {
                                    super.mergeVarintField(1, rawValue);
                                } else {
                                    this.bitField0_ = 1 | this.bitField0_;
                                    this.name_ = rawValue;
                                }
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
                        synchronized (WifiState.class) {
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

        public static WifiState getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<WifiState> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class WifiSupplicantState extends GeneratedMessageLite<WifiSupplicantState, Builder> implements WifiSupplicantStateOrBuilder {
        private static final WifiSupplicantState DEFAULT_INSTANCE = new WifiSupplicantState();
        public static final int NAME_FIELD_NUMBER = 1;
        private static volatile Parser<WifiSupplicantState> PARSER = null;
        public static final int TOTAL_FIELD_NUMBER = 2;
        private int bitField0_;
        private int name_ = 0;
        private TimerProto total_;

        private WifiSupplicantState() {
        }

        public enum Name implements Internal.EnumLite {
            INVALID(0),
            DISCONNECTED(1),
            INTERFACE_DISABLED(2),
            INACTIVE(3),
            SCANNING(4),
            AUTHENTICATING(5),
            ASSOCIATING(6),
            ASSOCIATED(7),
            FOUR_WAY_HANDSHAKE(8),
            GROUP_HANDSHAKE(9),
            COMPLETED(10),
            DORMANT(11),
            UNINITIALIZED(12);
            
            public static final int ASSOCIATED_VALUE = 7;
            public static final int ASSOCIATING_VALUE = 6;
            public static final int AUTHENTICATING_VALUE = 5;
            public static final int COMPLETED_VALUE = 10;
            public static final int DISCONNECTED_VALUE = 1;
            public static final int DORMANT_VALUE = 11;
            public static final int FOUR_WAY_HANDSHAKE_VALUE = 8;
            public static final int GROUP_HANDSHAKE_VALUE = 9;
            public static final int INACTIVE_VALUE = 3;
            public static final int INTERFACE_DISABLED_VALUE = 2;
            public static final int INVALID_VALUE = 0;
            public static final int SCANNING_VALUE = 4;
            public static final int UNINITIALIZED_VALUE = 12;
            private static final Internal.EnumLiteMap<Name> internalValueMap = new Internal.EnumLiteMap<Name>() {
                /* class android.os.SystemProto.WifiSupplicantState.Name.AnonymousClass1 */

                @Override // com.google.protobuf.Internal.EnumLiteMap
                public Name findValueByNumber(int number) {
                    return Name.forNumber(number);
                }
            };
            private final int value;

            @Override // com.google.protobuf.Internal.EnumLite
            public final int getNumber() {
                return this.value;
            }

            @Deprecated
            public static Name valueOf(int value2) {
                return forNumber(value2);
            }

            public static Name forNumber(int value2) {
                switch (value2) {
                    case 0:
                        return INVALID;
                    case 1:
                        return DISCONNECTED;
                    case 2:
                        return INTERFACE_DISABLED;
                    case 3:
                        return INACTIVE;
                    case 4:
                        return SCANNING;
                    case 5:
                        return AUTHENTICATING;
                    case 6:
                        return ASSOCIATING;
                    case 7:
                        return ASSOCIATED;
                    case 8:
                        return FOUR_WAY_HANDSHAKE;
                    case 9:
                        return GROUP_HANDSHAKE;
                    case 10:
                        return COMPLETED;
                    case 11:
                        return DORMANT;
                    case 12:
                        return UNINITIALIZED;
                    default:
                        return null;
                }
            }

            public static Internal.EnumLiteMap<Name> internalGetValueMap() {
                return internalValueMap;
            }

            private Name(int value2) {
                this.value = value2;
            }
        }

        @Override // android.os.SystemProto.WifiSupplicantStateOrBuilder
        public boolean hasName() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // android.os.SystemProto.WifiSupplicantStateOrBuilder
        public Name getName() {
            Name result = Name.forNumber(this.name_);
            return result == null ? Name.INVALID : result;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setName(Name value) {
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

        @Override // android.os.SystemProto.WifiSupplicantStateOrBuilder
        public boolean hasTotal() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // android.os.SystemProto.WifiSupplicantStateOrBuilder
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

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                output.writeEnum(1, this.name_);
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeMessage(2, getTotal());
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
                size2 += CodedOutputStream.computeMessageSize(2, getTotal());
            }
            int size3 = size2 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static WifiSupplicantState parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (WifiSupplicantState) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static WifiSupplicantState parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (WifiSupplicantState) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static WifiSupplicantState parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (WifiSupplicantState) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static WifiSupplicantState parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (WifiSupplicantState) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static WifiSupplicantState parseFrom(InputStream input) throws IOException {
            return (WifiSupplicantState) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static WifiSupplicantState parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (WifiSupplicantState) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static WifiSupplicantState parseDelimitedFrom(InputStream input) throws IOException {
            return (WifiSupplicantState) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static WifiSupplicantState parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (WifiSupplicantState) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static WifiSupplicantState parseFrom(CodedInputStream input) throws IOException {
            return (WifiSupplicantState) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static WifiSupplicantState parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (WifiSupplicantState) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(WifiSupplicantState prototype) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<WifiSupplicantState, Builder> implements WifiSupplicantStateOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 x0) {
                this();
            }

            private Builder() {
                super(WifiSupplicantState.DEFAULT_INSTANCE);
            }

            @Override // android.os.SystemProto.WifiSupplicantStateOrBuilder
            public boolean hasName() {
                return ((WifiSupplicantState) this.instance).hasName();
            }

            @Override // android.os.SystemProto.WifiSupplicantStateOrBuilder
            public Name getName() {
                return ((WifiSupplicantState) this.instance).getName();
            }

            public Builder setName(Name value) {
                copyOnWrite();
                ((WifiSupplicantState) this.instance).setName(value);
                return this;
            }

            public Builder clearName() {
                copyOnWrite();
                ((WifiSupplicantState) this.instance).clearName();
                return this;
            }

            @Override // android.os.SystemProto.WifiSupplicantStateOrBuilder
            public boolean hasTotal() {
                return ((WifiSupplicantState) this.instance).hasTotal();
            }

            @Override // android.os.SystemProto.WifiSupplicantStateOrBuilder
            public TimerProto getTotal() {
                return ((WifiSupplicantState) this.instance).getTotal();
            }

            public Builder setTotal(TimerProto value) {
                copyOnWrite();
                ((WifiSupplicantState) this.instance).setTotal((WifiSupplicantState) value);
                return this;
            }

            public Builder setTotal(TimerProto.Builder builderForValue) {
                copyOnWrite();
                ((WifiSupplicantState) this.instance).setTotal((WifiSupplicantState) builderForValue);
                return this;
            }

            public Builder mergeTotal(TimerProto value) {
                copyOnWrite();
                ((WifiSupplicantState) this.instance).mergeTotal(value);
                return this;
            }

            public Builder clearTotal() {
                copyOnWrite();
                ((WifiSupplicantState) this.instance).clearTotal();
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new WifiSupplicantState();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder(null);
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    WifiSupplicantState other = (WifiSupplicantState) arg1;
                    this.name_ = visitor.visitInt(hasName(), this.name_, other.hasName(), other.name_);
                    this.total_ = (TimerProto) visitor.visitMessage(this.total_, other.total_);
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
                                if (Name.forNumber(rawValue) == null) {
                                    super.mergeVarintField(1, rawValue);
                                } else {
                                    this.bitField0_ = 1 | this.bitField0_;
                                    this.name_ = rawValue;
                                }
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
                        synchronized (WifiSupplicantState.class) {
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

        public static WifiSupplicantState getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<WifiSupplicantState> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public enum TimeRemainingCase implements Internal.EnumLite {
        CHARGE_TIME_REMAINING_MS(3),
        DISCHARGE_TIME_REMAINING_MS(4),
        TIMEREMAINING_NOT_SET(0);
        
        private final int value;

        private TimeRemainingCase(int value2) {
            this.value = value2;
        }

        @Deprecated
        public static TimeRemainingCase valueOf(int value2) {
            return forNumber(value2);
        }

        public static TimeRemainingCase forNumber(int value2) {
            if (value2 == 0) {
                return TIMEREMAINING_NOT_SET;
            }
            if (value2 == 3) {
                return CHARGE_TIME_REMAINING_MS;
            }
            if (value2 != 4) {
                return null;
            }
            return DISCHARGE_TIME_REMAINING_MS;
        }

        @Override // com.google.protobuf.Internal.EnumLite
        public int getNumber() {
            return this.value;
        }
    }

    @Override // android.os.SystemProtoOrBuilder
    public TimeRemainingCase getTimeRemainingCase() {
        return TimeRemainingCase.forNumber(this.timeRemainingCase_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearTimeRemaining() {
        this.timeRemainingCase_ = 0;
        this.timeRemaining_ = null;
    }

    @Override // android.os.SystemProtoOrBuilder
    public boolean hasBattery() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // android.os.SystemProtoOrBuilder
    public Battery getBattery() {
        Battery battery = this.battery_;
        return battery == null ? Battery.getDefaultInstance() : battery;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setBattery(Battery value) {
        if (value != null) {
            this.battery_ = value;
            this.bitField0_ |= 1;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setBattery(Battery.Builder builderForValue) {
        this.battery_ = (Battery) builderForValue.build();
        this.bitField0_ |= 1;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeBattery(Battery value) {
        Battery battery = this.battery_;
        if (battery == null || battery == Battery.getDefaultInstance()) {
            this.battery_ = value;
        } else {
            this.battery_ = (Battery) ((Battery.Builder) Battery.newBuilder(this.battery_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 1;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearBattery() {
        this.battery_ = null;
        this.bitField0_ &= -2;
    }

    @Override // android.os.SystemProtoOrBuilder
    public boolean hasBatteryDischarge() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // android.os.SystemProtoOrBuilder
    public BatteryDischarge getBatteryDischarge() {
        BatteryDischarge batteryDischarge = this.batteryDischarge_;
        return batteryDischarge == null ? BatteryDischarge.getDefaultInstance() : batteryDischarge;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setBatteryDischarge(BatteryDischarge value) {
        if (value != null) {
            this.batteryDischarge_ = value;
            this.bitField0_ |= 2;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setBatteryDischarge(BatteryDischarge.Builder builderForValue) {
        this.batteryDischarge_ = (BatteryDischarge) builderForValue.build();
        this.bitField0_ |= 2;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeBatteryDischarge(BatteryDischarge value) {
        BatteryDischarge batteryDischarge = this.batteryDischarge_;
        if (batteryDischarge == null || batteryDischarge == BatteryDischarge.getDefaultInstance()) {
            this.batteryDischarge_ = value;
        } else {
            this.batteryDischarge_ = (BatteryDischarge) ((BatteryDischarge.Builder) BatteryDischarge.newBuilder(this.batteryDischarge_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 2;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearBatteryDischarge() {
        this.batteryDischarge_ = null;
        this.bitField0_ &= -3;
    }

    @Override // android.os.SystemProtoOrBuilder
    public boolean hasChargeTimeRemainingMs() {
        return this.timeRemainingCase_ == 3;
    }

    @Override // android.os.SystemProtoOrBuilder
    public long getChargeTimeRemainingMs() {
        if (this.timeRemainingCase_ == 3) {
            return ((Long) this.timeRemaining_).longValue();
        }
        return 0;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setChargeTimeRemainingMs(long value) {
        this.timeRemainingCase_ = 3;
        this.timeRemaining_ = Long.valueOf(value);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearChargeTimeRemainingMs() {
        if (this.timeRemainingCase_ == 3) {
            this.timeRemainingCase_ = 0;
            this.timeRemaining_ = null;
        }
    }

    @Override // android.os.SystemProtoOrBuilder
    public boolean hasDischargeTimeRemainingMs() {
        return this.timeRemainingCase_ == 4;
    }

    @Override // android.os.SystemProtoOrBuilder
    public long getDischargeTimeRemainingMs() {
        if (this.timeRemainingCase_ == 4) {
            return ((Long) this.timeRemaining_).longValue();
        }
        return 0;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setDischargeTimeRemainingMs(long value) {
        this.timeRemainingCase_ = 4;
        this.timeRemaining_ = Long.valueOf(value);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearDischargeTimeRemainingMs() {
        if (this.timeRemainingCase_ == 4) {
            this.timeRemainingCase_ = 0;
            this.timeRemaining_ = null;
        }
    }

    @Override // android.os.SystemProtoOrBuilder
    public List<BatteryLevelStep> getChargeStepList() {
        return this.chargeStep_;
    }

    public List<? extends BatteryLevelStepOrBuilder> getChargeStepOrBuilderList() {
        return this.chargeStep_;
    }

    @Override // android.os.SystemProtoOrBuilder
    public int getChargeStepCount() {
        return this.chargeStep_.size();
    }

    @Override // android.os.SystemProtoOrBuilder
    public BatteryLevelStep getChargeStep(int index) {
        return this.chargeStep_.get(index);
    }

    public BatteryLevelStepOrBuilder getChargeStepOrBuilder(int index) {
        return this.chargeStep_.get(index);
    }

    private void ensureChargeStepIsMutable() {
        if (!this.chargeStep_.isModifiable()) {
            this.chargeStep_ = GeneratedMessageLite.mutableCopy(this.chargeStep_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setChargeStep(int index, BatteryLevelStep value) {
        if (value != null) {
            ensureChargeStepIsMutable();
            this.chargeStep_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setChargeStep(int index, BatteryLevelStep.Builder builderForValue) {
        ensureChargeStepIsMutable();
        this.chargeStep_.set(index, (BatteryLevelStep) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addChargeStep(BatteryLevelStep value) {
        if (value != null) {
            ensureChargeStepIsMutable();
            this.chargeStep_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addChargeStep(int index, BatteryLevelStep value) {
        if (value != null) {
            ensureChargeStepIsMutable();
            this.chargeStep_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addChargeStep(BatteryLevelStep.Builder builderForValue) {
        ensureChargeStepIsMutable();
        this.chargeStep_.add((BatteryLevelStep) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addChargeStep(int index, BatteryLevelStep.Builder builderForValue) {
        ensureChargeStepIsMutable();
        this.chargeStep_.add(index, (BatteryLevelStep) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllChargeStep(Iterable<? extends BatteryLevelStep> values) {
        ensureChargeStepIsMutable();
        AbstractMessageLite.addAll(values, this.chargeStep_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearChargeStep() {
        this.chargeStep_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeChargeStep(int index) {
        ensureChargeStepIsMutable();
        this.chargeStep_.remove(index);
    }

    @Override // android.os.SystemProtoOrBuilder
    public List<BatteryLevelStep> getDischargeStepList() {
        return this.dischargeStep_;
    }

    public List<? extends BatteryLevelStepOrBuilder> getDischargeStepOrBuilderList() {
        return this.dischargeStep_;
    }

    @Override // android.os.SystemProtoOrBuilder
    public int getDischargeStepCount() {
        return this.dischargeStep_.size();
    }

    @Override // android.os.SystemProtoOrBuilder
    public BatteryLevelStep getDischargeStep(int index) {
        return this.dischargeStep_.get(index);
    }

    public BatteryLevelStepOrBuilder getDischargeStepOrBuilder(int index) {
        return this.dischargeStep_.get(index);
    }

    private void ensureDischargeStepIsMutable() {
        if (!this.dischargeStep_.isModifiable()) {
            this.dischargeStep_ = GeneratedMessageLite.mutableCopy(this.dischargeStep_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setDischargeStep(int index, BatteryLevelStep value) {
        if (value != null) {
            ensureDischargeStepIsMutable();
            this.dischargeStep_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setDischargeStep(int index, BatteryLevelStep.Builder builderForValue) {
        ensureDischargeStepIsMutable();
        this.dischargeStep_.set(index, (BatteryLevelStep) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addDischargeStep(BatteryLevelStep value) {
        if (value != null) {
            ensureDischargeStepIsMutable();
            this.dischargeStep_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addDischargeStep(int index, BatteryLevelStep value) {
        if (value != null) {
            ensureDischargeStepIsMutable();
            this.dischargeStep_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addDischargeStep(BatteryLevelStep.Builder builderForValue) {
        ensureDischargeStepIsMutable();
        this.dischargeStep_.add((BatteryLevelStep) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addDischargeStep(int index, BatteryLevelStep.Builder builderForValue) {
        ensureDischargeStepIsMutable();
        this.dischargeStep_.add(index, (BatteryLevelStep) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllDischargeStep(Iterable<? extends BatteryLevelStep> values) {
        ensureDischargeStepIsMutable();
        AbstractMessageLite.addAll(values, this.dischargeStep_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearDischargeStep() {
        this.dischargeStep_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeDischargeStep(int index) {
        ensureDischargeStepIsMutable();
        this.dischargeStep_.remove(index);
    }

    @Override // android.os.SystemProtoOrBuilder
    public List<Long> getCpuFrequencyList() {
        return this.cpuFrequency_;
    }

    @Override // android.os.SystemProtoOrBuilder
    public int getCpuFrequencyCount() {
        return this.cpuFrequency_.size();
    }

    @Override // android.os.SystemProtoOrBuilder
    public long getCpuFrequency(int index) {
        return this.cpuFrequency_.getLong(index);
    }

    private void ensureCpuFrequencyIsMutable() {
        if (!this.cpuFrequency_.isModifiable()) {
            this.cpuFrequency_ = GeneratedMessageLite.mutableCopy(this.cpuFrequency_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setCpuFrequency(int index, long value) {
        ensureCpuFrequencyIsMutable();
        this.cpuFrequency_.setLong(index, value);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addCpuFrequency(long value) {
        ensureCpuFrequencyIsMutable();
        this.cpuFrequency_.addLong(value);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllCpuFrequency(Iterable<? extends Long> values) {
        ensureCpuFrequencyIsMutable();
        AbstractMessageLite.addAll(values, this.cpuFrequency_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearCpuFrequency() {
        this.cpuFrequency_ = emptyLongList();
    }

    @Override // android.os.SystemProtoOrBuilder
    public List<DataConnection> getDataConnectionList() {
        return this.dataConnection_;
    }

    public List<? extends DataConnectionOrBuilder> getDataConnectionOrBuilderList() {
        return this.dataConnection_;
    }

    @Override // android.os.SystemProtoOrBuilder
    public int getDataConnectionCount() {
        return this.dataConnection_.size();
    }

    @Override // android.os.SystemProtoOrBuilder
    public DataConnection getDataConnection(int index) {
        return this.dataConnection_.get(index);
    }

    public DataConnectionOrBuilder getDataConnectionOrBuilder(int index) {
        return this.dataConnection_.get(index);
    }

    private void ensureDataConnectionIsMutable() {
        if (!this.dataConnection_.isModifiable()) {
            this.dataConnection_ = GeneratedMessageLite.mutableCopy(this.dataConnection_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setDataConnection(int index, DataConnection value) {
        if (value != null) {
            ensureDataConnectionIsMutable();
            this.dataConnection_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setDataConnection(int index, DataConnection.Builder builderForValue) {
        ensureDataConnectionIsMutable();
        this.dataConnection_.set(index, (DataConnection) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addDataConnection(DataConnection value) {
        if (value != null) {
            ensureDataConnectionIsMutable();
            this.dataConnection_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addDataConnection(int index, DataConnection value) {
        if (value != null) {
            ensureDataConnectionIsMutable();
            this.dataConnection_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addDataConnection(DataConnection.Builder builderForValue) {
        ensureDataConnectionIsMutable();
        this.dataConnection_.add((DataConnection) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addDataConnection(int index, DataConnection.Builder builderForValue) {
        ensureDataConnectionIsMutable();
        this.dataConnection_.add(index, (DataConnection) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllDataConnection(Iterable<? extends DataConnection> values) {
        ensureDataConnectionIsMutable();
        AbstractMessageLite.addAll(values, this.dataConnection_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearDataConnection() {
        this.dataConnection_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeDataConnection(int index) {
        ensureDataConnectionIsMutable();
        this.dataConnection_.remove(index);
    }

    @Override // android.os.SystemProtoOrBuilder
    public boolean hasGlobalBluetoothController() {
        return (this.bitField0_ & 16) == 16;
    }

    @Override // android.os.SystemProtoOrBuilder
    public ControllerActivityProto getGlobalBluetoothController() {
        ControllerActivityProto controllerActivityProto = this.globalBluetoothController_;
        return controllerActivityProto == null ? ControllerActivityProto.getDefaultInstance() : controllerActivityProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setGlobalBluetoothController(ControllerActivityProto value) {
        if (value != null) {
            this.globalBluetoothController_ = value;
            this.bitField0_ |= 16;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setGlobalBluetoothController(ControllerActivityProto.Builder builderForValue) {
        this.globalBluetoothController_ = (ControllerActivityProto) builderForValue.build();
        this.bitField0_ |= 16;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeGlobalBluetoothController(ControllerActivityProto value) {
        ControllerActivityProto controllerActivityProto = this.globalBluetoothController_;
        if (controllerActivityProto == null || controllerActivityProto == ControllerActivityProto.getDefaultInstance()) {
            this.globalBluetoothController_ = value;
        } else {
            this.globalBluetoothController_ = (ControllerActivityProto) ((ControllerActivityProto.Builder) ControllerActivityProto.newBuilder(this.globalBluetoothController_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 16;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearGlobalBluetoothController() {
        this.globalBluetoothController_ = null;
        this.bitField0_ &= -17;
    }

    @Override // android.os.SystemProtoOrBuilder
    public boolean hasGlobalModemController() {
        return (this.bitField0_ & 32) == 32;
    }

    @Override // android.os.SystemProtoOrBuilder
    public ControllerActivityProto getGlobalModemController() {
        ControllerActivityProto controllerActivityProto = this.globalModemController_;
        return controllerActivityProto == null ? ControllerActivityProto.getDefaultInstance() : controllerActivityProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setGlobalModemController(ControllerActivityProto value) {
        if (value != null) {
            this.globalModemController_ = value;
            this.bitField0_ |= 32;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setGlobalModemController(ControllerActivityProto.Builder builderForValue) {
        this.globalModemController_ = (ControllerActivityProto) builderForValue.build();
        this.bitField0_ |= 32;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeGlobalModemController(ControllerActivityProto value) {
        ControllerActivityProto controllerActivityProto = this.globalModemController_;
        if (controllerActivityProto == null || controllerActivityProto == ControllerActivityProto.getDefaultInstance()) {
            this.globalModemController_ = value;
        } else {
            this.globalModemController_ = (ControllerActivityProto) ((ControllerActivityProto.Builder) ControllerActivityProto.newBuilder(this.globalModemController_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 32;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearGlobalModemController() {
        this.globalModemController_ = null;
        this.bitField0_ &= -33;
    }

    @Override // android.os.SystemProtoOrBuilder
    public boolean hasGlobalWifiController() {
        return (this.bitField0_ & 64) == 64;
    }

    @Override // android.os.SystemProtoOrBuilder
    public ControllerActivityProto getGlobalWifiController() {
        ControllerActivityProto controllerActivityProto = this.globalWifiController_;
        return controllerActivityProto == null ? ControllerActivityProto.getDefaultInstance() : controllerActivityProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setGlobalWifiController(ControllerActivityProto value) {
        if (value != null) {
            this.globalWifiController_ = value;
            this.bitField0_ |= 64;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setGlobalWifiController(ControllerActivityProto.Builder builderForValue) {
        this.globalWifiController_ = (ControllerActivityProto) builderForValue.build();
        this.bitField0_ |= 64;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeGlobalWifiController(ControllerActivityProto value) {
        ControllerActivityProto controllerActivityProto = this.globalWifiController_;
        if (controllerActivityProto == null || controllerActivityProto == ControllerActivityProto.getDefaultInstance()) {
            this.globalWifiController_ = value;
        } else {
            this.globalWifiController_ = (ControllerActivityProto) ((ControllerActivityProto.Builder) ControllerActivityProto.newBuilder(this.globalWifiController_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 64;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearGlobalWifiController() {
        this.globalWifiController_ = null;
        this.bitField0_ &= -65;
    }

    @Override // android.os.SystemProtoOrBuilder
    public boolean hasGlobalNetwork() {
        return (this.bitField0_ & 128) == 128;
    }

    @Override // android.os.SystemProtoOrBuilder
    public GlobalNetwork getGlobalNetwork() {
        GlobalNetwork globalNetwork = this.globalNetwork_;
        return globalNetwork == null ? GlobalNetwork.getDefaultInstance() : globalNetwork;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setGlobalNetwork(GlobalNetwork value) {
        if (value != null) {
            this.globalNetwork_ = value;
            this.bitField0_ |= 128;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setGlobalNetwork(GlobalNetwork.Builder builderForValue) {
        this.globalNetwork_ = (GlobalNetwork) builderForValue.build();
        this.bitField0_ |= 128;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeGlobalNetwork(GlobalNetwork value) {
        GlobalNetwork globalNetwork = this.globalNetwork_;
        if (globalNetwork == null || globalNetwork == GlobalNetwork.getDefaultInstance()) {
            this.globalNetwork_ = value;
        } else {
            this.globalNetwork_ = (GlobalNetwork) ((GlobalNetwork.Builder) GlobalNetwork.newBuilder(this.globalNetwork_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 128;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearGlobalNetwork() {
        this.globalNetwork_ = null;
        this.bitField0_ &= -129;
    }

    @Override // android.os.SystemProtoOrBuilder
    public boolean hasGlobalWifi() {
        return (this.bitField0_ & 256) == 256;
    }

    @Override // android.os.SystemProtoOrBuilder
    public GlobalWifi getGlobalWifi() {
        GlobalWifi globalWifi = this.globalWifi_;
        return globalWifi == null ? GlobalWifi.getDefaultInstance() : globalWifi;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setGlobalWifi(GlobalWifi value) {
        if (value != null) {
            this.globalWifi_ = value;
            this.bitField0_ |= 256;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setGlobalWifi(GlobalWifi.Builder builderForValue) {
        this.globalWifi_ = (GlobalWifi) builderForValue.build();
        this.bitField0_ |= 256;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeGlobalWifi(GlobalWifi value) {
        GlobalWifi globalWifi = this.globalWifi_;
        if (globalWifi == null || globalWifi == GlobalWifi.getDefaultInstance()) {
            this.globalWifi_ = value;
        } else {
            this.globalWifi_ = (GlobalWifi) ((GlobalWifi.Builder) GlobalWifi.newBuilder(this.globalWifi_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 256;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearGlobalWifi() {
        this.globalWifi_ = null;
        this.bitField0_ &= -257;
    }

    @Override // android.os.SystemProtoOrBuilder
    public List<KernelWakelock> getKernelWakelockList() {
        return this.kernelWakelock_;
    }

    public List<? extends KernelWakelockOrBuilder> getKernelWakelockOrBuilderList() {
        return this.kernelWakelock_;
    }

    @Override // android.os.SystemProtoOrBuilder
    public int getKernelWakelockCount() {
        return this.kernelWakelock_.size();
    }

    @Override // android.os.SystemProtoOrBuilder
    public KernelWakelock getKernelWakelock(int index) {
        return this.kernelWakelock_.get(index);
    }

    public KernelWakelockOrBuilder getKernelWakelockOrBuilder(int index) {
        return this.kernelWakelock_.get(index);
    }

    private void ensureKernelWakelockIsMutable() {
        if (!this.kernelWakelock_.isModifiable()) {
            this.kernelWakelock_ = GeneratedMessageLite.mutableCopy(this.kernelWakelock_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setKernelWakelock(int index, KernelWakelock value) {
        if (value != null) {
            ensureKernelWakelockIsMutable();
            this.kernelWakelock_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setKernelWakelock(int index, KernelWakelock.Builder builderForValue) {
        ensureKernelWakelockIsMutable();
        this.kernelWakelock_.set(index, (KernelWakelock) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addKernelWakelock(KernelWakelock value) {
        if (value != null) {
            ensureKernelWakelockIsMutable();
            this.kernelWakelock_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addKernelWakelock(int index, KernelWakelock value) {
        if (value != null) {
            ensureKernelWakelockIsMutable();
            this.kernelWakelock_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addKernelWakelock(KernelWakelock.Builder builderForValue) {
        ensureKernelWakelockIsMutable();
        this.kernelWakelock_.add((KernelWakelock) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addKernelWakelock(int index, KernelWakelock.Builder builderForValue) {
        ensureKernelWakelockIsMutable();
        this.kernelWakelock_.add(index, (KernelWakelock) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllKernelWakelock(Iterable<? extends KernelWakelock> values) {
        ensureKernelWakelockIsMutable();
        AbstractMessageLite.addAll(values, this.kernelWakelock_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearKernelWakelock() {
        this.kernelWakelock_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeKernelWakelock(int index) {
        ensureKernelWakelockIsMutable();
        this.kernelWakelock_.remove(index);
    }

    @Override // android.os.SystemProtoOrBuilder
    public boolean hasMisc() {
        return (this.bitField0_ & 512) == 512;
    }

    @Override // android.os.SystemProtoOrBuilder
    public Misc getMisc() {
        Misc misc = this.misc_;
        return misc == null ? Misc.getDefaultInstance() : misc;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setMisc(Misc value) {
        if (value != null) {
            this.misc_ = value;
            this.bitField0_ |= 512;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setMisc(Misc.Builder builderForValue) {
        this.misc_ = (Misc) builderForValue.build();
        this.bitField0_ |= 512;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeMisc(Misc value) {
        Misc misc = this.misc_;
        if (misc == null || misc == Misc.getDefaultInstance()) {
            this.misc_ = value;
        } else {
            this.misc_ = (Misc) ((Misc.Builder) Misc.newBuilder(this.misc_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 512;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearMisc() {
        this.misc_ = null;
        this.bitField0_ &= -513;
    }

    @Override // android.os.SystemProtoOrBuilder
    public List<PhoneSignalStrength> getPhoneSignalStrengthList() {
        return this.phoneSignalStrength_;
    }

    public List<? extends PhoneSignalStrengthOrBuilder> getPhoneSignalStrengthOrBuilderList() {
        return this.phoneSignalStrength_;
    }

    @Override // android.os.SystemProtoOrBuilder
    public int getPhoneSignalStrengthCount() {
        return this.phoneSignalStrength_.size();
    }

    @Override // android.os.SystemProtoOrBuilder
    public PhoneSignalStrength getPhoneSignalStrength(int index) {
        return this.phoneSignalStrength_.get(index);
    }

    public PhoneSignalStrengthOrBuilder getPhoneSignalStrengthOrBuilder(int index) {
        return this.phoneSignalStrength_.get(index);
    }

    private void ensurePhoneSignalStrengthIsMutable() {
        if (!this.phoneSignalStrength_.isModifiable()) {
            this.phoneSignalStrength_ = GeneratedMessageLite.mutableCopy(this.phoneSignalStrength_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPhoneSignalStrength(int index, PhoneSignalStrength value) {
        if (value != null) {
            ensurePhoneSignalStrengthIsMutable();
            this.phoneSignalStrength_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPhoneSignalStrength(int index, PhoneSignalStrength.Builder builderForValue) {
        ensurePhoneSignalStrengthIsMutable();
        this.phoneSignalStrength_.set(index, (PhoneSignalStrength) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addPhoneSignalStrength(PhoneSignalStrength value) {
        if (value != null) {
            ensurePhoneSignalStrengthIsMutable();
            this.phoneSignalStrength_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addPhoneSignalStrength(int index, PhoneSignalStrength value) {
        if (value != null) {
            ensurePhoneSignalStrengthIsMutable();
            this.phoneSignalStrength_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addPhoneSignalStrength(PhoneSignalStrength.Builder builderForValue) {
        ensurePhoneSignalStrengthIsMutable();
        this.phoneSignalStrength_.add((PhoneSignalStrength) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addPhoneSignalStrength(int index, PhoneSignalStrength.Builder builderForValue) {
        ensurePhoneSignalStrengthIsMutable();
        this.phoneSignalStrength_.add(index, (PhoneSignalStrength) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllPhoneSignalStrength(Iterable<? extends PhoneSignalStrength> values) {
        ensurePhoneSignalStrengthIsMutable();
        AbstractMessageLite.addAll(values, this.phoneSignalStrength_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearPhoneSignalStrength() {
        this.phoneSignalStrength_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removePhoneSignalStrength(int index) {
        ensurePhoneSignalStrengthIsMutable();
        this.phoneSignalStrength_.remove(index);
    }

    @Override // android.os.SystemProtoOrBuilder
    public List<PowerUseItem> getPowerUseItemList() {
        return this.powerUseItem_;
    }

    public List<? extends PowerUseItemOrBuilder> getPowerUseItemOrBuilderList() {
        return this.powerUseItem_;
    }

    @Override // android.os.SystemProtoOrBuilder
    public int getPowerUseItemCount() {
        return this.powerUseItem_.size();
    }

    @Override // android.os.SystemProtoOrBuilder
    public PowerUseItem getPowerUseItem(int index) {
        return this.powerUseItem_.get(index);
    }

    public PowerUseItemOrBuilder getPowerUseItemOrBuilder(int index) {
        return this.powerUseItem_.get(index);
    }

    private void ensurePowerUseItemIsMutable() {
        if (!this.powerUseItem_.isModifiable()) {
            this.powerUseItem_ = GeneratedMessageLite.mutableCopy(this.powerUseItem_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPowerUseItem(int index, PowerUseItem value) {
        if (value != null) {
            ensurePowerUseItemIsMutable();
            this.powerUseItem_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPowerUseItem(int index, PowerUseItem.Builder builderForValue) {
        ensurePowerUseItemIsMutable();
        this.powerUseItem_.set(index, (PowerUseItem) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addPowerUseItem(PowerUseItem value) {
        if (value != null) {
            ensurePowerUseItemIsMutable();
            this.powerUseItem_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addPowerUseItem(int index, PowerUseItem value) {
        if (value != null) {
            ensurePowerUseItemIsMutable();
            this.powerUseItem_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addPowerUseItem(PowerUseItem.Builder builderForValue) {
        ensurePowerUseItemIsMutable();
        this.powerUseItem_.add((PowerUseItem) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addPowerUseItem(int index, PowerUseItem.Builder builderForValue) {
        ensurePowerUseItemIsMutable();
        this.powerUseItem_.add(index, (PowerUseItem) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllPowerUseItem(Iterable<? extends PowerUseItem> values) {
        ensurePowerUseItemIsMutable();
        AbstractMessageLite.addAll(values, this.powerUseItem_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearPowerUseItem() {
        this.powerUseItem_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removePowerUseItem(int index) {
        ensurePowerUseItemIsMutable();
        this.powerUseItem_.remove(index);
    }

    @Override // android.os.SystemProtoOrBuilder
    public boolean hasPowerUseSummary() {
        return (this.bitField0_ & 1024) == 1024;
    }

    @Override // android.os.SystemProtoOrBuilder
    public PowerUseSummary getPowerUseSummary() {
        PowerUseSummary powerUseSummary = this.powerUseSummary_;
        return powerUseSummary == null ? PowerUseSummary.getDefaultInstance() : powerUseSummary;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPowerUseSummary(PowerUseSummary value) {
        if (value != null) {
            this.powerUseSummary_ = value;
            this.bitField0_ |= 1024;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPowerUseSummary(PowerUseSummary.Builder builderForValue) {
        this.powerUseSummary_ = (PowerUseSummary) builderForValue.build();
        this.bitField0_ |= 1024;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergePowerUseSummary(PowerUseSummary value) {
        PowerUseSummary powerUseSummary = this.powerUseSummary_;
        if (powerUseSummary == null || powerUseSummary == PowerUseSummary.getDefaultInstance()) {
            this.powerUseSummary_ = value;
        } else {
            this.powerUseSummary_ = (PowerUseSummary) ((PowerUseSummary.Builder) PowerUseSummary.newBuilder(this.powerUseSummary_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 1024;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearPowerUseSummary() {
        this.powerUseSummary_ = null;
        this.bitField0_ &= -1025;
    }

    @Override // android.os.SystemProtoOrBuilder
    public List<ResourcePowerManager> getResourcePowerManagerList() {
        return this.resourcePowerManager_;
    }

    public List<? extends ResourcePowerManagerOrBuilder> getResourcePowerManagerOrBuilderList() {
        return this.resourcePowerManager_;
    }

    @Override // android.os.SystemProtoOrBuilder
    public int getResourcePowerManagerCount() {
        return this.resourcePowerManager_.size();
    }

    @Override // android.os.SystemProtoOrBuilder
    public ResourcePowerManager getResourcePowerManager(int index) {
        return this.resourcePowerManager_.get(index);
    }

    public ResourcePowerManagerOrBuilder getResourcePowerManagerOrBuilder(int index) {
        return this.resourcePowerManager_.get(index);
    }

    private void ensureResourcePowerManagerIsMutable() {
        if (!this.resourcePowerManager_.isModifiable()) {
            this.resourcePowerManager_ = GeneratedMessageLite.mutableCopy(this.resourcePowerManager_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setResourcePowerManager(int index, ResourcePowerManager value) {
        if (value != null) {
            ensureResourcePowerManagerIsMutable();
            this.resourcePowerManager_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setResourcePowerManager(int index, ResourcePowerManager.Builder builderForValue) {
        ensureResourcePowerManagerIsMutable();
        this.resourcePowerManager_.set(index, (ResourcePowerManager) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addResourcePowerManager(ResourcePowerManager value) {
        if (value != null) {
            ensureResourcePowerManagerIsMutable();
            this.resourcePowerManager_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addResourcePowerManager(int index, ResourcePowerManager value) {
        if (value != null) {
            ensureResourcePowerManagerIsMutable();
            this.resourcePowerManager_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addResourcePowerManager(ResourcePowerManager.Builder builderForValue) {
        ensureResourcePowerManagerIsMutable();
        this.resourcePowerManager_.add((ResourcePowerManager) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addResourcePowerManager(int index, ResourcePowerManager.Builder builderForValue) {
        ensureResourcePowerManagerIsMutable();
        this.resourcePowerManager_.add(index, (ResourcePowerManager) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllResourcePowerManager(Iterable<? extends ResourcePowerManager> values) {
        ensureResourcePowerManagerIsMutable();
        AbstractMessageLite.addAll(values, this.resourcePowerManager_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearResourcePowerManager() {
        this.resourcePowerManager_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeResourcePowerManager(int index) {
        ensureResourcePowerManagerIsMutable();
        this.resourcePowerManager_.remove(index);
    }

    @Override // android.os.SystemProtoOrBuilder
    public List<ScreenBrightness> getScreenBrightnessList() {
        return this.screenBrightness_;
    }

    public List<? extends ScreenBrightnessOrBuilder> getScreenBrightnessOrBuilderList() {
        return this.screenBrightness_;
    }

    @Override // android.os.SystemProtoOrBuilder
    public int getScreenBrightnessCount() {
        return this.screenBrightness_.size();
    }

    @Override // android.os.SystemProtoOrBuilder
    public ScreenBrightness getScreenBrightness(int index) {
        return this.screenBrightness_.get(index);
    }

    public ScreenBrightnessOrBuilder getScreenBrightnessOrBuilder(int index) {
        return this.screenBrightness_.get(index);
    }

    private void ensureScreenBrightnessIsMutable() {
        if (!this.screenBrightness_.isModifiable()) {
            this.screenBrightness_ = GeneratedMessageLite.mutableCopy(this.screenBrightness_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setScreenBrightness(int index, ScreenBrightness value) {
        if (value != null) {
            ensureScreenBrightnessIsMutable();
            this.screenBrightness_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setScreenBrightness(int index, ScreenBrightness.Builder builderForValue) {
        ensureScreenBrightnessIsMutable();
        this.screenBrightness_.set(index, (ScreenBrightness) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addScreenBrightness(ScreenBrightness value) {
        if (value != null) {
            ensureScreenBrightnessIsMutable();
            this.screenBrightness_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addScreenBrightness(int index, ScreenBrightness value) {
        if (value != null) {
            ensureScreenBrightnessIsMutable();
            this.screenBrightness_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addScreenBrightness(ScreenBrightness.Builder builderForValue) {
        ensureScreenBrightnessIsMutable();
        this.screenBrightness_.add((ScreenBrightness) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addScreenBrightness(int index, ScreenBrightness.Builder builderForValue) {
        ensureScreenBrightnessIsMutable();
        this.screenBrightness_.add(index, (ScreenBrightness) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllScreenBrightness(Iterable<? extends ScreenBrightness> values) {
        ensureScreenBrightnessIsMutable();
        AbstractMessageLite.addAll(values, this.screenBrightness_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearScreenBrightness() {
        this.screenBrightness_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeScreenBrightness(int index) {
        ensureScreenBrightnessIsMutable();
        this.screenBrightness_.remove(index);
    }

    @Override // android.os.SystemProtoOrBuilder
    public boolean hasSignalScanning() {
        return (this.bitField0_ & 2048) == 2048;
    }

    @Override // android.os.SystemProtoOrBuilder
    public TimerProto getSignalScanning() {
        TimerProto timerProto = this.signalScanning_;
        return timerProto == null ? TimerProto.getDefaultInstance() : timerProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setSignalScanning(TimerProto value) {
        if (value != null) {
            this.signalScanning_ = value;
            this.bitField0_ |= 2048;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setSignalScanning(TimerProto.Builder builderForValue) {
        this.signalScanning_ = (TimerProto) builderForValue.build();
        this.bitField0_ |= 2048;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeSignalScanning(TimerProto value) {
        TimerProto timerProto = this.signalScanning_;
        if (timerProto == null || timerProto == TimerProto.getDefaultInstance()) {
            this.signalScanning_ = value;
        } else {
            this.signalScanning_ = (TimerProto) ((TimerProto.Builder) TimerProto.newBuilder(this.signalScanning_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 2048;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearSignalScanning() {
        this.signalScanning_ = null;
        this.bitField0_ &= -2049;
    }

    @Override // android.os.SystemProtoOrBuilder
    public List<WakeupReason> getWakeupReasonList() {
        return this.wakeupReason_;
    }

    public List<? extends WakeupReasonOrBuilder> getWakeupReasonOrBuilderList() {
        return this.wakeupReason_;
    }

    @Override // android.os.SystemProtoOrBuilder
    public int getWakeupReasonCount() {
        return this.wakeupReason_.size();
    }

    @Override // android.os.SystemProtoOrBuilder
    public WakeupReason getWakeupReason(int index) {
        return this.wakeupReason_.get(index);
    }

    public WakeupReasonOrBuilder getWakeupReasonOrBuilder(int index) {
        return this.wakeupReason_.get(index);
    }

    private void ensureWakeupReasonIsMutable() {
        if (!this.wakeupReason_.isModifiable()) {
            this.wakeupReason_ = GeneratedMessageLite.mutableCopy(this.wakeupReason_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setWakeupReason(int index, WakeupReason value) {
        if (value != null) {
            ensureWakeupReasonIsMutable();
            this.wakeupReason_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setWakeupReason(int index, WakeupReason.Builder builderForValue) {
        ensureWakeupReasonIsMutable();
        this.wakeupReason_.set(index, (WakeupReason) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addWakeupReason(WakeupReason value) {
        if (value != null) {
            ensureWakeupReasonIsMutable();
            this.wakeupReason_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addWakeupReason(int index, WakeupReason value) {
        if (value != null) {
            ensureWakeupReasonIsMutable();
            this.wakeupReason_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addWakeupReason(WakeupReason.Builder builderForValue) {
        ensureWakeupReasonIsMutable();
        this.wakeupReason_.add((WakeupReason) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addWakeupReason(int index, WakeupReason.Builder builderForValue) {
        ensureWakeupReasonIsMutable();
        this.wakeupReason_.add(index, (WakeupReason) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllWakeupReason(Iterable<? extends WakeupReason> values) {
        ensureWakeupReasonIsMutable();
        AbstractMessageLite.addAll(values, this.wakeupReason_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearWakeupReason() {
        this.wakeupReason_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeWakeupReason(int index) {
        ensureWakeupReasonIsMutable();
        this.wakeupReason_.remove(index);
    }

    @Override // android.os.SystemProtoOrBuilder
    public boolean hasWifiMulticastWakelockTotal() {
        return (this.bitField0_ & 4096) == 4096;
    }

    @Override // android.os.SystemProtoOrBuilder
    public WifiMulticastWakelockTotal getWifiMulticastWakelockTotal() {
        WifiMulticastWakelockTotal wifiMulticastWakelockTotal = this.wifiMulticastWakelockTotal_;
        return wifiMulticastWakelockTotal == null ? WifiMulticastWakelockTotal.getDefaultInstance() : wifiMulticastWakelockTotal;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setWifiMulticastWakelockTotal(WifiMulticastWakelockTotal value) {
        if (value != null) {
            this.wifiMulticastWakelockTotal_ = value;
            this.bitField0_ |= 4096;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setWifiMulticastWakelockTotal(WifiMulticastWakelockTotal.Builder builderForValue) {
        this.wifiMulticastWakelockTotal_ = (WifiMulticastWakelockTotal) builderForValue.build();
        this.bitField0_ |= 4096;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeWifiMulticastWakelockTotal(WifiMulticastWakelockTotal value) {
        WifiMulticastWakelockTotal wifiMulticastWakelockTotal = this.wifiMulticastWakelockTotal_;
        if (wifiMulticastWakelockTotal == null || wifiMulticastWakelockTotal == WifiMulticastWakelockTotal.getDefaultInstance()) {
            this.wifiMulticastWakelockTotal_ = value;
        } else {
            this.wifiMulticastWakelockTotal_ = (WifiMulticastWakelockTotal) ((WifiMulticastWakelockTotal.Builder) WifiMulticastWakelockTotal.newBuilder(this.wifiMulticastWakelockTotal_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 4096;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearWifiMulticastWakelockTotal() {
        this.wifiMulticastWakelockTotal_ = null;
        this.bitField0_ &= -4097;
    }

    @Override // android.os.SystemProtoOrBuilder
    public List<WifiSignalStrength> getWifiSignalStrengthList() {
        return this.wifiSignalStrength_;
    }

    public List<? extends WifiSignalStrengthOrBuilder> getWifiSignalStrengthOrBuilderList() {
        return this.wifiSignalStrength_;
    }

    @Override // android.os.SystemProtoOrBuilder
    public int getWifiSignalStrengthCount() {
        return this.wifiSignalStrength_.size();
    }

    @Override // android.os.SystemProtoOrBuilder
    public WifiSignalStrength getWifiSignalStrength(int index) {
        return this.wifiSignalStrength_.get(index);
    }

    public WifiSignalStrengthOrBuilder getWifiSignalStrengthOrBuilder(int index) {
        return this.wifiSignalStrength_.get(index);
    }

    private void ensureWifiSignalStrengthIsMutable() {
        if (!this.wifiSignalStrength_.isModifiable()) {
            this.wifiSignalStrength_ = GeneratedMessageLite.mutableCopy(this.wifiSignalStrength_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setWifiSignalStrength(int index, WifiSignalStrength value) {
        if (value != null) {
            ensureWifiSignalStrengthIsMutable();
            this.wifiSignalStrength_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setWifiSignalStrength(int index, WifiSignalStrength.Builder builderForValue) {
        ensureWifiSignalStrengthIsMutable();
        this.wifiSignalStrength_.set(index, (WifiSignalStrength) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addWifiSignalStrength(WifiSignalStrength value) {
        if (value != null) {
            ensureWifiSignalStrengthIsMutable();
            this.wifiSignalStrength_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addWifiSignalStrength(int index, WifiSignalStrength value) {
        if (value != null) {
            ensureWifiSignalStrengthIsMutable();
            this.wifiSignalStrength_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addWifiSignalStrength(WifiSignalStrength.Builder builderForValue) {
        ensureWifiSignalStrengthIsMutable();
        this.wifiSignalStrength_.add((WifiSignalStrength) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addWifiSignalStrength(int index, WifiSignalStrength.Builder builderForValue) {
        ensureWifiSignalStrengthIsMutable();
        this.wifiSignalStrength_.add(index, (WifiSignalStrength) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllWifiSignalStrength(Iterable<? extends WifiSignalStrength> values) {
        ensureWifiSignalStrengthIsMutable();
        AbstractMessageLite.addAll(values, this.wifiSignalStrength_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearWifiSignalStrength() {
        this.wifiSignalStrength_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeWifiSignalStrength(int index) {
        ensureWifiSignalStrengthIsMutable();
        this.wifiSignalStrength_.remove(index);
    }

    @Override // android.os.SystemProtoOrBuilder
    public List<WifiState> getWifiStateList() {
        return this.wifiState_;
    }

    public List<? extends WifiStateOrBuilder> getWifiStateOrBuilderList() {
        return this.wifiState_;
    }

    @Override // android.os.SystemProtoOrBuilder
    public int getWifiStateCount() {
        return this.wifiState_.size();
    }

    @Override // android.os.SystemProtoOrBuilder
    public WifiState getWifiState(int index) {
        return this.wifiState_.get(index);
    }

    public WifiStateOrBuilder getWifiStateOrBuilder(int index) {
        return this.wifiState_.get(index);
    }

    private void ensureWifiStateIsMutable() {
        if (!this.wifiState_.isModifiable()) {
            this.wifiState_ = GeneratedMessageLite.mutableCopy(this.wifiState_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setWifiState(int index, WifiState value) {
        if (value != null) {
            ensureWifiStateIsMutable();
            this.wifiState_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setWifiState(int index, WifiState.Builder builderForValue) {
        ensureWifiStateIsMutable();
        this.wifiState_.set(index, (WifiState) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addWifiState(WifiState value) {
        if (value != null) {
            ensureWifiStateIsMutable();
            this.wifiState_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addWifiState(int index, WifiState value) {
        if (value != null) {
            ensureWifiStateIsMutable();
            this.wifiState_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addWifiState(WifiState.Builder builderForValue) {
        ensureWifiStateIsMutable();
        this.wifiState_.add((WifiState) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addWifiState(int index, WifiState.Builder builderForValue) {
        ensureWifiStateIsMutable();
        this.wifiState_.add(index, (WifiState) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllWifiState(Iterable<? extends WifiState> values) {
        ensureWifiStateIsMutable();
        AbstractMessageLite.addAll(values, this.wifiState_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearWifiState() {
        this.wifiState_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeWifiState(int index) {
        ensureWifiStateIsMutable();
        this.wifiState_.remove(index);
    }

    @Override // android.os.SystemProtoOrBuilder
    public List<WifiSupplicantState> getWifiSupplicantStateList() {
        return this.wifiSupplicantState_;
    }

    public List<? extends WifiSupplicantStateOrBuilder> getWifiSupplicantStateOrBuilderList() {
        return this.wifiSupplicantState_;
    }

    @Override // android.os.SystemProtoOrBuilder
    public int getWifiSupplicantStateCount() {
        return this.wifiSupplicantState_.size();
    }

    @Override // android.os.SystemProtoOrBuilder
    public WifiSupplicantState getWifiSupplicantState(int index) {
        return this.wifiSupplicantState_.get(index);
    }

    public WifiSupplicantStateOrBuilder getWifiSupplicantStateOrBuilder(int index) {
        return this.wifiSupplicantState_.get(index);
    }

    private void ensureWifiSupplicantStateIsMutable() {
        if (!this.wifiSupplicantState_.isModifiable()) {
            this.wifiSupplicantState_ = GeneratedMessageLite.mutableCopy(this.wifiSupplicantState_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setWifiSupplicantState(int index, WifiSupplicantState value) {
        if (value != null) {
            ensureWifiSupplicantStateIsMutable();
            this.wifiSupplicantState_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setWifiSupplicantState(int index, WifiSupplicantState.Builder builderForValue) {
        ensureWifiSupplicantStateIsMutable();
        this.wifiSupplicantState_.set(index, (WifiSupplicantState) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addWifiSupplicantState(WifiSupplicantState value) {
        if (value != null) {
            ensureWifiSupplicantStateIsMutable();
            this.wifiSupplicantState_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addWifiSupplicantState(int index, WifiSupplicantState value) {
        if (value != null) {
            ensureWifiSupplicantStateIsMutable();
            this.wifiSupplicantState_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addWifiSupplicantState(WifiSupplicantState.Builder builderForValue) {
        ensureWifiSupplicantStateIsMutable();
        this.wifiSupplicantState_.add((WifiSupplicantState) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addWifiSupplicantState(int index, WifiSupplicantState.Builder builderForValue) {
        ensureWifiSupplicantStateIsMutable();
        this.wifiSupplicantState_.add(index, (WifiSupplicantState) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllWifiSupplicantState(Iterable<? extends WifiSupplicantState> values) {
        ensureWifiSupplicantStateIsMutable();
        AbstractMessageLite.addAll(values, this.wifiSupplicantState_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearWifiSupplicantState() {
        this.wifiSupplicantState_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeWifiSupplicantState(int index) {
        ensureWifiSupplicantStateIsMutable();
        this.wifiSupplicantState_.remove(index);
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeMessage(1, getBattery());
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeMessage(2, getBatteryDischarge());
        }
        if (this.timeRemainingCase_ == 3) {
            output.writeInt64(3, ((Long) this.timeRemaining_).longValue());
        }
        if (this.timeRemainingCase_ == 4) {
            output.writeInt64(4, ((Long) this.timeRemaining_).longValue());
        }
        for (int i = 0; i < this.chargeStep_.size(); i++) {
            output.writeMessage(5, this.chargeStep_.get(i));
        }
        for (int i2 = 0; i2 < this.dischargeStep_.size(); i2++) {
            output.writeMessage(6, this.dischargeStep_.get(i2));
        }
        for (int i3 = 0; i3 < this.cpuFrequency_.size(); i3++) {
            output.writeInt64(7, this.cpuFrequency_.getLong(i3));
        }
        for (int i4 = 0; i4 < this.dataConnection_.size(); i4++) {
            output.writeMessage(8, this.dataConnection_.get(i4));
        }
        if ((this.bitField0_ & 16) == 16) {
            output.writeMessage(9, getGlobalBluetoothController());
        }
        if ((this.bitField0_ & 32) == 32) {
            output.writeMessage(10, getGlobalModemController());
        }
        if ((this.bitField0_ & 64) == 64) {
            output.writeMessage(11, getGlobalWifiController());
        }
        if ((this.bitField0_ & 128) == 128) {
            output.writeMessage(12, getGlobalNetwork());
        }
        if ((this.bitField0_ & 256) == 256) {
            output.writeMessage(13, getGlobalWifi());
        }
        for (int i5 = 0; i5 < this.kernelWakelock_.size(); i5++) {
            output.writeMessage(14, this.kernelWakelock_.get(i5));
        }
        if ((this.bitField0_ & 512) == 512) {
            output.writeMessage(15, getMisc());
        }
        for (int i6 = 0; i6 < this.phoneSignalStrength_.size(); i6++) {
            output.writeMessage(16, this.phoneSignalStrength_.get(i6));
        }
        for (int i7 = 0; i7 < this.powerUseItem_.size(); i7++) {
            output.writeMessage(17, this.powerUseItem_.get(i7));
        }
        if ((this.bitField0_ & 1024) == 1024) {
            output.writeMessage(18, getPowerUseSummary());
        }
        for (int i8 = 0; i8 < this.resourcePowerManager_.size(); i8++) {
            output.writeMessage(19, this.resourcePowerManager_.get(i8));
        }
        for (int i9 = 0; i9 < this.screenBrightness_.size(); i9++) {
            output.writeMessage(20, this.screenBrightness_.get(i9));
        }
        if ((this.bitField0_ & 2048) == 2048) {
            output.writeMessage(21, getSignalScanning());
        }
        for (int i10 = 0; i10 < this.wakeupReason_.size(); i10++) {
            output.writeMessage(22, this.wakeupReason_.get(i10));
        }
        if ((this.bitField0_ & 4096) == 4096) {
            output.writeMessage(23, getWifiMulticastWakelockTotal());
        }
        for (int i11 = 0; i11 < this.wifiSignalStrength_.size(); i11++) {
            output.writeMessage(24, this.wifiSignalStrength_.get(i11));
        }
        for (int i12 = 0; i12 < this.wifiState_.size(); i12++) {
            output.writeMessage(25, this.wifiState_.get(i12));
        }
        for (int i13 = 0; i13 < this.wifiSupplicantState_.size(); i13++) {
            output.writeMessage(26, this.wifiSupplicantState_.get(i13));
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
            size2 = 0 + CodedOutputStream.computeMessageSize(1, getBattery());
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeMessageSize(2, getBatteryDischarge());
        }
        if (this.timeRemainingCase_ == 3) {
            size2 += CodedOutputStream.computeInt64Size(3, ((Long) this.timeRemaining_).longValue());
        }
        if (this.timeRemainingCase_ == 4) {
            size2 += CodedOutputStream.computeInt64Size(4, ((Long) this.timeRemaining_).longValue());
        }
        for (int i = 0; i < this.chargeStep_.size(); i++) {
            size2 += CodedOutputStream.computeMessageSize(5, this.chargeStep_.get(i));
        }
        for (int i2 = 0; i2 < this.dischargeStep_.size(); i2++) {
            size2 += CodedOutputStream.computeMessageSize(6, this.dischargeStep_.get(i2));
        }
        int dataSize = 0;
        for (int i3 = 0; i3 < this.cpuFrequency_.size(); i3++) {
            dataSize += CodedOutputStream.computeInt64SizeNoTag(this.cpuFrequency_.getLong(i3));
        }
        int size3 = size2 + dataSize + (getCpuFrequencyList().size() * 1);
        for (int i4 = 0; i4 < this.dataConnection_.size(); i4++) {
            size3 += CodedOutputStream.computeMessageSize(8, this.dataConnection_.get(i4));
        }
        if ((this.bitField0_ & 16) == 16) {
            size3 += CodedOutputStream.computeMessageSize(9, getGlobalBluetoothController());
        }
        if ((this.bitField0_ & 32) == 32) {
            size3 += CodedOutputStream.computeMessageSize(10, getGlobalModemController());
        }
        if ((this.bitField0_ & 64) == 64) {
            size3 += CodedOutputStream.computeMessageSize(11, getGlobalWifiController());
        }
        if ((this.bitField0_ & 128) == 128) {
            size3 += CodedOutputStream.computeMessageSize(12, getGlobalNetwork());
        }
        if ((this.bitField0_ & 256) == 256) {
            size3 += CodedOutputStream.computeMessageSize(13, getGlobalWifi());
        }
        for (int i5 = 0; i5 < this.kernelWakelock_.size(); i5++) {
            size3 += CodedOutputStream.computeMessageSize(14, this.kernelWakelock_.get(i5));
        }
        if ((this.bitField0_ & 512) == 512) {
            size3 += CodedOutputStream.computeMessageSize(15, getMisc());
        }
        for (int i6 = 0; i6 < this.phoneSignalStrength_.size(); i6++) {
            size3 += CodedOutputStream.computeMessageSize(16, this.phoneSignalStrength_.get(i6));
        }
        for (int i7 = 0; i7 < this.powerUseItem_.size(); i7++) {
            size3 += CodedOutputStream.computeMessageSize(17, this.powerUseItem_.get(i7));
        }
        if ((this.bitField0_ & 1024) == 1024) {
            size3 += CodedOutputStream.computeMessageSize(18, getPowerUseSummary());
        }
        for (int i8 = 0; i8 < this.resourcePowerManager_.size(); i8++) {
            size3 += CodedOutputStream.computeMessageSize(19, this.resourcePowerManager_.get(i8));
        }
        for (int i9 = 0; i9 < this.screenBrightness_.size(); i9++) {
            size3 += CodedOutputStream.computeMessageSize(20, this.screenBrightness_.get(i9));
        }
        if ((this.bitField0_ & 2048) == 2048) {
            size3 += CodedOutputStream.computeMessageSize(21, getSignalScanning());
        }
        for (int i10 = 0; i10 < this.wakeupReason_.size(); i10++) {
            size3 += CodedOutputStream.computeMessageSize(22, this.wakeupReason_.get(i10));
        }
        if ((this.bitField0_ & 4096) == 4096) {
            size3 += CodedOutputStream.computeMessageSize(23, getWifiMulticastWakelockTotal());
        }
        for (int i11 = 0; i11 < this.wifiSignalStrength_.size(); i11++) {
            size3 += CodedOutputStream.computeMessageSize(24, this.wifiSignalStrength_.get(i11));
        }
        for (int i12 = 0; i12 < this.wifiState_.size(); i12++) {
            size3 += CodedOutputStream.computeMessageSize(25, this.wifiState_.get(i12));
        }
        for (int i13 = 0; i13 < this.wifiSupplicantState_.size(); i13++) {
            size3 += CodedOutputStream.computeMessageSize(26, this.wifiSupplicantState_.get(i13));
        }
        int size4 = size3 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size4;
        return size4;
    }

    public static SystemProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (SystemProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static SystemProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (SystemProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static SystemProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (SystemProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static SystemProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (SystemProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static SystemProto parseFrom(InputStream input) throws IOException {
        return (SystemProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static SystemProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (SystemProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static SystemProto parseDelimitedFrom(InputStream input) throws IOException {
        return (SystemProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static SystemProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (SystemProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static SystemProto parseFrom(CodedInputStream input) throws IOException {
        return (SystemProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static SystemProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (SystemProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(SystemProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<SystemProto, Builder> implements SystemProtoOrBuilder {
        /* synthetic */ Builder(AnonymousClass1 x0) {
            this();
        }

        private Builder() {
            super(SystemProto.DEFAULT_INSTANCE);
        }

        @Override // android.os.SystemProtoOrBuilder
        public TimeRemainingCase getTimeRemainingCase() {
            return ((SystemProto) this.instance).getTimeRemainingCase();
        }

        public Builder clearTimeRemaining() {
            copyOnWrite();
            ((SystemProto) this.instance).clearTimeRemaining();
            return this;
        }

        @Override // android.os.SystemProtoOrBuilder
        public boolean hasBattery() {
            return ((SystemProto) this.instance).hasBattery();
        }

        @Override // android.os.SystemProtoOrBuilder
        public Battery getBattery() {
            return ((SystemProto) this.instance).getBattery();
        }

        public Builder setBattery(Battery value) {
            copyOnWrite();
            ((SystemProto) this.instance).setBattery((SystemProto) value);
            return this;
        }

        public Builder setBattery(Battery.Builder builderForValue) {
            copyOnWrite();
            ((SystemProto) this.instance).setBattery((SystemProto) builderForValue);
            return this;
        }

        public Builder mergeBattery(Battery value) {
            copyOnWrite();
            ((SystemProto) this.instance).mergeBattery(value);
            return this;
        }

        public Builder clearBattery() {
            copyOnWrite();
            ((SystemProto) this.instance).clearBattery();
            return this;
        }

        @Override // android.os.SystemProtoOrBuilder
        public boolean hasBatteryDischarge() {
            return ((SystemProto) this.instance).hasBatteryDischarge();
        }

        @Override // android.os.SystemProtoOrBuilder
        public BatteryDischarge getBatteryDischarge() {
            return ((SystemProto) this.instance).getBatteryDischarge();
        }

        public Builder setBatteryDischarge(BatteryDischarge value) {
            copyOnWrite();
            ((SystemProto) this.instance).setBatteryDischarge((SystemProto) value);
            return this;
        }

        public Builder setBatteryDischarge(BatteryDischarge.Builder builderForValue) {
            copyOnWrite();
            ((SystemProto) this.instance).setBatteryDischarge((SystemProto) builderForValue);
            return this;
        }

        public Builder mergeBatteryDischarge(BatteryDischarge value) {
            copyOnWrite();
            ((SystemProto) this.instance).mergeBatteryDischarge(value);
            return this;
        }

        public Builder clearBatteryDischarge() {
            copyOnWrite();
            ((SystemProto) this.instance).clearBatteryDischarge();
            return this;
        }

        @Override // android.os.SystemProtoOrBuilder
        public boolean hasChargeTimeRemainingMs() {
            return ((SystemProto) this.instance).hasChargeTimeRemainingMs();
        }

        @Override // android.os.SystemProtoOrBuilder
        public long getChargeTimeRemainingMs() {
            return ((SystemProto) this.instance).getChargeTimeRemainingMs();
        }

        public Builder setChargeTimeRemainingMs(long value) {
            copyOnWrite();
            ((SystemProto) this.instance).setChargeTimeRemainingMs(value);
            return this;
        }

        public Builder clearChargeTimeRemainingMs() {
            copyOnWrite();
            ((SystemProto) this.instance).clearChargeTimeRemainingMs();
            return this;
        }

        @Override // android.os.SystemProtoOrBuilder
        public boolean hasDischargeTimeRemainingMs() {
            return ((SystemProto) this.instance).hasDischargeTimeRemainingMs();
        }

        @Override // android.os.SystemProtoOrBuilder
        public long getDischargeTimeRemainingMs() {
            return ((SystemProto) this.instance).getDischargeTimeRemainingMs();
        }

        public Builder setDischargeTimeRemainingMs(long value) {
            copyOnWrite();
            ((SystemProto) this.instance).setDischargeTimeRemainingMs(value);
            return this;
        }

        public Builder clearDischargeTimeRemainingMs() {
            copyOnWrite();
            ((SystemProto) this.instance).clearDischargeTimeRemainingMs();
            return this;
        }

        @Override // android.os.SystemProtoOrBuilder
        public List<BatteryLevelStep> getChargeStepList() {
            return Collections.unmodifiableList(((SystemProto) this.instance).getChargeStepList());
        }

        @Override // android.os.SystemProtoOrBuilder
        public int getChargeStepCount() {
            return ((SystemProto) this.instance).getChargeStepCount();
        }

        @Override // android.os.SystemProtoOrBuilder
        public BatteryLevelStep getChargeStep(int index) {
            return ((SystemProto) this.instance).getChargeStep(index);
        }

        public Builder setChargeStep(int index, BatteryLevelStep value) {
            copyOnWrite();
            ((SystemProto) this.instance).setChargeStep((SystemProto) index, (int) value);
            return this;
        }

        public Builder setChargeStep(int index, BatteryLevelStep.Builder builderForValue) {
            copyOnWrite();
            ((SystemProto) this.instance).setChargeStep((SystemProto) index, (int) builderForValue);
            return this;
        }

        public Builder addChargeStep(BatteryLevelStep value) {
            copyOnWrite();
            ((SystemProto) this.instance).addChargeStep((SystemProto) value);
            return this;
        }

        public Builder addChargeStep(int index, BatteryLevelStep value) {
            copyOnWrite();
            ((SystemProto) this.instance).addChargeStep((SystemProto) index, (int) value);
            return this;
        }

        public Builder addChargeStep(BatteryLevelStep.Builder builderForValue) {
            copyOnWrite();
            ((SystemProto) this.instance).addChargeStep((SystemProto) builderForValue);
            return this;
        }

        public Builder addChargeStep(int index, BatteryLevelStep.Builder builderForValue) {
            copyOnWrite();
            ((SystemProto) this.instance).addChargeStep((SystemProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllChargeStep(Iterable<? extends BatteryLevelStep> values) {
            copyOnWrite();
            ((SystemProto) this.instance).addAllChargeStep(values);
            return this;
        }

        public Builder clearChargeStep() {
            copyOnWrite();
            ((SystemProto) this.instance).clearChargeStep();
            return this;
        }

        public Builder removeChargeStep(int index) {
            copyOnWrite();
            ((SystemProto) this.instance).removeChargeStep(index);
            return this;
        }

        @Override // android.os.SystemProtoOrBuilder
        public List<BatteryLevelStep> getDischargeStepList() {
            return Collections.unmodifiableList(((SystemProto) this.instance).getDischargeStepList());
        }

        @Override // android.os.SystemProtoOrBuilder
        public int getDischargeStepCount() {
            return ((SystemProto) this.instance).getDischargeStepCount();
        }

        @Override // android.os.SystemProtoOrBuilder
        public BatteryLevelStep getDischargeStep(int index) {
            return ((SystemProto) this.instance).getDischargeStep(index);
        }

        public Builder setDischargeStep(int index, BatteryLevelStep value) {
            copyOnWrite();
            ((SystemProto) this.instance).setDischargeStep((SystemProto) index, (int) value);
            return this;
        }

        public Builder setDischargeStep(int index, BatteryLevelStep.Builder builderForValue) {
            copyOnWrite();
            ((SystemProto) this.instance).setDischargeStep((SystemProto) index, (int) builderForValue);
            return this;
        }

        public Builder addDischargeStep(BatteryLevelStep value) {
            copyOnWrite();
            ((SystemProto) this.instance).addDischargeStep((SystemProto) value);
            return this;
        }

        public Builder addDischargeStep(int index, BatteryLevelStep value) {
            copyOnWrite();
            ((SystemProto) this.instance).addDischargeStep((SystemProto) index, (int) value);
            return this;
        }

        public Builder addDischargeStep(BatteryLevelStep.Builder builderForValue) {
            copyOnWrite();
            ((SystemProto) this.instance).addDischargeStep((SystemProto) builderForValue);
            return this;
        }

        public Builder addDischargeStep(int index, BatteryLevelStep.Builder builderForValue) {
            copyOnWrite();
            ((SystemProto) this.instance).addDischargeStep((SystemProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllDischargeStep(Iterable<? extends BatteryLevelStep> values) {
            copyOnWrite();
            ((SystemProto) this.instance).addAllDischargeStep(values);
            return this;
        }

        public Builder clearDischargeStep() {
            copyOnWrite();
            ((SystemProto) this.instance).clearDischargeStep();
            return this;
        }

        public Builder removeDischargeStep(int index) {
            copyOnWrite();
            ((SystemProto) this.instance).removeDischargeStep(index);
            return this;
        }

        @Override // android.os.SystemProtoOrBuilder
        public List<Long> getCpuFrequencyList() {
            return Collections.unmodifiableList(((SystemProto) this.instance).getCpuFrequencyList());
        }

        @Override // android.os.SystemProtoOrBuilder
        public int getCpuFrequencyCount() {
            return ((SystemProto) this.instance).getCpuFrequencyCount();
        }

        @Override // android.os.SystemProtoOrBuilder
        public long getCpuFrequency(int index) {
            return ((SystemProto) this.instance).getCpuFrequency(index);
        }

        public Builder setCpuFrequency(int index, long value) {
            copyOnWrite();
            ((SystemProto) this.instance).setCpuFrequency(index, value);
            return this;
        }

        public Builder addCpuFrequency(long value) {
            copyOnWrite();
            ((SystemProto) this.instance).addCpuFrequency(value);
            return this;
        }

        public Builder addAllCpuFrequency(Iterable<? extends Long> values) {
            copyOnWrite();
            ((SystemProto) this.instance).addAllCpuFrequency(values);
            return this;
        }

        public Builder clearCpuFrequency() {
            copyOnWrite();
            ((SystemProto) this.instance).clearCpuFrequency();
            return this;
        }

        @Override // android.os.SystemProtoOrBuilder
        public List<DataConnection> getDataConnectionList() {
            return Collections.unmodifiableList(((SystemProto) this.instance).getDataConnectionList());
        }

        @Override // android.os.SystemProtoOrBuilder
        public int getDataConnectionCount() {
            return ((SystemProto) this.instance).getDataConnectionCount();
        }

        @Override // android.os.SystemProtoOrBuilder
        public DataConnection getDataConnection(int index) {
            return ((SystemProto) this.instance).getDataConnection(index);
        }

        public Builder setDataConnection(int index, DataConnection value) {
            copyOnWrite();
            ((SystemProto) this.instance).setDataConnection((SystemProto) index, (int) value);
            return this;
        }

        public Builder setDataConnection(int index, DataConnection.Builder builderForValue) {
            copyOnWrite();
            ((SystemProto) this.instance).setDataConnection((SystemProto) index, (int) builderForValue);
            return this;
        }

        public Builder addDataConnection(DataConnection value) {
            copyOnWrite();
            ((SystemProto) this.instance).addDataConnection((SystemProto) value);
            return this;
        }

        public Builder addDataConnection(int index, DataConnection value) {
            copyOnWrite();
            ((SystemProto) this.instance).addDataConnection((SystemProto) index, (int) value);
            return this;
        }

        public Builder addDataConnection(DataConnection.Builder builderForValue) {
            copyOnWrite();
            ((SystemProto) this.instance).addDataConnection((SystemProto) builderForValue);
            return this;
        }

        public Builder addDataConnection(int index, DataConnection.Builder builderForValue) {
            copyOnWrite();
            ((SystemProto) this.instance).addDataConnection((SystemProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllDataConnection(Iterable<? extends DataConnection> values) {
            copyOnWrite();
            ((SystemProto) this.instance).addAllDataConnection(values);
            return this;
        }

        public Builder clearDataConnection() {
            copyOnWrite();
            ((SystemProto) this.instance).clearDataConnection();
            return this;
        }

        public Builder removeDataConnection(int index) {
            copyOnWrite();
            ((SystemProto) this.instance).removeDataConnection(index);
            return this;
        }

        @Override // android.os.SystemProtoOrBuilder
        public boolean hasGlobalBluetoothController() {
            return ((SystemProto) this.instance).hasGlobalBluetoothController();
        }

        @Override // android.os.SystemProtoOrBuilder
        public ControllerActivityProto getGlobalBluetoothController() {
            return ((SystemProto) this.instance).getGlobalBluetoothController();
        }

        public Builder setGlobalBluetoothController(ControllerActivityProto value) {
            copyOnWrite();
            ((SystemProto) this.instance).setGlobalBluetoothController((SystemProto) value);
            return this;
        }

        public Builder setGlobalBluetoothController(ControllerActivityProto.Builder builderForValue) {
            copyOnWrite();
            ((SystemProto) this.instance).setGlobalBluetoothController((SystemProto) builderForValue);
            return this;
        }

        public Builder mergeGlobalBluetoothController(ControllerActivityProto value) {
            copyOnWrite();
            ((SystemProto) this.instance).mergeGlobalBluetoothController(value);
            return this;
        }

        public Builder clearGlobalBluetoothController() {
            copyOnWrite();
            ((SystemProto) this.instance).clearGlobalBluetoothController();
            return this;
        }

        @Override // android.os.SystemProtoOrBuilder
        public boolean hasGlobalModemController() {
            return ((SystemProto) this.instance).hasGlobalModemController();
        }

        @Override // android.os.SystemProtoOrBuilder
        public ControllerActivityProto getGlobalModemController() {
            return ((SystemProto) this.instance).getGlobalModemController();
        }

        public Builder setGlobalModemController(ControllerActivityProto value) {
            copyOnWrite();
            ((SystemProto) this.instance).setGlobalModemController((SystemProto) value);
            return this;
        }

        public Builder setGlobalModemController(ControllerActivityProto.Builder builderForValue) {
            copyOnWrite();
            ((SystemProto) this.instance).setGlobalModemController((SystemProto) builderForValue);
            return this;
        }

        public Builder mergeGlobalModemController(ControllerActivityProto value) {
            copyOnWrite();
            ((SystemProto) this.instance).mergeGlobalModemController(value);
            return this;
        }

        public Builder clearGlobalModemController() {
            copyOnWrite();
            ((SystemProto) this.instance).clearGlobalModemController();
            return this;
        }

        @Override // android.os.SystemProtoOrBuilder
        public boolean hasGlobalWifiController() {
            return ((SystemProto) this.instance).hasGlobalWifiController();
        }

        @Override // android.os.SystemProtoOrBuilder
        public ControllerActivityProto getGlobalWifiController() {
            return ((SystemProto) this.instance).getGlobalWifiController();
        }

        public Builder setGlobalWifiController(ControllerActivityProto value) {
            copyOnWrite();
            ((SystemProto) this.instance).setGlobalWifiController((SystemProto) value);
            return this;
        }

        public Builder setGlobalWifiController(ControllerActivityProto.Builder builderForValue) {
            copyOnWrite();
            ((SystemProto) this.instance).setGlobalWifiController((SystemProto) builderForValue);
            return this;
        }

        public Builder mergeGlobalWifiController(ControllerActivityProto value) {
            copyOnWrite();
            ((SystemProto) this.instance).mergeGlobalWifiController(value);
            return this;
        }

        public Builder clearGlobalWifiController() {
            copyOnWrite();
            ((SystemProto) this.instance).clearGlobalWifiController();
            return this;
        }

        @Override // android.os.SystemProtoOrBuilder
        public boolean hasGlobalNetwork() {
            return ((SystemProto) this.instance).hasGlobalNetwork();
        }

        @Override // android.os.SystemProtoOrBuilder
        public GlobalNetwork getGlobalNetwork() {
            return ((SystemProto) this.instance).getGlobalNetwork();
        }

        public Builder setGlobalNetwork(GlobalNetwork value) {
            copyOnWrite();
            ((SystemProto) this.instance).setGlobalNetwork((SystemProto) value);
            return this;
        }

        public Builder setGlobalNetwork(GlobalNetwork.Builder builderForValue) {
            copyOnWrite();
            ((SystemProto) this.instance).setGlobalNetwork((SystemProto) builderForValue);
            return this;
        }

        public Builder mergeGlobalNetwork(GlobalNetwork value) {
            copyOnWrite();
            ((SystemProto) this.instance).mergeGlobalNetwork(value);
            return this;
        }

        public Builder clearGlobalNetwork() {
            copyOnWrite();
            ((SystemProto) this.instance).clearGlobalNetwork();
            return this;
        }

        @Override // android.os.SystemProtoOrBuilder
        public boolean hasGlobalWifi() {
            return ((SystemProto) this.instance).hasGlobalWifi();
        }

        @Override // android.os.SystemProtoOrBuilder
        public GlobalWifi getGlobalWifi() {
            return ((SystemProto) this.instance).getGlobalWifi();
        }

        public Builder setGlobalWifi(GlobalWifi value) {
            copyOnWrite();
            ((SystemProto) this.instance).setGlobalWifi((SystemProto) value);
            return this;
        }

        public Builder setGlobalWifi(GlobalWifi.Builder builderForValue) {
            copyOnWrite();
            ((SystemProto) this.instance).setGlobalWifi((SystemProto) builderForValue);
            return this;
        }

        public Builder mergeGlobalWifi(GlobalWifi value) {
            copyOnWrite();
            ((SystemProto) this.instance).mergeGlobalWifi(value);
            return this;
        }

        public Builder clearGlobalWifi() {
            copyOnWrite();
            ((SystemProto) this.instance).clearGlobalWifi();
            return this;
        }

        @Override // android.os.SystemProtoOrBuilder
        public List<KernelWakelock> getKernelWakelockList() {
            return Collections.unmodifiableList(((SystemProto) this.instance).getKernelWakelockList());
        }

        @Override // android.os.SystemProtoOrBuilder
        public int getKernelWakelockCount() {
            return ((SystemProto) this.instance).getKernelWakelockCount();
        }

        @Override // android.os.SystemProtoOrBuilder
        public KernelWakelock getKernelWakelock(int index) {
            return ((SystemProto) this.instance).getKernelWakelock(index);
        }

        public Builder setKernelWakelock(int index, KernelWakelock value) {
            copyOnWrite();
            ((SystemProto) this.instance).setKernelWakelock((SystemProto) index, (int) value);
            return this;
        }

        public Builder setKernelWakelock(int index, KernelWakelock.Builder builderForValue) {
            copyOnWrite();
            ((SystemProto) this.instance).setKernelWakelock((SystemProto) index, (int) builderForValue);
            return this;
        }

        public Builder addKernelWakelock(KernelWakelock value) {
            copyOnWrite();
            ((SystemProto) this.instance).addKernelWakelock((SystemProto) value);
            return this;
        }

        public Builder addKernelWakelock(int index, KernelWakelock value) {
            copyOnWrite();
            ((SystemProto) this.instance).addKernelWakelock((SystemProto) index, (int) value);
            return this;
        }

        public Builder addKernelWakelock(KernelWakelock.Builder builderForValue) {
            copyOnWrite();
            ((SystemProto) this.instance).addKernelWakelock((SystemProto) builderForValue);
            return this;
        }

        public Builder addKernelWakelock(int index, KernelWakelock.Builder builderForValue) {
            copyOnWrite();
            ((SystemProto) this.instance).addKernelWakelock((SystemProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllKernelWakelock(Iterable<? extends KernelWakelock> values) {
            copyOnWrite();
            ((SystemProto) this.instance).addAllKernelWakelock(values);
            return this;
        }

        public Builder clearKernelWakelock() {
            copyOnWrite();
            ((SystemProto) this.instance).clearKernelWakelock();
            return this;
        }

        public Builder removeKernelWakelock(int index) {
            copyOnWrite();
            ((SystemProto) this.instance).removeKernelWakelock(index);
            return this;
        }

        @Override // android.os.SystemProtoOrBuilder
        public boolean hasMisc() {
            return ((SystemProto) this.instance).hasMisc();
        }

        @Override // android.os.SystemProtoOrBuilder
        public Misc getMisc() {
            return ((SystemProto) this.instance).getMisc();
        }

        public Builder setMisc(Misc value) {
            copyOnWrite();
            ((SystemProto) this.instance).setMisc((SystemProto) value);
            return this;
        }

        public Builder setMisc(Misc.Builder builderForValue) {
            copyOnWrite();
            ((SystemProto) this.instance).setMisc((SystemProto) builderForValue);
            return this;
        }

        public Builder mergeMisc(Misc value) {
            copyOnWrite();
            ((SystemProto) this.instance).mergeMisc(value);
            return this;
        }

        public Builder clearMisc() {
            copyOnWrite();
            ((SystemProto) this.instance).clearMisc();
            return this;
        }

        @Override // android.os.SystemProtoOrBuilder
        public List<PhoneSignalStrength> getPhoneSignalStrengthList() {
            return Collections.unmodifiableList(((SystemProto) this.instance).getPhoneSignalStrengthList());
        }

        @Override // android.os.SystemProtoOrBuilder
        public int getPhoneSignalStrengthCount() {
            return ((SystemProto) this.instance).getPhoneSignalStrengthCount();
        }

        @Override // android.os.SystemProtoOrBuilder
        public PhoneSignalStrength getPhoneSignalStrength(int index) {
            return ((SystemProto) this.instance).getPhoneSignalStrength(index);
        }

        public Builder setPhoneSignalStrength(int index, PhoneSignalStrength value) {
            copyOnWrite();
            ((SystemProto) this.instance).setPhoneSignalStrength((SystemProto) index, (int) value);
            return this;
        }

        public Builder setPhoneSignalStrength(int index, PhoneSignalStrength.Builder builderForValue) {
            copyOnWrite();
            ((SystemProto) this.instance).setPhoneSignalStrength((SystemProto) index, (int) builderForValue);
            return this;
        }

        public Builder addPhoneSignalStrength(PhoneSignalStrength value) {
            copyOnWrite();
            ((SystemProto) this.instance).addPhoneSignalStrength((SystemProto) value);
            return this;
        }

        public Builder addPhoneSignalStrength(int index, PhoneSignalStrength value) {
            copyOnWrite();
            ((SystemProto) this.instance).addPhoneSignalStrength((SystemProto) index, (int) value);
            return this;
        }

        public Builder addPhoneSignalStrength(PhoneSignalStrength.Builder builderForValue) {
            copyOnWrite();
            ((SystemProto) this.instance).addPhoneSignalStrength((SystemProto) builderForValue);
            return this;
        }

        public Builder addPhoneSignalStrength(int index, PhoneSignalStrength.Builder builderForValue) {
            copyOnWrite();
            ((SystemProto) this.instance).addPhoneSignalStrength((SystemProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllPhoneSignalStrength(Iterable<? extends PhoneSignalStrength> values) {
            copyOnWrite();
            ((SystemProto) this.instance).addAllPhoneSignalStrength(values);
            return this;
        }

        public Builder clearPhoneSignalStrength() {
            copyOnWrite();
            ((SystemProto) this.instance).clearPhoneSignalStrength();
            return this;
        }

        public Builder removePhoneSignalStrength(int index) {
            copyOnWrite();
            ((SystemProto) this.instance).removePhoneSignalStrength(index);
            return this;
        }

        @Override // android.os.SystemProtoOrBuilder
        public List<PowerUseItem> getPowerUseItemList() {
            return Collections.unmodifiableList(((SystemProto) this.instance).getPowerUseItemList());
        }

        @Override // android.os.SystemProtoOrBuilder
        public int getPowerUseItemCount() {
            return ((SystemProto) this.instance).getPowerUseItemCount();
        }

        @Override // android.os.SystemProtoOrBuilder
        public PowerUseItem getPowerUseItem(int index) {
            return ((SystemProto) this.instance).getPowerUseItem(index);
        }

        public Builder setPowerUseItem(int index, PowerUseItem value) {
            copyOnWrite();
            ((SystemProto) this.instance).setPowerUseItem((SystemProto) index, (int) value);
            return this;
        }

        public Builder setPowerUseItem(int index, PowerUseItem.Builder builderForValue) {
            copyOnWrite();
            ((SystemProto) this.instance).setPowerUseItem((SystemProto) index, (int) builderForValue);
            return this;
        }

        public Builder addPowerUseItem(PowerUseItem value) {
            copyOnWrite();
            ((SystemProto) this.instance).addPowerUseItem((SystemProto) value);
            return this;
        }

        public Builder addPowerUseItem(int index, PowerUseItem value) {
            copyOnWrite();
            ((SystemProto) this.instance).addPowerUseItem((SystemProto) index, (int) value);
            return this;
        }

        public Builder addPowerUseItem(PowerUseItem.Builder builderForValue) {
            copyOnWrite();
            ((SystemProto) this.instance).addPowerUseItem((SystemProto) builderForValue);
            return this;
        }

        public Builder addPowerUseItem(int index, PowerUseItem.Builder builderForValue) {
            copyOnWrite();
            ((SystemProto) this.instance).addPowerUseItem((SystemProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllPowerUseItem(Iterable<? extends PowerUseItem> values) {
            copyOnWrite();
            ((SystemProto) this.instance).addAllPowerUseItem(values);
            return this;
        }

        public Builder clearPowerUseItem() {
            copyOnWrite();
            ((SystemProto) this.instance).clearPowerUseItem();
            return this;
        }

        public Builder removePowerUseItem(int index) {
            copyOnWrite();
            ((SystemProto) this.instance).removePowerUseItem(index);
            return this;
        }

        @Override // android.os.SystemProtoOrBuilder
        public boolean hasPowerUseSummary() {
            return ((SystemProto) this.instance).hasPowerUseSummary();
        }

        @Override // android.os.SystemProtoOrBuilder
        public PowerUseSummary getPowerUseSummary() {
            return ((SystemProto) this.instance).getPowerUseSummary();
        }

        public Builder setPowerUseSummary(PowerUseSummary value) {
            copyOnWrite();
            ((SystemProto) this.instance).setPowerUseSummary((SystemProto) value);
            return this;
        }

        public Builder setPowerUseSummary(PowerUseSummary.Builder builderForValue) {
            copyOnWrite();
            ((SystemProto) this.instance).setPowerUseSummary((SystemProto) builderForValue);
            return this;
        }

        public Builder mergePowerUseSummary(PowerUseSummary value) {
            copyOnWrite();
            ((SystemProto) this.instance).mergePowerUseSummary(value);
            return this;
        }

        public Builder clearPowerUseSummary() {
            copyOnWrite();
            ((SystemProto) this.instance).clearPowerUseSummary();
            return this;
        }

        @Override // android.os.SystemProtoOrBuilder
        public List<ResourcePowerManager> getResourcePowerManagerList() {
            return Collections.unmodifiableList(((SystemProto) this.instance).getResourcePowerManagerList());
        }

        @Override // android.os.SystemProtoOrBuilder
        public int getResourcePowerManagerCount() {
            return ((SystemProto) this.instance).getResourcePowerManagerCount();
        }

        @Override // android.os.SystemProtoOrBuilder
        public ResourcePowerManager getResourcePowerManager(int index) {
            return ((SystemProto) this.instance).getResourcePowerManager(index);
        }

        public Builder setResourcePowerManager(int index, ResourcePowerManager value) {
            copyOnWrite();
            ((SystemProto) this.instance).setResourcePowerManager((SystemProto) index, (int) value);
            return this;
        }

        public Builder setResourcePowerManager(int index, ResourcePowerManager.Builder builderForValue) {
            copyOnWrite();
            ((SystemProto) this.instance).setResourcePowerManager((SystemProto) index, (int) builderForValue);
            return this;
        }

        public Builder addResourcePowerManager(ResourcePowerManager value) {
            copyOnWrite();
            ((SystemProto) this.instance).addResourcePowerManager((SystemProto) value);
            return this;
        }

        public Builder addResourcePowerManager(int index, ResourcePowerManager value) {
            copyOnWrite();
            ((SystemProto) this.instance).addResourcePowerManager((SystemProto) index, (int) value);
            return this;
        }

        public Builder addResourcePowerManager(ResourcePowerManager.Builder builderForValue) {
            copyOnWrite();
            ((SystemProto) this.instance).addResourcePowerManager((SystemProto) builderForValue);
            return this;
        }

        public Builder addResourcePowerManager(int index, ResourcePowerManager.Builder builderForValue) {
            copyOnWrite();
            ((SystemProto) this.instance).addResourcePowerManager((SystemProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllResourcePowerManager(Iterable<? extends ResourcePowerManager> values) {
            copyOnWrite();
            ((SystemProto) this.instance).addAllResourcePowerManager(values);
            return this;
        }

        public Builder clearResourcePowerManager() {
            copyOnWrite();
            ((SystemProto) this.instance).clearResourcePowerManager();
            return this;
        }

        public Builder removeResourcePowerManager(int index) {
            copyOnWrite();
            ((SystemProto) this.instance).removeResourcePowerManager(index);
            return this;
        }

        @Override // android.os.SystemProtoOrBuilder
        public List<ScreenBrightness> getScreenBrightnessList() {
            return Collections.unmodifiableList(((SystemProto) this.instance).getScreenBrightnessList());
        }

        @Override // android.os.SystemProtoOrBuilder
        public int getScreenBrightnessCount() {
            return ((SystemProto) this.instance).getScreenBrightnessCount();
        }

        @Override // android.os.SystemProtoOrBuilder
        public ScreenBrightness getScreenBrightness(int index) {
            return ((SystemProto) this.instance).getScreenBrightness(index);
        }

        public Builder setScreenBrightness(int index, ScreenBrightness value) {
            copyOnWrite();
            ((SystemProto) this.instance).setScreenBrightness((SystemProto) index, (int) value);
            return this;
        }

        public Builder setScreenBrightness(int index, ScreenBrightness.Builder builderForValue) {
            copyOnWrite();
            ((SystemProto) this.instance).setScreenBrightness((SystemProto) index, (int) builderForValue);
            return this;
        }

        public Builder addScreenBrightness(ScreenBrightness value) {
            copyOnWrite();
            ((SystemProto) this.instance).addScreenBrightness((SystemProto) value);
            return this;
        }

        public Builder addScreenBrightness(int index, ScreenBrightness value) {
            copyOnWrite();
            ((SystemProto) this.instance).addScreenBrightness((SystemProto) index, (int) value);
            return this;
        }

        public Builder addScreenBrightness(ScreenBrightness.Builder builderForValue) {
            copyOnWrite();
            ((SystemProto) this.instance).addScreenBrightness((SystemProto) builderForValue);
            return this;
        }

        public Builder addScreenBrightness(int index, ScreenBrightness.Builder builderForValue) {
            copyOnWrite();
            ((SystemProto) this.instance).addScreenBrightness((SystemProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllScreenBrightness(Iterable<? extends ScreenBrightness> values) {
            copyOnWrite();
            ((SystemProto) this.instance).addAllScreenBrightness(values);
            return this;
        }

        public Builder clearScreenBrightness() {
            copyOnWrite();
            ((SystemProto) this.instance).clearScreenBrightness();
            return this;
        }

        public Builder removeScreenBrightness(int index) {
            copyOnWrite();
            ((SystemProto) this.instance).removeScreenBrightness(index);
            return this;
        }

        @Override // android.os.SystemProtoOrBuilder
        public boolean hasSignalScanning() {
            return ((SystemProto) this.instance).hasSignalScanning();
        }

        @Override // android.os.SystemProtoOrBuilder
        public TimerProto getSignalScanning() {
            return ((SystemProto) this.instance).getSignalScanning();
        }

        public Builder setSignalScanning(TimerProto value) {
            copyOnWrite();
            ((SystemProto) this.instance).setSignalScanning((SystemProto) value);
            return this;
        }

        public Builder setSignalScanning(TimerProto.Builder builderForValue) {
            copyOnWrite();
            ((SystemProto) this.instance).setSignalScanning((SystemProto) builderForValue);
            return this;
        }

        public Builder mergeSignalScanning(TimerProto value) {
            copyOnWrite();
            ((SystemProto) this.instance).mergeSignalScanning(value);
            return this;
        }

        public Builder clearSignalScanning() {
            copyOnWrite();
            ((SystemProto) this.instance).clearSignalScanning();
            return this;
        }

        @Override // android.os.SystemProtoOrBuilder
        public List<WakeupReason> getWakeupReasonList() {
            return Collections.unmodifiableList(((SystemProto) this.instance).getWakeupReasonList());
        }

        @Override // android.os.SystemProtoOrBuilder
        public int getWakeupReasonCount() {
            return ((SystemProto) this.instance).getWakeupReasonCount();
        }

        @Override // android.os.SystemProtoOrBuilder
        public WakeupReason getWakeupReason(int index) {
            return ((SystemProto) this.instance).getWakeupReason(index);
        }

        public Builder setWakeupReason(int index, WakeupReason value) {
            copyOnWrite();
            ((SystemProto) this.instance).setWakeupReason((SystemProto) index, (int) value);
            return this;
        }

        public Builder setWakeupReason(int index, WakeupReason.Builder builderForValue) {
            copyOnWrite();
            ((SystemProto) this.instance).setWakeupReason((SystemProto) index, (int) builderForValue);
            return this;
        }

        public Builder addWakeupReason(WakeupReason value) {
            copyOnWrite();
            ((SystemProto) this.instance).addWakeupReason((SystemProto) value);
            return this;
        }

        public Builder addWakeupReason(int index, WakeupReason value) {
            copyOnWrite();
            ((SystemProto) this.instance).addWakeupReason((SystemProto) index, (int) value);
            return this;
        }

        public Builder addWakeupReason(WakeupReason.Builder builderForValue) {
            copyOnWrite();
            ((SystemProto) this.instance).addWakeupReason((SystemProto) builderForValue);
            return this;
        }

        public Builder addWakeupReason(int index, WakeupReason.Builder builderForValue) {
            copyOnWrite();
            ((SystemProto) this.instance).addWakeupReason((SystemProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllWakeupReason(Iterable<? extends WakeupReason> values) {
            copyOnWrite();
            ((SystemProto) this.instance).addAllWakeupReason(values);
            return this;
        }

        public Builder clearWakeupReason() {
            copyOnWrite();
            ((SystemProto) this.instance).clearWakeupReason();
            return this;
        }

        public Builder removeWakeupReason(int index) {
            copyOnWrite();
            ((SystemProto) this.instance).removeWakeupReason(index);
            return this;
        }

        @Override // android.os.SystemProtoOrBuilder
        public boolean hasWifiMulticastWakelockTotal() {
            return ((SystemProto) this.instance).hasWifiMulticastWakelockTotal();
        }

        @Override // android.os.SystemProtoOrBuilder
        public WifiMulticastWakelockTotal getWifiMulticastWakelockTotal() {
            return ((SystemProto) this.instance).getWifiMulticastWakelockTotal();
        }

        public Builder setWifiMulticastWakelockTotal(WifiMulticastWakelockTotal value) {
            copyOnWrite();
            ((SystemProto) this.instance).setWifiMulticastWakelockTotal((SystemProto) value);
            return this;
        }

        public Builder setWifiMulticastWakelockTotal(WifiMulticastWakelockTotal.Builder builderForValue) {
            copyOnWrite();
            ((SystemProto) this.instance).setWifiMulticastWakelockTotal((SystemProto) builderForValue);
            return this;
        }

        public Builder mergeWifiMulticastWakelockTotal(WifiMulticastWakelockTotal value) {
            copyOnWrite();
            ((SystemProto) this.instance).mergeWifiMulticastWakelockTotal(value);
            return this;
        }

        public Builder clearWifiMulticastWakelockTotal() {
            copyOnWrite();
            ((SystemProto) this.instance).clearWifiMulticastWakelockTotal();
            return this;
        }

        @Override // android.os.SystemProtoOrBuilder
        public List<WifiSignalStrength> getWifiSignalStrengthList() {
            return Collections.unmodifiableList(((SystemProto) this.instance).getWifiSignalStrengthList());
        }

        @Override // android.os.SystemProtoOrBuilder
        public int getWifiSignalStrengthCount() {
            return ((SystemProto) this.instance).getWifiSignalStrengthCount();
        }

        @Override // android.os.SystemProtoOrBuilder
        public WifiSignalStrength getWifiSignalStrength(int index) {
            return ((SystemProto) this.instance).getWifiSignalStrength(index);
        }

        public Builder setWifiSignalStrength(int index, WifiSignalStrength value) {
            copyOnWrite();
            ((SystemProto) this.instance).setWifiSignalStrength((SystemProto) index, (int) value);
            return this;
        }

        public Builder setWifiSignalStrength(int index, WifiSignalStrength.Builder builderForValue) {
            copyOnWrite();
            ((SystemProto) this.instance).setWifiSignalStrength((SystemProto) index, (int) builderForValue);
            return this;
        }

        public Builder addWifiSignalStrength(WifiSignalStrength value) {
            copyOnWrite();
            ((SystemProto) this.instance).addWifiSignalStrength((SystemProto) value);
            return this;
        }

        public Builder addWifiSignalStrength(int index, WifiSignalStrength value) {
            copyOnWrite();
            ((SystemProto) this.instance).addWifiSignalStrength((SystemProto) index, (int) value);
            return this;
        }

        public Builder addWifiSignalStrength(WifiSignalStrength.Builder builderForValue) {
            copyOnWrite();
            ((SystemProto) this.instance).addWifiSignalStrength((SystemProto) builderForValue);
            return this;
        }

        public Builder addWifiSignalStrength(int index, WifiSignalStrength.Builder builderForValue) {
            copyOnWrite();
            ((SystemProto) this.instance).addWifiSignalStrength((SystemProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllWifiSignalStrength(Iterable<? extends WifiSignalStrength> values) {
            copyOnWrite();
            ((SystemProto) this.instance).addAllWifiSignalStrength(values);
            return this;
        }

        public Builder clearWifiSignalStrength() {
            copyOnWrite();
            ((SystemProto) this.instance).clearWifiSignalStrength();
            return this;
        }

        public Builder removeWifiSignalStrength(int index) {
            copyOnWrite();
            ((SystemProto) this.instance).removeWifiSignalStrength(index);
            return this;
        }

        @Override // android.os.SystemProtoOrBuilder
        public List<WifiState> getWifiStateList() {
            return Collections.unmodifiableList(((SystemProto) this.instance).getWifiStateList());
        }

        @Override // android.os.SystemProtoOrBuilder
        public int getWifiStateCount() {
            return ((SystemProto) this.instance).getWifiStateCount();
        }

        @Override // android.os.SystemProtoOrBuilder
        public WifiState getWifiState(int index) {
            return ((SystemProto) this.instance).getWifiState(index);
        }

        public Builder setWifiState(int index, WifiState value) {
            copyOnWrite();
            ((SystemProto) this.instance).setWifiState((SystemProto) index, (int) value);
            return this;
        }

        public Builder setWifiState(int index, WifiState.Builder builderForValue) {
            copyOnWrite();
            ((SystemProto) this.instance).setWifiState((SystemProto) index, (int) builderForValue);
            return this;
        }

        public Builder addWifiState(WifiState value) {
            copyOnWrite();
            ((SystemProto) this.instance).addWifiState((SystemProto) value);
            return this;
        }

        public Builder addWifiState(int index, WifiState value) {
            copyOnWrite();
            ((SystemProto) this.instance).addWifiState((SystemProto) index, (int) value);
            return this;
        }

        public Builder addWifiState(WifiState.Builder builderForValue) {
            copyOnWrite();
            ((SystemProto) this.instance).addWifiState((SystemProto) builderForValue);
            return this;
        }

        public Builder addWifiState(int index, WifiState.Builder builderForValue) {
            copyOnWrite();
            ((SystemProto) this.instance).addWifiState((SystemProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllWifiState(Iterable<? extends WifiState> values) {
            copyOnWrite();
            ((SystemProto) this.instance).addAllWifiState(values);
            return this;
        }

        public Builder clearWifiState() {
            copyOnWrite();
            ((SystemProto) this.instance).clearWifiState();
            return this;
        }

        public Builder removeWifiState(int index) {
            copyOnWrite();
            ((SystemProto) this.instance).removeWifiState(index);
            return this;
        }

        @Override // android.os.SystemProtoOrBuilder
        public List<WifiSupplicantState> getWifiSupplicantStateList() {
            return Collections.unmodifiableList(((SystemProto) this.instance).getWifiSupplicantStateList());
        }

        @Override // android.os.SystemProtoOrBuilder
        public int getWifiSupplicantStateCount() {
            return ((SystemProto) this.instance).getWifiSupplicantStateCount();
        }

        @Override // android.os.SystemProtoOrBuilder
        public WifiSupplicantState getWifiSupplicantState(int index) {
            return ((SystemProto) this.instance).getWifiSupplicantState(index);
        }

        public Builder setWifiSupplicantState(int index, WifiSupplicantState value) {
            copyOnWrite();
            ((SystemProto) this.instance).setWifiSupplicantState((SystemProto) index, (int) value);
            return this;
        }

        public Builder setWifiSupplicantState(int index, WifiSupplicantState.Builder builderForValue) {
            copyOnWrite();
            ((SystemProto) this.instance).setWifiSupplicantState((SystemProto) index, (int) builderForValue);
            return this;
        }

        public Builder addWifiSupplicantState(WifiSupplicantState value) {
            copyOnWrite();
            ((SystemProto) this.instance).addWifiSupplicantState((SystemProto) value);
            return this;
        }

        public Builder addWifiSupplicantState(int index, WifiSupplicantState value) {
            copyOnWrite();
            ((SystemProto) this.instance).addWifiSupplicantState((SystemProto) index, (int) value);
            return this;
        }

        public Builder addWifiSupplicantState(WifiSupplicantState.Builder builderForValue) {
            copyOnWrite();
            ((SystemProto) this.instance).addWifiSupplicantState((SystemProto) builderForValue);
            return this;
        }

        public Builder addWifiSupplicantState(int index, WifiSupplicantState.Builder builderForValue) {
            copyOnWrite();
            ((SystemProto) this.instance).addWifiSupplicantState((SystemProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllWifiSupplicantState(Iterable<? extends WifiSupplicantState> values) {
            copyOnWrite();
            ((SystemProto) this.instance).addAllWifiSupplicantState(values);
            return this;
        }

        public Builder clearWifiSupplicantState() {
            copyOnWrite();
            ((SystemProto) this.instance).clearWifiSupplicantState();
            return this;
        }

        public Builder removeWifiSupplicantState(int index) {
            copyOnWrite();
            ((SystemProto) this.instance).removeWifiSupplicantState(index);
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
                return new SystemProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                this.chargeStep_.makeImmutable();
                this.dischargeStep_.makeImmutable();
                this.cpuFrequency_.makeImmutable();
                this.dataConnection_.makeImmutable();
                this.kernelWakelock_.makeImmutable();
                this.phoneSignalStrength_.makeImmutable();
                this.powerUseItem_.makeImmutable();
                this.resourcePowerManager_.makeImmutable();
                this.screenBrightness_.makeImmutable();
                this.wakeupReason_.makeImmutable();
                this.wifiSignalStrength_.makeImmutable();
                this.wifiState_.makeImmutable();
                this.wifiSupplicantState_.makeImmutable();
                return null;
            case NEW_BUILDER:
                return new Builder(null);
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                SystemProto other = (SystemProto) arg1;
                this.battery_ = (Battery) visitor.visitMessage(this.battery_, other.battery_);
                this.batteryDischarge_ = (BatteryDischarge) visitor.visitMessage(this.batteryDischarge_, other.batteryDischarge_);
                this.chargeStep_ = visitor.visitList(this.chargeStep_, other.chargeStep_);
                this.dischargeStep_ = visitor.visitList(this.dischargeStep_, other.dischargeStep_);
                this.cpuFrequency_ = visitor.visitLongList(this.cpuFrequency_, other.cpuFrequency_);
                this.dataConnection_ = visitor.visitList(this.dataConnection_, other.dataConnection_);
                this.globalBluetoothController_ = (ControllerActivityProto) visitor.visitMessage(this.globalBluetoothController_, other.globalBluetoothController_);
                this.globalModemController_ = (ControllerActivityProto) visitor.visitMessage(this.globalModemController_, other.globalModemController_);
                this.globalWifiController_ = (ControllerActivityProto) visitor.visitMessage(this.globalWifiController_, other.globalWifiController_);
                this.globalNetwork_ = (GlobalNetwork) visitor.visitMessage(this.globalNetwork_, other.globalNetwork_);
                this.globalWifi_ = (GlobalWifi) visitor.visitMessage(this.globalWifi_, other.globalWifi_);
                this.kernelWakelock_ = visitor.visitList(this.kernelWakelock_, other.kernelWakelock_);
                this.misc_ = (Misc) visitor.visitMessage(this.misc_, other.misc_);
                this.phoneSignalStrength_ = visitor.visitList(this.phoneSignalStrength_, other.phoneSignalStrength_);
                this.powerUseItem_ = visitor.visitList(this.powerUseItem_, other.powerUseItem_);
                this.powerUseSummary_ = (PowerUseSummary) visitor.visitMessage(this.powerUseSummary_, other.powerUseSummary_);
                this.resourcePowerManager_ = visitor.visitList(this.resourcePowerManager_, other.resourcePowerManager_);
                this.screenBrightness_ = visitor.visitList(this.screenBrightness_, other.screenBrightness_);
                this.signalScanning_ = (TimerProto) visitor.visitMessage(this.signalScanning_, other.signalScanning_);
                this.wakeupReason_ = visitor.visitList(this.wakeupReason_, other.wakeupReason_);
                this.wifiMulticastWakelockTotal_ = (WifiMulticastWakelockTotal) visitor.visitMessage(this.wifiMulticastWakelockTotal_, other.wifiMulticastWakelockTotal_);
                this.wifiSignalStrength_ = visitor.visitList(this.wifiSignalStrength_, other.wifiSignalStrength_);
                this.wifiState_ = visitor.visitList(this.wifiState_, other.wifiState_);
                this.wifiSupplicantState_ = visitor.visitList(this.wifiSupplicantState_, other.wifiSupplicantState_);
                int i = AnonymousClass1.$SwitchMap$android$os$SystemProto$TimeRemainingCase[other.getTimeRemainingCase().ordinal()];
                if (i == 1) {
                    if (this.timeRemainingCase_ != 3) {
                        z = false;
                    }
                    this.timeRemaining_ = visitor.visitOneofLong(z, this.timeRemaining_, other.timeRemaining_);
                } else if (i == 2) {
                    if (this.timeRemainingCase_ != 4) {
                        z = false;
                    }
                    this.timeRemaining_ = visitor.visitOneofLong(z, this.timeRemaining_, other.timeRemaining_);
                } else if (i == 3) {
                    if (this.timeRemainingCase_ == 0) {
                        z = false;
                    }
                    visitor.visitOneofNotSet(z);
                }
                if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE) {
                    int i2 = other.timeRemainingCase_;
                    if (i2 != 0) {
                        this.timeRemainingCase_ = i2;
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
                        switch (tag) {
                            case 0:
                                done = true;
                                break;
                            case 10:
                                Battery.Builder subBuilder = null;
                                if ((this.bitField0_ & 1) == 1) {
                                    subBuilder = (Battery.Builder) this.battery_.toBuilder();
                                }
                                this.battery_ = (Battery) input.readMessage(Battery.parser(), extensionRegistry);
                                if (subBuilder != null) {
                                    subBuilder.mergeFrom((GeneratedMessageLite) this.battery_);
                                    this.battery_ = (Battery) subBuilder.buildPartial();
                                }
                                this.bitField0_ |= 1;
                                break;
                            case 18:
                                BatteryDischarge.Builder subBuilder2 = null;
                                if ((this.bitField0_ & 2) == 2) {
                                    subBuilder2 = (BatteryDischarge.Builder) this.batteryDischarge_.toBuilder();
                                }
                                this.batteryDischarge_ = (BatteryDischarge) input.readMessage(BatteryDischarge.parser(), extensionRegistry);
                                if (subBuilder2 != null) {
                                    subBuilder2.mergeFrom((GeneratedMessageLite) this.batteryDischarge_);
                                    this.batteryDischarge_ = (BatteryDischarge) subBuilder2.buildPartial();
                                }
                                this.bitField0_ |= 2;
                                break;
                            case 24:
                                this.timeRemainingCase_ = 3;
                                this.timeRemaining_ = Long.valueOf(input.readInt64());
                                break;
                            case 32:
                                this.timeRemainingCase_ = 4;
                                this.timeRemaining_ = Long.valueOf(input.readInt64());
                                break;
                            case 42:
                                if (!this.chargeStep_.isModifiable()) {
                                    this.chargeStep_ = GeneratedMessageLite.mutableCopy(this.chargeStep_);
                                }
                                this.chargeStep_.add((BatteryLevelStep) input.readMessage(BatteryLevelStep.parser(), extensionRegistry));
                                break;
                            case 50:
                                if (!this.dischargeStep_.isModifiable()) {
                                    this.dischargeStep_ = GeneratedMessageLite.mutableCopy(this.dischargeStep_);
                                }
                                this.dischargeStep_.add((BatteryLevelStep) input.readMessage(BatteryLevelStep.parser(), extensionRegistry));
                                break;
                            case 56:
                                if (!this.cpuFrequency_.isModifiable()) {
                                    this.cpuFrequency_ = GeneratedMessageLite.mutableCopy(this.cpuFrequency_);
                                }
                                this.cpuFrequency_.addLong(input.readInt64());
                                break;
                            case 58:
                                int limit = input.pushLimit(input.readRawVarint32());
                                if (!this.cpuFrequency_.isModifiable() && input.getBytesUntilLimit() > 0) {
                                    this.cpuFrequency_ = GeneratedMessageLite.mutableCopy(this.cpuFrequency_);
                                }
                                while (input.getBytesUntilLimit() > 0) {
                                    this.cpuFrequency_.addLong(input.readInt64());
                                }
                                input.popLimit(limit);
                                break;
                            case 66:
                                if (!this.dataConnection_.isModifiable()) {
                                    this.dataConnection_ = GeneratedMessageLite.mutableCopy(this.dataConnection_);
                                }
                                this.dataConnection_.add((DataConnection) input.readMessage(DataConnection.parser(), extensionRegistry));
                                break;
                            case 74:
                                ControllerActivityProto.Builder subBuilder3 = null;
                                if ((this.bitField0_ & 16) == 16) {
                                    subBuilder3 = (ControllerActivityProto.Builder) this.globalBluetoothController_.toBuilder();
                                }
                                this.globalBluetoothController_ = (ControllerActivityProto) input.readMessage(ControllerActivityProto.parser(), extensionRegistry);
                                if (subBuilder3 != null) {
                                    subBuilder3.mergeFrom((GeneratedMessageLite) this.globalBluetoothController_);
                                    this.globalBluetoothController_ = (ControllerActivityProto) subBuilder3.buildPartial();
                                }
                                this.bitField0_ |= 16;
                                break;
                            case 82:
                                ControllerActivityProto.Builder subBuilder4 = null;
                                if ((this.bitField0_ & 32) == 32) {
                                    subBuilder4 = (ControllerActivityProto.Builder) this.globalModemController_.toBuilder();
                                }
                                this.globalModemController_ = (ControllerActivityProto) input.readMessage(ControllerActivityProto.parser(), extensionRegistry);
                                if (subBuilder4 != null) {
                                    subBuilder4.mergeFrom((GeneratedMessageLite) this.globalModemController_);
                                    this.globalModemController_ = (ControllerActivityProto) subBuilder4.buildPartial();
                                }
                                this.bitField0_ |= 32;
                                break;
                            case 90:
                                ControllerActivityProto.Builder subBuilder5 = null;
                                if ((this.bitField0_ & 64) == 64) {
                                    subBuilder5 = (ControllerActivityProto.Builder) this.globalWifiController_.toBuilder();
                                }
                                this.globalWifiController_ = (ControllerActivityProto) input.readMessage(ControllerActivityProto.parser(), extensionRegistry);
                                if (subBuilder5 != null) {
                                    subBuilder5.mergeFrom((GeneratedMessageLite) this.globalWifiController_);
                                    this.globalWifiController_ = (ControllerActivityProto) subBuilder5.buildPartial();
                                }
                                this.bitField0_ |= 64;
                                break;
                            case 98:
                                GlobalNetwork.Builder subBuilder6 = null;
                                if ((this.bitField0_ & 128) == 128) {
                                    subBuilder6 = (GlobalNetwork.Builder) this.globalNetwork_.toBuilder();
                                }
                                this.globalNetwork_ = (GlobalNetwork) input.readMessage(GlobalNetwork.parser(), extensionRegistry);
                                if (subBuilder6 != null) {
                                    subBuilder6.mergeFrom((GeneratedMessageLite) this.globalNetwork_);
                                    this.globalNetwork_ = (GlobalNetwork) subBuilder6.buildPartial();
                                }
                                this.bitField0_ |= 128;
                                break;
                            case 106:
                                GlobalWifi.Builder subBuilder7 = null;
                                if ((this.bitField0_ & 256) == 256) {
                                    subBuilder7 = (GlobalWifi.Builder) this.globalWifi_.toBuilder();
                                }
                                this.globalWifi_ = (GlobalWifi) input.readMessage(GlobalWifi.parser(), extensionRegistry);
                                if (subBuilder7 != null) {
                                    subBuilder7.mergeFrom((GeneratedMessageLite) this.globalWifi_);
                                    this.globalWifi_ = (GlobalWifi) subBuilder7.buildPartial();
                                }
                                this.bitField0_ |= 256;
                                break;
                            case 114:
                                if (!this.kernelWakelock_.isModifiable()) {
                                    this.kernelWakelock_ = GeneratedMessageLite.mutableCopy(this.kernelWakelock_);
                                }
                                this.kernelWakelock_.add((KernelWakelock) input.readMessage(KernelWakelock.parser(), extensionRegistry));
                                break;
                            case 122:
                                Misc.Builder subBuilder8 = null;
                                if ((this.bitField0_ & 512) == 512) {
                                    subBuilder8 = (Misc.Builder) this.misc_.toBuilder();
                                }
                                this.misc_ = (Misc) input.readMessage(Misc.parser(), extensionRegistry);
                                if (subBuilder8 != null) {
                                    subBuilder8.mergeFrom((GeneratedMessageLite) this.misc_);
                                    this.misc_ = (Misc) subBuilder8.buildPartial();
                                }
                                this.bitField0_ |= 512;
                                break;
                            case 130:
                                if (!this.phoneSignalStrength_.isModifiable()) {
                                    this.phoneSignalStrength_ = GeneratedMessageLite.mutableCopy(this.phoneSignalStrength_);
                                }
                                this.phoneSignalStrength_.add((PhoneSignalStrength) input.readMessage(PhoneSignalStrength.parser(), extensionRegistry));
                                break;
                            case 138:
                                if (!this.powerUseItem_.isModifiable()) {
                                    this.powerUseItem_ = GeneratedMessageLite.mutableCopy(this.powerUseItem_);
                                }
                                this.powerUseItem_.add((PowerUseItem) input.readMessage(PowerUseItem.parser(), extensionRegistry));
                                break;
                            case 146:
                                PowerUseSummary.Builder subBuilder9 = null;
                                if ((this.bitField0_ & 1024) == 1024) {
                                    subBuilder9 = (PowerUseSummary.Builder) this.powerUseSummary_.toBuilder();
                                }
                                this.powerUseSummary_ = (PowerUseSummary) input.readMessage(PowerUseSummary.parser(), extensionRegistry);
                                if (subBuilder9 != null) {
                                    subBuilder9.mergeFrom((GeneratedMessageLite) this.powerUseSummary_);
                                    this.powerUseSummary_ = (PowerUseSummary) subBuilder9.buildPartial();
                                }
                                this.bitField0_ |= 1024;
                                break;
                            case 154:
                                if (!this.resourcePowerManager_.isModifiable()) {
                                    this.resourcePowerManager_ = GeneratedMessageLite.mutableCopy(this.resourcePowerManager_);
                                }
                                this.resourcePowerManager_.add((ResourcePowerManager) input.readMessage(ResourcePowerManager.parser(), extensionRegistry));
                                break;
                            case 162:
                                if (!this.screenBrightness_.isModifiable()) {
                                    this.screenBrightness_ = GeneratedMessageLite.mutableCopy(this.screenBrightness_);
                                }
                                this.screenBrightness_.add((ScreenBrightness) input.readMessage(ScreenBrightness.parser(), extensionRegistry));
                                break;
                            case 170:
                                TimerProto.Builder subBuilder10 = null;
                                if ((this.bitField0_ & 2048) == 2048) {
                                    subBuilder10 = (TimerProto.Builder) this.signalScanning_.toBuilder();
                                }
                                this.signalScanning_ = (TimerProto) input.readMessage(TimerProto.parser(), extensionRegistry);
                                if (subBuilder10 != null) {
                                    subBuilder10.mergeFrom((GeneratedMessageLite) this.signalScanning_);
                                    this.signalScanning_ = (TimerProto) subBuilder10.buildPartial();
                                }
                                this.bitField0_ |= 2048;
                                break;
                            case 178:
                                if (!this.wakeupReason_.isModifiable()) {
                                    this.wakeupReason_ = GeneratedMessageLite.mutableCopy(this.wakeupReason_);
                                }
                                this.wakeupReason_.add((WakeupReason) input.readMessage(WakeupReason.parser(), extensionRegistry));
                                break;
                            case AtomsProto.Atom.TOMB_STONE_OCCURRED_FIELD_NUMBER /*{ENCODED_INT: 186}*/:
                                WifiMulticastWakelockTotal.Builder subBuilder11 = null;
                                if ((this.bitField0_ & 4096) == 4096) {
                                    subBuilder11 = (WifiMulticastWakelockTotal.Builder) this.wifiMulticastWakelockTotal_.toBuilder();
                                }
                                this.wifiMulticastWakelockTotal_ = (WifiMulticastWakelockTotal) input.readMessage(WifiMulticastWakelockTotal.parser(), extensionRegistry);
                                if (subBuilder11 != null) {
                                    subBuilder11.mergeFrom((GeneratedMessageLite) this.wifiMulticastWakelockTotal_);
                                    this.wifiMulticastWakelockTotal_ = (WifiMulticastWakelockTotal) subBuilder11.buildPartial();
                                }
                                this.bitField0_ |= 4096;
                                break;
                            case AtomsProto.Atom.MEDIAMETRICS_AUDIOTRACK_REPORTED_FIELD_NUMBER /*{ENCODED_INT: 194}*/:
                                if (!this.wifiSignalStrength_.isModifiable()) {
                                    this.wifiSignalStrength_ = GeneratedMessageLite.mutableCopy(this.wifiSignalStrength_);
                                }
                                this.wifiSignalStrength_.add((WifiSignalStrength) input.readMessage(WifiSignalStrength.parser(), extensionRegistry));
                                break;
                            case PROCESS_STATS_SUMMARY_VALUE:
                                if (!this.wifiState_.isModifiable()) {
                                    this.wifiState_ = GeneratedMessageLite.mutableCopy(this.wifiState_);
                                }
                                this.wifiState_.add((WifiState) input.readMessage(WifiState.parser(), extensionRegistry));
                                break;
                            case AtomsProto.Atom.LOCATION_MANAGER_API_USAGE_REPORTED_FIELD_NUMBER /*{ENCODED_INT: 210}*/:
                                if (!this.wifiSupplicantState_.isModifiable()) {
                                    this.wifiSupplicantState_ = GeneratedMessageLite.mutableCopy(this.wifiSupplicantState_);
                                }
                                this.wifiSupplicantState_.add((WifiSupplicantState) input.readMessage(WifiSupplicantState.parser(), extensionRegistry));
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
                    synchronized (SystemProto.class) {
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

    /* renamed from: android.os.SystemProto$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$android$os$SystemProto$DataConnection$TypeCase = new int[DataConnection.TypeCase.values().length];
        static final /* synthetic */ int[] $SwitchMap$android$os$SystemProto$TimeRemainingCase = new int[TimeRemainingCase.values().length];

        static {
            try {
                $SwitchMap$android$os$SystemProto$TimeRemainingCase[TimeRemainingCase.CHARGE_TIME_REMAINING_MS.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$android$os$SystemProto$TimeRemainingCase[TimeRemainingCase.DISCHARGE_TIME_REMAINING_MS.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$android$os$SystemProto$TimeRemainingCase[TimeRemainingCase.TIMEREMAINING_NOT_SET.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$android$os$SystemProto$DataConnection$TypeCase[DataConnection.TypeCase.NAME.ordinal()] = 1;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$android$os$SystemProto$DataConnection$TypeCase[DataConnection.TypeCase.IS_NONE.ordinal()] = 2;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$android$os$SystemProto$DataConnection$TypeCase[DataConnection.TypeCase.TYPE_NOT_SET.ordinal()] = 3;
            } catch (NoSuchFieldError e6) {
            }
            $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke = new int[GeneratedMessageLite.MethodToInvoke.values().length];
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.NEW_MUTABLE_INSTANCE.ordinal()] = 1;
            } catch (NoSuchFieldError e7) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.IS_INITIALIZED.ordinal()] = 2;
            } catch (NoSuchFieldError e8) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.MAKE_IMMUTABLE.ordinal()] = 3;
            } catch (NoSuchFieldError e9) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.NEW_BUILDER.ordinal()] = 4;
            } catch (NoSuchFieldError e10) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.VISIT.ordinal()] = 5;
            } catch (NoSuchFieldError e11) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.MERGE_FROM_STREAM.ordinal()] = 6;
            } catch (NoSuchFieldError e12) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.GET_DEFAULT_INSTANCE.ordinal()] = 7;
            } catch (NoSuchFieldError e13) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.GET_PARSER.ordinal()] = 8;
            } catch (NoSuchFieldError e14) {
            }
        }
    }

    static {
        DEFAULT_INSTANCE.makeImmutable();
    }

    public static SystemProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<SystemProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
