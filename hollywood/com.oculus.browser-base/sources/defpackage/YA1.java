package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.ClientIdentity;

/* renamed from: YA1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class YA1 implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        int u = AbstractC5588xO0.u(parcel);
        int i = 0;
        String str = null;
        while (parcel.dataPosition() < u) {
            int readInt = parcel.readInt();
            int i2 = 65535 & readInt;
            if (i2 == 1) {
                i = AbstractC5588xO0.p(parcel, readInt);
            } else if (i2 != 2) {
                AbstractC5588xO0.t(parcel, readInt);
            } else {
                str = AbstractC5588xO0.e(parcel, readInt);
            }
        }
        AbstractC5588xO0.j(parcel, u);
        return new ClientIdentity(i, str);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new ClientIdentity[i];
    }
}
