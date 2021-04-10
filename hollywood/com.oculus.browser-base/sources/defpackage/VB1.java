package defpackage;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.ResolveAccountResponse;

/* renamed from: VB1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class VB1 implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        int u = AbstractC5588xO0.u(parcel);
        IBinder iBinder = null;
        ConnectionResult connectionResult = null;
        int i = 0;
        boolean z = false;
        boolean z2 = false;
        while (parcel.dataPosition() < u) {
            int readInt = parcel.readInt();
            int i2 = 65535 & readInt;
            if (i2 == 1) {
                i = AbstractC5588xO0.p(parcel, readInt);
            } else if (i2 == 2) {
                iBinder = AbstractC5588xO0.o(parcel, readInt);
            } else if (i2 == 3) {
                connectionResult = (ConnectionResult) AbstractC5588xO0.d(parcel, readInt, ConnectionResult.CREATOR);
            } else if (i2 == 4) {
                z = AbstractC5588xO0.k(parcel, readInt);
            } else if (i2 != 5) {
                AbstractC5588xO0.t(parcel, readInt);
            } else {
                z2 = AbstractC5588xO0.k(parcel, readInt);
            }
        }
        AbstractC5588xO0.j(parcel, u);
        return new ResolveAccountResponse(i, iBinder, connectionResult, z, z2);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new ResolveAccountResponse[i];
    }
}
