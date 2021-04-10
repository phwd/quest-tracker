package org.chromium.chrome.browser.signin.ui;

import J.N;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import com.oculus.browser.R;
import org.chromium.chrome.browser.profiles.Profile;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class SignOutDialogFragment extends OE implements DialogInterface.OnClickListener {
    public CheckBox M0;
    public int N0 = 0;

    public static SignOutDialogFragment l1(int i) {
        SignOutDialogFragment signOutDialogFragment = new SignOutDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("ShowGAIAServiceType", i);
        signOutDialogFragment.U0(bundle);
        return signOutDialogFragment;
    }

    @Override // defpackage.OE
    public Dialog g1(Bundle bundle) {
        Bundle bundle2 = this.K;
        if (bundle2 != null) {
            this.N0 = bundle2.getInt("ShowGAIAServiceType", this.N0);
        }
        String G = C5949zZ.a().d(Profile.b()).G();
        if (G != null) {
            C2290e4 e4Var = new C2290e4(u(), R.style.f72700_resource_name_obfuscated_RES_2132017843);
            e4Var.g(R.string.f62290_resource_name_obfuscated_RES_2131953546);
            e4Var.e(R.string.f50170_resource_name_obfuscated_RES_2131952334, this);
            e4Var.d(R.string.f48470_resource_name_obfuscated_RES_2131952164, this);
            e4Var.f9828a.f = P(R.string.f62280_resource_name_obfuscated_RES_2131953545, G);
            return e4Var.a();
        }
        C2290e4 e4Var2 = new C2290e4(u(), R.style.f72700_resource_name_obfuscated_RES_2132017843);
        View inflate = LayoutInflater.from(e4Var2.f9828a.f9407a).inflate(R.layout.f41510_resource_name_obfuscated_RES_2131624460, (ViewGroup) null);
        this.M0 = (CheckBox) inflate.findViewById(R.id.remove_local_data);
        ((TextView) inflate.findViewById(16908299)).setText(R.string.f62300_resource_name_obfuscated_RES_2131953547);
        e4Var2.g(R.string.f62310_resource_name_obfuscated_RES_2131953548);
        C1598a4 a4Var = e4Var2.f9828a;
        a4Var.r = inflate;
        a4Var.q = 0;
        e4Var2.e(R.string.f50170_resource_name_obfuscated_RES_2131952334, this);
        e4Var2.d(R.string.f48470_resource_name_obfuscated_RES_2131952164, this);
        return e4Var2.a();
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        if (i == -1) {
            N.MX17n_KK(6, this.N0);
            if (C5949zZ.a().d(Profile.b()).G() == null) {
                AbstractC3100ip1.f10165a.a("Signin.UserRequestedWipeDataOnSignout", this.M0.isChecked());
            }
            AbstractC5942zV0 zv0 = (AbstractC5942zV0) R();
            CheckBox checkBox = this.M0;
            zv0.c(checkBox != null && checkBox.isChecked());
        }
    }

    @Override // defpackage.OE
    public void onDismiss(DialogInterface dialogInterface) {
        if (!this.J0) {
            f1(true, true);
        }
        N.MX17n_KK(7, this.N0);
    }
}
