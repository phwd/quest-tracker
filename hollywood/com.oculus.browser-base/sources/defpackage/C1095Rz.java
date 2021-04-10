package defpackage;

import java.util.Iterator;
import org.chromium.chrome.browser.contextualsearch.ContextualSearchContext;
import org.chromium.chrome.browser.contextualsearch.ContextualSearchManager;

/* renamed from: Rz  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1095Rz extends ContextualSearchContext {
    public final /* synthetic */ C1278Uz s;

    public C1095Rz(C1278Uz uz) {
        this.s = uz;
    }

    @Override // org.chromium.chrome.browser.contextualsearch.ContextualSearchContext
    public void f() {
        ContextualSearchManager contextualSearchManager = this.s.f9059a;
        ContextualSearchContext contextualSearchContext = contextualSearchManager.h0;
        String str = contextualSearchContext.c;
        int i = contextualSearchContext.d;
        int i2 = contextualSearchContext.e;
        if (!AbstractC5686xz.c(16)) {
            C4238pU pUVar = new C4238pU(contextualSearchManager.h0.i, str, i, i2);
            if (!contextualSearchManager.R.b()) {
                pUVar = null;
            }
            Iterator it = contextualSearchManager.F.iterator();
            while (true) {
                C1261Uq0 uq0 = (C1261Uq0) it;
                if (uq0.hasNext()) {
                    ((AbstractC1583Zz) uq0.next()).a(pUVar);
                } else {
                    return;
                }
            }
        }
    }
}
