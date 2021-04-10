package defpackage;

import org.chromium.chrome.browser.tab.Tab;

/* renamed from: Ga1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0368Ga1 extends VK {
    public final /* synthetic */ C0551Ja1 F;

    public C0368Ga1(C0551Ja1 ja1) {
        this.F = ja1;
    }

    @Override // defpackage.VK, defpackage.AbstractC0612Ka1
    public void f(Tab tab, int i) {
        if (AbstractC1160Ta1.d(this.F.i(), tab.getId()) != null) {
            this.F.s.g(tab.getId(), tab.s());
        }
        if (i == 3) {
            C4766sb1 sb1 = this.F.m;
            sb1.d(tab);
            sb1.t();
        }
    }
}
