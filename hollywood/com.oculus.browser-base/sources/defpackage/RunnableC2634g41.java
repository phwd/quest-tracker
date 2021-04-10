package defpackage;

import org.chromium.chrome.browser.compositor.layouts.content.TabContentManager;

/* renamed from: g41  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC2634g41 implements Runnable {
    public final C2976i41 F;
    public final TabContentManager G;

    public RunnableC2634g41(C2976i41 i41, TabContentManager tabContentManager) {
        this.F = i41;
        this.G = tabContentManager;
    }

    public void run() {
        C2976i41 i41 = this.F;
        TabContentManager tabContentManager = this.G;
        tabContentManager.j(i41.F.getId());
        tabContentManager.b(i41.F);
    }
}
