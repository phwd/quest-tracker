package defpackage;

import android.app.PendingIntent;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.location.zzbd;
import com.google.android.gms.internal.location.zzbf;

/* renamed from: FE1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class FE1 implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        int u = AbstractC5588xO0.u(parcel);
        zzbd zzbd = null;
        IBinder iBinder = null;
        PendingIntent pendingIntent = null;
        IBinder iBinder2 = null;
        IBinder iBinder3 = null;
        int i = 1;
        while (parcel.dataPosition() < u) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 1:
                    i = AbstractC5588xO0.p(parcel, readInt);
                    break;
                case 2:
                    zzbd = (zzbd) AbstractC5588xO0.d(parcel, readInt, zzbd.CREATOR);
                    break;
                case 3:
                    iBinder = AbstractC5588xO0.o(parcel, readInt);
                    break;
                case 4:
                    pendingIntent = (PendingIntent) AbstractC5588xO0.d(parcel, readInt, PendingIntent.CREATOR);
                    break;
                case 5:
                    iBinder2 = AbstractC5588xO0.o(parcel, readInt);
                    break;
                case 6:
                    iBinder3 = AbstractC5588xO0.o(parcel, readInt);
                    break;
                default:
                    AbstractC5588xO0.t(parcel, readInt);
                    break;
            }
        }
        AbstractC5588xO0.j(parcel, u);
        return new zzbf(i, zzbd, iBinder, pendingIntent, iBinder2, iBinder3);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new zzbf[i];
    }
}
