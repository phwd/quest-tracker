package defpackage;

import J.N;
import androidx.preference.Preference;
import java.util.Objects;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.sync.settings.GoogleServicesSettings;

/* renamed from: oW  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C4071oW extends AbstractC0896Or {

    /* renamed from: a  reason: collision with root package name */
    public final GoogleServicesSettings f10555a;

    public C4071oW(GoogleServicesSettings googleServicesSettings) {
        this.f10555a = googleServicesSettings;
    }

    @Override // defpackage.AbstractC1528Zb0
    public boolean d(Preference preference) {
        GoogleServicesSettings googleServicesSettings = this.f10555a;
        Objects.requireNonNull(googleServicesSettings);
        String str = preference.Q;
        if ("allow_signin".equals(str)) {
            return N.MrEgF7hX(googleServicesSettings.G0.f10883a, "signin.allowed");
        }
        if ("navigation_error".equals(str)) {
            return N.MrEgF7hX(googleServicesSettings.G0.f10883a, "alternate_error_pages.enabled");
        }
        if ("search_suggestions".equals(str)) {
            return N.MrEgF7hX(googleServicesSettings.G0.f10883a, "search.suggest_enabled");
        }
        if ("safe_browsing_scout_reporting".equals(str)) {
            return N.Mp340wGB();
        }
        if ("safe_browsing".equals(str)) {
            return N.MrEgF7hX(googleServicesSettings.G0.f10883a, "safebrowsing.enabled");
        }
        if ("password_leak_detection".equals(str)) {
            return N.MrEgF7hX(googleServicesSettings.G0.f10883a, "profile.password_manager_leak_detection");
        }
        if ("usage_and_crash_reports".equals(str)) {
            Objects.requireNonNull(WF0.a());
            return N.Mx7M8SsH();
        } else if ("url_keyed_anonymized_data".equals(str)) {
            return N.MIMq96JJ(Profile.b());
        } else {
            return false;
        }
    }
}
