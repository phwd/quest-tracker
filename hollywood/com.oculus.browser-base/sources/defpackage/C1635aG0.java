package defpackage;

import android.view.View;
import org.chromium.chrome.browser.privacy.settings.PrivacySettings;

/* renamed from: aG0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C1635aG0 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final PrivacySettings f9422a;
    public final C2528fT0 b;

    public C1635aG0(PrivacySettings privacySettings, C2528fT0 ft0) {
        this.f9422a = privacySettings;
        this.b = ft0;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        View view = (View) obj;
        this.f9422a.m1(this.b);
    }
}
