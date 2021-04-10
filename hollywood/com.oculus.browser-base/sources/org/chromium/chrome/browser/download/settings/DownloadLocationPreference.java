package org.chromium.chrome.browser.download.settings;

import android.content.Context;
import android.text.SpannableStringBuilder;
import android.text.style.StyleSpan;
import android.util.AttributeSet;
import com.oculus.browser.R;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class DownloadLocationPreference extends WE implements AbstractC3525lH {
    public WH z0;

    public DownloadLocationPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.y0 = R.layout.f38070_resource_name_obfuscated_RES_2131624116;
        WH wh = new WH(this.F, this);
        this.z0 = wh;
        wh.b();
    }

    @Override // defpackage.AbstractC3525lH
    public void a() {
        a0();
    }

    public void a0() {
        WH wh = this.z0;
        int i = wh.G;
        if (i >= 0) {
            LF lf = (LF) wh.getItem(i);
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
            spannableStringBuilder.append((CharSequence) lf.f8414a);
            spannableStringBuilder.append((CharSequence) " ");
            spannableStringBuilder.append((CharSequence) lf.b);
            spannableStringBuilder.setSpan(new StyleSpan(1), 0, lf.f8414a.length(), 33);
            T(spannableStringBuilder);
        }
    }

    @Override // defpackage.AbstractC3525lH
    public void b() {
        WH wh = this.z0;
        int i = wh.G;
        int i2 = C3696mH.F;
        if (i == -1) {
            wh.c();
        }
        a0();
    }
}
