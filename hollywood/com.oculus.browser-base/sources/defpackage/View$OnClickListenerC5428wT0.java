package defpackage;

import android.view.View;
import org.chromium.chrome.browser.tab.Tab;

/* renamed from: wT0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class View$OnClickListenerC5428wT0 implements View.OnClickListener {
    public final C5768yT0 F;
    public final Runnable G;

    public View$OnClickListenerC5428wT0(C5768yT0 yt0, Runnable runnable) {
        this.F = yt0;
        this.G = runnable;
    }

    public void onClick(View view) {
        Tab tab;
        C5768yT0 yt0 = this.F;
        Runnable runnable = this.G;
        GT0 gt0 = (GT0) ((C1078Rq0) yt0.H).H;
        if (gt0 != null && (tab = yt0.f11684J.H) != null) {
            if (runnable != null) {
                runnable.run();
            }
            AbstractC3535lK0.a("MobileTopToolbarShareButton");
            gt0.c(tab, false, 1);
        }
    }
}
