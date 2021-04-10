package defpackage;

import android.content.Context;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import androidx.appcompat.widget.ActionBarContextView;
import java.lang.ref.WeakReference;

/* renamed from: VZ0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class VZ0 extends AbstractC5696y2 implements AbstractC4275pi0 {
    public Context H;
    public ActionBarContextView I;

    /* renamed from: J  reason: collision with root package name */
    public AbstractC5526x2 f9092J;
    public WeakReference K;
    public boolean L;
    public C4616ri0 M;

    public VZ0(Context context, ActionBarContextView actionBarContextView, AbstractC5526x2 x2Var, boolean z) {
        this.H = context;
        this.I = actionBarContextView;
        this.f9092J = x2Var;
        C4616ri0 ri0 = new C4616ri0(actionBarContextView.getContext());
        ri0.m = 1;
        this.M = ri0;
        ri0.f = this;
    }

    @Override // defpackage.AbstractC4275pi0
    public boolean a(C4616ri0 ri0, MenuItem menuItem) {
        return this.f9092J.b(this, menuItem);
    }

    @Override // defpackage.AbstractC4275pi0
    public void b(C4616ri0 ri0) {
        i();
        C4676s2 s2Var = this.I.I;
        if (s2Var != null) {
            s2Var.n();
        }
    }

    @Override // defpackage.AbstractC5696y2
    public void c() {
        if (!this.L) {
            this.L = true;
            this.I.sendAccessibilityEvent(32);
            this.f9092J.d(this);
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
        return this.M;
    }

    @Override // defpackage.AbstractC5696y2
    public MenuInflater f() {
        return new X31(this.I.getContext());
    }

    @Override // defpackage.AbstractC5696y2
    public CharSequence g() {
        return this.I.O;
    }

    @Override // defpackage.AbstractC5696y2
    public CharSequence h() {
        return this.I.N;
    }

    @Override // defpackage.AbstractC5696y2
    public void i() {
        this.f9092J.c(this, this.M);
    }

    @Override // defpackage.AbstractC5696y2
    public boolean j() {
        return this.I.W;
    }

    @Override // defpackage.AbstractC5696y2
    public void k(View view) {
        this.I.h(view);
        this.K = view != null ? new WeakReference(view) : null;
    }

    @Override // defpackage.AbstractC5696y2
    public void l(int i) {
        String string = this.H.getString(i);
        ActionBarContextView actionBarContextView = this.I;
        actionBarContextView.O = string;
        actionBarContextView.d();
    }

    @Override // defpackage.AbstractC5696y2
    public void m(CharSequence charSequence) {
        ActionBarContextView actionBarContextView = this.I;
        actionBarContextView.O = charSequence;
        actionBarContextView.d();
    }

    @Override // defpackage.AbstractC5696y2
    public void n(int i) {
        String string = this.H.getString(i);
        ActionBarContextView actionBarContextView = this.I;
        actionBarContextView.N = string;
        actionBarContextView.d();
    }

    @Override // defpackage.AbstractC5696y2
    public void o(CharSequence charSequence) {
        ActionBarContextView actionBarContextView = this.I;
        actionBarContextView.N = charSequence;
        actionBarContextView.d();
    }

    @Override // defpackage.AbstractC5696y2
    public void p(boolean z) {
        this.G = z;
        ActionBarContextView actionBarContextView = this.I;
        if (z != actionBarContextView.W) {
            actionBarContextView.requestLayout();
        }
        actionBarContextView.W = z;
    }
}
