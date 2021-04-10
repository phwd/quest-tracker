package com.oculus.companion.server;

import android.support.coordinatorlayout.R$styleable;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import java.io.IOException;

public final class Protocol$HmdStatusResponse extends GeneratedMessageLite<Protocol$HmdStatusResponse, Builder> implements Protocol$HmdStatusResponseOrBuilder {
    private static final Protocol$HmdStatusResponse DEFAULT_INSTANCE = new Protocol$HmdStatusResponse();
    private static volatile Parser<Protocol$HmdStatusResponse> PARSER;
    private boolean adbEnabled_ = false;
    private int batteryLevel_ = 0;
    private int bitField0_;
    private int bitField1_;
    private boolean chargerConnected_ = false;
    private boolean charging_ = false;
    private boolean controllerOtherConfigured_ = false;
    private boolean controllerOtherConnected_ = false;
    private int controllerPrimaryBatteryLevel_ = 0;
    private boolean controllerPrimaryConfigured_ = false;
    private boolean controllerPrimaryConnected_ = false;
    private int controllerSecondaryBatteryLevel_ = 0;
    private boolean controllerSecondaryConfigured_ = false;
    private boolean controllerSecondaryConnected_ = false;
    private boolean developerMode_ = false;
    private boolean fastChargerConnected_ = false;
    private int headsetState_ = 0;
    private int hmdUpdatePercentageComplete_ = 0;
    private boolean horizonLoggedIn_ = false;
    private boolean hswCompleted_ = false;
    private boolean nuxCompleted_ = false;
    private int nuxStatus_ = 0;
    private boolean otaAvailable_ = false;
    private boolean otaReady_ = false;
    private boolean pinConfigured_ = false;
    private boolean pinLocked_ = false;
    private int provisionType_ = 0;
    private String provisionedSerial_ = "";
    private boolean systemSoftwareLocked_ = false;
    private boolean wifiConfigured_ = false;
    private boolean wifiConnected_ = false;
    private String wifiDeviceName_ = "";
    private boolean wifiEnabled_ = false;
    private String wifiIpAddress_ = "";
    private boolean wifiOculusReachable_ = false;

    private Protocol$HmdStatusResponse() {
    }

    public boolean hasBatteryLevel() {
        return (this.bitField0_ & 1) == 1;
    }

    public int getBatteryLevel() {
        return this.batteryLevel_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setBatteryLevel(int i) {
        this.bitField0_ |= 1;
        this.batteryLevel_ = i;
    }

    public boolean hasChargerConnected() {
        return (this.bitField0_ & 2) == 2;
    }

    public boolean getChargerConnected() {
        return this.chargerConnected_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setChargerConnected(boolean z) {
        this.bitField0_ |= 2;
        this.chargerConnected_ = z;
    }

    public boolean hasFastChargerConnected() {
        return (this.bitField0_ & 4) == 4;
    }

    public boolean getFastChargerConnected() {
        return this.fastChargerConnected_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setFastChargerConnected(boolean z) {
        this.bitField0_ |= 4;
        this.fastChargerConnected_ = z;
    }

    public boolean hasCharging() {
        return (this.bitField0_ & 8) == 8;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setCharging(boolean z) {
        this.bitField0_ |= 8;
        this.charging_ = z;
    }

    public boolean hasWifiEnabled() {
        return (this.bitField0_ & 16) == 16;
    }

    public boolean getWifiEnabled() {
        return this.wifiEnabled_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setWifiEnabled(boolean z) {
        this.bitField0_ |= 16;
        this.wifiEnabled_ = z;
    }

    public boolean hasWifiConfigured() {
        return (this.bitField0_ & 32) == 32;
    }

    public boolean getWifiConfigured() {
        return this.wifiConfigured_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setWifiConfigured(boolean z) {
        this.bitField0_ |= 32;
        this.wifiConfigured_ = z;
    }

    public boolean hasWifiConnected() {
        return (this.bitField0_ & 64) == 64;
    }

    public boolean getWifiConnected() {
        return this.wifiConnected_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setWifiConnected(boolean z) {
        this.bitField0_ |= 64;
        this.wifiConnected_ = z;
    }

    public boolean hasWifiOculusReachable() {
        return (this.bitField0_ & 128) == 128;
    }

    public boolean getWifiOculusReachable() {
        return this.wifiOculusReachable_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setWifiOculusReachable(boolean z) {
        this.bitField0_ |= 128;
        this.wifiOculusReachable_ = z;
    }

    public boolean hasWifiIpAddress() {
        return (this.bitField0_ & 256) == 256;
    }

    public String getWifiIpAddress() {
        return this.wifiIpAddress_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setWifiIpAddress(String str) {
        if (str != null) {
            this.bitField0_ |= 256;
            this.wifiIpAddress_ = str;
            return;
        }
        throw new NullPointerException();
    }

    public boolean hasWifiDeviceName() {
        return (this.bitField0_ & 512) == 512;
    }

    public String getWifiDeviceName() {
        return this.wifiDeviceName_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setWifiDeviceName(String str) {
        if (str != null) {
            this.bitField0_ |= 512;
            this.wifiDeviceName_ = str;
            return;
        }
        throw new NullPointerException();
    }

    public boolean hasControllerPrimaryConfigured() {
        return (this.bitField0_ & 1024) == 1024;
    }

    public boolean getControllerPrimaryConfigured() {
        return this.controllerPrimaryConfigured_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setControllerPrimaryConfigured(boolean z) {
        this.bitField0_ |= 1024;
        this.controllerPrimaryConfigured_ = z;
    }

    public boolean hasControllerPrimaryConnected() {
        return (this.bitField0_ & 2048) == 2048;
    }

    public boolean getControllerPrimaryConnected() {
        return this.controllerPrimaryConnected_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setControllerPrimaryConnected(boolean z) {
        this.bitField0_ |= 2048;
        this.controllerPrimaryConnected_ = z;
    }

    public boolean hasControllerSecondaryConfigured() {
        return (this.bitField0_ & 4096) == 4096;
    }

    public boolean getControllerSecondaryConfigured() {
        return this.controllerSecondaryConfigured_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setControllerSecondaryConfigured(boolean z) {
        this.bitField0_ |= 4096;
        this.controllerSecondaryConfigured_ = z;
    }

    public boolean hasControllerSecondaryConnected() {
        return (this.bitField0_ & 8192) == 8192;
    }

    public boolean getControllerSecondaryConnected() {
        return this.controllerSecondaryConnected_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setControllerSecondaryConnected(boolean z) {
        this.bitField0_ |= 8192;
        this.controllerSecondaryConnected_ = z;
    }

    public boolean hasControllerOtherConfigured() {
        return (this.bitField0_ & 16384) == 16384;
    }

    public boolean getControllerOtherConfigured() {
        return this.controllerOtherConfigured_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setControllerOtherConfigured(boolean z) {
        this.bitField0_ |= 16384;
        this.controllerOtherConfigured_ = z;
    }

    public boolean hasControllerOtherConnected() {
        return (this.bitField0_ & 32768) == 32768;
    }

    public boolean getControllerOtherConnected() {
        return this.controllerOtherConnected_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setControllerOtherConnected(boolean z) {
        this.bitField0_ |= 32768;
        this.controllerOtherConnected_ = z;
    }

    public boolean hasControllerPrimaryBatteryLevel() {
        return (this.bitField0_ & 65536) == 65536;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setControllerPrimaryBatteryLevel(int i) {
        this.bitField0_ |= 65536;
        this.controllerPrimaryBatteryLevel_ = i;
    }

    public boolean hasControllerSecondaryBatteryLevel() {
        return (this.bitField0_ & 131072) == 131072;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setControllerSecondaryBatteryLevel(int i) {
        this.bitField0_ |= 131072;
        this.controllerSecondaryBatteryLevel_ = i;
    }

    public boolean hasOtaAvailable() {
        return (this.bitField0_ & 262144) == 262144;
    }

    public boolean getOtaAvailable() {
        return this.otaAvailable_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setOtaAvailable(boolean z) {
        this.bitField0_ |= 262144;
        this.otaAvailable_ = z;
    }

    public boolean hasOtaReady() {
        return (this.bitField0_ & 524288) == 524288;
    }

    public boolean getOtaReady() {
        return this.otaReady_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setOtaReady(boolean z) {
        this.bitField0_ |= 524288;
        this.otaReady_ = z;
    }

    public boolean hasHmdUpdatePercentageComplete() {
        return (this.bitField0_ & 1048576) == 1048576;
    }

    public int getHmdUpdatePercentageComplete() {
        return this.hmdUpdatePercentageComplete_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setHmdUpdatePercentageComplete(int i) {
        this.bitField0_ |= 1048576;
        this.hmdUpdatePercentageComplete_ = i;
    }

    public boolean hasPinConfigured() {
        return (this.bitField0_ & 2097152) == 2097152;
    }

    public boolean getPinConfigured() {
        return this.pinConfigured_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPinConfigured(boolean z) {
        this.bitField0_ |= 2097152;
        this.pinConfigured_ = z;
    }

    public boolean hasPinLocked() {
        return (this.bitField0_ & 4194304) == 4194304;
    }

    public boolean getPinLocked() {
        return this.pinLocked_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPinLocked(boolean z) {
        this.bitField0_ |= 4194304;
        this.pinLocked_ = z;
    }

    public boolean hasHorizonLoggedIn() {
        return (this.bitField0_ & 8388608) == 8388608;
    }

    public boolean getHorizonLoggedIn() {
        return this.horizonLoggedIn_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setHorizonLoggedIn(boolean z) {
        this.bitField0_ |= 8388608;
        this.horizonLoggedIn_ = z;
    }

    public boolean hasDeveloperMode() {
        return (this.bitField0_ & 16777216) == 16777216;
    }

    public boolean getDeveloperMode() {
        return this.developerMode_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setDeveloperMode(boolean z) {
        this.bitField0_ |= 16777216;
        this.developerMode_ = z;
    }

    public boolean hasAdbEnabled() {
        return (this.bitField0_ & 33554432) == 33554432;
    }

    public boolean getAdbEnabled() {
        return this.adbEnabled_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setAdbEnabled(boolean z) {
        this.bitField0_ |= 33554432;
        this.adbEnabled_ = z;
    }

    public boolean hasSystemSoftwareLocked() {
        return (this.bitField0_ & 67108864) == 67108864;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setSystemSoftwareLocked(boolean z) {
        this.bitField0_ |= 67108864;
        this.systemSoftwareLocked_ = z;
    }

    public boolean hasHswCompleted() {
        return (this.bitField0_ & 134217728) == 134217728;
    }

    public boolean getHswCompleted() {
        return this.hswCompleted_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setHswCompleted(boolean z) {
        this.bitField0_ |= 134217728;
        this.hswCompleted_ = z;
    }

    public boolean hasNuxCompleted() {
        return (this.bitField0_ & 268435456) == 268435456;
    }

    public boolean getNuxCompleted() {
        return this.nuxCompleted_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setNuxCompleted(boolean z) {
        this.bitField0_ |= 268435456;
        this.nuxCompleted_ = z;
    }

    public boolean hasNuxStatus() {
        return (this.bitField0_ & 536870912) == 536870912;
    }

    public Protocol$NuxStatus getNuxStatus() {
        Protocol$NuxStatus forNumber = Protocol$NuxStatus.forNumber(this.nuxStatus_);
        return forNumber == null ? Protocol$NuxStatus.NEW_DEVICE : forNumber;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setNuxStatus(Protocol$NuxStatus protocol$NuxStatus) {
        if (protocol$NuxStatus != null) {
            this.bitField0_ |= 536870912;
            this.nuxStatus_ = protocol$NuxStatus.getNumber();
            return;
        }
        throw new NullPointerException();
    }

    public boolean hasHeadsetState() {
        return (this.bitField0_ & 1073741824) == 1073741824;
    }

    public Protocol$HeadsetState getHeadsetState() {
        Protocol$HeadsetState forNumber = Protocol$HeadsetState.forNumber(this.headsetState_);
        return forNumber == null ? Protocol$HeadsetState.HEADSET_MOUNTED : forNumber;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setHeadsetState(Protocol$HeadsetState protocol$HeadsetState) {
        if (protocol$HeadsetState != null) {
            this.bitField0_ |= 1073741824;
            this.headsetState_ = protocol$HeadsetState.getNumber();
            return;
        }
        throw new NullPointerException();
    }

    public boolean hasProvisionType() {
        return (this.bitField0_ & Integer.MIN_VALUE) == Integer.MIN_VALUE;
    }

    public Protocol$ProvisionType getProvisionType() {
        Protocol$ProvisionType forNumber = Protocol$ProvisionType.forNumber(this.provisionType_);
        return forNumber == null ? Protocol$ProvisionType.UNPROVISIONED : forNumber;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setProvisionType(Protocol$ProvisionType protocol$ProvisionType) {
        if (protocol$ProvisionType != null) {
            this.bitField0_ |= Integer.MIN_VALUE;
            this.provisionType_ = protocol$ProvisionType.getNumber();
            return;
        }
        throw new NullPointerException();
    }

    public boolean hasProvisionedSerial() {
        return (this.bitField1_ & 1) == 1;
    }

    public String getProvisionedSerial() {
        return this.provisionedSerial_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setProvisionedSerial(String str) {
        if (str != null) {
            this.bitField1_ |= 1;
            this.provisionedSerial_ = str;
            return;
        }
        throw new NullPointerException();
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            codedOutputStream.writeUInt32(1, this.batteryLevel_);
        }
        if ((this.bitField0_ & 2) == 2) {
            codedOutputStream.writeBool(2, this.chargerConnected_);
        }
        if ((this.bitField0_ & 4) == 4) {
            codedOutputStream.writeBool(3, this.fastChargerConnected_);
        }
        if ((this.bitField0_ & 8) == 8) {
            codedOutputStream.writeBool(4, this.charging_);
        }
        if ((this.bitField0_ & 16) == 16) {
            codedOutputStream.writeBool(11, this.wifiEnabled_);
        }
        if ((this.bitField0_ & 32) == 32) {
            codedOutputStream.writeBool(12, this.wifiConfigured_);
        }
        if ((this.bitField0_ & 64) == 64) {
            codedOutputStream.writeBool(13, this.wifiConnected_);
        }
        if ((this.bitField0_ & 128) == 128) {
            codedOutputStream.writeBool(14, this.wifiOculusReachable_);
        }
        if ((this.bitField0_ & 256) == 256) {
            codedOutputStream.writeString(15, getWifiIpAddress());
        }
        if ((this.bitField0_ & 512) == 512) {
            codedOutputStream.writeString(16, getWifiDeviceName());
        }
        if ((this.bitField0_ & 1024) == 1024) {
            codedOutputStream.writeBool(21, this.controllerPrimaryConfigured_);
        }
        if ((this.bitField0_ & 2048) == 2048) {
            codedOutputStream.writeBool(22, this.controllerPrimaryConnected_);
        }
        if ((this.bitField0_ & 4096) == 4096) {
            codedOutputStream.writeBool(23, this.controllerSecondaryConfigured_);
        }
        if ((this.bitField0_ & 8192) == 8192) {
            codedOutputStream.writeBool(24, this.controllerSecondaryConnected_);
        }
        if ((this.bitField0_ & 16384) == 16384) {
            codedOutputStream.writeBool(25, this.controllerOtherConfigured_);
        }
        if ((this.bitField0_ & 32768) == 32768) {
            codedOutputStream.writeBool(26, this.controllerOtherConnected_);
        }
        if ((this.bitField0_ & 65536) == 65536) {
            codedOutputStream.writeUInt32(27, this.controllerPrimaryBatteryLevel_);
        }
        if ((this.bitField0_ & 131072) == 131072) {
            codedOutputStream.writeUInt32(28, this.controllerSecondaryBatteryLevel_);
        }
        if ((this.bitField0_ & 262144) == 262144) {
            codedOutputStream.writeBool(31, this.otaAvailable_);
        }
        if ((this.bitField0_ & 524288) == 524288) {
            codedOutputStream.writeBool(32, this.otaReady_);
        }
        if ((this.bitField0_ & 1048576) == 1048576) {
            codedOutputStream.writeUInt32(33, this.hmdUpdatePercentageComplete_);
        }
        if ((this.bitField0_ & 2097152) == 2097152) {
            codedOutputStream.writeBool(41, this.pinConfigured_);
        }
        if ((this.bitField0_ & 4194304) == 4194304) {
            codedOutputStream.writeBool(42, this.pinLocked_);
        }
        if ((this.bitField0_ & 8388608) == 8388608) {
            codedOutputStream.writeBool(51, this.horizonLoggedIn_);
        }
        if ((this.bitField0_ & 16777216) == 16777216) {
            codedOutputStream.writeBool(61, this.developerMode_);
        }
        if ((this.bitField0_ & 33554432) == 33554432) {
            codedOutputStream.writeBool(62, this.adbEnabled_);
        }
        if ((this.bitField0_ & 67108864) == 67108864) {
            codedOutputStream.writeBool(63, this.systemSoftwareLocked_);
        }
        if ((this.bitField0_ & 134217728) == 134217728) {
            codedOutputStream.writeBool(71, this.hswCompleted_);
        }
        if ((this.bitField0_ & 268435456) == 268435456) {
            codedOutputStream.writeBool(72, this.nuxCompleted_);
        }
        if ((this.bitField0_ & 536870912) == 536870912) {
            codedOutputStream.writeEnum(73, this.nuxStatus_);
        }
        if ((this.bitField0_ & 1073741824) == 1073741824) {
            codedOutputStream.writeEnum(81, this.headsetState_);
        }
        if ((this.bitField0_ & Integer.MIN_VALUE) == Integer.MIN_VALUE) {
            codedOutputStream.writeEnum(91, this.provisionType_);
        }
        if ((this.bitField1_ & 1) == 1) {
            codedOutputStream.writeString(92, getProvisionedSerial());
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    @Override // com.google.protobuf.MessageLite
    public int getSerializedSize() {
        int i = this.memoizedSerializedSize;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        if ((this.bitField0_ & 1) == 1) {
            i2 = 0 + CodedOutputStream.computeUInt32Size(1, this.batteryLevel_);
        }
        if ((this.bitField0_ & 2) == 2) {
            i2 += CodedOutputStream.computeBoolSize(2, this.chargerConnected_);
        }
        if ((this.bitField0_ & 4) == 4) {
            i2 += CodedOutputStream.computeBoolSize(3, this.fastChargerConnected_);
        }
        if ((this.bitField0_ & 8) == 8) {
            i2 += CodedOutputStream.computeBoolSize(4, this.charging_);
        }
        if ((this.bitField0_ & 16) == 16) {
            i2 += CodedOutputStream.computeBoolSize(11, this.wifiEnabled_);
        }
        if ((this.bitField0_ & 32) == 32) {
            i2 += CodedOutputStream.computeBoolSize(12, this.wifiConfigured_);
        }
        if ((this.bitField0_ & 64) == 64) {
            i2 += CodedOutputStream.computeBoolSize(13, this.wifiConnected_);
        }
        if ((this.bitField0_ & 128) == 128) {
            i2 += CodedOutputStream.computeBoolSize(14, this.wifiOculusReachable_);
        }
        if ((this.bitField0_ & 256) == 256) {
            i2 += CodedOutputStream.computeStringSize(15, getWifiIpAddress());
        }
        if ((this.bitField0_ & 512) == 512) {
            i2 += CodedOutputStream.computeStringSize(16, getWifiDeviceName());
        }
        if ((this.bitField0_ & 1024) == 1024) {
            i2 += CodedOutputStream.computeBoolSize(21, this.controllerPrimaryConfigured_);
        }
        if ((this.bitField0_ & 2048) == 2048) {
            i2 += CodedOutputStream.computeBoolSize(22, this.controllerPrimaryConnected_);
        }
        if ((this.bitField0_ & 4096) == 4096) {
            i2 += CodedOutputStream.computeBoolSize(23, this.controllerSecondaryConfigured_);
        }
        if ((this.bitField0_ & 8192) == 8192) {
            i2 += CodedOutputStream.computeBoolSize(24, this.controllerSecondaryConnected_);
        }
        if ((this.bitField0_ & 16384) == 16384) {
            i2 += CodedOutputStream.computeBoolSize(25, this.controllerOtherConfigured_);
        }
        if ((this.bitField0_ & 32768) == 32768) {
            i2 += CodedOutputStream.computeBoolSize(26, this.controllerOtherConnected_);
        }
        if ((this.bitField0_ & 65536) == 65536) {
            i2 += CodedOutputStream.computeUInt32Size(27, this.controllerPrimaryBatteryLevel_);
        }
        if ((this.bitField0_ & 131072) == 131072) {
            i2 += CodedOutputStream.computeUInt32Size(28, this.controllerSecondaryBatteryLevel_);
        }
        if ((this.bitField0_ & 262144) == 262144) {
            i2 += CodedOutputStream.computeBoolSize(31, this.otaAvailable_);
        }
        if ((this.bitField0_ & 524288) == 524288) {
            i2 += CodedOutputStream.computeBoolSize(32, this.otaReady_);
        }
        if ((this.bitField0_ & 1048576) == 1048576) {
            i2 += CodedOutputStream.computeUInt32Size(33, this.hmdUpdatePercentageComplete_);
        }
        if ((this.bitField0_ & 2097152) == 2097152) {
            i2 += CodedOutputStream.computeBoolSize(41, this.pinConfigured_);
        }
        if ((this.bitField0_ & 4194304) == 4194304) {
            i2 += CodedOutputStream.computeBoolSize(42, this.pinLocked_);
        }
        if ((this.bitField0_ & 8388608) == 8388608) {
            i2 += CodedOutputStream.computeBoolSize(51, this.horizonLoggedIn_);
        }
        if ((this.bitField0_ & 16777216) == 16777216) {
            i2 += CodedOutputStream.computeBoolSize(61, this.developerMode_);
        }
        if ((this.bitField0_ & 33554432) == 33554432) {
            i2 += CodedOutputStream.computeBoolSize(62, this.adbEnabled_);
        }
        if ((this.bitField0_ & 67108864) == 67108864) {
            i2 += CodedOutputStream.computeBoolSize(63, this.systemSoftwareLocked_);
        }
        if ((this.bitField0_ & 134217728) == 134217728) {
            i2 += CodedOutputStream.computeBoolSize(71, this.hswCompleted_);
        }
        if ((this.bitField0_ & 268435456) == 268435456) {
            i2 += CodedOutputStream.computeBoolSize(72, this.nuxCompleted_);
        }
        if ((this.bitField0_ & 536870912) == 536870912) {
            i2 += CodedOutputStream.computeEnumSize(73, this.nuxStatus_);
        }
        if ((this.bitField0_ & 1073741824) == 1073741824) {
            i2 += CodedOutputStream.computeEnumSize(81, this.headsetState_);
        }
        if ((this.bitField0_ & Integer.MIN_VALUE) == Integer.MIN_VALUE) {
            i2 += CodedOutputStream.computeEnumSize(91, this.provisionType_);
        }
        if ((this.bitField1_ & 1) == 1) {
            i2 += CodedOutputStream.computeStringSize(92, getProvisionedSerial());
        }
        int serializedSize = i2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = serializedSize;
        return serializedSize;
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static final class Builder extends GeneratedMessageLite.Builder<Protocol$HmdStatusResponse, Builder> implements Protocol$HmdStatusResponseOrBuilder {
        /* synthetic */ Builder(Protocol$1 protocol$1) {
            this();
        }

        private Builder() {
            super(Protocol$HmdStatusResponse.DEFAULT_INSTANCE);
        }

        public int getBatteryLevel() {
            return ((Protocol$HmdStatusResponse) this.instance).getBatteryLevel();
        }

        public Builder setBatteryLevel(int i) {
            copyOnWrite();
            ((Protocol$HmdStatusResponse) this.instance).setBatteryLevel(i);
            return this;
        }

        public boolean getChargerConnected() {
            return ((Protocol$HmdStatusResponse) this.instance).getChargerConnected();
        }

        public Builder setChargerConnected(boolean z) {
            copyOnWrite();
            ((Protocol$HmdStatusResponse) this.instance).setChargerConnected(z);
            return this;
        }

        public boolean getFastChargerConnected() {
            return ((Protocol$HmdStatusResponse) this.instance).getFastChargerConnected();
        }

        public Builder setFastChargerConnected(boolean z) {
            copyOnWrite();
            ((Protocol$HmdStatusResponse) this.instance).setFastChargerConnected(z);
            return this;
        }

        public Builder setCharging(boolean z) {
            copyOnWrite();
            ((Protocol$HmdStatusResponse) this.instance).setCharging(z);
            return this;
        }

        public boolean getWifiEnabled() {
            return ((Protocol$HmdStatusResponse) this.instance).getWifiEnabled();
        }

        public Builder setWifiEnabled(boolean z) {
            copyOnWrite();
            ((Protocol$HmdStatusResponse) this.instance).setWifiEnabled(z);
            return this;
        }

        public boolean getWifiConfigured() {
            return ((Protocol$HmdStatusResponse) this.instance).getWifiConfigured();
        }

        public Builder setWifiConfigured(boolean z) {
            copyOnWrite();
            ((Protocol$HmdStatusResponse) this.instance).setWifiConfigured(z);
            return this;
        }

        public boolean getWifiConnected() {
            return ((Protocol$HmdStatusResponse) this.instance).getWifiConnected();
        }

        public Builder setWifiConnected(boolean z) {
            copyOnWrite();
            ((Protocol$HmdStatusResponse) this.instance).setWifiConnected(z);
            return this;
        }

        public boolean getWifiOculusReachable() {
            return ((Protocol$HmdStatusResponse) this.instance).getWifiOculusReachable();
        }

        public Builder setWifiOculusReachable(boolean z) {
            copyOnWrite();
            ((Protocol$HmdStatusResponse) this.instance).setWifiOculusReachable(z);
            return this;
        }

        public String getWifiIpAddress() {
            return ((Protocol$HmdStatusResponse) this.instance).getWifiIpAddress();
        }

        public Builder setWifiIpAddress(String str) {
            copyOnWrite();
            ((Protocol$HmdStatusResponse) this.instance).setWifiIpAddress(str);
            return this;
        }

        public String getWifiDeviceName() {
            return ((Protocol$HmdStatusResponse) this.instance).getWifiDeviceName();
        }

        public Builder setWifiDeviceName(String str) {
            copyOnWrite();
            ((Protocol$HmdStatusResponse) this.instance).setWifiDeviceName(str);
            return this;
        }

        public boolean getControllerPrimaryConfigured() {
            return ((Protocol$HmdStatusResponse) this.instance).getControllerPrimaryConfigured();
        }

        public Builder setControllerPrimaryConfigured(boolean z) {
            copyOnWrite();
            ((Protocol$HmdStatusResponse) this.instance).setControllerPrimaryConfigured(z);
            return this;
        }

        public boolean getControllerPrimaryConnected() {
            return ((Protocol$HmdStatusResponse) this.instance).getControllerPrimaryConnected();
        }

        public Builder setControllerPrimaryConnected(boolean z) {
            copyOnWrite();
            ((Protocol$HmdStatusResponse) this.instance).setControllerPrimaryConnected(z);
            return this;
        }

        public boolean getControllerSecondaryConfigured() {
            return ((Protocol$HmdStatusResponse) this.instance).getControllerSecondaryConfigured();
        }

        public Builder setControllerSecondaryConfigured(boolean z) {
            copyOnWrite();
            ((Protocol$HmdStatusResponse) this.instance).setControllerSecondaryConfigured(z);
            return this;
        }

        public boolean getControllerSecondaryConnected() {
            return ((Protocol$HmdStatusResponse) this.instance).getControllerSecondaryConnected();
        }

        public Builder setControllerSecondaryConnected(boolean z) {
            copyOnWrite();
            ((Protocol$HmdStatusResponse) this.instance).setControllerSecondaryConnected(z);
            return this;
        }

        public boolean getControllerOtherConfigured() {
            return ((Protocol$HmdStatusResponse) this.instance).getControllerOtherConfigured();
        }

        public Builder setControllerOtherConfigured(boolean z) {
            copyOnWrite();
            ((Protocol$HmdStatusResponse) this.instance).setControllerOtherConfigured(z);
            return this;
        }

        public boolean getControllerOtherConnected() {
            return ((Protocol$HmdStatusResponse) this.instance).getControllerOtherConnected();
        }

        public Builder setControllerOtherConnected(boolean z) {
            copyOnWrite();
            ((Protocol$HmdStatusResponse) this.instance).setControllerOtherConnected(z);
            return this;
        }

        public Builder setControllerPrimaryBatteryLevel(int i) {
            copyOnWrite();
            ((Protocol$HmdStatusResponse) this.instance).setControllerPrimaryBatteryLevel(i);
            return this;
        }

        public Builder setControllerSecondaryBatteryLevel(int i) {
            copyOnWrite();
            ((Protocol$HmdStatusResponse) this.instance).setControllerSecondaryBatteryLevel(i);
            return this;
        }

        public boolean getOtaAvailable() {
            return ((Protocol$HmdStatusResponse) this.instance).getOtaAvailable();
        }

        public Builder setOtaAvailable(boolean z) {
            copyOnWrite();
            ((Protocol$HmdStatusResponse) this.instance).setOtaAvailable(z);
            return this;
        }

        public boolean getOtaReady() {
            return ((Protocol$HmdStatusResponse) this.instance).getOtaReady();
        }

        public Builder setOtaReady(boolean z) {
            copyOnWrite();
            ((Protocol$HmdStatusResponse) this.instance).setOtaReady(z);
            return this;
        }

        public int getHmdUpdatePercentageComplete() {
            return ((Protocol$HmdStatusResponse) this.instance).getHmdUpdatePercentageComplete();
        }

        public Builder setHmdUpdatePercentageComplete(int i) {
            copyOnWrite();
            ((Protocol$HmdStatusResponse) this.instance).setHmdUpdatePercentageComplete(i);
            return this;
        }

        public boolean getPinConfigured() {
            return ((Protocol$HmdStatusResponse) this.instance).getPinConfigured();
        }

        public Builder setPinConfigured(boolean z) {
            copyOnWrite();
            ((Protocol$HmdStatusResponse) this.instance).setPinConfigured(z);
            return this;
        }

        public boolean getPinLocked() {
            return ((Protocol$HmdStatusResponse) this.instance).getPinLocked();
        }

        public Builder setPinLocked(boolean z) {
            copyOnWrite();
            ((Protocol$HmdStatusResponse) this.instance).setPinLocked(z);
            return this;
        }

        public boolean getHorizonLoggedIn() {
            return ((Protocol$HmdStatusResponse) this.instance).getHorizonLoggedIn();
        }

        public Builder setHorizonLoggedIn(boolean z) {
            copyOnWrite();
            ((Protocol$HmdStatusResponse) this.instance).setHorizonLoggedIn(z);
            return this;
        }

        public boolean getDeveloperMode() {
            return ((Protocol$HmdStatusResponse) this.instance).getDeveloperMode();
        }

        public Builder setDeveloperMode(boolean z) {
            copyOnWrite();
            ((Protocol$HmdStatusResponse) this.instance).setDeveloperMode(z);
            return this;
        }

        public boolean getAdbEnabled() {
            return ((Protocol$HmdStatusResponse) this.instance).getAdbEnabled();
        }

        public Builder setAdbEnabled(boolean z) {
            copyOnWrite();
            ((Protocol$HmdStatusResponse) this.instance).setAdbEnabled(z);
            return this;
        }

        public Builder setSystemSoftwareLocked(boolean z) {
            copyOnWrite();
            ((Protocol$HmdStatusResponse) this.instance).setSystemSoftwareLocked(z);
            return this;
        }

        public boolean getHswCompleted() {
            return ((Protocol$HmdStatusResponse) this.instance).getHswCompleted();
        }

        public Builder setHswCompleted(boolean z) {
            copyOnWrite();
            ((Protocol$HmdStatusResponse) this.instance).setHswCompleted(z);
            return this;
        }

        public boolean getNuxCompleted() {
            return ((Protocol$HmdStatusResponse) this.instance).getNuxCompleted();
        }

        public Builder setNuxCompleted(boolean z) {
            copyOnWrite();
            ((Protocol$HmdStatusResponse) this.instance).setNuxCompleted(z);
            return this;
        }

        public Protocol$NuxStatus getNuxStatus() {
            return ((Protocol$HmdStatusResponse) this.instance).getNuxStatus();
        }

        public Builder setNuxStatus(Protocol$NuxStatus protocol$NuxStatus) {
            copyOnWrite();
            ((Protocol$HmdStatusResponse) this.instance).setNuxStatus(protocol$NuxStatus);
            return this;
        }

        public Protocol$HeadsetState getHeadsetState() {
            return ((Protocol$HmdStatusResponse) this.instance).getHeadsetState();
        }

        public Builder setHeadsetState(Protocol$HeadsetState protocol$HeadsetState) {
            copyOnWrite();
            ((Protocol$HmdStatusResponse) this.instance).setHeadsetState(protocol$HeadsetState);
            return this;
        }

        public Protocol$ProvisionType getProvisionType() {
            return ((Protocol$HmdStatusResponse) this.instance).getProvisionType();
        }

        public Builder setProvisionType(Protocol$ProvisionType protocol$ProvisionType) {
            copyOnWrite();
            ((Protocol$HmdStatusResponse) this.instance).setProvisionType(protocol$ProvisionType);
            return this;
        }

        public String getProvisionedSerial() {
            return ((Protocol$HmdStatusResponse) this.instance).getProvisionedSerial();
        }

        public Builder setProvisionedSerial(String str) {
            copyOnWrite();
            ((Protocol$HmdStatusResponse) this.instance).setProvisionedSerial(str);
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (Protocol$1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
            case 1:
                return new Protocol$HmdStatusResponse();
            case R$styleable.CoordinatorLayout_Layout_layout_anchorGravity:
                return DEFAULT_INSTANCE;
            case R$styleable.CoordinatorLayout_Layout_layout_behavior:
                return null;
            case R$styleable.CoordinatorLayout_Layout_layout_dodgeInsetEdges:
                return new Builder(null);
            case R$styleable.CoordinatorLayout_Layout_layout_insetEdge:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                Protocol$HmdStatusResponse protocol$HmdStatusResponse = (Protocol$HmdStatusResponse) obj2;
                this.batteryLevel_ = visitor.visitInt(hasBatteryLevel(), this.batteryLevel_, protocol$HmdStatusResponse.hasBatteryLevel(), protocol$HmdStatusResponse.batteryLevel_);
                this.chargerConnected_ = visitor.visitBoolean(hasChargerConnected(), this.chargerConnected_, protocol$HmdStatusResponse.hasChargerConnected(), protocol$HmdStatusResponse.chargerConnected_);
                this.fastChargerConnected_ = visitor.visitBoolean(hasFastChargerConnected(), this.fastChargerConnected_, protocol$HmdStatusResponse.hasFastChargerConnected(), protocol$HmdStatusResponse.fastChargerConnected_);
                this.charging_ = visitor.visitBoolean(hasCharging(), this.charging_, protocol$HmdStatusResponse.hasCharging(), protocol$HmdStatusResponse.charging_);
                this.wifiEnabled_ = visitor.visitBoolean(hasWifiEnabled(), this.wifiEnabled_, protocol$HmdStatusResponse.hasWifiEnabled(), protocol$HmdStatusResponse.wifiEnabled_);
                this.wifiConfigured_ = visitor.visitBoolean(hasWifiConfigured(), this.wifiConfigured_, protocol$HmdStatusResponse.hasWifiConfigured(), protocol$HmdStatusResponse.wifiConfigured_);
                this.wifiConnected_ = visitor.visitBoolean(hasWifiConnected(), this.wifiConnected_, protocol$HmdStatusResponse.hasWifiConnected(), protocol$HmdStatusResponse.wifiConnected_);
                this.wifiOculusReachable_ = visitor.visitBoolean(hasWifiOculusReachable(), this.wifiOculusReachable_, protocol$HmdStatusResponse.hasWifiOculusReachable(), protocol$HmdStatusResponse.wifiOculusReachable_);
                this.wifiIpAddress_ = visitor.visitString(hasWifiIpAddress(), this.wifiIpAddress_, protocol$HmdStatusResponse.hasWifiIpAddress(), protocol$HmdStatusResponse.wifiIpAddress_);
                this.wifiDeviceName_ = visitor.visitString(hasWifiDeviceName(), this.wifiDeviceName_, protocol$HmdStatusResponse.hasWifiDeviceName(), protocol$HmdStatusResponse.wifiDeviceName_);
                this.controllerPrimaryConfigured_ = visitor.visitBoolean(hasControllerPrimaryConfigured(), this.controllerPrimaryConfigured_, protocol$HmdStatusResponse.hasControllerPrimaryConfigured(), protocol$HmdStatusResponse.controllerPrimaryConfigured_);
                this.controllerPrimaryConnected_ = visitor.visitBoolean(hasControllerPrimaryConnected(), this.controllerPrimaryConnected_, protocol$HmdStatusResponse.hasControllerPrimaryConnected(), protocol$HmdStatusResponse.controllerPrimaryConnected_);
                this.controllerSecondaryConfigured_ = visitor.visitBoolean(hasControllerSecondaryConfigured(), this.controllerSecondaryConfigured_, protocol$HmdStatusResponse.hasControllerSecondaryConfigured(), protocol$HmdStatusResponse.controllerSecondaryConfigured_);
                this.controllerSecondaryConnected_ = visitor.visitBoolean(hasControllerSecondaryConnected(), this.controllerSecondaryConnected_, protocol$HmdStatusResponse.hasControllerSecondaryConnected(), protocol$HmdStatusResponse.controllerSecondaryConnected_);
                this.controllerOtherConfigured_ = visitor.visitBoolean(hasControllerOtherConfigured(), this.controllerOtherConfigured_, protocol$HmdStatusResponse.hasControllerOtherConfigured(), protocol$HmdStatusResponse.controllerOtherConfigured_);
                this.controllerOtherConnected_ = visitor.visitBoolean(hasControllerOtherConnected(), this.controllerOtherConnected_, protocol$HmdStatusResponse.hasControllerOtherConnected(), protocol$HmdStatusResponse.controllerOtherConnected_);
                this.controllerPrimaryBatteryLevel_ = visitor.visitInt(hasControllerPrimaryBatteryLevel(), this.controllerPrimaryBatteryLevel_, protocol$HmdStatusResponse.hasControllerPrimaryBatteryLevel(), protocol$HmdStatusResponse.controllerPrimaryBatteryLevel_);
                this.controllerSecondaryBatteryLevel_ = visitor.visitInt(hasControllerSecondaryBatteryLevel(), this.controllerSecondaryBatteryLevel_, protocol$HmdStatusResponse.hasControllerSecondaryBatteryLevel(), protocol$HmdStatusResponse.controllerSecondaryBatteryLevel_);
                this.otaAvailable_ = visitor.visitBoolean(hasOtaAvailable(), this.otaAvailable_, protocol$HmdStatusResponse.hasOtaAvailable(), protocol$HmdStatusResponse.otaAvailable_);
                this.otaReady_ = visitor.visitBoolean(hasOtaReady(), this.otaReady_, protocol$HmdStatusResponse.hasOtaReady(), protocol$HmdStatusResponse.otaReady_);
                this.hmdUpdatePercentageComplete_ = visitor.visitInt(hasHmdUpdatePercentageComplete(), this.hmdUpdatePercentageComplete_, protocol$HmdStatusResponse.hasHmdUpdatePercentageComplete(), protocol$HmdStatusResponse.hmdUpdatePercentageComplete_);
                this.pinConfigured_ = visitor.visitBoolean(hasPinConfigured(), this.pinConfigured_, protocol$HmdStatusResponse.hasPinConfigured(), protocol$HmdStatusResponse.pinConfigured_);
                this.pinLocked_ = visitor.visitBoolean(hasPinLocked(), this.pinLocked_, protocol$HmdStatusResponse.hasPinLocked(), protocol$HmdStatusResponse.pinLocked_);
                this.horizonLoggedIn_ = visitor.visitBoolean(hasHorizonLoggedIn(), this.horizonLoggedIn_, protocol$HmdStatusResponse.hasHorizonLoggedIn(), protocol$HmdStatusResponse.horizonLoggedIn_);
                this.developerMode_ = visitor.visitBoolean(hasDeveloperMode(), this.developerMode_, protocol$HmdStatusResponse.hasDeveloperMode(), protocol$HmdStatusResponse.developerMode_);
                this.adbEnabled_ = visitor.visitBoolean(hasAdbEnabled(), this.adbEnabled_, protocol$HmdStatusResponse.hasAdbEnabled(), protocol$HmdStatusResponse.adbEnabled_);
                this.systemSoftwareLocked_ = visitor.visitBoolean(hasSystemSoftwareLocked(), this.systemSoftwareLocked_, protocol$HmdStatusResponse.hasSystemSoftwareLocked(), protocol$HmdStatusResponse.systemSoftwareLocked_);
                this.hswCompleted_ = visitor.visitBoolean(hasHswCompleted(), this.hswCompleted_, protocol$HmdStatusResponse.hasHswCompleted(), protocol$HmdStatusResponse.hswCompleted_);
                this.nuxCompleted_ = visitor.visitBoolean(hasNuxCompleted(), this.nuxCompleted_, protocol$HmdStatusResponse.hasNuxCompleted(), protocol$HmdStatusResponse.nuxCompleted_);
                this.nuxStatus_ = visitor.visitInt(hasNuxStatus(), this.nuxStatus_, protocol$HmdStatusResponse.hasNuxStatus(), protocol$HmdStatusResponse.nuxStatus_);
                this.headsetState_ = visitor.visitInt(hasHeadsetState(), this.headsetState_, protocol$HmdStatusResponse.hasHeadsetState(), protocol$HmdStatusResponse.headsetState_);
                this.provisionType_ = visitor.visitInt(hasProvisionType(), this.provisionType_, protocol$HmdStatusResponse.hasProvisionType(), protocol$HmdStatusResponse.provisionType_);
                this.provisionedSerial_ = visitor.visitString(hasProvisionedSerial(), this.provisionedSerial_, protocol$HmdStatusResponse.hasProvisionedSerial(), protocol$HmdStatusResponse.provisionedSerial_);
                if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE) {
                    this.bitField0_ |= protocol$HmdStatusResponse.bitField0_;
                    this.bitField1_ |= protocol$HmdStatusResponse.bitField1_;
                }
                return this;
            case R$styleable.CoordinatorLayout_Layout_layout_keyline:
                CodedInputStream codedInputStream = (CodedInputStream) obj;
                ExtensionRegistryLite extensionRegistryLite = (ExtensionRegistryLite) obj2;
                boolean z = false;
                while (!z) {
                    try {
                        int readTag = codedInputStream.readTag();
                        switch (readTag) {
                            case 0:
                                break;
                            case 8:
                                this.bitField0_ |= 1;
                                this.batteryLevel_ = codedInputStream.readUInt32();
                                continue;
                            case 16:
                                this.bitField0_ |= 2;
                                this.chargerConnected_ = codedInputStream.readBool();
                                continue;
                            case 24:
                                this.bitField0_ |= 4;
                                this.fastChargerConnected_ = codedInputStream.readBool();
                                continue;
                            case 32:
                                this.bitField0_ |= 8;
                                this.charging_ = codedInputStream.readBool();
                                continue;
                            case 88:
                                this.bitField0_ |= 16;
                                this.wifiEnabled_ = codedInputStream.readBool();
                                continue;
                            case 96:
                                this.bitField0_ |= 32;
                                this.wifiConfigured_ = codedInputStream.readBool();
                                continue;
                            case 104:
                                this.bitField0_ |= 64;
                                this.wifiConnected_ = codedInputStream.readBool();
                                continue;
                            case 112:
                                this.bitField0_ |= 128;
                                this.wifiOculusReachable_ = codedInputStream.readBool();
                                continue;
                            case 122:
                                String readString = codedInputStream.readString();
                                this.bitField0_ |= 256;
                                this.wifiIpAddress_ = readString;
                                continue;
                            case 130:
                                String readString2 = codedInputStream.readString();
                                this.bitField0_ |= 512;
                                this.wifiDeviceName_ = readString2;
                                continue;
                            case 168:
                                this.bitField0_ |= 1024;
                                this.controllerPrimaryConfigured_ = codedInputStream.readBool();
                                continue;
                            case 176:
                                this.bitField0_ |= 2048;
                                this.controllerPrimaryConnected_ = codedInputStream.readBool();
                                continue;
                            case 184:
                                this.bitField0_ |= 4096;
                                this.controllerSecondaryConfigured_ = codedInputStream.readBool();
                                continue;
                            case 192:
                                this.bitField0_ |= 8192;
                                this.controllerSecondaryConnected_ = codedInputStream.readBool();
                                continue;
                            case 200:
                                this.bitField0_ |= 16384;
                                this.controllerOtherConfigured_ = codedInputStream.readBool();
                                continue;
                            case 208:
                                this.bitField0_ |= 32768;
                                this.controllerOtherConnected_ = codedInputStream.readBool();
                                continue;
                            case 216:
                                this.bitField0_ |= 65536;
                                this.controllerPrimaryBatteryLevel_ = codedInputStream.readUInt32();
                                continue;
                            case 224:
                                this.bitField0_ |= 131072;
                                this.controllerSecondaryBatteryLevel_ = codedInputStream.readUInt32();
                                continue;
                            case 248:
                                this.bitField0_ |= 262144;
                                this.otaAvailable_ = codedInputStream.readBool();
                                continue;
                            case 256:
                                this.bitField0_ |= 524288;
                                this.otaReady_ = codedInputStream.readBool();
                                continue;
                            case 264:
                                this.bitField0_ |= 1048576;
                                this.hmdUpdatePercentageComplete_ = codedInputStream.readUInt32();
                                continue;
                            case 328:
                                this.bitField0_ |= 2097152;
                                this.pinConfigured_ = codedInputStream.readBool();
                                continue;
                            case 336:
                                this.bitField0_ |= 4194304;
                                this.pinLocked_ = codedInputStream.readBool();
                                continue;
                            case 408:
                                this.bitField0_ |= 8388608;
                                this.horizonLoggedIn_ = codedInputStream.readBool();
                                continue;
                            case 488:
                                this.bitField0_ |= 16777216;
                                this.developerMode_ = codedInputStream.readBool();
                                continue;
                            case 496:
                                this.bitField0_ |= 33554432;
                                this.adbEnabled_ = codedInputStream.readBool();
                                continue;
                            case 504:
                                this.bitField0_ |= 67108864;
                                this.systemSoftwareLocked_ = codedInputStream.readBool();
                                continue;
                            case 568:
                                this.bitField0_ |= 134217728;
                                this.hswCompleted_ = codedInputStream.readBool();
                                continue;
                            case 576:
                                this.bitField0_ |= 268435456;
                                this.nuxCompleted_ = codedInputStream.readBool();
                                continue;
                            case 584:
                                int readEnum = codedInputStream.readEnum();
                                if (Protocol$NuxStatus.forNumber(readEnum) == null) {
                                    super.mergeVarintField(73, readEnum);
                                    continue;
                                } else {
                                    this.bitField0_ |= 536870912;
                                    this.nuxStatus_ = readEnum;
                                }
                            case 648:
                                int readEnum2 = codedInputStream.readEnum();
                                if (Protocol$HeadsetState.forNumber(readEnum2) == null) {
                                    super.mergeVarintField(81, readEnum2);
                                    continue;
                                } else {
                                    this.bitField0_ |= 1073741824;
                                    this.headsetState_ = readEnum2;
                                }
                            case 728:
                                int readEnum3 = codedInputStream.readEnum();
                                if (Protocol$ProvisionType.forNumber(readEnum3) == null) {
                                    super.mergeVarintField(91, readEnum3);
                                    continue;
                                } else {
                                    this.bitField0_ |= Integer.MIN_VALUE;
                                    this.provisionType_ = readEnum3;
                                }
                            case 738:
                                String readString3 = codedInputStream.readString();
                                this.bitField1_ = 1 | this.bitField1_;
                                this.provisionedSerial_ = readString3;
                                continue;
                            default:
                                if (parseUnknownField(readTag, codedInputStream)) {
                                    continue;
                                }
                                break;
                        }
                        z = true;
                    } catch (InvalidProtocolBufferException e) {
                        throw new RuntimeException(e.setUnfinishedMessage(this));
                    } catch (IOException e2) {
                        throw new RuntimeException(new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this));
                    }
                }
                break;
            case 7:
                break;
            case 8:
                if (PARSER == null) {
                    synchronized (Protocol$HmdStatusResponse.class) {
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
}
