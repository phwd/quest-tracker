package defpackage;

import android.os.Bundle;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.Objects;

/* renamed from: ZI1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class ZI1 extends AbstractC0750Mg0 {

    /* renamed from: a  reason: collision with root package name */
    public static final NF1 f9337a = new NF1("MediaRouterCallback");
    public final HH1 b;

    public ZI1(HH1 hh1) {
        Objects.requireNonNull(hh1, "null reference");
        this.b = hh1;
    }

    @Override // defpackage.AbstractC0750Mg0
    public final void d(C3246jh0 jh0, C2392eh0 eh0) {
        try {
            HH1 hh1 = this.b;
            String str = eh0.c;
            Bundle bundle = eh0.r;
            Parcel c = hh1.c();
            c.writeString(str);
            AbstractC4376qF1.c(c, bundle);
            hh1.f(1, c);
        } catch (RemoteException unused) {
            NF1 nf1 = f9337a;
            Object[] objArr = {"onRouteAdded", HH1.class.getSimpleName()};
            if (nf1.d()) {
                nf1.c("Unable to call %s on %s.", objArr);
            }
        }
    }

    @Override // defpackage.AbstractC0750Mg0
    public final void e(C3246jh0 jh0, C2392eh0 eh0) {
        try {
            HH1 hh1 = this.b;
            String str = eh0.c;
            Bundle bundle = eh0.r;
            Parcel c = hh1.c();
            c.writeString(str);
            AbstractC4376qF1.c(c, bundle);
            hh1.f(2, c);
        } catch (RemoteException unused) {
            NF1 nf1 = f9337a;
            Object[] objArr = {"onRouteChanged", HH1.class.getSimpleName()};
            if (nf1.d()) {
                nf1.c("Unable to call %s on %s.", objArr);
            }
        }
    }

    @Override // defpackage.AbstractC0750Mg0
    public final void f(C3246jh0 jh0, C2392eh0 eh0) {
        try {
            HH1 hh1 = this.b;
            String str = eh0.c;
            Bundle bundle = eh0.r;
            Parcel c = hh1.c();
            c.writeString(str);
            AbstractC4376qF1.c(c, bundle);
            hh1.f(3, c);
        } catch (RemoteException unused) {
            NF1 nf1 = f9337a;
            Object[] objArr = {"onRouteRemoved", HH1.class.getSimpleName()};
            if (nf1.d()) {
                nf1.c("Unable to call %s on %s.", objArr);
            }
        }
    }

    @Override // defpackage.AbstractC0750Mg0
    public final void g(C3246jh0 jh0, C2392eh0 eh0) {
        try {
            HH1 hh1 = this.b;
            String str = eh0.c;
            Bundle bundle = eh0.r;
            Parcel c = hh1.c();
            c.writeString(str);
            AbstractC4376qF1.c(c, bundle);
            hh1.f(4, c);
        } catch (RemoteException unused) {
            NF1 nf1 = f9337a;
            Object[] objArr = {"onRouteSelected", HH1.class.getSimpleName()};
            if (nf1.d()) {
                nf1.c("Unable to call %s on %s.", objArr);
            }
        }
    }

    @Override // defpackage.AbstractC0750Mg0
    public final void i(C3246jh0 jh0, C2392eh0 eh0, int i) {
        try {
            HH1 hh1 = this.b;
            String str = eh0.c;
            Bundle bundle = eh0.r;
            Parcel c = hh1.c();
            c.writeString(str);
            AbstractC4376qF1.c(c, bundle);
            c.writeInt(i);
            hh1.f(6, c);
        } catch (RemoteException unused) {
            NF1 nf1 = f9337a;
            Object[] objArr = {"onRouteUnselected", HH1.class.getSimpleName()};
            if (nf1.d()) {
                nf1.c("Unable to call %s on %s.", objArr);
            }
        }
    }
}
