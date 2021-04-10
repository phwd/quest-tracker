package defpackage;

import android.content.Context;

/* renamed from: M9  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class M9 extends AbstractC1117Sg1 implements C00 {
    public final int L;
    public final int M;
    public D00 N;
    public AbstractC2642g70 O;
    public final I70 P = new L9(this);
    public boolean Q;
    public boolean R;

    public M9(Context context) {
        super(context);
        this.L = AbstractC2934hr.a(context.getResources(), false);
        this.M = AbstractC2934hr.a(context.getResources(), true);
    }

    @Override // defpackage.C00
    public void c(boolean z) {
        this.Q = z;
        e();
    }

    public final void e() {
        b(this.Q && (!this.R || Pj1.a()) ? this.M : this.L, false);
    }
}
