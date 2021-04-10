package org.chromium.components.infobars;

import android.content.Context;
import android.text.style.ClickableSpan;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import org.chromium.ui.widget.TextViewWithClickableSpans;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class InfoBarMessageView extends TextViewWithClickableSpans {
    public boolean H;

    public InfoBarMessageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // org.chromium.ui.widget.TextViewWithClickableSpans
    public boolean onTouchEvent(MotionEvent motionEvent) {
        boolean onTouchEvent = super.onTouchEvent(motionEvent);
        if (!this.H && motionEvent.getActionMasked() == 1) {
            long eventTime = motionEvent.getEventTime() - motionEvent.getDownTime();
            boolean z = eventTime >= ((long) ViewConfiguration.getTapTimeout()) && eventTime <= ((long) ViewConfiguration.getLongPressTimeout());
            ClickableSpan[] b = b();
            if (z && b != null && b.length == 1 && !f(motionEvent)) {
                b[0].onClick(this);
            }
        }
        return onTouchEvent;
    }

    public final void setOnClickListener(View.OnClickListener onClickListener) {
        super.setOnClickListener(onClickListener);
        if (onClickListener != null) {
            this.H = true;
        }
    }
}
