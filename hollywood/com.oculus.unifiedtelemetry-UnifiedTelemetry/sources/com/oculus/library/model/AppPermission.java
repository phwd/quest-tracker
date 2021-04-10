package com.oculus.library.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;

public class AppPermission implements Parcelable {
    public static final Parcelable.Creator<AppPermission> CREATOR = new Parcelable.Creator<AppPermission>() {
        /* class com.oculus.library.model.AppPermission.AnonymousClass1 */

        /* Return type fixed from 'java.lang.Object' to match base method */
        @Override // android.os.Parcelable.Creator
        public final AppPermission createFromParcel(Parcel parcel) {
            return new AppPermission(parcel);
        }

        /* Return type fixed from 'java.lang.Object[]' to match base method */
        @Override // android.os.Parcelable.Creator
        public final AppPermission[] newArray(int i) {
            return new AppPermission[i];
        }
    };
    public static final String TAG = "com.oculus.library.model.AppPermission";
    public final String group;
    public final int groupPriority;
    public final boolean isDangerous;
    public final String key;
    public final String label;

    public final int describeContents() {
        return 0;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.key);
        parcel.writeInt(this.isDangerous ? 1 : 0);
        parcel.writeString(this.label);
        parcel.writeString(this.group);
        parcel.writeInt(this.groupPriority);
    }

    public AppPermission(Parcel parcel) {
        this.key = parcel.readString();
        this.isDangerous = parcel.readInt() != 1 ? false : true;
        this.label = parcel.readString();
        this.group = parcel.readString();
        this.groupPriority = parcel.readInt();
        if (TextUtils.isEmpty(this.key)) {
            Log.e(TAG, "Invalid key received: null");
        }
    }
}
