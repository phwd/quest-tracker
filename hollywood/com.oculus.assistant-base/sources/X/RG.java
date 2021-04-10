package X;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Scope;

public final class RG implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int A00 = C0326Rd.A00(parcel);
        int i = 0;
        String str = null;
        while (parcel.dataPosition() < A00) {
            int readInt = parcel.readInt();
            int i2 = readInt & 65535;
            if (i2 == 1) {
                i = C0326Rd.A01(parcel, readInt);
            } else if (i2 != 2) {
                C0326Rd.A06(parcel, readInt);
            } else {
                str = C0326Rd.A04(parcel, readInt);
            }
        }
        C0326Rd.A05(parcel, A00);
        return new Scope(i, str);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new Scope[i];
    }
}
