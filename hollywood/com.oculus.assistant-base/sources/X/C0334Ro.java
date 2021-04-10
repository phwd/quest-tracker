package X;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.zao;

/* renamed from: X.Ro  reason: case insensitive filesystem */
public final class C0334Ro implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int A00 = C0326Rd.A00(parcel);
        long j = 0;
        long j2 = 0;
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        while (parcel.dataPosition() < A00) {
            int readInt = parcel.readInt();
            int i4 = readInt & 65535;
            if (i4 == 1) {
                i = C0326Rd.A01(parcel, readInt);
            } else if (i4 == 2) {
                i2 = C0326Rd.A01(parcel, readInt);
            } else if (i4 == 3) {
                i3 = C0326Rd.A01(parcel, readInt);
            } else if (i4 == 4) {
                C0326Rd.A07(parcel, readInt, 8);
                j = parcel.readLong();
            } else if (i4 != 5) {
                C0326Rd.A06(parcel, readInt);
            } else {
                C0326Rd.A07(parcel, readInt, 8);
                j2 = parcel.readLong();
            }
        }
        C0326Rd.A05(parcel, A00);
        return new zao(i, i2, i3, j, j2);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new zao[i];
    }
}
