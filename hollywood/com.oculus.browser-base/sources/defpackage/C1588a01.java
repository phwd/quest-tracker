package defpackage;

import J.N;
import androidx.preference.Preference;
import java.util.Objects;
import org.chromium.chrome.browser.safe_browsing.settings.StandardProtectionSettingsFragment;

/* renamed from: a01  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C1588a01 extends AbstractC0896Or {

    /* renamed from: a  reason: collision with root package name */
    public final StandardProtectionSettingsFragment f9402a;

    public C1588a01(StandardProtectionSettingsFragment standardProtectionSettingsFragment) {
        this.f9402a = standardProtectionSettingsFragment;
    }

    @Override // defpackage.AbstractC1528Zb0
    public boolean d(Preference preference) {
        StandardProtectionSettingsFragment standardProtectionSettingsFragment = this.f9402a;
        Objects.requireNonNull(standardProtectionSettingsFragment);
        String str = preference.Q;
        if ("extended_reporting".equals(str)) {
            return N.Mp340wGB();
        }
        if ("password_leak_detection".equals(str)) {
            return N.MrEgF7hX(standardProtectionSettingsFragment.J0.f10883a, "profile.password_manager_leak_detection");
        }
        return false;
    }
}
