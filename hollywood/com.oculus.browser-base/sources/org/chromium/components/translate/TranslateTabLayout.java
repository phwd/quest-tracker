package org.chromium.components.translate;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.MotionEvent;
import com.google.android.material.tabs.TabLayout;
import com.oculus.browser.R;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class TranslateTabLayout extends TabLayout {
    public D81 w0;
    public int x0;
    public int y0;

    public TranslateTabLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, FJ0.H0, 0, R.style.f75000_resource_name_obfuscated_RES_2132018073);
        int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(15, 0);
        this.y0 = dimensionPixelSize;
        this.x0 = dimensionPixelSize;
        this.x0 = obtainStyledAttributes.getDimensionPixelSize(18, dimensionPixelSize);
        this.y0 = obtainStyledAttributes.getDimensionPixelSize(17, this.y0);
    }

    @Override // com.google.android.material.tabs.TabLayout
    public void c(D81 d81, int i, boolean z) {
        if (d81.e instanceof TranslateTabContent) {
            super.c(d81, i, z);
            return;
        }
        throw new IllegalArgumentException();
    }

    @Override // com.google.android.material.tabs.TabLayout
    public void d(D81 d81, boolean z) {
        if (d81.e instanceof TranslateTabContent) {
            c(d81, this.G.size(), z);
            return;
        }
        throw new IllegalArgumentException();
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (this.w0 != null) {
            return true;
        }
        return super.onInterceptTouchEvent(motionEvent);
    }
}
