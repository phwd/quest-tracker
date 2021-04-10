package defpackage;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import androidx.appcompat.view.menu.ExpandedMenuView;
import com.oculus.browser.R;

/* renamed from: a90  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1614a90 implements AbstractC2057cj0, AdapterView.OnItemClickListener {
    public Context F;
    public LayoutInflater G;
    public C4616ri0 H;
    public ExpandedMenuView I;

    /* renamed from: J  reason: collision with root package name */
    public AbstractC1886bj0 f9412J;
    public Z80 K;

    public C1614a90(Context context, int i) {
        this.F = context;
        this.G = LayoutInflater.from(context);
    }

    @Override // defpackage.AbstractC2057cj0
    public void a(C4616ri0 ri0, boolean z) {
        AbstractC1886bj0 bj0 = this.f9412J;
        if (bj0 != null) {
            bj0.a(ri0, z);
        }
    }

    public ListAdapter b() {
        if (this.K == null) {
            this.K = new Z80(this);
        }
        return this.K;
    }

    @Override // defpackage.AbstractC2057cj0
    public boolean c(C4616ri0 ri0, C0817Ni0 ni0) {
        return false;
    }

    @Override // defpackage.AbstractC2057cj0
    public void e(AbstractC1886bj0 bj0) {
        this.f9412J = bj0;
    }

    @Override // defpackage.AbstractC2057cj0
    public void g(Context context, C4616ri0 ri0) {
        if (this.F != null) {
            this.F = context;
            if (this.G == null) {
                this.G = LayoutInflater.from(context);
            }
        }
        this.H = ri0;
        Z80 z80 = this.K;
        if (z80 != null) {
            z80.notifyDataSetChanged();
        }
    }

    @Override // defpackage.AbstractC2057cj0
    public void h(boolean z) {
        Z80 z80 = this.K;
        if (z80 != null) {
            z80.notifyDataSetChanged();
        }
    }

    @Override // defpackage.AbstractC2057cj0
    public boolean i(SubMenuC4510r31 r31) {
        if (!r31.hasVisibleItems()) {
            return false;
        }
        DialogInterface$OnKeyListenerC0635Ki0 ki0 = new DialogInterface$OnKeyListenerC0635Ki0(r31);
        C2290e4 e4Var = new C2290e4(r31.b);
        C1614a90 a90 = new C1614a90(e4Var.f9828a.f9407a, R.layout.f36430_resource_name_obfuscated_RES_2131623952);
        ki0.H = a90;
        a90.f9412J = ki0;
        C4616ri0 ri0 = ki0.F;
        ri0.b(a90, ri0.b);
        e4Var.b(ki0.H.b(), ki0);
        View view = r31.p;
        if (view != null) {
            e4Var.f9828a.e = view;
        } else {
            Drawable drawable = r31.o;
            C1598a4 a4Var = e4Var.f9828a;
            a4Var.c = drawable;
            a4Var.d = r31.n;
        }
        e4Var.f9828a.m = ki0;
        DialogC2461f4 a2 = e4Var.a();
        ki0.G = a2;
        a2.setOnDismissListener(ki0);
        WindowManager.LayoutParams attributes = ki0.G.getWindow().getAttributes();
        attributes.type = 1003;
        attributes.flags |= 131072;
        ki0.G.show();
        AbstractC1886bj0 bj0 = this.f9412J;
        if (bj0 == null) {
            return true;
        }
        bj0.b(r31);
        return true;
    }

    @Override // defpackage.AbstractC2057cj0
    public boolean j() {
        return false;
    }

    @Override // defpackage.AbstractC2057cj0
    public boolean k(C4616ri0 ri0, C0817Ni0 ni0) {
        return false;
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView adapterView, View view, int i, long j) {
        this.H.r(this.K.getItem(i), this, 0);
    }
}
