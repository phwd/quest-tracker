package org.chromium.chrome.browser.autofill.prefeditor;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.GridView;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ExpandableGridView extends GridView {
    public ExpandableGridView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void onMeasure(int i, int i2) {
        if (getLayoutParams().height == -2) {
            i2 = View.MeasureSpec.makeMeasureSpec(16777215, Integer.MIN_VALUE);
        }
        super.onMeasure(i, i2);
    }
}
