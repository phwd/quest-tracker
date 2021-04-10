package X;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import android.view.ViewPropertyAnimator;
import com.oculus.vrshell.panels.AndroidPanelLayer;

/* renamed from: X.1Ed  reason: invalid class name and case insensitive filesystem */
public class C05971Ed extends AnimatorListenerAdapter {
    public final /* synthetic */ int A00;
    public final /* synthetic */ int A01;
    public final /* synthetic */ View A02;
    public final /* synthetic */ ViewPropertyAnimator A03;
    public final /* synthetic */ AnonymousClass1Ef A04;
    public final /* synthetic */ AnonymousClass1Ah A05;

    public C05971Ed(AnonymousClass1Ef r1, AnonymousClass1Ah r2, int i, View view, int i2, ViewPropertyAnimator viewPropertyAnimator) {
        this.A04 = r1;
        this.A05 = r2;
        this.A00 = i;
        this.A02 = view;
        this.A01 = i2;
        this.A03 = viewPropertyAnimator;
    }

    public final void onAnimationCancel(Animator animator) {
        if (this.A00 != 0) {
            this.A02.setTranslationX(AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z);
        }
        if (this.A01 != 0) {
            this.A02.setTranslationY(AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z);
        }
    }

    public final void onAnimationEnd(Animator animator) {
        this.A03.setListener(null);
        AnonymousClass1Ef r2 = this.A04;
        AnonymousClass1Ah r1 = this.A05;
        AnonymousClass1BQ r0 = ((AnonymousClass1Al) r2).A04;
        if (r0 != null) {
            r0.A6j(r1);
        }
        r2.A04.remove(r1);
        r2.A0C();
    }

    public final void onAnimationStart(Animator animator) {
    }
}
