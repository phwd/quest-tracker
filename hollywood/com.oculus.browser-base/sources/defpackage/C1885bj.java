package defpackage;

import org.chromium.chrome.browser.ui.BottomContainer;

/* renamed from: bj  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C1885bj extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final BottomContainer f9559a;

    public C1885bj(BottomContainer bottomContainer) {
        this.f9559a = bottomContainer;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        Integer num = (Integer) obj;
        this.f9559a.f();
    }
}
