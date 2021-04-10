package org.chromium.chrome.browser.about_settings;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import androidx.preference.Preference;
import org.chromium.base.ContextUtils;
import org.chromium.base.LocaleUtils;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class HyperlinkPreference extends Preference {
    public final int t0;

    public HyperlinkPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, FJ0.W, 0, 0);
        this.t0 = obtainStyledAttributes.getResourceId(0, 0);
        obtainStyledAttributes.recycle();
        R(false);
    }

    @Override // androidx.preference.Preference
    public void y() {
        AbstractActivityC5822yn1.r1(ContextUtils.a(this.F), this.F.getString(this.t0).replace("$LOCALE", LocaleUtils.getDefaultLocaleString().replace('-', '_')));
    }
}
