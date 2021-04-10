package com.qualcomm.wfd;

import android.os.Parcel;
import android.os.Parcelable;

public class WfdStatus implements Parcelable {
    public static final Parcelable.Creator<WfdStatus> CREATOR = new Parcelable.Creator<WfdStatus>() {
        /* class com.qualcomm.wfd.WfdStatus.AnonymousClass1 */

        @Override // android.os.Parcelable.Creator
        public WfdStatus createFromParcel(Parcel parcel) {
            WfdStatus wfdStatus = new WfdStatus();
            wfdStatus.state = parcel.readInt();
            wfdStatus.sessionId = parcel.readInt();
            wfdStatus.connectedDevice = (WfdDevice) parcel.readValue(WfdDevice.class.getClassLoader());
            return wfdStatus;
        }

        @Override // android.os.Parcelable.Creator
        public WfdStatus[] newArray(int i) {
            return new WfdStatus[i];
        }
    };
    public WfdDevice connectedDevice = null;
    public int sessionId = -1;
    public int state = WfdEnums$SessionState.INVALID.ordinal();

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.state);
        parcel.writeInt(this.sessionId);
        parcel.writeValue(this.connectedDevice);
    }
}
