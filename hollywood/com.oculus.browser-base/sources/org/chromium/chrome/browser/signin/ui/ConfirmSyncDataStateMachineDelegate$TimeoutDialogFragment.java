package org.chromium.chrome.browser.signin.ui;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import com.oculus.browser.R;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ConfirmSyncDataStateMachineDelegate$TimeoutDialogFragment extends OE {
    public static final /* synthetic */ int M0 = 0;
    public C0116Bx N0;

    @Override // defpackage.OE
    public Dialog g1(Bundle bundle) {
        if (bundle != null) {
            f1(false, false);
        }
        C2290e4 e4Var = new C2290e4(u(), R.style.f72700_resource_name_obfuscated_RES_2132017843);
        e4Var.g(R.string.f61920_resource_name_obfuscated_RES_2131953509);
        e4Var.c(R.string.f61910_resource_name_obfuscated_RES_2131953508);
        e4Var.d(R.string.f48470_resource_name_obfuscated_RES_2131952164, new DialogInterface$OnClickListenerC0299Ex());
        e4Var.e(R.string.f63810_resource_name_obfuscated_RES_2131953698, new DialogInterface$OnClickListenerC0360Fx(this));
        return e4Var.a();
    }

    public final void l1() {
        C0116Bx bx = this.N0;
        bx.f7775a.d();
        C0177Cx cx = bx.f7775a;
        if (cx.i == null) {
            cx.i = new RunnableC0055Ax(cx);
        }
        cx.f.postDelayed(cx.i, 30000);
        C0177Cx cx2 = bx.f7775a;
        C0421Gx gx = cx2.e;
        C6020zx zxVar = new C6020zx(cx2);
        gx.a();
        ConfirmSyncDataStateMachineDelegate$ProgressDialogFragment confirmSyncDataStateMachineDelegate$ProgressDialogFragment = new ConfirmSyncDataStateMachineDelegate$ProgressDialogFragment();
        confirmSyncDataStateMachineDelegate$ProgressDialogFragment.N0 = zxVar;
        gx.c(confirmSyncDataStateMachineDelegate$ProgressDialogFragment, "ConfirmSyncTimeoutDialog");
    }

    @Override // defpackage.OE
    public void onCancel(DialogInterface dialogInterface) {
        this.N0.f7775a.a(false);
    }
}
