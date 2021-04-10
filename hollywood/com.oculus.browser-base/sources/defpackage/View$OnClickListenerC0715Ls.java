package defpackage;

import android.view.View;
import java.util.Objects;
import org.chromium.chrome.browser.tab.Tab;

/* renamed from: Ls  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class View$OnClickListenerC0715Ls implements View.OnClickListener {
    public final C1569Zs F;

    public View$OnClickListenerC0715Ls(C1569Zs zs) {
        this.F = zs;
    }

    public void onClick(View view) {
        C1569Zs zs = this.F;
        Objects.requireNonNull(zs);
        AbstractC3535lK0.a("SharingHubAndroid.ScreenshotSelected");
        C1569Zs.b(zs.i);
        zs.o.notifyEvent("share_screenshot_clicked");
        zs.l = new C2691gP0(zs.f9379a, (Tab) zs.b.get(), zs.k, zs.c, zs.n);
        ((C5638xj) zs.c).j(zs.p);
        ((C5638xj) zs.c).p(zs.d, true, 0);
    }
}
