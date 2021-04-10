package org.chromium.chrome.browser.tasks.tab_management;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import org.chromium.components.browser_ui.widget.RoundedCornerImageView;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class TabGridThumbnailView extends RoundedCornerImageView {
    public float P = 1.0f;

    public TabGridThumbnailView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();
        if (AbstractC4772sd1.d() && (getDrawable() == null || (this.P != 1.0f && (getDrawable() instanceof ColorDrawable)))) {
            measuredHeight = (int) ((((double) measuredWidth) * 1.0d) / ((double) this.P));
        }
        setMeasuredDimension(measuredWidth, measuredHeight);
    }
}
