package defpackage;

import android.view.View;
import org.chromium.components.browser_ui.settings.ChromeImageViewPreference;

/* renamed from: ac0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class View$OnClickListenerC1685ac0 implements View.OnClickListener {
    public final AbstractC1528Zb0 F;
    public final ChromeImageViewPreference G;

    public View$OnClickListenerC1685ac0(AbstractC1528Zb0 zb0, ChromeImageViewPreference chromeImageViewPreference) {
        this.F = zb0;
        this.G = chromeImageViewPreference;
    }

    public void onClick(View view) {
        AbstractC1528Zb0 zb0 = this.F;
        ChromeImageViewPreference chromeImageViewPreference = this.G;
        if (zb0.d(chromeImageViewPreference)) {
            AbstractC1865bc0.e(chromeImageViewPreference.F);
        } else if (zb0.a(chromeImageViewPreference)) {
            AbstractC1865bc0.f(chromeImageViewPreference.F, zb0);
        }
    }
}
