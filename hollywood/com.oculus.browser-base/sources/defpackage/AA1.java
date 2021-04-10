package defpackage;

import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;

/* renamed from: AA1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class AA1 extends AbstractRunnableC4702sA1 {
    public final /* synthetic */ IBinder G;
    public final /* synthetic */ ServiceConnectionC5552xA1 H;

    public AA1(ServiceConnectionC5552xA1 xa1, IBinder iBinder) {
        this.H = xa1;
        this.G = iBinder;
    }

    @Override // defpackage.AbstractRunnableC4702sA1
    public final void a() {
        C4531rA1 ra1 = this.H.f11597a;
        ra1.l = (IInterface) ra1.h.a(this.G);
        C4531rA1 ra12 = this.H.f11597a;
        ra12.c.a(4, "linkToDeath", new Object[0]);
        try {
            ra12.l.asBinder().linkToDeath(ra12.j, 0);
        } catch (RemoteException e) {
            ra12.c.b(e, "linkToDeath failed", new Object[0]);
        }
        C4531rA1 ra13 = this.H.f11597a;
        ra13.f = false;
        for (Runnable runnable : ra13.e) {
            runnable.run();
        }
        this.H.f11597a.e.clear();
    }
}
