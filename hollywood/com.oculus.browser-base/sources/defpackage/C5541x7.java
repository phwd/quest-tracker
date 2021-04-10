package defpackage;

import android.app.Activity;
import android.os.Handler;

/* renamed from: x7  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5541x7 extends Ep1 {
    public final Activity F;
    public final AbstractC5717y9 G;
    public final Q31 H;
    public final Handler I;

    /* renamed from: J  reason: collision with root package name */
    public final Vr1 f11590J;
    public final int K;

    public C5541x7(Activity activity, AbstractC5717y9 y9Var, Q31 q31, int i, WT wt) {
        Handler handler = new Handler();
        this.I = handler;
        this.F = activity;
        this.G = y9Var;
        this.H = q31;
        this.K = i;
        this.f11590J = new Vr1(activity, handler, wt);
    }
}
