package defpackage;

import org.chromium.chrome.browser.app.ChromeActivity;
import org.chromium.chrome.browser.ui.messages.infobar.SimpleConfirmInfoBarBuilder;

/* renamed from: Vp1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class Vp1 implements SimpleConfirmInfoBarBuilder.Listener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ Wp1 f9107a;

    public Vp1(Wp1 wp1) {
        this.f9107a = wp1;
    }

    @Override // org.chromium.chrome.browser.ui.messages.infobar.SimpleConfirmInfoBarBuilder.Listener
    public boolean a(boolean z) {
        ChromeActivity chromeActivity;
        if (!z || (chromeActivity = this.f9107a.G) == null) {
            return false;
        }
        AbstractC4981tq1.f11374a.f(1, chromeActivity);
        return false;
    }

    @Override // org.chromium.chrome.browser.ui.messages.infobar.SimpleConfirmInfoBarBuilder.Listener
    public void b() {
    }

    @Override // org.chromium.chrome.browser.ui.messages.infobar.SimpleConfirmInfoBarBuilder.Listener
    public boolean c() {
        return false;
    }
}
