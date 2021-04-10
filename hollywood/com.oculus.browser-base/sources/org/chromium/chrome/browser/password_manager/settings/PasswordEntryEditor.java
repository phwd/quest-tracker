package org.chromium.chrome.browser.password_manager.settings;

import J.N;
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
import com.google.android.material.textfield.TextInputLayout;
import com.oculus.browser.R;
import java.util.Objects;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class PasswordEntryEditor extends AbstractComponentCallbacksC3550lS {
    public EditText A0;
    public TextInputLayout B0;
    public ImageButton C0;
    public Runnable D0;
    public EditText y0;
    public EditText z0;

    @Override // defpackage.AbstractComponentCallbacksC3550lS
    public void B0() {
        this.i0 = true;
        if (!AbstractC2852hK0.a(0)) {
            f1();
        } else {
            Runnable runnable = this.D0;
            if (runnable != null) {
                runnable.run();
            }
        }
        this.D0 = null;
    }

    @Override // defpackage.AbstractComponentCallbacksC3550lS
    public void F0(View view, Bundle bundle) {
        this.y0 = (EditText) view.findViewById(R.id.site_edit);
        this.z0 = (EditText) view.findViewById(R.id.username_edit);
        this.A0 = (EditText) view.findViewById(R.id.password_edit);
        this.B0 = (TextInputLayout) view.findViewById(R.id.password_label);
        this.C0 = (ImageButton) view.findViewById(R.id.password_entry_editor_view_password);
        this.y0.setText(this.K.getString("credentialUrl"));
        this.z0.setText(this.K.getString("credentialName"));
        this.A0.setText(this.K.getString("credentialPassword"));
        f1();
    }

    public final void e1() {
        RunnableC1214Tx0 tx0 = new RunnableC1214Tx0(this);
        if (!AbstractC2852hK0.c(u().getApplicationContext())) {
            C1184Ti1.a(u().getApplicationContext(), R.string.f57720_resource_name_obfuscated_RES_2131953089, 1).b.show();
        } else if (AbstractC2852hK0.a(0)) {
            tx0.run();
        } else {
            this.D0 = tx0;
            AbstractC2852hK0.b(R.string.f54220_resource_name_obfuscated_RES_2131952739, -1, G(), 0);
        }
    }

    /* renamed from: g1 */
    public final void f1() {
        u().getWindow().clearFlags(8192);
        this.A0.setInputType(131201);
        this.C0.setImageResource(R.drawable.f32920_resource_name_obfuscated_RES_2131231332);
        this.C0.setOnClickListener(new View$OnClickListenerC1092Rx0(this));
    }

    @Override // defpackage.AbstractComponentCallbacksC3550lS
    public void k0(Menu menu, MenuInflater menuInflater) {
        menuInflater.inflate(R.menu.f42460_resource_name_obfuscated_RES_2131689480, menu);
    }

    @Override // defpackage.AbstractComponentCallbacksC3550lS
    public View l0(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        V0(true);
        u().setTitle(R.string.f57780_resource_name_obfuscated_RES_2131953095);
        return layoutInflater.inflate(R.layout.f40330_resource_name_obfuscated_RES_2131624342, viewGroup, false);
    }

    @Override // defpackage.AbstractComponentCallbacksC3550lS
    public void m0() {
        this.i0 = true;
        C1031Qx0 qx0 = C1031Qx0.f8798a;
        PasswordEditingBridge passwordEditingBridge = (PasswordEditingBridge) qx0.b;
        Objects.requireNonNull(passwordEditingBridge);
        qx0.b = null;
        N.MgB0XVuk(passwordEditingBridge.f10741a, passwordEditingBridge);
        passwordEditingBridge.f10741a = 0;
    }

    @Override // defpackage.AbstractComponentCallbacksC3550lS
    public boolean u0(MenuItem menuItem) {
        if (menuItem.getItemId() != R.id.action_save_edited_password) {
            return false;
        }
        if (TextUtils.isEmpty(this.A0.getText().toString())) {
            this.B0.w(x().getString(R.string.f59020_resource_name_obfuscated_RES_2131953219));
            return true;
        }
        AbstractC0970Px0 px0 = C1031Qx0.f8798a.b;
        PasswordEditingBridge passwordEditingBridge = (PasswordEditingBridge) px0;
        N.MQ3sPtIJ(passwordEditingBridge.f10741a, passwordEditingBridge, this.z0.getText().toString(), this.A0.getText().toString());
        u().finish();
        return true;
    }
}
