package defpackage;

import org.chromium.chrome.browser.tab.Tab;
import org.chromium.content_public.browser.NavigationHandle;
import org.chromium.ui.base.WindowAndroid;

/* renamed from: g10  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2624g10 extends WK {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ C3649m10 f9971a;

    public C2624g10(C3649m10 m10) {
        this.f9971a = m10;
    }

    @Override // defpackage.AbstractC1404Xa1
    public void i(Tab tab, WindowAndroid windowAndroid) {
        if (windowAndroid != null) {
            this.f9971a.k(C3649m10.j(tab));
            C3649m10.c(this.f9971a);
            return;
        }
        this.f9971a.e();
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void n(Tab tab) {
        C3649m10.c(this.f9971a);
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void u(Tab tab, NavigationHandle navigationHandle) {
        if (navigationHandle.f && navigationHandle.f10940a) {
            this.f9971a.l(false);
        }
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void w(Tab tab, NavigationHandle navigationHandle) {
        B10 b10 = this.f9971a.R;
        if (b10 != null) {
            b10.setTranslationY(0.0f);
        }
    }
}
