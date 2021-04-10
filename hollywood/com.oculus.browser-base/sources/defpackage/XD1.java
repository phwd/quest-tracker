package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.cast.MediaTrack;
import com.oculus.os.Version;

/* renamed from: XD1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class XD1 implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        int u = AbstractC5588xO0.u(parcel);
        int i = 0;
        int i2 = 0;
        String str = null;
        String str2 = null;
        String str3 = null;
        String str4 = null;
        String str5 = null;
        long j = 0;
        while (parcel.dataPosition() < u) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 2:
                    j = AbstractC5588xO0.r(parcel, readInt);
                    break;
                case 3:
                    i = AbstractC5588xO0.p(parcel, readInt);
                    break;
                case 4:
                    str = AbstractC5588xO0.e(parcel, readInt);
                    break;
                case 5:
                    str2 = AbstractC5588xO0.e(parcel, readInt);
                    break;
                case 6:
                    str3 = AbstractC5588xO0.e(parcel, readInt);
                    break;
                case Version.VERSION_7 /*{ENCODED_INT: 7}*/:
                    str4 = AbstractC5588xO0.e(parcel, readInt);
                    break;
                case Version.VERSION_8 /*{ENCODED_INT: 8}*/:
                    i2 = AbstractC5588xO0.p(parcel, readInt);
                    break;
                case Version.VERSION_9 /*{ENCODED_INT: 9}*/:
                    str5 = AbstractC5588xO0.e(parcel, readInt);
                    break;
                default:
                    AbstractC5588xO0.t(parcel, readInt);
                    break;
            }
        }
        AbstractC5588xO0.j(parcel, u);
        return new MediaTrack(j, i, str, str2, str3, str4, i2, str5);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new MediaTrack[i];
    }
}
