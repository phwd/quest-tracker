package com.facebook.common.parcels;

import android.os.Parcel;
import android.os.Parcelable;
import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class ParcelableBoolean implements Parcelable {
    public static final Parcelable.Creator<ParcelableBoolean> CREATOR = new Parcelable.Creator<ParcelableBoolean>() {
        /* class com.facebook.common.parcels.ParcelableBoolean.AnonymousClass1 */

        @Override // android.os.Parcelable.Creator
        public ParcelableBoolean createFromParcel(Parcel parcel) {
            return new ParcelableBoolean(ParcelUtil.readBool(parcel));
        }

        @Override // android.os.Parcelable.Creator
        public ParcelableBoolean[] newArray(int i) {
            return new ParcelableBoolean[i];
        }
    };
    public final boolean value;

    public int describeContents() {
        return 0;
    }

    public ParcelableBoolean(boolean z) {
        this.value = z;
    }

    public void writeToParcel(Parcel parcel, int i) {
        ParcelUtil.writeBool(parcel, this.value);
    }
}
