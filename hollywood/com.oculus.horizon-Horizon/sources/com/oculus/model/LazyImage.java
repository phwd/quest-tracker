package com.oculus.model;

import android.os.Parcel;
import android.os.Parcelable;

public class LazyImage implements Parcelable {
    public static final Parcelable.Creator<LazyImage> CREATOR = new Parcelable.Creator<LazyImage>() {
        /* class com.oculus.model.LazyImage.AnonymousClass1 */

        /* Return type fixed from 'java.lang.Object' to match base method */
        @Override // android.os.Parcelable.Creator
        public final LazyImage createFromParcel(Parcel parcel) {
            return new LazyImage(parcel);
        }

        /* Return type fixed from 'java.lang.Object[]' to match base method */
        @Override // android.os.Parcelable.Creator
        public final LazyImage[] newArray(int i) {
            return new LazyImage[i];
        }
    };
    public final float mAspectRatio;
    public final int mResourceId;
    public final String mUri;

    public final int describeContents() {
        return 0;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mUri);
        parcel.writeInt(this.mResourceId);
        parcel.writeFloat(this.mAspectRatio);
    }

    public LazyImage(Parcel parcel) {
        this.mUri = parcel.readString();
        this.mResourceId = parcel.readInt();
        this.mAspectRatio = parcel.readFloat();
    }
}
