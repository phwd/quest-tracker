package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.location.LocationRequest;
import com.oculus.os.Version;

/* renamed from: CC1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class CC1 implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        int u = AbstractC5588xO0.u(parcel);
        int i = 102;
        long j = 3600000;
        long j2 = 600000;
        boolean z = false;
        long j3 = Long.MAX_VALUE;
        int i2 = Integer.MAX_VALUE;
        float f = 0.0f;
        long j4 = 0;
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
                    j2 = AbstractC5588xO0.r(parcel, readInt);
                    break;
                case 4:
                    z = AbstractC5588xO0.k(parcel, readInt);
                    break;
                case 5:
                    j3 = AbstractC5588xO0.r(parcel, readInt);
                    break;
                case 6:
                    i2 = AbstractC5588xO0.p(parcel, readInt);
                    break;
                case Version.VERSION_7 /*{ENCODED_INT: 7}*/:
                    f = AbstractC5588xO0.n(parcel, readInt);
                    break;
                case Version.VERSION_8 /*{ENCODED_INT: 8}*/:
                    j4 = AbstractC5588xO0.r(parcel, readInt);
                    break;
                default:
                    AbstractC5588xO0.t(parcel, readInt);
                    break;
            }
        }
        AbstractC5588xO0.j(parcel, u);
        return new LocationRequest(i, j, j2, z, j3, i2, f, j4);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new LocationRequest[i];
    }
}
