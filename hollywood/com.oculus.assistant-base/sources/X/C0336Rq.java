package X;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.zau;

/* renamed from: X.Rq  reason: case insensitive filesystem */
public final class C0336Rq implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int A00 = C0326Rd.A00(parcel);
        IBinder iBinder = null;
        ConnectionResult connectionResult = null;
        int i = 0;
        boolean z = false;
        boolean z2 = false;
        while (parcel.dataPosition() < A00) {
            int readInt = parcel.readInt();
            int i2 = readInt & 65535;
            if (i2 == 1) {
                i = C0326Rd.A01(parcel, readInt);
            } else if (i2 == 2) {
                int A02 = C0326Rd.A02(parcel, readInt);
                int dataPosition = parcel.dataPosition();
                if (A02 == 0) {
                    iBinder = null;
                } else {
                    iBinder = parcel.readStrongBinder();
                    parcel.setDataPosition(dataPosition + A02);
                }
            } else if (i2 == 3) {
                connectionResult = (ConnectionResult) C0326Rd.A03(parcel, readInt, ConnectionResult.CREATOR);
            } else if (i2 == 4) {
                z = C0326Rd.A08(parcel, readInt);
            } else if (i2 != 5) {
                C0326Rd.A06(parcel, readInt);
            } else {
                z2 = C0326Rd.A08(parcel, readInt);
            }
        }
        C0326Rd.A05(parcel, A00);
        return new zau(i, iBinder, connectionResult, z, z2);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new zau[i];
    }
}
