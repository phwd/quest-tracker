package androidx.preference;

import android.view.View;
import android.view.accessibility.AccessibilityManager;
import android.widget.Checkable;
import android.widget.Switch;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class SwitchPreference extends Jo1 {
    @Override // androidx.preference.Preference
    public void J(View view) {
        super.J(view);
        if (((AccessibilityManager) this.F.getSystemService("accessibility")).isEnabled()) {
            h0(view.findViewById(16908352));
            g0(view.findViewById(16908304));
        }
    }

    public final void h0(View view) {
        boolean z = view instanceof Switch;
        if (z) {
            ((Switch) view).setOnCheckedChangeListener(null);
        }
        if (view instanceof Checkable) {
            ((Checkable) view).setChecked(this.t0);
        }
        if (z) {
            Switch r5 = (Switch) view;
            r5.setTextOn(null);
            r5.setTextOff(null);
            r5.setOnCheckedChangeListener(null);
        }
    }

    @Override // androidx.preference.Preference
    public void x(C4886tF0 tf0) {
        super.x(tf0);
        h0(tf0.x(16908352));
        f0(tf0);
    }
}
