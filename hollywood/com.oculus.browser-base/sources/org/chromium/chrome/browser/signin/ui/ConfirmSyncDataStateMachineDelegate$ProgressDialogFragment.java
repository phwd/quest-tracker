package org.chromium.chrome.browser.signin.ui;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import com.oculus.browser.R;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ConfirmSyncDataStateMachineDelegate$ProgressDialogFragment extends OE {
    public static final /* synthetic */ int M0 = 0;
    public C6020zx N0;

    @Override // defpackage.OE
    public Dialog g1(Bundle bundle) {
        if (bundle != null) {
            f1(false, false);
        }
        C2290e4 e4Var = new C2290e4(u(), R.style.f72700_resource_name_obfuscated_RES_2132017843);
        C1598a4 a4Var = e4Var.f9828a;
        a4Var.r = null;
        a4Var.q = R.layout.f41490_resource_name_obfuscated_RES_2131624458;
        e4Var.d(R.string.f48470_resource_name_obfuscated_RES_2131952164, new DialogInterface$OnClickListenerC0238Dx());
        return e4Var.a();
    }

    @Override // defpackage.OE
    public void onCancel(DialogInterface dialogInterface) {
        this.N0.f11782a.a(false);
    }
}
