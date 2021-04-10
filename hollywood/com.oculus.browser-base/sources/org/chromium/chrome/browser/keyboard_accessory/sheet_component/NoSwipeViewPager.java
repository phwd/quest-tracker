package org.chromium.chrome.browser.keyboard_accessory.sheet_component;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import androidx.viewpager.widget.ViewPager;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class NoSwipeViewPager extends ViewPager {
    public NoSwipeViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // androidx.viewpager.widget.ViewPager
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return false;
    }

    @Override // androidx.viewpager.widget.ViewPager
    public boolean onTouchEvent(MotionEvent motionEvent) {
        return false;
    }

    @Override // androidx.viewpager.widget.ViewPager
    public void x(int i) {
        this.e0 = false;
        z(i, false, false, 0);
    }
}
