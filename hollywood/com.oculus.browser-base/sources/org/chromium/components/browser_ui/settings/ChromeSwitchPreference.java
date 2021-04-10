package org.chromium.components.browser_ui.settings;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import android.widget.Checkable;
import android.widget.TextView;
import androidx.appcompat.widget.SwitchCompat;
import com.oculus.browser.R;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ChromeSwitchPreference extends Jo1 {
    public CharSequence A0;
    public AbstractC1528Zb0 B0;
    public final M41 y0 = new M41(this);
    public CharSequence z0;

    public ChromeSwitchPreference(Context context) {
        super(context, null, R.attr.f8140_resource_name_obfuscated_RES_2130969260, 0);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(null, FJ0.F0, R.attr.f8140_resource_name_obfuscated_RES_2130969260, 0);
        e0(Ko1.e(obtainStyledAttributes, 7, 0));
        String string = obtainStyledAttributes.getString(6);
        c0(string == null ? obtainStyledAttributes.getString(1) : string);
        String string2 = obtainStyledAttributes.getString(9);
        this.z0 = string2 == null ? obtainStyledAttributes.getString(3) : string2;
        s();
        String string3 = obtainStyledAttributes.getString(8);
        this.A0 = string3 == null ? obtainStyledAttributes.getString(4) : string3;
        s();
        this.x0 = obtainStyledAttributes.getBoolean(5, obtainStyledAttributes.getBoolean(2, false));
        obtainStyledAttributes.recycle();
    }

    @Override // androidx.preference.Preference
    public void J(View view) {
        super.J(view);
        i0(view);
    }

    public final void h0(View view) {
        boolean z = view instanceof SwitchCompat;
        if (z) {
            ((SwitchCompat) view).setOnCheckedChangeListener(null);
        }
        if (view instanceof Checkable) {
            ((Checkable) view).setChecked(this.t0);
        }
        if (z) {
            SwitchCompat switchCompat = (SwitchCompat) view;
            switchCompat.V = this.z0;
            switchCompat.requestLayout();
            switchCompat.W = this.A0;
            switchCompat.requestLayout();
            switchCompat.setOnCheckedChangeListener(this.y0);
        }
    }

    public final void i0(View view) {
        if (((AccessibilityManager) this.F.getSystemService("accessibility")).isEnabled()) {
            h0(view.findViewById(R.id.switchWidget));
            g0(view.findViewById(16908304));
        }
    }

    @Override // androidx.preference.Preference
    public void x(C4886tF0 tf0) {
        super.x(tf0);
        h0(tf0.x(R.id.switchWidget));
        f0(tf0);
        TextView textView = (TextView) tf0.x(16908310);
        textView.setSingleLine(false);
        if (TextUtils.isEmpty(this.M)) {
            TextView textView2 = (TextView) tf0.x(16908304);
            textView.setText(textView2.getText());
            textView.setVisibility(0);
            textView2.setVisibility(8);
        }
        AbstractC1865bc0.c(this.B0, this, tf0.G);
    }

    @Override // defpackage.Jo1, androidx.preference.Preference
    public void y() {
        if (!AbstractC1865bc0.d(this.B0, this)) {
            super.y();
        }
    }

    public ChromeSwitchPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, R.attr.f8140_resource_name_obfuscated_RES_2130969260, 0);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, FJ0.F0, R.attr.f8140_resource_name_obfuscated_RES_2130969260, 0);
        e0(Ko1.e(obtainStyledAttributes, 7, 0));
        String string = obtainStyledAttributes.getString(6);
        c0(string == null ? obtainStyledAttributes.getString(1) : string);
        String string2 = obtainStyledAttributes.getString(9);
        this.z0 = string2 == null ? obtainStyledAttributes.getString(3) : string2;
        s();
        String string3 = obtainStyledAttributes.getString(8);
        this.A0 = string3 == null ? obtainStyledAttributes.getString(4) : string3;
        s();
        this.x0 = obtainStyledAttributes.getBoolean(5, obtainStyledAttributes.getBoolean(2, false));
        obtainStyledAttributes.recycle();
    }
}
