package defpackage;

import java.util.Objects;
import org.chromium.chrome.browser.privacy.settings.PrivacySettings;

/* renamed from: dG0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C2157dG0 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final PrivacySettings f9765a;

    public C2157dG0(PrivacySettings privacySettings) {
        this.f9765a = privacySettings;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        PrivacySettings privacySettings = this.f9765a;
        Objects.requireNonNull(privacySettings);
        if (((Boolean) obj).booleanValue()) {
            privacySettings.o1();
        }
    }
}
