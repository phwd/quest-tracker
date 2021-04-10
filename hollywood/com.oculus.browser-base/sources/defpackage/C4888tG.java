package defpackage;

import J.N;
import androidx.preference.Preference;
import org.chromium.chrome.browser.privacy.settings.DoNotTrackSettings;
import org.chromium.components.prefs.PrefService;

/* renamed from: tG  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C4888tG implements XE0 {
    public final PrefService F;

    public C4888tG(PrefService prefService) {
        this.F = prefService;
    }

    @Override // defpackage.XE0
    public boolean a(Preference preference, Object obj) {
        PrefService prefService = this.F;
        int i = DoNotTrackSettings.G0;
        N.Mf2ABpoH(prefService.f10883a, "enable_do_not_track", ((Boolean) obj).booleanValue());
        return true;
    }
}
