package defpackage;

import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.vision.zzag;

/* renamed from: gD1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C2661gD1 implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        int u = AbstractC5588xO0.u(parcel);
        Rect rect = null;
        while (parcel.dataPosition() < u) {
            int readInt = parcel.readInt();
            if ((65535 & readInt) != 2) {
                AbstractC5588xO0.t(parcel, readInt);
            } else {
                rect = (Rect) AbstractC5588xO0.d(parcel, readInt, Rect.CREATOR);
            }
        }
        AbstractC5588xO0.j(parcel, u);
        return new zzag(rect);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new zzag[i];
    }
}
