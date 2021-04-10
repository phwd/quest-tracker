package defpackage;

import org.chromium.chrome.browser.app.ChromeActivity;
import org.chromium.chrome.browser.tab.Tab;

/* renamed from: ce1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C2043ce1 implements Q31 {
    public final C5285ve1 F;

    public C2043ce1(C5285ve1 ve1) {
        this.F = ve1;
    }

    @Override // defpackage.Q31
    public Object get() {
        C1595a3 a3Var;
        ChromeActivity chromeActivity = this.F.F;
        if (chromeActivity == null || (a3Var = chromeActivity.W0) == null) {
            return Boolean.FALSE;
        }
        Tab tab = a3Var.H;
        return Boolean.valueOf(tab != null && tab.isUserInteractable() && !tab.isNativePage());
    }
}
