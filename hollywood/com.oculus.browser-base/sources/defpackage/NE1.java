package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.cast.VideoInfo;

/* renamed from: NE1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class NE1 implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        int u = AbstractC5588xO0.u(parcel);
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        while (parcel.dataPosition() < u) {
            int readInt = parcel.readInt();
            int i4 = 65535 & readInt;
            if (i4 == 2) {
                i = AbstractC5588xO0.p(parcel, readInt);
            } else if (i4 == 3) {
                i2 = AbstractC5588xO0.p(parcel, readInt);
            } else if (i4 != 4) {
                AbstractC5588xO0.t(parcel, readInt);
            } else {
                i3 = AbstractC5588xO0.p(parcel, readInt);
            }
        }
        AbstractC5588xO0.j(parcel, u);
        return new VideoInfo(i, i2, i3);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new VideoInfo[i];
    }
}
