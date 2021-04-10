package org.chromium.chrome.browser.keyboard_accessory.sheet_component;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.viewpager.widget.ViewPager;
import com.oculus.browser.R;
import org.chromium.ui.base.LocalizationUtils;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class AccessorySheetView extends FrameLayout {
    public ViewPager F;
    public ImageView G;

    public AccessorySheetView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void onFinishInflate() {
        super.onFinishInflate();
        this.F = (ViewPager) findViewById(R.id.keyboard_accessory_sheet);
        this.G = (ImageView) findViewById(R.id.accessory_sheet_shadow);
        this.F.setLayoutDirection(LocalizationUtils.isLayoutRtl() ? 1 : 0);
    }
}
