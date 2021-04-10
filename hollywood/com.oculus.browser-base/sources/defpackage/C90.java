package defpackage;

import org.chromium.chrome.browser.tab.Tab;

/* renamed from: C90  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C90 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final E90 f7790a;

    public C90(E90 e90) {
        this.f7790a = e90;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        this.f7790a.b((Tab) obj);
    }
}
