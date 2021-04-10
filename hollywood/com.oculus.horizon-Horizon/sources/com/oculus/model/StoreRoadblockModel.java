package com.oculus.model;

import android.os.Parcel;
import android.os.Parcelable;
import javax.annotation.Nullable;

public class StoreRoadblockModel implements Parcelable {
    public static final Parcelable.Creator<StoreRoadblockModel> CREATOR = new Parcelable.Creator<StoreRoadblockModel>() {
        /* class com.oculus.model.StoreRoadblockModel.AnonymousClass1 */

        /* Return type fixed from 'java.lang.Object' to match base method */
        @Override // android.os.Parcelable.Creator
        public final StoreRoadblockModel createFromParcel(Parcel parcel) {
            return new StoreRoadblockModel(parcel);
        }

        /* Return type fixed from 'java.lang.Object[]' to match base method */
        @Override // android.os.Parcelable.Creator
        public final StoreRoadblockModel[] newArray(int i) {
            return new StoreRoadblockModel[i];
        }
    };
    @Nullable
    public final String category;
    public final String key;
    @Nullable
    public final LazyString mButtonText;
    public final LazyStringWithUri mDescription;
    @Nullable
    public final LazyImage mImage;
    @Nullable
    public final LazyStringWithUri mSecondDescription;
    public final LazyString mTitle;

    public static class Builder {
        public LazyString mButtonText;
        public String mCategory;
        public LazyStringWithUri mDescription;
        public LazyImage mImage;
        public String mKey;
        public LazyStringWithUri mSecondDescription;
        public LazyString mTitle;
    }

    public final int describeContents() {
        return 0;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.key);
        parcel.writeParcelable(this.mTitle, i);
        parcel.writeParcelable(this.mDescription, i);
        parcel.writeParcelable(this.mSecondDescription, i);
        parcel.writeParcelable(this.mImage, i);
        parcel.writeParcelable(this.mButtonText, i);
        parcel.writeString(this.category);
    }

    public StoreRoadblockModel(Parcel parcel) {
        this.key = parcel.readString();
        this.mTitle = (LazyString) parcel.readParcelable(LazyString.class.getClassLoader());
        this.mDescription = (LazyStringWithUri) parcel.readParcelable(LazyStringWithUri.class.getClassLoader());
        this.mSecondDescription = (LazyStringWithUri) parcel.readParcelable(LazyStringWithUri.class.getClassLoader());
        this.mImage = (LazyImage) parcel.readParcelable(LazyImage.class.getClassLoader());
        this.mButtonText = (LazyString) parcel.readParcelable(LazyString.class.getClassLoader());
        this.category = parcel.readString();
    }
}
