package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.ClientIdentity;
import com.google.android.gms.internal.location.zzbd;
import com.google.android.gms.location.LocationRequest;
import com.oculus.os.Version;
import java.util.List;

/* renamed from: CE1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class CE1 implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        int u = AbstractC5588xO0.u(parcel);
        List list = zzbd.F;
        boolean z = false;
        boolean z2 = false;
        boolean z3 = false;
        LocationRequest locationRequest = null;
        String str = null;
        String str2 = null;
        while (parcel.dataPosition() < u) {
            int readInt = parcel.readInt();
            int i = 65535 & readInt;
            if (i != 1) {
                switch (i) {
                    case 5:
                        list = AbstractC5588xO0.i(parcel, readInt, ClientIdentity.CREATOR);
                        continue;
                    case 6:
                        str = AbstractC5588xO0.e(parcel, readInt);
                        continue;
                    case Version.VERSION_7 /*{ENCODED_INT: 7}*/:
                        z = AbstractC5588xO0.k(parcel, readInt);
                        continue;
                    case Version.VERSION_8 /*{ENCODED_INT: 8}*/:
                        z2 = AbstractC5588xO0.k(parcel, readInt);
                        continue;
                    case Version.VERSION_9 /*{ENCODED_INT: 9}*/:
                        z3 = AbstractC5588xO0.k(parcel, readInt);
                        continue;
                    case Version.VERSION_10 /*{ENCODED_INT: 10}*/:
                        str2 = AbstractC5588xO0.e(parcel, readInt);
                        continue;
                    default:
                        AbstractC5588xO0.t(parcel, readInt);
                        continue;
                }
            } else {
                locationRequest = (LocationRequest) AbstractC5588xO0.d(parcel, readInt, LocationRequest.CREATOR);
            }
        }
        AbstractC5588xO0.j(parcel, u);
        return new zzbd(locationRequest, list, str, z, z2, z3, str2);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new zzbd[i];
    }
}
