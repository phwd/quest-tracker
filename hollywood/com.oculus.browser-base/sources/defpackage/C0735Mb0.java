package defpackage;

import J.N;
import androidx.preference.Preference;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.sync.settings.ManageSyncSettings;

/* renamed from: Mb0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C0735Mb0 implements XE0 {
    public final Profile F;

    public C0735Mb0(Profile profile) {
        this.F = profile;
    }

    @Override // defpackage.XE0
    public boolean a(Preference preference, Object obj) {
        Profile profile = this.F;
        int i = ManageSyncSettings.G0;
        N.MnEYaN9w(profile, ((Boolean) obj).booleanValue());
        return true;
    }
}
