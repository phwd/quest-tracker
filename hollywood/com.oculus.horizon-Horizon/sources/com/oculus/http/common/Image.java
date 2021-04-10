package com.oculus.http.common;

import android.os.Parcel;
import android.os.Parcelable;

public class Image implements Parcelable {
    public static final String APP_IMAGE_HERO_SIZE = "1029x308";
    public static final String APP_IMAGE_MEDIUM_SIZE = "720x405";
    public static final String APP_IMAGE_SQUARE_SIZE = "360x360";
    public static final String APP_IMAGE_TINY_SIZE = "180x101";
    public static final String APP_SCREENSHOT_SMALL_SIZE = "720x405";
    public static final String APP_SCREENSHOT_TINY_SIZE = "360x203";
    public static final String APP_SMALL_LANDSCAPE_IMAGE_SIZE = "360x120";
    public static final Parcelable.Creator<Image> CREATOR = new Parcelable.Creator<Image>() {
        /* class com.oculus.http.common.Image.AnonymousClass1 */

        /* Return type fixed from 'java.lang.Object' to match base method */
        @Override // android.os.Parcelable.Creator
        public final Image createFromParcel(Parcel parcel) {
            return new Image(parcel);
        }

        /* Return type fixed from 'java.lang.Object[]' to match base method */
        @Override // android.os.Parcelable.Creator
        public final Image[] newArray(int i) {
            return new Image[i];
        }
    };
    public String name;
    public String uri;

    public final int describeContents() {
        return 0;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.uri);
        parcel.writeString(this.name);
    }

    public Image(Parcel parcel) {
        this.uri = parcel.readString();
        this.name = parcel.readString();
    }
}
