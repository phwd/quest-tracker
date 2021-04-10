package X;

import android.animation.ValueAnimator;
import android.view.View;

/* renamed from: X.07i  reason: invalid class name */
public class AnonymousClass07i implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ View A00;
    public final /* synthetic */ C003007j A01;
    public final /* synthetic */ AbstractC003207l A02;

    public AnonymousClass07i(C003007j r1, AbstractC003207l r2, View view) {
        this.A01 = r1;
        this.A02 = r2;
        this.A00 = view;
    }

    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        this.A02.A6l(this.A00);
    }
}
