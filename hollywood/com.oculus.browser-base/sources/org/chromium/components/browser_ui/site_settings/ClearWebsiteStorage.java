package org.chromium.components.browser_ui.site_settings;

import android.content.Context;
import android.util.AttributeSet;
import com.oculus.browser.R;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ClearWebsiteStorage extends WE {
    public static final /* synthetic */ int z0 = 0;
    public Context A0;
    public String B0;
    public boolean C0;

    public ClearWebsiteStorage(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.y0 = R.layout.f37300_resource_name_obfuscated_RES_2131624039;
        this.A0 = context;
    }

    @Override // androidx.preference.Preference
    public void x(C4886tF0 tf0) {
        super.x(tf0);
        this.u0 = this.A0.getString(this.C0 ? R.string.f65680_resource_name_obfuscated_RES_2131953885 : R.string.f65670_resource_name_obfuscated_RES_2131953884, this.B0);
    }

    public ClearWebsiteStorage(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i, 0);
        this.y0 = R.layout.f37300_resource_name_obfuscated_RES_2131624039;
        this.A0 = context;
    }
}
