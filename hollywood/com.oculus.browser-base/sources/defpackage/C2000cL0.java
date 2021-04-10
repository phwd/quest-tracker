package defpackage;

import android.view.ViewGroup;
import java.util.List;

/* renamed from: cL0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2000cL0 extends AbstractC5750yK0 implements AbstractC2648g90 {
    public final AbstractC1649aL0 I;

    /* renamed from: J  reason: collision with root package name */
    public final AbstractC1829bL0 f9601J;

    public C2000cL0(AbstractC1649aL0 al0, AbstractC1829bL0 bl0) {
        this.I = al0;
        this.f9601J = bl0;
        ((AbstractC2990i90) al0).F.b(this);
    }

    @Override // defpackage.AbstractC2648g90
    public void a(AbstractC2819h90 h90, int i, int i2) {
        this.F.f(i, i2);
    }

    @Override // defpackage.AbstractC5750yK0
    public int b() {
        return this.I.h();
    }

    @Override // defpackage.AbstractC5750yK0
    public int d(int i) {
        return this.I.getItemViewType(i);
    }

    @Override // defpackage.AbstractC2648g90
    public void e(AbstractC2819h90 h90, int i, int i2) {
        this.F.c(i, i2);
    }

    @Override // defpackage.AbstractC2648g90
    public void f(AbstractC2819h90 h90, int i, int i2) {
        h(i, i2);
    }

    @Override // defpackage.AbstractC5750yK0
    public void j(XK0 xk0, int i) {
        this.I.b(xk0, i, null);
    }

    @Override // defpackage.AbstractC5750yK0
    public void k(XK0 xk0, int i, List list) {
        if (list.isEmpty()) {
            this.I.b(xk0, i, null);
            return;
        }
        for (Object obj : list) {
            this.I.b(xk0, i, obj);
        }
    }

    @Override // defpackage.AbstractC2648g90
    public void l(AbstractC2819h90 h90, int i, int i2, Object obj) {
        this.F.d(i, i2, obj);
    }

    @Override // defpackage.AbstractC5750yK0
    public XK0 m(ViewGroup viewGroup, int i) {
        return (XK0) this.f9601J.a(viewGroup, i);
    }

    @Override // defpackage.AbstractC5750yK0
    public void q(XK0 xk0) {
        this.I.c(xk0);
    }
}
