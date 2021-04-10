package defpackage;

import android.view.View;
import android.view.ViewGroup;

/* renamed from: GK0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class GK0 implements Rt1 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ IK0 f8086a;

    public GK0(IK0 ik0) {
        this.f8086a = ik0;
    }

    @Override // defpackage.Rt1
    public int a() {
        IK0 ik0 = this.f8086a;
        return ik0.q - ik0.N();
    }

    @Override // defpackage.Rt1
    public int b(View view) {
        return this.f8086a.H(view) - ((ViewGroup.MarginLayoutParams) ((JK0) view.getLayoutParams())).topMargin;
    }

    @Override // defpackage.Rt1
    public View c(int i) {
        return this.f8086a.y(i);
    }

    @Override // defpackage.Rt1
    public int d() {
        return this.f8086a.Q();
    }

    @Override // defpackage.Rt1
    public int e(View view) {
        return this.f8086a.C(view) + ((ViewGroup.MarginLayoutParams) ((JK0) view.getLayoutParams())).bottomMargin;
    }
}
