package org.chromium.components.browser_ui.settings;

import android.view.View;
import android.view.accessibility.AccessibilityManager;
import android.widget.Checkable;
import android.widget.CompoundButton;
import android.widget.TextView;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ChromeBaseCheckBoxPreference extends Jo1 {
    public final C0706Ln y0 = new C0706Ln(this);

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public ChromeBaseCheckBoxPreference(android.content.Context r4, android.util.AttributeSet r5) {
        /*
            r3 = this;
            r0 = 2130968703(0x7f04007f, float:1.7546067E38)
            r1 = 16842895(0x101008f, float:2.369396E-38)
            int r0 = defpackage.Ko1.a(r4, r0, r1)
            r1 = 0
            r3.<init>(r4, r5, r0, r1)
            Ln r2 = new Ln
            r2.<init>(r3)
            r3.y0 = r2
            int[] r2 = defpackage.FJ0.y
            android.content.res.TypedArray r4 = r4.obtainStyledAttributes(r5, r2, r0, r1)
            r5 = 5
            java.lang.String r5 = defpackage.Ko1.e(r4, r5, r1)
            r3.e0(r5)
            r5 = 4
            java.lang.String r5 = r4.getString(r5)
            if (r5 != 0) goto L_0x002f
            r5 = 1
            java.lang.String r5 = r4.getString(r5)
        L_0x002f:
            r3.c0(r5)
            r5 = 3
            r0 = 2
            boolean r0 = r4.getBoolean(r0, r1)
            boolean r5 = r4.getBoolean(r5, r0)
            r3.x0 = r5
            r4.recycle()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.components.browser_ui.settings.ChromeBaseCheckBoxPreference.<init>(android.content.Context, android.util.AttributeSet):void");
    }

    @Override // androidx.preference.Preference
    public void J(View view) {
        super.J(view);
        i0(view);
    }

    public final void h0(View view) {
        boolean z = view instanceof CompoundButton;
        if (z) {
            ((CompoundButton) view).setOnCheckedChangeListener(null);
        }
        if (view instanceof Checkable) {
            ((Checkable) view).setChecked(this.t0);
        }
        if (z) {
            ((CompoundButton) view).setOnCheckedChangeListener(this.y0);
        }
    }

    public final void i0(View view) {
        if (((AccessibilityManager) this.F.getSystemService("accessibility")).isEnabled()) {
            h0(view.findViewById(16908289));
            g0(view.findViewById(16908304));
        }
    }

    @Override // androidx.preference.Preference
    public void x(C4886tF0 tf0) {
        super.x(tf0);
        h0(tf0.x(16908289));
        f0(tf0);
        ((TextView) tf0.x(16908310)).setSingleLine(false);
        AbstractC1865bc0.c(null, this, tf0.G);
    }

    @Override // defpackage.Jo1, androidx.preference.Preference
    public void y() {
        if (!AbstractC1865bc0.d(null, this)) {
            super.y();
        }
    }
}
