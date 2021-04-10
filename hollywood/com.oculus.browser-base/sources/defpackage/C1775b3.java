package defpackage;

import org.chromium.base.ThreadUtils;
import org.chromium.chrome.browser.metrics.PageLoadMetrics;

/* renamed from: b3  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C1775b3 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final C2287e3 f9510a;

    public C1775b3(C2287e3 e3Var) {
        this.f9510a = e3Var;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        C2287e3 e3Var = this.f9510a;
        AbstractC0124Ca1 ca1 = (AbstractC0124Ca1) obj;
        if (e3Var.f) {
            e3Var.d = new C1946c3(e3Var, ca1);
            C2117d3 d3Var = new C2117d3(e3Var);
            e3Var.e = d3Var;
            Object obj2 = ThreadUtils.f10596a;
            if (PageLoadMetrics.f10695a == null) {
                PageLoadMetrics.f10695a = new C1322Vq0();
            }
            PageLoadMetrics.f10695a.b(d3Var);
        }
    }
}
