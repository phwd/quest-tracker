package defpackage;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.cast.framework.media.CastMediaOptions;
import com.google.android.gms.cast.framework.media.NotificationOptions;

/* renamed from: nC1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C3854nC1 implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        int u = AbstractC5588xO0.u(parcel);
        String str = null;
        String str2 = null;
        IBinder iBinder = null;
        NotificationOptions notificationOptions = null;
        boolean z = false;
        while (parcel.dataPosition() < u) {
            int readInt = parcel.readInt();
            int i = 65535 & readInt;
            if (i == 2) {
                str = AbstractC5588xO0.e(parcel, readInt);
            } else if (i == 3) {
                str2 = AbstractC5588xO0.e(parcel, readInt);
            } else if (i == 4) {
                iBinder = AbstractC5588xO0.o(parcel, readInt);
            } else if (i == 5) {
                notificationOptions = (NotificationOptions) AbstractC5588xO0.d(parcel, readInt, NotificationOptions.CREATOR);
            } else if (i != 6) {
                AbstractC5588xO0.t(parcel, readInt);
            } else {
                z = AbstractC5588xO0.k(parcel, readInt);
            }
        }
        AbstractC5588xO0.j(parcel, u);
        return new CastMediaOptions(str, str2, iBinder, notificationOptions, z);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new CastMediaOptions[i];
    }
}
