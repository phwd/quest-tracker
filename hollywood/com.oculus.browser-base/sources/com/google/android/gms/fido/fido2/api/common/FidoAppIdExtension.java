package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import java.util.Arrays;
import java.util.Objects;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class FidoAppIdExtension extends AbstractSafeParcelable {
    public static final Parcelable.Creator CREATOR = new C3359kI1();
    public final String F;

    public FidoAppIdExtension(String str) {
        Objects.requireNonNull(str, "null reference");
        this.F = str;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof FidoAppIdExtension)) {
            return false;
        }
        return this.F.equals(((FidoAppIdExtension) obj).F);
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.F});
    }

    public void writeToParcel(Parcel parcel, int i) {
        int l = AbstractC5758yO0.l(parcel, 20293);
        AbstractC5758yO0.g(parcel, 2, this.F, false);
        AbstractC5758yO0.n(parcel, l);
    }
}
