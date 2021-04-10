package X;

import android.animation.ValueAnimator;
import android.view.View;

/* renamed from: X.0Az  reason: invalid class name */
public class AnonymousClass0Az implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ View A00;
    public final /* synthetic */ AnonymousClass0B0 A01;
    public final /* synthetic */ AnonymousClass0B2 A02;

    public AnonymousClass0Az(AnonymousClass0B0 r1, AnonymousClass0B2 r2, View view) {
        this.A01 = r1;
        this.A02 = r2;
        this.A00 = view;
    }

    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        this.A02.A5s(this.A00);
    }
}
