package defpackage;

import java.util.Objects;
import org.chromium.chrome.browser.tab.Tab;

/* renamed from: fm1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C2579fm1 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final C2921hm1 f9950a;

    public C2579fm1(C2921hm1 hm1) {
        this.f9950a = hm1;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        C2921hm1 hm1 = this.f9950a;
        Tab tab = (Tab) obj;
        Objects.requireNonNull(hm1);
        if (tab != null) {
            int m = tab.m();
            hm1.b(hm1.e(tab, m), false);
            hm1.P = hm1.i(tab, m);
        }
    }
}
