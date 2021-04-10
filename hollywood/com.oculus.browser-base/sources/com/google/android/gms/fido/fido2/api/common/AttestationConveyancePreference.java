package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public enum AttestationConveyancePreference implements Parcelable {
    NONE("none"),
    INDIRECT("indirect"),
    DIRECT("direct");
    
    public static final Parcelable.Creator CREATOR = new C5224vE1();

    /* renamed from: J  reason: collision with root package name */
    public final String f9661J;

    /* access modifiers changed from: public */
    AttestationConveyancePreference(String str) {
        this.f9661J = str;
    }

    public static AttestationConveyancePreference b(String str) {
        AttestationConveyancePreference[] values = values();
        for (AttestationConveyancePreference attestationConveyancePreference : values) {
            if (str.equals(attestationConveyancePreference.f9661J)) {
                return attestationConveyancePreference;
            }
        }
        throw new C2544fb(str);
    }

    public final int describeContents() {
        return 0;
    }

    public final String toString() {
        return this.f9661J;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f9661J);
    }
}
