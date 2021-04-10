package defpackage;

import org.chromium.chrome.browser.tabmodel.TabModel;

/* renamed from: Lk1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class Lk1 extends VK {
    public final /* synthetic */ Uk1 F;

    public Lk1(Uk1 uk1) {
        this.F = uk1;
    }

    @Override // defpackage.VK, defpackage.AbstractC0612Ka1
    public void b() {
        Uk1 uk1 = this.F;
        uk1.K0 = true;
        if (1 != 0 && uk1.L0) {
            uk1.K.f9104a.z();
        }
    }

    @Override // defpackage.VK, defpackage.AbstractC0612Ka1
    public void c(TabModel tabModel, TabModel tabModel2) {
        Uk1 uk1 = this.F;
        AbstractC0124Ca1 ca1 = uk1.U;
        if (ca1 != null) {
            uk1.o(((AbstractC0246Ea1) ca1).j());
        }
    }
}
