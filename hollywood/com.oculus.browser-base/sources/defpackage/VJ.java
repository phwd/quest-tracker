package defpackage;

import java.util.Objects;
import org.chromium.chrome.browser.tab.Tab;

/* renamed from: VJ  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class VJ implements Runnable {
    public final YJ F;

    public VJ(YJ yj) {
        this.F = yj;
    }

    public void run() {
        YJ yj = this.F;
        Objects.requireNonNull(yj);
        AbstractC3535lK0.a("Omnibox.EditUrlSuggestion.Share");
        ((C3909na0) yj.h).N.F.n(false, null, 12);
        ((GT0) yj.j.get()).c((Tab) yj.k.get(), false, 5);
    }
}
