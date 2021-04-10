package com.qualcomm.wfd;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

public class WfdDevice implements Parcelable {
    public static final Parcelable.Creator<WfdDevice> CREATOR = new Parcelable.Creator<WfdDevice>() {
        /* class com.qualcomm.wfd.WfdDevice.AnonymousClass1 */

        @Override // android.os.Parcelable.Creator
        public WfdDevice createFromParcel(Parcel parcel) {
            return new WfdDevice(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public WfdDevice[] newArray(int i) {
            return new WfdDevice[i];
        }
    };
    public String addressOfAP;
    public Bundle capabilities;
    public int coupledSinkStatus;
    public int decoderLatency;
    public String deviceName;
    public int deviceType;
    public int extSupport;
    public String ipAddress;
    public boolean isAvailableForSession;
    public String macAddress;
    public int netType;
    public int preferredConnectivity;
    public int rtspPort;

    public int describeContents() {
        return 0;
    }

    public WfdDevice() {
        this.deviceType = WfdEnums$WFDDeviceType.UNKNOWN.getCode();
        this.netType = WfdEnums$NetType.UNKNOWN_NET.ordinal();
        this.rtspPort = -1;
        this.capabilities = new Bundle();
    }

    public WfdDevice(Parcel parcel) {
        readFromParcel(parcel);
    }

    private void readFromParcel(Parcel parcel) {
        this.deviceType = parcel.readInt();
        this.netType = parcel.readInt();
        this.macAddress = parcel.readString();
        this.deviceName = parcel.readString();
        this.ipAddress = parcel.readString();
        this.rtspPort = parcel.readInt();
        this.decoderLatency = parcel.readInt();
        this.isAvailableForSession = parcel.readByte() != 0;
        this.preferredConnectivity = parcel.readInt();
        this.addressOfAP = parcel.readString();
        this.coupledSinkStatus = parcel.readInt();
        this.extSupport = parcel.readInt();
        this.capabilities = parcel.readBundle();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.deviceType);
        parcel.writeInt(this.netType);
        parcel.writeString(this.macAddress);
        parcel.writeString(this.deviceName);
        parcel.writeString(this.ipAddress);
        parcel.writeInt(this.rtspPort);
        parcel.writeInt(this.decoderLatency);
        parcel.writeByte(this.isAvailableForSession ? (byte) 1 : 0);
        parcel.writeInt(this.preferredConnectivity);
        parcel.writeString(this.addressOfAP);
        parcel.writeInt(this.coupledSinkStatus);
        parcel.writeInt(this.extSupport);
        parcel.writeBundle(this.capabilities);
    }
}
