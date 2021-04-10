package defpackage;

import J.N;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.url.GURL;

/* renamed from: bd  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1867bd extends Y2 {
    public C1867bd(C2379ed edVar, C1595a3 a3Var) {
        super(a3Var);
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void J(Tab tab, GURL gurl) {
        if (gurl != null && AbstractC5154ur1.h(gurl)) {
            N.MjJ0r9e$();
        }
    }

    @Override // defpackage.Y2
    public void V(Tab tab, boolean z) {
        GURL url;
        if (tab != null && (url = tab.getUrl()) != null && AbstractC5154ur1.h(url)) {
            N.MjJ0r9e$();
        }
    }
}
