package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ResolveAccountRequest extends AbstractSafeParcelable {
    public static final Parcelable.Creator CREATOR = new UB1();
    public final int F;
    public final Account G;
    public final int H;
    public final GoogleSignInAccount I;

    public ResolveAccountRequest(int i, Account account, int i2, GoogleSignInAccount googleSignInAccount) {
        this.F = i;
        this.G = account;
        this.H = i2;
        this.I = googleSignInAccount;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int l = AbstractC5758yO0.l(parcel, 20293);
        int i2 = this.F;
        AbstractC5758yO0.o(parcel, 1, 4);
        parcel.writeInt(i2);
        AbstractC5758yO0.f(parcel, 2, this.G, i, false);
        int i3 = this.H;
        AbstractC5758yO0.o(parcel, 3, 4);
        parcel.writeInt(i3);
        AbstractC5758yO0.f(parcel, 4, this.I, i, false);
        AbstractC5758yO0.n(parcel, l);
    }

    public ResolveAccountRequest(Account account, int i, GoogleSignInAccount googleSignInAccount) {
        this.F = 2;
        this.G = account;
        this.H = i;
        this.I = googleSignInAccount;
    }
}
