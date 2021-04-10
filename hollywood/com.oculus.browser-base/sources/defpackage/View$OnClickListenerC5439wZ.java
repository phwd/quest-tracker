package defpackage;

import J.N;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.settings.MainSettings;
import org.chromium.chrome.browser.sync.settings.SyncAndServicesSettings;

/* renamed from: wZ  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class View$OnClickListenerC5439wZ implements View.OnClickListener {
    public final C5609xZ F;

    public View$OnClickListenerC5439wZ(C5609xZ xZVar) {
        this.F = xZVar;
    }

    public void onClick(View view) {
        Class cls;
        C5609xZ xZVar = this.F;
        Um1.a((Profile) ((C1078Rq0) xZVar.H).H).notifyEvent("identity_disc_used");
        AbstractC3535lK0.a("MobileToolbarIdentityDiscTap");
        Context context = xZVar.F;
        if (N.M09VlOh_("MobileIdentityConsistency")) {
            cls = MainSettings.class;
        } else {
            cls = SyncAndServicesSettings.class;
        }
        String name = cls.getName();
        Intent l = AbstractC2531fV.l(context, XS0.class);
        if (!(context instanceof Activity)) {
            l.addFlags(268435456);
            l.addFlags(67108864);
        }
        l.putExtra("show_fragment", name);
        U20.q(context, l);
    }
}
