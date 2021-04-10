package defpackage;

import J.N;
import android.os.Build;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import org.chromium.base.ThreadUtils;
import org.chromium.chrome.browser.AppHooks;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.usage_stats.UsageStatsBridge;

/* renamed from: Jr1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class Jr1 {

    /* renamed from: a  reason: collision with root package name */
    public static Jr1 f8317a;
    public Profile b;
    public C3538lM c;
    public C2078cq0 d;
    public C4172p41 e;
    public C3766mj1 f = new C3766mj1(this.g);
    public UsageStatsBridge g;
    public List h = new ArrayList();
    public CF i;
    public boolean j;

    public Jr1() {
        Profile b2 = Profile.b();
        this.b = b2;
        UsageStatsBridge usageStatsBridge = new UsageStatsBridge(b2, this);
        this.g = usageStatsBridge;
        this.c = new C3538lM(usageStatsBridge);
        C2078cq0 cq0 = new C2078cq0(this.b);
        this.d = cq0;
        this.e = new C4172p41(this.g, cq0);
        Objects.requireNonNull(AppHooks.get());
        this.i = new CF();
        this.e.c.f(new C3488l41()).g(new Ar1(this));
        Object obj = ThreadUtils.f10596a;
        this.j = N.MzIXnlkD(Wr1.a(this.b).f10883a, "usage_stats_reporting.enabled");
    }

    public static Jr1 a() {
        if (f8317a == null) {
            f8317a = new Jr1();
        }
        return f8317a;
    }

    public static boolean b() {
        return Build.VERSION.SDK_INT >= 29;
    }

    public final void c(List list, boolean z) {
        Iterator it = ((ArrayList) AbstractC0417Gv.e(this.h)).iterator();
        while (it.hasNext()) {
            C0905Ov0 ov0 = (C0905Ov0) it.next();
            Iterator it2 = list.iterator();
            while (it2.hasNext()) {
                String str = (String) it2.next();
                Tab tab = ov0.g;
                if (tab != null && tab.isInitialized()) {
                    C2976i41 W = C2976i41.W(ov0.g);
                    if ((str.equals(ov0.h) || str.equals(W.H)) && ov0.c(z, str)) {
                        ov0.d();
                    }
                }
            }
        }
    }
}
