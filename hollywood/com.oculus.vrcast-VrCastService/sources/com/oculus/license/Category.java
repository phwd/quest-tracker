package com.oculus.license;

import android.os.Parcel;
import android.os.Parcelable;

public enum Category implements Parcelable {
    PurchasableItem("purchasable_item"),
    Development("development"),
    OnDemand("on_demand"),
    Provisional("provisional");
    
    public static final Parcelable.Creator<Category> CREATOR = new Parcelable.Creator<Category>() {
        /* class com.oculus.license.Category.AnonymousClass1 */

        @Override // android.os.Parcelable.Creator
        public Category createFromParcel(Parcel parcel) {
            return (Category) parcel.readSerializable();
        }

        @Override // android.os.Parcelable.Creator
        public Category[] newArray(int i) {
            return new Category[i];
        }
    };
    private final String value;

    public int describeContents() {
        return 0;
    }

    private Category(String str) {
        this.value = str;
    }

    public String toString() {
        return this.value;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeSerializable(this);
    }
}
