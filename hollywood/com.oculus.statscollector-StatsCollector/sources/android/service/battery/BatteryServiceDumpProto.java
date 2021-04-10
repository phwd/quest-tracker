package android.service.battery;

import android.os.BatteryHealthEnum;
import android.os.BatteryPluggedStateEnum;
import android.os.BatteryStatusEnum;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;

public final class BatteryServiceDumpProto extends GeneratedMessageLite<BatteryServiceDumpProto, Builder> implements BatteryServiceDumpProtoOrBuilder {
    public static final int ARE_UPDATES_STOPPED_FIELD_NUMBER = 1;
    public static final int CHARGE_COUNTER_FIELD_NUMBER = 5;
    private static final BatteryServiceDumpProto DEFAULT_INSTANCE = new BatteryServiceDumpProto();
    public static final int HEALTH_FIELD_NUMBER = 7;
    public static final int IS_PRESENT_FIELD_NUMBER = 8;
    public static final int LEVEL_FIELD_NUMBER = 9;
    public static final int MAX_CHARGING_CURRENT_FIELD_NUMBER = 3;
    public static final int MAX_CHARGING_VOLTAGE_FIELD_NUMBER = 4;
    private static volatile Parser<BatteryServiceDumpProto> PARSER = null;
    public static final int PLUGGED_FIELD_NUMBER = 2;
    public static final int SCALE_FIELD_NUMBER = 10;
    public static final int STATUS_FIELD_NUMBER = 6;
    public static final int TECHNOLOGY_FIELD_NUMBER = 13;
    public static final int TEMPERATURE_FIELD_NUMBER = 12;
    public static final int VOLTAGE_FIELD_NUMBER = 11;
    private boolean areUpdatesStopped_ = false;
    private int bitField0_;
    private int chargeCounter_ = 0;
    private int health_ = 0;
    private boolean isPresent_ = false;
    private int level_ = 0;
    private int maxChargingCurrent_ = 0;
    private int maxChargingVoltage_ = 0;
    private int plugged_ = 0;
    private int scale_ = 0;
    private int status_ = 0;
    private String technology_ = "";
    private int temperature_ = 0;
    private int voltage_ = 0;

    private BatteryServiceDumpProto() {
    }

    @Override // android.service.battery.BatteryServiceDumpProtoOrBuilder
    public boolean hasAreUpdatesStopped() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // android.service.battery.BatteryServiceDumpProtoOrBuilder
    public boolean getAreUpdatesStopped() {
        return this.areUpdatesStopped_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setAreUpdatesStopped(boolean value) {
        this.bitField0_ |= 1;
        this.areUpdatesStopped_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearAreUpdatesStopped() {
        this.bitField0_ &= -2;
        this.areUpdatesStopped_ = false;
    }

    @Override // android.service.battery.BatteryServiceDumpProtoOrBuilder
    public boolean hasPlugged() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // android.service.battery.BatteryServiceDumpProtoOrBuilder
    public BatteryPluggedStateEnum getPlugged() {
        BatteryPluggedStateEnum result = BatteryPluggedStateEnum.forNumber(this.plugged_);
        return result == null ? BatteryPluggedStateEnum.BATTERY_PLUGGED_NONE : result;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPlugged(BatteryPluggedStateEnum value) {
        if (value != null) {
            this.bitField0_ |= 2;
            this.plugged_ = value.getNumber();
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearPlugged() {
        this.bitField0_ &= -3;
        this.plugged_ = 0;
    }

    @Override // android.service.battery.BatteryServiceDumpProtoOrBuilder
    public boolean hasMaxChargingCurrent() {
        return (this.bitField0_ & 4) == 4;
    }

    @Override // android.service.battery.BatteryServiceDumpProtoOrBuilder
    public int getMaxChargingCurrent() {
        return this.maxChargingCurrent_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setMaxChargingCurrent(int value) {
        this.bitField0_ |= 4;
        this.maxChargingCurrent_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearMaxChargingCurrent() {
        this.bitField0_ &= -5;
        this.maxChargingCurrent_ = 0;
    }

    @Override // android.service.battery.BatteryServiceDumpProtoOrBuilder
    public boolean hasMaxChargingVoltage() {
        return (this.bitField0_ & 8) == 8;
    }

    @Override // android.service.battery.BatteryServiceDumpProtoOrBuilder
    public int getMaxChargingVoltage() {
        return this.maxChargingVoltage_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setMaxChargingVoltage(int value) {
        this.bitField0_ |= 8;
        this.maxChargingVoltage_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearMaxChargingVoltage() {
        this.bitField0_ &= -9;
        this.maxChargingVoltage_ = 0;
    }

    @Override // android.service.battery.BatteryServiceDumpProtoOrBuilder
    public boolean hasChargeCounter() {
        return (this.bitField0_ & 16) == 16;
    }

    @Override // android.service.battery.BatteryServiceDumpProtoOrBuilder
    public int getChargeCounter() {
        return this.chargeCounter_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setChargeCounter(int value) {
        this.bitField0_ |= 16;
        this.chargeCounter_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearChargeCounter() {
        this.bitField0_ &= -17;
        this.chargeCounter_ = 0;
    }

    @Override // android.service.battery.BatteryServiceDumpProtoOrBuilder
    public boolean hasStatus() {
        return (this.bitField0_ & 32) == 32;
    }

    @Override // android.service.battery.BatteryServiceDumpProtoOrBuilder
    public BatteryStatusEnum getStatus() {
        BatteryStatusEnum result = BatteryStatusEnum.forNumber(this.status_);
        return result == null ? BatteryStatusEnum.BATTERY_STATUS_INVALID : result;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setStatus(BatteryStatusEnum value) {
        if (value != null) {
            this.bitField0_ |= 32;
            this.status_ = value.getNumber();
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearStatus() {
        this.bitField0_ &= -33;
        this.status_ = 0;
    }

    @Override // android.service.battery.BatteryServiceDumpProtoOrBuilder
    public boolean hasHealth() {
        return (this.bitField0_ & 64) == 64;
    }

    @Override // android.service.battery.BatteryServiceDumpProtoOrBuilder
    public BatteryHealthEnum getHealth() {
        BatteryHealthEnum result = BatteryHealthEnum.forNumber(this.health_);
        return result == null ? BatteryHealthEnum.BATTERY_HEALTH_INVALID : result;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setHealth(BatteryHealthEnum value) {
        if (value != null) {
            this.bitField0_ |= 64;
            this.health_ = value.getNumber();
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearHealth() {
        this.bitField0_ &= -65;
        this.health_ = 0;
    }

    @Override // android.service.battery.BatteryServiceDumpProtoOrBuilder
    public boolean hasIsPresent() {
        return (this.bitField0_ & 128) == 128;
    }

    @Override // android.service.battery.BatteryServiceDumpProtoOrBuilder
    public boolean getIsPresent() {
        return this.isPresent_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setIsPresent(boolean value) {
        this.bitField0_ |= 128;
        this.isPresent_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearIsPresent() {
        this.bitField0_ &= -129;
        this.isPresent_ = false;
    }

    @Override // android.service.battery.BatteryServiceDumpProtoOrBuilder
    public boolean hasLevel() {
        return (this.bitField0_ & 256) == 256;
    }

    @Override // android.service.battery.BatteryServiceDumpProtoOrBuilder
    public int getLevel() {
        return this.level_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setLevel(int value) {
        this.bitField0_ |= 256;
        this.level_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearLevel() {
        this.bitField0_ &= -257;
        this.level_ = 0;
    }

    @Override // android.service.battery.BatteryServiceDumpProtoOrBuilder
    public boolean hasScale() {
        return (this.bitField0_ & 512) == 512;
    }

    @Override // android.service.battery.BatteryServiceDumpProtoOrBuilder
    public int getScale() {
        return this.scale_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setScale(int value) {
        this.bitField0_ |= 512;
        this.scale_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearScale() {
        this.bitField0_ &= -513;
        this.scale_ = 0;
    }

    @Override // android.service.battery.BatteryServiceDumpProtoOrBuilder
    public boolean hasVoltage() {
        return (this.bitField0_ & 1024) == 1024;
    }

    @Override // android.service.battery.BatteryServiceDumpProtoOrBuilder
    public int getVoltage() {
        return this.voltage_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setVoltage(int value) {
        this.bitField0_ |= 1024;
        this.voltage_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearVoltage() {
        this.bitField0_ &= -1025;
        this.voltage_ = 0;
    }

    @Override // android.service.battery.BatteryServiceDumpProtoOrBuilder
    public boolean hasTemperature() {
        return (this.bitField0_ & 2048) == 2048;
    }

    @Override // android.service.battery.BatteryServiceDumpProtoOrBuilder
    public int getTemperature() {
        return this.temperature_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setTemperature(int value) {
        this.bitField0_ |= 2048;
        this.temperature_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearTemperature() {
        this.bitField0_ &= -2049;
        this.temperature_ = 0;
    }

    @Override // android.service.battery.BatteryServiceDumpProtoOrBuilder
    public boolean hasTechnology() {
        return (this.bitField0_ & 4096) == 4096;
    }

    @Override // android.service.battery.BatteryServiceDumpProtoOrBuilder
    public String getTechnology() {
        return this.technology_;
    }

    @Override // android.service.battery.BatteryServiceDumpProtoOrBuilder
    public ByteString getTechnologyBytes() {
        return ByteString.copyFromUtf8(this.technology_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setTechnology(String value) {
        if (value != null) {
            this.bitField0_ |= 4096;
            this.technology_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearTechnology() {
        this.bitField0_ &= -4097;
        this.technology_ = getDefaultInstance().getTechnology();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setTechnologyBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 4096;
            this.technology_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeBool(1, this.areUpdatesStopped_);
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeEnum(2, this.plugged_);
        }
        if ((this.bitField0_ & 4) == 4) {
            output.writeInt32(3, this.maxChargingCurrent_);
        }
        if ((this.bitField0_ & 8) == 8) {
            output.writeInt32(4, this.maxChargingVoltage_);
        }
        if ((this.bitField0_ & 16) == 16) {
            output.writeInt32(5, this.chargeCounter_);
        }
        if ((this.bitField0_ & 32) == 32) {
            output.writeEnum(6, this.status_);
        }
        if ((this.bitField0_ & 64) == 64) {
            output.writeEnum(7, this.health_);
        }
        if ((this.bitField0_ & 128) == 128) {
            output.writeBool(8, this.isPresent_);
        }
        if ((this.bitField0_ & 256) == 256) {
            output.writeInt32(9, this.level_);
        }
        if ((this.bitField0_ & 512) == 512) {
            output.writeInt32(10, this.scale_);
        }
        if ((this.bitField0_ & 1024) == 1024) {
            output.writeInt32(11, this.voltage_);
        }
        if ((this.bitField0_ & 2048) == 2048) {
            output.writeInt32(12, this.temperature_);
        }
        if ((this.bitField0_ & 4096) == 4096) {
            output.writeString(13, getTechnology());
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
            size2 = 0 + CodedOutputStream.computeBoolSize(1, this.areUpdatesStopped_);
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeEnumSize(2, this.plugged_);
        }
        if ((this.bitField0_ & 4) == 4) {
            size2 += CodedOutputStream.computeInt32Size(3, this.maxChargingCurrent_);
        }
        if ((this.bitField0_ & 8) == 8) {
            size2 += CodedOutputStream.computeInt32Size(4, this.maxChargingVoltage_);
        }
        if ((this.bitField0_ & 16) == 16) {
            size2 += CodedOutputStream.computeInt32Size(5, this.chargeCounter_);
        }
        if ((this.bitField0_ & 32) == 32) {
            size2 += CodedOutputStream.computeEnumSize(6, this.status_);
        }
        if ((this.bitField0_ & 64) == 64) {
            size2 += CodedOutputStream.computeEnumSize(7, this.health_);
        }
        if ((this.bitField0_ & 128) == 128) {
            size2 += CodedOutputStream.computeBoolSize(8, this.isPresent_);
        }
        if ((this.bitField0_ & 256) == 256) {
            size2 += CodedOutputStream.computeInt32Size(9, this.level_);
        }
        if ((this.bitField0_ & 512) == 512) {
            size2 += CodedOutputStream.computeInt32Size(10, this.scale_);
        }
        if ((this.bitField0_ & 1024) == 1024) {
            size2 += CodedOutputStream.computeInt32Size(11, this.voltage_);
        }
        if ((this.bitField0_ & 2048) == 2048) {
            size2 += CodedOutputStream.computeInt32Size(12, this.temperature_);
        }
        if ((this.bitField0_ & 4096) == 4096) {
            size2 += CodedOutputStream.computeStringSize(13, getTechnology());
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static BatteryServiceDumpProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (BatteryServiceDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static BatteryServiceDumpProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (BatteryServiceDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static BatteryServiceDumpProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (BatteryServiceDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static BatteryServiceDumpProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (BatteryServiceDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static BatteryServiceDumpProto parseFrom(InputStream input) throws IOException {
        return (BatteryServiceDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static BatteryServiceDumpProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (BatteryServiceDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static BatteryServiceDumpProto parseDelimitedFrom(InputStream input) throws IOException {
        return (BatteryServiceDumpProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static BatteryServiceDumpProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (BatteryServiceDumpProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static BatteryServiceDumpProto parseFrom(CodedInputStream input) throws IOException {
        return (BatteryServiceDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static BatteryServiceDumpProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (BatteryServiceDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(BatteryServiceDumpProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<BatteryServiceDumpProto, Builder> implements BatteryServiceDumpProtoOrBuilder {
        private Builder() {
            super(BatteryServiceDumpProto.DEFAULT_INSTANCE);
        }

        @Override // android.service.battery.BatteryServiceDumpProtoOrBuilder
        public boolean hasAreUpdatesStopped() {
            return ((BatteryServiceDumpProto) this.instance).hasAreUpdatesStopped();
        }

        @Override // android.service.battery.BatteryServiceDumpProtoOrBuilder
        public boolean getAreUpdatesStopped() {
            return ((BatteryServiceDumpProto) this.instance).getAreUpdatesStopped();
        }

        public Builder setAreUpdatesStopped(boolean value) {
            copyOnWrite();
            ((BatteryServiceDumpProto) this.instance).setAreUpdatesStopped(value);
            return this;
        }

        public Builder clearAreUpdatesStopped() {
            copyOnWrite();
            ((BatteryServiceDumpProto) this.instance).clearAreUpdatesStopped();
            return this;
        }

        @Override // android.service.battery.BatteryServiceDumpProtoOrBuilder
        public boolean hasPlugged() {
            return ((BatteryServiceDumpProto) this.instance).hasPlugged();
        }

        @Override // android.service.battery.BatteryServiceDumpProtoOrBuilder
        public BatteryPluggedStateEnum getPlugged() {
            return ((BatteryServiceDumpProto) this.instance).getPlugged();
        }

        public Builder setPlugged(BatteryPluggedStateEnum value) {
            copyOnWrite();
            ((BatteryServiceDumpProto) this.instance).setPlugged(value);
            return this;
        }

        public Builder clearPlugged() {
            copyOnWrite();
            ((BatteryServiceDumpProto) this.instance).clearPlugged();
            return this;
        }

        @Override // android.service.battery.BatteryServiceDumpProtoOrBuilder
        public boolean hasMaxChargingCurrent() {
            return ((BatteryServiceDumpProto) this.instance).hasMaxChargingCurrent();
        }

        @Override // android.service.battery.BatteryServiceDumpProtoOrBuilder
        public int getMaxChargingCurrent() {
            return ((BatteryServiceDumpProto) this.instance).getMaxChargingCurrent();
        }

        public Builder setMaxChargingCurrent(int value) {
            copyOnWrite();
            ((BatteryServiceDumpProto) this.instance).setMaxChargingCurrent(value);
            return this;
        }

        public Builder clearMaxChargingCurrent() {
            copyOnWrite();
            ((BatteryServiceDumpProto) this.instance).clearMaxChargingCurrent();
            return this;
        }

        @Override // android.service.battery.BatteryServiceDumpProtoOrBuilder
        public boolean hasMaxChargingVoltage() {
            return ((BatteryServiceDumpProto) this.instance).hasMaxChargingVoltage();
        }

        @Override // android.service.battery.BatteryServiceDumpProtoOrBuilder
        public int getMaxChargingVoltage() {
            return ((BatteryServiceDumpProto) this.instance).getMaxChargingVoltage();
        }

        public Builder setMaxChargingVoltage(int value) {
            copyOnWrite();
            ((BatteryServiceDumpProto) this.instance).setMaxChargingVoltage(value);
            return this;
        }

        public Builder clearMaxChargingVoltage() {
            copyOnWrite();
            ((BatteryServiceDumpProto) this.instance).clearMaxChargingVoltage();
            return this;
        }

        @Override // android.service.battery.BatteryServiceDumpProtoOrBuilder
        public boolean hasChargeCounter() {
            return ((BatteryServiceDumpProto) this.instance).hasChargeCounter();
        }

        @Override // android.service.battery.BatteryServiceDumpProtoOrBuilder
        public int getChargeCounter() {
            return ((BatteryServiceDumpProto) this.instance).getChargeCounter();
        }

        public Builder setChargeCounter(int value) {
            copyOnWrite();
            ((BatteryServiceDumpProto) this.instance).setChargeCounter(value);
            return this;
        }

        public Builder clearChargeCounter() {
            copyOnWrite();
            ((BatteryServiceDumpProto) this.instance).clearChargeCounter();
            return this;
        }

        @Override // android.service.battery.BatteryServiceDumpProtoOrBuilder
        public boolean hasStatus() {
            return ((BatteryServiceDumpProto) this.instance).hasStatus();
        }

        @Override // android.service.battery.BatteryServiceDumpProtoOrBuilder
        public BatteryStatusEnum getStatus() {
            return ((BatteryServiceDumpProto) this.instance).getStatus();
        }

        public Builder setStatus(BatteryStatusEnum value) {
            copyOnWrite();
            ((BatteryServiceDumpProto) this.instance).setStatus(value);
            return this;
        }

        public Builder clearStatus() {
            copyOnWrite();
            ((BatteryServiceDumpProto) this.instance).clearStatus();
            return this;
        }

        @Override // android.service.battery.BatteryServiceDumpProtoOrBuilder
        public boolean hasHealth() {
            return ((BatteryServiceDumpProto) this.instance).hasHealth();
        }

        @Override // android.service.battery.BatteryServiceDumpProtoOrBuilder
        public BatteryHealthEnum getHealth() {
            return ((BatteryServiceDumpProto) this.instance).getHealth();
        }

        public Builder setHealth(BatteryHealthEnum value) {
            copyOnWrite();
            ((BatteryServiceDumpProto) this.instance).setHealth(value);
            return this;
        }

        public Builder clearHealth() {
            copyOnWrite();
            ((BatteryServiceDumpProto) this.instance).clearHealth();
            return this;
        }

        @Override // android.service.battery.BatteryServiceDumpProtoOrBuilder
        public boolean hasIsPresent() {
            return ((BatteryServiceDumpProto) this.instance).hasIsPresent();
        }

        @Override // android.service.battery.BatteryServiceDumpProtoOrBuilder
        public boolean getIsPresent() {
            return ((BatteryServiceDumpProto) this.instance).getIsPresent();
        }

        public Builder setIsPresent(boolean value) {
            copyOnWrite();
            ((BatteryServiceDumpProto) this.instance).setIsPresent(value);
            return this;
        }

        public Builder clearIsPresent() {
            copyOnWrite();
            ((BatteryServiceDumpProto) this.instance).clearIsPresent();
            return this;
        }

        @Override // android.service.battery.BatteryServiceDumpProtoOrBuilder
        public boolean hasLevel() {
            return ((BatteryServiceDumpProto) this.instance).hasLevel();
        }

        @Override // android.service.battery.BatteryServiceDumpProtoOrBuilder
        public int getLevel() {
            return ((BatteryServiceDumpProto) this.instance).getLevel();
        }

        public Builder setLevel(int value) {
            copyOnWrite();
            ((BatteryServiceDumpProto) this.instance).setLevel(value);
            return this;
        }

        public Builder clearLevel() {
            copyOnWrite();
            ((BatteryServiceDumpProto) this.instance).clearLevel();
            return this;
        }

        @Override // android.service.battery.BatteryServiceDumpProtoOrBuilder
        public boolean hasScale() {
            return ((BatteryServiceDumpProto) this.instance).hasScale();
        }

        @Override // android.service.battery.BatteryServiceDumpProtoOrBuilder
        public int getScale() {
            return ((BatteryServiceDumpProto) this.instance).getScale();
        }

        public Builder setScale(int value) {
            copyOnWrite();
            ((BatteryServiceDumpProto) this.instance).setScale(value);
            return this;
        }

        public Builder clearScale() {
            copyOnWrite();
            ((BatteryServiceDumpProto) this.instance).clearScale();
            return this;
        }

        @Override // android.service.battery.BatteryServiceDumpProtoOrBuilder
        public boolean hasVoltage() {
            return ((BatteryServiceDumpProto) this.instance).hasVoltage();
        }

        @Override // android.service.battery.BatteryServiceDumpProtoOrBuilder
        public int getVoltage() {
            return ((BatteryServiceDumpProto) this.instance).getVoltage();
        }

        public Builder setVoltage(int value) {
            copyOnWrite();
            ((BatteryServiceDumpProto) this.instance).setVoltage(value);
            return this;
        }

        public Builder clearVoltage() {
            copyOnWrite();
            ((BatteryServiceDumpProto) this.instance).clearVoltage();
            return this;
        }

        @Override // android.service.battery.BatteryServiceDumpProtoOrBuilder
        public boolean hasTemperature() {
            return ((BatteryServiceDumpProto) this.instance).hasTemperature();
        }

        @Override // android.service.battery.BatteryServiceDumpProtoOrBuilder
        public int getTemperature() {
            return ((BatteryServiceDumpProto) this.instance).getTemperature();
        }

        public Builder setTemperature(int value) {
            copyOnWrite();
            ((BatteryServiceDumpProto) this.instance).setTemperature(value);
            return this;
        }

        public Builder clearTemperature() {
            copyOnWrite();
            ((BatteryServiceDumpProto) this.instance).clearTemperature();
            return this;
        }

        @Override // android.service.battery.BatteryServiceDumpProtoOrBuilder
        public boolean hasTechnology() {
            return ((BatteryServiceDumpProto) this.instance).hasTechnology();
        }

        @Override // android.service.battery.BatteryServiceDumpProtoOrBuilder
        public String getTechnology() {
            return ((BatteryServiceDumpProto) this.instance).getTechnology();
        }

        @Override // android.service.battery.BatteryServiceDumpProtoOrBuilder
        public ByteString getTechnologyBytes() {
            return ((BatteryServiceDumpProto) this.instance).getTechnologyBytes();
        }

        public Builder setTechnology(String value) {
            copyOnWrite();
            ((BatteryServiceDumpProto) this.instance).setTechnology(value);
            return this;
        }

        public Builder clearTechnology() {
            copyOnWrite();
            ((BatteryServiceDumpProto) this.instance).clearTechnology();
            return this;
        }

        public Builder setTechnologyBytes(ByteString value) {
            copyOnWrite();
            ((BatteryServiceDumpProto) this.instance).setTechnologyBytes(value);
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new BatteryServiceDumpProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                BatteryServiceDumpProto other = (BatteryServiceDumpProto) arg1;
                this.areUpdatesStopped_ = visitor.visitBoolean(hasAreUpdatesStopped(), this.areUpdatesStopped_, other.hasAreUpdatesStopped(), other.areUpdatesStopped_);
                this.plugged_ = visitor.visitInt(hasPlugged(), this.plugged_, other.hasPlugged(), other.plugged_);
                this.maxChargingCurrent_ = visitor.visitInt(hasMaxChargingCurrent(), this.maxChargingCurrent_, other.hasMaxChargingCurrent(), other.maxChargingCurrent_);
                this.maxChargingVoltage_ = visitor.visitInt(hasMaxChargingVoltage(), this.maxChargingVoltage_, other.hasMaxChargingVoltage(), other.maxChargingVoltage_);
                this.chargeCounter_ = visitor.visitInt(hasChargeCounter(), this.chargeCounter_, other.hasChargeCounter(), other.chargeCounter_);
                this.status_ = visitor.visitInt(hasStatus(), this.status_, other.hasStatus(), other.status_);
                this.health_ = visitor.visitInt(hasHealth(), this.health_, other.hasHealth(), other.health_);
                this.isPresent_ = visitor.visitBoolean(hasIsPresent(), this.isPresent_, other.hasIsPresent(), other.isPresent_);
                this.level_ = visitor.visitInt(hasLevel(), this.level_, other.hasLevel(), other.level_);
                this.scale_ = visitor.visitInt(hasScale(), this.scale_, other.hasScale(), other.scale_);
                this.voltage_ = visitor.visitInt(hasVoltage(), this.voltage_, other.hasVoltage(), other.voltage_);
                this.temperature_ = visitor.visitInt(hasTemperature(), this.temperature_, other.hasTemperature(), other.temperature_);
                this.technology_ = visitor.visitString(hasTechnology(), this.technology_, other.hasTechnology(), other.technology_);
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
                                this.areUpdatesStopped_ = input.readBool();
                                break;
                            case 16:
                                int rawValue = input.readEnum();
                                if (BatteryPluggedStateEnum.forNumber(rawValue) != null) {
                                    this.bitField0_ = 2 | this.bitField0_;
                                    this.plugged_ = rawValue;
                                    break;
                                } else {
                                    super.mergeVarintField(2, rawValue);
                                    break;
                                }
                            case 24:
                                this.bitField0_ |= 4;
                                this.maxChargingCurrent_ = input.readInt32();
                                break;
                            case 32:
                                this.bitField0_ |= 8;
                                this.maxChargingVoltage_ = input.readInt32();
                                break;
                            case 40:
                                this.bitField0_ |= 16;
                                this.chargeCounter_ = input.readInt32();
                                break;
                            case 48:
                                int rawValue2 = input.readEnum();
                                if (BatteryStatusEnum.forNumber(rawValue2) != null) {
                                    this.bitField0_ |= 32;
                                    this.status_ = rawValue2;
                                    break;
                                } else {
                                    super.mergeVarintField(6, rawValue2);
                                    break;
                                }
                            case 56:
                                int rawValue3 = input.readEnum();
                                if (BatteryHealthEnum.forNumber(rawValue3) != null) {
                                    this.bitField0_ |= 64;
                                    this.health_ = rawValue3;
                                    break;
                                } else {
                                    super.mergeVarintField(7, rawValue3);
                                    break;
                                }
                            case 64:
                                this.bitField0_ |= 128;
                                this.isPresent_ = input.readBool();
                                break;
                            case 72:
                                this.bitField0_ |= 256;
                                this.level_ = input.readInt32();
                                break;
                            case 80:
                                this.bitField0_ |= 512;
                                this.scale_ = input.readInt32();
                                break;
                            case 88:
                                this.bitField0_ |= 1024;
                                this.voltage_ = input.readInt32();
                                break;
                            case 96:
                                this.bitField0_ |= 2048;
                                this.temperature_ = input.readInt32();
                                break;
                            case 106:
                                String s = input.readString();
                                this.bitField0_ |= 4096;
                                this.technology_ = s;
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
                    synchronized (BatteryServiceDumpProto.class) {
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

    public static BatteryServiceDumpProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<BatteryServiceDumpProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
