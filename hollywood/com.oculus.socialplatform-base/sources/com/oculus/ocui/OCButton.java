package com.oculus.ocui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import com.oculus.socialplatform.R;

public class OCButton extends OCTextView {
    public OCEventHandler mEventHandler;
    public boolean mLoading;
    public View.OnClickListener mOnClickListener;
    public View.OnHoverListener mOnHoverListener;
    public AnimatedVectorDrawable mSpinner;
    public boolean mSpinnerBoundsSet = false;

    public OCEventHandler getEventHandler() {
        return this.mEventHandler;
    }

    public void onClickHaptics() {
        OCEventHandler oCEventHandler = this.mEventHandler;
        if (oCEventHandler != null) {
            oCEventHandler.onButtonClick();
        }
    }

    public void setLoading(boolean z) {
        this.mLoading = z;
        setEnabled(!z);
        if (this.mSpinner == null) {
            initializeSpinner();
        }
        if (!this.mLoading) {
            this.mSpinner.stop();
            this.mSpinnerBoundsSet = false;
        } else {
            this.mSpinner.start();
        }
        invalidate();
    }

    public OCButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, R.attr.ocButtonStyle);
        super.setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.ocui.$$Lambda$OCButton$lDOezPsweQaS6mvfH87JAMcEo82 */

            public final void onClick(View view) {
                OCButton.this.lambda$new$0$OCButton(view);
            }
        });
        super.setOnHoverListener(new View.OnHoverListener() {
            /* class com.oculus.ocui.$$Lambda$OCButton$RR3LKI7iz1GRoweKgorrtaSKcYc2 */

            public final boolean onHover(View view, MotionEvent motionEvent) {
                return OCButton.this.lambda$new$1$OCButton(view, motionEvent);
            }
        });
    }

    private Rect calculateSpinnerBounds(Rect rect) {
        int min = Math.min(getHeight(), getWidth());
        int i = (rect.right + rect.left) >> 1;
        int i2 = min >> 1;
        return new Rect(i - i2, 0, i + i2, min);
    }

    private void initializeSpinner() {
        AnimatedVectorDrawable animatedVectorDrawable = (AnimatedVectorDrawable) getContext().getDrawable(R.drawable.ocspinner_indeterminate_circle);
        this.mSpinner = animatedVectorDrawable;
        animatedVectorDrawable.setCallback(this);
    }

    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (this.mLoading) {
            if (!this.mSpinnerBoundsSet) {
                this.mSpinner.setBounds(calculateSpinnerBounds(canvas.getClipBounds()));
                this.mSpinnerBoundsSet = true;
            }
            this.mSpinner.draw(canvas);
        }
    }

    public boolean isFocused() {
        if (super.isHovered() || super.isFocused()) {
            return true;
        }
        return false;
    }

    public /* synthetic */ void lambda$new$0$OCButton(View view) {
        onClickHaptics();
        View.OnClickListener onClickListener = this.mOnClickListener;
        if (onClickListener != null) {
            onClickListener.onClick(view);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0033  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x003e A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ boolean lambda$new$1$OCButton(android.view.View r6, android.view.MotionEvent r7) {
        /*
            r5 = this;
            boolean r0 = r5.isClickable()
            r4 = 1
            if (r0 != 0) goto L_0x0008
            return r4
        L_0x0008:
            com.oculus.ocui.OCEventHandler r0 = r5.mEventHandler
            r3 = 9
            if (r0 == 0) goto L_0x0019
            int r0 = r7.getAction()
            if (r0 != r3) goto L_0x0019
            com.oculus.ocui.OCEventHandler r0 = r5.mEventHandler
            r0.onButtonEnter()
        L_0x0019:
            int r2 = r7.getActionMasked()
            r0 = 7
            r1 = 0
            if (r2 == r0) goto L_0x0038
            if (r2 == r3) goto L_0x0038
            r0 = 10
            if (r2 != r0) goto L_0x002f
            super.setHovered(r1)
            android.text.TextUtils$TruncateAt r0 = android.text.TextUtils.TruncateAt.END
        L_0x002c:
            super.setEllipsize(r0)
        L_0x002f:
            android.view.View$OnHoverListener r0 = r5.mOnHoverListener
            if (r0 == 0) goto L_0x003e
            boolean r0 = r0.onHover(r6, r7)
            return r0
        L_0x0038:
            super.setHovered(r4)
            android.text.TextUtils$TruncateAt r0 = android.text.TextUtils.TruncateAt.MARQUEE
            goto L_0x002c
        L_0x003e:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.ocui.OCButton.lambda$new$1$OCButton(android.view.View, android.view.MotionEvent):boolean");
    }

    public void setEnabled(boolean z) {
        super.setEnabled(z);
        setAlpha(OCConstants.getOpacity(getResources(), z));
    }

    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    public void setEventHandler(OCEventHandler oCEventHandler) {
        this.mEventHandler = oCEventHandler;
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.mOnClickListener = onClickListener;
    }

    public void setOnHoverListener(View.OnHoverListener onHoverListener) {
        this.mOnHoverListener = onHoverListener;
    }
}
