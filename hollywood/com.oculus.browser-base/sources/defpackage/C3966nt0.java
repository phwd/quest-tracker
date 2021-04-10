package defpackage;

import android.view.View;
import android.view.ViewGroup;

/* renamed from: nt0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3966nt0 extends AbstractC4308pt0 {
    public C3966nt0(IK0 ik0) {
        super(ik0, null);
    }

    @Override // defpackage.AbstractC4308pt0
    public int b(View view) {
        return this.f11095a.G(view) + ((ViewGroup.MarginLayoutParams) ((JK0) view.getLayoutParams())).rightMargin;
    }

    @Override // defpackage.AbstractC4308pt0
    public int c(View view) {
        JK0 jk0 = (JK0) view.getLayoutParams();
        return this.f11095a.F(view) + ((ViewGroup.MarginLayoutParams) jk0).leftMargin + ((ViewGroup.MarginLayoutParams) jk0).rightMargin;
    }

    @Override // defpackage.AbstractC4308pt0
    public int d(View view) {
        JK0 jk0 = (JK0) view.getLayoutParams();
        return this.f11095a.E(view) + ((ViewGroup.MarginLayoutParams) jk0).topMargin + ((ViewGroup.MarginLayoutParams) jk0).bottomMargin;
    }

    @Override // defpackage.AbstractC4308pt0
    public int e(View view) {
        return this.f11095a.D(view) - ((ViewGroup.MarginLayoutParams) ((JK0) view.getLayoutParams())).leftMargin;
    }

    @Override // defpackage.AbstractC4308pt0
    public int f() {
        return this.f11095a.p;
    }

    @Override // defpackage.AbstractC4308pt0
    public int g() {
        IK0 ik0 = this.f11095a;
        return ik0.p - ik0.P();
    }

    @Override // defpackage.AbstractC4308pt0
    public int h() {
        return this.f11095a.P();
    }

    @Override // defpackage.AbstractC4308pt0
    public int i() {
        return this.f11095a.n;
    }

    @Override // defpackage.AbstractC4308pt0
    public int j() {
        return this.f11095a.o;
    }

    @Override // defpackage.AbstractC4308pt0
    public int k() {
        return this.f11095a.O();
    }

    @Override // defpackage.AbstractC4308pt0
    public int l() {
        IK0 ik0 = this.f11095a;
        return (ik0.p - ik0.O()) - this.f11095a.P();
    }

    @Override // defpackage.AbstractC4308pt0
    public int n(View view) {
        this.f11095a.V(view, true, this.c);
        return this.c.right;
    }

    @Override // defpackage.AbstractC4308pt0
    public int o(View view) {
        this.f11095a.V(view, true, this.c);
        return this.c.left;
    }

    @Override // defpackage.AbstractC4308pt0
    public void p(int i) {
        this.f11095a.b0(i);
    }
}
