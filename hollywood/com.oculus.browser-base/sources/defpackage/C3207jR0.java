package defpackage;

import android.text.TextUtils;
import java.util.Objects;
import org.chromium.base.ThreadUtils;
import org.chromium.components.search_engines.TemplateUrl;
import org.chromium.components.search_engines.TemplateUrlService;

/* renamed from: jR0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C3207jR0 implements AbstractC0322Ff1, AbstractC0383Gf1 {
    public C3207jR0(AbstractRunnableC2866hR0 hr0) {
    }

    @Override // defpackage.AbstractC0383Gf1
    public void N() {
        a();
    }

    public final void a() {
        String str;
        Object obj = ThreadUtils.f10596a;
        if (C2474f80.f9900a.f()) {
            TemplateUrlService a2 = AbstractC0444Hf1.a();
            if (a2.g()) {
                TemplateUrl a3 = a2.a();
                if (a3 != null) {
                    String b = a2.b(a3.b());
                    Pq1 d = Pq1.d(b, b, null);
                    AbstractC3378kR0.c = d.e.subSequence(d.g, d.h).toString();
                    str = a3.d();
                } else {
                    str = null;
                }
                Objects.requireNonNull(AbstractC3378kR0.a());
                PU0 pu0 = NU0.f8549a;
                if (!AbstractC3378kR0.c()) {
                    str = null;
                }
                if (!TextUtils.equals(pu0.i("org.chromium.chrome.browser.searchwidget.SEARCH_ENGINE_SHORTNAME", null), str)) {
                    pu0.p("org.chromium.chrome.browser.searchwidget.SEARCH_ENGINE_SHORTNAME", str);
                    AbstractC3378kR0.b(null);
                }
            }
        }
    }

    @Override // defpackage.AbstractC0322Ff1
    public void f() {
        AbstractC0444Hf1.a().k(this);
        a();
    }
}
