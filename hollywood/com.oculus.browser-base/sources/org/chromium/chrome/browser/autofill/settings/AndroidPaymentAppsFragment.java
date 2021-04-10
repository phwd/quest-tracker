package org.chromium.chrome.browser.autofill.settings;

import J.N;
import android.os.Bundle;
import android.view.View;
import com.oculus.browser.R;
import org.chromium.base.ThreadUtils;
import org.chromium.base.task.PostTask;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class AndroidPaymentAppsFragment extends AbstractC2324eF0 {
    public static final /* synthetic */ int G0 = 0;

    @Override // defpackage.AbstractComponentCallbacksC3550lS
    public void B0() {
        this.i0 = true;
        this.z0.g.e0();
        this.z0.g.w0 = true;
        C2638g6 g6Var = new C2638g6(this);
        Object obj = ThreadUtils.f10596a;
        if (!N.M1X7xdZV("ServiceWorkerPaymentApps")) {
            PostTask.b(Zo1.f9374a, new MS0(g6Var), 0);
        } else {
            N.MFeChwbo(g6Var);
        }
    }

    @Override // defpackage.AbstractComponentCallbacksC3550lS, defpackage.AbstractC2324eF0
    public void F0(View view, Bundle bundle) {
        super.F0(view, bundle);
        this.A0.s0(null);
    }

    @Override // defpackage.AbstractC2324eF0
    public void g1(Bundle bundle, String str) {
        u().setTitle(R.string.f58190_resource_name_obfuscated_RES_2131953136);
        C4375qF0 qf0 = this.z0;
        j1(qf0.a(qf0.f11127a));
    }
}
