package org.chromium.chrome.browser.ntp;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ExpandableListView;
import org.chromium.ui.base.DeviceFormFactor;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class RecentTabsExpandableListView extends ExpandableListView {
    public int F;
    public int G;
    public int H;

    public RecentTabsExpandableListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        setSelectionFromTop(this.G, this.H);
    }

    public void onDetachedFromWindow() {
        this.G = getFirstVisiblePosition();
        int i = 0;
        View childAt = getChildAt(0);
        if (childAt != null) {
            i = childAt.getTop();
        }
        this.H = i;
        super.onDetachedFromWindow();
    }

    public void onFinishInflate() {
        super.onFinishInflate();
        this.F = Math.round(getResources().getDisplayMetrics().density * 550.0f);
    }

    public void onMeasure(int i, int i2) {
        if (!DeviceFormFactor.a(getContext())) {
            super.onMeasure(i, i2);
            return;
        }
        int size = View.MeasureSpec.getSize(i) - this.F;
        int i3 = size > 0 ? (size / 2) + 0 : 0;
        setPadding(i3, 0, i3, 0);
        super.onMeasure(i, i2);
    }
}
