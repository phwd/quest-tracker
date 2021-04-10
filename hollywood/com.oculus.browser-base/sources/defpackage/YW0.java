package defpackage;

import androidx.preference.Preference;
import org.chromium.components.browser_ui.site_settings.SingleCategorySettings;

/* renamed from: YW0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class YW0 implements YE0 {
    public final SingleCategorySettings F;

    public YW0(SingleCategorySettings singleCategorySettings) {
        this.F = singleCategorySettings;
    }

    @Override // defpackage.YE0
    public boolean d(Preference preference) {
        return this.F.q1();
    }
}
