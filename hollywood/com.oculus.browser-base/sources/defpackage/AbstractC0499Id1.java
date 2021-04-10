package defpackage;

import org.chromium.chrome.browser.tab.Tab;
import org.chromium.content_public.browser.WebContents;

/* renamed from: Id1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC0499Id1 extends Pr1 {
    public WebContents F;

    public AbstractC0499Id1(Tab tab) {
        tab.A(new C0438Hd1(this));
    }

    public abstract void c(WebContents webContents);

    @Override // defpackage.Qr1, defpackage.Pr1
    public final void destroy() {
        c(this.F);
        e();
    }

    public void e() {
    }

    public abstract void h(WebContents webContents);
}
