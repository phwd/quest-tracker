package com.oculus.library.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Image implements Parcelable {
    public static final Parcelable.Creator<Image> CREATOR = new Parcelable.Creator<Image>() {
        /* class com.oculus.library.model.Image.AnonymousClass1 */

        @Override // android.os.Parcelable.Creator
        public Image createFromParcel(Parcel parcel) {
            return new Image(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public Image[] newArray(int i) {
            return new Image[i];
        }
    };
    public final int height;
    public final ImageName name;
    public final String uri;
    public final int width;

    public int describeContents() {
        return 0;
    }

    public enum ImageName {
        SOURCE_SQUARE("source_square"),
        SOURCE_MAIN("source_main"),
        SOURCE_TINY("source_tiny"),
        LANDSCAPE_SMALL("landscape_small"),
        HERO("hero");
        
        public final String value;

        /* access modifiers changed from: public */
        ImageName(String str) {
            this.value = str;
        }
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.name.name());
        parcel.writeString(this.uri);
        parcel.writeInt(this.width);
        parcel.writeInt(this.height);
    }

    public Image(Parcel parcel) {
        this.name = ImageName.valueOf(parcel.readString());
        this.uri = parcel.readString();
        this.width = parcel.readInt();
        this.height = parcel.readInt();
    }

    public Image(ImageName imageName, String str, int i, int i2) {
        this.name = imageName;
        this.uri = str;
        this.width = i;
        this.height = i2;
    }
}
