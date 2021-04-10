package com.google.android.gms.auth;

import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class AccountChangeEventsRequest extends AbstractSafeParcelable {
    public static final Parcelable.Creator CREATOR = new C5904zE1();
    public final int F;
    public int G;
    @Deprecated
    public String H;
    public Account I;

    public AccountChangeEventsRequest(int i, int i2, String str, Account account) {
        this.F = i;
        this.G = i2;
        this.H = str;
        if (account != null || TextUtils.isEmpty(str)) {
            this.I = account;
        } else {
            this.I = new Account(str, "com.google");
        }
    }

    public void writeToParcel(Parcel parcel, int i) {
        int l = AbstractC5758yO0.l(parcel, 20293);
        int i2 = this.F;
        AbstractC5758yO0.o(parcel, 1, 4);
        parcel.writeInt(i2);
        int i3 = this.G;
        AbstractC5758yO0.o(parcel, 2, 4);
        parcel.writeInt(i3);
        AbstractC5758yO0.g(parcel, 3, this.H, false);
        AbstractC5758yO0.f(parcel, 4, this.I, i, false);
        AbstractC5758yO0.n(parcel, l);
    }

    public AccountChangeEventsRequest() {
        this.F = 1;
    }
}
