package org.chromium.chrome.browser.password_check;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import com.oculus.browser.R;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class PasswordCheckFragmentView extends AbstractC2324eF0 {
    public int G0;

    @Override // defpackage.AbstractComponentCallbacksC3550lS
    public void B0() {
        this.i0 = true;
        throw null;
    }

    @Override // defpackage.AbstractComponentCallbacksC3550lS, defpackage.AbstractC2324eF0
    public void C0(Bundle bundle) {
        super.C0(bundle);
        bundle.putInt("password-check-referrer", this.G0);
    }

    @Override // defpackage.AbstractComponentCallbacksC3550lS, defpackage.AbstractC2324eF0
    public void D0() {
        super.D0();
        throw null;
    }

    @Override // defpackage.AbstractC2324eF0
    public void g1(Bundle bundle, String str) {
        int i;
        u().setTitle(R.string.f58110_resource_name_obfuscated_RES_2131953128);
        C4375qF0 qf0 = this.z0;
        j1(qf0.a(qf0.f11127a));
        if (bundle == null || !bundle.containsKey("password-check-referrer")) {
            i = this.K.getInt("password-check-referrer");
        } else {
            i = bundle.getInt("password-check-referrer");
        }
        this.G0 = i;
        V0(true);
    }

    @Override // defpackage.AbstractComponentCallbacksC3550lS
    public void k0(Menu menu, MenuInflater menuInflater) {
        menu.clear();
        menu.add(0, R.id.menu_id_targeted_help, 0, R.string.f54570_resource_name_obfuscated_RES_2131952774).setIcon(Fs1.a(I(), R.drawable.f30670_resource_name_obfuscated_RES_2131231107, u().getTheme()));
    }

    @Override // defpackage.AbstractComponentCallbacksC3550lS
    public void m0() {
        this.i0 = true;
        throw null;
    }

    @Override // defpackage.AbstractComponentCallbacksC3550lS
    public boolean u0(MenuItem menuItem) {
        throw null;
    }
}
