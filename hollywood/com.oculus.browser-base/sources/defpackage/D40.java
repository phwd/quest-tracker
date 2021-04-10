package defpackage;

import android.content.Context;
import android.content.res.Resources;
import com.oculus.browser.R;
import java.util.Objects;
import org.chromium.components.javascript_dialogs.JavascriptDialogCustomView;

/* renamed from: D40  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class D40 implements AbstractC3087il0 {
    public final String F;
    public final String G;
    public final int H;
    public final int I;

    /* renamed from: J  reason: collision with root package name */
    public final String f7861J;
    public final boolean K;
    public C2746gl0 L;
    public UH0 M;
    public JavascriptDialogCustomView N;

    public D40(String str, String str2, String str3, boolean z, int i, int i2) {
        this.F = str;
        this.G = str2;
        this.H = i;
        this.I = i2;
        this.f7861J = str3;
        this.K = z;
    }

    public abstract void a(String str, boolean z);

    public abstract void b(boolean z, boolean z2);

    public void c(Context context, C2746gl0 gl0, int i) {
        JavascriptDialogCustomView javascriptDialogCustomView = (JavascriptDialogCustomView) AbstractC2471f70.a(context, R.layout.f38890_resource_name_obfuscated_RES_2131624198, null);
        this.N = javascriptDialogCustomView;
        String str = this.f7861J;
        Objects.requireNonNull(javascriptDialogCustomView);
        if (str != null) {
            javascriptDialogCustomView.F.setVisibility(0);
            if (str.length() > 0) {
                javascriptDialogCustomView.F.setText(str);
                javascriptDialogCustomView.F.selectAll();
            }
        }
        this.N.G.setVisibility(this.K ? 0 : 8);
        Resources resources = context.getResources();
        HH0 hh0 = new HH0(AbstractC3258jl0.r);
        hh0.e(AbstractC3258jl0.f10235a, this);
        hh0.e(AbstractC3258jl0.c, this.F);
        hh0.e(AbstractC3258jl0.e, this.G);
        hh0.e(AbstractC3258jl0.f, this.N);
        hh0.d(AbstractC3258jl0.g, resources, this.H);
        hh0.d(AbstractC3258jl0.j, resources, this.I);
        hh0.b(AbstractC3258jl0.p, true);
        UH0 a2 = hh0.a();
        this.M = a2;
        this.L = gl0;
        gl0.i(a2, i, false);
    }

    @Override // defpackage.AbstractC3087il0
    public void d(UH0 uh0, int i) {
        C2746gl0 gl0 = this.L;
        if (gl0 != null) {
            if (i == 0) {
                gl0.b(uh0, 1);
            } else if (i != 1) {
                AbstractC1220Ua0.a("JSModalDialog", AbstractC2531fV.w("Unexpected button pressed in dialog: ", i), new Object[0]);
            } else {
                gl0.b(uh0, 2);
            }
        }
    }

    @Override // defpackage.AbstractC3087il0
    public void f(UH0 uh0, int i) {
        JavascriptDialogCustomView javascriptDialogCustomView = this.N;
        if (javascriptDialogCustomView != null) {
            if (i == 1) {
                a(javascriptDialogCustomView.F.getText().toString(), this.N.a());
            } else if (i == 2) {
                b(true, javascriptDialogCustomView.a());
            } else if (i != 4) {
                b(false, javascriptDialogCustomView.a());
            }
            this.M = null;
            this.N = null;
            this.L = null;
        }
    }
}
