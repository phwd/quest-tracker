package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.fido.fido2.api.common.UvmEntry;

/* renamed from: bD1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C1807bD1 implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        int u = AbstractC5588xO0.u(parcel);
        int i = 0;
        short s = 0;
        short s2 = 0;
        while (parcel.dataPosition() < u) {
            int readInt = parcel.readInt();
            int i2 = 65535 & readInt;
            if (i2 == 1) {
                i = AbstractC5588xO0.p(parcel, readInt);
            } else if (i2 == 2) {
                AbstractC5588xO0.v(parcel, readInt, 4);
                s = (short) parcel.readInt();
            } else if (i2 != 3) {
                AbstractC5588xO0.t(parcel, readInt);
            } else {
                AbstractC5588xO0.v(parcel, readInt, 4);
                s2 = (short) parcel.readInt();
            }
        }
        AbstractC5588xO0.j(parcel, u);
        return new UvmEntry(i, s, s2);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new UvmEntry[i];
    }
}
