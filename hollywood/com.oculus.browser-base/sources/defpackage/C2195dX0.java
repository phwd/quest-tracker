package defpackage;

import androidx.preference.Preference;
import org.chromium.components.browser_ui.site_settings.SingleCategorySettings;

/* renamed from: dX0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2195dX0 extends AbstractC2184dS {
    public final /* synthetic */ SingleCategorySettings b;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C2195dX0(SingleCategorySettings singleCategorySettings, AbstractC1528Zb0 zb0) {
        super(zb0);
        this.b = singleCategorySettings;
    }

    @Override // defpackage.AbstractC1528Zb0, defpackage.AbstractC2184dS
    public boolean a(Preference preference) {
        return this.b.J0.o();
    }

    @Override // defpackage.AbstractC1528Zb0
    public boolean d(Preference preference) {
        return this.b.J0.n() && !this.b.J0.o();
    }
}
