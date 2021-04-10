package defpackage;

import J.N;
import java.util.Objects;
import org.chromium.chrome.browser.partnercustomizations.PartnerBrowserCustomizations;

/* renamed from: Iw0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC0542Iw0 implements Runnable {
    public final C0785Mw0 F;
    public final PartnerBrowserCustomizations G;

    public RunnableC0542Iw0(C0785Mw0 mw0, PartnerBrowserCustomizations partnerBrowserCustomizations) {
        this.F = mw0;
        this.G = partnerBrowserCustomizations;
    }

    public void run() {
        C0785Mw0 mw0 = this.F;
        PartnerBrowserCustomizations partnerBrowserCustomizations = this.G;
        Objects.requireNonNull(mw0);
        if (partnerBrowserCustomizations.d) {
            N.McEggOd3();
        }
        mw0.j = true;
        if (mw0.i) {
            N.MZy4XMIu(mw0.c, mw0);
        }
    }
}
