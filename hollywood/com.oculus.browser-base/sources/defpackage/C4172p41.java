package defpackage;

import J.N;
import java.util.List;
import org.chromium.chrome.browser.usage_stats.UsageStatsBridge;

/* renamed from: p41  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4172p41 {

    /* renamed from: a  reason: collision with root package name */
    public final UsageStatsBridge f11047a;
    public final C2078cq0 b;
    public final C5232vH0 c = new C5232vH0();
    public C5232vH0 d;

    public C4172p41(UsageStatsBridge usageStatsBridge, C2078cq0 cq0) {
        this.f11047a = usageStatsBridge;
        this.b = cq0;
        N.MggFWmhE(usageStatsBridge.b, usageStatsBridge, new C5494wr1(new C3146j41(this)));
        this.d = C5232vH0.c(null);
    }

    public boolean a(String str) {
        C5232vH0 vh0 = this.c;
        if (vh0 == null || !vh0.d()) {
            return false;
        }
        return ((List) this.c.b).contains(str);
    }
}
