package org.chromium.chrome.browser.keyboard_accessory.sheet_tabs;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.oculus.browser.R;
import org.chromium.ui.widget.ChipView;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class CreditCardAccessoryInfoView extends LinearLayout {
    public ImageView F;
    public ChipView G;
    public LinearLayout H;
    public ChipView I;

    /* renamed from: J  reason: collision with root package name */
    public ChipView f10692J;
    public ChipView K;

    public CreditCardAccessoryInfoView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void onFinishInflate() {
        super.onFinishInflate();
        this.F = (ImageView) findViewById(R.id.icon);
        this.G = (ChipView) findViewById(R.id.cc_number);
        this.H = (LinearLayout) findViewById(R.id.exp_group);
        this.I = (ChipView) findViewById(R.id.exp_month);
        this.f10692J = (ChipView) findViewById(R.id.exp_year);
        this.K = (ChipView) findViewById(R.id.cardholder);
    }
}
