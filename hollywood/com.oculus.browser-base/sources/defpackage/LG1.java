package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.fido.fido2.api.common.AuthenticatorSelectionCriteria;

/* renamed from: LG1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class LG1 implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        int u = AbstractC5588xO0.u(parcel);
        String str = null;
        Boolean bool = null;
        String str2 = null;
        while (parcel.dataPosition() < u) {
            int readInt = parcel.readInt();
            int i = 65535 & readInt;
            if (i == 2) {
                str = AbstractC5588xO0.e(parcel, readInt);
            } else if (i == 3) {
                int s = AbstractC5588xO0.s(parcel, readInt);
                if (s == 0) {
                    bool = null;
                } else {
                    AbstractC5588xO0.w(parcel, s, 4);
                    bool = Boolean.valueOf(parcel.readInt() != 0);
                }
            } else if (i != 4) {
                AbstractC5588xO0.t(parcel, readInt);
            } else {
                str2 = AbstractC5588xO0.e(parcel, readInt);
            }
        }
        AbstractC5588xO0.j(parcel, u);
        return new AuthenticatorSelectionCriteria(str, bool, str2);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new AuthenticatorSelectionCriteria[i];
    }
}
