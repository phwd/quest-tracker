package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.vision.zzae;
import com.google.android.gms.internal.vision.zzan;
import com.google.android.gms.internal.vision.zzy;
import com.oculus.os.Version;

/* renamed from: ZC1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class ZC1 implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        int u = AbstractC5588xO0.u(parcel);
        int i = 0;
        boolean z = false;
        int i2 = 0;
        int i3 = 0;
        zzan[] zzanArr = null;
        zzy zzy = null;
        zzy zzy2 = null;
        zzy zzy3 = null;
        String str = null;
        String str2 = null;
        float f = 0.0f;
        while (parcel.dataPosition() < u) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 2:
                    zzanArr = (zzan[]) AbstractC5588xO0.h(parcel, readInt, zzan.CREATOR);
                    break;
                case 3:
                    zzy = (zzy) AbstractC5588xO0.d(parcel, readInt, zzy.CREATOR);
                    break;
                case 4:
                    zzy2 = (zzy) AbstractC5588xO0.d(parcel, readInt, zzy.CREATOR);
                    break;
                case 5:
                    zzy3 = (zzy) AbstractC5588xO0.d(parcel, readInt, zzy.CREATOR);
                    break;
                case 6:
                    str = AbstractC5588xO0.e(parcel, readInt);
                    break;
                case Version.VERSION_7 /*{ENCODED_INT: 7}*/:
                    f = AbstractC5588xO0.n(parcel, readInt);
                    break;
                case Version.VERSION_8 /*{ENCODED_INT: 8}*/:
                    str2 = AbstractC5588xO0.e(parcel, readInt);
                    break;
                case Version.VERSION_9 /*{ENCODED_INT: 9}*/:
                    i = AbstractC5588xO0.p(parcel, readInt);
                    break;
                case Version.VERSION_10 /*{ENCODED_INT: 10}*/:
                    z = AbstractC5588xO0.k(parcel, readInt);
                    break;
                case Version.VERSION_11 /*{ENCODED_INT: 11}*/:
                    i2 = AbstractC5588xO0.p(parcel, readInt);
                    break;
                case Version.VERSION_12 /*{ENCODED_INT: 12}*/:
                    i3 = AbstractC5588xO0.p(parcel, readInt);
                    break;
                default:
                    AbstractC5588xO0.t(parcel, readInt);
                    break;
            }
        }
        AbstractC5588xO0.j(parcel, u);
        return new zzae(zzanArr, zzy, zzy2, zzy3, str, f, str2, i, z, i2, i3);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new zzae[i];
    }
}
