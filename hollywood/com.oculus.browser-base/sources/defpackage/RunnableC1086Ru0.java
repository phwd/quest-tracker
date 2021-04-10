package defpackage;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import org.chromium.components.browser_ui.site_settings.SingleWebsiteSettings;
import org.chromium.components.page_info.PageInfoController;

/* renamed from: Ru0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC1086Ru0 implements Runnable {
    public final PageInfoController F;

    public RunnableC1086Ru0(PageInfoController pageInfoController) {
        this.F = pageInfoController;
    }

    public void run() {
        PageInfoController pageInfoController = this.F;
        pageInfoController.k(9);
        AbstractC1922bv0 bv0 = pageInfoController.I;
        String h = pageInfoController.N.h();
        Context context = ((C4985ts) bv0).k;
        String name = SingleWebsiteSettings.class.getName();
        Bundle k1 = SingleWebsiteSettings.k1(h);
        Intent l = AbstractC2531fV.l(context, XS0.class);
        if (!(context instanceof Activity)) {
            l.addFlags(268435456);
            l.addFlags(67108864);
        }
        l.putExtra("show_fragment", name);
        l.putExtra("show_fragment_args", k1);
        RX0.a(context, l);
    }
}
