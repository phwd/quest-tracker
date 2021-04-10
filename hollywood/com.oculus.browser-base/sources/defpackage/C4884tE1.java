package defpackage;

import android.app.PendingIntent;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Status;

/* renamed from: tE1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C4884tE1 implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        int u = AbstractC5588xO0.u(parcel);
        String str = null;
        int i = 0;
        int i2 = 0;
        PendingIntent pendingIntent = null;
        while (parcel.dataPosition() < u) {
            int readInt = parcel.readInt();
            int i3 = 65535 & readInt;
            if (i3 == 1) {
                i2 = AbstractC5588xO0.p(parcel, readInt);
            } else if (i3 == 2) {
                str = AbstractC5588xO0.e(parcel, readInt);
            } else if (i3 == 3) {
                pendingIntent = (PendingIntent) AbstractC5588xO0.d(parcel, readInt, PendingIntent.CREATOR);
            } else if (i3 != 1000) {
                AbstractC5588xO0.t(parcel, readInt);
            } else {
                i = AbstractC5588xO0.p(parcel, readInt);
            }
        }
        AbstractC5588xO0.j(parcel, u);
        return new Status(i, i2, str, pendingIntent);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new Status[i];
    }
}
