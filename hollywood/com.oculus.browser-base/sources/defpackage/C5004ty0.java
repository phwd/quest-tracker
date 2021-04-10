package defpackage;

import org.chromium.chrome.browser.password_manager.settings.PasswordSettings;

/* renamed from: ty0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C5004ty0 implements QQ0 {

    /* renamed from: a  reason: collision with root package name */
    public final PasswordSettings f11377a;

    public C5004ty0(PasswordSettings passwordSettings) {
        this.f11377a = passwordSettings;
    }

    @Override // defpackage.QQ0
    public void onQueryTextChange(String str) {
        PasswordSettings passwordSettings = this.f11377a;
        passwordSettings.K0 = str;
        passwordSettings.I0.setShowAsAction(str == null ? 1 : 0);
        passwordSettings.t1();
    }
}
