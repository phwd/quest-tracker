package org.chromium.components.media_router;

import J.N;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.chromium.base.ContextUtils;
import org.chromium.base.SysUtils;
import org.chromium.chrome.browser.media.router.ChromeMediaRouterClient;
import org.chromium.chrome.browser.tab.TabImpl;
import org.chromium.content_public.browser.WebContents;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class BrowserMediaRouter implements AbstractC5800yg0 {

    /* renamed from: a  reason: collision with root package name */
    public static C2913hk f10849a = new C2913hk();
    public long b;
    public final List c = new ArrayList();
    public final Map d = new HashMap();
    public final Map e = new HashMap();
    public final Map f = new HashMap();

    public BrowserMediaRouter(long j) {
        this.b = j;
    }

    public static C3246jh0 a() {
        P21 f0 = P21.f0();
        try {
            C3246jh0 e2 = C3246jh0.e(ContextUtils.getApplicationContext());
            try {
                f0.close();
                return e2;
            } catch (NoClassDefFoundError | NoSuchMethodError unused) {
                return null;
            }
        } catch (Throwable th) {
            AbstractC0754Mh1.f8495a.a(th, th);
        }
        throw th;
    }

    public static BrowserMediaRouter create(long j) {
        BrowserMediaRouter browserMediaRouter = new BrowserMediaRouter(j);
        Object obj = SV.c;
        SV sv = SV.d;
        int b2 = sv.b(ContextUtils.getApplicationContext(), 12600000);
        if (b2 != 0) {
            sv.g(ContextUtils.getApplicationContext(), b2);
        } else {
            browserMediaRouter.c.add(new C5814yl(a(), browserMediaRouter));
            browserMediaRouter.c.add(new C0275El(a(), browserMediaRouter));
        }
        return browserMediaRouter;
    }

    public final AbstractC5970zg0 b(String str) {
        boolean z;
        for (AbstractC5970zg0 zg0 : this.c) {
            if (((AbstractC5474wl) zg0).p(str) != null) {
                z = true;
                continue;
            } else {
                z = false;
                continue;
            }
            if (z) {
                return zg0;
            }
        }
        return null;
    }

    public void c(String str, int i) {
        long j = this.b;
        if (j != 0) {
            N.MpDGY7p4(j, this, str, i);
        }
    }

    public void closeRoute(String str) {
        AbstractC5970zg0 zg0 = (AbstractC5970zg0) this.d.get(str);
        if (zg0 != null) {
            zg0.m(str);
        }
    }

    public void createRoute(String str, String str2, String str3, String str4, WebContents webContents, int i) {
        int i2;
        C1363Wh0 wh0;
        C2392eh0 eh0;
        AbstractC5970zg0 b2 = b(str);
        if (b2 == null) {
            c("No provider supports createRoute with source: " + str + " and sink: " + str2, i);
            return;
        }
        Objects.requireNonNull(ChromeMediaRouterClient.f10694a);
        TabImpl tabImpl = (TabImpl) N.MMqeq$AW(webContents);
        if (tabImpl == null) {
            i2 = -1;
        } else {
            i2 = tabImpl.getId();
        }
        boolean a2 = webContents.a();
        AbstractC5474wl wlVar = (AbstractC5474wl) b2;
        if (wlVar.v().h()) {
            wlVar.v().c();
            wlVar.q();
        }
        if (wlVar.g != null) {
            wlVar.o("Request replaced");
        }
        Iterator it = wlVar.b.g().iterator();
        while (true) {
            if (!it.hasNext()) {
                wh0 = null;
                break;
            }
            C1363Wh0 a3 = C1363Wh0.a((C2392eh0) it.next());
            if (a3.f9167a.equals(str2)) {
                wh0 = a3;
                break;
            }
        }
        if (wh0 == null) {
            BrowserMediaRouter browserMediaRouter = (BrowserMediaRouter) wlVar.c;
            long j = browserMediaRouter.b;
            if (j != 0) {
                N.MpDGY7p4(j, browserMediaRouter, "No sink", i);
                return;
            }
            return;
        }
        AbstractC1424Xh0 p = wlVar.p(str);
        if (p == null) {
            BrowserMediaRouter browserMediaRouter2 = (BrowserMediaRouter) wlVar.c;
            long j2 = browserMediaRouter2.b;
            if (j2 != 0) {
                N.MpDGY7p4(j2, browserMediaRouter2, "Unsupported source URL", i);
                return;
            }
            return;
        }
        Iterator it2 = wlVar.b.g().iterator();
        while (true) {
            if (!it2.hasNext()) {
                eh0 = null;
                break;
            }
            C2392eh0 eh02 = (C2392eh0) it2.next();
            if (eh02.c.equals(wh0.f9167a)) {
                eh0 = eh02;
                break;
            }
        }
        if (eh0 == null) {
            BrowserMediaRouter browserMediaRouter3 = (BrowserMediaRouter) wlVar.c;
            long j3 = browserMediaRouter3.b;
            if (j3 != 0) {
                N.MpDGY7p4(j3, browserMediaRouter3, "The sink does not exist", i);
            }
        }
        AbstractC3776mn.a().b().a(wlVar, C2922hn.class);
        wlVar.g = new C2653gB(p, wh0, str3, str4, i2, a2, i, eh0);
        AbstractC0018Ag v = wlVar.v();
        v.c = v.b.g;
        AbstractC3776mn.a().d(v.c.f9983a.a());
        v.c.h.m();
    }

    public void detachRoute(String str) {
        AbstractC5970zg0 zg0 = (AbstractC5970zg0) this.d.get(str);
        if (zg0 != null) {
            ((AbstractC5474wl) zg0).t(str, null);
            this.d.remove(str);
        }
    }

    public FlingingControllerBridge getFlingingControllerBridge(String str) {
        AbstractC2352eR h;
        AbstractC5970zg0 zg0 = (AbstractC5970zg0) this.d.get(str);
        if (zg0 == null || (h = zg0.h(str)) == null) {
            return null;
        }
        return new FlingingControllerBridge(h);
    }

    public String getSinkName(String str, int i) {
        return ((C1363Wh0) ((List) this.f.get(str)).get(i)).b;
    }

    public String getSinkUrn(String str, int i) {
        C1363Wh0 wh0 = (C1363Wh0) ((List) this.f.get(str)).get(i);
        Objects.requireNonNull(wh0);
        return "urn:x-org.chromium:media:sink:cast-" + wh0.f9167a;
    }

    public void joinRoute(String str, String str2, String str3, WebContents webContents, int i) {
        int i2;
        AbstractC5970zg0 b2 = b(str);
        if (b2 == null) {
            long j = this.b;
            if (j != 0) {
                N.M9VY0XZb(j, this, "Route not found.", i);
                return;
            }
            return;
        }
        Objects.requireNonNull(ChromeMediaRouterClient.f10694a);
        TabImpl tabImpl = (TabImpl) N.MMqeq$AW(webContents);
        if (tabImpl == null) {
            i2 = -1;
        } else {
            i2 = tabImpl.getId();
        }
        b2.d(str, str2, str3, i2, i);
    }

    public void sendStringMessage(String str, String str2) {
        AbstractC5970zg0 zg0 = (AbstractC5970zg0) this.d.get(str);
        if (zg0 != null) {
            zg0.f(str, str2);
        }
    }

    public boolean startObservingMediaSinks(String str) {
        if (SysUtils.isLowEndDevice()) {
            return false;
        }
        for (AbstractC5970zg0 zg0 : this.c) {
            AbstractC5474wl wlVar = (AbstractC5474wl) zg0;
            AbstractC1424Xh0 p = wlVar.p(str);
            if (p == null) {
                wlVar.s(str, AbstractC5474wl.f11565a);
            } else {
                String a2 = p.a();
                QF qf = (QF) wlVar.d.get(a2);
                if (qf != null) {
                    qf.k(str);
                } else {
                    C0629Kg0 c2 = p.c();
                    if (c2 == null) {
                        wlVar.s(str, AbstractC5474wl.f11565a);
                    } else {
                        ArrayList arrayList = new ArrayList();
                        for (C2392eh0 eh0 : wlVar.b.g()) {
                            if (eh0.i(c2)) {
                                arrayList.add(C1363Wh0.a(eh0));
                            }
                        }
                        QF qf2 = new QF(str, arrayList, wlVar, c2);
                        wlVar.b.a(c2, qf2, 4);
                        wlVar.d.put(a2, qf2);
                    }
                }
            }
        }
        return true;
    }

    public void stopObservingMediaSinks(String str) {
        for (AbstractC5970zg0 zg0 : this.c) {
            AbstractC5474wl wlVar = (AbstractC5474wl) zg0;
            AbstractC1424Xh0 p = wlVar.p(str);
            if (p != null) {
                String a2 = p.a();
                QF qf = (QF) wlVar.d.get(a2);
                if (qf != null) {
                    qf.c.remove(str);
                    if (qf.c.isEmpty()) {
                        wlVar.b.j(qf);
                        wlVar.d.remove(a2);
                    }
                }
            }
        }
        this.f.remove(str);
        this.e.remove(str);
    }

    public void teardown() {
        this.b = 0;
    }
}
