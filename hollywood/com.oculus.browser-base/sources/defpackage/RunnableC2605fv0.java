package defpackage;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import org.chromium.components.browser_ui.site_settings.SingleCategorySettings;

/* renamed from: fv0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC2605fv0 implements Runnable {
    public final AbstractC1922bv0 F;

    public RunnableC2605fv0(AbstractC1922bv0 bv0) {
        this.F = bv0;
    }

    public void run() {
        Context context = ((C4985ts) this.F).k;
        Bundle bundle = new Bundle();
        bundle.putString("category", QX0.p(8));
        bundle.putString("title", context.getResources().getString(AbstractC1154Sy.g(QX0.c(8))));
        String name = SingleCategorySettings.class.getName();
        Intent l = AbstractC2531fV.l(context, XS0.class);
        if (!(context instanceof Activity)) {
            l.addFlags(268435456);
            l.addFlags(67108864);
        }
        l.putExtra("show_fragment", name);
        l.putExtra("show_fragment_args", bundle);
        RX0.a(context, l);
    }
}
