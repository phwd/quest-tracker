package defpackage;

import com.oculus.browser.PanelApp;
import org.chromium.content_public.browser.WebContents;

/* renamed from: nw0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3975nw0 extends AbstractC6022zx1 {
    public final /* synthetic */ PanelApp G;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C3975nw0(PanelApp panelApp, WebContents webContents) {
        super(webContents);
        this.G = panelApp;
    }

    @Override // defpackage.AbstractC6022zx1
    public void renderProcessGone(boolean z) {
        AbstractC1220Ua0.d("PanelApp", "NavUi Render Process Gone!", new Object[0]);
        PanelApp panelApp = this.G;
        if (!panelApp.b0) {
            panelApp.g();
        }
    }
}
