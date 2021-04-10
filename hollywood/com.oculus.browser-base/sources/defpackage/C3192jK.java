package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

/* renamed from: jK  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3192jK extends AnimatorListenerAdapter {
    public final /* synthetic */ View$OnClickListenerC3876nK F;

    public C3192jK(View$OnClickListenerC3876nK nKVar) {
        this.F = nKVar;
    }

    public void onAnimationEnd(Animator animator) {
        View$OnClickListenerC3876nK nKVar = this.F;
        nKVar.Y = null;
        nKVar.dismiss();
    }
}
