package com.oculus.model;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import com.oculus.library.model.CloudStorageStatus;
import com.oculus.library.model.LivestreamingStatus;
import com.oculus.library.model.MicrophoneUsage;
import java.util.List;

public class ProductModel implements Parcelable {
    public static final Parcelable.Creator<ProductModel> CREATOR = new Parcelable.Creator<ProductModel>() {
        /* class com.oculus.model.ProductModel.AnonymousClass1 */

        @Override // android.os.Parcelable.Creator
        public ProductModel createFromParcel(Parcel parcel) {
            return new ProductModel(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public ProductModel[] newArray(int i) {
            return new ProductModel[i];
        }
    };
    public final String applicationGroupingId;
    public final boolean chromecastAudioEnabled;
    public final CloudStorageStatus cloudStorageStatus;
    public final String comfortRatingsConst;
    public final String comfortRatingsName;
    public final String displayVersion;
    public final long downloadedSize;
    public final long installationSize;
    public final boolean isFavorite;
    public final boolean isFree;
    public final boolean isOwnedByCurrentUser;
    public final String itemId;
    public final int latestTargetSdkVersion;
    public final int latestVersionCode;
    public final String latestVersionName;
    public final boolean livestreamingAudioHookingEnabled;
    public final LivestreamingStatus livestreamingStatus;
    public final String mainImageSource;
    public final MicrophoneUsage microphoneUsage;
    public final int minRecommendedVersionCode;
    public final int minRequiredVersionCode;
    public final String name;
    public final String offerId;
    public final String packageName;
    public final long patchDownloadsize;
    public final List<String> permissions;
    public final String price;
    public final String promoBenefit;
    public final float qualityRatingsAverage;
    public final int qualityRatingsCount;
    public final String smallLandscapeImage;
    public final String squareImageSource;
    public final ProductStatus status;
    public final String strikethroughPrice;
    public final String tinyImageSource;
    public final int userQualityRating;

    public int describeContents() {
        return 0;
    }

    public ProductModel(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13, String str14, String str15, long j, long j2, long j3, int i, String str16, int i2, int i3, int i4, float f, boolean z, boolean z2, List<String> list, ProductStatus productStatus, CloudStorageStatus cloudStorageStatus2, boolean z3, LivestreamingStatus livestreamingStatus2, boolean z4, boolean z5, MicrophoneUsage microphoneUsage2, int i5, int i6) {
        this.itemId = str;
        this.offerId = str2;
        this.name = str3;
        this.applicationGroupingId = str4;
        this.packageName = str5;
        this.displayVersion = str6;
        this.squareImageSource = str7;
        this.mainImageSource = str8;
        this.tinyImageSource = str9;
        this.price = str10;
        this.strikethroughPrice = str11;
        this.promoBenefit = str12;
        this.comfortRatingsConst = str13;
        this.comfortRatingsName = str14;
        this.smallLandscapeImage = str15;
        this.downloadedSize = j;
        this.patchDownloadsize = j2;
        this.installationSize = j3;
        this.latestVersionCode = i;
        this.latestVersionName = str16;
        this.latestTargetSdkVersion = i2;
        this.userQualityRating = i3;
        this.qualityRatingsCount = i4;
        this.qualityRatingsAverage = f;
        this.isFree = z;
        this.isOwnedByCurrentUser = z2;
        this.permissions = list;
        this.status = productStatus;
        this.cloudStorageStatus = cloudStorageStatus2;
        this.isFavorite = z3;
        this.livestreamingStatus = livestreamingStatus2;
        this.livestreamingAudioHookingEnabled = z4;
        this.chromecastAudioEnabled = z5;
        this.microphoneUsage = microphoneUsage2;
        this.minRecommendedVersionCode = i5;
        this.minRequiredVersionCode = i6;
    }

    protected ProductModel(Parcel parcel) {
        this.itemId = parcel.readString();
        this.offerId = parcel.readString();
        this.name = parcel.readString();
        this.applicationGroupingId = parcel.readString();
        this.packageName = parcel.readString();
        this.displayVersion = parcel.readString();
        this.squareImageSource = parcel.readString();
        this.mainImageSource = parcel.readString();
        this.tinyImageSource = parcel.readString();
        this.price = parcel.readString();
        this.strikethroughPrice = parcel.readString();
        this.promoBenefit = parcel.readString();
        this.comfortRatingsConst = parcel.readString();
        this.comfortRatingsName = parcel.readString();
        this.smallLandscapeImage = parcel.readString();
        this.downloadedSize = parcel.readLong();
        this.patchDownloadsize = parcel.readLong();
        this.installationSize = parcel.readLong();
        this.latestVersionCode = parcel.readInt();
        this.latestVersionName = parcel.readString();
        this.latestTargetSdkVersion = parcel.readInt();
        this.userQualityRating = parcel.readInt();
        this.qualityRatingsCount = parcel.readInt();
        this.qualityRatingsAverage = parcel.readFloat();
        boolean z = false;
        this.isFree = parcel.readByte() != 0;
        this.isOwnedByCurrentUser = parcel.readByte() != 0;
        this.permissions = parcel.createStringArrayList();
        this.status = ProductStatus.valueOf(parcel.readString());
        this.cloudStorageStatus = CloudStorageStatus.valueOf(parcel.readString());
        this.isFavorite = parcel.readByte() != 0;
        this.livestreamingStatus = LivestreamingStatus.valueOf(parcel.readString());
        this.livestreamingAudioHookingEnabled = parcel.readByte() == 1;
        this.chromecastAudioEnabled = parcel.readByte() == 1 ? true : z;
        this.microphoneUsage = MicrophoneUsage.valueOf(parcel.readString());
        this.minRecommendedVersionCode = parcel.readInt();
        this.minRequiredVersionCode = parcel.readInt();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.itemId);
        parcel.writeString(this.offerId);
        parcel.writeString(this.name);
        parcel.writeString(this.applicationGroupingId);
        parcel.writeString(this.packageName);
        parcel.writeString(this.displayVersion);
        parcel.writeString(this.squareImageSource);
        parcel.writeString(this.mainImageSource);
        parcel.writeString(this.tinyImageSource);
        parcel.writeString(this.price);
        parcel.writeString(this.strikethroughPrice);
        parcel.writeString(this.promoBenefit);
        parcel.writeString(this.comfortRatingsConst);
        parcel.writeString(this.comfortRatingsName);
        parcel.writeString(this.smallLandscapeImage);
        parcel.writeLong(this.downloadedSize);
        parcel.writeLong(this.patchDownloadsize);
        parcel.writeLong(this.installationSize);
        parcel.writeInt(this.latestVersionCode);
        parcel.writeString(this.latestVersionName);
        parcel.writeInt(this.latestTargetSdkVersion);
        parcel.writeInt(this.userQualityRating);
        parcel.writeInt(this.qualityRatingsCount);
        parcel.writeFloat(this.qualityRatingsAverage);
        parcel.writeByte(this.isFree ? (byte) 1 : 0);
        parcel.writeByte(this.isOwnedByCurrentUser ? (byte) 1 : 0);
        parcel.writeStringList(this.permissions);
        parcel.writeString(this.status.name());
        parcel.writeString(this.cloudStorageStatus.name());
        parcel.writeByte(this.isFavorite ? (byte) 1 : 0);
        parcel.writeString(this.livestreamingStatus.name());
        parcel.writeByte(this.livestreamingAudioHookingEnabled ? (byte) 1 : 0);
        parcel.writeByte(this.chromecastAudioEnabled ? (byte) 1 : 0);
        parcel.writeString(this.microphoneUsage.name());
        parcel.writeInt(this.minRecommendedVersionCode);
        parcel.writeInt(this.minRequiredVersionCode);
    }

    public ProductModel newPurchasedModel() {
        return new ProductModel(this.itemId, this.offerId, this.name, this.applicationGroupingId, this.packageName, this.displayVersion, this.squareImageSource, this.mainImageSource, this.tinyImageSource, this.price, this.strikethroughPrice, this.promoBenefit, this.comfortRatingsConst, this.comfortRatingsName, this.smallLandscapeImage, this.downloadedSize, this.patchDownloadsize, this.installationSize, this.latestVersionCode, this.latestVersionName, this.latestTargetSdkVersion, this.userQualityRating, this.qualityRatingsCount, this.qualityRatingsAverage, this.isFree, true, this.permissions, this.status, this.cloudStorageStatus, this.isFavorite, this.livestreamingStatus, this.livestreamingAudioHookingEnabled, this.chromecastAudioEnabled, this.microphoneUsage, this.minRecommendedVersionCode, this.minRequiredVersionCode);
    }

    public CharSequence getPriceText(Context context, int i, int i2, int i3) {
        return new OfferPriceHelper(context, i, i2, i3).getPriceText(this.isFree, this.price, this.strikethroughPrice, this.promoBenefit);
    }
}
