package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.vision.zzai;
import com.google.android.gms.internal.vision.zzan;
import com.google.android.gms.internal.vision.zzy;
import com.oculus.os.Version;

/* renamed from: HD1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class HD1 implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        int u = AbstractC5588xO0.u(parcel);
        zzai[] zzaiArr = null;
        zzy zzy = null;
        zzy zzy2 = null;
        String str = null;
        String str2 = null;
        float f = 0.0f;
        boolean z = false;
        while (parcel.dataPosition() < u) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 2:
                    zzaiArr = (zzai[]) AbstractC5588xO0.h(parcel, readInt, zzai.CREATOR);
                    break;
                case 3:
                    zzy = (zzy) AbstractC5588xO0.d(parcel, readInt, zzy.CREATOR);
                    break;
                case 4:
                    zzy2 = (zzy) AbstractC5588xO0.d(parcel, readInt, zzy.CREATOR);
                    break;
                case 5:
                    str = AbstractC5588xO0.e(parcel, readInt);
                    break;
                case 6:
                    f = AbstractC5588xO0.n(parcel, readInt);
                    break;
                case Version.VERSION_7 /*{ENCODED_INT: 7}*/:
                    str2 = AbstractC5588xO0.e(parcel, readInt);
                    break;
                case Version.VERSION_8 /*{ENCODED_INT: 8}*/:
                    z = AbstractC5588xO0.k(parcel, readInt);
                    break;
                default:
                    AbstractC5588xO0.t(parcel, readInt);
                    break;
            }
        }
        AbstractC5588xO0.j(parcel, u);
        return new zzan(zzaiArr, zzy, zzy2, str, f, str2, z);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new zzan[i];
    }
}
