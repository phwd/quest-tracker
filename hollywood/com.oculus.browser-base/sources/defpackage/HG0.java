package defpackage;

import java.util.Objects;
import org.chromium.chrome.browser.partnercustomizations.PartnerBrowserCustomizations;

/* renamed from: HG0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class HG0 implements Runnable {
    public HG0(OG0 og0) {
    }

    public void run() {
        int i;
        if (QX.g()) {
            QX c = QX.c();
            Objects.requireNonNull(c);
            if (SX.d()) {
                i = !AbstractC5154ur1.g(SX.b()) ? 1 : 0;
            } else {
                if (!c.e()) {
                    if (c.f()) {
                        i = !PartnerBrowserCustomizations.c().e() ? 6 : AbstractC5154ur1.g(PartnerBrowserCustomizations.c().b()) ? 2 : 3;
                    } else if (!AbstractC5154ur1.g(c.d())) {
                        i = 5;
                    }
                }
                i = 4;
            }
            AbstractC3364kK0.g("Settings.Homepage.LocationType", i, 7);
        }
    }
}
