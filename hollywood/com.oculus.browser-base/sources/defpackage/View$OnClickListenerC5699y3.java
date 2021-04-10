package defpackage;

import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import com.oculus.browser.R;

/* renamed from: y3  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class View$OnClickListenerC5699y3 implements View.OnClickListener, AbstractC3087il0 {
    public UH0 F;
    public C2746gl0 G;
    public A3 H;
    public View I;

    /* renamed from: J  reason: collision with root package name */
    public EditText f11658J = ((EditText) this.I.findViewById(R.id.text));
    public LinearLayout K;
    public TextView L;
    public TextView M;
    public RatingBar N;
    public ImageView O;
    public View P;
    public ImageView Q = ((ImageView) this.I.findViewById(R.id.icon));
    public boolean R;

    public View$OnClickListenerC5699y3(Context context, C2746gl0 gl0, C5711y7 y7Var, A3 a3) {
        this.G = gl0;
        this.H = a3;
        View inflate = LayoutInflater.from(context).inflate(R.layout.f36800_resource_name_obfuscated_RES_2131623989, (ViewGroup) null);
        this.I = inflate;
        this.P = inflate.findViewById(R.id.spinny);
        LinearLayout linearLayout = (LinearLayout) this.I.findViewById(R.id.app_info);
        this.K = linearLayout;
        this.L = (TextView) linearLayout.findViewById(R.id.name);
        this.M = (TextView) this.K.findViewById(R.id.origin);
        this.N = (RatingBar) this.K.findViewById(R.id.control_rating);
        this.O = (ImageView) this.I.findViewById(R.id.play_logo);
        this.L.setOnClickListener(this);
        this.Q.setOnClickListener(this);
        this.I.addOnLayoutChangeListener(new View$OnLayoutChangeListenerC5359w3(this));
        this.f11658J.addTextChangedListener(new C5529x3(this));
        Resources resources = context.getResources();
        HH0 hh0 = new HH0(AbstractC3258jl0.r);
        hh0.e(AbstractC3258jl0.f10235a, this);
        hh0.d(AbstractC3258jl0.c, resources, y7Var.f11664a);
        hh0.d(AbstractC3258jl0.g, resources, y7Var.b);
        hh0.b(AbstractC3258jl0.i, true);
        hh0.d(AbstractC3258jl0.j, resources, R.string.f48470_resource_name_obfuscated_RES_2131952164);
        hh0.e(AbstractC3258jl0.f, this.I);
        hh0.b(AbstractC3258jl0.m, true);
        UH0 a2 = hh0.a();
        this.F = a2;
        this.G.i(a2, 0, false);
    }

    public final void a() {
        boolean z = true;
        boolean z2 = this.f11658J.getVisibility() == 0 && TextUtils.isEmpty(this.f11658J.getText());
        UH0 uh0 = this.F;
        QH0 qh0 = AbstractC3258jl0.i;
        if (this.R && !z2) {
            z = false;
        }
        uh0.j(qh0, z);
    }

    @Override // defpackage.AbstractC3087il0
    public void d(UH0 uh0, int i) {
        int i2;
        if (i == 0) {
            this.H.g(this.f11658J.getText().toString());
            i2 = 1;
        } else {
            i2 = 2;
        }
        this.G.b(this.F, i2);
    }

    @Override // defpackage.AbstractC3087il0
    public void f(UH0 uh0, int i) {
        if (i != 1) {
            this.H.i();
        }
    }

    public void onClick(View view) {
        if ((view == this.L || view == this.Q) && this.H.f()) {
            this.G.b(this.F, 3);
        }
    }
}
