package defpackage;

import com.oculus.browser.PanelApp;
import org.chromium.chrome.browser.tabmodel.TabModel;

/* renamed from: lw0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3633lw0 extends VK {
    public final /* synthetic */ PanelApp F;

    public C3633lw0(PanelApp panelApp) {
        this.F = panelApp;
    }

    @Override // defpackage.VK, defpackage.AbstractC0612Ka1
    public void c(TabModel tabModel, TabModel tabModel2) {
        AbstractC5783ya1 ya1 = this.F.P;
        if (ya1 != null) {
            tabModel2.w(ya1);
            tabModel.n(this.F.P);
        }
        this.F.t();
    }
}
