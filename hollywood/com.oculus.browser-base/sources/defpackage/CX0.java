package defpackage;

import android.view.View;
import androidx.preference.PreferenceScreen;
import org.chromium.components.browser_ui.settings.ChromeImageViewPreference;
import org.chromium.components.browser_ui.site_settings.SingleWebsiteSettings;

/* renamed from: CX0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class CX0 implements View.OnClickListener {
    public final SingleWebsiteSettings F;
    public final C5316vp G;
    public final PreferenceScreen H;
    public final ChromeImageViewPreference I;

    public CX0(SingleWebsiteSettings singleWebsiteSettings, C5316vp vpVar, PreferenceScreen preferenceScreen, ChromeImageViewPreference chromeImageViewPreference) {
        this.F = singleWebsiteSettings;
        this.G = vpVar;
        this.H = preferenceScreen;
        this.I = chromeImageViewPreference;
    }

    public void onClick(View view) {
        this.F.x1(this.G, this.H, this.I);
    }
}
