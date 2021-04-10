package defpackage;

import java.util.Objects;
import org.chromium.base.ThreadUtils;
import org.chromium.chrome.browser.tab.Tab;

/* renamed from: j00  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC3133j00 implements Runnable {
    public final C3304k00 F;

    public RunnableC3133j00(C3304k00 k00) {
        this.F = k00;
    }

    public void run() {
        C3304k00 k00 = this.F;
        k00.c.run();
        C2792h00 h00 = k00.b;
        Objects.requireNonNull(h00);
        Object obj = ThreadUtils.f10596a;
        Tab c = AbstractC1160Ta1.c(h00.d);
        h00.b.f(c.s(), 2);
        h00.d.h(c);
    }
}
