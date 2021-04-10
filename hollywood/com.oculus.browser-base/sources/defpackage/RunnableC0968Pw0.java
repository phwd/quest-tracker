package defpackage;

import org.chromium.chrome.browser.partnercustomizations.PartnerBrowserCustomizations;

/* renamed from: Pw0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC0968Pw0 implements Runnable {
    public final PartnerBrowserCustomizations F;
    public final Runnable G;

    public RunnableC0968Pw0(PartnerBrowserCustomizations partnerBrowserCustomizations, Runnable runnable) {
        this.F = partnerBrowserCustomizations;
        this.G = runnable;
    }

    public void run() {
        PartnerBrowserCustomizations partnerBrowserCustomizations = this.F;
        Runnable runnable = this.G;
        if (partnerBrowserCustomizations.f.remove(runnable)) {
            runnable.run();
        }
    }
}
