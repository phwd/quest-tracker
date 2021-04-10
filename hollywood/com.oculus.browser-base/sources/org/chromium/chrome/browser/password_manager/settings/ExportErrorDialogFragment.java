package org.chromium.chrome.browser.password_manager.settings;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.oculus.browser.R;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ExportErrorDialogFragment extends OE {
    public DialogInterface.OnClickListener M0;
    public KM N0;

    @Override // defpackage.OE
    public Dialog g1(Bundle bundle) {
        View inflate = u().getLayoutInflater().inflate(R.layout.f40420_resource_name_obfuscated_RES_2131624351, (ViewGroup) null);
        ((TextView) inflate.findViewById(R.id.passwords_error_main_description)).setText(this.N0.b);
        TextView textView = (TextView) inflate.findViewById(R.id.passwords_error_detailed_description);
        String str = this.N0.c;
        if (str != null) {
            textView.setText(str);
        } else {
            textView.setVisibility(8);
        }
        C2290e4 e4Var = new C2290e4(u(), R.style.f72710_resource_name_obfuscated_RES_2132017844);
        C1598a4 a4Var = e4Var.f9828a;
        a4Var.r = inflate;
        a4Var.q = 0;
        e4Var.g(R.string.f58000_resource_name_obfuscated_RES_2131953117);
        e4Var.e(this.N0.f8361a, this.M0);
        e4Var.d(R.string.f49160_resource_name_obfuscated_RES_2131952233, this.M0);
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
