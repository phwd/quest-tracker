package defpackage;

import java.util.Objects;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tab.TabImpl;
import org.chromium.content_public.browser.NavigationController;
import org.chromium.content_public.browser.NavigationHandle;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.base.WindowAndroid;

/* renamed from: Z20  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class Z20 extends WK {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ C1596a30 f9315a;

    public Z20(C1596a30 a30) {
        this.f9315a = a30;
    }

    @Override // defpackage.AbstractC1404Xa1
    public void i(Tab tab, WindowAndroid windowAndroid) {
        if (windowAndroid != null) {
            C1596a30 a30 = this.f9315a;
            C1947c30 c30 = a30.c;
            TabImpl tabImpl = a30.f9405a;
            c30.e = tabImpl.e0.c(tabImpl);
        }
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void n(Tab tab) {
        this.f9315a.c.a(tab.l());
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void r(Tab tab) {
        this.f9315a.c.a(null);
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void u(Tab tab, NavigationHandle navigationHandle) {
        C1947c30 c30 = this.f9315a.c;
        Objects.requireNonNull(c30);
        if (navigationHandle.f && navigationHandle.f10940a) {
            WebContents webContents = ((C1596a30) c30.b).f9405a.L;
            if (c30.f && webContents != null) {
                webContents.f().t();
            } else if (c30.g && webContents != null) {
                NavigationController f = webContents.f();
                int i = ((C1596a30) c30.b).a().h;
                int b = c30.b();
                while (true) {
                    b--;
                    if (b <= i) {
                        break;
                    }
                    f.i(b);
                }
            }
            c30.f = false;
            c30.g = false;
        }
    }
}
