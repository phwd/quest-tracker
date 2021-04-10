package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Objects;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public enum PublicKeyCredentialType implements Parcelable {
    PUBLIC_KEY;
    
    public static final Parcelable.Creator CREATOR = new C2850hJ1();

    /* access modifiers changed from: public */
    PublicKeyCredentialType() {
    }

    public static PublicKeyCredentialType b(String str) {
        PublicKeyCredentialType[] values = values();
        for (PublicKeyCredentialType publicKeyCredentialType : values) {
            Objects.requireNonNull(publicKeyCredentialType);
            if (str.equals("public-key")) {
                return publicKeyCredentialType;
            }
        }
        throw new C5405wI0(String.format("PublicKeyCredentialType %s not supported", str));
    }

    public final int describeContents() {
        return 0;
    }

    public final String toString() {
        return "public-key";
    }

    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeString("public-key");
    }
}
