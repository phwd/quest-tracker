package org.chromium.components.browser_ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.PointerIcon;
import android.view.View;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class CoordinatorLayoutForPointer extends CoordinatorLayout {
    public CoordinatorLayoutForPointer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setFocusable(false);
        setImportantForAccessibility(2);
    }

    public PointerIcon onResolvePointerIcon(MotionEvent motionEvent, int i) {
        int x = (int) motionEvent.getX(i);
        int y = (int) motionEvent.getY(i);
        for (int childCount = getChildCount() - 1; childCount >= 0; childCount--) {
            if (getChildAt(childCount).getVisibility() == 0) {
                View childAt = getChildAt(childCount);
                if (x >= childAt.getLeft() && x <= childAt.getRight() && y >= childAt.getTop() && y <= childAt.getBottom()) {
                    return getChildAt(childCount).onResolvePointerIcon(motionEvent, i);
                }
            }
        }
        return super.onResolvePointerIcon(motionEvent, i);
    }
}
