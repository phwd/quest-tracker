package defpackage;

import android.content.Intent;
import androidx.preference.Preference;
import org.chromium.components.browser_ui.site_settings.SingleWebsiteSettings;

/* renamed from: AX0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class AX0 implements YE0 {
    public final SingleWebsiteSettings F;
    public final Intent G;

    public AX0(SingleWebsiteSettings singleWebsiteSettings, Intent intent) {
        this.F = singleWebsiteSettings;
        this.G = intent;
    }

    @Override // defpackage.YE0
    public boolean d(Preference preference) {
        return this.F.z1(this.G);
    }
}
