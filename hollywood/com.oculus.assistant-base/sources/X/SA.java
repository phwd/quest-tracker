package X;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.Feature;

public final class SA implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int A00 = C0326Rd.A00(parcel);
        String str = null;
        int i = 0;
        long j = -1;
        while (parcel.dataPosition() < A00) {
            int readInt = parcel.readInt();
            int i2 = readInt & 65535;
            if (i2 == 1) {
                str = C0326Rd.A04(parcel, readInt);
            } else if (i2 == 2) {
                i = C0326Rd.A01(parcel, readInt);
            } else if (i2 != 3) {
                C0326Rd.A06(parcel, readInt);
            } else {
                C0326Rd.A07(parcel, readInt, 8);
                j = parcel.readLong();
            }
        }
        C0326Rd.A05(parcel, A00);
        return new Feature(str, i, j);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new Feature[i];
    }
}
