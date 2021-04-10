package defpackage;

import android.app.Activity;
import org.chromium.chrome.browser.printing.TabPrinter;
import org.chromium.chrome.browser.tab.Tab;

/* renamed from: AT0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class AT0 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final GT0 f7671a;

    public AT0(GT0 gt0) {
        this.f7671a = gt0;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        GT0 gt0 = this.f7671a;
        Tab tab = (Tab) obj;
        Activity activity = (Activity) ((Tab) gt0.c.get()).i().s0().get();
        UF0 b = VF0.b();
        if (b != null) {
            VF0 vf0 = (VF0) b;
            if (!vf0.n) {
                TabPrinter tabPrinter = new TabPrinter((Tab) gt0.c.get());
                SF0 sf0 = new SF0(activity);
                if (!vf0.n) {
                    vf0.d(tabPrinter, sf0, vf0.c, vf0.d);
                    vf0.e();
                }
            }
        }
    }
}
