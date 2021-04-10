package defpackage;

import java.util.Objects;
import org.chromium.base.ContextUtils;
import org.chromium.chrome.browser.app.ChromeActivity;

/* renamed from: Tp  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC1197Tp implements Runnable {
    public final ChromeActivity F;

    public RunnableC1197Tp(ChromeActivity chromeActivity) {
        this.F = chromeActivity;
    }

    public void run() {
        ChromeActivity chromeActivity = this.F;
        Objects.requireNonNull(chromeActivity);
        if (!C5259vU.b(chromeActivity).d()) {
            AbstractC3641lz.a(1);
            return;
        }
        if (C4067oU.f10552a == null) {
            C4067oU.f10552a = new C4067oU(ContextUtils.getApplicationContext());
        }
        C4067oU oUVar = C4067oU.f10552a;
        C5089uU uUVar = oUVar.c;
        if (uUVar != null) {
            uUVar.a();
        }
        oUVar.b++;
        chromeActivity.B0();
    }
}
