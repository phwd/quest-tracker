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
        public Category createFromParcel(Parcel in) {
            return (Category) in.readSerializable();
        }

        @Override // android.os.Parcelable.Creator
        public Category[] newArray(int size) {
            return new Category[size];
        }
    };
    private final String value;

    private Category(String value2) {
        this.value = value2;
    }

    public static Category fromString(String value2) throws IllegalArgumentException {
        Category[] values = values();
        for (Category category : values) {
            if (category.value.equals(value2)) {
                return category;
            }
        }
        throw new IllegalArgumentException(String.format("%s: unrecognised license category", value2));
    }

    public String getValue() {
        return this.value;
    }

    public String toString() {
        return this.value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        out.writeSerializable(this);
    }
}
