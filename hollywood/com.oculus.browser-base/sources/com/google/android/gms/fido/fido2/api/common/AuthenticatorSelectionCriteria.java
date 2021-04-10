package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import java.util.Arrays;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class AuthenticatorSelectionCriteria extends AbstractSafeParcelable {
    public static final Parcelable.Creator CREATOR = new LG1();
    public final Attachment F;
    public final Boolean G;
    public final zzad H;

    public AuthenticatorSelectionCriteria(String str, Boolean bool, String str2) {
        if (str == null) {
            this.F = null;
        } else {
            try {
                this.F = Attachment.b(str);
            } catch (C2373eb e) {
                throw new IllegalArgumentException(e);
            }
        }
        this.G = bool;
        if (str2 == null) {
            this.H = null;
            return;
        }
        try {
            this.H = zzad.b(str2);
        } catch (QC1 e2) {
            throw new IllegalArgumentException(e2);
        }
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof AuthenticatorSelectionCriteria)) {
            return false;
        }
        AuthenticatorSelectionCriteria authenticatorSelectionCriteria = (AuthenticatorSelectionCriteria) obj;
        if (!AbstractC0895Oq0.a(this.F, authenticatorSelectionCriteria.F) || !AbstractC0895Oq0.a(this.G, authenticatorSelectionCriteria.G) || !AbstractC0895Oq0.a(this.H, authenticatorSelectionCriteria.H)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.F, this.G, this.H});
    }

    public void writeToParcel(Parcel parcel, int i) {
        String str;
        int l = AbstractC5758yO0.l(parcel, 20293);
        Attachment attachment = this.F;
        String str2 = null;
        if (attachment == null) {
            str = null;
        } else {
            str = attachment.I;
        }
        AbstractC5758yO0.g(parcel, 2, str, false);
        Boolean bool = this.G;
        if (bool != null) {
            AbstractC5758yO0.o(parcel, 3, 4);
            parcel.writeInt(bool.booleanValue() ? 1 : 0);
        }
        zzad zzad = this.H;
        if (zzad != null) {
            str2 = zzad.f9668J;
        }
        AbstractC5758yO0.g(parcel, 4, str2, false);
        AbstractC5758yO0.n(parcel, l);
    }
}
