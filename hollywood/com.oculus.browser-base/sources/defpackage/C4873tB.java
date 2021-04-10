package defpackage;

import org.chromium.chrome.browser.tab.Tab;

/* renamed from: tB  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C4873tB implements Q31 {
    public final Tab F;

    public C4873tB(Tab tab) {
        this.F = tab;
    }

    @Override // defpackage.Q31
    public Object get() {
        Tab tab = this.F;
        C5383wB wBVar = new C5383wB(tab, "", "", -1, tab.getId(), -1, null, -1, "", 0, null);
        wBVar.n();
        return wBVar;
    }
}
