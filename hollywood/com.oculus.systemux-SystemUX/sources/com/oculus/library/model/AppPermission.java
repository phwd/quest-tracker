package com.oculus.library.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;

public class AppPermission implements Parcelable {
    public static final Parcelable.Creator<AppPermission> CREATOR = new Parcelable.Creator<AppPermission>() {
        /* class com.oculus.library.model.AppPermission.AnonymousClass1 */

        @Override // android.os.Parcelable.Creator
        public AppPermission createFromParcel(Parcel parcel) {
            return new AppPermission(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public AppPermission[] newArray(int i) {
            return new AppPermission[i];
        }
    };
    private static final String TAG = "com.oculus.library.model.AppPermission";
    public final String group;
    public final int groupPriority;
    public final boolean isDangerous;
    public final String key;
    public final String label;

    public int describeContents() {
        return 0;
    }

    public AppPermission(String str, boolean z, String str2, String str3, int i) {
        if (TextUtils.isEmpty(str)) {
            Log.e(TAG, "Invalid key received: null");
        }
        this.key = str;
        this.isDangerous = z;
        this.label = str2;
        this.group = str3;
        this.groupPriority = i;
    }

    protected AppPermission(Parcel parcel) {
        this.key = parcel.readString();
        this.isDangerous = parcel.readInt() != 1 ? false : true;
        this.label = parcel.readString();
        this.group = parcel.readString();
        this.groupPriority = parcel.readInt();
        if (TextUtils.isEmpty(this.key)) {
            Log.e(TAG, "Invalid key received: null");
        }
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.key);
        parcel.writeInt(this.isDangerous ? 1 : 0);
        parcel.writeString(this.label);
        parcel.writeString(this.group);
        parcel.writeInt(this.groupPriority);
    }
}
