package defpackage;

import android.os.SystemClock;
import java.util.Objects;
import org.chromium.chrome.browser.AppHooks;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tab.TabImpl;
import org.chromium.content_public.browser.RenderFrameHost;
import org.chromium.content_public.browser.WebContents;
import org.chromium.url.GURL;

/* renamed from: a9  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1613a9 extends AbstractC1099Sa1 {
    public final /* synthetic */ C1964c9 I;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C1613a9(C1964c9 c9Var, AbstractC0124Ca1 ca1) {
        super(ca1);
        this.I = c9Var;
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void J(Tab tab, GURL gurl) {
        RenderFrameHost N;
        C3826n30 h;
        C1793b9 b9Var;
        C1793b9 b9Var2;
        C1964c9 c9Var = this.I;
        if (c9Var.b(tab)) {
            String s = tab.s();
            if ((s == null || (b9Var2 = (C1793b9) c9Var.a().get(s)) == null || SystemClock.elapsedRealtime() - b9Var2.f9517a > 3600000) ? false : true) {
                if ((s == null || (b9Var = (C1793b9) c9Var.a().get(s)) == null || !b9Var.b) ? false : true) {
                    AbstractC3364kK0.g("CopylessPaste.CacheHit", 0, 3);
                } else {
                    AbstractC3364kK0.g("CopylessPaste.CacheHit", 1, 3);
                }
            } else {
                AbstractC3364kK0.g("CopylessPaste.CacheHit", 2, 3);
                WebContents l = tab.l();
                AbstractC5228vG vGVar = null;
                if (!(l == null || (N = l.N()) == null || (h = N.h()) == null)) {
                    int i = AbstractC5228vG.n;
                    vGVar = (AbstractC5228vG) h.a(DG.f7876a);
                }
                if (vGVar != null) {
                    ((BG) vGVar).f0(new Z8(c9Var, vGVar, s));
                }
            }
        }
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void h(Tab tab) {
        if (this.I.b(tab)) {
            if (Y8.f9254a == null) {
                Objects.requireNonNull(AppHooks.get());
                Y8.f9254a = new Y8();
            }
            Y8 y8 = Y8.f9254a;
            ((TabImpl) tab).s();
            tab.getTitle();
            Objects.requireNonNull(y8);
        }
    }
}
