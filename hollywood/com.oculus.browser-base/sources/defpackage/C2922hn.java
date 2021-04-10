package defpackage;

import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.cast.ApplicationMetadata;
import com.google.android.gms.cast.CastDevice;
import com.google.android.gms.cast.framework.CastOptions;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/* renamed from: hn  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2922hn extends PS0 {
    public static final NF1 d = new NF1("CastSession");
    public final Context e;
    public final Set f = new HashSet();
    public final AbstractC5403wH1 g;
    public final CastOptions h;
    public final C1069Rm i;
    public final C2490fD1 j;
    public YV k;
    public ML0 l;
    public CastDevice m;

    public C2922hn(Context context, String str, String str2, CastOptions castOptions, C1069Rm rm, C4549rG1 rg1, C2490fD1 fd1) {
        super(context, str, str2);
        AbstractC5403wH1 wh1;
        this.e = context.getApplicationContext();
        this.h = castOptions;
        this.i = rm;
        this.j = fd1;
        VY c = c();
        AbstractC5403wH1 wh12 = null;
        BinderC2239dn dnVar = new BinderC2239dn(this, null);
        OG1 a2 = AbstractC2499fG1.a(context);
        try {
            Parcel c2 = a2.c();
            AbstractC4376qF1.c(c2, castOptions);
            AbstractC4376qF1.b(c2, c);
            AbstractC4376qF1.b(c2, dnVar);
            Parcel d2 = a2.d(3, c2);
            IBinder readStrongBinder = d2.readStrongBinder();
            int i2 = EH1.f7951a;
            if (readStrongBinder == null) {
                wh1 = null;
            } else {
                IInterface queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.gms.cast.framework.ICastSession");
                if (queryLocalInterface instanceof AbstractC5403wH1) {
                    wh1 = (AbstractC5403wH1) queryLocalInterface;
                } else {
                    wh1 = new NH1(readStrongBinder);
                }
            }
            d2.recycle();
            wh12 = wh1;
        } catch (RemoteException unused) {
            NF1 nf1 = AbstractC2499fG1.f9911a;
            Object[] objArr = {"newCastSessionImpl", OG1.class.getSimpleName()};
            if (nf1.d()) {
                nf1.c("Unable to call %s on %s.", objArr);
            }
        }
        this.g = wh12;
    }

    public static void l(C2922hn hnVar, int i2) {
        Objects.requireNonNull(hnVar.j);
        YV yv = hnVar.k;
        if (yv != null) {
            yv.e();
            hnVar.k = null;
        }
        hnVar.m = null;
        ML0 ml0 = hnVar.l;
        if (ml0 != null) {
            ml0.u(null);
            hnVar.l = null;
        }
    }

    public ApplicationMetadata d() {
        SE0.e("Must be called from the main thread.");
        YV yv = this.k;
        if (yv == null) {
            return null;
        }
        Objects.requireNonNull(this.i);
        C3350kF1 kf1 = (C3350kF1) yv.g(KF1.f8354a);
        kf1.c();
        return kf1.G;
    }

    public CastDevice e() {
        SE0.e("Must be called from the main thread.");
        return this.m;
    }

    public ML0 f() {
        SE0.e("Must be called from the main thread.");
        return this.l;
    }

    public double g() {
        SE0.e("Must be called from the main thread.");
        YV yv = this.k;
        if (yv == null) {
            return 0.0d;
        }
        Objects.requireNonNull(this.i);
        C3350kF1 kf1 = (C3350kF1) yv.g(KF1.f8354a);
        kf1.c();
        return kf1.S;
    }

    public boolean h() {
        SE0.e("Must be called from the main thread.");
        YV yv = this.k;
        if (yv == null) {
            return false;
        }
        Objects.requireNonNull(this.i);
        C3350kF1 kf1 = (C3350kF1) yv.g(KF1.f8354a);
        kf1.c();
        return kf1.O;
    }

    public void i(String str, AbstractC1313Vm vm) {
        SE0.e("Must be called from the main thread.");
        YV yv = this.k;
        if (yv != null) {
            Objects.requireNonNull(this.i);
            try {
                ((C3350kF1) yv.g(KF1.f8354a)).A(str, vm);
            } catch (RemoteException unused) {
                throw new IOException("service error");
            }
        }
    }

    public void j(boolean z) {
        SE0.e("Must be called from the main thread.");
        YV yv = this.k;
        if (yv != null) {
            Objects.requireNonNull(this.i);
            try {
                C3350kF1 kf1 = (C3350kF1) yv.g(KF1.f8354a);
                JF1 jf1 = (JF1) kf1.l();
                if (kf1.I()) {
                    double d2 = kf1.S;
                    boolean z2 = kf1.O;
                    Parcel c = jf1.c();
                    int i2 = AbstractC4376qF1.f11128a;
                    c.writeInt(z ? 1 : 0);
                    c.writeDouble(d2);
                    c.writeInt(z2 ? 1 : 0);
                    jf1.e0(8, c);
                }
            } catch (RemoteException unused) {
                throw new IOException("service error");
            }
        }
    }

    public void k(double d2) {
        SE0.e("Must be called from the main thread.");
        YV yv = this.k;
        if (yv != null) {
            Objects.requireNonNull(this.i);
            try {
                ((C3350kF1) yv.g(KF1.f8354a)).B(d2);
            } catch (RemoteException unused) {
                throw new IOException("service error");
            }
        }
    }

    public final void m(Bundle bundle) {
        boolean z;
        CastDevice x = CastDevice.x(bundle);
        this.m = x;
        if (x == null) {
            SE0.e("Must be called from the main thread.");
            try {
                PI1 pi1 = (PI1) this.b;
                Parcel d2 = pi1.d(9, pi1.c());
                int i2 = AbstractC4376qF1.f11128a;
                z = d2.readInt() != 0;
                d2.recycle();
            } catch (RemoteException unused) {
                NF1 nf1 = PS0.f8692a;
                Object[] objArr = {"isResuming", DI1.class.getSimpleName()};
                if (nf1.d()) {
                    nf1.c("Unable to call %s on %s.", objArr);
                }
                z = false;
            }
            if (z) {
                try {
                    PI1 pi12 = (PI1) this.b;
                    Parcel c = pi12.c();
                    c.writeInt(8);
                    pi12.f(15, c);
                } catch (RemoteException unused2) {
                    NF1 nf12 = PS0.f8692a;
                    Object[] objArr2 = {"notifyFailedToResumeSession", DI1.class.getSimpleName()};
                    if (nf12.d()) {
                        nf12.c("Unable to call %s on %s.", objArr2);
                    }
                }
            } else {
                try {
                    PI1 pi13 = (PI1) this.b;
                    Parcel c2 = pi13.c();
                    c2.writeInt(8);
                    pi13.f(12, c2);
                } catch (RemoteException unused3) {
                    NF1 nf13 = PS0.f8692a;
                    Object[] objArr3 = {"notifyFailedToStartSession", DI1.class.getSimpleName()};
                    if (nf13.d()) {
                        nf13.c("Unable to call %s on %s.", objArr3);
                    }
                }
            }
        } else {
            YV yv = this.k;
            if (yv != null) {
                yv.e();
                this.k = null;
            }
            NF1 nf14 = d;
            Object[] objArr4 = {this.m};
            if (nf14.d()) {
                nf14.c("Acquiring a connection to Google Play Services for %s", objArr4);
            }
            C2580fn fnVar = new C2580fn(this, null);
            Context context = this.e;
            CastDevice castDevice = this.m;
            C2751gn gnVar = new C2751gn(this, null);
            Bundle bundle2 = new Bundle();
            bundle2.putBoolean("com.google.android.gms.cast.EXTRA_CAST_FRAMEWORK_NOTIFICATION_ENABLED", false);
            bundle2.putBoolean("com.google.android.gms.cast.EXTRA_CAST_REMOTE_CONTROL_NOTIFICATION_ENABLED", false);
            VV vv = new VV(context);
            C2470f7 f7Var = AbstractC1435Xm.b;
            C1130Sm sm = new C1130Sm(castDevice, gnVar);
            sm.c = bundle2;
            C1191Tm tm = new C1191Tm(sm, null);
            SE0.i(f7Var, "Api must not be null");
            SE0.i(tm, "Null options are not permitted for this Api");
            vv.g.put(f7Var, tm);
            Objects.requireNonNull(f7Var.f9899a);
            List emptyList = Collections.emptyList();
            vv.b.addAll(emptyList);
            vv.f9088a.addAll(emptyList);
            SE0.i(fnVar, "Listener must not be null");
            vv.l.add(fnVar);
            SE0.i(fnVar, "Listener must not be null");
            vv.m.add(fnVar);
            YV a2 = vv.a();
            this.k = a2;
            a2.d();
        }
    }
}
