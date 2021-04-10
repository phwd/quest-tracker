package defpackage;

import java.util.List;
import org.chromium.base.Callback;
import org.chromium.chrome.browser.tab.Tab;

/* renamed from: ZU0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class ZU0 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final Callback f9345a;
    public final Tab b;
    public final C2361eV0 c;

    public ZU0(Callback callback, Tab tab, C2361eV0 ev0) {
        this.f9345a = callback;
        this.b = tab;
        this.c = ev0;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        int i;
        Callback callback = this.f9345a;
        Tab tab = this.b;
        C2361eV0 ev0 = this.c;
        List list = (List) obj;
        C2361eV0 ev02 = new C2361eV0(tab);
        C0760Mk mk = (C0760Mk) AbstractC5162uu0.b(list, C0760Mk.class);
        SG0 sg0 = (SG0) AbstractC5162uu0.b(list, SG0.class);
        if (mk != null && sg0 != null) {
            ev02.V = sg0.c;
            ev02.n();
            ev02.W = sg0.b;
            ev02.n();
            ev02.X = sg0.d;
            ev02.n();
            ev02.f9756J = System.currentTimeMillis();
            i = 2;
        } else if (mk != null) {
            long j = mk.b;
            ev02.V = j;
            if (!(ev0 == null || j == -1)) {
                long j2 = ev0.V;
                if (!(j2 == -1 || j == j2)) {
                    ev02.W = j2;
                    ev02.U = System.currentTimeMillis();
                    ev02.n();
                    ev02.X = mk.c;
                    ev02.n();
                    ev02.f9756J = System.currentTimeMillis();
                    i = 1;
                }
            }
            if (ev0 != null) {
                ev02.W = ev0.W;
                ev02.U = ev0.U;
            }
            ev02.n();
            ev02.X = mk.c;
            ev02.n();
            ev02.f9756J = System.currentTimeMillis();
            i = 1;
        } else {
            i = 0;
        }
        AbstractC3364kK0.g("Tabs.ShoppingPersistedTabData.FoundBuyableProductAnnotation", i, 3);
        if (i == 1 || i == 2) {
            ev02.Y.m(Boolean.TRUE);
        } else {
            ev02 = null;
        }
        callback.onResult(ev02);
    }
}
