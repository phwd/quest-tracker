package com.oculus.model;

import android.content.Context;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

public class LazyStringWithUri implements Parcelable {
    public static final Parcelable.Creator<LazyStringWithUri> CREATOR = new Parcelable.Creator<LazyStringWithUri>() {
        /* class com.oculus.model.LazyStringWithUri.AnonymousClass1 */

        @Override // android.os.Parcelable.Creator
        public LazyStringWithUri createFromParcel(Parcel parcel) {
            return new LazyStringWithUri(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public LazyStringWithUri[] newArray(int i) {
            return new LazyStringWithUri[i];
        }
    };
    public final LazyString mText;
    public final Uri mUri;

    public int describeContents() {
        return 0;
    }

    public String getText(Context context) {
        return this.mText.get(context);
    }

    public Uri getUri() {
        return this.mUri;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.mText, i);
        parcel.writeParcelable(this.mUri, i);
    }

    public LazyStringWithUri(int i, Uri uri) {
        this.mText = new LazyString(i);
        this.mUri = uri;
    }

    public LazyStringWithUri(Parcel parcel) {
        this.mText = (LazyString) parcel.readParcelable(LazyString.class.getClassLoader());
        this.mUri = (Uri) parcel.readParcelable(Uri.class.getClassLoader());
    }

    public LazyStringWithUri(String str, Uri uri) {
        this.mText = new LazyString(str);
        this.mUri = uri;
    }
}
