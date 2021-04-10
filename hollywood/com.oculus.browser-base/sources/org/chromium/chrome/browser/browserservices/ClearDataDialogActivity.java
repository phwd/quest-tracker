package org.chromium.chrome.browser.browserservices;

import android.app.AlertDialog;
import android.os.Bundle;
import com.oculus.browser.R;
import java.util.ArrayList;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ClearDataDialogActivity extends I7 {
    public final void f0() {
        i0(true);
        ArrayList m = U20.m(getIntent(), "org.chromium.chrome.extra.origins");
        ArrayList m2 = U20.m(getIntent(), "org.chromium.chrome.extra.domains");
        if (m != null && !m.isEmpty() && m2 != null && !m2.isEmpty()) {
            AbstractC3952no1.b(this, m, m2);
        }
        finish();
    }

    public final /* synthetic */ void g0() {
        i0(false);
        finish();
    }

    public final /* synthetic */ void h0() {
        i0(false);
        finish();
    }

    public final void i0(boolean z) {
        boolean d = U20.d(getIntent(), "org.chromium.chrome.extra.app_uninstalled", false);
        C1207Tu f = AbstractApplicationC3785mq.g().f();
        if (z || f.b.h) {
            f.b.i(new RunnableC1146Su(f, z, d));
            return;
        }
        String str = d ? "twa_dialog_number_of_dismissals_on_uninstall" : "twa_dialog_number_of_dismissals_on_clear_data";
        P21 f0 = P21.f0();
        try {
            ((PU0) f.f8993a.get()).n(str, ((PU0) f.f8993a.get()).f(str, 0) + 1);
            f0.close();
            return;
        } catch (Throwable th) {
            AbstractC0754Mh1.f8495a.a(th, th);
        }
        throw th;
    }

    @Override // defpackage.AbstractActivityC3892nS, defpackage.I7, defpackage.AbstractActivityC3119iw
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        new AlertDialog.Builder(this, 16974546).setTitle(getString(R.string.f63840_resource_name_obfuscated_RES_2131953701, new Object[]{U20.n(getIntent(), "org.chromium.chrome.extra.app_name")})).setMessage(R.string.f63830_resource_name_obfuscated_RES_2131953700).setPositiveButton(R.string.f61350_resource_name_obfuscated_RES_2131953452, new DialogInterface$OnClickListenerC0963Pu(this)).setNegativeButton(R.string.f63820_resource_name_obfuscated_RES_2131953699, new DialogInterface$OnClickListenerC1024Qu(this)).setOnCancelListener(new DialogInterface$OnCancelListenerC1085Ru(this)).create().show();
    }
}
