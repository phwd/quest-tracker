package org.chromium.chrome.browser.password_manager.settings;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import com.oculus.browser.R;
import org.chromium.components.browser_ui.widget.MaterialProgressBar;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ProgressBarDialogFragment extends OE {
    public DialogInterface.OnClickListener M0;

    @Override // defpackage.OE
    public Dialog g1(Bundle bundle) {
        View inflate = u().getLayoutInflater().inflate(R.layout.f40430_resource_name_obfuscated_RES_2131624352, (ViewGroup) null);
        MaterialProgressBar materialProgressBar = (MaterialProgressBar) inflate.findViewById(R.id.passwords_progress_bar);
        if (!materialProgressBar.f10820J) {
            materialProgressBar.f10820J = true;
            materialProgressBar.b();
            materialProgressBar.postInvalidateOnAnimation();
        }
        C2290e4 e4Var = new C2290e4(u(), R.style.f72710_resource_name_obfuscated_RES_2132017844);
        C1598a4 a4Var = e4Var.f9828a;
        a4Var.r = inflate;
        a4Var.q = 0;
        e4Var.d(R.string.f48470_resource_name_obfuscated_RES_2131952164, this.M0);
        e4Var.f9828a.d = u().getResources().getString(R.string.f61420_resource_name_obfuscated_RES_2131953459);
        return e4Var.a();
    }

    @Override // defpackage.OE, defpackage.AbstractComponentCallbacksC3550lS
    public void h0(Bundle bundle) {
        super.h0(bundle);
        if (bundle != null) {
            f1(false, false);
        }
    }
}
