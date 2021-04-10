package defpackage;

import J.N;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.oculus.browser.R;
import org.chromium.chrome.browser.autofill.AutofillNameFixFlowBridge;

/* renamed from: Ud  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1228Ud implements TextWatcher, AbstractC3087il0 {
    public final AutofillNameFixFlowBridge F;
    public final UH0 G;
    public final View H;
    public final EditText I;

    /* renamed from: J  reason: collision with root package name */
    public final ImageView f9035J;
    public PopupWindow K;
    public C2746gl0 L;
    public Context M;

    public C1228Ud(Context context, AutofillNameFixFlowBridge autofillNameFixFlowBridge, String str, String str2, String str3, int i) {
        this.F = autofillNameFixFlowBridge;
        View inflate = LayoutInflater.from(context).inflate(R.layout.f36980_resource_name_obfuscated_RES_2131624007, (ViewGroup) null);
        this.H = inflate;
        EditText editText = (EditText) inflate.findViewById(R.id.cc_name_edit);
        this.I = editText;
        editText.setText(str2, TextView.BufferType.EDITABLE);
        ImageView imageView = (ImageView) inflate.findViewById(R.id.cc_name_tooltip_icon);
        this.f9035J = imageView;
        imageView.setOnClickListener(new View$OnClickListenerC0984Qd(this));
        HH0 hh0 = new HH0(AbstractC3258jl0.r);
        hh0.e(AbstractC3258jl0.f10235a, this);
        hh0.e(AbstractC3258jl0.c, str);
        hh0.e(AbstractC3258jl0.f, inflate);
        hh0.e(AbstractC3258jl0.g, str3);
        hh0.d(AbstractC3258jl0.j, context.getResources(), R.string.f48470_resource_name_obfuscated_RES_2131952164);
        hh0.b(AbstractC3258jl0.m, false);
        hh0.b(AbstractC3258jl0.i, str2.isEmpty());
        if (i != 0) {
            TH0 th0 = AbstractC3258jl0.d;
            if (i != 0) {
                hh0.e(th0, AbstractC5544x8.a(context, i));
            }
        }
        this.G = hh0.a();
        editText.setOnEditorActionListener(new C1045Rd(this));
    }

    public void afterTextChanged(Editable editable) {
        this.G.j(AbstractC3258jl0.i, this.I.getText().toString().trim().isEmpty());
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    @Override // defpackage.AbstractC3087il0
    public void d(UH0 uh0, int i) {
        if (i == 0) {
            AutofillNameFixFlowBridge autofillNameFixFlowBridge = this.F;
            N.MW86M3Ok(autofillNameFixFlowBridge.f10608a, autofillNameFixFlowBridge, this.I.getText().toString());
            this.L.b(uh0, 1);
        } else if (i == 1) {
            this.L.b(uh0, 2);
        }
    }

    @Override // defpackage.AbstractC3087il0
    public void f(UH0 uh0, int i) {
        if (i != 1 && i != 4) {
            AutofillNameFixFlowBridge autofillNameFixFlowBridge = this.F;
            N.MriHT7LJ(autofillNameFixFlowBridge.f10608a, autofillNameFixFlowBridge);
        }
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }
}
