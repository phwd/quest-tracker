package defpackage;

import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.internal.ResolveAccountRequest;

/* renamed from: UB1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class UB1 implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        int u = AbstractC5588xO0.u(parcel);
        Account account = null;
        int i = 0;
        int i2 = 0;
        GoogleSignInAccount googleSignInAccount = null;
        while (parcel.dataPosition() < u) {
            int readInt = parcel.readInt();
            int i3 = 65535 & readInt;
            if (i3 == 1) {
                i = AbstractC5588xO0.p(parcel, readInt);
            } else if (i3 == 2) {
                account = (Account) AbstractC5588xO0.d(parcel, readInt, Account.CREATOR);
            } else if (i3 == 3) {
                i2 = AbstractC5588xO0.p(parcel, readInt);
            } else if (i3 != 4) {
                AbstractC5588xO0.t(parcel, readInt);
            } else {
                googleSignInAccount = (GoogleSignInAccount) AbstractC5588xO0.d(parcel, readInt, GoogleSignInAccount.CREATOR);
            }
        }
        AbstractC5588xO0.j(parcel, u);
        return new ResolveAccountRequest(i, account, i2, googleSignInAccount);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new ResolveAccountRequest[i];
    }
}
