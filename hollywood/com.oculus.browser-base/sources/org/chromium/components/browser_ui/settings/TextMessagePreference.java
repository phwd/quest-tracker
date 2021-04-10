package org.chromium.components.browser_ui.settings;

import android.content.Context;
import android.text.method.LinkMovementMethod;
import android.util.AttributeSet;
import android.widget.TextView;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class TextMessagePreference extends ChromeBasePreference {
    public TextMessagePreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Q(false);
        R(false);
    }

    @Override // androidx.preference.Preference, org.chromium.components.browser_ui.settings.ChromeBasePreference
    public void x(C4886tF0 tf0) {
        super.x(tf0);
        ((TextView) tf0.x(16908304)).setMovementMethod(LinkMovementMethod.getInstance());
    }
}
