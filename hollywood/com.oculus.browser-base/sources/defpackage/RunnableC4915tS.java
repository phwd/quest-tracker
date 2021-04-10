package defpackage;

import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.Transformation;

/* renamed from: tS  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class RunnableC4915tS extends AnimationSet implements Runnable {
    public final ViewGroup F;
    public final View G;
    public boolean H;
    public boolean I;

    /* renamed from: J  reason: collision with root package name */
    public boolean f11343J = true;

    public RunnableC4915tS(Animation animation, ViewGroup viewGroup, View view) {
        super(false);
        this.F = viewGroup;
        this.G = view;
        addAnimation(animation);
        viewGroup.post(this);
    }

    public boolean getTransformation(long j, Transformation transformation) {
        this.f11343J = true;
        if (this.H) {
            return !this.I;
        }
        if (!super.getTransformation(j, transformation)) {
            this.H = true;
            ViewTreeObserver$OnPreDrawListenerC1448Xs0.a(this.F, this);
        }
        return true;
    }

    public void run() {
        if (this.H || !this.f11343J) {
            this.F.endViewTransition(this.G);
            this.I = true;
            return;
        }
        this.f11343J = false;
        this.F.post(this);
    }

    public boolean getTransformation(long j, Transformation transformation, float f) {
        this.f11343J = true;
        if (this.H) {
            return !this.I;
        }
        if (!super.getTransformation(j, transformation, f)) {
            this.H = true;
            ViewTreeObserver$OnPreDrawListenerC1448Xs0.a(this.F, this);
        }
        return true;
    }
}
