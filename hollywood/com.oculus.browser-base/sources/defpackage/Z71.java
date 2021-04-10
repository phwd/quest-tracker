package defpackage;

import org.chromium.chrome.browser.tab.Tab;
import org.chromium.ui.base.WindowAndroid;
import org.chromium.url.GURL;

/* renamed from: Z71  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class Z71 extends AbstractC1099Sa1 {
    public final /* synthetic */ C2475f81 I;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public Z71(C2475f81 f81, AbstractC0124Ca1 ca1) {
        super(ca1);
        this.I = f81;
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void K(Tab tab, GURL gurl) {
        if (((AbstractC0246Ea1) this.I.e).o(tab.getId()) != null) {
            int size = this.I.f(tab.getId()).size();
            if (!this.I.x || size == 1) {
                size = 0;
            }
            AbstractC3364kK0.d("TabStrip.TabCountOnPageLoad", size);
        }
    }

    @Override // defpackage.AbstractC1404Xa1
    public void i(Tab tab, WindowAndroid windowAndroid) {
        if (windowAndroid == null) {
            destroy();
            this.I.v = null;
        }
    }
}
