package defpackage;

import android.view.View;
import android.view.animation.DecelerateInterpolator;

/* renamed from: Hu0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC0477Hu0 implements Runnable {
    public final C0599Ju0 F;
    public final View G;
    public final CharSequence H;
    public final Runnable I;

    public RunnableC0477Hu0(C0599Ju0 ju0, View view, CharSequence charSequence, Runnable runnable) {
        this.F = ju0;
        this.G = view;
        this.H = charSequence;
        this.I = runnable;
    }

    public void run() {
        C0599Ju0 ju0 = this.F;
        View view = this.G;
        CharSequence charSequence = this.H;
        Runnable runnable = this.I;
        ju0.b(view, charSequence);
        ju0.I.setScaleX(0.92f);
        ju0.I.setScaleY(0.92f);
        ju0.I.setAlpha(0.0f);
        ju0.I.animate().setDuration(210).scaleX(1.0f).scaleY(1.0f).alpha(1.0f).setInterpolator(new DecelerateInterpolator()).withEndAction(runnable);
    }
}
