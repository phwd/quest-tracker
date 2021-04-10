package defpackage;

import J.N;
import java.util.Objects;
import org.chromium.base.ThreadUtils;
import org.chromium.chrome.browser.browsing_data.BrowsingDataBridge;

/* renamed from: r51  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class RunnableC4516r51 implements Runnable {
    public final /* synthetic */ C0695Li F;
    public final /* synthetic */ C5232vH0 G;

    public RunnableC4516r51(C0695Li li, C5232vH0 vh0) {
        this.F = li;
        this.G = vh0;
    }

    public void run() {
        C0695Li li = this.F;
        Objects.requireNonNull(li);
        Object obj = ThreadUtils.f10596a;
        N.M70Imm05(li.b, li);
        this.F.a();
        BrowsingDataBridge.c().a(new C4346q51(this), AbstractC4687s51.f11250a, 4);
    }
}
