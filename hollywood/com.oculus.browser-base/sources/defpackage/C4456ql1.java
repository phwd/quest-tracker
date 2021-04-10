package defpackage;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.TimeAnimator;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import com.oculus.browser.R;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import org.chromium.base.ThreadUtils;
import org.chromium.chrome.browser.vr.VrModuleProvider;

/* renamed from: ql1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4456ql1 extends AbstractC4142ov {

    /* renamed from: J  reason: collision with root package name */
    public boolean f11161J;
    public float K;
    public C3697mH0 L;
    public boolean M;
    public int N;
    public ViewGroup O;
    public int P;
    public int Q;
    public boolean R;
    public C4966tl1 S;
    public boolean T;
    public View U;
    public final int V;
    public Animator W;
    public final View.OnLayoutChangeListener a0;
    public final Runnable b0 = new RunnableC4114ol1(this);
    public final TimeAnimator c0;

    public C4456ql1(Context context, int i, View view, boolean z) {
        super(context, i);
        View$OnLayoutChangeListenerC3772ml1 ml1 = new View$OnLayoutChangeListenerC3772ml1(this);
        this.a0 = ml1;
        TimeAnimator timeAnimator = new TimeAnimator();
        this.c0 = timeAnimator;
        timeAnimator.setTimeListener(new C4285pl1(this));
        this.V = i;
        setAlpha(0.0f);
        View view2 = this.U;
        if (view2 != view) {
            if (view2 != null) {
                view2.removeOnLayoutChangeListener(ml1);
            }
            this.U = view;
            k();
            View view3 = this.U;
            if (view3 != null) {
                view3.addOnLayoutChangeListener(ml1);
            }
        }
        this.R = z;
        this.L = new C3697mH0();
        AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
        setAccessibilityLiveRegion(1);
    }

    @Override // defpackage.AbstractC4142ov
    public void a(float f) {
        Object obj = ThreadUtils.f10596a;
        if (this.f11161J && !AbstractC4089od0.a(this.K, f)) {
            this.K = f;
            removeCallbacks(this.b0);
            if (!this.c0.isRunning()) {
                postDelayed(this.b0, 5000);
                super.a(this.K);
            }
            sendAccessibilityEvent(4);
            if (AbstractC4089od0.a(f, 1.0f) || f > 1.0f) {
                e(true);
            }
        }
    }

    public final void d(float f) {
        float alpha = f - getAlpha();
        if (alpha != 0.0f) {
            long abs = (long) Math.abs(140.0f * alpha);
            animation.InterpolatorC5286vf vfVar = animation.InterpolatorC5286vf.g;
            if (alpha < 0.0f) {
                vfVar = animation.InterpolatorC5286vf.f;
            }
            Animator animator = this.W;
            if (animator != null) {
                animator.cancel();
            }
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this, ImageView.ALPHA, getAlpha(), f);
            ofFloat.setDuration(abs);
            ofFloat.setInterpolator(vfVar);
            this.W = ofFloat;
            C4966tl1 tl1 = this.S;
            if (tl1 != null) {
                ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(tl1, ImageView.ALPHA, tl1.getAlpha(), f);
                ofFloat2.setDuration(abs);
                ofFloat2.setInterpolator(vfVar);
                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.playTogether(this.W, ofFloat2);
                this.W = animatorSet;
            }
            this.W.start();
        }
    }

    public void e(boolean z) {
        Object obj = ThreadUtils.f10596a;
        if (!AbstractC4089od0.a(this.H, 1.0f)) {
            a(1.0f);
            if (this.c0.isRunning() && z) {
                return;
            }
        }
        this.f11161J = false;
        this.K = 0.0f;
        removeCallbacks(this.b0);
        C4966tl1 tl1 = this.S;
        if (tl1 != null) {
            tl1.L = true;
            tl1.I.cancel();
            tl1.setScaleX(0.0f);
            tl1.setTranslationX(0.0f);
            tl1.animate().cancel();
            tl1.setAlpha(0.0f);
            tl1.O = 0.0f;
            tl1.H = 0.0f;
        }
        this.c0.cancel();
        if (z) {
            postDelayed(new RunnableC3943nl1(this), 100);
        } else {
            f(false);
        }
    }

    public final void f(boolean z) {
        Object obj = ThreadUtils.f10596a;
        if (!this.f11161J) {
            if (!z) {
                animate().cancel();
            }
            if (z) {
                d(0.0f);
            } else {
                setAlpha(0.0f);
            }
        }
    }

    public void g() {
        if (!this.M) {
            this.M = true;
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(getLayoutParams());
            layoutParams.width = 1;
            layoutParams.topMargin = this.N;
            this.S = new C4966tl1(getContext(), layoutParams);
            int i = this.Q;
            if (i != 0 || this.R) {
                i(i, false);
            } else {
                h(this.F.getColor());
            }
            AbstractC2417ep1.g(this.O, this.S, this);
        }
    }

    public CharSequence getAccessibilityClassName() {
        return ProgressBar.class.getName();
    }

    public void h(int i) {
        this.F.setColor(i);
        C4966tl1 tl1 = this.S;
        if (tl1 != null) {
            tl1.F.setColor(AbstractC1270Uv.a(i, -1, 0.4f));
        }
    }

    public void i(int i, boolean z) {
        this.Q = i;
        boolean d = Pj1.d(getResources(), z, this.Q);
        if (this.R) {
            if (d) {
                i = -16777216;
            }
            h(getResources().getColor(R.color.f13490_resource_name_obfuscated_RES_2131100039));
            setBackgroundColor(AbstractC1270Uv.c(i));
        } else if ((d || !AbstractC1270Uv.f(i)) && !z) {
            h(getResources().getColor(R.color.f14760_resource_name_obfuscated_RES_2131100166));
            setBackgroundColor(getResources().getColor(R.color.f14740_resource_name_obfuscated_RES_2131100164));
        } else {
            h((AbstractC1270Uv.g(i) || z) ? -1 : AbstractC1270Uv.a(i, -16777216, 0.64f));
            if (this.S != null && (AbstractC1270Uv.g(i) || z)) {
                this.S.F.setColor(AbstractC1270Uv.a(i, -1, 0.4f));
            }
            setBackgroundColor(AbstractC1270Uv.a(i, -1, 0.2f));
        }
    }

    public void j() {
        Object obj = ThreadUtils.f10596a;
        this.f11161J = true;
        this.P++;
        removeCallbacks(this.b0);
        postDelayed(this.b0, 5000);
        super.a(0.0f);
        C3697mH0 mh0 = this.L;
        mh0.f10411a = 0.0f;
        mh0.b = 0.0f;
        d(1.0f);
    }

    public final void k() {
        View view = this.U;
        int bottom = (view != null ? view.getBottom() : 0) - this.V;
        if (this.N != bottom) {
            this.N = bottom;
            if (this.T) {
                ((ViewGroup.MarginLayoutParams) getLayoutParams()).topMargin = this.N;
                C4966tl1 tl1 = this.S;
                if (tl1 != null && tl1.getLayoutParams() != null) {
                    ((ViewGroup.MarginLayoutParams) this.S.getLayoutParams()).topMargin = this.N;
                }
            }
        }
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.T = true;
        ((ViewGroup.MarginLayoutParams) getLayoutParams()).topMargin = this.N;
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.T = false;
        this.c0.setTimeListener(null);
        this.c0.cancel();
    }

    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setCurrentItemIndex((int) (this.K * 100.0f));
        accessibilityEvent.setItemCount(100);
    }

    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        C4966tl1 tl1 = this.S;
        if (tl1 != null) {
            tl1.a(((float) i) * this.H);
        }
    }

    public void setAlpha(float f) {
        super.setAlpha(f);
        C4966tl1 tl1 = this.S;
        if (tl1 != null) {
            tl1.setAlpha(f);
        }
    }

    public void setVisibility(int i) {
        Objects.requireNonNull(VrModuleProvider.b());
        this.I = i;
        b();
        C4966tl1 tl1 = this.S;
        if (tl1 != null) {
            tl1.setVisibility(i);
        }
    }
}
