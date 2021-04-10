package org.chromium.chrome.browser.signin.ui;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import com.oculus.browser.R;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ConfirmManagedSyncDataDialog extends OE {
    public AbstractC5680xx M0;

    @Override // defpackage.OE
    public Dialog g1(Bundle bundle) {
        if (this.M0 == null) {
            f1(false, false);
        }
        String P = P(R.string.f61890_resource_name_obfuscated_RES_2131953506, this.K.getString("domain"));
        C2290e4 e4Var = new C2290e4(u(), R.style.f72700_resource_name_obfuscated_RES_2132017843);
        e4Var.g(R.string.f61880_resource_name_obfuscated_RES_2131953505);
        e4Var.f9828a.f = P;
        e4Var.e(R.string.f58990_resource_name_obfuscated_RES_2131953216, new DialogInterface$OnClickListenerC5340vx(this));
        e4Var.d(R.string.f48470_resource_name_obfuscated_RES_2131952164, new DialogInterface$OnClickListenerC5510wx(this));
        return e4Var.a();
    }

    public final void l1() {
        ((C0177Cx) this.M0).c();
    }

    public final void m1() {
        ((C0177Cx) this.M0).a(false);
    }

    @Override // defpackage.OE
    public void onCancel(DialogInterface dialogInterface) {
        ((C0177Cx) this.M0).a(false);
    }
}
