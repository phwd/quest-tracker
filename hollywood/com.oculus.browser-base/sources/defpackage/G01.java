package defpackage;

import org.chromium.chrome.browser.tabmodel.TabModel;

/* renamed from: G01  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class G01 extends VK {
    public final /* synthetic */ M01 F;

    public G01(M01 m01) {
        this.F = m01;
    }

    @Override // defpackage.VK, defpackage.AbstractC0612Ka1
    public void c(TabModel tabModel, TabModel tabModel2) {
        M01 m01 = this.F;
        boolean a2 = tabModel.a();
        if (a2 != m01.P) {
            m01.P = a2;
            m01.I.j(AbstractC5798yf1.b, a2);
            m01.m();
            if (m01.I.h(N01.g)) {
                m01.g();
            }
        }
    }
}
