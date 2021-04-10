package org.chromium.chrome.browser.contextmenu;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ListView;
import com.oculus.browser.R;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class RevampedContextMenuListView extends ListView {
    public RevampedContextMenuListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void onMeasure(int i, int i2) {
        int i3 = getResources().getDisplayMetrics().widthPixels;
        int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.f17740_resource_name_obfuscated_RES_2131165393);
        int dimensionPixelSize2 = getResources().getDimensionPixelSize(R.dimen.f24770_resource_name_obfuscated_RES_2131166096);
        int paddingLeft = ((View) getParent()).getPaddingLeft() * 2;
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(Math.min(dimensionPixelSize - paddingLeft, (i3 - (dimensionPixelSize2 * 2)) - paddingLeft), 1073741824), i2);
    }
}
