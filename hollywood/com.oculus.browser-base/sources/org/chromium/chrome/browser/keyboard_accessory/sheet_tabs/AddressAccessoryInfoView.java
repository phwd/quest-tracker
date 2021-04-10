package org.chromium.chrome.browser.keyboard_accessory.sheet_tabs;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import com.oculus.browser.R;
import org.chromium.ui.widget.ChipView;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class AddressAccessoryInfoView extends LinearLayout {
    public ChipView F;
    public ChipView G;
    public ChipView H;
    public ChipView I;

    /* renamed from: J  reason: collision with root package name */
    public ChipView f10691J;
    public ChipView K;
    public ChipView L;
    public ChipView M;
    public ChipView N;
    public ChipView O;

    public AddressAccessoryInfoView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void onFinishInflate() {
        super.onFinishInflate();
        this.F = (ChipView) findViewById(R.id.name_full);
        this.G = (ChipView) findViewById(R.id.company_name);
        this.H = (ChipView) findViewById(R.id.address_home_line_1);
        this.I = (ChipView) findViewById(R.id.address_home_line_2);
        this.f10691J = (ChipView) findViewById(R.id.address_home_zip);
        this.K = (ChipView) findViewById(R.id.address_home_city);
        this.L = (ChipView) findViewById(R.id.address_home_state);
        this.M = (ChipView) findViewById(R.id.address_home_country);
        this.N = (ChipView) findViewById(R.id.phone_home_whole_number);
        this.O = (ChipView) findViewById(R.id.email_address);
    }
}
