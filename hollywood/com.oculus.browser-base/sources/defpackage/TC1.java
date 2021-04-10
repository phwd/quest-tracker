package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.location.zzad;

/* renamed from: TC1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class TC1 implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        int u = AbstractC5588xO0.u(parcel);
        Status status = null;
        while (parcel.dataPosition() < u) {
            int readInt = parcel.readInt();
            if ((65535 & readInt) != 1) {
                AbstractC5588xO0.t(parcel, readInt);
            } else {
                status = (Status) AbstractC5588xO0.d(parcel, readInt, Status.CREATOR);
            }
        }
        AbstractC5588xO0.j(parcel, u);
        return new zzad(status);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new zzad[i];
    }
}
