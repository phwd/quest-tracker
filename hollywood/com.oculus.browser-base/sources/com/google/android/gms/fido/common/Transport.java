package com.google.android.gms.fido.common;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.ReflectedParcelable;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public enum Transport implements ReflectedParcelable {
    BLUETOOTH_CLASSIC("bt"),
    BLUETOOTH_LOW_ENERGY("ble"),
    NFC("nfc"),
    USB("usb"),
    INTERNAL("internal"),
    CABLE("cable");
    
    public static final Parcelable.Creator CREATOR = new C4708sC1();
    public final String M;

    /* access modifiers changed from: public */
    Transport(String str) {
        this.M = str;
    }

    public final int describeContents() {
        return 0;
    }

    public final String toString() {
        return this.M;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.M);
    }
}
