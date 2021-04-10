package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import java.util.Arrays;
import java.util.Objects;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class TokenBinding extends AbstractSafeParcelable {
    public static final Parcelable.Creator CREATOR = new C5558xC1();
    public final TokenBindingStatus F;
    public final String G;

    /* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
    public enum TokenBindingStatus implements Parcelable {
        PRESENT("present"),
        SUPPORTED("supported"),
        NOT_SUPPORTED("not-supported");
        
        public static final Parcelable.Creator CREATOR = new C4558rJ1();

        /* renamed from: J  reason: collision with root package name */
        public final String f9667J;

        /* access modifiers changed from: public */
        TokenBindingStatus(String str) {
            this.f9667J = str;
        }

        public static TokenBindingStatus b(String str) {
            TokenBindingStatus[] values = values();
            for (TokenBindingStatus tokenBindingStatus : values) {
                if (str.equals(tokenBindingStatus.f9667J)) {
                    return tokenBindingStatus;
                }
            }
            throw new C1887bj1(str);
        }

        public final int describeContents() {
            return 0;
        }

        public final String toString() {
            return this.f9667J;
        }

        public final void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(this.f9667J);
        }
    }

    static {
        new TokenBinding("supported", null);
        new TokenBinding("not-supported", null);
    }

    public TokenBinding(String str, String str2) {
        Objects.requireNonNull(str, "null reference");
        try {
            this.F = TokenBindingStatus.b(str);
            this.G = str2;
        } catch (C1887bj1 e) {
            throw new IllegalArgumentException(e);
        }
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof TokenBinding)) {
            return false;
        }
        TokenBinding tokenBinding = (TokenBinding) obj;
        if (!AbstractC4540rD1.a(this.F, tokenBinding.F) || !AbstractC4540rD1.a(this.G, tokenBinding.G)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.F, this.G});
    }

    public void writeToParcel(Parcel parcel, int i) {
        int l = AbstractC5758yO0.l(parcel, 20293);
        AbstractC5758yO0.g(parcel, 2, this.F.f9667J, false);
        AbstractC5758yO0.g(parcel, 3, this.G, false);
        AbstractC5758yO0.n(parcel, l);
    }
}
