package com.oculus.model;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

public class LazyStringWithUri implements Parcelable {
    public static final Parcelable.Creator<LazyStringWithUri> CREATOR = new Parcelable.Creator<LazyStringWithUri>() {
        /* class com.oculus.model.LazyStringWithUri.AnonymousClass1 */

        /* Return type fixed from 'java.lang.Object' to match base method */
        @Override // android.os.Parcelable.Creator
        public final LazyStringWithUri createFromParcel(Parcel parcel) {
            return new LazyStringWithUri(parcel);
        }

        /* Return type fixed from 'java.lang.Object[]' to match base method */
        @Override // android.os.Parcelable.Creator
        public final LazyStringWithUri[] newArray(int i) {
            return new LazyStringWithUri[i];
        }
    };
    public final LazyString mText;
    public final Uri mUri;

    public final int describeContents() {
        return 0;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.mText, i);
        parcel.writeParcelable(this.mUri, i);
    }

    public LazyStringWithUri(Parcel parcel) {
        this.mText = (LazyString) parcel.readParcelable(LazyString.class.getClassLoader());
        this.mUri = (Uri) parcel.readParcelable(Uri.class.getClassLoader());
    }
}
