package com.oculus.model;

import android.os.Parcel;
import android.os.Parcelable;

public class ProductUpdateModel implements Parcelable {
    public static final Parcelable.Creator<ProductUpdateModel> CREATOR = new Parcelable.Creator<ProductUpdateModel>() {
        /* class com.oculus.model.ProductUpdateModel.AnonymousClass1 */

        /* Return type fixed from 'java.lang.Object' to match base method */
        @Override // android.os.Parcelable.Creator
        public final ProductUpdateModel createFromParcel(Parcel parcel) {
            return new ProductUpdateModel(parcel);
        }

        /* Return type fixed from 'java.lang.Object[]' to match base method */
        @Override // android.os.Parcelable.Creator
        public final ProductUpdateModel[] newArray(int i) {
            return new ProductUpdateModel[i];
        }
    };
    public final String description;
    public final int releaseDate;
    public final String version;

    public final int describeContents() {
        return 0;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.version);
        parcel.writeString(this.description);
        parcel.writeInt(this.releaseDate);
    }

    public ProductUpdateModel(Parcel parcel) {
        this.version = parcel.readString();
        this.description = parcel.readString();
        this.releaseDate = parcel.readInt();
    }
}
