package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import java.util.Arrays;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class AuthenticationExtensionsClientOutputs extends AbstractSafeParcelable {
    public static final Parcelable.Creator CREATOR = new ZE1();
    public UvmEntries F;

    public AuthenticationExtensionsClientOutputs(UvmEntries uvmEntries) {
        this.F = uvmEntries;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof AuthenticationExtensionsClientOutputs)) {
            return false;
        }
        return AbstractC0895Oq0.a(this.F, ((AuthenticationExtensionsClientOutputs) obj).F);
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.F});
    }

    public void writeToParcel(Parcel parcel, int i) {
        int l = AbstractC5758yO0.l(parcel, 20293);
        AbstractC5758yO0.f(parcel, 1, this.F, i, false);
        AbstractC5758yO0.n(parcel, l);
    }
}
