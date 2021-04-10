package defpackage;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.TextView;
import com.oculus.browser.R;

/* renamed from: tz  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5006tz extends AbstractView$OnLayoutChangeListenerC1450Xt0 {
    public TextView Z;
    public boolean a0;
    public boolean b0;
    public float c0 = 0.0f;
    public C5677xw d0;
    public boolean e0;

    public C5006tz(AbstractC0292Et0 et0, Context context, ViewGroup viewGroup, IJ ij, boolean z) {
        super(et0, R.layout.f37500_resource_name_obfuscated_RES_2131624059, R.id.contextual_search_caption_view, context, viewGroup, ij, R.dimen.f17780_resource_name_obfuscated_RES_2131165397, R.dimen.f17790_resource_name_obfuscated_RES_2131165398);
    }

    @Override // defpackage.AbstractC3631lv1
    public void i() {
        if (!(this instanceof C3675mA)) {
            b();
        }
        if (!this.e0) {
            this.e0 = true;
            C5677xw c = C5677xw.c(this.Q.D(), 0.0f, 1.0f, 218, null);
            this.d0 = c;
            c.I.add(new C4836sz(this));
            C5677xw xwVar = this.d0;
            xwVar.K = G30.c;
            xwVar.start();
        }
    }

    @Override // defpackage.AbstractC1389Wt0, defpackage.AbstractView$OnLayoutChangeListenerC1450Xt0, defpackage.AbstractC3631lv1
    public void j() {
        super.j();
        this.Z = (TextView) this.L.findViewById(R.id.contextual_search_caption);
    }

    @Override // defpackage.AbstractC1389Wt0
    public void n(float f) {
        super.n(f);
        if (this.a0) {
            C5677xw xwVar = this.d0;
            if (xwVar != null) {
                xwVar.cancel();
            }
            this.c0 = 1.0f - f;
        }
    }

    @Override // defpackage.AbstractView$OnLayoutChangeListenerC1450Xt0
    public TextView o() {
        return this.Z;
    }

    public void p(String str) {
        if (!this.a0) {
            AbstractC1145St0.l(str);
            this.a0 = true;
            this.e0 = false;
            e();
            this.Z.setText(AbstractC1145St0.l(str));
            m(true);
            this.b0 = true;
        }
    }
}
