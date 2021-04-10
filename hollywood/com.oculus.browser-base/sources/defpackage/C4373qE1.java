package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.cast.AdBreakInfo;
import com.oculus.os.Version;

/* renamed from: qE1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C4373qE1 implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        int u = AbstractC5588xO0.u(parcel);
        boolean z = false;
        boolean z2 = false;
        String str = null;
        String[] strArr = null;
        long j = 0;
        long j2 = 0;
        while (parcel.dataPosition() < u) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 2:
                    j = AbstractC5588xO0.r(parcel, readInt);
                    break;
                case 3:
                    str = AbstractC5588xO0.e(parcel, readInt);
                    break;
                case 4:
                    j2 = AbstractC5588xO0.r(parcel, readInt);
                    break;
                case 5:
                    z = AbstractC5588xO0.k(parcel, readInt);
                    break;
                case 6:
                    strArr = AbstractC5588xO0.f(parcel, readInt);
                    break;
                case Version.VERSION_7:
                    z2 = AbstractC5588xO0.k(parcel, readInt);
                    break;
                default:
                    AbstractC5588xO0.t(parcel, readInt);
                    break;
            }
        }
        AbstractC5588xO0.j(parcel, u);
        return new AdBreakInfo(j, str, j2, z, strArr, z2);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new AdBreakInfo[i];
    }
}
