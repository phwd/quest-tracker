package defpackage;

import android.view.View;
import java.util.List;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.content_public.browser.LoadUrlParams;

/* renamed from: O61  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class O61 implements View.OnClickListener {
    public final U61 F;

    public O61(U61 u61) {
        this.F = u61;
    }

    public void onClick(View view) {
        U61 u61 = this.F;
        Tab o = ((AbstractC0246Ea1) u61.c).o(u61.q);
        u61.h(false);
        if (o == null) {
            u61.f.S(((AbstractC0246Ea1) u61.c).r()).e();
            return;
        }
        List f = u61.f(o.getId());
        u61.f.S(o.a()).b(new LoadUrlParams("chrome-native://newtab/", 0), 2, (Tab) f.get(f.size() - 1));
        AbstractC3535lK0.a("MobileNewTabOpened." + u61.m);
    }
}
