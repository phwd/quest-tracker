package com.oculus.horizon.api.common;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nullable;

public class ParcelableBundle implements Parcelable {
    public static final Parcelable.Creator<ParcelableBundle> CREATOR = new Parcelable.Creator<ParcelableBundle>() {
        /* class com.oculus.horizon.api.common.ParcelableBundle.AnonymousClass1 */

        @Override // android.os.Parcelable.Creator
        public ParcelableBundle createFromParcel(Parcel parcel) {
            return new ParcelableBundle(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public ParcelableBundle[] newArray(int i) {
            return new ParcelableBundle[i];
        }
    };
    public List<ParcelableItem> bundleItems;
    @Nullable
    public String displayName;
    @Nullable
    public String displayShortDescription;

    public int describeContents() {
        return 0;
    }

    public ParcelableBundle(String str, String str2, List<ParcelableItem> list) {
        this.displayName = str;
        this.displayShortDescription = str2;
        this.bundleItems = list;
    }

    public ParcelableBundle(Parcel parcel) {
        this.displayName = parcel.readString();
        this.displayShortDescription = parcel.readString();
        this.bundleItems = new ArrayList();
        parcel.readList(this.bundleItems, ParcelableBundle.class.getClassLoader());
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.displayName);
        parcel.writeString(this.displayShortDescription);
        parcel.writeList(this.bundleItems);
    }
}
