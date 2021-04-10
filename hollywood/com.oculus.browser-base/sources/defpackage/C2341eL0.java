package defpackage;

import J.N;
import android.content.Intent;
import android.text.TextUtils;
import org.chromium.base.ContextUtils;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.ui.base.WindowAndroid;

/* renamed from: eL0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2341eL0 extends WK implements Qr1 {
    public Tab F;
    public C2171dL0 G;

    public C2341eL0(Tab tab) {
        this.F = tab;
        this.G = new C2171dL0();
    }

    public static C2171dL0 V(Tab tab) {
        Rr1 M = tab.M();
        C2341eL0 el0 = (C2341eL0) M.c(C2341eL0.class);
        if (el0 == null) {
            el0 = new C2341eL0(tab);
            M.e(C2341eL0.class, el0);
            tab.A(el0);
        }
        return el0.G;
    }

    public static C2171dL0 W(Tab tab, C2171dL0 dl0) {
        Rr1 M = tab.M();
        C2341eL0 el0 = (C2341eL0) M.c(C2341eL0.class);
        if (dl0 != null) {
            M.e(C2341eL0.class, new C2341eL0(tab, dl0));
        } else {
            M.d(C2341eL0.class);
        }
        if (el0 == null) {
            return null;
        }
        return el0.G;
    }

    public static void X(Tab tab, Intent intent) {
        C2171dL0 V = V(tab);
        boolean e = Z60.e(intent);
        boolean z = false;
        boolean d = U20.d(intent, "android.support.customtabs.extra.SEND_TO_EXTERNAL_HANDLER", false);
        boolean M09VlOh_ = N.M09VlOh_("CCTExternalLinkHandling");
        V.a();
        if (intent != null && "android.intent.action.VIEW".equals(intent.getAction())) {
            V.d = e;
            if (!e || !d || !M09VlOh_) {
                String packageName = ContextUtils.getApplicationContext().getPackageName();
                if (TextUtils.equals(packageName, intent.getPackage()) || TextUtils.equals(packageName, U20.n(intent, "com.android.browser.application_id"))) {
                    z = true;
                }
                V.c = z;
            }
            Intent component = new Intent(intent).setComponent(null);
            V.f9773a = component;
            Intent selector = component.getSelector();
            if (selector != null) {
                selector.setComponent(null);
            }
        }
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void A(Tab tab, int i) {
        this.G.a();
    }

    @Override // defpackage.Qr1
    public void destroy() {
        this.F.I(this);
        this.F = null;
    }

    @Override // defpackage.AbstractC1404Xa1
    public void i(Tab tab, WindowAndroid windowAndroid) {
    }

    public C2341eL0(Tab tab, C2171dL0 dl0) {
        this.F = tab;
        this.G = dl0;
    }
}
