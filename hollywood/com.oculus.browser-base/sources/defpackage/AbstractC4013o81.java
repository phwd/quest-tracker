package defpackage;

import J.N;
import java.util.Map;
import org.chromium.chrome.browser.contextualsearch.ContextualSearchTabHelper;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tab.TabFavicon;
import org.chromium.chrome.browser.tab.TabImpl;
import org.chromium.chrome.browser.tab.TrustedCdn;

/* renamed from: o81  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC4013o81 {
    public static void a(Tab tab, Tab tab2) {
        TabImpl tabImpl = (TabImpl) tab;
        Integer num = tabImpl.W;
        if (num != null) {
            tabImpl.j0.e(C4942td1.class, new C4942td1(tab, num.intValue()));
        }
        tabImpl.j0.e(D61.class, new D61(tab));
        tabImpl.j0.e(C2118d30.class, new C2118d30(tab));
        new ContextualSearchTabHelper(tab);
        AbstractC3226ja1 ja1 = tabImpl.i0;
        if (ja1 != null && ja1.j()) {
            new C1302Vh0(tab);
        }
        if (tab2 != null) {
            Y51 c = Y51.c(tab);
            Object valueOf = Long.valueOf(N.MjsSsYT7(tab2.l()));
            Map map = c.G;
            if (valueOf == null) {
                valueOf = Y51.F;
            }
            map.put("ParentTaskId", valueOf);
            Y51 c2 = Y51.c(tab);
            Object valueOf2 = Long.valueOf(N.M848Q9ZH(tab2.l()));
            Map map2 = c2.G;
            if (valueOf2 == null) {
                valueOf2 = Y51.F;
            }
            map2.put("ParentRootTaskId", valueOf2);
        }
        tabImpl.j0.e(C1786b61.class, new C1786b61(tab));
        if (AbstractC4226pO.b() && N.M09VlOh_("ContinuousSearch")) {
            tabImpl.j0.e(KQ0.class, new KQ0());
            tabImpl.j0.e(GQ0.class, new GQ0(tab));
            new BA(tab);
        }
        if (C2510fK0.V()) {
            tabImpl.j0.e(C2510fK0.class, new C2510fK0(tab));
        }
        if (tabImpl.H) {
            AbstractC5161uu.f11443a.d();
        }
    }

    public static void b(Tab tab) {
        W w = C3649m10.F;
        TabImpl tabImpl = (TabImpl) tab;
        if (((C3649m10) tabImpl.j0.c(C3649m10.class)) == null) {
            C3649m10 m10 = (C3649m10) tabImpl.j0.e(C3649m10.class, new C3649m10(tab));
        }
        C0377Gd1.j(tab);
        C41.l(tab);
        if (TabFavicon.j(tab) == null) {
            TabFavicon tabFavicon = (TabFavicon) tabImpl.j0.e(TabFavicon.class, new TabFavicon(tab));
        }
        if (((TrustedCdn) tabImpl.j0.c(TrustedCdn.class)) == null) {
            TrustedCdn trustedCdn = (TrustedCdn) tabImpl.j0.e(TrustedCdn.class, new TrustedCdn(tab));
        }
        T51.j(tab);
        if (((F61) tabImpl.j0.c(F61.class)) == null) {
            F61 f61 = (F61) tabImpl.j0.e(F61.class, new F61(tab));
        }
    }
}
