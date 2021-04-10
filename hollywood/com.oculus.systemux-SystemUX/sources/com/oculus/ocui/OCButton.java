package com.oculus.ocui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import com.oculus.common.ocui.R;

public class OCButton extends OCTextView {
    private OCEventHandler mEventHandler;
    private boolean mLoading;
    private View.OnClickListener mOnClickListener;
    private View.OnHoverListener mOnHoverListener;
    private AnimatedVectorDrawable mSpinner;
    private boolean mSpinnerBoundsSet = false;

    public OCButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, R.attr.ocButtonStyle);
        super.setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.ocui.$$Lambda$OCButton$lDOezPsweQaS6mvfH87JAMcEo8 */

            public final void onClick(View view) {
                OCButton.this.lambda$new$0$OCButton(view);
            }
        });
        super.setOnHoverListener(new View.OnHoverListener() {
            /* class com.oculus.ocui.$$Lambda$OCButton$RR3LKI7iz1GRoweKgorrtaSKcYc */

            public final boolean onHover(View view, MotionEvent motionEvent) {
                return OCButton.this.lambda$new$1$OCButton(view, motionEvent);
            }
        });
    }

    public /* synthetic */ void lambda$new$0$OCButton(View view) {
        onClickHaptics();
        View.OnClickListener onClickListener = this.mOnClickListener;
        if (onClickListener != null) {
            onClickListener.onClick(view);
        }
    }

    public /* synthetic */ boolean lambda$new$1$OCButton(View view, MotionEvent motionEvent) {
        if (!isClickable()) {
            return true;
        }
        if (this.mEventHandler != null && motionEvent.getAction() == 9) {
            this.mEventHandler.onButtonEnter();
        }
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 7 || actionMasked == 9) {
            super.setHovered(true);
            super.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        } else if (actionMasked == 10) {
            super.setHovered(false);
            super.setEllipsize(TextUtils.TruncateAt.END);
        }
        View.OnHoverListener onHoverListener = this.mOnHoverListener;
        if (onHoverListener != null) {
            return onHoverListener.onHover(view, motionEvent);
        }
        return false;
    }

    public void setEventHandler(OCEventHandler oCEventHandler) {
        this.mEventHandler = oCEventHandler;
    }

    /* access modifiers changed from: protected */
    public OCEventHandler getEventHandler() {
        return this.mEventHandler;
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.mOnClickListener = onClickListener;
    }

    public void setOnHoverListener(View.OnHoverListener onHoverListener) {
        this.mOnHoverListener = onHoverListener;
    }

    public boolean isFocused() {
        return super.isHovered() || super.isFocused();
    }

    public void setEnabled(boolean z) {
        super.setEnabled(z);
        setAlpha(OCConstants.getOpacity(getResources(), z));
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

    private void initializeSpinner() {
        this.mSpinner = (AnimatedVectorDrawable) getContext().getDrawable(R.drawable.ocspinner_indeterminate_circle);
        this.mSpinner.setCallback(this);
    }

    private Rect calculateSpinnerBounds(Rect rect) {
        int min = Math.min(getHeight(), getWidth());
        int i = (rect.right + rect.left) / 2;
        int i2 = min / 2;
        return new Rect(i - i2, 0, i + i2, min);
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    /* access modifiers changed from: protected */
    public void onClickHaptics() {
        OCEventHandler oCEventHandler = this.mEventHandler;
        if (oCEventHandler != null) {
            oCEventHandler.onButtonClick();
        }
    }
}
