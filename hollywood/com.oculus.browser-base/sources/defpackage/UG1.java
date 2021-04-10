package defpackage;

import android.os.IBinder;
import android.os.Parcel;

/* renamed from: UG1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class UG1 extends AbstractC5564xE1 {
    public UG1(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.dynamite.IDynamiteLoader");
    }

    public final VY e0(VY vy, String str, int i) {
        Parcel c = c();
        AbstractC4546rF1.b(c, vy);
        c.writeString(str);
        c.writeInt(i);
        Parcel d = d(4, c);
        VY d2 = BinderC0773Mq0.d(d.readStrongBinder());
        d.recycle();
        return d2;
    }

    public final VY f(VY vy, String str, int i) {
        Parcel c = c();
        AbstractC4546rF1.b(c, vy);
        c.writeString(str);
        c.writeInt(i);
        Parcel d = d(2, c);
        VY d2 = BinderC0773Mq0.d(d.readStrongBinder());
        d.recycle();
        return d2;
    }
}
