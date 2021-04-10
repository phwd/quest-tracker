package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import com.oculus.browser.R;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ActionBarContainer extends FrameLayout {
    public boolean F;
    public View G;
    public View H;
    public View I;

    /* renamed from: J  reason: collision with root package name */
    public Drawable f9457J;
    public Drawable K;
    public Drawable L;
    public boolean M;
    public boolean N;
    public int O;

    public ActionBarContainer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        C1943c2 c2Var = new C1943c2(this);
        AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
        setBackground(c2Var);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, FJ0.f8010a);
        boolean z = false;
        this.f9457J = obtainStyledAttributes.getDrawable(0);
        this.K = obtainStyledAttributes.getDrawable(2);
        this.O = obtainStyledAttributes.getDimensionPixelSize(13, -1);
        if (getId() == R.id.split_action_bar) {
            this.M = true;
            this.L = obtainStyledAttributes.getDrawable(1);
        }
        obtainStyledAttributes.recycle();
        if (!this.M ? this.f9457J == null && this.K == null : this.L == null) {
            z = true;
        }
        setWillNotDraw(z);
    }

    public final int a(View view) {
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) view.getLayoutParams();
        return view.getMeasuredHeight() + layoutParams.topMargin + layoutParams.bottomMargin;
    }

    public final boolean b(View view) {
        return view == null || view.getVisibility() == 8 || view.getMeasuredHeight() == 0;
    }

    public void drawableStateChanged() {
        super.drawableStateChanged();
        Drawable drawable = this.f9457J;
        if (drawable != null && drawable.isStateful()) {
            this.f9457J.setState(getDrawableState());
        }
        Drawable drawable2 = this.K;
        if (drawable2 != null && drawable2.isStateful()) {
            this.K.setState(getDrawableState());
        }
        Drawable drawable3 = this.L;
        if (drawable3 != null && drawable3.isStateful()) {
            this.L.setState(getDrawableState());
        }
    }

    public void jumpDrawablesToCurrentState() {
        super.jumpDrawablesToCurrentState();
        Drawable drawable = this.f9457J;
        if (drawable != null) {
            drawable.jumpToCurrentState();
        }
        Drawable drawable2 = this.K;
        if (drawable2 != null) {
            drawable2.jumpToCurrentState();
        }
        Drawable drawable3 = this.L;
        if (drawable3 != null) {
            drawable3.jumpToCurrentState();
        }
    }

    public void onFinishInflate() {
        super.onFinishInflate();
        this.H = findViewById(R.id.action_bar);
        this.I = findViewById(R.id.action_context_bar);
    }

    public boolean onHoverEvent(MotionEvent motionEvent) {
        super.onHoverEvent(motionEvent);
        return true;
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return this.F || super.onInterceptTouchEvent(motionEvent);
    }

    /* JADX WARNING: Removed duplicated region for block: B:34:0x00c0  */
    /* JADX WARNING: Removed duplicated region for block: B:36:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onLayout(boolean r5, int r6, int r7, int r8, int r9) {
        /*
        // Method dump skipped, instructions count: 196
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.ActionBarContainer.onLayout(boolean, int, int, int, int):void");
    }

    public void onMeasure(int i, int i2) {
        int i3;
        int i4;
        if (this.H == null && View.MeasureSpec.getMode(i2) == Integer.MIN_VALUE && (i4 = this.O) >= 0) {
            i2 = View.MeasureSpec.makeMeasureSpec(Math.min(i4, View.MeasureSpec.getSize(i2)), Integer.MIN_VALUE);
        }
        super.onMeasure(i, i2);
        if (this.H != null) {
            int mode = View.MeasureSpec.getMode(i2);
            View view = this.G;
            if (view != null && view.getVisibility() != 8 && mode != 1073741824) {
                if (!b(this.H)) {
                    i3 = a(this.H);
                } else {
                    i3 = !b(this.I) ? a(this.I) : 0;
                }
                setMeasuredDimension(getMeasuredWidth(), Math.min(a(this.G) + i3, mode == Integer.MIN_VALUE ? View.MeasureSpec.getSize(i2) : Integer.MAX_VALUE));
            }
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        super.onTouchEvent(motionEvent);
        return true;
    }

    public void setVisibility(int i) {
        super.setVisibility(i);
        boolean z = i == 0;
        Drawable drawable = this.f9457J;
        if (drawable != null) {
            drawable.setVisible(z, false);
        }
        Drawable drawable2 = this.K;
        if (drawable2 != null) {
            drawable2.setVisible(z, false);
        }
        Drawable drawable3 = this.L;
        if (drawable3 != null) {
            drawable3.setVisible(z, false);
        }
    }

    public ActionMode startActionModeForChild(View view, ActionMode.Callback callback) {
        return null;
    }

    public ActionMode startActionModeForChild(View view, ActionMode.Callback callback, int i) {
        if (i != 0) {
            return super.startActionModeForChild(view, callback, i);
        }
        return null;
    }

    public boolean verifyDrawable(Drawable drawable) {
        return (drawable == this.f9457J && !this.M) || (drawable == this.K && this.N) || ((drawable == this.L && this.M) || super.verifyDrawable(drawable));
    }
}
