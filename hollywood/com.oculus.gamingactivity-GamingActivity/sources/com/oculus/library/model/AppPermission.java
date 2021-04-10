package com.oculus.library.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;

public class AppPermission implements Parcelable {
    public static final Parcelable.Creator<AppPermission> CREATOR = new Parcelable.Creator<AppPermission>() {
        /* class com.oculus.library.model.AppPermission.AnonymousClass1 */

        @Override // android.os.Parcelable.Creator
        public AppPermission createFromParcel(Parcel in) {
            return new AppPermission(in);
        }

        @Override // android.os.Parcelable.Creator
        public AppPermission[] newArray(int size) {
            return new AppPermission[size];
        }
    };
    private static final String TAG = AppPermission.class.getCanonicalName();
    public final String group;
    public final int groupPriority;
    public final boolean isDangerous;
    public final String key;
    public final String label;

    public AppPermission(String key2, boolean isDangerous2, String label2, String group2, int groupPriority2) {
        if (TextUtils.isEmpty(key2)) {
            Log.e(TAG, "Invalid key received: null");
        }
        this.key = key2;
        this.isDangerous = isDangerous2;
        this.label = label2;
        this.group = group2;
        this.groupPriority = groupPriority2;
    }

    protected AppPermission(Parcel in) {
        boolean z = true;
        this.key = in.readString();
        this.isDangerous = in.readInt() != 1 ? false : z;
        this.label = in.readString();
        this.group = in.readString();
        this.groupPriority = in.readInt();
        if (TextUtils.isEmpty(this.key)) {
            Log.e(TAG, "Invalid key received: null");
        }
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.key);
        parcel.writeInt(this.isDangerous ? 1 : 0);
        parcel.writeString(this.label);
        parcel.writeString(this.group);
        parcel.writeInt(this.groupPriority);
    }
}
