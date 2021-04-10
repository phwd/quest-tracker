package org.chromium.chrome.browser.tasks.tab_management;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import androidx.appcompat.widget.SwitchCompat;
import com.oculus.browser.R;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class PriceTrackingDialogView extends LinearLayout {
    public SwitchCompat F;
    public SwitchCompat G;

    public PriceTrackingDialogView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void onFinishInflate() {
        super.onFinishInflate();
        this.F = (SwitchCompat) findViewById(R.id.track_prices_switch);
        this.G = (SwitchCompat) findViewById(R.id.price_alerts_switch);
    }
}
