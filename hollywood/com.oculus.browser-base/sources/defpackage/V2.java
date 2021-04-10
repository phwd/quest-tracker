package defpackage;

import org.chromium.chrome.browser.tabmodel.TabModel;

/* renamed from: V2  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class V2 extends VK {
    public final /* synthetic */ C1595a3 F;

    public V2(C1595a3 a3Var) {
        this.F = a3Var;
    }

    @Override // defpackage.VK, defpackage.AbstractC0612Ka1
    public void c(TabModel tabModel, TabModel tabModel2) {
        if (tabModel.getCount() == 0) {
            C1595a3.a(this.F, null);
        }
    }
}
