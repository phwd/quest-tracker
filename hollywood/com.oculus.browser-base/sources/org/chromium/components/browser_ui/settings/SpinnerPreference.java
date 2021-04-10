package org.chromium.components.browser_ui.settings;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import androidx.preference.Preference;
import com.oculus.browser.R;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class SpinnerPreference extends Preference {
    public Spinner t0;
    public ArrayAdapter u0;
    public int v0;
    public final boolean w0;

    public SpinnerPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, FJ0.D0);
        boolean z = obtainStyledAttributes.getBoolean(0, false);
        this.w0 = z;
        obtainStyledAttributes.recycle();
        if (z) {
            this.k0 = R.layout.f40810_resource_name_obfuscated_RES_2131624390;
        } else {
            this.k0 = R.layout.f40800_resource_name_obfuscated_RES_2131624389;
        }
    }

    @Override // androidx.preference.Preference
    public void x(C4886tF0 tf0) {
        super.x(tf0);
        ((TextView) tf0.x(R.id.title)).setText(this.M);
        Spinner spinner = (Spinner) tf0.x(R.id.spinner);
        this.t0 = spinner;
        spinner.setOnItemSelectedListener(new PY0(this));
        SpinnerAdapter adapter = this.t0.getAdapter();
        ArrayAdapter arrayAdapter = this.u0;
        if (adapter != arrayAdapter) {
            this.t0.setAdapter((SpinnerAdapter) arrayAdapter);
        }
        this.t0.setSelection(this.v0);
    }
}
