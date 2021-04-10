package org.chromium.chrome.browser.signin.ui;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.oculus.browser.R;
import java.util.Arrays;
import java.util.List;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.components.browser_ui.widget.RadioButtonWithDescription;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ConfirmImportSyncDataDialog extends OE implements DialogInterface.OnClickListener {
    public static final /* synthetic */ int M0 = 0;
    public RadioButtonWithDescription N0;
    public RadioButtonWithDescription O0;
    public AbstractC4490qx P0;

    @Override // defpackage.OE
    public Dialog g1(Bundle bundle) {
        if (this.P0 == null) {
            f1(false, false);
        }
        String string = this.K.getString("lastAccountName");
        String string2 = this.K.getString("newAccountName");
        View inflate = u().getLayoutInflater().inflate(R.layout.f37380_resource_name_obfuscated_RES_2131624047, (ViewGroup) null);
        ((TextView) inflate.findViewById(R.id.sync_import_data_prompt)).setText(u().getString(R.string.f62760_resource_name_obfuscated_RES_2131953593, new Object[]{string}));
        this.N0 = (RadioButtonWithDescription) inflate.findViewById(R.id.sync_confirm_import_choice);
        this.O0 = (RadioButtonWithDescription) inflate.findViewById(R.id.sync_keep_separate_choice);
        this.N0.h(u().getString(R.string.f62780_resource_name_obfuscated_RES_2131953595, new Object[]{string2}));
        this.O0.h(u().getString(R.string.f62820_resource_name_obfuscated_RES_2131953599));
        List asList = Arrays.asList(this.N0, this.O0);
        this.N0.f10823J = asList;
        this.O0.f10823J = asList;
        boolean z = C5949zZ.a().d(Profile.b()).G() != null;
        C2290e4 e4Var = new C2290e4(u(), R.style.f72700_resource_name_obfuscated_RES_2132017843);
        e4Var.e(R.string.f50170_resource_name_obfuscated_RES_2131952334, this);
        e4Var.d(R.string.f48470_resource_name_obfuscated_RES_2131952164, this);
        C1598a4 a4Var = e4Var.f9828a;
        a4Var.r = inflate;
        a4Var.q = 0;
        DialogC2461f4 a2 = e4Var.a();
        a2.setOnShowListener(new DialogInterface$OnShowListenerC3806mx(a2, z));
        if (z) {
            this.O0.f(true);
            this.N0.setOnClickListener(new View$OnClickListenerC3977nx(this));
        } else {
            this.N0.I = new C4148ox(a2);
            this.O0.I = new C4319px(a2);
        }
        return a2;
    }

    public final /* synthetic */ void l1() {
        AbstractC1865bc0.e(u());
    }

    @Override // defpackage.OE
    public void onCancel(DialogInterface dialogInterface) {
        ((C0177Cx) this.P0).a(false);
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        if (i == -1) {
            AbstractC3535lK0.a(this.O0.e() ? "Signin_ImportDataPrompt_DontImport" : "Signin_ImportDataPrompt_ImportData");
            C0177Cx cx = (C0177Cx) this.P0;
            cx.g = this.O0.e();
            cx.c();
            return;
        }
        AbstractC3535lK0.a("Signin_ImportDataPrompt_Cancel");
        ((C0177Cx) this.P0).a(false);
    }
}
