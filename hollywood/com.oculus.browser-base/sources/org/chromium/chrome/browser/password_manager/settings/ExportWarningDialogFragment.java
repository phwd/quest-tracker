package org.chromium.chrome.browser.password_manager.settings;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import com.oculus.browser.R;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ExportWarningDialogFragment extends OE {
    public PM M0;

    @Override // defpackage.OE
    public Dialog g1(Bundle bundle) {
        C2290e4 e4Var = new C2290e4(u(), R.style.f72710_resource_name_obfuscated_RES_2132017844);
        e4Var.e(R.string.f57980_resource_name_obfuscated_RES_2131953115, this.M0);
        e4Var.d(R.string.f48470_resource_name_obfuscated_RES_2131952164, this.M0);
        e4Var.f9828a.f = u().getResources().getString(R.string.f61410_resource_name_obfuscated_RES_2131953458);
        return e4Var.a();
    }

    @Override // defpackage.OE, defpackage.AbstractComponentCallbacksC3550lS
    public void h0(Bundle bundle) {
        super.h0(bundle);
        if (bundle != null) {
            f1(false, false);
        }
    }

    @Override // defpackage.OE
    public void onDismiss(DialogInterface dialogInterface) {
        if (!this.J0) {
            f1(true, true);
        }
        PM pm = this.M0;
        if (pm != null) {
            SM sm = pm.F;
            if (sm.f8891a != 2) {
                sm.f8891a = 0;
            }
            sm.f = null;
            if (sm.e != null) {
                sm.c();
            }
        }
    }
}
