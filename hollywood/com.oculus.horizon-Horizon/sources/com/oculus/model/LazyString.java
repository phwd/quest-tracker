package com.oculus.model;

import android.os.Parcel;
import android.os.Parcelable;

public class LazyString implements Parcelable {
    public static final Parcelable.Creator<LazyString> CREATOR = new Parcelable.Creator<LazyString>() {
        /* class com.oculus.model.LazyString.AnonymousClass1 */

        /* Return type fixed from 'java.lang.Object' to match base method */
        @Override // android.os.Parcelable.Creator
        public final LazyString createFromParcel(Parcel parcel) {
            return new LazyString(parcel);
        }

        /* Return type fixed from 'java.lang.Object[]' to match base method */
        @Override // android.os.Parcelable.Creator
        public final LazyString[] newArray(int i) {
            return new LazyString[i];
        }
    };
    public final int mResourceId;
    public final String mString;

    public final int describeContents() {
        return 0;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mString);
        parcel.writeInt(this.mResourceId);
    }

    public LazyString(Parcel parcel) {
        this.mString = parcel.readString();
        this.mResourceId = parcel.readInt();
    }
}
