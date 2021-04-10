package com.android.internal.os;

import android.telephony.DataConnectionPowerStateEnum;
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

public final class PowerProfileProto extends GeneratedMessageLite<PowerProfileProto, Builder> implements PowerProfileProtoOrBuilder {
    public static final int AMBIENT_DISPLAY_FIELD_NUMBER = 27;
    public static final int AUDIO_FIELD_NUMBER = 33;
    public static final int BATTERY_CAPACITY_FIELD_NUMBER = 39;
    public static final int BLUETOOTH_ACTIVE_FIELD_NUMBER = 25;
    public static final int BLUETOOTH_AT_CMD_FIELD_NUMBER = 26;
    public static final int BLUETOOTH_CONTROLLER_IDLE_FIELD_NUMBER = 12;
    public static final int BLUETOOTH_CONTROLLER_OPERATING_VOLTAGE_FIELD_NUMBER = 15;
    public static final int BLUETOOTH_CONTROLLER_RX_FIELD_NUMBER = 13;
    public static final int BLUETOOTH_CONTROLLER_TX_FIELD_NUMBER = 14;
    public static final int BLUETOOTH_ON_FIELD_NUMBER = 24;
    public static final int CAMERA_FIELD_NUMBER = 37;
    public static final int CPU_ACTIVE_FIELD_NUMBER = 3;
    public static final int CPU_CLUSTER_FIELD_NUMBER = 40;
    public static final int CPU_IDLE_FIELD_NUMBER = 2;
    public static final int CPU_SUSPEND_FIELD_NUMBER = 1;
    private static final PowerProfileProto DEFAULT_INSTANCE = new PowerProfileProto();
    public static final int FLASHLIGHT_FIELD_NUMBER = 35;
    public static final int GPS_ON_FIELD_NUMBER = 21;
    public static final int GPS_OPERATING_VOLTAGE_FIELD_NUMBER = 23;
    public static final int GPS_SIGNAL_QUALITY_BASED_FIELD_NUMBER = 22;
    public static final int MEMORY_FIELD_NUMBER = 36;
    public static final int MODEM_CONTROLLER_IDLE_FIELD_NUMBER = 17;
    public static final int MODEM_CONTROLLER_OPERATING_VOLTAGE_FIELD_NUMBER = 20;
    public static final int MODEM_CONTROLLER_RX_FIELD_NUMBER = 18;
    public static final int MODEM_CONTROLLER_SLEEP_FIELD_NUMBER = 16;
    public static final int MODEM_CONTROLLER_TX_FIELD_NUMBER = 19;
    private static volatile Parser<PowerProfileProto> PARSER = null;
    public static final int RADIO_ACTIVE_FIELD_NUMBER = 31;
    public static final int RADIO_ON_FIELD_NUMBER = 29;
    public static final int RADIO_SCANNING_FIELD_NUMBER = 30;
    public static final int SCREEN_FULL_FIELD_NUMBER = 32;
    public static final int SCREEN_ON_FIELD_NUMBER = 28;
    public static final int VIDEO_FIELD_NUMBER = 34;
    public static final int WIFI_ACTIVE_FIELD_NUMBER = 6;
    public static final int WIFI_BATCHED_SCAN_FIELD_NUMBER = 38;
    public static final int WIFI_CONTROLLER_IDLE_FIELD_NUMBER = 7;
    public static final int WIFI_CONTROLLER_OPERATING_VOLTAGE_FIELD_NUMBER = 11;
    public static final int WIFI_CONTROLLER_RX_FIELD_NUMBER = 8;
    public static final int WIFI_CONTROLLER_TX_FIELD_NUMBER = 9;
    public static final int WIFI_CONTROLLER_TX_LEVELS_FIELD_NUMBER = 10;
    public static final int WIFI_ON_FIELD_NUMBER = 5;
    public static final int WIFI_SCAN_FIELD_NUMBER = 4;
    private double ambientDisplay_ = 0.0d;
    private double audio_ = 0.0d;
    private double batteryCapacity_ = 0.0d;
    private int bitField0_;
    private int bitField1_;
    private double bluetoothActive_ = 0.0d;
    private double bluetoothAtCmd_ = 0.0d;
    private double bluetoothControllerIdle_ = 0.0d;
    private double bluetoothControllerOperatingVoltage_ = 0.0d;
    private double bluetoothControllerRx_ = 0.0d;
    private double bluetoothControllerTx_ = 0.0d;
    private double bluetoothOn_ = 0.0d;
    private double camera_ = 0.0d;
    private double cpuActive_ = 0.0d;
    private Internal.ProtobufList<CpuCluster> cpuCluster_ = emptyProtobufList();
    private double cpuIdle_ = 0.0d;
    private double cpuSuspend_ = 0.0d;
    private double flashlight_ = 0.0d;
    private double gpsOn_ = 0.0d;
    private double gpsOperatingVoltage_ = 0.0d;
    private Internal.DoubleList gpsSignalQualityBased_ = emptyDoubleList();
    private double memory_ = 0.0d;
    private double modemControllerIdle_ = 0.0d;
    private double modemControllerOperatingVoltage_ = 0.0d;
    private double modemControllerRx_ = 0.0d;
    private double modemControllerSleep_ = 0.0d;
    private Internal.DoubleList modemControllerTx_ = emptyDoubleList();
    private double radioActive_ = 0.0d;
    private double radioOn_ = 0.0d;
    private double radioScanning_ = 0.0d;
    private double screenFull_ = 0.0d;
    private double screenOn_ = 0.0d;
    private double video_ = 0.0d;
    private double wifiActive_ = 0.0d;
    private double wifiBatchedScan_ = 0.0d;
    private double wifiControllerIdle_ = 0.0d;
    private double wifiControllerOperatingVoltage_ = 0.0d;
    private double wifiControllerRx_ = 0.0d;
    private Internal.DoubleList wifiControllerTxLevels_ = emptyDoubleList();
    private double wifiControllerTx_ = 0.0d;
    private double wifiOn_ = 0.0d;
    private double wifiScan_ = 0.0d;

    public interface CpuClusterOrBuilder extends MessageLiteOrBuilder {
        double getClusterPower();

        double getCorePower(int i);

        int getCorePowerCount();

        List<Double> getCorePowerList();

        int getCores();

        int getId();

        long getSpeed(int i);

        int getSpeedCount();

        List<Long> getSpeedList();

        boolean hasClusterPower();

        boolean hasCores();

        boolean hasId();
    }

    private PowerProfileProto() {
    }

    public static final class CpuCluster extends GeneratedMessageLite<CpuCluster, Builder> implements CpuClusterOrBuilder {
        public static final int CLUSTER_POWER_FIELD_NUMBER = 2;
        public static final int CORES_FIELD_NUMBER = 3;
        public static final int CORE_POWER_FIELD_NUMBER = 5;
        private static final CpuCluster DEFAULT_INSTANCE = new CpuCluster();
        public static final int ID_FIELD_NUMBER = 1;
        private static volatile Parser<CpuCluster> PARSER = null;
        public static final int SPEED_FIELD_NUMBER = 4;
        private int bitField0_;
        private double clusterPower_ = 0.0d;
        private Internal.DoubleList corePower_ = emptyDoubleList();
        private int cores_ = 0;
        private int id_ = 0;
        private Internal.LongList speed_ = emptyLongList();

        private CpuCluster() {
        }

        @Override // com.android.internal.os.PowerProfileProto.CpuClusterOrBuilder
        public boolean hasId() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // com.android.internal.os.PowerProfileProto.CpuClusterOrBuilder
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

        @Override // com.android.internal.os.PowerProfileProto.CpuClusterOrBuilder
        public boolean hasClusterPower() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // com.android.internal.os.PowerProfileProto.CpuClusterOrBuilder
        public double getClusterPower() {
            return this.clusterPower_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setClusterPower(double value) {
            this.bitField0_ |= 2;
            this.clusterPower_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearClusterPower() {
            this.bitField0_ &= -3;
            this.clusterPower_ = 0.0d;
        }

        @Override // com.android.internal.os.PowerProfileProto.CpuClusterOrBuilder
        public boolean hasCores() {
            return (this.bitField0_ & 4) == 4;
        }

        @Override // com.android.internal.os.PowerProfileProto.CpuClusterOrBuilder
        public int getCores() {
            return this.cores_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setCores(int value) {
            this.bitField0_ |= 4;
            this.cores_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearCores() {
            this.bitField0_ &= -5;
            this.cores_ = 0;
        }

        @Override // com.android.internal.os.PowerProfileProto.CpuClusterOrBuilder
        public List<Long> getSpeedList() {
            return this.speed_;
        }

        @Override // com.android.internal.os.PowerProfileProto.CpuClusterOrBuilder
        public int getSpeedCount() {
            return this.speed_.size();
        }

        @Override // com.android.internal.os.PowerProfileProto.CpuClusterOrBuilder
        public long getSpeed(int index) {
            return this.speed_.getLong(index);
        }

        private void ensureSpeedIsMutable() {
            if (!this.speed_.isModifiable()) {
                this.speed_ = GeneratedMessageLite.mutableCopy(this.speed_);
            }
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setSpeed(int index, long value) {
            ensureSpeedIsMutable();
            this.speed_.setLong(index, value);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addSpeed(long value) {
            ensureSpeedIsMutable();
            this.speed_.addLong(value);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addAllSpeed(Iterable<? extends Long> values) {
            ensureSpeedIsMutable();
            AbstractMessageLite.addAll(values, this.speed_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearSpeed() {
            this.speed_ = emptyLongList();
        }

        @Override // com.android.internal.os.PowerProfileProto.CpuClusterOrBuilder
        public List<Double> getCorePowerList() {
            return this.corePower_;
        }

        @Override // com.android.internal.os.PowerProfileProto.CpuClusterOrBuilder
        public int getCorePowerCount() {
            return this.corePower_.size();
        }

        @Override // com.android.internal.os.PowerProfileProto.CpuClusterOrBuilder
        public double getCorePower(int index) {
            return this.corePower_.getDouble(index);
        }

        private void ensureCorePowerIsMutable() {
            if (!this.corePower_.isModifiable()) {
                this.corePower_ = GeneratedMessageLite.mutableCopy(this.corePower_);
            }
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setCorePower(int index, double value) {
            ensureCorePowerIsMutable();
            this.corePower_.setDouble(index, value);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addCorePower(double value) {
            ensureCorePowerIsMutable();
            this.corePower_.addDouble(value);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addAllCorePower(Iterable<? extends Double> values) {
            ensureCorePowerIsMutable();
            AbstractMessageLite.addAll(values, this.corePower_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearCorePower() {
            this.corePower_ = emptyDoubleList();
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                output.writeInt32(1, this.id_);
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeDouble(2, this.clusterPower_);
            }
            if ((this.bitField0_ & 4) == 4) {
                output.writeInt32(3, this.cores_);
            }
            for (int i = 0; i < this.speed_.size(); i++) {
                output.writeInt64(4, this.speed_.getLong(i));
            }
            for (int i2 = 0; i2 < this.corePower_.size(); i2++) {
                output.writeDouble(5, this.corePower_.getDouble(i2));
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
                size2 += CodedOutputStream.computeDoubleSize(2, this.clusterPower_);
            }
            if ((this.bitField0_ & 4) == 4) {
                size2 += CodedOutputStream.computeInt32Size(3, this.cores_);
            }
            int dataSize = 0;
            for (int i = 0; i < this.speed_.size(); i++) {
                dataSize += CodedOutputStream.computeInt64SizeNoTag(this.speed_.getLong(i));
            }
            int size3 = size2 + dataSize + (getSpeedList().size() * 1) + (getCorePowerList().size() * 8) + (getCorePowerList().size() * 1) + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static CpuCluster parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (CpuCluster) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static CpuCluster parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (CpuCluster) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static CpuCluster parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (CpuCluster) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static CpuCluster parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (CpuCluster) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static CpuCluster parseFrom(InputStream input) throws IOException {
            return (CpuCluster) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static CpuCluster parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (CpuCluster) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static CpuCluster parseDelimitedFrom(InputStream input) throws IOException {
            return (CpuCluster) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static CpuCluster parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (CpuCluster) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static CpuCluster parseFrom(CodedInputStream input) throws IOException {
            return (CpuCluster) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static CpuCluster parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (CpuCluster) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(CpuCluster prototype) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<CpuCluster, Builder> implements CpuClusterOrBuilder {
            private Builder() {
                super(CpuCluster.DEFAULT_INSTANCE);
            }

            @Override // com.android.internal.os.PowerProfileProto.CpuClusterOrBuilder
            public boolean hasId() {
                return ((CpuCluster) this.instance).hasId();
            }

            @Override // com.android.internal.os.PowerProfileProto.CpuClusterOrBuilder
            public int getId() {
                return ((CpuCluster) this.instance).getId();
            }

            public Builder setId(int value) {
                copyOnWrite();
                ((CpuCluster) this.instance).setId(value);
                return this;
            }

            public Builder clearId() {
                copyOnWrite();
                ((CpuCluster) this.instance).clearId();
                return this;
            }

            @Override // com.android.internal.os.PowerProfileProto.CpuClusterOrBuilder
            public boolean hasClusterPower() {
                return ((CpuCluster) this.instance).hasClusterPower();
            }

            @Override // com.android.internal.os.PowerProfileProto.CpuClusterOrBuilder
            public double getClusterPower() {
                return ((CpuCluster) this.instance).getClusterPower();
            }

            public Builder setClusterPower(double value) {
                copyOnWrite();
                ((CpuCluster) this.instance).setClusterPower(value);
                return this;
            }

            public Builder clearClusterPower() {
                copyOnWrite();
                ((CpuCluster) this.instance).clearClusterPower();
                return this;
            }

            @Override // com.android.internal.os.PowerProfileProto.CpuClusterOrBuilder
            public boolean hasCores() {
                return ((CpuCluster) this.instance).hasCores();
            }

            @Override // com.android.internal.os.PowerProfileProto.CpuClusterOrBuilder
            public int getCores() {
                return ((CpuCluster) this.instance).getCores();
            }

            public Builder setCores(int value) {
                copyOnWrite();
                ((CpuCluster) this.instance).setCores(value);
                return this;
            }

            public Builder clearCores() {
                copyOnWrite();
                ((CpuCluster) this.instance).clearCores();
                return this;
            }

            @Override // com.android.internal.os.PowerProfileProto.CpuClusterOrBuilder
            public List<Long> getSpeedList() {
                return Collections.unmodifiableList(((CpuCluster) this.instance).getSpeedList());
            }

            @Override // com.android.internal.os.PowerProfileProto.CpuClusterOrBuilder
            public int getSpeedCount() {
                return ((CpuCluster) this.instance).getSpeedCount();
            }

            @Override // com.android.internal.os.PowerProfileProto.CpuClusterOrBuilder
            public long getSpeed(int index) {
                return ((CpuCluster) this.instance).getSpeed(index);
            }

            public Builder setSpeed(int index, long value) {
                copyOnWrite();
                ((CpuCluster) this.instance).setSpeed(index, value);
                return this;
            }

            public Builder addSpeed(long value) {
                copyOnWrite();
                ((CpuCluster) this.instance).addSpeed(value);
                return this;
            }

            public Builder addAllSpeed(Iterable<? extends Long> values) {
                copyOnWrite();
                ((CpuCluster) this.instance).addAllSpeed(values);
                return this;
            }

            public Builder clearSpeed() {
                copyOnWrite();
                ((CpuCluster) this.instance).clearSpeed();
                return this;
            }

            @Override // com.android.internal.os.PowerProfileProto.CpuClusterOrBuilder
            public List<Double> getCorePowerList() {
                return Collections.unmodifiableList(((CpuCluster) this.instance).getCorePowerList());
            }

            @Override // com.android.internal.os.PowerProfileProto.CpuClusterOrBuilder
            public int getCorePowerCount() {
                return ((CpuCluster) this.instance).getCorePowerCount();
            }

            @Override // com.android.internal.os.PowerProfileProto.CpuClusterOrBuilder
            public double getCorePower(int index) {
                return ((CpuCluster) this.instance).getCorePower(index);
            }

            public Builder setCorePower(int index, double value) {
                copyOnWrite();
                ((CpuCluster) this.instance).setCorePower(index, value);
                return this;
            }

            public Builder addCorePower(double value) {
                copyOnWrite();
                ((CpuCluster) this.instance).addCorePower(value);
                return this;
            }

            public Builder addAllCorePower(Iterable<? extends Double> values) {
                copyOnWrite();
                ((CpuCluster) this.instance).addAllCorePower(values);
                return this;
            }

            public Builder clearCorePower() {
                copyOnWrite();
                ((CpuCluster) this.instance).clearCorePower();
                return this;
            }
        }

        /* JADX WARN: Type inference failed for: r7v3, types: [com.google.protobuf.Internal$DoubleList] */
        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        /* JADX WARNING: Unknown variable types count: 1 */
        @Override // com.google.protobuf.GeneratedMessageLite
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final java.lang.Object dynamicMethod(com.google.protobuf.GeneratedMessageLite.MethodToInvoke r10, java.lang.Object r11, java.lang.Object r12) {
            /*
            // Method dump skipped, instructions count: 484
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.internal.os.PowerProfileProto.CpuCluster.dynamicMethod(com.google.protobuf.GeneratedMessageLite$MethodToInvoke, java.lang.Object, java.lang.Object):java.lang.Object");
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        public static CpuCluster getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<CpuCluster> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    @Override // com.android.internal.os.PowerProfileProtoOrBuilder
    public boolean hasCpuSuspend() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // com.android.internal.os.PowerProfileProtoOrBuilder
    public double getCpuSuspend() {
        return this.cpuSuspend_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setCpuSuspend(double value) {
        this.bitField0_ |= 1;
        this.cpuSuspend_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearCpuSuspend() {
        this.bitField0_ &= -2;
        this.cpuSuspend_ = 0.0d;
    }

    @Override // com.android.internal.os.PowerProfileProtoOrBuilder
    public boolean hasCpuIdle() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // com.android.internal.os.PowerProfileProtoOrBuilder
    public double getCpuIdle() {
        return this.cpuIdle_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setCpuIdle(double value) {
        this.bitField0_ |= 2;
        this.cpuIdle_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearCpuIdle() {
        this.bitField0_ &= -3;
        this.cpuIdle_ = 0.0d;
    }

    @Override // com.android.internal.os.PowerProfileProtoOrBuilder
    public boolean hasCpuActive() {
        return (this.bitField0_ & 4) == 4;
    }

    @Override // com.android.internal.os.PowerProfileProtoOrBuilder
    public double getCpuActive() {
        return this.cpuActive_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setCpuActive(double value) {
        this.bitField0_ |= 4;
        this.cpuActive_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearCpuActive() {
        this.bitField0_ &= -5;
        this.cpuActive_ = 0.0d;
    }

    @Override // com.android.internal.os.PowerProfileProtoOrBuilder
    public List<CpuCluster> getCpuClusterList() {
        return this.cpuCluster_;
    }

    public List<? extends CpuClusterOrBuilder> getCpuClusterOrBuilderList() {
        return this.cpuCluster_;
    }

    @Override // com.android.internal.os.PowerProfileProtoOrBuilder
    public int getCpuClusterCount() {
        return this.cpuCluster_.size();
    }

    @Override // com.android.internal.os.PowerProfileProtoOrBuilder
    public CpuCluster getCpuCluster(int index) {
        return this.cpuCluster_.get(index);
    }

    public CpuClusterOrBuilder getCpuClusterOrBuilder(int index) {
        return this.cpuCluster_.get(index);
    }

    private void ensureCpuClusterIsMutable() {
        if (!this.cpuCluster_.isModifiable()) {
            this.cpuCluster_ = GeneratedMessageLite.mutableCopy(this.cpuCluster_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setCpuCluster(int index, CpuCluster value) {
        if (value != null) {
            ensureCpuClusterIsMutable();
            this.cpuCluster_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setCpuCluster(int index, CpuCluster.Builder builderForValue) {
        ensureCpuClusterIsMutable();
        this.cpuCluster_.set(index, (CpuCluster) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addCpuCluster(CpuCluster value) {
        if (value != null) {
            ensureCpuClusterIsMutable();
            this.cpuCluster_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addCpuCluster(int index, CpuCluster value) {
        if (value != null) {
            ensureCpuClusterIsMutable();
            this.cpuCluster_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addCpuCluster(CpuCluster.Builder builderForValue) {
        ensureCpuClusterIsMutable();
        this.cpuCluster_.add((CpuCluster) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addCpuCluster(int index, CpuCluster.Builder builderForValue) {
        ensureCpuClusterIsMutable();
        this.cpuCluster_.add(index, (CpuCluster) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllCpuCluster(Iterable<? extends CpuCluster> values) {
        ensureCpuClusterIsMutable();
        AbstractMessageLite.addAll(values, this.cpuCluster_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearCpuCluster() {
        this.cpuCluster_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeCpuCluster(int index) {
        ensureCpuClusterIsMutable();
        this.cpuCluster_.remove(index);
    }

    @Override // com.android.internal.os.PowerProfileProtoOrBuilder
    public boolean hasWifiScan() {
        return (this.bitField0_ & 8) == 8;
    }

    @Override // com.android.internal.os.PowerProfileProtoOrBuilder
    public double getWifiScan() {
        return this.wifiScan_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setWifiScan(double value) {
        this.bitField0_ |= 8;
        this.wifiScan_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearWifiScan() {
        this.bitField0_ &= -9;
        this.wifiScan_ = 0.0d;
    }

    @Override // com.android.internal.os.PowerProfileProtoOrBuilder
    public boolean hasWifiOn() {
        return (this.bitField0_ & 16) == 16;
    }

    @Override // com.android.internal.os.PowerProfileProtoOrBuilder
    public double getWifiOn() {
        return this.wifiOn_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setWifiOn(double value) {
        this.bitField0_ |= 16;
        this.wifiOn_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearWifiOn() {
        this.bitField0_ &= -17;
        this.wifiOn_ = 0.0d;
    }

    @Override // com.android.internal.os.PowerProfileProtoOrBuilder
    public boolean hasWifiActive() {
        return (this.bitField0_ & 32) == 32;
    }

    @Override // com.android.internal.os.PowerProfileProtoOrBuilder
    public double getWifiActive() {
        return this.wifiActive_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setWifiActive(double value) {
        this.bitField0_ |= 32;
        this.wifiActive_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearWifiActive() {
        this.bitField0_ &= -33;
        this.wifiActive_ = 0.0d;
    }

    @Override // com.android.internal.os.PowerProfileProtoOrBuilder
    public boolean hasWifiControllerIdle() {
        return (this.bitField0_ & 64) == 64;
    }

    @Override // com.android.internal.os.PowerProfileProtoOrBuilder
    public double getWifiControllerIdle() {
        return this.wifiControllerIdle_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setWifiControllerIdle(double value) {
        this.bitField0_ |= 64;
        this.wifiControllerIdle_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearWifiControllerIdle() {
        this.bitField0_ &= -65;
        this.wifiControllerIdle_ = 0.0d;
    }

    @Override // com.android.internal.os.PowerProfileProtoOrBuilder
    public boolean hasWifiControllerRx() {
        return (this.bitField0_ & 128) == 128;
    }

    @Override // com.android.internal.os.PowerProfileProtoOrBuilder
    public double getWifiControllerRx() {
        return this.wifiControllerRx_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setWifiControllerRx(double value) {
        this.bitField0_ |= 128;
        this.wifiControllerRx_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearWifiControllerRx() {
        this.bitField0_ &= -129;
        this.wifiControllerRx_ = 0.0d;
    }

    @Override // com.android.internal.os.PowerProfileProtoOrBuilder
    public boolean hasWifiControllerTx() {
        return (this.bitField0_ & 256) == 256;
    }

    @Override // com.android.internal.os.PowerProfileProtoOrBuilder
    public double getWifiControllerTx() {
        return this.wifiControllerTx_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setWifiControllerTx(double value) {
        this.bitField0_ |= 256;
        this.wifiControllerTx_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearWifiControllerTx() {
        this.bitField0_ &= -257;
        this.wifiControllerTx_ = 0.0d;
    }

    @Override // com.android.internal.os.PowerProfileProtoOrBuilder
    public List<Double> getWifiControllerTxLevelsList() {
        return this.wifiControllerTxLevels_;
    }

    @Override // com.android.internal.os.PowerProfileProtoOrBuilder
    public int getWifiControllerTxLevelsCount() {
        return this.wifiControllerTxLevels_.size();
    }

    @Override // com.android.internal.os.PowerProfileProtoOrBuilder
    public double getWifiControllerTxLevels(int index) {
        return this.wifiControllerTxLevels_.getDouble(index);
    }

    private void ensureWifiControllerTxLevelsIsMutable() {
        if (!this.wifiControllerTxLevels_.isModifiable()) {
            this.wifiControllerTxLevels_ = GeneratedMessageLite.mutableCopy(this.wifiControllerTxLevels_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setWifiControllerTxLevels(int index, double value) {
        ensureWifiControllerTxLevelsIsMutable();
        this.wifiControllerTxLevels_.setDouble(index, value);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addWifiControllerTxLevels(double value) {
        ensureWifiControllerTxLevelsIsMutable();
        this.wifiControllerTxLevels_.addDouble(value);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllWifiControllerTxLevels(Iterable<? extends Double> values) {
        ensureWifiControllerTxLevelsIsMutable();
        AbstractMessageLite.addAll(values, this.wifiControllerTxLevels_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearWifiControllerTxLevels() {
        this.wifiControllerTxLevels_ = emptyDoubleList();
    }

    @Override // com.android.internal.os.PowerProfileProtoOrBuilder
    public boolean hasWifiControllerOperatingVoltage() {
        return (this.bitField0_ & 512) == 512;
    }

    @Override // com.android.internal.os.PowerProfileProtoOrBuilder
    public double getWifiControllerOperatingVoltage() {
        return this.wifiControllerOperatingVoltage_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setWifiControllerOperatingVoltage(double value) {
        this.bitField0_ |= 512;
        this.wifiControllerOperatingVoltage_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearWifiControllerOperatingVoltage() {
        this.bitField0_ &= -513;
        this.wifiControllerOperatingVoltage_ = 0.0d;
    }

    @Override // com.android.internal.os.PowerProfileProtoOrBuilder
    public boolean hasBluetoothControllerIdle() {
        return (this.bitField0_ & 1024) == 1024;
    }

    @Override // com.android.internal.os.PowerProfileProtoOrBuilder
    public double getBluetoothControllerIdle() {
        return this.bluetoothControllerIdle_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setBluetoothControllerIdle(double value) {
        this.bitField0_ |= 1024;
        this.bluetoothControllerIdle_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearBluetoothControllerIdle() {
        this.bitField0_ &= -1025;
        this.bluetoothControllerIdle_ = 0.0d;
    }

    @Override // com.android.internal.os.PowerProfileProtoOrBuilder
    public boolean hasBluetoothControllerRx() {
        return (this.bitField0_ & 2048) == 2048;
    }

    @Override // com.android.internal.os.PowerProfileProtoOrBuilder
    public double getBluetoothControllerRx() {
        return this.bluetoothControllerRx_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setBluetoothControllerRx(double value) {
        this.bitField0_ |= 2048;
        this.bluetoothControllerRx_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearBluetoothControllerRx() {
        this.bitField0_ &= -2049;
        this.bluetoothControllerRx_ = 0.0d;
    }

    @Override // com.android.internal.os.PowerProfileProtoOrBuilder
    public boolean hasBluetoothControllerTx() {
        return (this.bitField0_ & 4096) == 4096;
    }

    @Override // com.android.internal.os.PowerProfileProtoOrBuilder
    public double getBluetoothControllerTx() {
        return this.bluetoothControllerTx_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setBluetoothControllerTx(double value) {
        this.bitField0_ |= 4096;
        this.bluetoothControllerTx_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearBluetoothControllerTx() {
        this.bitField0_ &= -4097;
        this.bluetoothControllerTx_ = 0.0d;
    }

    @Override // com.android.internal.os.PowerProfileProtoOrBuilder
    public boolean hasBluetoothControllerOperatingVoltage() {
        return (this.bitField0_ & 8192) == 8192;
    }

    @Override // com.android.internal.os.PowerProfileProtoOrBuilder
    public double getBluetoothControllerOperatingVoltage() {
        return this.bluetoothControllerOperatingVoltage_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setBluetoothControllerOperatingVoltage(double value) {
        this.bitField0_ |= 8192;
        this.bluetoothControllerOperatingVoltage_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearBluetoothControllerOperatingVoltage() {
        this.bitField0_ &= -8193;
        this.bluetoothControllerOperatingVoltage_ = 0.0d;
    }

    @Override // com.android.internal.os.PowerProfileProtoOrBuilder
    public boolean hasModemControllerSleep() {
        return (this.bitField0_ & 16384) == 16384;
    }

    @Override // com.android.internal.os.PowerProfileProtoOrBuilder
    public double getModemControllerSleep() {
        return this.modemControllerSleep_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setModemControllerSleep(double value) {
        this.bitField0_ |= 16384;
        this.modemControllerSleep_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearModemControllerSleep() {
        this.bitField0_ &= -16385;
        this.modemControllerSleep_ = 0.0d;
    }

    @Override // com.android.internal.os.PowerProfileProtoOrBuilder
    public boolean hasModemControllerIdle() {
        return (this.bitField0_ & 32768) == 32768;
    }

    @Override // com.android.internal.os.PowerProfileProtoOrBuilder
    public double getModemControllerIdle() {
        return this.modemControllerIdle_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setModemControllerIdle(double value) {
        this.bitField0_ |= 32768;
        this.modemControllerIdle_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearModemControllerIdle() {
        this.bitField0_ &= -32769;
        this.modemControllerIdle_ = 0.0d;
    }

    @Override // com.android.internal.os.PowerProfileProtoOrBuilder
    public boolean hasModemControllerRx() {
        return (this.bitField0_ & 65536) == 65536;
    }

    @Override // com.android.internal.os.PowerProfileProtoOrBuilder
    public double getModemControllerRx() {
        return this.modemControllerRx_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setModemControllerRx(double value) {
        this.bitField0_ |= 65536;
        this.modemControllerRx_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearModemControllerRx() {
        this.bitField0_ &= -65537;
        this.modemControllerRx_ = 0.0d;
    }

    @Override // com.android.internal.os.PowerProfileProtoOrBuilder
    public List<Double> getModemControllerTxList() {
        return this.modemControllerTx_;
    }

    @Override // com.android.internal.os.PowerProfileProtoOrBuilder
    public int getModemControllerTxCount() {
        return this.modemControllerTx_.size();
    }

    @Override // com.android.internal.os.PowerProfileProtoOrBuilder
    public double getModemControllerTx(int index) {
        return this.modemControllerTx_.getDouble(index);
    }

    private void ensureModemControllerTxIsMutable() {
        if (!this.modemControllerTx_.isModifiable()) {
            this.modemControllerTx_ = GeneratedMessageLite.mutableCopy(this.modemControllerTx_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setModemControllerTx(int index, double value) {
        ensureModemControllerTxIsMutable();
        this.modemControllerTx_.setDouble(index, value);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addModemControllerTx(double value) {
        ensureModemControllerTxIsMutable();
        this.modemControllerTx_.addDouble(value);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllModemControllerTx(Iterable<? extends Double> values) {
        ensureModemControllerTxIsMutable();
        AbstractMessageLite.addAll(values, this.modemControllerTx_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearModemControllerTx() {
        this.modemControllerTx_ = emptyDoubleList();
    }

    @Override // com.android.internal.os.PowerProfileProtoOrBuilder
    public boolean hasModemControllerOperatingVoltage() {
        return (this.bitField0_ & 131072) == 131072;
    }

    @Override // com.android.internal.os.PowerProfileProtoOrBuilder
    public double getModemControllerOperatingVoltage() {
        return this.modemControllerOperatingVoltage_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setModemControllerOperatingVoltage(double value) {
        this.bitField0_ |= 131072;
        this.modemControllerOperatingVoltage_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearModemControllerOperatingVoltage() {
        this.bitField0_ &= -131073;
        this.modemControllerOperatingVoltage_ = 0.0d;
    }

    @Override // com.android.internal.os.PowerProfileProtoOrBuilder
    public boolean hasGpsOn() {
        return (this.bitField0_ & 262144) == 262144;
    }

    @Override // com.android.internal.os.PowerProfileProtoOrBuilder
    public double getGpsOn() {
        return this.gpsOn_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setGpsOn(double value) {
        this.bitField0_ |= 262144;
        this.gpsOn_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearGpsOn() {
        this.bitField0_ &= -262145;
        this.gpsOn_ = 0.0d;
    }

    @Override // com.android.internal.os.PowerProfileProtoOrBuilder
    public List<Double> getGpsSignalQualityBasedList() {
        return this.gpsSignalQualityBased_;
    }

    @Override // com.android.internal.os.PowerProfileProtoOrBuilder
    public int getGpsSignalQualityBasedCount() {
        return this.gpsSignalQualityBased_.size();
    }

    @Override // com.android.internal.os.PowerProfileProtoOrBuilder
    public double getGpsSignalQualityBased(int index) {
        return this.gpsSignalQualityBased_.getDouble(index);
    }

    private void ensureGpsSignalQualityBasedIsMutable() {
        if (!this.gpsSignalQualityBased_.isModifiable()) {
            this.gpsSignalQualityBased_ = GeneratedMessageLite.mutableCopy(this.gpsSignalQualityBased_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setGpsSignalQualityBased(int index, double value) {
        ensureGpsSignalQualityBasedIsMutable();
        this.gpsSignalQualityBased_.setDouble(index, value);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addGpsSignalQualityBased(double value) {
        ensureGpsSignalQualityBasedIsMutable();
        this.gpsSignalQualityBased_.addDouble(value);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllGpsSignalQualityBased(Iterable<? extends Double> values) {
        ensureGpsSignalQualityBasedIsMutable();
        AbstractMessageLite.addAll(values, this.gpsSignalQualityBased_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearGpsSignalQualityBased() {
        this.gpsSignalQualityBased_ = emptyDoubleList();
    }

    @Override // com.android.internal.os.PowerProfileProtoOrBuilder
    public boolean hasGpsOperatingVoltage() {
        return (this.bitField0_ & 524288) == 524288;
    }

    @Override // com.android.internal.os.PowerProfileProtoOrBuilder
    public double getGpsOperatingVoltage() {
        return this.gpsOperatingVoltage_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setGpsOperatingVoltage(double value) {
        this.bitField0_ |= 524288;
        this.gpsOperatingVoltage_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearGpsOperatingVoltage() {
        this.bitField0_ &= -524289;
        this.gpsOperatingVoltage_ = 0.0d;
    }

    @Override // com.android.internal.os.PowerProfileProtoOrBuilder
    public boolean hasBluetoothOn() {
        return (this.bitField0_ & 1048576) == 1048576;
    }

    @Override // com.android.internal.os.PowerProfileProtoOrBuilder
    public double getBluetoothOn() {
        return this.bluetoothOn_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setBluetoothOn(double value) {
        this.bitField0_ |= 1048576;
        this.bluetoothOn_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearBluetoothOn() {
        this.bitField0_ &= -1048577;
        this.bluetoothOn_ = 0.0d;
    }

    @Override // com.android.internal.os.PowerProfileProtoOrBuilder
    public boolean hasBluetoothActive() {
        return (this.bitField0_ & 2097152) == 2097152;
    }

    @Override // com.android.internal.os.PowerProfileProtoOrBuilder
    public double getBluetoothActive() {
        return this.bluetoothActive_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setBluetoothActive(double value) {
        this.bitField0_ |= 2097152;
        this.bluetoothActive_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearBluetoothActive() {
        this.bitField0_ &= -2097153;
        this.bluetoothActive_ = 0.0d;
    }

    @Override // com.android.internal.os.PowerProfileProtoOrBuilder
    public boolean hasBluetoothAtCmd() {
        return (this.bitField0_ & 4194304) == 4194304;
    }

    @Override // com.android.internal.os.PowerProfileProtoOrBuilder
    public double getBluetoothAtCmd() {
        return this.bluetoothAtCmd_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setBluetoothAtCmd(double value) {
        this.bitField0_ |= 4194304;
        this.bluetoothAtCmd_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearBluetoothAtCmd() {
        this.bitField0_ &= -4194305;
        this.bluetoothAtCmd_ = 0.0d;
    }

    @Override // com.android.internal.os.PowerProfileProtoOrBuilder
    public boolean hasAmbientDisplay() {
        return (this.bitField0_ & 8388608) == 8388608;
    }

    @Override // com.android.internal.os.PowerProfileProtoOrBuilder
    public double getAmbientDisplay() {
        return this.ambientDisplay_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setAmbientDisplay(double value) {
        this.bitField0_ |= 8388608;
        this.ambientDisplay_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearAmbientDisplay() {
        this.bitField0_ &= -8388609;
        this.ambientDisplay_ = 0.0d;
    }

    @Override // com.android.internal.os.PowerProfileProtoOrBuilder
    public boolean hasScreenOn() {
        return (this.bitField0_ & 16777216) == 16777216;
    }

    @Override // com.android.internal.os.PowerProfileProtoOrBuilder
    public double getScreenOn() {
        return this.screenOn_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setScreenOn(double value) {
        this.bitField0_ |= 16777216;
        this.screenOn_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearScreenOn() {
        this.bitField0_ &= -16777217;
        this.screenOn_ = 0.0d;
    }

    @Override // com.android.internal.os.PowerProfileProtoOrBuilder
    public boolean hasRadioOn() {
        return (this.bitField0_ & 33554432) == 33554432;
    }

    @Override // com.android.internal.os.PowerProfileProtoOrBuilder
    public double getRadioOn() {
        return this.radioOn_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setRadioOn(double value) {
        this.bitField0_ |= 33554432;
        this.radioOn_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearRadioOn() {
        this.bitField0_ &= -33554433;
        this.radioOn_ = 0.0d;
    }

    @Override // com.android.internal.os.PowerProfileProtoOrBuilder
    public boolean hasRadioScanning() {
        return (this.bitField0_ & 67108864) == 67108864;
    }

    @Override // com.android.internal.os.PowerProfileProtoOrBuilder
    public double getRadioScanning() {
        return this.radioScanning_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setRadioScanning(double value) {
        this.bitField0_ |= 67108864;
        this.radioScanning_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearRadioScanning() {
        this.bitField0_ &= -67108865;
        this.radioScanning_ = 0.0d;
    }

    @Override // com.android.internal.os.PowerProfileProtoOrBuilder
    public boolean hasRadioActive() {
        return (this.bitField0_ & 134217728) == 134217728;
    }

    @Override // com.android.internal.os.PowerProfileProtoOrBuilder
    public double getRadioActive() {
        return this.radioActive_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setRadioActive(double value) {
        this.bitField0_ |= 134217728;
        this.radioActive_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearRadioActive() {
        this.bitField0_ &= -134217729;
        this.radioActive_ = 0.0d;
    }

    @Override // com.android.internal.os.PowerProfileProtoOrBuilder
    public boolean hasScreenFull() {
        return (this.bitField0_ & 268435456) == 268435456;
    }

    @Override // com.android.internal.os.PowerProfileProtoOrBuilder
    public double getScreenFull() {
        return this.screenFull_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setScreenFull(double value) {
        this.bitField0_ |= 268435456;
        this.screenFull_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearScreenFull() {
        this.bitField0_ &= -268435457;
        this.screenFull_ = 0.0d;
    }

    @Override // com.android.internal.os.PowerProfileProtoOrBuilder
    public boolean hasAudio() {
        return (this.bitField0_ & 536870912) == 536870912;
    }

    @Override // com.android.internal.os.PowerProfileProtoOrBuilder
    public double getAudio() {
        return this.audio_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setAudio(double value) {
        this.bitField0_ |= 536870912;
        this.audio_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearAudio() {
        this.bitField0_ &= -536870913;
        this.audio_ = 0.0d;
    }

    @Override // com.android.internal.os.PowerProfileProtoOrBuilder
    public boolean hasVideo() {
        return (this.bitField0_ & 1073741824) == 1073741824;
    }

    @Override // com.android.internal.os.PowerProfileProtoOrBuilder
    public double getVideo() {
        return this.video_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setVideo(double value) {
        this.bitField0_ |= 1073741824;
        this.video_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearVideo() {
        this.bitField0_ &= -1073741825;
        this.video_ = 0.0d;
    }

    @Override // com.android.internal.os.PowerProfileProtoOrBuilder
    public boolean hasFlashlight() {
        return (this.bitField0_ & Integer.MIN_VALUE) == Integer.MIN_VALUE;
    }

    @Override // com.android.internal.os.PowerProfileProtoOrBuilder
    public double getFlashlight() {
        return this.flashlight_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setFlashlight(double value) {
        this.bitField0_ |= Integer.MIN_VALUE;
        this.flashlight_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearFlashlight() {
        this.bitField0_ &= DataConnectionPowerStateEnum.DATA_CONNECTION_POWER_STATE_UNKNOWN_VALUE;
        this.flashlight_ = 0.0d;
    }

    @Override // com.android.internal.os.PowerProfileProtoOrBuilder
    public boolean hasMemory() {
        return (this.bitField1_ & 1) == 1;
    }

    @Override // com.android.internal.os.PowerProfileProtoOrBuilder
    public double getMemory() {
        return this.memory_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setMemory(double value) {
        this.bitField1_ |= 1;
        this.memory_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearMemory() {
        this.bitField1_ &= -2;
        this.memory_ = 0.0d;
    }

    @Override // com.android.internal.os.PowerProfileProtoOrBuilder
    public boolean hasCamera() {
        return (this.bitField1_ & 2) == 2;
    }

    @Override // com.android.internal.os.PowerProfileProtoOrBuilder
    public double getCamera() {
        return this.camera_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setCamera(double value) {
        this.bitField1_ |= 2;
        this.camera_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearCamera() {
        this.bitField1_ &= -3;
        this.camera_ = 0.0d;
    }

    @Override // com.android.internal.os.PowerProfileProtoOrBuilder
    public boolean hasWifiBatchedScan() {
        return (this.bitField1_ & 4) == 4;
    }

    @Override // com.android.internal.os.PowerProfileProtoOrBuilder
    public double getWifiBatchedScan() {
        return this.wifiBatchedScan_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setWifiBatchedScan(double value) {
        this.bitField1_ |= 4;
        this.wifiBatchedScan_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearWifiBatchedScan() {
        this.bitField1_ &= -5;
        this.wifiBatchedScan_ = 0.0d;
    }

    @Override // com.android.internal.os.PowerProfileProtoOrBuilder
    public boolean hasBatteryCapacity() {
        return (this.bitField1_ & 8) == 8;
    }

    @Override // com.android.internal.os.PowerProfileProtoOrBuilder
    public double getBatteryCapacity() {
        return this.batteryCapacity_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setBatteryCapacity(double value) {
        this.bitField1_ |= 8;
        this.batteryCapacity_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearBatteryCapacity() {
        this.bitField1_ &= -9;
        this.batteryCapacity_ = 0.0d;
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeDouble(1, this.cpuSuspend_);
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeDouble(2, this.cpuIdle_);
        }
        if ((this.bitField0_ & 4) == 4) {
            output.writeDouble(3, this.cpuActive_);
        }
        if ((this.bitField0_ & 8) == 8) {
            output.writeDouble(4, this.wifiScan_);
        }
        if ((this.bitField0_ & 16) == 16) {
            output.writeDouble(5, this.wifiOn_);
        }
        if ((this.bitField0_ & 32) == 32) {
            output.writeDouble(6, this.wifiActive_);
        }
        if ((this.bitField0_ & 64) == 64) {
            output.writeDouble(7, this.wifiControllerIdle_);
        }
        if ((this.bitField0_ & 128) == 128) {
            output.writeDouble(8, this.wifiControllerRx_);
        }
        if ((this.bitField0_ & 256) == 256) {
            output.writeDouble(9, this.wifiControllerTx_);
        }
        for (int i = 0; i < this.wifiControllerTxLevels_.size(); i++) {
            output.writeDouble(10, this.wifiControllerTxLevels_.getDouble(i));
        }
        if ((this.bitField0_ & 512) == 512) {
            output.writeDouble(11, this.wifiControllerOperatingVoltage_);
        }
        if ((this.bitField0_ & 1024) == 1024) {
            output.writeDouble(12, this.bluetoothControllerIdle_);
        }
        if ((this.bitField0_ & 2048) == 2048) {
            output.writeDouble(13, this.bluetoothControllerRx_);
        }
        if ((this.bitField0_ & 4096) == 4096) {
            output.writeDouble(14, this.bluetoothControllerTx_);
        }
        if ((this.bitField0_ & 8192) == 8192) {
            output.writeDouble(15, this.bluetoothControllerOperatingVoltage_);
        }
        if ((this.bitField0_ & 16384) == 16384) {
            output.writeDouble(16, this.modemControllerSleep_);
        }
        if ((this.bitField0_ & 32768) == 32768) {
            output.writeDouble(17, this.modemControllerIdle_);
        }
        if ((this.bitField0_ & 65536) == 65536) {
            output.writeDouble(18, this.modemControllerRx_);
        }
        for (int i2 = 0; i2 < this.modemControllerTx_.size(); i2++) {
            output.writeDouble(19, this.modemControllerTx_.getDouble(i2));
        }
        if ((this.bitField0_ & 131072) == 131072) {
            output.writeDouble(20, this.modemControllerOperatingVoltage_);
        }
        if ((this.bitField0_ & 262144) == 262144) {
            output.writeDouble(21, this.gpsOn_);
        }
        for (int i3 = 0; i3 < this.gpsSignalQualityBased_.size(); i3++) {
            output.writeDouble(22, this.gpsSignalQualityBased_.getDouble(i3));
        }
        if ((this.bitField0_ & 524288) == 524288) {
            output.writeDouble(23, this.gpsOperatingVoltage_);
        }
        if ((this.bitField0_ & 1048576) == 1048576) {
            output.writeDouble(24, this.bluetoothOn_);
        }
        if ((this.bitField0_ & 2097152) == 2097152) {
            output.writeDouble(25, this.bluetoothActive_);
        }
        if ((this.bitField0_ & 4194304) == 4194304) {
            output.writeDouble(26, this.bluetoothAtCmd_);
        }
        if ((this.bitField0_ & 8388608) == 8388608) {
            output.writeDouble(27, this.ambientDisplay_);
        }
        if ((this.bitField0_ & 16777216) == 16777216) {
            output.writeDouble(28, this.screenOn_);
        }
        if ((this.bitField0_ & 33554432) == 33554432) {
            output.writeDouble(29, this.radioOn_);
        }
        if ((this.bitField0_ & 67108864) == 67108864) {
            output.writeDouble(30, this.radioScanning_);
        }
        if ((this.bitField0_ & 134217728) == 134217728) {
            output.writeDouble(31, this.radioActive_);
        }
        if ((this.bitField0_ & 268435456) == 268435456) {
            output.writeDouble(32, this.screenFull_);
        }
        if ((this.bitField0_ & 536870912) == 536870912) {
            output.writeDouble(33, this.audio_);
        }
        if ((this.bitField0_ & 1073741824) == 1073741824) {
            output.writeDouble(34, this.video_);
        }
        if ((this.bitField0_ & Integer.MIN_VALUE) == Integer.MIN_VALUE) {
            output.writeDouble(35, this.flashlight_);
        }
        if ((this.bitField1_ & 1) == 1) {
            output.writeDouble(36, this.memory_);
        }
        if ((this.bitField1_ & 2) == 2) {
            output.writeDouble(37, this.camera_);
        }
        if ((this.bitField1_ & 4) == 4) {
            output.writeDouble(38, this.wifiBatchedScan_);
        }
        if ((this.bitField1_ & 8) == 8) {
            output.writeDouble(39, this.batteryCapacity_);
        }
        for (int i4 = 0; i4 < this.cpuCluster_.size(); i4++) {
            output.writeMessage(40, this.cpuCluster_.get(i4));
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
            size2 = 0 + CodedOutputStream.computeDoubleSize(1, this.cpuSuspend_);
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeDoubleSize(2, this.cpuIdle_);
        }
        if ((this.bitField0_ & 4) == 4) {
            size2 += CodedOutputStream.computeDoubleSize(3, this.cpuActive_);
        }
        if ((this.bitField0_ & 8) == 8) {
            size2 += CodedOutputStream.computeDoubleSize(4, this.wifiScan_);
        }
        if ((this.bitField0_ & 16) == 16) {
            size2 += CodedOutputStream.computeDoubleSize(5, this.wifiOn_);
        }
        if ((this.bitField0_ & 32) == 32) {
            size2 += CodedOutputStream.computeDoubleSize(6, this.wifiActive_);
        }
        if ((this.bitField0_ & 64) == 64) {
            size2 += CodedOutputStream.computeDoubleSize(7, this.wifiControllerIdle_);
        }
        if ((this.bitField0_ & 128) == 128) {
            size2 += CodedOutputStream.computeDoubleSize(8, this.wifiControllerRx_);
        }
        if ((this.bitField0_ & 256) == 256) {
            size2 += CodedOutputStream.computeDoubleSize(9, this.wifiControllerTx_);
        }
        int size3 = size2 + (getWifiControllerTxLevelsList().size() * 8) + (getWifiControllerTxLevelsList().size() * 1);
        if ((this.bitField0_ & 512) == 512) {
            size3 += CodedOutputStream.computeDoubleSize(11, this.wifiControllerOperatingVoltage_);
        }
        if ((this.bitField0_ & 1024) == 1024) {
            size3 += CodedOutputStream.computeDoubleSize(12, this.bluetoothControllerIdle_);
        }
        if ((this.bitField0_ & 2048) == 2048) {
            size3 += CodedOutputStream.computeDoubleSize(13, this.bluetoothControllerRx_);
        }
        if ((this.bitField0_ & 4096) == 4096) {
            size3 += CodedOutputStream.computeDoubleSize(14, this.bluetoothControllerTx_);
        }
        if ((this.bitField0_ & 8192) == 8192) {
            size3 += CodedOutputStream.computeDoubleSize(15, this.bluetoothControllerOperatingVoltage_);
        }
        if ((this.bitField0_ & 16384) == 16384) {
            size3 += CodedOutputStream.computeDoubleSize(16, this.modemControllerSleep_);
        }
        if ((this.bitField0_ & 32768) == 32768) {
            size3 += CodedOutputStream.computeDoubleSize(17, this.modemControllerIdle_);
        }
        if ((this.bitField0_ & 65536) == 65536) {
            size3 += CodedOutputStream.computeDoubleSize(18, this.modemControllerRx_);
        }
        int size4 = size3 + (getModemControllerTxList().size() * 8) + (getModemControllerTxList().size() * 2);
        if ((this.bitField0_ & 131072) == 131072) {
            size4 += CodedOutputStream.computeDoubleSize(20, this.modemControllerOperatingVoltage_);
        }
        if ((this.bitField0_ & 262144) == 262144) {
            size4 += CodedOutputStream.computeDoubleSize(21, this.gpsOn_);
        }
        int size5 = size4 + (getGpsSignalQualityBasedList().size() * 8) + (getGpsSignalQualityBasedList().size() * 2);
        if ((this.bitField0_ & 524288) == 524288) {
            size5 += CodedOutputStream.computeDoubleSize(23, this.gpsOperatingVoltage_);
        }
        if ((this.bitField0_ & 1048576) == 1048576) {
            size5 += CodedOutputStream.computeDoubleSize(24, this.bluetoothOn_);
        }
        if ((this.bitField0_ & 2097152) == 2097152) {
            size5 += CodedOutputStream.computeDoubleSize(25, this.bluetoothActive_);
        }
        if ((this.bitField0_ & 4194304) == 4194304) {
            size5 += CodedOutputStream.computeDoubleSize(26, this.bluetoothAtCmd_);
        }
        if ((this.bitField0_ & 8388608) == 8388608) {
            size5 += CodedOutputStream.computeDoubleSize(27, this.ambientDisplay_);
        }
        if ((this.bitField0_ & 16777216) == 16777216) {
            size5 += CodedOutputStream.computeDoubleSize(28, this.screenOn_);
        }
        if ((this.bitField0_ & 33554432) == 33554432) {
            size5 += CodedOutputStream.computeDoubleSize(29, this.radioOn_);
        }
        if ((this.bitField0_ & 67108864) == 67108864) {
            size5 += CodedOutputStream.computeDoubleSize(30, this.radioScanning_);
        }
        if ((this.bitField0_ & 134217728) == 134217728) {
            size5 += CodedOutputStream.computeDoubleSize(31, this.radioActive_);
        }
        if ((this.bitField0_ & 268435456) == 268435456) {
            size5 += CodedOutputStream.computeDoubleSize(32, this.screenFull_);
        }
        if ((this.bitField0_ & 536870912) == 536870912) {
            size5 += CodedOutputStream.computeDoubleSize(33, this.audio_);
        }
        if ((this.bitField0_ & 1073741824) == 1073741824) {
            size5 += CodedOutputStream.computeDoubleSize(34, this.video_);
        }
        if ((this.bitField0_ & Integer.MIN_VALUE) == Integer.MIN_VALUE) {
            size5 += CodedOutputStream.computeDoubleSize(35, this.flashlight_);
        }
        if ((this.bitField1_ & 1) == 1) {
            size5 += CodedOutputStream.computeDoubleSize(36, this.memory_);
        }
        if ((this.bitField1_ & 2) == 2) {
            size5 += CodedOutputStream.computeDoubleSize(37, this.camera_);
        }
        if ((this.bitField1_ & 4) == 4) {
            size5 += CodedOutputStream.computeDoubleSize(38, this.wifiBatchedScan_);
        }
        if ((this.bitField1_ & 8) == 8) {
            size5 += CodedOutputStream.computeDoubleSize(39, this.batteryCapacity_);
        }
        for (int i = 0; i < this.cpuCluster_.size(); i++) {
            size5 += CodedOutputStream.computeMessageSize(40, this.cpuCluster_.get(i));
        }
        int size6 = size5 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size6;
        return size6;
    }

    public static PowerProfileProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (PowerProfileProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static PowerProfileProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (PowerProfileProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static PowerProfileProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (PowerProfileProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static PowerProfileProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (PowerProfileProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static PowerProfileProto parseFrom(InputStream input) throws IOException {
        return (PowerProfileProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static PowerProfileProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (PowerProfileProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static PowerProfileProto parseDelimitedFrom(InputStream input) throws IOException {
        return (PowerProfileProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static PowerProfileProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (PowerProfileProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static PowerProfileProto parseFrom(CodedInputStream input) throws IOException {
        return (PowerProfileProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static PowerProfileProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (PowerProfileProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(PowerProfileProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<PowerProfileProto, Builder> implements PowerProfileProtoOrBuilder {
        private Builder() {
            super(PowerProfileProto.DEFAULT_INSTANCE);
        }

        @Override // com.android.internal.os.PowerProfileProtoOrBuilder
        public boolean hasCpuSuspend() {
            return ((PowerProfileProto) this.instance).hasCpuSuspend();
        }

        @Override // com.android.internal.os.PowerProfileProtoOrBuilder
        public double getCpuSuspend() {
            return ((PowerProfileProto) this.instance).getCpuSuspend();
        }

        public Builder setCpuSuspend(double value) {
            copyOnWrite();
            ((PowerProfileProto) this.instance).setCpuSuspend(value);
            return this;
        }

        public Builder clearCpuSuspend() {
            copyOnWrite();
            ((PowerProfileProto) this.instance).clearCpuSuspend();
            return this;
        }

        @Override // com.android.internal.os.PowerProfileProtoOrBuilder
        public boolean hasCpuIdle() {
            return ((PowerProfileProto) this.instance).hasCpuIdle();
        }

        @Override // com.android.internal.os.PowerProfileProtoOrBuilder
        public double getCpuIdle() {
            return ((PowerProfileProto) this.instance).getCpuIdle();
        }

        public Builder setCpuIdle(double value) {
            copyOnWrite();
            ((PowerProfileProto) this.instance).setCpuIdle(value);
            return this;
        }

        public Builder clearCpuIdle() {
            copyOnWrite();
            ((PowerProfileProto) this.instance).clearCpuIdle();
            return this;
        }

        @Override // com.android.internal.os.PowerProfileProtoOrBuilder
        public boolean hasCpuActive() {
            return ((PowerProfileProto) this.instance).hasCpuActive();
        }

        @Override // com.android.internal.os.PowerProfileProtoOrBuilder
        public double getCpuActive() {
            return ((PowerProfileProto) this.instance).getCpuActive();
        }

        public Builder setCpuActive(double value) {
            copyOnWrite();
            ((PowerProfileProto) this.instance).setCpuActive(value);
            return this;
        }

        public Builder clearCpuActive() {
            copyOnWrite();
            ((PowerProfileProto) this.instance).clearCpuActive();
            return this;
        }

        @Override // com.android.internal.os.PowerProfileProtoOrBuilder
        public List<CpuCluster> getCpuClusterList() {
            return Collections.unmodifiableList(((PowerProfileProto) this.instance).getCpuClusterList());
        }

        @Override // com.android.internal.os.PowerProfileProtoOrBuilder
        public int getCpuClusterCount() {
            return ((PowerProfileProto) this.instance).getCpuClusterCount();
        }

        @Override // com.android.internal.os.PowerProfileProtoOrBuilder
        public CpuCluster getCpuCluster(int index) {
            return ((PowerProfileProto) this.instance).getCpuCluster(index);
        }

        public Builder setCpuCluster(int index, CpuCluster value) {
            copyOnWrite();
            ((PowerProfileProto) this.instance).setCpuCluster((PowerProfileProto) index, (int) value);
            return this;
        }

        public Builder setCpuCluster(int index, CpuCluster.Builder builderForValue) {
            copyOnWrite();
            ((PowerProfileProto) this.instance).setCpuCluster((PowerProfileProto) index, (int) builderForValue);
            return this;
        }

        public Builder addCpuCluster(CpuCluster value) {
            copyOnWrite();
            ((PowerProfileProto) this.instance).addCpuCluster((PowerProfileProto) value);
            return this;
        }

        public Builder addCpuCluster(int index, CpuCluster value) {
            copyOnWrite();
            ((PowerProfileProto) this.instance).addCpuCluster((PowerProfileProto) index, (int) value);
            return this;
        }

        public Builder addCpuCluster(CpuCluster.Builder builderForValue) {
            copyOnWrite();
            ((PowerProfileProto) this.instance).addCpuCluster((PowerProfileProto) builderForValue);
            return this;
        }

        public Builder addCpuCluster(int index, CpuCluster.Builder builderForValue) {
            copyOnWrite();
            ((PowerProfileProto) this.instance).addCpuCluster((PowerProfileProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllCpuCluster(Iterable<? extends CpuCluster> values) {
            copyOnWrite();
            ((PowerProfileProto) this.instance).addAllCpuCluster(values);
            return this;
        }

        public Builder clearCpuCluster() {
            copyOnWrite();
            ((PowerProfileProto) this.instance).clearCpuCluster();
            return this;
        }

        public Builder removeCpuCluster(int index) {
            copyOnWrite();
            ((PowerProfileProto) this.instance).removeCpuCluster(index);
            return this;
        }

        @Override // com.android.internal.os.PowerProfileProtoOrBuilder
        public boolean hasWifiScan() {
            return ((PowerProfileProto) this.instance).hasWifiScan();
        }

        @Override // com.android.internal.os.PowerProfileProtoOrBuilder
        public double getWifiScan() {
            return ((PowerProfileProto) this.instance).getWifiScan();
        }

        public Builder setWifiScan(double value) {
            copyOnWrite();
            ((PowerProfileProto) this.instance).setWifiScan(value);
            return this;
        }

        public Builder clearWifiScan() {
            copyOnWrite();
            ((PowerProfileProto) this.instance).clearWifiScan();
            return this;
        }

        @Override // com.android.internal.os.PowerProfileProtoOrBuilder
        public boolean hasWifiOn() {
            return ((PowerProfileProto) this.instance).hasWifiOn();
        }

        @Override // com.android.internal.os.PowerProfileProtoOrBuilder
        public double getWifiOn() {
            return ((PowerProfileProto) this.instance).getWifiOn();
        }

        public Builder setWifiOn(double value) {
            copyOnWrite();
            ((PowerProfileProto) this.instance).setWifiOn(value);
            return this;
        }

        public Builder clearWifiOn() {
            copyOnWrite();
            ((PowerProfileProto) this.instance).clearWifiOn();
            return this;
        }

        @Override // com.android.internal.os.PowerProfileProtoOrBuilder
        public boolean hasWifiActive() {
            return ((PowerProfileProto) this.instance).hasWifiActive();
        }

        @Override // com.android.internal.os.PowerProfileProtoOrBuilder
        public double getWifiActive() {
            return ((PowerProfileProto) this.instance).getWifiActive();
        }

        public Builder setWifiActive(double value) {
            copyOnWrite();
            ((PowerProfileProto) this.instance).setWifiActive(value);
            return this;
        }

        public Builder clearWifiActive() {
            copyOnWrite();
            ((PowerProfileProto) this.instance).clearWifiActive();
            return this;
        }

        @Override // com.android.internal.os.PowerProfileProtoOrBuilder
        public boolean hasWifiControllerIdle() {
            return ((PowerProfileProto) this.instance).hasWifiControllerIdle();
        }

        @Override // com.android.internal.os.PowerProfileProtoOrBuilder
        public double getWifiControllerIdle() {
            return ((PowerProfileProto) this.instance).getWifiControllerIdle();
        }

        public Builder setWifiControllerIdle(double value) {
            copyOnWrite();
            ((PowerProfileProto) this.instance).setWifiControllerIdle(value);
            return this;
        }

        public Builder clearWifiControllerIdle() {
            copyOnWrite();
            ((PowerProfileProto) this.instance).clearWifiControllerIdle();
            return this;
        }

        @Override // com.android.internal.os.PowerProfileProtoOrBuilder
        public boolean hasWifiControllerRx() {
            return ((PowerProfileProto) this.instance).hasWifiControllerRx();
        }

        @Override // com.android.internal.os.PowerProfileProtoOrBuilder
        public double getWifiControllerRx() {
            return ((PowerProfileProto) this.instance).getWifiControllerRx();
        }

        public Builder setWifiControllerRx(double value) {
            copyOnWrite();
            ((PowerProfileProto) this.instance).setWifiControllerRx(value);
            return this;
        }

        public Builder clearWifiControllerRx() {
            copyOnWrite();
            ((PowerProfileProto) this.instance).clearWifiControllerRx();
            return this;
        }

        @Override // com.android.internal.os.PowerProfileProtoOrBuilder
        public boolean hasWifiControllerTx() {
            return ((PowerProfileProto) this.instance).hasWifiControllerTx();
        }

        @Override // com.android.internal.os.PowerProfileProtoOrBuilder
        public double getWifiControllerTx() {
            return ((PowerProfileProto) this.instance).getWifiControllerTx();
        }

        public Builder setWifiControllerTx(double value) {
            copyOnWrite();
            ((PowerProfileProto) this.instance).setWifiControllerTx(value);
            return this;
        }

        public Builder clearWifiControllerTx() {
            copyOnWrite();
            ((PowerProfileProto) this.instance).clearWifiControllerTx();
            return this;
        }

        @Override // com.android.internal.os.PowerProfileProtoOrBuilder
        public List<Double> getWifiControllerTxLevelsList() {
            return Collections.unmodifiableList(((PowerProfileProto) this.instance).getWifiControllerTxLevelsList());
        }

        @Override // com.android.internal.os.PowerProfileProtoOrBuilder
        public int getWifiControllerTxLevelsCount() {
            return ((PowerProfileProto) this.instance).getWifiControllerTxLevelsCount();
        }

        @Override // com.android.internal.os.PowerProfileProtoOrBuilder
        public double getWifiControllerTxLevels(int index) {
            return ((PowerProfileProto) this.instance).getWifiControllerTxLevels(index);
        }

        public Builder setWifiControllerTxLevels(int index, double value) {
            copyOnWrite();
            ((PowerProfileProto) this.instance).setWifiControllerTxLevels(index, value);
            return this;
        }

        public Builder addWifiControllerTxLevels(double value) {
            copyOnWrite();
            ((PowerProfileProto) this.instance).addWifiControllerTxLevels(value);
            return this;
        }

        public Builder addAllWifiControllerTxLevels(Iterable<? extends Double> values) {
            copyOnWrite();
            ((PowerProfileProto) this.instance).addAllWifiControllerTxLevels(values);
            return this;
        }

        public Builder clearWifiControllerTxLevels() {
            copyOnWrite();
            ((PowerProfileProto) this.instance).clearWifiControllerTxLevels();
            return this;
        }

        @Override // com.android.internal.os.PowerProfileProtoOrBuilder
        public boolean hasWifiControllerOperatingVoltage() {
            return ((PowerProfileProto) this.instance).hasWifiControllerOperatingVoltage();
        }

        @Override // com.android.internal.os.PowerProfileProtoOrBuilder
        public double getWifiControllerOperatingVoltage() {
            return ((PowerProfileProto) this.instance).getWifiControllerOperatingVoltage();
        }

        public Builder setWifiControllerOperatingVoltage(double value) {
            copyOnWrite();
            ((PowerProfileProto) this.instance).setWifiControllerOperatingVoltage(value);
            return this;
        }

        public Builder clearWifiControllerOperatingVoltage() {
            copyOnWrite();
            ((PowerProfileProto) this.instance).clearWifiControllerOperatingVoltage();
            return this;
        }

        @Override // com.android.internal.os.PowerProfileProtoOrBuilder
        public boolean hasBluetoothControllerIdle() {
            return ((PowerProfileProto) this.instance).hasBluetoothControllerIdle();
        }

        @Override // com.android.internal.os.PowerProfileProtoOrBuilder
        public double getBluetoothControllerIdle() {
            return ((PowerProfileProto) this.instance).getBluetoothControllerIdle();
        }

        public Builder setBluetoothControllerIdle(double value) {
            copyOnWrite();
            ((PowerProfileProto) this.instance).setBluetoothControllerIdle(value);
            return this;
        }

        public Builder clearBluetoothControllerIdle() {
            copyOnWrite();
            ((PowerProfileProto) this.instance).clearBluetoothControllerIdle();
            return this;
        }

        @Override // com.android.internal.os.PowerProfileProtoOrBuilder
        public boolean hasBluetoothControllerRx() {
            return ((PowerProfileProto) this.instance).hasBluetoothControllerRx();
        }

        @Override // com.android.internal.os.PowerProfileProtoOrBuilder
        public double getBluetoothControllerRx() {
            return ((PowerProfileProto) this.instance).getBluetoothControllerRx();
        }

        public Builder setBluetoothControllerRx(double value) {
            copyOnWrite();
            ((PowerProfileProto) this.instance).setBluetoothControllerRx(value);
            return this;
        }

        public Builder clearBluetoothControllerRx() {
            copyOnWrite();
            ((PowerProfileProto) this.instance).clearBluetoothControllerRx();
            return this;
        }

        @Override // com.android.internal.os.PowerProfileProtoOrBuilder
        public boolean hasBluetoothControllerTx() {
            return ((PowerProfileProto) this.instance).hasBluetoothControllerTx();
        }

        @Override // com.android.internal.os.PowerProfileProtoOrBuilder
        public double getBluetoothControllerTx() {
            return ((PowerProfileProto) this.instance).getBluetoothControllerTx();
        }

        public Builder setBluetoothControllerTx(double value) {
            copyOnWrite();
            ((PowerProfileProto) this.instance).setBluetoothControllerTx(value);
            return this;
        }

        public Builder clearBluetoothControllerTx() {
            copyOnWrite();
            ((PowerProfileProto) this.instance).clearBluetoothControllerTx();
            return this;
        }

        @Override // com.android.internal.os.PowerProfileProtoOrBuilder
        public boolean hasBluetoothControllerOperatingVoltage() {
            return ((PowerProfileProto) this.instance).hasBluetoothControllerOperatingVoltage();
        }

        @Override // com.android.internal.os.PowerProfileProtoOrBuilder
        public double getBluetoothControllerOperatingVoltage() {
            return ((PowerProfileProto) this.instance).getBluetoothControllerOperatingVoltage();
        }

        public Builder setBluetoothControllerOperatingVoltage(double value) {
            copyOnWrite();
            ((PowerProfileProto) this.instance).setBluetoothControllerOperatingVoltage(value);
            return this;
        }

        public Builder clearBluetoothControllerOperatingVoltage() {
            copyOnWrite();
            ((PowerProfileProto) this.instance).clearBluetoothControllerOperatingVoltage();
            return this;
        }

        @Override // com.android.internal.os.PowerProfileProtoOrBuilder
        public boolean hasModemControllerSleep() {
            return ((PowerProfileProto) this.instance).hasModemControllerSleep();
        }

        @Override // com.android.internal.os.PowerProfileProtoOrBuilder
        public double getModemControllerSleep() {
            return ((PowerProfileProto) this.instance).getModemControllerSleep();
        }

        public Builder setModemControllerSleep(double value) {
            copyOnWrite();
            ((PowerProfileProto) this.instance).setModemControllerSleep(value);
            return this;
        }

        public Builder clearModemControllerSleep() {
            copyOnWrite();
            ((PowerProfileProto) this.instance).clearModemControllerSleep();
            return this;
        }

        @Override // com.android.internal.os.PowerProfileProtoOrBuilder
        public boolean hasModemControllerIdle() {
            return ((PowerProfileProto) this.instance).hasModemControllerIdle();
        }

        @Override // com.android.internal.os.PowerProfileProtoOrBuilder
        public double getModemControllerIdle() {
            return ((PowerProfileProto) this.instance).getModemControllerIdle();
        }

        public Builder setModemControllerIdle(double value) {
            copyOnWrite();
            ((PowerProfileProto) this.instance).setModemControllerIdle(value);
            return this;
        }

        public Builder clearModemControllerIdle() {
            copyOnWrite();
            ((PowerProfileProto) this.instance).clearModemControllerIdle();
            return this;
        }

        @Override // com.android.internal.os.PowerProfileProtoOrBuilder
        public boolean hasModemControllerRx() {
            return ((PowerProfileProto) this.instance).hasModemControllerRx();
        }

        @Override // com.android.internal.os.PowerProfileProtoOrBuilder
        public double getModemControllerRx() {
            return ((PowerProfileProto) this.instance).getModemControllerRx();
        }

        public Builder setModemControllerRx(double value) {
            copyOnWrite();
            ((PowerProfileProto) this.instance).setModemControllerRx(value);
            return this;
        }

        public Builder clearModemControllerRx() {
            copyOnWrite();
            ((PowerProfileProto) this.instance).clearModemControllerRx();
            return this;
        }

        @Override // com.android.internal.os.PowerProfileProtoOrBuilder
        public List<Double> getModemControllerTxList() {
            return Collections.unmodifiableList(((PowerProfileProto) this.instance).getModemControllerTxList());
        }

        @Override // com.android.internal.os.PowerProfileProtoOrBuilder
        public int getModemControllerTxCount() {
            return ((PowerProfileProto) this.instance).getModemControllerTxCount();
        }

        @Override // com.android.internal.os.PowerProfileProtoOrBuilder
        public double getModemControllerTx(int index) {
            return ((PowerProfileProto) this.instance).getModemControllerTx(index);
        }

        public Builder setModemControllerTx(int index, double value) {
            copyOnWrite();
            ((PowerProfileProto) this.instance).setModemControllerTx(index, value);
            return this;
        }

        public Builder addModemControllerTx(double value) {
            copyOnWrite();
            ((PowerProfileProto) this.instance).addModemControllerTx(value);
            return this;
        }

        public Builder addAllModemControllerTx(Iterable<? extends Double> values) {
            copyOnWrite();
            ((PowerProfileProto) this.instance).addAllModemControllerTx(values);
            return this;
        }

        public Builder clearModemControllerTx() {
            copyOnWrite();
            ((PowerProfileProto) this.instance).clearModemControllerTx();
            return this;
        }

        @Override // com.android.internal.os.PowerProfileProtoOrBuilder
        public boolean hasModemControllerOperatingVoltage() {
            return ((PowerProfileProto) this.instance).hasModemControllerOperatingVoltage();
        }

        @Override // com.android.internal.os.PowerProfileProtoOrBuilder
        public double getModemControllerOperatingVoltage() {
            return ((PowerProfileProto) this.instance).getModemControllerOperatingVoltage();
        }

        public Builder setModemControllerOperatingVoltage(double value) {
            copyOnWrite();
            ((PowerProfileProto) this.instance).setModemControllerOperatingVoltage(value);
            return this;
        }

        public Builder clearModemControllerOperatingVoltage() {
            copyOnWrite();
            ((PowerProfileProto) this.instance).clearModemControllerOperatingVoltage();
            return this;
        }

        @Override // com.android.internal.os.PowerProfileProtoOrBuilder
        public boolean hasGpsOn() {
            return ((PowerProfileProto) this.instance).hasGpsOn();
        }

        @Override // com.android.internal.os.PowerProfileProtoOrBuilder
        public double getGpsOn() {
            return ((PowerProfileProto) this.instance).getGpsOn();
        }

        public Builder setGpsOn(double value) {
            copyOnWrite();
            ((PowerProfileProto) this.instance).setGpsOn(value);
            return this;
        }

        public Builder clearGpsOn() {
            copyOnWrite();
            ((PowerProfileProto) this.instance).clearGpsOn();
            return this;
        }

        @Override // com.android.internal.os.PowerProfileProtoOrBuilder
        public List<Double> getGpsSignalQualityBasedList() {
            return Collections.unmodifiableList(((PowerProfileProto) this.instance).getGpsSignalQualityBasedList());
        }

        @Override // com.android.internal.os.PowerProfileProtoOrBuilder
        public int getGpsSignalQualityBasedCount() {
            return ((PowerProfileProto) this.instance).getGpsSignalQualityBasedCount();
        }

        @Override // com.android.internal.os.PowerProfileProtoOrBuilder
        public double getGpsSignalQualityBased(int index) {
            return ((PowerProfileProto) this.instance).getGpsSignalQualityBased(index);
        }

        public Builder setGpsSignalQualityBased(int index, double value) {
            copyOnWrite();
            ((PowerProfileProto) this.instance).setGpsSignalQualityBased(index, value);
            return this;
        }

        public Builder addGpsSignalQualityBased(double value) {
            copyOnWrite();
            ((PowerProfileProto) this.instance).addGpsSignalQualityBased(value);
            return this;
        }

        public Builder addAllGpsSignalQualityBased(Iterable<? extends Double> values) {
            copyOnWrite();
            ((PowerProfileProto) this.instance).addAllGpsSignalQualityBased(values);
            return this;
        }

        public Builder clearGpsSignalQualityBased() {
            copyOnWrite();
            ((PowerProfileProto) this.instance).clearGpsSignalQualityBased();
            return this;
        }

        @Override // com.android.internal.os.PowerProfileProtoOrBuilder
        public boolean hasGpsOperatingVoltage() {
            return ((PowerProfileProto) this.instance).hasGpsOperatingVoltage();
        }

        @Override // com.android.internal.os.PowerProfileProtoOrBuilder
        public double getGpsOperatingVoltage() {
            return ((PowerProfileProto) this.instance).getGpsOperatingVoltage();
        }

        public Builder setGpsOperatingVoltage(double value) {
            copyOnWrite();
            ((PowerProfileProto) this.instance).setGpsOperatingVoltage(value);
            return this;
        }

        public Builder clearGpsOperatingVoltage() {
            copyOnWrite();
            ((PowerProfileProto) this.instance).clearGpsOperatingVoltage();
            return this;
        }

        @Override // com.android.internal.os.PowerProfileProtoOrBuilder
        public boolean hasBluetoothOn() {
            return ((PowerProfileProto) this.instance).hasBluetoothOn();
        }

        @Override // com.android.internal.os.PowerProfileProtoOrBuilder
        public double getBluetoothOn() {
            return ((PowerProfileProto) this.instance).getBluetoothOn();
        }

        public Builder setBluetoothOn(double value) {
            copyOnWrite();
            ((PowerProfileProto) this.instance).setBluetoothOn(value);
            return this;
        }

        public Builder clearBluetoothOn() {
            copyOnWrite();
            ((PowerProfileProto) this.instance).clearBluetoothOn();
            return this;
        }

        @Override // com.android.internal.os.PowerProfileProtoOrBuilder
        public boolean hasBluetoothActive() {
            return ((PowerProfileProto) this.instance).hasBluetoothActive();
        }

        @Override // com.android.internal.os.PowerProfileProtoOrBuilder
        public double getBluetoothActive() {
            return ((PowerProfileProto) this.instance).getBluetoothActive();
        }

        public Builder setBluetoothActive(double value) {
            copyOnWrite();
            ((PowerProfileProto) this.instance).setBluetoothActive(value);
            return this;
        }

        public Builder clearBluetoothActive() {
            copyOnWrite();
            ((PowerProfileProto) this.instance).clearBluetoothActive();
            return this;
        }

        @Override // com.android.internal.os.PowerProfileProtoOrBuilder
        public boolean hasBluetoothAtCmd() {
            return ((PowerProfileProto) this.instance).hasBluetoothAtCmd();
        }

        @Override // com.android.internal.os.PowerProfileProtoOrBuilder
        public double getBluetoothAtCmd() {
            return ((PowerProfileProto) this.instance).getBluetoothAtCmd();
        }

        public Builder setBluetoothAtCmd(double value) {
            copyOnWrite();
            ((PowerProfileProto) this.instance).setBluetoothAtCmd(value);
            return this;
        }

        public Builder clearBluetoothAtCmd() {
            copyOnWrite();
            ((PowerProfileProto) this.instance).clearBluetoothAtCmd();
            return this;
        }

        @Override // com.android.internal.os.PowerProfileProtoOrBuilder
        public boolean hasAmbientDisplay() {
            return ((PowerProfileProto) this.instance).hasAmbientDisplay();
        }

        @Override // com.android.internal.os.PowerProfileProtoOrBuilder
        public double getAmbientDisplay() {
            return ((PowerProfileProto) this.instance).getAmbientDisplay();
        }

        public Builder setAmbientDisplay(double value) {
            copyOnWrite();
            ((PowerProfileProto) this.instance).setAmbientDisplay(value);
            return this;
        }

        public Builder clearAmbientDisplay() {
            copyOnWrite();
            ((PowerProfileProto) this.instance).clearAmbientDisplay();
            return this;
        }

        @Override // com.android.internal.os.PowerProfileProtoOrBuilder
        public boolean hasScreenOn() {
            return ((PowerProfileProto) this.instance).hasScreenOn();
        }

        @Override // com.android.internal.os.PowerProfileProtoOrBuilder
        public double getScreenOn() {
            return ((PowerProfileProto) this.instance).getScreenOn();
        }

        public Builder setScreenOn(double value) {
            copyOnWrite();
            ((PowerProfileProto) this.instance).setScreenOn(value);
            return this;
        }

        public Builder clearScreenOn() {
            copyOnWrite();
            ((PowerProfileProto) this.instance).clearScreenOn();
            return this;
        }

        @Override // com.android.internal.os.PowerProfileProtoOrBuilder
        public boolean hasRadioOn() {
            return ((PowerProfileProto) this.instance).hasRadioOn();
        }

        @Override // com.android.internal.os.PowerProfileProtoOrBuilder
        public double getRadioOn() {
            return ((PowerProfileProto) this.instance).getRadioOn();
        }

        public Builder setRadioOn(double value) {
            copyOnWrite();
            ((PowerProfileProto) this.instance).setRadioOn(value);
            return this;
        }

        public Builder clearRadioOn() {
            copyOnWrite();
            ((PowerProfileProto) this.instance).clearRadioOn();
            return this;
        }

        @Override // com.android.internal.os.PowerProfileProtoOrBuilder
        public boolean hasRadioScanning() {
            return ((PowerProfileProto) this.instance).hasRadioScanning();
        }

        @Override // com.android.internal.os.PowerProfileProtoOrBuilder
        public double getRadioScanning() {
            return ((PowerProfileProto) this.instance).getRadioScanning();
        }

        public Builder setRadioScanning(double value) {
            copyOnWrite();
            ((PowerProfileProto) this.instance).setRadioScanning(value);
            return this;
        }

        public Builder clearRadioScanning() {
            copyOnWrite();
            ((PowerProfileProto) this.instance).clearRadioScanning();
            return this;
        }

        @Override // com.android.internal.os.PowerProfileProtoOrBuilder
        public boolean hasRadioActive() {
            return ((PowerProfileProto) this.instance).hasRadioActive();
        }

        @Override // com.android.internal.os.PowerProfileProtoOrBuilder
        public double getRadioActive() {
            return ((PowerProfileProto) this.instance).getRadioActive();
        }

        public Builder setRadioActive(double value) {
            copyOnWrite();
            ((PowerProfileProto) this.instance).setRadioActive(value);
            return this;
        }

        public Builder clearRadioActive() {
            copyOnWrite();
            ((PowerProfileProto) this.instance).clearRadioActive();
            return this;
        }

        @Override // com.android.internal.os.PowerProfileProtoOrBuilder
        public boolean hasScreenFull() {
            return ((PowerProfileProto) this.instance).hasScreenFull();
        }

        @Override // com.android.internal.os.PowerProfileProtoOrBuilder
        public double getScreenFull() {
            return ((PowerProfileProto) this.instance).getScreenFull();
        }

        public Builder setScreenFull(double value) {
            copyOnWrite();
            ((PowerProfileProto) this.instance).setScreenFull(value);
            return this;
        }

        public Builder clearScreenFull() {
            copyOnWrite();
            ((PowerProfileProto) this.instance).clearScreenFull();
            return this;
        }

        @Override // com.android.internal.os.PowerProfileProtoOrBuilder
        public boolean hasAudio() {
            return ((PowerProfileProto) this.instance).hasAudio();
        }

        @Override // com.android.internal.os.PowerProfileProtoOrBuilder
        public double getAudio() {
            return ((PowerProfileProto) this.instance).getAudio();
        }

        public Builder setAudio(double value) {
            copyOnWrite();
            ((PowerProfileProto) this.instance).setAudio(value);
            return this;
        }

        public Builder clearAudio() {
            copyOnWrite();
            ((PowerProfileProto) this.instance).clearAudio();
            return this;
        }

        @Override // com.android.internal.os.PowerProfileProtoOrBuilder
        public boolean hasVideo() {
            return ((PowerProfileProto) this.instance).hasVideo();
        }

        @Override // com.android.internal.os.PowerProfileProtoOrBuilder
        public double getVideo() {
            return ((PowerProfileProto) this.instance).getVideo();
        }

        public Builder setVideo(double value) {
            copyOnWrite();
            ((PowerProfileProto) this.instance).setVideo(value);
            return this;
        }

        public Builder clearVideo() {
            copyOnWrite();
            ((PowerProfileProto) this.instance).clearVideo();
            return this;
        }

        @Override // com.android.internal.os.PowerProfileProtoOrBuilder
        public boolean hasFlashlight() {
            return ((PowerProfileProto) this.instance).hasFlashlight();
        }

        @Override // com.android.internal.os.PowerProfileProtoOrBuilder
        public double getFlashlight() {
            return ((PowerProfileProto) this.instance).getFlashlight();
        }

        public Builder setFlashlight(double value) {
            copyOnWrite();
            ((PowerProfileProto) this.instance).setFlashlight(value);
            return this;
        }

        public Builder clearFlashlight() {
            copyOnWrite();
            ((PowerProfileProto) this.instance).clearFlashlight();
            return this;
        }

        @Override // com.android.internal.os.PowerProfileProtoOrBuilder
        public boolean hasMemory() {
            return ((PowerProfileProto) this.instance).hasMemory();
        }

        @Override // com.android.internal.os.PowerProfileProtoOrBuilder
        public double getMemory() {
            return ((PowerProfileProto) this.instance).getMemory();
        }

        public Builder setMemory(double value) {
            copyOnWrite();
            ((PowerProfileProto) this.instance).setMemory(value);
            return this;
        }

        public Builder clearMemory() {
            copyOnWrite();
            ((PowerProfileProto) this.instance).clearMemory();
            return this;
        }

        @Override // com.android.internal.os.PowerProfileProtoOrBuilder
        public boolean hasCamera() {
            return ((PowerProfileProto) this.instance).hasCamera();
        }

        @Override // com.android.internal.os.PowerProfileProtoOrBuilder
        public double getCamera() {
            return ((PowerProfileProto) this.instance).getCamera();
        }

        public Builder setCamera(double value) {
            copyOnWrite();
            ((PowerProfileProto) this.instance).setCamera(value);
            return this;
        }

        public Builder clearCamera() {
            copyOnWrite();
            ((PowerProfileProto) this.instance).clearCamera();
            return this;
        }

        @Override // com.android.internal.os.PowerProfileProtoOrBuilder
        public boolean hasWifiBatchedScan() {
            return ((PowerProfileProto) this.instance).hasWifiBatchedScan();
        }

        @Override // com.android.internal.os.PowerProfileProtoOrBuilder
        public double getWifiBatchedScan() {
            return ((PowerProfileProto) this.instance).getWifiBatchedScan();
        }

        public Builder setWifiBatchedScan(double value) {
            copyOnWrite();
            ((PowerProfileProto) this.instance).setWifiBatchedScan(value);
            return this;
        }

        public Builder clearWifiBatchedScan() {
            copyOnWrite();
            ((PowerProfileProto) this.instance).clearWifiBatchedScan();
            return this;
        }

        @Override // com.android.internal.os.PowerProfileProtoOrBuilder
        public boolean hasBatteryCapacity() {
            return ((PowerProfileProto) this.instance).hasBatteryCapacity();
        }

        @Override // com.android.internal.os.PowerProfileProtoOrBuilder
        public double getBatteryCapacity() {
            return ((PowerProfileProto) this.instance).getBatteryCapacity();
        }

        public Builder setBatteryCapacity(double value) {
            copyOnWrite();
            ((PowerProfileProto) this.instance).setBatteryCapacity(value);
            return this;
        }

        public Builder clearBatteryCapacity() {
            copyOnWrite();
            ((PowerProfileProto) this.instance).clearBatteryCapacity();
            return this;
        }
    }

    /* JADX WARN: Type inference failed for: r7v2, types: [com.google.protobuf.Internal$DoubleList] */
    /* JADX WARN: Type inference failed for: r7v5, types: [com.google.protobuf.Internal$DoubleList] */
    /* JADX WARN: Type inference failed for: r7v8, types: [com.google.protobuf.Internal$DoubleList] */
    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    /* JADX WARNING: Unknown variable types count: 3 */
    @Override // com.google.protobuf.GeneratedMessageLite
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object dynamicMethod(com.google.protobuf.GeneratedMessageLite.MethodToInvoke r10, java.lang.Object r11, java.lang.Object r12) {
        /*
        // Method dump skipped, instructions count: 1940
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.internal.os.PowerProfileProto.dynamicMethod(com.google.protobuf.GeneratedMessageLite$MethodToInvoke, java.lang.Object, java.lang.Object):java.lang.Object");
    }

    static {
        DEFAULT_INSTANCE.makeImmutable();
    }

    public static PowerProfileProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<PowerProfileProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
