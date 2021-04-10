package defpackage;

import J.N;
import androidx.preference.Preference;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.sync.settings.ManageSyncSettings;

/* renamed from: Nb0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C0796Nb0 extends AbstractC0896Or {

    /* renamed from: a  reason: collision with root package name */
    public final Profile f8556a;

    public C0796Nb0(Profile profile) {
        this.f8556a = profile;
    }

    @Override // defpackage.AbstractC1528Zb0
    public boolean d(Preference preference) {
        Profile profile = this.f8556a;
        int i = ManageSyncSettings.G0;
        return N.MIMq96JJ(profile);
    }
}
