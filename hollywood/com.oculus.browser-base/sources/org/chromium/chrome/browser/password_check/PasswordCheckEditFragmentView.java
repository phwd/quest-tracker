package org.chromium.chrome.browser.password_check;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import com.google.android.material.textfield.TextInputLayout;
import com.oculus.browser.R;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class PasswordCheckEditFragmentView extends AbstractC2324eF0 {
    public String G0;
    public CompromisedCredential H0;
    public boolean I0;
    public EditText J0;
    public MenuItem K0;
    public TextInputLayout L0;
    public ImageButton M0;
    public ImageButton N0;

    @Override // defpackage.AbstractComponentCallbacksC3550lS
    public void B0() {
        this.i0 = true;
        if (!AbstractC2852hK0.a(0)) {
            u().finish();
        }
    }

    @Override // defpackage.AbstractComponentCallbacksC3550lS, defpackage.AbstractC2324eF0
    public void C0(Bundle bundle) {
        super.C0(bundle);
        bundle.putParcelable("extra_compromised_credential", this.H0);
        bundle.putString("extra_new_password", this.G0);
        bundle.putBoolean("extra_password_visible", this.I0);
    }

    @Override // defpackage.AbstractComponentCallbacksC3550lS, defpackage.AbstractC2324eF0
    public void F0(View view, Bundle bundle) {
        CompromisedCredential compromisedCredential;
        String str;
        super.F0(view, bundle);
        if (bundle == null || !bundle.containsKey("extra_compromised_credential")) {
            compromisedCredential = (CompromisedCredential) this.K.getParcelable("extra_compromised_credential");
        } else {
            compromisedCredential = (CompromisedCredential) bundle.getParcelable("extra_compromised_credential");
        }
        this.H0 = compromisedCredential;
        if (bundle == null || !bundle.containsKey("extra_new_password")) {
            Bundle bundle2 = this.K;
            if (bundle2 == null || !bundle2.containsKey("extra_new_password")) {
                str = this.H0.getPassword();
            } else {
                str = (String) bundle2.getParcelable("extra_new_password");
            }
        } else {
            str = (String) bundle.getParcelable("extra_new_password");
        }
        this.G0 = str;
        this.I0 = bundle != null && bundle.containsKey("extra_password_visible") && bundle.getBoolean("extra_password_visible");
        ((TextView) view.findViewById(R.id.edit_hint)).setText(P(R.string.f57710_resource_name_obfuscated_RES_2131953088, this.H0.I));
        ((EditText) view.findViewById(R.id.site_edit)).setText(this.H0.I);
        ((EditText) view.findViewById(R.id.username_edit)).setText(this.H0.f10730J);
        this.L0 = (TextInputLayout) view.findViewById(R.id.password_label);
        EditText editText = (EditText) view.findViewById(R.id.password_edit);
        this.J0 = editText;
        editText.setText(this.H0.getPassword());
        this.J0.addTextChangedListener(new C0787Mx0(this));
        k1(TextUtils.isEmpty(this.G0));
        ImageButton imageButton = (ImageButton) view.findViewById(R.id.password_entry_editor_view_password);
        this.M0 = imageButton;
        imageButton.setOnClickListener(new View$OnClickListenerC0666Kx0(this));
        ImageButton imageButton2 = (ImageButton) view.findViewById(R.id.password_entry_editor_mask_password);
        this.N0 = imageButton2;
        imageButton2.setOnClickListener(new View$OnClickListenerC0726Lx0(this));
        if (this.I0) {
            l1();
        } else {
            m1();
        }
    }

    @Override // defpackage.AbstractC2324eF0
    public void g1(Bundle bundle, String str) {
    }

    @Override // defpackage.AbstractComponentCallbacksC3550lS
    public void k0(Menu menu, MenuInflater menuInflater) {
        menu.clear();
        menuInflater.inflate(R.menu.f42450_resource_name_obfuscated_RES_2131689479, menu);
        this.K0 = menu.findItem(R.id.action_save_edited_password);
        k1(this.G0.isEmpty());
    }

    public final void k1(boolean z) {
        MenuItem menuItem = this.K0;
        if (menuItem != null) {
            menuItem.setEnabled(!z);
        }
        this.L0.w(z ? x().getString(R.string.f59020_resource_name_obfuscated_RES_2131953219) : "");
    }

    @Override // defpackage.AbstractComponentCallbacksC3550lS, defpackage.AbstractC2324eF0
    public View l0(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        V0(true);
        u().setTitle(R.string.f57780_resource_name_obfuscated_RES_2131953095);
        return layoutInflater.inflate(R.layout.f40300_resource_name_obfuscated_RES_2131624339, viewGroup, false);
    }

    /* renamed from: n1 */
    public final void m1() {
        u().getWindow().clearFlags(8192);
        this.J0.setInputType(131201);
        this.M0.setVisibility(0);
        this.N0.setVisibility(8);
        this.I0 = false;
    }

    /* renamed from: o1 */
    public final void l1() {
        u().getWindow().setFlags(8192, 8192);
        this.J0.setInputType(131217);
        this.M0.setVisibility(8);
        this.N0.setVisibility(0);
        this.I0 = true;
    }

    @Override // defpackage.AbstractComponentCallbacksC3550lS
    public boolean u0(MenuItem menuItem) {
        if (menuItem.getItemId() != R.id.action_save_edited_password) {
            return false;
        }
        AbstractC3364kK0.g("PasswordManager.BulkCheck.UserActionAndroid", 10, 13);
        CompromisedCredential compromisedCredential = this.H0;
        if (compromisedCredential.Q) {
            AbstractC3364kK0.g("PasswordManager.AutomaticChange.ForSitesWithScripts", 3, 5);
        }
        if (compromisedCredential.R) {
            AbstractC3364kK0.g("PasswordManager.AutomaticChange.AcceptanceWithAutoButton", 3, 5);
            throw null;
        }
        AbstractC3364kK0.g("PasswordManager.AutomaticChange.AcceptanceWithoutAutoButton", 3, 5);
        throw null;
    }
}
