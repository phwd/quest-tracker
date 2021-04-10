package defpackage;

import J.N;
import org.chromium.chrome.browser.feed.v2.FeedStreamSurface;
import org.chromium.components.prefs.PrefService;

/* renamed from: aP  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC1656aP implements Runnable {
    public final C2861hP F;

    public RunnableC1656aP(C2861hP hPVar) {
        this.F = hPVar;
    }

    public void run() {
        C2861hP hPVar = this.F;
        PrefService W = hPVar.W();
        N.Mf2ABpoH(W.f10883a, "ntp_snippets.list_visible", hPVar.K.M);
        K21 k21 = hPVar.F.t;
        boolean z = hPVar.K.M;
        FeedStreamSurface feedStreamSurface = ((FO) k21).f8014a;
        if (feedStreamSurface.n != z) {
            feedStreamSurface.n = z;
            feedStreamSurface.d();
        }
        hPVar.F.v.b();
    }
}
