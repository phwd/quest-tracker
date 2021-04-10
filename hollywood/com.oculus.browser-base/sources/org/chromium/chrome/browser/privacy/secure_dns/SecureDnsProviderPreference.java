package org.chromium.chrome.browser.privacy.secure_dns;

import J.N;
import android.content.Context;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import androidx.preference.Preference;
import com.google.android.material.textfield.TextInputLayout;
import com.oculus.browser.R;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.chromium.components.browser_ui.widget.RadioButtonWithDescription;
import org.chromium.components.browser_ui.widget.RadioButtonWithDescriptionLayout;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class SecureDnsProviderPreference extends Preference implements RadioGroup.OnCheckedChangeListener, AdapterView.OnItemSelectedListener, TextWatcher {
    public Spinner A0;
    public TextView B0;
    public EditText C0;
    public TextInputLayout D0;
    public CR0 E0;
    public final Runnable F0 = new RunnableC5934zR0(this);
    public final String t0;
    public final String u0;
    public final String v0;
    public final List w0;
    public RadioButtonWithDescriptionLayout x0;
    public RadioButtonWithDescription y0;
    public RadioButtonWithDescription z0;

    public SecureDnsProviderPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.k0 = R.layout.f41310_resource_name_obfuscated_RES_2131624440;
        this.t0 = context.getString(R.string.f61510_resource_name_obfuscated_RES_2131953468);
        this.u0 = context.getString(R.string.f61440_resource_name_obfuscated_RES_2131953461);
        this.v0 = context.getString(R.string.f61430_resource_name_obfuscated_RES_2131953460);
        List a2 = AbstractC5764yR0.a();
        ArrayList arrayList = new ArrayList(((ArrayList) a2).size() + 1);
        arrayList.add(new C5594xR0(context.getString(R.string.f61400_resource_name_obfuscated_RES_2131953457), "", ""));
        Collections.shuffle(a2);
        arrayList.addAll(a2);
        this.w0 = arrayList;
    }

    public final int a0() {
        for (int i = 1; i < this.A0.getCount(); i++) {
            if (((C5594xR0) this.A0.getItemAtPosition(i)).b.equals(this.E0.b)) {
                return i;
            }
        }
        return 0;
    }

    public void afterTextChanged(Editable editable) {
        CR0 cr0 = this.E0;
        b0(new CR0(cr0.f7809a, editable.toString(), cr0.c));
        this.C0.removeCallbacks(this.F0);
        this.C0.postDelayed(this.F0, 1000);
    }

    public final void b0(CR0 cr0) {
        if (!f(cr0)) {
            c0();
        } else if (!cr0.equals(this.E0)) {
            this.E0 = cr0;
            c0();
        }
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public final void c0() {
        if (this.x0 != null) {
            boolean e = this.z0.e();
            boolean z = this.E0.f7809a;
            if (e != z) {
                this.z0.f(z);
            }
            boolean z2 = true;
            boolean z3 = !this.E0.f7809a;
            if (this.y0.e() != z3) {
                this.y0.f(z3);
            }
            int a0 = a0();
            if (this.A0.getSelectedItemPosition() != a0) {
                this.A0.setSelection(a0);
            }
            if (this.E0.f7809a) {
                this.A0.setVisibility(0);
                if (a0 > 0) {
                    this.B0.setText(Html.fromHtml(this.t0.replace("$1", ((C5594xR0) this.A0.getSelectedItem()).c)));
                    this.B0.setVisibility(0);
                    this.D0.setVisibility(8);
                } else {
                    if (!this.C0.getText().toString().equals(this.E0.b)) {
                        this.C0.setText(this.E0.b);
                        this.C0.removeCallbacks(this.F0);
                        if (this.E0.f7809a) {
                            this.C0.requestFocus();
                            this.C0.postDelayed(this.F0, 1000);
                        }
                    }
                    CR0 cr0 = this.E0;
                    if (cr0.c || "https://".startsWith(cr0.b)) {
                        z2 = false;
                    }
                    this.D0.w(z2 ? this.u0 : null);
                    this.D0.setVisibility(0);
                    this.B0.setVisibility(8);
                }
            } else {
                this.A0.setVisibility(8);
                this.B0.setVisibility(8);
                this.D0.setVisibility(8);
            }
            N.M6OgZ3EY(this.E0.c);
        }
    }

    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        boolean z = i == R.id.secure;
        CR0 cr0 = this.E0;
        if (cr0.f7809a != z) {
            b0(new CR0(z, cr0.b, cr0.c));
        }
    }

    @Override // android.widget.AdapterView.OnItemSelectedListener
    public void onItemSelected(AdapterView adapterView, View view, int i, long j) {
        int a0 = a0();
        if (a0 != i) {
            C5594xR0 xr0 = (C5594xR0) adapterView.getItemAtPosition(i);
            CR0 cr0 = this.E0;
            b0(new CR0(cr0.f7809a, xr0.b, cr0.c));
            N.MHfKmORH(((C5594xR0) adapterView.getItemAtPosition(a0)).b, xr0.b);
        }
    }

    @Override // android.widget.AdapterView.OnItemSelectedListener
    public void onNothingSelected(AdapterView adapterView) {
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    @Override // androidx.preference.Preference
    public void x(C4886tF0 tf0) {
        super.x(tf0);
        RadioButtonWithDescriptionLayout radioButtonWithDescriptionLayout = (RadioButtonWithDescriptionLayout) tf0.x(R.id.mode_group);
        this.x0 = radioButtonWithDescriptionLayout;
        radioButtonWithDescriptionLayout.G = this;
        this.y0 = (RadioButtonWithDescription) tf0.x(R.id.automatic);
        this.z0 = (RadioButtonWithDescription) tf0.x(R.id.secure);
        View x = tf0.x(R.id.selection_container);
        Spinner spinner = (Spinner) x.findViewById(R.id.dropdown_spinner);
        this.A0 = spinner;
        spinner.setOnItemSelectedListener(this);
        ArrayAdapter arrayAdapter = new ArrayAdapter(x.getContext(), (int) R.layout.f41320_resource_name_obfuscated_RES_2131624441, this.w0);
        arrayAdapter.setDropDownViewResource(17367049);
        this.A0.setAdapter((SpinnerAdapter) arrayAdapter);
        TextView textView = (TextView) x.findViewById(R.id.privacy_policy);
        this.B0 = textView;
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        EditText editText = (EditText) x.findViewById(R.id.custom_server);
        this.C0 = editText;
        editText.addTextChangedListener(this);
        this.D0 = (TextInputLayout) x.findViewById(R.id.custom_server_layout);
        this.x0.b(x, this.z0);
        c0();
    }
}
