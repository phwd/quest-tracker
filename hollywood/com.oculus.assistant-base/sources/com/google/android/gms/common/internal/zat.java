package com.google.android.gms.common.internal;

import X.C0327Re;
import X.C0335Rp;
import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

public final class zat extends AbstractSafeParcelable {
    public static final Parcelable.Creator CREATOR = new C0335Rp();
    public final int A00;
    public final int A01;
    public final Account A02;
    public final GoogleSignInAccount A03;

    public final void writeToParcel(Parcel parcel, int i) {
        int A002 = C0327Re.A00(parcel, 20293);
        C0327Re.A02(parcel, 1, this.A00);
        C0327Re.A04(parcel, 2, this.A02, i);
        C0327Re.A02(parcel, 3, this.A01);
        C0327Re.A04(parcel, 4, this.A03, i);
        C0327Re.A01(parcel, A002);
    }

    public zat(int i, Account account, int i2, GoogleSignInAccount googleSignInAccount) {
        this.A00 = i;
        this.A02 = account;
        this.A01 = i2;
        this.A03 = googleSignInAccount;
    }
}
