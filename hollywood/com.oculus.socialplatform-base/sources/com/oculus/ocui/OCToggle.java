package com.oculus.ocui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Switch;
import com.oculus.socialplatform.R;

public final class OCToggle extends Switch {
    public OCEventHandler mEventHandler;
    public View.OnClickListener mOnClickListener;
    public View.OnHoverListener mOnHoverListener;

    public /* synthetic */ void lambda$new$0$OCToggle(View view) {
        OCEventHandler oCEventHandler = this.mEventHandler;
        if (oCEventHandler != null) {
            oCEventHandler.onButtonClick();
        }
        View.OnClickListener onClickListener = this.mOnClickListener;
        if (onClickListener != null) {
            onClickListener.onClick(view);
        }
    }

    public /* synthetic */ boolean lambda$new$1$OCToggle(View view, MotionEvent motionEvent) {
        if (this.mEventHandler != null && motionEvent.getAction() == 9) {
            this.mEventHandler.onButtonEnter();
        }
        View.OnHoverListener onHoverListener = this.mOnHoverListener;
        if (onHoverListener != null) {
            return onHoverListener.onHover(view, motionEvent);
        }
        return false;
    }

    public OCToggle(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        super.setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.ocui.$$Lambda$OCToggle$hboXUTRWVwSEGVt6_JC8h7flxuE2 */

            public final void onClick(View view) {
                OCToggle.this.lambda$new$0$OCToggle(view);
            }
        });
        super.setOnHoverListener(new View.OnHoverListener() {
            /* class com.oculus.ocui.$$Lambda$OCToggle$LW50A3Z3B_Bmn0fBzdaEYyqHNh02 */

            public final boolean onHover(View view, MotionEvent motionEvent) {
                return OCToggle.this.lambda$new$1$OCToggle(view, motionEvent);
            }
        });
        setShowText(false);
        setBackground(null);
        setThumbResource(R.drawable.octoggle_thumb);
        setTrackResource(R.drawable.octoggle_track);
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
