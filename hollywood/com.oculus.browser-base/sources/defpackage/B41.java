package defpackage;

import org.chromium.chrome.browser.tab.Tab;
import org.chromium.ui.base.WindowAndroid;

/* renamed from: B41  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class B41 extends WK {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ C41 f7715a;

    public B41(C41 c41) {
        this.f7715a = c41;
    }

    @Override // defpackage.AbstractC1404Xa1
    public void i(Tab tab, WindowAndroid windowAndroid) {
        if (windowAndroid == null) {
            C41 c41 = this.f7715a;
            if (c41.H != null) {
                c41.j();
                this.f7715a.k();
                C41 c412 = this.f7715a;
                K41 k41 = c412.H;
                k41.G = null;
                k41.H = null;
                c412.H = null;
            }
        }
    }
}
