package defpackage;

import J.N;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import com.oculus.browser.R;
import org.chromium.chrome.browser.autofill.AutofillExpirationDateFixFlowBridge;

/* renamed from: Md  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0740Md implements TextWatcher, AbstractC3087il0 {
    public final AutofillExpirationDateFixFlowBridge F;
    public final UH0 G;
    public final View H;
    public final EditText I;

    /* renamed from: J  reason: collision with root package name */
    public final EditText f8488J;
    public final TextView K;
    public final TextView L;
    public C2746gl0 M;
    public Context N;
    public boolean O;
    public boolean P;

    public C0740Md(Context context, AutofillExpirationDateFixFlowBridge autofillExpirationDateFixFlowBridge, String str, String str2, int i, String str3) {
        this.F = autofillExpirationDateFixFlowBridge;
        View inflate = LayoutInflater.from(context).inflate(R.layout.f36960_resource_name_obfuscated_RES_2131624005, (ViewGroup) null);
        this.H = inflate;
        this.K = (TextView) inflate.findViewById(R.id.error_message);
        TextView textView = (TextView) inflate.findViewById(R.id.cc_details_masked);
        this.L = textView;
        textView.setText(str3);
        EditText editText = (EditText) inflate.findViewById(R.id.cc_month_edit);
        this.I = editText;
        editText.addTextChangedListener(this);
        editText.setOnFocusChangeListener(new View$OnFocusChangeListenerC0619Kd(this));
        EditText editText2 = (EditText) inflate.findViewById(R.id.cc_year_edit);
        this.f8488J = editText2;
        editText2.addTextChangedListener(this);
        editText2.setOnFocusChangeListener(new View$OnFocusChangeListenerC0680Ld(this));
        HH0 hh0 = new HH0(AbstractC3258jl0.r);
        hh0.e(AbstractC3258jl0.f10235a, this);
        hh0.e(AbstractC3258jl0.c, str);
        hh0.e(AbstractC3258jl0.f, inflate);
        hh0.e(AbstractC3258jl0.g, str2);
        hh0.d(AbstractC3258jl0.j, context.getResources(), R.string.f48470_resource_name_obfuscated_RES_2131952164);
        hh0.b(AbstractC3258jl0.m, false);
        hh0.b(AbstractC3258jl0.i, true);
        if (i != 0) {
            TH0 th0 = AbstractC3258jl0.d;
            if (i != 0) {
                hh0.e(th0, AbstractC5544x8.a(context, i));
            }
        }
        this.G = hh0.a();
    }

    public void afterTextChanged(Editable editable) {
        int a2 = AbstractC0073Be.a(this.I, this.f8488J, this.O, this.P);
        this.G.j(AbstractC3258jl0.i, a2 != 7);
        AbstractC0073Be.d(a2, this.N, this.K);
        AbstractC0073Be.f(a2, this.N, this.I, this.f8488J, null);
        if (this.I.isFocused() && this.I.getText().length() == 2 && a2 != 1) {
            this.f8488J.requestFocus();
            this.P = true;
        }
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    @Override // defpackage.AbstractC3087il0
    public void d(UH0 uh0, int i) {
        if (i == 0) {
            String trim = this.I.getText().toString().trim();
            String trim2 = this.f8488J.getText().toString().trim();
            AutofillExpirationDateFixFlowBridge autofillExpirationDateFixFlowBridge = this.F;
            N.MX7djb2r(autofillExpirationDateFixFlowBridge.f10607a, autofillExpirationDateFixFlowBridge, trim, trim2);
            this.M.b(uh0, 1);
        } else if (i == 1) {
            this.M.b(uh0, 2);
        }
    }

    @Override // defpackage.AbstractC3087il0
    public void f(UH0 uh0, int i) {
        if (i != 1 && i != 4) {
            AutofillExpirationDateFixFlowBridge autofillExpirationDateFixFlowBridge = this.F;
            N.MYC4Z0Ea(autofillExpirationDateFixFlowBridge.f10607a, autofillExpirationDateFixFlowBridge);
            autofillExpirationDateFixFlowBridge.f10607a = 0;
        }
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }
}
