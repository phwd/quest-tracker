package defpackage;

import android.content.Context;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import androidx.appcompat.widget.ActionBarContextView;
import java.lang.ref.WeakReference;

/* renamed from: Sy1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class Sy1 extends AbstractC5696y2 implements AbstractC4275pi0 {
    public final Context H;
    public final C4616ri0 I;

    /* renamed from: J  reason: collision with root package name */
    public AbstractC5526x2 f8929J;
    public WeakReference K;
    public final /* synthetic */ Ty1 L;

    public Sy1(Ty1 ty1, Context context, AbstractC5526x2 x2Var) {
        this.L = ty1;
        this.H = context;
        this.f8929J = x2Var;
        C4616ri0 ri0 = new C4616ri0(context);
        ri0.m = 1;
        this.I = ri0;
        ri0.f = this;
    }

    @Override // defpackage.AbstractC4275pi0
    public boolean a(C4616ri0 ri0, MenuItem menuItem) {
        AbstractC5526x2 x2Var = this.f8929J;
        if (x2Var != null) {
            return x2Var.b(this, menuItem);
        }
        return false;
    }

    @Override // defpackage.AbstractC4275pi0
    public void b(C4616ri0 ri0) {
        if (this.f8929J != null) {
            i();
            C4676s2 s2Var = this.L.h.I;
            if (s2Var != null) {
                s2Var.n();
            }
        }
    }

    @Override // defpackage.AbstractC5696y2
    public void c() {
        Ty1 ty1 = this.L;
        if (ty1.k == this) {
            boolean z = ty1.s;
            boolean z2 = ty1.t;
            boolean z3 = true;
            if (z || z2) {
                z3 = false;
            }
            if (!z3) {
                ty1.l = this;
                ty1.m = this.f8929J;
            } else {
                this.f8929J.d(this);
            }
            this.f8929J = null;
            this.L.d(false);
            ActionBarContextView actionBarContextView = this.L.h;
            if (actionBarContextView.P == null) {
                actionBarContextView.e();
            }
            this.L.g.f8179a.sendAccessibilityEvent(32);
            Ty1 ty12 = this.L;
            ty12.e.o(ty12.y);
            this.L.k = null;
        }
    }

    @Override // defpackage.AbstractC5696y2
    public View d() {
        WeakReference weakReference = this.K;
        if (weakReference != null) {
            return (View) weakReference.get();
        }
        return null;
    }

    @Override // defpackage.AbstractC5696y2
    public Menu e() {
        return this.I;
    }

    @Override // defpackage.AbstractC5696y2
    public MenuInflater f() {
        return new X31(this.H);
    }

    @Override // defpackage.AbstractC5696y2
    public CharSequence g() {
        return this.L.h.O;
    }

    @Override // defpackage.AbstractC5696y2
    public CharSequence h() {
        return this.L.h.N;
    }

    @Override // defpackage.AbstractC5696y2
    public void i() {
        if (this.L.k == this) {
            this.I.y();
            try {
                this.f8929J.c(this, this.I);
            } finally {
                this.I.x();
            }
        }
    }

    @Override // defpackage.AbstractC5696y2
    public boolean j() {
        return this.L.h.W;
    }

    @Override // defpackage.AbstractC5696y2
    public void k(View view) {
        this.L.h.h(view);
        this.K = new WeakReference(view);
    }

    @Override // defpackage.AbstractC5696y2
    public void l(int i) {
        String string = this.L.c.getResources().getString(i);
        ActionBarContextView actionBarContextView = this.L.h;
        actionBarContextView.O = string;
        actionBarContextView.d();
    }

    @Override // defpackage.AbstractC5696y2
    public void m(CharSequence charSequence) {
        ActionBarContextView actionBarContextView = this.L.h;
        actionBarContextView.O = charSequence;
        actionBarContextView.d();
    }

    @Override // defpackage.AbstractC5696y2
    public void n(int i) {
        String string = this.L.c.getResources().getString(i);
        ActionBarContextView actionBarContextView = this.L.h;
        actionBarContextView.N = string;
        actionBarContextView.d();
    }

    @Override // defpackage.AbstractC5696y2
    public void o(CharSequence charSequence) {
        ActionBarContextView actionBarContextView = this.L.h;
        actionBarContextView.N = charSequence;
        actionBarContextView.d();
    }

    @Override // defpackage.AbstractC5696y2
    public void p(boolean z) {
        this.G = z;
        ActionBarContextView actionBarContextView = this.L.h;
        if (z != actionBarContextView.W) {
            actionBarContextView.requestLayout();
        }
        actionBarContextView.W = z;
    }
}
