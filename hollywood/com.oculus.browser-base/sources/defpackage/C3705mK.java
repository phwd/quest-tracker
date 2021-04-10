package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.widget.EditText;

/* renamed from: mK  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3705mK extends AnimatorListenerAdapter {
    public final /* synthetic */ View$OnClickListenerC3876nK F;

    public C3705mK(View$OnClickListenerC3876nK nKVar) {
        this.F = nKVar;
    }

    public void onAnimationEnd(Animator animator) {
        this.F.Q.setLayerType(0, null);
        for (int i = 0; i < this.F.L.size(); i++) {
            ((EditText) this.F.L.get(i)).setEnabled(true);
        }
        View$OnClickListenerC3876nK nKVar = this.F;
        nKVar.Y = null;
        nKVar.H.post(new RunnableC2338eK(nKVar));
    }
}
