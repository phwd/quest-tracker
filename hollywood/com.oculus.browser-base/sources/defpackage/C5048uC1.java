package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.auth.AccountChangeEvent;

/* renamed from: uC1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C5048uC1 implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        int u = AbstractC5588xO0.u(parcel);
        String str = null;
        String str2 = null;
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        long j = 0;
        while (parcel.dataPosition() < u) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 1:
                    i = AbstractC5588xO0.p(parcel, readInt);
                    break;
                case 2:
                    j = AbstractC5588xO0.r(parcel, readInt);
                    break;
                case 3:
                    str = AbstractC5588xO0.e(parcel, readInt);
                    break;
                case 4:
                    i2 = AbstractC5588xO0.p(parcel, readInt);
                    break;
                case 5:
                    i3 = AbstractC5588xO0.p(parcel, readInt);
                    break;
                case 6:
                    str2 = AbstractC5588xO0.e(parcel, readInt);
                    break;
                default:
                    AbstractC5588xO0.t(parcel, readInt);
                    break;
            }
        }
        AbstractC5588xO0.j(parcel, u);
        return new AccountChangeEvent(i, j, str, i2, i3, str2);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new AccountChangeEvent[i];
    }
}
