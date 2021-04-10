package defpackage;

import androidx.preference.Preference;
import org.chromium.components.browser_ui.site_settings.SingleWebsiteSettings;

/* renamed from: GX0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class GX0 extends AbstractC2184dS {
    public final /* synthetic */ C5316vp b;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public GX0(SingleWebsiteSettings singleWebsiteSettings, AbstractC1528Zb0 zb0, C5316vp vpVar) {
        super(zb0);
        this.b = vpVar;
    }

    @Override // defpackage.AbstractC1528Zb0
    public boolean d(Preference preference) {
        return this.b.K;
    }
}
