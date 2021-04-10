package X;

import android.app.PendingIntent;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Status;

public final class RH implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int A00 = C0326Rd.A00(parcel);
        String str = null;
        PendingIntent pendingIntent = null;
        ConnectionResult connectionResult = null;
        int i = 0;
        int i2 = 0;
        while (parcel.dataPosition() < A00) {
            int readInt = parcel.readInt();
            int i3 = readInt & 65535;
            if (i3 == 1) {
                i2 = C0326Rd.A01(parcel, readInt);
            } else if (i3 == 2) {
                str = C0326Rd.A04(parcel, readInt);
            } else if (i3 == 3) {
                pendingIntent = (PendingIntent) C0326Rd.A03(parcel, readInt, PendingIntent.CREATOR);
            } else if (i3 == 4) {
                connectionResult = (ConnectionResult) C0326Rd.A03(parcel, readInt, ConnectionResult.CREATOR);
            } else if (i3 != 1000) {
                C0326Rd.A06(parcel, readInt);
            } else {
                i = C0326Rd.A01(parcel, readInt);
            }
        }
        C0326Rd.A05(parcel, A00);
        return new Status(i, i2, str, pendingIntent, connectionResult);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new Status[i];
    }
}
