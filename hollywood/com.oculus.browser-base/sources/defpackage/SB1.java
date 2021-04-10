package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.ResolveAccountResponse;
import com.google.android.gms.signin.internal.zak;

/* renamed from: SB1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class SB1 implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        int u = AbstractC5588xO0.u(parcel);
        ConnectionResult connectionResult = null;
        int i = 0;
        ResolveAccountResponse resolveAccountResponse = null;
        while (parcel.dataPosition() < u) {
            int readInt = parcel.readInt();
            int i2 = 65535 & readInt;
            if (i2 == 1) {
                i = AbstractC5588xO0.p(parcel, readInt);
            } else if (i2 == 2) {
                connectionResult = (ConnectionResult) AbstractC5588xO0.d(parcel, readInt, ConnectionResult.CREATOR);
            } else if (i2 != 3) {
                AbstractC5588xO0.t(parcel, readInt);
            } else {
                resolveAccountResponse = (ResolveAccountResponse) AbstractC5588xO0.d(parcel, readInt, ResolveAccountResponse.CREATOR);
            }
        }
        AbstractC5588xO0.j(parcel, u);
        return new zak(i, connectionResult, resolveAccountResponse);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new zak[i];
    }
}
