package com.oculus.library.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Image implements Parcelable {
    public static final Parcelable.Creator<Image> CREATOR = new Parcelable.Creator<Image>() {
        /* class com.oculus.library.model.Image.AnonymousClass1 */

        @Override // android.os.Parcelable.Creator
        public Image createFromParcel(Parcel in) {
            return new Image(in);
        }

        @Override // android.os.Parcelable.Creator
        public Image[] newArray(int size) {
            return new Image[size];
        }
    };
    public final int height;
    public final ImageName name;
    public final String uri;
    public final int width;

    public enum ImageName {
        SOURCE_SQUARE("source_square"),
        SOURCE_MAIN("source_main"),
        SOURCE_TINY("source_tiny"),
        LANDSCAPE_SMALL("landscape_small"),
        HERO("hero");
        
        public final String value;

        private ImageName(String value2) {
            this.value = value2;
        }
    }

    public Image(ImageName name2, String uri2, int width2, int height2) {
        this.name = name2;
        this.uri = uri2;
        this.width = width2;
        this.height = height2;
    }

    public Image(Parcel parcel) {
        this.name = ImageName.valueOf(parcel.readString());
        this.uri = parcel.readString();
        this.width = parcel.readInt();
        this.height = parcel.readInt();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name.name());
        dest.writeString(this.uri);
        dest.writeInt(this.width);
        dest.writeInt(this.height);
    }
}
