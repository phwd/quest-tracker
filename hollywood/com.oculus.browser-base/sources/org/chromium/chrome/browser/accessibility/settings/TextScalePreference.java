package org.chromium.chrome.browser.accessibility.settings;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.preference.Preference;
import com.oculus.browser.R;
import java.text.NumberFormat;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class TextScalePreference extends Preference implements SeekBar.OnSeekBarChangeListener {
    public float t0 = 0.5f;
    public float u0;
    public TextView v0;
    public TextView w0;
    public NumberFormat x0 = NumberFormat.getPercentInstance();

    public TextScalePreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.k0 = R.layout.f37630_resource_name_obfuscated_RES_2131624072;
        this.l0 = R.layout.f40830_resource_name_obfuscated_RES_2131624392;
    }

    public final void a0() {
        this.v0.setText(this.x0.format((double) this.t0));
        this.w0.setTextSize(1, this.u0 * 12.0f);
    }

    public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
        if (z) {
            float f = (((float) i) * 0.05f) + 0.5f;
            if (f != this.t0) {
                f(Float.valueOf(f));
            }
        }
    }

    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    public void onStopTrackingTouch(SeekBar seekBar) {
    }

    @Override // androidx.preference.Preference
    public void x(C4886tF0 tf0) {
        super.x(tf0);
        SeekBar seekBar = (SeekBar) tf0.x(R.id.seekbar);
        seekBar.setOnSeekBarChangeListener(this);
        seekBar.setMax(Math.round(30.0f));
        seekBar.setProgress(Math.round((this.t0 - 0.5f) / 0.05f));
        this.v0 = (TextView) tf0.x(R.id.seekbar_amount);
        this.w0 = (TextView) tf0.x(R.id.preview);
        a0();
    }
}
