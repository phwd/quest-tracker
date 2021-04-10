package defpackage;

import android.view.View;
import org.chromium.chrome.browser.privacy.settings.PrivacySettings;

/* renamed from: ZF0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class ZF0 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final PrivacySettings f9332a;
    public final C2528fT0 b;

    public ZF0(PrivacySettings privacySettings, C2528fT0 ft0) {
        this.f9332a = privacySettings;
        this.b = ft0;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        View view = (View) obj;
        this.f9332a.l1(this.b);
    }
}
