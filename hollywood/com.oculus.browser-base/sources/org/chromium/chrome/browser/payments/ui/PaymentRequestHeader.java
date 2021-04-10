package org.chromium.chrome.browser.payments.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import com.oculus.browser.R;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class PaymentRequestHeader extends FrameLayout {
    public final int F = getResources().getColor(R.color.f14550_resource_name_obfuscated_RES_2131100145);
    public Context G;

    public PaymentRequestHeader(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.G = context;
    }

    public void onFinishInflate() {
        super.onFinishInflate();
        setBackgroundColor(this.F);
    }
}
