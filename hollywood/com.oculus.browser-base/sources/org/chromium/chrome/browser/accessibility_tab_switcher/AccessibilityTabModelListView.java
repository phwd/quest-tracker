package org.chromium.chrome.browser.accessibility_tab_switcher;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ListAdapter;
import android.widget.ListView;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class AccessibilityTabModelListView extends ListView {
    public final L F = new L(getContext(), this);
    public boolean G = true;

    public AccessibilityTabModelListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void onFinishInflate() {
        super.onFinishInflate();
        setAdapter((ListAdapter) this.F);
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (!this.G) {
            return false;
        }
        return super.onInterceptTouchEvent(motionEvent);
    }
}
