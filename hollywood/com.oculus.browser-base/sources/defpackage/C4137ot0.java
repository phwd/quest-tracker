package defpackage;

import android.view.View;
import android.view.ViewGroup;

/* renamed from: ot0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4137ot0 extends AbstractC4308pt0 {
    public C4137ot0(IK0 ik0) {
        super(ik0, null);
    }

    @Override // defpackage.AbstractC4308pt0
    public int b(View view) {
        return this.f11095a.C(view) + ((ViewGroup.MarginLayoutParams) ((JK0) view.getLayoutParams())).bottomMargin;
    }

    @Override // defpackage.AbstractC4308pt0
    public int c(View view) {
        JK0 jk0 = (JK0) view.getLayoutParams();
        return this.f11095a.E(view) + ((ViewGroup.MarginLayoutParams) jk0).topMargin + ((ViewGroup.MarginLayoutParams) jk0).bottomMargin;
    }

    @Override // defpackage.AbstractC4308pt0
    public int d(View view) {
        JK0 jk0 = (JK0) view.getLayoutParams();
        return this.f11095a.F(view) + ((ViewGroup.MarginLayoutParams) jk0).leftMargin + ((ViewGroup.MarginLayoutParams) jk0).rightMargin;
    }

    @Override // defpackage.AbstractC4308pt0
    public int e(View view) {
        return this.f11095a.H(view) - ((ViewGroup.MarginLayoutParams) ((JK0) view.getLayoutParams())).topMargin;
    }

    @Override // defpackage.AbstractC4308pt0
    public int f() {
        return this.f11095a.q;
    }

    @Override // defpackage.AbstractC4308pt0
    public int g() {
        IK0 ik0 = this.f11095a;
        return ik0.q - ik0.N();
    }

    @Override // defpackage.AbstractC4308pt0
    public int h() {
        return this.f11095a.N();
    }

    @Override // defpackage.AbstractC4308pt0
    public int i() {
        return this.f11095a.o;
    }

    @Override // defpackage.AbstractC4308pt0
    public int j() {
        return this.f11095a.n;
    }

    @Override // defpackage.AbstractC4308pt0
    public int k() {
        return this.f11095a.Q();
    }

    @Override // defpackage.AbstractC4308pt0
    public int l() {
        IK0 ik0 = this.f11095a;
        return (ik0.q - ik0.Q()) - this.f11095a.N();
    }

    @Override // defpackage.AbstractC4308pt0
    public int n(View view) {
        this.f11095a.V(view, true, this.c);
        return this.c.bottom;
    }

    @Override // defpackage.AbstractC4308pt0
    public int o(View view) {
        this.f11095a.V(view, true, this.c);
        return this.c.top;
    }

    @Override // defpackage.AbstractC4308pt0
    public void p(int i) {
        this.f11095a.c0(i);
    }
}
