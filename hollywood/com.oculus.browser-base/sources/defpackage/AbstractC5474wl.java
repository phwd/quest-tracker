package defpackage;

import J.N;
import android.os.Handler;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.chromium.components.media_router.BrowserMediaRouter;

/* renamed from: wl  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC5474wl implements AbstractC5970zg0, RF, TS0 {

    /* renamed from: a  reason: collision with root package name */
    public static final List f11565a = Collections.emptyList();
    public final C3246jh0 b;
    public final AbstractC5800yg0 c;
    public final Map d = new HashMap();
    public final Map e = new HashMap();
    public Handler f = new Handler();
    public C2653gB g;

    public AbstractC5474wl(C3246jh0 jh0, AbstractC5800yg0 yg0) {
        this.b = jh0;
        this.c = yg0;
    }

    @Override // defpackage.TS0
    public void a(PS0 ps0, int i) {
        C2922hn hnVar = (C2922hn) ps0;
        Iterator it = new HashSet(this.e.keySet()).iterator();
        while (it.hasNext()) {
            t((String) it.next(), "Launch error");
        }
        o("Launch error");
    }

    @Override // defpackage.TS0
    public void b(PS0 ps0, int i) {
        C2922hn hnVar = (C2922hn) ps0;
        q();
    }

    @Override // defpackage.TS0
    public void c(PS0 ps0) {
        C2922hn hnVar = (C2922hn) ps0;
        q();
    }

    @Override // defpackage.TS0
    public void e(PS0 ps0, int i) {
        C2922hn hnVar = (C2922hn) ps0;
    }

    @Override // defpackage.TS0
    public void g(PS0 ps0, boolean z) {
        v().a((C2922hn) ps0);
    }

    @Override // defpackage.AbstractC5970zg0
    public AbstractC2352eR h(String str) {
        return null;
    }

    @Override // defpackage.TS0
    public void i(PS0 ps0, String str) {
        C2922hn hnVar = (C2922hn) ps0;
        if (hnVar == AbstractC3776mn.a().b().c() && hnVar != v().f7686a && this.g != null) {
            r(hnVar, str);
        }
    }

    @Override // defpackage.TS0
    public void j(PS0 ps0) {
        C2922hn hnVar = (C2922hn) ps0;
    }

    @Override // defpackage.TS0
    public void k(PS0 ps0, String str) {
        C2922hn hnVar = (C2922hn) ps0;
    }

    @Override // defpackage.TS0
    public void l(PS0 ps0, int i) {
        C2922hn hnVar = (C2922hn) ps0;
        v().b();
    }

    @Override // defpackage.AbstractC5970zg0
    public void m(String str) {
        if (((C1694af0) this.e.get(str)) != null) {
            if (!v().h()) {
                t(str, null);
            } else {
                v().c();
            }
        }
    }

    public void n(C1694af0 af0, String str, int i, int i2, boolean z) {
        this.e.put(af0.f9441a, af0);
        AbstractC5800yg0 yg0 = this.c;
        String str2 = af0.f9441a;
        String str3 = af0.b;
        BrowserMediaRouter browserMediaRouter = (BrowserMediaRouter) yg0;
        browserMediaRouter.d.put(str2, this);
        long j = browserMediaRouter.b;
        if (j != 0) {
            N.MKeidYbK(j, browserMediaRouter, str2, str3, i2, z);
        }
    }

    public final void o(String str) {
        C2653gB gBVar = this.g;
        if (gBVar != null) {
            AbstractC5800yg0 yg0 = this.c;
            int i = gBVar.g;
            BrowserMediaRouter browserMediaRouter = (BrowserMediaRouter) yg0;
            long j = browserMediaRouter.b;
            if (j != 0) {
                N.MpDGY7p4(j, browserMediaRouter, str, i);
            }
            this.g = null;
        }
    }

    public abstract AbstractC1424Xh0 p(String str);

    public final void q() {
        if (this.g == null) {
            v().j();
            v().b();
            C3246jh0 jh0 = this.b;
            jh0.k(jh0.d());
            Iterator it = new HashSet(this.e.keySet()).iterator();
            while (it.hasNext()) {
                String str = (String) it.next();
                u(str);
                BrowserMediaRouter browserMediaRouter = (BrowserMediaRouter) this.c;
                long j = browserMediaRouter.b;
                if (j != 0) {
                    N.MsmvhUN_(j, browserMediaRouter, str);
                }
                browserMediaRouter.d.remove(str);
            }
            SS0 b2 = AbstractC3776mn.a().b();
            Objects.requireNonNull(b2);
            SE0.e("Must be called from the main thread.");
            try {
                WI1 wi1 = b2.b;
                UC1 uc1 = new UC1(this, C2922hn.class);
                Parcel c2 = wi1.c();
                AbstractC4376qF1.b(c2, uc1);
                wi1.f(3, c2);
            } catch (RemoteException unused) {
                NF1 nf1 = SS0.f8894a;
                Object[] objArr = {"removeSessionManagerListener", WI1.class.getSimpleName()};
                if (nf1.d()) {
                    nf1.c("Unable to call %s on %s.", objArr);
                }
            }
        }
    }

    public void r(C2922hn hnVar, String str) {
        v().a(hnVar);
        v().k();
        C2653gB gBVar = this.g;
        C1694af0 af0 = new C1694af0(gBVar.b.f9167a, gBVar.f9983a.b(), this.g.c);
        C2653gB gBVar2 = this.g;
        n(af0, gBVar2.d, gBVar2.e, gBVar2.g, true);
        this.g = null;
    }

    public final void s(String str, List list) {
        list.size();
        this.f.post(new RunnableC5304vl(this, str, list));
    }

    public void t(String str, String str2) {
        u(str);
        BrowserMediaRouter browserMediaRouter = (BrowserMediaRouter) this.c;
        long j = browserMediaRouter.b;
        if (j != 0) {
            N.MRz6aWnp(j, browserMediaRouter, str, str2);
        }
        browserMediaRouter.d.remove(str);
    }

    public void u(String str) {
        this.e.remove(str);
    }

    public abstract AbstractC0018Ag v();
}
