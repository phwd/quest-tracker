package defpackage;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import java.util.Iterator;

/* renamed from: B10  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class B10 extends AbstractC4854t41 {
    public static boolean P = true;
    public final C2966i10 Q;
    public final C5694y10 R;

    public B10(Context context, C2966i10 i10, AbstractC2400ek ekVar, boolean z) {
        super(context, null);
        this.Q = i10;
        setVerticalScrollBarEnabled(false);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -2, 80);
        layoutParams.topMargin = (int) ((((float) (z ? 144 : 104)) * YF.b(context).e) + 0.5f);
        setLayoutParams(layoutParams);
        C5694y10 y10 = new C5694y10(context, new RunnableC5864z10(this), new A10(this));
        this.R = y10;
        addView(y10, new FrameLayout.LayoutParams(-1, -2, 1));
    }

    @Override // defpackage.AbstractC4854t41
    public void c(boolean z) {
        float f;
        Animator animator = this.f11321J;
        if (animator != null) {
            animator.cancel();
        }
        if (z) {
            f = 0.0f;
        } else {
            f = (float) this.M;
        }
        long max = Math.max(0L, (long) ((Math.abs(f - getTranslationY()) / ((float) this.M)) * 250.0f));
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this, View.TRANSLATION_Y, f);
        ofFloat.setDuration(max);
        ofFloat.setInterpolator(this.H);
        this.f11321J = ofFloat;
        ofFloat.addListener(new C4684s41(this));
        this.f11321J.start();
    }

    @Override // defpackage.AbstractC4854t41
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (getVisibility() != 8) {
            setVisibility(0);
            setAlpha(0.0f);
            animate().alpha(1.0f).setDuration(250);
        }
    }

    public void setTranslationY(float f) {
        super.setTranslationY(f);
        float height = getHeight() > 0 ? 1.0f - (f / ((float) getHeight())) : 0.0f;
        C2966i10 i10 = this.Q;
        Iterator it = i10.F.f10392J.iterator();
        while (true) {
            C1261Uq0 uq0 = (C1261Uq0) it;
            if (uq0.hasNext()) {
                ((AbstractC3478l10) uq0.next()).c(i10.F, height);
            } else {
                return;
            }
        }
    }
}
