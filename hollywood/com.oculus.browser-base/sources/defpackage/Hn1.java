package defpackage;

import J.N;
import android.view.View;
import java.util.Objects;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.components.search_engines.TemplateUrlService;

/* renamed from: Hn1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class Hn1 implements View.OnClickListener {
    public final Jn1 F;
    public final String G;

    public Hn1(Jn1 jn1, String str) {
        this.F = jn1;
        this.G = str;
    }

    public void onClick(View view) {
        Jn1 jn1 = this.F;
        String str = this.G;
        Objects.requireNonNull(jn1);
        AbstractC3535lK0.a("StartSurface.TrendyTerms.TapTerm");
        TemplateUrlService a2 = AbstractC0444Hf1.a();
        EM0.g(N.Mweksmrf(a2.c, a2, str, null), 2, null, (Tab) jn1.b.get());
    }
}
