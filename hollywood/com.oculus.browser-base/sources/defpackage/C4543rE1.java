package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.cast.LaunchOptions;
import com.google.android.gms.cast.framework.CastOptions;
import com.google.android.gms.cast.framework.media.CastMediaOptions;
import com.oculus.os.Version;
import java.util.ArrayList;

/* renamed from: rE1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C4543rE1 implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        int u = AbstractC5588xO0.u(parcel);
        boolean z = false;
        boolean z2 = false;
        boolean z3 = false;
        boolean z4 = false;
        String str = null;
        ArrayList arrayList = null;
        LaunchOptions launchOptions = null;
        CastMediaOptions castMediaOptions = null;
        double d = 0.0d;
        while (parcel.dataPosition() < u) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 2:
                    str = AbstractC5588xO0.e(parcel, readInt);
                    break;
                case 3:
                    arrayList = AbstractC5588xO0.g(parcel, readInt);
                    break;
                case 4:
                    z = AbstractC5588xO0.k(parcel, readInt);
                    break;
                case 5:
                    launchOptions = (LaunchOptions) AbstractC5588xO0.d(parcel, readInt, LaunchOptions.CREATOR);
                    break;
                case 6:
                    z2 = AbstractC5588xO0.k(parcel, readInt);
                    break;
                case Version.VERSION_7:
                    castMediaOptions = (CastMediaOptions) AbstractC5588xO0.d(parcel, readInt, CastMediaOptions.CREATOR);
                    break;
                case Version.VERSION_8:
                    z3 = AbstractC5588xO0.k(parcel, readInt);
                    break;
                case Version.VERSION_9:
                    d = AbstractC5588xO0.l(parcel, readInt);
                    break;
                case Version.VERSION_10:
                    z4 = AbstractC5588xO0.k(parcel, readInt);
                    break;
                default:
                    AbstractC5588xO0.t(parcel, readInt);
                    break;
            }
        }
        AbstractC5588xO0.j(parcel, u);
        return new CastOptions(str, arrayList, z, launchOptions, z2, castMediaOptions, z3, d, z4);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new CastOptions[i];
    }
}
