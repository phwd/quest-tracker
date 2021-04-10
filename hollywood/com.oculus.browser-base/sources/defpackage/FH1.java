package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.auth.TokenData;
import com.oculus.os.Version;
import java.util.ArrayList;

/* renamed from: FH1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class FH1 implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        int u = AbstractC5588xO0.u(parcel);
        int i = 0;
        boolean z = false;
        boolean z2 = false;
        String str = null;
        Long l = null;
        ArrayList arrayList = null;
        String str2 = null;
        while (parcel.dataPosition() < u) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 1:
                    i = AbstractC5588xO0.p(parcel, readInt);
                    break;
                case 2:
                    str = AbstractC5588xO0.e(parcel, readInt);
                    break;
                case 3:
                    int s = AbstractC5588xO0.s(parcel, readInt);
                    if (s != 0) {
                        AbstractC5588xO0.w(parcel, s, 8);
                        l = Long.valueOf(parcel.readLong());
                        break;
                    } else {
                        l = null;
                        break;
                    }
                case 4:
                    z = AbstractC5588xO0.k(parcel, readInt);
                    break;
                case 5:
                    z2 = AbstractC5588xO0.k(parcel, readInt);
                    break;
                case 6:
                    arrayList = AbstractC5588xO0.g(parcel, readInt);
                    break;
                case Version.VERSION_7 /*{ENCODED_INT: 7}*/:
                    str2 = AbstractC5588xO0.e(parcel, readInt);
                    break;
                default:
                    AbstractC5588xO0.t(parcel, readInt);
                    break;
            }
        }
        AbstractC5588xO0.j(parcel, u);
        return new TokenData(i, str, l, z, z2, arrayList, str2);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new TokenData[i];
    }
}
