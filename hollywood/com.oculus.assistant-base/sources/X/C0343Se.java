package X;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.zat;
import com.google.android.gms.signin.internal.zaj;

/* renamed from: X.Se  reason: case insensitive filesystem */
public final class C0343Se implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int A00 = C0326Rd.A00(parcel);
        int i = 0;
        zat zat = null;
        while (parcel.dataPosition() < A00) {
            int readInt = parcel.readInt();
            int i2 = readInt & 65535;
            if (i2 == 1) {
                i = C0326Rd.A01(parcel, readInt);
            } else if (i2 != 2) {
                C0326Rd.A06(parcel, readInt);
            } else {
                zat = (zat) C0326Rd.A03(parcel, readInt, zat.CREATOR);
            }
        }
        C0326Rd.A05(parcel, A00);
        return new zaj(i, zat);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new zaj[i];
    }
}
