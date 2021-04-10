package defpackage;

import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* renamed from: PS0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class PS0 {

    /* renamed from: a  reason: collision with root package name */
    public static final NF1 f8692a = new NF1("Session");
    public final DI1 b;
    public final OS0 c;

    public PS0(Context context, String str, String str2) {
        DI1 di1;
        DI1 di12 = null;
        OS0 os0 = new OS0(this, null);
        this.c = os0;
        OG1 a2 = AbstractC2499fG1.a(context);
        try {
            Parcel c2 = a2.c();
            c2.writeString(str);
            c2.writeString(str2);
            AbstractC4376qF1.b(c2, os0);
            Parcel d = a2.d(2, c2);
            IBinder readStrongBinder = d.readStrongBinder();
            int i = CI1.f7801a;
            if (readStrongBinder == null) {
                di1 = null;
            } else {
                IInterface queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.gms.cast.framework.ISession");
                if (queryLocalInterface instanceof DI1) {
                    di1 = (DI1) queryLocalInterface;
                } else {
                    di1 = new PI1(readStrongBinder);
                }
            }
            d.recycle();
            di12 = di1;
        } catch (RemoteException unused) {
            NF1 nf1 = AbstractC2499fG1.f9911a;
            Object[] objArr = {"newSessionImpl", OG1.class.getSimpleName()};
            if (nf1.d()) {
                nf1.c("Unable to call %s on %s.", objArr);
            }
        }
        this.b = di12;
    }

    public boolean a() {
        SE0.e("Must be called from the main thread.");
        try {
            PI1 pi1 = (PI1) this.b;
            Parcel d = pi1.d(5, pi1.c());
            int i = AbstractC4376qF1.f11128a;
            boolean z = d.readInt() != 0;
            d.recycle();
            return z;
        } catch (RemoteException unused) {
            NF1 nf1 = f8692a;
            Object[] objArr = {"isConnected", DI1.class.getSimpleName()};
            if (nf1.d()) {
                nf1.c("Unable to call %s on %s.", objArr);
            }
            return false;
        }
    }

    public final void b(int i) {
        try {
            PI1 pi1 = (PI1) this.b;
            Parcel c2 = pi1.c();
            c2.writeInt(i);
            pi1.f(13, c2);
        } catch (RemoteException unused) {
            NF1 nf1 = f8692a;
            Object[] objArr = {"notifySessionEnded", DI1.class.getSimpleName()};
            if (nf1.d()) {
                nf1.c("Unable to call %s on %s.", objArr);
            }
        }
    }

    public final VY c() {
        try {
            PI1 pi1 = (PI1) this.b;
            Parcel d = pi1.d(1, pi1.c());
            VY d2 = BinderC0773Mq0.d(d.readStrongBinder());
            d.recycle();
            return d2;
        } catch (RemoteException unused) {
            NF1 nf1 = f8692a;
            Object[] objArr = {"getWrappedObject", DI1.class.getSimpleName()};
            if (!nf1.d()) {
                return null;
            }
            nf1.c("Unable to call %s on %s.", objArr);
            return null;
        }
    }
}
