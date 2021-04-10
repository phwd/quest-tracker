package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.vision.face.internal.client.LandmarkParcel;

/* renamed from: TH1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class TH1 implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        int u = AbstractC5588xO0.u(parcel);
        float f = 0.0f;
        int i = 0;
        int i2 = 0;
        float f2 = 0.0f;
        while (parcel.dataPosition() < u) {
            int readInt = parcel.readInt();
            int i3 = 65535 & readInt;
            if (i3 == 1) {
                i = AbstractC5588xO0.p(parcel, readInt);
            } else if (i3 == 2) {
                f = AbstractC5588xO0.n(parcel, readInt);
            } else if (i3 == 3) {
                f2 = AbstractC5588xO0.n(parcel, readInt);
            } else if (i3 != 4) {
                AbstractC5588xO0.t(parcel, readInt);
            } else {
                i2 = AbstractC5588xO0.p(parcel, readInt);
            }
        }
        AbstractC5588xO0.j(parcel, u);
        return new LandmarkParcel(i, f, f2, i2);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new LandmarkParcel[i];
    }
}
