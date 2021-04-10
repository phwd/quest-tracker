package org.chromium.chrome.browser.language.settings;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import org.chromium.base.LocaleUtils;
import org.chromium.components.browser_ui.settings.ChromeBasePreference;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class LanguageItemPickerPreference extends ChromeBasePreference {
    public B60 x0;
    public boolean y0;

    public LanguageItemPickerPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void a0(String str) {
        B60 b60;
        if (TextUtils.equals(str, null)) {
            b60 = B60.a();
        } else {
            T60 a2 = T60.a();
            B60 b602 = (B60) a2.b.get(str);
            if (b602 != null) {
                b60 = b602;
            } else {
                b60 = (B60) a2.b.get(LocaleUtils.b(str));
            }
        }
        this.x0 = b60;
        b0();
    }

    public final void b0() {
        B60 b60 = this.x0;
        if (b60 != null) {
            if (this.y0) {
                V(b60.b);
                T(this.x0.c);
                return;
            }
            T(b60.b);
        }
    }
}
