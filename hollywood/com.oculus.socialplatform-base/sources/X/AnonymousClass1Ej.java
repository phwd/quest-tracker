package X;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import android.view.ViewPropertyAnimator;
import com.oculus.vrshell.panels.AndroidPanelLayer;

/* renamed from: X.1Ej  reason: invalid class name */
public class AnonymousClass1Ej extends AnimatorListenerAdapter {
    public final /* synthetic */ View A00;
    public final /* synthetic */ ViewPropertyAnimator A01;
    public final /* synthetic */ AnonymousClass1Ek A02;
    public final /* synthetic */ AnonymousClass1Ef A03;

    public AnonymousClass1Ej(AnonymousClass1Ef r1, AnonymousClass1Ek r2, ViewPropertyAnimator viewPropertyAnimator, View view) {
        this.A03 = r1;
        this.A02 = r2;
        this.A01 = viewPropertyAnimator;
        this.A00 = view;
    }

    public final void onAnimationEnd(Animator animator) {
        this.A01.setListener(null);
        View view = this.A00;
        view.setAlpha(1.0f);
        view.setTranslationX(AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z);
        view.setTranslationY(AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z);
        AnonymousClass1Ef r3 = this.A03;
        AnonymousClass1Ek r2 = this.A02;
        AnonymousClass1Ah r1 = r2.A04;
        AnonymousClass1BQ r0 = ((AnonymousClass1Al) r3).A04;
        if (r0 != null) {
            r0.A6j(r1);
        }
        r3.A02.remove(r2.A04);
        r3.A0C();
    }

    public final void onAnimationStart(Animator animator) {
    }
}
