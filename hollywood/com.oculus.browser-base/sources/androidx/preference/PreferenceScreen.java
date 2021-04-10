package androidx.preference;

import android.content.Context;
import android.util.AttributeSet;
import com.oculus.browser.R;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class PreferenceScreen extends AbstractC2837hF0 {
    public boolean C0 = true;

    public PreferenceScreen(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, Ko1.a(context, R.attr.f7060_resource_name_obfuscated_RES_2130969152, 16842891), 0);
    }

    @Override // androidx.preference.Preference
    public void y() {
        AbstractC4033oF0 of0;
        if (this.R == null && this.S == null && d0() != 0 && (of0 = this.G.j) != null) {
            AbstractC2324eF0 ef0 = (AbstractC2324eF0) of0;
            ef0.x();
            ef0.u();
        }
    }
}
