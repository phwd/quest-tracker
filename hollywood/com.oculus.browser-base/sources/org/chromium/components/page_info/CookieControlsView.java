package org.chromium.components.page_info;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.widget.SwitchCompat;
import com.oculus.browser.R;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class CookieControlsView extends LinearLayout implements CompoundButton.OnCheckedChangeListener {
    public final SwitchCompat F;
    public final TextView G = ((TextView) findViewById(R.id.cookie_controls_blocked_cookies_text));
    public boolean H;
    public FA I;

    public CookieControlsView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setOrientation(1);
        setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
        LayoutInflater.from(context).inflate(R.layout.f37600_resource_name_obfuscated_RES_2131624069, (ViewGroup) this, true);
        SwitchCompat switchCompat = (SwitchCompat) findViewById(R.id.cookie_controls_block_cookies_switch);
        this.F = switchCompat;
        switchCompat.setOnCheckedChangeListener(this);
    }

    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        if (z != this.H) {
            this.I.f7997a.onResult(Boolean.valueOf(z));
        }
    }
}
