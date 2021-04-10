package com.oculus.ocui;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CheckBox;
import com.oculus.socialplatform.R;

public final class OCCheckbox extends CheckBox {
    public OCEventHandler mEventHandler;
    public View.OnClickListener mOnClickListener;
    public View.OnHoverListener mOnHoverListener;

    public /* synthetic */ void lambda$new$0$OCCheckbox(View view) {
        OCEventHandler oCEventHandler = this.mEventHandler;
        if (oCEventHandler != null) {
            oCEventHandler.onButtonClick();
        }
        View.OnClickListener onClickListener = this.mOnClickListener;
        if (onClickListener != null) {
            onClickListener.onClick(view);
        }
    }

    public /* synthetic */ boolean lambda$new$1$OCCheckbox(View view, MotionEvent motionEvent) {
        if (this.mEventHandler != null && motionEvent.getAction() == 9) {
            this.mEventHandler.onButtonEnter();
        }
        View.OnHoverListener onHoverListener = this.mOnHoverListener;
        if (onHoverListener != null) {
            return onHoverListener.onHover(view, motionEvent);
        }
        return false;
    }

    public OCCheckbox(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        super.setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.ocui.$$Lambda$OCCheckbox$2A0VaImhu09LGJT825xml8ddDwI2 */

            public final void onClick(View view) {
                OCCheckbox.this.lambda$new$0$OCCheckbox(view);
            }
        });
        super.setOnHoverListener(new View.OnHoverListener() {
            /* class com.oculus.ocui.$$Lambda$OCCheckbox$gTbnkQJRCSK1hw_SURCxedkQszg2 */

            public final boolean onHover(View view, MotionEvent motionEvent) {
                return OCCheckbox.this.lambda$new$1$OCCheckbox(view, motionEvent);
            }
        });
        setButtonDrawable((Drawable) null);
        setBackground(context.getDrawable(R.drawable.occheckbox_background));
    }

    public void setEnabled(boolean z) {
        super.setEnabled(z);
        setAlpha(OCConstants.getOpacity(getResources(), z));
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
