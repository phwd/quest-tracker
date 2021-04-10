package defpackage;

import org.chromium.base.Callback;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.content_public.browser.WebContents;

/* renamed from: Hp  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0466Hp extends Y2 {
    public final /* synthetic */ C0649Kp d;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C0466Hp(C0649Kp kp, C1595a3 a3Var) {
        super(a3Var);
        this.d = kp;
    }

    @Override // defpackage.Y2
    public void V(Tab tab, boolean z) {
        Tab tab2;
        WebContents webContents;
        if (tab != null && tab != (tab2 = this.d.b)) {
            if (tab2 != null && tab2.isInitialized()) {
                C0377Gd1.j(this.d.b).H.c(this.d.f8390a);
            }
            this.d.b = tab;
            C0377Gd1 j = C0377Gd1.j(tab);
            Callback callback = this.d.f8390a;
            if (j.H.b(callback) && (webContents = j.G.L) != null) {
                callback.onResult(webContents);
            }
        }
    }
}
