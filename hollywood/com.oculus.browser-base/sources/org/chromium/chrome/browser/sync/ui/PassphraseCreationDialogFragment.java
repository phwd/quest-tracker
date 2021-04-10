package org.chromium.chrome.browser.sync.ui;

import J.N;
import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import com.oculus.browser.R;
import org.chromium.chrome.browser.sync.ProfileSyncService;
import org.chromium.chrome.browser.sync.settings.ManageSyncSettings;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class PassphraseCreationDialogFragment extends OE {
    public EditText M0;
    public EditText N0;

    public static void l1(PassphraseCreationDialogFragment passphraseCreationDialogFragment) {
        String obj = passphraseCreationDialogFragment.M0.getText().toString();
        if (!obj.equals(passphraseCreationDialogFragment.N0.getText().toString())) {
            passphraseCreationDialogFragment.M0.setError(null);
            passphraseCreationDialogFragment.N0.setError(passphraseCreationDialogFragment.O(R.string.f62950_resource_name_obfuscated_RES_2131953612));
            passphraseCreationDialogFragment.N0.requestFocus();
        } else if (obj.isEmpty()) {
            passphraseCreationDialogFragment.N0.setError(null);
            passphraseCreationDialogFragment.M0.setError(passphraseCreationDialogFragment.O(R.string.f62860_resource_name_obfuscated_RES_2131953603));
            passphraseCreationDialogFragment.M0.requestFocus();
        } else {
            ManageSyncSettings manageSyncSettings = (ManageSyncSettings) ((AbstractC2611fx0) passphraseCreationDialogFragment.R());
            if (manageSyncSettings.H0.h()) {
                ProfileSyncService profileSyncService = manageSyncSettings.H0;
                N.M_l3G2yX(profileSyncService.e, profileSyncService, obj);
                manageSyncSettings.q1();
            }
            passphraseCreationDialogFragment.I0.dismiss();
        }
    }

    @Override // defpackage.OE, defpackage.AbstractComponentCallbacksC3550lS
    public void D0() {
        super.D0();
        DialogC2461f4 f4Var = (DialogC2461f4) this.I0;
        if (f4Var != null) {
            f4Var.c(-1).setOnClickListener(new View$OnClickListenerC2440ex0(this));
        }
    }

    @Override // defpackage.OE
    public Dialog g1(Bundle bundle) {
        new Dialog(P0(), this.D0);
        View inflate = u().getLayoutInflater().inflate(R.layout.f41670_resource_name_obfuscated_RES_2131624476, (ViewGroup) null);
        this.M0 = (EditText) inflate.findViewById(R.id.passphrase);
        EditText editText = (EditText) inflate.findViewById(R.id.confirm_passphrase);
        this.N0 = editText;
        editText.setOnEditorActionListener(new C2099cx0(this));
        TextView textView = (TextView) inflate.findViewById(R.id.custom_passphrase_instructions);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        Activity u = u();
        textView.setText(FY0.a(u.getString(R.string.f62630_resource_name_obfuscated_RES_2131953580), new EY0("<learnmore>", "</learnmore>", new C2269dx0(this, u))));
        C2290e4 e4Var = new C2290e4(u(), R.style.f72700_resource_name_obfuscated_RES_2132017843);
        C1598a4 a4Var = e4Var.f9828a;
        a4Var.r = inflate;
        a4Var.q = 0;
        e4Var.g(R.string.f62910_resource_name_obfuscated_RES_2131953608);
        e4Var.e(R.string.f60880_resource_name_obfuscated_RES_2131953405, null);
        e4Var.d(R.string.f48470_resource_name_obfuscated_RES_2131952164, null);
        DialogC2461f4 a2 = e4Var.a();
        ((LayoutInflater$Factory2C3156j8) a2.a()).c0 = false;
        return a2;
    }
}
