package defpackage;

import J.N;
import androidx.preference.Preference;
import org.chromium.chrome.browser.profiles.Profile;

/* renamed from: Or  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract /* synthetic */ class AbstractC0896Or extends AbstractC1467Yb0 {
    @Override // defpackage.AbstractC1528Zb0
    public boolean a(Preference preference) {
        return false;
    }

    @Override // defpackage.AbstractC1528Zb0
    public boolean c() {
        return !N.Ma80fvz5(Wr1.a(Profile.b()).f10883a, "profile.managed.second_custodian_name").isEmpty();
    }
}
