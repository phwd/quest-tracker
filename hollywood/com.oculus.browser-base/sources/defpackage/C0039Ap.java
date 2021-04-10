package defpackage;

import androidx.preference.Preference;
import org.chromium.components.browser_ui.site_settings.ChosenObjectSettings;

/* renamed from: Ap  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0039Ap extends AbstractC2184dS {
    public final /* synthetic */ C5316vp b;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C0039Ap(ChosenObjectSettings chosenObjectSettings, AbstractC1528Zb0 zb0, C5316vp vpVar) {
        super(zb0);
        this.b = vpVar;
    }

    @Override // defpackage.AbstractC1467Yb0, defpackage.AbstractC1528Zb0, defpackage.AbstractC2184dS
    public boolean b(Preference preference) {
        return false;
    }

    @Override // defpackage.AbstractC1528Zb0
    public boolean d(Preference preference) {
        return this.b.K;
    }
}
