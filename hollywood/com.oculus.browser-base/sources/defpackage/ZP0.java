package defpackage;

import org.chromium.base.TraceEvent;
import org.chromium.chrome.browser.searchwidget.SearchActivity;

/* renamed from: ZP0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class ZP0 implements Runnable {
    public final SearchActivity F;

    public ZP0(SearchActivity searchActivity) {
        this.F = searchActivity;
    }

    public void run() {
        SearchActivity searchActivity = this.F;
        boolean z = false;
        searchActivity.l0 = false;
        C5478wm0 wm0 = searchActivity.X;
        if (Bw1.a().f != null) {
            z = true;
        }
        wm0.b(!z);
        if (searchActivity.m0) {
            TraceEvent.h0("onFirstDrawComplete");
            C5478wm0 wm02 = searchActivity.X;
            wm02.g = true;
            wm02.a();
        }
    }
}
