package defpackage;

import org.chromium.chrome.browser.tab.Tab;
import org.chromium.content.browser.GestureListenerManagerImpl;
import org.chromium.content_public.browser.WebContents;

/* renamed from: F61  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class F61 extends AbstractC0499Id1 {
    public final Tab G;
    public AbstractC5601xV H;

    public F61(Tab tab) {
        super(tab);
        this.G = tab;
    }

    @Override // defpackage.AbstractC0499Id1
    public void c(WebContents webContents) {
        GestureListenerManagerImpl s0;
        if (!(webContents == null || (s0 = GestureListenerManagerImpl.s0(webContents)) == null)) {
            s0.u0(this.H);
        }
        this.H = null;
    }

    @Override // defpackage.AbstractC0499Id1
    public void h(WebContents webContents) {
        GestureListenerManagerImpl s0 = GestureListenerManagerImpl.s0(webContents);
        E61 e61 = new E61(this, s0);
        this.H = e61;
        s0.r0(e61);
    }
}
