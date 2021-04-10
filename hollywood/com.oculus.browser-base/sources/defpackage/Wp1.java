package defpackage;

import org.chromium.base.Callback;
import org.chromium.chrome.browser.app.ChromeActivity;

/* renamed from: Wp1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class Wp1 implements AbstractC4371qE {
    public final Callback F;
    public ChromeActivity G;

    public Wp1(ChromeActivity chromeActivity) {
        Tp1 tp1 = new Tp1(this);
        this.F = tp1;
        this.G = chromeActivity;
        AbstractC4981tq1.f11374a.a(tp1);
        this.G.Y.a(this);
    }

    @Override // defpackage.AbstractC4371qE
    public void destroy() {
        AbstractC4981tq1.f11374a.e(this.F);
        this.G.Y.b(this);
        this.G = null;
    }
}
