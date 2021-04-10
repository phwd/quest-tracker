package com.oculus.model;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

public class BundleModel implements Parcelable {
    public static final Parcelable.Creator<BundleModel> CREATOR = new Parcelable.Creator<BundleModel>() {
        /* class com.oculus.model.BundleModel.AnonymousClass1 */

        @Override // android.os.Parcelable.Creator
        public BundleModel createFromParcel(Parcel parcel) {
            return new BundleModel(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public BundleModel[] newArray(int i) {
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

    public int describeContents() {
        return 0;
    }

    public CharSequence getPriceText(Context context, int i, int i2, int i3) {
        return new OfferPriceHelper(context, i, i2, i3).getPriceText(this.isFree, this.price, this.strikethroughPrice, this.promoBenefit);
    }

    public BundleModel newPurchasedModel() {
        return new BundleModel(this.itemId, this.offerId, this.name, this.description, this.releaseDate, this.squareImageSource, this.mainImageSource, this.tinyImageSource, this.price, this.strikethroughPrice, this.promoBenefit, this.smallLandscapeImage, this.isFree, true, this.styleTheme);
    }

    public void writeToParcel(Parcel parcel, int i) {
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

    public BundleModel(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, boolean z, boolean z2, StyleTheme styleTheme2) {
        this.itemId = str;
        this.offerId = str2;
        this.name = str3;
        this.description = str4;
        this.releaseDate = str5;
        this.squareImageSource = str6;
        this.mainImageSource = str7;
        this.tinyImageSource = str8;
        this.price = str9;
        this.strikethroughPrice = str10;
        this.promoBenefit = str11;
        this.smallLandscapeImage = str12;
        this.isFree = z;
        this.isOwnedByCurrentUser = z2;
        this.styleTheme = styleTheme2;
    }
}
