package defpackage;

import android.view.View;
import java.util.List;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.content_public.browser.LoadUrlParams;

/* renamed from: V71  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class V71 implements View.OnClickListener {
    public final C2475f81 F;

    public V71(C2475f81 f81) {
        this.F = f81;
    }

    public void onClick(View view) {
        Tab tab;
        C2475f81 f81 = this.F;
        Tab j = ((AbstractC0246Ea1) f81.e).j();
        if (AbstractC4772sd1.g()) {
            List f = f81.f(j.getId());
            tab = (Tab) f.get(f.size() - 1);
        } else {
            tab = null;
        }
        f81.f.S(j.a()).b(new LoadUrlParams("chrome-native://newtab/", 0), 2, tab);
        AbstractC3535lK0.a("MobileNewTabOpened.TabStrip");
    }
}
