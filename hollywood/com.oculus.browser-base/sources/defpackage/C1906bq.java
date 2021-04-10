package defpackage;

import java.util.HashMap;
import org.chromium.base.ApplicationStatus;
import org.chromium.chrome.browser.app.ChromeActivity;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.net.NetworkChangeNotifier;
import org.chromium.url.GURL;

/* renamed from: bq  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1906bq extends AbstractC1099Sa1 {
    public final /* synthetic */ ChromeActivity I;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C1906bq(ChromeActivity chromeActivity, AbstractC0124Ca1 ca1) {
        super(ca1);
        this.I = chromeActivity;
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void F(Tab tab, boolean z) {
        this.I.m1();
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void J(Tab tab, GURL gurl) {
        this.I.m1();
        C1446Xr0 xr0 = AbstractC2254ds0.f9814a;
        ChromeActivity chromeActivity = (ChromeActivity) tab.i().s0().get();
        if (C0897Or0.F == null) {
            C0897Or0.F = new HashMap();
            ApplicationStatus.g.b(new C0654Kr0());
        }
        C0897Or0 or0 = (C0897Or0) C0897Or0.F.get(chromeActivity);
        if (or0 == null) {
            or0 = new C0897Or0(chromeActivity.P(), chromeActivity.U(), new C0775Mr0(chromeActivity.P()));
            C0897Or0.F.put(chromeActivity, or0);
        }
        if (AbstractC2254ds0.f(tab)) {
            or0.M = tab;
            if (!or0.V(tab)) {
                or0.K.put(Integer.valueOf(tab.getId()), new C0836Nr0(true));
                tab.A(or0);
            }
            if (!or0.L) {
                NetworkChangeNotifier.a(or0);
                or0.L = true;
            }
        }
        or0.W(tab, false);
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void q(Tab tab) {
        this.I.m1();
    }
}
