package defpackage;

import org.chromium.chrome.browser.tab.Tab;

/* renamed from: Na1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0794Na1 extends VK {
    public final /* synthetic */ AbstractC0855Oa1 F;

    public C0794Na1(AbstractC0855Oa1 oa1) {
        this.F = oa1;
    }

    @Override // defpackage.VK, defpackage.AbstractC0612Ka1
    public void e() {
        ((AbstractC0246Ea1) this.F.f8633a).f.c(this);
        AbstractC0855Oa1 oa1 = this.F;
        oa1.b = null;
        oa1.M();
    }

    @Override // defpackage.VK, defpackage.AbstractC0612Ka1
    public void f(Tab tab, int i) {
        throw new IllegalStateException("onChange should have happened and unregistered this listener.");
    }
}
