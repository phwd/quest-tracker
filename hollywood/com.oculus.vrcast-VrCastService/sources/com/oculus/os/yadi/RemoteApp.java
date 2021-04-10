package com.oculus.os.yadi;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RemoteApp implements Parcelable {
    public static final Parcelable.Creator<RemoteApp> CREATOR = new Creator();
    private final int _hashCode;
    public final String appId;
    public final ApplicationType appType;
    public final List<String> assetIds;
    public final String binaryResourceId;
    public final long createdTime;
    public final long lastUpdatedTime;
    public final Map<String, String> metadata;

    public enum ApplicationType {
        ANDROID_APP
    }

    public int describeContents() {
        return 0;
    }

    private static class Creator implements Parcelable.Creator<RemoteApp> {
        private Creator() {
        }

        @Override // android.os.Parcelable.Creator
        public RemoteApp[] newArray(int i) {
            return new RemoteApp[i];
        }

        @Override // android.os.Parcelable.Creator
        public RemoteApp createFromParcel(Parcel parcel) {
            String readString = parcel.readString();
            String readString2 = parcel.readString();
            ArrayList arrayList = new ArrayList();
            parcel.readStringList(arrayList);
            ApplicationType applicationType = ApplicationType.values()[parcel.readInt()];
            int readInt = parcel.readInt();
            HashMap hashMap = new HashMap(readInt);
            for (int i = 0; i < readInt; i++) {
                hashMap.put(parcel.readString(), parcel.readString());
            }
            return new RemoteApp(readString, readString2, arrayList, applicationType, hashMap, parcel.readLong(), parcel.readLong());
        }
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.appId);
        parcel.writeString(this.binaryResourceId);
        parcel.writeStringList(this.assetIds);
        parcel.writeInt(this.appType.ordinal());
        parcel.writeInt(this.metadata.size());
        for (Map.Entry<String, String> entry : this.metadata.entrySet()) {
            parcel.writeString(entry.getKey());
            parcel.writeString(entry.getValue());
        }
        parcel.writeLong(this.createdTime);
        parcel.writeLong(this.lastUpdatedTime);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || RemoteApp.class != obj.getClass()) {
            return false;
        }
        RemoteApp remoteApp = (RemoteApp) obj;
        return this.createdTime == remoteApp.createdTime && this.lastUpdatedTime == remoteApp.lastUpdatedTime && this.appType == remoteApp.appType && this.appId.equals(remoteApp.appId) && this.binaryResourceId.equals(remoteApp.binaryResourceId) && this.assetIds.equals(remoteApp.assetIds) && this.metadata.equals(remoteApp.metadata);
    }

    public int hashCode() {
        return this._hashCode;
    }

    private RemoteApp(String str, String str2, List<String> list, ApplicationType applicationType, Map<String, String> map, long j, long j2) {
        this.appId = str;
        this.binaryResourceId = str2;
        this.assetIds = Collections.unmodifiableList(list);
        this.appType = applicationType;
        this.metadata = Collections.unmodifiableMap(map);
        this.createdTime = j;
        this.lastUpdatedTime = j2;
        int hashCode = ((((((((((217 + str.hashCode()) * 31) + str2.hashCode()) * 31) + ((int) (j >> 32))) * 31) + ((int) (j & 4294967295L))) * 31) + ((int) (j2 >> 32))) * 31) + ((int) (j2 & 4294967295L));
        for (String str3 : list) {
            hashCode = (hashCode * 31) + str3.hashCode();
        }
        for (Map.Entry<String, String> entry : map.entrySet()) {
            hashCode = (((hashCode * 31) + entry.getKey().hashCode()) * 31) + entry.getValue().hashCode();
        }
        this._hashCode = hashCode;
    }
}
