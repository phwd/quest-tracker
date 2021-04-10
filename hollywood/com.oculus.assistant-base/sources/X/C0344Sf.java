package X;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.zau;
import com.google.android.gms.signin.internal.zak;

/* renamed from: X.Sf  reason: case insensitive filesystem */
public final class C0344Sf implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int A00 = C0326Rd.A00(parcel);
        ConnectionResult connectionResult = null;
        int i = 0;
        zau zau = null;
        while (parcel.dataPosition() < A00) {
            int readInt = parcel.readInt();
            int i2 = readInt & 65535;
            if (i2 == 1) {
                i = C0326Rd.A01(parcel, readInt);
            } else if (i2 == 2) {
                connectionResult = (ConnectionResult) C0326Rd.A03(parcel, readInt, ConnectionResult.CREATOR);
            } else if (i2 != 3) {
                C0326Rd.A06(parcel, readInt);
            } else {
                zau = (zau) C0326Rd.A03(parcel, readInt, zau.CREATOR);
            }
        }
        C0326Rd.A05(parcel, A00);
        return new zak(i, connectionResult, zau);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new zak[i];
    }
}
