package defpackage;

import org.chromium.chrome.browser.profiles.Profile;

/* renamed from: wa0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C5442wa0 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final View$OnKeyListenerC0001Aa0 f11550a;

    public C5442wa0(View$OnKeyListenerC0001Aa0 aa0) {
        this.f11550a = aa0;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        this.f11550a.t((Profile) obj);
    }
}
