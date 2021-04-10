package com.oculus.horizon.api.common.user;

import android.os.Parcel;
import android.os.Parcelable;

public class ProfilePhoto implements Parcelable {
    public static final Parcelable.Creator<ProfilePhoto> CREATOR = new Parcelable.Creator<ProfilePhoto>() {
        /* class com.oculus.horizon.api.common.user.ProfilePhoto.AnonymousClass1 */

        @Override // android.os.Parcelable.Creator
        public ProfilePhoto createFromParcel(Parcel parcel) {
            return new ProfilePhoto(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public ProfilePhoto[] newArray(int i) {
            return new ProfilePhoto[i];
        }
    };
    public String height;
    public String name;
    public String uri;
    public String width;

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.name);
        parcel.writeString(this.uri);
        parcel.writeString(this.width);
        parcel.writeString(this.height);
    }

    public ProfilePhoto() {
    }

    public ProfilePhoto(Parcel parcel) {
        this.name = parcel.readString();
        this.uri = parcel.readString();
        this.width = parcel.readString();
        this.height = parcel.readString();
    }
}
