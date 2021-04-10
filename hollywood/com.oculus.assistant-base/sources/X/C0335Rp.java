package X;

import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.internal.zat;

/* renamed from: X.Rp  reason: case insensitive filesystem */
public final class C0335Rp implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int A00 = C0326Rd.A00(parcel);
        Account account = null;
        int i = 0;
        GoogleSignInAccount googleSignInAccount = null;
        int i2 = 0;
        while (parcel.dataPosition() < A00) {
            int readInt = parcel.readInt();
            int i3 = readInt & 65535;
            if (i3 == 1) {
                i = C0326Rd.A01(parcel, readInt);
            } else if (i3 == 2) {
                account = (Account) C0326Rd.A03(parcel, readInt, Account.CREATOR);
            } else if (i3 == 3) {
                i2 = C0326Rd.A01(parcel, readInt);
            } else if (i3 != 4) {
                C0326Rd.A06(parcel, readInt);
            } else {
                googleSignInAccount = (GoogleSignInAccount) C0326Rd.A03(parcel, readInt, GoogleSignInAccount.CREATOR);
            }
        }
        C0326Rd.A05(parcel, A00);
        return new zat(i, account, i2, googleSignInAccount);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new zat[i];
    }
}
