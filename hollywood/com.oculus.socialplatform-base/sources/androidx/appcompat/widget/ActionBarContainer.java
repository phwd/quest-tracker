package androidx.appcompat.widget;

import X.AnonymousClass02C;
import X.AnonymousClass1Ey;
import X.AnonymousClass1F4;
import X.C11081qa;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import androidx.annotation.RestrictTo;
import com.oculus.socialplatform.R;

@RestrictTo({AnonymousClass02C.LIBRARY_GROUP_PREFIX})
public class ActionBarContainer extends FrameLayout {
    public Drawable A00;
    public Drawable A01;
    public Drawable A02;
    public boolean A03;
    public boolean A04;
    public View A05;
    public int A06;
    public View A07;
    public View A08;
    public boolean A09;

    public final ActionMode startActionModeForChild(View view, ActionMode.Callback callback) {
        return null;
    }

    public View getTabContainer() {
        return this.A05;
    }

    public final boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (this.A09 || super.onInterceptTouchEvent(motionEvent)) {
            return true;
        }
        return false;
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x005b  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x007e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onMeasure(int r7, int r8) {
        /*
        // Method dump skipped, instructions count: 151
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.ActionBarContainer.onMeasure(int, int):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x003a, code lost:
        if (r0 == null) goto L_0x003c;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setPrimaryBackground(android.graphics.drawable.Drawable r6) {
        /*
            r5 = this;
            android.graphics.drawable.Drawable r1 = r5.A00
            if (r1 == 0) goto L_0x000d
            r0 = 0
            r1.setCallback(r0)
            android.graphics.drawable.Drawable r0 = r5.A00
            r5.unscheduleDrawable(r0)
        L_0x000d:
            r5.A00 = r6
            if (r6 == 0) goto L_0x0033
            r6.setCallback(r5)
            android.view.View r0 = r5.A07
            if (r0 == 0) goto L_0x0033
            android.graphics.drawable.Drawable r4 = r5.A00
            int r3 = r0.getLeft()
            android.view.View r0 = r5.A07
            int r2 = r0.getTop()
            android.view.View r0 = r5.A07
            int r1 = r0.getRight()
            android.view.View r0 = r5.A07
            int r0 = r0.getBottom()
            r4.setBounds(r3, r2, r1, r0)
        L_0x0033:
            boolean r0 = r5.A03
            r1 = 1
            if (r0 == 0) goto L_0x0046
            android.graphics.drawable.Drawable r0 = r5.A01
        L_0x003a:
            if (r0 != 0) goto L_0x004d
        L_0x003c:
            r5.setWillNotDraw(r1)
            r5.invalidate()
            r5.invalidateOutline()
            return
        L_0x0046:
            android.graphics.drawable.Drawable r0 = r5.A00
            if (r0 != 0) goto L_0x004d
            android.graphics.drawable.Drawable r0 = r5.A02
            goto L_0x003a
        L_0x004d:
            r1 = 0
            goto L_0x003c
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.ActionBarContainer.setPrimaryBackground(android.graphics.drawable.Drawable):void");
    }

    public void setSplitBackground(Drawable drawable) {
        Drawable drawable2;
        Drawable drawable3;
        Drawable drawable4 = this.A01;
        if (drawable4 != null) {
            drawable4.setCallback(null);
            unscheduleDrawable(this.A01);
        }
        this.A01 = drawable;
        boolean z = false;
        if (drawable != null) {
            drawable.setCallback(this);
            if (this.A03 && (drawable3 = this.A01) != null) {
                drawable3.setBounds(0, 0, getMeasuredWidth(), getMeasuredHeight());
            }
        }
        if (this.A03) {
            drawable2 = this.A01;
        } else {
            if (this.A00 == null) {
                drawable2 = this.A02;
            }
            setWillNotDraw(z);
            invalidate();
            invalidateOutline();
        }
        if (drawable2 == null) {
            z = true;
        }
        setWillNotDraw(z);
        invalidate();
        invalidateOutline();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x003e, code lost:
        if (r0 == null) goto L_0x0040;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setStackedBackground(android.graphics.drawable.Drawable r6) {
        /*
            r5 = this;
            android.graphics.drawable.Drawable r1 = r5.A02
            if (r1 == 0) goto L_0x000d
            r0 = 0
            r1.setCallback(r0)
            android.graphics.drawable.Drawable r0 = r5.A02
            r5.unscheduleDrawable(r0)
        L_0x000d:
            r5.A02 = r6
            if (r6 == 0) goto L_0x0037
            r6.setCallback(r5)
            boolean r0 = r5.A04
            if (r0 == 0) goto L_0x0037
            android.graphics.drawable.Drawable r4 = r5.A02
            if (r4 == 0) goto L_0x0037
            android.view.View r0 = r5.A05
            int r3 = r0.getLeft()
            android.view.View r0 = r5.A05
            int r2 = r0.getTop()
            android.view.View r0 = r5.A05
            int r1 = r0.getRight()
            android.view.View r0 = r5.A05
            int r0 = r0.getBottom()
            r4.setBounds(r3, r2, r1, r0)
        L_0x0037:
            boolean r0 = r5.A03
            r1 = 1
            if (r0 == 0) goto L_0x004a
            android.graphics.drawable.Drawable r0 = r5.A01
        L_0x003e:
            if (r0 != 0) goto L_0x0051
        L_0x0040:
            r5.setWillNotDraw(r1)
            r5.invalidate()
            r5.invalidateOutline()
            return
        L_0x004a:
            android.graphics.drawable.Drawable r0 = r5.A00
            if (r0 != 0) goto L_0x0051
            android.graphics.drawable.Drawable r0 = r5.A02
            goto L_0x003e
        L_0x0051:
            r1 = 0
            goto L_0x0040
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.ActionBarContainer.setStackedBackground(android.graphics.drawable.Drawable):void");
    }

    public void setTabContainer(AnonymousClass1Ey r3) {
        View view = this.A05;
        if (view != null) {
            removeView(view);
        }
        this.A05 = r3;
        if (r3 != null) {
            addView(r3);
            throw new NullPointerException("getLayoutParams");
        }
    }

    public void setTransitioning(boolean z) {
        this.A09 = z;
        int i = 262144;
        if (z) {
            i = 393216;
        }
        setDescendantFocusability(i);
    }

    public final boolean verifyDrawable(Drawable drawable) {
        if ((drawable != this.A00 || this.A03) && ((drawable != this.A02 || !this.A04) && ((drawable != this.A01 || !this.A03) && !super.verifyDrawable(drawable)))) {
            return false;
        }
        return true;
    }

    public final void drawableStateChanged() {
        super.drawableStateChanged();
        Drawable drawable = this.A00;
        if (drawable != null && drawable.isStateful()) {
            this.A00.setState(getDrawableState());
        }
        Drawable drawable2 = this.A02;
        if (drawable2 != null && drawable2.isStateful()) {
            this.A02.setState(getDrawableState());
        }
        Drawable drawable3 = this.A01;
        if (drawable3 != null && drawable3.isStateful()) {
            this.A01.setState(getDrawableState());
        }
    }

    public final void jumpDrawablesToCurrentState() {
        super.jumpDrawablesToCurrentState();
        Drawable drawable = this.A00;
        if (drawable != null) {
            drawable.jumpToCurrentState();
        }
        Drawable drawable2 = this.A02;
        if (drawable2 != null) {
            drawable2.jumpToCurrentState();
        }
        Drawable drawable3 = this.A01;
        if (drawable3 != null) {
            drawable3.jumpToCurrentState();
        }
    }

    public final void onFinishInflate() {
        super.onFinishInflate();
        this.A07 = findViewById(R.id.action_bar);
        this.A08 = findViewById(R.id.action_context_bar);
    }

    public final boolean onHoverEvent(MotionEvent motionEvent) {
        super.onHoverEvent(motionEvent);
        return true;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0033, code lost:
        if (r7.A03 == false) goto L_0x0048;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0035, code lost:
        r2 = r7.A01;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0037, code lost:
        if (r2 == null) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0039, code lost:
        r2.setBounds(0, 0, getMeasuredWidth(), getMeasuredHeight());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0044, code lost:
        invalidate();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x004a, code lost:
        if (r7.A00 == null) goto L_0x0072;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0052, code lost:
        if (r7.A07.getVisibility() != 0) goto L_0x008e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0054, code lost:
        r6 = r7.A00;
        r4 = r7.A07.getLeft();
        r2 = r7.A07.getTop();
        r1 = r7.A07.getRight();
        r0 = r7.A07;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x006a, code lost:
        r6.setBounds(r4, r2, r1, r0.getBottom());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0071, code lost:
        r6 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0072, code lost:
        r7.A04 = r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0074, code lost:
        if (r3 == false) goto L_0x00b5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0076, code lost:
        r4 = r7.A02;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0078, code lost:
        if (r4 == null) goto L_0x00b5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x007a, code lost:
        r4.setBounds(r5.getLeft(), r5.getTop(), r5.getRight(), r5.getBottom());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x008e, code lost:
        r0 = r7.A08;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0090, code lost:
        if (r0 == null) goto L_0x00af;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0096, code lost:
        if (r0.getVisibility() != 0) goto L_0x00af;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0098, code lost:
        r6 = r7.A00;
        r4 = r7.A08.getLeft();
        r2 = r7.A08.getTop();
        r1 = r7.A08.getRight();
        r0 = r7.A08;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x00af, code lost:
        r7.A00.setBounds(0, 0, 0, 0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x00b5, code lost:
        if (r6 == false) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:3:0x000f, code lost:
        if (r5.getVisibility() == 8) goto L_0x0011;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0012, code lost:
        if (r5 != null) goto L_0x0014;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0018, code lost:
        if (r5.getVisibility() == 8) goto L_0x0031;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x001a, code lost:
        r4 = getMeasuredHeight();
        r1 = r4 - r5.getMeasuredHeight();
        r0 = ((android.widget.FrameLayout.LayoutParams) r5.getLayoutParams()).bottomMargin;
        r5.layout(r9, r1 - r0, r11, r4 - r0);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onLayout(boolean r8, int r9, int r10, int r11, int r12) {
        /*
        // Method dump skipped, instructions count: 184
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.ActionBarContainer.onLayout(boolean, int, int, int, int):void");
    }

    public final boolean onTouchEvent(MotionEvent motionEvent) {
        super.onTouchEvent(motionEvent);
        return true;
    }

    public void setVisibility(int i) {
        super.setVisibility(i);
        boolean z = false;
        if (i == 0) {
            z = true;
        }
        Drawable drawable = this.A00;
        if (drawable != null) {
            drawable.setVisible(z, false);
        }
        Drawable drawable2 = this.A02;
        if (drawable2 != null) {
            drawable2.setVisible(z, false);
        }
        Drawable drawable3 = this.A01;
        if (drawable3 != null) {
            drawable3.setVisible(z, false);
        }
    }

    public ActionBarContainer(Context context) {
        this(context, null);
    }

    public ActionBarContainer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Drawable drawable;
        setBackground(new AnonymousClass1F4(this));
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C11081qa.A00);
        this.A00 = obtainStyledAttributes.getDrawable(0);
        this.A02 = obtainStyledAttributes.getDrawable(2);
        this.A06 = obtainStyledAttributes.getDimensionPixelSize(13, -1);
        if (getId() == R.id.split_action_bar) {
            this.A03 = true;
            this.A01 = obtainStyledAttributes.getDrawable(1);
        }
        obtainStyledAttributes.recycle();
        boolean z = false;
        if (this.A03) {
            drawable = this.A01;
        } else {
            drawable = this.A00 == null ? this.A02 : drawable;
            setWillNotDraw(z);
        }
        if (drawable == null) {
            z = true;
        }
        setWillNotDraw(z);
    }

    public final ActionMode startActionModeForChild(View view, ActionMode.Callback callback, int i) {
        if (i != 0) {
            return super.startActionModeForChild(view, callback, i);
        }
        return null;
    }
}
