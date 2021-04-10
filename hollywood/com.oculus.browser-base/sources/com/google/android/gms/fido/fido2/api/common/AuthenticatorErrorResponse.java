package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Arrays;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class AuthenticatorErrorResponse extends AuthenticatorResponse {
    public static final Parcelable.Creator CREATOR = new CG1();
    public final ErrorCode F;
    public final String G;

    public AuthenticatorErrorResponse(int i, String str) {
        try {
            this.F = ErrorCode.b(i);
            this.G = str;
        } catch (OL e) {
            throw new IllegalArgumentException(e);
        }
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof AuthenticatorErrorResponse)) {
            return false;
        }
        AuthenticatorErrorResponse authenticatorErrorResponse = (AuthenticatorErrorResponse) obj;
        if (!AbstractC0895Oq0.a(this.F, authenticatorErrorResponse.F) || !AbstractC0895Oq0.a(this.G, authenticatorErrorResponse.G)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.F, this.G});
    }

    public String toString() {
        C3173jD1 a2 = AbstractC2149dD1.a(this);
        String valueOf = String.valueOf(this.F.S);
        C5221vD1 vd1 = new C5221vD1(null);
        a2.c.c = vd1;
        a2.c = vd1;
        vd1.b = valueOf;
        vd1.f11468a = "errorCode";
        String str = this.G;
        if (str != null) {
            a2.a("errorMessage", str);
        }
        return a2.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        int l = AbstractC5758yO0.l(parcel, 20293);
        int i2 = this.F.S;
        AbstractC5758yO0.o(parcel, 2, 4);
        parcel.writeInt(i2);
        AbstractC5758yO0.g(parcel, 3, this.G, false);
        AbstractC5758yO0.n(parcel, l);
    }
}
