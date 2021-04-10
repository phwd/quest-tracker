package defpackage;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.util.Log;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* renamed from: rL0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class ServiceConnectionC4562rL0 extends AbstractC0446Hg0 implements ServiceConnection {
    public final ComponentName i;
    public final HandlerC3708mL0 j;
    public final ArrayList k = new ArrayList();
    public boolean l;
    public boolean m;
    public C3366kL0 n;
    public boolean o;
    public C4732sL0 p;

    public ServiceConnectionC4562rL0(Context context, ComponentName componentName) {
        super(context, new C0324Fg0(componentName));
        this.i = componentName;
        this.j = new HandlerC3708mL0();
    }

    @Override // defpackage.AbstractC0446Hg0
    public AbstractC0202Dg0 c(String str) {
        if (str != null) {
            C0507Ig0 ig0 = this.g;
            if (ig0 != null) {
                List list = ig0.f8240a;
                int size = list.size();
                for (int i2 = 0; i2 < size; i2++) {
                    if (((C0869Of0) list.get(i2)).i().equals(str)) {
                        C4221pL0 pl0 = new C4221pL0(this, str);
                        this.k.add(pl0);
                        if (this.o) {
                            pl0.b(this.n);
                        }
                        p();
                        return pl0;
                    }
                }
            }
            return null;
        }
        throw new IllegalArgumentException("initialMemberRouteId cannot be null.");
    }

    @Override // defpackage.AbstractC0446Hg0
    public AbstractC0385Gg0 d(String str) {
        if (str != null) {
            return j(str, null);
        }
        throw new IllegalArgumentException("routeId cannot be null");
    }

    @Override // defpackage.AbstractC0446Hg0
    public AbstractC0385Gg0 e(String str, String str2) {
        if (str == null) {
            throw new IllegalArgumentException("routeId cannot be null");
        } else if (str2 != null) {
            return j(str, str2);
        } else {
            throw new IllegalArgumentException("routeGroupId cannot be null");
        }
    }

    @Override // defpackage.AbstractC0446Hg0
    public void f(C1052Rf0 rf0) {
        if (this.o) {
            this.n.c(rf0);
        }
        p();
    }

    public final void i() {
        if (!this.m) {
            Intent intent = new Intent("android.media.MediaRouteProviderService");
            intent.setComponent(this.i);
            int i2 = 1;
            try {
                if (Build.VERSION.SDK_INT >= 29) {
                    i2 = 4097;
                }
                this.m = this.f8172a.bindService(intent, this, i2);
            } catch (SecurityException unused) {
            }
        }
    }

    public final AbstractC0385Gg0 j(String str, String str2) {
        C0507Ig0 ig0 = this.g;
        if (ig0 == null) {
            return null;
        }
        List list = ig0.f8240a;
        int size = list.size();
        for (int i2 = 0; i2 < size; i2++) {
            if (((C0869Of0) list.get(i2)).i().equals(str)) {
                C4392qL0 ql0 = new C4392qL0(this, str, str2);
                this.k.add(ql0);
                if (this.o) {
                    ql0.b(this.n);
                }
                p();
                return ql0;
            }
        }
        return null;
    }

    public final void k() {
        if (this.n != null) {
            g(null);
            this.o = false;
            int size = this.k.size();
            for (int i2 = 0; i2 < size; i2++) {
                ((AbstractC3537lL0) this.k.get(i2)).c();
            }
            C3366kL0 kl0 = this.n;
            kl0.b(2, 0, 0, null, null);
            kl0.b.f10484a.clear();
            kl0.f10273a.getBinder().unlinkToDeath(kl0, 0);
            kl0.i.j.post(new RunnableC3025iL0(kl0));
            this.n = null;
        }
    }

    public final AbstractC3537lL0 l(int i2) {
        Iterator it = this.k.iterator();
        while (it.hasNext()) {
            AbstractC3537lL0 ll0 = (AbstractC3537lL0) it.next();
            if (ll0.a() == i2) {
                return ll0;
            }
        }
        return null;
    }

    public void m(AbstractC3537lL0 ll0) {
        this.k.remove(ll0);
        ll0.c();
        p();
    }

    public final boolean n() {
        if (!this.l) {
            return false;
        }
        if (this.e == null && this.k.isEmpty()) {
            return false;
        }
        return true;
    }

    public final void o() {
        if (this.m) {
            this.m = false;
            k();
            try {
                this.f8172a.unbindService(this);
            } catch (IllegalArgumentException e) {
                Log.e("MediaRouteProviderProxy", this + ": unbindService failed", e);
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x001f  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x004b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onServiceConnected(android.content.ComponentName r9, android.os.IBinder r10) {
        /*
            r8 = this;
            boolean r9 = r8.m
            if (r9 == 0) goto L_0x0061
            r8.k()
            if (r10 == 0) goto L_0x000f
            android.os.Messenger r9 = new android.os.Messenger
            r9.<init>(r10)
            goto L_0x0010
        L_0x000f:
            r9 = 0
        L_0x0010:
            r10 = 0
            r0 = 1
            if (r9 == 0) goto L_0x001c
            android.os.IBinder r1 = r9.getBinder()     // Catch:{ NullPointerException -> 0x001c }
            if (r1 == 0) goto L_0x001c
            r1 = r0
            goto L_0x001d
        L_0x001c:
            r1 = r10
        L_0x001d:
            if (r1 == 0) goto L_0x004b
            kL0 r1 = new kL0
            r1.<init>(r8, r9)
            int r4 = r1.d
            int r9 = r4 + 1
            r1.d = r9
            r1.g = r4
            r3 = 1
            r5 = 4
            r6 = 0
            r7 = 0
            r2 = r1
            boolean r9 = r2.b(r3, r4, r5, r6, r7)
            if (r9 != 0) goto L_0x0038
            goto L_0x0046
        L_0x0038:
            android.os.Messenger r9 = r1.f10273a     // Catch:{ RemoteException -> 0x0043 }
            android.os.IBinder r9 = r9.getBinder()     // Catch:{ RemoteException -> 0x0043 }
            r9.linkToDeath(r1, r10)     // Catch:{ RemoteException -> 0x0043 }
            r10 = r0
            goto L_0x0046
        L_0x0043:
            r1.binderDied()
        L_0x0046:
            if (r10 == 0) goto L_0x0061
            r8.n = r1
            goto L_0x0061
        L_0x004b:
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            r9.append(r8)
            java.lang.String r10 = ": Service returned invalid messenger binder"
            r9.append(r10)
            java.lang.String r9 = r9.toString()
            java.lang.String r10 = "MediaRouteProviderProxy"
            android.util.Log.e(r10, r9)
        L_0x0061:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.ServiceConnectionC4562rL0.onServiceConnected(android.content.ComponentName, android.os.IBinder):void");
    }

    public void onServiceDisconnected(ComponentName componentName) {
        k();
    }

    public final void p() {
        if (n()) {
            i();
        } else {
            o();
        }
    }

    public String toString() {
        StringBuilder i2 = AbstractC2531fV.i("Service connection ");
        i2.append(this.i.flattenToShortString());
        return i2.toString();
    }
}
