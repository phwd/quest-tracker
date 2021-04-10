package com.oculus.model;

import android.os.Parcel;
import android.os.Parcelable;

public class BundleModel implements Parcelable {
    public static final Parcelable.Creator<BundleModel> CREATOR = new Parcelable.Creator<BundleModel>() {
        /* class com.oculus.model.BundleModel.AnonymousClass1 */

        /* Return type fixed from 'java.lang.Object' to match base method */
        @Override // android.os.Parcelable.Creator
        public final BundleModel createFromParcel(Parcel parcel) {
            return new BundleModel(parcel);
        }

        /* Return type fixed from 'java.lang.Object[]' to match base method */
        @Override // android.os.Parcelable.Creator
        public final BundleModel[] newArray(int i) {
            return new BundleModel[i];
        }
    };
    public final String description;
    public final boolean isFree;
    public final boolean isOwnedByCurrentUser;
    public final String itemId;
    public final String mainImageSource;
    public final String name;
    public final String offerId;
    public final String price;
    public final String promoBenefit;
    public final String releaseDate;
    public final String smallLandscapeImage;
    public final String squareImageSource;
    public final String strikethroughPrice;
    public final StyleTheme styleTheme;
    public final String tinyImageSource;

    public final int describeContents() {
        return 0;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        String name2;
        parcel.writeString(this.itemId);
        parcel.writeString(this.offerId);
        parcel.writeString(this.name);
        parcel.writeString(this.description);
        parcel.writeString(this.releaseDate);
        parcel.writeString(this.squareImageSource);
        parcel.writeString(this.mainImageSource);
        parcel.writeString(this.tinyImageSource);
        parcel.writeString(this.price);
        parcel.writeString(this.strikethroughPrice);
        parcel.writeString(this.promoBenefit);
        parcel.writeString(this.smallLandscapeImage);
        parcel.writeByte(this.isFree ? (byte) 1 : 0);
        parcel.writeByte(this.isOwnedByCurrentUser ? (byte) 1 : 0);
        StyleTheme styleTheme2 = this.styleTheme;
        if (styleTheme2 == null) {
            name2 = null;
        } else {
            name2 = styleTheme2.name();
        }
        parcel.writeString(name2);
    }

    public BundleModel(Parcel parcel) {
        StyleTheme valueOf;
        this.itemId = parcel.readString();
        this.offerId = parcel.readString();
        this.name = parcel.readString();
        this.description = parcel.readString();
        this.releaseDate = parcel.readString();
        this.squareImageSource = parcel.readString();
        this.mainImageSource = parcel.readString();
        this.tinyImageSource = parcel.readString();
        this.price = parcel.readString();
        this.strikethroughPrice = parcel.readString();
        this.promoBenefit = parcel.readString();
        this.smallLandscapeImage = parcel.readString();
        boolean z = false;
        this.isFree = parcel.readByte() == 1;
        this.isOwnedByCurrentUser = parcel.readByte() == 1 ? true : z;
        String readString = parcel.readString();
        if (readString == null) {
            valueOf = null;
        } else {
            valueOf = StyleTheme.valueOf(readString);
        }
        this.styleTheme = valueOf;
    }
}
