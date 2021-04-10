package com.oculus.model;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

public class LazyString implements Parcelable {
    public static final Parcelable.Creator<LazyString> CREATOR = new Parcelable.Creator<LazyString>() {
        /* class com.oculus.model.LazyString.AnonymousClass1 */

        @Override // android.os.Parcelable.Creator
        public LazyString createFromParcel(Parcel parcel) {
            return new LazyString(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public LazyString[] newArray(int i) {
            return new LazyString[i];
        }
    };
    public final int mResourceId;
    public final String mString;

    public int describeContents() {
        return 0;
    }

    public String get(Context context) {
        String str = this.mString;
        if (str == null) {
            return context.getString(this.mResourceId);
        }
        return str;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mString);
        parcel.writeInt(this.mResourceId);
    }

    public LazyString(int i) {
        this.mString = null;
        this.mResourceId = i;
    }

    public LazyString(Parcel parcel) {
        this.mString = parcel.readString();
        this.mResourceId = parcel.readInt();
    }

    public LazyString(String str) {
        this.mString = str;
        this.mResourceId = 0;
    }
}
