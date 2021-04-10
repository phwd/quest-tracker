package defpackage;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.view.View;
import android.widget.LinearLayout;
import com.google.android.material.tabs.TabLayout;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: C81  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C81 extends LinearLayout {
    public int F;
    public final Paint G;
    public final GradientDrawable H;
    public int I = -1;

    /* renamed from: J  reason: collision with root package name */
    public float f7788J;
    public int K = -1;
    public int L = -1;
    public int M = -1;
    public ValueAnimator N;
    public int O = -1;
    public int P = -1;
    public final /* synthetic */ TabLayout Q;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C81(TabLayout tabLayout, Context context) {
        super(context);
        this.Q = tabLayout;
        setWillNotDraw(false);
        this.G = new Paint();
        this.H = new GradientDrawable();
    }

    public final void a(G81 g81, RectF rectF) {
        View[] viewArr = {g81.G, g81.H, g81.I};
        int i = 0;
        int i2 = 0;
        boolean z = false;
        for (int i3 = 0; i3 < 3; i3++) {
            View view = viewArr[i3];
            if (view != null && view.getVisibility() == 0) {
                i2 = z ? Math.min(i2, view.getLeft()) : view.getLeft();
                i = z ? Math.max(i, view.getRight()) : view.getRight();
                z = true;
            }
        }
        int i4 = i - i2;
        int a2 = (int) AbstractC4486qv1.a(getContext(), 24);
        if (i4 < a2) {
            i4 = a2;
        }
        int right = (g81.getRight() + g81.getLeft()) / 2;
        int i5 = i4 / 2;
        rectF.set((float) (right - i5), 0.0f, (float) (right + i5), 0.0f);
    }

    public final void b() {
        int i;
        View childAt = getChildAt(this.I);
        int i2 = -1;
        if (childAt == null || childAt.getWidth() <= 0) {
            i = -1;
        } else {
            int left = childAt.getLeft();
            int right = childAt.getRight();
            TabLayout tabLayout = this.Q;
            if (!tabLayout.k0 && (childAt instanceof G81)) {
                a((G81) childAt, tabLayout.I);
                RectF rectF = this.Q.I;
                left = (int) rectF.left;
                right = (int) rectF.right;
            }
            if (this.f7788J <= 0.0f || this.I >= getChildCount() - 1) {
                i2 = left;
                i = right;
            } else {
                View childAt2 = getChildAt(this.I + 1);
                int left2 = childAt2.getLeft();
                int right2 = childAt2.getRight();
                TabLayout tabLayout2 = this.Q;
                if (!tabLayout2.k0 && (childAt2 instanceof G81)) {
                    a((G81) childAt2, tabLayout2.I);
                    RectF rectF2 = this.Q.I;
                    left2 = (int) rectF2.left;
                    right2 = (int) rectF2.right;
                }
                float f = this.f7788J;
                float f2 = 1.0f - f;
                i2 = (int) ((((float) left) * f2) + (((float) left2) * f));
                i = (int) ((f2 * ((float) right)) + (((float) right2) * f));
            }
        }
        if (i2 != this.L || i != this.M) {
            this.L = i2;
            this.M = i;
            AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
            postInvalidateOnAnimation();
        }
    }

    public final void c(boolean z, int i, int i2) {
        View childAt = getChildAt(i);
        if (childAt == null) {
            b();
            return;
        }
        int left = childAt.getLeft();
        int right = childAt.getRight();
        TabLayout tabLayout = this.Q;
        if (!tabLayout.k0 && (childAt instanceof G81)) {
            a((G81) childAt, tabLayout.I);
            RectF rectF = this.Q.I;
            left = (int) rectF.left;
            right = (int) rectF.right;
        }
        int i3 = this.L;
        int i4 = this.M;
        if (i3 != left || i4 != right) {
            if (z) {
                this.O = i3;
                this.P = i4;
            }
            A81 a81 = new A81(this, left, right);
            if (z) {
                ValueAnimator valueAnimator = new ValueAnimator();
                this.N = valueAnimator;
                valueAnimator.setInterpolator(P6.b);
                valueAnimator.setDuration((long) i2);
                valueAnimator.setFloatValues(0.0f, 1.0f);
                valueAnimator.addUpdateListener(a81);
                valueAnimator.addListener(new B81(this, i));
                valueAnimator.start();
                return;
            }
            this.N.removeAllUpdateListeners();
            this.N.addUpdateListener(a81);
        }
    }

    public void draw(Canvas canvas) {
        Drawable drawable = this.Q.S;
        int i = 0;
        int intrinsicHeight = drawable != null ? drawable.getIntrinsicHeight() : 0;
        int i2 = this.F;
        if (i2 >= 0) {
            intrinsicHeight = i2;
        }
        int i3 = this.Q.h0;
        if (i3 == 0) {
            i = getHeight() - intrinsicHeight;
            intrinsicHeight = getHeight();
        } else if (i3 == 1) {
            i = (getHeight() - intrinsicHeight) / 2;
            intrinsicHeight = (getHeight() + intrinsicHeight) / 2;
        } else if (i3 != 2) {
            if (i3 != 3) {
                intrinsicHeight = 0;
            } else {
                intrinsicHeight = getHeight();
            }
        }
        int i4 = this.L;
        if (i4 >= 0 && this.M > i4) {
            Drawable drawable2 = this.Q.S;
            if (drawable2 == null) {
                drawable2 = this.H;
            }
            Drawable mutate = drawable2.mutate();
            mutate.setBounds(this.L, i, this.M, intrinsicHeight);
            Paint paint = this.G;
            if (paint != null) {
                mutate.setTint(paint.getColor());
            }
            mutate.draw(canvas);
        }
        super.draw(canvas);
    }

    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        ValueAnimator valueAnimator = this.N;
        if (valueAnimator == null || !valueAnimator.isRunning()) {
            b();
        } else {
            c(false, this.I, -1);
        }
    }

    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        if (View.MeasureSpec.getMode(i) == 1073741824) {
            TabLayout tabLayout = this.Q;
            boolean z = true;
            if (tabLayout.f0 == 1 || tabLayout.i0 == 2) {
                int childCount = getChildCount();
                int i3 = 0;
                for (int i4 = 0; i4 < childCount; i4++) {
                    View childAt = getChildAt(i4);
                    if (childAt.getVisibility() == 0) {
                        i3 = Math.max(i3, childAt.getMeasuredWidth());
                    }
                }
                if (i3 > 0) {
                    if (i3 * childCount <= getMeasuredWidth() - (((int) AbstractC4486qv1.a(getContext(), 16)) * 2)) {
                        boolean z2 = false;
                        for (int i5 = 0; i5 < childCount; i5++) {
                            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) getChildAt(i5).getLayoutParams();
                            if (layoutParams.width != i3 || layoutParams.weight != 0.0f) {
                                layoutParams.width = i3;
                                layoutParams.weight = 0.0f;
                                z2 = true;
                            }
                        }
                        z = z2;
                    } else {
                        TabLayout tabLayout2 = this.Q;
                        tabLayout2.f0 = 0;
                        tabLayout2.v(false);
                    }
                    if (z) {
                        super.onMeasure(i, i2);
                    }
                }
            }
        }
    }

    public void onRtlPropertiesChanged(int i) {
        super.onRtlPropertiesChanged(i);
    }
}
