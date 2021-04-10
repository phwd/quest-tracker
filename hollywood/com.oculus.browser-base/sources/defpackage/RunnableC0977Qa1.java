package defpackage;

import java.util.List;
import java.util.Objects;
import org.chromium.chrome.browser.tab.Tab;

/* renamed from: Qa1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC0977Qa1 implements Runnable {
    public final C1038Ra1 F;
    public final List G;

    public RunnableC0977Qa1(C1038Ra1 ra1, List list) {
        this.F = ra1;
        this.G = list;
    }

    public void run() {
        C1038Ra1 ra1 = this.F;
        List<Tab> list = this.G;
        Objects.requireNonNull(ra1);
        for (Tab tab : list) {
            ra1.c.V(tab);
        }
    }
}
