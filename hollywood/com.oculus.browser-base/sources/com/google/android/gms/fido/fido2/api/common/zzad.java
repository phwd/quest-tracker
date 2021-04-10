package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public enum zzad implements Parcelable {
    USER_VERIFICATION_REQUIRED("required"),
    USER_VERIFICATION_PREFERRED("preferred"),
    USER_VERIFICATION_DISCOURAGED("discouraged");
    
    public static final Parcelable.Creator CREATOR = new EC1();

    /* renamed from: J  reason: collision with root package name */
    public final String f9668J;

    /* access modifiers changed from: public */
    zzad(String str) {
        this.f9668J = str;
    }

    public static zzad b(String str) {
        zzad[] values = values();
        for (zzad zzad : values) {
            if (str.equals(zzad.f9668J)) {
                return zzad;
            }
        }
        throw new QC1(str);
    }

    public final int describeContents() {
        return 0;
    }

    public final String toString() {
        return this.f9668J;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f9668J);
    }
}
