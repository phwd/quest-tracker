package X;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.signin.internal.zaa;

/* renamed from: X.Sa  reason: case insensitive filesystem */
public final class C0342Sa implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int A00 = C0326Rd.A00(parcel);
        int i = 0;
        Intent intent = null;
        int i2 = 0;
        while (parcel.dataPosition() < A00) {
            int readInt = parcel.readInt();
            int i3 = readInt & 65535;
            if (i3 == 1) {
                i = C0326Rd.A01(parcel, readInt);
            } else if (i3 == 2) {
                i2 = C0326Rd.A01(parcel, readInt);
            } else if (i3 != 3) {
                C0326Rd.A06(parcel, readInt);
            } else {
                intent = (Intent) C0326Rd.A03(parcel, readInt, Intent.CREATOR);
            }
        }
        C0326Rd.A05(parcel, A00);
        return new zaa(i, i2, intent);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new zaa[i];
    }
}
