package defpackage;

import android.os.IBinder;
import android.os.RemoteException;

/* renamed from: uA1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C5042uA1 implements IBinder.DeathRecipient {

    /* renamed from: a  reason: collision with root package name */
    public final C4531rA1 f11394a;

    public C5042uA1(C4531rA1 ra1) {
        this.f11394a = ra1;
    }

    public final void binderDied() {
        C4531rA1 ra1 = this.f11394a;
        ra1.c.a(4, "reportBinderDeath", new Object[0]);
        AbstractC5212vA1 va1 = (AbstractC5212vA1) ra1.i.get();
        if (va1 != null) {
            ra1.c.a(4, "calling onBinderDied", new Object[0]);
            va1.a();
            return;
        }
        ra1.c.a(4, "%s : Binder has died.", new Object[]{ra1.d});
        for (AbstractRunnableC4702sA1 sa1 : ra1.e) {
            C2140dA1 da1 = sa1.F;
            if (da1 != null) {
                da1.a(new RemoteException(String.valueOf(ra1.d).concat(" : Binder has died.")));
            }
        }
        ra1.e.clear();
    }
}
