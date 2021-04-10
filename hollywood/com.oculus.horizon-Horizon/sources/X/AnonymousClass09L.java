package X;

import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.Transformation;
import androidx.annotation.NonNull;

/* renamed from: X.09L  reason: invalid class name */
public class AnonymousClass09L extends AnimationSet implements Runnable {
    public static final String __redex_internal_original_name = "androidx.fragment.app.FragmentAnim$EndViewTransitionAnimation";
    public boolean A00 = true;
    public boolean A01;
    public boolean A02;
    public final View A03;
    public final ViewGroup A04;

    public AnonymousClass09L(@NonNull Animation animation, @NonNull ViewGroup viewGroup, @NonNull View view) {
        super(false);
        this.A04 = viewGroup;
        this.A03 = view;
        addAnimation(animation);
        this.A04.post(this);
    }

    public final void run() {
        if (this.A01 || !this.A00) {
            this.A04.endViewTransition(this.A03);
            this.A02 = true;
            return;
        }
        this.A00 = false;
        this.A04.post(this);
    }

    public final boolean getTransformation(long j, @NonNull Transformation transformation) {
        this.A00 = true;
        if (this.A01) {
            return !this.A02;
        }
        if (!super.getTransformation(j, transformation)) {
            this.A01 = true;
            AnonymousClass07K.A00(this.A04, this);
        }
        return true;
    }

    public final boolean getTransformation(long j, @NonNull Transformation transformation, float f) {
        this.A00 = true;
        if (this.A01) {
            return !this.A02;
        }
        if (!super.getTransformation(j, transformation, f)) {
            this.A01 = true;
            AnonymousClass07K.A00(this.A04, this);
        }
        return true;
    }
}
