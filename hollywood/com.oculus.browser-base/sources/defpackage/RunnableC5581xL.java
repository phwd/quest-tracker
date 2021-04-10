package defpackage;

import java.util.Objects;
import org.chromium.content_public.browser.LoadUrlParams;

/* renamed from: xL  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC5581xL implements Runnable {
    public final DL F;

    public RunnableC5581xL(DL dl) {
        this.F = dl;
    }

    public void run() {
        DL dl = this.F;
        Objects.requireNonNull(dl);
        if (dl.R != null) {
            ((C5638xj) dl.K).p(dl.P, true, 7);
            ((A61) dl.f7883J.get()).b(new LoadUrlParams(dl.R.h(), 0), 0, dl.I.H);
            dl.L.c(7);
            AbstractC3535lK0.a("EphemeralTab.OpenInNewTab");
        }
    }
}
