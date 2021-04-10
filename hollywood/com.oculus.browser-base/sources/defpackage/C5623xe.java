package defpackage;

import android.app.Activity;

/* renamed from: xe  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5623xe implements AbstractC4371qE {
    public Activity F;
    public final Y2 G;

    public C5623xe(Activity activity, M2 m2, C1595a3 a3Var) {
        this.F = activity;
        this.G = new C5453we(this, a3Var, m2);
        m2.a(this);
    }

    @Override // defpackage.AbstractC4371qE
    public void destroy() {
        this.G.destroy();
        this.F = null;
    }
}
