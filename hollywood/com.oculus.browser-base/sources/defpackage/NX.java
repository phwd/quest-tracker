package defpackage;

import org.chromium.chrome.browser.toolbar.HomeButton;

/* renamed from: NX  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class NX extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final HomeButton f8551a;

    public NX(HomeButton homeButton) {
        this.f8551a = homeButton;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        Boolean bool = (Boolean) obj;
        this.f8551a.d();
    }
}
