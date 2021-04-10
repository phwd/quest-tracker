package defpackage;

import android.view.View;
import org.chromium.chrome.browser.privacy.settings.PrivacySettings;

/* renamed from: YF0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class YF0 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final PrivacySettings f9265a;
    public final C2528fT0 b;

    public YF0(PrivacySettings privacySettings, C2528fT0 ft0) {
        this.f9265a = privacySettings;
        this.b = ft0;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        View view = (View) obj;
        this.f9265a.k1(this.b);
    }
}
