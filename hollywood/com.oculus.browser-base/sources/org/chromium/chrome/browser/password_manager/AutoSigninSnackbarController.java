package org.chromium.chrome.browser.password_manager;

import android.content.Context;
import android.graphics.drawable.Drawable;
import com.oculus.browser.R;
import org.chromium.chrome.browser.app.ChromeActivity;
import org.chromium.chrome.browser.tab.Tab;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class AutoSigninSnackbarController extends AbstractC4758sY0 {

    /* renamed from: a  reason: collision with root package name */
    public final View$OnClickListenerC5098uY0 f10734a;
    public final AbstractC1404Xa1 b;
    public final Tab c;

    public AutoSigninSnackbarController(View$OnClickListenerC5098uY0 uy0, Tab tab) {
        this.c = tab;
        this.f10734a = uy0;
        C3914nc ncVar = new C3914nc(this);
        this.b = ncVar;
        tab.A(ncVar);
    }

    public static void showSnackbar(Tab tab, String str) {
        ChromeActivity chromeActivity = (ChromeActivity) AbstractC5112ud1.b(tab);
        if (chromeActivity != null) {
            View$OnClickListenerC5098uY0 U = chromeActivity.U();
            C4076oY0 c2 = C4076oY0.c(str, new AutoSigninSnackbarController(U, tab), 1, 4);
            Context context = (Context) tab.i().s0().get();
            int color = context.getResources().getColor(R.color.f11100_resource_name_obfuscated_RES_2131099800);
            Drawable a2 = AbstractC5544x8.a(context, R.drawable.f33530_resource_name_obfuscated_RES_2131231393);
            c2.i = false;
            c2.g = color;
            c2.k = a2;
            c2.h = R.style.f72000_resource_name_obfuscated_RES_2132017773;
            U.l(c2);
        }
    }

    @Override // defpackage.AbstractC4758sY0
    public void c(Object obj) {
    }

    @Override // defpackage.AbstractC4758sY0
    public void d(Object obj) {
        this.c.I(this.b);
    }

    public void e() {
        C5948zY0 zy0 = this.f10734a.G;
        if (zy0 != null && zy0.b.isShown()) {
            this.f10734a.j(this);
        }
    }
}
