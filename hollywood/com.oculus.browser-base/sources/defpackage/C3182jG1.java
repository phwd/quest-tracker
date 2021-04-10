package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.vision.face.internal.client.zze;
import com.oculus.os.Version;

/* renamed from: jG1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C3182jG1 implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        int u = AbstractC5588xO0.u(parcel);
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        boolean z = false;
        boolean z2 = false;
        float f = -1.0f;
        while (parcel.dataPosition() < u) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 2:
                    i = AbstractC5588xO0.p(parcel, readInt);
                    break;
                case 3:
                    i2 = AbstractC5588xO0.p(parcel, readInt);
                    break;
                case 4:
                    i3 = AbstractC5588xO0.p(parcel, readInt);
                    break;
                case 5:
                    z = AbstractC5588xO0.k(parcel, readInt);
                    break;
                case 6:
                    z2 = AbstractC5588xO0.k(parcel, readInt);
                    break;
                case Version.VERSION_7:
                    f = AbstractC5588xO0.n(parcel, readInt);
                    break;
                default:
                    AbstractC5588xO0.t(parcel, readInt);
                    break;
            }
        }
        AbstractC5588xO0.j(parcel, u);
        return new zze(i, i2, i3, z, z2, f);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new zze[i];
    }
}
