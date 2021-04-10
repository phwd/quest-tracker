package defpackage;

import android.os.IBinder;
import android.os.Parcel;
import com.google.android.gms.internal.location.zzbf;

/* renamed from: JD1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class JD1 extends AbstractC2829hC1 {
    public JD1(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.location.internal.IGoogleLocationManagerService");
    }

    public final void f(zzbf zzbf) {
        Parcel c = c();
        int i = PE1.f8678a;
        c.writeInt(1);
        zzbf.writeToParcel(c, 0);
        d(59, c);
    }
}
