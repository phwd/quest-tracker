package com.google.android.gms.auth;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import java.util.Arrays;
import java.util.List;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class TokenData extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Parcelable.Creator CREATOR = new FH1();
    public final int F;
    public final String G;
    public final Long H;
    public final boolean I;

    /* renamed from: J  reason: collision with root package name */
    public final boolean f9635J;
    public final List K;
    public final String L;

    public TokenData(int i, String str, Long l, boolean z, boolean z2, List list, String str2) {
        this.F = i;
        SE0.f(str);
        this.G = str;
        this.H = l;
        this.I = z;
        this.f9635J = z2;
        this.K = list;
        this.L = str2;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof TokenData)) {
            return false;
        }
        TokenData tokenData = (TokenData) obj;
        if (!TextUtils.equals(this.G, tokenData.G) || !AbstractC0895Oq0.a(this.H, tokenData.H) || this.I != tokenData.I || this.f9635J != tokenData.f9635J || !AbstractC0895Oq0.a(this.K, tokenData.K) || !AbstractC0895Oq0.a(this.L, tokenData.L)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.G, this.H, Boolean.valueOf(this.I), Boolean.valueOf(this.f9635J), this.K, this.L});
    }

    public void writeToParcel(Parcel parcel, int i) {
        int l = AbstractC5758yO0.l(parcel, 20293);
        int i2 = this.F;
        AbstractC5758yO0.o(parcel, 1, 4);
        parcel.writeInt(i2);
        AbstractC5758yO0.g(parcel, 2, this.G, false);
        Long l2 = this.H;
        if (l2 != null) {
            AbstractC5758yO0.o(parcel, 3, 8);
            parcel.writeLong(l2.longValue());
        }
        boolean z = this.I;
        AbstractC5758yO0.o(parcel, 4, 4);
        parcel.writeInt(z ? 1 : 0);
        boolean z2 = this.f9635J;
        AbstractC5758yO0.o(parcel, 5, 4);
        parcel.writeInt(z2 ? 1 : 0);
        AbstractC5758yO0.i(parcel, 6, this.K, false);
        AbstractC5758yO0.g(parcel, 7, this.L, false);
        AbstractC5758yO0.n(parcel, l);
    }
}
