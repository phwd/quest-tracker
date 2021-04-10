package defpackage;

import J.N;
import android.content.Context;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.oculus.browser.R;
import java.util.Objects;
import org.chromium.chrome.browser.contextualsearch.ContextualSearchManager;

/* renamed from: mA  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3675mA extends AbstractC1145St0 {
    public final C1616aA R;
    public final float S;
    public final int T;
    public boolean U;
    public boolean V;
    public float W;
    public float X;
    public float Y;
    public boolean Z;
    public float a0;
    public boolean b0;
    public boolean c0;

    public C3675mA(AbstractC0292Et0 et0, C1616aA aAVar, Context context, ViewGroup viewGroup, IJ ij) {
        super(et0, R.layout.f37530_resource_name_obfuscated_RES_2131624062, R.id.contextual_search_promo, context, viewGroup, ij);
        this.S = context.getResources().getDisplayMetrics().density;
        this.T = context.getResources().getColor(R.color.f10760_resource_name_obfuscated_RES_2131099766);
        this.R = aAVar;
    }

    @Override // defpackage.AbstractC3631lv1, defpackage.AbstractC1145St0
    public void a() {
        o();
        super.a();
    }

    @Override // defpackage.AbstractC3631lv1
    public void f(boolean z) {
        super.f(z);
        if (z) {
            m();
        }
    }

    @Override // defpackage.AbstractC3631lv1
    public void j() {
        View view = this.L;
        ((Button) view.findViewById(R.id.contextual_search_allow_button)).setOnClickListener(new View$OnClickListenerC2821hA(this));
        ((Button) view.findViewById(R.id.contextual_search_no_thanks_button)).setOnClickListener(new View$OnClickListenerC2992iA(this));
        TextView textView = (TextView) view.findViewById(R.id.contextual_search_promo_text);
        C4467qp0 qp0 = new C4467qp0(view.getResources(), new C3162jA(this));
        textView.setText(FY0.a(view.getResources().getString(R.string.f50140_resource_name_obfuscated_RES_2131952331), new EY0("<link>", "</link>", qp0)));
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        m();
    }

    public final void m() {
        h();
        float f = this.Y;
        float c = (float) c();
        this.Y = c;
        if (this.U) {
            this.X = (float) Math.round((this.X / f) * c);
        }
    }

    public final void n(boolean z) {
        if (!this.c0) {
            this.c0 = true;
            N.MY13p7Sp(ContextualSearchManager.f().f10883a, "search.contextual_search_enabled", z ? "true" : "false");
        }
    }

    public void o() {
        if (this.U) {
            p();
            this.U = false;
            this.V = false;
            this.X = 0.0f;
            this.W = 0.0f;
        }
    }

    public final void p() {
        View view = this.L;
        if (view != null && this.U && this.Z) {
            view.setVisibility(4);
            this.Z = false;
        }
    }

    public final void q(float f) {
        if (this.U) {
            float f2 = this.Y;
            this.X = (float) Math.round(AbstractC4089od0.b(f * f2, 0.0f, f2));
            this.W = f;
        } else {
            this.X = 0.0f;
            this.W = 0.0f;
        }
        Objects.requireNonNull(this.R);
    }
}
