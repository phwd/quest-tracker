package X;

import android.animation.Animator;
import android.view.animation.Animation;

/* renamed from: X.09K  reason: invalid class name */
public class AnonymousClass09K {
    public final Animator A00;
    public final Animation A01;

    public AnonymousClass09K(Animator animator) {
        this.A01 = null;
        this.A00 = animator;
    }

    public AnonymousClass09K(Animation animation) {
        this.A01 = animation;
        this.A00 = null;
        if (animation == null) {
            throw new IllegalStateException("Animation cannot be null");
        }
    }
}
