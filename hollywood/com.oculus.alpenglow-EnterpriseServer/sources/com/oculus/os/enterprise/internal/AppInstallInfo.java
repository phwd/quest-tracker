package com.oculus.os.enterprise.internal;

import android.os.Parcel;
import android.os.Parcelable;

public class AppInstallInfo implements Parcelable {
    public static final Parcelable.Creator<AppInstallInfo> CREATOR = new Parcelable.Creator<AppInstallInfo>() {
        /* class com.oculus.os.enterprise.internal.AppInstallInfo.AnonymousClass1 */

        @Override // android.os.Parcelable.Creator
        public AppInstallInfo createFromParcel(Parcel parcel) {
            return new AppInstallInfo(parcel.readString(), parcel.readString(), (AppInstallStatus) parcel.readParcelable(AppInstallStatus.class.getClassLoader()), parcel.readLong(), parcel.readLong());
        }

        @Override // android.os.Parcelable.Creator
        public AppInstallInfo[] newArray(int i) {
            return new AppInstallInfo[i];
        }
    };
    public final String appId;
    public final long bytesDownloaded;
    public final String packageName;
    public final AppInstallStatus status;
    public final long totalSize;

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.appId);
        parcel.writeString(this.packageName);
        parcel.writeParcelable(this.status, 0);
        parcel.writeLong(this.bytesDownloaded);
        parcel.writeLong(this.totalSize);
    }

    public AppInstallInfo(String str, String str2, AppInstallStatus appInstallStatus, long j, long j2) {
        this.appId = str;
        this.packageName = str2;
        this.status = appInstallStatus;
        this.bytesDownloaded = j;
        this.totalSize = j2;
    }
}
