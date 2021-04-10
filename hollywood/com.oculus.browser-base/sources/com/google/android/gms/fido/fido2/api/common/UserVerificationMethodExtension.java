package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import java.util.Arrays;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class UserVerificationMethodExtension extends AbstractSafeParcelable {
    public static final Parcelable.Creator CREATOR = new C5728yC1();
    public final boolean F;

    public UserVerificationMethodExtension(boolean z) {
        this.F = z;
    }

    public boolean equals(Object obj) {
        if ((obj instanceof UserVerificationMethodExtension) && this.F == ((UserVerificationMethodExtension) obj).F) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{Boolean.valueOf(this.F)});
    }

    public void writeToParcel(Parcel parcel, int i) {
        int l = AbstractC5758yO0.l(parcel, 20293);
        boolean z = this.F;
        AbstractC5758yO0.o(parcel, 1, 4);
        parcel.writeInt(z ? 1 : 0);
        AbstractC5758yO0.n(parcel, l);
    }
}
