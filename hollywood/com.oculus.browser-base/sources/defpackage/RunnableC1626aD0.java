package defpackage;

import org.chromium.chrome.browser.tab.Tab;
import org.chromium.content_public.browser.WebContents;

/* renamed from: aD0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC1626aD0 implements Runnable {
    public final WebContents F;
    public final Tab G;

    public RunnableC1626aD0(WebContents webContents, Tab tab) {
        this.F = webContents;
        this.G = tab;
    }

    public void run() {
        C2831hD0.i(this.F, this.G);
    }
}
