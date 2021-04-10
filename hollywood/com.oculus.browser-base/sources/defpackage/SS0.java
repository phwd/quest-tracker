package defpackage;

import android.content.Context;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.Objects;

/* renamed from: SS0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class SS0 {

    /* renamed from: a  reason: collision with root package name */
    public static final NF1 f8894a = new NF1("SessionManager");
    public final WI1 b;

    public SS0(WI1 wi1, Context context) {
        this.b = wi1;
    }

    public void a(TS0 ts0, Class cls) {
        Objects.requireNonNull(ts0, "null reference");
        SE0.e("Must be called from the main thread.");
        try {
            WI1 wi1 = this.b;
            UC1 uc1 = new UC1(ts0, cls);
            Parcel c = wi1.c();
            AbstractC4376qF1.b(c, uc1);
            wi1.f(2, c);
        } catch (RemoteException unused) {
            NF1 nf1 = f8894a;
            Object[] objArr = {"addSessionManagerListener", WI1.class.getSimpleName()};
            if (nf1.d()) {
                nf1.c("Unable to call %s on %s.", objArr);
            }
        }
    }

    public void b(boolean z) {
        SE0.e("Must be called from the main thread.");
        try {
            WI1 wi1 = this.b;
            Parcel c = wi1.c();
            int i = AbstractC4376qF1.f11128a;
            c.writeInt(1);
            c.writeInt(z ? 1 : 0);
            wi1.f(6, c);
        } catch (RemoteException unused) {
            NF1 nf1 = f8894a;
            Object[] objArr = {"endCurrentSession", WI1.class.getSimpleName()};
            if (nf1.d()) {
                nf1.c("Unable to call %s on %s.", objArr);
            }
        }
    }

    public C2922hn c() {
        SE0.e("Must be called from the main thread.");
        PS0 d = d();
        if (d == null || !(d instanceof C2922hn)) {
            return null;
        }
        return (C2922hn) d;
    }

    public PS0 d() {
        SE0.e("Must be called from the main thread.");
        try {
            WI1 wi1 = this.b;
            Parcel d = wi1.d(1, wi1.c());
            VY d2 = BinderC0773Mq0.d(d.readStrongBinder());
            d.recycle();
            return (PS0) BinderC0773Mq0.f(d2);
        } catch (RemoteException unused) {
            NF1 nf1 = f8894a;
            Object[] objArr = {"getWrappedCurrentSession", WI1.class.getSimpleName()};
            if (!nf1.d()) {
                return null;
            }
            nf1.c("Unable to call %s on %s.", objArr);
            return null;
        }
    }
}
