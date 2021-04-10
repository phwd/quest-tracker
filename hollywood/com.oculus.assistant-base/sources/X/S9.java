package X;

import android.app.PendingIntent;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.ConnectionResult;

public final class S9 implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int A00 = C0326Rd.A00(parcel);
        PendingIntent pendingIntent = null;
        int i = 0;
        String str = null;
        int i2 = 0;
        while (parcel.dataPosition() < A00) {
            int readInt = parcel.readInt();
            int i3 = readInt & 65535;
            if (i3 == 1) {
                i = C0326Rd.A01(parcel, readInt);
            } else if (i3 == 2) {
                i2 = C0326Rd.A01(parcel, readInt);
            } else if (i3 == 3) {
                pendingIntent = (PendingIntent) C0326Rd.A03(parcel, readInt, PendingIntent.CREATOR);
            } else if (i3 != 4) {
                C0326Rd.A06(parcel, readInt);
            } else {
                str = C0326Rd.A04(parcel, readInt);
            }
        }
        C0326Rd.A05(parcel, A00);
        return new ConnectionResult(i, i2, pendingIntent, str);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new ConnectionResult[i];
    }
}
