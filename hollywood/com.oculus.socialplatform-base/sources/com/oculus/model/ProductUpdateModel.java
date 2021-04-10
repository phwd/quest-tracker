package com.oculus.model;

import android.os.Parcel;
import android.os.Parcelable;

public class ProductUpdateModel implements Parcelable {
    public static final Parcelable.Creator<ProductUpdateModel> CREATOR = new Parcelable.Creator<ProductUpdateModel>() {
        /* class com.oculus.model.ProductUpdateModel.AnonymousClass1 */

        @Override // android.os.Parcelable.Creator
        public ProductUpdateModel createFromParcel(Parcel parcel) {
            return new ProductUpdateModel(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public ProductUpdateModel[] newArray(int i) {
            return new ProductUpdateModel[i];
        }
    };
    public final String description;
    public final int releaseDate;
    public final String version;

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.version);
        parcel.writeString(this.description);
        parcel.writeInt(this.releaseDate);
    }

    public ProductUpdateModel(Parcel parcel) {
        this.version = parcel.readString();
        this.description = parcel.readString();
        this.releaseDate = parcel.readInt();
    }

    public ProductUpdateModel(String str, String str2, int i) {
        this.version = str;
        this.description = str2;
        this.releaseDate = i;
    }
}
