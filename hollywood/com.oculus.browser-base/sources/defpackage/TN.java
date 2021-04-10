package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: TN  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class TN extends AnimatorListenerAdapter {
    public final View F;
    public boolean G = false;

    public TN(View view) {
        this.F = view;
    }

    public void onAnimationEnd(Animator animator) {
        AbstractC4315pv1.f11100a.e(this.F, 1.0f);
        if (this.G) {
            this.F.setLayerType(0, null);
        }
    }

    public void onAnimationStart(Animator animator) {
        View view = this.F;
        AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
        if (view.hasOverlappingRendering() && this.F.getLayerType() == 0) {
            this.G = true;
            this.F.setLayerType(2, null);
        }
    }
}
