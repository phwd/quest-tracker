package com.oculus.license;

import android.os.Parcel;
import android.os.Parcelable;

public enum Category implements Parcelable {
    PurchasableItem,
    Development,
    OnDemand,
    Provisional;
    
    public static final Parcelable.Creator<Category> CREATOR = null;

    public static Category fromString(String str) throws IllegalArgumentException {
        throw new RuntimeException("Stub!");
    }

    public int describeContents() {
        throw new RuntimeException("Stub!");
    }

    public String getValue() {
        throw new RuntimeException("Stub!");
    }

    public String toString() {
        throw new RuntimeException("Stub!");
    }

    public void writeToParcel(Parcel parcel, int i) {
        throw new RuntimeException("Stub!");
    }
}
