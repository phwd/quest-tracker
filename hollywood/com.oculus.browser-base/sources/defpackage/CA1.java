package defpackage;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.signin.internal.zab;

/* renamed from: CA1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class CA1 implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        int u = AbstractC5588xO0.u(parcel);
        int i = 0;
        Intent intent = null;
        int i2 = 0;
        while (parcel.dataPosition() < u) {
            int readInt = parcel.readInt();
            int i3 = 65535 & readInt;
            if (i3 == 1) {
                i = AbstractC5588xO0.p(parcel, readInt);
            } else if (i3 == 2) {
                i2 = AbstractC5588xO0.p(parcel, readInt);
            } else if (i3 != 3) {
                AbstractC5588xO0.t(parcel, readInt);
            } else {
                intent = (Intent) AbstractC5588xO0.d(parcel, readInt, Intent.CREATOR);
            }
        }
        AbstractC5588xO0.j(parcel, u);
        return new zab(i, i2, intent);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new zab[i];
    }
}
