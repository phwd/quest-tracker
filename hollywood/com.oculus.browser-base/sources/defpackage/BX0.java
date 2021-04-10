package defpackage;

import androidx.preference.Preference;
import org.chromium.components.browser_ui.site_settings.SingleWebsiteSettings;

/* renamed from: BX0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class BX0 implements YE0 {
    public final SingleWebsiteSettings F;
    public final Preference G;

    public BX0(SingleWebsiteSettings singleWebsiteSettings, Preference preference) {
        this.F = singleWebsiteSettings;
        this.G = preference;
    }

    @Override // defpackage.YE0
    public boolean d(Preference preference) {
        return this.F.y1(this.G);
    }
}
