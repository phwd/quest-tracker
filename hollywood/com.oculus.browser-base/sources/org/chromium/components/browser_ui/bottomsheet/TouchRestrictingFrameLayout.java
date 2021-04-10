package org.chromium.components.browser_ui.bottomsheet;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class TouchRestrictingFrameLayout extends FrameLayout {
    public BottomSheet F;

    public TouchRestrictingFrameLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        BottomSheet bottomSheet = this.F;
        if (bottomSheet == null || bottomSheet.U == 4) {
            return false;
        }
        return super.onInterceptTouchEvent(motionEvent);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        BottomSheet bottomSheet = this.F;
        if (bottomSheet == null || bottomSheet.U == 4) {
            return false;
        }
        return super.onTouchEvent(motionEvent);
    }
}
