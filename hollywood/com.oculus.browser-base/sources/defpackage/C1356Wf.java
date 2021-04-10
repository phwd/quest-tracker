package defpackage;

import android.view.View;

/* renamed from: Wf  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1356Wf implements KK0 {

    /* renamed from: a  reason: collision with root package name */
    public int f9163a = -1;
    public IK0 b;

    public C1356Wf(IK0 ik0) {
        this.b = ik0;
    }

    public void a(int i, boolean z) {
        IK0 ik0 = this.b;
        if (ik0 != null) {
            if (i != -1 && (i < 0 || i >= ik0.J())) {
                return;
            }
            if (z || i != this.f9163a) {
                View u = this.b.u(this.f9163a);
                if (u != null) {
                    u.setSelected(false);
                }
                this.f9163a = i;
                this.b.N0(i);
                View u2 = this.b.u(i);
                if (u2 != null) {
                    u2.setSelected(true);
                }
            }
        }
    }

    @Override // defpackage.KK0
    public void b(View view) {
        a(this.f9163a, true);
    }

    @Override // defpackage.KK0
    public void d(View view) {
        view.setSelected(false);
        a(this.f9163a, true);
    }
}
