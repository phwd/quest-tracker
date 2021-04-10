package defpackage;

import org.chromium.chrome.browser.tab.Tab;

/* renamed from: zB  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C5893zB extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final AB f11731a;

    public C5893zB(AB ab) {
        this.f11731a = ab;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        AB ab = this.f11731a;
        Tab tab = (Tab) obj;
        Tab tab2 = ab.e;
        if (tab2 != tab) {
            if (tab2 != null) {
                tab2.I(ab.b);
            }
            ab.e = tab;
            if (tab != null) {
                tab.A(ab.b);
            }
        }
    }
}
