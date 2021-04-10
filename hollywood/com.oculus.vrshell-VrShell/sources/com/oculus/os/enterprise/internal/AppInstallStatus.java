package com.oculus.os.enterprise.internal;

import android.os.Parcel;
import android.os.Parcelable;

public enum AppInstallStatus implements Parcelable {
    QUEUED,
    DOWNLOADING,
    DOWNLOADED,
    INSTALLING,
    INSTALLED,
    UNINSTALLING,
    UNINSTALLED,
    ERROR;
    
    public static final Parcelable.Creator<AppInstallStatus> CREATOR = new Parcelable.Creator<AppInstallStatus>() {
        /* class com.oculus.os.enterprise.internal.AppInstallStatus.AnonymousClass1 */

        @Override // android.os.Parcelable.Creator
        public AppInstallStatus createFromParcel(Parcel parcel) {
            return AppInstallStatus.values()[parcel.readInt()];
        }

        @Override // android.os.Parcelable.Creator
        public AppInstallStatus[] newArray(int i) {
            return new AppInstallStatus[i];
        }
    };

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(ordinal());
    }
}
