package defpackage;

import java.util.Objects;
import org.chromium.chrome.browser.tab.Tab;

/* renamed from: Pa1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC0916Pa1 implements Runnable {
    public final C1038Ra1 F;
    public final Tab G;

    public RunnableC0916Pa1(C1038Ra1 ra1, Tab tab) {
        this.F = ra1;
        this.G = tab;
    }

    public void run() {
        C1038Ra1 ra1 = this.F;
        Tab tab = this.G;
        Objects.requireNonNull(ra1);
        if (!tab.g()) {
            tab.I(ra1.c);
            if (!tab.x()) {
                C5383wB q = C5383wB.q(tab);
                q.Y.c(ra1.c);
            }
        }
    }
}
