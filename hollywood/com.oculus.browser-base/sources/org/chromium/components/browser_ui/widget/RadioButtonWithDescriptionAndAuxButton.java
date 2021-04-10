package org.chromium.components.browser_ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.ImageButton;
import com.oculus.browser.R;
import org.chromium.chrome.browser.safe_browsing.settings.RadioButtonGroupSafeBrowsingPreference;
import org.chromium.chrome.browser.safe_browsing.settings.SafeBrowsingSettingsFragment;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class RadioButtonWithDescriptionAndAuxButton extends RadioButtonWithDescription {
    public NJ0 K;
    public ImageButton L;

    public RadioButtonWithDescriptionAndAuxButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setPaddingRelative(getPaddingStart(), getPaddingTop(), 0, getPaddingBottom());
        View findViewById = findViewById(R.id.radio_container);
        findViewById.setPaddingRelative(findViewById.getPaddingStart(), findViewById.getPaddingTop(), getResources().getDimensionPixelSize(R.dimen.f24600_resource_name_obfuscated_RES_2131166079), findViewById.getPaddingBottom());
    }

    @Override // org.chromium.components.browser_ui.widget.RadioButtonWithDescription
    public int b() {
        return R.layout.f38380_resource_name_obfuscated_RES_2131624147;
    }

    @Override // org.chromium.components.browser_ui.widget.RadioButtonWithDescription
    public void i() {
        super.i();
        this.L = (ImageButton) findViewById(R.id.expand_arrow);
    }

    public final void j() {
        NJ0 nj0 = this.K;
        int id = getId();
        RadioButtonGroupSafeBrowsingPreference radioButtonGroupSafeBrowsingPreference = (RadioButtonGroupSafeBrowsingPreference) nj0;
        if (radioButtonGroupSafeBrowsingPreference.x0 && id == radioButtonGroupSafeBrowsingPreference.t0.getId()) {
            ((SafeBrowsingSettingsFragment) radioButtonGroupSafeBrowsingPreference.z0).n1(2);
        } else if (id == radioButtonGroupSafeBrowsingPreference.u0.getId()) {
            ((SafeBrowsingSettingsFragment) radioButtonGroupSafeBrowsingPreference.z0).n1(1);
        }
    }

    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setLabeledBy(this.L);
    }

    @Override // org.chromium.components.browser_ui.widget.RadioButtonWithDescription
    public void setEnabled(boolean z) {
        super.setEnabled(z);
        this.L.setEnabled(z);
    }
}
