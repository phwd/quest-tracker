package X;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import com.oculus.vrshell.panels.AndroidPanelLayer;

/* renamed from: X.1EE  reason: invalid class name */
public class AnonymousClass1EE extends AnimatorListenerAdapter {
    public boolean A00 = false;
    public final /* synthetic */ AnonymousClass1EA A01;

    public final void onAnimationCancel(Animator animator) {
        this.A00 = true;
    }

    public AnonymousClass1EE(AnonymousClass1EA r2) {
        this.A01 = r2;
    }

    public final void onAnimationEnd(Animator animator) {
        if (this.A00) {
            this.A00 = false;
            return;
        }
        AnonymousClass1EA r2 = this.A01;
        if (((Number) r2.A0K.getAnimatedValue()).floatValue() == AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z) {
            r2.A02 = 0;
            r2.A02(0);
            return;
        }
        r2.A02 = 2;
        r2.A0A.invalidate();
    }
}
