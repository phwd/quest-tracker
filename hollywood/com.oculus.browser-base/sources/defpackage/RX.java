package defpackage;

import J.N;
import org.chromium.chrome.browser.preferences.PrefChangeRegistrar;

/* renamed from: RX  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RX implements Runnable {
    public final SX F;

    public RX(SX sx) {
        this.F = sx;
    }

    public void run() {
        SX sx = this.F;
        if (!sx.d) {
            PrefChangeRegistrar prefChangeRegistrar = new PrefChangeRegistrar();
            sx.e = prefChangeRegistrar;
            prefChangeRegistrar.f10748a.put("homepage", sx);
            N.Mrf8X6ah(prefChangeRegistrar.b, prefChangeRegistrar, "homepage");
            sx.d = true;
            sx.e();
        }
    }
}
